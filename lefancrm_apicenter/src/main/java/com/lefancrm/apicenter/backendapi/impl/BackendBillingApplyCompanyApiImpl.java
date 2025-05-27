package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendBillingApplyCompanyApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by wangwei on 2018/10/9.
 */
@ApiService(descript = "开票对象")
@Service
public class BackendBillingApplyCompanyApiImpl extends BaseServiceImpl implements BackendBillingApplyCompanyApi {


    @Autowired
    private BillingApplyCompanyMapper billingApplyCompanyMapper;
    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;
    @Autowired
    private BillingApplyCorporationMapper billingApplyCorporationMapper;
    @Autowired
    private BillingApplyCorporationEnumMapper billingApplyCorporationEnumMapper;
    @Autowired
    private BillingApplyEnumItemMapper billingApplyEnumItemMapper;
    @Autowired
    private BackendBillingAppyApiImpl backendBillingAppyApi;
    @Autowired
    private BusApplyCorporationRoleMapper busApplyCorporationRoleMapper;
    @Autowired
    private StaffBudgetCompanyMapper staffBudgetCompanyMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    /**
     * 开票对象list
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "开票对象list" ,value = "backend-billing-apply-company-list")
    @Override
    public ApiResponse<List<BillingApplyCompany>> billingApplyCompanyList(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        List<BillingApplyCompany> billingApplyCompany = billingApplyCompanyMapper.selectList(apiReq);
        int count = billingApplyCompanyMapper.selectListSize(apiReq);

        //狄大人中的开票对象是：SurveyConsignor
        if("survey".equals(apiReq.get("type"))){
            apiReq.remove("type");//因SurveyConsignor表中，有type字段，会影响
            List<SurveyConsignor> consignors = surveyConsignorMapper.list(apiReq);
            count = surveyConsignorMapper.listSize(apiReq);
            return new ApiResponse(ApiMsgEnum.SUCCESS, count, consignors);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, billingApplyCompany);
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId().equals(roleId)){
                return true;
            }
        }
        return false;
    }

    /**
     * 开票公司
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "开票公司list" ,value = "backend-billing-apply-corporation-list")
    @Override
    public ApiResponse<List<BillingApplyCorporation>> billingApplyCorporationList(ApiRequest apiReq){
        Long userId = apiReq.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        //不分页
        int noPageIndex = apiReq.getInt("noPageIndex");
        if(noPageIndex !=1){
            this.setBackendPageSize(apiReq);
        }
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(userId);
        Boolean roleUser = isRoleUser(userRoles, 144L);
        if (roleUser){
            apiReq.put("enums144",144);
        }

        List<BillingApplyCorporation> billingApplyCorporation = billingApplyCorporationMapper.list(apiReq);
        int count = billingApplyCorporationMapper.listSize(apiReq);

        String auth = apiReq.getString("auth");
        if(auth!=null && "1".equals(auth)){
            List<BusApplyCorporationRole> corpRoles = busApplyCorporationRoleMapper.listByUserId(userId);
            billingApplyCorporation = backendBillingAppyApi.auth(billingApplyCorporation,corpRoles,"corp");
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, billingApplyCorporation);
    }
    /**
     * 开票公司详情
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "开票公司详情" ,value = "backend-billing-apply-corporation-details")
    @Override
    public ApiResponse<BillingApplyCorporation> billingApplyCorporationDetails(ApiRequest apiReq){
        Long corporationId = apiReq.getLong("corporationId");//
        StaffBudgetCompany staffBudgetCompany = staffBudgetCompanyMapper.selectByPrimaryKey(corporationId);
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, staffBudgetCompany);
//        BillingApplyCorporation billingApplyCorporation = billingApplyCorporationMapper.selectByPrimaryKey(corporationId);
//        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, billingApplyCorporation);
    }
    /**
     * 公司对应类目list
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "公司对应类目list" ,value = "backend-billing-apply-corporation-enum-list")
    @Override
    public ApiResponse<List<BillingApplyCorporationEnum>> billingApplyCorporationEnumList(ApiRequest apiReq){
        //不分页
        int noPageIndex = apiReq.getInt("noPageIndex");
        if(noPageIndex !=1){
            this.setBackendPageSize(apiReq);
        }
        List<BillingApplyCorporationEnum> billingApplyCorporationEnums = billingApplyCorporationEnumMapper.list(apiReq);
        int count = billingApplyCorporationEnumMapper.listSize(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, billingApplyCorporationEnums);
    }

    /**
     * 类目对应项目list
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "类目对应项目list" ,value = "backend-billing-apply-enum-item-list")
    @Override
    public ApiResponse<List<BillingApplyEnumItem>> billingApplyEnumItemList(ApiRequest apiReq){
        //不分页
        int noPageIndex = apiReq.getInt("noPageIndex");
        if(noPageIndex !=1){
            this.setBackendPageSize(apiReq);
        }
        List<BillingApplyEnumItem> billingApplyEnumItems = billingApplyEnumItemMapper.list(apiReq);
        int count = billingApplyEnumItemMapper.listSize(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, billingApplyEnumItems);
    }
}
