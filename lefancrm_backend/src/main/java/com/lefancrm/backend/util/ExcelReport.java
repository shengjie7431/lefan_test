package com.lefancrm.backend.util;

import com.lefancrm.backend.annotation.Excel;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.dto.claims.*;
import com.lefancrm.backend.dto.financial.FinancialReApplyDto;
import com.lefancrm.backend.dto.staff.StaffPayPersonnelSlipDto;
import com.lefancrm.backend.dto.staff.StaffPersonnelInfoDto;
import com.lefancrm.backend.dto.survey.SurveyCaseArchivesDto;
import jxl.Sheet;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ExcelReport {
    static private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void report(List<SurveyRiskCaseInfoDto> data, Map<String,Object> paramMap, HttpServletResponse response){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("平台复审保司导出数据-" + simpleDateFormat.format(new Date()));//建立sheet对象
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        //设置头
        HSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("案件编号");
        row1.createCell(1).setCellValue("保险公司");
        row1.createCell(2).setCellValue("被调查人");
        row1.createCell(3).setCellValue("调查机构");
        row1.createCell(4).setCellValue("复审人员");
        row1.createCell(5).setCellValue("委托时间");
        row1.createCell(6).setCellValue("调查完成时间");
        row1.createCell(7).setCellValue("案件截止时间");
        row1.createCell(8).setCellValue("审核描述");
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

        for (int i = 0; i < data.size(); i++) {
            SurveyRiskCaseInfoDto item = data.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(item.getSurveyRiskCase().getSurveyCaseNo());
            row.createCell(1).setCellValue(item.getEntrustOrgName());
            row.createCell(2).setCellValue(item.getSurveyRiskCase().getSurveyPerson());
            row.createCell(3).setCellValue(item.getSurveyOrgNames());
            row.createCell(4).setCellValue(item.getBelongUserName());
            row.createCell(5).setCellValue(simpleDateFormat.format(item.getSurveyRiskCase().getEntrustTime()));
            row.createCell(6).setCellValue(item.getLefanReportDate() == null ? "" : simpleDateFormat.format(item.getLefanReportDate()));
            row.createCell(7).setCellValue(item.getEndTime() == null ? "" : simpleDateFormat.format(item.getEndTime()));
            row.createCell(8).setCellValue(item.getLefanReportRemark());
        }

        //输出Excel文件
        try (OutputStream output = response.getOutputStream()) {
            //设置响应头
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(sheet.getSheetName(), "UTF-8") + ".xls");
            response.setContentType("application/msexcel");
            wb.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void reportAll(List<SurveyRiskCaseInfoDto> data, Map<String,Object> paramMap, HttpServletResponse response){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("导出数据-" + simpleDateFormat.format(new Date()));//建立sheet对象
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        //设置头
        HSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("案件编号");
        row1.createCell(1).setCellValue("调查编号");
        row1.createCell(2).setCellValue("被调查人");
        row1.createCell(3).setCellValue("委托时间");
        row1.createCell(4).setCellValue("保险公司");
        row1.createCell(5).setCellValue("委托方确认结算价格");
        row1.createCell(6).setCellValue("调查方确认结算价格");
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

        for (int i = 0; i < data.size(); i++) {
            SurveyRiskCaseInfoDto item = data.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(item.getSurveyRiskCase().getSurveyCaseNo());
            row.createCell(1).setCellValue(item.getSurveyRiskCase().getSurveyNo());
            row.createCell(2).setCellValue(item.getSurveyRiskCase().getSurveyPerson());
            if (item.getSurveyRiskCase().getEntrustTime() != null){
                row.createCell(3).setCellValue(simpleDateFormat.format(item.getSurveyRiskCase().getEntrustTime()));
            }else{
                row.createCell(3).setCellValue("");
            }
            row.createCell(4).setCellValue(item.getEntrustOrgName());
            row.createCell(5).setCellValue(item.getEntrustOkPrice1() == null ? "0" : item.getEntrustOkPrice1().toString());
            row.createCell(6).setCellValue(item.getSurveyOKMoney() == null ? "0" : item.getSurveyOKMoney().toString());
        }

        //输出Excel文件
        try (OutputStream output = response.getOutputStream()) {
            //设置响应头
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(sheet.getSheetName(), "UTF-8") + ".xls");
            response.setContentType("application/msexcel");
            wb.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //年度汇总表导出
    public static void reportOrgInfo(List<OrgInfoDto> data ,Map<String,Object> paramMap, HttpServletResponse response,String excelPath, String excelName){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("导出数据-" + simpleDateFormat.format(new Date()));//建立sheet对象
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        //设置头
        HSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("机构/产品");
        for (int i = 0; i < data.size(); i++) {
            row1.createCell(i + 1).setCellValue(data.get(i).getOrgName());
        }
        row1.createCell(data.size() + 1).setCellValue("合计");
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

        //根据月份排序
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM");
        Map<String,List<CommonEnumDto>> hashMap =  new TreeMap<>(new Comparator<String>() {//自定义倒叙排序map
            @Override
            public int compare(String o1, String o2) {
                try {
                    Date d1 = simpleDateFormat1.parse(o1);
                    Date d2 = simpleDateFormat1.parse(o2);
                    return d1.compareTo(d2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        for (int i = 0; i < data.size(); i++) {
            int finalI = i;
            List<CommonEnumDto> enumMoneyList = data.get(i).getEnumMoneyList();
            Map<String, List<CommonEnumDto>> collect = enumMoneyList.stream().collect(Collectors.groupingBy(CommonEnumDto::getDateTime));
            hashMap.putAll(collect);
            final AtomicInteger finalZ =   new AtomicInteger(0);
            hashMap.forEach((k,v)->{
                //此处循环开票数据
                for (int j = 0; j < v.size() ; j++) {
                    CommonEnumDto commonEnumDto = v.get(j);
                    commonEnumDto.getImgMoney();
                    //因为是循环开票，所以“到账总金额”、“收账款余额”不予落地
                    if("-1".equals(commonEnumDto.getEnumCode())  || "99999".equals(commonEnumDto.getEnumCode())  ){
                        continue;
                    }
                    HSSFRow row = null;
                    if (finalI > 0){
                        row = sheet.getRow(finalZ.get() + 1);
                    }else{
                        row = sheet.createRow(finalZ.get() + 1);
                    }
                    if(commonEnumDto!=null){
                        row.createCell(0).setCellValue(commonEnumDto.getEnumName());
                        row.createCell(finalI + 1).setCellValue(commonEnumDto.getImgMoney());
                    }else{
                        row.createCell(0).setCellValue("");
                        row.createCell(finalI + 1).setCellValue("");
                    }
                    finalZ.incrementAndGet();
                }
                //此处循环到账数据
                for (int j = 0; j < v.size() ; j++) {
                    CommonEnumDto commonEnumDto = v.get(j);
                    //因为是循环开票，所以“开票总金额”不予落地
                    if("-2".equals(commonEnumDto.getEnumCode())){
                        continue;
                    }
                    HSSFRow row = null;
                    if (finalI > 0){
                        row = sheet.getRow(finalZ.get() + 1);
                    }else{
                        row = sheet.createRow(finalZ.get() + 1);
                    }
                    if(commonEnumDto != null){
                        row.createCell(0).setCellValue(commonEnumDto.getEnumName());
                        row.createCell(finalI + 1).setCellValue(commonEnumDto.getAccMoney());
                    }else{
                        row.createCell(0).setCellValue("");
                        row.createCell(finalI + 1).setCellValue("");
                    }
                    finalZ.incrementAndGet();
                }

            });
        }

        HSSFSheet sh = wb.getSheetAt(0);
        for (int j = 1; j < sh.getPhysicalNumberOfRows(); j++) {
            Double totalMoney = 0D;
            HSSFRow row = sh.getRow(j);
            if(row != null){
                for (int k = 1; k < sh.getRow(j).getPhysicalNumberOfCells(); k++) {
                    Cell cell = row.getCell(k);
                    if(cell !=null){
                        totalMoney += cell.getNumericCellValue();
                    }
                }
            }

            row.createCell(row.getPhysicalNumberOfCells()).setCellValue(totalMoney);
        }

        //总表直接导出excel
        if (null == excelPath) {
            //输出Excel文件
            try (OutputStream output = response.getOutputStream()) {
                //设置响应头
                response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(excelName, "UTF-8") + ".xls");
                response.setContentType("application/msexcel");
                wb.write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            //分表需要导出压缩包
            try (OutputStream output = new FileOutputStream(excelPath + File.separator + excelName+".xls")) {
                wb.write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //员工管理导出
    public static void reportPersonnelInfo(List<StaffPersonnelInfoDto> data, Map<String,Object> paramMap, HttpServletResponse response){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("员工管理");//建立sheet对象
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        //设置头
        int index = -1;
        HSSFRow row1 = sheet.createRow(0);
        row1.createCell(++index).setCellValue("员工UserID");
        row1.createCell(++index).setCellValue("姓名");
        row1.createCell(++index).setCellValue("工号");
        row1.createCell(++index).setCellValue("社保缴纳公司");
        row1.createCell(++index).setCellValue("预算归属公司");
        row1.createCell(++index).setCellValue("机构/部门");
        row1.createCell(++index).setCellValue("科室");
        row1.createCell(++index).setCellValue("小组");
        row1.createCell(++index).setCellValue("职务称谓");
        row1.createCell(++index).setCellValue("岗位");
        row1.createCell(++index).setCellValue("岗位职级");

        row1.createCell(++index).setCellValue("调查员等级");


        row1.createCell(++index).setCellValue("手机号");
        row1.createCell(++index).setCellValue("分机号");
        row1.createCell(++index).setCellValue("办公地点");
        row1.createCell(++index).setCellValue("备注");
        row1.createCell(++index).setCellValue("入职时间");

        row1.createCell(++index).setCellValue("转正时间");

        row1.createCell(++index).setCellValue("离职时间");
        row1.createCell(++index).setCellValue("司龄（系统计算）");
        row1.createCell(++index).setCellValue("电子邮箱");
        row1.createCell(++index).setCellValue("员工类型");
        row1.createCell(++index).setCellValue("员工状态");
        row1.createCell(++index).setCellValue("试用期");
        row1.createCell(++index).setCellValue("转正日期");
        row1.createCell(++index).setCellValue("岗位职级");
        row1.createCell(++index).setCellValue("学历");
        row1.createCell(++index).setCellValue("毕业院校");
        row1.createCell(++index).setCellValue("毕业时间");
        row1.createCell(++index).setCellValue("所学专业");
        row1.createCell(++index).setCellValue("银行卡号");
        row1.createCell(++index).setCellValue("开户行");
        row1.createCell(++index).setCellValue("合同公司");
        row1.createCell(++index).setCellValue("合同类型");
        row1.createCell(++index).setCellValue("首次合同起始日");
        row1.createCell(++index).setCellValue("首次合同到期日");
        row1.createCell(++index).setCellValue("现合同起始日");
        row1.createCell(++index).setCellValue("现合同到期日");
        row1.createCell(++index).setCellValue("合同期限");
        row1.createCell(++index).setCellValue("续签次数");
        row1.createCell(++index).setCellValue("紧急联系人姓名");
        row1.createCell(++index).setCellValue("联系人关系");
        row1.createCell(++index).setCellValue("联系人电话");
        row1.createCell(++index).setCellValue("姓名(家人)");
        row1.createCell(++index).setCellValue("关系(家人)");
        row1.createCell(++index).setCellValue("性别(家人)");
        row1.createCell(++index).setCellValue("生日(家人)");
        row1.createCell(++index).setCellValue("电话(家人)");
        row1.createCell(++index).setCellValue("身份证姓名");
        row1.createCell(++index).setCellValue("证件号码");
        row1.createCell(++index).setCellValue("出生日期");
        row1.createCell(++index).setCellValue("年龄（系统计算）");
        row1.createCell(++index).setCellValue("性别");
        row1.createCell(++index).setCellValue("民族");
        row1.createCell(++index).setCellValue("身份证地址");
        row1.createCell(++index).setCellValue("证件有效期");
        row1.createCell(++index).setCellValue("婚姻状况");
        row1.createCell(++index).setCellValue("首次参加工作时间");
        row1.createCell(++index).setCellValue("工龄（系统计算）");
        row1.createCell(++index).setCellValue("户籍类型");
        row1.createCell(++index).setCellValue("住址");
        row1.createCell(++index).setCellValue("政治面貌");
        row1.createCell(++index).setCellValue("个人社保账号");
        row1.createCell(++index).setCellValue("个人公积金账号");
        row1.createCell(++index).setCellValue("社保公积金缴纳地");
        row1.createCell(++index).setCellValue("基本工资");
        row1.createCell(++index).setCellValue("固定绩效");
        row1.createCell(++index).setCellValue("岗位津贴");
        row1.createCell(++index).setCellValue("保险考核绩效（按量）");
        row1.createCell(++index).setCellValue("互助考核绩效（按量）");
        row1.createCell(++index).setCellValue("考核绩效");
        row1.createCell(++index).setCellValue("驻外补贴");
        row1.createCell(++index).setCellValue("养老保险基数");
        row1.createCell(++index).setCellValue("养老保险公司比例");
        row1.createCell(++index).setCellValue("养老保险个人比例");
        row1.createCell(++index).setCellValue("医疗保险基数");
        row1.createCell(++index).setCellValue("医疗保险公司比例");
        row1.createCell(++index).setCellValue("医疗保险个人比例");
        row1.createCell(++index).setCellValue("失业保险基数");
        row1.createCell(++index).setCellValue("失业保险公司比例");
        row1.createCell(++index).setCellValue("失业保险个人比例");
        row1.createCell(++index).setCellValue("工伤保险基数");
        row1.createCell(++index).setCellValue("工伤保险公司比例");
        row1.createCell(++index).setCellValue("生育保险基数");
        row1.createCell(++index).setCellValue("生育保险公司比例");
        row1.createCell(++index).setCellValue("公积金基数");
        row1.createCell(++index).setCellValue("公积金公司比例");
        row1.createCell(++index).setCellValue("公积金个人比例");
        row1.createCell(++index).setCellValue("离职成本");

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

        for (int i = 0; i < data.size(); i++) {
            StaffPersonnelInfoDto info = data.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(1).setCellValue(info.getRealName() == null ? "" : info.getRealName());
            row.createCell(2).setCellValue(info.getJobNo() == null ? "" : info.getJobNo());
            row.createCell(3).setCellValue(info.getSocialSecurityCompany() == null ? "" : info.getSocialSecurityCompany());
            row.createCell(4).setCellValue(info.getBudgetCompanyName() == null ? "" : info.getBudgetCompanyName());
            row.createCell(5).setCellValue(info.getOrgan() == null ? "" : info.getOrgan());
            row.createCell(6).setCellValue(info.getDepartment() == null ? "" : info.getDepartment());
            row.createCell(7).setCellValue(info.getTeam() == null ? "" : info.getTeam());
            row.createCell(8).setCellValue(info.getPostAppellationName() == null ? "" : info.getPostAppellationName());
            row.createCell(9).setCellValue(info.getJobPost() == null ? "" : info.getJobPost());
            row.createCell(10).setCellValue(info.getPostRankName() == null ? "" : info.getPostRankName());
            row.createCell(11).setCellValue(info.getSurveyLevelName() == null ? "" : info.getSurveyLevelName());
            row.createCell(12).setCellValue(info.getUserTel() == null ? "" : info.getUserTel());

            row.createCell(13).setCellValue(info.getExtTel() == null ? "" : info.getExtTel());
            row.createCell(14).setCellValue(info.getOfficePlace() == null ? "" : info.getOfficePlace());
            row.createCell(15).setCellValue(info.getRemark() == null ? "" : info.getRemark());
            if (info.getEntryTime() != null) {
                row.createCell(16).setCellValue(simpleDateFormat.format(info.getEntryTime()));
            } else {
                row.createCell(16).setCellValue("");
            }
            if (info.getRegularTime() != null) {
                row.createCell(17).setCellValue(simpleDateFormat.format(info.getRegularTime()));
            } else {
                row.createCell(17).setCellValue("");
            }
            if (info.getQuitTime() != null) {
                row.createCell(18).setCellValue(simpleDateFormat.format(info.getQuitTime()));
            } else {
                row.createCell(18).setCellValue("");
            }
            if (info.getRelation() != null) {
                int relation = info.getRelation();
                String relationName = "";
                switch (relation){
                    case 1 : relationName="全职"; break;
                    case 2 : relationName="兼职"; break;
                    case 3 : relationName="退休返聘"; break;
                    case 4 : relationName="实习生"; break;
                    case 5 : relationName="合伙"; break;
                    case 6 : relationName="合伙+兼职"; break;
                    case 7 : relationName="合伙(发固定绩效)"; break;
                }
                row.createCell(21).setCellValue(relationName);
            }

            if (info.getStaffState() != null) {
                int staffState = info.getStaffState();
                String staffStateName = "";
                switch (staffState){
                    case 1 : staffStateName="试用期员工"; break;
                    case 2 : staffStateName="调整人员"; break;
                    case 3 : staffStateName="离职待结算"; break;
                    case 4 : staffStateName="转正人员"; break;
                    case 5 : staffStateName="在职"; break;
                    case 6 : staffStateName="已离职"; break;
                }
                row.createCell(22).setCellValue(staffStateName);
            }

            row.createCell(23).setCellValue(info.getTrialTime() == null ? "" : info.getTrialTime());
            row.createCell(25).setCellValue(info.getJobLevel() == null ? "" : info.getJobLevel());
            row.createCell(26).setCellValue(info.getEducation() == null ? "" : info.getEducation());
            row.createCell(27).setCellValue(info.getGraduationSchool() == null ? "" : info.getGraduationSchool());
            if (info.getGraduationTime() != null) {
                row.createCell(28).setCellValue(simpleDateFormat.format(info.getGraduationTime()));
            } else {
                row.createCell(28).setCellValue("");
            }

            row.createCell(29).setCellValue(info.getMajor() == null ? "" : info.getMajor());
            row.createCell(30).setCellValue(info.getBankNo() == null ? "" : info.getBankNo());
            row.createCell(31).setCellValue(info.getBankName() == null ? "" : info.getBankName());
            row.createCell(32).setCellValue(info.getContractCompany() == null ? "" : info.getContractCompany());
            row.createCell(33).setCellValue(info.getContractType() == null ? "" : info.getContractType());
            if (info.getFirstContractBeginTime() != null) {
                row.createCell(34).setCellValue(simpleDateFormat.format(info.getFirstContractBeginTime()));
            } else {
                row.createCell(34).setCellValue("");
            }
            if (info.getFirstContractEndTime() != null) {
                row.createCell(35).setCellValue(simpleDateFormat.format(info.getFirstContractEndTime()));
            } else {
                row.createCell(35).setCellValue("");
            }
            if (info.getNowContractBeginTime() != null) {
                row.createCell(36).setCellValue(simpleDateFormat.format(info.getNowContractBeginTime()));
            } else {
                row.createCell(36).setCellValue("");
            }
            if (info.getNowContractEndTime() != null) {
                row.createCell(37).setCellValue(simpleDateFormat.format(info.getNowContractEndTime()));
            } else {
                row.createCell(37).setCellValue("");
            }
            row.createCell(38).setCellValue(info.getContractTerm() == null ? "" : info.getContractTerm());
            row.createCell(39).setCellValue(info.getRenewNum() == null ? "" : info.getRenewNum());
            row.createCell(40).setCellValue(info.getEmergencyContactName() == null ? "" : info.getEmergencyContactName());
            row.createCell(41).setCellValue(info.getEmergencyContactRelation() == null ? "" : info.getEmergencyContactRelation());
            row.createCell(42).setCellValue(info.getEmergencyContactTel() == null ? "" : info.getEmergencyContactTel());
            row.createCell(43).setCellValue(info.getFamilyName() == null ? "" : info.getFamilyName());
            row.createCell(44).setCellValue(info.getFamilyRelation() == null ? "" : info.getFamilyRelation());
            row.createCell(45).setCellValue(info.getFamilySex() == null ? "" : info.getFamilySex());
            if (info.getFamilyBirthday() != null) {
                row.createCell(46).setCellValue(simpleDateFormat.format(info.getFamilyBirthday()));
            } else {
                row.createCell(46).setCellValue("");
            }
            row.createCell(47).setCellValue(info.getFamilyTel() == null ? "" : info.getFamilyTel());
            row.createCell(48).setCellValue(info.getFamilyIdcardName() == null ? "" : info.getFamilyIdcardName());

            row.createCell(49).setCellValue(info.getIdCard() == null ? "" : info.getIdCard());
            row.createCell(64).setCellValue(info.getPayAddress() == null ? "" : info.getPayAddress());
            row.createCell(65).setCellValue(info.getBasePay() == null ? 0D : info.getBasePay());
            row.createCell(66).setCellValue(info.getFixedPerfPay() == null ? 0D : info.getFixedPerfPay());
            row.createCell(67).setCellValue(info.getManagePerfPay() == null ? 0D : info.getManagePerfPay());
            row.createCell(68).setCellValue(info.getManagePerfPaySize() == null ? 0D : info.getManagePerfPaySize());
            row.createCell(69).setCellValue(info.getManagePerfPaySizeHz() == null ? 0D : info.getManagePerfPaySizeHz());
            row.createCell(70).setCellValue(info.getAssesPerfPay() == null ? 0D : info.getAssesPerfPay());
            row.createCell(71).setCellValue(info.getTravelAllowancePay() == null ? 0D : info.getTravelAllowancePay());
            row.createCell(72).setCellValue(info.getPensionBase() == null ? 0D : info.getPensionBase());
            row.createCell(73).setCellValue(info.getPensionCompanyRate() == null ? 0D : info.getPensionCompanyRate());
            row.createCell(74).setCellValue(info.getPensionPersonalRate() == null ? 0D : info.getPensionPersonalRate());
            row.createCell(75).setCellValue(info.getMedicalBase() == null ? 0D : info.getMedicalBase());
            row.createCell(76).setCellValue(info.getMedicalCompanyRate() == null ? 0D : info.getMedicalCompanyRate());
            row.createCell(77).setCellValue(info.getMedicalPersonalRate() == null ? 0D : info.getMedicalPersonalRate());
            row.createCell(78).setCellValue(info.getUpmBase() == null ? 0D : info.getUpmBase());
            row.createCell(79).setCellValue(info.getUpmCompanyRate() == null ? 0D : info.getUpmCompanyRate());
            row.createCell(80).setCellValue(info.getUpmPersonalRate() == null ? 0D : info.getUpmPersonalRate());
            row.createCell(81).setCellValue(info.getIsaBase() == null ? 0D : info.getIsaBase());
            row.createCell(82).setCellValue(info.getIsaCompanyRate() == null ? 0D : info.getIsaCompanyRate());
            row.createCell(83).setCellValue(info.getBirthBase() == null ? 0D : info.getBirthBase());
            row.createCell(84).setCellValue(info.getBirthCompanyRate() == null ? 0D : info.getBirthCompanyRate());
            row.createCell(85).setCellValue(info.getFundPay() == null ? 0D : info.getFundPay());
            row.createCell(86).setCellValue(info.getFundPayCompanyRate() == null ? 0D : info.getFundPayCompanyRate());
            row.createCell(87).setCellValue(info.getFundPayPersonalRate() == null ? 0D : info.getFundPayPersonalRate());
            row.createCell(88).setCellValue(info.getQuitCost() == null ? 0D : info.getQuitCost());
        }

        //输出Excel文件
        try (OutputStream output = response.getOutputStream()) {
            //设置响应头
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(sheet.getSheetName(), "UTF-8") + ".xls");
            response.setContentType("application/msexcel");
            wb.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //工资条 -- 考勤数据模板导出
    public static void reportDdDataModel(List<StaffPayPersonnelSlipDto> infos, Map<String,Object> paramMap, HttpServletResponse response){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("考勤与其他数据模板");//建立sheet对象
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        //设置头
        HSSFRow row1 = sheet.createRow(0);
        int index = -1;
        row1.createCell(++index).setCellValue("姓名");
        row1.createCell(++index).setCellValue("工号");
        row1.createCell(++index).setCellValue("迟到次数");
        row1.createCell(++index).setCellValue("早退次数");
        row1.createCell(++index).setCellValue("补贴");
        row1.createCell(++index).setCellValue("事假");
        row1.createCell(++index).setCellValue("病假");
        row1.createCell(++index).setCellValue("加班工资");
        row1.createCell(++index).setCellValue("其他补发(均为正)");
        row1.createCell(++index).setCellValue("其他补发备注");
        row1.createCell(++index).setCellValue("其他扣款(均为负)");
        row1.createCell(++index).setCellValue("其他扣款备注");
        row1.createCell(++index).setCellValue("浮动绩效(均为正)");
        row1.createCell(++index).setCellValue("浮动绩效备注");


        row1.createCell(++index).setCellValue("个税调整(正为正、负为负)");
        row1.createCell(++index).setCellValue("公积金公司部分(正为负、负为正)");
        row1.createCell(++index).setCellValue("公积金个人部分(正为负、负为正)");
        row1.createCell(++index).setCellValue("养老保险公司部分(正为负、负为正)");
        row1.createCell(++index).setCellValue("养老保险个人部分(正为负、负为正)");
        row1.createCell(++index).setCellValue("医疗保险公司部分(正为负、负为正)");
        row1.createCell(++index).setCellValue("医疗保险个人部分(正为负、负为正)");
        row1.createCell(++index).setCellValue("个人失业保险公司部分(正为负、负为正)");
        row1.createCell(++index).setCellValue("个人失业保险个人部分(正为负、负为正)");
        row1.createCell(++index).setCellValue("生育险公司部分(正为负、负为正)");
        row1.createCell(++index).setCellValue("工伤保险公司部分(正为负、负为正)");
        row1.createCell(++index).setCellValue("大病补助公司部分(正为负、负为正)");
        row1.createCell(++index).setCellValue("大病补助个人部分(正为负、负为正)");
        row1.createCell(++index).setCellValue("残保金(正为负、负为正)");
        row1.createCell(++index).setCellValue("服务费(正为负、负为正)");
        row1.createCell(++index).setCellValue("社保备注");

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

        //输出Excel文件
        try (OutputStream output = response.getOutputStream()) {
            //设置响应头
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(sheet.getSheetName(), "UTF-8") + ".xls");
            response.setContentType("application/msexcel");
            wb.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 渠道费用导出
     * @param data
     * @param paramMap
     * @param response
     */
    public static void reportChannel(List<SurveyChannelCostNew> data,Map<String,Object> paramMap,HttpServletResponse response){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("渠道费用导出数据-" + simpleDateFormat.format(new Date()));//建立sheet对象
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        //设置头
        HSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("案件编号");
        row1.createCell(1).setCellValue("保险公司");
        row1.createCell(2).setCellValue("方向名称");
        row1.createCell(3).setCellValue("渠道金额");
        row1.createCell(4).setCellValue("审核状态");
        row1.createCell(5).setCellValue("审核通过时间");
        row1.createCell(6).setCellValue("付款状态");
        row1.createCell(7).setCellValue("实际付款时间");
        row1.createCell(8).setCellValue("备注");
        row1.createCell(9).setCellValue("机构负责人");
        row1.createCell(10).setCellValue("申请时间");
        row1.createCell(11).setCellValue("收款人姓名");
        row1.createCell(12).setCellValue("开户行");
        row1.createCell(13).setCellValue("支行");
        row1.createCell(14).setCellValue("银行账号");
        row1.createCell(15).setCellValue("区域");
        row1.createCell(16).setCellValue("任务类型");
        row1.createCell(17).setCellValue("任务子类");
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

        for (int i = 0; i < data.size(); i++) {
            SurveyChannelCostNew item = data.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(item.getSurveyCaseNo());
            row.createCell(1).setCellValue(item.getEntrustOrgName());
            row.createCell(2).setCellValue(item.getSurveyDirectionName());
            row.createCell(3).setCellValue(item.getChnannelMoney() == null ? 0D : item.getChnannelMoney());
            if (item.getState() == 5) {
                row.createCell(4).setCellValue("付费待审核");
            }else if (item.getState() == 6) {
                row.createCell(4).setCellValue("付费审核驳回");
            }else if (item.getState() == 3) {
                row.createCell(4).setCellValue("付费审核通过（自动）");
            }else if (item.getState() == 33) {
                row.createCell(4).setCellValue("付费审核通过（人工）");
            }
            row.createCell(5).setCellValue(item.getReviewerTime() == null ? "" : simpleDateFormat.format(item.getReviewerTime()));
            if (item.getIsProPay() == 0){
                row.createCell(6).setCellValue("未申请付款");
            }else if (item.getIsProPay() == 1){
                row.createCell(6).setCellValue("已申请付款");
            }else if (item.getIsProPay() == 2){
                row.createCell(6).setCellValue("已付款");
            }
            row.createCell(7).setCellValue(item.getPayRealTime() == null ? "" : simpleDateFormat.format(item.getPayRealTime()));
            row.createCell(8).setCellValue(item.getChannelDesc());
            row.createCell(9).setCellValue(item.getSurveyUserName());
            row.createCell(10).setCellValue(item.getOperationTime() == null ? "" : simpleDateFormat.format(item.getOperationTime()));
            row.createCell(11).setCellValue(item.getPayeeUserName());
            row.createCell(12).setCellValue(item.getBankDeposit());
            row.createCell(13).setCellValue(item.getBankBranch());
            row.createCell(14).setCellValue(item.getBankNo());
            row.createCell(15).setCellValue(item.getAreaName());
            row.createCell(16).setCellValue(item.getTaskName());
            row.createCell(17).setCellValue(item.getContentTaskName());
        }

        //输出Excel文件
        try (OutputStream output = response.getOutputStream()) {
            //设置响应头
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(sheet.getSheetName(), "UTF-8") + ".xls");
            response.setContentType("application/msexcel");
            wb.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 未匹配收款导出
     */
    public static void reportUnmatch(List<BillingApplyUnmatchDto> billingApplyUnmatchDtos,HttpServletResponse response){
        HSSFWorkbook wb = new HSSFWorkbook();
        String fileName = "未匹配收款";
        HSSFSheet sheet = wb.createSheet(fileName);//建立sheet对象
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        //设置头
        HSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("到账公司");
        row1.createCell(1).setCellValue("编号");
        row1.createCell(2).setCellValue("开票产品");
        row1.createCell(3).setCellValue("金额");
        row1.createCell(4).setCellValue("附言");
        row1.createCell(5).setCellValue("备注");
        row1.createCell(6).setCellValue("认领状态");
        row1.createCell(7).setCellValue("待财务审核");
        row1.createCell(8).setCellValue("付款方");
        row1.createCell(9).setCellValue("交易时间");
        row1.createCell(10).setCellValue("已认领金额");
        row1.createCell(11).setCellValue("未认领金额");
        row1.createCell(12).setCellValue("退费金额");

        row1.createCell(13).setCellValue("开票产品");
        row1.createCell(14).setCellValue("开票对象");
        row1.createCell(15).setCellValue("收入归属机构");
        row1.createCell(16).setCellValue("认领金额");
        row1.createCell(17).setCellValue("认领时间");

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

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < billingApplyUnmatchDtos.size(); i++) {
            int size = billingApplyUnmatchDtos.get(i).getReceiveInfos().size();
            if (size > 1) {
                CellRangeAddress region = new CellRangeAddress(i + 1, i + size, 0, 0);
                sheet.addMergedRegion(region);
                region = new CellRangeAddress(i + 1, i + size, 1, 1);
                sheet.addMergedRegion(region);
                region = new CellRangeAddress(i + 1, i + size, 2, 2);
                sheet.addMergedRegion(region);
                region = new CellRangeAddress(i + 1, i + size, 3, 3);
                sheet.addMergedRegion(region);
                region = new CellRangeAddress(i + 1, i + size, 4, 4);
                sheet.addMergedRegion(region);
                region = new CellRangeAddress(i + 1, i + size, 5, 5);
                sheet.addMergedRegion(region);
                region = new CellRangeAddress(i + 1, i + size, 6, 6);
                sheet.addMergedRegion(region);
                region = new CellRangeAddress(i + 1, i + size, 7, 7);
                sheet.addMergedRegion(region);
                region = new CellRangeAddress(i + 1, i + size, 8, 8);
                sheet.addMergedRegion(region);
                region = new CellRangeAddress(i + 1, i + size, 9, 9);
                sheet.addMergedRegion(region);
                region = new CellRangeAddress(i + 1, i + size, 10, 10);
                sheet.addMergedRegion(region);
                region = new CellRangeAddress(i + 1, i + size, 11, 11);
                sheet.addMergedRegion(region);
                region = new CellRangeAddress(i + 1, i + size, 12, 12);
                sheet.addMergedRegion(region);
            }
            BillingApplyUnmatchDto billingApplyUnmatchDto = billingApplyUnmatchDtos.get(i);
            HSSFRow rowi = sheet.createRow(i + 1);
            if (size > 0){
                for (int j = 0; j < billingApplyUnmatchDtos.get(i).getReceiveInfos().size(); j++) {
                    rowi = sheet.createRow(i + 1 + j);
                    rowi.setHeightInPoints(18);//行高设置成18px
                    rowi.createCell(0).setCellValue(billingApplyUnmatchDto.getReceivingCompanyName());
                    rowi.createCell(1).setCellValue(billingApplyUnmatchDto.getUnmatchNo());
                    rowi.createCell(2).setCellValue(billingApplyUnmatchDto.getBillingItemsName());
                    rowi.createCell(3).setCellValue(billingApplyUnmatchDto.getMoney());
                    rowi.createCell(4).setCellValue(billingApplyUnmatchDto.getRemark());
                    rowi.createCell(5).setCellValue(billingApplyUnmatchDto.getComments());
                    if (billingApplyUnmatchDto.getState() == 1){
                        rowi.createCell(6).setCellValue("未认领");
                    }else if(billingApplyUnmatchDto.getState() == 2){
                        rowi.createCell(6).setCellValue("已认领");
                    }else if(billingApplyUnmatchDto.getState() == 3){
                        rowi.createCell(6).setCellValue("部分认领");
                    }
                    rowi.createCell(7).setCellValue(billingApplyUnmatchDto.getWaitCheck()?"有":"无");
                    rowi.createCell(8).setCellValue(billingApplyUnmatchDto.getPayer());
                    if (billingApplyUnmatchDto.getPayTime() !=null){
                        rowi.createCell(9).setCellValue(df.format(billingApplyUnmatchDto.getPayTime()));
                    }else {
                        rowi.createCell(9).setCellValue("");
                    }
                    rowi.createCell(10).setCellValue(Optional.ofNullable(billingApplyUnmatchDto.getMatchMoney()).orElse(0d));
                    rowi.createCell(11).setCellValue(Optional.ofNullable(billingApplyUnmatchDto.getUnmatchMoney()).orElse(0d));
                    rowi.createCell(12).setCellValue(Optional.ofNullable(billingApplyUnmatchDto.getRefundMoney()).orElse(0d));
                }
            }else{
                rowi.setHeightInPoints(18);//行高设置成18px
                rowi.createCell(0).setCellValue(billingApplyUnmatchDto.getReceivingCompanyName());
                rowi.createCell(1).setCellValue(billingApplyUnmatchDto.getUnmatchNo());
                rowi.createCell(2).setCellValue(billingApplyUnmatchDto.getBillingItemsName());
                rowi.createCell(3).setCellValue(billingApplyUnmatchDto.getMoney());
                rowi.createCell(4).setCellValue(billingApplyUnmatchDto.getRemark());
                rowi.createCell(5).setCellValue(billingApplyUnmatchDto.getComments());
                if (billingApplyUnmatchDto.getState() == 1){
                    rowi.createCell(6).setCellValue("未认领");
                }else if(billingApplyUnmatchDto.getState() == 2){
                    rowi.createCell(6).setCellValue("已认领");
                }else if(billingApplyUnmatchDto.getState() == 3){
                    rowi.createCell(6).setCellValue("部分认领");
                }
                rowi.createCell(7).setCellValue(billingApplyUnmatchDto.getWaitCheck()?"有":"无");
                rowi.createCell(8).setCellValue(billingApplyUnmatchDto.getPayer());
                if (billingApplyUnmatchDto.getPayTime() !=null){
                    rowi.createCell(9).setCellValue(df.format(billingApplyUnmatchDto.getPayTime()));
                }else {
                    rowi.createCell(9).setCellValue("");
                }
                rowi.createCell(10).setCellValue(Optional.ofNullable(billingApplyUnmatchDto.getMatchMoney()).orElse(0d));
                rowi.createCell(11).setCellValue(Optional.ofNullable(billingApplyUnmatchDto.getUnmatchMoney()).orElse(0d));
                rowi.createCell(12).setCellValue(Optional.ofNullable(billingApplyUnmatchDto.getRefundMoney()).orElse(0d));
            }


            if (size > 0){
                for (int j = 0; j < billingApplyUnmatchDtos.get(i).getReceiveInfos().size(); j++) {
                    rowi = sheet.getRow(i + 1 + j);
                    rowi.createCell(13).setCellValue(billingApplyUnmatchDto.getReceiveInfos().get(j).getBillProName());
                    rowi.createCell(14).setCellValue(billingApplyUnmatchDto.getReceiveInfos().get(j).getBillComp());
                    rowi.createCell(15).setCellValue(billingApplyUnmatchDto.getReceiveInfos().get(j).getBillStaffOrgName());
                    rowi.createCell(16).setCellValue(billingApplyUnmatchDto.getReceiveInfos().get(j).getReceiveMoney());
                    if (billingApplyUnmatchDto.getReceiveInfos().get(j).getReceiveTime() != null){
                        rowi.createCell(17).setCellValue(df.format(billingApplyUnmatchDto.getReceiveInfos().get(j).getReceiveTime()));
                    }else {
                        rowi.createCell(17).setCellValue("");
                    }
                }
            }else{
                rowi.createCell(13).setCellValue("");
                rowi.createCell(14).setCellValue("");
                rowi.createCell(15).setCellValue("");
                rowi.createCell(16).setCellValue(0D);
                rowi.createCell(17).setCellValue("");
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

    public static <T> void report(T t,List<T> data,String fileName,HttpServletResponse response){
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(fileName + "-" + simpleDateFormat.format(new Date()));//建立sheet对象
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);

        //设置头
        XSSFRow row1 = sheet.createRow(0);
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Excel.class)) {
                Excel annotation = field.getAnnotation(Excel.class);
                row1.createCell(annotation.excelIndex()).setCellValue(annotation.name());
            }
        }
        Iterator<Cell> srcCells = row1.cellIterator();
        while (srcCells.hasNext()) {
            //设置样式
            XSSFFont font = wb.createFont();
            font.setFontName("宋体");
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            XSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.VERTICAL_TOP); // 指定单元格居中对齐
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);// 指定单元格垂直居中对齐
            cellStyle.setWrapText(true);// 指定单元格自动换行
            XSSFCell srcCell = (XSSFCell) srcCells.next();
            srcCell.setCellStyle(cellStyle);
        }

        //赋值
        for (int i = 0; i < data.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            t = data.get(i);
            Field[] dataField = t.getClass().getDeclaredFields();
            for (Field field : dataField) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Excel.class)) {
                    Excel attr = field.getAnnotation(Excel.class);
                    int index = attr.excelIndex();
                    try {
                        if (field.get(t) == null || "".equals(field.get(t))) {
                            row.createCell(index).setCellValue("");
                            continue;
                        }
                        if (StringUtils.isNotEmpty(attr.dateFormat())) {
                            row.createCell(index).setCellValue(new SimpleDateFormat(attr.dateFormat()).format(field.get(t)));
                        }else if (StringUtils.isNotEmpty(attr.enumValue())){
                            row.createCell(index).setCellValue(convertByExp(String.valueOf(field.get(t)),attr.enumValue()));
                        }else{
                            row.createCell(index).setCellValue(field.get(t).toString());
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        //输出Excel文件
        try (OutputStream output = response.getOutputStream()) {
            //设置响应头
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(sheet.getSheetName(), "UTF-8") + ".xlsx");
            response.setContentType("application/msexcel");
            wb.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 解析导出值 0=男,1=女,2=未知
     *
     * @param propertyValue 参数值
     * @param converterExp 翻译注解
     * @return 解析后值
     * @throws Exception
     */
    public static String convertByExp(String propertyValue, String converterExp) throws Exception
    {
        try
        {
            String[] convertSource = converterExp.split(",");
            for (String item : convertSource)
            {
                String[] itemArray = item.split("=");
                if (itemArray[0].equals(propertyValue))
                {
                    return itemArray[1];
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        return propertyValue;
    }

    public static void reportCaseArchives(List<SurveyCaseArchivesDto> data, HttpServletResponse response){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("归档数据");//建立sheet对象
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        //设置头
        HSSFRow row1 = sheet.createRow(0);

        CellRangeAddress region = new CellRangeAddress(0, 0, 5, 8);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(0, 0, 9, 12);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(0, 0, 13, 15);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(0, 0, 16, 22);
        sheet.addMergedRegion(region);

        region = new CellRangeAddress(0, 1, 0, 0);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(0, 1, 1, 1);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(0, 1, 2, 2);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(0, 1, 3, 3);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(0, 1, 4, 4);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(0, 1, 23, 23);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(0, 1, 24, 24);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(0, 1, 25, 25);
        region = new CellRangeAddress(0, 1, 26, 26);
        sheet.addMergedRegion(region);
        row1.createCell(0).setCellValue("案件编号");
        row1.createCell(1).setCellValue("被调查人");
        row1.createCell(2).setCellValue("委托方机构");
        row1.createCell(3).setCellValue("调查机构");
        row1.createCell(4).setCellValue("更新时间");
        row1.createCell(5).setCellValue("调取的医院、社保、体检资料");
        row1.createCell(9).setCellValue("被保人身故资料");
        row1.createCell(13).setCellValue("放弃理赔");
        row1.createCell(16).setCellValue("其他调取资料");
        row1.createCell(23).setCellValue("归档状态");
        row1.createCell(24).setCellValue("归档人");
        row1.createCell(25).setCellValue("保司终审时间");
        row1.createCell(26).setCellValue("备注");
        Iterator<Cell> srcCells = row1.cellIterator();
        while (srcCells.hasNext()) {
            //设置样式
            HSSFFont font = wb.createFont();
            font.setFontName("宋体");
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            HSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
            cellStyle.setWrapText(true);// 指定单元格自动换行
            HSSFCell srcCell = (HSSFCell) srcCells.next();
            srcCell.setCellStyle(cellStyle);
        }

        HSSFRow row2 = sheet.createRow(1);

        row2.createCell(5).setCellValue("面访笔录");
        row2.createCell(6).setCellValue("病历档案");
        row2.createCell(7).setCellValue("体检报告");
        row2.createCell(8).setCellValue("社保医疗费用明细");
        row2.createCell(9).setCellValue("是否身故");
        row2.createCell(10).setCellValue("法定继承人声明书");
        row2.createCell(11).setCellValue("法定继承人关系证明");
        row2.createCell(12).setCellValue("户籍注销或死亡证明");
        row2.createCell(13).setCellValue("是否放弃");
        row2.createCell(14).setCellValue("理赔放弃声明");
        row2.createCell(15).setCellValue("其他申明文件");
        row2.createCell(16).setCellValue("是否意外");
        row2.createCell(17).setCellValue("意外事故证明");
        row2.createCell(18).setCellValue("无意外证明的原因");
        row2.createCell(19).setCellValue("公检法、单位等证明");
        row2.createCell(20).setCellValue("鉴定报告");
        row2.createCell(21).setCellValue("法律文书");
        row2.createCell(22).setCellValue("其他项");

        srcCells = row2.cellIterator();
        while (srcCells.hasNext()) {
            //设置样式
            HSSFFont font = wb.createFont();
            font.setFontName("宋体");
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            HSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
            cellStyle.setWrapText(true);// 指定单元格自动换行
            HSSFCell srcCell = (HSSFCell) srcCells.next();
            srcCell.setCellStyle(cellStyle);
        }

        for (int i = 0; i < data.size(); i++) {
            SurveyCaseArchivesDto item = data.get(i);
            HSSFRow row = sheet.createRow(i + 2);
            row.createCell(0).setCellValue(item.getSurveyCaseNo() == null ? "" : item.getSurveyCaseNo());
            row.createCell(1).setCellValue(item.getSurveyPerson() == null?"":item.getSurveyPerson());
            row.createCell(2).setCellValue(item.getEntrustOrgName() == null?"":item.getEntrustOrgName());
            row.createCell(3).setCellValue(item.getSurveyOrgName() == null?"":item.getSurveyOrgName());
            row.createCell(4).setCellValue(item.getArchivesTime() == null ? "" : simpleDateFormat.format(item.getArchivesTime()));
            if(item.getInterviewRecord() != null) {row.createCell(5).setCellValue(item.getInterviewRecord());}
            if(item.getMedicalRecord() !=null){row.createCell(6).setCellValue(item.getMedicalRecord());}

            if(item.getMedicalReport() !=null){row.createCell(7).setCellValue(item.getMedicalReport());}
            if(item.getSocialInsurance() !=null){row.createCell(8).setCellValue(item.getSocialInsurance());}

            if (item.getIsDeath() == null) {
                row.createCell(9).setCellValue("");
            }else{
                if (item.getIsDeath() == 0) {
                    row.createCell(9).setCellValue("否");
                }else if (item.getIsDeath() == 1){
                    row.createCell(9).setCellValue("是");
                }
            }

            if(item.getLegalHeir() !=null){row.createCell(10).setCellValue(item.getLegalHeir());}
            if(item.getLegalHeirRelationship() !=null){row.createCell(11).setCellValue(item.getLegalHeirRelationship());}
            if(item.getDeathCertificate() !=null){row.createCell(12).setCellValue(item.getDeathCertificate());}

            if (item.getIsGiveUp() == null) {
                row.createCell(13).setCellValue("");
            }else{
                if (item.getIsGiveUp() == 0) {
                    row.createCell(13).setCellValue("否");
                }else if (item.getIsGiveUp() == 1){
                    row.createCell(13).setCellValue("是");
                }
            }

            if(item.getClaimsGiveUp() !=null){row.createCell(14).setCellValue(item.getClaimsGiveUp());}
            if(item.getOtherStatement() !=null){row.createCell(15).setCellValue(item.getOtherStatement());}

            if (item.getIsAccident() == null) {
                row.createCell(16).setCellValue("");
            }else{
                if (item.getIsAccident() == 0) {
                    row.createCell(16).setCellValue("否");
                }else if (item.getIsAccident() == 1){
                    row.createCell(16).setCellValue("是");
                }
            }

            if(item.getAnAccident() !=null){row.createCell(17).setCellValue(item.getAnAccident());}
            if(item.getNoAccident() !=null){row.createCell(18).setCellValue(item.getNoAccident());}
            if(item.getPublicInspection() !=null){row.createCell(19).setCellValue(item.getPublicInspection());}
            if(item.getAppraisalReport() !=null){row.createCell(20).setCellValue(item.getAppraisalReport());}
            if(item.getLegalInstrument() !=null){row.createCell(21).setCellValue(item.getLegalInstrument());}
            if(item.getOtherItems() !=null){row.createCell(22).setCellValue(item.getOtherItems());}

            if (item.getArchivesState() == null || item.getArchivesState() == 0) {
                row.createCell(23).setCellValue("未归档");
            }else if (item.getArchivesState() == 1){
                row.createCell(23).setCellValue("归档完成");
            }
            if(item.getArchivesBy() !=null){row.createCell(24).setCellValue(item.getArchivesBy());}
            row.createCell(25).setCellValue(item.getEntrustReportEndDate() == null ? "" : simpleDateFormat.format(item.getEntrustReportEndDate()));
            row.createCell(26).setCellValue(item.getRemark() == null ? "" : item.getRemark());
        }

        //输出Excel文件
        try (OutputStream output = response.getOutputStream()) {
            //设置响应头
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(sheet.getSheetName(), "UTF-8") + ".xls");
            response.setContentType("application/msexcel");
            wb.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //员工管理导出
    public static void reportReAppleInfo(List<FinancialReApplyDto> data, Map<String,Object> paramMap, HttpServletResponse response){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("每刻报销");//建立sheet对象
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        //设置头
        int index = -1;
        HSSFRow row1 = sheet.createRow(0);
        row1.createCell(++index).setCellValue("编号");
        row1.createCell(++index).setCellValue("报销类型");
        row1.createCell(++index).setCellValue("事由");
        row1.createCell(++index).setCellValue("报销金额/借款金额");
        row1.createCell(++index).setCellValue("待核销金额/待还款金额");
        row1.createCell(++index).setCellValue("状态");
        row1.createCell(++index).setCellValue("申请时间");
        row1.createCell(++index).setCellValue("付款时间");
        row1.createCell(++index).setCellValue("公司抬头");
        row1.createCell(++index).setCellValue("提单人/借款人");
        row1.createCell(++index).setCellValue("承担部门");
        row1.createCell(++index).setCellValue("备注");

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

        for (int i = 0; i < data.size(); i++) {
            index = -1;
            FinancialReApplyDto info = data.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(++index).setCellValue(info.getReNo() == null ? "" : info.getReNo());

            if (info.getReType() == 1){
                row.createCell(++index).setCellValue("日常费用报销");
            }else  if (info.getReType() == 2){
                row.createCell(++index).setCellValue("对公支付");
            }else  if (info.getReType() == 3){
                row.createCell(++index).setCellValue("借款");
            }

            row.createCell(++index).setCellValue(info.getReReasons() == null ? "" : info.getReReasons());
            row.createCell(++index).setCellValue(info.getReMoney() == null ? 0D : info.getReMoney());
            row.createCell(++index).setCellValue(info.getRepaymentMoney() == null ? 0D : info.getRepaymentMoney());
            row.createCell(++index).setCellValue(info.getStateStr() == null ? "" : info.getStateStr());

            if (info.getApplyTime() != null) {
                row.createCell(++index).setCellValue(simpleDateFormat.format(info.getApplyTime()));
            } else {
                row.createCell(++index).setCellValue("");
            }

            if (info.getPayTime() != null) {
                row.createCell(++index).setCellValue(simpleDateFormat.format(info.getPayTime()));
            } else {
                row.createCell(++index).setCellValue("");
            }
            row.createCell(++index).setCellValue(info.getCompanyTitle() == null ? "" : info.getCompanyTitle());
            row.createCell(++index).setCellValue(info.getApplyUserName() == null ? "" : info.getApplyUserName());

            row.createCell(++index).setCellValue(info.getDepartmentNameStr() == null ? "" : info.getDepartmentNameStr());
            row.createCell(++index).setCellValue(info.getApplyDesc() == null ? "" : info.getApplyDesc());
        }

        //输出Excel文件
        try (OutputStream output = response.getOutputStream()) {
            //设置响应头
            response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(sheet.getSheetName(), "UTF-8") + ".xls");
            response.setContentType("application/msexcel");
            wb.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
