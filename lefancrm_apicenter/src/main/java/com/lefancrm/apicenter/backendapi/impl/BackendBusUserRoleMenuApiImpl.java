package com.lefancrm.apicenter.backendapi.impl;


import com.lefancrm.apicenter.backendapi.BackendBusUserRoleMenuApi;
import com.lefancrm.apicenter.dao.BusUserRoleMapper;
import com.lefancrm.apicenter.dao.BusinessRoleMapper;
import com.lefancrm.apicenter.dao.FrontMenuMapper;
import com.lefancrm.apicenter.dao.FrontRoleMenuMapper;
import com.lefancrm.apicenter.dto.TreeData;
import com.lefancrm.apicenter.model.BusUserRole;
import com.lefancrm.apicenter.model.BusinessRole;
import com.lefancrm.apicenter.model.FrontMenu;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiParam;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
@ApiService(descript = "后台系统菜单管理")
public class BackendBusUserRoleMenuApiImpl extends BaseServiceImpl implements BackendBusUserRoleMenuApi {

    @Autowired
    private FrontMenuMapper frontMenuMapper;

    @Autowired
    private BusinessRoleMapper businessRoleMapper;

    @Autowired
    private FrontRoleMenuMapper frontRoleMenuMapper;

    @Autowired
    private BusUserRoleMapper busUserRoleMapper;

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    public List<FrontMenu> _createTree(List<FrontMenu> _results) {
        if (CollectionUtils.isEmpty(_results)) {
            return null;
        }
        List<FrontMenu> treeList = new ArrayList<FrontMenu>();
        int len = _results.size();
        for (int i = 0; i < len; i++) {
            FrontMenu menu = _results.get(i);
            if (!treeList.contains(menu)) {
                treeList.add(menu);
            }
            List<FrontMenu> childList = _getChild(_results, menu.getId());
            if (childList.size() > 0) {
                if (!treeList.containsAll(childList)) {
                    for (FrontMenu child : childList) {
                        treeList.add(child);
                        childList = _getChild(_results, child.getId());
                        if (!treeList.containsAll(childList)) {
                            treeList.addAll(childList);
                        }
                    }
                }
            }
        }
        return treeList;
    }

    public List<FrontMenu> _getChild(List<FrontMenu> _results, Integer parentId) {
        List<FrontMenu> childList = new ArrayList<FrontMenu>();
        for (FrontMenu m : _results) {
            if (m.getPid() == parentId) {
                childList.add(m);
            }
        }
        return childList;
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "是否是管理员", value = "is_role")
    @Override
    public ApiResponse isRole(ApiRequest apiReq) {
        Long userId = apiReq.getLong("operatorId");
        List<BusUserRole> roleList = busUserRoleMapper.orgUserRoleList(userId);
        if (roleList == null || roleList.isEmpty()) {
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, false);
        }
        for (BusUserRole sysUserRole : roleList) {
            if (sysUserRole.getRoleId() == 19) { // 总监权限
                return new ApiResponse(ApiMsgEnum.SUCCESS, 1, true);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,0,false);
    }


    @ApiMethod(descript = "判断某个业务用户是否有某种权限",value = "is-some-role-from-bususer")
    @Override
    public ApiResponse isSomeRoleFromUser(ApiRequest apiReq){
        BusUserRole busUserRole = busUserRoleMapper.selectBusRoleInfo(apiReq);
        if (busUserRole == null){
            return new ApiResponse(ApiMsgEnum.SUCCESS,0,false);
        }else{
            return new ApiResponse(ApiMsgEnum.SUCCESS,0,true);
        }
    }

    public List<BusUserRole> getRoleList(ApiRequest apiReq){
        Long userId = apiReq.getLong("operatorId");
        List<BusUserRole> roleList = busUserRoleMapper.orgUserRoleList(userId);
        return roleList;
    }

    @ApiMethod(descript = "当前登录人的所有角色",value = "backend-current-user-role-list")
    @Override
    public ApiResponse<List<BusUserRole>> getCurrentUserRoleList(ApiRequest apiReq){
        Long userId = apiReq.getLong("operatorId");
        List<BusUserRole> roleList = busUserRoleMapper.orgUserRoleList(userId);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,roleList);
    }
}
