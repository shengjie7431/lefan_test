package com.lefancrm.backend.web.fileupload;

import com.google.gson.reflect.TypeToken;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import com.lefancrm.backend.dto.SurveyCaseDirectionDto;
import com.lefancrm.backend.dto.SurveyUploadFileDto;
import com.lefancrm.backend.dto.TemplateData;
import com.lefancrm.backend.inner.FileSurveyService;
import com.lefancrm.backend.util.FileUtils;
import com.lefancrm.backend.util.FileZipUtil;
import com.lefancrm.backend.util.SFTPUtils;
import com.lefancrm.backend.util.fina.FilePathUtil;
import com.lefancrm.backend.web.BackendBaseController;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sh.zj100.common.util.ImageUtil;
import sh.zj100.common.util.JsonUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文件上传下载
 *
 * @author kevin
 *
 */
@RequestMapping(value = "/fina/files/")
@Controller
public class FileFinaController extends BackendBaseController {
    private static final Logger log = Logger.getLogger(FileFinaController.class);
    @Autowired
    private FileSurveyService fileService;
    @Value("${file.survey.upload.fileMaxSize}")
    private long fileMaxSize;
    @Value("${file.survey.upload.fileType.new}")
    private String fileType;
    @Value("${http.fina.path}")
    public String httpFilePath;
    @Value("${survey.upload.ip}")
    private String surveyUploadIp;
    @Value("${survey.upload.pwd}")
    private String surveyUploadPwd;

    @Value("${survey.file.source.sftp}")
    private String surveySource;
    @Value("${survey.report.path}")
    private String generateFilePath;

    @RequestMapping(value = "/uploadSftp", method = RequestMethod.POST)
    public void uploadSftp(MultipartHttpServletRequest request, HttpServletResponse response){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            String modelType = request.getParameter("modelType");//结算单 settlement  垫付案件applicant
            MultipartFile multipartFile = request.getFile("file");
            String uploadPath = "";
            String fileName = multipartFile.getOriginalFilename();
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!this.fileType.contains(fileExt)) {
                jsonMap.put("success", "false");
                jsonMap.put("message", "不支持的文件格式,文件名称：(" + fileName + ")");
                String json = JsonUtil.objectToJson(jsonMap);
                this.outputJson(json, response);
                return;
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
            String time = simpleDateFormat.format(new Date());
            if (validateFile(multipartFile)) {
                String pathId = request.getParameter("pathId");
                uploadPath = "/mnt/sftp/files/fina/" + surveySource + "/"+modelType+"/"+time+"/"+pathId+"/";

                fileName = getFileName(uploadPath,fileName);//如果文件已存在 获取新的文件名。 2020年6月23日 新需求
                boolean isImg = true;
                try {
                    BufferedImage prevImage = ImageIO.read(multipartFile.getInputStream());
                    double width = prevImage.getWidth();
                    double height = prevImage.getHeight();
                    if (width < 1920 || height < 1080) {
                        isImg = false;//不压缩
                    }
                }catch (NullPointerException e){
                    isImg = false;//不是图片不压缩
                }

                boolean b;
                if (isImg){
                    b = SFTPUtils.resizeImageNew(multipartFile.getInputStream(),surveyUploadIp,surveyUploadPwd,uploadPath,fileName);
                }else {
                    b = SFTPUtils.upload(surveyUploadIp,surveyUploadPwd,uploadPath, fileName, multipartFile.getInputStream());
                }
                if (b) {
                    jsonMap.put("success", "true");
                    SurveyUploadFileDto surveyFile = new SurveyUploadFileDto();
                    surveyFile.setFilePath(FilePathUtil.convertToHttpPath(httpFilePath,uploadPath + "/" + fileName));
                    surveyFile.setFileName(fileName);
                    surveyFile.setFileExt(fileExt);
                    jsonMap.put("surveyFile", surveyFile);
                } else {
                    jsonMap.put("success", "false");
                    jsonMap.put("message", "上传异常");
                }
            }else{
                jsonMap.put("success", "false");
                jsonMap.put("message", "验证不通过(文件过大或格式不正确)");
            }
        }catch (Exception e){
            jsonMap.put("success", "false");
            jsonMap.put("message", "上传异常");
            e.printStackTrace();
        }
        String json = JsonUtil.objectToJson(jsonMap);
        this.outputJson(json, response);
    }

    private static String getFileName(String path,String fileName){
        File file = new File(path + "/" + fileName);
        if (file.exists()) {
            for (int i = 1; true; i++) {
                fileName = fileName.replace(fileName.substring(0,fileName.lastIndexOf(".")),fileName.substring(0,fileName.lastIndexOf(".")) + "(" + i + ")");
                return getFileName(path,fileName);
            }
        }
        return fileName;
    }


    @RequestMapping(value = "/delFileByPath")
    public String delFileByPath(HttpServletResponse response, HttpServletRequest request){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("success", "false");
        String filePath = request.getParameter("filePath");
        String json = null;
        try {
            if (!StringUtils.isEmpty(filePath)){
                File file = new File(filePath);
                if (file.exists() && file.isFile()) {
                    file.delete();
                }
            }
            json = JsonUtil.objectToJson(jsonMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.outputJson(json, response);
    }


    public boolean validateFile(MultipartFile file) {
        long maxSize = file.getSize();
        String ext = file.getOriginalFilename();
        ext = ext.substring(ext.lastIndexOf(".") + 1).toLowerCase();
        if (maxSize > this.fileMaxSize * 1024 * 1024) {
            return false;
        }
//        if (!this.fileType.contains(ext)) {
//            return false;
//        }
        return true;
    }




}
