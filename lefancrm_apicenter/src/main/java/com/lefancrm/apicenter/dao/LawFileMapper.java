package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.LawFileDto;
import com.lefancrm.apicenter.model.LawFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface LawFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LawFile record);

    int insertSelective(LawFile record);

    LawFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LawFile record);

    int updateByPrimaryKey(LawFile record);

    List<LawFileDto> selectByCaseId(Map<String, Object> paramMap);

    List<HashMap<String,Object>> selectFilesAddress(HashMap<String, Object> map);
}