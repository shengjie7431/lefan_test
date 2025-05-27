package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendLevelPromotionApi;
import com.lefancrm.apicenter.backendapi.BackendUserPoLevelApi;
import com.lefancrm.apicenter.dao.LevelPromotionMapper;
import com.lefancrm.apicenter.dao.PositionLevelMapper;
import com.lefancrm.apicenter.dao.UserPoLevelMapper;
import com.lefancrm.apicenter.dto.UserPoLevelDto;
import com.lefancrm.apicenter.model.LevelPromotion;
import com.lefancrm.apicenter.model.PositionLevel;
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
 * “职级晋升指标”数据管理
 */
@Service
@ApiService(descript = "职级晋升指标数据管理API")
public class BackendLevelPromotionApiImpl extends BaseServiceImpl implements BackendLevelPromotionApi {

    @Autowired
    private LevelPromotionMapper levelPromotionMapper;
    @Autowired
    private PositionLevelMapper positionLevelMapper;
    /**
     * 职级晋升指标数据管理列表查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "职级晋升指标数据管理列表查询", value = "backend-level-promotion-list", apiParams = { })
    @Override
    public ApiResponse<List<LevelPromotion>> getLevelPromotionList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);

        //获取数据总值，包含条件查询
        int count = levelPromotionMapper.selectCountLevelPromotion(apiReq);

        //列表查询，包含条件查询
        List<LevelPromotion> list = levelPromotionMapper.selectLevelPromotionList(apiReq);

        return new ApiResponse<List<LevelPromotion>>(ApiMsgEnum.SUCCESS, count, list);

    }

    /**
     * 编辑职级晋升指标数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "编辑职级晋升指标数据", value = "backend-level-promotion-edit", apiParams = { })
    @Override
    public ApiResponse<List<LevelPromotion>> levelPromotionEdit(ApiRequest apiReq) {
        //根据ID获取单条数据
        LevelPromotion levelPromotion = levelPromotionMapper.selectByPrimaryKey(apiReq.getLong("id"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,levelPromotion);

    }

    /**
     * 新增职级晋升指标数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "新增职级晋升指标数据", value = "backend-level-promotion-save", apiParams = { })
    @Override
    public ApiResponse levelPromotionSave(ApiRequest apiReq) {
        LevelPromotion levelPromotion = ConvertToBeanUtil.toBeanFromApiRequest(apiReq, LevelPromotion.class);

        //添加职位级别的名称（前端未传过来）
        PositionLevel positionLevel = positionLevelMapper.selectByPrimaryKey(apiReq.getLong("levelId"));
        levelPromotion.setLevelCode(positionLevel.getLevelCode());

        int ret = -1;
        if (levelPromotion != null){
            ret = levelPromotionMapper.insertSelective(levelPromotion);
        }
        if (ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }


    }

    /**
     * 更新职级晋升指标数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "更新职级晋升指标数据", value = "backend-level-promotion-update", apiParams = {  })
    @Override
    public ApiResponse levelPromotionUpdate(ApiRequest apiReq) {
        LevelPromotion levelPromotion = levelPromotionMapper.selectByPrimaryKey(apiReq.getLong("id"));

        //添加职位级别的名称（前端未传过来）
        PositionLevel positionLevel = positionLevelMapper.selectByPrimaryKey(apiReq.getLong("levelId"));
        apiReq.put("levelCode",positionLevel.getLevelCode());

        if (levelPromotion != null){
            levelPromotion = ConvertToBeanUtil.toBeanFromApiRequest(apiReq,levelPromotion);
        }
        int ret = levelPromotionMapper.updateByPrimaryKey(levelPromotion);
        if (ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    /**
     * 删除职级晋升指标数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "删除职级晋升指标数据", value = "backend-level-promotion-delete", apiParams = {  })
    @Override
    public ApiResponse levelPromotionDelete(ApiRequest apiReq) {
        //根据ID获取单条数据
        LevelPromotion levelPromotion = levelPromotionMapper.selectByPrimaryKey(apiReq.getLong("id"));
        if (levelPromotion !=null){
            levelPromotionMapper.deleteByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
