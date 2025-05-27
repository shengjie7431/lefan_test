package com.lefancrm.apicenter.util.tsign;

import com.lefancrm.apicenter.util.tsign.eSign.SignHelper;
import com.lefancrm.apicenter.util.tsign.utils.FileHelper;
import com.timevale.esign.sdk.tech.bean.PosBean;
import com.timevale.esign.sdk.tech.bean.SignPDFStreamBean;
import com.timevale.esign.sdk.tech.bean.result.AddSealResult;
import com.timevale.esign.sdk.tech.bean.result.FileDigestSignResult;
import com.timevale.esign.sdk.tech.impl.constants.SignType;
import com.timevale.esign.sdk.tech.service.UserSignService;
import com.timevale.esign.sdk.tech.service.factory.UserSignServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by DELL on 2018/3/8.
 */
public class RiskReportSign {
    private static Logger LOG = LoggerFactory.getLogger(RiskReportSign.class);

    public static void main(String[] args) {

        // 待签署的PDF文件路径
        String srcPdfFile = "F:\\maven\\test\\评估报告\\JR_评估报告.pdf";
        // 最终签署后的PDF文件路径
        String signedFolder ="F:\\maven\\test\\";
        // 最终签署后PDF文件名称
        String signedFileName = "signed1.pdf";

        // 企业印章图片文件路径
        String organizeImgFilePath = "F:\\maven\\test\\评估报告\\20180313100923.png";
        // 初始化项目，做全局使用，只初始化一次即可
        SignHelper.initProject();
        String companyName = "上海乐凡金融信息服务有限公司";
        String organCode = "913101080677536805";
        String companyAddress = "共和新路340号";
        String agentName = "艾利";
        String agentIdNo = "220301198705170035";
        doSignWithImageSealByStream(srcPdfFile,signedFolder,signedFileName,organizeImgFilePath,companyName,organCode, companyAddress,agentName, agentIdNo);
    }

    /***
     * 上传印章图片签署，所用印章SealData为印章图片的Base64数据
     *
     * @param srcPdfFile
     * @param signedFolder
     * @param signedFileName
     */
    public static void doSignWithImageSealByStream(String srcPdfFile, String signedFolder, String signedFileName,String organizeImgFilePath,String companyName,String organCode, String companyAddress, String agentName, String agentIdNo) {

        // 创建企业客户账号
        String userOrganizeAccountId = SignHelper.addOrganizeAccount(companyName,organCode, companyAddress,agentName, agentIdNo);
//        // 创建企业印章
//        AddSealResult userOrganizeSealData = SignHelper.addOrganizeTemplateSeal(userOrganizeAccountId);
        // 通过上传的印章图片获取企业印章数据
		String organizeSealData = SignHelper.getSealDataByImage(organizeImgFilePath);

        // 企业客户签署,坐标定位,以文件流的方式传递pdf文档
        FileDigestSignResult userOrganizeSignResult = userOrganizeSignByStream(FileHelper.getBytes(srcPdfFile), userOrganizeAccountId, organizeSealData);

        // 所有签署完成,将最终签署后的文件流保存到本地
        if (0 == userOrganizeSignResult.getErrCode()) {
            SignHelper.saveSignedByStream(userOrganizeSignResult.getStream(), signedFolder, signedFileName);
        }
    }

    /***
     * 平台下企业用户PDF摘要签署（文件二进制流）；盖章位置通过关键字定位； 使用到接口：UserSignServiceFactory.instance();
     * userSignService.localSignPDF(accountId,addSealResult.getSealData(),
     * signPDFStreamBean, posBean, SignType.Single);
     */
    public static FileDigestSignResult userOrganizeSignByStream(byte[] pdfFileStream, String accountId,
                                                                String sealData) {

        // 设置文件流签署的PDF文档信息
        SignPDFStreamBean signPDFStreamBean = setSignPDFStreamBean(pdfFileStream);
        // 设置坐标定位签署的PosBean，坐标定位方式支持单页签章、多页签章和骑缝章，但对关键字签章指定页码无效；
        PosBean posBean = setXYPosBean("0",480,100);
        // 设置签署类型为 关键字签章
        SignType signType = SignType.Key;

        System.out.println("----开始平台企业客户的PDF摘要签署...");
        UserSignService userSignService = UserSignServiceFactory.instance();
        FileDigestSignResult fileDigestSignResult = userSignService.localSignPDF(accountId, sealData, signPDFStreamBean,
                posBean, signType);
        if (0 != fileDigestSignResult.getErrCode()) {
            LOG.info("平台企业客户的PDF摘要签署失败，errCode=" + fileDigestSignResult.getErrCode() + " msg="
                    + fileDigestSignResult.getMsg());
        } else {
            System.out.println("平台企业客户的PDF摘要签署成功！SignServiceId = " + fileDigestSignResult.getSignServiceId());
        }
        return fileDigestSignResult;
    }

    /***
     * 文件流签署的PDF文档信息
     */
    public static SignPDFStreamBean setSignPDFStreamBean(byte[] pdfFileStream) {
        SignPDFStreamBean signPDFStreamBean = new SignPDFStreamBean();
        // 待签署文档本地二进制数据
        signPDFStreamBean.setStream(pdfFileStream);
        // 文档名称，e签宝签署日志对应的文档名，若为空则取文档路径中的名称
        // signPDFStreamBean.setFileName("pdf文件名");
        // 文档编辑密码，当目标PDF设置权限密码保护时必填 */
        // signPDFStreamBean.setOwnerPassword(null);
        return signPDFStreamBean;
    }

    /***
     * 坐标定位签署的PosBean
     */
    public static PosBean setXYPosBean(String page,int x,int y) {
        PosBean posBean = new PosBean();
        // 定位类型，0-坐标定位，1-关键字定位，默认0，若选择关键字定位，签署类型(signType)必须指定为关键字签署才会生效。
        posBean.setPosType(1);
        // 签署页码，若为多页签章，支持页码格式“1-3,5,8“，若为坐标定位时，不可空
        posBean.setPosPage("1");
        // 签署位置X坐标，默认值为0，以pdf页面的左下角作为原点，控制横向移动距离，单位为px
       // posBean.setPosX(x);
        // 签署位置Y坐标，默认值为0，以pdf页面的左下角作为原点，控制纵向移动距离，单位为px
        //posBean.setPosY(y);
        // 印章展现宽度，将以此宽度对印章图片做同比缩放。详细查阅接口文档的15 PosBean描述
        posBean.setWidth(159);
        posBean.setKey("专用章");
        return posBean;
    }
}
