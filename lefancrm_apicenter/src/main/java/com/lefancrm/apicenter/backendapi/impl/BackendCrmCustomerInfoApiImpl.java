package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONObject;
import com.lefancrm.apicenter.backendapi.BackendCrmAccidentInfoApi;
import com.lefancrm.apicenter.backendapi.BackendCrmCaseInfoApi;
import com.lefancrm.apicenter.backendapi.BackendCrmCustomerInfoApi;
import com.lefancrm.apicenter.backendapi.BackendCrmInjuryInfoApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.CciListDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.FileUtils;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiParam;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jun on 2017/12/29.
 */
@Service
@ApiService(descript = "客户管理")
public class BackendCrmCustomerInfoApiImpl extends BaseServiceImpl implements BackendCrmCustomerInfoApi{


    @Autowired
    private CrmCustomerInfoMapper crmCustomerInfoMapper;

    @Autowired
    private CrmAccidentInfoMapper crmAccidentInfoMapper;

    @Autowired
    private CrmCaseInfoMapper crmCaseInfoMapper;

    @Autowired
    private CrmInjuryInfoMapper crmInjuryInfoMapper;

    @Autowired
    private BackendCrmInjuryInfoApiImpl backendCrmInjuryInfoApi;

    @Autowired
    private CrmCustomerFollowsMapper crmCustomerFollowsMapper;

    @Autowired
    private BackendCrmAccidentInfoApiImpl backendCrmAccidentInfoApi;

    @Autowired
    private BackendCrmCaseInfoApiImpl backendCrmCaseInfoApi;

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "客户列表", value = "backend-cci-list", apiParams = {@ApiParam(descript = "用户ID", name = "userId")})
    @Override
    public ApiResponse list(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
        List<CciListDto> list = crmCustomerInfoMapper.backendList(apiReq);
        int count = crmCustomerInfoMapper.backendListCount(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "客户列表", value = "backend-cci-customerPanorama", apiParams = {@ApiParam(descript = "用户ID", name = "userId")})
    @Override
    public ApiResponse customerPanorama(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        CrmCustomerInfo cci = crmCustomerInfoMapper.selectByPrimaryKey(id);
        CrmInjuryInfo cii = crmInjuryInfoMapper.selectByPrimaryCustomerId(id);
        CrmAccidentInfo cai = crmAccidentInfoMapper.selectByPrimaryCustomerId(id);
        CrmCaseInfo caseInfo = crmCaseInfoMapper.selectCaseByCustomerId(id);
        CrmCustomerFollows ccf = crmCustomerFollowsMapper.selectByPrimaryCustomerId(id);
        HashMap<String,Object> map = new HashMap<>();
        map.put("cci",cci);
        map.put("cii",cii);
        map.put("cai",cai);
        map.put("ccf",ccf);
        map.put("accidentDate", DateUtils.DateToStr(cai.getAccidentDate(),"yyyy-MM-dd HH:mm:ss"));
        map.put("firstVisitTime",DateUtils.DateToStr(caseInfo.getFirstVisitTime(),"yyyy-MM-dd HH:mm:ss"));
        map.put("nextFollowTime",DateUtils.DateToStr(ccf.getNextFollowTime(),"yyyy-MM-dd HH:mm:ss"));
        map.put("caseInfo",caseInfo);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "客户列表", value = "backend-cci-caseInformation", apiParams = {@ApiParam(descript = "用户ID", name = "userId")})
    @Override
    public ApiResponse caseInformation(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        CrmCaseInfo cci = crmCaseInfoMapper.selectCaseByCustomerId(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS,cci==null?0:1,cci);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "客户列表", value = "backend-cci-injuredInformation", apiParams = {@ApiParam(descript = "用户ID", name = "userId")})
    @Override
    public ApiResponse injuredInformation(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        CrmCustomerInfo cci = crmCustomerInfoMapper.selectByPrimaryKey(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS,cci==null?0:1,cci);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "客户列表", value = "backend-cci-injuryInformation", apiParams = {@ApiParam(descript = "用户ID", name = "userId")})
    @Override
    public ApiResponse injuryInformation(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        CrmInjuryInfo cii = crmInjuryInfoMapper.selectByPrimaryCustomerId(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS,cii ==null?0:1,cii);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "客户列表", value = "backend-cci-accidentInformation", apiParams = {@ApiParam(descript = "用户ID", name = "userId")})
    @Override
    public ApiResponse accidentInformation(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        CrmAccidentInfo cai = crmAccidentInfoMapper.selectByPrimaryCustomerId(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS,cai==null?0:1,cai);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "保存案件信息", value = "backend-cci-caseInformationSave", apiParams = {@ApiParam(descript = "用户ID", name = "userId")})
    @Override
    public ApiResponse caseInformationSave(ApiRequest apiReq) {
        System.out.println(apiReq);
        return null;
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "编辑客户信息", value = "backend-edit-customer-info")
    @Override
    public ApiResponse editCustomerInfo(ApiRequest apiReq) {
        int number = 6;
        Long id = apiReq.getLong("id");
        String userName = apiReq.getString("userName");  //*
        Integer sex = apiReq.getInt("sex");              //*
        Integer age = apiReq.getInt("age");              //*
        String userPhone = apiReq.getString("userPhone");//*
        String province = apiReq.getString("province");
        Integer provinceId = apiReq.getInt("provinceId");
        String city = apiReq.getString("city");
        Integer cityId = apiReq.getInt("cityId");
        String district = apiReq.getString("district");
        Integer districtId = apiReq.getInt("districtId");
        String familyAddress = apiReq.getString("familyAddress"); //*
        Integer households = apiReq.getInt("households");   //*
        String jobCompany = apiReq.getString("jobCompany"); //非必填
        Double income = apiReq.getDouble("income");//非必填
        Integer dependants = apiReq.getInt("dependants");//非必填
        String linkUser = apiReq.getString("linkUser");//非必填
        String linkTel = apiReq.getString("linkTel");//非必填
        CrmCustomerInfo cci = new CrmCustomerInfo();
        cci.setId(id);
        cci.setUserName(userName);
        cci.setSex(sex);
        cci.setAge(age);
        cci.setUserPhone(userPhone);
        cci.setProvince(province);
        cci.setProvinceId(provinceId);
        cci.setCity(city);
        cci.setCityId(cityId);
        cci.setDistrict(district);
        cci.setDistrictId(districtId);
        cci.setFamilyAddress(familyAddress);
        cci.setHouseholds(households);
        cci.setJobCompany(jobCompany);
        cci.setIncome(income);
        cci.setDependants(dependants);
        cci.setLinkUser(linkUser);
        cci.setLinkTel(linkTel);
        cci.setUpdateTime(new Date());
        if(cci.getJobCompany() != null){
            number+=1;
        }
        if(cci.getIncome() != null){
            number+=1;
        }
        if(cci.getDependants() != null){
            number+=1;
        }
        if(cci.getLinkUser()!= null){
            number+=1;
        }
        if(cci.getLinkTel() != null){
            number+=1;
        }
        Double dataRate = (double)25/11*number;
        cci.setDataRate(dataRate);
        int result = crmCustomerInfoMapper.updateByPrimaryKeySelective(cci);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod( descript = "添加客户信息", value = "backend-add-customer-info")
    @Override
    public ApiResponse<CrmCustomerInfo> addCustomerInfo(ApiRequest apiReq) {
        int number = 6;
        String userName = apiReq.getString("userName");  //*
        Integer sex = apiReq.getInt("sex");              //*
        Integer age = apiReq.getInt("age");              //*
        String userPhone = apiReq.getString("userPhone");//*
        String province = apiReq.getString("province");
        Integer provinceId = apiReq.getInt("provinceId");
        String city = apiReq.getString("city");
        Integer cityId = apiReq.getInt("cityId");
        String district = apiReq.getString("district");
        Integer districtId = apiReq.getInt("districtId");
        String familyAddress = apiReq.getString("familyAddress"); //*
        Integer households = apiReq.getInt("households");   //*
        String jobCompany = apiReq.getString("jobCompany"); //非必填
        Double income = apiReq.getDouble("income");//非必填
        Integer dependants = apiReq.getInt("dependants");//非必填
        String linkUser = apiReq.getString("linkUser");//非必填
        String linkTel = apiReq.getString("linkTel");//非必填
        Long ccId = apiReq.getLong("ccId");
        String ccName = apiReq.getString("ccName");
        Long orgId = apiReq.getLong("orgId");
        String orgName = apiReq.getString("orgName");

        CrmCustomerInfo cci = new CrmCustomerInfo();
        cci.setUserName(userName);
        cci.setSex(sex);
        cci.setAge(age);
        cci.setUserPhone(userPhone);
        cci.setProvince(province);
        cci.setProvinceId(provinceId);
        cci.setCity(city);
        cci.setCityId(cityId);
        cci.setDistrict(district);
        cci.setDistrictId(districtId);
        cci.setFamilyAddress(familyAddress);
        cci.setHouseholds(households);
        cci.setJobCompany(jobCompany);
        cci.setIncome(income);
        cci.setDependants(dependants);
        cci.setLinkUser(linkUser);
        cci.setLinkTel(linkTel);
        cci.setCreateTime(new Date());
        cci.setCcId(ccId);
        cci.setCcName(ccName);
        cci.setOrgId(orgId);
        cci.setOrgName(orgName);
        if(cci.getJobCompany() != null){
            number+=1;
        }
        if(cci.getIncome() != null){
            number+=1;
        }
        if(cci.getDependants() != null){
            number+=1;
        }
        if(cci.getLinkUser()!= null){
            number+=1;
        }
        if(cci.getLinkTel() != null){
            number+=1;
        }
        Double dataRate = (double)25/11*number;
        cci.setDataRate(dataRate);
        int result = crmCustomerInfoMapper.insertSelective(cci);
        if(result>0){
            CrmCaseInfo caseInfo = new CrmCaseInfo();
            caseInfo.setCustomerId(cci.getId());
            caseInfo.setCreateTime(new Date());
            caseInfo.setFirstVisitTime(new Date());
            CrmInjuryInfo cii = new CrmInjuryInfo();
            cii.setCustomerId(cci.getId());
            cii.setCreateDate(new Date());
            CrmAccidentInfo cai = new CrmAccidentInfo();
            cai.setCustomerId(cci.getId());
            cai.setCreateTime(new Date());
            crmCaseInfoMapper.insertSelective(caseInfo);
            crmInjuryInfoMapper.insertSelective(cii);
            crmAccidentInfoMapper.insertSelective(cai);
            return new ApiResponse<CrmCustomerInfo>(ApiMsgEnum.SUCCESS,1,cci);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }


    @SuppressWarnings("rawtypes")
    @ApiMethod( descript = "批量导入客户信息", value = "backend-readExcel")
    @Override
    public ApiResponse readExcel(ApiRequest apiReq) throws IOException {
        String fileData = apiReq.getString("fileData");
        Long ccId = apiReq.getLong("ccId");
        String ccName = apiReq.getString("ccName");
        Long orgId = apiReq.getLong("orgId");
        String orgName = apiReq.getString("orgName");
        String path = FileUtils.saveBytesToFileXls("data/test",fileData);
        FileInputStream fileIn = new FileInputStream(path);
        Workbook hssfWorkbook  = null;
        try{
            hssfWorkbook = new HSSFWorkbook(fileIn);
        } catch (Exception ex){
            fileIn = new FileInputStream(path);
            hssfWorkbook = new XSSFWorkbook(fileIn);
        }
        Sheet sheet = hssfWorkbook.getSheetAt(0);
        HashMap<String,Object> areaMap = new HashMap<>();
        for(Row r: sheet){
            if(r.getRowNum()<1){
                continue;
            }
            apiReq.put("userName",r.getCell(2));
            apiReq.put("sex",r.getCell(3).toString().equals("男")?0:1);
            apiReq.put("age",r.getCell(4));
            apiReq.put("userPhone",r.getCell(5));
            apiReq.put("province",r.getCell(6));
            areaMap.put("areaName",r.getCell(6).toString());
            areaMap.put("areaType","1");
            Long provinceId = crmCustomerInfoMapper.selectAreaByAreaName(areaMap);
            apiReq.put("provinceId",provinceId);
            apiReq.put("city",r.getCell(7));
            areaMap.put("areaName",r.getCell(7).toString());
            areaMap.put("areaType","2");
            Long cityId = crmCustomerInfoMapper.selectAreaByAreaName(areaMap);
            apiReq.put("cityId",cityId);
            apiReq.put("district",r.getCell(8));
            areaMap.put("areaName",r.getCell(8).toString());
            areaMap.put("areaType","3");
            Long districtId = crmCustomerInfoMapper.selectAreaByAreaName(areaMap);
            apiReq.put("districtId",districtId);
            apiReq.put("familyAddress",r.getCell(9));
            apiReq.put("households",r.getCell(10).toString().equals("城镇")?1:0);
            apiReq.put("jobCompany",r.getCell(12));
            apiReq.put("income",r.getCell(13));
            apiReq.put("dependants",r.getCell(11).toString().equals("无")?0:1);
            apiReq.put("linkUser",r.getCell(2));
            apiReq.put("linkTel",r.getCell(5));
            apiReq.put("ccId",ccId);
            apiReq.put("ccName",ccName);
            apiReq.put("orgId",orgId);
            apiReq.put("orgName",orgName);
            ApiResponse<CrmCustomerInfo> addCustomerInfoApiRes = this.addCustomerInfo(apiReq);
            if(addCustomerInfoApiRes.getMsgEnum().getIsSuccess()){
                //如果添加成功，开始进行伤情信息封装
                apiReq.put("customerId",addCustomerInfoApiRes.getResults().getId());
                apiReq.put("injuryName",r.getCell(14));
                apiReq.put("usedMedicalFee",r.getCell(15));
                apiReq.put("oweMedicalFee",r.getCell(16));
                apiReq.put("neededMedicalFee",r.getCell(17));
                Integer financingType = -1;
                switch (r.getCell(18).toString()){
                    case "自费":
                        financingType = 0;
                        break;
                    case "保司":
                        financingType = 1;
                        break;
                    case "道救救助基金垫付":
                        financingType = 2;
                        break;
                    case "其他":
                        financingType = 3;
                        break;
                }
                apiReq.put("financingType",financingType);
                apiReq.put("visHospital",r.getCell(19));
                apiReq.put("isInhospital",r.getCell(20).toString().equals("否")?0:1);
                apiReq.put("isOperation",r.getCell(21).toString().equals("否")?0:1);
                apiReq.put("hospitalDepartments",r.getCell(22));
                apiReq.put("bedNumber",r.getCell(23));
                apiReq.put("hospitalNumber",r.getCell(24));
                apiReq.put("doctor",r.getCell(25));
                apiReq.put("doctorTel",r.getCell(26));
                apiReq.put("nurse",r.getCell(27));
                apiReq.put("nurseTel",r.getCell(28));
                apiReq.put("otherDesc",r.getCell(29));
                ApiResponse injuryRes = backendCrmInjuryInfoApi.editInjuryInfo(apiReq);
                if(injuryRes.getMsgEnum().getIsSuccess()){
                    //如果添加成功，开始进行事故信息封装
                    apiReq.put("accidentDate",dateTransformation(r.getCell(30).toString()));
                    apiReq.put("accidentAddress",r.getCell(31));
                    Integer accidentCognizance = -1;
                    switch (r.getCell(32).toString()){
                        case "全部责任":
                            accidentCognizance = 1;
                            break;
                        case "主要责任":
                            accidentCognizance = 2;
                            break;
                        case "同等责任":
                            accidentCognizance = 3;
                            break;
                        case "次要责任":
                            accidentCognizance = 4;
                            break;
                        case "无责任":
                            accidentCognizance = 5;
                            break;
                        case "责任无法认定":
                            accidentCognizance = 6;
                            break;
                    }
                    apiReq.put("accidentCognizance",accidentCognizance);
                    apiReq.put("policeTeam",r.getCell(33));
                    apiReq.put("policeMan",r.getCell(34));
                    apiReq.put("policeTel",r.getCell(35));
                    apiReq.put("insCompulsory",r.getCell(36));
                    apiReq.put("insCommercial",r.getCell(37));
                    apiReq.put("threeQuota",r.getCell(38));
                    apiReq.put("isDeductibles",r.getCell(39).toString().equals("否")?0:1);
                    apiReq.put("driverName",r.getCell(40));
                    apiReq.put("driverTel",r.getCell(41));
                    apiReq.put("isMulti",r.getCell(42).toString().equals("否")?0:1);
                    apiReq.put("isRelief",r.getCell(43).toString().equals("否")?0:1);
                    apiReq.put("otherDesc",r.getCell(44));
                    ApiResponse accidentRes = backendCrmAccidentInfoApi.editAccidentInfo(apiReq);
                    if(accidentRes.getMsgEnum().getIsSuccess()){
                        //开始封装案件信息
                        apiReq.put("isIntention",r.getCell(45).toString().equals("否")?0:1);
                        Integer caseProgress = -1;
                        switch (r.getCell(46).toString()){
                            case "初访":
                                caseProgress = 1;
                                break;
                            case "洽谈中":
                                caseProgress = 2;
                                break;
                            case "待签约":
                                caseProgress = 3;
                                break;
                            case "已签约":
                                caseProgress = 4;
                                break;
                            case "暂时搁置":
                                caseProgress = 5;
                                break;
                            case "已放弃":
                                caseProgress = 6;
                                break;
                        }
                        Integer caseSource = -1;
                        switch (r.getCell(47).toString()){
                            case "医院":
                                caseSource=0;
                                break;
                            case "小程序":
                                caseSource=1;
                                break;
                            case "工作室":
                                caseSource=2;
                                break;
                            case "保司":
                                caseSource=3;
                                break;
                            case "交警":
                                caseSource=4;
                                break;
                            case "陌拜":
                                caseSource=5;
                                break;
                            case "护工":
                                caseSource=6;
                                break;
                            case "转介":
                                caseSource=7;
                                break;
                            case "其他":
                                caseSource=8;
                                break;
                        }
                        apiReq.put("caseSource",caseSource);
                        apiReq.put("caseProgress",caseProgress);
                        Integer caseType = -1;
                        switch (r.getCell(48).toString()){
                            case"简易代理":
                                caseType = 1;
                                break;
                            case"案件代理":
                                caseType = 2;
                                break;
                            case"代理+垫付":
                                caseType = 3;
                                break;
                        }
                        apiReq.put("caseType",caseType);
                        apiReq.put("claimFee",r.getCell(49));
                        apiReq.put("loanFee",r.getCell(50));
                        apiReq.put("serviceFee",r.getCell(51));
                        apiReq.put("followTime",dateTransformation(r.getCell(52).toString()));
                        apiReq.put("followAddress",r.getCell(53));
                        apiReq.put("nextFollowTime",dateTransformation(r.getCell(1).toString()));
                        apiReq.put("followDesc",r.getCell(54));
                        apiReq.put("customerName",r.getCell(2));
                        backendCrmCaseInfoApi.editCaseInfo(apiReq);
                    }
                }
            }
        }
        fileIn.close();
        File f = new File(path);
        f.delete();
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    public String dateTransformation(String date){
        String yearDate = date.replace("年","-");
        String mDate = yearDate.replace("月","-");
        String dDate = mDate.replace("日"," ");
        return  dDate;
    }
}
