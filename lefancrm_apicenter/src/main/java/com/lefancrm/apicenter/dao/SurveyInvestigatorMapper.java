package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyInvestigatorDto;
import com.lefancrm.apicenter.dto.hzReport.AssessmentIndexDto;
import com.lefancrm.apicenter.dto.report.SurveyManPowerItemDTO;
import com.lefancrm.apicenter.model.SurveyInvestigator;
import com.lefancrm.base.dto.ApiRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 调查员认证表
 * @author EDZ
 */
public interface SurveyInvestigatorMapper {

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);


    SurveyInvestigator selectByUser(Map map);

    /**
     * 根据实体类新增数据
     * @param record
     * @return
     */
    int insert(SurveyInvestigator record);

    /**
     * 根据实体类新增数据
     * @param record
     * @return
     */
    int insertSelective(SurveyInvestigator record);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    SurveyInvestigator selectByPrimaryKey(Long id);

    /**
     * 根据实体类修改数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SurveyInvestigator record);

    /**
     * 根据实体类修改数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(SurveyInvestigator record);

    /**
     * 数据
     * @param map
     * @return
     */
    List<SurveyInvestigator> list(Map map);

    /**
     * 根据条件查询总条数
     * @param map
     * @return
     */
    int listSize(Map map);

    /**
     * 根据id查询数据
     * @param userId
     * @return
     */
    SurveyInvestigator selectByUserId(Long userId);

    /**
     * 修改所有名下调查员的类型（调查员类型（1：自营，2：加盟））
     * @param map
     * @return
     */
    int updateTypeByFranchiseeId(Map map);

    /**
     * 获取“调查员角色-50”的surveyInvestigator
     * @param map
     * @return
     */
    List<SurveyInvestigator> selectInfoByRole(Map map);
    List<SurveyInvestigatorDto> selectNumberIndividual(Map map);

    /**
     * 根据id查询连表数据
     * @param orgId
     * @return
     */
    List<SurveyInvestigator> selectInfoAndCaseNum(Long orgId);

    /**
     * 查询机构下面所有调查员
     * @param orgId
     * @return
     */
    List<SurveyInvestigator> selectOrgAllSurveyInv(Long orgId);

    List<SurveyInvestigator> selectOrgAllSurveyInvPre(ApiRequest apiRequest);

    List<Long> selectOrgIdByUserIdStr(String userIdStr);

    List<SurveyManPowerItemDTO> selectManPowerData(Map<String,Object> paramMap);

    List<SurveyManPowerItemDTO> selectManPowerDataBs(Map<String,Object> paramMap);

    List<AssessmentIndexDto> selectAssePersonData(Map paramMap);

    List<SurveyInvestigator> selectOrgUser(Map paramMap);

    /**
     * 查询所有的新人
     * @param map
     * @return
     */
    List<SurveyInvestigator> selectRookieList(Map map);

    /**
     * 创建带教清单
     * @param map
     * @return
     */
    List<SurveyInvestigator> createTeachingList(Map map);

    /**
     * 带教清单详情
     * @param map
     * @return
     */
    List<SurveyInvestigator> detailsTeachingList(Map map);

    /**
     * 统计带教老师人数
     * @param map
     * @return
     */
    Integer selectTeacherUserCount(Map map);
    Integer selectTeacherCount(Long rewardListId);
    /**
     * 修改符合条件的数据
     * @param ids
     * @return
     */
    Integer updateIsSettlement(@Param("ids") String ids);

    /**
     * 根据指定的id获取实际带教奖励的总和
     * @param rewardListId
     * @return
     */
    Double selectSumTeacherRealReward(Long rewardListId);

    List<SurveyInvestigator> selectByMap(Map map);

    List<SurveyInvestigator> selectByMapJurisdiction(Map map);

    List<SurveyInvestigator> selectBusinessRole(Map map);

    Integer updateSurveyAreaId(Map map);
}