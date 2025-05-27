package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendSurveyChannelApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyZhaAssessApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.SurveyAssignOrgDto;
import com.lefancrm.apicenter.dto.SurveyZhaOrgAssessUpdateDTO;
import com.lefancrm.apicenter.dto.zhaAssess.ZhaAssessDTO;
import com.lefancrm.apicenter.dto.zhaAssess.ZhaOrgDTO;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.task.ReTask;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.DecimalUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@ApiService(descript = "众安机构考核相关API")
public class BackendSurveyZhaAssessApiImpl extends BaseServiceImpl implements BackendSurveyZhaAssessApi {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SurveyZhaAssessMapper surveyZhaAssessMapper;
    @Autowired
    private SurveyZhaOrgAssessMapper surveyZhaOrgAssessMapper;

    @ApiMethod(needLogin = false,descript = "众安机构考核管理列表",value = "list-survey-check")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        setBackendPageSize(apiRequest);
        int count = surveyZhaAssessMapper.listSize(apiRequest);
        List<SurveyZhaAssess> list = surveyZhaAssessMapper.list(apiRequest);
        for (SurveyZhaAssess surveyZhaAssess : list) {
            if (surveyZhaAssess.getDerogationMoney() == null){
                surveyZhaAssess.setDerogationMoney(0D);
            }
            if (surveyZhaAssess.getEntrustSubmitMoney() == null){
                surveyZhaAssess.setEntrustSubmitMoney(0D);
            }
            if (surveyZhaAssess.getCaseNum() == null){
                surveyZhaAssess.setCaseNum(0);
            }
            if (surveyZhaAssess.getSunCaseNum() == null){
                surveyZhaAssess.setSunCaseNum(0);
            }
            if (surveyZhaAssess.getJsAmt() == null){
                surveyZhaAssess.setJsAmt(0D);
            }
            if (surveyZhaAssess.getPfAmt() == null){
                surveyZhaAssess.setPfAmt(0D);
            }

            //阳性率
            Double sunRate = 0D;
            if (surveyZhaAssess.getCaseNum() == 0){

            }else{
                sunRate = DecimalUtil.twoDecimalTOFourFromFive(new Double(surveyZhaAssess.getSunCaseNum()) / new Double(surveyZhaAssess.getCaseNum())  * 100);
            }
            surveyZhaAssess.setSunRate(sunRate);

            //投产比=减损金额 /（对应机构“委托方确认金额”+对应机构“委托方确认金额”*阳性率奖励比例+“减损奖励金”）
            //阳性率奖励比例
            Double yxjlRate = 0D;// todo 阳性率奖励
            if (sunRate < 45){
                yxjlRate = sunRate - 45;
            }else if (sunRate >=45 && sunRate < 47){
                yxjlRate = 3D;
            }else if (sunRate >=47 && sunRate < 49){
                yxjlRate = 8D;
            }else if (sunRate >=49 && sunRate < 51){
                yxjlRate = 13D;
            }else if (sunRate >=51 && sunRate < 53){
                yxjlRate = 18D;
            }else if (sunRate >=53 && sunRate < 55){
                yxjlRate = 23D;
            }else if (sunRate >=55){
                yxjlRate = 28D;
            }
            Double temp = (surveyZhaAssess.getEntrustSubmitMoney() + surveyZhaAssess.getEntrustSubmitMoney() * (yxjlRate / 100) + surveyZhaAssess.getJsAmt());
            Double rate = DecimalUtil.twoDecimalTOFourFromFive(surveyZhaAssess.getDerogationMoney() / (temp == 0 ? 1 : temp));//投产比
            surveyZhaAssess.setRate(rate);

            surveyZhaAssess.setPfAmtStr(new DecimalFormat("#.##").format(surveyZhaAssess.getPfAmt()));
            surveyZhaAssess.setJsAmtStr(new DecimalFormat("#.##").format(surveyZhaAssess.getJsAmt()));

        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    @ApiMethod(needLogin = false,descript = "众安机构考核操作管理",value = "operate-survey-check")
    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        String btnCode = apiRequest.getString("btnCode");
        Long currentUserId = getCurrentUserId(apiRequest);
        Long id = apiRequest.getLong("id");
        SurveyZhaAssess surveyZhaAssess = surveyZhaAssessMapper.selectByPrimaryKey(id);
        if ("org".equals(btnCode)){//机构列表
            Double tcb = surveyZhaAssess.getTcb() == null ? 0D : surveyZhaAssess.getTcb();

            List<ZhaOrgDTO> zhaOrgs = surveyZhaOrgAssessMapper.selectZhaOrgs(surveyZhaAssess.getId());
            List<SurveyZhaOrgAssess> surveyZhaOrgAssesses = surveyZhaOrgAssessMapper.selectFindCases(surveyZhaAssess.getId());

            int gsSunRate= 0;//公司整体阳性率
            long count = surveyZhaOrgAssesses.stream().filter(p -> p.getIsSun() == 1).count();
            if (surveyZhaOrgAssesses.size() > 0){
                gsSunRate = (int) Math.floor(DecimalUtil.twoDecimalTOFourFromFive(new Double(count) / new Double(surveyZhaOrgAssesses.size()) * 100));//向下取整
            }

            for (ZhaOrgDTO zhaOrg : zhaOrgs) {
                zhaOrg.setEff(DecimalUtil.twoDecimalTOFourFromFive(zhaOrg.getEff()));
                //机构阳性率
                if (zhaOrg.getCaseNum() == 0){
                    zhaOrg.setSunRate(0D);
                }else{
                    zhaOrg.setSunRate(DecimalUtil.twoDecimalTOFourFromFive(new Double(zhaOrg.getSunCaseNum()) / new Double(zhaOrg.getCaseNum())  * 100));
                }


                //减损率
                if (zhaOrg.getMoney1() == 0){
                    zhaOrg.setJsl(0D);
                }else{
                    zhaOrg.setJsl(DecimalUtil.twoDecimalTOFourFromFive(zhaOrg.getMoney1() / (zhaOrg.getMoney1() + zhaOrg.getPfAmt()) * 100));
                }


                //阳性费奖励
                int yxjlRate = 0;// 阳性费奖励
                if (gsSunRate < 45){
                    yxjlRate = gsSunRate - 45;
                }else if (gsSunRate >=45 && gsSunRate < 47){
                    yxjlRate = 3;
                }else if (gsSunRate >=47 && gsSunRate < 49){
                    yxjlRate = 8;
                }else if (gsSunRate >=49 && gsSunRate < 51){
                    yxjlRate = 13;
                }else if (gsSunRate >=51 && gsSunRate < 53){
                    yxjlRate = 18;
                }else if (gsSunRate >=53 && gsSunRate < 55){
                    yxjlRate = 23;
                }else if (gsSunRate >=55){
                    yxjlRate = 28;
                }

                //投产比=减损金额 /（对应机构“委托方确认金额”+对应机构“委托方确认金额”*阳性率奖励比例+“减损奖励金”）
                Double temp = (zhaOrg.getMoney2() + zhaOrg.getMoney2() * (new Double(yxjlRate) / 100) + zhaOrg.getJsAmt());
                Double money3 = DecimalUtil.twoDecimalTOFourFromFive(zhaOrg.getMoney1() / (temp == 0 ? 1 : temp));//投产比
                zhaOrg.setMoney3(money3);
                //zhaOrg.setMoney3(DecimalUtil.twoDecimalTOFourFromFive(zhaOrg.getMoney1() / zhaOrg.getMoney2() * 100));


                //3W以上
                List<SurveyZhaOrgAssess> collect = surveyZhaOrgAssesses.stream().filter(p -> p.getSurveyOrgId().toString().equals(zhaOrg.getSurveyOrgId().toString())).collect(Collectors.toList());
                long js3W = collect.stream().filter(k -> k.getDerogationMoney() >= 30000D).count();
                long pf3W = collect.stream().filter(k -> k.getPfAmt() >= 30000D).count();
                if (js3W + pf3W ==0){
                    zhaOrg.setGe3wRate(0D);
                }else{
                    Double ge3W = DecimalUtil.twoDecimalTOFourFromFive(new Double(js3W) / new Double((js3W + pf3W)) * 100);
                    zhaOrg.setGe3wRate(ge3W);
                }


                //5W以上
                collect = surveyZhaOrgAssesses.stream().filter(p -> p.getSurveyOrgId().toString().equals(zhaOrg.getSurveyOrgId().toString())).collect(Collectors.toList());
                long js5W = collect.stream().filter(k -> k.getDerogationMoney() >= 50000D).count();
                long pf5W = collect.stream().filter(k -> k.getPfAmt() >= 50000D).count();

                if (js5W + pf5W ==0){
                    zhaOrg.setGe5wRate(0D);
                }else{
                    Double ge5W = DecimalUtil.twoDecimalTOFourFromFive(new Double(js5W) / new Double((js5W + pf5W)) * 100);
                    zhaOrg.setGe5wRate(ge5W);
                }

                //时效奖励
                Double jslAvg = 0D;//公司减损率平均线
                double m1 = zhaOrgs.stream().mapToDouble(ZhaOrgDTO::getMoney1).sum();//拒付金额（减损金额）
                double m2 = zhaOrgs.stream().mapToDouble(ZhaOrgDTO::getPfAmt).sum();//赔付金额
                if (m1 + m2 == 0){
                    jslAvg = 0D;
                }else{
                    jslAvg = DecimalUtil.twoDecimalTOFourFromFive(m1 / (m1 + m2) * 100);
                }

                Double effMoney = 0D;
                if (zhaOrg.getEff() <= 6){

                }else if (zhaOrg.getEff() > 6 && zhaOrg.getEff() <= 6.5){
                    effMoney = zhaOrg.getSurveySubmitMoney() * 0.08;
                    if (zhaOrg.getJsl() >= jslAvg){
                        effMoney = effMoney / 2;
                    }
                }else if (zhaOrg.getEff() > 6.5){
                    effMoney = zhaOrg.getSurveySubmitMoney() * 0.15;
                    if (zhaOrg.getJsl() >= jslAvg){
                        effMoney = effMoney / 2;
                    }
                }
                zhaOrg.setEffMoney(DecimalUtil.twoDecimalTOFourFromFive(-effMoney));



                //机构阳性奖励费
                int orgSunRate = (int) Math.floor(zhaOrg.getSunRate());//阳性率考核
                Double checkSunMoney = 0D;//阳性奖励费
                if (zhaOrg.getMoney3() < tcb){
                    if (orgSunRate < 30){
                        checkSunMoney = zhaOrg.getSurveySubmitMoney() * (orgSunRate - 36) / 100 * 1.8;
                    }else if (orgSunRate >=30 && orgSunRate < 36){
                        checkSunMoney = zhaOrg.getSurveySubmitMoney() * (orgSunRate - 36) / 100 * 1.5;
                    }else if (orgSunRate >= 36){
                        checkSunMoney = zhaOrg.getSurveySubmitMoney() * (orgSunRate - 36) / 100 * 1.3;
                    }
                }else if (zhaOrg.getMoney3() >= tcb){
                    if (orgSunRate < 30){
                        checkSunMoney = zhaOrg.getSurveySubmitMoney() * (orgSunRate - 30) / 100;
                    }else if (orgSunRate >=30 && orgSunRate < 36){
                        Double n = zhaOrg.getMoney3() / (tcb == 0 ? 1 : tcb);
                        if (n > 1.5){
                            n = 1.5;
                        }
                        checkSunMoney = zhaOrg.getSurveySubmitMoney() * (orgSunRate - 30) / 100 * 1.3 * n;
                    }else if (orgSunRate >= 36){
                        Double n = zhaOrg.getMoney3() / (tcb == 0 ? 1 : tcb);
                        if (n > 1.5){
                            n = 1.5;
                        }
                        checkSunMoney = zhaOrg.getSurveySubmitMoney() * (orgSunRate - 30) / 100 * 1.8 * n;
                        if (checkSunMoney > (zhaOrg.getSurveySubmitMoney() * 0.28)){
                            checkSunMoney = zhaOrg.getSurveySubmitMoney() * 0.28;
                        }
                    }
                }
                zhaOrg.setCheckSunMoney(DecimalUtil.twoDecimalTOFourFromFive(checkSunMoney));

                

//                if (zhaOrg.getCaseNum() == 0){
//                    zhaOrg.setSunRate(0D);
//                    zhaOrg.setCheckSunMoney(0D);
//                }else{
//                    zhaOrg.setSunRate(DecimalUtil.twoDecimalTOFourFromFive(new Double(zhaOrg.getSunCaseNum()) / new Double(zhaOrg.getCaseNum())  * 100));
//                    //阳性率考核     阳性率计算向下取整。郑总提出。2020年11月2日
////                    Double checkMoney = zhaOrg.getSurveySubmitMoney() * (Math.floor(zhaOrg.getSunRate()) - 25) / 100;
//                    //2020年12月24日  郑总提出。   25改成35
//                    Double checkMoney = zhaOrg.getSurveySubmitMoney() * (Math.floor(zhaOrg.getSunRate()) - 35) / 100;
//                    zhaOrg.setCheckSunMoney(DecimalUtil.twoDecimalTOFourFromFive(checkMoney));
//                }



            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,zhaOrgs.size(),zhaOrgs);
        }else if ("num".equals(btnCode)){
            Map<String,Object> paramMap =  new HashMap<String,Object>();
            paramMap.put("surveyOrgId",apiRequest.getLong("surveyOrgId"));
            paramMap.put("isSun",apiRequest.getInt("isSun"));
            paramMap.put("zhaAssessId",surveyZhaAssess.getId());
            List<SurveyZhaOrgAssess> assess = surveyZhaOrgAssessMapper.select(paramMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS,assess.size(),assess);
        }else if ("del".equals(btnCode)){
            surveyZhaAssess.setDeleteFlag(1);
            surveyZhaAssessMapper.updateByPrimaryKey(surveyZhaAssess);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyZhaAssess);
    }

    @ApiMethod(needLogin = false,descript = "众安机构考核处理详情",value = "info-survey-check")
    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        Long currentUserId = getCurrentUserId(apiRequest);
        SurveyZhaAssess surveyZhaAssess = surveyZhaAssessMapper.selectByPrimaryKey(id);
        if (surveyZhaAssess == null) {
            surveyZhaAssess = new SurveyZhaAssess();
            String year = new SimpleDateFormat("yy").format(new Date());
            String month = new SimpleDateFormat("MM").format(DateUtils.getUpMonth());
            surveyZhaAssess.setListName(year + "年" + month + "月");
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyZhaAssess);
    }

    @ApiMethod(needLogin = false,descript = "立即创建",value = "ajax-data-survey-check")
    @Override
    public ApiResponse ajaxData(ApiRequest apiRequest) {
        String btnCode = apiRequest.getString("btnCode");
        Long currentUserId = getCurrentUserId(apiRequest);
        Long id = apiRequest.getLong("id");
        SurveyZhaAssess surveyZhaAssess = surveyZhaAssessMapper.selectByPrimaryKey(id);
        if (surveyZhaAssess == null){
            surveyZhaAssess = new SurveyZhaAssess();
        }
        if ("create".equals(btnCode)){
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            Map<String,Object> msgMap =  new HashMap<String,Object>();
            List<SurveyZhaOrgAssess> excelData = JSONArray.parseArray(apiRequest.getString("data"),SurveyZhaOrgAssess.class);
            if (excelData == null){
                return new ApiResponse(ApiMsgEnum.SURVEY_ZHA_EXCEL_ERROR);
            }
            if (excelData.size() == 0){
                return new ApiResponse(ApiMsgEnum.SURVEY_ZHA_EXCEL_NOT_DATA);
            }
            surveyZhaAssess.setListName(apiRequest.getString("listName"));
            surveyZhaAssess.setTcb(apiRequest.getDouble("tcb"));
            surveyZhaAssess.setOperateId(userInfo.getUserId());
            surveyZhaAssess.setOperateName(userInfo.getUserName());
            surveyZhaAssess.setOparateTime(new Date());
            surveyZhaAssess.setDeleteFlag(0);
            surveyZhaAssessMapper.insert(surveyZhaAssess);
            //插入子表
            Map<String,Object> paramMap =  new HashMap<String,Object>();
            paramMap.put("zhaAssessId",surveyZhaAssess.getId());
            paramMap.put("data",excelData);
            surveyZhaOrgAssessMapper.insertCreate(paramMap);

            StringBuffer notFindMsg = new StringBuffer();
            //差找未匹配到的数据
            String ids = "";
            List<SurveyZhaOrgAssess> notOrgAssesses = surveyZhaOrgAssessMapper.selectNotFindCases(surveyZhaAssess.getId());
            for (SurveyZhaOrgAssess orgAssess : notOrgAssesses) {
                notFindMsg.append(orgAssess.getClaimsNo() + ",");
                ids += orgAssess.getId()  + ",";
            }
            msgMap.put("notFindMsg",notFindMsg.toString());//没有匹配到的案件信息.返回给前端
            //删除未匹配到的记录
            if (!StringUtils.isEmpty(ids)){
                surveyZhaOrgAssessMapper.delNotFindCases(ids);
            }

            StringBuffer findMsg = new StringBuffer();
            List<SurveyZhaOrgAssess> orgAssesses = surveyZhaOrgAssessMapper.selectFindCases(surveyZhaAssess.getId());

            //每一条数据对应主机构。（阳性机构>方向多的机构>有无医保>初审通过时间）
            List<ZhaAssessDTO> tempData = surveyZhaOrgAssessMapper.selectZhaAssessDTOs(surveyZhaAssess.getId());



            List<SurveyZhaOrgAssessUpdateDTO> updateData = new ArrayList<>();
            for (SurveyZhaOrgAssess orgAssess : orgAssesses) {
                ZhaAssessDTO temp = null;
                List<ZhaAssessDTO> collect = tempData.stream().filter(e -> e.getClaimNo().equals(orgAssess.getClaimsNo())).collect(Collectors.toList());//案件匹配的所有机构
                List<ZhaAssessDTO> sunCollect = collect.stream().filter(e -> e.getSurveyInfoIdSun() != null).collect(Collectors.toList());//案件匹配到的阳性机构列表
                if (sunCollect.size() == 0) {//未匹配到阳性机构
                    if (collect.size() > 0) {//直接取第一条。  SQL已根据案件、方向数量、提交时间排序
                        temp = collect.get(0);
                    }
                }else{//该案件有阳性机构。从阳性机构列表取第一条
                    temp = sunCollect.get(0);
                }
                if (temp == null){
                    findMsg.append(orgAssess.getClaimsNo() + ",");
                    continue;
                }
                if (temp != null){
                    orgAssess.setSurveyOrgId(temp.getOrgId());
                    orgAssess.setSurveyOrgName(temp.getOrgName());
                    orgAssess.setSurveyNo(temp.getSurveyCaseNo());
                    orgAssess.setSurveyInfoId(temp.getSurveyInfoId());
                    orgAssess.setSurveyPerson(temp.getSurveyPerson());
                    orgAssess.setSurveySubmitMoney(temp.getSurveySubmitMoney());
                    orgAssess.setIsSun(orgAssess.getIsSun());
//                    surveyZhaOrgAssessMapper.updateByPrimaryKey(orgAssess);

                    SurveyZhaOrgAssessUpdateDTO updateDTO = new SurveyZhaOrgAssessUpdateDTO();
                    updateDTO.setId(orgAssess.getId());
                    updateDTO.setSurveyOrgId(temp.getOrgId());
                    updateDTO.setSurveyOrgName(temp.getOrgName());
                    updateDTO.setSurveyNo(temp.getSurveyCaseNo());
                    updateDTO.setSurveyInfoId(temp.getSurveyInfoId());
                    updateDTO.setSurveyPerson(temp.getSurveyPerson());
                    updateDTO.setSurveySubmitMoney(temp.getSurveySubmitMoney());
                    updateDTO.setIsSun(orgAssess.getIsSun());
                    updateData.add(updateDTO);
                }
            }
            if (updateData.size() > 0){
                surveyZhaOrgAssessMapper.updateData(updateData);
            }


            msgMap.put("findMsg",findMsg.toString());//未找到主机构的案件信息。返回给前端
            for (SurveyZhaOrgAssess orgAssess : orgAssesses) {
                if (orgAssess.getSurveySubmitMoney() == null){
                    orgAssess.setSurveySubmitMoney(0D);
                }
                if (orgAssess.getDerogationMoney() == null){
                    orgAssess.setDerogationMoney(0D);
                }
                if (orgAssess.getEntrustSubmitMoney() == null){
                    orgAssess.setEntrustSubmitMoney(0D);
                }
                if (orgAssess.getCaeEff() == null){
                    orgAssess.setCaeEff(0D);
                }

                if (orgAssess.getPfAmt() == null){
                    orgAssess.setPfAmt(0D);
                }

                if (orgAssess.getJsAmt() == null){
                    orgAssess.setJsAmt(0D);
                }
            }
            //更新主表案件数量、减损总额
            surveyZhaAssess.setCaseNum(orgAssesses.size());//案件数量
            surveyZhaAssess.setSunCaseNum(new Long(orgAssesses.stream().filter(e -> e.getIsSun() == 1).count()).intValue());//阳性案件数量
            surveyZhaAssess.setDerogationMoney(orgAssesses.stream().collect(Collectors.summingDouble(SurveyZhaOrgAssess ::getDerogationMoney)));//减损金额
            surveyZhaAssess.setEntrustSubmitMoney(orgAssesses.stream().collect(Collectors.summingDouble(SurveyZhaOrgAssess ::getEntrustSubmitMoney)));//委托方确认金额
            surveyZhaAssess.setSurveySubmitMoney(orgAssesses.stream().collect(Collectors.summingDouble(SurveyZhaOrgAssess ::getSurveySubmitMoney)));//调查方确认金额
            surveyZhaAssess.setCaeTotalEff(orgAssesses.stream().collect(Collectors.summingDouble(SurveyZhaOrgAssess::getCaeEff)));//总时效

            surveyZhaAssess.setPfAmt(orgAssesses.stream().collect(Collectors.summingDouble(SurveyZhaOrgAssess ::getPfAmt)));//赔付金额
            surveyZhaAssess.setJsAmt(orgAssesses.stream().collect(Collectors.summingDouble(SurveyZhaOrgAssess ::getJsAmt)));//减损奖励金
            if (surveyZhaAssess.getCaseNum() == 0){
                surveyZhaAssess.setCaeAvgEff(0D);
                surveyZhaAssess.setDeleteFlag(0);//如果没有案件导入成功。则直接删除主表
            }else{
                surveyZhaAssess.setCaeAvgEff(DecimalUtil.twoDecimalTOFourFromFive(surveyZhaAssess.getCaeTotalEff() / surveyZhaAssess.getCaseNum()));//平均案件时效
            }
            surveyZhaAssessMapper.updateByPrimaryKey(surveyZhaAssess);

            StringBuffer msg = new StringBuffer();
            msg.append("总条数：" + excelData.size() + "条，导入成功" + surveyZhaAssess.getCaseNum() + "条;");
            if (!StringUtils.isEmpty(notFindMsg.toString())){
                msg.append("未匹配到的案件编号：[" + notFindMsg.toString() + "]");
            }
            if (!StringUtils.isEmpty(findMsg.toString())){
                msg.append("未匹配到的主机构的编号：[" + findMsg.toString() + "]");
            }
            msgMap.put("msg",msg);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,msgMap);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }


}
