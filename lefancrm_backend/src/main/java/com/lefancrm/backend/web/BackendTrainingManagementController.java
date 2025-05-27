package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author lsg
 * @date 2018/12/21
 *
 */
@Controller
@RequestMapping(value = "/training")
public class BackendTrainingManagementController extends BackendBaseController {

    /**
     *list
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        String menuCode=req.getParameter("menuCode");
        Map appendMap=new HashMap();
        //新人管理
        if("rookieManagement".equals(menuCode)){
            Map params = new HashMap();
            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType",1); //不分页
            appendMap.put("surveyCode","franchisee");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
            franchisees = franchisees.parallelStream().filter(e -> e.getBusType() != 2).collect(Collectors.toList());
            model.put("franchisees",franchisees);
            params.put("consignorsJson", JsonUtil.objectToJson(franchisees));
            appendMap = new HashMap<String, Object>();
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INVESTIGATOR_ALLLIST, appendMap, null);
            List<SurveyInvestigatorDto> surveyInvestigatorDtoList=(List<SurveyInvestigatorDto>)apiFinalResponse.getResults();
            surveyInvestigatorDtoList = surveyInvestigatorDtoList.parallelStream().filter(e -> Optional.ofNullable(e.getBusType()).isPresent()&&e.getBusType() != 2).collect(Collectors.toList());
            List<SurveyInvestigatorDto> standardList=new ArrayList<>();
            List<SurveyInvestigatorDto> notStandardList=new ArrayList<>();
            for (SurveyInvestigatorDto surveyInvestigatorDto:surveyInvestigatorDtoList) {
                if(surveyInvestigatorDto.getIsNewPeople()==0 || surveyInvestigatorDto.getIsNewPeople()==null){
                    notStandardList.add(surveyInvestigatorDto);
                }else if(surveyInvestigatorDto.getIsNewPeople()==1){
                    standardList.add(surveyInvestigatorDto);
                }
            }
            params.put("standardListJson", JsonUtil.objectToJson(standardList));
            params.put("notStandardListJson", JsonUtil.objectToJson(notStandardList));
            model.put("params",params);
            return new ModelAndView("/trainingManagement/rookieManagement", model);
        }else if("teachingReward".equals(menuCode)){//带教奖励清单
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_CUR_USER_ROLES, null, req);
            List<BusUserRoleDto> userRoles = (List<BusUserRoleDto>)apiFinalResponse.getResults();
            Boolean finance = isRoleUser(userRoles,105L);
            if(finance){
                model.put("roleCode","1");
            }
            Boolean financeTwo = isRoleUser(userRoles,106L);
            if(financeTwo){
                model.put("roleCode","2");
            }
            if(finance && financeTwo){
                model.put("roleCode","3");
            }
            return new ModelAndView("/trainingManagement/teachingReward", model);
        }
        return null;
    }

    /**
     * 获取详情列表信息
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "getDetail")
    public String getDetail(HttpServletRequest req, HttpServletResponse rsp){
        Map appendMap=new HashMap();
        String menuCode=req.getParameter("menuCode");
        //新人管理
        if("rookieManagement".equals(menuCode)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_INVESTIGATOR_SELECTROOKIELIST, appendMap, req,rsp);
        }else if("teachingRewardDetail".equals(menuCode)) {//带教奖励清单详情
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_TEACH_REWARD_SELECTTASKDETAILS, appendMap, req,rsp);
        }else if("teachingReward".equals(menuCode)) {//带教奖励清单
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_TEACH_REWARD_SELECTBYMAP, appendMap, req,rsp);
        }

        return null;
    }

    /**
     * 修改信息
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "getEdit")
    public String getEdit(HttpServletRequest req, HttpServletResponse rsp){
        String menuCode=req.getParameter("menuCode");
        Map appendMap=new HashMap();
        //新人管理
        if("rookieManagement".equals(menuCode)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_INVESTIGATOR_UPDATEROOKIE, appendMap, req,rsp);
        }else if("teachingReward".equals(menuCode)){//带教奖励
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_TEACH_REWARD_UPDATEDETAILEDLIST, appendMap, req,rsp);
        }
        return null;
    }

    /**
     * 操作-弹窗页面
     */
    @RequestMapping(value = "/popup")
    public String popup(HttpServletRequest req, HttpServletResponse rsp) {
        Map appendMap=new HashMap();
        String menuCode=req.getParameter("menuCode");
        String btnCode=req.getParameter("btnCode");
        if("teachingReward".equals(menuCode)){//带教奖励
            return this.callApiAndOutput( BackendApiMethodEnum.BACKEND_SURVEY_TEACH_REWARD_ADD_SELECTBYMAP, appendMap, req,rsp);
        }
        return null;
    }
}
