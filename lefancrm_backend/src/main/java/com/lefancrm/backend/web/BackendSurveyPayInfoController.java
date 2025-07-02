package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.StaffOrganDto;
import com.lefancrm.backend.dto.SurveyFranchiseeDto;
import com.lefancrm.backend.dto.SurveyPayInfoAjaxFinancialData;
import com.lefancrm.backend.dto.SurveyPayInfoDto;
import com.lefancrm.backend.dto.feere.SurveyInvestigatorReInfoDto;
import com.lefancrm.backend.dto.financial.FinancialCostBearDto;
import com.lefancrm.backend.dto.financial.FinancialCostDetailsDto;
import com.lefancrm.backend.dto.financial.FinancialFileDto;
import com.lefancrm.backend.dto.financial.FinancialReApplyDto;
import com.lefancrm.backend.dto.staff.*;
import com.lefancrm.backend.util.FileUtils;
import com.lefancrm.backend.util.PDFFinaancial;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/survey/pay/")
public class BackendSurveyPayInfoController  extends BackendBaseController{

    @Value("${survey.file.path.sftp}")
    public String httpFilePath;
    @Value("${survey.file.source.sftp}")
    private String surveySource;
    @Value("${survey.temp.path}")
    private String realTempPath;

    @RequestMapping(value = "list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        model.put("menuCode",req.getParameter("menuCode"));
        model.put("payNo",req.getParameter("payNo"));
        model.put("orgId",req.getParameter("orgId"));
        model.put("sourceSupportType",req.getParameter("sourceSupportType"));
        model.put("payState",req.getParameter("payStates"));
        model.put("startCreateDate",req.getParameter("startCreateDate"));
        model.put("endCreateDate",req.getParameter("endCreateDate"));
        model.put("startPayTime",req.getParameter("startPayTime"));
        model.put("endPayTime",req.getParameter("endPayTime"));
        model.put("realName",req.getParameter("realName"));

        //付款列表
        Map<String, Object> appendMap = new HashMap<String, Object>();
        if (StringUtils.isBlank(req.getParameter("payStates"))){
            appendMap.put("payState","1,2");
            model.put("payStates","1,2");
        }else {
            model.put("payStates",req.getParameter("payStates")==null?"":req.getParameter("payStates"));
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyPayInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_PAY_INFO_LIST, appendMap, req);
        model.put("apiRsp",apiFinalResponse);

        //所有的调查方
        appendMap = new HashMap<String, Object>();
        appendMap.put("menuType",1); //不分页
        appendMap.put("surveyCode","franchisee");
        typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
        List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
        model.put("franchisees",franchisees);

        Map params=new HashMap();
        //社保缴纳公司
        appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode","company");
        appendMap.put("havePage","no");//不分页
        typeToken = new TypeToken<ApiFinalResponse<List<StaffCompanyDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, null);
        List<StaffCompanyDto> companys = (List<StaffCompanyDto>)apiFinalResponse.getResults();
        model.put("companys", companys);
        params.put("companysJson", JsonUtil.objectToJson(companys));
        //成本归属公司
        typeToken = new TypeToken<ApiFinalResponse<List<StaffBudgetCompanyDto>>>() {};
        appendMap.put("surveyCode","budgetCompany");
        appendMap.put("havePage","no");//不分页
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, null);
        List<StaffBudgetCompanyDto> budCompanys = (List<StaffBudgetCompanyDto>) apiFinalResponse.getResults();
        model.put("budCompanys", budCompanys);
        params.put("budCompanysJson", JsonUtil.objectToJson(budCompanys));
        //机构/部门 信息
        appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode","organ");
        appendMap.put("havePage","no");//不分页
        typeToken = new TypeToken<ApiFinalResponse<List<StaffOrganDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
        List<StaffOrganDto> organs = (List<StaffOrganDto>)apiFinalResponse.getResults();
        model.put("organs", organs);
        params.put("organsJson", JsonUtil.objectToJson(organs));
        //科室信息
        appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode","department");
        appendMap.put("havePage","no");//不分页
        typeToken = new TypeToken<ApiFinalResponse<List<StaffDepartmentDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
        List<StaffDepartmentDto> departments = (List<StaffDepartmentDto>)apiFinalResponse.getResults();
        model.put("departments", departments);
        params.put("departmentsJson", JsonUtil.objectToJson(departments));

        //小组信息
        appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode","team");
        appendMap.put("havePage","no");//不分页
        typeToken = new TypeToken<ApiFinalResponse<List<StaffTeamDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
        List<StaffTeamDto> teams = (List<StaffTeamDto>)apiFinalResponse.getResults();
        model.put("teams", teams);
        params.put("teamsJson", JsonUtil.objectToJson(teams));

        //岗位信息
        appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode","jobPost");
        appendMap.put("havePage","no");//不分页
        typeToken = new TypeToken<ApiFinalResponse<List<StaffJobPostDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
        List<StaffJobPostDto> jobPosts = (List<StaffJobPostDto>)apiFinalResponse.getResults();
        model.put("jobPosts", jobPosts);
        params.put("jobPostsJson", JsonUtil.objectToJson(jobPosts));

        model.put("params", params);

        //多选
        model.put("surveyOrgIds",req.getParameter("surveyOrgIds")==null?"":req.getParameter("surveyOrgIds"));
        model.put("sourceSupportTypes",req.getParameter("sourceSupportTypes")==null?"":req.getParameter("sourceSupportTypes"));
        model.put("companyIds",req.getParameter("companyIds")==null?"":req.getParameter("companyIds"));
        model.put("budCompanyIds",req.getParameter("budCompanyIds")==null?"":req.getParameter("budCompanyIds"));
        model.put("organIds",req.getParameter("organIds")==null?"":req.getParameter("organIds"));
        model.put("departmentIds",req.getParameter("departmentIds")==null?"":req.getParameter("departmentIds"));
        model.put("teamIds",req.getParameter("teamIds")==null?"":req.getParameter("teamIds"));
        model.put("jobPostIds",req.getParameter("jobPostIds")==null?"":req.getParameter("jobPostIds"));
        model.put("payTypes",req.getParameter("payTypes")==null?"":req.getParameter("payTypes"));

        return new ModelAndView("/survey/pay/list",model);
    }

    @RequestMapping(value = "operate")
    public String operate(HttpServletRequest req,HttpServletResponse rsp){
        Map<String,Object> param =  new HashMap<>();
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_PAY_INFO_OPERATE, param, req, rsp);
    }

    @RequestMapping(value = "operateView")
    public ModelAndView operateView(HttpServletRequest req,HttpServletResponse rsp){
        Map<String,Object> model =  new HashMap<String,Object>();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyPayInfoDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_PAY_INFO_INFO, null, req);
        SurveyPayInfoDto surveyPayInfo = (SurveyPayInfoDto) apiFinalResponse.getResults();
        model.put("surveyPayInfo",surveyPayInfo);
        model.put("btnCode",req.getParameter("btnCode"));
        //查看凭证页面
        if ("filesView".equals(model.get("btnCode"))){
            return new ModelAndView("/survey/pay/filesView",model);
        }
        return new ModelAndView("/survey/pay/operateView",model);
    }

    /**
     * 导出
     */
    @RequestMapping(value = "/export")
    public void export(HttpServletRequest req, HttpServletResponse rsp) {
        String dataType = req.getParameter("dataType");
        if ("financial".equals(dataType)){
            String type = req.getParameter("type");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyPayInfoAjaxFinancialData>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_PAY_INFO_AJAX_FINANCIAL_DATA, null, req);
            if (apiFinalResponse.getResults() != null){
                SurveyPayInfoAjaxFinancialData data = (SurveyPayInfoAjaxFinancialData) apiFinalResponse.getResults();
                File file = PDFFinaancial.generate(realTempPath + File.separator + "pdf" + File.separator + System.currentTimeMillis(), data);
                Map<String,Object> jsonMap = new HashMap<>();
                if ("2".equals(type)){//打印
                    jsonMap.put("fileUrl",file.getPath().replace("/mnt/sftp/files/",httpFilePath));
                }else{//下载(zip)
                    String zipPath = realTempPath + File.separator + "zip" + File.separator + System.currentTimeMillis() + File.separator + data.getTitle() + ".rar";
                    List<File> files = new ArrayList<>();
                    files.add(file);
                    if (data.getFiles().size() > 0){
                        for (FinancialFileDto dataFile : data.getFiles()) {
                            if (!StringUtils.isEmpty(dataFile.getFilePath())){
                                files.add(new File(dataFile.getFilePath().replace(httpFilePath,"/mnt/sftp/files/")));
                            }
                        }

                    }
                    try {
                        file = FileUtils.zipTwo(zipPath,files);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    jsonMap.put("fileUrl",file.getPath().replace("/mnt/sftp/files/",httpFilePath));
                }
                String json = sh.zj100.common.util.JsonUtil.objectToJson(jsonMap);
                this.outputJson(json, rsp);
            }
            return;
        }
        else{
            Map map = new HashMap();
            map.put("payNo",req.getParameter("payNo")==null?"":req.getParameter("payNo"));
            map.put("startCreateDate",req.getParameter("startCreateDate")==null?"":req.getParameter("startCreateDate"));
            map.put("endCreateDate",req.getParameter("endCreateDate")==null?"":req.getParameter("endCreateDate"));
            map.put("startPayTime",req.getParameter("startPayTime")==null?"":req.getParameter("startPayTime"));
            map.put("endPayTime",req.getParameter("endPayTime")==null?"":req.getParameter("endPayTime"));

            //下拉多选
            map.put("sourceSupportTypes",req.getParameter("sourceSupportTypes")==null?"":req.getParameter("sourceSupportTypes"));
            map.put("payStates",req.getParameter("payStates")==null?"":req.getParameter("payStates"));
            map.put("companyIds",req.getParameter("companyIds")==null?"":req.getParameter("companyIds"));
            map.put("budCompanyIds",req.getParameter("budCompanyIds")==null?"":req.getParameter("budCompanyIds"));
            map.put("organIds",req.getParameter("organIds")==null?"":req.getParameter("organIds"));
            map.put("departmentIds",req.getParameter("departmentIds")==null?"":req.getParameter("departmentIds"));
            map.put("teamIds",req.getParameter("teamIds")==null?"":req.getParameter("teamIds"));
            map.put("JobPostIds",req.getParameter("JobPostIds")==null?"":req.getParameter("JobPostIds"));
            map.put("payTypes",req.getParameter("payTypes")==null?"":req.getParameter("payTypes"));
            map.put("pageSize", 100);
            if ("yes".equals(req.getParameter("exportNew"))){
                map.put("exportNew","yes");
            }
            TypeToken<ApiFinalResponse<List<SurveyPayInfoDto>>> typeToken = new TypeToken<ApiFinalResponse<List<SurveyPayInfoDto>>>() {};
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            ApiFinalResponse apiFinalResponse2 = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_PAY_INFO_LIST, map, req);
            List<SurveyPayInfoDto> list = ecursiveResult(apiFinalResponse2, typeToken, map, req, new ArrayList<>(apiFinalResponse2.getCount()), 0);
            if ("yes".equals(req.getParameter("exportNew"))){
                String zipPath = realTempPath + File.separator + "zip" + File.separator + System.currentTimeMillis() + File.separator
                        + "付款数据明细-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".rar";
                List<File> files = new ArrayList<>();
                Map<String,List<SurveyPayInfoDto>> companyData = list.stream().filter(p -> p.getSocialSecurityCompany() != null).collect(Collectors.groupingBy(SurveyPayInfoDto :: getSocialSecurityCompany));
                companyData.forEach((e,v) -> {
                    String companyName = v.get(0).getSocialSecurityCompany();

                    //生成excel
                    HSSFWorkbook wb = new HSSFWorkbook();
                    HSSFCellStyle style = wb.createCellStyle();
                    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                    HSSFSheet sheet = wb.createSheet(companyName);//建立sheet对象
                    int index = -1;
                    HSSFRow row = sheet.createRow(0);
                    row.createCell(++index).setCellValue("人员主体机构");
                    row.createCell(++index).setCellValue("费用归属机构");
                    row.createCell(++index).setCellValue("月份");
                    row.createCell(++index).setCellValue("发生日期");
                    row.createCell(++index).setCellValue("报销单编号");
                    row.createCell(++index).setCellValue("付费类型");
                    row.createCell(++index).setCellValue("报销属性");
                    row.createCell(++index).setCellValue("报销名称");
                    row.createCell(++index).setCellValue("分项金额");
                    row.createCell(++index).setCellValue("金额");
                    row.createCell(++index).setCellValue("报销人姓名");
                    row.createCell(++index).setCellValue("备注");
                    row.createCell(++index).setCellValue("支付日期");
                    row.createCell(++index).setCellValue("计税金额");
                    row.createCell(++index).setCellValue("其中进项税");
                    row.createCell(++index).setCellValue("职位");
                    int rowIndex = 1;
                    for (SurveyPayInfoDto item : v) {
                        String name = "";
                        Integer payType = item.getPayType() == null ? 1: item.getPayType();
                        switch (payType){
                            case 1 : name = item.getSurveyFranchisee().getAcceptUser(); break;
                            case 2 :
                            case 3 :
                            case 7 :
                                name = item.getSurveyInvestigatorDto().getRealName(); break;
                            case 8 :
                                name = item.getFinaHospitalAccount().getAccountName(); break;
                            case 9 :
                            case 10 :
                            case 11 :
                                name = item.getFinancialReApply().getPayeeName();
                        }
                        if (StringUtils.isEmpty(name)){
                            name = item.getRealName();
                        }
                        if (name == null) name = "";

                        if (item.getPayType() == 2){
                            SurveyInvestigatorReInfoDto reInfo = item.getReInfo();
                            if (reInfo == null){
                                continue;
                            }
                            //室内交通费、病史费（含复印费）、住院排查费用、门诊排查费用、体检报告打印费、住宿费、跨地市交通费（汽车、火车、飞机）、跨地市交通费（自驾）、其他
                            Map<String,Double> moneyMap = new HashMap<>();
                            if (reInfo.getCityinDrivingMoney() > 0){
                                moneyMap.put("市内交通费",reInfo.getCityinDrivingMoney());
                            }
                            if (reInfo.getMedicalHistoryMoney() > 0){
                                moneyMap.put("病史费（含复印费）",reInfo.getMedicalHistoryMoney());
                            }
                            if (reInfo.getTroubleshootingMoney() > 0){
                                moneyMap.put("住院排查费用",reInfo.getTroubleshootingMoney());
                            }
                            if (reInfo.getOpcTroubleshootingMoney() > 0){
                                moneyMap.put("门诊排查费用",reInfo.getOpcTroubleshootingMoney());
                            }
                            if (reInfo.getPrintingMoney() > 0){
                                moneyMap.put("体检报告打印费",reInfo.getPrintingMoney());
                            }
                            if (reInfo.getAccommodatioMoney() > 0){
                                moneyMap.put("住宿费",reInfo.getAccommodatioMoney());
                            }
                            if (reInfo.getCrossDrivingMoney() > 0){
                                moneyMap.put("跨地市交通费（汽车、火车、飞机）",reInfo.getCrossDrivingMoney());
                            }
                            if (reInfo.getSelfDrivingMoney() > 0){
                                moneyMap.put("跨地市交通费（自驾）",reInfo.getSelfDrivingMoney());
                            }
                            if (reInfo.getOtherMoney() > 0){
                                moneyMap.put("其他",reInfo.getOtherMoney());
                            }
                            Iterator<String> iterator = moneyMap.keySet().iterator();
                            while (iterator.hasNext()){
                                //合并单元格
                                if (moneyMap.size() > 1){
                                    CellRangeAddress region = new CellRangeAddress(rowIndex, rowIndex + moneyMap.keySet().size() - 1, 9, 9);
                                    sheet.addMergedRegion(region);
                                }

                                String key = iterator.next();
                                HSSFRow tempRow = sheet.createRow(rowIndex);
                                index = -1;
                                tempRow.createCell(++index).setCellValue(item.getSocialSecurityCompany());
                                tempRow.createCell(++index).setCellValue(item.getOrgan());
                                tempRow.createCell(++index).setCellValue(item.getPayTime() == null ? "" : new SimpleDateFormat("MM").format(item.getPayTime()));
                                tempRow.createCell(++index).setCellValue(item.getPayTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(item.getPayTime()));
                                tempRow.createCell(++index).setCellValue(item.getPayNo());
                                String payTypeName = "";
                                switch (item.getPayType()){
                                    case 1 : payTypeName = "调查费"; break;
                                    case 2 : payTypeName = "费用报销"; break;
                                    case 3 : payTypeName = "渠道费用报销"; break;
                                    case 4 : payTypeName = "员工工资"; break;
                                    case 5 : payTypeName = "员工绩效"; break;
                                    case 6 : payTypeName = "离职预报销"; break;
                                    case 7 : payTypeName = "退费"; break;
                                    case 8 : payTypeName = "垫付业务"; break;
                                    case 9 : payTypeName = "日常费用报销"; break;
                                    case 10 : payTypeName = "对公支付"; break;
                                    case 11 : payTypeName = "借款单"; break;
                                }
                                tempRow.createCell(++index).setCellValue(payTypeName);
                                tempRow.createCell(++index).setCellValue(key);
                                tempRow.createCell(++index).setCellValue(key);
                                tempRow.createCell(++index).setCellValue(moneyMap.get(key));
                                tempRow.createCell(++index).setCellValue(item.getAppPayMoney() == null ? 0D : item.getAppPayMoney());
                                tempRow.createCell(++index).setCellValue(name);
                                tempRow.createCell(++index).setCellValue("");
                                tempRow.createCell(++index).setCellValue(item.getPayTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(item.getPayTime()));
                                tempRow.createCell(++index).setCellValue(0D);
                                tempRow.createCell(++index).setCellValue(0D);
                                tempRow.createCell(++index).setCellValue("");
                                rowIndex = rowIndex + 1;
                            }
                        }else if (item.getPayType() == 9 || item.getPayType() == 10){
                            List<FinancialCostDetailsDto> costDetails = item.getCostDetails();
                            List<FinancialCostBearDto> costBears = item.getCostBears();
                            //合并单元格
                            if (costDetails.size() > 1 && costBears.size() == 1)
                            {
                                FinancialCostBearDto costBear = costBears.get(0);
                                CellRangeAddress region = new CellRangeAddress(rowIndex, rowIndex + costDetails.size() - 1, 9, 9);
                                sheet.addMergedRegion(region);
                                for (FinancialCostDetailsDto costDetail : costDetails) {
                                    HSSFRow tempRow = sheet.createRow(rowIndex);
                                    index = -1;
                                    tempRow.createCell(++index).setCellValue(item.getSocialSecurityCompany());
                                    tempRow.createCell(++index).setCellValue(costBear.getDepartmentName());
                                    tempRow.createCell(++index).setCellValue(item.getPayTime() == null ? "" : new SimpleDateFormat("MM").format(item.getPayTime()));
                                    tempRow.createCell(++index).setCellValue(item.getPayTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(item.getPayTime()));
                                    tempRow.createCell(++index).setCellValue(item.getPayNo());
                                    String payTypeName = "";
                                    switch (item.getPayType()){
                                        case 1 : payTypeName = "调查费"; break;
                                        case 2 : payTypeName = "费用报销"; break;
                                        case 3 : payTypeName = "渠道费用报销"; break;
                                        case 4 : payTypeName = "员工工资"; break;
                                        case 5 : payTypeName = "员工绩效"; break;
                                        case 6 : payTypeName = "离职预报销"; break;
                                        case 7 : payTypeName = "退费"; break;
                                        case 8 : payTypeName = "垫付业务"; break;
                                        case 9 : payTypeName = "日常费用报销"; break;
                                        case 10 : payTypeName = "对公支付"; break;
                                        case 11 : payTypeName = "借款单"; break;
                                    }
                                    tempRow.createCell(++index).setCellValue(payTypeName);
                                    tempRow.createCell(++index).setCellValue(costDetail.getCostTypeName());
                                    tempRow.createCell(++index).setCellValue(costDetail.getCostTypeName());
                                    tempRow.createCell(++index).setCellValue(costDetail.getCostMoney() == null ? 0D : costDetail.getCostMoney());
                                    tempRow.createCell(++index).setCellValue(item.getAppPayMoney() == null ? 0D : item.getAppPayMoney());
                                    tempRow.createCell(++index).setCellValue(name);
                                    tempRow.createCell(++index).setCellValue(costDetail.getCostDesc() == null ? "" : costDetail.getCostDesc());
                                    tempRow.createCell(++index).setCellValue(item.getPayTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(item.getPayTime()));
                                    tempRow.createCell(++index).setCellValue(0D);
                                    tempRow.createCell(++index).setCellValue(0D);
                                    tempRow.createCell(++index).setCellValue("");
                                    rowIndex = rowIndex + 1;
                                }
                            }
                            else if (costDetails.size() == 1 && costBears.size() > 1 || costDetails.size() == 1 && costBears.size() == 1)
                            {
                                FinancialCostDetailsDto costType = costDetails.get(0);
                                CellRangeAddress region = new CellRangeAddress(rowIndex, rowIndex + costBears.size() - 1, 9, 9);
                                sheet.addMergedRegion(region);
                                for (FinancialCostBearDto costBear : costBears) {
                                    HSSFRow tempRow = sheet.createRow(rowIndex);
                                    index = -1;
                                    tempRow.createCell(++index).setCellValue(item.getSocialSecurityCompany());
                                    tempRow.createCell(++index).setCellValue(costBear.getDepartmentName());
                                    tempRow.createCell(++index).setCellValue(item.getPayTime() == null ? "" : new SimpleDateFormat("MM").format(item.getPayTime()));
                                    tempRow.createCell(++index).setCellValue(item.getPayTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(item.getPayTime()));
                                    tempRow.createCell(++index).setCellValue(item.getPayNo());
                                    String payTypeName = "";
                                    switch (item.getPayType()){
                                        case 1 : payTypeName = "调查费"; break;
                                        case 2 : payTypeName = "费用报销"; break;
                                        case 3 : payTypeName = "渠道费用报销"; break;
                                        case 4 : payTypeName = "员工工资"; break;
                                        case 5 : payTypeName = "员工绩效"; break;
                                        case 6 : payTypeName = "离职预报销"; break;
                                        case 7 : payTypeName = "退费"; break;
                                        case 8 : payTypeName = "垫付业务"; break;
                                        case 9 : payTypeName = "日常费用报销"; break;
                                        case 10 : payTypeName = "对公支付"; break;
                                        case 11 : payTypeName = "借款单"; break;
                                    }
                                    tempRow.createCell(++index).setCellValue(payTypeName);
                                    tempRow.createCell(++index).setCellValue(costType.getCostTypeName());
                                    tempRow.createCell(++index).setCellValue(costType.getCostTypeName());
                                    tempRow.createCell(++index).setCellValue(costBear.getShareCost() == null ? 0D : costBear.getShareCost());
                                    tempRow.createCell(++index).setCellValue(item.getAppPayMoney() == null ? 0D : item.getAppPayMoney());
                                    tempRow.createCell(++index).setCellValue(name);
                                    tempRow.createCell(++index).setCellValue(costBear.getCostDesc() == null ? "" : costBear.getCostDesc());
                                    tempRow.createCell(++index).setCellValue(item.getPayTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(item.getPayTime()));
                                    tempRow.createCell(++index).setCellValue(0D);
                                    tempRow.createCell(++index).setCellValue(0D);
                                    tempRow.createCell(++index).setCellValue("");
                                    rowIndex = rowIndex + 1;
                                }
                            }
                        }else{
                            HSSFRow tempRow = sheet.createRow(rowIndex);
                            index = -1;
                            tempRow.createCell(++index).setCellValue(item.getSocialSecurityCompany());
                            tempRow.createCell(++index).setCellValue(item.getOrgan());
                            tempRow.createCell(++index).setCellValue(item.getPayTime() == null ? "" : new SimpleDateFormat("MM").format(item.getPayTime()));
                            tempRow.createCell(++index).setCellValue(item.getPayTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(item.getPayTime()));
                            tempRow.createCell(++index).setCellValue(item.getPayNo());
                            String payTypeName = "";
                            switch (item.getPayType()){
                                case 1 : payTypeName = "调查费"; break;
                                case 2 : payTypeName = "费用报销"; break;
                                case 3 : payTypeName = "渠道费用报销"; break;
                                case 4 : payTypeName = "员工工资"; break;
                                case 5 : payTypeName = "员工绩效"; break;
                                case 6 : payTypeName = "离职预报销"; break;
                                case 7 : payTypeName = "退费"; break;
                                case 8 : payTypeName = "垫付业务"; break;
                                case 9 : payTypeName = "日常费用报销"; break;
                                case 10 : payTypeName = "对公支付"; break;
                                case 11 : payTypeName = "借款单"; break;
                            }
                            tempRow.createCell(++index).setCellValue(payTypeName);
                            tempRow.createCell(++index).setCellValue("");
                            tempRow.createCell(++index).setCellValue("");
                            tempRow.createCell(++index).setCellValue(item.getAppPayMoney() == null ? 0D : item.getAppPayMoney());
                            tempRow.createCell(++index).setCellValue(item.getAppPayMoney() == null ? 0D : item.getAppPayMoney());
                            tempRow.createCell(++index).setCellValue(name);
                            tempRow.createCell(++index).setCellValue("");
                            tempRow.createCell(++index).setCellValue(item.getPayTime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(item.getPayTime()));
                            tempRow.createCell(++index).setCellValue(0D);
                            tempRow.createCell(++index).setCellValue(0D);
                            tempRow.createCell(++index).setCellValue("");
                            rowIndex = rowIndex + 1;
                        }
                    }
                    File file = new File(realTempPath + File.separator + "excel" + File.separator + System.currentTimeMillis() + File.separator + companyName + ".xls");
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    try {
                        FileOutputStream out = new FileOutputStream(file.getPath());
                        wb.write(out);
                        out.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    files.add(file);
                });
                //创建压缩包
                File file = FileUtils.zipTwo(zipPath,files);
                BufferedInputStream in = null;
                BufferedOutputStream  out = null;
                try {
                    in = new BufferedInputStream(new FileInputStream(zipPath));
                    out = new BufferedOutputStream(rsp.getOutputStream());
                    rsp.setContentType("application/x-download;charset=utf-8");
                    rsp.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
                    byte[] buffer = new byte[8192];
                    int count = 0;
                    while ((count = in.read(buffer, 0, 8192)) != -1) {
                        out.write(buffer, 0, count);
                    }
                    out.flush();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (null != in) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            // e.printStackTrace();
                        }
                    }
                    if (null != out) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            // e.printStackTrace();
                        }
                    }
                }
            }else if ("yesPrint".equals(req.getParameter("exportNew"))){
                String zipPath = realTempPath + File.separator + "zip" + File.separator
                        + "批量打印数据-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".rar";

                List<File> files = new ArrayList<>();
                //创建压缩包
                Map paramMap = new HashMap();
                for (SurveyPayInfoDto surveyPayInfoDto : list) {
                    TypeToken typeToken3 = new TypeToken<ApiFinalResponse<SurveyPayInfoAjaxFinancialData>>() {};
                    paramMap.put("id",surveyPayInfoDto.getId());
                    ApiFinalResponse apiFinalResponse3 = this.callApi(typeToken3, BackendApiMethodEnum.BACKEND_SURVEY_PAY_INFO_AJAX_FINANCIAL_DATA, paramMap, req);
                    if (apiFinalResponse3.getResults() != null) {
                        SurveyPayInfoAjaxFinancialData data = (SurveyPayInfoAjaxFinancialData) apiFinalResponse3.getResults();
                        File file = PDFFinaancial.generate(realTempPath + File.separator + "pdf", data);
                        files.add(file);
                    }
                }
                File file = FileUtils.zipTwo(zipPath,files);
                BufferedInputStream in = null;
                BufferedOutputStream  out = null;
                try {
                    in = new BufferedInputStream(new FileInputStream(zipPath));
                    out = new BufferedOutputStream(rsp.getOutputStream());
                    rsp.setContentType("application/x-download;charset=utf-8");
                    rsp.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
                    byte[] buffer = new byte[8192];
                    int count = 0;
                    while ((count = in.read(buffer, 0, 8192)) != -1) {
                        out.write(buffer, 0, count);
                    }
                    out.flush();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (null != in) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            // e.printStackTrace();
                        }
                    }
                    if (null != out) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            // e.printStackTrace();
                        }
                    }
                }
            }else{
                export(list,rsp,df,null,"付款管理");
            }
        }
    }

    //递归 分段处理数据
    public List<SurveyPayInfoDto> ecursiveResult(ApiFinalResponse apiFinalResponse, TypeToken<ApiFinalResponse<List<SurveyPayInfoDto>>> typeToken, Map map, HttpServletRequest req, List<SurveyPayInfoDto> temporaryList, int pageNum) {
        List<SurveyPayInfoDto> results = new ArrayList<>((List<SurveyPayInfoDto>) apiFinalResponse.getResults());
        temporaryList.addAll(results);
        pageNum = pageNum + 1;
        while (pageNum * 100 < apiFinalResponse.getCount()) {
            map.put("pageIndex", pageNum * 100);
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_PAY_INFO_LIST, map, req);
            return ecursiveResult(apiFinalResponse, typeToken, map, req, temporaryList, pageNum);
        }
        return temporaryList;
    }

    public void export(List<SurveyPayInfoDto> list,HttpServletResponse rsp ,SimpleDateFormat df ,String excelPath,String fileName){
        // 创建excel
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFSheet sheet_0 = wb.createSheet(fileName);//建立sheet对象
        sheet_0.setDefaultColumnWidth(15);
        sheet_0.setDefaultRowHeightInPoints(15);
        //设置头
        HSSFRow cell_row_0 = sheet_0.createRow(0);
        int index = -1;
        cell_row_0.createCell(++index).setCellValue("付款编号");
        cell_row_0.createCell(++index).setCellValue("付款类型");
        cell_row_0.createCell(++index).setCellValue("社保缴纳公司");
        cell_row_0.createCell(++index).setCellValue("成本归属公司");
        cell_row_0.createCell(++index).setCellValue("机构/部门");
        cell_row_0.createCell(++index).setCellValue("科室");
        cell_row_0.createCell(++index).setCellValue("小组");
        cell_row_0.createCell(++index).setCellValue("岗位");
        cell_row_0.createCell(++index).setCellValue("姓名");
        cell_row_0.createCell(++index).setCellValue("申请付款金额");
        cell_row_0.createCell(++index).setCellValue("备注");
        cell_row_0.createCell(++index).setCellValue("实际付款金额");
        cell_row_0.createCell(++index).setCellValue("税费");
        cell_row_0.createCell(++index).setCellValue("付款状态");
        cell_row_0.createCell(++index).setCellValue("收款人姓名");
        cell_row_0.createCell(++index).setCellValue("银行名称");
        cell_row_0.createCell(++index).setCellValue("银行账号");
        cell_row_0.createCell(++index).setCellValue("申请时间");
        cell_row_0.createCell(++index).setCellValue("实际付款时间");
        cell_row_0.createCell(++index).setCellValue("财务人员");
        int size = 1;
        for (int i = 0; i < list.size(); i++) {
            SurveyPayInfoDto surveyPayInfo = list.get(i);
            HSSFRow cell_row_i = sheet_0.createRow(size);
            cell_row_i.setHeightInPoints(18);//行高设置成18px
            index = -1;
            cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getPayNo()==null?"":surveyPayInfo.getPayNo());

            int payType = surveyPayInfo.getPayType();
            if(payType ==1){cell_row_i.createCell(++index).setCellValue("调查费");
            }else if(payType ==2){cell_row_i.createCell(++index).setCellValue("费用报销");
            }else if(payType ==3){cell_row_i.createCell(++index).setCellValue("渠道费用报销");
            }else if(payType ==4){cell_row_i.createCell(++index).setCellValue("员工工资");
            }else if(payType ==5){cell_row_i.createCell(++index).setCellValue("员工绩效");
            }else if(payType ==6){cell_row_i.createCell(++index).setCellValue("离职预报销");
            }else if(payType ==7){cell_row_i.createCell(++index).setCellValue("退费");
            }else if(payType ==8){cell_row_i.createCell(++index).setCellValue("垫付业务");
            }else if(payType ==9){cell_row_i.createCell(++index).setCellValue("日常费用报销");
            }else if(payType ==10){cell_row_i.createCell(++index).setCellValue("对公支付");
            }else if(payType ==11){cell_row_i.createCell(++index).setCellValue("借款单");
            }else{cell_row_i.createCell(++index).setCellValue("");}

            cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getSocialSecurityCompany()==null?"":surveyPayInfo.getSocialSecurityCompany());
            cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getCompanyName()==null?"":surveyPayInfo.getCompanyName());
            cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getOrgan()==null?"":surveyPayInfo.getOrgan());
            cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getDepartment()==null?"":surveyPayInfo.getDepartment());
            cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getTeam()==null?"":surveyPayInfo.getTeam());
            cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getJobPost()==null?"":surveyPayInfo.getJobPost());
            cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getRealName()==null?"":surveyPayInfo.getRealName());
            cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getAppPayMoney()==null?0D:surveyPayInfo.getAppPayMoney());
            cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getRemark()==null?"":surveyPayInfo.getRemark());
            cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getRealPayMoney()==null?0D:surveyPayInfo.getRealPayMoney());
            cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getPayTax()==null?0D:surveyPayInfo.getPayTax());
            int payState = surveyPayInfo.getPayState();
            if(payState ==1){cell_row_i.createCell(++index).setCellValue("待付款");
            }else if(payState ==2){cell_row_i.createCell(++index).setCellValue("待确认到账");
            }else if(payState ==3){cell_row_i.createCell(++index).setCellValue("已确认到账");
            }else if(payState ==4){cell_row_i.createCell(++index).setCellValue("已驳回");
            }else{cell_row_i.createCell(++index).setCellValue("");}

            if(payType==1){
                if(surveyPayInfo.getSurveyFranchisee() !=null){
                    cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getSurveyFranchisee().getAcceptUser()==null?"":surveyPayInfo.getSurveyFranchisee().getAcceptUser());
                    cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getSurveyFranchisee().getBankName()==null?"":surveyPayInfo.getSurveyFranchisee().getBankName());
                    cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getSurveyFranchisee().getBankCard()==null?"":surveyPayInfo.getSurveyFranchisee().getBankCard());
                }else{
                    cell_row_i.createCell(++index).setCellValue("");
                    cell_row_i.createCell(++index).setCellValue("");
                    cell_row_i.createCell(++index).setCellValue("");
                }
            }else if(payType==2 || payType==3 || payType==7) {
                if(surveyPayInfo.getSurveyInvestigatorDto() != null){
                    cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getSurveyInvestigatorDto().getRealName()==null?"":surveyPayInfo.getSurveyInvestigatorDto().getRealName());
                    cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getSurveyInvestigatorDto().getBankName()==null?"":surveyPayInfo.getSurveyInvestigatorDto().getBankName());
                    cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getSurveyInvestigatorDto().getBankNo()==null?"":surveyPayInfo.getSurveyInvestigatorDto().getBankNo());
                }else{
                    cell_row_i.createCell(++index).setCellValue("");
                    cell_row_i.createCell(++index).setCellValue("");
                    cell_row_i.createCell(++index).setCellValue("");
                }
            }else if(payType==8) {
                if(surveyPayInfo.getSurveyInvestigatorDto() != null){
                    cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getFinaHospitalAccount().getAccountName()==null?"":surveyPayInfo.getFinaHospitalAccount().getAccountName());
                    cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getFinaHospitalAccount().getBankName()==null?"":surveyPayInfo.getFinaHospitalAccount().getBankName());
                    cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getFinaHospitalAccount().getBranchBankName()==null?"":surveyPayInfo.getFinaHospitalAccount().getBranchBankName());
                }else{
                    cell_row_i.createCell(++index).setCellValue("");
                    cell_row_i.createCell(++index).setCellValue("");
                    cell_row_i.createCell(++index).setCellValue("");
                }
            }else if(payType==9||payType==10||payType==11) {
                if(surveyPayInfo.getFinancialReApply() != null){
                    cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getFinancialReApply().getPayeeName()==null?"":surveyPayInfo.getFinancialReApply().getPayeeName());
                    cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getFinancialReApply().getBankName()==null?"":surveyPayInfo.getFinancialReApply().getBankName());
                    cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getFinancialReApply().getPayeeNo()==null?"":surveyPayInfo.getFinancialReApply().getPayeeNo());
                }else{
                    cell_row_i.createCell(++index).setCellValue("");
                    cell_row_i.createCell(++index).setCellValue("");
                    cell_row_i.createCell(++index).setCellValue("");
                }
            }


            else{
                cell_row_i.createCell(++index).setCellValue("");
                cell_row_i.createCell(++index).setCellValue("");
                cell_row_i.createCell(++index).setCellValue("");
            }

            cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getCreateTime()==null?"":df.format(surveyPayInfo.getCreateTime()));
            cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getPayTime()==null?"":df.format(surveyPayInfo.getPayTime()));
            cell_row_i.createCell(++index).setCellValue(surveyPayInfo.getPayUserName()==null?"":surveyPayInfo.getPayUserName());
            size += 1;
        }

        if (null == excelPath){
            try (OutputStream output = rsp.getOutputStream()){
                //设置响应头
                rsp.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName.concat("-").concat(LocalDate.now().toString()), "UTF-8") + ".xls");
                rsp.setContentType("application/msexcel");
                wb.write(output);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            try (OutputStream output = new FileOutputStream(excelPath + File.separator + fileName+".xls")) {
                wb.write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
