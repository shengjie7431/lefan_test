package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendPositionInfoApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
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
 * “职位”数据管理
 */
@Service
@ApiService(descript = "职位数据管理API")
public class BackendPositionInfoApiImpl extends BaseServiceImpl implements BackendPositionInfoApi {

    @Autowired
    private PositionInfoMapper positionInfoMapper;
    @Autowired
    private PositionLevelMapper positionLevelMapper;
    @Autowired
    private PositionPromotionMapper positionPromotionMapper;
    @Autowired
    private UserPoLevelMapper userPoLevelMapper;
    /**
     * 职位数据管理列表查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "职位数据管理列表查询", value = "backend-position-info-list", apiParams = { })
    @Override
    public ApiResponse<List<PositionInfo>> getPositionInfoList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);

        //获取数据总值，包含条件查询
        int count = positionInfoMapper.selectCountPositionInfo(apiReq);

        //列表查询，包含条件查询
        List<PositionInfo> list = positionInfoMapper.selectPositionInfoList(apiReq);

        return new ApiResponse<List<PositionInfo>>(ApiMsgEnum.SUCCESS, count, list);

    }

    /**
     * 编辑职位数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "编辑职位数据", value = "backend-position-info-edit", apiParams = { })
    @Override
    public ApiResponse<List<PositionInfo>> positionInfoEdit(ApiRequest apiReq) {
        //根据ID获取单条数据
        PositionInfo positionInfo = positionInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,positionInfo);

    }

    /**
     * 新增职位数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "新增职位数据", value = "backend-position-info-save", apiParams = { })
    @Override
    public ApiResponse positionInfoSave(ApiRequest apiReq) {

        String positionName = apiReq.getString("positionName");
        String positionDesc = apiReq.getString("positionDesc");
        Long positionLevelId = apiReq.getLong("positionLevelId");
        //获取职级对象
        PositionLevel positionLevel = positionLevelMapper.selectByPrimaryKey(positionLevelId);

        PositionInfo positionInfo = new PositionInfo();

        positionInfo.setPositionName(positionName);
        positionInfo.setPositionDesc(positionDesc);
        positionInfo.setPositionLevelName(positionLevel.getLevelCode());

        positionInfo.setCreateTime(new Date());
        positionInfo.setUpdateTime(new Date());
        positionInfo.setPositionLevelId(positionLevelId);

        int ret = positionInfoMapper.insertSelective(positionInfo);
        if(ret < 1){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);


    }

    /**
     * 更新职位数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "更新职位数据", value = "backend-position-info-update", apiParams = {  })
    @Override
    public ApiResponse positionInfoUpdate(ApiRequest apiReq) {

        String positionName = apiReq.getString("positionName");
        String positionDesc = apiReq.getString("positionDesc");
        Long positionLevelId = apiReq.getLong("positionLevelId");
        //获取职级对象
        PositionLevel positionLevel = positionLevelMapper.selectByPrimaryKey(positionLevelId);

        PositionInfo positionInfo = positionInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));

        positionInfo.setPositionName(positionName);
        positionInfo.setPositionDesc(positionDesc);
        positionInfo.setPositionLevelName(positionLevel.getLevelCode());
        positionInfo.setUpdateTime(new Date());
        positionInfo.setPositionLevelId(positionLevelId);

        int ret = positionInfoMapper.updateByPrimaryKeySelective(positionInfo);
        if(ret < 1){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 删除职位数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "删除职位数据", value = "backend-position-info-delete", apiParams = {  })
    @Override
    public ApiResponse positionInfoDelete(ApiRequest apiReq) {
        //根据ID获取单条数据
        PositionInfo positionInfo = positionInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
        if (positionInfo !=null){
            positionInfoMapper.deleteByPrimaryKey(apiReq.getLong("id"));


            //查询“职位晋升指标”：职位是此ID的数据：PositionPromotion.PositionId 对应的数据   并更新
            List<PositionPromotion> positionPromotionList = positionPromotionMapper.searchPositionPromotionByPositionId(apiReq.getLong("id"));
            for (int i = 0; i < positionPromotionList.size(); i++) {
                PositionPromotion positionPromotion = positionPromotionList.get(i);
                positionPromotion.setPositionId(null);
                positionPromotion.setPositionName(null);
                positionPromotionMapper.updateByPrimaryKey(positionPromotion);
            }

            //查询“CC职位职级”：职级是此ID的数据：UserPoLevel.PositionId 对应的数据   并更新
            List<UserPoLevel> userPoLevelList = userPoLevelMapper.searchUserPoLevelByPositionId(apiReq.getLong("id"));
            for (int i = 0; i < userPoLevelList.size(); i++) {
                UserPoLevel userPoLevel = userPoLevelList.get(i);
                userPoLevel.setLevelId(null);
                userPoLevelMapper.updateByPrimaryKey(userPoLevel);
            }

            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    /**
     * 根据职位id查询职级信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "根据职位id查询职级信息", value = "backend-search-info-by-positionId", apiParams = { })
    @Override
    public ApiResponse<List<PositionInfo>> searchInfoByPositionId(ApiRequest apiReq) {
        //根据ID获取单条数据
        PositionInfo positionInfo = positionInfoMapper.selectByPositionIdAndLevelId(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,positionInfo);

    }


    /**
     * 根据职级ID查询职位信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "根据职级ID查询职位信息", value = "backend-search-info-by-levelId", apiParams = { })
    @Override
    public ApiResponse<List<PositionInfo>> searchInfoByLevelId(ApiRequest apiReq) {
        //根据ID获取单条数据
        PositionInfo positionInfo = positionInfoMapper.selectByPositionIdAndLevelId(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,positionInfo);

    }

}
