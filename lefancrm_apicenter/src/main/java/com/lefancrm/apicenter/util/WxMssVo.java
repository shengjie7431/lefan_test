package com.lefancrm.apicenter.util;

import java.util.Map;

/**
 * Created by Jani on 2019/5/17.
 */
public class WxMssVo {
    private String touser;//openid2
    private String template_id;//模板id
    private Map<String,String> miniprogram;//小程序appid
    private Map<String, Map<String,String>> data;//数据
    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public Map<String, String> getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(Map<String, String> miniprogram) {
        this.miniprogram = miniprogram;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }


}
