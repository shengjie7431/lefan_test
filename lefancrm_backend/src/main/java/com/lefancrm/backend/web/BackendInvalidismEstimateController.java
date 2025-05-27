package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.util.Util;
import com.lefancrm.backend.dto.InvalidismEstimate;
import com.lefancrm.backend.dto.InvalidismEstimateFile;
import com.lefancrm.backend.dto.InvalidismEstimateReportDto;
import com.lefancrm.backend.util.Util;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代理申请
 * 
 * @author Daniel
 */
@Controller
@RequestMapping("/invalidism")
public class BackendInvalidismEstimateController extends BackendBaseController {

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest req, HttpServletResponse rsp) throws  Exception{
        String userName = Util.decode(req.getParameter("userName"));
        String state = req.getParameter("state");
        String accidentType = req.getParameter("accidentType");
        String caseNo = req.getParameter("caseNo");
        String page = req.getParameter("page");
        if(StringUtils.isEmpty(page)){
            page = "1";
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userName",userName);
		String json = this.callApi(BackendApiMethodEnum.BACKEND_INVALIDISM_LIST, paramMap, req);
		Type type = new TypeToken<ApiFinalResponse<List<InvalidismEstimate>>>() {
		}.getType();
		ApiFinalResponse<List<InvalidismEstimate>> apiRsp = JsonUtil.jsonToObject(json, type);
		req.setAttribute("apiRsp", apiRsp);
        req.setAttribute("userName", userName);
        req.setAttribute("state", state);
        req.setAttribute("accidentType", accidentType);
        req.setAttribute("caseNo", caseNo);
        req.setAttribute("page", page);
		return "/invalidismEstimate/invalidismEstimateList";
	}

    @RequestMapping(value = "/report")
    public String report(HttpServletRequest req, HttpServletResponse rsp, String invalidismGrade){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("invalidismGrade",invalidismGrade);
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_INVALIDISM_REPORT, paramMap, req, rsp);
    }

    @RequestMapping(value = "/queryFile")
    public String queryFile(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String json = this.callApi(BackendApiMethodEnum.BACKEND_INVALIDISM_FILE, appendMap, req);
        Type type = new TypeToken<ApiFinalResponse<List<InvalidismEstimateFile>>>() {
        }.getType();
        ApiFinalResponse<List<InvalidismEstimateFile>> apiRsp = JsonUtil.jsonToObject(json, type);
        req.setAttribute("apiRsp", apiRsp);
        String id = req.getParameter("id");
        req.setAttribute("id", id);
        return "/invalidismEstimate/invalidismEstimateFile";
    }

    @RequestMapping(value = "/del")
    public String del(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_INVALIDISM_DEL, null, req, rsp);
    }

    @RequestMapping(value = "/toReport")
    public String toReport(HttpServletRequest req, HttpServletResponse rsp) {
        String id = req.getParameter("id");
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("caseNo",req.getParameter("caseNo"));
        appendMap.put("caseId",req.getParameter("caseId"));
        String json = this.callApi(BackendApiMethodEnum.BACKEND_INVALIDISM_TO_REPORT, appendMap, req);
        Type type = new TypeToken<ApiFinalResponse<InvalidismEstimateReportDto>>() {
        }.getType();
        ApiFinalResponse<InvalidismEstimateReportDto> apiRsp = JsonUtil.jsonToObject(json, type);
        req.setAttribute("report",apiRsp.getResults());
        if(id != null){
            req.setAttribute("id",id);
        }else{
            if(apiRsp.getResults()!=null){
                req.setAttribute("id",apiRsp.getResults().getEstimateId());
            }
        }
        req.setAttribute("op",req.getParameter("op"));
        return "/invalidismEstimate/invalidismEstimateReport";
    }

    /*
    * 放大图片展示
    * */
    @RequestMapping(value = "/invalidismShow")
    public String invalidismShow(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String json = this.callApi(BackendApiMethodEnum.BACKEND_INVALIDISM_FILE, appendMap, req);
        Type type = new TypeToken<ApiFinalResponse<List<InvalidismEstimateFile>>>() {
        }.getType();
        ApiFinalResponse<List<InvalidismEstimateFile>> apiRsp = JsonUtil.jsonToObject(json, type);
        req.setAttribute("invalidismEstimateFile", apiRsp.getResults());

        String index = req.getParameter("index");
        req.setAttribute("id",index);
        return "/invalidismEstimate/invalidismShow";
    }
}
