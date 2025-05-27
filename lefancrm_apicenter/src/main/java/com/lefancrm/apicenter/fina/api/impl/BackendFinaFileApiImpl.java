package com.lefancrm.apicenter.fina.api.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.fina.dao.FinaFileMapper;
import com.lefancrm.apicenter.fina.enums.FileTableEnum;
import com.lefancrm.apicenter.fina.model.FinaFile;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.fina.FilePathUtil;
import com.lefancrm.base.annotations.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
@ApiService(descript = "附件材料")
public class BackendFinaFileApiImpl  extends BaseServiceImpl {
    @Value("${http.fina.path}")
    public String httpFilePath;

    @Autowired
    private FinaFileMapper finaFileMapper;

    /**
     * 保存附件
     * @param keyId  业务表ID
     * @param fileTableEnum 定义枚举
     * @param userInfo 当前登陆人
     * @param json  附件json
     */
    public void saveFile(Long keyId, FileTableEnum fileTableEnum, UserInfo userInfo, String json){
        if (!StringUtils.isEmpty(json)) {
            List<FinaFile> finaFiles = JSONArray.parseArray(json, FinaFile.class);
            for (FinaFile finaFile : finaFiles) {
                String filePath = finaFile.getFilePath();
                if (StringUtils.isEmpty(filePath))
                    continue;
                filePath = FilePathUtil.convertToHttpPath(httpFilePath,filePath);//转换成HTTP路径存储
                finaFile.setFilePath(filePath);
                filePath = FilePathUtil.convert(filePath);
                finaFile.setFileName(filePath.substring(filePath.lastIndexOf("/") + 1));
                finaFile.setKeyId(keyId);
                finaFile.setKeyCode(fileTableEnum.getCode());
                finaFile.setKeyName(fileTableEnum.getName());
                finaFile.setUploadBy(userInfo.getUserName());
                finaFile.setUploadTime(new Date());
                finaFileMapper.insert(finaFile);
            }
        }
    }


    /**
     * 查询附件列表
     * @param keyId
     * @param fileTableEnum
     * @return
     */
    public List<FinaFile> getFiles(Long keyId,FileTableEnum fileTableEnum){
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        paramMap.put("keyId",keyId);
        paramMap.put("keyCode",fileTableEnum.getCode());
        List<FinaFile> finaFiles = finaFileMapper.list(paramMap);
        return finaFiles;
    }

    /**
     * 根据ID集合及类型 查询附件列表
     * @param keyIds
     * @param fileTableEnum
     * @return
     */
    public List<FinaFile> getFilesByIds(List<Long> keyIds,FileTableEnum fileTableEnum){
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        String ids = keyIds.stream().map(item -> item.toString()).collect(Collectors.joining(","));
        paramMap.put("keyIds",ids);
        paramMap.put("keyCode",fileTableEnum.getCode());
        List<FinaFile> finaFiles = finaFileMapper.list(paramMap);
        return finaFiles;
    }
}
