package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendHoboProCaseApi;
import com.lefancrm.apicenter.dao.HoboProCaseMapper;
import com.lefancrm.apicenter.dao.HoboProInfoMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.dto.HoboReportData;
import com.lefancrm.apicenter.model.HoboProCase;
import com.lefancrm.apicenter.model.HoboProInfo;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@ApiService(descript = "咨询服务API")
public class BackendHoboProCaseApiImpl extends BaseServiceImpl implements BackendHoboProCaseApi {

    @Autowired
    private HoboProCaseMapper hoboProCaseMapper;
    @Autowired
    private HoboProInfoMapper hoboProInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @ApiMethod(needLogin = false,descript = "咨询服务列表",value = "list-hobo-pro-case")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        Long userId = getCurrentUserId(apiRequest);
        if (!"2189".equals(userId)){
            apiRequest.put("deptId",userId);
        }
        if ("report".equals(apiRequest.getString("btnCode"))){

        }else{
            setBackendPageSize(apiRequest);
        }
        int count = hoboProCaseMapper.listSize(apiRequest);
        List<HoboProCase> list = hoboProCaseMapper.list(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    @ApiMethod(needLogin = false,descript = "咨询服务详情",value = "info-hobo-pro-case")
    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        HoboProCase hoboProCase = hoboProCaseMapper.selectByPrimaryKey(apiRequest.getLong("id"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,hoboProCase);
    }


    @ApiMethod(needLogin = false,descript = "导入咨询服务案件",value = "import-hobo-pro-case")
    @Override
    public ApiResponse importData(ApiRequest apiRequest) {
        Long userId = getCurrentUserId(apiRequest);
        String entrustTime = apiRequest.getString("entrustTime");
        Map<String,Object> msgMap =  new HashMap<String,Object>();
        List<HoboProCase> importData = JSONArray.parseArray(apiRequest.getString("data"),HoboProCase.class);
        if (importData == null){
            return new ApiResponse(ApiMsgEnum.SURVEY_ZHA_EXCEL_ERROR);
        }
        if (importData.size() == 0){
            return new ApiResponse(ApiMsgEnum.SURVEY_ZHA_EXCEL_NOT_DATA);
        }
        List<HoboProCase> data = new ArrayList<>();
        List<HoboProInfo> hoboProInfos = hoboProInfoMapper.list(new HashMap<>());
        for (HoboProCase item : importData) {
            HoboProCase proCase = new HoboProCase();
            proCase.setInsureName(item.getInsureName());
            proCase.setInsureDeptName(item.getInsureDeptName());
            proCase.setName(item.getName());
            proCase.setIdCard(item.getIdCard());
            proCase.setTel(item.getTel());
            proCase.setCarNo(item.getCarNo());
            proCase.setProName(item.getProName());
            List<HoboProInfo> collect = hoboProInfos.stream().filter(p -> p.getProName().equals(proCase.getProName())).collect(Collectors.toList());
            if (collect.size() > 0){
                HoboProInfo hoboProInfo = collect.get(0);
                proCase.setProId(hoboProInfo.getId());
                proCase.setProPrice(hoboProInfo.getProPrice());
                proCase.setProText(hoboProInfo.getProText());
            }
            if (StringUtils.isEmpty(entrustTime)){
                proCase.setEntrustTime(new Date());
            }else{
                proCase.setEntrustTime(DateUtils.parseDate(entrustTime,"yyyy-MM-dd"));
            }
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
            proCase.setDeptId(userInfo.getUserId());
            proCase.setDeptName(userInfo.getUserName());
            data.add(proCase);
        }
        if (data.size() > 0){
            hoboProCaseMapper.saveByData(data);
        }

        StringBuffer msg = new StringBuffer();
        msg.append("总条数：" + importData.size() + "条，导入成功"+data.size()+"条;");
        msgMap.put("msg",msg);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,msgMap);
    }


    @ApiMethod(needLogin = false,descript = "报表",value = "report-list-hobo-pro-case")
    @Override
    public ApiResponse reportList(ApiRequest apiRequest) {
        List<HoboReportData> hoboReportData = hoboProCaseMapper.selectReportData(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,hoboReportData.size(),hoboReportData);
    }
}
