package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCaseEntrustInputApi;
import com.lefancrm.apicenter.dao.*;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2019-05-08
 * 新增案件
 */
@Service
@ApiService(descript = "新增案件API")
public class BackendCaseEntrustInputApiImpl extends BaseServiceImpl implements BackendCaseEntrustInputApi {

    @Autowired
    private CaseEntrustInputMapper caseEntrustInputMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private OrgInfoMapper orgInfoMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private PromotedInfoMapper promotedInfoMapper;
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    @Autowired
    private CaseCenterExtendMapper caseCenterExtendMapper;
    @Autowired
    private UserPromotedMapper userPromotedMapper;
    @Autowired
    private CaseCenterInfoExtend2Mapper caseCenterInfoExtend2Mapper;

    /**
     * 案件列表list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "案件列表list", value = "backend-case-entrust-input-list", apiParams = { })
    @Override
    public ApiResponse list(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
        List<CaseEntrustInput> list = caseEntrustInputMapper.list(apiReq);
        int count = caseEntrustInputMapper.listSize(apiReq);
        return new ApiResponse<List<CaseEntrustInput>>(ApiMsgEnum.SUCCESS, count, list);
    }

    /**
     * 数据 info
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "数据 info", value = "backend-case-entrust-input-info", apiParams = { })
    @Override
    public ApiResponse info(ApiRequest apiReq) {
        CaseEntrustInput caseEntrustInput = caseEntrustInputMapper.selectByPrimaryKey(apiReq.getLong("id"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,caseEntrustInput);
    }

    /**
     * 数据处理
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "数据处理", value = "backend-case-entrust-input-operate", apiParams = { })
    @Override
    public ApiResponse operate(ApiRequest apiReq) {
        String btnCode = apiReq.getString("btnCode"); //具体操作标识
        Long userId = apiReq.getLong("operatorId");

        CaseEntrustInput caseEntrustInput = caseEntrustInputMapper.selectByPrimaryKey(apiReq.getLong("id"));
        //审核通过
        if ("1000".equals(btnCode)) {
            caseEntrustInput.setCheckState(1);

            //保存caseCenterInfo
            try {
                CaseCenterInfo caseCenterInfo = CaseCenterInfo.class.newInstance();
                caseCenterInfo.setCaseId(caseEntrustInput.getId());
                //设置机构
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
                if (userInfo.getOrgId() > 0) {
                    caseCenterInfo.setOrgId(userInfo.getOrgId());
                    caseCenterInfo.setOrgName(userInfo.getOrgName());
                } else {
                    //根据事故发生地匹配机构
                    OrgInfo orgInfo = orgInfoMapper.selectOrgInfoByCityId(caseEntrustInput.getAccidentCityId());
                    if (orgInfo != null) {
                        if (orgInfo.getId() == 1) {//如果匹配的是乐凡赔偿总部，则默认将组织改为 上海机构
                            caseCenterInfo.setOrgId(26L);
                            caseCenterInfo.setOrgName("上海机构");
                        } else {
                            caseCenterInfo.setOrgId(orgInfo.getId());
                            caseCenterInfo.setOrgName(orgInfo.getOrgName());
                        }
                    } else {
                        caseCenterInfo.setOrgId(26L);
                        caseCenterInfo.setOrgName("上海机构");
                    }
                }
                UserInfo oprUser = returnBusUser(userId);//业务员
                if (oprUser == null) {
                    caseCenterInfo.setSalesmanId(null);
                    caseCenterInfo.setSalesmanName(null);
                } else {
                    caseCenterInfo.setSalesmanId(oprUser.getUserId());
                    caseCenterInfo.setSalesmanName(oprUser.getUserName());
                    caseCenterInfo.setOrgUserId(oprUser.getUserId());
                    caseCenterInfo.setOrgUserName(oprUser.getUserName());
                    caseCenterInfo.setOperatorId(oprUser.getUserId());
                    caseCenterInfo.setOperatorName(oprUser.getUserName());
                }
                caseCenterInfo.setType(null);//签约之后才清楚产品类型
                caseCenterInfo.setCaseNo(SerialNumberUtil.nextCaseCode("SP"));
                caseCenterInfo.setCaseTitle(caseEntrustInput.getAccidentCity() + caseEntrustInput.getInjuredPerson() + getAgentTypeName(caseEntrustInput.getAgentType().intValue()));
                caseCenterInfo.setCaseName(caseEntrustInput.getInjuredPerson());
                caseCenterInfo.setCaseTel(caseEntrustInput.getInjuredTel());
                caseCenterInfo.setCaseState(2);
                caseCenterInfo.setCaseStateStr("已接收");
                caseCenterInfo.setCreateBy(userInfo.getUserId());
                caseCenterInfo.setCreateTime(new Date());
                caseCenterInfo.setGradationState(1);
                caseCenterInfo.setDangerTime(caseEntrustInput.getAccidentTime());
                caseCenterInfo.setReleaseState(0);
                caseCenterInfo.setIssuanceState(null);
                caseCenterInfo.setClaimState(null);
                caseCenterInfo.setClosedState(null);
                caseCenterInfo.setDistributionTime(new Date());
                caseCenterInfo.setUpdateTime(new Date());
                caseCenterInfo.setNegotiateState(0);
                caseCenterInfo.setListState(null);
                caseCenterInfo.setListStateName(null);
                caseCenterInfo.setIsTestcase(0);
                caseCenterInfo.setDeleteFlag(0);
                caseCenterInfoMapper.insertSelective(caseCenterInfo);

                //保存附属表
                CaseCenterExtend caseCenterExtend = CaseCenterExtend.class.newInstance();
                caseCenterExtend.setId(caseCenterInfo.getId());
                caseCenterExtend.setAssessFlowState(0l);
                caseCenterExtend.setClaimFlowState(0l);
                caseCenterExtend.setCustomerFlowState(0l);
                caseCenterExtend.setLegalFlowState(0l);
                caseCenterExtend.setOperatorFlowState(0l);
                caseCenterExtendMapper.insert(caseCenterExtend);

                //保存附属表二
                CaseCenterInfoExtend2 caseCenterInfoExtend2 = CaseCenterInfoExtend2.class.newInstance();
                caseCenterInfoExtend2.setId(caseCenterInfo.getId());
                caseCenterInfoExtend2.setCaseId(caseEntrustInput.getId());
                caseCenterInfoExtend2.setCaseNo(caseCenterInfo.getCaseNo());
                caseCenterInfoExtend2.setProcessState(1);//审核通过的默认在预约洽谈阶段
                //查看推广人
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("userId", userId);
                UserPromoted userPromoted = userPromotedMapper.selectUserPromotedByUserId(map);
                if (userPromoted != null) {
                    caseCenterInfoExtend2.setUserPromotedName(userPromoted.getRealName());
                    caseCenterInfoExtend2.setUserPromontedPhone(userPromoted.getPhone());
                }
                caseCenterInfoExtend2Mapper.insert(caseCenterInfoExtend2);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //驳回动作
        else if("1100".equals(btnCode)){
            caseEntrustInput.setCheckState(2);
            String reson = apiReq.getString("reson");
            caseEntrustInput.setReson(reson);
        }
        caseEntrustInputMapper.updateByPrimaryKeySelective(caseEntrustInput);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,caseEntrustInput);
    }

    /**
     * 递归调用   获取用户对应的业务员
     * @param userId  用户ID
     * @return  用户对应的业务员  没有返回NULL
     */
    private UserInfo returnBusUser(Long userId){
        List<BusUserRole> busUserRoles = busUserRoleMapper.orgUserRoleList(userId);
        //验证是否有业务员角色  true 表示用户是业务员，直接返回业务员对象
        if (isBusUser(busUserRoles)){
            return userInfoMapper.selectByPrimaryKey(userId);
        }else {
            //获取上级UserId  验证  上级是否是业务员
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("customerId",userId);
            PromotedInfo promotedInfo = promotedInfoMapper.selectPromotedInfoByParam(paramMap);
            //表示没有上级
            if (promotedInfo == null || promotedInfo.getPromoterId() == null){
                return null;
            }
            //表示有上级  就递归迪调用
            return returnBusUser(promotedInfo.getPromoterId());
        }
    }

    /**
     * 验证角色列表是否有业务员角色
     * @param busUserRoles 角色列表
     * @return
     */
    private Boolean isBusUser(List<BusUserRole> busUserRoles){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId() == 2){
                return true;
            }
        }
        return false;
    }

    private String getAgentTypeName(int agentType){
        switch (agentType){
            case 1 : return "交通事故索赔";
            case 2 : return "工伤事故索赔";
            default: return "其他";
        }
    }
}
