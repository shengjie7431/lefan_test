package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendStaffPerformanceApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.SurveyAssignOrgDto;
import com.lefancrm.apicenter.dto.hzReport.ScoreDto;
import com.lefancrm.apicenter.dto.staff.StaffPerformanceInfoDTO;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DecimalUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@ApiService(descript = "绩效API")
public class BackendStaffPerformanceApiImpl extends BaseServiceImpl implements BackendStaffPerformanceApi {
    @Autowired
    private StaffPerformanceMapper staffPerformanceMapper;
    @Autowired
    private StaffPerformancePersonnelMapper staffPerformancePersonnelMapper;
    @Autowired
    private StaffPersonnelInfoMapper staffPersonnelInfoMapper;
    @Autowired
    private StaffWorkingDaysInfoMapper staffWorkingDaysInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private StaffPaySlipMapper staffPaySlipMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;

    @Value("${survey.role.one}")
    private Integer one;
    @Value("${survey.role.two}")
    private Integer two;
    @Value("${survey.role.three}")
    private Integer three;
    @Value("${staff.users.not.score}")
    private String persons;
    @Autowired
    private StaffPerformanceManagerMapper staffPerformanceManagerMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private SurveyInvestigatorCaseMapper surveyInvestigatorCaseMapper;
    @Autowired
    private BackendWechatApiImpl backendWechatApi;
    @Autowired
    private SurveyAssignOrgMapper surveyAssignOrgMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;
    @Value("${staff.users.not.examine}")
    private String examinePersons; //跳过该阶段的审核人员
    @Value("${staff.users.not.send.wechat}")
    private String sendWechatPersons; //不发送微信通知的审核人员
    @Value("${staff.users.zhangyongzhi}")
    private String zhangYongZhi; //安徽张永志，同时可以看到工资条详情
    @Value("${staff.users.zhengyadong}")
    private String zhengYaDong;//安徽郑亚东
    @Autowired
    private SurveyInvestigatorCaseSubMapper surveyInvestigatorCaseSubMapper;
    @Autowired
    private StaffAuthOrgMapper staffAuthOrgMapper;


    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
    @Autowired
    private StaffPayPersonnelSlipMapper staffPayPersonnelSlipMapper;

    @ApiMethod(descript = "绩效明细",value = "staff-performance-info")
    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        Long currentUserId = getCurrentUserId(apiRequest);
        StaffPerformance staffPerformance = staffPerformanceMapper.selectByPrimaryKey(id);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        if (staffPerformance != null) {
            //查询绩效明细是否存在
            Map<String,Object> map = new HashMap<>();
            map.put("workTime",staffPerformance.getWorkTime());
            map.put("staffPerformanceId",staffPerformance.getId());
            //查询条件
            map.put("companyId",apiRequest.getLong("companyId"));
            map.put("organId",apiRequest.getLong("organId"));
            map.put("departmentId",apiRequest.getLong("departmentId"));
            map.put("jobPostId",apiRequest.getLong("jobPostId"));
            map.put("jobNo",apiRequest.getString("jobNo"));
            map.put("realName",apiRequest.getString("realName"));
            map.put("companyIds",apiRequest.getString("companyIds"));
            map.put("organIds",apiRequest.getString("organIds"));
            map.put("departmentIds",apiRequest.getString("departmentIds"));
            map.put("teamIds",apiRequest.getString("teamIds"));
            map.put("jobPostIds",apiRequest.getString("jobPostIds"));
            map.put("socialSecurityCompanyIds",apiRequest.getString("socialSecurityCompanyIds"));
            map.put("socialSecurityCompanyIds",apiRequest.getString("socialSecurityCompanyIds"));
            map.put("entryTime",apiRequest.getString("entryTime"));
            map.put("entryTimeEnd",apiRequest.getString("entryTimeEnd"));

            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean hrRole = false,ceoRole = false, hrManageRole = false, organManagerRole = false,
                    superiorManagerRole= false, surveyUserRole =false,financeRole= false;
            hrRole = isRoleUser(userRoles,100L);
            ceoRole = isRoleUser(userRoles,103L);
            hrManageRole = isRoleUser(userRoles,107L);
            organManagerRole = isRoleUser(userRoles,108L);
            superiorManagerRole = isRoleUser(userRoles,109L);
            surveyUserRole = isRoleUser(userRoles,50L);
            financeRole = isRoleUser(userRoles,23L);

            String roleCode = apiRequest.getString("roleCode");

            if(organManagerRole){
                map.put("infoState",2);
                map.put("organManagerUserId",userInfo.getUserId());
            }
            if(superiorManagerRole){
                map.put("infoState",2);
                map.put("superiorManagerUserId",userInfo.getUserId());
            }

            if(organManagerRole && superiorManagerRole){
                if("hrManage-step".equals(roleCode)|| "ceo-step".equals(roleCode) || "end-step".equals(roleCode)){
                    map.put("infoState",3);
                }else if("organManager-step".equals(roleCode)){
                    map.put("infoState",2);
                    map.put("superiorManagerUserId",null);
                }else if("superiorManager-step".equals(roleCode)){
                    map.put("infoState",2);
                    map.put("organManagerUserId",null);
                }else if("superiorManager-first-step".equals(roleCode)){
                    map.put("infoState",2);
                    map.put("organManagerUserId",null);
                }
            }
            if(hrRole){
                map.put("infoState",null); //查询全部
            }
            //人事主管、总经理，可查询退回的案件
            if(hrManageRole){
                map.put("infoState",null); //查询全部
//                if("hrManage-step".equals(roleCode)){
//                    map.put("infoState",1);
//                }
//                if(organManagerRole && "organManager-step".equals(roleCode)){
//                    map.put("infoState",2);
//                }
//                if(superiorManagerRole && "superiorManager-step".equals(roleCode)){
//                    map.put("infoState",2);
//                }
//                if(superiorManagerRole && "superiorManager-first-step".equals(roleCode)){
//                    map.put("infoState",2);
//                }
            }
            if(ceoRole){
                map.put("infoState",null); //查询全部
                if("ceo-step".equals(roleCode)){
                    map.put("infoState",1);
                }
                if(organManagerRole && "organManager-step".equals(roleCode)){
                    map.put("infoState",2);
                }
                if(superiorManagerRole && "superiorManager-step".equals(roleCode)){
                    map.put("infoState",2);
                }
                if(superiorManagerRole && "superiorManager-first-step".equals(roleCode)){
                    map.put("infoState",2);
                }
            }
            if(financeRole){
                if("end-step".equals(roleCode)){
                    map.put("infoState",null); //查询全部
                }
            }
            /*if(surveyUserRole && "surveyUser-step".equals(roleCode)){
                map.put("userId",userInfo.getUserId());
                map.put("infoState",null);
                map.put("organManagerUserId",null);
                map.put("superiorManagerUserId",null);
            }*/

            if(surveyUserRole){
                if(!(hrRole || ceoRole || hrManageRole || organManagerRole || superiorManagerRole || financeRole) || "surveyUser-step".equals(roleCode)){
                    map.put("userId",userInfo.getUserId());
                    map.put("infoState",null);
                    map.put("organManagerUserId",null);
                    map.put("superiorManagerUserId",null);
                }
            }

            //特殊处理安徽机构，郑亚东（机构经理）和张永志，都可以看到数据
            if (!StringUtils.isEmpty(zhangYongZhi) && zhangYongZhi.equals(currentUserId.toString())) {
                map.put("organManagerUserId",zhengYaDong);
            }

            List<StaffPerformanceInfoDTO> data = new ArrayList<>();

            //2020年11月5日15:17:43 ：不再实时获取 积分等数据
            if("end-step".equals(roleCode)){
                map.put("state","end");
                map.put("performanceState",2);
            }else{
                map.put("state","ongoing");
                map.put("performanceState",1);
            }

            //特殊队员需要看到多机构数据。
            List<StaffAuthOrg> staffAuthOrgs = staffAuthOrgMapper.selectByUserId(currentUserId);
            String authData = staffAuthOrgs.stream().map(p -> p.getOrgId().toString()).collect(Collectors.joining(","));
            if (!StringUtils.isEmpty(authData)){//如果存在特殊人员的处理
                map.remove("organManagerUserId");
                map.remove("superiorManagerUserId");
                map.remove("infoState");
                map.put("authData","".equals(authData) || authData == null ? null : authData);
            }

            data = staffPerformancePersonnelMapper.selectPerformances(map);
            staffPerformance.setData(data);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPerformance);
    }

    @ApiMethod(descript = "绩效明细",value = "staff-performance-info-key")
    @Override
    public ApiResponse infoKey(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        Long currentUserId = getCurrentUserId(apiRequest);
        StaffPerformance staffPerformance = staffPerformanceMapper.selectByPrimaryKey(id);
        int state = staffPerformance.getPerformanceState().intValue();
        if ( state == 0){
            staffPerformance.setRoleCode("hr-step");//人事审核
        }else if (state == 1){
            staffPerformance.setRoleCode("organManager-step");//机构经理处理
            //获取当前登录的 机构经理，审核状态
            Map map = new HashMap<>();
            map.put("staffPerformanceId",id);
            map.put("managerType",1);
            map.put("organManagerUserId",currentUserId);
            //特殊处理安徽机构，郑亚东（机构经理）和张永志，都可以看到数据
            if (!StringUtils.isEmpty(zhangYongZhi) && zhangYongZhi.equals(currentUserId.toString())) {
                map.put("organManagerUserId",zhengYaDong);
            }
            StaffPerformanceManager staffPaySlipManager = staffPerformanceManagerMapper.selectByOne(map);
            staffPerformance.setStaffPerformanceManager(staffPaySlipManager);

            //剩余时间(3天后)
            String timeRemaining = getDistanceTime(staffPerformance.getSuperiorManagerFirstTime(), 3);
            staffPerformance.setTimeRemaining(timeRemaining);
        }else if (state == 2){
            staffPerformance.setRoleCode("superiorManager-step");//待分管总二审
            //获取当前登录的 分管总二审，审核状态
            Map map = new HashMap<>();
            map.put("staffPerformanceId",id);
            map.put("managerType",2);//
            map.put("organManagerUserId",currentUserId);
            StaffPerformanceManager staffPaySlipManager = staffPerformanceManagerMapper.selectByOne(map);
            staffPerformance.setStaffPerformanceManager(staffPaySlipManager);
        }else if (state == 3){
            staffPerformance.setRoleCode("hrManage-step");//人事主管
        }else if(state == 4){
            staffPerformance.setRoleCode("ceo-step");//总部审核
        }else if(state == 6){
            staffPerformance.setRoleCode("surveyUser-step");//调查员确认
        }else if(state == 7){
            staffPerformance.setRoleCode("superiorManager-first-step");//待分管总一审
            //获取当前登录的 分管总一审，审核状态
            Map map = new HashMap<>();
            map.put("staffPerformanceId",id);
            map.put("managerType",3);
            map.put("organManagerUserId",currentUserId);
            StaffPerformanceManager staffPaySlipManager = staffPerformanceManagerMapper.selectByOne(map);
            staffPerformance.setStaffPerformanceManager(staffPaySlipManager);
        }else if (state == 5){
            staffPerformance.setRoleCode("end-step");//完成
        }

        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean hrRole = false,ceoRole = false, hrManageRole = false, organManagerRole = false, superiorManagerRole= false, surveyUserRole =false,financeRole =false;
        hrRole = isRoleUser(userRoles,100L);
        staffPerformance.setHrRole(hrRole);
        ceoRole = isRoleUser(userRoles,103L);
        staffPerformance.setCeoRole(ceoRole);
        hrManageRole = isRoleUser(userRoles,107L);
        staffPerformance.setHrManageRole(hrManageRole);
        organManagerRole = isRoleUser(userRoles,108L);
        staffPerformance.setOrganManagerRole(organManagerRole);
        superiorManagerRole = isRoleUser(userRoles,109L);
        staffPerformance.setSuperiorManagerRole(superiorManagerRole);
        surveyUserRole = isRoleUser(userRoles,50L);
        staffPerformance.setSurveyUserRole(surveyUserRole);
        financeRole = isRoleUser(userRoles,101L);//财务（工资条完成状态，财务角色可以查看、导出）
        staffPerformance.setFinanceRole(financeRole);

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPerformance);
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId() == roleId){
                return true;
            }
        }
        return false;
    }

    @ApiMethod(descript = "绩效操作",value = "staff-performance-operate")
    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        String btnCode = apiRequest.getString("btnCode");
        Long id = apiRequest.getLong("id");
        String stepType = apiRequest.getString("stepType");
        String reason = apiRequest.getString("reason");
        StaffPerformance staffPerformance = staffPerformanceMapper.selectByPrimaryKey(id);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(apiRequest));
        if (staffPerformance == null){
            staffPerformance = new StaffPerformance();
        }

        if ("hr-step".equals(btnCode)){//人事发起审批 待调查员审核
            staffPerformance.setPerformanceState(7);
            staffPerformance.setPerformanceStateName("待分管总一审");
            staffPerformance.setQueryPassword(apiRequest.getString("password"));//密码
            staffPerformance.setHrId(userInfo.getUserId());
            staffPerformance.setHrName(userInfo.getUserName());
            staffPerformance.setHrTime(new Date());
            staffPerformance.setReason(null);
            staffPerformanceMapper.updateByPrimaryKey(staffPerformance);

            //微信通知：发于分管总一审
             Map map = new HashMap<>();
            map.put("staffPerformanceId",staffPerformance.getId());
            map.put("managerType",3);
            List<StaffPerformanceManager> list = staffPerformanceManagerMapper.list(map);
            Map<String, Object> msgMap = new HashMap<String, Object>();
            for (StaffPerformanceManager staffPerformanceManager : list) {
                //部分人员不予审核，直接跳过
                Boolean sendWechat  = true;
                if (!StringUtils.isEmpty(examinePersons)) {
                    String [] personIds = examinePersons.split(",");
                    for (String personId : personIds) {
                        if (!StringUtils.isEmpty(personId)){
                            if (staffPerformanceManager.getOrganManagerUserId().intValue() == Integer.parseInt(personId)){
                                staffPerformanceManager.setState(1);
                                staffPerformanceManagerMapper.updateByPrimaryKey(staffPerformanceManager);
                                sendWechat = false;
                                break;
                            }
                        }
                    }
                }

                if(sendWechat){
                    msgMap = new HashMap<String, Object>();
                    msgMap.put("title", "绩效审核");
                    msgMap.put("content", "你有一笔绩效待审核，请尽快处理！");
                    msgMap.put("keyWords", "清单名称：" + staffPerformance.getWorkTime() + "绩效");
                    backendWechatApi.send(staffPerformanceManager.getOrganManagerUserId(), msgMap);
                }
            }

        }else if("superiorManager-first-step".equals(btnCode)){//分管总一审，待机构经理审核
            //先更新自己的审核状态
            Map map = new HashMap<>();
            map.put("staffPerformanceId",staffPerformance.getId());
            map.put("organManagerUserId",userInfo.getUserId());
            map.put("managerType",3);
            List<StaffPerformanceManager> list = staffPerformanceManagerMapper.list(map);
            for (StaffPerformanceManager performanceManager : list) {
                performanceManager.setState(1);
                staffPerformanceManagerMapper.updateByPrimaryKey(performanceManager);
            }
            //再查询其余的“分管总状态”，如果自己试最后一个审核通过的人员，则更新整个工资条状态
            map = new HashMap<>();
            map.put("staffPerformanceId",staffPerformance.getId());
            map.put("managerType",3);
            map.put("state",0);
            list = staffPerformanceManagerMapper.list(map);
            if(list.size() == 0){
                /*原需求：流程中需经过“调查员确认”，现取消，并同时取消“定时器”中的
                staffPerformance.setPerformanceState(1);
                staffPerformance.setSuperiorManagerFirstTime(new Date());
                staffPerformance.setPerformanceStateName("待调查员确认");
                staffPerformanceMapper.updateByPrimaryKey(staffPerformance);
                List<StaffPerformancePersonnel> staffPerformancePersonnels = staffPerformancePersonnelMapper.selectSurveyInvestigator(staffPerformance.getId());
                for (StaffPerformancePersonnel staffPerformancePersonnel : staffPerformancePersonnels) {
                    Map msgMap = new HashMap<String, Object>();
                    msgMap.put("title", "绩效核对");
                    msgMap.put("content", "你有一笔绩效待核对，请于24小时内至狄大人saas系统处理！");
                    msgMap.put("keyWords", "清单名称：" + staffPerformance.getWorkTime() + "绩效");
                    backendWechatApi.send(staffPerformancePersonnel.getUserId(), msgMap);
                }*/

                //更新工资条主表的状态
                staffPerformance.setPerformanceState(1);//
                staffPerformance.setPerformanceStateName("待机构经理处理");
                staffPerformance.setSuperiorManagerFirstTime(new Date());
                staffPerformanceMapper.updateByPrimaryKey(staffPerformance);

                //微信通知：发于机构经理
                map = new HashMap<>();
                map.put("staffPerformanceId",staffPerformance.getId());
                map.put("managerType",1);
                List<StaffPerformanceManager> managerList = staffPerformanceManagerMapper.list(map);

                Map<String, Object> msgMap = new HashMap<String, Object>();
                for (StaffPerformanceManager staffPerformanceManager : managerList) {
                    //部分人员不予审核，直接跳过
                    Boolean sendWechat  = true;
                    if (!StringUtils.isEmpty(examinePersons)) {
                        String [] personIds = examinePersons.split(",");
                        for (String personId : personIds) {
                            if (!StringUtils.isEmpty(personId)){
                                if (staffPerformanceManager.getOrganManagerUserId().intValue() == Integer.parseInt(personId)){
                                    staffPerformanceManager.setState(1);
                                    staffPerformanceManagerMapper.updateByPrimaryKey(staffPerformanceManager);
                                    sendWechat = false;
                                    break;
                                }
                            }
                        }
                    }

                    if(sendWechat){
                        map = new HashMap<>();
                        map.put("organManagerStaffId",staffPerformanceManager.getOrganManagerStaffId());
                        int myCount = staffPersonnelInfoMapper.myPersonelInfosSize(map);

                        msgMap = new HashMap<String, Object>();
                        msgMap.put("title", "绩效审核");
                        msgMap.put("content", "你有一笔绩效待审核，请于3天内处理！");
                        msgMap.put("keyWords", "清单名称：" + staffPerformance.getWorkTime() + "绩效" + "\n" + "员工人数：" + myCount + "人\n" + "查询密码：" + staffPerformance.getQueryPassword());
                        backendWechatApi.send(staffPerformanceManager.getOrganManagerUserId(), msgMap);
                    }
                }
            }
        }else if("organManager-step".equals(btnCode)){ //机构经理审核
            //先更新自己的审核状态
            Map map = new HashMap<>();
            map.put("staffPerformanceId",staffPerformance.getId());
            map.put("organManagerUserId",userInfo.getUserId());
            //特殊处理安徽机构，郑亚东（机构经理）和张永志，都可以看到数据
            if (!StringUtils.isEmpty(zhangYongZhi) && zhangYongZhi.equals(userInfo.getUserId().toString())) {
                map.put("organManagerUserId",zhengYaDong);
            }
            map.put("managerType",1);

            List<StaffPerformanceManager> list = staffPerformanceManagerMapper.list(map);
            for (StaffPerformanceManager performanceManager : list) {
                performanceManager.setState(1);
                staffPerformanceManagerMapper.updateByPrimaryKey(performanceManager);
            }

            //再查询其余的“机构经理状态”，如果自己试最后一个审核通过的人员，则更新整个工资条状态
            map = new HashMap<>();
            map.put("staffPerformanceId",staffPerformance.getId());
            map.put("managerType",1);
            map.put("state",0);
            list = staffPerformanceManagerMapper.list(map);
            if(list.size() == 0){
                staffPerformance.setPerformanceState(2);
                staffPerformance.setPerformanceStateName("待分管总处理");

                //微信通知：发于分管总
                map = new HashMap<>();
                map.put("staffPerformanceId",staffPerformance.getId());
                map.put("managerType",2);
                list = staffPerformanceManagerMapper.list(map);

                Map<String, Object> msgMap = new HashMap<String, Object>();
                for (StaffPerformanceManager staffPerformanceManager : list) {
                    //部分人员不予审核，直接跳过
                    Boolean sendWechat  = true;//此处和上面的不同，因为郑总的账号，在此不能跳过
                    if (!StringUtils.isEmpty(sendWechatPersons)) {
                        String [] personIds = sendWechatPersons.split(",");
                        for (String personId : personIds) {
                            if (!StringUtils.isEmpty(personId)){
                                if (staffPerformanceManager.getOrganManagerUserId().intValue() == Integer.parseInt(personId)){
                                    staffPerformanceManager.setState(1);
                                    staffPerformanceManagerMapper.updateByPrimaryKey(staffPerformanceManager);
                                    sendWechat = false;
                                    break;
                                }
                            }
                        }
                    }
                    if(sendWechat){
                        msgMap = new HashMap<String, Object>();
                        msgMap.put("title", "绩效审核");
                        msgMap.put("content", "你有一笔绩效待审核，请尽快处理！");
                        msgMap.put("keyWords", "清单名称：" + staffPerformance.getWorkTime() + "绩效");
                        backendWechatApi.send(staffPerformanceManager.getOrganManagerUserId(), msgMap);
                    }
                }
            }
            staffPerformanceMapper.updateByPrimaryKey(staffPerformance);
        }else if("superiorManager-step".equals(btnCode)){ //分管总二审
            //先更新自己的审核状态
            Map map = new HashMap<>();
            map.put("staffPerformanceId",staffPerformance.getId());
            map.put("organManagerUserId",userInfo.getUserId());
            map.put("managerType",2);
            List<StaffPerformanceManager> list = staffPerformanceManagerMapper.list(map);
            for (StaffPerformanceManager performanceManager : list) {
                performanceManager.setState(1);
                staffPerformanceManagerMapper.updateByPrimaryKey(performanceManager);
            }

            //再查询其余的“分管总状态”，如果自己试最后一个审核通过的人员，则更新整个工资条状态
            map = new HashMap<>();
            map.put("staffPerformanceId",staffPerformance.getId());
            map.put("managerType",2);
            map.put("state",0);
            list = staffPerformanceManagerMapper.list(map);
            if(list.size() == 0){
                staffPerformance.setPerformanceState(3);
                staffPerformance.setPerformanceStateName("待人事主管处理");

                //发给 人事主管
                map = new HashMap<>();
                map.put("roleId",107L);
                List<BusUserRole> roles = busUserRoleMapper.selectBusInfo(map);

                Map<String, Object> msgMap = new HashMap<String, Object>();
                for (BusUserRole role : roles) {
                    msgMap = new HashMap<String, Object>();
                    msgMap.put("title", "绩效审核");
                    msgMap.put("content", "你有一笔绩效待审核，请尽快处理！");
                    msgMap.put("keyWords", "清单名称：" + staffPerformance.getWorkTime() + "绩效");
                    backendWechatApi.send(role.getUserId(), msgMap);
                }
                //同时清空所有的“调查员案件”--原因
//                surveyInvestigatorCaseSubMapper.deleteAllStaffOpinion();
            }
            staffPerformanceMapper.updateByPrimaryKey(staffPerformance);
        }
        else if ("hrManage-step".equals(btnCode)){//人事主管发起审批
            staffPerformance.setPerformanceState(4);
            staffPerformance.setPerformanceStateName("待总经理处理");
            staffPerformance.setHrManageId(userInfo.getUserId());
            staffPerformance.setHrManageName(userInfo.getUserName());
            staffPerformance.setHrManageTime(new Date());
            staffPerformance.setReason(null);
            staffPerformance.setBackState(0);
            staffPerformanceMapper.updateByPrimaryKey(staffPerformance);

            //人事主管提交时，清空原因：总部意见（驳回时，会有意见）
            staffPerformancePersonnelMapper.updateOption(staffPerformance.getId());

            //驳回的数据修改
            Map map = new HashMap();
            map.put("staffPerformanceId",staffPerformance.getId());
            map.put("showState",0);
            map.put("backState",0);
            map.put("oldBackState",1);
            staffPerformancePersonnelMapper.updateStateByInfo(map);

        }
        else if ("ceo-step".equals(btnCode)){// 总经理审核  同意or退回
            switch (stepType){
                case "yes" :
                    staffPerformance.setPerformanceState(5);
                    staffPerformance.setPerformanceStateName("绩效完成");
                    staffPerformance.setReason(null);
                    staffPerformance.setGeneralManagerTime(new Date());

                    Map map = new HashMap();
                    map.put("staffPerformanceId",staffPerformance.getId());
                    map.put("showState",0);
                    map.put("backState",0);
                    staffPerformancePersonnelMapper.updateStateByInfo(map);

                    //离职待结算的人员 绩效完成之后 自动更新为已离职
                    staffPersonnelInfoMapper.updateStaffState(staffPerformance.getId());
                    //工资条完成后，生成付款管理
                    map = new HashMap<>();
                    map.put("staffPerformanceId",staffPerformance.getId());
                    map.put("currUserId",userInfo.getUserId());
                    map.put("currUserName",userInfo.getUserName());
                    staffPerformancePersonnelMapper.generateSurveyPayInfo(map);

                    //同时生成“绩效”与“付款管理”的关联关系表
                    map = new HashMap<>();
                    map.put("staffPerformanceId",staffPerformance.getId());
                    staffPerformancePersonnelMapper.generateSurveyPayInfoDetailNew(map);

                    //更新案件的结算绩效状态
                    map = new HashMap<>();
                    map.put("workTime",staffPerformance.getWorkTime());
                    map.put("oldPerformanceState",1);// 结算绩效的状态 （0、未结算；1、结算中；2、已结算）
                    map.put("newPerformanceState",2);
                    map.put("performanceId",staffPerformance.getId());//绩效id
                    surveyRiskCaseInfoMapper.updatePerformanceState(map);
                    break;
                case "no" :
                    staffPerformance.setPerformanceState(3);
                    staffPerformance.setPerformanceStateName("总经理驳回");
                    staffPerformance.setReason(reason);
                    staffPerformance.setBackState(1);

                    map = new HashMap();
                    map.put("staffPerformanceId",staffPerformance.getId());
                    map.put("showState",1);
                    map.put("backState",1);
                    map.put("oldShowState",0);
                    staffPerformancePersonnelMapper.updateStateByInfo(map);

                    //发给 人事主管
                    map = new HashMap<>();
                    map.put("roleId",107L);
                    List<BusUserRole> roles = busUserRoleMapper.selectBusInfo(map);

                    map = new HashMap();
                    map.put("staffPerformanceId",staffPerformance.getId());
                    map.put("showState",1);
                    map.put("backState",1);
                    int count = staffPerformancePersonnelMapper.listSize(map);
                    Map<String, Object> msgMap = new HashMap<String, Object>();
                    for (BusUserRole role : roles) {
                        msgMap = new HashMap<String, Object>();
                        msgMap.put("title", "绩效驳回");
                        msgMap.put("content", "你有一笔绩效审核驳回，请尽快处理！");
                        msgMap.put("keyWords", "清单名称：" + staffPerformance.getWorkTime() + "绩效"+ "\n" + "驳回人数："+ count + "人");
                        backendWechatApi.send(role.getUserId(), msgMap);
                    }
                    break;
                case "partReturn" : //部分驳回
                    staffPerformance.setPerformanceState(3);
                    staffPerformance.setPerformanceStateName("总经理驳回");
                    staffPerformance.setBackState(2);
                    map = new HashMap();
                    map.put("staffPerformanceId",staffPerformance.getId());
                    map.put("showState",1);
                    map.put("backState",0);
                    map.put("oldBackState",0);
                    staffPerformancePersonnelMapper.updateStateByInfo(map);

                    //发给 人事主管
                    map = new HashMap<>();
                    map.put("roleId",107L);
                    roles = busUserRoleMapper.selectBusInfo(map);

                    map = new HashMap();
                    map.put("staffPerformanceId",staffPerformance.getId());
                    map.put("showState",1);
                    map.put("backState",1);
                    count = staffPerformancePersonnelMapper.listSize(map);
                    msgMap = new HashMap<String, Object>();
                    for (BusUserRole role : roles) {
                        msgMap = new HashMap<String, Object>();
                        msgMap.put("title", "绩效驳回");
                        msgMap.put("content", "你有一笔绩效审核驳回，请尽快处理！");
                        msgMap.put("keyWords", "清单名称：" + staffPerformance.getWorkTime() + "绩效"+ "\n" + "驳回人数："+ count + "人");
                        backendWechatApi.send(role.getUserId(), msgMap);
                    }
                    break;
            }
            staffPerformance.setGeneralManagerId(userInfo.getUserId());
            staffPerformance.setGeneralManagerName(userInfo.getUserName());
            staffPerformanceMapper.updateByPrimaryKey(staffPerformance);
        }else if ("itemSave".equals(btnCode)){
            String jobNo = apiRequest.getString("jobNo");
            String value = apiRequest.getString("value");
            String colCode = apiRequest.getString("colCode");
            Map map = new HashMap();
            map.put("jobNo",jobNo);
            map.put("staffPerformanceId",staffPerformance.getId());
            StaffPerformancePersonnel item = staffPerformancePersonnelMapper.personnelItem(map);//修改的工资明细对象
            switch (colCode){
                case "rate" : item.setRate(convert(value));break;
                case "assessKpi" : item.setAssessKpi(convert(value));break;
                case "integral" : item.setIntegral(convert(value));break;
                case "integralPay" : item.setIntegralPay(convert(value));break;
                case "lateEarlyMoney" : item.setLateEarlyMoney(convert(value));break;
                case "absenteeismMoney" : item.setAbsenteeismMoney(convert(value));break;
                case "leaveMoney" : item.setLeaveMoney(convert(value));break;
                case "sickLeaveTime" : item.setSickLeaveTime(convert(value));break;
                case "sickLeaveMoney" : item.setSickLeaveMoney(convert(value));break;
                case "otherPay" : item.setOtherPay(convert(value));break;
                case "realPay" : item.setRealPay(convert(value));break;
                case "remarks" : item.setRemarks(value);break;
                /*
                case "otherScoreHz" : item.setOtherScoreHz(convert(value));break;
                case "bsScoreHz" : item.setBsScoreHz(convert(value));break;
                case "sunScoreHz" : item.setSunScoreHz(convert(value));break;
                case "otherScoreBs" : item.setOtherScoreBs(convert(value));break;
                case "bsScoreBs" : item.setBsScoreBs(convert(value));break;
                case "sunScoreBs" : item.setSunScoreBs(convert(value));break;
                */
//                case "trafficSubsidy" : item.setTrafficSubsidy(convert(value));break;
                case "realWorkingDays" :item.setRealWorkingDays(convert(value));break;//实际出勤天数
                case "assesPerfPay" :item.setAssesPerfPay(convert(value));break;
                case "managePerfPaySize" :item.setManagePerfPaySize(convert(value));break;
/*                case "welfarePay" :item.setWelfarePay(convert(value));break;
                case "welfareRemark" :item.setWelfareRemark(value);break;*/
                case "managePerfPaySizeHz" :item.setManagePerfPaySizeHz(convert(value));break;
                case "otherCutPay" :item.setOtherCutPay(convert(value));break;
                case "otherCutRemarks" :item.setOtherCutRemarks(value);break;
                case "examinePay" :item.setExaminePay(convert(value));break;
                case "examineRate" :item.setExamineRate(convert(value));break;
                case "fixedPerfPayBase" :item.setFixedPerfPayBase(convert(value));break;
                case "travelAllowancePayBase" :item.setTravelAllowancePayBase(convert(value));break;
                case "managePerfPayBase" :item.setManagePerfPayBase(convert(value));break;
                case "assesPerfBasePay" :item.setAssesPerfBasePay(convert(value));break;
                case "caseSubMoney" :item.setCaseSubMoney(convert(value));break;

            }
            StaffWorkingDaysInfo  staffWorkingDaysInfo = staffWorkingDaysInfoMapper.selectByWorkTime(staffPerformance.getWorkTime());;//根据workTime 查找出勤天数
            if(!"trafficSubsidy".equals(colCode)){
                item = convertPersonnel(item,staffWorkingDaysInfo.getWrokingDays(),"update", staffPerformance.getWorkTime());
            }
            if (!"remarks".equals(colCode) && !"welfareRemark".equals(colCode) && !"otherCutRemarks".equals(colCode)){
                StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByJobNo(item.getJobNo());//根据jobNo查询员工对象
                item.setRealPay(realPay(staffPersonnelInfo,item));//计算实发绩效
            }
            staffPerformancePersonnelMapper.updateByPrimaryKey(item);
            staffPerformance.setStaffPerformancePersonnel(item);//返回给前端
        }
        else if ("9999".equals(btnCode)) {
            staffPerformance.setDeleteFlag(1);
            staffPerformanceMapper.updateByPrimaryKey(staffPerformance);
            //同时删除绩效明细
            staffPerformancePersonnelMapper.deleteByStaffPerformanceId(staffPerformance.getId());
            //同时释放案件状态
            Map map = new HashMap<>();
            map.put("workTime",staffPerformance.getWorkTime());
            map.put("oldPerformanceState",1);// 结算绩效的状态 （0、未结算；1、结算中；2、已结算）
            map.put("newPerformanceState",0);
            map.put("performanceId",staffPerformance.getId());//绩效id
            map.put("delStaffPerformanceId",1);//删除绩效id
            surveyRiskCaseInfoMapper.updatePerformanceState(map);

        }else if("passWord".equals(btnCode)){ //校准密码
            String passWord = apiRequest.getString("queryPassword");
            if(passWord.equals(staffPerformance.getQueryPassword())){
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }else{
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        }else if("back".equals(btnCode)){ //填写意见
            String backReason = apiRequest.getString("backReason");
            StaffPerformancePersonnel staffPerformancePersonnel = staffPerformancePersonnelMapper.selectByPrimaryKey(apiRequest.getLong("staffPerformancePersonnelId"));

            String roleCode = apiRequest.getString("roleCode");
            if("organManager-step".equals(roleCode)){
                staffPerformancePersonnel.setOrganOpinion(backReason);
            }else if("superiorManager-step".equals(roleCode)){
                staffPerformancePersonnel.setSuperiorOpinion(backReason);
            }else if("ceo-step".equals(roleCode)){
                staffPerformancePersonnel.setBossOpinion(backReason);
                staffPerformancePersonnel.setShowState(1);
                staffPerformancePersonnel.setBackState(1);
            }else if("surveyUser-step".equals(roleCode)){
                staffPerformancePersonnel.setOrganOpinion(backReason);
            }
            staffPerformancePersonnelMapper.updateByPrimaryKey(staffPerformancePersonnel);
            staffPerformance.setStaffPerformancePersonnel(staffPerformancePersonnel);
        }else if("removeBack".equals(btnCode)){ //取消意见
            StaffPerformancePersonnel staffPerformancePersonnel = staffPerformancePersonnelMapper.selectByPrimaryKey(apiRequest.getLong("staffPerformancePersonnelId"));
            String roleCode = apiRequest.getString("roleCode");
            if("organManager-step".equals(roleCode)){
                staffPerformancePersonnel.setOrganOpinion(null);
            }else if("superiorManager-step".equals(roleCode)){
                staffPerformancePersonnel.setSuperiorOpinion(null);
            }else if("ceo-step".equals(roleCode)){
                staffPerformancePersonnel.setBossOpinion(null);
                staffPerformancePersonnel.setShowState(0);
                staffPerformancePersonnel.setBackState(0);
            }else if("surveyUser-step".equals(roleCode)){
                staffPerformancePersonnel.setOrganOpinion(null);
            }
            staffPerformancePersonnelMapper.updateByPrimaryKey(staffPerformancePersonnel);
            staffPerformance.setStaffPerformancePersonnel(staffPerformancePersonnel);
        }
        else if("investigatorCaseOpinion".equals(btnCode)) { //具体案件的意见
            Long surveyInvestigatorCaseId = apiRequest.getLong("surveyInvestigatorCaseId");
            String staffOpinion = apiRequest.getString("staffOpinion");
            SurveyInvestigatorCaseSub surveyInvestigatorCaseSub = surveyInvestigatorCaseSubMapper.selectByInvestigatorCaseId(surveyInvestigatorCaseId);
            if(surveyInvestigatorCaseSub == null){
                SurveyInvestigatorCase surveyInvestigatorCase = surveyInvestigatorCaseMapper.selectByPrimaryKey(surveyInvestigatorCaseId);
                if(surveyInvestigatorCase != null){
                    surveyInvestigatorCaseSub = new SurveyInvestigatorCaseSub();
                    surveyInvestigatorCaseSub.setSurveyInvestigatorCaseId(surveyInvestigatorCaseId);
                    surveyInvestigatorCaseSub.setStaffOpinion(staffOpinion);
                    if(staffOpinion ==null){
                        surveyInvestigatorCaseSub.setStaffOpinionState(null);
                    }else{
                        surveyInvestigatorCaseSub.setStaffOpinionState(0);
                    }
                    surveyInvestigatorCaseSubMapper.insert(surveyInvestigatorCaseSub);
                }
            }else{
                surveyInvestigatorCaseSub.setStaffOpinion(staffOpinion);
                if(staffOpinion ==null){
                    surveyInvestigatorCaseSub.setStaffOpinionState(null);
                }else{
                    surveyInvestigatorCaseSub.setStaffOpinionState(0);
                }
                surveyInvestigatorCaseSubMapper.updateByPrimaryKey(surveyInvestigatorCaseSub);
            }
        }
        else if("upInvestigatorCaseOpinion".equals(btnCode)) { //取消具体案件的意见（分管总标记处理）
            Long surveyInvestigatorCaseId = apiRequest.getLong("surveyInvestigatorCaseId");
            SurveyInvestigatorCaseSub surveyInvestigatorCaseSub = surveyInvestigatorCaseSubMapper.selectByInvestigatorCaseId(surveyInvestigatorCaseId);
            if(surveyInvestigatorCaseSub !=null){
//                surveyInvestigatorCaseSub.setStaffOpinion(null);
                surveyInvestigatorCaseSub.setStaffOpinionState(1);
                surveyInvestigatorCaseSubMapper.updateByPrimaryKey(surveyInvestigatorCaseSub);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPerformance);
    }

    private Double convert(String value){
        if (!StringUtils.isEmpty(value)){
            return Double.parseDouble(value);
        }
        return 0D;
    }

    /**
     * 初始化绩效数据
     * @param staffPerformance 绩效单对象(月份)
     * @param userInfo
     * @param staffPaySlip  工资单对象（关联钉钉数据）
     */
    public void generateStaffPerformance(StaffPerformance staffPerformance, UserInfo userInfo, StaffPaySlip staffPaySlip){
        try {
            StaffWorkingDaysInfo staffWorkingDaysInfo = staffWorkingDaysInfoMapper.selectByWorkTime(staffPerformance.getWorkTime());;//根据workTime 查找出勤天数

            Map<String,Object> map = new HashMap();
            map.put("staffPerformanceId",staffPerformance.getId());//绩效单ID
            map.put("userName",userInfo.getUserName());
            map.put("staffPaySlipId",staffPaySlip.getId());//工资单ID
            map.put("workTime",staffPerformance.getWorkTime());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date sDate = sdf.parse(staffPerformance.getWorkTime());
            Calendar c = Calendar.getInstance();
            c.setTime(sDate);
            c.add(Calendar.MONTH, 1);
            map.put("entryTime",c.getTime());
            map.put("workingDays",staffWorkingDaysInfo.getWrokingDays());
            map.put("performance",0);
            staffPerformancePersonnelMapper.generate(map);// 插入绩效子表  insert into select  // 2、新需求：2020年5月25日15:18:19  创建绩效时，状态为合伙的员工不进入绩效
            // 同步 所有的案件(未标记结算，在此时间之前保司终审通过的案件)：1、是否结算绩效 2、绩效结算时间
            map = new HashMap<>();
            map.put("workTime",staffPerformance.getWorkTime());
            map.put("oldPerformanceState",0);// 结算绩效的状态 （0、未结算；1、结算中；2、已结算）
            map.put("newPerformanceState",1);
            map.put("dateTime",new Date());
            map.put("addStaffPerformanceId",staffPerformance.getId());//绩效id(此处为了录入绩效id)
            surveyRiskCaseInfoMapper.updatePerformanceState(map);

            map.clear();
            map.put("staffPerformanceId",staffPerformance.getId());
//            List<StaffPerformancePersonnel> data = staffPerformancePersonnelMapper.list(map);
            List<StaffPerformancePersonnel> data = staffPerformancePersonnelMapper.listTwo(map);
            for (StaffPerformancePersonnel item : data) {
                item = convertPersonnel(item,staffWorkingDaysInfo.getWrokingDays(),"init",staffPerformance.getWorkTime());//计算绩效相关逻辑
                staffPerformancePersonnelMapper.updateByPrimaryKey(item);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    /**
     * 初始化绩效数据
     * @param
     * @param userInfo
     * @param staffPaySlip  工资单对象（关联钉钉数据）
     * 这个方法是在选择工资单发放绩效时，存入到绩效子表中
     */
    public StaffPerformancePersonnel generateAAAStaffPerformance(StaffPerformance staffPerformance, UserInfo userInfo, StaffPaySlip staffPaySlip,StaffPayPersonnelSlip staffPayPersonnelSlip){
        try {
            StaffWorkingDaysInfo staffWorkingDaysInfo = staffWorkingDaysInfoMapper.selectByWorkTime(staffPerformance.getWorkTime());;//根据workTime 查找出勤天数

            Map<String,Object> map = new HashMap();
            map.put("staffPerformanceId",staffPerformance.getId());//绩效单ID
            map.put("userName",userInfo.getUserName());
            map.put("staffPaySlipId",staffPaySlip.getId());//工资单ID
            map.put("workTime",staffPerformance.getWorkTime());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date sDate = sdf.parse(staffPerformance.getWorkTime());
            Calendar c = Calendar.getInstance();
            c.setTime(sDate);
            c.add(Calendar.MONTH, 1);
            map.put("entryTime",c.getTime());
            map.put("workingDays",staffWorkingDaysInfo.getWrokingDays());
            map.put("performance",1);
            map.put("staffPersonnelId",staffPayPersonnelSlip.getStaffPersonnelId());
            map.put("staffPaySlipId",staffPaySlip.getId());
            staffPerformancePersonnelMapper.generateOne(map);
            Long newId = (Long) map.get("id");// 插入绩效子表  insert into select  // 2、新需求：2020年5月25日15:18:19  创建绩效时，状态为合伙的员工不进入绩效
            map = new HashMap<>();
            map.put("workTime",staffPerformance.getWorkTime());
            map.put("oldPerformanceState",0);// 结算绩效的状态 （0、未结算；1、结算中；2、已结算）
            map.put("newPerformanceState",1);
            map.put("dateTime",new Date());
            map.put("userId",staffPayPersonnelSlip.getUserId());
//            todo暂时不知道是否要
            surveyRiskCaseInfoMapper.updatePerformanceStatePerson(map);
            map.clear();
            map.put("staffPerformanceId",staffPerformance.getId());
            StaffPerformancePersonnel item = staffPerformancePersonnelMapper.listOne(newId);
            item.setStaffPersonnelInfo(staffPersonnelInfoMapper.selectStaffPersonelInfoByJobNo(item.getJobNo()));
            item.setSurveyInvestigator(surveyInvestigatorMapper.selectByUserId(item.getStaffPersonnelInfo().getUserId()));
                item = convertPersonnel(item,staffWorkingDaysInfo.getWrokingDays(),"init",staffPerformance.getWorkTime());
                staffPerformancePersonnelMapper.updateByPrimaryKey(item);
            return item;
        } catch (ParseException e) {
         return null;
        }
    }
    /**
     * 绩效迟到早退 矿工 等算法
     * @param item 绩效明细
     * @param workingDays 应上班天数
     * @return
     */
    public StaffPerformancePersonnel convertPersonnel(StaffPerformancePersonnel item,Double workingDays,String state, String workTime){
        /*StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByJobNo(item.getJobNo());//根据jobNo查询员工对象
        SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(staffPersonnelInfo.getUserId());*/
        StaffPersonnelInfo staffPersonnelInfo = item.getStaffPersonnelInfo();
        SurveyInvestigator surveyInvestigator = item.getSurveyInvestigator();
        if(staffPersonnelInfo == null){
            staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByJobNo(item.getJobNo());
        }
        if(surveyInvestigator == null){
            surveyInvestigator = surveyInvestigatorMapper.selectByUserId(staffPersonnelInfo.getUserId());
        }
        Double A = staffPersonnelInfo.getFixedPerfPay() + staffPersonnelInfo.getManagePerfPay() + staffPersonnelInfo.getTravelAllowancePay();
        Double D = workingDays;

        //百分比
        Double rate =(item.getRealWorkingDays()/item.getWorkingDays());
        BigDecimal bg = new BigDecimal(rate);
        rate = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        item.setRate(rate);

        Double money = 0D;
        if("init".equals(state)){
            //固定 + 管理 + 补贴
            //迟到早退
            Integer lateEarlyNum = item.getLateEarlyNum() == null ? 0 : item.getLateEarlyNum();
            if (lateEarlyNum == 3){
                money = A / D;
            }else if (lateEarlyNum == 4){
                money = 2 * A / D;
            }else if (lateEarlyNum >=5 && lateEarlyNum < 10){
                money = 3 * A / D;
            }else if (lateEarlyNum >= 10){
                money = 0.5 * A;
            }
            item.setLateEarlyMoney(money.intValue() != 0 ? -DecimalUtil.twoDecimalTOFourFromFive(money) : 0D);

            //矿工
            money = 0D;
            Integer absentNum = item.getAbsenteeismNum() == null ? 0 : item.getAbsenteeismNum();
            if (absentNum == 1){
                money = 5 * A / D;
            }else if (absentNum == 2){
                money = 0.5 * A;
            }else if (absentNum > 2){
                money = A;
            }
            item.setAbsenteeismMoney(money.intValue() != 0 ? -DecimalUtil.twoDecimalTOFourFromFive(money) : 0D);

            //事假
            money = 0D;
            Double leaveNum = item.getLeaveNum() == null ? 0D : item.getLeaveNum();
            money = (A / D / 7.5) * leaveNum;
            item.setLeaveMoney(money.intValue() != 0 ? -DecimalUtil.twoDecimalTOFourFromFive(money) : 0);

            //病假
            money = 0D;
            Double sickLeaveTime = item.getSickLeaveTime() == null ? 0D : item.getSickLeaveTime();
            money = (A / D / 7.5) * sickLeaveTime * 0.3;
            item.setSickLeaveMoney(money.intValue() != 0 ? -DecimalUtil.twoDecimalTOFourFromFive(money) : 0);

            //市内交通补贴 -- 是否新人达标 为否时 交通费补贴永远为0
            /*money = 0D;
            item.setTrafficSubsidy(money);
            if(surveyInvestigator != null && surveyInvestigator.getIsNewPeople() ==1){
                Double assessOtherScore = item.getAssessOtherScore() == null ? 0D : item.getAssessOtherScore();//考核前积分（非京沪）
                Double assessBsScore = item.getAssessBsScore() == null ? 0D : item.getAssessBsScore();//考核前积分（京沪）
                money = assessOtherScore * 7 + assessBsScore * 5;
                item.setTrafficSubsidy(money.intValue() != 0 ? DecimalUtil.twoDecimalTOFourFromFive(money) : 0);
            }*/

            //当月有效基础积分
            money = 0D;
            item.setMonthBasicIntegral(money);
            if(surveyInvestigator !=null && surveyInvestigator.getBasicIntegral() != null){
                //如果入职时间在当月，则计算百分比，否则直接全额积分
                String entryTime = format.format(staffPersonnelInfo.getEntryTime());//员工入职时间
                item.setBasicIntegral(surveyInvestigator.getBasicIntegral());
                if(workTime.compareTo(entryTime) == 0){
                    money =  surveyInvestigator.getBasicIntegral() * rate;
                }else{
                    money =  surveyInvestigator.getBasicIntegral();
                }
                item.setMonthBasicIntegral(money.intValue() != 0 ? DecimalUtil.twoDecimalTOFourFromFive(money) : 0);
            }

            //离职待结算的人员,生成绩效时，需要把离职成本算入其他补扣款中
//            if(staffPersonnelInfo.getStaffState() != null && staffPersonnelInfo.getStaffState() == 3 ){
//                Double quitCost = 0D;
//                if(staffPersonnelInfo.getQuitCost() != null){
//                    quitCost = staffPersonnelInfo.getQuitCost();
//                }
//                item.setOtherPay((item.getOtherPay() == null ? 0D : item.getOtherPay()) + quitCost);
//                item.setRemarks("离职成本：" + quitCost + "元");
//            }
        }
        //实时获取积分 -- 积分绩效 和 管理绩效（按量）；   2020年11月5日15:16:00 新需求：不再实时获取
        item = score(item, workTime, state, surveyInvestigator,staffPersonnelInfo);
        //合伙（发固定绩效）:发绩效,但积分绩效模块永远是0,其他都正常
        if(staffPersonnelInfo.getRelation() !=null && staffPersonnelInfo.getRelation() == 7){
            item.setOtherScoreHz(0D);
            item.setBsScoreHz(0D);
            item.setSunScoreHz(0D);
            item.setScoreHz(0D);
            item.setOtherScoreBs(0D);
            item.setBsScoreBs(0D);
            item.setSunScoreBs(0D);
            item.setScoreBs(0D);
            item.setSunMoneyBs(0D);
            item.setIntegralPay(0D);
            item.setCaseSubMoney(0D);
        }
        //固定绩效 驻外补贴、管理绩效
        item.setFixedPerfPay(DecimalUtil.twoDecimalTOFourFromFive(item.getFixedPerfPayBase() * rate));
        item.setTravelAllowancePay(DecimalUtil.twoDecimalTOFourFromFive(item.getTravelAllowancePayBase() * rate));
        item.setManagePerfPay(DecimalUtil.twoDecimalTOFourFromFive(item.getManagePerfPayBase() * rate));

        //实际考核绩效real_assess_kpi = 考核绩效 asses_perf_pay* 绩效考核assess_kpi
        //考核绩效基数 = 员工管理中的考核绩效 + 互助考核绩效（按量）*互助案件数量（机构）+保险考核绩效（按量）*保险案件数量（机构）
        money = 0D;
        Double assesPerfBasePay = item.getAssesPerfBasePay() == null ? 0D : item.getAssesPerfBasePay();//员工管理中的考核绩效
        Double assessKpi = item.getAssessKpi() == null ? 0D : item.getAssessKpi();//考核系数
        Double bsMoney = (item.getManagePerfPaySize() == null ? 0D : item.getManagePerfPaySize()) * (item.getManageCaseNum() == null ? 0D : item.getManageCaseNum()); //保险考核绩效（按量）*保险案件数量（机构）
        Double hzMoney = (item.getManagePerfPaySizeHz() == null ? 0D : item.getManagePerfPaySizeHz()) * (item.getManageCaseNumHz() == null ? 0D : item.getManageCaseNumHz()) ;//互助考核绩效（按量）*互助案件数量（机构）
        bsMoney = 0D;
        hzMoney = 0D;

        //2021年4月15日 10点59分  ，综合考核绩效基数 。 不计算。
        if ("init".equals(state)){
            Double assesPerfPay = assesPerfBasePay + bsMoney + hzMoney;
            item.setAssesPerfPay(assesPerfPay);
        }
//        Double assesPerfPay = item.getAssesPerfPay() == null ? 0D : item.getAssesPerfPay();

        money = item.getAssesPerfPay() * assessKpi;
        item.setRealAssessKpi(money.intValue() != 0 ? DecimalUtil.twoDecimalTOFourFromFive(money) : 0);


//        Double examineScore = item.getExamineScore() == null ? 0D : item.getExamineScore();
//        Double examineRate = item.getExamineRate() == null ? 0D : item.getExamineRate();
//        item.setExaminePay(DecimalUtil.twoDecimalTOFourFromFive(examineScore * 2 * examineRate));
        //不计算分值相关 绩效数据
        if (!StringUtils.isEmpty(persons)) {
            String [] personIds = persons.split(",");
            for (String personId : personIds) {
                if (!StringUtils.isEmpty(personId)){
                    if (staffPersonnelInfo.getId().intValue() == Integer.parseInt(personId)){
                        item.setOtherScoreHz(0D);
                        item.setBsScoreHz(0D);
                        item.setSunScoreHz(0D);
                        item.setOtherScoreBs(0D);
                        item.setBsScoreBs(0D);
                        item.setSunScoreBs(0D);
                        item.setSunMoneyBs(0D);
                        item.setIntegralPay(0D);
//                        item.setTrafficSubsidy(0D);
                        item.setCaseSubMoney(0D);
                    }
                }
            }
        }

        Double realPay = realPay(staffPersonnelInfo,item);
        item.setRealPay(realPay);

        return item;
    }

    /**
         * 绩效迟到早退 矿工 等算法
         * @param item 绩效明细
         * @param workingDays 应上班天数
         * @return
         */
//        public StaffPayPersonnelSlip convertPersonnel(StaffPayPersonnelSlip item,Double workingDays,String state, String workTime){
//        /*StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByJobNo(item.getJobNo());//根据jobNo查询员工对象
//        SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(staffPersonnelInfo.getUserId());*/
//        StaffPersonnelInfo staffPersonnelInfo = item.getStaffPersonnelInfo();
//        SurveyInvestigator surveyInvestigator = item.getSurveyInvestigator();
//        if(staffPersonnelInfo == null){
//            staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByJobNo(item.getJobNo());
//        }
//        if(surveyInvestigator == null){
//            surveyInvestigator = surveyInvestigatorMapper.selectByUserId(staffPersonnelInfo.getUserId());
//        }
//        Double A = staffPersonnelInfo.getFixedPerfPay() + staffPersonnelInfo.getManagePerfPay() + staffPersonnelInfo.getTravelAllowancePay();
//        Double D = workingDays;
//
//        //百分比
//        Double rate =(item.getRealWorkingDays()/item.getWorkingDays());
//        BigDecimal bg = new BigDecimal(rate);
//        rate = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
//        item.setRate(rate);
//
//        Double money = 0D;
//        if("init".equals(state)){
//            //固定 + 管理 + 补贴
//            //迟到早退
//            Integer lateEarlyNum = item.getLateEarlyNum() == null ? 0 : item.getLateEarlyNum();
//            if (lateEarlyNum == 3){
//                money = A / D;
//            }else if (lateEarlyNum == 4){
//                money = 2 * A / D;
//            }else if (lateEarlyNum >=5 && lateEarlyNum < 10){
//                money = 3 * A / D;
//            }else if (lateEarlyNum >= 10){
//                money = 0.5 * A;
//            }
//            item.setLateEarlyMoney(money.intValue() != 0 ? -DecimalUtil.twoDecimalTOFourFromFive(money) : 0D);
//
//            //矿工
//            money = 0D;
//            Integer absentNum = item.getAbsenteeismNum() == null ? 0 : item.getAbsenteeismNum();
//            if (absentNum == 1){
//                money = 5 * A / D;
//            }else if (absentNum == 2){
//                money = 0.5 * A;
//            }else if (absentNum > 2){
//                money = A;
//            }
//            item.setAbsenteeismMoney(money.intValue() != 0 ? -DecimalUtil.twoDecimalTOFourFromFive(money) : 0D);
//
//            //事假
//            money = 0D;
//            Double leaveNum = item.getLeaveNum() == null ? 0D : item.getLeaveNum();
//            money = (A / D / 7.5) * leaveNum;
//            item.setLeaveMoney(money.intValue() != 0 ? -DecimalUtil.twoDecimalTOFourFromFive(money) : 0);
//
//            //病假
//            money = 0D;
//            Double sickLeaveTime = item.getSickLeaveTime() == null ? 0D : item.getSickLeaveTime();
//            money = (A / D / 7.5) * sickLeaveTime * 0.3;
//            item.setSickLeaveMoney(money.intValue() != 0 ? -DecimalUtil.twoDecimalTOFourFromFive(money) : 0);
//
//            //市内交通补贴 -- 是否新人达标 为否时 交通费补贴永远为0
//            /*money = 0D;
//            item.setTrafficSubsidy(money);
//            if(surveyInvestigator != null && surveyInvestigator.getIsNewPeople() ==1){
//                Double assessOtherScore = item.getAssessOtherScore() == null ? 0D : item.getAssessOtherScore();//考核前积分（非京沪）
//                Double assessBsScore = item.getAssessBsScore() == null ? 0D : item.getAssessBsScore();//考核前积分（京沪）
//                money = assessOtherScore * 7 + assessBsScore * 5;
//                item.setTrafficSubsidy(money.intValue() != 0 ? DecimalUtil.twoDecimalTOFourFromFive(money) : 0);
//            }*/
//
//            //当月有效基础积分
//            money = 0D;
//            item.setMonthBasicIntegral(money);
//            if(surveyInvestigator !=null && surveyInvestigator.getBasicIntegral() != null){
//                //如果入职时间在当月，则计算百分比，否则直接全额积分
//                String entryTime = format.format(staffPersonnelInfo.getEntryTime());//员工入职时间
//                item.setBasicIntegral(surveyInvestigator.getBasicIntegral());
//                if(workTime.compareTo(entryTime) == 0){
//                    money =  surveyInvestigator.getBasicIntegral() * rate;
//                }else{
//                    money =  surveyInvestigator.getBasicIntegral();
//                }
//                item.setMonthBasicIntegral(money.intValue() != 0 ? DecimalUtil.twoDecimalTOFourFromFive(money) : 0);
//            }
//
//            //离职待结算的人员,生成绩效时，需要把离职成本算入其他补扣款中
////            if(staffPersonnelInfo.getStaffState() != null && staffPersonnelInfo.getStaffState() == 3 ){
////                Double quitCost = 0D;
////                if(staffPersonnelInfo.getQuitCost() != null){
////                    quitCost = staffPersonnelInfo.getQuitCost();
////                }
////                item.setOtherPay((item.getOtherPay() == null ? 0D : item.getOtherPay()) + quitCost);
////                item.setRemarks("离职成本：" + quitCost + "元");
////            }
//
//        }
//
//        //实时获取积分 -- 积分绩效 和 管理绩效（按量）；   2020年11月5日15:16:00 新需求：不再实时获取
//        item = score(item, workTime, state, surveyInvestigator,staffPersonnelInfo);
//
//        //合伙（发固定绩效）:发绩效,但积分绩效模块永远是0,其他都正常
//        if(staffPersonnelInfo.getRelation() !=null && staffPersonnelInfo.getRelation() == 7){
//            item.setOtherScoreHz(0D);
//            item.setBsScoreHz(0D);
//            item.setSunScoreHz(0D);
//            item.setScoreHz(0D);
//
//            item.setOtherScoreBs(0D);
//            item.setBsScoreBs(0D);
//            item.setSunScoreBs(0D);
//            item.setScoreBs(0D);
//            item.setSunMoneyBs(0D);
//            item.setIntegralPay(0D);
//            item.setCaseSubMoney(0D);
//        }
//
//        //固定绩效 驻外补贴、管理绩效
//        item.setFixedPerfPay(DecimalUtil.twoDecimalTOFourFromFive(item.getFixedPerfPayBase() * rate));
//        item.setTravelAllowancePay(DecimalUtil.twoDecimalTOFourFromFive(item.getTravelAllowancePayBase() * rate));
//        item.setManagePerfPay(DecimalUtil.twoDecimalTOFourFromFive(item.getManagePerfPayBase() * rate));
//
//        //实际考核绩效real_assess_kpi = 考核绩效 asses_perf_pay* 绩效考核assess_kpi
//        //考核绩效基数 = 员工管理中的考核绩效 + 互助考核绩效（按量）*互助案件数量（机构）+保险考核绩效（按量）*保险案件数量（机构）
//        money = 0D;
//        Double assesPerfBasePay = item.getAssesPerfBasePay() == null ? 0D : item.getAssesPerfBasePay();//员工管理中的考核绩效
//        Double assessKpi = item.getAssessKpi() == null ? 0D : item.getAssessKpi();//考核系数
//        Double bsMoney = (item.getManagePerfPaySize() == null ? 0D : item.getManagePerfPaySize()) * (item.getManageCaseNum() == null ? 0D : item.getManageCaseNum()); //保险考核绩效（按量）*保险案件数量（机构）
//        Double hzMoney = (item.getManagePerfPaySizeHz() == null ? 0D : item.getManagePerfPaySizeHz()) * (item.getManageCaseNumHz() == null ? 0D : item.getManageCaseNumHz()) ;//互助考核绩效（按量）*互助案件数量（机构）
//        bsMoney = 0D;
//        hzMoney = 0D;
//
//        //2021年4月15日 10点59分  ，综合考核绩效基数 。 不计算。
//        if ("init".equals(state)){
//            Double assesPerfPay = assesPerfBasePay + bsMoney + hzMoney;
//            item.setAssesPerfPay(assesPerfPay);
//        }
////        Double assesPerfPay = item.getAssesPerfPay() == null ? 0D : item.getAssesPerfPay();
//
//        money = item.getAssesPerfPay() * assessKpi;
//        item.setRealAssessKpi(money.intValue() != 0 ? DecimalUtil.twoDecimalTOFourFromFive(money) : 0);
//
//
////        Double examineScore = item.getExamineScore() == null ? 0D : item.getExamineScore();
////        Double examineRate = item.getExamineRate() == null ? 0D : item.getExamineRate();
////        item.setExaminePay(DecimalUtil.twoDecimalTOFourFromFive(examineScore * 2 * examineRate));
//        //不计算分值相关 绩效数据
//        if (!StringUtils.isEmpty(persons)) {
//            String [] personIds = persons.split(",");
//            for (String personId : personIds) {
//                if (!StringUtils.isEmpty(personId)){
//                    if (staffPersonnelInfo.getId().intValue() == Integer.parseInt(personId)){
//                        item.setOtherScoreHz(0D);
//                        item.setBsScoreHz(0D);
//                        item.setSunScoreHz(0D);
//                        item.setOtherScoreBs(0D);
//                        item.setBsScoreBs(0D);
//                        item.setSunScoreBs(0D);
//                        item.setSunMoneyBs(0D);
//                        item.setIntegralPay(0D);
////                        item.setTrafficSubsidy(0D);
//                        item.setCaseSubMoney(0D);
//                    }
//                }
//            }
//        }
//
//        Double realPay = realPay(staffPersonnelInfo,item);
//        item.setRealPay(realPay);
//
//        return item;
//    }

    //实时获取积分 -- 积分绩效
    private StaffPerformancePersonnel score(StaffPerformancePersonnel item, String workTime,String state, SurveyInvestigator surveyInvestigator,StaffPersonnelInfo staffPersonnelInfo) {
        Map map = new HashMap<>();
        if("init".equals(state)) {
            Double money = 0D;
            Double score =  item.getScoreHz() + item.getSunScoreHz() + item.getScoreBs();
            if (score > item.getMonthBasicIntegral()){
                money = (score - item.getMonthBasicIntegral()) *30;
            }
            //保司阳性奖励
            if(item.getSunMoneyBs() != null){
                money = money + item.getSunMoneyBs();
            }
            //个案减损奖励
            if (item.getCaseSubMoney() != null){
                money = money + item.getCaseSubMoney();
            }
            item.setIntegralPay(money.intValue() != 0 ? DecimalUtil.twoDecimalTOFourFromFive(money) : 0);

        }else{
            //绩效详情的积分分值
            map = new HashMap<>();
            map.put("id",item.getId());
            map.put("workTime",workTime);
            map.put("staffPerformanceId",item.getStaffPerformanceId());
            StaffPerformanceInfoDTO info = staffPerformancePersonnelMapper.selectScore(map);
            //(实时查询到的积分，与之前落地的数据，不相同，才更新)
            if(item.getScoreBs() != info.getScoreBs() || item.getSunMoneyBs() != info.getSunMoneyBs() || item.getScoreHz() != info.getScoreHz() || item.getSunScoreHz() != info.getSunScoreHz() || item.getCaseSubMoney() != info.getCaseSubMoney()){
                item.setScoreHz(info.getScoreHz());
                item.setSunScoreHz(info.getSunScoreHz());
                item.setScoreBs(info.getScoreBs());
//                item.setSunScoreBs(info.getSunScoreBs());
                item.setSunMoneyBs(info.getSunMoneyBs());

                Double money = 0D;
                Double score =  info.getScoreHz() + info.getSunScoreHz() + info.getScoreBs();
                if (score > item.getMonthBasicIntegral()){
                    money = (score - item.getMonthBasicIntegral()) *30;
                }
                //保司阳性奖励
                if(item.getSunMoneyBs() != null){
                    money = money + item.getSunMoneyBs();
                }
                //个案减损奖励
                if (item.getCaseSubMoney() != null){
                    money = money + item.getCaseSubMoney();
                }
                item.setIntegralPay(money.intValue() != 0 ? DecimalUtil.twoDecimalTOFourFromFive(money) : 0);
            }
            item.setHzStaffOpinionState(info.getHzStaffOpinionState());
            item.setBsStaffOpinionState(info.getBsStaffOpinionState());
        }

        //获取“审核总积分”，“审核绩效”
        if("互助审核员A".equals(item.getJobPost())){
            map = new HashMap<>();
            map.put("workTime",workTime);
            map.put("reviewUserId",item.getUserId());
            map.put("staffPerformanceId",item.getStaffPerformanceId());
            double examineScore = staffPerformancePersonnelMapper.selectExamineScore(map);
            item.setExaminePay(examineScore * 2);
            item.setExamineScore(examineScore);
        }

        if(surveyInvestigator !=null){
            item.setManageCaseNum(0);
            item.setManageCaseNumHz(0);
            //2021年4月14日  17点33分。  暂时取0  以与汪鑫确认
            if (false){
                //如果该员工的“管理绩效（按量）”不为零，则计算其所在机构做的所有案件数量（无论人员在省级或者片区，都查询省级的所有案件）
                if (staffPersonnelInfo.getManagePerfPaySize() !=null && staffPersonnelInfo.getManagePerfPaySize() > 0) {
                    map = new HashMap<>();
                    map.put("workTime",workTime);
                    map.put("surveyParentOrgId",surveyFranchiseeMapper.selectId(surveyInvestigator.getOrgId()));
                    map.put("search",40);
                    map.put("orgAttr",1);
                    map.put("performanceId",item.getStaffPerformanceId());
                    int num = surveyAssignOrgMapper.selectOrgCaseListSize(map);
                    item.setManageCaseNum(num);
                }
                if (staffPersonnelInfo.getManagePerfPaySizeHz() !=null && staffPersonnelInfo.getManagePerfPaySizeHz() > 0) {
                    map = new HashMap<>();
                    map.put("workTime",workTime);
                    map.put("surveyParentOrgId",surveyFranchiseeMapper.selectId(surveyInvestigator.getOrgId()));
                    map.put("search",40);
                    map.put("orgAttr",2);
                    map.put("performanceId",item.getStaffPerformanceId());
                    int num = surveyAssignOrgMapper.selectOrgCaseListSize(map);
                    item.setManageCaseNumHz(num);
                }
            }
        }


        return item;
    }

    /**
     * 计算实发绩效
     * @param staffPersonnelInfo 员工对象
     * @param item 绩效明细行
     * @return
     */
    private Double realPay(StaffPersonnelInfo staffPersonnelInfo,StaffPerformancePersonnel item){
        Field[] filelds = StaffPerformancePersonnel.class.getDeclaredFields();
        for (Field fileld : filelds) {
            fileld.setAccessible(true);
            try {
                Object value = fileld.get(item);
                if (value == null){
                    if(Long.class.equals(fileld.getType())){
                        fileld.set(item,0L);
                    }else if (Double.class.equals(fileld.getType())){
                        fileld.set(item,0D);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Double A = item.getFixedPerfPay() + item.getManagePerfPay() + item.getTravelAllowancePay();
        Double money = A + item.getRealAssessKpi()
                + item.getIntegralPay() + item.getLateEarlyMoney() + item.getAbsenteeismMoney()
                + item.getLeaveMoney() + item.getSickLeaveMoney() + item.getOtherPay() + item.getOtherCutPay() +item.getExaminePay();
        return DecimalUtil.twoDecimalTOFourFromFive(money);
    }


    public String getDistanceTime(Date hrTime, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hrTime);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.add(Calendar.DATE, days);
        long threeDate = calendar.getTime().getTime();

        Date date2 = new Date();
        long nowDate = date2.getTime();
        long diff = threeDate - nowDate;
        long day = diff / (24 * 60 * 60 * 1000);
        long hour = diff / (60 * 60 * 1000) - day * 24;
        long min = diff / (60 * 1000) - day * 24 * 60 - hour * 60;
        long sec = diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
        String time = day + "天" + hour + "小时" + min + "分" + sec + "秒";
        if(day == 0){
            time = hour + "小时" + min + "分" + sec + "秒";
        }
        return time;
    }


    /**
     * 员工的积分案件list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "员工的积分案件list", value = "staff-performance-score-case-list", apiParams = { })
    @Override
    public ApiResponse scoreCaseList(ApiRequest apiReq) {
        StaffPerformance staffPerformance = staffPerformanceMapper.selectByPrimaryKey(apiReq.getLong("staffPerformanceId"));
        //不分页
        if(apiReq.getString("havePage") == null){
            this.setBackendPageSize(apiReq);
        }
        //调查员
        Long userId = apiReq.getLong("userId");
        SurveyInvestigator investigator = surveyInvestigatorMapper.selectByUserId(userId);

        List<ScoreDto> list = new ArrayList<>();
        int count = 0;
        String btnCode = apiReq.getString("btnCode");
        if("case".equals(btnCode)){
            /*if(investigator !=null){
                apiReq.put("mySurveyOrgId",investigator.getOrgId());
            }*/
            apiReq.put("workTime",staffPerformance.getWorkTime());
            apiReq.put("surveyUserId",userId);
            apiReq.put("performanceId",staffPerformance.getId());
            list = surveyInvestigatorCaseMapper.getScoreCaseListByInvestigator(apiReq);
            count = surveyInvestigatorCaseMapper.getScoreCaseListSizeByInvestigator(apiReq);
        }
        else if("caseNum".equals(btnCode)){
            apiReq.put("workTime",staffPerformance.getWorkTime());
            apiReq.put("surveyParentOrgId",surveyFranchiseeMapper.selectId(investigator.getOrgId()));
            apiReq.put("search",40);
            apiReq.put("orgAttr",apiReq.getString("orgAttr"));
            apiReq.put("performanceId",staffPerformance.getId());
            list = surveyAssignOrgMapper.selectOrgCaseList(apiReq);
            count= surveyAssignOrgMapper.selectOrgCaseListSize(apiReq);
        }
        else if("examineCaseNum".equals(btnCode)){//审核总积分
            apiReq.put("workTime",staffPerformance.getWorkTime());
            apiReq.put("orgAttr",2);
            apiReq.put("reviewUserId",userId);
            apiReq.put("performanceId",staffPerformance.getId());
            list = surveyAssignOrgMapper.selectOrgCaseList(apiReq);
            count= surveyAssignOrgMapper.selectOrgCaseListSize(apiReq);
        }
        return new ApiResponse<List<ScoreDto>>(ApiMsgEnum.SUCCESS, count, list);

    }

}
