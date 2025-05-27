package com.lefancrm.apicenter.fina.api.impl;

import com.lefancrm.apicenter.dao.CommonAreaMapper;
import com.lefancrm.apicenter.dao.SurveyConsignorMapper;
import com.lefancrm.apicenter.dao.SurveyFranchiseeMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.fina.api.BackendFinaOperationsManagementApi;
import com.lefancrm.apicenter.fina.dao.*;
import com.lefancrm.apicenter.fina.model.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.xml.crypto.Data;
import java.util.*;
import java.util.stream.Collectors;

@Service
@ApiService(descript = "垫付运营管理api")
public class BackendFinaOperationsManagementApiImpl extends BaseServiceImpl implements BackendFinaOperationsManagementApi {

    @Autowired
    private FinaSurveyConsignorEfficiencyModelMapper surveyConsignorEfficiencyModelMapper;
    @Autowired
    private FinaSurveyConsignorEfficiencyModelOrgMapper surveyConsignorEfficiencyModelOrgMapper;
    @Autowired
    private FinaSurveyConsignorEfficiencyModelAreaMapper surveyConsignorEfficiencyModelAreaMapper;
    @Autowired
    private FinaSurveyConsignorEfficiencyAreaCityMapper surveyConsignorEfficiencyAreaCityMapper;
    @Autowired
    private FinaSurveyConsignorEfficiencyModelInfoMapper surveyConsignorEfficiencyModelInfoMapper;
    @Autowired
    private FinaSurveyServiceTypeMapper surveyServiceTypeMapper;
    @Autowired
    private CommonAreaMapper commonAreaMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;
    @Autowired
    private FinaSurveyPriceModelMapper surveyPriceModelMapper;
    @Autowired
    private FinaSurveyPriceModelOrgMapper surveyPriceModelOrgMapper;
    @Autowired
    private FinaSurveyPriceMapper surveyPriceMapper;
    @Autowired
    private FinaSurveyPriceModelAreaCategoriesMapper surveyPriceModelAreaCategoriesMapper;
    @Autowired
    private FinaSurveyAreaCategoriesAreaCityMapper surveyAreaCategoriesAreaCityMapper;
    @Autowired
    private FinaTaskInfoMapper taskInfoMapper;
    @Autowired
    private FinaSignModelMapper signModelMapper;
    @Autowired
    private FinaModelEntrustMidMapper modelEntrustMidMapper;
    @Autowired
    private FinaHospitalInfoMapper hospitalInfoMapper;
    @Autowired
    private FinaSurveyCoefficientModelMapper surveyCoefficientModelMapper;
    @Autowired
    private FinaSurveyCoefficientAreaCityMapper surveyCoefficientAreaCityMapper;
    @Autowired
    private FinaSurveyCoefficientModelAreaMapper surveyCoefficientModelAreaMapper;
    @Autowired
    private FinaSurveyCoefficientModelInfoMapper surveyCoefficientModelInfoMapper;

    @Override
    @ApiMethod(needLogin = false, descript = "垫付委托时效模板", value = "backend-fina-manager-entrust-model")
    public ApiResponse list(ApiRequest apiRequest) {
        //不分页
        if (apiRequest.getString("menuType") == null) {
            this.setBackendPageSize(apiRequest);
        }
        String surveyCode = apiRequest.getString("surveyCode");
        Long currentUserId = getCurrentUserId(apiRequest);
        int count = 1;
        List list = new ArrayList();
        Map<String, Object> map = new HashMap<>();
        //委托时效模板
        if ("consignorEfficiencyModel".equals(surveyCode)) {
            count = surveyConsignorEfficiencyModelMapper.listSize(apiRequest);
            list = surveyConsignorEfficiencyModelMapper.list(apiRequest);
        }
        //委托时效模板对应的机构信息
        else if ("consignorEfficiencyModelOrg".equals(surveyCode)) {
            count = surveyConsignorEfficiencyModelOrgMapper.listSize(apiRequest);
            list = surveyConsignorEfficiencyModelOrgMapper.list(apiRequest);
        }
        //委托模板对应的区域列表
        else if ("efficiencyArea".equals(surveyCode)) {
            List<FinaSurveyConsignorEfficiencyModelArea> modelAreaList = surveyConsignorEfficiencyModelAreaMapper.list(apiRequest);
            for (FinaSurveyConsignorEfficiencyModelArea areas : modelAreaList) {
                map = new HashMap<>();
                map.put("areaCategoriesId", areas.getId());
                List<FinaSurveyConsignorEfficiencyAreaCity> areaCities = surveyConsignorEfficiencyAreaCityMapper.list(map);
                areas.setAreaCities(areaCities);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, modelAreaList);
        } else if ("consignorEfficiencyModelArea".equals(surveyCode)) {
            String fromType = apiRequest.getString("fromType");
            Long areaCategoriesId = apiRequest.getLong("id");
            List<CommonArea> commonAreas = commonAreaMapper.selectAreaByParentId(0L);
            for (CommonArea commonArea : commonAreas) {
                List<CommonArea> childrenList = commonAreaMapper.selectAreaCountByParentId(commonArea.getAreaId());
                commonArea.setAllChildrenNum((int) childrenList.stream().filter(e -> e.getAreaType() == 2).count());
                commonArea.setSelectedChildrenNum(0);
                if ("edit".equals(fromType)) {
                    String collect = childrenList.stream().filter(e -> e.getAreaType() == 2).map(e -> e.getAreaId().toString()).collect(Collectors.joining(","));
                    commonArea.setSelectedChildrenNum(surveyConsignorEfficiencyAreaCityMapper.selectAllSelectedCount(collect, areaCategoriesId));
                    commonArea.setSelectAreaIds(surveyConsignorEfficiencyAreaCityMapper.selectAllChildrenEfficiency2(commonArea.getAreaId(), areaCategoriesId));
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, commonAreas);
        } else if ("commonAreaEfficiency".equals(surveyCode)) {
            Long areaCategoriesId = apiRequest.getLong("priceModelCaId");
            Long modelId = apiRequest.getLong("modelId");
            List<CommonArea> commonAreas = surveyConsignorEfficiencyAreaCityMapper.selectAllChildrenEfficiency(Long.valueOf(apiRequest.getString("areaId")), areaCategoriesId, modelId);
            for (CommonArea commonArea : commonAreas) {
                List<CommonArea> allChildren = surveyConsignorEfficiencyAreaCityMapper.selectAllChildrenEfficiency(commonArea.getAreaId(), areaCategoriesId, modelId);
                if (null != areaCategoriesId) {//说明是修改
                    List<CommonArea> collect = allChildren.stream().filter(e -> e.getAreaCateGoriesId() != null && e.getAreaCateGoriesId().equals(areaCategoriesId)).collect(Collectors.toList());//属于当前区域选中的数量
                    int count1 = collect.size();
                    if (count1 == 0 && !commonArea.getSelected()) {
                        commonArea.setShowType(0);
                    }
                    if ((count1 > 0 && count1 < commonAreas.size()) || commonArea.getSelected()) {
                        commonArea.setShowType(1);
                    }
                    if (count1 == commonAreas.size()) {
                        commonArea.setShowType(2);
                    }
                }
                commonArea.setChildrens(allChildren);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, commonAreas);
        } else if ("surveyEfficiency1".equals(surveyCode)) {  //设置时效获取业务类型
            List<FinaSurveyServiceType> surveyServiceTypeList = surveyServiceTypeMapper.list(apiRequest);
            int c = surveyServiceTypeMapper.listSize(apiRequest);
            return new ApiResponse(ApiMsgEnum.SUCCESS, c, surveyServiceTypeList);
        } else if ("surveyEfficiency2".equals(surveyCode)) {
            Long modelId = apiRequest.getLong("modelId");
            //价格模板
            map = new HashMap<>();
            map.put("modelId", modelId);
            //区域类别
            List<FinaSurveyConsignorEfficiencyModelArea> surveyConsignorEfficiencyModelAreas = surveyConsignorEfficiencyModelAreaMapper.list(map);
            for (FinaSurveyConsignorEfficiencyModelArea areaCategory : surveyConsignorEfficiencyModelAreas) {
                map = new HashMap<>();
                map.put("areaCategoriesId", areaCategory.getId());
                //具体价格
                List<FinaSurveyConsignorEfficiencyModelInfo> surveyConsignorEfficiencyModelInfos = surveyConsignorEfficiencyModelInfoMapper.list(map);
                areaCategory.setModelInfos(surveyConsignorEfficiencyModelInfos);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, surveyConsignorEfficiencyModelAreas);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, list);
    }

    @Override
    @ApiMethod(needLogin = false, descript = "垫付委托时效模板相关操作", value = "backend-fina-manager-entrust-model-operate")
    public ApiResponse operate(ApiRequest apiRequest) {
        String surveyCode = apiRequest.getString("surveyCode");
        Long currentUserId = getCurrentUserId(apiRequest);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        if ("agingModelAdd".equals(surveyCode)) {  //新增委托模板
            FinaSurveyConsignorEfficiencyModel surveyConsignorEfficiencyModel = null;
            try {
                surveyConsignorEfficiencyModel = ConvertToBeanUtil.toBean(apiRequest, FinaSurveyConsignorEfficiencyModel.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            surveyConsignorEfficiencyModel.setCreateByName(userInfo.getUserName());// 发起人
            surveyConsignorEfficiencyModel.setCreateBy(currentUserId);// 发起人id
            surveyConsignorEfficiencyModel.setCreateTime(new Date());//创建时间
            surveyConsignorEfficiencyModel.setDeleteFlag(0);
            surveyConsignorEfficiencyModelMapper.insert(surveyConsignorEfficiencyModel);
        } else if ("agingModelUpd".equals(surveyCode)) { //修改委托模板
            Long id = apiRequest.getLong("id");
            FinaSurveyConsignorEfficiencyModel finaSurveyConsignorEfficiencyModel = surveyConsignorEfficiencyModelMapper.selectByPrimaryKey(id);
            if (finaSurveyConsignorEfficiencyModel != null) {
                finaSurveyConsignorEfficiencyModel.setName(apiRequest.getString("name"));
                finaSurveyConsignorEfficiencyModel.setEfficiencyAttr(apiRequest.getInt("efficiencyAttr"));
                surveyConsignorEfficiencyModelMapper.updateByPrimaryKey(finaSurveyConsignorEfficiencyModel);
            } else {
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        } else if ("agingModelDel".equals(surveyCode)) { //删除委托模板
            Long id = apiRequest.getLong("id");
            surveyConsignorEfficiencyModelMapper.deleteByPrimaryKey(id);
            surveyConsignorEfficiencyModelInfoMapper.deleteByModelId(id);
            surveyConsignorEfficiencyModelOrgMapper.deleteByModelId(id);
        } else if ("entrustOrgSet".equals(surveyCode)) {  //委托模板设置委托方
            Long modelId = apiRequest.getLong("efficiencyModelId");
            FinaSurveyConsignorEfficiencyModel finaSurveyConsignorEfficiencyModel = surveyConsignorEfficiencyModelMapper.selectByPrimaryKey(modelId);
            if (finaSurveyConsignorEfficiencyModel == null) {
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
            surveyConsignorEfficiencyModelOrgMapper.deleteByModelId(modelId);
            List<String> list = Arrays.asList(apiRequest.getString("roleIds").split(","));
            for (int i = 0; i < list.size(); i++) {
                Long entrustOrgId = Long.valueOf(list.get(i));
                SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(entrustOrgId);
                if (surveyConsignor == null) {
                    continue;
                }
                surveyConsignorEfficiencyModelOrgMapper.deleteByOrgId(entrustOrgId);
                FinaSurveyConsignorEfficiencyModelOrg finaSurveyConsignorEfficiencyModelOrg = new FinaSurveyConsignorEfficiencyModelOrg();
                finaSurveyConsignorEfficiencyModelOrg.setEfficiencyModelId(modelId);
                finaSurveyConsignorEfficiencyModelOrg.setEfficiencyModelName(finaSurveyConsignorEfficiencyModel.getName());
                finaSurveyConsignorEfficiencyModelOrg.setOrgId(surveyConsignor.getId());
                finaSurveyConsignorEfficiencyModelOrg.setOrgName(surveyConsignor.getName());
                surveyConsignorEfficiencyModelOrgMapper.insert(finaSurveyConsignorEfficiencyModelOrg);
            }
        } else if ("consignorEfficiencyModelArea".equals(surveyCode)) { //添加修改区域类别
            FinaSurveyConsignorEfficiencyModelArea surveyConsignorEfficiencyModelArea = surveyConsignorEfficiencyModelAreaMapper.selectByPrimaryKey(apiRequest.getLong("id"));
            FinaSurveyConsignorEfficiencyModel surveyConsignorEfficiencyModel = surveyConsignorEfficiencyModelMapper.selectByPrimaryKey(apiRequest.getLong("modelId"));
            if (surveyConsignorEfficiencyModelArea == null) {
                //保存
                try {
                    surveyConsignorEfficiencyModelArea = ConvertToBeanUtil.toBean(apiRequest, FinaSurveyConsignorEfficiencyModelArea.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                surveyConsignorEfficiencyModelArea.setModelId(surveyConsignorEfficiencyModel.getId());
                surveyConsignorEfficiencyModelArea.setModelName(surveyConsignorEfficiencyModel.getName());
                surveyConsignorEfficiencyModelAreaMapper.insert(surveyConsignorEfficiencyModelArea);

                //区域类别 对应 具体区域（城市）
                String cityIds = apiRequest.getString("cityIds");
                if (cityIds != null) {
                    ArrayList<String> cityId = new ArrayList<>(Arrays.asList(cityIds.split(",")));
                    HashSet citySet = new HashSet(cityId);
                    Iterator iterator = citySet.iterator();
                    while (iterator.hasNext()) {
                        String ro = iterator.next().toString().trim();
                        FinaSurveyConsignorEfficiencyAreaCity surveyConsignorEfficiencyAreaCity = new FinaSurveyConsignorEfficiencyAreaCity();
                        surveyConsignorEfficiencyAreaCity.setModelId(surveyConsignorEfficiencyModelArea.getModelId());
                        surveyConsignorEfficiencyAreaCity.setModelName(surveyConsignorEfficiencyModelArea.getModelName());
                        surveyConsignorEfficiencyAreaCity.setAreaCategoriesId(surveyConsignorEfficiencyModelArea.getId());
                        surveyConsignorEfficiencyAreaCity.setAreaCategoriesName(surveyConsignorEfficiencyModelArea.getName());
                        CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(Long.valueOf(ro));
                        if (commonArea != null) {
                            surveyConsignorEfficiencyAreaCity.setAreaId(commonArea.getAreaId());
                            surveyConsignorEfficiencyAreaCity.setAreaName(commonArea.getAreaName());
                            surveyConsignorEfficiencyAreaCity.setAreaLongname(commonArea.getAreaLongname());
                        }
                        surveyConsignorEfficiencyAreaCityMapper.insertSelective(surveyConsignorEfficiencyAreaCity);
                    }
                }
            } else {
                //修改
                try {
                    surveyConsignorEfficiencyModelArea = ConvertToBeanUtil.toBean(apiRequest, surveyConsignorEfficiencyModelArea);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                surveyConsignorEfficiencyModelAreaMapper.updateByPrimaryKey(surveyConsignorEfficiencyModelArea);
                if (StringUtils.isNotBlank(apiRequest.getString("first")) && "true".equals(apiRequest.getString("first"))){
                    //先删除已匹配的数据
                    surveyConsignorEfficiencyAreaCityMapper.deleteByAreaCategoriesId(surveyConsignorEfficiencyModelArea.getId(), surveyConsignorEfficiencyModelArea.getModelId());
                }
                //区域类别 对应 具体区域（城市）
                String cityIds = apiRequest.getString("cityIds");
                if (cityIds != null) {
                    ArrayList<String> cityId = new ArrayList<>(Arrays.asList(cityIds.split(",")));
                    HashSet citySet = new HashSet(cityId);
                    Iterator iterator = citySet.iterator();
                    while (iterator.hasNext()) {
                        String ro = iterator.next().toString().trim();
                        surveyConsignorEfficiencyAreaCityMapper.deleteByAreaId(Long.valueOf(ro), surveyConsignorEfficiencyModelArea.getModelId());
                        FinaSurveyConsignorEfficiencyAreaCity surveyConsignorEfficiencyAreaCity = new FinaSurveyConsignorEfficiencyAreaCity();
                        surveyConsignorEfficiencyAreaCity.setModelId(surveyConsignorEfficiencyModelArea.getModelId());
                        surveyConsignorEfficiencyAreaCity.setModelName(surveyConsignorEfficiencyModelArea.getModelName());
                        surveyConsignorEfficiencyAreaCity.setAreaCategoriesId(surveyConsignorEfficiencyModelArea.getId());
                        surveyConsignorEfficiencyAreaCity.setAreaCategoriesName(surveyConsignorEfficiencyModelArea.getName());
                        CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(Long.valueOf(ro));
                        if (commonArea != null) {
                            surveyConsignorEfficiencyAreaCity.setAreaId(commonArea.getAreaId());
                            surveyConsignorEfficiencyAreaCity.setAreaName(commonArea.getAreaName());
                        }
                        surveyConsignorEfficiencyAreaCityMapper.insertSelective(surveyConsignorEfficiencyAreaCity);
                    }
                }
            }
        } else if ("modelAreaDel".equals(surveyCode)) {
            Long id = apiRequest.getLong("id");
            FinaSurveyConsignorEfficiencyModelArea finaSurveyConsignorEfficiencyModelArea = surveyConsignorEfficiencyModelAreaMapper.selectByPrimaryKey(id);
            if (finaSurveyConsignorEfficiencyModelArea == null) {
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
            surveyConsignorEfficiencyAreaCityMapper.deleteByAreaCategoriesId(finaSurveyConsignorEfficiencyModelArea.getId(), finaSurveyConsignorEfficiencyModelArea.getModelId());
            surveyConsignorEfficiencyModelAreaMapper.deleteByPrimaryKey(id);
        } else if ("surveyEfficiency".equals(surveyCode)) {
            String days = apiRequest.getString("days");
            Long modelId = apiRequest.getLong("modelId");//
            Long serviceId = apiRequest.getLong("serviceId");
            String subServiceId = apiRequest.getString("subServiceId");
            String subServiceName = apiRequest.getString("subServiceName");
            Long areaCategoriesId = apiRequest.getLong("areaCategoriesId");
            Map<String, Object> map = new HashMap<>();
            map.put("efficiencyModelId", modelId);
            map.put("areaCategoriesId", areaCategoriesId);
            map.put("serviceId", serviceId);
            map.put("subServiceId", StringUtils.isEmpty(subServiceId) ? null : subServiceId);
            FinaSurveyConsignorEfficiencyModelInfo info = surveyConsignorEfficiencyModelInfoMapper.selectOne(map);
            if (info != null) {
                if (StringUtils.isEmpty(days)) {
                    surveyConsignorEfficiencyModelInfoMapper.deleteByPrimaryKey(info.getId());
                } else {
                    info.setDays(Integer.parseInt(days));
                    surveyConsignorEfficiencyModelInfoMapper.updateByPrimaryKey(info);
                }
            } else {
                if (!StringUtils.isEmpty(days)) {
                    info = new FinaSurveyConsignorEfficiencyModelInfo();
                    //模板信息
                    FinaSurveyConsignorEfficiencyModel model = surveyConsignorEfficiencyModelMapper.selectByPrimaryKey(modelId);
                    info.setEfficiencyModelId(model.getId());
                    info.setEfficiencyModelName(model.getName());
                    if (StringUtils.isEmpty(subServiceId)) {
                        //业务类型数据
                        FinaSurveyServiceType serviceType = surveyServiceTypeMapper.selectByPrimaryKey(serviceId);
                        info.setServiceId(serviceType.getId());
                        info.setServiceName(serviceType.getName());
                    } else {
                        info.setSubServiceId(Long.parseLong(subServiceId));
                        info.setSubServiceName(subServiceName);
                    }

                    info.setCityType(null);
                    info.setAreaCategoriesId(areaCategoriesId);
                    info.setDays(Integer.parseInt(days));
                    surveyConsignorEfficiencyModelInfoMapper.insert(info);
                }
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @Override
    @ApiMethod(needLogin = false, descript = "垫付委托时效模板获取详情", value = "backend-fina-manager-entrust-model-info")
    public ApiResponse ajaxData(ApiRequest apiRequest) {

        String surveyCode = apiRequest.getString("surveyCode");
        if ("consignorEfficiencyModel".equals(surveyCode)) { //获取详情
            Long modelId = apiRequest.getLong("id");
            FinaSurveyConsignorEfficiencyModel finaSurveyConsignorEfficiencyModel = surveyConsignorEfficiencyModelMapper.selectByPrimaryKey(modelId);
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, finaSurveyConsignorEfficiencyModel);
        } else if ("consignorEfficiencyModelArea".equals(surveyCode)) {
            Long id = apiRequest.getLong("id");
            FinaSurveyConsignorEfficiencyModelArea finaSurveyConsignorEfficiencyModelArea = surveyConsignorEfficiencyModelAreaMapper.selectByPrimaryKey(id);
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, finaSurveyConsignorEfficiencyModelArea);
        }else if ("getDays".equals(surveyCode)){
            Long hospitalId = apiRequest.getLong("hospitalId");
            FinaHospitalInfo finaHospitalInfo = hospitalInfoMapper.selectByPrimaryKey(hospitalId);
            if (finaHospitalInfo == null || finaHospitalInfo.getDistrictId() == null){
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,0);
            }
            Long districtId = finaHospitalInfo.getDistrictId();
            Long taskId = apiRequest.getLong("taskId");
            Long entrustOrgId = apiRequest.getLong("entrustOrgId");
            Integer days = surveyConsignorEfficiencyModelInfoMapper.getDays(entrustOrgId, taskId, districtId);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,Optional.ofNullable(days).orElse(0));
        }
        return null;
    }

    @Override
    @ApiMethod(needLogin = false, descript = "价格模板", value = "backend-fina-manager-price-model")
    public ApiResponse priceList(ApiRequest apiRequest) {
        //不分页
        if (apiRequest.getString("menuType") == null) {
            this.setBackendPageSize(apiRequest);
        }
        String surveyCode = apiRequest.getString("surveyCode");
        Long currentUserId = getCurrentUserId(apiRequest);
        int count = 1;
        List list = new ArrayList();
        Map<String, Object> map = new HashMap<>();

        //价格模板
        if ("priceModel".equals(surveyCode)) {
            count = surveyPriceModelMapper.listSize(apiRequest);
            list = surveyPriceModelMapper.list(apiRequest);
        }
        //价格模板对应机构
        else if ("priceModelOrg".equals(surveyCode)) {
            count = surveyPriceModelOrgMapper.listSize(apiRequest);
            list = surveyPriceModelOrgMapper.list(apiRequest);
        }
        //价格模板对应的区域列表
        else if ("areaCategories".equals(surveyCode)) {
            List<FinaSurveyPriceModelAreaCategories> categoriesList = surveyPriceModelAreaCategoriesMapper.list(apiRequest);
            for (FinaSurveyPriceModelAreaCategories surveyPriceModelAreaCategories : categoriesList) {
                map = new HashMap<>();
                map.put("areaCategoriesId", surveyPriceModelAreaCategories.getId());
                List<FinaSurveyAreaCategoriesAreaCity> areaCityList = surveyAreaCategoriesAreaCityMapper.list(map);
                surveyPriceModelAreaCategories.setAreaCitys(areaCityList);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, categoriesList);
        } else if ("priceModelAreaCategories".equals(surveyCode)) {
            String fromType = apiRequest.getString("fromType");
            Long areaCategoriesId = apiRequest.getLong("id");
            List<CommonArea> commonAreas = commonAreaMapper.selectAreaByParentId(0L);
            for (CommonArea commonArea : commonAreas) {
                List<CommonArea> childrenList = commonAreaMapper.selectAreaCountByParentId(commonArea.getAreaId());
                commonArea.setAllChildrenNum((int) childrenList.stream().filter(e -> e.getAreaType() == 2).count());
                commonArea.setSelectedChildrenNum(0);
                if ("edit".equals(fromType)) {
                    String collect = childrenList.stream().filter(e -> e.getAreaType() == 2).map(e -> e.getAreaId().toString()).collect(Collectors.joining(","));
                    commonArea.setSelectedChildrenNum(surveyAreaCategoriesAreaCityMapper.selectAllSelectedCount(collect, areaCategoriesId));
                    commonArea.setSelectAreaIds(surveyAreaCategoriesAreaCityMapper.selectAllChildren2(commonArea.getAreaId(), areaCategoriesId));
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, commonAreas);
        }
        //添加区域类别 获取区域id
        else if ("getAreaIdList".equals(surveyCode)) {
            try {
                String btnCode = apiRequest.getString("btnCode");
                Map<String, Map<String, Object>> resultMap = new HashMap<>();
                String cityIds = apiRequest.getString("cityIds");
                if (StringUtils.isNotBlank(cityIds)) {
                    String[] areaIds = cityIds.split(",");
                    for (String areaId : areaIds) {
                        Map<String, Object> map1 = new HashMap();
                        List<Long> commonAreas = commonAreaMapper.selectAllByType(Long.parseLong(areaId), Integer.parseInt(btnCode));
                        Integer c = commonAreaMapper.selectAllByTypeCount(Long.parseLong(areaId), Integer.parseInt(btnCode));
                        map1.put("k1", commonAreas);
                        map1.put("k2", c);
                        resultMap.put(areaId, map1);
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS, 1, resultMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("commonArea".equals(surveyCode)) {
            Long areaCategoriesId = apiRequest.getLong("priceModelCaId");
            Long modelId = apiRequest.getLong("modelId");
            List<CommonArea> commonAreas = surveyAreaCategoriesAreaCityMapper.selectAllChildren(Long.valueOf(apiRequest.getString("areaId")), areaCategoriesId, modelId);
            for (CommonArea commonArea : commonAreas) {
                List<CommonArea> allChildren = surveyAreaCategoriesAreaCityMapper.selectAllChildren(commonArea.getAreaId(), areaCategoriesId, modelId);
                if (null != areaCategoriesId) {//说明是修改
                    List<CommonArea> collect = allChildren.stream().filter(e -> e.getAreaCateGoriesId() != null && e.getAreaCateGoriesId().equals(areaCategoriesId)).collect(Collectors.toList());//属于当前区域选中的数量
                    int c = collect.size();
                    if (c == 0 && !commonArea.getSelected()) {
                        commonArea.setShowType(0);
                    }
                    if ((c > 0 && c < commonAreas.size()) || commonArea.getSelected()) {
                        commonArea.setShowType(1);
                    }
                    if (c == commonAreas.size()) {
                        commonArea.setShowType(2);
                    }
                }
                commonArea.setChildrens(allChildren);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, commonAreas);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, count, list);
    }

    @Override
    @ApiMethod(needLogin = false, descript = "价格模板相关操作", value = "backend-fina-manager-price-model-operate")
    public ApiResponse priceOperate(ApiRequest apiRequest) {
        String surveyCode = apiRequest.getString("surveyCode");
        Long id = apiRequest.getLong("id");
        Long currentUserId = getCurrentUserId(apiRequest);
        if ("priceModelAdd".equals(surveyCode)) {  //价格模板新增
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            FinaSurveyPriceModel finaSurveyPriceModel = new FinaSurveyPriceModel();
            finaSurveyPriceModel.setName(apiRequest.getString("name"));
            finaSurveyPriceModel.setType(apiRequest.getInt("type"));
            finaSurveyPriceModel.setUseObj(apiRequest.getInt("useObj"));
            finaSurveyPriceModel.setRemark(Optional.ofNullable(apiRequest.getString("remark")).orElse(""));
            finaSurveyPriceModel.setCreateTime(new Date());
            finaSurveyPriceModel.setCreateBy(userInfo.getUserId());
            finaSurveyPriceModel.setCreateByName(userInfo.getUserName());
            finaSurveyPriceModel.setDeleteFlag(0);
            surveyPriceModelMapper.insert(finaSurveyPriceModel);
        } else if ("priceModelUpd".equals(surveyCode)) {  //价格模板修改
            FinaSurveyPriceModel finaSurveyPriceModel = surveyPriceModelMapper.selectByPrimaryKey(id);
            finaSurveyPriceModel.setName(apiRequest.getString("name"));
            finaSurveyPriceModel.setType(apiRequest.getInt("type"));
            finaSurveyPriceModel.setUseObj(apiRequest.getInt("useObj"));
            finaSurveyPriceModel.setRemark(Optional.ofNullable(apiRequest.getString("remark")).orElse(""));
            surveyPriceModelMapper.updateByPrimaryKey(finaSurveyPriceModel);
        } else if ("priceModelDelete".equals(surveyCode)) { //价格模板删除
            surveyPriceModelMapper.deleteByPrimaryKey(id);
            surveyPriceModelOrgMapper.deleteByModelId(id);
            surveyPriceModelAreaCategoriesMapper.deleteByPriceModelId(id);
        } else if ("priceModelCopy".equals(surveyCode)) {//价格模板复制
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            FinaSurveyPriceModel surveyPriceModel = new FinaSurveyPriceModel();
            surveyPriceModel.setCreateBy(userInfo.getUserId());
            surveyPriceModel.setCreateByName(userInfo.getUserName());
            surveyPriceModel.setId(id);
            surveyPriceModelMapper.copyOneByPriceModelId(surveyPriceModel);
            surveyPriceModelAreaCategoriesMapper.copyOneByPriceModelId(id, surveyPriceModel.getId());
            List<FinaSurveyPriceModelAreaCategories> surveyPriceModelAreaCategories = surveyPriceModelAreaCategoriesMapper.selectByPriceModelId(surveyPriceModel.getId(), id);
            for (FinaSurveyPriceModelAreaCategories surveyPriceModelAreaCategory : surveyPriceModelAreaCategories) {
                surveyAreaCategoriesAreaCityMapper.copyOneByPriceModelId(surveyPriceModelAreaCategory, id);
                surveyPriceMapper.copyOneByPriceModelId(surveyPriceModelAreaCategory.getId(), surveyPriceModelAreaCategory.getOldAreaCategoriesId());
            }
        } else if ("priceAreaDel".equals(surveyCode)) {
            FinaSurveyPriceModelAreaCategories finaSurveyPriceModelAreaCategories = surveyPriceModelAreaCategoriesMapper.selectByPrimaryKey(id);
            if (finaSurveyPriceModelAreaCategories != null) {
                surveyAreaCategoriesAreaCityMapper.deleteByAreaCategoriesId(finaSurveyPriceModelAreaCategories.getId(), finaSurveyPriceModelAreaCategories.getPriceModelId());
                surveyPriceModelAreaCategoriesMapper.deleteByPrimaryKey(finaSurveyPriceModelAreaCategories.getId());
            }
        } else if ("priceEntrustOrgSet".equals(surveyCode)) { // 价格模板委托方机构设置
            FinaSurveyPriceModel surveyPriceModel = surveyPriceModelMapper.selectByPrimaryKey(id);
            if (surveyPriceModel == null) {
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
            int type = apiRequest.getInt("type");
            //先删除记录，再保存全新数据
            Map<String, Object> map = new HashMap<>();
            map.put("priceModelId", id);
            map.put("type", type);
            surveyPriceModelOrgMapper.deleteByInfo(map);
            //保存数据
            String buss = apiRequest.getString("roleIds");
            if (buss != null) {
                String[] role = buss.split(",");
                for (String ro : role) {
                    //如果该机构在其他模板中，需将其删除，加入此类数据中
                    map = new HashMap<>();
                    map.put("orgId", ro);
                    map.put("type", type);
                    FinaSurveyPriceModelOrg oldInfo = surveyPriceModelOrgMapper.selectOneByInfo(map);
                    if (oldInfo != null) {
                        surveyPriceModelOrgMapper.deleteByPrimaryKey(oldInfo.getId());
                    }

                    FinaSurveyPriceModelOrg surveyPriceModelOrg = new FinaSurveyPriceModelOrg();
                    //机构信息
                    SurveyConsignor consignor = surveyConsignorMapper.selectByPrimaryKey(Long.valueOf(ro));
                    if (consignor != null) {
                        surveyPriceModelOrg.setOrgId(consignor.getId());
                        surveyPriceModelOrg.setOrgName(consignor.getCompany());
                    }
                    surveyPriceModelOrg.setPriceModelId(surveyPriceModel.getId());
                    surveyPriceModelOrg.setPriceModelName(surveyPriceModel.getName());
                    surveyPriceModelOrg.setType(type);
                    surveyPriceModelOrgMapper.insertSelective(surveyPriceModelOrg);
                }
            }
        } else if ("priceSurveyOrgSet".equals(surveyCode)) {
            FinaSurveyPriceModel surveyPriceModel = surveyPriceModelMapper.selectByPrimaryKey(id);
            if (surveyPriceModel == null) {
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
            int type = apiRequest.getInt("type");
            //先删除记录，再保存全新数据
            Map<String, Object> map = new HashMap<>();
            map.put("priceModelId", id);
            map.put("type", type);
            surveyPriceModelOrgMapper.deleteByInfo(map);

            //保存数据
            String buss = apiRequest.getString("roleIds");
            if (buss != null) {
                String[] role = buss.split(",");
                for (String ro : role) {
                    //如果该机构在其他模板中，需将其删除，加入此类数据中
                    map = new HashMap<>();
                    map.put("orgId", ro);
                    map.put("type", type);
                    FinaSurveyPriceModelOrg oldInfo = surveyPriceModelOrgMapper.selectOneByInfo(map);
                    if (oldInfo != null) {
                        surveyPriceModelOrgMapper.deleteByPrimaryKey(oldInfo.getId());
                    }

                    FinaSurveyPriceModelOrg surveyPriceModelOrg = new FinaSurveyPriceModelOrg();
                    //机构信息
                    SurveyFranchisee franchisee = surveyFranchiseeMapper.selectByPrimaryKey(Long.valueOf(ro));
                    if (franchisee != null) {
                        surveyPriceModelOrg.setOrgId(franchisee.getId());
                        surveyPriceModelOrg.setOrgName(franchisee.getName());
                    }
                    surveyPriceModelOrg.setPriceModelId(surveyPriceModel.getId());
                    surveyPriceModelOrg.setPriceModelName(surveyPriceModel.getName());
                    surveyPriceModelOrg.setType(type);
                    surveyPriceModelOrgMapper.insertSelective(surveyPriceModelOrg);
                }
            }
        }
        //区域类别对应具体区域
        else if ("priceModelAreaCategories".equals(surveyCode)) {
            FinaSurveyPriceModelAreaCategories surveyPriceModelAreaCategories = surveyPriceModelAreaCategoriesMapper.selectByPrimaryKey(apiRequest.getLong("id"));
            FinaSurveyPriceModel surveyPriceModel = surveyPriceModelMapper.selectByPrimaryKey(apiRequest.getLong("priceModelId"));
            if (surveyPriceModelAreaCategories == null) {
                //保存
                try {
                    surveyPriceModelAreaCategories = ConvertToBeanUtil.toBean(apiRequest, FinaSurveyPriceModelAreaCategories.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                surveyPriceModelAreaCategories.setPriceModelId(surveyPriceModel.getId());
                surveyPriceModelAreaCategories.setPriceModelName(surveyPriceModel.getName());
                surveyPriceModelAreaCategoriesMapper.insert(surveyPriceModelAreaCategories);

                //区域类别 对应 具体区域（城市）
                String cityIds = apiRequest.getString("cityIds");
                if (cityIds != null) {
                    ArrayList<String> cityId = new ArrayList<>(Arrays.asList(cityIds.split(",")));
                    HashSet citySet = new HashSet(cityId);
                    Iterator iterator = citySet.iterator();
                    while (iterator.hasNext()) {
                        String ro = iterator.next().toString().trim();
                        surveyAreaCategoriesAreaCityMapper.deleteByAreaId(Long.valueOf(ro), surveyPriceModelAreaCategories.getPriceModelId());
                        FinaSurveyAreaCategoriesAreaCity surveyAreaCategoriesAreaCity = new FinaSurveyAreaCategoriesAreaCity();
                        surveyAreaCategoriesAreaCity.setPriceModelId(surveyPriceModelAreaCategories.getPriceModelId());
                        surveyAreaCategoriesAreaCity.setPriceModelName(surveyPriceModelAreaCategories.getPriceModelName());
                        surveyAreaCategoriesAreaCity.setAreaCategoriesId(surveyPriceModelAreaCategories.getId());
                        surveyAreaCategoriesAreaCity.setAreaCategoriesName(surveyPriceModelAreaCategories.getName());
                        CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(Long.valueOf(ro));
                        if (commonArea != null) {
                            surveyAreaCategoriesAreaCity.setAreaId(commonArea.getAreaId());
                            surveyAreaCategoriesAreaCity.setAreaName(commonArea.getAreaName());
                            surveyAreaCategoriesAreaCity.setAreaLongName(commonArea.getAreaLongname());
                        }
                        surveyAreaCategoriesAreaCityMapper.insertSelective(surveyAreaCategoriesAreaCity);
                    }
                }
            } else {
                //修改
                try {
                    surveyPriceModelAreaCategories = ConvertToBeanUtil.toBean(apiRequest, surveyPriceModelAreaCategories);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                surveyPriceModelAreaCategoriesMapper.updateByPrimaryKey(surveyPriceModelAreaCategories);
                if (StringUtils.isNotBlank(apiRequest.getString("first")) && apiRequest.getString("first").equals("true")) {
                    //先删除已匹配的数据
                    surveyAreaCategoriesAreaCityMapper.deleteByAreaCategoriesId(surveyPriceModelAreaCategories.getId(), surveyPriceModelAreaCategories.getPriceModelId());
                }
                //区域类别 对应 具体区域（城市）
                String cityIds = apiRequest.getString("cityIds");
                if (cityIds != null) {
                    ArrayList<String> cityId = new ArrayList<>(Arrays.asList(cityIds.split(",")));
                    HashSet citySet = new HashSet(cityId);
                    Iterator iterator = citySet.iterator();
                    while (iterator.hasNext()) {
                        String ro = iterator.next().toString().trim();
                        surveyAreaCategoriesAreaCityMapper.deleteByAreaId(Long.valueOf(ro), surveyPriceModelAreaCategories.getPriceModelId());
                        FinaSurveyAreaCategoriesAreaCity surveyAreaCategoriesAreaCity = new FinaSurveyAreaCategoriesAreaCity();
                        surveyAreaCategoriesAreaCity.setPriceModelId(surveyPriceModelAreaCategories.getPriceModelId());
                        surveyAreaCategoriesAreaCity.setPriceModelName(surveyPriceModelAreaCategories.getPriceModelName());
                        surveyAreaCategoriesAreaCity.setAreaCategoriesId(surveyPriceModelAreaCategories.getId());
                        surveyAreaCategoriesAreaCity.setAreaCategoriesName(surveyPriceModelAreaCategories.getName());
                        CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(Long.valueOf(ro));
                        if (commonArea != null) {
                            surveyAreaCategoriesAreaCity.setAreaId(commonArea.getAreaId());
                            surveyAreaCategoriesAreaCity.setAreaName(commonArea.getAreaName());
                        }
                        surveyAreaCategoriesAreaCityMapper.insertSelective(surveyAreaCategoriesAreaCity);
                    }
                }
            }
        } else if ("surveyPriceSet".equals(surveyCode)) {
            Long taskId = apiRequest.getLong("taskId");
            Long areaCategoriesId = apiRequest.getLong("areaCategoriesId");
            Long priceId = apiRequest.getLong("priceId");
            Double taskPrice = apiRequest.getDouble("taskPrice");
            if (priceId == null) { // 新增
                FinaTaskInfo finaTaskInfo = taskInfoMapper.selectByPrimaryKey(taskId);
                FinaSurveyPriceModelAreaCategories finaSurveyPriceModelAreaCategories = surveyPriceModelAreaCategoriesMapper.selectByPrimaryKey(areaCategoriesId);
                if (finaTaskInfo == null || finaSurveyPriceModelAreaCategories == null)
                    return new ApiResponse(ApiMsgEnum.FAIL);
                FinaSurveyPrice finaSurveyPrice = new FinaSurveyPrice();
                finaSurveyPrice.setAreaCategoriesId(areaCategoriesId);
                finaSurveyPrice.setAreaCategoriesName(finaSurveyPriceModelAreaCategories.getName());
                finaSurveyPrice.setTaskPrice(taskPrice);
                finaSurveyPrice.setTaskId(taskId);
                finaSurveyPrice.setTaskName(finaTaskInfo.getTaskName());
                finaSurveyPrice.setCreateTime(new Date());
                finaSurveyPrice.setDeleteFlag(0);
                surveyPriceMapper.insert(finaSurveyPrice);
            } else {
                FinaSurveyPrice finaSurveyPrice = surveyPriceMapper.selectByPrimaryKey(priceId);
                if (finaSurveyPrice == null) return new ApiResponse(ApiMsgEnum.FAIL);
                finaSurveyPrice.setTaskPrice(taskPrice);
                surveyPriceMapper.updateByPrimaryKey(finaSurveyPrice);
            }
        } else if ("copyPrice".equals(surveyCode)) {
            Long modelId = apiRequest.getLong("modelId");
            FinaSurveyConsignorEfficiencyModel surveyConsignorEfficiencyModel = surveyConsignorEfficiencyModelMapper.selectByPrimaryKey(modelId);
            surveyConsignorEfficiencyModelAreaMapper.deleteByModelId(modelId);
            Long selPriceModelId = apiRequest.getLong("selPriceModelId");
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("modelId", modelId);
            paramMap.put("modelName", surveyConsignorEfficiencyModel.getName());
            paramMap.put("selPriceModelId", selPriceModelId);
            surveyConsignorEfficiencyModelAreaMapper.copyPirce(paramMap);
            surveyConsignorEfficiencyAreaCityMapper.copyPirce(paramMap);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @Override
    @ApiMethod(needLogin = false, descript = "垫付价格模板获取数据", value = "backend-fina-manager-price-model-info")
    public ApiResponse priceAjaxData(ApiRequest apiRequest) {
        String surveyCode = apiRequest.getString("surveyCode");
        if ("priceModel".equals(surveyCode)) {
            Long id = apiRequest.getLong("id");
            FinaSurveyPriceModel finaSurveyPriceModel = surveyPriceModelMapper.selectByPrimaryKey(id);
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, finaSurveyPriceModel);
        } else if ("priceModelAreaCategories".equals(surveyCode)) {
            Long id = apiRequest.getLong("id");
            FinaSurveyPriceModelAreaCategories finaSurveyPriceModelAreaCategories = surveyPriceModelAreaCategoriesMapper.selectByPrimaryKey(id);
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, finaSurveyPriceModelAreaCategories);
        } else if ("priceTaskList".equals(surveyCode)) {
            List<FinaTaskInfo> finaTaskInfos = taskInfoMapper.selectList();
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, finaTaskInfos);
        } else if ("priceList".equals(surveyCode)) {
            Long priceModelId = apiRequest.getLong("priceModelId");
            //价格模板
            HashMap map = new HashMap<>();
            map.put("priceModelId", priceModelId);
            //区域类别
            List<FinaSurveyPriceModelAreaCategories> areaCategories = surveyPriceModelAreaCategoriesMapper.list(map);
            for (FinaSurveyPriceModelAreaCategories areaCategory : areaCategories) {
                map = new HashMap<>();
                map.put("areaCategoriesId", areaCategory.getId());
                //具体价格
                List<FinaSurveyPrice> surveyPrices = surveyPriceMapper.list(map);
                areaCategory.setPrices(surveyPrices);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, areaCategories);
        } else if ("copyPrice".equals(surveyCode)) {
            List<FinaSurveyPriceModel> surveyPriceModels = surveyPriceModelMapper.list(new HashMap());
            return new ApiResponse(ApiMsgEnum.SUCCESS, surveyPriceModels.size(), surveyPriceModels);
        } else if ("getPrice".equals(surveyCode)) {
            Long hospitalId = apiRequest.getLong("hospitalId");
            FinaHospitalInfo finaHospitalInfo = hospitalInfoMapper.selectByPrimaryKey(hospitalId);
            if (finaHospitalInfo == null || finaHospitalInfo.getDistrictId() == null){
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,0);
            }
            Long districtId = finaHospitalInfo.getDistrictId();
            Long taskId = apiRequest.getLong("taskId");
            Long surveyOrgId = apiRequest.getLong("surveyOrgId");
            Double money = surveyPriceMapper.getPrice(surveyOrgId, taskId, districtId);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1, Optional.ofNullable(money).orElse(0d));
        }
        return null;
    }

    @Override
    @ApiMethod(needLogin = false, descript = "签约模板", value = "backend-fina-manager-sign-model")
    public ApiResponse signList(ApiRequest apiRequest) {
        String surveyCode = apiRequest.getString("surveyCode");
        if ("signModel".equals(surveyCode)) {
            this.setBackendPageSize(apiRequest);
            HashMap<String, Object> paramMap = new HashMap();
            paramMap.put("modelName", apiRequest.getString("modelName"));
            paramMap.put("modelType", apiRequest.getInt("modelType"));
            paramMap.put("pageSize",apiRequest.getInt("pageSize"));
            paramMap.put("pageIndex",apiRequest.getInt("pageIndex"));
            List<FinaSignModel> finaSignModels = signModelMapper.selectByParam(paramMap);
            int count = signModelMapper.selectByParamCount(paramMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS, count, finaSignModels);
        } else if ("signModelOrg".equals(surveyCode)) {
            Long modelId = apiRequest.getLong("modelId");
            FinaSignModel finaSignModel = signModelMapper.selectByPrimaryKey(modelId);
            if (finaSignModel == null) return new ApiResponse(ApiMsgEnum.FAIL);
            List<FinaModelEntrustMid> finaModelEntrustMids = modelEntrustMidMapper.selectListByModelId(modelId);
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, finaModelEntrustMids);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @Override
    @ApiMethod(needLogin = false, descript = "签约模板操作", value = "backend-fina-manager-sign-model-operate")
    public ApiResponse signOperate(ApiRequest apiRequest) {
        String surveyCode = apiRequest.getString("surveyCode");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(apiRequest));
        if ("signModelAdd".equals(surveyCode)) {
            FinaSignModel finaSignModel = new FinaSignModel();
            finaSignModel.setModelName(apiRequest.getString("modelName"));
            finaSignModel.setModelType(apiRequest.getInt("modelType"));
            finaSignModel.setModelPath(apiRequest.getString("modelPath"));
            finaSignModel.setCreateBy(userInfo.getUserName());
            finaSignModel.setCreateTime(new Date());
            finaSignModel.setDeleteFlag(0);
            signModelMapper.insert(finaSignModel);
        } else if ("signModelDel".equals(surveyCode)) {
            Long signModelId = apiRequest.getLong("id");
            FinaSignModel finaSignModel = signModelMapper.selectByPrimaryKey(signModelId);
            if (finaSignModel != null) {
                finaSignModel.setDeleteFlag(1);
                signModelMapper.updateByPrimaryKey(finaSignModel);
            } else {
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        } else if ("signModelUpd".equals(surveyCode)) {
            Long signModelId = apiRequest.getLong("id");
            FinaSignModel finaSignModel = signModelMapper.selectByPrimaryKey(signModelId);
            if (finaSignModel != null) {
                finaSignModel.setModelName(apiRequest.getString("modelName"));
                finaSignModel.setModelType(apiRequest.getInt("modelType"));
                finaSignModel.setModelPath(apiRequest.getString("modelPath"));
                finaSignModel.setUpdateBy(userInfo.getUserName());
                finaSignModel.setUpdateTime(new Date());
                signModelMapper.updateByPrimaryKey(finaSignModel);
            } else {
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        } else if ("signModelOrgSet".equals(surveyCode)) {
            Long modelId = apiRequest.getLong("id");
            FinaSignModel finaSignModel = signModelMapper.selectByPrimaryKey(modelId);
            if (finaSignModel == null) return new ApiResponse(ApiMsgEnum.FAIL);
            modelEntrustMidMapper.deleteByModelId(modelId);
            //保存数据
            String buss = apiRequest.getString("roleIds");
            if (buss != null) {
                String[] role = buss.split(",");
                for (String ro : role) {
                    Long orgId = Long.valueOf(ro);
                    List<FinaModelEntrustMid> finaModelEntrustMids = modelEntrustMidMapper.selectByMtypeAndOrgId(finaSignModel.getModelType(), orgId);
                    for (FinaModelEntrustMid finaModelEntrustMid : finaModelEntrustMids) {
                        modelEntrustMidMapper.deleteByPrimaryKey(finaModelEntrustMid.getId());
                    }
//                    modelEntrustMidMapper.deleteByEntrustOrgId(orgId);
                    FinaModelEntrustMid finaModelEntrustMid = new FinaModelEntrustMid();
                    finaModelEntrustMid.setEntrustOrgId(orgId);
                    finaModelEntrustMid.setModelId(modelId);
                    modelEntrustMidMapper.insert(finaModelEntrustMid);
                }
            }
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @Override
    @ApiMethod(needLogin = false, descript = "价格模板相关操作", value = "backend-fina-manager-sign-model-info")
    public ApiResponse signAjaxData(ApiRequest apiRequest) {
        String surveyCode = apiRequest.getString("surveyCode");
        if ("signModel".equals(surveyCode)) {
            Long id = apiRequest.getLong("id");
            FinaSignModel finaSignModel = signModelMapper.selectByPrimaryKey(id);
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, finaSignModel);
        }
        return null;
    }

    @Override
    @ApiMethod(needLogin = false, descript = "系数模板", value = "backend-fina-manager-coefficient-model")
    public ApiResponse coefficientList(ApiRequest apiRequest) {
        String surveyCode = apiRequest.getString("surveyCode");
        if ("coefModel".equals(surveyCode)) {
            this.setBackendPageSize(apiRequest);
            HashMap<String, Object> paramMap = new HashMap();
            paramMap.put("modelName", apiRequest.getString("name"));
            paramMap.put("pageSize",apiRequest.getInt("pageSize"));
            paramMap.put("pageIndex",apiRequest.getInt("pageIndex"));
            List<FinaSurveyCoefficientModel> finaSurveyCoefficientModels = surveyCoefficientModelMapper.selectListByParam(paramMap);
            Integer count = surveyCoefficientModelMapper.selectListByParamCount(paramMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS, count, finaSurveyCoefficientModels);
        } else if ("surveyEfficiency1".equals(surveyCode)) {  //设置时效获取业务类型
            List<FinaSurveyServiceType> surveyServiceTypeList = surveyServiceTypeMapper.list(apiRequest);
            int c = surveyServiceTypeMapper.listSize(apiRequest);
            return new ApiResponse(ApiMsgEnum.SUCCESS, c, surveyServiceTypeList);
        } else if ("coefficientArea".equals(surveyCode)) {
            Long modelId = apiRequest.getLong("modelId");
            HashMap map = new HashMap<>();
            map.put("modelId", modelId);
            //区域类别
            List<FinaSurveyCoefficientModelArea> surveyCoefficientModelAreas = surveyCoefficientModelAreaMapper.list(map);
            for (FinaSurveyCoefficientModelArea areas : surveyCoefficientModelAreas) {
                map = new HashMap<>();
                map.put("areaCategoriesId", areas.getId());
                List<FinaSurveyCoefficientAreaCity> areaCities = surveyCoefficientAreaCityMapper.list(map);
                areas.setAreaCities(areaCities);
            }

            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, surveyCoefficientModelAreas);
        }else if ("commonAreaEfficiency".equals(surveyCode)) {
            Long areaCategoriesId = apiRequest.getLong("priceModelCaId");
            Long modelId = apiRequest.getLong("modelId");
            List<CommonArea> commonAreas = surveyCoefficientAreaCityMapper.selectAllChildrenEfficiency(Long.valueOf(apiRequest.getString("areaId")), areaCategoriesId, modelId);
            for (CommonArea commonArea : commonAreas) {
                List<CommonArea> allChildren = surveyCoefficientAreaCityMapper.selectAllChildrenEfficiency(commonArea.getAreaId(), areaCategoriesId, modelId);
                if (null != areaCategoriesId) {//说明是修改
                    List<CommonArea> collect = allChildren.stream().filter(e -> e.getAreaCateGoriesId() != null && e.getAreaCateGoriesId().equals(areaCategoriesId)).collect(Collectors.toList());//属于当前区域选中的数量
                    int count1 = collect.size();
                    if (count1 == 0 && !commonArea.getSelected()) {
                        commonArea.setShowType(0);
                    }
                    if ((count1 > 0 && count1 < commonAreas.size()) || commonArea.getSelected()) {
                        commonArea.setShowType(1);
                    }
                    if (count1 == commonAreas.size()) {
                        commonArea.setShowType(2);
                    }
                }
                commonArea.setChildrens(allChildren);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, commonAreas);
        }else if ("coefModelAreaCategories".equals(surveyCode)){
            String fromType = apiRequest.getString("fromType");
            Long areaCategoriesId = apiRequest.getLong("id");
            List<CommonArea> commonAreas = commonAreaMapper.selectAreaByParentId(0L);
            for (CommonArea commonArea : commonAreas) {
                List<CommonArea> childrenList = commonAreaMapper.selectAreaCountByParentId(commonArea.getAreaId());
                commonArea.setAllChildrenNum((int) childrenList.stream().filter(e -> e.getAreaType() == 2).count());
                commonArea.setSelectedChildrenNum(0);
                if ("edit".equals(fromType)) {
                    String collect = childrenList.stream().filter(e -> e.getAreaType() == 2).map(e -> e.getAreaId().toString()).collect(Collectors.joining(","));
                    commonArea.setSelectedChildrenNum(surveyCoefficientAreaCityMapper.selectAllSelectedCount(collect, areaCategoriesId));
                    commonArea.setSelectAreaIds(surveyCoefficientAreaCityMapper.selectAllChildrenEfficiency2(commonArea.getAreaId(), areaCategoriesId));
                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, commonAreas);
        }else if ("coefEfficiency".equals(surveyCode)) {
            Long modelId = apiRequest.getLong("modelId");
            //价格模板
            Map map = new HashMap<>();
            map.put("modelId", modelId);
            //区域类别
            List<FinaSurveyCoefficientModelArea> surveyCoefficientModelAreas = surveyCoefficientModelAreaMapper.list(map);
            for (FinaSurveyCoefficientModelArea areaCategory : surveyCoefficientModelAreas) {
                map = new HashMap<>();
                map.put("areaCategoriesId", areaCategory.getId());
                //具体价格
                List<FinaSurveyCoefficientModelInfo> surveyCoefficientModelInfos = surveyCoefficientModelInfoMapper.list(map);
                areaCategory.setModelInfos(surveyCoefficientModelInfos);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, surveyCoefficientModelAreas);
        }
        return null;
    }

    @Override
    @ApiMethod(needLogin = false, descript = "系数模板操作", value = "backend-fina-manager-coefficient-model-operate")
    public ApiResponse coefficientOperate(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        String surveyCode = apiRequest.getString("surveyCode");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        if ("coefModelAdd".equals(surveyCode)) {
            FinaSurveyCoefficientModel coefficientModel = new FinaSurveyCoefficientModel();
            coefficientModel.setName(apiRequest.getString("name"));
            coefficientModel.setCreateBy(userInfo.getUserId());
            coefficientModel.setCreateByName(userInfo.getUserName());
            coefficientModel.setCreateTime(new Date());
            coefficientModel.setDeleteFlag(0);
            surveyCoefficientModelMapper.insert(coefficientModel);
        } else if ("coefModelDel".equals(surveyCode)) {
            Long coefModelId = apiRequest.getLong("id");
            FinaSurveyCoefficientModel coefficientModel = surveyCoefficientModelMapper.selectByPrimaryKey(coefModelId);
            if (coefficientModel != null) {
                coefficientModel.setDeleteFlag(1);
                surveyCoefficientModelMapper.updateByPrimaryKey(coefficientModel);
            } else {
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        } else if ("coefModelUpd".equals(surveyCode)) {
            Long coefModelId = apiRequest.getLong("id");
            FinaSurveyCoefficientModel coefficientModel = surveyCoefficientModelMapper.selectByPrimaryKey(coefModelId);
            if (coefficientModel != null) {
                coefficientModel.setName(apiRequest.getString("name"));
                coefficientModel.setUpdateBy(userInfo.getUserId());
                coefficientModel.setUpdateTime(new Date());
                surveyCoefficientModelMapper.updateByPrimaryKey(coefficientModel);
            } else {
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        }else if ("coefAreaDel".equals(surveyCode)) {
            Long id = apiRequest.getLong("id");
            FinaSurveyCoefficientModelArea surveyCoefficientModelArea = surveyCoefficientModelAreaMapper.selectByPrimaryKey(id);
            if (surveyCoefficientModelArea != null) {
                surveyCoefficientModelAreaMapper.deleteByPrimaryKey(id);
                surveyCoefficientAreaCityMapper.deleteByAreaCategoriesId(surveyCoefficientModelArea.getId(), surveyCoefficientModelArea.getModelId());
            }
        }
        //区域类别对应具体区域
        else if ("coefModelAreaCategories".equals(surveyCode)) {
            FinaSurveyCoefficientModelArea surveyCoefficientModelArea = surveyCoefficientModelAreaMapper.selectByPrimaryKey(apiRequest.getLong("id"));
            FinaSurveyCoefficientModel coefficientModel = surveyCoefficientModelMapper.selectByPrimaryKey(apiRequest.getLong("modelId"));
            if (surveyCoefficientModelArea == null) {
                //保存
                try {
                    surveyCoefficientModelArea = ConvertToBeanUtil.toBean(apiRequest, FinaSurveyCoefficientModelArea.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                surveyCoefficientModelArea.setModelId(coefficientModel.getId());
                surveyCoefficientModelArea.setModelName(coefficientModel.getName());
                surveyCoefficientModelAreaMapper.insert(surveyCoefficientModelArea);

                //区域类别 对应 具体区域（城市）
                String cityIds = apiRequest.getString("cityIds");
                if (cityIds != null) {
                    ArrayList<String> cityId = new ArrayList<>(Arrays.asList(cityIds.split(",")));
                    HashSet citySet = new HashSet(cityId);
                    Iterator iterator = citySet.iterator();
                    while (iterator.hasNext()) {
                        String ro = iterator.next().toString().trim();
                        FinaSurveyCoefficientAreaCity surveyCoefficientAreaCity = new FinaSurveyCoefficientAreaCity();
                        surveyCoefficientAreaCity.setModelId(surveyCoefficientModelArea.getModelId());
                        surveyCoefficientAreaCity.setModelName(surveyCoefficientModelArea.getModelName());
                        surveyCoefficientAreaCity.setAreaCategoriesId(surveyCoefficientModelArea.getId());
                        surveyCoefficientAreaCity.setAreaCategoriesName(surveyCoefficientModelArea.getName());
                        CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(Long.valueOf(ro));
                        if (commonArea != null) {
                            surveyCoefficientAreaCity.setAreaId(commonArea.getAreaId());
                            surveyCoefficientAreaCity.setAreaName(commonArea.getAreaName());
                            surveyCoefficientAreaCity.setAreaLongname(commonArea.getAreaLongname());
                        }
                        surveyCoefficientAreaCityMapper.insertSelective(surveyCoefficientAreaCity);
                    }
                }
            } else {
                //修改
                try {
                    surveyCoefficientModelArea = ConvertToBeanUtil.toBean(apiRequest, FinaSurveyCoefficientModelArea.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                surveyCoefficientModelAreaMapper.updateByPrimaryKey(surveyCoefficientModelArea);
                if (StringUtils.isNotBlank(apiRequest.getString("first")) && "true".equals(apiRequest.getString("first"))){
                    //先删除已匹配的数据
                    surveyCoefficientAreaCityMapper.deleteByAreaCategoriesId(surveyCoefficientModelArea.getId(), surveyCoefficientModelArea.getModelId());
                }

                //区域类别 对应 具体区域（城市）
                String cityIds = apiRequest.getString("cityIds");
                if (cityIds != null) {
                    ArrayList<String> cityId = new ArrayList<>(Arrays.asList(cityIds.split(",")));
                    HashSet citySet = new HashSet(cityId);
                    Iterator iterator = citySet.iterator();
                    while (iterator.hasNext()) {
                        String ro = iterator.next().toString().trim();
                        surveyCoefficientAreaCityMapper.deleteByAreaId(Long.valueOf(ro), surveyCoefficientModelArea.getModelId());
                        FinaSurveyCoefficientAreaCity surveyCoefficientAreaCity = new FinaSurveyCoefficientAreaCity();
                        surveyCoefficientAreaCity.setModelId(surveyCoefficientModelArea.getModelId());
                        surveyCoefficientAreaCity.setModelName(surveyCoefficientModelArea.getModelName());
                        surveyCoefficientAreaCity.setAreaCategoriesId(surveyCoefficientModelArea.getId());
                        surveyCoefficientAreaCity.setAreaCategoriesName(surveyCoefficientModelArea.getName());
                        CommonArea commonArea = commonAreaMapper.selectByPrimaryKey(Long.valueOf(ro));
                        if (commonArea != null) {
                            surveyCoefficientAreaCity.setAreaId(commonArea.getAreaId());
                            surveyCoefficientAreaCity.setAreaName(commonArea.getAreaName());
                        }
                        surveyCoefficientAreaCityMapper.insertSelective(surveyCoefficientAreaCity);
                    }
                }
            }
        } else if ("coefEfficiency".equals(surveyCode)) {
            Long areaCategoriesId = apiRequest.getLong("areaCategoriesId");
            Long modelId = apiRequest.getLong("modelId");
            Double coef = apiRequest.getDouble("coef");
            FinaSurveyCoefficientModelArea surveyCoefficientModelArea = surveyCoefficientModelAreaMapper.selectByPrimaryKey(areaCategoriesId);
            if (surveyCoefficientModelArea != null){
                FinaSurveyCoefficientModelInfo finaSurveyCoefficientModelInfo = surveyCoefficientModelInfoMapper.selectByModelIdAndAreaId(modelId, areaCategoriesId);
                if (finaSurveyCoefficientModelInfo == null) {
                    finaSurveyCoefficientModelInfo = new FinaSurveyCoefficientModelInfo();
                    finaSurveyCoefficientModelInfo.setModelId(modelId);
                    finaSurveyCoefficientModelInfo.setModelName(surveyCoefficientModelArea.getModelName());
                    finaSurveyCoefficientModelInfo.setCoeff(coef);
                    finaSurveyCoefficientModelInfo.setAreaCategoriesId(areaCategoriesId);
                    finaSurveyCoefficientModelInfo.setAreaCategoriesName(surveyCoefficientModelArea.getName());
                    surveyCoefficientModelInfoMapper.insert(finaSurveyCoefficientModelInfo);
                }else {
                    finaSurveyCoefficientModelInfo.setCoeff(coef);
                    surveyCoefficientModelInfoMapper.updateByPrimaryKey(finaSurveyCoefficientModelInfo);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }else {
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @Override
    @ApiMethod(needLogin = false, descript = "系数模板数据", value = "backend-fina-manager-coefficient-model-info")
    public ApiResponse coefficientAjaxData(ApiRequest apiRequest) {
        String surveyCode = apiRequest.getString("surveyCode");
        if ("coefModel".equals(surveyCode)) {
            Long id = apiRequest.getLong("id");
            FinaSurveyCoefficientModel coefficientModel = surveyCoefficientModelMapper.selectByPrimaryKey(id);
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, coefficientModel);
        }else if ("coefModelArea".equals(surveyCode)) {
            Long id = apiRequest.getLong("id");
            FinaSurveyCoefficientModelArea surveyCoefficientModelArea = surveyCoefficientModelAreaMapper.selectByPrimaryKey(id);
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, surveyCoefficientModelArea);
        }else if ("coefModelAreaCategories".equals(surveyCode)) {
            Long id = apiRequest.getLong("id");
            FinaSurveyCoefficientModelArea surveyCoefficientModelArea = surveyCoefficientModelAreaMapper.selectByPrimaryKey(id);
            return new ApiResponse(ApiMsgEnum.SUCCESS, 1, surveyCoefficientModelArea);
        }
        return null;
    }

}
