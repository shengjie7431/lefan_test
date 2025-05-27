package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendPositionLevelApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.PositionLevelDto;
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
 * “职位级别”数据管理
 */
@Service
@ApiService(descript = "职位级别数据管理API")
public class BackendPositionLevelApiImpl extends BaseServiceImpl implements BackendPositionLevelApi {

    @Autowired
    private PositionLevelMapper positionLevelMapper;
    @Autowired
    private PositionInfoMapper positionInfoMapper;
    @Autowired
    private LevelPromotionMapper levelPromotionMapper;
    @Autowired
    private CommissionInfoMapper commissionInfoMapper;
    @Autowired
    private StudioCommissionInfoMapper studioCommissionInfoMapper;
    @Autowired
    private QualifiedManpowerMapper qualifiedManpowerMapper;
    @Autowired
    private UserPoLevelMapper userPoLevelMapper;
    /**
     * 职位级别数据管理列表查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "职位级别数据管理列表查询", value = "backend-position-level-list", apiParams = { })
    @Override
    public ApiResponse<List<PositionLevelDto>> getPositionLevelList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);

        //获取数据总值，包含条件查询
        int count = positionLevelMapper.selectCountPositionLevel(apiReq);

        //列表查询，包含条件查询
        List<PositionLevelDto> list = positionLevelMapper.selectPositionLevelList(apiReq);

        return new ApiResponse<List<PositionLevelDto>>(ApiMsgEnum.SUCCESS, count, list);

    }

    /**
     * 编辑职位级别数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "编辑职位级别数据", value = "backend-position-level-edit", apiParams = { })
    @Override
    public ApiResponse<List<PositionLevelDto>> positionLevelEdit(ApiRequest apiReq) {
        //根据ID获取单条数据
        PositionLevelDto positionLevelDto = positionLevelMapper.selectDtoByPrimaryKey(apiReq.getLong("id"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,positionLevelDto);

    }

    /**
     * 新增职位级别数据
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "新增职位级别数据", value = "backend-position-level-save", apiParams = { })
    @Override
    public ApiResponse positionLevelSave(ApiRequest apiReq) {
        String levelCode = apiReq.getString("levelCode");
        String levelDesc = apiReq.getString("levelDesc");
        Double baseWages = apiReq.getDouble("baseWages");
        Double evaWages = apiReq.getDouble("evaWages");
        Long parentId = apiReq.getLong("parentId");
        Long managerComrateId = apiReq.getLong("managerComrateId");

        PositionLevel positionLevel = new PositionLevel();

        positionLevel.setLevelCode(levelCode);
        positionLevel.setLevelDesc(levelDesc);
        positionLevel.setBaseWages(baseWages);
        positionLevel.setEvaWages(evaWages);
        positionLevel.setParentId(parentId);
        positionLevel.setManagerComrateId(managerComrateId);
        positionLevel.setCreateTime(new Date());
        positionLevel.setUpdateTime(new Date());

        int ret = positionLevelMapper.insertSelective(positionLevel);
        if(ret < 1){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);


    }

    /**
     * 更新职位级别数据
     * @param apiReq
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "更新职位级别数据", value = "backend-position-level-update", apiParams = {  })
    @Override
    public ApiResponse positionLevelUpdate(ApiRequest apiReq) {

        String levelCode = apiReq.getString("levelCode");
        String levelDesc = apiReq.getString("levelDesc");
        Double baseWages = apiReq.getDouble("baseWages");
        Double evaWages = apiReq.getDouble("evaWages");
        Long parentId = apiReq.getLong("parentId");
        Long managerComrateId = apiReq.getLong("managerComrateId");

        PositionLevel positionLevel = positionLevelMapper.selectByPrimaryKey(apiReq.getLong("id"));

        if (positionLevel != null){
            positionLevel.setLevelCode(levelCode);
            positionLevel.setLevelDesc(levelDesc);
            positionLevel.setBaseWages(baseWages);
            positionLevel.setEvaWages(evaWages);
            positionLevel.setParentId(parentId);
            positionLevel.setManagerComrateId(managerComrateId);
            positionLevel.setUpdateTime(new Date());
        }

        int ret = positionLevelMapper.updateByPrimaryKeySelective(positionLevel);
        if(ret < 1){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 删除职位级别数据
     * @param apiReq
     *
     * @return
     */

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "删除职位级别数据", value = "backend-position-level-delete", apiParams = {  })
    @Override
    public ApiResponse positionLevelDelete(ApiRequest apiReq) {
        //根据ID获取单条数据
        PositionLevel positionLevel = positionLevelMapper.selectByPrimaryKey(apiReq.getLong("id"));
        if (positionLevel !=null){
            positionLevelMapper.deleteByPrimaryKey(apiReq.getLong("id"));

            //查询“职级”：父级是此ID的数据：PositionLevel.parentid
            List<PositionLevel> positionLevelList = positionLevelMapper.searchListByParentId(apiReq.getLong("id"));
            for (int i = 0; i < positionLevelList.size(); i++) {
                PositionLevel positionLe = positionLevelList.get(i);
                positionLe.setParentId(null);
                positionLevelMapper.updateByPrimaryKey(positionLe);
            }

            //查询“职位”：职级是此ID的数据：PositionInfo.positionLevelId对应的数据   并更新
            List<PositionInfo> positionInfoList = positionInfoMapper.searchPositionInfoByLevelId(apiReq.getLong("id"));
            for (int i = 0; i < positionInfoList.size(); i++) {
                PositionInfo positionInfo = positionInfoList.get(i);
                positionInfo.setPositionLevelId(null);
                positionInfo.setPositionLevelName(null);
                positionInfoMapper.updateByPrimaryKey(positionInfo);
            }

            //查询“CC职位职级”：职级是此ID的数据：UserPoLevel.levelId 对应的数据   并更新
            List<UserPoLevel> userPoLevelList = userPoLevelMapper.searchUserPoLevelByLevelId(apiReq.getLong("id"));
            for (int i = 0; i < userPoLevelList.size(); i++) {
                UserPoLevel userPoLevel = userPoLevelList.get(i);
                userPoLevel.setLevelId(null);
                userPoLevelMapper.updateByPrimaryKey(userPoLevel);
            }

            //查询“职级晋升指标”：职级是此ID的数据：LevelPromotion.levelId对应的数据   并更新
            List<LevelPromotion>  levelPromotionList = levelPromotionMapper.searchLevelPromotionByLevelId(apiReq.getLong("id"));
            for (int i = 0; i < levelPromotionList.size(); i++) {
                LevelPromotion levelPromotion = levelPromotionList.get(i);
                levelPromotion.setLevelId(null);
                levelPromotion.setLevelCode(null);
                levelPromotionMapper.updateByPrimaryKey(levelPromotion);
            }

            //查询“佣金指标管理”：职级是此ID的数据：CommissionInfo.levelId对应的数据   并更新
            List<CommissionInfo> commissionInfoList = commissionInfoMapper.searchCommissionInfoByLevelId(apiReq.getLong("id"));
            for (int i = 0; i < commissionInfoList.size(); i++) {
                CommissionInfo commissionInfo = commissionInfoList.get(i);
                commissionInfo.setLevelId(null);
                commissionInfo.setLevelCode(null);
                commissionInfoMapper.updateByPrimaryKey(commissionInfo);
            }

            //查询“工作室佣金指标管理”：职级是此ID的数据：StudioCommissionInfo.levelId对应的数据   并更新
            List<StudioCommissionInfo> studioCommissionInfoList = studioCommissionInfoMapper.searchStudioCommissionInfoByLevelId(apiReq.getLong("id"));
            for (int i = 0; i < studioCommissionInfoList.size(); i++) {
                StudioCommissionInfo studioCommissionInfo = studioCommissionInfoList.get(i);
                studioCommissionInfo.setLevelId(null);
                studioCommissionInfo.setLevelCode(null);
                studioCommissionInfoMapper.updateByPrimaryKey(studioCommissionInfo);
            }

            //查询“合格人力奖金指标管理”：职级是此ID的数据：QualifiedManpower.levelId对应的数据   并更新
            List<QualifiedManpower> qualifiedManpowerList = qualifiedManpowerMapper.searchQualifiedManpowerByLevelId(apiReq.getLong("id"));
            for (int i = 0; i < qualifiedManpowerList.size(); i++) {
                QualifiedManpower qualifiedManpower = qualifiedManpowerList.get(i);
                qualifiedManpower.setLevelId(null);
                qualifiedManpower.setLevelCode(null);
                qualifiedManpowerMapper.updateByPrimaryKey(qualifiedManpower);
            }

            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

}
