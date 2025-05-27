package com.lefancrm.backend.web.fina;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.CommonEnumDto;
import com.lefancrm.backend.dto.SurveyConsignorDto;
import com.lefancrm.backend.dto.SurveyRiskCaseInfoDto;
import com.lefancrm.backend.dto.UserInfo;
import com.lefancrm.backend.dto.fina.*;
import com.lefancrm.backend.web.BackendBaseController;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/fina/applicant/")
public class FinaApplicantInfoController extends BackendBaseController {
    @RequestMapping(value = "list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        model.put("menuCode",req.getParameter("menuCode"));
        return new ModelAndView("/fina/applicant/list",model);
    }

    @RequestMapping(value = "edit")
    public ModelAndView edit(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<FinaApplicantInfo>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INFO_FINA_APPLICANT_INFO, null, req);
        FinaApplicantInfo dto = (FinaApplicantInfo)apiFinalResponse.getResults();
        model.put("dto",dto);

        //初始化数据
        Map params = new HashMap();

        //获取保险公司列表
        typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>(){};
        Map appendMap = new HashMap<>();
        appendMap.put("dataType","entrust-org-list");
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_AJAX_DATA_FINA_PUB, appendMap, req);
        List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>)apiFinalResponse.getResults();
        model.put("consignors",consignors);
        params.put("consignorJson", JsonUtil.objectToJson(consignors));

        //获取诊断数据
        typeToken = new TypeToken<ApiFinalResponse<List<FinaDiagnosisInfo>>>(){};
        appendMap = new HashMap<>();
        appendMap.put("dataType","diagnosis-info-list");
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_AJAX_DATA_FINA_PUB, appendMap, req);
        List<FinaDiagnosisInfo> finaDiagnosisInfos = (List<FinaDiagnosisInfo>)apiFinalResponse.getResults();
        model.put("finaDiagnosisInfos",finaDiagnosisInfos);
        params.put("finaDiagnosisInfoJson", JsonUtil.objectToJson(finaDiagnosisInfos));

        model.put("params",params);

        model.put("btnCode",req.getParameter("btnCode"));
        model.put("urgeType",req.getParameter("urgeType"));
        model.put("transferCloseType",req.getParameter("transferCloseType"));

        return new ModelAndView("/fina/applicant/edit",model);
    }

    /**
     * ajax获取list 列表
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "getDetail")
    public String getDetail(HttpServletRequest req, HttpServletResponse rsp){
        Map<String,Object> appendMap = new HashMap<>();
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_LIST_FINA_APPLICANT_INFO, appendMap, req, rsp);
    }

    @RequestMapping(value = "info")
    public ModelAndView info(HttpServletRequest req, HttpServletResponse rsp){
        String menuCode = req.getParameter("menuCode");
        Map model = new HashMap();
        model.put("menuCode",menuCode);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<FinaApplicantInfo>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INFO_FINA_APPLICANT_INFO, null, req);
        FinaApplicantInfo finaApplicantInfo = (FinaApplicantInfo)apiFinalResponse.getResults();
        model.put("dto",finaApplicantInfo);

        if("assign-org-list".equals(menuCode)){
            model.put("surveyOrgId",req.getParameter("surveyOrgId"));//机构id
        }
        //当前登录人信息
        UserInfo adminSession = this.getSessionAdmin(req);
        model.put("currentUserId",adminSession.getUserId());
        return new ModelAndView("/fina/applicant/info",model);
    }

    @RequestMapping(value = "operate")
    public String operate(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_OPERATE_FINA_APPLICANT_INFO, null, req, rsp);
    }

    @RequestMapping(value = "ajaxData")
    public String ajaxData(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_AJAX_DATA_FINA_APPLICANT_INFO, null, req, rsp);
    }

    @RequestMapping(value = "operateView")
    public ModelAndView operateView(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        String btnCode = req.getParameter("btnCode");
        model.put("btnCode",btnCode);
        model.put("finaInfoId",req.getParameter("finaInfoId"));
        model.put("caseApplicantNo",req.getParameter("caseApplicantNo"));
        Map<String, Object> appendMap = new HashMap<String, Object>();
        if ("to-lefan-survey".equals(btnCode))//将垫付案件中的数据，带到调查案件中
        {

            appendMap = new HashMap<String, Object>();
            appendMap.put("dataType",btnCode);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyRiskCaseInfoDto>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO, appendMap, req);
            SurveyRiskCaseInfoDto dto = (SurveyRiskCaseInfoDto)apiFinalResponse.getResults();
            dto.setMinEndDate(new Date());
            model.put("dto",dto);
            return new ModelAndView("/survey/case/editNew",model);
        }
        else if ("get-applicant-files".equals(btnCode))//上传材料页面
        {
            model.put("opr",req.getParameter("opr"));
            model.put("urgeType",req.getParameter("urgeType"));
            model.put("transferCloseType",req.getParameter("transferCloseType"));

            return new ModelAndView("/fina/applicant/files",model);
        }
        else if ("confirm-account".equals(btnCode))//确认到账页面
        {
            appendMap = new HashMap<String, Object>();
            appendMap.put("dataType",btnCode);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<FinaApplicantInfo>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_AJAX_DATA_FINA_APPLICANT_INFO, appendMap, req);
            FinaApplicantInfo finaApplicantInfo = (FinaApplicantInfo)apiFinalResponse.getResults();
            model.put("dto",finaApplicantInfo);
            if(finaApplicantInfo!=null){
                model.put("confirmType",finaApplicantInfo.getFinaApplicantMoney().getConfirmType());
                if(finaApplicantInfo.getFinaConfirmAccount()!=null && finaApplicantInfo.getFinaConfirmAccount().getFinaFiles()!=null){
                    model.put("finaFiles", JsonUtil.objectToJson(finaApplicantInfo.getFinaConfirmAccount().getFinaFiles()));
                }
            }
            model.put("haveAccount",req.getParameter("haveAccount"));//是否已确认到账
            return new ModelAndView("/fina/applicant/operateView",model);
        }
        else if("get-applicant-track".equals(btnCode))//确认跟踪页面
        {
            //跟踪记录
            appendMap = new HashMap<String, Object>();
            appendMap.put("dataType",btnCode);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<FinaApplicantInfo>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_AJAX_DATA_FINA_APPLICANT_INFO, appendMap, req);
            FinaApplicantInfo dto = (FinaApplicantInfo)apiFinalResponse.getResults();
            model.put("dto",dto);
            if(dto.getFinaApplicantTrackList() !=null){
                model.put("finaApplicantTrackListJson", JsonUtil.objectToJson(dto.getFinaApplicantTrackList()));
            }else{
                model.put("finaApplicantTrackListJson", null);
            }

            return new ModelAndView("/fina/applicant/trackView",model);
        }
        else if("risk-level-info".equals(btnCode))//风险识别详情
        {
            appendMap = new HashMap<String, Object>();
            appendMap.put("dataType",btnCode);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<Map>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_AJAX_DATA_FINA_APPLICANT_INFO, appendMap, req);
            Map map = (Map) apiFinalResponse.getResults();
            model.put("map",map);

            List<FinaApplicantFileEnum> allEnum = (List)map.get("allEnum");
            model.put("allEnumJson", JsonUtil.objectToJson(allEnum));

            List<FinaApplicantFile> finaApplicantFiles = (List)map.get("finaApplicantFiles");
            model.put("finaApplicantFilesJson", JsonUtil.objectToJson(finaApplicantFiles));

            return new ModelAndView("/fina/applicant/riskLevelView",model);
        }
        else if("risk-level-view-left-info".equals(btnCode))//风险识别图片详情
        {
            appendMap = new HashMap<String, Object>();
            appendMap.put("dataType",btnCode);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<Map>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_AJAX_DATA_FINA_APPLICANT_INFO, appendMap, req);
            Map map = (Map) apiFinalResponse.getResults();
            model.put("map",map);

            List<FinaApplicantFileEnum> allEnum = (List)map.get("allEnum");
            model.put("allEnumJson", JsonUtil.objectToJson(allEnum));

            List<FinaApplicantFile> finaApplicantFiles = (List)map.get("finaApplicantFiles");
            model.put("finaApplicantFilesJson", JsonUtil.objectToJson(finaApplicantFiles));

            return new ModelAndView("/fina/applicant/riskLevelViewLeft",model);
        }
        else if ("get-fina-progress".equals(btnCode))//上传材料页面
        {
            model.put("finaInfoId",req.getParameter("finaInfoId"));
            model.put("keyCode",req.getParameter("keyCode"));

            return new ModelAndView("/fina/applicant/progres",model);
        }
        return null;
    }
}
