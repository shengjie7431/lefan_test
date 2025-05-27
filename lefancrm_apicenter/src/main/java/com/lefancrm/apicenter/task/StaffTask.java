package com.lefancrm.apicenter.task;

import com.lefancrm.apicenter.backendapi.BackendWechatApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.StaffPaySlip;
import com.lefancrm.apicenter.model.StaffPaySlipManager;
import com.lefancrm.apicenter.model.StaffPerformance;
import com.lefancrm.apicenter.model.StaffPerformanceManager;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

@Service
public class StaffTask implements Serializable {

    @Autowired
    private StaffPaySlipMapper staffPaySlipMapper;
    @Autowired
    private StaffPaySlipManagerMapper staffPaySlipManagerMapper;
    @Autowired
    private StaffPerformanceMapper staffPerformanceMapper;
    @Autowired
    private StaffPerformanceManagerMapper staffPerformanceManagerMapper;
    @Autowired
    private BackendWechatApi backendWechatApi;
    @Autowired
    private StaffPersonnelInfoMapper staffPersonnelInfoMapper;
    @Value("${staff.users.not.examine}")
    private String examinePersons; //跳过该阶段的审核人员

    /**
     * 人事专员提交
     * @return
     */
    public ApiResponse staffPaySlip(){

        //工资条（人事专员提交审核3天，自动更新到“待分管总处理”）
        updatePaySlipForSuperiorManager();
        //绩效（人事专员提交审核3天，自动更新到“待分管总处理”）
        updatePerformanceForSuperiorManager();
        //绩效（人事专员提交审核1天，自动更新到“待机构经理处理”）
//        updatePerformanceForOrganManager();

        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    private void updatePaySlipForSuperiorManager() {
        Map map = new HashMap<>();
        map.put("slipState",1);
        List<StaffPaySlip> list = staffPaySlipMapper.allList(map);
        for (StaffPaySlip staffPaySlip : list) {

            Date hrTime = staffPaySlip.getHrTime();
            long days = (new Date().getTime() - hrTime.getTime()) / 1000 / 3600 / 24;
            if(days < 3){
                continue;
            }

            map = new HashMap<>();
            map.put("staffPaySlipId",staffPaySlip.getId());
            map.put("managerType",1);
            map.put("state",0);
            List<StaffPaySlipManager> manager = staffPaySlipManagerMapper.list(map);
            for (StaffPaySlipManager staffPaySlipManager : manager) {
                staffPaySlipManager.setState(1);
                staffPaySlipManagerMapper.updateByPrimaryKey(staffPaySlipManager);
            }

            //更新工资条主表的状态
            staffPaySlip.setSlipState(2);//2:待分管总审核
            staffPaySlip.setSlipStateName("待分管总审核");
            staffPaySlipMapper.updateByPrimaryKey(staffPaySlip);

            //微信通知：发于分管总
            map = new HashMap<>();
            map.put("staffPaySlipId",staffPaySlip.getId());
            map.put("managerType",2);
            List<StaffPaySlipManager> list1 = staffPaySlipManagerMapper.list(map);

            Map<String, Object> msgMap = new HashMap<String, Object>();
            for (StaffPaySlipManager staffPaySlipManager : list1) {
                //部分人员不予审核，直接跳过
                if (!StringUtils.isEmpty(examinePersons)) {
                    String [] personIds = examinePersons.split(",");
                    for (String personId : personIds) {
                        if (!StringUtils.isEmpty(personId)){
                            if (staffPaySlipManager.getOrganManagerUserId().intValue() == Integer.parseInt(personId)){
                                staffPaySlipManager.setState(1);
                                staffPaySlipManagerMapper.updateByPrimaryKey(staffPaySlipManager);
                                continue;
                            }
                        }
                    }
                }

                msgMap = new HashMap<String, Object>();
                msgMap.put("title", "工资条审核");
                msgMap.put("content", "你有一笔工资条待审核，请尽快处理！");
                msgMap.put("keyWords", "清单名称：" + staffPaySlip.getWorkTime() + "工资条");
                backendWechatApi.send(staffPaySlipManager.getOrganManagerUserId(), msgMap);
            }
        }
    }


    private void updatePerformanceForSuperiorManager() {
        Map map = new HashMap<>();
        map.put("performanceState",1);
        List<StaffPerformance> list = staffPerformanceMapper.allList(map);
        for (StaffPerformance staffPerformance : list) {

            Date hrTime = staffPerformance.getSuperiorManagerFirstTime();
            long days = (new Date().getTime() - hrTime.getTime()) / 1000 / 3600 / 24;
            if(days < 3){
                continue;
            }

            map = new HashMap<>();
            map.put("staffPerformanceId",staffPerformance.getId());
            map.put("managerType",1);
            map.put("state",0);
            List<StaffPerformanceManager> manager = staffPerformanceManagerMapper.list(map);
            for (StaffPerformanceManager staffPerformanceManager : manager) {
                staffPerformanceManager.setState(1);
                staffPerformanceManagerMapper.updateByPrimaryKey(staffPerformanceManager);
            }

            //更新工资条主表的状态
            staffPerformance.setPerformanceState(2);//2:待分管总审核
            staffPerformance.setPerformanceStateName("待分管总审核");
            staffPerformanceMapper.updateByPrimaryKey(staffPerformance);


            //微信通知：发于分管总
            map = new HashMap<>();
            map.put("staffPerformanceId",staffPerformance.getId());
            map.put("managerType",2);
            manager = staffPerformanceManagerMapper.list(map);

            Map<String, Object> msgMap = new HashMap<String, Object>();
            for (StaffPerformanceManager staffPerformanceManager : manager) {

                //部分人员不予审核，直接跳过
                if (!StringUtils.isEmpty(examinePersons)) {
                    String [] personIds = examinePersons.split(",");
                    for (String personId : personIds) {
                        if (!StringUtils.isEmpty(personId)){
                            if (staffPerformanceManager.getOrganManagerUserId().intValue() == Integer.parseInt(personId)){
                                staffPerformanceManager.setState(1);
                                staffPerformanceManagerMapper.updateByPrimaryKey(staffPerformanceManager);
                                continue;
                            }
                        }
                    }
                }

                msgMap = new HashMap<String, Object>();
                msgMap.put("title", "绩效审核");
                msgMap.put("content", "你有一笔绩效待审核，请尽快处理！");
                msgMap.put("keyWords", "清单名称：" + staffPerformance.getWorkTime() + "绩效");
                backendWechatApi.send(staffPerformanceManager.getOrganManagerUserId(), msgMap);
            }

        }
    }


    private void updatePerformanceForOrganManager() {
        Map map = new HashMap<>();
        map.put("performanceState",6);
        List<StaffPerformance> list = staffPerformanceMapper.allList(map);
        for (StaffPerformance staffPerformance : list) {

            Date hrTime = staffPerformance.getSuperiorManagerFirstTime();
            long days = (new Date().getTime() - hrTime.getTime()) / 1000 / 3600 / 24;
            if(days < 1){
                continue;
            }

            //更新工资条主表的状态
            staffPerformance.setPerformanceState(1);//
            staffPerformance.setPerformanceStateName("待机构经理处理");
            staffPerformanceMapper.updateByPrimaryKey(staffPerformance);

            //微信通知：发于机构经理
            map = new HashMap<>();
            map.put("staffPerformanceId",staffPerformance.getId());
            map.put("managerType",1);
            List<StaffPerformanceManager> managerList = staffPerformanceManagerMapper.list(map);

            Map<String, Object> msgMap = new HashMap<String, Object>();
            for (StaffPerformanceManager staffPerformanceManager : managerList) {

                //部分人员不予审核，直接跳过
                if (!StringUtils.isEmpty(examinePersons)) {
                    String [] personIds = examinePersons.split(",");
                    for (String personId : personIds) {
                        if (!StringUtils.isEmpty(personId)){
                            if (staffPerformanceManager.getOrganManagerUserId().intValue() == Integer.parseInt(personId)){
                                staffPerformanceManager.setState(1);
                                staffPerformanceManagerMapper.updateByPrimaryKey(staffPerformanceManager);
                                continue;
                            }
                        }
                    }
                }

                map = new HashMap<>();
                map.put("organManagerStaffId",staffPerformanceManager.getOrganManagerStaffId());
                int myCount = staffPersonnelInfoMapper.myPersonelInfosSize(map);

                msgMap = new HashMap<String, Object>();
                msgMap.put("title", "绩效审核");
                msgMap.put("content", "你有一笔绩效待审核，请于2天内处理！");
                msgMap.put("keyWords", "清单名称：" + staffPerformance.getWorkTime() + "绩效" + "\n" + "员工人数：" + myCount + "人\n" + "查询密码：" + staffPerformance.getQueryPassword());
                backendWechatApi.send(staffPerformanceManager.getOrganManagerUserId(), msgMap);
            }
        }
    }

}
