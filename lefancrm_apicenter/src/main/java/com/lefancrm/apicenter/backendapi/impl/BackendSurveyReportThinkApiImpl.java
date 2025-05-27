package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyReportThinkApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.think.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
@ApiService(descript = "经营分析报表相关API")
public class BackendSurveyReportThinkApiImpl extends BaseServiceImpl implements BackendSurveyReportThinkApi {
    @Autowired
    private CommonEnumMapper commonEnumMapper;
    @Autowired
    private StaffOrganMapper staffOrganMapper;
    @Autowired
    private SurveyBusinessReportMapper surveyBusinessReportMapper;

    @Autowired
    private StaffOrganProductMapper staffOrganProductMapper;
    @Autowired
    private StaffPersonnelInfoMapper staffPersonnelInfoMapper;
    @Autowired
    private ThinkDataOrgProductMapper thinkDataOrgProductMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private BillingApplyCorporationMapper billingApplyCorporationMapper;

    private final int BS_PROID = 2,HZ_PROID = 230,BS_SHARP = 257;//保司  互助  反欺诈开票产品ID

    @ApiMethod(needLogin = false,descript = "获取报表数据",value = "get-data-think-report")
    @Override
    public ApiResponse getData(ApiRequest apiRequest) {
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //产品经营分析报表 proThink   机构经营分析报表(业务) orgThinkBus   机构经营分析报表(职能) orgThinkPost   机构经营分析报表(业务管理) orgThinkBusManager
        String dataType = apiRequest.getString("dataType");
        String searchType = apiRequest.getString("searchType");//上月  当月   本季度   本年度
        StringBuilder builderStart = new StringBuilder("");
        StringBuilder builderEnd = new StringBuilder("");
        String startTime = apiRequest.getString("startTime");
        String endTime = apiRequest.getString("endTime");
        if ("date".equals(searchType)){
            //正常情况下startTime  endTime 都不为空  防止前台传NULL 故加 默认值
            if (startTime == null){
                startTime = "2019-02-27";
            }
            if (endTime == null){
                endTime = simpleDateFormat.format(new Date());
            }
        }else {
            if ("curQuarter".equals(searchType)){
                int curQuarter = 1;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.getTime().getMonth() + 1;
                if (month >= 1 && month <=3){
                    curQuarter = 1;
                }else if (month >= 4 && month <=6){
                    curQuarter = 2;
                }else if (month >= 7 && month <=9){
                    curQuarter = 3;
                }else if (month >= 10 && month <=12){
                    curQuarter = 4;
                }
                DateUtils.convertTimeBySearchType(builderStart,builderEnd,searchType,year,curQuarter);
            }else{
                DateUtils.convertTimeBySearchType(builderStart,builderEnd,searchType);
            }
            startTime = builderStart.toString();
            endTime = builderEnd.toString();
        }

        try {
            if (!StringUtils.isEmpty(startTime)){
                paramMap.put("startTime",simpleDateFormat.parse(startTime));
            }
            if (!StringUtils.isEmpty(endTime)){
                paramMap.put("endTime",simpleDateFormat.parse(endTime));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //计算环比日期 和 同比日期
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)){
            String momTime = DateUtils.getMomTime(startTime,endTime);
            try {
                paramMap.put("momTime",simpleDateFormat.parse(momTime));//环比日期
            } catch (ParseException e) {
                e.printStackTrace();
            }
            paramMap.put("tbStartTime",DateUtils.getTbDate(startTime));
            paramMap.put("tbEndTime",DateUtils.getTbDate(endTime));
        }
        try {
            paramMap.put("staffType",apiRequest.getInt("staffType"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            paramMap.put("reType",apiRequest.getInt("reType"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        paramMap.put("orgIds",apiRequest.getString("orgIds"));
        paramMap.put("proIds",apiRequest.getString("proIds"));
        paramMap.put("searchTimeType",apiRequest.getString("searchTimeType"));

        Map map = new HashMap();
        switch (dataType){
            case "proThink" :
                map = getProThinkMap(dataType,"",paramMap,map,apiRequest); break;
            case "orgThinkBus" :
                map = getOrgThinkBusMap(dataType,"",paramMap,map,apiRequest); break;
            case "orgThinkPost" :
                map = getOrgThinkPostMap(dataType,"",paramMap,map,apiRequest); break;
            case "orgThinkBusManager" :
                map = getOrgThinkBusManagerMap(dataType,"",paramMap,map,apiRequest); break;
            case "think-ywzy" :
                map = getProThinkMapYWZY(dataType,"",paramMap,map,apiRequest); break;
            case "think-ywgl" :
                map = getProThinkMapYWGL(dataType,"",paramMap,map,apiRequest); break;
            case "think-ywxs" :
                map = getProThinkMapYWXS(dataType,"",paramMap,map,apiRequest); break;
            case "think-hygl" :
                map = getProThinkMapHYGL(dataType,"",paramMap,map,apiRequest); break;
            case "think-cpx" :
                map = getProThinkMapCPX(dataType,"",paramMap,map,apiRequest); break;
            case "think-lfzb" :
                map = getProThinkMapLFZB(dataType,"",paramMap,map,apiRequest); break;
            case "think-lfjt" :
                map = getProThinkMapLFJT(dataType,"",paramMap,map,apiRequest); break;
            default: break;
        }

        map.put("searchType",searchType);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }

    /**
     * 经营分析报表 业务主营
     * @param dataType
     * @param dataTable
     * @param paramMap
     * @param map
     * @param apiRequest
     * @return
     */
    public Map<String,Object> getProThinkMapYWZY(String dataType, String dataTable, Map paramMap, Map map, ApiRequest apiRequest){
        Long curUserId = getCurrentUserId(apiRequest);
        Map<String,Object> tempMap =  new HashMap<String,Object>();
        //机构只查询自己机构的数据
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(curUserId);
        Boolean lefanRole = isRoleUser(userRoles,138L);//平台
        Boolean orgRole = isRoleUser(userRoles,139L);//机构
        if (orgRole && !lefanRole){
            StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(curUserId);
            if (staffPersonnelInfo != null){
                tempMap.put("staffOrgId",staffPersonnelInfo.getOrganId());
            }
        }
        tempMap.put("organAttribute",1);
        tempMap.put("orgIds",StringUtils.isEmpty(paramMap.get("orgIds")) ? null : paramMap.get("orgIds"));
        List<StaffOrganProduct> organProducts = staffOrganProductMapper.list(tempMap);
        List<ProThinkYWZYDTO> data = new ArrayList<ProThinkYWZYDTO>();
        for (StaffOrganProduct organProduct : organProducts) {
            data.add(new ProThinkYWZYDTO(organProduct.getOrganId(),organProduct.getOrganName(),organProduct.getProductEnumId(),organProduct.getProductEnumName(),organProduct.getAccOutEqual()));
        }
        data = initZero(data);

        ProThinkYWZYDTO total = initZero(new ProThinkYWZYDTO());
        //业务
        List<YWDTO> ywData = initZero(staffOrganProductMapper.ywData(paramMap));
        for (ProThinkYWZYDTO item : data) {
            int proId = item.getProId().intValue();
            List<YWDTO> tempData = ywData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).collect(Collectors.toList());
            if (proId == BS_PROID || proId == HZ_PROID || proId == BS_SHARP){
                int orgAttr = proId == HZ_PROID ? 2 : 1;//保司或互助
                int sharp = proId == BS_SHARP ? 1 : 0;//是否反欺诈
                List<YWDTO> searchData1 = tempData.stream().filter(p -> p.getOrgAttr() == orgAttr && p.getSharp() == sharp && p.getServiceType() == 1).collect(Collectors.toList());//全案
                List<YWDTO> searchData2 = tempData.stream().filter(p -> p.getOrgAttr() == orgAttr && p.getSharp() == sharp && p.getServiceType() == 2).collect(Collectors.toList());//单点
                long caseNum1 = searchData1.stream().map(YWDTO::getSurveyInfoId).distinct().count();//全案案件数量
                long caseNum2 = searchData2.stream().map(YWDTO::getSurveyInfoId).distinct().count();//单点案件数量
                long directionNum1 = searchData1.stream().count();
                long directionNum2 = searchData2.stream().count();
                double accScore1 = searchData1.stream().mapToDouble(YWDTO::getAccScore).sum();
                double accScore2 = searchData2.stream().mapToDouble(YWDTO::getAccScore).sum();
                item.setYw1(Double.valueOf(caseNum1));
                item.setYw2(Double.valueOf(caseNum2));
                item.setYw3(Double.valueOf(directionNum1));
                item.setYw4(Double.valueOf(directionNum2));
                item.setYw5(accScore1 + accScore2);
                double totalAccScore = tempData.stream().mapToDouble(YWDTO::getAccScore).sum();
                item.setYw6Rate(fourRate(item.getYw5(),totalAccScore));
            }
        }
        total.setYw1(data.stream().mapToDouble(ProThinkYWZYDTO::getYw1).sum());
        total.setYw2(data.stream().mapToDouble(ProThinkYWZYDTO::getYw2).sum());
        total.setYw3(data.stream().mapToDouble(ProThinkYWZYDTO::getYw3).sum());
        total.setYw4(data.stream().mapToDouble(ProThinkYWZYDTO::getYw4).sum());
        total.setYw5(ywData.stream().mapToDouble(YWDTO::getAccScore).sum());
        total.setYw6Rate(fourRate(total.getYw5(),total.getYw5()));
        //收入
        List<SRDTO> billData = initZero(staffOrganProductMapper.srBillData(paramMap));//开票
        List<SRDTO> accData = initZero(staffOrganProductMapper.srAccData(paramMap));//到账
        List<ThinkDataOrgProduct> thinkData = initZero(thinkDataOrgProductMapper.thinkData(paramMap));//数据录入 阳性奖励相关
        for (ProThinkYWZYDTO item : data) {
            int proId = item.getProId().intValue();
            if (proId == BS_PROID || proId == HZ_PROID || proId == BS_SHARP){//是保司互助反欺诈业务
                //
                int orgAttr = proId == HZ_PROID ? 2 : 1;//保司或互助
                int sharp = proId == BS_SHARP ? 1 : 0;//是否反欺诈
                List<YWDTO> tempYwData = ywData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue() && p.getOrgAttr() == orgAttr && p.getSharp() == sharp).collect(Collectors.toList());
                double sr3 = tempYwData.stream().filter(distinctByKey(YWDTO::getSurveyInfoId)).mapToDouble(YWDTO::getEntrustMoney).sum();//根据案件去重之后。算出机构案件的委托方金额
                item.setSr3(sr3);//产品线开票非税收入
                double sr7 = tempYwData.stream().filter(distinctByKey(YWDTO::getSurveyInfoId)).mapToDouble(YWDTO::getAccMoney).sum();
                item.setSr7(sr7);//业务核算收入
            }else{
                List<SRDTO> tempBillData = billData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue() && p.getProId().intValue() == proId).collect(Collectors.toList());
                List<SRDTO> tempAccData = accData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue() && p.getProId().intValue() == proId).collect(Collectors.toList());
                double billMoney = tempBillData.stream().mapToDouble(SRDTO::getBillMoney).sum();
                item.setSr1(billMoney);
                double sr2 = billData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).mapToDouble(SRDTO::getBillMoney).sum();
                item.setSr2(sr2);
                item.setSr2Rate(fourRate(item.getSr1(),item.getSr2()));

                double billMoneyTax = tempBillData.stream().mapToDouble(SRDTO::getBillMoneyTax).sum();
                item.setSr3(item.getSr1() - billMoneyTax);//产品线开票非税收入
                item.setSr4(billMoneyTax);

                double accMoney = tempAccData.stream().mapToDouble(SRDTO::getAccMoney).sum();
                item.setSr5(accMoney);
                item.setSr6Rate(fourRate(item.getSr5(),item.getSr1()));
                item.setSr7(accMoney);
            }
            //阳性奖励相关
            double sunAddMoney = thinkData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue() && p.getProductId().intValue() == proId).mapToDouble(ThinkDataOrgProduct::getClaimSunAddmony).sum();
            double sunSubMoney = thinkData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue() && p.getProductId().intValue() == proId).mapToDouble(ThinkDataOrgProduct::getClaimSunSubmoney).sum();
            item.setSr8(sunAddMoney);
            item.setSr9(sunSubMoney);
        }
        total.setSr1(data.stream().mapToDouble(ProThinkYWZYDTO :: getSr1).sum());
        total.setSr2(data.stream().mapToDouble(ProThinkYWZYDTO :: getSr2).sum());
        total.setSr2Rate(fourRate(total.getSr1(),total.getSr2()));
        total.setSr3(data.stream().mapToDouble(ProThinkYWZYDTO :: getSr3).sum());
        total.setSr4(data.stream().mapToDouble(ProThinkYWZYDTO :: getSr4).sum());
        total.setSr5(data.stream().mapToDouble(ProThinkYWZYDTO :: getSr5).sum());
        total.setSr6(data.stream().mapToDouble(ProThinkYWZYDTO :: getSr6).sum());
        total.setSr6Rate(fourRate(total.getSr5(),total.getSr1()));
        total.setSr7(data.stream().mapToDouble(ProThinkYWZYDTO :: getSr7).sum());
        total.setSr8(data.stream().mapToDouble(ProThinkYWZYDTO :: getSr8).sum());
        total.setSr9(data.stream().mapToDouble(ProThinkYWZYDTO :: getSr9).sum());
        total.setSr10(data.stream().mapToDouble(ProThinkYWZYDTO :: getSr10).sum());

        List<DetailDTO> zc1Data = initZero(staffOrganProductMapper.zc1Data(paramMap));//工资、绩效、打卡、渠道费、员工数
        List<DetailDTO> zc2Data = initZero(staffOrganProductMapper.zc2Data(paramMap));//报销
        //支出
        for (ProThinkYWZYDTO item : data) {
            List<ProThinkYWZYDTO> tempData = data.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId()).collect(Collectors.toList());//当前机构的总记录
            Double totalAccMoney = tempData.stream().mapToDouble(ProThinkYWZYDTO :: getSr7).sum();//当前机构的总核算收入
            Double rate = 0D;//当前机构的支出比例
            if (totalAccMoney == 0){
                rate = 1D / tempData.stream().collect(Collectors.toList()).size();
            }else{
                rate = item.getSr7() / totalAccMoney;
            }
            item.setRate(rate);
            DetailDTO cb = cb(2,item.getOrgId(),item.getOrgName(),rate,zc1Data,zc2Data,1);//业务主营
            item.setZc1(cb.totalZc1(cb));item.setZc1Detail(cb);
            DetailDTO gl = gl(2,item.getOrgId(),item.getOrgName(),rate,zc1Data,zc2Data,3);//业务管理
            item.setZc3(gl.totalZc3(gl));item.setZc3Detail(gl);
            DetailDTO xs = xs(2,item.getOrgId(),item.getOrgName(),rate,zc1Data,zc2Data,2);//业务销售
            item.setZc2(xs.totalZc2(xs));item.setZc2Detail(xs);
            item.setSr10(zc1Data.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).mapToDouble(DetailDTO :: getJx9).sum() * rate);//个案阳性奖励
        }
        total.setZc1(data.stream().mapToDouble(ProThinkYWZYDTO :: getZc1).sum());
        total.setZc2(data.stream().mapToDouble(ProThinkYWZYDTO :: getZc2).sum());
        total.setZc3(data.stream().mapToDouble(ProThinkYWZYDTO :: getZc3).sum());

        //利润
        for (ProThinkYWZYDTO item : data) {
            item.setLr1(item.getSr3() - item.getZc1());
            item.setLr1Rate(fourRate(item.getLr1(),item.getSr3()));
            item.setLr2(item.getSr3() - (item.getZc1() + item.getZc2() + item.getZc3()));
            item.setLr2Rate(fourRate(item.getLr2(),item.getSr3()));
            item.setLr3(item.getSr7() + item.getSr8() + item.getSr9() + item.getSr10() - (item.getZc1() + item.getZc2() + item.getZc3()));
            item.setLr3Rate(fourRate(item.getLr3(),item.getSr7() + item.getSr8() + item.getSr9() + item.getSr10()));
        }
        total.setLr1(data.stream().mapToDouble(ProThinkYWZYDTO :: getLr1).sum());
        total.setLr1Rate(fourRate(total.getLr1(),total.getSr3()));
        total.setLr2(data.stream().mapToDouble(ProThinkYWZYDTO :: getLr2).sum());
        total.setLr2Rate(fourRate(total.getLr2(),total.getSr3()));
        total.setLr3(data.stream().mapToDouble(ProThinkYWZYDTO :: getLr3).sum());
        total.setLr3Rate(fourRate(total.getLr3(),total.getSr7() + total.getSr8() + total.getSr9() + total.getSr10()));

        //其他
        for (ProThinkYWZYDTO item : data) {
            List<DetailDTO> collect = zc1Data.stream().filter(p -> p.getCostType() == 1 && p.getStaffState() != 6 && item.getOrgId().intValue() == p.getOrgId().intValue()).collect(Collectors.toList());
            item.setQt1(collect.stream().count() * item.getRate());//主营业务员工数
            item.setQt2(fourRate(item.getSr3(),item.getQt1()));
            item.setQt3(fourRate(item.getSr5(),item.getQt1()));
            item.setQt4(fourRate(item.getZc1() + item.getZc2() + item.getZc3(),item.getQt1()));
            item.setQt5(fourRate(item.getSr3() - item.getZc1(),item.getQt1()));
            item.setQt6(fourRate(item.getSr3() - item.getZc1(),item.getSr3()));
            collect = zc1Data.stream().filter(p -> p.getStaffState() != 6 && item.getOrgId().intValue() == p.getOrgId().intValue()).collect(Collectors.toList());
            item.setQt7(collect.stream().count() * item.getRate());//在编员工数
            item.setQt8(fourRate(item.getSr3(),item.getQt7()));
            item.setQt9(fourRate(item.getSr5(),item.getQt7()));
            item.setQt10(fourRate(item.getSr7() + item.getSr8() + item.getSr9() + item.getSr10(),item.getQt7()));
            item.setQt11(fourRate(item.getZc1() + item.getZc2() + item.getZc3(),item.getQt7()));
            item.setQt12(fourRate(item.getSr3() - item.getZc1(),item.getQt7()));
            item.setQt13(fourRate(item.getSr3() - item.getZc1(),item.getSr3()));
        }
        total.setQt1(data.stream().mapToDouble(ProThinkYWZYDTO :: getQt1).sum());
        total.setQt2(data.stream().mapToDouble(ProThinkYWZYDTO :: getQt2).sum());
        total.setQt3(data.stream().mapToDouble(ProThinkYWZYDTO :: getQt3).sum());
        total.setQt4(data.stream().mapToDouble(ProThinkYWZYDTO :: getQt4).sum());
        total.setQt5(data.stream().mapToDouble(ProThinkYWZYDTO :: getQt5).sum());
        total.setQt6(data.stream().mapToDouble(ProThinkYWZYDTO :: getQt6).sum());
        total.setQt7(data.stream().mapToDouble(ProThinkYWZYDTO :: getQt7).sum());
        total.setQt8(data.stream().mapToDouble(ProThinkYWZYDTO :: getQt8).sum());
        total.setQt9(data.stream().mapToDouble(ProThinkYWZYDTO :: getQt9).sum());
        total.setQt10(data.stream().mapToDouble(ProThinkYWZYDTO :: getQt10).sum());
        total.setQt11(data.stream().mapToDouble(ProThinkYWZYDTO :: getQt11).sum());
        total.setQt12(data.stream().mapToDouble(ProThinkYWZYDTO :: getQt12).sum());
        total.setQt13(data.stream().mapToDouble(ProThinkYWZYDTO :: getQt13).sum());
        total.setQt14(data.stream().mapToDouble(ProThinkYWZYDTO :: getQt14).sum());
        total.setQt15(data.stream().mapToDouble(ProThinkYWZYDTO :: getQt15).sum());

        map.put("list",data);
        map.put("total",total);
        return map;
    }


    /**
     * 经营分析报表 业务管理
     * @param dataType
     * @param dataTable
     * @param paramMap
     * @param map
     * @param apiRequest
     * @return
     */
    public Map<String,Object> getProThinkMapYWGL(String dataType, String dataTable, Map paramMap, Map map, ApiRequest apiRequest){
        Long curUserId = getCurrentUserId(apiRequest);
        Map<String,Object> tempMap =  new HashMap<String,Object>();
        //机构只查询自己机构的数据
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(curUserId);
        Boolean lefanRole = isRoleUser(userRoles,138L);//平台
        Boolean orgRole = isRoleUser(userRoles,139L);//机构
        if (orgRole && !lefanRole){
            StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(curUserId);
            if (staffPersonnelInfo != null){
                tempMap.put("staffOrgId",staffPersonnelInfo.getOrganId());
            }
        }
        tempMap.put("organAttribute",3);
        tempMap.put("orgIds",StringUtils.isEmpty(paramMap.get("orgIds")) ? null : paramMap.get("orgIds"));
        List<StaffOrganProduct> organProducts = staffOrganProductMapper.list(tempMap);
        List<ProThinkYWGLDTO> data = new ArrayList<ProThinkYWGLDTO>();
        for (StaffOrganProduct organProduct : organProducts) {
            data.add(new ProThinkYWGLDTO(organProduct.getOrganId(),organProduct.getOrganName(),organProduct.getProductEnumId(),organProduct.getProductEnumName(),organProduct.getAccOutEqual()));
        }
        data = initZero(data);

        ProThinkYWGLDTO total = initZero(new ProThinkYWGLDTO());
        //业务
        List<YWDTO> ywData = initZero(staffOrganProductMapper.ywData(paramMap));
        for (ProThinkYWGLDTO item : data) {
            int proId = item.getProId().intValue();
            List<YWDTO> tempData = ywData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).collect(Collectors.toList());
            if (proId == BS_PROID || proId == HZ_PROID || proId == BS_SHARP){
                int orgAttr = proId == HZ_PROID ? 2 : 1;//保司或互助
                int sharp = proId == BS_SHARP ? 1 : 0;//是否反欺诈
                List<YWDTO> searchData1 = tempData.stream().filter(p -> p.getOrgAttr() == orgAttr && p.getSharp() == sharp).collect(Collectors.toList());
                long caseNum1 = searchData1.stream().map(YWDTO::getSurveyInfoId).distinct().count();//案件数量
                item.setYw1(Double.valueOf(caseNum1));
                List<YWDTO> searchData2 = tempData.stream().filter(p -> p.getOrgAttr() == orgAttr).collect(Collectors.toList());
                long caseNum2 = searchData2.stream().map(YWDTO::getSurveyInfoId).distinct().count();//案件数量
                item.setYw2(Double.valueOf(caseNum2));
                item.setYw2Rate(fourRate(item.getYw1(),item.getYw2()));
            }
        }
        total.setYw1(data.stream().mapToDouble(ProThinkYWGLDTO::getYw1).sum());
        total.setYw2(data.stream().mapToDouble(ProThinkYWGLDTO::getYw2).sum());
        total.setYw2Rate(fourRate(total.getYw1(),total.getYw2()));
        //收入
        List<SRDTO> billData = initZero(staffOrganProductMapper.srBillData(paramMap));//开票
        List<SRDTO> accData = initZero(staffOrganProductMapper.srAccData(paramMap));//到账
        List<ThinkDataOrgProduct> thinkData = initZero(thinkDataOrgProductMapper.thinkData(paramMap));//数据录入 业务核算收入
        for (ProThinkYWGLDTO item : data) {
            int proId = item.getProId().intValue();
            if (proId == BS_PROID || proId == HZ_PROID || proId == BS_SHARP){//是保司互助反欺诈业务
                int orgAttr = proId == HZ_PROID ? 2 : 1;//保司或互助
                int sharp = proId == BS_SHARP ? 1 : 0;//是否反欺诈
                List<YWDTO> tempYwData = ywData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue() && p.getOrgAttr() == orgAttr && p.getSharp() == sharp).collect(Collectors.toList());
                double sr3 = tempYwData.stream().filter(distinctByKey(YWDTO::getSurveyInfoId)).mapToDouble(YWDTO::getEntrustMoney).sum();//根据案件去重之后。算出机构案件的委托方金额
                item.setSr3(sr3);//产品线开票非税收入
            }else{
                List<SRDTO> tempBillData = billData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue() && p.getProId().intValue() == proId).collect(Collectors.toList());
                List<SRDTO> tempAccData = accData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue() && p.getProId().intValue() == proId).collect(Collectors.toList());
                double billMoney = tempBillData.stream().mapToDouble(SRDTO::getBillMoney).sum();
                item.setSr1(billMoney);
                double sr2 = billData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).mapToDouble(SRDTO::getBillMoney).sum();
                item.setSr2(sr2);
                item.setSr2Rate(fourRate(item.getSr1(),item.getSr2()));

                double billMoneyTax = tempBillData.stream().mapToDouble(SRDTO::getBillMoneyTax).sum();
                item.setSr3(item.getSr1() - billMoneyTax);//产品线开票非税收入
                item.setSr4(billMoneyTax);

                double accMoney = tempAccData.stream().mapToDouble(SRDTO::getAccMoney).sum();
                item.setSr5(accMoney);
                item.setSr6Rate(fourRate(item.getSr5(),item.getSr1()));
            }
            item.setSr7(thinkData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).mapToDouble(ThinkDataOrgProduct :: getBusAccMony).sum());//业务核算收入
        }
        total.setSr1(data.stream().mapToDouble(ProThinkYWGLDTO :: getSr1).sum());
        total.setSr2(data.stream().mapToDouble(ProThinkYWGLDTO :: getSr2).sum());
        total.setSr2Rate(fourRate(total.getSr1(),total.getSr2()));
        total.setSr3(data.stream().mapToDouble(ProThinkYWGLDTO :: getSr3).sum());
        total.setSr4(data.stream().mapToDouble(ProThinkYWGLDTO :: getSr4).sum());
        total.setSr5(data.stream().mapToDouble(ProThinkYWGLDTO :: getSr5).sum());
        total.setSr6(data.stream().mapToDouble(ProThinkYWGLDTO :: getSr6).sum());
        total.setSr6Rate(fourRate(total.getSr5(),total.getSr1()));
        total.setSr7(data.stream().mapToDouble(ProThinkYWGLDTO :: getSr7).sum());

        List<DetailDTO> zc1Data = initZero(staffOrganProductMapper.zc1Data(paramMap));//工资、绩效、打卡、渠道费、员工数
        List<DetailDTO> zc2Data = initZero(staffOrganProductMapper.zc2Data(paramMap));//报销
        //支出
        for (ProThinkYWGLDTO item : data) {
            List<ProThinkYWGLDTO> tempData = data.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId()).collect(Collectors.toList());//当前机构的总记录
            Double totalAccMoney = tempData.stream().mapToDouble(ProThinkYWGLDTO :: getSr7).sum();//当前机构的总核算收入
            Double rate = 0D;//当前机构的支出比例
            if (totalAccMoney == 0){
                rate = 1D / tempData.stream().collect(Collectors.toList()).size();
            }else{
                rate = item.getSr7() / totalAccMoney;
            }
            item.setRate(rate);
            DetailDTO cb = cb(2,item.getOrgId(),item.getOrgName(),rate,zc1Data,zc2Data,1);//业务主营
            item.setZc1(cb.totalZc1(cb));item.setZc1Detail(cb);
            DetailDTO gl = gl(2,item.getOrgId(),item.getOrgName(),rate,zc1Data,zc2Data,3);//业务管理
            item.setZc3(gl.totalZc3(gl));item.setZc3Detail(gl);
            DetailDTO xs = xs(2,item.getOrgId(),item.getOrgName(),rate,zc1Data,zc2Data,2);//业务销售
            item.setZc2(xs.totalZc2(xs));item.setZc2Detail(xs);
        }
        total.setZc1(data.stream().mapToDouble(ProThinkYWGLDTO :: getZc1).sum());
        total.setZc2(data.stream().mapToDouble(ProThinkYWGLDTO :: getZc2).sum());
        total.setZc3(data.stream().mapToDouble(ProThinkYWGLDTO :: getZc3).sum());

        //利润
        for (ProThinkYWGLDTO item : data) {
            item.setLr1(item.getSr3() - item.getZc1());
            item.setLr1Rate(fourRate(item.getLr1(),item.getSr3()));
            item.setLr2(item.getSr3() - (item.getZc1() + item.getZc2() + item.getZc3()));
            item.setLr2Rate(fourRate(item.getLr2(),item.getSr3()));
            item.setLr3(item.getSr7() - (item.getZc1() + item.getZc2() + item.getZc3()));
            item.setLr3Rate(fourRate(item.getLr3(),item.getSr7()));
        }
        total.setLr1(data.stream().mapToDouble(ProThinkYWGLDTO :: getLr1).sum());
        total.setLr1Rate(fourRate(total.getLr1(),total.getSr3()));
        total.setLr2(data.stream().mapToDouble(ProThinkYWGLDTO :: getLr2).sum());
        total.setLr2Rate(fourRate(total.getLr2(),total.getSr3()));
        total.setLr3(data.stream().mapToDouble(ProThinkYWGLDTO :: getLr3).sum());
        total.setLr3Rate(fourRate(total.getLr3(),total.getSr7()));

        //其他
        for (ProThinkYWGLDTO item : data) {
            List<DetailDTO> collect = zc1Data.stream().filter(p -> p.getCostType() == 1 && p.getStaffState() != 6 && item.getOrgId().intValue() == p.getOrgId().intValue()).collect(Collectors.toList());
            item.setQt1(collect.stream().count() * item.getRate());//主营业务员工数
            item.setQt2(fourRate(item.getSr3(),item.getQt1()));
            item.setQt3(fourRate(item.getSr5(),item.getQt1()));
            item.setQt4(fourRate(item.getZc1() + item.getZc2() + item.getZc3(),item.getQt1()));
            item.setQt5(fourRate(item.getSr3() - item.getZc1(),item.getQt1()));
            item.setQt6(fourRate(item.getSr3() - item.getZc1(),item.getSr3()));
            collect = zc1Data.stream().filter(p -> p.getStaffState() != 6 && item.getOrgId().intValue() == p.getOrgId().intValue()).collect(Collectors.toList());
            item.setQt7(collect.stream().count() * item.getRate());//在编员工数
            item.setQt8(fourRate(item.getSr3(),item.getQt7()));
            item.setQt9(fourRate(item.getSr5(),item.getQt7()));
            item.setQt10(fourRate(item.getSr7(),item.getQt7()));
            item.setQt11(fourRate(item.getZc1() + item.getZc2() + item.getZc3(),item.getQt7()));
            item.setQt12(fourRate(item.getSr3() - item.getZc1(),item.getQt7()));
            item.setQt13(fourRate(item.getSr3() - item.getZc1(),item.getSr3()));
        }
        total.setQt1(data.stream().mapToDouble(ProThinkYWGLDTO :: getQt1).sum());
        total.setQt2(data.stream().mapToDouble(ProThinkYWGLDTO :: getQt2).sum());
        total.setQt3(data.stream().mapToDouble(ProThinkYWGLDTO :: getQt3).sum());
        total.setQt4(data.stream().mapToDouble(ProThinkYWGLDTO :: getQt4).sum());
        total.setQt5(data.stream().mapToDouble(ProThinkYWGLDTO :: getQt5).sum());
        total.setQt6(data.stream().mapToDouble(ProThinkYWGLDTO :: getQt6).sum());
        total.setQt7(data.stream().mapToDouble(ProThinkYWGLDTO :: getQt7).sum());
        total.setQt8(data.stream().mapToDouble(ProThinkYWGLDTO :: getQt8).sum());
        total.setQt9(data.stream().mapToDouble(ProThinkYWGLDTO :: getQt9).sum());
        total.setQt10(data.stream().mapToDouble(ProThinkYWGLDTO :: getQt10).sum());
        total.setQt11(data.stream().mapToDouble(ProThinkYWGLDTO :: getQt11).sum());
        total.setQt12(data.stream().mapToDouble(ProThinkYWGLDTO :: getQt12).sum());
        total.setQt13(data.stream().mapToDouble(ProThinkYWGLDTO :: getQt13).sum());
        total.setQt14(data.stream().mapToDouble(ProThinkYWGLDTO :: getQt14).sum());
        total.setQt15(data.stream().mapToDouble(ProThinkYWGLDTO :: getQt15).sum());

        map.put("list",data);
        map.put("total",total);
        return map;
    }

    /**
     * 经营分析报表 业务销售
     * @param dataType
     * @param dataTable
     * @param paramMap
     * @param map
     * @param apiRequest
     * @return
     */
    public Map<String,Object> getProThinkMapYWXS(String dataType, String dataTable, Map paramMap, Map map, ApiRequest apiRequest){
        Long curUserId = getCurrentUserId(apiRequest);
        Map<String,Object> tempMap =  new HashMap<String,Object>();
        //机构只查询自己机构的数据
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(curUserId);
        Boolean lefanRole = isRoleUser(userRoles,138L);//平台
        Boolean orgRole = isRoleUser(userRoles,139L);//机构
        if (orgRole && !lefanRole){
            StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(curUserId);
            if (staffPersonnelInfo != null){
                tempMap.put("staffOrgId",staffPersonnelInfo.getOrganId());
            }
        }
        tempMap.put("organAttribute",4);
        tempMap.put("orgIds",StringUtils.isEmpty(paramMap.get("orgIds")) ? null : paramMap.get("orgIds"));
        List<StaffOrganProduct> organProducts = staffOrganProductMapper.list(tempMap);
        List<ProThinkYWXSDTO> data = new ArrayList<ProThinkYWXSDTO>();
        for (StaffOrganProduct organProduct : organProducts) {
            data.add(new ProThinkYWXSDTO(organProduct.getOrganId(),organProduct.getOrganName(),organProduct.getProductEnumId(),organProduct.getProductEnumName(),organProduct.getAccOutEqual()));
        }
        data = initZero(data);

        ProThinkYWXSDTO total = initZero(new ProThinkYWXSDTO());
        //业务
        List<YWDTO> ywData = initZero(staffOrganProductMapper.ywData(paramMap));
        for (ProThinkYWXSDTO item : data) {
            int proId = item.getProId().intValue();
            List<YWDTO> tempData = ywData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).collect(Collectors.toList());
            if (proId == BS_PROID || proId == HZ_PROID || proId == BS_SHARP){
                int orgAttr = proId == HZ_PROID ? 2 : 1;//保司或互助
                int sharp = proId == BS_SHARP ? 1 : 0;//是否反欺诈
                List<YWDTO> searchData1 = tempData.stream().filter(p -> p.getOrgAttr() == orgAttr && p.getSharp() == sharp).collect(Collectors.toList());
                long caseNum1 = searchData1.stream().map(YWDTO::getSurveyInfoId).distinct().count();//案件数量
                item.setYw1(Double.valueOf(caseNum1));
                List<YWDTO> searchData2 = tempData.stream().filter(p -> p.getOrgAttr() == orgAttr).collect(Collectors.toList());
                long caseNum2 = searchData2.stream().map(YWDTO::getSurveyInfoId).distinct().count();//案件数量
                item.setYw2(Double.valueOf(caseNum2));
                item.setYw2Rate(fourRate(item.getYw1(),item.getYw2()));
            }
        }
        total.setYw1(data.stream().mapToDouble(ProThinkYWXSDTO::getYw1).sum());
        total.setYw2(data.stream().mapToDouble(ProThinkYWXSDTO::getYw2).sum());
        total.setYw2Rate(fourRate(total.getYw1(),total.getYw2()));
        //收入
        List<SRDTO> billData = initZero(staffOrganProductMapper.srBillData(paramMap));//开票
        List<SRDTO> accData = initZero(staffOrganProductMapper.srAccData(paramMap));//到账
        List<ThinkDataOrgProduct> thinkData = initZero(thinkDataOrgProductMapper.thinkData(paramMap));//数据录入 业务核算收入
        for (ProThinkYWXSDTO item : data) {
            int proId = item.getProId().intValue();
            if (proId == BS_PROID || proId == HZ_PROID || proId == BS_SHARP){//是保司互助反欺诈业务
                int orgAttr = proId == HZ_PROID ? 2 : 1;//保司或互助
                int sharp = proId == BS_SHARP ? 1 : 0;//是否反欺诈
                List<YWDTO> tempYwData = ywData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue() && p.getOrgAttr() == orgAttr && p.getSharp() == sharp).collect(Collectors.toList());
                double sr3 = tempYwData.stream().filter(distinctByKey(YWDTO::getSurveyInfoId)).mapToDouble(YWDTO::getEntrustMoney).sum();//根据案件去重之后。算出机构案件的委托方金额
                item.setSr3(sr3);//产品线开票非税收入
            }else{
                List<SRDTO> tempBillData = billData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue() && p.getProId().intValue() == proId).collect(Collectors.toList());
                List<SRDTO> tempAccData = accData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue() && p.getProId().intValue() == proId).collect(Collectors.toList());
                double billMoney = tempBillData.stream().mapToDouble(SRDTO::getBillMoney).sum();
                item.setSr1(billMoney);
                double sr2 = billData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).mapToDouble(SRDTO::getBillMoney).sum();
                item.setSr2(sr2);
                item.setSr2Rate(fourRate(item.getSr1(),item.getSr2()));

                double billMoneyTax = tempBillData.stream().mapToDouble(SRDTO::getBillMoneyTax).sum();
                item.setSr3(item.getSr1() - billMoneyTax);//产品线开票非税收入
                item.setSr4(billMoneyTax);

                double accMoney = tempAccData.stream().mapToDouble(SRDTO::getAccMoney).sum();
                item.setSr5(accMoney);
                item.setSr6Rate(fourRate(item.getSr5(),item.getSr1()));
            }
            item.setSr7(thinkData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).mapToDouble(ThinkDataOrgProduct :: getBusAccMony).sum());//业务核算收入
        }
        total.setSr1(data.stream().mapToDouble(ProThinkYWXSDTO :: getSr1).sum());
        total.setSr2(data.stream().mapToDouble(ProThinkYWXSDTO :: getSr2).sum());
        total.setSr2Rate(fourRate(total.getSr1(),total.getSr2()));
        total.setSr3(data.stream().mapToDouble(ProThinkYWXSDTO :: getSr3).sum());
        total.setSr4(data.stream().mapToDouble(ProThinkYWXSDTO :: getSr4).sum());
        total.setSr5(data.stream().mapToDouble(ProThinkYWXSDTO :: getSr5).sum());
        total.setSr6(data.stream().mapToDouble(ProThinkYWXSDTO :: getSr6).sum());
        total.setSr6Rate(fourRate(total.getSr5(),total.getSr1()));
        total.setSr7(data.stream().mapToDouble(ProThinkYWXSDTO :: getSr7).sum());

        List<DetailDTO> zc1Data = initZero(staffOrganProductMapper.zc1Data(paramMap));//工资、绩效、打卡、渠道费、员工数
        List<DetailDTO> zc2Data = initZero(staffOrganProductMapper.zc2Data(paramMap));//报销
        //支出
        for (ProThinkYWXSDTO item : data) {
            List<ProThinkYWXSDTO> tempData = data.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId()).collect(Collectors.toList());//当前机构的总记录
            Double totalAccMoney = tempData.stream().mapToDouble(ProThinkYWXSDTO :: getSr7).sum();//当前机构的总核算收入
            Double rate = 0D;//当前机构的支出比例
            if (totalAccMoney == 0){
                rate = 1D / tempData.stream().collect(Collectors.toList()).size();
            }else{
                rate = item.getSr7() / totalAccMoney;
            }
            item.setRate(rate);
            DetailDTO cb = cb(2,item.getOrgId(),item.getOrgName(),rate,zc1Data,zc2Data,1);//业务主营
            item.setZc1(cb.totalZc1(cb));item.setZc1Detail(cb);
            DetailDTO gl = gl(2,item.getOrgId(),item.getOrgName(),rate,zc1Data,zc2Data,3);//业务管理
            item.setZc3(gl.totalZc3(gl));item.setZc3Detail(gl);
            DetailDTO xs = xs(2,item.getOrgId(),item.getOrgName(),rate,zc1Data,zc2Data,2);//业务销售
            item.setZc2(xs.totalZc2(xs));item.setZc2Detail(xs);
        }
        total.setZc1(data.stream().mapToDouble(ProThinkYWXSDTO :: getZc1).sum());
        total.setZc2(data.stream().mapToDouble(ProThinkYWXSDTO :: getZc2).sum());
        total.setZc3(data.stream().mapToDouble(ProThinkYWXSDTO :: getZc3).sum());

        //利润
        for (ProThinkYWXSDTO item : data) {
            item.setLr1(item.getSr3() - item.getZc1());
            item.setLr1Rate(fourRate(item.getLr1(),item.getSr3()));
            item.setLr2(item.getSr3() - (item.getZc1() + item.getZc2() + item.getZc3()));
            item.setLr2Rate(fourRate(item.getLr2(),item.getSr3()));
            item.setLr3(item.getSr7() - (item.getZc1() + item.getZc2() + item.getZc3()));
            item.setLr3Rate(fourRate(item.getLr3(),item.getSr7()));
        }
        total.setLr1(data.stream().mapToDouble(ProThinkYWXSDTO :: getLr1).sum());
        total.setLr1Rate(fourRate(total.getLr1(),total.getSr3()));
        total.setLr2(data.stream().mapToDouble(ProThinkYWXSDTO :: getLr2).sum());
        total.setLr2Rate(fourRate(total.getLr2(),total.getSr3()));
        total.setLr3(data.stream().mapToDouble(ProThinkYWXSDTO :: getLr3).sum());
        total.setLr3Rate(fourRate(total.getLr3(),total.getSr7()));

        //其他
        for (ProThinkYWXSDTO item : data) {
            List<DetailDTO> collect = zc1Data.stream().filter(p -> p.getCostType() == 1 && p.getStaffState() != 6 && item.getOrgId().intValue() == p.getOrgId().intValue()).collect(Collectors.toList());
            item.setQt1(collect.stream().count() * item.getRate());//主营业务员工数
            item.setQt2(fourRate(item.getSr3(),item.getQt1()));
            item.setQt3(fourRate(item.getSr5(),item.getQt1()));
            item.setQt4(fourRate(item.getZc1() + item.getZc2() + item.getZc3(),item.getQt1()));
            item.setQt5(fourRate(item.getSr3() - item.getZc1(),item.getQt1()));
            item.setQt6(fourRate(item.getSr3() - item.getZc1(),item.getSr3()));
            collect = zc1Data.stream().filter(p -> p.getStaffState() != 6 && item.getOrgId().intValue() == p.getOrgId().intValue()).collect(Collectors.toList());
            item.setQt7(collect.stream().count() * item.getRate());//在编员工数
            item.setQt8(fourRate(item.getSr3(),item.getQt7()));
            item.setQt9(fourRate(item.getSr5(),item.getQt7()));
            item.setQt10(fourRate(item.getSr7(),item.getQt7()));
            item.setQt11(fourRate(item.getZc1() + item.getZc2() + item.getZc3(),item.getQt7()));
            item.setQt12(fourRate(item.getSr3() - item.getZc1(),item.getQt7()));
            item.setQt13(fourRate(item.getSr3() - item.getZc1(),item.getSr3()));
        }
        total.setQt1(data.stream().mapToDouble(ProThinkYWXSDTO :: getQt1).sum());
        total.setQt2(data.stream().mapToDouble(ProThinkYWXSDTO :: getQt2).sum());
        total.setQt3(data.stream().mapToDouble(ProThinkYWXSDTO :: getQt3).sum());
        total.setQt4(data.stream().mapToDouble(ProThinkYWXSDTO :: getQt4).sum());
        total.setQt5(data.stream().mapToDouble(ProThinkYWXSDTO :: getQt5).sum());
        total.setQt6(data.stream().mapToDouble(ProThinkYWXSDTO :: getQt6).sum());
        total.setQt7(data.stream().mapToDouble(ProThinkYWXSDTO :: getQt7).sum());
        total.setQt8(data.stream().mapToDouble(ProThinkYWXSDTO :: getQt8).sum());
        total.setQt9(data.stream().mapToDouble(ProThinkYWXSDTO :: getQt9).sum());
        total.setQt10(data.stream().mapToDouble(ProThinkYWXSDTO :: getQt10).sum());
        total.setQt11(data.stream().mapToDouble(ProThinkYWXSDTO :: getQt11).sum());
        total.setQt12(data.stream().mapToDouble(ProThinkYWXSDTO :: getQt12).sum());
        total.setQt13(data.stream().mapToDouble(ProThinkYWXSDTO :: getQt13).sum());
        total.setQt14(data.stream().mapToDouble(ProThinkYWXSDTO :: getQt14).sum());
        total.setQt15(data.stream().mapToDouble(ProThinkYWXSDTO :: getQt15).sum());

        map.put("list",data);
        map.put("total",total);
        return map;
    }

    /**
     * 经营分析报表 后援管理
     * @param dataType
     * @param dataTable
     * @param paramMap
     * @param map
     * @param apiRequest
     * @return
     */
    public Map<String,Object> getProThinkMapHYGL(String dataType, String dataTable, Map paramMap, Map map, ApiRequest apiRequest){
        Long curUserId = getCurrentUserId(apiRequest);
        Map<String,Object> tempMap =  new HashMap<String,Object>();
        //机构只查询自己机构的数据
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(curUserId);
        Boolean lefanRole = isRoleUser(userRoles,138L);//平台
        Boolean orgRole = isRoleUser(userRoles,139L);//机构
        if (orgRole && !lefanRole){
            StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(curUserId);
            if (staffPersonnelInfo != null){
                tempMap.put("staffOrgId",staffPersonnelInfo.getOrganId());
            }
        }
        tempMap.put("organAttribute",2);
        tempMap.put("orgIds",StringUtils.isEmpty(paramMap.get("orgIds")) ? null : paramMap.get("orgIds"));
        List<StaffOrgan> organs = staffOrganMapper.list(tempMap);
        List<ProThinkHYGLDTO> data = new ArrayList<ProThinkHYGLDTO>();
        for (StaffOrgan staffOrgan : organs) {
            data.add(new ProThinkHYGLDTO(staffOrgan.getId(),staffOrgan.getName(),staffOrgan.getAccOutEqual()));
        }
        data = initZero(data);

        ProThinkHYGLDTO total = initZero(new ProThinkHYGLDTO());
        //收入
        List<SRDTO> billData = initZero(staffOrganProductMapper.srBillData(paramMap));//开票
        List<SRDTO> accData = initZero(staffOrganProductMapper.srAccData(paramMap));//到账
        List<ThinkDataDetail> thinkData = initZero(thinkDataOrgProductMapper.thinkDataDetail(paramMap));//数据录入 部门核算收入
        for (ProThinkHYGLDTO item : data) {
            List<SRDTO> tempBillData = billData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).collect(Collectors.toList());
            List<SRDTO> tempAccData = accData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).collect(Collectors.toList());
            double billMoney = tempBillData.stream().mapToDouble(SRDTO::getBillMoney).sum();
            item.setSr1(billMoney);
            double sr2 = billData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).mapToDouble(SRDTO::getBillMoney).sum();
            item.setSr2(sr2);
            item.setSr2Rate(fourRate(item.getSr1(),item.getSr2()));

            double billMoneyTax = tempBillData.stream().mapToDouble(SRDTO::getBillMoneyTax).sum();
            item.setSr3(item.getSr1() - billMoneyTax);//产品线开票非税收入
            item.setSr4(billMoneyTax);

            double accMoney = tempAccData.stream().mapToDouble(SRDTO::getAccMoney).sum();
            item.setSr5(accMoney);
            item.setSr6Rate(fourRate(item.getSr5(),item.getSr1()));
            item.setSr7(thinkData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).mapToDouble(ThinkDataDetail :: getDepAccMony).sum());//部门核算收入
        }
        total.setSr1(data.stream().mapToDouble(ProThinkHYGLDTO :: getSr1).sum());
        total.setSr2(data.stream().mapToDouble(ProThinkHYGLDTO :: getSr2).sum());
        total.setSr2Rate(fourRate(total.getSr1(),total.getSr2()));
        total.setSr3(data.stream().mapToDouble(ProThinkHYGLDTO :: getSr3).sum());
        total.setSr4(data.stream().mapToDouble(ProThinkHYGLDTO :: getSr4).sum());
        total.setSr5(data.stream().mapToDouble(ProThinkHYGLDTO :: getSr5).sum());
        total.setSr6(data.stream().mapToDouble(ProThinkHYGLDTO :: getSr6).sum());
        total.setSr6Rate(fourRate(total.getSr5(),total.getSr1()));
        total.setSr7(data.stream().mapToDouble(ProThinkHYGLDTO :: getSr7).sum());

        List<DetailDTO> zc1Data = initZero(staffOrganProductMapper.zc1Data(paramMap));//工资、绩效、打卡、渠道费、员工数
        List<DetailDTO> zc2Data = initZero(staffOrganProductMapper.zc2Data(paramMap));//报销
        //支出
        for (ProThinkHYGLDTO item : data) {
            item.setRate(1D);
            DetailDTO cb = cb(2,item.getOrgId(),item.getOrgName(),1D,zc1Data,zc2Data,1);//业务主营
            item.setZc1(cb.totalZc1(cb));item.setZc1Detail(cb);
            DetailDTO gl = gl(2,item.getOrgId(),item.getOrgName(),1D,zc1Data,zc2Data,3);//业务管理
            item.setZc3(gl.totalZc3(gl));item.setZc3Detail(gl);
            DetailDTO xs = xs(2,item.getOrgId(),item.getOrgName(),1D,zc1Data,zc2Data,2);//业务销售
            item.setZc2(xs.totalZc2(xs));item.setZc2Detail(xs);
        }
        total.setZc1(data.stream().mapToDouble(ProThinkHYGLDTO :: getZc1).sum());
        total.setZc2(data.stream().mapToDouble(ProThinkHYGLDTO :: getZc2).sum());
        total.setZc3(data.stream().mapToDouble(ProThinkHYGLDTO :: getZc3).sum());

        //利润
        for (ProThinkHYGLDTO item : data) {
            item.setLr1(item.getSr3() - item.getZc1());
            item.setLr1Rate(fourRate(item.getLr1(),item.getSr3()));
            item.setLr2(item.getSr3() - (item.getZc1() + item.getZc2() + item.getZc3()));
            item.setLr2Rate(fourRate(item.getLr2(),item.getSr3()));
            item.setLr3(item.getSr7() - (item.getZc1() + item.getZc2() + item.getZc3()));
            item.setLr3Rate(fourRate(item.getLr3(),item.getSr7()));
        }
        total.setLr1(data.stream().mapToDouble(ProThinkHYGLDTO :: getLr1).sum());
        total.setLr1Rate(fourRate(total.getLr1(),total.getSr3()));
        total.setLr2(data.stream().mapToDouble(ProThinkHYGLDTO :: getLr2).sum());
        total.setLr2Rate(fourRate(total.getLr2(),total.getSr3()));
        total.setLr3(data.stream().mapToDouble(ProThinkHYGLDTO :: getLr3).sum());
        total.setLr3Rate(fourRate(total.getLr3(),total.getSr7()));

        //其他
        for (ProThinkHYGLDTO item : data) {
            List<DetailDTO> collect = zc1Data.stream().filter(p -> p.getCostType() == 1 && p.getStaffState() != 6 && item.getOrgId().intValue() == p.getOrgId().intValue()).collect(Collectors.toList());
            item.setQt1(collect.stream().count() * item.getRate());//主营业务员工数
            item.setQt2(fourRate(item.getSr3(),item.getQt1()));
            item.setQt3(fourRate(item.getSr5(),item.getQt1()));
            item.setQt4(fourRate(item.getZc1() + item.getZc2() + item.getZc3(),item.getQt1()));
            item.setQt5(fourRate(item.getSr3() - item.getZc1(),item.getQt1()));
            item.setQt6(fourRate(item.getSr3() - item.getZc1(),item.getSr3()));
            collect = zc1Data.stream().filter(p -> p.getStaffState() != 6 && item.getOrgId().intValue() == p.getOrgId().intValue()).collect(Collectors.toList());
            item.setQt7(collect.stream().count() * item.getRate());//在编员工数
            item.setQt8(fourRate(item.getSr3(),item.getQt7()));
            item.setQt9(fourRate(item.getSr5(),item.getQt7()));
            item.setQt10(fourRate(item.getSr7(),item.getQt7()));
            item.setQt11(fourRate(item.getZc1() + item.getZc2() + item.getZc3(),item.getQt7()));
            item.setQt12(fourRate(item.getSr3() - item.getZc1(),item.getQt7()));
            item.setQt13(fourRate(item.getSr3() - item.getZc1(),item.getSr3()));
        }
        total.setQt1(data.stream().mapToDouble(ProThinkHYGLDTO :: getQt1).sum());
        total.setQt2(data.stream().mapToDouble(ProThinkHYGLDTO :: getQt2).sum());
        total.setQt3(data.stream().mapToDouble(ProThinkHYGLDTO :: getQt3).sum());
        total.setQt4(data.stream().mapToDouble(ProThinkHYGLDTO :: getQt4).sum());
        total.setQt5(data.stream().mapToDouble(ProThinkHYGLDTO :: getQt5).sum());
        total.setQt6(data.stream().mapToDouble(ProThinkHYGLDTO :: getQt6).sum());
        total.setQt7(data.stream().mapToDouble(ProThinkHYGLDTO :: getQt7).sum());
        total.setQt8(data.stream().mapToDouble(ProThinkHYGLDTO :: getQt8).sum());
        total.setQt9(data.stream().mapToDouble(ProThinkHYGLDTO :: getQt9).sum());
        total.setQt10(data.stream().mapToDouble(ProThinkHYGLDTO :: getQt10).sum());
        total.setQt11(data.stream().mapToDouble(ProThinkHYGLDTO :: getQt11).sum());
        total.setQt12(data.stream().mapToDouble(ProThinkHYGLDTO :: getQt12).sum());
        total.setQt13(data.stream().mapToDouble(ProThinkHYGLDTO :: getQt13).sum());
        total.setQt14(data.stream().mapToDouble(ProThinkHYGLDTO :: getQt14).sum());
        total.setQt15(data.stream().mapToDouble(ProThinkHYGLDTO :: getQt15).sum());

        map.put("list",data);
        map.put("total",total);
        return map;
    }

    /**
     * 经营分析报表：乐凡总部
     * @param dataType
     * @param dataTable
     * @param paramMap
     * @param map
     * @param apiRequest
     * @return
     */
    public Map<String,Object> getProThinkMapLFZB(String dataType, String dataTable, Map paramMap, Map map, ApiRequest apiRequest){
        Long curUserId = getCurrentUserId(apiRequest);
        Map<String,Object> tempMap =  new HashMap<String,Object>();
        //机构只查询自己机构的数据
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(curUserId);
        Boolean lefanRole = isRoleUser(userRoles,138L);//平台
        Boolean orgRole = isRoleUser(userRoles,139L);//机构
        if (orgRole && !lefanRole){
            StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(curUserId);
            if (staffPersonnelInfo != null){
                tempMap.put("staffOrgId",staffPersonnelInfo.getOrganId());
            }
        }
        // tempMap.put("organAttribute",2);
        List<StaffOrgan> organs = staffOrganMapper.listHq();
        List<ProThinkLFZBDTO> data = new ArrayList<ProThinkLFZBDTO>();
        for (StaffOrgan staffOrgan : organs) {
            data.add(new ProThinkLFZBDTO(staffOrgan.getId(),staffOrgan.getName(),staffOrgan.getAccOutEqual()));
        }
        data = initZero(data);

        ProThinkHYGLDTO total = initZero(new ProThinkHYGLDTO());
        //收入
        List<SRDTO> billData = initZero(staffOrganProductMapper.srBillData(paramMap));//开票
        List<SRDTO> accData = initZero(staffOrganProductMapper.srAccData(paramMap));//到账
        List<ThinkDataDetail> thinkData = initZero(thinkDataOrgProductMapper.thinkDataDetail(paramMap));//数据录入 部门核算收入
        for (ProThinkLFZBDTO item : data) {
            List<SRDTO> tempBillData = billData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).collect(Collectors.toList());
            List<SRDTO> tempAccData = accData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).collect(Collectors.toList());
            double billMoney = tempBillData.stream().mapToDouble(SRDTO::getBillMoney).sum();
            item.setSr1(billMoney);
            double sr2 = billData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).mapToDouble(SRDTO::getBillMoney).sum();
            item.setSr2(sr2);
            item.setSr2Rate(fourRate(item.getSr1(),item.getSr2()));

            double billMoneyTax = tempBillData.stream().mapToDouble(SRDTO::getBillMoneyTax).sum();
            item.setSr3(item.getSr1() - billMoneyTax);//产品线开票非税收入
            item.setSr4(billMoneyTax);

            double accMoney = tempAccData.stream().mapToDouble(SRDTO::getAccMoney).sum();
            item.setSr5(accMoney);
            item.setSr6Rate(fourRate(item.getSr5(),item.getSr1()));
            item.setSr7(thinkData.stream().filter(p -> p.getOrgId().intValue() == item.getOrgId().intValue()).mapToDouble(ThinkDataDetail :: getDepAccMony).sum());//部门核算收入
        }
        total.setSr1(data.stream().mapToDouble(ProThinkLFZBDTO :: getSr1).sum());
        total.setSr2(data.stream().mapToDouble(ProThinkLFZBDTO :: getSr2).sum());
        total.setSr2Rate(fourRate(total.getSr1(),total.getSr2()));
        total.setSr3(data.stream().mapToDouble(ProThinkLFZBDTO :: getSr3).sum());
        total.setSr4(data.stream().mapToDouble(ProThinkLFZBDTO :: getSr4).sum());
        total.setSr5(data.stream().mapToDouble(ProThinkLFZBDTO :: getSr5).sum());
        total.setSr6(data.stream().mapToDouble(ProThinkLFZBDTO :: getSr6).sum());
        total.setSr6Rate(fourRate(total.getSr5(),total.getSr1()));
        total.setSr7(data.stream().mapToDouble(ProThinkLFZBDTO :: getSr7).sum());

        List<DetailDTO> zc1Data = initZero(staffOrganProductMapper.zc1Data(paramMap));//工资、绩效、打卡、渠道费、员工数
        List<DetailDTO> zc2Data = initZero(staffOrganProductMapper.zc2Data(paramMap));//报销
        //支出
        for (ProThinkLFZBDTO item : data) {
            item.setRate(1D);
            DetailDTO cb = cb(2,item.getOrgId(),item.getOrgName(),1D,zc1Data,zc2Data,1);//业务主营
            item.setZc1(cb.totalZc1(cb));item.setZc1Detail(cb);
            DetailDTO gl = gl(2,item.getOrgId(),item.getOrgName(),1D,zc1Data,zc2Data,3);//业务管理
            item.setZc3(gl.totalZc3(gl));item.setZc3Detail(gl);
            DetailDTO xs = xs(2,item.getOrgId(),item.getOrgName(),1D,zc1Data,zc2Data,2);//业务销售
            item.setZc2(xs.totalZc2(xs));item.setZc2Detail(xs);
        }
        total.setZc1(data.stream().mapToDouble(ProThinkLFZBDTO :: getZc1).sum());
        total.setZc2(data.stream().mapToDouble(ProThinkLFZBDTO :: getZc2).sum());
        total.setZc3(data.stream().mapToDouble(ProThinkLFZBDTO :: getZc3).sum());

        //利润
        for (ProThinkLFZBDTO item : data) {
            item.setLr1(item.getSr3() - item.getZc1());
            item.setLr1Rate(fourRate(item.getLr1(),item.getSr3()));
            item.setLr2(item.getSr3() - (item.getZc1() + item.getZc2() + item.getZc3()));
            item.setLr2Rate(fourRate(item.getLr2(),item.getSr3()));
            item.setLr3(item.getSr7() - (item.getZc1() + item.getZc2() + item.getZc3()));
            item.setLr3Rate(fourRate(item.getLr3(),item.getSr7()));
        }
        total.setLr1(data.stream().mapToDouble(ProThinkLFZBDTO :: getLr1).sum());
        total.setLr1Rate(fourRate(total.getLr1(),total.getSr3()));
        total.setLr2(data.stream().mapToDouble(ProThinkLFZBDTO :: getLr2).sum());
        total.setLr2Rate(fourRate(total.getLr2(),total.getSr3()));
        total.setLr3(data.stream().mapToDouble(ProThinkLFZBDTO :: getLr3).sum());
        total.setLr3Rate(fourRate(total.getLr3(),total.getSr7()));

        //其他
        for (ProThinkLFZBDTO item : data) {
            List<DetailDTO> collect = zc1Data.stream().filter(p -> p.getCostType() == 1 && p.getStaffState() != 6 && p.getOrgId().intValue() == item.getOrgId().intValue()).collect(Collectors.toList());
            item.setQt1(collect.stream().count() * item.getRate());//主营业务员工数
            item.setQt2(fourRate(item.getSr3(),item.getQt1()));
            item.setQt3(fourRate(item.getSr5(),item.getQt1()));
            item.setQt4(fourRate(item.getZc1() + item.getZc2() + item.getZc3(),item.getQt1()));
            item.setQt5(fourRate(item.getSr3() - item.getZc1(),item.getQt1()));
            item.setQt6(fourRate(item.getSr3() - item.getZc1(),item.getSr3()));
            collect = zc1Data.stream().filter(p -> p.getStaffState() != 6 && p.getOrgId().intValue() == item.getOrgId().intValue()).collect(Collectors.toList());
            item.setQt7(collect.stream().count() * item.getRate());//在编员工数
            item.setQt8(fourRate(item.getSr3(),item.getQt7()));
            item.setQt9(fourRate(item.getSr5(),item.getQt7()));
            item.setQt10(fourRate(item.getSr7(),item.getQt7()));
            item.setQt11(fourRate(item.getZc1() + item.getZc2() + item.getZc3(),item.getQt7()));
            item.setQt12(fourRate(item.getSr3() - item.getZc1(),item.getQt7()));
            item.setQt13(fourRate(item.getSr3() - item.getZc1(),item.getSr3()));
        }
        total.setQt1(data.stream().mapToDouble(ProThinkLFZBDTO :: getQt1).sum());
        total.setQt2(data.stream().mapToDouble(ProThinkLFZBDTO :: getQt2).sum());
        total.setQt3(data.stream().mapToDouble(ProThinkLFZBDTO :: getQt3).sum());
        total.setQt4(data.stream().mapToDouble(ProThinkLFZBDTO :: getQt4).sum());
        total.setQt5(data.stream().mapToDouble(ProThinkLFZBDTO :: getQt5).sum());
        total.setQt6(data.stream().mapToDouble(ProThinkLFZBDTO :: getQt6).sum());
        total.setQt7(data.stream().mapToDouble(ProThinkLFZBDTO :: getQt7).sum());
        total.setQt8(data.stream().mapToDouble(ProThinkLFZBDTO :: getQt8).sum());
        total.setQt9(data.stream().mapToDouble(ProThinkLFZBDTO :: getQt9).sum());
        total.setQt10(data.stream().mapToDouble(ProThinkLFZBDTO :: getQt10).sum());
        total.setQt11(data.stream().mapToDouble(ProThinkLFZBDTO :: getQt11).sum());
        total.setQt12(data.stream().mapToDouble(ProThinkLFZBDTO :: getQt12).sum());
        total.setQt13(data.stream().mapToDouble(ProThinkLFZBDTO :: getQt13).sum());
        total.setQt14(data.stream().mapToDouble(ProThinkLFZBDTO :: getQt14).sum());
        total.setQt15(data.stream().mapToDouble(ProThinkLFZBDTO :: getQt15).sum());

        map.put("list",data);
        map.put("total",total);
        return map;
    }

    /**
     * 经营分析报表：乐凡集团
     * @param dataType
     * @param dataTable
     * @param paramMap
     * @param map
     * @param apiRequest
     * @return
     */
    public Map<String,Object> getProThinkMapLFJT(String dataType, String dataTable, Map paramMap, Map map, ApiRequest apiRequest){
        Long curUserId = getCurrentUserId(apiRequest);
        Map<String,Object> tempMap =  new HashMap<String,Object>();
        //查询开票公司
        List<BillingApplyCorporation> billingApplyCorporations = billingApplyCorporationMapper.list(apiRequest);
        List<ProThinkLFJTDTO> data = new ArrayList<ProThinkLFJTDTO>();
        for (BillingApplyCorporation billingApplyCorporation : billingApplyCorporations) {
            data.add(new ProThinkLFJTDTO(billingApplyCorporation.getId(),billingApplyCorporation.getName()));
        }
        data = initZero(data);

        ProThinkLFJTDTO total = initZero(new ProThinkLFJTDTO());
        //收入
        List<SRCompanyDTO> billData = initZero(billingApplyCorporationMapper.srCompanyBillData(paramMap));//开票
        List<SRCompanyDTO> accData = initZero(billingApplyCorporationMapper.srAccCompanyData(paramMap));//到账
        paramMap.put("dataType",4);//只查询公司数据
        List<ThinkDataDetail> thinkData = initZero(thinkDataOrgProductMapper.thinkDataDetail(paramMap));//数据录入 部门核算收入
        for (ProThinkLFJTDTO item : data) {
            List<SRCompanyDTO> tempBillData = billData.stream().filter(p -> p.getCommpanyId().intValue() == item.getCommpanyId().intValue()).collect(Collectors.toList());
            List<SRCompanyDTO> tempAccData = accData.stream().filter(p -> p.getCommpanyId().intValue() == item.getCommpanyId().intValue()).collect(Collectors.toList());
            double billMoney = tempBillData.stream().mapToDouble(SRCompanyDTO::getBillMoney).sum();
            item.setSr1(billMoney);
            double sr2 = billData.stream().filter(p -> p.getCommpanyId().intValue() == item.getCommpanyId().intValue()).mapToDouble(SRCompanyDTO::getBillMoney).sum();
            item.setSr2(sr2);
            item.setSr2Rate(fourRate(item.getSr1(),item.getSr2()));

            double billMoneyTax = tempBillData.stream().mapToDouble(SRCompanyDTO::getBillMoneyTax).sum();
            item.setSr3(item.getSr1() - billMoneyTax);//产品线开票非税收入
            item.setSr4(billMoneyTax);

            double accMoney = tempAccData.stream().mapToDouble(SRCompanyDTO::getAccMoney).sum();
            item.setSr5(accMoney);
            item.setSr6Rate(fourRate(item.getSr5(),item.getSr1()));
            item.setSr7(thinkData.stream().filter(p -> p.getOrgId().intValue() == item.getCommpanyId().intValue()).mapToDouble(ThinkDataDetail :: getDepAccMony).sum());//部门核算收入
        }
        total.setSr1(data.stream().mapToDouble(ProThinkLFJTDTO :: getSr1).sum());
        total.setSr2(data.stream().mapToDouble(ProThinkLFJTDTO :: getSr2).sum());
        total.setSr2Rate(fourRate(total.getSr1(),total.getSr2()));
        total.setSr3(data.stream().mapToDouble(ProThinkLFJTDTO :: getSr3).sum());
        total.setSr4(data.stream().mapToDouble(ProThinkLFJTDTO :: getSr4).sum());
        total.setSr5(data.stream().mapToDouble(ProThinkLFJTDTO :: getSr5).sum());
        total.setSr6(data.stream().mapToDouble(ProThinkLFJTDTO :: getSr6).sum());
        total.setSr6Rate(fourRate(total.getSr5(),total.getSr1()));
        total.setSr7(data.stream().mapToDouble(ProThinkLFJTDTO :: getSr7).sum());

        List<DetailDTO> zc1Data = initZero(staffOrganProductMapper.zc1CompanyData(paramMap));//工资、绩效、打卡、渠道费、员工数
        List<DetailDTO> zc2Data = initZero(staffOrganProductMapper.zc2CompanyData(paramMap));//报销
        //支出
        for (ProThinkLFJTDTO item : data) {
            item.setRate(1D);
            DetailDTO cb = cb(3,item.getCommpanyId(),item.getCommpanyName(),1D,zc1Data,zc2Data,1);//业务主营
            item.setZc1(cb.totalZc1(cb));item.setZc1Detail(cb);
            DetailDTO gl = gl(3,item.getCommpanyId(),item.getCommpanyName(),1D,zc1Data,zc2Data,3);//业务管理
            item.setZc3(gl.totalZc3(gl));item.setZc3Detail(gl);
            DetailDTO xs = xs(3,item.getCommpanyId(),item.getCommpanyName(),1D,zc1Data,zc2Data,2);//业务销售
            item.setZc2(xs.totalZc2(xs));item.setZc2Detail(xs);
        }
        total.setZc1(data.stream().mapToDouble(ProThinkLFJTDTO :: getZc1).sum());
        total.setZc2(data.stream().mapToDouble(ProThinkLFJTDTO :: getZc2).sum());
        total.setZc3(data.stream().mapToDouble(ProThinkLFJTDTO :: getZc3).sum());

        //利润
        for (ProThinkLFJTDTO item : data) {
            item.setLr1(item.getSr3() - item.getZc1());
            item.setLr1Rate(fourRate(item.getLr1(),item.getSr3()));
            item.setLr2(item.getSr3() - (item.getZc1() + item.getZc2() + item.getZc3()));
            item.setLr2Rate(fourRate(item.getLr2(),item.getSr3()));
            item.setLr3(item.getSr7() - (item.getZc1() + item.getZc2() + item.getZc3()));
            item.setLr3Rate(fourRate(item.getLr3(),item.getSr7()));

            item.setLr4(thinkData.stream().filter(p -> p.getOrgId().intValue() == item.getCommpanyId().intValue()).mapToDouble(ThinkDataDetail :: getLefanInMony).sum());
            item.setLr5(thinkData.stream().filter(p -> p.getOrgId().intValue() == item.getCommpanyId().intValue()).mapToDouble(ThinkDataDetail :: getLefanOutMony).sum());
            item.setLr6(item.getLr4() - item.getLr5());
            item.setLr7(item.getLr2() + item.getLr6());
            item.setLr7Rate(fourRate(item.getLr7(),item.getSr3()));
            item.setLr8(thinkData.stream().filter(p -> p.getOrgId().intValue() == item.getCommpanyId().intValue()).mapToDouble(ThinkDataDetail :: getLefanTaxMony).sum());
            item.setLr9(item.getLr7() - item.getLr8());
            item.setLr9Rate(fourRate(item.getLr9(),item.getSr3()));
        }
        total.setLr1(data.stream().mapToDouble(ProThinkLFJTDTO :: getLr1).sum());
        total.setLr1Rate(fourRate(total.getLr1(),total.getSr3()));
        total.setLr2(data.stream().mapToDouble(ProThinkLFJTDTO :: getLr2).sum());
        total.setLr2Rate(fourRate(total.getLr2(),total.getSr3()));
        total.setLr3(data.stream().mapToDouble(ProThinkLFJTDTO :: getLr3).sum());
        total.setLr3Rate(fourRate(total.getLr3(),total.getSr7()));
        total.setLr4(data.stream().mapToDouble(ProThinkLFJTDTO :: getLr4).sum());
        total.setLr5(data.stream().mapToDouble(ProThinkLFJTDTO :: getLr5).sum());
        total.setLr6(data.stream().mapToDouble(ProThinkLFJTDTO :: getLr6).sum());
        total.setLr7(data.stream().mapToDouble(ProThinkLFJTDTO :: getLr7).sum());
        total.setLr7Rate(fourRate(total.getLr7(),total.getSr3()));
        total.setLr8(data.stream().mapToDouble(ProThinkLFJTDTO :: getLr8).sum());
        total.setLr9(data.stream().mapToDouble(ProThinkLFJTDTO :: getLr9).sum());
        total.setLr9Rate(fourRate(total.getLr9(),total.getSr3()));

        //其他
        for (ProThinkLFJTDTO item : data) {
            List<DetailDTO> collect = zc1Data.stream().filter(p -> p.getCostType() == 1 && p.getStaffState() != 6 && p.getCompanyId() != null && p.getCompanyId().intValue() == item.getCommpanyId().intValue()).collect(Collectors.toList());
            item.setQt1(collect.stream().count() * item.getRate());//主营业务员工数
            item.setQt2(fourRate(item.getSr3(),item.getQt1()));
            item.setQt3(fourRate(item.getSr5(),item.getQt1()));
            item.setQt4(fourRate(item.getZc1() + item.getZc2() + item.getZc3(),item.getQt1()));
            item.setQt5(fourRate(item.getSr3() - item.getZc1(),item.getQt1()));
            item.setQt6(fourRate(item.getSr3() - item.getZc1(),item.getSr3()));
            collect = zc1Data.stream().filter(p -> p.getStaffState() != 6 && p.getCompanyId() != null && p.getCompanyId().intValue() == item.getCommpanyId().intValue()).collect(Collectors.toList());
            item.setQt7(collect.stream().count() * item.getRate());//在编员工数
            item.setQt8(fourRate(item.getSr3(),item.getQt7()));
            item.setQt9(fourRate(item.getSr5(),item.getQt7()));
            item.setQt10(fourRate(item.getSr7(),item.getQt7()));
            item.setQt11(fourRate(item.getZc1() + item.getZc2() + item.getZc3(),item.getQt7()));
            item.setQt12(fourRate(item.getSr3() - item.getZc1(),item.getQt7()));
            item.setQt13(fourRate(item.getSr3() - item.getZc1(),item.getSr3()));
        }
        total.setQt1(data.stream().mapToDouble(ProThinkLFJTDTO :: getQt1).sum());
        total.setQt2(data.stream().mapToDouble(ProThinkLFJTDTO :: getQt2).sum());
        total.setQt3(data.stream().mapToDouble(ProThinkLFJTDTO :: getQt3).sum());
        total.setQt4(data.stream().mapToDouble(ProThinkLFJTDTO :: getQt4).sum());
        total.setQt5(data.stream().mapToDouble(ProThinkLFJTDTO :: getQt5).sum());
        total.setQt6(data.stream().mapToDouble(ProThinkLFJTDTO :: getQt6).sum());
        total.setQt7(data.stream().mapToDouble(ProThinkLFJTDTO :: getQt7).sum());
        total.setQt8(data.stream().mapToDouble(ProThinkLFJTDTO :: getQt8).sum());
        total.setQt9(data.stream().mapToDouble(ProThinkLFJTDTO :: getQt9).sum());
        total.setQt10(data.stream().mapToDouble(ProThinkLFJTDTO :: getQt10).sum());
        total.setQt11(data.stream().mapToDouble(ProThinkLFJTDTO :: getQt11).sum());
        total.setQt12(data.stream().mapToDouble(ProThinkLFJTDTO :: getQt12).sum());
        total.setQt13(data.stream().mapToDouble(ProThinkLFJTDTO :: getQt13).sum());
        total.setQt14(data.stream().mapToDouble(ProThinkLFJTDTO :: getQt14).sum());
        total.setQt15(data.stream().mapToDouble(ProThinkLFJTDTO :: getQt15).sum());

        map.put("list",data);
        map.put("total",total);
        return map;
    }
    /**
     * 经营分析报表 产品线
     * @param dataType
     * @param dataTable
     * @param paramMap
     * @param map
     * @param apiRequest
     * @return
     */
    public Map<String,Object> getProThinkMapCPX(String dataType, String dataTable, Map paramMap, Map map, ApiRequest apiRequest){
        Map<String,Object> tempMap =  new HashMap<String,Object>();
        tempMap.put("enumCode","billingEnum");
        tempMap.put("proIds",StringUtils.isEmpty(paramMap.get("proIds")) ? null : paramMap.get("proIds"));
        List<CommonEnum> enums = commonEnumMapper.selectListByParentEnumCodeAndMap(tempMap);
        List<ProThinkCPXDTO> data = new ArrayList<ProThinkCPXDTO>();
        for (CommonEnum commonEnum : enums) {
            data.add(new ProThinkCPXDTO(Long.parseLong(commonEnum.getEnumCode()),commonEnum.getEnumName()));
        }
        data = initZero(data);
        ProThinkCPXDTO total = initZero(new ProThinkCPXDTO());
        //业务
        List<YWDTO> ywData = initZero(staffOrganProductMapper.ywData(paramMap));
        for (ProThinkCPXDTO item : data) {
            int proId = item.getProId().intValue();
            if (proId == BS_PROID || proId == HZ_PROID || proId == BS_SHARP){
                int orgAttr = proId == HZ_PROID ? 2 : 1;//保司或互助
                int sharp = proId == BS_SHARP ? 1 : 0;//是否反欺诈
                List<YWDTO> searchData1 = ywData.stream().filter(p -> p.getOrgAttr() == orgAttr && p.getSharp() == sharp).collect(Collectors.toList());
                long caseNum1 = searchData1.stream().map(YWDTO::getSurveyInfoId).distinct().count();//案件数量
                item.setYw1(Double.valueOf(caseNum1));
            }
        }
        total.setYw1(data.stream().mapToDouble(ProThinkCPXDTO::getYw1).sum());
        //收入
        List<SRDTO> billData = initZero(staffOrganProductMapper.srBillData(paramMap));//开票
        List<SRDTO> accData = initZero(staffOrganProductMapper.srAccData(paramMap));//到账
        for (ProThinkCPXDTO item : data) {
            List<SRDTO> tempBillData = billData.stream().filter(p -> p.getProId().intValue() == item.getProId().intValue()).collect(Collectors.toList());
            List<SRDTO> tempAccData = accData.stream().filter(p -> p.getProId().intValue() == item.getProId().intValue()).collect(Collectors.toList());
            double billMoney = tempBillData.stream().mapToDouble(SRDTO::getBillMoney).sum();
            item.setSr1(billMoney);
            double sr2 = billData.stream().filter(p -> p.getProId().intValue() == item.getProId().intValue()).mapToDouble(SRDTO::getBillMoney).sum();
            item.setSr2(sr2);
            item.setSr2Rate(fourRate(item.getSr1(),item.getSr2()));

            double billMoneyTax = tempBillData.stream().mapToDouble(SRDTO::getBillMoneyTax).sum();
            item.setSr3(item.getSr1() - billMoneyTax);//产品线开票非税收入
            item.setSr4(billMoneyTax);

            double accMoney = tempAccData.stream().mapToDouble(SRDTO::getAccMoney).sum();
            item.setSr5(accMoney);
            item.setSr6Rate(fourRate(item.getSr5(),item.getSr1()));


            item.setSr7(accMoney);//该产品线的核算收入。便于支出比例
            int proId = item.getProId().intValue();
            if (proId == BS_PROID || proId == HZ_PROID || proId == BS_SHARP){
                int orgAttr = proId == HZ_PROID ? 2 : 1;//保司或互助
                int sharp = proId == BS_SHARP ? 1 : 0;//是否反欺诈
                List<YWDTO> tempYwData = ywData.stream().filter(p -> p.getOrgAttr() == orgAttr && p.getSharp() == sharp).collect(Collectors.toList());
                double sr7 = tempYwData.stream().filter(distinctByKey(YWDTO::getSurveyInfoId)).mapToDouble(YWDTO::getAccMoney).sum();
                item.setSr7(sr7);//业务核算收入
            }
        }
        total.setSr1(data.stream().mapToDouble(ProThinkCPXDTO :: getSr1).sum());
        total.setSr2(data.stream().mapToDouble(ProThinkCPXDTO :: getSr2).sum());
        total.setSr2Rate(fourRate(total.getSr1(),total.getSr2()));
        total.setSr3(data.stream().mapToDouble(ProThinkCPXDTO :: getSr3).sum());
        total.setSr4(data.stream().mapToDouble(ProThinkCPXDTO :: getSr4).sum());
        total.setSr5(data.stream().mapToDouble(ProThinkCPXDTO :: getSr5).sum());
        total.setSr6(data.stream().mapToDouble(ProThinkCPXDTO :: getSr6).sum());
        total.setSr6Rate(fourRate(total.getSr5(),total.getSr1()));

        List<DetailDTO> zc1Data = initZero(staffOrganProductMapper.zc1Data(paramMap));//工资、绩效、打卡、渠道费、员工数
        List<DetailDTO> zc2Data = initZero(staffOrganProductMapper.zc2Data(paramMap));//报销
        //支出
        for (ProThinkCPXDTO item : data) {
            Double totalAccMoney = data.stream().mapToDouble(ProThinkCPXDTO :: getSr7).sum();
            item.setRate(fourRate(item.getSr7(),totalAccMoney));
            DetailDTO cb = cb(1,null,null,item.getRate(),zc1Data,zc2Data,1);//业务主营
            item.setZc1(cb.totalZc1(cb));item.setZc1Detail(cb);
            DetailDTO gl = gl(1,null,null,item.getRate(),zc1Data,zc2Data,3);//业务管理
            item.setZc3(gl.totalZc3(gl));item.setZc3Detail(gl);
            DetailDTO xs = xs(1,null,null,item.getRate(),zc1Data,zc2Data,2);//业务销售
            item.setZc2(xs.totalZc2(xs));item.setZc2Detail(xs);
        }
        total.setZc1(data.stream().mapToDouble(ProThinkCPXDTO :: getZc1).sum());
        total.setZc2(data.stream().mapToDouble(ProThinkCPXDTO :: getZc2).sum());
        total.setZc3(data.stream().mapToDouble(ProThinkCPXDTO :: getZc3).sum());

        //利润
        for (ProThinkCPXDTO item : data) {
            item.setLr1(item.getSr3() - item.getZc1());
            item.setLr1Rate(fourRate(item.getLr1(),item.getSr3()));
            item.setLr2(item.getSr3() - (item.getZc1() + item.getZc2() + item.getZc3()));
            item.setLr2Rate(fourRate(item.getLr2(),item.getSr3()));
        }
        total.setLr1(data.stream().mapToDouble(ProThinkCPXDTO :: getLr1).sum());
        total.setLr1Rate(fourRate(total.getLr1(),total.getSr3()));
        total.setLr2(data.stream().mapToDouble(ProThinkCPXDTO :: getLr2).sum());
        total.setLr2Rate(fourRate(total.getLr2(),total.getSr3()));

        //其他
        for (ProThinkCPXDTO item : data) {
            List<DetailDTO> collect = zc1Data.stream().filter(p -> p.getCostType() == 1 && p.getStaffState() != 6).collect(Collectors.toList());
            item.setQt1(collect.stream().count() * item.getRate());//主营业务员工数
            item.setQt2(fourRate(item.getSr3(),item.getQt1()));
            item.setQt3(fourRate(item.getSr5(),item.getQt1()));
            item.setQt4(fourRate(item.getZc1() + item.getZc2() + item.getZc3(),item.getQt1()));
            item.setQt5(fourRate(item.getSr3() - item.getZc1(),item.getQt1()));
            item.setQt6(fourRate(item.getSr3() - item.getZc1(),item.getSr3()));
            collect = zc1Data.stream().filter(p -> p.getStaffState() != 6).collect(Collectors.toList());
            item.setQt7(collect.stream().count() * item.getRate());//在编员工数
            item.setQt8(fourRate(item.getSr3(),item.getQt7()));
            item.setQt9(fourRate(item.getSr5(),item.getQt7()));
            item.setQt10(fourRate(item.getSr7() ,item.getQt7()));
            item.setQt11(fourRate(item.getZc1() + item.getZc2() + item.getZc3(),item.getQt7()));
            item.setQt12(fourRate(item.getSr3() - item.getZc1(),item.getQt7()));
            item.setQt13(fourRate(item.getSr3() - item.getZc1(),item.getSr3()));
        }
        total.setQt1(data.stream().mapToDouble(ProThinkCPXDTO :: getQt1).sum());
        total.setQt2(data.stream().mapToDouble(ProThinkCPXDTO :: getQt2).sum());
        total.setQt3(data.stream().mapToDouble(ProThinkCPXDTO :: getQt3).sum());
        total.setQt4(data.stream().mapToDouble(ProThinkCPXDTO :: getQt4).sum());
        total.setQt5(data.stream().mapToDouble(ProThinkCPXDTO :: getQt5).sum());
        total.setQt6(data.stream().mapToDouble(ProThinkCPXDTO :: getQt6).sum());
        total.setQt7(data.stream().mapToDouble(ProThinkCPXDTO :: getQt7).sum());
        total.setQt8(data.stream().mapToDouble(ProThinkCPXDTO :: getQt8).sum());
        total.setQt9(data.stream().mapToDouble(ProThinkCPXDTO :: getQt9).sum());
        total.setQt10(data.stream().mapToDouble(ProThinkCPXDTO :: getQt10).sum());
        total.setQt11(data.stream().mapToDouble(ProThinkCPXDTO :: getQt11).sum());
        total.setQt12(data.stream().mapToDouble(ProThinkCPXDTO :: getQt12).sum());
        total.setQt13(data.stream().mapToDouble(ProThinkCPXDTO :: getQt13).sum());
        total.setQt14(data.stream().mapToDouble(ProThinkCPXDTO :: getQt14).sum());
        total.setQt15(data.stream().mapToDouble(ProThinkCPXDTO :: getQt15).sum());

        map.put("list",data);
        map.put("total",total);
        return map;
    }


    

    /**
     * 转换对象。 DOUBLE类型若为NULL 初始化为0
     * @param data
     * @param <T>
     * @return
     */
    private <T> List<T> initZero(List<T> data){
        for (T item : data) {
            initZero(item);
        }
        return data;
    }
    private <T> T initZero(T item){
        Field[] declaredFields = item.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if (Double.class.equals(declaredField.getType())) {
                try {
                    if (declaredField.get(item) == null) {
                        declaredField.set(item,0D);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return item;
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId().intValue() == roleId.intValue()){
                return true;
            }
        }
        return false;
    }

    /**
     * 组织成本数据 按比例分摊
     * @param conType
     * @param conId
     * @param conName
     * @param rate
     * @param zc1Data 工资、绩效、打卡、渠道费 数据
     * @param zc2Data 报销
     * @param costType 成本属性:1.主营成本，2：销售费用，3：管理费用
     * @return
     */
    private DetailDTO cb(int conType,Long conId,String conName,Double rate,List<DetailDTO> zc1Data,List<DetailDTO> zc2Data,int costType){
        List<DetailDTO> tempData = null,temp2Data = null,tempData3 = null;
        if (conType == 1){
            tempData = zc1Data.stream().filter(p -> p.getCostType() == costType).collect(Collectors.toList());
            tempData3 = zc1Data.stream().collect(Collectors.toList());
            temp2Data = zc2Data.stream().collect(Collectors.toList());
        }else if (conType == 2){
            tempData = zc1Data.stream().filter(p -> p.getOrgId().intValue() == conId.intValue() && p.getCostType() == costType).collect(Collectors.toList());
            tempData3 = zc1Data.stream().filter(p -> p.getOrgId().intValue() == conId.intValue()).collect(Collectors.toList());
            temp2Data = zc2Data.stream().filter(p -> p.getOrgId().intValue() == conId.intValue()).collect(Collectors.toList());
        }else if (conType == 3){
            tempData = zc1Data.stream().filter(p -> p.getCompanyId() != null && p.getCompanyId().intValue() == conId.intValue() && p.getCostType() == costType).collect(Collectors.toList());
            tempData3 = zc2Data.stream().filter(p -> p.getCompanyId() != null && p.getCompanyId().intValue() == conId.intValue()).collect(Collectors.toList());
            temp2Data = zc2Data.stream().filter(p -> p.getCompanyId() != null && p.getCompanyId().intValue() == conId.intValue()).collect(Collectors.toList());
        }
        DetailDTO data = new DetailDTO(conId,conName,costType,
                tempData.stream().mapToDouble(DetailDTO :: getGz1).sum() * rate,
                Math.abs(tempData.stream().mapToDouble(DetailDTO :: getGz2).sum()) * rate,
                Math.abs(tempData.stream().mapToDouble(DetailDTO :: getGz3).sum()) * rate,
                tempData.stream().mapToDouble(DetailDTO :: getGz4).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx1).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx2).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx3).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx4).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx5).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx6).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx7).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx8).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getDk1).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getDk2).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getDk3).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getDk4).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getDk5).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getDk6).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getDk7).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getDk8).sum() * rate,
                tempData3.stream().mapToDouble(DetailDTO :: getQdMoney).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxCb1).sum() * rate);
        return data;
    }

    /**
     * 组织管理费用
     * @param conType 比较条件类型  1无机构 2机构  3公司
     * @param conId
     * @param conName
     * @param rate
     * @param zc1Data 工资、绩效、打卡、渠道费 数据
     * @param zc2Data 报销
     * @param costType 成本属性:1.主营成本，2：销售费用，3：管理费用
     * @return
     */
    private DetailDTO gl(int conType,Long conId,String conName,Double rate,List<DetailDTO> zc1Data,List<DetailDTO> zc2Data,int costType){
        List<DetailDTO> tempData = null,temp2Data = null;
        if (conType == 1){
            tempData = zc1Data.stream().filter(p -> p.getCostType() == costType).collect(Collectors.toList());
            temp2Data = zc2Data.stream().collect(Collectors.toList());
        }else if (conType == 2){
            tempData = zc1Data.stream().filter(p -> p.getOrgId().intValue() == conId.intValue() && p.getCostType() == costType).collect(Collectors.toList());
            temp2Data = zc2Data.stream().filter(p -> p.getOrgId().intValue() == conId.intValue()).collect(Collectors.toList());
        }else if (conType == 3){
            tempData = zc1Data.stream().filter(p -> p.getCompanyId() != null && p.getCompanyId().intValue() == conId.intValue() && p.getCostType() == costType).collect(Collectors.toList());
            temp2Data = zc2Data.stream().filter(p -> p.getCompanyId() != null && p.getCompanyId().intValue() == conId.intValue()).collect(Collectors.toList());
        }
        DetailDTO data = new DetailDTO(conId,conName,costType,
                tempData.stream().mapToDouble(DetailDTO :: getGz1).sum() * rate,
                Math.abs(tempData.stream().mapToDouble(DetailDTO :: getGz2).sum()) * rate,
                        Math.abs(tempData.stream().mapToDouble(DetailDTO :: getGz3).sum()) * rate,
                tempData.stream().mapToDouble(DetailDTO :: getGz4).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx1).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx2).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx3).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx4).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx5).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx6).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx7).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx8).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl1).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl2).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl3).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl4).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl5).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl6).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl7).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl8).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl9).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl10).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl11).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl12).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl13).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl14).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl15).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl16).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl17).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl18).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl19).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl20).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl21).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl22).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl23).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl24).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl25).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl26).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl27).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl28).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl29).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl30).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl31).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl32).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl33).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl34).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxGl35).sum() * rate
        );
        return data;
    }

    /**
     * 组织销售费用
     * @param conType 比较条件类型  1无机构 2机构  3公司
     * @param conId
     * @param conName
     * @param rate
     * @param zc1Data
     * @param zc2Data
     * @param costType 成本属性:1.主营成本，2：销售费用，3：管理费用
     * @return
     */
    private DetailDTO xs(int conType,Long conId,String conName,Double rate,List<DetailDTO> zc1Data,List<DetailDTO> zc2Data,int costType){
        List<DetailDTO> tempData = null,temp2Data = null;
        if (conType == 1){
            tempData = zc1Data.stream().filter(p -> p.getCostType() == costType).collect(Collectors.toList());
            temp2Data = zc2Data.stream().collect(Collectors.toList());
        }else if (conType == 2){
            tempData = zc1Data.stream().filter(p -> p.getOrgId().intValue() == conId.intValue() && p.getCostType() == costType).collect(Collectors.toList());
            temp2Data = zc2Data.stream().filter(p -> p.getOrgId().intValue() == conId.intValue()).collect(Collectors.toList());
        }else if (conType == 3){
            tempData = zc1Data.stream().filter(p ->p.getCompanyId() != null && p.getCompanyId().intValue() == conId.intValue() && p.getCostType() == costType).collect(Collectors.toList());
            temp2Data = zc2Data.stream().filter(p ->p.getCompanyId() != null && p.getCompanyId().intValue() == conId.intValue()).collect(Collectors.toList());
        }
        DetailDTO data = new DetailDTO("",conId,conName,costType,
                tempData.stream().mapToDouble(DetailDTO :: getGz1).sum() * rate,
                Math.abs(tempData.stream().mapToDouble(DetailDTO :: getGz2).sum()) * rate,
                Math.abs(tempData.stream().mapToDouble(DetailDTO :: getGz3).sum()) * rate,
                tempData.stream().mapToDouble(DetailDTO :: getGz4).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx1).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx2).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx3).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx4).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx5).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx6).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx7).sum() * rate,
                tempData.stream().mapToDouble(DetailDTO :: getJx8).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxXs1).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxXs2).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxXs3).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxXs4).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxXs5).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxXs6).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxXs7).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxXs8).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxXs9).sum() * rate,
                temp2Data.stream().mapToDouble(DetailDTO :: getBxXs10).sum() * rate
        );
        return data;
    }

    /**
     * 计算比例 保留4位小数
     * @param a
     * @param b
     * @return
     */
    private Double fourRate(Double a, Double b){
        if (b == 0) return 0D;
        return new BigDecimal(a / b).setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 根据某个字段去重
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }





    /********************************************************************************************************************************************以下旧版
     /**
     * 产品经营分析报表
     * @param dataType
     * @param dataTable
     * @param paramMap
     * @param map
     * @param apiRequest
     * @return
     */
    public Map<String,Object> getProThinkMap(String dataType, String dataTable, Map paramMap, Map map, ApiRequest apiRequest){
        //行数据
        Map<String,Object> enumMap =  new HashMap<String,Object>();
        enumMap.put("enumCode","billingEnum");
        enumMap.put("proIds",apiRequest.getString("proIds"));
        List<CommonEnum> enums = commonEnumMapper.selectListByParentEnumCodeAndMap(enumMap);
        List<ProThinkDTO> list = new ArrayList<>();
        for (CommonEnum anEnum : enums) {
            list.add(new ProThinkDTO(anEnum.getId(),anEnum.getEnumName()));
        }
        //列数据查询
        //1.平台复审收入、保司终审收入
        paramMap.put("orgAttr",2);
        List<Double> moneysHz = surveyBusinessReportMapper.selectThinkOprMoney(paramMap);
        paramMap.put("orgAttr",1);
        List<Double> moneysBs = surveyBusinessReportMapper.selectThinkOprMoney(paramMap);
        //2.开票收入、到账输入
        List<ThinkBillAndAccDTO> billAndAccMoney = surveyBusinessReportMapper.selectThinkBillAndAcc(paramMap);
        //3.主营业务成本与销售费用与财务费用
        //工资、绩效
        //打卡报销、每刻报销
        List<ThinkProDataDTO> proDataMoney = surveyBusinessReportMapper.selectThinkInOut(paramMap);
        List<ThinkProDataDTO> hzBsDataMoney = proDataMoney.stream().filter(p ->  p.getOrganProduct() != null && p.getOrganProduct().intValue() == -100).collect(Collectors.toList());//如果归属机构是 互助+保司。 成本则需要根据保司积分、互助积分按比例分配
        Double bsScore = hzBsDataMoney.stream().collect(Collectors.summingDouble(e -> getValue(e.getJxBsScore())));
        Double hzScore = hzBsDataMoney.stream().collect(Collectors.summingDouble(e -> getValue(e.getJxHzScore())));

        //列数据赋值
        for (ProThinkDTO item : list) {
            Long proId = item.getProId();
            if (proId.intValue() == 130){//互助
                item.setFsMoney(moneysHz.get(0));
                item.setFsMoneyHb(moneysHz.get(1));item.setFsRateHb(rate(item.getFsMoney(),item.getFsMoneyHb()));
                item.setFsMoneyTb(moneysHz.get(2));item.setFsRateTb(rate(item.getFsMoney(),item.getFsMoneyTb()));
                item.setZsMoney(moneysHz.get(3));
                item.setZsMoneyHb(moneysHz.get(4));item.setZsRateHb(rate(item.getZsMoney(),item.getZsMoneyHb()));
                item.setZsMoneyTb(moneysHz.get(5));item.setZsRateTb(rate(item.getZsMoney(),item.getZsMoneyTb()));
            }else if (proId.intValue() == 78){//保司
                item.setFsMoney(moneysBs.get(0));
                item.setFsMoneyHb(moneysBs.get(1));item.setFsRateHb(rate(item.getFsMoney(),item.getFsMoneyHb()));
                item.setFsMoneyTb(moneysBs.get(2));item.setFsRateTb(rate(item.getFsMoney(),item.getFsMoneyTb()));
                item.setZsMoney(moneysBs.get(3));
                item.setZsMoneyHb(moneysBs.get(4));item.setZsRateHb(rate(item.getZsMoney(),item.getZsMoneyHb()));
                item.setZsMoneyTb(moneysBs.get(5));item.setZsRateTb(rate(item.getZsMoney(),item.getZsMoneyTb()));
            }
            //开票、到账
            for (ThinkBillAndAccDTO thinkBillAndAccDTO : billAndAccMoney) {
                if (proId.intValue() == thinkBillAndAccDTO.getProId().intValue()) {
                    //开票
                    item.setKpMoney(thinkBillAndAccDTO.getBillMoney());
                    item.setKpMoneyHb(thinkBillAndAccDTO.getBillMoneyHb());item.setKpRateHb(rate(item.getKpMoney(),item.getKpMoneyHb()));
                    item.setKpMoneyTb(thinkBillAndAccDTO.getBillMoneyTb());item.setKpRateTb(rate(item.getKpMoney(),item.getKpMoneyTb()));
                    //到账
                    item.setDzMoney(thinkBillAndAccDTO.getAccMoney());
                    item.setDzMoneyHb(thinkBillAndAccDTO.getAccMoneyHb());item.setDzRateHb(rate(item.getDzMoney(),item.getDzMoneyHb()));
                    item.setDzMoneyTb(thinkBillAndAccDTO.getAccMoneyTb());item.setDzRateTb(rate(item.getDzMoney(),item.getDzMoneyTb()));
                }
            }
            ThinkProDataDTO detail = new ThinkProDataDTO();
            ThinkOutDataDTO outDetail = new ThinkOutDataDTO();
            //同产品的所有数据
            List<ThinkProDataDTO> collect = proDataMoney.stream().filter(p -> p.getOrganProduct() != null && p.getOrganProduct().intValue() == proId.intValue()).collect(Collectors.toList());
            Double inMoney = getInMoney(collect);
            Double bCostMoney = collect.stream().collect(Collectors.summingDouble(e -> getValue(e.getbCostMoney())));
            Double cCostMoney = collect.stream().collect(Collectors.summingDouble(e -> getValue(e.getcCostMoney())));
            item.setInMoney(inMoney);
            detail = setDetail(detail,collect,1D);
            outDetail = setOutDetail(outDetail,collect,1D);
            item.setOutMoney(bCostMoney);
            item.setCwMoney(cCostMoney);

            if (proId.intValue() == 130 || proId.intValue() == 78){//互助 或者  保司产品的时候
                if (hzBsDataMoney.size() > 0) {
                    Double hzRate = 1D,bsRate = 1D;
                    if (bsScore == 0D && hzScore == 0){//如果保司互助积分都为0，那么就平分
                        hzRate = 0.5D;bsRate = 0.5D;
                    }else if (bsScore > 0D && hzScore == 0D){
                        hzRate = 0D;bsRate = 1D;
                    }else if (bsScore ==0D && hzScore > 0D){
                        hzRate = 1D;bsRate = 0D;
                    }else{
                        hzRate = hzScore / (hzScore + bsScore);
                        bsRate = bsScore / (hzScore + bsScore);
                    }
                    Double tempRate = proId.intValue() == 130 ? bsRate : hzRate;
                    inMoney = getInMoney(hzBsDataMoney);
                    detail = setDetail(detail,hzBsDataMoney,tempRate);//按比例累加成本详情
                    outDetail = setOutDetail(outDetail,hzBsDataMoney,tempRate);//按比例累加销售详情
                    bCostMoney = hzBsDataMoney.stream().collect(Collectors.summingDouble(e -> getValue(e.getbCostMoney())));
                    cCostMoney = hzBsDataMoney.stream().collect(Collectors.summingDouble(e -> getValue(e.getcCostMoney())));
                    if (proId.intValue() == 130){
                        item.setInMoney(item.getInMoney() + inMoney * bsRate);
                        item.setOutMoney(item.getOutMoney() + bCostMoney * bsRate);
                        item.setCwMoney(item.getCwMoney() + cCostMoney * bsRate);
                    }else if (proId.intValue() == 78){
                        item.setInMoney(item.getInMoney() + inMoney * hzRate);
                        item.setOutMoney(item.getOutMoney() + bCostMoney * hzRate);
                        item.setCwMoney(item.getCwMoney() + cCostMoney * hzRate);
                    }
                }
            }
            item.setDetail(detail);
            item.setOutDetail(outDetail);
        }
        ProThinkDTO total = new ProThinkDTO();
        total.setFsMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getFsMoney()))));
        total.setFsMoneyTb(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getFsMoneyTb()))));
        total.setFsMoneyHb(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getFsMoneyHb()))));
        total.setFsRateTb(rate(total.getFsMoney(),total.getFsMoneyTb()));
        total.setFsRateHb(rate(total.getFsMoney(),total.getFsMoneyHb()));
        total.setZsMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getZsMoney()))));
        total.setZsMoneyTb(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getZsMoneyTb()))));
        total.setZsMoneyHb(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getZsMoneyHb()))));
        total.setZsRateTb(rate(total.getZsMoney(),total.getZsMoneyTb()));
        total.setZsRateHb(rate(total.getZsMoney(),total.getZsMoneyHb()));
        total.setKpMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getKpMoney()))));
        total.setKpMoneyTb(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getKpMoneyTb()))));
        total.setKpMoneyHb(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getKpMoneyHb()))));
        total.setKpRateTb(rate(total.getKpMoney(),total.getKpMoneyTb()));
        total.setKpRateHb(rate(total.getKpMoney(),total.getKpMoneyHb()));
        total.setDzMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getDzMoney()))));
        total.setDzMoneyTb(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getDzMoneyTb()))));
        total.setDzMoneyHb(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getDzMoneyHb()))));
        total.setDzRateTb(rate(total.getDzMoney(),total.getDzMoneyTb()));
        total.setDzRateHb(rate(total.getDzMoney(),total.getDzMoneyHb()));
        total.setInMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getInMoney()))));
        total.setOutMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getOutMoney()))));
        total.setCwMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getCwMoney()))));
        map.put("list",list);
        map.put("total",total);
        return map;
    }

    /**
     * 机构经营分析报表(业务)
     * @param dataType
     * @param dataTable
     * @param paramMap
     * @param map
     * @param apiRequest
     * @return
     */
    public Map<String,Object> getOrgThinkBusMap(String dataType, String dataTable, Map paramMap, Map map, ApiRequest apiRequest){

        Map<String,Object> enumMap =  new HashMap<String,Object>();
        enumMap.put("enumCode","billingEnum");
        List<CommonEnum> enums = commonEnumMapper.selectListByParentEnumCodeAndMap(enumMap);
        //行数据
        Map<String,Object> orgMap =  new HashMap<String,Object>();
        orgMap.put("organAttribute",1);
        orgMap.put("orgIds",apiRequest.getString("orgIds"));
        orgMap.put("proIds",apiRequest.getString("proIds"));
        List<StaffOrgan> organs = staffOrganMapper.list(orgMap);
        List<ProThinkOrgDTO> list = new ArrayList<>();
        for (StaffOrgan organ : organs) {
            if (organ.getOrganProduct() == null) {
                organ.setOrganProduct(-1L);
            }
            for (CommonEnum anEnum : enums) {
                if (anEnum.getId().intValue() == organ.getOrganProduct().intValue()) {
                    organ.setOrganProductName(anEnum.getEnumName());
                }
            }
            if (organ.getOrganProduct().intValue() == -100){
                organ.setOrganProductName("互助+保司");
            }
            list.add(new ProThinkOrgDTO(organ.getId(),organ.getName(),organ.getOrganProduct(),organ.getOrganProductName()));
        }
        //列数据查询
        //1.平台复审收入、保司终审收入
        List<ThinkProBusDataDTO> busData = surveyBusinessReportMapper.selectThinkOprMoneyOrg(paramMap);
        //2.开票收入、到账输入
        //开票
        paramMap.put("searchTimeType",1);
        List<ThinkBillAndAccDTO> caseBills = surveyBusinessReportMapper.selectThinkCaseBill(paramMap);
        paramMap.put("searchTimeType",2);
        List<ThinkBillAndAccDTO> caseBillsTb = surveyBusinessReportMapper.selectThinkCaseBill(paramMap);
        paramMap.put("searchTimeType",3);
        List<ThinkBillAndAccDTO> caseBillsHb = surveyBusinessReportMapper.selectThinkCaseBill(paramMap);
        //到账
        paramMap.put("searchTimeType",1);
        List<ThinkBillAndAccDTO> caseAccs = surveyBusinessReportMapper.selectThinkCaseAcc(paramMap);
        paramMap.put("searchTimeType",2);
        List<ThinkBillAndAccDTO> caseAccsTb = surveyBusinessReportMapper.selectThinkCaseAcc(paramMap);
        paramMap.put("searchTimeType",3);
        List<ThinkBillAndAccDTO> caseAccsHb = surveyBusinessReportMapper.selectThinkCaseAcc(paramMap);

        //开票
        paramMap.put("searchTimeType",1);
        List<ThinkBillAndAccDTO> bills = surveyBusinessReportMapper.selectThinkBill(paramMap);
        paramMap.put("searchTimeType",2);
        List<ThinkBillAndAccDTO> billsTb = surveyBusinessReportMapper.selectThinkBill(paramMap);
        paramMap.put("searchTimeType",3);
        List<ThinkBillAndAccDTO> billsHb = surveyBusinessReportMapper.selectThinkBill(paramMap);
        //到账
        paramMap.put("searchTimeType",1);
        List<ThinkBillAndAccDTO> accs = surveyBusinessReportMapper.selectThinkAcc(paramMap);
        paramMap.put("searchTimeType",2);
        List<ThinkBillAndAccDTO> accsTb = surveyBusinessReportMapper.selectThinkAcc(paramMap);
        paramMap.put("searchTimeType",3);
        List<ThinkBillAndAccDTO> accsHb = surveyBusinessReportMapper.selectThinkAcc(paramMap);
//        List<ThinkBillAndAccDTO> billAndAccMoney = surveyBusinessReportMapper.selectThinkBillAndAccOrg(paramMap);
        //3.主营业务成本与销售费用与财务费用
        //工资、绩效
        //打卡报销、每刻报销
        List<ThinkProDataDTO> proDataMoney = surveyBusinessReportMapper.selectThinkInOut(paramMap);

        //列数据赋值
        for (ProThinkOrgDTO item : list) {
            Long orgId = item.getOrgId();
            //复审、终审
            item.setFsMoney(busData.stream().filter(p -> p.getOrgId() != null && p.getOrgId().intValue() == orgId.intValue() && p.getSearchType() == 1 && p.getDataType() == 1).collect(Collectors.summingDouble(e -> getValue(e.getMoney()))));
            item.setFsMoneyHb(busData.stream().filter(p -> p.getOrgId() != null && p.getOrgId().intValue() == orgId.intValue() && p.getSearchType() == 2 && p.getDataType() == 1).collect(Collectors.summingDouble(e -> getValue(e.getMoney()))));
            item.setFsMoneyTb(busData.stream().filter(p -> p.getOrgId() != null && p.getOrgId().intValue() == orgId.intValue() && p.getSearchType() == 3 && p.getDataType() == 1).collect(Collectors.summingDouble(e -> getValue(e.getMoney()))));
            item.setFsRateHb(rate(item.getFsMoney(),item.getFsMoneyHb()));
            item.setFsRateTb(rate(item.getFsMoney(),item.getFsMoneyTb()));

            item.setZsMoney(busData.stream().filter(p -> p.getOrgId() != null && p.getOrgId().intValue() == orgId.intValue() && p.getSearchType() == 1 && p.getDataType() == 2).collect(Collectors.summingDouble(e -> getValue(e.getMoney()))));
            item.setZsMoneyHb(busData.stream().filter(p -> p.getOrgId() != null && p.getOrgId().intValue() == orgId.intValue() && p.getSearchType() == 2 && p.getDataType() == 2).collect(Collectors.summingDouble(e -> getValue(e.getMoney()))));
            item.setZsMoneyTb(busData.stream().filter(p -> p.getOrgId() != null && p.getOrgId().intValue() == orgId.intValue() && p.getSearchType() == 3 && p.getDataType() == 2).collect(Collectors.summingDouble(e -> getValue(e.getMoney()))));
            item.setZsRateHb(rate(item.getZsMoney(),item.getZsMoneyHb()));
            item.setZsRateTb(rate(item.getZsMoney(),item.getZsMoneyTb()));

            //开票、到账
            if (item.getProId().intValue() == -100){
                item.setKpMoney(caseBills.stream().filter(p -> p.getProId() != null && p.getProId().intValue() == orgId.intValue()).collect(Collectors.summingDouble(e -> getValue(e.getBillMoney()))));
                item.setKpMoneyHb(caseBillsHb.stream().filter(p -> p.getProId() != null && p.getProId().intValue() == orgId.intValue()).collect(Collectors.summingDouble(e -> getValue(e.getBillMoney()))));
                item.setKpMoneyTb(caseBillsTb.stream().filter(p -> p.getProId() != null && p.getProId().intValue() == orgId.intValue()).collect(Collectors.summingDouble(e -> getValue(e.getBillMoney()))));
                item.setKpRateHb(rate(item.getKpMoney(),item.getKpMoneyHb()));
                item.setKpRateTb(rate(item.getKpMoney(),item.getKpMoneyTb()));
                item.setDzMoney(caseAccs.stream().filter(p -> p.getProId() != null && p.getProId().intValue() == orgId.intValue()).collect(Collectors.summingDouble(e -> getValue(e.getAccMoney()))));
                item.setDzMoneyHb(caseAccsHb.stream().filter(p -> p.getProId() != null && p.getProId().intValue() == orgId.intValue()).collect(Collectors.summingDouble(e -> getValue(e.getAccMoney()))));
                item.setDzMoneyTb(caseAccsTb.stream().filter(p -> p.getProId() != null && p.getProId().intValue() == orgId.intValue()).collect(Collectors.summingDouble(e -> getValue(e.getAccMoney()))));
                item.setDzRateHb(rate(item.getDzMoney(),item.getDzMoneyHb()));
                item.setDzRateTb(rate(item.getDzMoney(),item.getDzMoneyTb()));
            }else{
                item.setKpMoney(bills.stream().filter(p -> p.getProId() != null && p.getProId().intValue() == orgId.intValue()).collect(Collectors.summingDouble(e -> getValue(e.getBillMoney()))));
                item.setKpMoneyHb(billsHb.stream().filter(p -> p.getProId() != null && p.getProId().intValue() == orgId.intValue()).collect(Collectors.summingDouble(e -> getValue(e.getBillMoney()))));
                item.setKpMoneyTb(billsTb.stream().filter(p -> p.getProId() != null && p.getProId().intValue() == orgId.intValue()).collect(Collectors.summingDouble(e -> getValue(e.getBillMoney()))));
                item.setKpRateHb(rate(item.getKpMoney(),item.getKpMoneyHb()));
                item.setKpRateTb(rate(item.getKpMoney(),item.getKpMoneyTb()));
                item.setDzMoney(accs.stream().filter(p -> p.getProId() != null && p.getProId().intValue() == orgId.intValue()).collect(Collectors.summingDouble(e -> getValue(e.getAccMoney()))));
                item.setDzMoneyHb(accsHb.stream().filter(p -> p.getProId() != null && p.getProId().intValue() == orgId.intValue()).collect(Collectors.summingDouble(e -> getValue(e.getAccMoney()))));
                item.setDzMoneyTb(accsTb.stream().filter(p -> p.getProId() != null && p.getProId().intValue() == orgId.intValue()).collect(Collectors.summingDouble(e -> getValue(e.getAccMoney()))));
                item.setDzRateHb(rate(item.getDzMoney(),item.getDzMoneyHb()));
                item.setDzRateTb(rate(item.getDzMoney(),item.getDzMoneyTb()));
            }

            ThinkProDataDTO detail = new ThinkProDataDTO();
            ThinkOutDataDTO outDetail = new ThinkOutDataDTO();
            //同机构的所有数据
            List<ThinkProDataDTO> collect = proDataMoney.stream().filter(p -> p.getOrgId() != null && p.getOrgId().intValue() == orgId.intValue()).collect(Collectors.toList());
            Double inMoney = getInMoney(collect);
            Double bCostMoney = collect.stream().collect(Collectors.summingDouble(e -> getValue(e.getbCostMoney())));
            Double cCostMoney = collect.stream().collect(Collectors.summingDouble(e -> getValue(e.getcCostMoney())));
            item.setInMoney(inMoney);
            detail = setDetail(detail,collect,1D);
            outDetail = setOutDetail(outDetail,collect,1D);
            item.setOutMoney(bCostMoney);
            item.setCwMoney(cCostMoney);
            item.setDetail(detail);
            item.setOutDetail(outDetail);
        }
        ProThinkDTO total = new ProThinkDTO();
        total.setFsMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getFsMoney()))));
        total.setFsMoneyTb(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getFsMoneyTb()))));
        total.setFsMoneyHb(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getFsMoneyHb()))));
        total.setFsRateTb(rate(total.getFsMoney(),total.getFsMoneyTb()));
        total.setFsRateHb(rate(total.getFsMoney(),total.getFsMoneyHb()));
        total.setZsMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getZsMoney()))));
        total.setZsMoneyTb(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getZsMoneyTb()))));
        total.setZsMoneyHb(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getZsMoneyHb()))));
        total.setZsRateTb(rate(total.getZsMoney(),total.getZsMoneyTb()));
        total.setZsRateHb(rate(total.getZsMoney(),total.getZsMoneyHb()));
        total.setKpMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getKpMoney()))));
        total.setKpMoneyTb(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getKpMoneyTb()))));
        total.setKpMoneyHb(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getKpMoneyHb()))));
        total.setKpRateTb(rate(total.getKpMoney(),total.getKpMoneyTb()));
        total.setKpRateHb(rate(total.getKpMoney(),total.getKpMoneyHb()));
        total.setDzMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getDzMoney()))));
        total.setDzMoneyTb(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getDzMoneyTb()))));
        total.setDzMoneyHb(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getDzMoneyHb()))));
        total.setDzRateTb(rate(total.getDzMoney(),total.getDzMoneyTb()));
        total.setDzRateHb(rate(total.getDzMoney(),total.getDzMoneyHb()));
        total.setInMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getInMoney()))));
        total.setOutMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getOutMoney()))));
        total.setCwMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getCwMoney()))));
        map.put("list",list);
        map.put("total",total);
        return map;
    }

    /**
     *机构经营分析报表(职能)
     * @param dataType
     * @param dataTable
     * @param paramMap
     * @param map
     * @param apiRequest
     * @return
     */
    public Map<String,Object> getOrgThinkPostMap(String dataType,String dataTable,Map paramMap,Map map,ApiRequest apiRequest){
        Map<String,Object> enumMap =  new HashMap<String,Object>();
        enumMap.put("enumCode","billingEnum");
        List<CommonEnum> enums = commonEnumMapper.selectListByParentEnumCodeAndMap(enumMap);
        //行数据
        Map<String,Object> orgMap =  new HashMap<String,Object>();
        orgMap.put("organAttribute",2);
        orgMap.put("orgIds",apiRequest.getString("orgIds"));
        orgMap.put("proIds",apiRequest.getString("proIds"));
        List<StaffOrgan> organs = staffOrganMapper.list(orgMap);
        List<ProThinkOrgDTO> list = new ArrayList<>();
        for (StaffOrgan organ : organs) {
            if (organ.getOrganProduct() == null) {
                organ.setOrganProduct(-1L);
            }
            for (CommonEnum anEnum : enums) {
                if (anEnum.getId().intValue() == organ.getOrganProduct().intValue()) {
                    organ.setOrganProductName(anEnum.getEnumName());
                }
            }
            if (organ.getOrganProduct().intValue() == -100){
                organ.setOrganProductName("互助+保司");
            }
            list.add(new ProThinkOrgDTO(organ.getId(),organ.getName(),organ.getOrganProduct(),organ.getOrganProductName()));
        }
        //3.主营业务成本与销售费用与财务费用
        //工资、绩效
        //打卡报销、每刻报销
        List<ThinkProDataDTO> proDataMoney = surveyBusinessReportMapper.selectThinkInOut(paramMap);

        //列数据赋值
        for (ProThinkOrgDTO item : list) {
            Long orgId = item.getOrgId();
            ThinkProDataDTO detail = new ThinkProDataDTO();
            ThinkOutDataDTO outDetail = new ThinkOutDataDTO();
            //同机构的所有数据
            List<ThinkProDataDTO> collect = proDataMoney.stream().filter(p -> p.getOrgId() != null && p.getOrgId().intValue() == orgId.intValue()).collect(Collectors.toList());
            Double inMoney = getInMoney(collect);
            Double bCostMoney = collect.stream().collect(Collectors.summingDouble(e -> getValue(e.getbCostMoney())));
            Double cCostMoney = collect.stream().collect(Collectors.summingDouble(e -> getValue(e.getcCostMoney())));
            item.setInMoney(inMoney);
            detail = setDetail(detail,collect,1D);
            outDetail = setOutDetail(outDetail,collect,1D);
            item.setOutMoney(bCostMoney);
            item.setCwMoney(cCostMoney);
            item.setDetail(detail);
            item.setOutDetail(outDetail);
        }
        ProThinkDTO total = new ProThinkDTO();
        total.setInMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getInMoney()))));
        total.setOutMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getOutMoney()))));
        total.setCwMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getCwMoney()))));
        map.put("list",list);
        map.put("total",total);
        return map;
    }
    /**
     *机构经营分析报表(业务管理)
     * @param dataType
     * @param dataTable
     * @param paramMap
     * @param map
     * @param apiRequest
     * @return
     */
    public Map<String,Object> getOrgThinkBusManagerMap(String dataType,String dataTable,Map paramMap,Map map,ApiRequest apiRequest){
        Map<String,Object> enumMap =  new HashMap<String,Object>();
        enumMap.put("enumCode","billingEnum");
        List<CommonEnum> enums = commonEnumMapper.selectListByParentEnumCodeAndMap(enumMap);
        //行数据
        Map<String,Object> orgMap =  new HashMap<String,Object>();
        orgMap.put("organAttribute",3);
        orgMap.put("orgIds",apiRequest.getString("orgIds"));
        orgMap.put("proIds",apiRequest.getString("proIds"));
        List<StaffOrgan> organs = staffOrganMapper.list(orgMap);
        List<ProThinkOrgDTO> list = new ArrayList<>();
        for (StaffOrgan organ : organs) {
            if (organ.getOrganProduct() == null) {
                organ.setOrganProduct(-1L);
            }
            for (CommonEnum anEnum : enums) {
                if (anEnum.getId().intValue() == organ.getOrganProduct().intValue()) {
                    organ.setOrganProductName(anEnum.getEnumName());
                }
            }
            if (organ.getOrganProduct().intValue() == -100){
                organ.setOrganProductName("互助+保司");
            }
            list.add(new ProThinkOrgDTO(organ.getId(),organ.getName(),organ.getOrganProduct(),organ.getOrganProductName()));
        }
        //3.主营业务成本与销售费用与财务费用
        //工资、绩效
        //打卡报销、每刻报销
        List<ThinkProDataDTO> proDataMoney = surveyBusinessReportMapper.selectThinkInOut(paramMap);

        //列数据赋值
        for (ProThinkOrgDTO item : list) {
            Long orgId = item.getOrgId();
            ThinkProDataDTO detail = new ThinkProDataDTO();
            ThinkOutDataDTO outDetail = new ThinkOutDataDTO();
            //同机构的所有数据
            List<ThinkProDataDTO> collect = proDataMoney.stream().filter(p -> p.getOrgId() != null && p.getOrgId().intValue() == orgId.intValue()).collect(Collectors.toList());
            Double inMoney = getInMoney(collect);
            Double bCostMoney = collect.stream().collect(Collectors.summingDouble(e -> getValue(e.getbCostMoney())));
            Double cCostMoney = collect.stream().collect(Collectors.summingDouble(e -> getValue(e.getcCostMoney())));
            item.setInMoney(inMoney);
            detail = setDetail(detail,collect,1D);
            outDetail = setOutDetail(outDetail,collect,1D);
            item.setOutMoney(bCostMoney);
            item.setCwMoney(cCostMoney);
            item.setDetail(detail);
            item.setOutDetail(outDetail);
        }
        ProThinkDTO total = new ProThinkDTO();
        total.setInMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getInMoney()))));
        total.setOutMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getOutMoney()))));
        total.setCwMoney(list.stream().collect(Collectors.summingDouble(e -> getValue(e.getCwMoney()))));
        map.put("list",list);
        map.put("total",total);
        return map;
    }

    @ApiMethod(needLogin = false,descript = "获取产品列表",value = "get-think-pro-list")
    @Override
    public ApiResponse getPros(ApiRequest apiRequest) {
        List<CommonEnum> enums = commonEnumMapper.selectListByParentEnumCode("billingEnum");
        List<ProThinkDTO> list = new ArrayList<>();
        for (CommonEnum anEnum : enums) {
            list.add(new ProThinkDTO(anEnum.getId(),anEnum.getEnumName()));
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
    }

    private Double getValue(Double value){
        return value == null ? 0 : value;
    }
    private Double getInMoney(List<ThinkProDataDTO> collect){
        return collect.stream().mapToDouble(e -> getValue(e.getGzMoney1()) + getValue(e.getGzMoney2())
                + getValue(e.getJxMoney1()) + getValue(e.getJxMoney2()) + getValue(e.getJxMoney3()) + getValue(e.getJxMoney4()) + getValue(e.getJxMoney5()) + getValue(e.getJxMoney6()) + getValue(e.getJxMoney7()) + getValue(e.getJxMoney8())
                + getValue(e.getBxMoney1()) + getValue(e.getBxMoney2()) + getValue(e.getBxMoney3()) + getValue(e.getBxMoney4()) + getValue(e.getBxMoney5()) + getValue(e.getBxMoney6()) + getValue(e.getBxMoney7()) + getValue(e.getBxMoney8())
                + getValue(e.getQdMoney()) + getValue(e.getaCostMoney())).sum();
    }
    private ThinkProDataDTO setDetail(ThinkProDataDTO detail,List<ThinkProDataDTO> collect,Double rate){
        detail.setGzMoney1(getValue(detail.getGzMoney1()) + collect.stream().mapToDouble(e -> getValue(e.getGzMoney1()) * rate).sum());
        detail.setGzMoney2(getValue(detail.getGzMoney2()) + collect.stream().mapToDouble(e -> getValue(e.getGzMoney2()) * rate).sum());

        detail.setJxMoney1(getValue(detail.getJxMoney1()) + collect.stream().mapToDouble(e -> getValue(e.getJxMoney1()) * rate).sum());
        detail.setJxMoney2(getValue(detail.getJxMoney2()) + collect.stream().mapToDouble(e -> getValue(e.getJxMoney2()) * rate).sum());
        detail.setJxMoney3(getValue(detail.getJxMoney3()) + collect.stream().mapToDouble(e -> getValue(e.getJxMoney3()) * rate).sum());
        detail.setJxMoney4(getValue(detail.getJxMoney4()) + collect.stream().mapToDouble(e -> getValue(e.getJxMoney4()) * rate).sum());
        detail.setJxMoney5(getValue(detail.getJxMoney5()) + collect.stream().mapToDouble(e -> getValue(e.getJxMoney5()) * rate).sum());
        detail.setJxMoney6(getValue(detail.getJxMoney6()) + collect.stream().mapToDouble(e -> getValue(e.getJxMoney6()) * rate).sum());
        detail.setJxMoney7(getValue(detail.getJxMoney7()) + collect.stream().mapToDouble(e -> getValue(e.getJxMoney7()) * rate).sum());
        detail.setJxMoney8(getValue(detail.getJxMoney8()) + collect.stream().mapToDouble(e -> getValue(e.getJxMoney8()) * rate).sum());

        detail.setBxMoney1(getValue(detail.getBxMoney1()) + collect.stream().mapToDouble(e -> getValue(e.getBxMoney1()) * rate).sum());
        detail.setBxMoney2(getValue(detail.getBxMoney2()) + collect.stream().mapToDouble(e -> getValue(e.getBxMoney2()) * rate).sum());
        detail.setBxMoney3(getValue(detail.getBxMoney3()) + collect.stream().mapToDouble(e -> getValue(e.getBxMoney3()) * rate).sum());
        detail.setBxMoney4(getValue(detail.getBxMoney4()) + collect.stream().mapToDouble(e -> getValue(e.getBxMoney4()) * rate).sum());
        detail.setBxMoney5(getValue(detail.getBxMoney5()) + collect.stream().mapToDouble(e -> getValue(e.getBxMoney5()) * rate).sum());
        detail.setBxMoney6(getValue(detail.getBxMoney6()) + collect.stream().mapToDouble(e -> getValue(e.getBxMoney6()) * rate).sum());
        detail.setBxMoney7(getValue(detail.getBxMoney7()) + collect.stream().mapToDouble(e -> getValue(e.getBxMoney7()) * rate).sum());
        detail.setBxMoney8(getValue(detail.getBxMoney8()) + collect.stream().mapToDouble(e -> getValue(e.getBxMoney8()) * rate).sum());

        detail.setQdMoney(getValue(detail.getQdMoney()) + collect.stream().mapToDouble(e -> getValue(e.getQdMoney()) * rate).sum());
        detail.setaCostMoney(getValue(detail.getaCostMoney()) + collect.stream().mapToDouble(e -> getValue(e.getaCostMoney()) * rate).sum());
        return detail;
    }
    private ThinkOutDataDTO setOutDetail(ThinkOutDataDTO outDetail,List<ThinkProDataDTO> collect,Double rate){
        outDetail.setXsMoney1(getValue(outDetail.getXsMoney1()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney1()) * rate).sum());
        outDetail.setXsMoney2(getValue(outDetail.getXsMoney2()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney2()) * rate).sum());
        outDetail.setXsMoney3(getValue(outDetail.getXsMoney3()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney3()) * rate).sum());
        outDetail.setXsMoney4(getValue(outDetail.getXsMoney4()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney4()) * rate).sum());
        outDetail.setXsMoney5(getValue(outDetail.getXsMoney5()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney5()) * rate).sum());
        outDetail.setXsMoney6(getValue(outDetail.getXsMoney6()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney6()) * rate).sum());
        outDetail.setXsMoney7(getValue(outDetail.getXsMoney7()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney7()) * rate).sum());
        outDetail.setXsMoney8(getValue(outDetail.getXsMoney8()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney8()) * rate).sum());
        outDetail.setXsMoney9(getValue(outDetail.getXsMoney9()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney9()) * rate).sum());
        outDetail.setXsMoney10(getValue(outDetail.getXsMoney10()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney10()) * rate).sum());
        outDetail.setXsMoney11(getValue(outDetail.getXsMoney11()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney11()) * rate).sum());
        outDetail.setXsMoney12(getValue(outDetail.getXsMoney12()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney12()) * rate).sum());
        outDetail.setXsMoney13(getValue(outDetail.getXsMoney13()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney13()) * rate).sum());
        outDetail.setXsMoney14(getValue(outDetail.getXsMoney14()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney14()) * rate).sum());
        outDetail.setXsMoney15(getValue(outDetail.getXsMoney15()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney15()) * rate).sum());
        outDetail.setXsMoney16(getValue(outDetail.getXsMoney16()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney16()) * rate).sum());
        outDetail.setXsMoney17(getValue(outDetail.getXsMoney17()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney17()) * rate).sum());
        outDetail.setXsMoney18(getValue(outDetail.getXsMoney18()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney18()) * rate).sum());
        outDetail.setXsMoney19(getValue(outDetail.getXsMoney19()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney19()) * rate).sum());
        outDetail.setXsMoney20(getValue(outDetail.getXsMoney20()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney20()) * rate).sum());
        outDetail.setXsMoney21(getValue(outDetail.getXsMoney21()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney21()) * rate).sum());
        outDetail.setXsMoney22(getValue(outDetail.getXsMoney22()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney22()) * rate).sum());
        outDetail.setXsMoney23(getValue(outDetail.getXsMoney23()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney23()) * rate).sum());
        outDetail.setXsMoney24(getValue(outDetail.getXsMoney24()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney24()) * rate).sum());
        outDetail.setXsMoney25(getValue(outDetail.getXsMoney25()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney25()) * rate).sum());
        outDetail.setXsMoney26(getValue(outDetail.getXsMoney26()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney26()) * rate).sum());
        outDetail.setXsMoney27(getValue(outDetail.getXsMoney27()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney27()) * rate).sum());
        outDetail.setXsMoney28(getValue(outDetail.getXsMoney28()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney28()) * rate).sum());
        outDetail.setXsMoney29(getValue(outDetail.getXsMoney29()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney29()) * rate).sum());
        outDetail.setXsMoney30(getValue(outDetail.getXsMoney30()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney30()) * rate).sum());
        outDetail.setXsMoney31(getValue(outDetail.getXsMoney31()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney31()) * rate).sum());
        outDetail.setXsMoney32(getValue(outDetail.getXsMoney32()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney32()) * rate).sum());
        outDetail.setXsMoney33(getValue(outDetail.getXsMoney33()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney33()) * rate).sum());
        outDetail.setXsMoney34(getValue(outDetail.getXsMoney34()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney34()) * rate).sum());
        outDetail.setXsMoney35(getValue(outDetail.getXsMoney35()) + collect.stream().mapToDouble(e -> getValue(e.getXsMoney35()) * rate).sum());
        return outDetail;
    }
    private Double rate(Double a,Double b){
        if (a == null) a = 0D;
        if (b == null) b = 0D;
        if (a == 0){
            return b == 0 ? 0 : -b;
        }
        if (b == 0){
            return a == 0 ? 0 : a;
        }
        return new BigDecimal((float)(a - b) / b).setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
