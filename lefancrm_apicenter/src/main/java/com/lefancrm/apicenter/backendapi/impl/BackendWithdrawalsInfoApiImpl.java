package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendWithdrawalsInfoApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.PromotionOutlayDto;
import com.lefancrm.apicenter.dto.UserAccountDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.wechatPay.RequestHandler;
import com.lefancrm.apicenter.util.wechatPay.client.TenpayHttpClient;
import com.lefancrm.apicenter.util.wechatPay.util.Sha1Util;
import com.lefancrm.apicenter.util.wechatPay.util.WxPubPayConfig;
import com.lefancrm.apicenter.util.wechatPay.util.XMLUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * Created by wangwei on 2018/5/17.
 */
@ApiService(descript = "提现审核列表API")
@Service
public class BackendWithdrawalsInfoApiImpl extends BaseServiceImpl implements BackendWithdrawalsInfoApi {
    @Autowired
    private WithdrawalsInfoMapper withdrawalsInfoMapper;
    @Autowired
    private PromotionOutlayMapper promotionOutlayMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserAccountDetailMapper userAccountDetailMapper;
    @Autowired
    private TenpayCompanyParamsMapper tenpayCompanyParamsMapper;
    /**
     * 提现审核列表
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "提现审核列表" ,value = "backend-withdrawals-info-list")
    @Override
    public ApiResponse<List<WithdrawalsInfo>> withdrawalsInfoList(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        int count = withdrawalsInfoMapper.selectWithdrawalsInfoListSize(apiReq);
        List<WithdrawalsInfo> list = withdrawalsInfoMapper.selectWithdrawalsInfoList(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    /**
     * 查询提现明细信息
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "查询提现明细信息", value = "backend-promotion-outlay-dto-list")
    @Override
    public ApiResponse<List<PromotionOutlayDto>> selectBkPromotionOutlayDtoListByParam(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
        int count = promotionOutlayMapper.selectCountPromotionOutlay(apiReq);
        List<PromotionOutlayDto> list = promotionOutlayMapper.selectPromotionOutlayList(apiReq);
        return new ApiResponse<List<PromotionOutlayDto>>(ApiMsgEnum.SUCCESS, count, list);
    }

    /**
     * 推广用户账户信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "推广用户账户信息", value = "backend-user-account-dto")
    @Override
    public ApiResponse<UserAccountDto> selectBkUserAccountDto(ApiRequest apiReq) {
        Long userId = apiReq.getLong("userId");
        UserAccount userAccount=this.userAccountMapper.selectByPrimaryKey(userId);
        UserAccountDto userAccountDto=new UserAccountDto();
        userAccountDto.setUserRecharge(userAccount.getUserRecharge());
        userAccountDto.setWithdrawDeposit(userAccount.getWithdrawDeposit());

        //查询出已经提现金额总和
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId",userId);
        Double  isWithdrawDeposit = this.withdrawalsInfoMapper.selectWithdrawalsInfoMoneyByUserId(paramMap);
        userAccountDto.setIsWithdrawDeposit(isWithdrawDeposit);
        return new ApiResponse<UserAccountDto>(ApiMsgEnum.SUCCESS, 1, userAccountDto);
    }

    /**
     * 审核提现申请
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "审核提现申请" ,value = "backend-withdrawals-state-edit")
    @Override
    public ApiResponse<WithdrawalsInfo> editwithdrawalsState(ApiRequest apiReq){

        WithdrawalsInfo withdrawalsInfo = withdrawalsInfoMapper.selectByPrimaryKey(apiReq.getLong("id"));
        withdrawalsInfo.setState(2);

        int result = withdrawalsInfoMapper.updateByPrimaryKeySelective(withdrawalsInfo);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    /**
     * 减掉此笔费用
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "减掉此笔费用" ,value = "backend-promotion-outlay-cut-off")
    @Override
    public ApiResponse<PromotionOutlay> cutOffPromotionOutlayInfo(ApiRequest apiReq){

        //被减掉的推广费用记录表
        PromotionOutlay promotionOutlay = promotionOutlayMapper.selectByPrimaryKey(apiReq.getLong("id"));

        //删除之前的收支记录; 生成新的收支记录
        UserAccountDetail newUserAccountDetail = addAndDelUserAccountDetail(apiReq.getLong("id"));

        //修改账户金额
        UserAccount userAccount = userAccountMapper.selectByPrimaryKey(promotionOutlay.getUserId());

        //可提现金额 大于 被删除的金额
        if(userAccount.getWithdrawDeposit() > newUserAccountDetail.getMoney()){
            userAccount.setWithdrawDeposit(userAccount.getWithdrawDeposit()- newUserAccountDetail.getMoney());
            userAccount.setUserRecharge(userAccount.getUserRecharge() - newUserAccountDetail.getMoney());
            userAccountMapper.updateByPrimaryKeySelective(userAccount);
        }else{
            //减去可提现金额
            Double money = newUserAccountDetail.getMoney()-userAccount.getWithdrawDeposit();

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("userId",promotionOutlay.getUserId());
            List<WithdrawalsInfo> withdrawalsInfo = withdrawalsInfoMapper.selectWithdrawalsInfoByUserIdAndState(paramMap);

            if(withdrawalsInfo.get(0).getMoney() < money){
                //再减去"最近记录"的提现金额  第一笔+第二笔
                Double moneyTwo = money -withdrawalsInfo.get(0).getMoney();
                withdrawalsInfo.get(1).setMoney(withdrawalsInfo.get(1).getMoney() - moneyTwo);
                withdrawalsInfoMapper.updateByPrimaryKey(withdrawalsInfo.get(1));
            }else{
                //再减去"最近记录"的提现金额 第一笔
                withdrawalsInfo.get(0).setMoney(withdrawalsInfo.get(0).getMoney() - money);
                withdrawalsInfoMapper.updateByPrimaryKey(withdrawalsInfo.get(0));
            }
        }

        int result = promotionOutlayMapper.updateByPrimaryKeySelective(promotionOutlay);
        if(result>0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    private UserAccountDetail addAndDelUserAccountDetail(Long id) {

        //被减掉"推广费用记录表"对应的"收支明细"
        UserAccountDetail userAccountDetail = userAccountDetailMapper.selectUserAccountDetailByBusinessId(id);

        //生成新的收支记录
        UserAccountDetail newUserAccountDetail = new UserAccountDetail();
        newUserAccountDetail.setUserId(userAccountDetail.getUserId());
        newUserAccountDetail.setType(2);
        newUserAccountDetail.setTitle("被减掉的费用");
        newUserAccountDetail.setMoney(userAccountDetail.getMoney());
        newUserAccountDetail.setGenre(userAccountDetail.getGenre());
        newUserAccountDetail.setUserName(userAccountDetail.getUserName());
        newUserAccountDetail.setBusinessId(userAccountDetail.getBusinessId());
        newUserAccountDetail.setCreateTime(new Date());
        userAccountDetailMapper.insertSelective(newUserAccountDetail);

        //删除之前的收支记录
        userAccountDetailMapper.deleteByPrimaryKey(userAccountDetail.getId());

        return newUserAccountDetail;
    }

    /**
     * 保存线下提现凭证
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "保存线下提现凭证" ,value = "backend-withdrawals-info-unline-save")
    @Override
    public ApiResponse<WithdrawalsInfo> withdrawalsUnlineSave(ApiRequest apiReq) {
        //修改提现记录状态
        Long id = apiReq.getLong("withdrawalsId");
        String unlineImg=apiReq.getString("unlineImg");
        WithdrawalsInfo withdrawalsInfo=this.withdrawalsInfoMapper.selectByPrimaryKey(id);
        withdrawalsInfo.setState(3);//提现成功
        withdrawalsInfo.setUnlineImg(unlineImg);
        int result = this.withdrawalsInfoMapper.updateByPrimaryKeySelective(withdrawalsInfo);
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 在线提现
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "在线提现", value = "backend-withdrawals-info-online")
    @Override
    public ApiResponse<String> withdrawalsOnline(ApiRequest apiReq) {
        //生成提现申请单，回调成功责修改提现记录状态，
        //修改提现记录状态
        Long id = apiReq.getLong("withdrawalsId");
        WithdrawalsInfo withdrawalsInfo=this.withdrawalsInfoMapper.selectByPrimaryKey(id);

        //打款操作
        Double fiprice=withdrawalsInfo.getMoney()*100;
        Integer price = (int)Math.round(fiprice);
        try {
            //判断价格不能低于1分钱
            if (price >= 1) {
                String body = "";
                HttpServletRequest request = apiReq.getRes();
                HttpServletResponse response = apiReq.getRos();
                //获取提交的商品价格
                String order_price = price.toString();
                //获取提交的商品名称
                String product_name = body;
                Map<String, String> outParams = new HashMap<String, String>();
                RequestHandler reqHandler = new RequestHandler(request, response);
                TenpayHttpClient httpClient = new TenpayHttpClient();
                //初始化
                reqHandler.init();
                reqHandler.init(WxPubPayConfig.app_id, WxPubPayConfig.partner, WxPubPayConfig.partner_key);
                //=========================
                //生成预支付单
                //=========================
                String noncestr = Sha1Util.getNonceStr();
                //设置package订单参数
                SortedMap<String, String> packageParams = new TreeMap<String, String>();
                packageParams.put("mch_appid", WxPubPayConfig.app_id);
                packageParams.put("mchid", WxPubPayConfig.partner); //商户号
                packageParams.put("nonce_str", noncestr);
                packageParams.put("partner_trade_no", withdrawalsInfo.getWidraCode()); //商家订单号
                packageParams.put("openid", withdrawalsInfo.getOpenId()); //收款方的微信OpenID
                packageParams.put("check_name", "NO_CHECK"); //是否需要检验用户认证信息
                /**
                 *NO_CHECK：不校验真实姓名
                 FORCE_CHECK：强校验真实姓名（未实名认证的用户会校验失败，无法转账）
                 OPTION_CHECK：针对已实名认证的用户才校验真实姓名（未实名认证用户不校验，可以转账成功）
                 */
                       /*packageParams.put("re_user_name", accountRecord.getPayName()); //用户姓名*/
                packageParams.put("amount", order_price); //金额
                packageParams.put("desc", "乐凡公估推广费用支出"); //用户姓名
                packageParams.put("spbill_create_ip", request.getRemoteAddr()); //交易ip
                String path = request.getSession().getServletContext().getRealPath("/");
                String resultStr = reqHandler.sendPayToCar(packageParams, path);
                Map<String, String> map = XMLUtil.doXMLParse(resultStr);
                String returnCode = map.get("return_code");
                String resultCode = map.get("result_code");

                TenpayCompanyParams tenpayCompanyParams=new TenpayCompanyParams();
                tenpayCompanyParams.setMchAppid(WxPubPayConfig.app_id);
                tenpayCompanyParams.setMchid(WxPubPayConfig.partner);
                tenpayCompanyParams.setNonceStr(noncestr);
                tenpayCompanyParams.setWidraCode(withdrawalsInfo.getWidraCode());
                tenpayCompanyParams.setOpenid(withdrawalsInfo.getOpenId());
                tenpayCompanyParams.setCheckName("NO_CHECK");
                tenpayCompanyParams.setAmount(Double.parseDouble(order_price));
                tenpayCompanyParams.setDesc("乐凡公估推广费用支出");
                tenpayCompanyParams.setSpbillCreateIp(request.getRemoteAddr());
                tenpayCompanyParams.setResultCode(resultCode);
                tenpayCompanyParams.setReturnCode(returnCode);
                this.tenpayCompanyParamsMapper.insertSelective(tenpayCompanyParams);

                if (returnCode.equals("SUCCESS") && resultCode.equals("SUCCESS")) {
                    System.out.println("return_code=" + map.get("return_code"));
                    System.out.println("return_msg=" + map.get("return_msg"));
                    withdrawalsInfo.setState(3);//提现成功
                    int result = this.withdrawalsInfoMapper.updateByPrimaryKeySelective(withdrawalsInfo);
                    //判断recode是否成功，如果成功的话，则修改结算表中的支付状态
                    /*  boolean result =  updatePayState(Integer.valueOf(accountIdObj.toString()));//修改状态的方法*/
                    return new ApiResponse<String>(ApiMsgEnum.SUCCESS, 1, returnCode);
                } else {
                    System.out.println("return_code=" + map.get("return_code"));
                    System.out.println("return_msg=" + map.get("return_msg"));
                    //如果没有支付成功，则反馈给客户端支付结果
                    return new ApiResponse<String>(ApiMsgEnum.FailPay, 1, returnCode);
                }
            }
        }catch (Exception ex){
            return  new ApiResponse<String>(ApiMsgEnum.BAD_REQUEST);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 根据id查询单条提现详情
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据id查询单条提现详情" ,value = "backend-withdrawals-info-by-id")
    @Override
    public ApiResponse<WithdrawalsInfo> searchwithdrawalsInfoById(ApiRequest apiReq){
        WithdrawalsInfo withdrawalsInfo = withdrawalsInfoMapper.selectByPrimaryKey(apiReq.getLong("withdrawalsId"));
        return new ApiResponse<WithdrawalsInfo>(ApiMsgEnum.SUCCESS, 1, withdrawalsInfo);
    }

}
