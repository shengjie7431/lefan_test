package com.lefancrm.backend.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.dto.help.TemplateHelpData;
import com.lefancrm.backend.dto.survey.SurveyEmailInfoDTO;
import com.lefancrm.backend.dto.survey.SurveyRiskCaseVisitDto;
import com.lefancrm.backend.enums.SurveyRiskCaseDelegationModeEnum;
import com.lefancrm.backend.util.*;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import com.lefancrm.base.utils.Md5Util;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Number;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.Boolean;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by lixianfeng on 2018/12/24.
 */
@Controller
@RequestMapping(value = "/survey/case/")
public class BackendSurveyCaseController extends BackendBaseController {
    private static final Logger log = LoggerFactory.getLogger(BackendSurveyCaseController.class);
    @Value("${survey.report.path}")
    private String generateFilePath;
    @Value("${survey.setting.source}")
    private String surveySettingSource;
    @Value("${survey.file.path.sftp}")
    private String surveyFilePathSftp;
    @Value("${survey.account.path}")
    private String accountExcelPath;
    @Value("${survey.file.source.sftp}")
    private String surveyFilePathSftpSource;
    @Value("${survey.upload.ip}")
    private String surveyUploadIp;

    @Value("${survey.transfer.path}")
    private String transferPath;
    @Value("${nuanwa.company}")
    private String nuanWaCompany;
    @Value("${nuanwa.secret}")
    private String nuanWaSecret;
    @Value("${survey.direction.path}")
    private String directionFilePath;


    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        Map<String, Object> appendMapList = new HashMap<String, Object>();
        String menuCode = req.getParameter("menuCode");
        String btnCode = req.getParameter("btnCode");
        Map model = new HashMap();
        if ("xhbCase".equals(menuCode)) {
            return new ModelAndView("/survey/case/xhbCase", model);
        } else if ("xhbCaseSettlement".equals(menuCode)) {
            return new ModelAndView("/survey/case/xhbCaseSettlement", model);
        }
        model.put("pageSize", req.getParameter("pageSize"));
        model.put("surveyPerson", req.getParameter("surveyPerson"));
        model.put("surveyPhase", req.getParameter("surveyPhase"));
        model.put("surveyNo", req.getParameter("surveyNo"));
        model.put("menuCode", req.getParameter("menuCode"));
        model.put("isSendReport", req.getParameter("isSendReport"));//是否寄送
        model.put("price1IsCalc", req.getParameter("price1IsCalc"));//基本费是否结算
        model.put("price2IsCalc", req.getParameter("price2IsCalc"));//减损奖励是否结算
        model.put("surveryPersonTel", req.getParameter("surveryPersonTel"));//联系电话
        model.put("entrustOrgName", req.getParameter("entrustOrgName"));//委托机构
        model.put("entrustOrgId", req.getParameter("entrustOrgId"));//委托机构Id
        model.put("surveyOrgId", req.getParameter("surveyOrgId"));//调查方Id
        model.put("assignState", req.getParameter("assignState"));//调查员分派状态
        model.put("orgAssign", req.getParameter("orgAssign"));//机构分派状态
        model.put("surveyState", req.getParameter("surveyState"));//机构分派状态
        model.put("policyNo", req.getParameter("policyNo"));//保险合同编号
        model.put("priceIsCalcType", req.getParameter("priceIsCalcType"));//费用类型
        model.put("operateState", req.getParameter("operateState"));//调查审核状态 待审核 已审核
        model.put("belongUserName", req.getParameter("belongUserName"));//案件归属人
        model.put("surveyCaseNo", req.getParameter("surveyCaseNo"));//案件编号

        model.put("searchStr", req.getParameter("searchStr"));//快捷查询

        String order = req.getParameter("order");
        if (!StringUtils.isEmpty(order)) {
            appendMap.put("order", order);
        }
        String colSortType = req.getParameter("colSortType");
        if (!StringUtils.isEmpty(colSortType)) {
            appendMap.put("colSortType", colSortType);
        }
        model.put("order", order);
        model.put("colSortType", colSortType);

        //多选
        model.put("entrustOrgIds", req.getParameter("entrustOrgIds") == null ? "" : req.getParameter("entrustOrgIds"));
        model.put("departmentIds", req.getParameter("departmentIds") == null ? "" : req.getParameter("departmentIds"));
        model.put("surveyOrgIds", req.getParameter("surveyOrgIds") == null ? "" : req.getParameter("surveyOrgIds"));
        model.put("surveyPhases", req.getParameter("surveyPhases") == null ? "" : req.getParameter("surveyPhases"));
        model.put("orgAssigns", req.getParameter("orgAssigns") == null ? "" : req.getParameter("orgAssigns"));
        model.put("assignStates", req.getParameter("assignStates") == null ? "" : req.getParameter("assignStates"));
        model.put("isPayEntrustFees", req.getParameter("isPayEntrustFees") == null ? "" : req.getParameter("isPayEntrustFees"));
        model.put("surveyStates", req.getParameter("surveyStates") == null ? "" : req.getParameter("surveyStates"));
        model.put("serviceTypes", req.getParameter("surveyStates") == null ? "" : req.getParameter("serviceTypes"));
        model.put("reviewStartTime", req.getParameter("reviewStartTime") == null ? "" : req.getParameter("reviewStartTime"));
        model.put("reviewEndTime", req.getParameter("reviewEndTime") == null ? "" : req.getParameter("reviewEndTime"));

        //查询条件：案件归属清单、时效跟踪清单、调查审核清单：获取“平台终审人员”对应的“委托方机构”和“调查方机构”
        if ("belong-list".equals(menuCode) || "survey-list".equals(menuCode) || "time-track-list".equals(menuCode)) {
            //名下的委托方机构
            if ("survey-list".equals(menuCode)) {
                appendMap.put("oprType", "safe");//用以区分保司与互助机构
                appendMap.put("oprTypeValue", "safe");
            }
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CONSIGNOR_LIST_FOR_FINAL_USER, appendMap, req);
            List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
            model.put("consignors", consignors);

            //名下的调查方机构
            appendMap = new HashMap<String, Object>();
            if ("survey-list".equals(menuCode)) {
                //用以区分保司与互助机构
                appendMap.put("oprTypeValue", "safe");
            }
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_FRANCHISEE_LIST_FOR_FINAL_USER, appendMap, req);
            List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
            model.put("franchisees", franchisees);
        } else {
            //所有的委托方机构
            appendMap = new HashMap<String, Object>();
            if ("agent-entrust-list".equals(menuCode) && "bsCase".equals(btnCode)) { //保司案件批量处理
                appendMap.put("orgAttr", 1); //1：保险公司；2、互助机构
            }
            Boolean surveyAgentEntrust = false;
            if ("entrust-list".equals(menuCode) || "my-list".equals(menuCode)) { //委托机构
                appendMap.put("btnCode", "myInfo");//仅获取自身的委托机构

                TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseUserRoleDto>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_USER_ROLE, null, req);
                CaseUserRoleDto lawUserRole = (CaseUserRoleDto) apiFinalResponse.getResults();
                if (lawUserRole.getSurveyAgentEntrust()) {
                    surveyAgentEntrust = true;
                    appendMap.remove("btnCode");//仅获取自身的委托机构
                }
            }
            appendMap.put("menuType", 1); //不分页
            appendMap.put("surveyCode", "consignor");//查询所有的委托方
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
            model.put("consignors", consignors);

            if ("entrust-list".equals(menuCode) || "my-list".equals(menuCode)) { //委托机构
                if (consignors != null) {
                    if (!surveyAgentEntrust) {
                        appendMapList.put("entrustOrgIds", consignors.get(0).getId());//仅获取自身的委托机构
                    }
                }
            }
            //所有的调查方
            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType", 1); //不分页
            appendMap.put("surveyCode", "franchisee");//查询所有的调查方
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
            model.put("franchisees", franchisees);

            //对账清单
            if ("account-list".equals(menuCode)) {
                //平台复审人员主管
                typeToken = new TypeToken<ApiFinalResponse<CaseUserRoleDto>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_USER_ROLE, null, req);
                CaseUserRoleDto lawUserRole = (CaseUserRoleDto) apiFinalResponse.getResults();
                //平台复审人员主管
                if (lawUserRole.getIsFinalUserManage()) {
                    typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
                    };
                    apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CONSIGNOR_LIST_FOR_FINAL_USER, appendMap, req);
                    consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
                    model.put("consignors", consignors);
                    model.put("isFinalUserManage", 1);//代表是平台复审人员主管 查看
                    appendMapList.put("isFinalUserManage", 1);//代表是平台复审人员主管 查看
                }
            }
        }

        ////调查费用结算 默认 选择第一个

        if ("mark-list".equals(menuCode) || "bill-list".equals(menuCode)) {
            if (req.getParameter("entrustOrgIds") == null) {
                List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) model.get("consignors");
                if (consignors != null) {
                    if (consignors.size() > 0) {
                        Long entrustOrgId = consignors.get(0).getId();
                        appendMapList.put("entrustOrgIds", entrustOrgId);
                        model.put("entrustOrgIds", entrustOrgId);
                    }
                }
            } else {
                //多选
                model.put("entrustOrgIds", req.getParameter("entrustOrgIds"));
            }
        }

        //保司审核时间 -- 保司审核开始时间
        String entrReportStateDate = req.getParameter("entrReportStateDate");
        String entrReportEndDate = req.getParameter("entrReportEndDate");
        if ("mark-list".equals(menuCode)) {
            if (entrReportStateDate == null && entrReportEndDate == null) {
                StringBuilder start = new StringBuilder();
                StringBuilder end = new StringBuilder();
                DateUtil.convertTimeBySearchType(start, end, "upMonth");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date d1 = simpleDateFormat.parse(start.toString());
                    Date d2 = simpleDateFormat.parse(end.toString());
                    entrReportStateDate = simpleDateFormat.format(d1);
                    entrReportEndDate = simpleDateFormat.format(d2);
                    appendMapList.put("entrReportStateDate", entrReportStateDate);
                    appendMapList.put("entrReportEndDate", entrReportEndDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        if ("mark-list".equals(menuCode) || "all-list".equals(menuCode)) {
            model.put("entrReportStateDate", entrReportStateDate);
            model.put("entrReportEndDate", entrReportEndDate);
        }

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoDto>>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_LIST, appendMapList, req);
        model.put("apiRsp", apiFinalResponse);
        //批量开票
        if ("bill-list".equals(menuCode)) {
            String isPayEntrustFee = req.getParameter("isPayEntrustFee");
            model.put("isPayEntrustFee", isPayEntrustFee == null ? "" : isPayEntrustFee);

            //创建时间
            String date = req.getParameter("date");
            model.put("date", date == null ? "" : date);

            //终审时间 -- 保司终审通过时间
            model.put("entrReportStateDate", entrReportStateDate == null ? "" : entrReportStateDate);
            model.put("entrReportEndDate", entrReportEndDate == null ? "" : entrReportEndDate);
            model.put("sortField", req.getParameter("sortField"));
            model.put("sortType", req.getParameter("sortType"));
            return new ModelAndView("/survey/case/billList", model);
        }
        if ("agent-entrust-list".equals(menuCode) && "bsCase".equals(btnCode)) { //保司案件批量处理
            model.put("btnCode", btnCode);
            model.put("haveSunMoney", req.getParameter("haveSunMoney"));
            model.put("isSun", req.getParameter("isSun"));
            model.put("entrustOrgIds", req.getParameter("entrustOrgIds") == null ? 94 : req.getParameter("entrustOrgIds"));//默认众安
            return new ModelAndView("/survey/case/bsCaseList", model);
        }
        if ("my-list".equals(menuCode) || "survey-list".equals(menuCode)) {
            typeToken = new TypeToken<ApiFinalResponse<CaseUserRoleDto>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_USER_ROLE, null, req);
            CaseUserRoleDto lawUserRole = (CaseUserRoleDto) apiFinalResponse.getResults();
            model.put("surveyAgentEntrust", lawUserRole.getSurveyAgentEntrust());
            model.put("surveyEntrust", lawUserRole.getSurveyEntrust());
            model.put("surveyBelong", lawUserRole.getIsBelong());
            model.put("surveyFinalUserManage", lawUserRole.getIsFinalUserManage());
            if (lawUserRole.getIsBelong()) {
                //获取复审人员列表
                Map<String, Object> roleMap = new HashMap<String, Object>();
                roleMap.put("roleId", 53);
                roleMap.put("oprUserType", 2);//1查询互助复审人员  2查询保司复审人员
                typeToken = new TypeToken<ApiFinalResponse<List<UserInfoOprDTO>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_OPR_USER, roleMap, req);
                List<UserInfoOprDTO> finalUserInfos = (List<UserInfoOprDTO>) apiFinalResponse.getResults();
                model.put("finalUserInfos", finalUserInfos);
                model.put("finalInfos", req.getParameter("finalInfos")); //多选
            }
        }
        //对账清单
        if ("account-list".equals(menuCode)) {
            //开票状态
            String isPayEntrustFee = req.getParameter("isPayEntrustFee");
            model.put("isPayEntrustFee", isPayEntrustFee == null ? "" : isPayEntrustFee);
            //终审时间 -- 保司审核开始时间
            String reportStartDate = req.getParameter("reportStartDate");
            model.put("reportStartDate", reportStartDate == null ? "" : reportStartDate);
            String reportEndDate = req.getParameter("reportEndDate");
            model.put("reportEndDate", reportEndDate == null ? "" : reportEndDate);

            //保司审核时间 -- 保司审核开始时间
            model.put("entrReportStateDate", entrReportStateDate == null ? "" : entrReportStateDate);
            model.put("entrReportEndDate", entrReportEndDate == null ? "" : entrReportEndDate);

            //是否阳性
            String isSun = req.getParameter("isSun");
            model.put("isSun", isSun == null ? "" : isSun);

            //“测试导出”，权限控制
            typeToken = new TypeToken<ApiFinalResponse<CaseUserRoleDto>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_USER_ROLE, null, req);
            CaseUserRoleDto lawUserRole = (CaseUserRoleDto) apiFinalResponse.getResults();
            model.put("testRole", lawUserRole.getIsTest());
        }

//        if ("time-track-list".equals(menuCode)){
//            return new ModelAndView("/survey/case/timeTrackList",model);
//        }

        if ("assign-list".equals(menuCode) || "all-list".equals(menuCode)) {
            //委托时间
            model.put("entrustStateTime", req.getParameter("entrustStateTime"));
            model.put("entrustEndTime", req.getParameter("entrustEndTime"));
        }


        if ("assign-list".equals(menuCode)) {


            //使用场景：“代理委托”录入案件时，可直接“分派调查员”
            model.put("autoOpenInfo", req.getParameter("autoOpenInfo"));  //是否主动开页面
            model.put("autoOpenInfoId", req.getParameter("autoOpenInfoId")); //开页面的id
        }
        if ("guide-list".equals(menuCode)) { //案件指导
            //指导状态
            model.put("guideState", req.getParameter("guideState"));

            //所有的委托方机构
            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType", 1); //不分页
            appendMap.put("surveyCode", "consignor");//查询“狄大人终审人员对应的委托方”，改变surveyCode值
            appendMap.put("orgAttr", 2);//公司属性（1：保险公司；2、互助机构）
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
            model.put("consignors", consignors);

        }
        return new ModelAndView("/survey/case/list", model);
    }

    @RequestMapping(value = "listAssign")
    public ModelAndView listAssign(HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String menuCode = req.getParameter("menuCode");
        model.put("menuCode", menuCode);
        model.put("pageSize", req.getParameter("pageSize"));
        if ("task-org-review".equals(menuCode)) {
            String reviewOff = req.getParameter("reviewOff");
            if (StringUtils.isEmpty(reviewOff)) {
                reviewOff = "0";
                appendMap.put("reviewOff", reviewOff);
            }
            model.put("reviewOff", reviewOff);//预审状态
        } else if ("help-review".equals(menuCode)) {


            String oprReview = req.getParameter("oprReview");
            if (StringUtils.isEmpty(oprReview)) {
                oprReview = "0";
                appendMap.put("oprReview", oprReview);
            }
            model.put("oprReview", oprReview);//审核状态

            //所有的终审人员
            Map<String, Object> roleMap = new HashMap<String, Object>();
            roleMap.put("roleId", 53);
            roleMap.put("oprUserType", 1);//1查询互助复审人员  2查询保司复审人员
            TypeToken<ApiFinalResponse<List<UserInfoOprDTO>>> typeToken = new TypeToken<ApiFinalResponse<List<UserInfoOprDTO>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_OPR_USER, roleMap, req);
            List<UserInfoOprDTO> finalUserInfos = (List<UserInfoOprDTO>) apiFinalResponse.getResults();
            model.put("finalUserInfos", finalUserInfos);
            model.put("finalUserInfosJson", JsonUtil.objectToJson(finalUserInfos));

            model.put("finalInfos", req.getParameter("finalInfos")); //多选

            appendMap.put("oprType", "help");//互助
            TypeToken typeTokens = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
            };
            apiFinalResponse = this.callApi(typeTokens, BackendApiMethodEnum.BACKEND_SURVEY_CONSIGNOR_LIST_FOR_FINAL_USER, appendMap, req);
            List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
            model.put("consignors", consignors);

            //名下的调查方机构
            Map<String, Object> franchiseeMap = new HashMap<String, Object>();
            franchiseeMap.put("oprTypeValue", "help");//互助
            typeTokens = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {
            };
            apiFinalResponse = this.callApi(typeTokens, BackendApiMethodEnum.BACKEND_SURVEY_FRANCHISEE_LIST_FOR_FINAL_USER, franchiseeMap, req);
            List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
            model.put("franchisees", franchisees);

            //注意不可直接new appendMap; 会影响查询传值
            //
            //

            //人员userId为 477  669 2189  26 的人员，页面上展示 "复审人员列表"
            UserInfo adminSession = this.getSessionAdmin(req);
            Long userId = adminSession.getUserId();
            Boolean showFinalUser = false;
            if (userId == 477 || userId == 669 || userId == 2189 || userId == 26) {
                showFinalUser = true;
            }
            model.put("showFinalUser", showFinalUser);
            model.put("showFinalUserId", userId);

        } else if ("time-track-list".equals(menuCode)) {
            model.put("startDate", req.getParameter("startDate"));
            model.put("endDate", req.getParameter("endDate"));
            model.put("orgSurveyStates", req.getParameter("orgSurveyStates"));
            model.put("serviceTypes", req.getParameter("serviceTypes"));
            String oprType = req.getParameter("oprType");
            if ("help".equals(oprType)) {
                appendMap.put("oprTypeValue", 1);//互助
            } else {
                appendMap.put("oprTypeValue", 2);//保司
            }

            model.put("oprType", oprType);
            String dateItem = req.getParameter("dateItem");
            model.put("dateItem", dateItem == null ? "1" : dateItem);
            appendMap.put("dateItem", dateItem == null ? "1" : dateItem);

            String agingType = req.getParameter("agingType");
            model.put("agingType", agingType == null ? "0" : agingType);
            appendMap.put("agingType", agingType == null ? "0" : agingType);

            String order = req.getParameter("order");
            if (StringUtils.isEmpty(order)) {
                order = "23";
                appendMap.put("order", order);
            }
            String colSortType = req.getParameter("colSortType");
            if (StringUtils.isEmpty(colSortType)) {
                colSortType = "1";
                appendMap.put("colSortType", colSortType);
            }
            model.put("order", order);
            model.put("colSortType", colSortType);

            if ("safe".equals(oprType)) {//时效跟踪保司
                String handquery = req.getParameter("handquery");
                model.put("handquery", handquery);
                if (StringUtils.isBlank(handquery)) {//初始化页面设置默认值
                    if (StringUtils.isBlank(dateItem)) {
                        model.put("dateItem", 3);
                        appendMap.put("dateItem", 3);
                    }
                    if (StringUtils.isBlank(req.getParameter("orgSurveyStates"))) {
                        model.put("orgSurveyStates", "1,2");
                        appendMap.put("orgSurveyStates", "1,2");
                    }
                    if (StringUtils.isBlank(req.getParameter("endDate"))) {
//                        model.put("startDate", LocalDate.now().plusDays(1).toString());新需求，开始时间不做限定
                        model.put("endDate", LocalDate.now().plusDays(1).toString());
                        appendMap.put("endDate", LocalDate.now().plusDays(1).toString());
                    }
                }
            }
        }

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyAssignOrgDto>>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_ASSIGN_ORG_LIST, appendMap, req);
        model.put("apiRsp", apiFinalResponse);
        model.put("surveyNo", req.getParameter("surveyNo"));//调查编号
        model.put("surveyPerson", req.getParameter("surveyPerson"));//被调查人
        model.put("surveryPersonTel", req.getParameter("surveryPersonTel"));//联系方式
        model.put("operateState", req.getParameter("operateState"));//处理状态
        model.put("orgSurveyState", req.getParameter("orgSurveyState"));//案件状态
        model.put("surveyState", req.getParameter("surveyState"));//案件状态
        model.put("surveyInvestigatorCaseIdState", req.getParameter("surveyInvestigatorCaseIdState"));//分派状态
        model.put("policyNo", req.getParameter("policyNo"));//保单号
        model.put("entrustOrgName", req.getParameter("entrustOrgName"));//保险合同编号
        model.put("surveyCaseNo", req.getParameter("surveyCaseNo"));//案件编号
        model.put("surveyStates", req.getParameter("surveyStates"));
        model.put("serviceTypes", req.getParameter("serviceTypes"));
        model.put("entrustOrgIds", req.getParameter("entrustOrgIds"));
        model.put("surveyOrgIds", req.getParameter("surveyOrgIds"));

        model.put("searchStr", req.getParameter("searchStr"));//快捷查询
        model.put("sortField", req.getParameter("sortField"));//快捷查询
        model.put("sortType", req.getParameter("sortType"));//快捷查询

        if ("time-track-list".equals(menuCode) || "extension-time".equals(menuCode)) {
            //名下的委托方机构
            appendMap = new HashMap<String, Object>();
            if ("time-track-list".equals(menuCode)) {
                String oprType = req.getParameter("oprType");
                if ("help".equals(oprType)) {
                    appendMap.put("oprTypeValue", "help");//互助
                } else {
                    appendMap.put("oprTypeValue", "safe");//保司
                }
            }
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CONSIGNOR_LIST_FOR_FINAL_USER, appendMap, req);
            List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
            model.put("consignors", consignors);

            //名下的调查方机构
            appendMap = new HashMap<String, Object>();
            if ("time-track-list".equals(menuCode)) {
                String oprType = req.getParameter("oprType");
                if ("help".equals(oprType)) {
                    appendMap.put("oprTypeValue", "help");//互助
                } else {
                    appendMap.put("oprTypeValue", "safe");//保司
                }
            }
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_FRANCHISEE_LIST_FOR_FINAL_USER, appendMap, req);
            List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
            model.put("franchisees", franchisees);
        }

        if ("time-track-list".equals(menuCode)) {
            model.put("surveyStates", req.getParameter("surveyStates") == null ? "" : req.getParameter("surveyStates"));
//            model.put("orgSurveyStates",req.getParameter("orgSurveyStates")==null?"":req.getParameter("orgSurveyStates"));
            return new ModelAndView("/survey/case/timeTrackList", model);
        } else if ("assign-org-list".equals(menuCode) || "org-review-list".equals(menuCode)) {
            //调查员截止时间
            model.put("surveyStartDate", req.getParameter("surveyStartDate"));
            model.put("surveyEndDate", req.getParameter("surveyEndDate"));
            model.put("surveyUserName", req.getParameter("surveyUserName"));//调查员
            model.put("surveyOperateState", req.getParameter("surveyOperateState"));
            model.put("investigators", req.getParameter("investigators"));
            model.put("agingType", req.getParameter("agingType"));
            model.put("sortField", req.getParameter("sortField"));
            model.put("sortType", req.getParameter("sortType"));

            //获取本机构下的调查员
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode", "investigator");
            appendMap.put("menuCode", "assign-org-list");
            appendMap.put("btnCode", 4000);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req);
            List<SurveyInvestigatorDto> investigatorList = (List<SurveyInvestigatorDto>) apiFinalResponse.getResults();
            model.put("investigatorList", investigatorList);

            //保险公司
            //所有的委托方机构
            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType", 1); //不分页
            appendMap.put("surveyCode", "consignor");//查询“狄大人终审人员对应的委托方”，改变surveyCode值
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
            model.put("consignors", consignors);

        } else if ("extension-time".equals(menuCode)) { //延期审核
            //申请状态
            model.put("extensionState", req.getParameter("extensionState"));
        } else if ("visit-list".equals(menuCode)) { // 调查回访

        }
        return new ModelAndView("/survey/case/listAssign", model);
    }

    /**
     * 时效跟踪导出
     *
     * @param req
     * @param rsp
     */
    @RequestMapping(value = "listAssignExport")
    public void listAssignExport(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String oprType = req.getParameter("oprType");
        if ("help".equals(oprType)) {
            appendMap.put("oprTypeValue", 1);//互助
        } else {
            appendMap.put("oprTypeValue", 2);//保司
        }
        String dateItem = req.getParameter("dateItem");
        appendMap.put("dateItem", dateItem == null ? "1" : dateItem);

        String agingType = req.getParameter("agingType");
        appendMap.put("agingType", agingType == null ? "0" : agingType);

        String order = req.getParameter("order");
        if (StringUtils.isEmpty(order)) {
            order = "23";
            appendMap.put("order", order);
        }
        String colSortType = req.getParameter("colSortType");
        if (StringUtils.isEmpty(colSortType)) {
            colSortType = "1";
            appendMap.put("colSortType", colSortType);
        }
        appendMap.put("pageSize", 200);
        TypeToken<ApiFinalResponse<List<SurveyAssignOrgDto>>> typeToken = new TypeToken<ApiFinalResponse<List<SurveyAssignOrgDto>>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_ASSIGN_ORG_LIST, appendMap, req);
        List<SurveyAssignOrgDto> resultList = ecursiveResult(apiFinalResponse, typeToken, appendMap, req, new ArrayList<>(apiFinalResponse.getCount()), 0);
        timeTrackingExport(resultList, oprType, rsp);
    }

    //递归 分段处理数据
    public List<SurveyAssignOrgDto> ecursiveResult(ApiFinalResponse apiFinalResponse, TypeToken<ApiFinalResponse<List<SurveyAssignOrgDto>>> typeToken, Map<String, Object> appendMap, HttpServletRequest req, List<SurveyAssignOrgDto> temporaryList, int pageNum) {
        if (apiFinalResponse != null && apiFinalResponse.getResults() != null) {
            List<SurveyAssignOrgDto> results = new ArrayList<>((List<SurveyAssignOrgDto>) apiFinalResponse.getResults());
            temporaryList.addAll(results);
            while (pageNum * 200 < apiFinalResponse.getCount()) {
                appendMap.put("pageIndex", ++pageNum * 200);
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_ASSIGN_ORG_LIST, appendMap, req);
                return ecursiveResult(apiFinalResponse, typeToken, appendMap, req, temporaryList, pageNum);
            }
        }
        return temporaryList;
    }

    //时效跟踪导出
    public void timeTrackingExport(List<SurveyAssignOrgDto> surveyAssignOrgDtoList, String oprType, HttpServletResponse response) {
        HSSFWorkbook wb = new HSSFWorkbook();
        String fileName = "help".equals(oprType) ? "时效跟踪(互助)" : "时效跟踪(保司)";
        HSSFSheet sheet = wb.createSheet(fileName);//建立sheet对象
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        //设置头
        HSSFRow row1 = sheet.createRow(0);
        int index = 0;
        row1.createCell(0).setCellValue("案件编号");
        row1.createCell(1).setCellValue("保险公司");
        row1.createCell(2).setCellValue("被调查人");
        row1.createCell(3).setCellValue("联系方式");
        row1.createCell(4).setCellValue("业务类型");
        row1.createCell(5).setCellValue("案件阶段");
        row1.createCell(6).setCellValue("委托时间");
        row1.createCell(7).setCellValue("调查机构");
        row1.createCell(8).setCellValue("机构案件状态");
        row1.createCell(9).setCellValue("分派机构时间");
        row1.createCell(10).setCellValue("机构提交时间");
        row1.createCell(11).setCellValue("机构截止时间");
        row1.createCell(12).setCellValue("机构时效");
        if ("help".equals(oprType)) {
            row1.createCell(13).setCellValue("复审通过时间");
            row1.createCell(14).setCellValue("案件截止时间");
            row1.createCell(15).setCellValue("案件时效");
        } else if ("safe".equals(oprType)) {
            row1.createCell(13).setCellValue("案件截止时间");
            row1.createCell(14).setCellValue("案件时效");

            row1.createCell(15).setCellValue("回销时间");
            row1.createCell(16).setCellValue("案件状态");
            row1.createCell(17).setCellValue("退回记录");
            row1.createCell(18).setCellValue("延期记录");
            row1.createCell(19).setCellValue("案件沟通情况");
        }
        Iterator<Cell> srcCells = row1.cellIterator();
        while (srcCells.hasNext()) {
            //设置样式
            HSSFFont font = wb.createFont();
            font.setFontName("宋体");
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            HSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.VERTICAL_TOP); // 指定单元格居中对齐
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);// 指定单元格垂直居中对齐
            cellStyle.setWrapText(true);// 指定单元格自动换行
            HSSFCell srcCell = (HSSFCell) srcCells.next();
            srcCell.setCellStyle(cellStyle);
        }

        FastDateFormat df = FastDateFormat.getInstance("yyyy-MM-dd", TimeZone.getDefault(), Locale.getDefault());
        for (int i = 0; i < surveyAssignOrgDtoList.size(); i++) {
            SurveyAssignOrgDto dto = surveyAssignOrgDtoList.get(i);
            SurveyRiskCaseDto caseDto = dto.getSurveyRiskCase();
            HSSFRow rowi = sheet.createRow(i + 1);
            rowi.setHeightInPoints(18);//行高设置成18px
            rowi.createCell(0).setCellValue(caseDto.getSurveyCaseNo());
            rowi.createCell(1).setCellValue(caseDto.getEntrustOrgName());
            rowi.createCell(2).setCellValue(caseDto.getSurveyPerson());
            rowi.createCell(3).setCellValue(caseDto.getSurveryPersonTel());
            rowi.createCell(4).setCellValue(dto.getServicesName());
            rowi.createCell(5).setCellValue(dto.getSurveyRiskCaseInfoDto().getSupplementState() == 1 ? "信息补充中" : dto.getSurveyRiskCaseInfoDto().getSurveyStateName());
            rowi.createCell(6).setCellValue(df.format(caseDto.getEntrustTime()));
            rowi.createCell(7).setCellValue(dto.getSurveyOrgName().concat(dto.getOrgPrimaryType() == 1 ? "(主)" : ""));
            switch (dto.getOrgSurveyState().intValue()) {
                case 0:
                    rowi.createCell(8).setCellValue("待接收");
                    break;
                case 2:
                    rowi.createCell(8).setCellValue("初审中");
                    break;
                case 3:
                    rowi.createCell(8).setCellValue("已拒绝");
                    break;
                case 4:
                    if ("help".equals(oprType) && Optional.ofNullable(dto.getReview()).isPresent()) {
                        rowi.createCell(8).setCellValue("复审通过");
                        break;
                    }
                    if ("help".equals(oprType) && !Optional.ofNullable(dto.getReview()).isPresent()) {
                        rowi.createCell(8).setCellValue("复审中");
                        break;
                    }
                    rowi.createCell(8).setCellValue("初审通过");
                    break;
                case 1:
                case 5:
                case 6:
                    rowi.createCell(8).setCellValue("调查中");
                    break;
            }
            rowi.createCell(9).setCellValue(df.format(dto.getCreateTime()));
            rowi.createCell(10).setCellValue(Optional.ofNullable(dto.getReportDate()).isPresent() ? df.format(dto.getReportDate()) : "");
            rowi.createCell(11).setCellValue(df.format(dto.getOrgEndTime()));
            rowi.createCell(12).setCellValue(dto.getEfficiencyState());
            if ("help".equals(oprType)) {
                rowi.createCell(13).setCellValue(Optional.ofNullable(dto.getReviewTime()).isPresent() ? df.format(dto.getReviewTime()) : "");
                rowi.createCell(14).setCellValue(Optional.ofNullable(dto.getSurveyRiskCaseInfoDto().getEndTime()).isPresent() ? df.format(dto.getSurveyRiskCaseInfoDto().getEndTime()) : "");
                rowi.createCell(15).setCellValue(dto.getSurveyRiskCaseInfoDto().getEfficiencyState());
            } else if ("safe".equals(oprType)) {
                rowi.createCell(13).setCellValue(df.format(dto.getSurveyRiskCaseInfoDto().getEndTime()));
                rowi.createCell(14).setCellValue(dto.getSurveyRiskCaseInfoDto().getEfficiencyState());
                rowi.createCell(15).setCellValue(dto.getHxsj());
                rowi.createCell(16).setCellValue(dto.getAjzt());
                rowi.createCell(17).setCellValue(dto.getThjl());
                rowi.createCell(18).setCellValue(dto.getYqjl());
                rowi.createCell(19).setCellValue(dto.getAjgt());
            }
        }
        //输出Excel文件
        try (OutputStream output = response.getOutputStream()) {
            //设置响应头
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName.concat("-").concat(LocalDate.now().toString()), "UTF-8") + ".xls");
            response.setContentType("application/msexcel");
            wb.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "listBackCase")
    public ModelAndView listBackCase(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Serializable> model = new HashMap<String, Serializable>();
        model.put("menuCode", req.getParameter("menuCode"));
        model.put("pageSize", req.getParameter("pageSize"));
        TypeToken<ApiFinalResponse<List<SurveyBackCaseDto>>> typeToken = new TypeToken<ApiFinalResponse<List<SurveyBackCaseDto>>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_BACK_CASE_LIST, null, req);
        model.put("apiRsp", apiFinalResponse);
        model.put("surveyNo", req.getParameter("surveyNo"));//调查编号
        model.put("surveyPerson", req.getParameter("surveyPerson"));//被调查人
        model.put("surveryPersonTel", req.getParameter("surveryPersonTel"));//联系方式
        model.put("operateState", req.getParameter("operateState"));
        model.put("policyNo", req.getParameter("policyNo"));
        model.put("entrustOrgName", req.getParameter("entrustOrgName"));
        model.put("surveyCaseNo", req.getParameter("surveyCaseNo"));//案件编号
        model.put("sortField", req.getParameter("sortField"));
        model.put("sortType", req.getParameter("sortType"));
        return new ModelAndView("/survey/case/listBackCase", model);
    }


    @RequestMapping(value = "infoAssign")
    public ModelAndView infoAssign(HttpServletRequest req, HttpServletResponse rsp) {
        return null;
    }

    @RequestMapping(value = "operateAssign")
    public String operateAssign(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> param = new HashMap<>();

        String btnCode = req.getParameter("btnCode");
        if ("org5".equals(btnCode)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("surveyInfoId", req.getParameter("surveyInfoId"));
            TypeToken<ApiFinalResponse<TemplateData>> typeToken = new TypeToken<ApiFinalResponse<TemplateData>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_GET_TEMPLATE_DATA, paramMap, req);
            TemplateData data = (TemplateData) apiFinalResponse.getResults();
            SurveyModelInfoDto model = data.getModel();
            String modelPath = null;
            if ("dev".equals(surveySettingSource)) {//如果是本低环境 则模板路径 不取数据库配置路径
                modelPath = "D:\\templete";
            } else {
                modelPath = model.getModelPath();
            }
            try {
                String generateReportPath = WordUtil.generateReport(data, model.getId().intValue(), model.getModelName(), generateFilePath.concat("/").concat(data.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase()).concat("/report"), data.getReportName(), modelPath);
                if ("/mnt/sftp/files/product/ddr/cno/".equals(generateFilePath) || "/mnt/sftp/files/test/ddr/cno/".equals(generateFilePath)) {
                    if ("/mnt/sftp/files/product/ddr/cno/".equals(generateFilePath)) {
                        generateReportPath = generateReportPath.replace("/mnt/sftp/files/product/ddr/cno/", surveyFilePathSftp.concat("/product/ddr/cno/"));
                    } else if ("/mnt/sftp/files/test/ddr/cno/".equals(generateFilePath)) {
                        generateReportPath = generateReportPath.replace("/mnt/sftp/files/test/ddr/cno/", surveyFilePathSftp.concat("/test/ddr/cno/"));
                    }
                }
                param.put("generateReportPath", generateReportPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("org-help-commit".equals(btnCode) || "generateTest".equalsIgnoreCase(btnCode) || "downFileReport".equals(btnCode)) {
            //生成互助机构报告
            if ("generateTest".equals(btnCode) || "downFileReport".equals(btnCode)) {
                param.put("generateReportPath", null);
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("surveyInfoId", req.getParameter("surveyInfoId"));
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyAssignOrgDto>>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_GET_ORGS, paramMap, req);
                List<SurveyAssignOrgDto> orgs = (List<SurveyAssignOrgDto>) apiFinalResponse.getResults();
                if (orgs != null) {
                    List<String> files = new ArrayList<String>();
                    for (SurveyAssignOrgDto org : orgs) {
                        paramMap.put("surveyInfoId", org.getSurveyInfoId());
                        paramMap.put("surveyOrgId", org.getSurveyOrgId());
                        typeToken = new TypeToken<ApiFinalResponse<TemplateHelpData>>() {
                        };
                        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_GET_HELP_REPORT_DATA, paramMap, req);
                        TemplateHelpData data = (TemplateHelpData) apiFinalResponse.getResults();
                        SurveyModelInfoDto model = data.getSurveyModelInfo();
                        String modelPath = null;
                        if ("dev".equals(surveySettingSource)) {//如果是本地环境 则模板路径 不取数据库配置路径
                            modelPath = "D:\\templete";
                        } else {
                            modelPath = data.getSurveyModelInfo().getModelPath();
                        }
                        if (model.getId() == 5) {
                            File file = ExcelUtil.generateReportPoi(data, model.getModelName(), generateFilePath.concat("/").concat(data.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase()).concat("/report"), data.getReportName() + "(" + org.getSurveyOrgName() + ")", modelPath);
                            String generateReportPath = file.getPath();
                            if ("/mnt/sftp/files/product/ddr/cno/".equals(generateFilePath) || "/mnt/sftp/files/test/ddr/cno/".equals(generateFilePath)) {
                                if ("/mnt/sftp/files/product/ddr/cno/".equals(generateFilePath)) {
                                    generateReportPath = generateReportPath.replace("/mnt/sftp/files/product/ddr/cno/", surveyFilePathSftp.concat("/product/ddr/cno/"));
                                } else if ("/mnt/sftp/files/test/ddr/cno/".equals(generateFilePath)) {
                                    generateReportPath = generateReportPath.replace("/mnt/sftp/files/test/ddr/cno/", surveyFilePathSftp.concat("/test/ddr/cno/"));
                                }
                            }
                            files.add(generateReportPath);
                        } else {
                            paramMap = new HashMap<String, Object>();
                            paramMap.put("surveyInfoId", req.getParameter("surveyInfoId"));
                            typeToken = new TypeToken<ApiFinalResponse<TemplateData>>() {
                            };
                            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_GET_TEMPLATE_DATA, paramMap, req);
                            TemplateData data1 = (TemplateData) apiFinalResponse.getResults();
                            model = data1.getModel();
                            modelPath = null;
                            if ("dev".equals(surveySettingSource)) {//如果是本低环境 则模板路径 不取数据库配置路径
                                modelPath = "D:\\templete";
                            } else {
                                modelPath = model.getModelPath();
                            }
                            try {
                                String generateReportPath = WordUtil.generateReport(data1, model.getId().intValue(), model.getModelName(), generateFilePath.concat("/").concat(data.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase()).concat("/report"), data.getReportName(), modelPath);
                                if ("/mnt/sftp/files/product/ddr/cno/".equals(generateFilePath) || "/mnt/sftp/files/test/ddr/cno/".equals(generateFilePath)) {
                                    if ("/mnt/sftp/files/product/ddr/cno/".equals(generateFilePath)) {
                                        generateReportPath = generateReportPath.replace("/mnt/sftp/files/product/ddr/cno/", surveyFilePathSftp.concat("/product/ddr/cno/"));
                                    } else if ("/mnt/sftp/files/test/ddr/cno/".equals(generateFilePath)) {
                                        generateReportPath = generateReportPath.replace("/mnt/sftp/files/test/ddr/cno/", surveyFilePathSftp.concat("/test/ddr/cno/"));
                                    }
                                }
                                files.add(generateReportPath);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                    param.put("files", JsonUtil.objectToJson(files));
                }
            }
        }
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_ASSIGN_ORG_OPERATE, param, req, rsp);
    }

    @RequestMapping(value = "edit")
    public ModelAndView edit(HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyRiskCaseInfoDto>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO, null, req);
        SurveyRiskCaseInfoDto dto = (SurveyRiskCaseInfoDto) apiFinalResponse.getResults();
        dto.setMinEndDate(new Date());
        model.put("dto", dto);
        //获取领域集合（根据排序获取数据）
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("sortRule", 1);
        typeToken = new TypeToken<ApiFinalResponse<List<SurveyBusinessTypeDto>>>() {
        };
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_BUSINESS_TYPE_LIST, appendMap, req);
        model.put("buses", apiFinalResponse.getResults());
        List<SurveyBusinessTypeDto> buses = (List<SurveyBusinessTypeDto>) apiFinalResponse.getResults();
        model.put("buses", buses);

        //获取任务类型集合（根据排序获取数据）
        appendMap = new HashMap<String, Object>();
        appendMap.put("sortRule", 1);
        typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>() {
        };
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST, appendMap, req);
        List<SurveyTaskInfoDto> tasks = (List<SurveyTaskInfoDto>) apiFinalResponse.getResults();
        model.put("tasks", tasks);

        //获取业务类型集合（根据排序获取数据）
        appendMap = new HashMap<String, Object>();
        appendMap.put("sortRule", 1);
        typeToken = new TypeToken<ApiFinalResponse<List<SurveyServiceTypeDto>>>() {
        };
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SERVICE_TYPE_LIST, appendMap, req);
        List<SurveyServiceTypeDto> services = (List<SurveyServiceTypeDto>) apiFinalResponse.getResults();
        model.put("services", services);

        //获取材料列表
        model.put("id", dto.getId());
        typeToken = new TypeToken<ApiFinalResponse<Map>>() {
        };
        appendMap = new HashMap<>();
        appendMap.put("selectType", "new");
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_FILE_LIST, appendMap, req);
        Map map = (Map) apiFinalResponse.getResults();
        model.put("fileCatalogs", map.get("fileCatalogs"));
        model.put("surveyCaseFiles", map.get("surveyCaseFiles"));

        //初始化数据
        Map params = new HashMap();

        String obj = req.getParameter("obj");
//        if ("agent".equals(obj)){
        //获取保险公司列表
        typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
        };
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CONSIGNOR_LIST, null, req);
        List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
        model.put("consignors", consignors);
        model.put("obj", obj);
        params.put("consignorJson", JsonUtil.objectToJson(consignors));
//        }
        params.put("dtoJson", JsonUtil.objectToJson(dto));
        params.put("busesJson", JsonUtil.objectToJson(buses));
        params.put("tasksJson", JsonUtil.objectToJson(tasks));
        params.put("servicesJson", JsonUtil.objectToJson(services));
        model.put("params", params);
        //发起二调
        String btnCode = req.getParameter("btnCode");
        if ("to-lefan-survey".equals(btnCode)) {
            model.put("obj", "agent");
            model.put("btnCode", btnCode);
            model.put("finaInfoId", req.getParameter("finaInfoId"));
        }
        if ("transfer".equals(btnCode)) {
            model.put("btnCode", btnCode);
            dto.setId(null);//id为空
            model.put("dto", dto);

            //下一个“二调”的类型
            String nextTransferType = req.getParameter("nextTransferType");
            model.put("nextTransferType", nextTransferType);

            //“发起二调”的顶级 调查id
            String topSurveyId = req.getParameter("topSurveyId");
            model.put("topSurveyId", topSurveyId);

            String showTransfer = req.getParameter("showTransfer");
            model.put("showTransfer", showTransfer);
//            return new ModelAndView("/survey/case/editInfo",model);
        }
        return new ModelAndView("/survey/case/editNew", model);
    }

    @RequestMapping(value = "save")
    public String save(HttpServletRequest req, HttpServletResponse rsp) {
        String menuCode = req.getParameter("menuCode");
        Map<String, Object> param = new HashMap<>();
        if (StringUtils.isNotBlank(menuCode)) {
            if ("batchKp".equals(menuCode)) {
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_LIST_UPD_BATCH_KP, param, req, rsp);
            } else if ("markList".equals(menuCode)) {
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_LIST_UPD_MARKLIST_ENTRUSTMONEY, param, req, rsp);
            }
        }
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_ENTRUST, param, req, rsp);
    }

    /**
     * 保司代理委托获取截止时间  出去周末 和节假日
     *
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "getDeadline")
    @ResponseBody
    public String getDeadline(HttpServletRequest req, HttpServletResponse rsp) throws ParseException {
        JSONObject obj = new JSONObject();
        if (StringUtils.isNotBlank(req.getParameter("assignType"))) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            String btnCode = req.getParameter("btnCode");
            String serviceId = req.getParameter("serviceId");
            String surveyOrgId = req.getParameter("surveyOrgId");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyRiskCaseInfoDto>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO, null, req);
            SurveyRiskCaseInfoDto dto = (SurveyRiskCaseInfoDto) apiFinalResponse.getResults();
            dto.setServicesId(Long.valueOf(serviceId));
            Date date = selectMaxDate(dto, btnCode, surveyOrgId);
            obj.put("data", simpleDateFormat.format(date));
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String startTime = StringUtils.isNotBlank(req.getParameter("startTime")) ? req.getParameter("startTime") : LocalDate.now().toString();
            TypeToken typeToken = new TypeToken<ApiFinalResponse<HashMap<String, Integer>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_ZA_AGING_DAY, null, req);
            Map<String, Integer> results = (HashMap<String, Integer>) apiFinalResponse.getResults();
            Integer days = 0;
            if (results != null && results.get("days") != null) {
                days = results.get("days");
            }
            Integer efficiencyAttr = (results == null ? 1 : results.get("efficiencyAttr"));
            Date endTime = GetWorkDay.calLeaveEndDate(simpleDateFormat.parse(startTime), null, days, efficiencyAttr);
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            obj.put("data", simpleDateFormat.format(endTime));
        }
        return JSON.toJSONString(obj);
    }

    @RequestMapping(value = "info")
    public ModelAndView info(HttpServletRequest req, HttpServletResponse rsp) {
        String menuCode = req.getParameter("menuCode");
        String fromName = req.getParameter("fromName");
        String oprType = req.getParameter("oprType");
        String showTransfer = req.getParameter("showTransfer");//二调
        Map model = new HashMap();
        UserInfo curUser = this.getSessionAdmin(req);
        if (curUser != null) {
            if (curUser.getUserId().intValue() == 2189) {
                model.put("maxRole", true);
            }
        }


        model.put("display", req.getParameter("display"));//是否显示打卡足迹按钮
        model.put("oprType", oprType);
        model.put("fromName", fromName);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyRiskCaseInfoDto>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO, null, req);
        SurveyRiskCaseInfoDto dto = (SurveyRiskCaseInfoDto) apiFinalResponse.getResults();
        dto.setMinEndDate(new Date());
        for (SurveyCaseDirectionDto surveyCaseDirection : dto.getSurveyCaseDirections()) {
            try {
                //新的方向附件 没有 任务类型文件夹
                String folder = "/mnt/sftp/files/" + surveyFilePathSftpSource + "/ddr/cno/" + dto.getSurveyCno().toLowerCase() + "/" + "direction" + "/" + surveyCaseDirection.getDirectionName();
                int size = 0;
                File file = new File(folder);
                if (!file.exists()) {//如果不存在。则进入任务类型文件夹
                    folder = "/mnt/sftp/files/" + surveyFilePathSftpSource + "/ddr/cno/" + dto.getSurveyCno().toLowerCase() + "/" + "direction" + "/" + surveyCaseDirection.getTaskName() + "/" + surveyCaseDirection.getDirectionName();
                    file = new File(folder);
                }
                if (file.exists()) {
                    if (file.isDirectory()) {
                        size = file.listFiles().length;
                    }
                }
                surveyCaseDirection.setDirectionFilesSize(size);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.put("dto", dto);
        model.put("menuCode", menuCode);

        model.put("showNwPageSyncBtn", true);
        model.put("showNwPageEdit", true);
        if (dto.getEntrustOrgId() == 94) {
            Long user = getSessionAdminId(req);
            String sign = Md5Util.encodeString(nuanWaCompany + user + nuanWaSecret);
            model.put("nuanWaUser", user);
            model.put("nuanWaSign", sign);
            model.put("showNwPage", true);
            model.put("nuanWaCompany", nuanWaCompany);
            if (dto.getHandleId() != null) {
                model.put("showNwPageEdit", false);
                model.put("handleId", dto.getHandleId());
            }
        } else {
            model.put("showNwPage", false);
        }

        //案件指导信息
        typeToken = new TypeToken<ApiFinalResponse<SurveyRiskCaseGuideDto>>() {
        };
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_RISK_CASE_GUIDE_INFO, null, req);
        SurveyRiskCaseGuideDto guideDto = (SurveyRiskCaseGuideDto) apiFinalResponse.getResults();
        model.put("guideDto", guideDto);


        if ("agent-entrust-list".equals(menuCode)) {
            model.put("menuCode", "survey-list");//如果是代理保司审核的详情 则看到的信息 和  调查的信息一样
            model.put("menuCode2", "agent-entrust-list");//代理保司审核特殊标志
        }
        if ("all-list".equals(menuCode)) {//客服列表  补充中的调转至编辑界面
            //获取任务类型集合
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap = new HashMap<String, Object>();
            appendMap.put("sortRule", 1);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST, appendMap, req);
            List<SurveyTaskInfoDto> tasks = (List<SurveyTaskInfoDto>) apiFinalResponse.getResults();
            model.put("tasks", tasks);

            if (dto.getSupplementState() == 1) {
                appendMap.put("sortRule", 1);
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyBusinessTypeDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_BUSINESS_TYPE_LIST, appendMap, req);
                List<SurveyBusinessTypeDto> buses = (List<SurveyBusinessTypeDto>) apiFinalResponse.getResults();
                model.put("buses", buses);

                model.put("oprType", "1");
                //获取业务类型集合
                appendMap = new HashMap<String, Object>();
                appendMap.put("sortRule", 1);
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyServiceTypeDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SERVICE_TYPE_LIST, appendMap, req);
                List<SurveyServiceTypeDto> services = (List<SurveyServiceTypeDto>) apiFinalResponse.getResults();
                model.put("services", services);

                //获取材料列表
                model.put("id", dto.getId());
                typeToken = new TypeToken<ApiFinalResponse<Map>>() {
                };
                appendMap = new HashMap<>();
                appendMap.put("selectType", "new");
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_FILE_LIST, appendMap, req);
                Map map = (Map) apiFinalResponse.getResults();
                model.put("fileCatalogs", map.get("fileCatalogs"));
                model.put("surveyCaseFiles", map.get("surveyCaseFiles"));

                typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CONSIGNOR_LIST, null, req);
                List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
                model.put("consignors", consignors);

                //初始化数据
                Map params = new HashMap();
                params.put("dtoJson", JsonUtil.objectToJson(dto));
                params.put("busesJson", JsonUtil.objectToJson(buses));
                params.put("tasksJson", JsonUtil.objectToJson(tasks));
                params.put("servicesJson", JsonUtil.objectToJson(services));
                params.put("consignorJson", JsonUtil.objectToJson(consignors));
                model.put("params", params);

//                return new ModelAndView("/survey/case/edit",model);
                return new ModelAndView("/survey/case/editNew", model);
            }
        }
        //委托清单，受理不通过的案件，有两个info入口：1、list页面；2、新增委托时，身份证管理出来的数据-详情（此时showTransfer==1）
        if ((dto.getSurveyState() == 6 && "my-list".equals(menuCode) && showTransfer == null) || ("dcy-list".equals(menuCode))) {//案件清单 审核不通过 详情跳转至编辑界面
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("sortRule", 1);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyBusinessTypeDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_BUSINESS_TYPE_LIST, appendMap, req);
            List<SurveyBusinessTypeDto> buses = (List<SurveyBusinessTypeDto>) apiFinalResponse.getResults();
            model.put("buses", buses);

            //获取任务类型集合
            appendMap = new HashMap<String, Object>();
            appendMap.put("sortRule", 1);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASK_INFO_LIST, appendMap, req);
            List<SurveyTaskInfoDto> tasks = (List<SurveyTaskInfoDto>) apiFinalResponse.getResults();
            model.put("tasks", tasks);
            model.put("oprType", "2");

            //获取业务类型集合
            appendMap = new HashMap<String, Object>();
            appendMap.put("sortRule", 1);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyServiceTypeDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SERVICE_TYPE_LIST, appendMap, req);
            List<SurveyServiceTypeDto> services = (List<SurveyServiceTypeDto>) apiFinalResponse.getResults();
            model.put("services", services);

            //获取材料列表
            model.put("id", dto.getId());
            typeToken = new TypeToken<ApiFinalResponse<Map>>() {
            };
            appendMap = new HashMap<>();
            appendMap.put("selectType", "new");
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_FILE_LIST, appendMap, req);
            Map map = (Map) apiFinalResponse.getResults();
            model.put("fileCatalogs", map.get("fileCatalogs"));
            model.put("surveyCaseFiles", map.get("surveyCaseFiles"));

            typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CONSIGNOR_LIST, null, req);
            List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
            model.put("consignors", consignors);

            //初始化数据
            Map params = new HashMap();
            params.put("dtoJson", JsonUtil.objectToJson(dto));
            params.put("busesJson", JsonUtil.objectToJson(buses));
            params.put("tasksJson", JsonUtil.objectToJson(tasks));
            params.put("servicesJson", JsonUtil.objectToJson(services));
            params.put("consignorJson", JsonUtil.objectToJson(consignors));
            model.put("params", params);

            if ("dcy-list".equals(menuCode) || ("my-list".equals(menuCode) && dto.getSurveyState() != 6)) {
                String btnCode = req.getParameter("btnCode");
                model.put("btnCode", btnCode);
            }

            //获取调查员案件是否已提交
            typeToken = new TypeToken<ApiFinalResponse<SurveyInvestigatorCaseDto>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INFO_SURVER_INVESTIGATOR_SELECTONE, null, req);
            SurveyInvestigatorCaseDto surveyInvestigatorCaseDto = (SurveyInvestigatorCaseDto) apiFinalResponse.getResults();
            model.put("surveyInvestigatorCaseDto", surveyInvestigatorCaseDto);
            return new ModelAndView("/survey/case/editNew", model);
        }
        typeToken = new TypeToken<ApiFinalResponse<List<SurveyFollowDto>>>() {
        };
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("surveyInfoId", dto.getId());
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST_FOLLOW, param, req);
        model.put("follows", apiFinalResponse.getResults());
        if ("account-list".equals(menuCode)) { //对账清单
//            return new ModelAndView("/survey/case/accountInfo", model);
            return new ModelAndView("/survey/case/info", model);

        } else if ("my-list".equals(menuCode)) { //二调：委托清单，新增时的操作，关闭多个窗体
            model.put("showTransfer", showTransfer);
        } else if ("assign-org-list".equals(menuCode)) {
            model.put("assignOrgId", req.getParameter("assignOrgId"));
            model.put("curSurveyOrgId", req.getParameter("curSurveyOrgId"));

            //当前登录人角色scoreRole是：provincialManger 省级机构负责人，areaManger 片区机构负责人，manger 平台人员
            typeToken = new TypeToken<ApiFinalResponse<Map>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.GET_DATA_SURVEY_HZ_REPORT_ROLE, null, req);
            Map map = (Map) apiFinalResponse.getResults();
            model.put("scoreRole", map.get("scoreRole"));
            model.put("dataRoleCode", map.get("dataRoleCode"));
        } else if ("survey-list".equals(menuCode)) {
            model.put("currentDate", new Date());
            model.put("minDate", dto.getSurveyRiskCase().getEntrustTime());
            model.put("maxDate", new Date());
            model.put("showNwPageSyncBtn", false);
        } else if ("assign-list".equals(menuCode)) {
            //使用场景：“代理委托”录入案件时，可直接“分派调查员”
            model.put("autoOpenInfo", req.getParameter("autoOpenInfo"));//是否主动开页面
            model.put("auto", req.getParameter("auto"));//页面关闭--“X”按钮
        } else if ("visit-list".equals(menuCode)) {
            //调查回访信息
            typeToken = new TypeToken<ApiFinalResponse<SurveyRiskCaseVisitDto>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_RISK_CASE_VISIT_INFO, null, req);
            SurveyRiskCaseVisitDto visitDto = (SurveyRiskCaseVisitDto) apiFinalResponse.getResults();
            model.put("visitDto", visitDto);
            model.put("surveyAssorgCaseId", req.getParameter("surveyAssorgCaseId")); //机构案件id
        } else if ("feeViewSurvey".equals(menuCode)) {//费用报销查看 案件详情

        } else if ("feeViewManager".equals(menuCode)) {//费用报销查看

        }

        typeToken = new TypeToken<ApiFinalResponse<UserInfo>>() {
        };
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_USER_INFO, null, req);
        UserInfo userInfo = (UserInfo) apiFinalResponse.getResults();
        model.put("userInfo", userInfo);

        //控制页面一个div展示，场景：绩效管理-案件详情
        String showFrom = req.getParameter("showFrom");
        String btnCode = req.getParameter("btnCode");
        if ("staff".equals(showFrom) && "case".equals(btnCode)) {
            List<SurveyInvestigatorCaseDto> surveyInvestigatorCase = dto.getSurveyInvestigatorCases();
            Long investigatorCaseIdS = Long.valueOf(req.getParameter("investigatorCaseIdS"));
            for (SurveyInvestigatorCaseDto surveyInvestigatorCaseDto : surveyInvestigatorCase) {
                if (surveyInvestigatorCaseDto.getId().equals(investigatorCaseIdS)) {
                    if (surveyInvestigatorCaseDto.getSurveyInvestigatorCaseSub() != null) {
                        model.put("staffOpinionState", surveyInvestigatorCaseDto.getSurveyInvestigatorCaseSub().getStaffOpinionState());
                    }
                }

            }
            model.put("showFrom", showFrom);
            model.put("surveyUserName", req.getParameter("surveyUserName"));
            model.put("staffOpinion", req.getParameter("staffOpinion"));
            model.put("investigatorCaseIdS", req.getParameter("investigatorCaseIdS"));
            model.put("roleCode", req.getParameter("roleCode"));
        }

        if (dto != null && dto.getSurveyAssignOrgs() != null && dto.getSurveyAssignOrgs().size() > 0) {
            model.put("selOrgJson", JsonUtil.objectToJson(dto.getSurveyAssignOrgs()));
        }


        typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {
        };
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CURRENT_USER_ROLE_LIST, null, req);
        List<BusUserRoleDto> userRoles = (List<BusUserRoleDto>) apiFinalResponse.getResults();
        Boolean scoreUpd = false;
        for (BusUserRoleDto userRole : userRoles) {
            if (userRole.getRoleId().intValue() == 142) {
                scoreUpd = true;
                break;
            }
        }
        model.put("scoreUpd", scoreUpd);
        return new ModelAndView("/survey/case/info", model);
    }

    @RequestMapping(value = "operate")
    public String operate(HttpServletRequest req, HttpServletResponse rsp) {
        String btnCode = req.getParameter("btnCode");
        Map<String, Object> param = new HashMap<>();
        if ("generateReport".equals(btnCode) || "1300".equals(btnCode)) {//测试生成报告 或 终审通过
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("surveyInfoId", req.getParameter("id"));
            TypeToken<ApiFinalResponse<TemplateData>> typeToken = new TypeToken<ApiFinalResponse<TemplateData>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_GET_TEMPLATE_DATA, paramMap, req);
            TemplateData data = (TemplateData) apiFinalResponse.getResults();
            SurveyModelInfoDto model = data.getModel();
            String modelPath = null;
            if ("dev".equals(surveySettingSource)) { //如果是本低环境 则模板路径 不取数据库配置路径
                modelPath = "D:\\templete";
            } else {
                modelPath = model.getModelPath();
            }
            try {
                //因为会存在无用的“附件”（如新增方向的时候，先上传附件，却未最终提交，会导致上述附件为冗余附件，故删除）
                deleteNotFindDirectionName(data);

                String generateReportPath = WordUtil.generateReport(data, model.getId().intValue(), model.getModelName(), generateFilePath.concat("/").concat(data.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase()).concat("/report"), data.getReportName(), modelPath);
                if ("/mnt/sftp/files/product/ddr/cno/".equals(generateFilePath) || "/mnt/sftp/files/test/ddr/cno/".equals(generateFilePath)) {
                    if ("/mnt/sftp/files/product/ddr/cno/".equals(generateFilePath)) {
                        generateReportPath = generateReportPath.replace("/mnt/sftp/files/product/ddr/cno/", surveyFilePathSftp.concat("/product/ddr/cno/"));
                    } else if ("/mnt/sftp/files/test/ddr/cno/".equals(generateFilePath)) {
                        generateReportPath = generateReportPath.replace("/mnt/sftp/files/test/ddr/cno/", surveyFilePathSftp.concat("/test/ddr/cno/"));
                    }
                }
                param.put("generateReportPath", generateReportPath);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //处理二调的数据
            if ("generateReport".equals(btnCode)) {
                List<SurveyRiskCaseInfoDto> riskCaseInfoDtos = data.getRiskCaseInfos();//二调前的案件
                if (riskCaseInfoDtos != null && riskCaseInfoDtos.size() > 0) {
                    String path = downTransfer(data);
                    param.put("path", path.replace("/mnt/sftp/files", surveyFilePathSftp));
                    param.put("btnCode", "downTransfer");
                }
            }

        } else if ("updateTaskType".equals(btnCode)) { //修改任务类型（获取数据）
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_TASK_BY_USER_ID, param, req, rsp);
        } else if ("updateIsSun".equals(btnCode)) {//初审修改调查员是否阳性
            return this.callApiAndOutput(BackendApiMethodEnum.INFO_SURVEY_INVESTIGATOR_UPDATE_ISSUN, null, req, rsp);
        } else if ("sendEmail".equals(btnCode)) {//发送邮件
            String surveyCaseNo = req.getParameter("surveyCaseNo");
            String emailContent = req.getParameter("emailContent");
            String fromEmailAddress = req.getParameter("fromEmailAddress");
            String fromUserName = req.getParameter("fromUserName");
            String fromPwd = req.getParameter("fromPwd");
            String toEmailAddress = req.getParameter("toEmailAddress");
            String[] makeEmails = req.getParameter("makeEmail").split(";");
            String filesJSON = req.getParameter("filesJSON");
            Map<String, Object> reqParamMap = new HashMap<String, Object>();
            if (!StringUtils.isEmpty(filesJSON)) {
                List<SurveyFileDTO> files = JSONArray.parseArray(filesJSON, SurveyFileDTO.class);
                for (SurveyFileDTO file : files) {
                    Boolean send = SendMailUtil.sendEmail(new File(file.getFilePath()), surveyCaseNo, emailContent, fromEmailAddress, fromUserName, fromPwd, toEmailAddress, makeEmails);
                    if (!send) {
                        reqParamMap.put("msg", "发送失败，密码错误或其他原因。");
                        break;
                    } else {
                        reqParamMap.put("msg", "发送成功。");
                    }
                    reqParamMap.put("isSuccess", send);
                }
            }
            return this.outputJson(JsonUtil.objectToJson(reqParamMap, Map.class), rsp);
//            TypeToken<ApiFinalResponse<SurveyEmailInfoDTO>> typeToken = new TypeToken<ApiFinalResponse<SurveyEmailInfoDTO>>(){};
//            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_GET_ENTRUST_END_INFO,null,req);
//            SurveyEmailInfoDTO emailInfo = (SurveyEmailInfoDTO)apiFinalResponse.getResults();
//
//            System.out.println("ABCDEFG");
//            System.out.println(emailInfo.toString());
//            //重新生成附件
//            Map<String,Object> paramMap =  new HashMap<String,Object>();
//            paramMap.put("surveyInfoId",req.getParameter("id"));
//            TypeToken<ApiFinalResponse<TemplateData>> typeToken1 = new TypeToken<ApiFinalResponse<TemplateData>>(){};
//            apiFinalResponse= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_SURVEY_GET_TEMPLATE_DATA, paramMap, req);
//            TemplateData data = (TemplateData)apiFinalResponse.getResults();
//            SurveyModelInfoDto model = data.getModel();
//            String modelPath = null;
//            if ("dev".equals(surveySettingSource)){//如果是本低环境 则模板路径 不取数据库配置路径
//                modelPath = "D:\\templete";
//                Map<String,Object> reqParamMap =  new HashMap<String,Object>();
//                reqParamMap.put("isSuccess",true);
//                reqParamMap.put("msg","发送成功");
//                Boolean send = SendMailUtil.sendEmail("测试案件编号",emailInfo, new File("F:\\新建文件夹\\测试附件.txt"));
//                return this.outputJson(JsonUtil.objectToJson(reqParamMap, Map.class),rsp);
//            }else{
//                modelPath = model.getModelPath();
//            }
//            try {
//                String reportPath = WordUtil.generateReport(data, model.getId().intValue(), model.getModelName(), generateFilePath.concat("/").concat(data.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase()).concat("/report"), data.getReportName(),modelPath);
//                System.out.println("reportPath" + reportPath);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//            Map<String,Object> reqParamMap =  new HashMap<String,Object>();
//            reqParamMap.put("isSuccess",false);
//            reqParamMap.put("msg","压缩文件异常");
//            SurveyRiskCaseInfoDto surveyRiskCaseInfo = data.getSurveyRiskCaseInfo();
//            SurveyRiskCaseDto surveyRiskCase = surveyRiskCaseInfo.getSurveyRiskCase();
//
//            //压缩方向附件+附件
//            System.out.println("开始压缩");
//            Map zipMap = SurveyZipUtil.sftpZip(surveyRiskCaseInfo.getSurveyCno().toLowerCase(),surveySettingSource,surveyRiskCase.getSurveyCaseNo());
//            System.out.println("结束压缩");
//            System.out.println("zipMap" + zipMap);
//            if (zipMap != null){
//                if (zipMap.containsKey("path")){
//                    System.out.println(zipMap.get("path").toString());
//                    File file = new File(zipMap.get("path").toString());
//                    if (file.exists()){
//                        long size = file.length() / 1024 / 1024;
//                        System.out.println("size" + size);
//                        if (size >= 50){
//                            reqParamMap.put("isSuccess",false);
//                            reqParamMap.put("msg","文件大于50兆无法发送。");
//                        }else{
//                            Boolean send = SendMailUtil.sendEmail(surveyRiskCase.getSurveyCaseNo(),emailInfo,file);
//                            if (!send){
//                                reqParamMap.put("msg","发送失败，密码错误或其他原因。");
//                            }
//                            reqParamMap.put("msg","发送成功。");
//                            reqParamMap.put("isSuccess",send);
//                        }
//                    }
//                }
//            }
        } else if ("everyCaseRemind".equals(btnCode)) {
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_REMIND_OPREATE, param, req, rsp);
        } else if ("reass".equals(btnCode)) {//获取案件数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_LIST, param, req, rsp);
        } else if ("extensionSendEmail".equals(btnCode)) {//发送邮件
            String surveyCaseNo = req.getParameter("surveyCaseNo");
            String emailContent = req.getParameter("emailContent");
            String fromEmailAddress = req.getParameter("fromEmailAddress");
            String fromUserName = req.getParameter("fromUserName");
            String fromPwd = req.getParameter("fromPwd");
            String toEmailAddress = req.getParameter("toEmailAddress");
            String[] makeEmails = req.getParameter("makeEmail").split(";");
            String filesZip = req.getParameter("filesZip");
            Map<String, Object> reqParamMap = new HashMap<String, Object>();

            File file = new File(filesZip);
            if (!file.exists()) {//如果不存在
                file = null;
            }
            Boolean send = SendMailUtil.sendEmail(file, surveyCaseNo, emailContent, fromEmailAddress, fromUserName, fromPwd, toEmailAddress, makeEmails);
            if (!send) {
                reqParamMap.put("msg", "发送失败，密码错误或其他原因。");
            } else {
                reqParamMap.put("msg", "发送成功。");
            }
            reqParamMap.put("isSuccess", send);


            return this.outputJson(JsonUtil.objectToJson(reqParamMap, Map.class), rsp);
        }

        String directionId = req.getParameter("directionId");
        String updCode = req.getParameter("updCode");
        if ("updInfoStr".equals(btnCode) && !StringUtils.isEmpty(directionId) && "upd-direction-name".equals(updCode)) {//修改方向名称
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("directionId", directionId);
            TypeToken<ApiFinalResponse<SurveyCaseDirectionDto>> typeToken = new TypeToken<ApiFinalResponse<SurveyCaseDirectionDto>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_DIRECTION_INFO_BY_DIRECTION_ID, paramMap, req);
            SurveyCaseDirectionDto direction = (SurveyCaseDirectionDto) apiFinalResponse.getResults();
            String updValue = req.getParameter("updValue");
            String surveyCno = direction.getSurveyCno().toLowerCase();
            if (!updValue.equals(direction.getDirectionName())) {
                String uploadFolder = directionFilePath + surveyCno + File.separator + "direction" + File.separator + direction.getDirectionName();
                String newFolder = directionFilePath + surveyCno + File.separator + "direction" + File.separator + updValue;
                try {
                    if (!uploadFolder.equals(newFolder)) {
                        FileUtils.copyFolder(uploadFolder, newFolder);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_OPERATE, param, req, rsp);
    }

    /**
     * 保司终审相互宝案件批量处理上传
     *
     * @param file
     * @param req
     * @return
     */
    @RequestMapping(value = "xhbCaseBatchUpload")
    @ResponseBody
    public String xhbCaseBatchUpload(MultipartFile file, HttpServletRequest req) {
        JSONObject resultJSON = new JSONObject();
        String exportType = req.getParameter("exportType");
        if ("ddDataSettlement".equals(exportType)) {//相互宝结算并开票 解析Excel
            //读取IO流文件
            try (InputStream input = file.getInputStream()) {
                XSSFWorkbook wb = new XSSFWorkbook(input);
                List<SurveyCaseXHB> xhbCaseList = new ArrayList<>();
                //读取页
//                for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {
                XSSFSheet xssfSheet = wb.getSheetAt(0);
                if (xssfSheet != null) {
                    //读取行
                    for (int rowNum = 4; rowNum < xssfSheet.getLastRowNum() + 1; rowNum++) {
                        XSSFRow row = xssfSheet.getRow(rowNum);
                        if (row == null) continue;
                        SurveyCaseXHB xhb = new SurveyCaseXHB();
                        //首先强制设置成string类型
                        row.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
                        xhb.setClaimsNo(row.getCell(0).getStringCellValue().trim());

                        row.getCell(7).setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        xhb.setSettlementMoney(row.getCell(7).getNumericCellValue());
                        xhbCaseList.add(xhb);
                    }
                }
//                }
                if (xhbCaseList.size() == 0) {
                    resultJSON.put("isSuccess", false);
                    resultJSON.put("msg", "无数据或导入数据有误");
                    return resultJSON.toJSONString();
                }
                Map appendMap = new HashMap();
                appendMap.put("caseList", JSONArray.toJSONString(xhbCaseList));
                appendMap.put("exportType", exportType);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<JSONObject>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_XHBBATCH_CASE_LIST, appendMap, req);
                resultJSON = (JSONObject) apiFinalResponse.getResults();
                resultJSON.put("isSuccess", true);
                return resultJSON.toJSONString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            resultJSON.put("isSuccess", false);
            resultJSON.put("msg", "系统出错");
        } else {
            //读取IO流文件
            try (InputStream input = file.getInputStream()) {
                XSSFWorkbook wb = new XSSFWorkbook(input);
                List<String> xhbCaseList = new ArrayList<>();
                //读取页
                for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {
                    XSSFSheet xssfSheet = wb.getSheetAt(sheetNum);
                    if (xssfSheet == null) {
                        continue;
                    }
                    //读取行
                    for (int rowNum = 1; rowNum < xssfSheet.getLastRowNum() + 1; rowNum++) {
                        XSSFRow row = xssfSheet.getRow(rowNum);
                        if (row == null) continue;
                        for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                            //首先强制设置成string类型
                            row.getCell(cellNum).setCellType(XSSFCell.CELL_TYPE_STRING);
                            String rowContent = row.getCell(cellNum).getStringCellValue().trim();
                            xhbCaseList.add(rowContent);
                        }
                    }
                }
                if (xhbCaseList.isEmpty()) {
                    resultJSON.put("isSuccess", false);
                    resultJSON.put("msg", "导入数据有误");
                    return resultJSON.toJSONString();
                }
                Map appendMap = new HashMap();
                appendMap.put("caseList", StringUtils.strip(xhbCaseList.toString(), "[]"));
                TypeToken typeToken = new TypeToken<ApiFinalResponse<JSONObject>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_XHBBATCH_CASE_LIST, appendMap, req);
                resultJSON = (JSONObject) apiFinalResponse.getResults();
                resultJSON.put("isSuccess", true);
                return resultJSON.toJSONString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            resultJSON.put("isSuccess", false);
            resultJSON.put("msg", "系统出错");
        }
        return resultJSON.toJSONString();
    }

    @RequestMapping("/xhbCaseBatch")
    @ResponseBody
    public String xhbCaseBatch(@RequestBody String json, HttpServletRequest req, HttpServletResponse rsp) {
        JSONObject resultJSON = new JSONObject();
        JSONObject jsonObject = JSON.parseObject(json);
        String type = jsonObject.get("type") == null ? "" : jsonObject.get("type").toString();
        if ("settlement".equals(type)) {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<String>>() {
            };
            HashMap param = new HashMap();
            param.put("successData", jsonObject.get("successData").toString());
            this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_RISK_INFO_XHB_CASE, param, req);
            resultJSON.put("isSuccess", true);
        } else {
            try {
                List<String> allData = (List<String>) jsonObject.get("caseData");
                HashMap param = new HashMap();
                param.put("passTime", jsonObject.getString("passtime"));
                int size = 50;
                TypeToken typeToken = new TypeToken<ApiFinalResponse<String>>() {
                };
                for (int begin = 0; begin < allData.size(); begin = begin + size) {
                    int end = Math.min(begin + size, allData.size());
                    List<String> subList = allData.subList(begin, end);
                    param.put("caseData", StringUtils.strip(subList.toString(), "[]"));
                    this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_XHBBATCH_CASE_OPERATOR, param, req);
                }
            } catch (Exception e) {
                e.printStackTrace();
                resultJSON.put("isSuccess", false);
                return resultJSON.toJSONString();
            }
            resultJSON.put("isSuccess", true);
        }
        return resultJSON.toJSONString();
    }

    @RequestMapping(value = "back")
    public ModelAndView back(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> model = new HashMap<String, Object>();
        String btnCode = req.getParameter("btnCode");
        model.put("id", req.getParameter("id"));
        model.put("btnCode", btnCode);
        model.put("backCaseId", req.getParameter("backCaseId"));
        if ("1301".equals(btnCode)) {
            TypeToken<ApiFinalResponse<SurveyRiskCaseInfoDto>> typeToken = new TypeToken<ApiFinalResponse<SurveyRiskCaseInfoDto>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO, null, req);
            SurveyRiskCaseInfoDto dto = (SurveyRiskCaseInfoDto) apiFinalResponse.getResults();
            model.put("orgs", dto.getSurveyAssignOrgs());
        }
        if ("fastReply".equals(btnCode)) {
            TypeToken<ApiFinalResponse<List<SurveyAssignOrgDto>>> typeToken = new TypeToken<ApiFinalResponse<List<SurveyAssignOrgDto>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_ORG_CASE_REMIND_LIST, null, req);
            model.put("results", apiFinalResponse.getResults());
            return new ModelAndView("/survey/case/caseRemind", model);
        }
        return new ModelAndView("/survey/case/back", model);
    }

    @RequestMapping(value = "fileMid")
    public String fileMid(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> param = new HashMap<>();
        param.put("surveyId", req.getParameter("surveyId"));
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_FILE_LIST, param, req, rsp);
    }

    @RequestMapping(value = "fileMidView")
    public ModelAndView fileMidView(HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        TypeToken<ApiFinalResponse<Map>> typeToken = new TypeToken<ApiFinalResponse<Map>>() {
        };
        Map<String, Object> params = new HashMap<String, Object>();
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_FILE_LIST, params, req);
        Map map = (Map) apiFinalResponse.getResults();
        model.put("fileCatalogs", map.get("fileCatalogs"));
        model.put("surveyCaseFiles", map.get("surveyCaseFiles"));
        model.put("surveyId", req.getParameter("surveyId"));
        model.put("surveyInfoId", req.getParameter("surveyInfoId"));
        model.put("surveyCno", req.getParameter("surveyCno"));
        String index = req.getParameter("index");
        req.setAttribute("id", index);
        //狄大人 -- 附件详情
        if ("direction".equals(req.getParameter("type"))) {
            model.put("surveyCaseFiles", map.get("directionFile"));
        }
        if (index != null) {
            return new ModelAndView("/survey/case/fileMidShow", model);
        } else {
            return new ModelAndView("/survey/case/fileMidView", model);
        }
    }

    @RequestMapping(value = "fileMidOK")
    public String fileMidOK(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> param = new HashMap<>();
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_FILE_UPLOAD, param, req, rsp);
    }

    @RequestMapping(value = "assignSurveyUser")
    public ModelAndView assignSurveyUser(HttpServletRequest req, HttpServletResponse rsp) {
        String btnCode = req.getParameter("btnCode");
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyRiskCaseInfoDto>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO, null, req);
        SurveyRiskCaseInfoDto dto = (SurveyRiskCaseInfoDto) apiFinalResponse.getResults();
        model.put("dto", dto);
        Map<String, Object> param = new HashMap<String, Object>();
        String clientType = dto.getSurveyConsignor().getOrgAttr().toString();

        String surveyOrgId = null;
        if ("111".equals(btnCode) || "113".equals(btnCode) || "115".equals(btnCode) || "116".equals(btnCode)) {
            //111是乐凡直接分配调查员 查看的是所有调查员
            //113是机构经理分派调查员  查看的是该机构的调查员
            param.put("btnCode", btnCode);

            if ("111".equals(btnCode) || "115".equals(btnCode)) {
                model.put("taskRemark", req.getParameter("taskRemark"));
                param.put("surveyCode", "franchisee");
                param.put("menuType", 1);//不分页
                param.put("clientType", clientType);
                param.put("enable", 0);//查询启用状态的调查方机构
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, param, req);
                List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
                UserInfo adminSession = this.getSessionAdmin(req);
                Long userId = adminSession.getUserId();
                if (userId.intValue() == 1053) {//2021年10月28日  如果是曹天安的账号只显示反欺诈业务部
                    franchisees = franchisees.stream().filter(p -> p.getId().intValue() == 166).collect(Collectors.toList());
                }
                model.put("surveyfranchisees", franchisees);
                if ("115".equals(btnCode)) {
                    model.put("surveyCaseId", req.getParameter("surveyCaseId"));//调查员案件ID
                }
            }
            if ("113".equals(btnCode) || "116".equals(btnCode)) {
                if (dto.getCurrentSurveyAssignOrg() != null) {
                    model.put("taskRemark", dto.getCurrentSurveyAssignOrg().getOrgTaskRemark());
                }
                if ("116".equals(btnCode)) {
                    model.put("surveyCaseId", req.getParameter("surveyCaseId"));//调查员案件ID
                }
                if ("113".equals(btnCode)) {
                    model.put("isSurveyPrimary", dto.getIsPrimaryUser());
//                    if (dto.getSurveyInvestigatorCases() != null && dto.getSurveyInvestigatorCases().size() > 0){
//                        model.put("isSurveyPrimary",false);//是辅助调查员
//                    }else{
//                        model.put("isSurveyPrimary",true);//是主调查员
//                    }
                }
                model.put("curSurveyOrgId", dto.getCurrentSurveyAssignOrg().getSurveyOrgId());//机构id
                surveyOrgId = dto.getCurrentSurveyAssignOrg().getSurveyOrgId().toString();
            }

            param = new HashMap<String, Object>();
            param.put("surveyCode", "investigator");
            param.put("authType", 2);
            param.put("accState", 0);
            param.put("menuType", 1);//不分页
            if ("116".equals(btnCode)) {//取当前选择调查员案件所在机构的调查员
                if (dto.getCurrentSurveyAssignOrg() != null) {
                    param.put("currentOrgId", dto.getCurrentSurveyAssignOrg().getSurveyOrgId());
                }
            }
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, param, req);
            model.put("surveyUsers", apiFinalResponse.getResults());

        } else if ("112".equals(btnCode) || "117".equals(btnCode)) {
            param.put("surveyCode", "franchisee");
            param.put("menuType", 1);//不分页
            param.put("level", 1);//仅获取顶级的调查机构
            param.put("clientType", clientType);
            param.put("enable", 0);//查询启用状态的调查方机构
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, param, req);
            model.put("surveyfranchisees", apiFinalResponse.getResults());
            model.put("taskRemark", req.getParameter("taskRemark"));
            if ("117".equals(btnCode)) {
                model.put("orgCaseId", req.getParameter("orgCaseId"));//调查员案件ID
            }

            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("sortRule", 1);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyServiceTypeDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SERVICE_TYPE_LIST, appendMap, req);
            model.put("services", apiFinalResponse.getResults());

        }
        if ("112".equals(btnCode) || "117".equals(btnCode)) {
            model.put("taskRemark", dto.getSurveyItem());
        }
        if ("111".equals(btnCode) || "115".equals(btnCode)) {
            model.put("taskRemark", dto.getSurveyItem());
        }
        String backCaseId = req.getParameter("backCaseId");
        if ("112".equals(btnCode) || "113".equals(btnCode)) {
            model.put("backCaseId", backCaseId);
        }
        if (backCaseId != null && !"".equals(backCaseId)) {
            model.put("isSurveyPrimary", false);//是辅助调查员
        }
        Date minDate = new Date();
        Date maxDate = null;
        try {
            maxDate = selectMaxDate(dto, btnCode, surveyOrgId);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.put("minDate", null);
        model.put("maxDate", maxDate);
        model.put("btnCode", btnCode);
        if ("112".equals(btnCode) || "117".equals(btnCode)) {
            Date hzEndDate = dto.getEndTime();//案件截止时间
            model.put("hzEndDate", hzEndDate.getTime());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date entrustTime = dto.getSurveyRiskCase().getEntrustTime();//案件委托时间
            Date entrustTime = new Date();
            LocalDate localDate = entrustTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            try {
                model.put("hzSixDate", sdf.parse(localDate.plusDays(7).toString()).getTime());
                model.put("hzTenDate", sdf.parse(localDate.plusDays(15).toString()).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            model.put("maxDateTime", maxDate.getTime());
            model.put("autoOpenInfo", req.getParameter("autoOpenInfo"));
        }
        return new ModelAndView("/survey/case/assignSurveyUser", model);
    }

    //分派案件、改派案件；截止日期选择
    private Date selectMaxDate(SurveyRiskCaseInfoDto dto, String btnCode, String surveyOrgId) throws ParseException {
        Date maxDate = new Date();
        // 111、乐凡分派人员；112、乐凡分派机构；117、乐凡改派机构；116、乐凡改派调查员；113、机构分派调查员；116、机构改派点差远
        switch (btnCode) {
            case "111":
                maxDate = dto.getEndTime();
                break;//乐凡分派人员
            case "112":
                maxDate = dto.getEndTime();
                break;
//            case "113": maxDate = dto.getCurrentSurveyAssignOrg().getOrgEndTime();break;
            case "113":
                maxDate = dto.getEndTime();
                break;
            case "115":
                maxDate = dto.getEndTime();
                break;//乐凡改派人员
            case "116":
                maxDate = dto.getCurrentSurveyAssignOrg().getOrgEndTime();
                break;
            case "117":
                maxDate = dto.getEndTime();
                break;
        }
        //112,117时，如果是
        if ("112".equals(btnCode) || "117".equals(btnCode) || "113".equals(btnCode) || "116".equals(btnCode) || "111".equals(btnCode)) {
            if (dto.getSurveyConsignor() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //互助的截取时间，单点、契约根据当前时间往后6个自然日；深度根据当前时间往后10个自然日
                if (dto.getSurveyConsignor().getOrgAttr() == 2) {//互助
                    Integer efficiencyAttr = dto.getSurveyConsignor().getEfficiencyAttr();
                    if ("112".equals(btnCode) || "117".equals(btnCode)) {
//                        Date entrustTime = dto.getSurveyRiskCase().getEntrustTime();//案件委托时间
                        Date entrustTime = new Date();
                        LocalDate localDate = entrustTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        if (dto.getServicesId() == 12 || dto.getServicesId() == 11) {
                            String mydate = GetWorkDay.calLeaveEndDate(entrustTime, null, 7, efficiencyAttr).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
                            maxDate = sdf.parse(mydate + " 23:59:59");
                        } else if (dto.getServicesId() == 13) {
                            String mydate = GetWorkDay.calLeaveEndDate(entrustTime, null, 15, efficiencyAttr).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
                            maxDate = sdf.parse(mydate + " 23:59:59");
                        }
                        System.out.println(sdf.format(maxDate));
                    }
                    if ("113".equals(btnCode) || "116".equals(btnCode)) {
//                        Date entrustTime = dto.getSurveyRiskCase().getEntrustTime();//案件委托时间
                        Date entrustTime = new Date();
                        LocalDate localDate = entrustTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        Long servicesId = dto.getServicesId() == null ? dto.getCurrentSurveyAssignOrg().getServicesId() : dto.getServicesId();
                        if (servicesId == 12 || servicesId == 11) {
                            String mydate = GetWorkDay.calLeaveEndDate(entrustTime, null, 5, efficiencyAttr).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
                            maxDate = sdf.parse(mydate + " 23:59:59");
                        } else if (servicesId == 13) {
                            String mydate = GetWorkDay.calLeaveEndDate(entrustTime, null, 10, efficiencyAttr).toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
                            maxDate = sdf.parse(mydate + " 23:59:59");
                        }
                    }
//                    if(!maxDate.before(dto.getEndTime())){
//                        maxDate = dto.getEndTime();
//                    }
                }
                if (dto.getSurveyConsignor().getOrgAttr() == 1) {//保司
                    int day = GetWorkDay.calLeaveDays(dto.getSurveyRiskCase().getEntrustTime(), dto.getEndTime(), 1);
                    if (day > 0) {
                        maxDate = GetWorkDay.calLeaveEndDate(new Date(), null, (day - 1), 1);
                    } else {
                        maxDate = new Date();
                    }
                    maxDate = sdf.parse(new SimpleDateFormat("yyyy-MM-dd").format(maxDate) + " 23:59:59");
                }
            }
        }
        return maxDate;
    }

    @RequestMapping(value = "/selectConsignerByOrgId")
    public String selectConsignerByOrgId(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        Long consignorId = Long.parseLong(req.getParameter("consignorId"));
        appendMap.put("consignorId", consignorId);
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CONSIGNER_BY_ORG, appendMap, req, rsp);
    }


    @RequestMapping(value = "assignSurveyUserOK")
    public String assignSurveyUserOK(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> param = new HashMap<>();
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_ASSIGNATION, param, req, rsp);
    }

    @RequestMapping(value = "addFollow")
    public ModelAndView addFollow(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, String> model = new HashMap<String, String>();
        model.put("id", req.getParameter("id"));
        model.put("btnCode", req.getParameter("btnCode"));
        model.put("surveyCno", req.getParameter("surveyCno"));
        if ("newfollow".equals(req.getParameter("btnCode"))) {
            model.put("surveyOrgId", req.getParameter("surveyOrgId"));
            return new ModelAndView("/survey/case/addFollowNew", model);
        }
        return new ModelAndView("/survey/case/addFollow", model);
    }

    @RequestMapping(value = "addFollowOK")
    public String addFollowOK(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> param = new HashMap<>();
        if ("0610addFollow".equals(req.getParameter("type"))) {
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_ADD_PROGRESS, param, req, rsp);
        }
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_ADD_FOLLOW, param, req, rsp);
    }


    @RequestMapping(value = "manager")
    public ModelAndView manager(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, SurveyNumberDto> model = new HashMap<>();
        TypeToken<ApiFinalResponse<SurveyNumberDto>> typeToken = new TypeToken<ApiFinalResponse<SurveyNumberDto>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_MANAGER_SURVEY, null, req);
        SurveyNumberDto dto = (SurveyNumberDto) apiFinalResponse.getResults();
        model.put("dto", dto);
        return new ModelAndView("/survey/case/manager", model);
    }

    @RequestMapping(value = "view")
    public ModelAndView view(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, List<SurveyFeeDetailsDto>> model = new HashMap<String, List<SurveyFeeDetailsDto>>();
        TypeToken<ApiFinalResponse<List<SurveyFeeDetailsDto>>> typeToken = new TypeToken<ApiFinalResponse<List<SurveyFeeDetailsDto>>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_FEE_DETAIL_LIST, null, req);
        List<SurveyFeeDetailsDto> feeDetails = (List<SurveyFeeDetailsDto>) apiFinalResponse.getResults();
        model.put("feeDetails", feeDetails);
        return new ModelAndView("/survey/case/manager", model);
    }

    @RequestMapping(value = "operateView")
    public ModelAndView operateView(HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        String btnCode = req.getParameter("btnCode");
        model.put("id", req.getParameter("id"));
        System.out.println(req.getParameter("surveyInfoId"));
        model.put("btnCode", btnCode);
        if ("1300".equals(btnCode) || "1400".equals(btnCode)) {
            model.put("currentDate", new Date());
        }
        if ("dispatch".equals(btnCode)) {
            TypeToken<ApiFinalResponse<Map>> typeToken = new TypeToken<ApiFinalResponse<Map>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_DISPATCHER, null, req);
            Map map = (Map) apiFinalResponse.getResults();
            model.put("surveyTaskTypes", map.get("surveyTaskTypes"));
            model.put("surveyTaskInfos", map.get("surveyTaskInfos"));
        } else if ("progress".equals(btnCode)) {
            TypeToken<ApiFinalResponse<List<SurveyProgressDto>>> typeToken = new TypeToken<ApiFinalResponse<List<SurveyProgressDto>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_PROGRESS, null, req);
            model.put("progressList", apiFinalResponse.getResults());
            return new ModelAndView("/survey/case/progress", model);
        } else if ("workflow".equals(btnCode)) {
            TypeToken<ApiFinalResponse<List<SurveyCaseWorkflowDto>>> typeToken = new TypeToken<ApiFinalResponse<List<SurveyCaseWorkflowDto>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_WORKFLOW, null, req);
            model.put("caseWorkflowList", apiFinalResponse.getResults());
        } else if ("1300".equals(btnCode) || "1400".equals(btnCode)) {
            try {
                SimpleDateFormat minSdf = new SimpleDateFormat("yyyy");
                String year = minSdf.format(new Date());
                SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
                Date minDate = dateSdf.parse(year + "-01-01");
                Date maxDate = dateSdf.parse(year + "-12-31");
//                model.put("minDate",minDate);
                model.put("maxDate", new Date());//最大时间不能大于当前时间  2019年9月19日10点25分 新增
                if ("1400".equals(btnCode) || "1300".equals(btnCode)) {
                    String entrustTime = req.getParameter("entrustTime");
                    if (entrustTime != null && !"".equals(entrustTime)) {
                        minDate = new SimpleDateFormat("yyyy-MM-dd").parse(entrustTime);
                        model.put("minDate", minDate);
                    }
                }
                if ("1300".equals(btnCode)) {
                    //获取委托方的收件箱发件箱信息。
                    TypeToken<ApiFinalResponse<SurveyEmailInfoDTO>> typeToken = new TypeToken<ApiFinalResponse<SurveyEmailInfoDTO>>() {
                    };
                    ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_GET_ENTRUST_END_INFO, null, req);
                    SurveyEmailInfoDTO emailInfo = (SurveyEmailInfoDTO) apiFinalResponse.getResults();
                    model.put("emailInfo", emailInfo);

                    SurveyRiskCaseInfoDto surveyRiskCaseInfo = emailInfo.getSurveyRiskCaseInfo();
                    SurveyRiskCaseDto surveyRiskCase = emailInfo.getSurveyRiskCase();
                    model.put("surveyCaseNo", surveyRiskCase.getSurveyCaseNo());
                    Double price1 = surveyRiskCaseInfo.getEntrustMoney() == null ? 0D : surveyRiskCaseInfo.getEntrustMoney();
                    Double price2 = surveyRiskCaseInfo.getEntrustReLosses() == null ? 0D : surveyRiskCaseInfo.getEntrustReLosses();
                    model.put("price", price1 + price2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if ("1400".equals(btnCode)) {
                TypeToken<ApiFinalResponse<List<SurveyInvestigatorCaseDto>>> typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorCaseDto>>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.INFO_SURVEY_INVESTIGATOR_CASE_REWARD, null, req);
                List<SurveyInvestigatorCaseDto> surveyInvestigatorCaseDtoList = (List<SurveyInvestigatorCaseDto>) apiFinalResponse.getResults();
                model.put("surveyInvestigatorCaseDtoList", surveyInvestigatorCaseDtoList);
            }
        } else if ("sendReport".equals(btnCode)) {
            TypeToken<ApiFinalResponse<List<UserInfo>>> typeToken = new TypeToken<ApiFinalResponse<List<UserInfo>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_USER_INFO_LIST_BY_ROLEID, null, req);
            List<UserInfo> users = (List<UserInfo>) apiFinalResponse.getResults();
//            model.put("roleId",req.getParameter("roleId"));
            model.put("users", users);
        } else if ("belongUser".equals(btnCode)) {
            //狄大人-调查审核（改派归属人 改派时，对人员的筛选）
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode", "finalJudgmentUser");//狄大人平台终审人员
            appendMap.put("caseId", req.getParameter("id"));//被改派的案件id
            TypeToken<ApiFinalResponse<List<UserInfo>>> typeToken = new TypeToken<ApiFinalResponse<List<UserInfo>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req);
            List<UserInfo> users = (List<UserInfo>) apiFinalResponse.getResults();
            model.put("users", users);
        } else if ("org5".equals(btnCode) || "survey-report-opr".equals(btnCode) || "survey-opr".equals(btnCode)) {
            TypeToken<ApiFinalResponse<SurveyOprInfoDTO>> typeToken = new TypeToken<ApiFinalResponse<SurveyOprInfoDTO>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_GET_OPR_INFO, null, req);
            SurveyOprInfoDTO oprInfo = (SurveyOprInfoDTO) apiFinalResponse.getResults();
            List<SurveyCaseDirectionDto> directions = oprInfo.getDirections();
            for (SurveyCaseDirectionDto direction : directions) {
                if (direction.getSurveyCaseDirectionFiles() != null) {
                    direction.setDirectionFilesSize(direction.getSurveyCaseDirectionFiles().size());
                } else {
                    String folder = "/mnt/sftp/files/" + surveyFilePathSftpSource + "/ddr/cno/" + oprInfo.getSurveyRiskInfo().getSurveyCno().toLowerCase() + "/" + "direction" + "/" + direction.getDirectionName();
                    File file = new File(folder);
                    if (file.exists()) {
                        if (file.isDirectory()) {
                            direction.setDirectionFilesSize(file.listFiles().length);
                        }
                    }
                }
            }
            model.put("oprInfo", oprInfo);

            // 重新生成报告 为了显示预览 2019年9月20日15点23分
            try {
                TemplateData data = oprInfo.getTemplateData();
                SurveyModelInfoDto m = data.getModel();
                String modelPath = null;
                if ("dev".equals(surveySettingSource)) {//如果是本低环境 则模板路径 不取数据库配置路径
                    modelPath = "E:\\mnt\\model";
                } else {
                    modelPath = m.getModelPath();
                }
                String generateReportPath = WordUtil.generateReport(data, m.getId().intValue(), m.getModelName(), generateFilePath.concat("/").concat(data.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase()).concat("/report"), data.getReportName(), modelPath);
                //为了解决缓存
                //将文件复制一份。预览复制后的文件。复制的路径以时间戳做为路径
                String copyPath = "";
                File file = new File(generateReportPath);
                if (file.exists()) {
                    copyPath = "/mnt/sftp/files/temp/report/" + data.getSurveyRiskCaseInfo().getSurveyNo() + "/" + System.currentTimeMillis() + "/" + file.getName();
                    FileUtils.copy(file, new File(copyPath));
                }
                if ("/mnt/sftp/files/product/ddr/cno/".equals(generateFilePath) || "/mnt/sftp/files/test/ddr/cno/".equals(generateFilePath)) {
//                    if ("/mnt/sftp/files/product/ddr/cno/".equals(generateFilePath)){
//                        generateReportPath = generateReportPath.replace("/mnt/sftp/files/product/ddr/cno/",surveyFilePathSftp.concat("/product/ddr/cno/"));
//                        copyPath = copyPath.replace("/mnt/sftp/files",surveyFilePathSftp);
//                    }else if ("/mnt/sftp/files/test/ddr/cno/".equals(generateFilePath)){
//                        generateReportPath = generateReportPath.replace("/mnt/sftp/files/test/ddr/cno/",surveyFilePathSftp.concat("/test/ddr/cno/"));
//                        copyPath = copyPath.replace("/mnt/sftp/files",surveyFilePathSftp);
//                    }
                    copyPath = copyPath.replace("/mnt/sftp/files", surveyFilePathSftp);
                }
                model.put("generateReportPath", copyPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.put("curPath", "/mnt/sftp/files/" + surveyFilePathSftpSource + "/ddr/cno/" + oprInfo.getSurveyRiskInfo().getSurveyCno().toLowerCase() + "/" + "direction" + "/");
            if ("dev".equals(surveySettingSource)) {
                model.put("curPath", "E:\\mnt");
            }
            return new ModelAndView("/survey/case/showInfoOpr", model);
        } else if ("services".equals(btnCode)) {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyRiskCaseInfoDto>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO, null, req);
            SurveyRiskCaseInfoDto dto = (SurveyRiskCaseInfoDto) apiFinalResponse.getResults();
            dto.setMinEndDate(new Date());
            model.put("dto", dto);

            //获取业务类型集合
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("sortRule", 1);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyServiceTypeDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SERVICE_TYPE_LIST, appendMap, req);
            model.put("services", apiFinalResponse.getResults());

        } else if ("entrustUpdate".equals(btnCode)) {
            String updateType = req.getParameter("updateType");//修改类型 ：org：修改委托机构；user:修改委托人
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyRiskCaseInfoDto>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO, null, req);
            SurveyRiskCaseInfoDto dto = (SurveyRiskCaseInfoDto) apiFinalResponse.getResults();
            model.put("dto", dto);
            if ("org".equals(updateType)) {
                //所有委托机构
                Map<String, Object> appendMap = new HashMap<String, Object>();
                appendMap.put("menuType", 1); //不分页
                appendMap.put("surveyCode", "consignor");//查询“委托方机构”，改变surveyCode值
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
                model.put("consignors", consignors);
            }
            model.put("updateType", updateType);
        } else if ("guide".equals(btnCode) || "guided".equals(btnCode)) {
            TypeToken<ApiFinalResponse<SurveyRiskCaseGuideDto>> typeToken = new TypeToken<ApiFinalResponse<SurveyRiskCaseGuideDto>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_RISK_CASE_GUIDE_INFO, null, req);
            SurveyRiskCaseGuideDto guideDto = (SurveyRiskCaseGuideDto) apiFinalResponse.getResults();
            model.put("guideDto", guideDto);
        } else if ("visit".equals(btnCode)) {
            TypeToken<ApiFinalResponse<SurveyRiskCaseVisitDto>> typeToken = new TypeToken<ApiFinalResponse<SurveyRiskCaseVisitDto>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_RISK_CASE_VISIT_INFO, null, req);
            SurveyRiskCaseVisitDto visitDto = (SurveyRiskCaseVisitDto) apiFinalResponse.getResults();
            model.put("visitDto", visitDto);
            model.put("surveyAssorgCaseId", req.getParameter("surveyAssorgCaseId"));
        } else if ("adjustment".equals(btnCode)) {
            String codeType = req.getParameter("codeType");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("surveyInfoId", req.getParameter("id"));
            map.put("codeType", codeType);
            map.put("dataTimeDesc", req.getParameter("dataTimeDesc"));
            TypeToken<ApiFinalResponse<List<SurveyAccountLogDto>>> typeToken = new TypeToken<ApiFinalResponse<List<SurveyAccountLogDto>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.SURVEY_ACCOUNT_LOG_LIST, map, null);
            List<SurveyAccountLogDto> list = (List<SurveyAccountLogDto>) apiFinalResponse.getResults();
            model.put("list", list);
            return new ModelAndView("/survey/case/adjustment", model);
        } else if ("reass".equals(btnCode)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            /* //案件list
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoDto>>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_LIST, paramMap, req);
            List<SurveyRiskCaseInfoDto> data = (List<SurveyRiskCaseInfoDto>)apiFinalResponse.getResults();
            model.put("data",data);*/

            //委托方
            paramMap = new HashMap<String, Object>();
            paramMap.put("oprType", "safe");//用以区分保司与互助机构
            paramMap.put("oprTypeValue", "safe");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CONSIGNOR_LIST_FOR_FINAL_USER, paramMap, req);
            List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
            model.put("consignors", consignors);
            model.put("consignorsJson", JsonUtil.objectToJson(consignors));

            //和当前登录人，配置了重复委托方的“复审人员”
            paramMap = new HashMap<String, Object>();
            paramMap.put("oprUserType", 2);//1查询互助复审人员  2查询保司复审人员
            typeToken = new TypeToken<ApiFinalResponse<List<UserInfo>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SAME_FINAL_USER, paramMap, req);
            List<UserInfo> myFinalUsers = (List<UserInfo>) apiFinalResponse.getResults();
            model.put("myFinalUsers", myFinalUsers);
            model.put("myFinalUsersJson", JsonUtil.objectToJson(myFinalUsers));

            //所有的复审人员
            paramMap = new HashMap<String, Object>();
            paramMap.put("roleId", 53);
            paramMap.put("oprUserType", 2);//1查询互助复审人员  2查询保司复审人员
            typeToken = new TypeToken<ApiFinalResponse<List<UserInfoOprDTO>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_OPR_USER, paramMap, req);
            List<UserInfoOprDTO> finalUserInfos = (List<UserInfoOprDTO>) apiFinalResponse.getResults();
            model.put("finalUserInfos", finalUserInfos);
            model.put("finalUserInfosJson", JsonUtil.objectToJson(finalUserInfos));

            return new ModelAndView("/survey/case/surveyFinalUser", model);

        }
        return new ModelAndView("/survey/case/operateView", model);
    }

    @RequestMapping(value = "ajaxData")
    public void ajaxData(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        String btnCode = request.getParameter("btnCode");
        String downType = request.getParameter("downType");
        if ("1300".equals(btnCode)) {
            //分卷压缩 报告及附件
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("surveyInfoId", request.getParameter("id"));
            TypeToken<ApiFinalResponse<TemplateData>> typeToken1 = new TypeToken<ApiFinalResponse<TemplateData>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_SURVEY_GET_TEMPLATE_DATA, paramMap, request);
            TemplateData data = (TemplateData) apiFinalResponse.getResults();
            SurveyModelInfoDto dataModel = data.getModel();
            String modelPath = null;
            if ("dev".equals(surveySettingSource)) {//如果是本低环境 则模板路径 不取数据库配置路径
                modelPath = "D:\\templete";
            } else {
                modelPath = dataModel.getModelPath();
            }
            try {
                WordUtil.generateReport(data, dataModel.getId().intValue(), dataModel.getModelName(), generateFilePath.concat("/").concat(data.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase()).concat("/report"), data.getReportName(), modelPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //因为会存在无用的“附件”（如新增方向的时候，先上传附件，却未最终提交，会导致上述附件为冗余附件，故删除）
            deleteNotFindDirectionName(data);

            SurveyRiskCaseInfoDto surveyRiskCaseInfo = data.getSurveyRiskCaseInfo();
            SurveyRiskCaseDto surveyRiskCase = surveyRiskCaseInfo.getSurveyRiskCase();
            jsonMap.put("surveyCaseNo", surveyRiskCase.getSurveyCaseNo());
            Double price1 = surveyRiskCaseInfo.getEntrustMoney() == null ? 0D : surveyRiskCaseInfo.getEntrustMoney();
            Double price2 = surveyRiskCaseInfo.getEntrustReLosses() == null ? 0D : surveyRiskCaseInfo.getEntrustReLosses();
            jsonMap.put("price", price1 + price2);
            //压缩方向附件+附件
            Integer pdfType = surveyRiskCaseInfo.getSurveyConsignor().getPdfType();
            if (pdfType == null) {
                pdfType = 1;
            }
            Map zipMap = SurveyZipUtil.sftpZip(surveyRiskCaseInfo.getSurveyCno().toLowerCase(), surveySettingSource, surveyRiskCase.getSurveyCaseNo(), downType, pdfType.toString());
            if (zipMap != null) {
                String zipPath = zipMap.get("path").toString();
                Long maxSize = StringUtils.isEmpty(request.getParameter("maxSize")) ? 50 : new Double(request.getParameter("maxSize")).longValue();
                Double size = surveyRiskCaseInfo.getSurveyConsignor().getAttrMaxSize();
                if (size != null) {
                    maxSize = size.longValue() == 0 ? 50L : size.longValue();
                }
                try {
                    List<SurveyFileDTO> files = new ArrayList<SurveyFileDTO>();
                    String destFile = "/mnt/sftp/files/temp/email/" + surveyRiskCaseInfo.getSurveyCno() + "/" + surveyRiskCase.getSurveyCaseNo() + ".zip";
                    if ("dev".equals(surveySettingSource)) {
                        destFile = "D:\\templete\\zip\\" + surveyRiskCase.getSurveyCaseNo() + ".zip";
                    }
                    List<Map<String, Object>> zipFiles = ZipPartUtil.zip(zipPath, destFile, null, maxSize);
                    for (Map<String, Object> zipFile : zipFiles) {
                        Iterator<String> iterator = zipFile.keySet().iterator();
                        while (iterator.hasNext()) {
                            String key = iterator.next();
                            SurveyFileDTO file = new SurveyFileDTO();
                            file.setFilePath(key);
                            file.setHttpFilePath(key.replace("/mnt/sftp/files", surveyFilePathSftp));
                            file.setFileName(zipFile.get(key).toString().substring(zipFile.get(key).toString().lastIndexOf(File.separator) + 1));
                            files.add(file);
                        }
                    }
                    jsonMap.put("files", files);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if ("direction-error".equals(btnCode)) {
            TypeToken<ApiFinalResponse<String>> typeToken1 = new TypeToken<ApiFinalResponse<String>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_VALID_DIRECTION_TEXT_ERROR_BAIDU, null, request);
            jsonMap.put("json", apiFinalResponse.getResults());
        }
        String json = sh.zj100.common.util.JsonUtil.objectToJson(jsonMap);
        this.outputJson(json, response);
    }


    @RequestMapping(value = "listCaseDirection")
    public ModelAndView listCaseDirection(HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        model.put("pageSize", req.getParameter("pageSize"));
        //保司审核时间 -- 保司审核开始时间
        String entrReportStateDate = req.getParameter("entrReportStateDate");
        String entrReportEndDate = req.getParameter("entrReportEndDate");
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("busType", "2,3");
        appendMap.put("entrReportStateDate", entrReportStateDate == null ? DateUtil.getTopMonth() : entrReportStateDate);
        appendMap.put("entrReportEndDate", entrReportEndDate == null ? DateUtil.getLastMonth() : entrReportEndDate);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyCaseDirectionScoreDto>>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_DIRECTION_LIST_BY_SCORE, appendMap, req);
        model.put("apiRsp", apiFinalResponse);

        //所有调查机构信息
        appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode", "franchisee");
        appendMap.put("menuType", 1);
        appendMap.put("btnCode", "1000");
        typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {
        };
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
        List<SurveyFranchiseeDto> surveyFranchiseeList = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
        surveyFranchiseeList = surveyFranchiseeList.parallelStream().filter(e -> Optional.ofNullable(e.getBusType()).isPresent() && e.getBusType() != 1).collect(Collectors.toList());
        model.put("franchisee", surveyFranchiseeList);

        //所有保险公司信息
        appendMap = new HashMap<String, Object>();
        appendMap.put("menuType", 1); //不分页
        appendMap.put("surveyCode", "consignor");//查询“狄大人终审人员对应的委托方”，改变surveyCode值
        typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
        };
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
        List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
        model.put("consignors", consignors);

        String consignorIds = req.getParameter("consignorIds");
        model.put("consignorIds", consignorIds == null ? "" : consignorIds);

        String surveyOrgId = req.getParameter("surveyOrgId");
        model.put("surveyOrgId", surveyOrgId == null ? "" : surveyOrgId);

        appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode", "investigator");
        appendMap.put("menuType", 1);
        appendMap.put("orgId", surveyOrgId);
        typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {
        };
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
        List<SurveyInvestigatorDto> surveyInvestigatorList = (List<SurveyInvestigatorDto>) apiFinalResponse.getResults();
        surveyInvestigatorList = surveyInvestigatorList.parallelStream().filter(e -> Optional.ofNullable(e.getBusType()).isPresent() && e.getBusType() != 1).collect(Collectors.toList());
        model.put("investigator", surveyInvestigatorList);

        String surveyUserId = req.getParameter("surveyUserId");
        model.put("surveyUserId", surveyUserId == null ? "" : surveyUserId);

        //终审时间 -- 保司审核开始时间
        String reportStartDate = req.getParameter("reportStartDate");
        model.put("reportStartDate", reportStartDate == null ? "" : reportStartDate);
        String reportEndDate = req.getParameter("reportEndDate");
        model.put("reportEndDate", reportEndDate == null ? "" : reportEndDate);
        //保司审核时间 -- 保司审核开始时间
        model.put("entrReportStateDate", entrReportStateDate == null ? DateUtil.getTopMonth() : entrReportStateDate);
        model.put("entrReportEndDate", entrReportEndDate == null ? DateUtil.getLastMonth() : entrReportEndDate);

        //多选
        model.put("surveyOrgIds", req.getParameter("surveyOrgIds") == null ? "" : req.getParameter("surveyOrgIds"));
        model.put("consignorIds", req.getParameter("consignorIds") == null ? "" : req.getParameter("consignorIds"));
        model.put("surveyUserIds", req.getParameter("surveyUserIds") == null ? "" : req.getParameter("surveyUserIds"));
        return new ModelAndView("/survey/case/listCaseDirection", model);
    }

    @RequestMapping(value = "infoCaseDirection")
    public ModelAndView infoCaseDirection(HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        Map<String, Object> appendMap = new HashMap<String, Object>();
        //调查方向信息
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyCaseDirectionScoreDto>>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_DIRECTION_INFO_BY_SCORE, null, req);
        model.put("apiRsp", apiFinalResponse);

        //人员基础信息
        appendMap = new HashMap<String, Object>();
        appendMap.put("userId", req.getParameter("userId"));
        appendMap.put("surveyCode", "investigator");
        appendMap.put("btnCode", 1000);
        typeToken = new TypeToken<ApiFinalResponse<SurveyInvestigatorDto>>() {
        };
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, null);
        model.put("surveyInvestigator", apiFinalResponse.getResults());

        String scores = req.getParameter("scores");
        model.put("scores", scores == null ? "" : scores);
        return new ModelAndView("/survey/case/infoCaseDirection", model);
    }

    @RequestMapping(value = "exportCaseDirection")
    public void exportCaseDirection(HttpServletRequest req, HttpServletResponse rsp) {

        TypeToken<ApiFinalResponse<Map>> typeToken = new TypeToken<ApiFinalResponse<Map>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_DIRECTION_LIST_TO_EXPORT, null, req);
        Map map = (Map) apiFinalResponse.getResults();
        List<SurveyCaseDirectionScoreDto> list = (List<SurveyCaseDirectionScoreDto>) map.get("list");
        List<SurveyCaseDirectionScoreDto> directionList = (List<SurveyCaseDirectionScoreDto>) map.get("directionList");

        // 创建excel
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename = "分值清单.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页

            WritableSheet sheet = book.createSheet("分值数据", 0);

            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            WritableCellFormat wcfColour = new WritableCellFormat();
            wcfColour.setBackground(Colour.BLUE_GREY);
            sheet.setColumnView(0, 15);// 将第一列的宽度设为30
            sheet.setColumnView(1, 15);
            sheet.setColumnView(2, 35);
            sheet.setColumnView(3, 12);

            sheet.mergeCells(0, 0, 0, 1);//
            sheet.mergeCells(1, 0, 1, 1);//
            sheet.mergeCells(2, 0, 2, 1);//
            sheet.mergeCells(3, 0, 3, 1);//

            // 设置表头
            sheet.addCell(new Label(0, 0, "调查员", wcf));
            sheet.addCell(new Label(1, 0, "联系方式", wcf));
            sheet.addCell(new Label(2, 0, "所属机构", wcf));
            sheet.addCell(new Label(3, 0, "分值", wcf));

            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Map scores = ((Map) list.get(i));
                    sheet.addCell(new Label(0, i + 2, scores.get("surveyUserName") == null ? "" : (String) scores.get("surveyUserName"), wcf));
                    sheet.addCell(new Label(1, i + 2, scores.get("tel") == null ? "" : (String) scores.get("tel"), wcf));
                    sheet.addCell(new Label(2, i + 2, scores.get("surveyOrgName") == null ? "" : (String) scores.get("surveyOrgName"), wcf));
                    sheet.addCell(new Number(3, i + 2, (Double) (scores.get("scores") == null ? 0D : scores.get("scores")), wcf));
                }
            }

            //sheet2的内容
            WritableSheet sheetTwo = book.createSheet("分值数据明细", 1);
            sheetTwo.setColumnView(0, 25);// 将第一列的宽度设为30
            sheetTwo.setColumnView(1, 25);// 将第一列的宽度设为30
            sheetTwo.setColumnView(2, 30);// 将第一列的宽度设为30
            sheetTwo.setColumnView(3, 20);// 将第一列的宽度设为30
            sheetTwo.setColumnView(4, 20);// 将第一列的宽度设为30
            sheetTwo.setColumnView(5, 20);// 将第一列的宽度设为30
            sheetTwo.setColumnView(6, 25);// 将第一列的宽度设为30
            sheetTwo.setColumnView(7, 50);
            sheetTwo.setColumnView(8, 25);
            // 设置表头
            sheetTwo.addCell(new Label(0, 0, "调查员"));
            sheetTwo.addCell(new Label(1, 0, "案件编号"));
            sheetTwo.addCell(new Label(2, 0, "保险公司"));
            sheetTwo.addCell(new Label(3, 0, "区域"));
            sheetTwo.addCell(new Label(4, 0, "任务类型"));
            sheetTwo.addCell(new Label(5, 0, "任务子类"));
            sheetTwo.addCell(new Label(6, 0, "方向名称"));
            sheetTwo.addCell(new Label(7, 0, "方向内容"));
            sheetTwo.addCell(new Label(8, 0, "分值"));

            if (directionList != null && !directionList.isEmpty()) {
                for (int i = 0; i < directionList.size(); i++) {
                    Map directions = ((Map) directionList.get(i));
                    sheetTwo.addCell(new Label(0, i + 1, directions.get("surveyUserName") == null ? "" : (String) directions.get("surveyUserName"), wcf));
                    sheetTwo.addCell(new Label(1, i + 1, directions.get("surveyNo") == null ? "" : (String) directions.get("surveyNo"), wcf));
                    sheetTwo.addCell(new Label(2, i + 1, directions.get("entrustOrgName") == null ? "" : (String) directions.get("entrustOrgName"), wcf));
                    sheetTwo.addCell(new Label(3, i + 1, directions.get("areaName") == null ? "" : (String) directions.get("areaName"), wcf));
                    sheetTwo.addCell(new Label(4, i + 1, directions.get("taskName") == null ? "" : (String) directions.get("taskName"), wcf));
                    sheetTwo.addCell(new Label(5, i + 1, directions.get("newName") == null ? "" : (String) directions.get("newName"), wcf));
                    sheetTwo.addCell(new Label(6, i + 1, directions.get("directionName") == null ? "" : (String) directions.get("directionName"), wcf));
                    sheetTwo.addCell(new Label(7, i + 1, directions.get("directionText") == null ? "" : (String) directions.get("directionText"), wcf));
                    sheetTwo.addCell(new Number(8, i + 1, (Double) (directions.get("score") == null ? 0D : directions.get("score")), wcf));
                }
            }
            // 写入数据并关闭文件
            book.write();
            book.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (book != null) {
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "surveyMoney")
    public ModelAndView surveyMoney(HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        Map<String, Object> appendMap = new HashMap<String, Object>();
        model.put("searchType", req.getParameter("searchType"));
        model.put("orgId", req.getParameter("orgId"));
        model.put("sortField", req.getParameter("sortField"));
        model.put("sortType", req.getParameter("sortType"));
//        model.put("orgName",req.getParameter("orgName"));
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        if (startDate == null && endDate == null) {
            StringBuilder start = new StringBuilder();
            StringBuilder end = new StringBuilder();
            DateUtil.convertTimeBySearchType(start, end, "upMonth");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            try {
                Date d1 = simpleDateFormat.parse(start.toString());
                Date d2 = simpleDateFormat.parse(end.toString());

                startDate = simpleDateFormat.format(d1);
                endDate = simpleDateFormat.format(d2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        appendMap.put("startDate", startDate);
        appendMap.put("endDate", endDate);
        model.put("startDate", startDate);
        model.put("endDate", endDate);
        String sourceSupportType = req.getParameter("sourceSupportType");
        if (sourceSupportType == null) {
            sourceSupportType = "4";//正言
            appendMap.put("sourceSupportType", sourceSupportType);
        }
        model.put("sourceSupportType", sourceSupportType);
        String dateType = req.getParameter("dateType");
        if (dateType == null) {
            dateType = "1";//保司审核时间
            appendMap.put("dateType", dateType);
        }
        model.put("dateType", dateType);

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyMoneyDto>>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_MONEY, appendMap, req);
        model.put("apiRsp", apiFinalResponse);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        List<SurveyMoneyDto> surveyMoneyList = (List<SurveyMoneyDto>) apiFinalResponse.getResults();
        double surveyMoneyTotal = surveyMoneyList.stream().mapToDouble(SurveyMoneyDto::getSurveyMoney).sum();
        double waitMoneyTotal = surveyMoneyList.stream().mapToDouble(SurveyMoneyDto::getWageMoney).sum();
        double fitMoneyTotal = surveyMoneyList.stream().mapToDouble(SurveyMoneyDto::getFitMoney).sum();
        double hisMoneyTotal = surveyMoneyList.stream().mapToDouble(SurveyMoneyDto::getHisOweMoney).sum();
        double appMoneyTotal = surveyMoneyList.stream().mapToDouble(SurveyMoneyDto::getAppMoney).sum();
        model.put("surveyMoneyTotal", decimalFormat.format(surveyMoneyTotal));
        model.put("waitMoneyTotal", decimalFormat.format(waitMoneyTotal));
        model.put("fitMoneyTotal", decimalFormat.format(fitMoneyTotal));
        model.put("hisMoneyTotal", decimalFormat.format(hisMoneyTotal));
        model.put("appMoneyTotal", decimalFormat.format(appMoneyTotal));
        appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode", "franchisee");
        appendMap.put("menuType", 1);
        typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {
        };
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
        model.put("franchisee", apiFinalResponse.getResults());

        //多选
        model.put("surveyOrgIds", req.getParameter("surveyOrgIds") == null ? "" : req.getParameter("surveyOrgIds"));
        model.put("sourceSupportTypes", req.getParameter("sourceSupportTypes") == null ? "" : req.getParameter("sourceSupportTypes"));
        return new ModelAndView("/survey/case/surveyMoney", model);
    }

    @RequestMapping(value = "surveyMoneyDetail")
    public ModelAndView surveyMoneyDetail(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Serializable> model = new HashMap<String, Serializable>();
        model.put("searchType", req.getParameter("searchType"));
        model.put("orgId", req.getParameter("orgId"));
        model.put("startDate", req.getParameter("startDate"));
        model.put("endDate", req.getParameter("endDate"));
        model.put("dateType", req.getParameter("dateType"));
        model.put("sourceSupportType", req.getParameter("sourceSupportType"));
        model.put("detailType", req.getParameter("detailType"));
        Map<String, Object> appendMap = new HashMap<String, Object>();
        TypeToken<ApiFinalResponse<List<SurveyMoneyDtoDetail>>> typeToken = new TypeToken<ApiFinalResponse<List<SurveyMoneyDtoDetail>>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_MONEY_DETAIL, appendMap, req);
        model.put("apiRsp", apiFinalResponse);
        return new ModelAndView("/survey/case/surveyMoneyDetail", model);
    }

    @RequestMapping(value = "exportSurveyMoney")
    public void exportSurveyMoney(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        TypeToken<ApiFinalResponse<List<SurveyMoneyDto>>> typeToken = new TypeToken<ApiFinalResponse<List<SurveyMoneyDto>>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_MONEY, appendMap, req);

        List<SurveyMoneyDto> list = (List<SurveyMoneyDto>) apiFinalResponse.getResults();
        // 创建excel
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename = "调查费用清单.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页

            WritableSheet sheet = book.createSheet("调查费用清单", 0);

            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            WritableCellFormat wcfColour = new WritableCellFormat();
            wcfColour.setBackground(Colour.BLUE_GREY);
            sheet.setColumnView(0, 35);// 将第一列的宽度设为30
            sheet.setColumnView(1, 15);
            sheet.setColumnView(2, 15);

            sheet.mergeCells(0, 0, 0, 1);//
            sheet.mergeCells(1, 0, 1, 1);//
            sheet.mergeCells(2, 0, 2, 1);//

            // 设置表头
            sheet.addCell(new Label(0, 0, "调查机构名称", wcf));
            sheet.addCell(new Label(1, 0, "调查方金额", wcf));
            sheet.addCell(new Label(2, 0, "委托方金额", wcf));

            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    sheet.addCell(new Label(0, i + 2, list.get(i).getOrgName(), wcf));
                    sheet.addCell(new Number(1, i + 2, list.get(i).getSurveyMoney(), wcf));
                    sheet.addCell(new Number(2, i + 2, list.get(i).getEntrustMoney(), wcf));
                }
            }

            // 写入数据并关闭文件
            book.write();
            book.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (book != null) {
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "exportSurveyMoneyDetail")
    public void exportSurveyMoneyDetail(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        TypeToken<ApiFinalResponse<List<SurveyMoneyDtoDetail>>> typeToken = new TypeToken<ApiFinalResponse<List<SurveyMoneyDtoDetail>>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_MONEY_DETAIL, appendMap, req);

        List<SurveyMoneyDtoDetail> list = (List<SurveyMoneyDtoDetail>) apiFinalResponse.getResults();
        Long timestamp = new Date().getTime();//当前时间戳
        String excelPath = accountExcelPath + timestamp;
        File file = new File(excelPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        int index = 0;
        String endDate = req.getParameter("endDate");
        if (endDate == null || "".equals(endDate)) {
            endDate = new Date().toString();
        }
        int month = 1;
        int year = 2019;
        try {
            Date date = new SimpleDateFormat("yyyy-MM").parse(endDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            month = cal.get(Calendar.MONTH) + 1;
            year = cal.get(Calendar.YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        createSurveyMoneyBook(list, rsp, excelPath, index,  false, month, year);
        try {
            generateSurveyMoney(list, excelPath, year + "", month + "", rsp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateSurveyMoney(List<SurveyMoneyDtoDetail> list, String excelPath, String year, String month, HttpServletResponse rsp) {
        List<String> orgNames = new ArrayList<String>();
        for (SurveyMoneyDtoDetail surveyMoneyDtoDetail : list) {
            if (!orgNames.contains(surveyMoneyDtoDetail.getParentOrgName())) {
                orgNames.add(surveyMoneyDtoDetail.getParentOrgName());
            }
        }
        try {
            for (String orgName : orgNames) {
                String fileName = orgName + "-" + year + "年" + month + "月调查费用清单.xls";
                OutputStream outputStream = new FileOutputStream(excelPath + File.separator + fileName);
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFCellStyle cellStyle = workbook.createCellStyle();
                HSSFDataFormat dataFormat = workbook.createDataFormat();
                cellStyle.setDataFormat(dataFormat.getFormat("0.00"));
                HSSFCellStyle cellStyle2 = workbook.createCellStyle();
                cellStyle2.setDataFormat(dataFormat.getFormat("0"));
                HSSFSheet sheet = workbook.createSheet(orgName + "-" + year + "年" + month + "月表");
                sheet.setDefaultColumnWidth(20);
                sheet.setDefaultRowHeightInPoints(20);
                HSSFRow row = sheet.createRow(0);
                row.createCell(0).setCellValue("调查机构");
                row.createCell(1).setCellValue("案件编号");
                row.createCell(2).setCellValue("被调查人");
                row.createCell(3).setCellValue("委托公司");
                row.createCell(4).setCellValue("业务类型");

                row.createCell(5).setCellValue("调查员");

                row.createCell(6).setCellValue("分派机构时间");
                row.createCell(7).setCellValue("机构提交时间");
                row.createCell(8).setCellValue("是否阳性");
                row.createCell(9).setCellValue("机构时效");
                row.createCell(10).setCellValue("区域类别");
                row.createCell(11).setCellValue("考核时效");
                row.createCell(12).setCellValue("超期天数");
                row.createCell(13).setCellValue("原调查金额");
                row.createCell(14).setCellValue("超期绩效考核");
                row.createCell(15).setCellValue("实际调查金额");
                row.createCell(16).setCellValue("备注");
                Iterator<Cell> srcCells = row.cellIterator();
                while (srcCells.hasNext()) {
                    //设置样式
                    HSSFFont font = workbook.createFont();
                    font.setFontName("宋体");
                    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
                    HSSFCellStyle cellStyle1 = workbook.createCellStyle();
                    cellStyle1.setAlignment(HSSFCellStyle.VERTICAL_TOP); // 指定单元格居中对齐
                    cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);// 指定单元格垂直居中对齐
                    cellStyle1.setWrapText(true);// 指定单元格自动换行
                    HSSFCell srcCell = (HSSFCell) srcCells.next();
                    srcCell.setCellStyle(cellStyle1);
                }
                for (int i = 0; i < list.size(); i++) {
                    SurveyMoneyDtoDetail item = list.get(i);
                    if (orgName.equals(item.getParentOrgName())) {
                        row = sheet.createRow(sheet.getPhysicalNumberOfRows());
                        row.createCell(0).setCellValue(item.getOrgName());
                        row.createCell(1).setCellValue(item.getSurveyCaseNo());
                        row.createCell(2).setCellValue(item.getSurveyPerson());
                        row.createCell(3).setCellValue(item.getEntrustOrgName());
                        row.createCell(4).setCellValue(item.getServicesName());

                        row.createCell(5).setCellValue(item.getSurveyUserName());

                        row.createCell(6).setCellValue(item.getOrgCreateTimeStr());
                        row.createCell(7).setCellValue(item.getOrgStartTimeStr());
                        if (item.getIsSun() != null) {
                            row.createCell(8).setCellValue("是");
                        } else {
                            row.createCell(8).setCellValue("否");
                        }

                        HSSFCell cell = row.createCell(9);
                        cell.setCellStyle(cellStyle2);
                        cell.setCellValue(item.getOrgDays1());

                        String areaTypeName = "";
                        Integer areaType = item.getAreaType();
                        switch (areaType) {
                            case 0:
                                areaTypeName = "直辖市市区";
                                break;
                            case 1:
                                areaTypeName = "直辖市郊区";
                                break;
                            case 2:
                                areaTypeName = "省会";
                                break;
                            case 3:
                                areaTypeName = "地级市";
                                break;
                            case 4:
                                areaTypeName = "县级市";
                                break;
                            default:
                                areaTypeName = "县级市";
                        }
                        row.createCell(10).setCellValue(areaTypeName);

                        cell = row.createCell(11);
                        cell.setCellStyle(cellStyle2);
                        cell.setCellValue(item.getAgingDay1());

                        cell = row.createCell(12);
                        cell.setCellStyle(cellStyle2);
                        cell.setCellValue(item.getAgingOver());

                        cell = row.createCell(13);
                        cell.setCellStyle(cellStyle);
                        if (item.getAssessMoney() == null) {
                            item.setAssessMoney(0D);
                        }
                        BigDecimal b3 = new BigDecimal(item.getAssessMoney());
                        double decimal3 = b3.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        cell.setCellValue(decimal3);

                        cell = row.createCell(14);
                        cell.setCellStyle(cellStyle);
                        cell.setCellValue(item.getOverdueAgingRate());

                        cell = row.createCell(15);
                        cell.setCellStyle(cellStyle);
                        if (item.getMoney() == null) {
                            item.setMoney(0D);
                        }
                        BigDecimal b = new BigDecimal(item.getMoney());
                        double decimal2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        cell.setCellValue(decimal2);

                        row.createCell(16).setCellValue(item.getRemarkAll());
                    }
                }
                workbook.write(outputStream);
                outputStream.close();
            }

            FileZipUtil.createZip(excelPath, excelPath + ".zip");
            String url = excelPath + ".zip";
            File downLoadFile = new File(url);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(downLoadFile));
            BufferedOutputStream out = new BufferedOutputStream(rsp.getOutputStream());
            byte[] buff = new byte[2048];
            int length = 0;
            while ((length = in.read(buff)) > 0) {
                out.write(buff, 0, length);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //递归 分段处理数据
    public List<SurveyRiskCaseInfoExportDto> ecursiveResultAcc(ApiFinalResponse apiFinalResponse, TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoExportDto>>> typeToken, Map<String, Object> appendMap, HttpServletRequest req, List<SurveyRiskCaseInfoExportDto> temporaryList, int page, Integer count) {
        if (apiFinalResponse != null && apiFinalResponse.getResults() != null) {
            List<SurveyRiskCaseInfoExportDto> results = new ArrayList<>((List<SurveyRiskCaseInfoExportDto>) apiFinalResponse.getResults());
            temporaryList.addAll(results);
            if (results.size() < 2000) {
                return temporaryList;
            }
            do {
                appendMap.put("page", page++);
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_LIST_TO_EXPORT, appendMap, req);
                return ecursiveResultAcc(apiFinalResponse, typeToken, appendMap, req, temporaryList, page, count);
            } while (page * 2000 < count);
        }
        return temporaryList;
    }


    /**
     * 对账清单导出
     */
    @RequestMapping(value = "/export")
    public void export(HttpServletRequest req, HttpServletResponse rsp) {
        String menuCode = req.getParameter("menuCode");
        Map<String, String> model = new HashMap<String, String>();
        model.put("surveyPerson", req.getParameter("surveyPerson"));
        model.put("surveyPhase", req.getParameter("surveyPhase"));
        model.put("surveyNo", req.getParameter("surveyNo"));
        model.put("menuCode", req.getParameter("menuCode"));
        model.put("isSendReport", req.getParameter("isSendReport"));//是否寄送
        model.put("price1IsCalc", req.getParameter("price1IsCalc"));//基本费是否结算
        model.put("price2IsCalc", req.getParameter("price2IsCalc"));//减损奖励是否结算
        model.put("surveryPersonTel", req.getParameter("surveryPersonTel"));//联系电话
        model.put("entrustOrgName", req.getParameter("entrustOrgName"));//委托机构
        model.put("assignState", req.getParameter("assignState"));//是否分派
        model.put("policyNo", req.getParameter("policyNo"));//保险合同编号
        model.put("entrustOrgId", req.getParameter("entrustOrgId"));//委托机构Id
        model.put("surveyOrgId", req.getParameter("surveyOrgId"));//调查方Id

        //对账清单导出（应用场景：1、对账订单列表 2、保司报表）
        if ("account-list".equals(menuCode)) {
            Map<String, Object> appendMap = new HashMap<String, Object>();
            String isPayEntrustFee = req.getParameter("isPayEntrustFee");
            model.put("isPayEntrustFee", isPayEntrustFee == null ? "" : isPayEntrustFee);
            //终审时间 -- 保司审核开始时间
            String reportStartDate = req.getParameter("reportStartDate");
            SimpleDateFormat simpleDateFormatMonth = new SimpleDateFormat("yyyy-MM");
            if (reportStartDate == null || "".equals(reportStartDate)) {
                model.put("reportStartDate", reportStartDate == null ? simpleDateFormatMonth.format(new Date()) : reportStartDate);
            } else {
                model.put("reportStartDate", reportStartDate == null ? "" : reportStartDate);
            }
            //是否阳性
            String isSun = req.getParameter("isSun");
            model.put("isSun", isSun == null ? "" : isSun);


            appendMap.put("searchCount", "searchCount");
            TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoExportDto>>> typeToken1 = new TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoExportDto>>>() {
            };
            ApiFinalResponse apiFinalResponse1 = this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_SURVEY_CASE_LIST_TO_EXPORT, appendMap, req);
            Integer count = apiFinalResponse1.getCount();

            appendMap.remove("searchCount");
            TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoExportDto>>> typeToken = new TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoExportDto>>>() {
            };

            appendMap.put("page", 1);
            appendMap.put("pageSize", 2000);
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_LIST_TO_EXPORT, appendMap, req);
            if (Objects.isNull(apiFinalResponse)) {
                log.error("系统异常.........................");
                return;
            }

            List<SurveyRiskCaseInfoExportDto> surveyRiskCaseInfoExportDtos = ecursiveResultAcc(apiFinalResponse,typeToken,appendMap,req,new ArrayList<>((count)),2,count);

            String bookType = req.getParameter("bookType");
            if ("all".equals(bookType)) {//总表
                createBook(surveyRiskCaseInfoExportDtos, rsp);
            } else if ("each".equals(bookType)) {//分表

                Long timestamp = new Date().getTime();//当前时间戳
                String excelPath = accountExcelPath + timestamp;
                File file = new File(excelPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                int index = 0;

                String entrReportEndDate = req.getParameter("entrReportEndDate");
                if (entrReportEndDate == null || "".equals(entrReportEndDate)) {
                    Date currentTime = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    entrReportEndDate = formatter.format(currentTime);
                }
                int month = 1;
                int year = 2019;
                try {
                    Date date = new SimpleDateFormat("yyyy-MM").parse(entrReportEndDate);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    month = cal.get(Calendar.MONTH) + 1;
                    year = cal.get(Calendar.YEAR);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                createEachBook(surveyRiskCaseInfoExportDtos, rsp, excelPath, index, false, month, year);
            }
        }
        //案件分派导出
        else if ("assign-list".equals(menuCode)) {
            String menuType = req.getParameter("menuType");//menuType有值代表按查询条件导出案件分派无值代表快速导出当日保司案件
            if ("condition-assign-list".equals(menuType)) {
                TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoDtoExport>>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_RISK_CASE_INFO_EXPORT, null, req);
                List<SurveyRiskCaseInfoDtoExport> surveyRiskCaseInfoDtoExport = (List<SurveyRiskCaseInfoDtoExport>) apiFinalResponse.getResults();
                String entrustStateTime = req.getParameter("entrustStateTime");
                String entrustEndTime = req.getParameter("entrustEndTime");
                if (entrustStateTime == "") {
                    entrustStateTime = "2019-01-01";
                }
                if (entrustEndTime == "") {
                    Date currentTime = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    entrustEndTime = formatter.format(currentTime);
                }
                exportAssignmentConditions(surveyRiskCaseInfoDtoExport, rsp, entrustStateTime, entrustEndTime);
            } else {
                TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoExportDto>>> typeToken = new TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoExportDto>>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_LIST_TO_EXPORT, null, req);
                List<SurveyRiskCaseInfoExportDto> surveyRiskCaseInfoExportDtos = (List<SurveyRiskCaseInfoExportDto>) apiFinalResponse.getResults();
                String entrustStateTime = req.getParameter("entrustStateTime");
                String entrustEndTime = req.getParameter("entrustEndTime");

                Calendar c = Calendar.getInstance();
                c.add(Calendar.DATE, -1);
                Date start = c.getTime();
                String yesterday = format.format(start);//前一天
                if (entrustStateTime == null || entrustStateTime == "") {
                    entrustStateTime = yesterday;
                }
                if (entrustEndTime == null || entrustEndTime == "") {
                    entrustEndTime = yesterday;
                }
                exportAssign(surveyRiskCaseInfoExportDtos, rsp, entrustStateTime, entrustEndTime);
            }
        } else if ("survey-list".equals(menuCode) || "all-list".equals(menuCode)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("surveyReport", "surveyReport");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoDto>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_LIST, paramMap, req);
            List<SurveyRiskCaseInfoDto> data = (List<SurveyRiskCaseInfoDto>) apiFinalResponse.getResults();
            if ("survey-list".equals(menuCode)) {
                ExcelReport.report(data, new HashMap<>(), rsp);
            } else if ("all-list".equals(menuCode)) {
                ExcelReport.reportAll(data, new HashMap<>(), rsp);
            }
        } else if ("channelNew".equals(menuCode)) {
            Map<String, Object> appendMap = new HashMap<String, Object>();
            if (req.getParameter("states") == null) {
                model.put("states", "5,6");
                appendMap.put("states", "5,6");
            } else {
                model.put("states", req.getParameter("states"));
                appendMap.put("states", "".equals(req.getParameter("states")) ? null : req.getParameter("states"));
            }
            model.put("appStartTime", req.getParameter("appStartTime"));
            model.put("appEndTime", req.getParameter("appEndTime"));
            model.put("oprStartTime", req.getParameter("oprStartTime"));
            model.put("oprEndTime", req.getParameter("oprEndTime"));
            model.put("payStartTime", req.getParameter("payStartTime"));
            model.put("payEndTime", req.getParameter("payEndTime"));

            model.put("orgUserName", req.getParameter("orgUserName"));
            model.put("orgUserIds", req.getParameter("orgUserIds"));
            model.put("entrustOrgIds", req.getParameter("entrustOrgIds"));
            model.put("isProPay", req.getParameter("isProPay") == null || "-1".equals(req.getParameter("isProPay")) ? null : req.getParameter("isProPay"));
            appendMap.put("isProPay", model.get("isProPay"));
            appendMap.put("report", "report");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyChannelCostNew>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.SURVEY_CHANNEL_NEW_LIST, appendMap, req);
            List<SurveyChannelCostNew> data = (List<SurveyChannelCostNew>) apiFinalResponse.getResults();
            ExcelReport.reportChannel(data, new HashMap<>(), rsp);
        }
    }

    private void createEachBook(List<SurveyRiskCaseInfoExportDto> surveyRiskCaseInfoExportDtos, HttpServletResponse rsp, String excelPath, int index, boolean isNewBook, int month, int year) {
        if (surveyRiskCaseInfoExportDtos.size() == 0) {
            return;
        }
        surveyRiskCaseInfoExportDtos.forEach(e -> {
            e.setDepartmentNameCopy(e.getDepartmentName());
            if (e.getEntrustOrgId() == 45){//中宏一个部门生成一个sheet
                e.setDepartmentName("中宏");
                e.setDepartmentId(45L);
            }
        });
        // 创建excel
        WritableWorkbook book = null; // 创建jxl工作簿
        SurveyRiskCaseInfoExportDto dto = surveyRiskCaseInfoExportDtos.get(index);

//        String departmentName =  dto.getDepartmentName() == null ? "" : dto.getDepartmentName().replace("/","-").replace("\\","-");
        String filename = dto.getEntrustOrgName() + "-" + dto.getDepartmentName() + "-" + year + "年" + month + "月" + "对账清单.xls";
        filename = filename.replace(" ", "").replace("\t", "").replace("/", "-").replace("\\", "-");
        int consignorOrgAttr = dto.getConsignorOrgAttr();//委托机构：公司属性（1：保险公司；2、互助机构）
        try {
            rsp.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "ISO8859-1"));
//            rsp.addHeader("Content-disposition","attachment;filename=\"" + new String(filename.getBytes("UTF-8"), "ISO8859_1") + "\"");
            rsp.setContentType("application/msexcel");
            // 打开文件
            String path = excelPath + File.separator + filename;
            File file = new File(path);
            book = Workbook.createWorkbook(file);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页

            WritableSheet sheet = book.createSheet(dto.getEntrustOrgName() + "-" + dto.getDepartmentName() + "-" + year + "年" + month + "月" + "表", 0);

            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            WritableCellFormat wcfColour = new WritableCellFormat();
            wcfColour.setBackground(Colour.BLUE_GREY);
            if (consignorOrgAttr == 1) {
                sheet.setColumnView(0, 20);
                sheet.setColumnView(1, 35);// 将第一列的宽度设为30
                sheet.setColumnView(2, 15);
                sheet.setColumnView(3, 20);
                sheet.setColumnView(4, 20);
                sheet.setColumnView(5, 20);
                sheet.setColumnView(6, 15);
                sheet.setColumnView(7, 15);
                sheet.setColumnView(8, 15);
                sheet.setColumnView(9, 15);
                sheet.setColumnView(10, 15);
                sheet.setColumnView(11, 15);
                sheet.setColumnView(12, 15);
                sheet.setColumnView(13, 20);
                sheet.setColumnView(14, 15);
                sheet.setColumnView(15, 35);
                sheet.setColumnView(16, 35);
                int i = 17;
                //如果是复星联合健康保险股份有限公司。显示结果与地区类型
                if (dto.getEntrustOrgId().intValue() == 67) {
                    sheet.setColumnView(i++, 20);
                    sheet.setColumnView(i++, 20);
                }
                sheet.setColumnView(i++, 20);
                sheet.setColumnView(i++, 20);
                sheet.setColumnView(i++, 80);

                CellView cellView = new CellView();
                cellView.setHidden(true);
//                sheet.setColumnView(3,cellView);
//                sheet.setColumnView(14,cellView);
            } else {
                sheet.setColumnView(0, 15);// 将第一列的宽度设为30
                sheet.setColumnView(1, 15);
                sheet.setColumnView(2, 15);
                sheet.setColumnView(3, 20);
                sheet.setColumnView(4, 20);
                sheet.setColumnView(5, 35);
                sheet.setColumnView(6, 15);
                sheet.setColumnView(7, 80);
            }

            WritableFont wf = new WritableFont(WritableFont.TIMES);
            wf.setColour(Colour.BLACK);
            wf.setPointSize(12);

            //标题
            WritableFont wf1 = new WritableFont(WritableFont.ARIAL, 13, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
            WritableCellFormat wcf1 = new WritableCellFormat(wf1);
            wcf1.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf1.setAlignment(Alignment.LEFT);//把水平对齐方式指定为居中
            wcf1.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            //表头
            WritableFont wf2 = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
            WritableCellFormat wcf2 = new WritableCellFormat(wf2);
            wcf2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf2.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            //具体内容
            wf = new WritableFont(WritableFont.TIMES);
            WritableCellFormat wcf3 = new WritableCellFormat(wf);
            wcf3.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf3.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf3.setWrap(true);

            //底部
            WritableFont wf4 = new WritableFont(WritableFont.ARIAL, 13, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
            WritableCellFormat wcf4 = new WritableCellFormat(wf4);
            wcf4.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf4.setAlignment(Alignment.LEFT);
            wcf4.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            //表头
            if (consignorOrgAttr == 1) {
                String title = dto.getEntrustOrgName() + "-" + dto.getDepartmentName() + "-" + year + "年" + month + "月" + "对账清单";
                sheet.mergeCells(0, 0, 10, 0);//
                sheet.addCell(new Label(0, 0, title, wcf1));
            } else {
                sheet.mergeCells(0, 0, 7, 0);//
                sheet.addCell(new Label(0, 0, "收费对账单", wcf1));
            }
            addEachSheetHeader(sheet, wcf2, 1, consignorOrgAttr, dto.getEntrustOrgId());

            int z = 2;//合并单元格的行数
            int caseNum = 0; //单个机构下 案件数量
            Double entrustMoneySum = 0D;//单个机构下 调查费金额
            for (int i = index; i < surveyRiskCaseInfoExportDtos.size(); i++) {
                SurveyRiskCaseInfoExportDto thisInfo = surveyRiskCaseInfoExportDtos.get(i);
                if (i == 0) {
                    addEachSheetContent(z, thisInfo, sheet, wcf3, wcf4);
                    if (thisInfo.getConsignorOrgAttr() == 1) {
                        z = z + (thisInfo.getSurveyCaseDirections().size() == 0 ? 1 : thisInfo.getSurveyCaseDirections().size()); //增加合并单元格数
                        caseNum = caseNum + 1;
                        entrustMoneySum = entrustMoneySum + (thisInfo.getBillingMoney() == null ? 0D : thisInfo.getBillingMoney());
                    } else if (thisInfo.getConsignorOrgAttr() == 2) {
                        z = z + 1;
                    }
                } else {
                    if (isNewBook) {
                        //添加内容
                        addEachSheetContent(z, thisInfo, sheet, wcf3, wcf4);
                        if (thisInfo.getConsignorOrgAttr() == 1) {
                            z = z + (thisInfo.getSurveyCaseDirections().size() == 0 ? 1 : thisInfo.getSurveyCaseDirections().size());//增加合并单元格数
                            caseNum = caseNum + 1;
                            entrustMoneySum = entrustMoneySum + (thisInfo.getBillingMoney() == null ? 0D : thisInfo.getBillingMoney());
                        } else if (thisInfo.getConsignorOrgAttr() == 2) {
                            z = z + 1;
                        }
                        isNewBook = false;
                    } else {
                        SurveyRiskCaseInfoExportDto lastInfo = surveyRiskCaseInfoExportDtos.get(i - 1);
                        if (thisInfo.getEntrustOrgId().equals(lastInfo.getEntrustOrgId()) && thisInfo.getDepartmentId().equals(lastInfo.getDepartmentId())) {
                            //添加内容
                            addEachSheetContent(z, thisInfo, sheet, wcf3, wcf4);
                            if (thisInfo.getConsignorOrgAttr() == 1) {
                                z = z + (thisInfo.getSurveyCaseDirections().size() == 0 ? 1 : thisInfo.getSurveyCaseDirections().size());//增加合并单元格数
                                caseNum = caseNum + 1;
                                entrustMoneySum = entrustMoneySum + (thisInfo.getBillingMoney() == null ? 0D : thisInfo.getBillingMoney());
                            } else if (thisInfo.getConsignorOrgAttr() == 2) {
                                z = z + 1;
                            }
                        } else {
                            if (thisInfo.getConsignorOrgAttr() == 1) {
                                sheet.mergeCells(0, z, 3, 0);
                                String lowName = "本月结案" + caseNum + "件，调查费" + entrustMoneySum + "元";
                                sheet.addCell(new Label(0, z, lowName, wcf4));
                            }
                            // 写入数据并关闭文件
                            book.write();
                            book.close();
                            //创建新的excel
                            if (index < surveyRiskCaseInfoExportDtos.size()) {
                                createEachBook(surveyRiskCaseInfoExportDtos, rsp, excelPath, i, true, month, year);
                            }

                        }
                    }
                }
            }
            if (consignorOrgAttr == 1) {
                sheet.mergeCells(0, z, 3, 0);
                String lowName = "本月结案" + caseNum + "件，调查费" + entrustMoneySum + "元";
                sheet.addCell(new Label(0, z, lowName, wcf4));
            }
            // 写入数据并关闭文件
            book.write();
            book.close();

            //下载zip
            FileZipUtil.createZip(excelPath, excelPath + ".zip");

            String url = excelPath + ".zip";
            File downLoadFile = new File(url);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(downLoadFile));

            rsp.reset();
            BufferedOutputStream out = new BufferedOutputStream(rsp.getOutputStream());

            byte[] buff = new byte[2048];
            int length = 0;
            while ((length = in.read(buff)) > 0) {
                out.write(buff, 0, length);
                out.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if(book!=null){
//                try {
//                    book.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        }

    }

    //export导出 分表 --创建表头
    private void addEachSheetHeader(WritableSheet sheet, WritableCellFormat wcf, int i, int consignorOrgAttr, Long entrustOrgId) {
        try {
            // 列表编号
            int c = 0;
            if (consignorOrgAttr == 1) {
                // 委托机构编号
                if (entrustOrgId.intValue() == 52) {//太平洋健康保险股份有限公司
                    sheet.addCell(new Label(0, i, "合作公司", wcf));
                    sheet.addCell(new Label(1, i, "分公司", wcf));
                    sheet.addCell(new Label(2, i, "健康险公司", wcf));
                    sheet.addCell(new Label(3, i, "任务号", wcf));
                    c = 4;
                }
                if (entrustOrgId.intValue() == 67) {//复星联合健康保险股份有限公司
                    sheet.addCell(new Label(0, i, "委托类型", wcf));
                    sheet.addCell(new Label(1, i, "调查性质", wcf));
                    c = 2;
                }
                if (entrustOrgId.intValue() == 45) {//中宏
                    sheet.addCell(new Label(0, i, "委托人机构名称", wcf));
                    sheet.addCell(new Label(1, i, "委托人名称", wcf));
                    c = 2;
                }
                if (entrustOrgId.intValue() == 131){//同方全球人寿保险有限公司
                    sheet.addCell(new Label(0, i, "派案人", wcf));
                    sheet.addCell(new Label(1, i, "调查号", wcf));
                    sheet.addCell(new Label(2, i, "案件类型", wcf));
                    c = 3;
                }
                if(entrustOrgId.intValue() == 200){//友邦人寿保险有限公司
                    sheet.addCell(new Label(0, i, "调查号", wcf));
                    sheet.addCell(new Label(1, i, "主险代码", wcf));
                    c = 2;
                }

                sheet.addCell(new Label(0 + c, i, "部门", wcf));
                sheet.addCell(new Label(1 + c, i, "理赔编号", wcf));
                sheet.addCell(new Label(2 + c, i, "被调查人", wcf));

                CellView cellView = new CellView();
                cellView.setHidden(true);
                sheet.addCell(new Label(3 + c, i, "身份证号", wcf));
                sheet.setColumnView(3 + c, cellView);

                sheet.addCell(new Label(4 + c, i, "开票金额", wcf));
                sheet.addCell(new Label(5 + c, i, "是否阳性", wcf));
                sheet.addCell(new Label(6 + c, i, "委托时间", wcf));
                sheet.addCell(new Label(7 + c, i, "终审时间", wcf));
                sheet.addCell(new Label(8 + c, i, "案件时效", wcf));

                sheet.addCell(new Label(9 + c, i, "业务类型", wcf));
                sheet.addCell(new Label(10 + c, i, "省份", wcf));

                sheet.addCell(new Label(11 + c, i, "区域类型", wcf));
                sheet.addCell(new Label(12 + c, i, "案件考核时效", wcf));
                sheet.addCell(new Label(13 + c, i, "超期天数", wcf));

                sheet.addCell(new Label(14 + c, i, "调查要求", wcf));

                sheet.addCell(new Label(15 + c, i, "方向编号", wcf));
                sheet.addCell(new Label(16 + c, i, "调查方向", wcf));
                int j = 17 + c;
                //如果是复星联合健康保险股份有限公司。显示结果与地区类型
                if (entrustOrgId.intValue() == 67) {
                    sheet.addCell(new Label(j++, i, "任务结果", wcf));
                    sheet.addCell(new Label(j++, i, "区域", wcf));
                }
                if (entrustOrgId.intValue() == 45) {//中宏
                    sheet.addCell(new Label(j++, i, "是否有纸质版调查资料", wcf));
                    sheet.addCell(new Label(j++, i, "纸质版调查资料归档以及页数", wcf));
                }
                sheet.addCell(new Label(j++, i, "调查金额", wcf));
                if (entrustOrgId.intValue() == 67) {
                    sheet.addCell(new Label(j++, i, "费用计算", wcf));
                }
                sheet.addCell(new Label(j++, i, "备注", wcf));

            } else {
                sheet.addCell(new Label(0, i, "会员姓名", wcf));
                sheet.addCell(new Label(1, i, "互助案件编号", wcf));
                sheet.addCell(new Label(2, i, "所属月份", wcf));
                sheet.addCell(new Label(3, i, "委托时间", wcf));
                sheet.addCell(new Label(4, i, "完成时间", wcf));
                sheet.addCell(new Label(5, i, "调查内容", wcf));
                sheet.addCell(new Label(6, i, "收费金额", wcf));
                sheet.addCell(new Label(7, i, "备注", wcf));
            }
        } catch (WriteException e) {
            log.error("组装导出excel表头失败 $:", e);
        }
    }

    //export导出 分表-- 具体内容
    private void addEachSheetContent(int z, SurveyRiskCaseInfoExportDto info, WritableSheet sheet, WritableCellFormat wcf, WritableCellFormat wcf4) {
        try {
            int i = 0, i2 = 0, c = 0;
            if (info.getConsignorOrgAttr() == 1) {
                int rowspan = info.getSurveyCaseDirections().size();
                if (info.getEntrustOrgId().intValue() == 52) {
                    sheet.mergeCells(i++, z, i2++, z + rowspan - 1);//跨行
                    sheet.addCell(new Label(c++, z, StringUtils.defaultString(info.getCooperativeCompany()), wcf));

                    sheet.mergeCells(i++, z, i2++, z + rowspan - 1);//跨行
                    sheet.addCell(new Label(c++, z, StringUtils.defaultString(info.getSubsidiaryCompany()), wcf));

                    sheet.mergeCells(i++, z, i2++, z + rowspan - 1);//跨行
                    sheet.addCell(new Label(c++, z, StringUtils.defaultString(info.getHealthInsuranceCompany()), wcf));

                    sheet.mergeCells(i++, z, i2++, z + rowspan - 1);//跨行
                    sheet.addCell(new Label(c++, z, StringUtils.defaultString(info.getTaskNumber()), wcf));
                    i = 4;
                    i2 = 4;
                    c = 4;
                }

                if (info.getEntrustOrgId().intValue() == 67) {
                    sheet.mergeCells(i++, z, i2++, z + rowspan - 1);//跨行

                    SurveyRiskCaseDelegationModeEnum delegationModeEnum = SurveyRiskCaseDelegationModeEnum.getDelegationModeEnum(info.getDelegationMode());
                    sheet.addCell(new Label(c++, z, Objects.isNull(delegationModeEnum) ? "" : delegationModeEnum.getDesc(), wcf));

                    sheet.mergeCells(i++, z, i2++, z + rowspan - 1);//跨行
                    sheet.addCell(new Label(c++, z, StringUtils.defaultString(info.getTransferTypeName(), "首调"), wcf));
                    i = 2;
                    i2 = 2;
                    c = 2;
                }

                if (info.getEntrustOrgId().intValue() == 45) {
                    sheet.mergeCells(i++, z, i2++, z + rowspan - 1);//跨行
                    sheet.addCell(new Label(c++, z, info.getEntrustOrgName(), wcf));

                    sheet.mergeCells(i++, z, i2++, z + rowspan - 1);//跨行
                    sheet.addCell(new Label(c++, z, info.getEntrustUserName(), wcf));
                    i = 2;
                    i2 = 2;
                    c = 2;
                }


                if (info.getEntrustOrgId().intValue() == 131) {
                    sheet.mergeCells(i++, z, i2++, z + rowspan - 1);//跨行
                    sheet.addCell(new Label(c++, z, StringUtils.defaultString(info.getHzContactTel()), wcf));

                    sheet.mergeCells(i++, z, i2++, z + rowspan - 1);//跨行
                    sheet.addCell(new Label(c++, z, StringUtils.defaultString(info.getHzContactName()), wcf));

                    sheet.mergeCells(i++, z, i2++, z + rowspan - 1);//跨行
                    sheet.addCell(new Label(c++, z, StringUtils.defaultString(info.getInsureName()), wcf));
                    i = 3;
                    i2 = 3;
                    c = 3;
                }

                if (info.getEntrustOrgId().intValue() == 200) {
                    sheet.mergeCells(i++, z, i2++, z + rowspan - 1);//跨行
                    sheet.addCell(new Label(c++, z, StringUtils.defaultString(info.getHzContactName()), wcf));

                    sheet.mergeCells(i++, z, i2++, z + rowspan - 1);//跨行
                    sheet.addCell(new Label(c++, z, StringUtils.defaultString(info.getMainInsurance()), wcf));
                    i = 2;
                    i2 = 2;
                    c = 2;
                }

                sheet.mergeCells(0 + i, z, 0 + i2, z + rowspan - 1);//跨行
                sheet.addCell(new Label(0 + c, z, StringUtils.defaultString(info.getDepartmentNameCopy() == null ? info.getDepartmentName() : info.getDepartmentNameCopy()), wcf));


                sheet.mergeCells(1 + i, z, 1 + i2, z + rowspan - 1);//跨行
                sheet.addCell(new Label(1 + c, z, StringUtils.defaultString(info.getClaimsNo()), wcf));
                if (info.getEntrustOrgId().intValue() == 52) {
                    sheet.addCell(new Label(1 + c, z, StringUtils.defaultString(info.getHzContactName()), wcf));
                }
                //中宏、中德：policyNo
                if (info.getConsignorModelId() != null && (info.getConsignorModelId() == 3 || info.getConsignorModelId() == 4)) {
                    sheet.mergeCells(1 + i, z, 1 + i2, z + rowspan - 1);//跨行
                    sheet.addCell(new Label(1 + c, z, StringUtils.defaultString(info.getPolicyNo()), wcf));
                }

                sheet.mergeCells(2 + i, z, 2 + i2, z + rowspan - 1);
                sheet.addCell(new Label(2 + c, z, StringUtils.defaultString(info.getSurveyPerson()), wcf));

                sheet.mergeCells(3 + i, z, 3 + i2, z + rowspan - 1);
                sheet.addCell(new Label(3 + c, z, StringUtils.defaultString(info.getIdCard()), wcf));

                sheet.mergeCells(4 + i, z, 4 + i2, z + rowspan - 1);
                sheet.addCell(new Number(4 + c, z, info.getBillingMoney() == null ? 0D : info.getBillingMoney(), wcf));

                sheet.mergeCells(5 + i, z, 5 + i2, z + rowspan - 1);
                if (info.getIsSun() != null) {
                    int isSun = info.getIsSun();
                    if (isSun == 0) {
                        sheet.addCell(new Label(5 + c, z, "否", wcf));
                    } else if (isSun == 1) {
                        sheet.addCell(new Label(5 + c, z, "是", wcf));
                    }
                } else {
                    sheet.addCell(new Label(5 + c, z, "", wcf));
                }

                sheet.mergeCells(6 + i, z, 6 + i2, z + rowspan - 1);
                if (info.getEntrustTime() != null) {
                    sheet.addCell(new Label(6 + c, z, format.format(info.getEntrustTime()), wcf));
                } else {
                    sheet.addCell(new Label(6 + c, z, "", wcf));
                }

                sheet.mergeCells(7 + i, z, 7 + i2, z + rowspan - 1);
                if (info.getEntrustReportStartDate() != null) {
                    sheet.addCell(new Label(7 + c, z, format.format(info.getEntrustReportStartDate()), wcf));
                } else {
                    sheet.addCell(new Label(7 + c, z, "", wcf));
                }

                //去除工作日的时间
//            int days = GetWorkDay.calLeaveDays(info.getEntrustTime() == null ? new Date() : info.getEntrustTime(), info.getEntrustReportStartDate() == null ? new Date() : info.getEntrustReportStartDate());
//            days = Math.abs(days);
                sheet.mergeCells(8 + i, z, 8 + i2, z + rowspan - 1);
                sheet.addCell(new Label(8 + c, z, info.getEfficiency() + " 天", wcf));//案件时效

                sheet.mergeCells(9 + i, z, 9 + i2, z + rowspan - 1);//业务类型
                if (info.getServicesId() != null) {
                    int servicesId = info.getServicesId().intValue();
                    if (servicesId == 11) {
                        sheet.addCell(new Label(9 + c, z, "契约调查", wcf));
                    } else if (servicesId == 12) {
                        sheet.addCell(new Label(9 + c, z, "单点调查", wcf));
                    } else if (servicesId == 13) {
                        sheet.addCell(new Label(9 + c, z, "深度案件", wcf));
                    }
                } else {
                    sheet.addCell(new Label(9 + c + c, z, "单点调查", wcf));
                }

                sheet.mergeCells(10 + i, z, 10 + i2, z + rowspan - 1);
                sheet.addCell(new Label(10 + c, z, info.getProvinceStr(), wcf));//省份

                sheet.mergeCells(11 + i, z, 11 + i2, z + rowspan - 1);
                if (info.getAreaType() != null) {
                    int areaType = info.getAreaType();
                    if (areaType == 0) {
                        sheet.addCell(new Label(11 + c, z, "直辖市市区", wcf));
                    } else if (areaType == 1) {
                        sheet.addCell(new Label(11 + c, z, "直辖市郊区", wcf));
                    } else if (areaType == 2) {
                        sheet.addCell(new Label(11 + c, z, "省会", wcf));
                    } else if (areaType == 3) {
                        sheet.addCell(new Label(11 + c, z, "地级市", wcf));
                    } else if (areaType == 4) {
                        sheet.addCell(new Label(11 + c, z, "县级市", wcf));
                    }
                } else {
                    sheet.addCell(new Label(11 + c, z, "县级市", wcf));
                }

                sheet.mergeCells(12 + i, z, 12 + i2, z + rowspan - 1);
                sheet.addCell(new Label(12 + c, z, info.getAgingDay() + " 天", wcf));//案件时效

                sheet.mergeCells(13 + i, z, 13 + i2, z + rowspan - 1);
                sheet.addCell(new Label(13 + c, z, info.getOverTimeDay() + " 天", wcf));//案件时效

                sheet.mergeCells(14 + i, z, 14 + i2, z + rowspan - 1);
                sheet.addCell(new Label(14 + c, z, info.getSurveyItem(), wcf));//调查要求

                int iRef = z;
                Map<Double, List<SurveyCaseDirectionDto>> map = new HashMap<>();
                if (info.getSurveyCaseDirections() != null && info.getSurveyCaseDirections().size() > 0) {
                    List<SurveyCaseDirectionDto> directionDtos = info.getSurveyCaseDirections();

                    map = directionDtos.stream().collect(Collectors.groupingBy(SurveyCaseDirectionDto::getEntrustMoney));

                    for (int j = 0; j < directionDtos.size(); j++) {
                        sheet.addCell(new Number(15 + c, z, j + 1, wcf));
                        sheet.addCell(new Label(16 + c, z, directionDtos.get(j).getDirectionName() == null ? "" : directionDtos.get(j).getDirectionName(), wcf));//调查方向
                        int k = 17 + c;
                        if (info.getEntrustOrgId().intValue() == 67) {
                            String name = directionDtos.get(j).getDirectionResultTypeName();
                            if (info.getServicesId().intValue() != 13) {
                                if (directionDtos.get(j).getMedicalNumber() == null) {
                                    directionDtos.get(j).setMedicalNumber(0);
                                }
                                if (directionDtos.get(j).getMedicalNumber() > 1) {
                                    name = name + "(" + directionDtos.get(j).getMedicalNumber() + "份病历)";
                                }
                            }
                            sheet.addCell(new Label(k++, z, name, wcf));//任务结果
                            int areaType = directionDtos.get(j).getAreaType() == null ? 4 : directionDtos.get(j).getAreaType();
                            if (areaType == 0) {
                                sheet.addCell(new Label(k++, z, "直辖市市区", wcf));//区域
                            } else if (areaType == 1) {
                                sheet.addCell(new Label(k++, z, "直辖市郊区", wcf));//区域
                            } else if (areaType == 2) {
                                sheet.addCell(new Label(k++, z, "省会", wcf));//区域
                            } else if (areaType == 3) {
                                sheet.addCell(new Label(k++, z, "地级市", wcf));//区域
                            } else if (areaType == 4) {
                                sheet.addCell(new Label(k++, z, "县级市", wcf));//区域
                            }
                        }
                        if (info.getEntrustOrgId().intValue() == 45){
                            Integer materRaw = directionDtos.get(j).getMaterRaw();
                            Integer materRawNumber = directionDtos.get(j).getMaterRawNumber();
                            sheet.addCell(new Label(k++, z, materRaw != null && materRaw == 1 ? "是" : "否", wcf));//
                            sheet.addCell(new Label(k++, z, materRawNumber != null && materRawNumber > 0 ? materRawNumber + "页" : "", wcf));//
                        }
                        sheet.addCell(new Label(k++, z, directionDtos.get(j).getEntrustMoney() == null ? "" : directionDtos.get(j).getEntrustMoney() + "元", wcf));//案件时效
                        z = z + 1;
                    }
                } else {
                    sheet.addCell(new Label(15 + c, z, "", wcf));
                    sheet.addCell(new Label(16 + c, z, "", wcf));//调查方向
                    int k = 17 + c;
                    if (info.getEntrustOrgId().intValue() == 67) {
                        sheet.addCell(new Label(k++, z, "", wcf));//
                        sheet.addCell(new Label(k++, z, "", wcf));//
                    }
                    if (info.getEntrustOrgId().intValue() == 45) {
                        sheet.addCell(new Label(k++, z, "", wcf));//
                        sheet.addCell(new Label(k++, z, "", wcf));//
                    }
                    sheet.addCell(new Label(k++, z, "", wcf));//
                    z = z + 1;
                }
                int m = 18 + c;
                if (info.getEntrustOrgId().intValue() == 67) {
                    m = 20 + c;
                }
                if (info.getEntrustOrgId().intValue() == 45) {
                    m = 20 + c;
                }
                if (info.getEntrustOrgId().intValue() == 67) {
                    sheet.mergeCells(m, iRef, m, iRef + rowspan - 1);
                    StringBuilder strMsg = new StringBuilder();
                    map.forEach((k, v) -> {
                        strMsg.append(k).append("*").append(v.size()).append("+");
                    });
                    String str = "";
                    if (strMsg.length() > 0) {
                        str = strMsg.substring(0, strMsg.length() - 1);
                    }
                    sheet.addCell(new Label(m, iRef, str, wcf4));
                    m = m + 1;
                }

                sheet.mergeCells(m, iRef, m, iRef + rowspan - 1);
                sheet.addCell(new Label(m, iRef, info.getRemarkAll() == null ? "" : info.getRemarkAll(), wcf4));
            } else {
                sheet.addCell(new Label(0 + c, z, info.getSurveyPerson() == null ? "" : info.getSurveyPerson(), wcf));
                sheet.addCell(new Label(1 + c, z, info.getClaimsNo() == null ? "" : info.getClaimsNo(), wcf));

                int month = info.getEntrustReportEndDate().getMonth();
                sheet.addCell(new Label(2 + c, z, (month + 1) + "月", wcf));
                if (info.getEntrustTime() != null) {
                    sheet.addCell(new Label(3 + c, z, format.format(info.getEntrustTime()), wcf));
                } else {
                    sheet.addCell(new Label(3 + c, z, "", wcf));
                }
                if (info.getEntrustReportEndDate() != null) {
                    sheet.addCell(new Label(4 + c, z, format.format(info.getEntrustReportEndDate()), wcf));
                } else {
                    sheet.addCell(new Label(4 + c, z, "", wcf));
                }
                sheet.addCell(new Label(5 + c, z, info.getDirectionNameStrs() == null ? "" : info.getDirectionNameStrs(), wcf));
                sheet.addCell(new Label(6 + c, z, info.getEntrustMoneyCount() == null ? "" : info.getEntrustMoneyCount(), wcf));
                sheet.addCell(new Label(7 + c, z, info.getRemarkAll() == null ? "" : info.getRemarkAll(), wcf4));
                z = z + 1;
            }
        } catch (WriteException e) {
            e.printStackTrace();
        }

    }

    private void createBook(List<SurveyRiskCaseInfoExportDto> surveyRiskCaseInfoExportDtos, HttpServletResponse rsp) {
        // 创建excel
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename = "总表.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "ISO8859-1"));
//            rsp.addHeader("Content-Length",String.valueOf(jsonResult.getBytes().length));
//            rsp.addHeader("Content-disposition","attachment;filename=\"" + new String(filename.getBytes("UTF-8"), "ISO8859_1") + "\"");

            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页

            WritableSheet sheet = book.createSheet("总表", 0);

            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            WritableCellFormat wcfColour = new WritableCellFormat();
            wcfColour.setBackground(Colour.BLUE_GREY);
            sheet.setColumnView(0, 15);// 将第一列的宽度设为30
            sheet.setColumnView(1, 15);
            sheet.setColumnView(2, 15);
            sheet.setColumnView(3, 35);
            sheet.setColumnView(4, 35);
            sheet.setColumnView(5, 20);
            sheet.setColumnView(6, 20);
            sheet.setColumnView(7, 15);
            sheet.setColumnView(8, 15);
            sheet.setColumnView(9, 15);
            sheet.setColumnView(10, 15);
            sheet.setColumnView(11, 15);
            sheet.setColumnView(12, 15);
            sheet.setColumnView(13, 15);
            sheet.setColumnView(14, 40);
            sheet.setColumnView(15, 20);
            sheet.setColumnView(16, 100);
            sheet.setColumnView(17, 80);
            sheet.setColumnView(18, 80);//
            sheet.setColumnView(19, 80);//
            sheet.setColumnView(20, 80);//
            sheet.setColumnView(21, 80);//

            //隐藏列
            CellView cellView = new CellView();
            cellView.setHidden(true);
//            sheet.setColumnView(16,cellView);//调查要求
//            sheet.setColumnView(21,cellView);//身份证列


            WritableFont wf = new WritableFont(WritableFont.TIMES);
            wf.setColour(Colour.BLACK);
            wf.setPointSize(12);
            //表头
            WritableFont wf2 = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
            WritableCellFormat wcf2 = new WritableCellFormat(wf2);
            wcf2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf2.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            //具体内容
            wf = new WritableFont(WritableFont.TIMES);
            WritableCellFormat wcf3 = new WritableCellFormat(wf);
            wcf3.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf3.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf3.setWrap(true);

            wf = new WritableFont(WritableFont.TIMES);
            WritableCellFormat wcf4 = new WritableCellFormat(wf);
            wcf4.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf4.setAlignment(Alignment.LEFT);
            wcf4.setWrap(true);

            if (surveyRiskCaseInfoExportDtos != null && !surveyRiskCaseInfoExportDtos.isEmpty()) {
                //添加表头
                addSheetHeader(sheet, wcf2, 0);

                //添加内容
                addSheetContent(1, surveyRiskCaseInfoExportDtos, sheet, wcf3, wcf4);

            }

            // 写入数据并关闭文件
            book.write();
            book.close();

            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (book != null) {
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //export导出 --创建表头
    private void addSheetHeader(WritableSheet sheet, WritableCellFormat wcf, int i) {
        try {
            int index = -1;
            sheet.addCell(new Label(++index, i, "编号", wcf));
            sheet.addCell(new Label(++index, i, "理赔编号", wcf));
            sheet.addCell(new Label(++index, i, "被调查人", wcf));
            sheet.addCell(new Label(++index, i, "委托人机构名称", wcf));
            sheet.addCell(new Label(++index, i, "委托人部门", wcf));
            sheet.addCell(new Label(++index, i, "委托人名称", wcf));
            sheet.addCell(new Label(++index, i, "开票金额", wcf));
            sheet.addCell(new Label(++index, i, "是否阳性", wcf));
            sheet.addCell(new Label(++index, i, "委托时间", wcf));
            sheet.addCell(new Label(++index, i, "终审时间", wcf));
            sheet.addCell(new Label(++index, i, "案件时效", wcf));
            sheet.addCell(new Label(++index, i, "业务类型", wcf));
            sheet.addCell(new Label(++index, i, "省份", wcf));
            sheet.addCell(new Label(++index, i, "区域类别", wcf));
            sheet.addCell(new Label(++index, i, "案件考核时效", wcf));
            sheet.addCell(new Label(++index, i, "超期天数", wcf));
            sheet.addCell(new Label(++index, i, "调查要求", wcf));
            sheet.addCell(new Label(++index, i, "备注", wcf));
            sheet.addCell(new Label(++index, i, "身份证号", wcf));
            sheet.addCell(new Label(++index, i, "方向编号", wcf));
            sheet.addCell(new Label(++index, i, "调查方向", wcf));
            sheet.addCell(new Label(++index, i, "调查金额", wcf));

        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    //export导出 -- 具体内容
    private void addSheetContent(int z, List<SurveyRiskCaseInfoExportDto> infos, WritableSheet sheet, WritableCellFormat wcf, WritableCellFormat wcf4) {
        try {
            int index = 1;
            for (SurveyRiskCaseInfoExportDto info : infos) {
                int rowspan = info.getSurveyCaseDirections().size();
                sheet.mergeCells(0, z, 0, z + rowspan - 1);//跨行
                sheet.addCell(new Number(0, z, index, wcf));

                sheet.mergeCells(1, z, 1, z + rowspan - 1);//跨行
                sheet.addCell(new Label(1, z, info.getClaimsNo() == null ? "" : info.getClaimsNo(), wcf));
                //中宏、中德：policyNo
                if (info.getConsignorModelId() != null && (info.getConsignorModelId() == 3 || info.getConsignorModelId() == 4)) {
                    sheet.mergeCells(1, z, 1, z + rowspan - 1);//跨行
                    sheet.addCell(new Label(1, z, info.getPolicyNo() == null ? "" : info.getPolicyNo(), wcf));
                }

                sheet.mergeCells(2, z, 2, z + rowspan - 1);
                sheet.addCell(new Label(2, z, info.getSurveyPerson() == null ? "" : info.getSurveyPerson(), wcf));

                sheet.mergeCells(3, z, 3, z + rowspan - 1);
                sheet.addCell(new Label(3, z, info.getEntrustOrgName() == null ? "" : info.getEntrustOrgName(), wcf));

                sheet.mergeCells(4, z, 4, z + rowspan - 1);
                sheet.addCell(new Label(4, z, info.getDepartmentName() == null ? "" : info.getDepartmentName(), wcf));

                sheet.mergeCells(5, z, 5, z + rowspan - 1);
                sheet.addCell(new Label(5, z, info.getEntrustUserName() == null ? "" : info.getEntrustUserName(), wcf));


                sheet.mergeCells(6, z, 6, z + rowspan - 1);
//                sheet.addCell(new Number(5, z, info.getBillingMoney() == null ? 0D : info.getBillingMoney(),wcf));
                sheet.addCell(new Number(6, z, info.getEntrustOkPrice1() == null ? 0D : info.getEntrustOkPrice1(), wcf));

                sheet.mergeCells(7, z, 7, z + rowspan - 1);
                if (info.getIsSun() != null) {
                    int isSun = info.getIsSun();
                    if (isSun == 0) {
                        sheet.addCell(new Label(7, z, "否", wcf));
                    } else if (isSun == 1) {
                        sheet.addCell(new Label(7, z, "是", wcf));
                    }
                } else {
                    sheet.addCell(new Label(7, z, "", wcf));
                }

                sheet.mergeCells(8, z, 8, z + rowspan - 1);
                if (info.getEntrustTime() != null) {
                    sheet.addCell(new Label(8, z, format.format(info.getEntrustTime()), wcf));
                } else {
                    sheet.addCell(new Label(8, z, "", wcf));
                }

                sheet.mergeCells(9, z, 9, z + rowspan - 1);
                if (info.getEntrustReportStartDate() != null) {
                    sheet.addCell(new Label(9, z, format.format(info.getEntrustReportStartDate()), wcf));
                } else {
                    sheet.addCell(new Label(9, z, "", wcf));
                }

                //去除工作日的时间
//                int days = GetWorkDay.calLeaveDays(info.getEntrustTime()==null?new Date():info.getEntrustTime(),info.getEntrustReportStartDate()==null?new Date():info.getEntrustReportStartDate());
//                days = Math.abs(days);
                sheet.mergeCells(10, z, 10, z + rowspan - 1);
                sheet.addCell(new Label(10, z, info.getEfficiency() + " 天", wcf));//案件时效

                sheet.mergeCells(11, z, 11, z + rowspan - 1);//业务类型
                if (info.getServicesId() != null) {
                    int servicesId = info.getServicesId().intValue();
                    if (servicesId == 11) {
                        sheet.addCell(new Label(11, z, "契约调查", wcf));
                    } else if (servicesId == 12) {
                        sheet.addCell(new Label(11, z, "单点调查", wcf));
                    } else if (servicesId == 13) {
                        sheet.addCell(new Label(11, z, "深度案件", wcf));
                    }
                } else {
                    sheet.addCell(new Label(11, z, "单点调查", wcf));
                }

                sheet.mergeCells(12, z, 12, z + rowspan - 1);
                sheet.addCell(new Label(12, z, info.getProvinceStr(), wcf));//省份

                sheet.mergeCells(13, z, 13, z + rowspan - 1);
                if (info.getAreaType() != null) {
                    int areaType = info.getAreaType();
                    if (areaType == 0) {
                        sheet.addCell(new Label(13, z, "直辖市市区", wcf));
                    } else if (areaType == 1) {
                        sheet.addCell(new Label(13, z, "直辖市郊区", wcf));
                    } else if (areaType == 2) {
                        sheet.addCell(new Label(13, z, "省会", wcf));
                    } else if (areaType == 3) {
                        sheet.addCell(new Label(13, z, "地级市", wcf));
                    } else if (areaType == 4) {
                        sheet.addCell(new Label(13, z, "县级市", wcf));
                    }
                } else {
                    sheet.addCell(new Label(13, z, "县级市", wcf));
                }

                sheet.mergeCells(14, z, 14, z + rowspan - 1);
                sheet.addCell(new Label(14, z, info.getAgingDay() + " 天", wcf));//案件时效

                sheet.mergeCells(15, z, 15, z + rowspan - 1);
                sheet.addCell(new Label(15, z, info.getOverTimeDay() + " 天", wcf));//案件时效

                sheet.mergeCells(16, z, 16, z + rowspan - 1);
                sheet.addCell(new Label(16, z, info.getSurveyItem(), wcf));//调查要求


                sheet.mergeCells(17, z, 17, z + rowspan - 1);
                sheet.addCell(new Label(17, z, info.getRemarkAll() == null ? "" : info.getRemarkAll(), wcf4));

                sheet.mergeCells(18, z, 18, z + rowspan - 1);
                sheet.addCell(new Label(18, z, info.getIdCard() == null ? "" : info.getIdCard(), wcf4));

                if (info.getSurveyCaseDirections() != null && info.getSurveyCaseDirections().size() > 0) {
                    List<SurveyCaseDirectionDto> directionDtos = info.getSurveyCaseDirections();
                    for (int j = 0; j < directionDtos.size(); j++) {
                        sheet.addCell(new Number(19, z, j + 1, wcf));
                        sheet.addCell(new Label(20, z, directionDtos.get(j).getDirectionName() == null ? "" : directionDtos.get(j).getDirectionName(), wcf));//调查方向
                        sheet.addCell(new Label(21, z, directionDtos.get(j).getEntrustMoney() == null ? "" : directionDtos.get(j).getEntrustMoney() + "元", wcf));//案件时效
                        z = z + 1;
                    }
                } else {
                    sheet.addCell(new Label(19, z, "", wcf));
                    sheet.addCell(new Label(20, z, "", wcf));//调查方向
                    sheet.addCell(new Label(21, z, "", wcf));//案件时效
                    z = z + 1;
                }

                index++;
            }

        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    //分派调查员，查询对应的任务类型
    @RequestMapping(value = "/selectTaskByUserId")
    public String selectTaskByUserId(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String userIds = req.getParameter("userId");
        if (userIds != null && userIds != "") {
            Long userId = Long.parseLong(req.getParameter("userId"));
            appendMap.put("userId", userId);
        }
        Long surveyInfoId = Long.parseLong(req.getParameter("surveyInfoId"));
        appendMap.put("surveyInfoId", surveyInfoId);
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_TASK_BY_USER_ID, appendMap, req, rsp);
    }

    //调查方向-上传图片
    @RequestMapping(value = "/directionFileMidOK")
    public String directionFileMidOK(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> param = new HashMap<>();
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_DIRECTION_FILE_UPLOAD, param, req, rsp);

//        String json = this.callApi(BackendApiMethodEnum.BACKEND_SURVEY_CASE_DIRECTION_FILE_UPLOAD, param, req);
//        req.getSession().setAttribute("json",json);
//        return WebHelper.outputJson(json, rsp);

//        String retJson = "";
//        String json = this.callApi(BackendApiMethodEnum.BACKEND_SURVEY_CASE_DIRECTION_FILE_UPLOAD, param, req);
//        Type type = new TypeToken<ApiFinalResponse<List<CommonFile>>>() {
//        }.getType();
//        ApiFinalResponse<List<CommonFile>> apiRsp = JsonUtil.jsonToObject(json, type);
//
//        retJson = JsonUtil.objectToJson(apiRsp.getResults());
//
//        return WebHelper.outputJson(retJson, rsp);

    }

    private void exportAssign(List<SurveyRiskCaseInfoExportDto> surveyRiskCaseInfoExportDtos, HttpServletResponse rsp, String entrustStateTime, String entrustEndTime) {
        // 创建excel
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename = "新建案件（" + entrustStateTime + "-" + entrustEndTime + "）.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页

            WritableSheet sheet = book.createSheet("新建案件", 0);

            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            WritableCellFormat wcfColour = new WritableCellFormat();
            wcfColour.setBackground(Colour.BLUE_GREY);
            sheet.setColumnView(0, 15);// 将第一列的宽度设为30
            sheet.setColumnView(1, 35);// 将第一列的宽度设为30
            sheet.setColumnView(2, 15);
            sheet.setColumnView(3, 15);
            sheet.setColumnView(4, 15);
            sheet.setColumnView(5, 15);
            sheet.setColumnView(6, 40);

            WritableFont wf = new WritableFont(WritableFont.TIMES);
            wf.setColour(Colour.BLACK);
            wf.setPointSize(12);

            //表头
            WritableFont wf2 = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
            WritableCellFormat wcf2 = new WritableCellFormat(wf2);
            wcf2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf2.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            //具体内容
            wf = new WritableFont(WritableFont.TIMES);
            WritableCellFormat wcf3 = new WritableCellFormat(wf);
            wcf3.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf3.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中

            if (surveyRiskCaseInfoExportDtos != null && !surveyRiskCaseInfoExportDtos.isEmpty()) {
                //添加表头
                sheet.addCell(new Label(0, 0, "编号", wcf));
                sheet.addCell(new Label(1, 0, "保险公司", wcf));
                sheet.addCell(new Label(2, 0, "被调查人", wcf));
                sheet.addCell(new Label(3, 0, "委托时间", wcf));
                sheet.addCell(new Label(4, 0, "案件截至时间", wcf));
                sheet.addCell(new Label(5, 0, "业务类型", wcf));
                sheet.addCell(new Label(6, 0, "调查机构", wcf));

                //添加内容
                int z = 1;
                for (int i = 0; i < surveyRiskCaseInfoExportDtos.size(); i++) {
                    int rowspan = surveyRiskCaseInfoExportDtos.get(i).getSurveyAssignOrgs().size();

                    sheet.mergeCells(0, z, 0, z + rowspan - 1);
                    sheet.addCell(new Label(0, z, "" + (i + 1), wcf));

                    sheet.mergeCells(1, z, 1, z + rowspan - 1);
                    sheet.addCell(new Label(1, z, surveyRiskCaseInfoExportDtos.get(i).getEntrustOrgName(), wcf));

                    sheet.mergeCells(2, z, 2, z + rowspan - 1);
                    sheet.addCell(new Label(2, z, surveyRiskCaseInfoExportDtos.get(i).getSurveyPerson(), wcf));

                    sheet.mergeCells(3, z, 3, z + rowspan - 1);
                    if (surveyRiskCaseInfoExportDtos.get(i).getEntrustTime() != null) {
//                        sheet.addCell(new DateTime(2, z, surveyRiskCaseInfoExportDtos.get(i).getEntrustTime()));
                        sheet.addCell(new Label(3, z, format.format(surveyRiskCaseInfoExportDtos.get(i).getEntrustTime())));
                    } else {
                        sheet.addCell(new Label(3, z, null));
                    }


                    sheet.mergeCells(4, z, 4, z + rowspan - 1);
                    if (surveyRiskCaseInfoExportDtos.get(i).getEndTime() != null) {
//                        sheet.addCell(new DateTime(2, z, surveyRiskCaseInfoExportDtos.get(i).getEntrustTime()));
                        sheet.addCell(new Label(4, z, format.format(surveyRiskCaseInfoExportDtos.get(i).getEndTime())));
                    } else {
                        sheet.addCell(new Label(4, z, null));
                    }


                    sheet.mergeCells(5, z, 5, z + rowspan - 1);
                    sheet.addCell(new Label(5, z, surveyRiskCaseInfoExportDtos.get(i).getServicesName(), wcf));

                    if (surveyRiskCaseInfoExportDtos.get(i).getSurveyAssignOrgs() != null && surveyRiskCaseInfoExportDtos.get(i).getSurveyAssignOrgs().size() > 0) {
                        List<SurveyAssignOrgDto> assignOrgDtos = surveyRiskCaseInfoExportDtos.get(i).getSurveyAssignOrgs();
                        for (int j = 0; j < assignOrgDtos.size(); j++) {
                            sheet.addCell(new Label(6, z, assignOrgDtos.get(j).getSurveyOrgName() == null ? "" : assignOrgDtos.get(j).getSurveyOrgName(), wcf));
                            z = z + 1;
                        }
                    } else {
                        sheet.addCell(new Label(6, z, "", wcf));
                        z = z + 1;
                    }
                }
            }

            // 写入数据并关闭文件
            book.write();
            book.close();

            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (book != null) {
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //按查询条件导出案件分派
    private void exportAssignmentConditions(List<SurveyRiskCaseInfoDtoExport> surveyRiskCaseInfoDtoExport, HttpServletResponse rsp, String entrustStateTime, String entrustEndTime) {
        // 创建excel
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename = "新建案件（" + entrustStateTime + "-" + entrustEndTime + "）.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页

            WritableSheet sheet = book.createSheet("新建案件", 0);

            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            WritableCellFormat wcfColour = new WritableCellFormat();
            wcfColour.setBackground(Colour.BLUE_GREY);
            sheet.setColumnView(0, 15);// 将第一列的宽度设为30
            sheet.setColumnView(1, 35);// 将第一列的宽度设为30
            sheet.setColumnView(2, 15);
            sheet.setColumnView(3, 15);
            sheet.setColumnView(4, 15);
            sheet.setColumnView(5, 40);
            sheet.setColumnView(6, 40);
            sheet.setColumnView(7, 40);
            sheet.setColumnView(8, 40);
            sheet.setColumnView(9, 40);
            sheet.setColumnView(10, 40);

            WritableFont wf = new WritableFont(WritableFont.TIMES);
            wf.setColour(Colour.BLACK);
            wf.setPointSize(12);

            //表头
            WritableFont wf2 = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
            WritableCellFormat wcf2 = new WritableCellFormat(wf2);
            wcf2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf2.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            //具体内容
            wf = new WritableFont(WritableFont.TIMES);
            WritableCellFormat wcf3 = new WritableCellFormat(wf);
            wcf3.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf3.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中

            if (surveyRiskCaseInfoDtoExport != null && !surveyRiskCaseInfoDtoExport.isEmpty()) {
                //添加表头
                sheet.addCell(new Label(0, 0, "序号", wcf));
                sheet.addCell(new Label(1, 0, "案件编号", wcf));
                sheet.addCell(new Label(2, 0, "调查编号", wcf));
                sheet.addCell(new Label(3, 0, "被调查人", wcf));
                sheet.addCell(new Label(4, 0, "联系方式", wcf));
                sheet.addCell(new Label(5, 0, "理赔申请金额", wcf));
                sheet.addCell(new Label(6, 0, "领域", wcf));
                sheet.addCell(new Label(7, 0, "案件阶段", wcf));
                sheet.addCell(new Label(8, 0, "分派机构状态", wcf));
                sheet.addCell(new Label(9, 0, "分派调查员状态", wcf));
                sheet.addCell(new Label(10, 0, "保险公司", wcf));
                sheet.addCell(new Label(11, 0, "委托时间", wcf));
                sheet.addCell(new Label(12, 0, "创建时间", wcf));
            }
            //添加内容
            if (surveyRiskCaseInfoDtoExport != null && !surveyRiskCaseInfoDtoExport.isEmpty()) {
                for (int i = 0; i < surveyRiskCaseInfoDtoExport.size(); i++) {
                    sheet.addCell(new Label(0, i + 1, String.valueOf(i + 1), wcf));
                    sheet.addCell(new Label(1, i + 1, surveyRiskCaseInfoDtoExport.get(i).getSurveyCaseNo(), wcf));
                    sheet.addCell(new Label(2, i + 1, surveyRiskCaseInfoDtoExport.get(i).getSurveyNo(), wcf));
                    sheet.addCell(new Label(3, i + 1, surveyRiskCaseInfoDtoExport.get(i).getSurveyPerson(), wcf));
                    sheet.addCell(new Label(4, i + 1, surveyRiskCaseInfoDtoExport.get(i).getSurveryPersonTel(), wcf));
                    sheet.addCell(new Label(5, i + 1, surveyRiskCaseInfoDtoExport.get(i).getClaimsMoney(), wcf));
                    sheet.addCell(new Label(6, i + 1, surveyRiskCaseInfoDtoExport.get(i).getSurveyBusName(), wcf));
                    if (surveyRiskCaseInfoDtoExport.get(i).getSurveyPhase() != null && surveyRiskCaseInfoDtoExport.get(i).getSurveyPhase() == 1) {
                        sheet.addCell(new Label(7, i + 1, "委托阶段", wcf));
                    } else if (surveyRiskCaseInfoDtoExport.get(i).getSurveyPhase() != null && surveyRiskCaseInfoDtoExport.get(i).getSurveyPhase() == 2) {
                        sheet.addCell(new Label(7, i + 1, "调查阶段", wcf));
                    } else if (surveyRiskCaseInfoDtoExport.get(i).getSurveyPhase() != null && surveyRiskCaseInfoDtoExport.get(i).getSurveyPhase() == 3) {
                        sheet.addCell(new Label(7, i + 1, "已结案", wcf));
                    } else {
                        sheet.addCell(new Label(7, i + 1, "", wcf));
                    }
                    if (surveyRiskCaseInfoDtoExport.get(i).getOrgAssign() != null && surveyRiskCaseInfoDtoExport.get(i).getOrgAssign() != 1) {
                        sheet.addCell(new Label(8, i + 1, "未分派", wcf));
                    } else if (surveyRiskCaseInfoDtoExport.get(i).getOrgAssign() != null && surveyRiskCaseInfoDtoExport.get(i).getOrgAssign() == 1) {
                        sheet.addCell(new Label(8, i + 1, "已分派", wcf));
                    }
                    if (surveyRiskCaseInfoDtoExport.get(i).getAssignState() != null && surveyRiskCaseInfoDtoExport.get(i).getAssignState() == 0) {
                        sheet.addCell(new Label(9, i + 1, "未分派", wcf));
                    } else if (surveyRiskCaseInfoDtoExport.get(i).getAssignState() != null && surveyRiskCaseInfoDtoExport.get(i).getAssignState() == 2) {
                        sheet.addCell(new Label(9, i + 1, "已分派", wcf));
                    } else {
                        sheet.addCell(new Label(9, i + 1, "", wcf));
                    }
                    sheet.addCell(new Label(10, i + 1, surveyRiskCaseInfoDtoExport.get(i).getEntrustOrgName(), wcf));
                    sheet.addCell(new Label(11, i + 1, surveyRiskCaseInfoDtoExport.get(i).getEntrustTime(), wcf));
                    sheet.addCell(new Label(12, i + 1, surveyRiskCaseInfoDtoExport.get(i).getCreateTime(), wcf));
                }
            }
            // 写入数据并关闭文件
            book.write();
            book.close();

            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (book != null) {
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void createSurveyMoneyBook(List<SurveyMoneyDtoDetail> surveyMoneyDtoDetails, HttpServletResponse rsp, String excelPath, int index, boolean isNewBook, int month, int year) {

        // 创建excel
        WritableWorkbook book = null; // 创建jxl工作簿
        SurveyMoneyDtoDetail dto = surveyMoneyDtoDetails.get(index);
        String filename = dto.getOrgName() + "-" + year + "年" + month + "月" + "调查费用清单.xls";
        try {
            rsp.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            String path = excelPath + File.separator + filename;
            File file = new File(path);
            book = Workbook.createWorkbook(file);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页

            WritableSheet sheet = book.createSheet(dto.getOrgName() + "-" + year + "年" + month + "月" + "表", 0);

            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            WritableCellFormat wcfColour = new WritableCellFormat();
            wcfColour.setBackground(Colour.BLUE_GREY);
            sheet.setColumnView(0, 15);// 将第一列的宽度设为30
            sheet.setColumnView(1, 35);
            sheet.setColumnView(2, 15);
            sheet.setColumnView(3, 15);
            sheet.setColumnView(4, 20);
            sheet.setColumnView(5, 15);
            sheet.setColumnView(6, 20);
            sheet.setColumnView(7, 15);
            sheet.setColumnView(8, 80);

            WritableFont wf = new WritableFont(WritableFont.TIMES);
            wf.setColour(Colour.BLACK);
            wf.setPointSize(12);

            //标题
            WritableFont wf1 = new WritableFont(WritableFont.ARIAL, 13, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
            WritableCellFormat wcf1 = new WritableCellFormat(wf1);
            wcf1.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf1.setAlignment(Alignment.LEFT);//把水平对齐方式指定为居中
            wcf1.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            //表头
            WritableFont wf2 = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
            WritableCellFormat wcf2 = new WritableCellFormat(wf2);
            wcf2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf2.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            //具体内容
            wf = new WritableFont(WritableFont.TIMES);
            WritableCellFormat wcf3 = new WritableCellFormat(wf);
            wcf3.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf3.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            //底部
            WritableFont wf4 = new WritableFont(WritableFont.ARIAL, 13, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
            WritableCellFormat wcf4 = new WritableCellFormat(wf4);
            wcf4.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf4.setAlignment(Alignment.LEFT);
            wcf4.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            wf = new WritableFont(WritableFont.TIMES);
            WritableCellFormat wcf5 = new WritableCellFormat(wf);
            wcf5.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf5.setAlignment(Alignment.LEFT);
            wcf5.setWrap(true);
            //表头
            String title = dto.getOrgName() + "-" + year + "年" + month + "月" + "调查费用清单";
            sheet.mergeCells(0, 0, 8, 0);//
            sheet.addCell(new Label(0, 0, title, wcf1));
            addSurveyMoneySheetHeader(sheet, wcf2, 1);

            int z = 2;//合并单元格的行数
            Double surveyMoneySum = 0D;//单个机构下 调查费金额
            for (int i = index; i < surveyMoneyDtoDetails.size(); i++) {
                SurveyMoneyDtoDetail thisInfo = surveyMoneyDtoDetails.get(i);
                if (i == 0) {
                    addSurveyMoneySheetContent(z, thisInfo, sheet, wcf3, wcf5);//具体内容
                    z = z + 1;
                    surveyMoneySum = surveyMoneySum + (thisInfo.getMoney() == null ? 0D : thisInfo.getMoney());
                } else {
                    if (isNewBook) {
                        //添加内容
                        addSurveyMoneySheetContent(z, thisInfo, sheet, wcf3, wcf5);
                        z = z + 1;
                        surveyMoneySum = surveyMoneySum + (thisInfo.getMoney() == null ? 0D : thisInfo.getMoney());
                        isNewBook = false;
                    } else {
                        SurveyMoneyDtoDetail lastInfo = surveyMoneyDtoDetails.get(i - 1);
                        if (thisInfo.getParentOrgId().equals(lastInfo.getParentOrgId())) {
                            //添加内容
                            addSurveyMoneySheetContent(z, thisInfo, sheet, wcf3, wcf5);
                            z = z + 1;
                            surveyMoneySum = surveyMoneySum + (thisInfo.getMoney() == null ? 0D : thisInfo.getMoney());

                        } else {
                            sheet.mergeCells(0, z, 8, 0);
                            String lowName = "总金额" + surveyMoneySum + "元";
                            sheet.addCell(new Label(0, z, lowName, wcf4));
                            // 写入数据并关闭文件
                            if (book != null) {
                                book.write();
                                book.close();
                            }
                            //创建新的excel
                            if (i < surveyMoneyDtoDetails.size()) {
                                createSurveyMoneyBook(surveyMoneyDtoDetails, rsp, excelPath, i, true, month, year);
                            }
                        }
                    }
                }
            }
            sheet.mergeCells(0, z, 8, 0);
            String lowName = "总金额" + surveyMoneySum + "元";
            sheet.addCell(new Label(0, z, lowName, wcf4));
            // 写入数据并关闭文件
            if (book != null) {
                book.write();
                book.close();
            }
            //下载zip
            FileZipUtil.createZip(excelPath, excelPath + ".zip");

            String url = excelPath + ".zip";
            File downLoadFile = new File(url);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(downLoadFile));

            rsp.reset();
            BufferedOutputStream out = new BufferedOutputStream(rsp.getOutputStream());

            byte[] buff = new byte[2048];
            int length = 0;
            while ((length = in.read(buff)) > 0) {
                out.write(buff, 0, length);
                out.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }

    //调查费用清单export导出 分表 --创建表头
    private void addSurveyMoneySheetHeader(WritableSheet sheet, WritableCellFormat wcf, int i) {
        try {
            sheet.addCell(new Label(0, i, "调查机构", wcf));
            sheet.addCell(new Label(1, i, "被调查人", wcf));
            sheet.addCell(new Label(2, i, "委托公司", wcf));
            sheet.addCell(new Label(3, i, "分派机构时间", wcf));
            sheet.addCell(new Label(4, i, "机构提交时间", wcf));
            sheet.addCell(new Label(5, i, "机构时效（天）", wcf));
            sheet.addCell(new Label(6, i, "区域类别", wcf));
            sheet.addCell(new Label(7, i, "考核时效（天）", wcf));
            sheet.addCell(new Label(8, i, "调查金额", wcf));
            sheet.addCell(new Label(9, i, "备注", wcf));

        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    //调查费用清单 export导出 分表-- 具体内容
    private void addSurveyMoneySheetContent(int z, SurveyMoneyDtoDetail info, WritableSheet sheet, WritableCellFormat wcf, WritableCellFormat wcf5) {
        try {
            sheet.addCell(new Label(0, z, info.getOrgName(), wcf));
            sheet.addCell(new Label(1, z, info.getSurveyPerson() == null ? "" : info.getSurveyPerson(), wcf));
            sheet.addCell(new Label(2, z, info.getEntrustOrgName() == null ? "" : info.getEntrustOrgName(), wcf));

            if (info.getOrgCreateTimeStr() != null) {
                sheet.addCell(new Label(3, z, info.getOrgCreateTimeStr()));
            } else {
                sheet.addCell(new Label(3, z, ""));
            }

            if (info.getOrgStartTimeStr() != null) {
                sheet.addCell(new Label(4, z, info.getOrgStartTimeStr()));
            } else {
                sheet.addCell(new Label(4, z, ""));
            }
            sheet.addCell(new Number(5, z, info.getOrgDays1(), wcf));


//            sheet.addCell(new Number(5, z, info.getAreaType(),wcf));
            if (info.getAreaType() != null) {
                int areaType = info.getAreaType();
                if (areaType == 0) {
                    sheet.addCell(new Label(6, z, "直辖市市区", wcf));
                } else if (areaType == 1) {
                    sheet.addCell(new Label(6, z, "直辖市郊区", wcf));
                } else if (areaType == 2) {
                    sheet.addCell(new Label(6, z, "省会", wcf));
                } else if (areaType == 3) {
                    sheet.addCell(new Label(6, z, "地级市", wcf));
                } else if (areaType == 4) {
                    sheet.addCell(new Label(6, z, "县级市", wcf));
                }
            } else {
                sheet.addCell(new Label(6, z, "县级市", wcf));
            }

            sheet.addCell(new Number(7, z, info.getAgingDay1(), wcf));
            sheet.addCell(new Number(8, z, info.getMoney() == null ? 0D : info.getMoney(), wcf));
            sheet.addCell(new Label(9, z, info.getRemarkAll() == null ? "" : info.getRemarkAll(), wcf5));
        } catch (WriteException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "exportTest")
    public void exportTest(HttpServletRequest req, HttpServletResponse rsp) {
        String menuCode = req.getParameter("menuCode");
        //对账清单导出（应用场景：1、对账订单列表 2、保司报表）
        if ("account-list".equals(menuCode)) {
            SimpleDateFormat simpleDateFormatMonth = new SimpleDateFormat("yyyy-MM");
            String newDate = simpleDateFormatMonth.format(new Date());

            String bookType = req.getParameter("bookType");
            if ("all1".equals(bookType)) {//总表
                TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoExportDto>>> typeToken = new TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoExportDto>>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_LIST_TO_EXPORT, null, req);
                List<SurveyRiskCaseInfoExportDto> surveyRiskCaseInfoExportDtos = (List<SurveyRiskCaseInfoExportDto>) apiFinalResponse.getResults();
                // 创建excel
                WritableWorkbook book = null; // 创建jxl工作簿
                String filename = "总表（" + newDate + "）.xls";
                try {
                    OutputStream os = rsp.getOutputStream();
                    rsp.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO8859-1"));
                    rsp.setContentType("application/msexcel");
                    // 打开文件
                    book = Workbook.createWorkbook(os);
                    // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
                    WritableSheet sheet = book.createSheet("总表", 0);

                    sheet.setColumnView(0, 35);// 将第一列的宽度设为30
                    sheet.setColumnView(1, 15);
                    sheet.setColumnView(2, 15);
                    sheet.setColumnView(3, 30);
                    sheet.setColumnView(4, 15);
                    sheet.setColumnView(5, 20);
                    sheet.setColumnView(6, 20);
                    sheet.setColumnView(7, 15);
                    sheet.setColumnView(8, 15);
                    sheet.setColumnView(9, 15);
                    sheet.setColumnView(10, 35);
                    sheet.setColumnView(11, 20);

                    //添加表头
                    sheet.addCell(new Label(0, 0, "编号"));
                    sheet.addCell(new Label(1, 0, "理赔编号"));
                    sheet.addCell(new Label(2, 0, "被调查人"));
                    sheet.addCell(new Label(3, 0, "委托人名称"));
                    sheet.addCell(new Label(4, 0, "委托人机构名称"));
                    sheet.addCell(new Label(5, 0, "开票金额"));
                    sheet.addCell(new Label(6, 0, "是否阳性"));
                    sheet.addCell(new Label(7, 0, "委托时间"));
                    sheet.addCell(new Label(8, 0, "终审时间"));
                    sheet.addCell(new Label(9, 0, "案件时效"));
                    sheet.addCell(new Label(10, 0, "方向编号"));
                    sheet.addCell(new Label(11, 0, "调查方向"));
                    sheet.addCell(new Label(12, 0, "调查金额"));

                    //添加内容
                    int index = 1;
                    int z = 1;
                    for (SurveyRiskCaseInfoExportDto info : surveyRiskCaseInfoExportDtos) {
                        int rowspan = info.getSurveyCaseDirections().size();
                        sheet.mergeCells(0, z, 0, z + rowspan - 1);//跨行
                        sheet.addCell(new Number(0, z, index));

                        sheet.mergeCells(1, z, 1, z + rowspan - 1);//跨行
                        sheet.addCell(new Label(1, z, info.getClaimsNo() == null ? "" : info.getClaimsNo()));

                        sheet.mergeCells(2, z, 2, z + rowspan - 1);
                        sheet.addCell(new Label(2, z, info.getSurveyPerson() == null ? "" : info.getSurveyPerson()));

                        sheet.mergeCells(3, z, 3, z + rowspan - 1);
                        sheet.addCell(new Label(3, z, info.getEntrustUserName() == null ? "" : info.getEntrustUserName()));

                        sheet.mergeCells(4, z, 4, z + rowspan - 1);
                        sheet.addCell(new Label(4, z, info.getEntrustOrgName() == null ? "" : info.getEntrustOrgName()));

                        sheet.mergeCells(5, z, 5, z + rowspan - 1);
                        sheet.addCell(new Number(5, z, info.getEntrustOkPrice1() == null ? 0D : info.getEntrustOkPrice1()));

                        sheet.mergeCells(6, z, 6, z + rowspan - 1);
                        if (info.getIsSun() != null) {
                            int isSun = info.getIsSun();
                            if (isSun == 0) {
                                sheet.addCell(new Label(6, z, "否"));
                            } else if (isSun == 1) {
                                sheet.addCell(new Label(6, z, "是"));
                            }
                        } else {
                            sheet.addCell(new Label(6, z, ""));
                        }

                        sheet.mergeCells(7, z, 7, z + rowspan - 1);
                        if (info.getEntrustTime() != null) {
                            sheet.addCell(new Label(7, z, format.format(info.getEntrustTime())));
                        } else {
                            sheet.addCell(new Label(7, z, ""));
                        }

                        sheet.mergeCells(8, z, 8, z + rowspan - 1);
                        if (info.getEntrustReportStartDate() != null) {
                            sheet.addCell(new Label(8, z, format.format(info.getEntrustReportStartDate())));
                        } else {
                            sheet.addCell(new Label(8, z, ""));
                        }

                        //去除工作日的时间
//                        int days = GetWorkDay.calLeaveDays(info.getEntrustTime()==null?new Date():info.getEntrustTime(),info.getEntrustReportStartDate()==null?new Date():info.getEntrustReportStartDate());
//                        days = Math.abs(days);
                        sheet.mergeCells(9, z, 9, z + rowspan - 1);
                        sheet.addCell(new Label(9, z, info.getEfficiency() + " 天"));//案件时效

                        if (info.getSurveyCaseDirections() != null && info.getSurveyCaseDirections().size() > 0) {
                            List<SurveyCaseDirectionDto> directionDtos = info.getSurveyCaseDirections();
                            for (int j = 0; j < directionDtos.size(); j++) {
                                sheet.addCell(new Number(10, z, j + 1));
                                sheet.addCell(new Label(11, z, directionDtos.get(j).getDirectionName() == null ? "" : directionDtos.get(j).getDirectionName()));//调查方向
                                sheet.addCell(new Label(12, z, directionDtos.get(j).getEntrustMoney() == null ? "" : directionDtos.get(j).getEntrustMoney() + "元"));//案件时效
                                z = z + 1;
                            }
                        } else {
                            sheet.addCell(new Label(10, z, ""));
                            sheet.addCell(new Label(11, z, ""));//调查方向
                            sheet.addCell(new Label(12, z, ""));//案件时效
                            z = z + 1;
                        }
                        index++;
                    }

                    // 写入数据并关闭文件
                    book.write();
                    book.close();

                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (book != null) {
                        try {
                            book.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            } else if ("all2".equals(bookType)) {
                String retJson = "";
                String json = this.callApi(BackendApiMethodEnum.BACKEND_SURVEY_CASE_LIST_TO_EXPORT, null, req);
                System.out.println(json);
                Type type = new TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoExportDto>>>() {
                }.getType();
                ApiFinalResponse<List<SurveyRiskCaseInfoExportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
                if (apiRsp == null) {
                    return;
                }
                // 创建excel
                List<SurveyRiskCaseInfoExportDto> queryList = apiRsp.getResults();
                WritableWorkbook book = null; // 创建jxl工作簿
                String filename = "总表.xls";

                OutputStream os = null;
                try {
                    os = rsp.getOutputStream();
                    rsp.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "ISO8859-1"));
                    rsp.setContentType("application/msexcel");
                    // 打开文件
                    book = Workbook.createWorkbook(os);
                    // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
                    WritableSheet sheet = book.createSheet("总表", 0);

                    // 设置表头
                    sheet.addCell(new Label(0, 0, "编号"));
                    sheet.addCell(new Label(1, 0, "理赔编号"));
                    sheet.addCell(new Label(2, 0, "被调查人"));
                    sheet.addCell(new Label(3, 0, "委托人名称"));
                    sheet.addCell(new Label(4, 0, "委托人机构名称"));
                    sheet.addCell(new Label(5, 0, "开票金额"));
                    sheet.addCell(new Label(6, 0, "是否阳性"));
                    sheet.addCell(new Label(7, 0, "委托时间"));
                    sheet.addCell(new Label(8, 0, "终审时间"));
                    sheet.addCell(new Label(9, 0, "案件时效"));
                    sheet.addCell(new Label(10, 0, "方向编号"));
                    sheet.addCell(new Label(11, 0, "调查方向"));
                    sheet.addCell(new Label(12, 0, "调查金额"));


                    if (queryList != null && !queryList.isEmpty()) {
                        int index = 1;
                        int z = 1;
                        for (int i = 0; i < queryList.size(); i++) {
                            int rowspan = queryList.get(i).getSurveyCaseDirections().size();
                            SurveyRiskCaseInfoExportDto info = queryList.get(i);
                            sheet.mergeCells(0, z, 0, z + rowspan - 1);//跨行
                            sheet.addCell(new Number(0, z, index));

                            sheet.mergeCells(1, z, 1, z + rowspan - 1);//跨行
                            sheet.addCell(new Label(1, z, info.getClaimsNo() == null ? "" : info.getClaimsNo()));

                            sheet.mergeCells(2, z, 2, z + rowspan - 1);
                            sheet.addCell(new Label(2, z, info.getSurveyPerson() == null ? "" : info.getSurveyPerson()));

                            sheet.mergeCells(3, z, 3, z + rowspan - 1);
                            sheet.addCell(new Label(3, z, info.getEntrustUserName() == null ? "" : info.getEntrustUserName()));

                            sheet.mergeCells(4, z, 4, z + rowspan - 1);
                            sheet.addCell(new Label(4, z, info.getEntrustOrgName() == null ? "" : info.getEntrustOrgName()));

                            sheet.mergeCells(5, z, 5, z + rowspan - 1);
                            sheet.addCell(new Number(5, z, info.getBillingMoney() == null ? 0D : info.getBillingMoney()));

                            sheet.mergeCells(6, z, 6, z + rowspan - 1);
                            if (info.getIsSun() != null) {
                                int isSun = info.getIsSun();
                                if (isSun == 0) {
                                    sheet.addCell(new Label(6, z, "否"));
                                } else if (isSun == 1) {
                                    sheet.addCell(new Label(6, z, "是"));
                                }
                            } else {
                                sheet.addCell(new Label(6, z, ""));
                            }

                            sheet.mergeCells(7, z, 7, z + rowspan - 1);
                            if (info.getEntrustTime() != null) {
                                sheet.addCell(new Label(7, z, format.format(info.getEntrustTime())));
                            } else {
                                sheet.addCell(new Label(7, z, ""));
                            }

                            sheet.mergeCells(8, z, 8, z + rowspan - 1);
                            if (info.getEntrustReportStartDate() != null) {
                                sheet.addCell(new Label(8, z, format.format(info.getEntrustReportStartDate())));
                            } else {
                                sheet.addCell(new Label(8, z, ""));
                            }

                            //去除工作日的时间
//                            int days = GetWorkDay.calLeaveDays(info.getEntrustTime()==null?new Date():info.getEntrustTime(),info.getEntrustReportStartDate()==null?new Date():info.getEntrustReportStartDate());
//                            days = Math.abs(days);
                            sheet.mergeCells(9, z, 9, z + rowspan - 1);
                            sheet.addCell(new Label(9, z, info.getEfficiency() + " 天"));//案件时效

                            if (info.getSurveyCaseDirections() != null && info.getSurveyCaseDirections().size() > 0) {
                                List<SurveyCaseDirectionDto> directionDtos = info.getSurveyCaseDirections();
                                for (int j = 0; j < directionDtos.size(); j++) {
                                    sheet.addCell(new Number(10, z, j + 1));
                                    sheet.addCell(new Label(11, z, directionDtos.get(j).getDirectionName() == null ? "" : directionDtos.get(j).getDirectionName()));//调查方向
                                    sheet.addCell(new Label(12, z, directionDtos.get(j).getEntrustMoney() == null ? "" : directionDtos.get(j).getEntrustMoney() + "元"));//案件时效
                                    z = z + 1;
                                }
                            } else {
                                sheet.addCell(new Label(10, z, ""));
                                sheet.addCell(new Label(11, z, ""));//调查方向
                                sheet.addCell(new Label(12, z, ""));//案件时效
                                z = z + 1;
                            }
                            index++;
                        }
                    }
                    // 写入数据并关闭文件
                    book.write();
                    book.close();
                    os.close();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (RowsExceededException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }


            }
        }

    }

    @RequestMapping(value = "listCaseVisit")
    public ModelAndView listCaseVisit(HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String menuCode = req.getParameter("menuCode");
        model.put("menuCode", menuCode);
        model.put("pageSize", req.getParameter("pageSize"));

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyAssignOrgDto>>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_ASSIGN_VISIT_LIST, appendMap, req);
        model.put("apiRsp", apiFinalResponse);
        model.put("surveyCaseNo", req.getParameter("surveyCaseNo"));//案件编号
        model.put("surveyNo", req.getParameter("surveyNo"));//调查编号
        model.put("surveyPerson", req.getParameter("surveyPerson"));//被调查人
        model.put("surveryPersonTel", req.getParameter("surveryPersonTel"));//联系方式
        model.put("entrustOrgIds", req.getParameter("entrustOrgIds"));//保险公司

        model.put("sortField", req.getParameter("sortField"));
        model.put("sortType", req.getParameter("sortType"));
        model.put("searchStr", req.getParameter("searchStr"));//保险公司

        //回访人员
        typeToken = new TypeToken<ApiFinalResponse<List<UserInfo>>>() {
        };
        appendMap = new HashMap<String, Object>();
        appendMap.put("roleId", 99);
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_USER_INFO_LIST_BY_ROLEID, appendMap, req);
        List<UserInfo> userInfos = (List<UserInfo>) apiFinalResponse.getResults();
        model.put("userInfos", userInfos);

        //所有的委托方机构
        appendMap = new HashMap<String, Object>();
        appendMap.put("menuType", 1); //不分页
        appendMap.put("surveyCode", "consignor");//查询“狄大人终审人员对应的委托方”，改变surveyCode值
        typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
        };
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
        List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
        model.put("consignors", consignors);

        if ("visit-list".equals(menuCode)) { // 调查回访
            model.put("isAbnormal", req.getParameter("isAbnormal"));//回访异常
            model.put("visitState", req.getParameter("visitState"));//回访状态
            model.put("visitPersons", req.getParameter("visitPersons"));//回访人员
            //回访时间
            String visitStateTime = req.getParameter("visitStateTime");
            model.put("visitStateTime", visitStateTime == null ? "" : visitStateTime);
            String visitEndTime = req.getParameter("visitEndTime");
            model.put("visitEndTime", visitEndTime == null ? "" : visitEndTime);
        }
        return new ModelAndView("/survey/case/listVisit", model);
    }

    /**
     * 下载图片
     *
     * @param req
     * @param response
     */
    @RequestMapping(value = "downloadImg")
    public void downloadImg(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String fileName = req.getParameter("fileName");
        String path = req.getParameter("filePath");
        //设置响应参数
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
//      response.setHeader("Content-Disposition", " attachment; filename=" + fileName);//使用此会导致中文变“_”下划线
        response.setHeader("Content-Disposition", " attachment; filename=" + new String(fileName.getBytes(), "iso-8859-1"));

        InputStream is = null;
        BufferedOutputStream outs = null;
        try {
            //创建数据流，执行下载
            URL url = new URL(path);
            is = url.openStream();
            outs = new BufferedOutputStream(response.getOutputStream());
            byte[] bytes = IOUtils.toByteArray(is);
            outs.write(bytes);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outs)
                outs.close();
            if (null != is)
                is.close();
        }
    }

    //二调报告的下载（需求：二调的案件 下载报告需要把之前调查的附件一起打包下载）
    public String downTransfer(TemplateData data) {
        //查询是否是二调案件
        String zipPath = "";
        SurveyRiskCaseInfoDto riskCaseInfoDto = data.getSurveyRiskCaseInfo();//主案件
        List<SurveyRiskCaseInfoDto> riskCaseInfoDtos = data.getRiskCaseInfos();//二调前的案件
        if (riskCaseInfoDtos != null && riskCaseInfoDtos.size() > 0) {

            //被压缩文件夹的路径
            String subFolder = transferPath.concat(riskCaseInfoDto.getSurveyCno().toLowerCase()).concat("/zip");// /mnt/sftp/files/test/temp/transfer/
            File file = new File(subFolder);
            if (!file.exists()) {//如果不存在
                file.mkdirs();
            }

            //将当前的案件 附件等数据copy到临时目录中
            String casePath = generateFilePath.concat(riskCaseInfoDto.getSurveyCno().toLowerCase()).concat("/");
            //casePath = /mnt/sftp/files/test/ddr/cno/cwt41071597981920758/
            FileUtils.copyFolder(casePath, subFolder, false);

            //被压缩文件夹 中 -附件-所在地址
            String directionFolder = subFolder.concat("/direction").concat("/"); // /mnt/sftp/files/test/temp/transfer/direction/
            file = new File(directionFolder);
            if (!file.exists()) {//如果不存在
                file.mkdirs();
            }
            //再循环二调之前的案件
            for (SurveyRiskCaseInfoDto caseInfoDto : riskCaseInfoDtos) {
                casePath = generateFilePath.concat(caseInfoDto.getSurveyCno().toLowerCase()).concat("/direction").concat("/");
                // casePath = /mnt/sftp/files/test/ddr/cno/cwt1111111111/direction/
                FileUtils.copyFolder(casePath, directionFolder, false);
            }

            zipPath = transferPath.concat(riskCaseInfoDto.getSurveyCno().toLowerCase()).concat(".zip");
            // zip Path = /mnt/sftp/files/test/temp/transfer/cwt41071597981920758.zip
            FileZipUtil.createZip(subFolder, zipPath);

            if ("/mnt/sftp/files/test/temp/transfer/".equals(subFolder) || "/mnt/sftp/files/product/temp/transfer/".equals(subFolder)) {
                if ("/mnt/sftp/files/product/temp/transfer/".equals(subFolder)) {
                    zipPath = zipPath.replace("/mnt/sftp/files", surveyFilePathSftp);
                } else if ("/mnt/sftp/files/test/temp/transfer/".equals(subFolder)) {
                    zipPath = zipPath.replace("/mnt/sftp/files", surveyFilePathSftp);
                }
            }
        }
        return zipPath;
    }

    @RequestMapping(value = "sendEmail")
    public void sendEmail(HttpServletRequest req, HttpServletResponse response) {

    }

    /**
     * 机构案件提醒
     *
     * @param req
     * @param response
     */
    @RequestMapping(value = "orgCaseRemindList")
    @ResponseBody
    public String orgCaseRemindList(HttpServletRequest req, HttpServletResponse response) {

        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_ORG_CASE_REMIND_LIST, null, req, response);
    }

    /**
     * 保司代理委托获取截止时间  出去周末 和节假日
     *
     * @param req
     * @param
     * @return
     */
    @RequestMapping(value = "asyncNwCase")
    @ResponseBody
    public String asyncNwCase(HttpServletRequest req) {
        String s = this.callApi(BackendApiMethodEnum.BACKEND_SURVEY_CASE_NW_ASYNC, null, req);
        return s;
    }

    //因为会存在无用的“附件”（如新增方向的时候，先上传附件，却未最终提交，会导致上述附件为冗余附件，故删除）
    private void deleteNotFindDirectionName(TemplateData data) {
        String directionPath = generateFilePath.concat("/").concat(data.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase()).concat("/direction");
        File file = new File(directionPath);
        if (file.exists()) { //如果存在
            List<SurveyCaseDirectionDto> directions = data.getDirections();
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                Boolean havaDel = false; //不删除
                for (SurveyCaseDirectionDto direction : directions) {
                    if (direction.getRealDirectionName().equals(files[i].getName())) {
                        havaDel = false;
                        break;
                    } else {
                        havaDel = true;
                        continue;
                    }
                }
                //如果“文件名称”不存在与“方向”中，删除
                if (havaDel) {
                    if (files[i].exists()) {
                        FileUtils.deleteDir(files[i].getPath());
                    }
                }
            }
        }
    }

}
