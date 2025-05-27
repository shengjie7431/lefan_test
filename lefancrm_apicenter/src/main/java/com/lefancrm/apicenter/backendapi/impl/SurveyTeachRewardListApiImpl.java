package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.SurveyTeachRewardListApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.SurveyInvestigatorCaseDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 带教奖励清单API
 * @author EDZ
 */
@Service
@ApiService(descript = "带教奖励清单API")
public class SurveyTeachRewardListApiImpl extends BaseServiceImpl implements SurveyTeachRewardListApi {

    @Autowired
    private SurveyTeachRewardListMapper surveyTeachRewardListMapper;

    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;

    @Autowired
    private SurveyInvestigatorCaseMapper surveyInvestigatorCaseMapper;

    @Autowired
    private SurveyInvestigatorRewordMidMapper surveyInvestigatorRewordMidMapper;

    @Autowired
    private SurveyInvestigatorCaseTypeMapper surveyInvestigatorCaseTypeMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 查询所有的带教清单
     * @param apiRequest
     * @return
     */
    @ApiMethod(descript = "查询所有的带教清单", value = "backend-survey-teach-reward-selectByMap")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse selectByMap(ApiRequest apiRequest) {
        List<SurveyTeachRewardList> list=surveyTeachRewardListMapper.selectByMap(apiRequest);
        return new ApiResponse<List<SurveyTeachRewardList>>(ApiMsgEnum.SUCCESS, 1, list);
    }

    /**
     * 新增时查询未创建的带教清单
     * @param apiRequest
     * @return
     */
    @ApiMethod(descript = "新增时查询未创建的带教清单", value = "backend-survey-teach-reward-add-selectByMap")
    @SuppressWarnings("rawtypes")
    public ApiResponse addSelectByMap(ApiRequest apiRequest) {
        String btnCode=apiRequest.getString("btnCode");
        List<SurveyInvestigator> list=new ArrayList<>();
        if("newAdd".equals(btnCode)){//点击新增时
            list=surveyInvestigatorMapper.createTeachingList(apiRequest);
        }else if("handle".equals(btnCode)){//点击处理时
            list=surveyInvestigatorMapper.detailsTeachingList(apiRequest);
        }
        //查询所有人的积分
        List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseDtoList=surveyInvestigatorCaseMapper.selectAllScore(apiRequest);
        for (SurveyInvestigator surveyInvestigator:list) {
            Double integral=0D;
            for (SurveyInvestigatorCaseDto surveyInvestigatorCaseDto:surveyInvestigatorCaseDtoList) {
                if(surveyInvestigator.getUserId().equals(surveyInvestigatorCaseDto.getSurveyUserId())){
                    //计算每个新人的积分总和
                    integral=integral+surveyInvestigatorCaseDto.getScore();
                }
            }
            surveyInvestigator.setScore(integral);
        }
        return new ApiResponse<List<SurveyInvestigator>>(ApiMsgEnum.SUCCESS, 1, list);
    }

    /**
     * 修改清单
     * @param apiRequest
     * @return
     */
    @ApiMethod(descript = "修改清单", value = "backend-survey-teach-reward-updateDetailedList")
    @SuppressWarnings("rawtypes")
    public ApiResponse updateDetailedList(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo=userInfoMapper.selectByPrimaryKey(currentUserId);
        String btnCode=apiRequest.getString("btnCode");
        if("cancel".equals(btnCode) || "submit".equals(btnCode)){//点击保存,提交
            String id=apiRequest.getString("id");
            if(id != null){ //点击提交时当清单id不为空时保存并修改状态
                SurveyTeachRewardList surveyTeachRewardList=surveyTeachRewardListMapper.selectByPrimaryKey(Long.parseLong(id));
                if("submit".equals(btnCode)){
                    surveyTeachRewardList.setState(2);
                    surveyTeachRewardList.setUpdateTime(new Date());
                    surveyTeachRewardList.setUpdateBy(userInfo.getUserName());
                    Integer count=surveyTeachRewardListMapper.updateByPrimaryKey(surveyTeachRewardList);
                    return new ApiResponse(ApiMsgEnum.SUCCESS, count, null);
                }else if("cancel".equals(btnCode)){
                    surveyTeachRewardList.setListName(apiRequest.getString("listName"));
                    surveyTeachRewardList.setUpdateTime(new Date());
                    surveyTeachRewardList.setUpdateBy(userInfo.getUserName());
                    Integer count=surveyTeachRewardListMapper.updateByPrimaryKey(surveyTeachRewardList);
                    return new ApiResponse(ApiMsgEnum.SUCCESS, count, null);
                }
            }else {//当id为空时创建清单
                Map findMap = new HashMap();
                String ids = apiRequest.getString("ids");
                findMap.put("ids", ids);
                List<SurveyInvestigator> list = surveyInvestigatorMapper.list(findMap);
                Integer teacherUserCount = surveyInvestigatorMapper.selectTeacherUserCount(findMap);
                Double money = 0D;
                for (SurveyInvestigator surveyInvestigator : list) {
                    if (surveyInvestigator.getTeacherRealReward() != null) {
                        money = money + surveyInvestigator.getTeacherRealReward();
                    }
                }
                SurveyTeachRewardList surveyTeachRewardList = new SurveyTeachRewardList();
                surveyTeachRewardList.setListName(apiRequest.getString("listName"));
                surveyTeachRewardList.setTeacherNum(teacherUserCount);
                surveyTeachRewardList.setStudentNum(list.size());
                surveyTeachRewardList.setTeacherReward(money);
                if ("cancel".equals(btnCode)) {
                    surveyTeachRewardList.setState(1);//点击保存
                } else if ("submit".equals(btnCode)) {
                    surveyTeachRewardList.setState(2);//点击提交
                }
                surveyTeachRewardList.setCreateTime(new Date());
                surveyTeachRewardList.setCraeteBy(userInfo.getUserName());
                surveyTeachRewardListMapper.insert(surveyTeachRewardList);
                System.out.println(surveyTeachRewardList.getId());
                for (SurveyInvestigator surveyInvestigator : list) {
                    SurveyInvestigatorRewordMid surveyInvestigatorRewordMid = new SurveyInvestigatorRewordMid();
                    surveyInvestigatorRewordMid.setRewardListId(surveyTeachRewardList.getId());
                    surveyInvestigatorRewordMid.setSurveyInvestigatorId(surveyInvestigator.getId());
                    surveyInvestigatorRewordMidMapper.insert(surveyInvestigatorRewordMid);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS, Integer.parseInt(surveyTeachRewardList.getId().toString()), null);
            }
        }else if("reject".equals(btnCode) || "adopt".equals(btnCode)){//点击驳回,通过
            Long id=apiRequest.getLong("id");
            Integer count=0;
            SurveyTeachRewardList surveyTeachRewardList=surveyTeachRewardListMapper.selectByPrimaryKey(id);
            if(surveyTeachRewardList!=null){
                if("reject".equals(btnCode)){//点击驳回
                    surveyTeachRewardList.setState(4);
                    surveyTeachRewardList.setReturnDesc(apiRequest.getString("returnDesc"));
                }else if("adopt".equals(btnCode)){//点击通过
                    String ids=apiRequest.getString("ids");
                    surveyTeachRewardList.setState(3);
                    surveyTeachRewardList.setApprovalTime(new Date());
                    surveyInvestigatorMapper.updateIsSettlement(ids);
                }
                surveyTeachRewardList.setUpdateTime(new Date());
                surveyTeachRewardList.setUpdateBy(userInfo.getUserName());
                count=surveyTeachRewardListMapper.updateByPrimaryKey(surveyTeachRewardList);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, count, null);
        }else if("delete".equals(btnCode)){//点击删除
            SurveyTeachRewardList surveyTeachRewardList=surveyTeachRewardListMapper.selectByPrimaryKey(apiRequest.getLong("id"));
            surveyTeachRewardList.setUpdateTime(new Date());
            surveyTeachRewardList.setUpdateBy(userInfo.getUserName());
            surveyTeachRewardList.setDeleteFlag(1);
            Integer count=surveyTeachRewardListMapper.updateByPrimaryKey(surveyTeachRewardList);
            surveyInvestigatorRewordMidMapper.updateDeleteFlag(surveyTeachRewardList.getId());
            return new ApiResponse(ApiMsgEnum.SUCCESS, count, null);
        }
        return null;
    }

    /**
     * 点击积分查询信息
     * @param apiRequest
     * @return
     */
    @ApiMethod(descript = "点击积分查询信息", value = "backend-survey-teach-reward-selectTaskDetails")
    @SuppressWarnings("rawtypes")
    public ApiResponse selectTaskDetails(ApiRequest apiRequest){
        Long id=apiRequest.getLong("id");
        SurveyInvestigator surveyInvestigator=surveyInvestigatorMapper.selectByPrimaryKey(id);
        Map findMap=new HashMap();
        findMap.put("currentUserId",surveyInvestigator.getUserId());
        List<SurveyInvestigatorCaseDto> list=surveyInvestigatorCaseMapper.selectTaskDetails(findMap);
        Double jifen=0D;
        Map map=new HashMap();
        for (SurveyInvestigatorCaseDto surveyInvestigatorCaseDto:list) {
            if (surveyInvestigatorCaseDto.getScore() != null) {
                jifen = jifen + surveyInvestigatorCaseDto.getScore();
                surveyInvestigatorCaseDto.setCumulativePoints(jifen);
            }
            map.put("surveyUserId",surveyInvestigatorCaseDto.getSurveyUserId());
            map.put("surveyUserCaseId",surveyInvestigatorCaseDto.getSurveyUserCaseId());
            List<SurveyInvestigatorCaseType> caseTypeList=surveyInvestigatorCaseTypeMapper.list(map);
            surveyInvestigatorCaseDto.setTasks(caseTypeList);
        }
        return new ApiResponse<List<SurveyInvestigatorCaseDto>>(ApiMsgEnum.SUCCESS, 1, list);
    }
}
