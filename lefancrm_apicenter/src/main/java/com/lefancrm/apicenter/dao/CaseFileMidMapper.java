package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.CaseFileMidDto;
import com.lefancrm.apicenter.model.CaseFileMid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CaseFileMidMapper {
    int insert(CaseFileMid record);

    int insertSelective(CaseFileMid record);


    List<HashMap<String,Object>> selectFileMenuList();

    List<HashMap<String,Object>> selectFilesAddress(HashMap<String, Object> map);

    int deleteFile(Long id);

    int insertBatchCaseFileMid(List<CaseFileMidDto> list);
	List<CaseFileMid> selectByCaseIdAndCatalogId(Map<String, Object> paramMap);

    int deleteByFileId(Long fileId);

    CaseFileMid selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseFileMid caseFileMid);
}