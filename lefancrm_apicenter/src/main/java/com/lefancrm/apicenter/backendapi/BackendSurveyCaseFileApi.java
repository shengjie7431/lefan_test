package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by lixianfeng on 2019/1/3.
 */
public interface BackendSurveyCaseFileApi {
    ApiResponse listCaseFile(ApiRequest apiRequest);
    ApiResponse uploadCaseFile(ApiRequest apiRequest);
    ApiResponse directionFileMidOK(ApiRequest apiRequest);
    ApiResponse directionFileDelete(ApiRequest apiRequest);

    ApiResponse execZip(ApiRequest apiRequest);//执行命令压缩文件
    ApiResponse sftpZip(ApiRequest apiRequest);//sftp压缩
    ApiResponse sftpZipByEntrust(ApiRequest apiRequest);//调查案件管理 一键下载 报告 及 方向附件
}
