package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.SurveyInvestigatorDto;
import com.lefancrm.backend.dto.SurveyKnowledgeBaseDto;
import com.lefancrm.backend.dto.SurveyKnowledgeCommentDto;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2018/12/20.
 * 论坛帖子
 */
@Controller
@RequestMapping(value = "/surveyKnowledgeBase")
public class BackendSurveyKnowledgeBaseController extends BackendBaseController{



}
