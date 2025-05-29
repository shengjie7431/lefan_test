package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.dto.avg.SurveyHuzhuDTO;
import com.lefancrm.backend.dto.survey.*;
import com.lefancrm.backend.dto.think.ProThinkDTO;
import com.lefancrm.backend.util.DateUtil;
import com.lefancrm.backend.util.ExcelReport;
import com.lefancrm.backend.util.GetWorkDay;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.Number;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.Boolean;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/survey/report/")
public class BackendSurveyReportController  extends BackendBaseController{

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "index")
    public ModelAndView index(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        String menuCode = req.getParameter("menuCode");
        if ("caseItem".equals(menuCode)){
            model.put("params",req.getParameter("params"));
            //是否是平台角色。91总部  93保司
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CURRENT_USER_ROLE_LIST, null, req);
            List<BusUserRoleDto> userRoles = (List<BusUserRoleDto>)apiFinalResponse.getResults();
            Boolean allRole = isRoleUser(userRoles,91L);
            Boolean entrustRole = isRoleUser(userRoles,93L);
            model.put("haveReport",false);
            if (allRole || entrustRole){
                model.put("haveReport",true);
            }
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_FRANCHISEE_DATA, null, req);
            List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
            model.put("franchisees",franchisees);
            model.put("franchiseesJson",JsonUtil.objectToJson(franchisees));

            typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {};
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CONSIGNOR_DATA, null, req);
            List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
            model.put("consignors",consignors);
            model.put("consignorsJson",JsonUtil.objectToJson(consignors));

            return new ModelAndView("/survey/report/reportCaseItem",model);
        }
        String startTime = null;
        String endTime = null;
        String searchType = req.getParameter("searchType") == null ? "upMonth" : req.getParameter("searchType");//默认上月
        if (!"date".equals(searchType)){//非时间段查询
            StringBuilder builderStart = new StringBuilder("");
            StringBuilder builderEnd = new StringBuilder("");
            DateUtil.convertTimeBySearchType(builderStart,builderEnd,searchType);
            startTime = builderStart.toString();
            endTime = builderEnd.toString();
            if ("all".equals(searchType)){
                startTime = "2019-01-01";
                endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            }
        }else{
            startTime = req.getParameter("startTime");
            endTime = req.getParameter("endTime");
        }

        model.put("searchType",searchType);
        model.put("startTime",startTime);
        model.put("endTime",endTime);
        model.put("menuCode",menuCode);
        if ("clock".equals(menuCode)){
            /*TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.GET_DATA_SURVEY_USER_ORG, null, req);
            List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>)apiFinalResponse.getResults();
            model.put("franchiseesJson",JsonUtil.objectToJson(franchisees));*/

            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.GET_DATA_SURVEY_USER_ORG, null, req);
            List<SurveyFranchiseeDto> franchisees=(List<SurveyFranchiseeDto>)apiFinalResponse.getResults();
            model.put("franchiseesJson",JsonUtil.objectToJson(franchisees));

            Map appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","investigator");
            appendMap.put("btnCode",3000);
//            appendMap.put("btnCode",5000);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, null);
            List<SurveyInvestigatorDto> investigators = (List<SurveyInvestigatorDto>) apiFinalResponse.getResults();
            model.put("investigatorsJson",JsonUtil.objectToJson(investigators));
            return new ModelAndView("/survey/report/clockIndex",model);
        }else if("proThink".equals(menuCode)){
            Map appendMap = new HashMap<String, Object>();
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<ProThinkDTO>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_THINK_PRO_LIST, appendMap, null);
            List<ProThinkDTO> proThinks = (List<ProThinkDTO>) apiFinalResponse.getResults();
            model.put("proThinks",proThinks);
            model.put("proThinksJson",JsonUtil.objectToJson(proThinks));
            return new ModelAndView("/survey/report/think/" + menuCode,model);
        }else if("orgThinkBus".equals(menuCode) || "orgThinkPost".equals(menuCode) || "orgThinkBusManager".equals(menuCode)){
            //产品
            Map appendMap = new HashMap<String, Object>();
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<ProThinkDTO>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_THINK_PRO_LIST, appendMap, null);
            List<ProThinkDTO> proThinks = (List<ProThinkDTO>) apiFinalResponse.getResults();
            model.put("proThinksJson",JsonUtil.objectToJson(proThinks));

            //机构信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","organ");
            appendMap.put("havePage","no");//不分页
            typeToken = new TypeToken<ApiFinalResponse<List<StaffOrganDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffOrganDto> organs = (List<StaffOrganDto>)apiFinalResponse.getResults();
            model.put("organsJson", JsonUtil.objectToJson(organs));
            return new ModelAndView("/survey/report/think/" + menuCode,model);
        }else if ("think-lfjt".equals(menuCode) || "think-lfzb".equals(menuCode)  || "think-hygl".equals(menuCode) ||
                "think-ywgl".equals(menuCode) || "think-ywxs".equals(menuCode) || "think-ywzy".equals(menuCode) || "think-cpx".equals(menuCode)){//经营分析报表

            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CURRENT_USER_ROLE_LIST, null, req);
            List<BusUserRoleDto> userRoles = (List<BusUserRoleDto>)apiFinalResponse.getResults();
            model.put("orgManager",false);//机构
            if (!isRoleUser(userRoles,138L) && isRoleUser(userRoles,139L)){
                model.put("orgManager",true);
            }
            //机构信息
            Map appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","organ");
            appendMap.put("havePage","no");//不分页
            typeToken = new TypeToken<ApiFinalResponse<List<StaffOrganDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffOrganDto> organs = (List<StaffOrganDto>)apiFinalResponse.getResults();
            model.put("organsJson", JsonUtil.objectToJson(organs));

            //产品
            appendMap = new HashMap<String, Object>();
            typeToken = new TypeToken<ApiFinalResponse<List<ProThinkDTO>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_THINK_PRO_LIST, appendMap, null);
            List<ProThinkDTO> proThinks = (List<ProThinkDTO>) apiFinalResponse.getResults();
            model.put("proThinksJson",JsonUtil.objectToJson(proThinks));

            return new ModelAndView("/survey/report/think/" + menuCode,model);
        }

        Map<String, Object> appendMap = new HashMap<String, Object>();
        //所有的委托方机构
        appendMap.put("menuType", 1); //不分页
        appendMap.put("surveyCode", "consignor");//查询“狄大人终审人员对应的委托方”，改变surveyCode值
        appendMap.put("orgAttr",1);
        if ("huZhu".equals(menuCode)){//只查询互助的保险公司
            appendMap.put("orgAttr",2);
        }
        if ("project".equals(req.getParameter("menuCode2"))){
            appendMap.put("project","project");//查询项目组对应的委托方机构
        }

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {
        };
        Map params=new HashMap();
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
        List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
        model.put("consignors", consignors);
        params.put("consignorsJson", JsonUtil.objectToJson(consignors));
        model.put("params", params);
        model.put("entrustOrgId", req.getParameter("entrustOrgId"));
        //所有的调查机构-  非保司报表 则查询调查方机构列表
//        if(!"entrust".equals(menuCode)){
            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType",1); //不分页
            appendMap.put("surveyCode","franchisee");//查询调查机构，改变surveyCode值
            appendMap.put("clientType","1");
            if("huZhu".equals(menuCode)){
                appendMap.put("clientType","2");
            }
            appendMap.put("parentId",0);//仅查询省级机构
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
            model.put("franchisees",franchisees);
            model.put("surveyOrgId",req.getParameter("surveyOrgId"));
//        }

        if ("entrust".equals(menuCode)){
            return new ModelAndView("/survey/report/entrustIndex",model);
        }else if("org".equals(menuCode)){
            model.put("checkType",req.getParameter("checkType"));
            return new ModelAndView("/survey/report/orgIndex",model);
        }else if("survey".equals(menuCode)){
            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType",1); //不分页
            appendMap.put("surveyCode","investigator");//查询调查机构，改变surveyCode值
            String openSource = req.getParameter("openSource");
            if(openSource!=null){
                //两种情况：1、orgIndex，机构报表，点击“排名”跳转  2、总部报表，跳两级 转过来的
                if("orgIndex".equals(openSource)){//机构报表，点击“排名”跳转
//                    appendMap.put("orgId",req.getParameter("surveyOrgId"));
                }
                appendMap.put("btnCode","realInvest");//仅取出“调查员”角色的人
            }else{
                String surveyUserId = req.getParameter("surveyUserId");
                if(surveyUserId==null){
                    appendMap.put("btnCode","myInfo");//查询当前登录人所在机构的所有调查员
                }else{
                    appendMap.put("btnCode","otherInfo");//查询当前登录人所在机构的所有调查员
                }
            }

            typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CURRENT_USER_ROLE_LIST, null, req);
            List<BusUserRoleDto> userRoles = (List<BusUserRoleDto>)apiFinalResponse.getResults();
            Boolean whole = isRoleUser(userRoles,91L);
            Boolean wholeTwo = isRoleUser(userRoles,96L);
            if(whole || wholeTwo){
//                appendMap.put("btnCode","whole");//查询当前登录人权限
            }
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyInvestigatorDto> investigatorDtos = (List<SurveyInvestigatorDto>) apiFinalResponse.getResults();
            model.put("investigatorDtos",investigatorDtos);
            model.put("surveyUserId",req.getParameter("surveyUserId"));
            model.put("checkType",req.getParameter("checkType"));
            return new ModelAndView("/survey/report/surveyIndex",model);
        }
        else if("huZhu".equals(menuCode)){
            appendMap = new HashMap<String, Object>();
            String entrustOrgIds = req.getParameter("entrustOrgIds");
            if (entrustOrgIds == null || "".equals(entrustOrgIds)){
                entrustOrgIds = "";
                for (SurveyConsignorDto consignor : consignors) {
                    entrustOrgIds += consignor.getId() + ",";
                }
                appendMap.put("entrustOrgIds",entrustOrgIds);
            }
            //期间
            String beginDate = req.getParameter("beginDate");
            if (beginDate == null){
                appendMap.put("beginDate",getDate(-37));
                beginDate = appendMap.get("beginDate").toString();
            }
            String endDate = req.getParameter("endDate");
            if (endDate == null){
                appendMap.put("endDate",getDate(-7));
                endDate = appendMap.get("endDate").toString();
            }
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyOrgDTO>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_ORG_HELP, appendMap, req);
            List<SurveyOrgDTO> surveyOrgDTOS = (List<SurveyOrgDTO>)apiFinalResponse.getResults();
            if (surveyOrgDTOS.size() > 0){
                model.put("objAvg",surveyOrgDTOS.get(0).getSurveyHuzhuDTO());
            }else{
                model.put("objAvg",new SurveyHuzhuDTO());
            }
            model.put("apiRsp",apiFinalResponse);
            model.put("surveyOrgIds",req.getParameter("surveyOrgIds"));
            model.put("beginDate",beginDate);
            model.put("endDate",endDate);
            model.put("entrustOrgIds",entrustOrgIds);
            return new ModelAndView("/survey/report/huZhuIndex",model);
        }else if("positiveReward".equals(menuCode)){
            appendMap = new HashMap<String, Object>();
            params=new HashMap();
            appendMap.put("menuType",1); //不分页
            appendMap.put("surveyCode","InvestigatorFranchisee");//查询调查机构，改变surveyCode值
            appendMap.put("clientType",1);
            appendMap.put("type",1);
            appendMap.put("insuranceType",1);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
            params.put("franchiseesJson", JsonUtil.objectToJson(franchisees));
            appendMap = new HashMap<String, Object>();
            appendMap.put("type",1);
            appendMap.put("busType",1);
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INVESTIGATOR_ALLLIST, appendMap, req);
            List<SurveyInvestigatorDto> investigator = (List<SurveyInvestigatorDto>) apiFinalResponse.getResults();
            params.put("investigatorJson", JsonUtil.objectToJson(investigator));
            model.put("dropDownJson",params);
            return new ModelAndView("/survey/report/positiveReward",model);
        }
        if ("project".equals(menuCode)){
            return new ModelAndView("/survey/report/project",model);
        }
        return new ModelAndView("/survey/report/index",model);
    }

    private String getDate(int day){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH,day);
            calendar.getTime();
            return simpleDateFormat.format(calendar.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "acc")
    public ModelAndView acc(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        //对账清单
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyAccEntrustDTO>>>(){};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_ENTRUST_ACC_LIST,null,req);
        List<SurveyAccEntrustDTO> accs = (List<SurveyAccEntrustDTO>) apiFinalResponse.getResults();
        model.put("accs",accs);
        model.put("apiRsp",apiFinalResponse);
        model.put("startTime",req.getParameter("startTime"));
        model.put("endTime",req.getParameter("endTime"));
        model.put("entrustOrgId",req.getParameter("entrustOrgId"));
        model.put("entrustOrgName",req.getParameter("entrustOrgName"));
        return new ModelAndView("/survey/report/acc",model);
    }

    @RequestMapping(value = "accReport")
    public void accReport(HttpServletRequest req, HttpServletResponse rsp){
        //对账清单导出
        Map appendMap = new HashMap();
        appendMap.put("reportBtn","report");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyAccEntrustDTO>>>(){};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_ENTRUST_ACC_LIST,appendMap,req);
        List<SurveyAccEntrustDTO> accs = (List<SurveyAccEntrustDTO>) apiFinalResponse.getResults();



    }

    @RequestMapping(value = "getData")
    public String getData(HttpServletRequest req, HttpServletResponse rsp){
        Map<String,Object> param =  new HashMap<>();
        String dataType = req.getParameter("dataType");
        if ("proThink".equals(dataType) || "orgThinkBus".equals(dataType) || "orgThinkPost".equals(dataType) || "orgThinkBusManager".equals(dataType) ||
                "think-lfjt".equals(dataType) || "think-lfzb".equals(dataType) || "think-hygl".equals(dataType) || "think-ywgl".equals(dataType) ||
                "think-ywxs".equals(dataType) || "think-ywzy".equals(dataType) || "think-cpx".equals(dataType)
            ){
            return this.callApiAndOutput(BackendApiMethodEnum.SURVEY_GET_THINK_REPORT, param, req, rsp);
        }
        if (StringUtils.isEmpty(req.getParameter("requestExpenseReimbursementInfo"))){
            System.out.println("456");
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_GET_DATA, param, req, rsp);
        }else {
            System.out.println("123");
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_EXPENSE_REIMBURSE_INFO, param, req, rsp);
        }
    }

    @RequestMapping(value = "getDataList")
    public String getDataList(HttpServletRequest req, HttpServletResponse rsp){
        String menuCode=req.getParameter("menuCode");
        Map<String,Object> param =  new HashMap<>();
         if("positiveReward".equals(menuCode)){
            return this.callApiAndOutput(BackendApiMethodEnum.SURVEY_INVESTIGATOR_CASE_REWARD_REPORT, param, req, rsp);
        }
         return null;
    }

    /**
     * 调查费到账信息
     * @param req
     * @param rsp
     * @return
     */
//    @RequestMapping(value = "getExpenseReimbursementInfo")
//    public String getExpenseReimbursementInfo(HttpServletRequest req, HttpServletResponse rsp){
//        Map<String,Object> param =  new HashMap<>();
//
//    }

    /**
     * 保司报表、机构报表、调查员报表 中：对账清单、分值清单
     * */
    @RequestMapping(value = "tableList")
    public ModelAndView tableList(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        model.put("startTime",req.getParameter("startTime"));
        model.put("endTime",req.getParameter("endTime"));
        model.put("entrustOrgId",req.getParameter("entrustOrgId"));
        model.put("checkType",req.getParameter("checkType"));
        String menuCode=req.getParameter("menuCode");
        model.put("menuCode",menuCode);
        if("org".equals(menuCode)){
            //机构分值
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoExportDto>>>(){};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_REPORT_SCORE_LIST,null,req);
            List<SurveyRiskCaseInfoExportDto> scores = (List<SurveyRiskCaseInfoExportDto>) apiFinalResponse.getResults();
            model.put("scores",scores);
            model.put("apiRsp",apiFinalResponse);
            model.put("surveyOrgId",req.getParameter("surveyOrgId"));
            model.put("orgType",req.getParameter("orgType"));
            return new ModelAndView("/survey/report/score",model);
        }else if("survey".equals(menuCode)){
            //机构分值
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoExportDto>>>(){};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_REPORT_SCORE_LIST,null,req);
            List<SurveyRiskCaseInfoExportDto> scores = (List<SurveyRiskCaseInfoExportDto>) apiFinalResponse.getResults();
            model.put("scores",scores);
            model.put("apiRsp",apiFinalResponse);
            model.put("surveyUserId",req.getParameter("surveyUserId"));
            return new ModelAndView("/survey/report/score",model);
        }
        return null;
    }

    /**
     *报表中的分值清单导出
     * */
    @RequestMapping(value = "exportRoportScore")
    public void exportRoportScore(HttpServletRequest req, HttpServletResponse rsp) {
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyRiskCaseInfoExportDto>>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_REPORT_SCORE_EXPORT, null, req);
        List<SurveyRiskCaseInfoExportDto> surveyRiskCaseInfoExportDtos = (List<SurveyRiskCaseInfoExportDto>)apiFinalResponse.getResults();

        String dataType = req.getParameter("dataType"); // org调查机构报表，survey调查员报表
        String orgType = req.getParameter("orgType");
        // 创建excel
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename ="分值清单.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
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
            sheet.setColumnView(0,35);// 将第一列的宽度设为30
            sheet.setColumnView(1,15);
            sheet.setColumnView(2,15);
            sheet.setColumnView(3,30);
            sheet.setColumnView(4,15);
            sheet.setColumnView(5,20);
            sheet.setColumnView(6,20);
            sheet.setColumnView(7,15);
            sheet.setColumnView(8,15);
            sheet.setColumnView(9,15);
            sheet.setColumnView(10,20);
            sheet.setColumnView(11,20);
            sheet.setColumnView(12,30);
            sheet.setColumnView(13,15);
            sheet.setColumnView(14,15);
            sheet.setColumnView(15,15);

            WritableFont wf = new WritableFont(WritableFont.TIMES);
            wf.setColour(Colour.BLACK);
            wf.setPointSize(12);

            //表头
            WritableFont wf2 = new WritableFont(WritableFont.ARIAL,11,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
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

            // 设置表头
            sheet.addCell(new Label(0,0,"案件编号",wcf));
            sheet.addCell(new Label(1,0,"保险公司",wcf));
            sheet.addCell(new Label(2,0,"被调查人",wcf));
            sheet.addCell(new Label(3,0,"分派时间",wcf));
            sheet.addCell(new Label(4,0,"调查完成时间",wcf));
            sheet.addCell(new Label(5,0,"调查时效",wcf));
            sheet.addCell(new Label(6,0,"是否阳性",wcf));
            sheet.addCell(new Label(7,0,"是否退回",wcf));
            if("org".equals(dataType)){
                sheet.addCell(new Label(8,0,"调查员",wcf));
                sheet.addCell(new Label(9,0,"方向编号",wcf));
                sheet.addCell(new Label(10,0,"任务类型",wcf));
                sheet.addCell(new Label(11,0,"任务子类",wcf));
                sheet.addCell(new Label(12,0,"调查方向",wcf));
                sheet.addCell(new Label(13,0,"评价",wcf));
                if("1".equals(orgType)){
                    sheet.addCell(new Label(14,0,"方向分值",wcf));
                    sheet.addCell(new Label(15,0,"总分值",wcf));
                }else if("2".equals(orgType) || "3".equals(orgType)){
                    sheet.addCell(new Label(14,0,"方向金额",wcf));
                    sheet.addCell(new Label(15,0,"总金额",wcf));
                }

            }else if("survey".equals(dataType)){
                sheet.addCell(new Label(8,0,"方向编号",wcf));
                sheet.addCell(new Label(9,0,"任务类型",wcf));
                sheet.addCell(new Label(10,0,"任务子类",wcf));
                sheet.addCell(new Label(11,0,"调查方向",wcf));
                sheet.addCell(new Label(12,0,"评价",wcf));
                sheet.addCell(new Label(13,0,"方向分值",wcf));
                sheet.addCell(new Label(14,0,"总分值",wcf));
            }



            if(surveyRiskCaseInfoExportDtos!=null && !surveyRiskCaseInfoExportDtos.isEmpty()){
                int z = 1;
                for (SurveyRiskCaseInfoExportDto info : surveyRiskCaseInfoExportDtos) {
                    int rowspan = info.getSurveyCaseDirections().size();
                    sheet.mergeCells(0, z, 0, z+rowspan-1);//跨行
                    sheet.addCell(new Label(0, z, info.getSurveyCaseNo()==null?"":info.getSurveyCaseNo(),wcf));

                    sheet.mergeCells(1, z, 1, z+rowspan-1);
                    sheet.addCell(new Label(1, z, info.getEntrustOrgName()==null?"":info.getEntrustOrgName(),wcf));

                    sheet.mergeCells(2, z, 2, z+rowspan-1);
                    sheet.addCell(new Label(2, z, info.getSurveyPerson()==null?"":info.getSurveyPerson(),wcf));

                    sheet.mergeCells(3, z, 3, z+rowspan-1);
                    if (info.getAssignDate() != null) {
                        sheet.addCell(new Label(3, z, format.format(info.getAssignDate()),wcf));
                    } else {
                        sheet.addCell(new Label(3, z, "",wcf));
                    }

                    sheet.mergeCells(4, z, 4, z+rowspan-1);
                    if (info.getEntrustReportStartDate() != null) {
                        sheet.addCell(new Label(4, z, format.format(info.getEntrustReportStartDate()),wcf));
                    } else {
                        sheet.addCell(new Label(4, z, "",wcf));
                    }

                    //去除工作日的时间
//                    int days = GetWorkDay.calLeaveDays(info.getAssignDate() == null ? new Date() : info.getAssignDate(), info.getEntrustReportStartDate() == null ? new Date() : info.getEntrustReportStartDate());
//                    days = Math.abs(days);
                    sheet.mergeCells(5, z, 5, z+rowspan-1);
                    sheet.addCell(new Number(5, z, info.getEfficiency(),wcf));//案件时效

                    sheet.mergeCells(6, z, 6, z+rowspan-1);
                    if (info.getIsSun() != null) {
                        int isSun = info.getIsSun();
                        if (isSun == 0) {
                            sheet.addCell(new Label(6, z, "否",wcf));
                        }else if(isSun == 1){
                            sheet.addCell(new Label(6, z, "是",wcf));
                        }
                    }else{
                        sheet.addCell(new Label(6, z, "",wcf));
                    }

                    sheet.mergeCells(7, z, 7, z+rowspan-1);
                    if (info.getReturnState() != null) {
                        int returnState = info.getReturnState();
                        if (returnState == 0) {
                            sheet.addCell(new Label(7, z, "否",wcf));
                        }else if(returnState == 1){
                            sheet.addCell(new Label(7, z, "是",wcf));
                        }
                    }else{
                        sheet.addCell(new Label(7, z, "",wcf));
                    }

                    //机构报表
                    if("org".equals(dataType)) {
                        //调查员
//                        sheet.mergeCells(8, z, 8, z + rowspan - 1);
//                        sheet.addCell(new Label(8, z, info.getSurveyUserName() == null ? "" : info.getSurveyUserName(), wcf));

                        if (info.getSurveyCaseDirections() != null && info.getSurveyCaseDirections().size() > 0) {
                            List<SurveyCaseDirectionDto> directionDtos = info.getSurveyCaseDirections();
//                            Double scoreSum = 0D;
//                            Double entrustMoneySum = 0D;
                            for (int j = 0; j < directionDtos.size(); j++) {
                                sheet.addCell(new Label(8, z, directionDtos.get(j).getSurveyUserName() == null ? "" : directionDtos.get(j).getSurveyUserName(), wcf));//调查员
                                sheet.addCell(new Number(9, z, j + 1, wcf));
                                sheet.addCell(new Label(10, z, directionDtos.get(j).getTaskName() == null ? "" : directionDtos.get(j).getTaskName(), wcf));//调查方向
                                sheet.addCell(new Label(11, z, directionDtos.get(j).getNewName() == null ? "" : directionDtos.get(j).getNewName(), wcf));//调查方向
                                sheet.addCell(new Label(12, z, directionDtos.get(j).getDirectionName() == null ? "" : directionDtos.get(j).getDirectionName(), wcf));//调查方向
                                String evaluateName = "合格";
                                Integer evaluate = directionDtos.get(j).getEvaluate();
                                evaluate = evaluate == null ? 1 : evaluate;
                                if (evaluate == 1){

                                }else if (evaluate == 2){
                                    evaluateName = "优";
                                }else if (evaluate == 3){
                                    evaluateName = "差";
                                }
                                sheet.addCell(new Label(13, z, evaluateName, wcf));//评价
                                if("1".equals(orgType)){
                                    sheet.addCell(new Label(14, z, directionDtos.get(j).getScore() == null ? "" : directionDtos.get(j).getScore() + "分", wcf));//分值
//                                    scoreSum = scoreSum + (directionDtos.get(j).getScore() == null ? 0D : directionDtos.get(j).getScore());
                                }else if("2".equals(orgType) || "3".equals(orgType)){
                                    sheet.addCell(new Label(14, z, directionDtos.get(j).getSurveyMoney() == null ? "" : directionDtos.get(j).getSurveyMoney() +"元",wcf));//调查费
//                                    entrustMoneySum = entrustMoneySum + (directionDtos.get(j).getSurveyMoney() == null ? 0D : directionDtos.get(j).getSurveyMoney());
                                }

                                z = z + 1;
                            }
                            if("1".equals(orgType)) {
                                sheet.mergeCells(15, z - directionDtos.size(), 15, z - directionDtos.size() + rowspan - 1);
                                sheet.addCell(new Number(15, z - directionDtos.size(), info.getScore(), wcf));
                            }else if("2".equals(orgType) || "3".equals(orgType)){
                                sheet.mergeCells(15, z - directionDtos.size(), 15, z - directionDtos.size() + rowspan - 1);
                                sheet.addCell(new Number(15, z - directionDtos.size(), info.getEntrustMoneySum(), wcf));
                            }
                        } else {
                            sheet.addCell(new Label(9, z, "", wcf));
                            sheet.addCell(new Label(10, z, "", wcf));//
                            sheet.addCell(new Label(11, z, "", wcf));//
                            sheet.addCell(new Label(12, z, "", wcf));
                            sheet.addCell(new Label(13, z, "", wcf));
                            sheet.addCell(new Label(14, z, "", wcf));//
                            sheet.addCell(new Number(15, z, 0, wcf));
                            z = z + 1;
                        }
                    }
                    //调查员报表
                    else if("survey".equals(dataType)) {
                        if (info.getSurveyCaseDirections() != null && info.getSurveyCaseDirections().size() > 0) {
                            List<SurveyCaseDirectionDto> directionDtos = info.getSurveyCaseDirections();
                            Double scoreSum = 0D;
                            for (int j = 0; j < directionDtos.size(); j++) {
                                sheet.addCell(new Number(8, z, j + 1, wcf));
                                sheet.addCell(new Label(9, z, directionDtos.get(j).getTaskName() == null ? "" : directionDtos.get(j).getTaskName(), wcf));//调查方向
                                sheet.addCell(new Label(10, z, directionDtos.get(j).getNewName() == null ? "" : directionDtos.get(j).getNewName(), wcf));//调查方向
                                sheet.addCell(new Label(11, z, directionDtos.get(j).getDirectionName() == null ? "" : directionDtos.get(j).getDirectionName(), wcf));//调查方向
                                String evaluateName = "合格";
                                Integer evaluate = directionDtos.get(j).getEvaluate();
                                evaluate = evaluate == null ? 1 : evaluate;
                                if (evaluate == 1){

                                }else if (evaluate == 2){
                                    evaluateName = "优";
                                }else if (evaluate == 3){
                                    evaluateName = "差";
                                }
                                sheet.addCell(new Label(12, z, evaluateName, wcf));//评价
                                sheet.addCell(new Label(13, z, directionDtos.get(j).getScore() == null ? "" : directionDtos.get(j).getScore() + "分", wcf));//分值
                                scoreSum = scoreSum + (directionDtos.get(j).getScore() == null ? 0D : directionDtos.get(j).getScore());
                                z = z + 1;
                            }
                            sheet.mergeCells(14, z-directionDtos.size(), 14, z -directionDtos.size() + rowspan - 1);
                            sheet.addCell(new Number(14, z-directionDtos.size(), info.getScore(), wcf));
                        } else {
                            sheet.addCell(new Label(9, z, "", wcf));
                            sheet.addCell(new Label(10, z, "", wcf));//
                            sheet.addCell(new Label(11, z, "", wcf));//
                            sheet.addCell(new Label(12, z, "", wcf));
                            sheet.addCell(new Label(13, z, "", wcf));//
                            sheet.addCell(new Label(14, z, "", wcf));//
                            z = z + 1;
                        }
                    }

                }

            }


            // 写入数据并关闭文件
            book.write();
            book.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @RequestMapping(value = "/surveyOrgListExport")
    public void surveyOrgListExport(HttpServletRequest req, HttpServletResponse rsp) {
        String exportType = req.getParameter("exportType");
        if ("index-survey-data-export".equals(exportType)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorOrgReport>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_GET_DATA, null, req);
            List<SurveyInvestigatorOrgReport> data = (List<SurveyInvestigatorOrgReport>)apiFinalResponse.getResults();
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setIndex((i + 1) + "");
                BigDecimal bg = new BigDecimal(data.get(i).getHbCaseNumRate() * 100);
                Double tempValue = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                data.get(i).setHbCaseNumRateStr(tempValue.toString() + "%");
                bg = new BigDecimal(data.get(i).getTbCaseNumRate() * 100);
                tempValue = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                data.get(i).setTbCaseNumRateStr(tempValue.toString() + "%");
                bg = new BigDecimal(data.get(i).getPositiveRate() * 100);
                tempValue = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                data.get(i).setPositiveRateStr(tempValue.toString());
                bg = new BigDecimal(data.get(i).getEfficiency());
                tempValue = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                data.get(i).setEfficiencyStr(tempValue.toString());
                bg = new BigDecimal(data.get(i).getLossEfficiencyRate() * 100);
                tempValue = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                data.get(i).setLossEfficiencyRateStr(tempValue.toString());
                bg = new BigDecimal(data.get(i).getReturnRate() * 100);
                tempValue = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                data.get(i).setReturnRateStr(tempValue.toString());
            }
            ExcelReport.report(new SurveyInvestigatorOrgReport(),data,"导出数据",rsp);
            return;
        }



        String surveyOrgIds = req.getParameter("surveyOrgIds");
        String entrustOrgId = req.getParameter("entrustOrgId");
        String beginDate = req.getParameter("beginDate");
        String endDate = req.getParameter("endDate");

        Map map = new HashMap();
        map.put("surveyOrgIds",surveyOrgIds);
        map.put("entrustOrgId",entrustOrgId);
        map.put("report","report");//不分页

        String retJson = "";

        String json = this.callApi(BackendApiMethodEnum.BACKEND_SURVEY_ORG_HELP, map, req);
        Type type = new TypeToken<ApiFinalResponse<List<SurveyOrgDTO>>>() {
        }.getType();
        ApiFinalResponse<List<SurveyOrgDTO>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp == null) {
            return;
        }

        // 创建excel
        List<SurveyOrgDTO> orgList = apiRsp.getResults();
        WritableWorkbook book = null; // 创建jxl工作簿

        if(beginDate==null || beginDate ==""){
            beginDate = "2019-03-01";
        }
        if(endDate==null || endDate ==""){
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            endDate = formatter.format(currentTime);
        }
        String filename ="互助超时报表" + beginDate + "-" + endDate + ".xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");

            //表头
            WritableFont wf2 = new WritableFont(WritableFont.ARIAL,11,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
            WritableCellFormat wcf2 = new WritableCellFormat(wf2);
            wcf2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf2.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            //具体内容
            WritableFont wf = new WritableFont(WritableFont.TIMES);
            wf.setColour(Colour.BLACK);
            wf.setPointSize(12);
            wf = new WritableFont(WritableFont.TIMES);
            WritableCellFormat wcf3 = new WritableCellFormat(wf);
            wcf3.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            wcf3.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中

            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("总表", 0);
            sheet.setColumnView(0,30);// 将第一列的宽度设为30
            sheet.setColumnView(1,35);// 将第一列的宽度设为30
            sheet.setColumnView(2,35);
            sheet.setColumnView(3,35);
            sheet.setColumnView(4,35);
            sheet.setColumnView(5,35);
            sheet.setColumnView(6,35);
            // 设置表头
            String caseNumMark = "";
            String caseAvgMark = "";
            String longTimeNumMark = "";
            String longTimeAvgMark = "";
            String longTimeRateMark = "";
            String vetoCaseNumMark = "";
            String vetoCaseAvgMark = "";
            String vetoNumMark = "";
            String vetoAvgMark = "";
            String vetoRateMark = "";
            if(orgList!=null && orgList.size()>0){
                SurveyHuzhuDTO surveyHuzhuDTO = orgList.get(0).getSurveyHuzhuDTO();
                if(surveyHuzhuDTO == null){
                    surveyHuzhuDTO = new SurveyHuzhuDTO();
                }
                caseNumMark = "总数：" + surveyHuzhuDTO.getCaseNum();
                caseAvgMark = "平均：" + surveyHuzhuDTO.getCaseAvg();
                longTimeNumMark = "总数：" + surveyHuzhuDTO.getLongTimeNum();
                longTimeAvgMark = "平均：" + surveyHuzhuDTO.getLongTimeAvg();
                longTimeRateMark = "互助超期率：" + surveyHuzhuDTO.getLongTimeRate() + "%";
                vetoCaseNumMark = "总数：" + surveyHuzhuDTO.getVetoCaseNum();
                vetoCaseAvgMark = "平均：" + surveyHuzhuDTO.getVetoCaseAvg();
                vetoNumMark = "总数：" + surveyHuzhuDTO.getVetoNum();
                vetoAvgMark = "平均：" + surveyHuzhuDTO.getVetoAvg();
                vetoRateMark = "互助驳回率：" + surveyHuzhuDTO.getVetoRate() + "%";
            }
            sheet.addCell(new Label(0,0,"调查机构"));
            sheet.addCell(new Label(1,0,"案件数" + "（" + caseNumMark + "," + caseAvgMark + "）"));
            sheet.addCell(new Label(2,0,"超期数" + "（" + longTimeNumMark + "," + longTimeAvgMark + "）"));
            sheet.addCell(new Label(3,0,"超期率" + "（" + longTimeRateMark + "）"));
            sheet.addCell(new Label(4,0,"驳回案件数" + "（" + vetoCaseNumMark + "," + vetoCaseAvgMark + "）"));
            sheet.addCell(new Label(5,0,"驳回次数" + "（" + vetoNumMark + "," + vetoAvgMark + "）"));
            sheet.addCell(new Label(6,0,"驳回率" + "（" + vetoRateMark + "）"));

            if(orgList!=null && !orgList.isEmpty()){
                int sheetNum = 1;
                for(int i=0; i< orgList.size(); i++){

                    //超时集合
                    List<SurveyOrgDetailDTO> longCaseList = orgList.get(i).getLongCaseList();
                    WritableSheet newLongSheet = book.createSheet(orgList.get(i).getSurveyOrgName()+"_超", sheetNum);
                    addSheetHeader(newLongSheet, wcf2, sheet);
                    for (int j = 0; j < longCaseList.size(); j++) {
                        addSheetContent(j, longCaseList.get(j), newLongSheet, wcf3);
                    }
                    sheetNum = sheetNum + 1; //+1 sheet

                    //驳回集合
                    List<SurveyOrgDetailDTO> vetoCaseList = orgList.get(i).getVetoCaseList();
                    WritableSheet newVetoSheet = book.createSheet(orgList.get(i).getSurveyOrgName()+"_驳", sheetNum);
                    addSheetHeader(newVetoSheet, wcf2, sheet);
                    for (int j = 0; j < vetoCaseList.size(); j++) {
                        addSheetContent(j, vetoCaseList.get(j), newVetoSheet, wcf3);
                    }
                    sheetNum = sheetNum + 1; //+1 sheet

                    sheet.addCell(new Label(0,i+1,  orgList.get(i).getSurveyOrgName()==null?"":orgList.get(i).getSurveyOrgName()));
                    sheet.addCell(new Label(1,i+1, String.valueOf(orgList.get(i).getCaseNum())));
                    sheet.addHyperlink(new WritableHyperlink(2, i + 1, String.valueOf(orgList.get(i).getLongNum()), newLongSheet, 0, 0));
                    sheet.addCell(new Label(3,i+1, orgList.get(i).getLongRate()+"%"));
                    sheet.addHyperlink(new WritableHyperlink(4, i + 1, String.valueOf(orgList.get(i).getVetoCaseNum()), newVetoSheet, 0, 0));
                    sheet.addHyperlink(new WritableHyperlink(5, i + 1, String.valueOf(orgList.get(i).getVetoNum()), newVetoSheet, 0, 0));
                    sheet.addCell(new Label(6,i+1, orgList.get(i).getVetoRate()+"%"));
                }
            }

            // 写入数据并关闭文件
            book.write();
            book.close();
            os.close();
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //export导出 分表 --创建表头
    private void addSheetHeader(WritableSheet sheet,WritableCellFormat wcf, WritableSheet firstSheet) {
        try {
            sheet.setColumnView(0,30);// 将第一列的宽度设为30
            sheet.setColumnView(1,15);// 将第一列的宽度设为30
            sheet.setColumnView(2,15);// 将第一列的宽度设为30
            sheet.setColumnView(3,20);// 将第一列的宽度设为30
            sheet.setColumnView(4,20);// 将第一列的宽度设为30
            sheet.setColumnView(5,15);// 将第一列的宽度设为30
            sheet.setColumnView(6,30);// 将第一列的宽度设为30
            sheet.setColumnView(7,15);// 将第一列的宽度设为30
            sheet.setColumnView(8,15);// 将第一列的宽度设为30
            sheet.setColumnView(9,15);// 将第一列的宽度设为30
            sheet.setColumnView(10,15);// 将第一列的宽度设为30
            sheet.setColumnView(11,15);// 将第一列的宽度设为30
            sheet.setColumnView(12,10);// 将第一列的宽度设为30
            sheet.setColumnView(13,15);// 将第一列的宽度设为30
            sheet.setColumnView(14,15);// 将第一列的宽度设为30

            sheet.addHyperlink(new WritableHyperlink(0, 0, "返回总表", firstSheet, 0, 0));
            sheet.addCell(new Label(0,1,"案件编号"));
            sheet.addCell(new Label(1,1,"保险公司"));
            sheet.addCell(new Label(2,1,"被调查人"));
            sheet.addCell(new Label(3,1,"联系方式"));
            sheet.addCell(new Label(4,1,"案件状态"));
            sheet.addCell(new Label(5,1,"委托时间"));
            sheet.addCell(new Label(6,1,"调查机构"));
            sheet.addCell(new Label(7,1,"机构案件状态"));
            sheet.addCell(new Label(8,1,"分派机构时间"));
            sheet.addCell(new Label(9,1,"机构提交时间"));
            sheet.addCell(new Label(10,1,"机构截止时间"));
            sheet.addCell(new Label(11,1,"机构时效"));
            sheet.addCell(new Label(12,1,"驳回次数"));
            sheet.addCell(new Label(13,1,"案件截止时间"));
            sheet.addCell(new Label(14,1,"案件时效"));

        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    //export导出 sheet-- 具体内容
    private void addSheetContent(int j, SurveyOrgDetailDTO info, WritableSheet sheet, WritableCellFormat wcf) {
        try{
            sheet.addCell(new Label(0, j+2, info.getSurveyCaseNo()==null?"":info.getSurveyCaseNo()));
            sheet.addCell(new Label(1, j+2, info.getEntrustOrgName()==null?"":info.getEntrustOrgName()));
            sheet.addCell(new Label(2, j+2, info.getSurveyPerson()==null?"":info.getSurveyPerson()));
            sheet.addCell(new Label(3, j+2, info.getSurveryPersonTel()==null?"":info.getSurveryPersonTel()));
            sheet.addCell(new Label(4, j+2, info.getSurveyStateName()==null?"":info.getSurveyStateName()));
            if(info.getEntrustTime() !=null){
                sheet.addCell(new Label(5, j+2, format.format(info.getEntrustTime())));
            }else{
                sheet.addCell(new Label(5, j+2, null));
            }
            sheet.addCell(new Label(6, j+2, info.getSurveyOrgName()==null?"":info.getSurveyOrgName()));
            sheet.addCell(new Label(7, j+2, info.getOrgSurveyStateName()==null?"":info.getOrgSurveyStateName()));

            if(info.getOrgAssignDate() !=null){
                sheet.addCell(new Label(8, j+2, format.format(info.getOrgAssignDate())));
            }else {
                sheet.addCell(new Label(8, j + 2, null));
            }
            if(info.getOrgReportDate() !=null){
                sheet.addCell(new Label(9, j+2, format.format(info.getOrgReportDate())));
            }else{
                sheet.addCell(new Label(9, j+2, null));
            }
            if(info.getOrgEndTime() !=null){
                sheet.addCell(new Label(10, j+2, format.format(info.getOrgEndTime())));
            }else{
                sheet.addCell(new Label(10, j+2, null));
            }
            sheet.addCell(new Label(11, j+2, info.getOrgEfficiencyState()==null?"":info.getOrgEfficiencyState()));
            sheet.addCell(new Number(12, j+2, info.getVetoNum()));

            if(info.getEndTime() !=null){
                sheet.addCell(new Label(13, j+2, format.format(info.getEndTime())));
            }else{
                sheet.addCell(new Label(13, j+2, null));
            }
            sheet.addCell(new Label(14, j+2, info.getEfficiencyState()==null?"":info.getEfficiencyState()));
        } catch (WriteException e) {
            e.printStackTrace();
        }

    }

    /**
     * 互助超时报表详情
     * */
    @RequestMapping(value = "huZhuList")
    public ModelAndView huZhuList(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyOrgDetailDTO>>>(){};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_ORG_HELP_DETAIL,null,req);
        model.put("apiRsp",apiFinalResponse);
        model.put("surveyOrgId",req.getParameter("surveyOrgId"));
        model.put("type",req.getParameter("type"));
        model.put("beginDate",req.getParameter("beginDate"));
        model.put("endDate",req.getParameter("endDate"));
        model.put("entrustOrgIds",req.getParameter("entrustOrgIds"));
        return new ModelAndView("/survey/report/huZhuList",model);
    }
}
