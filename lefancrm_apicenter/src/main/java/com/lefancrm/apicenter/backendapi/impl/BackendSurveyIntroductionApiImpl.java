package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyIntroductionApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
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
 * Created by wangwei on 2018/12/17.
 * 平台介绍
 */
@Service
@ApiService(descript = "平台介绍API")
public class BackendSurveyIntroductionApiImpl extends BaseServiceImpl implements BackendSurveyIntroductionApi {

    @Autowired
    private SurveyTaskInfoMapper surveyTaskInfoMapper;
    @Autowired
    private CommonAreaMapper commonAreaMapper;
    @Autowired
    private SurveyCommonAreaPriceMapper surveyCommonAreaPriceMapper;

//    /**
//     * 测试
//     * @param apiReq
//     * @return
//     */
//    @SuppressWarnings("rawtypes")
//    @ApiMethod(descript = "测试", value = "select-test", apiParams = { })
//    @Override
//    public ApiResponse test(ApiRequest apiReq) {
//        //任务类型list
//        List<SurveyTaskInfo> surveyTaskInfoList = surveyTaskInfoMapper.list(apiReq);
//
//        //省
//        List<CommonArea> commonAreaP = commonAreaMapper.selectByType(1L);
//        Double p = 0D;
//        for (SurveyTaskInfo surveyTaskInfo : surveyTaskInfoList) {
//            if (surveyTaskInfo.getId() == 11){//深度案件
//                p = 3000D;
//            }else if(surveyTaskInfo.getId() == 12){//调病例
//                p = 250D;
//            }else if(surveyTaskInfo.getId() == 13){//医院排查
//                p = 300D;
//            }else if(surveyTaskInfo.getId() == 14){//走访医生
//                p = 100D;
//            }else if(surveyTaskInfo.getId() == 10){//医保调查
//                p = 500D;
//            }else if(surveyTaskInfo.getId() == 9){//走访调查
//                p = 300D;
//            }
//            for (CommonArea commonArea : commonAreaP) {
//
//                SurveyCommonAreaPrice price = new SurveyCommonAreaPrice();
//                price.setAreaId(commonArea.getAreaId());
//                price.setAreaName(commonArea.getAreaName());
//                price.setAreaLongname(commonArea.getAreaLongname());
//                price.setParentId(0);
//                price.setAreaType(commonArea.getAreaType().toString());
//                price.setAreaShortname(commonArea.getAreaShortname());
//                price.setAreaOrder(commonArea.getAreaOrder());
//                price.setLongPinyin(commonArea.getLongPinyin());
//                price.setDeleteFlag(commonArea.getDeleteFlag());
//                price.setShortPinyin(commonArea.getShortPinyin());
//                price.setIsShow(commonArea.getIsShow());
//                price.setTaskId(surveyTaskInfo.getId().intValue());
//                price.setTaskName(surveyTaskInfo.getName());
//                price.setPrice(p);
//                surveyCommonAreaPriceMapper.insertSelective(price);
//
//
//            }
//        }
//
//
//        //市
//        List<CommonArea> commonAreaC = commonAreaMapper.selectByType(2L);
//        Double c = 0D;
//        for (SurveyTaskInfo surveyTaskInfo : surveyTaskInfoList) {
//            if (surveyTaskInfo.getId() == 11){//深度案件
//                c = 3000D;
//            }else if(surveyTaskInfo.getId() == 12){//调病例
//                c = 300D;
//            }else if(surveyTaskInfo.getId() == 13){//医院排查
//                c = 350D;
//            }else if(surveyTaskInfo.getId() == 14){//走访医生
//                c = 100D;
//            }else if(surveyTaskInfo.getId() == 10){//医保调查
//                c = 500D;
//            }else if(surveyTaskInfo.getId() == 9){//走访调查
//                c = 400D;
//            }
//            for (CommonArea commonArea : commonAreaC) {
//
//                SurveyCommonAreaPrice price = new SurveyCommonAreaPrice();
//                price.setAreaId(commonArea.getAreaId());
//                price.setAreaName(commonArea.getAreaName());
//                price.setAreaLongname(commonArea.getAreaLongname());
//                price.setParentId(0);
//                price.setAreaType(commonArea.getAreaType().toString());
//                price.setAreaShortname(commonArea.getAreaShortname());
//                price.setAreaOrder(commonArea.getAreaOrder());
//                price.setLongPinyin(commonArea.getLongPinyin());
//                price.setDeleteFlag(commonArea.getDeleteFlag());
//                price.setShortPinyin(commonArea.getShortPinyin());
//                price.setIsShow(commonArea.getIsShow());
//                price.setTaskId(surveyTaskInfo.getId().intValue());
//                price.setTaskName(surveyTaskInfo.getName());
//                price.setPrice(c);
//                surveyCommonAreaPriceMapper.insertSelective(price);
//
//
//            }
//        }
//
//
//        //市
//        List<CommonArea> commonAreaD = commonAreaMapper.selectByType(3L);
//        Double d = 0D;
//        for (SurveyTaskInfo surveyTaskInfo : surveyTaskInfoList) {
//            if (surveyTaskInfo.getId() == 11){//深度案件
//                d = 3000D;
//            }else if(surveyTaskInfo.getId() == 12){//调病例
//                d = 350D;
//            }else if(surveyTaskInfo.getId() == 13){//医院排查
//                d = 450D;
//            }else if(surveyTaskInfo.getId() == 14){//走访医生
//                d = 100D;
//            }else if(surveyTaskInfo.getId() == 10){//医保调查
//                d = 500D;
//            }else if(surveyTaskInfo.getId() == 9){//走访调查
//                d = 500D;
//            }
//            for (CommonArea commonArea : commonAreaD) {
//
//                SurveyCommonAreaPrice price = new SurveyCommonAreaPrice();
//                price.setAreaId(commonArea.getAreaId());
//                price.setAreaName(commonArea.getAreaName());
//                price.setAreaLongname(commonArea.getAreaLongname());
//                price.setParentId(0);
//                price.setAreaType(commonArea.getAreaType().toString());
//                price.setAreaShortname(commonArea.getAreaShortname());
//                price.setAreaOrder(commonArea.getAreaOrder());
//                price.setLongPinyin(commonArea.getLongPinyin());
//                price.setDeleteFlag(commonArea.getDeleteFlag());
//                price.setShortPinyin(commonArea.getShortPinyin());
//                price.setIsShow(commonArea.getIsShow());
//                price.setTaskId(surveyTaskInfo.getId().intValue());
//                price.setTaskName(surveyTaskInfo.getName());
//                price.setPrice(d);
//                surveyCommonAreaPriceMapper.insertSelective(price);
//
//
//            }
//        }
//
//
//
//        return null;
//    }

}
