package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyTaskInfoApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyUserClockApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.SurveyUserClockDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

/**
 *
 * @author wangwei
 * @date 2018/12/17
 * 调查员打卡API
 */
@Service
@ApiService(descript = "调查员打卡API")
public class BackendSurveyUserClockApiImpl extends BaseServiceImpl implements BackendSurveyUserClockApi {

    @Autowired
    private BusUserRoleMapper busUserRoleMapper;

    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;

    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;

    @Autowired
    private SurveyUserClockMapper surveyUserClockMapper;

    @Autowired
    private SurveyClockCaseMapper surveyClockCaseMapper;

    @Autowired
    private SurveyClockReInfoMapper surveyClockReInfoMapper;
    @Autowired
    private SurveyPreReimbursementMapper surveyPreReimbursementMapper;
    @Autowired
    private SurveyInvestigatorReInfoMapper surveyInvestigatorReInfoMapper;

    @ApiMethod(needLogin = false,descript = "获取机构信息",value = "get-data-survey-user-org")
    public ApiResponse selectUserOrg(ApiRequest apiRequest){
        Long currentUserId = getCurrentUserId(apiRequest);
        Map map=new HashMap();
        Map<String,Object> paramMap=new HashMap<>();
        Map<String,Object> dataRoleMap = getDataRole(currentUserId);
        if(dataRoleMap == null){
            return new ApiResponse(ApiMsgEnum.SURVEY_AUTH_NOT);
        }
        SurveyInvestigator surveyInvestigator=surveyInvestigatorMapper.selectByUserId(currentUserId);
        //获取  平台数据manager 省级数据provincialManger  片区数据areaManger 的编码
        String dataRoleCode = dataRoleMap.get("dataRoleCode") != null ? dataRoleMap.get("dataRoleCode").toString() : "";
        List<SurveyFranchisee> surveyFranchiseeList=null;
        String menuCode=apiRequest.getString("menuCode");
        if("userClock".equals(menuCode)){
            paramMap.put("parentIdJudge",0);
//            paramMap.put("category",0);
        }
        if("manger".equals(dataRoleCode)){
            surveyFranchiseeList=surveyFranchiseeMapper.list(paramMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyFranchiseeList);

        }else if("provincialManger".equals(dataRoleCode)){
            if(surveyInvestigator != null){
                paramMap.put("orgId",surveyInvestigator.getOrgId());
                surveyFranchiseeList=surveyFranchiseeMapper.selectSurveyFranchisee(paramMap);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyFranchiseeList);
            }
        }else if("areaManger".equals(dataRoleCode)){
            if(surveyInvestigator != null){
                paramMap.put("parentIdJudge",null);
                paramMap.put("orgId",surveyInvestigator.getOrgId());
//                surveyFranchiseeList=surveyFranchiseeMapper.selectSurveyFranchisee(paramMap);
                SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyInvestigator.getOrgId());
                surveyFranchiseeList = new ArrayList<>();
                surveyFranchiseeList.add(surveyFranchisee);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyFranchiseeList);
            }
        }

        return null;
    }

    private Map<String,Object> getDataRole(Long currentUserId){
        Map<String,Object> map =  new HashMap<String,Object>();
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean orgManger = isRoleUser(userRoles,104L), lfManger = isRoleUser(userRoles,95L),generalManger = isRoleUser(userRoles,75L),
                rsRoleUser = isRoleUser(userRoles,100L),forgManger = isRoleUser(userRoles,114L),orgReviewManger = isRoleUser(userRoles,58L),
                provincialManger = isRoleUser(userRoles,98L),personneManger = isRoleUser(userRoles,107L),areaManger = isRoleUser(userRoles,116L);
        if (lfManger || rsRoleUser || generalManger || personneManger){
            map.put("dataRoleCode","manger");
            return map;
        }
        if (orgManger || forgManger || provincialManger || orgReviewManger){
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
        if (areaManger){
            map.put("dataRoleCode","areaManger");
            return map;
        }
        return null;
    }


    //角色判断
    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId() .equals(roleId) ){
                return true;
            }
        }
        return false;
    }

    @ApiMethod(needLogin = false,descript = "获取调查员信息",value = "get-data-survey-user-getDetail")
    public ApiResponse userGetDetail(ApiRequest apiRequest){
        String btnCode=apiRequest.getString("btnCode");
        Map paramMap=new HashMap();
        Map map=new HashMap();
        if("Investigator".equals(btnCode)){//获取机构下人员信息
            Long currentUserId = getCurrentUserId(apiRequest);
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean orgManger = isRoleUser(userRoles,116L),provincialManger = isRoleUser(userRoles,98L);
            if (orgManger && !provincialManger){//片区机构负责人
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(currentUserId);
                if (surveyInvestigator != null){
                    paramMap.put("surveyAreaId",surveyInvestigator.getSurveyAreaId());
                }
            }
            Integer orgId=apiRequest.getInt("orgId");
            paramMap.put("orgId",orgId);
            List<SurveyInvestigator> surveyInvestigatorList=surveyInvestigatorMapper.selectByMapJurisdiction(paramMap);
            map.put("surveyInvestigatorList",surveyInvestigatorList);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
        } else if("punchInDate".equals(btnCode)) {//获取人员打卡信息
            Integer userId=apiRequest.getInt("userId");
            paramMap.put("userId",userId);
            Integer orgId=apiRequest.getInt("orgId");
            paramMap.put("orgId",orgId);
            String dateTime= apiRequest.getString("dateTime");
            paramMap.put("dateTime",dateTime);
            List<SurveyUserClockDto> surveyUserClockDtoList=surveyUserClockMapper.selectPunchTheClock(paramMap);
            String previousMonth=DateUtils.getFirstMonth(dateTime,-1);
            paramMap.put("dateTime",previousMonth);
            List<SurveyUserClockDto> previousList=surveyUserClockMapper.selectPunchTheClock(paramMap);
            String nextNonth=DateUtils.getFirstMonth(dateTime,1);
            paramMap.put("dateTime",nextNonth);
            List<SurveyUserClockDto> nextList=surveyUserClockMapper.selectPunchTheClock(paramMap);
            map.put("surveyUserClockDtoList",surveyUserClockDtoList);
            map.put("previousList",previousList);
            map.put("nextList",nextList);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
        }
        return null;
    }

    @ApiMethod(needLogin = false,descript = "获取调查员打卡信息",value = "get-data-survey-user-org-getDetail")
    public ApiResponse getDetail(ApiRequest apiRequest){
        String btnCode=apiRequest.getString("btnCode");
        if("clockInDetails".equals(btnCode)){//打卡详情
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(getCurrentUserId(apiRequest));
            Boolean orgRole = isRoleUser(userRoles, 104L);
//            if (StringUtils.isBlank(apiRequest.getString("orgId"))){
//                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(getCurrentUserId(apiRequest));
//                if (surveyInvestigator != null){
//                    apiRequest.put("orgId",surveyInvestigator.getOrgId());
//                }
//            }
            //平台角色。（财务部23，平台全案查看95，人事主管角色（人事管理）107，测试角色28，人事角色100，狄大人平台总经理）75

            List<SurveyUserClockDto> list=surveyUserClockMapper.selectByMap(apiRequest);
            int index = 1;
            for (int i = 0; i < list.size(); i++) {
                SurveyUserClockDto surveyUserClockDto = list.get(i);
                SurveyClockReInfo surveyClockReInfo=surveyClockReInfoMapper.selectByClockId(surveyUserClockDto.getId());
                if (surveyClockReInfo != null) {
                    surveyClockReInfo.setClockCaseList(surveyClockCaseMapper.selectByClocId(surveyUserClockDto.getId()));//案件关联表
                }
                surveyUserClockDto.setSurveyClockReInfo(surveyClockReInfo);
                surveyUserClockDto.setSurveyCostApplyDto(surveyClockReInfoMapper.selectCostApplyByClockId(surveyUserClockDto.getId()));
                surveyUserClockDto.setClockCaseList(surveyClockCaseMapper.selectByClocId(surveyUserClockDto.getId()));
                if (surveyClockReInfo != null){
                    double cityMoney = Optional.ofNullable(surveyClockReInfo.getCityinDrivingMoney()).orElse(0d);
                    if (surveyClockReInfo.getReferenceMoney() == null || surveyClockReInfo.getReferenceMoney() == 0){
                        surveyUserClockDto.setRed(false);
                    }else {
                        double refMoney = surveyClockReInfo.getReferenceMoney();
                        if (cityMoney > refMoney * 1.5) {
                            surveyUserClockDto.setRed(true);
                        } else {
                            surveyUserClockDto.setRed(false);
                        }
                    }
                    if (orgRole){//机构才可以修改
                        surveyUserClockDto.setEdit(true);
                        //此打卡是否有报销或者预报销
                        SurveyPreReimbursement surveyPreReimbursement = surveyPreReimbursementMapper.selectByUserId(surveyUserClockDto.getSurveyUserId(),DateUtils.DateToStr(surveyUserClockDto.getClockTime(),"yyyy-MM"),surveyUserClockDto.getSurveyOrgId());
                        if (surveyPreReimbursement != null){
                            if (surveyPreReimbursement.getState() == 2 || surveyPreReimbursement.getState() == 3){
                                surveyUserClockDto.setEdit(false);
                            }
                        }else {
                            String reInfoDate = DateUtils.dateToLocalDate(surveyUserClockDto.getClockTime()).plusMonths(1).toString().substring(0,7);
                            SurveyInvestigatorReInfo surveyInvestigatorReInfo = surveyInvestigatorReInfoMapper.selectReInfoSurveyUserId(surveyUserClockDto.getSurveyUserId(),reInfoDate,surveyUserClockDto.getSurveyOrgId());
                            if (surveyInvestigatorReInfo != null){
                                if (surveyInvestigatorReInfo.getReState() >= 3 && surveyInvestigatorReInfo.getReState() <= 7){
                                    surveyUserClockDto.setEdit(false);
                                }
                            }
                        }
                    }else {
                        surveyUserClockDto.setEdit(false);
                    }
                }else {
                    surveyUserClockDto.setRed(false);
                }

                if (i == 0) {
                    list.get(i).setSameDaySort(1);
                } else {
                    Date thisInfoDate = list.get(i).getClockTime();
                    Date lastInfoDate = list.get(i - 1).getClockTime();
                    GregorianCalendar thisInfoca = new GregorianCalendar();
                    GregorianCalendar lastInfoca = new GregorianCalendar();
                    thisInfoca.setTime(thisInfoDate);
                    lastInfoca.setTime(lastInfoDate);
                    //获取thisInfoca和lastInfoca的年，月，日，对比是否相同
                    if (thisInfoca.get(GregorianCalendar.YEAR) == lastInfoca.get(GregorianCalendar.YEAR) &&
                            thisInfoca.get(GregorianCalendar.MONTH) == lastInfoca.get(GregorianCalendar.MONTH) &&
                            thisInfoca.get(GregorianCalendar.DAY_OF_MONTH) == lastInfoca.get(GregorianCalendar.DAY_OF_MONTH))
                    {
                        list.get(i).setSameDaySort(index);
                    } else {
                        list.get(i).setSameDaySort(1);
                        index = 1;//跨天之后index清空为0
                    }
                }
                index++;
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
        }else if("reimbursementDetails".equals(btnCode)){//费用报销详情
            List<SurveyClockCase> list=surveyClockCaseMapper.selectByClocId(apiRequest.getLong("clockId"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
        }else if("caseClockDetails".equals(btnCode)){//获取案件打卡足迹
            Integer surveyInfoId =apiRequest.getInt("id");
            List<SurveyUserClockDto> list=surveyUserClockMapper.selectCaseClockDetails(surveyInfoId);
            for (SurveyUserClockDto userClock : list) {
                SurveyClockCase surveyClockCase = surveyClockCaseMapper.selectByPrimaryKey(userClock.getClockCaseId());
                if (surveyClockCase != null){
                    //关联出本次打卡的备注
                    SurveyClockReInfo surveyClockReInfo = surveyClockReInfoMapper.selectByClockId(surveyClockCase.getClockId());
                    surveyClockCase.setClockDesc(surveyClockReInfo.getClockDesc());
                }
                userClock.setSurveyClockCase(surveyClockCase);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
        }
        return null;
    }
}
