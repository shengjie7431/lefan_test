package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendPositionPromotionApi;
import com.lefancrm.apicenter.backendapi.BackendUserPoLevelApi;
import com.lefancrm.apicenter.dao.PositionInfoMapper;
import com.lefancrm.apicenter.dao.PositionPromotionMapper;
import com.lefancrm.apicenter.dao.UserPoLevelMapper;
import com.lefancrm.apicenter.dto.UserPoLevelDto;
import com.lefancrm.apicenter.model.CaseAssessmentReport;
import com.lefancrm.apicenter.model.PositionInfo;
import com.lefancrm.apicenter.model.PositionPromotion;
import com.lefancrm.apicenter.model.UserPoLevel;
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
 * Created by wangwei on 2018/3/24.
 * “职位晋升指标”数据管理
 */
@Service
@ApiService(descript = "职位晋升指标数据管理API")
public class BackendPositionPromotionApiImpl extends BaseServiceImpl implements BackendPositionPromotionApi {

    @Autowired
    private PositionPromotionMapper positionPromotionMapper;
    @Autowired
    private PositionInfoMapper positionInfoMapper;

    /**
     * 职位晋升指标数据管理列表查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "职位晋升指标数据管理列表查询", value = "backend-position-promotion-list", apiParams = { })
    @Override
    public ApiResponse<List<PositionPromotion>> getPositionPromotionList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);

        //获取数据总值，包含条件查询
        int count = positionPromotionMapper.selectCountPositionPromotion(apiReq);

        //列表查询，包含条件查询
        List<PositionPromotion> list = positionPromotionMapper.selectPositionPromotionList(apiReq);

        return new ApiResponse<List<PositionPromotion>>(ApiMsgEnum.SUCCESS, count, list);

    }

    /**
     * 编辑职位晋升指标数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "编辑职位晋升指标数据", value = "backend-position-promotion-edit", apiParams = { })
    @Override
    public ApiResponse<List<PositionPromotion>> positionPromotionEdit(ApiRequest apiReq) {
        //根据ID获取单条数据
        PositionPromotion positionPromotion = positionPromotionMapper.selectByPrimaryKey(apiReq.getLong("id"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,positionPromotion);

    }

    /**
     * 新增职位晋升指标数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "新增职位晋升指标数据", value = "backend-position-promotion-save", apiParams = { })
    @Override
    public ApiResponse positionPromotionSave(ApiRequest apiReq) {

        PositionPromotion positionPromotion = ConvertToBeanUtil.toBeanFromApiRequest(apiReq, PositionPromotion.class);

        //添加职位的名称（前端未传过来）
        PositionInfo positionInfo = positionInfoMapper.selectByPrimaryKey(apiReq.getLong("positionId"));
        positionPromotion.setPositionName(positionInfo.getPositionName());

        int ret = -1;
        if (positionPromotion != null){
            ret = positionPromotionMapper.insertSelective(positionPromotion);
        }
        if (ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }

    }

    /**
     * 更新职位晋升指标数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "更新职位晋升指标数据", value = "backend-position-promotion-update", apiParams = {  })
    @Override
    public ApiResponse positionPromotionUpdate(ApiRequest apiReq) {

        PositionPromotion positionPromotion = positionPromotionMapper.selectByPrimaryKey(apiReq.getLong("id"));

        //添加职位的名称（前端未传过来）
        PositionInfo positionInfo = positionInfoMapper.selectByPrimaryKey(apiReq.getLong("positionId"));
        apiReq.put("positionName",positionInfo.getPositionName());

        if (positionPromotion != null){
            positionPromotion = ConvertToBeanUtil.toBeanFromApiRequest(apiReq,positionPromotion);
        }
        int ret = positionPromotionMapper.updateByPrimaryKey(positionPromotion);
        if (ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    /**
     * 删除职位晋升指标数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "删除职位晋升指标数据", value = "backend-position-promotion-delete", apiParams = {  })
    @Override
    public ApiResponse positionPromotionDelete(ApiRequest apiReq) {
        //根据ID获取单条数据
        PositionPromotion positionPromotion = positionPromotionMapper.selectByPrimaryKey(apiReq.getLong("id"));
        if (positionPromotion !=null){
            positionPromotionMapper.deleteByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
