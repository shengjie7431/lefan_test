package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.dto.CaseStepsDTO;
import com.lefancrm.apicenter.model.CaseCenterInfo;
import com.lefancrm.apicenter.model.UserInfo;

import java.util.List;

/**
 * Created by lixianfeng on 2019/5/20.
 */
public interface BackendCaseStepsApi {
    List<CaseStepsDTO> list(CaseCenterInfo caseCenterInfo);
    Boolean insert(CaseCenterInfo caseCenterInfo, String stepCode, UserInfo userInfo, Integer curState, Integer curstateStep);
    Boolean update(CaseCenterInfo caseCenterInfo, UserInfo userInfo, Boolean isCreate, String stepCode, Integer curState, Integer curStateStep);
}
