package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.staff.StaffPayInfoDTO;
import com.lefancrm.apicenter.model.StaffPayPersonnelSlip;

import java.util.List;
import java.util.Map;

public interface StaffPayPersonnelSlipMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffPayPersonnelSlip record);

    int insertSelective(StaffPayPersonnelSlip record);

    StaffPayPersonnelSlip selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffPayPersonnelSlip record);

    int updateByPrimaryKey(StaffPayPersonnelSlip record);

    List<StaffPayPersonnelSlip> list(Map map);

    int listSize(Map map);

    List<StaffPayPersonnelSlip> slips(Map map);

    StaffPayPersonnelSlip slipItem(Map map);

    int updateDeleteFlagByStaffPaySlipId(Long staffPaySlipId);

    int generate(Map<String,Object> map);

    //修改 展示状态和驳回状态
    int updateStateByInfo(Map map);

    //所有的数据，不判断退回与否
    List<StaffPayPersonnelSlip> allList(Map map);

    //人事主管提交时，清空所有的原因：机构经理意见，分管总意见，总部意见（驳回时，会有意见）
    int updateOption(Long staffPaySlipId);


    int updCostSettel(Map map);

    //删除工资条，同步删除明细
    int deleteByStaffPaySlipId(Long staffPaySlipId);

    //工资条完成后，生成付款管理
    int generateSurveyPayInfo(Map<String,Object> map);
    //工资条完成后，同时生成“工资条”与“付款管理”的关联关系表
    int generateSurveyPayInfoDetailNew(Map<String,Object> map);
}