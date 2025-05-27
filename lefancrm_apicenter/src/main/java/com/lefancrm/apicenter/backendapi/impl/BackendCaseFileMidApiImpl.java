package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCaseFileMidApi;
import com.lefancrm.apicenter.dao.CaseFileMidMapper;
import com.lefancrm.apicenter.dao.CommonEnumMapper;
import com.lefancrm.apicenter.model.BankInfo;
import com.lefancrm.apicenter.model.CaseFileMid;
import com.lefancrm.apicenter.model.CommonEnum;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2019-05-27
 * 单证信息
 */
@Service
@ApiService(descript = "单证信息API")
public class BackendCaseFileMidApiImpl extends BaseServiceImpl implements BackendCaseFileMidApi {

    @Autowired
    private CaseFileMidMapper caseFileMidMapper;
    @Autowired
    private CommonEnumMapper commonEnumMapper;
    /**
     * 单证信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "单证信息", value = "backend-case-file-mid-operate", apiParams = { })
    @Override
    public ApiResponse operate(ApiRequest apiReq) {

        String code = apiReq.getString("code");
        String files = apiReq.getString("files");
        if ("caseFileMidRemove".equals(code)) { //移动单证
//            Long catalogId = apiReq.getLong("catalogId");
            String catalog = apiReq.getString("catalog");
//            CommonEnum commonEnum = commonEnumMapper.s
            if (files != null) {
                String[] fileIds = files.split(",");
                for (String fileId : fileIds) {
                    CaseFileMid caseFileMid = caseFileMidMapper.selectByPrimaryKey(Long.valueOf(fileId));
                    if(caseFileMid !=null){

                        Map<String,Object> map = new HashMap<>();
                        map.put("parentEnumCode","fileCatelog");
                        map.put("enumCode",catalog);
                        CommonEnum commonEnum = commonEnumMapper.selectBill(map);
//                        int lastName = catalog.lastIndexOf(",");
//                        Long catalogId = Long.valueOf(catalog.substring(0,lastName));
//
//                        int firstName = catalog.lastIndexOf(",") + 1 ;
//                        String catalogName = catalog.substring(firstName,catalog.length());

//                        caseFileMid.setCatalogId(catalogId);
//                        caseFileMid.setCatalogName(catalogName);
                        caseFileMid.setCatalogId(Long.valueOf(catalog));
                        caseFileMid.setCatalogName(commonEnum.getEnumName());
                        caseFileMidMapper.updateByPrimaryKeySelective(caseFileMid);
                    }
                }

            }
        }
        //删除单证
        else if ("caseFileMidDelete".equals(code)) {
            if (files != null) {
                String[] fileIds = files.split(",");
                for (String fileId : fileIds) {
                    caseFileMidMapper.deleteFile(Long.valueOf(fileId));
                }
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

}
