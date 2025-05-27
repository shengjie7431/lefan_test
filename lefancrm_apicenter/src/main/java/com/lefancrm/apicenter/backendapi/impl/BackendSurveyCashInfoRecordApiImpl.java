package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyBusinessTypeApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyCashInfoRecordApi;
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

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangwei on 2019-03-14
 * “提现数据”数据管理
 */
@Service
@ApiService(descript = "提现数据API")
public class BackendSurveyCashInfoRecordApiImpl extends BaseServiceImpl implements BackendSurveyCashInfoRecordApi {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private SurveyCashInfoMapper surveyCashInfoMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    /**
     * 提现状态
     */
    @ApiMethod(descript = "提现状态" ,value = "backend-survey-cash-info-record-state")
    @Override
    public ApiResponse cashInfoState(ApiRequest apiReq) {

        String cashInfoState="";

        Long userId = apiReq.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        SurveyInvestigator investigator = surveyInvestigatorMapper.selectByUserId(userId);
        List<BusUserRole> roles = busUserRoleMapper.orgUserRoleList(userInfo.getUserId());

        Boolean cashMan = isRoleUser(roles,61L);//提现角色

        //cashInfoState：1、佣金提现；2、本月已提现；3、每月1号-7号开放体现，4、随时可提现  5、佣金提现（有未到账）
        //提现角色可以随时提现，不受时间、次数的限制
        if(cashMan){
            cashInfoState = "4";
        }else{
            //判断当前时间是否在每月的1号--7号之间
            Boolean isTime = isEffectiveDate();
            if(!isTime){
                cashInfoState = "3";//已过提现时间
            }else{
                //当月是否有提现记录
                Map<String,Object> map = new HashMap<>();
                map.put("franchiseeId",investigator.getOrgId());
                SimpleDateFormat simpleDateFormatMonth = new SimpleDateFormat("yyyy-MM");
                map.put("cashDate",simpleDateFormatMonth.format(new Date()));
                List<SurveyCashInfo> thisMonthInfo = surveyCashInfoMapper.selectByDate(map);

                if (thisMonthInfo != null && thisMonthInfo.size() == 0){
                    //查询有没有历史提现列表
                    map.clear();
                    map.put("franchiseeId",investigator.getOrgId());
                    List<SurveyCashInfo> historyInfo = surveyCashInfoMapper.selectByDate(map);
                    if (historyInfo!=null && historyInfo.size() > 0){ //有历史提现
                        map.clear();
                        map.put("franchiseeId",investigator.getOrgId());
                        map.put("confirmAccountState",1);
                        List<SurveyCashInfo> accountInfo = surveyCashInfoMapper.selectByDate(map);
                        if(accountInfo!=null && accountInfo.size() > 0){ //历史到账有 未确认的
                            cashInfoState = "5";;
                        }else{
                            cashInfoState = "1";
                        }
                    }else{
                        cashInfoState = "1";
                    }
                }else{
                    cashInfoState = "2";
                }
            }
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,null,cashInfoState);
    }


    //时间判断
    private Boolean isEffectiveDate() {
        Date date=new Date();
        Calendar ca=Calendar.getInstance();
        ca.setTime(date);

        int a=ca.get(Calendar.DAY_OF_MONTH);
        if (a>=1 && a <=7 ) {
            return true;
        }else{
            return false;
        }
    }

    //角色判断
    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId() == roleId){
                return true;
            }
        }
        return false;
    }
}
