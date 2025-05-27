package com.lefancrm.apicenter.backendapi.impl;


import com.lefancrm.apicenter.backendapi.BackendClaimsApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.claims.ClaimsApplyEnumFilesDTO;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@ApiService(descript = "共享理赔相关API")
public class BackendClaimsApiImpl extends BaseServiceImpl implements BackendClaimsApi {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ClaimsApplyInfoMapper claimsApplyInfoMapper;
    @Autowired
    private ClaimsAnalysisInfoMapper claimsAnalysisInfoMapper;
    @Autowired
    private ClaimsShuttleInfoMapper claimsShuttleInfoMapper;
    @Autowired
    private ClaimsVisitInfoMapper claimsVisitInfoMapper;
    @Autowired
    private ClaimsArrangedInfoMapper claimsArrangedInfoMapper;
    @Autowired
    private ClaimsApplyFileMapper claimsApplyFileMapper;
    @Autowired
    private CommonEnumMapper commonEnumMapper;
    /**
     * 列表
     * @param apiRequest
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "共享理赔", value = "backend-claims-list", apiParams = { })
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        String tableName = apiRequest.getString("tableName");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(apiRequest));
        Boolean report = "report".equals(apiRequest.getString("report")) ? true : false;
        try {
            switch (tableName){
                case "sqlp" : return sqlp(apiRequest, userInfo, report);
                case "sgzrzd" : return sgzrzd(apiRequest, userInfo, report);
                case "sgzrfw" : return sgzrfw(apiRequest, userInfo, report);
                case "wwts" : return wwts(apiRequest, userInfo, report);
                case "qcbb" : return qcbb(apiRequest,userInfo, report);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
    /**
     * 申请理赔
     * @param apiRequest
     * @param userInfo
     * @return
     * @throws Exception
     */
    private ApiResponse sqlp(ApiRequest apiRequest,UserInfo userInfo,Boolean report) throws Exception{
        //组织特殊查询条件


        int count = 0;
        if (!report){
            this.setBackendPageSize(apiRequest);
            count = claimsApplyInfoMapper.listSize(apiRequest);
        }
        List<ClaimsApplyInfo> list = claimsApplyInfoMapper.list(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,report ? list.size() : count,list);
    }
    private ApiResponse sgzrzd(ApiRequest apiRequest,UserInfo userInfo,Boolean report) throws Exception{
        //组织特殊查询条件


        int count = 0;
        if (!report){
            this.setBackendPageSize(apiRequest);
            count = claimsAnalysisInfoMapper.listSize(apiRequest);
        }
        List<ClaimsAnalysisInfo> list = claimsAnalysisInfoMapper.list(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,report ? list.size() : count,list);
    }
    private ApiResponse sgzrfw(ApiRequest apiRequest,UserInfo userInfo,Boolean report) throws Exception{
        //组织特殊查询条件


        int count = 0;
        if (!report){
            this.setBackendPageSize(apiRequest);
            count = claimsShuttleInfoMapper.listSize(apiRequest);
        }
        List<ClaimsShuttleInfo> list = claimsShuttleInfoMapper.list(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,report ? list.size() : count,list);
    }
    private ApiResponse wwts(ApiRequest apiRequest,UserInfo userInfo,Boolean report) throws Exception{
        //组织特殊查询条件


        int count = 0;
        if (!report){
            this.setBackendPageSize(apiRequest);
            count = claimsVisitInfoMapper.listSize(apiRequest);
        }
        List<ClaimsVisitInfo> list = claimsVisitInfoMapper.list(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,report ? list.size() : count,list);
    }
    private ApiResponse qcbb(ApiRequest apiRequest,UserInfo userInfo,Boolean report) throws Exception{
        //组织特殊查询条件


        int count = 0;
        if (!report){
            this.setBackendPageSize(apiRequest);
            count = claimsArrangedInfoMapper.listSize(apiRequest);
        }
        List<ClaimsArrangedInfo> list = claimsArrangedInfoMapper.list(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,report ? list.size() : count,list);
    }



    @ApiMethod(descript = "共享理赔", value = "backend-claims-operate", apiParams = { })
    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        String tableName = apiRequest.getString("tableName");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(apiRequest));
        try {
            switch (tableName){
                case "sqlp" : {
                    Long id = apiRequest.getLong("id");
                    String btnCode = apiRequest.getString("btnCode");
                    ClaimsApplyInfo claimsApplyInfo = claimsApplyInfoMapper.selectByPrimaryKey(id);
                    if ("handle-yes".equals(btnCode)){
                        claimsApplyInfo.setHandleState(1);
                        claimsApplyInfo.setHandleTime(new Date());
                        claimsApplyInfo.setHandleUser(userInfo.getUserName());
                        claimsApplyInfoMapper.updateByPrimaryKey(claimsApplyInfo);
                    }if ("handle-no".equals(btnCode)){
                        claimsApplyInfo.setHandleState(0);
                        claimsApplyInfo.setHandleTime(null);
                        claimsApplyInfo.setHandleUser(null);
                        claimsApplyInfoMapper.updateByPrimaryKey(claimsApplyInfo);
                    }
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
                case "sgzrzd" : {
                    Long id = apiRequest.getLong("id");
                    String btnCode = apiRequest.getString("btnCode");
                    ClaimsAnalysisInfo claimsAnalysisInfo = claimsAnalysisInfoMapper.selectByPrimaryKey(id);
                    if ("handle-yes".equals(btnCode)){
                        claimsAnalysisInfo.setHandleState(1);
                        claimsAnalysisInfo.setHandleTime(new Date());
                        claimsAnalysisInfo.setHandleUser(userInfo.getUserName());
                        claimsAnalysisInfoMapper.updateByPrimaryKey(claimsAnalysisInfo);
                    }if ("handle-no".equals(btnCode)){
                        claimsAnalysisInfo.setHandleState(0);
                        claimsAnalysisInfo.setHandleTime(null);
                        claimsAnalysisInfo.setHandleUser(null);
                        claimsAnalysisInfoMapper.updateByPrimaryKey(claimsAnalysisInfo);
                    }
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
                case "sgzrfw" : {
                    Long id = apiRequest.getLong("id");
                    String btnCode = apiRequest.getString("btnCode");
                    ClaimsShuttleInfo claimsShuttleInfo = claimsShuttleInfoMapper.selectByPrimaryKey(id);
                    if ("handle-yes".equals(btnCode)){
                        claimsShuttleInfo.setHandleState(1);
                        claimsShuttleInfo.setHandleTime(new Date());
                        claimsShuttleInfo.setHandleUser(userInfo.getUserName());
                        claimsShuttleInfoMapper.updateByPrimaryKey(claimsShuttleInfo);
                    }if ("handle-no".equals(btnCode)){
                        claimsShuttleInfo.setHandleState(0);
                        claimsShuttleInfo.setHandleTime(null);
                        claimsShuttleInfo.setHandleUser(null);
                        claimsShuttleInfoMapper.updateByPrimaryKey(claimsShuttleInfo);
                    }
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
                case "wwts" : {
                    Long id = apiRequest.getLong("id");
                    String btnCode = apiRequest.getString("btnCode");
                    ClaimsVisitInfo claimsVisitInfo = claimsVisitInfoMapper.selectByPrimaryKey(id);
                    if ("handle-yes".equals(btnCode)){
                        claimsVisitInfo.setHandleState(1);
                        claimsVisitInfo.setHandleTime(new Date());
                        claimsVisitInfo.setHandleUser(userInfo.getUserName());
                        claimsVisitInfoMapper.updateByPrimaryKey(claimsVisitInfo);
                    }if ("handle-no".equals(btnCode)){
                        claimsVisitInfo.setHandleState(0);
                        claimsVisitInfo.setHandleTime(null);
                        claimsVisitInfo.setHandleUser(null);
                        claimsVisitInfoMapper.updateByPrimaryKey(claimsVisitInfo);
                    }
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
                case "qcbb" : {
                    Long id = apiRequest.getLong("id");
                    String btnCode = apiRequest.getString("btnCode");
                    ClaimsArrangedInfo claimsArrangedInfo = claimsArrangedInfoMapper.selectByPrimaryKey(id);
                    if ("handle-yes".equals(btnCode)){
                        claimsArrangedInfo.setHandleState(1);
                        claimsArrangedInfo.setHandleTime(new Date());
                        claimsArrangedInfo.setHandleUser(userInfo.getUserName());
                        claimsArrangedInfoMapper.updateByPrimaryKey(claimsArrangedInfo);
                    }if ("handle-no".equals(btnCode)){
                        claimsArrangedInfo.setHandleState(0);
                        claimsArrangedInfo.setHandleTime(null);
                        claimsArrangedInfo.setHandleUser(null);
                        claimsArrangedInfoMapper.updateByPrimaryKey(claimsArrangedInfo);
                    }
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(descript = "共享理赔", value = "backend-claims-ajax-data", apiParams = { })
    @Override
    public ApiResponse ajaxData(ApiRequest apiRequest) {
        String tableName = apiRequest.getString("tableName");
        String dataType = apiRequest.getString("dataType");
        if ("sqlp".equals(tableName)){
            if ("files".equals(dataType)){
                Long id = apiRequest.getLong("id");
                List<CommonEnum> enums = commonEnumMapper.selectListByParentEnumCode("claims");
                ApiRequest paramMap = new ApiRequest();
                paramMap.put("claimsApplyId",id);
                List<ClaimsApplyFile> applyFiles = claimsApplyFileMapper.list(paramMap);
                List<ClaimsApplyEnumFilesDTO> files = new ArrayList<>();
                for (CommonEnum item : enums) {
                    ClaimsApplyEnumFilesDTO temp = new ClaimsApplyEnumFilesDTO();
                    temp.setEnumId(item.getId());
                    temp.setEnumName(item.getEnumName());
                    List<ClaimsApplyFile> tempFiles = new ArrayList<>();
                    for (ClaimsApplyFile applyFile : applyFiles) {
                        if (item.getId().intValue() == applyFile.getEnumId().intValue()){
                            tempFiles.add(applyFile);
                        }
                    }
                    temp.setClaimsApplyFiles(tempFiles);
                    files.add(temp);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS,files.size(),files);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }
}
