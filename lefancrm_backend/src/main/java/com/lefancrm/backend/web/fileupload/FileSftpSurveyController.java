package com.lefancrm.backend.web.fileupload;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.gson.reflect.TypeToken;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.enums.ImageSizeEnum;
import com.lefancrm.backend.inner.FileSurveyService;
import com.lefancrm.backend.util.*;
import com.lefancrm.backend.util.fina.FilePathUtil;
import com.lefancrm.backend.web.BackendBaseController;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.AppApiMethodEnum;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sh.zj100.common.util.ImageUtil;
import sh.zj100.common.util.JsonUtil;
import sh.zj100.common.util.RandomIDUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * 文件上传下载
 *
 * @author kevin
 *
 */
@RequestMapping(value = "/sftp/survey/")
@Controller
public class FileSftpSurveyController extends BackendBaseController {
    private static final Logger log = Logger.getLogger(FileSftpSurveyController.class);
    @Autowired
    private FileSurveyService fileService;
    @Value("${file.survey.upload.fileMaxSize}")
    private long fileMaxSize;
    @Value("${file.survey.upload.fileType.new}")
    private String fileType;
    @Value("${survey.file.path.sftp}")
    public String surveyFilePath;
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
            String modelType = request.getParameter("modelType");// report 报告  material 资料  other 其他 product商品 entrust认证 knowledge知识库论坛 fee费用报销
            MultipartFile multipartFile = request.getFile("file");
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String primary = format.format(new Date());
            String uploadPath = surveySource;//test测试  proudct生产
//            String uploadFileName = RandomIDUtil.getNewUUID();
            String fileName = multipartFile.getOriginalFilename();
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!this.fileType.contains(fileExt)) {
                jsonMap.put("success", "false");
                jsonMap.put("message", "不支持的文件格式,文件名称：(" + fileName + ")");
                String json = JsonUtil.objectToJson(jsonMap);
                this.outputJson(json, response);
                return;
            }

            if (validateFile(multipartFile)) {
                if ("direction".equals(modelType) || "report".equals(modelType) || "material".equals(modelType) || "follow".equals(modelType) || "extensionTime".equals(modelType)) {
                    String surveyCno = request.getParameter("surveyCno");
                    if ("".equals(surveyCno) || surveyCno == null) {
                        surveyCno = "other";
                    }
                    uploadPath = uploadPath.concat("/ddr/cno/").concat(surveyCno.toLowerCase()).concat("/").concat(modelType);
                    if ("direction".equals(modelType)){
                        String taskName = request.getParameter("taskName");
                        String directionName = request.getParameter("directionName");
                        if (directionName != null && !"".equals(directionName)){
                            uploadPath = uploadPath.concat("/" + directionName);
                        }
//                        if (!"".equals(taskName) && !"".equals(directionName) && taskName != null && directionName != null){
//                            uploadPath = uploadPath.concat("/" + taskName).concat("/" + directionName);
//                        }
                        String folder = request.getParameter("folder");
                        if (folder != null && !"".equals(folder)){
                            String[] folders = folder.split(",");
                            for (String s : folders) {
                                uploadPath = uploadPath.concat("/" + s.trim() + "/");
                            }
                        }
                    }
                    if ("extensionTime".equals(modelType)){//案件延期文件
                        String assignOrgId = request.getParameter("assignOrgId");
                        String extensionFileId = request.getParameter("extensionFileId");
                        uploadPath = uploadPath.concat("/" + assignOrgId+"/"+extensionFileId);
                    }
                }else if ("fee".equals(modelType)){
                    String surveyCno = request.getParameter("surveyCno");
                    uploadPath = uploadPath.concat("/ddr/cno/").concat(modelType + "/").concat(surveyCno.toLowerCase());
                }else if ("feeAddBill".equals(modelType)){//费用报销添加发票
                    String id = request.getParameter("id");
                    uploadPath = uploadPath.concat("/ddr/file/").concat(modelType).concat("/").concat(id).concat("/").concat(primary);
                }else if ("otherReply".equals(modelType)){
                    String id = request.getParameter("assignOrgId");
                    uploadPath = uploadPath.concat("/ddr/file/").concat(modelType).concat("/").concat(id).concat("/").concat(primary);
                }else if ("financial".equals(modelType)){
                    String fileType = request.getParameter("fileType");
                    uploadPath = uploadPath.concat("/ddr/file/").concat(modelType).concat("/").concat(fileType).concat("/").concat(primary);
                } else{
                    uploadPath = uploadPath.concat("/ddr/file/").concat(modelType).concat("/").concat(primary);
                }
                String path = "/mnt/sftp/files/".concat(uploadPath);
                File file = new File(path + "/" + fileName);
                fileName = getFileName(path,fileName);//如果文件已存在 获取新的文件名。 2020年6月23日 新需求

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
                if ("direction".equals(modelType) && isImg){
                    b = SFTPUtils.resizeImageNew(multipartFile.getInputStream(),surveyUploadIp,surveyUploadPwd,path,fileName);
                }else {
                    b = SFTPUtils.upload(surveyUploadIp,surveyUploadPwd,path, fileName, multipartFile.getInputStream());
                }
                if (b) {
                    jsonMap.put("success", "true");
                    SurveyUploadFileDto surveyFile = new SurveyUploadFileDto();
//                    surveyFile.setFilePath(surveyFilePath.concat(uploadPath).concat("/").concat(uploadFileName).concat(".").concat(fileExt));
                    surveyFile.setFilePath(surveyFilePath.concat(uploadPath).concat("/").concat(fileName));
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

    /**
     * 不用SFTP 上传 。 测试使用。  2020年1月14日 16点46分
     * @param request
     * @param response
     */
    @RequestMapping(value = "/uploadSftpTest", method = RequestMethod.POST)
    public void uploadSftpTest(MultipartHttpServletRequest request, HttpServletResponse response){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            String modelType = request.getParameter("modelType");// report 报告  material 资料  other 其他 product商品 entrust认证 knowledge知识库论坛
            MultipartFile multipartFile = request.getFile("file");
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String primary = format.format(new Date());
            String uploadPath = surveySource;//test测试  proudct生产
//            String uploadFileName = RandomIDUtil.getNewUUID();
            String fileName = multipartFile.getOriginalFilename();
            String fileExt = fileName.substring(fileName.indexOf(".") + 1).toLowerCase();
            if (validateFile(multipartFile)) {
                if ("direction".equals(modelType) || "report".equals(modelType) || "material".equals(modelType) || "follow".equals(modelType) || "extensionTime".equals(modelType)) {
                    String surveyCno = request.getParameter("surveyCno");
                    if ("".equals(surveyCno) || surveyCno == null) {
                        surveyCno = "other";
                    }
                    uploadPath = uploadPath.concat("/ddr/cno/").concat(surveyCno.toLowerCase()).concat("/").concat(modelType);
                    if ("direction".equals(modelType)){
                        String taskName = request.getParameter("taskName");
                        String directionName = request.getParameter("directionName");
                        if (directionName != null && !"".equals(directionName)){
                            uploadPath = uploadPath.concat("/" + directionName);
                        }
//                        if (!"".equals(taskName) && !"".equals(directionName) && taskName != null && directionName != null){
//                            uploadPath = uploadPath.concat("/" + taskName).concat("/" + directionName);
//                        }
                        String folder = request.getParameter("folder");
                        if (folder != null && !"".equals(folder)){
                            String[] folders = folder.split(",");
                            for (String s : folders) {
                                uploadPath = uploadPath.concat("/" + s.trim() + "/");
                            }
                        }
                    }
                    if ("extensionTime".equals(modelType)){//案件延期文件
                        String assignOrgId = request.getParameter("assignOrgId");
                        uploadPath = uploadPath.concat("/" + assignOrgId);
                    }
                }else{
                    uploadPath = uploadPath.concat("/ddr/file/").concat(modelType).concat("/").concat(primary);
                }

                OutputStream out = null;
                InputStream in = null;
                File uploadedFile = new File(uploadPath, fileName);
                out = new FileOutputStream(uploadedFile);
                in = multipartFile.getInputStream();
                byte buf[] = new byte[1024];// 可以修改 1024 以提高读取速度
                int length = 0;
                while ((length = in.read(buf)) > 0) {
                    out.write(buf, 0, length);
                    out.flush();
                }
                try {
                    ImageUtil.drawImageScale(uploadedFile,uploadedFile,1200,1200);
                }catch (Exception e){
                    e.printStackTrace();
                }

                jsonMap.put("success", "true");
                SurveyUploadFileDto surveyFile = new SurveyUploadFileDto();
//                    surveyFile.setFilePath(surveyFilePath.concat(uploadPath).concat("/").concat(uploadFileName).concat(".").concat(fileExt));
                surveyFile.setFilePath(surveyFilePath.concat(uploadPath).concat("/").concat(fileName));
                surveyFile.setFileName(fileName);
                surveyFile.setFileExt(fileExt);
                jsonMap.put("surveyFile", surveyFile);
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



    @RequestMapping(value = "/getFileSftp1")
    public String getFileSftp1(HttpServletResponse response, HttpServletRequest request){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            String surveyCno = request.getParameter("surveyCno");
            String taskName = request.getParameter("taskName");
            String folder = request.getParameter("folder");
            String folderPath = "/mnt/sftp/files/" + surveySource + "/ddr/cno/" + surveyCno.toLowerCase() + "/direction/" + folder;
            folderPath = folderPath.replace("//","/").replace("/","/");
//            String folderPath = "/mnt/sftp/files/test/ddr/cno/cwt1331570760897909/direction/医疗调查/对的";
            if (!SFTPUtils.existsFolder(surveyUploadIp,folderPath)){
                folderPath = "/mnt/sftp/files/" + surveySource + "/ddr/cno/" + surveyCno.toLowerCase() + "/direction/" + taskName + "/" + folder;
            }
            Vector vector = SFTPUtils.listFiles(surveyUploadIp,folderPath);
            List<SurveyUploadFileDto> files = new ArrayList<SurveyUploadFileDto>();
            if (vector.size() > 0){
                Iterator iterator = vector.iterator();
                while (iterator.hasNext()){
                    ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) iterator.next();
                    String filename = entry.getFilename();
                    SftpATTRS attrs = entry.getAttrs();
                    if (!".".equals(filename) && !"..".equals(filename)){
                        SurveyUploadFileDto surveyFile = new SurveyUploadFileDto();
                        if (attrs.isDir()){
                            surveyFile.setFolder(false);
                            surveyFile.setFileName(filename);
                            surveyFile.setFilePath(folderPath + "/" + filename);
                            surveyFile.setFileSize(SFTPUtils.listFiles(surveyUploadIp,surveyFile.getFilePath()).size());
                        }else{
                            surveyFile.setFolder(true);
                            surveyFile.setFileName(filename);
                            String filePath = folderPath + "/" + filename;
                            surveyFile.setFilePath(filePath.replace("/mnt/sftp/files",surveyFilePath));
                        }
                        files.add(surveyFile);
                    }
                }
            }
            jsonMap.put("success", "true");
            jsonMap.put("files", files);
        }catch (Exception e){
            jsonMap.put("success", "false");
            jsonMap.put("message", "上传异常");
            e.printStackTrace();
        }
        String json = JsonUtil.objectToJson(jsonMap);
        return this.outputJson(json, response);
    }


    @RequestMapping(value = "/getFileSftp")
    public String getFileSftp(HttpServletResponse response, HttpServletRequest request){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            String surveyCno = request.getParameter("surveyCno");
            String taskName = request.getParameter("taskName");
            String folder = request.getParameter("folder");
            String folderPath = "/mnt/sftp/files/" + surveySource + "/ddr/cno/" + surveyCno.toLowerCase() + "/direction/" + folder;
            if (surveySource.indexOf("dev") > -1){
                folderPath = "E:\\mnt";
            }
            File file = new File(folderPath);
            if (!file.exists()){
                folderPath = "/mnt/sftp/files/" + surveySource + "/ddr/cno/" + surveyCno.toLowerCase() + "/direction/" + taskName + "/" + folder;
                file = new File(folderPath);
            }
            List<SurveyUploadFileDto> files = new ArrayList<SurveyUploadFileDto>();
            if (file.exists()){
                if (file.isDirectory()){
                    for (File listFile : file.listFiles()) {
                        SurveyUploadFileDto surveyFile = new SurveyUploadFileDto();
                        if (listFile.isDirectory()) {//是文件夹
                            if ("1200x1200".equals(listFile.getName())){
                                continue;
                            }
                            surveyFile.setFolder(false);
                            surveyFile.setFileName(listFile.getName());
                            surveyFile.setFilePath(folderPath + "/" + listFile.getName());
                            surveyFile.setFileSize(listFile.listFiles().length);
                            surveyFile.setRealFilePath(folderPath + "/" + listFile.getName());
                        }else{
                            surveyFile.setFolder(true);
                            surveyFile.setFileName(listFile.getName());
                            String filePath = folderPath + "/" + listFile.getName();
                            surveyFile.setFileExt(surveyFile.getFileName().substring(surveyFile.getFileName().lastIndexOf(".") + 1));
                            surveyFile.setFilePath(filePath.replace("/mnt/sftp/files",surveyFilePath));
                            surveyFile.setRealFilePath(folderPath + "/" + listFile.getName());
                        }
                        files.add(surveyFile);
                    }
                }
            }
            jsonMap.put("success", "true");
            try {
                //files 根据文件名称排序
                Collections.sort(files, new Comparator<SurveyUploadFileDto>() {
                    @Override
                    public int compare(SurveyUploadFileDto o1, SurveyUploadFileDto o2) {
                        int diff = o1.getFileName().charAt(0) - o2.getFileName().charAt(0);
                        if (diff > 0){
                            return 1;
                        }else if (diff < 0){
                            return -1;
                        }
                        return 0;
                    }
                });
            }catch (Exception e){
                System.out.println("排序");
                e.printStackTrace();
            }
            jsonMap.put("files", files);
        }catch (Exception e){
            jsonMap.put("success", "false");
            jsonMap.put("message", "异常");
            e.printStackTrace();
        }
        String json = JsonUtil.objectToJson(jsonMap);
        return this.outputJson(json, response);
    }


    @RequestMapping(value = "/getFileByPath")
    public String getFileByPath(HttpServletResponse response, HttpServletRequest request){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        List<SurveyUploadFileDto> files = new ArrayList<SurveyUploadFileDto>();
        String folderPath = request.getParameter("folderPath");
        if (!StringUtils.isEmpty(folderPath)) {
            if (folderPath.length() > 2) {
                File file = new File(folderPath);
                if (file.exists()){
                    if (file.exists()){
                        if (file.isDirectory()){
                            for (File listFile : file.listFiles()) {
                                SurveyUploadFileDto surveyFile = new SurveyUploadFileDto();
                                if (listFile.isDirectory()) {//是文件夹
                                    if ("1200x1200".equals(listFile.getName())){
                                        continue;
                                    }
                                    surveyFile.setFolder(false);
                                    surveyFile.setFileName(listFile.getName());
                                    surveyFile.setFilePath(folderPath + "/" + listFile.getName());
                                    surveyFile.setFileSize(listFile.listFiles().length);
                                    surveyFile.setRealFilePath(folderPath + "/" + listFile.getName());
                                }else{
                                    surveyFile.setFolder(true);
                                    surveyFile.setFileName(listFile.getName());
                                    String filePath = folderPath + "/" + listFile.getName();
                                    surveyFile.setFileExt(surveyFile.getFileName().substring(surveyFile.getFileName().lastIndexOf(".") + 1));
                                    surveyFile.setFilePath(filePath.replace("/mnt/sftp/files",surveyFilePath));
                                    surveyFile.setRealFilePath(folderPath + "/" + listFile.getName());
                                }
                                files.add(surveyFile);
                            }
                        }
                        try {
                            //files 根据文件名称排序
                            Collections.sort(files, new Comparator<SurveyUploadFileDto>() {
                                @Override
                                public int compare(SurveyUploadFileDto o1, SurveyUploadFileDto o2) {
                                    int diff = o1.getFileName().charAt(0) - o2.getFileName().charAt(0);
                                    if (diff > 0){
                                        return 1;
                                    }else if (diff < 0){
                                        return -1;
                                    }
                                    return 0;
                                }
                            });
                        }catch (Exception e){
                            System.out.println("排序");
                            e.printStackTrace();
                        }

                    }
                }
                jsonMap.put("curPath",file.getPath());
            }
        }
        jsonMap.put("success", "true");
        jsonMap.put("files", files);
        String json = JsonUtil.objectToJson(jsonMap);
        return this.outputJson(json, response);
    }

    @RequestMapping(value = "/delFileByPath")
    public String delFileByPath(HttpServletResponse response, HttpServletRequest request){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("success", "false");
        String filePath = request.getParameter("filePath");
        String json = null;
        try {
            if (!StringUtils.isEmpty(filePath)){
                if (surveySource.indexOf("product") > -1 || surveySource.indexOf("test") > -1){//如果是测试或线上
                    if (filePath.indexOf(generateFilePath) > -1 && filePath.indexOf("direction") > -1){//验证必须是方向目录下  为保证安全
                        FileUtils.deleteDir(filePath);
                        jsonMap.put("success", "true");
                    }
                }else{
                    FileUtils.deleteDir(filePath);
                    jsonMap.put("success", "true");
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

    @RequestMapping(value = "/downSftp",method = RequestMethod.POST)
    public String  downSftp(HttpServletResponse response, HttpServletRequest request){
        String uploadType = request.getParameter("uploadType");
        if ("entrust".equals(uploadType)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CONVERT_TEMP_ZIP_ENTRUST,null,request,response);
        }else if ("extensionTime".equals(uploadType)){
            Map<String,Object> appendMap = new HashMap<>();
            appendMap.put("url",returnUrl(request, "extensionTime"));
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CONVERT_TEMP_ZIP_EXTENSION_TIME,appendMap,request,response);
        }else if("directionFile".equals(uploadType)){
            Map<String,Object> appendMap = new HashMap<>();
            appendMap.put("url",returnUrl(request, "direction"));
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CONVERT_TEMP_ZIP_DIRECTION_FILE,appendMap,request,response);
        }else if("allFile".equals(uploadType)){
            //因为会存在无用的“附件”（如新增方向的时候，先上传附件，却未最终提交，会导致上述附件为冗余附件，故删除）
            Map<String,Object> paramMap =  new HashMap<String,Object>();
            paramMap.put("surveyInfoId",request.getParameter("surveyInfoId"));
            TypeToken<ApiFinalResponse<TemplateData>> typeToken = new TypeToken<ApiFinalResponse<TemplateData>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_GET_TEMPLATE_DATA, paramMap, request);
            TemplateData data = (TemplateData)apiFinalResponse.getResults();

            deleteNotFindDirectionName(data);
        }else if ("caseFiles".equals(uploadType)){// 调查案件批量下载附件
            List<String> httpFiles = JSONArray.parseArray(request.getParameter("files"),String.class);
            ArrayList<String> imgFiles = Lists.newArrayList();
            ArrayList<String> attrFiles = Lists.newArrayList();
            for (String httpFile : httpFiles) {
                httpFile = httpFile.replaceAll("\\\\","/");
                String realPath = httpFile.replace(surveyFilePath,"/mnt/sftp/files/");
                Boolean img = SurveyZipUtil.checkImg(new File(realPath));
                if (img){
                    imgFiles.add(realPath);
                }else{
                    attrFiles.add(realPath);
                }
            }
            if (imgFiles.size() > 0){
                String zipPath = "/mnt/sftp/files/temp/zip/" + "images-" + System.currentTimeMillis() + ".zip";
                FileZipUtil.createZipEntrust("/a/b/c/d/", zipPath,imgFiles);//
                attrFiles.add(zipPath);
            }
            ArrayList<String> tempHttpFiles = Lists.newArrayList();
            Map<String,Object> appendMap = new HashMap<>();
            for (String attrFile : attrFiles) {
                tempHttpFiles.add(FilePathUtil.convertToHttpPath(surveyFilePath,attrFile));
            }
            appendMap.put("files",tempHttpFiles);
            return this.outputJson(com.lefancrm.base.utils.JsonUtil.objectToJson(appendMap, Map.class),response);
        }
//        TypeToken typeToken = new TypeToken<ApiFinalResponse<Map>>() {};
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CONVERT_TEMP_ZIP,null,request,response);
//        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CONVERT_TEMP_ZIP, null, request);
//        Map map = (Map)apiFinalResponse.getResults();
//        String path = map.get("path").toString();
//        String filePath = path.substring(0,path.lastIndexOf("/") + 1);
//        String fileName = path.substring(path.lastIndexOf("/") + 1);
//        SFTPUtils.down(filePath,fileName,"F:\\sftp\\",fileName,true);
//        return path;
    }

    private String returnUrl(HttpServletRequest request,String code) {
        //返回的压缩路径
        File file = null;

        if("direction".equals(code)){
            String surveyCno = request.getParameter("surveyCno").toLowerCase();
            String directionName = request.getParameter("directionName").toLowerCase();

            file = new File("/mnt/sftp/files/temp/zip/" + surveyCno + "/direction/" + directionName + ".zip");
            if(file.exists()){
                file.delete();
            }
            if (!file.getParentFile().exists()){
                try {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            }catch (Exception e){
                e.printStackTrace();
            }


//            url = "/mnt/sftp/files/temp/zip/" + surveyCno + "/direction/" ;
//            File file = new File(url);
//            if (!file.exists()){
//                file.mkdirs();
//            }
//            url = url.concat(directionName)+".zip";
            //原文件路径
            String sourcePath = "/mnt/sftp/files/" + surveySource + "/ddr/cno/" + surveyCno + "/direction/" + directionName + "/";
            if (new File(sourcePath).exists()){
                FileZipUtil.createZip(sourcePath, file.getPath());
            }
        }else if("extensionTime".equals(code)){

            String surveyCno = request.getParameter("surveyCno").toLowerCase();
            String assignOrgId = request.getParameter("assignOrgId");
            String extensionFileId = request.getParameter("extensionFileId");

            file = new File("/mnt/sftp/files/temp/zip/" + surveyCno + "/extensionTime/" + assignOrgId + "/"+ extensionFileId +".zip");
            if(file.exists()){
                file.delete();
            }
            if (!file.getParentFile().exists()){
                try {
                    file.getParentFile().mkdirs();
                    file.createNewFile();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
//            url = "/mnt/sftp/files/temp/zip/" + surveyCno + "/extensionTime/" ;
//            File file = new File(url);
//            if (!file.exists()){
//                file.mkdirs();
//            }
//            url = url.concat(assignOrgId)+".zip";
            //原文件路径
            String sourcePath = "/mnt/sftp/files/" + surveySource + "/ddr/cno/" + surveyCno + "/extensionTime/" + assignOrgId + "/"+ extensionFileId + "/";
            if (new File(sourcePath).exists()){
                FileZipUtil.createZip(sourcePath, file.getPath());
            }
        }
        if (file != null){
            return file.getPath().replace("/mnt/sftp/files",surveyFilePath);
        }
        return null;
//        url = (url.replace("/mnt/sftp/files",surveyFilePath));
//        return url;
    }


    //因为会存在无用的“附件”（如新增方向的时候，先上传附件，却未最终提交，会导致上述附件为冗余附件，故删除）
    private void deleteNotFindDirectionName(TemplateData data){
        String directionPath = generateFilePath.concat("/").concat(data.getSurveyRiskCaseInfo().getSurveyCno().toLowerCase()).concat("/direction");
        File file = new File(directionPath);
        if (file.exists()) { //如果存在
            List<SurveyCaseDirectionDto> directions = data.getDirections();
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                Boolean havaDel = false; //不删除
                for (SurveyCaseDirectionDto direction : directions) {
                    if (direction.getRealDirectionName().equals(files[i].getName())){
                        havaDel = false;
                        break;
                    }else{
                        havaDel = true;
                        continue;
                    }
                }
                //如果“文件名称”不存在与“方向”中，删除
                if(havaDel){
                    if(files[i].exists()){
                        FileUtils.deleteDir(files[i].getPath());
                    }
                }
            }
        }
    }
}
