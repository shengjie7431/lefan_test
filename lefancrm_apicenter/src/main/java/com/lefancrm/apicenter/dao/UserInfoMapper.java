package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.UserInfoOprDTO;
import com.lefancrm.apicenter.dto.hzReport.OprUserDataItemDTO;
import com.lefancrm.apicenter.model.ActivityDayReport;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo selectUserInfoByUserId(Map<String, Object> paramMap);

    List<UserInfo> selectUserInfoByParam(Map<String, Object> paramMap);


    List<UserInfo> selectByStateList(Map<String, Object> paramMap);
/**
     * 查询当前用户下的机构人员
     * @param paramMap
     * @return
     */
    List<UserInfo> selectAgencyManager(Map<String, Object> paramMap);
    /**
     * 根据用户ID查询所属机构
     * @param paramMap
     * @return
     */
    UserInfo selectAgency(Map<String, Object> paramMap);

    /**
     * 查询机构下的用户
     * @param paramMap
     * @return
     */
    List<UserInfo> selectUserByOrgId(Map<String, Object> paramMap);

    int selectUserCount(Map<String, Object> paramMap);
    int selectUserByOrgIdCount(Map<String, Object> paramMap);
    int selectByStateListCount(Map<String, Object> paramMap);
    List<UserInfo> selectInsOfficerList();

    List<UserInfo> selectShareInsOfficerList(Long orgId);

    UserInfo selectUserInfo(Map<String, Object> map);

    int updateSettingUserInfo(Map<String, Object> map);

    UserInfo selectUserInfoByPhone(Map<String, Object> map);

    // 根据查询orgId下的所有用户
    List<UserInfo> selectAllUserByOrgId(ApiRequest apiReq);
    List<UserInfo> selectListForBackend(Map<String, Object> map);

    //查询是CC人员职级职位的用户list
    List<UserInfo> selectCcUserInfoList(Map<String, Object> map);

    /**
     * 查询当前用户下的机构下的业务员、评估员、索赔员
     * @param paramMap
     * @return
     */
    List<UserInfo> selectUserInfoByOrgIdAndRoleId(Map<String, Object> paramMap);

    /**
     * 查看机构下面的（业务员）用户
     *
     * @return
     */
    List<UserInfo> selectSalesUserByOrgId(Long orgId);

    /**
     * 根据角色查询用户列表
     * @param roleId
     * @return
     */
    List<UserInfo> selectUserByRoleId(Long roleId);

    /**
     * 非调查员或委托人的用户
     * @param
     * @return
     */
    List<UserInfo> selectUserInfoForSurvey(Map<String,Object> map);

    int selectUserInfoForSurveysSize(Map<String,Object> map);

    //根据角色查询用户列表
    List<UserInfo> selectListByRoleId(Map<String,Object> map);
    int selectSizeByRoleId(Map<String,Object> map);

    //狄大人-调查审核（改派归属人 改派时，对人员的筛选）
    List<UserInfo> selectListForBelongUser(Map<String,Object> map);

    List<UserInfo> selectUserByOrgIdAndRoleId(Map map);

    List<UserInfo> selectOprUserSurveyInfoId(Long surveyInfoId);

    /**
     * 根据委托方  调查方 机构ID 匹配终审人员列表
     * @param map  entrustOrgId 委托方机构ID  surveyOrgId 调查方机构ID
     * @return
     */
    List<UserInfo> selectUserBySurveyOrg(Map map);

    List<UserInfoOprDTO> selectUserInfoOprHelp(Map map);

    List<UserInfoOprDTO> selectUserInfoOprSafe(Map map);

    List<UserInfoOprDTO> selectUserInfoOprData(Map map);

    List<OprUserDataItemDTO> selectUserInfoOprDataItem(Map map);

    //查询某些角色，并剔除另外的角色
    List<UserInfo> selectUserInfoByRoles(Map<String,Object> map);
}