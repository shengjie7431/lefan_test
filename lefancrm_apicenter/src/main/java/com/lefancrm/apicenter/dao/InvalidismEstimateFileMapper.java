package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.InvalidismEstimateFile;

import java.util.List;

public interface InvalidismEstimateFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvalidismEstimateFile record);

    int insertSelective(InvalidismEstimateFile record);

    InvalidismEstimateFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvalidismEstimateFile record);

    int updateByPrimaryKey(InvalidismEstimateFile record);

    List<InvalidismEstimateFile> queryFileByInvalidismEstimateId(Long invalidismEstimateId);
}