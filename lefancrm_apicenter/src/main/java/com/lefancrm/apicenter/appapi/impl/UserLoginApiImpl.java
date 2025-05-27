package com.lefancrm.apicenter.appapi.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lefancrm.apicenter.appapi.UserLoginApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.UserSessionDto;
import com.lefancrm.apicenter.model.BusUserRole;
import com.lefancrm.apicenter.model.SendSms;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.model.UserLogin;
import com.lefancrm.apicenter.service.RedisService;
import com.lefancrm.apicenter.service.SmsService;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.HttpClientUtils;
import com.lefancrm.apicenter.util.SendMessageUntil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiParam;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import com.lefancrm.base.utils.Md5Util;
import com.lefancrm.base.utils.RandomIDUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ting on 2017/12/15.
 */
@Service
@ApiService(descript = "用户登录相关API")
public class UserLoginApiImpl extends BaseServiceImpl implements UserLoginApi {
    private static final Logger loger = Logger.getLogger(UserLoginApiImpl.class);
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SmsService smsService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private SendSmsMapper sendSmsMapper;
    @Resource
    private PlatformTransactionManager platformTransactionManager;
    @Autowired
   private UserPromotedMapper userPromotedMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Value("${sms.repeat.time}")
    private Integer smsRepeatTime;
    @Value("${sms.expired.time}")
    private Integer smsExpiredTime;
    @Value("${mpwx_appId}")
    private String mpwxAppId;
    @Value("${mpwx_secret}")
    private String mpwxSecret;

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "用户登录", value = "crm-user-login", apiParams = { @ApiParam(descript = "手机号(*)", name = "userTelphone"),
            @ApiParam(descript = "用户密码(*)", name = "password"),
            @ApiParam(descript = "昵称", name = "nickName"), @ApiParam(descript = "头像", name = "img"),
            @ApiParam(descript = "用户openId", name = "openId")})
    @Override
    public ApiResponse login(ApiRequest apiReq) {
        String userTelphone = apiReq.getString("userTelphone");
        String password = apiReq.getString("password");
        String openId = apiReq.getString("openId");
        String nickName="";
        if(!StringUtils.isEmpty(apiReq.get("nickName"))){
            nickName=apiReq.getString("nickName");
        }
        String img="";
        if(!StringUtils.isEmpty(apiReq.get("img"))){
            img=apiReq.getString("img");
        }
        if (StringUtils.isEmpty(userTelphone) || StringUtils.isEmpty(password)) {
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        UserSessionDto sessionDto = new UserSessionDto();
       UserLogin user = this.userLoginMapper.selectByPhone(userTelphone);
        int userType = 0;
        try {
            //测试人员不需要做验证
            if(user != null){
                //######################### 角色校验 ##########################
                if(!isRole(user.getUserId())){
                    return new ApiResponse(ApiMsgEnum.FAIL);
                }
                //######################### 角色校验 ##########################

                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(user.getUserId());
                if(userInfo != null){
                    userType = userInfo.getUserType();
                }
            }else{
                // 用户未注册 返回登陆失败信息 需要注册
                return new ApiResponse(ApiMsgEnum.UsernameOrPasswordException);
            }
            if(userType != 3){ // 非测试用户 校验密码
                if(StringUtils.isEmpty(password) || !Md5Util.encodeString(password).equals(user.getPassword())){

                    return new ApiResponse(ApiMsgEnum.UsernameOrPasswordException);
                }
           }
            // 登陆成功用户
            Long userId = null;
            // 登陆成功处理
            if(user.getWechatId() == null || user.getWechatId().equals("")){
                user.setWechatId(openId);
                userLoginMapper.updateByPrimaryKey(user);
            }
            userId = user.getUserId();
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
            if(userInfo == null){
                return new ApiResponse(ApiMsgEnum.UserDosentExist);
            }
            if(userInfo.getUserState()==1){
                return new ApiResponse<UserSessionDto>(ApiMsgEnum.UserBeenLookedException, 1, sessionDto);
            }
            userInfo.setNickName(nickName);
            userInfo.setImg(img);

            if(userInfo.getNickName() != null && userInfo.getNickName().equals("")){
                userInfo.setNickName(null);
            }
            this.userInfoMapper.updateByPrimaryKeySelective(userInfo);

            // 这段代码的作用
            List<BusUserRole> busUserRoles = busUserRoleMapper.orgUserRoleList(userId);
            StringBuffer sb = new StringBuffer();
            if(busUserRoles != null && !busUserRoles.isEmpty()){
                for (BusUserRole busUserRole : busUserRoles){
                    sb.append(busUserRole.getRoleId() + ",");
                }
                sb.deleteCharAt(sb.length() - 1);
            }

            // session中存储用户信息
            sessionDto.setIcon(userInfo.getImg());
            sessionDto.setPhone(user.getUserTelphone());
            sessionDto.setNickname(this.getDisplayName(user.getUserTelphone(), userInfo.getNickName()));
            sessionDto.setUserType(userInfo.getUserType());
            sessionDto.setOrgName(userInfo.getOrgName());
            sessionDto.setOrgId(userInfo.getOrgId());
            sessionDto.setBsRoleIds(sb.toString());

            sessionDto.setUserId(userInfo.getUserId());
            sessionDto.setUserName(userInfo.getUserName());
            sessionDto.setUserTel(userInfo.getUserTel());
            sessionDto.setEmail(userInfo.getEmail());
            sessionDto.setUserState(userInfo.getUserState());
            sessionDto.setIsPromoter(userInfo.getIsPromoter());
            sessionDto.setDeleteFlag(userInfo.getDeleteFlag());
            sessionDto.setSex(userInfo.getSex());
            sessionDto.setUserAddress(userInfo.getUserAddress());
            sessionDto.setCreateTime(userInfo.getCreateTime());
            sessionDto.setCreateBy(userInfo.getCreateBy());
            sessionDto.setModifyTime(userInfo.getModifyTime());
            sessionDto.setModifyBy(userInfo.getModifyBy());
            sessionDto.setPromotedQrcode(userInfo.getPromotedQrcode());
            sessionDto.setImg(userInfo.getImg());
            sessionDto.setUserAccount(userInfo.getUserAccount());
            sessionDto.setUserProvinceId(userInfo.getUserProvinceId());
            sessionDto.setUserProvince(userInfo.getUserProvince());
            sessionDto.setUserCityId(userInfo.getUserCityId());
            sessionDto.setUserCity(userInfo.getUserCity());
            sessionDto.setUserDistrictId(userInfo.getUserDistrictId());
            sessionDto.setUserDistrict(userInfo.getUserDistrict());

            String loginToken = RandomIDUtil.getNewUUID();
            // 用户session写入缓存
            sessionDto.setUserToken(loginToken);
            redisService.setUserSession(loginToken, sessionDto);
            // 登陆成功返回session信息到前台
            return new ApiResponse<UserSessionDto>(ApiMsgEnum.LoginSuccess, 1, sessionDto);
        } catch (RuntimeException e) {
            loger.error(e);
		/*	platformTransactionManager.rollback(transactionStatus);*/
            return new ApiResponse(ApiMsgEnum.BAD_REQUEST);
        }

    }

    // 权限判断方法
    private boolean isRole(Long userId) {
        List<BusUserRole> roleList = busUserRoleMapper.orgUserRoleList(userId);
        if (roleList == null || roleList.isEmpty()) {
            return false;
        }
        for (BusUserRole sysUserRole : roleList) {
            if (sysUserRole.getRoleId() == 19 || sysUserRole.getRoleId() == 17 || sysUserRole.getRoleId() == 20 || sysUserRole.getRoleId() == 2 ) { // 总监权限
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "CRM用户退出", value = "crm-user-logout")
    @Override
    public ApiResponse logout(ApiRequest apiReq) {
        redisService.removeUserSession(apiReq.getUserToken());
        return new ApiResponse(ApiMsgEnum.LogoutSuccess);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = false, descript = "根据微信openID查询用户", value = "select-user-byCode",apiParams = { @ApiParam(descript = "微信客户端code(*)", name = "jsCode")})
    @Override
    public ApiResponse selectUserByOpenId(ApiRequest apiReq) throws IOException {
        String jsCode = apiReq.getString("jsCode");
        String openId = getUserOpenId(jsCode);
        UserLogin userLogin=this.userLoginMapper.selectByWechatId(openId);
        if(userLogin!=null){
            return new ApiResponse(ApiMsgEnum.SUCCESS,1,userLogin);
        }else{
            return new ApiResponse(ApiMsgEnum.UserDosentExist,1,openId);
        }
    }

    /**
     * 发送验证短信,校验用户是否存在
     * @param apiRequest
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = false, descript = "CRM发送验证短信", value = "crm-user-sendSMS",apiParams = { @ApiParam(descript = "用户手机号(*)", name = "telphone")})
    @Override
    public ApiResponse<UserInfo> sendSMS(ApiRequest apiRequest) {
        Object telphoneObj = apiRequest.get("telphone");
        if (StringUtils.isEmpty(telphoneObj)) {
            return new ApiResponse<UserInfo>(ApiMsgEnum.MISS_PARAMETER);
        }
        ApiResponse<UserInfo> apiResponse = null;
        try {
            String telphone=telphoneObj.toString();

            if(!isMobile(telphone)){
                return new ApiResponse<UserInfo>(ApiMsgEnum.MISS_PARAMETER);
            }
            UserLogin user = this.userLoginMapper.selectByPhone(telphone);
            if(user == null){
                // 用户不存在
                return new ApiResponse(ApiMsgEnum.UserDosentExist);
            }
            HashMap<String,Object> map=new HashMap<String, Object>();
            map.put("telphone",telphone);
            SendSms oldSendSMS =sendSmsMapper.selectSendSMS(map);
            //10分钟之内的不发
            map.clear();
            map.put("telphone",telphone);
            map.put("updateTime",new Date());
            map.put("longTime",smsRepeatTime);
            SendSms sendSMS =sendSmsMapper.selectSendSMS(map);

            if(sendSMS!=null){
                return new ApiResponse<UserInfo>(ApiMsgEnum.ValidateCodeSendSuccess);//告诉用户正常发送，其实后台不发送新验证码
            }
            String smsCode=createRandomVcode();
            SendSms newSendSMS= SendMessageUntil.sendSmsValidateCode(telphone, smsCode);
            int flag=0;
            if(newSendSMS!=null&&newSendSMS.getTelphone()!=null){
                if(oldSendSMS!=null){
                    oldSendSMS.setUpdateTime(new Date());
                    oldSendSMS.setSmsCode(newSendSMS.getSmsCode());
                    flag=sendSmsMapper.updateByPrimaryKeySelective(oldSendSMS);
                }else{
                    Date date=new Date();
                    newSendSMS.setCreateTime(date);
                    newSendSMS.setUpdateTime(date);
                    flag=sendSmsMapper.insertSelective(newSendSMS);
                }
            }
            if(flag>0){
                apiResponse = new ApiResponse<UserInfo>(ApiMsgEnum.ValidateCodeSendSuccess);//正常发送
            }else{
                apiResponse = new ApiResponse<UserInfo>(ApiMsgEnum.FAIL);//验证码发送失败
            }
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse = new ApiResponse<UserInfo>(ApiMsgEnum.BAD_REQUEST);//出现异常
        }
        return apiResponse;
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = false, descript = "校验短信验证码并更改密码", value = "crm-user-updatePwd",apiParams = { @ApiParam(descript = "用户手机号(*)", name = "userTelphone"),
            @ApiParam(descript = "验证码(*)", name = "validateCode"),@ApiParam(descript = "新密码(*)", name = "newPwd")/*,@ApiParam(descript = "用户user_token", name = "user_token")*/})
    @Override
    public ApiResponse forgetPwd(ApiRequest apiReq) {
        String userTelphone = apiReq.getString("userTelphone");
        String validateCode = apiReq.getString("validateCode");
        String newPwd = apiReq.getString("newPwd");

        if (StringUtils.isEmpty(userTelphone) || StringUtils.isEmpty(validateCode) || StringUtils.isEmpty(newPwd)) {
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        UserSessionDto sessionDto = new UserSessionDto();
        UserLogin user = this.userLoginMapper.selectByPhone(userTelphone);
        if(user == null){
            // 用户不存在
            return new ApiResponse<UserSessionDto>(ApiMsgEnum.FAIL);
        }
        int userType = 0;
        try {
            // 验证验证码并处理
            HashMap<String,Object> map =new HashMap<String, Object>();
            map.put("telphone",userTelphone);
            map.put("smsCode",validateCode);
            map.put("updateTime",new Date());
            map.put("longTime",smsExpiredTime);
            SendSms oldSendSMS =sendSmsMapper.selectSendSMS(map);
            if(oldSendSMS==null){
                return new ApiResponse<UserSessionDto>(ApiMsgEnum.SmsCodeOutTime);
            }else{
                //sp验证成功，将该验证码失效
                map.clear();
                map.put("telphone",userTelphone);
                sendSmsMapper.deleteSendSMSByParam(map);
            }
            //判断是否注册，未注册返回信息，已经注册--查看用户是否冻结，已经冻结则返回冻结信息，未冻结则返回信息账户信息
            map.clear();

            // 验证码校验成功  更新密码
            // 根据用户id查询更新用户密码
            user.setPassword(newPwd);

            Integer integer = userLoginMapper.updateByPrimaryKeySelective(user);
            if(integer == 1){
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
            return new ApiResponse(ApiMsgEnum.FAIL);

        } catch (RuntimeException e) {
            loger.error(e);
		/*	platformTransactionManager.rollback(transactionStatus);*/
            return new ApiResponse(ApiMsgEnum.BAD_REQUEST);
        }
      /*  return null;*/
    }

    /**
     * 更改密码
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "更改密码", value = "user-updatePwd",apiParams = { @ApiParam(descript = "新密码(*)", name = "password")})
    @Override
    public ApiResponse updatePwd(ApiRequest apiReq) {
        UserSessionDto sessionDto = redisService.getUserSession(apiReq.getUserToken());
        if(sessionDto == null){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        String password = (String) apiReq.get("password");
        if(StringUtils.isEmpty(password)){
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }

        // 根据用户id查询更新用户密码
        UserLogin user = new UserLogin();
        user.setUserId(sessionDto.getUserId());
        /*user.setPassword(password);*/
        user.setPassword(Md5Util.encodeString(password));
        Integer integer = userLoginMapper.updateByPrimaryKeySelective(user);
        if(integer == 1){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }


    public String getUserOpenId(String code) throws IOException {
        String openId="";
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+mpwxAppId+"&secret="+mpwxSecret+"&js_code="+code+"&grant_type=authorization_code";
        Map<String,String> map=new HashMap<String,String>();
        String str = HttpClientUtils.httpPost(url, map);
        JSONObject jsonObject = JSON.parseObject(str);
        String session_key = jsonObject.get("session_key").toString();
        String openid = jsonObject.get("openid").toString();
        /**
         * {"session_key":"e+gt7uMAXyA8RjQoTvVGvg==","expires_in":7200,"openid":"oF0sJ0Sr9k7hDsQAow7wnF5qalLA"}
         */
        System.out.println("str:"+str);
        return openid;
    }
    /**
     * 手机号验证
     *
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[0-9]{11}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    /**
     * 随机生成6位随机验证码
     */
    public static String createRandomVcode(){
        //验证码
        StringBuffer vcode = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            vcode.append((int)(Math.random() * 9));
        }
        return vcode.toString();
    }
}
