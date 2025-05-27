package com.lefancrm.apicenter.util;

import java.math.BigDecimal;

/**
 * Created by user on 2015/11/10.
 */
public class DecimalUtil {

    /**
     * 保留两位小数（四舍五入）
     *
     * @return
     * @author daniel
     */
    public static double twoDecimalTOFourFromFive(double decimal) {
        BigDecimal   b   =   new BigDecimal(decimal);
        double   decimal2   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        return decimal2;
    }

    public static double twoDecimalTOFourFromFive(Double decimal) {
        if (decimal == null){
            decimal = 0D;
        }
        BigDecimal   b   =   new BigDecimal(decimal);
        double   decimal2   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        return decimal2;
    }


    public static double fourDecimalTOFourFromFive(Double decimal) {
        if (decimal == null){
            decimal = 0D;
        }
        BigDecimal   b   =   new BigDecimal(decimal);
        double   decimal2   =   b.setScale(4,   BigDecimal.ROUND_HALF_UP).doubleValue();
        return decimal2;
    }
}
