package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.InvestigatorPreDetails;
import com.lefancrm.base.dto.ApiRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvestigatorPreDetailsMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByPreId(Long id);

    int insert(InvestigatorPreDetails record);

    int insertSelective(InvestigatorPreDetails record);

    InvestigatorPreDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvestigatorPreDetails record);

    int updateByPrimaryKey(InvestigatorPreDetails record);

    void insertBySelect(@Param("userId") Long userId,@Param("preReId") Long id,@Param("orgId") Long orgId);

    List<InvestigatorPreDetails> selectByInvestigatorReId(ApiRequest apiRequest);

    InvestigatorPreDetails selectByInvCaseId(Long invCaseId);

    List<Long> selectAllCaseByUserId(Long userId);
}