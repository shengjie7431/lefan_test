package com.lefancrm.apicenter.util;



import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * 消息消息通知实体
 */

public class SubscribeMessage implements Serializable {
    private String touser;
    private String template_id;
    private String page;
    private String lang="zh_CN";
    private String miniprogram_state;
    private Map<String, MessageTemplate> data = new HashMap<>();


    public static class MessageTemplate{
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

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

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getMiniprogram_state() {
        return miniprogram_state;
    }

    public void setMiniprogram_state(String miniprogram_state) {
        this.miniprogram_state = miniprogram_state;
    }

    public Map<String, MessageTemplate> getData() {
        return data;
    }

    public void setData(Map<String, MessageTemplate> data) {
        this.data = data;
    }
}
