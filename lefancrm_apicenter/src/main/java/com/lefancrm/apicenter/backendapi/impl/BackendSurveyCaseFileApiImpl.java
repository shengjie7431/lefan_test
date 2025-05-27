package com.lefancrm.apicenter.backendapi.impl;

import cn.jpush.api.utils.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendSurveyCaseFileApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.SurveyCaseDirectionFileDto;
import com.lefancrm.apicenter.dto.SurveyCaseFileDto;
import com.lefancrm.apicenter.dto.SurveyFilesDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.FileZipUtil;
import com.lefancrm.apicenter.util.PDFUtil;
import com.lefancrm.apicenter.util.WordToPDF;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lixianfeng on 2019/1/3.
 */
@Service
@ApiService(descript = "案件资料API")
public class BackendSurveyCaseFileApiImpl extends BaseServiceImpl implements BackendSurveyCaseFileApi{
    @Autowired
    private SurveyFileCatalogMapper surveyFileCatalogMapper;
    @Autowired
    private SurveyCaseFileMapper surveyCaseFileMapper;
    @Autowired
    private CommonFileMapper commonFileMapper;
    @Autowired
    private SurveyCaseDirectionFileMapper surveyCaseDirectionFileMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Value("${survey.file.source.sftp}")
    private String confSurveySouce;

    @ApiMethod(needLogin = false,descript = "上传材料",value = "upload-survey-case-file")
    @Override
    public ApiResponse listCaseFile(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        String files = apiRequest.getString("files");
        List<SurveyCaseFileDto> filesDtos = JSONArray.parseArray(files, SurveyCaseFileDto.class);
        for (SurveyCaseFileDto filesDto : filesDtos) {
            if (filesDto.getCatalogId() == null || "".equals(filesDto.getCatalogId())){
                filesDto.setCatalogId(8L);
                filesDto.setCatalogName("理赔申请资料");
            }
            String path = filesDto.getFilePath();
            CommonFile commonFile = new CommonFile();
            commonFile.setFilePath(path);
            int first = path.lastIndexOf("/");//最后一个斜杠出现的位置
            int last = path.lastIndexOf(".");//最后一个点出现的位置
//            commonFile.setFileName(path.substring(first + 1,last));
            commonFile.setFileName(filesDto.getFileName());
            commonFile.setCreateTime(new Date());
            commonFileMapper.insert(commonFile);
            filesDto.setCommonFileId(commonFile.getId());
            filesDto.setCreateBy(userInfo.getUserName());
            filesDto.setCreateTime(new Date());
            filesDto.setDeleteFlag(0);
            if (filesDto.getCatalogId() == null || "".equals(filesDto.getCatalogId())){
                filesDto.setCatalogId(8L);
            }
            SurveyFileCatalog surveyFileCatalog = surveyFileCatalogMapper.selectByPrimaryKey(filesDto.getCatalogId());
            if (surveyFileCatalog != null) {
                filesDto.setCatalogName(surveyFileCatalog.getCatalogName());
            }
            surveyCaseFileMapper.insert(filesDto);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @ApiMethod(needLogin = false,descript = "查看材料",value = "list-survey-case-file")
    @Override
    public ApiResponse uploadCaseFile(ApiRequest apiRequest) {
        Map map = new HashMap();
        List<SurveyFileCatalog> fileCatalogs = surveyFileCatalogMapper.list(apiRequest);
        //如果是新增案件的查看资料  则默认没有任何附件
        if ("new".equals(apiRequest.getString("selectType"))){
            apiRequest.put("surveyInfoId",-1);
        }
        //查看狄大人--查看资料
        if(!"direction".equals(apiRequest.getString("type"))) {
            List<SurveyCaseFileDto> surveyCaseFiles = surveyCaseFileMapper.list(apiRequest);
            for (SurveyCaseFileDto surveyCaseFile : surveyCaseFiles) {
                SurveyFileCatalog surveyFileCatalog = surveyFileCatalogMapper.selectByPrimaryKey(surveyCaseFile.getCatalogId());
                if (surveyFileCatalog != null) {
                    surveyCaseFile.setCatalogName(surveyFileCatalog.getCatalogName());
                    surveyCaseFile.setCatalogText(surveyFileCatalog.getCatalogText());
                    CommonFile commonFile = commonFileMapper.selectByPrimaryKey(surveyCaseFile.getCommonFileId());
                    surveyCaseFile.setCommonFile(commonFile);
                    int lastNamePdf = commonFile.getFilePath().lastIndexOf(".") + 1;
                    String ext =commonFile.getFilePath().substring(lastNamePdf);
                    if("doc".equals(ext) || "docx".equals(ext)){
                        surveyCaseFile.setFileType(1);
                    }else if ("rar".equals(ext) || "zip".equals(ext)){
                        surveyCaseFile.setFileType(3);
                    }else if ("xls".equals(ext) || "xlsx".equals(ext)){
                        surveyCaseFile.setFileType(4);
                    }else if("pdf".equals(ext)){
                        surveyCaseFile.setFileType(5);
                    }else if("txt".equals(ext)){
                        surveyCaseFile.setFileType(6);
                    }else{
                        surveyCaseFile.setFileType(2);
                    }
                }
                //上传时间（使用场景：查看资料页面，上传时间展示）
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(surveyCaseFile.getCreateTime());
                surveyCaseFile.setUpLoadTime(dateString);
            }
            map.put("surveyCaseFiles", surveyCaseFiles);
            for (SurveyFileCatalog fileCatalog : fileCatalogs) {
                fileCatalog.setSize(surveyCaseFiles.stream().filter(p -> p.getCatalogId() != null && p.getCatalogId().intValue() == fileCatalog.getId().intValue()).collect(Collectors.toList()).size());
            }
        }

        //查看狄大人--案件附件详情
        if("direction".equals(apiRequest.getString("type"))){
            Map<String,Object> directionMap = new HashMap<>();
            directionMap.put("directionId",apiRequest.getString("directionId"));
            List<SurveyCaseDirectionFileDto> directionFile = surveyCaseDirectionFileMapper.list(directionMap);
            for (SurveyCaseDirectionFileDto surveyCaseDirectionFile : directionFile) {
                CommonFile commonFile = commonFileMapper.selectByPrimaryKey(surveyCaseDirectionFile.getFileId());
                surveyCaseDirectionFile.setCommonFile(commonFile);
                int lastNamePdf = commonFile.getFilePath().lastIndexOf(".") + 1;
                String urlName =commonFile.getFilePath().substring(lastNamePdf);
                if("doc".equals(urlName)){
                    surveyCaseDirectionFile.setFileType(1);
                }else{
                    surveyCaseDirectionFile.setFileType(2);
                }
            }
            map.put("directionFile",directionFile);
        }
        map.put("fileCatalogs",fileCatalogs);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }

    @ApiMethod(needLogin = false,descript = "调查方向-上传图片",value = "backend-survey-case-direction-file-upload")
    @Override
    public ApiResponse directionFileMidOK(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        String files = apiRequest.getString("files");
        List<CommonFile> commonFiles = JSONArray.parseArray(files, CommonFile.class);
        for (CommonFile commonFile:commonFiles) {
            commonFile.setCreateTime(new Date());
            commonFileMapper.insert(commonFile);
        }
//        return new ApiResponse(ApiMsgEnum.SUCCESS);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,commonFiles);
    }

    @ApiMethod(needLogin = false,descript = "调查方向-删除图片",value = "backend-survey-case-direction-file-delete")
    @Override
    public ApiResponse directionFileDelete(ApiRequest apiRequest) {

        /*2019-10-31 新更改：上传的图片，直接保存进服务器，不再保存数据库，所以删除时，不需要走注释部分
        String fileId = apiRequest.getString("fileId");
        String directionId = apiRequest.getString("directionId");
        if(fileId != null && !"".equals(fileId)){
            Map<String ,Object> map = new HashMap<>();
            map.put("fileId",fileId);
            map.put("directionId",directionId);
            SurveyCaseDirectionFileDto surveyCaseDirectionFile = surveyCaseDirectionFileMapper.selectByInfo(map);
            if(surveyCaseDirectionFile!=null){
                surveyCaseDirectionFile.setDeleteFlag(1);
                surveyCaseDirectionFileMapper.updateByPrimaryKey(surveyCaseDirectionFile);
            }
        }*/
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @ApiMethod(descript = "下载文件将选中的文件转换为zip",value = "exec-temp-zip")
    @Override
    public ApiResponse execZip(ApiRequest apiRequest) {
        Map<String,Object> map =  new HashMap<String,Object>();
        try {
            String surveyCno = apiRequest.getString("surveyCno");
            String sourcePath = "/mnt/sftp/files/ddr/cno/" + surveyCno + "/";
            String zipPath = "/mnt/sftp/temp/zip/" + surveyCno + ".zip";
            File source = new File(sourcePath);
            if (!source.isDirectory()){
                sourcePath = "/data/ftp/ddr/cno/" + surveyCno + "/";
                source = new File(sourcePath);
                if (!source.isDirectory()){
                    return new ApiResponse(ApiMsgEnum.SURVEY_FILE_DIR);
                }
            }
            FileZipUtil.createZip(sourcePath, zipPath);
            map.put("path", zipPath);
            map.put("surveyCno", surveyCno);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("抛出异常");
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }


    @ApiMethod(descript = "下载文件将选中的文件转换为zip",value = "sftp-temp-zip")
    @Override
    public ApiResponse sftpZip(ApiRequest apiRequest) {
        Map<String,Object> map =  new HashMap<String,Object>();
        try {
            String surveyCno = apiRequest.getString("surveyCno").toLowerCase();
            String sourcePath = "/mnt/sftp/files/" + confSurveySouce + "/ddr/cno/" + surveyCno + "/";
            File source = new File(sourcePath);
            if (!source.isDirectory()){
                sourcePath = "/mnt/sftp/files/ddr/cno/" + surveyCno + "/";
                source = new File(sourcePath);
                if (!source.isDirectory()){
                    sourcePath = "/data/ftp/ddr/cno/" + surveyCno + "/";
                    source = new File(sourcePath);
                    if (!source.isDirectory()){
                        return new ApiResponse(ApiMsgEnum.SURVEY_FILE_DIR);
                    }
                }
            }
            String zipPath = "/mnt/sftp/temp/zip/" + surveyCno + ".zip";
            FileZipUtil.createZip(sourcePath, zipPath);
            if ("/test/".equals(confSurveySouce)){
                zipPath = zipPath.replace("/mnt/","http://119.3.48.62:6077/");
            }else if ("/product/".equals(confSurveySouce)){
                zipPath = zipPath.replace("/mnt/","https://ddrapi.shlefan.com/");
            }
            map.put("path", zipPath);
            map.put("surveyCno", surveyCno);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("抛出异常");
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }


    @ApiMethod(descript = "下载文件将选中的文件转换为zip-委托公司",value = "sftp-temp-zip-entrust")
    @Override
    public ApiResponse sftpZipByEntrust(ApiRequest apiRequest) {
        Map<String,Object> map =  new HashMap<String,Object>();
        try {
            String surveyCno = apiRequest.getString("surveyCno");
            String newFileName  = apiRequest.getString("newFileName");
            String orgNames = apiRequest.getString("orgNames");
            String sourcePath = "/mnt/sftp/files/" + confSurveySouce + "/ddr/cno/" + surveyCno + "/direction/";
            String reportPath = "/mnt/sftp/files/" + confSurveySouce + "/ddr/cno/" + surveyCno + "/report/";
            File source = new File(sourcePath);
            if (!source.isDirectory()){
                sourcePath = "/mnt/sftp/files/ddr/cno/" + surveyCno + "/direction/";
                source = new File(sourcePath);
                if (!source.isDirectory()){
                    sourcePath = "/data/ftp/ddr/cno/" + surveyCno + "/direction/";
                    source = new File(sourcePath);
                    File report = new File(reportPath);
                    if (!source.isDirectory() && !report.isDirectory()){//如果方向附件 和 报告路径同时不存在  则提示 没有附件
                        return new ApiResponse(ApiMsgEnum.SURVEY_FILE_DIR);
                    }
                }
            }
            String zipName = newFileName.substring(0,newFileName.lastIndexOf("."));
            String zipPath = "/mnt/sftp/temp/zip/" + zipName + ".zip";

            //2019年12月31日  15点03分   如果orgNames不为NULL 表示 是互助下载报告。 则需要下载多份报告
            if (StringUtils.isNotEmpty(orgNames)){
                List<String> files = new ArrayList<String>();
                List<String> fileNames = Arrays.asList(orgNames.split(","));
                for (String name : fileNames) {
                    files.add(reportPath + name);
                }
                FileZipUtil.createZipEntrust(sourcePath, zipPath,files);
            }else{
                String fPath = reportPath.concat(newFileName);
                File file = new File(reportPath);
                if (file.exists() && file.isDirectory()){
                    if (file.listFiles().length == 1) {//如果只有一个文件 则就取当前文件
                        fPath = file.listFiles()[0].getPath();
                    }
                }
                String downType = apiRequest.getString("downType");
                if ("pdf".equals(downType)){//将word转换为pdf
                    String pdfPath = "/mnt/sftp/temp/pdf/" + zipName + ".pdf";
                    WordToPDF.doc2pdf(fPath,pdfPath);//将word转换为pdf
                    fPath = pdfPath;
                    //获取所有的非图片文件
                    List<String> attrs = new ArrayList<>();
                    //所有的图片文件压缩成一个pdf
                    String imgPdfPath = "/mnt/sftp/temp/pdf/"+zipName+"(附件).pdf";
                    Map<String,List<String>> tempMap = new HashMap<>();//目录对应的图片集合
                    File temp = new File(sourcePath);//方向目录
                    if (temp.exists())
                    {
                        File[] names = temp.listFiles();//所有的方向名称
                        for (File name : names) {
                            if (name.isDirectory()){
                                File[] sonFoder = name.listFiles();//所有方向名称对应的子文件夹/老案件也可能没有文件夹直接对应的文件
                                List<String> imgs = new ArrayList<>();
                                for (File file1 : sonFoder) {
                                    if (file1.isFile() && !checkImg(file1)) {//是文件并且不是图片
                                        attrs.add(file1.getPath());
                                    }
                                    else if (file1.isFile() && checkImg(file1)){//是文件并且是图片
                                        imgs.add(file1.getPath());
                                        tempMap.put(name.getName(),imgs);//方向名称对于的图片集合
                                    }
                                    else if (file1.isDirectory()) {//是文件夹
                                        if (file1.getName().indexOf("-不下载") > -1){
                                            continue;
                                        }
                                        imgs = new ArrayList<>();
                                        File[] files = file1.listFiles();//所有方向名称子文件夹的文件
                                        for (File file2 : files) {
                                            if (file2.isFile() && !checkImg(file2)) {//是文件不是图片
                                                attrs.add(file2.getPath());
                                            }
                                            else if (file2.isFile() && checkImg(file2)){//是文件是图片
                                                imgs.add(file2.getPath());
                                                tempMap.put(name.getName() + "-" + file1.getName(),imgs);//方向名称对于的图片集合
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    imgPdfPath = PDFUtil.imgToPdf(tempMap,imgPdfPath);
                    List<String> files = new ArrayList<String>();
                    files.add(fPath);//报告
                    files.add(imgPdfPath);//图片附件的pdf
                    files.addAll(attrs);//所有非图片的附件
                    FileZipUtil.createZipEntrust("/a/b/c/d/", zipPath,files);//soucePath无意义
                }else {
                    FileZipUtil.createZipEntrust(sourcePath, zipPath,fPath);
                }
            }
            if ("/test/".equals(confSurveySouce)){
                zipPath = zipPath.replace("/mnt/","http://119.3.48.62:6077/");
            }else if ("/product/".equals(confSurveySouce)){
                zipPath = zipPath.replace("/mnt/","https://ddrapi.shlefan.com/");
            }
            map.put("path", zipPath);
            map.put("surveyCno", surveyCno);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("抛出异常");
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }


    /**
     * 判断文件是否是图片
     * @param file
     * @return
     */
    public boolean checkImg(File file){
        try {
            Image image = ImageIO.read(file);
            return image != null;
        } catch(IOException ex) {
            return false;
        }
    }
}
