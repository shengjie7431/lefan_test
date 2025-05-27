package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.appapi.CrmCustomerFollowsApi;
import com.lefancrm.apicenter.dao.CrmCaseInfoMapper;
import com.lefancrm.apicenter.dao.CrmCustomerFollowsMapper;
import com.lefancrm.apicenter.dao.CrmCustomerInfoMapper;
import com.lefancrm.apicenter.dto.CrmCustomerFollowsDto;
import com.lefancrm.apicenter.dto.UserSessionDto;
import com.lefancrm.apicenter.model.CrmCaseInfo;
import com.lefancrm.apicenter.model.CrmCustomerFollows;
import com.lefancrm.apicenter.model.CrmCustomerInfo;
import com.lefancrm.apicenter.service.RedisService;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiParam;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ting on 2017/12/19.
 */
@Service
@ApiService(descript = "待办事项API")
public class BackendCrmCustomerFollowsApiImpl extends BaseServiceImpl implements CrmCustomerFollowsApi {

    private static final Logger loger = Logger.getLogger(BackendCrmCustomerFollowsApiImpl.class);
    @Autowired
    private CrmCustomerFollowsMapper crmCustomerFollowsMapper;
    @Autowired
    private CrmCustomerInfoMapper crmCustomerInfoMapper;
    @Autowired
    private RedisService redisService;

	@Autowired
    private CrmCaseInfoMapper crmCaseInfoMapper;


/**
     * 查询跟进状态
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod( descript = "查询跟进状态", value = "backend-query-customer-follows")
    @Override
    public ApiResponse queryCustomerFollows(ApiRequest apiReq) {
        Long customerId = apiReq.getLong("customerId");
        List<CrmCustomerFollows> list = crmCustomerFollowsMapper.selectFollowsByCustomerId(customerId);
        return new ApiResponse(ApiMsgEnum.SUCCESS,list==null?0:list.size(),list);
    }

    /**
     * 添加跟进状态
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod( descript = "添加跟进状态", value = "backend-add-customer-follows")
    @Override
    public ApiResponse addCustomerFollows(ApiRequest apiReq) {
        Long customerId = apiReq.getLong("customerId");
        String customerName = apiReq.getString("customerName");
        Date followTime = DateUtils.parseDate(apiReq.getString("followTime"), "yyyy-MM-dd HH:mm:ss");
        String followAddress = apiReq.getString("followAddress");
        Double longitude = apiReq.getDouble("longitude");
        Double latitude = apiReq.getDouble("latitude");
        Integer followState = apiReq.getInt("followState");
        Date nextFollowTime = DateUtils.parseDate(apiReq.getString("nextFollowTime"),"yyyy-MM-dd HH:mm:ss");
        String followDesc = apiReq.getString("followDesc");
        Integer followType = apiReq.getInt("followType");
        CrmCustomerFollows ccf = new CrmCustomerFollows();
        ccf.setCustomerId(customerId);
        ccf.setCustomerName(customerName);
        ccf.setFollowTime(followTime);
        ccf.setFollowAddress(followAddress);
        ccf.setLongitude(longitude);
        ccf.setLatitude(latitude);
        ccf.setFollowState(followState);
        ccf.setNextFollowTime(nextFollowTime);
        ccf.setFollowDesc(followDesc);
        ccf.setFollowType(followType);
        ccf.setCreateTime(new Date());
        int result = crmCustomerFollowsMapper.insertSelective(ccf);
        if(result>0){
            CrmCaseInfo cci = new CrmCaseInfo();
            cci.setCustomerId(customerId);
            cci.setCaseProgress(followState);
            cci.setUpdateTime(new Date());
            int resultCci = crmCaseInfoMapper.updateByPrimaryCustomerIdSelective(cci);
            CrmCustomerInfo crmCuInfo = new CrmCustomerInfo();
            crmCuInfo.setId(customerId);
            crmCuInfo.setUpdateTime(new Date());
            crmCustomerInfoMapper.updateByPrimaryKeySelective(crmCuInfo);
            if(resultCci>0){
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true,descript = "待办事项客户数量", value = "crm-customer-follows-count", apiParams = {@ApiParam(name = "user_token",descript = "当前用户token(*)")
            ,@ApiParam(name = "nowDate",descript = "当天时间(*)")})
    @Override
    public ApiResponse<Map<String, Object>> getCrmCustomerFollowsCount(ApiRequest apiReq) {
        String nowDate = apiReq.getString("nowDate");
        if (StringUtils.isEmpty(nowDate)) {
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        UserSessionDto sessionDto = redisService.getUserSession(apiReq.getUserToken());
        if(sessionDto == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }

        apiReq.put("ccId", sessionDto.getUserId());
        apiReq.put("followState", "1,2,3");
        int followToday = crmCustomerFollowsMapper.countToFollowToday(apiReq);
       int followOverTime = crmCustomerFollowsMapper.countToFollowOverTime(apiReq);
       apiReq.put("followState", "5");
       int shelve = crmCustomerFollowsMapper.countForFollowStatus(apiReq);
       apiReq.put("followState", "6");
       int giveUp = crmCustomerFollowsMapper.countForFollowStatus(apiReq);

        Map<String, Object> map = new HashMap<String, Object>();
       map.put("followToday", followToday);
       map.put("followOverTime", followOverTime);
       map.put("shelve", shelve);
       map.put("giveUp", giveUp);

        return new ApiResponse<Map<String, Object>>(ApiMsgEnum.SUCCESS, 1, map);
//        return null;
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true,descript = "今日待跟进客户", value = "wait-crm-customer-follows", apiParams = {@ApiParam(name = "user_token",descript = "当前用户token(*)")
            ,@ApiParam(name = "nowDate",descript = "当天时间(*)")})
    @Override
    public ApiResponse<List<CrmCustomerFollowsDto>> getWaitCrmCustomerFollowsList(ApiRequest apiReq) {
        String nowDate = apiReq.getString("nowDate");
        if (StringUtils.isEmpty(nowDate)) {
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        UserSessionDto sessionDto = redisService.getUserSession(apiReq.getUserToken());
        if(sessionDto == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }

        apiReq.put("ccId", sessionDto.getUserId());
        apiReq.put("followState", "1,2,3");
        apiReq.put("nowDate", nowDate);
        List<CrmCustomerFollowsDto> list = crmCustomerInfoMapper.listForFollowStatus(apiReq);

        return new ApiResponse(ApiMsgEnum.SUCCESS,list==null?0:list.size(),list);

    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true,descript = "超时未跟进客户", value = "no-crm-customer-follows", apiParams = {@ApiParam(name = "user_token",descript = "当前用户token(*)")
            ,@ApiParam(name = "nowDate",descript = "当天时间(*)") })
    @Override
    public ApiResponse<List<CrmCustomerFollowsDto>> getNoCrmCustomerFollowsList(ApiRequest apiReq) {
        String nowDate = apiReq.getString("nowDate");
        if (StringUtils.isEmpty(nowDate)) {
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        UserSessionDto sessionDto = redisService.getUserSession(apiReq.getUserToken());
        if(sessionDto == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }

        apiReq.put("ccId", sessionDto.getUserId());
        apiReq.put("followState", "1,2,3");
        apiReq.put("nowDate", nowDate);

        List<CrmCustomerFollowsDto> list = crmCustomerInfoMapper.listForFollowOverTime(apiReq);

        return new ApiResponse(ApiMsgEnum.SUCCESS,list==null?0:list.size(),list);

    }
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true,descript = "已经搁置客户或已放弃客户", value = "alreadyOrGiveup-crm-customer-follows", apiParams = {@ApiParam(name = "user_token",descript = "当前用户token(*)")
            ,@ApiParam(name = "followState",descript = "跟进状态(*)")})
    @Override
    public ApiResponse<List<CrmCustomerFollowsDto>> getAlreadyCrmCustomerFollowsList(ApiRequest apiReq) {
        String followState = apiReq.getString("followState");
        if (StringUtils.isEmpty(followState)) {
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        UserSessionDto sessionDto = redisService.getUserSession(apiReq.getUserToken());
        if(sessionDto == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }

        apiReq.put("ccId", sessionDto.getUserId());
        apiReq.put("followState", followState);

        List<CrmCustomerFollowsDto> list = crmCustomerInfoMapper.listForAlreadyOrGiveupFollowStatus(apiReq);

        return new ApiResponse(ApiMsgEnum.SUCCESS,list==null?0:list.size(),list);
    }

}
