package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendInvalidismEstimateApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ApiService(descript = "伤残等级预估")
public class BackendInvalidismEstimateApiImpl extends BaseServiceImpl implements BackendInvalidismEstimateApi {
	@Autowired
	private InvalidismEstimateMapper invalidismEstimateMapper;
    @Autowired
    private InvalidismEstimateReportMapper invalidismEstimateReportMapper;
    @Autowired
    private CaseFollowInfoMapper caseFollowInfoMapper;
    @Autowired
    private InvalidismEstimateFileMapper invalidismEstimateFileMapper;

    @Autowired
    private MessageInfoMapper messageInfoMapper;
    @Autowired
    private UserPromotedMapper userPromotedMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private PromotedInfoMapper promotedInfoMapper;

	@SuppressWarnings("rawtypes")
	@Override
	@ApiMethod(descript = "伤残等级预估列表", value = "backend-invalidism-list")
	public ApiResponse list(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
        List<InvalidismEstimate> invalidismEstimates=invalidismEstimateMapper.selectInvalidismEstimateList(apiReq);
        if(!invalidismEstimates.isEmpty()){
            for(InvalidismEstimate invalidismEstimate : invalidismEstimates){
                Long userId = invalidismEstimate.getUserId();
                String promotedName = getPromotedName(userId);
                invalidismEstimate.setPromoterName(promotedName);
            }
        }
        int count = invalidismEstimateMapper.selectInvalidismEstimateListCount(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS,invalidismEstimates==null?0:count,invalidismEstimates);
	}

    private String getPromotedName(Long userId){
        if(userId == null || userId == 0){
            return "无";
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userId",userId);
        UserPromoted userPromoted = userPromotedMapper.selectUserPromotedByUserId(paramMap);
        if(userPromoted == null){
            paramMap.clear();
            paramMap.put("customerId",userId);
            //去找推广人员
            PromotedInfo promotedInfo = promotedInfoMapper.selectPromotedInfoByParam(paramMap);
            if(promotedInfo == null){
                return "无";
            }
            userPromoted = userPromotedMapper.selectByPrimaryKey(promotedInfo.getPromoterId());
            if(userPromoted == null){
                return "无";
            }
            paramMap.clear();
            paramMap.put("userId",userPromoted.getUserId());
            paramMap.put("roleId",2);
            BusUserRole busUserRole = busUserRoleMapper.selectBusRoleInfo(paramMap);
            if(busUserRole != null){
                return userPromoted.getRealName();
            }
            UserPromoted userPromoted1 = userPromotedMapper.selectByPrimaryKey(userPromoted.getParentId());
            if(userPromoted1 == null){
                return "无";
            }
            return getPromotedName(userPromoted1.getUserId());
        }else {
            paramMap.clear();
            paramMap.put("userId",userId);
            paramMap.put("roleId",2);
            BusUserRole busUserRole = busUserRoleMapper.selectBusRoleInfo(paramMap);
            if(busUserRole != null){
                return userPromoted.getRealName();
            }
            UserPromoted userPromoted1 = userPromotedMapper.selectByPrimaryKey(userPromoted.getParentId());
            if(userPromoted1 == null){
                return "无";
            }
            return getPromotedName(userPromoted1.getUserId());
        }
    }


   @Override
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "伤残预估等级回复", value = "backend-invalidism-report")
    public ApiResponse report(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        String injuryDiagnose = apiReq.getString("injuryDiagnose");
        Integer isOperation = apiReq.getInt("isOperation");
        String reportBasis = apiReq.getString("reportBasis");
        String invalidismGrade = apiReq.getString("invalidismGrade");
        String reportDesc = apiReq.getString("reportDesc");
       InvalidismEstimateReport report = invalidismEstimateReportMapper.queryApplyByEstimateId(id);
       if(report == null){
           report = new InvalidismEstimateReport();
       }
       InvalidismEstimate invalidismEstimate = invalidismEstimateMapper.selectByPrimaryKey(id);
       report.setInjuryDiagnose(injuryDiagnose);
       report.setIsOperation(isOperation);
       report.setReportBasis(reportBasis);
       report.setInvalidismGrade(invalidismGrade == null || invalidismGrade == "" ? "无" :invalidismGrade);
       report.setReportDesc(reportDesc);
       report.setEstimateId(id);
       report.setUserName(invalidismEstimate.getUserName());
       report.setAccidentAddress(invalidismEstimate.getAccidentProvince() + invalidismEstimate.getAccidentCity() +
               invalidismEstimate.getAccidentDistrict() + invalidismEstimate.getAccidentAddress());
       report.setCreateTime(new Date());
       int ret = 0;
       if(report.getId() == null){
           ret=  invalidismEstimateReportMapper.insertSelective(report);
       }else{
           ret=  invalidismEstimateReportMapper.updateByPrimaryKeySelective(report);
       }
        if(ret > 0){
            if(invalidismEstimate.getState() == 1){
                CaseFollowInfo caseFollowInfo = new CaseFollowInfo();
                caseFollowInfo.setType(3);
                caseFollowInfo.setCaseId(id);
                caseFollowInfo.setCaseState(2);
                caseFollowInfo.setCaseStateStr("已预估");
                caseFollowInfo.setFollowTime(new Date());
                caseFollowInfo.setFollowBy(apiReq.getCurrentUserDisplayName());
                caseFollowInfo.setFollowById(apiReq.getCurrentUserId());
                caseFollowInfoMapper.insertSelective(caseFollowInfo);

                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setTitle("伤残等级预估审核通过通知");
                messageInfo.setConent("您好，您提交的伤残等级预估我们已经替您预估，服务专员将在24小时内与您联系，请您保持电话畅通，如需其他咨询，可致电24小时服务电话4006303071，乐凡时刻在您身边。");
                messageInfo.setSenderId(0);
                messageInfo.setSenderName("系统消息");
                messageInfo.setReceiverId(invalidismEstimate.getUserId());
                messageInfo.setReceiverName(invalidismEstimate.getUserName());
                messageInfo.setMessageType(1);
                messageInfo.setSendTime(new Date());
                messageInfo.setDeleteFlag(0);
                messageInfo.setIsRead(0);
                messageInfoMapper.insertSelective(messageInfo);
            }
            invalidismEstimate.setState(2);
            invalidismEstimate.setUpdateTime(new Date());
            invalidismEstimate.setUpdateBy(String.valueOf(apiReq.getCurrentUserId()));
            invalidismEstimateMapper.updateByPrimaryKeySelective(invalidismEstimate);
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @Override
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "查看伤残预估等级回复", value = "backend-invalidism-to-report")
    public ApiResponse toReport(ApiRequest apiReq) {
        Long estimateId = apiReq.getLong("id");
        //info页面查看伤残评估
        if(estimateId == null){
            Map<String,Object> map = new HashMap<>();
            map.put("caseId",apiReq.getLong("caseId"));
            map.put("caseNo",apiReq.getString("caseNo"));
            InvalidismEstimate invalidismEstimate= invalidismEstimateMapper.selectInvalidismEstimateByInfo(map);
            if(invalidismEstimate!=null){
                estimateId = invalidismEstimate.getId();
            }
        }
        InvalidismEstimateReport report = invalidismEstimateReportMapper.queryApplyByEstimateId(estimateId);
        //转换成伤残等级 字符串
        String gradeStr = "";
        if (report != null) {
            String grade = report.getInvalidismGrade();
            if (!"".equals(grade) && grade != null) {
                gradeStr = convertGradeStr(grade);
            }else {
                gradeStr = gradeStr.concat("无级");
            }
            report.setInvalidismGradeStr(gradeStr);
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,report);
    }

   @Override
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "查看资料", value = "backend-invalidism-file")
    public ApiResponse queryFile(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
       List<InvalidismEstimateFile> invalidismEstimates=invalidismEstimateFileMapper.queryFileByInvalidismEstimateId(id);
       return new ApiResponse(ApiMsgEnum.SUCCESS,invalidismEstimates==null?0:invalidismEstimates.size(),invalidismEstimates);
    }

    @Override
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "伤残等级预估删除", value = "backend-invalidism-del")
    public ApiResponse del(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        if (StringUtils.isEmpty(id)) {
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        InvalidismEstimate invalidismEstimate = new InvalidismEstimate();
        invalidismEstimate.setId(id);
        invalidismEstimate.setDeleteFlag(1);
        invalidismEstimate.setUpdateTime(new Date());
        invalidismEstimate.setUpdateBy(String.valueOf(apiReq.getCurrentUserId()));
        this.invalidismEstimateMapper.updateByPrimaryKeySelective(invalidismEstimate);
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 转换成伤残等级 字符串  (1,2,3,1 to 一级,二级,三级,一级)
     * @param grade
     * @return
     */
    private String convertGradeStr(String grade){
        String gradeStr = "";
        if (!"".equals(grade) && grade != null) {
            String [] grades = grade.split(",");
            for (String s : grades) {
                if ("无".equals(s) || "".equals(s)){
                    gradeStr = gradeStr.concat("无级,");
                    continue;
                }
                try {
                    switch (Integer.valueOf(s)){
                        case 1 : gradeStr = gradeStr.concat("一级,"); break;
                        case 2 : gradeStr = gradeStr.concat("二级,"); break;
                        case 3 : gradeStr = gradeStr.concat("三级,"); break;
                        case 4 : gradeStr = gradeStr.concat("四级,"); break;
                        case 5 : gradeStr = gradeStr.concat("五级,"); break;
                        case 6 : gradeStr = gradeStr.concat("六级,"); break;
                        case 7 : gradeStr = gradeStr.concat("七级,"); break;
                        case 8 : gradeStr = gradeStr.concat("八级,"); break;
                        case 9 : gradeStr = gradeStr.concat("九级,"); break;
                        case 10 : gradeStr = gradeStr.concat("十级,"); break;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else {
            gradeStr = gradeStr.concat("无级,");
        }
        return gradeStr.substring(0,gradeStr.length() - 1);
    }
}
