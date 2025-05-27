package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCommissionInfoApi;
import com.lefancrm.apicenter.backendapi.BackendPositionInfoApi;
import com.lefancrm.apicenter.dao.CommissionInfoMapper;
import com.lefancrm.apicenter.dao.PositionInfoMapper;
import com.lefancrm.apicenter.dao.PositionLevelMapper;
import com.lefancrm.apicenter.model.CommissionInfo;
import com.lefancrm.apicenter.model.PositionInfo;
import com.lefancrm.apicenter.model.PositionLevel;
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
 * Created by wangwei on 2018/3/26.
 * “佣金指标”数据管理
 */
@Service
@ApiService(descript = "佣金指标数据管理API")
public class BackendCommissionInfoApiImpl extends BaseServiceImpl implements BackendCommissionInfoApi {

    @Autowired
    private CommissionInfoMapper commissionInfoMapper;

    @Autowired
    private PositionLevelMapper positionLevelMapper;
    /**
     * 佣金指标数据管理列表查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "佣金指标数据管理列表查询", value = "backend-commission-info-list", apiParams = { })
    @Override
    public ApiResponse<List<CommissionInfo>> getCommissionInfoList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);

        //获取数据总值，包含条件查询
        int count = commissionInfoMapper.selectCountCommissionInfo(apiReq);

        //列表查询，包含条件查询
        List<CommissionInfo> list = commissionInfoMapper.selectCommissionInfoList(apiReq);

        return new ApiResponse<List<CommissionInfo>>(ApiMsgEnum.SUCCESS, count, list);

    }

    /**
     * 编辑佣金指标数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "编辑佣金指标数据", value = "backend-commission-info-edit", apiParams = { })
    @Override
    public ApiResponse<List<CommissionInfo>> commissionInfoEdit(ApiRequest apiReq) {
        //根据ID获取单条数据
        CommissionInfo commissionInfo = commissionInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,commissionInfo);

    }

    /**
     * 新增佣金指标数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "新增佣金指标数据", value = "backend-commission-info-save", apiParams = { })
    @Override
    public ApiResponse commissionInfoSave(ApiRequest apiReq) {

        CommissionInfo commissionInfo = ConvertToBeanUtil.toBeanFromApiRequest(apiReq, CommissionInfo.class);

        //添加职级的名称（前端未传过来）
        PositionLevel positionLevel = positionLevelMapper.selectByPrimaryKey(apiReq.getLong("levelId"));
        commissionInfo.setLevelCode(positionLevel.getLevelCode());

        int ret = -1;
        if (commissionInfo != null){
            ret = commissionInfoMapper.insertSelective(commissionInfo);
        }
        if (ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }


    }

    /**
     * 更新佣金指标数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "更新佣金指标数据", value = "backend-commission-info-update", apiParams = {  })
    @Override
    public ApiResponse commissionInfoUpdate(ApiRequest apiReq) {

        CommissionInfo commissionInfo = commissionInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));

        //添加职位的名称（前端未传过来）
        PositionLevel positionLevel = positionLevelMapper.selectByPrimaryKey(apiReq.getLong("levelId"));
        apiReq.put("levelCode",positionLevel.getLevelCode());

        if (commissionInfo != null){
            commissionInfo = ConvertToBeanUtil.toBeanFromApiRequest(apiReq,commissionInfo);
        }
        int ret = commissionInfoMapper.updateByPrimaryKey(commissionInfo);
        if (ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    /**
     * 删除佣金指标数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "删除佣金指标数据", value = "backend-commission-info-delete", apiParams = {  })
    @Override
    public ApiResponse commissionInfoDelete(ApiRequest apiReq) {
        //根据ID获取单条数据
        CommissionInfo commissionInfo = commissionInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
        if (commissionInfo !=null){
            commissionInfoMapper.deleteByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
