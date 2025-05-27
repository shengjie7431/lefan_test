package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lefancrm.apicenter.backendapi.BackendPinganfuApi;
import com.lefancrm.apicenter.cache.CacheManager;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.*;
import com.lefancrm.apicenter.enums.OperateTypeEnum;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.CommonService;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.SerialNumberUtil;
import com.lefancrm.apicenter.util.pinganfu.*;
import com.lefancrm.apicenter.util.pinganfu.http.SubmitProxy;
import com.lefancrm.apicenter.vo.*;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiParam;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixianfeng on 2018/6/26.
 */
@Service
@ApiService(descript = "平安付相关API")
public class BackendPinganfuApiImpl implements BackendPinganfuApi{
    private String regionAccountURL = "https://test-www.stg.1qianbao.com/caps/request.do";//域账号注册 请求
    private String cardIndexURL = "https://test-ccdc.stg.1qianbao.com:23443/ccdc-web/bankCard/index";//获取卡索引 请求
    private String bindCardURL = "https://test-mzone.stg.yqb.com/mzone-http/bind_card/bind_card_entrance_for_outer";//绑卡URL
    private String authCardURL = "https://test-www.1qianbao.com/caps/request.do";//授权卡URL

    @Value("${pingan_server_returnURL}")
    private String returnURL;//绑卡回调接口
    @Value("${pingan_withHoldURL}")
    private String withHoldURL;


    //注册域账号(1)配置参数
    @Value("${pingan_serviceCode}")
    private String serviceCode = "R0568";
    @Value("${pingan_systemId}")
    private String systemId = "LEFAN";
    @Value("${pingan_hashFunc}")
    private String hashFunc = "SHA-1";
    @Value("${pingan_salt}")
    private String salt = "";
    @Value("${pingan_encrypt}")
    private String encrypt = "AES";
    //注册域账号(2)
    @Value("${pingan_channel}")
    private String channel = "Y-100054";
    //    @Value("${pingan_system}")
    private String system = "乐凡";
    @Value("${pingan_coOperCode}")
    private String coOperCode = "0077";//数据来源
    @Value("${pingan_merchantNo}")
    private String merchantNo = "900000112448";//对应商户号
    //    @Value("${pingan_partnerId}")
//    private String partnerId = "2000010003620066";//商户会员编号
    //绑卡
    @Value("${pingan_bindCardVERSION}")
    private String bindCardVERSION = "1.0";//版本号
    @Value("${pingan_capital_index_card_no}")
    private String capital_index_card_no = "89050078801100000030";//对公账号--A7D91AC82E78FD8DBC2246AB8BA7A558
    //    @Value("${pingan_capital_name}")
    private String capital_name = "上海乐凡金融信息服务有限公司";

    @Value("${pingan_key}")
    private String key = "DF84CBDCA294DC5DEF1368E64313FD3B98FE5EBCAB7D46AE";
    @Value("${pingan_private_key}")
    private String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANmIwlODpqQ79tELqfbP+x" +
            "7GGlPLuUdRsCiTajdoVcTTQ5znjH0sR8EGULucUaQaKiGhi3oLH0gJ0G6PWbXGszkrfj4xNXaxKrv6XhgEFrLr/3X15" +
            "77Mc2i8ir9e+mfs8D5lJqw0/ghsMTmKaS1Ktg+VytdbKLY65svSqG3APVh5AgMBAAECgYB0QqUpIUlkqS+mVgeGg22A6vuT" +
            "IWRqe3wck3zHFBoS0Z5nM1FgbNQEk61+N3NisyElTENQ/LTBOY2OKUsW3ZVW0enx7EMVOxX7lWh0/W0QlT62y4jIcUXwSZs" +
            "Mhf9Y/2C8+6iC50Nwqcfy4vM/2zQILlff+Fsy+xg2dbO8cenRKQJBAPthFRbsNFx2BMVWZ3EDumFy6z2TDx4eQnJUkP8ikw" +
            "9q0ONTvIZf8Ybdw64BCOfvO4YTk6dk6/2MxjC4kYrATvcCQQDdiGlyxkJFa+ogBiql24LdyZIOaufEzBjcCvSkMksSzgCHeFif" +
            "0mgh0wJ6Ds6xiahhhCTfK73MPuUQnu96RwgPAkEA+0t61LZ+RawpasjMkbrjWEWUirJ3W2lujXKinJsInlHuzkJIZa" +
            "CGB4er9UfNimaqf4cUhTOrgoV3Hv9zZ1yF0wJAGYVjkINikxjRigrr0tutAdv85YhPVw6kNoVUla3tlCcDTJgCrbbRpEPo3yLhk" +
            "PZo3YMZtFQXs3Xzcay9rfqM5QJBAOfs5+DErNmFWgPG/z5W+ZlDRl2eLdiNB/Uzd8Z3zvvGy7qffApR8k/KQDJ8DPpq+tllWJkQ+" +
            "sYNryThOmkAK1c=";

    @Autowired
    private CommonService commonService;
    @Autowired
    private PinganAreaAccountMapper pinganAreaAccountMapper;
    @Autowired
    private CardInfoDtoMapper cardInfoDtoMapper;
    @Autowired
    private PinganAuthInfoMapper pinganAuthInfoMapper;
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    @Autowired
    private PaymentEstimateInquiryMapper paymentEstimateInquiryMapper;
    @Autowired
    private PinganBindCardMapper pinganBindCardMapper;
    @Autowired
    private PinganRepayInfoMapper pinganRepayInfoMapper;
    /**
     * 初始化  公共参数 设置
     * @return
     */
    private BaseReqVO getBaseReqVO(String url){
        BaseReqVO baseReqVO = new BaseReqVO();
        baseReqVO.setUrlValue(url);
        baseReqVO.setServiceCode(serviceCode);
        baseReqVO.setSystemId(systemId);
        baseReqVO.setHashFunc(hashFunc);
        baseReqVO.setSalt(salt);
        baseReqVO.setToken(null);
        baseReqVO.setEncrypt(encrypt);
        baseReqVO.setKey(key);
        baseReqVO.setContent(null);
        baseReqVO.setPrivateKey(privateKey);
        return baseReqVO;
    }

    @ApiMethod(descript = "获取平安付域账号注册信息", value = "pinganfu-get-region-account" ,
            apiParams = {@ApiParam(name = "user_token",descript = "当前用户token(*)")})
    @Override
    public ApiResponse getRegionAccount(ApiRequest request) {
        Long caseId = request.getLong("caseId");
        PinganAreaAccount pinganAreaAccount = pinganAreaAccountMapper.selectByCaseId(caseId);
        if (pinganAreaAccount == null){
            CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseId);
            pinganAreaAccount = new PinganAreaAccount();
            pinganAreaAccount.setCaseId(caseId);
            pinganAreaAccount.setCaseNo(caseCenterInfo.getCaseNo());
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,pinganAreaAccount);
    }

    @ApiMethod(descript = "平安付域账号注册", value = "pinganfu-region-account" ,
            apiParams = {@ApiParam(name = "user_token",descript = "当前用户token(*)")})
    @Override
    public ApiResponse regionAccount(ApiRequest request) {
        ResultVO<R0568RespVO> resultVO = new ResultVO<R0568RespVO>();
        R0568RespVO respVO = new R0568RespVO();
        resultVO.setSuccess(false);
        resultVO.setMessage("系统异常");
        resultVO.setEntity(respVO);
        Long caseId = request.getLong("caseId");
        String customerId = request.getString("customerId");
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseId);
        PinganAreaAccount pinganAreaAccount = pinganAreaAccountMapper.selectByCaseId(caseId);
        //customerId 说明已经注册域账号 直接跳转至 绑卡界面
        if (StringUtils.isEmpty(customerId)){
            R0568ReqVO r0568ReqVO = new R0568ReqVO();
            BeanUtils.copyProperties(getBaseReqVO(regionAccountURL),r0568ReqVO);//域账号注册请求
//            BeanUtils.copyProperties(request,r0568ReqVO);//请求业务参数转换
            ConvertToBeanUtil.toBeanFromApiRequest(request,r0568ReqVO);//请求业务参数转换
            r0568ReqVO.setChannel(channel);
            r0568ReqVO.setSystem(system);
            r0568ReqVO.setCoOperCode(coOperCode);
            r0568ReqVO.setMerchantNo(merchantNo);
            r0568ReqVO.setPartnerId(SerialNumberUtil.generSeqCode());
            //获取Token
            ResultVO<String> hashFuncR0568 = hashFuncR0568(r0568ReqVO);
            if (!hashFuncR0568.isSuccess()){
                resultVO.setMessage(hashFuncR0568.getMessage());
            }
            r0568ReqVO.setToken(hashFuncR0568.getEntity());
            //加密内容
            ResultVO<String> aesR0568 = aesR0568(r0568ReqVO);
            if (!aesR0568.isSuccess()){
                resultVO.setMessage(aesR0568.getMessage());
            }
            String errorMsg = null;
            r0568ReqVO.setContent(aesR0568.getEntity());
            if (hashFuncR0568.isSuccess() && aesR0568.isSuccess()){
                CapsReqDTO capsReqDTO = new CapsReqDTO();
                capsReqDTO.setContent(r0568ReqVO.getContent());
                capsReqDTO.setToken(r0568ReqVO.getToken());
                capsReqDTO.setHashFunc(r0568ReqVO.getHashFunc());
                capsReqDTO.setServiceCode(r0568ReqVO.getServiceCode());
                capsReqDTO.setSystemId(r0568ReqVO.getSystemId());
                String capsR0568Request = JSON.toJSONString(capsReqDTO);
                String response = null;
                try {
                    response = SubmitProxy.postContentHttp(capsR0568Request, r0568ReqVO.getUrlValue(), null);
                    CapsRespDTO capsRespDTO = JSON.parseObject(response, new TypeReference<CapsRespDTO>() {});
                    resultVO.setMessage("CAPS返回信息码：" + capsRespDTO.getCode() + "，信息：" + capsRespDTO.getMemo());
                    errorMsg = resultVO.getMessage();
                    if ("000000".equals(capsRespDTO.getCode())){
                        String capsRespContent = capsRespDTO.getContent();
                        String r0568Resp = AESUtils.decrypt4Aes2Str(capsRespContent, r0568ReqVO.getKey());
                        R0568RespVO r0568RespVO = JSON.parseObject(r0568Resp, new TypeReference<R0568RespVO>() {});
                        resultVO.setEntity(r0568RespVO);
                        resultVO.setSuccess(true);
                        resultVO.setMessage("CAPS返回信息码：" + capsRespDTO.getCode() + "，信息：" + capsRespDTO.getMemo());
                        //保存域账户信息表
                        if (pinganAreaAccount == null){
                            pinganAreaAccount = new PinganAreaAccount();
                            BeanUtils.copyProperties(r0568ReqVO,pinganAreaAccount);
                            pinganAreaAccount.setCustomerId(r0568RespVO.getCustomerId());
                            pinganAreaAccount.setCaseId(caseId);
                            pinganAreaAccount.setCaseNo(caseCenterInfo.getCaseNo());
                            if ("M".equals(r0568ReqVO.getSex())){
                                pinganAreaAccount.setSex(1);
                            }else if ("F".equals(r0568ReqVO.getSex())){
                                pinganAreaAccount.setSex(2);
                            }
                            try {
                                Date date = DateUtils.parseDate(request.getString("birthDate"),"yyyy-MM-dd");
                                pinganAreaAccount.setBirthDate(date);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            pinganAreaAccount.setIsSuccess(1);
                            pinganAreaAccount.setCreateTime(new Date());
                            pinganAreaAccountMapper.insert(pinganAreaAccount);
                        }else {

                        }
                        return new ApiResponse(ApiMsgEnum.SUCCESS,1,resultVO);
                    }
                } catch (Exception e) {
                    resultVO.setMessage("调用域账户注册接口异常");
                    errorMsg = resultVO.getMessage();
                }
            }

            if (!hashFuncR0568.isSuccess()){
                errorMsg = hashFuncR0568.getMessage();
            }else if (!aesR0568.isSuccess()){
                errorMsg = aesR0568.getMessage();
            }
            //修改或新增域账户注册信息
            if (pinganAreaAccount == null){
                pinganAreaAccount = new PinganAreaAccount();
                BeanUtils.copyProperties(r0568ReqVO,pinganAreaAccount);
                pinganAreaAccount.setCaseId(caseId);
                pinganAreaAccount.setCaseNo(caseCenterInfo.getCaseNo());
                pinganAreaAccount.setIsSuccess(0);
                pinganAreaAccount.setCreateTime(new Date());
                pinganAreaAccount.setRemark(errorMsg);
                pinganAreaAccountMapper.insert(pinganAreaAccount);
            }else{
                BeanUtils.copyProperties(r0568ReqVO,pinganAreaAccount);
                pinganAreaAccount.setRemark(errorMsg);
                pinganAreaAccount.setCreateTime(new Date());
                pinganAreaAccountMapper.updateByPrimaryKeySelective(pinganAreaAccount);
            }
        }else{
            BeanUtils.copyProperties(request, pinganAreaAccount);
            //注册暂时不可保存
//            pinganAreaAccountMapper.updateByPrimaryKeySelective(pinganAreaAccount);
            resultVO.setSuccess(true);
            resultVO.setMessage("成功");
            respVO = new R0568RespVO();
            respVO.setCustomerId(customerId);
            resultVO.setEntity(respVO);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,resultVO);
    }

    /**
     * 域账户注册获取TOKEN
     * @return
     */
    private ResultVO<String> hashFuncR0568(R0568ReqVO r0568ReqVO) {
        String hashFunc = r0568ReqVO.getHashFunc();
        String salt = r0568ReqVO.getSalt();
        R0568DTO r0568DTO = new R0568DTO();
        BeanUtils.copyProperties(r0568ReqVO, r0568DTO);
        ResultVO<String> resultVO = new ResultVO<String>();
        String result = null;
        boolean success = true;
        String message = "域账户注册计算token成功";
        try {
            result = commonService.hashFunc(r0568DTO, hashFunc, salt, Constants.CHARSET);
            if (StringUtils.isBlank(result)) {
                success = false;
                message = "域账户注册签约计算token为空";
            }
        } catch (Exception e) {
            success = false;
            message = "域账户注册签约算token失败";
        }
        resultVO.setSuccess(success);
        resultVO.setEntity(result);
        resultVO.setMessage(message);
        return resultVO;
    }

    /**
     *域账户注册获取加密CONTENT
     * @param r0568ReqVO
     * @return
     */
    private ResultVO<String> aesR0568(R0568ReqVO r0568ReqVO) {
        String key = r0568ReqVO.getKey();
        R0568DTO r0568DTO = new R0568DTO();
        BeanUtils.copyProperties(r0568ReqVO, r0568DTO);
        ResultVO<String> resultVO = new ResultVO<String>();
        String result = null;
        boolean success = true;
        String message = "域账户注册AES加密成功";
        try {
            result = commonService.aes(r0568DTO, key);
            if (StringUtils.isBlank(result)) {
                success = false;
                message = "域账户注册AES加密为空";
            }
        } catch (Exception e) {
            success = false;
            message = "域账户注册AES加密失败";
        }
        resultVO.setSuccess(success);
        resultVO.setEntity(result);
        resultVO.setMessage(message);
        return resultVO;
    }

    /**
     *计算Token R0622接口计算token(hashFuncR0622)
     * @return
     */
    public String interCaclToken(){
        return null;
    }
    /**
     * 加密  R0622接口加密(aesR0622)
     * @return
     */
    private String interRegPwd(){
        return null;
    }

    @ApiMethod(descript = "获取平安付绑卡的卡信息", value = "pinganfu-get-bind-card" ,
            apiParams = {@ApiParam(name = "user_token",descript = "当前用户token(*)")})
    @Override
    public ApiResponse getBindCard(ApiRequest request) {
        String customerId = request.getString("customerId");//根据平安付会员号获取卡信息
        CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByCustomerId(customerId);
        if (cardInfoDto == null){
            cardInfoDto = new CardInfoDto();
            cardInfoDto.setCustomerId(customerId);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,cardInfoDto);
    }

    /**
     * 页面点击绑卡  FORM请求平安付URL,发送请求前通过异步方式,调用此处接口,接口返回参数数据.前端通过现有参数 与 平安付URL 提交FORM表单
     * 参数包含其他 以及 返回地址(returnUrl), 返回地址URL 保存银行卡信息至本地
     * @param request
     * @return
     */
    @ApiMethod(descript = "平安付绑卡", value = "pinganfu-bind-card" ,
            apiParams = {@ApiParam(name = "user_token",descript = "当前用户token(*)")})
    @Override
    public ApiResponse bindCard(ApiRequest request) {
        ResultVO<BindCardDTO> resultBindCard = new ResultVO<BindCardDTO>();
        String customerId = request.getString("customerId");
//        PinganBindCard pinganBindCard = pinganBindCardMapper.selectByCardNo(request.getString("cardNo"));
        PinganBindCard pinganBindCard = pinganBindCardMapper.selectByCustomerId(customerId);
        if (pinganBindCard == null || pinganBindCard.getIsSuccess() == 0){
            PinganAreaAccount pinganAreaAccount = pinganAreaAccountMapper.selectByCustomerId(customerId);
            if (pinganAreaAccount == null){
                pinganAreaAccount = new PinganAreaAccount();
                pinganAreaAccount.setCustomerId(customerId);
            }
            //获取卡索引
            CardIndexReqVO cardIndexReqVO = new CardIndexReqVO();
            cardIndexReqVO.setCardIndexUrlValue(cardIndexURL);
//            BeanUtils.copyProperties(request,cardIndexReqVO);
//            ConvertToBeanUtil.toBeanFromApiRequest(request,cardIndexReqVO);
            cardIndexReqVO.setCardNo(request.getString("cardNo"));
            cardIndexReqVO.setCvv2(request.getString("cvv"));
            String expYear = request.getString("expYear") == null ? "" : request.getString("expYear");
            String expMonth = request.getString("expMonth") == null ? "" : request.getString("expMonth");
            if (!"".equals(expYear)){
                cardIndexReqVO.setExpDate(expYear.concat("-").concat(expMonth).concat("-01"));
            }
            //获取卡索引
            ResultVO<CardIndexRespVO> resultCardIndexVO = getCardIndex(cardIndexReqVO);
            if (!resultCardIndexVO.isSuccess()){
                resultBindCard = new ResultVO<BindCardDTO>();
                resultBindCard.setSuccess(false);
                resultBindCard.setMessage(resultCardIndexVO.getMessage());
                resultBindCard.setEntity(new BindCardDTO());
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,resultBindCard);
            }

            //得到定制化信息
            ExtInfoVO extInfoVO = new ExtInfoVO();

//            BeanUtils.copyProperties(request,extInfoVO);
//            ConvertToBeanUtil.toBeanFromApiRequest(request,extInfoVO);
            extInfoVO.setBankCode(request.getString("bankCode"));
            extInfoVO.setCardNum(resultCardIndexVO.getEntity().getCardSeqId());
            extInfoVO.setCardTypeCode(request.getString("cardType"));
            extInfoVO.setIdentityNum(request.getString("certNo"));
            extInfoVO.setCustomerId(pinganAreaAccount.getCustomerId());
            extInfoVO.setName(request.getString("cardHolderName"));
            ResultVO<String> resultExtInfo = getRequestMsg(extInfoVO);
            if (!resultExtInfo.isSuccess()){
                resultBindCard = new ResultVO<BindCardDTO>();
                resultBindCard.setSuccess(false);
                resultBindCard.setMessage(resultExtInfo.getMessage());
                resultBindCard.setEntity(new BindCardDTO());
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,resultBindCard);
            }

            //加密定制化信息
            ResultVO<String> rsaExtInfoPlain = rsaRequestMsg(resultExtInfo.getEntity(),privateKey);
            if (!rsaExtInfoPlain.isSuccess()){
                resultBindCard = new ResultVO<BindCardDTO>();
                resultBindCard.setSuccess(false);
                resultBindCard.setMessage(rsaExtInfoPlain.getMessage());
                resultBindCard.setEntity(new BindCardDTO());
                return new ApiResponse(ApiMsgEnum.SUCCESS,1,resultBindCard);
            }

            //返回绑卡DTO
            resultBindCard = new ResultVO<BindCardDTO>();
            BindCardDTO bindCardDTO = new BindCardDTO();
            bindCardDTO.setBindCardUrlValue(bindCardURL);
            bindCardDTO.setMerchantNo(pinganAreaAccount.getMerchantNo());
            bindCardDTO.setVersion(bindCardVERSION);
            bindCardDTO.setReturnUrl(returnURL);
            bindCardDTO.setCancelUrl(null);
            bindCardDTO.setRequestMsg(rsaExtInfoPlain.getEntity());
            bindCardDTO.setRequestNo("123321");
            bindCardDTO.setExtfield(null);
            resultBindCard.setSuccess(true);
            resultBindCard.setEntity(bindCardDTO);
            if (pinganBindCard == null){
                pinganBindCard = new PinganBindCard();
                BeanUtils.copyProperties(cardIndexReqVO,pinganBindCard);
                BeanUtils.copyProperties(extInfoVO, pinganBindCard);
                pinganBindCard.setCaseId(pinganAreaAccount.getCaseId());
                pinganBindCard.setCaseNo(pinganAreaAccount.getCaseNo());
                pinganBindCard.setCvv(cardIndexReqVO.getCvv2());
                if (cardIndexReqVO.getExpDate() != null && !"".equals(cardIndexReqVO.getExpDate())){
                    Date date = DateUtils.parseDate(cardIndexReqVO.getExpDate(),"yyyy-MM-dd");
                    pinganBindCard.setExpDate(date);
                }
                pinganBindCard.setIsSuccess(0);
                pinganBindCard.setRemark("默认失败");
                pinganBindCard.setRequestMsg(resultExtInfo.getEntity());
                pinganBindCardMapper.insert(pinganBindCard);
            }else{
                BeanUtils.copyProperties(cardIndexReqVO,pinganBindCard);
                BeanUtils.copyProperties(extInfoVO,pinganBindCard);
                pinganBindCard.setCvv(cardIndexReqVO.getCvv2());
                if (cardIndexReqVO.getExpDate() != null && !"".equals(cardIndexReqVO.getExpDate())){
                    Date date = DateUtils.parseDate(cardIndexReqVO.getExpDate(),"yyyy-MM-dd");
                    pinganBindCard.setExpDate(date);
                }
                pinganBindCardMapper.updateByPrimaryKey(pinganBindCard);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,resultBindCard);
        }else{
            resultBindCard.setSuccess(true);
            BindCardDTO bindCardDTO = new BindCardDTO();
            bindCardDTO.setSendPost(false);//不发送请求
            bindCardDTO.setCaseId(pinganBindCard.getCaseId());
            bindCardDTO.setCustomerId(pinganBindCard.getCustomerId());
            bindCardDTO.setUserIndexCardNo(pinganBindCard.getCardNum());
            resultBindCard.setEntity(bindCardDTO);
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,resultBindCard);
        }
    }

    @ApiMethod(descript = "平安付绑卡是否成功", value = "pinganfu-bind-card-result" ,
            apiParams = {@ApiParam(name = "user_token",descript = "当前用户token(*)")})
    @Override
    public ApiResponse bindCardResult(ApiRequest request) {
        String customerId = request.getString("customerId");
//        String userIndexCardNo = request.getString("userIndexCardNo");
//        Long caseId = request.getLong("caseId");
        ResultVO<BindCardResultDTO> resultVO = new ResultVO<>();
        resultVO.setSuccess(true);
        resultVO.setMessage(null);
        BindCardResultDTO bindCardResultDTO = new BindCardResultDTO();
        bindCardResultDTO.setCustomerId(customerId);
//        bindCardResultDTO.setUserIndexCardNo(userIndexCardNo);
//        bindCardResultDTO.setCaseId(caseId);
        CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByCustomerId(customerId);
        if (cardInfoDto == null){
            bindCardResultDTO.setSuccess(false);
        }else{
            bindCardResultDTO.setSuccess(true);
        }
        resultVO.setEntity(bindCardResultDTO);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,resultVO);
    }

    /**
     * 获取卡索引
     * @param cardIndexReqVO
     * @return
     */
    private ResultVO<CardIndexRespVO> getCardIndex(CardIndexReqVO cardIndexReqVO) {
        ResultVO<CardIndexRespVO> resultVO = new ResultVO<CardIndexRespVO>();
        CardIndexRespVO respVO = new CardIndexRespVO();
        resultVO.setEntity(respVO);
        String url = cardIndexReqVO.getCardIndexUrlValue();
        StringBuilder uriSb = new StringBuilder("?");
        if (StringUtils.isNotBlank(cardIndexReqVO.getCardNo())) {
            uriSb.append("cardNo=").append(cardIndexReqVO.getCardNo());
        }
        if (StringUtils.isNotBlank(cardIndexReqVO.getCvv2())) {
            uriSb.append("&cvv2=").append(cardIndexReqVO.getCvv2());
        }
        if (StringUtils.isNotBlank(cardIndexReqVO.getExpDate())) {
            uriSb.append("&expDate=").append(cardIndexReqVO.getExpDate());
        }
        String uri = url + uriSb.toString();
        String response = null;
        try {
            response = HttpClientUtil.executeGet(uri);
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setMessage("调用换取卡索引接口通讯异常");
            return resultVO;
        }
        CardIndexRespVO cardIndexRespVO = JSON.parseObject(response, new TypeReference<CardIndexRespVO>() {});
        if (cardIndexRespVO == null){
            cardIndexRespVO = new CardIndexRespVO();
        }
        resultVO.setEntity(cardIndexRespVO);
        resultVO.setSuccess(true);
        resultVO.setMessage("换取卡索引成功");
        return resultVO;
    }

    /**
     * 生成定制化信息
     * @param extInfoVO
     * @return
     */
    private ResultVO<String> getRequestMsg(ExtInfoVO extInfoVO) {
        ExtInfoDTO extInfoDTO = new ExtInfoDTO();
        BeanUtils.copyProperties(extInfoVO, extInfoDTO);
        String customerId = extInfoVO.getCustomerId();
        String extInfoJson = JSON.toJSONString(extInfoDTO);
        StringBuilder requestMsgSb = new StringBuilder();
        if (StringUtils.isNotBlank(extInfoJson)) {
            requestMsgSb.append("extInfo=").append(extInfoJson).append("&");
        }
        requestMsgSb.append("operateType=").append(OperateTypeEnum.SIGN.getCode());
        if (StringUtils.isNotBlank(customerId)) {
            requestMsgSb.append("&customerId=").append(customerId);
        }
        ResultVO<String> resultVO = new ResultVO<String>();
        resultVO.setSuccess(true);
        resultVO.setMessage("获取定制信息成功");
        resultVO.setEntity(requestMsgSb.toString());
        return resultVO;
    }

    /**
     * 定制化信息加密
     * @param requestMsg
     * @param privateKey
     * @return
     */
    private ResultVO<String> rsaRequestMsg(String requestMsg,String privateKey) {
        CacheManager.putCache("privateKey", privateKey);
        ResultVO<String> resultVO = new ResultVO<String>();
        String base64CipherText = null;
        try {
            if (StringUtils.isNotBlank(requestMsg) && StringUtils.isNotBlank(privateKey)) {
                base64CipherText = DigestUtils.encryptBASE64(RsaUtils.encryptByPrivateKey(requestMsg.getBytes(Constants.CHARSET), privateKey));
                resultVO.setSuccess(true);
                resultVO.setMessage("RSA加密定制化信息成功");
                resultVO.setEntity(base64CipherText);
            } else {
                resultVO.setSuccess(false);
                resultVO.setMessage("获取定制化信息或密钥失败");
            }
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setMessage("RSA加密定制化信息失败");
            return resultVO;
        }
        return resultVO;
    }

    @ApiMethod(descript = "授权卡信息", value = "pinganfu-get-auth-card" ,
            apiParams = {@ApiParam(name = "user_token",descript = "当前用户token(*)")})
    @Override
    public ApiResponse getAuthCard(ApiRequest request) {
        String customerId = request.getString("customerId");
//        String userIndexCardNo = request.getString("userIndexCardNo");
//        Long caseId = request.getLong("caseId");
//        PinganAuthInfo params = new PinganAuthInfo();
//        params.setCustomerId(customerId);
//        params.setUserIndexCardNo(userIndexCardNo);
        PinganAuthInfo pinganAuthInfo = pinganAuthInfoMapper.selectByCustomerId(customerId);
        CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByCustomerId(customerId);
        if (pinganAuthInfo == null){
            pinganAuthInfo = new PinganAuthInfo();
            pinganAuthInfo.setMerchantId(merchantNo);
            pinganAuthInfo.setCustomerId(customerId);
            pinganAuthInfo.setUserIndexCardNo(cardInfoDto.getCardIndex());

            //获取对公账号卡索引
            CardIndexReqVO cardIndexReqVO = new CardIndexReqVO();
            cardIndexReqVO.setCardIndexUrlValue(cardIndexURL);
//            BeanUtils.copyProperties(request,cardIndexReqVO);
//            ConvertToBeanUtil.toBeanFromApiRequest(request,cardIndexReqVO);
            cardIndexReqVO.setCardNo(capital_index_card_no);
            ResultVO<CardIndexRespVO> resultCardIndexVO = getCardIndex(cardIndexReqVO);
            pinganAuthInfo.setCapitalIndexCardNo(resultCardIndexVO.getEntity().getCardSeqId());
            pinganAuthInfo.setCapitalIndexCard(capital_index_card_no);
            pinganAuthInfo.setCapitalName(capital_name);

            pinganAuthInfo.setLoanAgreementNo(SerialNumberUtil.generLoanAgreementCode("AM"));
            CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(cardInfoDto.getCaseId());
            Map map = new HashMap();
            map.put("caseId",caseCenterInfo.getCaseId());
            map.put("caseType",caseCenterInfo.getType());
            Double amount = 0D;
            PaymentEstimateInquiry paymentEstimateInquiry = paymentEstimateInquiryMapper.selectPaymentEstimateInquiryByPeId(map);
            if (paymentEstimateInquiry != null){
                amount = paymentEstimateInquiry.getTotalLoanFee();
            }
            pinganAuthInfo.setLoanAmt(amount);//不乘1000000
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,pinganAuthInfo);
    }

    @ApiMethod(descript = "授权卡信息", value = "****pinganfu-auth-card" ,
            apiParams = {@ApiParam(name = "user_token",descript = "当前用户token(*)")})
    @Override
    public ApiResponse authCard(ApiRequest request) {
        ResultVO<PinganAuthInfo> resultVO = new ResultVO<>();
        resultVO.setSuccess(false);
        resultVO.setMessage("出现异常,请联系管理员");
        resultVO.setEntity(new PinganAuthInfo());
        String customerId = request.getString("customerId");
//        String userIndexCardNo = request.getString("userIndexCardNo");
//        PinganAuthInfo params = new PinganAuthInfo();
//        params.setCustomerId(customerId);
//        params.setUserIndexCardNo(userIndexCardNo);
//        PinganAuthInfo pinganAuthInfo = pinganAuthInfoMapper.selectByParams(params);
        PinganAuthInfo pinganAuthInfo = pinganAuthInfoMapper.selectByCustomerId(customerId);
        //表示授权成功
        if (pinganAuthInfo != null && pinganAuthInfo.getIsSuccess() != null && pinganAuthInfo.getIsSuccess() == 1){
            resultVO.setSuccess(true);
            resultVO.setEntity(pinganAuthInfo);
        }else{
            AuthInfoReqDTO authInfoReqDTO = new AuthInfoReqDTO();
//            BeanUtils.copyProperties(request,authInfoReqDTO);
            ConvertToBeanUtil.toBeanFromApiRequest(request,authInfoReqDTO);
            authInfoReqDTO.setReqSerial("1000000001");
            String authInfoRequest = JSON.toJSONString(authInfoReqDTO);
            try {
                String response = SubmitProxy.postContentHttp(authInfoRequest, authCardURL, null);
                AuthinfoRespDTO authinfoRespDTO = JSON.parseObject(response, new TypeReference<AuthinfoRespDTO>() {});
                if (authinfoRespDTO == null){
                    authinfoRespDTO = new AuthinfoRespDTO();
                }
                if ("000000".equals(authinfoRespDTO.getRespCode())){
                    if (pinganAuthInfo == null){
                        pinganAuthInfo = new PinganAuthInfo();
                        BeanUtils.copyProperties(request, pinganAuthInfo);
                        pinganAuthInfo.setReqSerial(getNum().concat("0001"));
                        pinganAuthInfo.setMerchantId(merchantNo);
                        pinganAuthInfo.setCreateTime(new Date());
                        pinganAuthInfo.setIsSuccess(1);
                        pinganAuthInfoMapper.insert(pinganAuthInfo);
                    }else{
                        pinganAuthInfo.setCreateTime(new Date());
                        pinganAuthInfo.setIsSuccess(1);
                        pinganAuthInfoMapper.updateByPrimaryKey(pinganAuthInfo);
                    }
                    resultVO.setSuccess(true);
                    resultVO.setEntity(pinganAuthInfo);
                }else{
                    if (pinganAuthInfo == null){
                        pinganAuthInfo = new PinganAuthInfo();
                        BeanUtils.copyProperties(request, pinganAuthInfo);
                        pinganAuthInfo.setReqSerial(getNum().concat("0001"));
                        pinganAuthInfo.setMerchantId(merchantNo);
                        pinganAuthInfo.setCreateTime(new Date());
                        pinganAuthInfo.setIsSuccess(0);
                        pinganAuthInfo.setRemark(authinfoRespDTO.getRespMsg());
                        pinganAuthInfoMapper.insert(pinganAuthInfo);
                    }else{
                        pinganAuthInfo.setCreateTime(new Date());
                        pinganAuthInfo.setIsSuccess(0);
                        pinganAuthInfo.setRemark(authinfoRespDTO.getRespMsg());
                        pinganAuthInfoMapper.updateByPrimaryKey(pinganAuthInfo);
                    }
                    resultVO.setSuccess(false);
                    resultVO.setMessage(authinfoRespDTO.getRespMsg());
                    resultVO.setEntity(pinganAuthInfo);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,resultVO);
    }


    @ApiMethod(descript = "授权卡信息", value = "pinganfu-auth-card" ,
            apiParams = {@ApiParam(name = "user_token",descript = "当前用户token(*)")})
    @Override
    public ApiResponse buildAuthCard(ApiRequest request) {
        ResultVO<PinganAuthInfo> resultVO = new ResultVO<>();
        resultVO.setSuccess(false);
        resultVO.setMessage("出现异常,请联系管理员");
        resultVO.setEntity(new PinganAuthInfo());
        String customerId = request.getString("customerId");
//        String userIndexCardNo = request.getString("userIndexCardNo");
//        PinganAuthInfo params = new PinganAuthInfo();
//        params.setCustomerId(customerId);
//        params.setUserIndexCardNo(userIndexCardNo);
//        PinganAuthInfo pinganAuthInfo = pinganAuthInfoMapper.selectByParams(params);
        PinganAuthInfo pinganAuthInfo = pinganAuthInfoMapper.selectByCustomerId(customerId);
        //表示授权成功
        if (pinganAuthInfo != null && pinganAuthInfo.getIsSuccess() != null && pinganAuthInfo.getIsSuccess() == 1){
            resultVO.setSuccess(true);
            resultVO.setEntity(pinganAuthInfo);
        }else{
            AuthInfoReqDTO authInfoReqDTO = new AuthInfoReqDTO();
//            BeanUtils.copyProperties(request,authInfoReqDTO);
            ConvertToBeanUtil.toBeanFromApiRequest(request,authInfoReqDTO);
            authInfoReqDTO.setReqSerial(getReqnum());
            authInfoReqDTO.setLoanAmt(authInfoReqDTO.getLoanAmt() * 1000000);
            String content = JSON.toJSONString(authInfoReqDTO);

            try {
                Map<String,Object> reqMap = new HashMap<>();
                reqMap.put("token",StringEncrypt.encryptPwd(content,hashFunc,"UTF-8"));
//                reqMap.put("content",commonService.aes(content,key));
                reqMap.put("content",commonService.aes(authInfoReqDTO,key));

                reqMap.put("hashFunc",hashFunc);
                reqMap.put("serviceCode","R0758");
                reqMap.put("systemId",systemId);
                String reqStr = JSON.toJSONString(reqMap);
                String response = SubmitProxy.postContentHttp(reqStr, authCardURL, null);
                Map responseMap = JSONObject.parseObject(response);
                String contentRes = responseMap.get("content").toString();
                String responseContent = AESUtils.decrypt4Aes2Str(contentRes,key);

                AuthinfoRespDTO authinfoRespDTO = JSON.parseObject(responseContent, new TypeReference<AuthinfoRespDTO>() {});
                if (authinfoRespDTO == null){
                    authinfoRespDTO = new AuthinfoRespDTO();
                }
                if ("000000".equals(authinfoRespDTO.getRespCode())){
                    if (pinganAuthInfo == null){
                        pinganAuthInfo = new PinganAuthInfo();
//                        BeanUtils.copyProperties(request, pinganAuthInfo);
                        BeanUtils.copyProperties(authInfoReqDTO,pinganAuthInfo);
//                        ConvertToBeanUtil.toBeanFromApiRequest(request,pinganAuthInfo);
//                        pinganAuthInfo.setReqSerial(getNum().concat("0001"));
//                        pinganAuthInfo.setMerchantId(merchantNo);
                        pinganAuthInfo.setCreateTime(new Date());
                        pinganAuthInfo.setIsSuccess(1);
                        pinganAuthInfoMapper.insert(pinganAuthInfo);
                    }else{
                        BeanUtils.copyProperties(authInfoReqDTO,pinganAuthInfo);
                        pinganAuthInfo.setCreateTime(new Date());
                        pinganAuthInfo.setIsSuccess(1);
                        pinganAuthInfoMapper.updateByPrimaryKey(pinganAuthInfo);
                    }
                    resultVO.setSuccess(true);
                    resultVO.setEntity(pinganAuthInfo);
                }else{
                    if (pinganAuthInfo == null){
                        pinganAuthInfo = new PinganAuthInfo();
                        BeanUtils.copyProperties(authInfoReqDTO,pinganAuthInfo);
//                        ConvertToBeanUtil.toBeanFromApiRequest(request,pinganAuthInfo);
//                        pinganAuthInfo.setReqSerial(getNum().concat("0001"));
//                        pinganAuthInfo.setMerchantId(merchantNo);
                        pinganAuthInfo.setCreateTime(new Date());
                        pinganAuthInfo.setIsSuccess(0);
                        pinganAuthInfo.setRemark(authinfoRespDTO.getRespMsg());
                        pinganAuthInfoMapper.insert(pinganAuthInfo);
                    }else{
                        BeanUtils.copyProperties(authInfoReqDTO,pinganAuthInfo);
                        pinganAuthInfo.setCreateTime(new Date());
                        pinganAuthInfo.setIsSuccess(0);
                        pinganAuthInfo.setRemark(authinfoRespDTO.getRespMsg());
                        pinganAuthInfoMapper.updateByPrimaryKey(pinganAuthInfo);
                    }
                    resultVO.setSuccess(false);
                    resultVO.setMessage(authinfoRespDTO.getRespMsg());
                    resultVO.setEntity(pinganAuthInfo);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,resultVO);
    }

    private String  getNum(){
        return SerialNumberUtil.generSeqCode();
    }

    private String  getReqnum(){
        return SerialNumberUtil.generSeqCode();
    }

    /**
     * 平安代扣
     * @param cardInfoDto   卡信息
     * @param repaymentType 还款类型(01 还平台佣金 02还资方本息)
     * @param amount    还款金额
     * @return
     */
    public RepayRespDTO withHold(CaseCenterInfo caseCenterInfo,CardInfoDto cardInfoDto,String repaymentType,Double amount,Long currentUserId) {
        amount =  (amount == null ? 0 : amount);
        PinganAuthInfo pinganAuthInfo = pinganAuthInfoMapper.selectByCustomerId(cardInfoDto.getCustomerId());
        if (pinganAuthInfo == null){
            return null;//未授权
        }
        RepayReqDTO repayReqDTO = new RepayReqDTO();
        repayReqDTO.setReqSerial(getReqnum());
        repayReqDTO.setMerchantId(merchantNo);
        repayReqDTO.setRepaymentAgreementNo(pinganAuthInfo.getRepayLoanAgreementNo());
        repayReqDTO.setRepaymentType(repaymentType);
        repayReqDTO.setRepaymentAmt(new BigDecimal(amount * 1000000D));
        String content = JSON.toJSONString(repayReqDTO);

        try {
            Map<String,Object> reqMap = new HashMap<>();
            String token = StringEncrypt.encryptPwd(content,hashFunc,"UTF-8");
            reqMap.put("token",token);
//                reqMap.put("content",commonService.aes(content,key));
            reqMap.put("content",commonService.aes(repayReqDTO,key));
            reqMap.put("hashFunc",hashFunc);
            reqMap.put("serviceCode","R0759");
            reqMap.put("systemId",systemId);
            String reqStr = JSON.toJSONString(reqMap);
            String response = SubmitProxy.postContentHttp(reqStr, withHoldURL, null);
            Map responseMap = JSONObject.parseObject(response);
            String contentRes = responseMap.get("content").toString();
            String responseContent = AESUtils.decrypt4Aes2Str(contentRes,key);
            RepayRespDTO repayRespDTO = JSON.parseObject(responseContent, new TypeReference<RepayRespDTO>() {});
            if (repayRespDTO == null){
                repayRespDTO = new RepayRespDTO();
            }
            repayRespDTO.setReqNo(repayReqDTO.getReqSerial());
            PinganRepayInfo pinganRepayInfo = new PinganRepayInfo();
            BeanUtils.copyProperties(repayReqDTO,pinganRepayInfo);
            pinganRepayInfo.setRepaymentAmt(amount);
            pinganRepayInfo.setCreateTime(new Date());
            pinganRepayInfo.setCreateBy(currentUserId.toString());
            pinganRepayInfo.setToken(token);
            pinganRepayInfo.setSuccess("00");
            pinganRepayInfo.setRemark("默认失败");
            pinganRepayInfo.setCaseId(caseCenterInfo.getId());
            pinganRepayInfo.setCaseNo(caseCenterInfo.getCaseNo());
            pinganRepayInfoMapper.insert(pinganRepayInfo);
            return  repayRespDTO;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
