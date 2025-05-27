package com.lefancrm.apicenter.appapi.impl;

import com.lefancrm.apicenter.appapi.UserInfoApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.UserSessionDto;
import com.lefancrm.apicenter.model.ActivityDayReport;
import com.lefancrm.apicenter.model.SaleMoneyReport;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.RedisService;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiParam;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ting on 2017/12/18.
 */
@Service
@ApiService(descript = "用户基本信息服务")
public class UserInfoApiImpl extends BaseServiceImpl implements UserInfoApi {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserPromotedMapper userPromotedMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private SaleGoalMapper saleGoalMapper;
    @Autowired
    private ActivityDayReportMapper activityDayReportMapper;
    @Autowired
    private SaleMoneyReportMapper saleMoneyReportMapper;
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true,descript = "修改CRM用户的个人信息", value = "update-CRMuser-info", apiParams = {@ApiParam(name = "user_token",descript = "当前用户token(*)"),@ApiParam(name = "userId",descript = "用户ID(*)")   })
    @Override
    public ApiResponse updateSettingUserInfo(ApiRequest apiReq) {
        int result = userInfoMapper.updateSettingUserInfo(apiReq);
        if(result>0){
            String province = apiReq.getString("userProvince");
            if(province !=null && !"".equals(province)){
                //在修改个人资料的同时如果修改了地区，同步更新推广人的地区信息
                userPromotedMapper.updateUserPromotedRegion(apiReq);
            }
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(Long.parseLong(apiReq.get("userId") + ""));
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,userInfo);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true,descript = "用户个人业绩", value = "select-my-sale", apiParams = {@ApiParam(name = "user_token",descript = "当前用户token(*)"),@ApiParam(name = "month",descript = "月份")
            ,@ApiParam(name = "year",descript = "年份") })
    @Override
    public ApiResponse selectMySale(ApiRequest apiReq) {
        UserSessionDto sessionDto = redisService.getUserSession(apiReq.getUserToken());
        if(sessionDto == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        StringBuffer sb = new StringBuffer();
        sb.append(apiReq.get("year"));
        sb.append("-");
        sb.append(apiReq.get("month"));
        Date salesDate = DateUtils.parseDate(sb.toString(), "yyyy-MM");
        apiReq.put("salesDate",salesDate);

        // 根据用户id查询用户目标
        apiReq.put("objectType","2");
        apiReq.put("objectId",sessionDto.getUserId());
        apiReq.put("userId",sessionDto.getUserId());
        //目标金额
        int saleGold = saleGoalMapper.querySaleGoalGold(apiReq);
        //实际金额
       // int saleAmount = saleMoneyReportMapper.querySaleAmount(apiReq);
        int saleAmount = activityDayReportMapper.queryMeSaleAmountGold(apiReq);
        //超过人数
        //int outNumber = saleMoneyReportMapper.queryOutNumberByUserId(apiReq);
        int outNumber = activityDayReportMapper.queryOutNumberByUserId(apiReq);
        //机构名称
       // SaleMoneyReport smr = saleMoneyReportMapper.queryOrgNameByUserId(apiReq);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(sessionDto.getUserId());
//        ActivityDayReport activityDayReport = activityDayReportMapper.queryMeSaleFunnel(apiReq);
        String orgName = userInfo.getOrgName();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("saleGoal", saleGold);
        map.put("saleAmount", saleAmount);
        map.put("outNumber", outNumber);
        map.put("orgName", orgName);


        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }
}
