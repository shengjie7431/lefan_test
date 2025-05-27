package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.SurveyFileApi;
import com.lefancrm.apicenter.dao.SurveyAssignOrgMapper;
import com.lefancrm.apicenter.dao.SurveyRiskCaseInfoMapper;
import com.lefancrm.apicenter.dao.SurveyRiskCaseMapper;
import com.lefancrm.apicenter.dto.SurveyAssignOrgDto;
import com.lefancrm.apicenter.model.SurveyCaseDirection;
import com.lefancrm.apicenter.model.SurveyCaseDirectionFile;
import com.lefancrm.apicenter.model.SurveyRiskCase;
import com.lefancrm.apicenter.model.SurveyRiskCaseInfo;
import com.lefancrm.apicenter.util.FileUtil32;
import com.lefancrm.apicenter.util.FileZipUtil;
import com.lefancrm.apicenter.util.HttpClientUtils;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixianfeng on 2019/3/13.
 */
@Service
@ApiService(descript = "调查压缩下载文件API")
public class BackendSurveyFileApiImpl implements SurveyFileApi {
//    private String url = "http://ddrapi.shlefan.com/lefansurveyapicenter/apicenter/";
    @Value("${survey_remote_url}")
    private String url = "http://ddrapi.shlefan.com/lefansurveytestapicenter/apitest/doApiTest";
//    private String url = "http://localhost:8100/apitest/doApiTest";
    @Value("${survey.file.source.sftp}")
    private String confSurveySouce;

    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;
    @Autowired
    private SurveyRiskCaseMapper surveyRiskCaseMapper;
    @Autowired
    private SurveyAssignOrgMapper surveyAssignOrgMapper;
    @Autowired
    private BackendSurveyCaseFileApiImpl backendSurveyCaseFileApi;
    @Value("${survey.file.path.sftp}")
    private String filePath;

    @ApiMethod(needLogin = false,descript = "转换成zip文件",value = "survey-convert-temp-zip")
    @Override
    public ApiResponse convertTempZip(ApiRequest apiRequest) {
        if ("/test/".equals(confSurveySouce)){//测试环境
            return backendSurveyCaseFileApi.sftpZip(apiRequest);
        }

        try {
            String surveyCno = apiRequest.getString("surveyCno").toLowerCase();
            NameValuePair[] data = new NameValuePair[5];
            data[0] =new NameValuePair("apiKey","ca89e65c77be0d3f0d732cc3134edaf4");
            data[1] =new NameValuePair("apiSecret","32f48148e40fcf5586c269f65e6045b5");
            data[2] =new NameValuePair("functionCodeInput","sftp-temp-zip");
            data[3] =new NameValuePair("paramName","surveyCno");
            data[4] =new NameValuePair("paramValue",surveyCno);
            String res = HttpClientUtils.httpPost(url, data);
            System.out.println(res);
            Map map = JSONArray.parseObject(res);
            String code = map.get("code").toString();
            if ("0000".equals(code)){
                Map resultMap = (Map)map.get("results");
                String path = resultMap.get("path").toString();
                System.out.println(path);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,resultMap);
            }else if ("1901".equals(code)){
                return new ApiResponse(ApiMsgEnum.SURVEY_FILE_DIR);//无目录
            }else{
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false,descript = "转换成zip文件",value = "survey-convert-temp-zip-entrust")
    @Override
    public ApiResponse convertTempZipEntrust(ApiRequest apiRequest) {
        Long surveyInfoId = apiRequest.getLong("surveyInfoId");
        SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(surveyInfoId);
        SurveyRiskCase surveyRiskCase = surveyRiskCaseMapper.selectByPrimaryKey(surveyRiskCaseInfo.getSurveyId());
        String fileName = surveyRiskCase.getSurveyCaseNo() == null ? surveyRiskCase.getSurveyPerson() + "案" : surveyRiskCase.getSurveyCaseNo();
        String ext = apiRequest.getString("ext");
        String downType = apiRequest.getString("downType");
        apiRequest.put("downType",downType);
        if ("/test/".equals(confSurveySouce)){//测试环境
            apiRequest.put("surveyCno",surveyRiskCaseInfo.getSurveyCno().toLowerCase());
            apiRequest.put("newFileName",fileName + "." + ext);
            return backendSurveyCaseFileApi.sftpZipByEntrust(apiRequest);
        }
        try {
            String type = apiRequest.getString("type");
            int length = 9;
//            if ("help".equals(type)){
//                length = 9;
//            }
            NameValuePair[] data = new NameValuePair[length];
            data[0] =new NameValuePair("apiKey","ca89e65c77be0d3f0d732cc3134edaf4");
            data[1] =new NameValuePair("apiSecret","32f48148e40fcf5586c269f65e6045b5");
            data[2] =new NameValuePair("functionCodeInput","sftp-temp-zip-entrust");
            data[3] =new NameValuePair("paramName","surveyCno");
            data[4] =new NameValuePair("paramValue",surveyRiskCaseInfo.getSurveyCno().toLowerCase());
            data[5] =new NameValuePair("paramName","newFileName");
            data[6] =new NameValuePair("paramValue",fileName + "." + ext);
            if ("help".equals(type)){//如果是互助下载报告，则将所有互助审核通过的报告一并下载
                StringBuffer orgNames = new StringBuffer();
                Map<String,Object> map =  new HashMap<String,Object>();
                map.put("surveyInfoId",surveyInfoId);
                map.put("orgSurveyState",4);
                List<SurveyAssignOrgDto> list = surveyAssignOrgMapper.list(map);
                for (SurveyAssignOrgDto surveyAssignOrgDto : list) {
                    orgNames.append(fileName + "(" + surveyAssignOrgDto.getSurveyOrgName() + ")" + "." + ext + ",");
                }
                data[7] =new NameValuePair("paramName","orgNames");
                data[8] =new NameValuePair("paramValue",orgNames.toString());
            }else{
                data[7] = new NameValuePair("paramName","downType");
                data[8] = new NameValuePair("paramValue",downType);
            }
            String res = HttpClientUtils.httpPost(url, data);
            System.out.println(res);
            Map map = JSONArray.parseObject(res);
            String code = map.get("code").toString();
            if ("0000".equals(code)){
                Map resultMap = (Map)map.get("results");
                String path = resultMap.get("path").toString();
                System.out.println(path);
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,resultMap);
            }else if ("1901".equals(code)){
                return new ApiResponse(ApiMsgEnum.SURVEY_FILE_DIR);//无目录
            }else{
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.SURVEY_FILE_OVER);
    }

    @ApiMethod(needLogin = false,descript = "转换成zip文件",value = "survey-convert-temp-zip-extension-time")
    @Override
    public ApiResponse convertTempZipExtensionTime(ApiRequest apiRequest) {
        try {
            String url = apiRequest.getString("url");
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,url);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(needLogin = false,descript = "方向附件转换成zip文件",value = "survey-convert-temp-zip-direction-file")
    @Override
    public ApiResponse convertTempZipDirectionFile(ApiRequest apiRequest) {
        try {
            String url = apiRequest.getString("url");
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,url);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
    @Value("${down.http.path.claims}")
    private String httpPath;
    @Value("${down.real.path.claims}")
    private String realPath1;
    @ApiMethod(needLogin = false,descript = "下载32服务器文件",value = "down-file-32")
    @Override
    public ApiResponse downFile(ApiRequest apiRequest) {
        List<File> files = new ArrayList<>();
        List<String> realFiles = JSONArray.parseArray(apiRequest.getString("files"),String.class);
        for (String realFile : realFiles) {
            realFile = realFile.replace(httpPath, realPath1);
            //测试L:
            File file = new File(realFile);
            if (file.exists()) {
                files.add(file);
            }
        }
        Map<String,Object> map =  new HashMap<String,Object>();
        String realPath = realPath1 +  "/pic/zip/temp/" + System.currentTimeMillis() + ".zip";
        realPath = FileUtil32.zipFiles(files,new File(realPath));

        realPath = realPath.replace(realPath1,httpPath);
        map.put("realPath",realPath);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }
}
