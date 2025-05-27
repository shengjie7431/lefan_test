package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyZhaOrgAssessUpdateDTO;
import com.lefancrm.apicenter.dto.zhaAssess.ZhaAssessDTO;
import com.lefancrm.apicenter.dto.zhaAssess.ZhaOrgDTO;
import com.lefancrm.apicenter.model.SurveyZhaOrgAssess;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyZhaOrgAssessMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyZhaOrgAssess record);

    int insertSelective(SurveyZhaOrgAssess record);

    SurveyZhaOrgAssess selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyZhaOrgAssess record);

    int updateByPrimaryKey(SurveyZhaOrgAssess record);

    int insertCreate(Map<String,Object> paramMap);

    /**
     * 未匹配到的数据
     * @param zhaAssessId
     * @return
     */
    List<SurveyZhaOrgAssess> selectNotFindCases(Long zhaAssessId);

    /**
     * 匹配到的数据
     * @param zhaAssessId
     * @return
     */
    List<SurveyZhaOrgAssess> selectFindCases(Long zhaAssessId);

    int delNotFindCases(String ids);

    List<ZhaAssessDTO> selectZhaAssessDTOs(Long zhaAssessId);


    List<ZhaOrgDTO> selectZhaOrgs(Long zhaAssessId);

    List<SurveyZhaOrgAssess> select(Map<String,Object> paramMap);


    int updateData(@Param("data") List<SurveyZhaOrgAssessUpdateDTO> data);

}