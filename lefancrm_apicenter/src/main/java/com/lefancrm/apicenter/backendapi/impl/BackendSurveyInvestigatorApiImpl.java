package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyConsignerApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyInvestigatorApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.SurveyInvestigatorCaseDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import com.timevale.tgtext.text.xml.xmp.h;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wangwei on 2018/12/17.
 * 调查方认证
 */
@Service
@ApiService(descript = "调查方认证API")
public class BackendSurveyInvestigatorApiImpl extends BaseServiceImpl implements BackendSurveyInvestigatorApi {

    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BusinessRoleMapper businessRoleMapper;
    @Autowired
    private SurveyAssignOrgMapper surveyAssignOrgMapper;
    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;
    @Autowired
    private SurveyLfcoinDetailMapper surveyLfcoinDetailMapper;
    @Autowired
    private SurveyAchieveDetailMapper surveyAchieveDetailMapper;
    @Autowired
    private SurveyLevelMapper surveyLevelMapper;
    @Autowired
    private SurveyRiskInfoFinalUserMapper surveyRiskInfoFinalUserMapper;
    @Autowired
    private SurveyConsignorModelMapper surveyConsignorModelMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;
    @Autowired
    private SurveyInvestigatorCaseMapper surveyInvestigatorCaseMapper;
    @Autowired
    private SurveyTeachRewardListMapper surveyTeachRewardListMapper;
    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;
    @Autowired
    private SurveyOrgAreaMapper surveyOrgAreaMapper;

    /**
     * 调查方认证 list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "调查方认证 list", value = "backend-survey-investigator-list", apiParams = { })
    @Override
    public ApiResponse list(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
        int count = surveyInvestigatorMapper.listSize(apiReq);
        List<SurveyInvestigator> list = surveyInvestigatorMapper.list(apiReq);
        return new ApiResponse<List<SurveyInvestigator>>(ApiMsgEnum.SUCCESS, count, list);

    }

    /**
     * 查询所有的调查员
     */
    @ApiMethod(descript = "查询所有的调查员", value = "backend-survey-investigator-alllist")
    @SuppressWarnings("rawtypes")
    public ApiResponse alllist(ApiRequest apiReq) {
        List<SurveyInvestigator> list = surveyInvestigatorMapper.list(apiReq);
        return new ApiResponse<List<SurveyInvestigator>>(ApiMsgEnum.SUCCESS, 1, list);
    }

    /**
     * 根据条件查询调查员
     */
    @ApiMethod(descript = "根据条件查询调查员", value = "backend-survey-investigator-selectByUser")
    @SuppressWarnings("rawtypes")
    public ApiResponse selectByUser(ApiRequest apiReq){
        Map map=new HashMap();
        String homePage=apiReq.getString("homePage");
        if(homePage != null && !homePage.equals("")){
            map.put("homePage",homePage);
            Integer userId=apiReq.getInt("currentUserId");
            map.put("userId",userId);
        }
        SurveyInvestigator surveyInvestigator=surveyInvestigatorMapper.selectByUser(map);
        return new ApiResponse<SurveyInvestigator>(ApiMsgEnum.SUCCESS, 1, surveyInvestigator);
    }

    /**
     * 更新 调查方认证
     */
    @ApiMethod(descript = "更新 调查方认证", value = "backend-survey-investigator-operate")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse operate(ApiRequest apiReq) {

        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 机构下的所有调查员
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "机构下的所有调查员list", value = "backend-survey-investigator-by-org", apiParams = { })
    @Override
    public ApiResponse listByOrg(ApiRequest apiReq) {
        Long surveyInfoId = apiReq.getLong("surveyInfoId");
        Long orgId = apiReq.getLong("orgId");
        //查询机构 和 案件是否有主调查员
        Map<String,Object> map =  new HashMap<String,Object>();
        map.put("surveyOrgId",orgId);
        map.put("surveyInfoId",surveyInfoId);
        SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map);
        Map<String,Object> resultMap =  new HashMap<String,Object>();
        if (surveyAssignOrg == null || surveyAssignOrg.getSurveyInvestigatorCaseId() == null){
            resultMap.put("isSurveyPrimary",true);
        }else {
            resultMap.put("isSurveyPrimary",false);
        }
        resultMap.put("surveyAssignOrg",surveyAssignOrg);
//        map = new HashMap<>();
//        map.put("orgId",orgId);
//        List<SurveyInvestigator> surveyInvestigator = surveyInvestigatorMapper.list(map);
//        //仅获取调查员角色
//        List<SurveyInvestigator> list = new ArrayList();
//        for (int i = 0; i < surveyInvestigator.size(); i++) {
//            String roles = businessRoleMapper.selectInvestigatorRoles(surveyInvestigator.get(i).getUserId());
//            if (roles != null) {
//                String[] role = roles.split(",");
//                for (String ro : role) {
//                    if ("50".equals(ro)) {
//                        list.add(surveyInvestigator.get(i));
//                    }
//                }
//            }
//        }

//        apiReq.put("orgId",orgId);
//        apiReq.put("roleId",50);
//        List<SurveyInvestigator> list = surveyInvestigatorMapper.selectInfoByRole(apiReq);
        SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(orgId);
        if(surveyFranchisee!=null){
            if(surveyFranchisee.getLevel() !=1){
                surveyFranchisee = surveyFranchiseeMapper.selectByParentId(surveyFranchisee.getParentId());
                if(surveyFranchisee!=null){
                    orgId = surveyFranchisee.getId();
                }
            }
        }
        List<SurveyInvestigator> list = surveyInvestigatorMapper.selectInfoAndCaseNum(orgId);
        resultMap.put("list",list);

        //查询终审人员列表
        List<UserInfo> oprUsers = new ArrayList<UserInfo>();

        //默认列表的终审人员列表 （1.当前案件所存在的终审人员 2.根据委托方机构 调查方机构匹配出来的终审人员）
        SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInfoId);
        List<UserInfo> defaultUsers = new ArrayList<UserInfo>();
        if (surveyRiskCaseInfo.getBelongUserId() != null){//当前案件所存在的终审人员
            defaultUsers.add(userInfoMapper.selectByPrimaryKey(surveyRiskCaseInfo.getBelongUserId()));
        }else{
            //根据委托方机构 调查方机构匹配出来的终审人员
            map = new HashMap<>();
            map.put("entrustOrgId",surveyRiskCaseInfo.getEntrustOrgId());
            //查询该案件的主机构ID
            SurveyAssignOrg primaryOrg = surveyAssignOrgMapper.selectPrimaryOrg(surveyRiskCaseInfo.getId());
            if (primaryOrg != null) {
                orgId = primaryOrg.getSurveyOrgId();
            }
            map.put("surveyOrgId",orgId);//主机构的ID
            List<UserInfo> all = userInfoMapper.selectUserBySurveyOrg(map);
            defaultUsers.addAll(all);

            //2020年2月21日15:22:42 需求：互助案件：默认展示的终审人员是一次循环
//            SurveyConsignorModel surveyConsignorModel = surveyConsignorModelMapper.selectByConsignorId(surveyRiskCaseInfo.getEntrustOrgId());
//            if(surveyConsignorModel != null && surveyConsignorModel.getModelId() ==5){
                map = new HashMap<>();
                map.put("consignorId",surveyRiskCaseInfo.getEntrustOrgId());
                map.put("franchiseeId",orgId);
                SurveyRiskInfoFinalUser  surveyRiskInfoFinalUser = surveyRiskInfoFinalUserMapper.selectByInfo(map);
                UserInfo nextFinalUser = new UserInfo();
                if(surveyRiskInfoFinalUser != null){
                    for (int i = 0; i < all.size(); i++) {
                        if(surveyRiskInfoFinalUser.getUserId().intValue() == all.get(i).getUserId().intValue()){
                            if(i == all.size() - 1){
                                nextFinalUser =  all.get(0);
                                defaultUsers.remove(all.get(0));
                            }else{
                                nextFinalUser = all.get(i+1);
                                defaultUsers.remove(all.get(i+1));
                            }
                            break;
                        }else{
                            nextFinalUser =  all.get(0);
                        }
                    }
                    resultMap.put("nextFinalUser",nextFinalUser);
                }
//            }
        }

        //所有终审人员列表(查询某些角色，并剔除另外的角色)
        map = new HashMap<>();
        map.put("needId",53L);
        map.put("noNeedId",118L);
        List<UserInfo> users = userInfoMapper.selectUserInfoByRoles(map);
//        List<UserInfo> users = userInfoMapper.selectUserByRoleId(53L);
        List<UserInfo> tempUsers = new ArrayList<>();
        //所有终审人员 不包括已经匹配出来的人员列表
        for (UserInfo user : users) {
            Boolean have = false;
            for (UserInfo defaultUser : defaultUsers) {
                if (user.getUserId().intValue() == defaultUser.getUserId().intValue()){//说明当前 user  存在默认里边
                    have = true;
                    break;
                }
            }
            if (!have){
                tempUsers.add(user);
            }
        }
        oprUsers.addAll(defaultUsers);
        oprUsers.addAll(tempUsers);
        resultMap.put("deepCasesPrice",null);
        SurveyConsignor surveyConsignor=surveyConsignorMapper.selectByPrimaryKey(surveyRiskCaseInfo.getEntrustOrgId());
        if(surveyConsignor.getOrgAttr() == 1){ // 判断是否是保司案子
            if(surveyRiskCaseInfo.getServicesId() == 13){ //判断案子类型是否是深度
                if(true){ // 判断保司调查方类别（1.直营，2，合伙，3.合作）   直营 非 直营 深度案件都取价格 。  0909版本需求
                    resultMap.put("deepCasesPrice",surveyFranchisee.getDeepCasesPrice());
                }
            }
        }

        resultMap.put("oprUsers",oprUsers);
        return new ApiResponse<Map>(ApiMsgEnum.SUCCESS,resultMap.size(),resultMap);
    }


    /**
     * 增加乐凡币 和 成就点
     * @param surveyUserId  调查员userId
     * @param num   增加数量
     * @param type  增加类型(1乐凡币   2成就点  3乐凡币成就点都增加)
     * @param remark 增加描述
     * @param oprId     业务id
     * @param oprType   业务类型(1.认证，2.业务案件，3.论坛.4.案件评星，5.兑换)
     * @return
     */
    @Override
    public void addPrice(Long surveyUserId, Long num, int type, String remark, Long oprId, int oprType) {
        SurveyInvestigator investigator = surveyInvestigatorMapper.selectByUserId(surveyUserId);
        if (num == 0L || investigator == null){
            return ;
        }
        if (investigator != null){
            if (type == 1){
                //增加乐凡币
                Long lefanCurrency = investigator.getLefanCurrency() == null ? 0L : investigator.getLefanCurrency();
                investigator.setLefanCurrency(lefanCurrency + num);
                surveyInvestigatorMapper.updateByPrimaryKey(investigator);
                //增加乐凡币明细
                SurveyLfcoinDetail surveyLfcoinDetail = new SurveyLfcoinDetail();
                surveyLfcoinDetail.setLefanCoin(num);
                surveyLfcoinDetail.setConsumeType(1);
                surveyLfcoinDetail.setSurveyUserId(investigator.getUserId());
                surveyLfcoinDetail.setSurveyUserName(investigator.getRealName());
                surveyLfcoinDetail.setRemark(remark);
                surveyLfcoinDetail.setCreateTime(new Date());
                surveyLfcoinDetail.setOprId(oprId);
                surveyLfcoinDetail.setOprType(oprType);
                surveyLfcoinDetailMapper.insert(surveyLfcoinDetail);
            }else if (type == 2){
                //增加成就点
                Long achPoint = investigator.getAchPoint() == null ? 0L : investigator.getAchPoint();
                investigator.setAchPoint(achPoint + num);
                surveyInvestigatorMapper.updateByPrimaryKey(investigator);
                //增加成就点明细
                SurveyAchieveDetail surveyAchieveDetail = new SurveyAchieveDetail();
                surveyAchieveDetail.setAchNum(num);
                surveyAchieveDetail.setAchRemark(remark);
                surveyAchieveDetail.setSurveyUserId(investigator.getUserId());
                surveyAchieveDetail.setSurveyUserName(investigator.getRealName());
                surveyAchieveDetail.setCreateTime(new Date());
                surveyAchieveDetail.setOprId(oprId);
                surveyAchieveDetail.setOprType(oprType);
                surveyAchieveDetailMapper.insert(surveyAchieveDetail);
            }else if (type == 3){
                //增加乐凡币
                Long lefanCurrency = investigator.getLefanCurrency() == null ? 0L : investigator.getLefanCurrency();
                investigator.setLefanCurrency(lefanCurrency + num);
                surveyInvestigatorMapper.updateByPrimaryKey(investigator);
                //增加乐凡币明细
                SurveyLfcoinDetail surveyLfcoinDetail = new SurveyLfcoinDetail();
                surveyLfcoinDetail.setLefanCoin(num);
                surveyLfcoinDetail.setConsumeType(1);
                surveyLfcoinDetail.setSurveyUserId(investigator.getUserId());
                surveyLfcoinDetail.setSurveyUserName(investigator.getRealName());
                surveyLfcoinDetail.setRemark(remark);
                surveyLfcoinDetail.setCreateTime(new Date());
                surveyLfcoinDetail.setOprId(oprId);
                surveyLfcoinDetail.setOprType(oprType);
                surveyLfcoinDetailMapper.insert(surveyLfcoinDetail);
                //增加成就点
                Long achPoint = investigator.getAchPoint() == null ? 0L : investigator.getAchPoint();
                investigator.setAchPoint(achPoint + num);
                surveyInvestigatorMapper.updateByPrimaryKey(investigator);
                //增加成就点明细
                SurveyAchieveDetail surveyAchieveDetail = new SurveyAchieveDetail();
                surveyAchieveDetail.setAchNum(num);
                surveyAchieveDetail.setAchRemark(remark);
                surveyAchieveDetail.setSurveyUserId(investigator.getUserId());
                surveyAchieveDetail.setSurveyUserName(investigator.getRealName());
                surveyAchieveDetail.setCreateTime(new Date());
                surveyAchieveDetail.setOprId(oprId);
                surveyAchieveDetail.setOprType(oprType);
                surveyAchieveDetailMapper.insert(surveyAchieveDetail);
            }

            //升级调查员称号
            SurveyLevel surveyLevel = surveyLevelMapper.selectByPoint(investigator.getAchPoint());//小于等于当前调查员的成就点 升序 取第一条
            if (surveyLevel != null) {
                investigator.setTitleId(surveyLevel.getId());
                investigator.setTitleName(surveyLevel.getName());
                surveyInvestigatorMapper.updateByPrimaryKey(investigator);
            }
        }
    }

    /**
     * 查询所有的新人
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "查询所有的新人", value = "backend-survey-investigator-selectRookieList", apiParams = { })
    @SuppressWarnings("rawtypes")
    public ApiResponse selectRookieList(ApiRequest apiReq){
        String startTargetTime=apiReq.getString("startTargetTime");
        String endTargetTime=apiReq.getString("endTargetTime");
        //查询新人数据
        List<SurveyInvestigator> list = surveyInvestigatorMapper.selectRookieList(apiReq);
        //查询所有人的积分
        List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseDtoList=surveyInvestigatorCaseMapper.selectAllScore(apiReq);
        for (SurveyInvestigator surveyInvestigator:list) {
            if(surveyInvestigator.getQualifiedEnfdTime()==null){
                String createTime=DateUtils.DateToStr(surveyInvestigator.getCreateTime(),"yyyy-MM-dd HH:mm:ss");
                String laterTime=DateUtils.getFirstDays(createTime,+28);
                surveyInvestigator.setQualifiedEnfdTime(DateUtils.strToDate(laterTime));
            }
            Double integral=0D;
            for (SurveyInvestigatorCaseDto surveyInvestigatorCaseDto:surveyInvestigatorCaseDtoList) {
                if(surveyInvestigator.getUserId().equals(surveyInvestigatorCaseDto.getSurveyUserId())){
                    //计算每个新人的积分总和
                    integral=integral+surveyInvestigatorCaseDto.getScore();
                    if(integral>=50){
                        surveyInvestigator.setLAY_CHECKED(true);
                        surveyInvestigator.setRealQualifiedTime(surveyInvestigatorCaseDto.getReviewTime());
                        Long day=DateUtils.getDaySub(surveyInvestigator.getCreateTime(),surveyInvestigatorCaseDto.getReviewTime());
                        surveyInvestigator.setEffecDays(Integer.parseInt(day.toString()));
                        if(day>28){
                            surveyInvestigator.setTeacherReward(0D);
                        }else if(day>21&&day<=28){
                            surveyInvestigator.setTeacherReward(600D);
                        }else if(day>14&&day<=21){
                            surveyInvestigator.setTeacherReward(900D);
                        }else if(day<=14){
                            surveyInvestigator.setTeacherReward(1200D);
                        }
                        break;
                    }
                }
            }
            surveyInvestigator.setScore(integral);
            if(surveyInvestigator.getTeacherRealReward() == null){
                surveyInvestigator.setTeacherRealReward(surveyInvestigator.getTeacherReward());
            }
        }

        Iterator<SurveyInvestigator> iterator = list.iterator();
        if(startTargetTime != null && endTargetTime != null ){
            startTargetTime=startTargetTime+" 00:00:00";
            endTargetTime=endTargetTime+" 23:59:59";
            Date strStartTargetTime=DateUtils.strToDate(startTargetTime);
            Date strEndTargetTime=DateUtils.strToDate(endTargetTime);
            while (iterator.hasNext()) {
                SurveyInvestigator surveyInvestigator = iterator.next();
                if (surveyInvestigator.getRealQualifiedTime() != null) {
                    Boolean flag=DateUtils.belongCalendar(surveyInvestigator.getRealQualifiedTime(),strStartTargetTime,strEndTargetTime);
                    if(!flag){
                        iterator.remove();//使用迭代器的删除方法删除
                    }
                }else{
                    iterator.remove();//使用迭代器的删除方法删除
                }
            }
        }else if(startTargetTime != null){
            startTargetTime=startTargetTime+" 00:00:00";
            Date strStartTargetTime=DateUtils.strToDate(startTargetTime);
            while (iterator.hasNext()) {
                SurveyInvestigator surveyInvestigator = iterator.next();
                if (surveyInvestigator.getRealQualifiedTime() != null) {
                    Boolean flag=surveyInvestigator.getRealQualifiedTime().before(strStartTargetTime);
                    if(flag){
                        iterator.remove();//使用迭代器的删除方法删除
                    }
                }else{
                    iterator.remove();//使用迭代器的删除方法删除
                }
            }
        } else if (endTargetTime != null ) {
            endTargetTime=endTargetTime+" 23:59:59";
            Date strEndTargetTime=DateUtils.strToDate(endTargetTime);
            while (iterator.hasNext()) {
                SurveyInvestigator surveyInvestigator = iterator.next();
                if (surveyInvestigator.getRealQualifiedTime() != null) {
                    Boolean flag=surveyInvestigator.getRealQualifiedTime().before(strEndTargetTime);
                    if(!flag){
                        iterator.remove();//使用迭代器的删除方法删除
                    }
                }else{
                    iterator.remove();//使用迭代器的删除方法删除
                }
            }
        }
        return new ApiResponse<List<SurveyInvestigator>>(ApiMsgEnum.SUCCESS, 1, list);
    }


    /**
     * 更新新人信息
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "更新新人信息", value = "backend-survey-investigator-updateRookie", apiParams = { })
    @SuppressWarnings("rawtypes")
    public ApiResponse updateRookie(ApiRequest apiReq){
        List<SurveyInvestigator> list=surveyInvestigatorMapper.list(apiReq);
        String btnCode=apiReq.getString("btnCode");
        Long currentUserId = getCurrentUserId(apiReq);
        UserInfo userInfo=userInfoMapper.selectByPrimaryKey(currentUserId);
        int count=0;
        if("updMoney".equals(btnCode)){
            for (SurveyInvestigator surveyInvestigator:list) {
                Double money=apiReq.getDouble("money");
                String rewardDesc=apiReq.getString("rewardDesc");
                if(money != null){
                    surveyInvestigator.setTeacherRealReward(money);
                }
                if(rewardDesc != null){
                    surveyInvestigator.setRewardDesc(rewardDesc);
                }
                surveyInvestigator.setOperateUserId(Integer.parseInt(userInfo.getUserId().toString()));
                surveyInvestigator.setOperateUserName(userInfo.getUserName());
                surveyInvestigator.setOperateTime(new Date());
                count=surveyInvestigatorMapper.updateByPrimaryKey(surveyInvestigator);

                Long surveyTeachRewardListid=apiReq.getLong("surveyTeachRewardListid");
                if(surveyTeachRewardListid != null){//根据前端传过来的带教清单id修改清单数据
                    Double sum=surveyInvestigatorMapper.selectSumTeacherRealReward(surveyTeachRewardListid);
                    SurveyTeachRewardList surveyTeachRewardList=surveyTeachRewardListMapper.selectByPrimaryKey(surveyTeachRewardListid);
                    surveyTeachRewardList.setTeacherReward(sum);
                    surveyTeachRewardList.setUpdateTime(new Date());
                    surveyTeachRewardList.setUpdateBy(userInfo.getUserName());
                    surveyTeachRewardListMapper.updateByPrimaryKey(surveyTeachRewardList);
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, count, null);
        }
        if("updisNewPeople".equals(btnCode)){
            for (SurveyInvestigator surveyInvestigator:list) {
                Map findMap=new HashMap();
                findMap.put("surveyUserId",surveyInvestigator.getUserId());
                //查询积分
                List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseDtoList=surveyInvestigatorCaseMapper.selectAllScore(findMap);
                Double integral=0D;
                for (SurveyInvestigatorCaseDto surveyInvestigatorCaseDto:surveyInvestigatorCaseDtoList) {
                    //计算新人的积分总和
                    integral=integral+surveyInvestigatorCaseDto.getScore();
                    if(integral>=50){
                        surveyInvestigator.setRealQualifiedTime(surveyInvestigatorCaseDto.getReviewTime());
                        Long day=DateUtils.getDaySub(surveyInvestigator.getCreateTime(),surveyInvestigatorCaseDto.getReviewTime());
                        surveyInvestigator.setEffecDays(Integer.parseInt(day.toString()));
                        if(day>28){
                            surveyInvestigator.setTeacherReward(0D);
                        }else if(day>21&&day<=28){
                            surveyInvestigator.setTeacherReward(600D);
                        }else if(day>14&&day<=21){
                            surveyInvestigator.setTeacherReward(900D);
                        }else if(day<=14){
                            surveyInvestigator.setTeacherReward(1200D);
                        }
                        break;
                    }
                    if(surveyInvestigator.getTeacherRealReward() == null){
                        surveyInvestigator.setTeacherRealReward(surveyInvestigator.getTeacherReward());
                    }
                }
                surveyInvestigator.setIsNewPeople(1);
                surveyInvestigator.setOperateUserId(Integer.parseInt(userInfo.getUserId().toString()));
                surveyInvestigator.setOperateUserName(userInfo.getUserName());
                surveyInvestigator.setOperateTime(new Date());
                if(surveyInvestigator.getQualifiedEnfdTime()==null){
                    String createTime=DateUtils.DateToStr(surveyInvestigator.getCreateTime(),"yyyy-MM-dd HH:mm:ss");
                    String laterTime=DateUtils.getFirstDays(createTime,+28);
                    surveyInvestigator.setQualifiedEnfdTime(DateUtils.strToDate(laterTime));
                }
                count=surveyInvestigatorMapper.updateByPrimaryKey(surveyInvestigator);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, count, null);
        }
       return  null;
    }


    /**
     * 根据权限查询调查员
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据权限查询调查员", value = "backend-survey-investigator-jurisdiction", apiParams = { })
    public ApiResponse jurisdiction(ApiRequest apiReq){
        Map findMap=new HashMap();
        findMap.put("roleId",113);
        List<SurveyInvestigator> surveyInvestigatorList=surveyInvestigatorMapper.selectBusinessRole(findMap);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyInvestigatorList);
    }


    /**
     * 修改调查员数据
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "修改调查员数据", value = "backend-survey-investigator-updateUser", apiParams = { })
    public ApiResponse updateUser(ApiRequest apiReq){
        String surveyCode=apiReq.getString("surveyCode");
        if("addAreaPersonnel".equals(surveyCode)){
            String investigatorIds=apiReq.getString("investigatorIds");
            Long areaId=apiReq.getLong("areaId");
            if(areaId != null){
                Map map=new HashMap();
                map.put("id",areaId);
                SurveyOrgArea surveyOrgArea=surveyOrgAreaMapper.selectOne(map);
                if(surveyOrgArea != null){
                    map=new HashMap();
                    map.put("ids",investigatorIds);
                    map.put("surveyAreaId",surveyOrgArea.getId());
                    map.put("surveyAreaName",surveyOrgArea.getSurveyAreaName());
                    surveyInvestigatorMapper.updateSurveyAreaId(map);
                    return new ApiResponse(ApiMsgEnum.SUCCESS,1,null);
                }
            }
        }else if("delAreaPersonnel".equals(surveyCode)){
            Long id=apiReq.getLong("id");
            SurveyInvestigator surveyInvestigator=surveyInvestigatorMapper.selectByPrimaryKey(id);
            if(surveyInvestigator!=null){
                surveyInvestigator.setSurveyAreaId(null);
                surveyInvestigator.setSurveyAreaName(null);
                surveyInvestigatorMapper.updateByPrimaryKey(surveyInvestigator);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,"成功");
            }else{
                return new ApiResponse(ApiMsgEnum.FAIL,0,"该调查员数据不存在");
            }
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
