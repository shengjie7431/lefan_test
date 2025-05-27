package com.lefancrm.apicenter.task;

import com.lefancrm.apicenter.backendapi.impl.BackendWechatApiImpl;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.SurveyReInfoDto;
import com.lefancrm.apicenter.enums.ReInfoEnum;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.util.ConcurrentLockUtils;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.SerialNumberUtil;
import com.lefancrm.base.dto.ApiRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class ReTask {

    @Autowired
    private SurveyReInfoMapper surveyReInfoMapper;
    @Autowired
    private SurveyInvestigatorReInfoMapper surveyInvestigatorReInfoMapper;
    @Autowired
    private SurveyUserClockMapper surveyUserClockMapper;
    @Autowired
    private BackendWechatApiImpl backendWechatApi;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SurveyPayInfoMapper surveyPayInfoMapper;
    @Autowired
    private SurveyChannelCostMapper surveyChannelCostMapper;
    @Autowired
    private SurveyChannelCostNewMapper surveyChannelCostNewMapper;
    @Autowired
    private StaffPersonnelInfoMapper staffPersonnelInfoMapper;
    @Autowired
    private SurveyPayInfoDetailMapper surveyPayInfoDetailMapper;
    @Autowired
    private SurveyPayInfoDetailNewMapper surveyPayInfoDetailNewMapper;

    /**
     * 下发报销单
     */
    public void issuedReInfo() {
        LocalDate localDate = LocalDate.now().plusMonths(-1);
        Date date = new Date();
        String reName = localDate.getYear() + "年" + localDate.getMonthValue() + "月份清单";
        SurveyReInfo surveyReInfo = new SurveyReInfo();
        surveyReInfo.setReName(reName);
        surveyReInfo.setReState(0);
        surveyReInfo.setDownTime(date);
        surveyReInfo.setCreateTime(date);
        surveyReInfo.setReDate(localDate.getYear()+"-"+(localDate.getMonthValue()<10?("0"+localDate.getMonthValue()):localDate.getMonthValue()));

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.put("reName",reName);
        List<SurveyReInfoDto> surveyReInfoDtos = surveyReInfoMapper.selectAllListByParam(apiRequest);
        if (surveyReInfoDtos.size() > 0){
            return;
        }
        surveyReInfoMapper.insert(surveyReInfo);
        //下发报销清单
        surveyInvestigatorReInfoMapper.insertBySelect(surveyReInfo);
        //修改打卡表关联的ReId
        surveyUserClockMapper.updateReIdForBatch(surveyReInfo);

        String preInfoDate = LocalDate.now().plusMonths(-1).toString().substring(0,7);
        //如果有提前下发过预报销清单 需要同步状态
        List<SurveyInvestigatorReInfo> reInfoList = surveyInvestigatorReInfoMapper.selectCurrentReUserIdsByReId(surveyReInfo.getId(),preInfoDate);
        Date currentDate = new Date();
        for (SurveyInvestigatorReInfo surveyInvestigatorReInfo : reInfoList) {

            if (surveyInvestigatorReInfo.getPreState() == 1 || surveyInvestigatorReInfo.getPreState() == 4){
                surveyInvestigatorReInfo.setReState(ReInfoEnum.WAIT_ORG_CHECK.getState());
                surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.WAIT_ORG_CHECK.getStateName());
            }
            if (surveyInvestigatorReInfo.getPreState() == 2) {
                surveyInvestigatorReInfo.setReState(ReInfoEnum.WAIT_FINANCE_CHECK.getState());
                surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.WAIT_FINANCE_CHECK.getStateName());
            }

            if (surveyInvestigatorReInfo.getPreState() == 3){ //生成待付款记录
                surveyInvestigatorReInfo.setReState(ReInfoEnum.THE_PAYING.getState());
                surveyInvestigatorReInfo.setReStateStr(ReInfoEnum.THE_PAYING.getStateName());

                //生成一条公估付款记录
                SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(surveyInvestigatorReInfo.getSurveyUserId());
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(surveyInvestigatorReInfo.getSurveyUserId());
                SurveyPayInfo surveyPayInfo = new SurveyPayInfo();
                surveyPayInfo.setPayNo(SerialNumberUtil.toBuilNo("RE"));
                surveyPayInfo.setOrgId(surveyInvestigator.getOrgId());
                surveyPayInfo.setOrgName(surveyInvestigator.getOrgName());
                surveyPayInfo.setSourceSupportType(null);
                surveyPayInfo.setAppPayMoney(surveyInvestigatorReInfo.getTotalMoney());
                surveyPayInfo.setRemark(surveyInvestigatorReInfo.getReName());
                surveyPayInfo.setAppStartDate(currentDate);
                surveyPayInfo.setAppEndDate(currentDate);
                surveyPayInfo.setAppType(1);
                surveyPayInfo.setCreateUserId(userInfo.getUserId());
                surveyPayInfo.setCreateBy(userInfo.getUserName());
                surveyPayInfo.setPayState(1);
                surveyPayInfo.setCreateTime(currentDate);
                surveyPayInfo.setDeleteFlag(0);
                surveyPayInfo.setUpdateBy(userInfo.getUserName());
                surveyPayInfo.setUpdateTime(currentDate);
                surveyPayInfo.setPayType(2);//报销费
                surveyPayInfo.setPaySurveyUserId(surveyInvestigator.getUserId());
                surveyPayInfo.setPaySurveyUserName(surveyInvestigator.getRealName());
                surveyPayInfo.setPayKeyId(surveyInvestigatorReInfo.getId());

                //同时查询是否是员工管理中的人员
                StaffPersonnelInfo info  = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(surveyInvestigator.getUserId());
                if(info != null){
                    surveyPayInfo.setUserId(info.getUserId());
                    surveyPayInfo.setRealName(info.getRealName());
                    surveyPayInfo.setSocialSecurityCompanyId(info.getSocialSecurityCompanyId());
                    surveyPayInfo.setSocialSecurityCompany(info.getSocialSecurityCompany());
                    surveyPayInfo.setOrganId(info.getOrganId());
                    surveyPayInfo.setOrgan(info.getOrgan());
                    surveyPayInfo.setDepartmentId(info.getDepartmentId());
                    surveyPayInfo.setDepartment(info.getDepartment());
                    surveyPayInfo.setTeam(info.getTeam());
                    surveyPayInfo.setTeamId(info.getTeamId());
                    surveyPayInfo.setJobPost(info.getJobPost());
                    surveyPayInfo.setJobPostId(info.getJobPostId());
                }
                surveyPayInfoMapper.insert(surveyPayInfo);
            }

            surveyInvestigatorReInfoMapper.updateByPrimaryKeySelective(surveyInvestigatorReInfo);
        }

        //通知
        List<SurveyInvestigatorReInfo> surveyInvestigatorReInfos = surveyInvestigatorReInfoMapper.selectByReName(reName);
        for (SurveyInvestigatorReInfo surveyInvestigatorReInfo : surveyInvestigatorReInfos) {
            Map<String, Object> msgMap = new HashMap<String, Object>();
            msgMap.put("title", "费用报销");
            msgMap.put("content", "收到一笔下发的报销清单，请尽快进行报销！");
            msgMap.put("keyWords", "清单名称：" + surveyReInfo.getReName() + "\n" + "案件数量：" + surveyInvestigatorReInfo.getTotalCaseNum() + "件");
            backendWechatApi.send(surveyInvestigatorReInfo.getSurveyUserId(), msgMap);
        }
    }


    /**
     * 每月5号自动执行
     */
    public void channelNew(){
//        calendar.setTime(new Date());
//        //当前时间减去7天 。  当前时间减1天
//        calendar.add(Calendar.DATE,-7);
        String startTime = new SimpleDateFormat("yyyy-MM").format(DateUtils.getUpMonth()) + "-05";//换成每月5号执行，获取上月时间点

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,-1);
        String endTime = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

        String reName = startTime + "至" + endTime + "渠道费用报销";

        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("shlefan.com123");
        if (!jedis.exists("channelName")) {
            jedis.set("channelName",reName);
            jedis.expire("channelName",60 * 10);//token缓存十分钟

            List<SurveyChannelCostNew> costs = surveyChannelCostNewMapper.selectGeneratePayInfo(new HashMap<>());
            for (SurveyChannelCostNew cost : costs) {
                SurveyPayInfo surveyPayInfo = new SurveyPayInfo();
                surveyPayInfo.setPayNo(SerialNumberUtil.toBuilNo("CH"));
                surveyPayInfo.setOrgId(cost.getSurveyOrgId());
                surveyPayInfo.setOrgName(cost.getSurveyOrgName());
                surveyPayInfo.setSourceSupportType(null);
                surveyPayInfo.setAppPayMoney(cost.getChnannelMoney());
                surveyPayInfo.setRemark(reName);
                surveyPayInfo.setAppStartDate(new Date());
                surveyPayInfo.setAppEndDate(new Date());
                surveyPayInfo.setAppType(1);
                surveyPayInfo.setCreateUserId(null);
                surveyPayInfo.setCreateBy(null);
                surveyPayInfo.setPayState(1);
                surveyPayInfo.setCreateTime(new Date());
                surveyPayInfo.setDeleteFlag(0);
                surveyPayInfo.setUpdateBy(null);
                surveyPayInfo.setUpdateTime(null);
                surveyPayInfo.setPayType(3);//渠道费用报销
                surveyPayInfo.setPaySurveyUserId(null);
                surveyPayInfo.setPaySurveyUserName(null);
                surveyPayInfo.setPayKeyId(cost.getId());
                if (!StringUtils.isEmpty(cost.getSurveyUsersStr())) {
                    List<String> tempUsers = new ArrayList<String>();
                    String[] users = cost.getSurveyUsersStr().split(",");
                    for (String user : users) {
                        if (!tempUsers.contains(user)) {
                            tempUsers.add(user);
                        }
                    }
                    String userNames = "";
                    for (String tempUser : tempUsers) {
                        userNames = userNames.concat(tempUser) + ",";
                    }
                    if (userNames.length() > 0){
                        userNames = userNames.substring(0,userNames.length() - 1);
                    }
                    surveyPayInfo.setPaySurveyUserName(userNames);
                }

                surveyPayInfo.setUserId(cost.getSurveyUserId());
                surveyPayInfo.setRealName(cost.getSurveyUserName());
                surveyPayInfo.setSocialSecurityCompanyId(cost.getSocialSecurityCompanyId());
                surveyPayInfo.setSocialSecurityCompany(cost.getSocialSecurityCompany());
                surveyPayInfo.setOrganId(cost.getOrganId());
                surveyPayInfo.setOrgan(cost.getOrgan());
                surveyPayInfo.setDepartmentId(cost.getDepartmentId());
                surveyPayInfo.setDepartment(cost.getDepartment());
                surveyPayInfo.setTeam(cost.getTeam());
                surveyPayInfo.setTeamId(cost.getTeamId());
                surveyPayInfo.setJobPost(cost.getJobPost());
                surveyPayInfo.setJobPostId(cost.getJobPostId());

                surveyPayInfoMapper.insert(surveyPayInfo);
                //修改公估付款申请状态
                String ids = cost.getIds();
                if (!StringUtils.isEmpty(ids)){
                    surveyChannelCostNewMapper.updateProPayByIds(ids);
                }

                //插入付款申请业务关联表
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("payId",surveyPayInfo.getId());
                paramMap.put("oprCode","channel");
                if (!StringUtils.isEmpty(ids)){
                    paramMap.put("ids",ids.split(","));
                    surveyPayInfoDetailNewMapper.insertItems(paramMap);
                }
        }



    }
    }

    /**
     * 每天自动生成删除的渠道费用
     */
    public void deleteChannelNew(){
        List<SurveyChannelCostNew> costs = surveyChannelCostNewMapper.selectDeleteChannel(new HashMap<>());//查询已申请付款且删除的方向，生成负的付款
        for (SurveyChannelCostNew cost : costs) {
            SurveyPayInfo surveyPayInfo = new SurveyPayInfo();
            surveyPayInfo.setPayNo(SerialNumberUtil.toBuilNo("CH"));
            surveyPayInfo.setOrgId(cost.getSurveyOrgId());
            surveyPayInfo.setOrgName(cost.getSurveyOrgName());
            surveyPayInfo.setSourceSupportType(null);
            surveyPayInfo.setAppPayMoney(-cost.getChnannelMoney());
            surveyPayInfo.setRemark("渠道费用报销(删除方向:"+ cost.getSurveyCaseNo() +"-"+ cost.getDirectionName()+")");
            surveyPayInfo.setAppStartDate(new Date());
            surveyPayInfo.setAppEndDate(new Date());
            surveyPayInfo.setAppType(1);
            surveyPayInfo.setCreateUserId(null);
            surveyPayInfo.setCreateBy(null);
            surveyPayInfo.setPayState(1);
            surveyPayInfo.setCreateTime(new Date());
            surveyPayInfo.setDeleteFlag(0);
            surveyPayInfo.setUpdateBy(null);
            surveyPayInfo.setUpdateTime(null);
            surveyPayInfo.setPayType(3);//渠道费用报销
            surveyPayInfo.setPaySurveyUserId(null);
            surveyPayInfo.setPaySurveyUserName(null);
            surveyPayInfo.setPayKeyId(cost.getId());
            surveyPayInfo.setPaySurveyUserName(cost.getSurveyUserName());

            surveyPayInfo.setUserId(cost.getSurveyUserId());
            surveyPayInfo.setRealName(cost.getSurveyUserName());
            surveyPayInfo.setSocialSecurityCompanyId(cost.getSocialSecurityCompanyId());
            surveyPayInfo.setSocialSecurityCompany(cost.getSocialSecurityCompany());
            surveyPayInfo.setOrganId(cost.getOrganId());
            surveyPayInfo.setOrgan(cost.getOrgan());
            surveyPayInfo.setDepartmentId(cost.getDepartmentId());
            surveyPayInfo.setDepartment(cost.getDepartment());
            surveyPayInfo.setTeam(cost.getTeam());
            surveyPayInfo.setTeamId(cost.getTeamId());
            surveyPayInfo.setJobPost(cost.getJobPost());
            surveyPayInfo.setJobPostId(cost.getJobPostId());
            surveyPayInfoMapper.insert(surveyPayInfo);

            //标记为删除已处理
            SurveyChannelCostNew surveyChannelCostNew = surveyChannelCostNewMapper.selectByPrimaryKey(cost.getId());
            surveyChannelCostNew.setDeleteDirectionState(1);
            surveyChannelCostNewMapper.updateByPrimaryKey(surveyChannelCostNew);


            try {
                SurveyChannelCostNew record = new SurveyChannelCostNew();
                BeanUtils.copyProperties(record,surveyChannelCostNew);
                record.setId(null);
                if (record.getChnannelMoney() == null){
                    record.setChnannelMoney(0D);
                }
                if (record.getChnannelMoney() == 0){
                    continue;
                }
                record.setChnannelMoney(-record.getChnannelMoney());
                surveyChannelCostNewMapper.insert(record);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * 每周一自动生成渠道费用报销(已废除)
     */
    public void channel(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        //当前时间减去7天 。  当前时间减1天
        calendar.add(Calendar.DATE,-7);
        String startTime = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,-1);
        String endTime = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

//        Calendar cal = Calendar.getInstance();
//        String reName = cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月渠道费用报销(第"+(cal.get(Calendar.WEEK_OF_MONTH))+"周)";
        String reName = startTime + "至" + endTime + "渠道费用报销";

        List<SurveyChannelCost> costs = surveyChannelCostMapper.selectGeneratePayInfo(new HashMap<>());
        for (SurveyChannelCost cost : costs) {
            SurveyPayInfo surveyPayInfo = new SurveyPayInfo();
            surveyPayInfo.setPayNo(SerialNumberUtil.toBuilNo("CH"));
            surveyPayInfo.setOrgId(cost.getSurveyOrgId());
            surveyPayInfo.setOrgName(cost.getSurveyOrgName());
            surveyPayInfo.setSourceSupportType(null);
            surveyPayInfo.setAppPayMoney(cost.getChnannelMoney());
            surveyPayInfo.setRemark(reName);
            surveyPayInfo.setAppStartDate(new Date());
            surveyPayInfo.setAppEndDate(new Date());
            surveyPayInfo.setAppType(1);
            surveyPayInfo.setCreateUserId(null);
            surveyPayInfo.setCreateBy(null);
            surveyPayInfo.setPayState(1);
            surveyPayInfo.setCreateTime(new Date());
            surveyPayInfo.setDeleteFlag(0);
            surveyPayInfo.setUpdateBy(null);
            surveyPayInfo.setUpdateTime(null);
            surveyPayInfo.setPayType(3);//渠道费用报销
            surveyPayInfo.setPaySurveyUserId(null);
            surveyPayInfo.setPaySurveyUserName(null);
            surveyPayInfo.setPayKeyId(cost.getId());
            if (!StringUtils.isEmpty(cost.getSurveyUsersStr())) {
                List<String> tempUsers = new ArrayList<String>();
                String[] users = cost.getSurveyUsersStr().split(",");
                for (String user : users) {
                    if (!tempUsers.contains(user)) {
                        tempUsers.add(user);
                    }
                }
                String userNames = "";
                for (String tempUser : tempUsers) {
                    userNames = userNames.concat(tempUser) + ",";
                }
                if (userNames.length() > 0){
                    userNames = userNames.substring(0,userNames.length() - 1);
                }
                surveyPayInfo.setPaySurveyUserName(userNames);
            }

            surveyPayInfo.setUserId(cost.getSurveyUserId());
            surveyPayInfo.setRealName(cost.getSurveyUserName());
            surveyPayInfo.setSocialSecurityCompanyId(cost.getSocialSecurityCompanyId());
            surveyPayInfo.setSocialSecurityCompany(cost.getSocialSecurityCompany());
            surveyPayInfo.setOrganId(cost.getOrganId());
            surveyPayInfo.setOrgan(cost.getOrgan());
            surveyPayInfo.setDepartmentId(cost.getDepartmentId());
            surveyPayInfo.setDepartment(cost.getDepartment());
            surveyPayInfo.setTeam(cost.getTeam());
            surveyPayInfo.setTeamId(cost.getTeamId());
            surveyPayInfo.setJobPost(cost.getJobPost());
            surveyPayInfo.setJobPostId(cost.getJobPostId());

            surveyPayInfoMapper.insert(surveyPayInfo);
            //修改公估付款申请状态
            String ids = cost.getIds();
            if (!StringUtils.isEmpty(ids)){
                surveyChannelCostMapper.updateProPayByIds(ids);
            }

            //插入付款申请业务关联表
            Map<String,Object> paramMap =  new HashMap<String,Object>();
            paramMap.put("payId",surveyPayInfo.getId());
            if (!StringUtils.isEmpty(ids)){
                paramMap.put("ids",ids.split(","));
                surveyPayInfoDetailMapper.insertItems(paramMap);
            }


        }
    }
}
