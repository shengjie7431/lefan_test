package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.dto.CaseFileMidDto;
import com.lefancrm.apicenter.dto.ShareCaseFileMidDto;
import com.lefancrm.apicenter.model.CommonFile;

import java.util.List;

public interface CommonFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommonFile record);

    int insertSelective(CommonFile record);

    CommonFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommonFile record);

    int updateByPrimaryKey(CommonFile record);

    int insertBatchFile(List<CaseFileMidDto> list);

    int insertBatchShareFile(List<ShareCaseFileMidDto> list);

    List<CommonFile> selectByFilePath(CommonFile commonFile);

    List<CommonFile> selectByIds(String ids);
}