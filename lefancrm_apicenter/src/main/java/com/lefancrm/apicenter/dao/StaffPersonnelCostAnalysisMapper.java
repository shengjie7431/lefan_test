package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.staff.StaffPersonnelCostAnalysisDTO;

import java.util.List;
import java.util.Map;

public interface StaffPersonnelCostAnalysisMapper {

    //数据
    List<StaffPersonnelCostAnalysisDTO> list(Map map);
    List<StaffPersonnelCostAnalysisDTO> userList(Map map);

    StaffPersonnelCostAnalysisDTO selectPersonnelInfo(Map map);
}