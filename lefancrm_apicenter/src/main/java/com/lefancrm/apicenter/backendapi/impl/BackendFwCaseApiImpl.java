package com.lefancrm.apicenter.backendapi.impl;

import cn.jpush.api.utils.StringUtils;
import com.lefancrm.apicenter.backendapi.BackendFwCaseApi;
import com.lefancrm.apicenter.dao.SurveyFwCaseMapper;
import com.lefancrm.apicenter.dao.SurveyFwProgressMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.SurveyFwCase;
import com.lefancrm.apicenter.model.SurveyFwProgress;
import com.lefancrm.apicenter.model.SurveyZhaAssess;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.DecimalUtil;
import com.lefancrm.apicenter.util.GetWorkDay;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@ApiService(descript = "保险增值服务相关API")
public class BackendFwCaseApiImpl extends BaseServiceImpl implements BackendFwCaseApi {

    @Autowired
    private SurveyFwCaseMapper surveyFwCaseMapper;
    @Autowired
    private SurveyFwProgressMapper surveyFwProgressMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @ApiMethod(needLogin = false,descript = "保险增值案件list",value = "list-fw-case")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        setBackendPageSize(apiRequest);
        int count = surveyFwCaseMapper.listSize(apiRequest);
        List<SurveyFwCase> list = surveyFwCaseMapper.list(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    @ApiMethod(needLogin = false,descript = "保险增值案件list",value = "operate-fw-case")
    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        String btnCode = apiRequest.getString("btnCode");
        Long fwId = apiRequest.getLong("fwId");
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        if ("progress".equals(btnCode)){
            String progressDesc = apiRequest.getString("progressDesc");
            String progressName = apiRequest.getString("progressName");
            SurveyFwProgress progress = new SurveyFwProgress();
            progress.setFwId(fwId);
            progress.setProgressUserId(userInfo.getUserId());
            progress.setProgressUserName(userInfo.getUserName());
            progress.setProgressName(progressName);
            progress.setProgressDesc(progressDesc);
            progress.setProgressTime(new Date());
            progress.setCreateBy(userInfo.getUserName());
            progress.setCreateTime(new Date());
            progress.setUpdateBy(userInfo.getUserName());
            progress.setUpdateTime(new Date());
            progress.setDeleteFlag(0);
            surveyFwProgressMapper.insert(progress);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,progress);
        }else if ("save".equals(btnCode)){
            String zfrq = apiRequest.getString("zfrq");//等待期截止日期
            if (StringUtils.isNotEmpty(zfrq)) {
                Date zfrqTime = DateUtils.parseDate(zfrq, "yyyy-MM-dd");
                apiRequest.put("zfrq", zfrqTime);
            }
            try {
                SurveyFwCase surveyFwCase = ConvertToBeanUtil.toBean(apiRequest, SurveyFwCase.class);
                surveyFwCase.setKpzt(2);
                surveyFwCaseMapper.insert(surveyFwCase);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyFwCase);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @ApiMethod(needLogin = false,descript = "保险增值案件list",value = "info-fw-case")
    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        SurveyFwCase surveyFwCase = surveyFwCaseMapper.selectByPrimaryKey(apiRequest.getLong("id"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyFwCase);
    }


    @ApiMethod(needLogin = false,descript = "保险增值案件ajax",value = "ajax-fw-case")
    @Override
    public ApiResponse ajaxData(ApiRequest apiRequest) {
        String code = apiRequest.getString("code");
        if ("progress".equals(code)){
            Long fwId = apiRequest.getLong("fwId");
            List<SurveyFwProgress> surveyFwProgresses = surveyFwProgressMapper.selectByFwId(fwId);
            return new ApiResponse(ApiMsgEnum.SUCCESS,surveyFwProgresses.size(),surveyFwProgresses);
        }
        return null;
    }


    private void initProgress(){
        Map<Long,Object> fwIds = new HashMap<>();
        String [] desc = {"",""};
        //加2000个案子
        for (int j = 0; j < 3208; j++) {
            Long fwId = randomFwId(fwIds);//案子Id
            //这个案子加几条跟踪信息
            Long num = new Long ((int)(Math.random() * 3 + 0));
            List<Integer> is = new ArrayList<>();
            List<Date> dates = new ArrayList<>();
            for (int k = 0; k < num ; k ++){
                Date gzDateTime = getGzDateTime("2022-01-01",dates);
                int i = randowDescIndex(is,desc.length);//随机一个描述信息
                SurveyFwProgress progress = new SurveyFwProgress();
                progress.setFwId(fwId);
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(2189L);
                progress.setProgressUserId(userInfo.getUserId());
                progress.setProgressUserName(userInfo.getUserName());
                progress.setProgressName("");
                progress.setProgressDesc(desc[i]);
                progress.setProgressTime(gzDateTime);
                progress.setCreateBy(userInfo.getUserName());
                progress.setCreateTime(gzDateTime);
                progress.setUpdateBy(userInfo.getUserName());
                progress.setUpdateTime(gzDateTime);
                progress.setDeleteFlag(0);
                surveyFwProgressMapper.insert(progress);
                is.add(i);
                dates.contains(gzDateTime);
            }
            fwIds.put(fwId,is);
        }
    }

    private Long randomFwId(Map<Long,Object> fwIds){
        Long fwId = new Long ((int)(Math.random() * 3208 + 1));//案子Id
        if (fwIds.containsKey(fwId)) {
            randomFwId(fwIds);
        }
        return fwId;
    }

    private int randowDescIndex(List<Integer> is,int length){
        int i = (int) (Math.random() * length);//随机一个描述信息
        if (is.contains(i)) {
            randowDescIndex(is,length);
        }
        return i;
    }

    private Date getGzDateTime(String startTimeStr,List<Date> dates){
        Date start = null;
        try {
            start = new SimpleDateFormat("yyyy-mm-dd").parse(startTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int day = (int)(Math.random() * 40 + 1);//随机N天
        Date end = GetWorkDay.calLeaveEndDate(start, null, day, 1);

        if (dates.contains(end)) {
            try {
                getGzDateTime(new SimpleDateFormat("yyyy-MM-dd").format(end),dates);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return end;
    }


    public static void main(String[] args) {
        Date end = null;
        try {
            end = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01" + " " + getStr(288800));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end));
    }

    public static String getStr(Integer s) {
        int second = s; //这是随便输入的秒值
        int hour = second / 3600; // 得到分钟数
        second = second % 3600;//剩余的秒数
        int minute = second / 60;//得到分
        second = second % 60;//剩余的秒
        String.format("%02d:%02d:%02d", hour, minute, second);
        System.out.println(String.format("%02d:%02d:%02d", hour, minute, second));
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }


}
