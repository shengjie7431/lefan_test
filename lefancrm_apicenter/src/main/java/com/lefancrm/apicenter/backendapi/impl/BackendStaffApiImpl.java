package com.lefancrm.apicenter.backendapi.impl;

import cn.jpush.api.utils.StringUtils;
import com.lefancrm.apicenter.backendapi.BackendStaffApi;
import com.lefancrm.apicenter.backendapi.BackendStaffWorkingDaysInfoApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.staff.StaffPersonnelCostAnalysisDTO;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.DecimalUtil;
import com.lefancrm.apicenter.util.GetWorkDay;
import com.lefancrm.apicenter.util.wechatPay.util.MD5Util;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.annotation.XmlElement;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.LongBuffer;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by wangwei on 2020-03-19.
 * 人事管理 - 基础数据
 */
@Service
@ApiService(descript = "人事管理 - 基础数据")
public class BackendStaffApiImpl extends BaseServiceImpl implements BackendStaffApi {

    @Autowired
    private StaffWorkingDaysInfoMapper staffWorkingDaysInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private StaffBusinessUnitMapper staffBusinessUnitMapper;
    @Autowired
    private StaffCompanyMapper staffCompanyMapper;
    @Autowired
    private StaffOrganMapper staffOrganMapper;
    @Autowired
    private StaffOrganDepartmentMapper staffOrganDepartmentMapper;
    @Autowired
    private StaffDepartmentMapper staffDepartmentMapper;
    @Autowired
    private StaffDepartmentJobPostMapper staffDepartmentJobPostMapper;
    @Autowired
    private StaffJobPostMapper staffJobPostMapper;
    @Autowired
    private StaffPersonnelInfoMapper staffPersonnelInfoMapper;
    @Autowired
    private StaffPaySlipMapper staffPaySlipMapper;
    @Autowired
    private StaffPerformanceMapper staffPerformanceMapper;
    @Autowired
    private StaffBusinessUnitCompanyMapper staffBusinessUnitCompanyMapper;
    @Autowired
    private StaffCompanyOrganMapper staffCompanyOrganMapper;
    @Autowired
    private StaffPayPersonnelSlipMapper staffPayPersonnelSlipMapper;
    @Autowired
    private StaffPerformancePersonnelMapper staffPerformancePersonnelMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private BackendStaffPerformanceApiImpl backendStaffPerformanceApiImpl;
    @Autowired
    private BackendStaffPaySlipApiImpl backendStaffPaySlipApiImpl;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private CommonEnumMapper commonEnumMapper;

    @Autowired
    private StaffPostRankMapper staffPostRankMapper;

    @Autowired
    private StaffPostAppellationMapper staffPostAppellationMapper;
    @Autowired
    private StaffOrganProductMapper staffOrganProductMapper;


    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

    @Value("${survey.role.one}")
    private Integer one;
    @Value("${survey.role.two}")
    private Integer two;
    @Value("${survey.role.three}")
    private Integer three;
    @Autowired
    private StaffPaySlipManagerMapper staffPaySlipManagerMapper;
    @Autowired
    private StaffPerformanceManagerMapper staffPerformanceManagerMapper;
    @Autowired
    private StaffTeamMapper staffTeamMapper;
    @Autowired
    private StaffTeamJobPostMapper staffTeamJobPostMapper;
    @Autowired
    private StaffDepartmentTeamMapper staffDepartmentTeamMapper;
    @Autowired
    private StaffPersonnelInfoLogMapper staffPersonnelInfoLogMapper;
    @Autowired
    private StaffPersonnelCostAnalysisMapper staffPersonnelCostAnalysisMapper;
    @Autowired
    private StaffBudgetCompanyMapper staffBudgetCompanyMapper;
    @Autowired
    private StaffBudgetCompanyOrganMapper staffBudgetCompanyOrganMapper;

    /**
     * 人事管理list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "人事管理list", value = "backend-staff-list", apiParams = { })
    @Override
    public ApiResponse list(ApiRequest apiReq) {
       //不分页
        if(apiReq.getString("havePage") == null){
            this.setBackendPageSize(apiReq);
        }
        String surveyCode = apiReq.getString("surveyCode");
        Long currentUserId = getCurrentUserId(apiReq);
        int count = 1;
        List list = new ArrayList();

        //每月应上班天数
        if("workingDaysInfo".equals(surveyCode)){
            count = staffWorkingDaysInfoMapper.listSize(apiReq);
            list = staffWorkingDaysInfoMapper.list(apiReq);
        }
        //员工管理
        else if("personnelInfo".equals(surveyCode)){
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean roleUser = isRoleUser(userRoles, 144L);
            if (roleUser){
                apiReq.put("names144",144);
            }

            count = staffPersonnelInfoMapper.listSize(apiReq);
            list = staffPersonnelInfoMapper.list(apiReq);
        }
        //事业部
        else if("businessUnit".equals(surveyCode)){
            if (apiReq.getString("state") == null) {
                apiReq.put("state",0);
            }
            count = staffBusinessUnitMapper.listSize(apiReq);
            list = staffBusinessUnitMapper.list(apiReq);
        }
        //公司
        else if("company".equals(surveyCode)){
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean roleUser = isRoleUser(userRoles, 144L);
            if (roleUser){
                apiReq.put("names144",144);
            }



            if (apiReq.getString("state") == null) {
                apiReq.put("state",0);
            }
            count = staffCompanyMapper.listSize(apiReq);
            list = staffCompanyMapper.list(apiReq);
        }
        //公司budgetCompany
        else if("budgetCompany".equals(surveyCode)){

            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean roleUser = isRoleUser(userRoles, 144L);

            if (roleUser){
                apiReq.put("names144",144);
            }

            if (apiReq.getString("state") == null) {
                apiReq.put("state",0);
            }
            count = staffBudgetCompanyMapper.listSize(apiReq);
            list = staffBudgetCompanyMapper.list(apiReq);
        }
        //机构
        else if("organ".equals(surveyCode)){
            if (apiReq.getString("state") == null) {
                apiReq.put("state",0);
            }
            if ("-1".equals(apiReq.getString("state"))){
                apiReq.remove("state");
            }
            count = staffOrganMapper.listSize(apiReq);
            list = staffOrganMapper.list(apiReq);
           /* for (Object o : list) {
                StaffOrgan staffOrgan =  (StaffOrgan)o;
                if (staffOrgan.getOrganProduct() != null){
                  *//*  if (staffOrgan.getOrganProduct() == -100l){
                        staffOrgan.setOrganProductName("互助+保司");
                    }else {
                        CommonEnum commonEnum = commonEnumMapper.selectByPrimaryKey(staffOrgan.getOrganProduct());
                        staffOrgan.setOrganProductName(commonEnum.getEnumName());
                    }*//*
                }
            }*/
        }
        //部门
        else if("department".equals(surveyCode)){
            if (apiReq.getString("state") == null) {
                apiReq.put("state",0);
            }
            if ("-1".equals(apiReq.getString("state"))){
                apiReq.remove("state");
            }
            count = staffDepartmentMapper.listSize(apiReq);
            list = staffDepartmentMapper.list(apiReq);
        }
        //小组
        else if("team".equals(surveyCode)){
            if (apiReq.getString("state") == null) {
                apiReq.put("state",0);
            }
            if ("-1".equals(apiReq.getString("state"))){
                apiReq.remove("state");
            }
            count = staffTeamMapper.listSize(apiReq);
            list = staffTeamMapper.list(apiReq);
        }
        //岗位
        else if("jobPost".equals(surveyCode)){
            if (apiReq.getString("state") == null) {
                apiReq.put("state",0);
            }
            if ("-1".equals(apiReq.getString("state"))){
                apiReq.remove("state");
            }
            count = staffJobPostMapper.listSize(apiReq);
            list = staffJobPostMapper.list(apiReq);
        }
        //工资条 与 绩效
        else if("paySlip".equals(surveyCode) || "performance".equals(surveyCode)){
            //根据角色加查询条件
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            apiReq.put("userId",userInfo.getUserId());
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean hrRole = false,superiorManager = false,ceoRole = false,orgManagerRole=false, hrManageRole = false,financeRole = false, surveyUserRole =false;
            hrRole = isRoleUser(userRoles,100L);//人事专员
            orgManagerRole = isRoleUser(userRoles,108L);//机构经理
            superiorManager = isRoleUser(userRoles,109L);//分管总
            hrManageRole = isRoleUser(userRoles,107L);//人事主管
            ceoRole = isRoleUser(userRoles,103L);//总经理
            financeRole = isRoleUser(userRoles,101L);//财务
            surveyUserRole = isRoleUser(userRoles,50L);//

            Boolean roleUser = isRoleUser(userRoles, 144L);
            if (roleUser){
                financeRole = true;
            }

            apiReq.put("mySuperior",null); //用于查询是否机构经理、分管总查询
            if (hrRole){
                apiReq.put("roleOne",1);
            }
            if (orgManagerRole){//机构经理
                apiReq.put("roleTwo",1);
                apiReq.put("mySuperior",1);
            }
            if (superiorManager){//分管总
                apiReq.put("roleThree",1);
                apiReq.put("mySuperior",1);
            }
            if (ceoRole){
                apiReq.put("roleFour",1);
            }
            if (hrManageRole){
                apiReq.put("roleSix",1);
                //如“人事主管”和“机构经理、分管总”同时存在时，人事主管需看到全部数据
                apiReq.put("roleTwo",null);
                apiReq.put("roleThree",null);
                apiReq.put("mySuperior",null);
            }
            if (financeRole){
                apiReq.put("roleFive",1);
            }

            if("performance".equals(surveyCode)){
                if (surveyUserRole){
                    apiReq.put("roleSeven",1);
                    //仅仅是调查员
                    if(!(hrRole || ceoRole || hrManageRole || orgManagerRole || superiorManager)){
                        StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(userInfo.getUserId());
                        if(staffPersonnelInfo !=null){
                            StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(staffPersonnelInfo.getOrganId());
                            if(staffOrgan != null){
                                apiReq.put("survey",1);
                                apiReq.put("organManagerUserId",staffOrgan.getOrganManagerUserId());
                                apiReq.put("superiorManagerUserId",staffOrgan.getSuperiorManagerUserId());
                            }
                        }

                    }
                }
            }
            if (!hrRole && !orgManagerRole && !superiorManager && !ceoRole && !hrManageRole && !financeRole && !surveyUserRole){
                return new ApiResponse(ApiMsgEnum.NO_AUTHORITY);
            }
            ////机构角色查看工资条、查看绩效
            String type = apiReq.getString("type");
            if ("paySlip".equals(surveyCode)){
                if("view".equals(type)){
                    apiReq.put("slipState",5);
                }
                count = staffPaySlipMapper.listSize(apiReq);
                List<StaffPaySlip> lists = staffPaySlipMapper.list(apiReq);
                for (StaffPaySlip staffPaySlip: lists) {
                    if(staffPaySlip.getOrganManagerInfo() ==null || staffPaySlip.getSuperiorManagerInfo()==null){
                        list.add(staffPaySlip);
                        continue;
                    }
                    //机构经理数据
                    String [] strs = staffPaySlip.getOrganManagerInfo().split(",");
                    List<StaffPaySlip.OrganManagerList> organManagerList = new ArrayList<>();
                    for (String str : strs) {
                        String [] item = str.split("_");
                        if(StringUtils.isEmpty(item[0])){
                            continue;
                        }
                        StaffPaySlip.OrganManagerList oList = staffPaySlip.new OrganManagerList();
                        oList.setUserName(item[0]);
                        oList.setState(item[1]);
                        organManagerList.add(oList);
                    }
                    staffPaySlip.setOrganManagerList(organManagerList);

                    //分管总数据
                    strs = staffPaySlip.getSuperiorManagerInfo().split(",");
                    List<StaffPaySlip.SuperiorManagerList> superiorManagerList = new ArrayList<>();
                    for (String str : strs) {
                        String [] item = str.split("_");
                        if(StringUtils.isEmpty(item[0])){
                            continue;
                        }
                        StaffPaySlip.SuperiorManagerList sList = staffPaySlip.new SuperiorManagerList();
                        sList.setUserName(item[0]);
                        sList.setState(item[1]);
                        superiorManagerList.add(sList);
                    }
                    staffPaySlip.setSuperiorManagerList(superiorManagerList);

                    list.add(staffPaySlip);
                }
            }else if ("performance".equals(surveyCode)){
                if("view".equals(type)){
                    apiReq.put("performanceState",5);
                }
                count = staffPerformanceMapper.listSize(apiReq);
                List<StaffPerformance> lists = staffPerformanceMapper.list(apiReq);
                for (StaffPerformance staffPerformance: lists) {
                    if(staffPerformance.getOrganManagerInfo() ==null || staffPerformance.getSuperiorManagerInfo()==null){
                        list.add(staffPerformance);
                        continue;
                    }
                    //机构经理数据
                    String [] strs = staffPerformance.getOrganManagerInfo().split(",");
                    List<StaffPerformance.OrganManagerList> organManagerList = new ArrayList<>();
                    for (String str : strs) {
                        String [] item = str.split("_");
                        if(StringUtils.isEmpty(item[0])){
                            continue;
                        }
                        StaffPerformance.OrganManagerList oList = staffPerformance.new OrganManagerList();
                        oList.setUserName(item[0]);
                        oList.setState(item[1]);
                        organManagerList.add(oList);
                    }
                    staffPerformance.setOrganManagerList(organManagerList);

                    //分管总二审数据
                    strs = staffPerformance.getSuperiorManagerInfo().split(",");
                    List<StaffPerformance.SuperiorManagerList> superiorManagerList = new ArrayList<>();
                    for (String str : strs) {
                        String [] item = str.split("_");
                        if(StringUtils.isEmpty(item[0])){
                            continue;
                        }
                        StaffPerformance.SuperiorManagerList sList = staffPerformance.new SuperiorManagerList();
                        sList.setUserName(item[0]);
                        sList.setState(item[1]);
                        superiorManagerList.add(sList);
                    }
                    staffPerformance.setSuperiorManagerList(superiorManagerList);

                    //分管总一审数据
                    strs = staffPerformance.getFirstSuperiorManagerInfo().split(",");
                    List<StaffPerformance.SuperiorManagerList> firstSuperiorManagerList = new ArrayList<>();
                    for (String str : strs) {
                        String [] item = str.split("_");
                        if(StringUtils.isEmpty(item[0])){
                            continue;
                        }
                        StaffPerformance.SuperiorManagerList sList = staffPerformance.new SuperiorManagerList();
                        sList.setUserName(item[0]);
                        sList.setState(item[1]);
                        firstSuperiorManagerList.add(sList);
                    }
                    staffPerformance.setFirstSuperiorManagerList(firstSuperiorManagerList);
                    list.add(staffPerformance);
                }

            }
        }
        //事业部对应公司
        else if("businessUnitCompany".equals(surveyCode)){
            count = staffBusinessUnitCompanyMapper.listSize(apiReq);
            list = staffBusinessUnitCompanyMapper.list(apiReq);
        }
        //公司对应机构
        else if("companyOrgan".equals(surveyCode)){
            count = staffBudgetCompanyOrganMapper.listSize(apiReq);
            list = staffBudgetCompanyOrganMapper.list(apiReq);
        }else if ("companyOrganY".equals(surveyCode)){
            list = staffBudgetCompanyOrganMapper.selectOrgans(apiReq);
            count = list.size();
        }
        //预算公司对应机构
        else if("budgetCompanyOrgan".equals(surveyCode)){
            count = staffBudgetCompanyOrganMapper.listSize(apiReq);
            list = staffBudgetCompanyOrganMapper.list(apiReq);
        }
        //机构对应科室
        else if("organDepartment".equals(surveyCode)){
            count = staffOrganDepartmentMapper.listSize(apiReq);
            list = staffOrganDepartmentMapper.list(apiReq);
        }
        //科室对应小组
        else if("departmentTeam".equals(surveyCode)){
            count = staffDepartmentTeamMapper.listSize(apiReq);
            list = staffDepartmentTeamMapper.list(apiReq);
        }
        //小组对应岗位
        else if("teamJobPost".equals(surveyCode)){
            count = staffTeamJobPostMapper.listSize(apiReq);
            list = staffTeamJobPostMapper.list(apiReq);
        }
        //工资条明细
        else if("payPersonnelSlip".equals(surveyCode)){
            count = staffPayPersonnelSlipMapper.listSize(apiReq);
            list = staffPayPersonnelSlipMapper.list(apiReq);
        }
        //绩效明细
        else if("performancePersonnel".equals(surveyCode)){
            count = staffPerformancePersonnelMapper.listSize(apiReq);
            list = staffPerformancePersonnelMapper.list(apiReq);
        }
        //员工数据变更历史
        else if("personnelInfoLog".equals(surveyCode)){
            list = staffPersonnelInfoLogMapper.list(apiReq);
            count = staffPersonnelInfoLogMapper.listSize(apiReq);
        }
        //人事成本分析
        else if("personnelCostAnalysis".equals(surveyCode)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            String startTime = apiReq.getString("startTime");
            String endTime = apiReq.getString("endTime");

            List<StaffPersonnelCostAnalysisDTO> dtoList = staffPersonnelCostAnalysisMapper.list(apiReq);

            //员工数据拼接
            List<StaffPersonnelInfo> data = staffPersonnelInfoMapper.selectStr();
            for (StaffPersonnelInfo item : data) {
                if(item.getEntryTime() !=null){
                    item.setEntryTimeStr(new SimpleDateFormat("yyyy-MM-dd").format(item.getEntryTime()));
                }else{
                    item.setEntryTimeStr("2020-01-01");//默认一个日期
                }
                if(item.getQuitTime()!=null){
                    item.setQuitTimeStr(new SimpleDateFormat("yyyy-MM").format(item.getQuitTime()));
                    item.setQuitTimeStrTwo(new SimpleDateFormat("yyyy-MM-dd").format(item.getQuitTime()));
                }else{
                    item.setQuitTimeStr(null);
                    item.setQuitTimeStrTwo(null);
                }
            }


            Double wagesPaySub = 0D;
            Double fixedPerfPay = 0D;
            Double assesPerfPay = 0D;
            Double travelAllowancePay = 0D;
            Double integralPay = 0D;
            Double welfarePay = 0D;
            Double quitCost = 0D;
            Double companyMoneySub = 0D;
            Double personnelCostSub = 0D;


            for (StaffPersonnelCostAnalysisDTO line : dtoList) {
                //期初人数   (每个时间段1号之前（包含1号），的在职人数)条件：1、公司机构科室小组相同  2、员工入职时间在 当前时间段之前，3、员工的离职时间在此时间之前
                Long monthBeginPersonnelNum = data.stream().filter(e -> (e.getContidion().equals(line.getContidion()) && e.getEntryTimeStr().concat("-01").compareTo(line.getWorkTime().concat("-01")) <= 0 && (e.getQuitTime() ==null || (e.getQuitTime() !=null && e.getQuitTimeStrTwo().concat("-01").compareTo(line.getWorkTime().concat("-01")) > 0)))).count();
                line.setMonthBeginPersonnelNum(monthBeginPersonnelNum.intValue());


                //期末人数(每个时间段30号之前（包含30号），的在职人数)条件：1、公司机构科室小组相同  2、员工入职时间在 当前时间段之前
                //当月最后一天
                int year = Integer.valueOf(line.getWorkTime().substring(0, 4));
                int month = Integer.valueOf(line.getWorkTime().substring(5, 7));
                Date monthEndTime = DateUtils.getLastDayOfYearMonth(year, month);
                String workTime =  (new SimpleDateFormat("yyyy-MM-dd").format(monthEndTime));

                Long monthEndPersonnelNum = data.stream().filter(e -> (e.getContidion().equals(line.getContidion()) && e.getEntryTimeStr().compareTo(workTime) <= 0 && e.getQuitTime() ==null)).count();
                line.setMonthEndPersonnelNum(monthEndPersonnelNum.intValue());

                //离职人数
                Long quitPersonnelNum = data.stream().filter(e -> (e.getContidion().equals(line.getContidion()) && (e.getQuitTime() !=null && e.getQuitTimeStr().compareTo(line.getWorkTime()) == 0))).count();
                line.setQuitPersonnelNum(quitPersonnelNum.intValue());
                //离职率
                Double quitRate = new BigDecimal((float)line.getQuitPersonnelNum() / line.getPersonnelNum()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();;
                line.setQuitRate(quitRate*100);

                //公积金公司部分，正负相反显示
                line.setCompanyMoneySub(-line.getCompanyMoneySub());


                //总计
                wagesPaySub = wagesPaySub + (line.getWagesPaySub()==null ? 0D: line.getWagesPaySub());
                fixedPerfPay = fixedPerfPay + (line.getFixedPerfPay()==null ? 0D: line.getFixedPerfPay());
                assesPerfPay = assesPerfPay + (line.getAssesPerfPay()==null ? 0D: line.getAssesPerfPay());
                travelAllowancePay = travelAllowancePay + (line.getTravelAllowancePay()==null ? 0D: line.getTravelAllowancePay());
                integralPay = integralPay + (line.getIntegralPay()==null ? 0D: line.getIntegralPay());
                welfarePay = welfarePay + (line.getWelfarePay()==null ? 0D: line.getWelfarePay());
                quitCost = quitCost + (line.getQuitCost()==null ? 0D: line.getQuitCost());
                companyMoneySub = companyMoneySub + (line.getCompanyMoneySub()==null ? 0D: line.getCompanyMoneySub());
                personnelCostSub = personnelCostSub + (line.getPersonnelCostSub()==null ? 0D: line.getPersonnelCostSub());

            }

            list.addAll(dtoList);

            //总计的人员
            int year = Integer.valueOf(endTime.substring(0, 4));
            int month = Integer.valueOf(endTime.substring(5, 7));
            Date monthEndTime = DateUtils.getLastDayOfYearMonth(year, month);
            String workTime =  (new SimpleDateFormat("yyyy-MM-dd").format(monthEndTime));
            apiReq.put("monthEndTime",workTime);
            StaffPersonnelCostAnalysisDTO personnelInfo = staffPersonnelCostAnalysisMapper.selectPersonnelInfo(apiReq);
            Double quitRate = 0D;
            if(personnelInfo.getQuitPersonnelNum()!=null && personnelInfo.getPersonnelNum()!=null){
                quitRate = new BigDecimal((float)personnelInfo.getQuitPersonnelNum() / personnelInfo.getPersonnelNum()).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            personnelInfo.setQuitRate(quitRate*100);
            personnelInfo.setWagesPaySub(DecimalUtil.twoDecimalTOFourFromFive(wagesPaySub));
            personnelInfo.setFixedPerfPay(DecimalUtil.twoDecimalTOFourFromFive(fixedPerfPay));
            personnelInfo.setAssesPerfPay(DecimalUtil.twoDecimalTOFourFromFive(assesPerfPay));
            personnelInfo.setTravelAllowancePay(DecimalUtil.twoDecimalTOFourFromFive(travelAllowancePay));
            personnelInfo.setIntegralPay(DecimalUtil.twoDecimalTOFourFromFive(integralPay));
            personnelInfo.setWelfarePay(DecimalUtil.twoDecimalTOFourFromFive(welfarePay));
            personnelInfo.setQuitCost(DecimalUtil.twoDecimalTOFourFromFive(quitCost));
            personnelInfo.setCompanyMoneySub(-DecimalUtil.twoDecimalTOFourFromFive(companyMoneySub));
            personnelInfo.setPersonnelCostSub(DecimalUtil.twoDecimalTOFourFromFive(personnelCostSub));

            Map map = new HashMap<>();
            map.put("list",list);
            map.put("personnelInfo",personnelInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS, count, map);
        }
        //员工数据变更历史
        else if("personnelCostAnalysisUser".equals(surveyCode)){
            List<StaffPersonnelCostAnalysisDTO> dtoList = staffPersonnelCostAnalysisMapper.userList(apiReq);
            for (StaffPersonnelCostAnalysisDTO line : dtoList) {
                //公积金公司部分，正负相反显示
                Double companyMoneySub = 0D;
                if(line.getCompanyMoneySub() !=null){
                    companyMoneySub = line.getCompanyMoneySub();
                }
                line.setCompanyMoneySub(-companyMoneySub);
            }
            list.addAll(dtoList);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, list);
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
     * 人事管理info
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "人事管理info", value = "backend-staff-info", apiParams = { })
    @Override
    public ApiResponse info(ApiRequest apiReq) {
        String surveyCode = apiReq.getString("surveyCode");
        Long currentUserId = getCurrentUserId(apiReq);
        //每月应上班天数
        if("workingDaysInfo".equals(surveyCode)){
            StaffWorkingDaysInfo staffWorkingDaysInfo = staffWorkingDaysInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffWorkingDaysInfo);
        }
        //员工管理
        else if("personnelInfo".equals(surveyCode)){
            StaffPersonnelInfo staffPersonnelInfo;
            if(apiReq.get("id")==null){
                staffPersonnelInfo = null;
            }else if(apiReq.getString("id").equals("undefined")){
                staffPersonnelInfo = null;
            }
            else{
                staffPersonnelInfo = staffPersonnelInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPersonnelInfo);
        }
        //事业部
        else if("businessUnit".equals(surveyCode)){
            StaffBusinessUnit staffBusinessUnit = staffBusinessUnitMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffBusinessUnit);
        }
        //公司
        else if("company".equals(surveyCode)){
            StaffCompany staffCompany = staffCompanyMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffCompany);
        }
        //预算归属公司
        else if("budgetCompany".equals(surveyCode)){
            StaffBudgetCompany staffBudgetCompany = staffBudgetCompanyMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffBudgetCompany);
        }
        //机构
        else if("organ".equals(surveyCode)){
            StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if (staffOrgan != null){
                //查询关联的开票产品
                Map map=new HashMap();
                map.put("organId",staffOrgan.getId());
                List<StaffOrganProduct>  staffOrganProductList = this.staffOrganProductMapper.listByParam(map);
                staffOrgan.setStaffOrganProductList(staffOrganProductList);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffOrgan);
        }
        //部门
        else if("department".equals(surveyCode)){
            StaffDepartment staffDepartment = staffDepartmentMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffDepartment);
        }
        //小组
        else if("team".equals(surveyCode)){
            StaffTeam staffTeam = staffTeamMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffTeam);
        }
        //岗位
        else if("jobPost".equals(surveyCode)){
            StaffJobPost staffJobPost = staffJobPostMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffJobPost);
        }
        //工资条
        else if("paySlip".equals(surveyCode)){
            StaffPaySlip staffPaySlip = staffPaySlipMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPaySlip);
        }
        //绩效
        else if("performance".equals(surveyCode)){
            StaffPerformance staffPerformance = staffPerformanceMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPerformance);
        }
        //绩效
        else if("performancePersonnel".equals(surveyCode)){
            StaffPerformancePersonnel staffPerformancePersonnel = staffPerformancePersonnelMapper.selectByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPerformancePersonnel);
        }
        return null;
    }

    /**
     * 人事管理update
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "人事管理update", value = "backend-staff-update", apiParams = { })
    @Override
    public ApiResponse update(ApiRequest apiReq) {
        String surveyCode = apiReq.getString("surveyCode");
        Long userId = apiReq.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        String userName = null;
        if(userInfo != null){
            userName = userInfo.getUserName();
            userId = userInfo.getUserId();
        }
        try {
            //每月应上班天数
            if("workingDaysInfo".equals(surveyCode)) {
                //是否重复数据
                String workTime = apiReq.getString("workTime");
                Long id = apiReq.getLong("id");
                StaffWorkingDaysInfo newInfo = staffWorkingDaysInfoMapper.selectByWorkTime(workTime);
                if(newInfo != null){
                    if(!(id !=null && id.equals(newInfo.getId()))){
                        return new ApiResponse(ApiMsgEnum.StaffInfoRepeat);
                    }
                }
                StaffWorkingDaysInfo staffWorkingDaysInfo = staffWorkingDaysInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if (staffWorkingDaysInfo == null) {
                    //保存
                    staffWorkingDaysInfo = new StaffWorkingDaysInfo();
                    staffWorkingDaysInfo.setWorkTime(workTime);
                    staffWorkingDaysInfo.setWrokingDays(apiReq.getDouble("wrokingDays"));
                    staffWorkingDaysInfo.setCreateBy(userName);// 发起人
                    staffWorkingDaysInfo.setCreateTime(new Date());//创建时间
                    staffWorkingDaysInfo.setDeleteFlag(0);
                    staffWorkingDaysInfoMapper.insert(staffWorkingDaysInfo);
                } else {
                    //修改
                    //如该月份的工资条数据。已经通过人事审核，就不予修改
                    StaffPaySlip staffPaySlip = staffPaySlipMapper.selectByWorkTime(workTime);
                    if(staffPaySlip !=null && staffPaySlip.getSlipState() !=0){
                        return new ApiResponse(ApiMsgEnum.StaffNoUpdateWorkingDays);
                    }

                    staffWorkingDaysInfo.setWorkTime(workTime);
                    staffWorkingDaysInfo.setWrokingDays(apiReq.getDouble("wrokingDays"));
                    staffWorkingDaysInfo.setUpdateBy(userName);// 更新人id
                    staffWorkingDaysInfo.setUpdateTime(new Date());//更新时间
                    staffWorkingDaysInfoMapper.updateByPrimaryKey(staffWorkingDaysInfo);
                }
            }
            //员工管理
            else if("personnelInfo".equals(surveyCode)) {
                Long id = apiReq.getLong("id");
                Map<String, Object> map = new HashMap<>();
                map = new HashMap<>();
                map.put("userTel",apiReq.getString("userTel"));
                StaffPersonnelInfo newInfo = staffPersonnelInfoMapper.selectByInfo(map);

                if(newInfo != null){
                    if(!(id !=null && id.equals(newInfo.getId()))){
                        return new ApiResponse(ApiMsgEnum.StaffTelInfoRepeat);
                    }
                }

                map = new HashMap<>();
                map.put("jobNo",apiReq.getString("jobNo"));
                newInfo = staffPersonnelInfoMapper.selectByInfo(map);
                if(newInfo != null){
                    if(!(id !=null && id.equals(newInfo.getId()))){
                        return new ApiResponse(ApiMsgEnum.StaffNoInfoRepeat);
                    }
                }

                map = new HashMap<>();
                map.put("idCard",apiReq.getString("idCard"));
                newInfo = staffPersonnelInfoMapper.selectByInfo(map);
                if(newInfo != null){
                    if(!(id !=null && id.equals(newInfo.getId()))){
                        return new ApiResponse(ApiMsgEnum.StaffIdCardInfoRepeat);
                    }
                }

                Pattern pattern = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$");
                Boolean isNumber = pattern.matcher(apiReq.getString("userTel")).matches();
                if(!isNumber){
                    return new ApiResponse(ApiMsgEnum.StaffTelInfoError);
                }

                StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
                StaffPersonnelInfo oldInfo = staffPersonnelInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));//用于比较修改的字段
                StaffCompany staffCompany = staffCompanyMapper.selectByPrimaryKey(apiReq.getLong("companyId"));//公司
                StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(apiReq.getLong("organId"));//公司
                StaffDepartment staffDepartment = staffDepartmentMapper.selectByPrimaryKey(apiReq.getLong("departmentId"));//部门
                StaffJobPost staffJobPost = staffJobPostMapper.selectByPrimaryKey(apiReq.getLong("jobPostId"));//岗位
                StaffTeam staffTeam = staffTeamMapper.selectByPrimaryKey(apiReq.getLong("teamId"));//岗位
                StaffCompany staffCompany2 = staffCompanyMapper.selectByPrimaryKey(apiReq.getLong("socialSecurityCompanyId"));//社保缴纳公司
                StaffPostRank staffPostRank=staffPostRankMapper.selectByPrimaryKey(apiReq.getLong("postRankId"));
                StaffPostAppellation staffPostAppellation=staffPostAppellationMapper.selectByPrimaryKey(apiReq.getLong("postAppellationId"));
                StaffBudgetCompanyOrgan staffBudgetCompanyOrgan = null;
                if (staffOrgan != null){
                     staffBudgetCompanyOrgan = staffBudgetCompanyOrganMapper.selectByOrganId(staffOrgan.getId());
                }

                String entryTime = apiReq.getString("entryTime");//入职时间
                if (StringUtils.isNotEmpty(entryTime)) {
                    Date entryTimeD = DateUtils.parseDate(entryTime, "yyyy-MM-dd");
                    apiReq.put("entryTime", entryTimeD);
                }else{
                    return new ApiResponse(ApiMsgEnum.StaffEntryTimeError);
                }

                String quitTime = apiReq.getString("quitTime");//离职时间
                if (StringUtils.isNotEmpty(quitTime)) {
                    Date quitTimeD = DateUtils.parseDate(quitTime, "yyyy-MM-dd");
                    apiReq.put("quitTime", quitTimeD);
                }

                String regularTime = apiReq.getString("regularTime");//转正时间
                if (StringUtils.isNotEmpty(regularTime)) {
                    Date regularTimeD = DateUtils.parseDate(regularTime, "yyyy-MM-dd");
                    apiReq.put("regularTime", regularTimeD);
                }

                String graduationTime = apiReq.getString("graduationTime");//毕业时间
                if (StringUtils.isNotEmpty(graduationTime)) {
                    Date graduationTimeD = DateUtils.parseDate(graduationTime, "yyyy-MM-dd");
                    apiReq.put("graduationTime", graduationTimeD);
                }

                String firstContractBeginTime = apiReq.getString("firstContractBeginTime");//首次合同起始日
                if (StringUtils.isNotEmpty(firstContractBeginTime)) {
                    Date firstContractBeginTimeD = DateUtils.parseDate(firstContractBeginTime, "yyyy-MM-dd");
                    apiReq.put("firstContractBeginTime", firstContractBeginTimeD);
                }

                String firstContractEndTime = apiReq.getString("firstContractEndTime");//首次合同到期日
                if (StringUtils.isNotEmpty(firstContractEndTime)) {
                    Date firstContractEndTimeD = DateUtils.parseDate(firstContractEndTime, "yyyy-MM-dd");
                    apiReq.put("firstContractEndTime", firstContractEndTimeD);
                }

                String nowContractBeginTime = apiReq.getString("nowContractBeginTime");//现合同起始日
                if (StringUtils.isNotEmpty(nowContractBeginTime)) {
                    Date nowContractBeginTimeD = DateUtils.parseDate(nowContractBeginTime, "yyyy-MM-dd");
                    apiReq.put("nowContractBeginTime", nowContractBeginTimeD);
                }

                String nowContractEndTime = apiReq.getString("nowContractEndTime");//现合同到期日
                if (StringUtils.isNotEmpty(nowContractEndTime)) {
                    Date nowContractEndTimeD = DateUtils.parseDate(nowContractEndTime, "yyyy-MM-dd");
                    apiReq.put("nowContractEndTime", nowContractEndTimeD);
                }

                String familyBirthday = apiReq.getString("familyBirthday");//生日（家人）
                if (StringUtils.isNotEmpty(familyBirthday)) {
                    Date familyBirthdayD = DateUtils.parseDate(familyBirthday, "yyyy-MM-dd");
                    apiReq.put("familyBirthday", familyBirthdayD);
                }

                //控制空格
                apiReq.put("realName", apiReq.getString("realName").replace(" ",""));
                apiReq.put("jobNo", apiReq.getString("jobNo").replace(" ",""));
                if(apiReq.getString("payAddress") != null){
                    apiReq.put("payAddress", apiReq.getString("payAddress").replace(" ",""));
                }

                if(staffPersonnelInfo == null){
                    //保存
                    staffPersonnelInfo = ConvertToBeanUtil.toBean(apiReq, StaffPersonnelInfo.class);
                    if(staffDepartment != null ){
                        staffPersonnelInfo.setDepartmentId(staffDepartment.getId());
                        staffPersonnelInfo.setDepartment(staffDepartment.getName());
                    }else{
                        staffPersonnelInfo.setDepartment(null);
                        staffPersonnelInfo.setDepartmentId(null);
                    }
                    if(staffJobPost != null ){
                        staffPersonnelInfo.setJobPostId(staffJobPost.getId());
                        staffPersonnelInfo.setJobPost(staffJobPost.getName());
                    }else{
                        staffPersonnelInfo.setJobPostId(null);
                        staffPersonnelInfo.setJobPost(null);
                    }
                    if(staffTeam != null ){
                        staffPersonnelInfo.setTeamId(staffTeam.getId());
                        staffPersonnelInfo.setTeam(staffTeam.getName());
                    }else{
                        staffPersonnelInfo.setTeamId(null);
                        staffPersonnelInfo.setTeam(null);
                    }
                    if(staffPostRank!=null){
                        staffPersonnelInfo.setPostRankName(staffPostRank.getRankName());
                    }else{
                        staffPersonnelInfo.setPostRankName(null);
                    }
                    if(staffPostAppellation!=null){
                        staffPersonnelInfo.setPostAppellationName(staffPostAppellation.getAppellationName());
                    }else{
                        staffPersonnelInfo.setPostAppellationName(null);
                    }
                    if(staffBudgetCompanyOrgan!=null){
                        staffPersonnelInfo.setBudgetCompanyName(staffBudgetCompanyOrgan.getCompanyName());
                    }else{
                        staffPersonnelInfo.setBudgetCompanyName(null);
                    }



                    staffPersonnelInfo.setSocialSecurityCompany(staffCompany2.getName());

                    staffPersonnelInfo.setCreateBy(userName);// 发起人
                    staffPersonnelInfo.setCreateTime(new Date());//创建时间
                    staffPersonnelInfo.setDeleteFlag(0);
                    Double pensionBase=apiReq.getDouble("pensionBase");
                    staffPersonnelInfo.setPensionBase(pensionBase);
                    Double pensionCompanyRate=apiReq.getDouble("pensionCompanyRate");
                    staffPersonnelInfo.setPensionCompanyRate(pensionCompanyRate);
                    Double pensionPersonalRate=apiReq.getDouble("pensionPersonalRate");
                    staffPersonnelInfo.setPensionPersonalRate(pensionPersonalRate);
                    Double medicalBase=apiReq.getDouble("medicalBase");
                    staffPersonnelInfo.setMedicalBase(medicalBase);
                    Double medicalCompanyRate=apiReq.getDouble("medicalCompanyRate");
                    staffPersonnelInfo.setMedicalCompanyRate(medicalCompanyRate);
                    Double medicalPersonalRate=apiReq.getDouble("medicalPersonalRate");
                    staffPersonnelInfo.setMedicalPersonalRate(medicalPersonalRate);
                    Double upmBase=apiReq.getDouble("upmBase");
                    staffPersonnelInfo.setUpmBase(upmBase);
                    Double upmCompanyRate=apiReq.getDouble("upmCompanyRate");
                    staffPersonnelInfo.setUpmCompanyRate(upmCompanyRate);
                    Double upmPersonalRate=apiReq.getDouble("upmPersonalRate");
                    staffPersonnelInfo.setUpmPersonalRate(upmPersonalRate);
                    Double isaBase=apiReq.getDouble("isaBase");
                    staffPersonnelInfo.setIsaBase(isaBase);
                    Double isaCompanyRate=apiReq.getDouble("isaCompanyRate");
                    staffPersonnelInfo.setIsaCompanyRate(isaCompanyRate);
                    Double birthBase=apiReq.getDouble("birthBase");
                    staffPersonnelInfo.setBirthBase(birthBase);
                    Double birthCompanyRate=apiReq.getDouble("birthCompanyRate");
                    staffPersonnelInfo.setBirthCompanyRate(birthCompanyRate);
                    Double fundPayCompanyRate=apiReq.getDouble("fundPayCompanyRate");
                    staffPersonnelInfo.setFundPayCompanyRate(fundPayCompanyRate);
                    Double fundPayPersonalRate=apiReq.getDouble("fundPayPersonalRate");
                    staffPersonnelInfo.setFundPayPersonalRate(fundPayPersonalRate);
                    if (staffBudgetCompanyOrgan != null){
                        staffPersonnelInfo.setCompanyId(staffBudgetCompanyOrgan.getCompanyId());
                        staffPersonnelInfo.setCompany(staffBudgetCompanyOrgan.getCompanyName());
                        staffPersonnelInfo.setOrgan(staffBudgetCompanyOrgan.getOrganName());
                        staffPersonnelInfo.setOrganId(staffBudgetCompanyOrgan.getOrganId());
                    }
                    staffPersonnelInfo.setHomeLbsX(apiReq.getDouble("homeLbsX"));
                    staffPersonnelInfo.setHomeLbsY(apiReq.getDouble("homeLbsY"));
                    staffPersonnelInfo.setHomeAddress(apiReq.getString("homeAddress"));
                    staffPersonnelInfo.setHomeAddressName(apiReq.getString("homeAddressName"));
                   //根据选择的机构的绩效方式，赋值给员工表
                    staffPersonnelInfo.setPerformance(staffOrgan.getPerformance());
                    staffPersonnelInfoMapper.insert(staffPersonnelInfo);

                    //人员数据
                    String userTel = apiReq.getString("userTel");
                    map = new HashMap<>();
                    map.put("userTelphone",userTel);
                    UserInfo user = userInfoMapper.selectUserInfoByPhone(map);
                    if(user == null){
                        //先添加login信息
                        UserLogin userLogin = userLoginMapper.selectByPhone(userTel);
                        if(userLogin != null){
                            return new ApiResponse(ApiMsgEnum.UsernameBeenRegistered);
                        }
                        userLogin = new UserLogin();
                        userLogin.setUserTelphone(userTel);
                        userLogin.setPassword(MD5Util.MD5Encode("123456@Qaz", null));
                        userLogin.setCreateTime(new Date());
                        int ret = userLoginMapper.insertSelective(userLogin);
                        if(ret < 1){
                            return new ApiResponse(ApiMsgEnum.FAIL);
                        }

                        UserInfo record = new UserInfo();
                        record.setUserId(userLogin.getUserId());
                        record.setUserName(staffPersonnelInfo.getRealName());
                        record.setNickName(staffPersonnelInfo.getRealName());
                        record.setUserTel(userTel);
                        record.setIsPromoter(0);//是否是推广人(1:否，0：是)
                        record.setUserState(0);//用户状态(1:禁用，0：正常)
                        record.setCreateTime(new Date());
                        record.setCreateBy(userInfo.getUserName());
                        record.setIsTester(0);//是否为测试人员：0、不是；1、是
                        record.setDeleteFlag(0);//是否删除(0:否，1：是)
                        record.setUserType(1);//用户类型：1普通用户，2机构用户，3测试用户，4其他
                        userInfoMapper.insertSelective(record);
                        //添加用户账号信息
                        UserAccount userAccount = new UserAccount();
                        userAccount.setUserId(record.getUserId());
                        userAccount.setUserRecharge(0D);
                        userAccount.setWithdrawDeposit(0D);
                        userAccountMapper.insertSelective(userAccount);

                        BusUserRole busUserRole = new BusUserRole();
                        busUserRole.setUserId(record.getUserId());
                        busUserRole.setRoleId(6L);
                        busUserRoleMapper.insertSelective(busUserRole);

                        staffPersonnelInfo.setUserId(record.getUserId());
                        staffPersonnelInfoMapper.updateByPrimaryKey(staffPersonnelInfo);
                    }else{
                        staffPersonnelInfo.setUserId(user.getUserId());
                        staffPersonnelInfoMapper.updateByPrimaryKey(staffPersonnelInfo);
                    }

                }else{
                    //修改
                    staffPersonnelInfo = ConvertToBeanUtil.toBean(apiReq,staffPersonnelInfo);

                    if(staffCompany !=null){
                        staffPersonnelInfo.setCompanyId(staffCompany.getId());
                        staffPersonnelInfo.setCompany(staffCompany.getName());
                    }else{
                        staffPersonnelInfo.setCompanyId(null);
                        staffPersonnelInfo.setCompany(null);
                    }

                    if(staffOrgan !=null){
                        staffPersonnelInfo.setOrganId(staffOrgan.getId());
                        staffPersonnelInfo.setOrgan(staffOrgan.getName());
                    }else{
                        staffPersonnelInfo.setOrganId(null);
                        staffPersonnelInfo.setOrgan(null);
                    }
                    if(staffDepartment != null ){
                        staffPersonnelInfo.setDepartmentId(staffDepartment.getId());
                        staffPersonnelInfo.setDepartment(staffDepartment.getName());
                    }else{
                        staffPersonnelInfo.setDepartment(null);
                        staffPersonnelInfo.setDepartmentId(null);
                    }
                    if(staffJobPost != null ){
                        staffPersonnelInfo.setJobPostId(staffJobPost.getId());
                        staffPersonnelInfo.setJobPost(staffJobPost.getName());
                    }else{
                        staffPersonnelInfo.setJobPostId(null);
                        staffPersonnelInfo.setJobPost(null);
                    }
                    if(staffTeam != null ){
                        staffPersonnelInfo.setTeamId(staffTeam.getId());
                        staffPersonnelInfo.setTeam(staffTeam.getName());
                    }else{
                        staffPersonnelInfo.setTeamId(null);
                        staffPersonnelInfo.setTeam(null);
                    }
                    if(staffCompany2 !=null){
                        staffPersonnelInfo.setSocialSecurityCompanyId(staffCompany2.getId());
                        staffPersonnelInfo.setSocialSecurityCompany(staffCompany2.getName());
                    }else{
                        staffPersonnelInfo.setSocialSecurityCompanyId(null);
                        staffPersonnelInfo.setSocialSecurityCompany(null);
                    }

                    Double pensionBase=apiReq.getDouble("pensionBase");
                    staffPersonnelInfo.setPensionBase(pensionBase);
                    Double pensionCompanyRate=apiReq.getDouble("pensionCompanyRate");
                    staffPersonnelInfo.setPensionCompanyRate(pensionCompanyRate);
                    Double pensionPersonalRate=apiReq.getDouble("pensionPersonalRate");
                    staffPersonnelInfo.setPensionPersonalRate(pensionPersonalRate);
                    Double medicalBase=apiReq.getDouble("medicalBase");
                    staffPersonnelInfo.setMedicalBase(medicalBase);
                    Double medicalCompanyRate=apiReq.getDouble("medicalCompanyRate");
                    staffPersonnelInfo.setMedicalCompanyRate(medicalCompanyRate);
                    Double medicalPersonalRate=apiReq.getDouble("medicalPersonalRate");
                    staffPersonnelInfo.setMedicalPersonalRate(medicalPersonalRate);
                    Double upmBase=apiReq.getDouble("upmBase");
                    staffPersonnelInfo.setUpmBase(upmBase);
                    Double upmCompanyRate=apiReq.getDouble("upmCompanyRate");
                    staffPersonnelInfo.setUpmCompanyRate(upmCompanyRate);
                    Double upmPersonalRate=apiReq.getDouble("upmPersonalRate");
                    staffPersonnelInfo.setUpmPersonalRate(upmPersonalRate);
                    Double isaBase=apiReq.getDouble("isaBase");
                    staffPersonnelInfo.setIsaBase(isaBase);
                    Double isaCompanyRate=apiReq.getDouble("isaCompanyRate");
                    staffPersonnelInfo.setIsaCompanyRate(isaCompanyRate);
                    Double birthBase=apiReq.getDouble("birthBase");
                    staffPersonnelInfo.setBirthBase(birthBase);
                    Double birthCompanyRate=apiReq.getDouble("birthCompanyRate");
                    staffPersonnelInfo.setBirthCompanyRate(birthCompanyRate);
                    Double fundPayCompanyRate=apiReq.getDouble("fundPayCompanyRate");
                    staffPersonnelInfo.setFundPayCompanyRate(fundPayCompanyRate);
                    Double fundPayPersonalRate=apiReq.getDouble("fundPayPersonalRate");
                    staffPersonnelInfo.setFundPayPersonalRate(fundPayPersonalRate);
                    staffPersonnelInfo.setUpdateBy(userName);// 更新人id
                    staffPersonnelInfo.setUpdateTime(new Date());//更新时间
                    if (staffBudgetCompanyOrgan != null){
                        staffPersonnelInfo.setCompanyId(staffBudgetCompanyOrgan.getCompanyId());
                        staffPersonnelInfo.setCompany(staffBudgetCompanyOrgan.getCompanyName());
                        staffPersonnelInfo.setOrgan(staffOrgan.getName());
                        staffPersonnelInfo.setOrganId(staffOrgan.getId());

                        staffPersonnelInfo.setBudgetCompanyId(staffBudgetCompanyOrgan.getCompanyId());
                        staffPersonnelInfo.setBudgetCompanyName(staffBudgetCompanyOrgan.getCompanyName());
                    }
                    staffPersonnelInfo.setHomeLbsX(apiReq.getDouble("homeLbsX"));
                    staffPersonnelInfo.setHomeLbsY(apiReq.getDouble("homeLbsY"));
                    staffPersonnelInfo.setHomeAddress(apiReq.getString("homeAddress"));
                    staffPersonnelInfo.setHomeAddressName(apiReq.getString("homeAddressName"));
                    staffPersonnelInfo.setPerformance(staffOrgan.getPerformance());
                    staffPersonnelInfoMapper.updateByPrimaryKey(staffPersonnelInfo);

                    try {
                        updateLog(oldInfo, staffPersonnelInfo, "编辑", userInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            //事业部
            else if("businessUnit".equals(surveyCode)) {
                //是否重复数据
                String newName = apiReq.getString("name");
                Long id = apiReq.getLong("id");
                StaffBusinessUnit newInfo = staffBusinessUnitMapper.selectByOne(newName);
                if(newInfo != null){
                    if(!(id !=null && id.equals(newInfo.getId()))){
                        return new ApiResponse(ApiMsgEnum.StaffInfoRepeat);
                    }
                }
                StaffBusinessUnit staffBusinessUnit = staffBusinessUnitMapper.selectByPrimaryKey(id);
                if (staffBusinessUnit == null) {
                    //保存
                    staffBusinessUnit = new StaffBusinessUnit();
                    staffBusinessUnit.setName(newName);
                    staffBusinessUnit.setCreateBy(userName);// 发起人
                    staffBusinessUnit.setCreateTime(new Date());//创建时间
                    staffBusinessUnit.setDeleteFlag(0);
                    staffBusinessUnitMapper.insert(staffBusinessUnit);
                } else {
                    //修改
                    String oldName = staffBusinessUnit.getName();
                    staffBusinessUnit.setName(newName);
                    staffBusinessUnit.setUpdateBy(userName);// 更新人id
                    staffBusinessUnit.setUpdateTime(new Date());//更新时间
                    staffBusinessUnitMapper.updateByPrimaryKey(staffBusinessUnit);
                    //真的修改了名称，同时修改关联关系的冗余字段
                    if(!oldName.equals(newName)){
                        Map<String,Object> map = new HashMap<>();
                        map.put("businessUnitId",staffBusinessUnit.getId());
                        List<StaffBusinessUnitCompany> list = staffBusinessUnitCompanyMapper.list(map);
                        for (StaffBusinessUnitCompany staffBusinessUnitCompany : list) {
                            staffBusinessUnitCompany.setBusinessUnitName(newName);
                            staffBusinessUnitCompanyMapper.updateByPrimaryKey(staffBusinessUnitCompany);
                        }

                        //同时修改所有人员
                        map = new HashMap<>();
                        map.put("businessUnitId",staffBusinessUnit.getId());
                        List<StaffPersonnelInfo> infoList = staffPersonnelInfoMapper.list(map);
                        for (StaffPersonnelInfo staffPersonnelInfo : infoList) {
                            staffPersonnelInfo.setBusinessUnit(staffBusinessUnit.getName());
                            staffPersonnelInfoMapper.updateByPrimaryKey(staffPersonnelInfo);
                        }
                    }
                }
            }
            //公司
            else if("company".equals(surveyCode)) {
                //是否重复数据
                String newName = apiReq.getString("name");
                Long id = apiReq.getLong("id");
                StaffCompany newInfo = staffCompanyMapper.selectByOne(newName);
                if(newInfo != null){
                    if(!(id !=null && id.equals(newInfo.getId()))){
                        return new ApiResponse(ApiMsgEnum.StaffInfoRepeat);
                    }
                }
                StaffCompany staffCompany = staffCompanyMapper.selectByPrimaryKey(id);
                if (staffCompany == null) {
                    //保存
                    staffCompany = new StaffCompany();
                    staffCompany.setName(newName);
                    staffCompany.setCreateBy(userName);// 发起人
                    staffCompany.setCreateTime(new Date());//创建时间
                    staffCompany.setDeleteFlag(0);
                    staffCompanyMapper.insert(staffCompany);
                } else {
                    //修改
                    String oldName = staffCompany.getName();
                    staffCompany.setName(newName);
                    staffCompany.setUpdateBy(userName);// 更新人id
                    staffCompany.setUpdateTime(new Date());//更新时间
                    staffCompanyMapper.updateByPrimaryKey(staffCompany);

                    //真的修改了名称，同时修改关联关系的冗余字段
                    if(!oldName.equals(newName)){
                        Map<String,Object> map = new HashMap<>();
                        map.put("companyId",staffCompany.getId());
                        //向下修改
                        List<StaffCompanyOrgan> listTwo = staffCompanyOrganMapper.list(map);
                        for (StaffCompanyOrgan staffCompanyOrgan : listTwo) {
                            staffCompanyOrgan.setCompanyName(newName);
                            staffCompanyOrganMapper.updateByPrimaryKey(staffCompanyOrgan);
                        }

                        //同时修改所有人员
                        map = new HashMap<>();
                        map.put("companyId",staffCompany.getId());
                        List<StaffPersonnelInfo> infoList = staffPersonnelInfoMapper.list(map);
                        for (StaffPersonnelInfo staffPersonnelInfo : infoList) {
                            staffPersonnelInfo.setCompany(staffCompany.getName());
                            staffPersonnelInfoMapper.updateByPrimaryKey(staffPersonnelInfo);
                        }
                    }
                }
            }
            //预算归属公司
            else if("budgetCompany".equals(surveyCode)) {
                //是否重复数据
                String newName = apiReq.getString("name");
                Long id = apiReq.getLong("id");
                StaffBudgetCompany newInfo = staffBudgetCompanyMapper.selectByOne(newName);
                if(newInfo != null){
                    if(!(id !=null && id.equals(newInfo.getId()))){
                        return new ApiResponse(ApiMsgEnum.StaffInfoRepeat);
                    }
                }
                StaffBudgetCompany staffBudgetCompany = staffBudgetCompanyMapper.selectByPrimaryKey(id);
                if (staffBudgetCompany == null) {
                    //保存
                    staffBudgetCompany = new StaffBudgetCompany();
                    staffBudgetCompany.setName(newName);
                    staffBudgetCompany.setCreateBy(userName);// 发起人
                    staffBudgetCompany.setCreateTime(new Date());//创建时间
                    staffBudgetCompany.setDeleteFlag(0);
                    staffBudgetCompany.setState(0);
                    staffBudgetCompanyMapper.insert(staffBudgetCompany);
                } else {
                    //修改
                    String oldName = staffBudgetCompany.getName();
                    staffBudgetCompany.setName(newName);
                    staffBudgetCompany.setUpdateBy(userName);// 更新人id
                    staffBudgetCompany.setUpdateTime(new Date());//更新时间
                    staffBudgetCompany.setState(apiReq.getInt("state"));
                    staffBudgetCompanyMapper.updateByPrimaryKey(staffBudgetCompany);

                    //真的修改了名称，同时修改关联关系的冗余字段
                    if(!oldName.equals(newName)){
                        Map<String,Object> map = new HashMap<>();
                        map.put("companyId",staffBudgetCompany.getId());
                        //向下修改
                        List<StaffBudgetCompanyOrgan> listTwo = staffBudgetCompanyOrganMapper.list(map);
                        for (StaffBudgetCompanyOrgan staffCompanyOrgan : listTwo) {
                            staffCompanyOrgan.setCompanyName(newName);
                            staffBudgetCompanyOrganMapper.updateByPrimaryKey(staffCompanyOrgan);
                        }

                        //同时修改所有人员
                        map = new HashMap<>();
                        map.put("companyId",staffBudgetCompany.getId());
                        List<StaffPersonnelInfo> infoList = staffPersonnelInfoMapper.list(map);
                        for (StaffPersonnelInfo staffPersonnelInfo : infoList) {
                            staffPersonnelInfo.setCompany(staffBudgetCompany.getName());
                            staffPersonnelInfoMapper.updateByPrimaryKey(staffPersonnelInfo);
                        }
                    }
                }
            }
            //机构
            else if("organ".equals(surveyCode)) {
                String organProduct = apiReq.getString("organProduct");
                //是否重复数据
                String newName = apiReq.getString("name");
                Long id = apiReq.getLong("id");
                StaffOrgan newInfo = staffOrganMapper.selectByOne(newName);
                if(newInfo != null){
                    if(!(id !=null && id.equals(newInfo.getId()))){
                        return new ApiResponse(ApiMsgEnum.StaffInfoRepeat);
                    }
                }
                StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(id);
                StaffOrgan staffOrganOld = staffOrganMapper.selectByPrimaryKey(id);
               /* Long companyId = apiReq.getLong("companyId");
                StaffCompany staffCompany = staffCompanyMapper.selectByPrimaryKey(companyId);*/

                Long organManagerStaffId = apiReq.getLong("organManagerStaffId");
                StaffPersonnelInfo organManager = new StaffPersonnelInfo();
                if(organManagerStaffId != null){
                    //机构经理
                    organManager = staffPersonnelInfoMapper.selectByPrimaryKey(organManagerStaffId);
                    //赋“机构经理”角色 108L
                    Map map = new HashMap<>();
                    map.put("userId",organManager.getUserId());
                    map.put("roleId",108);
                    BusUserRole busUserRole = busUserRoleMapper.selectBusRoleInfo(map);
                    if(busUserRole == null){
                        busUserRole = new BusUserRole();
                        busUserRole.setRoleId(108L);
                        busUserRole.setUserId(organManager.getUserId());
                        busUserRoleMapper.insert(busUserRole);
                    }
                }

                Long superiorManagerStaffId = apiReq.getLong("superiorManagerStaffId");
                StaffPersonnelInfo superiorManager = new StaffPersonnelInfo();
                Integer organAttribute = apiReq.getInt("organAttribute");
                if(superiorManagerStaffId != null){
                    //上级分管总
                    superiorManager = staffPersonnelInfoMapper.selectByPrimaryKey(apiReq.getLong("superiorManagerStaffId"));
                    //赋“分管总”角色 109L
                    Map map = new HashMap<>();
                    map.put("userId",superiorManager.getUserId());
                    map.put("roleId",109);
                    BusUserRole busUserRole = busUserRoleMapper.selectBusRoleInfo(map);
                    if(busUserRole == null){
                        busUserRole = new BusUserRole();
                        busUserRole.setRoleId(109L);
                        busUserRole.setUserId(superiorManager.getUserId());
                        busUserRoleMapper.insert(busUserRole);
                    }
                }

                if (staffOrgan == null) {
                    //保存
                    staffOrgan = new StaffOrgan();
                    /*if(staffCompany !=null){
                        staffOrgan.setCompanyId(staffCompany.getId());
                        staffOrgan.setCompanyName(staffCompany.getName());
                    }*/
                    staffOrgan.setOrganAttribute(organAttribute);
                    staffOrgan.setName(apiReq.getString("name"));
                    staffOrgan.setType(apiReq.getInt("type"));
                    staffOrgan.setCreateBy(userName);// 发起人
                    staffOrgan.setCreateTime(new Date());//创建时间
                    staffOrgan.setDeleteFlag(0);
                    //机构经理
                    staffOrgan.setOrganManagerName(organManager.getRealName());
                    staffOrgan.setOrganManagerStaffId(organManager.getId());
                    staffOrgan.setOrganManagerUserId(organManager.getUserId());
                    //上级分管总
                    staffOrgan.setSuperiorManagerName(superiorManager.getRealName());
                    staffOrgan.setSuperiorManagerStaffId(superiorManager.getId());
                    staffOrgan.setSuperiorManagerUserId(superiorManager.getUserId());
                    /*staffOrgan.setOrganProduct(organProduct);*/

                    staffOrgan.setOrganType(apiReq.getInt("organType"));
                    staffOrgan.setIsCanModify(apiReq.getInt("isCanModify"));
                    staffOrgan.setIsCanModify(0);
                    staffOrgan.setState(apiReq.getInt("state"));
                    staffOrgan.setPerformance(apiReq.getInt("performance"));
                    staffOrgan.setAccOutEqual(apiReq.getInt("accOutEqual"));
                    staffOrgan.setWarnMoney(apiReq.getDouble("warnMoney"));
                    staffOrganMapper.insert(staffOrgan);

                    String[] organProStr = organProduct.split(",");
                    for (String o:organProStr) {
                        /**多条存储中间表*/
                        StaffOrganProduct staffOrganProduct=new StaffOrganProduct();
                        staffOrganProduct.setOrganId(staffOrgan.getId());
                        staffOrganProduct.setOrganName(staffOrgan.getName());
                        Long productEnumId=Long.parseLong(o);
                        staffOrganProduct.setProductEnumId(productEnumId);
                        CommonEnum commonEnum = commonEnumMapper.selectByPrimaryKey(productEnumId);
                        staffOrganProduct.setProductEnumName(commonEnum.getEnumName());
                        staffOrganProductMapper.insertSelective(staffOrganProduct);
                    }
                } else {
                    //修改
                    String oldName = staffOrgan.getName();
                   /* if(staffCompany !=null){
                        staffOrgan.setCompanyId(staffCompany.getId());
                        staffOrgan.setCompanyName(staffCompany.getName());
                    }*/
                    staffOrgan.setName(apiReq.getString("name"));
                    staffOrgan.setType(apiReq.getInt("type"));
                    staffOrgan.setUpdateBy(userName);// 更新人id
                    staffOrgan.setUpdateTime(new Date());//更新时间
                    staffOrgan.setOrganAttribute(organAttribute);
                    //机构经理
                    staffOrgan.setOrganManagerName(organManager.getRealName());
                    staffOrgan.setOrganManagerStaffId(organManager.getId());
                    staffOrgan.setOrganManagerUserId(organManager.getUserId());
                    //上级分管总
                    staffOrgan.setSuperiorManagerName(superiorManager.getRealName());
                    staffOrgan.setSuperiorManagerStaffId(superiorManager.getId());
                    staffOrgan.setSuperiorManagerUserId(superiorManager.getUserId());
                    /*staffOrgan.setOrganProduct(organProduct);*/
                    Map  objectObjectHashMap = new HashMap<>();
                    objectObjectHashMap.put("id",apiReq.getInt("id"));
                    objectObjectHashMap.put("performance",apiReq.getInt("performance"));
                    staffPersonnelInfoMapper.updateByOrganKey(objectObjectHashMap);
                    staffOrgan.setOrganType(apiReq.getInt("organType"));
                    staffOrgan.setIsCanModify(apiReq.getInt("isCanModify"));
                    staffOrgan.setState(apiReq.getInt("state"));
                    staffOrgan.setPerformance(apiReq.getInt("performance"));
                    staffOrgan.setAccOutEqual(apiReq.getInt("accOutEqual"));
                    staffOrgan.setWarnMoney(apiReq.getDouble("warnMoney"));

                    staffOrganMapper.updateByPrimaryKey(staffOrgan);
                    //删除原有机构产品，新增勾选的产品
                    Map OrganProMap1 = new HashMap<>();
                    OrganProMap1.put("organId",staffOrgan.getId());
                    staffOrganProductMapper.deleteByParam(OrganProMap1);
                    if (!StringUtils.isEmpty(organProduct)){
                        String[] organProStr = organProduct.split(",");
                        for (String o:organProStr) {
                            /**多条存储中间表*/
                            Long productEnumId=Long.parseLong(o);
                            Map OrganProMap = new HashMap<>();
                            OrganProMap.put("organId",staffOrgan.getId());
                            OrganProMap.put("productEnumId",productEnumId);
                            StaffOrganProduct staffOrganProduct=this.staffOrganProductMapper.selectOrganProByParam(OrganProMap);
                            if(staffOrganProduct!=null){
                                staffOrganProduct.setOrganId(staffOrgan.getId());
                                staffOrganProduct.setOrganName(staffOrgan.getName());
                                staffOrganProduct.setProductEnumId(productEnumId);
                                Map map = new HashMap<>();
                                map.put("parentEnumCode","billingEnum");
                                map.put("enumCode",productEnumId);
                                CommonEnum commonEnum = commonEnumMapper.selectBill(map);
                                staffOrganProduct.setProductEnumName(commonEnum.getEnumName());
                                staffOrganProductMapper.updateByPrimaryKeySelective(staffOrganProduct);
                            }else{
                                staffOrganProduct=new StaffOrganProduct();
                                staffOrganProduct.setOrganId(staffOrgan.getId());
                                staffOrganProduct.setOrganName(staffOrgan.getName());
                                staffOrganProduct.setProductEnumId(productEnumId);
                                Map map = new HashMap<>();
                                map.put("parentEnumCode","billingEnum");
                                map.put("enumCode",productEnumId);
                                CommonEnum commonEnum = commonEnumMapper.selectBill(map);
                                staffOrganProduct.setProductEnumName(commonEnum.getEnumName());
                                staffOrganProductMapper.insertSelective(staffOrganProduct);
                            }

                        }
                    }

                    //旧的“机构经理”和“分管总” -- 查询其是否还是 其他机构的“机构经理”和“分管总”，如果不存在，则去掉角色
                    Long oldOrganManagerUserId = staffOrganOld.getOrganManagerUserId();
                    Map map = new HashMap<>();
                    map.put("organManagerUserId",oldOrganManagerUserId);
                    List<StaffOrgan> organList = staffOrganMapper.list(map);
                    if(organList.size() == 0){
                        map = new HashMap<>();
                        map.put("userId",oldOrganManagerUserId);
                        map.put("roleId",108);
                        busUserRoleMapper.deleteByParam(map);
                    }
                    //分管总的处理
                    Long oldSuperiorManagerUserId = staffOrganOld.getSuperiorManagerUserId();
                    map = new HashMap<>();
                    map.put("superiorManagerUserId",oldSuperiorManagerUserId);
                    organList = staffOrganMapper.list(map);
                    if(organList.size() == 0){
                        map = new HashMap<>();
                        map.put("userId",oldSuperiorManagerUserId);
                        map.put("roleId",109);
                        busUserRoleMapper.deleteByParam(map);
                    }

                    //真的修改了名称，同时修改关联关系的冗余字段
                    if(!oldName.equals(newName)){
                        map = new HashMap<>();
                        map.put("organId",staffOrgan.getId());
                        //向上修改
                        List<StaffCompanyOrgan> list = staffCompanyOrganMapper.list(map);
                        for (StaffCompanyOrgan staffCompanyOrgan : list) {
                            staffCompanyOrgan.setOrganName(newName);
                            staffCompanyOrganMapper.updateByPrimaryKey(staffCompanyOrgan);
                        }
                        StaffBudgetCompanyOrgan staffBudgetCompanyOrgan = staffBudgetCompanyOrganMapper.selectByOrganId(staffOrgan.getId());
                        if (staffBudgetCompanyOrgan != null){
                            staffBudgetCompanyOrgan.setOrganName(staffOrgan.getName());
                            staffBudgetCompanyOrganMapper.updateByPrimaryKey(staffBudgetCompanyOrgan);
                        }

                        //向下修改
                        List<StaffOrganDepartment> listTwo = staffOrganDepartmentMapper.list(map);
                        for (StaffOrganDepartment staffOrganDepartment : listTwo) {
                            staffOrganDepartment.setOrganName(newName);
                            staffOrganDepartmentMapper.updateByPrimaryKey(staffOrganDepartment);
                        }
                        //同时修改所有人员
                        map = new HashMap<>();
                        map.put("organId",staffOrgan.getId());
                        List<StaffPersonnelInfo> infoList = staffPersonnelInfoMapper.list(map);
                        for (StaffPersonnelInfo staffPersonnelInfo : infoList) {
                            staffPersonnelInfo.setOrgan(staffOrgan.getName());
                            staffPersonnelInfoMapper.updateByPrimaryKey(staffPersonnelInfo);
                        }
                    }

                }
            }
            //部门
            else if("department".equals(surveyCode)) {
                //是否重复数据
                String newName = apiReq.getString("name");
                Long id = apiReq.getLong("id");
                StaffDepartment newInfo = staffDepartmentMapper.selectByOne(newName);
                if(newInfo != null){
                    if(!(id !=null && id.equals(newInfo.getId()))){
                        return new ApiResponse(ApiMsgEnum.StaffInfoRepeat);
                    }
                }
                StaffDepartment staffDepartment = staffDepartmentMapper.selectByPrimaryKey(id);
                Long companyId = apiReq.getLong("companyId");
                StaffCompany staffCompany = staffCompanyMapper.selectByPrimaryKey(companyId);
                if (staffDepartment == null) {
                    //保存
                    staffDepartment = new StaffDepartment();
                    if(staffCompany !=null){
                        staffDepartment.setCompanyId(staffCompany.getId());
                        staffDepartment.setCompanyName(staffCompany.getName());
                    }
                    staffDepartment.setName(apiReq.getString("name"));
                    staffDepartment.setCreateBy(userName);// 发起人
                    staffDepartment.setCreateTime(new Date());//创建时间
                    staffDepartment.setDeleteFlag(0);
                    staffDepartment.setState(0);
                    staffDepartmentMapper.insert(staffDepartment);
                } else {
                    //修改
                    String oldName = staffDepartment.getName();

                    if(staffCompany !=null){
                        staffDepartment.setCompanyId(staffCompany.getId());
                        staffDepartment.setCompanyName(staffCompany.getName());
                    }
                    staffDepartment.setName(apiReq.getString("name"));
                    staffDepartment.setUpdateBy(userName);// 更新人id
                    staffDepartment.setUpdateTime(new Date());//更新时间
                    staffDepartment.setState(apiReq.getInt("state"));
                    staffDepartmentMapper.updateByPrimaryKey(staffDepartment);


                    //真的修改了名称，同时修改关联关系的冗余字段
                    if(!oldName.equals(newName)){
                        Map<String,Object> map = new HashMap<>();
                        map.put("departmentId",staffDepartment.getId());
                        //向上修改
                        List<StaffOrganDepartment> list = staffOrganDepartmentMapper.list(map);
                        for (StaffOrganDepartment staffOrganDepartment : list) {
                            staffOrganDepartment.setDepartmentName(newName);
                            staffOrganDepartmentMapper.updateByPrimaryKey(staffOrganDepartment);
                        }
                        //向下修改
                        List<StaffDepartmentJobPost> listTwo = staffDepartmentJobPostMapper.list(map);
                        for (StaffDepartmentJobPost staffDepartmentJobPost : listTwo) {
                            staffDepartmentJobPost.setDepartmentName(newName);
                            staffDepartmentJobPostMapper.updateByPrimaryKey(staffDepartmentJobPost);
                        }
                        //同时修改所有人员
                        map = new HashMap<>();
                        map.put("departmentId",staffDepartment.getId());
                        List<StaffPersonnelInfo> infoList = staffPersonnelInfoMapper.list(map);
                        for (StaffPersonnelInfo staffPersonnelInfo : infoList) {
                            staffPersonnelInfo.setDepartment(staffDepartment.getName());
                            staffPersonnelInfoMapper.updateByPrimaryKey(staffPersonnelInfo);
                        }
                    }
                }
            }


            //小组
            else if("team".equals(surveyCode)) {
                //是否重复数据
                String newName = apiReq.getString("name");
                Long id = apiReq.getLong("id");
                StaffTeam newInfo = staffTeamMapper.selectByOne(newName);
                if(newInfo != null){
                    if(!(id !=null && id.equals(newInfo.getId()))){
                        return new ApiResponse(ApiMsgEnum.StaffInfoRepeat);
                    }
                }
                StaffTeam staffTeam = staffTeamMapper.selectByPrimaryKey(id);
                Long companyId = apiReq.getLong("companyId");
                StaffCompany staffCompany = staffCompanyMapper.selectByPrimaryKey(companyId);
                if (staffTeam == null) {
                    //保存
                    staffTeam = new StaffTeam();
                    staffTeam.setName(apiReq.getString("name"));
                    staffTeam.setCreateBy(userName);// 发起人
                    staffTeam.setCreateTime(new Date());//创建时间
                    staffTeam.setDeleteFlag(0);
                    staffTeam.setState(0);
                    staffTeamMapper.insert(staffTeam);
                } else {
                    //修改
                    String oldName = staffTeam.getName();
                    staffTeam.setName(apiReq.getString("name"));
                    staffTeam.setUpdateBy(userName);// 更新人id
                    staffTeam.setUpdateTime(new Date());//更新时间
                    staffTeam.setState(apiReq.getInt("state"));
                    staffTeamMapper.updateByPrimaryKey(staffTeam);


                    //真的修改了名称，同时修改关联关系的冗余字段
                    if(!oldName.equals(newName)){
                        Map<String,Object> map = new HashMap<>();
                        map.put("teamId",staffTeam.getId());

                        //向上修改
                        List<StaffDepartmentTeam> list = staffDepartmentTeamMapper.list(map);
                        for (StaffDepartmentTeam staffDepartmentTeam : list) {
                            staffDepartmentTeam.setTeamName(newName);
                            staffDepartmentTeamMapper.updateByPrimaryKey(staffDepartmentTeam);
                        }

                        //向下修改
                        List<StaffTeamJobPost> listTwo = staffTeamJobPostMapper.list(map);
                        for (StaffTeamJobPost staffTeamJobPost : listTwo) {
                            staffTeamJobPost.setTeamName(newName);
                            staffTeamJobPostMapper.updateByPrimaryKey(staffTeamJobPost);
                        }
                        //同时修改所有人员
                        map = new HashMap<>();
                        map.put("teamId",staffTeam.getId());
                        List<StaffPersonnelInfo> infoList = staffPersonnelInfoMapper.list(map);
                        for (StaffPersonnelInfo staffPersonnelInfo : infoList) {
                            staffPersonnelInfo.setTeam(staffTeam.getName());
                            staffPersonnelInfoMapper.updateByPrimaryKey(staffPersonnelInfo);
                        }
                    }
                }
            }




            //岗位
            else if("jobPost".equals(surveyCode)) {
                //是否重复数据
                String newName = apiReq.getString("name");
                Long id = apiReq.getLong("id");
                StaffJobPost newInfo = staffJobPostMapper.selectByOne(newName);
                if(newInfo != null){
                    if(!(id !=null && id.equals(newInfo.getId()))){
                        return new ApiResponse(ApiMsgEnum.StaffInfoRepeat);
                    }
                }
                StaffJobPost staffJobPost = staffJobPostMapper.selectByPrimaryKey(id);
                Long departmentId = apiReq.getLong("departmentId");
                StaffDepartment staffDepartment = staffDepartmentMapper.selectByPrimaryKey(departmentId);
                if (staffJobPost == null) {
                    //保存
                    staffJobPost = new StaffJobPost();
                    if(staffDepartment !=null){
                        staffJobPost.setDepartmentId(staffDepartment.getId());
                        staffJobPost.setDepartmentName(staffDepartment.getName());
                    }
                    staffJobPost.setName(apiReq.getString("name"));
                    staffJobPost.setCreateBy(userName);// 发起人
                    staffJobPost.setCreateTime(new Date());//创建时间
                    staffJobPost.setCostType(apiReq.getInt("costType"));
                    staffJobPost.setState(0);
                    staffJobPost.setDeleteFlag(0);
                    staffJobPostMapper.insert(staffJobPost);
                } else {
                    //修改
                    String oldName = staffJobPost.getName();

                    if(staffDepartment !=null){
                        staffJobPost.setDepartmentId(staffDepartment.getId());
                        staffJobPost.setDepartmentName(staffDepartment.getName());
                    }
                    staffJobPost.setName(apiReq.getString("name"));
                    staffJobPost.setUpdateBy(userName);// 更新人id
                    staffJobPost.setUpdateTime(new Date());//更新时间
                    staffJobPost.setCostType(apiReq.getInt("costType"));
                    staffJobPost.setState(apiReq.getInt("state"));
                    staffJobPostMapper.updateByPrimaryKey(staffJobPost);

                    //真的修改了名称，同时修改关联关系的冗余字段
                    if(!oldName.equals(newName)){
                        Map<String,Object> map = new HashMap<>();
                        map.put("jobPostId",staffJobPost.getId());
                        //向上修改
                        List<StaffDepartmentJobPost> listTwo = staffDepartmentJobPostMapper.list(map);
                        for (StaffDepartmentJobPost staffDepartmentJobPost : listTwo) {
                            staffDepartmentJobPost.setJobPostName(newName);
                            staffDepartmentJobPostMapper.updateByPrimaryKey(staffDepartmentJobPost);
                        }
                        //同时修改所有人员
                        map = new HashMap<>();
                        map.put("jobPostId",staffJobPost.getId());
                        List<StaffPersonnelInfo> infoList = staffPersonnelInfoMapper.list(map);
                        for (StaffPersonnelInfo staffPersonnelInfo : infoList) {
                            staffPersonnelInfo.setJobPost(staffJobPost.getName());
                            staffPersonnelInfoMapper.updateByPrimaryKey(staffPersonnelInfo);
                        }
                    }
                }
            }
            //工资条
            else if("paySlip".equals(surveyCode)) {
                //是否重复数据
                String workTime = apiReq.getString("workTime");
                Long id = apiReq.getLong("id");
                StaffPaySlip newInfo = staffPaySlipMapper.selectByWorkTime(workTime);
//                if(newInfo != null){
//                    if(!(id !=null && id.equals(newInfo.getId()))){
//                        return new ApiResponse(ApiMsgEnum.StaffInfoRepeat);
//                    }
//                }
                //必须创建“每月已上班天数”
                StaffWorkingDaysInfo workingDaysInfo = staffWorkingDaysInfoMapper.selectByWorkTime(workTime);
                if(workingDaysInfo == null){
                    return new ApiResponse(ApiMsgEnum.StaffWorkingDaysInfo);
                }

                StaffPaySlip staffPaySlip = staffPaySlipMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if (staffPaySlip == null) {
                    //保存
                    staffPaySlip = new StaffPaySlip();
                    staffPaySlip.setSlipState(0); //0:待人事处理,1:待财务审核,2:待总经理审核,3:工资条完成
                    staffPaySlip.setSlipStateName("待人事专员处理");
                    staffPaySlip.setWorkTime(workTime);
                    staffPaySlip.setCreateBy(userName);// 发起人
                    staffPaySlip.setCreateTime(new Date());//创建时间
                    staffPaySlip.setUpdateTime(new Date());//更新时间（在此新增：主要为了排序）
                    staffPaySlip.setDeleteFlag(0);
                    staffPaySlip.setDivsionOne("0");
                    staffPaySlip.setDivsionTwo("0");
                    staffPaySlip.setDivsionThree("0");
                    staffPaySlipMapper.insert(staffPaySlip);
                    Long generatedId = staffPaySlip.getId();
                    //保存工资条明细
                    Map<String,Object> map = new HashMap<>();
                    map.put("search",1);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                    Date sDate = sdf.parse(workTime);
                    Calendar c = Calendar.getInstance();
                    c.setTime(sDate);
                    c.add(Calendar.MONTH, 1);
                    map.put("entryTime",c.getTime());//入职时间在该月份之内和之前
                    map.put("staffPaySlipId",staffPaySlip.getId());
                    map.put("userName",userName);
                    map.put("workingDays",workingDaysInfo.getWrokingDays());
                    map.put("workTime",workTime);
                    staffPayPersonnelSlipMapper.generate(map);// 插入工资子表  insert into select   2、新需求：2020年5月25日15:18:19  创建工资条时，状态为兼职的员工不进入工资条

                    map = new HashMap<>();
                    map.put("staffPaySlipId",staffPaySlip.getId());
                    map.put("entryTime",c.getTime());
                    staffPaySlipManagerMapper.generateOrganManager(map);//插入“工资条--管理人员审核状态”表  insert into select (机构经理)
                    staffPaySlipManagerMapper.generateSuperiorManager(map);//插入“工资条--管理人员审核状态”表  insert into select (分管总)

                    map = new HashMap<>();
                    map.put("staffPaySlipId",staffPaySlip.getId());
                    List<StaffPayPersonnelSlip> staffPayPersonnelSlips = staffPayPersonnelSlipMapper.list(map);
                    //是调查员岗位，并且是“直营”，（办公补贴）
                    for (StaffPayPersonnelSlip staffPayPersonnelSlip : staffPayPersonnelSlips) {
//                        staffPayPersonnelSlips.stream().forEach(staffPayPersonnelSlip -> {
                        StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectByPrimaryKey(staffPayPersonnelSlip.getStaffPersonnelId());
                        if(staffPersonnelInfo!=null && staffPersonnelInfo.getJobPostId().intValue() == 57){
                            //2020年9月24日 机构为：“保险调查运营中心”的调查员，没有
                            StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(staffPersonnelInfo.getOrganId());
                            Boolean needOfficeSubsidies = true;
                            if(staffOrgan != null && "bxdcyyzx".equals(staffOrgan.getCode())){
                                needOfficeSubsidies = false;
                            }
                            if(needOfficeSubsidies){
                                map = new HashMap<>();
                                map.put("userTelphone",staffPayPersonnelSlip.getUserTel());
                                UserInfo user = userInfoMapper.selectUserInfoByPhone(map);
                                if(user !=null){
                                    SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(user.getUserId());
                                    if(surveyInvestigator!=null && surveyInvestigator.getType() ==1){
                                        String entryTime = format.format(staffPersonnelInfo.getEntryTime());//员工入职时间
                                        if(staffPersonnelInfo.getQuitTime() !=null){
                                            String quitTime = format.format(staffPersonnelInfo.getQuitTime());//员工离职时间
                                            if(workTime.compareTo(entryTime)>0 && quitTime.compareTo(workTime)>0){
                                                staffPayPersonnelSlip.setOfficeSubsidies(100D);
                                                staffPayPersonnelSlip.setRealWages(staffPayPersonnelSlip.getRealWages() + 100D);
                                            }
                                        }else{
                                            if(workTime.compareTo(entryTime)>0){
                                                staffPayPersonnelSlip.setOfficeSubsidies(100D);
                                                staffPayPersonnelSlip.setRealWages(staffPayPersonnelSlip.getRealWages() + 100D);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        //计算“本人应出勤天数”
                        int year = Integer.valueOf(workTime.substring(0, 4));
                        int month = Integer.valueOf(workTime.substring(5, 7));

                        if(staffPersonnelInfo != null){
                            Date startTime = staffPersonnelInfo.getEntryTime();
                            Date endTime = staffPersonnelInfo.getQuitTime();
                            //入职时间是否在本月
                            if(startTime != null){
                                if(!isThisTime(startTime,workTime)){
                                    startTime = DateUtils.getFirstDayOfYearMonth(year,month);//获取当月第一天
                                }
                            }else{
                                startTime = DateUtils.getFirstDayOfYearMonth(year,month);//获取当月第一天
                            }
                            //离职时间是否在本月
                            if(endTime != null){
                                if(!isThisTime(endTime,workTime)){
                                    endTime = DateUtils.getLastDayOfYearMonth(year, month);//当月最后一天
                                }
                            }else{
                                endTime = DateUtils.getLastDayOfYearMonth(year, month);//当月最后一天
                            }


//                            int days = GetWorkDay.calLeaveDays(startTime, endTime, 3);
                            int days = GetWorkDay.getDutyDays(startTime, endTime);
                            staffPayPersonnelSlip.setRealWorkingDays(Double.valueOf(days));
                            //2025年2月11日 入职日期和离职日期不在本月 ，应实际出勤天数等于出勤天数
                            if (!(isThisTime(staffPersonnelInfo.getEntryTime(),workTime) || isThisTime(staffPersonnelInfo.getQuitTime(),workTime))){
                                staffPayPersonnelSlip.setRealWorkingDays(workingDaysInfo.getWrokingDays());
                            }

                        }
                        StaffOrgan staffOrgan=staffOrganMapper.selectByPrimaryKey(staffPersonnelInfo.getOrganId());
                        //判断人员说在的部门机构是否是工资条发放0：绩效 ，1：工资条
                        if(staffOrgan.getPerformance()==1){
                            StaffPerformance staffPerformance = new StaffPerformance();
                            staffPerformance.setWorkTime(workTime);
                            staffPerformance.setPerformanceState(0); //0:待人事处理,1:待财务审核,2:待总经理审核,3:绩效工资完成
                            staffPerformance.setPerformanceStateName("待人事专员处理");
                            staffPerformance.setCreateBy(userName);// 发起人
                            staffPerformance.setCreateTime(new Date());//创建时间
                            staffPerformance.setUpdateTime(new Date());//更新时间（在此新增：主要为了排序）
                            staffPerformance.setDeleteFlag(0);
                            staffPerformance.setDivsionOne("0");
                            staffPerformance.setDivsionTwo("0");
                            staffPerformance.setDivsionThree("0");
                            //同步钉钉数据，并保存绩效详情
                            StaffPerformancePersonnel staffPerformancePersonnel = backendStaffPerformanceApiImpl.generateAAAStaffPerformance(staffPerformance, userInfo, staffPaySlip, staffPayPersonnelSlip);
                            //郑亚东1所算出的绩效加到郑亚东上
                            Map<String,Double> res = new HashMap();
                            if(staffPayPersonnelSlip.getRealName().equals("郑亚东1")){
                                res.put("RealPay",staffPerformancePersonnel.getRealPay());
                            }
                            if(staffPayPersonnelSlip.getRealName().equals("郑亚东")){
                                Double realPay = res.get("RealPay");
                                staffPerformancePersonnel.setRealPay(realPay+staffPerformancePersonnel.getRealPay());
                            }
                            staffPayPersonnelSlip.setWelfarePay(staffPerformancePersonnel.getRealPay());



                         }
                        //各种计算
                        staffPayPersonnelSlip = backendStaffPaySlipApiImpl.returnSlip(staffPersonnelInfo,staffPayPersonnelSlip,true);
                        staffPayPersonnelSlipMapper.updateByPrimaryKey(staffPayPersonnelSlip);
                    }
                } else {
                    //修改
                    staffPaySlip.setWorkTime(apiReq.getString("workTime"));
                    staffPaySlip.setUpdateBy(userName);// 更新人id
                    staffPaySlip.setUpdateTime(new Date());//更新时间
                    staffPaySlipMapper.updateByPrimaryKey(staffPaySlip);
                }
            }
            //绩效
            else if("performance".equals(surveyCode)) {
                //是否重复数据
                String workTime = apiReq.getString("workTime");
                Long id = apiReq.getLong("id");
                StaffPerformance newInfo = staffPerformanceMapper.selectByWorkTime(workTime);
                if(newInfo != null){
                    if(!(id !=null && id.equals(newInfo.getId()))){
                        return new ApiResponse(ApiMsgEnum.StaffInfoRepeat);
                    }
                }
                //必须创建“工资条”
                StaffPaySlip staffPaySlip = staffPaySlipMapper.selectByWorkTime(workTime);
                if(staffPaySlip == null){
                    return new ApiResponse(ApiMsgEnum.StaffPaySlip);
                }

                StaffPerformance staffPerformance = staffPerformanceMapper.selectByPrimaryKey(apiReq.getLong("id"));
                if (staffPerformance == null) {
                    //保存
                    staffPerformance = new StaffPerformance();
                    staffPerformance.setWorkTime(workTime);
                    staffPerformance.setPerformanceState(0); //0:待人事处理,1:待财务审核,2:待总经理审核,3:绩效工资完成
                    staffPerformance.setPerformanceStateName("待人事专员处理");
                    staffPerformance.setCreateBy(userName);// 发起人
                    staffPerformance.setCreateTime(new Date());//创建时间
                    staffPerformance.setUpdateTime(new Date());//更新时间（在此新增：主要为了排序）
                    staffPerformance.setDeleteFlag(0);
                    staffPerformance.setDivsionOne("0");
                    staffPerformance.setDivsionTwo("0");
                    staffPerformance.setDivsionThree("0");
                    staffPerformanceMapper.insert(staffPerformance);

                    //同步钉钉数据，并保存绩效详情
                    backendStaffPerformanceApiImpl.generateStaffPerformance(staffPerformance,userInfo,staffPaySlip);

                    Map map = new HashMap<>();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                    Date sDate = sdf.parse(workTime);
                    Calendar c = Calendar.getInstance();
                    c.setTime(sDate);
                    c.add(Calendar.MONTH, 1);
                    map.put("staffPerformanceId",staffPerformance.getId());
                    map.put("entryTime",c.getTime());
                    staffPerformanceManagerMapper.generateOrganManager(map);//插入“绩效--管理人员审核状态”表  insert into select (机构经理)

                    //需求：2020年8月31日  分管总审核两次
                    map.put("managerType",3);
                    staffPerformanceManagerMapper.generateSuperiorManager(map);//插入“绩效--管理人员审核状态”表  insert into select (分管总  一审)
                    map.put("managerType",2);
                    staffPerformanceManagerMapper.generateSuperiorManager(map);//插入“绩效--管理人员审核状态”表  insert into select (分管总  二审)
                } else {
                    //修改
                    staffPerformance.setWorkTime(apiReq.getString("workTime"));
                    staffPerformance.setUpdateBy(userName);// 更新人id
                    staffPerformance.setUpdateTime(new Date());//更新时间
                    staffPerformanceMapper.updateByPrimaryKey(staffPerformance);
                }
            }
            //事业部关联公司
            else if("businessUnitCompany".equals(surveyCode)){
                String btnCode = apiReq.getString("btnCode");
                Long businessUnitId = apiReq.getLong("businessUnitId");
                StaffBusinessUnit staffBusinessUnit = staffBusinessUnitMapper.selectByPrimaryKey(businessUnitId);
                if("1000".equals(btnCode)){
                    //先删除记录，再保存全新数据
                    Map<String,Object> map = new HashMap<>();
                    map.put("businessUnitId",businessUnitId);
                    staffBusinessUnitCompanyMapper.deleteByInfo(map);
                    //保存数据
                    String buss = apiReq.getString("roleIds");
                    if(buss!=null) {
                        String[] role = buss.split(",");
                        for (String ro : role) {
                            StaffBusinessUnitCompany staffBusinessUnitCompany =new StaffBusinessUnitCompany();
                            //机构信息
                            StaffCompany staffCompany = staffCompanyMapper.selectByPrimaryKey(Long.valueOf(ro));
                            if(staffCompany!=null){
                                staffBusinessUnitCompany.setCompanyId(staffCompany.getId());
                                staffBusinessUnitCompany.setCompanyName(staffCompany.getName());
                            }
                            staffBusinessUnitCompany.setBusinessUnitId(staffBusinessUnit.getId());
                            staffBusinessUnitCompany.setBusinessUnitName(staffBusinessUnit.getName());
                            staffBusinessUnitCompanyMapper.insert(staffBusinessUnitCompany);
                        }
                    }
                }
            }
            //公司关联机构
            else if("companyOrgan".equals(surveyCode)){
                String btnCode = apiReq.getString("btnCode");
                Long companyId = apiReq.getLong("companyId");
                StaffCompany staffCompany = staffCompanyMapper.selectByPrimaryKey(companyId);
                if("1000".equals(btnCode)){
                    //先删除记录，再保存全新数据
                    Map<String,Object> map = new HashMap<>();
                    map.put("companyId",companyId);
                    staffCompanyOrganMapper.deleteByInfo(map);
                    //保存数据
                    String buss = apiReq.getString("roleIds");
                    if(buss!=null) {
                        String[] role = buss.split(",");
                        for (String ro : role) {
                            StaffCompanyOrgan staffCompanyOrgan =new StaffCompanyOrgan();
                            //机构信息
                            StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(Long.valueOf(ro));
                            if(staffOrgan!=null){
                                staffCompanyOrgan.setOrganId(staffOrgan.getId());
                                staffCompanyOrgan.setOrganName(staffOrgan.getName());
                            }
                            staffCompanyOrgan.setCompanyId(staffCompany.getId());
                            staffCompanyOrgan.setCompanyName(staffCompany.getName());
                            staffCompanyOrganMapper.insert(staffCompanyOrgan);
                        }
                    }
                }
            }
            //预算公司关联机构
            else if("budgetCompanyOrgan".equals(surveyCode)){
                String btnCode = apiReq.getString("btnCode");
                Long companyId = apiReq.getLong("companyId");
                StaffBudgetCompany staffCompany = staffBudgetCompanyMapper.selectByPrimaryKey(companyId);
                if("1000".equals(btnCode)){
                    //先删除记录，再保存全新数据
                    Map<String,Object> map = new HashMap<>();
                    map.put("companyId",companyId);
                    staffBudgetCompanyOrganMapper.deleteByInfo(map);
                    //保存数据
                    String buss = apiReq.getString("roleIds");
                    if(buss!=null) {
                        String[] role = buss.split(",");
                        for (String ro : role) {
                            StaffBudgetCompanyOrgan staffCompanyOrgan =new StaffBudgetCompanyOrgan();
                            //机构信息
                            StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(Long.valueOf(ro));
                            if(staffOrgan!=null){
                                staffCompanyOrgan.setOrganId(staffOrgan.getId());
                                staffCompanyOrgan.setOrganName(staffOrgan.getName());
                            }
                            staffCompanyOrgan.setCompanyId(staffCompany.getId());
                            staffCompanyOrgan.setCompanyName(staffCompany.getName());
                            StaffBudgetCompanyOrgan staffBudgetCompanyOrgan = staffBudgetCompanyOrganMapper.selectByOrganId(staffOrgan.getId());
                            if (staffBudgetCompanyOrgan != null){
                                staffBudgetCompanyOrganMapper.deleteByPrimaryKey(staffBudgetCompanyOrgan.getId());
                            }
                            staffBudgetCompanyOrganMapper.insert(staffCompanyOrgan);

                            //修改员工的预算归属公司
                            List<StaffPersonnelInfo> staffPersonnelInfos = staffPersonnelInfoMapper.selectListByOrgId(staffOrgan.getId());
                            for (StaffPersonnelInfo staffPersonnelInfo : staffPersonnelInfos) {
                                staffPersonnelInfo.setCompanyId(staffCompany.getId());
                                staffPersonnelInfo.setCompany(staffCompany.getName());
                                staffPersonnelInfoMapper.updateByPrimaryKey(staffPersonnelInfo);
                            }
                        }
                    }
                }
            }
            //机构关联部门
            else if("organDepartment".equals(surveyCode)){
                String btnCode = apiReq.getString("btnCode");
                Long companyId = apiReq.getLong("companyId");
                Long organId = apiReq.getLong("organId");
                StaffCompany staffCompany = staffCompanyMapper.selectByPrimaryKey(companyId);
                StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(organId);
                if("1000".equals(btnCode)){
                    //先删除记录，再保存全新数据
                    Map<String,Object> map = new HashMap<>();
                    map.put("companyId",companyId);
                    map.put("organId",organId);
                    staffOrganDepartmentMapper.deleteByInfo(map);
                    //保存数据
                    String buss = apiReq.getString("roleIds");
                    if(buss!=null) {
                        String[] role = buss.split(",");
                        for (String ro : role) {
                            StaffOrganDepartment staffOrganDepartment =new StaffOrganDepartment();
                            //部门信息
                            StaffDepartment staffDepartment = staffDepartmentMapper.selectByPrimaryKey(Long.valueOf(ro));
                            if(staffDepartment!=null){
                                staffOrganDepartment.setDepartmentId(staffDepartment.getId());
                                staffOrganDepartment.setDepartmentName(staffDepartment.getName());
                            }
                            staffOrganDepartment.setCompanyId(staffCompany.getId());
                            staffOrganDepartment.setCompanyName(staffCompany.getName());
                            staffOrganDepartment.setOrganId(staffOrgan.getId());
                            staffOrganDepartment.setOrganName(staffOrgan.getName());
                            staffOrganDepartmentMapper.insert(staffOrganDepartment);
                        }
                    }
                }
            }
            //部门关联 小组
            else if("departmentTeam".equals(surveyCode)){
                String btnCode = apiReq.getString("btnCode");
                Long companyId = apiReq.getLong("companyId");
                Long organId = apiReq.getLong("organId");
                Long departmentId = apiReq.getLong("departmentId");
                StaffCompany staffCompany = staffCompanyMapper.selectByPrimaryKey(companyId);
                StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(organId);
                StaffDepartment staffDepartment = staffDepartmentMapper.selectByPrimaryKey(departmentId);
                if("1000".equals(btnCode)){
                    //先删除记录，再保存全新数据
                    Map<String,Object> map = new HashMap<>();
                    map.put("companyId",companyId);
                    map.put("organId",organId);
                    map.put("departmentId",departmentId);
                    staffDepartmentTeamMapper.deleteByInfo(map);
                    //保存数据
                    String buss = apiReq.getString("roleIds");
                    if(buss!=null) {
                        String[] role = buss.split(",");
                        for (String ro : role) {
                            StaffDepartmentTeam staffDepartmentTeam =new StaffDepartmentTeam();
                            //岗位信息
                            StaffTeam staffTeam = staffTeamMapper.selectByPrimaryKey(Long.valueOf(ro));
                            if(staffTeam!=null){
                                staffDepartmentTeam.setTeamId(staffTeam.getId());
                                staffDepartmentTeam.setTeamName(staffTeam.getName());
                            }
                            staffDepartmentTeam.setCompanyId(staffCompany.getId());
                            staffDepartmentTeam.setCompanyName(staffCompany.getName());
                            staffDepartmentTeam.setOrganId(staffOrgan.getId());
                            staffDepartmentTeam.setOrganName(staffOrgan.getName());
                            staffDepartmentTeam.setDepartmentId(staffDepartment.getId());
                            staffDepartmentTeam.setDepartmentName(staffDepartment.getName());
                            staffDepartmentTeamMapper.insert(staffDepartmentTeam);
                        }
                    }
                }
            }
            //小组关联 岗位
            else if("teamJobPost".equals(surveyCode)){
                String btnCode = apiReq.getString("btnCode");
                Long companyId = apiReq.getLong("companyId");
                Long organId = apiReq.getLong("organId");
                Long departmentId = apiReq.getLong("departmentId");
                Long teamId = apiReq.getLong("teamId");
                StaffCompany staffCompany = staffCompanyMapper.selectByPrimaryKey(companyId);
                StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(organId);
                StaffDepartment staffDepartment = staffDepartmentMapper.selectByPrimaryKey(departmentId);
                StaffTeam staffTeam = staffTeamMapper.selectByPrimaryKey(teamId);
                if("1000".equals(btnCode)){
                    //先删除记录，再保存全新数据
                    Map<String,Object> map = new HashMap<>();
                    map.put("companyId",companyId);
                    map.put("organId",organId);
                    map.put("departmentId",departmentId);
                    map.put("teamId",teamId);
                    staffTeamJobPostMapper.deleteByInfo(map);
                    //保存数据
                    String buss = apiReq.getString("roleIds");
                    if(buss!=null) {
                        String[] role = buss.split(",");
                        for (String ro : role) {
                            StaffTeamJobPost staffTeamJobPost =new StaffTeamJobPost();
                            //岗位信息
                            StaffJobPost staffJobPost = staffJobPostMapper.selectByPrimaryKey(Long.valueOf(ro));
                            if(staffJobPost!=null){
                                staffTeamJobPost.setJobPostId(staffJobPost.getId());
                                staffTeamJobPost.setJobPostName(staffJobPost.getName());
                            }
                            staffTeamJobPost.setCompanyId(staffCompany.getId());
                            staffTeamJobPost.setCompanyName(staffCompany.getName());
                            staffTeamJobPost.setOrganId(staffOrgan.getId());
                            staffTeamJobPost.setOrganName(staffOrgan.getName());
                            staffTeamJobPost.setDepartmentId(staffDepartment.getId());
                            staffTeamJobPost.setDepartmentName(staffDepartment.getName());
                            staffTeamJobPost.setTeamId(staffTeam.getId());
                            staffTeamJobPost.setTeamName(staffTeam.getName());
                            staffTeamJobPostMapper.insert(staffTeamJobPost);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    private Boolean isThisTime(Date time, String workTime) {
        if (time == null) return false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String now = sdf.format(time);//当前时间
        if(workTime.equals(now)){
            return true;
        }
        return false;
    }


    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "根据id，查询关联的子表数据", value = "backend-select-staff-info-by-relation-id", apiParams = { })
    @Override
    public ApiResponse selectStaffInfoByRelationId(ApiRequest apiReq) {
        String surveyCode = apiReq.getString("surveyCode");
        String btnCode = apiReq.getString("btnCode");
        Map<String ,Object> map  =  new HashMap<>();
        //不分页
        if("no".equals(apiReq.getString("havePage"))){
            map.put("pageIndex", null);
            map.put("pageSize", null);
        }
        //根据“事业部id”，查询“公司”数据
        if("businessUnit".equals(surveyCode)){
            if("1000".equals(btnCode)){
                map.put("businessUnitId",apiReq.getString("businessUnitId"));
                List<StaffBusinessUnitCompany> businessUnitCompanys = staffBusinessUnitCompanyMapper.list(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,businessUnitCompanys);
            }
            //根据name 查询
            else if("2000".equals(btnCode)){
                String name = apiReq.getString("name");
                Long businessUnitId = apiReq.getLong("businessUnitId");
                StaffBusinessUnit staffBusinessUnit = staffBusinessUnitMapper.selectByOne(name);
                //不为空，并且不是当前数据
                if(staffBusinessUnit !=null){
                    if(businessUnitId !=null && businessUnitId.equals(staffBusinessUnit.getId())){
                        return new ApiResponse(ApiMsgEnum.SUCCESS,null,null);
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffBusinessUnit);
            }
        }
        //根据“公司id”，查询“机构”数据
        else if("company".equals(surveyCode)){
            if("1000".equals(btnCode)){
                map.put("companyId",apiReq.getString("companyId"));
                List<StaffCompanyOrgan> companyOrgans = staffCompanyOrganMapper.list(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,companyOrgans);
            }
            //根据name 查询
            else if("2000".equals(btnCode)){
                String name = apiReq.getString("name");
                Long companyId = apiReq.getLong("companyId");
                StaffCompany staffCompany = staffCompanyMapper.selectByOne(name);
                //不为空，并且不是当前数据
                if(staffCompany !=null){
                    if(companyId !=null && companyId.equals(staffCompany.getId()) ){
                        return new ApiResponse(ApiMsgEnum.SUCCESS,null,null);
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffCompany);
            }
        }
        //根据“预算归属公司id”，查询“机构”数据
        else if("staffBudgetCompany".equals(surveyCode)){
            if("1000".equals(btnCode)){
                map.put("companyId",apiReq.getString("budgetCompanyId"));
                List<StaffBudgetCompanyOrgan> staffBudgetCompanyOrgans = staffBudgetCompanyOrganMapper.list(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffBudgetCompanyOrgans);
            }
            //根据name 查询
           /* else if("2000".equals(btnCode)){
                String name = apiReq.getString("name");
                Long companyId = apiReq.getLong("companyId");
                StaffCompany staffCompany = staffCompanyMapper.selectByOne(name);
                //不为空，并且不是当前数据
                if(staffCompany !=null){
                    if(companyId !=null && companyId.equals(staffCompany.getId()) ){
                        return new ApiResponse(ApiMsgEnum.SUCCESS,null,null);
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffCompany);
            }*/
        }
        //根据“机构id”，查询“部门”数据
        else if("organ".equals(surveyCode)){
            if("1000".equals(btnCode)){
                map.put("companyId",apiReq.getString("companyId"));
                map.put("organId",apiReq.getString("organId"));
                List<StaffOrganDepartment> staffOrganDepartments = staffOrganDepartmentMapper.list(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffOrganDepartments);
            }
            //根据name 查询
            else if("2000".equals(btnCode)){
                String name = apiReq.getString("name");
                Long organId = apiReq.getLong("organId");
                StaffOrgan staffOrgan = staffOrganMapper.selectByOne(name);
                //不为空，并且不是当前数据
                if(staffOrgan !=null){
                    if(organId != null && organId.equals(staffOrgan.getId())){
                        return new ApiResponse(ApiMsgEnum.SUCCESS,null,null);
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffOrgan);
            }
        }
        //根据“部门id”，查询“岗位”数据
        else if("department".equals(surveyCode)){
            if("1000".equals(btnCode)){
                map.put("companyId",apiReq.getString("companyId"));
                map.put("organId",apiReq.getString("organId"));
                map.put("departmentId",apiReq.getString("departmentId"));
                List<StaffDepartmentTeam> staffDepartmentTeams = staffDepartmentTeamMapper.list(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffDepartmentTeams);
            }
            //根据name 查询
            else if("2000".equals(btnCode)){
                String name = apiReq.getString("name");
                Long departmentId = apiReq.getLong("departmentId");
                StaffDepartment staffDepartment = staffDepartmentMapper.selectByOne(name);
                //不为空，并且不是当前数据
                if(staffDepartment !=null){
                    if(departmentId != null && departmentId.equals(staffDepartment.getId())){
                        return new ApiResponse(ApiMsgEnum.SUCCESS,null,null);
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffDepartment);
            }
        }
        //根据“小组id”，查询“岗位”数据
        else if("team".equals(surveyCode)){
            if("1000".equals(btnCode)){
                map.put("companyId",apiReq.getString("companyId"));
                map.put("organId",apiReq.getString("organId"));
                map.put("teamId",apiReq.getString("teamId"));
                map.put("departmentId",apiReq.getString("departmentId"));
                List<StaffTeamJobPost> staffTeamJobPosts = staffTeamJobPostMapper.list(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffTeamJobPosts);
            }
            //根据name 查询
            else if("2000".equals(btnCode)){
                String name = apiReq.getString("name");
                Long teamId = apiReq.getLong("teamId");
                StaffTeam staffTeam = staffTeamMapper.selectByOne(name);
                //不为空，并且不是当前数据
                if(staffTeam !=null){
                    if(teamId != null && teamId.equals(staffTeam.getId())){
                        return new ApiResponse(ApiMsgEnum.SUCCESS,null,null);
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffTeam);
            }
        }
        //根据“岗位id”，查询“人员”数据
        else if("jobPost".equals(surveyCode)){
            if("1000".equals(btnCode)){
                map.put("companyId",apiReq.getString("companyId"));
                map.put("organId",apiReq.getString("organId"));
                map.put("departmentId",apiReq.getString("departmentId"));
                map.put("teamId",apiReq.getString("teamId"));
                map.put("jobPostId",apiReq.getString("jobPostId"));
                List<StaffPersonnelInfo> staffPersonnelInfos = staffPersonnelInfoMapper.list(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPersonnelInfos);
            }
            //根据name 查询
            else if("2000".equals(btnCode)){
                String name = apiReq.getString("name");
                Long jobPostId = apiReq.getLong("jobPostId");
                StaffJobPost staffJobPost = staffJobPostMapper.selectByOne(name);
                //不为空，并且不是当前数据
                if(staffJobPost !=null){
                    if(jobPostId != null && jobPostId.equals(staffJobPost.getId())){
                        return new ApiResponse(ApiMsgEnum.SUCCESS,null,null);
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffJobPost);
            }
        }
        //根据“手机号”、“工号”、“身份证”，查询“人员”数据
        else if("personnelInfo".equals(surveyCode)){
            if("1001".equals(btnCode)){//通过userTel查询
                String info = apiReq.getString("info");
                map.put("userTel",info);
                StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectByInfo(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPersonnelInfo);
            }
            else if("1002".equals(btnCode)){//通过jobNo查询
                String info = apiReq.getString("info");
                map.put("jobNo",info);
                StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectByInfo(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPersonnelInfo);
            }
            else if("1003".equals(btnCode)){//通过idCard查询
                String info = apiReq.getString("info");
                map.put("idCard",info);
                StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectByInfo(map);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPersonnelInfo);
            }
        }
        //根据“月份”，查询“工资条”数据
        else if("paySlip".equals(surveyCode)){
            //workTime 查询
            if("2000".equals(btnCode)){
                String workTime = apiReq.getString("workTime");
                Long paySlipId = apiReq.getLong("paySlipId");
                StaffPaySlip staffPaySlip = staffPaySlipMapper.selectByWorkTime(workTime);
                //不为空，并且不是当前数据
                if(staffPaySlip !=null){
                    if(staffPaySlip != null && paySlipId.equals(staffPaySlip.getId())){
                        return new ApiResponse(ApiMsgEnum.SUCCESS,null,null);
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPaySlip);
            }
        }
        //根据“月份”，查询“工资条”数据
        else if("performance".equals(surveyCode)){
            //根据name 查询
            if("2000".equals(btnCode)){
                String workTime = apiReq.getString("workTime");
                Long performanceId = apiReq.getLong("performanceId");
                StaffPerformance staffPerformance = staffPerformanceMapper.selectByWorkTime(workTime);
                //不为空，并且不是当前数据
                if(staffPerformance !=null){
                    if(performanceId != null && performanceId.equals(staffPerformance.getId())){
                        return new ApiResponse(ApiMsgEnum.SUCCESS,null,null);
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPerformance);
            }
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "人事管理操作", value = "backend-staff-operate", apiParams = { })
    @Override
    public ApiResponse operate(ApiRequest apiReq) {
        String operateCode = apiReq.getString("operateCode"); // 菜单标识
        String btnCode = apiReq.getString("btnCode"); //具体操作标识

        Long userId = apiReq.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);

        //事业部
        if ("businessUnit".equals(operateCode)) {
            StaffBusinessUnit staffBusinessUnit = staffBusinessUnitMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if ("9999".equals(btnCode)) {
                staffBusinessUnit.setDeleteFlag(1);
                staffBusinessUnitMapper.updateByPrimaryKey(staffBusinessUnit);
                //同时删除关联关系
                Map<String,Object> map = new HashMap<>();
                map.put("businessUnitId",staffBusinessUnit.getId());
                staffBusinessUnitCompanyMapper.deleteByInfo(map);
            }
        }
        //公司
        else if("company".equals(operateCode)){
            StaffCompany staffCompany = staffCompanyMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if ("9999".equals(btnCode)) {
                staffCompany.setDeleteFlag(1);
                staffCompanyMapper.updateByPrimaryKey(staffCompany);

                //同时删除关联关系
                Map<String,Object> map = new HashMap<>();
                map.put("companyId",staffCompany.getId());
                staffBusinessUnitCompanyMapper.deleteByInfo(map);//向上关联删除
                staffCompanyOrganMapper.deleteByInfo(map);//想下关联删除
            }
        }
        //机构
        else if("organ".equals(operateCode)){
            StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if ("9999".equals(btnCode)) {
                staffOrgan.setDeleteFlag(1);
                staffOrganMapper.updateByPrimaryKey(staffOrgan);

                //同时删除关联关系
                Map<String,Object> map = new HashMap<>();
                map.put("organId",staffOrgan.getId());
                staffCompanyOrganMapper.deleteByInfo(map);//向上关联删除
                staffOrganDepartmentMapper.deleteByInfo(map);//向下删除
            }
        }
        //部门
        else if("department".equals(operateCode)){
            StaffDepartment staffDepartment = staffDepartmentMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if ("9999".equals(btnCode)) {
                staffDepartment.setDeleteFlag(1);
                staffDepartmentMapper.updateByPrimaryKey(staffDepartment);

                //同时删除关联关系
                Map<String,Object> map = new HashMap<>();
                map.put("departmentId",staffDepartment.getId());
                staffOrganDepartmentMapper.deleteByInfo(map);//向上关联删除
                staffDepartmentTeamMapper.deleteByInfo(map);//向下删除
//                staffDepartmentJobPostMapper.deleteByInfo(map);//向下删除
            }
        }
        //部门
        else if("team".equals(operateCode)){
            StaffTeam staffTeam = staffTeamMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if ("9999".equals(btnCode)) {
                staffTeam.setDeleteFlag(1);
                staffTeamMapper.updateByPrimaryKey(staffTeam);

                //同时删除关联关系
                Map<String,Object> map = new HashMap<>();
                map.put("teamId",staffTeam.getId());
                staffDepartmentTeamMapper.deleteByInfo(map);//向上关联删除
                staffTeamJobPostMapper.deleteByInfo(map);//向下删除
            }
        }
        //岗位
        else if("jobPost".equals(operateCode)){
            StaffJobPost staffJobPost = staffJobPostMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if ("9999".equals(btnCode)) {
                staffJobPost.setDeleteFlag(1);
                staffJobPostMapper.updateByPrimaryKey(staffJobPost);

                //同时删除关联关系
                Map<String,Object> map = new HashMap<>();
                map.put("jobPostId",staffJobPost.getId());
                staffTeamJobPostMapper.deleteByInfo(map);//向上删除
            }
        }
        //员工管理
        else if("personnelInfo".equals(operateCode)){
            StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
            if ("9999".equals(btnCode)) {
                staffPersonnelInfo.setDeleteFlag(1);
                staffPersonnelInfoMapper.updateByPrimaryKey(staffPersonnelInfo);
            }
        }
        //调查员，是否有绩效需确认
        else if("performanceForSurvey".equals(operateCode)){
            Map map = new HashMap<>();
            map.put("performanceState",6);
            List<StaffPerformance> staffPerformances = staffPerformanceMapper.allList(map);//绩效list
            StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(userId);//人员
            //条件：必须是调查员，同时不是“机构经理”，“分管总”，“总经理”等角色
            map = new HashMap<>();
            map.put("userId",userId);
            map.put("roleId",50);
            BusUserRole busUserRole = busUserRoleMapper.selectBusRoleInfo(map);

            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(userId);
            Boolean hrRole = false,superiorManager = false,ceoRole = false,orgManagerRole=false, hrManageRole = false;
            hrRole = isRoleUser(userRoles,100L);//人事专员
            orgManagerRole = isRoleUser(userRoles,108L);//机构经理
            superiorManager = isRoleUser(userRoles,109L);//分管总
            hrManageRole = isRoleUser(userRoles,107L);//人事主管
            ceoRole = isRoleUser(userRoles,103L);//总经理

            if(staffPersonnelInfo ==null || busUserRole == null || hrRole || orgManagerRole || superiorManager || hrManageRole || ceoRole){
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
            if(staffPerformances.size() > 0){
                map = new HashMap<>();
                StaffPerformance staffPerformance = staffPerformances.get(0);
                map.put("staffPerformanceId",staffPerformance.getId());
                map.put("staffPersonnelId",staffPersonnelInfo.getId());
                List<StaffPerformancePersonnel> personnels = staffPerformancePersonnelMapper.list(map);//绩效明细
                if(personnels.size() > 0){
                    StaffPerformancePersonnel personnel = personnels.get(0);

                    if(personnel!=null && personnel.getOrganOpinion() ==null){
                        //剩余时间(1天后)
                        String timeRemaining = backendStaffPerformanceApiImpl.getDistanceTime(staffPerformance.getSuperiorManagerFirstTime(), 1);
                        staffPerformance.setTimeRemaining(timeRemaining);

                        return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPerformance);
                    }
                }
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    public static void main(String[] args) throws ParseException {
        int absentDays = (int) Math.floor(Double.parseDouble("1.6"));//向下取整

        System.out.println("Date结束日期 " + absentDays);
    }


    //更新员工管理时 -- 日志记录
    public String updateLog(StaffPersonnelInfo obj_old,StaffPersonnelInfo obj_new, String updateType, UserInfo userInfo) throws Exception {

        Field[] fields = obj_old.getClass().getDeclaredFields();
        String remark = "";
        for (Field field : fields) {
            field.setAccessible(true); //设置些属性是可以访问的 
            String name = field.getName();
            String type = field.getType().getName();
            Object val_old = field.get(obj_old);//得到此属性的修改前值 
            Object val_new = field.get(obj_new);//得到此属性的修改后值 
            //bigdecimal 类型的数据要去掉小数点后尾部的0不一致造成数据比对差异
            if(type.equals("java.math.BigDecimal") && val_old!=null && val_new!=null ){
                BigDecimal val_old_big = new BigDecimal(String.valueOf(val_old));
                BigDecimal val_new_big = new BigDecimal(String.valueOf(val_new));
                if(String.valueOf(val_old_big).indexOf(".")!= -1 || String.valueOf(val_new_big).indexOf(".")!= -1 ){//由于无法获取精度值，只能对所有带小数点的数据进行处理
                    DecimalFormat formatter1=new DecimalFormat("0.00");
                    val_old = formatter1.format(val_old_big);
                    val_new = formatter1.format(val_new_big);
                }
            }
            //DATE 类型的数据要格式化
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(type.equals("java.util.Date")){
                if (val_old != null) {
                    val_old = sdf.format(val_old);
                }
                if (val_new != null) {
                    val_new = sdf.format(val_new);
                }
            }

            if(name.equals("relation") || name.equals("staffState")){
                DecimalFormat formatter=new DecimalFormat("0");
                Integer val_old_int = Integer.valueOf(String.valueOf(val_old));
                val_old = formatter.format(val_old_int);
                Integer val_new_int = Integer.valueOf(String.valueOf(val_new));
                val_new = formatter.format(val_new_int);

                if(name.equals("relation")){
                    if("1".equals(val_old)){val_old = "全职";
                    }else if("2".equals(val_old)){val_old = "兼职";
                    }else if("3".equals(val_old)){val_old = "退休返聘";
                    }else if("4".equals(val_old)){val_old = "实习生";
                    }else if("5".equals(val_old)){val_old = "合伙";
                    }else if("6".equals(val_old)){val_old = "合伙+兼职";
                    }else if("7".equals(val_old)){val_old = "合伙(发固定绩效)";}

                    if("1".equals(val_new)){val_new = "全职";
                    }else if("2".equals(val_new)){val_new = "兼职";
                    }else if("3".equals(val_new)){val_new = "退休返聘";
                    }else if("4".equals(val_new)){val_new = "实习生";
                    }else if("5".equals(val_new)){val_new = "合伙";
                    }else if("6".equals(val_new)){val_new = "合伙+兼职";
                    }else if("7".equals(val_new)){val_new = "合伙(发固定绩效)";}
                }

                if(name.equals("staffState")){
                    if("1".equals(val_old)){val_old = "试用期员工";
                    }else if("2".equals(val_old)){val_old = "调整人员";
                    }else if("3".equals(val_old)){val_old = "离职待结算";
                    }else if("4".equals(val_old)){val_old = "转正人员";
                    }else if("5".equals(val_old)){val_old = "在职";
                    }else if("6".equals(val_old)){val_old = "已离职";}

                    if("1".equals(val_new)){val_new = "试用期员工";
                    }else if("2".equals(val_new)){val_new = "调整人员";
                    }else if("3".equals(val_new)){val_new = "离职待结算";
                    }else if("4".equals(val_new)){val_new = "转正人员";
                    }else if("5".equals(val_new)){val_new = "在职";
                    }else if("6".equals(val_new)){val_new = "已离职";}
                }

            }
            if ("".equals(val_old) && "0".equals(val_new)){
                continue;
            }

            if(!String.valueOf(val_old).equals(String.valueOf(val_new))){
                //保存处理数据 
                //1、获取属性上的指定类型的注解 
                Annotation annotation = field.getAnnotation(XmlElement.class);
                //有该类型的注解存在 
                if (annotation!=null) {
                    XmlElement xmlElement = (XmlElement)annotation;

                    String str_val_old = "",str_val_new = "";
                    if (!"null".equals(String.valueOf(val_old))) {
                        str_val_old = String.valueOf(val_old);
                    }
                    if (!"null".equals(String.valueOf(val_new))) {
                        str_val_new = String.valueOf(val_new);
                    }
                    //更新数据
                    StaffPersonnelInfoLog log = new StaffPersonnelInfoLog();
                    log.setStaffPersonnelInfoId(obj_old.getId());
                    log.setFieldName(xmlElement.name());
                    log.setOldName(str_val_old);
                    log.setNewName(str_val_new);
                    log.setUpdateType(updateType);
                    log.setCreateBy(userInfo.getUserId());
                    log.setCreateByName(userInfo.getUserName());
                    log.setCreateTime(new Date());
                    staffPersonnelInfoLogMapper.insert(log);
                }
            }
        }
        return remark;
    }
}
