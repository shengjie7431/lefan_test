package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lefancrm.apicenter.backendapi.BackendFinancialBudgetApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@ApiService(descript = "预算方案apo")
public class BackendFinancialBudgetApiImpl extends BaseServiceImpl implements BackendFinancialBudgetApi {

    @Autowired
    private FinancialBudgetMapper financialBudgetMapper;
    @Autowired
    private FinancialBudgetInfoMapper financialBudgetInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private StaffBudgetCompanyMapper staffBudgetCompanyMapper;
    @Autowired
    private StaffOrganMapper staffOrganMapper;
    @Autowired
    private FinancialCostTypeMapper financialCostTypeMapper;

    /**
     * 预算主表列表
     *
     * @param apiReq
     * @return
     */
    @Override
    @ApiMethod(descript = "", value = "backend-financial-budget-list")
    public ApiResponse budgetList(ApiRequest apiReq) {

        List<FinancialBudget> financialBudgetList = financialBudgetMapper.budgetList(apiReq);
        Integer count = financialBudgetMapper.budgetListSize(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, financialBudgetList);
    }

    /**
     * 预算详情列表
     *
     * @param apiReq
     * @return
     */
    @Override
    @ApiMethod(descript = "", value = "backend-financial-budget-info-list")
    public ApiResponse budgetInfoList(ApiRequest apiReq) {

        Object isPage = apiReq.get("isPage");
        if (isPage == null) { // 分页
            this.setBackendPageSize(apiReq);
        }
        List<FinancialBudgetInfo> financialBudgetInfoList = financialBudgetInfoMapper.selectByBudgetId(apiReq);
        Integer count = financialBudgetInfoMapper.selectByBudgetIdSize(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, financialBudgetInfoList);
    }

    /**
     * 预算详情信息
     *
     * @param apiReq
     * @return
     */
    @Override
    @ApiMethod(descript = "", value = "backend-financial-budget-info-one")
    public ApiResponse budgetInfoOne(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        FinancialBudgetInfo financialBudgetInfo = financialBudgetInfoMapper.selectByPrimaryKey(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, financialBudgetInfo);
    }

    /**
     * 新增预算方案
     *
     * @param apiReq
     * @return
     */
    @Override
    @ApiMethod(descript = "", value = "backend-financial-budget-add")
    public ApiResponse budgetAdd(ApiRequest apiReq) {
        Long currentUserId = getCurrentUserId(apiReq);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        Date date = new Date();
        String year = apiReq.getString("year");
        FinancialBudget financialBudget = new FinancialBudget();
        financialBudget.setYear(year);
        financialBudget.setCreateBy(userInfo.getUserName());
        financialBudget.setCreateTime(date);
        financialBudget.setUpdateBy(userInfo.getUserName());
        financialBudget.setUpdateTime(date);
        financialBudgetMapper.insert(financialBudget);

        List<FinancialBudgetInfo> financialBudgetInfoList = financialBudgetInfoMapper.selectData();
        financialBudgetInfoList.forEach(e -> {
            e.setBudgetId(financialBudget.getId());
            e.setYear(financialBudget.getYear());
        });

        financialBudgetInfoMapper.insertBatchData(financialBudgetInfoList, userInfo);

        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 预算方案删除
     *
     * @param apiReq
     * @return
     */
    @Override
    @ApiMethod(descript = "删除", value = "backend-financial-budget-delete")
    public ApiResponse budgetDelete(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        FinancialBudget financialBudget = financialBudgetMapper.selectByPrimaryKey(id);
        if (financialBudget != null) {
            financialBudgetMapper.deleteByPrimaryKey(financialBudget.getId());
            financialBudgetInfoMapper.deleteByBudgetId(financialBudget.getId());
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }


    /**
     * 预算方案编辑
     *
     * @param apiReq
     * @return
     */
    @Override
    @ApiMethod(descript = "编辑", value = "backend-financial-budget-edit")
    public ApiResponse budgetEdit(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        FinancialBudgetInfo financialBudgetInfo = financialBudgetInfoMapper.selectByPrimaryKey(id);
        if (financialBudgetInfo != null) {
            financialBudgetInfo.setJanuary(apiReq.getDouble("january"));
            financialBudgetInfo.setFebruary(apiReq.getDouble("february"));
            financialBudgetInfo.setMarch(apiReq.getDouble("march"));
            financialBudgetInfo.setApril(apiReq.getDouble("april"));
            financialBudgetInfo.setMay(apiReq.getDouble("may"));
            financialBudgetInfo.setJune(apiReq.getDouble("june"));
            financialBudgetInfo.setJuly(apiReq.getDouble("july"));
            financialBudgetInfo.setAugust(apiReq.getDouble("august"));
            financialBudgetInfo.setSeptember(apiReq.getDouble("september"));
            financialBudgetInfo.setOctober(apiReq.getDouble("october"));
            financialBudgetInfo.setNovember(apiReq.getDouble("november"));
            financialBudgetInfo.setDecember(apiReq.getDouble("december"));
            financialBudgetInfo.setYearMoney(apiReq.getDouble("yearMoney"));
            financialBudgetInfo.setUpdateTime(new Date());
            Long currentUserId = getCurrentUserId(apiReq);
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            if (userInfo != null) {
                financialBudgetInfo.setUpdateBy(userInfo.getUserName());
            }
            financialBudgetInfoMapper.updateByPrimaryKey(financialBudgetInfo);

            update();
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 详情页数据
     *
     * @param apiReq
     * @return
     */
    @Override
    @ApiMethod(descript = "", value = "backend-financial-budget-page-data")
    public ApiResponse budgetPageData(ApiRequest apiReq) {
        JSONObject resultObj = new JSONObject();
        Map emptyMap = Collections.EMPTY_MAP;
        //预算归属公司
        List<StaffBudgetCompany> staffBudgetCompanyList = staffBudgetCompanyMapper.list(emptyMap);
        //机构
        List<StaffOrgan> organList = staffOrganMapper.list(emptyMap);
        //费用类型
        List<FinancialCostType> costTypeList = financialCostTypeMapper.list(emptyMap);
        resultObj.put("staffBudgetCompanyList", staffBudgetCompanyList);
        resultObj.put("organList", organList);
        resultObj.put("costTypeList", costTypeList);
        return new ApiResponse(ApiMsgEnum.SUCCESS, 0, JSON.toJSONString(resultObj));
    }


    /**
     * 刷新
     *
     * @param apiReq
     * @return
     */
    @Override
    @ApiMethod(descript = "", value = "backend-financial-budget-refresh")
    public ApiResponse budgetRefresh(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        FinancialBudget financialBudget = financialBudgetMapper.selectByPrimaryKey(id);
        if (financialBudget != null) {
            Long currentUserId = getCurrentUserId(apiReq);
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            Date date = new Date();
            //获取所有没有添加的费用类别或者是机构信息
            List<FinancialBudgetInfo> financialBudgetInfoList = financialBudgetInfoMapper.selectNoInsertDataByBudgetId(financialBudget.getId());
            for (FinancialBudgetInfo financialBudgetInfo : financialBudgetInfoList) {
                financialBudgetInfo.setYear(financialBudget.getYear());
                financialBudgetInfo.setBudgetId(financialBudget.getId());
                financialBudgetInfo.setCreateTime(date);
                financialBudgetInfo.setCreateBy(userInfo.getUserName());
                financialBudgetInfo.setUpdateTime(date);
                financialBudgetInfo.setUpdateBy(userInfo.getUserName());
                financialBudgetInfoMapper.insert(financialBudgetInfo);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 预算方案导入
     *
     * @param apiReq
     * @return
     */
    @Override
    @ApiMethod(descript = "", value = "backend-financial-budget-import")
    public ApiResponse budgetImport(ApiRequest apiReq) {
        String str = apiReq.getString("infoDtoList");
        List<FinancialBudgetInfo> infoDtoList = JSONArray.parseArray(str, FinancialBudgetInfo.class);
        for (FinancialBudgetInfo e : infoDtoList) {
            double sum = getValue(e.getJanuary()) + getValue(e.getFebruary()) + getValue(e.getMarch()) + getValue(e.getApril()) + getValue(e.getMay()) + getValue(e.getJune()) + getValue(e.getJuly()) + getValue(e.getAugust()) + getValue(e.getSeptember()) + getValue(e.getOctober()) + getValue(e.getNovember()) + getValue(e.getDecember());
            e.setYearMoney(sum);
            financialBudgetInfoMapper.updateByPrimaryKeySelective(e);
        }
        update();

        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }


    /**
     * 报表数据
     *
     * @param apiReq
     * @return
     */
    @Override
    @ApiMethod(descript = "", value = "backend-financial-budget-report-data")
    public ApiResponse budgetReportData(ApiRequest apiReq) {

        HashMap<String, Object> paramMap = new HashMap<>();
        String searchCode = apiReq.getString("searchCode");

        if ("type1".equals(searchCode)) {
            List<FinancialBudget> financialBudgetList = financialBudgetMapper.budgetList(apiReq);
            for (int i = 0; i < financialBudgetList.size(); i++) {
                FinancialBudget financialBudget = financialBudgetList.get(i);
                for (int j = 1; j <= 12; j++) {
                    paramMap.put("date" + j, financialBudget.getYear() + "-" + (j < 10 ? "0" + j : String.valueOf(j)));
                }
                double sum = 0;
                Map<String, Double> stringDoubleMap = financialBudgetMapper.selectEveryUseMoney(paramMap);
                if (stringDoubleMap != null) {
                    financialBudget.setJanuaryUes(stringDoubleMap.get("money1"));
                    financialBudget.setFebruaryUes(stringDoubleMap.get("money2"));
                    financialBudget.setMarchUes(stringDoubleMap.get("money3"));
                    financialBudget.setAprilUes(stringDoubleMap.get("money4"));
                    financialBudget.setMayUes(stringDoubleMap.get("money5"));
                    financialBudget.setJuneUes(stringDoubleMap.get("money6"));
                    financialBudget.setJulyUes(stringDoubleMap.get("money7"));
                    financialBudget.setAugustUes(stringDoubleMap.get("money8"));
                    financialBudget.setSeptemberUes(stringDoubleMap.get("money9"));
                    financialBudget.setOctoberUes(stringDoubleMap.get("money10"));
                    financialBudget.setNovemberUes(stringDoubleMap.get("money11"));
                    financialBudget.setDecemberUes(stringDoubleMap.get("money12"));
                    sum = stringDoubleMap.values().stream().mapToDouble(v -> v).sum();
                }
                financialBudget.setUseYearMoney(sum);
                financialBudget.setSurplusYearMoney(Optional.ofNullable(financialBudget.getYearMoney()).orElse(0d) - sum);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, financialBudgetList);
        }

        Long budgetId = apiReq.getLong("budgetId");
        FinancialBudget budget = financialBudgetMapper.selectByPrimaryKey(budgetId);
        if (budget == null) return new ApiResponse(ApiMsgEnum.FAIL);
        for (int j = 1; j <= 12; j++) {
            paramMap.put("date" + j, budget.getYear() + "-" + (j < 10 ? "0" + j : String.valueOf(j)));
        }

        if (apiReq.get("pageFlag") == null){
            this.setBackendPageSize(apiReq);
        }

        if ("type2".equals(searchCode)) {
            List<FinancialBudgetInfo> financialBudgetInfoList = financialBudgetInfoMapper.selectDataByGroup(apiReq);
            Integer count = financialBudgetInfoMapper.selectDataByGroupCount(apiReq);
            for (FinancialBudgetInfo financialBudgetInfo : financialBudgetInfoList) {
                Long orgId = financialBudgetInfo.getAscriptionOrganId();
                paramMap.put("orgId", orgId);
                double sum = 0;
                Map<String, Double> stringDoubleMap = financialBudgetMapper.selectEveryUseMoney(paramMap);
                if (stringDoubleMap != null) {
                    financialBudgetInfo.setJanuaryUes(stringDoubleMap.get("money1"));
                    financialBudgetInfo.setFebruaryUes(stringDoubleMap.get("money2"));
                    financialBudgetInfo.setMarchUes(stringDoubleMap.get("money3"));
                    financialBudgetInfo.setAprilUes(stringDoubleMap.get("money4"));
                    financialBudgetInfo.setMayUes(stringDoubleMap.get("money5"));
                    financialBudgetInfo.setJuneUes(stringDoubleMap.get("money6"));
                    financialBudgetInfo.setJulyUes(stringDoubleMap.get("money7"));
                    financialBudgetInfo.setAugustUes(stringDoubleMap.get("money8"));
                    financialBudgetInfo.setSeptemberUes(stringDoubleMap.get("money9"));
                    financialBudgetInfo.setOctoberUes(stringDoubleMap.get("money10"));
                    financialBudgetInfo.setNovemberUes(stringDoubleMap.get("money11"));
                    financialBudgetInfo.setDecemberUes(stringDoubleMap.get("money12"));
                    sum = stringDoubleMap.values().stream().mapToDouble(v -> v).sum();
                }
                financialBudgetInfo.setUseYearMoney(sum);
                financialBudgetInfo.setSurplusYearMoney(Optional.ofNullable(financialBudgetInfo.getYearMoney()).orElse(0d) - sum);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, count, financialBudgetInfoList);
        }

        if ("type3".equals(searchCode)) {
            List<FinancialBudgetInfo> financialBudgetInfoList = financialBudgetInfoMapper.selectByBudgetId(apiReq);
            Integer count = financialBudgetInfoMapper.selectByBudgetIdSize(apiReq);
            for (FinancialBudgetInfo financialBudgetInfo : financialBudgetInfoList) {
                Long orgId = financialBudgetInfo.getAscriptionOrganId();
                Long costTypeId = financialBudgetInfo.getCostTypeId();
                Integer costTypeSource = financialBudgetInfo.getCostTypeSource();
                paramMap.put("orgId", orgId);
                paramMap.put("costTypeId", costTypeId);
                paramMap.put("source", costTypeSource);
                if (costTypeId == null || costTypeSource == null) continue;
                double sum = 0;
                Map<String, Double> stringDoubleMap = financialBudgetMapper.selectEveryCostTypeUseMoney(paramMap);
                if (stringDoubleMap != null) {
                    financialBudgetInfo.setJanuaryUes(stringDoubleMap.get("money1"));
                    financialBudgetInfo.setFebruaryUes(stringDoubleMap.get("money2"));
                    financialBudgetInfo.setMarchUes(stringDoubleMap.get("money3"));
                    financialBudgetInfo.setAprilUes(stringDoubleMap.get("money4"));
                    financialBudgetInfo.setMayUes(stringDoubleMap.get("money5"));
                    financialBudgetInfo.setJuneUes(stringDoubleMap.get("money6"));
                    financialBudgetInfo.setJulyUes(stringDoubleMap.get("money7"));
                    financialBudgetInfo.setAugustUes(stringDoubleMap.get("money8"));
                    financialBudgetInfo.setSeptemberUes(stringDoubleMap.get("money9"));
                    financialBudgetInfo.setOctoberUes(stringDoubleMap.get("money10"));
                    financialBudgetInfo.setNovemberUes(stringDoubleMap.get("money11"));
                    financialBudgetInfo.setDecemberUes(stringDoubleMap.get("money12"));
                    sum = stringDoubleMap.values().stream().mapToDouble(this::getValue).sum();
                }
                financialBudgetInfo.setUseYearMoney(sum);
                financialBudgetInfo.setSurplusYearMoney(Optional.ofNullable(financialBudgetInfo.getYearMoney()).orElse(0d) - sum);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, count, financialBudgetInfoList);
        }

        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    public void update() {
        List<FinancialBudgetInfo> list = financialBudgetInfoMapper.list(Collections.emptyMap());
        Map<Long, List<FinancialBudgetInfo>> collect = list.stream().collect(Collectors.groupingBy(FinancialBudgetInfo::getBudgetId));
        collect.forEach((k, v) -> {
            FinancialBudget financialBudget = financialBudgetMapper.selectByPrimaryKey(k);
            Supplier<Stream<FinancialBudgetInfo>> financialBudgetInfoStream = () -> v.stream().filter(e -> k.equals(e.getBudgetId()));
            double sum1 = financialBudgetInfoStream.get().mapToDouble(e -> getValue(e.getJanuary())).sum();
            double sum2 = financialBudgetInfoStream.get().mapToDouble(e -> getValue(e.getFebruary())).sum();
            double sum3 = financialBudgetInfoStream.get().mapToDouble(e -> getValue(e.getMarch())).sum();
            double sum4 = financialBudgetInfoStream.get().mapToDouble(e -> getValue(e.getApril())).sum();
            double sum5 = financialBudgetInfoStream.get().mapToDouble(e -> getValue(e.getMay())).sum();
            double sum6 = financialBudgetInfoStream.get().mapToDouble(e -> getValue(e.getJune())).sum();
            double sum7 = financialBudgetInfoStream.get().mapToDouble(e -> getValue(e.getJuly())).sum();
            double sum8 = financialBudgetInfoStream.get().mapToDouble(e -> getValue(e.getAugust())).sum();
            double sum9 = financialBudgetInfoStream.get().mapToDouble(e -> getValue(e.getSeptember())).sum();
            double sum10 = financialBudgetInfoStream.get().mapToDouble(e -> getValue(e.getOctober())).sum();
            double sum11 = financialBudgetInfoStream.get().mapToDouble(e -> getValue(e.getNovember())).sum();
            double sum12 = financialBudgetInfoStream.get().mapToDouble(e -> getValue(e.getDecember())).sum();
            financialBudget.setJanuary(sum1);
            financialBudget.setFebruary(sum2);
            financialBudget.setMarch(sum3);
            financialBudget.setApril(sum4);
            financialBudget.setMay(sum5);
            financialBudget.setJune(sum6);
            financialBudget.setJuly(sum7);
            financialBudget.setAugust(sum8);
            financialBudget.setSeptember(sum9);
            financialBudget.setOctober(sum10);
            financialBudget.setNovember(sum11);
            financialBudget.setDecember(sum12);
            double sum = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10 + sum11 + sum12;
            financialBudget.setYearMoney(sum);
            financialBudgetMapper.updateByPrimaryKey(financialBudget);
        });
    }

    private double getValue(Double d) {
        return d == null ? 0 : d;
    }
}
