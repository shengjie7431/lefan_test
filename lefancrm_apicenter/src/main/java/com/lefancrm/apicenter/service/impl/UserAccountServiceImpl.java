package com.lefancrm.apicenter.service.impl;

import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserAccountServiceImpl extends BaseServiceImpl implements UserAccountService {

    @Autowired
    private DistributionBasicMapper distributionBasicMapper;

    @Autowired
    private UserPromotedMapper userPromotedMapper;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private PromotedInfoMapper promotedInfoMapper;

    @Autowired
    private PromotionOutlayMapper promotionOutlayMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserAccountDetailMapper userAccountDetailMapper;

    @Override
    public void caseMoney(Long userId,String caseNo,String caseName,Long currentUserId) {
        if(userId == null){
            return;
        }
        //判断是否是推广人
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        Map<String,Object> paramMap = new HashMap<>();
        //如果不是推广人，那么需要找自己的推广客户信息
        PromotedInfo promotedInfo = null;
        UserPromoted userPromoted = null;
        Long parentId = 0L;
        if(userInfo.getIsPromoter() != 1){
            paramMap.put("customerId",userId);
            //查看我是否有推广记录(没有推广记录不给钱,案件还是继续往下走)
            promotedInfo = promotedInfoMapper.selectPromotedInfoByParam(paramMap);
            if(promotedInfo == null){
                return;
            }
            parentId = promotedInfo.getPromoterId();
        }else{
            paramMap.put("userId",userId);
            userPromoted =  userPromotedMapper.selectUserPromotedByUserId(paramMap);
            if(userPromoted == null){
                return;
            }
            if(userPromoted.getState() != 2){
                return;
            }
            parentId = userPromoted.getParentId();
        }
        //如果自己没有，则取上一层
        if(userPromoted == null){
            userPromoted = userPromotedMapper.selectByPrimaryKey
                    (parentId);
        }
        if(userPromoted.getIsOpen() != null && userPromoted.getIsOpen() == 0){
            //取出上一层，如果还是关闭的，则以上一层为基本，算钱
            UserPromoted userPromoted1 = userPromotedMapper.selectByPrimaryKey
                    (userPromoted.getParentId());
            if(userPromoted1 == null || userPromoted1.getIsOpen() == null){
                return;
            }
            if(userPromoted1.getIsOpen() == 0){
                userPromoted = userPromoted1;
            }
        }
        //添加推广人费用
        DistributionBasic basic =
                distributionBasicMapper.selectDistributionBasic();
        UserPromoted userPromoted1 = userPromotedMapper.selectByPrimaryKey
                (userPromoted.getParentId());//我的上级
        UserPromoted userPromoted2 = null;//我的上上级
        UserPromoted userPromoted3 = null;//我的上上上级
        if (userPromoted1 != null) {
            //是否有上级
            if (userPromoted1.getParentId() != 0) {
                userPromoted2 = userPromotedMapper.selectByPrimaryKey
                        (userPromoted1.getParentId());
                if (userPromoted == null && userPromoted2 != null) {
                    userPromoted3 = userPromotedMapper.selectByPrimaryKey
                            (userPromoted2.getParentId());
                }
            }
        }
        double myMoney = 0;
        double upMoney = 0;
        double upMoneyTo = 0;
        double upMoneyThree = 0;
        if (userPromoted != null && userPromoted1 == null) {
            myMoney = basic.getOneLevelMoney();
        } else if (userPromoted != null && userPromoted1 != null && userPromoted2 == null) {
            myMoney = basic.getOneLevelMoney();
            upMoney = basic.getTwoLevelMoney();
        } else if (userPromoted != null && userPromoted1 != null && userPromoted2 != null) {
            myMoney = basic.getOneLevelMoney();
            upMoney = basic.getTwoLevelMoney();
            upMoneyTo = basic.getThreeLevelMoney();
        } else if (userPromoted == null && userPromoted1 != null && userPromoted2 == null) {
            upMoney = basic.getOneLevelMoney();
        } else if (userPromoted == null && userPromoted1 != null && userPromoted2 != null && userPromoted3 == null) {
            upMoney = basic.getOneLevelMoney();
            upMoneyTo = basic.getTwoLevelMoney();
        } else if (userPromoted == null && userPromoted1 != null && userPromoted2 != null && userPromoted3 != null) {
            upMoney = basic.getOneLevelMoney();
            upMoneyTo = basic.getTwoLevelMoney();
            upMoneyThree = basic.getThreeLevelMoney();
        }
        //自己的钱
        if (userPromoted != null && myMoney != 0) {
            userInfo = userInfoMapper.selectByPrimaryKey(userPromoted.getUserId());
            this.addMoney(myMoney, userInfo, caseNo, caseName, "用户推广收入记录", 1, currentUserId);
        }
        //上级的钱
        if (userPromoted1 != null && upMoney != 0) {
            UserInfo userInfo1 = userInfoMapper.selectByPrimaryKey(userPromoted1.getUserId());
            this.addMoney(upMoney, userInfo1, caseNo, caseName, "用户推广收入记录", 1, currentUserId);
        }
        //上上级的钱
        if (userPromoted2 != null && upMoneyTo != 0) {
            UserInfo userInfo1 = userInfoMapper.selectByPrimaryKey(userPromoted2.getUserId());
            this.addMoney(upMoneyTo, userInfo1, caseNo, caseName, "用户推广收入记录", 1, currentUserId);
        }
        //上上上级的钱
        if (userPromoted3 != null && upMoneyThree != 0) {
            UserInfo userInfo1 = userInfoMapper.selectByPrimaryKey(userPromoted3.getUserId());
            this.addMoney(upMoneyThree, userInfo1, caseNo, caseName, "用户推广收入记录", 1, currentUserId);
        }

    }


    private void addMoney( double money, UserInfo userInfo,String caseNo,String caseName,String title, Integer type, Long currentUserId){
        UserAccount userAccount  = userAccountMapper.selectByPrimaryKey
                (userInfo.getUserId());
        if(userAccount == null){
            userAccount = new UserAccount();
            userAccount.setUserRecharge(0D);
            userAccount.setWithdrawDeposit(0D);
            userAccount.setUserId(userInfo.getUserId());
            userAccountMapper.insertSelective(userAccount);
        }
        userAccount.setUserRecharge(userAccount.getUserRecharge()  +money);
        userAccount.setWithdrawDeposit( userAccount.getWithdrawDeposit
                ()+money);

        userAccountMapper.updateByPrimaryKeySelective(userAccount);
        //添加推广费记录
        PromotionOutlay promotionOutlay = new PromotionOutlay();
        promotionOutlay.setOrgId(userInfo.getOrgId());
        promotionOutlay.setOrgName(userInfo.getOrgName());
        promotionOutlay.setUserId(userInfo.getUserId());
        promotionOutlay.setUserName(userInfo.getNickName());
        promotionOutlay.setMoney(money);
        promotionOutlay.setCaseNo(caseNo);
        promotionOutlay.setCaseName(caseName);
        promotionOutlay.setCreateTime(new Date());
        promotionOutlay.setCreateBy(currentUserId.toString());
        promotionOutlay.setType(type);
        promotionOutlayMapper.insertSelective(promotionOutlay);
        //添加钱的明细
        UserAccountDetail userAccountDetail = new UserAccountDetail();
        userAccountDetail.setUserId(userInfo.getUserId());
        userAccountDetail.setTitle(title);
        userAccountDetail.setType(1);
        userAccountDetail.setMoney(money);
        userAccountDetail.setCreateTime(new Date());
        userAccountDetail.setGenre(1);
        userAccountDetail.setBusinessId(promotionOutlay.getId());
        userAccountDetailMapper.insertSelective(userAccountDetail);
    }

}
