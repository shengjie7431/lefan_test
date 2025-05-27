package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCaseCenterInfoFollowApi;
import com.lefancrm.apicenter.dao.CaseCenterExtendMapper;
import com.lefancrm.apicenter.dao.CaseCenterInfoFollowMapper;
import com.lefancrm.apicenter.dao.CaseCenterInfoMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.CaseCenterExtend;
import com.lefancrm.apicenter.model.CaseCenterInfo;
import com.lefancrm.apicenter.model.CaseCenterInfoFollow;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wangwei on 2018/5/15.
 */
@ApiService(descript = "跟踪信息列表API")
@Service
public class BackendCaseCenterInfoFollowApiImpl extends BaseServiceImpl implements BackendCaseCenterInfoFollowApi {
    @Autowired
    private CaseCenterInfoFollowMapper caseCenterInfoFollowMapper;
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    @Autowired
    private CaseCenterExtendMapper caseCenterExtendMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    /**
     * 案件跟踪列表
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "案件跟踪列表" ,value = "backend-case-center-info-follow-list")
    @Override
    public ApiResponse<List<CaseCenterInfoFollow>> caseCenterInfoFollowList(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        List<CaseCenterInfoFollow> list = caseCenterInfoFollowMapper.selectCaseCenterInfoFollowList(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,null,list);
    }

    /**
     * 根据‘案件编号’查询案件跟踪信息
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据‘案件编号’查询案件跟踪信息" ,value = "backend-case-center-info-follow-by-caseid")
    @Override
    public ApiResponse<List<CaseCenterInfoFollow>> searchCaseCenterInfoFollowByCaseId(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        List<CaseCenterInfoFollow> list = caseCenterInfoFollowMapper.searchCaseCenterInfoFollowByCaseId(apiReq);
        int count = caseCenterInfoFollowMapper.searchCaseCenterInfoFollowSizeByCaseId(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }


    /**
     * 根据‘id’查询案件信息
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据‘id’查询案件信息" ,value = "backend-case-center-info-by-id")
    @Override
    public ApiResponse<CaseCenterInfo> searchCaseCenterInfoById(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(apiReq.getLong("caseId"));
        return  new ApiResponse(ApiMsgEnum.SUCCESS,1,caseCenterInfo);
    }


    /**
     * 保存案件跟踪信息
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "保存案件跟踪信息", value = "backend-case-center-info-follow-save")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse caseCenterInfoFollowSave(ApiRequest apiReq) {
        String choose = apiReq.getString("choose");
        String type = apiReq.getString("type");
        Long operatorId = apiReq.getLong("operatorId");
        String followDesc = apiReq.getString("followDesc");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(operatorId);
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(apiReq.getLong("caseId"));
        CaseCenterExtend caseCenterExtend =  caseCenterExtendMapper.selectByPrimaryKey(apiReq.getLong("caseId"));
        //修改历史跟踪记录结束
        updateListByGradationStateAndCaseId(apiReq, choose);
        if ("add".equals(type)){
            //增加一条跟进中的
            CaseCenterInfoFollow caseCenterInfoFollow = addCaseCenterInfoFollow(caseCenterInfo,userInfo,choose);
            //跟踪说明
            caseCenterInfoFollow.setFollowDesc(followDesc);
            //下次跟踪时间
            Date nextFollowTime = DateUtils.parseDate(apiReq.getString("nextFollowTime"), "yyyy-MM-dd HH:mm:ss");
            caseCenterInfoFollow.setNextFollowTime(nextFollowTime);
            //跟踪状态 -- 跟进中
            caseCenterInfoFollow.setFollowType(1);
            //更新案件中心附属表
            if (caseCenterExtend != null){
                if (caseCenterExtend != null){
                    switch (choose){
                        case "PG": caseCenterExtend.setAssessFlowState(1L); break;
                        case "SP": caseCenterExtend.setClaimFlowState(1L); break;
                        case "SS": caseCenterExtend.setLegalFlowState(1L); break;
                        case "KF": caseCenterExtend.setCustomerFlowState(1L); break;
                    }
                    caseCenterExtendMapper.updateByPrimaryKeySelective(caseCenterExtend);
                }
            }
            int result = caseCenterInfoFollowMapper.insertSelective(caseCenterInfoFollow);
            if(result > 0){
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
        }else if ("stop".equals(type)){
            if (caseCenterExtend != null){
                switch (choose){
                    case "PG": caseCenterExtend.setAssessFlowState(2L); break;
                    case "SP": caseCenterExtend.setClaimFlowState(2L); break;
                    case "SS": caseCenterExtend.setLegalFlowState(2L); break;
                    case "KF": caseCenterExtend.setCustomerFlowState(2L); break;
                }
                caseCenterExtendMapper.updateByPrimaryKeySelective(caseCenterExtend);
            }
            CaseCenterInfoFollow caseCenterInfoFollow = new CaseCenterInfoFollow();
            caseCenterInfoFollow.setCaseId(caseCenterInfo.getId());
            caseCenterInfoFollow.setFollowBy(userInfo.getUserName());
            caseCenterInfoFollow.setFollowById(userInfo.getUserId());
            caseCenterInfoFollow.setFollowTime(new Date());
            //下一次跟踪时间
            caseCenterInfoFollow.setNextFollowTime(null);
            //保存案件信息
            caseCenterInfoFollow.setCaseNo(caseCenterInfo.getCaseNo());
            caseCenterInfoFollow.setCaseType(caseCenterInfo.getType());
            switch (choose){
                case "PG": caseCenterInfoFollow.setGradationState(2);break;
                case "SP": caseCenterInfoFollow.setGradationState(3);break;
                case "SS": caseCenterInfoFollow.setGradationState(6);break;
                case "KF": caseCenterInfoFollow.setGradationState(1);break;
            }
            caseCenterInfoFollow.setCaseState(caseCenterInfo.getCaseState());
            caseCenterInfoFollow.setCaseTitle(caseCenterInfo.getCaseTitle());
            caseCenterInfoFollow.setCaseName(caseCenterInfo.getCaseName());
            caseCenterInfoFollow.setOrgName(caseCenterInfo.getOrgName());
            caseCenterInfoFollow.setOrgId(caseCenterInfo.getOrgId());
            caseCenterInfoFollow.setFollowType(2);
            caseCenterInfoFollow.setFollowDesc("[已结束]".concat(followDesc));
            caseCenterInfoFollowMapper.insertSelective(caseCenterInfoFollow);
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }


    private int updateListByGradationStateAndCaseId(ApiRequest apiReq, String choose) {
        //修改历史跟踪记录结束 ,增加一条跟进中的
        Map<String, Object> map = new HashMap<String, Object>();
        switch (choose){
            case "PG": map.put("gradationState", 2);break;
            case "SP": map.put("gradationState", 3);break;
            case "SS": map.put("gradationState", 6);break;
            case "KF": map.put("gradationState", 1);break;
        }
        map.put("caseId",apiReq.getLong("caseId"));
        map.put("followType",2);
        caseCenterInfoFollowMapper.updateListByGradationStateAndCaseId(map);
        return 0;
    }

    private CaseCenterInfoFollow addCaseCenterInfoFollow(CaseCenterInfo caseCenterInfo,UserInfo userInfo,String choose) {
        CaseCenterInfoFollow caseCenterInfoFollow = new CaseCenterInfoFollow();
        caseCenterInfoFollow.setCaseId(caseCenterInfo.getId());
        caseCenterInfoFollow.setFollowBy(userInfo.getUserName());
        caseCenterInfoFollow.setFollowById(userInfo.getUserId());
        caseCenterInfoFollow.setFollowTime(new Date());

        //保存案件信息
        caseCenterInfoFollow.setCaseNo(caseCenterInfo.getCaseNo());
        caseCenterInfoFollow.setCaseType(caseCenterInfo.getType());
        switch (choose){
            case "PG": caseCenterInfoFollow.setGradationState(2);break;
            case "SP": caseCenterInfoFollow.setGradationState(3);break;
            case "SS": caseCenterInfoFollow.setGradationState(6);break;
            case "KF": caseCenterInfoFollow.setGradationState(1);break;
        }

        caseCenterInfoFollow.setCaseState(caseCenterInfo.getCaseState());
        caseCenterInfoFollow.setCaseTitle(caseCenterInfo.getCaseTitle());
        caseCenterInfoFollow.setCaseName(caseCenterInfo.getCaseName());
        caseCenterInfoFollow.setOrgName(caseCenterInfo.getOrgName());
        caseCenterInfoFollow.setOrgId(caseCenterInfo.getOrgId());
        return caseCenterInfoFollow;
    }
}
