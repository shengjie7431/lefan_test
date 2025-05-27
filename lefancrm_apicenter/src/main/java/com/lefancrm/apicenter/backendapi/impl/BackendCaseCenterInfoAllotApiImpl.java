package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCaseCenterInfoAllotApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.CaseCenterInfoAllotDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by wangwei on 2018/5/16.
 */
@ApiService(descript = "案件分配列表API")
@Service
public class BackendCaseCenterInfoAllotApiImpl extends BaseServiceImpl implements BackendCaseCenterInfoAllotApi {
    @Autowired
    private CaseCenterInfoAllotMapper caseCenterInfoAllotMapper;
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserPatchOrgMapper userPatchOrgMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private OrgInfoMapper orgInfoMapper;
    /**
     * 案件跟踪列表
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "案件分配列表" ,value = "backend-case-center-info-allot-list")
    @Override
    public ApiResponse<List<CaseCenterInfoAllotDto>> caseCenterInfoAllotList(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        Map<String,Object> map = new HashMap<>();
        map.put("userId",apiReq.getLong("operatorId"));
        UserInfo ui = userInfoMapper.selectAgency(map);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(apiReq.getLong("operatorId"));
        if(ui != null){
            //查询该客户是否是调度员，并是否已分配机构
            List<UserPatchOrg> userPatchOrgList = userPatchOrgMapper.selectInfoByUserId(map);
            List<CaseCenterInfoAllotDto> list = new ArrayList<>();
            int count = 0;
            if(userPatchOrgList != null && userPatchOrgList.size() > 0){
                String [] orgIds = new String[userPatchOrgList.size()];
                for (int i = 0; i < userPatchOrgList.size(); i++) {
                    orgIds[i] = userPatchOrgList.get(i).getOrgId().toString();
                }
                apiReq.put("orgIds",orgIds);
                if(userInfo!=null){
                    if(userInfo.getIsTester() != 1){
                        //不是测试人员：默认查询非测试案件
                        apiReq.put("isTest",0);
                    }else{
                        //测试人员:默认查询测试案件
                        apiReq.put("isTest",1);
                    }
                }
                count = caseCenterInfoAllotMapper.selectCountAgencyCaseCenterInfoByPatch(apiReq);
                list = caseCenterInfoAllotMapper.selectAgencyCaseCenterInfoByPatch(apiReq);
                return new ApiResponse<List<CaseCenterInfoAllotDto>>(ApiMsgEnum.SUCCESS, count, list);
            }else if(ui.getOrgId()!=null){
                String [] orgIds = new String[1];
                orgIds[0] = ui.getOrgId().toString();
                apiReq.put("orgIds",orgIds);
                if(userInfo!=null){
                    if(userInfo.getIsTester() != 1){
                        //不是测试人员：默认查询非测试案件
                        apiReq.put("isTest",0);
                    }else{
                        //测试人员:默认查询测试案件
                        apiReq.put("isTest",1);
                    }
                }
                count = caseCenterInfoAllotMapper.selectCountAgencyCaseCenterInfoByPatch(apiReq);
                list = caseCenterInfoAllotMapper.selectAgencyCaseCenterInfoByPatch(apiReq);
                return new ApiResponse<List<CaseCenterInfoAllotDto>>(ApiMsgEnum.SUCCESS, count, list);
            }
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    /**
     * 案件分配-查看案件详情
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "案件分配-查看案件详情" ,value = "backend-case-center-info-for-allot-by-id")
    @Override
    public ApiResponse<CaseCenterInfoAllotDto> searchCaseCenterInfoForAllotById(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        CaseCenterInfoAllotDto caseCenterInfoAllot = caseCenterInfoAllotMapper.searchCaseCenterInfoForAllotById(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,null,caseCenterInfoAllot);
    }

    /**
     * 案件分配-选定经办人员
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "案件分配-选定经办人员" ,value = "backend-user-info-list-for-allot")
    @Override
    public ApiResponse<List<UserInfo>> selectUserInfoForAllot(ApiRequest apiReq) {
        //添加字段bsRoleId  2 业务员 3评估员 7索赔员 29诉讼员
        Map<String,Object> map = new HashMap<>();
        map.put("userId",apiReq.getLong("currentUserId"));
        UserInfo userInfo = userInfoMapper.selectUserInfoByUserId(map);
        Long orgId = getUserOrgId(userInfo.getOrgId());

        if(orgId == 0){
            orgId = userInfo.getOrgId();
        }
        String [] s = {orgId.toString()};
        map.clear();
        map.put("orgIds",s);

        String gradationState = apiReq.getString("gradationState");
        //gradationState : 1、洽谈阶段（业务员）；2、评估阶段（评估员）3、索赔阶段（索赔员）6、诉讼阶段（诉讼员）
        if("1".equals(gradationState)){
            map.put("bsRoleId",2);
        }else if("2".equals(gradationState)){
            map.put("bsRoleId",3);
        }else if("3".equals(gradationState)){
            map.put("bsRoleId",7);
        }else if("6".equals(gradationState)){
            map.put("bsRoleId",29);
        }
        List<UserInfo> list = userInfoMapper.selectUserInfoByOrgIdAndRoleId(map);

        //获取当前登录人的角色
        List<BusUserRole> busUserRoleList = busUserRoleMapper.orgUserRoleList(apiReq.getLong("currentUserId"));

        for (BusUserRole busUserRole : busUserRoleList){
            //如是“调度员”角色
            if(busUserRole.getRoleId() == 27){
                list.removeAll(list);
                Map<String,Object> map2 = new HashMap<>();
                map2.put("userId",apiReq.getLong("currentUserId"));
                //获取调度员名下所有机构
                List<UserPatchOrg> userPatchOrgList = userPatchOrgMapper.selectInfoByUserId(map2);
                if(userPatchOrgList != null && userPatchOrgList.size() > 0){
                    String [] orgIds = new String[userPatchOrgList.size()];
                    for (int i = 0; i < userPatchOrgList.size(); i++) {
                        orgIds[i] = userPatchOrgList.get(i).getOrgId().toString();
                    }
                    map.put("orgIds",orgIds);
                    //查询机构下的 各个角色人员
                    List<UserInfo> listAll = userInfoMapper.selectUserInfoByOrgIdAndRoleId(map);
                    list.addAll(listAll);
                }
            }
        }
        return new ApiResponse<List<UserInfo>>(ApiMsgEnum.SUCCESS, (list == null ? 0 : list.size()), list);
    }

    private Long getUserOrgId(Long orgId){
        OrgInfo orgInfo = orgInfoMapper.selectByPrimaryKey(orgId);
        if(orgInfo == null){
            return 0L;
        }
        //如果是机构则直接返回
        if(orgInfo.getOrgType() == 1){
            return orgInfo.getId();
        }else if(orgInfo.getOrgType() == 2){
            return getUserOrgId(orgInfo.getOrgParentid());
        }else{
            return 0L;
        }
    }

    /**
     * 保存被分配的案件
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "保存被分配的案件" ,value = "backend-case-center-info-allot-save")
    @Override
    public ApiResponse caseCenterInfoAllotSave(ApiRequest apiReq) {
        Long caseId = apiReq.getLong("caseId");
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseId);

        Long bsRoleId = apiReq.getLong("bsRoleId");
        Long userId = apiReq.getLong("userId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        String userName = userInfo.getUserName();

        caseCenterInfo.setOrgUserId(userId);
        caseCenterInfo.setOrgUserName(userName);
        if(2==bsRoleId){
            caseCenterInfo.setOperatorId(userId);
            caseCenterInfo.setOperatorName(userName);
        }else if (3==bsRoleId){
            caseCenterInfo.setAssessId(userId);
            caseCenterInfo.setAssessName(userName);
        }else if (7==bsRoleId){
            caseCenterInfo.setClaimantId(userId);
            caseCenterInfo.setClaimantName(userName);
        }else if (29==bsRoleId){
            caseCenterInfo.setLegalUserId(userId);
            caseCenterInfo.setLegalUserName(userName);
        }
        String type = apiReq.getString("type");
        if (!"2".equals(type)){
            caseCenterInfo.setListState(-1);
            caseCenterInfo.setListStateName("待接收");
            caseCenterInfo.setCaseState(1);
            caseCenterInfo.setCaseStateStr("待接收");
        }
        caseCenterInfo.setDistributionTime(new Date());
        int result = caseCenterInfoMapper.updateByPrimaryKey(caseCenterInfo);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    /**
     * 修改业务员-选定业务员list
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "修改业务员-选定业务员list" ,value = "backend-user-list-by-orgId")
    @Override
    public ApiResponse<List<UserInfo>> selectUserInfoByOrgId(ApiRequest apiReq) {
        //添加字段bsRoleId  2 业务员
        Map<String,Object> map = new HashMap<>();
        //根据案件所在的机构
        Long orgId = apiReq.getLong("orgId");
        if(orgId != null){
            String [] s = {orgId.toString()};
            map.put("orgIds",s);
        }else{
            //如案件所在的机构为null,则默认当前登录人所在的机构
            map.put("userId",apiReq.getLong("currentUserId"));
            UserInfo userInfo = userInfoMapper.selectUserInfoByUserId(map);
            orgId = getUserOrgId(userInfo.getOrgId());

            if(orgId == 0){
                orgId = userInfo.getOrgId();
            }
            String [] s = {orgId.toString()};
            map.clear();
            map.put("orgIds",s);
        }

        map.put("bsRoleId",2);
        List<UserInfo> list = userInfoMapper.selectUserInfoByOrgIdAndRoleId(map);

        return new ApiResponse<List<UserInfo>>(ApiMsgEnum.SUCCESS, (list == null ? 0 : list.size()), list);
    }

    /**
     * 修改业务员
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "修改业务员" ,value = "backend-update-operator")
    @Override
    public ApiResponse updateOperator(ApiRequest apiReq) {
        Long caseId = apiReq.getLong("caseId");
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseId);

        Long userId = apiReq.getLong("userId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        String operatorName = userInfo.getUserName();

        caseCenterInfo.setOperatorName(operatorName);//业务员姓名
        caseCenterInfo.setOperatorId(userId);//业务员id
        int result = caseCenterInfoMapper.updateByPrimaryKey(caseCenterInfo);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }
}
