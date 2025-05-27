package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.staff.StaffPerformanceInfoDTO;
import com.lefancrm.apicenter.model.StaffPerformancePersonnel;

import java.util.List;
import java.util.Map;

public interface StaffPerformancePersonnelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffPerformancePersonnel record);

    int insertSelective(StaffPerformancePersonnel record);

    StaffPerformancePersonnel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffPerformancePersonnel record);

    int updateByPrimaryKey(StaffPerformancePersonnel record);

    List<StaffPerformancePersonnel> list(Map map);

    int listSize(Map map);

    int generate(Map<String,Object> map);
    int generateOne(Map<String,Object> map);

    StaffPerformancePersonnel personnelItem(Map map);

    List<StaffPerformanceInfoDTO> selectPerformances(Map map);

    //修改 展示状态和驳回状态
    int updateStateByInfo(Map map);

    //人事主管提交时，清空所有的原因：机构经理意见，分管总意见，总部意见（驳回时，会有意见）
    int updateOption(Long staffPerformanceId);

    //实时获取积分
    StaffPerformanceInfoDTO selectScore(Map map);

    //绩效下，所有的调查员
    List<StaffPerformancePersonnel> selectSurveyInvestigator(Long staffPerformanceId);

    //删除绩效- 同步删除明细
    int deleteByStaffPerformanceId(Long staffPerformanceId);

    //绩效完成时，生成付款管理
    int generateSurveyPayInfo(Map<String,Object> map);
    //绩效完成时，同时生成“绩效”与“付款管理”的关联关系表
    int generateSurveyPayInfoDetailNew(Map<String,Object> map);

    //查询“互助审核员A”的审核积分、审核绩效
    double selectExamineScore(Map<String,Object> map);

    //包含员工信息、调查员信息
    List<StaffPerformancePersonnel> listTwo(Map map);

    StaffPerformancePersonnel listOne(Long newId);

}