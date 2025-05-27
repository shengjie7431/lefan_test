package com.lefancrm.apicenter.backendapi.impl;


import com.lefancrm.apicenter.backendapi.BackendCaseStepsApi;
import com.lefancrm.apicenter.dao.CaseStepsMapper;
import com.lefancrm.apicenter.dto.CaseStepsDTO;
import com.lefancrm.apicenter.model.CaseCenterInfo;
import com.lefancrm.apicenter.model.CaseSteps;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lixianfeng on 2019/5/20.
 */
@Service
@ApiService(descript = "案件步骤")
public class BackendCaseStepsApiImpl extends BaseServiceImpl implements BackendCaseStepsApi{
    @Autowired
    private CaseStepsMapper caseStepsMapper;

    @Override
    public List<CaseStepsDTO> list(CaseCenterInfo caseCenterInfo) {
        List<CaseStepsDTO> list = new LinkedList<>();
        //蓝色部分
        List<CaseSteps> caseSteps = caseStepsMapper.selectByCaseCenterIdToList(caseCenterInfo.getId());
        for (CaseSteps casaStep : caseSteps) {
            list.add(new CaseStepsDTO(casaStep.getStepName(),casaStep.getEndTime(),true));
        }

        //灰色部分
        if (caseSteps.size() > 0){
            CaseSteps caseStep = caseSteps.get(caseSteps.size() - 1);
            list.addAll(steps(caseStep.getCurState(),caseStep.getCurStateStep()));
        }else {
            list.addAll(steps(1,0));
        }
        return list;
    }

    private List<CaseStepsDTO> steps(int curState,Integer curStateStep){
        List<CaseStepsDTO> list = new LinkedList<>();
        if (curState == 1){
            String [] claims = {"案件接收","CC交流","见面伤者","材料收集","鉴定情况","索赔预案","预案审核","索赔结果"};
            for (int i = curStateStep; i < claims.length; i++) {
                list.add(new CaseStepsDTO(claims[i],null,false));
            }
        }else if (curState == 2){
            String [] legals = {"案件接收","CC交流","见面伤者","材料收集","鉴定情况","诉讼预案","预案审核","立案","开庭","诉讼结果"};
            for (int i = curStateStep; i < legals.length; i++) {
                list.add(new CaseStepsDTO(legals[i],null,false));
            }
        }
        return list;
    }

    /**
     * 插入
     * @param caseCenterInfo
     * @param stepCode   当前步骤编码
     * @param userInfo   当前操作用户
     * @param curState      当前所属状态（1索赔  2诉讼）
     * @param curStateStep  当前所属状态步骤 (1,2,3.....)
     * @return
     */
    @Override
    public Boolean insert(CaseCenterInfo caseCenterInfo,String stepCode,UserInfo userInfo,Integer curState,Integer curStateStep) {
        try {
            CaseSteps maxNewSteps = caseStepsMapper.selectByCaseCenterIdToUpdate(caseCenterInfo.getId());
            if (maxNewSteps != null){
                if (maxNewSteps.getStepCode().equals(stepCode)){
                    return true;
                }
            }
            CaseSteps caseSteps = CaseSteps.class.newInstance();
            caseSteps.setCaseCenterId(caseCenterInfo.getId());
            caseSteps.setStepCode(stepCode);
            caseSteps.setStepName(getStepName(caseSteps.getStepCode()));
            if ("claim_to_legal".equals(stepCode)){//转办诉讼
                caseSteps.setStartTime(new Date());
                caseSteps.setEndTime(new Date());
                caseSteps.setUpdateBy(userInfo.getUserName());
            }else if ("legal_to_claim".equals(stepCode)){//转办索赔
                caseSteps.setStartTime(new Date());
                caseSteps.setEndTime(new Date());
                caseSteps.setUpdateBy(userInfo.getUserName());
            }else{
                caseSteps.setStartTime(new Date());
                caseSteps.setEndTime(null);
                caseSteps.setUpdateBy(null);
            }
            caseSteps.setCreateBy(userInfo.getUserName());
            caseSteps.setDeleteFlag(0);
            caseSteps.setCurState(curState);
            caseSteps.setCurStateStep(curStateStep);
            caseStepsMapper.insert(caseSteps);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更新
     * @param caseCenterInfo
     * @param userInfo
     * @param isCreate  是否创建
     * @param stepCode
     * @param curState
     * @param curStateStep
     * @return
     */
    @Override
    public Boolean update(CaseCenterInfo caseCenterInfo,UserInfo userInfo,Boolean isCreate,String stepCode,Integer curState,Integer curStateStep) {
        try {
            CaseSteps caseSteps = caseStepsMapper.selectByCaseCenterIdToUpdate(caseCenterInfo.getId());
            if (caseSteps != null){
                caseSteps.setEndTime(new Date());
                caseSteps.setUpdateBy(userInfo.getUserName());
                caseStepsMapper.updateByPrimaryKey(caseSteps);
            }else{
                //初始化老案件数据
                if (caseCenterInfo.getGradationState() == 3){

                }else if (caseCenterInfo.getGradationState() == 6){

                }
                caseSteps = new CaseSteps();
                caseSteps.setCurState(caseCenterInfo.getGradationState() == 6 ? 2 : 1);
            }
            if (isCreate){
                if (caseSteps.getCurState() != curState){//说明是  索赔转诉讼  或者 诉讼转索赔
                    if (caseSteps.getCurState() == 1){
                        insert(caseCenterInfo,"claim_to_legal",userInfo,curState,curStateStep);
                        stepCode = "legal_jianding";
                    }else  if (caseSteps.getCurState() == 2){
                        insert(caseCenterInfo,"legal_to_claim",userInfo,curState,curStateStep);
                        stepCode = "claim_jianding";
                    }
                    if (caseSteps.getCurStateStep() > 4){//如果是材料收集之后的步骤转办则都从第五步鉴定情况开始
                        curStateStep = 5;
                    }
                }
                insert(caseCenterInfo,stepCode,userInfo,curState,curStateStep);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取步骤名称
     * @param stepCode
     * @return
     */
    private String getStepName(String stepCode){
        switch (stepCode){
            case "claim_jieshou" : return "案件接收";
            case "claim_ccjiaoliu" : return "CC交流";
            case "claim_jianmian" : return "见面伤者";
            case "claim_cailiao" : return "材料收集";
            case "claim_jianding" : return "鉴定情况";
            case "claim_yuan" : return "索赔预案";
            case "claim_shenhe" : return "预案审核";
            case "claim_jieguo" : return "预案结果";
            case "claim_to_legal" : return "转办诉讼";


            case "legal_jieshou" : return "案件接收";
            case "legal_ccjiaoliu" : return "CC交流";
            case "legal_jianmian" : return "见面伤者";
            case "legal_cailiao" : return "材料收集";
            case "legal_jianding" : return "鉴定情况";
            case "legal_yuan" : return "诉讼预案";
            case "legal_shenhe" : return "预案审核";
            case "legal_lian" : return "立案";
            case "legal_kaiting" : return "开庭";
            case "legal_jieguo" : return "诉讼结果";
            case "legal_to_claim" : return "转办索赔";
            default: return "";
        }
    }
}
