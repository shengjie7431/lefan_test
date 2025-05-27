package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendHbServiceApi;
import com.lefancrm.apicenter.dao.HbOrgInfoMapper;
import com.lefancrm.apicenter.dao.HbRecordMapper;
import com.lefancrm.apicenter.dao.HbUserInfoMapper;
import com.lefancrm.apicenter.model.*;
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

@Service
@ApiService(descript = "核保管理后台")
public class BackendHbServiceApiImpl extends BaseServiceImpl implements BackendHbServiceApi {

    @Autowired
    private HbOrgInfoMapper hbOrgInfoMapper;

    @Autowired
    private HbRecordMapper hbRecordMapper;

    @Autowired
    private HbUserInfoMapper hbUserInfoMapper;

    @ApiMethod(descript = "核保机构列表" ,value = "backend-hb-org-list")
    @Override
    public ApiResponse hbOrgList(ApiRequest apiRequest) {
        this.setBackendPageSize(apiRequest);
        try{
            //获取数据总值，包含条件查询
            int count = hbOrgInfoMapper.selectListSize(apiRequest);
            //列表查询，包含条件查询
            List<HbOrgInfo> list = hbOrgInfoMapper.selectList(apiRequest);
            return new ApiResponse<List<HbOrgInfo>>(ApiMsgEnum.SUCCESS, count, list);
        }catch(Exception e){
            e.printStackTrace();
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    @ApiMethod(descript = "新增核保修改" ,value = "save-hb-org")
    @Override
    public ApiResponse saveHbOrg(ApiRequest apiRequest) {
         String createBy= apiRequest.getCurrentUserDisplayName();
         String hbOrgName=null;
         if(apiRequest.get("hbOrgName")!=null){
             hbOrgName=apiRequest.getString("hbOrgName");
         }else{
             return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
         }
         Integer hbOrgStatus=null;
        if(apiRequest.get("hbOrgStatus")!=null){
            hbOrgStatus=apiRequest.getInt("hbOrgStatus");
        }else{
            hbOrgStatus=1;
        }
        try{
            HbOrgInfo hbOrgInfo=new HbOrgInfo();
            hbOrgInfo.setHbOrgName(hbOrgName);
            hbOrgInfo.setHbOrgStatus(hbOrgStatus);
            hbOrgInfo.setCreateTime(new Date());
            hbOrgInfo.setCreateBy(createBy);
            int result =  this.hbOrgInfoMapper.insertSelective(hbOrgInfo);
            if(result>0){
                return new ApiResponse<List<HbOrgInfo>>(ApiMsgEnum.SUCCESS);
            }else{
                return new ApiResponse<List<HbOrgInfo>>(ApiMsgEnum.FAIL);
            }

        }catch(Exception e){
            e.printStackTrace();
            return new ApiResponse(ApiMsgEnum.FAIL);
        }


    }



    @ApiMethod(descript = "核保机构修改" ,value = "edit-hb-org")
    @Override
    public ApiResponse editHbOrg(ApiRequest apiRequest) {
        Long id =null;

        if(apiRequest.get("id")!=null){
            id=apiRequest.getLong("id");
        }else{
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        String createBy= apiRequest.getCurrentUserDisplayName();
        String hbOrgName=null;
        if(apiRequest.get("hbOrgName")!=null){
            hbOrgName=apiRequest.getString("hbOrgName");
        }else{
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        Integer hbOrgStatus=null;
        if(apiRequest.get("hbOrgStatus")!=null){
            hbOrgStatus=apiRequest.getInt("hbOrgStatus");
        }else{
            hbOrgStatus=1;
        }
        try{
            HbOrgInfo hbOrgInfo=new HbOrgInfo();
            hbOrgInfo.setId(id);
            hbOrgInfo.setHbOrgName(hbOrgName);
            hbOrgInfo.setHbOrgStatus(hbOrgStatus);
            int result =  this.hbOrgInfoMapper.updateByPrimaryKeySelective(hbOrgInfo);
            if(result>0){
                return new ApiResponse<List<HbOrgInfo>>(ApiMsgEnum.SUCCESS);
            }else{
                return new ApiResponse<List<HbOrgInfo>>(ApiMsgEnum.FAIL);
            }

        }catch(Exception e){
            e.printStackTrace();
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    @ApiMethod(descript = "核保机构删除" ,value = "delete-hb-org")
    @Override
    public ApiResponse deleteHbOrg(ApiRequest apiRequest) {
        Long id =null;

        if(apiRequest.get("id")!=null){
            id=apiRequest.getLong("id");
        }else{
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        try{
            int result =  this.hbOrgInfoMapper.deleteByPrimaryKey(id);
            if(result>0){
                return new ApiResponse<List<HbOrgInfo>>(ApiMsgEnum.SUCCESS);
            }else{
                return new ApiResponse<List<HbOrgInfo>>(ApiMsgEnum.FAIL);
            }

        }catch(Exception e){
            e.printStackTrace();
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }
    @ApiMethod(descript = "核保机构详情查询" ,value = "query-hb-org-byid")
    @Override
    public ApiResponse queryHbOrgById(ApiRequest apiRequest) {
        Long id =null;
        if(apiRequest.get("id")!=null){
            id=apiRequest.getLong("id");
        }else{
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        try{
            HbOrgInfo hbOrgInfo =  this.hbOrgInfoMapper.selectByPrimaryKey(id);
            return new ApiResponse<HbOrgInfo>(ApiMsgEnum.SUCCESS,1,hbOrgInfo);
        }catch(Exception e){
            e.printStackTrace();
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    @ApiMethod(descript = "核保用户列表" ,value = "backend-hb-user-list")
    @Override
    public ApiResponse hbUserList(ApiRequest apiRequest) {
        this.setBackendPageSize(apiRequest);
        try{
            //获取数据总值，包含条件查询
            int count = hbUserInfoMapper.selectListSize(apiRequest);
            //列表查询，包含条件查询
            List<HbUserInfo> list = hbUserInfoMapper.selectList(apiRequest);
            return new ApiResponse<List<HbUserInfo>>(ApiMsgEnum.SUCCESS, count, list);
        }catch(Exception e){
            e.printStackTrace();
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }



    @ApiMethod(descript = "核保用户删除" ,value = "delete-hb-user")
    @Override
    public ApiResponse deleteHbUser(ApiRequest apiRequest) {
        Long userId =null;
        if(apiRequest.get("userId")!=null){
            userId=apiRequest.getLong("userId");
        }else{
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        try{
            int result =  this.hbUserInfoMapper.deleteByPrimaryKey(userId);
            if(result>0){
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }else{
                return new ApiResponse(ApiMsgEnum.FAIL);
            }

        }catch(Exception e){
            e.printStackTrace();
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    @ApiMethod(descript = "核保用户详情查询" ,value = "query-hb-user-byid")
    @Override
    public ApiResponse queryHbuserById(ApiRequest apiRequest) {
        Long id =null;

        if(apiRequest.get("id")!=null){
            id=apiRequest.getLong("id");
        }else{
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        try{
            HbUserInfo hbUserInfo =  this.hbUserInfoMapper.selectByPrimaryKey(id);
            return new ApiResponse<HbUserInfo>(ApiMsgEnum.SUCCESS,1,hbUserInfo);
        }catch(Exception e){
            e.printStackTrace();
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }


    @ApiMethod(descript = "核保记录列表查询" ,value = "backend-hb-record-list")
    @Override
    public ApiResponse hbRecordList(ApiRequest apiRequest) {
        this.setBackendPageSize(apiRequest);
        try{
            //获取数据总值，包含条件查询
            int count = hbRecordMapper.selectListSize(apiRequest);
            //列表查询，包含条件查询
            List<HbRecord> list = hbRecordMapper.selectList(apiRequest);
            return new ApiResponse<List<HbRecord>>(ApiMsgEnum.SUCCESS, count, list);
        }catch(Exception e){
            e.printStackTrace();
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }
}
