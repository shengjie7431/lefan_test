package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jun on 2017/12/29.
 */
@Controller
@RequestMapping(value = "/cci")
public class BackendCrmCustomerInfoController extends BackendBaseController{



    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String caseProgress = req.getParameter("caseProgress");
        String caseSource = req.getParameter("caseSource");
        String orgId = req.getParameter("orgId");
        String ccId = req.getParameter("ccId");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CciListDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CCI_LIST, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("caseProgress",caseProgress==null?"":caseProgress);
        model.put("caseSource",caseSource==null?"":caseSource);
        model.put("orgId",orgId==null?"":orgId);
        model.put("ccId",ccId==null?"":ccId);
        return new ModelAndView("/cci/list",model);
    }
    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        Map model = new HashMap();
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String familyAddress = req.getParameter("familyAddress");
        String ccName = req.getParameter("ccName");
        String caseProgress = req.getParameter("caseProgress");
        model.put("id",id);
        model.put("name",name);
        model.put("familyAddress",familyAddress);
        model.put("ccName",ccName);
        model.put("caseProgress",caseProgress);
        return new ModelAndView("/cci/index",model);
    }

    @RequestMapping(value = "/customerPanorama")
    public ModelAndView customerPanorama(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        Map model = new HashMap();
        String id = req.getParameter("id");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<HashMap<String,Object>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CCI_CUSTOMERPANORAMA, null, req);
        model.put("apiRsp",apiFinalResponse);
        return new ModelAndView("/cci/customerPanorama",model);
    }

    @RequestMapping(value = "/salesDynamics")
    public ModelAndView salesDynamics(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String customerId = req.getParameter("customerId");
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CrmCustomerFollowsDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_QUERY_CUSTOMER_FOLLOWS, null, req);
        model.put("customerId",customerId);
        model.put("apiRsp",apiFinalResponse);
        return new ModelAndView("/cci/salesDynamics",model);
    }

    /**
     * 添加销售动态
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/salesDynamicsAdd")
    public ModelAndView salesDynamicsAdd(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String customerId = req.getParameter("customerId");
        Map model = new HashMap();
        model.put("customerId",customerId);
        return new ModelAndView("/cci/edit/salesDynamicsAdd",model);
    }

    /**
     * 保存销售动态
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/salesDynamicsSave")
    public String salesDynamicsSave(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADD_CUSTOMER_FOLLOWS, null, req, rsp);
    }

    @RequestMapping(value = "/caseInformation")
    public ModelAndView caseInformation(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String customerId = req.getParameter("customerId");
        String name = req.getParameter("name");
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CrmCaseInfoDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_QUERY_CASE_INFO_DETAILS, null, req);
        model.put("apiRsp",apiFinalResponse);
        model.put("customerId",customerId);
        model.put("name",name);
        return new ModelAndView("/cci/caseInformation",model);
    }

    /**
     * 编辑案件信息
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/caseInformationEdit")
    public ModelAndView caseInformationEdit(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String caseSource=req.getParameter("caseSource");
        String isIntention = req.getParameter("isIntention");
        String caseType = req.getParameter("caseType");
        String caseProgress = req.getParameter("caseProgress");
        String claimFee = req.getParameter("claimFee");
        String loanFee = req.getParameter("loanFee");
        String serviceFee = req.getParameter("serviceFee");
        String nextTime = req.getParameter("nextTime");
        String createTime = req.getParameter("createTime");
        String updateTime = req.getParameter("updateTime");
        String customerId = req.getParameter("customerId");
        String customerName = req.getParameter("customerName");
        String followTime = req.getParameter("followTime");
        String followAddress = req.getParameter("followAddress");
        String nextFollowTime = req.getParameter("nextFollowTime");
        String followDesc = req.getParameter("followDesc");
        Map model = new HashMap();
        model.put("caseSource",caseSource);
        model.put("isIntention",isIntention);
        model.put("caseType",caseType);
        model.put("caseProgress",caseProgress);
        model.put("claimFee",claimFee);
        model.put("loanFee",loanFee);
        model.put("serviceFee",serviceFee);
        model.put("nextTime",nextTime);
        model.put("createTime",createTime);
        model.put("updateTime",updateTime);
        model.put("customerId",customerId);
        model.put("customerName",customerName);
        model.put("followTime",followTime);
        model.put("followAddress",followAddress);
        model.put("nextFollowTime",nextFollowTime);
        model.put("followDesc",followDesc);
        return new ModelAndView("/cci/edit/caseInformationEdit",model);
    }

    /**
     * 保存案件信息
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/caseInformationSave")
    public String caseInformationSave(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_EDIT_CASE_INFO, null, req, rsp);
    }


    /**
     * 伤者信息
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/injuredInformation")
    public ModelAndView injuredInformation(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String id = req.getParameter("id");
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CrmCustomerInfoDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CCI_INJUREDINFORMATION, null, req);
        model.put("id",id);
        model.put("apiRsp",apiFinalResponse);
        return new ModelAndView("/cci/injuredInformation",model);
    }


    /**
     * 保存伤者信息
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/injuredInformationSave")
    public String injuredInformationSave(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_EDIT_CUSTOMER_INFO, null, req, rsp);
    }

    /**
     * 显示添加伤者页面
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/injuredInformationAddView")
    public ModelAndView injuredInformationAddView(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken,BackendApiMethodEnum.BACKEND_ORG_TO_ADD, null, req);
        model.put("apiRsp",apiFinalResponse);
        return new ModelAndView("/cci/edit/injuredInformationAdd",model);
    }
    /**
     * 添加伤者信息
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/injuredInformationAdd")
    public String injuredInformationAdd(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        UserInfo ui = (UserInfo) req.getSession().getAttribute("adminDto");
        Map model = new HashMap();
        model.put("ccId",ui.getUserId());
        model.put("ccName",ui.getUserName());
        model.put("orgId",ui.getOrgId());
        model.put("orgName",ui.getOrgName());
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADD_CUSTOMER_INFO, model, req, rsp);
    }


    /**
     * 编辑伤者信息
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/injuredInformationEdit")
    public ModelAndView injuredInformationEdit(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String id = req.getParameter("id");
        String userName = req.getParameter("userName");
        String sex = req.getParameter("sex");
        String age = req.getParameter("age");
        String userPhone = req.getParameter("userPhone");
        String familyAddress = req.getParameter("familyAddress");
        String households = req.getParameter("households");
        String jobCompany = req.getParameter("jobCompany");
        String income = req.getParameter("income");
        String dependants = req.getParameter("dependants");
        String linkUser = req.getParameter("linkUser");
        String linkTel = req.getParameter("linkTel");
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken,BackendApiMethodEnum.BACKEND_ORG_TO_ADD, null, req);
        model.put("apiRsp",apiFinalResponse);
        model.put("id",id);
        model.put("userName",userName);
        model.put("sex",sex);
        model.put("age",age);
        model.put("userPhone",userPhone);
        model.put("familyAddress",familyAddress);
        model.put("households",households);
        model.put("jobCompany",jobCompany);
        model.put("income",income);
        model.put("dependants",dependants);
        model.put("linkUser",linkUser);
        model.put("linkTel",linkTel);
        return new ModelAndView("/cci/edit/injuredInformationEdit",model);
    }

    /**
     * 伤情信息
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/injuryInformation")
    public ModelAndView injuryInformation(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String id = req.getParameter("id");
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CrmInjuryInfoDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CCI_INJURYINFORMATION, null, req);
        model.put("id",id);
        model.put("apiRsp",apiFinalResponse);
        return new ModelAndView("/cci/injuryInformation",model);
    }

    /**
     * 编辑伤情信息
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/injuryInformationEdit")
    public ModelAndView injuryInformationEdit(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String id = req.getParameter("id");
        String injuryName = req.getParameter("injuryName");
        String usedMedicalFee = req.getParameter("usedMedicalFee");
        String oweMedicalFee = req.getParameter("oweMedicalFee");
        String neededMedicalFee = req.getParameter("neededMedicalFee");
        String financingType = req.getParameter("financingType");
        String visHospital = req.getParameter("visHospital");
        String isInhospital = req.getParameter("isInhospital");
        String isOperation = req.getParameter("isOperation");
        String hospitalDepartments = req.getParameter("hospitalDepartments");
        String bedNumber = req.getParameter("bedNumber");
        String hospitalNumber = req.getParameter("hospitalNumber");
        String doctor = req.getParameter("doctor");
        String doctorTel = req.getParameter("doctorTel");
        String nurse = req.getParameter("nurse");
        String nurseTel = req.getParameter("nurseTel");
        String otherDesc = req.getParameter("otherDesc");
        Map model = new HashMap();
        model.put("id",id);
        model.put("injuryName",injuryName);
        model.put("usedMedicalFee",usedMedicalFee);
        model.put("oweMedicalFee",oweMedicalFee);
        model.put("neededMedicalFee",neededMedicalFee);
        model.put("financingType",financingType);
        model.put("visHospital",visHospital);
        model.put("isInhospital",isInhospital);
        model.put("isOperation",isOperation);
        model.put("hospitalDepartments",hospitalDepartments);
        model.put("bedNumber",bedNumber);
        model.put("hospitalNumber",hospitalNumber);
        model.put("doctor",doctor);
        model.put("doctorTel",doctorTel);
        model.put("nurse",nurse);
        model.put("nurseTel",nurseTel);
        model.put("otherDesc",otherDesc);
        return new ModelAndView("/cci/edit/injuryInformationEdit",model);
    }

    /**
     * 保存伤情信息
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/injuryInformationSave")
    public String injuryInformationSave(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_EDIT_INJURY_INFO, null, req, rsp);
    }

    /**
     * 事故信息
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/accidentInformation")
    public ModelAndView accidentInformation(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String id = req.getParameter("id");
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CrmAccidentInfoDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CCI_ACCIDENTINFORMATION, null, req);
        model.put("apiRsp",apiFinalResponse);
        model.put("id",id);
        return new ModelAndView("/cci/accidentInformation",model);
    }

    /**
     * 编辑事故信息
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/accidentInformationEdit")
    public ModelAndView accidentInformationEdit(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String id = req.getParameter("id");
        String accidentDate = req.getParameter("accidentDate");
        String accidentAddress = req.getParameter("accidentAddress");
        String accidentCognizance = req.getParameter("accidentCognizance");
        String policeTeam = req.getParameter("policeTeam");
        String policeMan = req.getParameter("policeMan");
        String policeTel = req.getParameter("policeTel");
        String insCompulsory = req.getParameter("insCompulsory");
        String insCommercial = req.getParameter("insCommercial");
        String threeQuota = req.getParameter("threeQuota");
        String isDeductibles = req.getParameter("isDeductibles");
        String driverName = req.getParameter("driverName");
        String driverTel = req.getParameter("driverTel");
        String isMulti = req.getParameter("isMulti");
        String isRelief = req.getParameter("isRelief");
        String otherDesc = req.getParameter("otherDesc");
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken,BackendApiMethodEnum.BACKEND_ORG_TO_ADD, null, req);
        model.put("apiRsp",apiFinalResponse);
        model.put("id",id);
        model.put("accidentDate",accidentDate);
        model.put("accidentAddress",accidentAddress);
        model.put("accidentCognizance",accidentCognizance);
        model.put("policeTeam",policeTeam);
        model.put("policeMan",policeMan);
        model.put("policeTel",policeTel);
        model.put("insCompulsory",insCompulsory);
        model.put("insCommercial",insCommercial);
        model.put("threeQuota",threeQuota);
        model.put("isDeductibles",isDeductibles);
        model.put("driverName",driverName);
        model.put("driverTel",driverTel);
        model.put("isMulti",isMulti);
        model.put("isRelief",isRelief);
        model.put("otherDesc",otherDesc);
        return new ModelAndView("/cci/edit/accidentInformationEdit",model);
    }

    /**
     * 保存事故信息
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/accidentInformationSave")
    public String accidentInformationSave(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_EDIT_ACCIDENT_INFO, null, req, rsp);
    }


}
