package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.PromotionOutlayDto;
import com.lefancrm.apicenter.model.PromotionOutlay;

import java.util.List;
import java.util.Map;

public interface PromotionOutlayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PromotionOutlay record);

    int insertSelective(PromotionOutlay record);

    PromotionOutlay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PromotionOutlay record);

    int updateByPrimaryKey(PromotionOutlay record);

    List<PromotionOutlay> selectByCaseNo(String caseNo);

    /**
     * 根据案件编号查询案件的总费用
     * @param caseNo
     * @return
     */
    Double selectSumMoneyByCaseNo(String caseNo);

    List<PromotionOutlayDto> selectPromotionOutlayList(Map<String, Object> paramMap);

    int selectCountPromotionOutlay(Map<String, Object> paramMap);
}