package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendBillingApplyApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.*;
import com.lefancrm.apicenter.fina.api.BackendFinaSettlementInfoApi;
import com.lefancrm.apicenter.fina.api.impl.BackendFinaSettlementInfoApiImpl;
import com.lefancrm.apicenter.fina.dao.FinaSettlementInfoMapper;
import com.lefancrm.apicenter.fina.enums.SettlementEnum;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
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
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by wangwei on 2018/7/24.
 */
@ApiService(descript = "发票列表API")
@Service
public class BackendBillingAppyApiImpl extends BaseServiceImpl implements BackendBillingApplyApi {

    @Autowired
    private BillingApplyMapper billingApplyMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private OrgInfoMapper orgInfoMapper;
    @Autowired
    private BillingApplyMaterialMapper billingApplyMaterialMapper;
    @Autowired
    private BillingApplyImgsMapper billingApplyImgsMapper;
    @Autowired
    private ArrivalInfoMapper arrivalInfoMapper;
    @Autowired
    private SuningWithholdApplyMapper suningWithholdApplyMapper;
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    @Autowired
    private WithdrawalsInfoMapper withdrawalsInfoMapper;
    @Autowired
    private CasePayInfoMapper casePayInfoMapper;
    @Autowired
    private CaseApplicationInfoMapper caseApplicationInfoMapper;
    @Autowired
    private UserPatchOrgMapper userPatchOrgMapper;
    @Autowired
    private CaseCenterInfoAllotMapper caseCenterInfoAllotMapper;
    @Autowired
    private CaseCenterInfoFinedMapper caseCenterInfoFinedMapper;
    @Autowired
    private CaseEstimateInfoMapper caseEstimateInfoMapper;
    @Autowired
    private CommonEnumMapper commonEnumMapper;
    @Autowired
    private BillingApplyAccountsMapper billingApplyAccountsMapper;
    @Autowired
    private BillingApplyUnmatchMapper billingApplyUnmatchMapper;
    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;
    @Autowired
    private SurveyBillingApplyMapper surveyBillingApplyMapper;
    @Autowired
    private SurveyConsignorDepartmentMapper surveyConsignorDepartmentMapper;
    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;
    @Autowired
    private BillingApplyCorporationEnumMapper billingApplyCorporationEnumMapper;
    @Autowired
    private BillingApplyEnumItemMapper billingApplyEnumItemMapper;
    @Autowired
    private LawCaseInfoMapper lawCaseInfoMapper;
    @Autowired
    private BusApplyOrgRoleMapper busApplyOrgRoleMapper;
    @Autowired
    private BusApplyEnumRoleMapper busApplyEnumRoleMapper;
    @Autowired
    private BusApplyItemRoleMapper busApplyItemRoleMapper;
    @Autowired
    private BillingApplyProductOrgMapper billingApplyProductOrgMapper;
    @Autowired
    private SurveyConsignorBillSubjectMapper surveyConsignorBillSubjectMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;

    @Autowired
    private FinaSettlementInfoMapper finaSettlementInfoMapper;
    @Autowired
    private SurveyPayInfoDetailNewMapper surveyPayInfoDetailNewMapper;
    @Autowired
    private BackendFinaSettlementInfoApiImpl backendFinaSettlementInfoApi;
    @Autowired
    private StaffPersonnelInfoMapper staffPersonnelInfoMapper;
    @Autowired
    private StaffOrganProductMapper staffOrganProductMapper;
    @Autowired
    private StaffOrganMapper staffOrganMapper;
    @Autowired
    private BillingApplyRecipientMapper billingApplyRecipientMapper;
    @Autowired
    private BillingApplyCompanyMapper billingApplyCompanyMapper;
    @Autowired
    private BillingApplyRefundMapper billingApplyRefundMapper;
    @Autowired
    private SurveyFwCaseMapper surveyFwCaseMapper;
    /**
     * 开票列表
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "发票列表" ,value = "backend-billing-apply-list")
    @Override
    public ApiResponse billingApplyList(ApiRequest apiReq){
        //导出：发票清单、开票、开票管理 三个模块的分别条件
        apiReq = putApiReq(apiReq);
        //判断当前是否是机构财务角色登录，财务机构角色只能取当前机构的财务数据

//        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(getCurrentUserId(apiReq));
//        Boolean boss = isRoleUser(userRoles,134L);//盐城财务
//        if(boss){
////            apiReq.put("companyIds",6);//
//            apiReq.put("ycRole",1);
//        }
//        Boolean boss1 = isRoleUser(userRoles,135L);//常熟财务
//        if(boss1){
////            apiReq.put("companyIds",7);//
//            apiReq.put("csRole",1);
//        }

        this.setBackendPageSize(apiReq);
        int count = billingApplyMapper.selectListSize(apiReq);
        List<BillingApply> list = billingApplyMapper.selectList(apiReq);




        for (BillingApply billingApply : list) {
//            if (billingApply.getBillingItem() != null && billingApply.getBillingItem() == 12){
//                billingApply.setIsRed(true);
//            }else {
//                billingApply.setIsRed(false);
//            }
            List<BillingApplyImgs> imgsList = billingApplyImgsMapper.selectListByBillId(billingApply.getId());
            long count1 = imgsList.stream().filter(e -> e.getState() != null && e.getState() == 4).count();
            billingApply.setIsRed(count1>0);
//            for (int i = 0; i < imgsList.size(); i++) {
//                if(imgsList.get(i).getState()!=null &&imgsList.get(i).getState() ==4){
//                    billingApply.setIsRed(true);
//                }
//            }
            BillingApply b = billingApplyMapper.selectMoneyById(billingApply.getId());
            billingApply.setOkBillMoney(b.getOkBillMoney());
            billingApply.setNoBillMoney(b.getNoBillMoney());
            // 2021年6月9日  已到账金额要减去已退费金额
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("billId",billingApply.getId());
            List<BillingApplyRefund> refunds = billingApplyRefundMapper.list(paramMap);
            Double refundMoney = refunds.stream().mapToDouble(BillingApplyRefund :: getRefundMoney).sum();
            billingApply.setOkAccountMoney(b.getOkAccountMoney() - refundMoney);
            billingApply.setNoAccountMoney(b.getNoAccountMoney());
            if (billingApply.getOkAccountMoney() == 0)  billingApply.setConfirmAccountState(1);
            if (billingApply.getOkAccountMoney() > 0 && billingApply.getOkAccountMoney() < billingApply.getBillingMoney())  billingApply.setConfirmAccountState(3);
            if (billingApply.getOkAccountMoney().equals(billingApply.getBillingMoney()))  billingApply.setConfirmAccountState(2);
        }
        return  new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    private ApiRequest putApiReq(ApiRequest apiReq) {
        Long menuType = apiReq.getLong("menuType");
        Long currentUserId = apiReq.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        if (menuType != null && menuType == 5){
            Boolean boss = isRoleUser(userRoles,32L);//总经理
            Boolean orgManager = isRoleUser(userRoles,31L);//机构经理
            if (orgManager){
//                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
//                if (surveyInvestigator !=  null){
//                    apiReq.put("surveyOrgId", surveyInvestigator.getOrgId());
//                }
                apiReq.put("menuType",51);//billingState in (3,5,6,7,8);
            }
            if (boss){
                apiReq.put("menuType",52);// billingState in (5,6);
            }
            if (boss && orgManager){
                if (apiReq.containsKey("myOrgId")){
                    apiReq.remove("myOrgId");
                }
                apiReq.put("menuType",51);//如果两个角色都有, 则同机构经理 billingState in (3,5,6,7,8);
            }
            if (!orgManager && !boss){
                apiReq.put("billingState",-1);//如果两个角色都没有,查询不到数据
            }
        }else if (menuType != null && menuType == 3){
            Boolean roleUser = isRoleUser(userRoles, 144L);
            if (roleUser){
                apiReq.put("enums144",144);
            }


            Long business = apiReq.getLong("business");
            //查询“个人业务”数据
            if(business == 1){
                apiReq.put("mType",4);
            }
            //工作台：金融未开票数据
            if(business == 11){
                apiReq.put("mType",11);
            }
            //查询所有公估数据
            if(business == 2){
                apiReq.put("mType",3);
                //2019年11月8日 16点36分  如果是机构经理只查询 自己机构的案件
//                Boolean orgManager = isRoleUser(userRoles,31L);//机构经理
//                if (orgManager){
//                    SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
//                    if (surveyInvestigator !=  null){
//                        apiReq.put("surveyOrgId", surveyInvestigator.getOrgId());
//                    }
//                }
            }
            //工作台：公估未开票数据
            if(business == 12){
                apiReq.put("mType",12);
            }
            //工作台：公估未确认到账
            if(business == 13){
                apiReq.put("mType",13);
            }

            Boolean boss = isRoleUser(userRoles,134L);//盐城财务
            if(boss){
//                 apiReq.put("businessType",6);//
                apiReq.put("ycRole",1);
             }
            Boolean boss1 = isRoleUser(userRoles,135L);//常熟财务
            if(boss1){
//                apiReq.put("businessType",7);//
                apiReq.put("csRole",1);
            }

            Boolean boss2 = isRoleUser(userRoles,145L);//无锡财务
            if(boss2){
//                apiReq.put("businessType",7);//
                apiReq.put("wxRole",1);
            }

            Boolean boss3 = isRoleUser(userRoles,146L);//南通财务
            if(boss3){
//                apiReq.put("businessType",7);//
                apiReq.put("ntRole",1);
            }

            Boolean cwRole = isRoleUser(userRoles,23L);//财务
            Boolean cw2Role = isRoleUser(userRoles,140L);//财务开票专员
            if (cwRole || cw2Role){
                apiReq.put("cwRole",1);
            }

        }else if(menuType != null && menuType == 2){
            //即是list查询条件，也是导出查询条件
            apiReq.put("menuType",2);
            apiReq.put("mType",1);
            //如果是管理员，查询该所在机构的所有数据，如不是管理员，仅查询自己的数据
            Boolean manager = isRoleUser(userRoles,1L);//管理员
            if(manager){
                StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(currentUserId);
                if (staffPersonnelInfo != null){
                    apiReq.put("myOrgId",staffPersonnelInfo.getOrganId());

                }
//                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
                apiReq.put("currentUserId",currentUserId);
                apiReq.put("mType",5);
            }else{
                apiReq.put("mType",2);
                apiReq.put("currentUserId",currentUserId);
            }

            //2019年11月8日 16点36分  如果是机构经理只查询 自己机构的案件
            Boolean orgManager = isRoleUser(userRoles,31L);//机构经理
            if (!manager && orgManager) {
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
                if (surveyInvestigator !=  null){
                    apiReq.put("surveyOrgId", surveyInvestigator.getOrgId());
                }
            }

        }else if (menuType != null && menuType == 8){//调查业务付款
            Boolean orgManager = isRoleUser(userRoles,31L);//机构经理
            if (orgManager) {
                //当前登录人所在的调查方机构ID
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
                if (surveyInvestigator !=  null){
                    apiReq.put("surveyOrgId", surveyInvestigator.getOrgId());
                    apiReq.put("mType",3);
                }else{
                    apiReq.put("billingState",-1);//查不到数据
                }
            }else{
                apiReq.put("billingState",-1);//查不到数据
            }
        }

        //发票单号查询：因开票数据与发票单号，是一对多关系，所以查询时，需单独查询
        String billingCode = apiReq.getString("billingCode");
        if(billingCode != null){
            apiReq.put("billingCodeType",5);
        }
        String billingCompany = apiReq.getString("billingCompany");//开票对象
        //开票对象查询：因开票数据与开票对象，两张表查询，所以查询时，需单独查询
        if(billingCompany != null) {
            apiReq.put("billingCompanyType", 5);
        }
        //发票状态查询：因开票数据与发票状态，是一对多关系，所以查询时，需单独查询
        String imgsState = apiReq.getString("imgsState");
        if(imgsState != null && imgsState !=""){
            apiReq.put("imgsState",imgsState);
        }
        return apiReq;
    }

    /**
     * 根据“id”查询发票信息
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据“id”查询发票信息" ,value = "backend-billing-apply-by-id")
    @Override
    public ApiResponse searchBillingApplyById(ApiRequest apiReq){
        Long currentUserId = apiReq.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean orgManager = isRoleUser(userRoles, 31L);//机构经理
        Boolean boss = isRoleUser(userRoles, 32L);//总经理

        this.setBackendPageSize(apiReq);
        BillingApply billingApply = billingApplyMapper.selectByPrimaryKey(apiReq.getLong("id"));
        BillingApply billMoney = billingApplyMapper.selectMoneyById(billingApply.getId());
        // 2021年6月9日  已到账金额要减去已退费金额
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("billId",billingApply.getId());
        List<BillingApplyRefund> refunds = billingApplyRefundMapper.list(paramMap);
        Double refundMoney = refunds.stream().mapToDouble(BillingApplyRefund :: getRefundMoney).sum();
        billingApply.setOkAccountMoney(billMoney.getOkAccountMoney() - refundMoney);
        billingApply.setNoAccountMoney(billMoney.getNoAccountMoney());
        billingApply.setOkBillMoney(billMoney.getOkBillMoney());
        billingApply.setNoBillMoney(billMoney.getNoBillMoney());
        BillingApplyDto dto = ConvertToBeanUtil.buildInfo(BillingApplyDto.class, billingApply);
        dto.setOrgManager(orgManager);
        dto.setBoss(boss);
        dto.setRefunds(refunds);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,1,dto);
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId().equals(roleId)){
                return true;
            }
        }
        return false;
    }

    /**
     * 根据billId，查询开票材料的list
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据billId，查询开票材料的list" ,value = "backend-billing-apply-material-by-id")
    @Override
    public ApiResponse searchBillingApplyMaterialById(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        Long billId = apiReq.getLong("id");
//        List<BillingApplyMaterial> billingApplyMaterial = billingApplyMaterialMapper.selectListByBillId(billId);
        List<BillingApplyMaterialDto> billingApplyMaterial = billingApplyMaterialMapper.selectByBillId(billId);
        for (int i = 0; i < billingApplyMaterial.size(); i++) {
            int firstName = billingApplyMaterial.get(i).getMaterialImgs().lastIndexOf(".") + 1;
            String name = billingApplyMaterial.get(i).getMaterialImgs().substring(firstName,billingApplyMaterial.get(i).getMaterialImgs().length());
            billingApplyMaterial.get(i).setFileType(name);
        }
        return  new ApiResponse(ApiMsgEnum.SUCCESS,1,billingApplyMaterial);
    }

    @ApiMethod(descript = "开票申请附表记录",value = "backend-billing-apply-imgs-by-id")
    @Override
    public ApiResponse findBillApplyById(ApiRequest request){
        Long id = request.getLong("id");
        List<BillingApplyImgs> billingApplyImgs = billingApplyImgsMapper.selectListByBillId(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS,null,billingApplyImgs);
    }

    @ApiMethod(descript = "开票申请附表记录",value = "backend-billing-apply-imgs-by-id-new")
    @Override
    public ApiResponse findBillApplyByIdNew(ApiRequest request){
        Long id = request.getLong("id");
        DecimalFormat df = new DecimalFormat("#.##");
        List<BillingApplyImgs> billingApplyImgs = billingApplyImgsMapper.selectListByBillId(id);
        for (BillingApplyImgs billingApplyImg : billingApplyImgs) {
            List<BillingApplyAccounts> billingApplyAccounts = billingApplyAccountsMapper.selectListByImgsId(billingApplyImg.getId());
            billingApplyImg.setBillingApplyAccountsList(billingApplyAccounts);
            double sum = billingApplyAccounts.stream().mapToDouble(BillingApplyAccounts::getMoney).sum();
            billingApplyImg.setOkAccountMoney(Double.parseDouble(df.format(sum)));
            if (billingApplyImg.getBillingMoney() == null){
                billingApplyImg.setBillingMoney(0d);
            }
            billingApplyImg.setNoAccountMoney(Double.parseDouble(df.format(billingApplyImg.getBillingMoney()-sum)));
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,null,billingApplyImgs);
    }

    /**
     * 根据选择的类目 生成案件编号
     */
    @ApiMethod(descript = "生成案件编号" ,value = "backend-to-build-case-no")
    @Override
    public ApiResponse toBuildCaseNo(ApiRequest apiReq) {
        Integer billingEnum =apiReq.getInt("billingEnum");

        String caseNo="";
        if(billingEnum != null && !"".equals(billingEnum)) {
            switch (billingEnum) {
                case 1:
                    //全程通类目
                    caseNo = SerialNumberUtil.toBuildCaseNo("QC");
                    break;
                case 2:
                    //风险调查类目
                    caseNo = SerialNumberUtil.toBuildCaseNo("FX");
                    break;
                case 3:
                    //交警队工作室类目
                    caseNo = SerialNumberUtil.toBuildCaseNo("JJ");
                    break;
                case 4:
                    //诉调对接类目
                    caseNo = SerialNumberUtil.toBuildCaseNo("ST");
                    break;
                case 5:
                    //培训类目
                    caseNo = SerialNumberUtil.toBuildCaseNo("PX");
                    break;
                case 6:
                    //其他类目
                    caseNo = SerialNumberUtil.toBuildCaseNo("QT");
                    break;
                case 8:
                    //法院工作室类目
                    caseNo = SerialNumberUtil.toBuildCaseNo("FY");
                    break;
                case 9:
                    //财产险类目
                    caseNo = SerialNumberUtil.toBuildCaseNo("CC");
                    break;
                case 10:
                    //公估评估类目
                    caseNo = SerialNumberUtil.toBuildCaseNo("GG");
                    break;
                case 11:
                    //司法评估类目
                    caseNo = SerialNumberUtil.toBuildCaseNo("SFPG");
                    break;
                case 7:
                    //个人业务类目(其类目是代理案件、贷款案件正常流转中生成的开票记录，故不在此列中)
                    break;
                case 13:
                    //医疗纠纷理赔工作室
                    caseNo = SerialNumberUtil.toBuildCaseNo("YL");
                    break;
                case 14:
                    //非医保鉴定
                    caseNo = SerialNumberUtil.toBuildCaseNo("FYB");
                    break;
                case 15:
                    //合伙业务
                    caseNo = SerialNumberUtil.toBuildCaseNo("HHYW");
                    break;
                case 16:
                    //医保通
                    caseNo = SerialNumberUtil.toBuildCaseNo("YBT");
                    break;
                case 17:
                    //保信通
                    caseNo = SerialNumberUtil.toBuildCaseNo("BXT");
                    break;
                case 18:
                    //财务服务
                    caseNo = SerialNumberUtil.toBuildCaseNo("CWFW");
                    break;
                case 19:
                    //索赔通
                    caseNo = SerialNumberUtil.toBuildCaseNo("SPT");
                    break;
                case 20:
                    //乐赔宝
                    caseNo = SerialNumberUtil.toBuildCaseNo("LPB");
                    break;
                case 21:
                    //幸运宝
                    caseNo = SerialNumberUtil.toBuildCaseNo("XYB");
                    break;
                default:
                    caseNo = SerialNumberUtil.toBuildCaseNo("LM");
            }
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,null,caseNo);
    }

    /**
     * 保存发票信息
     */
    @ApiMethod(descript = "保存发票信息", value = "backend-billing-apply-save")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse billingApplySave(ApiRequest apiReq) {

        //查询开票对象是否存在，如不存在则insert
        String companyName = apiReq.getString("companyName");

        BillingApply billingApply = billingApplyMapper.selectByPrimaryKey(apiReq.getLong("id"));
        Long userId = apiReq.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);

        if(billingApply == null){
            String billSource = apiReq.getString("billSource");
            //保存
            billingApply = ConvertToBeanUtil.toBeanFromApiRequest(apiReq, BillingApply.class);
            if ("survey".equals(billSource)){
                billingApply.setBillingState(2);//开票状态 1、未申请；2、申请中；3、已开票
                //更改 狄大人调查案件表的开票状态  开票中
                SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.getSurveyRiskCaseInfoBySurveyCno(billingApply.getCaseNo());
                if (surveyRiskCaseInfo != null){
                    surveyRiskCaseInfo.setIsPayEntrustFee(1);
                    surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                }
                //区别批量开票
                String idList = apiReq.getString("idList");
                if(idList ==null) {
                    //如之前已申请并被驳回，则删除之前的开票记录
                    Map<String, Object> map = new HashMap<>();
                    map.put("caseNo", surveyRiskCaseInfo.getSurveyCno());
                    BillingApply apply = billingApplyMapper.selectByInfo(map);
                    if (apply != null) {
                        billingApplyMapper.deleteByPrimaryKey(apply.getId());
                    }
                }
            }else if ("df".equals(billSource) || "fw".equals(billSource)){//垫付业务开票保存
                billingApply.setBillingState(2);
            }else{
                billingApply.setBillingState(1);//开票状态 1、未申请；2、申请中；3、已开票
            }
            billingApply.setCreateBy(userInfo.getUserName());// 发起人
            billingApply.setCreateById(userInfo.getUserId());// 发起人id
            billingApply.setCreateTime(new Date());//创建时间

            Long staffOrgId = apiReq.getLong("staffOrgId");
            StaffOrgan staffOrgan = this.staffOrganMapper.selectByPrimaryKey(staffOrgId);
            billingApply.setStaffOrgName(staffOrgan.getName());

           /* OrgInfo orgInfo = orgInfoMapper.selectByPrimaryKey(apiReq.getLong("orgId"));
            billingApply.setOrgName(orgInfo.getOrgName());//机构名称*/

            billingApply.setConfirmAccountState(1);

            billingApply.setCompanyName(companyName);

            //委托机构
            SurveyConsignor consignor = surveyConsignorMapper.selectByPrimaryKey(apiReq.getLong("surveyConsignorId"));
            if(consignor!=null){
                billingApply.setSurveyConsignorId(consignor.getId());
                billingApply.setSurveyConsignorName(consignor.getName());
            }
            //委托机构部门
            SurveyConsignorDepartment department = surveyConsignorDepartmentMapper.selectByPrimaryKey(apiReq.getLong("surveyDepartmentId"));
            if(department!=null){
                billingApply.setSurveyDepartmentId(department.getId());
                billingApply.setSurveyDepartmentName(department.getName());
            }
            //委托机构--开票主体
            SurveyConsignorBillSubject billSubject = surveyConsignorBillSubjectMapper.selectByPrimaryKey(apiReq.getLong("surveyBillSubjectId"));
            if(billSubject!=null){
                billingApply.setSurveyBillSubjectId(billSubject.getId());
                billingApply.setSurveyBillSubjectName(billSubject.getName());
            }
//            Long enumItemId = apiReq.getLong("billingItem");
//            BillingApplyEnumItem enumItem = billingApplyEnumItemMapper.selectByPrimaryKey(enumItemId);
//            if(enumItem != null){
//                billingApply.setBillingEnum(enumItem.getBillingEnumId().intValue());
//                billingApply.setBillingItem(enumItem.getBillingItemId().intValue());
//            }
            billingApply.setBillingSource(apiReq.getInt("billingSource"));

            //2019年11月8日 16点53分   增加 。   若狄大人机构的人增开的开票， 则将 狄大人机构ID保存。 用于  调查业务开票查询 自身机构列表
            SurveyInvestigator surveyInvestigator =  surveyInvestigatorMapper.selectByUserId(userInfo.getUserId());
            if (surveyInvestigator != null) {
                billingApply.setSurveyOrgId(surveyInvestigator.getOrgId());
                billingApply.setSurveyOrgName(surveyInvestigator.getOrgName());
            }
            Long meritUserId = apiReq.getLong("meritUserId");
            if (meritUserId != null){
                StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(meritUserId);
                if (staffPersonnelInfo != null){
                    billingApply.setMeritUserId(meritUserId);
                    billingApply.setMeritName(staffPersonnelInfo.getRealName());
                }else {
                    billingApply.setMeritUserId(meritUserId);
                    billingApply.setMeritName(apiReq.getString("meritUserName"));
                }
            }else {
                billingApply.setMeritName(apiReq.getString("meritUserName"));
            }
            Double taxRate = apiReq.getDouble("taxRate");
            if (taxRate != null){
                taxRate = (taxRate + 100) / 100;
            }
            billingApply.setTaxRate(taxRate);

            //添加数据
            billingApplyMapper.insert(billingApply);

            if ("survey".equals(billSource)) {
                //狄大人 -- 批量开票
                String idList = apiReq.getString("idList");
                if (idList != null) {
                    String[] ids = idList.split(",");
                    //批量修改案件状态
                    surveyRiskCaseInfoMapper.updateIsPayEntrustFee(Arrays.asList(idList.split(",")));
                    //插入案件关联开票表
                    Map<String,Object> paramMap =  new HashMap<String,Object>();
                    paramMap.put("billId",billingApply.getId());
                    paramMap.put("createBy",userInfo.getUserName());
                    paramMap.put("createById",userInfo.getUserId());
                    if (!StringUtils.isEmpty(idList)){
                        paramMap.put("ids",idList.split(","));
                        surveyBillingApplyMapper.insertItems(paramMap);
                    }


//                    for (String id : ids) {
//                        //修改原案件状态
//                        SurveyRiskCaseInfo caseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(Long.parseLong(id));
//                        if (caseInfo != null) {
//                            caseInfo.setIsPayEntrustFee(1);
//                            surveyRiskCaseInfoMapper.updateByPrimaryKey(caseInfo);
//
//                            SurveyBillingApply apply = new SurveyBillingApply();
//                            apply.setBillId(billingApply.getId());
//                            apply.setRiskCaseInfoId(caseInfo.getId());
//                            apply.setCreateBy(userInfo.getUserName());
//                            apply.setCreateById(userInfo.getUserId());
//                            apply.setCreateTime(new Date());
//                            surveyBillingApplyMapper.insertSelective(apply);
//                        }
//
//                    }
                }else{
                    SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.getSurveyRiskCaseInfoBySurveyCno(billingApply.getCaseNo());
                    SurveyBillingApply apply = new SurveyBillingApply();
                    apply.setBillId(billingApply.getId());
                    apply.setRiskCaseInfoId(surveyRiskCaseInfo.getId());
                    apply.setCreateBy(userInfo.getUserName());
                    apply.setCreateById(userInfo.getUserId());
                    apply.setCreateTime(new Date());
                    surveyBillingApplyMapper.insertSelective(apply);
                }
            }else if ("df".equals(billSource)){//垫付业务开票
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                String idList = apiReq.getString("idList");
                if (idList != null) {
                    String[] ids = idList.split(",");
                    List<String> asList = Arrays.asList(ids);
                    paramMap.put("ids",asList);
                    paramMap.put("updateType",1);//
                    paramMap.put("settlementState", SettlementEnum.SETTLEMENT_FWFDQRDZ.getState());
                    finaSettlementInfoMapper.updateDataByIds(paramMap);
                    //关联表关联表
                    paramMap =  new HashMap<String,Object>();
                    paramMap.put("payId",billingApply.getId());
                    paramMap.put("oprCode","settlement");
                    paramMap.put("ids",asList);
                    surveyPayInfoDetailNewMapper.insertItems(paramMap);
                    //更新案件状态
                    for (String id : asList) {
                        backendFinaSettlementInfoApi.synCaseData(SettlementEnum.SETTLEMENT_FWFDQRDZ,Long.parseLong(id));
                    }
                }
            }else if ("fw".equals(billSource)){
                Long fwId = apiReq.getLong("fwId");
                SurveyFwCase surveyFwCase = surveyFwCaseMapper.selectByPrimaryKey(fwId);
                if (surveyFwCase != null) {
                    surveyFwCase.setKpzt(1);
                    surveyFwCaseMapper.updateByPrimaryKey(surveyFwCase);
                }
            }
        }else{
            //修改
            billingApply = ConvertToBeanUtil.toBeanFromApiRequest(apiReq,billingApply);
            billingApply.setUpdateBy(userInfo.getUserName());// 更新人
            billingApply.setUpdateById(userInfo.getUserId());// 更新人id
            billingApply.setUpdateTime(new Date());//更新时间


            Long staffOrgId = apiReq.getLong("staffOrgId");
            StaffOrgan staffOrgan = this.staffOrganMapper.selectByPrimaryKey(staffOrgId);
            billingApply.setStaffOrgName(staffOrgan.getName());
//            OrgInfo orgInfo = orgInfoMapper.selectByPrimaryKey(apiReq.getLong("staffOrgId"));
//            billingApply.setOrgName(orgInfo.getOrgName());//机构名称

            billingApply.setCompanyName(companyName);

//            Long enumItemId = apiReq.getLong("billingItem");
//            BillingApplyEnumItem enumItem = billingApplyEnumItemMapper.selectByPrimaryKey(enumItemId);
//            if(enumItem != null){
//                billingApply.setBillingEnum(enumItem.getBillingEnumId().intValue());
//                billingApply.setBillingItem(enumItem.getBillingItemId().intValue());
//            }
            if (billingApply.getMeritUserId() != null){
                StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(billingApply.getMeritUserId());
                if (staffPersonnelInfo != null){
                    billingApply.setMeritName(staffPersonnelInfo.getRealName());
                }else {
                    billingApply.setMeritUserId(billingApply.getMeritUserId());
                    billingApply.setMeritName(apiReq.getString("meritUserName"));
                }
            }else {
                billingApply.setMeritName(apiReq.getString("meritUserName"));
            }
            Double taxRate = apiReq.getDouble("taxRate");
            if (taxRate != null){
                taxRate = (taxRate + 100) / 100;
            }
            billingApply.setTaxRate(taxRate);
            //修改数据
            billingApplyMapper.updateByPrimaryKey(billingApply);
        }

        //操作时，如上传材料凭证,即保存附属表
        String materialImgs = apiReq.getString("materialImgs");
        if (materialImgs != null) {
            String[] urls = materialImgs.split(",");
            for (String url : urls) {
                BillingApplyMaterial billingApplyMaterial = new BillingApplyMaterial();
                billingApplyMaterial.setBillId(billingApply.getId());
                billingApplyMaterial.setMaterialImgs(url);
                billingApplyMaterial.setCreateById(userInfo.getUserId());
                billingApplyMaterial.setCreateBy(userInfo.getUserName());
                billingApplyMaterial.setCreateTime(new Date());
                billingApplyMaterialMapper.insert(billingApplyMaterial);
            }
        }

        //未匹配收款 -- 认领
        String claim = apiReq.getString("claim");
        if(claim!=null){
            BillingApplyUnmatch billingApplyUnmatch = billingApplyUnmatchMapper.selectByPrimaryKey(apiReq.getLong("unmatchId"));
            billingApplyUnmatch.setClaimBy(userInfo.getUserName());
            billingApplyUnmatch.setClaimById(userInfo.getUserId());
            billingApplyUnmatch.setClaimTime(new Date());
            billingApplyUnmatch.setBillId(billingApply.getId());
            billingApplyUnmatch.setBillNo(billingApply.getCaseNo());
            billingApplyUnmatch.setState(2);
            billingApplyUnmatchMapper.updateByPrimaryKeySelective(billingApplyUnmatch);

            //更新开票清单
            billingApply.setBillingState(2);// 2、申请中
            billingApply.setConfirmAccountTime(new Date());
            billingApply.setConfirmAccountState(2);//确认到账状态：1、未到账 2、已到账
            billingApplyMapper.updateByPrimaryKey(billingApply);

            //生成公估确认到账记录
            BillingApplyAccounts billingApplyAccounts =  new BillingApplyAccounts();
            billingApplyAccounts.setMoney(billingApplyUnmatch.getMoney());
            billingApplyAccounts.setBillId(billingApply.getId());
            billingApplyAccounts.setState(1);
            billingApplyAccounts.setCreateBy(userInfo.getUserName());
            billingApplyAccounts.setCreateById(userInfo.getUserId());
            billingApplyAccounts.setCreateTime(new Date());
            billingApplyAccountsMapper.insertSelective(billingApplyAccounts);

        }

        //确认开票时,查询开票对象是否存在，如不存在则insert
        Long entrustOrgId = apiReq.getLong("entrustOrgId");
        if(!org.apache.commons.lang3.StringUtils.isEmpty(billingApply.getCompanyName())){
            Map<String, Object> map = new HashMap<>();
            map.put("companyName",billingApply.getCompanyName());
            map.put("entrustOrgId",entrustOrgId);
            BillingApplyCompany company = billingApplyCompanyMapper.selectByInfo(map);
            if(company == null){
                company = new BillingApplyCompany();
                company.setCompanyName(billingApply.getCompanyName());
                company.setEntrustOrgId(entrustOrgId);
                if (company.getEntrustOrgId() != null){
                    SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(company.getEntrustOrgId());
                    if (surveyConsignor != null){
                        company.setEntrustOrgName(surveyConsignor.getName());
                    }
                }
                company.setCreateTime(new Date());
                company.setCreateBy(userInfo.getUserId());
                company.setCreateByName(userInfo.getUserName());
                company.setState(0);
                company.setDeleteFlag(0);
                billingApplyCompanyMapper.insert(company);
            }
            if (billingApply != null){
                billingApply.setCompanyId(company.getId());//更新开票对象
            }
        }

        //
        Map<String, Object> map2 = new HashMap<>();
        map2.put("recipientsName",billingApply.getRecipientsName());
        map2.put("recipientsPhone",billingApply.getRecipientsPhone());
        map2.put("address",billingApply.getAddress());
        map2.put("curUserId",userInfo.getUserId());
        BillingApplyRecipient recipient = billingApplyRecipientMapper.selectByInfo(map2);
        if(recipient == null){
            recipient = new BillingApplyRecipient();
            recipient.setRecipientsName(billingApply.getRecipientsName());
            recipient.setRecipientsPhone(billingApply.getRecipientsPhone());
            recipient.setProvince(billingApply.getProvince());
            recipient.setProvinceId(billingApply.getProvinceId());
            recipient.setCity(billingApply.getCity());
            recipient.setCityId(billingApply.getCityId());
            recipient.setDistrict(billingApply.getDistrict());
            recipient.setDistrictId(billingApply.getDistrictId());
            recipient.setAddress(billingApply.getAddress());
            recipient.setCreateBy(billingApply.getCreateBy());
            recipient.setCreateById(billingApply.getCreateById());
            recipient.setStaffOrgId(billingApply.getStaffOrgId());
            recipient.setStaffOrgName(billingApply.getStaffOrgName());
            billingApplyRecipientMapper.insert(recipient);
        }else{
            recipient.setStaffOrgId(billingApply.getStaffOrgId());
            recipient.setStaffOrgName(billingApply.getStaffOrgName());
            billingApplyRecipientMapper.updateByPrimaryKey(recipient);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 发票信息提交申请
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "发票信息提交申请", value = "backend-billing-apply-state-up")
    @Override
    public ApiResponse billApplyStateUp(ApiRequest apiReq){

        BillingApply billingApply = billingApplyMapper.selectByPrimaryKey(apiReq.getLong("id"));
        Long menuType = apiReq.getLong("menuType");
        if (menuType != null){
            String model = apiReq.getString("model");//退票retreat   重新开票renew
            //机构经理和总经理 操作
            if(menuType == 5){
                if ("retreat".equals(model)){
                    billingApply.setBillingState(5);//退票审核中
                }else if ("renew".equals(model)){
                    billingApply.setBillingState(6);//重开审核中
                }else if ("retreatPass".equals(model)){
                    billingApply.setBillingState(7);//退票审核通过
                }else if ("renewPass".equals(model)){
                    billingApply.setBillingState(8);//重开审核通过
                }else if ("back".equals(model)){
                    String reason = apiReq.getString("reason");
                    billingApply.setBillingState(3);//审核不通过-  改成原始状态已开票
                    billingApply.setOperReason(reason);
                }
            }
            //财务操作“确认退票”和“确认重开”
            if(menuType == 6) {
                if ("confirmRetreat".equals(model)) {
                    billingApply.setBillingState(4);//已退票
                } else if ("confirmNew".equals(model)) {
                    billingApply.setBillingState(3);//通过重开 --改成原始状态已开票
                }
            }
            //评估审核通过，转入客服
            if(menuType == 2){
                if ("PGpass".equals(model)){
                    Double billingMoney = apiReq.getDouble("billingMoney");
                    billingApply.setBillingMoney(billingMoney);
                    billingApply.setBillingState(2);//开票中
                }
            }
            int result = this.billingApplyMapper.updateByPrimaryKey(billingApply);
            if(result>0){
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }else{
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        }

        Integer billingState = apiReq.getInt("billingState");
        billingApply.setBillingState(billingState);//开票状态 1、未申请；2、申请中；3、已开票

        //驳回申请的原因
        String rejectReason = apiReq.getString("rejectReason");
        if(rejectReason != null){
            billingApply.setRejectReason(rejectReason);
//            if(billingApply.getBillingSource()!=null && billingApply.getBillingSource()==3){//狄大人调查的开票
//                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("billId",billingApply.getId());
//                List<SurveyBillingApply> applyList = surveyBillingApplyMapper.selectByInfo(map);
//                if(applyList.size() == 0){ //单个开票
//                    SurveyRiskCaseInfo info = surveyRiskCaseInfoMapper.getSurveyRiskCaseInfoBySurveyCno(billingApply.getCaseNo());
//                    info.setIsPayEntrustFee(0);
//                    surveyRiskCaseInfoMapper.updateByPrimaryKeySelective(info);
//                }else{
//                    //批量开票
//                    for (SurveyBillingApply surveyBillingApply : applyList) {
//                        SurveyRiskCaseInfo info = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyBillingApply.getRiskCaseInfoId());
//                        if(info!=null){
//                            info.setIsPayEntrustFee(0);
//                            surveyRiskCaseInfoMapper.updateByPrimaryKeySelective(info);
//                        }
//                    }
//                    billingApplyMapper.deleteByPrimaryKey(billingApply.getId());
//                    return new ApiResponse(ApiMsgEnum.SUCCESS);
//                }
//            }
            if(billingApply.getBillingSource()!=null && billingApply.getBillingSource()==4){//司法评估
                LawCaseInfo info = lawCaseInfoMapper.selectByCaseNo(billingApply.getCaseNo());
                info.setIsBill(0);
                lawCaseInfoMapper.updateByPrimaryKeySelective(info);
            }
//            else if(billingApply.getBillingSource()!=null && billingApply.getBillingSource()==6){//未匹配收款
//                BillingApplyUnmatch info = billingApplyUnmatchMapper.selectByCaseNo(billingApply.getCaseNo());
//                info.setState(1);
//                billingApplyUnmatchMapper.updateByPrimaryKeySelective(info);
//                List<BillingApplyAccounts> billingApplyAccounts = billingApplyAccountsMapper.selectListByBillId(billingApply.getId());
//                for(BillingApplyAccounts accounts : billingApplyAccounts){
//                    billingApplyAccountsMapper.deleteByPrimaryKey(accounts.getId());
//                }
//            }
        }
        Long userId = apiReq.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        billingApply.setUpdateBy(userInfo.getUserName());// 更新人
        billingApply.setUpdateById(userInfo.getUserId());// 更新人id
        billingApply.setUpdateTime(new Date());//更新时间

        int result = this.billingApplyMapper.updateByPrimaryKey(billingApply);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    /**
     * 保存开票材料
     * @param request
     * @return
     */
    @ApiMethod(descript = "保存开票材料",value = "backend-billing-apply-save-material")
    @Override
    public ApiResponse saveMaterial(ApiRequest request) {
        Long id = request.getLong("id");
        BillingApply apply = billingApplyMapper.selectByPrimaryKey(id);

        if(apply!=null) {
            String strUrl = request.getString("img");
            if (strUrl != null) {
                String[] urls = strUrl.split(",");
                for (String url : urls) {
                    BillingApplyMaterial billingApplyMaterial = new BillingApplyMaterial();
                    billingApplyMaterial.setBillId(id);
                    billingApplyMaterial.setCreateTime(new Date());
                    billingApplyMaterial.setMaterialImgs(url);

                    Long userId = request.getLong("operatorId");
                    UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
                    billingApplyMaterial.setCreateById(userId);
                    billingApplyMaterial.setCreateBy(userInfo.getUserName());
                    billingApplyMaterialMapper.insert(billingApplyMaterial);
                }
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }


    /**
     * 删除开票信息
     */
    @ApiMethod(descript = "删除开票信息", value = "backend-billing-apply-delete")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse billingApplyDelete(ApiRequest apiReq) {

        Long billId = apiReq.getLong("id");
        BillingApply billingApply = billingApplyMapper.selectByPrimaryKey(billId);
        if(billingApply != null){
            if(billingApply.getBillingSource()!=null && billingApply.getBillingSource()==3){//狄大人调查的开票
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("billId",billingApply.getId());
                List<SurveyBillingApply> applyList = surveyBillingApplyMapper.selectByInfo(map);
                if(applyList.size() == 0){ //单个开票
                    SurveyRiskCaseInfo info = surveyRiskCaseInfoMapper.getSurveyRiskCaseInfoBySurveyCno(billingApply.getCaseNo());
                    info.setIsPayEntrustFee(0);
                    surveyRiskCaseInfoMapper.updateByPrimaryKeySelective(info);
                }else{
                    //批量开票
                    for (SurveyBillingApply surveyBillingApply : applyList) {
                        SurveyRiskCaseInfo info = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyBillingApply.getRiskCaseInfoId());
                        if(info!=null){
                            info.setIsPayEntrustFee(0);
                            surveyRiskCaseInfoMapper.updateByPrimaryKeySelective(info);
                        }
                    }
                    billingApplyMapper.deleteByPrimaryKey(billingApply.getId());
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
            }
            else if(billingApply.getBillingSource()!=null && billingApply.getBillingSource()==6){//未匹配收款
                BillingApplyUnmatch info = billingApplyUnmatchMapper.selectByCaseNo(billingApply.getCaseNo());
                info.setState(1);
                billingApplyUnmatchMapper.updateByPrimaryKeySelective(info);
                List<BillingApplyAccounts> billingApplyAccounts = billingApplyAccountsMapper.selectListByBillId(billingApply.getId());
                for(BillingApplyAccounts accounts : billingApplyAccounts){
                    billingApplyAccountsMapper.deleteByPrimaryKey(accounts.getId());
                }
            }
        }
        if(billingApply != null){
            billingApplyMapper.deleteByPrimaryKey(billId);
        }
        List<BillingApplyMaterial> billingApplyMaterial = billingApplyMaterialMapper.selectListByBillId(billId);
        for (int i = 0; i <billingApplyMaterial.size() ; i++) {
            billingApplyMaterialMapper.deleteByPrimaryKey(billingApplyMaterial.get(i).getId());
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS);

    }

    /**
     * 发票作废或红冲
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "发票作废或红冲", value = "backend-billing-apply-imgs-upd")
    @Override
    public ApiResponse billApplyImgsUpd(ApiRequest apiReq) {
        Long currentUserId = apiReq.getLong("operatorId");
        Long id = apiReq.getLong("id");
        Integer state = apiReq.getInt("billApplyImgsState");
        String oprType = apiReq.getString("oprType");
        BillingApplyImgs billingApplyImgs = billingApplyImgsMapper.selectByPrimaryKey(id);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        billingApplyImgs.setStateUpdateBy(userInfo.getUserName());
        billingApplyImgs.setStateUpdateById(userInfo.getUserId());
        if ("chexiao-acc".equals(oprType)){//撤销到账
            billingApplyImgs.setPayState(0);
            //删除到账记录
            List<BillingApplyAccounts> accounts = billingApplyAccountsMapper.selectListByImgsId(billingApplyImgs.getId());
            for (BillingApplyAccounts account : accounts) {
                account.setState(0);
                billingApplyAccountsMapper.updateByPrimaryKey(account);
            }
            billingApplyImgsMapper.updateByPrimaryKeySelective(billingApplyImgs);
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }

        billingApplyImgs.setState(state);
        billingApplyImgs.setStateUpdateTime(new Date());

        if(state == 2 || state == 3){ //作废或者红冲的发票都是未到账的状态
            billingApplyImgs.setPayState(0);
        }

        int result=billingApplyImgsMapper.updateByPrimaryKeySelective(billingApplyImgs);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    /**
     * 删除单条发票或材料记录
     */
    @ApiMethod(descript = "删除单条发票、材料记录、公估确认到账记录", value = "backend-billing-apply-imgs-delete")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse billApplyImgsDelete(ApiRequest apiReq) {

        Long id = apiReq.getLong("id");
        String type = apiReq.getString("type");
        int result=0;
        if("billingImgs".equals(type)){
            //删除发票记录
            result = this.billingApplyImgsMapper.deleteByPrimaryKey(id);
        }
        if("materialImgs".equals(type)){
            //删除材料记录
            result = this.billingApplyMaterialMapper.deleteByPrimaryKey(id);
        }
        if("accounts".equals(type)){
            //删除公估确认到账记录
            result = this.billingApplyAccountsMapper.deleteByPrimaryKey(id);
        }
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    /**
     * 公估确认到账
     */
    @ApiMethod(descript = "公估确认到账", value = "backend-confirm-account-submit")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse confirmAccountSubmit(ApiRequest apiReq) {

        Long id = apiReq.getLong("id");
        Long imgsId = apiReq.getLong("imgsId");
        BillingApplyImgs imgs = billingApplyImgsMapper.selectByPrimaryKey(imgsId);
        //并将“公估确认到账”保存
        BillingApplyAccounts accounts = new BillingApplyAccounts();
        accounts.setBillId(imgs.getBillId());
        String img = apiReq.getString("img");
        accounts.setImgs(img);
        Double money = apiReq.getDouble("money");
        accounts.setMoney(DecimalUtil.twoDecimalTOFourFromFive(money));
        Long userId = apiReq.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        accounts.setCreateById(userId);
        accounts.setCreateBy(userInfo.getUserName());
        accounts.setState(1);
        Date confirmAccountTime = DateUtils.parseDate(apiReq.getString("confirmAccountTime"), "yyyy-MM-dd");
        accounts.setCreateTime(new Date());
        accounts.setAccountTime(confirmAccountTime);
        accounts.setBillImgsId(imgs.getId());
        String remark = apiReq.getString("remark");
        accounts.setRemark(remark);
        billingApplyAccountsMapper.insertSelective(accounts);

        List<BillingApplyAccounts> billingApplyAccounts = billingApplyAccountsMapper.selectListByImgsId(imgs.getId());
        double okAccountMoney = billingApplyAccounts.stream().mapToDouble(BillingApplyAccounts::getMoney).sum();
        if (imgs.getBillingMoney() > okAccountMoney){
            imgs.setPayState(1);
        }
        if (imgs.getBillingMoney() == okAccountMoney){
            imgs.setPayState(2);
        }
        billingApplyImgsMapper.updateByPrimaryKey(imgs);

        BillingApply billingApply = billingApplyMapper.selectByPrimaryKey(id);
        if (billingApply.getBillingMoney() == null){
            billingApply.setBillingMoney(0D);
        }
        if (billingApply.getConfirmAccountMoney() == null){
            billingApply.setConfirmAccountMoney(0D);
        }
        List<BillingApplyAccounts> okAccountList = billingApplyAccountsMapper.selectListByBillId(billingApply.getId());
        Double allOkMoney = okAccountList.stream().mapToDouble(BillingApplyAccounts::getMoney).sum();
        if (billingApply.getBillingMoney().doubleValue() == allOkMoney.doubleValue()){
            billingApply.setConfirmAccountState(2);
        }else {
            billingApply.setConfirmAccountState(1);
        }
        //修改公估到账时间、金额、状态
        billingApply.setConfirmAccountTime(confirmAccountTime);
        int result = billingApplyMapper.updateByPrimaryKey(billingApply);

        /** * 狄大人平台 调查开票  更改案件的 到账时间 */
        if (billingApply.getBillingSource()!=null && billingApply.getBillingSource()==3){
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.getSurveyRiskCaseInfoBySurveyCno(billingApply.getCaseNo());
            if(surveyRiskCaseInfo != null){
                if (surveyRiskCaseInfo.getEntrustReportStartDate() != null && surveyRiskCaseInfo.getEntrustReportEndDate() != null){//只有保司终审通过的案子才可结案
                    if (surveyRiskCaseInfo.getPayType() == 2 || surveyRiskCaseInfo.getPayType() == 4){//有减损的时候  必须基本费 和  减损 都标记结算 才可结案
                        if (surveyRiskCaseInfo.getPrice1IsCalc() == 1 && surveyRiskCaseInfo.getPrice2IsCalc() == 1){
                            //2019年8月22日09点25分  增加雪球  确认到账 更改案件为结案状态
                            surveyRiskCaseInfo.setCloseStartDate(new Date());
                            surveyRiskCaseInfo.setCloseEndDate(new Date());
                            surveyRiskCaseInfo.setSurveyState(34);
                            surveyRiskCaseInfo.setSurveyPhase(3);
                            surveyRiskCaseInfo.setSurveyStateName("已结案");
                        }
                    }else{
                        //2019年8月22日09点25分  增加雪球  确认到账 更改案件为结案状态
                        surveyRiskCaseInfo.setCloseStartDate(new Date());
                        surveyRiskCaseInfo.setCloseEndDate(new Date());
                        surveyRiskCaseInfo.setSurveyState(34);
                        surveyRiskCaseInfo.setSurveyPhase(3);
                        surveyRiskCaseInfo.setSurveyStateName("已结案");

                    }
                }
                surveyRiskCaseInfo.setArrivalDate(new Date());
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
            }else{//批量开票的数据
                Map<String,Object> map = new HashMap<>();
                map.put("billId",billingApply.getId());
                List<SurveyBillingApply> applyList = surveyBillingApplyMapper.selectByInfo(map);
                for (int i = 0; i < applyList.size(); i++) {
                    surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(applyList.get(i).getRiskCaseInfoId());
                    if(surveyRiskCaseInfo != null){
                        if (surveyRiskCaseInfo.getEntrustReportStartDate() != null && surveyRiskCaseInfo.getEntrustReportEndDate() != null) {//只有保司终审通过的案子才可结案
                            if (surveyRiskCaseInfo.getPayType() == 2 || surveyRiskCaseInfo.getPayType() == 4) {//有减损的时候  必须基本费 和  减损 都标记结算 才可结案
                                if (surveyRiskCaseInfo.getPrice1IsCalc() == 1 && surveyRiskCaseInfo.getPrice2IsCalc() == 1){
                                    //2019年8月22日09点25分  增加雪球  确认到账 更改案件为结案状态
                                    surveyRiskCaseInfo.setCloseStartDate(new Date());
                                    surveyRiskCaseInfo.setCloseEndDate(new Date());
                                    surveyRiskCaseInfo.setSurveyState(34);
                                    surveyRiskCaseInfo.setSurveyPhase(3);
                                    surveyRiskCaseInfo.setSurveyStateName("已结案");
                                }
                            }else{
                                //2019年8月22日09点25分  增加雪球  确认到账 更改案件为结案状态
                                surveyRiskCaseInfo.setCloseStartDate(new Date());
                                surveyRiskCaseInfo.setCloseEndDate(new Date());
                                surveyRiskCaseInfo.setSurveyState(34);
                                surveyRiskCaseInfo.setSurveyPhase(3);
                                surveyRiskCaseInfo.setSurveyStateName("已结案");
                            }
                        }
                        surveyRiskCaseInfo.setArrivalDate(new Date());
                        surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                    }
                }
            }
        }else if (billingApply.getBillingSource() != null && billingApply.getBillingSource() == 6){//如果为垫付业务 修改状态
            if (billingApply.getConfirmAccountState() == 2){
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("billId",billingApply.getId());
                paramMap.put("settlementState",SettlementEnum.SETTLEMENT_ZCJA.getState());
                surveyPayInfoDetailNewMapper.updateSettlementItems(paramMap);
                paramMap =  new HashMap<String,Object>();
                paramMap.put("billId",billingApply.getId());
                List<Long> ids = surveyPayInfoDetailNewMapper.getSettlementItems(paramMap);
                for (Long settlementInfoId : ids) {
                    backendFinaSettlementInfoApi.synCaseData(SettlementEnum.SETTLEMENT_ZCJA,settlementInfoId);
                }
            }
        }

        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }


    /**
     * 根据billId，查询开票信息及发票单号信息
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据billId，查询开票信息及发票单号信息" ,value = "backend-billing-apply-imgs-for-export")
    @Override
    public ApiResponse searchBillingApplyImgsForExport(ApiRequest apiReq){
        apiReq = putApiReq(apiReq);
        this.setBackendPageSize(apiReq);
        List<BillingApplyImgsMaterialDto> billingApply = billingApplyMapper.selectImgsInfoForExport(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,1,billingApply);
    }

    /**
     * 根据billId，查询开票信息及材料信息
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据billId，查询开票信息及材料信息" ,value = "backend-billing-apply-material-for-export")
    @Override
    public ApiResponse searchBillingApplyMaterialForExport(ApiRequest apiReq){
        apiReq = putApiReq(apiReq);
        this.setBackendPageSize(apiReq);
        List<BillingApplyImgsMaterialDto> billingApply = billingApplyMapper.selectMaterialInfoForExport(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,1,billingApply);
    }

    /**
     * 需要合并的开票list
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "需要合并的开票list" ,value = "backend-billing-apply-merge-list")
    @Override
    public ApiResponse<List<BillingApply>> billingApplyMergeList(ApiRequest apiReq){

        //当前登录人是不是管理员
        Long currentUserId = apiReq.getLong("operatorId");
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean manager = isRoleUser(userRoles,1L);//管理员
        if(manager){
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            apiReq.put("mType",1);
            apiReq.put("orgId",userInfo.getOrgId());
        }else{
            apiReq.put("mType",2);
            apiReq.put("currentUserId",currentUserId);
        }


        this.setBackendPageSize(apiReq);
        BillingApply billingApply = billingApplyMapper.selectByPrimaryKey(apiReq.getLong("id"));

        apiReq.put("companyId",billingApply.getCompanyId());
        apiReq.put("billingType",billingApply.getBillingType());
        apiReq.put("businessType", billingApply.getBusinessType());

        List<BillingApply> billingApplyList = billingApplyMapper.selectListForMerge(apiReq);

        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, billingApplyList);
    }

    /**
     * 合并开票
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "合并开票", value = "backend-billing-apply-merge")
    @Override
    public ApiResponse billingApplyMeger(ApiRequest apiReq) {
        Long currentUserId = apiReq.getLong("operatorId");

        String idArrs = apiReq.getString("idArr");
        if (idArrs != null) {
            String[] idArr = idArrs.split(",");
            BillingApply billingApply = new BillingApply();
            Double billingMoney =0D;
            for (String id : idArr) {
                billingApply = billingApplyMapper.selectByPrimaryKey(Long.parseLong(id));
                billingMoney += billingApply.getBillingMoney();
            }
            //生成最新的开票记录（合并在一起的）
            BillingApply newInfo = ConvertToBeanUtil.buildInfo(BillingApply.class,billingApply);
            newInfo.setBillingMoney(billingMoney);
            newInfo.setCreateTime(new Date());
            newInfo.setCreateById(currentUserId);
            newInfo.setIsMerge(1);
            billingApplyMapper.insertSelective(newInfo);

            //将被合并的数据，父级id 改为最新数据id
            for (String id : idArr) {
                billingApply = billingApplyMapper.selectByPrimaryKey(Long.parseLong(id));
                billingApply.setParentId(newInfo.getId());
                billingApplyMapper.updateByPrimaryKey(billingApply);

                //将被合并的数据关联的材料凭证数据，更新为最新的开票记录
                List<BillingApplyMaterial> materials = billingApplyMaterialMapper.selectListByBillId(Long.parseLong(id));
                for (int i = 0; i < materials.size(); i++) {
                    materials.get(i).setBillId(newInfo.getId());
                    billingApplyMaterialMapper.updateByPrimaryKey(materials.get(i));
                }
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 合并开票（角色是不是新角色）
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "合并开票（角色是不是新角色）" ,value = "backend-billing-apply-merge-role")
    @Override
    public ApiResponse<BusUserRole> billingApplyMergeRole(ApiRequest apiReq){

        //当前登录人是不是管理员
        Long currentUserId = apiReq.getLong("operatorId");
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        for (BusUserRole busUserRole : userRoles){
            //新角色
            if (busUserRole.getRoleId() == 33L){
                BusUserRole newUserRole = new BusUserRole();
                newUserRole = ConvertToBeanUtil.buildInfo(BusUserRole.class,busUserRole);
                return new ApiResponse(ApiMsgEnum.SUCCESS, 1, newUserRole);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, 0, null);
    }

    /**
     *
     * @param apiRequest
     * @return
     */

    @ApiMethod(descript = "财务中心管理数量", value = "backend-finance-info-number")
    @Override
    public ApiResponse<FinanceNumberDto> financeNumber(ApiRequest apiRequest) {

        FinanceNumberDto numberDto =  new FinanceNumberDto();
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(apiRequest.getLong("operatorId"));
        String isTest =null;
        if(userInfo != null){
            if(userInfo.getIsTester() != 1){
                //不是测试人员：默认查询非测试案件
                isTest ="0";
            }else{
                //测试人员:默认查询测试案件
                isTest ="1";
            }
        }
        int count = 0;
        //金融未开票
        apiRequest.clear();
        apiRequest.put("mType",11);
        count = billingApplyMapper.selectListSize(apiRequest);
        numberDto.setNumber1(count);

        //公估未开票
        apiRequest.clear();
        apiRequest.put("mType",12);
        count = billingApplyMapper.selectListSize(apiRequest);
        numberDto.setNumber2(count);

        //公估未确认到账
        apiRequest.clear();
        apiRequest.put("mType",13);
        count = billingApplyMapper.selectListSize(apiRequest);
        numberDto.setType1(count);

        //苏宁代扣 未确认到账
        apiRequest.clear();
        apiRequest.put("withholdState",2);
        apiRequest.put("numberType",11);
        apiRequest.put("isTest",isTest);
        count = suningWithholdApplyMapper.selectListSize(apiRequest);
        numberDto.setNumber3(count);

        //还款清单 -- 未提交还款
        apiRequest.clear();
        apiRequest.put("handOutFlag",2);
        apiRequest.put("isTest",isTest);
        count = caseCenterInfoMapper.selectCaseCenterInfoForRepaySize(apiRequest);
        numberDto.setType2(count);

        //还款确认 -- 待确认还款
        apiRequest.clear();
        apiRequest.put("isTest",isTest);
        count = caseCenterInfoMapper.selectConfirmRepayCaseCenterInfoSize(apiRequest);
        numberDto.setType3(count);

        //待提现确认
        apiRequest.clear();
        apiRequest.put("mtype",11);
        count = withdrawalsInfoMapper.selectWithdrawalsInfoListSize(apiRequest);
        numberDto.setType4(count);

        //待审核项目
        apiRequest.clear();
        apiRequest.put("auditState",2);
        apiRequest.put("isTest",isTest);
        count = casePayInfoMapper.selectCountCasePayInfo(apiRequest);
        numberDto.setType5(count);

        //待支付项目
        apiRequest.clear();
        apiRequest.put("payState",0);
        apiRequest.put("isTest",isTest);
        count = casePayInfoMapper.selectCountCasePayInfo(apiRequest);
        numberDto.setType6(count);


        return new ApiResponse(ApiMsgEnum.SUCCESS,1,numberDto);
    }

    /**
     * 财务中心管理数量
     * @param apiRequest
     * @return
     */

    @ApiMethod(descript = "财务中心管理数量", value = "backend-customer-info-number")
    @Override
    public ApiResponse<CustomerNumberDto> customerNumber(ApiRequest apiRequest) {
        Long userId = apiRequest.getLong("operatorId");

        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(apiRequest.getLong("operatorId"));
        String isTest =null;
        if(userInfo != null){
            if(userInfo.getIsTester() != 1){
                //不是测试人员：默认查询非测试案件
                isTest ="0";
            }else{
                //测试人员:默认查询测试案件
                isTest ="1";
            }
        }

        CustomerNumberDto numberDto =  new CustomerNumberDto();
        int count = 0;
        //待审核案件
        apiRequest.clear();
        apiRequest.put("state",1);
        apiRequest.put("isTest",isTest);
        count = caseApplicationInfoMapper.selectCountCaseApplicationInfo(apiRequest);
        numberDto.setNumber1(count);

        //待分配案件
        apiRequest.clear();
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        UserInfo ui = userInfoMapper.selectAgency(map);
        if(ui != null){
            //查询该客户是否是调度员，并是否已分配机构
            List<UserPatchOrg> userPatchOrgList = userPatchOrgMapper.selectInfoByUserId(map);
            if(userPatchOrgList != null && userPatchOrgList.size() > 0){
                String [] orgIds = new String[userPatchOrgList.size()];
                for (int i = 0; i < userPatchOrgList.size(); i++) {
                    orgIds[i] = userPatchOrgList.get(i).getOrgId().toString();
                }
                apiRequest.put("orgIds",orgIds);
                apiRequest.put("isTest",isTest);
                count = caseCenterInfoAllotMapper.selectCountAgencyCaseCenterInfoByPatch(apiRequest);
            }else if(ui.getOrgId()!=null){
                String [] orgIds = new String[1];
                orgIds[0] = ui.getOrgId().toString();
                apiRequest.put("orgIds",orgIds);
                apiRequest.put("isTest",isTest);
                count = caseCenterInfoAllotMapper.selectCountAgencyCaseCenterInfoByPatch(apiRequest);
            }
        }
        numberDto.setNumber2(count);

        //待提现审核
        apiRequest.clear();
        apiRequest.put("mtype",1);
        count = withdrawalsInfoMapper.selectWithdrawalsInfoListSize(apiRequest);
        numberDto.setNumber3(count);

        //待支付案件
        apiRequest.clear();
        apiRequest.put("numberType",1);
        apiRequest.put("isTest",isTest);
        count = caseApplicationInfoMapper.selectCountCaseApplicationInfo(apiRequest);
        numberDto.setType1(count);

        //超时未接收
        apiRequest.clear();
        apiRequest.put("overTimeType",1);
        apiRequest.put("isTest",isTest);
        count = caseCenterInfoMapper.selectCountFour(apiRequest) ;
        numberDto.setType2(count);

        //超时未跟踪
        apiRequest.clear();
        apiRequest.put("overTimeType",2);
        apiRequest.put("isTest",isTest);
        count = caseCenterInfoMapper.selectCountFour(apiRequest) ;
        numberDto.setType3(count);

        //超时状态未变更
        apiRequest.clear();
        apiRequest.put("overTimeType",3);
        apiRequest.put("isTest",isTest);
        count = caseCenterInfoMapper.selectCountFour(apiRequest) ;
        numberDto.setType4(count);

        //超时未签约
        apiRequest.clear();
        apiRequest.put("overTimeType",4);
        apiRequest.put("isTest",isTest);
        count = caseCenterInfoMapper.selectCountFour(apiRequest) ;
        numberDto.setType5(count);

        //扣罚记录
        apiRequest.clear();
        apiRequest.put("isTest",isTest);
        count = caseCenterInfoFinedMapper.selectCaseCenterInfoFinedListSize(apiRequest);
        numberDto.setType6(count);

        //伤残赔付待转办
        apiRequest.clear();
        apiRequest.put("mStatus" , 1);
        apiRequest.put("turnStatus" , 1);
        count = caseEstimateInfoMapper.selectCountCaseEstimateInfo(apiRequest);
        numberDto.setType7(count);

        //解约审核
        apiRequest.clear();
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(userId);
        Boolean marketManager = isRoleUser(userRoles,19L);
        Boolean customer = isRoleUser(userRoles,30L);
        if (marketManager && customer){
            apiRequest.put("menuType",56);
        }else{
            if (marketManager){
                apiRequest.put("releaseState",4);
            }else if (customer){
                apiRequest.put("releaseState",1);
            }else{
                apiRequest.put("releaseState",-99);//-99代表 该条件下 无数据
            }
        }
        apiRequest.put("isTest",isTest);
        count = caseCenterInfoMapper.findListSize(apiRequest);
        numberDto.setType8(count);

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,numberDto);
    }


    /**
     * 开票类目 -- 枚举查询
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "开票类目 -- 枚举查询" ,value = "backend-common-enum-for-billing-enum")
    @Override
    public ApiResponse<CommonEnum> searchBillingEnum(ApiRequest apiReq){
        Long currentUserId = apiReq.getLong("operatorId");

        String enumCode = apiReq.getString("enumCode");
        List<CommonEnum> enums = commonEnumMapper.selectListByParentEnumCode(enumCode);

        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean roleUser = isRoleUser(userRoles, 144L);
        if (roleUser){
//            1,2,3,9,11,257,17,8,13
            List<CommonEnum> collect = enums.stream().filter(p -> "1".equals(p.getEnumCode()) || "2".equals(p.getEnumCode()) || "3".equals(p.getEnumCode())
                    || "9".equals(p.getEnumCode()) || "8".equals(p.getEnumCode()) || "13".equals(p.getEnumCode()) ||
                    "11".equals(p.getEnumCode()) || "257".equals(p.getEnumCode()) || "17".equals(p.getEnumCode())).collect(Collectors.toList());
            return new ApiResponse(ApiMsgEnum.SUCCESS, 0, collect);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, 0, enums);
    }

    /**
     * 公估确认到账记录
     * @param
     * @return
     */
    @ApiMethod(descript = "公估确认到账记录",value = "backend-billing-apply-accounts-by-id")
    @Override
    public ApiResponse findBillApplyAccountsById(ApiRequest request){
        Long id = request.getLong("id");
        List<BillingApplyAccounts> billingApplyAccounts = billingApplyAccountsMapper.selectListByBillId(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS,null,billingApplyAccounts);
    }

    /**
     * 开票列表导出
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "发票列表" ,value = "backend-billing-apply-list-for-export")
    @Override
    public ApiResponse billingApplyListForExport(ApiRequest apiReq){
        //导出：发票清单、开票、开票管理 三个模块的分别条件
        apiReq = putApiReq(apiReq);
        setPageIndex(apiReq);
        List<BillingApplyDto> list = billingApplyMapper.selectListForExportGonggu(apiReq);
        int count = billingApplyMapper.selectListForExportGongguCount(apiReq);
//        for (BillingApply billingApply : list) {
//            List<BillingApplyAccounts> billingApplyAccounts = billingApplyAccountsMapper.selectListByImgsId(billingApply.getId());
//            billingApply.setBillingApplyAccountsList(billingApplyAccounts);
//        }
//      list = billingApplyMapper.selectListForExport(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, list);
    }


    /**
     * 数据 info
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "根据id，查询关联的子表数据", value = "backend-billing-select-info-by-relation-id", apiParams = { })
    @Override
    public ApiResponse selectInfoByRelationId(ApiRequest apiReq) {
        Long userId = apiReq.getLong("operatorId");
        List<BusUserRole> roles = busUserRoleMapper.orgUserRoleList(userId);

        String btnCode = apiReq.getString("btnCode");
        Map<String ,Object> map  =  new HashMap<>();
        //根据“产品类型id”，查询“机构”
        if("1000".equals(btnCode)){
            String auth = apiReq.getString("auth");//判断是否需要根据“开票权限”筛选机构数据
            List<OrgInfo> orgInfoList = new ArrayList<>();
            if(auth != null) {
                map.put("productId", apiReq.getLong("productTypeId"));
                List<BillingApplyProductOrg> productOrgList = billingApplyProductOrgMapper.list(map);
                for (BillingApplyProductOrg billingApplyProductOrg : productOrgList) {
                    OrgInfo info = orgInfoMapper.selectByPrimaryKey(billingApplyProductOrg.getOrgId());
                    orgInfoList.add(info);
                }
            }else{
                Long typeId =  apiReq.getLong("productTypeId");
                typeId = typeId == null ? 1 : typeId;
                if(typeId == 1){
                    map.put("orgName","公估");
                }else if(typeId == 2){
                    map.put("orgName","个人");
                }
                map.put("orgType",1);
                map.put("orgParentid",-1);
                List<OrgInfo> orgInfos = orgInfoMapper.queryOrgList(map);
                for (OrgInfo orgInfo : orgInfos){
                    map.clear();
                    map.put("orgParentid",orgInfo.getId());
                    orgInfoList = orgInfoMapper.queryOrgList(map);
                }
            }

            //“年度汇总表”功能，如果是orgManage-117角色，仅查询本机构数据；finance-23，显示全部角色
            String orgManage = apiReq.getString("orgManage");
            String finance = apiReq.getString("finance");
            if("true".equals(orgManage) && !"true".equals(finance)){
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
                if(userInfo!=null){
                    if(userInfo.getOrgId() !=null){
                        int typeId =  apiReq.getInt("productTypeId");
                        OrgInfo orgInfo = orgInfoMapper.selectByPrimaryKey(userInfo.getOrgId());
                        map.clear();
                        map.put("orgId",orgInfo.getId());
                        map.put("orgParentid",typeId);
                        orgInfoList = orgInfoMapper.queryOrgList(map);
                    }else{
                        orgInfoList = new ArrayList<>();
                    }
                }
            }

            //根据当前用户 查询 用户对应的 机构列表  机构权限表
            List<BusApplyOrgRole> orgRoles = busApplyOrgRoleMapper.listByUserId(userId);

            if(auth != null){
                orgInfoList = auth(orgInfoList,orgRoles,"org");
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,orgInfoList);
        }
        //根据“机构”，查询“类目”
        else if("1100".equals(btnCode)){
            map.put("organId",apiReq.getLong("organId"));
            List<StaffOrganProduct> staffOrganProductList = staffOrganProductMapper.listByParam(map);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,staffOrganProductList);
        }
        //根据“类目”，查询“项目”
        else if("1200".equals(btnCode)){
           // CommonEnum commonEnum = commonEnumMapper.selectByPrimaryKey(apiReq.getLong("billingEnum"));
            map.put("billingEnumId",apiReq.getLong("billingEnum"));
            List<BillingApplyEnumItem> enumItems = billingApplyEnumItemMapper.list(map);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,enumItems);
        }
        //查询批量开票的所有金额
        else if("1300".equals(btnCode)) {
            Double billingMoney = 0D;
            String surveyNo = "";
            Map<String ,Object> paramMap  =  new HashMap<>();
            String ids = apiReq.getString("ids");
            if(ids!=null) {
                List<String> aIds = JSONArray.parseArray(apiReq.getString("ids"), String.class);
                billingMoney = surveyRiskCaseInfoMapper.selectBillingMoney(aIds);
                surveyNo = SerialNumberUtil.getSurveyCode("CWTPL");
                paramMap.put("billingMoney",billingMoney);
                paramMap.put("surveyNo",surveyNo);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,paramMap);
        }else if ("13001".equals(btnCode)){
            Double billingMoney = 0D;
            String surveyNo = "";
            Map<String ,Object> paramMap  =  new HashMap<>();
            String ids = apiReq.getString("ids");
            if(ids!=null) {
                List<String> aIds = JSONArray.parseArray(apiReq.getString("ids"), String.class);
                billingMoney = finaSettlementInfoMapper.selectServiceMoney(aIds);
                surveyNo = SerialNumberUtil.getSurveyCode("DFPL");
                paramMap.put("billingMoney",billingMoney);
                paramMap.put("surveyNo",surveyNo);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,paramMap);
        }
        return null;
    }


    /**
     * @param t      需要过滤的列表
     * @param d      权限对应的列表
     * @param searchType    过滤类型  产品类型product  业务来源org  公司corp  产品enum  项目item
     * @param <T>
     * @param <D>
     * @return      权限过滤后的列表
     */
    public <T,D> T auth(T t,D d,String searchType){
        try {
            if ("product".equals(searchType)){
                List<BillingApplyProductType> list = (List<BillingApplyProductType>)t;
                List<BusApplyProductRole> auth = (List<BusApplyProductRole>)d;
                List<BillingApplyProductType> newList = list.getClass().newInstance();
                for (BusApplyProductRole proRole : auth) {
                    if (proRole.getProductId() == null){
                        continue;
                    }
                    for (BillingApplyProductType item : list) {
                        if (proRole.getProductId().intValue() == item.getId().intValue()){
                            if (newList.contains(item)) {
                                continue;
                            }
                            newList.add(item);
                        }
                    }
                }
                return (T)newList;
            }else if ("org".equals(searchType)){
                List<OrgInfo> list = (List<OrgInfo>)t;
                List<BusApplyOrgRole> auth = (List<BusApplyOrgRole>)d;
                List<OrgInfo> newList = list.getClass().newInstance();
                for (BusApplyOrgRole orgByauth : auth) {
                    if (orgByauth.getOrgId() == null){
                        continue;
                    }
                    for (OrgInfo item : list) {
                        if (orgByauth.getOrgId().intValue() == item.getId().intValue()){
                            if (newList.contains(item)) {
                                continue;
                            }
                            newList.add(item);
                        }
                    }
                }
                return (T)newList;
            }else if ("corp".equals(searchType)){
                List<BillingApplyCorporation> list = (List<BillingApplyCorporation>)t;
                List<BusApplyCorporationRole> auth = (List<BusApplyCorporationRole>)d;
                List<BillingApplyCorporation> newList = list.getClass().newInstance();
                for (BusApplyCorporationRole itemRole : auth) {
                    if (itemRole.getCorporationId() == null){
                        continue;
                    }
                    for (BillingApplyCorporation item : list) {
                        if (item.getId() == null){
                            continue;
                        }
                        if (itemRole.getCorporationId().intValue() == item.getId().intValue()){
                            if (newList.contains(item)) {
                                continue;
                            }
                            newList.add(item);
                        }
                    }
                }
                return (T)newList;
            }else if ("enum".equals(searchType)){
                List<BillingApplyCorporationEnum> list = (List<BillingApplyCorporationEnum>)t;
                List<BusApplyEnumRole> auth = (List<BusApplyEnumRole>)d;
                List<BillingApplyCorporationEnum> newList = list.getClass().newInstance();
                for (BusApplyEnumRole enumRole : auth) {
                    if (enumRole.getEnumId() == null){
                        continue;
                    }
                    for (BillingApplyCorporationEnum item : list) {
                        if (item.getBillingEnumId() == null){
                            continue;
                        }
                        if (enumRole.getEnumId().intValue() == item.getBillingEnumId().intValue()){
                            if (newList.contains(item)) {
                                continue;
                            }
                            newList.add(item);
                        }
                    }
                }
                return (T)newList;
            }else if ("item".equals(searchType)){
                List<BillingApplyEnumItem> list = (List<BillingApplyEnumItem>)t;
                List<BusApplyItemRole> auth = (List<BusApplyItemRole>)d;
                List<BillingApplyEnumItem> newList = list.getClass().newInstance();
                for (BusApplyItemRole itemRole : auth) {
                    if (itemRole.getItemId() == null){
                        continue;
                    }
                    for (BillingApplyEnumItem item : list) {
                        if (item.getBillingItemId() == null){
                            continue;
                        }
                        if (itemRole.getItemId().intValue() == item.getBillingItemId().intValue()){
                            if (newList.contains(item)) {
                                continue;
                            }
                            newList.add(item);
                        }
                    }
                }
                return (T)newList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
