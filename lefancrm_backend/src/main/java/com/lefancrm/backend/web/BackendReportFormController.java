package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2018/8/24.
 * 报表
 */
@Controller
@RequestMapping(value = "/reportForm")
public class BackendReportFormController extends BackendBaseController{

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /*
    * 索赔报表list
    */
    @RequestMapping(value = "/caseClaimReportList")
    public ModelAndView caseClaimReportList(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId = req.getParameter("orgId");
//        String date = req.getParameter("date");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseClaimReportDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CLAIM_REPORT_LIST, null, req);

        //parentId为1的机构list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_ORG_LIST_BY_ORGPARENTID, null, req);
        List<OrgInfoDto> orgInfoDtos = (List<OrgInfoDto>) apiFinalResponse1.getResults();

        Map model = new HashMap();
        model.put("page", page);
        model.put("orgInfoDtos",orgInfoDtos);
        model.put("orgId",orgId==null?"":orgId);

        //如果没有选择时间，默认为当天
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        model.put("date",date==null?simpleDateFormat.format(new Date()):date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if((startDate == null || "".equals(startDate)) && (endDate == null || "".equals(endDate))){
            model.put("startDate",startDate == null?simpleDateFormat.format(new Date()):startDate);
            model.put("endDate",(endDate == null || "".equals(endDate))?simpleDateFormat.format(new Date()):endDate);
        }else{
            model.put("startDate",startDate==null?"":startDate);
            model.put("endDate",endDate==null?"":endDate);
        }


        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/reportForm/caseClaimReportList",model);
    }

    @RequestMapping(value = "/caseClaimReportListExport")
    public void caseClaimReportListExport(HttpServletRequest req, HttpServletResponse rsp) {

        String orgId = req.getParameter("orgId");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");

        Map map = new HashMap();
        map.put("orgId",orgId==null?"":orgId);
        //如果没有选择时间，默认为当天
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if((startDate == null || "".equals(startDate)) && (endDate == null || "".equals(endDate))){
            map.put("startDate",startDate == null?simpleDateFormat.format(new Date()):startDate);
            map.put("endDate",(endDate == null || "".equals(endDate))?simpleDateFormat.format(new Date()):endDate);
        }else{
            map.put("startDate",startDate==null?"":startDate);
            map.put("endDate",endDate==null?"":endDate);
        }

        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_CASE_CLAIM_REPORT_LIST, map, req);
        Type type = new TypeToken<ApiFinalResponse<List<CaseClaimReportDto>>>() {
        }.getType();
        ApiFinalResponse<List<CaseClaimReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp == null) {
            return;
        }
        // 创建excel
        List<CaseClaimReportDto> queryList = apiRsp.getResults();
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename ="索赔报表.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("索赔报表", 0);
            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            sheet.setColumnView(0,25);// 将第一列的宽度设为30
            sheet.mergeCells(0, 0, 0, 1);//
            sheet.mergeCells(1, 0, 1, 1);//
            sheet.mergeCells(2, 0, 3, 0);//
            sheet.mergeCells(4, 0, 5, 0);//
            sheet.mergeCells(6, 0, 8, 0);
            sheet.mergeCells(9, 0, 11, 0);
            sheet.mergeCells(12, 0, 13, 0);
            sheet.mergeCells(14, 0, 15, 0);
            sheet.mergeCells(16, 0, 17, 0);
            sheet.mergeCells(18, 0, 19, 0);
            sheet.mergeCells(20, 0, 20, 1);
            // 设置表头
            sheet.addCell(new Label(0,0,"机构名称",wcf));
            sheet.addCell(new Label(1,0,"日期",wcf));
            sheet.addCell(new Label(2,0,"当日结案数",wcf));
            sheet.addCell(new Label(4,0,"当月结案数",wcf));
            sheet.addCell(new Label(6,0,"当日回款金额",wcf));
            sheet.addCell(new Label(9,0,"当月回款金额",wcf));
            sheet.addCell(new Label(12,0,"解约案件数",wcf));
            sheet.addCell(new Label(14,0,"累计未结案计数",wcf));
            sheet.addCell(new Label(16,0,"累计已结案计数",wcf));
            sheet.addCell(new Label(18,0,"累计结案类型",wcf));
            sheet.addCell(new Label(20,0,"总案件数",wcf));

            sheet.addCell(new Label(2,1,"调解案件",wcf));
            sheet.addCell(new Label(3,1,"诉讼案件",wcf));
            sheet.addCell(new Label(4,1,"调解案件",wcf));
            sheet.addCell(new Label(5,1,"诉讼案件",wcf));
            sheet.addCell(new Label(6,1,"服务费",wcf));
            sheet.addCell(new Label(7,1,"贷款本金",wcf));
            sheet.addCell(new Label(8,1,"贷款利息",wcf));
            sheet.addCell(new Label(9,1,"服务费",wcf));
            sheet.addCell(new Label(10,1,"贷款本金",wcf));
            sheet.addCell(new Label(11,1,"贷款利息",wcf));
            sheet.addCell(new Label(12,1,"代理案件",wcf));
            sheet.addCell(new Label(13,1,"贷款案件",wcf));
            sheet.addCell(new Label(14,1,"代理案件",wcf));
            sheet.addCell(new Label(15,1,"贷款案件",wcf));
            sheet.addCell(new Label(16,1,"代理案件",wcf));
            sheet.addCell(new Label(17,1,"贷款案件",wcf));
            sheet.addCell(new Label(18,1,"调解案件",wcf));
            sheet.addCell(new Label(19,1,"诉讼案件",wcf));

            if(queryList!=null && !queryList.isEmpty()){
                for(int i=0; i<queryList.size(); i++){
                    sheet.addCell(new Label(0, i+2,   queryList.get(i).getOrgName()==null?"":queryList.get(i).getOrgName()));
                    if(queryList.get(i).getDate() !=null){
                        sheet.addCell(new Label(1,i+2,format.format(queryList.get(i).getDate())));
                    }else{
                        sheet.addCell(new Label(1, i+2, null));
                    }
                    sheet.addCell(new Number(2,i+2,  queryList.get(i).getMediateClosedDayNum()==null?0:queryList.get(i).getMediateClosedDayNum()));
                    sheet.addCell(new Number(3,i+2,  queryList.get(i).getLitigationClosedDayNum()==null?0:queryList.get(i).getLitigationClosedDayNum()));
                    sheet.addCell(new Number(4,i+2,  queryList.get(i).getMediateClosedMonthNum()==null?0:queryList.get(i).getMediateClosedMonthNum()));
                    sheet.addCell(new Number(5,i+2,  queryList.get(i).getLitigationClosedMonthNum()==null?0:queryList.get(i).getLitigationClosedMonthNum()));
                    sheet.addCell(new Number(6,i+2,  queryList.get(i).getReturnServiceDayMoney()==null?0D:queryList.get(i).getReturnServiceDayMoney()));
                    sheet.addCell(new Number(7,i+2,  queryList.get(i).getReturnPrincipalDayMoney()==null?0D:queryList.get(i).getReturnPrincipalDayMoney()));
                    sheet.addCell(new Number(8,i+2,  queryList.get(i).getReturnInterestDayMoney()==null?0D:queryList.get(i).getReturnInterestDayMoney()));
                    sheet.addCell(new Number(9,i+2,  queryList.get(i).getReturnServiceMonthMoney()==null?0D:queryList.get(i).getReturnServiceMonthMoney()));
                    sheet.addCell(new Number(10,i+2, queryList.get(i).getReturnPrincipalMonthMoney()==null?0D:queryList.get(i).getReturnPrincipalMonthMoney()));
                    sheet.addCell(new Number(11,i+2, queryList.get(i).getReturnInterestMonthMoney()==null?0D:queryList.get(i).getReturnInterestMonthMoney()));
                    sheet.addCell(new Number(12,i+2, queryList.get(i).getReleaseAgentNum()==null?0:queryList.get(i).getReleaseAgentNum()));
                    sheet.addCell(new Number(13,i+2, queryList.get(i).getReleaseLoanNum()==null?0:queryList.get(i).getReleaseLoanNum()));
                    sheet.addCell(new Number(14,i+2, queryList.get(i).getUnclosedAgentNum()==null?0:queryList.get(i).getUnclosedAgentNum()));
                    sheet.addCell(new Number(15,i+2, queryList.get(i).getUnclosedLoanNum()==null?0:queryList.get(i).getUnclosedLoanNum()));
                    sheet.addCell(new Number(16,i+2, queryList.get(i).getClosedAgentNum()==null?0:queryList.get(i).getClosedAgentNum()));
                    sheet.addCell(new Number(17,i+2, queryList.get(i).getClosedLoanNum()==null?0:queryList.get(i).getClosedLoanNum()));
                    sheet.addCell(new Number(18,i+2, queryList.get(i).getMediateClosedNum()==null?0:queryList.get(i).getMediateClosedNum()));
                    sheet.addCell(new Number(19,i+2, queryList.get(i).getLitigationClosedNum()==null?0:queryList.get(i).getLitigationClosedNum()));
                    sheet.addCell(new Number(20,i+2, queryList.get(i).getTotalNum()==null?0:queryList.get(i).getTotalNum()));
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

    /*
    * 贷款报表list
    */
    @RequestMapping(value = "/caseLoanReportList")
    public ModelAndView caseLoanReportList(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId = req.getParameter("orgId");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");

        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseLoanReportDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_LOAN_REPORT_LIST, null, req);

        //parentId为1的机构list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_ORG_LIST_BY_ORGPARENTID, null, req);
        List<OrgInfoDto> orgInfoDtos = (List<OrgInfoDto>) apiFinalResponse1.getResults();

        Map model = new HashMap();
        model.put("page", page);
        model.put("orgInfoDtos",orgInfoDtos);
        model.put("orgId",orgId==null?"":orgId);
        //如果没有选择时间，默认为当天
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if((startDate == null || "".equals(startDate)) && (endDate == null || "".equals(endDate))){
            model.put("startDate",startDate == null?simpleDateFormat.format(new Date()):startDate);
            model.put("endDate",(endDate == null || "".equals(endDate))?simpleDateFormat.format(new Date()):endDate);
        }else{
            model.put("startDate",startDate==null?"":startDate);
            model.put("endDate",endDate==null?"":endDate);
        }

        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/reportForm/caseLoanReportList",model);
    }

    @RequestMapping(value = "/caseLoanReportListExport")
    public void caseLoanReportListExport(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId = req.getParameter("orgId");
        String date = req.getParameter("date");

        Map map = new HashMap();
        map.put("orgId",orgId==null?"":orgId);
        map.put("date",date==null?"":date);

        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_CASE_LOAN_REPORT_LIST, map, req);
        Type type = new TypeToken<ApiFinalResponse<List<CaseLoanReportDto>>>() {
        }.getType();
        ApiFinalResponse<List<CaseLoanReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp == null) {
            return;
        }
        // 创建excel
        List<CaseLoanReportDto> queryList = apiRsp.getResults();
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename ="贷款报表.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("贷款报表", 0);

            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            sheet.setColumnView(0,25);// 将第一列的宽度设为30
            sheet.mergeCells(0, 0, 0, 1);//
            sheet.mergeCells(1, 0, 2, 0);//
            sheet.mergeCells(3, 0, 4, 0);//
            sheet.mergeCells(5, 0, 6, 0);//
            sheet.mergeCells(7, 0, 8, 0);//
            sheet.mergeCells(9, 0, 10, 0);//
            sheet.mergeCells(11, 0, 12, 0);//
            sheet.mergeCells(13, 0, 14, 0);//
            sheet.mergeCells(15, 0, 15, 1);
            sheet.mergeCells(16, 0, 16, 1);
            sheet.mergeCells(17, 0, 17, 1);
            sheet.mergeCells(18, 0, 18, 1);
            sheet.mergeCells(19, 0, 19, 1);
            sheet.mergeCells(20, 0, 20, 1);
            sheet.mergeCells(21, 0, 21, 1);
            sheet.mergeCells(22, 0, 22, 1);
            // 设置表头
            sheet.addCell(new Label(0,0,"机构名称",wcf));
            sheet.addCell(new Label(1,0,"累计放款金额",wcf));
            sheet.addCell(new Label(3,0,"累计回款金额",wcf));
            sheet.addCell(new Label(5,0,"累计应收金额",wcf));
            sheet.addCell(new Label(7,0,"当日放款金额",wcf));
            sheet.addCell(new Label(9,0,"当日回款金额",wcf));
            sheet.addCell(new Label(11,0,"当月放款金额",wcf));
            sheet.addCell(new Label(13,0,"当月回款金额",wcf));
            sheet.addCell(new Label(15,0,"贷款周期4个月的案件数",wcf));
            sheet.addCell(new Label(16,0,"贷款周期6个月的案件数",wcf));
            sheet.addCell(new Label(17,0,"贷款周期8个月的案件",wcf));
            sheet.addCell(new Label(18,0,"贷款周期10个月的案件",wcf));
            sheet.addCell(new Label(19,0,"贷款周期12个月的案件",wcf));
            sheet.addCell(new Label(20,0,"超过12个月案件",wcf));
            sheet.addCell(new Label(21,0,"逾期案件数量",wcf));
            sheet.addCell(new Label(22,0,"时间",wcf));

            sheet.addCell(new Label(1,1,"乐凡",wcf));
            sheet.addCell(new Label(2,1,"苏宁",wcf));
            sheet.addCell(new Label(3,1,"乐凡",wcf));
            sheet.addCell(new Label(4,1,"苏宁",wcf));
            sheet.addCell(new Label(5,1,"乐凡",wcf));
            sheet.addCell(new Label(6,1,"苏宁",wcf));
            sheet.addCell(new Label(7,1,"乐凡",wcf));
            sheet.addCell(new Label(8,1,"苏宁",wcf));
            sheet.addCell(new Label(9,1,"乐凡",wcf));
            sheet.addCell(new Label(10,1,"苏宁",wcf));
            sheet.addCell(new Label(11,1,"乐凡",wcf));
            sheet.addCell(new Label(12,1,"苏宁",wcf));
            sheet.addCell(new Label(13,1,"乐凡",wcf));
            sheet.addCell(new Label(14,1,"苏宁",wcf));

            if(queryList!=null && !queryList.isEmpty()){
                for(int i=0; i<queryList.size(); i++){
                    sheet.addCell(new Label(0, i+2,   queryList.get(i).getOrgName()==null?"":queryList.get(i).getOrgName()));
                    sheet.addCell(new Number(1,i+2,   queryList.get(i).getTotalLefanLoanMoney()==null?0D:queryList.get(i).getTotalLefanLoanMoney()));
                    sheet.addCell(new Number(2,i+2,   queryList.get(i).getTotalSuningLoanMoney()==null?0D:queryList.get(i).getTotalSuningLoanMoney()));
                    sheet.addCell(new Number(3,i+2,   queryList.get(i).getTotalLefanReturnMoney()==null?0D:queryList.get(i).getTotalLefanReturnMoney()));
                    sheet.addCell(new Number(4,i+2,   queryList.get(i).getTotalSuningReturnMoney()==null?0D:queryList.get(i).getTotalSuningReturnMoney()));
                    sheet.addCell(new Number(5,i+2,   queryList.get(i).getTotalLefanReceivableMoney()==null?0D:queryList.get(i).getTotalLefanReceivableMoney()));
                    sheet.addCell(new Number(6,i+2,   queryList.get(i).getTotalSuningReceivableMoney()==null?0D:queryList.get(i).getTotalSuningReceivableMoney()));
                    sheet.addCell(new Number(7,i+2,   queryList.get(i).getTotalLfloanDayMoney()==null?0D:queryList.get(i).getTotalLfloanDayMoney()));
                    sheet.addCell(new Number(8,i+2,   queryList.get(i).getTotalSnloanDayMoney()==null?0D:queryList.get(i).getTotalSnloanDayMoney()));
                    sheet.addCell(new Number(9,i+2,   queryList.get(i).getTotalLfreturnDayMoney()==null?0D:queryList.get(i).getTotalLfreturnDayMoney()));
                    sheet.addCell(new Number(10,i+2,  queryList.get(i).getTotalSnreturnDayMoney()==null?0D:queryList.get(i).getTotalSnreturnDayMoney()));
                    sheet.addCell(new Number(11,i+2,  queryList.get(i).getTotalLfloanMonthMoney()==null?0D:queryList.get(i).getTotalLfloanMonthMoney()));
                    sheet.addCell(new Number(12,i+2,  queryList.get(i).getTotalSnloanMonthMoney()==null?0D:queryList.get(i).getTotalSnloanMonthMoney()));
                    sheet.addCell(new Number(13,i+2,  queryList.get(i).getTotalLfreturnMonthMoney()==null?0D:queryList.get(i).getTotalLfreturnMonthMoney()));
                    sheet.addCell(new Number(14,i+2,  queryList.get(i).getTotalSnreturnMonthMoney()==null?0D:queryList.get(i).getTotalSnreturnMonthMoney()));
                    sheet.addCell(new Number(15,i+2,  queryList.get(i).getLoanFourNum()==null?0:queryList.get(i).getLoanFourNum()));
                    sheet.addCell(new Number(16,i+2,  queryList.get(i).getLoanSixNum()==null?0:queryList.get(i).getLoanSixNum()));
                    sheet.addCell(new Number(17,i+2,  queryList.get(i).getLoanEightNum()==null?0:queryList.get(i).getLoanEightNum()));
                    sheet.addCell(new Number(18,i+2,  queryList.get(i).getLoanTenNum()==null?0:queryList.get(i).getLoanTenNum()));
                    sheet.addCell(new Number(19,i+2,  queryList.get(i).getLoanDecNum()==null?0:queryList.get(i).getLoanDecNum()));
                    sheet.addCell(new Number(20,i+2,  queryList.get(i).getLoanOutdecNum()==null?0:queryList.get(i).getLoanOutdecNum()));
                    sheet.addCell(new Number(21,i+2,  queryList.get(i).getLoanExpectNum()==null?0:queryList.get(i).getLoanExpectNum()));
                    if(queryList.get(i).getDate() !=null){
                        sheet.addCell(new Label(22,i+2,format.format(queryList.get(i).getDate())));
                    }else{
                        sheet.addCell(new Label(22, i+2, null));
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

    /*
    * 个人业务台账报表list
    */
    @RequestMapping(value = "/casePersonalInfoList")
    public ModelAndView casePersonalInfoList(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId = req.getParameter("orgId");
        String caseNo = req.getParameter("caseNo");
        String userName = req.getParameter("userName");
        String userTel = req.getParameter("userTel");
        String caseType = req.getParameter("caseType");
        String isTarget = req.getParameter("isTarget");
        String isSign = req.getParameter("isSign");
        String isApplyFinance = req.getParameter("isApplyFinance");
        String salesmanName = req.getParameter("salesmanName");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");

        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CasePersonalInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_PERSONAL_INFO_LIST, null, req);

        //parentId为1的机构list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_ORG_LIST_BY_ORGPARENTID, null, req);
        List<OrgInfoDto> orgInfoDtos = (List<OrgInfoDto>) apiFinalResponse1.getResults();

        Map model = new HashMap();
        model.put("page", page);
        model.put("orgInfoDtos",orgInfoDtos);
        model.put("orgId",orgId==null?"":orgId);
        model.put("caseNo",caseNo==null?"":caseNo);
        model.put("userName",userName==null?"":userName);
        model.put("userTel",userTel==null?"":userTel);
        model.put("caseType",caseType==null?"":caseType);
        model.put("isTarget",isTarget==null?"":isTarget);
        model.put("isSign",isSign==null?"":isSign);
        model.put("isApplyFinance",isApplyFinance==null?"":isApplyFinance);
        model.put("salesmanName",salesmanName==null?"":salesmanName);
        model.put("startDate",startDate==null?"":startDate);
        model.put("endDate",endDate==null?"":endDate);

        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/reportForm/casePersonalInfoList",model);
    }

    /**
     * 个人业务台账报表详情页面
     */
    @RequestMapping(value = "/casePersonalInfoView")
    public ModelAndView casePersonalInfoView(HttpServletRequest req , HttpServletResponse rsp) {
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CasePersonalInfoDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_PERSONAL_INFO_BY_ID, null, req);
        CasePersonalInfoDto casePersonalInfoDto = (CasePersonalInfoDto) apiFinalResponse.getResults();
        model.put("casePersonalInfo", casePersonalInfoDto);
        return new ModelAndView("/reportForm/casePersonalInfoView",model);
    }

    @RequestMapping(value = "/casePersonalInfoListExport")
    public void casePersonalInfoListExport(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId = req.getParameter("orgId");
        String caseNo = req.getParameter("caseNo");
        String userName = req.getParameter("userName");
        String userTel = req.getParameter("userTel");

        Map map = new HashMap();
        map.put("orgId",orgId==null?"":orgId);
        map.put("caseNo",caseNo==null?"":caseNo);
        map.put("userName",userName==null?"":userName);
        map.put("userTel",userTel==null?"":userTel);

        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_CASE_PERSONAL_INFO_LIST, map, req);
        Type type = new TypeToken<ApiFinalResponse<List<CasePersonalInfoDto>>>() {
        }.getType();
        ApiFinalResponse<List<CasePersonalInfoDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp == null) {
            return;
        }
        // 创建excel
        List<CasePersonalInfoDto> queryList = apiRsp.getResults();
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename ="个人业务台账报表.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("个人业务台账报表", 0);

            // 设置表头
            sheet.addCell(new Label(0,0,"案件编号"));
            sheet.addCell(new Label(1,0,"案件类型"));
            sheet.addCell(new Label(2,0,"机构名称"));
            sheet.addCell(new Label(3,0,"用户姓名"));
            sheet.addCell(new Label(4,0,"身份证号"));
            sheet.addCell(new Label(5,0,"电话"));
            sheet.addCell(new Label(6,0,"就诊医院"));
            sheet.addCell(new Label(7,0,"床位号"));
            sheet.addCell(new Label(8,0,"伤情描述"));
            sheet.addCell(new Label(9,0,"出险地"));
            sheet.addCell(new Label(10,0,"出险时间"));
            sheet.addCell(new Label(11,0,"业务员姓名"));
            sheet.addCell(new Label(12,0,"录入时间"));
            sheet.addCell(new Label(13,0,"是否是目标客户"));
            sheet.addCell(new Label(14,0,"业务员是否接触客户"));
            sheet.addCell(new Label(15,0,"是否留下业务员联系方式"));
            sheet.addCell(new Label(16,0,"伤残等级"));
            sheet.addCell(new Label(17,0,"是否潜在客户"));
            sheet.addCell(new Label(18,0,"是否是意向客户"));
            sheet.addCell(new Label(19,0,"是否签约客户"));
            sheet.addCell(new Label(20,0,"是否超时案件"));
            sheet.addCell(new Label(21,0,"超时天数"));
            sheet.addCell(new Label(22,0,"服务协议是否规范"));
            sheet.addCell(new Label(23,0,"代办协议是否规范"));
            sheet.addCell(new Label(24,0,"委托协议是否规范"));
            sheet.addCell(new Label(25,0,"信息登记表填写是否规范"));
            sheet.addCell(new Label(26,0,"审核是否通过"));
            sheet.addCell(new Label(27,0,"预估签约服务费"));
            sheet.addCell(new Label(28,0,"预收基本费"));
            sheet.addCell(new Label(29,0,"收费方式"));
            sheet.addCell(new Label(30,0,"审核服务费"));
            sheet.addCell(new Label(31,0,"审核意见"));
            sheet.addCell(new Label(32,0,"应收服务费金额"));
            sheet.addCell(new Label(33,0,"差异原因"));
            sheet.addCell(new Label(34,0,"到账金额"));
            sheet.addCell(new Label(35,0,"到账时间"));
            sheet.addCell(new Label(36,0,"佣金是否结算"));
            sheet.addCell(new Label(37,0,"电话邀约是否成功"));
            sheet.addCell(new Label(38,0,"现场邀约是否成功"));
            sheet.addCell(new Label(39,0,"客户信息表是否提供"));
            sheet.addCell(new Label(40,0,"邀会码是否发送"));
            sheet.addCell(new Label(41,0,"预约参会时间"));
            sheet.addCell(new Label(42,0,"跟进CC"));
            sheet.addCell(new Label(43,0,"是否参会"));
            sheet.addCell(new Label(44,0,"会销签约是否成功"));
            sheet.addCell(new Label(45,0,"会销服务协议是否规范"));
            sheet.addCell(new Label(46,0,"代办协议是否规范"));
            sheet.addCell(new Label(47,0,"委托协议是否规范"));
            sheet.addCell(new Label(48,0,"援助申请协议是否规范"));
            sheet.addCell(new Label(49,0,"保密协议是否规范"));
            sheet.addCell(new Label(50,0,"会销服务费是否支付"));
            sheet.addCell(new Label(51,0,"会销签约手续是否完成"));
            sheet.addCell(new Label(52,0,"会销签约服务费是否到账"));
            sheet.addCell(new Label(53,0,"会销佣金是否结算"));
            sheet.addCell(new Label(54,0,"是否申请金融"));
            sheet.addCell(new Label(55,0,"申请贷款金额"));
            sheet.addCell(new Label(56,0,"申请垫付时间"));
            sheet.addCell(new Label(57,0,"垫付资料是否齐全"));
            sheet.addCell(new Label(58,0,"审核贷款金额"));
            sheet.addCell(new Label(59,0,"实际放款金额"));
            sheet.addCell(new Label(60,0,"放款时间"));
            sheet.addCell(new Label(61,0,"放款资方"));
            sheet.addCell(new Label(62,0,"预收通道费"));
            sheet.addCell(new Label(63,0,"预收利息"));
            sheet.addCell(new Label(64,0,"是否公司免费垫付"));
            sheet.addCell(new Label(65,0,"是否公司转接垫付"));
            sheet.addCell(new Label(66,0,"是否第三方公司垫付金融"));
            sheet.addCell(new Label(67,0,"本金"));
            sheet.addCell(new Label(68,0,"利息"));
            sheet.addCell(new Label(69,0,"通道费"));
            sheet.addCell(new Label(70,0,"贷款回款金额"));
            sheet.addCell(new Label(71,0,"贷款回款时间"));

            if(queryList!=null && !queryList.isEmpty()){
                for(int i=0; i<queryList.size(); i++){
                    sheet.addCell(new Label(0, i+1,   queryList.get(i).getCaseNo()==null?"":queryList.get(i).getCaseNo()));

                    if(queryList.get(i).getCaseType() !=null) {
                        if (queryList.get(i).getCaseType() == 1) {
                            sheet.addCell(new Label(1, i + 1, "垫付案件"));
                        } else if (queryList.get(i).getCaseType() == 2) {
                            sheet.addCell(new Label(1, i + 1, "代理案件"));
                        }
                    }
                    sheet.addCell(new Label(2,i+1,   queryList.get(i).getOrgName()==null?"":queryList.get(i).getOrgName()));
                    sheet.addCell(new Label(3,i+1,   queryList.get(i).getUserName()==null?"":queryList.get(i).getUserName()));
                    sheet.addCell(new Label(4,i+1,   queryList.get(i).getIdCard()==null?"":queryList.get(i).getIdCard()));
                    sheet.addCell(new Label(5,i+1,   queryList.get(i).getUserTel()==null?"":queryList.get(i).getUserTel()));
                    sheet.addCell(new Label(6,i+1,   queryList.get(i).getVisitHospital()==null?"":queryList.get(i).getVisitHospital()));
                    sheet.addCell(new Label(7,i+1,   queryList.get(i).getBedNumber()==null?"":queryList.get(i).getBedNumber()));
                    sheet.addCell(new Label(8,i+1,   queryList.get(i).getInjuryDesc()==null?"":queryList.get(i).getInjuryDesc()));
                    sheet.addCell(new Label(9,i+1,   queryList.get(i).getDangerAddress()==null?"":queryList.get(i).getDangerAddress()));
                    if(queryList.get(i).getDangerTime()!=null){
                        sheet.addCell(new Label(10,i+1,  format.format(queryList.get(i).getDangerTime())));
                    }else{
                        sheet.addCell(new Label(10,i+1,null));
                    }
                    sheet.addCell(new Label(11,i+1,  queryList.get(i).getSalesmanName()==null?"":queryList.get(i).getSalesmanName()));
                    if(queryList.get(i).getIntoTime()!=null){
                        sheet.addCell(new Label(12,i+1,  format.format(queryList.get(i).getIntoTime())));
                    }else{
                        sheet.addCell(new Label(12,i+1,null));
                    }
                    if(queryList.get(i).getIsTarget()==null || queryList.get(i).getIsTarget()==0){
                        sheet.addCell(new Label(13,i+1,  "否"));
                    }else if(queryList.get(i).getIsTarget()==1){
                        sheet.addCell(new Label(13,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsContact()==null || queryList.get(i).getIsContact()==0){
                        sheet.addCell(new Label(14,i+1,  "否"));
                    }else if(queryList.get(i).getIsContact()==1){
                        sheet.addCell(new Label(14,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsKeepTel()==null || queryList.get(i).getIsKeepTel()==0){
                        sheet.addCell(new Label(15,i+1,  "否"));
                    }else if(queryList.get(i).getIsKeepTel()==1){
                        sheet.addCell(new Label(15,i+1,  "是"));
                    }

                    sheet.addCell(new Label(16,i+1,  queryList.get(i).getDisabilityGrade()==null?"":queryList.get(i).getDisabilityGrade()));

                    if(queryList.get(i).getIsPotential()==null || queryList.get(i).getIsPotential()==0){
                        sheet.addCell(new Label(17,i+1,  "否"));
                    }else if(queryList.get(i).getIsPotential()==1){
                        sheet.addCell(new Label(17,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsIntention()==null || queryList.get(i).getIsIntention()==0){
                        sheet.addCell(new Label(18,i+1,  "否"));
                    }else if(queryList.get(i).getIsIntention()==1){
                        sheet.addCell(new Label(18,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsSign()==null || queryList.get(i).getIsSign()==0){
                        sheet.addCell(new Label(19,i+1,  "否"));
                    }else if(queryList.get(i).getIsSign()==1){
                        sheet.addCell(new Label(19,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsOvertime()==null || queryList.get(i).getIsOvertime()==0){
                        sheet.addCell(new Label(20,i+1,  "否"));
                    }else if(queryList.get(i).getIsOvertime()==1){
                        sheet.addCell(new Label(20,i+1,  "是"));
                    }

                    sheet.addCell(new Number(21,i+1,  queryList.get(i).getTimeoutDay()==null?0:queryList.get(i).getTimeoutDay()));

                    if(queryList.get(i).getServiceAgreement()==null || queryList.get(i).getServiceAgreement()==0){
                        sheet.addCell(new Label(22,i+1,  "否"));
                    }else if(queryList.get(i).getServiceAgreement()==1){
                        sheet.addCell(new Label(22,i+1,  "是"));
                    }

                    if(queryList.get(i).getAgentAgreement()==null || queryList.get(i).getAgentAgreement()==0){
                        sheet.addCell(new Label(23,i+1,  "否"));
                    }else if(queryList.get(i).getAgentAgreement()==1){
                        sheet.addCell(new Label(23,i+1,  "是"));
                    }

                    if(queryList.get(i).getEntrustAgreement()==null || queryList.get(i).getEntrustAgreement()==0){
                        sheet.addCell(new Label(24,i+1,  "否"));
                    }else if(queryList.get(i).getEntrustAgreement()==1){
                        sheet.addCell(new Label(24,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsRegistration()==null || queryList.get(i).getIsRegistration()==0){
                        sheet.addCell(new Label(25,i+1,  "否"));
                    }else if(queryList.get(i).getIsRegistration()==1){
                        sheet.addCell(new Label(25,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsPassed()==null || queryList.get(i).getIsPassed()==0){
                        sheet.addCell(new Label(26,i+1,  "否"));
                    }else if(queryList.get(i).getIsPassed()==1){
                        sheet.addCell(new Label(26,i+1,  "是"));
                    }

                    sheet.addCell(new Number(27,i+1,  queryList.get(i).getEstimateServiceMoney()==null?0D:queryList.get(i).getEstimateServiceMoney()));
                    sheet.addCell(new Number(28,i+1,  queryList.get(i).getAdvanceBasicMoney()==null?0D:queryList.get(i).getAdvanceBasicMoney()));
                    sheet.addCell(new Number(29,i+1,  queryList.get(i).getChargingType()==null?0D:queryList.get(i).getChargingType()));
                    sheet.addCell(new Number(30,i+1,  queryList.get(i).getAuditServiceMoney()==null?0D:queryList.get(i).getAuditServiceMoney()));
                    sheet.addCell(new Label(31,i+1,  queryList.get(i).getAuditDesc()==null?"":queryList.get(i).getAuditDesc()));
                    sheet.addCell(new Number(32,i+1,  queryList.get(i).getReceivableServiceMoney()==null?0D:queryList.get(i).getReceivableServiceMoney()));
                    sheet.addCell(new Label(33,i+1,  queryList.get(i).getDifferenceCause()==null?"":queryList.get(i).getDifferenceCause()));
                    sheet.addCell(new Number(34,i+1,  queryList.get(i).getReceivedMoney()==null?0:queryList.get(i).getReceivedMoney()));

                    if(queryList.get(i).getReceivedTime() !=null){
                        sheet.addCell(new Label(35,i+1,format.format(queryList.get(i).getReceivedTime())));
                    }else{
                        sheet.addCell(new Label(35, i+1, null));
                    }

                    if(queryList.get(i).getIsSettlementCommission()==null || queryList.get(i).getIsSettlementCommission()==0){
                        sheet.addCell(new Label(36,i+1,  "否"));
                    }else if(queryList.get(i).getIsSettlementCommission()==1){
                        sheet.addCell(new Label(36,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsCallInvitation()==null || queryList.get(i).getIsCallInvitation()==0){
                        sheet.addCell(new Label(37,i+1,  "否"));
                    }else if(queryList.get(i).getIsCallInvitation()==1){
                        sheet.addCell(new Label(37,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsSiteInvitation()==null || queryList.get(i).getIsSiteInvitation()==0){
                        sheet.addCell(new Label(38,i+1,  "否"));
                    }else if(queryList.get(i).getIsSiteInvitation()==1){
                        sheet.addCell(new Label(38,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsInformationSubmit()==null || queryList.get(i).getIsInformationSubmit()==0){
                        sheet.addCell(new Label(39,i+1,  "否"));
                    }else if(queryList.get(i).getIsInformationSubmit()==1){
                        sheet.addCell(new Label(39,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsSendInvitationCode()==null || queryList.get(i).getIsSendInvitationCode()==0){
                        sheet.addCell(new Label(40,i+1,  "否"));
                    }else if(queryList.get(i).getIsSendInvitationCode()==1){
                        sheet.addCell(new Label(40,i+1,  "是"));
                    }

                    if(queryList.get(i).getBespokeMeetingTime() !=null){
                        sheet.addCell(new Label(41,i+1,format.format(queryList.get(i).getBespokeMeetingTime())));
                    }else{
                        sheet.addCell(new Label(41, i+1, null));
                    }

                    sheet.addCell(new Label(42,i+1,  queryList.get(i).getFollowCc()==null?"":queryList.get(i).getFollowCc()));

                    if(queryList.get(i).getIsMeeting()==null ||queryList.get(i).getIsMeeting()==0){
                        sheet.addCell(new Label(43,i+1,  "否"));
                    }else if(queryList.get(i).getIsMeeting()==1){
                        sheet.addCell(new Label(43,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsSaleSign()==null || queryList.get(i).getIsSaleSign()==0){
                        sheet.addCell(new Label(44,i+1,  "否"));
                    }else if(queryList.get(i).getIsSaleSign()==1){
                        sheet.addCell(new Label(44,i+1,  "是"));
                    }

                    if(queryList.get(i).getMeetingServiceAgreement()==null ||queryList.get(i).getMeetingServiceAgreement()==0){
                        sheet.addCell(new Label(45,i+1,  "否"));
                    }else if(queryList.get(i).getMeetingServiceAgreement()==1){
                        sheet.addCell(new Label(45,i+1,  "是"));
                    }

                    if(queryList.get(i).getMeetingAgentAgreement()==null || queryList.get(i).getMeetingAgentAgreement()==0){
                        sheet.addCell(new Label(46,i+1,  "否"));
                    }else if(queryList.get(i).getMeetingAgentAgreement()==1){
                        sheet.addCell(new Label(46,i+1,  "是"));
                    }

                    if(queryList.get(i).getMeetingEntrustAgreement()==null || queryList.get(i).getMeetingEntrustAgreement()==0){
                        sheet.addCell(new Label(47,i+1,  "否"));
                    }else if(queryList.get(i).getMeetingEntrustAgreement()==1){
                        sheet.addCell(new Label(47,i+1,  "是"));
                    }

                    if(queryList.get(i).getAssistanceApplyAgreement()==null || queryList.get(i).getAssistanceApplyAgreement()==0){
                        sheet.addCell(new Label(48,i+1,  "否"));
                    }else if(queryList.get(i).getAssistanceApplyAgreement()==1){
                        sheet.addCell(new Label(48,i+1,  "是"));
                    }

                    if(queryList.get(i).getMeetingSecrecyAgreement()==null || queryList.get(i).getMeetingSecrecyAgreement()==0){
                        sheet.addCell(new Label(49,i+1,  "否"));
                    }else if(queryList.get(i).getMeetingSecrecyAgreement()==1){
                        sheet.addCell(new Label(49,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsMeetingServiceMoney()==null || queryList.get(i).getIsMeetingServiceMoney()==0){
                        sheet.addCell(new Label(50,i+1,  "否"));
                    }else if(queryList.get(i).getIsMeetingServiceMoney()==1){
                        sheet.addCell(new Label(50,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsMeetingSign()==null || queryList.get(i).getIsMeetingSign()==0){
                        sheet.addCell(new Label(51,i+1,  "否"));
                    }else if(queryList.get(i).getIsMeetingSign()==1){
                        sheet.addCell(new Label(51,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsMeetingReceived()==null || queryList.get(i).getIsMeetingReceived()==0){
                        sheet.addCell(new Label(52,i+1,  "否"));
                    }else if(queryList.get(i).getIsMeetingReceived()==1){
                        sheet.addCell(new Label(52,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsMeetingSettCommission()==null || queryList.get(i).getIsMeetingSettCommission()==0){
                        sheet.addCell(new Label(53,i+1,  "否"));
                    }else if(queryList.get(i).getIsMeetingSettCommission()==1){
                        sheet.addCell(new Label(53,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsApplyFinance()==null || queryList.get(i).getIsApplyFinance()==0){
                        sheet.addCell(new Label(54,i+1,  "否"));
                    }else if(queryList.get(i).getIsApplyFinance()==1){
                        sheet.addCell(new Label(54,i+1,  "是"));
                    }
                    sheet.addCell(new Number(55,i+1,  queryList.get(i).getApplyLoanMoney()==null?0D:queryList.get(i).getApplyLoanMoney()));

                    if(queryList.get(i).getApplyLoanTime() !=null){
                        sheet.addCell(new Label(56,i+1,format.format(queryList.get(i).getApplyLoanTime())));
                    }else{
                        sheet.addCell(new Label(56, i+1, null));
                    }

                    if(queryList.get(i).getIsSucLoanFile()==null || queryList.get(i).getIsSucLoanFile()==0){
                        sheet.addCell(new Label(57,i+1,  "否"));
                    }else if(queryList.get(i).getIsSucLoanFile()==1){
                        sheet.addCell(new Label(57,i+1,  "是"));
                    }

                    sheet.addCell(new Number(58,i+1,  queryList.get(i).getCheckLoanMoney()==null?0D:queryList.get(i).getCheckLoanMoney()));
                    sheet.addCell(new Number(59,i+1,  queryList.get(i).getLoanMoney()==null?0D:queryList.get(i).getLoanMoney()));

                    if(queryList.get(i).getLoanTime() !=null){
                        sheet.addCell(new Label(60,i+1,format.format(queryList.get(i).getLoanTime())));
                    }else{
                        sheet.addCell(new Label(60, i+1, null));
                    }

                    if(queryList.get(i).getLoanCapitalName()!=null){
                        if("1".equals(queryList.get(i).getLoanCapitalName())){
                            sheet.addCell(new Label(61,i+1,"苏宁"));
                        }else if("2".equals(queryList.get(i).getLoanCapitalName())){
                            sheet.addCell(new Label(61,i+1,"乐凡"));
                        }
                    }

                    sheet.addCell(new Number(62,i+1,  queryList.get(i).getAdvanceChannelMoney()==null?0D:queryList.get(i).getAdvanceChannelMoney()));
                    sheet.addCell(new Number(63,i+1,  queryList.get(i).getAdvanceInterestMoney()==null?0D:queryList.get(i).getAdvanceInterestMoney()));
                    if(queryList.get(i).getIsFreeLoan()==null || queryList.get(i).getIsFreeLoan()==0){
                        sheet.addCell(new Label(64,i+1,  "否"));
                    }else if(queryList.get(i).getIsFreeLoan()==1){
                        sheet.addCell(new Label(64,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsTransferLoan()==null || queryList.get(i).getIsTransferLoan()==0){
                        sheet.addCell(new Label(65,i+1,  "否"));
                    }else if(queryList.get(i).getIsTransferLoan()==1){
                        sheet.addCell(new Label(65,i+1,  "是"));
                    }

                    if(queryList.get(i).getIsThirdLoan()==null || queryList.get(i).getIsThirdLoan()==0){
                        sheet.addCell(new Label(66,i+1,  "否"));
                    }else if(queryList.get(i).getIsThirdLoan()==1){
                        sheet.addCell(new Label(66,i+1,  "是"));
                    }

                    sheet.addCell(new Number(67,i+1,  queryList.get(i).getCapitalMoney()==null?0D:queryList.get(i).getCapitalMoney()));
                    sheet.addCell(new Number(68,i+1,  queryList.get(i).getInterestMoney()==null?0D:queryList.get(i).getInterestMoney()));
                    sheet.addCell(new Number(69,i+1,  queryList.get(i).getChannelMoney()==null?0D:queryList.get(i).getChannelMoney()));
                    sheet.addCell(new Number(70,i+1,  queryList.get(i).getLoanReturnMoney()==null?0D:queryList.get(i).getLoanReturnMoney()));
                    if(queryList.get(i).getLoanReturnTime() !=null){
                        sheet.addCell(new Label(71,i+1,format.format(queryList.get(i).getLoanReturnTime())));
                    }else{
                        sheet.addCell(new Label(71, i+1, null));
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


    /*
    * 个人业务案件日报表list
    */
    @RequestMapping(value = "/casePersonalDayReportList")
    public ModelAndView casePersonalDayReportList(HttpServletRequest req, HttpServletResponse rsp) {
        String type = req.getParameter("type");
        String orgId = req.getParameter("orgId");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String directorName = req.getParameter("directorName");
        String salesmanName = req.getParameter("salesmanName");
        String isDisability = req.getParameter("isDisability");
        String isSum = req.getParameter("isSum");
        String month = req.getParameter("month");
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }

        TypeToken typeToken = new TypeToken<ApiFinalResponse<CasePersonalDayReportListDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_PERSONAL_REPORT_LIST, null, req);

        CasePersonalDayReportListDto dto =  (CasePersonalDayReportListDto)apiFinalResponse.getResults();
        //根据“是否汇总数据”，回传不同list
        if("1".equals(isSum)){
            req.setAttribute("list", dto.getOrgList());
        }else{
            req.setAttribute("list", dto.getNewList());
        }
        req.setAttribute("allSumList", dto.getAllList());

        //parentId为1的机构list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_ORG_LIST_BY_ORGPARENTID, null, req);
        List<OrgInfoDto> orgInfoDtos = (List<OrgInfoDto>) apiFinalResponse1.getResults();

        Map model = new HashMap();
        model.put("page", page);
        model.put("orgInfoDtos",orgInfoDtos);
        model.put("type",type==null?"":type);
        model.put("orgId",orgId==null?"":orgId);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if((startDate == null || "".equals(startDate)) && (endDate == null || "".equals(endDate))){
            model.put("startDate",startDate == null?simpleDateFormat.format(new Date()):startDate);
            model.put("endDate",(endDate == null || "".equals(endDate))?simpleDateFormat.format(new Date()):endDate);
        }else{
            model.put("startDate",startDate==null?"":startDate);
            model.put("endDate",endDate==null?"":endDate);
        }

        SimpleDateFormat simpleDateFormatMonth = new SimpleDateFormat("yyyy-MM");
        if(month == null || "".equals(month)){
            model.put("month",month == null?simpleDateFormatMonth.format(new Date()):month);
        }else{
            model.put("month",month==null?"":month);
        }

        model.put("directorName",directorName==null?"":directorName);
        model.put("salesmanName",salesmanName==null?"":salesmanName);
        model.put("isDisability",isDisability==null?"":isDisability);
        model.put("isSum",isSum==null?0:isSum);
        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/reportForm/casePersonalDayReportList",model);
    }

    @RequestMapping(value = "/casePersonalReportExport")
    public void casePersonalReportExport(HttpServletRequest req, HttpServletResponse rsp) {
        String reportType = req.getParameter("type");
        String orgId = req.getParameter("orgId");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String directorName = req.getParameter("directorName");
        String salesmanName = req.getParameter("salesmanName");
        String isDisability = req.getParameter("isDisability");
        String month = req.getParameter("month");
        Map map = new HashMap();
        map.put("type",reportType==null?"":reportType);
        map.put("orgId",orgId==null?"":orgId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if((startDate == null || "".equals(startDate)) && (endDate == null || "".equals(endDate))){
            map.put("startDate",startDate == null?simpleDateFormat.format(new Date()):startDate);
            map.put("endDate",(endDate == null || "".equals(endDate))?simpleDateFormat.format(new Date()):endDate);
        }else{
            map.put("startDate",startDate==null?"":startDate);
            map.put("endDate",endDate==null?"":endDate);
        }

        SimpleDateFormat simpleDateFormatMonth = new SimpleDateFormat("yyyy-MM");
        if(month == null || "".equals(month)){
            map.put("month",month == null?simpleDateFormatMonth.format(new Date()):month);
        }else{
            map.put("month",month==null?"":month);
        }
        map.put("directorName",directorName==null?"":directorName);
        map.put("salesmanName",salesmanName==null?"":salesmanName);
        map.put("isDisability",isDisability==null?"":isDisability);

        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_CASE_PERSONAL_REPORT_LIST, map, req);
        Type type = new TypeToken<ApiFinalResponse<List<CasePersonalDayReportDto>>>() {
        }.getType();
        ApiFinalResponse<List<CasePersonalDayReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp == null) {
            return;
        }
        // 创建excel
        List<CasePersonalDayReportDto> queryList = apiRsp.getResults();
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename ="个人业务案件报表.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("汇总数据", 0);

            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            WritableCellFormat wcfColour = new WritableCellFormat();
            wcfColour.setBackground(Colour.BLUE_GREY);

            sheet.setColumnView(0,25);// 将第一列的宽度设为30
            sheet.mergeCells(0, 0, 0, 1);//
            sheet.mergeCells(1, 0, 1, 1);//
            sheet.mergeCells(2, 0, 2, 1);//
            sheet.mergeCells(3, 0, 6, 0);//
            sheet.mergeCells(7, 0, 11, 0);//
            sheet.mergeCells(12, 0, 12, 1);//
            sheet.mergeCells(13, 0, 14, 0);//
            sheet.mergeCells(15, 0, 18, 0);//
            sheet.mergeCells(19, 0, 19, 1);//
            sheet.mergeCells(20, 0, 20, 1);//

            // 设置表头
            sheet.addCell(new Label(0,0,"机构",wcf));
            sheet.addCell(new Label(1,0,"主管",wcf));
            sheet.addCell(new Label(2,0,"CC",wcf));
            sheet.addCell(new Label(3,0,"代办理赔案件",wcf));
            sheet.addCell(new Label(7,0,"涉金融案件",wcf));
            sheet.addCell(new Label(12,0,"毁约案件数",wcf));
            sheet.addCell(new Label(13,0,"新增案件数",wcf));
            sheet.addCell(new Label(15,0,"合计数",wcf));
            sheet.addCell(new Label(19,0,"时间",wcf));
            sheet.addCell(new Label(20,0,"是否涉残",wcf));

            sheet.addCell(new Label(3,1,"签单数",wcf));
            sheet.addCell(new Label(4,1,"签约服务费金额",wcf));
            sheet.addCell(new Label(5,1,"预收服务费金额",wcf));
            sheet.addCell(new Label(6,1,"审核服务费金额",wcf));
            sheet.addCell(new Label(7,1,"签单贷款案件数",wcf));
            sheet.addCell(new Label(8,1,"签约服务费金额",wcf));
            sheet.addCell(new Label(9,1,"通道费金额",wcf));
            sheet.addCell(new Label(10,1,"预收服务费金额",wcf));
            sheet.addCell(new Label(11,1,"审核服务费金额",wcf));
            sheet.addCell(new Label(13,1,"签单数",wcf));
            sheet.addCell(new Label(14,1,"服务费金额",wcf));
            sheet.addCell(new Label(15,1,"总签单数",wcf));
            sheet.addCell(new Label(16,1,"总签单服务费金额",wcf));
            sheet.addCell(new Label(17,1,"审核服务费金额",wcf));
            sheet.addCell(new Label(18,1,"预收服务费总金额",wcf));

            if(queryList!=null && !queryList.isEmpty()){
                for(int i=0; i<queryList.size(); i++){
                    sheet.addCell(new Label(0, i+2,   queryList.get(i).getOrgName()==null?"":queryList.get(i).getOrgName()));
                    sheet.addCell(new Label(1,i+2,   queryList.get(i).getDirectorName()==null?"":queryList.get(i).getDirectorName()));
                    sheet.addCell(new Label(2,i+2,   queryList.get(i).getSalesmanName()==null?"":queryList.get(i).getSalesmanName()));
                    sheet.addCell(new Number(3,i+2,   queryList.get(i).getAgentSignNum()==null?0:queryList.get(i).getAgentSignNum()));
                    sheet.addCell(new Number(4,i+2,   queryList.get(i).getAgentServiceMoney()==null?0D:queryList.get(i).getAgentServiceMoney()));
                    sheet.addCell(new Number(5,i+2,   queryList.get(i).getAdvanceAgentServiceMoney()==null?0D:queryList.get(i).getAdvanceAgentServiceMoney()));
                    sheet.addCell(new Number(6,i+2,   queryList.get(i).getOkAgentServiceMoney()==null?0D:queryList.get(i).getOkAgentServiceMoney()));
                    sheet.addCell(new Number(7,i+2,   queryList.get(i).getLoanSignNum()==null?0:queryList.get(i).getLoanSignNum()));
                    sheet.addCell(new Number(8,i+2,   queryList.get(i).getLoanServiceMoney()==null?0D:queryList.get(i).getLoanServiceMoney()));
                    sheet.addCell(new Number(9,i+2,   queryList.get(i).getLoanChannelMoney()==null?0D:queryList.get(i).getLoanChannelMoney()));
                    sheet.addCell(new Number(10,i+2,  queryList.get(i).getAdvanceLoanServiceMoney()==null?0D:queryList.get(i).getAdvanceLoanServiceMoney()));
                    sheet.addCell(new Number(11,i+2,  queryList.get(i).getOkLoanServiceMoney()==null?0D:queryList.get(i).getOkLoanServiceMoney()));
                    sheet.addCell(new Number(12,i+2,  queryList.get(i).getDestroyCaseNum()==null?0:queryList.get(i).getDestroyCaseNum()));
                    sheet.addCell(new Number(13,i+2,  queryList.get(i).getNewCaseNum()==null?0D:queryList.get(i).getNewCaseNum()));
                    sheet.addCell(new Number(14,i+2,  queryList.get(i).getNewServiceMoney()==null?0D:queryList.get(i).getNewServiceMoney()));
                    sheet.addCell(new Number(15,i+2,  queryList.get(i).getTotalSignNum()==null?0:queryList.get(i).getTotalSignNum()));
                    sheet.addCell(new Number(16,i+2,  queryList.get(i).getTotalServiceMoney()==null?0D:queryList.get(i).getTotalServiceMoney()));
                    sheet.addCell(new Number(17,i+2,  queryList.get(i).getTotalOkServiceMoney()==null?0D:queryList.get(i).getTotalOkServiceMoney()));
                    sheet.addCell(new Number(18,i+2,  queryList.get(i).getTotalAdServiceMoney()==null?0D:queryList.get(i).getTotalAdServiceMoney()));

                    if(queryList.get(i).getDate() !=null){
                        sheet.addCell(new Label(19,i+2,format.format(queryList.get(i).getDate())));
                    }else{
                        sheet.addCell(new Label(19, i+2, null));
                    }
                    if(isDisability != ""){
                        if (queryList.get(i).getIsDisability() != null) {
                            if (queryList.get(i).getIsDisability() == 0) {
                                sheet.addCell(new Label(20, i + 2, "否"));
                            } else if (queryList.get(i).getIsDisability() == 1) {
                                sheet.addCell(new Label(20, i + 2, "是"));
                            }
                        }
                    }else{
                        sheet.addCell(new Label(20, i + 2, "全部"));
                    }
                }
            }
            //sheet2的内容
//            WritableSheet sheetTwo = book.createSheet("转化率", 1);
//
//            sheetTwo.setColumnView(0,25);// 将第一列的宽度设为30
//            sheetTwo.mergeCells(0, 0, 0, 1);//
//            sheetTwo.mergeCells(1, 0, 1, 1);//
//            sheetTwo.mergeCells(2, 0, 2, 1);//
//            sheetTwo.mergeCells(3, 0, 7, 0);//
//            sheetTwo.mergeCells(8, 0, 10, 0);//
//            // 设置表头
//            sheetTwo.addCell(new Label(0,0,"机构",wcf));
//            sheetTwo.addCell(new Label(1,0,"主管",wcf));
//            sheetTwo.addCell(new Label(2,0,"CC",wcf));
//            sheetTwo.addCell(new Label(3,0,"客户状态",wcf));
//            sheetTwo.addCell(new Label(8,0,"客户转换率",wcf));
//
//            sheetTwo.addCell(new Label(3,1,"录入客户",wcf));
//            sheetTwo.addCell(new Label(4,1,"目标客户",wcf));
//            sheetTwo.addCell(new Label(5,1,"潜在客户",wcf));
//            sheetTwo.addCell(new Label(6,1,"意向客户",wcf));
//            sheetTwo.addCell(new Label(7,1,"签约客户",wcf));
//            sheetTwo.addCell(new Label(8,1,"目标转潜在",wcf));
//            sheetTwo.addCell(new Label(9,1,"潜在转意向",wcf));
//            sheetTwo.addCell(new Label(10,1,"意向转签约",wcf));
//
//            if(queryList!=null && !queryList.isEmpty()){
//                for(int i=0; i<queryList.size(); i++){
//                    sheetTwo.addCell(new Label(0, i+2,   queryList.get(i).getOrgName()==null?"":queryList.get(i).getOrgName()));
//                    sheetTwo.addCell(new Label(1,i+2,   queryList.get(i).getDirectorName()==null?"":queryList.get(i).getDirectorName()));
//                    sheetTwo.addCell(new Label(2,i+2,   queryList.get(i).getSalesmanName()==null?"":queryList.get(i).getSalesmanName()));
//                    sheetTwo.addCell(new Number(3,i+2,  queryList.get(i).getCaseInputNum()==null?0:queryList.get(i).getCaseInputNum()));
//                    sheetTwo.addCell(new Number(4,i+2,  queryList.get(i).getCaseTargetNum()==null?0:queryList.get(i).getCaseTargetNum()));
//                    sheetTwo.addCell(new Number(5,i+2,  queryList.get(i).getCasePotentialNum()==null?0:queryList.get(i).getCasePotentialNum()));
//                    sheetTwo.addCell(new Number(6,i+2,  queryList.get(i).getCaseIntentionNum()==null?0:queryList.get(i).getCaseIntentionNum()));
//                    sheetTwo.addCell(new Number(7,i+2,  queryList.get(i).getCaseSignNum()==null?0:queryList.get(i).getCaseSignNum()));
//                    sheetTwo.addCell(new Number(8,i+2,  queryList.get(i).getTargetToPotRate()==null?0D:queryList.get(i).getTargetToPotRate()));
//                    sheetTwo.addCell(new Number(9,i+2,  queryList.get(i).getPotToInteRate()==null?0D:queryList.get(i).getPotToInteRate()));
//                    sheetTwo.addCell(new Number(10,i+2,  queryList.get(i).getInteToSignRate()==null?0:queryList.get(i).getInteToSignRate()));
//                }
//            }
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

    /*
    * 公估开票到账月报表list
    */
    @RequestMapping(value = "/caseAssessmentkpdzMonthReportList")
    public ModelAndView caseAssessmentkpdzMonthReportList(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId = req.getParameter("orgId");
        String date = req.getParameter("date");

        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        Map model = new HashMap();

        TypeToken typeToken = new TypeToken<ApiFinalResponse<Map<String,Object>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_ASSESSMENTKPDZ_MONTH_REPORT_LIST, null, req);
        Map map = (Map)apiFinalResponse.getResults();
        List<CaseAssessmentkpdzMonthReportDto> list = (List<CaseAssessmentkpdzMonthReportDto>)map.get("list");
        //“总计数据”
        List<CaseAssessmentkpdzMonthReportDto> titleList = (List<CaseAssessmentkpdzMonthReportDto>)map.get("titleList");
        req.setAttribute("list", list);
        req.setAttribute("titleList", titleList);

        //parentId为1的机构list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_ORG_LIST_BY_ORGPARENTID, null, req);
        List<OrgInfoDto> orgInfoDtos = (List<OrgInfoDto>) apiFinalResponse1.getResults();


        model.put("page", page);
        model.put("orgInfoDtos",orgInfoDtos);
        model.put("orgId",orgId==null?"":orgId);
        SimpleDateFormat simpleDateFormatMonth = new SimpleDateFormat("yyyy-MM");
        if(date == null || "".equals(date)){
            model.put("date",date == null?simpleDateFormatMonth.format(new Date()):date);
        }else{
            model.put("date",date==null?"":date);
        }
        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/reportForm/caseAssessmentkpdzMonthReportList",model);
    }


    @RequestMapping(value = "/caseAssessmentkpdzMonthReportListExport")
    public void caseAssessmentkpdzMonthReportListExport(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId = req.getParameter("orgId");
        String date = req.getParameter("date");
        Map map = new HashMap();
        map.put("orgId",orgId==null?"":orgId);
        SimpleDateFormat simpleDateFormatMonth = new SimpleDateFormat("yyyy-MM");
        if(date == null || "".equals(date)){
            map.put("date",date == null?simpleDateFormatMonth.format(new Date()):date);
        }else{
            map.put("date",date==null?"":date);
        }

        String json = this.callApi(BackendApiMethodEnum.BACKEND_CASE_ASSESSMENTKPDZ_MONTH_REPORT_LIST, map, req);
        Type type = new TypeToken<ApiFinalResponse<List<CaseAssessmentkpdzMonthReportDto>>>() {
        }.getType();
        ApiFinalResponse<List<CaseAssessmentkpdzMonthReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp == null) {
            return;
        }
        // 创建excel
        List<CaseAssessmentkpdzMonthReportDto> queryList = apiRsp.getResults();
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename ="公估开票到账月报表.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("公估开票到账月报表", 0);

            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            sheet.setColumnView(0,25);// 将第一列的宽度设为30
            sheet.mergeCells(0, 0, 0, 1);//
            sheet.mergeCells(1, 0, 9, 0);//
            sheet.mergeCells(10, 0, 18, 0);//
            sheet.mergeCells(19, 0, 27, 0);//
            sheet.mergeCells(28, 0, 36, 0);//
            sheet.mergeCells(37, 0, 37, 1);//

            // 设置表头
            sheet.addCell(new Label(0,0,"机构名称",wcf));
            sheet.addCell(new Label(1,0,"开票金额",wcf));
            sheet.addCell(new Label(10,0,"到账金额",wcf));
            sheet.addCell(new Label(19,0,"作废金额",wcf));
            sheet.addCell(new Label(28,0,"红冲金额",wcf));
            sheet.addCell(new Label(37,0,"时间",wcf));

            // 设置表头
            sheet.addCell(new Label(1,1,"全程通",wcf));
            sheet.addCell(new Label(2,1,"风险调查",wcf));
            sheet.addCell(new Label(3,1,"交警队工作室",wcf));
            sheet.addCell(new Label(4,1,"法院工作室",wcf));
            sheet.addCell(new Label(5,1,"财产险",wcf));
            sheet.addCell(new Label(6,1,"公估评估",wcf));
            sheet.addCell(new Label(7,1,"其他",wcf));
            sheet.addCell(new Label(8,1,"个人业务",wcf));
            sheet.addCell(new Label(9,1,"合计",wcf));
            sheet.addCell(new Label(10,1,"全程通",wcf));
            sheet.addCell(new Label(11,1,"风险调查",wcf));
            sheet.addCell(new Label(12,1,"交警队工作室",wcf));
            sheet.addCell(new Label(13,1,"法院工作室",wcf));
            sheet.addCell(new Label(14,1,"财产险",wcf));
            sheet.addCell(new Label(15,1,"公估评估",wcf));
            sheet.addCell(new Label(16,1,"其他",wcf));
            sheet.addCell(new Label(17,1,"个人业务",wcf));
            sheet.addCell(new Label(18,1,"合计",wcf));
            sheet.addCell(new Label(19,1,"全程通",wcf));
            sheet.addCell(new Label(20,1,"风险调查",wcf));
            sheet.addCell(new Label(21,1,"交警队工作室",wcf));
            sheet.addCell(new Label(22,1,"法院工作室",wcf));
            sheet.addCell(new Label(23,1,"财产险",wcf));
            sheet.addCell(new Label(24,1,"公估评估",wcf));
            sheet.addCell(new Label(25,1,"其他",wcf));
            sheet.addCell(new Label(26,1,"个人业务",wcf));
            sheet.addCell(new Label(27,1,"合计",wcf));
            sheet.addCell(new Label(28,1,"全程通",wcf));
            sheet.addCell(new Label(29,1,"风险调查",wcf));
            sheet.addCell(new Label(30,1,"交警队工作室",wcf));
            sheet.addCell(new Label(31,1,"法院工作室",wcf));
            sheet.addCell(new Label(32,1,"财产险",wcf));
            sheet.addCell(new Label(33,1,"公估评估",wcf));
            sheet.addCell(new Label(34,1,"其他",wcf));
            sheet.addCell(new Label(35,1,"个人业务",wcf));
            sheet.addCell(new Label(36,1,"合计",wcf));

            if(queryList!=null && !queryList.isEmpty()){
                for(int i=0; i<queryList.size(); i++){
                    sheet.addCell(new Label(0, i+2,   queryList.get(i).getOrgName()==null?"":queryList.get(i).getOrgName()));
                    sheet.addCell(new Number(1,i+2,   queryList.get(i).getQctInvoiceMoney()==null?0D:queryList.get(i).getQctInvoiceMoney()));
                    sheet.addCell(new Number(2,i+2,   queryList.get(i).getFxdcInvoiceMoney()==null?0D:queryList.get(i).getFxdcInvoiceMoney()));
                    sheet.addCell(new Number(3,i+2,   queryList.get(i).getJjInvoiceMoney()==null?0D:queryList.get(i).getJjInvoiceMoney()));
                    sheet.addCell(new Number(4,i+2,   queryList.get(i).getFyInvoiceMoney()==null?0D:queryList.get(i).getFyInvoiceMoney()));
                    sheet.addCell(new Number(5,i+2,   queryList.get(i).getCcxInvoiceMoney()==null?0D:queryList.get(i).getCcxInvoiceMoney()));
                    sheet.addCell(new Number(6,i+2,   queryList.get(i).getGgpgInvoiceMoney()==null?0D:queryList.get(i).getGgpgInvoiceMoney()));
                    sheet.addCell(new Number(7,i+2,   queryList.get(i).getOtherInvoiceMoney()==null?0D:queryList.get(i).getOtherInvoiceMoney()));
                    sheet.addCell(new Number(8,i+2,  queryList.get(i).getPbInvoiceMoney()==null?0D:queryList.get(i).getPbInvoiceMoney()));
                    sheet.addCell(new Number(9,i+2,  queryList.get(i).getInvoiceTitle()==null?0D:queryList.get(i).getInvoiceTitle()));

                    sheet.addCell(new Number(10,i+2,   queryList.get(i).getQctReceivedMoney()==null?0D:queryList.get(i).getQctReceivedMoney()));
                    sheet.addCell(new Number(11,i+2,   queryList.get(i).getFxdcReceivedMoney()==null?0D:queryList.get(i).getFxdcReceivedMoney()));
                    sheet.addCell(new Number(12,i+2,  queryList.get(i).getJjReceivedMoney()==null?0D:queryList.get(i).getJjReceivedMoney()));
                    sheet.addCell(new Number(13,i+2,  queryList.get(i).getFyReceivedMoney()==null?0D:queryList.get(i).getFyReceivedMoney()));
                    sheet.addCell(new Number(14,i+2,  queryList.get(i).getCcxReceivedMoney()==null?0D:queryList.get(i).getCcxReceivedMoney()));
                    sheet.addCell(new Number(15,i+2,  queryList.get(i).getGgpgReceivedMoney()==null?0D:queryList.get(i).getGgpgReceivedMoney()));
                    sheet.addCell(new Number(16,i+2,  queryList.get(i).getOtherReceivedMoney()==null?0D:queryList.get(i).getOtherReceivedMoney()));
                    sheet.addCell(new Number(17,i+2,  queryList.get(i).getPbReceivedMoney()==null?0D:queryList.get(i).getPbReceivedMoney()));
                    sheet.addCell(new Number(18,i+2,  queryList.get(i).getReceivedTitle()==null?0D:queryList.get(i).getReceivedTitle()));

                    sheet.addCell(new Number(19,i+2,   queryList.get(i).getQctInvalidMoney()==null?0D:queryList.get(i).getQctInvalidMoney()));
                    sheet.addCell(new Number(20,i+2,   queryList.get(i).getFxdcInvalidMoney()==null?0D:queryList.get(i).getFxdcInvalidMoney()));
                    sheet.addCell(new Number(21,i+2,  queryList.get(i).getJjInvalidMoney()==null?0D:queryList.get(i).getJjInvalidMoney()));
                    sheet.addCell(new Number(22,i+2,  queryList.get(i).getFyInvalidMoney()==null?0D:queryList.get(i).getFyInvalidMoney()));
                    sheet.addCell(new Number(23,i+2,  queryList.get(i).getCcxInvalidMoney()==null?0D:queryList.get(i).getCcxInvalidMoney()));
                    sheet.addCell(new Number(24,i+2,  queryList.get(i).getGgpgInvalidMoney()==null?0D:queryList.get(i).getGgpgInvalidMoney()));
                    sheet.addCell(new Number(25,i+2,  queryList.get(i).getOtherInvalidMoney()==null?0D:queryList.get(i).getOtherInvalidMoney()));
                    sheet.addCell(new Number(26,i+2,  queryList.get(i).getPbInvalidMoney()==null?0D:queryList.get(i).getPbInvalidMoney()));
                    sheet.addCell(new Number(27,i+2,  queryList.get(i).getInvalidTitle()==null?0D:queryList.get(i).getInvalidTitle()));

                    sheet.addCell(new Number(28,i+2,   queryList.get(i).getQctRedrushMoney()==null?0D:queryList.get(i).getQctRedrushMoney()));
                    sheet.addCell(new Number(29,i+2,   queryList.get(i).getFxdcRedrushMoney()==null?0D:queryList.get(i).getFxdcRedrushMoney()));
                    sheet.addCell(new Number(30,i+2,  queryList.get(i).getJjRedrushMoney()==null?0D:queryList.get(i).getJjRedrushMoney()));
                    sheet.addCell(new Number(31,i+2,  queryList.get(i).getFyRedrushMoney()==null?0D:queryList.get(i).getFyRedrushMoney()));
                    sheet.addCell(new Number(32,i+2,  queryList.get(i).getCcxRedrushMoney()==null?0D:queryList.get(i).getCcxRedrushMoney()));
                    sheet.addCell(new Number(33,i+2,  queryList.get(i).getGgpgRedrushMoney()==null?0D:queryList.get(i).getGgpgRedrushMoney()));
                    sheet.addCell(new Number(34,i+2,  queryList.get(i).getOtherRedrushMoney()==null?0D:queryList.get(i).getOtherRedrushMoney()));
                    sheet.addCell(new Number(35,i+2,  queryList.get(i).getPbRedrushMoney()==null?0D:queryList.get(i).getPbRedrushMoney()));
                    sheet.addCell(new Number(36,i+2,  queryList.get(i).getRedrushTitle()==null?0D:queryList.get(i).getRedrushTitle()));

                    if(queryList.get(i).getDate() !=null){
                        sheet.addCell(new Label(37,i+2,format.format(queryList.get(i).getDate())));
                    }else{
                        sheet.addCell(new Label(37, i+2, null));
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


    /*
    * 个人业务开票到账月报表list
    */
    @RequestMapping(value = "/casePerkpdzMonthReportList")
    public ModelAndView casePerkpdzMonthReportList(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId = req.getParameter("orgId");
        String date = req.getParameter("date");

        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }

        TypeToken typeToken = new TypeToken<ApiFinalResponse<Map<String,Object>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_PERKPDZ_MONTH_REPORT_LIST, null, req);
        Map map = (Map)apiFinalResponse.getResults();
        List<CasePerkpdzMonthReportDto> list = (List<CasePerkpdzMonthReportDto>)map.get("list");
        //“总计数据”
        List<CasePerkpdzMonthReportDto> titleList = (List<CasePerkpdzMonthReportDto>)map.get("titleList");
        req.setAttribute("list", list);
        req.setAttribute("titleList", titleList);

        //parentId为1的机构list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_ORG_LIST_BY_ORGPARENTID, null, req);
        List<OrgInfoDto> orgInfoDtos = (List<OrgInfoDto>) apiFinalResponse1.getResults();

        Map model = new HashMap();
        model.put("page", page);
        model.put("orgInfoDtos",orgInfoDtos);
        model.put("orgId",orgId==null?"":orgId);

        SimpleDateFormat simpleDateFormatMonth = new SimpleDateFormat("yyyy-MM");
        if(date == null || "".equals(date)){
            model.put("date",date == null?simpleDateFormatMonth.format(new Date()):date);
        }else{
            model.put("date",date==null?"":date);
        }

        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/reportForm/casePerkpdzMonthReportList",model);
    }


    @RequestMapping(value = "/casePerkpdzMonthReportListExport")
    public void casePerkpdzMonthReportListExport(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId = req.getParameter("orgId");
        String date = req.getParameter("date");
        Map map = new HashMap();
        map.put("orgId",orgId==null?"":orgId);

        SimpleDateFormat simpleDateFormatMonth = new SimpleDateFormat("yyyy-MM");
        if(date == null || "".equals(date)){
            map.put("date",date == null?simpleDateFormatMonth.format(new Date()):date);
        }else{
            map.put("date",date==null?"":date);
        }

        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_CASE_PERKPDZ_MONTH_REPORT_LIST, map, req);
        Type type = new TypeToken<ApiFinalResponse<List<CasePerkpdzMonthReportDto>>>() {
        }.getType();
        ApiFinalResponse<List<CasePerkpdzMonthReportDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp == null) {
            return;
        }
        // 创建excel
        List<CasePerkpdzMonthReportDto> queryList = apiRsp.getResults();
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename ="个人业务开票到账月报表.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("个人业务开票到账月报表", 0);

            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            sheet.setColumnView(0,25);// 将第一列的宽度设为30
            sheet.mergeCells(0, 0, 0, 1);//
            sheet.mergeCells(1, 0, 9, 0);//
            sheet.mergeCells(10, 0, 18, 0);//
            sheet.mergeCells(19, 0, 27, 0);//
            sheet.mergeCells(28, 0, 36, 0);//
            sheet.mergeCells(37, 0, 37, 1);//

            // 设置表头
            sheet.addCell(new Label(0,0,"机构名称",wcf));
            sheet.addCell(new Label(1,0,"开票金额",wcf));
            sheet.addCell(new Label(10,0,"到账金额",wcf));
            sheet.addCell(new Label(19,0,"作废金额",wcf));
            sheet.addCell(new Label(28,0,"红冲金额",wcf));
            sheet.addCell(new Label(37,0,"时间",wcf));

            // 设置表头
            sheet.addCell(new Label(1,1,"全程通",wcf));
            sheet.addCell(new Label(2,1,"风险调查",wcf));
            sheet.addCell(new Label(3,1,"交警队工作室",wcf));
            sheet.addCell(new Label(4,1,"法院工作室",wcf));
            sheet.addCell(new Label(5,1,"财产险",wcf));
            sheet.addCell(new Label(6,1,"公估评估",wcf));
            sheet.addCell(new Label(7,1,"其他",wcf));
            sheet.addCell(new Label(8,1,"个人业务",wcf));
            sheet.addCell(new Label(9,1,"合计",wcf));
            sheet.addCell(new Label(10,1,"全程通",wcf));
            sheet.addCell(new Label(11,1,"风险调查",wcf));
            sheet.addCell(new Label(12,1,"交警队工作室",wcf));
            sheet.addCell(new Label(13,1,"法院工作室",wcf));
            sheet.addCell(new Label(14,1,"财产险",wcf));
            sheet.addCell(new Label(15,1,"公估评估",wcf));
            sheet.addCell(new Label(16,1,"其他",wcf));
            sheet.addCell(new Label(17,1,"个人业务",wcf));
            sheet.addCell(new Label(18,1,"合计",wcf));
            sheet.addCell(new Label(19,1,"全程通",wcf));
            sheet.addCell(new Label(20,1,"风险调查",wcf));
            sheet.addCell(new Label(21,1,"交警队工作室",wcf));
            sheet.addCell(new Label(22,1,"法院工作室",wcf));
            sheet.addCell(new Label(23,1,"财产险",wcf));
            sheet.addCell(new Label(24,1,"公估评估",wcf));
            sheet.addCell(new Label(25,1,"其他",wcf));
            sheet.addCell(new Label(26,1,"个人业务",wcf));
            sheet.addCell(new Label(27,1,"合计",wcf));
            sheet.addCell(new Label(28,1,"全程通",wcf));
            sheet.addCell(new Label(29,1,"风险调查",wcf));
            sheet.addCell(new Label(30,1,"交警队工作室",wcf));
            sheet.addCell(new Label(31,1,"法院工作室",wcf));
            sheet.addCell(new Label(32,1,"财产险",wcf));
            sheet.addCell(new Label(33,1,"公估评估",wcf));
            sheet.addCell(new Label(34,1,"其他",wcf));
            sheet.addCell(new Label(35,1,"个人业务",wcf));
            sheet.addCell(new Label(36,1,"合计",wcf));

            if(queryList!=null && !queryList.isEmpty()){
                for(int i=0; i<queryList.size(); i++){
                    sheet.addCell(new Label(0, i+2,   queryList.get(i).getOrgName()==null?"":queryList.get(i).getOrgName()));
                    sheet.addCell(new Number(1,i+2,   queryList.get(i).getQctInvoiceMoney()==null?0D:queryList.get(i).getQctInvoiceMoney()));
                    sheet.addCell(new Number(2,i+2,   queryList.get(i).getFxdcInvoiceMoney()==null?0D:queryList.get(i).getFxdcInvoiceMoney()));
                    sheet.addCell(new Number(3,i+2,   queryList.get(i).getJjInvoiceMoney()==null?0D:queryList.get(i).getJjInvoiceMoney()));
                    sheet.addCell(new Number(4,i+2,   queryList.get(i).getFyInvoiceMoney()==null?0D:queryList.get(i).getFyInvoiceMoney()));
                    sheet.addCell(new Number(5,i+2,   queryList.get(i).getCcxInvoiceMoney()==null?0D:queryList.get(i).getCcxInvoiceMoney()));
                    sheet.addCell(new Number(6,i+2,   queryList.get(i).getGgpgInvoiceMoney()==null?0D:queryList.get(i).getGgpgInvoiceMoney()));
                    sheet.addCell(new Number(7,i+2,   queryList.get(i).getOtherInvoiceMoney()==null?0D:queryList.get(i).getOtherInvoiceMoney()));
                    sheet.addCell(new Number(8,i+2,  queryList.get(i).getPbInvoiceMoney()==null?0D:queryList.get(i).getPbInvoiceMoney()));
                    sheet.addCell(new Number(9,i+2,  queryList.get(i).getInvoiceTitle()==null?0D:queryList.get(i).getInvoiceTitle()));

                    sheet.addCell(new Number(10,i+2,   queryList.get(i).getQctReceivedMoney()==null?0D:queryList.get(i).getQctReceivedMoney()));
                    sheet.addCell(new Number(11,i+2,   queryList.get(i).getFxdcReceivedMoney()==null?0D:queryList.get(i).getFxdcReceivedMoney()));
                    sheet.addCell(new Number(12,i+2,  queryList.get(i).getJjReceivedMoney()==null?0D:queryList.get(i).getJjReceivedMoney()));
                    sheet.addCell(new Number(13,i+2,  queryList.get(i).getFyReceivedMoney()==null?0D:queryList.get(i).getFyReceivedMoney()));
                    sheet.addCell(new Number(14,i+2,  queryList.get(i).getCcxReceivedMoney()==null?0D:queryList.get(i).getCcxReceivedMoney()));
                    sheet.addCell(new Number(15,i+2,  queryList.get(i).getGgpgReceivedMoney()==null?0D:queryList.get(i).getGgpgReceivedMoney()));
                    sheet.addCell(new Number(16,i+2,  queryList.get(i).getOtherReceivedMoney()==null?0D:queryList.get(i).getOtherReceivedMoney()));
                    sheet.addCell(new Number(17,i+2,  queryList.get(i).getPbReceivedMoney()==null?0D:queryList.get(i).getPbReceivedMoney()));
                    sheet.addCell(new Number(18,i+2,  queryList.get(i).getReceivedTitle()==null?0D:queryList.get(i).getReceivedTitle()));

                    sheet.addCell(new Number(19,i+2,   queryList.get(i).getQctInvalidMoney()==null?0D:queryList.get(i).getQctInvalidMoney()));
                    sheet.addCell(new Number(20,i+2,   queryList.get(i).getFxdcInvalidMoney()==null?0D:queryList.get(i).getFxdcInvalidMoney()));
                    sheet.addCell(new Number(21,i+2,  queryList.get(i).getJjInvalidMoney()==null?0D:queryList.get(i).getJjInvalidMoney()));
                    sheet.addCell(new Number(22,i+2,  queryList.get(i).getFyInvalidMoney()==null?0D:queryList.get(i).getFyInvalidMoney()));
                    sheet.addCell(new Number(23,i+2,  queryList.get(i).getCcxInvalidMoney()==null?0D:queryList.get(i).getCcxInvalidMoney()));
                    sheet.addCell(new Number(24,i+2,  queryList.get(i).getGgpgInvalidMoney()==null?0D:queryList.get(i).getGgpgInvalidMoney()));
                    sheet.addCell(new Number(25,i+2,  queryList.get(i).getOtherInvalidMoney()==null?0D:queryList.get(i).getOtherInvalidMoney()));
                    sheet.addCell(new Number(26,i+2,  queryList.get(i).getPbInvalidMoney()==null?0D:queryList.get(i).getPbInvalidMoney()));
                    sheet.addCell(new Number(27,i+2,  queryList.get(i).getInvalidTitle()==null?0D:queryList.get(i).getInvalidTitle()));

                    sheet.addCell(new Number(28,i+2,   queryList.get(i).getQctRedrushMoney()==null?0D:queryList.get(i).getQctRedrushMoney()));
                    sheet.addCell(new Number(29,i+2,   queryList.get(i).getFxdcRedrushMoney()==null?0D:queryList.get(i).getFxdcRedrushMoney()));
                    sheet.addCell(new Number(30,i+2,  queryList.get(i).getJjRedrushMoney()==null?0D:queryList.get(i).getJjRedrushMoney()));
                    sheet.addCell(new Number(31,i+2,  queryList.get(i).getFyRedrushMoney()==null?0D:queryList.get(i).getFyRedrushMoney()));
                    sheet.addCell(new Number(32,i+2,  queryList.get(i).getCcxRedrushMoney()==null?0D:queryList.get(i).getCcxRedrushMoney()));
                    sheet.addCell(new Number(33,i+2,  queryList.get(i).getGgpgRedrushMoney()==null?0D:queryList.get(i).getGgpgRedrushMoney()));
                    sheet.addCell(new Number(34,i+2,  queryList.get(i).getOtherRedrushMoney()==null?0D:queryList.get(i).getOtherRedrushMoney()));
                    sheet.addCell(new Number(35,i+2,  queryList.get(i).getPbRedrushMoney()==null?0D:queryList.get(i).getPbRedrushMoney()));
                    sheet.addCell(new Number(36,i+2,  queryList.get(i).getRedrushTitle()==null?0D:queryList.get(i).getRedrushTitle()));

                    if(queryList.get(i).getDate() !=null){
                        sheet.addCell(new Label(37,i+2,format.format(queryList.get(i).getDate())));
                    }else{
                        sheet.addCell(new Label(37, i+2, null));
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

    /*
    * 个人业务案件财务台账list
    */
    @RequestMapping(value = "/casePersonalCwInfoList")
    public ModelAndView casePersonalCwInfoList(HttpServletRequest req, HttpServletResponse rsp) {
        String orgId = req.getParameter("orgId");
        String caseNo = req.getParameter("caseNo");
        String salesmanName = req.getParameter("salesmanName");
        String clientName = req.getParameter("clientName");
        String gradationState = req.getParameter("gradationState");

        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CasePersonalCwInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_PERSONAL_CW_INFO_LIST, null, req);

        //parentId为1的机构list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_ORG_LIST_BY_ORGPARENTID, null, req);
        List<OrgInfoDto> orgInfoDtos = (List<OrgInfoDto>) apiFinalResponse1.getResults();

        Map model = new HashMap();
        model.put("page", page);
        model.put("orgInfoDtos",orgInfoDtos);
        model.put("orgId",orgId==null?"":orgId);
        model.put("caseNo",caseNo==null?"":caseNo);
        model.put("salesmanName",salesmanName==null?"":salesmanName);
        model.put("clientName",clientName==null?"":clientName);
        model.put("gradationState",gradationState==null?"":gradationState);

        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/reportForm/casePersonalCwInfoList",model);
    }

    /**
     * 个人业务案件财务台账详情页面
     */
    @RequestMapping(value = "/casePersonalCwInfoView")
    public ModelAndView casePersonalCwInfoView(HttpServletRequest req , HttpServletResponse rsp) {
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CasePersonalCwInfoDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_PERSONAL_CW_INFO_BY_ID, null, req);
        CasePersonalCwInfoDto casePersonalCwInfo = (CasePersonalCwInfoDto) apiFinalResponse.getResults();
        model.put("casePersonalCwInfo", casePersonalCwInfo);
        return new ModelAndView("/reportForm/casePersonalCwInfoView",model);
    }

    @RequestMapping(value = "/casePersonalCwInfoListExport")
    public void casePersonalCwInfoListExport(HttpServletRequest req, HttpServletResponse rsp) {

        String orgId = req.getParameter("orgId");
        String caseNo = req.getParameter("caseNo");
        String salesmanName = req.getParameter("salesmanName");
        String clientName = req.getParameter("clientName");

        Map map = new HashMap();
        map.put("caseNo",caseNo==null?"":caseNo);
        map.put("salesmanName",salesmanName==null?"":salesmanName);
        map.put("orgId",orgId==null?"":orgId);
        map.put("clientName",clientName==null?"":clientName);

        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_CASE_PERSONAL_CW_INFO_LIST, map, req);
        Type type = new TypeToken<ApiFinalResponse<List<CasePersonalCwInfoDto>>>() {
        }.getType();
        ApiFinalResponse<List<CasePersonalCwInfoDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp == null) {
            return;
        }
        // 创建excel
        List<CasePersonalCwInfoDto> queryList = apiRsp.getResults();
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename ="个人业务案件财务台账.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("个人业务案件财务台账", 0);

            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            sheet.mergeCells(0, 0, 12, 0);//
            sheet.mergeCells(13, 0, 27, 0);//
            sheet.mergeCells(28, 0, 37, 0);//
            sheet.mergeCells(38, 0, 41, 0);//

            // 设置表头
            sheet.addCell(new Label(0,0,"案件基本信息",wcf));
            sheet.addCell(new Label(13,0,"收入",wcf));
            sheet.addCell(new Label(28,0,"成本&支出",wcf));
            sheet.addCell(new Label(38,0,"医疗费垫付",wcf));

            // 设置表头
            sheet.addCell(new Label(0,1,"机构名称"));
            sheet.addCell(new Label(1,1,"案件录入时间"));
            sheet.addCell(new Label(2,1,"案件签约时间"));
            sheet.addCell(new Label(3,1,"业务员姓名"));
            sheet.addCell(new Label(4,1,"案件编号"));
            sheet.addCell(new Label(5,1,"案件状态"));
            sheet.addCell(new Label(6,1,"案件阶段"));
            sheet.addCell(new Label(7,1,"委托人"));
            sheet.addCell(new Label(8,1,"合同摘要"));
            sheet.addCell(new Label(9,1,"索赔员姓名"));
            sheet.addCell(new Label(10,1,"转办索赔日期"));
            sheet.addCell(new Label(11,1,"是否结案"));
            sheet.addCell(new Label(12,1,"结案日期"));

            sheet.addCell(new Label(13,1,"审核服务费金额"));
            sheet.addCell(new Label(14,1,"应收基本费"));
            sheet.addCell(new Label(15,1,"应收尾款"));
            sheet.addCell(new Label(16,1,"应收利息"));
            sheet.addCell(new Label(17,1,"应收其他"));
            sheet.addCell(new Label(18,1,"收入小计"));
            sheet.addCell(new Label(19,1,"开票金额"));
            sheet.addCell(new Label(20,1,"开票日期"));
            sheet.addCell(new Label(21,1,"发票号码"));
            sheet.addCell(new Label(22,1,"发票小计"));
            sheet.addCell(new Label(23,1,"基本费回款金额"));
            sheet.addCell(new Label(24,1,"基本费回款日期"));
            sheet.addCell(new Label(25,1,"服务费回款金额"));
            sheet.addCell(new Label(26,1,"服务费回款日期"));
            sheet.addCell(new Label(27,1,"回款小计"));

            sheet.addCell(new Label(28,1,"诉讼费金额"));
            sheet.addCell(new Label(29,1,"诉讼费支付日期"));
            sheet.addCell(new Label(30,1,"佣金金额"));
            sheet.addCell(new Label(31,1,"佣金支付日期"));
            sheet.addCell(new Label(32,1,"维护费金额"));
            sheet.addCell(new Label(33,1,"维护费支付日期"));
            sheet.addCell(new Label(34,1,"利息支出"));
            sheet.addCell(new Label(35,1,"其他支出"));
            sheet.addCell(new Label(36,1,"其他支出日期"));
            sheet.addCell(new Label(37,1,"合计"));

            sheet.addCell(new Label(38,1,"垫付金额"));
            sheet.addCell(new Label(39,1,"垫付日期"));
            sheet.addCell(new Label(40,1,"回款金额"));
            sheet.addCell(new Label(41,1,"回款日期"));

            if(queryList!=null && !queryList.isEmpty()){
                for(int i=0; i<queryList.size(); i++){
                    sheet.addCell(new Label(0, i+2, queryList.get(i).getOrgName()==null?"":queryList.get(i).getOrgName()));

                    if(queryList.get(i).getCaseEntryTime() !=null){
                        sheet.addCell(new Label(1,i+2,format.format(queryList.get(i).getCaseEntryTime())));
                    }else{
                        sheet.addCell(new Label(1, i+2, null));
                    }

                    if(queryList.get(i).getSignTime() !=null){
                        sheet.addCell(new Label(2,i+2,format.format(queryList.get(i).getSignTime())));
                    }else{
                        sheet.addCell(new Label(2, i+2, null));
                    }

                    sheet.addCell(new Label(3,i+2, queryList.get(i).getSalesmanName()==null?"":queryList.get(i).getSalesmanName()));
                    sheet.addCell(new Label(4,i+2, queryList.get(i).getCaseNo()==null?"":queryList.get(i).getCaseNo()));
                    sheet.addCell(new Label(5,i+2, queryList.get(i).getCaseState()==null?"":queryList.get(i).getCaseState()));
                    sheet.addCell(new Label(6,i+2, queryList.get(i).getGradationState()==null?"":queryList.get(i).getGradationState()));
                    sheet.addCell(new Label(7,i+2, queryList.get(i).getClientName()==null?"":queryList.get(i).getClientName()));
                    sheet.addCell(new Label(8,i+2, queryList.get(i).getContractSummary()==null?"":queryList.get(i).getContractSummary()));
                    sheet.addCell(new Label(9,i+2, queryList.get(i).getClientName()==null?"":queryList.get(i).getClientName()));

                    if(queryList.get(i).getClaimTime() !=null){
                        sheet.addCell(new Label(10,i+2,format.format(queryList.get(i).getClaimTime())));
                    }else{
                        sheet.addCell(new Label(10, i+2, null));
                    }

                    if(queryList.get(i).getIsClosed()==null || queryList.get(i).getIsClosed()==0){
                        sheet.addCell(new Label(11, i+2, "否"));
                    }else{
                        sheet.addCell(new Label(11, i+2, "是"));
                    }

                    if(queryList.get(i).getClosedTime() !=null){
                        sheet.addCell(new Label(12,i+2,format.format(queryList.get(i).getClosedTime())));
                    }else{
                        sheet.addCell(new Label(12, i+2, null));
                    }

                    //收入
                    sheet.addCell(new Number(13,i+2, queryList.get(i).getCheckServiceMoney()==null?0D:queryList.get(i).getCheckServiceMoney()));
                    sheet.addCell(new Number(14,i+2, queryList.get(i).getReBasicMoney()==null?0D:queryList.get(i).getReBasicMoney()));
                    sheet.addCell(new Number(15,i+2, queryList.get(i).getReTailMoney()==null?0D:queryList.get(i).getReTailMoney()));
                    sheet.addCell(new Number(16,i+2, queryList.get(i).getReInterestMoney()==null?0D:queryList.get(i).getReInterestMoney()));
                    sheet.addCell(new Number(17,i+2, queryList.get(i).getReOtherMoney()==null?0D:queryList.get(i).getReOtherMoney()));
                    sheet.addCell(new Number(18,i+2, queryList.get(i).getReTotalMoney()==null?0D:queryList.get(i).getReTotalMoney()));
                    sheet.addCell(new Number(19,i+2, queryList.get(i).getBillingMoney()==null?0D:queryList.get(i).getBillingMoney()));
                    if(queryList.get(i).getBillingTime() !=null){
                        sheet.addCell(new Label(20,i+2,format.format(queryList.get(i).getBillingTime())));
                    }else{
                        sheet.addCell(new Label(20, i+2, null));
                    }
                    sheet.addCell(new Label(21,i+2,  queryList.get(i).getBillingCode()==null?"":queryList.get(i).getBillingCode()));
                    sheet.addCell(new Number(22,i+2, queryList.get(i).getBillingTotalMoney()==null?0D:queryList.get(i).getBillingTotalMoney()));

                    sheet.addCell(new Number(23,i+2, queryList.get(i).getBasicMoney()==null?0D:queryList.get(i).getBasicMoney()));
                    if(queryList.get(i).getBasicMoneyTime() !=null){
                        sheet.addCell(new Label(24,i+2,format.format(queryList.get(i).getBasicMoneyTime())));
                    }else{
                        sheet.addCell(new Label(24, i+2, null));
                    }
                    sheet.addCell(new Number(25,i+2, queryList.get(i).getReturnMoney()==null?0D:queryList.get(i).getReturnMoney()));
                    if(queryList.get(i).getReturnMoneyTime() !=null){
                        sheet.addCell(new Label(26,i+2,format.format(queryList.get(i).getReturnMoneyTime())));
                    }else{
                        sheet.addCell(new Label(26, i+2, null));
                    }
                    sheet.addCell(new Number(27,i+2, queryList.get(i).getReturnTotalMoney()==null?0D:queryList.get(i).getReturnTotalMoney()));

                    //成本&支出
                    sheet.addCell(new Number(28,i+2, queryList.get(i).getLitigationMoney()==null?0D:queryList.get(i).getLitigationMoney()));
                    if(queryList.get(i).getLitigationTime() !=null){
                        sheet.addCell(new Label(29,i+2,format.format(queryList.get(i).getLitigationTime())));
                    }else{
                        sheet.addCell(new Label(29, i+2, null));
                    }
                    sheet.addCell(new Number(30,i+2, queryList.get(i).getCommissionMoney()==null?0D:queryList.get(i).getCommissionMoney()));
                    if(queryList.get(i).getCommissionTime() !=null){
                        sheet.addCell(new Label(31,i+2,format.format(queryList.get(i).getCommissionTime())));
                    }else{
                        sheet.addCell(new Label(31, i+2, null));
                    }
                    sheet.addCell(new Number(32,i+2, queryList.get(i).getMaintainMoney()==null?0D:queryList.get(i).getMaintainMoney()));
                    if(queryList.get(i).getMaintainTime() !=null){
                        sheet.addCell(new Label(33,i+2,format.format(queryList.get(i).getMaintainTime())));
                    }else{
                        sheet.addCell(new Label(33, i+2, null));
                    }
                    sheet.addCell(new Number(34,i+2, queryList.get(i).getRealInterestMoney()==null?0D:queryList.get(i).getRealInterestMoney()));
                    sheet.addCell(new Number(35,i+2, queryList.get(i).getOtherCostMoney()==null?0D:queryList.get(i).getOtherCostMoney()));
                    if(queryList.get(i).getOtherCostTime() !=null){
                        sheet.addCell(new Label(36,i+2,format.format(queryList.get(i).getOtherCostTime())));
                    }else{
                        sheet.addCell(new Label(36, i+2, null));
                    }
                    sheet.addCell(new Number(37,i+2, queryList.get(i).getCostTotle()==null?0D:queryList.get(i).getCostTotle()));

                    //医疗费垫付
                    sheet.addCell(new Number(38,i+2, queryList.get(i).getRealInterestMoney()==null?0D:queryList.get(i).getRealInterestMoney()));
                    if(queryList.get(i).getLoanTime() !=null){
                        sheet.addCell(new Label(39,i+2,format.format(queryList.get(i).getLoanTime())));
                    }else{
                        sheet.addCell(new Label(39, i+2, null));
                    }
                    sheet.addCell(new Number(40,i+2, queryList.get(i).getReLoanMoney()==null?0D:queryList.get(i).getReLoanMoney()));
                    if(queryList.get(i).getReLoanTime() !=null){
                        sheet.addCell(new Label(41,i+2,format.format(queryList.get(i).getReLoanTime())));
                    }else{
                        sheet.addCell(new Label(41, i+2, null));
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
}
