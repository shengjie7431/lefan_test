package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.think.DetailDTO;
import com.lefancrm.apicenter.dto.think.YWDTO;
import com.lefancrm.apicenter.dto.think.SRDTO;
import com.lefancrm.apicenter.model.StaffOrganProduct;

import java.util.List;
import java.util.Map;

public interface StaffOrganProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffOrganProduct record);

    int insertSelective(StaffOrganProduct record);

    StaffOrganProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffOrganProduct record);

    int updateByPrimaryKey(StaffOrganProduct record);

    List<StaffOrganProduct> list(Map<String,Object> paramMap);


    List<YWDTO> ywData(Map paramMap);
    List<SRDTO> srBillData(Map paramMap);//开票收入
    List<SRDTO> srAccData(Map paramMap);//到账收入
    List<DetailDTO> zc1Data(Map paramMap);//支出(工资、绩效、打卡、渠道费)
    List<DetailDTO> zc1CompanyData(Map paramMap);//公司--支出(工资、绩效、打卡、渠道费)

    List<DetailDTO> zc2Data(Map paramMap);//支出(报销)
    List<DetailDTO> zc2CompanyData(Map paramMap);//公司--支出(报销)

    StaffOrganProduct  selectOrganProByParam(Map paramMap);//机构和产品ID查询关联信息，更新机构产品
    List<StaffOrganProduct> listByParam(Map paramMap);


    int deleteByParam(Map paramMap);



}