package com.lefancrm.backend.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonArray;
import com.lefancrm.backend.dto.HoboProCase;
import com.lefancrm.backend.dto.SurveyZhaOrgAssess;
import com.lefancrm.backend.dto.staff.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaffExcel {
    /**
     * 解析钉钉模板数据
     * @param inputStream
     * @return
     */
    public static List<DdDataDTO>  generate(InputStream inputStream){
        HSSFWorkbook workbook = null;
        Sheet sheet = null;
        Row row = null;
        List<DdDataDTO> data = new ArrayList<>();
        StaffExcel excel = new StaffExcel();
        workbook = excel.readHSSFExcel(inputStream);
        if (workbook != null){
            sheet = workbook.getSheetAt(0);
            int lines = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < lines; i++) {
                DdDataDTO ddDataDTO = new DdDataDTO();
                row = sheet.getRow(i);
                if("0".equals(excel.convert(row.getCell(1)))){
                    continue;
                }
                ddDataDTO.setJobNo(excel.convert(row.getCell(1)));
                Double num1 = Double.parseDouble(excel.convert(row.getCell(2)));//迟到
                Double num2 = Double.parseDouble(excel.convert(row.getCell(3)));//早退
                ddDataDTO.setLateDays(new DecimalFormat("0").format(num1 + num2));//迟到 + 早退
                ddDataDTO.setAbsentDays("0"); //旷工天数
                //2025年6月5日   矿工天数改成了补贴
                ddDataDTO.setButie(excel.convert(row.getCell(4)));
                ddDataDTO.setLeaveDays(excel.convert(row.getCell(5)));  //事假
                ddDataDTO.setSickTIme(excel.convert(row.getCell(6)));   //病假
                ddDataDTO.setOvertimePay(excel.convert(row.getCell(7)));//加班工资
                ddDataDTO.setOtherPay(excel.convert(row.getCell(8)));   //其他补发款

                ddDataDTO.setRemarks(null);
                if(row.getCell(9) != null && !"0".equals(excel.convert(row.getCell(9)))){
                    ddDataDTO.setRemarks(excel.convert(row.getCell(9)));    //其他补发备注
                }

                ddDataDTO.setOtherCutPay(excel.convert(row.getCell(10)));   //其他扣款
                ddDataDTO.setOtherCutRemarks(null);
                if(row.getCell(11) != null && !"0".equals(excel.convert(row.getCell(11)))){
                    ddDataDTO.setOtherCutRemarks(excel.convert(row.getCell(11)));    //其他补发备注
                }

                ddDataDTO.setWelfarePay(excel.convert(row.getCell(12)));   //员工福利
                ddDataDTO.setWelfareRemark(null);
                if(row.getCell(13) != null && !"0".equals(excel.convert(row.getCell(13)))){
                    ddDataDTO.setWelfareRemark(excel.convert(row.getCell(13)));    //员工福利备注
                }

                ddDataDTO.setIndividualTaxChange(excel.convert(row.getCell(14))); //个税调整

                ddDataDTO.setConpanyFundMoney(excel.convert(row.getCell(15)));//
                ddDataDTO.setPersonalFundMoney(excel.convert(row.getCell(16)));//
                ddDataDTO.setCompanyPensionBenefits(excel.convert(row.getCell(17)));//
                ddDataDTO.setPersonalPensionBenefits(excel.convert(row.getCell(18)));//
                ddDataDTO.setCompanyMedicalInsurance(excel.convert(row.getCell(19)));//
                ddDataDTO.setPersonalMedicalInsurance(excel.convert(row.getCell(20)));//
                ddDataDTO.setCompanyUnemploymentInsurance(excel.convert(row.getCell(21)));//
                ddDataDTO.setPersonalUnemploymentInsurance(excel.convert(row.getCell(22)));//
                ddDataDTO.setCompanyBirthInsurance(excel.convert(row.getCell(23)));//
                ddDataDTO.setCompanyInjuryInsurance(excel.convert(row.getCell(24)));//

                ddDataDTO.setCompanySickSubsidy(excel.convert(row.getCell(25))); //大病补助公司部分
                ddDataDTO.setPersonalSickSubsidy(excel.convert(row.getCell(26))); //大病补助个人部分
                ddDataDTO.setDisabilityInsurance(excel.convert(row.getCell(27))); //残保金
                ddDataDTO.setServiceFee(excel.convert(row.getCell(28))); //服务费

                ddDataDTO.setSocialRemark(null);
                if(row.getCell(29) != null && !"0".equals(excel.convert(row.getCell(29)))){
                    ddDataDTO.setSocialRemark(excel.convert(row.getCell(29))); //社保备注
                }
                data.add(ddDataDTO);
            }
        }
        return data;
    }

    /**
     * 导入其他数据
     * @param inputStream
     * @return
     */
    public static List<JsDataDTO> generateJsData(InputStream inputStream){
        XSSFWorkbook workbook = null;
        Sheet sheet = null;
        Row row = null;
        List<JsDataDTO> data = new ArrayList<>();
        StaffExcel excel = new StaffExcel();
        workbook = excel.readExcel(inputStream);
        if (workbook != null){
            sheet = workbook.getSheetAt(0);
            int lines = sheet.getPhysicalNumberOfRows();
            /*for (int i = 1; i < lines; i++) {
                JsDataDTO ddDataDTO = new JsDataDTO();
                row = sheet.getRow(i);
                if("0".equals(excel.convert(row.getCell(1)))){
                    continue;
                }
                ddDataDTO.setCardNo(excel.convert(row.getCell(1)));
                ddDataDTO.setConpanyFundMoney(excel.convert(row.getCell(2)));//
                ddDataDTO.setPersonalFundMoney(excel.convert(row.getCell(3)));//
                ddDataDTO.setCompanyPensionBenefits(excel.convert(row.getCell(4)));//
                ddDataDTO.setPersonalPensionBenefits(excel.convert(row.getCell(5)));//
                ddDataDTO.setCompanyMedicalInsurance(excel.convert(row.getCell(6)));//
                ddDataDTO.setPersonalMedicalInsurance(excel.convert(row.getCell(7)));//
                ddDataDTO.setCompanyUnemploymentInsurance(excel.convert(row.getCell(8)));//
                ddDataDTO.setPersonalUnemploymentInsurance(excel.convert(row.getCell(9)));//
                ddDataDTO.setCompanyBirthInsurance(excel.convert(row.getCell(10)));//
                ddDataDTO.setCompanyInjuryInsurance(excel.convert(row.getCell(11)));//
                data.add(ddDataDTO);
            }*/

            for (int i = 6; i < lines; i++) {
                JsDataDTO ddDataDTO = new JsDataDTO();
                row = sheet.getRow(i);
                if("0".equals(excel.convert(row.getCell(4)))){
                    continue;
                }
                ddDataDTO.setUserName(excel.convert(row.getCell(3)));
                ddDataDTO.setCardNo(excel.convert(row.getCell(4)));
                ddDataDTO.setCompanyPensionBenefits(excel.convert(row.getCell(10)));//
                ddDataDTO.setPersonalPensionBenefits(excel.convert(row.getCell(12)));//
                ddDataDTO.setCompanyMedicalInsurance(excel.convert(row.getCell(15)));//
                ddDataDTO.setPersonalMedicalInsurance(excel.convert(row.getCell(17)));//
                ddDataDTO.setCompanyUnemploymentInsurance(excel.convert(row.getCell(20)));//
                ddDataDTO.setPersonalUnemploymentInsurance(excel.convert(row.getCell(22)));//
                ddDataDTO.setCompanyInjuryInsurance(excel.convert(row.getCell(25)));//
                ddDataDTO.setCompanyBirthInsurance(excel.convert(row.getCell(28)));//
/*                ddDataDTO.setCompanySickSubsidy(excel.convert(row.getCell(29)));
                ddDataDTO.setPersonalSickSubsidy(excel.convert(row.getCell(30)));*/
                ddDataDTO.setConpanyFundMoney(excel.convert(row.getCell(33)));//
                ddDataDTO.setPersonalFundMoney(excel.convert(row.getCell(35)));//
                /*ddDataDTO.setDisabilityInsurance(excel.convert(row.getCell(42)));
                ddDataDTO.setServiceFee(excel.convert(row.getCell(43)));
                ddDataDTO.setSocialRemark(excel.convert(row.getCell(45)));*/
                data.add(ddDataDTO);
            }
        }
        return data;
    }

    /**
     * 导入其他数据
     * @param inputStream
     * @return
     */
    public static List<OtherDataDTO> generateOtherData(InputStream inputStream){
        XSSFWorkbook workbook = null;
        Sheet sheet = null;
        Row row = null;
        List<OtherDataDTO> data = new ArrayList<>();
        StaffExcel excel = new StaffExcel();
        workbook = excel.readExcel(inputStream);
        if (workbook != null){
            sheet = workbook.getSheetAt(0);
            int lines = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < lines; i++) {
                OtherDataDTO ddDataDTO = new OtherDataDTO();
                row = sheet.getRow(i);
                if("0".equals(excel.convert(row.getCell(1)))){
                    continue;
                }
                ddDataDTO.setJobNo(excel.convert(row.getCell(1)));
                ddDataDTO.setOverTimeMoney(Double.parseDouble(excel.convert(row.getCell(2))));//
                ddDataDTO.setOtherMoney(Double.parseDouble(excel.convert(row.getCell(3))));
                data.add(ddDataDTO);
            }
        }
        return data;
    }

    /**
     * 解析众安机构考核Excel
     * @param inputStream
     * @return
     */
    public static List<SurveyZhaOrgAssess> generateZhaData(InputStream inputStream){
        XSSFWorkbook workbook = null;
        Sheet sheet = null;
        Row row = null;
        List<SurveyZhaOrgAssess> zhaOrgAssesses = new ArrayList<>();
        StaffExcel excel = new StaffExcel();
        workbook = excel.readExcel(inputStream);
        if (workbook != null){
            sheet = workbook.getSheetAt(0);
            int lines = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < lines; i++) {
                SurveyZhaOrgAssess zhaOrgAssess = new SurveyZhaOrgAssess();
                row = sheet.getRow(i);
                if (row == null){
                    continue;
                }
                zhaOrgAssess.setClaimsNo(excel.convert(row.getCell(0),new String()));
                if (StringUtils.isEmpty(zhaOrgAssess.getClaimsNo())){
                    continue;
                }
                String value = excel.convert(row.getCell(2),new String());
                zhaOrgAssess.setIsSun("阳性".equals(value) || "阳".equals(value) ? 1 : 0);
                if (zhaOrgAssess.getIsSun() == 0){
                    zhaOrgAssess.setDerogationMoney(0D);
                }else{
                    zhaOrgAssess.setDerogationMoney(excel.convert(row.getCell(1),new Double(0)));
                }
                zhaOrgAssess.setCaeEff(excel.convert(row.getCell(3),new Double(0)));
                zhaOrgAssess.setEntrustSubmitMoney(excel.convert(row.getCell(4),new Double(0)));
                zhaOrgAssess.setPfAmt(excel.convert(row.getCell(5),new Double(0)));
                if (zhaOrgAssess.getIsSun() == 0){
                    zhaOrgAssess.setJsAmt(0D);
                }else{
                    zhaOrgAssess.setJsAmt(excel.convert(row.getCell(6),new Double(0)));
                }
                zhaOrgAssesses.add(zhaOrgAssess);
            }
        }
        return zhaOrgAssesses;
    }


    public static void main(String[] args) {
        String filePath = "/Users/lixianfeng/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/1214abae169247d539cfdda0f1b7ebfa/Message/MessageTemp" +
                "/e4759aed40a072f0c63c7e76669fc142/File/zhonganV1.xlsx";
//        String filePath = "F:\\generate\\zhongan.xlsx";
        try {
            List<SurveyZhaOrgAssess> zhaOrgAssesses = generateZhaData(new FileInputStream(filePath));
            for (SurveyZhaOrgAssess zhaOrgAssess : zhaOrgAssesses) {
                System.out.println(JSON.toJSONString(zhaOrgAssess));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public <T> T convert(Cell cell,T t){
        if (String.class.equals(t.getClass())){
            if (cell == null){
                return (T) "";
            }
            return (T) cell.getStringCellValue();
        }
        else if (Double.class.equals(t.getClass())){
            if (cell == null){
                return (T) new Double(0);
            }
            try {
                return (T) new Double(cell.getNumericCellValue());
            } catch (Exception e) {
                return (T) new Double(StringUtils.isEmpty(cell.getStringCellValue()) ? "0" : cell.getStringCellValue());
            }
        }else if (Date.class.equals(t.getClass())){
            if (cell == null){
                return null;
            }
            return (T) cell.getDateCellValue();
        }
        return null;
    }

    public String convert(Cell cell,String pattern){
        String value = null;
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        if (cell == null) {
            return "0";
        }
        int cellType = cell.getCellType();
        switch (cellType){
            case 0 : value = decimalFormat.format(cell.getNumericCellValue()); break;
            case 1 : value = cell.getStringCellValue(); break;
            case 3 : value = "0"; break;
            default: value = null;
        }
        return value == null || "".equals(value) || "".equals(value) ? "0" : value;
//        CELL_TYPE_NUMERIC　　数值型　　0
//
//        CELL_TYPE_STRING　　字符串型　　1
//
//        CELL_TYPE_FORMULA　　公式型       2
//
//        CELL_TYPE_BLANK　　     空值　　　3
//
//        CELL_TYPE_BOOLEAN　　布尔型　　4
//
//        CELL_TYPE_ERROR　　    错误　　　5
    }

    public String convert(Cell cell){
        String value = null;
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        if (cell == null) {
            return "0";
        }
        int cellType = cell.getCellType();
        switch (cellType){
            case 0 : value = decimalFormat.format(cell.getNumericCellValue()); break;
            case 1 : value = cell.getStringCellValue(); break;
            case 3 : value = "0"; break;
            default: value = null;
        }
        return value == null || "".equals(value) || "".equals(value) ? "0" : value;
//        CELL_TYPE_NUMERIC　　数值型　　0
//
//        CELL_TYPE_STRING　　字符串型　　1
//
//        CELL_TYPE_FORMULA　　公式型       2
//
//        CELL_TYPE_BLANK　　     空值　　　3
//
//        CELL_TYPE_BOOLEAN　　布尔型　　4
//
//        CELL_TYPE_ERROR　　    错误　　　5
    }

    //读取excel
    public XSSFWorkbook readExcel(String filePath){
        XSSFWorkbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public XSSFWorkbook readExcel(InputStream inputStream){
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }


    /**
     * 解析员工管理导入系统
     * @param inputStream
     * @return
     */
    public static List<StaffPersonnelInfoDto>  generatePersonnelInfo(InputStream inputStream){
        HSSFWorkbook workbook = null;
        Sheet sheet = null;
        Row row = null;
        List<StaffPersonnelInfoDto> data = new ArrayList<>();
        StaffExcel excel = new StaffExcel();
        workbook = excel.readHSSFExcel(inputStream);
        if (workbook != null){
            sheet = workbook.getSheetAt(0);
            int lines = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < lines; i++) {
                StaffPersonnelInfoDto infoDTO = new StaffPersonnelInfoDto();
                row = sheet.getRow(i);
                infoDTO.setRealName(excel.convert(row.getCell(1)));
                if("0".equals(excel.convert(row.getCell(2)))){
                    continue;
                }
                infoDTO.setJobNo(excel.convert(row.getCell(2)));
                infoDTO.setSocialSecurityCompany(excel.convert(row.getCell(3)));
                infoDTO.setCompany(excel.convert(row.getCell(4)));
                infoDTO.setOrgan(excel.convert(row.getCell(5)));
                infoDTO.setDepartment(excel.convert(row.getCell(6)));
                infoDTO.setTeam(excel.convert(row.getCell(7)));
                infoDTO.setPostAppellationName(excel.convert(row.getCell(8)));
                infoDTO.setJobPost(excel.convert(row.getCell(9)));
                infoDTO.setPostRankName(excel.convert(row.getCell(10)));
                infoDTO.setSurveyLevelName(excel.convert(row.getCell(11)));

                String userTel = excel.convert(row.getCell(12));
                if (!StringUtils.isEmpty(userTel)){
                    if (userTel.indexOf(".") > -1){
                        userTel = userTel.substring(0,userTel.indexOf("."));
                    }
                }
                infoDTO.setUserTel(userTel);

                infoDTO.setExtTel(excel.convert(row.getCell(13)));//分机号
                infoDTO.setOfficePlace(excel.convert(row.getCell(14)));//办公地点
                infoDTO.setRemark(excel.convert(row.getCell(15)));//备注
                String time = "";
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");


                if (!StringUtils.isEmpty(row.getCell(16).getStringCellValue())){
                    time = row.getCell(16).getStringCellValue();
                    try {
                        infoDTO.setEntryTime(format.parse(time));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }

                if (!StringUtils.isEmpty(row.getCell(17).getStringCellValue())){
                    time = row.getCell(17).getStringCellValue();
                    try {
                        infoDTO.setRegularTime(format.parse(time));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }


                if (!StringUtils.isEmpty(row.getCell(18).getStringCellValue())){
                    time = row.getCell(18).getStringCellValue();
                    try {
                        infoDTO.setQuitTime(format.parse(time));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }

                String relationName = excel.convert(row.getCell(21));
                switch (relationName){
                    case "全职" : infoDTO.setRelation(1); break;
                    case "兼职" : infoDTO.setRelation(2); break;
                    case "退休返聘" : infoDTO.setRelation(3); break;
                    case "实习生" : infoDTO.setRelation(4); break;
                    case "合伙" : infoDTO.setRelation(5); break;
                    case "合伙+兼职" : infoDTO.setRelation(6); break;
                    case "合伙（发固定绩效）" :
                    case "合伙(发固定绩效)":
                        infoDTO.setRelation(7); break;
                }

                String staffStateName = excel.convert(row.getCell(22));
                switch (staffStateName){
                    case "试用期员工" : infoDTO.setStaffState(1); break;
                    case "调整人员" : infoDTO.setStaffState(2); break;
                    case "离职待结算" : infoDTO.setStaffState(3); break;
                    case "转正人员" : infoDTO.setStaffState(4); break;
                    case "在职" : infoDTO.setStaffState(5); break;
                    case "已离职" : infoDTO.setStaffState(6); break;
                }
                if (infoDTO.getStaffState() ==  null){
                    infoDTO.setStaffState(1);
                }
                infoDTO.setTrialTime(excel.convert(row.getCell(23)));//试用期
                infoDTO.setJobLevel(excel.convert(row.getCell(25)));//岗位职别
                infoDTO.setEducation(excel.convert(row.getCell(26)));//学历
                infoDTO.setGraduationSchool(excel.convert(row.getCell(27)));//毕业院校

                if (!StringUtils.isEmpty(row.getCell(28).getStringCellValue())){
                    time = row.getCell(28).getStringCellValue();
                    try {
                        infoDTO.setGraduationTime(format.parse(time));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
                infoDTO.setMajor(excel.convert(row.getCell(29)));//专业

                infoDTO.setBankNo(excel.convert(row.getCell(30)));//
                infoDTO.setBankName(excel.convert(row.getCell(31)));//
                infoDTO.setContractCompany(excel.convert(row.getCell(32)));//
                infoDTO.setContractType(excel.convert(row.getCell(33)));//

                if (!StringUtils.isEmpty(row.getCell(34).getStringCellValue())){
                    time = row.getCell(34).getStringCellValue();
                    try {
                        infoDTO.setFirstContractBeginTime(format.parse(time));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }


                if (!StringUtils.isEmpty(row.getCell(35).getStringCellValue())){
                    time = row.getCell(35).getStringCellValue();
                    try {
                        infoDTO.setFirstContractEndTime(format.parse(time));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }

                if (!StringUtils.isEmpty(row.getCell(36).getStringCellValue())){
                    time = row.getCell(36).getStringCellValue();
                    try {
                        infoDTO.setNowContractBeginTime(format.parse(time));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }


                if (!StringUtils.isEmpty(row.getCell(37).getStringCellValue())){
                    time = row.getCell(37).getStringCellValue();
                    try {
                        infoDTO.setNowContractEndTime(format.parse(time));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }

                infoDTO.setContractTerm(excel.convert(row.getCell(38)));//合同期限
                infoDTO.setRenewNum(excel.convert(row.getCell(39)));//续约次数

                infoDTO.setEmergencyContactName(excel.convert(row.getCell(40)));//
                infoDTO.setEmergencyContactRelation(excel.convert(row.getCell(41)));//
                infoDTO.setEmergencyContactTel(excel.convert(row.getCell(42)));//

                infoDTO.setFamilyName(excel.convert(row.getCell(43)));//
                infoDTO.setFamilyRelation(excel.convert(row.getCell(44)));//
                infoDTO.setFamilySex(excel.convert(row.getCell(45)));//
                if (!StringUtils.isEmpty(row.getCell(46).getStringCellValue())){
                    time = row.getCell(46).getStringCellValue();
                    try {
                        infoDTO.setFamilyBirthday(format.parse(time));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
                infoDTO.setFamilyTel(excel.convert(row.getCell(47)));//
                infoDTO.setFamilyIdcardName(excel.convert(row.getCell(48)));//






                infoDTO.setIdCard(excel.convert(row.getCell(49)));
                infoDTO.setPayAddress(excel.convert(row.getCell(64)));
                infoDTO.setBasePay(Double.parseDouble(excel.convert(row.getCell(65))));
                infoDTO.setFixedPerfPay(Double.parseDouble(excel.convert(row.getCell(66))));
                infoDTO.setManagePerfPay(Double.parseDouble(excel.convert(row.getCell(67))));
                infoDTO.setManagePerfPaySize(Double.parseDouble(excel.convert(row.getCell(68))));
                infoDTO.setManagePerfPaySizeHz(Double.parseDouble(excel.convert(row.getCell(69))));
                infoDTO.setAssesPerfPay(Double.parseDouble(excel.convert(row.getCell(70))));
                infoDTO.setTravelAllowancePay(Double.parseDouble(excel.convert(row.getCell(71))));
                infoDTO.setPensionBase(Double.parseDouble(excel.convert(row.getCell(72))));
                infoDTO.setPensionCompanyRate(Double.parseDouble(excel.convertTwo(row.getCell(73))));
                infoDTO.setPensionPersonalRate(Double.parseDouble(excel.convertTwo(row.getCell(74))));
                infoDTO.setMedicalBase(Double.parseDouble(excel.convert(row.getCell(75))));
                infoDTO.setMedicalCompanyRate(Double.parseDouble(excel.convertTwo(row.getCell(76))));
                infoDTO.setMedicalPersonalRate(Double.parseDouble(excel.convertTwo(row.getCell(77))));
                infoDTO.setUpmBase(Double.parseDouble(excel.convert(row.getCell(78))));
                infoDTO.setUpmCompanyRate(Double.parseDouble(excel.convertTwo(row.getCell(79))));
                infoDTO.setUpmPersonalRate(Double.parseDouble(excel.convertTwo(row.getCell(80))));
                infoDTO.setIsaBase(Double.parseDouble(excel.convert(row.getCell(81))));
                infoDTO.setIsaCompanyRate(Double.parseDouble(excel.convertTwo(row.getCell(82))));
                infoDTO.setBirthBase(Double.parseDouble(excel.convert(row.getCell(83))));
                infoDTO.setBirthCompanyRate(Double.parseDouble(excel.convertTwo(row.getCell(84))));
                infoDTO.setFundPay(Double.parseDouble(excel.convert(row.getCell(85))));
                infoDTO.setFundPayCompanyRate(Double.parseDouble(excel.convertTwo(row.getCell(86))));
                infoDTO.setFundPayPersonalRate(Double.parseDouble(excel.convertTwo(row.getCell(87))));
                infoDTO.setQuitCost(Double.parseDouble(excel.convertTwo(row.getCell(88))));
                data.add(infoDTO);
            }
        }
        return data;
    }

    public String convertTwo(Cell cell){
        String value = null;
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        int cellType = cell.getCellType();
        switch (cellType){
            case 0 : value = decimalFormat.format(cell.getNumericCellValue()); break;
            case 1 : value = cell.getStringCellValue(); break;
            case 3 : value = "0"; break;
            default: value = null;
        }
        return value == null || "".equals(value) || "".equals(value) ? "0" : value;
//        CELL_TYPE_NUMERIC　　数值型　　0
//
//        CELL_TYPE_STRING　　字符串型　　1
//
//        CELL_TYPE_FORMULA　　公式型       2
//
//        CELL_TYPE_BLANK　　     空值　　　3
//
//        CELL_TYPE_BOOLEAN　　布尔型　　4
//
//        CELL_TYPE_ERROR　　    错误　　　5
    }

    public HSSFWorkbook readHSSFExcel(InputStream inputStream){
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * 导入绩效的明细
     * @param inputStream
     * @return
     */
    public static List<StaffPerformancePersonnelDto> generatePerformancePersonnelData(InputStream inputStream){
        HSSFWorkbook workbook = null;
        Sheet sheet = null;
        Row row = null;
        List<StaffPerformancePersonnelDto> data = new ArrayList<>();
        StaffExcel excel = new StaffExcel();
        workbook = excel.readHSSFExcel(inputStream);
        if (workbook != null){
            sheet = workbook.getSheetAt(0);
            int lines = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < lines; i++) {
                StaffPerformancePersonnelDto ddDataDTO = new StaffPerformancePersonnelDto();
                row = sheet.getRow(i);
                if("0".equals(excel.convert(row.getCell(1)))){
                    continue;
                }
                ddDataDTO.setRealName(excel.convert(row.getCell(0)));
                ddDataDTO.setJobNo(excel.convert(row.getCell(1)));
                ddDataDTO.setAssesPerfPay(Double.parseDouble(excel.convert(row.getCell(2))));//
                ddDataDTO.setAssessKpi(Double.parseDouble(excel.convert(row.getCell(3))));//

                Double userClock = 0D;
                /*if(row.getCell(4) != null && !"0".equals(excel.convert(row.getCell(4)))){
                    userClock = Double.parseDouble(excel.convert(row.getCell(4)));
                }*/

                Double video = 0D;
                /*if(row.getCell(5) != null && !"0".equals(excel.convert(row.getCell(5)))){
                    video = Double.parseDouble(excel.convert(row.getCell(5)));
                }*/

                ddDataDTO.setOtherPay(userClock + video + Double.parseDouble(excel.convert(row.getCell(6)))); //其他补扣款

                String remark = "";
                if(row.getCell(7) != null && !"0".equals(excel.convert(row.getCell(7)))){
                    remark = excel.convert(row.getCell(7));
                }

                String remarkAll = "";
                /*if(userClock !=null && userClock != 0D){
                    remarkAll = remarkAll + "小程序打卡率绩效：" + userClock+ "元；" ;
                }
                if(video !=null && video != 0D){
                    remarkAll = remarkAll + "视频面访率绩效：" + video+ "元；" ;
                }*/
                remarkAll = remarkAll + remark;
                ddDataDTO.setRemarks(remarkAll);

                ddDataDTO.setOtherCutPay(Double.parseDouble(excel.convert(row.getCell(8)))); //其他补扣款

                ddDataDTO.setOtherCutRemarks(null);
                if(row.getCell(9) != null && !"0".equals(excel.convert(row.getCell(9)))){
                    ddDataDTO.setOtherCutRemarks(excel.convert(row.getCell(9)));
                }

                data.add(ddDataDTO);
            }
        }
        return data;
    }




    public static List<HoboProCase> importHoboProCase(InputStream inputStream){
        XSSFWorkbook workbook = null;
        Sheet sheet = null;
        Row row = null;
        List<HoboProCase> data = new ArrayList<>();
        StaffExcel excel = new StaffExcel();
        workbook = excel.readExcel(inputStream);
        if (workbook != null){
            sheet = workbook.getSheetAt(0);
            int lines = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < lines; i++) {
                HoboProCase hoboProCase = new HoboProCase();
                row = sheet.getRow(i);
                hoboProCase.setInsureName(excel.convert(row.getCell(0)));
                hoboProCase.setInsureDeptName(excel.convert(row.getCell(1)));
                hoboProCase.setName(excel.convert(row.getCell(2)));
                hoboProCase.setIdCard(excel.convert(row.getCell(3),"0"));
                hoboProCase.setTel(excel.convert(row.getCell(4),"0"));
                hoboProCase.setCarNo(excel.convert(row.getCell(5)));
                hoboProCase.setProName(excel.convert(row.getCell(6)));
                data.add(hoboProCase);
            }
        }
        return data;
    }
}
