package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.SuningWithholdApply;

/**
 * Created by lixianfeng on 2018/4/10.
 */
public class SuningWithholdApplyDto extends SuningWithholdApply {
    /**
     * 是否显示转结案按钮
     */
    private boolean showConvertCloseState;

    public boolean isShowConvertCloseState() {
        return showConvertCloseState;
    }

    public void setShowConvertCloseState(boolean showConvertCloseState) {
        this.showConvertCloseState = showConvertCloseState;
    }
}
