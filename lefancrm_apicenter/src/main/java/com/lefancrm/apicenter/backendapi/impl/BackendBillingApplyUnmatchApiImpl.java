package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendBillingApplyUnmatchApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.BillingApplyDto;
import com.lefancrm.apicenter.dto.CommonEnumDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.apicenter.util.SerialNumberUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by wangwei on 2018/10/9.
 */
@ApiService(descript = "未匹配收款")
@Service
public class BackendBillingApplyUnmatchApiImpl extends BaseServiceImpl implements BackendBillingApplyUnmatchApi {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private BillingApplyUnmatchMapper billingApplyUnmatchMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BillingApplyMapper billingApplyMapper;
    @Autowired
    private BillingApplyAccountsMapper billingApplyAccountsMapper;
    @Autowired
    private CommonEnumMapper commonEnumMapper;
    @Autowired
    private BillingApplyCorporationMapper billingApplyCorporationMapper;

    @Autowired
    private BillingReceiveInfoMapper billingReceiveInfoMapper;

    @Autowired
    private OrgInfoMapper orgInfoMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private BillingRefundInfoMapper billingRefundInfoMapper;

    /**
     * 未匹配收款list
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "未匹配收款list" ,value = "backend-billing-apply-unmatch-list")
    @Override
    public ApiResponse list(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        Long currentUserId = apiReq.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean boss = isRoleUser(userRoles,134L);//盐城财务
        if(boss){
            apiReq.put("receivingCompanyIds",6);//
        }
        Boolean boss1 = isRoleUser(userRoles,135L);//常熟财务
        if(boss1){
            apiReq.put("receivingCompanyIds",7);//
        }

        Boolean boss2 = isRoleUser(userRoles,145L);//无锡财务
        if(boss2){
            apiReq.put("receivingCompanyIds",54);//
        }

        Boolean boss3 = isRoleUser(userRoles,146L);//南通财务
        if(boss3){
            apiReq.put("receivingCompanyIds",53);//
        }


        Boolean roleUser = isRoleUser(userRoles, 144L);
        if (roleUser){
            apiReq.put("enums144",144);
        }

        List<BillingApplyUnmatch> billingApplyUnmatch = billingApplyUnmatchMapper.selectList(apiReq);
        for (BillingApplyUnmatch applyUnmatch : billingApplyUnmatch) {
            Double aDouble = billingRefundInfoMapper.selectAllRefundMoneyByMatchId(applyUnmatch.getId());
            applyUnmatch.setRefundMoney(Optional.ofNullable(aDouble).orElse(0d));
        }
        //查询认领明细
        String matchIds = billingApplyUnmatch.stream().map(r -> r.getId().toString()).collect(Collectors.joining(","));
        List<BillingReceiveInfo> receiveInfos = billingReceiveInfoMapper.selectByMatchIds(matchIds);
        for (BillingApplyUnmatch applyUnmatch : billingApplyUnmatch) {
            List<BillingReceiveInfo> collect = receiveInfos.stream().filter(p -> p.getBillingMatchId().intValue() == applyUnmatch.getId().intValue()).collect(Collectors.toList());
            applyUnmatch.setReceiveInfos(collect);
        }
        int count = billingApplyUnmatchMapper.selectListSize(apiReq);

        return new ApiResponse(ApiMsgEnum.SUCCESS, count, billingApplyUnmatch);
    }

    /**
     * 未匹配收款单条数据
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "未匹配收款单条数据" ,value = "backend-billing-apply-unmatch-by-id")
    @Override
    public ApiResponse info(ApiRequest apiReq){
        BillingApplyUnmatch billingApplyUnmatch = billingApplyUnmatchMapper.selectByPrimaryKey(apiReq.getLong("id"));
        Double aDouble = billingRefundInfoMapper.selectAllRefundMoneyByMatchId(billingApplyUnmatch.getId());
        billingApplyUnmatch.setRefundMoney(Optional.ofNullable(aDouble).orElse(0d));
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, billingApplyUnmatch);
    }

    /**
     * 更新未匹配收款
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "更新未匹配收款" ,value = "backend-billing-apply-unmatch-update")
    @Override
    public ApiResponse update(ApiRequest apiReq){
        BillingApplyUnmatch billingApplyUnmatch = billingApplyUnmatchMapper.selectByPrimaryKey(apiReq.getLong("id"));
        Long userId = apiReq.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);

        if(billingApplyUnmatch == null){
            //保存
            billingApplyUnmatch = ConvertToBeanUtil.toBeanFromApiRequest(apiReq, BillingApplyUnmatch.class);
            billingApplyUnmatch.setCreateBy(userInfo.getUserName());// 发起人
            billingApplyUnmatch.setCreateById(userInfo.getUserId());// 发起人id
            billingApplyUnmatch.setCreateTime(new Date());//创建时间
            billingApplyUnmatch.setState(1);//状态（1、未认领；2、已认领）
            CommonEnum commonEnum=commonEnumMapper.selectByPrimaryKey(Long.parseLong(billingApplyUnmatch.getBillingItemsId().toString()));
            billingApplyUnmatch.setBillingItemsName(commonEnum.getEnumText());
            BillingApplyCorporation billingApplyCorporation=billingApplyCorporationMapper.selectByPrimaryKey(Long.parseLong(billingApplyUnmatch.getReceivingCompanyId().toString()));
            billingApplyUnmatch.setReceivingCompanyName(billingApplyCorporation.getName());
            //处理“交易时间”
            String payTime = apiReq.getString("payTime");
            try {
                billingApplyUnmatch.setPayTime(sdf.parse(payTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ;
            billingApplyUnmatchMapper.insert(billingApplyUnmatch);
        }else{
            //修改
            billingApplyUnmatch = ConvertToBeanUtil.toBeanFromApiRequest(apiReq,billingApplyUnmatch);
            CommonEnum commonEnum=commonEnumMapper.selectByPrimaryKey(Long.parseLong(billingApplyUnmatch.getBillingItemsId().toString()));
            billingApplyUnmatch.setBillingItemsName(commonEnum.getEnumText());
            BillingApplyCorporation billingApplyCorporation=billingApplyCorporationMapper.selectByPrimaryKey(Long.parseLong(billingApplyUnmatch.getReceivingCompanyId().toString()));
            billingApplyUnmatch.setReceivingCompanyName(billingApplyCorporation.getName());
            //处理“交易时间”
            String payTime = apiReq.getString("payTime");
            try {
                billingApplyUnmatch.setPayTime(sdf.parse(payTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            billingApplyUnmatchMapper.updateByPrimaryKey(billingApplyUnmatch);
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 未匹配收款删除
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "删除" ,value = "backend-billing-apply-unmatch-delete")
    @Override
    public ApiResponse delete(ApiRequest apiReq){
        int result = billingApplyUnmatchMapper.deleteByPrimaryKey(apiReq.getLong("id"));
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    /**
     * 生成编号
     */
    @ApiMethod(descript = "生成编号" ,value = "backend-to-build-unmatch-no")
    @Override
    public ApiResponse buildNo(ApiRequest apiReq) {

        String unmatchNo = SerialNumberUtil.toBuildCaseNo("PPSK");
        return new ApiResponse(ApiMsgEnum.SUCCESS,null,unmatchNo);
    }

    /**
     * 已开票认领--开票清单
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "已开票认领--开票清单" ,value = "backend-billing-apply-list-for-unmatch")
    @Override
    public ApiResponse billingList(ApiRequest apiReq){
        Long menuType=apiReq.getLong("menuType");
        if(menuType != 101){
            this.setBackendPageSize(apiReq);
        }
        apiReq.put("billingState",3);//已开票
        apiReq.put("mType",1);//公估开票
        apiReq.put("confirmAccountState",1);//公估未到账
        String billingCode = apiReq.getString("billingCode");//发票单号
        //发票单号查询：因开票数据与发票单号，是一对多关系，所以查询时，需单独查询
        if(billingCode != null) {
            billingCode=billingCode.trim();
            apiReq.put("billingCode",billingCode);
            apiReq.put("billingCodeType", 5);
        }
        String billingCompany = apiReq.getString("billingCompany");//开票对象
        //开票对象查询：因开票数据与开票对象，两张表查询，所以查询时，需单独查询
        if(billingCompany != null) {
            apiReq.put("billingCompanyType", 5);
        }
        String caseNo=apiReq.getString("caseNo");
        if(caseNo != null){
            caseNo=caseNo.trim();
            apiReq.put("caseNo",caseNo);
        }
        String caseTitle=apiReq.getString("caseTitle");
        if(caseTitle != null){
            caseTitle=caseTitle.trim();
            apiReq.put("caseTitle",caseTitle);
        }
        Long currentUserId = getCurrentUserId(apiReq);
        apiReq.put("currentUserId",currentUserId);
        String menuCode = apiReq.getString("muenCode");
        if ("claimByInvoice".equals(menuCode) && apiReq.get("apply") == null && apiReq.get("clickBtn") == null){
          return new ApiResponse(ApiMsgEnum.SUCCESS, 0, Collections.emptyList());
        }

        List<BillingApplyDto> billingApply = billingApplyMapper.selectByMap(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS, billingApply.size(), billingApply);
//        List<BillingApply> billingApply = billingApplyMapper.selectList(apiReq);
//        int count = billingApplyMapper.selectListSize(apiReq);
    }

    /**
     * 确认认领--已开票认领
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "确认认领--已开票认领" ,value = "backend-billing-apply-unmatch-claim")
    @Override
    public ApiResponse claim(ApiRequest apiReq){

        Long userId = apiReq.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);

        Long billId = apiReq.getLong("billId");
        Double money = apiReq.getDouble("money");
        //更新开票记录
        BillingApply billingApply = billingApplyMapper.selectByPrimaryKey(billId);
        billingApply.setConfirmAccountState(2);
        billingApply.setConfirmAccountTime(new Date());
        billingApplyMapper.updateByPrimaryKeySelective(billingApply);

        //生成公估到账记录
        BillingApplyAccounts accounts = new BillingApplyAccounts();
        accounts.setBillId(billId);
        accounts.setMoney(money);
        accounts.setState(1);
        accounts.setCreateById(userInfo.getUserId());
        accounts.setCreateBy(userInfo.getUserName());
        accounts.setCreateTime(new Date());
        billingApplyAccountsMapper.insertSelective(accounts);

        //更新未匹配收款
        Long unmatchId = apiReq.getLong("unmatchId");
        BillingApplyUnmatch billingApplyUnmatch = billingApplyUnmatchMapper.selectByPrimaryKey(unmatchId);
        billingApplyUnmatch.setState(2);
        billingApplyUnmatch.setBillId(billId);
        billingApplyUnmatch.setBillNo(billingApply.getCaseNo());
        billingApplyUnmatch.setClaimBy(userInfo.getUserName());
        billingApplyUnmatch.setClaimById(userInfo.getUserId());
        billingApplyUnmatch.setClaimTime(new Date());
        int result = billingApplyUnmatchMapper.updateByPrimaryKeySelective(billingApplyUnmatch);

        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }


    /**
     * 年度汇总报表
     * @param
     * @return
     */
    @ApiMethod(descript = "年度汇总报表" ,value = "backend-billing-annual-table-list")
    @Override
    public ApiResponse annualTableList(ApiRequest apiRequest) {
        String export = apiRequest.getString("export");
        if(export == null){
            //不是导出的时候，仅为页面数据
            OrgInfo orgInfos = tableList(apiRequest);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,orgInfos);
        }
        Long currentUserId = getCurrentUserId(apiRequest);
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);

        Boolean orgManage = isRoleUser(userRoles,117L);//机构负责人（年度汇总表）
        Boolean finance = isRoleUser(userRoles,23L);//财务
        String orgId = apiRequest.getString("orgId");
        if(!finance){
            orgId = userInfo.getOrgId().toString();
        }

        List<OrgInfo> orgInfos = orgInfoMapper.selectOrgInfoListByIds(orgId);
        setMoney(orgInfos);
        if ("single".equals(apiRequest.getString("type"))){
            String dateTimes = apiRequest.getString("dateTimes");
            List<Map<String, String>> maps = orgInfoMapper.selectCurrentYearAllMonth(StringUtils.isNotBlank(dateTimes) ? Integer.parseInt(dateTimes.split("-")[1]) : Integer.parseInt(LocalDate.now().toString().split("-")[1]));
            for (OrgInfo orgInfo : orgInfos) {
                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("orgId", orgInfo.getId());
                paramMap.put("dateTimes", maps);
                paramMap.put("type", "single");
                paramMap.put("enumCode", apiRequest.getString("enumCode"));
                List<CommonEnumDto> list = billingApplyMapper.selectEnumMoneyList(paramMap);
                orgInfo.setEnumMoneyList(list);

                for (Map<String, String> map : maps) {
                    String months = map.get("months");
                    List<CommonEnumDto> mapList = list.stream().filter(e -> e.getDateTime().equals(months)).collect(Collectors.toList());
                    double imgSum = mapList.stream().mapToDouble(CommonEnumDto::getImgMoney).sum();
                    double accSum = mapList.stream().mapToDouble(CommonEnumDto::getAccMoney).sum();
                    mapList.stream().filter(e -> "开票总金额".equals(e.getEnumName())).findFirst().ifPresent(e -> e.setImgSum(imgSum));
                    mapList.stream().filter(e -> "到账总金额".equals(e.getEnumName())).findFirst().ifPresent(e -> e.setAccSum(accSum));
                    mapList.stream().filter(e -> "应收账款余额".equals(e.getEnumName())).findFirst().ifPresent(e -> e.setReceMoney(Optional.ofNullable(orgInfo.getMoney()).orElse(0d) + imgSum - accSum));
                }
            }
        }
        else if ("all".equals(apiRequest.getString("type"))){
            for (OrgInfo orgInfo : orgInfos) {
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("orgId", orgInfo.getId());
                paramMap.put("type", "all");
                paramMap.put("enumCode", apiRequest.getString("enumCode"));
                List<CommonEnumDto> list = billingApplyMapper.selectEnumMoneyList(paramMap);
                orgInfo.setEnumMoneyList(list);
                double accSum = list.stream().mapToDouble(CommonEnumDto::getAccMoney).sum();
                double imgSum = list.stream().mapToDouble(CommonEnumDto::getImgMoney).sum();
                list.stream().filter(e -> "到账总金额".equals(e.getEnumName())).findFirst().ifPresent(e->e.setAccSum(accSum));
                list.stream().filter(e -> "开票总金额".equals(e.getEnumName())).findFirst().ifPresent(e -> e.setImgSum(imgSum));

            }
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,orgInfos.size(),orgInfos);
    }


    public OrgInfo tableList(ApiRequest apiRequest) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String orgId = apiRequest.getString("orgId");
        OrgInfo orgInfo = new OrgInfo();
        if(orgId !=null){
            orgInfo = orgInfoMapper.test(orgId);
        }

        String dateTimes = apiRequest.getString("dateTimes");
        Date date = null;
        try {
            date = sdf.parse(dateTimes);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //上个月
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        String monthDateTimes = sdf.format(cal.getTime());

        //去年本月
        cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, -1);
        String yearDateTimes = sdf.format(cal.getTime());

        cal = Calendar.getInstance();
        String thisYearTimes = String.valueOf(cal.get(Calendar.YEAR));//今年
        String lastYearTimes = String.valueOf(cal.get(Calendar.YEAR) -1);//去年

        //历史应收金额：去年的时间
        String lastYearBeginTime = lastYearTimes+"-01";
        String lastYearEndTime = yearDateTimes;
        //历史应收金额：今年的时间
        String thisYearBeginTime = thisYearTimes+"-01";
        String thisYearEndTime = dateTimes;


        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("orgId", orgId);
        paramMap.put("dateTimes", dateTimes);//当前时间
        paramMap.put("monthDateTimes", monthDateTimes);//上个月
        paramMap.put("yearDateTimes", yearDateTimes);//去年本月
        paramMap.put("thisYearTimes", thisYearTimes);//今年
        paramMap.put("lastYearTimes", lastYearTimes);//去年
        paramMap.put("type", "single");
        paramMap.put("enumCode", apiRequest.getString("enumCode"));
        paramMap.put("businessType", apiRequest.getString("businessType"));//开票公司

        DateFormat format = new SimpleDateFormat("yyyy-MM");
        try {
            paramMap.put("lastYearBeginTime", format.parse(lastYearBeginTime));
            paramMap.put("lastYearEndTime", format.parse(lastYearEndTime));
            paramMap.put("thisYearBeginTime", format.parse(thisYearBeginTime));
            paramMap.put("thisYearEndTime", format.parse(thisYearEndTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //上年的应收账款余额
        List<OrgInfo> orgInfos = new ArrayList<>();
        setMoney(orgInfos);
        orgInfos.add(orgInfo);
        Double money = orgInfos.get(0).getMoney() ==null?0D:orgInfos.get(0).getMoney();
        paramMap.put("lastYearMoney", money);

        //今年的应收账款余额
        paramMap.put("thisYearMoney", 0D);

        List<CommonEnumDto> list = billingApplyMapper.selectTest(paramMap);
        orgInfo.setEnumMoneyList(list);

        return orgInfo;
    }

    public void setMoney(List<OrgInfo> orgInfos){
        for (OrgInfo orgInfo : orgInfos) {
            if ("上海分公司".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(1759711.88);
            }
            if ("广西分公司".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(3200.00);
            }
            if ("镇江分公司".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(399198.50);
            }
            if ("无锡分公司".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(96778.96);
            }
            if ("苏州分公司".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(21093.00);
            }
            if ("南通分公司".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(301737.83);
            }
            if ("泰州分公司".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(208240.00);
            }
            if ("扬州分公司".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(34100.00);
            }
            if ("盐城分公司".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(100.00);
            }
            if ("宿迁分公司".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(90400.00);
            }
            if ("徐州分公司".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(2058327.67);
            }
            if ("南京营业部".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(1149804.03);
            }
            if ("山东营业部".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(15439.35);
            }
            if ("市场营销二部".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(142176.00);
            }
            if ("互助调查事业部".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(4384800.00);
            }
            if ("安徽".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(134286.00);
            }
            if ("连云港".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(420940.16);
            }
            if ("浙江".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(515854.37);
            }
            if ("临沂".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(-37480.49);
            }
            if ("总部".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 106){
                orgInfo.setMoney(-37480.49);
            }
            //个人
            if ("上海分部".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 1){
                orgInfo.setMoney(-780251.74);
            }
            if ("南通分部".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 1){
                orgInfo.setMoney(-198392.07);
            }
            if ("广西".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 1){
                orgInfo.setMoney(-3500.00);
            }
            if ("扬州分部".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 1){
                orgInfo.setMoney(-10000.00);
            }
            if ("徐州分部".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 1){
                orgInfo.setMoney(-635974.47);
            }
            if ("连云港分公司".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 1){
                orgInfo.setMoney(-290100.00);
            }
            if ("南京".equals(orgInfo.getOrgName()) && orgInfo.getOrgParentid() == 1){
                orgInfo.setMoney(-111500.00);
            }
            orgInfo.setUnmatchMoney(-2147510.91);
        }
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId().equals(roleId)){
                return true;
            }
        }
        return false;
    }
}
