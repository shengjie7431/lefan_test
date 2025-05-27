package com.lefancrm.apicenter.util;

import com.lefancrm.apicenter.dto.SurveyAssignOrgDto;
import com.lefancrm.apicenter.dto.SurveyInvestigatorCaseDto;
import com.lefancrm.apicenter.dto.SurveyRiskCaseInfoDto;
import com.lefancrm.apicenter.model.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AgingDayUtil {

    /**
     * 计算案件时效
     * @param surveyRiskCaseInfo
     * @param efficiencyAttr
     * @return
     */
    public static int riskCaseInfoAgingDay(SurveyRiskCaseInfoDto surveyRiskCaseInfo, int efficiencyAttr){
        Date startTime = surveyRiskCaseInfo.getEntrustStartDate();//委托时间
        Date endTime = surveyRiskCaseInfo.getEntrustReportEndDate();//保司审核通过时间
        return GetWorkDay.calLeaveDays(startTime, endTime, efficiencyAttr);
    }

    /**
     * 计算案件时效
     * @param entrustTime
     * @param entrustReportEndTime
     * @param efficiencyAttr
     * @return
     */
    public static int riskCaseInfoAgingDay(Date entrustTime,Date entrustReportEndTime,int efficiencyAttr){
        SurveyRiskCaseInfoDto item = new SurveyRiskCaseInfoDto();
        item.setEntrustStartDate(entrustTime);
        item.setEntrustReportEndDate(entrustReportEndTime);
        return riskCaseInfoAgingDay(item,efficiencyAttr);
    }

    /**
     * 计算机构时效 复数则表示超时
     * @param item
     * @return
     */
    public static int orgAgingDay(SurveyAssignOrgDto item,double surveyOrgAgingDay){
        int efficiencyAttr = item.getEfficiencyAttr() ;//时效设置（1：工作日；2、自然日）
        //分配给机构时间
        Date createTime = item.getCreateTime()==null ? new Date() : item.getCreateTime();
        //机构截止时间
        Date endTime = item.getOrgEndTime()==null ? new Date() : item.getOrgEndTime();
        //提交初审通过时间
        Date commitDate = item.getReportDate();
        int days = 0;
        if(commitDate != null){ //已提交
//            days = GetWorkDay.calLeaveDays(createTime, commitDate,efficiencyAttr);
            days = (int) surveyOrgAgingDay;
//            if(!commitDate.before(endTime)){
//                days = -days;
//            }
//            if (new SimpleDateFormat("yyyyMMdd").format(createTime).equals(new SimpleDateFormat("yyyyMMdd").format(commitDate))){
//                days = 0;
//            }
//            if (new SimpleDateFormat("yyyyMMdd").format(commitDate).equals(new SimpleDateFormat("yyyyMMdd").format(endTime))){
//                days = Math.abs(days);
//            }
        }else{//未提交
            days = GetWorkDay.calLeaveDays(new Date(),endTime,efficiencyAttr);
        }
        return days;
    }

    public static int orgAgingDay(SurveyAssignOrg surveyAssignOrg,int efficiencyAttr,double v){
        SurveyAssignOrgDto surveyAssignOrgDto = new SurveyAssignOrgDto();
        surveyAssignOrgDto.setCreateTime(surveyAssignOrg.getCreateTime());
        surveyAssignOrgDto.setOrgEndTime(surveyAssignOrg.getOrgEndTime());
        surveyAssignOrgDto.setReportDate(surveyAssignOrg.getReportDate());
        surveyAssignOrgDto.setEfficiencyAttr(efficiencyAttr);
        return orgAgingDay(surveyAssignOrgDto,v);
    }

    /**
     * 调查员时效
     * @param item
     * @return
     */
    public static int surveyAgingDay(SurveyInvestigatorCaseDto item,double s){
        int efficiencyAttr = item.getEfficiencyAttr();
        Date createTime = item.getAssignDate() == null ? new Date() : item.getAssignDate();
        Date endTime = item.getSurveyEndTime() == null ? new Date() : item.getSurveyEndTime();
        Date commitDate = item.getCreportDate();

        int days = 0;
        if(commitDate != null){ //已提交
//            days = GetWorkDay.calLeaveDays(createTime, commitDate,efficiencyAttr);
            days = (int) s;
//            if(!commitDate.before(endTime)){
//                days = -days;
//            }
//            if (new SimpleDateFormat("yyyyMMdd").format(createTime).equals(new SimpleDateFormat("yyyyMMdd").format(commitDate))){
//                days = 0;
//            }
//            if (new SimpleDateFormat("yyyyMMdd").format(commitDate).equals(new SimpleDateFormat("yyyyMMdd").format(endTime))){
//                days = Math.abs(days);
//            }
        }else{//未提交
            days = GetWorkDay.calLeaveDays(new Date(), endTime,efficiencyAttr);
        }
        return days;
    }

    public static int surveyAgingDay(SurveyInvestigatorCase surveyInvestigatorCase,int efficiencyAttr,double s){
        SurveyInvestigatorCaseDto surveyInvestigatorCaseDto =  new SurveyInvestigatorCaseDto();
        surveyInvestigatorCaseDto.setAssignDate(surveyInvestigatorCase.getAssignDate());
        surveyInvestigatorCaseDto.setSurveyEndTime(surveyInvestigatorCase.getSurveyEndTime());
        surveyInvestigatorCaseDto.setCreportDate(surveyInvestigatorCase.getCreportDate());
        surveyInvestigatorCaseDto.setEfficiencyAttr(efficiencyAttr);
        return surveyAgingDay(surveyInvestigatorCaseDto,s);
    }

    /**
     * 调查员 超期考核绩效
     * @param surveyInvestigatorCase 调查员案件信息
     * @param surveyAssignOrg   机构案件信息
     * @param surveyFranchisee  调查机构信息
     * @param surveyConsignor   委托方信息
     * @return
     */
    public static Double surveyAgingRate(SurveyInvestigatorCase surveyInvestigatorCase,SurveyAssignOrg surveyAssignOrg,SurveyFranchisee surveyFranchisee, SurveyConsignor surveyConsignor){
        Double rate = 1D;
        if (surveyFranchisee.getType() == 1 && surveyConsignor.getOrgAttr() == 2){//直营 且  互助机构案件
            int day = 0;
            if (surveyInvestigatorCase.getCreportDate() != null){
                day = surveyInvestigatorCase.getAgingOver().intValue();
            }
            day = Math.abs(day);
            if (day > 0){
                if (surveyAssignOrg.getServicesId() == 13){//全案
                    if (day >= 2  && day < 5){
                        rate = 0.8D;
                    }else if (day >= 5 && day < 10){
                        rate = 0.7;
                    }else if (day >= 10){
                        rate = 0.5;
                    }
                }else{//单点
                    if (day >= 1  && day < 2){
                        rate = 0.8D;
                    }else if (day >= 2 && day < 5){
                        rate = 0.7;
                    }else if (day >= 5){
                        rate = 0.5;
                    }
                }
            }
        }else if (surveyFranchisee.getInsuranceType() == 1 && surveyConsignor.getOrgAttr() == 1){//直营 且 保司
            int day = 0;
            if (surveyInvestigatorCase.getCreportDate() != null){
                day = surveyInvestigatorCase.getAgingOver().intValue();
            }
            day = Math.abs(day);
            if (day > 0){
                if (day >= 1 && day < 2){
                    rate = 0.8;
                }else if (day >= 2 && day < 3){
                    rate = 0.5;
                }else if (day >= 3){
                    rate = 0D;
                }
            }
        }

        if (surveyConsignor.getId().intValue() == 52){// 所有太平洋健康 的超时效占比  2022年5月24日  新需求
            int day = 0;
            if (surveyAssignOrg.getReportDate() != null){
                day = surveyAssignOrg.getAgingOver().intValue();
            }
            day = Math.abs(day);
            if (day > 0){
                if (day >= 1 && day < 2){
                    rate = 0.9D;
                }else if (day >=2 && day < 5){
                    rate = 0.7D;
                }else if (day >=5){
                    rate = 0D;
                }
            }
        }

        return rate;
    }

    /**
     * 机构   超期考核绩效
     * @param surveyAssignOrg   机构案件信息
     * @param surveyFranchisee  机构信息
     * @param  surveyConsignor  委托方信息
     * @return
     */
    public static  Double orgAgingRate(SurveyAssignOrg surveyAssignOrg, SurveyFranchisee surveyFranchisee, SurveyConsignor surveyConsignor){
        Double rate = 1D;
        if (surveyFranchisee.getType() != 1 && surveyConsignor.getOrgAttr() == 2){//非直营 且  互助机构案件
            int day = 0;
            if (surveyAssignOrg.getReportDate() != null){
//                 Integer agingDay1 = GetWorkDay.calLeaveDays(surveyAssignOrg.getCreateTime(),surveyAssignOrg.getOrgEndTime(),surveyConsignor.getEfficiencyAttr());
//                Integer agingDay2 = surveyAssignOrg.getAgingDay();
//                int diff = Math.abs(agingDay2) - Math.abs(agingDay1);
//                day = diff < 0 ? 0 : diff;
                day = surveyAssignOrg.getAgingOver().intValue();
            }
            day = Math.abs(day);
            if (day > 0){
                if (surveyAssignOrg.getServicesId() == 13){
                    if (day >= 1  && day < 3){
                        rate = 0.9D;
                    }else if (day >= 3 && day < 5){
                        rate = 0.8D;
                    }else if (day >= 5){
                        rate = 0.7D;
                    }
                }else{
                    if (day >= 1  && day < 3){
                        rate = 0.9D;
                    }else if (day >= 3 && day < 5){
                        rate = 0.8D;
                    }else if (day >= 5){
                        rate = 0.7D;
                    }
                }
            }
        }else if (surveyFranchisee.getInsuranceType() != 1 && surveyConsignor.getOrgAttr() == 1){//非直营 且 保司机构案件
            int day = 0;
            if (surveyAssignOrg.getReportDate() != null){
                day = surveyAssignOrg.getAgingOver().intValue();
            }
            day = Math.abs(day);
            if (day > 0){
                if (day >= 1 && day < 2){
                    rate = 0.9;
                }else if (day >= 2 && day < 3){
                    rate = 0.8;
                }else if (day >= 3 && day < 4){
                    rate = 0.7;
                }else if (day >= 4 && day < 5){
                    rate = 0.6;
                }else if (day >= 5 && day < 6){
                    rate = 0.5;
                }else if (day >= 6){
                    rate = 0D;
                }
            }
        }
        if (surveyConsignor.getId().intValue() == 94){// 所有众安的案件 机构的超期考核绩效永远都是1.0   2022年1月19日  新增需求
            rate = 1D;
        }
        return rate;
    }

}
