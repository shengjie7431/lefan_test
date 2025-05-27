package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCaseCenterInfoFinedApi;
import com.lefancrm.apicenter.backendapi.BackendCaseCenterInfoFollowApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.UserAccountService;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wangwei on 2018/5/17.
 */
@ApiService(descript = "扣罚案件清单列表API")
@Service
public class BackendCaseCenterInfoFinedApiImpl extends BaseServiceImpl implements BackendCaseCenterInfoFinedApi {
    @Autowired
    private CaseCenterInfoFinedMapper caseCenterInfoFinedMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private AgentApplyMapper agentApplyMapper;
    @Autowired
    private LoanApplicationMapper loanApplicationMapper;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    @Autowired
    private CommonAreaMapper commonAreaMapper;
    @Autowired
    private CommonFineEnumMapper commonFineEnumMapper;
    /**
     * 扣罚案件清单列表
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "扣罚案件清单列表" ,value = "backend-case-center-info-fined-list")
    @Override
    public ApiResponse<List<CaseCenterInfoFined>> caseCenterInfoFinedList(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(apiReq.getLong("operatorId"));
        if(userInfo != null){
            if(userInfo.getIsTester() != 1){
                //不是测试人员：默认查询非测试案件
                apiReq.put("isTest",0);
            }else{
                //测试人员:默认查询测试案件
                apiReq.put("isTest",1);
            }
        }
        int count = caseCenterInfoFinedMapper.selectCaseCenterInfoFinedListSize(apiReq);
        List<CaseCenterInfoFined> list = caseCenterInfoFinedMapper.selectCaseCenterInfoFinedList(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    /**
     * 保存扣罚案件
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "保存扣罚案件", value = "backend-case-center-info-fined-save")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse caseCenterInfoFinedSave(ApiRequest apiReq) {

        Integer isFined = apiReq.getInt("isFined");
        int result = 0;
        //判断：0：不支付 1：支付
        if(isFined == 0){
            CaseCenterInfoFined caseCenterInfoFined = addCaseCenterInfoFollow(apiReq);
            result = caseCenterInfoFinedMapper.insertSelective(caseCenterInfoFined);
        }else if(isFined == 1){
            result = reckonCaseMoney(apiReq);
        }

        if(result > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    /**
     * 扣罚案件的数据
     *
     */
    private CaseCenterInfoFined addCaseCenterInfoFollow(ApiRequest apiReq) {
        Long caseId = apiReq.getLong("caseId");
        Long operatorId = apiReq.getLong("operatorId");
        Double finedMoney = apiReq.getDouble("finedMoney");
        Integer isFined = apiReq.getInt("isFined");
        Integer finedType = apiReq.getInt("finedType");
        CaseCenterInfoFined caseCenterInfoFined = new CaseCenterInfoFined();
        caseCenterInfoFined.setCaseId(caseId);

        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(operatorId);
        caseCenterInfoFined.setFinedBy(userInfo.getUserName());
        caseCenterInfoFined.setFinedById(operatorId);
        caseCenterInfoFined.setFinedTime(new Date());
        caseCenterInfoFined.setFinedMoney(finedMoney);
        caseCenterInfoFined.setIsFined(isFined);
        caseCenterInfoFined.setFinedType(finedType);
        CommonFineEnum commonFineEnum = commonFineEnumMapper.selectByPrimaryKey(finedType.longValue());
        caseCenterInfoFined.setFinedTypeName(commonFineEnum.getFineEnumName());

        Integer caseType = apiReq.getInt("caseType");
        //贷款案件
        if(caseType == 1){
            LoanApplication loanApplication = loanApplicationMapper.selectByPrimaryKey(caseId);
            caseCenterInfoFined.setCaseNo(loanApplication.getLoanNo());
            caseCenterInfoFined.setCaseType(caseType);
            caseCenterInfoFined.setCaseTitle(loanApplication.getUserName()+loanApplication.getAccidentAddress());
            caseCenterInfoFined.setCaseName(loanApplication.getUserName());

            //修改案件为“未支付”案件
            loanApplication.setIsFined(0);
            loanApplicationMapper.updateByPrimaryKeySelective(loanApplication);
        }
        //代理案件
        if(caseType == 2){
            AgentApply agentApply = agentApplyMapper.selectByPrimaryKey(caseId);
            caseCenterInfoFined.setCaseNo(agentApply.getAgentNo());
            caseCenterInfoFined.setCaseType(caseType);
            caseCenterInfoFined.setCaseTitle(agentApply.getUserName()+agentApply.getAccidentAddress());
            caseCenterInfoFined.setCaseName(agentApply.getUserName());

            //修改案件为“未支付”案件
            agentApply.setIsFined(0);
            agentApplyMapper.updateByPrimaryKeySelective(agentApply);
        }

        return caseCenterInfoFined;
    }

    /**
     * 支付的案件，计算佣金
     *
     */
    private int reckonCaseMoney(ApiRequest apiReq) {
        Long caseId = apiReq.getLong("caseId");
        Integer caseType = apiReq.getInt("caseType");
        Long currentUserId = apiReq.getLong("currentUserId");
        //贷款案件
        if(caseType == 1){
            LoanApplication loanApplication = loanApplicationMapper.selectByPrimaryKey(caseId);

            String loanTypeStr="医疗费垫付";
            if(loanApplication.getLoanPurpose()==1){
                loanTypeStr="医疗费垫付";
            }else if(loanApplication.getLoanPurpose()==2){
                loanTypeStr="赔偿款垫付";
            }
            //验证提供案源客户是否是第一次
//            Map<String, Object> paramMap = new HashMap<>();
//            paramMap.put("caseName", loanApplication.getUserName());
//            paramMap.put("caseTel", loanApplication.getUserPhone());
//            int count = caseCenterInfoMapper.selectUserNameCaseCount(paramMap);
//            CommonArea commonArea =commonAreaMapper.selectByPrimaryKey(Long.parseLong(loanApplication.getAccidentCity()));
//            //计算贷款申请的案件佣金
//            if(count <= 0){
//                userAccountService.caseMoney(loanApplication.getUserId(),loanApplication.getLoanNo(),commonArea.getAreaName()+loanApplication.getUserName()+loanTypeStr);
//            }
            CommonArea commonArea =commonAreaMapper.selectByPrimaryKey(Long.parseLong(loanApplication.getAccidentCity()));
            userAccountService.caseMoney(loanApplication.getUserId(),loanApplication.getLoanNo(),commonArea.getAreaName()+loanApplication.getUserName()+loanTypeStr,currentUserId);

            //修改支付状态为：已支付
            loanApplication.setIsFined(1);
            loanApplicationMapper.updateByPrimaryKeySelective(loanApplication);
        }
        //代理案件
        if(caseType == 2){
            AgentApply agentApply = agentApplyMapper.selectByPrimaryKey(caseId);

            String typeName = "";
            switch (agentApply.getAgentType()){
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
            //验证提供案源客户是否是第一次
            userAccountService.caseMoney(agentApply.getUserId(),agentApply.getAgentNo(),agentApply.getAccidentCity() + agentApply.getUserName() + typeName,currentUserId);

            //修改支付状态为：已支付
            agentApply.setIsFined(1);
            agentApplyMapper.updateByPrimaryKeySelective(agentApply);
        }
        return 1;
    }

    /**
     * 根据“caseNo”查询扣罚记录详情
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据“caseNo”查询扣罚记录详情" ,value = "backend-case-center-info-fined-by-caseNo")
    @Override
    public ApiResponse<CaseCenterInfoFined> selectCaseCenterInfoFinedByCaseNo(ApiRequest apiReq){
        CaseCenterInfoFined caseCenterInfoFined = caseCenterInfoFinedMapper.selectCaseCenterInfoFinedByCaseNo(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,1,caseCenterInfoFined);
    }
}
