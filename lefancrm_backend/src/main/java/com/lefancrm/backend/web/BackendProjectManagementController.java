package com.lefancrm.backend.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.InfoSafeCompanyDto;
import com.lefancrm.backend.dto.PositionLevelDto;
import com.lefancrm.backend.dto.ProjectManageInfoDto;
import com.lefancrm.backend.dto.SurveyInvestigatorDto;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *项目管理controller
 * @author wangwei
 * @date 2018/3/24
 */
@Controller
@RequestMapping(value = "/projectManagement")
public class BackendProjectManagementController extends BackendBaseController{

    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKNED_SURVEY_INVESTIGATOR_JURISDICTION, null, req);
        List<SurveyInvestigatorDto> surveyInvestigatorDtoList=(List<SurveyInvestigatorDto>)apiFinalResponse.getResults();
        Map params = new HashMap();
        model.put("surveyInvestigatorDtoList",surveyInvestigatorDtoList);
        params.put("surveyInvestigatorDtoListJson", JsonUtil.objectToJson(surveyInvestigatorDtoList));
        model.put("params", params);
        return new ModelAndView("/market/projectList",model);
    }

    /**
     * 详情
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "/details")
    public String details(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKNED_PROJECT_MANAGE_INFO_DATAHANDLE, null, req, rsp);
    }

    @RequestMapping(value = "/export")
    public void export(HttpServletRequest req, HttpServletResponse rsp) {
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<ProjectManageInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKNED_PROJECT_MANAGE_INFO_DATAHANDLE, null, req);
        List<ProjectManageInfoDto> projectManageInfoDtoList=(List<ProjectManageInfoDto>)apiFinalResponse.getResults();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("项目管理");//建立sheet对象
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        //设置头
        HSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("项目名称");
        row1.createCell(1).setCellValue("行业归属");
        row1.createCell(2).setCellValue("司内业务属性");
        row1.createCell(3).setCellValue("项目经理");
        row1.createCell(4).setCellValue("项目进度");
        row1.createCell(5).setCellValue("委托按件数");
        row1.createCell(6).setCellValue("已结案件数");
        row1.createCell(7).setCellValue("已开票金额");
        row1.createCell(8).setCellValue("开票已到账金额");
        row1.createCell(9).setCellValue("开票未到账金额");
        row1.createCell(10).setCellValue("创建时间");

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        for (int i = 0; i < projectManageInfoDtoList.size(); i++) {
            ProjectManageInfoDto projectManageInfoDto = projectManageInfoDtoList.get(i);
            HSSFRow rowi = sheet.createRow(i + 1);
            rowi.setHeightInPoints(18);//行高设置成18px
            rowi.createCell(0).setCellValue(projectManageInfoDto.getProjectName());
            rowi.createCell(1).setCellValue(projectManageInfoDto.getIndustryAttributes());
            rowi.createCell(2).setCellValue(projectManageInfoDto.getBusinessAttributes());
            rowi.createCell(3).setCellValue(projectManageInfoDto.getManagerName());
            Integer managerState = projectManageInfoDto.getManagerState();
            if (managerState == 1){
                rowi.createCell(4).setCellValue("已接洽");
            }else if (managerState == 2){
                rowi.createCell(4).setCellValue("已投标（谈判）");
            }else if (managerState == 3){
                rowi.createCell(4).setCellValue("已签约");
            }else if (managerState == 4){
                rowi.createCell(4).setCellValue("已移交");
            }
            rowi.createCell(5).setCellValue(projectManageInfoDto.getEntrustCaseNum());
            rowi.createCell(6).setCellValue(projectManageInfoDto.getFinishCaseNum());
            rowi.createCell(7).setCellValue(projectManageInfoDto.getImgMoney());
            rowi.createCell(8).setCellValue(projectManageInfoDto.getImgAccountMoney());
            rowi.createCell(9).setCellValue(projectManageInfoDto.getImgNoAccountMoney());
            rowi.createCell(10).setCellValue(sf.format(projectManageInfoDto.getCreateTime()));
        }
        //输出Excel文件
        try (OutputStream output = rsp.getOutputStream()) {
            //设置响应头
            rsp.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode("项目管理".concat("-").concat(LocalDate.now().toString()), "UTF-8") + ".xls");
            rsp.setContentType("application/msexcel");
            wb.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
