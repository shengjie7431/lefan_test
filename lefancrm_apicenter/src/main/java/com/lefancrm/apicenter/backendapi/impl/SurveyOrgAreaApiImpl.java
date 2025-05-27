package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.SurveyOrgAreaApi;
import com.lefancrm.apicenter.dao.SurveyFranchiseeMapper;
import com.lefancrm.apicenter.dao.SurveyInvestigatorMapper;
import com.lefancrm.apicenter.dao.SurveyOrgAreaMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.SurveyFranchisee;
import com.lefancrm.apicenter.model.SurveyOrgArea;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LSG
 */
@Service
@ApiService(descript = "机构片区表API")
public class SurveyOrgAreaApiImpl extends BaseServiceImpl implements SurveyOrgAreaApi {


    @Autowired
    private SurveyOrgAreaMapper surveyOrgAreaMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;

    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;

    @Override
    @ApiMethod(needLogin = false,descript = "机构片区信息处理",value = "backend-survey-org-area-operate")
    public ApiResponse operate(ApiRequest apiRequest) {
        Long userId = apiRequest.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        Map map=new HashMap();
        String surveyCode=apiRequest.getString("surveyCode");
        if("areaInformation".equals(surveyCode)){//按条件查询所有数据
            map.put("surveyOrgId",apiRequest.getInt("id"));
            map.put("surveyAreaName",apiRequest.getString("surveyAreaName"));
            List<SurveyOrgArea> list= surveyOrgAreaMapper.selectByList(map);
            return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
        }else if("areaInformationSelectOne".equals(surveyCode)){//根据条件查询一条数据
            map.put("id",apiRequest.getInt("id"));
            SurveyOrgArea surveyOrgArea=surveyOrgAreaMapper.selectOne(map);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyOrgArea);
        }else if("addAreaInformation".equals(surveyCode)){//新增一条数据
            map.put("surveyAreaName",apiRequest.getString("surveyAreaName").trim());
            SurveyOrgArea surveyOrgAreaTwo=surveyOrgAreaMapper.selectOne(map);
            if(surveyOrgAreaTwo != null){
                return new ApiResponse(ApiMsgEnum.FAIL,0,"片区名称已存在!");
            }
            Long surveyOrgId=apiRequest.getLong("surveyOrgId");
            SurveyFranchisee surveyFranchisee=surveyFranchiseeMapper.selectByPrimaryKey(surveyOrgId);
            if(surveyFranchisee == null){
                return new ApiResponse(ApiMsgEnum.FAIL,0,"该机构已不存在!");
            }
            SurveyOrgArea surveyOrgArea = ConvertToBeanUtil.toBeanFromApiRequest(apiRequest, SurveyOrgArea.class);
            surveyOrgArea.setSurveyOrgId(Integer.parseInt(surveyFranchisee.getId().toString()));
            surveyOrgArea.setSurveyOrgName(surveyFranchisee.getName());
            surveyOrgArea.setDeleteFlag(0);
            surveyOrgArea.setState(1);
            surveyOrgArea.setCreateBy(userInfo.getUserName());
            surveyOrgArea.setCreateTime(new Date());
            surveyOrgAreaMapper.addOne(surveyOrgArea);
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else if("updAreaInformation".equals(surveyCode)) {//修改一条数据
            map.put("id",apiRequest.getInt("id"));
            SurveyOrgArea surveyOrgArea=surveyOrgAreaMapper.selectOne(map);
            if(surveyOrgArea != null){
                String surveyAreaName=apiRequest.getString("surveyAreaName").trim();
                if(!surveyAreaName.equals(surveyOrgArea.getSurveyAreaName())){
                    map=new HashMap();
                    map.put("surveyAreaName",surveyAreaName);
                    SurveyOrgArea surveyOrgAreaTwo=surveyOrgAreaMapper.selectOne(map);
                    if(surveyOrgAreaTwo != null){
                        return new ApiResponse(ApiMsgEnum.FAIL,0,"片区名称已存在!");
                    }
                }
                surveyOrgArea=ConvertToBeanUtil.toBeanFromApiRequest(apiRequest, SurveyOrgArea.class);
                surveyOrgAreaMapper.updOne(surveyOrgArea);
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
        }else if("delAreaInformation".equals(surveyCode)) {//删除一条数据
            map.put("id",apiRequest.getInt("id"));
            SurveyOrgArea surveyOrgArea=surveyOrgAreaMapper.selectOne(map);
            if(surveyOrgArea != null){
                surveyOrgArea=ConvertToBeanUtil.toBeanFromApiRequest(apiRequest, SurveyOrgArea.class);
                Integer count=surveyOrgAreaMapper.updOne(surveyOrgArea);
                if(count>0){
                    Map findMap=new HashMap();
                    findMap.put("areaId",surveyOrgArea.getId());
                    findMap.put("surveyAreaId",null);
                    findMap.put("surveyAreaName",null);
                    surveyInvestigatorMapper.updateSurveyAreaId(findMap);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
        }
        return null;
    }
}
