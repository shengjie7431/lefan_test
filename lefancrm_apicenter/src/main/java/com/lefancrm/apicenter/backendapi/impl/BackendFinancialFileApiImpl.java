package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.dao.FinancialFileMapper;
import com.lefancrm.apicenter.dto.finacial.FinancialFileTableEnumDto;
import com.lefancrm.apicenter.model.FinancialFile;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.fina.FilePathUtil;
import com.lefancrm.base.annotations.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@ApiService(descript = "每刻报销附件材料")
public class BackendFinancialFileApiImpl extends BaseServiceImpl {
    @Value("${http.fina.path}")
    public String httpFilePath;

    @Autowired
    private FinancialFileMapper financialFileMapper;

    /**
     * 保存附件
     * @param keyId  业务表ID
     * @param fileTableEnum 定义枚举
     * @param userInfo 当前登陆人
     * @param json  附件json
     */
    public void saveFile(Long keyId, FinancialFileTableEnumDto fileTableEnum, UserInfo userInfo, String json){
        if (!StringUtils.isEmpty(json)) {
            List<FinancialFile> finaFiles = JSONArray.parseArray(json, FinancialFile.class);
            for (FinancialFile finaFile : finaFiles) {
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
                finaFile.setDeleteFlag(0);
                financialFileMapper.insert(finaFile);
            }
        }
    }


    /**
     * 查询附件列表
     * @param keyId
     * @param fileTableEnum
     * @return
     */
    public List<FinancialFile> getFiles(Long keyId, FinancialFileTableEnumDto fileTableEnum){
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        paramMap.put("keyId",keyId);
        paramMap.put("keyCode",fileTableEnum.getCode());
        List<FinancialFile> finaFiles = financialFileMapper.list(paramMap);
        return finaFiles;
    }

    /**
     * 根据ID集合及类型 查询附件列表
     * @param keyIds
     * @param fileTableEnum
     * @return
     */
    public List<FinancialFile> getFilesByIds(List<Long> keyIds, FinancialFileTableEnumDto fileTableEnum){
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        String ids = keyIds.stream().map(item -> item.toString()).collect(Collectors.joining(","));
        paramMap.put("keyIds",ids);
        paramMap.put("keyCode",fileTableEnum.getCode());
        List<FinancialFile> finaFiles = financialFileMapper.list(paramMap);
        return finaFiles;
    }

    /**
     * 删除附件
     * @param id
     */
    public void deleteFileById(Long id){
        financialFileMapper.deleteByPrimaryKey(id);
    }
}
