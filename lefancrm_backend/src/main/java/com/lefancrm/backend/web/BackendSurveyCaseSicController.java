package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.util.FileUtils;
import com.lefancrm.backend.util.FileZipUtil;
import com.lefancrm.base.utils.JsonUtil;
import com.lefancrm.backend.util.SFTPUtils;
import com.lefancrm.backend.util.WordUtil;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.Md5Util;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.Number;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.Boolean;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lixianfeng on 2018/12/24.
 */
@Controller
@RequestMapping(value = "/survey/case/sic/")
public class BackendSurveyCaseSicController extends BackendBaseController{
    @Value("${survey.report.path}")
    private String generateFilePath;
    @Value("${survey.setting.source}")
    private String surveySettingSource;
    @Value("${survey.direction.path}")
    private String directionFilePath;
    @Value("${survey.file.path.sftp}")
    private String surveyFilePathSftp;
    @Value("${survey.file.source.sftp}")
    private String surveyFilePathSftpSource;
    @Value("${survey.upload.ip}")
    private String surveyUploadIp;
    @Value("${survey.upload.pwd}")
    private String surveyUploadPwd;
    @Value("${nuanwa.company}")
    private String nuanWaCompany;
    @Value("${nuanwa.secret}")
    private String nuanWaSecret;

    @RequestMapping(value = "list")
    public ModelAndView list(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        Map<String,Object> appendMap =  new HashMap<String,Object>();
        model.put("pageSize",req.getParameter("pageSize"));
        model.put("surveyPerson",req.getParameter("surveyPerson"));
        model.put("surveyPhase",req.getParameter("surveyPhase"));
        String menuCode = req.getParameter("menuCode");
        model.put("menuCode",menuCode);
        model.put("creportState",req.getParameter("creportState"));//报告状态
        model.put("surveryPersonTel",req.getParameter("surveryPersonTel"));
        model.put("surveyNo",req.getParameter("surveyNo"));
        model.put("operateState",req.getParameter("operateState"));
        model.put("policyNo",req.getParameter("policyNo"));
        model.put("entrustOrgName",req.getParameter("entrustOrgName"));
        model.put("overTimeType",req.getParameter("overTimeType"));
        model.put("entrustOrgId",req.getParameter("entrustOrgId"));//委托机构Id
        model.put("surveyCaseNo",req.getParameter("surveyCaseNo"));//案件编号
        model.put("sortField",req.getParameter("sortField"));
        model.put("sortType",req.getParameter("sortType"));//快捷查询
        model.put("searchStr",req.getParameter("searchStr"));//快捷查询
        model.put("entrustOrgIds",req.getParameter("entrustOrgIds")==null?"":req.getParameter("entrustOrgIds"));
        model.put("serviceTypes",req.getParameter("serviceTypes")==null?"":req.getParameter("serviceTypes"));
        if ("task-user-review".equals(menuCode)){
            String reviewOff = req.getParameter("reviewOff");
            if (StringUtils.isEmpty(reviewOff)){
                reviewOff = "0";
                appendMap.put("reviewOff",reviewOff);
            }
            model.put("reviewOff",reviewOff);//预审状态
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorCaseDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_LIST_SIC, appendMap, req);
        model.put("apiRsp",apiFinalResponse);

        //所有的委托方机构
        appendMap = new HashMap<String, Object>();
        appendMap.put("menuType",1); //不分页
        appendMap.put("surveyCode","consignor");//查询“狄大人终审人员对应的委托方”，改变surveyCode值
        typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
        List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
        model.put("consignors",consignors);
        return new ModelAndView("/survey/case/sic/list",model);
    }

    @RequestMapping(value = "info")
    public ModelAndView info(HttpServletRequest req,HttpServletResponse rsp){
        String menuCode = req.getParameter("menuCode");
        Map model = new HashMap();
        Map appendMap = new HashMap();
        model.put("display",req.getParameter("display"));//是否显示打卡足迹按钮
        if ("feeViewSurvey".equals(menuCode)){//根据调查员ID + 案件ID 查询调查员案件ID
            model.put("menuCode",menuCode);
            model.put("feeOpr",req.getParameter("feeOpr"));//view  edit 是否可编辑方向
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyInvestigatorCaseDto>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO_SIC, appendMap, req);
        SurveyInvestigatorCaseDto dto = (SurveyInvestigatorCaseDto)apiFinalResponse.getResults();

        //获取同事的任务跟方向
        TypeToken typeTokens = new TypeToken<ApiFinalResponse<SurveyColleagueTaskDto>>(){};
        ApiFinalResponse apiFinalResponses= this.callApi(typeTokens, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO_SIC_COLLEAGUE, null, req);
        SurveyColleagueTaskDto surveyColleagueTaskDto=(SurveyColleagueTaskDto) apiFinalResponses.getResults();
        model.put("surveyColleagueTaskDto",surveyColleagueTaskDto);
        model.put("dto",dto);
        if (dto != null){
            List<SurveyCaseDirectionDto> directionDtos =  dto.getSurveyCaseDirections();
            if (directionDtos != null){
                for (SurveyCaseDirectionDto directionDto : directionDtos) {
//                    if (directionDto.getSurveyCaseDirectionFiles() != null) {
//                        directionDto.setDirectionFilesSize(directionDto.getSurveyCaseDirectionFiles().size());
//                    }else{
//                        String folder = "/mnt/sftp/files/" + surveyFilePathSftpSource + "/ddr/cno/" +  dto.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase() + "/" + "direction" + "/" + directionDto.getDirectionName();
//                        File file = new File(folder);
//                        if (file.exists()){
//                            if (file.isDirectory()){
//                                directionDto.setDirectionFilesSize(file.listFiles().length);
//                            }
//                        }
//                    }
                    try {
                        //新的方向附件 没有 任务类型文件夹
                        String folder = "/mnt/sftp/files/" + surveyFilePathSftpSource + "/ddr/cno/" +  dto.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase() + "/" + "direction" + "/" + directionDto.getDirectionName();
                        int size = 0;
                        File file = new File(folder);
                        if (!file.exists()){//如果不存在。则进入任务类型文件夹
                            folder = "/mnt/sftp/files/" + surveyFilePathSftpSource + "/ddr/cno/" +  dto.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase() + "/" + "direction" + "/" + directionDto.getTaskName() + "/" + directionDto.getDirectionName();
                            file = new File(folder);
                        }
                        if (file.exists()){
                            if (file.isDirectory()){
                                size = file.listFiles().length;
                            }
                        }
                        directionDto.setDirectionFilesSize(size);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            //调查处理详情页显示查看按钮
            if (dto.getShowExpenseReimbursementValue()){
                String reStateStr = req.getParameter("reStateStr");
                if (StringUtils.isNotBlank(reStateStr) && !"待提交发票".equals(reStateStr)){
                    dto.setReStateStr(reStateStr);
                }
            }
        }

        model.put("menuCode",menuCode);

        typeToken = new TypeToken<ApiFinalResponse<List<SurveyFollowDto>>>(){};
        Map param = new HashMap();
        param.put("surveyInfoId",dto.getSurveyInfoId());
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST_FOLLOW, param, req);
        model.put("follows",apiFinalResponse.getResults());

        //案件指导信息
        typeToken = new TypeToken<ApiFinalResponse<SurveyRiskCaseGuideDto>>(){};
        param = new HashMap();
        param.put("id",dto.getSurveyInfoId());
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_RISK_CASE_GUIDE_INFO, param, null);
        SurveyRiskCaseGuideDto guideDto = (SurveyRiskCaseGuideDto)apiFinalResponse.getResults();
        model.put("guideDto",guideDto);

        if ("report-list".equals(menuCode) || "report-review-list".equals(menuCode)){
            return new ModelAndView("/survey/case/sic/reportOpr",model);
        }
        if ("cash-info".equals(menuCode)){ //提现中的详情
            return new ModelAndView("/survey/surveyCashInfo/caseInfo",model);
        }
        model.put("showNwPageEdit",true);
        if ("dcy-list".equals(menuCode)){
            model.put("again",req.getParameter("again"));//添加方向时候，保存并开始下一个方向，此功能，为了弹窗“输入方向”
            model.put("surveyInfoId",dto.getSurveyInfoId());

            if (dto.getEntrustOrgId() == 94){
                Long user = getSessionAdminId(req);
                String sign = Md5Util.encodeString(nuanWaCompany + user + nuanWaSecret);
                model.put("nuanWaUser",user);
                model.put("nuanWaSign",sign);
                model.put("showNwPage",true);
                model.put("nuanWaCompany",nuanWaCompany);
                if (dto.getRiskHandleId() != null){
                    model.put("showNwPageEdit",false);
                    model.put("handleId",dto.getRiskHandleId());
                }
            }else {
                model.put("showNwPage",false);
            }

            return new ModelAndView("/survey/case/sic/info3",model);
        }else if ("feeViewSurvey".equals(menuCode)){
            model.put("surveyInfoId",dto.getSurveyInfoId());
            return new ModelAndView("/survey/case/sic/info3",model);
        }
        return new ModelAndView("/survey/case/sic/info",model);
    }

    @RequestMapping(value = "operate")
    public String operate(HttpServletRequest req,HttpServletResponse rsp) {
        String btnCode = req.getParameter("btnCode");
        if ("accept".equals(btnCode)) {
            Map<String, Object> param = new HashMap<>();
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_ACCEPT_SIC, param, req, rsp);
        } else if ("refuse".equals(btnCode)) {
            Map<String, Object> param = new HashMap<>();
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_REFUSE_SIC, param, req, rsp);
        } else if ("direction".equals(btnCode)) {
            Map<String, Object> param = new HashMap<>();
            //
            String surveyCno = req.getParameter("surveyCno").toLowerCase();
            String uploadDirectionName = req.getParameter("uploadDirectionName");
            String directionName = req.getParameter("directionName");
            String uploadFolder = directionFilePath + surveyCno + File.separator + "direction" + File.separator + uploadDirectionName;
            String newFolder = directionFilePath + surveyCno + File.separator + "direction" + File.separator + directionName;
            try {
                if (!uploadFolder.equals(newFolder)) {
                    FileUtils.copyFolder(uploadFolder, newFolder);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_DIRECTION_SIC, param, req, rsp);
        } else if ("upload".equals(btnCode)) {
            Map<String, Object> param = new HashMap<>();
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_UPLOAD_REPORT_SIC, param, req, rsp);
        } else if ("primary".equals(btnCode)) {
            Map<String, Object> param = new HashMap<>();
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_UPLOAD_REPORT_SIC, param, req, rsp);
        }else if("updPricedirection".equals(btnCode)){
            String updType = req.getParameter("updType");//方向处理
            if ("7".equals(updType)) {//标记无效方向时，同时删除方向附件
                // 2020年12月29日需求：不能删除，否则再次标记为正常方向，附件不存在了
                /*TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyCaseDirectionDto>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_DIRECTION_INFO, null, req);
                SurveyCaseDirectionDto surveyCaseDirectionDto = (SurveyCaseDirectionDto) apiFinalResponse.getResults();
                if (surveyCaseDirectionDto != null) {
                    //新的方向附件 没有 任务类型文件夹
                    String folder = "/mnt/sftp/files/" + surveyFilePathSftpSource + "/ddr/cno/" + surveyCaseDirectionDto.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase() + "/" + "direction" + "/" + surveyCaseDirectionDto.getDirectionName();
                    File file = new File(folder);
                    if (!file.exists()) {//如果不存在。则进入任务类型文件夹
                        folder = "/mnt/sftp/files/" + surveyFilePathSftpSource + "/ddr/cno/" + surveyCaseDirectionDto.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase() + "/" + "direction" + "/" + surveyCaseDirectionDto.getTaskName() + "/" + surveyCaseDirectionDto.getDirectionName();
                    }
                    FileUtils.deleteDir(folder);
                }*/
            }
            Map<String, Object> param = new HashMap<>();
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_OPERATE_SIC, param, req, rsp);
        } else if ("orgPrimary".equals(btnCode)) {
            Map<String, Object> param = new HashMap<>();
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_UPLOAD_REPORT_SIC, param, req, rsp);
        } else if ("commit".equals(btnCode) || "org-commit".equals(btnCode)) {
            Map<String, Object> param = new HashMap<>();
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_COMMIT_SIC, param, req, rsp);
        } else if ("commit-to-org".equals(btnCode)) {
            Map<String, Object> param = new HashMap<>();
            //生成调查报告
            String typeCode = req.getParameter("typeCode");
            if ("last-org-primary".equals(typeCode)) {//主机构提交复核
                TypeToken typeToken = new TypeToken<ApiFinalResponse<TemplateData>>() {
                };
                ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_GET_TEMPLATE_DATA, null, req);
                TemplateData data = (TemplateData) apiFinalResponse.getResults();
                SurveyModelInfoDto model = data.getModel();
                String modelPath = null;
                if ("dev".equals(surveySettingSource)) {//如果是本低环境 则模板路径 不取数据库配置路径
                    modelPath = "F:\\templete";
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
            }
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_COMMIT_SIC, param, req, rsp);
        }
        else if ("deleteFile".equals(btnCode))
        {
            //删除服务器上的文件
            String filePath = req.getParameter("filePath");
            if (!StringUtils.isEmpty(filePath)) {
//                https://ddrapi.shlefan.com/sftp/files
//                http://119.3.48.62:6077/sftp/files/
                filePath = filePath.replace(surveyFilePathSftp, "/mnt/sftp/files/");
                FileUtils.deleteDir(filePath);
            }
            Map<String, Object> param = new HashMap<>();
            //删除数据库数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_DIRECTION_FILE_DELETE, param, req, rsp);
        }
        else if("updFileName".equals(btnCode))
        {
            //修改服务器上的文件名称
            String filePath = req.getParameter("filePath");
            if (!StringUtils.isEmpty(filePath)) {
                filePath = filePath.replace(surveyFilePathSftp, "/mnt/sftp/files/");
                File file = new File(filePath);
                File tempFile = null;
                if (file.isDirectory()) {
                    String newFile = file.getParent();
                    tempFile = new File(newFile + File.separator + req.getParameter("fileName"));
                }else{
                    String fileName = file.getName();
                    String newFile = file.getParent();
                    tempFile = new File(newFile + File.separator + req.getParameter("fileName") + "." + fileName.substring(fileName.lastIndexOf(".") + 1));
                }
                file.renameTo(tempFile);
            }
            Map<String, Object> param = new HashMap<>();
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_DIRECTION_FILE_DELETE, param, req, rsp);
        }
        else if ("folder-upload".equals(btnCode))
        {
            String filePath = req.getParameter("filePath");
            String type = req.getParameter("type");

            if (!StringUtils.isEmpty(filePath)) {
                filePath = filePath.replace(surveyFilePathSftp, "/mnt/sftp/files/");
                File file = new File(filePath);
                File tempFile = null;
                if (file.isDirectory()) {
                    String fileName = file.getName();
                    if ("upload".equals(type)){
                        fileName = fileName + "-不下载";
                    }else{
                        fileName = fileName.replace("-不下载","");
                    }
                    String newFile = file.getParent();
                    tempFile = new File(newFile + File.separator + fileName);
                }
                file.renameTo(tempFile);
            }
            Map<String, Object> param = new HashMap<>();
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_DIRECTION_FILE_DELETE, param, req, rsp);
        }
        else if ("reloadReport".equals(btnCode)) {
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("surveyInfoId", req.getParameter("surveyInfoId"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<TemplateData>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_GET_TEMPLATE_DATA, paramMap, req);
            TemplateData data = (TemplateData) apiFinalResponse.getResults();
            SurveyModelInfoDto model = data.getModel();
            String modelPath = null;
            if ("dev".equals(surveySettingSource)) {//如果是本低环境 则模板路径 不取数据库配置路径
                modelPath = "F:\\templete";
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
                //如果是本地环境 则将本地文件上传至47服务器
                if ("dev".equals(surveySettingSource)) {
                    File file = new File(generateReportPath);
                    FileInputStream inputStream = new FileInputStream(file);
                    String reportPath = "/mnt/sftp/files/test/ddr/cno/" + data.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase() + "/report";
                    if (SFTPUtils.upload(surveyUploadIp,surveyUploadPwd, reportPath, file.getName(), inputStream)) {
                        generateReportPath = (reportPath + "/" + file.getName()).replaceAll("/mnt/sftp/files", surveyFilePathSftp);
                    }
                }
                jsonMap.put("generateReportPath", generateReportPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String json = JsonUtil.objectToJson(jsonMap);
            return this.outputJson(json, rsp);
        } else if ("extension-time".equals(btnCode)) {
            Map<String, Object> param = new HashMap<>();
            String roleCode = req.getParameter("roleCode");
            if("orgManager".equals(roleCode)){
                TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyAssignOrgExtensionDto>>(){};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_ASSIGN_ORG_OPERATE, param, req);
                SurveyAssignOrgExtensionDto dto = (SurveyAssignOrgExtensionDto)apiFinalResponse.getResults();

                String extensionFileId = req.getParameter("extensionFileId");
                String surveyCno = req.getParameter("surveyCno").toLowerCase();
                String id = req.getParameter("id");
                String uploadFolder = directionFilePath + surveyCno + File.separator + "extensionTime" + File.separator + id + File.separator  + extensionFileId;
                String newFolder = directionFilePath + surveyCno + File.separator + "extensionTime" + File.separator  + id + File.separator  + dto.getId();
                try {
                    if (!uploadFolder.equals(newFolder)) {
                        FileUtils.copyFolder(uploadFolder, newFolder);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                param = new HashMap<>();
                param.put("btnCode",null);
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_ASSIGN_ORG_OPERATE, param, req, rsp);
            }else if("lfManager".equals(roleCode) || "lfManager-recall".equals(roleCode) || "lfManager-back".equals(roleCode)){
                param = new HashMap<>();
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_ASSIGN_ORG_OPERATE, param, req, rsp);
            }
        }else if ("otherReply".equals(btnCode)) {
            Map<String, Object> param = new HashMap<>();
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_REMIND_OPREATE, param, req, rsp);
        } else if ("deldirection".equals(btnCode)) {//同时删除方向附件
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyCaseDirectionDto>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_DIRECTION_INFO, null, req);
            SurveyCaseDirectionDto surveyCaseDirectionDto = (SurveyCaseDirectionDto) apiFinalResponse.getResults();
            if (surveyCaseDirectionDto != null) {
                //新的方向附件 没有 任务类型文件夹
                String folder = "/mnt/sftp/files/" + surveyFilePathSftpSource + "/ddr/cno/" + surveyCaseDirectionDto.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase() + "/" + "direction" + "/" + surveyCaseDirectionDto.getDirectionName();
                File file = new File(folder);
                if (!file.exists()) {//如果不存在。则进入任务类型文件夹
                    folder = "/mnt/sftp/files/" + surveyFilePathSftpSource + "/ddr/cno/" + surveyCaseDirectionDto.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase() + "/" + "direction" + "/" + surveyCaseDirectionDto.getTaskName() + "/" + surveyCaseDirectionDto.getDirectionName();
                }
                FileUtils.deleteDir(folder);
            }
        } else if ("deleteReimFile".equals(btnCode)) {
            Map<String, Object> param = new HashMap<>();
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_DIRECTION_REMIBURSE_FILE_DELETE, param, req, rsp);
        }else if ("repetition".equals(btnCode)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_REPETITION, null, req, rsp);
        }
        Map<String, Object> param = new HashMap<>();
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_OPERATE_SIC, param, req, rsp);
    }


    @RequestMapping(value = "back")
    public ModelAndView back(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        model.put("id",req.getParameter("id"));
        model.put("btnCode",req.getParameter("btnCode"));
        model.put("signType",req.getParameter("signType"));
        return new ModelAndView("/survey/case/sic/back",model);
    }

    @RequestMapping(value = "operateView")
    public ModelAndView operateView(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        String btnCode = req.getParameter("btnCode");
        model.put("id",req.getParameter("id"));
        model.put("btnCode",btnCode);
        String menuCode = req.getParameter("menuCode");
        model.put("menuCode",menuCode);
        if ("direction".equals(btnCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyInvestigatorCaseDto>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO_SIC, null, req);
            SurveyInvestigatorCaseDto dto = (SurveyInvestigatorCaseDto)apiFinalResponse.getResults();
            model.put("dto",dto);

            //获取案件任务类型的第一个
            if(dto != null){
                if(dto.getTasks()!=null && dto.getTasks().size()>0){
                    model.put("fristTaskId",dto.getTasks().get(0).getTaskId());
                    model.put("fristTaskName",dto.getTasks().get(0).getTaskName());
                }
            }

            //根据方向ID获取方向
            typeToken = new TypeToken<ApiFinalResponse<SurveyCaseDirectionDto>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_DIRECTION_INFO, null, req);
            SurveyCaseDirectionDto surveyCaseDirectionDto = (SurveyCaseDirectionDto)apiFinalResponse.getResults();
            if (surveyCaseDirectionDto != null){
                if (surveyCaseDirectionDto.getId() == null){
                    surveyCaseDirectionDto.setHuzhuDate(new Date());
                }
                if (surveyCaseDirectionDto.getMedicalNumber() == null || surveyCaseDirectionDto.getMedicalNumber() == 0){
                    surveyCaseDirectionDto.setMedicalNumber(1);
                }
            }
            model.put("direction",surveyCaseDirectionDto);
            model.put("directionId",req.getParameter("directionId"));

            Map<String, Object> appendMap1 = new HashMap<String, Object>();
            appendMap1.put("surveyCode","commonArea");
            typeToken = new TypeToken<ApiFinalResponse<List<CommonAreaDto2>>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap1, req);
            List<CommonAreaDto2> commonAreaDtos = (List<CommonAreaDto2>)apiFinalResponse.getResults();
            model.put("commonAreaDtos",commonAreaDtos);

            //查询该调查员的方向 “地址信息”历史记录
            Map<String, Object> appendMapHis = new HashMap<String, Object>();
            appendMapHis.put("surveyCode","directionAreaHistory");
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyDirectionAreaHistoryDto>>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMapHis, req);
            List<SurveyDirectionAreaHistoryDto> historyDtos = (List<SurveyDirectionAreaHistoryDto>)apiFinalResponse.getResults();
            model.put("directionAreaHistory",historyDtos);

            if("1".equals(req.getParameter("again"))){
                if(historyDtos!=null && historyDtos.size()>0){
                    model.put("firstHistory",historyDtos.get(0));
                }
            }

            //默认方向名称，原因：录入方向时，附件找到对应路径
            UserInfo userInfo = this.getSessionAdmin(req); //当前登录人
            String uploadDirectionName = new Long(System.currentTimeMillis()).toString().concat(userInfo.getUserId().toString());
            model.put("directionName",uploadDirectionName);

            SurveyConsignorDto surveyConsignor = dto.getSurveyRiskCaseInfo().getSurveyConsignor();
            //互助案件 直营机构显示费用报销
            boolean showExpenseReimbursement = surveyConsignor.getOrgAttr() == 2 && dto.getSurveyFranchisee().getType() == 1;
            //保司案件 直营机构显示费用报销
            boolean showBaoSi = surveyConsignor.getOrgAttr() == 1 && dto.getSurveyFranchisee().getInsuranceType() == 1;
            model.put("showExpenseReimbursement", showExpenseReimbursement);
            model.put("showBaoSi", showBaoSi);
            if (showExpenseReimbursement || showBaoSi){
//                typeToken = new TypeToken<ApiFinalResponse<SurveyReimbursementInfoDto>>(){};
//                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_REIMBURSEMENT_INFO, null, req);
//                SurveyReimbursementInfoDto surveyReimbursementInfoDto = (SurveyReimbursementInfoDto) apiFinalResponse.getResults();
//                model.put("surveyReimbursementInfo",surveyReimbursementInfoDto);
            }
            if (surveyConsignor.getOrgAttr() == 2){
                //新增方向
                if (surveyCaseDirectionDto.getId() == null){
                    surveyCaseDirectionDto.setDirectionName(uploadDirectionName);
                    surveyCaseDirectionDto.setSun(0);
                }
                if ("1".equals(req.getParameter("again"))){//获取历史地址信息
                    if(historyDtos != null && historyDtos.size() > 0) {
                        SurveyDirectionAreaHistoryDto history = historyDtos.get(0);
                        surveyCaseDirectionDto.setProvince(history.getProvince());
                        surveyCaseDirectionDto.setCity(history.getCity());
                        surveyCaseDirectionDto.setDistrict(history.getDistrict());
                        surveyCaseDirectionDto.setProvinceId(history.getProvinceId());
                        surveyCaseDirectionDto.setCityId(history.getCityId());
                        surveyCaseDirectionDto.setDistrictId(history.getDistrictId());
                        surveyCaseDirectionDto.setAreaType(history.getAreaType());
                        surveyCaseDirectionDto.setRegionType(history.getRegionType());
                    }
                }
                if (surveyCaseDirectionDto.getSun() == 1) {
                    surveyCaseDirectionDto.setHuzhuSunStr("是");
                }else{
                    surveyCaseDirectionDto.setHuzhuSunStr("否");
                }
                model.put("direction",surveyCaseDirectionDto);
            }

            String opr = req.getParameter("opr");
            if(opr!=null && "upd".equals(opr)){
                //获取任务子类
                Map<String, Object> appendMap = new HashMap<String, Object>();
                appendMap.put("surveyCode","taskInfo");
                appendMap.put("taskInfoId", surveyCaseDirectionDto.getTaskId());
                appendMap.put("menuType",1);//不分页
                appendMap.put("btnCode",1000);
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoContentDto>>>(){};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req);
                List<SurveyTaskInfoContentDto> surveyTaskInfoContentDto = (List<SurveyTaskInfoContentDto>)apiFinalResponse.getResults();
                model.put("taskInfoContents",surveyTaskInfoContentDto);
                if (surveyConsignor.getOrgAttr() == 2){//互助
                    model.put("orgReview",req.getParameter("orgReview"));//平台复查info页面，修改方向，不重定向
                    model.put("feeOpr",req.getParameter("feeOpr"));
                    return new ModelAndView("/survey/case/sic/directionHelp",model);
                }
                return new ModelAndView("/survey/case/sic/directionUpd",model);
            }else{
                model.put("orgReview",req.getParameter("orgReview"));//报告复核info页，增加方向，不重定向
                if (surveyConsignor.getOrgAttr() == 2){//互助
                    return new ModelAndView("/survey/case/sic/directionHelp",model);
                }
                return new ModelAndView("/survey/case/sic/directionView",model);
            }
        }else if ("primary".equals(btnCode) || "upload".equals(btnCode) || "orgPrimary".equals(btnCode)){
            model.put("surveyCno",req.getParameter("surveyCno"));
            model.put("tsId",req.getParameter("tsId"));//特殊的ID 从子案件info界面上传报告 则是子案件ID
        }else if ("updAssign".equals(btnCode)){
            //机构列表
            Map param = new HashMap();
            param.put("surveyCode","franchisee");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, param, req);
            model.put("surveyfranchisees",apiFinalResponse.getResults());
        }else if ("last-org-report-completion".equals(btnCode) || "last-org-summary".equals(btnCode)
                || "tasks".equals(btnCode)
                || "last-org-report-completion-view".equals(btnCode) || "last-org-summary-view".equals(btnCode)){
            String surveyInfoId = req.getParameter("surveyInfoId");
            Map param = new HashMap();
            param.put("surveyInfoId",surveyInfoId);
            param.put("surveyInvestigatorCaseId",req.getParameter("surveyInvestigatorCaseId"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyShowInfoDTO>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_TASKS_ASSIGN_ORG, param, req);
            SurveyShowInfoDTO showInfo = (SurveyShowInfoDTO) apiFinalResponse.getResults();
            if ("tasks".equals(btnCode)){
                showInfo.setType(4);
            }
            model.put("showInfo",showInfo);
            model.put("surveyInfoId",surveyInfoId);
            model.put("nullCode",req.getParameter("nullCode"));
            model.put("view",req.getParameter("view"));
            if ("last-org-report-completion".equals(btnCode)){
                //基础信息
                Map<String,Object> appendMap =  new HashMap<String,Object>();
                appendMap.put("id",surveyInfoId);
                typeToken = new TypeToken<ApiFinalResponse<SurveyRiskCaseInfoDto>>(){};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO, appendMap, req);
                SurveyRiskCaseInfoDto dto = (SurveyRiskCaseInfoDto)apiFinalResponse.getResults();
                model.put("dto",dto);
                //生成报告
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("surveyInfoId",surveyInfoId);
                typeToken = new TypeToken<ApiFinalResponse<TemplateData>>(){};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_GET_TEMPLATE_DATA, paramMap, req);
                TemplateData data = (TemplateData)apiFinalResponse.getResults();
                SurveyModelInfoDto modelInfo = data.getModel();
                String modelPath = null;
                if ("dev".equals(surveySettingSource)){//如果是本低环境 则模板路径 不取数据库配置路径
                    modelPath = "F:\\templete";
                }else{
                    modelPath = modelInfo.getModelPath();
                }
                try {
                    String generateReportPath = WordUtil.generateReport(data, modelInfo.getId().intValue(), modelInfo.getModelName(), generateFilePath.concat("/").concat(data.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase()).concat("/report"), data.getReportName(),modelPath);
                    if ("/mnt/sftp/files/product/ddr/cno/".equals(generateFilePath) || "/mnt/sftp/files/test/ddr/cno/".equals(generateFilePath)){
                        if ("/mnt/sftp/files/product/ddr/cno/".equals(generateFilePath)){
                            generateReportPath = generateReportPath.replace("/mnt/sftp/files/product/ddr/cno/",surveyFilePathSftp.concat("/product/ddr/cno/"));
                        }else if ("/mnt/sftp/files/test/ddr/cno/".equals(generateFilePath)){
                            generateReportPath = generateReportPath.replace("/mnt/sftp/files/test/ddr/cno/",surveyFilePathSftp.concat("/test/ddr/cno/"));
                        }
                    }
                    model.put("generateReportPath",generateReportPath);

                    //如果是本地环境 则将本地文件上传至47服务器
                    if ("dev".equals(surveySettingSource)){
                        File file = new File(generateReportPath);
                        FileInputStream inputStream = new FileInputStream(file);
                        String reportPath = "/mnt/sftp/files/test/ddr/cno/" + data.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase() + "/report";
                        if(SFTPUtils.upload(surveyUploadIp,surveyUploadPwd,reportPath,file.getName(),inputStream)){
                            generateReportPath = (reportPath + "/" + file.getName()).replaceAll("/mnt/sftp/files",surveyFilePathSftp);
                        }
                    }
                    model.put("httpReportPath",generateReportPath + "?v=" + new Date().getTime());
                }catch (Exception e){
                    e.printStackTrace();
                }
                return new ModelAndView("/survey/case/sic/tableReport",model);
            }
            return new ModelAndView("/survey/case/sic/table",model);
        }else if ("last-org-report-completion-new".equals(btnCode)){

        }else if("directionFileMid".equals(btnCode)){
//            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyInvestigatorCaseDto>>(){};
//            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO_SIC, null, req);
//            SurveyInvestigatorCaseDto dto = (SurveyInvestigatorCaseDto)apiFinalResponse.getResults();
//            model.put("dto",dto);
            //根据方向ID获取方向
//            typeToken = new TypeToken<ApiFinalResponse<SurveyCaseDirectionDto>>(){};
//            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_DIRECTION_INFO, null, req);
//            SurveyCaseDirectionDto surveyCaseDirectionDto = (SurveyCaseDirectionDto)apiFinalResponse.getResults();
//            model.put("direction",surveyCaseDirectionDto);
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyInvestigatorCaseDto>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO_SIC, null, req);
            SurveyInvestigatorCaseDto dto = (SurveyInvestigatorCaseDto)apiFinalResponse.getResults();
//            if (dto !=null &&dto.getSurveyState() == 4){
//                model.put("directionFileMidFlag",Boolean.FALSE);
//                if (dto.getSurveyAssignOrg() != null && dto.getSurveyAssignOrg().getReportDate() == null) {
//                    model.put("directionFileMidFlag",Boolean.TRUE);
//                }
//            }else {
//                model.put("directionFileMidFlag",Boolean.TRUE);
//            }
            model.put("directionFileMidFlag",Boolean.TRUE);
            model.put("directionId",req.getParameter("directionId"));
            model.put("surveyId",req.getParameter("surveyId"));
            model.put("surveyInfoId",req.getParameter("surveyInfoId"));
            model.put("surveyCno",req.getParameter("surveyCno"));
            if (dto!= null && dto.getEntrustOrgId() != null){
                model.put("entrustOrgId",dto.getEntrustOrgId());
            }
            return new ModelAndView("/survey/case/sic/directionFileMidIframe",model);
        }else if ("sign".equals(btnCode)){
            model.put("oprType",req.getParameter("oprType"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyInvestigatorCaseDto>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO_SIC, null, req);
            SurveyInvestigatorCaseDto dto = (SurveyInvestigatorCaseDto)apiFinalResponse.getResults();
            model.put("dto",dto);
            return new ModelAndView("/survey/case/sic/sign",model);
        }else if("118".equals(btnCode)){
            model.put("surveyInfoId",req.getParameter("surveyInfoId"));
        }else if("case-return".equals(btnCode)){
            model.put("roleCode",req.getParameter("roleCode"));
            model.put("assignOrgId",req.getParameter("assignOrgId"));
        }else if("primary-veto".equals(btnCode)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyPrimaryVetoDTO>>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_PRIMARY_VETO_GET_DATA, null, req);
            List<SurveyPrimaryVetoDTO> vetos = (List<SurveyPrimaryVetoDTO>)apiFinalResponse.getResults();
            model.put("vetos",vetos);
        }else if("extension-time".equals(btnCode)){
            //id为机构案件id（assignOrgId）
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyAssignOrgDto>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_ASSIGN_ORG_INFO, null, req);
            SurveyAssignOrgDto assignOrg = (SurveyAssignOrgDto)apiFinalResponse.getResults();
            model.put("assignOrg",assignOrg);
            //延期申请记录
            if(assignOrg!=null){
                model.put("extension",assignOrg.getExtension());
                //是否发送邮件，以及邮件内容：emailInfo
                model.put("sendEmail",assignOrg.getExtension().getSendEmail());
//                model.put("surveyEmailInfo",assignOrg.getExtension().getSurveyEmailInfo());

                //用于判断 上传文件 的extensionId
                model.put("extensionFileId",new Long(System.currentTimeMillis()).toString());
                if(assignOrg.getExtension()!=null && assignOrg.getExtension().getId()!=null){
                    model.put("extensionFileId",assignOrg.getExtension().getId());
                }
            }

            String roleCode = req.getParameter("roleCode");
            model.put("roleCode",roleCode);//操作人角色
            model.put("operateType",req.getParameter("operateType"));
            model.put("type",req.getParameter("type"));//区分“申请延期-first”，“延期申请中-second”
            String replyId = req.getParameter("replyId");
            if (StringUtils.isBlank(replyId)){
                model.put("view",true);
            }else {
                model.put("view",false);
            }

            //审核通过之后，如满足发邮件的条件，会弹窗发邮件
            String sendEmail = req.getParameter("sendEmail");
            if("true".equals(sendEmail) && "lfManager".equals(roleCode)){
                model.put("btnCode","extension");
                model.put("emailInfo",assignOrg.getExtension().getSurveyEmailInfo());

                /*List<CommonFile> files = assignOrg.getExtension().getCommonFiles();
                model.put("files", files);
                if(files==null){
                    model.put("filesJSON", null);
                }else{
                    model.put("filesJSON", JsonUtil.objectToJson(files));
                }*/

                String surveyCno = assignOrg.getSurveyRiskCaseInfoDto().getSurveyCno().toLowerCase();
                String fileFolder = directionFilePath + surveyCno + File.separator + "extensionTime" + File.separator  + assignOrg.getId() + File.separator  + assignOrg.getExtension().getId();

                String zipPath = fileFolder + ".zip";
                File file = new File(fileFolder);
                if (!file.exists()){//如果不存在
                    file.mkdirs();
                }
                FileZipUtil.createZip(fileFolder, zipPath);
                model.put("filesZip", zipPath);

                List<CommonFile> files = assignOrg.getExtension().getCommonFiles();
                model.put("files", files);

                return new ModelAndView("/survey/case/operateView",model);
            }

            return new ModelAndView("/survey/case/sic/extensionView",model);
        }else if("caseClockDetails".equals(btnCode)){
            String id=req.getParameter("id");
            model.put("id",id);
            return new ModelAndView("/survey/clock/caseClockDetails",model);
        }else if ("otherReply".equals(btnCode)){
            model.put("roleCode",req.getParameter("roleCode"));//操作人角色
            model.put("replyOrgCaseId",req.getParameter("id"));
            model.put("type",req.getParameter("type"));
            model.put("replyId",req.getParameter("replyId"));
            model.put("operateType",req.getParameter("operateType"));
            //id为机构案件id（assignOrgId）
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyAssignOrgDto>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_ASSIGN_ORG_INFO, null, req);
            SurveyAssignOrgDto assignOrg = (SurveyAssignOrgDto)apiFinalResponse.getResults();
            model.put("assignOrg",assignOrg);
            //延期申请记录
            if(assignOrg!=null){
                model.put("extension",assignOrg.getExtension());
            }
        }
        return new ModelAndView("/survey/case/sic/operateView",model);
    }

    @RequestMapping(value = "directionFiles")
    public ModelAndView directionFiles(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        String isdelete = req.getParameter("isdelete");
        model.put("isdelete",true);
        String type = req.getParameter("type");
        model.put("type",type);
        String btnCode = req.getParameter("btnCode");
        model.put("directionId",req.getParameter("directionId"));
        model.put("btnCode",btnCode);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyCaseDirectionDto>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_DIRECTION_INFO, null, req);
        SurveyCaseDirectionDto surveyCaseDirectionDto = (SurveyCaseDirectionDto)apiFinalResponse.getResults();
        model.put("direction",surveyCaseDirectionDto);
        model.put("surveyCno",req.getParameter("surveyCno"));
        if (surveyCaseDirectionDto != null){
            model.put("taskName",surveyCaseDirectionDto.getTaskName());
        }
        if (StringUtils.isNotBlank(type)){
            String dataId = req.getParameter("dataId");
            model.put("dataId",dataId);
            List<SurveyReimbursementFileDto> surveyReimbursementFileDtoList = surveyCaseDirectionDto.getSurveyReimbursementFileDtoList();
            List<SurveyReimbursementFileDto> dtos = new ArrayList<>();
            if (surveyReimbursementFileDtoList!=null && surveyReimbursementFileDtoList.size()>0){
                for (int i = 0; i < surveyReimbursementFileDtoList.size(); i++) {
                    if (StringUtils.isNotBlank(type) && "all".equals(type)){
                        dtos.add(surveyReimbursementFileDtoList.get(i));
                    }
                    if (StringUtils.isNotBlank(type)&&type.equals(surveyReimbursementFileDtoList.get(i).getFileCode())){
                        dtos.add(surveyReimbursementFileDtoList.get(i));
                    }
                }
            }
            surveyCaseDirectionDto.setSurveyReimbursementFileDtoList(dtos);
            return new ModelAndView("/survey/case/sic/directionReimbursementFiles",model);
        }
        return new ModelAndView("/survey/case/sic/directionFiles",model);
    }

    @RequestMapping(value = "details")
    public ModelAndView details(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyInvestigatorCaseDto>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO_SIC, null, req);
        SurveyInvestigatorCaseDto dto = (SurveyInvestigatorCaseDto)apiFinalResponse.getResults();
        model.put("dto",dto);
        model.put("menuCode",req.getParameter("menuCode"));
        return new ModelAndView("/survey/case/sic/details",model);
    }

    @RequestMapping(value = "sourceScore")
    public ModelAndView sourceScore(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveySourceScoreDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELF_SCORE, null, req);
        model.put("sourceList",apiFinalResponse.getResults());
        model.put("searchStartDate",req.getParameter("startDate"));
        model.put("searchEndDate",req.getParameter("endDate"));
        model.put("searchMonth",req.getParameter("searchMonth"));
        return new ModelAndView("/survey/case/sic/sourceScoreList",model);
    }

    @RequestMapping(value = "sourceScoreDetail")
    public ModelAndView sourceScoreDetail(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveySourceScoreDetailDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELF_SCORE_DETAIL, null, req);
        model.put("sourceListMx",apiFinalResponse.getResults());
        return new ModelAndView("/survey/case/sic/sourceScoreListMx",model);
    }

    @RequestMapping(value = "exportSourceScore")
    public String exportSourceScore(HttpServletRequest req,HttpServletResponse rsp){
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        paramMap.put("export","export");//表示导出调用API
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveySourceScoreDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELF_SCORE, paramMap, req);

        // 创建excel
        List<SurveySourceScoreDto> sourceList = (List<SurveySourceScoreDto>) apiFinalResponse.getResults();
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename ="案源分值.xls";

        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            //设置字体、边框
            //表头
            WritableFont wf1 = new WritableFont(WritableFont.ARIAL,11,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
            WritableCellFormat wcf1 = new WritableCellFormat(wf1);
            wcf1.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
            //具体内容
            WritableFont wf = new WritableFont(WritableFont.TIMES);
            wf.setColour(Colour.BLACK);
            wf.setPointSize(12);
            wf = new WritableFont(WritableFont.TIMES);
            WritableCellFormat wcf2 = new WritableCellFormat(wf);
            wcf2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("案源分值", 0);
            sheet.setColumnView(0,25);// 将第一列的宽度设为30
            sheet.setColumnView(1,25);// 将第一列的宽度设为30
            // 设置表头
            sheet.addCell(new Label(0, 0, "案源机构",wcf1));
            sheet.addCell(new Label(1, 0, "分值",wcf1));

            if(sourceList!=null && !sourceList.isEmpty()){
                for(int i=0; i<sourceList.size(); i++){
                    sheet.addCell(new Label(0, i+1, sourceList.get(i).getName(),wcf2));
                    sheet.addCell(new Number(1, i+1, sourceList.get(i).getValue(),wcf2));

                    WritableSheet sheetTwo = book.createSheet(sourceList.get(i).getName(), i+1);
                    sheetTwo.setColumnView(0,25);// 将第一列的宽度设为30
                    sheetTwo.setColumnView(1,25);// 将第一列的宽度设为30
                    // 设置表头
                    sheetTwo.addCell(new Label(0,0,"调查员",wcf1));
                    sheetTwo.addCell(new Label(1,0,"分值",wcf1));
                    List<SurveySourceScoreDetailDto> detailsOne = sourceList.get(i).getSourceListMx();
                    if(detailsOne!=null && !detailsOne.isEmpty()){
                        for(int j=0; j< detailsOne.size(); j++){
                            sheetTwo.addCell(new Label(0,j+1, detailsOne.get(j).getUserName()==null?"":detailsOne.get(j).getUserName(),wcf2));
                            if(detailsOne.get(j).getScore() !=null){
                                sheetTwo.addCell(new Number(1, j+1, detailsOne.get(j).getScore(),wcf2));
                            }else{
                                sheetTwo.addCell(new Label(1, j+1, "",wcf2));
                            }
                        }
                    }
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
        return null;
    }

    // 复制文件
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try{
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1){
                outBuff.write(b, 0, len);
            }
                outBuff.flush();
        }finally{
            if (inBuff != null){
                inBuff.close();
            }
            if (outBuff != null){
                outBuff.close();}
            }
    }

}
