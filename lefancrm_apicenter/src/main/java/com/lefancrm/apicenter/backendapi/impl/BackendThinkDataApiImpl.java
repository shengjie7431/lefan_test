package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendThinkDataApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.finacial.FinancialFileTableEnumDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.task.ThinkTask;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import common.Assert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@ApiService(descript = "经营分析数据录入相关API")
public class BackendThinkDataApiImpl extends BaseServiceImpl implements BackendThinkDataApi {
    @Autowired
    private ThinkDataMapper thinkDataMapper;
    @Autowired
    private ThinkDataDetailMapper thinkDataDetailMapper;
    @Autowired
    private ThinkDataOrgProductMapper thinkDataOrgProductMapper;
    @Autowired
    private BackendFinancialFileApiImpl backendFinancialFileApi;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;

    @ApiMethod(needLogin = false,descript = "经营分析数据录入列表",value = "list-think-data")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        setAppPageSize(apiRequest);
        int size = thinkDataMapper.listSize(apiRequest);
        List<ThinkData> list = thinkDataMapper.list(apiRequest);
        for (ThinkData thinkData : list) {
            if (StringUtils.isNotEmpty(thinkData.getUserAndStateStr())) {
                List<ThinkData.ManagerUser> managerUsers = new ArrayList<ThinkData.ManagerUser>();
                List<String> tempUserStates = Arrays.asList(thinkData.getUserAndStateStr().split(","));
                for (String tempUserState : tempUserStates) {
                    if (StringUtils.isNotEmpty(tempUserState)) {
                        ThinkData.ManagerUser managerUser = thinkData.new ManagerUser();
                        String[] tempList = tempUserState.split("_");
                        managerUser.setUserName(tempList[0]);
                        managerUser.setState(tempList[1]);
                        managerUsers.add(managerUser);
                    }
                }
                thinkData.setManagerUsers(managerUsers);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,size,list);
    }

    @ApiMethod(needLogin = false,descript = "经营分析数据录入操作",value = "operate-think-data")
    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        Long thinkDataId = apiRequest.getLong("thinkDataId");
        String btnCode = apiRequest.getString("btnCode");
        ThinkData thinkData = thinkDataMapper.selectByPrimaryKey(thinkDataId);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(apiRequest));
        if ("save-money".equals(btnCode)){//保存金额
            Long oprId = apiRequest.getLong("oprId");
            String colStr = apiRequest.getString("colStr");
            Double value = apiRequest.getDouble("value");
            switch (colStr){
                case "claimSunAddmony" :
                    ThinkDataOrgProduct thinkDataOrgProduct = thinkDataOrgProductMapper.selectByPrimaryKey(oprId);
                    thinkDataOrgProduct.setClaimSunAddmony(value);
                    thinkDataOrgProductMapper.updateByPrimaryKey(thinkDataOrgProduct);
                    break;
                case "claimSunSubmoney" :
                    thinkDataOrgProduct = thinkDataOrgProductMapper.selectByPrimaryKey(oprId);
                    thinkDataOrgProduct.setClaimSunSubmoney(value);
                    thinkDataOrgProductMapper.updateByPrimaryKey(thinkDataOrgProduct);
                    break;
                case "busAccMony" :
                    thinkDataOrgProduct = thinkDataOrgProductMapper.selectByPrimaryKey(oprId);
                    thinkDataOrgProduct.setBusAccMony(value);
                    thinkDataOrgProductMapper.updateByPrimaryKey(thinkDataOrgProduct);
                    break;
                case "depAccMony" :
                    ThinkDataDetail thinkDataDetail = thinkDataDetailMapper.selectByPrimaryKey(oprId);
                    thinkDataDetail.setDepAccMony(value);
                    thinkDataDetailMapper.updateByPrimaryKey(thinkDataDetail);
                    break;
                case "lefanInMony" :
                    thinkDataDetail = thinkDataDetailMapper.selectByPrimaryKey(oprId);
                    thinkDataDetail.setLefanInMony(value);
                    thinkDataDetailMapper.updateByPrimaryKey(thinkDataDetail);
                    break;
                case "lefanOutMony" :
                    thinkDataDetail = thinkDataDetailMapper.selectByPrimaryKey(oprId);
                    thinkDataDetail.setLefanOutMony(value);
                    thinkDataDetailMapper.updateByPrimaryKey(thinkDataDetail);
                    break;
                case "lefanTaxMony" :
                    thinkDataDetail = thinkDataDetailMapper.selectByPrimaryKey(oprId);
                    thinkDataDetail.setLefanTaxMony(value);
                    thinkDataDetailMapper.updateByPrimaryKey(thinkDataDetail);
                    break;
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,thinkData);
        }else if ("upload-file".equals(btnCode)){//上传附件
            Long oprId = apiRequest.getLong("oprId");
            String json = apiRequest.getString("files");
            backendFinancialFileApi.saveFile(oprId, FinancialFileTableEnumDto.THINK_DATA_ORG_ATTR,userInfo,json);
            List<FinancialFile> files = backendFinancialFileApi.getFiles(oprId, FinancialFileTableEnumDto.THINK_DATA_ORG_ATTR);
            thinkData.setFiles(files);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,thinkData);
        }
        else if ("delete-file".equals(btnCode)){
            backendFinancialFileApi.deleteFileById(apiRequest.getLong("fileId"));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,thinkData);
        }
        else if ("commit".equals(btnCode)){//提交审核
            Long oprId = apiRequest.getLong("oprId");
            ThinkDataDetail thinkDataDetail = thinkDataDetailMapper.selectByPrimaryKey(oprId);
            thinkDataDetail.setState(2);
            thinkDataDetailMapper.updateByPrimaryKey(thinkDataDetail);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,thinkData);
        }else if ("commit-pass".equals(btnCode)){//审核通过
            Long oprId = apiRequest.getLong("oprId");
            ThinkDataDetail thinkDataDetail = thinkDataDetailMapper.selectByPrimaryKey(oprId);
            thinkDataDetail.setState(3);
            thinkDataDetailMapper.updateByPrimaryKey(thinkDataDetail);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,thinkData);
        }
        else if ("ceshi-generate".equals(btnCode)){//测试生成每月数据
            thinkTask.think();
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,thinkData);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
    @Autowired
    private ThinkTask thinkTask;

    @ApiMethod(needLogin = false,descript = "经营分析数据录入获取数据",value = "ajax-data-think-data")
    @Override
    public ApiResponse ajaxData(ApiRequest apiRequest) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(apiRequest));
        Long thinkDataId = apiRequest.getLong("thinkDataId");
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        paramMap.put("thinkDataId",thinkDataId);
        paramMap.put("dataType",StringUtils.isEmpty(apiRequest.getString("dataType")) ? 1 : apiRequest.getString("dataType"));
        //机构经理、分管总看自己的数据、财务看乐凡集团的数据、平台看所有的数据
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(userInfo.getUserId());
        paramMap.put("oneRole",isRoleUser(userRoles,108L) ? 1 : -1);//机构经理
        paramMap.put("twoRole",isRoleUser(userRoles,109L) ? 1 : -1);//分管总
        paramMap.put("threeRole",isRoleUser(userRoles,23L) ? 1 : -1);//财务
        paramMap.put("fourRole",isRoleUser(userRoles,138L) || isRoleUser(userRoles,28L)  ? 1 : -1);//经营分析报表-平台 测试角色
        paramMap.put("curUserId",getCurrentUserId(apiRequest));
        List<ThinkDataDetail> dataDetails = thinkDataDetailMapper.list(paramMap);
        paramMap =  new HashMap<String,Object>();
        paramMap.put("thinkDataId",thinkDataId);
        List<ThinkDataOrgProduct> orgProducts = thinkDataOrgProductMapper.list(paramMap);
        List<FinancialFile> files = backendFinancialFileApi.getFilesByIds(dataDetails.stream().map(ThinkDataDetail::getId).collect(Collectors.toList()), FinancialFileTableEnumDto.THINK_DATA_ORG_ATTR);
        for (ThinkDataDetail dataDetail : dataDetails) {
            dataDetail.setOrgProducts(orgProducts.stream().filter(p -> p.getThinkDataDetailId().intValue() == dataDetail.getId().intValue()).collect(Collectors.toList()));
            dataDetail.setFiles(files.stream().filter(p -> p.getKeyId().intValue() == dataDetail.getId().intValue()).collect(Collectors.toList()));
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,dataDetails.size(),dataDetails);
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId().intValue() == roleId.intValue()){
                return true;
            }
        }
        return false;
    }
}
