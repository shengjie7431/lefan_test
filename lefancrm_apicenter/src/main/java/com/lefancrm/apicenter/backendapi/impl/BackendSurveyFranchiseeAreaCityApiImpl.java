package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyFranchiseeAreaCityApi;
import com.lefancrm.apicenter.dao.CommonAreaMapper;
import com.lefancrm.apicenter.dao.SurveyFranchiseeAreaCityMapper;
import com.lefancrm.apicenter.model.CommonArea;
import com.lefancrm.apicenter.model.SurveyFranchiseeAreaCity;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@ApiService(descript = "调查方设置区域相关API")
public class BackendSurveyFranchiseeAreaCityApiImpl extends BaseServiceImpl implements BackendSurveyFranchiseeAreaCityApi {
    @Autowired
    private CommonAreaMapper commonAreaMapper;
    @Autowired
    private SurveyFranchiseeAreaCityMapper surveyFranchiseeAreaCityMapper;

    @ApiMethod(descript = "获取调查方区域列表", value = "backend-list-survey-franchisee-area-city", apiParams = { })
    @Override
    public ApiResponse getAreaData(ApiRequest apiRequest) {
        Long orgId = apiRequest.getLong("id");
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        paramMap.put("orgId",orgId);
        List<SurveyFranchiseeAreaCity> areas = surveyFranchiseeAreaCityMapper.getAreas(paramMap);
        return new ApiResponse(ApiMsgEnum.SUCCESS,areas.size(),areas);
    }


    @ApiMethod(descript = "获取调查方区域列表", value = "backend-list-survey-franchisee-area-city-common", apiParams = { })
    @Override
    public ApiResponse getCommonAreas(ApiRequest apiRequest){
        Long orgId = apiRequest.getLong("id");
        List<CommonArea> commonAreas = commonAreaMapper.selectAreaByParentId(0L);
        for (CommonArea commonArea : commonAreas) {
            List<CommonArea> childrenList = commonAreaMapper.selectAreaCountByParentId(commonArea.getAreaId());
            commonArea.setAllChildrenNum((int)childrenList.stream().filter(e->e.getAreaType() == 2).count());
            commonArea.setSelectedChildrenNum(0);
            String collect = childrenList.stream().filter(e->e.getAreaType() == 2).map(e -> e.getAreaId().toString()).collect(Collectors.joining(","));
            commonArea.setSelectedChildrenNum(surveyFranchiseeAreaCityMapper.selectAllSelectedCount(collect,orgId));
            commonArea.setSelectAreaIds(commonAreaMapper.selectAllChildrenFranchiseeArea(commonArea.getAreaId(),orgId));
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,commonAreas);
    }


}
