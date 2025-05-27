package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONObject;
import com.lefancrm.apicenter.backendapi.BackendReInfoApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.CanBeSuedCaseDTO;
import com.lefancrm.apicenter.dto.InvestigatorReInfoDTO;
import com.lefancrm.apicenter.dto.ReInfoDTO;
import com.lefancrm.apicenter.dto.SurveyInvestigatorCaseDto;
import com.lefancrm.apicenter.enums.ReInfoEnum;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.DecimalUtil;
import com.lefancrm.apicenter.util.SerialNumberUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@ApiService(descript = "报销相关API")
public class BackendReInfoApiImpl  extends BaseServiceImpl implements BackendReInfoApi {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ReInfoMapper reInfoMapper;
    @Autowired
    private InvestigatorReInfoMapper investigatorReInfoMapper;
    @Autowired
    private InvestigatorReDetailsMapper investigatorReDetailsMapper;
    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;
    @Autowired
    private SurveyPayInfoMapper surveyPayInfoMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;
    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;
    @Autowired
    private SurveyPreReimbursementMapper surveyPreReimbursementMapper;
    @Autowired
    private InvestigatorPreDetailsMapper investigatorPreDetailsMapper;
    @Autowired
    private SurveyInvestigatorCaseMapper surveyInvestigatorCaseMapper;
    @Autowired
    private SurveyUserClockMapper surveyUserClockMapper;
    @Autowired
    private SurveyInvestigatorReInfoMapper surveyInvestigatorReInfoMapper;
    @Autowired
    private StaffPersonnelInfoMapper staffPersonnelInfoMapper;

    @ApiMethod(needLogin = false,descript = "报销下发",value = "backend-fee-re-sued")
    @Override
    public ApiResponse sued(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        String ids = apiRequest.getString("ids");
        if (StringUtils.isEmpty(ids)){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        ReInfo reInfo = null;
        //加锁
        synchronized (this){
            String reName = apiRequest.getString("reName");
            if (!StringUtils.isEmpty(reName)) {
                reName = reName.trim();
                int havaCount = reInfoMapper.selectByReName(reName);
                if (havaCount > 0){
                    return new ApiResponse(ApiMsgEnum.FEE_RE_INFO_NAME_HAVE);
                }
            }
            //保存下发单主表
            reInfo = new ReInfo();
            reInfo.setReName(reName);
            reInfo.setReState(0);
            reInfo.setDownTime(new Date());
            reInfo.setCreateTime(new Date());
            reInfo.setCreateBy(userInfo.getUserName());
            reInfo.setDeleteFlag(0);
            reInfo.setTotalCaseNum(ids.split(",").length);
            reInfoMapper.insert(reInfo);
        }
        //更改案件报销状态
        surveyRiskCaseInfoMapper.updateReimState(Arrays.asList(ids.split(",")));

        //插入调查员报销表 (insert into select)
        Map<String,Object> map =  new HashMap<String,Object>();
        map.put("ids",ids);
        map.put("reId",reInfo.getId());
        map.put("reName",reInfo.getReName());
        investigatorReInfoMapper.suedInsert(map);
        //调查员报销明细表 (insert into select)
        map = new HashMap<>();
        map.put("ids",ids);
        map.put("reId",reInfo.getId());
        investigatorReDetailsMapper.suedInsert(map);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,reInfo);
    }

    @ApiMethod(needLogin = false,descript = "可下发案件清单",value = "backend-can-be-sued-list")
    @Override
    public ApiResponse canBeSued(ApiRequest apiRequest) {
        apiRequest.put("searchStr",apiRequest.getString("searchStr") == null ? null : apiRequest.getString("searchStr").trim());
        List<CanBeSuedCaseDTO> list = surveyRiskCaseInfoMapper.canBeSuedList(apiRequest);
        List<SurveyConsignor> consignorList = surveyConsignorMapper.list(apiRequest);
        JSONObject resultJSON = new JSONObject();
        resultJSON.put("list",list);
        resultJSON.put("consignorList",consignorList);
        resultJSON.put("entrustOrgIds",apiRequest.getString("entrustOrgIds"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),resultJSON);
    }

    @ApiMethod(needLogin = false,descript = "调查员报销清单列表",value = "backend-fee-re-list")
    @Override
    public ApiResponse surveyUserSuedList(ApiRequest apiRequest) {
        setBackendPageSize(apiRequest);
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        String searchCode = apiRequest.getString("searchCode");
        //机构角色  和 财务角色
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean orgRole = isRoleUser(userRoles,104L),finance = isRoleUser(userRoles,23L);
        if ("reimbursement-list".equals(searchCode)){//调查员的报销清单
            apiRequest.put("surveyUserId",userInfo.getUserId());
        }else if ("reimbursement-manager-list".equals(searchCode)){//报销管理  通过报销清单ID查询明细
            //机构角色只查询自己机构的数据（包含子机构）
            if (orgRole && !finance){
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(userInfo.getUserId());
                if (surveyInvestigator != null){
                    apiRequest.put("surveyOrgId",surveyInvestigator.getOrgId());
                    SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyInvestigator.getOrgId());
                    if (surveyFranchisee != null){
                        if (surveyFranchisee.getLevel() == 1) {
                            //添加自己机构的集合
                            apiRequest.remove("surveyOrgId");
                            Map<String,Object> map =  new HashMap<String,Object>();
                            map.put("parentId",surveyFranchisee.getId());
                            List<SurveyFranchisee> list = surveyFranchiseeMapper.list(map);
                            String parentOrgIds = surveyFranchisee.getId() + ",";
                            for (SurveyFranchisee franchisee : list) {
                                parentOrgIds += franchisee.getId() + ",";
                            }
                            apiRequest.put("parentOrgIds",parentOrgIds);
                        }
                    }
                }
                if (apiRequest.containsKey("surveyOrgIds")){//如果是机构筛选 则查询机构下的所有片区机构及本身的数据。
                    apiRequest.remove("surveyOrgIds");
                }
            }
        }

        List<Integer> orgIds = null;//可审核的机构ID
        if (orgRole){
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(userInfo.getUserId());
            if (surveyInvestigator != null){
                orgIds = new ArrayList<Integer>();
                orgIds.add(surveyInvestigator.getOrgId().intValue());
                Map<String,Object> map =  new HashMap<String,Object>();
                map.put("parentId",surveyInvestigator.getOrgId());
                List<SurveyFranchisee> franchisees = surveyFranchiseeMapper.list(map);
                for (SurveyFranchisee franchisee : franchisees) {
                    orgIds.add(franchisee.getId().intValue());
                }
            }
        }

        List<InvestigatorReInfoDTO> list = investigatorReInfoMapper.selectInvestigatorReInfos(apiRequest);
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        for (InvestigatorReInfoDTO item : list) {
            Map feeMap = investigatorReInfoMapper.selectGroupMoney(item);
            if (!CollectionUtils.isEmpty(feeMap)){
                item.setDataFee(Double.valueOf(decimalFormat.format(feeMap.get("dataFee"))));
                item.setHzAccommodationFee(Double.valueOf(decimalFormat.format(feeMap.get("hzAccommodationFee"))));
                item.setHzTransportationFee(Double.valueOf(decimalFormat.format(feeMap.get("hzTransportationFee"))));
                item.setMileageSubsidy(Double.valueOf(decimalFormat.format(feeMap.get("mileageSubsidy"))));
            }
            if (item.getTotalMoney() != null) {
                item.setTotalMoney(DecimalUtil.twoDecimalTOFourFromFive(item.getTotalMoney()));
            }
            Boolean showBtn = false;
            if ("reimbursement-list".equals(searchCode)){
                showBtn = true;
            }
            if (item.getReState() == 2 && orgRole) {//待机构审核  且有机构角色  且当前登陆人得机构ID 等于 清单得机构ID 则显示审核通过
                if (orgIds != null){
                    if (orgIds.contains(item.getSurveyOrgId().intValue())) {
                        showBtn = true;
                    }
                }
            }
            if (item.getReState() == 3 && finance){
                showBtn = true;
            }
            item.setShowBtn(showBtn);
            if (!StringUtils.isEmpty(item.getLineStr())) {
                String [] lines = item.getLineStr().split("_");
                Double totalMoney = 0D;
                List<CanBeSuedCaseDTO> items = new ArrayList<>();
                for (String line : lines) {
                    CanBeSuedCaseDTO temp = new CanBeSuedCaseDTO();
                    if (!StringUtils.isEmpty(line)) {
                        String [] data = line.split(",");
                        temp.setSurveyInfoId(data[0] == null ? null : Long.parseLong(data[0]));
                        temp.setSurveyCaseNo(data[1]);
                        temp.setSurveyPerson(data[2]);
                        temp.setEntrustOrgId(data[3] == null ? null : Long.parseLong(data[3]));
                        temp.setEntrustOrgName(data[4]);
                        try {
                            temp.setEntrustOprDate(data[5] == null ? null : new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(data[5]));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        temp.setMoney(data[6] == null ? null : DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(data[6])));
                        if (temp.getMoney() != null){
                            totalMoney += temp.getMoney();//计算费用报销合计
                        }
                    }
                    items.add(temp);
                }
                item.setItems(items);
                item.setTotalMoney(DecimalUtil.twoDecimalTOFourFromFive(totalMoney));
            }
        }
        int count = investigatorReInfoMapper.selectInvestigatorReInfosSize(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    @ApiMethod(needLogin = false,descript = "报销管理清单列表",value = "backend-fee-re-manager")
    @Override
    public ApiResponse suedManagerList(ApiRequest apiRequest) {
        setBackendPageSize(apiRequest);
        apiRequest.put("success","".equals(apiRequest.getString("success")) ? null : apiRequest.getString("success"));
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean orgRole = isRoleUser(userRoles,104L),finance = isRoleUser(userRoles,23L);
        if (orgRole && !finance){
            apiRequest.put("orgRole","orgRole");
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(userInfo.getUserId());
            if (surveyInvestigator != null){
                apiRequest.put("surveyOrgId",surveyInvestigator.getOrgId());
                SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyInvestigator.getOrgId());
                if (surveyFranchisee != null){
                    if (surveyFranchisee.getLevel() == 1) {
                        //添加自己机构的集合
                        apiRequest.remove("surveyOrgId");
                        Map<String,Object> map =  new HashMap<String,Object>();
                        map.put("parentId",surveyFranchisee.getId());
                        List<SurveyFranchisee> list = surveyFranchiseeMapper.list(map);
                        String parentOrgIds = surveyFranchisee.getId() + ",";
                        for (SurveyFranchisee franchisee : list) {
                            parentOrgIds += franchisee.getId() + ",";
                        }
                        apiRequest.put("parentOrgIds",parentOrgIds);
                    }
                }
            }
        }
        if (finance){
            apiRequest.put("finance","finance");
            if (apiRequest.containsKey("orgRole")) {
                apiRequest.remove("orgRole");
            }
            if (apiRequest.containsKey("surveyOrgId")) {
                apiRequest.remove("surveyOrgId");
            }
            if (apiRequest.containsKey("parentOrgIds")) {
                apiRequest.remove("parentOrgIds");
            }
        }
        List<ReInfoDTO> list = reInfoMapper.selectReinfos(apiRequest);
        for (ReInfoDTO reInfoDTO : list) {
            reInfoDTO.setTotalMoney(reInfoDTO.getTotalMoney() == null ? 0.00D : DecimalUtil.twoDecimalTOFourFromFive(reInfoDTO.getTotalMoney()));
        }
        int count = reInfoMapper.selectReinfosSize(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId() == roleId){
                return true;
            }
        }
        return false;
    }

    @ApiMethod(needLogin = false,descript = "报销处理操作",value = "backend-fee-re-operate")
    @Override
    public ApiResponse suedOperate(ApiRequest apiRequest) {
        Long  id = apiRequest.getLong("id");
        String btnCode = apiRequest.getString("btnCode");
        String reason = apiRequest.getString("reason");
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        InvestigatorReInfo investigatorReInfo = investigatorReInfoMapper.selectByPrimaryKey(id);
        investigatorReInfo.setRejectDesc(null);
        if ("step-one".equals(btnCode)){
            investigatorReInfo.setReState(2);
            investigatorReInfo.setReStateStr("待机构审核");
        }else if ("step-two-yes".equals(btnCode)){
            investigatorReInfo.setReState(3);
            investigatorReInfo.setReStateStr("待财务审核");
            investigatorReInfo.setOrgCheckTime(new Date());
        }else if ("step-two-no".equals(btnCode)){
            investigatorReInfo.setReState(1);
            investigatorReInfo.setReStateStr("机构审核驳回");
            investigatorReInfo.setRejectDesc(reason);
        }else if ("step-three-yes".equals(btnCode)){
            investigatorReInfo.setReState(4);
            investigatorReInfo.setReStateStr("付款中");
            investigatorReInfo.setFinanceCheckTime(new Date());
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(investigatorReInfo.getSurveyUserId());
            //生成一条公估付款记录
            SurveyPayInfo surveyPayInfo = new SurveyPayInfo();
            surveyPayInfo.setPayNo(SerialNumberUtil.toBuilNo("RE"));
            surveyPayInfo.setOrgId(surveyInvestigator.getOrgId());
            surveyPayInfo.setOrgName(surveyInvestigator.getOrgName());
            surveyPayInfo.setSourceSupportType(null);
            Double totalMoney = investigatorReDetailsMapper.selectTotalMoneyByInvestigatorReId(investigatorReInfo.getId());
            surveyPayInfo.setAppPayMoney(totalMoney);
            surveyPayInfo.setRemark(investigatorReInfo.getReName());
            surveyPayInfo.setAppStartDate(new Date());
            surveyPayInfo.setAppEndDate(new Date());
            surveyPayInfo.setAppType(1);
            surveyPayInfo.setCreateUserId(userInfo.getUserId());
            surveyPayInfo.setCreateBy(userInfo.getUserName());
            surveyPayInfo.setPayState(1);
            surveyPayInfo.setCreateTime(new Date());
            surveyPayInfo.setDeleteFlag(0);
            surveyPayInfo.setUpdateBy(userInfo.getUserName());
            surveyPayInfo.setUpdateTime(new Date());
            surveyPayInfo.setPayType(2);//报销费
            surveyPayInfo.setPaySurveyUserId(surveyInvestigator.getUserId());
            surveyPayInfo.setPaySurveyUserName(surveyInvestigator.getRealName());
            surveyPayInfo.setPayKeyId(investigatorReInfo.getId());

            //同时查询是否是员工管理中的人员
            StaffPersonnelInfo info  = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(surveyInvestigator.getUserId());
            if(info != null){
                surveyPayInfo.setUserId(info.getUserId());
                surveyPayInfo.setRealName(info.getRealName());
                surveyPayInfo.setSocialSecurityCompanyId(info.getSocialSecurityCompanyId());
                surveyPayInfo.setSocialSecurityCompany(info.getSocialSecurityCompany());
                surveyPayInfo.setOrganId(info.getOrganId());
                surveyPayInfo.setOrgan(info.getOrgan());
                surveyPayInfo.setDepartmentId(info.getDepartmentId());
                surveyPayInfo.setDepartment(info.getDepartment());
                surveyPayInfo.setTeam(info.getTeam());
                surveyPayInfo.setTeamId(info.getTeamId());
                surveyPayInfo.setJobPost(info.getJobPost());
                surveyPayInfo.setJobPostId(info.getJobPostId());
            }

            surveyPayInfoMapper.insert(surveyPayInfo);
        }else if ("step-three-no".equals(btnCode)){
            investigatorReInfo.setReState(1);
            investigatorReInfo.setReStateStr("财务审核驳回");
            investigatorReInfo.setRejectDesc(reason);
        }else if ("step-four".equals(btnCode) || "no-re".equals(btnCode)){
            //报销完成有多个入口。  若增加其他逻辑。  BackendSurveyPayInfoApiImpl  (ok-acc-pay)的确认到账也需要增加
            investigatorReInfo.setReState(6);
            investigatorReInfo.setReStateStr("报销完成");
            if ("no-re".equals(btnCode)){
                investigatorReInfo.setReStateStr("报销完成(无报销)");
            }
            investigatorReInfo.setFinshTime(new Date());
            investigatorReInfoMapper.updateByPrimaryKey(investigatorReInfo);//状态先更改
            //判断该报销清单的案件 所有调查员是否都已经报销完成。 若报销完成 则更改案件状态 已报销
            investigatorReInfoMapper.updateSurveyRiskInfoReimSate(investigatorReInfo.getReId());
            //判断该报销单下的所有报销清单是否都已完成。 都已完成 则更改报销单状态 已完成
            // select count(1) from investigator_re_info r where r.re_id = 4 and r.re_state != 6; 只要count(1) 等于0  则说明。 报销单已完成
            int count = investigatorReInfoMapper.selectAllSuccessByReId(investigatorReInfo.getReId());
            if (count == 0){
                ReInfo reInfo = reInfoMapper.selectByPrimaryKey(investigatorReInfo.getReId());
                reInfo.setReState(1);//已完成
                reInfoMapper.updateByPrimaryKey(reInfo);
            }
            //同步付款申请表的到账状态
            SurveyPayInfo surveyPayInfo = surveyPayInfoMapper.selectByPayKeyId(investigatorReInfo.getId());
            if (surveyPayInfo != null){
                surveyPayInfo.setPayState(3);
                surveyPayInfo.setPayStateOkTime(new Date());
                surveyPayInfo.setUpdateBy(userInfo.getUserName());
                surveyPayInfo.setUpdateTime(new Date());
                surveyPayInfoMapper.updateByPrimaryKey(surveyPayInfo);
                //  更改机构到账状态。  为 否
                SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyPayInfo.getOrgId());
                if (surveyFranchisee != null){
                    surveyFranchisee.setPayStateOk(0);//否  不存在未到账
                    surveyFranchiseeMapper.updateByPrimaryKey(surveyFranchisee);
                }
            }


        }
        investigatorReInfoMapper.updateByPrimaryKey(investigatorReInfo);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,investigatorReInfo);
    }

    @ApiMethod(needLogin = false,descript = "调查员预报销清单列表",value = "backend-pre-fee-re-list")
    @Override
    public ApiResponse surveyUserPreSuedList(ApiRequest apiRequest) {
        Integer pageIndex = apiRequest.getInt("pageNum");
        Integer pageSize = apiRequest.getInt("pageSize");
        apiRequest.put("pageIndex",(pageIndex - 1) * pageSize);
        JSONObject resultJson = new JSONObject();
        Long currentUserId = getCurrentUserId(apiRequest);
        //机构角色  和 财务角色
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean orgRole = isRoleUser(userRoles,104L),finance = isRoleUser(userRoles,23L);
        if (!orgRole && !finance){
            return new ApiResponse(ApiMsgEnum.SUCCESS,0,null);
        }
        apiRequest.put("orgRole",orgRole);
        apiRequest.put("finance",finance);
        //查询当前机构下面的所有调查员
        SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
        if(surveyInvestigator != null){
//            List<SurveyInvestigator> surveyInvestigatorList = surveyInvestigatorMapper.selectOrgAllSurveyInv(surveyInvestigator.getOrgId());
//            String collect = surveyInvestigatorList.stream().map(e -> e.getUserId().toString()).collect(Collectors.joining(","));
//            apiRequest.put("userIds",collect);
            apiRequest.put("orgId",surveyInvestigator.getOrgId());
        }
        List<SurveyPreReimbursement> surveyPreReimbursements = surveyPreReimbursementMapper.selectBySelective(apiRequest);
        surveyPreReimbursements.removeIf(e->e.getId() == null);
        for (SurveyPreReimbursement surveyPreReimbursement : surveyPreReimbursements) {
            SurveyPreReimbursement ment = surveyPreReimbursementMapper.selectEveryMoneyBySelective(surveyPreReimbursement.getClockIds());
            surveyPreReimbursement.setMedicalHistoryMoney(ment.getMedicalHistoryMoney());
            surveyPreReimbursement.setCityinDrivingMoney(ment.getCityinDrivingMoney());
            surveyPreReimbursement.setTroubleshootingMoney(ment.getTroubleshootingMoney());
            surveyPreReimbursement.setOpcTroubleshootingMoney(ment.getOpcTroubleshootingMoney());
            surveyPreReimbursement.setPrintingMoney(ment.getPrintingMoney());
            surveyPreReimbursement.setAccommodatioMoney(ment.getAccommodatioMoney());
            surveyPreReimbursement.setCrossDrivingMoney(ment.getCrossDrivingMoney());
            surveyPreReimbursement.setSelfDrivingMoney(ment.getSelfDrivingMoney());
            surveyPreReimbursement.setOtherMoney(ment.getOtherMoney());
            surveyPreReimbursement.setAvgMoney(surveyPreReimbursement.getTotalMoney()/surveyPreReimbursement.getCaseCount());
        }

        apiRequest.put("surveyInvestigator",surveyInvestigator);

        List<SurveyFranchisee> franseList = surveyFranchiseeMapper.selectOrgAllPre(apiRequest);//机构角色显示发起报销的机构  财务角色显示所有发起报销机构
        resultJson.put("franseList",franseList);

        List<SurveyInvestigator> invList = surveyInvestigatorMapper.selectOrgAllSurveyInvPre(apiRequest);//机构角色显示机构下发起预报销的所有调查员  财务角色显示所有发起报销调查员
        String surveyOrgIds = apiRequest.getString("surveyOrgIds");
        if(!StringUtils.isEmpty(surveyOrgIds)){
            ArrayList<String> orgList = new ArrayList<>(Arrays.asList(surveyOrgIds.split(",")));
            invList = invList.stream().filter(e-> orgList.contains(e.getOrgId().toString())).collect(Collectors.toList());
        }
        resultJson.put("investigators",invList);

        resultJson.put("surveyPreReimbursements",surveyPreReimbursements);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,resultJson);
    }

    @ApiMethod(needLogin = false,descript = "预报销选择调查员列表",value = "backend-pre-org-user-list")
    @Override
    public ApiResponse surveyUserPreList(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
        List<SurveyInvestigator> surveyInvestigatorList = Collections.emptyList();
        if (surveyInvestigator != null){
            surveyInvestigatorList = surveyInvestigatorMapper.selectOrgAllSurveyInv(surveyInvestigator.getOrgId());
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyInvestigatorList);
    }

    @ApiMethod(needLogin = false,descript = "预报销下发",value = "backend-pre-fee-re-sued")
    @Override
    public ApiResponse addPre(ApiRequest apiRequest) {
        Map paramMap = new HashMap();
        Long userId = apiRequest.getLong("userId");
        paramMap.put("surveyUserId", userId);
        String preInfoDate = LocalDate.now().toString().substring(0,7);
        SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(getCurrentUserId(apiRequest));
        SurveyPreReimbursement prement = surveyPreReimbursementMapper.selectByUserId(userId,preInfoDate,surveyInvestigator.getOrgId());
        int i = surveyUserClockMapper.selectByUserIdAndDate(userId);
        if (prement != null || i == 0){ //下发过预报销或者打卡没有案子的  不下发预报销清单
            return new ApiResponse(ApiMsgEnum.SUCCESS,0,null);
        }
        List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseDtos = surveyInvestigatorCaseMapper.selectByList(paramMap);
        if (surveyInvestigatorCaseDtos == null || surveyInvestigatorCaseDtos.size() == 0) {//没有做过案子
            return new ApiResponse(ApiMsgEnum.SUCCESS,0,null);
        }

        List<SurveyInvestigator> surveyInvestigatorList = surveyPreReimbursementMapper.selectUserClockGroupOrgId(userId, preInfoDate);
        for (SurveyInvestigator investigator : surveyInvestigatorList) {
            SurveyPreReimbursement surveyPreReimbursement = surveyPreReimbursementMapper.selectAllCaseTotalMoneyByUserId(investigator.getUserId(),investigator.getOrgId());
            surveyPreReimbursement.setState(1);
            surveyPreReimbursement.setSurveyOrgId(investigator.getOrgId());
            surveyPreReimbursement.setCreateTime(new Date());
            surveyPreReimbursementMapper.insert(surveyPreReimbursement);
            investigatorPreDetailsMapper.insertBySelect(investigator.getUserId(), surveyPreReimbursement.getId(),investigator.getOrgId());
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,null);
    }

    @ApiMethod(needLogin = false,descript = "预报销处理操作",value = "backend-pre-fee-re-operate")
    @Override
    public ApiResponse preOperate(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        String btnCode = apiRequest.getString("btnCode");
        SurveyPreReimbursement surveyPreReimbursement = surveyPreReimbursementMapper.selectByPrimaryKey(id);
        if ("preDelete".equals(btnCode)){//预报销撤销
            surveyPreReimbursementMapper.deleteByPrimaryKey(id);
            investigatorPreDetailsMapper.deleteByPreId(surveyPreReimbursement.getId());
        }else if ("submit".equals(btnCode)){//提交审核
            surveyPreReimbursement.setState(2);
            surveyPreReimbursementMapper.updateByPrimaryKeySelective(surveyPreReimbursement);
            //查看是否有对应的报销清单 如果有则更新状态
            if (surveyPreReimbursement.getCreateTime() != null){
               String reInfoDate = DateUtils.dateToLocalDate(surveyPreReimbursement.getCreateTime()).plusMonths(1).toString().substring(0,7);
                SurveyInvestigatorReInfo surveyInvestigatorReInfo = surveyInvestigatorReInfoMapper.selectReInfoSurveyUserId(surveyPreReimbursement.getSurveyUserId(),reInfoDate,surveyPreReimbursement.getSurveyOrgId());
                if (surveyInvestigatorReInfo != null){
                    surveyInvestigatorReInfo.setReState(ReInfoEnum.WAIT_FINANCE_CHECK.getState());
                    surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.WAIT_FINANCE_CHECK.getStateName());
                    surveyInvestigatorReInfoMapper.updateByPrimaryKeySelective(surveyInvestigatorReInfo);
                }
            }
        }else if ("pass".equals(btnCode)) {//付款
            surveyPreReimbursement.setState(3);
            surveyPreReimbursementMapper.updateByPrimaryKeySelective(surveyPreReimbursement);

            //查看是否有对应的报销清单 如果有则更新状态
            if (surveyPreReimbursement.getCreateTime() != null){
                String reInfoDate = DateUtils.dateToLocalDate(surveyPreReimbursement.getCreateTime()).plusMonths(1).toString().substring(0,7);//一定是下一个月的报销清单
                SurveyInvestigatorReInfo surveyInvestigatorReInfo = surveyInvestigatorReInfoMapper.selectReInfoSurveyUserId(surveyPreReimbursement.getSurveyUserId(),reInfoDate,surveyPreReimbursement.getSurveyOrgId());
                if (surveyInvestigatorReInfo != null){
                    surveyInvestigatorReInfo.setReState(ReInfoEnum.THE_PAYING.getState());
                    surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.THE_PAYING.getStateName());
                    surveyInvestigatorReInfoMapper.updateByPrimaryKeySelective(surveyInvestigatorReInfo);
                    //生成一条公估付款记录
                    Date currentDate = new Date();
                    SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(surveyInvestigatorReInfo.getSurveyUserId());
                    UserInfo userInfo = userInfoMapper.selectByPrimaryKey(surveyInvestigatorReInfo.getSurveyUserId());
                    SurveyPayInfo surveyPayInfo = new SurveyPayInfo();
                    surveyPayInfo.setPayNo(SerialNumberUtil.toBuilNo("RE"));
                    surveyPayInfo.setOrgId(surveyInvestigatorReInfo.getSurveyOrgId());
                    surveyPayInfo.setOrgName(surveyInvestigatorReInfo.getSurveyOrgName());
                    surveyPayInfo.setSourceSupportType(null);
                    surveyPayInfo.setAppPayMoney(surveyInvestigatorReInfo.getTotalMoney());
                    surveyPayInfo.setRemark(surveyInvestigatorReInfo.getReName());
                    surveyPayInfo.setAppStartDate(currentDate);
                    surveyPayInfo.setAppEndDate(currentDate);
                    surveyPayInfo.setAppType(1);
                    surveyPayInfo.setCreateUserId(userInfo.getUserId());
                    surveyPayInfo.setCreateBy(userInfo.getUserName());
                    surveyPayInfo.setPayState(1);
                    surveyPayInfo.setCreateTime(currentDate);
                    surveyPayInfo.setDeleteFlag(0);
                    surveyPayInfo.setUpdateBy(userInfo.getUserName());
                    surveyPayInfo.setUpdateTime(currentDate);
                    surveyPayInfo.setPayType(6);//离职预报销
                    surveyPayInfo.setPaySurveyUserId(surveyInvestigator.getUserId());
                    surveyPayInfo.setPaySurveyUserName(surveyInvestigator.getRealName());
                    surveyPayInfo.setPayKeyId(surveyInvestigatorReInfo.getId());

                    //同时查询是否是员工管理中的人员
                    StaffPersonnelInfo info  = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(surveyInvestigator.getUserId());
                    if(info != null){
                        surveyPayInfo.setUserId(info.getUserId());
                        surveyPayInfo.setRealName(info.getRealName());
                        surveyPayInfo.setSocialSecurityCompanyId(info.getSocialSecurityCompanyId());
                        surveyPayInfo.setSocialSecurityCompany(info.getSocialSecurityCompany());
                        surveyPayInfo.setOrganId(info.getOrganId());
                        surveyPayInfo.setOrgan(info.getOrgan());
                        surveyPayInfo.setDepartmentId(info.getDepartmentId());
                        surveyPayInfo.setDepartment(info.getDepartment());
                        surveyPayInfo.setTeam(info.getTeam());
                        surveyPayInfo.setTeamId(info.getTeamId());
                        surveyPayInfo.setJobPost(info.getJobPost());
                        surveyPayInfo.setJobPostId(info.getJobPostId());
                    }
                    surveyPayInfoMapper.insert(surveyPayInfo);
                }
            }

        }else if ("reject".equals(btnCode)){
            surveyPreReimbursement.setState(4);
            String returnText = apiRequest.getString("returnText");
            surveyPreReimbursement.setReturnText(returnText);
            surveyPreReimbursementMapper.updateByPrimaryKeySelective(surveyPreReimbursement);

            //查看是否有对应的报销清单 如果有则更新状态
            if (surveyPreReimbursement.getCreateTime() != null) {
                String reInfoDate = DateUtils.dateToLocalDate(surveyPreReimbursement.getCreateTime()).plusMonths(1).toString().substring(0,7);//一定是下一个月的报销清单
                //查看是否有对应的报销清单 如果有则更新状态
                SurveyInvestigatorReInfo surveyInvestigatorReInfo = surveyInvestigatorReInfoMapper.selectReInfoSurveyUserId(surveyPreReimbursement.getSurveyUserId(),reInfoDate,surveyPreReimbursement.getSurveyOrgId());
                if (surveyInvestigatorReInfo != null){
                    surveyInvestigatorReInfo.setReState(ReInfoEnum.FINANCE_CHECK_REJECT.getState());
                    surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.FINANCE_CHECK_REJECT.getStateName());
                    surveyInvestigatorReInfo.setRejectDesc(returnText);
                    surveyInvestigatorReInfoMapper.updateByPrimaryKeySelective(surveyInvestigatorReInfo);
                }
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,null);
    }

    @ApiMethod(needLogin = false,descript = "报销处理操作",value = "backend-pre-fee-user-list")
    @Override
    public ApiResponse preUserList(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        Map<String,Object> dataRoleMap = getDataRole(currentUserId);
        List<SurveyInvestigator> surveyInvestigatorList = null;
        if (dataRoleMap != null){
            //机构负责人，仅看到本机构的数据
            String dataRoleCode = dataRoleMap.get("dataRoleCode") != null ? dataRoleMap.get("dataRoleCode").toString() : "";
            String dataRoleOrgId = dataRoleMap.get("dataRoleOrgId") != null ? dataRoleMap.get("dataRoleOrgId").toString() : "";
            dataRoleMap.put("orgId",dataRoleOrgId);
            if ("manger".equals(dataRoleCode) || "provincialManger".equals(dataRoleCode)) {//省级
                dataRoleMap.put("level",1);
                surveyInvestigatorList =  surveyInvestigatorMapper.selectOrgUser(dataRoleMap);
            } else if ("areaManger".equals(dataRoleCode)) {//片区
                dataRoleMap.put("level",2);
                surveyInvestigatorList = surveyInvestigatorMapper.selectOrgUser(dataRoleMap);
            }
        }else{
            return new ApiResponse(ApiMsgEnum.SURVEY_AUTH_NOT);
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyInvestigatorList);
    }

    private Map<String,Object> getDataRole(Long currentUserId){
        Map<String,Object> map =  new HashMap<String,Object>();
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean orgManger = isRoleUser(userRoles,104L), lfManger = isRoleUser(userRoles,95L), investigators = isRoleUser(userRoles,50L);
        if (lfManger){
            map.put("dataRoleCode","manger");
            return map;
        }
        if (orgManger){
            SurveyInvestigator investigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
            if (investigator != null){
                SurveyFranchisee franchisee = surveyFranchiseeMapper.selectByPrimaryKey(investigator.getOrgId());
                if (franchisee != null){
                    if (franchisee.getLevel() == 1){
                        map.put("dataRoleCode","provincialManger");// 省级数据
                    }else if (franchisee.getLevel() == 2){
                        map.put("dataRoleCode","areaManger");//片区数据
                    }
                    map.put("dataRoleOrgId",investigator.getOrgId());
                    return map;
                }
            }
        }
        if(investigators){
            map.put("dataRoleCode","investigators");
            return map;
        }
        return null;
    }
}
