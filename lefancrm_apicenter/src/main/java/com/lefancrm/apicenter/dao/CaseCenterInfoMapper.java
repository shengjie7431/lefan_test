package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.CaseCenterInfoDto;
import com.lefancrm.apicenter.dto.PaymentEstimateInquiryDto;
import com.lefancrm.apicenter.model.BillingApply;
import com.lefancrm.apicenter.model.CaseCenterInfo;
import com.lefancrm.base.dto.ApiRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixianfeng on 2018/2/26.
 */
public interface CaseCenterInfoMapper {

    int insertSelective(CaseCenterInfo record);

    List<CaseCenterInfoDto> findListByInfo(Map map);//无分页
    List<CaseCenterInfoDto> findList(ApiRequest request);//有分页
    int findListSize(ApiRequest request);
    CaseCenterInfo selectByPrimaryKey(Long id);
    int updateByPrimaryKey(CaseCenterInfo record);

    /**
     * 查询最新报价信息    case_id 和 type
     * @param map
     * @return
     */
    PaymentEstimateInquiryDto selectNewestPayInquiry1(Map map);
    PaymentEstimateInquiryDto selectNewestPayInquiryNew(Long caseId);

    /******** old ******/
    /**
     * 查询条件下的数据总条数
     * @param map
     * @return
     */
    int selectCountCaseCenterInfoByParam(Map<String,Object> map);
    /**
     * 分页查询案件中心的数据
     * @param map
     * @return
     */
    List<CaseCenterInfo> selectCaseCenterInfoByParam(Map<String,Object> map);

    int updateByPrimaryKeySelective(CaseCenterInfo record);
    int queryCaseNegotiateStateCount(ApiRequest request);
    List<CaseCenterInfo> queryCaseNegotiateState(ApiRequest request);

    List<CaseCenterInfo> searchMonthNewSignList(ApiRequest request);

    /**
     * 给机构人员分配案件
     * @param map
     * @return
     */
    int allotCaseManager(HashMap<String,Object> map);

    /**
     * 查询当前案件人是否有过案件记录
     * @param paramMap
     * @return
     */
    int selectUserNameCaseCount(Map<String, Object> paramMap);

    /**
     * 根据用户ID查询推广人是否为加盟机构
     * @param userId
     * @return
     */
    HashMap<String,Object> selectByOrgIdAndOrgName(Integer userId);

    HashMap<String,Object> selectByApplyInfoOrgIdAndOrgNameByCaseId(Long id);

    /**
     * 根据caseId和caseNo 查询数据
     * @param
     * @return
     */
    BillingApply searchBillApplyByCase(Map<String,Object> map);

    /**
     * 还款清单列表
     * @param
     * @return
     */
    List<CaseCenterInfo> selectCaseCenterInfoForRepay(ApiRequest request);
    int selectCaseCenterInfoForRepaySize(ApiRequest request);
    /**
     * 还款确认列表
     * @param
     * @return
     */
    List<CaseCenterInfo> selectConfirmRepayCaseCenterInfo(ApiRequest request);
    int selectConfirmRepayCaseCenterInfoSize(ApiRequest request);

    HashMap<String,Object> selectOrgIdAndOrgNameByCaseId(HashMap<String,Object> map);

    /**
     * 超时未接收列表
     * @param
     * @return
     */
    List<CaseCenterInfoDto> selectCaseCenterInfoForOverCaseState(ApiRequest request);
    int selectCountCaseCenterInfoForOverCaseState(ApiRequest request);

    /**
     * 未跟踪案件
     * @param
     * @return
     */
    List<CaseCenterInfoDto> selectCaseCenterInfoForOverFollow(ApiRequest request);
    int selectCountCaseCenterInfoForOverFollow(ApiRequest request);

    List<CaseCenterInfoDto> selectFour(ApiRequest request);
    int selectCountFour(ApiRequest request);
}
