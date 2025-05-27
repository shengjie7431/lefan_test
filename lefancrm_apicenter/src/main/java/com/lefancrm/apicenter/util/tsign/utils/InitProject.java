package com.lefancrm.apicenter.util.tsign.utils;

import com.lefancrm.apicenter.util.tsign.eSign.SignHelper;

/**
 * Created by jun on 2017/8/16.
 */
public class InitProject {


    static {
        // 初始化项目，做全局使用，只初始化一次即可
        SignHelper.initProject();
    }


}
