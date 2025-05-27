package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCaseInfoApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 案件中心
 * Created by wangwei on 2018-05-22.
 */
@Service
@ApiService(descript = "后台案件中心API")
public class BackendCaseInfoApiImpl extends BaseServiceImpl implements BackendCaseInfoApi {

    @Autowired
    private LoanApplicationMapper loanApplicationMapper;
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    @Autowired
    private AgentApplyMapper agentApplyMapper;

    SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private PromotedInfoMapper promotedInfoMapper;

    @Autowired
    private CaseCenterExtendMapper caseCenterExtendMapper;
    /**
     * 添加案件中心数据
     * @param apiReq
     * @return
     */
    @Override
    public CaseCenterInfo addCaseCenterInfo(ApiRequest apiReq) {
        //根据type案件类型(1：贷款申请，2：代理申请）
        Integer type = apiReq.getInt("type");
        if (type == null){
            return null;
        }
        //1.准备封装案件中心数据。
        CaseCenterInfo cci = new CaseCenterInfo();
        CaseCenterExtend caseCenterExtend = new CaseCenterExtend();
        HashMap<String,Object> orgMap = new HashMap<>();
        orgMap.put("type",type);
        orgMap.put("id",apiReq.getLong("caseId"));
        HashMap<String,Object> map2 = caseCenterInfoMapper.selectOrgIdAndOrgNameByCaseId(orgMap);
        if(map2!=null){
            //2018年9月20日14:10:13  若匹配的是"乐凡赔偿总部"则默认,将组织更改为:上海机构
            if ("1".equals(map2.get("orgId").toString())){
                cci.setOrgId(26L);
                cci.setOrgName("上海机构");
            }else{
                cci.setOrgId(Long.parseLong(map2.get("orgId").toString())); //添加机构id
                cci.setOrgName(map2.get("orgName").toString());              //添加机构名称
            }
        }

        Long userId = null;
        if(type == 1){
            //查询贷款申请表
            LoanApplication loanApplication = loanApplicationMapper.selectByPrimaryKey(apiReq.getLong("caseId"));
            userId =loanApplication.getUserId();
            caseCenterExtend.setUserPromotedPhone(loanApplication.getUserPromotedPhone());
            //根据是否是测试人员，设置案件是否是测试案件
            cci.setIsTestcase(loanApplication.getIsTestcase());
        }else if (type == 2){
            //代理申请表
            AgentApply agentApply = agentApplyMapper.selectByPrimaryKey(apiReq.getLong("caseId"));
            userId = agentApply.getUserId();
            caseCenterExtend.setUserPromotedPhone(agentApply.getUserPromotedPhone());
            //根据是否是测试人员，设置案件是否是测试案件
            cci.setIsTestcase(agentApply.getIsTestcase());
        }
        //如果是测试案件，机构默认为“总部”
        if(cci.getIsTestcase() == 1){
            cci.setOrgId(33L);
            cci.setOrgName("总部");
        }

        //获取当前用户对应的业务员
        UserInfo userInfo = returnBusUser(userId);
        if (userInfo == null){
            cci.setSalesmanId(null);
            cci.setSalesmanName(null);
        }else{
            cci.setSalesmanId(userInfo.getUserId());
            cci.setSalesmanName(userInfo.getUserName());
            cci.setOrgUserId(userInfo.getUserId());
            cci.setOrgUserName(userInfo.getUserName());
            cci.setOperatorId(userInfo.getUserId());
            cci.setOperatorName(userInfo.getUserName());
        }
        cci.setType(type);
        cci.setCaseId(apiReq.getLong("caseId"));
        cci.setCaseTitle(apiReq.getString("caseTitle"));
        cci.setCaseState(1);//1.案件待接收
        cci.setCaseStateStr("待接收");
        cci.setCreateTime(new Date());
        cci.setCreateBy(apiReq.getLong("userId"));
        cci.setCaseNo(apiReq.getString("caseNo"));
        cci.setCaseName(apiReq.getString("caseName"));
        cci.setCaseTel(apiReq.getString("caseTel"));
        cci.setGradationState(1);
        cci.setCarNo(apiReq.getString("carNumber"));
        cci.setUpdateTime(new Date());
        cci.setListState(999);
        cci.setListStateName("待签约");

        cci.setDeleteFlag(0);
        try {
            cci.setDangerTime(sdf.parse(apiReq.get("dangerTime").toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //3.添加案件中心的数据
        int result = caseCenterInfoMapper.insertSelective(cci);

        //落地数据至“案件中心”附属表
        caseCenterExtend.setId(cci.getId());
        caseCenterExtend.setAssessFlowState(0l);
        caseCenterExtend.setClaimFlowState(0l);
        caseCenterExtend.setCustomerFlowState(0l);
        caseCenterExtend.setLegalFlowState(0l);
        caseCenterExtend.setOperatorFlowState(0l);
        caseCenterExtendMapper.insertSelective(caseCenterExtend);
        return cci;
    }

    /**
     * 递归调用   获取用户对应的业务员
     * @param userId  用户ID
     * @return  用户对应的业务员  没有返回NULL
     */
    private UserInfo returnBusUser(Long userId){
        List<BusUserRole> busUserRoles = busUserRoleMapper.orgUserRoleList(userId);
        //验证是否有业务员角色  true 表示用户是业务员，直接返回业务员对象
        if (isBusUser(busUserRoles)){
            return userInfoMapper.selectByPrimaryKey(userId);
        }else {
            //获取上级UserId  验证  上级是否是业务员
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("customerId",userId);
            PromotedInfo promotedInfo = promotedInfoMapper.selectPromotedInfoByParam(paramMap);
            //表示没有上级
            if (promotedInfo == null || promotedInfo.getPromoterId() == null){
                return null;
            }
            //表示有上级  就递归迪调用
            return returnBusUser(promotedInfo.getPromoterId());
        }
    }

    /**
     * 验证角色列表是否有业务员角色
     * @param busUserRoles 角色列表
     * @return
     */
    private Boolean isBusUser(List<BusUserRole> busUserRoles){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId() == 2){
                return true;
            }
        }
        return false;
    }

}
