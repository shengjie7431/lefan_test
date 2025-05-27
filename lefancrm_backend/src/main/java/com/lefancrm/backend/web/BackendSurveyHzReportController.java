package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.dto.hzReport.AssessmentIndexDto;
import com.lefancrm.backend.dto.hzReport.ProgressTrackDto;
import com.lefancrm.backend.dto.hzReport.ScoreDto;
import com.lefancrm.backend.util.DateUtil;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/survey/hzReport")
public class BackendSurveyHzReportController extends BackendBaseController{


    /**
     * 互助报表
     * */
    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp){

        String menuCode = req.getParameter("menuCode");
        Map model = new HashMap();
        model.put("menuCode",menuCode);
        Map<String, Object> appendMap = new HashMap<String, Object>();

        //条件查询
        if("score".equals(menuCode) || "assessmentIndex".equals(menuCode) || "incomeAndCost".equals(menuCode)|| "incomeAndCostBs".equals(menuCode)
                || "surveyManpower".equals(menuCode) || "surveyManpowerBs".equals(menuCode) || "progressTrack".equals(menuCode) || "progressTrackBs".equals(menuCode)
                || "regionalDistribution".equals(menuCode) || "taskDistribution".equals(menuCode)
                || "investigatorReport".equals(menuCode) || "caseDirection".equals(menuCode)
                || "review-user".equals(menuCode) || "directionAreaDistribution".equals(menuCode)
                || "scoreBs".equals(menuCode)){
            //多选查询
            model.put("entrustOrgIds",req.getParameter("entrustOrgIds")==null?"":req.getParameter("entrustOrgIds"));
            model.put("surveyOrgId",req.getParameter("surveyOrgId")==null?"":req.getParameter("surveyOrgId"));
            model.put("surveyInvestigators",req.getParameter("surveyInvestigators")==null?"":req.getParameter("surveyInvestigators"));
            model.put("caseState",req.getParameter("caseState")==null?"":req.getParameter("caseState"));
            model.put("orgCaseState",req.getParameter("orgCaseState")==null?"":req.getParameter("orgCaseState"));
            model.put("surveyState",req.getParameter("surveyState")==null?"":req.getParameter("surveyState"));
            model.put("searchType",req.getParameter("searchType")==null?"":req.getParameter("searchType"));
            model.put("orgAttr",req.getParameter("orgAttr")==null? 2: req.getParameter("orgAttr"));

            //当前登录人角色scoreRole是：provincialManger 省级机构负责人，areaManger 片区机构负责人，manger 平台人员
            TypeToken typeToken = new TypeToken<ApiFinalResponse<Map>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.GET_DATA_SURVEY_HZ_REPORT_ROLE, null, req);
            Map map = (Map)apiFinalResponse.getResults();
            if (map==null){
                return null;
            }
            model.put("scoreRole",map.get("dataRoleCode"));

            Integer curOrgId = null;
            if((Double)map.get("dataRoleOrgId") !=null){
                curOrgId = ((Double)map.get("dataRoleOrgId")).intValue(); //当前登录人的机构（provincialManger 省级机构负责人，areaManger 片区机构负责人，才会有值）
            }

            Map params = new HashMap();
            //所有的调查机构
            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType",1); //不分页
            appendMap.put("surveyCode","franchisee");

            if(curOrgId != null){//当前登录人所在机构
                appendMap.put("btnCode","myHzInfo");
                appendMap.put("scoreRole",map.get("dataRoleCode"));
            }else{
                appendMap.put("level",1);//仅获取顶级的调查机构
            }
            if (!"review-user".equals(menuCode)){
                appendMap.put("enable",0);//查询启用状态的调查方机构
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
                if ("incomeAndCostBs".equals(menuCode) || "surveyManpowerBs".equals(menuCode) || "progressTrackBs".equals(menuCode) || "scoreBs".equals(menuCode) || "incomeAndCostBs".equals(menuCode)){
                    franchisees = franchisees.parallelStream().filter(e ->e.getBusType() != null && e.getBusType() != 1).collect(Collectors.toList());
                }else{
                    franchisees = franchisees.parallelStream().filter(e ->e.getBusType() != null && e.getBusType() != 2).collect(Collectors.toList());
                }
                model.put("franchisees",franchisees);
//                if ("incomeAndCost".equals(menuCode)){
//                    franchisees = franchisees.stream().filter((SurveyFranchiseeDto f)-> f.getType() == 1).collect(Collectors.toList());
//                }else if ("incomeAndCostBs".equals(menuCode)){
//                    franchisees = franchisees.stream().filter((SurveyFranchiseeDto f)-> f.getInsuranceType() == 1).collect(Collectors.toList());
//                }
                params.put("franchiseesJson",JsonUtil.objectToJson(franchisees));
            }


            //委托公司（互助 -- 互助平台，保司 -- 保险公司）
            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType",1); //不分页
            appendMap.put("surveyCode","consignor");
            appendMap.put("orgAttr",req.getParameter("orgAttr"));//公司属性（1：保险公司；2、互助机构）
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, null);
            List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
            model.put("consignors",consignors);
            params.put("consignorsJson",JsonUtil.objectToJson(consignors));

            //调查机构下的 -- 所有的调查员
            if (!"surveyManpower".equals(menuCode) && !"surveyManpowerBs".equals(menuCode) && !"review-user".equals(menuCode)){//调查员人力报表不需要查询调查数据 其他不需要查询请直接判断
                appendMap = new HashMap<String, Object>();
                appendMap.put("surveyCode","investigator");
                appendMap.put("btnCode",3000);
                appendMap.put("surveyOrgIds",req.getParameter("surveyOrgId")==null? curOrgId : req.getParameter("surveyOrgId"));
                if ("investigators".equals(map.get("dataRoleCode"))){
                    appendMap.put("btnCode",5000);
                    if ("investigatorReport".equals(menuCode)){
                        if (map.containsKey("dataRoleUserId")){
                            Double dataRoleUserId=Double.parseDouble(map.get("dataRoleUserId").toString());
                            appendMap.put("userId",new Double(dataRoleUserId).intValue());
                        }
                    }
                }
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, null);
                List<SurveyInvestigatorDto> investigators = (List<SurveyInvestigatorDto>) apiFinalResponse.getResults();
                model.put("investigators",investigators);
                params.put("investigatorsJson",JsonUtil.objectToJson(investigators));
            }

            //复审人员
            if ("review-user".equals(menuCode)){
                Map<String,Object> roleMap =  new HashMap<String,Object>();
                roleMap.put("roleId",53);
                roleMap.put("oprUserType",2);//1查询互助复审人员  2查询保司复审人员
                typeToken = new TypeToken<ApiFinalResponse<List<UserInfoOprDTO>>>(){};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_OPR_USER, roleMap, req);
                List<UserInfoOprDTO> finalUserInfos =(List<UserInfoOprDTO>)apiFinalResponse.getResults();
                model.put("finalUserInfos",finalUserInfos);
                params.put("finalUserInfos",JsonUtil.objectToJson(finalUserInfos));
            } else if ("regionalDistribution".equals(menuCode) || "directionAreaDistribution".equals(menuCode)) {
                //地区信息
                appendMap = new HashMap<String, Object>();
                appendMap.put("parentId",0);
                String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
                Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
                }.getType();
                ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
                List<CommonArea> commonAreaList=(List<CommonArea>)apiRsp.getResults();
                params.put("commonAreaList",JsonUtil.objectToJson(commonAreaList));
            }
            model.put("params",params);
        }

        //积分报表
        if("score".equals(menuCode)){
            return new ModelAndView("/survey/hzReport/scoreList",model);
        }else if ("scoreBs".equals(menuCode)){
            return new ModelAndView("/survey/bsReport/scoreList",model);
        }else if ("assessmentIndex".equals(menuCode)){//考核指标报表
            return new ModelAndView("/survey/hzReport/assessmentIndex",model);
        }else if ("incomeAndCost".equals(menuCode)){//收入与成本报表
            return new ModelAndView("/survey/hzReport/incomeAndCost",model);
        }else if ("incomeAndCostBs".equals(menuCode)){//收入与成本报表保司
            return new ModelAndView("/survey/bsReport/incomeAndCost",model);
        }else if("regionalDistribution".equals(menuCode)){//案件区域分布报表
            return new ModelAndView("/survey/hzReport/regionalDistributionList",model);
        } else if ("taskDistribution".equals(menuCode)) {//任务分布报表
            return new ModelAndView("/survey/hzReport/taskDistributionList",model);
        }else if ("surveyManpower".equals(menuCode)){//调查员人力报表
            return new ModelAndView("/survey/hzReport/surveyManpower",model);
        }else if ("surveyManpowerBs".equals(menuCode)){//调查员人力报表
            return new ModelAndView("/survey/bsReport/surveyManpower",model);
        }else if ("investigatorReport".equals(menuCode)){//调查员报表
            Map findMap=new HashMap();
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyFranchiseeDto>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVER_FRANCHISEE_INVESTIGATOR, findMap, req);
            SurveyFranchiseeDto surveyFranchiseeDto=(SurveyFranchiseeDto)apiFinalResponse.getResults();
            String organizationType=surveyFranchiseeDto.getBusType().toString();
            if("2".equals(organizationType)){
                model.put("searchType","upMonth");
                String startTime=DateUtil.getTopMonth();
                String endTime=DateUtil.getLastMonth();
                model.put("startTime",startTime);
                model.put("endTime",endTime);
                model.put("dataType","survey");

                appendMap = new HashMap<String, Object>();
                appendMap.put("surveyCode","investigator");
                appendMap.put("btnCode","myInfo");//查询当前登录人所在机构的所有调查员
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyInvestigatorDto> investigatorDtos = (List<SurveyInvestigatorDto>) apiFinalResponse.getResults();
                model.put("investigatorDtos",investigatorDtos);
                return new ModelAndView("/survey/report/surveyIndex",model);
            }
            model.put("organizationType",organizationType);
            return new ModelAndView("/survey/hzReport/investigatorReportList",model);
        }else if("progressTrack".equals(menuCode)){ //进度跟踪报表
            return new ModelAndView("/survey/hzReport/progressTrackList",model);
        }else if("progressTrackBs".equals(menuCode)){ //进度跟踪报表保司
            return new ModelAndView("/survey/bsReport/progressTrackList",model);
        }else if("caseDirection".equals(menuCode)){ //查得率报表（录入方向时的：是否获得录音，是否获得屏拍或者纸质材料）
            return new ModelAndView("/survey/hzReport/caseDirectionList",model);
        }else if ("review-user".equals(menuCode)){
            return new ModelAndView("/survey/hzReport/reviewUser",model);
        }else if("directionAreaDistribution".equals(menuCode)){//方向区域分布报表
            return new ModelAndView("/survey/hzReport/directionAreaDistributionList",model);
        }
        return null;
    }


    /**
     * 获取详情列表信息
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "getDetail")
    public String getDetail(HttpServletRequest req, HttpServletResponse rsp){
        String meunType=req.getParameter("meunType");
        if("regionalDistributionDetails".equals(meunType)){//案件区域分布报表点击省级时查询数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVER_CASE_DIRCETION_LIST_TO_DETAILSLIST, null, req, rsp);
        }
        return this.callApiAndOutput(BackendApiMethodEnum.GET_DATA_SURVEY_HZ_REPORT, null, req, rsp);
    }

    /**
     * 操作-弹窗页面
     */
    @RequestMapping(value = "/popup")
    public ModelAndView popup(HttpServletRequest req, HttpServletResponse rsp) {

        String menuCode = req.getParameter("menuCode");
        String btnCode = req.getParameter("btnCode");
        Map model = new HashMap();
        model.put("menuCode", menuCode);//code标识
        model.put("btnCode", btnCode);//code标识

        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("menuCode",menuCode);
        //积分报表
        if("score".equals(menuCode) || "scoreBs".equals(menuCode) || "progressTrack".equals(menuCode) ||"progressTrackBs".equals(menuCode) ) {
            //具体案件
            if("case".equals(btnCode)){
                //传值
                model.put("caseState",req.getParameter("caseState"));
                model.put("entrustOrgIds",req.getParameter("entrustOrgIds"));
                model.put("orgCaseState",req.getParameter("orgCaseState"));
                model.put("surveyState",req.getParameter("surveyState"));
                model.put("surveyOrgId",req.getParameter("surveyOrgId"));
                model.put("surveyUserId",req.getParameter("surveyUserId"));
                model.put("startTime",req.getParameter("startTime"));
                model.put("endTime",req.getParameter("endTime"));
                model.put("searchType",req.getParameter("searchType"));
                model.put("searchStr",req.getParameter("searchStr"));
                model.put("caseCode",req.getParameter("caseCode"));
                model.put("scoreRole",req.getParameter("scoreRole"));
                model.put("colType",req.getParameter("colType"));

                if("score".equals(menuCode) || "scoreBs".equals(menuCode)) {
                    model.put("caseType",req.getParameter("caseType"));
                    model.put("orgAttr",req.getParameter("orgAttr"));
                    model.put("orgLevel",req.getParameter("orgLevel")==null? 1: req.getParameter("orgLevel"));
                    TypeToken typeToken = new TypeToken<ApiFinalResponse<List<ScoreDto>>>() {};
                    ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.GET_SURVEY_HZ_REPORT_CASE, appendMap, req);
                    req.setAttribute("apiRsp", apiFinalResponse);
                    return new ModelAndView("/survey/hzReport/scoreCaseList", model);
                }else if ("progressTrack".equals(menuCode)){
                    model.put("colType",req.getParameter("colType"));
                    model.put("orgAttr",req.getParameter("orgAttr"));
                    model.put("orgLevel",req.getParameter("orgLevel")==null? 1: req.getParameter("orgLevel"));
                    TypeToken typeToken = new TypeToken<ApiFinalResponse<List<ProgressTrackDto>>>() {};
                    ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.GET_SURVEY_HZ_REPORT_CASE, appendMap, req);
                    req.setAttribute("apiRsp", apiFinalResponse);
                    return new ModelAndView("/survey/hzReport/progressCaseList", model);
                }else if ("progressTrackBs".equals(menuCode)){
                    model.put("colType",req.getParameter("colType"));
                    model.put("orgAttr",req.getParameter("orgAttr"));
                    model.put("orgLevel",req.getParameter("orgLevel")==null? 1: req.getParameter("orgLevel"));
                    TypeToken typeToken = new TypeToken<ApiFinalResponse<List<ProgressTrackDto>>>() {};
                    ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.GET_SURVEY_HZ_REPORT_CASE, appendMap, req);
                    req.setAttribute("apiRsp", apiFinalResponse);
                    return new ModelAndView("/survey/bsReport/progressCaseList", model);
                }
            }
        }else if("regionalDistribution".equals(menuCode)){//案件区域分布报表
            String areaType=req.getParameter("areaType");
            model.put("areaType",areaType);
            String regionType=req.getParameter("regionType");
            model.put("regionType",regionType);
            //获取所选互助平台
            String platform=req.getParameter("platform");
            model.put("platform",platform);
            //获取案件状态
            String caseStatus=req.getParameter("caseStatus");
            model.put("caseStatus",caseStatus);
            //获取案件类型
            String caseType=req.getParameter("caseType");
            model.put("caseType",caseType);
            //获取时间
            String startTime=req.getParameter("startTime");
            model.put("startTime",startTime +" 00:00:00");
            String endTime=req.getParameter("endTime");
            model.put("endTime",endTime+" 23:59:59");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyRegionalDistributionCasesDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVER_CASE_DIRCETION_LIST_TO_DETAILSLIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/survey/hzReport/caseDetailsList",model);
        }else if (menuCode.equals("assessmentIndex")){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<AssessmentIndexDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.GET_SURVEY_HZ_REPORT_ASSESSMENTINDEX_CASE, appendMap, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            model.put("scoreRole",req.getParameter("scoreRole"));

            //传值
            model.put("caseState",req.getParameter("caseState"));
            model.put("entrustOrgIds",req.getParameter("entrustOrgIds"));
            model.put("orgCaseState",req.getParameter("orgCaseState"));
            model.put("surveyState",req.getParameter("surveyState"));
            model.put("surveyOrgId",req.getParameter("surveyOrgId"));
            model.put("surveyUserId",req.getParameter("surveyUserId"));
            model.put("startTime",req.getParameter("startTime"));
            model.put("endTime",req.getParameter("endTime"));
            model.put("searchType",req.getParameter("searchType"));
            model.put("searchStr",req.getParameter("searchStr"));
            model.put("caseType",req.getParameter("caseType"));
            model.put("caseCode",req.getParameter("caseCode"));
            model.put("num",req.getParameter("num"));
            model.put("surveyAreaId",req.getParameter("surveyAreaId"));
            return new ModelAndView("/survey/hzReport/AssessCaseList",model);
        }else if (menuCode.equals("investigatorReport")){//调查员报表
            String investigatorType=req.getParameter("investigatorType");
            model.put("investigatorType",investigatorType);
            String operatorId=req.getParameter("operatorId");
            model.put("operatorId",operatorId);
            //获取时间
            String startTime=req.getParameter("startTime");
            model.put("startTime",startTime +" 00:00:00");
            String endTime=req.getParameter("endTime");
            model.put("endTime",endTime+" 23:59:59");
            model.put("userId",req.getParameter("userId"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<InvestigatorDetailsDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVER_CASE_DIRCETION_LIST_TO_DETAILSLIST, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            req.setAttribute("investigatorType", investigatorType);
            return new ModelAndView("/survey/hzReport/investigatorStatementDetails",model);
        }
        return null;
    }


}
