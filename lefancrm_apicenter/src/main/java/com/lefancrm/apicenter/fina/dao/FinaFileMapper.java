package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaFile;

import java.util.List;
import java.util.Map;

public interface FinaFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaFile record);

    int insertSelective(FinaFile record);

    FinaFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaFile record);

    int updateByPrimaryKey(FinaFile record);

    List<FinaFile> list(Map<String,Object> paramMap);
}