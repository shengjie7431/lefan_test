package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendPositionInfoApi;
import com.lefancrm.apicenter.backendapi.BackendStudioCommissionInfoApi;
import com.lefancrm.apicenter.dao.PositionInfoMapper;
import com.lefancrm.apicenter.dao.PositionLevelMapper;
import com.lefancrm.apicenter.dao.StudioCommissionInfoMapper;
import com.lefancrm.apicenter.model.PositionInfo;
import com.lefancrm.apicenter.model.PositionLevel;
import com.lefancrm.apicenter.model.StudioCommissionInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wangwei on 2018/3/21.
 * “工作室佣金指标”数据管理
 */
@Service
@ApiService(descript = "工作室佣金指标数据管理API")
public class BackendStudioCommissionInfoApiImpl extends BaseServiceImpl implements BackendStudioCommissionInfoApi {

    @Autowired
    private StudioCommissionInfoMapper studioCommissionInfoMapper;

    @Autowired
    private PositionLevelMapper positionLevelMapper;
    /**
     * 工作室佣金指标数据管理列表查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "工作室佣金指标数据管理列表查询", value = "backend-studio-commission-info-list", apiParams = { })
    @Override
    public ApiResponse<List<StudioCommissionInfo>> getStudioCommissionInfoList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);

        //获取数据总值，包含条件查询
        int count = studioCommissionInfoMapper.selectCountStudioCommissionInfo(apiReq);

        //列表查询，包含条件查询
        List<StudioCommissionInfo> list = studioCommissionInfoMapper.selectStudioCommissionInfoList(apiReq);

        return new ApiResponse<List<StudioCommissionInfo>>(ApiMsgEnum.SUCCESS, count, list);

    }

    /**
     * 编辑工作室佣金指标数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "编辑工作室佣金指标数据", value = "backend-studio-commission-info-edit", apiParams = { })
    @Override
    public ApiResponse<List<StudioCommissionInfo>> studioCommissionInfoEdit(ApiRequest apiReq) {
        //根据ID获取单条数据
        StudioCommissionInfo studioCommissionInfo = studioCommissionInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,studioCommissionInfo);

    }

    /**
     * 新增工作室佣金指标数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "新增工作室佣金指标数据", value = "backend-studio-commission-info-save", apiParams = { })
    @Override
    public ApiResponse studioCommissionInfoSave(ApiRequest apiReq) {

        StudioCommissionInfo studioCommissionInfo = ConvertToBeanUtil.toBeanFromApiRequest(apiReq, StudioCommissionInfo.class);

        //添加职级的名称（前端未传过来）
        PositionLevel positionLevel = positionLevelMapper.selectByPrimaryKey(apiReq.getLong("levelId"));
        studioCommissionInfo.setLevelCode(positionLevel.getLevelCode());

        int ret = -1;
        if (studioCommissionInfo != null){
            ret = studioCommissionInfoMapper.insertSelective(studioCommissionInfo);
        }
        if (ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }


    }

    /**
     * 更新工作室佣金指标数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "更新工作室佣金指标数据", value = "backend-studio-commission-info-update", apiParams = {  })
    @Override
    public ApiResponse studioCommissionInfoUpdate(ApiRequest apiReq) {

        StudioCommissionInfo studioCommissionInfo = studioCommissionInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));

        //添加职级的名称（前端未传过来）
        PositionLevel positionLevel = positionLevelMapper.selectByPrimaryKey(apiReq.getLong("levelId"));
        apiReq.put("levelCode",positionLevel.getLevelCode());

        if (studioCommissionInfo != null){
            studioCommissionInfo = ConvertToBeanUtil.toBeanFromApiRequest(apiReq,studioCommissionInfo);
        }
        int ret = studioCommissionInfoMapper.updateByPrimaryKey(studioCommissionInfo);
        if (ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    /**
     * 删除工作室佣金指标数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "删除工作室佣金指标数据", value = "backend-studio-commission-info-delete", apiParams = {  })
    @Override
    public ApiResponse studioCommissionInfoDelete(ApiRequest apiReq) {
        //根据ID获取单条数据
        StudioCommissionInfo studioCommissionInfo = studioCommissionInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
        if (studioCommissionInfo !=null){
            studioCommissionInfoMapper.deleteByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
