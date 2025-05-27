package com.lefancrm.backend.web.fina;

import com.lefancrm.backend.web.BackendBaseController;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/fina/pub/")
public class FinaPubController extends BackendBaseController {
    @RequestMapping(value = "ajaxData")
    public String ajaxData(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_AJAX_DATA_FINA_PUB, null, req, rsp);
    }

    /**
     * 导出
     * @param req
     * @param rsp
     */
    @RequestMapping(value = "export")
    public void export(HttpServletRequest req, HttpServletResponse rsp){

    }
}
