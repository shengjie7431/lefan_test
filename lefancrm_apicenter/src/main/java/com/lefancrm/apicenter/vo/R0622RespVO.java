package com.lefancrm.apicenter.vo;

import com.lefancrm.apicenter.model.QueryBankCardByCustomerId4DomainBean;

import java.io.Serializable;
import java.util.List;

public class R0622RespVO implements Serializable {

    /**结果码*/
    private String respCode;

    /**备注*/
    private String memo;

    private List<QueryBankCardByCustomerId4DomainBean> queryByCustomerId4DomainBeanList;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<QueryBankCardByCustomerId4DomainBean> getQueryByCustomerId4DomainBeanList() {
        return queryByCustomerId4DomainBeanList;
    }

    public void setQueryByCustomerId4DomainBeanList(List<QueryBankCardByCustomerId4DomainBean> queryByCustomerId4DomainBeanList) {
        this.queryByCustomerId4DomainBeanList = queryByCustomerId4DomainBeanList;
    }
}
