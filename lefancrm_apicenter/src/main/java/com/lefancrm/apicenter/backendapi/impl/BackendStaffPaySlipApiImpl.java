package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendStaffPaySlipApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.SurveyCaseFileDto;
import com.lefancrm.apicenter.dto.SurveyUserClockDto;
import com.lefancrm.apicenter.dto.staff.DdDataDTO;
import com.lefancrm.apicenter.dto.staff.JsDataDTO;
import com.lefancrm.apicenter.dto.staff.OtherDataDTO;
import com.lefancrm.apicenter.dto.staff.StaffPayInfoDTO;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
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
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@ApiService(descript = "工资API")
public class BackendStaffPaySlipApiImpl  extends BaseServiceImpl implements BackendStaffPaySlipApi{
    @Autowired
    private StaffPaySlipMapper staffPaySlipMapper;
    @Autowired
    private StaffPayPersonnelSlipMapper staffPayPersonnelSlipMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private StaffPersonnelInfoMapper staffPersonnelInfoMapper;
    @Autowired
    private StaffWorkingDaysInfoMapper staffWorkingDaysInfoMapper;
    @Autowired
    private StaffBusinessUnitMapper staffBusinessUnitMapper;
    @Autowired
    private StaffCompanyMapper staffCompanyMapper;
    @Autowired
    private StaffOrganMapper staffOrganMapper;
    @Autowired
    private StaffDepartmentMapper staffDepartmentMapper;
    @Autowired
    private StaffJobPostMapper staffJobPostMapper;
    @Value("${survey.role.one}")
    private Integer one;
    @Value("${survey.role.two}")
    private Integer two;
    @Value("${survey.role.three}")
    private Integer three;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private StaffPerformancePersonnelMapper staffPerformancePersonnelMapper;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private StaffPaySlipManagerMapper staffPaySlipManagerMapper;
    @Autowired
    private StaffTeamMapper staffTeamMapper;
    @Autowired
    private BackendStaffApiImpl backendStaffApiImpl;
    @Autowired
    private BackendWechatApiImpl backendWechatApi;
    @Autowired
    private SurveyUserClockMapper surveyUserClockMapper;
    @Value("${staff.users.not.examine}")
    private String examinePersons; //跳过该阶段的审核人员
    @Value("${staff.users.not.send.wechat}")
    private String sendWechatPersons; //不发送微信通知的审核人员
    @Autowired
    private SurveyLevelMapper surveyLevelMapper;
    @Value("${staff.users.zhangyongzhi}")
    private String zhangYongZhi; //安徽张永志，同时可以看到工资条详情
    @Value("${staff.users.zhengyadong}")
    private String zhengYaDong;//安徽郑亚东
    @Autowired
    private StaffBudgetCompanyOrganMapper staffBudgetCompanyOrganMapper;
    @Autowired
    private StaffPostAppellationMapper staffPostAppellationMapper;
    @Autowired
    private StaffPostRankMapper staffPostRankMapper;
    @Autowired
    private StaffBudgetCompanyMapper staffBudgetCompanyMapper;
    @Autowired
    private StaffAuthOrgMapper staffAuthOrgMapper;

    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;

    @ApiMethod(descript = "工资列表",value = "staff-pay-slip-list")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        return null;
    }

    @ApiMethod(descript = "工资详情",value = "staff-pay-slip-info")
    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        Long currentUserId = getCurrentUserId(apiRequest);
        StaffPaySlip staffPaySlip = staffPaySlipMapper.selectByPrimaryKey(id);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        if (staffPaySlip != null) {
            //查询工资单明细是否已经创建 创建则查询工资单 未创建则查询员工
            Map map = new HashMap();
            map.put("staffPaySlipId",staffPaySlip.getId());
            int count = staffPayPersonnelSlipMapper.listSize(map);
            map = new HashMap();
            map.put("workTime",staffPaySlip.getWorkTime());
            map.put("staffPaySlipId",staffPaySlip.getId());
            map.put("searchType",count > 0 ? 1 : 0);//1的时候 条件加上工资单ID不为NULL
            //查询条件
            map.put("companyId",apiRequest.getLong("companyId"));
            map.put("organId",apiRequest.getLong("organId"));
            map.put("departmentId",apiRequest.getLong("departmentId"));
            map.put("teamId",apiRequest.getLong("teamId"));
            map.put("jobPostId",apiRequest.getLong("jobPostId"));
            map.put("socialSecurityCompanyId",apiRequest.getLong("socialSecurityCompanyId"));
            map.put("companyIds",apiRequest.getString("companyIds"));
            map.put("organIds",apiRequest.getString("organIds"));
            map.put("departmentIds",apiRequest.getString("departmentIds"));
            map.put("teamIds",apiRequest.getString("teamIds"));
            map.put("jobPostIds",apiRequest.getString("jobPostIds"));
            map.put("socialSecurityCompanyIds",apiRequest.getString("socialSecurityCompanyIds"));
            map.put("jobNo",apiRequest.getString("jobNo"));
            map.put("realName",apiRequest.getString("realName"));
            map.put("entryTime",apiRequest.getString("entryTime"));
            map.put("entryTimeEnd",apiRequest.getString("entryTimeEnd"));
            map.put("orderType",apiRequest.getString("orderType"));

            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean hrRole = false,ceoRole = false, hrManageRole = false, organManagerRole = false,
                    superiorManagerRole= false,financeRole= false;
            hrRole = isRoleUser(userRoles,100L);
            ceoRole = isRoleUser(userRoles,103L);
            hrManageRole = isRoleUser(userRoles,107L);
            organManagerRole = isRoleUser(userRoles,108L);
            superiorManagerRole = isRoleUser(userRoles,109L);
            financeRole = isRoleUser(userRoles,23L);
            String roleCode = apiRequest.getString("roleCode");

            //机构经理，分管总，查询自己名下机构数据
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
                }
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
            }
            if(hrRole){
                map.put("infoState",null); //查询全部
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
            }
            if(financeRole){
                if("end-step".equals(roleCode)){
                    map.put("infoState",null); //查询全部
                }
            }

            //特殊处理安徽机构，郑亚东（机构经理）和张永志，都可以看到数据
            if (!StringUtils.isEmpty(zhangYongZhi) && zhangYongZhi.equals(currentUserId.toString())) {
                map.put("organManagerUserId",zhengYaDong);
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
            List<StaffPayPersonnelSlip> slips = staffPayPersonnelSlipMapper.slips(map);
            //代表导出
            if("1".equals(apiRequest.getString("downLoad"))){
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,slips);
            }
            staffPaySlip.setSlips(slips);
        }
        if (staffPaySlip.getDdUrl() != null){
            staffPaySlip.setDdUrl(staffPaySlip.getDdUrl().replaceAll("\\\\","/"));
            staffPaySlip.setDdUrlName(staffPaySlip.getDdUrl().substring(staffPaySlip.getDdUrl().lastIndexOf("/") + 1));
        }
        if (staffPaySlip.getJsUrl() != null){
            staffPaySlip.setJsUrlName(staffPaySlip.getJsUrl().replaceAll("\\\\","/"));
            staffPaySlip.setJsUrlName(staffPaySlip.getJsUrl().substring(staffPaySlip.getJsUrl().lastIndexOf("/") + 1));
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPaySlip);
    }

    @ApiMethod(descript = "工资详情",value = "staff-pay-slip-info-key")
    @Override
    public ApiResponse infoKey(ApiRequest apiRequest){
        Long id = apiRequest.getLong("id");
        Long currentUserId = getCurrentUserId(apiRequest);
        StaffPaySlip staffPaySlip = staffPaySlipMapper.selectByPrimaryKey(id);
        //0:待人事专员处理,1:待机构经理处理,2:待分管总审核,3、待人事主管处理；4:待总经理审核,5审核完成
        int state = staffPaySlip.getSlipState().intValue();
        if ( state == 0){
            staffPaySlip.setRoleCode("hr-step");//人事专员审核
        }else if (state == 1){
            staffPaySlip.setRoleCode("organManager-step");//机构经理处理
            //获取当前登录的 机构经理，审核状态
            Map map = new HashMap<>();
            map.put("staffPaySlipId",id);
            map.put("managerType",1);
            map.put("organManagerUserId",currentUserId);
            //特殊处理安徽机构，郑亚东（机构经理）和张永志，都可以看到数据
            if (!StringUtils.isEmpty(zhangYongZhi) && zhangYongZhi.equals(currentUserId.toString())) {
                map.put("organManagerUserId",zhengYaDong);
            }
            StaffPaySlipManager staffPaySlipManager = staffPaySlipManagerMapper.selectByOne(map);
            staffPaySlip.setStaffPaySlipManager(staffPaySlipManager);

            //剩余时间(三天后)
            String timeRemaining = getDistanceTime(staffPaySlip.getHrTime(), 3);
            staffPaySlip.setTimeRemaining(timeRemaining);
        }else if (state == 2){
            staffPaySlip.setRoleCode("superiorManager-step");//待分管总审核
            //获取当前登录的 分管总，审核状态
            Map map = new HashMap<>();
            map.put("staffPaySlipId",id);
            map.put("managerType",2);
            map.put("organManagerUserId",currentUserId);
            StaffPaySlipManager staffPaySlipManager = staffPaySlipManagerMapper.selectByOne(map);
            staffPaySlip.setStaffPaySlipManager(staffPaySlipManager);
        }else if (state == 3){
            staffPaySlip.setRoleCode("hrManage-step");//人事主管
        }else if (state == 4){
            staffPaySlip.setRoleCode("ceo-step");//总部审核
        }else if (state == 5){
            staffPaySlip.setRoleCode("end-step");//完成阶段
        }


        if (staffPaySlip.getDdUrl() != null){
            staffPaySlip.setDdUrl(staffPaySlip.getDdUrl().replaceAll("\\\\","/"));
            staffPaySlip.setDdUrlName(staffPaySlip.getDdUrl().substring(staffPaySlip.getDdUrl().lastIndexOf("/") + 1));
        }
        if (staffPaySlip.getJsUrl() != null){
            staffPaySlip.setJsUrlName(staffPaySlip.getJsUrl().replaceAll("\\\\","/"));
            staffPaySlip.setJsUrlName(staffPaySlip.getJsUrl().substring(staffPaySlip.getJsUrl().lastIndexOf("/") + 1));
        }
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean hrRole = false,ceoRole = false, hrManageRole = false, organManagerRole = false, superiorManagerRole= false, financeRole =false;
        hrRole = isRoleUser(userRoles,100L);
        staffPaySlip.setHrRole(hrRole);
        ceoRole = isRoleUser(userRoles,103L);
        staffPaySlip.setCeoRole(ceoRole);
        hrManageRole = isRoleUser(userRoles,107L);
        staffPaySlip.setHrManageRole(hrManageRole);
        organManagerRole = isRoleUser(userRoles,108L);
        staffPaySlip.setOrganManagerRole(organManagerRole);
        superiorManagerRole = isRoleUser(userRoles,109L);
        staffPaySlip.setSuperiorManagerRole(superiorManagerRole);
        financeRole = isRoleUser(userRoles,101L);//财务（工资条完成状态，财务角色可以查看、导出）
        staffPaySlip.setFinanceRole(financeRole);

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPaySlip);
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId().intValue() == roleId.intValue()){
                return true;
            }
        }
        return false;
    }


    @ApiMethod(descript = "工资操作",value = "staff-pay-slip-operate")
    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        String btnCode = apiRequest.getString("btnCode");
        Long id = apiRequest.getLong("id");
        String stepType = apiRequest.getString("stepType");
        String reason = apiRequest.getString("reason");
        StaffPaySlip staffPaySlip = staffPaySlipMapper.selectByPrimaryKey(id);
        staffPaySlip.setReason(null);//置NULL原因
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(apiRequest));
        if (staffPaySlip == null){
            staffPaySlip = new StaffPaySlip();
        }
        if ("save".equals(btnCode)){
            String workTime = apiRequest.getString("workTime");
            staffPaySlip.setWorkTime(workTime);
            staffPaySlip.setSlipState(0);
            staffPaySlip.setHrId(userInfo.getUserId());
            staffPaySlip.setHrName(userInfo.getUserName());
            staffPaySlip.setCreateBy(userInfo.getUserName());
            staffPaySlip.setCreateTime(new Date());
            staffPaySlip.setUpdateBy(userInfo.getUserName());
            staffPaySlip.setUpdateTime(new Date());
            staffPaySlip.setDeleteFlag(0);
            staffPaySlip.setBackState(0);
            staffPaySlipMapper.insert(staffPaySlip);
        }else if ("hr-step".equals(btnCode)){//人事发起审批 待机构经理审核
            //判断钉钉数据是否已经导入
            Map map = new HashMap();
            map.put("staffPaySlipId",staffPaySlip.getId());
            int count = staffPayPersonnelSlipMapper.listSize(map);
            if (count == 0){
                return new ApiResponse(ApiMsgEnum.STAFF_SLIP_DD_DATA_NULL);
            }
            staffPaySlip.setSlipState(1);
            staffPaySlip.setSlipStateName("待机构经理处理");
            staffPaySlip.setQueryPassword(apiRequest.getString("password"));//密码
            staffPaySlip.setHrId(userInfo.getUserId());
            staffPaySlip.setHrName(userInfo.getUserName());
            staffPaySlip.setHrTime(new Date());
            staffPaySlip.setReason(null);
            staffPaySlip.setBackState(0);
            staffPaySlipMapper.updateByPrimaryKey(staffPaySlip);


            //微信通知：发于机构经理
            map = new HashMap<>();
            map.put("staffPaySlipId",staffPaySlip.getId());
            map.put("managerType",1);
            List<StaffPaySlipManager> list = staffPaySlipManagerMapper.list(map);

            Map<String, Object> msgMap = new HashMap<String, Object>();
            for (StaffPaySlipManager staffPaySlipManager : list) {
                //部分人员不予审核，直接跳过
                Boolean sendWechat  = true;
                if (!StringUtils.isEmpty(examinePersons)) {
                    String [] personIds = examinePersons.split(",");
                    for (String personId : personIds) {
                        if (!StringUtils.isEmpty(personId)){
                            if (staffPaySlipManager.getOrganManagerUserId().intValue() == Integer.parseInt(personId)){
                                staffPaySlipManager.setState(1);
                                staffPaySlipManagerMapper.updateByPrimaryKey(staffPaySlipManager);
                                sendWechat = false;
                                break;
                            }
                        }
                    }
                }

                if(sendWechat){
                    //机构下人数
                    map = new HashMap<>();
                    map.put("organManagerStaffId",staffPaySlipManager.getOrganManagerStaffId());
                    int myCount = staffPersonnelInfoMapper.myPersonelInfosSize(map);

                    msgMap = new HashMap<String, Object>();
                    msgMap.put("title", "工资条审核");
                    msgMap.put("content", "你有一笔工资条待审核，请于3天内处理！");
                    msgMap.put("keyWords", "清单名称：" + staffPaySlip.getWorkTime() + "工资条" + "\n" + "员工人数：" + myCount + "人\n" + "查询密码：" + staffPaySlip.getQueryPassword());
                    backendWechatApi.send(staffPaySlipManager.getOrganManagerUserId(), msgMap);
                }
            }

        }
        else if("organManager-step".equals(btnCode)){ //机构经理通过
            //先更新自己的审核状态
            Map map = new HashMap<>();
            map.put("staffPaySlipId",staffPaySlip.getId());
            map.put("organManagerUserId",userInfo.getUserId());
            //特殊处理安徽机构，郑亚东（机构经理）和张永志，都可以看到数据
            if (!StringUtils.isEmpty(zhangYongZhi) && zhangYongZhi.equals(userInfo.getUserId().toString())) {
                map.put("organManagerUserId",zhengYaDong);
            }
            map.put("managerType",1);
            List<StaffPaySlipManager> list = staffPaySlipManagerMapper.list(map);
            if(list.size() > 0){
                for (StaffPaySlipManager staffPaySlipManager : list) {
                    staffPaySlipManager.setState(1);
                    staffPaySlipManagerMapper.updateByPrimaryKey(staffPaySlipManager);
                }
            }

            //再查询其余的“机构经理状态”，如果自己试最后一个审核通过的人员，则更新整个工资条状态
            map = new HashMap<>();
            map.put("staffPaySlipId",staffPaySlip.getId());
            map.put("managerType",1);
            map.put("state",0);
            list = staffPaySlipManagerMapper.list(map);
            if(list.size() == 0){
                staffPaySlip.setSlipState(2);
                staffPaySlip.setSlipStateName("待分管总处理");

                //微信通知：发于分管总
                map = new HashMap<>();
                map.put("staffPaySlipId",staffPaySlip.getId());
                map.put("managerType",2);
                list = staffPaySlipManagerMapper.list(map);

                Map<String, Object> msgMap = new HashMap<String, Object>();
                for (StaffPaySlipManager staffPaySlipManager : list) {
                    //部分人员不予审核，直接跳过
                    Boolean sendWechat  = true;//此处和上面的不同，因为郑总的账号，在此不能跳过
                    if (!StringUtils.isEmpty(sendWechatPersons)) {
                        String [] personIds = sendWechatPersons.split(",");
                        for (String personId : personIds) {
                            if (!StringUtils.isEmpty(personId)){
                                if (staffPaySlipManager.getOrganManagerUserId().intValue() == Integer.parseInt(personId)){
                                    staffPaySlipManager.setState(1);
                                    staffPaySlipManagerMapper.updateByPrimaryKey(staffPaySlipManager);
                                    sendWechat = false;
                                    break;
                                }
                            }
                        }
                    }

                    if(sendWechat){
                        msgMap = new HashMap<String, Object>();
                        msgMap.put("title", "工资条审核");
                        msgMap.put("content", "你有一笔工资条待审核，请尽快处理！");
                        msgMap.put("keyWords", "清单名称：" + staffPaySlip.getWorkTime() + "工资条");
                        backendWechatApi.send(staffPaySlipManager.getOrganManagerUserId(), msgMap);
                    }
                }
            }
            staffPaySlipMapper.updateByPrimaryKey(staffPaySlip);
        }
        else if("superiorManager-step".equals(btnCode)){ //分管总通过
            //先更新自己的审核状态
            Map map = new HashMap<>();
            map.put("staffPaySlipId",staffPaySlip.getId());
            map.put("organManagerUserId",userInfo.getUserId());
            map.put("managerType",2);
            List<StaffPaySlipManager> list = staffPaySlipManagerMapper.list(map);
            if(list.size() > 0){
                for (StaffPaySlipManager staffPaySlipManager : list) {
                    staffPaySlipManager.setState(1);
                    staffPaySlipManagerMapper.updateByPrimaryKey(staffPaySlipManager);
                }
            }

            //再查询其余的“分管总状态”，如果自己试最后一个审核通过的人员，则更新整个工资条状态
            map = new HashMap<>();
            map.put("staffPaySlipId",staffPaySlip.getId());
            map.put("managerType",2);
            map.put("state",0);
            list = staffPaySlipManagerMapper.list(map);
            if(list.size() == 0){
                staffPaySlip.setSlipState(3);
                staffPaySlip.setSlipStateName("待人事主管处理");

                //发给 人事主管
                map = new HashMap<>();
                map.put("roleId",107L);
                List<BusUserRole> roles = busUserRoleMapper.selectBusInfo(map);

                Map<String, Object> msgMap = new HashMap<String, Object>();
                for (BusUserRole role : roles) {
                    msgMap = new HashMap<String, Object>();
                    msgMap.put("title", "工资条审核");
                    msgMap.put("content", "你有一笔工资条待审核，请尽快处理！");
                    msgMap.put("keyWords", "清单名称：" + staffPaySlip.getWorkTime() + "工资条");
                    backendWechatApi.send(role.getUserId(), msgMap);
                }
            }
            staffPaySlipMapper.updateByPrimaryKey(staffPaySlip);
        }
        else if ("hrManage-step".equals(btnCode)){//人事主管审核  同意
            staffPaySlip.setSlipState(4);
            staffPaySlip.setSlipStateName("待总经理处理");
            staffPaySlip.setHrManageId(userInfo.getUserId());
            staffPaySlip.setHrManageName(userInfo.getUserName());
            staffPaySlip.setHrManageTime(new Date());
            staffPaySlip.setReason(null);
            staffPaySlip.setBackState(0);
            staffPaySlipMapper.updateByPrimaryKey(staffPaySlip);

            //人事主管提交时，清空所有的原因：机构经理意见，分管总意见，总部意见（驳回时，会有意见）
            staffPayPersonnelSlipMapper.updateOption(staffPaySlip.getId());

            //驳回的数据修改
            Map map = new HashMap();
            map.put("staffPaySlipId",staffPaySlip.getId());
            map.put("showState",0);
            map.put("backState",0);
            map.put("oldBackState",1);
            staffPayPersonnelSlipMapper.updateStateByInfo(map);

            //发给总经理
            map = new HashMap<>();
            map.put("roleId",103L);
            List<BusUserRole> roles = busUserRoleMapper.selectBusInfo(map);

            Map<String, Object> msgMap = new HashMap<String, Object>();
            for (BusUserRole role : roles) {
                msgMap = new HashMap<String, Object>();
                msgMap.put("title", "工资条审核");
                msgMap.put("content", "你有一笔工资条待审核，请尽快处理！");
                msgMap.put("keyWords", "清单名称：" + staffPaySlip.getWorkTime() + "工资条");
                backendWechatApi.send(role.getUserId(), msgMap);
            }
        }
        else if ("ceo-step".equals(btnCode)){// 总经理审核  同意or退回
            switch (stepType){
                case "yes" :
                    staffPaySlip.setSlipState(5);
                    staffPaySlip.setSlipStateName("工资条完成");
                    staffPaySlip.setReason(null);
                    staffPaySlip.setBackState(0);
                    staffPaySlip.setGeneralManagerTime(new Date());

                    Map map = new HashMap();
                    map.put("staffPaySlipId",staffPaySlip.getId());
                    map.put("showState",0);
                    map.put("backState",0);
                    staffPayPersonnelSlipMapper.updateStateByInfo(map);

                    //工资条完成后，生成付款管理
                    map = new HashMap<>();
                    map.put("staffPaySlipId",staffPaySlip.getId());
                    map.put("currUserId",userInfo.getUserId());
                    map.put("currUserName",userInfo.getUserName());
                    staffPayPersonnelSlipMapper.generateSurveyPayInfo(map);

                    //同时生成“工资条”与“付款管理”的关联关系表
                    map = new HashMap<>();
                    map.put("staffPaySlipId",staffPaySlip.getId());
                    staffPayPersonnelSlipMapper.generateSurveyPayInfoDetailNew(map);

                    //离职待结算的人员 绩效完成之后 自动更新为已离职
                    staffPersonnelInfoMapper.updateStaffStates(staffPaySlip.getId());
                    //条件1、离职待结算；条件2、合伙的 ，这些人员工资条完成之后 自动更新为已离职
                    staffPersonnelInfoMapper.updateStaffStateByPaySlip(staffPaySlip.getId());

                    //更新案件的结算绩效状态
                    map = new HashMap<>();
                    map.put("workTime",staffPaySlip.getWorkTime());
                    map.put("oldPerformanceState",1);// 结算绩效的状态 （0、未结算；1、结算中；2、已结算）
                    map.put("newPerformanceState",2);
                    map.put("performanceId",staffPaySlip.getId());//工资ID
                    map.put("staffType","payslip");//绩效id
                    surveyRiskCaseInfoMapper.updatePerformanceState(map);
                    break;
                case "no" :
                    staffPaySlip.setSlipState(3);
                    staffPaySlip.setSlipStateName("总经理驳回");
                    staffPaySlip.setReason(reason);
                    staffPaySlip.setBackState(1);

                    map = new HashMap();
                    map.put("staffPaySlipId",staffPaySlip.getId());
                    map.put("showState",1);
                    map.put("backState",1);
                    map.put("oldShowState",0);
                    staffPayPersonnelSlipMapper.updateStateByInfo(map);

                    //发给 人事主管
                    map = new HashMap<>();
                    map.put("roleId",107L);
                    List<BusUserRole> roles = busUserRoleMapper.selectBusInfo(map);

                    map = new HashMap();
                    map.put("staffPaySlipId",staffPaySlip.getId());
                    map.put("showState",1);
                    map.put("backState",1);
                    int count = staffPayPersonnelSlipMapper.listSize(map);
                    Map<String, Object> msgMap = new HashMap<String, Object>();
                    for (BusUserRole role : roles) {
                        msgMap = new HashMap<String, Object>();
                        msgMap.put("title", "工资条驳回");
                        msgMap.put("content", "你有一笔工资条审核驳回，请尽快处理！");
                        msgMap.put("keyWords", "清单名称：" + staffPaySlip.getWorkTime() + "工资条"+ "\n" + "驳回人数："+ count + "人");
                        backendWechatApi.send(role.getUserId(), msgMap);
                    }

                    break;
                case "partReturn" : //部分驳回
                    staffPaySlip.setSlipState(3);
                    staffPaySlip.setSlipStateName("总经理驳回");
                    staffPaySlip.setBackState(2);
                    map = new HashMap();
                    map.put("staffPaySlipId",staffPaySlip.getId());
                    map.put("showState",1);
                    map.put("backState",0);
                    map.put("oldBackState",0);
                    staffPayPersonnelSlipMapper.updateStateByInfo(map);

                    //发给 人事主管
                    map = new HashMap<>();
                    map.put("roleId",107L);
                    roles = busUserRoleMapper.selectBusInfo(map);

                    map = new HashMap();
                    map.put("staffPaySlipId",staffPaySlip.getId());
                    map.put("showState",1);
                    map.put("backState",1);
                    count = staffPayPersonnelSlipMapper.listSize(map);
                    msgMap = new HashMap<String, Object>();
                    for (BusUserRole role : roles) {
                        msgMap = new HashMap<String, Object>();
                        msgMap.put("title", "工资条驳回");
                        msgMap.put("content", "你有一笔工资条审核驳回，请尽快处理！");
                        msgMap.put("keyWords", "清单名称：" + staffPaySlip.getWorkTime() + "工资条"+ "\n" + "驳回人数："+ count + "人");
                        backendWechatApi.send(role.getUserId(), msgMap);
                    }
                    break;
            }
            staffPaySlip.setGeneralManagerId(userInfo.getUserId());
            staffPaySlip.setGeneralManagerName(userInfo.getUserName());
            staffPaySlipMapper.updateByPrimaryKey(staffPaySlip);
        }else if ("slipItemSave".equals(btnCode)){
            String jobNo = apiRequest.getString("jobNo");
            String value = apiRequest.getString("value");
            String colCode = apiRequest.getString("colCode");
            Map map = new HashMap();
            map.put("jobNo",jobNo);
            map.put("staffPaySlipId",staffPaySlip.getId());
            StaffPayPersonnelSlip slip = staffPayPersonnelSlipMapper.slipItem(map);//修改的工资明细对象
            if (slip == null){
                return new ApiResponse(ApiMsgEnum.STAFF_SLIP_DD_DATA_NULL);
            }

            Boolean update = true;
            switch (colCode){
                case "rate" :
                    StaffWorkingDaysInfo staffWorkingDaysInfo = staffWorkingDaysInfoMapper.selectByWorkTime(staffPaySlip.getWorkTime());;//根据workTime 查找出勤天数
                    slip.setRate(convert(value));
                    break;
                case "lateEarlyMoney" : slip.setLateEarlyMoney(convert(value)); break;
                case "absenteeismMoney" : slip.setAbsenteeismMoney(convert(value)); break;
                case "leaveMoney" : slip.setLeaveMoney(convert(value)); break;
                case "sickLeaveTime" : slip.setSickLeaveTime(convert(value)); break;
                case "sickLeaveMoney" : slip.setSickLeaveMoney(convert(value)); break;
                case "conpanyFundMoney" : slip.setConpanyFundMoney(convert(value)); break;
                case "personalFundMoney" : slip.setPersonalFundMoney(convert(value)); break;
                case "companyPensionBenefits" : slip.setCompanyPensionBenefits(convert(value)); break;
                case "personalPensionBenefits" : slip.setPersonalPensionBenefits(convert(value)); break;
                case "companyMedicalInsurance" : slip.setCompanyMedicalInsurance(convert(value)); break;
                case "personalMedicalInsurance" : slip.setPersonalMedicalInsurance(convert(value)); break;
                case "companyUnemploymentInsurance" : slip.setCompanyUnemploymentInsurance(convert(value)); break;
                case "personalUnemploymentInsurance" : slip.setPersonalUnemploymentInsurance(convert(value)); break;
                case "companyBirthInsurance" : slip.setCompanyBirthInsurance(convert(value)); break;
                case "companyInjuryInsurance" : slip.setCompanyInjuryInsurance(convert(value)); break;
                case "overtimePay" : slip.setOvertimePay(convert(value)); break;
                case "otherPay" : slip.setOtherPay(convert(value)); break;
//                case "individualTax" : slip.setIndividualTax(convert(value)); break;
                case "remarks" : slip.setRemarks(value); break;
                case "realWorkingDays" :slip.setRealWorkingDays(convert(value));break;//实际出勤天数
                case "officeSubsidies" :slip.setOfficeSubsidies(convert(value));break;//办公补贴
                case "companySickSubsidy" :slip.setCompanySickSubsidy(convert(value));break;//大病补助公司部分
                case "personalSickSubsidy" :slip.setPersonalSickSubsidy(convert(value));break;//大病补助个人部分
                case "disabilityInsurance" :slip.setDisabilityInsurance(convert(value));break;//残保金
                case "serviceFee" :slip.setServiceFee(convert(value));break;//服务费
                case "individualTaxChange" :slip.setIndividualTaxChange(convert(value));
                    update = false;
                    break;//个税调整
                case "socialRemark" : slip.setSocialRemark(value); break;//社保备注
                case "otherCutPay" : slip.setOtherCutPay(convert(value)); break;//其他扣款
                case "otherCutRemarks" : slip.setOtherCutRemarks(value); break;//其他扣款备注
                case "welfarePay" :slip.setWelfarePay(convert(value));break;//员工福利
                case "welfareRemark" :slip.setWelfareRemark(value);break;//员工福利备注
                case "quitCost" : slip.setQuitCost(convert(value));// 离职成本

            }
            if (!("remarks".equals(colCode) || "socialRemark".equals(colCode) || "otherCutRemarks".equals(colCode) || "welfareRemark".equals(colCode))){
                StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByJobNo(slip.getJobNo());//根据jobNo查询员工对象
                slip = returnSlip(staffPersonnelInfo,slip,update);//计算各种小计
            }
            staffPayPersonnelSlipMapper.updateByPrimaryKey(slip);
            staffPaySlip.setStaffPayPersonnelSlip(slip);//返回给前端
        }
        else if ("9999".equals(btnCode)) {
            staffPaySlip.setDeleteFlag(1);
            staffPaySlipMapper.updateByPrimaryKey(staffPaySlip);
            //同步删除明细
            staffPayPersonnelSlipMapper.deleteByStaffPaySlipId(staffPaySlip.getId());

            //同时释放案件状态
            Map map = new HashMap<>();
            map.put("workTime",staffPaySlip.getWorkTime());
            map.put("oldPerformanceState",1);// 结算绩效的状态 （0、未结算；1、结算中；2、已结算）
            map.put("newPerformanceState",0);
            map.put("performanceId",staffPaySlip.getId());//绩效id
            map.put("delStaffPerformanceId",1);//删除绩效id
            map.put("staffType","payslip");//绩效id
            surveyRiskCaseInfoMapper.updatePerformanceState(map);
        }else if("passWord".equals(btnCode)){ //校准密码
            String passWord = apiRequest.getString("queryPassword");
            if(passWord.equals(staffPaySlip.getQueryPassword())){
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPaySlip);
            }else{
                return new ApiResponse(ApiMsgEnum.FAIL,null,null);
            }
        }
        /*else if("back".equals(btnCode)){ //标记退回
            StaffPayPersonnelSlip staffPayPersonnelSlip = staffPayPersonnelSlipMapper.selectByPrimaryKey(apiRequest.getLong("staffPayPersonnelSlipId"));
            staffPayPersonnelSlip.setShowState(1);
            staffPayPersonnelSlip.setBackState(1);
            staffPayPersonnelSlip.setBackReason(apiRequest.getString("backReason"));
            staffPayPersonnelSlipMapper.updateByPrimaryKey(staffPayPersonnelSlip);
            staffPaySlip.setStaffPayPersonnelSlip(staffPayPersonnelSlip);
        }else if("removeBack".equals(btnCode)){ //取消标记退回
            StaffPayPersonnelSlip staffPayPersonnelSlip = staffPayPersonnelSlipMapper.selectByPrimaryKey(apiRequest.getLong("staffPayPersonnelSlipId"));
            staffPayPersonnelSlip.setShowState(0);
            staffPayPersonnelSlip.setBackState(0);
            staffPayPersonnelSlip.setBackReason(null);
            staffPayPersonnelSlipMapper.updateByPrimaryKey(staffPayPersonnelSlip);
            staffPaySlip.setStaffPayPersonnelSlip(staffPayPersonnelSlip);
        }*/
        else if("back".equals(btnCode)){ //填写意见
            String backReason = apiRequest.getString("backReason");
            StaffPayPersonnelSlip staffPayPersonnelSlip = staffPayPersonnelSlipMapper.selectByPrimaryKey(apiRequest.getLong("staffPayPersonnelSlipId"));

            String roleCode = apiRequest.getString("roleCode");
            if("organManager-step".equals(roleCode)){
                staffPayPersonnelSlip.setOrganOpinion(backReason);
            }else if("superiorManager-step".equals(roleCode)){
                staffPayPersonnelSlip.setSuperiorOpinion(backReason);
            }else if("ceo-step".equals(roleCode)){
                staffPayPersonnelSlip.setBossOpinion(backReason);
                staffPayPersonnelSlip.setShowState(1);
                staffPayPersonnelSlip.setBackState(1);
            }
            staffPayPersonnelSlipMapper.updateByPrimaryKey(staffPayPersonnelSlip);
            staffPaySlip.setStaffPayPersonnelSlip(staffPayPersonnelSlip);
        }else if("removeBack".equals(btnCode)){ //取消意见
            StaffPayPersonnelSlip staffPayPersonnelSlip = staffPayPersonnelSlipMapper.selectByPrimaryKey(apiRequest.getLong("staffPayPersonnelSlipId"));
            String roleCode = apiRequest.getString("roleCode");
            if("organManager-step".equals(roleCode)){
                staffPayPersonnelSlip.setOrganOpinion(null);
            }else if("superiorManager-step".equals(roleCode)){
                staffPayPersonnelSlip.setSuperiorOpinion(null);
            }else if("ceo-step".equals(roleCode)){
                staffPayPersonnelSlip.setBossOpinion(null);
                staffPayPersonnelSlip.setShowState(0);
                staffPayPersonnelSlip.setBackState(0);
            }
            staffPayPersonnelSlipMapper.updateByPrimaryKey(staffPayPersonnelSlip);
            staffPaySlip.setStaffPayPersonnelSlip(staffPayPersonnelSlip);
        }else if("sendWechat".equals(btnCode)){ //发送工资条
            Map map = new HashMap<>();
            map.put("staffPaySlipId",id);
            List<StaffPayPersonnelSlip> roles = staffPayPersonnelSlipMapper.allList(map);
            Map<String, Object> msgMap = new HashMap<String, Object>();
            for (StaffPayPersonnelSlip role : roles) {
                msgMap = new HashMap<String, Object>();
                msgMap.put("title", "本期工资条");
                msgMap.put("content", "你有一笔工资条，请尽快查看！");
                msgMap.put("keyWords", "工资条月份：" + staffPaySlip.getWorkTime());
                String path = "pages/user/salarySheet/index?id="+role.getId()+"&workTime="+staffPaySlip.getWorkTime();
                msgMap.put("path", path);
                backendWechatApi.send(role.getUserId(), msgMap);
            }
            staffPaySlip.setIsSend(1);
            staffPaySlipMapper.updateByPrimaryKey(staffPaySlip);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffPaySlip);
    }

    /**
     * 提交审核     非全部提交更新 退回状态   全部提交更新 显示状态
     * @param btnCode
     * @return
     */
    private int  updBackState(String btnCode,Long staffPaySlipId){
        Map<String,Object> map =  new HashMap<String,Object>();
        if ("hr-step".equals(btnCode)){//人事提交
            map.put("setValue",3);
            map.put("setCondition",1);
        }else if ("".equals(btnCode)){//主管提交
            map.put("setValue",5);
            map.put("setCondition",3);
        }else if ("".equals(btnCode)){//事业部提交
            map.put("setValue",7);
            map.put("setCondition",5);
            map.put("事业部ID","事业部ID");//事业部ID
        }else if ("".equals(btnCode)){//总部提交
            map.put("setValue",0);
        }
        int line = 0;
        String sql = "update staff_pay_personnel_slip set back_state = setValue where back_state = setCondition";
        if (line == 0){//说明是全部提交
            sql = "update staff_pay_personnel_slip set back_state = 0,show_state = 0";
        }
        return line;
    }

    /**
     * 驳回   更新退回状态
     * @param btnCode
     * @return
     */
    private int updShowState(String btnCode,String ids){
        Map<String,Object> map =  new HashMap<String,Object>();
        if ("".equals(btnCode)){//主管驳回
            map.put("setValue",1);
        }else if ("".equals(btnCode)){//事业部驳回
            map.put("setValue",3);
        }else if ("".equals(btnCode)){//总部驳回
            map.put("setValue",5);
        }
        String sql1 = "update staff_pay_personnel_slip set back_state = setValue where ids in ()";//选中的驳回

        String sql2 = "update staff_pay_personnel_slip set show_state = 1 where ids not in ()";//未选中的提交
        return 0;
    }



    private Double convert(String value){
        if (!StringUtils.isEmpty(value)) {
            return Double.parseDouble(value);
        }
        return null;
    }


    @ApiMethod(descript = "导入数据",value = "staff-pay-slip-export")
    @Override
    public ApiResponse exportData(ApiRequest apiRequest) {
        int count = 0;
        String errorMessage = "";
        int errorCount = 0;
        String exportType = apiRequest.getString("exportType");
        Long staffPaySlipId = apiRequest.getLong("staffPaySlipId");
        StaffPaySlip staffPaySlip = staffPaySlipMapper.selectByPrimaryKey(staffPaySlipId);
        String workTime = staffPaySlip.getWorkTime();
        if ("ddData".equals(exportType)){//钉钉数据导入
            //删除历史数据
            //staffPayPersonnelSlipMapper.updateDeleteFlagByStaffPaySlipId(staffPaySlip.getId());

            StaffWorkingDaysInfo staffWorkingDaysInfo = staffWorkingDaysInfoMapper.selectByWorkTime(workTime);;//根据workTime 查找出勤天数
            Double workingDays = staffWorkingDaysInfo.getWrokingDays();
            String ddData = apiRequest.getString("ddData");
            List<DdDataDTO> ddDatas = JSONArray.parseArray(ddData, DdDataDTO.class);
            for (DdDataDTO data : ddDatas) {
                Map<String,Object> map = new HashMap<>();
                map.put("staffPaySlipId",staffPaySlipId);
                map.put("jobNo",data.getJobNo());
                StaffPayPersonnelSlip slipInfo = staffPayPersonnelSlipMapper.slipItem(map);

                //StaffPayPersonnelSlip staffPayPersonnelSlip = ddDataToSlip(data,workingDays,rate);
                //数据有误，不予处理，并返回错误信息
                Map mapT =  ddDataToSlip(data,workingDays,slipInfo,workTime,getCurrentUserId(apiRequest));
                if((Boolean) mapT.get("isReturn")){
                    errorMessage = errorMessage + mapT.get("errorMessage");
                    errorCount ++;
                    continue;
                }
                StaffPayPersonnelSlip staffPayPersonnelSlip  = (StaffPayPersonnelSlip)mapT.get("slip");
                staffPayPersonnelSlip.setStaffPaySlipId(staffPaySlipId);
                staffPayPersonnelSlip.setDeleteFlag(0);
                //更新或者保存
                if(slipInfo != null){
                    staffPayPersonnelSlip.setId(slipInfo.getId());
                    staffPayPersonnelSlipMapper.updateByPrimaryKeySelective(staffPayPersonnelSlip);
                }else{
                    staffPayPersonnelSlipMapper.insert(staffPayPersonnelSlip);
                }
            }
            count = ddDatas.size();
            staffPaySlip.setDdUrl(apiRequest.getString("url"));
            if (staffPaySlip.getDdUrl() != null){
                staffPaySlip.setDdUrl(staffPaySlip.getDdUrl().replaceAll("\\\\","/"));
                staffPaySlip.setDdUrlName(staffPaySlip.getDdUrl().substring(staffPaySlip.getDdUrl().lastIndexOf("/") + 1));
            }

        } else if ("jsData".equals(exportType)){//缴税数据导入
            String jsData = apiRequest.getString("jsData");
            List<JsDataDTO> jsDatas = JSONArray.parseArray(jsData,JsDataDTO.class);
            for (JsDataDTO data : jsDatas) {
                //根据工资表ID +  jobNo查询工资明细数据
                StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByIdCard(data.getCardNo());
                if(staffPersonnelInfo == null){
                    errorCount ++ ;
                    errorMessage = errorMessage+"身份号"+data.getCardNo()+"有误；";
                    continue;
                }
                Map map = new HashMap();
                map.put("jobNo", staffPersonnelInfo.getJobNo());
                map.put("staffPaySlipId",staffPaySlip.getId());
                StaffPayPersonnelSlip slip = staffPayPersonnelSlipMapper.slipItem(map);

                //数据有误，不予修改，并返回错误信息
                Map mapT =  jsDataToSlip(slip,data);
                if((Boolean) mapT.get("isReturn")){
                    errorMessage = errorMessage + mapT.get("errorMessage");
                    errorCount ++;
                    continue;
                }
                staffPayPersonnelSlipMapper.updateByPrimaryKey(slip);
            }
            count = jsDatas.size();
            staffPaySlip.setJsUrl(apiRequest.getString("url"));
            if (staffPaySlip.getJsUrl() != null){
                staffPaySlip.setJsUrl(staffPaySlip.getJsUrl().replaceAll("\\\\","/"));
                staffPaySlip.setJsUrlName(staffPaySlip.getJsUrl().substring(staffPaySlip.getJsUrl().lastIndexOf("/") + 1));
            }
        } else if ("otherData".equals(exportType)){
            String otherData = apiRequest.getString("otherData");
            List<OtherDataDTO> otherDatas = JSONArray.parseArray(otherData,OtherDataDTO.class);
            for (OtherDataDTO data : otherDatas) {
                //根据工资表ID +  jobNo查询工资明细数据
                Map map = new HashMap();
                map.put("jobNo",data.getJobNo());
                map.put("staffPaySlipId",staffPaySlip.getId());
                StaffPayPersonnelSlip slip = staffPayPersonnelSlipMapper.slipItem(map);
                if(slip != null){
                    slip.setOvertimePay(data.getOverTimeMoney());
                    slip.setOtherPay(data.getOtherMoney());
                    StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByJobNo(slip.getJobNo());//根据jobNo查询员工对象
                    slip.setRealWages(realWages(staffPersonnelInfo,slip));
                    staffPayPersonnelSlipMapper.updateByPrimaryKey(slip);
                }
            }
            count = otherDatas.size();
        }
        staffPaySlipMapper.updateByPrimaryKey(staffPaySlip);

        Map map = new HashMap();
        /*String returnErrorMessage = "";
        if(!"".equals(errorMessage)){
            returnErrorMessage = "导入失败"+errorCount+"条数据，失败原因："+errorMessage;
        }
        map.put("message","成功导入" + (count-errorCount) + "条数据。"+ returnErrorMessage);*/
        map.put("data", staffPaySlip);
        map.put("ddUrl", staffPaySlip.getDdUrl());
        map.put("ddUrlName", staffPaySlip.getDdUrlName());

        map.put("successCount",count-errorCount);
        map.put("errorCount",errorCount);
        map.put("errorMessage",errorMessage);

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }

    /**
     * 转换钉钉数据
     * @param data
     * @param workingDays
     * @return
     */
    private Map ddDataToSlip(DdDataDTO data,Double workingDays,StaffPayPersonnelSlip slip,String workTime,Long currentUserId){
        Pattern pattern = Pattern.compile("^(\\-|\\+)?[0-9]+(.[0-9]{1,9})?$");
        Map messageMap = new HashMap();
        String errorMessage = "工号为"+data.getJobNo()+":";
        Boolean isReturn = false;
        if(slip == null){
            errorMessage = errorMessage+"不存在工资条中；"; isReturn = true;
            messageMap.put("errorMessage",errorMessage);
            messageMap.put("isReturn",isReturn);
            return messageMap;

        }

        slip.setJobNo(data.getJobNo());
        slip.setWorkingDays(workingDays);

        StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByJobNo(slip.getJobNo());//根据jobNo查询员工对象
        if(staffPersonnelInfo == null){
            errorMessage = errorMessage+"工号不存在；"; isReturn = true;
            messageMap.put("errorMessage",errorMessage);
            messageMap.put("isReturn",isReturn);
            return messageMap;
        }

        slip.setStaffPersonnelId(staffPersonnelInfo.getId());
        //迟到天数
        Boolean isNumber = pattern.matcher(data.getLateDays()).matches();
        if(!isNumber){
            errorMessage = errorMessage + "迟到天数有误；";isReturn = true;
            messageMap.put("errorMessage",errorMessage);
            messageMap.put("isReturn",isReturn);
            return messageMap;
        }
        int lateDays = Math.abs(convertDays(data.getLateDays()));
        Double money = 0D;
        Double A = staffPersonnelInfo.getBasePay();
        Double D = workingDays;
        if (lateDays == 1){
            money = 50D;
        }else if (lateDays > 1 && lateDays <=2){
            money = 100D;
        }else if (lateDays > 2 && lateDays <= 3){
            money = A / D;
        }else if (lateDays > 3 && lateDays <5){
            money = 2 * (A / D);
        }else if (lateDays >=5 && lateDays <10){
            money = 3 * (A / D);
        }else if (lateDays >= 10){
            money = 0.5 * A;
        }
        slip.setLateEarlyNum(lateDays);
        slip.setLateEarlyMoney(money.intValue() != 0 ? -DecimalUtil.twoDecimalTOFourFromFive(money) : 0);

        //矿工天数
        isNumber = pattern.matcher(data.getAbsentDays()).matches();
        if(!isNumber){
            errorMessage = errorMessage + "旷工天数有误；";isReturn = true;
            messageMap.put("errorMessage",errorMessage);
            messageMap.put("isReturn",isReturn);
            return messageMap;
        }
        //
        int absentDays = (int) Math.floor(Double.parseDouble(data.getAbsentDays()));//向下取整
        // 2021年10月18日  ，工资条绩效不根据打卡情况计算矿工。
        if (false){
            if(staffPersonnelInfo!=null){
//            if("调查专员".equals(staffPersonnelInfo.getJobPost())){
                if (staffPersonnelInfo.getJobPostId().intValue() == 57){
                    int year = Integer.valueOf(workTime.substring(0, 4));
                    int month = Integer.valueOf(workTime.substring(5, 7));

                    Date startTime = staffPersonnelInfo.getEntryTime();
                    Date endTime = staffPersonnelInfo.getQuitTime();
                    //入职时间是否在本月
                    if(startTime != null){
                        if(!isThisTime(startTime,workTime)){
                            startTime = DateUtils.getFirstDayOfYearMonth(year, month);//获取当月第一天
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
                    int day= 0;
                    //先获取应上班的具体天
                    List<Date> list = GetWorkDay.calLeaveDaysList(startTime, endTime, 1);
                    //获取“足迹打卡”，旷工天数
                    Map map = new HashMap<>();
                    map.put("dateTime",workTime);
                    map.put("userId",staffPersonnelInfo.getUserId());
                    List<SurveyUserClockDto> userClocks = surveyUserClockMapper.selectPunchTheClock(map);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    for (Date date : list) {
                        String s = formatter.format(date);
                        Boolean have = false;
                        for (SurveyUserClockDto userClock : userClocks) {
                            if(s.equals(formatter.format(userClock.getClockTime())) ){
                                have = true;
                                continue;
                            }
                        }
                        if(!have){
                            day = day + 1;
                        }
                    }
                    absentDays =day;
                }
            }
        }

        money = 0D;
        A = staffPersonnelInfo.getBasePay();
        D = workingDays;
        if (absentDays == 1){
            money = 5 * A / D;
        }else if (absentDays > 1 && absentDays <=2){
            money = A * 0.5;
        }else if (absentDays > 2){
            money = A;
        }
        slip.setAbsenteeismNum(absentDays);
        slip.setAbsenteeismMoney(money.intValue() != 0 ? -DecimalUtil.twoDecimalTOFourFromFive(money) : 0);
        //

        //事假
        money = 0D;
        isNumber = pattern.matcher(data.getLeaveDays()).matches();
        if(!isNumber){
            errorMessage = errorMessage + "事假天数有误；";isReturn = true;
            messageMap.put("errorMessage",errorMessage);
            messageMap.put("isReturn",isReturn);
            return messageMap;
        }
        Double leaveDays = Math.abs(convertDouble(data.getLeaveDays()));
        slip.setLeaveNum(leaveDays);
        money = (A / D / 7.5) * leaveDays;
        slip.setLeaveMoney(money.intValue() != 0 ? -DecimalUtil.twoDecimalTOFourFromFive(money) : 0);

        //病假
        money = 0D;
        isNumber = pattern.matcher(data.getSickTIme()).matches();
        if(!isNumber){
            errorMessage = errorMessage + "病假天数有误；";isReturn = true;
            messageMap.put("errorMessage",errorMessage);
            messageMap.put("isReturn",isReturn);
            return messageMap;
        }
        Double sickLeaveTime = Math.abs(convertDouble(data.getSickTIme()));
        slip.setSickLeaveTime(sickLeaveTime);
//        money = (A / D / 7.5) * sickLeaveTime * 0.3;
        //2025年3月3日  病假 按天数计算;
        money = (A / D) * sickLeaveTime * 0.3;
        slip.setSickLeaveMoney(money.intValue() != 0 ? -DecimalUtil.twoDecimalTOFourFromFive(money) : 0);

        isNumber = pattern.matcher(data.getOvertimePay()).matches();
        if(isNumber){
            slip.setOvertimePay(convertDouble(data.getOvertimePay()));
        }else{
            errorMessage = errorMessage + "加班工资有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getOtherPay()).matches();
        if(isNumber){
            money= Math.abs(convertDouble(data.getOtherPay()));
            slip.setOtherPay(money);
        }else{
            errorMessage = errorMessage + "其他补发有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getIndividualTaxChange()).matches();
        if(isNumber){
            slip.setIndividualTaxChange(convertDouble(data.getIndividualTaxChange()));
        }else{
            errorMessage = errorMessage + "个税调整有误；";isReturn = true;
        }
        slip.setRemarks(data.getRemarks());

        isNumber = pattern.matcher(data.getConpanyFundMoney()).matches();
        if(isNumber){
            slip.setConpanyFundMoney(-convertDouble(data.getConpanyFundMoney()));
        }else{
            errorMessage = errorMessage + "公积金公司部分有误；";isReturn = true;
        }
        isNumber = pattern.matcher(data.getPersonalFundMoney()).matches();
        if(isNumber){
            slip.setPersonalFundMoney(-convertDouble(data.getPersonalFundMoney()));
        }else{
            errorMessage = errorMessage + "公积金个人部分有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getCompanyPensionBenefits()).matches();
        if(isNumber){
            slip.setCompanyPensionBenefits(-convertDouble(data.getCompanyPensionBenefits()));
        }else{
            errorMessage = errorMessage + "养老保险公司部分有误；";isReturn = true;
        }
        isNumber = pattern.matcher(data.getPersonalPensionBenefits()).matches();
        if(isNumber){
            slip.setPersonalPensionBenefits(-convertDouble(data.getPersonalPensionBenefits()));
        }else{
            errorMessage = errorMessage + "养老保险个人部分有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getCompanyMedicalInsurance()).matches();
        if(isNumber){
            slip.setCompanyMedicalInsurance(-convertDouble(data.getCompanyMedicalInsurance()));
        }else{
            errorMessage = errorMessage + "医疗保险公司部分有误；";isReturn = true;
        }
        isNumber = pattern.matcher(data.getPersonalMedicalInsurance()).matches();
        if(isNumber){
            slip.setPersonalMedicalInsurance(-convertDouble(data.getPersonalMedicalInsurance()));
        }else{
            errorMessage = errorMessage + "医疗保险个人部分有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getCompanyUnemploymentInsurance()).matches();
        if(isNumber){
            slip.setCompanyUnemploymentInsurance(-convertDouble(data.getCompanyUnemploymentInsurance()));
        }else{
            errorMessage = errorMessage + "失业保险公司部分有误；";isReturn = true;
        }
        isNumber = pattern.matcher(data.getPersonalUnemploymentInsurance()).matches();
        if(isNumber){
            slip.setPersonalUnemploymentInsurance(-convertDouble(data.getPersonalUnemploymentInsurance()));
        }else{
            errorMessage = errorMessage + "失业保险个人部分有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getCompanyBirthInsurance()).matches();
        if(isNumber){
            slip.setCompanyBirthInsurance(-convertDouble(data.getCompanyBirthInsurance()));
        }else{
            errorMessage = errorMessage + "生育险公司部分有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getCompanyInjuryInsurance()).matches();
        if(isNumber){
            slip.setCompanyInjuryInsurance(-convertDouble(data.getCompanyInjuryInsurance()));
        }else{
            errorMessage = errorMessage + "工伤保险公司部分有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getCompanySickSubsidy()).matches();
        if(isNumber){
            slip.setCompanySickSubsidy(-convertDouble(data.getCompanySickSubsidy()));
        }else{
            errorMessage = errorMessage + "大病补助公司部分有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getPersonalSickSubsidy()).matches();
        if(isNumber){
            slip.setPersonalSickSubsidy(-convertDouble(data.getPersonalSickSubsidy()));
        }else{
            errorMessage = errorMessage + "大病补助个人部分有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getDisabilityInsurance()).matches();
        if(isNumber){
            slip.setDisabilityInsurance(-convertDouble(data.getDisabilityInsurance()));
        }else{
            errorMessage = errorMessage + "残保金有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getServiceFee()).matches();
        if(isNumber){
            slip.setServiceFee(-convertDouble(data.getServiceFee()));
        }else{
            errorMessage = errorMessage + "服务费有误；";isReturn = true;
        }

        slip.setSocialRemark(data.getSocialRemark());//社保备注

        isNumber = pattern.matcher(data.getOtherCutPay()).matches();
        if(isNumber){
            money= Math.abs(convertDouble(data.getOtherCutPay()));
            slip.setOtherCutPay(-money);
        }else{
            errorMessage = errorMessage + "其他扣款有误；";isReturn = true;
        }
        slip.setOtherCutRemarks(data.getOtherCutRemarks());//社保备注

        isNumber = pattern.matcher(data.getWelfarePay()).matches(); //员工福利
        if(isNumber){
            money= Math.abs(convertDouble(data.getWelfarePay()));
            slip.setWelfarePay(money);
        }else{
            errorMessage = errorMessage + "员工福利有误；";isReturn = true;
        }
        slip.setWelfareRemark(data.getWelfareRemark());//员工福利备注


        isNumber = pattern.matcher(data.getButie()).matches(); //补贴
        if(isNumber){
            money= Math.abs(convertDouble(data.getButie()));
            slip.setOfficeSubsidies(money);
        }else{
            errorMessage = errorMessage + "补贴有误；";isReturn = true;
        }

        //各种计算在内
        slip = returnSlip(staffPersonnelInfo, slip,true);


        messageMap.put("slip",slip);
        messageMap.put("errorMessage",errorMessage);
        messageMap.put("isReturn",isReturn);
        return messageMap;
    }

    /**
     * 转换缴税数据
     * @param slip
     * @param data
     * @return
     */
    private Map jsDataToSlip(StaffPayPersonnelSlip slip,JsDataDTO data){
        //判断是否是数字型
        Pattern pattern = Pattern.compile("^(\\-|\\+)?[0-9]+(.[0-9]{1,9})?$");
        Map messageMap = new HashMap();
        String errorMessage = "身份证"+data.getCardNo()+":";
        Boolean isReturn = false;
        if(slip ==null){
            errorMessage = errorMessage + "不存在工资条中；";isReturn = true;
            messageMap.put("slip",slip);
            messageMap.put("errorMessage",errorMessage);
            messageMap.put("isReturn",isReturn);
            return messageMap;
        }

        StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByJobNo(slip.getJobNo());//根据jobNo查询员工对象

        Boolean isNumber = pattern.matcher(data.getConpanyFundMoney()).matches();
        if(isNumber){
            slip.setConpanyFundMoney(-convertDouble(data.getConpanyFundMoney()));
        }else{
            errorMessage = errorMessage + "公积金公司部分有误；";isReturn = true;
        }
        isNumber = pattern.matcher(data.getPersonalFundMoney()).matches();
        if(isNumber){
            slip.setPersonalFundMoney(-convertDouble(data.getPersonalFundMoney()));
        }else{
            errorMessage = errorMessage + "公积金个人部分有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getCompanyPensionBenefits()).matches();
        if(isNumber){
            slip.setCompanyPensionBenefits(-convertDouble(data.getCompanyPensionBenefits()));
        }else{
            errorMessage = errorMessage + "养老保险公司部分有误；";isReturn = true;
        }
        isNumber = pattern.matcher(data.getPersonalPensionBenefits()).matches();
        if(isNumber){
            slip.setPersonalPensionBenefits(-convertDouble(data.getPersonalPensionBenefits()));
        }else{
            errorMessage = errorMessage + "养老保险个人部分有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getCompanyMedicalInsurance()).matches();
        if(isNumber){
            slip.setCompanyMedicalInsurance(-convertDouble(data.getCompanyMedicalInsurance()));
        }else{
            errorMessage = errorMessage + "医疗保险公司部分有误；";isReturn = true;
        }
        isNumber = pattern.matcher(data.getPersonalMedicalInsurance()).matches();
        if(isNumber){
            slip.setPersonalMedicalInsurance(-convertDouble(data.getPersonalMedicalInsurance()));
        }else{
            errorMessage = errorMessage + "医疗保险个人部分有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getCompanyUnemploymentInsurance()).matches();
        if(isNumber){
            slip.setCompanyUnemploymentInsurance(-convertDouble(data.getCompanyUnemploymentInsurance()));
        }else{
            errorMessage = errorMessage + "失业保险公司部分有误；";isReturn = true;
        }
        isNumber = pattern.matcher(data.getPersonalUnemploymentInsurance()).matches();
        if(isNumber){
            slip.setPersonalUnemploymentInsurance(-convertDouble(data.getPersonalUnemploymentInsurance()));
        }else{
            errorMessage = errorMessage + "失业保险个人部分有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getCompanyBirthInsurance()).matches();
        if(isNumber){
            slip.setCompanyBirthInsurance(-convertDouble(data.getCompanyBirthInsurance()));
        }else{
            errorMessage = errorMessage + "生育险公司部分有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getCompanyInjuryInsurance()).matches();
        if(isNumber){
            slip.setCompanyInjuryInsurance(-convertDouble(data.getCompanyInjuryInsurance()));
        }else{
            errorMessage = errorMessage + "工伤保险公司部分有误；";isReturn = true;
        }

//        slip.setOvertimePay(convertDouble(data.getOvertimePay()));
//        slip.setOtherPay(convertDouble(data.getOtherPay()));
//        slip.setIndividualTax(convertDouble(data.getIndividualTax()));

        /*isNumber = pattern.matcher(data.getCompanySickSubsidy()).matches();
        if(isNumber){
            slip.setCompanySickSubsidy(-Math.abs(convertDouble(data.getCompanySickSubsidy())));
        }else{
            errorMessage = errorMessage + "大病补助公司部分有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getPersonalSickSubsidy()).matches();
        if(isNumber){
            slip.setPersonalSickSubsidy(-Math.abs(convertDouble(data.getPersonalSickSubsidy())));
        }else{
            errorMessage = errorMessage + "大病补助个人部分有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getDisabilityInsurance()).matches();
        if(isNumber){
            slip.setDisabilityInsurance(-Math.abs(convertDouble(data.getDisabilityInsurance())));
        }else{
            errorMessage = errorMessage + "残保金部分有误；";isReturn = true;
        }

        isNumber = pattern.matcher(data.getServiceFee()).matches();
        if(isNumber){
            slip.setServiceFee(-Math.abs(convertDouble(data.getServiceFee())));
        }else{
            errorMessage = errorMessage + "服务费部分有误；";isReturn = true;
        }

        slip.setSocialRemark(data.getSocialRemark());//社保备注*/
        //实发工资
//        slip.setRealWages(realWages(staffPersonnelInfo,slip));
        slip = returnSlip(staffPersonnelInfo, slip,true);

        messageMap.put("slip",slip);
        messageMap.put("errorMessage",errorMessage);
        messageMap.put("isReturn",isReturn);
        return messageMap;
    }

    private int convertDays(String days){
        return  days == null || "".equals(days) ? 0 : Integer.parseInt(days);
    }
    private Double convertDouble(String time){
        return time == null || "".equals(time) ? 0D : Double.valueOf(time);
    }
    private Double realWages(StaffPersonnelInfo staffPersonnelInfo,StaffPayPersonnelSlip slip){
        Field[] declaredFields = StaffPayPersonnelSlip.class.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                Object value = field.get(slip);
                if (value == null){
                    if (Long.class.equals(field.getType())){
                        field.set(slip,0L);
                    }
                    if (Double.class.equals(field.getType())){
                        field.set(slip,0D);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Double A = staffPersonnelInfo.getBasePay() * slip.getRate();
        Double money =  A + slip.getLateEarlyMoney() + slip.getAbsenteeismMoney() + slip.getLeaveMoney() + slip.getSickLeaveMoney()
                + slip.getPersonalFundMoney() + slip.getPersonalPensionBenefits() + slip.getPersonalMedicalInsurance()
                + slip.getPersonalUnemploymentInsurance() + slip.getOvertimePay() + slip.getOtherPay() + slip.getIndividualTax();
        return DecimalUtil.twoDecimalTOFourFromFive(money);
    }


    @ApiMethod(descript = "导入员工数据",value = "staff-personnel-info-export")
    @Override
    public ApiResponse exportPersonnelInfoData(ApiRequest apiRequest) {
        int count = 0;
        String exportType = apiRequest.getString("exportType");
        String errorMessage = "";
        int errorCount = 0;
        if ("personnelInfo".equals(exportType)){
            //当前登录人
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(apiRequest));
            String ddData = apiRequest.getString("ddData");
            List<StaffPersonnelInfo> ddDatas = JSONArray.parseArray(ddData, StaffPersonnelInfo.class);

            for (StaffPersonnelInfo data : ddDatas) {
                StaffPersonnelInfo staffPersonnelInfo = new StaffPersonnelInfo();
                //新：返回错误信息，错误数据不予导入
                Map mapT = jsDataToPersonnelInfoT(staffPersonnelInfo, data);
                Boolean isReturn = (Boolean) mapT.get("isReturn");
                if(isReturn){
                    errorMessage = errorMessage + mapT.get("errorMessage");
                    errorCount ++;
                    continue;
                }
                staffPersonnelInfo.setDeleteFlag(0);

                //是否已存在
                String userTel = data.getUserTel();
                Map<String,Object> map = new HashMap<>();
                map.put("userTel",userTel);
                StaffPersonnelInfo info = staffPersonnelInfoMapper.selectByInfo(map);

                if(info!=null){
                    staffPersonnelInfo.setId(info.getId());
                    staffPersonnelInfo.setUpdateBy(userInfo.getUserName());
                    staffPersonnelInfo.setUpdateTime(new Date());
                    staffPersonnelInfoMapper.updateByPrimaryKey(staffPersonnelInfo);

                    //生成更新日志
                    try {
                        backendStaffApiImpl.updateLog(info, staffPersonnelInfo, "批量导入", userInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    staffPersonnelInfo.setCreateBy(userInfo.getUserName());
                    staffPersonnelInfo.setCreateTime(new Date());
                    staffPersonnelInfoMapper.insert(staffPersonnelInfo);
                }

                //同步userId
                if(staffPersonnelInfo.getUserId() == null){
                    //先添加login信息
                    UserLogin userLogin = userLoginMapper.selectByPhone(userTel);
                    if(userLogin != null){
                        continue;
                    }
                    userLogin = new UserLogin();
                    userLogin.setUserTelphone(userTel);
                    userLogin.setPassword(MD5Util.MD5Encode("123456@Qaz", null));
                    userLogin.setCreateTime(new Date());
                    int ret = userLoginMapper.insertSelective(userLogin);
                    if(ret < 1){
                        continue;
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
                }
            }

            count = ddDatas.size();
        }
        else if("performancePersonnelData".equals(exportType)){
            //当前登录人
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(apiRequest));
            String ddData = apiRequest.getString("performancePersonnelData");
            Long staffPerformanceId = apiRequest.getLong("staffPerformanceId");
            List<StaffPerformancePersonnel> ddDatas = JSONArray.parseArray(ddData, StaffPerformancePersonnel.class);

            for (StaffPerformancePersonnel data : ddDatas) {
                StaffPerformancePersonnel info = new StaffPerformancePersonnel();
                //新：返回错误信息，错误数据不予导入
                Map mapT = jsDataToPerformancePersonnelT(info, data, staffPerformanceId);
                Boolean isReturn = (Boolean) mapT.get("isReturn");
                if(isReturn){
                    errorMessage = errorMessage + mapT.get("errorMessage");
                    errorCount ++;
                    continue;
                }

                info = (StaffPerformancePersonnel)mapT.get("info");
                staffPerformancePersonnelMapper.updateByPrimaryKey(info);
            }
            count = ddDatas.size();
        }

        Map map = new HashMap();
        map.put("data", null);
        /*String returnErrorMessage = "";
        if(!"".equals(errorMessage)){
            returnErrorMessage = "导入失败"+errorCount+"条数据，失败原因："+errorMessage;
        }
        map.put("message","成功导入" + (count-errorCount) + "条数据。"+ returnErrorMessage);*/

        map.put("successCount",count-errorCount);
        map.put("errorCount",errorCount);
        map.put("errorMessage",errorMessage);

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }

    private Map jsDataToPersonnelInfoT(StaffPersonnelInfo info, StaffPersonnelInfo data) {
        Map messageMap = new HashMap();
        String errorMessage = "工号为"+data.getJobNo()+":";
        Boolean isReturn = false;
        info.setRealName(data.getRealName());
        info.setJobNo(data.getJobNo());
        //公司
        info.setSocialSecurityCompany(data.getSocialSecurityCompany());
        StaffCompany staffCompany = staffCompanyMapper.selectByOne(data.getSocialSecurityCompany());
        if(staffCompany != null){
            info.setSocialSecurityCompanyId(staffCompany.getId());
        }

        //公司
//        info.setCompany(data.getCompany());
//        staffCompany = staffCompanyMapper.selectByOne(data.getCompany());
//        if(staffCompany != null){
//            info.setCompanyId(staffCompany.getId());
//        }else{
//            errorMessage = errorMessage+"预算归属公司名称错误；";isReturn = true;
//        }

        data.setBudgetCompanyName(data.getCompany());
//        info.setBudgetCompanyName(data.getBudgetCompanyName());
        StaffBudgetCompany staffBudgetCompany = staffBudgetCompanyMapper.selectByOne(data.getBudgetCompanyName());
        if (staffBudgetCompany != null){
            info.setBudgetCompanyId(staffBudgetCompany.getId());
            info.setBudgetCompanyName(staffBudgetCompany.getName());
        }else{
            errorMessage = errorMessage + "预算归属公司名称错误："; isReturn = true;
        }


        //机构
        info.setOrgan(data.getOrgan());
        StaffOrgan staffOrgan = staffOrganMapper.selectByOne(data.getOrgan());
        if(staffOrgan != null){
            info.setOrganId(staffOrgan.getId());
            if (staffOrgan != null){
                StaffBudgetCompanyOrgan staffBudgetCompanyOrgan = staffBudgetCompanyOrganMapper.selectByOrganId(staffOrgan.getId());
                if (staffBudgetCompanyOrgan != null){
                    info.setCompanyId(staffBudgetCompanyOrgan.getCompanyId());
                    info.setCompany(staffBudgetCompanyOrgan.getCompanyName());
                }
            }
        }else{
            errorMessage = errorMessage+"机构/部门名称错误；";isReturn = true;
        }

        //部门
        if(!"0".equals(data.getDepartment())){
            StaffDepartment staffDepartment = staffDepartmentMapper.selectByOne(data.getDepartment());
            if(staffDepartment != null){
                info.setDepartmentId(staffDepartment.getId());
                info.setDepartment(data.getDepartment());
            }else{
                errorMessage = errorMessage+"科室名称错误；";isReturn = true;
            }
        }else{
            info.setDepartmentId(null);
            info.setDepartment(null);
        }

        //小组
        if(!"0".equals(data.getTeam())){
            StaffTeam staffTeam = staffTeamMapper.selectByOne(data.getTeam());
            if(staffTeam != null){
                info.setTeamId(staffTeam.getId());
                info.setTeam(data.getTeam());
            }else{
                errorMessage = errorMessage+"小组名称错误；";isReturn = true;
            }
        }else{
            info.setTeamId(null);
            info.setTeam(null);
        }

        //职务称谓
        if (!"0".equals(data.getPostAppellationName())){
            StaffPostAppellation staffPostAppellation = staffPostAppellationMapper.selectByOne(data.getPostAppellationName());
            if (staffPostAppellation != null){
                info.setPostAppellationId(staffPostAppellation.getId());
                info.setPostAppellationName(staffPostAppellation.getAppellationName());
            }else{
                errorMessage = errorMessage+"职务称谓名称错误；";isReturn = true;
            }
        }else{
            info.setPostAppellationId(null);
            info.setPostAppellationName(null);
        }

        //岗位
        if(!"0".equals(data.getJobPost())){
            StaffJobPost staffJobPost = staffJobPostMapper.selectByOne(data.getJobPost());
            if(staffJobPost != null){
                info.setJobPostId(staffJobPost.getId());
                info.setJobPost(data.getJobPost());
            }else{
                errorMessage = errorMessage+"岗位名称错误；";isReturn = true;
            }
        }else{
            info.setJobPostId(null);
            info.setJobPost(null);
        }

        //岗位职级
        if (!"0".equals(data.getPostRankName())){
            StaffPostRank staffPostRank = staffPostRankMapper.selectByOne(data.getPostRankName());
            if (staffPostRank != null){
                info.setPostRankId(staffPostRank.getId());
                info.setPostRankName(staffPostRank.getRankName());
            }else{
                errorMessage = errorMessage+"岗位职级错误；";isReturn = true;
            }
        }else {
            info.setPostRankId(null);
            info.setPostRankName(null);
        }

        //调查员等级
        if(!"0".equals(data.getSurveyLevelName())){
            Map<String,Object> parmap = new HashMap<>();
            parmap.put("name",data.getSurveyLevelName());
            SurveyLevel surveyLevel = surveyLevelMapper.selectOne(parmap);
            if(surveyLevel != null){
                info.setSurveyLevelId(surveyLevel.getId());
                info.setSurveyLevelName(data.getSurveyLevelName());
            }else{
                errorMessage = errorMessage+"调查员等级错误；";isReturn = true;
            }
        }else{
            info.setSurveyLevelId(null);
            info.setSurveyLevelName(null);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("userTelphone",data.getUserTel());
        UserInfo userInfo = userInfoMapper.selectUserInfoByPhone(map);
        if(userInfo !=null){
            info.setUserId(userInfo.getUserId());
        }

        //判断是不是手机号
        Pattern pattern = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$");
        Boolean isNumber = pattern.matcher(data.getUserTel()).matches();
        if(data.getUserTel() != null){
            if(isNumber){
                info.setUserTel(data.getUserTel());
            }else{
                errorMessage = errorMessage + "手机号错误；";isReturn = true;
            }
        }else{
            errorMessage = errorMessage+"手机号错误；";isReturn = true;
        }

        pattern = Pattern.compile("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)");
        isNumber = pattern.matcher(data.getIdCard()).matches();
        if(data.getIdCard() != null){
            if(isNumber){
                info.setIdCard(data.getIdCard());
            }else{
                errorMessage = errorMessage + "身份证号码错误；";isReturn = true;
            }
        }else{
            errorMessage = errorMessage+"身份证号码错误；";isReturn = true;
        }

        //三者查询，存在即update，都不存在insert
        map = new HashMap<>();
        map.put("userTel",data.getUserTel());
        StaffPersonnelInfo userNewInfo  = staffPersonnelInfoMapper.selectByInfo(map);
//        map = new HashMap<>();
//        map.put("jobNo",data.getJobNo());
//        StaffPersonnelInfo jobNoNewInfo  = staffPersonnelInfoMapper.selectByInfo(map);
        map = new HashMap<>();
        map.put("idCard",data.getIdCard());
        StaffPersonnelInfo idCardNewInfo  = staffPersonnelInfoMapper.selectByInfo(map);
//        if((userNewInfo != null && jobNoNewInfo != null && idCardNewInfo != null) || (userNewInfo == null && jobNoNewInfo == null && idCardNewInfo == null)){
        if((userNewInfo != null && idCardNewInfo != null) || (userNewInfo == null  && idCardNewInfo == null)){
            info.setUserTel(data.getUserTel());
            info.setIdCard(data.getIdCard());
            info.setJobNo(data.getJobNo());
        }else{
            errorMessage = errorMessage+"手机号重复或身份证号码重复；";isReturn = true;
        }

        if(data.getEntryTime() != null){
            info.setEntryTime(data.getEntryTime());
        }else{
            errorMessage = errorMessage+"入职时间有误；";isReturn = true;
        }

        if(data.getRegularTime() != null){
            info.setRegularTime(data.getRegularTime()); //转正时间
        }else{
            info.setRegularTime(null);
        }

        info.setRelation(data.getRelation());
        info.setStaffState(data.getStaffState());
        info.setPayAddress(data.getPayAddress());
        info.setBasePay(data.getBasePay());
        info.setFixedPerfPay(data.getFixedPerfPay());
        info.setManagePerfPay(data.getManagePerfPay());
        info.setManagePerfPaySize(data.getManagePerfPaySize());
        info.setManagePerfPaySizeHz(data.getManagePerfPaySizeHz());
        info.setAssesPerfPay(data.getAssesPerfPay());
        info.setTravelAllowancePay(data.getTravelAllowancePay());
        info.setSocialSecurityPay(data.getSocialSecurityPay());
        info.setFundPay(data.getFundPay());
        info.setQuitCost(data.getQuitCost());
        String pensionBase=data.getPensionBase().toString().replaceAll(" ", "");
        if (pensionBase != null && !"".equals(pensionBase.trim())) {
            boolean flag=isNumeric(pensionBase);
            if(flag){
                info.setPensionBase(Double.parseDouble(pensionBase));
            }else{
                errorMessage = errorMessage+"养老保险基数金额有误；";isReturn = true;
            }
        }
        String pensionCompanyRate=data.getPensionCompanyRate().toString().replaceAll(" ", "");
        if (pensionCompanyRate != null && !"".equals(pensionCompanyRate.trim())) {
            boolean flag=isNumeric(pensionCompanyRate);
            if(flag){
                Double pensionCompanyRateTwo=Double.parseDouble(pensionCompanyRate);
                if(pensionCompanyRateTwo>=0 && pensionCompanyRateTwo<=1){
                    info.setPensionCompanyRate(pensionCompanyRateTwo);
                }else{
                    errorMessage = errorMessage+"养老保险公司比例不能大于1且不能小于0；";isReturn = true;
                }
            }else{
                errorMessage = errorMessage+"养老保险公司比例输入错误；";isReturn = true;
            }
        }
        String pensionPersonalRate=data.getPensionPersonalRate().toString().replaceAll(" ", "");
        if (pensionPersonalRate != null && !"".equals(pensionPersonalRate.trim())) {
            boolean flag=isNumeric(pensionPersonalRate);
            if(flag){
                Double pensionPersonalRateTwo=Double.parseDouble(pensionPersonalRate);
                if(pensionPersonalRateTwo>=0 && pensionPersonalRateTwo<=1){
                    info.setPensionPersonalRate(pensionPersonalRateTwo);
                }else{
                    errorMessage = errorMessage+"养老保险个人比例不能大于1且不能小于0；";isReturn = true;
                }
            }else{
                errorMessage = errorMessage+"养老保险个人比例输入错误；";isReturn = true;
            }
        }
        String medicalBase=data.getMedicalBase().toString().replaceAll(" ", "");
        if (medicalBase != null && !"".equals(medicalBase.trim())) {
            boolean flag=isNumeric(medicalBase);
            if(flag){
                info.setMedicalBase(Double.parseDouble(medicalBase));
            }else{
                errorMessage = errorMessage+"医疗保险基数金额有误；";isReturn = true;
            }
        }
        String medicalCompanyRate=data.getMedicalCompanyRate().toString().replaceAll(" ", "");
        if (medicalCompanyRate != null && !"".equals(medicalCompanyRate.trim())) {
            boolean flag=isNumeric(medicalCompanyRate);
            if(flag){
                Double medicalCompanyRateTwo=Double.parseDouble(medicalCompanyRate);
                if(medicalCompanyRateTwo>=0 && medicalCompanyRateTwo<=1){
                    info.setMedicalCompanyRate(medicalCompanyRateTwo);
                }else{
                    errorMessage = errorMessage+"医疗保险公司比例不能大于1且不能小于0；";isReturn = true;
                }
            }else{
                errorMessage = errorMessage+"医疗保险公司比例输入错误；";isReturn = true;
            }
        }
        String medicalPersonalRate=data.getMedicalPersonalRate().toString().replaceAll(" ", "");
        if (medicalPersonalRate != null && !"".equals(medicalPersonalRate.trim())) {
            boolean flag=isNumeric(medicalPersonalRate);
            if(flag){
                Double medicalPersonalRateTwo=Double.parseDouble(medicalPersonalRate);
                if(medicalPersonalRateTwo>=0 && medicalPersonalRateTwo<=1){
                    info.setMedicalPersonalRate(medicalPersonalRateTwo);
                }else{
                    errorMessage = errorMessage+"医疗保险个人比例不能大于1且不能小于0；";isReturn = true;
                }
            }else{
                errorMessage = errorMessage+"医疗保险个人比例输入错误；";isReturn = true;
            }
        }
        String upmBase=data.getUpmBase().toString().replaceAll(" ", "");
        if (upmBase != null && !"".equals(upmBase.trim())) {
            boolean flag=isNumeric(upmBase);
            if(flag){
                info.setUpmBase(Double.parseDouble(upmBase));
            }else{
                errorMessage = errorMessage+"失业保险基数金额有误；";isReturn = true;
            }
        }
        String upmCompanyRate=data.getUpmCompanyRate().toString().replaceAll(" ", "");
        if (upmCompanyRate != null && !"".equals(upmCompanyRate.trim())) {
            boolean flag=isNumeric(upmCompanyRate);
            if(flag){
                Double upmCompanyRateTwo=Double.parseDouble(upmCompanyRate);
                if(upmCompanyRateTwo>=0 && upmCompanyRateTwo<=1){
                    info.setUpmCompanyRate(upmCompanyRateTwo);
                }else{
                    errorMessage = errorMessage+"失业保险公司比例不能大于1且不能小于0；";isReturn = true;
                }
            }else{
                errorMessage = errorMessage+"失业保险公司比例输入错误；";isReturn = true;
            }
        }
        String upmPersonalRate=data.getUpmPersonalRate().toString().replaceAll(" ", "");
        if (upmPersonalRate != null && !"".equals(upmPersonalRate.trim())) {
            boolean flag=isNumeric(upmPersonalRate);
            if(flag){
                Double upmPersonalRateTwo=Double.parseDouble(upmPersonalRate);
                if(upmPersonalRateTwo>=0 && upmPersonalRateTwo<=1){
                    info.setUpmPersonalRate(upmPersonalRateTwo);
                }else{
                    errorMessage = errorMessage+"失业保险个人比例不能大于1且不能小于0；";isReturn = true;
                }
            }else{
                errorMessage = errorMessage+"失业保险个人比例输入错误；";isReturn = true;
            }
        }
        String isaBase=data.getIsaBase().toString().replaceAll(" ", "");
        if (isaBase != null && !"".equals(isaBase.trim())) {
            boolean flag=isNumeric(isaBase);
            if(flag){
                info.setIsaBase(Double.parseDouble(isaBase));
            }else{
                errorMessage = errorMessage+"工伤保险基数金额有误；";isReturn = true;
            }
        }
        String isaCompanyRate=data.getIsaCompanyRate().toString().replaceAll(" ", "");
        if (isaCompanyRate != null && !"".equals(isaCompanyRate.trim())) {
            boolean flag=isNumeric(isaCompanyRate);
            if(flag){
                Double isaCompanyRateTwo=Double.parseDouble(isaCompanyRate);
                if(isaCompanyRateTwo>=0 && isaCompanyRateTwo<=1){
                    info.setIsaCompanyRate(isaCompanyRateTwo);
                }else{
                    errorMessage = errorMessage+"工伤保险公司比例不能大于1且不能小于0；";isReturn = true;
                }
            }else{
                errorMessage = errorMessage+"工伤保险公司比例输入错误；";isReturn = true;
            }
        }
        String birthBase=data.getBirthBase().toString().replaceAll(" ", "");
        if (birthBase != null && !"".equals(birthBase.trim())) {
            boolean flag=isNumeric(birthBase);
            if(flag){
                info.setBirthBase(Double.parseDouble(birthBase));
            }else{
                errorMessage = errorMessage+"生育保险基数金额有误；";isReturn = true;
            }
        }
        String birthCompanyRate=data.getBirthCompanyRate().toString().replaceAll(" ", "");
        if (birthCompanyRate != null && !"".equals(birthCompanyRate.trim())) {
            boolean flag=isNumeric(birthCompanyRate);
            if(flag){
                Double birthCompanyRateTwo=Double.parseDouble(birthCompanyRate);
                if(birthCompanyRateTwo>=0 && birthCompanyRateTwo<=1){
                    info.setBirthCompanyRate(birthCompanyRateTwo);
                }else{
                    errorMessage = errorMessage+"生育保险公司比例不能大于1且不能小于0；";isReturn = true;
                }
            }else{
                errorMessage = errorMessage+"生育保险公司比例输入错误；";isReturn = true;
            }
        }
        String fundPayCompanyRate=data.getFundPayCompanyRate().toString().replaceAll(" ", "");
        if (fundPayCompanyRate != null && !"".equals(fundPayCompanyRate.trim())) {
            boolean flag=isNumeric(fundPayCompanyRate);
            if(flag){
                Double fundPayCompanyRateTwo=Double.parseDouble(fundPayCompanyRate);
                if(fundPayCompanyRateTwo>=0 && fundPayCompanyRateTwo<=1){
                    info.setFundPayCompanyRate(fundPayCompanyRateTwo);
                }else{
                    errorMessage = errorMessage+"公积金公司比例不能大于1且不能小于0；";isReturn = true;
                }
            }else{
                errorMessage = errorMessage+"公积金公司比例输入错误；";isReturn = true;
            }
        }
        String fundPayPersonalRate=data.getFundPayPersonalRate().toString().replaceAll(" ", "");
        if (fundPayPersonalRate != null && !"".equals(fundPayPersonalRate.trim())) {
            boolean flag=isNumeric(fundPayPersonalRate);
            if(flag){
                Double fundPayPersonalRateTwo=Double.parseDouble(fundPayPersonalRate);
                if(fundPayPersonalRateTwo>=0 && fundPayPersonalRateTwo<=1){
                    info.setFundPayPersonalRate(fundPayPersonalRateTwo);
                }else{
                    errorMessage = errorMessage+"公积金个人比例不能大于1且不能小于0；";isReturn = true;
                }
            }else{
                errorMessage = errorMessage+"公积金个人比例输入错误；";isReturn = true;
            }
        }
        if(data.getQuitTime() != null){
            info.setQuitTime(data.getQuitTime());
        }else{
            info.setQuitTime(null);
        }

        info.setExtTel(data.getExtTel()=="0"? null:data.getExtTel());
        info.setOfficePlace(data.getOfficePlace()=="0"? null:data.getOfficePlace());
        info.setRemark(data.getRemark()=="0"? null:data.getRemark());
        info.setTrialTime(data.getTrialTime()=="0"? null:data.getTrialTime());
        info.setJobLevel(data.getJobLevel()=="0"? null:data.getJobLevel());
        info.setEducation(data.getEducation()=="0"? null:data.getEducation());
        info.setGraduationSchool(data.getGraduationSchool()=="0"? null:data.getGraduationSchool());
        info.setGraduationTime(data.getGraduationTime());
        info.setMajor(data.getMajor()=="0"? null:data.getMajor());
        info.setBankNo(data.getBankNo()=="0"? null:data.getBankNo());
        info.setBankName(data.getBankName()=="0"? null:data.getBankName());
        info.setContractCompany(data.getContractCompany()=="0"? null:data.getContractCompany());
        info.setContractType(data.getContractType()=="0"? null:data.getContractType());
        info.setFirstContractBeginTime(data.getFirstContractBeginTime());
        info.setFirstContractEndTime(data.getFirstContractEndTime());
        info.setNowContractBeginTime(data.getNowContractBeginTime());
        info.setNowContractEndTime(data.getNowContractEndTime());
        info.setContractTerm(data.getContractTerm()=="0"? null:data.getContractTerm());
        info.setRenewNum(data.getRenewNum()=="0"? null:data.getRenewNum());
        info.setEmergencyContactName(data.getEmergencyContactName()=="0"? null:data.getEmergencyContactName());
        info.setEmergencyContactRelation(data.getEmergencyContactRelation()=="0"? null:data.getEmergencyContactRelation());
        info.setEmergencyContactTel(data.getEmergencyContactTel()=="0"? null:data.getEmergencyContactTel());
        info.setFamilyName(data.getFamilyName()=="0"? null:data.getFamilyName());
        info.setFamilyRelation(data.getFamilyRelation()=="0"? null:data.getFamilyRelation());
        info.setFamilySex(data.getFamilySex()=="0"? null:data.getFamilySex());
        info.setFamilyBirthday(data.getFamilyBirthday());
        info.setFamilyTel(data.getFamilyTel()=="0"? null:data.getFamilyTel());
        info.setFamilyIdcardName(data.getFamilyIdcardName()=="0"? null:data.getFamilyIdcardName());

        messageMap.put("info",info);
        messageMap.put("errorMessage",errorMessage);
        messageMap.put("isReturn",isReturn);
        return messageMap;
    }



    public boolean isNumeric(String str){
        String reg = "\\d+(\\.\\d+)?";
        boolean status = str.contains(".");
        if(status){
            String str1=str.substring(0, str.indexOf("."));
            String str2=str.substring(str1.length()+1, str.length());
            if(str2.length()>4){
                return false;
            }
        }
        boolean flag=str.matches(reg);
        return flag;
    }

    public StaffPayPersonnelSlip returnSlip(StaffPersonnelInfo staffPersonnelInfo, StaffPayPersonnelSlip slip,Boolean update) {
        Field[] declaredFields = StaffPayPersonnelSlip.class.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                Object value = field.get(slip);
                if (value == null){
                    if (Long.class.equals(field.getType())){
                        field.set(slip,0L);
                    }
                    if (Double.class.equals(field.getType())){
                        field.set(slip,0D);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Double money = 0D;
        //应发工资小计
//        Double rate =DecimalUtil.twoDecimalTOFourFromFive(slip.getRealWorkingDays()/slip.getWorkingDays());
        Double rate =(slip.getRealWorkingDays()/slip.getWorkingDays());
        BigDecimal bg = new BigDecimal(rate);
        rate = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();

        //应发工资小计
        Double A = slip.getBasePay() * rate;
        money =  A + slip.getLateEarlyMoney() + slip.getAbsenteeismMoney() + slip.getLeaveMoney() + slip.getSickLeaveMoney()
                + slip.getOvertimePay() + slip.getOtherPay() + slip.getOfficeSubsidies()+ slip.getOtherCutPay() + slip.getWelfarePay() + slip.getQuitCost();
        slip.setRate(rate);
        slip.setWagesPaySub(DecimalUtil.twoDecimalTOFourFromFive(money));

        //社保公积金公司部分小计
        money = slip.getConpanyFundMoney() + slip.getCompanyPensionBenefits() + slip.getCompanyMedicalInsurance()
                + slip.getCompanyUnemploymentInsurance() + slip.getCompanySickSubsidy() + slip.getServiceFee()
                + slip.getDisabilityInsurance() + slip.getCompanyBirthInsurance() + slip.getCompanyInjuryInsurance();
        slip.setCompanyMoneySub(DecimalUtil.twoDecimalTOFourFromFive(money));

        //社保公积金公司个人小计
        money = slip.getPersonalFundMoney() + slip.getPersonalPensionBenefits() + slip.getPersonalMedicalInsurance()
                + slip.getPersonalUnemploymentInsurance() + slip.getPersonalSickSubsidy();
        slip.setPersonalMoneySub(DecimalUtil.twoDecimalTOFourFromFive(money));

        // 税前工资
        money = slip.getWagesPaySub() + slip.getPersonalMoneySub();
        slip.setGrossPay(DecimalUtil.twoDecimalTOFourFromFive(money));

        LocalDate slipDate = LocalDate.parse(slip.getWorkTime() + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (update){
            // 2024年11月13日  调整   个人所得税逻辑  ；
            Map paramMap = new HashMap();
            paramMap.put("staffUserId",slip.getUserId());
            paramMap.put("nowYear",slipDate.getYear());
            //本年度工资总条数
            List<StaffPayPersonnelSlip> slips = staffPayPersonnelSlipMapper.list(paramMap);
            String nowYearMonth = slipDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            //todo  删除有BUG
            List<Long> delIds = new ArrayList<>();
            List<Long> collect = slips.stream().map(StaffPayPersonnelSlip::getStaffPaySlipId).collect(Collectors.toList());
            collect.forEach(e -> {
                StaffPaySlip staffPaySlip = staffPaySlipMapper.selectByPrimaryKey(e);
                if (staffPaySlip.getDeleteFlag() == 1){
                    delIds.add(staffPaySlip.getId());
                }
            });
            slips = slips.stream().filter(e -> !delIds.contains(e.getStaffPaySlipId())).collect(Collectors.toList());



            slips.stream().forEach(e -> {
                if (e.getGrossPay() == null){e.setGrossPay(0D);}
                if (e.getIndividualTax() == null){e.setIndividualTax(0D);}
                if (e.getIndividualTaxChange() == null){e.setIndividualTaxChange(0D);}
                e.setTotalIndividual(e.getIndividualTax());
                e.setTotalIndividualChange(e.getIndividualTaxChange());
            });
            slips = slips.stream().filter(p -> !p.getWorkTime().equals(nowYearMonth)).collect(Collectors.toList());//不包括本月
            Double totalGrossPay = 0D, totalIndividual = 0D,totalIndividualChange = 0D;//累计税前工资 or  累计个税合计 累计个税调整
//            Integer totalMonth = slips.stream().filter(e -> staffPersonnelInfo.getSocialSecurityCompanyId().toString().equals(e.getSocialSecurityCompanyId().toString())).collect(Collectors.toList()).size() + 1;
            totalGrossPay = slips.stream().filter(e -> staffPersonnelInfo.getSocialSecurityCompanyId().toString().equals(e.getSocialSecurityCompanyId().toString())).mapToDouble(StaffPayPersonnelSlip :: getGrossPay).sum() + slip.getGrossPay();//累计税前工资（含当月）
            totalIndividual = slips.stream().mapToDouble(StaffPayPersonnelSlip :: getTotalIndividual).sum();
            totalIndividualChange = slips.stream().mapToDouble(StaffPayPersonnelSlip :: getTotalIndividualChange).sum();

            // todo Integer totalMonth
            Integer totalMonth = slipDate.getMonthValue();
            Date entryTime = staffPersonnelInfo.getEntryTime();
            LocalDate tempEntryTime = entryTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int year = tempEntryTime.getYear();
            if (slipDate.getYear() == year){//说明是本年度入职  本年度入职取的累计月份含当月
                totalMonth = totalMonth - tempEntryTime.getMonthValue() + 1;
            }
            //totalMonth 等于累计税前工资计算的月份；
            slip.setIndividualTax(getIndividualTax(totalGrossPay,totalIndividual,totalIndividualChange,totalMonth));//获取个人所得税
            if (slip.getIndividualTax() < 0){
                slip.setIndividualTax(0D);
            }
        }

        //实发工资
        //        slip.setIndividualTax(getIndividualTax(slip.getGrossPay()));//获取个人所得税
        money = slip.getGrossPay() - slip.getIndividualTax() + slip.getIndividualTaxChange();
        slip.setRealWages(DecimalUtil.twoDecimalTOFourFromFive(money));

        return slip;
    }

    //获取个人所得税
    private Double getIndividualTax(Double grossPay) {
        Double realMoney = 0D;
        Double money = grossPay - 5000;
        if(money > 0 && money <= 3000){
            realMoney = money * 0.03;
        }else if(money > 3000 && money <= 12000){
            realMoney = money * 0.10 - 210;
        }else if(money > 12000 && money <= 25000){
            realMoney = money * 0.20 - 1410;
        }else if(money > 25000 && money <= 35000){
            realMoney = money * 0.25 - 2660;
        }else if(money > 35000 && money <= 55000){
            realMoney = money * 0.30 - 4410;
        }else if(money > 55000 && money <= 80000){
            realMoney = money * 0.35 - 7160;
        }else if(money > 80000){
            realMoney = money * 0.45 - 15160;
        }
        return DecimalUtil.twoDecimalTOFourFromFive(realMoney);
    }

    /**
     * 个人所得税
     * @param totalGrossPay 累计税前工资（含当月）
     * @param totalIndividual 累计个税合计（含调整个税不含当月）
     * @param totalMonth 本年度累计月份         todo   本年度入职时间的总月份（含当月）
     * @return
     */
    private Double getIndividualTax(Double totalGrossPay,Double totalIndividual,Double totalIndividualChange,Integer totalMonth){
        Double realMoney = 0D;
        Double money = totalGrossPay - 5000 * totalMonth;
        if(money > 0 && money <= 36000){
            realMoney = money * 0.03;
        }else if(money > 36000 && money <= 144000){
            realMoney = money * 0.10 - 2520;
        }else if(money > 144000 && money <= 300000){
            realMoney = money * 0.20 - 16920;
        }else if(money > 300000 && money <= 420000){
            realMoney = money * 0.25 - 31920;
        }else if(money > 420000 && money <= 660000){
            realMoney = money * 0.30 - 52920;
        }else if(money > 660000 && money <= 960000){
            realMoney = money * 0.35 - 85920;
        }else if(money > 960000){
            realMoney = money * 0.45 - 181920;
        }
        return DecimalUtil.twoDecimalTOFourFromFive(realMoney - totalIndividual + totalIndividualChange);
    }


    private Map jsDataToPerformancePersonnelT(StaffPerformancePersonnel info, StaffPerformancePersonnel data, Long staffPerformanceId) {
        Map messageMap = new HashMap();
        String errorMessage = "工号为"+data.getJobNo()+":";
        Boolean isReturn = false;

        Map map  = new HashMap<>();
        map.put("staffPerformanceId",staffPerformanceId);
        map.put("jobNo",data.getJobNo());
        info = staffPerformancePersonnelMapper.personnelItem(map);//根据jobNo查询员工对象
        if(info == null){
            errorMessage = errorMessage+"工号不存在；"; isReturn = true;
            messageMap.put("errorMessage",errorMessage);
            messageMap.put("isReturn",isReturn);
            return messageMap;
        }

        info.setOtherPay(data.getOtherPay());
        info.setAssesPerfBasePay(data.getAssesPerfPay());//此处是考核绩效基数
        info.setAssessKpi(data.getAssessKpi());

        Double money = 0D;
        Double assesPerfBasePay = info.getAssesPerfBasePay() == null ? 0D : info.getAssesPerfBasePay();//员工管理中的考核绩效
        Double assessKpi = info.getAssessKpi() == null ? 0D : info.getAssessKpi();//考核系数
        Double bsMoney = (info.getManagePerfPaySize() == null ? 0D : info.getManagePerfPaySize()) * (info.getManageCaseNum() == null ? 0D : info.getManageCaseNum()); //保险考核绩效（按量）*保险案件数量（机构）
        Double hzMoney = (info.getManagePerfPaySizeHz() == null ? 0D : info.getManagePerfPaySizeHz()) * (info.getManageCaseNumHz() == null ? 0D : info.getManageCaseNumHz()) ;//互助考核绩效（按量）*互助案件数量（机构）
        bsMoney = 0D;
        hzMoney = 0D;
        Double assesPerfPay = assesPerfBasePay + bsMoney + hzMoney;

        money = assesPerfPay * assessKpi;
        info.setAssesPerfPay(assesPerfPay);
        info.setRealAssessKpi(money.intValue() != 0 ? DecimalUtil.twoDecimalTOFourFromFive(money) : 0);

        info.setRemarks(data.getRemarks());

        Double otherCutPay = Math.abs(data.getOtherCutPay());
        info.setOtherCutPay(-otherCutPay);
        info.setOtherCutRemarks(data.getOtherCutRemarks());
        //各种计算在内
        money = 0D;
        Double A = info.getFixedPerfPay() + info.getManagePerfPay() + info.getTravelAllowancePay();

        money = A + info.getRealAssessKpi()
                + info.getIntegralPay() + info.getLateEarlyMoney() + info.getAbsenteeismMoney()
                + info.getLeaveMoney() + info.getSickLeaveMoney() + info.getOtherPay() + info.getOtherCutPay()+ info.getExaminePay();;
        info.setRealPay(DecimalUtil.twoDecimalTOFourFromFive(money));

        messageMap.put("info",info);
        messageMap.put("errorMessage",errorMessage);
        messageMap.put("isReturn",isReturn);
        return messageMap;
    }


    private String getDistanceTime(Date hrTime, int days) {
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
        return time;
    }

    private Boolean isThisTime(Date time, String workTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String now = sdf.format(time);//当前时间
        if(workTime.equals(now)){
            return true;
        }
        return false;
    }
}
