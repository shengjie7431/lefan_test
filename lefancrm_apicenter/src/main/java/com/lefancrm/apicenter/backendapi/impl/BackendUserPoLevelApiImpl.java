package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendUserPoLevelApi;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.dao.UserPoLevelMapper;
import com.lefancrm.apicenter.dto.UserPoLevelDto;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.model.UserPoLevel;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by wangwei on 2018/3/22.
 * “CC人员职级职位”数据管理
 */
@Service
@ApiService(descript = "CC人员职级职位数据管理API")
public class BackendUserPoLevelApiImpl extends BaseServiceImpl implements BackendUserPoLevelApi {

    @Autowired
    private UserPoLevelMapper userPoLevelMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    /**
     * CC人员职级职位数据管理列表查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "CC人员职级职位数据管理列表查询", value = "backend-user-po-level-list", apiParams = { })
    @Override
    public ApiResponse<List<UserPoLevelDto>> getUserPoLevelList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);

        //获取数据总值，包含条件查询
        int count = userPoLevelMapper.selectCountUserPoLevel(apiReq);

        //列表查询，包含条件查询
        List<UserPoLevelDto> list = userPoLevelMapper.selectUserPoLevelList(apiReq);

        return new ApiResponse<List<UserPoLevelDto>>(ApiMsgEnum.SUCCESS, count, list);

    }

    /**
     * 编辑CC人员职级职位数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "编辑CC人员职级职位数据", value = "backend-user-po-level-edit", apiParams = { })
    @Override
    public ApiResponse<List<UserPoLevelDto>> userPoLevelEdit(ApiRequest apiReq) {
        //根据ID获取单条数据
        UserPoLevelDto userPoLevelDto = userPoLevelMapper.selectDtoByPrimaryKey(apiReq.getLong("id"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,userPoLevelDto);

    }

    /**
     * 新增CC人员职级职位数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "新增CC人员职级职位数据", value = "backend-user-po-level-save", apiParams = { })
    @Override
    public ApiResponse userPoLevelSave(ApiRequest apiReq) {
        Long userId = apiReq.getLong("userId");
        Long positionId = apiReq.getLong("positionId");
        Long levelId = apiReq.getLong("levelId");
        Integer state = apiReq.getInt("state");
        Integer stateName = apiReq.getInt("stateName");

        UserPoLevel userPoLevel = new UserPoLevel();

        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        userPoLevel.setUserName(userInfo.getUserName());

        userPoLevel.setUserId(userId);
        userPoLevel.setPositionId(positionId);
        userPoLevel.setLevelId(levelId);
        //新增时 默认为绿色
        userPoLevel.setState(state);
        userPoLevel.setStateName(stateName);

        int ret = userPoLevelMapper.insertSelective(userPoLevel);
        if(ret < 1){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);


    }

    /**
     * 更新CC人员职级职位数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "更新CC人员职级职位数据", value = "backend-user-po-level-update", apiParams = {  })
    @Override
    public ApiResponse userPoLevelUpdate(ApiRequest apiReq) {
        Long userId = apiReq.getLong("userId");
        Long positionId = apiReq.getLong("positionId");
        Long levelId = apiReq.getLong("levelId");
        Integer state = apiReq.getInt("state");
        Integer stateName = apiReq.getInt("stateName");

        UserPoLevel userPoLevel = userPoLevelMapper.selectByPrimaryKey(apiReq.getLong("id"));

        if (userPoLevel != null){
            userPoLevel.setUserId(userId);
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
            userPoLevel.setUserName(userInfo.getUserName());

            userPoLevel.setPositionId(positionId);
            userPoLevel.setLevelId(levelId);
            userPoLevel.setState(state);
            userPoLevel.setStateName(stateName);
        }
        int ret = userPoLevelMapper.updateByPrimaryKeySelective(userPoLevel);
        if(ret < 1){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 删除CC人员职级职位数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "删除CC人员职级职位数据", value = "backend-user-po-level-delete", apiParams = {  })
    @Override
    public ApiResponse userPoLevelDelete(ApiRequest apiReq) {
        //根据ID获取单条数据
        UserPoLevel userPoLevel = userPoLevelMapper.selectByPrimaryKey(apiReq.getLong("id"));
        if (userPoLevel !=null){
            userPoLevelMapper.deleteByPrimaryKey(apiReq.getLong("id"));
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
