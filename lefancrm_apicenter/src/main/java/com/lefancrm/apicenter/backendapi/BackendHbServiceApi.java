package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendHbServiceApi {
    public ApiResponse hbOrgList(ApiRequest apiRequest);
    public ApiResponse saveHbOrg(ApiRequest apiRequest);
    public ApiResponse deleteHbOrg(ApiRequest apiRequest);
    public ApiResponse editHbOrg(ApiRequest apiRequest);
    public ApiResponse queryHbOrgById(ApiRequest apiRequest);

    public ApiResponse hbUserList(ApiRequest apiRequest);
    public ApiResponse deleteHbUser(ApiRequest apiRequest);
    public ApiResponse queryHbuserById(ApiRequest apiRequest);


    public ApiResponse hbRecordList(ApiRequest apiRequest);
}
