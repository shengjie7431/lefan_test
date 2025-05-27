package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendBillingRoleApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.CommonEnumDto;
import com.lefancrm.apicenter.dto.SurveyCashInfoDto;
import com.lefancrm.apicenter.dto.SurveyProductDto;
import com.lefancrm.apicenter.dto.SurveyRiskCaseInfoDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.apicenter.util.DecimalUtil;
import com.lefancrm.apicenter.util.SerialNumberUtil;
import com.lefancrm.apicenter.util.wechatPay.util.MD5Util;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wangwei on 2019-04-23
 * 开票权限管理
 */
@Service
@ApiService(descript = "狄大人API")
public class BackendBillingRoleApiImpl extends BaseServiceImpl implements BackendBillingRoleApi {

    @Autowired
    private BillingApplyCorporationMapper billingApplyCorporationMapper;
    @Autowired
    private BillingApplyCorporationEnumMapper  billingApplyCorporationEnumMapper;
    @Autowired
    private BillingApplyEnumItemMapper  billingApplyEnumItemMapper;
    @Autowired
    private CommonEnumMapper  commonEnumMapper;
    @Autowired
    private BusApplyProductRoleMapper busApplyProductRoleMapper;
    @Autowired
    private BusApplyEnumRoleMapper busApplyEnumRoleMapper;
    @Autowired
    private BusApplyItemRoleMapper busApplyItemRoleMapper;
    @Autowired
    private BusApplyOrgRoleMapper busApplyOrgRoleMapper;
    @Autowired
    private BusApplyCorporationRoleMapper busApplyCorporationRoleMapper;
    @Autowired
    private BillingApplyProductTypeMapper billingApplyProductTypeMapper;
    @Autowired
    private OrgInfoMapper orgInfoMapper;
    @Autowired
    private BusinessRoleMapper businessRoleMapper;
    @Autowired
    private BackendBillingAppyApiImpl backendBillingAppyApi;

    /**
     * 开票权限 list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "开票权限 list", value = "backend-billing-role-list", apiParams = { })
    @Override
    public ApiResponse list(ApiRequest apiReq) {
        //设置分页
        if(apiReq.getString("noPageIndex") == null){
            this.setBackendPageSize(apiReq);
        }
        String surveyCode = apiReq.getString("surveyCode");
        Long currentUserId = getCurrentUserId(apiReq);
        int count = 1;
        List list = new ArrayList();

        //开票权限
        if("applyRole".equals(surveyCode)){
            list = businessRoleMapper.selectAll(apiReq);
            count = businessRoleMapper.selectAllCount(apiReq);
        }
        //开票权限 -- 开票产品类型
        else if("product".equals(surveyCode)){
            List<BillingApplyProductType> lists = billingApplyProductTypeMapper.list(apiReq);
            count = billingApplyProductTypeMapper.listSize(apiReq);
            List<BusApplyProductRole> corpRoles = busApplyProductRoleMapper.listByUserId(currentUserId);
            list = backendBillingAppyApi.auth(lists,corpRoles,"product");
        }else if("setrole".equals(surveyCode)){
            Map<String,Object> map =  new HashMap<String,Object>();
            map.put("leftId",apiReq.getLong("leftId"));
            map.put("roleName",apiReq.getString("roleName"));
            String searchType = apiReq.getString("searchType");
            switch (searchType){
                case "product" : map.put("leftType",1); break;
                case "org" : map.put("leftType",2); break;
                case "corp" : map.put("leftType",3); break;
                case "enum" : map.put("leftType",4); break;
                case "item" : map.put("leftType",5); break;
            }
            list = businessRoleMapper.selectDtoByLeftId(map);
            count = list.size();
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, list);

    }


    @ApiMethod(descript = "获取开票设置授权对象的列表", value = "backend-billing-auth-list", apiParams = { })
    @Override
    public ApiResponse list1(ApiRequest apiRequest) {
        //产品类型product  业务来源org  公司corp  产品enum  项目item
        String searchType = apiRequest.getString("searchType");
        Long currentUserId = getCurrentUserId(apiRequest);
        List list = new ArrayList();
        if ("product".equals(searchType)){//
            list = billingApplyProductTypeMapper.getDtos(apiRequest);
        }else if ("org".equals(searchType)){
            String type = apiRequest.getString("type");
            if(type!=null){
                Map<String ,Object> map  =  new HashMap<>();
                map.put("orgType",1);
                map.put("orgParentid",-1);
                List<OrgInfo> orgInfos = orgInfoMapper.queryOrgList(map);
                if (orgInfos.size() > 0){
                    for (OrgInfo orgInfo : orgInfos) {
                        List orgInfoList = new ArrayList();
                        map.clear();
                        map.put("orgParentid", orgInfo.getId());
//                        map.put("roleId",apiRequest.getString("roleId"));
                        orgInfoList = orgInfoMapper.queryOrgList(map);
                        list.addAll(orgInfoList);
                    }
                }
            }else{
                list = orgInfoMapper.getDtos(apiRequest);
            }
        }else if ("corp".equals(searchType)){
            list = billingApplyCorporationMapper.getDtos(apiRequest);
        }else if ("enum".equals(searchType)){
            String enumCode = apiRequest.getString("enumCode");
            Map<String,Object> map =  new HashMap<String,Object>();
            map.put("enumCode",enumCode);
            map.put("roleId",apiRequest.getString("roleId"));
            list = commonEnumMapper.getEnumDtos(map);
            List<BillingApplyCorporationEnum> enums = billingApplyCorporationEnumMapper.selectByRoleId(apiRequest.getLong("roleId"));
            list = auth(list,enums,searchType);

        }else if ("item".equals(searchType)){
            String enumCode = apiRequest.getString("enumCode");
            Map<String,Object> map =  new HashMap<String,Object>();
            map.put("enumCode",enumCode);
            map.put("roleId",apiRequest.getString("roleId"));
            list = commonEnumMapper.getItemDtos(map);
            List<BillingApplyEnumItem> items = billingApplyEnumItemMapper.selectByRoleId(apiRequest.getLong("roleId"));
            list = auth(list,items,searchType);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, list.size(), list);
    }

    @ApiMethod(descript = "保存开票设置授权对象", value = "backend-billing-auth-save", apiParams = { })
    @Override
    public ApiResponse save(ApiRequest apiRequest) {
        try {
            //产品类型product  业务来源org  公司corp  产品enum  项目item
            String searchType = apiRequest.getString("searchType");
            Long roleId = apiRequest.getLong("roleId");
            String strIds = apiRequest.getString("ids");
            strIds = strIds == null ? "" : strIds;
            String[] ids = strIds.split(",");
            if ("product".equals(searchType)){
                busApplyProductRoleMapper.deleteByRoleId(roleId);
                for (String id : ids) {
                    BusApplyProductRole item = BusApplyProductRole.class.newInstance();
                    item.setRoleId(roleId);
                    item.setProductId(Long.parseLong(id));
                    busApplyProductRoleMapper.insert(item);
                }
            }else if ("org".equals(searchType)){
                busApplyOrgRoleMapper.deleteByRoleId(roleId);
                for (String id : ids) {
                    BusApplyOrgRole item = BusApplyOrgRole.class.newInstance();
                    item.setRoleId(roleId);
                    item.setOrgId(Long.parseLong(id));
                    busApplyOrgRoleMapper.insert(item);
                }
            }else if ("corp".equals(searchType)){
                busApplyCorporationRoleMapper.deleteByRoleId(roleId);
                for (String id : ids) {
                    BusApplyCorporationRole item = BusApplyCorporationRole.class.newInstance();
                    item.setRoleId(roleId);
                    item.setCorporationId(Long.parseLong(id));
                    busApplyCorporationRoleMapper.insert(item);
                }
            }else if ("enum".equals(searchType)){
                busApplyEnumRoleMapper.deleteByRoleId(roleId);
                for (String id : ids) {
                    BusApplyEnumRole item = BusApplyEnumRole.class.newInstance();
                    item.setRoleId(roleId);
                    item.setEnumId(Long.parseLong(id));
                    busApplyEnumRoleMapper.insert(item);
                }
            }else if ("item".equals(searchType)){
                busApplyItemRoleMapper.deleteByRoleId(roleId);
                for (String id : ids) {
                    BusApplyItemRole item = BusApplyItemRole.class.newInstance();
                    item.setRoleId(roleId);
                    item.setItemId(Long.parseLong(id));
                    busApplyItemRoleMapper.insert(item);
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(descript = "保存开票设置授权对象", value = "backend-billing-auth-save-two", apiParams = { })
    @Override
    public ApiResponse saveTwo(ApiRequest apiRequest) {
        try {
            //产品类型product  业务来源org  公司corp  产品enum  项目item
            String searchType = apiRequest.getString("searchType");
            Long leftId = apiRequest.getLong("leftId");
            String strIds = apiRequest.getString("ids");
            strIds = strIds == null ? "" : strIds;
            String[] ids = strIds.split(",");
            if ("product".equals(searchType)){
                busApplyProductRoleMapper.deleteByLeftId(leftId);
                for (String id : ids) {
                    BusApplyProductRole item = BusApplyProductRole.class.newInstance();
                    item.setRoleId(Long.parseLong(id));
                    item.setProductId(leftId);
                    busApplyProductRoleMapper.insert(item);
                }
            }else if ("org".equals(searchType)){
                busApplyOrgRoleMapper.deleteByLeftId(leftId);
                for (String id : ids) {
                    BusApplyOrgRole item = BusApplyOrgRole.class.newInstance();
                    item.setRoleId(Long.parseLong(id));
                    item.setOrgId(leftId);
                    busApplyOrgRoleMapper.insert(item);
                }
            }else if ("corp".equals(searchType)){
                busApplyCorporationRoleMapper.deleteByLeftId(leftId);
                for (String id : ids) {
                    BusApplyCorporationRole item = BusApplyCorporationRole.class.newInstance();
                    item.setRoleId(Long.parseLong(id));
                    item.setCorporationId(leftId);
                    busApplyCorporationRoleMapper.insert(item);
                }
            }else if ("enum".equals(searchType)){
                busApplyEnumRoleMapper.deleteByLeftId(leftId);
                for (String id : ids) {
                    BusApplyEnumRole item = BusApplyEnumRole.class.newInstance();
                    item.setRoleId(Long.parseLong(id));
                    item.setEnumId(leftId);
                    busApplyEnumRoleMapper.insert(item);
                }
            }else if ("item".equals(searchType)){
                busApplyItemRoleMapper.deleteByLeftId(leftId);
                for (String id : ids) {
                    BusApplyItemRole item = BusApplyItemRole.class.newInstance();
                    item.setRoleId(Long.parseLong(id));
                    item.setItemId(leftId);
                    busApplyItemRoleMapper.insert(item);
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }


    /**
     * 筛选 产品  以及项目
     * @param t
     * @param d
     * @param searchType
     * @param <T>
     * @param <D>
     * @return
     */
    private <T,D > T auth(T t,D d,String searchType){
        try {
            if ("enum".equals(searchType)) {
                List<CommonEnumDto> dtos = (List<CommonEnumDto>)t;
                List<BillingApplyCorporationEnum> items = (List<BillingApplyCorporationEnum>)d;
                List<CommonEnumDto> newList = dtos.getClass().newInstance();
                for (CommonEnumDto dto : dtos) {
                    for (BillingApplyCorporationEnum item : items) {
                        if (new Long(dto.getEnumCode()).intValue() == item.getBillingEnumId().intValue()){
                            if (newList.contains(dto)) {
                                continue;
                            }
                            newList.add(dto);
                        }
                    }
                }
                return (T)newList;
            }else if ("item".equals(searchType)){
                List<CommonEnumDto> dtos = (List<CommonEnumDto>)t;
                List<BillingApplyEnumItem> items = (List<BillingApplyEnumItem>)d;
                List<CommonEnumDto> newList = dtos.getClass().newInstance();
                for (CommonEnumDto dto : dtos) {
                    for (BillingApplyEnumItem item : items) {
                        if (new Long(dto.getEnumCode()).intValue() == item.getBillingItemId().intValue()){
                            if (newList.contains(dto)) {
                                continue;
                            }
                            newList.add(dto);
                        }
                    }
                }
                return (T)newList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
