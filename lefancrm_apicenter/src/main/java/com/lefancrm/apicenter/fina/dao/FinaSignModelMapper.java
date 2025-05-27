package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSignModel;
import com.lefancrm.base.dto.ApiRequest;

import java.util.HashMap;
import java.util.List;

public interface FinaSignModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSignModel record);

    int insertSelective(FinaSignModel record);

    FinaSignModel selectByPrimaryKey(Long id);

    List<FinaSignModel> selectByParam(HashMap<String,Object> paramMap);
    int selectByParamCount(HashMap<String,Object> paramMap);

    int updateByPrimaryKeySelective(FinaSignModel record);

    int updateByPrimaryKey(FinaSignModel record);
}