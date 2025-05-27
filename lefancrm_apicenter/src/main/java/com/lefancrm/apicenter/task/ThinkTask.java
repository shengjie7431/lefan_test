package com.lefancrm.apicenter.task;

import com.lefancrm.apicenter.dao.ThinkDataDetailMapper;
import com.lefancrm.apicenter.dao.ThinkDataMapper;
import com.lefancrm.apicenter.dao.ThinkDataOrgProductMapper;
import com.lefancrm.apicenter.model.ThinkData;
import com.lefancrm.apicenter.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ThinkTask {
    @Autowired
    private ThinkDataMapper thinkDataMapper;
    @Autowired
    private ThinkDataDetailMapper thinkDataDetailMapper;
    @Autowired
    private ThinkDataOrgProductMapper thinkDataOrgProductMapper;

    public void think(){
        ThinkData thinkData = new ThinkData();
        thinkData.setThinkTime(DateUtils.getUpMonth());
        thinkData.setDeleteFlag(0);
        thinkDataMapper.insert(thinkData);
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        paramMap.put("thinkDataId",thinkData.getId());
        thinkDataDetailMapper.insertStaffOrg(paramMap);//保存主营机构、业务管理机构、后援机构
        //保存公司
        thinkDataDetailMapper.insertBillcorporation(paramMap);
        thinkDataOrgProductMapper.insertOrgProduct(paramMap);//保存机构-产品
    }
}
