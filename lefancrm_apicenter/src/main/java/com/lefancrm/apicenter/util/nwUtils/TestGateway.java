/*
 * Copyright 2019 Zhongan.io All right reserved. This software is the
 * confidential and proprietary information of Zhongan.io ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.io.
 */
package com.lefancrm.apicenter.util.nwUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 类TestGateway的实现描述：TODO 类实现描述
 *
 * @author za-wanggang 2019-2-21 13:08
 */
public class TestGateway extends BaseOperator<Map<String, Object>> {

    private String handleId;

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }


    @Override
    protected Map getBizContent(){
        Map<String,String> data = new HashMap<>();
        data.put("organizationCode", "10001");
        data.put("handleId", this.getHandleId());
        return data;
    }

}
