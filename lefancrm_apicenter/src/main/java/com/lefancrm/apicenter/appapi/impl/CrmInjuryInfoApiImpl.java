package com.lefancrm.apicenter.appapi.impl;

import com.lefancrm.apicenter.appapi.CrmInjuryInfoApi;
import com.lefancrm.apicenter.dao.CrmInjuryInfoMapper;
import com.lefancrm.apicenter.model.CrmInjuryInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jun on 2017/12/15.
 */
@Service
@ApiService(descript = "伤情信息api")
public class CrmInjuryInfoApiImpl extends BaseServiceImpl implements CrmInjuryInfoApi{

    @Autowired
    private CrmInjuryInfoMapper crmInjuryInfoMapper;

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "查询伤情信息", value = "query-injury-info")
    @Override
    public ApiResponse queryInjuryInfo(ApiRequest apiReq) {
        Long customerId = apiReq.getLong("customerId");
        CrmInjuryInfo cii = crmInjuryInfoMapper.selectByPrimaryCustomerId(customerId);
        return new ApiResponse(ApiMsgEnum.SUCCESS,cii==null?0:1,cii);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "编辑伤情信息", value = "edit-injury-info")
    @Override
    public ApiResponse editInjuryInfo(ApiRequest apiReq) {
        int number = 5;
        Long customerId = apiReq.getLong("customerId");
        String injuryName = apiReq.getString("injuryName");
        Double usedMedicalFee = apiReq.getDouble("usedMedicalFee");
        Double oweMedicalFee = apiReq.getDouble("oweMedicalFee");
        Double neededMedicalFee = apiReq.getDouble("neededMedicalFee");
        Integer financingType = apiReq.getInt("financingType");
        String visHospital = apiReq.getString("visHospital");
        Integer isInhospital = apiReq.getInt("isInhospital");
        Integer isOperation = apiReq.getInt("isOperation");
        String hospitalDepartments = apiReq.getString("hospitalDepartments");
        Integer bedNumber = apiReq.getInt("bedNumber");
        Integer hospitalNumber = apiReq.getInt("hospitalNumber");
        String doctor = apiReq.getString("doctor");
        String doctorTel = apiReq.getString("doctorTel");
        String nurse = apiReq.getString("nurse");
        String nurseTel = apiReq.getString("nurseTel");
        String otherDesc = apiReq.getString("otherDesc");
        if(oweMedicalFee != null){
            number+=1;
        }
        if(neededMedicalFee != null){
            number+=1;
        }
        if(visHospital != null){
            number+=1;
        }
        if(hospitalDepartments != null){
            number+=1;
        }
        if(bedNumber != null){
            number+=1;
        }
        if(hospitalNumber != null){
            number+=1;
        }
        if(doctor != null){
            number+=1;
        }
        if(doctorTel != null){
            number+=1;
        }
        if(nurse != null){
            number+=1;
        }
        if(nurseTel != null){
            number+=1;
        }
        if(otherDesc != null){
            number+=1;
        }
        Double dataRate = (double)25/16*number;
        CrmInjuryInfo cii = new CrmInjuryInfo();
        cii.setCustomerId(customerId);
        cii.setInjuryName(injuryName);
        cii.setUsedMedicalFee(usedMedicalFee);
        cii.setOweMedicalFee(oweMedicalFee);
        cii.setNeededMedicalFee(neededMedicalFee);
        cii.setFinancingType(financingType);
        cii.setVisHospital(visHospital);
        cii.setIsInhospital(isInhospital);
        cii.setIsOperation(isOperation);
        cii.setHospitalDepartments(hospitalDepartments);
        cii.setBedNumber(bedNumber);
        cii.setHospitalNumber(hospitalNumber);
        cii.setDoctor(doctor);
        cii.setDoctorTel(doctorTel);
        cii.setNurse(nurse);
        cii.setNurseTel(nurseTel);
        cii.setOtherDesc(otherDesc);
        cii.setDataRate(dataRate);
        int result = crmInjuryInfoMapper.updateByPrimaryCustomerIdSelective(cii);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
