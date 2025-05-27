package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.CommonEnumDto;
import com.lefancrm.apicenter.model.CommonEnum;

import java.util.List;
import java.util.Map;

public interface CommonEnumMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommonEnum record);

    int insertSelective(CommonEnum record);

    CommonEnum selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommonEnum record);

    int updateByPrimaryKey(CommonEnum record);

    List<CommonEnum> selectEnumTreeList(Map<String, Object> params);

    /**
     * 查询评估阶段状态
     * @return
     */
    List<CommonEnum> selectAssessState();

    /**
     * 查询索赔阶段状态
     * @return
     */
    List<CommonEnum> selectClaimantState();

    /**
     * 查询案件资料文件类型
     * @return
     */
    List<CommonEnum> selectFileCatelogState();

    /**
     * 根据code查询机构类型
     * @param enumCode
     * @return
     */
    CommonEnum selectOrgState(String enumCode);

    List<CommonEnum> selectListByParentEnumCode(String enumCode);
    List<CommonEnum> selectListByParentEnumCodeAndMap(Map paramMap);

    int selectListByParentEnumCodePageCount(Map map);
    List<CommonEnum> selectListByParentEnumCodePage(Map map);

    CommonEnum selectBill(Map map);

    List<CommonEnumDto> getEnumDtos(Map map);

    List<CommonEnumDto> getItemDtos(Map map);


}