package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiParam;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import com.lefancrm.base.utils.Md5Util;
import com.lefancrm.apicenter.backendapi.BackendAdminApi;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
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
@ApiService(descript = "后台管理员")
public class BackendAdminApiImpl extends BaseServiceImpl implements BackendAdminApi {
	private static final Logger loger = Logger.getLogger(BackendAdminApiImpl.class);

    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
	@Resource
	private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private FrontRoleMenuMapper frontRoleMenuMapper;

    @Autowired
    private OrgInfoMapper orgInfoMapper;

    @Autowired
    private BusUserRoleMapper busUserRoleMapper;

	@SuppressWarnings("rawtypes")
	@ApiMethod(descript = "登录", value = "backend-admin-login", apiParams = { @ApiParam(descript = "用户名", name = "userTelphone"), @ApiParam(descript = "密码", name = "password"),
			@ApiParam(descript = "登录IP", name = "login_ip") })
	@Override
	public ApiResponse login(ApiRequest apiReq) {
		Object usernameObj = apiReq.get("userTelphone");
		Object passwordObj = apiReq.get("password");
		Object login_ip = apiReq.get("login_ip");
		if (StringUtils.isEmpty(usernameObj) || StringUtils.isEmpty(passwordObj) || StringUtils.isEmpty(login_ip)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
		// 登录
        UserLogin user = userLoginMapper.selectByPhone(usernameObj.toString());
		if (user == null) {
			return new ApiResponse(ApiMsgEnum.UserDosentExist);
		}
        UserInfo u = this.userInfoMapper.selectByPrimaryKey(user.getUserId());
        if (u.getUserState() == null){
            u.setUserState(0);
        }
		if (u.getDeleteFlag() == 1 || u.getUserState() != 0){
            return new ApiResponse(ApiMsgEnum.UserDosentExist);
        }

        // ############### 权限判断 ##################
        if(!isRole(user.getUserId())){
            return new ApiResponse(ApiMsgEnum.UserDosentExist);
        }
        // ############### 权限判断 ##################

		if (!Md5Util.encodeString(passwordObj.toString()).equals(user.getPassword())) {
			return new ApiResponse(ApiMsgEnum.UsernameOrPasswordException);
		}
		// 返回用户信息
        if(u != null){
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("userId",u.getUserId());
            List<FrontRoleMenu> userRoles = frontRoleMenuMapper.selectList(paramMap);
            u.setUserRoleList(userRoles);

            List<BusUserRole> list = busUserRoleMapper.orgUserRoleList(u.getUserId());
            if(!list.isEmpty()){
                List<Long> busUserRoles = new ArrayList<>();
                for (BusUserRole busUserRole : list){
                    busUserRoles.add(busUserRole.getRoleId());
                }
                u.setBusUserRoleIds(busUserRoles);
            }

            u.setModifyTime(new Date());//记录最新的登录时间
            userInfoMapper.updateByPrimaryKey(u);

        }
		return new ApiResponse<UserInfo>(ApiMsgEnum.SUCCESS, 1, u);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "更改密码", value = "backend-admin-updatePassword", apiParams = { @ApiParam(descript = "用户ID", name = "userId"), @ApiParam(descript = "新密码", name = "new_password"),
			@ApiParam(descript = "旧密码", name = "old_password") })
	public ApiResponse updatePassword(ApiRequest apiReq) {
        System.out.print("ceshi ");
        Integer userId = (Integer)apiReq.get("userId");
     /*  Long userId =  Long.parseLong((String) idObj);*/
		Object newPasswordObj = apiReq.get("new_password");
		Object oldPasswordObj = apiReq.get("old_password");
		if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(newPasswordObj) || StringUtils.isEmpty(oldPasswordObj)) {
			return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
		}
        UserLogin user = userLoginMapper.selectByPrimaryKey(userId.longValue());
		if (user == null) {
			return new ApiResponse(ApiMsgEnum.UserDosentExist);
		}
		if (!Md5Util.encodeString(oldPasswordObj.toString()).equals(user.getPassword())) {
			return new ApiResponse(ApiMsgEnum.CurrentPasswordException);
		}
		String newPassword = Md5Util.encodeString(newPasswordObj.toString());
        UserLogin record = new UserLogin();
        record.setUserId(user.getUserId());
        record.setPassword(newPassword);
		this.userLoginMapper.updateByPrimaryKeySelective(record);
		return new ApiResponse(ApiMsgEnum.SUCCESS);
	}


    // 权限判断方法
    private boolean isRole(Long userId) {
        List<BusUserRole> roleList = busUserRoleMapper.orgUserRoleList(userId);
        if (roleList == null || roleList.isEmpty()) {
            return false;
        }
        return true;
//        for (BusUserRole sysUserRole : roleList) {
//            if (sysUserRole.getRoleId() == 19 || sysUserRole.getRoleId() == 17 || sysUserRole.getRoleId() == 20 || sysUserRole.getRoleId() == 2 ) { // 总监权限
//                return true;
//            }
//        }
//        return false;
    }

}
