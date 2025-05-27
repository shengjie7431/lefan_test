package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.think.ThinkBillAndAccDTO;
import com.lefancrm.apicenter.dto.think.ThinkProBusDataDTO;
import com.lefancrm.apicenter.dto.think.ThinkProDataDTO;
import com.lefancrm.apicenter.model.SurveyBusinessReport;

import java.util.List;
import java.util.Map;

public interface SurveyBusinessReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyBusinessReport record);

    int insertSelective(SurveyBusinessReport record);

    SurveyBusinessReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyBusinessReport record);

    int updateByPrimaryKey(SurveyBusinessReport record);

    List<SurveyBusinessReport> selectAll(Map map);

    List<SurveyBusinessReport> selectAllNew(Map map);


    List<Double> selectThinkOprMoney(Map paramMap);

    List<ThinkProBusDataDTO> selectThinkOprMoneyOrg(Map paramMap);

    List<ThinkBillAndAccDTO> selectThinkBillAndAcc(Map paramMap);

    List<ThinkBillAndAccDTO> selectThinkBillAndAccOrg(Map paramMap);

    List<ThinkProDataDTO> selectThinkInOut(Map paramMap);

    //互助+保司的开票到账
    List<ThinkBillAndAccDTO> selectThinkCaseBill(Map paramMap);
    List<ThinkBillAndAccDTO> selectThinkCaseAcc(Map paramMap);
    //其他产品的开票到账
    List<ThinkBillAndAccDTO> selectThinkBill(Map paramMap);
    List<ThinkBillAndAccDTO> selectThinkAcc(Map paramMap);

}