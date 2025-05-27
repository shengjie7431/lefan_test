package com.lefancrm.backend.web.fina;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.dto.fina.*;
import com.lefancrm.backend.web.BackendBaseController;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by wangwei on 2018/12/21.
 */
@Controller
@RequestMapping(value = "/finaManager")
public class FinaBackendMangerController extends BackendBaseController {

    /**
     * list
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req) {
        String surveyCode = req.getParameter("surveyCode");
        Map model = new HashMap();
        model.put("surveyCode", surveyCode);//code标识
        model.put("pageSize", req.getParameter("pageSize"));
        //委托时效模板
        if ("consignorEfficiencyModel".equals(surveyCode)) {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorEfficiencyModelDto>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_ENTRUST_MODEL, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            String name = req.getParameter("name");
            model.put("name", name);
            String type = req.getParameter("type");
            model.put("type", type);
            return new ModelAndView("/fina/surveyConsignorEfficiencyModel/list", model);
        }
        //价格模板
        else if ("priceModel".equals(surveyCode)) {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinaSurveyPriceModelDto>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            String name = req.getParameter("name");
            model.put("name", name);
            String type = req.getParameter("type");
            model.put("type", type);
            String useObj = req.getParameter("useObj");
            model.put("useObj", useObj);
            return new ModelAndView("/fina/surveyPriceModel/list", model);
        }
        //签约模板
        else if("signModel".equals(surveyCode)){
            model.put("modelName",req.getParameter("modelName"));
            model.put("modelType",req.getParameter("modelType"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinaSignModelDto>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_SIGN_MODEL, null, req);
            model.put("apiRsp",apiFinalResponse);
            return new ModelAndView("/fina/signModel/list",model);
        }
        //系数模板
        else if("coefModel".equals(surveyCode)){
            model.put("name",req.getParameter("name"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinaSurveyCoefficientModelDto>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_COEFFICIENT_MODEL, null, req);
            model.put("apiRsp",apiFinalResponse);
            return new ModelAndView("/fina/coefficientModel/list",model);
        }
        return null;
    }

    /**
     * add页面
     */
    @RequestMapping(value = "/add")
    public ModelAndView add(HttpServletRequest req, HttpServletResponse rsp) {
        String surveyCode = req.getParameter("surveyCode");
        Map model = new HashMap();
        model.put("surveyCode", surveyCode);//code标识
        //价格模板
        if ("priceModel".equals(surveyCode)) {
            model.put("surveyCode", "priceModelAdd");
            return new ModelAndView("/fina/surveyPriceModel/edit", model);
        }
        //区域类别对应具体区域
        else if ("priceModelAreaCategories".equals(surveyCode)) {
            Map hashMap = new HashMap();
            hashMap.put("fromType", "add");
            hashMap.put("surveyCode", surveyCode);
            model.put("priceModelId", req.getParameter("priceModelId"));//价格模板id
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_AREA_TYPE_LIST, hashMap, req);
            List<CommonArea> commonAreaList = (List<CommonArea>) apiFinalResponse.getResults();
            model.put("commonAreaList", commonAreaList);
            return new ModelAndView("/fina/surveyPriceModel/areaCityEdit", model);
        }
        //区域类别对应具体区域
        else if ("coefModelAreaCategories".equals(surveyCode)) {
            Map hashMap = new HashMap();
            hashMap.put("fromType", "add");
            hashMap.put("surveyCode", surveyCode);
            model.put("modelId", req.getParameter("modelId"));//价格模板id
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_COEFFICIENT_MODEL, hashMap, req);
            List<CommonArea> commonAreaList = (List<CommonArea>) apiFinalResponse.getResults();
            model.put("commonAreaList", commonAreaList);
            return new ModelAndView("/fina/coefficientModel/areaCityEdit", model);
        }
        //委托时效模板
        else if ("consignorEfficiencyModelArea".equals(surveyCode)) {
            Map hashMap = new HashMap();
            hashMap.put("fromType", "add");
            hashMap.put("surveyCode", surveyCode);
            model.put("modelId", req.getParameter("modelId"));//价格模板id
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_ENTRUST_MODEL, hashMap, req);
            List<CommonArea> commonAreaList = (List<CommonArea>) apiFinalResponse.getResults();
            model.put("commonAreaList", commonAreaList);
            return new ModelAndView("/fina/surveyConsignorEfficiencyModel/areaCityEdit", model);
        }
        //委托时效模板
        else if ("consignorEfficiencyModel".equals(surveyCode)) {
            model.put("type", req.getParameter("type"));
            model.put("surveyCode", "agingModelAdd");
            return new ModelAndView("/fina/surveyConsignorEfficiencyModel/edit", model);
        }
        //签约模板
        else if ("signModel".equals(surveyCode)){
            model.put("surveyCode", "signModelAdd");
            return new ModelAndView("/fina/signModel/edit", model);
        }
        //系数模板
        else if ("coefModel".equals(surveyCode)){
            model.put("surveyCode", "coefModelAdd");
            return new ModelAndView("/fina/coefficientModel/edit", model);
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
        appendMap.put("surveyCode", surveyCode);

        //价格模板
        if ("priceModel".equals(surveyCode)) {
            appendMap.put("id", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<FinaSurveyPriceModelDto>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL_INFO, appendMap, null);
            model.put("surveyPriceModel", apiFinalResponse.getResults());
            model.put("surveyCode", "priceModelUpd");
            return new ModelAndView("/fina/surveyPriceModel/edit", model);
        }
        //区域类别对应具体区域
        else if ("priceModelAreaCategories".equals(surveyCode)) {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyPriceModelAreaCategoriesDto>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL_INFO, appendMap, req);
            model.put("priceModelAreaCategories", apiFinalResponse.getResults());
            model.put("priceModelId", req.getParameter("priceModelId"));//价格模板id

            appendMap.put("fromType", "edit");
            appendMap.put("surveyCode", surveyCode);
            typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL, appendMap, req);
            List<CommonArea> commonAreaList = (List<CommonArea>) apiFinalResponse.getResults();
            model.put("commonAreaList", commonAreaList);

            return new ModelAndView("/fina/surveyPriceModel/areaCityEdit", model);
        }
        //设置价格
        else if ("surveyPrice".equals(surveyCode)) {

            appendMap = new HashMap<String, Object>();
            appendMap.put("type", req.getParameter("type"));
            appendMap.put("surveyCode", "priceTaskList");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinaTaskInfoDto>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL_INFO, appendMap, req);
            model.put("taskInfos", apiFinalResponse.getResults());

            //互助价格
            appendMap = new HashMap<String, Object>();
            appendMap.put("priceModelId", req.getParameter("priceModelId"));//价格模板id
            appendMap.put("surveyCode", "priceList");
            typeToken = new TypeToken<ApiFinalResponse<List<FinaSurveyPriceModelAreaCategoriesDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL_INFO, appendMap, null);
            model.put("areaCategories", apiFinalResponse.getResults());
            model.put("priceModelId", req.getParameter("priceModelId"));
            model.put("type", req.getParameter("type"));
            return new ModelAndView("/fina/surveyPriceModel/addHzPrice", model);

        } else if ("surveyEfficiency".equals(surveyCode)) {
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode", "surveyEfficiency1");
            appendMap.put("sortRule", 1);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinaSurveyServiceTypeDto>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_ENTRUST_MODEL, appendMap, req);
            List<FinaSurveyServiceTypeDto> services = (List<FinaSurveyServiceTypeDto>) apiFinalResponse.getResults();
            model.put("services", services);

            //互助价格
            appendMap = new HashMap<String, Object>();
            appendMap.put("modelId", req.getParameter("modelId"));//价格模板id
            appendMap.put("surveyCode", "surveyEfficiency2");
            typeToken = new TypeToken<ApiFinalResponse<List<FinaSurveyConsignorEfficiencyModelAreaDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_ENTRUST_MODEL, appendMap, null);
            model.put("areaCategories", apiFinalResponse.getResults());
            model.put("modelId", req.getParameter("modelId"));
            model.put("type", req.getParameter("type"));
            return new ModelAndView("/fina/surveyConsignorEfficiencyModel/addHzPrice", model);
        }
        //委托时效模板
        else if ("consignorEfficiencyModel".equals(surveyCode)) {
            //委托模板信息，内含“价格体系”
            TypeToken typeToken = new TypeToken<ApiFinalResponse<FinaSurveyConsignorEfficiencyModelDto>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_ENTRUST_MODEL_INFO, null, req);
            FinaSurveyConsignorEfficiencyModelDto surveyConsignorEfficiencyModel = (FinaSurveyConsignorEfficiencyModelDto) apiFinalResponse.getResults();
            model.put("surveyConsignorEfficiencyModel", surveyConsignorEfficiencyModel);
            model.put("btnCode", req.getParameter("btnCode"));
            model.put("type", req.getParameter("type"));
            model.put("surveyCode", "agingModelUpd");
            return new ModelAndView("/fina/surveyConsignorEfficiencyModel/edit", model);
        } else if ("consignorEfficiencyModelArea".equals(surveyCode)) {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<FinaSurveyConsignorEfficiencyModelAreaDto>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_ENTRUST_MODEL_INFO, appendMap, req);
            model.put("efficiencyModelArea", apiFinalResponse.getResults());
            model.put("modelId", req.getParameter("modelId"));//价格模板id
            appendMap.put("fromType", "edit");
            appendMap.put("surveyCode", surveyCode);
            typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_ENTRUST_MODEL, appendMap, req);
            List<CommonArea> commonAreaList = (List<CommonArea>) apiFinalResponse.getResults();
            model.put("commonAreaList", commonAreaList);

            return new ModelAndView("/fina/surveyConsignorEfficiencyModel/areaCityEdit", model);
        }else if ("signModel".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<FinaSignModelDto>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_SIGN_MODEL_INFO, appendMap, req);
            model.put("info",apiFinalResponse.getResults());
            model.put("surveyCode","signModelUpd");
            return new ModelAndView("/fina/signModel/edit", model);
        }else if ("coefModel".equals(surveyCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<FinaSurveyCoefficientModelDto>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_COEFFICIENT_MODEL_INFO, appendMap, req);
            model.put("info",apiFinalResponse.getResults());
            model.put("surveyCode","coefModelUpd");
            return new ModelAndView("/fina/coefficientModel/edit", model);
        } else if ("coefModelAreaCategories".equals(surveyCode)) {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<FinaSurveyCoefficientModelAreaDto>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_COEFFICIENT_MODEL_INFO, appendMap, req);
            model.put("efficiencyModelArea", apiFinalResponse.getResults());
            model.put("modelId", req.getParameter("modelId"));//价格模板id
            appendMap.put("fromType", "edit");
            appendMap.put("surveyCode", surveyCode);
            typeToken = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_COEFFICIENT_MODEL, appendMap, req);
            List<CommonArea> commonAreaList = (List<CommonArea>) apiFinalResponse.getResults();
            model.put("commonAreaList", commonAreaList);

            return new ModelAndView("/fina/coefficientModel/areaCityEdit", model);
        } else if ("coefEfficiency".equals(surveyCode)) {
            //互助价格
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","coefEfficiency");
            appendMap.put("modelId", req.getParameter("modelId"));//价格模板id
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinaSurveyCoefficientModelAreaDto>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_COEFFICIENT_MODEL, appendMap, null);
            model.put("areaCategories", apiFinalResponse.getResults());
            model.put("modelId", req.getParameter("modelId"));
            model.put("type", req.getParameter("type"));
            return new ModelAndView("/fina/coefficientModel/addHzPrice", model);
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
        if ("consignorEfficiencyModelArea".equals(surveyCode)) {
            if (StringUtils.isNotBlank(req.getParameter("id"))) {
                //防止超时
                List<String> allData = Arrays.asList(req.getParameter("cityIds").split(","));
                int size = 50;
                appendMap.put("first", "true");
                for (int begin = 0; begin < allData.size(); begin = begin + size) {
                    int end = Math.min(begin + size, allData.size());
                    List<String> subList = allData.subList(begin, end);
                    appendMap.put("cityIds", StringUtils.strip(subList.toString(), "[]"));
                    if (allData.size() - begin <= 50) {
                        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_ENTRUST_MODEL_OPERATE, appendMap, req, rsp);
                    } else {
                        this.callApi(BackendApiMethodEnum.BACKEND_FINA_MANAGER_ENTRUST_MODEL_OPERATE, appendMap, req);
                    }
                    appendMap.put("first", "false");
                }
            } else {
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_ENTRUST_MODEL_OPERATE, appendMap, req, rsp);
            }
            return null;
        }
        if ("priceModelAreaCategories".equals(surveyCode)) {
            if (StringUtils.isNotBlank(req.getParameter("id"))) {
                //防止超时
                List<String> allData = Arrays.asList(req.getParameter("cityIds").split(","));
                int size = 50;
                appendMap.put("first", "true");
                for (int begin = 0; begin < allData.size(); begin = begin + size) {
                    int end = Math.min(begin + size, allData.size());
                    List<String> subList = allData.subList(begin, end);
                    appendMap.put("cityIds", StringUtils.strip(subList.toString(), "[]"));
                    if (allData.size() - begin <= 50) {
                        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL_OPERATE, appendMap, req, rsp);
                    } else {
                        this.callApi(BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL_OPERATE, appendMap, req);
                    }
                    appendMap.put("first", "false");
                }
            } else {
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL_OPERATE, appendMap, req, rsp);
            }
            return null;
        }
        if ("coefModelAreaCategories".equals(surveyCode)) {
            if (StringUtils.isNotBlank(req.getParameter("id"))) {
                //防止超时
                List<String> allData = Arrays.asList(req.getParameter("cityIds").split(","));
                int size = 50;
                appendMap.put("first", "true");
                for (int begin = 0; begin < allData.size(); begin = begin + size) {
                    int end = Math.min(begin + size, allData.size());
                    List<String> subList = allData.subList(begin, end);
                    appendMap.put("cityIds", StringUtils.strip(subList.toString(), "[]"));
                    if (allData.size() - begin <= 50) {
                        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_COEFFICIENT_MODEL_OPERATE, appendMap, req, rsp);
                    } else {
                        this.callApi(BackendApiMethodEnum.BACKEND_FINA_MANAGER_COEFFICIENT_MODEL_OPERATE, appendMap, req);
                    }
                    appendMap.put("first", "false");
                }
            } else {
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_COEFFICIENT_MODEL_OPERATE, appendMap, req, rsp);
            }
            return null;
        }
        if ("priceModelAdd".equals(surveyCode) || "priceModelUpd".equals(surveyCode) || "priceModelDelete".equals(surveyCode) || "priceModelCopy".equals(surveyCode)) {
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL_OPERATE, appendMap, req, rsp);
        }
        if ("signModelAdd".equals(surveyCode) || "signModelDel".equals(surveyCode) || "signModelUpd".equals(surveyCode)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_SIGN_MODEL_OPERATE, appendMap, req, rsp);
        }
        if ("coefModelAdd".equals(surveyCode) || "coefModelDel".equals(surveyCode) || "coefModelUpd".equals(surveyCode)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_COEFFICIENT_MODEL_OPERATE, appendMap, req, rsp);
        }
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_ENTRUST_MODEL_OPERATE, appendMap, req, rsp);

    }

    /**
     * 数据处理
     */
    @RequestMapping(value = "/operate")
    public String operate(HttpServletRequest req, HttpServletResponse rsp) {
        String surveyCode = req.getParameter("surveyCode");
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode", surveyCode);//code标识
        if ("priceEntrustOrgSet".equals(surveyCode) || "priceSurveyOrgSet".equals(surveyCode) || "priceAreaDel".equals(surveyCode) || "surveyPriceSet".equals(surveyCode) || "copyPrice".equals(surveyCode)) {
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL_OPERATE, appendMap, req, rsp);
        }
        if ("signModelOrgSet".equals(surveyCode)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_SIGN_MODEL_OPERATE, appendMap, req, rsp);
        }
        if ("coefAreaDel".equals(surveyCode) || ("coefEfficiency".equals(surveyCode))){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_COEFFICIENT_MODEL_OPERATE, appendMap, req, rsp);
        }
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_ENTRUST_MODEL_OPERATE, appendMap, req, rsp);

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
        appendMap.put("surveyCode", surveyCode);

        //价格模板
        if ("priceModel".equals(surveyCode)) {
            String id = req.getParameter("id");
            model.put("id", id);
            String type = req.getParameter("type");//机构类型：1、委托方机构；2、调查方机构
            String orgAttr = req.getParameter("orgAttr");
            model.put("type", type);
            //设置委托机构
            if ("2000".equals(btnCode)) {
                //所有的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("surveyCode", "consignor");//查询“委托方机构”，改变surveyCode值
                appendMap.put("orgAttr", orgAttr); //type被其他地方使用过 还是用tttType吧 1保险 2互助
                appendMap.put("type", null);
                appendMap.put("company", req.getParameter("company"));//搜索
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
                model.put("consignors", consignors);

                //模板下的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("priceModelId", id);
                appendMap.put("surveyCode", "priceModelOrg");//查询“价格模板对应机构”，改变surveyCode值
                appendMap.put("type", type); //机构类型：1、委托方机构；2、调查方机构
                typeToken = new TypeToken<ApiFinalResponse<List<FinaSurveyPriceModelOrgDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL, appendMap, req);
                List<FinaSurveyPriceModelOrgDto> priceModelConsignors = (List<FinaSurveyPriceModelOrgDto>) apiFinalResponse.getResults();
                model.put("priceModelConsignors", priceModelConsignors);

                //所有模板下的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("surveyCode", "priceModelOrg");
                appendMap.put("type", type); //机构类型：1、委托方机构；2、调查方机构
                typeToken = new TypeToken<ApiFinalResponse<List<FinaSurveyPriceModelOrgDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL, appendMap, null);
                List<FinaSurveyPriceModelOrgDto> priceModelConsignorAll = (List<FinaSurveyPriceModelOrgDto>) apiFinalResponse.getResults();
                model.put("priceModelConsignorAll", priceModelConsignorAll);
                model.put("company", req.getParameter("company"));//搜索
                model.put("surveyCode", "priceEntrustOrgSet");
                return new ModelAndView("/fina/surveyPriceModel/orgList", model);
            } else if ("2100".equals(btnCode)) {
                //所有的调查机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("surveyCode", "franchisee");//查询“调查方机构”，改变surveyCode值
                appendMap.put("type", null);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
                model.put("franchisees", franchisees);

                //模板下的调查方机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("priceModelId", id);
                appendMap.put("surveyCode", "priceModelOrg");//查询“价格模板对应机构”，改变surveyCode值
                appendMap.put("type", type);//机构类型：1、委托方机构；2、调查方机构
                typeToken = new TypeToken<ApiFinalResponse<List<FinaSurveyPriceModelOrgDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL, appendMap, req);
                List<FinaSurveyPriceModelOrgDto> priceModelFranchisees = (List<FinaSurveyPriceModelOrgDto>) apiFinalResponse.getResults();
                model.put("priceModelFranchisees", priceModelFranchisees);
                model.put("surveyCode", "priceSurveyOrgSet");
                return new ModelAndView("/fina/surveyPriceModel/orgList", model);
            } else if ("3000".equals(btnCode)) {
                //所有的区域类别，以及区域类别对应的具体区域
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("surveyCode", "areaCategories");
                appendMap.put("priceModelId", id);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinaSurveyPriceModelAreaCategoriesDto>>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL, appendMap, req);
                List<FinaSurveyPriceModelAreaCategoriesDto> infos = (List<FinaSurveyPriceModelAreaCategoriesDto>) apiFinalResponse.getResults();
                model.put("infos", infos);
                return new ModelAndView("/fina/surveyPriceModel/areaCategoriesList", model);
            }
        }
        //委托方时效 -- 设置委托方
        else if ("consignorEfficiencyModel".equals(surveyCode)) {
            String id = req.getParameter("id");
            model.put("id", id);
            //设置委托机构
            if ("2000".equals(btnCode)) {
                //所有的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("surveyCode", "consignor");//查询“委托方机构”，改变surveyCode值
                appendMap.put("company", req.getParameter("company"));//搜索
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
                typeToken = new TypeToken<ApiFinalResponse<List<FinaSurveyConsignorEfficiencyModelOrgDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_ENTRUST_MODEL, appendMap, req);
                List<FinaSurveyConsignorEfficiencyModelOrgDto> modelOrgs = (List<FinaSurveyConsignorEfficiencyModelOrgDto>) apiFinalResponse.getResults();
                model.put("modelOrgs", modelOrgs);

                //所有模板下的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("surveyCode", "consignorEfficiencyModelOrg");
                typeToken = new TypeToken<ApiFinalResponse<List<FinaSurveyConsignorEfficiencyModelOrgDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_ENTRUST_MODEL, appendMap, null);
                List<FinaSurveyConsignorEfficiencyModelOrgDto> modelOrgAll = (List<FinaSurveyConsignorEfficiencyModelOrgDto>) apiFinalResponse.getResults();
                model.put("modelOrgAll", modelOrgAll);
                model.put("company", req.getParameter("company"));//搜索
                model.put("surveyCode", "entrustOrgSet");
                return new ModelAndView("/fina/surveyConsignorEfficiencyModel/consignorList", model);
            } else if ("3000".equals(btnCode)) {
                //所有的区域类别，以及区域类别对应的具体区域
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("surveyCode", "efficiencyArea");
                appendMap.put("modelId", id);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinaSurveyConsignorEfficiencyModelAreaDto>>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_ENTRUST_MODEL, appendMap, req);
                List<FinaSurveyConsignorEfficiencyModelAreaDto> infos = (List<FinaSurveyConsignorEfficiencyModelAreaDto>) apiFinalResponse.getResults();
                model.put("infos", infos);
                return new ModelAndView("/fina/surveyConsignorEfficiencyModel/areaCategoriesList", model);
            }
        }else if ("signModel".equals(surveyCode)){
            if ("2000".equals(btnCode)){
                String id = req.getParameter("id");
                model.put("id", id);
                //所有的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("surveyCode", "consignor");
                appendMap.put("orgAttr", 1);
                appendMap.put("company", req.getParameter("company"));//搜索
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
                model.put("consignors", consignors);

                //模板下的委托机构
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("modelId", id);
                appendMap.put("surveyCode", "signModelOrg");//查询“价格模板对应机构”，改变surveyCode值
                appendMap.put("type", 1); //机构类型：1、委托方机构；2、调查方机构
                typeToken = new TypeToken<ApiFinalResponse<List<FinaModelEntrustMidDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_SIGN_MODEL, appendMap, req);
                List<FinaModelEntrustMidDto> modelEntrustMidDtos = (List<FinaModelEntrustMidDto>) apiFinalResponse.getResults();
                model.put("signModelConsignors", modelEntrustMidDtos);
                model.put("surveyCode", "signModelOrgSet");
                return new ModelAndView("/fina/signModel/orgList", model);
            }

        }else if ("coefModel".equals(surveyCode)){
            if ("3000".equals(btnCode)){
                String id = req.getParameter("id");
                model.put("id",id);
                //所有的区域类别，以及区域类别对应的具体区域
                appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("surveyCode", "coefficientArea");
                appendMap.put("modelId", id);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinaSurveyCoefficientModelAreaDto>>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINA_MANAGER_COEFFICIENT_MODEL, appendMap, req);
                List<FinaSurveyCoefficientModelAreaDto> infos = (List<FinaSurveyCoefficientModelAreaDto>) apiFinalResponse.getResults();
                model.put("infos", infos);
                return new ModelAndView("/fina/coefficientModel/areaCategoriesList", model);
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
        appendMap.put("surveyCode", surveyCode);
        //根据“地区名字”查询地区信息
        if ("commonArea".equals(surveyCode) || "commonAreaEfficiency".equals(surveyCode)) {
            if ("1000".equals(btnCode)) {
                appendMap.put("areaId", req.getParameter("areaId"));
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL, appendMap, req, rsp);
            }
            if ("2000".equals(btnCode)) {
                appendMap.put("areaId", req.getParameter("areaId"));
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_ENTRUST_MODEL, appendMap, req, rsp);
            }
            if ("3000".equals(btnCode)) {
                appendMap.put("areaId", req.getParameter("areaId"));
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_COEFFICIENT_MODEL, appendMap, req, rsp);
            }
        }
        if ("getAreaIdList".equals(surveyCode)) {
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL, appendMap, req, rsp);
        }
        if ("copyPrice".equals(surveyCode)) {
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINA_MANAGER_PRICE_MODEL_INFO, appendMap, req, rsp);
        }
        return null;
    }
}
