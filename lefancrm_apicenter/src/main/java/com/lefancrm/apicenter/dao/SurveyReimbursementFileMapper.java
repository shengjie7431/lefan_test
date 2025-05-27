package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyReimbursementFileDto;
import com.lefancrm.apicenter.model.SurveyReimbursementFile;

import java.util.List;

public interface SurveyReimbursementFileMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByReimId(Long id);

    int insert(SurveyReimbursementFile record);

    int insertSelective(SurveyReimbursementFile record);

    SurveyReimbursementFile selectByPrimaryKey(Long id);

    List<SurveyReimbursementFileDto> selectByReimbursementId(Long reimbursementId);

    List<SurveyReimbursementFileDto> selectBillNewByReimbursementId(Long reimbursementId);

    int updateByPrimaryKeySelective(SurveyReimbursementFile record);

    int updateByPrimaryKey(SurveyReimbursementFile record);
}