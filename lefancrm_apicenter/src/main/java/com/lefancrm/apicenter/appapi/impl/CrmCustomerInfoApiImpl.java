package com.lefancrm.apicenter.appapi.impl;

import com.lefancrm.apicenter.appapi.CrmCustomerInfoApi;
import com.lefancrm.apicenter.dao.CrmAccidentInfoMapper;
import com.lefancrm.apicenter.dao.CrmCaseInfoMapper;
import com.lefancrm.apicenter.dao.CrmCustomerInfoMapper;
import com.lefancrm.apicenter.dao.CrmInjuryInfoMapper;
import com.lefancrm.apicenter.model.CrmAccidentInfo;
import com.lefancrm.apicenter.model.CrmCaseInfo;
import com.lefancrm.apicenter.model.CrmCustomerInfo;
import com.lefancrm.apicenter.model.CrmInjuryInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiParam;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jun on 2017/12/15.
 */
@Service
@ApiService(descript = "伤者信息api")
public class CrmCustomerInfoApiImpl extends BaseServiceImpl implements CrmCustomerInfoApi {


    @Autowired
    private CrmCustomerInfoMapper crmCustomerInfoMapper;

    @Autowired
    private CrmCaseInfoMapper crmCaseInfoMapper;

    @Autowired
    private CrmInjuryInfoMapper crmInjuryInfoMapper;

    @Autowired
    private CrmAccidentInfoMapper crmAccidentInfoMapper;

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "添加客户信息", value = "add-customer-info")
    @Override
    public ApiResponse addCustomerInfo(ApiRequest apiReq) {
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
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "查询客户信息详情", value = "query-customer-details")
    @Override
    public ApiResponse queryCustomerDetails(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        CrmCustomerInfo cci = crmCustomerInfoMapper.selectByPrimaryKey(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS,cci==null?0:1,cci);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "编辑客户信息", value = "edit-customer-info")
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
    @ApiMethod(needLogin = true, descript = "查询客户列表", value = "query-customer-list")
    @Override
    public ApiResponse queryCustomerList(ApiRequest apiReq) {
        HashMap<String,Object> map = new HashMap<>();
        this.setAppPageSize(apiReq);
        Integer pageIndex = apiReq.getInt("pageIndex");
        if(pageIndex!=null){
            pageIndex = pageIndex*10;
        }else{
            pageIndex=0;
        }
        String role = apiReq.getString("role");
        Long ccId = apiReq.getLong("ccId");
        Long orgId = apiReq.getLong("orgId");
        String isScreen = apiReq.getString("isScreen");
        Integer sort = apiReq.getInt("sort");
        String userName = apiReq.getString("userName");
        Integer isIntention = apiReq.getInt("isIntention");
        Integer caseProgress = apiReq.getInt("caseProgress");
        if(isScreen!=null){
            //说明添加了筛选条件，开始进行
            map.put("pageIndex",pageIndex);
            map.put("pageSize",apiReq.getInt("pageSize"));
            map.put("orgId",orgId);
            map.put("ccId",ccId);
            map.put("userName",userName);
            map.put("isIntention",isIntention);
            map.put("caseProgress",caseProgress);
            map.put("sort",sort);
            List<CrmCustomerInfo> list = crmCustomerInfoMapper.selectCustomerByOrgIdOrCcId(map);
            int count = crmCustomerInfoMapper.selectCustomerByOrgIdOrCcIdCount(map);
            if(list!=null){
                return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
            }
        }
        if(role.contains("19")){
            //市场总监查所有的数据
            map.put("pageIndex",pageIndex);
            map.put("pageSize",apiReq.getInt("pageSize"));
        }else if(role.contains("17")){
            //cc主管，先查询出cc主管下所有的小组机构id，然后根据cc主管机构加小组机构id查询出客户数据
            map.put("pageIndex",pageIndex);
            map.put("pageSize",apiReq.getInt("pageSize"));
            map.put("orgId",orgId);
            map.put("sort",sort);
            List<CrmCustomerInfo> list = crmCustomerInfoMapper.selectCustomerByOrgIds(map);
            int count = crmCustomerInfoMapper.selectCustomerByOrgIdsCount(map);
            if(list!=null){
                return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
            }
        }else if (role.contains("20")){
            //根据机构ID查询客户信息
            map.put("pageIndex",pageIndex);
            map.put("pageSize",apiReq.getInt("pageSize"));
            map.put("orgId",orgId);
        }else if(role.contains("2")){
            //查询所属自己的客户信息
            map.put("pageIndex",pageIndex);
            map.put("pageSize",apiReq.getInt("pageSize"));
            map.put("ccId",ccId);
        }else{
            System.out.println("无角色可以进行查询");
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        List<CrmCustomerInfo> list = crmCustomerInfoMapper.selectCustomerByOrgIdOrCcId(map);
        int count = crmCustomerInfoMapper.selectCustomerByOrgIdOrCcIdCount(map);
        if(list!=null){
            return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,0,null);
    }


    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "查询筛选条件的姓名和部门", value = "query-screen-ccIdAndOrgId")
    @Override
    public ApiResponse queryScreenCcIdAndOrgId(ApiRequest apiReq) {
        String role = apiReq.getString("role");
        Long orgId = apiReq.getLong("orgId");
        List<HashMap<String,Object>> orgInfoList = new ArrayList<HashMap<String,Object>>();
        List<HashMap<String,Object>> userInfoList = new ArrayList<HashMap<String,Object>>();
        if(role.contains("19")){
            //市场总监
            //查询各大机构，cc姓名为所有的业务员、市场总监、cc主管、小组长
            orgInfoList = crmCustomerInfoMapper.queryAllOrgInfo();
            userInfoList = crmCustomerInfoMapper.queryUserInfoByCc();
        }else if(role.contains("17")){
            //cc主管
            //查询本部门和他管理的所有小组的orgId和部门名称
            //查询根据查询出来的orgId得到orgId下的所有用户id和用户名称
            orgInfoList = crmCustomerInfoMapper.queryOrgIdAndOrgNameByOrgId(orgId);
            userInfoList = crmCustomerInfoMapper.queryUserIdAndUserNameByOrgIds(orgId);
        }else if (role.contains("20")){
            //小组长
            //只查小组长机构下的所有成员的id和姓名
            userInfoList = crmCustomerInfoMapper.queryUserIdAndUserNameByOrgId(orgId);
        }else{
            System.out.println("无角色可以进行查询");
            return new ApiResponse(ApiMsgEnum.NO_AUTHORITY);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("orgInfoList",orgInfoList);
        resultMap.put("userInfoList",userInfoList);
        return new ApiResponse(ApiMsgEnum.SUCCESS,resultMap==null?0:resultMap.size(),resultMap);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "查询筛选条件的姓名根据部门查询", value = "query-screen-ccIdByOrgId")
    @Override
    public ApiResponse queryScreenCcIdByOrgId(ApiRequest apiReq) {
        //根据orgId查询出ccid和姓名
        Long orgId = apiReq.getLong("orgId");
        List<HashMap<String,Object>> userInfoList = new ArrayList<HashMap<String,Object>>();
        userInfoList = crmCustomerInfoMapper.queryUserIdAndUserNameByOrgId(orgId);
        return new ApiResponse(ApiMsgEnum.SUCCESS,userInfoList==null?0:userInfoList.size(),userInfoList);
    }
}
