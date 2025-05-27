package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.dto.staff.StaffBudgetCompanyDto;
import com.lefancrm.backend.dto.survey.SurveyConsignorAreaCityDTO;
import com.lefancrm.backend.dto.survey.SurveyEmailInfoDTO;
import com.lefancrm.backend.dto.survey.SurveyEmailInfoOrgDTO;
import com.lefancrm.backend.dto.survey.SurveyFranchiseeAreaCity;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by wangwei on 2018/12/21.
 *
 */
@Controller
@RequestMapping(value = "/baseSurvey")
public class BackendSurveyController extends BackendBaseController{

    /**
     *list
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp) {


        String surveyCode = req.getParameter("surveyCode");
        Map model = new HashMap();
        model.put("surveyCode", surveyCode);//code标识

        model.put("pageSize",req.getParameter("pageSize"));

        // 领域类型
        if("businessType".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyBusinessTypeDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);

            String name = req.getParameter("name");
            model.put("name", name);
            return new ModelAndView("/survey/surveyBusinessType/list",model);
        }
        //业务类型
        else if("serviceType".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyServiceTypeDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);

            String name = req.getParameter("name");
            model.put("name", name);
            return new ModelAndView("/survey/surveyServiceType/list",model);
        }
        //任务类型
        else if("taskInfo".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);

            String name = req.getParameter("name");
            model.put("name", name);

            String type = req.getParameter("type");
            model.put("type", type);
            return new ModelAndView("/survey/surveyTaskInfo/list",model);
        }
        //材料目录
        else if("fileCatalog".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyFileCatalogDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String name = req.getParameter("name");
            model.put("name", name);

            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyFileCatalog/list",model);
        }
        //调查员等级
        else if("level".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyLevelDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);

            String name = req.getParameter("name");
            model.put("name", name);
            return new ModelAndView("/survey/surveyLevel/list",model);
        }
        //称号特权详情
        else if("levelExplain".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyLevelExplainDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);

            String title = req.getParameter("title");
            model.put("title", title);
            return new ModelAndView("/survey/surveyLevelExplain/list",model);
        }
        //平台介绍
        else if("introduction".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyIntroductionDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String name = req.getParameter("name");
            model.put("name", name);

            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyIntroduction/list",model);
        }
        //服务优势
        else if("serviceAdvantage".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyServiceAdvantageDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String name = req.getParameter("name");
            model.put("name", name);

            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyServiceAdvantage/list",model);
        }
        //委托人认证
        else if("consigner".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignerDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String userName = req.getParameter("userName");
            model.put("userName", userName);
            String tel = req.getParameter("tel");
            model.put("tel", tel);
            String company = req.getParameter("company");
            model.put("company", company);
            String authState = req.getParameter("authState");
            model.put("authState", authState);

            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyConsigner/list",model);
        }
        //委托方机构
        else if("consignor".equals(surveyCode)){
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode", "clientConsignor");
            String orgState = null;
            if (req.getParameter("orgState") == null){
                appendMap.put("orgState",0);
                orgState = "0";
            }else{
                orgState = req.getParameter("orgState");
            }
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            String company = req.getParameter("company");
            model.put("company", company);
            String areaType = req.getParameter("areaType");
            model.put("areaType", areaType);
            String type = req.getParameter("type");
            model.put("type", type);
            model.put("orgState", orgState);
            model.put("businessAttr", req.getParameter("businessAttr"));
            model.put("orgAttrTemp", req.getParameter("orgAttrTemp"));
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyConsignor/list",model);
        }
        //调查方认证
        else if("investigator".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String realName = req.getParameter("realName");
            model.put("realName", realName);
            String orgName = req.getParameter("orgName");
            model.put("orgName", orgName);
            String tel = req.getParameter("tel");
            model.put("tel", tel);
            String authType = req.getParameter("authType");
            model.put("authType", authType);
            req.setAttribute("apiRsp", apiFinalResponse);

            return new ModelAndView("/survey/surveyInvestigator/list",model);
        }
        //乐凡币介绍
        else if("lfcoinExplain".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyLfcoinExplainDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String remark = req.getParameter("remark");
            model.put("remark", remark);

            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyLfcoinExplain/list",model);
        }
        //乐凡币规则
        else if("lfcoinRule".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyLfcoinRuleDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String remark = req.getParameter("remark");
            model.put("remark", remark);

            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyLfcoinRule/list",model);
        }
        //成就点规则
        else if("achieveRule".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyAchieveRuleDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String remark = req.getParameter("remark");
            model.put("remark", remark);

            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/base/surveyAchieveRule/list",model);
        }
        //知识库类别
        else if("knowledgeType".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyKnowledgeTypeDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String name = req.getParameter("name");
            model.put("name", name);

            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyKnowledgeType/list",model);
        }
        //知识库论坛
        else if("knowledgeBase".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyKnowledgeBaseDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String title = req.getParameter("title");
            model.put("title", title);
            String knowledgeTypeId = req.getParameter("knowledgeTypeId");
            model.put("knowledgeTypeId", knowledgeTypeId);
            req.setAttribute("apiRsp", apiFinalResponse);

            //知识库类别(非分页数据)
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyKnowledgeTypeDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_KNOW_LEDGE_TYPE_LIST, null, req);
            List<SurveyKnowledgeTypeDto> typeList = (List<SurveyKnowledgeTypeDto>) apiFinalResponse.getResults();
            model.put("typeList",typeList);
            return new ModelAndView("/survey/surveyKnowledgeBase/list",model);
        }
        //帖子评论
        else if("knowledgeComment".equals(surveyCode)){

        }
        //调查委托方价格
        else if("consignorPrice".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorPriceDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String enturyName = req.getParameter("enturyName");
            model.put("enturyName", enturyName);

            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyConsignorPrice/list",model);
        }
        //调查调查方价格
        else if("franchiseePrice".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseePriceDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String franchiseeName = req.getParameter("franchiseeName");
            model.put("franchiseeName", franchiseeName);

            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyFranchiseePrice/list",model);
        }
        //QA问答
        else if("qa".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyQaDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String question = req.getParameter("question");
            model.put("question", question);
            String questionState = req.getParameter("questionState");
            model.put("questionState", questionState);
            String questionType = req.getParameter("questionType");
            model.put("questionType", questionType);
            req.setAttribute("apiRsp", apiFinalResponse);

            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap = new HashMap<String, Object>();
            appendMap.put("enumCode", "surveyQa");
            TypeToken typeToken2 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
            ApiFinalResponse apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
            List<CommonEnumDto> surveyQas = (List<CommonEnumDto>) apiFinalResponse2.getResults();
            model.put("surveyQas",surveyQas);
            return new ModelAndView("/survey/surveyQa/list",model);
        }
        //调查调查方
        else if("franchisee".equals(surveyCode)){
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode", "InvestigatorFranchisee");
            String orgState = null;
            if (req.getParameter("orgState") == null){
                appendMap.put("orgState",0);
                orgState = "0";
            }else{
                orgState = req.getParameter("orgState");
            }
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            String name = req.getParameter("name");
            model.put("name", name);
            String areaType = req.getParameter("areaType");
            model.put("areaType", areaType);
            String type = req.getParameter("type");
            model.put("type", type);
            model.put("orgState", orgState);
            model.put("busType", req.getParameter("busType"));

            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyFranchisee/list",model);
        }
        //商品
        else if("product".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyProductDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String name = req.getParameter("name");
            model.put("name", name);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyProduct/list",model);
        }
        //委托方默认价格
        else if("commonAreaPrice".equals(surveyCode)){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("parentId",0);  //列表式：多条数据添加
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("apiRspArea", apiRsp);

//            Map<String,Object> appendMap = new HashMap<String, Object>();
//            appendMap.put("menuType", 1);//不分页
//            appendMap.put("surveyCode", surveyCode);
//            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyCommonAreaPrice2Dto>>>() {};
//            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req);
//            List<SurveyCommonAreaPrice2Dto> surveyCommonAreaPrice2Dtos = (List<SurveyCommonAreaPrice2Dto>)apiFinalResponse.getResults();
//            req.setAttribute("surveyCommonAreaPrice2Dtos", surveyCommonAreaPrice2Dtos);
            //区分“保险类”、“互助类”
            String priceType = req.getParameter("priceType");
            if(priceType ==null || priceType ==""){
                priceType = "1";
            }
            //调查方价格
            Map<String,Object> appendMap = new HashMap<String, Object>();
            appendMap.put("menuType", 1);//不分页
            appendMap.put("surveyCode", surveyCode);
            appendMap.put("priceType", priceType);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyCommonAreaPrice2Dto>>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req);
            List<SurveyCommonAreaPrice2Dto> surveyCommonAreaPrice2Dtos = (List<SurveyCommonAreaPrice2Dto>)apiFinalResponse.getResults();
            model.put("surveyCommonAreaPrice2Dtos",surveyCommonAreaPrice2Dtos);

            //任务类型(不分页)
//            typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>(){};
//            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST, null, req);
//            model.put("taskInfos",apiFinalResponse.getResults());

            //区分“保险类”、“互助类”
            appendMap = new HashMap<String, Object>();
            appendMap.put("type", priceType);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfo2Dto>>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST_TEST, appendMap, req);
            model.put("taskInfos",apiFinalResponse.getResults());

            String areaName = req.getParameter("areaName");
            model.put("areaName", areaName);
            model.put("cityType",req.getParameter("cityType"));
            model.put("taskId",req.getParameter("taskId"));
            model.put("priceType",priceType);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyCommonAreaPrice/list",model);
        }
        //调查方默认价格
        else if("investigatorAreaPrice".equals(surveyCode)){
//            TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>(){};
//            ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST, null, req);
//            model.put("taskInfos",apiFinalResponse1.getResults());

            String priceType = req.getParameter("priceType");
            if(priceType ==null || priceType ==""){
                priceType = "1";
            }
            Map<String,Object> appendMap = new HashMap<String, Object>();
            appendMap.put("menuType", 1);//不分页
            appendMap.put("surveyCode", surveyCode);
            appendMap.put("priceType", priceType);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorAreaPrice2Dto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req);
            List<SurveyInvestigatorAreaPrice2Dto> surveyInvestigatorAreaPrice2Dtos = (List<SurveyInvestigatorAreaPrice2Dto>)apiFinalResponse.getResults();
            req.setAttribute("surveyInvestigatorAreaPrice2Dtos", surveyInvestigatorAreaPrice2Dtos);

            //区分“保险类”、“互助类”
            appendMap = new HashMap<String, Object>();
            appendMap.put("type", priceType);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfo2Dto>>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST_TEST, appendMap, req);
            model.put("taskInfos",apiFinalResponse.getResults());

            String areaName = req.getParameter("areaName");
            model.put("areaName", areaName);
            model.put("cityType",req.getParameter("cityType"));
            model.put("taskId",req.getParameter("taskId"));
            model.put("priceType",priceType);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyInvestigatorAreaPrice/list",model);
        }
        //订单
        else if("order".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyOrderDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String orderCode = req.getParameter("orderCode");
            model.put("orderCode", orderCode);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyOrder/list",model);
        }
        //服务区域表
        else if("serviceArea".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyServiceAreaDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String name = req.getParameter("name");
            model.put("name", name);

            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyServiceArea/list",model);
        }
        //处罚记录表
        else if("punish".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyPunishDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String surveyUserName = req.getParameter("surveyUserName");
            model.put("surveyUserName", surveyUserName);
            String isExec = req.getParameter("isExec");
            model.put("isExec", isExec);
            model.put("sortField",req.getParameter("sortField"));
            model.put("sortType",req.getParameter("sortType"));
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyPunish/list",model);
        }
        //提现记录表
        else if("cashInfo".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyCashInfoDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String cashInfoCode = req.getParameter("cashInfoCode");
            String applyUserName = req.getParameter("applyUserName");
            String applyUserTel = req.getParameter("applyUserTel");
            String cashState = req.getParameter("cashState");
            String confirmAccountState = req.getParameter("confirmAccountState");
            String paySource = req.getParameter("paySource");
            model.put("cashInfoCode", cashInfoCode);
            model.put("applyUserName", applyUserName);
            model.put("applyUserTel", applyUserTel);
            model.put("cashState", cashState);
            model.put("confirmAccountState", confirmAccountState);
            model.put("paySource", paySource);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyCashInfo/list",model);
        }
        //委托方机构-部门信息
        else if("consignorDepartment".equals(surveyCode)){
            String consignorId = req.getParameter("consignorId");
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("consignorId", consignorId);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDepartmentDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String name = req.getParameter("name");
            model.put("name", name);
            model.put("consignorId",consignorId);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyConsignorDepartment/list",model);
        }
        //任务类型-方向名称
        else if("taskInfoContent".equals(surveyCode)){
            String taskInfoId = req.getParameter("taskInfoId");
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("taskInfoId", taskInfoId);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoContentDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String name = req.getParameter("name");
            model.put("name", name);
            model.put("taskInfoId",taskInfoId);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyTaskInfoContent/list",model);
        }
        //提现列表（全部提现数据、可提现数据）
        else if("cashInfoRecord".equals(surveyCode)){
            Map<String, Object> appendMap = new HashMap<String, Object>();
            String menuCode = req.getParameter("menuCode");
            //待提现数据
            if(menuCode!=null && "toBe-cash-list".equals(menuCode)){
                appendMap.put("menuType", 1);//不分页
            }
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyCashInfoRecordDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            String surveyCaseName = req.getParameter("surveyCaseName");
            model.put("surveyCaseName", surveyCaseName);
            String franchiseeName = req.getParameter("franchiseeName");
            model.put("franchiseeName", franchiseeName);
            String surveyUserName = req.getParameter("surveyUserName");
            model.put("surveyUserName", surveyUserName);
            String cashState = req.getParameter("cashState");
            model.put("cashState", cashState);
            String cashType = req.getParameter("cashType");
            model.put("cashType", cashType);
            req.setAttribute("apiRsp", apiFinalResponse);
            if(menuCode!=null && "toBe-cash-list".equals(menuCode)){
                model.put("menuCode", menuCode);
                appendMap.clear();
                appendMap.put("menuCode",menuCode);
                appendMap.put("btnCode",1000);
                typeToken = new TypeToken<ApiFinalResponse<SurveyBankCardDto>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req);
                req.setAttribute("surveyBankCard", apiFinalResponse.getResults());

                return new ModelAndView("/survey/surveyCashInfoRecord/cashList",model);
            }
            return new ModelAndView("/survey/surveyCashInfoRecord/list",model);
        }
        //开票-开票公司
        else if("applyCorporation".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String name = req.getParameter("name");
            model.put("name", name);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/billing/applyCorporation/list",model);
        }
        //开票权限
        else if("applyRole".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BusinessRoleDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_ROLE_LIST, null, req);
            String name = req.getParameter("name");
            model.put("name", name);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/billing/applyRole/role",model);
        }
        else if("setrole".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BusinessRoleDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_ROLE_LIST, null, req);
            String roleName = req.getParameter("roleName");
            model.put("roleName", roleName);
            model.put("leftId",req.getParameter("leftId"));
            model.put("noPageIndex",1);
            model.put("searchType",req.getParameter("searchType"));
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/billing/applyRole/listRole",model);
        }
        //开票-开票产品类型
        else if("applyProductType".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyProductTypeDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String name = req.getParameter("name");
            model.put("name", name);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/billing/applyProductType/list",model);
        }
        //开票-开票产品
        else if("billingEnum".equals(surveyCode)){
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap = new HashMap<String, Object>();
            appendMap.put("enumCode", "billingEnum");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            List<CommonEnumDto> bullingEnums = (List<CommonEnumDto>) apiFinalResponse.getResults();
            if(bullingEnums.size() > 0){
                model.put("parentId",bullingEnums.get(0).getParentId());
            }
            String enumName = req.getParameter("enumName");
            model.put("enumName", enumName);
            return new ModelAndView("/survey/billing/billingEnum/list",model);
        }
        //开票-开票项目
        else if("billingItem".equals(surveyCode)){
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap = new HashMap<String, Object>();
            appendMap.put("enumCode", "billingItem");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            List<CommonEnumDto> billingItems = (List<CommonEnumDto>) apiFinalResponse.getResults();
            if(billingItems.size() > 0){
                model.put("parentId",billingItems.get(0).getParentId());
            }
            String enumName = req.getParameter("enumName");
            model.put("enumName", enumName);
            return new ModelAndView("/survey/billing/billingItem/list",model);
        }
        //狄大人平台终审人员
        else if("finalJudgmentUser".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<UserInfo>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String userName = req.getParameter("userName");
            model.put("userName", userName);
            String userTel = req.getParameter("userTel");
            model.put("userTel", userTel);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyUser/list",model);
        }
        //报告模板
        else if("modelInfo".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyModelInfoDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String name = req.getParameter("name");
            model.put("name", name);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyModelInfo/list",model);
        }
        //通知中心
        else if("message".equals(surveyCode)){
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("messageType", 4);//消息类型(1:系统消息，2：客服消息，3：其他消息)
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyMessageDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            //获取当前登录人
            UserInfo adminSession = this.getSessionAdmin(req);
            model.put("toUserId",adminSession.getUserId());

            return new ModelAndView("/survey/case/listMessage",model);
        }
        //委托方机构-开票主体
        else if("consignorBillSubject".equals(surveyCode)){
            String consignorId = req.getParameter("consignorId");
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("consignorId", consignorId);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyCompanyDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String companyName = req.getParameter("companyName");
            model.put("companyName", companyName);
            model.put("consignorId",consignorId);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyConsignorBillSubject/list",model);
        }
        //方向结果类型
        else if("directionResultType".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyDirectionResultTypeDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String name = req.getParameter("name");
            model.put("name", name);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/surveyDirectionResultType/list",model);
        }
        //方向结果类型
        else if("priceModel".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyPriceModelDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            String name = req.getParameter("name");
            model.put("name", name);
            String type = req.getParameter("type");
            model.put("type", type);
            model.put("useObj", req.getParameter("useObj"));
            return new ModelAndView("/survey/surveyPriceModel/list",model);
        }
        //委托时效模板
        else if("consignorEfficiencyModel".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorEfficiencyModelDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            String name = req.getParameter("name");
            model.put("name", name);
            String type = req.getParameter("type");
            model.put("type", type);
            return new ModelAndView("/survey/base/surveyConsignorEfficiencyModel/list",model);
        }
        else if("channelModel".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyChannelModel>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            String name = req.getParameter("name");
            model.put("name", name);
            String type = req.getParameter("type");
            model.put("type", type);
            return new ModelAndView("/survey/base/channelModel/list",model);
        }
        else if("scoreModel".equals(surveyCode)){//区域分值系数
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyChannelModel>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            String name = req.getParameter("name");
            model.put("name", name);
            return new ModelAndView("/survey/base/scoreModel/list",model);
        }
        else if ("areaInfo".equals(surveyCode)){
            String parentId = req.getParameter("parentId");
            model.put("parentId",parentId);
            return null;
        }
        //邮件模板
        else if("emailInfo".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyEmailInfoDTO>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
            String emailUserName = req.getParameter("emailUserName");
            model.put("emailUserName", emailUserName);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/base/surveyEmailInfo/list",model);
        }
        return null;
    }

    /**
     * add页面
     */
    @RequestMapping(value = "/add")
    public ModelAndView add(HttpServletRequest req, HttpServletResponse rsp){

        String surveyCode = req.getParameter("surveyCode");
        Map model = new HashMap();
        model.put("surveyCode", surveyCode);//code标识

        //领域类型
        if("businessType".equals(surveyCode)){
            return new ModelAndView("/survey/surveyBusinessType/edit",model);
        }
        //业务类型
        if("serviceType".equals(surveyCode)){
            return new ModelAndView("/survey/surveyServiceType/edit",model);
        }
        //任务类型
        else if("taskInfo".equals(surveyCode)){
            return new ModelAndView("/survey/surveyTaskInfo/edit",model);
        }
        //材料目录
        else if("fileCatalog".equals(surveyCode)){
            return new ModelAndView("/survey/surveyFileCatalog/edit",model);
        }
        //调查员登记
        else if("level".equals(surveyCode)){
            return new ModelAndView("/survey/surveyLevel/edit",model);
        }
        //称号特权详情
        else if("levelExplain".equals(surveyCode)){
            return new ModelAndView("/survey/surveyLevelExplain/edit",model);
        }
        //平台介绍
        else if("introduction".equals(surveyCode)){
            return new ModelAndView("/survey/surveyIntroduction/edit",model);
        }
        //服务优势
        else if("serviceAdvantage".equals(surveyCode)){
            return new ModelAndView("/survey/surveyServiceAdvantage/edit",model);
        }
        //委托人认证
        else if("consigner".equals(surveyCode)){
            return new ModelAndView("/survey/surveyConsigner/edit",model);
        }
        //委托方机构
        else if("consignor".equals(surveyCode)){
            //地区信息
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("parentId",0);
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("apiRsp", apiRsp);

            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("menuType", 1);//不分页
            appendMap.put("surveyCode", "modelInfo");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyModelInfoDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyModelInfoDto> surveyModelInfoDtos = (List<SurveyModelInfoDto>)apiFinalResponse.getResults();
            req.setAttribute("surveyModelInfoDtos", surveyModelInfoDtos);
            //查询市场人员
            Map<String, Object> appendMap1 = new HashMap<String, Object>();
            appendMap1.put("roleId", 136);//
            TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
            ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_SURVEY_USER_LIST_BY_ROLE, appendMap1, req);
            List<BusUserRoleDto> busUserRoleDtoList1 = (List<BusUserRoleDto>)apiFinalResponse1.getResults();
            req.setAttribute("busUserRoleDtoList1", busUserRoleDtoList1);
            //查询市场引荐人员
            Map<String, Object> appendMap2 = new HashMap<String, Object>();
            appendMap2.put("roleId", 137);//
            TypeToken typeToken2 = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
            ApiFinalResponse apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_SURVEY_USER_LIST_BY_ROLE, appendMap2, req);
            List<BusUserRoleDto> busUserRoleDtoList2 = (List<BusUserRoleDto>)apiFinalResponse2.getResults();
            req.setAttribute("busUserRoleDtoList2", busUserRoleDtoList2);

            //开票公司(人事系统  预算归属公司)
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","budgetCompany");
            appendMap.put("havePage","no");
            appendMap.put("state",0);
            typeToken = new TypeToken<ApiFinalResponse<List<StaffBudgetCompanyDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffBudgetCompanyDto> billCompanys = (List<StaffBudgetCompanyDto>)apiFinalResponse.getResults();
            model.put("billCompanys", billCompanys);

            return new ModelAndView("/survey/surveyConsignor/add",model);
        }
        //调查方认证
        else if("investigator".equals(surveyCode)){

        }
        //乐凡币介绍
        else if("lfcoinExplain".equals(surveyCode)){
            return new ModelAndView("/survey/surveyLfcoinExplain/edit",model);
        }
        //乐凡币规则
        else if("lfcoinRule".equals(surveyCode)){
            return new ModelAndView("/survey/surveyLfcoinRule/edit",model);
        }
        //成就点规则
        else if("achieveRule".equals(surveyCode)){
            return new ModelAndView("/survey/base/surveyAchieveRule/edit",model);
        }
        //知识库类别
        else if("knowledgeType".equals(surveyCode)){
            return new ModelAndView("/survey/surveyKnowledgeType/edit",model);
        }
        //知识库论坛
        else if("knowledgeBase".equals(surveyCode)){
            //知识库类别(非分页数据)
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyKnowledgeTypeDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_KNOW_LEDGE_TYPE_LIST, null, req);
            List<SurveyKnowledgeTypeDto> typeList = (List<SurveyKnowledgeTypeDto>) apiFinalResponse.getResults();
            model.put("typeList",typeList);

            return new ModelAndView("/survey/surveyKnowledgeBase/add",model);
        }
        //帖子评论
        else if("knowledgeComment".equals(surveyCode)){

        }
        //调查委托方价格
        else if("consignorPrice".equals(surveyCode)){
            //地区信息
            Map<String, Object> map = new HashMap<String, Object>();
            String addType = req.getParameter("type");//两种添加方式


            //任务类型old
//            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>(){};
//            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST, null, req);
//            model.put("taskInfos",apiFinalResponse.getResults());

            //获取所有信息（任务类型--任务子类--方向任务结果）
            //任务类型
            String priceType = req.getParameter("priceType");
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("type", priceType);//不分页
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfo2Dto>>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST_TEST, appendMap, req);
            model.put("taskInfos",apiFinalResponse.getResults());
            model.put("priceType",priceType);

            model.put("enturyId",req.getParameter("consignorId"));
            model.put("btnCode",req.getParameter("btnCode"));
            model.put("enturyName",req.getParameter("consignorName"));

            //所有价格体系
            //列表多选

            if(addType!=null){
                map.put("parentId",req.getParameter("parentId"));  //列表式：多条数据添加
            }else{
                map.put("parentId",0);//单条添加
            }
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("apiRsp", apiRsp);

            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType", 1);//不分页
            appendMap.put("surveyCode", "consignorPrice");
            appendMap.put("enturyId", req.getParameter("consignorId"));
            appendMap.put("taskId", req.getParameter("taskId")); //任务类别
            appendMap.put("areaId", req.getParameter("parentId"));

            typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorPriceDto>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
            model.put("surveyConsignorPrice",apiFinalResponse.getResults());


            if(addType!=null){//列表式：多条数据添加
                if("1".equals(addType)){
                    return new ModelAndView("/survey/surveyConsignorPrice/addNew",model);
                }
                //省会、地级市、县级市
                else if("2".equals(addType)){
                    model.put("areaId",req.getParameter("parentId"));
                    model.put("areaName",req.getParameter("parentName"));
                    //直辖市
                    if(req.getParameter("cityType") !=null && "3".equals(req.getParameter("cityType"))){
                        return new ModelAndView("/survey/surveyConsignorPrice/addNew",model);
                    }
                    return new ModelAndView("/survey/surveyConsignorPrice/addNewCity",model);
                }
            }
            return new ModelAndView("/survey/surveyConsignorPrice/add",model);
        }
        //调查调查方价格
        else if("franchiseePrice".equals(surveyCode)){
            //地区信息
            Map<String, Object> map = new HashMap<String, Object>();
            String addType = req.getParameter("type");//两种添加方式
            if(addType!=null){
                map.put("parentId",req.getParameter("parentId"));  //列表式：多条数据添加
            }else{
                map.put("parentId",0);//单条添加
            }
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("apiRsp", apiRsp);

            //获取所有的任务类型（old）
//            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>(){};
//            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST, null, req);
//            model.put("taskInfos",apiFinalResponse.getResults());

            //获取所有信息（任务类型--任务子类--方向任务结果）
            //任务类型
            String priceType = req.getParameter("priceType");
            //区分“保险类”、“互助类”
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("type", priceType);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfo2Dto>>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST_TEST, appendMap, req);
            model.put("taskInfos",apiFinalResponse.getResults());

            model.put("franchiseeId",req.getParameter("franchiseeId"));
            model.put("btnCode",req.getParameter("btnCode"));
            model.put("franchiseeName",req.getParameter("franchiseeName"));

            //所有价格体系
            //列表多选
            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType", 1);//不分页
            appendMap.put("surveyCode", "franchiseePrice");
            appendMap.put("franchiseeId", req.getParameter("franchiseeId"));
            appendMap.put("taskId", req.getParameter("taskId")); //任务类别
            appendMap.put("areaId", req.getParameter("parentId"));
            appendMap.put("priceType", priceType);

            typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseePriceDto>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
            model.put("surveyFranchiseePrice",apiFinalResponse.getResults());
            model.put("priceType", priceType);//价格类型（1：保险版；2、互助版）

            if(addType!=null){//列表式：多条数据添加
                if("1".equals(addType)){
                    return new ModelAndView("/survey/surveyFranchiseePrice/addNew",model);
                }
                //省会、地级市、县级市
                else if("2".equals(addType)){
                    model.put("areaId",req.getParameter("parentId"));
                    model.put("areaName",req.getParameter("parentName"));
                    //直辖市
                    if(req.getParameter("cityType") !=null && "3".equals(req.getParameter("cityType"))){
                        return new ModelAndView("/survey/surveyFranchiseePrice/addNew",model);
                    }
                    return new ModelAndView("/survey/surveyFranchiseePrice/addNewCity",model);
                }
            }

            return new ModelAndView("/survey/surveyFranchiseePrice/add",model);
        }
        //QA问答
        else if("qa".equals(surveyCode)){
            //开票项目 -- 枚举查询
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap = new HashMap<String, Object>();
            appendMap.put("enumCode", "surveyQa");
            TypeToken typeToken2 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
            ApiFinalResponse apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
            List<CommonEnumDto> surveyQas = (List<CommonEnumDto>) apiFinalResponse2.getResults();
            model.put("surveyQas",surveyQas);
            return new ModelAndView("/survey/surveyQa/edit",model);
        }
        //调查调查方
        else if("franchisee".equals(surveyCode)){
            //地区信息
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("parentId",0);
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("apiRsp", apiRsp);

            //关联人事管理-机构/部门信息
            map = new HashMap<String, Object>();
            map.put("surveyCode","organ");
            map.put("havePage","1");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffOrganDto>>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_STAFF_LIST, map, null);
            List<StaffOrganDto> staffOrganList=(List<StaffOrganDto>)apiFinalResponse.getResults();
            model.put("staffOrganList", staffOrganList);
            return new ModelAndView("/survey/surveyFranchisee/add",model);
        }
        //商品
        else if("product".equals(surveyCode)){
            return new ModelAndView("/survey/surveyProduct/add",model);
        }
        //委托方默认价格
        else if("commonAreaPrice".equals(surveyCode)){
            Map<String, Object> map = new HashMap<String, Object>();
            String addType = req.getParameter("type");//两种添加方式
            if(addType!=null){
                map.put("parentId",req.getParameter("parentId"));  //列表式：多条数据添加
            }else{
                map.put("parentId",0);//单条添加
            }
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("apiRsp", apiRsp);

            //任务类型old
//            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>(){};
//            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST, null, req);
//            model.put("taskInfos",apiFinalResponse.getResults());

            //获取所有信息（任务类型--任务子类--方向任务结果）
            //任务类型
            String priceType = req.getParameter("priceType");
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("type", priceType);//保险版，互助版
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfo2Dto>>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST_TEST, appendMap, req);
            model.put("taskInfos",apiFinalResponse.getResults());

            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType", 2);//不分页
            appendMap.put("surveyCode", "commonAreaPrice");
            appendMap.put("areaId", req.getParameter("parentId"));

            typeToken = new TypeToken<ApiFinalResponse<List<SurveyCommonAreaPriceDto>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
            model.put("surveyCommonAreaPrice",apiFinalResponse.getResults());
            model.put("priceType",priceType);
            if(addType!=null) {//列表式：多条数据添加
                model.put("areaId", req.getParameter("parentId"));
                model.put("areaName",req.getParameter("parentName"));
                //直辖市
                if (req.getParameter("cityType") != null&& "3".equals(req.getParameter("cityType"))) {
                    return new ModelAndView("/survey/surveyCommonAreaPrice/addNew", model);
                }
                return new ModelAndView("/survey/surveyCommonAreaPrice/addNewCity", model);

            }
            return new ModelAndView("/survey/surveyCommonAreaPrice/add",model);
        }
        //调查方默认价格
        else if("investigatorAreaPrice".equals(surveyCode)){
            Map<String, Object> map = new HashMap<String, Object>();
            String addType = req.getParameter("type");//两种添加方式
            if(addType!=null){
                map.put("parentId",req.getParameter("parentId"));  //列表式：多条数据添加
            }else{
                map.put("parentId",0);//单条添加
            }
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("apiRsp", apiRsp);

            //任务类型old
//            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>(){};
//            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST, null, req);
//            model.put("taskInfos",apiFinalResponse.getResults());

            //获取所有信息（任务类型--任务子类--方向任务结果）
            //任务类型
            String priceType = req.getParameter("priceType");
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("type", priceType);//保险版，互助版
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfo2Dto>>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST_TEST, appendMap, req);
            model.put("taskInfos",apiFinalResponse.getResults());


            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType", 2);//不分页
            appendMap.put("surveyCode", "investigatorAreaPrice");
            appendMap.put("areaId", req.getParameter("parentId"));
            appendMap.put("priceType",priceType);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorAreaPriceDto>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
            model.put("surveyInvestigatorAreaPrice",apiFinalResponse.getResults());
            model.put("priceType",priceType);

            if(addType!=null) {//列表式：多条数据添加
                model.put("areaId", req.getParameter("parentId"));
                model.put("areaName",req.getParameter("parentName"));

                //直辖市
                if (req.getParameter("cityType") != null&& "3".equals(req.getParameter("cityType"))) {
                    return new ModelAndView("/survey/surveyInvestigatorAreaPrice/addNew", model);
                }
                return new ModelAndView("/survey/surveyInvestigatorAreaPrice/addNewCity", model);

            }
            return new ModelAndView("/survey/surveyInvestigatorAreaPrice/add",model);

        }
        //服务区域表
        else if("serviceArea".equals(surveyCode)){
            //地区信息
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("parentId",0);
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("apiRsp", apiRsp);
            return new ModelAndView("/survey/surveyServiceArea/add",model);
        }
        //委托方机构-部门信息
        else if("consignorDepartment".equals(surveyCode)){
            model.put("consignorId", req.getParameter("consignorId"));
            return new ModelAndView("/survey/surveyConsignorDepartment/edit",model);
        }
        //任务类型-方向名称
        else if("taskInfoContent".equals(surveyCode)){
            model.put("taskInfoId", req.getParameter("taskInfoId"));
            return new ModelAndView("/survey/surveyTaskInfoContent/edit",model);
        }
        //添加用户
        else if("userInfo".equals(surveyCode)){
            return new ModelAndView("/user/surveyUserAdd",model);
        }
        //提现--银行卡信息
        else if("bankCard".equals(surveyCode)){
            return new ModelAndView("/survey/surveyCashInfoRecord/editBankCard",model);
        }
        //开票-开票公司
        else if("applyCorporation".equals(surveyCode)){
            return new ModelAndView("/survey/billing/applyCorporation/edit",model);
        }
        //开票-公司对应产品
        else if("applyCorporationEnum".equals(surveyCode)){

            //开票产品 -- 枚举查询
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("enumCode", "billingEnum");
            TypeToken typeToken5 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
            ApiFinalResponse apiFinalResponse5= this.callApi(typeToken5, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
            List<CommonEnumDto> bullingEnums = (List<CommonEnumDto>) apiFinalResponse5.getResults();
            model.put("bullingEnums",bullingEnums);
            //开票项目 -- 枚举查询
//            appendMap = new HashMap<String, Object>();
//            appendMap.put("enumCode", "billingItem");
//            typeToken5 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
//            apiFinalResponse5= this.callApi(typeToken5, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
//            List<CommonEnumDto> bullingItems = (List<CommonEnumDto>) apiFinalResponse5.getResults();
//            model.put("bullingItems",bullingItems);

            model.put("applyCorporationId",req.getParameter("applyCorporationId"));

            //公司下已经存在的产品
            appendMap = new HashMap<String, Object>();
            appendMap.put("corporationId", req.getParameter("applyCorporationId"));
            typeToken5 = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationEnumDto>>>() {};
            apiFinalResponse5= this.callApi(typeToken5, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<BillingApplyCorporationEnumDto> corporationEnums = (List<BillingApplyCorporationEnumDto>) apiFinalResponse5.getResults();
            model.put("corporationEnums",corporationEnums);
            model.put("type",1);//因为公司对应产品，产品对应项目用的是同一个页面，多以多选框区分
            return new ModelAndView("/survey/billing/applyEnumItem/edit",model);
        }
        //开票-开票产品对应 项目
        else if("applyEnumItem".equals(surveyCode)){

            //开票项目 -- 枚举查询
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("enumCode", "billingItem");
            TypeToken typeToken5 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
            ApiFinalResponse apiFinalResponse5= this.callApi(typeToken5, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
            List<CommonEnumDto> bullingEnums = (List<CommonEnumDto>) apiFinalResponse5.getResults();
            model.put("bullingEnums",bullingEnums);
            model.put("applyCorporationId",req.getParameter("applyCorporationId"));
            model.put("billingEnumId",req.getParameter("billingEnumId"));

            //产品下已经存在的项目
            appendMap = new HashMap<String, Object>();
            appendMap.put("corporationId", req.getParameter("applyCorporationId"));
            appendMap.put("billingEnumId", req.getParameter("billingEnumId"));
            typeToken5 = new TypeToken<ApiFinalResponse<List<BillingApplyEnumItemDto>>>() {};
            apiFinalResponse5= this.callApi(typeToken5, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<BillingApplyEnumItemDto> enumItems = (List<BillingApplyEnumItemDto>) apiFinalResponse5.getResults();
            model.put("enumItems",enumItems);
            model.put("type",2);//因为公司对应产品，产品对应项目用的是同一个页面，多以多选框区分
            return new ModelAndView("/survey/billing/applyEnumItem/edit",model);
        }
        //开票-开票产品类型
        else if("applyProductType".equals(surveyCode)){
            return new ModelAndView("/survey/billing/applyProductType/edit",model);
        }
        //开票-开票产品
        else if("billingEnum".equals(surveyCode)){
            model.put("enumCode",req.getParameter("enumCode"));
            model.put("parentId",req.getParameter("parentId"));
            return new ModelAndView("/survey/billing/billingEnum/edit",model);
        }
        //开票-开票项目
        else if("billingItem".equals(surveyCode)){
            model.put("enumCode",req.getParameter("enumCode"));
            model.put("parentId",req.getParameter("parentId"));
            return new ModelAndView("/survey/billing/billingItem/edit",model);
        }
        //报告模板
        else if("modelInfo".equals(surveyCode)){
            return new ModelAndView("/survey/surveyModelInfo/edit",model);
        }
        //委托方机构-开票主体
        else if("consignorBillSubject".equals(surveyCode)){
            model.put("consignorId", req.getParameter("consignorId"));
            return new ModelAndView("/survey/surveyConsignorBillSubject/edit",model);
        }
        //方向结果类型
        else if("directionResultType".equals(surveyCode)){
            return new ModelAndView("/survey/surveyDirectionResultType/edit",model);
        }
        //价格模板
        else if("priceModel".equals(surveyCode)){
            return new ModelAndView("/survey/surveyPriceModel/edit",model);
        }
        //区域类别对应具体区域
        else if("priceModelAreaCategories".equals(surveyCode)){
            Map hashMap = new HashMap();
            hashMap.put("fromType","add");
            hashMap.put("surveyCode",surveyCode);
            model.put("priceModelId", req.getParameter("priceModelId"));//价格模板id
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_AREA_TYPE_LIST, hashMap, req);
            List<CommonArea> commonAreaList= (List<CommonArea>) apiFinalResponse.getResults();
            model.put("commonAreaList",commonAreaList);
            return new ModelAndView("/survey/surveyPriceModel/areaCityEdit",model);
        }
        //委托时效模板
        else if("consignorEfficiencyModelArea".equals(surveyCode)){
            Map hashMap = new HashMap();
            hashMap.put("fromType","add");
            hashMap.put("surveyCode",surveyCode);
            model.put("modelId", req.getParameter("modelId"));//价格模板id
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_AREA_TYPE_LIST, hashMap, req);
            List<CommonArea> commonAreaList= (List<CommonArea>) apiFinalResponse.getResults();
            model.put("commonAreaList",commonAreaList);
            return new ModelAndView("/survey/base/surveyConsignorEfficiencyModel/areaCityEdit",model);
        }
        //委托时效模板
        else if("consignorEfficiencyModel".equals(surveyCode)){
            //获取所有业务类型
//            Map<String,Object> appendMap = new HashMap<String, Object>();
//            appendMap.put("sortRule",1);
//            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyServiceTypeDto>>>(){};
//            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SERVICE_TYPE_LIST, appendMap, req);
//            List<SurveyServiceTypeDto> services = (List<SurveyServiceTypeDto>)apiFinalResponse.getResults();
//            model.put("services",services);
            model.put("type",req.getParameter("type"));
            return new ModelAndView("/survey/base/surveyConsignorEfficiencyModel/edit",model);
        }
        else if("channelModelArea".equals(surveyCode)){
            Map hashMap = new HashMap();
            hashMap.put("fromType","add");
            hashMap.put("surveyCode",surveyCode);
            model.put("modelId", req.getParameter("modelId"));//价格模板id
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_AREA_TYPE_LIST, hashMap, req);
            List<CommonArea> commonAreaList= (List<CommonArea>) apiFinalResponse.getResults();
            model.put("commonAreaList",commonAreaList);
            return new ModelAndView("/survey/base/channelModel/areaCityEdit",model);
        }
        else if("scoreModelArea".equals(surveyCode)){
            Map hashMap = new HashMap();
            hashMap.put("fromType","add");
            hashMap.put("surveyCode",surveyCode);
            model.put("modelId", req.getParameter("modelId"));//价格模板id
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_AREA_TYPE_LIST, hashMap, req);
            List<CommonArea> commonAreaList= (List<CommonArea>) apiFinalResponse.getResults();
            model.put("commonAreaList",commonAreaList);
            return new ModelAndView("/survey/base/scoreModel/areaCityEdit",model);
        }
        //委托时效模板
        else if("channelModel".equals(surveyCode)){
            //获取所有业务类型
            model.put("type",req.getParameter("type"));
            return new ModelAndView("/survey/base/channelModel/edit",model);
        }
        else if("scoreModel".equals(surveyCode)){
            //获取所有业务类型
            model.put("type",req.getParameter("type"));
            return new ModelAndView("/survey/base/scoreModel/edit",model);
        }
        //调查方片区
        else if("areaInformation".equals(surveyCode)){
            model.put("franchiseeId",req.getParameter("franchiseeId"));
            model.put("type","insert");
            return new ModelAndView("/survey/surveyFranchisee/addAreaInformation",model);
        }
        //调查方片区人员添加
        else if("addAreaPersonnel".equals(surveyCode)){
            String category=req.getParameter("category");
            String franchiseeId=req.getParameter("franchiseeId");//机构表Id
            String areaId=req.getParameter("areaId");//机构片区表Id
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","investigator");
            appendMap.put("orgId",franchiseeId);
            appendMap.put("menuType","area");
            if("select".equals(category)){
                appendMap.put("surveyAreaId",areaId);
            }
            TypeToken typeToken5 = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {};
            ApiFinalResponse apiFinalResponse5= this.callApi(typeToken5, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyInvestigatorDto> surveyInvestigatorDto = (List<SurveyInvestigatorDto>) apiFinalResponse5.getResults();
            model.put("surveyInvestigatorDto",surveyInvestigatorDto);
            model.put("franchiseeId",franchiseeId);
            model.put("category",category);
            model.put("areaId",areaId);
            return new ModelAndView("/survey/surveyFranchisee/addAreaPersonnel",model);
        }
        //邮箱模板
        else if("emailInfo".equals(surveyCode)){
            return new ModelAndView("/survey/base/surveyEmailInfo/edit",model);
        }
        return null;
    }

    /**
     * edit页面
     */
    @RequestMapping(value = "/edit")
    public ModelAndView edit(HttpServletRequest req, HttpServletResponse rsp) {

        String surveyCode = req.getParameter("surveyCode");
        Map model = new HashMap();
        model.put("surveyCode", surveyCode);//code标识

        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode",surveyCode);
        //领域类型
        if("businessType".equals(surveyCode)) {
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyBusinessTypeDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyBusinessType", apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyBusinessType/edit", model);
        }
        //领域类型
        else if("serviceType".equals(surveyCode)) {
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyServiceTypeDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyServiceType", apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyServiceType/edit", model);
        }
        //任务类型
        else if("taskInfo".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyTaskInfoDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyTaskInfo",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyTaskInfo/edit",model);
        }
        //材料目录
        else if("fileCatalog".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyFileCatalogDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyFileCatalog",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyFileCatalog/edit",model);
        }
        //调查员登记
        else if("level".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyLevelDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyLevel",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyLevel/edit",model);
        }
        //称号特权详情
        else if("levelExplain".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyLevelExplainDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyLevelExplain",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyLevelExplain/edit",model);
        }
        //平台介绍
        else if("introduction".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyIntroductionDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyIntroduction",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyIntroduction/edit",model);
        }
        //服务优势
        else if("serviceAdvantage".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyServiceAdvantageDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyServiceAdvantage",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyServiceAdvantage/edit",model);
        }
        //委托人认证
        else if("consigner".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyConsignerDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyConsigner",apiFinalResponse.getResults());

            appendMap.clear();
            appendMap.put("surveyCode", "consignorDepartment");
            appendMap.put("consignorId", req.getParameter("consignorId"));
            appendMap.put("menuType", 1);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDepartmentDto>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
            model.put("surveyConsignorDepartment",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyConsigner/edit",model);
        }
        //委托方机构
        else if("consignor".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyConsignorDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            SurveyConsignorDto consignor = (SurveyConsignorDto)apiFinalResponse.getResults();
            model.put("surveyConsignor",consignor);

            //地区信息
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("parentId",0);
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("apiRsp", apiRsp);

            //市信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",consignor.getProvinceId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> cityApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("cityApiRsp", cityApiRsp);

            //区消息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId", consignor.getCityId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> districtApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("districtApiRsp", districtApiRsp);

            //所有的报告模板
            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType", 1);//不分页
            appendMap.put("surveyCode", "modelInfo");
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyModelInfoDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyModelInfoDto> surveyModelInfoDtos = (List<SurveyModelInfoDto>)apiFinalResponse.getResults();
            req.setAttribute("surveyModelInfoDtos", surveyModelInfoDtos);

            //该委托机构下对应的报告模板
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode", "consignorModel");
            appendMap.put("consignorId", req.getParameter("id"));
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorModelDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyConsignorModelDto> surveyConsignorModelDtos = (List<SurveyConsignorModelDto>)apiFinalResponse.getResults();
            if(surveyConsignorModelDtos!=null && surveyConsignorModelDtos.size()>0){
                model.put("surveyConsignorModel", surveyConsignorModelDtos.get(0));
            }

            //开票公司(人事系统  预算归属公司)
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","budgetCompany");
            appendMap.put("havePage","no");
            appendMap.put("state",0);
            typeToken = new TypeToken<ApiFinalResponse<List<StaffBudgetCompanyDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffBudgetCompanyDto> billCompanys = (List<StaffBudgetCompanyDto>)apiFinalResponse.getResults();
            model.put("billCompanys", billCompanys);

            return new ModelAndView("/survey/surveyConsignor/edit",model);
        }
        //调查方认证
        else if("investigator".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyInvestigatorDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            SurveyInvestigatorDto investigatorDto = (SurveyInvestigatorDto)apiFinalResponse.getResults();
            //地区信息
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("parentId",0);
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("apiRsp", apiRsp);

            //市信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",investigatorDto.getProvinceId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> cityApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("cityApiRsp", cityApiRsp);

            //区消息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId", investigatorDto.getCityId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> districtApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("districtApiRsp", districtApiRsp);
            model.put("surveyInvestigator",apiFinalResponse.getResults());

            appendMap.clear();
            appendMap.put("surveyCode","level");
            appendMap.put("menuType", 1);//不分页
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyLevelDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyLevelDto> levelInfo = (List<SurveyLevelDto>)apiFinalResponse.getResults();
            model.put("levelInfo",levelInfo);

            appendMap.clear();
            appendMap.put("consignType", 2);
            appendMap.put("id", req.getParameter("id"));
            typeToken = new TypeToken<ApiFinalResponse<List<CommonFile>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_COMMON_FILE, appendMap, null);
            model.put("apiRspFile",apiFinalResponse);

            appendMap=new HashMap<>();
            appendMap.put("isNewPeople","1");
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INVESTIGATOR_ALLLIST, appendMap, null);
            List<SurveyInvestigatorDto> allInvestigatorsList=(List<SurveyInvestigatorDto>)apiFinalResponse.getResults();
            model.put("allInvestigatorsList",allInvestigatorsList);
            return new ModelAndView("/survey/surveyInvestigator/edit",model);
        }
        //乐凡币介绍
        else if("lfcoinExplain".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyLfcoinExplainDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyLfcoinExplain",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyLfcoinExplain/edit",model);
        }
        //乐凡币规则
        else if("lfcoinRule".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyLfcoinRuleDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyLfcoinRule",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyLfcoinRule/edit",model);
        }
        //成就点规则
        else if("achieveRule".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyAchieveRuleDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyAchieveRule",apiFinalResponse.getResults());
            return new ModelAndView("/survey/base/surveyAchieveRule/edit",model);
        }
        //知识库类别
        else if("knowledgeType".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyKnowledgeTypeDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyKnowledgeType",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyKnowledgeType/edit",model);
        }
        //知识库论坛
        else if("knowledgeBase".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyKnowledgeBaseDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyKnowledgeBase",apiFinalResponse.getResults());

            //知识库类别(非分页数据)
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyKnowledgeTypeDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_KNOW_LEDGE_TYPE_LIST, null, req);
            List<SurveyKnowledgeTypeDto> typeList = (List<SurveyKnowledgeTypeDto>) apiFinalResponse.getResults();
            model.put("typeList",typeList);
            return new ModelAndView("/survey/surveyKnowledgeBase/edit",model);
        }
        //帖子评论
        else if("knowledgeComment".equals(surveyCode)){

        }
        //调查委托商价格
        else if("consignorPrice".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyConsignorPriceDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            SurveyConsignorPriceDto consignorPrice = (SurveyConsignorPriceDto)apiFinalResponse.getResults();
            model.put("surveyConsignorPrice",consignorPrice);
            //地区信息
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("parentId",0);
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("apiRsp", apiRsp);

            //市信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",consignorPrice.getProvinceId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> cityApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("cityApiRsp", cityApiRsp);

            //区消息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId", consignorPrice.getCityId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> districtApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("districtApiRsp", districtApiRsp);

            model.put("surveyConsignorPrice",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyConsignorPrice/edit",model);
        }
        //调查调查方价格
        else if("franchiseePrice".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyFranchiseePriceDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            SurveyFranchiseePriceDto franchiseePrice = (SurveyFranchiseePriceDto)apiFinalResponse.getResults();
            model.put("surveyFranchiseePrice",franchiseePrice);
            //地区信息
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("parentId",0);
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("apiRsp", apiRsp);

            //市信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",franchiseePrice.getProvinceId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> cityApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("cityApiRsp", cityApiRsp);

            //区消息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId", franchiseePrice.getCityId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> districtApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("districtApiRsp", districtApiRsp);

            model.put("surveyFranchiseePrice",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyFranchiseePrice/edit",model);
        }
        //qa问答
        else if("qa".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyQaDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyQa",apiFinalResponse.getResults());

            appendMap = new HashMap<String, Object>();
            appendMap.put("enumCode", "surveyQa");
            TypeToken typeToken2 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
            ApiFinalResponse apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
            List<CommonEnumDto> surveyQas = (List<CommonEnumDto>) apiFinalResponse2.getResults();
            model.put("surveyQas",surveyQas);
            return new ModelAndView("/survey/surveyQa/edit",model);
        }
        //调查调查方
        else if("franchisee".equals(surveyCode)){

            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyFranchiseeDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            SurveyFranchiseeDto surveyFranchisee = (SurveyFranchiseeDto) apiFinalResponse.getResults();
            model.put("surveyFranchisee",surveyFranchisee);

            //地区信息
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("parentId",0);
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("apiRsp", apiRsp);

            //市信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",surveyFranchisee.getProvinceId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> cityApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("cityApiRsp", cityApiRsp);

            //区消息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId", surveyFranchisee.getCityId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> districtApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("districtApiRsp", districtApiRsp);

            //关联人事管理-机构/部门信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","organ");
            appendMap.put("havePage","1");
            typeToken = new TypeToken<ApiFinalResponse<List<StaffOrganDto>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, null);
            List<StaffOrganDto> staffOrganList=(List<StaffOrganDto>)apiFinalResponse.getResults();
            model.put("staffOrganList", staffOrganList);
            return new ModelAndView("/survey/surveyFranchisee/edit",model);
        }
        //商品
        else if("product".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyProductDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyProduct",apiFinalResponse.getResults());

            //商品的角色list
            appendMap.clear();
            appendMap.put("productId", req.getParameter("id"));
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyProductRoleDto>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_PRODUCT_ROLE_LIST, appendMap, null);
            model.put("roles",apiFinalResponse.getResults());
            //商品的调查员等级list
            appendMap.clear();
            appendMap.put("productId", req.getParameter("id"));
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyProductLevelDto>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_PRODUCT_LEVEL_LIST, appendMap, null);
            model.put("levels",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyProduct/edit",model);
        }
        //委托方默认价格
        else if("commonAreaPrice".equals(surveyCode)){

//            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>(){};
//            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST, null, req);
//            model.put("taskInfos",apiFinalResponse.getResults());
//            model.put("btnCode",req.getParameter("btnCode"));
//
//            //所有价格体系
//            //列表多选
//            appendMap = new HashMap<String, Object>();
//            appendMap.put("menuType", 1);//不分页
//            appendMap.put("surveyCode", surveyCode);
//            appendMap.put("taskId", req.getParameter("taskId")); //任务类别
//            appendMap.put("areaId", req.getParameter("parentId"));
//
//            typeToken = new TypeToken<ApiFinalResponse<List<SurveyCommonAreaPriceDto>>>() {};
//            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
//            model.put("surveyCommonAreaPrice",apiFinalResponse.getResults());

            //调查方价格
            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType", 1);//不分页
            appendMap.put("surveyCode", surveyCode);
            appendMap.put("taskId", req.getParameter("taskId")); //任务类别
            appendMap.put("areaId", req.getParameter("parentId"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyCommonAreaPriceDto>>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
            List<SurveyCommonAreaPrice2Dto> surveyCommonAreaPrice2Dtos = (List<SurveyCommonAreaPrice2Dto>)apiFinalResponse.getResults();
            model.put("surveyCommonAreaPrice",surveyCommonAreaPrice2Dtos);

            //任务类型(不分页)
            String priceType = req.getParameter("priceType");//保险版、互助版
            appendMap = new HashMap<String, Object>();
            appendMap.put("type", priceType );
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfo2Dto>>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST_TEST, appendMap, req);
            model.put("taskInfos",apiFinalResponse.getResults());
            String areaType = req.getParameter("areaType");

            model.put("areaId",req.getParameter("parentId"));
            model.put("areaName",req.getParameter("parentName"));
            model.put("btnCode",req.getParameter("btnCode"));
            model.put("priceType",priceType);
            if(areaType!=null){
                if("1".equals(areaType)){ //直辖市
                    return new ModelAndView("/survey/surveyCommonAreaPrice/addNew",model);
                }else if("0".equals(areaType)){ //省会、地级市、县级市
                    return new ModelAndView("/survey/surveyCommonAreaPrice/addNewCity",model);
                }
            }

        }
        //调查方默认价格
        else if("investigatorAreaPrice".equals(surveyCode)){
//            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>(){};
//            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST, null, req);
//            model.put("taskInfos",apiFinalResponse.getResults());
//            model.put("btnCode",req.getParameter("btnCode"));
//
//            //所有价格体系
//            //列表多选
//            appendMap = new HashMap<String, Object>();
//            appendMap.put("menuType", 1);//不分页
//            appendMap.put("surveyCode", surveyCode);
//            appendMap.put("taskId", req.getParameter("taskId")); //任务类别
//            appendMap.put("areaId", req.getParameter("parentId"));
//
//            typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorAreaPriceDto>>>() {};
//            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
//            model.put("surveyInvestigatorAreaPrice",apiFinalResponse.getResults());

            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType", 1);//不分页
            appendMap.put("surveyCode", surveyCode);
            appendMap.put("taskId", req.getParameter("taskId")); //任务类别
            appendMap.put("areaId", req.getParameter("parentId"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorAreaPriceDto>>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
            List<SurveyInvestigatorAreaPriceDto> surveyInvestigatorAreaPrice = (List<SurveyInvestigatorAreaPriceDto>)apiFinalResponse.getResults();
            model.put("surveyInvestigatorAreaPrice",surveyInvestigatorAreaPrice);

            //任务类型(不分页)
            String priceType = req.getParameter("priceType");//保险版、互助版
            appendMap = new HashMap<String, Object>();
            appendMap.put("type", priceType );
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfo2Dto>>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST_TEST, appendMap, req);
            model.put("taskInfos",apiFinalResponse.getResults());

            model.put("btnCode",req.getParameter("btnCode"));
            String areaType = req.getParameter("areaType");
            model.put("areaId",req.getParameter("parentId"));
            model.put("areaName",req.getParameter("parentName"));
            model.put("priceType",priceType);
            if(areaType!=null){
                if("1".equals(areaType)){ //直辖市
                    return new ModelAndView("/survey/surveyInvestigatorAreaPrice/addNew",model);
                }else if("0".equals(areaType)){ //省会、地级市、县级市
                    return new ModelAndView("/survey/surveyInvestigatorAreaPrice/addNewCity",model);
                }
            }

//            appendMap.put("taskId", req.getParameter("taskId"));
//            appendMap.put("areaId", req.getParameter("areaId"));
//            appendMap.put("cityType", req.getParameter("cityType"));
//            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyInvestigatorAreaPriceDto>>() {};
//            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
//
//            model.put("surveyInvestigatorAreaPrice",apiFinalResponse.getResults());
//            model.put("btnCode",req.getParameter("btnCode"));
//            model.put("surveyCode",req.getParameter("surveyCode"));
//            return new ModelAndView("/survey/surveyInvestigatorAreaPrice/edit",model);
        }
        //服务区域表
        else if("serviceArea".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyServiceAreaDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            SurveyServiceAreaDto surveyServiceArea = (SurveyServiceAreaDto)apiFinalResponse.getResults();
            model.put("surveyServiceArea",surveyServiceArea);
            //地区信息
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("parentId",0);
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("apiRsp", apiRsp);

            //市信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",surveyServiceArea.getProvinceId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> cityApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("cityApiRsp", cityApiRsp);

            //区消息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId", surveyServiceArea.getCityId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> districtApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("districtApiRsp", districtApiRsp);

            return new ModelAndView("/survey/surveyServiceArea/edit",model);
        }
        //委托方机构-部门信息
        else if("consignorDepartment".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyConsignorDepartmentDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyConsignorDepartment",apiFinalResponse.getResults());
            model.put("consignorId", req.getParameter("consignorId"));
            return new ModelAndView("/survey/surveyConsignorDepartment/edit",model);
        }
        //任务类型-方向名称
        else if("taskInfoContent".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyTaskInfoContentDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyTaskInfoContent",apiFinalResponse.getResults());
            model.put("taskInfoId", req.getParameter("taskInfoId"));
            return new ModelAndView("/survey/surveyTaskInfoContent/edit",model);
        }
        //调查员登记
        else if("userInfo".equals(surveyCode)){
            appendMap.put("userId", req.getParameter("userId"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<UserInfo>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("userInfo",apiFinalResponse.getResults());
            return new ModelAndView("/user/surveyUserEdit",model);
        }
        //提现--银行卡信息
        else if("bankCard".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyBankCardDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyBankCard",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyCashInfoRecord/editBankCard",model);
        }
        //开票-开票公司
        else if("applyCorporation".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<BillingApplyCorporationDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("applyCorporation",apiFinalResponse.getResults());
            return new ModelAndView("/survey/billing/applyCorporation/edit",model);
        }
        //开票-开票产品类型
        else if("applyProductType".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<BillingApplyProductTypeDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("applyProductType",apiFinalResponse.getResults());
            return new ModelAndView("/survey/billing/applyProductType/edit",model);
        }
        //开票-开票产品
        else if("billingEnum".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<CommonEnumDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            CommonEnumDto commonEnum = (CommonEnumDto)apiFinalResponse.getResults();
            model.put("commonEnum",commonEnum);
            model.put("parentId",commonEnum.getParentId());
            return new ModelAndView("/survey/billing/billingEnum/edit",model);
        }
        //开票-开票项目
        else if("billingItem".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<CommonEnumDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            CommonEnumDto commonEnum = (CommonEnumDto)apiFinalResponse.getResults();
            model.put("commonEnum",commonEnum);
            model.put("parentId",commonEnum.getParentId());
            return new ModelAndView("/survey/billing/billingItem/edit",model);
        }
        //报告模板
        else if("modelInfo".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyModelInfoDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyModelInfo",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyModelInfo/edit",model);
        }
        //委托方机构-开票主体
        else if("consignorBillSubject".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<BillingApplyCompanyDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyConsignorBillSubject",apiFinalResponse.getResults());
            model.put("consignorId", req.getParameter("consignorId"));
            return new ModelAndView("/survey/surveyConsignorBillSubject/edit",model);
        }
        //方向结果类型
        else if("directionResultType".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyDirectionResultTypeDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyDirectionResultType",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyDirectionResultType/edit",model);
        }
        //价格模板
        else if("priceModel".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyPriceModelDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyPriceModel",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyPriceModel/edit",model);
        }
        //区域类别对应具体区域
        else if("priceModelAreaCategories".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyPriceModelAreaCategoriesDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, req);
            model.put("priceModelAreaCategories",apiFinalResponse.getResults());
            model.put("priceModelId", req.getParameter("priceModelId"));//价格模板id

            appendMap.put("fromType","edit");
            appendMap.put("surveyCode",surveyCode);
            typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_AREA_TYPE_LIST, appendMap, req);
            List<CommonArea> commonAreaList= (List<CommonArea>) apiFinalResponse.getResults();
            model.put("commonAreaList",commonAreaList);

            return new ModelAndView("/survey/surveyPriceModel/areaCityEdit",model);
        }
        //设置价格
        else if("surveyPrice".equals(surveyCode)){

            appendMap = new HashMap<String, Object>();
            appendMap.put("type", req.getParameter("type"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfo2Dto>>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST_TEST, appendMap, req);
            model.put("taskInfos",apiFinalResponse.getResults());

            //互助价格
            appendMap = new HashMap<String, Object>();
            appendMap.put("priceModelId", req.getParameter("priceModelId"));//价格模板id
            appendMap.put("surveyCode", "surveyPrice");
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyPriceModelAreaCategoriesDto>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, null);
            model.put("areaCategories",apiFinalResponse.getResults());
            model.put("priceModelId",req.getParameter("priceModelId"));
            model.put("type",req.getParameter("type"));

            //获取模板对象
            appendMap = new HashMap<>();
            appendMap.put("id",req.getParameter("priceModelId"));
            appendMap.put("surveyCode","priceModel");
            typeToken = new TypeToken<ApiFinalResponse<SurveyPriceModelDto>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, req);
            SurveyPriceModelDto priceModel = (SurveyPriceModelDto) apiFinalResponse.getResults();
//            if (priceModel != null){
//                model.put("surveyEntrustIsRate",priceModel.getSurveyEntrustIsRate());
//                model.put("surveyEntrustRate",priceModel.getSurveyEntrustRate());
//            }
            model.put("priceModel",priceModel);
            return new ModelAndView("/survey/surveyPriceModel/addHzPrice",model);

        }
        else if("surveyEfficiency".equals(surveyCode)){
            appendMap = new HashMap<String, Object>();
            appendMap.put("sortRule",1);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyServiceTypeDto>>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SERVICE_TYPE_LIST, appendMap, req);
            List<SurveyServiceTypeDto> services = (List<SurveyServiceTypeDto>)apiFinalResponse.getResults();
            model.put("services",services);

            //互助价格
            appendMap = new HashMap<String, Object>();
            appendMap.put("modelId", req.getParameter("modelId"));//价格模板id
            appendMap.put("surveyCode", surveyCode);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorEfficiencyModelArea>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, null);
            model.put("areaCategories",apiFinalResponse.getResults());
            model.put("modelId",req.getParameter("modelId"));
            model.put("type",req.getParameter("type"));
            return new ModelAndView("/survey/base/surveyConsignorEfficiencyModel/addHzPrice",model);

        }else if ("channelModePrice".equals(surveyCode)){
            //任务类型列表
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode", "channelTasks");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfo2Dto>>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, null);
            model.put("tasks",apiFinalResponse.getResults());

            //区域类别列表
            appendMap = new HashMap<String, Object>();
            appendMap.put("modelId", req.getParameter("modelId"));//价格模板id
            appendMap.put("surveyCode", "channelAreaPrice");
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyChannelModelArea>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, null);
            model.put("areaCategories",apiFinalResponse.getResults());
            model.put("modelId",req.getParameter("modelId"));
            model.put("type",req.getParameter("type"));
            return new ModelAndView("/survey/base/channelModel/addHzPrice",model);
        }
        else if ("scoreModelPrice".equals(surveyCode))
        {
            //区域类别列表
            appendMap = new HashMap<String, Object>();
            appendMap.put("modelId", req.getParameter("modelId"));//价格模板id
            appendMap.put("surveyCode", "scoreAreaPrice");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyScoreModelArea>>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, null);
            model.put("areaCategories",apiFinalResponse.getResults());
            model.put("modelId",req.getParameter("modelId"));
            return new ModelAndView("/survey/base/scoreModel/addHzPrice",model);
        }
        //委托时效模板
        else if("consignorEfficiencyModel".equals(surveyCode)){
            //委托模板信息，内含“价格体系”
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyConsignorEfficiencyModelDto>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INFO, null, req);
            SurveyConsignorEfficiencyModelDto surveyConsignorEfficiencyModel = (SurveyConsignorEfficiencyModelDto)apiFinalResponse.getResults();
            model.put("surveyConsignorEfficiencyModel",surveyConsignorEfficiencyModel);
            model.put("btnCode",req.getParameter("btnCode"));
            model.put("type",req.getParameter("type"));
            return new ModelAndView("/survey/base/surveyConsignorEfficiencyModel/edit",model);
        }
        else if("consignorEfficiencyModelArea".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyConsignorEfficiencyModelArea>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, req);
            model.put("efficiencyModelArea",apiFinalResponse.getResults());
            model.put("modelId", req.getParameter("modelId"));//价格模板id

            appendMap.put("fromType","edit");
            appendMap.put("surveyCode",surveyCode);
            typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_AREA_TYPE_LIST, appendMap, req);
            List<CommonArea> commonAreaList= (List<CommonArea>) apiFinalResponse.getResults();
            model.put("commonAreaList",commonAreaList);

            return new ModelAndView("/survey/base/surveyConsignorEfficiencyModel/areaCityEdit",model);
        }
        else if("channelModel".equals(surveyCode)){
            //委托模板信息，内含“价格体系”
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyChannelModel>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INFO, null, req);
            SurveyChannelModel surveyChannelModel = (SurveyChannelModel)apiFinalResponse.getResults();
            model.put("surveyChannelModel",surveyChannelModel);
            model.put("btnCode",req.getParameter("btnCode"));
            model.put("type",req.getParameter("type"));
            return new ModelAndView("/survey/base/channelModel/edit",model);
        }
        else if("scoreModel".equals(surveyCode)){
            //委托模板信息，内含“价格体系”
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyChannelModel>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INFO, null, req);
            SurveyChannelModel surveyChannelModel = (SurveyChannelModel)apiFinalResponse.getResults();
            model.put("surveyChannelModel",surveyChannelModel);
            model.put("btnCode",req.getParameter("btnCode"));
            model.put("type",req.getParameter("type"));
            return new ModelAndView("/survey/base/scoreModel/edit",model);
        }
        else if("channelModelArea".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyChannelModelArea>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, req);
            model.put("channelModelArea",apiFinalResponse.getResults());
            model.put("modelId", req.getParameter("modelId"));//价格模板id

            appendMap.put("fromType","edit");
            appendMap.put("surveyCode",surveyCode);
            typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_AREA_TYPE_LIST, appendMap, req);
            List<CommonArea> commonAreaList= (List<CommonArea>) apiFinalResponse.getResults();
            model.put("commonAreaList",commonAreaList);

            return new ModelAndView("/survey/base/channelModel/areaCityEdit",model);
        }
        else if("scoreModelArea".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyChannelModelArea>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, req);
            model.put("scoreModelArea",apiFinalResponse.getResults());
            model.put("modelId", req.getParameter("modelId"));//价格模板id

            appendMap.put("fromType","edit");
            appendMap.put("surveyCode",surveyCode);
            typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_AREA_TYPE_LIST, appendMap, req);
            List<CommonArea> commonAreaList= (List<CommonArea>) apiFinalResponse.getResults();
            model.put("commonAreaList",commonAreaList);

            return new ModelAndView("/survey/base/scoreModel/areaCityEdit",model);
        }
        //调查方片区
        else if("areaInformationSelectOne".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyOrgAreaDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_ORG_AREA_OPERATE, appendMap, null);
            model.put("surveyOrgAreaDto", apiFinalResponse.getResults());
            model.put("type","update");
            return new ModelAndView("/survey/surveyFranchisee/addAreaInformation", model);
        }
        //邮箱模板
        else if("emailInfo".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyEmailInfoDTO>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyEmailInfo",apiFinalResponse.getResults());
            return new ModelAndView("/survey/base/surveyEmailInfo/edit",model);
        }
        return null;
    }

    /**
     * info页面
     */
    @RequestMapping(value = "/info")
    public ModelAndView info(HttpServletRequest req, HttpServletResponse rsp) {

        String surveyCode = req.getParameter("surveyCode");
        Map model = new HashMap();
        model.put("surveyCode", surveyCode);//code标识

        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode",surveyCode);
        //领域类型
        if("businessType".equals(surveyCode)) {
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyBusinessTypeDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyBusinessType", apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyBusinessType/info", model);
        }
        //领域类型
        else if("serviceType".equals(surveyCode)) {
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyServiceTypeDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyServiceType", apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyServiceType/info", model);
        }
        //任务类型
        else if("taskInfo".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyTaskInfoDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyTaskInfo",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyTaskInfo/info",model);
        }
        //材料目录
        else if("fileCatalog".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyFileCatalogDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyFileCatalog",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyFileCatalog/info",model);
        }
        //调查员登记
        else if("level".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyLevelDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyLevel",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyLevel/info",model);
        }
        //称号特权详情
        else if("levelExplain".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyLevelExplainDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyLevelExplain",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyLevelExplain/info",model);
        }
        //平台介绍
        else if("introduction".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyIntroductionDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyIntroduction",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyIntroduction/info",model);
        }
        //服务优势
        else if("serviceAdvantage".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyServiceAdvantageDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyServiceAdvantage",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyServiceAdvantage/info",model);
        }
        //委托人认证
        else if("consigner".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyConsignerDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyConsigner",apiFinalResponse.getResults());

            //委托人认证材料
            appendMap.put("consignType", 1);
            typeToken = new TypeToken<ApiFinalResponse<List<CommonFile>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_COMMON_FILE, appendMap, null);
            model.put("apiRsp",apiFinalResponse);

            //查看图片轮播
            String type = req.getParameter("type");
            if(type!=null){
                return new ModelAndView("/survey/surveyConsigner/imgsShow",model);
            }
            return new ModelAndView("/survey/surveyConsigner/info",model);
        }
        //委托方机构
        else if("consignor".equals(surveyCode)){
            //委托机构信息
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyConsignorDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyConsignor",apiFinalResponse.getResults());

            SurveyConsignorDto surveyConsignorDto = (SurveyConsignorDto) apiFinalResponse.getResults();
            Integer priceType = surveyConsignorDto.getOrgAttr();
            model.put("priceType",priceType);

            //任务类型(不分页)
            appendMap = new HashMap<String, Object>();
            appendMap.put("type",priceType);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfo2Dto>>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST_TEST, appendMap, req);
            model.put("taskInfos",apiFinalResponse.getResults());

            //委托商价格
            appendMap.put("menuType", 2);//不分页
            appendMap.put("surveyCode", "consignorPrice");
            appendMap.put("enturyId", req.getParameter("id"));
            appendMap.put("taskId", req.getParameter("taskId")); //任务类别
            appendMap.put("cityType", req.getParameter("cityType")); //区域级别
            appendMap.put("areaName", req.getParameter("areaName")); //区域名称
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorPrice2Dto>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, null);
            model.put("surveyConsignorPrice",apiFinalResponse.getResults());
            model.put("cityType",req.getParameter("cityType"));
            model.put("taskId",req.getParameter("taskId"));
            model.put("areaName",req.getParameter("areaName"));

            //地区信息
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("parentId",0);
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("apiRsp", apiRsp);

            //该委托机构下对应的报告模板
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode", "consignorModel");
            appendMap.put("consignorId", req.getParameter("id"));
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorModelDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyConsignorModelDto> surveyConsignorModelDtos = (List<SurveyConsignorModelDto>)apiFinalResponse.getResults();
            if(surveyConsignorModelDtos!=null && surveyConsignorModelDtos.size()>0){
                model.put("surveyConsignorModel", surveyConsignorModelDtos.get(0));
            }

            //该委托机构下报告模板命名规则
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode", "consignorReportRule");
            appendMap.put("surveyConsignorId", req.getParameter("id"));
            typeToken = new TypeToken<ApiFinalResponse<SurveyConsignorReportRuleDto>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, req);
            model.put("reportRuleDto",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyConsignor/info",model);
        }
        //调查方认证
        else if("investigator".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyInvestigatorDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyInvestigator",apiFinalResponse.getResults());

            //调查方认证材料
            appendMap.put("consignType", 2);
            typeToken = new TypeToken<ApiFinalResponse<List<CommonFile>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_COMMON_FILE, appendMap, null);
            model.put("apiRsp",apiFinalResponse);

            //查看图片轮播
            String type = req.getParameter("type");
            if(type!=null){
                return new ModelAndView("/survey/surveyInvestigator/imgsShow",model);
            }
            return new ModelAndView("/survey/surveyInvestigator/info",model);
        }
        //乐凡币介绍
        else if("lfcoinExplain".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyLfcoinExplainDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyLfcoinExplain",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyLfcoinExplain/info",model);
        }
        //乐凡币规则
        else if("lfcoinRule".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyLfcoinRuleDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyLfcoinRule",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyLfcoinRule/info",model);
        }
        //成就点规则
        else if("achieveRule".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyAchieveRuleDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyAchieveRule",apiFinalResponse.getResults());
            return new ModelAndView("/survey/base/surveyAchieveRule/info",model);
        }
        //知识库类别
        else if("knowledgeType".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyKnowledgeTypeDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyKnowledgeType",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyKnowledgeType/info",model);
        }
        //知识库论坛
        else if("knowledgeBase".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyKnowledgeBaseDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyKnowledgeBase",apiFinalResponse.getResults());

            //帖子评价list
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyKnowledgeCommentDto>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_KNOW_LEDGE_COMMENT_INFO, appendMap, null);
            model.put("surveyKnowledgeComment",apiFinalResponse.getResults());

            return new ModelAndView("/survey/surveyKnowledgeBase/info",model);
        }
        //帖子评论
        else if("knowledgeComment".equals(surveyCode)){

        }
        //调查委托商价格
        else if("consignorPrice".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyConsignorPriceDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyConsignorPrice",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyConsignorPrice/info",model);
        }
        //调查调查方价格
        else if("franchiseePrice".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyFranchiseePriceDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyFranchiseePrice",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyFranchiseePrice/info",model);
        }
        //QA问答
        else if("qa".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyQaDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
            model.put("surveyQa",apiFinalResponse.getResults());

            appendMap = new HashMap<String, Object>();
            appendMap.put("enumCode", "surveyQa");
            TypeToken typeToken2 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
            ApiFinalResponse apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
            List<CommonEnumDto> surveyQas = (List<CommonEnumDto>) apiFinalResponse2.getResults();
            model.put("surveyQas",surveyQas);
            return new ModelAndView("/survey/surveyQa/info",model);
        }
        //调查调查方
        else if("franchisee".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyFranchiseeDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyFranchisee",apiFinalResponse.getResults());

            //调查方价格（保险版）
            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType", 1);//不分页
            appendMap.put("surveyCode", "franchiseePrice");
            appendMap.put("franchiseeId", req.getParameter("id"));
            appendMap.put("priceType", 1);//价格类型（1：保险版；2、互助版）
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseePrice2Dto>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, null);
            model.put("surveyFranchiseePrice1",apiFinalResponse.getResults());


            //调查方价格（互助版）
            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType", 1);//不分页
            appendMap.put("surveyCode", "franchiseePrice");
            appendMap.put("franchiseeId", req.getParameter("id"));
            appendMap.put("priceType", 2);//价格类型（1：保险版；2、互助版）
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseePrice2Dto>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, null);
            model.put("surveyFranchiseePrice2",apiFinalResponse.getResults());

            model.put("cityType",req.getParameter("cityType"));
            model.put("taskId",req.getParameter("taskId"));
            model.put("areaName",req.getParameter("areaName"));
            //任务类型(不分页)
//            typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>(){};
//            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST, null, req);
//            model.put("taskInfos",apiFinalResponse.getResults());
            appendMap = new HashMap<String, Object>();
            appendMap.put("type", 1);//保司版
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfo2Dto>>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST_TEST, appendMap, req);
            model.put("taskInfos",apiFinalResponse.getResults());

            appendMap = new HashMap<String, Object>();
            appendMap.put("type", 2);//互助版
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfo2Dto>>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST_TEST, appendMap, req);
            model.put("taskInfosTwo",apiFinalResponse.getResults());

            //地区信息
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("parentId",0);
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, map, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("apiRsp", apiRsp);

            return new ModelAndView("/survey/surveyFranchisee/info",model);
        }
        //商品
        else if("product".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyProductDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyProduct",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyProduct/info",model);
        }
        //委托方默认价格
        else if("commonAreaPrice".equals(surveyCode)){
            appendMap.put("areaId", req.getParameter("areaId"));
            appendMap.put("taskId", req.getParameter("taskId"));
            appendMap.put("cityType", req.getParameter("cityType"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyCommonAreaPriceDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyCommonAreaPrice",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyCommonAreaPrice/info",model);
        }
        //调查方默认价格
        else if("investigatorAreaPrice".equals(surveyCode)){
            appendMap.put("areaId", req.getParameter("areaId"));
            appendMap.put("taskId", req.getParameter("taskId"));
            appendMap.put("cityType", req.getParameter("cityType"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyInvestigatorAreaPriceDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyInvestigatorAreaPrice",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyInvestigatorAreaPrice/info",model);
        }
        //订单
        else if("order".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyOrderDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyOrder",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyOrder/info",model);
        }
        //服务区域表
        else if("serviceArea".equals(surveyCode)){
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyServiceAreaDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

            model.put("surveyServiceArea",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyServiceArea/info",model);
        }
        //处罚记录表
        else if("punish".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyPunishDto>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INFO, null, req);

            model.put("surveyPunish",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyPunish/info",model);
        }
        //提现记录表
        else if("cashInfo".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyCashInfoDto>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INFO, null, req);
            model.put("surveyCashInfo",apiFinalResponse.getResults());

            //银行卡信息
            appendMap.put("surveyCode",surveyCode);
            appendMap.put("cashInfoId", req.getParameter("id"));
            appendMap.put("btnCode", 1000);
            typeToken = new TypeToken<ApiFinalResponse<SurveyBankCardDto>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req);
            model.put("surveyBankCard",apiFinalResponse.getResults());

            //明细
            appendMap.put("cashInfoId", req.getParameter("id"));
            appendMap.put("btnCode", 1100);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyCashInfoDetailDto>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, null);
            model.put("surveyCashInfoDetail",apiFinalResponse.getResults());


            return new ModelAndView("/survey/surveyCashInfo/info",model);
        }
        //提现列表（全部提现数据、可提现数据）
        else if("cashInfoRecord".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyCashInfoRecordDto>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INFO, null, req);

            model.put("surveyCashInfoRecord",apiFinalResponse.getResults());
            return new ModelAndView("/survey/surveyCashInfoRecord/info",model);
        }
        //开票-开票公司
        else if("applyCorporation".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<BillingApplyCorporationDto>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INFO, null, req);
            model.put("applyCorporation",apiFinalResponse.getResults());

            //开票公司名下产品
            appendMap = new HashMap<>();
            appendMap.put("surveyCode","applyCorporationEnum");
            appendMap.put("corporationId", req.getParameter("id"));
            appendMap.put("menuType",1);
            appendMap.put("billingEnumId",null);
            typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationEnumDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            model.put("applyCorporationEnum",apiFinalResponse.getResults());

            //开票公司名下项目
            appendMap = new HashMap<>();
            appendMap.put("surveyCode","applyEnumItem");
            appendMap.put("corporationId", req.getParameter("id"));
            appendMap.put("menuType",1);
            typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyEnumItemDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            model.put("applyEnumItem",apiFinalResponse.getResults());

            model.put("billingEnumId",req.getParameter("billingEnumId"));
            return new ModelAndView("/survey/billing/applyCorporation/info",model);
        }
        //开票-开票产品类型
        else if("applyProductType".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<BillingApplyProductTypeDto>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INFO, null, req);

            model.put("applyProductType",apiFinalResponse.getResults());
            return new ModelAndView("/survey/billing/applyProductType/info",model);
        }
        return null;
    }

    /**
     * 新增或修改数据
     */
    @RequestMapping(value = "/update")
    public String update(HttpServletRequest req, HttpServletResponse rsp) {
        String surveyCode = req.getParameter("surveyCode");
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode", surveyCode);//code标识

        if ("priceModelAreaCategories".equals(surveyCode) || "consignorEfficiencyModelArea".equals(surveyCode) || "channelModelArea".equals(surveyCode) || "scoreModelArea".equals(surveyCode)){
            if ( StringUtils.isBlank(req.getParameter("id"))){
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_UPDATE, appendMap, req, rsp);
            }else {
                //防止超时
                List<String> allData = Arrays.asList(req.getParameter("cityIds").split(","));
                int size = 50;
                appendMap.put("first","true");
                for (int begin = 0; begin < allData.size(); begin = begin + size) {
                    int end = Math.min(begin + size, allData.size());
                    List<String> subList = allData.subList(begin, end);
                    appendMap.put("cityIds", StringUtils.strip(subList.toString(), "[]"));
                    if (allData.size() - begin <= 50){
                        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_UPDATE, appendMap, req, rsp);
                    }else {
                        this.callApi(BackendApiMethodEnum.BACKEND_SURVEY_UPDATE, appendMap, req);
                    }
                    appendMap.put("first","false");

                }
            }
            return null;
        }else {
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_UPDATE, appendMap, req, rsp);
        }
    }

    /**
     * 数据处理
     */
    @RequestMapping(value = "/operate")
    public String operate(HttpServletRequest req, HttpServletResponse rsp) {
        String surveyCode = req.getParameter("surveyCode");
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode", surveyCode);//code标识
        if("product".equals(surveyCode) ||"corp".equals(surveyCode)
                ||"org".equals(surveyCode) ||"enum".equals(surveyCode) ||"item".equals(surveyCode) ){
            appendMap.put("searchType", surveyCode);
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_AUTH_SAVE, appendMap, req, rsp);
        }else if("setrole".equals(surveyCode)){
            String searchType = req.getParameter("searchType");
            appendMap.put("searchType", searchType);
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_AUTH_SAVE_TWO, appendMap, req, rsp);
        }else if ("getMessageSize".equals(surveyCode)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_MESSAGE_SIZE, appendMap, req, rsp);
        }else if("authenticationPhone".equals(surveyCode)){
            Map map=new HashMap();
            map.put("userTel", req.getParameter("userTel"));
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_SELECTBYONE, map, req, rsp);
        }else if("updAreaInformation".equals(surveyCode)){
            String type=req.getParameter("type");
            if("update".equals(type)){
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_ORG_AREA_OPERATE, appendMap, req, rsp);
            }else if("insert".equals(type)){
                appendMap.put("surveyCode", "addAreaInformation");
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_ORG_AREA_OPERATE, appendMap, req, rsp);
            }
            return "";
        }else if("delAreaInformation".equals(surveyCode)){
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_ORG_AREA_OPERATE, appendMap, req, rsp);
        }else if("addAreaPersonnel".equals(surveyCode) || "delAreaPersonnel".equals(surveyCode)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_INVESTIGATOR_UPDATEUSER, null, req, rsp);
        }else if ("scoreModelAreaDel".equals(surveyCode) || "channelModelAreaDel".equals(surveyCode) || "priceModelAreaCategoriesDel".equals(surveyCode) || "consignorEfficiencyModelAreaDel".equals(surveyCode)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_MODEL_AREA_DELETE, appendMap, req, rsp);
        } else{
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_OPERATE, appendMap, req, rsp);
        }
    }

    /**
     * 操作-弹窗页面
     */
    @RequestMapping(value = "/popup")
    public ModelAndView popup(HttpServletRequest req, HttpServletResponse rsp) {

        String surveyCode = req.getParameter("surveyCode");
        String btnCode = req.getParameter("btnCode");
        Map model = new HashMap();
        model.put("surveyCode", surveyCode);//code标识
        model.put("btnCode", btnCode);//code标识

        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode",surveyCode);
        //领域类型
        if("businessType".equals(surveyCode)) {
            String businessTypeId = req.getParameter("id");
            model.put("businessTypeId",businessTypeId);
            //关联任务类型
            if("2100".equals(btnCode) || "2000".equals(btnCode)){
                //所有任务类型
                appendMap = new HashMap<String, Object>();
                appendMap.put("surveyCode", "taskInfo");
                appendMap.put("menuType",1); //不分页
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyTaskInfoDto> taskInfoDtos = (List<SurveyTaskInfoDto>) apiFinalResponse.getResults();
                model.put("taskInfos",taskInfoDtos);
                //名下任务类型
                appendMap = new HashMap<String, Object>();
                appendMap.put("businessTypeId", businessTypeId);
                appendMap.put("surveyCode", "businessTaskType");
                appendMap.put("menuType",1); //不分页
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyBusinessTaskTypeDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyTaskInfoDto> myTaskInfoDtos = (List<SurveyTaskInfoDto>) apiFinalResponse.getResults();
                model.put("myTaskInfos",myTaskInfoDtos);
                return new ModelAndView("/survey/surveyBusinessType/addTaskInfoList",model);
            }
        }
        //委托人认证
        else if("consigner".equals(surveyCode)){
            //修改部门
            if("1900".equals(btnCode)){
                appendMap.put("id", req.getParameter("id"));
                TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyConsignerDto>>() {};
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
                model.put("surveyConsigner",apiFinalResponse.getResults());

                appendMap.clear();
                appendMap.put("surveyCode", "consignorDepartment");
                appendMap.put("consignorId", req.getParameter("consignorId"));
                appendMap.put("menuType", 1);
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDepartmentDto>>>() {};
                apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
                model.put("surveyConsignorDepartment",apiFinalResponse.getResults());

                return new ModelAndView("/survey/surveyConsigner/department", model);
            }
        }
        //委托方机构
        else if("consignor".equals(surveyCode)){
            if("3100".equals(btnCode)){
                return new ModelAndView("/survey/surveyConsignor/email", model);
            }
            //报告命名规则
            else if("3200".equals(btnCode)){

                appendMap.put("surveyCode", "consignorReportRule");//查询报告命名规则，故更改surveyCode
                appendMap.put("surveyConsignorId", req.getParameter("surveyConsignorId"));
                TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyConsignorReportRuleDto>>() {};
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
                model.put("surveyConsignorReportRule",apiFinalResponse.getResults());

                model.put("surveyConsignorId", req.getParameter("surveyConsignorId"));
                model.put("surveyConsignorName", req.getParameter("surveyConsignorName"));
                return new ModelAndView("/survey/surveyConsignor/reportRule", model);
            }
            //设置终审人员
            else if("5000".equals(btnCode)){
                String id = req.getParameter("id");
                model.put("id",id);
                //所有的终审人员
                appendMap = new HashMap<String, Object>();
                appendMap.put("surveyCode","finalJudgmentUser");
                appendMap.put("menuType",1);//不分页
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<UserInfo>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<UserInfo> finalJudgmentUser = (List<UserInfo>)apiFinalResponse.getResults();
                model.put("finalJudgmentUser",finalJudgmentUser);

                //该委托机构下的终审人员
                appendMap = new HashMap<String, Object>();
                appendMap.put("surveyCode","userConsignor");
                appendMap.put("consignorId",id);
                appendMap.put("menuType",1);//不分页
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyUserConsignorDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyUserConsignorDto> myFinalJudgmentUser = (List<SurveyUserConsignorDto>)apiFinalResponse.getResults();
                model.put("myFinalJudgmentUser",myFinalJudgmentUser);

                //机构id

                return new ModelAndView("/survey/surveyConsignor/finalJudgmentUserList",model);
            }
            //理赔原件回寄地址设置
            else if("6000".equals(btnCode)){
                //委托机构信息
                appendMap.put("id", req.getParameter("id"));
                TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyConsignorDto>>() {};
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
                model.put("surveyConsignor",apiFinalResponse.getResults());
                return new ModelAndView("/survey/surveyConsignor/claimSet",model);
            }
            else if ("pact".equals(btnCode)){
                model.put("id",req.getParameter("id"));
                return new ModelAndView("/survey/surveyConsignor/part",model);
            }
            //设置区域类别
            //设置区域类别
            else if("7000".equals(btnCode)){
                String id = req.getParameter("id");
                model.put("id",id);
                model.put("surveyCode",surveyCode);
                model.put("btnCode",req.getParameter("btnCode"));
                //获取区域数据
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorAreaCityDTO>>>() {};
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST_CONSIGNOR_AREA_CITY, appendMap, req);
                model.put("areas",apiFinalResponse.getResults());
                return new ModelAndView("/survey/surveyConsignor/consignorAreaList",model);
            }
            else if("6666".equals(btnCode)){
                model.put("orgId",req.getParameter("orgId"));
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>(){};
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST_CONSIGNOR_AREA_CITY_COMMON, appendMap, req);
                model.put("commonAreaList",apiFinalResponse.getResults());
                return new ModelAndView("/survey/surveyConsignor/consignorAreaCityEdit",model);
            }
        }
        //调查方认证
        else if("investigator".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyLevelDto>>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LEVEL_LIST, null, null);
            model.put("surveyLevels", apiFinalResponse.getResults());

            model.put("id",req.getParameter("id"));
            model.put("surveyCode",surveyCode);
            model.put("btnCode",req.getParameter("btnCode"));
            return new ModelAndView("/survey/surveyInvestigator/level", model);
        }
        //调查调查方
        else if("franchisee".equals(surveyCode)){
            if("1100".equals(btnCode)){

                //所有狄大人角色
                appendMap = new HashMap<String, Object>();
                appendMap.put("surveyCode",surveyCode);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BusinessRoleDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INVESTIGATOR_BUSINESS_ROLE_LIST, appendMap, null);
                model.put("roles",apiFinalResponse.getResults());

                //修改人的所有角色
                appendMap = new HashMap<String, Object>();
                appendMap.put("userId",req.getParameter("userId"));
                appendMap.put("surveyCode",surveyCode);
                TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<BusinessRoleDto>>>() {};
                ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_SURVEY_INVESTIGATOR_BUSINESS_ROLE_LIST, appendMap, req);
                model.put("apiRspMy",apiFinalResponse1);

                model.put("userId",req.getParameter("userId"));
                model.put("surveyCode",surveyCode);
                model.put("btnCode",req.getParameter("btnCode"));
                return new ModelAndView("/survey/surveyFranchisee/investigatorRoleList",model);
            }
            //设置终审人员
            else if("5000".equals(btnCode)){
                String id = req.getParameter("id");
                model.put("id",id);
                //所有的终审人员
                appendMap = new HashMap<String, Object>();
                appendMap.put("surveyCode","finalJudgmentUser");
                appendMap.put("menuType",1);//不分页
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<UserInfo>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<UserInfo> finalJudgmentUser = (List<UserInfo>)apiFinalResponse.getResults();
                model.put("finalJudgmentUser",finalJudgmentUser);

                //该调查机构下的终审人员
                appendMap = new HashMap<String, Object>();
                appendMap.put("surveyCode","userFranchisee");
                appendMap.put("franchiseeId",id);
                appendMap.put("menuType",1);//不分页
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyUserFranchiseeDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyUserFranchiseeDto> myFinalJudgmentUser = (List<SurveyUserFranchiseeDto>)apiFinalResponse.getResults();
                model.put("myFinalJudgmentUser",myFinalJudgmentUser);

                return new ModelAndView("/survey/surveyConsignor/finalJudgmentUserList",model);
            }
            //设置区域类别
            else if("6000".equals(btnCode)){
                String id = req.getParameter("id");
                model.put("id",id);
                model.put("surveyCode",surveyCode);
                model.put("btnCode",req.getParameter("btnCode"));
                //获取区域数据
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeAreaCity>>>() {};
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST_FRANCHISEE_AREA_CITY, appendMap, req);
                model.put("areas",apiFinalResponse.getResults());
                return new ModelAndView("/survey/surveyFranchisee/franchiseeAreaList",model);
            }
            else if("6666".equals(btnCode)){
                model.put("orgId",req.getParameter("orgId"));
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>(){};
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST_FRANCHISEE_AREA_CITY_COMMON, appendMap, req);
                model.put("commonAreaList",apiFinalResponse.getResults());
                return new ModelAndView("/survey/surveyFranchisee/franchiseeAreaCityEdit",model);
            }
        }
        //提现记录表
        else if("cashInfo".equals(surveyCode)){
            if("1300".equals(btnCode) || "1200".equals(btnCode)){
                model.put("id",req.getParameter("id"));
                model.put("surveyCode",surveyCode);
                model.put("btnCode",req.getParameter("btnCode"));
                return new ModelAndView("/survey/surveyCashInfo/unline", model);
            }
            //驳回提现--输入驳回原因
            else if("1400".equals(btnCode)){
                model.put("id",req.getParameter("id"));
                model.put("surveyCode",surveyCode);
                model.put("btnCode",req.getParameter("btnCode"));
                return new ModelAndView("/survey/surveyCashInfo/back", model);
            }
        }
        //委托方机构-部门
        else if("consignorDepartment".equals(surveyCode)) {
            //添加人员到部门
            if("1100".equals(btnCode)) {
                appendMap = new HashMap<String, Object>();
                appendMap.put("entrustOrgId",req.getParameter("consignorId"));
                appendMap.put("surveyCode","consigner");
                appendMap.put("menuType",1);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignerDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                model.put("consigneres",apiFinalResponse.getResults());
                model.put("id",req.getParameter("id"));
                return new ModelAndView("/survey/surveyConsignorDepartment/consigner", model);
            }
            //部门名下人员
            if("1200".equals(btnCode)) {
                appendMap = new HashMap<String, Object>();
                appendMap.put("departmentId",req.getParameter("id"));
                appendMap.put("surveyCode","consigner");
                appendMap.put("menuType",1);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignerDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                model.put("consigneres",apiFinalResponse.getResults());
                model.put("departmentId",req.getParameter("id"));
                return new ModelAndView("/survey/surveyConsignorDepartment/departConsigner", model);
            }
        }
        //提现记录表
        else if("cashInfoRecord".equals(surveyCode)) {
            if("1100".equals(btnCode)) {
                TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyCashInfoDto>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_OPERATE, appendMap, req);
                model.put("info", apiFinalResponse.getResults());
            }
            return new ModelAndView("/survey/surveyCashInfoRecord/detail", model);
        }
        //开票权限设置
        else if("applyRole".equals(surveyCode)){
            String searchType = req.getParameter("searchType");
            model.put("roleId",req.getParameter("roleId"));
            //产品类型
            if("product".equals(searchType)){
                appendMap = new HashMap<String, Object>();
                appendMap.put("noPageIndex",1);
                appendMap.put("searchType",searchType);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyProductTypeDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_AUTH_LIST, appendMap, req);
                String name = req.getParameter("name");
                model.put("name", name);
                req.setAttribute("apiRsp", apiFinalResponse);
                model.put("surveyCode",searchType);

                return new ModelAndView("/survey/billing/applyRole/list",model);
            }
            //业务来源
            else if("org".equals(searchType)){
                appendMap = new HashMap<String, Object>();
                appendMap.put("noPageIndex",1);
                appendMap.put("searchType",searchType);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_AUTH_LIST, appendMap, req);
                String name = req.getParameter("name");
                model.put("name", name);
                req.setAttribute("apiRsp", apiFinalResponse);
                model.put("surveyCode",searchType);
                return new ModelAndView("/survey/billing/applyRole/list",model);
            }
            //开票公司
            else if("corp".equals(searchType)){
                appendMap = new HashMap<String, Object>();
                appendMap.put("noPageIndex",1);
                appendMap.put("searchType",searchType);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_AUTH_LIST, appendMap, req);
                String name = req.getParameter("name");
                model.put("name", name);
                req.setAttribute("apiRsp", apiFinalResponse);
                model.put("surveyCode",searchType);

                return new ModelAndView("/survey/billing/applyRole/list",model);
            }
            //开票产品
            else if("enum".equals(searchType)){
                appendMap = new HashMap<String, Object>();
                appendMap.put("noPageIndex",1);
                appendMap.put("enumCode", "billingEnum");
                appendMap.put("searchType",searchType);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_AUTH_LIST, appendMap, req);
                String name = req.getParameter("name");
                model.put("name", name);
                req.setAttribute("apiRsp", apiFinalResponse);
                model.put("surveyCode",searchType);

                return new ModelAndView("/survey/billing/applyRole/list",model);
            }
            //开票项目
            else if("item".equals(searchType)){
                appendMap = new HashMap<String, Object>();
                appendMap.put("noPageIndex",1);
                appendMap.put("enumCode", "billingItem");
                appendMap.put("searchType",searchType);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_AUTH_LIST, appendMap, req);
                String name = req.getParameter("name");
                model.put("name", name);
                req.setAttribute("apiRsp", apiFinalResponse);
                model.put("surveyCode",searchType);

                return new ModelAndView("/survey/billing/applyRole/list",model);
            }
        }else if ("setrole".equals(surveyCode)){
            String searchType = req.getParameter("searchType");
            appendMap = new HashMap<String, Object>();
            appendMap.put("noPageIndex",1);
            appendMap.put("searchType",searchType);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BusinessRoleDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_ROLE_LIST, appendMap, req);
            String name = req.getParameter("name");
            model.put("name", name);
            model.put("surveyCode",surveyCode);
            model.put("leftId",req.getParameter("leftId"));
            model.put("noPageIndex",1);
            model.put("searchType",searchType);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/billing/applyRole/listRole",model);
        }
        //开票-公司
        else if("applyCorporation".equals(surveyCode)){
            //公司设置产品
            if("1100".equals(btnCode)){
                //开票产品 -- 枚举查询
                appendMap = new HashMap<String, Object>();
                appendMap.put("enumCode", "billingEnum");
                TypeToken typeToken5 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
                ApiFinalResponse apiFinalResponse5= this.callApi(typeToken5, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
                List<CommonEnumDto> bullingEnums = (List<CommonEnumDto>) apiFinalResponse5.getResults();
                model.put("bullingEnums",bullingEnums);
                model.put("applyCorporationId",req.getParameter("applyCorporationId"));

                //公司下已经存在的产品
                appendMap = new HashMap<String, Object>();
                appendMap.put("corporationId", req.getParameter("applyCorporationId"));
                appendMap.put("surveyCode", "applyCorporationEnum");
                appendMap.put("menuType",1); //不分页
                typeToken5 = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationEnumDto>>>() {};
                apiFinalResponse5= this.callApi(typeToken5, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<BillingApplyCorporationEnumDto> corporationEnums = (List<BillingApplyCorporationEnumDto>) apiFinalResponse5.getResults();
                model.put("corporationEnums",corporationEnums);
                model.put("type",1);//因为公司对应产品，产品对应项目用的是同一个页面，多以多选框区分
                model.put("surveyCode","applyCorporationEnum");//为保存及修改功能，转换了surveyCode
                return new ModelAndView("/survey/billing/applyEnumItem/addList",model);
            }
            //公司名下产品
            else if("1200".equals(btnCode)){
                appendMap = new HashMap<String, Object>();
                appendMap.put("corporationId", req.getParameter("applyCorporationId"));
                appendMap.put("surveyCode", "applyCorporationEnum");
                appendMap.put("menuType",1); //不分页
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationEnumDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<BillingApplyCorporationEnumDto> corporationEnums = (List<BillingApplyCorporationEnumDto>) apiFinalResponse.getResults();
                model.put("myList",corporationEnums);
                model.put("corporationId",req.getParameter("applyCorporationId"));
                model.put("type", 1);//因为公司对应产品，产品对应项目用的是同一个页面，多以多选框区分
                return new ModelAndView("/survey/billing/applyEnumItem/myList",model);
            }
        }
        //开票-开票产品
        else if("applyCorporationEnum".equals(surveyCode)){
            //产品设置项目
            if("1100".equals(btnCode)) {
                //开票项目 -- 枚举查询
                appendMap = new HashMap<String, Object>();
                appendMap.put("enumCode", "billingItem");
                TypeToken typeToken5 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
                ApiFinalResponse apiFinalResponse5 = this.callApi(typeToken5, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
                List<CommonEnumDto> bullingEnums = (List<CommonEnumDto>) apiFinalResponse5.getResults();
                model.put("bullingEnums", bullingEnums);
                model.put("billingEnumId", req.getParameter("billingEnumId"));

                //产品下的项目
                appendMap = new HashMap<String, Object>();
                appendMap.put("billingEnumId", req.getParameter("billingEnumId"));
                appendMap.put("surveyCode", "applyEnumItem");
                appendMap.put("menuType",1); //不分页
                typeToken5 = new TypeToken<ApiFinalResponse<List<BillingApplyEnumItemDto>>>() {};
                apiFinalResponse5 = this.callApi(typeToken5, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<BillingApplyEnumItemDto> enumItems = (List<BillingApplyEnumItemDto>) apiFinalResponse5.getResults();
                model.put("enumItems", enumItems);
                model.put("type", 2);//因为公司对应产品，产品对应项目用的是同一个页面，多以多选框区分
                model.put("surveyCode","applyEnumItem"); //为保存及修改功能，转换了surveyCode
                return new ModelAndView("/survey/billing/applyEnumItem/addList", model);
            }
            //产品下的项目
            else if("1200".equals(btnCode)){
                appendMap = new HashMap<String, Object>();
                appendMap.put("corporationId", req.getParameter("applyCorporationId"));
                appendMap.put("billingEnumId", req.getParameter("billingEnumId"));
                appendMap.put("surveyCode", "applyEnumItem");
                appendMap.put("menuType",1); //不分页
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyEnumItemDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<BillingApplyEnumItemDto> enumItems = (List<BillingApplyEnumItemDto>) apiFinalResponse.getResults();
                model.put("myList", enumItems);
                model.put("type", 2);//因为公司对应产品，产品对应项目用的是同一个页面，多以多选框区分
                return new ModelAndView("/survey/billing/applyEnumItem/myList",model);
            }
        }
        //开票-产品类型
        else if("applyProductType".equals(surveyCode)){
            //产品类型设置业务来源
            if("1100".equals(btnCode)){
                //业务来源
                appendMap = new HashMap<String, Object>();
                appendMap.put("noPageIndex",1);
                appendMap.put("searchType","org");
                appendMap.put("type",1);//区分：1、不为null时，开票产品类型菜单下 - 设置业务来源功能（取所有业务来源值）；2、为null时，开票权限菜单下 - 根据产品类型设置对应的业务来源数据
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_AUTH_LIST, appendMap, req);
                List<OrgInfoDto> orgInfoList = (List<OrgInfoDto>) apiFinalResponse.getResults();
                model.put("orgInfoList",orgInfoList);

                //产品类型下已经存在的业务来源
                appendMap = new HashMap<String, Object>();
                appendMap.put("productId", req.getParameter("productId"));
                appendMap.put("surveyCode", "applyProductOrg");
                appendMap.put("menuType",1); //不分页
                typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyProductOrgDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<BillingApplyProductOrgDto> productOrgList = (List<BillingApplyProductOrgDto>) apiFinalResponse.getResults();
                model.put("productOrgList",productOrgList);
                model.put("surveyCode","applyProductOrg");//为保存及修改功能，转换了surveyCode
                model.put("productId", req.getParameter("productId"));
                return new ModelAndView("/survey/billing/applyProductType/addList",model);
            }
            //产品类型下已经存在的业务来源
            else if("1200".equals(btnCode)){
                appendMap = new HashMap<String, Object>();
                appendMap.put("productId", req.getParameter("productId"));
                appendMap.put("surveyCode", "applyProductOrg");
                appendMap.put("menuType",1); //不分页
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyProductOrgDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<BillingApplyProductOrgDto> productOrgList = (List<BillingApplyProductOrgDto>) apiFinalResponse.getResults();
                model.put("myList",productOrgList);
                return new ModelAndView("/survey/billing/applyProductType/myList",model);
            }
        }
        //狄大人平台终审人员
        else if("finalJudgmentUser".equals(surveyCode)){
            String userId = req.getParameter("userId");
            //设置保险公司
            if("1000".equals(btnCode)){
                //所有的保险公司
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","consignor");//查询“狄大人终审人员对应的委托方”，改变surveyCode值
                appendMap.put("company",req.getParameter("company"));//搜索
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
                model.put("consignors",consignors);
                model.put("userId",userId);

                //名下保险公司
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("userId",userId);
                appendMap.put("surveyCode","userConsignor");//查询“狄大人终审人员对应的委托方”，改变surveyCode值
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyUserConsignorDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyUserConsignorDto> userConsignors = (List<SurveyUserConsignorDto>) apiFinalResponse.getResults();
                model.put("userConsignors",userConsignors);

                //所有模板下的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","userConsignor");
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyUserConsignorDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
                List<SurveyUserConsignorDto> userConsignorAll = (List<SurveyUserConsignorDto>) apiFinalResponse.getResults();
                model.put("userConsignorAll",userConsignorAll);
                model.put("company",req.getParameter("company"));//搜索
                return new ModelAndView("/survey/surveyUser/consignorList", model);
            }
            //名下保险公司
            else if("1100".equals(btnCode)){
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("userId",userId);
                appendMap.put("surveyCode","userConsignor");//查询“狄大人终审人员对应的委托方”，改变surveyCode值
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyUserConsignorDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyUserConsignorDto> userConsignors = (List<SurveyUserConsignorDto>) apiFinalResponse.getResults();
                model.put("userConsignors",userConsignors);
                return new ModelAndView("/survey/surveyUser/myConsignorList", model);
            }
            //设置加盟机构
            else if("2000".equals(btnCode)){
                //所有调查方
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","franchisee");//查询“狄大人终审人员对应的委托方”，改变surveyCode值
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
                model.put("franchisees",franchisees);
                model.put("userId",userId);

                //名下调查方
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("userId",userId);
                appendMap.put("surveyCode","userFranchisee");//查询“狄大人终审人员对应的委托方”，改变surveyCode值
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyUserFranchiseeDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyUserFranchiseeDto> userFranchisees = (List<SurveyUserFranchiseeDto>) apiFinalResponse.getResults();
                model.put("userFranchisees",userFranchisees);

                //所有模板下的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","userFranchisee");
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyUserFranchiseeDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
                List<SurveyUserFranchiseeDto> userFranchiseeAll = (List<SurveyUserFranchiseeDto>) apiFinalResponse.getResults();
                model.put("userFranchiseeAll",userFranchiseeAll);
                model.put("name",req.getParameter("name"));//搜索

                return new ModelAndView("/survey/surveyUser/franchiseeList", model);
            }
            //名下加盟机构
            else if("2100".equals(btnCode)){
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("userId",userId);
                appendMap.put("surveyCode","userFranchisee");//查询“狄大人终审人员对应的调查方”，改变surveyCode值
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyUserFranchiseeDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyUserFranchiseeDto> userFranchisees = (List<SurveyUserFranchiseeDto>) apiFinalResponse.getResults();
                model.put("userFranchisees",userFranchisees);
                return new ModelAndView("/survey/surveyUser/myFranchiseeList", model);
            }
            //终审人员-手写签名
            else if("2200".equals(btnCode)){
                appendMap = new HashMap<String, Object>();
                appendMap.put("userId",userId);
                appendMap.put("surveyCode","userSign");//查询“狄大人终审人员对应的手写签名”，改变surveyCode值
                TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyUserSignDto>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, req);
                SurveyUserSignDto surveyUserSign = (SurveyUserSignDto)apiFinalResponse.getResults();
                model.put("userId",userId);
                model.put("surveyUserSign",surveyUserSign);
                return new ModelAndView("/survey/surveyUser/userSign", model);
            }
        }
        //狄大人--报告模板
        else if("consignorModel".equals(surveyCode)){
            String modelId = req.getParameter("modelId");
            //设置委托机构
            if("1000".equals(btnCode)){
                //所有的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","consignor");//查询“委托方机构”，改变surveyCode值
                appendMap.put("company",req.getParameter("company"));//搜索
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
                model.put("consignors",consignors);
                model.put("modelId",modelId);
                model.put("modelName",req.getParameter("modelName"));

                //模板下的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("modelId",modelId);
                appendMap.put("surveyCode","consignorModel");//查询“报告模板对应的委托方机构”，改变surveyCode值
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorModelDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyConsignorModelDto> consignorModels = (List<SurveyConsignorModelDto>) apiFinalResponse.getResults();
                model.put("consignorModels",consignorModels);

                //所有模板下的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","consignorModel");//查询“报告模板对应的委托方机构”，改变surveyCode值
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorModelDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
                List<SurveyConsignorModelDto> consignorModelAll = (List<SurveyConsignorModelDto>) apiFinalResponse.getResults();
                model.put("consignorModelAll",consignorModelAll);

                model.put("company",req.getParameter("company"));//搜索
                return new ModelAndView("/survey/surveyModelInfo/consignorList", model);
            }
            //名下保险公司
            else if("1100".equals(btnCode)){
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("modelId",modelId);
                appendMap.put("surveyCode","consignorModel");//查询“报告模板对应的委托方机构”，改变surveyCode值
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorModelDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyConsignorModelDto> consignorModels = (List<SurveyConsignorModelDto>) apiFinalResponse.getResults();
                model.put("consignorModels",consignorModels);
                return new ModelAndView("/survey/surveyModelInfo/myConsignorList", model);
            }
        }
        //案件调查方向
        else if("investigatorCase".equals(surveyCode)){
            if("direction".equals(btnCode)){
                model.put("id",req.getParameter("id"));
//                return new ModelAndView("/survey/case/sic/direction", model);
                return new ModelAndView("/survey/case/sic/directionView", model);
            }
        }
        //任务子类
        if("taskInfoContent".equals(surveyCode)) {
            String taskInfoContentId = req.getParameter("id");
            model.put("taskInfoContentId",taskInfoContentId);
            //关联方向结果类型
            if("2000".equals(btnCode)){
                //所有方向结果类型
                appendMap = new HashMap<String, Object>();
                appendMap.put("surveyCode", "directionResultType");
                appendMap.put("menuType",1); //不分页
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyDirectionResultTypeDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyDirectionResultTypeDto> directionResultTypeDtos = (List<SurveyDirectionResultTypeDto>) apiFinalResponse.getResults();
                model.put("directionResultTypes",directionResultTypeDtos);
                //名下方向结果类型
                appendMap = new HashMap<String, Object>();
                appendMap.put("taskInfoContentId", taskInfoContentId);
                appendMap.put("surveyCode", "taskDirectionResult");
                appendMap.put("menuType",1); //不分页
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskDirectionResultDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyTaskDirectionResultDto> myTaskDirectionResults = (List<SurveyTaskDirectionResultDto>) apiFinalResponse.getResults();
                model.put("myTaskDirectionResults",myTaskDirectionResults);
                return new ModelAndView("/survey/surveyTaskInfoContent/addDirectionResultType",model);
            }
        }

        //价格模板
        else if("priceModel".equals(surveyCode)){
            String id = req.getParameter("id");
            model.put("id",id);
            String type = req.getParameter("type");//机构类型：1、委托方机构；2、调查方机构
            String orgAttr = req.getParameter("orgAttr");
            model.put("type",type);
            //设置委托机构
            if("2000".equals(btnCode)){
                //所有的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","consignor");//查询“委托方机构”，改变surveyCode值
                appendMap.put("orgAttr",orgAttr); //type被其他地方使用过 还是用tttType吧 1保险 2互助
                appendMap.put("type",null);
                appendMap.put("company",req.getParameter("company"));//搜索
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
                model.put("consignors",consignors);

                //模板下的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("priceModelId",id);
                appendMap.put("surveyCode","priceModelOrg");//查询“价格模板对应机构”，改变surveyCode值
                appendMap.put("type",type); //机构类型：1、委托方机构；2、调查方机构
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyPriceModelOrgDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyPriceModelOrgDto> priceModelConsignors = (List<SurveyPriceModelOrgDto>) apiFinalResponse.getResults();
                model.put("priceModelConsignors",priceModelConsignors);

                //所有模板下的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","priceModelOrg");
                appendMap.put("type",type); //机构类型：1、委托方机构；2、调查方机构
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyPriceModelOrgDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
                List<SurveyPriceModelOrgDto> priceModelConsignorAll = (List<SurveyPriceModelOrgDto>) apiFinalResponse.getResults();
                model.put("priceModelConsignorAll",priceModelConsignorAll);
                model.put("company",req.getParameter("company"));//搜索
                return new ModelAndView("/survey/surveyPriceModel/orgList", model);
            }else if("2100".equals(btnCode)){
                //所有的调查机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","franchisee");//查询“调查方机构”，改变surveyCode值
                appendMap.put("type",null);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
                model.put("franchisees",franchisees);

                //模板下的调查方机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("priceModelId",id);
                appendMap.put("surveyCode","priceModelOrg");//查询“价格模板对应机构”，改变surveyCode值
                appendMap.put("type",type);//机构类型：1、委托方机构；2、调查方机构
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyPriceModelOrgDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyPriceModelOrgDto> priceModelFranchisees = (List<SurveyPriceModelOrgDto>) apiFinalResponse.getResults();
                model.put("priceModelFranchisees",priceModelFranchisees);
                return new ModelAndView("/survey/surveyPriceModel/orgList", model);
            }else if("3000".equals(btnCode)){
                //所有的区域类别，以及区域类别对应的具体区域
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","areaCategories");
                appendMap.put("priceModelId",id);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyPriceModelAreaCategoriesDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req);
                List<SurveyPriceModelAreaCategoriesDto> infos = (List<SurveyPriceModelAreaCategoriesDto>) apiFinalResponse.getResults();
                model.put("infos",infos);
                return new ModelAndView("/survey/surveyPriceModel/areaCategoriesList", model);
            }
        }
        //委托方时效 -- 设置委托方
        else if("consignorEfficiencyModel".equals(surveyCode)){
            String id = req.getParameter("id");
            model.put("id",id);
            //设置委托机构
            if("2000".equals(btnCode)) {
                //所有的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("surveyCode", "consignor");//查询“委托方机构”，改变surveyCode值
                appendMap.put("company",req.getParameter("company"));//搜索
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
                model.put("consignors", consignors);
                model.put("efficiencyModelId", id);

                //时效模板下的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("efficiencyModelId", id);
                appendMap.put("surveyCode", "consignorEfficiencyModelOrg");//查询“委托时效模板对应的委托方机构”，改变surveyCode值
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorEfficiencyModelOrgDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyConsignorEfficiencyModelOrgDto> modelOrgs = (List<SurveyConsignorEfficiencyModelOrgDto>) apiFinalResponse.getResults();
                model.put("modelOrgs", modelOrgs);

                //所有模板下的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","consignorEfficiencyModelOrg");
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorEfficiencyModelOrgDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
                List<SurveyConsignorEfficiencyModelOrgDto> modelOrgAll = (List<SurveyConsignorEfficiencyModelOrgDto>) apiFinalResponse.getResults();
                model.put("modelOrgAll",modelOrgAll);
                model.put("company",req.getParameter("company"));//搜索
                return new ModelAndView("/survey/base/surveyConsignorEfficiencyModel/consignorList", model);
            }else if("3000".equals(btnCode)){
                    //所有的区域类别，以及区域类别对应的具体区域
                    appendMap = new HashMap<String, Object>();
                    appendMap.put("menuType",1); //不分页
                    appendMap.put("surveyCode","efficiencyArea");
                    appendMap.put("modelId",id);
                    TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorEfficiencyModelArea>>>() {};
                    ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req);
                    List<SurveyConsignorEfficiencyModelArea> infos = (List<SurveyConsignorEfficiencyModelArea>) apiFinalResponse.getResults();
                    model.put("infos",infos);
                    return new ModelAndView("/survey/base/surveyConsignorEfficiencyModel/areaCategoriesList", model);
                }
        }else if("channelModel".equals(surveyCode)){
            String id = req.getParameter("id");
            model.put("id",id);
            //设置委托机构
            if("2000".equals(btnCode)) {
                //所有的调查机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","franchisee");//查询“调查方机构”，改变surveyCode值
                appendMap.put("type",null);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
                model.put("franchisees",franchisees);

                model.put("modelId", id);

                //时效模板下的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("modelId", id);
                appendMap.put("surveyCode", "channelModelOrg");//查询“委托时效模板对应的委托方机构”，改变surveyCode值
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyChannelModelOrg>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyChannelModelOrg> modelOrgs = (List<SurveyChannelModelOrg>) apiFinalResponse.getResults();
                model.put("modelOrgs", modelOrgs);

                return new ModelAndView("/survey/base/channelModel/consignorList", model);
            }else if("3000".equals(btnCode)){
                //所有的区域类别，以及区域类别对应的具体区域
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","channelModelArea");
                appendMap.put("modelId",id);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyChannelModelArea>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req);
                List<SurveyChannelModelArea> infos = (List<SurveyChannelModelArea>) apiFinalResponse.getResults();
                model.put("infos",infos);
                return new ModelAndView("/survey/base/channelModel/areaCategoriesList", model);
            }
        }
        else if("scoreModel".equals(surveyCode)){
            String id = req.getParameter("id");
            model.put("id",id);
            //设置委托机构
            if("2000".equals(btnCode)) {
                //所有的调查机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","franchisee");//查询“调查方机构”，改变surveyCode值
                appendMap.put("type",null);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
                model.put("franchisees",franchisees);

                model.put("modelId", id);

                //时效模板下的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("modelId", id);
                appendMap.put("surveyCode", "scoreModelOrg");//查询“委托时效模板对应的委托方机构”，改变surveyCode值
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyChannelModelOrg>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyChannelModelOrg> modelOrgs = (List<SurveyChannelModelOrg>) apiFinalResponse.getResults();
                model.put("modelOrgs", modelOrgs);

                return new ModelAndView("/survey/base/scoreModel/consignorList", model);
            }else if("3000".equals(btnCode)){
                //所有的区域类别，以及区域类别对应的具体区域
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","scoreModelArea");
                appendMap.put("modelId",id);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyChannelModelArea>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req);
                List<SurveyChannelModelArea> infos = (List<SurveyChannelModelArea>) apiFinalResponse.getResults();
                model.put("infos",infos);
                return new ModelAndView("/survey/base/scoreModel/areaCategoriesList", model);
            }
        }
        else if("areaInformation".equals(surveyCode)){
            String id=req.getParameter("id");
            model.put("id",id);
            String surveyAreaName=req.getParameter("surveyAreaName");
            model.put("surveyAreaName",surveyAreaName);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyOrgAreaDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_ORG_AREA_OPERATE, appendMap, req);
            List<SurveyOrgAreaDto> surveyOrgAreaList = (List<SurveyOrgAreaDto>) apiFinalResponse.getResults();
            model.put("surveyOrgAreaList",surveyOrgAreaList);
            return new ModelAndView("/survey/surveyFranchisee/areaInformationList", model);
        }
        //邮箱模板
        else if("emailInfoOrg".equals(surveyCode)){
            String modelId = req.getParameter("modelId");
            //设置委托机构
            if("1000".equals(btnCode)){
                //所有的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","consignor");//查询“委托方机构”，改变surveyCode值
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
                model.put("consignors",consignors);
                model.put("modelId",modelId);
                model.put("emailInfoName",req.getParameter("modelName"));
                model.put("company",req.getParameter("company"));

                //模板下的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("emailInfoId",modelId);
                appendMap.put("surveyCode","emailInfoOrg");//查询“报告模板对应的委托方机构”，改变surveyCode值
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyEmailInfoOrgDTO>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyEmailInfoOrgDTO> emailInfoOrgs = (List<SurveyEmailInfoOrgDTO>) apiFinalResponse.getResults();
                model.put("emailInfoOrgs",emailInfoOrgs);

                //所有模板下的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("surveyCode","emailInfoOrg");//查询“邮件模板对应的委托方机构”，改变surveyCode值
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyEmailInfoOrgDTO>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
                List<SurveyEmailInfoOrgDTO> emailInfoOrgAll = (List<SurveyEmailInfoOrgDTO>) apiFinalResponse.getResults();
                model.put("emailInfoOrgAll",emailInfoOrgAll);
                return new ModelAndView("/survey/base/surveyEmailInfo/consignorList", model);
            }
            //名下保险公司
            else if("1100".equals(btnCode)){
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType",1); //不分页
                appendMap.put("emailInfoId",modelId);
                appendMap.put("surveyCode","emailInfoOrg");//查询“邮件模板对应的委托方机构”，改变surveyCode值
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyEmailInfoOrgDTO>>>() {};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyEmailInfoOrgDTO> emailInfoOrgs = (List<SurveyEmailInfoOrgDTO>) apiFinalResponse.getResults();
                model.put("emailInfoOrgs",emailInfoOrgs);
                return new ModelAndView("/survey/base/surveyEmailInfo/myConsignorList", model);
            }
        }
        return null;
    }



    /**
     * 根据id，查询关联的子表数据
     */
    @RequestMapping(value = "/selectInfoByRelationId")
    public String selectInfoByRelationId(HttpServletRequest req, HttpServletResponse rsp) {
        String surveyCode = req.getParameter("surveyCode");
        String btnCode = req.getParameter("btnCode");
        Map model = new HashMap();
        model.put("surveyCode", surveyCode);//code标识

        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode",surveyCode);
        //根据“任务类型id”，查询“任务类型-方向名称”
        if("taskInfo".equals(surveyCode)) {
            if("1000".equals(btnCode)) {
                Long taskInfoId = Long.parseLong(req.getParameter("taskInfoId"));
                appendMap.put("taskInfoId", taskInfoId);
                appendMap.put("menuType",1);//不分页
                appendMap.put("btnCode",btnCode);
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        //根据“委托方机构id”，查询“机构部门”
        else if("consignor".equals(surveyCode)) {
            if("1000".equals(btnCode)) {
                Long consignorId = Long.parseLong(req.getParameter("consignorId"));
                appendMap.put("consignorId", consignorId);
                appendMap.put("menuType",1);//不分页
                appendMap.put("btnCode",btnCode);
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }else if("1001".equals(btnCode)) {
                appendMap.put("menuType",1);//不分页
                appendMap.put("btnCode",btnCode);
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req, rsp);
            }
        }
        //根据“委托方机构-部门id”，查询“委托人员”
        else if("consignorDepartment".equals(surveyCode)) {
            if("1000".equals(btnCode)) {
                Long departmentId = Long.parseLong(req.getParameter("departmentId"));
                appendMap.put("departmentId", departmentId);
                appendMap.put("menuType",1);//不分页
                appendMap.put("btnCode",btnCode);
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        //查询所有“调查方机构”
        else if("franchisee".equals(surveyCode)) {
            if("1000".equals(btnCode)) {
                appendMap.put("menuType",1);//不分页
                appendMap.put("btnCode",btnCode);
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req, rsp);
            }
            else if("1600".equals(btnCode)) {
                appendMap.clear();
                String surveyOrgId = req.getParameter("surveyOrgId");
                appendMap.put("menuType",1);//不分页
                appendMap.put("btnCode",btnCode);
                appendMap.put("id",surveyOrgId);
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INVESTIGATOR, appendMap, req, rsp);
            }
        }
        //根据“身份证号”查询名下案件
        else if("riskCase".equals(surveyCode)) {
            if("1000".equals(btnCode)) {
                appendMap.put("menuType",1);//不分页
                appendMap.put("btnCode",btnCode);
                appendMap.put("idNumber",req.getParameter("idNumber"));
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        //根据“地区名字”查询地区信息
        else if("commonArea".equals(surveyCode) || "commonAreaEfficiency".equals(surveyCode)) {
            if("1000".equals(btnCode)) {
                appendMap.put("areaName",req.getParameter("areaName"));
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
            if("2000".equals(btnCode)) {
                appendMap.put("areaId",req.getParameter("areaId"));
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        //根据任务子类，获取 方向结果类型
        else if("taskInfoContent".equals(surveyCode)) {
            if("1000".equals(btnCode)) {
                Long taskInfoContentId = Long.parseLong(req.getParameter("taskInfoContentId"));
                appendMap.put("taskInfoContentId", taskInfoContentId);
                appendMap.put("menuType",1);//不分页
                appendMap.put("btnCode",btnCode);
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        //查询“方向结果类型”
        else if("directionResultType".equals(surveyCode)){
            if("1000".equals(btnCode) || "1100".equals(btnCode)) { //code和name 查重复
                String info = req.getParameter("info");
                appendMap.put("info", info);
                appendMap.put("btnCode",btnCode);
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        //根据子类id与结果id查询分值
        else if("taskInfoScore".equals(surveyCode)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
        }
        //领域类型对应“任务类型”
        else if("businessType".equals(surveyCode)) {
            if("1000".equals(btnCode)) {
                Long businessTypeId = Long.parseLong(req.getParameter("businessTypeId"));
                appendMap.put("businessTypeId", businessTypeId);
                appendMap.put("menuType",1);//不分页
                appendMap.put("btnCode",btnCode);
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        //平台终审人员
        else if("finalJudgmentUser".equals(surveyCode)) {
            if("1000".equals(btnCode)) {
                Long menuType = Long.parseLong(req.getParameter("menuType"));
                appendMap.put("menuType",menuType);//不分页
                appendMap.put("btnCode",btnCode);
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req, rsp);
            }
        }
        //委托机构的委托时效计算（场景：录取案件时，计算“截止时间”）
        else if("entrustEndTime".equals(surveyCode)) {
            if("1000".equals(btnCode)) {
                appendMap.put("btnCode",btnCode);
                appendMap.put("entrustOrgId",req.getParameter("entrustOrgId"));
                appendMap.put("serviceId",req.getParameter("serviceId"));
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        //机构名下的调查员（省级及片区机构）
        else if("investigator".equals(surveyCode)) {
            if("3000".equals(btnCode)) {
                appendMap.put("btnCode",btnCode);
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
            }
        }
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
    }
}
