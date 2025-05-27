package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCrmCaseInfoApi;
import com.lefancrm.apicenter.dao.AgentApplyMapper;
import com.lefancrm.apicenter.dao.CrmCaseInfoMapper;
import com.lefancrm.apicenter.dao.CrmCustomerFollowsMapper;
import com.lefancrm.apicenter.dao.CrmCustomerInfoMapper;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.HttpClientUtils;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jun on 2017/12/15.
 */
@Service
@ApiService(descript = "案件信息api")
public class BackendCrmCaseInfoApiImpl extends BaseServiceImpl implements BackendCrmCaseInfoApi {


    @Value("${xcx_url}")
    protected String xcxUrl;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private CrmCaseInfoMapper crmCaseInfoMapper;
    @Autowired
    private CrmCustomerFollowsMapper crmCustomerFollowsMapper;

    @Autowired
    private CrmCustomerInfoMapper crmCustomerInfoMapper;


    /**
     * 查询案件信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "查询案件信息", value = "backend-query-case-info-details")
    @Override
    public ApiResponse queryCaseInfoDetails(ApiRequest apiReq) {
        Long customerId = apiReq.getLong("customerId");
        CrmCaseInfo cci = crmCaseInfoMapper.selectCaseByCustomerId(customerId);
        CrmCustomerFollows ccf = crmCustomerFollowsMapper.selectByPrimaryCustomerId(customerId);
        cci.setCrmCustomerFollows(ccf);
        return new ApiResponse(ApiMsgEnum.SUCCESS,cci==null?0:1,cci);
    }


    /**
     * 编辑案件信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "编辑案件信息", value = "backend-edit-case-info")
    @Override
    public ApiResponse editCaseInfo(ApiRequest apiReq) throws IOException {
        int number = 0;
        Integer caseSource = apiReq.getInt("caseSource");
        Integer isIntention = apiReq.getInt("isIntention");
        Integer caseType = apiReq.getInt("caseType");
        Integer caseProgress = apiReq.getInt("caseProgress");
        Double claimFee = apiReq.getDouble("claimFee");
        Double loanFee = apiReq.getDouble("loanFee");
        Double serviceFee = apiReq.getDouble("serviceFee");
        Date followTime = DateUtils.parseDate(apiReq.getString("followTime"),"yyyy-MM-dd HH:mm:ss");//*必填
        String followAddress = apiReq.getString("followAddress");//*必填
        Date nextFollowTime =DateUtils.parseDate(apiReq.getString("nextFollowTime"),"yyyy-MM-dd HH:mm:ss");//*下次跟进时间
        String followDesc = apiReq.getString("followDesc");//*备注

        Double longitude = apiReq.getDouble("longitude");
        Double latitude = apiReq.getDouble("latitude");
        Long customerId = apiReq.getLong("customerId");
        String customerName = apiReq.getString("customerName");
        Integer followType = 4; //1:电话，2:qq/微信，3：拜访，4：其他
        if(caseSource !=null){
            number+=1;
        }
        if(isIntention !=null){
            number+=1;
        }
        if(caseType !=null){
            number+=1;
        }
        if(caseProgress !=null){
            number+=1;
        }
        if(claimFee!=null){
            number+=1;
        }
        if(loanFee!=null){
            number+=1;
        }
        if(serviceFee!=null){
            number+=1;
        }
        if(followTime!=null){
            number+=1;
        }
        if(followAddress !=null){
            number+=1;
        }
        if(nextFollowTime != null){
            number+=1;
        }
        if(followDesc != null){
            number+=1;
        }
        Double dataRate = (double)25/11*number;
        CrmCaseInfo cci = new CrmCaseInfo();
        cci.setCaseSource(caseSource);
        cci.setIsIntention(isIntention);
        cci.setCaseType(caseType);
        cci.setCaseProgress(caseProgress);
        cci.setClaimFee(claimFee);
        cci.setLoanFee(loanFee);
        cci.setServiceFee(serviceFee);
        cci.setUpdateTime(new Date());
        cci.setDataRate(dataRate);
        cci.setCustomerId(customerId);
        CrmCaseInfo caseInfo  = crmCaseInfoMapper.selectCaseByCustomerId(customerId);
        int result = crmCaseInfoMapper.updateByPrimaryCustomerIdSelective(cci);
        if(result>0){
            CrmCustomerFollows ccf = new CrmCustomerFollows();
            ccf.setCustomerId(customerId);
            ccf.setCustomerName(customerName);
            ccf.setFollowTime(followTime);
            ccf.setFollowAddress(followAddress);
            ccf.setLongitude(longitude);
            ccf.setLatitude(latitude);
            ccf.setFollowState(caseProgress);
            ccf.setNextFollowTime(nextFollowTime);
            ccf.setFollowDesc(followDesc);
            ccf.setFollowType(followType);
            ccf.setCreateTime(new Date());
            int resultCcf = crmCustomerFollowsMapper.insertSelective(ccf);
            if(resultCcf>0){
                if(caseInfo.getUpdateTime()==null){
                    CrmCustomerInfo customerInfo = crmCustomerInfoMapper.selectByPrimaryKey(customerId);
                    if(caseType ==1 || caseType==2){
                        // 这里开始调用接口
                        NameValuePair[] data = new NameValuePair[29];
                        data[0] =new NameValuePair("apiKey","ca89e65c77be0d3f0d732cc3134edaf4");
                        data[1] =new NameValuePair("apiSecret","32f48148e40fcf5586c269f65e6045b5");
                        data[2] =new NameValuePair("functionCodeInput","insert-agent-applyCrm");
                        data[3] =new NameValuePair("paramName","ccName");
                        data[4] =new NameValuePair("paramValue",customerInfo.getCcName());
                        data[5] =new NameValuePair("paramName","userId");
                        data[6] =new NameValuePair("paramValue",String.valueOf(customerInfo.getCcId()));
                        data[7] =new NameValuePair("paramName","userName");
                        data[8] =new NameValuePair("paramValue",customerName);
                        data[9] =new NameValuePair("paramName","userPhone");
                        data[10] =new NameValuePair("paramValue",customerInfo.getUserPhone());
                        data[11] =new NameValuePair("paramName","accidentProvince");
                        data[12] =new NameValuePair("paramValue",customerInfo.getProvince());
                        data[13] =new NameValuePair("paramName","accidentCity");
                        data[14] =new NameValuePair("paramValue",customerInfo.getCity());
                        data[15] =new NameValuePair("paramName","accidentDistrict");
                        data[16] =new NameValuePair("paramValue",customerInfo.getDistrict());
                        data[17] =new NameValuePair("paramName","accidentProvinceId");
                        data[18] =new NameValuePair("paramValue",String.valueOf(customerInfo.getProvinceId()));
                        data[19] =new NameValuePair("paramName","accidentCityId");
                        data[20] =new NameValuePair("paramValue",String.valueOf(customerInfo.getCityId()));
                        data[21] =new NameValuePair("paramName","accidentDistrictId");
                        data[22] =new NameValuePair("paramValue",String.valueOf(customerInfo.getDistrictId()));
                        data[23] =new NameValuePair("paramName","accidentAddress");
                        data[24] =new NameValuePair("paramValue",customerInfo.getFamilyAddress());
                        data[25] =new NameValuePair("paramName","agentType");
                        data[26] =new NameValuePair("paramValue","1");
                        data[27] =new NameValuePair("paramName","accidentTime");
                        data[28] =new NameValuePair("paramValue",sdf.format(new Date()));
                       String res = HttpClientUtils.httpPost(xcxUrl,data);
                        System.out.println(res);
                    }else{
                        //信息同步到贷款垫付中loan_application
                        //这里开始调用接口
                        NameValuePair[] data = new NameValuePair[25];
                        data[0] =new NameValuePair("apiKey","ca89e65c77be0d3f0d732cc3134edaf4");
                        data[1] =new NameValuePair("apiSecret","32f48148e40fcf5586c269f65e6045b5");
                        data[2] =new NameValuePair("functionCodeInput","insert-loan-applicationCrm");
                        data[3] =new NameValuePair("paramName","userId");
                        data[4] =new NameValuePair("paramValue",String.valueOf(customerInfo.getCcId()));
                        data[5] =new NameValuePair("paramName","userName");
                        data[6] =new NameValuePair("paramValue",customerName);
                        data[7] =new NameValuePair("paramName","userPhone");
                        data[8] =new NameValuePair("paramValue",customerInfo.getUserPhone());
                        data[9] =new NameValuePair("paramName","isTrafficAccident");
                        data[10] =new NameValuePair("paramValue","1");
                        data[11] =new NameValuePair("paramName","loanMoney");
                        data[12] =new NameValuePair("paramValue",String.valueOf(loanFee));
                        data[13] =new NameValuePair("paramName","loanPurpose");
                        data[14] =new NameValuePair("paramValue","1");
                        data[15] =new NameValuePair("paramName","accidentProvince");
                        data[16] =new NameValuePair("paramValue",String.valueOf(customerInfo.getProvinceId()));
                        data[17] =new NameValuePair("paramName","accidentCity");
                        data[18] =new NameValuePair("paramValue",String.valueOf(customerInfo.getCityId()));
                        data[19] =new NameValuePair("paramName","accidentDistrict");
                        data[20] =new NameValuePair("paramValue",String.valueOf(customerInfo.getDistrictId()));
                        data[21] =new NameValuePair("paramName","accidentAddress");
                        data[22] =new NameValuePair("paramValue",customerInfo.getFamilyAddress());
                        data[23] =new NameValuePair("paramName","accidentTime");
                        data[24] =new NameValuePair("paramValue",sdf.format(new Date()));
                    String res = HttpClientUtils.httpPost(xcxUrl,data);
                        System.out.println(res);
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }else{
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
