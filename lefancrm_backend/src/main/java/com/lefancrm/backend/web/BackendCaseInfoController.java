package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.util.FileUtils;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by lixianfeng on 2018/2/26.
 */
@Controller
public class BackendCaseInfoController extends BackendBaseController {

    @Value("${file.server.rootZipDir}")
    private String zipUrl;

    @Value("${file.server.rootDir}")
    private String imgUrl;

    @RequestMapping(value = "/case/caseCenterInfoList")
    public ModelAndView caseCenterInfoList(HttpServletRequest req , HttpServletResponse rsp){
        String type = req.getParameter("type");
        String caseId = req.getParameter("caseId");
        String caseState = req.getParameter("caseState");
        String orgName = req.getParameter("orgName");
        String orgUserName = req.getParameter("orgUserName");
        String caseNo = req.getParameter("caseNo");
        String caseName = req.getParameter("caseName");
        String caseTel = req.getParameter("caseTel");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_LIST, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("type",type==null?"":type);
        model.put("caseId",caseId==null?"":caseId);
        model.put("caseState",caseState==null?"":caseState);
        model.put("orgName",orgName==null?"":orgName);
        model.put("orgUserName",orgUserName==null?"":orgUserName);
        model.put("caseNo",caseNo==null?"":caseNo);
        model.put("caseName",caseName==null?"":caseName);
        model.put("caseTel",caseTel==null?"":caseTel);
        String isRole = this.callApi(BackendApiMethodEnum.BACKEND_IS_ROLE, null, req);
        Type isRoleType = new TypeToken<ApiFinalResponse<Boolean>>() {
        }.getType();
        ApiFinalResponse<Boolean> isRoleApiRsp = JsonUtil.jsonToObject(isRole, isRoleType);
        if(!Boolean.parseBoolean(String.valueOf(isRoleApiRsp.getResults()))){
            model.put("isRole",0);
        }else{
            model.put("isRole",1);
        }
        return new ModelAndView("/case/caseCenterInfoList",model);
    }

    /**
     * 案件列表
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "/case/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        //listType 列表类型
        //评估案件列表  0评估列表、1待评估列表
        //案件风控     2一审待审核列表、3二审待审核列表
        //案件风控     4待提交苏宁贷款、5已提交苏宁贷款
        //索赔案件列表 6待索赔列表、
        //案件风控    7索赔预案审核列表
        //索赔案件列表 77预案通过列表
        //案件风控     777结案报告审核
        //索赔案件列表  8需代扣列表、9案件列表、10待结案列表、11结案列表
        //财务管理     12已结案案件
        //案件管理     13紧急代扣
        Long listType = Long.valueOf(req.getParameter("listType"));

        model.put("listType",listType);
        String type = req.getParameter("type");
        String caseId = req.getParameter("caseId");
        String caseState = req.getParameter("caseState");
        String orgName = req.getParameter("orgName");
        String orgUserName = req.getParameter("orgUserName");
        String caseNo = req.getParameter("caseNo");
        String caseName = req.getParameter("caseName");
        String caseTel = req.getParameter("caseTel");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoNewDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_NEW_LIST, null, req);
        model.put("apiRsp",apiFinalResponse);
        model.put("type",type==null?"":type);
        model.put("caseId",caseId==null?"":caseId);
        model.put("caseState",caseState==null?"":caseState);
        model.put("orgName",orgName==null?"":orgName);
        model.put("orgUserName",orgUserName==null?"":orgUserName);
        model.put("caseNo",caseNo==null?"":caseNo);
        model.put("caseName",caseName==null?"":caseName);
        model.put("caseTel",caseTel==null?"":caseTel);
        return new ModelAndView("/case/caseCenterInfoListNew",model);
    }

//    @RequestMapping("/case/bankInfo")
//    public String bankInfo(HttpServletRequest req,HttpServletResponse rsp){
//        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_INFO,null,req,rsp);
//    }
    /**
     * 业务操作   提交
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/case/operate")
    public String operate(HttpServletRequest req,HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_CENTER_NEW_OPERATE,null,req,rsp);
    }

    /**
     * 退回
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/case/back")
    public ModelAndView back(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        Long id = Long.valueOf(req.getParameter("id"));
        Long listType = Long.valueOf(req.getParameter("listType"));//列表类型,0 评估列表  1,待评估列表  2评审主管列表(一审)  3风控主管列表(二审) 4未提交苏宁贷款的  5已提价苏宁贷款
        model.put("listType",listType);
        model.put("id",id);
        return new ModelAndView("/case/caseCenterInfoListNewBack",model);
    }

    /**
     * 补票
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/case/openBill")
    public ModelAndView openBill(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        Long id = Long.valueOf(req.getParameter("id"));
        Long listType = Long.valueOf(req.getParameter("listType"));//列表类型,0 评估列表  1,待评估列表  2评审主管列表(一审)  3风控主管列表(二审) 4未提交苏宁贷款的  5已提价苏宁贷款
        model.put("listType",listType);
        model.put("id",id);
        Map<String,Object> map = new HashMap<String,Object>();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<PaymentEstimateInquiryDto>>(){};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_PAY_INQUIRY_INFO,map,req);
        model.put("payEstimateInquiry",apiFinalResponse.getResults());
        return new ModelAndView("/case/caseCenterInfoListNewOpen",model);
    }


    /**
     *
     * 查看单证
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/case/selectFileMid")
    public String selectFileMid(HttpServletRequest req,HttpServletResponse rsp){
        String viewType = req.getParameter("viewType");
        String caseNo = req.getParameter("caseNo");
        String caseId = req.getParameter("caseId");
        if ("treeClick".equals(viewType)){//无刷新查看单证
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_FILE_ADDRESS, null, req,rsp);
        }

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>(){};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_CASE_FILE_ENUM,null,req);
        List<CommonEnumDto> commonEnumDtos = (List<CommonEnumDto>)apiFinalResponse.getResults();

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("caseNo",caseNo);
        paramMap.put("enumId",commonEnumDtos.get(0).getEnumCode());
        TypeToken token = new TypeToken<ApiFinalResponse<List<CaseFileMidDto>>>(){};
        ApiFinalResponse response = this.callApi(token, BackendApiMethodEnum.BACKEND_CASE_FILE,paramMap,req);
        List<CaseFileMidDto> caseFileMidDtos = (List<CaseFileMidDto>)(response == null ? null : response.getResults());
        req.setAttribute("caseNo",caseNo);
        req.setAttribute("enums",commonEnumDtos);
        req.setAttribute("caseFile",caseFileMidDtos);
        req.setAttribute("caseId",caseId);
        return "/case/caseFileMid";
    }

    /**
     *
     * 放大查看单证图片
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/case/caseFileMidShow")
    public String caseFileMidShow(HttpServletRequest req,HttpServletResponse rsp){
        String caseNo = req.getParameter("caseNo");
        String catalogId = req.getParameter("catalogId");
        String viewType = req.getParameter("viewType");
        if (viewType !=null){//无刷新查看单证
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_FILE_ADDRESS, null, req,rsp);
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("caseNo",caseNo);
        paramMap.put("catalogId",catalogId);
        TypeToken token = new TypeToken<ApiFinalResponse<List<CaseFileMidDto>>>(){};
        ApiFinalResponse response = this.callApi(token, BackendApiMethodEnum.BACKEND_CASE_FILE,paramMap,req);
        List<CaseFileMidDto> caseFileMidDtos = (List<CaseFileMidDto>)(response == null ? null : response.getResults());
        req.setAttribute("caseFile",caseFileMidDtos);

        String index = req.getParameter("index");
        req.setAttribute("id",index);
        return "/case/caseFileMidShow";
    }

    /**
     *
     * 保存上传单证图片
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/case/uploadCommonFileImg")
    public String uploadCommonFileImg(HttpServletRequest req,HttpServletResponse rsp){

        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPLOAD_COMMON_FILE_IMG,null,req,rsp);
    }
    /**
     * 查看索赔报告
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "/case/caseMediation")
    public ModelAndView caseMediation(HttpServletRequest req, HttpServletResponse rsp) throws Exception{
        Map<String,Object> model = new HashMap<>();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<HashMap<String,CaseMediationClaimDto>>>() {};
        this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_MEDIATION_CLAIM, null, req);
        //索赔信息费用
        typeToken = new TypeToken<ApiFinalResponse<List<CaseMediationClaimReportDto>>>(){};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_CASE_MEDIATION_CLAIM_REPORT,null,req);
        model.put("dtos",apiFinalResponse.getResults());

        typeToken = new TypeToken<ApiFinalResponse<HashMap<String,CaseMediationClaimDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_MEDIATION_CLAIM, null, req);
        model.put("apiRsp",apiFinalResponse.getResults());
        String op = req.getParameter("op");
        model.put("op", op);

        model.put("caseNo", req.getParameter("caseNo"));
        model.put("caseId", req.getParameter("caseId"));
        model.put("stepCode",req.getParameter("stepCode"));

        String type = req.getParameter("type");
        if("3".equals(type)){
            return new ModelAndView("/case/report/caseMediation",model);
        }else if("33".equals(type)){
            return new ModelAndView("/case/report/caseMediationNew",model);
        }
        return new ModelAndView("/case/report/caseMediation",model);
    }

    //索赔报告保存
    @RequestMapping(value = "/case/saveCaseMediation")
    public String saveCaseMediation(HttpServletRequest req,HttpServletResponse rsp) throws Exception {
        String id = req.getParameter("id");
        if (StringUtils.isEmpty(id)) {
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADD_CASE_MEDIATION_CLAIM, null, req, rsp);
        } else {
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPD_CASE_MEDIATION_CLAIM, null, req, rsp);
        }
    }
    //索赔信息费用保存
    @RequestMapping(value = "/case/updMediationClaimReport")
    public String updMediationClaimReport(HttpServletRequest req,HttpServletResponse rsp) throws Exception {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPD_CASE_MEDIATION_CLAIM_REPORT, null, req, rsp);
    }

    /**
     * 案件经办跟踪
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "/case/selectCaseDetails")
    public ModelAndView selectCaseDetails (HttpServletRequest req, HttpServletResponse rsp) {
        TypeToken typeToken = new TypeToken<ApiFinalResponse<BackendCaseDetailsDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_CASEDETAILS, null, req);
        BackendCaseDetailsDto caseDetailsDto = (BackendCaseDetailsDto) apiFinalResponse.getResults();
        Map model = new HashMap();
        model.put("caseDetailsDto", caseDetailsDto);
        return new ModelAndView("/case/caseDetails",model);
    }

    /**
     * 从服务器中下载图片
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/download")
    public void downloadMedia(HttpServletResponse response, HttpServletRequest request) {
        OutputStream os = null;
        InputStream fis = null;
        try {
            List<File> fileList = new ArrayList<>();
            String arr[] = request.getParameterValues("files");
            for(String str : arr){
                if (str.indexOf("openapi.shlefan.com") > 1){//如果是32服务器的文件 不下载
                    continue;
                }
//                String newUrl = str.substring(str.indexOf("images")+6,str.length());

                imgUrl = "/mnt/sftp/files/";
                String newUrl = str.substring(str.indexOf("/ddrapi.shlefan.com/sftp/files")+30,str.length());
                newUrl = imgUrl + newUrl;
                fileList.add(new File(newUrl));
            }

            zipUrl = "/mnt/sftp/files/zip/";
            File file = FileUtils.zip(zipUrl, fileList);
            fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            response.reset();
            // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            os.write(buffer);// 输出文件
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(os);
        }
    }

    /**
    *   超时列表
    */
    @RequestMapping(value = "/case/overtimeCaseCenterInfoList")
    public ModelAndView overtimeCaseCenterInfoList(HttpServletRequest req, HttpServletResponse rsp) {
        String overTimeType = req.getParameter("overTimeType");
        String caseNo = req.getParameter("caseNo");
        String caseName = req.getParameter("caseName");
        String salesmanName = req.getParameter("salesmanName");
        String salesmanPhone = req.getParameter("salesmanPhone");
        String isTestcase = req.getParameter("isTestcase");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_OVERTIMELIST, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("overTimeType",overTimeType==null?"":overTimeType);
        model.put("caseNo",caseNo==null?"":caseNo);
        model.put("caseName",caseName==null?"":caseName);
        model.put("salesmanName",salesmanName==null?"":salesmanName);
        model.put("salesmanPhone",salesmanPhone==null?"":salesmanPhone);

        //判断登录人是不是测试人员
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<UserInfo>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_USER_INFO, null, req);
        UserInfo userInfoDto = (UserInfo)apiFinalResponse1.getResults();
        if(isTestcase == null){
            if(userInfoDto.getIsTester() ==1){
                //测试人员
                model.put("isTestcase",1);
            }else{
                model.put("isTestcase",0);
            }
        }else if(isTestcase == ""){
            model.put("isTestcase",-1);
        }else {
            model.put("isTestcase", isTestcase == null ? "" : isTestcase);
        }

        return new ModelAndView("/case/overtimeCaseCenterInfoList",model);
    }


    /**
     *   还款清单列表
     *
     */
    @RequestMapping(value = "/case/repayCaseCenterInfoList")
    public ModelAndView repayCaseCenterInfoList(HttpServletRequest req, HttpServletResponse rsp) {
        String gradationState = req.getParameter("gradationState");
        String handOutFlag = req.getParameter("handOutFlag");
        String caseNo = req.getParameter("caseNo");
        String caseName = req.getParameter("caseName");
        String isTestcase = req.getParameter("isTestcase");

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_REPAY_LIST, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("gradationState",gradationState==null?"":gradationState);
        model.put("handOutFlag",handOutFlag==null?"":handOutFlag);
        model.put("caseNo",caseNo==null?"":caseNo);
        model.put("caseName",caseName==null?"":caseName);
        model.put("pageSize",req.getParameter("pageSize"));

        //判断登录人是不是测试人员
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<UserInfo>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_USER_INFO, null, req);
        UserInfo userInfoDto = (UserInfo)apiFinalResponse1.getResults();
        if(isTestcase == null){
            if(userInfoDto.getIsTester() ==1){
                //测试人员
                model.put("isTestcase",1);
            }else{
                model.put("isTestcase",0);
            }
        }else if(isTestcase == ""){
            model.put("isTestcase",-1);
        }else {
            model.put("isTestcase", isTestcase == null ? "" : isTestcase);
        }
        return new ModelAndView("/case/repay/caseCenterInfoRepayList",model);
    }

    /**
     *   还款清单的详情页面
     *
     */
    @RequestMapping(value = "/case/repayCaseCenterInfoView")
    public ModelAndView repayCaseCenterInfoView(HttpServletRequest req, HttpServletResponse rsp) {

        TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseCenterInfoDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_FOR_REPAY, null, req);
        Map model = new HashMap();
        model.put("caseCenterInfo",apiFinalResponse.getResults());

        return new ModelAndView("/case/repay/caseCenterInfoRepayView",model);
    }

    /**
     *   操作还款页面
     *
     */
    @RequestMapping(value = "/case/haldleRepayCaseCenterInfo")
    public ModelAndView haldleRepayCaseCenterInfo(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();

        String id = req.getParameter("id");
        model.put("id",id);

        TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseCenterInfoDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_FOR_REPAY, null, req);
        model.put("caseCenterInfo",apiFinalResponse.getResults());

        typeToken = new TypeToken<ApiFinalResponse<CaseCenterExtendDto>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_EXTEND_INFO_VIEW, null, req);
        model.put("caseCenterExtend",apiFinalResponse.getResults());

        return new ModelAndView("/case/repay/uploadRepayCaseCenterInfoImg",model);
    }

    /**
     * 操作还款确认
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/case/updHaldleRepayCaseCenterInfo")
    public String updHaldleRepayCaseCenterInfo(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_HALDLE_REPAY_CASE_CENTER_INFO_UPD,null,req,rsp);
    }

    /**
     *   还款确认列表
     *
     */
    @RequestMapping(value = "/case/confirmRepayCaseCenterInfoList")
    public ModelAndView confirmRepayCaseCenterInfoList(HttpServletRequest req, HttpServletResponse rsp) {

        String gradationState = req.getParameter("gradationState");
        String caseNo = req.getParameter("caseNo");
        String caseName = req.getParameter("caseName");
        String isTestcase = req.getParameter("isTestcase");

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_CONFIRM_REPAY_LIST, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("gradationState",gradationState==null?"":gradationState);
        model.put("caseNo",caseNo==null?"":caseNo);
        model.put("caseName",caseName==null?"":caseName);
        //判断登录人是不是测试人员
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<UserInfo>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_USER_INFO, null, req);
        UserInfo userInfoDto = (UserInfo)apiFinalResponse1.getResults();
        if(isTestcase == null){
            if(userInfoDto.getIsTester() ==1){
                //测试人员
                model.put("isTestcase",1);
            }else{
                model.put("isTestcase",0);
            }
        }else if(isTestcase == ""){
            model.put("isTestcase",-1);
        }else {
            model.put("isTestcase", isTestcase == null ? "" : isTestcase);
        }

        return new ModelAndView("/case/repay/caseCenterInfoConfirmRepayList",model);
    }

    /**
     *   还款确认的详情页面
     *
     */
    @RequestMapping(value = "/case/caseCenterInfoConfirmRepayView")
    public ModelAndView caseCenterInfoConfirmRepayView(HttpServletRequest req, HttpServletResponse rsp) {

        TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseCenterInfoDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_CONFIRM_REPAY_VIEW, null, req);
        Map model = new HashMap();
        model.put("caseCenterInfo",apiFinalResponse.getResults());

        return new ModelAndView("/case/repay/caseCenterInfoConfirmRepayView",model);
    }

    /**
     *   确认还款
     *
     */
    @RequestMapping(value = "/case/updCaseCenterInfoConfirmRepay")
    public String updCaseCenterInfoConfirmRepay(HttpServletRequest req, HttpServletResponse rsp) {

        String fromType = req.getParameter("fromType");
        if(fromType !=null && "32Type".equals(fromType)) {//索赔案件的批量下载（临时使用）
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_DOWN_FILE_THREE_TWO, null, req, rsp);
        }
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_CENTER_CONFIRM_REPAY_UPD,null,req,rsp);
    }

}
