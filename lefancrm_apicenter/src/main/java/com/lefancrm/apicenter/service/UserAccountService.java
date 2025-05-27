package com.lefancrm.apicenter.service;

/**
 * 用户资金管理
 * 
 * @author Daniel
 */
public interface UserAccountService {
    /**
     * 提供案源获得费用
     */
    public void caseMoney(Long userId, String caseNo, String caseName, Long currentUserId);

}
