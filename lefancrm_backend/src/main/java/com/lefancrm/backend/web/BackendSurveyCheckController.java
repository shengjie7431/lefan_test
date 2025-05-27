package com.lefancrm.backend.web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.BusUserRoleDto;
import com.lefancrm.backend.dto.SurveyZhaAssess;
import com.lefancrm.backend.dto.SurveyZhaOrgAssess;
import com.lefancrm.backend.util.ExcelUtil;
import com.lefancrm.backend.util.StaffExcel;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/survey/check/")
public class BackendSurveyCheckController extends BackendBaseController{
    @Value("${survey.setting.source}")
    private String settingSource;
    @Value("${survey.file.path.sftp}")
    private String fileHttp;


    @RequestMapping(value = "list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        model.put("pageSize",req.getParameter("pageSize"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyZhaAssess>>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.SURVEY_CHECK_LIST, null, req);
        model.put("apiRsp",apiFinalResponse);
        model.put("listName",req.getParameter("listName"));
        return new ModelAndView("/survey/check/list",model);
    }
    @RequestMapping(value = "info")
    public ModelAndView info(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyZhaAssess>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.SURVEY_CHECK_INFO, null, req);
        model.put("dto",apiFinalResponse.getResults());
        return new ModelAndView("/survey/check/info",model);
    }
    @RequestMapping(value = "operate")
    public String operate(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.SURVEY_CHECK_OPERATE, null, req, rsp);
    }

    /**
     * 创建清单
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "ajaxData")
    public String ajaxData(MultipartHttpServletRequest req, HttpServletResponse rsp){
        MultipartFile multipartFile = req.getFile("file");
        Map<String,Object> appendMap =  new HashMap<String,Object>();
        try {
            InputStream inputStream = multipartFile.getInputStream();
            //将文件上传至服务器
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//            File file = new File("/mnt/sftp/files/check/" + simpleDateFormat.format(new Date()) + "/" + System.currentTimeMillis() + "/" + multipartFile.getOriginalFilename());
//            if (!file.getParentFile().exists()){
//                file.getParentFile().mkdirs();
//                file.createNewFile();
//            }
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            OutputStream outputStream = new FileOutputStream(file);
//            byte[] buffer = new byte[1024];
//            int length  = 0 ;
//            while((length = inputStream.read(buffer))>0){
//                outputStream.write(buffer, 0, length);
//            }
//            outputStream.close();
//
//            String url = file.getPath();
//            if ("test".equals(settingSource) || "product".equals(settingSource)){//测试 或 线上
//                url = url.replace("/mnt/sftp/files/check/",fileHttp.concat("/check/"));
//            }
            List<SurveyZhaOrgAssess> zhaData = StaffExcel.generateZhaData(inputStream);
            appendMap.put("data", JSONArray.toJSON(zhaData));
//            appendMap.put("url",url);
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.callApiAndOutput(BackendApiMethodEnum.SURVEY_CHECK_AJAX_DATA, appendMap, req, rsp);
    }

}
