package com.lefancrm.apicenter.util;

import com.lefancrm.apicenter.model.BlameQuotiety;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jani on 2018/5/16.
 */
public class PaymentAlgorithmUtil {
    /**
     * 获取责任系数
     * @param blameQuotieties 责任系数基本数据列表
     * @param myAccidentLiability 我的责任ID
     * @return
     */
    public static double getBlameQuotiety(Integer mystatus,List<BlameQuotiety> blameQuotieties, Integer myAccidentLiability, Integer otherTarfficStatus, Integer myTarfficStatus){
        long myAccidentLiabilityL = (long)myAccidentLiability;
        for(BlameQuotiety blameQuotiety : blameQuotieties){
            if( blameQuotiety.getId() == myAccidentLiabilityL){
                if(otherTarfficStatus !=1  && myTarfficStatus == 1){
                    return blameQuotiety.getVehicleToUnvehicle();
                }else if(otherTarfficStatus ==1  && myTarfficStatus == 1){
                    if(mystatus==1){
                        return blameQuotiety.getHvehicleToUnvehicle();
                    }else if(mystatus==2){
                        return blameQuotiety.getHvehicleToVehicle();
                    }
                    return blameQuotiety.getVehicleToVehicle();
                }else if(otherTarfficStatus ==1  && myTarfficStatus != 1){
                    return blameQuotiety.getHvehicleToVehicle();
                }else if(otherTarfficStatus !=1  && myTarfficStatus != 1){
                    if(mystatus==1){
                        return blameQuotiety.getHvehicleToUnvehicle();
                    }else if(mystatus==2){
                        return blameQuotiety.getHvehicleToVehicle();
                    }
                }
            }
        }
        return 0;
    }
    /**
     * 判断有无责
     * @return
     */
    public static boolean getResponsibility(List<BlameQuotiety> blameQuotieties, Integer myAccidentLiability, Integer otherTarfficStatus, Integer myTarfficStatus){
        boolean bl=false;
        long myAccidentLiabilityL = (long)myAccidentLiability;
        if(myAccidentLiabilityL==5){
            //无责任，我方是机动车无责
            for(BlameQuotiety blameQuotiety : blameQuotieties){
                if(blameQuotiety.getId()==5)
                {
                    if(otherTarfficStatus !=1  && myTarfficStatus == 1){
                        bl=true;
                    }
                }
            }
        }else if(myAccidentLiabilityL==1){
            //全责，我方全责，机动车方无责
            for(BlameQuotiety blameQuotiety : blameQuotieties){
                if(blameQuotiety.getId()==1)
                {
                    if(otherTarfficStatus ==1&&myTarfficStatus != 1){
                        bl=true;
                    }
                }
            }
        }
        return bl;
    }
    //限额5万第一部分计算费用总和 残 无责任
    public static Map<String,Object> estimateOneIrresponsibility (String irrespQuotaOne,Double  medicalFee,Double  hospitalFoodFee,Double nutritionFee,Double againCureFee)
    {
        //第一部分费用总和
        Double estimateOneTotal=medicalFee+hospitalFoodFee+nutritionFee+againCureFee;
        //第一部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第一部分商业险总和
        Double commercialInsuranceFee=0D;
        Double irrespQuotaOneFee=Double.parseDouble(irrespQuotaOne);
        if(estimateOneTotal>irrespQuotaOneFee){
            compulsoryInsuranceFee = irrespQuotaOneFee;//限额只赔交强险
            commercialInsuranceFee=0D;//商业险为0D
        }else{
            compulsoryInsuranceFee=estimateOneTotal;//只赔付交强险
            commercialInsuranceFee=0D;//商业险为0D
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("estimateOneTotal",estimateOneTotal);//费用总和
        map.put("compulsoryInsuranceOneFee",compulsoryInsuranceFee);//交强险
        map.put("commercialInsuranceOneFee",commercialInsuranceFee);//商业险

        return map;
    }
    //第二部分费用，无责任的情况，计算t_litigationFee,t_disabilityEquipmentFee,t_appraisalFee
    public static Map<String,Object> estimateTwoIrresponsibility(String irrespQuotaTwo,Double lossWordFee,Double nursingFee,Double invalidismIndemnifyFee,Double spiritComfortFee,Double trafficFee,Double litigationFee,Double disabilityEquipmentFee,Double appraisalFee)
    {
        Map<String,Object> map=new HashMap<String,Object>();
        //第2部分费用总和
        Double estimateTwoTotal=lossWordFee+nursingFee+invalidismIndemnifyFee+trafficFee+spiritComfortFee+litigationFee+disabilityEquipmentFee+appraisalFee;
        //第2部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第2部分商业险总和
        Double commercialInsuranceFee=0D;
        Double irrespQuotaTwoFee=Double.valueOf(irrespQuotaTwo);
        if(estimateTwoTotal>=irrespQuotaTwoFee){
            compulsoryInsuranceFee = irrespQuotaTwoFee;
            commercialInsuranceFee=estimateTwoTotal-irrespQuotaTwoFee;
        }else{
            compulsoryInsuranceFee = estimateTwoTotal;
        }
        map.put("estimateTwoTotal",estimateTwoTotal-spiritComfortFee);//费用总和
        map.put("compulsoryInsuranceTwoFee",compulsoryInsuranceFee-spiritComfortFee);//交强险
        map.put("commercialInsuranceTwoFee",commercialInsuranceFee);//商业险
        /*if(subtractSpiritComfortFee>=0){
            if(estimateTwoTotal>subtractSpiritComfortFee){
                if(subtractSpiritComfortFee>irrespQuotaTwoFee){
                    compulsoryInsuranceFee = irrespQuotaTwoFee;
                }else{
                    compulsoryInsuranceFee = subtractSpiritComfortFee;
                }
                commercialInsuranceFee=0D;
            }
            else{
                if(estimateTwoTotal>irrespQuotaTwoFee){
                    compulsoryInsuranceFee = irrespQuotaTwoFee;
                }else{
                    compulsoryInsuranceFee = estimateTwoTotal;
                }
                commercialInsuranceFee=0D;
            }
            map.put("estimateTwoTotal",estimateTwoTotal);//费用总和
            map.put("compulsoryInsuranceTwoFee",compulsoryInsuranceFee);//交强险
            map.put("commercialInsuranceTwoFee",commercialInsuranceFee);//商业险
        }else{
            map.put("estimateTwoTotal",estimateTwoTotal);//费用总和
            map.put("compulsoryInsuranceTwoFee",0D);//交强险
            map.put("commercialInsuranceTwoFee",estimateTwoTotal);//商业险
        }*/
        return map;
    }
    //限额2000，第三部分计算费用总和(财务损失) 无责任
    public static Map<String,Object> estimateThreeIrresponsibility (String irrespQuotaThree,Double financialLossFee)
    {
        //第3部分费用总和
        Double estimateThreeTotal=financialLossFee;
        //第3部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第3部分商业险总和
        Double commercialInsuranceFee=0D;
        Double irrespQuotaThreeFee=Double.parseDouble(irrespQuotaThree);
        if(estimateThreeTotal>irrespQuotaThreeFee){
            compulsoryInsuranceFee = irrespQuotaThreeFee;
            commercialInsuranceFee=0D;
        }
        else{
            compulsoryInsuranceFee=estimateThreeTotal;
            commercialInsuranceFee=0D;
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("estimateThreeTotal",estimateThreeTotal);//费用总和
        map.put("compulsoryInsuranceThreeFee",compulsoryInsuranceFee);//交强险
        map.put("commercialInsuranceThreeFee",commercialInsuranceFee);//商业险
        return map;
    }
    //第一部分无责死亡的计算
    public static Map<String,Object> destimateOneIrresponsibility (String irrespQuotaOne,Double  medicalFee)
    {
        //第一部分费用总和
        Double estimateOneTotal=medicalFee;
        //第一部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第一部分商业险总和
        Double commercialInsuranceFee=0D;
        Double irrespQuotaOneFee=Double.parseDouble(irrespQuotaOne);
        if(estimateOneTotal>irrespQuotaOneFee){
            compulsoryInsuranceFee = irrespQuotaOneFee;
            commercialInsuranceFee=0D;
        }
        else{
            compulsoryInsuranceFee=estimateOneTotal;
            commercialInsuranceFee=0D;
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("destimateOneTotal",estimateOneTotal);//费用总和
        map.put("dcompulsoryInsuranceOneFee",compulsoryInsuranceFee);//交强险
        map.put("dcommercialInsuranceOneFee",commercialInsuranceFee);//商业险
        return map;
    }
    //限额11万，第二部分计算费用总和 死亡 无责任计算
    public static Map<String,Object> destimateTwoIrresponsibility(String irrespQuotaTwo,Double deathIndemnifyFee,Double spiritComfortFee,Double funeralFee)
    {
        //第2部分费用总和
        Double estimateTwoTotal=deathIndemnifyFee+funeralFee+spiritComfortFee;
        //第2部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第2部分商业险总和
        Double commercialInsuranceFee=0D;
        /**
         * 无责限额，只赔交强险
         */
        Double irrespQuotaTwoFee=Double.valueOf(irrespQuotaTwo);
        Map<String,Object> map=new HashMap<String,Object>();

        if(estimateTwoTotal>=irrespQuotaTwoFee){
            compulsoryInsuranceFee = irrespQuotaTwoFee;
            commercialInsuranceFee=estimateTwoTotal-irrespQuotaTwoFee;
        }else{
            compulsoryInsuranceFee=estimateTwoTotal;
        }
        map.put("destimateTwoTotal",estimateTwoTotal-spiritComfortFee);//费用总和
        map.put("dcompulsoryInsuranceTwoFee",compulsoryInsuranceFee-spiritComfortFee);//交强险
        map.put("dcommercialInsuranceTwoFee",commercialInsuranceFee);//商业险
        /*if(subtractSpiritComfortFee>=0){
            if(estimateTwoTotal>subtractSpiritComfortFee){
                compulsoryInsuranceFee = subtractSpiritComfortFee;
                if(subtractSpiritComfortFee>irrespQuotaTwoFee){
                    compulsoryInsuranceFee = irrespQuotaTwoFee;
                }else{
                    compulsoryInsuranceFee = subtractSpiritComfortFee;
                }
                commercialInsuranceFee=0D;
            }
            else{
                if(estimateTwoTotal>irrespQuotaTwoFee){
                    compulsoryInsuranceFee = irrespQuotaTwoFee;
                }else{
                    compulsoryInsuranceFee = estimateTwoTotal;
                }
                commercialInsuranceFee=0D;
            }
            map.put("destimateTwoTotal",estimateTwoTotal);//费用总和
            map.put("dcompulsoryInsuranceTwoFee",compulsoryInsuranceFee);//交强险
            map.put("dcommercialInsuranceTwoFee",commercialInsuranceFee);//商业险
        }else{
            map.put("destimateTwoTotal",estimateTwoTotal);//费用总和
            map.put("dcompulsoryInsuranceTwoFee",0D);//交强险
            map.put("dcommercialInsuranceTwoFee",estimateTwoTotal);//商业险
        }*/
        return map;
    }
    //限额5万第一部分计算费用总和 残 除开无责任
    public static Map<String,Object> estimateOne (String quotaOne,Double  medicalFee,Double  hospitalFoodFee,Double nutritionFee,Double againCureFee)
    {
        //第一部分费用总和
        Double estimateOneTotal=medicalFee+hospitalFoodFee+nutritionFee+againCureFee;
        //第一部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第一部分商业险总和
        Double commercialInsuranceFee=0D;
        Double quotaOneFee=Double.parseDouble(quotaOne);
        if(estimateOneTotal>quotaOneFee){
            compulsoryInsuranceFee = quotaOneFee;
            commercialInsuranceFee=estimateOneTotal-quotaOneFee;
        }else{
            compulsoryInsuranceFee=estimateOneTotal;
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("estimateOneTotal",estimateOneTotal);//费用总和
        map.put("compulsoryInsuranceOneFee",compulsoryInsuranceFee);//交强险
        map.put("commercialInsuranceOneFee",commercialInsuranceFee);//商业险

        return map;
    }
    //限额11万，第二部分计算费用总和加上优先扣除精神抚慰金算法  残
    public static Map<String,Object> estimateTwo(String quotaTwo,Double lossWordFee,Double nursingFee,Double invalidismIndemnifyFee,Double spiritComfortFee,Double trafficFee,Double litigationFee,Double disabilityEquipmentFee,Double appraisalFee)
    {
        Map<String,Object> map=new HashMap<String,Object>();
        //第2部分费用总和
        Double estimateTwoTotal=lossWordFee+nursingFee+invalidismIndemnifyFee+trafficFee+spiritComfortFee+litigationFee+disabilityEquipmentFee+appraisalFee;
        //第2部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第2部分商业险总和
        Double commercialInsuranceFee=0D;
        Double quotaTwoFee=Double.valueOf(quotaTwo);
        if(estimateTwoTotal>=quotaTwoFee){
            compulsoryInsuranceFee = quotaTwoFee;
            commercialInsuranceFee=estimateTwoTotal-quotaTwoFee;
        }else{
            compulsoryInsuranceFee=estimateTwoTotal;
        }
        map.put("estimateTwoTotal",estimateTwoTotal-spiritComfortFee);//费用总和
        map.put("compulsoryInsuranceTwoFee",compulsoryInsuranceFee-spiritComfortFee);//交强险
        map.put("commercialInsuranceTwoFee",commercialInsuranceFee);//商业险
        /*if(subtractSpiritComfortFee>=0){
            if(estimateTwoTotal>subtractSpiritComfortFee){
                compulsoryInsuranceFee = subtractSpiritComfortFee;
                commercialInsuranceFee=estimateTwoTotal-subtractSpiritComfortFee;
            }
            else{
                compulsoryInsuranceFee=estimateTwoTotal;
            }
            map.put("estimateTwoTotal",estimateTwoTotal);//费用总和
            map.put("compulsoryInsuranceTwoFee",compulsoryInsuranceFee);//交强险
            map.put("commercialInsuranceTwoFee",commercialInsuranceFee);//商业险
        }else{
            map.put("estimateTwoTotal",estimateTwoTotal);//费用总和
            map.put("compulsoryInsuranceTwoFee",0D);//交强险
            map.put("commercialInsuranceTwoFee",estimateTwoTotal);//商业险
        }*/
        return map;
    }
    //限额2000，第三部分计算费用总和(财务损失)
    public static Map<String,Object> estimateThree (String quotaThree,Double financialLossFee)
    {
        //第3部分费用总和
        Double estimateThreeTotal=financialLossFee;
        //第3部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第3部分商业险总和
        Double commercialInsuranceFee=0D;
        Double quotaThreeFee=Double.parseDouble(quotaThree);
        if(estimateThreeTotal>quotaThreeFee){
            compulsoryInsuranceFee = quotaThreeFee;
            commercialInsuranceFee=estimateThreeTotal-quotaThreeFee;
        }
        else{
            compulsoryInsuranceFee=estimateThreeTotal;
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("estimateThreeTotal",estimateThreeTotal);//费用总和
        map.put("compulsoryInsuranceThreeFee",compulsoryInsuranceFee);//交强险
        map.put("commercialInsuranceThreeFee",commercialInsuranceFee);//商业险
        return map;
    }
    //限额第一部分计算费用总和  死亡
    public static Map<String,Object> destimateOne (String quotaOne,Double  medicalFee)
    {
        //第一部分费用总和
        Double estimateOneTotal=medicalFee;
        //第一部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第一部分商业险总和
        Double commercialInsuranceFee=0D;
        Double quotaOneFee=Double.parseDouble(quotaOne);
        if(estimateOneTotal>quotaOneFee){
            compulsoryInsuranceFee = quotaOneFee;
            commercialInsuranceFee=estimateOneTotal-quotaOneFee;
        }
        else{
            compulsoryInsuranceFee=estimateOneTotal;
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("destimateOneTotal",estimateOneTotal);//费用总和
        map.put("dcompulsoryInsuranceOneFee",compulsoryInsuranceFee);//交强险
        map.put("dcommercialInsuranceOneFee",commercialInsuranceFee);//商业险
        return map;
    }
    //限额11万，第二部分计算费用总和 死亡
    public static Map<String,Object> destimateTwo(String quotaTwo,Double deathIndemnifyFee,Double spiritComfortFee,Double funeralFee)
    {
        //第2部分费用总和
        Double estimateTwoTotal=deathIndemnifyFee+funeralFee+spiritComfortFee;
        //第2部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第2部分商业险总和
        Double commercialInsuranceFee=0D;
        Double quotaTwoFee=Double.valueOf(quotaTwo);
        Map<String,Object> map=new HashMap<String,Object>();

        if(estimateTwoTotal>=quotaTwoFee){
            compulsoryInsuranceFee = quotaTwoFee;
            commercialInsuranceFee=estimateTwoTotal-quotaTwoFee;
        }else{
            compulsoryInsuranceFee=estimateTwoTotal;
        }
        map.put("destimateTwoTotal",estimateTwoTotal-spiritComfortFee);//费用总和
        map.put("dcompulsoryInsuranceTwoFee",compulsoryInsuranceFee-spiritComfortFee);//交强险
        map.put("dcommercialInsuranceTwoFee",commercialInsuranceFee);//商业险
        /*if(subtractSpiritComfortFee>=0){
            if(estimateTwoTotal>subtractSpiritComfortFee){
                compulsoryInsuranceFee = subtractSpiritComfortFee;
                commercialInsuranceFee=estimateTwoTotal-subtractSpiritComfortFee;
            }
            else{
                compulsoryInsuranceFee=estimateTwoTotal;
            }
            map.put("destimateTwoTotal",estimateTwoTotal);//费用总和
            map.put("dcompulsoryInsuranceTwoFee",compulsoryInsuranceFee);//交强险
            map.put("dcommercialInsuranceTwoFee",commercialInsuranceFee);//商业险
        }else{
            map.put("destimateTwoTotal",estimateTwoTotal);//费用总和
            map.put("dcompulsoryInsuranceTwoFee",0D);//交强险
            map.put("dcommercialInsuranceTwoFee",estimateTwoTotal);//商业险
        }*/
        return map;
    }
    /**
     * 扣除精神抚慰金
     * @return
     */
    public static Double subtractSpiritComfortFee (String quotaTwo,Double spiritComfortFee)
    {
        Double subtractSpiritComfortFee=Double.parseDouble(quotaTwo)-spiritComfortFee;
        return subtractSpiritComfortFee;
    }
}
