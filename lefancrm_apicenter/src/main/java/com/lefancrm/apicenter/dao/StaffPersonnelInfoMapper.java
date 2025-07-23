package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffPersonnelInfo;

import java.util.List;
import java.util.Map;

public interface StaffPersonnelInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffPersonnelInfo record);

    int insertSelective(StaffPersonnelInfo record);

    StaffPersonnelInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffPersonnelInfo record);

    int updateByPrimaryKey(StaffPersonnelInfo record);

    //数据
    List<StaffPersonnelInfo> list(Map map);
    int listSize(Map map);

    StaffPersonnelInfo selectStaffPersonelInfoByJobNo(String jobNo);

    StaffPersonnelInfo selectStaffPersonelInfoByIdCard(String idCard);

    StaffPersonnelInfo selectByInfo(Map map);


    //离职待结算的人员 工资条完成之后 自动更新为已离职
    int updateStaffStates(Long staffPerformanceId);

    //离职待结算的人员 绩效完成之后 自动更新为已离职
    int updateStaffState(Long staffPerformanceId);

    StaffPersonnelInfo selectStaffPersonelInfoByUserId(Long userId);

    //机构经理，或者分管总名下的人员数量
    int myPersonelInfosSize(Map map);

    //拼接员工信息（使用场景：人事成本分析）
    List<StaffPersonnelInfo> selectStr();

    //条件1、离职待结算；条件2、合伙的 ，这些人员工资条完成之后 自动更新为已离职
    int updateStaffStateByPaySlip(Long staffPaySlipId);

    List<StaffPersonnelInfo> selectListByOrgId(Long id);
    // 在更新机构的绩效发放类型时将用户表中绩效发放数据一起更改
    void updateByOrganKey(Map objectObjectHashMap);

    StaffPersonnelInfo detail(String applyUserName);

}