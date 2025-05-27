package com.lefancrm.apicenter.backendapi.impl;

import cn.jpush.api.utils.StringUtils;
import com.lefancrm.apicenter.backendapi.BackendInfoSafeCompanyApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.UserInfoDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.apicenter.util.wechatPay.util.MD5Util;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by wangwei on 2018/11/28.
 */
@Service
@ApiService(descript = "保险公司API")
public class BackendInfoSafeCompanyApiImpl extends BaseServiceImpl implements BackendInfoSafeCompanyApi {

    @Autowired
    private InfoSafeCompanyMapper infoSafeCompanyMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private InfoSafeUserMapper infoSafeUserMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private UserLoginMapper userLoginMapper;

    /**
     *  保险公司清单
     * @param apiRequest
     * @return
     */
    @ApiMethod(descript = "保险公司清单" ,value = "backend-info-safe-company-list")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        this.setBackendPageSize(apiRequest);
        apiRequest.put("isDelete",0);//未删除
        int count = infoSafeCompanyMapper.selectListSize(apiRequest);
        List<InfoSafeCompany> list = infoSafeCompanyMapper.selectList(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    @ApiMethod(descript = "保险公司保存、修改" ,value = "backend-info-safe-company-edit")
    @Override
    public ApiResponse edit(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        InfoSafeCompany infoSafeCompany = infoSafeCompanyMapper.selectByPrimaryKey(id);

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,infoSafeCompany);
    }

    @ApiMethod(descript = "保险公司保存、修改" ,value = "backend-info-safe-company-save")
    @Override
    public ApiResponse save(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        InfoSafeCompany infoSafeCompany = infoSafeCompanyMapper.selectByPrimaryKey(id);
        Long currentUserId = apiRequest.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);

        if (infoSafeCompany == null){
            infoSafeCompany = ConvertToBeanUtil.toBeanFromApiRequest(apiRequest,InfoSafeCompany.class);
            infoSafeCompany.setCreateBy(currentUserId);
            infoSafeCompany.setCreateByName(userInfo.getUserName());
            infoSafeCompany.setCreateTime(new Date());
            infoSafeCompany.setIsDelete(0);
            infoSafeCompanyMapper.insert(infoSafeCompany);
        }else{
            infoSafeCompany = ConvertToBeanUtil.toBeanFromApiRequest(apiRequest,infoSafeCompany);
            infoSafeCompany.setUpdateBy(currentUserId);
            infoSafeCompany.setUpdateByName(userInfo.getUserName());
            infoSafeCompany.setUpdateTime(new Date());
            infoSafeCompanyMapper.updateByPrimaryKey(infoSafeCompany);
        }

        //将填写的联系人，加入到userInfo中
        String safeTel = apiRequest.getString("safeTel");
        Map<String,Object> map =  new HashMap<>();
        map.put("userTelphone",safeTel);
        UserInfo userInfoNew = userInfoMapper.selectUserInfoByPhone(map);
        if(userInfoNew== null){
            //先添加login信息
            UserLogin userLogin = userLoginMapper.selectByPhone(safeTel);
            if(userLogin != null){
                return new ApiResponse(ApiMsgEnum.UsernameBeenRegistered);
            }
            userLogin = new UserLogin();
            userLogin.setUserTelphone(safeTel);
            userLogin.setPassword(MD5Util.MD5Encode("123456@Qaz", null));
            userLogin.setCreateTime(new Date());
            int retL = userLoginMapper.insertSelective(userLogin);
            if(retL < 1){
                return new ApiResponse(ApiMsgEnum.FAIL);
            }

            userInfoNew = new UserInfo();
            userInfoNew.setUserId(userLogin.getUserId());
            userInfoNew.setUserName(apiRequest.getString("safeUser"));
            userInfoNew.setUserTel(safeTel);
            userInfoNew.setCreateTime(new Date());
            userInfoNew.setIsTester(0);
            userInfoNew.setDeleteFlag(0);
            userInfoNew.setUserType(1);//用户类型：1普通用户，2机构用户，3测试用户，4其他
            int ret = userInfoMapper.insertSelective(userInfoNew);
            if(ret < 1){
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        }
        //将填写的联系人，加上“排查人”角色
        map =  new HashMap<>();
        map.put("userId",userInfoNew.getUserId());
        map.put("roleId",41);
        BusUserRole busUserRole = busUserRoleMapper.selectBusRoleInfo(map);
        if(busUserRole == null){
            busUserRole = new BusUserRole();
            busUserRole.setRoleId(41L);
            busUserRole.setUserId(userInfoNew.getUserId());
            busUserRoleMapper.insertSelective(busUserRole);
        }

        //将填写的联系人，加入到本保险公司下
        map = new HashMap<>();
        map.put("safeCompanyId",infoSafeCompany.getId());
        map.put("userId",userInfoNew.getUserId());
        List<InfoSafeUser> infoSafeUserList = infoSafeUserMapper.selectList(map);
        if (infoSafeUserList.size()==0){
            InfoSafeUser infoSafeUser = new InfoSafeUser();
            infoSafeUser.setSafeCompanyId(infoSafeCompany.getId());
            infoSafeUser.setUserId(userInfoNew.getUserId());
            infoSafeUserMapper.insertSelective(infoSafeUser);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 分配用户
     * @param apiRequest
     * @return
     */
    @ApiMethod(descript = "分配用户" ,value = "backend-select-all-user-info-list")
    @Override
    public ApiResponse selectAllUser(ApiRequest apiRequest) {
        this.setBackendPageSize(apiRequest);
        int count = userInfoMapper.selectUserCount(apiRequest);
        List<UserInfo> userInfo = userInfoMapper.selectUserInfoByParam(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,userInfo);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "确认分配用户", value = "backend-confirm-user-info")
    @Override
    public ApiResponse confirmUser(ApiRequest apiReq) {
        String users = apiReq.getString("users");
        Long safeCompanyId = apiReq.getLong("safeCompanyId");

        InfoSafeCompany infoSafeCompany = infoSafeCompanyMapper.selectByPrimaryKey(safeCompanyId);
        if(infoSafeCompany == null ){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        String arr[] = users.split(",");
        for (String str : arr){
            Map<String,Object> map = new HashMap<>();
            map.put("safeCompanyId",safeCompanyId);
            map.put("userId",str);
            List<InfoSafeUser> infoSafeUserList = infoSafeUserMapper.selectList(map);
            //如该用户没有分配到该公司，新增
            if (infoSafeUserList.size()==0){
                InfoSafeUser infoSafeUser = new InfoSafeUser();
                infoSafeUser.setSafeCompanyId(safeCompanyId);
                infoSafeUser.setUserId(Long.parseLong(str));
                infoSafeUserMapper.insertSelective(infoSafeUser);
            }
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(Long.parseLong(str));
            Boolean safer = isRoleUser(userRoles,41L);//排查人
            //如不是“排查人”角色，新增
            if (!safer){
                BusUserRole userRole = new BusUserRole();
                userRole.setUserId(Long.parseLong(str));
                userRole.setRoleId(41L);
                busUserRoleMapper.insertSelective(userRole);
            }

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

    /**
     * 查询保险公司名下用户
     * @param apiRequest
     * @return
     */
    @ApiMethod(descript = "查询保险公司名下用户" ,value = "backend-select-company-user-info-list")
    @Override
    public ApiResponse companyUser(ApiRequest apiRequest) {
        this.setBackendPageSize(apiRequest);
        int count = infoSafeUserMapper.selectUserInfoCompanyCount(apiRequest);
        List<UserInfoDto> userInfo = infoSafeUserMapper.selectUserInfoCompany(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,userInfo);
    }

    /**
     * 移除保险公司名下用户
     * @param apiRequest
     * @return
     */
    @ApiMethod(descript = "移除保险公司名下用户" ,value = "backend-info-safe-user-remove")
    @Override
    public ApiResponse remove(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        InfoSafeUser infoSafeUser =infoSafeUserMapper.selectByPrimaryKey(id);

        //移除该用户
        int result = infoSafeUserMapper.deleteByPrimaryKey(id);

        //查询该用户不再为所有保险公司名下，同时移除该用户的“排查人”角色
        Map<String,Object> map = new HashMap<>();
        map.put("userId",infoSafeUser.getUserId());
        List<InfoSafeUser> list = infoSafeUserMapper.selectList(map);
        if(list.size() == 0){
            Map<String,Object> map2 = new HashMap<>();
            map2.put("userId",infoSafeUser.getUserId());
            map2.put("roleId",41L);
            result = busUserRoleMapper.deleteByParam(map2);
        }

        if(result > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }

    }

    @ApiMethod(descript = "保险公司删除" ,value = "backend-info-safe-company-delete")
    @Override
    public ApiResponse delete(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        InfoSafeCompany infoSafeCompany = infoSafeCompanyMapper.selectByPrimaryKey(id);
        infoSafeCompany.setIsDelete(1);//删除
        infoSafeCompanyMapper.updateByPrimaryKeySelective(infoSafeCompany);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,infoSafeCompany);
    }
}
