package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCasePayInfoApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.CaseApplicationInfoDto;
import com.lefancrm.apicenter.dto.CasePayInfoDto;
import com.lefancrm.apicenter.model.BusUserRole;
import com.lefancrm.apicenter.model.BusinessRole;
import com.lefancrm.apicenter.model.CasePayInfo;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * Created by wangwei on 2018/6/19.
 */
@ApiService(descript = "待支付项目列表API")
@Service
public class BackendCasePayInfoApiImpl extends BaseServiceImpl implements BackendCasePayInfoApi {

    @Autowired
    private CasePayInfoMapper casePayInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    /**
     * 待支付项目列表
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "待支付项目列表" ,value = "backend-case-pay-info-list")
    @Override
    public ApiResponse<List<CasePayInfoDto>> casePayInfoList(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(apiReq.getLong("operatorId"));
        Boolean isCw = isRoleUser(userRoles, 23L); //财务部
        Boolean isCwsh = isRoleUser(userRoles, 39L); //财务审核
        //“财务审核”角色查询：audit_state = 2（审核中的案件）
        if(isCw){
            apiReq.put("role",1);//财务部
        }
        if(isCwsh){
            apiReq.put("role",2);//财务审核
        }
        if(isCw && isCwsh){
            apiReq.put("role",3);//财务部 和 财务审核
        }

        //根据当前登录人是否是测试人员
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

        int count = casePayInfoMapper.selectCountCasePayInfo(apiReq);
        List<CasePayInfoDto> list = casePayInfoMapper.selectCasePayInfoList(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    /**
     * 根据‘id’查询案件信息
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据‘id’查询待支付项目" ,value = "backend-case-pay-info-by-id")
    @Override
    public ApiResponse<CasePayInfo> searchCasePayInfoById(ApiRequest apiReq){
        CasePayInfo casePayInfo = casePayInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
        return  new ApiResponse(ApiMsgEnum.SUCCESS,1,casePayInfo);
    }


    /**
     * 修改状态
     * @param
     * @return
     */
    @ApiMethod(descript = "修改状态",value = "backend-case-pay-info-upd")
    @Override
    public ApiResponse casePayInfoUpd(ApiRequest request) {
        Long id = request.getLong("id");
        CasePayInfo casePayInfo = casePayInfoMapper.selectByPrimaryKey(id);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(request.getLong("operatorId"));
        String btnCode = request.getString("btnCode");//操作按钮code
        //确认支付
        if("1200".equals(btnCode)){
            if (casePayInfo != null){
                String img = request.getString("img");
                casePayInfo.setImg(img);
                casePayInfo.setPayState(2);
                casePayInfo.setPayTime(new Date());
                if(userInfo !=null){
                    casePayInfo.setOperatorName(userInfo.getUserName());
                }
            }
        }
        //提交审核
        if("1100".equals(btnCode)){
            casePayInfo.setAuditState(2);//审核状态：1、未提交审核，2、审核中，3、审核成功，4、审核失败
        }
        //通过审核
        if("1101".equals(btnCode)){
            casePayInfo.setAuditState(3);
        }
        //驳回审核
        if("1102".equals(btnCode)){
            String auditReason = request.getString("auditReason");
            casePayInfo.setAuditReason(auditReason);
            casePayInfo.setAuditState(4);
        }
        int ret = casePayInfoMapper.updateByPrimaryKey(casePayInfo);
        if (ret <0){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId() == roleId){
                return true;
            }
        }
        return false;
    }
}
