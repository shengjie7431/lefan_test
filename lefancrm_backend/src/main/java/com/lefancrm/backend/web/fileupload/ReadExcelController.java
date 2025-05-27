package com.lefancrm.backend.web.fileupload;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.UserInfo;
import com.lefancrm.backend.web.BackendBaseController;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.web.WebHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jun on 2018/1/24.
 */
@Controller
public class ReadExcelController extends BackendBaseController {






    @RequestMapping(value="/readExcel")
    public String readExcel(@RequestParam(value = "fileupload")MultipartFile file,HttpServletRequest request,HttpSession session,HttpServletResponse res) throws IOException {
        JSONObject json = new JSONObject();
        UserInfo ui = (UserInfo) session.getAttribute("adminDto");
        try {
        String base64 = Base64Utils.encodeToString(file.getBytes());
        Map paramMap = new HashMap();
        paramMap.put("ccId", ui.getUserId());
        paramMap.put("ccName",ui.getUserName());
        paramMap.put("orgId",ui.getOrgId());
        paramMap.put("orgName",ui.getOrgName());
        paramMap.put("fileData", base64);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<HashMap>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_READEXCEL, paramMap, request);
        if (apiFinalResponse.getCode().equals(ApiMsgEnum.SUCCESS.getCode()) ){
            json.put("code",200);
        }
        } catch (IOException e) {
            e.printStackTrace();
            json.put("errorMsg",e.getMessage());
        }
        return WebHelper.outputJson(json.toJSONString(), res);
    }


}
