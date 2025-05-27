package com.lefancrm.apicenter.appapi.impl;

import com.lefancrm.apicenter.appapi.SignInfoApi;
import com.lefancrm.apicenter.dao.SignInfoMapper;
import com.lefancrm.apicenter.dto.NoSignUserDto;
import com.lefancrm.apicenter.model.SignInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiParam;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jani on 2017/12/15.
 */
@Service
@ApiService(descript = "CC签到相关功能")
public class SignInfoApiImpl extends BaseServiceImpl implements SignInfoApi {

    @Autowired
    private SignInfoMapper signInfoMapper;

    @SuppressWarnings("rawtypes")
    @ApiMethod(needLogin = true, descript = "新增签到记录", value = "sign-in",
            apiParams = { @ApiParam(name = "user_token",descript = "当前用户token(*)"),
                    @ApiParam(descript = "签到类型", name = "signType"),@ApiParam(descript = "用户ID", name = "userId"),@ApiParam(descript = "用户名", name = "userName")
                    ,@ApiParam(descript = "签到备注", name = "signDesc") ,@ApiParam(descript = "拜访客户数量", name = "customerNum")
                    ,@ApiParam(descript = "地标", name = "landmark"),@ApiParam(descript = "签到精确地址", name = "address"),@ApiParam(descript = "纬度", name = "latitude"),@ApiParam(descript = "经度", name = "longitude")
                    ,@ApiParam(descript = "机构ID", name = "orgId"),@ApiParam(descript = "机构名称", name = "orgName")})

    @Override
    public ApiResponse signIn(ApiRequest apiReq) throws Exception {

        if(StringUtils.isEmpty(apiReq.get("signType"))||StringUtils.isEmpty(apiReq.get("userId"))||
                StringUtils.isEmpty(apiReq.get("userName"))||StringUtils.isEmpty(apiReq.get("landmark"))||StringUtils.isEmpty(apiReq.get("address"))
                ||StringUtils.isEmpty(apiReq.get("latitude"))||StringUtils.isEmpty(apiReq.get("longitude"))||StringUtils.isEmpty(apiReq.get("orgId"))
                ||StringUtils.isEmpty(apiReq.get("orgName"))){
                return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }

        Integer signType = apiReq.getInt("signType");
        Long userId = apiReq.getLong("userId");
        String userName = apiReq.getString("userName");
        String signDesc = apiReq.getString("signDesc");
        Integer customerNum = apiReq.getInt("customerNum");
        String landmark = apiReq.getString("landmark");
        String address = apiReq.getString("address");
        Double latitude = apiReq.getDouble("latitude");
        Double longitude = apiReq.getDouble("longitude");
        Long orgId = apiReq.getLong("orgId");
        String orgName = apiReq.getString("orgName");

        SignInfo signInfo=new SignInfo();
        signInfo.setSignType(signType);
        signInfo.setUserId(userId);
        signInfo.setUserName(userName);
        signInfo.setSignDesc(signDesc);
        signInfo.setCustomerNum(customerNum);
        signInfo.setLandmark(landmark);
        signInfo.setAddress(address);
        signInfo.setLatitude(latitude);
        signInfo.setLongitude(longitude);
        signInfo.setOrgId(orgId);
        signInfo.setOrgName(orgName);
        signInfo.setSignTime(new Date());
        int ret = signInfoMapper.insertSelective(signInfo);
        if(ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
    /**
     * 查询机构下面的最新签到信息列表
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "查询机构下面的最新签到信息列表", value = "org-sign-info-list")
    @Override
    public ApiResponse orgSignInfoList(ApiRequest apiReq) {
        Long orgId = apiReq.getLong("orgId");
        String signTime = apiReq.getString("signTime");
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("orgId",orgId);
        paramMap.put("signTime",signTime);

        //根据传进来的订单编号来进行支付
        List<SignInfo> signInfos = this.signInfoMapper.selectSignInfoByOrgId(paramMap);
        return new ApiResponse(ApiMsgEnum.SUCCESS,signInfos==null?0:signInfos.size(),signInfos);
    }

    /**
     * 根据CC业务员ID查询业务员签到信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "根据CC业务员ID查询业务员签到信息", value = "user-sign-info-list", apiParams = { @ApiParam(name = "userId",descript = "当前用户userId(*)"),
            @ApiParam(descript = "签到时间", name = "signTime")})
    @Override
    public ApiResponse userSignInfoList(ApiRequest apiReq) {
        Long userId = apiReq.getLong("userId");
        String signTime = apiReq.getString("signTime");
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("userId",userId);
        paramMap.put("signTime",signTime);
        //根据传进来的订单编号来进行支付
        List<SignInfo> signInfos = this.signInfoMapper.selectSignInfoByUserId(paramMap);
        return new ApiResponse(ApiMsgEnum.SUCCESS,signInfos==null?0:signInfos.size(),signInfos);
    }

    /**
     * CC业务员签到详情
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "CC业务员签到详情", value = "sign-info-details",apiParams = { @ApiParam(name = "id",descript = "签到信息ID(*)")})
    @Override
    public ApiResponse signInfoDetails(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");

        //根据传进来的订单编号来进行支付
        SignInfo signInfo = this.signInfoMapper.selectByPrimaryKey(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS,signInfo==null?0:1,signInfo);
    }

    /**
     * CC业务员未签到人员
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "CC业务员未签到人员", value = "not-sign-list",apiParams = { @ApiParam(name = "orgId",descript = "机构ID(*)"),
            @ApiParam(descript = "签到时间", name = "signTime")})
    @Override
    public ApiResponse notSignList(ApiRequest apiReq) {
        Long orgId = apiReq.getLong("orgId");
        String signTime = apiReq.getString("signTime");
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("orgId",orgId);
        paramMap.put("signTime",signTime);
        //根据传进来的订单编号来进行支付
        List<NoSignUserDto> noSignUserDtos = this.signInfoMapper.selectNoSignByOrgId(paramMap);
        return new ApiResponse(ApiMsgEnum.SUCCESS,noSignUserDtos==null?0:noSignUserDtos.size(),noSignUserDtos);
    }
}
