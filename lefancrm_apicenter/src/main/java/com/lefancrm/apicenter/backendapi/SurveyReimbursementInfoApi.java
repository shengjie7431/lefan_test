package com.lefancrm.apicenter.backendapi;


import com.lefancrm.apicenter.model.SurveyReimbursementInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * 调查费用报销登记表(SurveyReimbursementInfo)表服务接口
 *
 * @author makejava
 * @since 2020-04-09 17:06:39
 */
public interface SurveyReimbursementInfoApi {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SurveyReimbursementInfo queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SurveyReimbursementInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param surveyReimbursementInfo 实例对象
     * @return 实例对象
     */
    SurveyReimbursementInfo insert(SurveyReimbursementInfo surveyReimbursementInfo);

    /**
     * 修改数据
     *
     * @param surveyReimbursementInfo 实例对象
     * @return 实例对象
     */
    SurveyReimbursementInfo update(SurveyReimbursementInfo surveyReimbursementInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    ApiResponse selectBySurveyDirectionId(ApiRequest apiRequest);

}