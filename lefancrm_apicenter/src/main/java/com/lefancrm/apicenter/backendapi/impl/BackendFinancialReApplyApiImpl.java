package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendFinancialReApplyApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.finacial.FinanLastUserDTO;
import com.lefancrm.apicenter.dto.finacial.FinancialFileTableEnumDto;
import com.lefancrm.apicenter.dto.finacial.FinancialReApplyStateEnumDto;
import com.lefancrm.apicenter.fina.dto.StatesDTO;
import com.lefancrm.apicenter.fina.enums.*;
import com.lefancrm.apicenter.fina.model.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.*;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.jpush.api.utils.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
@ApiService(descript = "每刻报销表相关API")
public class BackendFinancialReApplyApiImpl extends BaseServiceImpl implements BackendFinancialReApplyApi {
    @Autowired
    private FinancialReApplyMapper financialReApplyMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private StaffOrganMapper staffOrganMapper;
    @Autowired
    private FinancialCostTypeMapper financialCostTypeMapper;
    @Autowired
    private StaffPersonnelInfoMapper staffPersonnelInfoMapper;
    @Autowired
    private FinancialCostBearMapper financialCostBearMapper;
    @Autowired
    private FinancialCostDetailsMapper financialCostDetailsMapper;
    @Autowired
    private BackendFinancialFileApiImpl backendFinancialFileApiImpl;
    @Autowired
    private StaffCompanyMapper staffCompanyMapper;
    //    @Autowired
//    private StaffBudgetCompanyMapper staffBudgetCompanyMapper;
    @Autowired
    private BankInfoMapper bankInfoMapper;
    @Autowired
    private FinancialFileMapper financialFileMapper;
    @Autowired
    private SurveyPayInfoMapper surveyPayInfoMapper;
    @Autowired
    private BackendFinancialReProgresApiImpl backendFinancialReProgresApiImpl;
    @Autowired
    private FinancialReProgresMapper financialReProgresMapper;
    @Autowired
    private BackendWechatApiImpl backendWechatApi;
    @Autowired
    private FinancialCostOrgDetailsMapper financialCostOrgDetailsMapper;


    @ApiMethod(needLogin = false,descript = "每刻报销列表",value = "backend-financial-re-apply-list")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {

        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        //分页
        Boolean pageFlag = !"report".equals(apiRequest.getString("report"));//是否分页
        if (pageFlag){
            setBackendPageSize(apiRequest);
        }
        String menuCode = apiRequest.getString("menuCode");
        //角色
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean superiorManager = false, ceoRole = false,orgManagerRole=false, financeRole = false, financeMangeRole = false,lefanRole = false,cwzjRole = false;
        orgManagerRole = isRoleUser(userRoles,108L);//机构经理
        superiorManager = isRoleUser(userRoles,109L);//分管总
        ceoRole = isRoleUser(userRoles,103L);//总经理
        financeRole = isRoleUser(userRoles,23L);//财务专员
        financeMangeRole = isRoleUser(userRoles,101L);//财务经理
        lefanRole = isRoleUser(userRoles,132L);; //董事长
        cwzjRole = isRoleUser(userRoles,150L);; //财务总监
        Boolean csRole = isRoleUser(userRoles,28L);; //测试角色


        // 1、总经理、财务可以看到所有数据  2、分管总可以看到自己分管的机构的数据 ；机构负责人可以看到自己机构的数据；个人可以看到自己的数据
        /*if(!(ceoRole || financeRole || financeMangeRole)){
            apiRequest.put("role", true);
            apiRequest.put("currentUserId",currentUserId);
            String departmentIds = apiRequest.getString("departmentIds");
            if(StringUtils.isEmpty(departmentIds)){
                if(orgManagerRole){
                    apiRequest.put("organManagerUserId",currentUserId);
                }
                if(superiorManager){
                    apiRequest.put("superiorManagerUserId",currentUserId);
                }
            }
        }*/

        apiRequest.put("role", true);
        apiRequest.put("currentUserId",currentUserId);
        if (orgManagerRole){//机构经理
            apiRequest.put("roleTwo",1);
            apiRequest.put("organManagerUserId",currentUserId);
        }
        if (superiorManager){//分管总
            apiRequest.put("roleThree",1);
            apiRequest.put("superiorManagerUserId",currentUserId);
        }
        if (financeRole){
            apiRequest.put("roleFour",1);
        }
        if (financeMangeRole){
            apiRequest.put("roleFive",1);
        }
        if (ceoRole){
            apiRequest.put("roleSix",1);
        }
        if (cwzjRole){
            apiRequest.put("roleFive2",1);
        }
        if (lefanRole){
//            apiRequest.put("roleSixsix",1);
            apiRequest.put("role",null);
            String defValue = apiRequest.getString("defValue");
            if ("yes".equals(defValue)){//如果有董事长审核的权限，则默认查询董事长审核的数据
                apiRequest.put("states","66");
            }
        }
        if (csRole){//如果有测试角色  查看所有数据
            apiRequest.put("role",null);
        }

        //查询每个角色拥有者
        String roleIds = "103,23,101,132,150";//总经理103,财务专员23,财务经理101,董事长132,财务总监150
        List<BusUserRole> users = busUserRoleMapper.selectUserByRoleIds(roleIds);

        List<FinancialReApply> list = financialReApplyMapper.list(apiRequest);
        int count= 1 ;
        if (pageFlag){
            count = financialReApplyMapper.listSize(apiRequest);
        }
        for (FinancialReApply financialReApply : list) {
            Integer state = financialReApply.getState();
            financialReApply.setStateStr(FinancialReApplyStateEnumDto.getStateNameByState(state));//状态
            financialReApply.setShowOprUserNames("");
            switch (state){
                case 1 :
                case 9 :
                case 10 :
                case 11 :
                case 12 :
                case 13 :
                case 14 :
                case 15 :
                case 17 :
                    financialReApply.setShowOprUserNames(financialReApply.getApplyUserName()); break;
                case 2 : financialReApply.setShowOprUserNames(financialReApply.getOrganManagerUserNames()); break;
                case 3 : financialReApply.setShowOprUserNames(financialReApply.getSuperiorManagerUserNames()); break;
                case 4 :
                case 7 :
                    List<BusUserRole> collect = users.stream().filter(p -> p != null && p.getRoleId().intValue() == 23).collect(Collectors.toList());
                    String names = collect.stream().map(BusUserRole :: getUserName).collect(Collectors.joining(","));
                    financialReApply.setShowOprUserNames(names); break;
                case 5 :
                    collect = users.stream().filter(p -> p != null && p.getRoleId().intValue() == 101).collect(Collectors.toList());
                    names = collect.stream().map(BusUserRole :: getUserName).collect(Collectors.joining(","));
                    financialReApply.setShowOprUserNames(names); break;
                case 55 :
                    collect = users.stream().filter(p -> p != null && p.getRoleId().intValue() == 150).collect(Collectors.toList());
                    names = collect.stream().map(BusUserRole :: getUserName).collect(Collectors.joining(","));
                    financialReApply.setShowOprUserNames(names); break;
                case 6 :
                    collect = users.stream().filter(p -> p != null && p.getRoleId().intValue() == 103).collect(Collectors.toList());
                    names = collect.stream().map(BusUserRole :: getUserName).collect(Collectors.joining(","));
                    financialReApply.setShowOprUserNames(names); break;
                case 66 :
                    collect = users.stream().filter(p -> p != null && p.getRoleId().intValue() == 132).collect(Collectors.toList());
                    names = collect.stream().map(BusUserRole :: getUserName).collect(Collectors.joining(","));
                    financialReApply.setShowOprUserNames(names); break;
            }
            if (financialReApply.getZhuanUserId() != null && financialReApply.getZhuanOprState() == 0){
                financialReApply.setStateStr("任务转交");
                financialReApply.setShowOprUserNames(financialReApply.getZhuanUserName());
            }
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    @ApiMethod(needLogin = false,descript = "垫付信息表详情",value = "backend-financial-re-apply-info")
    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        Long financialReApplyId = apiRequest.getLong("financialReApplyId");
        FinancialReApply financialReApply = financialReApplyMapper.selectByPrimaryKey(financialReApplyId);

        //承担明细
        Map map = new HashMap<>();
        map.put("financialReApplyId",financialReApplyId);
        List<FinancialCostBear> bears = financialCostBearMapper.list(map);
        financialReApply.setFinancialCostBearList(bears);

        //费用明细
        map = new HashMap<>();
        map.put("financialReApplyId",financialReApplyId);
        List<FinancialCostDetails> detailses = financialCostDetailsMapper.list(map);
        for (FinancialCostDetails detailse : detailses) {
            List<Long> ids = new ArrayList<Long>();
            ids.add(detailse.getId());
            List<FinancialFile> files = backendFinancialFileApiImpl.getFilesByIds(ids, FinancialFileTableEnumDto.FINACIAL_COST_DETAILS_ATTR);
            detailse.setFinancialFileList(files);
        }
        financialReApply.setFinancialCostDetails(detailses);


        if(financialReApply.getState()!=null){
            financialReApply.setStateStr(FinancialReApplyStateEnumDto.getStateNameByState(financialReApply.getState()));//状态
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
    }

    @ApiMethod(needLogin = false,descript = "垫付信息表操作",value = "backend-financial-re-apply-operate")
    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        Long userId = apiRequest.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        String userName = null;
        if(userInfo != null){
            userName = userInfo.getUserName();
        }

        Long financialReApplyId = apiRequest.getLong("financialReApplyId");
        FinancialReApply financialReApply = financialReApplyMapper.selectByPrimaryKey(financialReApplyId);
        String btnCode = apiRequest.getString("btnCode");
        String stepType = apiRequest.getString("stepType");
        String copy = apiRequest.getString("copy");
        String oprRemark = apiRequest.getString("oprRemark") == null ? "" : apiRequest.getString("oprRemark");
        String progressDesc = "";
        String progressName = "";
        String details = "";
        Integer progressType = 0;
        Map map = new HashMap<>();
        if("add-info".equals(btnCode)) //新增、修改
        {
            try {
                Integer reType = apiRequest.getInt("reType");
                // urgeType: 新增：add, 暂存：storage
                String urgeType = apiRequest.getString("urgeType");
                if("add".equals(urgeType) || "storage".equals(urgeType)){
                    if(financialReApply ==null || "copy".equals(copy)){
                        financialReApply = ConvertToBeanUtil.toBean(apiRequest, FinancialReApply.class);
                        if ("copy".equals(copy)){
                            financialReApply.setId(null);
                        }
                    }else{
                        financialReApply = ConvertToBeanUtil.toBean(apiRequest,financialReApply);
                    }
                    if("storage".equals(urgeType)){
                        financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_DTJ.getState());//状态 1:待提交
                    }else if("add".equals(urgeType)){
                        //如果是机构经理，直接跳过机构经理审核
//                        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(userId);
//                        Boolean orgManagerRole=false;
//                        orgManagerRole = isRoleUser(userRoles,108L);//机构经理
//                        int state = FinancialReApplyStateEnumDto.APPLY_STATE_DSHJGJL.getState();//2:待机构经理审核
//                        if(orgManagerRole){
//                            state = FinancialReApplyStateEnumDto.APPLY_STATE_DSHFGZ.getState();//3/待分管总审核
//                        }
//                        financialReApply.setState(state);//状态
                        financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_DSHJGJL.getState());//默认都是待机构经理审核
                    }

                    //公司抬头
                    StaffCompany company = staffCompanyMapper.selectByPrimaryKey(apiRequest.getLong("companyId"));
                    if(company !=null){
                        financialReApply.setCompanyId(company.getId());
                        financialReApply.setCompanyTitle(company.getName());
                    }

//                    StaffBudgetCompany staffBudgetCompany = staffBudgetCompanyMapper.selectByPrimaryKey(apiRequest.getLong("companyId"));
//                    if(staffBudgetCompany != null){
//                        financialReApply.setCompanyId(staffBudgetCompany.getId());
//                        financialReApply.setCompanyTitle(staffBudgetCompany.getName());
//                    }

                    BankInfo bankInfo = bankInfoMapper.selectByPrimaryKey(apiRequest.getLong("bankId"));
                    if(bankInfo!=null){
                        financialReApply.setBankId(bankInfo.getId());
                        financialReApply.setBankName(bankInfo.getBankName());
                    }

                    //非借款单的时候。收款人为选择的人
                    if (financialReApply.getReType() == 1 || financialReApply.getReType() == 3){
                        Long payUserId = apiRequest.getLong("payUserId");
                        StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(payUserId);
                        if (staffPersonnelInfo != null){
                            financialReApply.setPayUserId(staffPersonnelInfo.getUserId());
                            financialReApply.setPayeeName(staffPersonnelInfo.getRealName());
                        }
                    }else{
                        financialReApply.setPayUserId(null);
                    }

//                    StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(apiRequest.getLong("bearOrgId"));
//                    if(staffOrgan!=null){
//                        financialReApply.setDepartmentId(staffOrgan.getId());
//                        financialReApply.setDepartmentName(staffOrgan.getName());
//                        map = new HashMap<>();
//                        map.put("organId",staffOrgan.getId());
//                        List<StaffPersonnelInfo> personnelInfo = staffPersonnelInfoMapper.list(map);
//                        if(personnelInfo.size() >0){
//                            financialReApply.setDepartmentUserId(personnelInfo.get(0).getUserId());
//                            financialReApply.setDepartmentUserName(personnelInfo.get(0).getRealName());
//                        }
//                    }

                    //金额
                    financialReApply.setReMoney(apiRequest.getDouble("reMoney"));//报销金额/借款金额
                    if(financialReApply.getId() !=null){
                        financialReApply.setUpdateBy(userName);// 创建人id
                        financialReApply.setUpdateTime(new Date());//创建时间
                        financialReApply.setBackReason(null);
                        financialReApplyMapper.updateByPrimaryKey(financialReApply);
                    }else {
                        financialReApply.setReNo(SerialNumberUtil.getSurveyCode("BX"));
                        //提单人
                        financialReApply.setApplyUserId(userId);
                        financialReApply.setApplyUserName(userName);
                        financialReApply.setReType(reType);
                        financialReApply.setApplyTime(new Date());//申请时间
                        financialReApply.setCreateBy(userName);// 创建人id
                        financialReApply.setCreateTime(new Date());//创建时间
                        financialReApply.setDeleteFlag(0);
                        financialReApply.setUpdateTime(new Date());
                        financialReApply.setUpdateBy(userName);
                        financialReApplyMapper.insert(financialReApply);
                    }

                    //附件
                    String json = apiRequest.getString("files");//附件
                    FinancialFileTableEnumDto fileTableEnum = FinancialFileTableEnumDto.FINACIAL_RE_APPLE_ATTR;
                    if(reType == 3) {//借款单
                        fileTableEnum = FinancialFileTableEnumDto.FINACIAL_RE_LOAN_ATTR;
                    }
                    backendFinancialFileApiImpl.saveFile(financialReApply.getId(),fileTableEnum,userInfo,json);



//                    map  = new HashMap<>();
//                    map.put("financialReApplyId", financialReApply.getId());
//                    FinancialCostBear financialCostBear = financialCostBearMapper.selectOne(map);
//                    if(financialCostBear !=null){
////                            financialCostBear.setShareCost(apiRequest.getDouble("reMoney"));
//                        if(financialCostBear.getDepartmentId() != apiRequest.getLong("bearOrgId")){
//                            staffOrgan = staffOrganMapper.selectByPrimaryKey(apiRequest.getLong("bearOrgId"));
//                            if(staffOrgan!=null){
//                                financialCostBear.setDepartmentId(staffOrgan.getId());
//                                financialCostBear.setDepartmentName(staffOrgan.getName());
//                                financialCostBear.setOrganManagerUserId(staffOrgan.getOrganManagerUserId());
//                                financialCostBear.setOrganManagerState(0);
//                                financialCostBear.setSuperiorManagerUserId(staffOrgan.getSuperiorManagerUserId());
//                                financialCostBear.setSuperiorManagerState(0);
//                            }
//                        }
//                        financialCostBear.setUpdateBy(userName);
//                        financialCostBear.setUpdateTime(new Date());
//                        financialCostBearMapper.updateByPrimaryKey(financialCostBear);
//                    }else{
//                        financialCostBear = new FinancialCostBear();
////                            financialCostBear.setShareCost(apiRequest.getDouble("reMoney"));
//                        financialCostBear.setFinancialReApplyId(financialReApply.getId());
//                        financialCostBear.setCreateBy(userName);
//                        financialCostBear.setCreateTime(new Date());
//                        financialCostBear.setDeleteFlag(0);
//                        staffOrgan = staffOrganMapper.selectByPrimaryKey(apiRequest.getLong("bearOrgId"));
//                        if(staffOrgan!=null){
//                            financialCostBear.setDepartmentId(staffOrgan.getId());
//                            financialCostBear.setDepartmentName(staffOrgan.getName());
//                            financialCostBear.setOrganManagerUserId(staffOrgan.getOrganManagerUserId());
//                            financialCostBear.setOrganManagerState(0);
//                            financialCostBear.setSuperiorManagerUserId(staffOrgan.getSuperiorManagerUserId());
//                            financialCostBear.setSuperiorManagerState(0);
//                        }
//                        financialCostBearMapper.insert(financialCostBear);
//                    }
                    if(reType == 1 || reType == 2) //日常费用报销、对公支付
                    {
                        //费用明细
                        String costTypes = apiRequest.getString("costTypes");
                        if(costTypes!=null){
                            List<FinancialCostDetails> financialCostDetailses = JSONArray.parseArray(costTypes, FinancialCostDetails.class);
                            for (FinancialCostDetails financialCostDetailse : financialCostDetailses) {
                                if ("copy".equals(copy)){
                                    financialCostDetailse.setFinancialReApplyId(null);
                                }
                                if(financialCostDetailse.getFinancialReApplyId() !=null){
                                    FinancialCostDetails old = financialCostDetailsMapper.selectByPrimaryKey(financialCostDetailse.getId());

                                    FinancialCostType financialCostType = financialCostTypeMapper.selectByPrimaryKey(financialCostDetailse.getCostTypeId());
                                    if (financialCostType != null) {
                                        financialCostDetailse.setCostTypeId(financialCostType.getId());
                                        financialCostDetailse.setCostTypeName(financialCostType.getCostName());
                                    }
                                    financialCostDetailse.setCostMoney(financialCostDetailse.getCostMoney()==null?0D:financialCostDetailse.getCostMoney());
                                    financialCostDetailse.setUpdateBy(userName);
                                    financialCostDetailse.setUpdateTime(new Date());
                                    financialCostDetailse.setCreateBy(old.getCreateBy());
                                    financialCostDetailse.setCreateTime(old.getCreateTime());
                                    financialCostDetailse.setDeleteFlag(old.getDeleteFlag());

                                    financialCostDetailsMapper.updateByPrimaryKey(financialCostDetailse);
                                    String detailJson = JsonUtil.objectToJson(financialCostDetailse.getFinancialFileList());//附件
                                    List<FinancialFile> files = backendFinancialFileApiImpl.getFiles(financialCostDetailse.getId(), FinancialFileTableEnumDto.FINACIAL_COST_DETAILS_ATTR);
                                    for (FinancialFile file : files) {
                                        file.setDeleteFlag(1);
                                        financialFileMapper.updateByPrimaryKey(file);
                                    }
                                    backendFinancialFileApiImpl.saveFile(financialCostDetailse.getId(),FinancialFileTableEnumDto.FINACIAL_COST_DETAILS_ATTR,userInfo,detailJson);
                                }else{
                                    financialCostDetailse.setFinancialReApplyId(financialReApply.getId());
                                    FinancialCostType financialCostType = financialCostTypeMapper.selectByPrimaryKey(financialCostDetailse.getCostTypeId());
                                    if (financialCostType != null) {
                                        financialCostDetailse.setCostTypeId(financialCostType.getId());
                                        financialCostDetailse.setCostTypeName(financialCostType.getCostName());
                                    }
                                    financialCostDetailse.setCostMoney(financialCostDetailse.getCostMoney()==null?0D:financialCostDetailse.getCostMoney());
                                    financialCostDetailse.setCreateBy(userName);
                                    financialCostDetailse.setCreateTime(new Date());
                                    financialCostDetailse.setDeleteFlag(0);

                                    financialCostDetailsMapper.insert(financialCostDetailse);
                                    String detailJson = JsonUtil.objectToJson(financialCostDetailse.getFinancialFileList());//附件
                                    backendFinancialFileApiImpl.saveFile(financialCostDetailse.getId(),FinancialFileTableEnumDto.FINACIAL_COST_DETAILS_ATTR,userInfo,detailJson);
                                }
                            }
                        }

                        //承担明细 bearInfos 修改需求：不存在多条机构
                        String bearInfos = apiRequest.getString("bearInfos");
                        if(bearInfos != null){
                            List<FinancialCostBear> financialCostBears = JSONArray.parseArray(bearInfos, FinancialCostBear.class);
                            for (FinancialCostBear financialCostBear : financialCostBears) {
                                if ("copy".equals(copy)){
                                    financialCostBear.setFinancialReApplyId(null);
                                }
                                if(financialCostBear.getFinancialReApplyId()!=null){
                                    FinancialCostBear old = financialCostBearMapper.selectByPrimaryKey(financialCostBear.getId());
//                                financialCostBear = ConvertToBeanUtil.toBean(apiRequest,old);
                                    StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(financialCostBear.getDepartmentId());
                                    if(staffOrgan!=null){
                                        financialCostBear.setDepartmentId(staffOrgan.getId());
                                        financialCostBear.setDepartmentName(staffOrgan.getName());
                                        financialCostBear.setOrganManagerUserId(staffOrgan.getOrganManagerUserId());
                                        financialCostBear.setOrganManagerState(0);
                                        financialCostBear.setSuperiorManagerUserId(staffOrgan.getSuperiorManagerUserId());
                                        financialCostBear.setSuperiorManagerState(0);
                                    }
                                    financialCostBear.setUpdateBy(userName);
                                    financialCostBear.setUpdateTime(new Date());
                                    financialCostBear.setCreateBy(old.getCreateBy());
                                    financialCostBear.setCreateTime(old.getCreateTime());
                                    financialCostBear.setDeleteFlag(old.getDeleteFlag());
                                    financialCostBearMapper.updateByPrimaryKey(financialCostBear);
                                }else{
//                                    //承担公司不可重复
//                                    map = new HashMap<>();
//                                    map.put("financialReApplyId", financialReApply.getId());
//                                    map.put("departmentId",financialCostBear.getDepartmentId());
//                                    FinancialCostBear bear = financialCostBearMapper.selectOne(map);
//                                    if(bear!=null){
//                                        return new ApiResponse(ApiMsgEnum.FINANCIAL_BEAR_HAVE);
//                                    }
                                    financialCostBear.setFinancialReApplyId(financialReApply.getId());
                                    StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(financialCostBear.getDepartmentId());
                                    if(staffOrgan!=null){
                                        financialCostBear.setDepartmentId(staffOrgan.getId());
                                        financialCostBear.setDepartmentName(staffOrgan.getName());
                                        financialCostBear.setOrganManagerUserId(staffOrgan.getOrganManagerUserId());
                                        financialCostBear.setOrganManagerState(0);
                                        financialCostBear.setSuperiorManagerUserId(staffOrgan.getSuperiorManagerUserId());
                                        financialCostBear.setSuperiorManagerState(0);
                                    }
                                    financialCostBear.setCreateBy(userName);
                                    financialCostBear.setCreateTime(new Date());
                                    financialCostBear.setDeleteFlag(0);
                                    financialCostBearMapper.insert(financialCostBear);
                                }
                            }
                        }
                    }else if (reType == 3){
                        StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(financialReApply.getApplyUserId());
                        if (staffPersonnelInfo != null){
                            Map<String,Object> paramMap = new HashMap<>();
                            paramMap.put("financialReApplyId",financialReApply.getId());
                            paramMap.put("departmentId",staffPersonnelInfo.getOrganId());
                            FinancialCostBear financialCostBear = financialCostBearMapper.selectOne(paramMap);
                            if (financialCostBear == null){
                                financialCostBear = new FinancialCostBear();
                                financialCostBear.setFinancialReApplyId(financialReApply.getId());
                                StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(staffPersonnelInfo.getOrganId());
                                if(staffOrgan!=null){
                                    financialCostBear.setDepartmentId(staffOrgan.getId());
                                    financialCostBear.setDepartmentName(staffOrgan.getName());
                                    financialCostBear.setOrganManagerUserId(staffOrgan.getOrganManagerUserId());
                                    financialCostBear.setOrganManagerState(0);
                                    financialCostBear.setSuperiorManagerUserId(staffOrgan.getSuperiorManagerUserId());
                                    financialCostBear.setSuperiorManagerState(0);
                                }
                                financialCostBear.setShareRate(1D);
                                financialCostBear.setShareCost(financialReApply.getReMoney());
                                financialCostBear.setCreateBy(userName);
                                financialCostBear.setCreateTime(new Date());
                                financialCostBear.setDeleteFlag(0);
                                financialCostBearMapper.insert(financialCostBear);
                            }else if (financialCostBear != null){
                                financialCostBear.setShareCost(financialReApply.getReMoney());
                                financialCostBearMapper.updateByPrimaryKey(financialCostBear);
                            }
                        }

                    }

                    if("add".equals(urgeType)){
                        FinanLastUserDTO last = getLastState(financialReApply,userInfo.getUserId(), FinancialReApplyStateEnumDto.APPLY_STATE_DTJ,oprRemark);
                        financialReApply.setState(last.getState());
                        financialReApplyMapper.updateByPrimaryKey(financialReApply);
                        for (Long user : last.getUsers()) {
                            sendWechat(user,financialReApply,"yes","",userInfo);//给下一步审核人员发送微信通知
                        }
                        if (financialReApply.getState() == 7){
                            lefanStep(financialReApply,userInfo,"",userId,userName);//核销相关信息
                        }

//                        //进度
//                        setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, progressType);
//                        if(financialReApply.getState() == 3){
//                            //微信端通知
//                            map = new HashMap<>();
//                            map.put("financialReApplyId", financialReApply.getId());
//                            map.put("superiorManagerState", 0);
//                            List<FinancialCostBear> superiorBears = financialCostBearMapper.list(map);
//                            for (FinancialCostBear bear : superiorBears) {
//                                sendWechat(bear.getSuperiorManagerUserId(), financialReApply, "yes", "", userInfo);
//                            }
//                        }else if(financialReApply.getState() == 2){
//                            //微信端通知
//                            map = new HashMap<>();
//                            map.put("financialReApplyId", financialReApply.getId());
//                            map.put("organManagerState", 0);
//                            List<FinancialCostBear> superiorBears = financialCostBearMapper.list(map);
//                            for (FinancialCostBear bear : superiorBears) {
//                                sendWechat(bear.getSuperiorManagerUserId(), financialReApply, "yes", "", userInfo);
//                            }
//                        }


                        financialCostOrgDetailsMapper.deleteByReApplyId(financialReApply.getId());
                        //承担部门明细
                        map = new HashMap<>();
                        map.put("financialReApplyId",financialReApply.getId());
                        List<FinancialCostBear> bears = financialCostBearMapper.list(map);
                        //费用明细
                        map = new HashMap<>();
                        map.put("financialReApplyId",financialReApply.getId());
                        List<FinancialCostDetails> costs = financialCostDetailsMapper.list(map);
                        if (costs.size() == 1) {
                            for (FinancialCostBear bear : bears) {
                                FinancialCostOrgDetails record = new FinancialCostOrgDetails();
                                record.setFinancialReApplyId(financialReApply.getId());
                                record.setDepartmentId(bear.getDepartmentId());
                                record.setDepartmentName(bear.getDepartmentName());
                                record.setCostTypeId(costs.get(0).getCostTypeId().intValue());
                                record.setCostTypeName(costs.get(0).getCostTypeName());
                                record.setCostMoney(bear.getShareCost());
                                financialCostOrgDetailsMapper.insert(record);
                            }
                        }else if (costs.size() > 1){
                            if (bears.size() > 0){
                                for (FinancialCostDetails cost : costs) {
                                    FinancialCostOrgDetails record = new FinancialCostOrgDetails();
                                    record.setFinancialReApplyId(financialReApply.getId());
                                    record.setDepartmentId(bears.get(0).getDepartmentId());
                                    record.setDepartmentName(bears.get(0).getDepartmentName());
                                    record.setCostTypeId(cost.getCostTypeId().intValue());
                                    record.setCostTypeName(cost.getCostTypeName());
                                    record.setCostMoney(cost.getCostMoney());
                                    financialCostOrgDetailsMapper.insert(record);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
        }
        else if("delete-file".equals(btnCode))//删除附件
        {
            if ("copy".equals(copy)){
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
            }
            FinancialFile financialFile = financialFileMapper.selectByPrimaryKey(apiRequest.getLong("fileId"));
            if(financialFile!=null){
                financialFileMapper.deleteByPrimaryKey(financialFile.getId());
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
        }
        else if("delete-cost-details".equals(btnCode))//删除明细
        {
            if ("copy".equals(copy)){
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
            }
            FinancialCostDetails financialCostDetails = financialCostDetailsMapper.selectByPrimaryKey(apiRequest.getLong("costDetailsId"));
            if(financialCostDetails != null){
                financialCostDetails.setDeleteFlag(1);
                financialCostDetailsMapper.updateByPrimaryKey(financialCostDetails);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
        }
        else if ("user-revoke".equals(btnCode))//提单人撤销
        {
            financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_DTJ.getState());
            financialReApply.setUpdateBy(userName);
            financialReApply.setUpdateTime(new Date());
            financialReApplyMapper.updateByPrimaryKey(financialReApply);
            //同时清空“承担部门明细”的审核
            financialCostBearMapper.updateState(financialReApply.getId());
            //进度
            setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, progressType);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
        }
        else if ("organManager-step".equals(btnCode))//机构经理审核
        {
            switch (stepType){
                case "yes" :
                    FinanLastUserDTO last = getLastState(financialReApply,userInfo.getUserId(), FinancialReApplyStateEnumDto.APPLY_STATE_DSHJGJL,oprRemark);
                    financialReApply.setState(last.getState());
                    financialReApplyMapper.updateByPrimaryKey(financialReApply);
                    if (financialReApply.getState() != FinancialReApplyStateEnumDto.APPLY_STATE_DSHJGJL.getState()){
                        for (Long user : last.getUsers()) {
                            sendWechat(user,financialReApply,"yes","",userInfo);//给下一步审核人员发送微信通知
                        }
                    }

                    if (financialReApply.getState() == 7){
                        lefanStep(financialReApply,userInfo,"",userId,userName);//核销相关信息
                    }

                    //先更新自己的审核状态
//                    map = new HashMap<>();
//                    map.put("financialReApplyId", financialReApplyId);
//                    map.put("organManagerUserId", userId);
//                    List<FinancialCostBear> bears = financialCostBearMapper.list(map);
//                    for (FinancialCostBear bear : bears) {
//                        bear.setOrganManagerState(1);
//                        financialCostBearMapper.updateByPrimaryKey(bear);
//                    }
//                    //需判断自己是不是最后一个提交,如果是，则更改状态
//                    map = new HashMap<>();
//                    map.put("financialReApplyId", financialReApplyId);
//                    map.put("organManagerState", 0);
//                    List<FinancialCostBear> bearStates = financialCostBearMapper.list(map);
//                    if(bearStates.size() == 0){
//                        financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_DSHFGZ.getState());//状态 3:待分管总审核
//
//                        //微信端通知
//                        map = new HashMap<>();
//                        map.put("financialReApplyId", financialReApplyId);
//                        map.put("superiorManagerState", 0);
//                        List<FinancialCostBear> superiorBears = financialCostBearMapper.list(map);
//                        for (FinancialCostBear bear : superiorBears) {
//                            sendWechat(bear.getSuperiorManagerUserId(), financialReApply, stepType, "", userInfo);
//                        }
//                    }
                    break;
                case "no" :
                    financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_THJGJL.getState());//状态 9:机构经理审核退回
                    //同时清空“承担部门明细”的审核
                    financialCostBearMapper.updateState(financialReApply.getId());
                    details = apiRequest.getString("backReason");
                    financialReApply.setBackReason(details);
                    setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, progressType);
                    //微信端通知
                    sendWechat(financialReApply.getApplyUserId(), financialReApply, stepType, details, userInfo);
                    break;
            }
            financialReApply.setUpdateBy(userName);
            financialReApply.setUpdateTime(new Date());
            financialReApplyMapper.updateByPrimaryKey(financialReApply);
            //进度
//            setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, progressType);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
        }
        else if ("superiorManager-step".equals(btnCode))//分管总审核
        {
            switch (stepType){
                case "yes" :
                    FinanLastUserDTO last = getLastState(financialReApply,userInfo.getUserId(), FinancialReApplyStateEnumDto.APPLY_STATE_DSHFGZ,oprRemark);
                    financialReApply.setState(last.getState());
                    financialReApplyMapper.updateByPrimaryKey(financialReApply);
                    if (financialReApply.getState() != FinancialReApplyStateEnumDto.APPLY_STATE_DSHFGZ.getState()){
                        for (Long user : last.getUsers()) {
                            sendWechat(user,financialReApply,"yes","",userInfo);//给下一步审核人员发送微信通知
                        }
                    }
                    if (financialReApply.getState() == 7){
                        lefanStep(financialReApply,userInfo,"",userId,userName);//核销相关信息
                    }


//                    //存在多个机构时，需判断自己是不是最后一个提交
//                    //先更新自己的审核状态
//                    map = new HashMap<>();
//                    map.put("financialReApplyId", financialReApplyId);
//                    map.put("superiorManagerUserId", userId);
//                    List<FinancialCostBear> bears = financialCostBearMapper.list(map);
//                    for (FinancialCostBear bear : bears) {
//                        bear.setSuperiorManagerState(1);
//                        financialCostBearMapper.updateByPrimaryKey(bear);
//                    }
//
//                    //需判断自己是不是最后一个提交,如果是，则更改状态
//                    map = new HashMap<>();
//                    map.put("financialReApplyId", financialReApplyId);
//                    map.put("superiorManagerState", 0);
//                    List<FinancialCostBear> bearStates = financialCostBearMapper.list(map);
//                    if(bearStates.size() == 0){
//                        financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_DSHCWZY.getState());//状态 4:待财务专员审核
//
//                        //获取所有的财务专员
//                        Map roleMap = new HashMap<>();
//                        roleMap.put("roleId",23);
//                        List<BusUserRole> userRoles = busUserRoleMapper.selectBusInfo(roleMap);
//                        //微信端通知
//                        for (BusUserRole userRole : userRoles) {
//                            sendWechat(userRole.getUserId(), financialReApply, stepType, "", userInfo);
//                        }
//                    }
                    break;
                case "no" :
                    financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_THFGZ.getState());//状态 10:分管总审核退回
                    //同时清空“承担部门明细”的审核
                    financialCostBearMapper.updateState(financialReApply.getId());

                    details = apiRequest.getString("backReason");
                    financialReApply.setBackReason(details);
                    setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, progressType);
                    //微信端通知
                    sendWechat(financialReApply.getApplyUserId(), financialReApply, stepType, details, userInfo);
                    break;
            }
            financialReApply.setUpdateBy(userName);
            financialReApply.setUpdateTime(new Date());
            financialReApplyMapper.updateByPrimaryKey(financialReApply);
            //进度
//            setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, progressType);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
        }
        else if ("finance-step".equals(btnCode))//财务专员审核
        {
            switch (stepType){
                case "yes" :
                    Boolean b = false;//是否进入下一步
                    String zhuan = apiRequest.getString("zhuan");
                    if ("commit".equals(zhuan)){//转的提交审核
                        financialReApply.setZhuanOprState(1);
                        if (financialReApply.getZhuanType() == 1){//跳过我
                            b = true;
                        }else if (financialReApply.getZhuanType() == 2) { //回到我
                            b = false;
                            //添加转交人的进度
                            setProgress(financialReApply,userInfo,"",stepType,"任务转交","提交", oprRemark, progressType);
                        }
                    }else{
                        b = true;
                    }
                    if (b){
                        FinanLastUserDTO last = getLastState(financialReApply,userInfo.getUserId(), FinancialReApplyStateEnumDto.APPLY_STATE_DSHCWZY,oprRemark);
                        financialReApply.setState(last.getState());
                        financialReApplyMapper.updateByPrimaryKey(financialReApply);
                        for (Long user : last.getUsers()) {
                            sendWechat(user,financialReApply,"yes","",userInfo);//给下一步审核人员发送微信通知
                        }
                        if (financialReApply.getState() == 7){
                            lefanStep(financialReApply,userInfo,"",userId,userName);//核销相关信息
                        }
                    }
//                    financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_DSHCWJL.getState());//状态 5:待财务经理审核
//
//                    //获取所有的财务经理
//                    Map roleMap = new HashMap<>();
//                    roleMap.put("roleId",101);
//                    List<BusUserRole> userRoles = busUserRoleMapper.selectBusInfo(roleMap);
//                    //微信端通知
//                    for (BusUserRole userRole : userRoles) {
//                        sendWechat(userRole.getUserId(), financialReApply, stepType, "", userInfo);
//                    }
                    break;
                case "no" :
                    financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_THCWZY.getState());//状态 11:财务专员审核退回
                    //同时清空“承担部门明细”的审核
                    financialCostBearMapper.updateState(financialReApply.getId());

                    details = apiRequest.getString("backReason");
                    financialReApply.setBackReason(details);
                    setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, progressType);
                    //微信端通知
                    sendWechat(financialReApply.getApplyUserId(), financialReApply, stepType, details, userInfo);
                    break;
                case "zhuan" :
                    Long zhuanUserId = apiRequest.getLong("zhuanUserId");
                    Integer zhuanType = apiRequest.getInt("zhuanType");//1 转交并跳过我   2 转交并回到我
                    String zhuanRemark = apiRequest.getString("zhuanRemark");
                    financialReApply.setZhuanUserId(zhuanUserId);
                    financialReApply.setZhuanUserName(staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(zhuanUserId).getRealName());
                    financialReApply.setZhuanType(zhuanType);
                    financialReApply.setZhuanOprState(0);
                    //添加进度
                    setProgress(financialReApply,userInfo,"",stepType,"任务转交(" +(zhuanType == 1 ? "转交并跳过我" : "转交并回到我")+")","任务转交予" + financialReApply.getZhuanUserName(), zhuanRemark, progressType);
                    sendWechat(zhuanUserId, financialReApply, stepType, details, userInfo);
                    break;
            }
            financialReApply.setUpdateBy(userName);
            financialReApply.setUpdateTime(new Date());
            financialReApplyMapper.updateByPrimaryKey(financialReApply);
            //进度
//            setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, progressType);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
        }
        else if ("financeManage-step".equals(btnCode))//财务经理审核
        {
            switch (stepType){
                case "yes" :
                    Boolean b = false;//是否进入下一步
                    String zhuan = apiRequest.getString("zhuan");
                    if ("commit".equals(zhuan)){//转的提交审核
                        financialReApply.setZhuanOprState(1);
                        if (financialReApply.getZhuanType() == 1){//跳过我
                            b = true;
                        }else if (financialReApply.getZhuanType() == 2) { //回到我
                            b = false;
                            //添加转交人的进度
                            setProgress(financialReApply,userInfo,"",stepType,"任务转交","提交", oprRemark, progressType);
                        }
                    }else{
                        b = true;
                    }
                    if (b){
                        FinanLastUserDTO last = getLastState(financialReApply,userInfo.getUserId(), FinancialReApplyStateEnumDto.APPLY_STATE_DSHCWJL,oprRemark);
                        financialReApply.setState(last.getState());
                        financialReApplyMapper.updateByPrimaryKey(financialReApply);
                        for (Long user : last.getUsers()) {
                            sendWechat(user,financialReApply,"yes","",userInfo);//给下一步审核人员发送微信通知
                        }
                        if (financialReApply.getState() == 7){
                            lefanStep(financialReApply,userInfo,"",userId,userName);//核销相关信息
                        }
                    }
                    break;
                case "no" :
                    financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_THCWJL.getState());//状态 12:财务经理审核退回
                    //同时清空“承担部门明细”的审核
                    financialCostBearMapper.updateState(financialReApply.getId());

                    details = apiRequest.getString("backReason");
                    financialReApply.setBackReason(details);
                    setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, progressType);
                    //微信端通知
                    sendWechat(financialReApply.getApplyUserId(), financialReApply, stepType, details, userInfo);
                    break;
                case "zhuan" :
                    Long zhuanUserId = apiRequest.getLong("zhuanUserId");
                    Integer zhuanType = apiRequest.getInt("zhuanType");//1 转交并跳过我   2 转交并回到我
                    String zhuanRemark = apiRequest.getString("zhuanRemark");
                    financialReApply.setZhuanUserId(zhuanUserId);
                    financialReApply.setZhuanUserName(staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(zhuanUserId).getRealName());
                    financialReApply.setZhuanType(zhuanType);
                    financialReApply.setZhuanOprState(0);
                    //添加进度
                    setProgress(financialReApply,userInfo,"",stepType,"任务转交(" +(zhuanType == 1 ? "转交并跳过我" : "转交并回到我")+")","任务转交予" + financialReApply.getZhuanUserName(), zhuanRemark, progressType);
                    sendWechat(zhuanUserId, financialReApply, stepType, details, userInfo);
                    break;
            }
            financialReApply.setUpdateBy(userName);
            financialReApply.setUpdateTime(new Date());
            financialReApplyMapper.updateByPrimaryKey(financialReApply);
            //进度
//            setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, progressType);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
        }

        else if ("cwzj-step".equals(btnCode))//财务总监
        {
            switch (stepType){
                case "yes" :
                    Boolean b = false;//是否进入下一步
                    String zhuan = apiRequest.getString("zhuan");
                    if ("commit".equals(zhuan)){//转的提交审核
                        financialReApply.setZhuanOprState(1);
                        if (financialReApply.getZhuanType() == 1){//跳过我
                            b = true;
                        }else if (financialReApply.getZhuanType() == 2) { //回到我
                            b = false;
                            //添加转交人的进度
                            setProgress(financialReApply,userInfo,"",stepType,"任务转交","提交", oprRemark, progressType);
                        }
                    }else{
                        b = true;
                    }
                    if (b){
                        FinanLastUserDTO last = getLastState(financialReApply,userInfo.getUserId(), FinancialReApplyStateEnumDto.APPLY_STATE_DSHCWZJ,oprRemark);
                        financialReApply.setState(last.getState());
                        financialReApplyMapper.updateByPrimaryKey(financialReApply);
                        for (Long user : last.getUsers()) {
                            sendWechat(user,financialReApply,"yes","",userInfo);//给下一步审核人员发送微信通知
                        }
                        if (financialReApply.getState() == 7){
                            lefanStep(financialReApply,userInfo,"",userId,userName);//核销相关信息
                        }
                    }
                    break;
                case "no" :
                    financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_DSHCWJL.getState());//状态  财务经理审核

                    details = apiRequest.getString("backReason");
                    financialReApply.setBackReason(details);
                    setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, progressType);
                    //微信端通知
                    sendWechat(financialReApply.getApplyUserId(), financialReApply, stepType, details, userInfo);
                    break;
                case "zhuan" :
                    Long zhuanUserId = apiRequest.getLong("zhuanUserId");
                    Integer zhuanType = apiRequest.getInt("zhuanType");//1 转交并跳过我   2 转交并回到我
                    String zhuanRemark = apiRequest.getString("zhuanRemark");
                    financialReApply.setZhuanUserId(zhuanUserId);
                    financialReApply.setZhuanUserName(staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(zhuanUserId).getRealName());
                    financialReApply.setZhuanType(zhuanType);
                    financialReApply.setZhuanOprState(0);
                    //添加进度
                    setProgress(financialReApply,userInfo,"",stepType,"任务转交(" +(zhuanType == 1 ? "转交并跳过我" : "转交并回到我")+")","任务转交予" + financialReApply.getZhuanUserName(), zhuanRemark, progressType);
                    sendWechat(zhuanUserId, financialReApply, stepType, details, userInfo);
                    break;
            }
            financialReApply.setUpdateBy(userName);
            financialReApply.setUpdateTime(new Date());
            financialReApplyMapper.updateByPrimaryKey(financialReApply);
            //进度
//            setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, progressType);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
        }

        else if ("ceo-step".equals(btnCode))//总经理审核
        {
            switch (stepType){
                case "yes" :
                    Boolean b = false;//是否进入下一步
                    String zhuan = apiRequest.getString("zhuan");
                    if ("commit".equals(zhuan)){//转的提交审核
                        financialReApply.setZhuanOprState(1);
                        if (financialReApply.getZhuanType() == 1){//跳过我
                            b = true;
                        }else if (financialReApply.getZhuanType() == 2) { //回到我
                            b = false;
                            //添加转交人的进度
                            setProgress(financialReApply,userInfo,"",stepType,"任务转交","提交", oprRemark, progressType);
                        }
                    }else{
                        b = true;
                    }
                    if (b){
                        FinanLastUserDTO last = getLastState(financialReApply,userInfo.getUserId(), FinancialReApplyStateEnumDto.APPLY_STATE_DSHZJL,oprRemark);
                        financialReApply.setState(last.getState());
                        financialReApplyMapper.updateByPrimaryKey(financialReApply);
                        for (Long user : last.getUsers()) {
                            sendWechat(user,financialReApply,"yes","",userInfo);//给下一步审核人员发送微信通知
                        }
                        if (financialReApply.getState() == 7){
                            lefanStep(financialReApply,userInfo,"",userId,userName);//核销相关信息
                        }
                    }
//                    financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_DSHDSZ.getState());//状态 66:董事长审核
//
//                    //获取所有的董事长
//                    Map roleMap = new HashMap<>();
//                    roleMap.put("roleId",132);
//                    List<BusUserRole> userRoles = busUserRoleMapper.selectBusInfo(roleMap);
//                    //微信端通知
//                    for (BusUserRole userRole : userRoles) {
//                        sendWechat(userRole.getUserId(), financialReApply, stepType, "", userInfo);
//                    }
                    break;
                case "no" :
                    financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_THZJL.getState());//状态 13:总经理审核退回
                    //同时清空“承担部门明细”的审核
                    financialCostBearMapper.updateState(financialReApply.getId());

                    details = apiRequest.getString("backReason");
                    financialReApply.setBackReason(details);
                    setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, progressType);
                    //微信端通知
                    sendWechat(financialReApply.getApplyUserId(), financialReApply, stepType, details, userInfo);
                    break;
                case "zhuan" :
                    Long zhuanUserId = apiRequest.getLong("zhuanUserId");
                    Integer zhuanType = apiRequest.getInt("zhuanType");//1 转交并跳过我   2 转交并回到我
                    String zhuanRemark = apiRequest.getString("zhuanRemark");
                    financialReApply.setZhuanUserId(zhuanUserId);
                    financialReApply.setZhuanUserName(staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(zhuanUserId).getRealName());
                    financialReApply.setZhuanType(zhuanType);
                    financialReApply.setZhuanOprState(0);
                    //添加进度
                    setProgress(financialReApply,userInfo,"",stepType,"任务转交(" +(zhuanType == 1 ? "转交并跳过我" : "转交并回到我")+")","任务转交予" + financialReApply.getZhuanUserName(), zhuanRemark, progressType);
                    sendWechat(zhuanUserId, financialReApply, stepType, details, userInfo);
                    break;
            }
            financialReApply.setUpdateBy(userName);
            financialReApply.setUpdateTime(new Date());
            financialReApplyMapper.updateByPrimaryKey(financialReApply);
            //进度
//            setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, progressType);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
        }
        else if ("lefan-step".equals(btnCode))//董事长审核
        {
            switch (stepType){
                case "yes" :
                    Boolean b = false;//是否进入下一步
                    String zhuan = apiRequest.getString("zhuan");
                    if ("commit".equals(zhuan)){//转的提交审核
                        financialReApply.setZhuanOprState(1);
                        if (financialReApply.getZhuanType() == 1){//跳过我
                            b = true;
                        }else if (financialReApply.getZhuanType() == 2) { //回到我
                            b = false;
                            //添加转交人的进度
                            setProgress(financialReApply,userInfo,"",stepType,"任务转交","提交", oprRemark, progressType);
                        }
                    }else{
                        b = true;
                    }
                    if (b){
                        FinanLastUserDTO last = getLastState(financialReApply,userInfo.getUserId(), FinancialReApplyStateEnumDto.APPLY_STATE_DSHDSZ,oprRemark);
                        financialReApply.setState(last.getState());
                        financialReApplyMapper.updateByPrimaryKey(financialReApply);
                        for (Long user : last.getUsers()) {
                            sendWechat(user,financialReApply,"yes","",userInfo);//给下一步审核人员发送微信通知
                        }
                        if (financialReApply.getState() == 7){
                            lefanStep(financialReApply,userInfo,"",userId,userName);//核销相关信息
                        }
                    }

//                    financialReApply = lefanStep(financialReApply,userInfo,details,userId,userName);
                    break;
                case "no" :
                    financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_THZJL.getState());//状态 13:总经理审核退回
                    //同时清空“承担部门明细”的审核
                    financialCostBearMapper.updateState(financialReApply.getId());

                    details = apiRequest.getString("backReason");
                    financialReApply.setBackReason(details);
                    setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, progressType);
                    //微信端通知
                    sendWechat(financialReApply.getApplyUserId(), financialReApply, stepType, details, userInfo);
                    break;
                case "zhuan" :
                    Long zhuanUserId = apiRequest.getLong("zhuanUserId");
                    Integer zhuanType = apiRequest.getInt("zhuanType");//1 转交并跳过我   2 转交并回到我
                    String zhuanRemark = apiRequest.getString("zhuanRemark");
                    financialReApply.setZhuanUserId(zhuanUserId);
                    financialReApply.setZhuanUserName(staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(zhuanUserId).getRealName());
                    financialReApply.setZhuanType(zhuanType);
                    financialReApply.setZhuanOprState(0);
                    //添加进度
                    setProgress(financialReApply,userInfo,"",stepType,"任务转交(" +(zhuanType == 1 ? "转交并跳过我" : "转交并回到我")+")","任务转交予" + financialReApply.getZhuanUserName(), zhuanRemark, progressType);
                    sendWechat(zhuanUserId, financialReApply, stepType, details, userInfo);
                    break;
            }
            financialReApply.setUpdateBy(userName);
            financialReApply.setUpdateTime(new Date());
            financialReApplyMapper.updateByPrimaryKey(financialReApply);
            //进度
//            setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, progressType);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
        }
        else if("repayment".equals(btnCode))//财务的：新增还款
        {
            String repayTime = apiRequest.getString("repayTime");

            Double repayCurMoney = apiRequest.getDouble("repayCurMoney");
            if(repayCurMoney <=0){
                return new ApiResponse(ApiMsgEnum.FINANCIAL_RE_APPLY_ZERO_ERROR);
            }
            BigDecimal data1 = new BigDecimal(financialReApply.getRepaymentMoney()).setScale(2,BigDecimal.ROUND_HALF_UP);//待还款金额
            BigDecimal data2 = new BigDecimal(repayCurMoney).setScale(2,BigDecimal.ROUND_HALF_UP);//实际还款金额
            if(data1.compareTo(data2) ==0){//代表相等
                financialReApply.setRepaymentMoney(0D);
                financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_YJKHX.getState());//状态 16:已借款核销
            }else if(data1.compareTo(data2) ==1){
                financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_BFJKHX.getState());//状态 15:部分借款核销
                //重新计算待还款金额
                financialReApply.setRepaymentMoney(financialReApply.getRepaymentMoney()-apiRequest.getDouble("repayCurMoney"));
            }else if(data1.compareTo(data2) == -1){
                return new ApiResponse(ApiMsgEnum.FINANCIAL_RE_APPLY_MONEY_ERROR);
            }

            //进度传值
            progressName = "还款" + data2 + "元，还款时间："+ repayTime;
            progressDesc = "财务人员："+ userName;
            details = "剩余待还款/待核销：" + financialReApply.getRepaymentMoney() + "元";
            //进度(特殊处理，因为保存附件时，需要进度id)
            //setProgress(financialReApply,userInfo,btnCode,stepType,progressName,progressDesc, details, 3);
            FinancialReProgres progress = new FinancialReProgres();
            progress.setFinancialReApplyId(financialReApplyId);
            progress.setProgressUserId(userId);
            progress.setProgressUserName(userName);
            progress.setProgressName(progressName);
            progress.setProgressDesc(progressDesc);
            progress.setProgressTime(new Date());
            progress.setCreateBy(userName);
            progress.setCreateTime(new Date());
            progress.setDeleteFlag(0);
            progress.setStateStr(FinancialReApplyStateEnumDto.getStateNameByState(financialReApply.getState()));
            progress.setDetails(details);
            progress.setProgressType(3);
            financialReProgresMapper.insert(progress);

            //附件·
            String json = apiRequest.getString("files");//附件
            backendFinancialFileApiImpl.saveFile(progress.getId(),FinancialFileTableEnumDto.FINACIAL_RE_BACK_ATTR,userInfo,json);
            financialReApply.setUpdateBy(userName);
            financialReApply.setUpdateTime(new Date());
            financialReApplyMapper.updateByPrimaryKey(financialReApply);

            return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);

        }
        else if ("del-info".equals(btnCode))//删除
        {
            financialReApply.setDeleteFlag(1);
            financialReApply.setUpdateBy(userName);
            financialReApply.setUpdateTime(new Date());
            financialReApplyMapper.updateByPrimaryKey(financialReApply);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
        }
        else if ("zhuan-veto".equals(btnCode)){//转交的驳回
            financialReApply.setState(1);//待提交
            financialReApply.setZhuanOprState(0);
            financialReApply.setZhuanType(null);
            financialReApply.setZhuanUserId(null);
            financialReApply.setZhuanUserName(null);
            //同时清空“承担部门明细”的审核
            financialCostBearMapper.updateState(financialReApply.getId());

            details = apiRequest.getString("backReason");
            financialReApply.setBackReason(details);
            setProgress(financialReApply,userInfo,btnCode,stepType,progressName,"驳回", details, 2);
            //微信端通知
            sendWechat(financialReApply.getApplyUserId(), financialReApply, "no", details, userInfo);
            financialReApplyMapper.updateByPrimaryKey(financialReApply);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    private FinancialReApply lefanStep(FinancialReApply financialReApply,UserInfo userInfo,String details,Long userId,String userName){
        Map<String,Object> map =  null;
        financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_DFK.getState());//状态 7:待付款
        //生成付款管理记录（2：对公支付和3：借款 直接生成付款管理； 1、日常费用报销-- 涉及借款单自动核销，只有该笔报销的金额，大于该员工所有的借款单，才会生成付款管理）
        // 1：日常费用报销，2：对公支付，3：借款
        Integer payType = 9;
        if(financialReApply.getReType() ==2){
            payType = 10;
        }else if(financialReApply.getReType() ==3){
            payType = 11;
        }

        Double appPayMoney = financialReApply.getReMoney();//
        Double allMoney = appPayMoney;//此笔“日常费用报销”的申请金额
        Boolean payFlag = true;
        if(financialReApply.getReType() == 1){
            map = new HashMap<>();
            map.put("payUserId",financialReApply.getPayUserId() == null ? -1 : financialReApply.getPayUserId());//根据收款人抵扣。2021年10月29日 需求变更
            map.put("companyId",financialReApply.getCompanyId());//2021年5月20日  公司也要一样
            map.put("reType",3);//借款单
            map.put("states","14,15");//没有还完的借款单
            map.put("order",3);
            List<FinancialReApply> financialReApplies = financialReApplyMapper.list(map);


            if(financialReApplies.size() > 0)//不存在借款单，直接生成“日常费用报销”
            {
                String reNoStr = "";
                for (int i = financialReApplies.size() - 1; i >= 0; i--) {
                    FinancialReApply reApply = financialReApplies.get(i);

                    String stateName1 = FinancialReApplyStateEnumDto.getStateNameByState(reApply.getState());
                    Integer progressType1 = 4;
                    Double reMoney = 0D;//核销的金额
                    String details1 = "";

                    Double thisRepaymentMoney =  reApply.getRepaymentMoney();
                    BigDecimal data1 = new BigDecimal(thisRepaymentMoney);//该笔“借款单”待还款金额 小-1 大1
                    BigDecimal data2 = new BigDecimal(allMoney);//实际还款金额
                    System.out.println(data1.compareTo(data2));
                    if(data1.compareTo(data2) == 0)//相等
                    {
                        reMoney = thisRepaymentMoney;
                        details1 = "剩余待还款/待核销：0元。（关联报销："+financialReApply.getReNo()+")";
                        reApply.setRepaymentMoney(0D);
                        reApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_YJKHX.getState());//状态 16:已借款核销
                        reApply.setUpdateTime(new Date());
                        reApply.setUpdateBy(userInfo.getUserName());
                        financialReApplyMapper.updateByPrimaryKey(reApply);
                        backendFinancialReProgresApiImpl.saveProgress(reApply.getId(),userInfo.getUserId(),userInfo.getUserName(),"自动核销"+reMoney+"元","日常费用报销",stateName1, details1 , progressType1);
                        backendFinancialReProgresApiImpl.saveProgress(financialReApply.getId(),userInfo.getUserId(),userInfo.getUserName(),"自动核销"+reMoney+"元","日常费用报销",stateName1, "关联借款单" + reApply.getReNo() , progressType1);
                        //整体结束
                        financialReApply.setPayTime(new Date());
                        financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_YFK.getState());//状态 8:已付款
                        reNoStr  = reNoStr + reApply.getReNo()+"  ";
                        payFlag = false;
                        break;
                    }
                    else if(data1.compareTo(data2) == -1)//借款金额更小
                    {
                        reMoney = thisRepaymentMoney;
                        details1 = "剩余待还款/待核销：0元。（关联报销："+financialReApply.getReNo()+")";
                        reApply.setRepaymentMoney(0D);
                        allMoney = allMoney - thisRepaymentMoney;
                        reApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_YJKHX.getState());//状态 16:已借款核销
                        reApply.setUpdateTime(new Date());
                        reApply.setUpdateBy(userInfo.getUserName());
                        financialReApplyMapper.updateByPrimaryKey(reApply);
                        backendFinancialReProgresApiImpl.saveProgress(reApply.getId(),userInfo.getUserId(),userInfo.getUserName(),"自动核销"+reMoney+"元","日常费用报销",stateName1, details1 , progressType1);
                        backendFinancialReProgresApiImpl.saveProgress(financialReApply.getId(),userInfo.getUserId(),userInfo.getUserName(),"自动核销"+reMoney+"元","日常费用报销",stateName1, "关联借款单" + reApply.getReNo() , progressType1);

                        appPayMoney = allMoney;
                        reNoStr  = reNoStr + reApply.getReNo()+"  ";
                        continue;
                        //继续下一个借款单
                    }
                    else if(data1.compareTo(data2) == 1)//借款金额更大
                    {
                        reMoney = thisRepaymentMoney - allMoney;
                        details1 = "剩余待还款/待核销：" + DecimalUtil.twoDecimalTOFourFromFive(reMoney) + "元。（关联报销："+financialReApply.getReNo()+")";
                        reApply.setRepaymentMoney(thisRepaymentMoney - allMoney);
                        reApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_BFJKHX.getState());//状态 15:部分借款核销
                        reApply.setUpdateTime(new Date());
                        reApply.setUpdateBy(userInfo.getUserName());
                        financialReApplyMapper.updateByPrimaryKey(reApply);
                        backendFinancialReProgresApiImpl.saveProgress(reApply.getId(),userInfo.getUserId(),userInfo.getUserName(),"自动核销"+allMoney+"元","日常费用报销",stateName1, details1 , progressType1);
                        backendFinancialReProgresApiImpl.saveProgress(financialReApply.getId(),userInfo.getUserId(),userInfo.getUserName(),"自动核销"+reMoney+"元","日常费用报销",stateName1, "关联借款单" + reApply.getReNo() , progressType1);

                        //整体结束
                        financialReApply.setPayTime(new Date());
                        financialReApply.setState(FinancialReApplyStateEnumDto.APPLY_STATE_YFK.getState());//状态 8:已付款
                        appPayMoney = 0D;
                        reNoStr  = reNoStr + reApply.getReNo()+"  ";
                        payFlag = false;
                        break;
                    }
                }

                details = "关联借款单：" + reNoStr;
            }
        }

        if(appPayMoney > 0 && payFlag){
            map = new HashMap<>();
            map.put("payType",payType);
            map.put("appPayMoney",appPayMoney);
            map.put("applyUserId",financialReApply.getApplyUserId());
            map.put("payKeyId",financialReApply.getId());
            map.put("currUserId",userId);
            map.put("currUserName",userName);
            map.put("companyId",financialReApply.getCompanyId());
            StaffCompany staffCompany = staffCompanyMapper.selectByPrimaryKey(financialReApply.getCompanyId());
            if (staffCompany != null){
                map.put("companyName",staffCompany.getName());
            }
//            StaffBudgetCompany staffBudgetCompany = staffBudgetCompanyMapper.selectByPrimaryKey(financialReApply.getCompanyId());
//            if (staffBudgetCompany != null){
//                map.put("companyName",staffBudgetCompany.getName());
//            }
            map.put("remark",financialReApply.getReNo() + "(" + financialReApply.getReReasons() + ")");
            financialReApplyMapper.generateSurveyPayInfo(map);
        }
        //总经理审核通过时间
        financialReApply.setCeoEndTime(new Date());
        return financialReApply;
    }

    private FinanLastUserDTO getLastState(FinancialReApply financialReApply,Long curUserId,FinancialReApplyStateEnumDto curReApplyStateEnum,String oprRemark){
        int curState = curReApplyStateEnum.getState();
        List<FinanLastUserDTO> nodeUsers = new ArrayList<>();
        Map map = new HashMap<>();
        map.put("financialReApplyId", financialReApply.getId());
        List<FinancialCostBear> breas = financialCostBearMapper.list(map);
        for (FinancialCostBear brea : breas) {
            StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(brea.getDepartmentId());
            if (staffOrgan != null){
                brea.setWarnMoney(staffOrgan.getWarnMoney() == null ? 0D : staffOrgan.getWarnMoney());
            }
        }
        financialReApply.setFinancialCostBearList(breas);
        //查询每个角色拥有者
        String roleIds = "103,23,101,132,150";//总经理103,财务专员23,财务经理101,董事长132,财务总监150
        List<BusUserRole> userRoles = busUserRoleMapper.selectUserByRoleIds(roleIds);
        List<Long> users = new ArrayList<>();
        users.add(financialReApply.getApplyUserId());
        nodeUsers.add(new FinanLastUserDTO(1,users));//申请人
        nodeUsers.add(new FinanLastUserDTO(2,breas.stream().map(FinancialCostBear :: getOrganManagerUserId).collect(Collectors.toList())));//机构经理
        nodeUsers.add(new FinanLastUserDTO(3,breas.stream().map(FinancialCostBear :: getSuperiorManagerUserId).collect(Collectors.toList())));//分管总
        nodeUsers.add(new FinanLastUserDTO(4,userRoles.stream().filter(p -> p != null && p.getRoleId().intValue() == 23).map(BusUserRole::getUserId).collect(Collectors.toList())));//财务专员
        nodeUsers.add(new FinanLastUserDTO(5,userRoles.stream().filter(p -> p != null && p.getRoleId().intValue() == 101).map(BusUserRole::getUserId).collect(Collectors.toList())));//财务经理
        nodeUsers.add(new FinanLastUserDTO(6,userRoles.stream().filter(p -> p != null && p.getRoleId().intValue() == 103).map(BusUserRole::getUserId).collect(Collectors.toList())));//总经理
        nodeUsers.add(new FinanLastUserDTO(66,userRoles.stream().filter(p -> p != null && p.getRoleId().intValue() == 132).map(BusUserRole::getUserId).collect(Collectors.toList())));//董事长
        nodeUsers.add(new FinanLastUserDTO(55,userRoles.stream().filter(p -> p != null && p.getRoleId().intValue() == 150).map(BusUserRole::getUserId).collect(Collectors.toList())));//财务总监
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        paramMap.put("financialReApplyId",financialReApply.getId());
        List<FinancialReProgres> progress = financialReProgresMapper.list(paramMap);
        List<Long> progressUserIds = progress.stream().map(FinancialReProgres :: getProgressUserId).collect(Collectors.toList());
        if (!progressUserIds.contains(financialReApply.getApplyUserId())) {
            progressUserIds.add(financialReApply.getApplyUserId());
        }
        if (!progressUserIds.contains(curUserId)){
            progressUserIds.add(curUserId);
        }
        if (curState == 1){
            setProgress(financialReApply,userInfoMapper.selectByPrimaryKey(curUserId),"add-info","yes","","", oprRemark, 1);
            for (FinancialCostBear brea : breas) {
                if (brea.getOrganManagerUserId().intValue() == curUserId.intValue()) {
                    brea.setOrganManagerState(1);
                }
                if (brea.getSuperiorManagerUserId().intValue() == curUserId.intValue()){
                    brea.setSuperiorManagerState(1);
                }
                financialCostBearMapper.updateByPrimaryKey(brea);
            }
        }
        if (curState == 2){
            setProgress(financialReApply,userInfoMapper.selectByPrimaryKey(curUserId),"organManager-step","yes","","", oprRemark, 1);
            for (FinancialCostBear brea : breas) {
                if (brea.getOrganManagerUserId().intValue() == curUserId.intValue()) {
                    brea.setOrganManagerState(1);
                }
                if (brea.getSuperiorManagerUserId().intValue() == curUserId.intValue()){
                    brea.setSuperiorManagerState(1);
                }
                financialCostBearMapper.updateByPrimaryKey(brea);
            }
        }
        if (curState == 3){
            setProgress(financialReApply,userInfoMapper.selectByPrimaryKey(curUserId),"superiorManager-step","yes","","", oprRemark, 1);
            for (FinancialCostBear brea : breas) {
                if (brea.getSuperiorManagerUserId().intValue() == curUserId.intValue()){
                    brea.setSuperiorManagerState(1);
                    financialCostBearMapper.updateByPrimaryKey(brea);
                }
            }
        }
        if (curState == 4){
            setProgress(financialReApply,userInfoMapper.selectByPrimaryKey(curUserId),"finance-step","yes","","", oprRemark, 1);
        }
        if (curState == 5){
            setProgress(financialReApply,userInfoMapper.selectByPrimaryKey(curUserId),"financeManage-step","yes","","", oprRemark, 1);
        }
        if (curState == 55){//财务总监
            setProgress(financialReApply,userInfoMapper.selectByPrimaryKey(curUserId),"cwzj-step","yes","","", oprRemark, 1);
        }
        if (curState == 6){
            setProgress(financialReApply,userInfoMapper.selectByPrimaryKey(curUserId),"ceo-step","yes","","", oprRemark, 1);
        }
        if (curState == 66){
            setProgress(financialReApply,userInfoMapper.selectByPrimaryKey(curUserId),"lefan-step","yes","","", oprRemark, 1);
            financialReApply.setCeoEndTime(new Date());
        }
        Boolean org = true,sup = true;
        for (FinancialCostBear brea : breas) {
            if (curState == 4 || curState == 5 || curState == 55 || curState == 6 || curState == 66){
                if (brea.getOrganManagerState() != 1 || brea.getSuperiorManagerState() != 1){
                    brea.setOrganManagerState(1);
                    brea.setSuperiorManagerState(1);
                    financialCostBearMapper.updateByPrimaryKey(brea);
                }
            }
            if (brea.getOrganManagerState() == 0){
                org = false;
            }
            if (brea.getSuperiorManagerState() == 0){
                sup = false;
            }
        }

        FinanLastUserDTO last = new FinanLastUserDTO();
        if (org && sup){
            last.setState(getLastStateCallBack(financialReApply,curUserId,nodeUsers,curReApplyStateEnum,progressUserIds));
        }else{
            if (!org){
                last.setState(2);
            }else if (!sup){
                last.setState(3);
            }
        }
        for (FinanLastUserDTO nodeUser : nodeUsers) {
            if (nodeUser.getState() == last.getState())
                last.setUsers(nodeUser.getUsers());
        }
        if (last.getUsers() == null){
            last.setUsers(new ArrayList<>());
        }
        return last;
    }

    /**
     * 回调 获取状态
     * @param nodeUsers
     * @param curReApplyStateEnum
     * @return
     */
    private int getLastStateCallBack(FinancialReApply financialReApply,Long curUserId,List<FinanLastUserDTO> nodeUsers,FinancialReApplyStateEnumDto curReApplyStateEnum,List<Long> progressUserIds){
        int curState = curReApplyStateEnum.getState();
        List<Long> lastUsers = new ArrayList<>();
        List<Long> afterUsers = new ArrayList<>();
        if (curState == 1){
            for (FinanLastUserDTO item : nodeUsers) {
                if (item.getState() == 2){
                    lastUsers.addAll(item.getUsers());
                }
                if (item.getState() == 1){
                    afterUsers.addAll(item.getUsers());
                }
            }
        }
        if (curState == 2){
            for (FinanLastUserDTO item : nodeUsers) {
                if (item.getState() == 3){
                    lastUsers.addAll(item.getUsers());
                }
                if (item.getState() == 1 || item.getState() == 2){
                    afterUsers.addAll(item.getUsers());
                }
            }
        }
        if (curState == 3){
            for (FinanLastUserDTO item : nodeUsers) {
                if (item.getState() == 4){
                    lastUsers.addAll(item.getUsers());
                }
                if (item.getState() == 1 || item.getState() == 2 || item.getState() == 3){
                    afterUsers.addAll(item.getUsers());
                }
            }
        }
        if (curState == 4){
            for (FinanLastUserDTO item : nodeUsers) {
                if (item.getState() == 5){
                    lastUsers.addAll(item.getUsers());
                }
                if (item.getState() == 1 || item.getState() == 2 || item.getState() == 3 || item.getState() == 4){
                    afterUsers.addAll(item.getUsers());
                }
            }
        }
        if (curState == 5){
            for (FinanLastUserDTO item : nodeUsers) {
                if (item.getState() == 55){
                    lastUsers.addAll(item.getUsers());
                }
                if (item.getState() == 1 || item.getState() == 2 || item.getState() == 3 || item.getState() == 4 || item.getState() == 5){
                    afterUsers.addAll(item.getUsers());
                }
            }
        }

        if (curState == 55){
            for (FinanLastUserDTO item : nodeUsers) {
                if (item.getState() == 6){
                    lastUsers.addAll(item.getUsers());
                }
                if (item.getState() == 1 || item.getState() == 2 || item.getState() == 3 || item.getState() == 4 || item.getState() == 5 || item.getState() == 55){
                    afterUsers.addAll(item.getUsers());
                }
            }
        }

        if (curState == 6){
            for (FinanLastUserDTO item : nodeUsers) {
                if (item.getState() == 66){
                    lastUsers.addAll(item.getUsers());
                }
                if (item.getState() == 1 || item.getState() == 2 || item.getState() == 3 || item.getState() == 4 || item.getState() == 5 || item.getState() == 55 || item.getState() == 6){
                    afterUsers.addAll(item.getUsers());
                }
            }
        }

        if (curState == 66){
            return 7;//待付款
        }

        Boolean auto = false;// 是否自动跳转下一步
        for (Long lastUser : lastUsers) {
            for (Long afterUser : afterUsers) {
                if (lastUser.intValue() == afterUser.intValue()){//只要下一步审核人  在之前步骤里边存在过。 则自动跳转下一步
                    for (Long progressUserId : progressUserIds) {
                        if (lastUser.intValue() == progressUserId.intValue()){// 下一步审核人 在之前步骤里边存在。 同时在进度里边也存在。 则自动审核通过
                            auto = true;
                            break;
                        }
                    }
                }
                if (auto){
                    break;
                }
            }
            if (auto){
                break;
            }
        }
        int lastState = curState == 1 ? 2 : curState == 2 ? 3 : curState == 3 ? 4 : curState == 4 ? 5 : curState == 5 ? 55 : curState == 55 ? 6 : curState == 6 ? 66 : curState == 66 ? 7 : -1;
        //总金额小于1W  总经理董事长自动审核通过---------------此条需求 2021年3月16日 已作废。
        //小于5W   总经理审核，董事长自动审核通过
        //总金额大于等于5W  董事长审核
        Double reMoney = financialReApply.getReMoney();
        if (reMoney == null){
            financialReApply.setReMoney(0D);
        }
//        if (reMoney < 10000 && (lastState == 6 || lastState == 66)){
//            auto = true;
//        }
//        if (reMoney >= 10000  &&  reMoney < 50000 && (lastState == 66)){
//            auto = true;
//        }

        if (lastState == 6){ //如果是总经理审核
            List<FinancialCostBear> costBears = financialReApply.getFinancialCostBearList();
            if (costBears != null){
                Double maxWarnMoney = Collections.max(costBears.stream().map(FinancialCostBear :: getWarnMoney).collect(Collectors.toList()));
                if (reMoney <= maxWarnMoney){
                    auto = true;
                }
            }
        }
        if (reMoney < 50000 && (lastState == 66)){//如果金额小于5W，则董事长自动审核通过
            auto = true;
            curUserId = lastUsers.size() > 0 ? lastUsers.get(0) : curUserId;
        }

        if (reMoney < 3000 && lastState == 55){//财务总监且小于2W  2025年4月14日  时间改成小于3000自动审核
            auto = true;
            curUserId = lastUsers.size() > 0 ? lastUsers.get(0) : curUserId;
        }

        if (auto){
            String code = lastState == 1 ? "add-info" : lastState == 2 ? "organManager-step" : lastState == 3 ? "superiorManager-step" : lastState == 4
                    ? "finance-step" : lastState == 5 ? "financeManage-step" : lastState == 55 ? "cwzj-step" : lastState == 6 ? "ceo-step" : lastState == 66 ? "lefan-step" : "";
//            if (!progressUserIds.contains(curUserId)){//如果进度不存在当前这个人
//                if (progressUserIds.size() > 0){
//                    curUserId = progressUserIds.get(0);
//                }else{
//                    curUserId = lastUsers.size() > 0 ? lastUsers.get(0) : curUserId;
//                }
//            }
            for (Long lastUser : lastUsers) {
                for (Long progressUserId : progressUserIds) {
                    if (lastUser.intValue() == progressUserId.intValue()){
                        curUserId = lastUser;
                    }
                }
            }

            if (lastState == 6){
                List<FinanLastUserDTO> collect = nodeUsers.stream().filter(p -> p.getState() == 6).collect(Collectors.toList());
                if (collect.size() > 0){
                    if (collect.get(0).getUsers().size() > 0) {
                        curUserId = collect.get(0).getUsers().get(0);
                    }
                }
            }

            if (lastState == 55){
                List<FinanLastUserDTO> collect = nodeUsers.stream().filter(p -> p.getState() == 55).collect(Collectors.toList());
                if (collect.size() > 0){
                    if (collect.get(0).getUsers().size() > 0) {
                        curUserId = collect.get(0).getUsers().get(0);
                    }
                }
            }

            if (lastState == 66){
                List<FinanLastUserDTO> collect = nodeUsers.stream().filter(p -> p.getState() == 66).collect(Collectors.toList());
                if (collect.size() > 0){
                    if (collect.get(0).getUsers().size() > 0) {
                        curUserId = collect.get(0).getUsers().get(0);
                    }
                }
//                for (FinanLastUserDTO nodeUser : nodeUsers) {
//                    curUserId = nodeUser.getUsers().size() > 0 ? nodeUser.getUsers().get(0).longValue() : curUserId;
//                }
            }

            if (auto && lastState == 55){//如果是财务总监的自动审核 则不需要节点进度

            }else{
                setProgress(financialReApply,userInfoMapper.selectByPrimaryKey(curUserId),code,"yes","","", "自动审核", 1);
            }
            return getLastStateCallBack(financialReApply,curUserId,nodeUsers,FinancialReApplyStateEnumDto.get(lastState),progressUserIds);
        }
        return lastState;
    }


    @ApiMethod(needLogin = false,descript = "垫付信息表ajax",value = "ajax-data-financial-re-apply")
    @Override
    public ApiResponse ajaxData(ApiRequest apiRequest) {
        Long userId = apiRequest.getLong("operatorId");
        String dataType = apiRequest.getString("dataType");
        switch (dataType)
        {
            case "financial-re-apply-state-list"://每刻报销状态
            {
                List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(userId);
                Boolean lefanRole = isRoleUser(userRoles,132L);; //董事长
                List<StatesDTO> states = new ArrayList<StatesDTO>();
                FinancialReApplyStateEnumDto[] values = FinancialReApplyStateEnumDto.values();
                for (FinancialReApplyStateEnumDto value : values) {
                    StatesDTO dto = new StatesDTO(value.getState(), value.getStateName(), value.getChecked());
                    if (lefanRole){
                        dto.setChecked(false);
                        if (value.getState() == 66){
                            dto.setChecked(true);
                        }
                    }
                    states.add(dto);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,states.size(),states);
            }
            case "financial-cost-type-list"://费用类型
            {
                List<FinancialCostType> list = financialCostTypeMapper.list(apiRequest);
                return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
            }
            case "billing-apply-corporation"://公司抬头
            {
                List<StaffCompany> list = staffCompanyMapper.list(apiRequest);
//                List<StaffBudgetCompany> list = staffBudgetCompanyMapper.list(apiRequest);
                return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
            }
            case "survey-organ-list"://承担部门
            {
                String searchType = apiRequest.getString("searchType");//查询全部all，部分查询part
                apiRequest.put("state",0);
                List<StaffOrgan> list = staffOrganMapper.list(apiRequest);
                if("part".equals(searchType)){ //根据当前登录人的角色，如果是机构经理，分管总，仅仅查询自己管辖的机构
                    Long currentUserId = getCurrentUserId(apiRequest);
                    List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
                    Boolean superiorManager = false, ceoRole = false,orgManagerRole=false, financeRole = false, financeMangeRole = false;
                    orgManagerRole = isRoleUser(userRoles,108L);//机构经理
                    superiorManager = isRoleUser(userRoles,109L);//分管总
                    ceoRole = isRoleUser(userRoles,103L);//总经理
                    financeRole = isRoleUser(userRoles,23L);//财务专员
                    financeMangeRole = isRoleUser(userRoles,101L);//财务经理

                    if(!(ceoRole || financeRole || financeMangeRole)){
                        ApiRequest req = new ApiRequest();
                        req.put("searchFrom","financialA");//代表是来源已“每刻报销”
//                        StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(currentUserId);
                        //当前登录人所在机构
//                        req.put("organId", staffPersonnelInfo.getOrganId());
                        if(orgManagerRole){
//                            req.put("organManagerUserId",currentUserId);
                            req.put("organManagerUserIdA",currentUserId);
                        }
                        if(superiorManager){
//                            req.put("superiorManagerUserId",currentUserId);
                            req.put("superiorManagerUserIdA",currentUserId);
                        }
                        list = staffOrganMapper.list(req);
                    }

                }

                return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
            }
            case "staff-personnel-info-list"://提单人：根据传入的机构id集合查询
            {
                ApiRequest req = new ApiRequest();
                String organIds = apiRequest.getString("organIds");
                Long currentUserId = getCurrentUserId(apiRequest);

                req.put("organIds", organIds);
                List<StaffPersonnelInfo> list = staffPersonnelInfoMapper.list(req);

                //当前登录人是否在 organIds中，如果不存在，需加上该人
                StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(currentUserId);
                Boolean myOrgan = false;
                if (!StringUtils.isEmpty(organIds)) {
                    String [] organIdList = organIds.split(",");
                    for (String organId : organIdList) {
                        if (!StringUtils.isEmpty(organId)){
                            if (staffPersonnelInfo.getOrganId().intValue() == Integer.parseInt(organId)){
                                myOrgan = true;
                            }
                        }
                    }
                }
                if(!myOrgan){
                    list.add(staffPersonnelInfo);
                }

                return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
            }
            case "bank-info-list"://银行信息
            {
                List<BankInfo> list = bankInfoMapper.selectBankInfoList(new HashMap<>());
                return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
            }
            case "get-info"://详情
            {
                Long financialReApplyId = apiRequest.getLong("financialReApplyId");
                FinancialReApply financialReApply = financialReApplyMapper.selectByPrimaryKey(financialReApplyId);
                List<Long> ids = new ArrayList<Long>();
                ids.add(financialReApply.getId());
                FinancialFileTableEnumDto fileTableEnum = FinancialFileTableEnumDto.FINACIAL_RE_APPLE_ATTR;//每刻报销申请单凭证
                if(financialReApply.getReType() == 3) {//借款单
                    fileTableEnum = FinancialFileTableEnumDto.FINACIAL_RE_LOAN_ATTR;
                }
                List<FinancialFile> files = backendFinancialFileApiImpl.getFilesByIds(ids, fileTableEnum);
                if ("copy".equals(apiRequest.getString("copy"))){
                    files.clear();
                }
                financialReApply.setFinancialFileList(files);


                //承担明细
                //如果是机构经理审核或者分管总审核的步骤。  只看自己审核的承担部门  2021年5月20日  新增需求
                Map map = new HashMap<>();
                map.put("financialReApplyId",financialReApplyId);
                if (financialReApply.getState() == 2 || financialReApply.getState() == 3){
                    if (userId.intValue() != financialReApply.getApplyUserId().intValue()){
                        map.put("bearsOrg",1);
                        map.put("curUserId",userId);
                    }
                }
                List<FinancialCostBear> bears = financialCostBearMapper.list(map);
                for (FinancialCostBear bear : bears) {
                    if (bear.getOrganManagerUserId() != null) {
                        bear.setOrganManagerUserName(userInfoMapper.selectByPrimaryKey(bear.getOrganManagerUserId()).getUserName());
                    }
                    if (bear.getSuperiorManagerUserId() != null){
                        bear.setSuperiorManagerUserName(userInfoMapper.selectByPrimaryKey(bear.getSuperiorManagerUserId()).getUserName());
                    }
                }
                financialReApply.setFinancialCostBearList(bears);

                //多机构审核时，判断当前机构负责人是否已审核过
                if(financialReApply.getState() ==2){//待机构经理审核
                    map = new HashMap<>();
                    map.put("financialReApplyId",financialReApplyId);
                    map.put("organManagerState",0);
                    map.put("organManagerUserId",userId);
                    bears = financialCostBearMapper.list(map);
                    if(bears.size() > 0 ){
                        financialReApply.setChecked(false);
                    }
                }
                if(financialReApply.getState() == 3){//待分管总审核
                    map = new HashMap<>();
                    map.put("financialReApplyId",financialReApplyId);
                    map.put("superiorManagerState",0);
                    map.put("superiorManagerUserId",userId);
                    bears = financialCostBearMapper.list(map);
                    if(bears.size() > 0 ){
                        financialReApply.setChecked(false);
                    }
                }

                //费用明细
                map = new HashMap<>();
                map.put("financialReApplyId",financialReApplyId);
                List<FinancialCostDetails> detailses = financialCostDetailsMapper.list(map);
                Double costSumMoney = 0D;//费用明细总金额，用于页面展示
                for (FinancialCostDetails detailse : detailses) {
                    ids = new ArrayList<Long>();
                    ids.add(detailse.getId());
                    files = backendFinancialFileApiImpl.getFilesByIds(ids, FinancialFileTableEnumDto.FINACIAL_COST_DETAILS_ATTR);
                    detailse.setFinancialFileList(files);
                    costSumMoney = costSumMoney + detailse.getCostMoney();
                }
                financialReApply.setFinancialCostDetails(detailses);
                financialReApply.setCostSumMoney(costSumMoney);

                if(financialReApply.getState()!=null){
                    financialReApply.setStateStr(FinancialReApplyStateEnumDto.getStateNameByState(financialReApply.getState()));//状态
                }

                List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(userId);
                financialReApply.setOrganManagerRole(isRoleUser(userRoles,108L));//机构经理
                financialReApply.setSuperiorManagerRole(isRoleUser(userRoles,109L));//分管总
                financialReApply.setCeoRole(isRoleUser(userRoles,103L));//总经理
                financialReApply.setFinanceRole(isRoleUser(userRoles,23L));//财务专员
                financialReApply.setFinanceMangeRole(isRoleUser(userRoles,101L));//财务经理
                financialReApply.setLefanRole(isRoleUser(userRoles,132L));//董事长
                financialReApply.setCwzjRole(isRoleUser(userRoles,150L));//财务总监
                financialReApply.setCurUserId(userId);//当前登陆人
                StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(userId);
                if (staffPersonnelInfo != null && staffPersonnelInfo.getOrganId() != null){
                    StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(staffPersonnelInfo.getOrganId());
                    staffPersonnelInfo.setIsCanModify(staffOrgan.getIsCanModify() == null ? 0 : staffOrgan.getIsCanModify());
                    financialReApply.setIsCanModify(staffPersonnelInfo.getIsCanModify());
                }
                StaffPersonnelInfo data=staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(financialReApply.getApplyUserId());
                financialReApply.setSocialSecurityCompany(data.getSocialSecurityCompany());
                financialReApply.setOrgan(data.getOrgan());
                financialReApply.setBudgetCompanyName(data.getBudgetCompanyName());
                if(financialReApply.getFinancialCostBearList()!=null){
                    List<FinancialCostBear> costBearList = financialReApply.getFinancialCostBearList();
                    for (FinancialCostBear item : costBearList) {
                        StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(item.getDepartmentId());
                        item.setUnderdepartment(staffOrgan.getName());
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,financialReApply);
            }
            case "re-progres-list"://进度
            {
                Long financialReApplyId = apiRequest.getLong("financialReApplyId");
                Map map = new HashMap<>();
                map.put("financialReApplyId", financialReApplyId);
                List<FinancialReProgres> list = financialReProgresMapper.list(map);
                for (FinancialReProgres financialReProgres : list) {
                    //还款进度，需返回“附件”
                    if(financialReProgres.getProgressType()!=null && financialReProgres.getProgressType()== 3){
                        List<Long> ids = new ArrayList<Long>();
                        ids.add(financialReProgres.getId());
                        List<FinancialFile> files = backendFinancialFileApiImpl.getFilesByIds(ids, FinancialFileTableEnumDto.FINACIAL_RE_BACK_ATTR);
                        financialReProgres.setFiles(files);
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
            }
            case "staff-personnel-info"://上一条数据，获取历史数据初始化
            {
                StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(userId);
                if (staffPersonnelInfo != null && staffPersonnelInfo.getOrganId() != null){
                    StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(staffPersonnelInfo.getOrganId());
                    staffPersonnelInfo.setIsCanModify(staffOrgan.getIsCanModify() == null ? 0 : staffOrgan.getIsCanModify());
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,null,staffPersonnelInfo);
            }
            case "last-financial-re-apply-info"://上一条数据，获取历史数据初始化
            {
                FinancialReApply financialReApply = financialReApplyMapper.selectLastOne(userId);
                return new ApiResponse(ApiMsgEnum.SUCCESS,null,financialReApply);
            }
            case "get-staff-users" : //获取人事的系统员工
            {
                List<StaffPersonnelInfo> list = staffPersonnelInfoMapper.list(new HashMap());
                return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
            }
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId().intValue() == roleId.intValue()){
                return true;
            }
        }
        return false;
    }


    //进度保存
    private void setProgress(FinancialReApply financialReApply,UserInfo userInfo,String btnCode,String stepType,String progressName, String progressDesc, String details , Integer progressType){

        String stateName = FinancialReApplyStateEnumDto.getStateNameByState(financialReApply.getState());
        if("add-info".equals(btnCode)){
            progressName = "提交单据"+financialReApply.getReMoney()+"元";
            // 1：日常费用报销，2：对公支付，3：借款
            progressDesc = "日常费用报销";
            if(financialReApply.getReType() ==2){
                progressDesc = "对公支付";
            }else if(financialReApply.getReType() ==3){
                progressDesc = "借款单";
            }
        }
        else if("user-revoke".equals(btnCode))
        {
            progressName = "撤销单据";
            progressType= 5;
        }
        else if ("organManager-step".equals(btnCode))//机构经理审核
        {
            progressName = "机构经理审核";
            switch (stepType){
                case "yes":progressDesc = "通过";progressType= 1; break;
                case "no" :progressDesc = "驳回";progressType= 2; break;
            }
        }
        else if ("superiorManager-step".equals(btnCode))//分管总审核
        {
            progressName = "分管总审核";
            switch (stepType){
                case "yes":progressDesc = "通过";progressType= 1; break;
                case "no" :progressDesc = "驳回";progressType= 2; break;
            }
        }
        else if ("finance-step".equals(btnCode))//财务专员审核
        {
            progressName = "财务专员审核";
            switch (stepType){
                case "yes":progressDesc = "通过";progressType= 1; break;
                case "no" :progressDesc = "驳回";progressType= 2; break;
            }
        }
        else if ("financeManage-step".equals(btnCode))//财务经理审核
        {
            progressName = "财务经理审核";
            switch (stepType){
                case "yes":progressDesc = "通过";progressType= 1; break;
                case "no" :progressDesc = "驳回";progressType= 2; break;
            }
        }
        else if ("cwzj-step".equals(btnCode))//财务总监
        {
            progressName = "财务总监审核";
            switch (stepType){
                case "yes":progressDesc = "通过";progressType= 1; break;
                case "no" :progressDesc = "驳回";progressType= 2; break;
            }
        }
        else if ("ceo-step".equals(btnCode))//总经理审核
        {
            progressName = "总经理审核";
            switch (stepType){
                case "yes":progressDesc = "通过";progressType= 1; break;
                case "no" :progressDesc = "驳回";progressType= 2; break;
            }
        }
        else if ("lefan-step".equals(btnCode))
        {
            progressName = "董事长审核";
            switch (stepType){
                case "yes":progressDesc = "通过";progressType= 1; break;
                case "no" :progressDesc = "驳回";progressType= 2; break;
            }
        }
        else if ("zhuan-veto".equals(btnCode)){
            progressName = "转交驳回";
        }

        if (!"".equals(progressName)){
            if ("".equals(progressDesc) || progressDesc == null){
                progressDesc = "通过";
            }
            backendFinancialReProgresApiImpl.saveProgress(financialReApply.getId(),userInfo.getUserId(),userInfo.getUserName(),progressName,progressDesc,stateName, details , progressType);
        }
    }

    //微信端通知1、(stepType：yes、审核通过。no、驳回) 3、backReason:退回原因
    private void sendWechat(Long user,FinancialReApply financialReApply, String stepType, String backReason, UserInfo userInfo) {

        Map<String, Object> msgMap = new HashMap<String, Object>();
        //微信端通知
        String progressDesc = "日常费用报销";
        if(financialReApply.getReType() ==2){
            progressDesc = "对公支付";
        }else if(financialReApply.getReType() ==3){
            progressDesc = "借款单";
        }

        switch (stepType) {
            case "yes":
                msgMap = new HashMap<String, Object>();
                msgMap.put("title", "报销审核");
                msgMap.put("content", "你有报销待审核，请登录saas系统处理！");
                msgMap.put("keyWords", "事由：【" + progressDesc + "】"+financialReApply.getReReasons() + "\n" + "金额：" + financialReApply.getReMoney() + "元\n" + "提单人/借款人：" + financialReApply.getApplyUserName());
                String titleTypeStr = "";
                switch (financialReApply.getReType()){
                    case 1 : titleTypeStr = "日常费用报销"; break;
                    case 2 : titleTypeStr = "对公支付"; break;
                    case 3 : titleTypeStr = "借款单"; break;
                }
                String title = "【"+financialReApply.getApplyUserName()+"-"+titleTypeStr+"】";
                msgMap.put("path","/pages/expense/info?id=" + financialReApply.getId() + "&title=" + title + "&fromType=wait");
                backendWechatApi.send(user, msgMap);
                break;
            case "no":
                msgMap = new HashMap<String, Object>();
                msgMap.put("title", "报销审核");
                msgMap.put("content", "你有报销待退回，请登录saas系统处理！");
                msgMap.put("keyWords", "事由：【" + progressDesc + "】"+financialReApply.getReReasons() + "\n" + "金额：" + financialReApply.getReMoney() + "元\n" + "审核人：" + userInfo.getUserName()  + "\n退回原因：" + backReason);
                backendWechatApi.send(user, msgMap);
                break;
        }
    }


    public static void main(String[] args) {
        BigDecimal data1 = new BigDecimal(122D);//待还款金额 小-1 大1
        BigDecimal data2 = new BigDecimal(121D);//实际还款金额
        System.out.println(data1.compareTo(data2));
        if(data1.compareTo(data2) ==0){//代表相等
            System.out.println("相等");
        }else{
            System.out.println("不相等");
        }
    }
}
