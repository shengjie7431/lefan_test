package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.UserPoLevelDto;
import com.lefancrm.apicenter.model.UserPoLevel;

import java.util.List;
import java.util.Map;

public interface UserPoLevelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserPoLevel record);

    int insertSelective(UserPoLevel record);

    UserPoLevel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPoLevel record);

    int updateByPrimaryKey(UserPoLevel record);

    UserPoLevel queryByUserId(Long userId);

    List<UserPoLevel> queryByOrgAndLevelId(Map<String,Object> paramMap);

    /**
     * 查询机构佣金总金额
     * @param paramMap
     * @return
     */
    Double queryCommissionMoneyNum(Map<String,Object> paramMap);

    /**
     * 查询机构除去F0CC的人数
     * @param orgId
     * @return
     */
    Integer queryCommissionMoneyCount(Long orgId);

    /**
     * 查询机构管理职级
     * @param orgId
     * @return
     */
    List<UserPoLevel> queryManagerComrateLevel(Long orgId);

    //获取数据总值，包含条件查询后的结果
    int selectCountUserPoLevel(Map<String, Object> paramMap);

    //列表list查询，包含条件查询
    List<UserPoLevelDto> selectUserPoLevelList(Map<String, Object> paramMap);

    //根据ID获取数据，返回DTO中（背景：获取userName、positionName、positionLevelName）
    UserPoLevelDto selectDtoByPrimaryKey(Long id);

    //查询UserPoLevelDto.LevelId=id的list
    List<UserPoLevel> searchUserPoLevelByLevelId(Long id);

    //查询UserPoLevelDto.positionId=id的list
    List<UserPoLevel> searchUserPoLevelByPositionId(Long id);
}