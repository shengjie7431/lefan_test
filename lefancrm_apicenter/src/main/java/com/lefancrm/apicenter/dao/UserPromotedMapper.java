package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.UserPromoted;
/*import com.lefancrm.apicenter.model.WorkInfo;*/

import java.util.List;
import java.util.Map;

public interface UserPromotedMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserPromoted record);

    int insertSelective(UserPromoted record);

    UserPromoted selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPromoted record);

    int updateByPrimaryKey(UserPromoted record);

    UserPromoted selectUserPromotedByUserId(Map<String, Object> paramMap);
    UserPromoted selectUserPromotedByPhone(Map<String, Object> paramMap);
    List<UserPromoted> selectUserPromotedByParam(Map<String, Object> paramMap);

    List<UserPromoted> selectMyPromotedTeam(Map<String, Object> paramMap);

    int selectMyPromotedTeamCount(Map<String, Object> paramMap);

    int selectUserPromotedByParamCount(Map<String, Object> paramMap);

    int updateUserPromotedRegion(Map<String, Object> paramMap);

   /* List<WorkInfo> selectUserPromotedByState(Map<String, Object> paramMap);*/

    int selectPromotedWorkCount(Map<String, Object> paramMap);
}