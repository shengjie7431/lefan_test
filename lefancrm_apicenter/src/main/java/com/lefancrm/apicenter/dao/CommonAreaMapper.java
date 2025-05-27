package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.CommonAreaDto;
import com.lefancrm.apicenter.model.AreaInfo;
import com.lefancrm.apicenter.model.CommonArea;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CommonAreaMapper {
    int deleteByPrimaryKey(Long areaId);

    int insert(CommonArea record);

    int insertSelective(CommonArea record);

    CommonArea selectByPrimaryKey(Long areaId);

    int updateByPrimaryKeySelective(CommonArea record);

    int updateByPrimaryKey(CommonArea record);

    List<CommonArea> selectByType(Long type);

    List<CommonArea> selectByParentId(Long parentId);

    List<CommonArea> selectByTypeRes(Long type);

    List<CommonArea> selectByParentIdRes(Long parentId);

    List<CommonArea> selectAll();

    List<Long> selectAllByType(Long areaId,Integer type);
    Integer selectAllByTypeCount(Long areaId,Integer type);

    List<CommonArea> selectResAll();

    List<CommonArea> selectAreaByAreaIdList(Map<String, Object> paramMap);

    List<CommonArea> selectAreaByParentId(Long parentId);
    List<CommonArea> selectAreaCountByParentId(Long parentId);


    List<AreaInfo> queryByIds(List<Long> list);

    int updateAreaStateAll(Integer isShow);

    List<CommonArea> selectAreaShowList(Integer isShow);


    CommonArea selectByInfo(Map<String, Object> map);

    List<CommonArea> selectListByCityType(Map<String, Object> map);

    //模糊查询
    List<CommonAreaDto> selectListByName(String areaName);

    List<CommonArea> selectAllChildren(@Param("areaId") Long areaId, @Param("getAreaCateGoriesId")  Long getAreaCateGoriesId, @Param("modelId") Long modelId);

    List<CommonArea> selectAllChildrenEfficiency(@Param("areaId") Long areaId, @Param("getAreaCateGoriesId")  Long getAreaCateGoriesId, @Param("modelId") Long modelId);

    List<CommonArea> selectAllChildrenChannel(@Param("areaId") Long areaId, @Param("getAreaCateGoriesId")  Long getAreaCateGoriesId, @Param("modelId") Long modelId);
    List<CommonArea> selectAllChildrenScore(@Param("areaId") Long areaId, @Param("getAreaCateGoriesId")  Long getAreaCateGoriesId, @Param("modelId") Long modelId);

    List<CommonArea> selectAllChildrenFranchiseeAreas(@Param("areaId") Long areaId, @Param("orgId") Long orgId);
    List<CommonArea> selectAllChildrenConsignorAreas(@Param("areaId") Long areaId, @Param("orgId") Long orgId);

   String selectAllChildren2(@Param("areaId") Long areaId,@Param("getAreaCateGoriesId")  Long getAreaCateGoriesId);

   String selectAllChildrenEfficiency2(@Param("areaId") Long areaId,@Param("getAreaCateGoriesId")  Long getAreaCateGoriesId);

   String selectAllChildrenChannel2(@Param("areaId") Long areaId,@Param("getAreaCateGoriesId")  Long getAreaCateGoriesId);

   String selectAllChildrenFranchiseeArea(@Param("areaId") Long areaId,@Param("orgId")  Long orgId);

   Map<String, Object> selectThreeId(String pName,String cName,String aName);

    CommonAreaDto matchAddress(@Param("name1") String name1,@Param("name2")  String name2);
    CommonArea matchAddress2(@Param("name1") String name1);
}