package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.BillingApplyDto;
import com.lefancrm.apicenter.dto.BillingApplyImgsMaterialDto;
import com.lefancrm.apicenter.dto.CommonEnumDto;
import com.lefancrm.apicenter.model.BillingApply;
import com.lefancrm.base.dto.ApiRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BillingApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillingApply record);

    int insertSelective(BillingApply record);

    BillingApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillingApply record);

    int updateByPrimaryKey(BillingApply record);

    List<BillingApply> selectList(ApiRequest request);

    int selectListSize(ApiRequest request);

    List<BillingApplyDto> selectByMap(ApiRequest request);

    List<BillingApplyDto> selectByList(ApiRequest request);

    BillingApply selectByInfo(Map<String, Object> map);

    List<BillingApply> selectAll();

    List<BillingApplyImgsMaterialDto> selectImgsInfoForExport(ApiRequest request);
    List<BillingApplyImgsMaterialDto> selectMaterialInfoForExport(ApiRequest request);

    List<BillingApply> selectListForMerge(ApiRequest request);

    int updateAccountState(Map<String, Object> map);

    List<BillingApply> selectListForExport(ApiRequest request);
    List<BillingApplyDto> selectListForExportGonggu(ApiRequest request);
    int selectListForExportGongguCount(ApiRequest request);

    Double selectBillMoneyByCaseId(Long caseId);

    BillingApply selectMoneyById(Long id);

    List<CommonEnumDto> selectEnumMoneyList(Map<String,Object> paramMap);

    List<CommonEnumDto> selectTest(Map<String,Object> paramMap);
}