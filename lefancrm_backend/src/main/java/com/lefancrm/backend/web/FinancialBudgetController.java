package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.FinancialBudgetDto;
import com.lefancrm.backend.dto.FinancialBudgetInfoDto;
import com.lefancrm.backend.dto.FinancialBudgetInfoExportDto;
import com.lefancrm.backend.util.ExcelReport;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/financial/budget")
public class FinancialBudgetController extends BackendBaseController {

    private DecimalFormat df = new DecimalFormat("#.00");

    @RequestMapping(value = "/budgetList")
    public ModelAndView budgetList(HttpServletRequest req, HttpServletResponse rsp, ModelAndView modelAndView) {

        String searchCode = req.getParameter("searchCode");
        if ("budget-list".equals(searchCode)) {
            modelAndView.setViewName("/budget/budgetList");
        }
        if ("budget-info-list".equals(searchCode)) {
            modelAndView.setViewName("/budget/budgetInfoList");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/budgetAjaxData")
    @ResponseBody
    public ApiFinalResponse budgetAjaxData(HttpServletRequest req) {
        String searchCode = req.getParameter("searchCode");
        if ("budget-list".equals(searchCode)) {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinancialBudgetDto>>>() {
            };
            return this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINANCIAL_BUDGET_LIST, null, req);
        }
        if ("budget-info-list".equals(searchCode)) {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinancialBudgetInfoDto>>>() {
            };
            return this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINANCIAL_BUDGET_INFO_LIST, null, req);
        }
        if ("budget-page-data".equals(searchCode)) {
            //页面筛选框数据
            TypeToken typeToken = new TypeToken<ApiFinalResponse<String>>() {
            };
            return this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINANCIAL_BUDGET_PAGE_DATA, null, req);
        }
        return null;
    }

    @RequestMapping(value = "/budgetOperate")
    @ResponseBody
    public ApiFinalResponse budgetOperate(HttpServletRequest req) {

        String btnCode = req.getParameter("btnCode");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<String>>() {
        };

        if ("add".equals(btnCode)) {
            return this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINANCIAL_BUDGET_ADD, null, req);
        }
        if ("delete".equals(btnCode)) {
            return this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINANCIAL_BUDGET_DELETE, null, req);
        }
        if ("edit".equals(btnCode)) {
            return this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINANCIAL_BUDGET_EDIT, null, req);
        }
        if ("refresh".equals(btnCode)) {
            return this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINANCIAL_BUDGET_REFRESH, null, req);
        }
        return null;
    }


    /**
     * 导出
     *
     * @param req
     * @param rsp
     */
    @RequestMapping(value = "/budgetExport")
    public void budgetExport(HttpServletRequest req, HttpServletResponse rsp) {

        String budgetId = req.getParameter("budgetId");
        Map<String, Object> paramMap = new HashMap<>();
        if (StringUtils.isNotBlank(budgetId)) {
            paramMap.put("budgetId", Long.valueOf(budgetId));
        } else {
            paramMap.put("budgetId", 0L);
        }
        paramMap.put("isPage", false);//不分页

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinancialBudgetInfoDto>>>() {
        };
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINANCIAL_BUDGET_INFO_LIST, paramMap, req);
        List<FinancialBudgetInfoDto> financialBudgetInfoDtoList = (List<FinancialBudgetInfoDto>) apiFinalResponse.getResults();
        ExcelReport.report(new FinancialBudgetInfoDto(), financialBudgetInfoDtoList, "预算模板", rsp);
    }


    /**
     * 导入
     *
     * @param req
     */
    @RequestMapping(value = "/budgetImport")
    public ApiFinalResponse budgetImport(MultipartFile file, HttpServletRequest req) {
        List<FinancialBudgetInfoDto> infoDtoList = new ArrayList<>();
        Map paramMap = new HashMap();
        try (InputStream input = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(input);
            Sheet hssfSheet = workbook.getSheetAt(0);
            //读取行
            for (int rowNum = 1; rowNum < hssfSheet.getLastRowNum() + 1; rowNum++) {
                Row row = hssfSheet.getRow(rowNum);
                if (row == null) continue;
                FinancialBudgetInfoDto infoDto = new FinancialBudgetInfoDto();
                for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                    if (cellNum == 0) {
                        //首先强制设置成string类型
                        row.getCell(cellNum).setCellType(XSSFCell.CELL_TYPE_STRING);
                        String rowContent = row.getCell(cellNum).getStringCellValue().trim();
                        if (StringUtils.isNotBlank(rowContent)) {
                            Long aLong = Long.valueOf(rowContent);
                            infoDto.setId(aLong);
                        }
                    }
                    if (cellNum >= 5) {
                        row.getCell(cellNum).setCellType(XSSFCell.CELL_TYPE_STRING);
                        Double numericCellValue = 0d;
                        if (row.getCell(cellNum) != null) {
                            String stringCellValue = row.getCell(cellNum).getStringCellValue();
                            if (StringUtils.isNotBlank(stringCellValue)) {
                                try {
                                    numericCellValue = Double.valueOf(stringCellValue);
                                }catch (Exception e){
                                    continue;
                                }
                            }
                        }
                        if (cellNum == 5) {
                            infoDto.setJanuary(numericCellValue);
                        } else if (cellNum == 6) {
                            infoDto.setFebruary(numericCellValue);
                        } else if (cellNum == 7) {
                            infoDto.setMarch(numericCellValue);
                        } else if (cellNum == 8) {
                            infoDto.setApril(numericCellValue);
                        } else if (cellNum == 9) {
                            infoDto.setMay(numericCellValue);
                        } else if (cellNum == 10) {
                            infoDto.setJune(numericCellValue);
                        } else if (cellNum == 11) {
                            infoDto.setJuly(numericCellValue);
                        } else if (cellNum == 12) {
                            infoDto.setAugust(numericCellValue);
                        } else if (cellNum == 13) {
                            infoDto.setSeptember(numericCellValue);
                        } else if (cellNum == 14) {
                            infoDto.setOctober(numericCellValue);
                        } else if (cellNum == 15) {
                            infoDto.setNovember(numericCellValue);
                        } else if (cellNum == 16) {
                            infoDto.setDecember(numericCellValue);
                        }
                    }
                }
                infoDtoList.add(infoDto);
            }
            paramMap.put("infoDtoList", infoDtoList);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinancialBudgetInfoDto>>>() {
        };
        return this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINANCIAL_BUDGET_IMPORT, paramMap, req);
    }


    @RequestMapping(value = "/report")
    public ModelAndView report(ModelAndView modelAndView) {

        modelAndView.setViewName("/budget/report/reportList");
        return modelAndView;
    }

    /**
     * 报表数据
     *
     * @param req
     * @return
     */

    @RequestMapping(value = "/budgetReportAjaxData")
    @ResponseBody
    public ApiFinalResponse budgetReportAjaxData(HttpServletRequest req) {
        String searchCode = req.getParameter("searchCode");
        if ("type1".equals(searchCode)) {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinancialBudgetDto>>>() {
            };
            return this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINANCIAL_BUDGET_REPORT_DATA, null, req);
        }
        if ("type2".equals(searchCode) || "type3".equals(searchCode)) {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinancialBudgetInfoDto>>>() {
            };
            return this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINANCIAL_BUDGET_REPORT_DATA, null, req);
        }
        return null;
    }

    /**
     * 预算报表导出
     *
     * @param req
     * @param rsp
     */
    @RequestMapping(value = "/budgetReportExport")
    public void budgetReportExport(HttpServletRequest req, HttpServletResponse rsp) {

        String searchCode = req.getParameter("searchCode");
        List<FinancialBudgetInfoExportDto> resultList = new ArrayList<>();
        Map appendMap = new HashMap();
        appendMap.put("pageSize", 50);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinancialBudgetInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINANCIAL_BUDGET_REPORT_DATA, appendMap, req);
        appendMap.put("pageFlag",false);
        List<FinancialBudgetInfoDto> list = ecursiveResult(apiFinalResponse, typeToken, appendMap, req, new ArrayList<>(apiFinalResponse.getCount()), 0);
        for (FinancialBudgetInfoDto infoDto : list) {
            FinancialBudgetInfoExportDto financialBudgetInfoExportDto = new FinancialBudgetInfoExportDto();
            financialBudgetInfoExportDto.setYear(infoDto.getYear());
            financialBudgetInfoExportDto.setYearMoney(getValue(infoDto.getYearMoney()));
            financialBudgetInfoExportDto.setAscriptionCompany(infoDto.getAscriptionCompany());
            financialBudgetInfoExportDto.setAscriptionOrgan(infoDto.getAscriptionOrgan());
            if ("type2".equals(searchCode)) {
                financialBudgetInfoExportDto.setCostTypeName("-");
            } else if ("type3".equals(searchCode)) {
                financialBudgetInfoExportDto.setCostTypeName(infoDto.getCostTypeName());
            }
            financialBudgetInfoExportDto.setUseYearMoney(getValue(infoDto.getUseYearMoney()));
            financialBudgetInfoExportDto.setSurplusYearMoney(infoDto.getSurplusYearMoney());
            financialBudgetInfoExportDto.setJanuary(getValue(infoDto.getJanuaryUes()) + "/" + getValue(infoDto.getJanuary()));
            financialBudgetInfoExportDto.setFebruary(getValue(infoDto.getFebruaryUes()) + "/" + getValue(infoDto.getFebruary()));
            financialBudgetInfoExportDto.setMarch(getValue(infoDto.getMarchUes()) + "/" + getValue(infoDto.getMarch()));
            financialBudgetInfoExportDto.setApril(getValue(infoDto.getAprilUes()) + "/" + getValue(infoDto.getApril()));
            financialBudgetInfoExportDto.setMay(getValue(infoDto.getMayUes()) + "/" + getValue(infoDto.getMay()));
            financialBudgetInfoExportDto.setJune(getValue(infoDto.getJuneUes()) + "/" + getValue(infoDto.getJune()));
            financialBudgetInfoExportDto.setJuly(getValue(infoDto.getJulyUes()) + "/" + getValue(infoDto.getJuly()));
            financialBudgetInfoExportDto.setAugust(getValue(infoDto.getAugustUes()) + "/" + getValue(infoDto.getAugust()));
            financialBudgetInfoExportDto.setSeptember(getValue(infoDto.getSeptemberUes()) + "/" + getValue(infoDto.getSeptember()));
            financialBudgetInfoExportDto.setOctober(getValue(infoDto.getOctoberUes()) + "/" + getValue(infoDto.getOctober()));
            financialBudgetInfoExportDto.setNovember(getValue(infoDto.getNovemberUes()) + "/" + getValue(infoDto.getNovember()));
            financialBudgetInfoExportDto.setDecember(getValue(infoDto.getDecemberUes()) + "/" + getValue(infoDto.getDecember()));
            resultList.add(financialBudgetInfoExportDto);
        }

        ExcelReport.report(new FinancialBudgetInfoExportDto(), resultList, "预算报表", rsp);

    }


    public List<FinancialBudgetInfoDto> ecursiveResult(ApiFinalResponse apiFinalResponse, TypeToken<ApiFinalResponse<List<FinancialBudgetInfoDto>>> typeToken, Map<String, Object> appendMap, HttpServletRequest req, List<FinancialBudgetInfoDto> temporaryList, int pageNum) {
        if(apiFinalResponse !=null && apiFinalResponse.getResults() !=null){
            List<FinancialBudgetInfoDto> results = new ArrayList<>((List<FinancialBudgetInfoDto>) apiFinalResponse.getResults());
            temporaryList.addAll(results);
            pageNum ++;
            if (pageNum * 50 < apiFinalResponse.getCount()) {
                appendMap.put("pageIndex", pageNum * 50);
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINANCIAL_BUDGET_REPORT_DATA, appendMap, req);
                return ecursiveResult(apiFinalResponse, typeToken, appendMap, req, temporaryList, pageNum);
            }
        }
        return temporaryList;
    }

    private double getValue(Double d) {
        return d == null ? 0 : Double.parseDouble(df.format(d));
    }
}
