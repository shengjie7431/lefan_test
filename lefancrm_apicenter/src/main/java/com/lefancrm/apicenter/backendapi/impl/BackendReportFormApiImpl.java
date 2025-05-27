package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendReportFormApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.apicenter.util.DecimalUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.constants.Constant;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangwei on 2018/8/24.
 * 报表数据管理
 */
@Service
@ApiService(descript = "报表数据管理API")
public class BackendReportFormApiImpl extends BaseServiceImpl implements BackendReportFormApi {

    @Autowired
    private CaseClaimReportMapper caseClaimReportMapper;
    @Autowired
    private CaseLoanReportMapper caseLoanReportMapper;
    @Autowired
    private CasePersonalInfoMapper casePersonalInfoMapper;
    @Autowired
    private CasePersonalDayReportMapper casePersonalDayReportMapper;
    @Autowired
    private CasePersonalMonthReportMapper casePersonalMonthReportMapper;
    @Autowired
    private CaseAssessmentkpdzMonthReportMapper caseAssessmentkpdzMonthReportMapper;
    @Autowired
    private CasePerkpdzMonthReportMapper casePerkpdzMonthReportMapper;
    @Autowired
    private CasePersonalCwInfoMapper casePersonalCwInfoMapper;
    /**
     * 索赔报表
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "索赔报表", value = "backend-case-claim-report-list", apiParams = { })
    @Override
    public ApiResponse getCaseClaimReportList(ApiRequest apiReq) {
        apiReq.put(Constant.PAGE_SIZE,30);
        this.setBackendPageSize(apiReq);
//        String date = apiReq.getString("date");
//        if(date == null || "".equals(date)){
//            apiReq.put("today",1);
//        }
        String startDate = apiReq.getString("startDate");
        String endDate = apiReq.getString("endDate");
        if((startDate == null || "".equals(startDate)) && (endDate == null || "".equals(endDate))){
            apiReq.put("today",1);
        }
        //获取数据总值，包含条件查询
        int count = caseClaimReportMapper.selectCountList(apiReq);
        //列表查询，包含条件查询
        List<CaseClaimReport> list = caseClaimReportMapper.selectList(apiReq);
        return new ApiResponse<List<CaseClaimReport>>(ApiMsgEnum.SUCCESS, count, list);

    }

    /**
     * 贷款报表
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "贷款报表", value = "backend-case-loan-report-list", apiParams = { })
    @Override
    public ApiResponse getCaseLoanReportList(ApiRequest apiReq) {
        apiReq.put(Constant.PAGE_SIZE,30);
        this.setBackendPageSize(apiReq);
        String date = apiReq.getString("date");
//        if(date == null || "".equals(date)){
//            apiReq.put("today",1);
//        }
        String startDate = apiReq.getString("startDate");
        String endDate = apiReq.getString("endDate");
        if((startDate == null || "".equals(startDate)) && (endDate == null || "".equals(endDate))){
            apiReq.put("today",1);
        }
        //获取数据总值，包含条件查询
        int count = caseLoanReportMapper.selectCountList(apiReq);
        //列表查询，包含条件查询
        List<CaseLoanReport> list = caseLoanReportMapper.selectList(apiReq);
        return new ApiResponse<List<CaseLoanReport>>(ApiMsgEnum.SUCCESS, count, list);

    }


    /**
     * 个人业务台账
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "个人业务台账", value = "backend-case-personal-info-list", apiParams = { })
    @Override
    public ApiResponse getCasePersonalInfoList(ApiRequest apiReq) {
        apiReq.put(Constant.PAGE_SIZE,15);
        this.setBackendPageSize(apiReq);
        //获取数据总值，包含条件查询
        int count = casePersonalInfoMapper.selectCountList(apiReq);
        //列表查询，包含条件查询
        List<CasePersonalInfo> list = casePersonalInfoMapper.selectList(apiReq);
        return new ApiResponse<List<CasePersonalInfo>>(ApiMsgEnum.SUCCESS, count, list);

    }

    /**
     * 根据“id”查询个人业务台账详情页面
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据“id”查询个人业务台账详情页面" ,value = "backend-case-personal-info-by-id")
    @Override
    public ApiResponse getCasePersonalInfoView(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        CasePersonalInfo casePersonalInfo = casePersonalInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
        return  new ApiResponse(ApiMsgEnum.SUCCESS,1,casePersonalInfo);
    }


    /**
     * 公估开票到账月报表
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "公估开票到账月报表", value = "backend-case-assessmentkpdz-month-report-list", apiParams = { })
    @Override
    public ApiResponse getCaseAssessmentkpdzMonthReportList(ApiRequest apiReq) {
        apiReq.put(Constant.PAGE_SIZE,30);
        this.setBackendPageSize(apiReq);

        String date = apiReq.getString("date");
        if(date==null || "".equals(date)){
            apiReq.put("month",1);
        }
        int count =caseAssessmentkpdzMonthReportMapper.selectCountList(apiReq);
        List<CaseAssessmentkpdzMonthReportDto> list = caseAssessmentkpdzMonthReportMapper.selectList(apiReq);

        //计算-公估开票到账 总计数据
        CaseAssessmentkpdzMonthReportDto titleDto = assessmentkpdzTitle(list);
        String export = apiReq.getString("export");
        Map<String,Object> map = new HashMap<>();
        if(export !=null && "1".equals(export)){
            list.add(titleDto);
            return new ApiResponse<List<CaseAssessmentkpdzMonthReportDto>>(ApiMsgEnum.SUCCESS, count, list);
        }else{
            List<CaseAssessmentkpdzMonthReportDto> titleList = new ArrayList<>();
            titleList.add(titleDto);
            map.put("titleList",titleList);
            map.put("list",list);
            return new ApiResponse<Map<String,Object>>(ApiMsgEnum.SUCCESS, count, map);
        }
    }

    /**
     * 个人业务开票到账月报表
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "个人业务开票到账月报表", value = "backend-case-perkpdz-month-report-list", apiParams = { })
    @Override
    public ApiResponse getCasePerkpdzMonthReportList(ApiRequest apiReq) {
        apiReq.put(Constant.PAGE_SIZE,30);
        this.setBackendPageSize(apiReq);
        String date = apiReq.getString("date");
        if(date==null || "".equals(date)){
            apiReq.put("month",1);
        }
        int count =casePerkpdzMonthReportMapper.selectCountList(apiReq);
        List<CasePerkpdzMonthReportDto> list = casePerkpdzMonthReportMapper.selectList(apiReq);

        //计算"总计数据"
        CasePerkpdzMonthReportDto titleDto = perkpdzTitle(list);
        String export = apiReq.getString("export");
        Map<String,Object> map = new HashMap<>();
        if(export !=null && "1".equals(export)){
            list.add(titleDto);
            return new ApiResponse<List<CasePerkpdzMonthReportDto>>(ApiMsgEnum.SUCCESS, count, list);
        }else{
            List<CasePerkpdzMonthReportDto> titleList = new ArrayList<>();
            titleList.add(titleDto);
            map.put("titleList",titleList);
            map.put("list",list);
            return new ApiResponse<Map<String,Object>>(ApiMsgEnum.SUCCESS, count, map);
        }

    }

    /**
     * 个人业务案件财务台账
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "个人业务案件财务台账", value = "backend-case-personal-cw-info-list", apiParams = { })
    @Override
    public ApiResponse getCasePersonalCwInfoList(ApiRequest apiReq) {
        apiReq.put(Constant.PAGE_SIZE,15);
        this.setBackendPageSize(apiReq);
        //获取数据总值，包含条件查询
        int count = casePersonalCwInfoMapper.selectCountList(apiReq);
        //列表查询，包含条件查询
        List<CasePersonalCwInfoDto> list = casePersonalCwInfoMapper.selectList(apiReq);
        return new ApiResponse<List<CasePersonalCwInfoDto>>(ApiMsgEnum.SUCCESS, count, list);

    }

    /**
     * 根据“id”查询个人业务案件财务台账详情页面
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据“id”查询个人业务案件财务台账页面详情" ,value = "backend-case-personal-cw-info-by-id")
    @Override
    public ApiResponse getCasePersonalCwInfoView(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        CasePersonalCwInfoDto casePersonalCwInfo = casePersonalCwInfoMapper.selectById(apiReq.getLong("id"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,casePersonalCwInfo);
    }


    /**
     * 个人业务案件日报表
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "个人业务案件报表(日、月)", value = "backend-case-personal-report-list", apiParams = { })
    @Override
    public ApiResponse getCasePersonalReportList(ApiRequest apiReq) {
        apiReq.put(Constant.PAGE_SIZE,15);
        this.setBackendPageSize(apiReq);
        String type = apiReq.getString("type");

        //日报表
        if("1".equals(type) || "".equals(type) || type == null){
            //日报表标志
            apiReq.put("timeType",1);
            //获取当天数据
            String startDate = apiReq.getString("startDate");
            String endDate = apiReq.getString("endDate");
            if((startDate == null || "".equals(startDate)) && (endDate == null || "".equals(endDate))){
                apiReq.put("today",1);
                apiReq.put("endDate",(new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
            }
        }
        //月报表
        if("2".equals(type)){
            //月报表标志
            apiReq.put("timeType",30);//在sql中区分是否输入了月份month
            String month = apiReq.getString("month");
            //截取年
            String year = month.substring(0,4);
            //截取月
            String newMonth = month.substring(month.length()-2);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.valueOf(year));
            calendar.set(Calendar.MONTH, Integer.valueOf(newMonth)-1);

            int day = calendar.getActualMaximum(Calendar.DATE);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            String lastDayOfMonth = (new SimpleDateFormat("yyyy-MM-dd")).format(calendar.getTime());

            if (calendar.getTime().before(new Date())){
                apiReq.put("endDate",lastDayOfMonth);
            }else{
                apiReq.put("endDate",(new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
            }

        }
        int count = casePersonalDayReportMapper.selectCountList(apiReq);
        List<CasePersonalDayReport> list = casePersonalDayReportMapper.selectList(apiReq);

        String export = apiReq.getString("export");
        if(export !=null && "1".equals(export)){
            //包含“主管合计”、“机构合计”、“总计”
//            CasePersonalDayReportListDto directorListDtos = personalDayDirectorTitle(list, apiReq);
            List<CasePersonalDayReportDto> directorListDtos = personalDayDirectorTitle(list, apiReq);
            return new ApiResponse<List<CasePersonalDayReportDto>>(ApiMsgEnum.SUCCESS, 1, directorListDtos);
        }else{
            CasePersonalDayReportListDto orgListDtos = personalDayOrgTitle(list, count, apiReq);
            return new ApiResponse<CasePersonalDayReportListDto>(ApiMsgEnum.SUCCESS, orgListDtos.getCount(), orgListDtos);
        }
    }

    //计算-公估开票到账 (包含“总计”数据)
    private CaseAssessmentkpdzMonthReportDto assessmentkpdzTitle(List<CaseAssessmentkpdzMonthReportDto> list) {
        CaseAssessmentkpdzMonthReportDto caseAssessmentkpdzMonthReportSum = new CaseAssessmentkpdzMonthReportDto();
        double qctInvoiceMoneySum = 0D;
        double fxdcInvoiceMoneySum = 0D;
        double jjInvoiceMoneySum = 0D;
        double fyInvoiceMoneySum = 0D;
        double ccxInvoiceMoneySum = 0D;
        double ggpgInvoiceMoneySum = 0D;
        double otherInvoiceMoneySum = 0D;
        double pbInvoiceMoneySum = 0D;
        double invoiceTitle =0D;

        double qctReceivedMoneySum = 0D;
        double fxdcReceivedMoneySum = 0D;
        double jjReceivedMoneySum = 0D;
        double fyReceivedMoneySum = 0D;
        double ccxReceivedMoneySum = 0D;
        double ggpgReceivedMoneySum = 0D;
        double otherReceivedMoneySum = 0D;
        double pbReceivedMoneySum = 0D;
        double receivedTitle =0D;

        double qctInvalidMoneySum =0D;
        double fxdcInvalidMoneySum =0D;
        double jjInvalidMoneySum =0D;
        double fyInvalidMoneySum =0D;
        double ccxInvalidMoneySum =0D;
        double ggpgInvalidMoneySum =0D;
        double otherInvalidMoneySum =0D;
        double pbInvalidMoneySum =0D;
        double invalidTitleSum =0D; //作废金额合计

        double qctRedrushMoneySum =0D;
        double fxdcRedrushMoneySum =0D;
        double jjRedrushMoneySum =0D;
        double fyRedrushMoneySum =0D;
        double ccxRedrushMoneySum =0D;
        double ggpgRedrushMoneySum =0D;
        double otherRedrushMoneySum =0D;
        double pbRedrushMoneySum =0D;
        double redrushTitleSum = 0D; //红冲金额合计

        for (int i = 0; i < list.size(); i++) {
            qctInvoiceMoneySum += list.get(i).getQctInvoiceMoney()==null?0D:list.get(i).getQctInvoiceMoney();
            fxdcInvoiceMoneySum += list.get(i).getFxdcInvoiceMoney()==null?0D:list.get(i).getFxdcInvoiceMoney();
            jjInvoiceMoneySum += list.get(i).getJjInvoiceMoney()==null?0D:list.get(i).getJjInvoiceMoney();
            fyInvoiceMoneySum += list.get(i).getFyInvoiceMoney()==null?0D:list.get(i).getFyInvoiceMoney();
            ccxInvoiceMoneySum += list.get(i).getCcxInvoiceMoney()==null?0D:list.get(i).getCcxInvoiceMoney();
            ggpgInvoiceMoneySum += list.get(i).getGgpgInvoiceMoney()==null?0D:list.get(i).getGgpgInvoiceMoney();
            otherInvoiceMoneySum += list.get(i).getOtherInvoiceMoney()==null?0D:list.get(i).getOtherInvoiceMoney();
            pbInvoiceMoneySum += list.get(i).getPbInvoiceMoney()==null?0D:list.get(i).getPbInvoiceMoney();
            invoiceTitle += list.get(i).getInvoiceTitle()==null?0D:list.get(i).getInvoiceTitle();

            qctReceivedMoneySum += list.get(i).getQctReceivedMoney()==null?0D:list.get(i).getQctReceivedMoney();
            fxdcReceivedMoneySum += list.get(i).getFxdcReceivedMoney()==null?0D:list.get(i).getFxdcReceivedMoney();
            jjReceivedMoneySum += list.get(i).getJjReceivedMoney()==null?0D:list.get(i).getJjReceivedMoney();
            fyReceivedMoneySum += list.get(i).getFyReceivedMoney()==null?0D:list.get(i).getFyReceivedMoney();
            ccxReceivedMoneySum += list.get(i).getCcxReceivedMoney()==null?0D:list.get(i).getCcxReceivedMoney();
            ggpgReceivedMoneySum += list.get(i).getGgpgReceivedMoney()==null?0D:list.get(i).getGgpgReceivedMoney();
            otherReceivedMoneySum += list.get(i).getOtherReceivedMoney()==null?0D:list.get(i).getOtherReceivedMoney();
            pbReceivedMoneySum += list.get(i).getPbReceivedMoney()==null?0D:list.get(i).getPbReceivedMoney();
            receivedTitle += list.get(i).getReceivedTitle()==null?0D:list.get(i).getReceivedTitle();

            qctInvalidMoneySum += list.get(i).getQctInvalidMoney()==null?0D:list.get(i).getQctInvalidMoney();
            fxdcInvalidMoneySum += list.get(i).getFxdcInvalidMoney()==null?0D:list.get(i).getFxdcInvalidMoney();
            jjInvalidMoneySum += list.get(i).getJjInvalidMoney()==null?0D:list.get(i).getJjInvalidMoney();
            fyInvalidMoneySum += list.get(i).getFyInvalidMoney()==null?0D:list.get(i).getFyInvalidMoney();
            ccxInvalidMoneySum += list.get(i).getCcxInvalidMoney()==null?0D:list.get(i).getCcxInvalidMoney();
            ggpgInvalidMoneySum += list.get(i).getGgpgInvalidMoney()==null?0D:list.get(i).getGgpgInvalidMoney();
            otherInvalidMoneySum += list.get(i).getOtherInvalidMoney()==null?0D:list.get(i).getOtherInvalidMoney();
            pbInvalidMoneySum += list.get(i).getPbInvalidMoney()==null?0D:list.get(i).getPbInvalidMoney();
            invalidTitleSum += list.get(i).getInvalidTitle()==null?0D:list.get(i).getInvalidTitle();

            qctRedrushMoneySum += list.get(i).getQctRedrushMoney()==null?0D:list.get(i).getQctRedrushMoney();
            fxdcRedrushMoneySum += list.get(i).getFxdcRedrushMoney()==null?0D:list.get(i).getFxdcRedrushMoney();
            jjRedrushMoneySum += list.get(i).getJjRedrushMoney()==null?0D:list.get(i).getJjRedrushMoney();
            fyRedrushMoneySum += list.get(i).getFyRedrushMoney()==null?0D:list.get(i).getFyRedrushMoney();
            ccxRedrushMoneySum += list.get(i).getCcxRedrushMoney()==null?0D:list.get(i).getCcxRedrushMoney();
            ggpgRedrushMoneySum += list.get(i).getGgpgRedrushMoney()==null?0D:list.get(i).getGgpgRedrushMoney();
            otherRedrushMoneySum += list.get(i).getOtherRedrushMoney()==null?0D:list.get(i).getOtherRedrushMoney();
            pbRedrushMoneySum += list.get(i).getPbRedrushMoney()==null?0D:list.get(i).getPbRedrushMoney();
            redrushTitleSum += list.get(i).getRedrushTitle()==null?0D:list.get(i).getRedrushTitle();
        }
        caseAssessmentkpdzMonthReportSum.setOrgName("总计");
        caseAssessmentkpdzMonthReportSum.setQctInvoiceMoney(DecimalUtil.twoDecimalTOFourFromFive(qctInvoiceMoneySum));
        caseAssessmentkpdzMonthReportSum.setFxdcInvoiceMoney(DecimalUtil.twoDecimalTOFourFromFive(fxdcInvoiceMoneySum));
        caseAssessmentkpdzMonthReportSum.setJjInvoiceMoney(DecimalUtil.twoDecimalTOFourFromFive(jjInvoiceMoneySum));
        caseAssessmentkpdzMonthReportSum.setFyInvoiceMoney(DecimalUtil.twoDecimalTOFourFromFive(fyInvoiceMoneySum));
        caseAssessmentkpdzMonthReportSum.setCcxInvoiceMoney(DecimalUtil.twoDecimalTOFourFromFive(ccxInvoiceMoneySum));
        caseAssessmentkpdzMonthReportSum.setGgpgInvoiceMoney(DecimalUtil.twoDecimalTOFourFromFive(ggpgInvoiceMoneySum));
        caseAssessmentkpdzMonthReportSum.setOtherInvoiceMoney(DecimalUtil.twoDecimalTOFourFromFive(otherInvoiceMoneySum));
        caseAssessmentkpdzMonthReportSum.setPbInvoiceMoney(DecimalUtil.twoDecimalTOFourFromFive(pbInvoiceMoneySum));
        caseAssessmentkpdzMonthReportSum.setInvoiceTitle(DecimalUtil.twoDecimalTOFourFromFive(invoiceTitle));

        caseAssessmentkpdzMonthReportSum.setQctReceivedMoney(DecimalUtil.twoDecimalTOFourFromFive(qctReceivedMoneySum));
        caseAssessmentkpdzMonthReportSum.setFxdcReceivedMoney(DecimalUtil.twoDecimalTOFourFromFive(fxdcReceivedMoneySum));
        caseAssessmentkpdzMonthReportSum.setJjReceivedMoney(DecimalUtil.twoDecimalTOFourFromFive(jjReceivedMoneySum));
        caseAssessmentkpdzMonthReportSum.setFyReceivedMoney(DecimalUtil.twoDecimalTOFourFromFive(fyReceivedMoneySum));
        caseAssessmentkpdzMonthReportSum.setCcxReceivedMoney(DecimalUtil.twoDecimalTOFourFromFive(ccxReceivedMoneySum));
        caseAssessmentkpdzMonthReportSum.setGgpgReceivedMoney(DecimalUtil.twoDecimalTOFourFromFive(ggpgReceivedMoneySum));
        caseAssessmentkpdzMonthReportSum.setOtherReceivedMoney(DecimalUtil.twoDecimalTOFourFromFive(otherReceivedMoneySum));
        caseAssessmentkpdzMonthReportSum.setPbReceivedMoney(DecimalUtil.twoDecimalTOFourFromFive(pbReceivedMoneySum));
        caseAssessmentkpdzMonthReportSum.setReceivedTitle(DecimalUtil.twoDecimalTOFourFromFive(receivedTitle));

        caseAssessmentkpdzMonthReportSum.setQctInvalidMoney(DecimalUtil.twoDecimalTOFourFromFive(qctInvalidMoneySum));
        caseAssessmentkpdzMonthReportSum.setFxdcInvalidMoney(DecimalUtil.twoDecimalTOFourFromFive(fxdcInvalidMoneySum));
        caseAssessmentkpdzMonthReportSum.setJjInvalidMoney(DecimalUtil.twoDecimalTOFourFromFive(jjInvalidMoneySum));
        caseAssessmentkpdzMonthReportSum.setFyInvalidMoney(DecimalUtil.twoDecimalTOFourFromFive(fyInvalidMoneySum));
        caseAssessmentkpdzMonthReportSum.setCcxInvalidMoney(DecimalUtil.twoDecimalTOFourFromFive(ccxInvalidMoneySum));
        caseAssessmentkpdzMonthReportSum.setGgpgInvalidMoney(DecimalUtil.twoDecimalTOFourFromFive(ggpgInvalidMoneySum));
        caseAssessmentkpdzMonthReportSum.setOtherInvalidMoney(DecimalUtil.twoDecimalTOFourFromFive(otherInvalidMoneySum));
        caseAssessmentkpdzMonthReportSum.setPbInvalidMoney(DecimalUtil.twoDecimalTOFourFromFive(pbInvalidMoneySum));
        caseAssessmentkpdzMonthReportSum.setInvalidTitle(DecimalUtil.twoDecimalTOFourFromFive(invalidTitleSum));

        caseAssessmentkpdzMonthReportSum.setQctRedrushMoney(DecimalUtil.twoDecimalTOFourFromFive(qctRedrushMoneySum));
        caseAssessmentkpdzMonthReportSum.setFxdcRedrushMoney(DecimalUtil.twoDecimalTOFourFromFive(fxdcRedrushMoneySum));
        caseAssessmentkpdzMonthReportSum.setJjRedrushMoney(DecimalUtil.twoDecimalTOFourFromFive(jjRedrushMoneySum));
        caseAssessmentkpdzMonthReportSum.setFyRedrushMoney(DecimalUtil.twoDecimalTOFourFromFive(fyRedrushMoneySum));
        caseAssessmentkpdzMonthReportSum.setCcxRedrushMoney(DecimalUtil.twoDecimalTOFourFromFive(ccxRedrushMoneySum));
        caseAssessmentkpdzMonthReportSum.setGgpgRedrushMoney(DecimalUtil.twoDecimalTOFourFromFive(ggpgRedrushMoneySum));
        caseAssessmentkpdzMonthReportSum.setOtherRedrushMoney(DecimalUtil.twoDecimalTOFourFromFive(otherRedrushMoneySum));
        caseAssessmentkpdzMonthReportSum.setPbRedrushMoney(DecimalUtil.twoDecimalTOFourFromFive(pbRedrushMoneySum));
        caseAssessmentkpdzMonthReportSum.setRedrushTitle(DecimalUtil.twoDecimalTOFourFromFive(redrushTitleSum));

        return caseAssessmentkpdzMonthReportSum;
    }

    //返回计算的-个人业务开票到账 (包含“总计”数据)
    private CasePerkpdzMonthReportDto perkpdzTitle(List<CasePerkpdzMonthReportDto> list) {
        CasePerkpdzMonthReportDto casePerkpdzMonthReportSum = new CasePerkpdzMonthReportDto();
        double qctInvoiceMoneySum = 0D;
        double fxdcInvoiceMoneySum = 0D;
        double jjInvoiceMoneySum = 0D;
        double fyInvoiceMoneySum = 0D;
        double ccxInvoiceMoneySum = 0D;
        double ggpgInvoiceMoneySum = 0D;
        double otherInvoiceMoneySum = 0D;
        double pbInvoiceMoneySum = 0D;
        double invoiceTitle =0D;

        double qctReceivedMoneySum = 0D;
        double fxdcReceivedMoneySum = 0D;
        double jjReceivedMoneySum = 0D;
        double fyReceivedMoneySum = 0D;
        double ccxReceivedMoneySum = 0D;
        double ggpgReceivedMoneySum = 0D;
        double otherReceivedMoneySum = 0D;
        double pbReceivedMoneySum = 0D;
        double receivedTitle =0D;

        double qctInvalidMoneySum =0D;
        double fxdcInvalidMoneySum =0D;
        double jjInvalidMoneySum =0D;
        double fyInvalidMoneySum =0D;
        double ccxInvalidMoneySum =0D;
        double ggpgInvalidMoneySum =0D;
        double otherInvalidMoneySum =0D;
        double pbInvalidMoneySum =0D;
        double invalidTitleSum =0D; //作废金额合计

        double qctRedrushMoneySum =0D;
        double fxdcRedrushMoneySum =0D;
        double jjRedrushMoneySum =0D;
        double fyRedrushMoneySum =0D;
        double ccxRedrushMoneySum =0D;
        double ggpgRedrushMoneySum =0D;
        double otherRedrushMoneySum =0D;
        double pbRedrushMoneySum =0D;
        double redrushTitleSum = 0D; //红冲金额合计

        for (int i = 0; i < list.size(); i++) {
            qctInvoiceMoneySum += list.get(i).getQctInvoiceMoney()==null?0D:list.get(i).getQctInvoiceMoney();
            fxdcInvoiceMoneySum += list.get(i).getFxdcInvoiceMoney()==null?0D:list.get(i).getFxdcInvoiceMoney();
            jjInvoiceMoneySum += list.get(i).getJjInvoiceMoney()==null?0D:list.get(i).getJjInvoiceMoney();
            fyInvoiceMoneySum += list.get(i).getFyInvoiceMoney()==null?0D:list.get(i).getFyInvoiceMoney();
            ccxInvoiceMoneySum += list.get(i).getCcxInvoiceMoney()==null?0D:list.get(i).getCcxInvoiceMoney();
            ggpgInvoiceMoneySum += list.get(i).getGgpgInvoiceMoney()==null?0D:list.get(i).getGgpgInvoiceMoney();
            otherInvoiceMoneySum += list.get(i).getOtherInvoiceMoney()==null?0D:list.get(i).getOtherInvoiceMoney();
            pbInvoiceMoneySum += list.get(i).getPbInvoiceMoney()==null?0D:list.get(i).getPbInvoiceMoney();
            invoiceTitle += list.get(i).getInvoiceTitle()==null?0D:list.get(i).getInvoiceTitle();

            qctReceivedMoneySum += list.get(i).getQctReceivedMoney()==null?0D:list.get(i).getQctReceivedMoney();
            fxdcReceivedMoneySum += list.get(i).getFxdcReceivedMoney()==null?0D:list.get(i).getFxdcReceivedMoney();
            jjReceivedMoneySum += list.get(i).getJjReceivedMoney()==null?0D:list.get(i).getJjReceivedMoney();
            fyReceivedMoneySum += list.get(i).getFyReceivedMoney()==null?0D:list.get(i).getFyReceivedMoney();
            ccxReceivedMoneySum += list.get(i).getCcxReceivedMoney()==null?0D:list.get(i).getCcxReceivedMoney();
            ggpgReceivedMoneySum += list.get(i).getGgpgReceivedMoney()==null?0D:list.get(i).getGgpgReceivedMoney();
            otherReceivedMoneySum += list.get(i).getOtherReceivedMoney()==null?0D:list.get(i).getOtherReceivedMoney();
            pbReceivedMoneySum += list.get(i).getPbReceivedMoney()==null?0D:list.get(i).getPbReceivedMoney();
            receivedTitle += list.get(i).getReceivedTitle()==null?0D:list.get(i).getReceivedTitle();

            qctInvalidMoneySum += list.get(i).getQctInvalidMoney()==null?0D:list.get(i).getQctInvalidMoney();
            fxdcInvalidMoneySum += list.get(i).getFxdcInvalidMoney()==null?0D:list.get(i).getFxdcInvalidMoney();
            jjInvalidMoneySum += list.get(i).getJjInvalidMoney()==null?0D:list.get(i).getJjInvalidMoney();
            fyInvalidMoneySum += list.get(i).getFyInvalidMoney()==null?0D:list.get(i).getFyInvalidMoney();
            ccxInvalidMoneySum += list.get(i).getCcxInvalidMoney()==null?0D:list.get(i).getCcxInvalidMoney();
            ggpgInvalidMoneySum += list.get(i).getGgpgInvalidMoney()==null?0D:list.get(i).getGgpgInvalidMoney();
            otherInvalidMoneySum += list.get(i).getOtherInvalidMoney()==null?0D:list.get(i).getOtherInvalidMoney();
            pbInvalidMoneySum += list.get(i).getPbInvalidMoney()==null?0D:list.get(i).getPbInvalidMoney();
            invalidTitleSum += list.get(i).getInvalidTitle()==null?0D:list.get(i).getInvalidTitle();

            qctRedrushMoneySum += list.get(i).getQctRedrushMoney()==null?0D:list.get(i).getQctRedrushMoney();
            fxdcRedrushMoneySum += list.get(i).getFxdcRedrushMoney()==null?0D:list.get(i).getFxdcRedrushMoney();
            jjRedrushMoneySum += list.get(i).getJjRedrushMoney()==null?0D:list.get(i).getJjRedrushMoney();
            fyRedrushMoneySum += list.get(i).getFyRedrushMoney()==null?0D:list.get(i).getFyRedrushMoney();
            ccxRedrushMoneySum += list.get(i).getCcxRedrushMoney()==null?0D:list.get(i).getCcxRedrushMoney();
            ggpgRedrushMoneySum += list.get(i).getGgpgRedrushMoney()==null?0D:list.get(i).getGgpgRedrushMoney();
            otherRedrushMoneySum += list.get(i).getOtherRedrushMoney()==null?0D:list.get(i).getOtherRedrushMoney();
            pbRedrushMoneySum += list.get(i).getPbRedrushMoney()==null?0D:list.get(i).getPbRedrushMoney();
            redrushTitleSum += list.get(i).getRedrushTitle()==null?0D:list.get(i).getRedrushTitle();
        }
        casePerkpdzMonthReportSum.setOrgName("总计");
        casePerkpdzMonthReportSum.setQctInvoiceMoney(DecimalUtil.twoDecimalTOFourFromFive(qctInvoiceMoneySum));
        casePerkpdzMonthReportSum.setFxdcInvoiceMoney(DecimalUtil.twoDecimalTOFourFromFive(fxdcInvoiceMoneySum));
        casePerkpdzMonthReportSum.setJjInvoiceMoney(DecimalUtil.twoDecimalTOFourFromFive(jjInvoiceMoneySum));
        casePerkpdzMonthReportSum.setFyInvoiceMoney(DecimalUtil.twoDecimalTOFourFromFive(fyInvoiceMoneySum));
        casePerkpdzMonthReportSum.setCcxInvoiceMoney(DecimalUtil.twoDecimalTOFourFromFive(ccxInvoiceMoneySum));
        casePerkpdzMonthReportSum.setGgpgInvoiceMoney(DecimalUtil.twoDecimalTOFourFromFive(ggpgInvoiceMoneySum));
        casePerkpdzMonthReportSum.setOtherInvoiceMoney(DecimalUtil.twoDecimalTOFourFromFive(otherInvoiceMoneySum));
        casePerkpdzMonthReportSum.setPbInvoiceMoney(DecimalUtil.twoDecimalTOFourFromFive(pbInvoiceMoneySum));
        casePerkpdzMonthReportSum.setInvoiceTitle(DecimalUtil.twoDecimalTOFourFromFive(invoiceTitle));

        casePerkpdzMonthReportSum.setQctReceivedMoney(DecimalUtil.twoDecimalTOFourFromFive(qctReceivedMoneySum));
        casePerkpdzMonthReportSum.setFxdcReceivedMoney(DecimalUtil.twoDecimalTOFourFromFive(fxdcReceivedMoneySum));
        casePerkpdzMonthReportSum.setJjReceivedMoney(DecimalUtil.twoDecimalTOFourFromFive(jjReceivedMoneySum));
        casePerkpdzMonthReportSum.setFyReceivedMoney(DecimalUtil.twoDecimalTOFourFromFive(fyReceivedMoneySum));
        casePerkpdzMonthReportSum.setCcxReceivedMoney(DecimalUtil.twoDecimalTOFourFromFive(ccxReceivedMoneySum));
        casePerkpdzMonthReportSum.setGgpgReceivedMoney(DecimalUtil.twoDecimalTOFourFromFive(ggpgReceivedMoneySum));
        casePerkpdzMonthReportSum.setOtherReceivedMoney(DecimalUtil.twoDecimalTOFourFromFive(otherReceivedMoneySum));
        casePerkpdzMonthReportSum.setPbReceivedMoney(DecimalUtil.twoDecimalTOFourFromFive(pbReceivedMoneySum));
        casePerkpdzMonthReportSum.setReceivedTitle(DecimalUtil.twoDecimalTOFourFromFive(receivedTitle));

        casePerkpdzMonthReportSum.setQctInvalidMoney(DecimalUtil.twoDecimalTOFourFromFive(qctInvalidMoneySum));
        casePerkpdzMonthReportSum.setFxdcInvalidMoney(DecimalUtil.twoDecimalTOFourFromFive(fxdcInvalidMoneySum));
        casePerkpdzMonthReportSum.setJjInvalidMoney(DecimalUtil.twoDecimalTOFourFromFive(jjInvalidMoneySum));
        casePerkpdzMonthReportSum.setFyInvalidMoney(DecimalUtil.twoDecimalTOFourFromFive(fyInvalidMoneySum));
        casePerkpdzMonthReportSum.setCcxInvalidMoney(DecimalUtil.twoDecimalTOFourFromFive(ccxInvalidMoneySum));
        casePerkpdzMonthReportSum.setGgpgInvalidMoney(DecimalUtil.twoDecimalTOFourFromFive(ggpgInvalidMoneySum));
        casePerkpdzMonthReportSum.setOtherInvalidMoney(DecimalUtil.twoDecimalTOFourFromFive(otherInvalidMoneySum));
        casePerkpdzMonthReportSum.setPbInvalidMoney(DecimalUtil.twoDecimalTOFourFromFive(pbInvalidMoneySum));
        casePerkpdzMonthReportSum.setInvalidTitle(DecimalUtil.twoDecimalTOFourFromFive(invalidTitleSum));

        casePerkpdzMonthReportSum.setQctRedrushMoney(DecimalUtil.twoDecimalTOFourFromFive(qctRedrushMoneySum));
        casePerkpdzMonthReportSum.setFxdcRedrushMoney(DecimalUtil.twoDecimalTOFourFromFive(fxdcRedrushMoneySum));
        casePerkpdzMonthReportSum.setJjRedrushMoney(DecimalUtil.twoDecimalTOFourFromFive(jjRedrushMoneySum));
        casePerkpdzMonthReportSum.setFyRedrushMoney(DecimalUtil.twoDecimalTOFourFromFive(fyRedrushMoneySum));
        casePerkpdzMonthReportSum.setCcxRedrushMoney(DecimalUtil.twoDecimalTOFourFromFive(ccxRedrushMoneySum));
        casePerkpdzMonthReportSum.setGgpgRedrushMoney(DecimalUtil.twoDecimalTOFourFromFive(ggpgRedrushMoneySum));
        casePerkpdzMonthReportSum.setOtherRedrushMoney(DecimalUtil.twoDecimalTOFourFromFive(otherRedrushMoneySum));
        casePerkpdzMonthReportSum.setPbRedrushMoney(DecimalUtil.twoDecimalTOFourFromFive(pbRedrushMoneySum));
        casePerkpdzMonthReportSum.setRedrushTitle(DecimalUtil.twoDecimalTOFourFromFive(redrushTitleSum));

        return casePerkpdzMonthReportSum;

    }

    //个人业务案件报表(日、月) 页面查询显示(包含“机构合计”、“总计”)
    private CasePersonalDayReportListDto personalDayOrgTitle(List<CasePersonalDayReport> list, int count, ApiRequest apiReq) {
        List<CasePersonalDayReportDto> newList = new LinkedList<>();
        List<CasePersonalDayReportDto> orgList = new LinkedList<>();
        List<CasePersonalDayReportDto> allSumList = new LinkedList<>();

        //各机构合计
        int agentSignNumOrg = 0;
        double agentServiceMoneyOrg = 0D;
        double advanceAgentServiceMoneyOrg = 0D;
        double okAgentServiceMoneyOrg = 0D;
        int loanSignNumOrg = 0;
        double loanServiceMoneyOrg = 0D;
        double loanChannelMoneyOrg = 0D;
        double advanceLoanServiceMoneyOrg = 0D;
        double okLoanServiceMoneyOrg = 0D;
        int destroyCaseNumOrg = 0;
        int newCaseNumOrg = 0;
        double newServiceMoneyOrg = 0D;
        int totalSignNumOrg = 0;
        double totalServiceMoneyOrg = 0D;
        double totalOkServiceMoneyOrg = 0D;
        double totalAdServiceMoneyOrg = 0D;
        int caseInputNumOrg = 0;
        int caseTargetNumOrg = 0;
        int casePotentialNumOrg = 0;
        int caseIntentionNumOrg = 0;
        int caseSignNumOrg = 0;

        //总计
        int agentSignNumAll = 0;
        double agentServiceMoneyAll = 0D;
        double advanceAgentServiceMoneyAll = 0D;
        double okAgentServiceMoneyAll = 0D;
        int loanSignNumAll = 0;
        double loanServiceMoneyAll = 0D;
        double loanChannelMoneyAll = 0D;
        double advanceLoanServiceMoneyAll = 0D;
        double okLoanServiceMoneyAll = 0D;
        int destroyCaseNumAll = 0;
        int newCaseNumAll = 0;
        double newServiceMoneyAll = 0D;
        int totalSignNumAll = 0;
        double totalServiceMoneyAll = 0D;
        double totalOkServiceMoneyAll = 0D;
        double totalAdServiceMoneyAll = 0D;
        int caseInputNumAll = 0;
        int caseTargetNumAll = 0;
        int casePotentialNumAll = 0;
        int caseIntentionNumAll = 0;
        int caseSignNumAll = 0;

        int orgCount = 0;
        int allCount = 0;
        for (int i = 0; i < list.size(); i++) {
            CasePersonalDayReport report = list.get(i);
            CasePersonalDayReportDto dto = ConvertToBeanUtil.buildInfo(CasePersonalDayReportDto.class, report);

            //全部数据总计
            agentSignNumAll += report.getAgentSignNum() ==null ? 0 : report.getAgentSignNum();
            agentServiceMoneyAll += report.getAgentServiceMoney() ==null ? 0D : report.getAgentServiceMoney();
            advanceAgentServiceMoneyAll += report.getAdvanceAgentServiceMoney() ==null ? 0D : report.getAdvanceAgentServiceMoney();
            okAgentServiceMoneyAll += report.getOkAgentServiceMoney() ==null ? 0D : report.getOkAgentServiceMoney();
            loanSignNumAll += report.getLoanSignNum() ==null ? 0 : report.getLoanSignNum();
            loanServiceMoneyAll += report.getLoanServiceMoney() ==null ? 0D : report.getLoanServiceMoney();
            loanChannelMoneyAll += report.getLoanChannelMoney() ==null ? 0D : report.getLoanChannelMoney();
            advanceLoanServiceMoneyAll += report.getAdvanceLoanServiceMoney() ==null ? 0D : report.getAdvanceLoanServiceMoney();
            okLoanServiceMoneyAll += report.getOkLoanServiceMoney() ==null ? 0D : report.getOkLoanServiceMoney();
            destroyCaseNumAll += report.getDestroyCaseNum() ==null ? 0 : report.getDestroyCaseNum();
            newCaseNumAll += report.getNewCaseNum() ==null ? 0 : report.getNewCaseNum();
            newServiceMoneyAll += report.getNewServiceMoney() ==null ? 0D : report.getNewServiceMoney();
            totalSignNumAll += report.getTotalSignNum() ==null ? 0 : report.getTotalSignNum();
            totalServiceMoneyAll += report.getTotalServiceMoney() ==null ? 0D : report.getTotalServiceMoney();
            totalOkServiceMoneyAll += report.getTotalOkServiceMoney() ==null ? 0D : report.getTotalOkServiceMoney();
            totalAdServiceMoneyAll += report.getTotalAdServiceMoney() ==null ? 0D : report.getTotalAdServiceMoney();
            caseInputNumAll += report.getCaseInputNum() ==null ? 0 : report.getCaseInputNum();
            caseTargetNumAll += report.getCaseTargetNum() ==null ? 0 : report.getCaseTargetNum();
            casePotentialNumAll += report.getCasePotentialNum() ==null ? 0 : report.getCasePotentialNum();
            caseIntentionNumAll += report.getCaseIntentionNum() ==null ? 0 : report.getCaseIntentionNum();
            caseSignNumAll += report.getCaseSignNum() ==null ? 0 : report.getCaseSignNum();

            //机构总计
            agentSignNumOrg += report.getAgentSignNum() ==null ? 0 : report.getAgentSignNum();
            agentServiceMoneyOrg += report.getAgentServiceMoney() ==null ? 0D : report.getAgentServiceMoney();
            advanceAgentServiceMoneyOrg += report.getAdvanceAgentServiceMoney() ==null ? 0D : report.getAdvanceAgentServiceMoney();
            okAgentServiceMoneyOrg += report.getOkAgentServiceMoney() ==null ? 0D : report.getOkAgentServiceMoney();
            loanSignNumOrg += report.getLoanSignNum() ==null ? 0 : report.getLoanSignNum();
            loanServiceMoneyOrg += report.getLoanServiceMoney() ==null ? 0D : report.getLoanServiceMoney();
            loanChannelMoneyOrg += report.getLoanChannelMoney() ==null ? 0D : report.getLoanChannelMoney();
            advanceLoanServiceMoneyOrg += report.getAdvanceLoanServiceMoney() ==null ? 0D : report.getAdvanceLoanServiceMoney();
            okLoanServiceMoneyOrg += report.getOkLoanServiceMoney() ==null ? 0D : report.getOkLoanServiceMoney();
            destroyCaseNumOrg += report.getDestroyCaseNum() ==null ? 0 : report.getDestroyCaseNum();
            newCaseNumOrg += report.getNewCaseNum() ==null ? 0 : report.getNewCaseNum();
            newServiceMoneyOrg += report.getNewServiceMoney() ==null ? 0D : report.getNewServiceMoney();
            totalSignNumOrg += report.getTotalSignNum() ==null ? 0 : report.getTotalSignNum();
            totalServiceMoneyOrg += report.getTotalServiceMoney() ==null ? 0D : report.getTotalServiceMoney();
            totalOkServiceMoneyOrg += report.getTotalOkServiceMoney() ==null ? 0D : report.getTotalOkServiceMoney();
            totalAdServiceMoneyOrg += report.getTotalAdServiceMoney() ==null ? 0D : report.getTotalAdServiceMoney();
            caseInputNumOrg += report.getCaseInputNum() ==null ? 0 : report.getCaseInputNum();
            caseTargetNumOrg += report.getCaseTargetNum() ==null ? 0 : report.getCaseTargetNum();
            casePotentialNumOrg += report.getCasePotentialNum() ==null ? 0 : report.getCasePotentialNum();
            caseIntentionNumOrg += report.getCaseIntentionNum() ==null ? 0 : report.getCaseIntentionNum();
            caseSignNumOrg += report.getCaseSignNum() ==null ? 0 : report.getCaseSignNum();
            //机构总计，从第二条数据开始计算。
            if (i > 0 && report.getOrgId()!=null && list.get(i - 1).getOrgId()!=null) {
                if (!report.getOrgId().equals(list.get(i - 1).getOrgId())) {
                    CasePersonalDayReportDto reportDto = new CasePersonalDayReportDto();
                    reportDto.setOrgId(list.get(i - 1).getOrgId());
                    reportDto.setOrgName(list.get(i - 1).getOrgName()==null?"空机构":list.get(i - 1).getOrgName());
                    reportDto.setDirectorName(null);
                    reportDto.setDirectorId(null);
                    reportDto.setSalesmanId(null);
                    reportDto.setSalesmanName(null);
                    reportDto.setIsDisability(null);

                    reportDto.setAgentSignNum(agentSignNumOrg - (report.getAgentSignNum() ==null ? 0 : report.getAgentSignNum()));
                    reportDto.setAgentServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(agentServiceMoneyOrg - (report.getAgentServiceMoney() ==null ? 0D : report.getAgentServiceMoney())));
                    reportDto.setAdvanceAgentServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(advanceAgentServiceMoneyOrg - (report.getAdvanceAgentServiceMoney() ==null ? 0D : report.getAdvanceAgentServiceMoney())));
                    reportDto.setOkAgentServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(okAgentServiceMoneyOrg - (report.getOkAgentServiceMoney() ==null ? 0D : report.getOkAgentServiceMoney())));
                    reportDto.setLoanSignNum(loanSignNumOrg - (report.getLoanSignNum() ==null ? 0 : report.getLoanSignNum()));
                    reportDto.setLoanServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(loanServiceMoneyOrg - (report.getLoanServiceMoney() ==null ? 0D : report.getLoanServiceMoney())));
                    reportDto.setLoanChannelMoney(DecimalUtil.twoDecimalTOFourFromFive(loanChannelMoneyOrg - (report.getLoanChannelMoney() ==null ? 0D : report.getLoanChannelMoney())));
                    reportDto.setAdvanceLoanServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(advanceLoanServiceMoneyOrg - (report.getAdvanceLoanServiceMoney() ==null ? 0D : report.getAdvanceLoanServiceMoney())));
                    reportDto.setOkLoanServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(okLoanServiceMoneyOrg - (report.getOkLoanServiceMoney() ==null ? 0D : report.getOkLoanServiceMoney())));
                    reportDto.setDestroyCaseNum(destroyCaseNumOrg - (report.getDestroyCaseNum() ==null ? 0 : report.getDestroyCaseNum()));
                    reportDto.setNewCaseNum(newCaseNumOrg - (report.getNewCaseNum() ==null ? 0 : report.getNewCaseNum()));
                    reportDto.setNewServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(newServiceMoneyOrg - (report.getNewServiceMoney() ==null ? 0D : report.getNewServiceMoney())));
                    reportDto.setTotalSignNum(totalSignNumOrg - (report.getTotalSignNum() ==null ? 0 : report.getTotalSignNum()));
                    reportDto.setTotalServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(totalServiceMoneyOrg - (report.getTotalServiceMoney() ==null ? 0D : report.getTotalServiceMoney())));
                    reportDto.setTotalOkServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(totalOkServiceMoneyOrg - (report.getTotalOkServiceMoney() ==null ? 0D : report.getTotalOkServiceMoney())));
                    reportDto.setTotalAdServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(totalAdServiceMoneyOrg - (report.getTotalAdServiceMoney() ==null ? 0D : report.getTotalAdServiceMoney())));
                    reportDto.setCaseInputNum(caseInputNumOrg - (report.getCaseInputNum() ==null ? 0 : report.getCaseInputNum()));
                    reportDto.setCaseTargetNum(caseTargetNumOrg - (report.getCaseTargetNum() ==null ? 0 : report.getCaseTargetNum()));
                    reportDto.setCasePotentialNum(casePotentialNumOrg - (report.getCasePotentialNum() ==null ? 0 : report.getCasePotentialNum()));
                    reportDto.setCaseIntentionNum(caseIntentionNumOrg - (report.getCaseIntentionNum() ==null ? 0 : report.getCaseIntentionNum()));
                    reportDto.setCaseSignNum(caseSignNumOrg - (report.getCaseSignNum() ==null ? 0 : report.getCaseSignNum()));
                    reportDto.setIsDisability(null);

                    newList.add(reportDto);
                    orgCount = orgCount + 1;
                    orgList.add(reportDto);
                    allCount = allCount + 1;
                    if(i != list.size() - 1) {
                        agentSignNumOrg = report.getAgentSignNum() ==null ? 0 : report.getAgentSignNum();
                        agentServiceMoneyOrg = report.getAgentServiceMoney() ==null ? 0D : report.getAgentServiceMoney();
                        advanceAgentServiceMoneyOrg = report.getAdvanceAgentServiceMoney() ==null ? 0D : report.getAdvanceAgentServiceMoney();
                        okAgentServiceMoneyOrg = report.getOkAgentServiceMoney() ==null ? 0D : report.getOkAgentServiceMoney();
                        loanSignNumOrg = report.getLoanSignNum() ==null ? 0 : report.getLoanSignNum();
                        loanServiceMoneyOrg = report.getLoanServiceMoney() ==null ? 0D : report.getLoanServiceMoney();
                        loanChannelMoneyOrg = report.getLoanChannelMoney() ==null ? 0D : report.getLoanChannelMoney();
                        advanceLoanServiceMoneyOrg = report.getAdvanceLoanServiceMoney() ==null ? 0D : report.getAdvanceLoanServiceMoney();
                        okLoanServiceMoneyOrg = report.getOkLoanServiceMoney() ==null ? 0D : report.getOkLoanServiceMoney();
                        destroyCaseNumOrg = report.getDestroyCaseNum() ==null ? 0 : report.getDestroyCaseNum();
                        newCaseNumOrg = report.getNewCaseNum() ==null ? 0 : report.getNewCaseNum();
                        newServiceMoneyOrg = report.getNewServiceMoney() ==null ? 0D : report.getNewServiceMoney();
                        totalSignNumOrg = report.getTotalSignNum() ==null ? 0 : report.getTotalSignNum();
                        totalServiceMoneyOrg = report.getTotalServiceMoney() ==null ? 0D : report.getTotalServiceMoney();
                        totalOkServiceMoneyOrg = report.getTotalOkServiceMoney() ==null ? 0D : report.getTotalOkServiceMoney();
                        totalAdServiceMoneyOrg = report.getTotalAdServiceMoney() ==null ? 0D : report.getTotalAdServiceMoney();
                        caseInputNumOrg = report.getCaseInputNum() ==null ? 0 : report.getCaseInputNum();
                        caseTargetNumOrg = report.getCaseTargetNum() ==null ? 0 : report.getCaseTargetNum();
                        casePotentialNumOrg = report.getCasePotentialNum() ==null ? 0 : report.getCasePotentialNum();
                        caseIntentionNumOrg = report.getCaseIntentionNum() ==null ? 0 : report.getCaseIntentionNum();
                        caseSignNumOrg = report.getCaseSignNum() ==null ? 0 : report.getCaseSignNum();
                    }
                }
            }
            dto.setTargetToPotRate(DecimalUtil.twoDecimalTOFourFromFive(dto.getTargetToPotRate()));
            dto.setInteToSignRate(DecimalUtil.twoDecimalTOFourFromFive(dto.getInteToSignRate()));
            newList.add(dto);
            orgCount = orgCount + 1;
            //最后一条数据
            if(list.size() > 1 && i == list.size() - 1){
                //机构数据
                CasePersonalDayReportDto LastOrgReportDto = new CasePersonalDayReportDto();
                LastOrgReportDto.setOrgId(report.getOrgId());
                LastOrgReportDto.setOrgName(report.getOrgName() == null ? "空机构" : report.getOrgName());
                LastOrgReportDto.setDirectorName(null);
                LastOrgReportDto.setDirectorId(null);
                LastOrgReportDto.setSalesmanId(null);
                LastOrgReportDto.setSalesmanName(null);
                LastOrgReportDto.setIsDisability(null);

                if (report.getOrgId() ==  null){
                    report.setOrgId(-1L);
                }
                if (list.get(i-1).getOrgId() == null){
                    list.get(i-1).setOrgId(-1L);
                }

                //判断最后一条数据和上一条数据是否同一机构下
                if (report.getOrgId().equals(list.get(i - 1).getOrgId())) {
                    LastOrgReportDto.setAgentSignNum(agentSignNumOrg);
                    LastOrgReportDto.setAgentServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(agentServiceMoneyOrg));
                    LastOrgReportDto.setAdvanceAgentServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(advanceAgentServiceMoneyOrg));
                    LastOrgReportDto.setOkAgentServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(okAgentServiceMoneyOrg));
                    LastOrgReportDto.setLoanSignNum(loanSignNumOrg);
                    LastOrgReportDto.setLoanServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(loanServiceMoneyOrg));
                    LastOrgReportDto.setLoanChannelMoney(DecimalUtil.twoDecimalTOFourFromFive(loanChannelMoneyOrg));
                    LastOrgReportDto.setAdvanceLoanServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(advanceLoanServiceMoneyOrg));
                    LastOrgReportDto.setOkLoanServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(okLoanServiceMoneyOrg));
                    LastOrgReportDto.setDestroyCaseNum(destroyCaseNumOrg);
                    LastOrgReportDto.setNewCaseNum(newCaseNumOrg);
                    LastOrgReportDto.setNewServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(newServiceMoneyOrg));
                    LastOrgReportDto.setTotalSignNum(totalSignNumOrg);
                    LastOrgReportDto.setTotalServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(totalServiceMoneyOrg));
                    LastOrgReportDto.setTotalOkServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(totalOkServiceMoneyOrg));
                    LastOrgReportDto.setTotalAdServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(totalAdServiceMoneyOrg));
                    LastOrgReportDto.setCaseInputNum(caseInputNumOrg);
                    LastOrgReportDto.setCaseTargetNum(caseTargetNumOrg);
                    LastOrgReportDto.setCasePotentialNum(casePotentialNumOrg);
                    LastOrgReportDto.setCaseIntentionNum(caseIntentionNumOrg);
                    LastOrgReportDto.setCaseSignNum(caseSignNumOrg);
                    LastOrgReportDto.setIsDisability(null);
                }else{
                    LastOrgReportDto.setAgentSignNum(report.getAgentSignNum());
                    LastOrgReportDto.setAgentServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(report.getAgentServiceMoney()));
                    LastOrgReportDto.setAdvanceAgentServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(report.getAdvanceAgentServiceMoney()));
                    LastOrgReportDto.setOkAgentServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(report.getOkAgentServiceMoney()));
                    LastOrgReportDto.setLoanSignNum(report.getLoanSignNum());
                    LastOrgReportDto.setLoanServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(report.getLoanServiceMoney()));
                    LastOrgReportDto.setLoanChannelMoney(DecimalUtil.twoDecimalTOFourFromFive(report.getLoanChannelMoney()));
                    LastOrgReportDto.setAdvanceLoanServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(report.getAdvanceLoanServiceMoney()));
                    LastOrgReportDto.setOkLoanServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(report.getOkLoanServiceMoney()));
                    LastOrgReportDto.setDestroyCaseNum(report.getDestroyCaseNum());
                    LastOrgReportDto.setNewCaseNum(report.getNewCaseNum());
                    LastOrgReportDto.setNewServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(report.getNewServiceMoney()));
                    LastOrgReportDto.setTotalSignNum(report.getTotalSignNum());
                    LastOrgReportDto.setTotalServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(report.getTotalServiceMoney()));
                    LastOrgReportDto.setTotalOkServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(report.getTotalOkServiceMoney()));
                    LastOrgReportDto.setTotalAdServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(report.getTotalAdServiceMoney()));
                    LastOrgReportDto.setCaseInputNum(report.getCaseInputNum());
                    LastOrgReportDto.setCaseTargetNum(report.getCaseTargetNum());
                    LastOrgReportDto.setCasePotentialNum(report.getCasePotentialNum());
                    LastOrgReportDto.setCaseIntentionNum(report.getCaseIntentionNum());
                    LastOrgReportDto.setCaseSignNum(report.getCaseSignNum());
                    LastOrgReportDto.setIsDisability(null);
                }
                newList.add(LastOrgReportDto);
                orgCount = orgCount + 1;
                orgList.add(LastOrgReportDto);
                allCount = allCount + 1;
            }
        }

        //总计数据
        CasePersonalDayReportDto LastAllReportDto = new CasePersonalDayReportDto();
        LastAllReportDto.setOrgId(null);
        LastAllReportDto.setOrgName("总计");
        LastAllReportDto.setDirectorName(null);
        LastAllReportDto.setDirectorId(null);
        LastAllReportDto.setSalesmanId(null);
        LastAllReportDto.setSalesmanName(null);

        LastAllReportDto.setAgentSignNum(agentSignNumAll);
        LastAllReportDto.setAgentServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(agentServiceMoneyAll));
        LastAllReportDto.setAdvanceAgentServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(advanceAgentServiceMoneyAll));
        LastAllReportDto.setOkAgentServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(okAgentServiceMoneyAll));
        LastAllReportDto.setLoanSignNum(loanSignNumAll);
        LastAllReportDto.setLoanServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(loanServiceMoneyAll));
        LastAllReportDto.setLoanChannelMoney(DecimalUtil.twoDecimalTOFourFromFive(loanChannelMoneyAll));
        LastAllReportDto.setAdvanceLoanServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(advanceLoanServiceMoneyAll));
        LastAllReportDto.setOkLoanServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(okLoanServiceMoneyAll));
        LastAllReportDto.setDestroyCaseNum(destroyCaseNumAll);
        LastAllReportDto.setNewCaseNum(newCaseNumAll);
        LastAllReportDto.setNewServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(newServiceMoneyAll));
        LastAllReportDto.setTotalSignNum(totalSignNumAll);
        LastAllReportDto.setTotalServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(totalServiceMoneyAll));
        LastAllReportDto.setTotalOkServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(totalOkServiceMoneyAll));
        LastAllReportDto.setTotalAdServiceMoney(DecimalUtil.twoDecimalTOFourFromFive(totalAdServiceMoneyAll));
        LastAllReportDto.setCaseInputNum(caseInputNumAll);
        LastAllReportDto.setCaseTargetNum(caseTargetNumAll);
        LastAllReportDto.setCasePotentialNum(casePotentialNumAll);
        LastAllReportDto.setCaseIntentionNum(caseIntentionNumAll);
        LastAllReportDto.setCaseSignNum(caseSignNumAll);
        LastAllReportDto.setIsDisability(null);
        allSumList.add(LastAllReportDto);

        List<CasePersonalDayReportDto> newList1 = new LinkedList<>();
        int newSize;
        //获取包含 “机构合计”的数据，并操作分页
        if(newList.size() >= apiReq.getInt("pageIndex") + apiReq.getInt("pageSize")){
            newSize = apiReq.getInt("pageSize") +apiReq.getInt("pageIndex");
        }else{
            newSize = newList.size();
        }

        //仅为“机构合并的数据”
        for (int i = apiReq.getInt("pageIndex"); i < newSize; i++) {
            if(newList.size()>0){
                newList1.add(newList.get(i));
            }
        }

        List<CasePersonalDayReportDto> orgList1 = new LinkedList<>();
        int sumSize;
        if(orgList.size() >= apiReq.getInt("pageIndex") + apiReq.getInt("pageSize")){
            sumSize = apiReq.getInt("pageSize");
        }else{
            sumSize = orgList.size();
        }
        for (int i = apiReq.getInt("pageIndex"); i < sumSize; i++) {
            if(orgList.size()>0) {
                orgList1.add(orgList.get(i));
            }
        }

        CasePersonalDayReportListDto listDtos = new CasePersonalDayReportListDto();
        listDtos.setNewList(newList1);
        listDtos.setOrgList(orgList1);
        listDtos.setAllList(allSumList);

        //根据“是否汇总数据”，返回count值
        String isSum = apiReq.getString("isSum");
        if("1".equals(isSum)){
            count = allCount;
        }else{
            count = orgCount;
        }
        listDtos.setCount(count);

        return listDtos;
    }

    //个人业务案件报表(日、月) 页面查询显示(包含“主管合计”、“机构合计”、“总计”)
    private List<CasePersonalDayReportDto> personalDayDirectorTitle(List<CasePersonalDayReport> list, ApiRequest apiReq) {
        List<CasePersonalDayReportDto> newList = new LinkedList<>();
        List<CasePersonalDayReportDto> orgList = new LinkedList<>();
        //主管总计
        int agentSignNum = 0;
        double agentServiceMoney = 0D;
        double advanceAgentServiceMoney = 0D;
        double okAgentServiceMoney = 0D;
        int loanSignNum = 0;
        double loanServiceMoney = 0D;
        double loanChannelMoney = 0D;
        double advanceLoanServiceMoney = 0D;
        double okLoanServiceMoney = 0D;
        int destroyCaseNum = 0;
        int newCaseNum = 0;
        double newServiceMoney = 0D;
        int totalSignNum = 0;
        double totalServiceMoney = 0D;
        double totalOkServiceMoney = 0D;
        double totalAdServiceMoney = 0D;
        int caseInputNum = 0;
        int caseTargetNum = 0;
        int casePotentialNum = 0;
        int caseIntentionNum = 0;
        int caseSignNum = 0;

        //机构总计
        int agentSignNumOrg = 0;
        double agentServiceMoneyOrg = 0D;
        double advanceAgentServiceMoneyOrg = 0D;
        double okAgentServiceMoneyOrg = 0D;
        int loanSignNumOrg = 0;
        double loanServiceMoneyOrg = 0D;
        double loanChannelMoneyOrg = 0D;
        double advanceLoanServiceMoneyOrg = 0D;
        double okLoanServiceMoneyOrg = 0D;
        int destroyCaseNumOrg = 0;
        int newCaseNumOrg = 0;
        double newServiceMoneyOrg = 0D;
        int totalSignNumOrg = 0;
        double totalServiceMoneyOrg = 0D;
        double totalOkServiceMoneyOrg = 0D;
        double totalAdServiceMoneyOrg = 0D;
        int caseInputNumOrg = 0;
        int caseTargetNumOrg = 0;
        int casePotentialNumOrg = 0;
        int caseIntentionNumOrg = 0;
        int caseSignNumOrg = 0;

        //全部数据总计
        int agentSignNumAll = 0;
        double agentServiceMoneyAll = 0D;
        double advanceAgentServiceMoneyAll = 0D;
        double okAgentServiceMoneyAll = 0D;
        int loanSignNumAll = 0;
        double loanServiceMoneyAll = 0D;
        double loanChannelMoneyAll = 0D;
        double advanceLoanServiceMoneyAll = 0D;
        double okLoanServiceMoneyAll = 0D;
        int destroyCaseNumAll = 0;
        int newCaseNumAll = 0;
        double newServiceMoneyAll = 0D;
        int totalSignNumAll = 0;
        double totalServiceMoneyAll = 0D;
        double totalOkServiceMoneyAll = 0D;
        double totalAdServiceMoneyAll = 0D;
        int caseInputNumAll = 0;
        int caseTargetNumAll = 0;
        int casePotentialNumAll = 0;
        int caseIntentionNumAll = 0;
        int caseSignNumAll = 0;

        for (int i = 0; i < list.size(); i++) {
            CasePersonalDayReport report = list.get(i);
            CasePersonalDayReportDto dto = ConvertToBeanUtil.buildInfo(CasePersonalDayReportDto.class, report);

            //全部数据总计
            agentSignNumAll += report.getAgentSignNum() ==null ? 0 : report.getAgentSignNum();
            agentServiceMoneyAll += report.getAgentServiceMoney() ==null ? 0D : report.getAgentServiceMoney();
            advanceAgentServiceMoneyAll += report.getAdvanceAgentServiceMoney() ==null ? 0D : report.getAdvanceAgentServiceMoney();
            okAgentServiceMoneyAll += report.getOkAgentServiceMoney() ==null ? 0D : report.getOkAgentServiceMoney();
            loanSignNumAll += report.getLoanSignNum() ==null ? 0 : report.getLoanSignNum();
            loanServiceMoneyAll += report.getLoanServiceMoney() ==null ? 0D : report.getLoanServiceMoney();
            loanChannelMoneyAll += report.getLoanChannelMoney() ==null ? 0D : report.getLoanChannelMoney();
            advanceLoanServiceMoneyAll += report.getAdvanceLoanServiceMoney() ==null ? 0D : report.getAdvanceLoanServiceMoney();
            okLoanServiceMoneyAll += report.getOkLoanServiceMoney() ==null ? 0D : report.getOkLoanServiceMoney();
            destroyCaseNumAll += report.getDestroyCaseNum() ==null ? 0 : report.getDestroyCaseNum();
            newCaseNumAll += report.getNewCaseNum() ==null ? 0 : report.getNewCaseNum();
            newServiceMoneyAll += report.getNewServiceMoney() ==null ? 0D : report.getNewServiceMoney();
            totalSignNumAll += report.getTotalSignNum() ==null ? 0 : report.getTotalSignNum();
            totalServiceMoneyAll += report.getTotalServiceMoney() ==null ? 0D : report.getTotalServiceMoney();
            totalOkServiceMoneyAll += report.getTotalOkServiceMoney() ==null ? 0D : report.getTotalOkServiceMoney();
            totalAdServiceMoneyAll += report.getTotalAdServiceMoney() ==null ? 0D : report.getTotalAdServiceMoney();
            caseInputNumAll += report.getCaseInputNum() ==null ? 0 : report.getCaseInputNum();
            caseTargetNumAll += report.getCaseTargetNum() ==null ? 0 : report.getCaseTargetNum();
            casePotentialNumAll += report.getCasePotentialNum() ==null ? 0 : report.getCasePotentialNum();
            caseIntentionNumAll += report.getCaseIntentionNum() ==null ? 0 : report.getCaseIntentionNum();
            caseSignNumAll += report.getCaseSignNum() ==null ? 0 : report.getCaseSignNum();

            //主管总计
            agentSignNum += report.getAgentSignNum() ==null ? 0 : report.getAgentSignNum();
            agentServiceMoney += report.getAgentServiceMoney() ==null ? 0D : report.getAgentServiceMoney();
            advanceAgentServiceMoney += report.getAdvanceAgentServiceMoney() ==null ? 0D : report.getAdvanceAgentServiceMoney();
            okAgentServiceMoney += report.getOkAgentServiceMoney() ==null ? 0D : report.getOkAgentServiceMoney();
            loanSignNum += report.getLoanSignNum() ==null ? 0 : report.getLoanSignNum();
            loanServiceMoney += report.getLoanServiceMoney() ==null ? 0D : report.getLoanServiceMoney();
            loanChannelMoney += report.getLoanChannelMoney() ==null ? 0D : report.getLoanChannelMoney();
            advanceLoanServiceMoney += report.getAdvanceLoanServiceMoney() ==null ? 0D : report.getAdvanceLoanServiceMoney();
            okLoanServiceMoney += report.getOkLoanServiceMoney() ==null ? 0D : report.getOkLoanServiceMoney();
            destroyCaseNum += report.getDestroyCaseNum() ==null ? 0 : report.getDestroyCaseNum();
            newCaseNum += report.getNewCaseNum() ==null ? 0 : report.getNewCaseNum();
            newServiceMoney += report.getNewServiceMoney() ==null ? 0D : report.getNewServiceMoney();
            totalSignNum += report.getTotalSignNum() ==null ? 0 : report.getTotalSignNum();
            totalServiceMoney += report.getTotalServiceMoney() ==null ? 0D : report.getTotalServiceMoney();
            totalOkServiceMoney += report.getTotalOkServiceMoney() ==null ? 0D : report.getTotalOkServiceMoney();
            totalAdServiceMoney += report.getTotalAdServiceMoney() ==null ? 0D : report.getTotalAdServiceMoney();
            caseInputNum += report.getCaseInputNum() ==null ? 0 : report.getCaseInputNum();
            caseTargetNum += report.getCaseTargetNum() ==null ? 0 : report.getCaseTargetNum();
            casePotentialNum += report.getCasePotentialNum() ==null ? 0 : report.getCasePotentialNum();
            caseIntentionNum += report.getCaseIntentionNum() ==null ? 0 : report.getCaseIntentionNum();
            caseSignNum += report.getCaseSignNum() ==null ? 0 : report.getCaseSignNum();

            if (i > 0) {
//              if (i > 0 && report.getDirectorId()!=null && list.get(i - 1).getDirectorId()!=null) {
                report.setDirectorId(report.getDirectorId()==null?0l:report.getDirectorId());
                list.get(i - 1).setDirectorId(list.get(i - 1).getDirectorId()==null?0l:list.get(i - 1).getDirectorId());
                if (!report.getDirectorId().equals(list.get(i - 1).getDirectorId())) {
                    CasePersonalDayReportDto reportDto = new CasePersonalDayReportDto();
                    reportDto.setOrgId(list.get(i - 1).getOrgId());
                    reportDto.setOrgName(list.get(i - 1).getOrgName());
                    reportDto.setDirectorName(list.get(i - 1).getDirectorName());
                    reportDto.setDirectorId(list.get(i - 1).getDirectorId());
                    reportDto.setSalesmanId(null);
                    reportDto.setSalesmanName("小计");

                    reportDto.setAgentSignNum(agentSignNum - (report.getAgentSignNum() ==null ? 0 : report.getAgentSignNum()));
                    reportDto.setAgentServiceMoney(agentServiceMoney - (report.getAgentServiceMoney() ==null ? 0D : report.getAgentServiceMoney()));
                    reportDto.setAdvanceAgentServiceMoney(advanceAgentServiceMoney - (report.getAdvanceAgentServiceMoney() ==null ? 0D : report.getAdvanceAgentServiceMoney()));
                    reportDto.setOkAgentServiceMoney(okAgentServiceMoney - (report.getOkAgentServiceMoney() ==null ? 0D : report.getOkAgentServiceMoney()));
                    reportDto.setLoanSignNum(loanSignNum - (report.getLoanSignNum() ==null ? 0 : report.getLoanSignNum()));
                    reportDto.setLoanServiceMoney(loanServiceMoney - (report.getLoanServiceMoney() ==null ? 0D : report.getLoanServiceMoney()));
                    reportDto.setLoanChannelMoney(loanChannelMoney - (report.getLoanChannelMoney() ==null ? 0D : report.getLoanChannelMoney()));
                    reportDto.setAdvanceLoanServiceMoney(advanceLoanServiceMoney - (report.getAdvanceLoanServiceMoney() ==null ? 0D : report.getAdvanceLoanServiceMoney()));
                    reportDto.setOkLoanServiceMoney(okLoanServiceMoney - (report.getOkLoanServiceMoney() ==null ? 0D : report.getOkLoanServiceMoney()));
                    reportDto.setDestroyCaseNum(destroyCaseNum - (report.getDestroyCaseNum() ==null ? 0 : report.getDestroyCaseNum()));
                    reportDto.setNewCaseNum(newCaseNum - (report.getNewCaseNum() ==null ? 0 : report.getNewCaseNum()));
                    reportDto.setNewServiceMoney(newServiceMoney - (report.getNewServiceMoney() ==null ? 0D : report.getNewServiceMoney()));
                    reportDto.setTotalSignNum(totalSignNum - (report.getTotalSignNum() ==null ? 0 : report.getTotalSignNum()));
                    reportDto.setTotalServiceMoney(totalServiceMoney - (report.getTotalServiceMoney() ==null ? 0D : report.getTotalServiceMoney()));
                    reportDto.setTotalOkServiceMoney(totalOkServiceMoney - (report.getTotalOkServiceMoney() ==null ? 0D : report.getTotalOkServiceMoney()));
                    reportDto.setTotalAdServiceMoney(totalAdServiceMoney - (report.getTotalAdServiceMoney() ==null ? 0D : report.getTotalAdServiceMoney()));
                    reportDto.setCaseInputNum(caseInputNum - (report.getCaseInputNum() ==null ? 0 : report.getCaseInputNum()));
                    reportDto.setCaseTargetNum(caseTargetNum - (report.getCaseTargetNum() ==null ? 0 : report.getCaseTargetNum()));
                    reportDto.setCasePotentialNum(casePotentialNum - (report.getCasePotentialNum() ==null ? 0 : report.getCasePotentialNum()));
                    reportDto.setCaseIntentionNum(caseIntentionNum - (report.getCaseIntentionNum() ==null ? 0 : report.getCaseIntentionNum()));
                    reportDto.setCaseSignNum(caseSignNum - (report.getCaseSignNum() ==null ? 0 : report.getCaseSignNum()));

                    newList.add(reportDto);
                    if(i != list.size() - 1) {
                        agentSignNum = report.getAgentSignNum() ==null ? 0 : report.getAgentSignNum();
                        agentServiceMoney = report.getAgentServiceMoney() ==null ? 0D : report.getAgentServiceMoney();
                        advanceAgentServiceMoney = report.getAdvanceAgentServiceMoney() ==null ? 0D : report.getAdvanceAgentServiceMoney();
                        okAgentServiceMoney = report.getOkAgentServiceMoney() ==null ? 0D : report.getOkAgentServiceMoney();
                        loanSignNum = report.getLoanSignNum() ==null ? 0 : report.getLoanSignNum();
                        loanServiceMoney = report.getLoanServiceMoney() ==null ? 0D : report.getLoanServiceMoney();
                        loanChannelMoney = report.getLoanChannelMoney() ==null ? 0D : report.getLoanChannelMoney();
                        advanceLoanServiceMoney = report.getAdvanceLoanServiceMoney() ==null ? 0D : report.getAdvanceLoanServiceMoney();
                        okLoanServiceMoney = report.getOkLoanServiceMoney() ==null ? 0D : report.getOkLoanServiceMoney();
                        destroyCaseNum = report.getDestroyCaseNum() ==null ? 0 : report.getDestroyCaseNum();
                        newCaseNum = report.getNewCaseNum() ==null ? 0 : report.getNewCaseNum();
                        newServiceMoney = report.getNewServiceMoney() ==null ? 0D : report.getNewServiceMoney();
                        totalSignNum = report.getTotalSignNum() ==null ? 0 : report.getTotalSignNum();
                        totalServiceMoney = report.getTotalServiceMoney() ==null ? 0D : report.getTotalServiceMoney();
                        totalOkServiceMoney = report.getTotalOkServiceMoney() ==null ? 0D : report.getTotalOkServiceMoney();
                        totalAdServiceMoney = report.getTotalAdServiceMoney() ==null ? 0D : report.getTotalAdServiceMoney();
                        caseInputNum = report.getCaseInputNum() ==null ? 0 : report.getCaseInputNum();
                        caseTargetNum = report.getCaseTargetNum() ==null ? 0 : report.getCaseTargetNum();
                        casePotentialNum = report.getCasePotentialNum() ==null ? 0 : report.getCasePotentialNum();
                        caseIntentionNum = report.getCaseIntentionNum() ==null ? 0 : report.getCaseIntentionNum();
                        caseSignNum = report.getCaseSignNum() ==null ? 0 : report.getCaseSignNum();
                    }
                }
            }

            //机构总计
            agentSignNumOrg += report.getAgentSignNum() ==null ? 0 : report.getAgentSignNum();
            agentServiceMoneyOrg += report.getAgentServiceMoney() ==null ? 0D : report.getAgentServiceMoney();
            advanceAgentServiceMoneyOrg += report.getAdvanceAgentServiceMoney() ==null ? 0D : report.getAdvanceAgentServiceMoney();
            okAgentServiceMoneyOrg += report.getOkAgentServiceMoney() ==null ? 0D : report.getOkAgentServiceMoney();
            loanSignNumOrg += report.getLoanSignNum() ==null ? 0 : report.getLoanSignNum();
            loanServiceMoneyOrg += report.getLoanServiceMoney() ==null ? 0D : report.getLoanServiceMoney();
            loanChannelMoneyOrg += report.getLoanChannelMoney() ==null ? 0D : report.getLoanChannelMoney();
            advanceLoanServiceMoneyOrg += report.getAdvanceLoanServiceMoney() ==null ? 0D : report.getAdvanceLoanServiceMoney();
            okLoanServiceMoneyOrg += report.getOkLoanServiceMoney() ==null ? 0D : report.getOkLoanServiceMoney();
            destroyCaseNumOrg += report.getDestroyCaseNum() ==null ? 0 : report.getDestroyCaseNum();
            newCaseNumOrg += report.getNewCaseNum() ==null ? 0 : report.getNewCaseNum();
            newServiceMoneyOrg += report.getNewServiceMoney() ==null ? 0D : report.getNewServiceMoney();
            totalSignNumOrg += report.getTotalSignNum() ==null ? 0 : report.getTotalSignNum();
            totalServiceMoneyOrg += report.getTotalServiceMoney() ==null ? 0D : report.getTotalServiceMoney();
            totalOkServiceMoneyOrg += report.getTotalOkServiceMoney() ==null ? 0D : report.getTotalOkServiceMoney();
            totalAdServiceMoneyOrg += report.getTotalAdServiceMoney() ==null ? 0D : report.getTotalAdServiceMoney();
            caseInputNumOrg += report.getCaseInputNum() ==null ? 0 : report.getCaseInputNum();
            caseTargetNumOrg += report.getCaseTargetNum() ==null ? 0 : report.getCaseTargetNum();
            casePotentialNumOrg += report.getCasePotentialNum() ==null ? 0 : report.getCasePotentialNum();
            caseIntentionNumOrg += report.getCaseIntentionNum() ==null ? 0 : report.getCaseIntentionNum();
            caseSignNumOrg += report.getCaseSignNum() ==null ? 0 : report.getCaseSignNum();
            //机构总计
            if (i > 0 && report.getOrgId()!=null && list.get(i - 1).getOrgId()!=null) {
                if (!report.getOrgId().equals(list.get(i - 1).getOrgId())) {
                    CasePersonalDayReportDto reportDto = new CasePersonalDayReportDto();
                    reportDto.setOrgId(list.get(i - 1).getOrgId());
                    reportDto.setOrgName(list.get(i - 1).getOrgName());
                    reportDto.setDirectorName("合计");
                    reportDto.setDirectorId(null);
                    reportDto.setSalesmanId(null);
                    reportDto.setSalesmanName(null);

                    reportDto.setAgentSignNum(agentSignNumOrg - (report.getAgentSignNum() ==null ? 0 : report.getAgentSignNum()));
                    reportDto.setAgentServiceMoney(agentServiceMoneyOrg - (report.getAgentServiceMoney() ==null ? 0D : report.getAgentServiceMoney()));
                    reportDto.setAdvanceAgentServiceMoney(advanceAgentServiceMoneyOrg - (report.getAdvanceAgentServiceMoney() ==null ? 0D : report.getAdvanceAgentServiceMoney()));
                    reportDto.setOkAgentServiceMoney(okAgentServiceMoneyOrg - (report.getOkAgentServiceMoney() ==null ? 0D : report.getOkAgentServiceMoney()));
                    reportDto.setLoanSignNum(loanSignNumOrg - (report.getLoanSignNum() ==null ? 0 : report.getLoanSignNum()));
                    reportDto.setLoanServiceMoney(loanServiceMoneyOrg - (report.getLoanServiceMoney() ==null ? 0D : report.getLoanServiceMoney()));
                    reportDto.setLoanChannelMoney(loanChannelMoneyOrg - (report.getLoanChannelMoney() ==null ? 0D : report.getLoanChannelMoney()));
                    reportDto.setAdvanceLoanServiceMoney(advanceLoanServiceMoneyOrg - (report.getAdvanceLoanServiceMoney() ==null ? 0D : report.getAdvanceLoanServiceMoney()));
                    reportDto.setOkLoanServiceMoney(okLoanServiceMoneyOrg - (report.getOkLoanServiceMoney() ==null ? 0D : report.getOkLoanServiceMoney()));
                    reportDto.setDestroyCaseNum(destroyCaseNumOrg - (report.getDestroyCaseNum() ==null ? 0 : report.getDestroyCaseNum()));
                    reportDto.setNewCaseNum(newCaseNumOrg - (report.getNewCaseNum() ==null ? 0 : report.getNewCaseNum()));
                    reportDto.setNewServiceMoney(newServiceMoneyOrg - (report.getNewServiceMoney() ==null ? 0D : report.getNewServiceMoney()));
                    reportDto.setTotalSignNum(totalSignNumOrg - (report.getTotalSignNum() ==null ? 0 : report.getTotalSignNum()));
                    reportDto.setTotalServiceMoney(totalServiceMoneyOrg - (report.getTotalServiceMoney() ==null ? 0D : report.getTotalServiceMoney()));
                    reportDto.setTotalOkServiceMoney(totalOkServiceMoneyOrg - (report.getTotalOkServiceMoney() ==null ? 0D : report.getTotalOkServiceMoney()));
                    reportDto.setTotalAdServiceMoney(totalAdServiceMoneyOrg - (report.getTotalAdServiceMoney() ==null ? 0D : report.getTotalAdServiceMoney()));
                    reportDto.setCaseInputNum(caseInputNumOrg - (report.getCaseInputNum() ==null ? 0 : report.getCaseInputNum()));
                    reportDto.setCaseTargetNum(caseTargetNumOrg - (report.getCaseTargetNum() ==null ? 0 : report.getCaseTargetNum()));
                    reportDto.setCasePotentialNum(casePotentialNumOrg - (report.getCasePotentialNum() ==null ? 0 : report.getCasePotentialNum()));
                    reportDto.setCaseIntentionNum(caseIntentionNumOrg - (report.getCaseIntentionNum() ==null ? 0 : report.getCaseIntentionNum()));
                    reportDto.setCaseSignNum(caseSignNumOrg - (report.getCaseSignNum() ==null ? 0 : report.getCaseSignNum()));

                    newList.add(reportDto);
                    orgList.add(reportDto);
                    if(i != list.size() - 1) {
                        agentSignNumOrg = report.getAgentSignNum() ==null ? 0 : report.getAgentSignNum();
                        agentServiceMoneyOrg = report.getAgentServiceMoney() ==null ? 0D : report.getAgentServiceMoney();
                        advanceAgentServiceMoneyOrg = report.getAdvanceAgentServiceMoney() ==null ? 0D : report.getAdvanceAgentServiceMoney();
                        okAgentServiceMoneyOrg = report.getOkAgentServiceMoney() ==null ? 0D : report.getOkAgentServiceMoney();
                        loanSignNumOrg = report.getLoanSignNum() ==null ? 0 : report.getLoanSignNum();
                        loanServiceMoneyOrg = report.getLoanServiceMoney() ==null ? 0D : report.getLoanServiceMoney();
                        loanChannelMoneyOrg = report.getLoanChannelMoney() ==null ? 0D : report.getLoanChannelMoney();
                        advanceLoanServiceMoneyOrg = report.getAdvanceLoanServiceMoney() ==null ? 0D : report.getAdvanceLoanServiceMoney();
                        okLoanServiceMoneyOrg = report.getOkLoanServiceMoney() ==null ? 0D : report.getOkLoanServiceMoney();
                        destroyCaseNumOrg = report.getDestroyCaseNum() ==null ? 0 : report.getDestroyCaseNum();
                        newCaseNumOrg = report.getNewCaseNum() ==null ? 0 : report.getNewCaseNum();
                        newServiceMoneyOrg = report.getNewServiceMoney() ==null ? 0D : report.getNewServiceMoney();
                        totalSignNumOrg = report.getTotalSignNum() ==null ? 0 : report.getTotalSignNum();
                        totalServiceMoneyOrg = report.getTotalServiceMoney() ==null ? 0D : report.getTotalServiceMoney();
                        totalOkServiceMoneyOrg = report.getTotalOkServiceMoney() ==null ? 0D : report.getTotalOkServiceMoney();
                        totalAdServiceMoneyOrg = report.getTotalAdServiceMoney() ==null ? 0D : report.getTotalAdServiceMoney();
                        caseInputNumOrg = report.getCaseInputNum() ==null ? 0 : report.getCaseInputNum();
                        caseTargetNumOrg = report.getCaseTargetNum() ==null ? 0 : report.getCaseTargetNum();
                        casePotentialNumOrg = report.getCasePotentialNum() ==null ? 0 : report.getCasePotentialNum();
                        caseIntentionNumOrg = report.getCaseIntentionNum() ==null ? 0 : report.getCaseIntentionNum();
                        caseSignNumOrg = report.getCaseSignNum() ==null ? 0 : report.getCaseSignNum();
                    }
                }
            }
            newList.add(dto);
            //最后一条数据
            if(list.size() > 1 && i == list.size() - 1){
                CasePersonalDayReportDto LastReportDto = new CasePersonalDayReportDto();
                LastReportDto.setOrgId(report.getOrgId());
                LastReportDto.setOrgName(report.getOrgName());
                LastReportDto.setDirectorName(report.getDirectorName());
                LastReportDto.setDirectorId(report.getDirectorId());
                LastReportDto.setSalesmanId(null);
                LastReportDto.setSalesmanName("小计");
                //判断最后一条数据和上一条数据是否同一主管下
                if (report.getDirectorId().equals(list.get(i - 1).getDirectorId())) {
                    LastReportDto.setAgentSignNum(agentSignNum);
                    LastReportDto.setAgentServiceMoney(agentServiceMoney);
                    LastReportDto.setAdvanceAgentServiceMoney(advanceAgentServiceMoney);
                    LastReportDto.setOkAgentServiceMoney(okAgentServiceMoney);
                    LastReportDto.setLoanSignNum(loanSignNum);
                    LastReportDto.setLoanServiceMoney(loanServiceMoney);
                    LastReportDto.setLoanChannelMoney(loanChannelMoney);
                    LastReportDto.setAdvanceLoanServiceMoney(advanceLoanServiceMoney);
                    LastReportDto.setOkLoanServiceMoney(okLoanServiceMoney);
                    LastReportDto.setDestroyCaseNum(destroyCaseNum);
                    LastReportDto.setNewCaseNum(newCaseNum);
                    LastReportDto.setNewServiceMoney(newServiceMoney);
                    LastReportDto.setTotalSignNum(totalSignNum);
                    LastReportDto.setTotalServiceMoney(totalServiceMoney);
                    LastReportDto.setTotalOkServiceMoney(totalOkServiceMoney);
                    LastReportDto.setTotalAdServiceMoney(totalAdServiceMoney);
                    LastReportDto.setCaseInputNum(caseInputNum);
                    LastReportDto.setCaseTargetNum(caseTargetNum);
                    LastReportDto.setCasePotentialNum(casePotentialNum);
                    LastReportDto.setCaseIntentionNum(caseIntentionNum);
                    LastReportDto.setCaseSignNum(caseSignNum);

                }else{
                    LastReportDto.setAgentSignNum(report.getAgentSignNum());
                    LastReportDto.setAgentServiceMoney(report.getAgentServiceMoney());
                    LastReportDto.setAdvanceAgentServiceMoney(report.getAdvanceAgentServiceMoney());
                    LastReportDto.setOkAgentServiceMoney(report.getOkAgentServiceMoney());
                    LastReportDto.setLoanSignNum(report.getLoanSignNum());
                    LastReportDto.setLoanServiceMoney(report.getLoanServiceMoney());
                    LastReportDto.setLoanChannelMoney(report.getLoanChannelMoney());
                    LastReportDto.setAdvanceLoanServiceMoney(report.getAdvanceLoanServiceMoney());
                    LastReportDto.setOkLoanServiceMoney(report.getOkLoanServiceMoney());
                    LastReportDto.setDestroyCaseNum(report.getDestroyCaseNum());
                    LastReportDto.setNewCaseNum(report.getNewCaseNum());
                    LastReportDto.setNewServiceMoney(report.getNewServiceMoney());
                    LastReportDto.setTotalSignNum(report.getTotalSignNum());
                    LastReportDto.setTotalServiceMoney(report.getTotalServiceMoney());
                    LastReportDto.setTotalOkServiceMoney(report.getTotalOkServiceMoney());
                    LastReportDto.setTotalAdServiceMoney(report.getTotalAdServiceMoney());
                    LastReportDto.setCaseInputNum(report.getCaseInputNum());
                    LastReportDto.setCaseTargetNum(report.getCaseTargetNum());
                    LastReportDto.setCasePotentialNum(report.getCasePotentialNum());
                    LastReportDto.setCaseIntentionNum(report.getCaseIntentionNum());
                    LastReportDto.setCaseSignNum(report.getCaseSignNum());
                }
                newList.add(LastReportDto);

                //机构数据
                CasePersonalDayReportDto LastOrgReportDto = new CasePersonalDayReportDto();
                LastOrgReportDto.setOrgId(report.getOrgId());
                LastOrgReportDto.setOrgName(report.getOrgName());
                LastOrgReportDto.setDirectorName("合计");
                LastOrgReportDto.setDirectorId(null);
                LastOrgReportDto.setSalesmanId(null);
                LastOrgReportDto.setSalesmanName(null);
                //判断最后一条数据和上一条数据是否同一机构下
                if (report.getOrgId().equals(list.get(i - 1).getOrgId())) {
                    LastOrgReportDto.setAgentSignNum(agentSignNumOrg);
                    LastOrgReportDto.setAgentServiceMoney(agentServiceMoneyOrg);
                    LastOrgReportDto.setAdvanceAgentServiceMoney(advanceAgentServiceMoneyOrg);
                    LastOrgReportDto.setOkAgentServiceMoney(okAgentServiceMoneyOrg);
                    LastOrgReportDto.setLoanSignNum(loanSignNumOrg);
                    LastOrgReportDto.setLoanServiceMoney(loanServiceMoneyOrg);
                    LastOrgReportDto.setLoanChannelMoney(loanChannelMoneyOrg);
                    LastOrgReportDto.setAdvanceLoanServiceMoney(advanceLoanServiceMoneyOrg);
                    LastOrgReportDto.setOkLoanServiceMoney(okLoanServiceMoneyOrg);
                    LastOrgReportDto.setDestroyCaseNum(destroyCaseNumOrg);
                    LastOrgReportDto.setNewCaseNum(newCaseNumOrg);
                    LastOrgReportDto.setNewServiceMoney(newServiceMoneyOrg);
                    LastOrgReportDto.setTotalSignNum(totalSignNumOrg);
                    LastOrgReportDto.setTotalServiceMoney(totalServiceMoneyOrg);
                    LastOrgReportDto.setTotalOkServiceMoney(totalOkServiceMoneyOrg);
                    LastOrgReportDto.setTotalAdServiceMoney(totalAdServiceMoneyOrg);
                    LastOrgReportDto.setCaseInputNum(caseInputNumOrg);
                    LastOrgReportDto.setCaseTargetNum(caseTargetNumOrg);
                    LastOrgReportDto.setCasePotentialNum(casePotentialNumOrg);
                    LastOrgReportDto.setCaseIntentionNum(caseIntentionNumOrg);
                    LastOrgReportDto.setCaseSignNum(caseSignNumOrg);
                }else{
                    LastOrgReportDto.setAgentSignNum(report.getAgentSignNum());
                    LastOrgReportDto.setAgentServiceMoney(report.getAgentServiceMoney());
                    LastOrgReportDto.setAdvanceAgentServiceMoney(report.getAdvanceAgentServiceMoney());
                    LastOrgReportDto.setOkAgentServiceMoney(report.getOkAgentServiceMoney());
                    LastOrgReportDto.setLoanSignNum(report.getLoanSignNum());
                    LastOrgReportDto.setLoanServiceMoney(report.getLoanServiceMoney());
                    LastOrgReportDto.setLoanChannelMoney(report.getLoanChannelMoney());
                    LastOrgReportDto.setAdvanceLoanServiceMoney(report.getAdvanceLoanServiceMoney());
                    LastOrgReportDto.setOkLoanServiceMoney(report.getOkLoanServiceMoney());
                    LastOrgReportDto.setDestroyCaseNum(report.getDestroyCaseNum());
                    LastOrgReportDto.setNewCaseNum(report.getNewCaseNum());
                    LastOrgReportDto.setNewServiceMoney(report.getNewServiceMoney());
                    LastOrgReportDto.setTotalSignNum(report.getTotalSignNum());
                    LastOrgReportDto.setTotalServiceMoney(report.getTotalServiceMoney());
                    LastOrgReportDto.setTotalOkServiceMoney(report.getTotalOkServiceMoney());
                    LastOrgReportDto.setTotalAdServiceMoney(report.getTotalAdServiceMoney());
                    LastOrgReportDto.setCaseInputNum(report.getCaseInputNum());
                    LastOrgReportDto.setCaseTargetNum(report.getCaseTargetNum());
                    LastOrgReportDto.setCasePotentialNum(report.getCasePotentialNum());
                    LastOrgReportDto.setCaseIntentionNum(report.getCaseIntentionNum());
                    LastOrgReportDto.setCaseSignNum(report.getCaseSignNum());
                }
                newList.add(LastOrgReportDto);
                orgList.add(LastOrgReportDto);
            }
        }

        //总计数据
        CasePersonalDayReportDto LastAllReportDto = new CasePersonalDayReportDto();
        LastAllReportDto.setOrgId(null);
        LastAllReportDto.setOrgName("总计");
        LastAllReportDto.setDirectorName(null);
        LastAllReportDto.setDirectorId(null);
        LastAllReportDto.setSalesmanId(null);
        LastAllReportDto.setSalesmanName(null);

        LastAllReportDto.setAgentSignNum(agentSignNumAll);
        LastAllReportDto.setAgentServiceMoney(agentServiceMoneyAll);
        LastAllReportDto.setAdvanceAgentServiceMoney(advanceAgentServiceMoneyAll);
        LastAllReportDto.setOkAgentServiceMoney(okAgentServiceMoneyAll);
        LastAllReportDto.setLoanSignNum(loanSignNumAll);
        LastAllReportDto.setLoanServiceMoney(loanServiceMoneyAll);
        LastAllReportDto.setLoanChannelMoney(loanChannelMoneyAll);
        LastAllReportDto.setAdvanceLoanServiceMoney(advanceLoanServiceMoneyAll);
        LastAllReportDto.setOkLoanServiceMoney(okLoanServiceMoneyAll);
        LastAllReportDto.setDestroyCaseNum(destroyCaseNumAll);
        LastAllReportDto.setNewCaseNum(newCaseNumAll);
        LastAllReportDto.setNewServiceMoney(newServiceMoneyAll);
        LastAllReportDto.setTotalSignNum(totalSignNumAll);
        LastAllReportDto.setTotalServiceMoney(totalServiceMoneyAll);
        LastAllReportDto.setTotalOkServiceMoney(totalOkServiceMoneyAll);
        LastAllReportDto.setTotalAdServiceMoney(totalAdServiceMoneyAll);
        LastAllReportDto.setCaseInputNum(caseInputNumAll);
        LastAllReportDto.setCaseTargetNum(caseTargetNumAll);
        LastAllReportDto.setCasePotentialNum(casePotentialNumAll);
        LastAllReportDto.setCaseIntentionNum(caseIntentionNumAll);
        LastAllReportDto.setCaseSignNum(caseSignNumAll);
        newList.add(LastAllReportDto);
        orgList.add(LastAllReportDto);

        String isSum = apiReq.getString("isSum");
        if("1".equals(isSum)){
            return orgList;
        }else{
            return newList;
        }
    }
}
