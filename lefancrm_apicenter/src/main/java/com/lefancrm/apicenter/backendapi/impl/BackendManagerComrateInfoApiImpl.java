package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCommissionInfoApi;
import com.lefancrm.apicenter.backendapi.BackendManagerComrateInfoApi;
import com.lefancrm.apicenter.dao.CommissionInfoMapper;
import com.lefancrm.apicenter.dao.ManagerComrateInfoMapper;
import com.lefancrm.apicenter.dao.PositionLevelMapper;
import com.lefancrm.apicenter.model.CommissionInfo;
import com.lefancrm.apicenter.model.ManagerComrateInfo;
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

import java.util.List;

/**
 * Created by wangwei on 2018/3/26.
 * “管理佣金指标和提成比例”数据管理
 */
@Service
@ApiService(descript = "管理佣金指标和提成比例数据管理API")
public class BackendManagerComrateInfoApiImpl extends BaseServiceImpl implements BackendManagerComrateInfoApi {

    @Autowired
    private ManagerComrateInfoMapper managerComrateInfoMapper;
    @Autowired
    private PositionLevelMapper positionLevelMapper;
    /**
     * 管理佣金指标和提成比例 数据管理列表查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "管理佣金指标和提成比例 数据管理列表查询", value = "backend-manager-comrate-info-list", apiParams = { })
    @Override
    public ApiResponse<List<ManagerComrateInfo>> getManagerComrateInfoList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);

        //获取数据总值，包含条件查询
        int count = managerComrateInfoMapper.selectManagerComrateInfo(apiReq);

        //列表查询，包含条件查询
        List<ManagerComrateInfo> list = managerComrateInfoMapper.selectManagerComrateInfoList(apiReq);

        return new ApiResponse<List<ManagerComrateInfo>>(ApiMsgEnum.SUCCESS, count, list);

    }

    /**
     * 编辑管理佣金指标和提成比例数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "编辑管理佣金指标和提成比例数据", value = "backend-manager-comrate-info-edit", apiParams = { })
    @Override
    public ApiResponse<List<ManagerComrateInfo>> managerComrateInfoEdit(ApiRequest apiReq) {
        //根据ID获取单条数据
        ManagerComrateInfo managerComrateInfo = managerComrateInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,managerComrateInfo);

    }

    /**
     * 新增管理佣金指标和提成比例数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "新增管理佣金指标和提成比例数据", value = "backend-manager-comrate-info-save", apiParams = { })
    @Override
    public ApiResponse managerComrateInfoSave(ApiRequest apiReq) {

        ManagerComrateInfo managerComrateInfo = ConvertToBeanUtil.toBeanFromApiRequest(apiReq, ManagerComrateInfo.class);

        int ret = -1;
        if (managerComrateInfo != null){
            ret = managerComrateInfoMapper.insertSelective(managerComrateInfo);
        }
        if (ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }


    }

    /**
     * 更新管理佣金指标和提成比例数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "更新管理佣金指标和提成比例数据", value = "backend-manager-comrate-info-update", apiParams = {  })
    @Override
    public ApiResponse managerComrateInfoUpdate(ApiRequest apiReq) {

        ManagerComrateInfo managerComrateInfo = managerComrateInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));

        if (managerComrateInfo != null){
            managerComrateInfo = ConvertToBeanUtil.toBeanFromApiRequest(apiReq,managerComrateInfo);
        }
        int ret = managerComrateInfoMapper.updateByPrimaryKey(managerComrateInfo);
        if (ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    /**
     * 删除管理佣金指标和提成比例数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "删除管理佣金指标和提成比例数据", value = "backend-manager-comrate-info-delete", apiParams = {  })
    @Override
    public ApiResponse managerComrateInfoDelete(ApiRequest apiReq) {
        //根据ID获取单条数据
        ManagerComrateInfo managerComrateInfo = managerComrateInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
        if (managerComrateInfo !=null){
            managerComrateInfoMapper.deleteByPrimaryKey(apiReq.getLong("id"));

            //查询“职级”：职级中的managerComrateId = id的数据  并更新
            List<PositionLevel> positionLevelList = positionLevelMapper.searchListByManagerComrateId(apiReq.getLong("id"));
            for (int i = 0; i < positionLevelList.size(); i++) {
                PositionLevel positionLevel = positionLevelList.get(i);
                positionLevel.setManagerComrateId(null);
                positionLevelMapper.updateByPrimaryKey(positionLevel);
            }

            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
