package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendStaffOrganApi;
import com.lefancrm.apicenter.dao.StaffOrganMapper;
import com.lefancrm.apicenter.dao.StaffOrganProductMapper;
import com.lefancrm.apicenter.dao.SurveyPayInfoMapper;
import com.lefancrm.apicenter.dao.ThinkDataOrgProductMapper;
import com.lefancrm.apicenter.dto.think.DetailDTO;
import com.lefancrm.apicenter.dto.think.SRDTO;
import com.lefancrm.apicenter.dto.think.YWDTO;
import com.lefancrm.apicenter.model.StaffOrgan;
import com.lefancrm.apicenter.model.ThinkDataDetail;
import com.lefancrm.apicenter.model.ThinkDataOrgProduct;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
//import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ApiService(descript = "人事管理 - 机构API")
public class BackendStaffOrganApiImpl extends BaseServiceImpl implements BackendStaffOrganApi {
    @Autowired
    private StaffOrganMapper staffOrganMapper;
    @Autowired
    private StaffOrganProductMapper staffOrganProductMapper;
    @Autowired
    private ThinkDataOrgProductMapper thinkDataOrgProductMapper;
    @Autowired
    private SurveyPayInfoMapper surveyPayInfoMapper;

    @ApiMethod(descript = "获取机构剩余报销金额", value = "backend-staff-organ-ajax-data", apiParams = { })
    @Override
    public ApiResponse ajaxData(ApiRequest apiRequest) {
        Long staffOrgId = apiRequest.getLong("staffOrgId");
        try {
            Date curTime = new SimpleDateFormat("yyyy-MM-dd").parse(apiRequest.getString("time"));
            Double lastReimbMoney = lastReimbMoney(staffOrgId,curTime);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,lastReimbMoney);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    /**
     * 获取机构剩余报销金额
     * @param staffOrgId  机构ID
     * @param curTime    本月时间(创建单的时间)，非当前操作时间
     * @return
     */
    public Double lastReimbMoney(Long staffOrgId, Date curTime){
        //业务主营：上月业务核算收入 + 【本年业务核算收入】+ 阳性率奖励-阳性率扣减+个案减损奖励-支出成本合计
        // 本年业务核算收入(不含当月)= 案件核算收入+开票回款收入
        StaffOrgan staffOrgan = staffOrganMapper.selectByPrimaryKey(staffOrgId);
        if (staffOrgan == null) {
            return 0D;
        }
        Integer organAttribute = staffOrgan.getOrganAttribute();
        if (organAttribute == null) {
            return 0D;
        }
        Map<String,Object> paramMap = new HashMap<>();
        if (organAttribute == 1){//业务主营
            //本年核算收入（不含当月）
            paramMap.put("startTime",DateUtils.getCurYear(curTime));
            paramMap.put("endTime",DateUtils.getUpMonthLast(curTime));
            paramMap.put("staffOrgId",staffOrgan.getId());
            paramMap.put("searchTimeType",1);
            List<YWDTO> ywData = staffOrganProductMapper.ywData(paramMap);
            List<SRDTO> accData = initZero(staffOrganProductMapper.srAccData(paramMap));
            List<ThinkDataOrgProduct> thinkData = initZero(thinkDataOrgProductMapper.thinkData(paramMap));
            List<DetailDTO> zc1Data = initZero(staffOrganProductMapper.zc1Data(paramMap));//个案减损奖励
            paramMap.put("staffOrgId",staffOrgId);
            Double curYearZcMoney = surveyPayInfoMapper.zcMoney(paramMap);
            Double ywMoney = ywData.stream().filter(p -> p.getOrgId().intValue() == staffOrgId.intValue()).mapToDouble(YWDTO :: getAccMoney).sum();//保司互助反欺诈案件的核算收入
            Double accMoney = accData.stream().filter(p -> p.getOrgId().intValue() == staffOrgId.intValue()).mapToDouble(SRDTO :: getAccMoney).sum();//回款收入
            Double thinkMoney1 = thinkData.stream().filter(p -> p.getOrgId().intValue() == staffOrgId.intValue()).mapToDouble(ThinkDataOrgProduct :: getClaimSunAddmony).sum();//阳性奖励
            Double thinkMoney2 = thinkData.stream().filter(p -> p.getOrgId().intValue() == staffOrgId.intValue()).mapToDouble(ThinkDataOrgProduct :: getClaimSunSubmoney).sum();//阳性扣减
            Double zcMoney = zc1Data.stream().filter(p -> p.getOrgId().intValue() == staffOrgId.intValue()).mapToDouble(DetailDTO :: getJx9).sum();//个案减损奖励
            Double curYearMoney = ywMoney + accMoney +thinkMoney1 - thinkMoney2 + zcMoney - curYearZcMoney;

            //上月核算收入
            paramMap.put("startTime",DateUtils.getUpMonthFirst(curTime));
            paramMap.put("endTime",DateUtils.getUpMonthLast(curTime));
            ywData = staffOrganProductMapper.ywData(paramMap);
            accData = initZero(staffOrganProductMapper.srAccData(paramMap));
            thinkData = initZero(thinkDataOrgProductMapper.thinkData(paramMap));
            zc1Data = initZero(staffOrganProductMapper.zc1Data(paramMap));//个案减损奖励
            ywMoney = ywData.stream().filter(p -> p.getOrgId().intValue() == staffOrgId.intValue()).mapToDouble(YWDTO :: getAccMoney).sum();//保司互助反欺诈案件的核算收入
            accMoney = accData.stream().filter(p -> p.getOrgId().intValue() == staffOrgId.intValue()).mapToDouble(SRDTO :: getAccMoney).sum();//回款收入
            thinkMoney1 = thinkData.stream().filter(p -> p.getOrgId().intValue() == staffOrgId.intValue()).mapToDouble(ThinkDataOrgProduct :: getClaimSunAddmony).sum();//阳性奖励
            thinkMoney2 = thinkData.stream().filter(p -> p.getOrgId().intValue() == staffOrgId.intValue()).mapToDouble(ThinkDataOrgProduct :: getClaimSunSubmoney).sum();//阳性扣减
            zcMoney = zc1Data.stream().filter(p -> p.getOrgId().intValue() == staffOrgId.intValue()).mapToDouble(DetailDTO :: getJx9).sum();//个案减损奖励
            Double upAccMoney = ywMoney + accMoney +thinkMoney1 - thinkMoney2 + zcMoney;

            //当月支出
            paramMap.put("startTime",DateUtils.getCurMonthFirst(curTime));
            paramMap.put("endTime",DateUtils.getCurMonthLast(curTime));
            Double curZcMoney = surveyPayInfoMapper.zcMoney(paramMap);
            return upAccMoney + curYearMoney - curZcMoney;
        }else{
            paramMap.put("startTime",DateUtils.getCurYear(curTime));
            paramMap.put("endTime",DateUtils.getUpMonthLast(curTime));
            Double accMoney = 0D;
            if (organAttribute == 2){
                List<ThinkDataDetail> thinkData = initZero(thinkDataOrgProductMapper.thinkDataDetail(paramMap));//数据录入 部门核算收入
                accMoney = thinkData.stream().filter(p -> p.getOrgId().intValue() == staffOrgId.intValue()).mapToDouble(ThinkDataDetail :: getDepAccMony).sum();
            }else {
                List<ThinkDataOrgProduct> thinkData = initZero(thinkDataOrgProductMapper.thinkData(paramMap));//
                accMoney = thinkData.stream().filter(p -> p.getOrgId().intValue() == staffOrgId.intValue()).mapToDouble(ThinkDataOrgProduct :: getBusAccMony).sum();
            }
            paramMap.put("staffOrgId",staffOrgId);
            Double curYearZcMoney = surveyPayInfoMapper.zcMoney(paramMap);
            return accMoney - curYearZcMoney;
        }
    }

    private <T> List<T> initZero(List<T> data){
        for (T item : data) {
            initZero(item);
        }
        return data;
    }
    private <T> T initZero(T item){
        Field[] declaredFields = item.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if (Double.class.equals(declaredField.getType())) {
                try {
                    if (declaredField.get(item) == null) {
                        declaredField.set(item,0D);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return item;
    }
}
