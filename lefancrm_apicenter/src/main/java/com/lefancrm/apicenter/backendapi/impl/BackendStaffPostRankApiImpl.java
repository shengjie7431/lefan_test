package com.lefancrm.apicenter.backendapi.impl;


import com.lefancrm.apicenter.backendapi.BackendStaffPostRankApi;
import com.lefancrm.apicenter.dao.StaffPostRankMapper;
import com.lefancrm.apicenter.model.StaffPostAppellation;
import com.lefancrm.apicenter.model.StaffPostRank;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;


/**
 * jani
 * 2021-03-23
 */
@ApiService(descript = "岗位职级Api")
@Service
public class BackendStaffPostRankApiImpl extends BaseServiceImpl implements BackendStaffPostRankApi {
    @Autowired
    private StaffPostRankMapper staffPostRankMapper;

    @ApiMethod(needLogin = false,descript = "根据ID删除岗位职级" ,value = "staff-post-rank-del")
    @Override
    public ApiResponse staffPostRankDel(ApiRequest apiReq) {
        Long id =apiReq.getLong("id");
        int result=staffPostRankMapper.deleteByPrimaryKey(id);
        if(result>0)
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        else
            return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false,descript = "新增岗位职级" ,value = "staff-post-rank-add")
    @Override
    public ApiResponse staffPostRankAdd(ApiRequest apiReq)  {
        Long userId= this.getCurrentUserId(apiReq);
        String  rankName="";
        String  rankDesc="";
        if(!StringUtils.isEmpty(apiReq.get("rankName"))){
            rankName=apiReq.getString("rankName");
        }
        if(!StringUtils.isEmpty(apiReq.get("rankDesc"))){
            rankDesc=apiReq.getString("rankDesc");
        }

        StaffPostRank staffPostRank=new StaffPostRank();
        staffPostRank.setRankName(rankName);
        staffPostRank.setRankDesc(rankDesc);
        staffPostRank.setCreateTime(new Date());
        staffPostRank.setDeleteFlag(0);
        staffPostRank.setState(0);
        if(null!=userId){
            staffPostRank.setCreateBy(userId+"");
        }
        int result=staffPostRankMapper.insert(staffPostRank);
        if(result>0)
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        else
            return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false,descript = "新增岗位职级selective" ,value = "staff-post-rank-add-selective")
    @Override
    public ApiResponse staffPostRankAddSelective(ApiRequest apiReq)  {
        Long userId= this.getCurrentUserId(apiReq);
        String  rankName="";
        String  rankDesc="";
        if(!StringUtils.isEmpty(apiReq.get("rankName"))){
            rankName=apiReq.getString("rankName");
        }
        if(!StringUtils.isEmpty(apiReq.get("rankDesc"))){
            rankDesc=apiReq.getString("rankDesc");
        }

        StaffPostRank staffPostRank=new StaffPostRank();
        staffPostRank.setRankName(rankName);
        staffPostRank.setRankDesc(rankDesc);
        staffPostRank.setCreateTime(new Date());
        staffPostRank.setDeleteFlag(0);
        staffPostRank.setState(0);
        if(null!=userId){
            staffPostRank.setCreateBy(userId+"");
        }
        int result=staffPostRankMapper.insertSelective(staffPostRank);
        if(result>0)
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        else
            return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false,descript = "根据ID查询岗位职级" ,value = "query-staff-post-rank-by-id")
    @Override
    public ApiResponse queryStaffPostRankById(ApiRequest apiReq){
        Long id =apiReq.getLong("id");
        StaffPostRank staffPostRank=this.staffPostRankMapper.selectByPrimaryKey(id);
        return new ApiResponse<StaffPostRank>(ApiMsgEnum.SUCCESS,1,staffPostRank);
    }

    @ApiMethod(needLogin = false,descript = "根据入参修改相关参数" ,value = "update-staff-post-rank-by-param")
    @Override
    public ApiResponse updateStaffPostRankByParam(ApiRequest apiReq) {
        Long id= apiReq.getLong("id");
        String  rankName="";
        String  rankDesc="";
        if(!StringUtils.isEmpty(apiReq.get("rankName"))){
            rankName=apiReq.getString("rankName");
        }
        if(!StringUtils.isEmpty(apiReq.get("rankDesc"))){
            rankDesc=apiReq.getString("rankDesc");
        }

        StaffPostRank staffPostRank=staffPostRankMapper.selectByPrimaryKey(id);
        staffPostRank.setRankName(rankName);
        staffPostRank.setRankDesc(rankDesc);
        staffPostRank.setState(apiReq.getInt("state"));
        int result=staffPostRankMapper.updateByPrimaryKey(staffPostRank);
        if(result>0)
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        else
            return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false,descript = "全实体修改" ,value = "update-staff-post-rank")
    @Override
    public ApiResponse updateStaffPostRank(ApiRequest apiReq) {
        Long id= apiReq.getLong("id");
        String  rankName="";
        String  rankDesc="";
        if(!StringUtils.isEmpty(apiReq.get("rankName"))){
            rankName=apiReq.getString("rankName");
        }
        if(!StringUtils.isEmpty(apiReq.get("rankDesc"))){
            rankDesc=apiReq.getString("rankDesc");
        }

        StaffPostRank staffPostRank=staffPostRankMapper.selectByPrimaryKey(id);
        staffPostRank.setRankName(rankName);
        staffPostRank.setRankDesc(rankDesc);
        staffPostRank.setState(apiReq.getInt("state"));
        int result=staffPostRankMapper.updateByPrimaryKeySelective(staffPostRank);
        if(result>0)
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        else
            return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false,descript = "查询岗位职级列表" ,value = "query-staff-post-rank-list")
    @Override
    public ApiResponse selectStaffPostRankList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
        int count = staffPostRankMapper.listSize(apiReq);
        List<StaffPostRank> list = staffPostRankMapper.list(apiReq);
        return new ApiResponse<List<StaffPostRank>>(ApiMsgEnum.SUCCESS, count, list);
    }

    @ApiMethod(needLogin = false,descript = "查询职务称谓列表" ,value = "query-staff-post-rank-list-all")
    @Override
    public ApiResponse selectStaffPostRankListAll(ApiRequest apiReq) {
        List<StaffPostRank> list = staffPostRankMapper.listAll(apiReq);
        return new ApiResponse<List<StaffPostRank>>(ApiMsgEnum.SUCCESS, list.size(), list);
    }

}
