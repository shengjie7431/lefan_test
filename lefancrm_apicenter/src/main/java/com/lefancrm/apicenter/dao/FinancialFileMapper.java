package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.FinancialFile;

import java.util.List;
import java.util.Map;

public interface FinancialFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinancialFile record);

    int insertSelective(FinancialFile record);

    FinancialFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinancialFile record);

    int updateByPrimaryKey(FinancialFile record);

    //数据
    List<FinancialFile> list(Map map);
}