package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCaseApplicationInfoApi;
import com.lefancrm.apicenter.backendapi.BackendCaseApplyApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.CaseApplicationInfoDto;
import com.lefancrm.apicenter.dto.CaseApplyDto;
import com.lefancrm.apicenter.dto.CaseCenterInfoDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.SerialNumberUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wangwei on 2018/5/14.
 */
@ApiService(descript = "报案中心列表API")
@Service
public class BackendCaseAppyApiImpl extends BaseServiceImpl implements BackendCaseApplyApi {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private CaseApplyMapper caseApplyMapper;
    @Autowired
    private LoanApplicationMapper loanApplicationMapper;
    @Autowired
    private AgentApplyMapper agentApplyMapper;
    @Autowired
    private InvalidismEstimateMapper invalidismEstimateMapper;
    @Autowired
    private PaymentEstimateApplyMapper paymentEstimateApplyMapper;
    @Autowired
    private BackendCaseInfoApiImpl backendCaseInfoApi;
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    /**
     * 报案中心列表
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "报案中心列表" ,value = "backend-case-apply-list")
    @Override
    public ApiResponse<List<CaseApplyDto>> caseApplyList(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        int count = caseApplyMapper.selectCaseApplyListSize(apiReq);
        List<CaseApplyDto> list = caseApplyMapper.selectCaseApplyList(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    /**
     * 根据‘id’查询报案中心数据
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据‘id’查询报案中心数据" ,value = "backend-case-apply-info-by-id")
    @Override
    public ApiResponse<CaseApply> searchCaseApplyById(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        CaseApply caseApplyInfo = caseApplyMapper.selectByPrimaryKey(apiReq.getLong("id"));
        return  new ApiResponse(ApiMsgEnum.SUCCESS,1,caseApplyInfo);
    }

    @ApiMethod(descript = "添加报案申请", value = "backend-case-apply-save")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse caseApplySave(ApiRequest apiReq) {
        //判断不能为空的参数
        if (apiReq.getString("phone")==null){
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        if (apiReq.getString("userName")==null){
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        if(apiReq.getString("caseSources")==null){
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        if(apiReq.getString("caseProvince") ==null){
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        if(apiReq.getString("caseCity") == null){
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        if(apiReq.getString("caseDistrict") == null){
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        String caseSources = apiReq.getString("caseSources");
        String phone = apiReq.getString("phone");
        String userName = apiReq.getString("userName");

        String caseProvince = apiReq.getString("caseProvince");
        String caseCity = apiReq.getString("caseCity");
        String caseDistrict = apiReq.getString("caseDistrict");
        String caseAddress = null;
        if(apiReq.get("caseAddress") !=null){
            caseAddress = apiReq.get("caseAddress").toString();
        }
        //获取省市区的id
        Long provinceId = apiReq.getLong("provinceId");
        Long cityId =apiReq.getLong("cityId");
        Long districtId =apiReq.getLong("districtId");
        //开始封装数据
        CaseApply ca = new CaseApply();
        ca.setUserName(userName);
        ca.setPhone(phone);
        ca.setState(0);
        ca.setCaseProvince(caseProvince);
        ca.setCaseProvinceId(provinceId);
        ca.setCaseCity(caseCity);
        ca.setCaseCityId(cityId);
        ca.setCaseDistrict(caseDistrict);
        ca.setCaseDistrictId(districtId);
        ca.setCaseAddress(caseAddress);
        ca.setCreateTime(new Date());
        ca.setCaseSources(caseSources);
        //添加数据
        int result = caseApplyMapper.insertSelective(ca);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse<CaseApply>(ApiMsgEnum.FAIL);
    }

    /**
     * 报案申请提交转办
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "报案申请提交转办", value = "backend-caseApply-forwardSubmit")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse caseApplyForwardSubmit(ApiRequest apiReq){
        Long id = apiReq.getLong("id");
        Integer caseType = apiReq.getInt("caseType");
        String userName = apiReq.getString("userName");
        String userPhone = apiReq.getString("phone");
        String accidentAddress = apiReq.getString("caseAddress");
        String dangerTime = apiReq.getString("dangerTime");
        Long userId = apiReq.getLong("userId");
        Integer type = apiReq.getInt("type");
        String carNumber = apiReq.getString("carNumber");

        //根据手机号判断：如果已经有该手机号码提交的案件，不能转案件中心
        Map<String,Object> phoneMap = new HashMap<String, Object>();
        phoneMap.put("caseTel",userPhone);
        List<CaseCenterInfoDto> caseCenterInfoDto = caseCenterInfoMapper.findListByInfo(phoneMap);
        if(caseCenterInfoDto.size() > 0){
            if(type!=null){
                if(type.equals(1)){
                    //修改伤残预估turnStatus状态
                    InvalidismEstimate invalidismEstimate = invalidismEstimateMapper.selectByPrimaryKey(id);
                    invalidismEstimate.setTurnStatus(1l);
                    int result = invalidismEstimateMapper.updateByPrimaryKey(invalidismEstimate);
                    if(result>0){
                        return new ApiResponse(ApiMsgEnum.FORWARD_SUBMIT);
                    }
                }else{
                    PaymentEstimateApply paymentEstimateApply = paymentEstimateApplyMapper.selectByPrimaryKey(id);
                    paymentEstimateApply.setTurnStatus(1l);
                    int result = paymentEstimateApplyMapper.updateByPrimaryKey(paymentEstimateApply);
                    if(result>0){
                        return new ApiResponse(ApiMsgEnum.FORWARD_SUBMIT);
                    }
                }
            }
            return new ApiResponse(ApiMsgEnum.FORWARD_SUBMIT);
        }

        Date accidentTime = null;
        if(dangerTime == null){
            accidentTime = new Date();
            dangerTime = sdf.format(new Date());
        }else{
            try {
                accidentTime = sdf.parse(dangerTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        CaseCenterInfo caseCenterInfo = new CaseCenterInfo();
        String loanNo = null;
        if(caseType == 1 || caseType ==2){
            // 1.添加到医疗费垫付 2.添加赔偿款垫付
            String accidentProvince = apiReq.getString("caseProvinceId");
            String accidentCity = apiReq.getString("caseCityId");
            String accidentDistrict = apiReq.getString("caseDistrictId");
            Integer isTrafficAccident = apiReq.getInt("isTrafficAccident");
            Double loanMoney = apiReq.getDouble("loanMoney");
            Long loanApplicationUserId = apiReq.getLong("loanApplicationUserId");
            loanNo = SerialNumberUtil.nextAgentCode("DF");
            //先进行查询一次，是否提交过相同的信息
            Map<String, Object> pramaMap = new HashMap<>();
            pramaMap.put("userName",userName);
            pramaMap.put("userPhone",userPhone);
            pramaMap.put("accidentProvince",accidentProvince);
            pramaMap.put("accidentCity",accidentCity);
            pramaMap.put("accidentDistrict",accidentDistrict);
            pramaMap.put("accidentTime",dangerTime);
            LoanApplication loanApplication = loanApplicationMapper.selectLoanApplicationByParamTo(pramaMap);
            if(loanApplication  != null){
                return new ApiResponse(ApiMsgEnum.RepeatSubmit,0,loanApplication);
            }
            loanApplication=new LoanApplication();
            loanApplication.setLoanNo(loanNo);
            loanApplication.setUserId(userId);
            loanApplication.setUserName(userName);
            loanApplication.setUserPhone(userPhone);
            loanApplication.setIsTrafficAccident(isTrafficAccident);
            loanApplication.setLoanMoney(loanMoney);
            loanApplication.setLoanPurpose(caseType);
            loanApplication.setAccidentProvince(accidentProvince);
            loanApplication.setAccidentCity(accidentCity);
            loanApplication.setAccidentDistrict(accidentDistrict);
            loanApplication.setAccidentAddress(accidentAddress);
            loanApplication.setDeleteFlag(0);
            loanApplication.setCreateTime(new Date());
            loanApplication.setState(2);//状态(1:报案，2：受理.3:驳回，4：申请中，5,：完成)
            loanApplication.setAccidentTime(accidentTime);
            loanApplication.setUserId(loanApplicationUserId);
            this.loanApplicationMapper.insert(loanApplication);
            //案件添加申请后，开始转案件中心
            apiReq.put("caseId",loanApplication.getId());
            apiReq.put("type",1);
            apiReq.put("caseTitle",(apiReq.getString("caseCity")==null?"":apiReq.getString("caseCity"))+userName+(caseType==1?"医疗费垫付":"赔偿款垫付"));
            apiReq.put("caseNo",loanNo);
            apiReq.put("caseName",userName);
            apiReq.put("caseTel",userPhone);
            apiReq.put("dangerTime",dangerTime);
            apiReq.put("carNumber",carNumber);
            caseCenterInfo = backendCaseInfoApi.addCaseCenterInfo(apiReq);
        }else{
            //添加到代理
            String accidentProvince = apiReq.getString("caseProvince");
            String accidentCity = apiReq.getString("caseCity");
            String accidentDistrict = apiReq.getString("caseDistrict");
            Integer accidentProvinceId = apiReq.getInt("caseProvinceId");
            Integer accidentCityId = apiReq.getInt("caseCityId");
            Integer accidentDistrictId = apiReq.getInt("caseDistrictId");
            String claimIndemnityDesc = apiReq.getString("claimIndemnityDesc");
            Integer agentType = apiReq.getInt("agentType");
            loanNo = SerialNumberUtil.nextAgentCode("DL");
            //先进行查询一次，是否提交过相同的信息
            Map<String, Object> pramaMap = new HashMap<>();
            pramaMap.put("userName",userName);
            pramaMap.put("userPhone",userPhone);
            pramaMap.put("accidentProvinceId",accidentProvinceId);
            pramaMap.put("accidentCityId",accidentCityId);
            pramaMap.put("accidentDistrictId",accidentDistrictId);
            pramaMap.put("accidentTime",dangerTime);
            AgentApply agentApplyInfo = agentApplyMapper.selectAgentApplyByParam(pramaMap);
            if(agentApplyInfo  != null){
                return new ApiResponse(ApiMsgEnum.RepeatSubmit,0,agentApplyInfo);
            }
            agentApplyInfo = new AgentApply();
            agentApplyInfo.setUserId(userId);
            agentApplyInfo.setUserName(userName);
            agentApplyInfo.setUserPhone(userPhone);
            agentApplyInfo.setAccidentProvince(accidentProvince);
            agentApplyInfo.setAccidentCity(accidentCity);
            agentApplyInfo.setAccidentDistrict(accidentDistrict);
            agentApplyInfo.setAccidentAddress(accidentAddress);
            agentApplyInfo.setAgentType(agentType);
            agentApplyInfo.setClaimIndemnityDesc(claimIndemnityDesc);
            agentApplyInfo.setState(2);
            agentApplyInfo.setCreateTime(new Date());
            agentApplyInfo.setCreateBy(apiReq.getCurrentUserDisplayName());
            agentApplyInfo.setDeleteFlag(0);
            agentApplyInfo.setAgentNo(loanNo);
            agentApplyInfo.setAccidentProvinceId(accidentProvinceId);
            agentApplyInfo.setAccidentDistrictId(accidentDistrictId);
            agentApplyInfo.setAccidentCityId(accidentCityId);
            agentApplyInfo.setAccidentTime(accidentTime);
            agentApplyMapper.insertSelective(agentApplyInfo);
            String typeName = "";
            switch (agentType){
                case 1:
                    typeName = "交通事故索赔";
                    break;
                case 2:
                    typeName = "工伤事故索赔";
                    break;
                case 3:
                    typeName = "寿险索赔";
                    break;
                case 4:
                    typeName = "车辆损失索赔";
                    break;
                case 5:
                    typeName = "保险拒赔";
                    break;
                case 6:
                    typeName = "意外保险";
                    break;
                case 7:
                    typeName = "其他侵权";
                    break;
                case 8:
                    typeName = "援助服务";
                    break;
            }
            //案件添加申请后，开始转案件中心
            apiReq.put("caseId",agentApplyInfo.getId());
            apiReq.put("type",2);
            apiReq.put("caseTitle",(accidentCity==null?"":accidentCity)+userName+typeName);
            apiReq.put("caseNo",loanNo);
            apiReq.put("caseName",userName);
            apiReq.put("caseTel",userPhone);
            apiReq.put("dangerTime",dangerTime);
            apiReq.put("carNumber",carNumber);
            caseCenterInfo = backendCaseInfoApi.addCaseCenterInfo(apiReq);
        }
        if(type!=null){
            if(type.equals(1)){
                //修改伤残预估turnStatus状态
                InvalidismEstimate invalidismEstimate = invalidismEstimateMapper.selectByPrimaryKey(id);
                invalidismEstimate.setTurnStatus(1l);
                int result = invalidismEstimateMapper.updateByPrimaryKey(invalidismEstimate);
                if(result>0){
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
            }else{
                PaymentEstimateApply paymentEstimateApply = paymentEstimateApplyMapper.selectByPrimaryKey(id);
                paymentEstimateApply.setTurnStatus(1l);
                paymentEstimateApply.setCaseId(caseCenterInfo.getId());
                int result = paymentEstimateApplyMapper.updateByPrimaryKey(paymentEstimateApply);
                if(result>0){
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
            }
        }else{
            HashMap<String,Object> map = new HashMap<>();
            map.put("caseNo",loanNo);
            map.put("id",id);
            int result = caseApplyMapper.updateCaseNoAndStateById(map);
            if(result>0){
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(descript = "修改报案状态为已处理", value = "backend-caseApply-to-already-state")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse caseApplyToAlready(ApiRequest apiReq) {
        Integer state = apiReq.getInt("state");
        Long id = apiReq.getLong("id");
        CaseApply caseApply = new CaseApply();
        caseApply.setId(id);
        caseApply.setState(state);
        int result = caseApplyMapper.updateByPrimaryKeySelective(caseApply);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
