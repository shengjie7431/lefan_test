package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseFollowInfoFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CaseFollowInfoFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseFollowInfoFile record);

    int insertSelective(CaseFollowInfoFile record);

    CaseFollowInfoFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseFollowInfoFile record);

    int updateByPrimaryKey(CaseFollowInfoFile record);

    int inserts(@Param("id") Long id, @Param("path") String[] path);

    List<CaseFollowInfoFile> selectByFollowInfoId(Long id);
}