package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyTaskInfoApi;
import com.lefancrm.apicenter.dao.SurveyTaskDirectionResultMapper;
import com.lefancrm.apicenter.dao.SurveyTaskInfoContentMapper;
import com.lefancrm.apicenter.dao.SurveyTaskInfoMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.dto.SurveyTaskInfo2Dto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wangwei on 2018/12/17.
 * “任务类型表”数据管理
 */
@Service
@ApiService(descript = "职位数据管理API")
public class BackendSurveyTaskInfoApiImpl extends BaseServiceImpl implements BackendSurveyTaskInfoApi {

    @Autowired
    private SurveyTaskInfoMapper surveyTaskInfoMapper;
    @Autowired
    private SurveyTaskInfoContentMapper surveyTaskInfoContentMapper;
    @Autowired
    private SurveyTaskDirectionResultMapper surveyTaskDirectionResultMapper;

    /**
     * 任务类型表(非分页数据)list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "任务类型表(非分页数据)list", value = "backend-survey-task-info-list", apiParams = { })
    @Override
    public ApiResponse list(ApiRequest apiReq) {
        //非分页数据
        List<SurveyTaskInfo> list = surveyTaskInfoMapper.list(apiReq);
        return new ApiResponse<List<SurveyTaskInfo>>(ApiMsgEnum.SUCCESS, null, list);
    }

    /**
     * 任务类型表(非分页数据)list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "任务类型表list-test", value = "backend-survey-task-info-list-test", apiParams = { })
    @Override
    public ApiResponse listTest(ApiRequest apiReq) {

        List<SurveyTaskInfo2Dto> list = new ArrayList<>();

        //任务类型
        List<SurveyTaskInfo> taskInfos = surveyTaskInfoMapper.list(apiReq);
        for (int i = 0; i < taskInfos.size(); i++) {
            Map<String,Object> map = new HashMap<>();
            Long taskInfoId = taskInfos.get(i).getId();
            map.put("taskInfoId",taskInfoId);
            //任务子类
            List<SurveyTaskInfoContent> contents = surveyTaskInfoContentMapper.list(map);

            outterLoop :for (int j = 0; j < contents.size(); j++) {
                map = new HashMap<>();
                map.put("taskInfoContentId", contents.get(j).getId());
                //子类对应方向结果
                List<SurveyTaskDirectionResult> results = surveyTaskDirectionResultMapper.list(map);

                for (int z = 0; z < results.size(); z++) {
                    String infoName = "";
                    if (taskInfoId == 13 || taskInfoId == 10) {//医疗调查
                        infoName = contents.get(j).getName() +"("+ results.get(z).getDirectionResultTypeName() +")";
                        SurveyTaskInfo2Dto taskInfo2 = addSurveyTaskInfo2Dto(taskInfos.get(i).getId(),taskInfos.get(i).getName(),contents.get(j).getId(),contents.get(j).getName(),results.get(z).getDirectionResultTypeId(),results.get(z).getDirectionResultTypeName(),infoName);
                        list.add(taskInfo2);
                    } else if (taskInfoId == 9 || taskInfoId == 14 || taskInfoId == 17) {//一对多对一 （走访调查）
                        infoName = taskInfos.get(i).getName();
                        SurveyTaskInfo2Dto taskInfo2 = addSurveyTaskInfo2Dto(taskInfos.get(i).getId(),taskInfos.get(i).getName(),contents.get(j).getId(),contents.get(j).getName(),results.get(z).getDirectionResultTypeId(),results.get(z).getDirectionResultTypeName(),infoName);
                        list.add(taskInfo2);
                        break outterLoop;//跳出标号标记的那个循环
                    } else if (taskInfoId == 16 || taskInfoId == 23 || taskInfoId == 24) { //一对多对多
                        infoName = taskInfos.get(i).getName() + "("+ results.get(z).getDirectionResultTypeName() +")";
                        SurveyTaskInfo2Dto taskInfo2 = addSurveyTaskInfo2Dto(taskInfos.get(i).getId(),taskInfos.get(i).getName(),contents.get(j).getId(),contents.get(j).getName(),results.get(z).getDirectionResultTypeId(),results.get(z).getDirectionResultTypeName(),infoName);
                        list.add(taskInfo2);
                    }
//                    else if(taskInfoId == 25 || taskInfoId == 26 || taskInfoId == 27 || taskInfoId == 28){ //互助类的
//                        infoName = taskInfos.get(i).getName() + "("+ contents.get(j).getName() +")";
//                        SurveyTaskInfo2Dto taskInfo2 = addSurveyTaskInfo2Dto(taskInfos.get(i).getId(),taskInfos.get(i).getName(),contents.get(j).getId(),contents.get(j).getName(),results.get(z).getDirectionResultTypeId(),results.get(z).getDirectionResultTypeName(),infoName);
//                        list.add(taskInfo2);
//                    }
                    if(taskInfos.get(i).getType() == 2){ //互助类的
                        infoName = taskInfos.get(i).getName() + "("+ results.get(z).getDirectionResultTypeName() +")";
                        SurveyTaskInfo2Dto taskInfo2 = addSurveyTaskInfo2Dto(taskInfos.get(i).getId(),taskInfos.get(i).getName(),contents.get(j).getId(),contents.get(j).getName(),results.get(z).getDirectionResultTypeId(),results.get(z).getDirectionResultTypeName(),infoName);
                        list.add(taskInfo2);
                    }
                }
//                if (taskInfoId == 10 || taskInfoId == 16 || taskInfoId == 23 || taskInfoId == 24) {//一对多
                if (taskInfoId == 16 || taskInfoId == 23 || taskInfoId == 24) {//一对多
                    break outterLoop; //跳出标号标记的那个循环
                }

            }

        }

        return new ApiResponse<List<SurveyTaskInfo2Dto>>(ApiMsgEnum.SUCCESS, null, list);
    }


    private SurveyTaskInfo2Dto addSurveyTaskInfo2Dto(Long taskId, String taskName, Long taskInfoContentId, String taskInfoContentName,Long directionResultTypeId, String directionResultTypeName, String infoName) {
        SurveyTaskInfo2Dto taskInfo2 = new SurveyTaskInfo2Dto();
        taskInfo2.setTaskId(taskId);
        taskInfo2.setTaskName(taskName);
        taskInfo2.setTaskInfoContentId(taskInfoContentId);
        taskInfo2.setTaskInfoContentName(taskInfoContentName);
        taskInfo2.setDirectionResultTypeId(directionResultTypeId);
        taskInfo2.setDirectionResultTypeName(directionResultTypeName);
        taskInfo2.setInfoName(infoName);
        return taskInfo2;
    }
}
