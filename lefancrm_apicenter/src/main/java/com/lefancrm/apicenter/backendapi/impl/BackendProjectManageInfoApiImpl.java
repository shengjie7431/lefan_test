package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSON;
import com.lefancrm.apicenter.backendapi.BackendProjectManageInfoApi;
import com.lefancrm.apicenter.dao.ProjectManageInfoMapper;
import com.lefancrm.apicenter.dao.SurveyConsignorMapper;
import com.lefancrm.apicenter.dao.SurveyInvestigatorMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author EDZ
 */
@Service
@ApiService(descript = "项目管理")
public class BackendProjectManageInfoApiImpl extends BaseServiceImpl implements BackendProjectManageInfoApi {

    @Autowired
    private ProjectManageInfoMapper projectManageInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;

    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;

    /**
     * 查询项目管理所有数据
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "查询项目管理所有数据", value = "backend-project-manage-info-selectByMap")
    @Override
    public ApiResponse selectByMap(ApiRequest apiReq) {
        List<ProjectManageInfo> list=projectManageInfoMapper.selectByMap(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
    }

    /**
     * 查询项目管理一条数据
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "查询项目管理一条数据", value = "backend-project-manage-info-selectByOne")
    @Override
    public ApiResponse selectByOne(ApiRequest apiReq) {
        ProjectManageInfo projectManageInfo=projectManageInfoMapper.selectByOne(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,projectManageInfo);
    }

    /**
     * 修改项目管理一条数据
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "修改项目管理一条数据", value = "backend-project-manage-info-updateOne")
    @Override
    public ApiResponse updateOne(ApiRequest apiReq) {
        ProjectManageInfo projectManageInfo=projectManageInfoMapper.selectByOne(apiReq);
        if(projectManageInfo != null){
            Integer count=projectManageInfoMapper.updateOne(apiReq);
            return new ApiResponse(ApiMsgEnum.SUCCESS,count,null);
        }
        return null;
    }

    /**
     * 新增项目管理一条数据
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "新增项目管理一条数据", value = "backend-project-manage-info-insertOne")
    @Override
    public ApiResponse insertOne(ApiRequest apiReq) {
        ProjectManageInfo projectManageInfo = JSON.parseObject(JSON.toJSONString(apiReq), ProjectManageInfo.class);
        if(projectManageInfo != null){
            Integer count=projectManageInfoMapper.insertOne(projectManageInfo);
            return new ApiResponse(ApiMsgEnum.SUCCESS,count,projectManageInfo);
        }
        return null;
    }

    /**
     * 删除项目管理一条数据
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "删除项目管理一条数据", value = "backend-project-manage-info-deleteOne")
    @Override
    public ApiResponse deleteOne(ApiRequest apiReq) {
        ProjectManageInfo projectManageInfo=projectManageInfoMapper.selectByOne(apiReq);
        if(projectManageInfo != null){
            Integer count=projectManageInfoMapper.deleteOne(apiReq);
            return new ApiResponse(ApiMsgEnum.SUCCESS,count,null);
        }
        return null;
    }

    /**
     * 项目管理数据处理
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "项目管理数据处理", value = "backend-project-manage-info-dataHandle")
    @Override
    public ApiResponse dataHandle(ApiRequest apiReq){
        Long currentUserId = getCurrentUserId(apiReq);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        String muenCode=apiReq.getString("menuCode");
        if (StringUtils.isBlank(apiReq.getString("startTime"))) {
            apiReq.put("startTime", LocalDate.now().toString().substring(0, 7) + "-01");
            apiReq.put("endTime", LocalDate.now().toString());
        }
        if("queryAll".equals(muenCode)){//查询所有数据
            List<ProjectManageInfo> list=projectManageInfoMapper.selectByMap(apiReq);
            for (ProjectManageInfo projectManageInfo : list) {
                if (projectManageInfo.getManagerState() == 4 && projectManageInfo.getEntrustOrgId() != null){
                    Map<String, Object> paramMap = new HashMap();
                    paramMap.put("entrustOrgId",projectManageInfo.getEntrustOrgId());
                    paramMap.put("startTime",apiReq.getString("startTime"));
                    paramMap.put("endTime",apiReq.getString("endTime"));
                    Map map = projectManageInfoMapper.queryDataByEntrustOrgId(paramMap);
                    projectManageInfo.setEntrustCaseNum((long)map.get("entrustCaseNum"));
                    projectManageInfo.setFinishCaseNum((long)map.get("finishCaseNum"));
                    projectManageInfo.setImgMoney((double)map.get("imgMoney"));
                    projectManageInfo.setImgAccountMoney((double)map.get("imgAccountMoney"));
                    projectManageInfo.setImgNoAccountMoney(projectManageInfo.getImgMoney()-projectManageInfo.getImgAccountMoney());
                }else {
                    projectManageInfo.setEntrustCaseNum(0L);
                    projectManageInfo.setFinishCaseNum(0L);
                    projectManageInfo.setImgMoney(0d);
                    projectManageInfo.setImgAccountMoney(0d);
                    projectManageInfo.setImgNoAccountMoney(0d);
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,list);
        }else if("queryOne".equals(muenCode)){//查询一条数据
            String id=apiReq.getString("id");
            Map findMap=new HashMap();
            findMap.put("roleId",113);
            List<SurveyInvestigator> surveyInvestigatorList=surveyInvestigatorMapper.selectBusinessRole(findMap);
            Map map=new HashMap();
            map.put("surveyInvestigatorList",surveyInvestigatorList);
            if(id != null){
                findMap=new HashMap();
                findMap.put("id",id);
                ProjectManageInfo projectManageInfo=projectManageInfoMapper.selectByOne(findMap);
                map.put("projectManageInfo",projectManageInfo);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
        }else if("insertOne".equals(muenCode)){//新增数据
            ProjectManageInfo projectManageInfo = JSON.parseObject(JSON.toJSONString(apiReq), ProjectManageInfo.class);
            Map findMap=new HashMap();
            findMap.put("projectName",projectManageInfo.getProjectName());
            ProjectManageInfo projectManageInfoTwo=projectManageInfoMapper.selectByOne(findMap);
            if(projectManageInfoTwo != null){
                return new ApiResponse(ApiMsgEnum.FAIL,null,"项目名称重复!");
            }
            if(projectManageInfo != null){
                projectManageInfo.setCreateTime(new Date());
                projectManageInfo.setCreateBy(userInfo.getUserName());
                Integer count=projectManageInfoMapper.insertSelective(projectManageInfo);
                if(count>0 && projectManageInfo.getManagerState()==4){
                    SurveyConsignor surveyConsignor=new SurveyConsignor();
                    surveyConsignor.setName(projectManageInfo.getProjectName());
                    surveyConsignor.setCompany(projectManageInfo.getProjectName());
                    surveyConsignor.setOrgAttr(projectManageInfo.getBusinessAttributesId());
                    surveyConsignor.setCreateBy(userInfo.getUserId());
                    surveyConsignor.setCreateByName(userInfo.getUserName());
                    surveyConsignor.setCreateTime(new Date());
                    surveyConsignor.setDeleteFlag(0);
                    surveyConsignor.setIsCredit(1);
                    surveyConsignor.setOrgState(0);
                    surveyConsignorMapper.insert(surveyConsignor);
                    surveyConsignor.setCode("WT"+surveyConsignor.getId());
                    surveyConsignorMapper.updateByPrimaryKey(surveyConsignor);
                    Map paramMap = new HashMap();
                    paramMap.put("entrustOrgId",surveyConsignor.getId());
                    paramMap.put("id",projectManageInfo.getId());
                    projectManageInfoMapper.updateOne(paramMap);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,count,projectManageInfo);
            }
        }else if("updateOne".equals(muenCode)){//修改数据
            String id=apiReq.getString("id");
            Map findMap=new HashMap();
            findMap.put("id",id);
            ProjectManageInfo projectManageInfo=projectManageInfoMapper.selectByOne(findMap);
            if(projectManageInfo != null){
                String projectName=apiReq.getString("projectName");
                if(!projectManageInfo.getProjectName().equals(projectName)){
                    findMap=new HashMap();
                    findMap.put("projectName",projectName);
                    ProjectManageInfo projectManageInfoTwo=projectManageInfoMapper.selectByOne(findMap);
                    if(projectManageInfoTwo != null){
                        return new ApiResponse(ApiMsgEnum.FAIL,null,"项目名称重复!");
                    }
                }
                Integer count=projectManageInfoMapper.updateOne(apiReq);
                Integer managerState=apiReq.getInt("managerState")==null?0:apiReq.getInt("managerState");
                if(count>0 && managerState==4){
                    SurveyConsignor surveyConsignor=new SurveyConsignor();
                    surveyConsignor.setName(projectManageInfo.getProjectName());
                    surveyConsignor.setCompany(projectManageInfo.getProjectName());
                    surveyConsignor.setOrgAttr(projectManageInfo.getBusinessAttributesId());
                    surveyConsignor.setCreateBy(userInfo.getUserId());
                    surveyConsignor.setCreateByName(userInfo.getUserName());
                    surveyConsignor.setCreateTime(new Date());
                    surveyConsignor.setDeleteFlag(0);
                    surveyConsignor.setOrgState(0);
                    surveyConsignorMapper.insert(surveyConsignor);
                    surveyConsignor.setCode("WT"+surveyConsignor.getId());
                    surveyConsignorMapper.updateByPrimaryKey(surveyConsignor);
                    Map paramMap = new HashMap();
                    paramMap.put("entrustOrgId",surveyConsignor.getId());
                    paramMap.put("id",projectManageInfo.getId());
                    projectManageInfoMapper.updateOne(paramMap);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,count,null);
            }
        }else if("daleteOne".equals(muenCode)){//删除数据
            String id=apiReq.getString("id");
            Map findMap=new HashMap();
            findMap.put("id",id);
            ProjectManageInfo projectManageInfo=projectManageInfoMapper.selectByOne(findMap);
            if(projectManageInfo != null){
                Integer count=projectManageInfoMapper.deleteOne(apiReq);
                return new ApiResponse(ApiMsgEnum.SUCCESS,count,null);
            }
        }
        return null;
    }
}
