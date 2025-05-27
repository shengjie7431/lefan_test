package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSaleGoalApi;
import com.lefancrm.apicenter.dao.SaleGoalMapper;
import com.lefancrm.apicenter.dto.SaleGoalDto;
import com.lefancrm.apicenter.model.SaleGoal;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jun on 2018/1/4.
 */
@Service
@ApiService(descript = "业绩目标")
public class BackendSaleGoalApiImpl extends BaseServiceImpl implements BackendSaleGoalApi {



    @Autowired
    private SaleGoalMapper saleGoalMapper;


    @Override
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "查询机构部门业绩目标列表", value = "backend-sale-goal-list")
    public ApiResponse list(ApiRequest apiReq) {
        List<HashMap<String,Object>> list = saleGoalMapper.list(apiReq);
        List<SaleGoalDto> resultList = new ArrayList<SaleGoalDto>();
        if(list!=null&&list.size()>0){
            for(int i = 0;i<list.size();i++){
                HashMap<String,Object> map = list.get(i);
                SaleGoalDto sgd = new SaleGoalDto();
                sgd.setId((Long) map.get("objectId"));
                sgd.setName(map.get("objectName").toString());
                sgd.setTotal((Double) map.get("total"));
                sgd.setYear((Integer) map.get("year"));
                if(map.get("saleGoal")!=null){
                    String [] saleGoal = map.get("saleGoal").toString().split(",");
                    String [] month = map.get("month").toString().split(",");
                    for(int j =0;j<saleGoal.length;j++){
                        switch (month[j]){
                            case "1":
                                sgd.setJanuary(Double.parseDouble(saleGoal[j]));
                                break;
                            case "2":
                                sgd.setFebruary(Double.parseDouble(saleGoal[j]));
                                break;
                            case "3":
                                sgd.setMarch(Double.parseDouble(saleGoal[j]));
                                break;
                            case "4":
                                sgd.setApril(Double.parseDouble(saleGoal[j]));
                                break;
                            case "5":
                                sgd.setMay(Double.parseDouble(saleGoal[j]));
                                break;
                            case "6":
                                sgd.setJune(Double.parseDouble(saleGoal[j]));
                                break;
                            case "7":
                                sgd.setJuly(Double.parseDouble(saleGoal[j]));
                                break;
                            case "8":
                                sgd.setAugust(Double.parseDouble(saleGoal[j]));
                                break;
                            case "9":
                                sgd.setSeptember(Double.parseDouble(saleGoal[j]));
                                break;
                            case "10":
                                sgd.setOctober(Double.parseDouble(saleGoal[j]));
                                break;
                            case "11":
                                sgd.setNovember(Double.parseDouble(saleGoal[j]));
                                break;
                            case "12":
                                sgd.setDecember(Double.parseDouble(saleGoal[j]));
                                break;
                        }
                    }
                }
                resultList.add(sgd);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS,resultList==null?0:resultList.size(),resultList);
        }else{
            return new ApiResponse(ApiMsgEnum.SUCCESS,0,null);
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "查询年度业绩目标列表", value = "backend-saleGoalList")
    public ApiResponse saleGoalList(ApiRequest apiReq) {
        List<SaleGoal> list = saleGoalMapper.saleGoalList();
        return new ApiResponse(ApiMsgEnum.SUCCESS,list==null?0:list.size(),list);
    }



    @Override
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "修改用户业绩目标", value = "backend-updateSaleGoal")
    public ApiResponse updateSaleGoal(ApiRequest apiReq) {
        int userResult = 0;
        Integer addOrReduce = apiReq.getInt("addOrReduce");
        String year = apiReq.getString("year");
        String month = apiReq.getString("month");
        Double saleGoal = apiReq.getDouble("saleGoal");
        Long objectId = apiReq.getLong("objectId");
        String objectName = apiReq.getString("objectName");
        Long parentId = apiReq.getLong("parentId");
        apiReq.put("objectType","2");
        SaleGoal sg = saleGoalMapper.selectIsSaleGoalByUser(apiReq);
        if(sg!=null){
            //开始修改操作
            apiReq.put("id",sg.getId());
            userResult = saleGoalMapper.updateSaleGoalById(apiReq);
        }else{
            //等于null，开始添加操作
            SaleGoal addSaleGoal = new SaleGoal();
            addSaleGoal.setYear(year);
            addSaleGoal.setMonth(month);
            addSaleGoal.setSaleGoal(saleGoal);
            addSaleGoal.setObjectType(2);
            addSaleGoal.setObjectId(objectId);
            addSaleGoal.setObjectName(objectName);
            addSaleGoal.setParentId(parentId);
            addSaleGoal.setCreateTime(new Date());
            userResult = saleGoalMapper.insertSelective(addSaleGoal);
        }
        if(userResult>0){
            Long orgObjectId = 0l;
            Long orgParentId = 0l;
            String orgIdList = saleGoalMapper.getOrgRootList(parentId);
            String [] charOrgIdList = orgIdList.split(",");
            for(int i =1;i<charOrgIdList.length;i++){
                if("1".equals(charOrgIdList[i])){
                    System.out.println("已经到总部了。全部传1吧");
                    orgParentId = 1l;
                    orgObjectId = 1l;
                }else{
                    orgParentId = Long.parseLong(charOrgIdList[i+1]);
                    orgObjectId = Long.parseLong(charOrgIdList[i]);
                    System.out.println("当前的object_id为："+charOrgIdList[i]+";上级的parentId为："+charOrgIdList[i+1]);
                }
                HashMap<String,Object> map = new HashMap<>();
                map.put("year",year);
                map.put("month",month);
                map.put("objectId",orgObjectId);
                map.put("objectType",1);
                map.put("parentId",orgParentId);
                SaleGoal orgSg = saleGoalMapper.selectIsSaleGoalByUser(map);
                if(orgSg!=null){
                    map.put("addOrReduce",addOrReduce);
                    map.put("id",orgSg.getId());
                    map.put("saleGoal",saleGoal);
                    saleGoalMapper.updateSaleGoalById(map);
                }else{
                    String orgName = saleGoalMapper.selectOrgNameByOrgId(orgObjectId);
                    //等于null，开始添加操作
                    SaleGoal addSaleGoal = new SaleGoal();
                    addSaleGoal.setYear(year);
                    addSaleGoal.setMonth(month);
                    addSaleGoal.setSaleGoal(saleGoal);
                    addSaleGoal.setObjectType(1);
                    addSaleGoal.setObjectId(orgObjectId);
                    addSaleGoal.setObjectName(orgName);
                    addSaleGoal.setParentId(orgParentId);
                    addSaleGoal.setCreateTime(new Date());
                    saleGoalMapper.insertSelective(addSaleGoal);
                }
                if(orgParentId==1&&orgObjectId==1){
                    return new ApiResponse(ApiMsgEnum.SUCCESS);
                }
            }
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return null;
    }
}
