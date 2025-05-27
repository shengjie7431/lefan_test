package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.report.SurveyManpowerDTO;
import com.lefancrm.apicenter.model.SurveyFranchisee;
import com.lefancrm.base.dto.ApiRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyFranchiseeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyFranchisee record);

    int insertSelective(SurveyFranchisee record);

    SurveyFranchisee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyFranchisee record);

    int updateByPrimaryKey(SurveyFranchisee record);

    //数据
    List<SurveyFranchisee> listPage(Map map);
    List<SurveyFranchisee> list(Map map);
    int listSize(Map map);
    int listPageSize(Map map);

    SurveyFranchisee selectByParentId(Long parentId);

    SurveyFranchisee selectByName(String name);

    //“平台终审人员”对应的“调查方机构”
    List<SurveyFranchisee> selectFranchiseeListForFinalUser(Map map);
    List<SurveyFranchisee> selectFranchiseeListForFinalUserNew(Map map);

    String selectChildrens(Long id);

    //是否是级联关系的机构（省级，片区）
    int relationships(Long id);
    String selectChildrens2(@Param("surveyOrgId") Long id, @Param("num") Long num);

    String selectParentOrgId(@Param("surveyOrgId") Long id);

    List<SurveyManpowerDTO> selectManPowerList(Map<String,Object> map);

    /**
     * 根据人员查询机构
     * @param userId
     * @return
     */
    SurveyFranchisee selectMechanism(Long userId);

    List<SurveyFranchisee> selectOrgAllPre(ApiRequest apiRequest);

    List<SurveyFranchisee> selectSurveyFranchisee(Map map);

    Long selectId(Long orgId);

    /**
     * 查询调查机构信息。包含调查中 初审中数据。
     * @param paramMap
     * @return
     */
    List<SurveyFranchisee> selectFranchiseeAndDataByParam(Map<String,Object> paramMap);
}