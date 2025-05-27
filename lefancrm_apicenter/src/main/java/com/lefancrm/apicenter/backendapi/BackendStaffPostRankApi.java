package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.StaffPostRank;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendStaffPostRankApi {
    ApiResponse staffPostRankDel(ApiRequest apiReq);

    ApiResponse staffPostRankAdd(ApiRequest apiReq);

    ApiResponse staffPostRankAddSelective(ApiRequest apiReq);

    ApiResponse queryStaffPostRankById(ApiRequest apiReq);

    ApiResponse updateStaffPostRankByParam(ApiRequest apiReq);

    ApiResponse updateStaffPostRank(ApiRequest apiReq);

    ApiResponse selectStaffPostRankList(ApiRequest apiReq);

    ApiResponse selectStaffPostRankListAll(ApiRequest apiReq);
}
