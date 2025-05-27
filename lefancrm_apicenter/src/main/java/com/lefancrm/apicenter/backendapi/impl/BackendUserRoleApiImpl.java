package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendUserRoleApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.TreeData;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.tree.impl.AreaTreeImpl;
import com.lefancrm.apicenter.tree.impl.UserOrgTreeImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiParam;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
@ApiService(descript = "机构公司")
public class BackendUserRoleApiImpl extends BaseServiceImpl implements BackendUserRoleApi {
	private static final Logger loger = Logger.getLogger(BackendOrgApiImpl.class);

	@Autowired
	private OrgInfoMapper orgInfoMapper;
    @Autowired
    private OrgFileMidMapper orgFileMidMapper;
    @Autowired
    private CommonAreaMapper commonAreaMapper;

    @Autowired
    private CommonFileMapper commonFileMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private BusinessRoleMapper businessRoleMapper;

    @Autowired
    private BusUserRoleMapper busUserRoleMapper;

    @Autowired
    private UserOrgTreeImpl userOrgTree;

    @Autowired
    private AreaTreeImpl areaTree;

    @Autowired
    private FrontRoleMenuMapper frontRoleMenuMapper;

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "其他接口调用机构公司列表", value = "backend-org-list-to")
    @Override
    public ApiResponse<List<OrgInfo>> queryOrgListTo(ApiRequest apiReq){
        List<OrgInfo> orgInfos = orgInfoMapper.queryOrgList(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS,orgInfos==null?0:orgInfos.size(),orgInfos);
//        }
    }
	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "机构公司列表", value = "backend-user-role-list")
	@Override
	public ApiResponse<List<OrgInfo>> queryOrgList(ApiRequest apiReq){
        Integer operatorId = apiReq.getInt("operatorId");
            List<OrgInfo> orgInfos = orgInfoMapper.queryOrgList(apiReq);
//         List<OrgInfo> menuList = this._createTree(orgInfos);
            List<TreeData> _results = userOrgTree._resultTreeDate(orgInfos);
            return new ApiResponse(ApiMsgEnum.SUCCESS,_results==null?0:_results.size(),_results);
//        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    @ApiMethod(descript = "查询树形结构时返回的第一个机构信息", value = "user-role-tree-data")
    public ApiResponse orgTreeData(ApiRequest apiReq) {
        Integer operatorId = apiReq.getInt("operatorId");
            apiReq.put("orgParentid",-1);
            List<OrgInfo> orgInfos = orgInfoMapper.queryOrgList(apiReq);
            return new ApiResponse(ApiMsgEnum.SUCCESS,orgInfos==null?0:1,!orgInfos.isEmpty()?orgInfos.get(0):null);

    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "省市区联动查询", value = "backend-org-to-add")
    @Override
    public ApiResponse<List<CommonArea>> selectArea(ApiRequest apiReq) {
        List<CommonArea> commonAreas = commonAreaMapper.selectAreaByParentId(apiReq.getLong("parentId"));
        return new ApiResponse<List<CommonArea>>(ApiMsgEnum.SUCCESS,commonAreas==null?0:commonAreas.size(),commonAreas);
    }



    @SuppressWarnings("rawtypes")
    @Override
    @ApiMethod(descript = "机构详情", value = "backend-user-role-info")
    public ApiResponse getById(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        if (StringUtils.isEmpty(id)) {
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        OrgInfo record = orgInfoMapper.selectByPrimaryKey(id);
        List<OrgFileMid> orgFileMids = orgFileMidMapper.queryOrgFiles(record.getId());
        List<CommonFile> files = new ArrayList<CommonFile>();
        for (OrgFileMid orgFileMid : orgFileMids){
            CommonFile commonFile = commonFileMapper.selectByPrimaryKey(orgFileMid.getFileId());
            if(commonFile != null){
                files.add(commonFile);
            }
        }
        record.setFiles(files);
        return new ApiResponse<OrgInfo>(ApiMsgEnum.SUCCESS, (record == null ? 0 : 1), record);
    }


    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "查询机构用户", value = "backend-user-query-user")
    @Override
    public ApiResponse queryUser(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
        List<UserInfo> userInfos = userInfoMapper.selectUserByOrgId(apiReq);
        for (UserInfo userInfo : userInfos){
            List<BusUserRole> busUserRoles = busUserRoleMapper.orgUserRoleList(userInfo.getUserId());
            if(busUserRoles == null){
                busUserRoles = new ArrayList<>();
            }
            userInfo.setBusUserRoles(busUserRoles);
        }
        int count = userInfoMapper.selectUserByOrgIdCount(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS,userInfos==null?0:count,userInfos);
    }


    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "机构用户列表", value = "user-business-role-list")
    @Override
    public ApiResponse businessRoleList(ApiRequest apiReq) {
        if(apiReq.getString("all") == null){
            this.setBackendPageSize(apiReq);
        }
        Long userId = apiReq.getLong("userId");
        String roles = businessRoleMapper.selectUserRoles(userId);
        String ids = null;
        if(roles.contains("19")){
            ids= "2,3,6,7,17,19,20";
        }else if (roles.contains("17")){
            ids = "2,3,6,7,17,20";
        }else if (roles.contains("20")){
            ids = "2,3,6,7,20";
        }
        apiReq.put("ids",ids);
        List<BusinessRole> businessRoles = businessRoleMapper.selectAll(apiReq);

        int count  = businessRoleMapper.selectAllCount(apiReq);
        for(BusinessRole businessRole : businessRoles){
            if(businessRole.getCreateBy() == null || businessRole.getCreateBy().equals("")){
                continue;
            }
//           SysUser sysUser =  sysUserMapper.selectByPrimaryKey(Integer.parseInt(businessRole.getCreateBy()));
//            businessRole.setAdminName(sysUser.getRealname());
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,businessRoles==null?0:count,businessRoles);
    }

    @Override
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "查询机构个人角色", value = "user-role", apiParams = {@ApiParam(descript = "用户ID", name = "userId")})
    public ApiResponse orgUserRole(ApiRequest apiReq) {
        Long userId = apiReq.getLong("userId");
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(userId);
        return new ApiResponse(ApiMsgEnum.FAIL,userRoles != null?userRoles.size():0,userRoles);
    }

    @Override
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "所有角色", value = "backend-business-role-list" )
    public ApiResponse roleList(ApiRequest apiReq) {
        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex",null);
        map.put("pageSize",null);
        List<BusinessRole> roles = businessRoleMapper.selectAll(map);
        int count = businessRoleMapper.selectAllCount(map);
        return new ApiResponse(ApiMsgEnum.FAIL,count,roles);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "查询地区", value = "backend-area-by-id")
    @Override
    public ApiResponse selectByAreaId(ApiRequest apiReq) {
        CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(apiReq.getLong("areaId"));
        if(commonArea.getCityType()!=null && commonArea.getCityType() == 1){
            List<CommonArea> commonAreas = commonAreaMapper.selectAreaByParentId(commonArea.getAreaId());
            return new ApiResponse(ApiMsgEnum.SUCCESS,commonAreas==null?0:commonAreas.size(),commonAreas);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,null,null);
    }
}
