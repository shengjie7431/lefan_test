package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendStaffPostAppellationApi;
import com.lefancrm.apicenter.dao.StaffPostAppellationMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.StaffPostAppellation;
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
@ApiService(descript = "职务称谓Api")
@Service
public class BackendStaffPostAppellationApiImpl extends BaseServiceImpl implements BackendStaffPostAppellationApi {

    @Autowired
    private StaffPostAppellationMapper staffPostAppellationMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @ApiMethod(needLogin = false,descript = "根据ID删除职务称谓" ,value = "staff-post-appellation-del")
    @Override
    public ApiResponse staffPostAppellationDel(ApiRequest apiReq){
        Long id =apiReq.getLong("id");
        int result=staffPostAppellationMapper.deleteByPrimaryKey(id);
        if(result>0)
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        else
            return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false,descript = "新增职务称谓" ,value = "staff-post-appellation-add")
    @Override
    public ApiResponse staffPostAppellationAdd(ApiRequest apiReq) {
        Long userId= this.getCurrentUserId(apiReq);
        String  appellationName="";
        String  appellationDesc="";
        if(!StringUtils.isEmpty(apiReq.get("appellationName"))){
              appellationName=apiReq.getString("appellationName");
        }
        if(!StringUtils.isEmpty(apiReq.get("appellationDesc"))){
              appellationDesc=apiReq.getString("appellationDesc");
        }

        StaffPostAppellation staffPostAppellation=new StaffPostAppellation();
        staffPostAppellation.setAppellationName(appellationName);
        staffPostAppellation.setAppellationDesc(appellationDesc);
        staffPostAppellation.setCreateTime(new Date());
        staffPostAppellation.setDeleteFlag(0);
        staffPostAppellation.setState(0);
        if(null!=userId){
            staffPostAppellation.setCreateBy(userId+"");
        }
        int result=staffPostAppellationMapper.insert(staffPostAppellation);
        if(result>0)
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        else
            return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false,descript = "根据参数新增职务称谓" ,value = "staff-post-appellation-add-selective")
    @Override
    public ApiResponse staffPostAppellationAddSelective(ApiRequest apiReq) {
        Long userId= this.getCurrentUserId(apiReq);
        String  appellationName="";
        String  appellationDesc="";
        if(!StringUtils.isEmpty(apiReq.get("appellationName"))){
            appellationName=apiReq.getString("appellationName");
        }
        if(!StringUtils.isEmpty(apiReq.get("appellationDesc"))){
            appellationDesc=apiReq.getString("appellationDesc");
        }

        StaffPostAppellation staffPostAppellation=new StaffPostAppellation();
        staffPostAppellation.setAppellationName(appellationName);
        staffPostAppellation.setAppellationDesc(appellationDesc);
        staffPostAppellation.setCreateTime(new Date());
        staffPostAppellation.setDeleteFlag(0);
        staffPostAppellation.setState(0);
        if(null!=userId){
            staffPostAppellation.setCreateBy(userId+"");
        }
        int result=staffPostAppellationMapper.insertSelective(staffPostAppellation);
        if(result>0)
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        else
            return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false,descript = "根据ID查询职务称谓" ,value = "query-staff-post-appellation-by-id")
    @Override
    public ApiResponse queryStaffPostAppellationById(ApiRequest apiReq) {
        Long id =apiReq.getLong("id");
        StaffPostAppellation staffPostAppellation=this.staffPostAppellationMapper.selectByPrimaryKey(id);
        return new ApiResponse<StaffPostAppellation>(ApiMsgEnum.SUCCESS,1,staffPostAppellation);
    }

    @ApiMethod(needLogin = false,descript = "职务称谓根据入参修改" ,value = "update-staff-post-appellation-by-param")
    @Override
    public ApiResponse updateStaffPostAppellationByParam(ApiRequest apiReq)  {
        Long id= apiReq.getLong("id");
        String  appellationName="";
        String  appellationDesc="";
        if(!StringUtils.isEmpty(apiReq.get("appellationName"))){
            appellationName=apiReq.getString("appellationName");
        }
        if(!StringUtils.isEmpty(apiReq.get("appellationDesc"))){
            appellationDesc=apiReq.getString("appellationDesc");
        }

        StaffPostAppellation staffPostAppellation=staffPostAppellationMapper.selectByPrimaryKey(id);
        staffPostAppellation.setAppellationName(appellationName);
        staffPostAppellation.setAppellationDesc(appellationDesc);
        staffPostAppellation.setState(apiReq.getInt("state"));
        int result=staffPostAppellationMapper.updateByPrimaryKey(staffPostAppellation);
        if(result>0)
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        else
            return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false,descript = "职务称谓全实体修改" ,value = "update-staff-post-appellation")
    @Override
    public ApiResponse updateStaffPostAppellation(ApiRequest apiReq) {
        Long id= apiReq.getLong("id");
        String  appellationName="";
        String  appellationDesc="";
        if(!StringUtils.isEmpty(apiReq.get("appellationName"))){
            appellationName=apiReq.getString("appellationName");
        }
        if(!StringUtils.isEmpty(apiReq.get("appellationDesc"))){
            appellationDesc=apiReq.getString("appellationDesc");
        }

        StaffPostAppellation staffPostAppellation=staffPostAppellationMapper.selectByPrimaryKey(id);
        staffPostAppellation.setAppellationName(appellationName);
        staffPostAppellation.setAppellationDesc(appellationDesc);
        staffPostAppellation.setState(apiReq.getInt("state"));
        int result=staffPostAppellationMapper.updateByPrimaryKeySelective(staffPostAppellation);
        if(result>0)
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        else
            return new ApiResponse(ApiMsgEnum.FAIL);
    };

    @ApiMethod(needLogin = false,descript = "查询职务称谓列表分页" ,value = "query-staff-post-appellation-list")
    @Override
    public ApiResponse selectStaffPostAppellationList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
        int count = staffPostAppellationMapper.listSize(apiReq);
        List<StaffPostAppellation> list = staffPostAppellationMapper.list(apiReq);
        return new ApiResponse<List<StaffPostAppellation>>(ApiMsgEnum.SUCCESS, count, list);
    }

    @ApiMethod(needLogin = false,descript = "查询职务称谓列表" ,value = "query-staff-post-appellation-list-all")
    @Override
    public ApiResponse selectStaffPostAppellationListAll(ApiRequest apiReq) {
        List<StaffPostAppellation> list = staffPostAppellationMapper.listAll(apiReq);
        return new ApiResponse<List<StaffPostAppellation>>(ApiMsgEnum.SUCCESS, list.size(), list);
    }
}
