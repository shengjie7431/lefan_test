package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendFrontRoleMenuApi;
import com.lefancrm.apicenter.dao.FrontMenuMapper;
import com.lefancrm.apicenter.dao.SurveyFranchiseeMapper;
import com.lefancrm.apicenter.dao.SurveyInvestigatorMapper;
import com.lefancrm.apicenter.model.FrontMenu;
import com.lefancrm.apicenter.model.FrontRoleMenu;
import com.lefancrm.apicenter.model.SurveyFranchisee;
import com.lefancrm.apicenter.model.SurveyInvestigator;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiParam;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
@ApiService(descript = "后台系统菜单管理")
public class BackendFrontRoleMenuApiImpl extends BaseServiceImpl implements BackendFrontRoleMenuApi {

    @Autowired
    private FrontMenuMapper frontMenuMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "用户菜单列表", value = "backend-sysmenu-listByUserId", apiParams = {@ApiParam(descript = "用户ID", name = "userId")})
    @Override
    public ApiResponse menuListByUserId(ApiRequest apiReq) {
        List<FrontMenu> _results = this.frontMenuMapper.selectTreeList(apiReq);
        SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(apiReq.getLong("userId"));
        if (surveyInvestigator != null){
            SurveyFranchisee surveyFranchisee = surveyFranchiseeMapper.selectByPrimaryKey(surveyInvestigator.getOrgId());
            int busType = surveyFranchisee.getBusType();
            int type = surveyFranchisee.getType();
            int insType = surveyFranchisee.getInsuranceType();
            if ((busType == 1 && type != 1) || (busType == 2 && insType != 1) || (busType == 3 && type != 1 && insType != 1)){
                _results.removeIf(e -> "报销清单".equals(e.getMenuName()));
            }
        }
        return new ApiResponse<List<FrontMenu>>(ApiMsgEnum.SUCCESS, 1, _results);
    }

}
