package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.appapi.CrmAccidentInfoApi;
import com.lefancrm.apicenter.dao.CrmAccidentInfoMapper;
import com.lefancrm.apicenter.model.CrmAccidentInfo;
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

/**
 * Created by jun on 2017/12/15.
 */
@Service
@ApiService(descript = "事故信息api")
public class BackendCrmAccidentInfoApiImpl extends BaseServiceImpl implements CrmAccidentInfoApi {


    @Autowired
    private CrmAccidentInfoMapper crmAccidentInfoMapper;

    /**
     * 查询事故信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod( descript = "查询事故信息", value = "backend-query-accident-details")
    @Override
    public ApiResponse queryAccidentDetails(ApiRequest apiReq) {
        Long customerId = apiReq.getLong("customerId");
        CrmAccidentInfo cai = crmAccidentInfoMapper.selectByPrimaryCustomerId(customerId);
        return new ApiResponse(ApiMsgEnum.SUCCESS,cai==null?0:1,cai);
    }

    /**
     * 编辑事故信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod( descript = "编辑事故信息", value = "backend-edit-accident-info")
    @Override
    public ApiResponse editAccidentInfo(ApiRequest apiReq) {
        int number = 2;
        Long customerId = apiReq.getLong("customerId");
        Date accidentDate = DateUtils.parseDate(apiReq.getString("accidentDate"),"yyyy-MM-dd HH:mm:ss");
        String province = apiReq.getString("province");
        Integer provinceId = apiReq.getInt("provinceId");
        String city = apiReq.getString("city");
        Integer cityId = apiReq.getInt("cityId");
        String district = apiReq.getString("district");
        Integer districtId = apiReq.getInt("districtId");
        String accidentAddress = apiReq.getString("accidentAddress");
        Integer accidentCognizance = apiReq.getInt("accidentCognizance");
        String policeTeam = apiReq.getString("policeTeam");
        String policeMan = apiReq.getString("policeMan");
        String policeTel = apiReq.getString("policeTel");
        String insCompulsory = apiReq.getString("insCompulsory");
        String insCommercial = apiReq.getString("insCommercial");
        Double threeQuota = apiReq.getDouble("threeQuota");
        Integer isDeductibles = apiReq.getInt("isDeductibles");
        String driverName = apiReq.getString("driverName");
        String driverTel = apiReq.getString("driverTel");
        Integer isMulti = apiReq.getInt("isMulti");
        Integer isRelief = apiReq.getInt("isRelief");
        String otherDesc = apiReq.getString("otherDesc");
        if(accidentCognizance != null){
           number+=1;
        }
        if(policeTeam != null){
            number+=1;
        }
        if(policeMan != null){
            number+=1;
        }
        if(policeTel != null){
            number+=1;
        }
        if(insCompulsory != null){
            number+=1;
        }
        if(insCommercial != null){
            number+=1;
        }
        if(threeQuota != null){
            number+=1;
        }
        if(isDeductibles != null){
            number+=1;
        }
        if(driverName != null){
            number+=1;
        }
        if(driverTel != null){
            number+=1;
        }
        if(isMulti != null){
            number+=1;
        }
        if(isRelief != null){
            number+=1;
        }
        if(otherDesc != null){
            number+=1;
        }
        Double dataRate = (double)25/15*number;
        CrmAccidentInfo cai = new CrmAccidentInfo();
        cai.setCustomerId(customerId);
        cai.setAccidentDate(accidentDate);
        cai.setProvince(province);
        cai.setProvinceId(provinceId);
        cai.setCityId(cityId);
        cai.setCity(city);
        cai.setDistrict(district);
        cai.setDistrictId(districtId);
        cai.setAccidentAddress(accidentAddress);
        cai.setAccidentCognizance(accidentCognizance);
        cai.setPoliceTeam(policeTeam);
        cai.setPoliceMan(policeMan);
        cai.setPoliceTel(policeTel);
        cai.setInsCompulsory(insCompulsory);
        cai.setInsCommercial(insCommercial);
        cai.setThreeQuota(threeQuota);
        cai.setIsDeductibles(isDeductibles);
        cai.setDriverName(driverName);
        cai.setDriverTel(driverTel);
        cai.setIsMulti(isMulti);
        cai.setIsRelief(isRelief);
        cai.setOtherDesc(otherDesc);
        cai.setDataRate(dataRate);
        int result = crmAccidentInfoMapper.updateByPrimaryCustomerIdSelective(cai);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
