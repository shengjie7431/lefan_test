package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCommissionInfoApi;
import com.lefancrm.apicenter.backendapi.BackendQualifiedManpowerApi;
import com.lefancrm.apicenter.dao.CommissionInfoMapper;
import com.lefancrm.apicenter.dao.PositionLevelMapper;
import com.lefancrm.apicenter.dao.QualifiedManpowerMapper;
import com.lefancrm.apicenter.model.CommissionInfo;
import com.lefancrm.apicenter.model.PositionLevel;
import com.lefancrm.apicenter.model.QualifiedManpower;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangwei on 2018/3/26.
 * “合格人力奖金指标”数据管理
 */
@Service
@ApiService(descript = "合格人力奖金指标数据管理API")
public class BackendQualifiedManpowerApiImpl extends BaseServiceImpl implements BackendQualifiedManpowerApi {

    @Autowired
    private QualifiedManpowerMapper qualifiedManpowerMapper;

    @Autowired
    private PositionLevelMapper positionLevelMapper;
    /**
     * 合格人力奖金指标 数据管理列表查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "合格人力奖金指标数据管理列表查询", value = "backend-qualified-manpower-list", apiParams = { })
    @Override
    public ApiResponse<List<QualifiedManpower>> getQualifiedManpowerList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);

        //获取数据总值，包含条件查询
        int count = qualifiedManpowerMapper.selectCountQualifiedManpower(apiReq);

        //列表查询，包含条件查询
        List<QualifiedManpower> list = qualifiedManpowerMapper.selectQualifiedManpowerList(apiReq);

        return new ApiResponse<List<QualifiedManpower>>(ApiMsgEnum.SUCCESS, count, list);

    }

    /**
     * 编辑合格人力奖金指标数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "编辑合格人力奖金指标数据", value = "backend-qualified-manpower-edit", apiParams = { })
    @Override
    public ApiResponse<List<QualifiedManpower>> qualifiedManpowerEdit(ApiRequest apiReq) {
        //根据ID获取单条数据
        QualifiedManpower qualifiedManpower = qualifiedManpowerMapper.selectByPrimaryKey(apiReq.getLong("id"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,qualifiedManpower);

    }

    /**
     * 新增合格人力奖金指标数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "新增合格人力奖金指标数据", value = "backend-qualified-manpower-save", apiParams = { })
    @Override
    public ApiResponse qualifiedManpowerSave(ApiRequest apiReq) {

        QualifiedManpower qualifiedManpower = ConvertToBeanUtil.toBeanFromApiRequest(apiReq, QualifiedManpower.class);

        //添加职级的名称（前端未传过来）
        PositionLevel positionLevel = positionLevelMapper.selectByPrimaryKey(apiReq.getLong("levelId"));
        qualifiedManpower.setLevelCode(positionLevel.getLevelCode());

        int ret = -1;
        if (qualifiedManpower != null){
            ret = qualifiedManpowerMapper.insertSelective(qualifiedManpower);
        }
        if (ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }


    }

    /**
     * 更新合格人力奖金指标数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "更新合格人力奖金指标数据", value = "backend-qualified-manpower-update", apiParams = {  })
    @Override
    public ApiResponse qualifiedManpowerUpdate(ApiRequest apiReq) {

        QualifiedManpower qualifiedManpower = qualifiedManpowerMapper.selectByPrimaryKey(apiReq.getLong("id"));

        //添加职位的名称（前端未传过来）
        PositionLevel positionLevel = positionLevelMapper.selectByPrimaryKey(apiReq.getLong("levelId"));
        apiReq.put("levelCode",positionLevel.getLevelCode());

        if (qualifiedManpower != null){
            qualifiedManpower = ConvertToBeanUtil.toBeanFromApiRequest(apiReq,qualifiedManpower);
        }
        int ret = qualifiedManpowerMapper.updateByPrimaryKey(qualifiedManpower);
        if (ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    /**
     * 删除合格人力奖金指标数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "删除合格人力奖金指标数据", value = "backend-qualified-manpower-delete", apiParams = {  })
    @Override
    public ApiResponse qualifiedManpowerDelete(ApiRequest apiReq) {
        //根据ID获取单条数据
        QualifiedManpower qualifiedManpower = qualifiedManpowerMapper.selectByPrimaryKey(apiReq.getLong("id"));
        if (qualifiedManpower !=null){
            qualifiedManpowerMapper.deleteByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
