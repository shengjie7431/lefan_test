package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.dto.TreeData;
import com.lefancrm.apicenter.model.CommonArea;
import com.lefancrm.apicenter.model.OrgInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * 后台管理员API
 * 
 * @author Daniel
 */
public interface BackendOrgApi {


    @SuppressWarnings("rawtypes")
    ApiResponse<List<OrgInfo>> queryOrgList(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    public ApiResponse orgCCTreeData(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    public ApiResponse orgCCTreeDataByOrgId(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    public ApiResponse queryUserListByOrgId(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    public ApiResponse treeDataOrgMoney(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    public ApiResponse queryOrgMoneyListById(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    public ApiResponse queryOrgCCMoneyListById(ApiRequest apiReq);

    @SuppressWarnings("rawtypes")
    ApiResponse getByOrgId(ApiRequest apiReq);

    @SuppressWarnings("rawtypes")
    ApiResponse orgTreeData(ApiRequest apiReq);

    @SuppressWarnings("rawtypes")
    ApiResponse<List<OrgInfo>> selectOrgInfoByRiskOrg(ApiRequest apiReq);
    /*
     *查询机构-父级为1的数据
     */
    ApiResponse searchOrgListByOrgParentId(ApiRequest apiReq);

    @SuppressWarnings("rawtypes")
    ApiResponse<List<OrgInfo>> selectSalesUserByOrgId(ApiRequest apiReq);

    ApiResponse<List<TreeData>> selectAreaAll(ApiRequest apiReq);

    ApiResponse getShowArea(ApiRequest apiReq);

    ApiResponse selectInsuranceCompany(ApiRequest apiReq);
}
