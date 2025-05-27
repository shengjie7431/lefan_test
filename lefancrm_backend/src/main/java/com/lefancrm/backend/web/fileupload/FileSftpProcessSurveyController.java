package com.lefancrm.backend.web.fileupload;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import com.lefancrm.backend.dto.SurveyUploadFileDto;
import com.lefancrm.backend.inner.FileSurveyService;
import com.lefancrm.backend.util.SFTPUtils;
import com.lefancrm.backend.web.BackendBaseController;
import com.lefancrm.backend.web.comment.MyProgressListener;
import com.lefancrm.backend.web.comment.ProcessInfo;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import sh.zj100.common.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文件上传下载
 *
 * @author kevin
 *
 */
@RequestMapping(value = "/process/sftp/survey/")
@Controller
public class FileSftpProcessSurveyController extends BackendBaseController {
    private static final Logger log = Logger.getLogger(FileSftpProcessSurveyController.class);
    @Autowired
    private FileSurveyService fileService;
    @Value("${file.survey.upload.fileMaxSize}")
    private long fileMaxSize;
    @Value("${file.survey.upload.fileType}")
    private String fileType;
    @Value("${survey.file.path.sftp}")
    public String surveyFilePath;
    @Value("${survey.upload.ip}")
    private String surveyUploadIp;

    @Value("${survey.file.source.sftp}")
    private String surveySource;

    @RequestMapping(value = "/uploadSftp", method = RequestMethod.POST)
    public void uploadSftp(HttpServletRequest request, HttpServletResponse response){
        final HttpSession httpSession = request.getSession();
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            upload.setProgressListener(new MyProgressListener(request));
            String modelType = request.getParameter("modelType");
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String primary = format.format(new Date());
            String uploadPath = surveySource;//test测试  proudct生产
            if ("direction".equals(modelType) || "report".equals(modelType) || "material".equals(modelType) || "follow".equals(modelType)) {
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
                    String folder = request.getParameter("folder");
                    if (folder != null && !"".equals(folder)){
                        String[] folders = folder.split(",");
                        for (String s : folders) {
                            uploadPath = uploadPath.concat("/" + s.trim() + "/");
                        }
                    }
                }
            }else{
                uploadPath = uploadPath.concat("/ddr/file/").concat(modelType).concat("/").concat(primary);
            }
            List<SurveyUploadFileDto> surveyFiles = new ArrayList<>();
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem fileItem : list) {
                String fileName = fileItem.getName();
                File file = new File("E:/mnt/sftp/files/".concat(uploadPath));
                if (!file.exists()){
                    file.mkdirs();
                }
                file = new File("E:/mnt/sftp/files/".concat(uploadPath) + "/" + fileName);
                if (!file.exists()){
                    file.createNewFile();
                }
                fileItem.write(file);
                SurveyUploadFileDto surveyFile = new SurveyUploadFileDto();
                surveyFile.setFilePath(surveyFilePath.concat(uploadPath).concat("/").concat(fileName));
                surveyFile.setFileName(fileName);
                surveyFile.setFileExt(fileName.substring(fileName.lastIndexOf(".") + 1));
                surveyFiles.add(surveyFile);

//                Boolean b = SFTPUtils.upload(surveyUploadIp,"/mnt/sftp/files/".concat(uploadPath), fileName,fileItem.getInputStream());
//                if (b){
//                    SurveyUploadFileDto surveyFile = new SurveyUploadFileDto();
//                    surveyFile.setFilePath(surveyFilePath.concat(uploadPath).concat("/").concat(fileName));
//                    surveyFile.setFileName(fileName);
//                    surveyFile.setFileExt(fileName.substring(fileName.lastIndexOf(".") + 1));
//                    surveyFiles.add(surveyFile);
//                }
            }
            httpSession.setAttribute("surveyFiles",surveyFiles);

//            List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
//            for (MultipartFile multipartFile : files){
//                String fileName = ((CommonsMultipartFile) multipartFile).getFileItem().getName();
//                InputStream in = null;
//                try {
//                    in = multipartFile.getInputStream();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//                try {
//                    Boolean b = SFTPUtils.upload(surveyUploadIp,"/mnt/sftp/files/".concat(uploadPath), fileName,in);
//                    if (b){
//                        SurveyUploadFileDto surveyFile = new SurveyUploadFileDto();
//                        surveyFile.setFilePath(surveyFilePath.concat(uploadPath).concat("/").concat(fileName));
//                        surveyFile.setFileName(fileName);
//                        surveyFile.setFileExt(fileName.substring(fileName.lastIndexOf(".") + 1));
//                        surveyFiles.add(surveyFile);
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
            jsonMap.put("success", "true");
            jsonMap.put("surveyFiles", surveyFiles);
        }catch (Exception e){
            jsonMap.put("success", "false");
            jsonMap.put("message", "上传异常");
            e.printStackTrace();
        }
        String json = JsonUtil.objectToJson(jsonMap);
        this.outputJson(json, response);
    }


    /**
     * process 获取进度
     */
    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public String process(HttpServletRequest request,HttpServletResponse response) throws Exception {
        ProcessInfo processInfo = (ProcessInfo)request.getSession().getAttribute("processInfo");
        List<SurveyUploadFileDto> surveyFiles = (List<SurveyUploadFileDto>)request.getSession().getAttribute("surveyFiles");
        processInfo.setSurveyFiles(surveyFiles);
        String json = JsonUtil.objectToJson(processInfo);
        return this.outputJson(json, response);
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
                            surveyFile.setFolder(false);
                            surveyFile.setFileName(listFile.getName());
                            surveyFile.setFilePath(folderPath + "/" + listFile.getName());
                            surveyFile.setFileSize(listFile.listFiles().length);
                        }else{
                            surveyFile.setFolder(true);
                            surveyFile.setFileName(listFile.getName());
                            String filePath = folderPath + "/" + listFile.getName();
                            surveyFile.setFileExt(surveyFile.getFileName().substring(surveyFile.getFileName().lastIndexOf(".") + 1));
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
            jsonMap.put("message", "异常");
            e.printStackTrace();
        }
        String json = JsonUtil.objectToJson(jsonMap);
        return this.outputJson(json, response);
    }

    public boolean validateFile(MultipartFile file) {
        long maxSize = file.getSize();
        String ext = file.getOriginalFilename();
        ext = ext.substring(ext.lastIndexOf(".") + 1).toLowerCase();
        if (maxSize > this.fileMaxSize * 1024 * 1024) {
            return false;
        }
        if (!this.fileType.contains(ext)) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/downSftp",method = RequestMethod.POST)
    public String  downSftp(HttpServletResponse response, HttpServletRequest request){
        String uploadType = request.getParameter("uploadType");
        if ("entrust".equals(uploadType)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CONVERT_TEMP_ZIP_ENTRUST,null,request,response);
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
}
