package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.dto.OrgInfoDto;
import com.lefancrm.apicenter.model.OrgInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrgInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrgInfo record);

    int insertSelective(OrgInfo record);

    OrgInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrgInfo record);

    int updateByPrimaryKey(OrgInfo record);

    List<OrgInfo> queryOrgList(Map<String, Object> paramMap);
    List<OrgInfo> queryOrgListBackend(Map<String, Object> paramMap);
    int queryCountOrgListBackend(Map<String, Object> map);
//    int queryOrgListCount(Map<String, Object> paramMap);
//    List<OrgInfo> selectInsuranceCompany();
//
//    List<OrgInfo> queryOrgByParentId();

    List<OrgInfo> selectOrgInfoByRiskOrg(Map<String, Object> paramMap);

    /*
     * 查询机构-父级为1的数据
     */
    List<OrgInfo> searchOrgListByOrgParentId(Map<String, Object> paramMap);

    List<OrgInfo> selectOrgInfoListByIds(@Param("orgId") String orgId);

    List<Map<String,String>> selectCurrentYearAllMonth(Integer endTime);

    OrgInfo selectOrgInfoByCityId(Long cityId);

    List<OrgInfoDto> getDtos(Map map);

    OrgInfo test(@Param("orgId") String orgId);
}