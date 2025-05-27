package com.lefancrm.apicenter.backendapi;


import com.lefancrm.apicenter.model.SurveyReimbursementFile;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * 费用报销登记文件表(SurveyReimbursementFile)表服务接口
 *
 * @author makejava
 * @since 2020-04-09 17:06:37
 */
public interface SurveyReimbursementFileApi {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SurveyReimbursementFile queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SurveyReimbursementFile> queryAllByLimit(int offset, int limit);


    /**
     * 通过主键删除数据
     *
     * @return 是否成功
     */
    ApiResponse deleteById(ApiRequest apiRequest);

}