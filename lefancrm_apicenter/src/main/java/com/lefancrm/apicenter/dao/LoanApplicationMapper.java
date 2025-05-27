package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.LoanApplication;

import java.util.Map;

public interface LoanApplicationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LoanApplication record);

    int insertSelective(LoanApplication record);

    LoanApplication selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoanApplication record);

    int updateByPrimaryKey(LoanApplication record);

    LoanApplication searchLoanApplicationByLoanNo(Map<String, Object> paramMap);

    LoanApplication selectLoanApplicationByParamTo(Map<String, Object> paramMap);
}