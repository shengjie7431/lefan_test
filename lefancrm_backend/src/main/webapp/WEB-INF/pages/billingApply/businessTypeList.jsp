<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>开票列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <style>
        .form-group{
            margin-bottom: 10px!important;
            padding-right: 16px;
        }
        .time{
            width: 130px!important;
        }
        .title{
            width: 85px;
            font-weight: normal;
        }
        .form-control{
            width: 160px!important;
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>开票列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/billingApply/billingApplyList" method="post">
                    <input type="hidden" name="menuType" value="${menuType}">
                    <input type="hidden" name="business" value="${business}">
                    <div class="form-group">
                        <label class="title">案件编号:</label>
                        <input name="caseNo" type="text"  value="${caseNo}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="title">案件标题:</label>
                        <input name="caseTitle" type="text"  value="${caseTitle}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="title">机构名称:</label>
                        <select name="orgId" class="form-control">
                            <option value="">全部</option>
                            <c:forEach items="${orgInfoDtos}" var="itemOrg">
                                <option <c:if test="${orgId == itemOrg.id}">selected="selected" </c:if> value="${itemOrg.id}" >${itemOrg.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">所属公司:</label>
                        <select name="businessType"  class="form-control">
                            <option value=""  <c:if test="${businessType == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${businessType == '1'}">selected="selected" </c:if> >金融公司</option>
                            <option value="2" <c:if test="${businessType == '2'}">selected="selected" </c:if> >公估公司</option>
                            <option value="3" <c:if test="${businessType == '3'}">selected="selected" </c:if> >正言公司</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">开票时间:</label>
                        <input name="startDate" type="text" value="${startDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        <span>--</span>
                        <input name="endDate" type="text" value="${endDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <br>
                    <div class="form-group">
                        <label class="title">开票类目:</label>
                        <select name="billingEnum"  class="form-control">
                            <option value=""  <c:if test="${billingEnum == ''}">selected="selected" </c:if> >全部</option>
                            <c:if test="${business ==2}">
                                <option value="1" <c:if test="${billingEnum==1}">selected="selected" </c:if>>全程通</option>
                                <option value="2" <c:if test="${billingEnum==2}">selected="selected" </c:if>>风险调查</option>
                                <option value="3" <c:if test="${billingEnum==3}">selected="selected" </c:if>>交警队工作室</option>
                                <option value="8" <c:if test="${billingEnum==8}">selected="selected" </c:if>>法院工作室</option>
                                <option value="4" <c:if test="${billingEnum==4}">selected="selected" </c:if>>诉调对接</option>
                                <option value="5" <c:if test="${billingEnum==5}">selected="selected" </c:if>>培训</option>
                                <option value="6" <c:if test="${billingEnum==6}">selected="selected" </c:if>>其他</option>
                                <option value="9" <c:if test="${billingEnum==9}">selected="selected" </c:if>>财产险</option>
                                <option value="10" <c:if test="${billingEnum==10}">selected="selected" </c:if>>公估评估</option>
                                <option value="11" <c:if test="${billingEnum==11}">selected="selected" </c:if>>司法评估</option>
                            </c:if>
                            <c:if test="${business ==1}">
                                <option value="7" <c:if test="${billingEnum==7}">selected="selected" </c:if>>个人业务</option>
                            </c:if>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">开票项目:</label>
                        <select name="billingItem"  class="form-control">
                            <option value=""  <c:if test="${billingItem == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${billingItem==1}">selected="selected" </c:if>>调查费</option>
                            <option value="2" <c:if test="${billingItem==2}">selected="selected" </c:if>>公估费</option>
                            <option value="3" <c:if test="${billingItem==3}">selected="selected" </c:if>>调解费</option>
                            <option value="4" <c:if test="${billingItem==4}">selected="selected" </c:if>>咨询费</option>
                            <option value="5" <c:if test="${billingItem==5}">selected="selected" </c:if>>服务费</option>
                            <option value="6" <c:if test="${billingItem==6}">selected="selected" </c:if>>奖励费</option>
                            <option value="7" <c:if test="${billingItem==7}">selected="selected" </c:if>>代理费</option>
                            <option value="8" <c:if test="${billingItem==8}">selected="selected" </c:if>>检验费</option>
                            <option value="9" <c:if test="${billingItem==9}">selected="selected" </c:if>>服务费(调解)</option>
                            <option value="10" <c:if test="${billingItem==10}">selected="selected"</c:if>>调查费(减损奖励)</option>
                            <option value="11" <c:if test="${billingItem==11}">selected="selected"</c:if>>司法评估费</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">开票类型:</label>
                        <select name="billingType"  class="form-control">
                            <option value=""  <c:if test="${billingType == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${billingType == '1'}">selected="selected" </c:if> >专票</option>
                            <option value="2" <c:if test="${billingType == '2'}">selected="selected" </c:if> >普票</option>
                            <option value="3" <c:if test="${billingType == '3'}">selected="selected" </c:if> >电子普票</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">开票状态:</label>
                        <select name="billingState"  class="form-control">
                            <option value=""  <c:if test="${billingState == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${billingState == '1'}">selected="selected" </c:if> >未申请</option>
                            <option value="2" <c:if test="${billingState == '2'}">selected="selected" </c:if> >开票中</option>
                            <option value="3" <c:if test="${billingState == '3'}">selected="selected" </c:if> >已开票</option>
                            <option value="9" <c:if test="${billingState == '9'}">selected="selected" </c:if> >退票中</option>
                            <option value="4" <c:if test="${billingState == '4'}">selected="selected" </c:if> >已退票</option>
                            <option value="5" <c:if test="${billingState == '5'}">selected="selected" </c:if> >退票审核中</option>
                            <option value="6" <c:if test="${billingState == '6'}">selected="selected" </c:if> >重开审核中</option>
                            <option value="7" <c:if test="${billingState == '7'}">selected="selected" </c:if> >退票审核通过</option>
                            <option value="8" <c:if test="${billingState == '8'}">selected="selected" </c:if> >重开审核通过</option>
                        </select>
                    </div>
                    <c:if test="${business ==2 }">
                        <div class="form-group">
                            <label class="title">到账时间:</label>
                            <input name="confirmStartDate" type="text" value="${confirmStartDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                            <span>--</span>
                            <input name="confirmEndDate" type="text" value="${confirmEndDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        </div>
                        <br>
                    </c:if>
                    <div class="form-group">
                        <label class="title">发票单号:</label>
                        <input name="billingCode" type="text"  value="${billingCode}" class="form-control">
                    </div>
                    <c:if test="${business ==2 }">
                        <div class="form-group">
                            <label class="title">车牌号码:</label>
                            <input name="carNo" type="text"  value="${carNo}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="title">伤者姓名:</label>
                            <input name="woundedName" type="text"  value="${woundedName}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="title">被保人姓名:</label>
                            <input name="insuredName" type="text"  value="${insuredName}" class="form-control">
                        </div>
                    </c:if>

                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                        <button class="btn btn-default"><a href="${ctx}/billingApply/billingApplyListExport?billingState=${billingState}&caseNo=${caseNo}&caseTitle=${caseTitle}&orgId=${orgId}&billingItem=${billingItem}&billingEnum=${billingEnum}&billingType=${billingType}&businessType=${businessType}&startDate=${startDate}&endDate=${endDate}&insuredName=${insuredName}&carNo=${carNo}&woundedName=${woundedName}&confirmStartDate=${confirmStartDate}&confirmEndDate=${confirmEndDate}&menuType=${menuType}&business=${business}&billingCode=${billingCode}&export=1">导出</a></button>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th width="80">案件编号</th>
                <th width="300">案件标题</th>
                <th width="200">开票类目</th>
                <th width="250">开票项目</th>
                <c:if test="${business == 1 }">
                    <th width="150">服务费金额</th>
                    <th width="150">通道费</th>
                    <th width="150">扣费金额</th>
                </c:if>
                <th width="150">开票金额</th>
                <th width="150">开票类型</th>
                <th width="150">所属公司</th>
                <th width="150">开票状态</th>
                <th width="200">收入归属机构</th>
                <c:if test="${business ==2 }">
                    <th width="150">车牌号</th>
                    <th width="150">伤者姓名</th>
                    <th width="150">被保人姓名</th>
                </c:if>
                <th width="200">开票时间</th>
                <c:if test="${business ==2 }">
                    <th width="150">到账金额</th>
                    <th width="150">到账状态</th>
                    <th width="200">到账时间</th>
                </c:if>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.caseNo}</td>
                    <td>${item.caseTitle}</td>
                    <td>
                        <c:if test="${item.billingEnum == 1}">全程通</c:if>
                        <c:if test="${item.billingEnum == 2}">风险调查</c:if>
                        <c:if test="${item.billingEnum == 3}">交警队工作室</c:if>
                        <c:if test="${item.billingEnum == 8}">法院工作室</c:if>
                        <c:if test="${item.billingEnum == 4}">诉调对接</c:if>
                        <c:if test="${item.billingEnum == 5}">培训</c:if>
                        <c:if test="${item.billingEnum == 6}">其他</c:if>
                        <c:if test="${item.billingEnum == 7}">个人业务</c:if>
                        <c:if test="${item.billingEnum == 9}">财产险</c:if>
                        <c:if test="${item.billingEnum == 10}">公估评估</c:if>
                    </td>
                    <td>
                        <c:if test="${item.billingItem == 1}">调查费</c:if>
                        <c:if test="${item.billingItem == 2}">公估费</c:if>
                        <c:if test="${item.billingItem == 3}">调解费</c:if>
                        <c:if test="${item.billingItem == 4}">咨询费</c:if>
                        <c:if test="${item.billingItem == 5}">服务费</c:if>
                        <c:if test="${item.billingItem == 6}">奖励费</c:if>
                        <c:if test="${item.billingItem == 7}">代理费</c:if>
                        <c:if test="${item.billingItem == 8}">检验费</c:if>
                        <c:if test="${item.billingItem == 9}">服务费(调解)</c:if>
                        <c:if test="${item.billingItem == 10}">调查费(减损奖励)</c:if>
                    </td>
                    <c:if test="${business ==1}">
                        <td>${item.servcieMoney == null ? 0.0 : item.servcieMoney}</td>
                        <td>${item.channelMoney == null ? 0.0 : item.channelMoney}</td>
                        <td>${item.deductionMoney == null ? 0.0 : item.deductionMoney}</td>
                    </c:if>
                    <td>${item.billingMoney}</td>
                    <td>
                        <c:if test="${item.billingType == 1}">专票</c:if>
                        <c:if test="${item.billingType == 2}">普票</c:if>
                        <c:if test="${item.billingType == 3}">电子普票</c:if>
                    </td>
                    <td>
                        <c:if test="${item.businessType == 1}">金融公司</c:if>
                        <c:if test="${item.businessType == 2}">公估公司</c:if>
                        <c:if test="${item.businessType == 3}">正言公司</c:if>
                    </td>
                    <td>
                        <c:if test="${item.billingState == 1}">未申请</c:if>
                        <c:if test="${item.billingState == 2}">开票中</c:if>
                        <c:if test="${item.billingState == 3}">已开票</c:if>
                        <c:if test="${item.billingState == 9}">退票中</c:if>
                        <c:if test="${item.billingState == 4}">已退票</c:if>
                        <c:if test="${item.billingState == 5}">退票审核中</c:if>
                        <c:if test="${item.billingState == 6}">重开审核中</c:if>
                        <c:if test="${item.billingState == 7}">退票审核通过</c:if>
                        <c:if test="${item.billingState == 8}">重开审核通过</c:if>
                    </td>
                    <td>${item.orgName}</td>
                    <c:if test="${business ==2}">
                        <td>${item.carNo}</td>
                        <td>${item.woundedName}</td>
                        <td>${item.insuredName}</td>
                    </c:if>
                    <td><fmt:formatDate value="${item.billingTime}" pattern="yyyy-MM-dd"/></td>
                    <c:if test="${business ==2}">
                        <td>${item.confirmAccountMoney}</td>
                        <td>
                            <c:if test="${item.confirmAccountState == 1 || item.confirmAccountState == null}">未到账</c:if>
                            <c:if test="${item.confirmAccountState == 2}">已到账</c:if>
                        </td>
                        <td><fmt:formatDate value="${item.confirmAccountTime}" pattern="yyyy-MM-dd"/></td>
                    </c:if>
                    <td>
                        <c:if test="${item.billingEnum == 7}">
                            <a href="javascript:findBillApplyView('${item.id}','${item.caseId}','${item.caseNo}','${menuType}');">查看</a>
                        </c:if>
                        <c:if test="${item.billingEnum != 7}">
                            <a href="javascript:findBillingApplyView('${item.id}',2,'${menuType}');">查看</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/billingApply/billingApplyList?billingState=${billingState}&caseNo=${caseNo}&caseTitle=${caseTitle}&orgId=${orgId}&billingItem=${billingItem}&billingEnum=${billingEnum}&billingType=${billingType}&businessType=${businessType}&startDate=${startDate}&endDate=${endDate}&insuredName=${insuredName}&carNo=${carNo}&woundedName=${woundedName}&confirmStartDate=${confirmStartDate}&confirmEndDate=${confirmEndDate}&menuType=${menuType}&business=${business}&billingCode=${billingCode}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script>
   var findBillingApplyView = function(id,mType,menuType) {
       openDialog({
           frame:true,
           title:"查看开票详情",
           height:800,
           width:1000,
           url:"${ctx}/billingApply/billingApplyView?id="+id+"&mType="+mType+"&menuType="+menuType,
           load:true

       });
   }

   var addBillingApply = function(){
       openDialog({
           frame:true,
           title:"添加开票",
           height:700,
           width:800,
           url:"${ctx}/billingApply/billingApplyAdd"
       });
   }

   /**
    * 提交申请
    */
   function billingApplyDelete(id){
       ajaxSubmit("${ctx}/billingApply/billingApplyDelete",{"id":id},reload,"删除成功！","确认删除？","删除失败！");
   }

   var findBillApplyView = function(id,caseId,caseNo,menuType) {
       openDialog({
           frame:true,
           title:"查看开票详情",
           height:800,
           width:1000,
           url:"${ctx}/suning/billApply/findBillApplyView?caseId="+caseId+"&caseNo="+caseNo+"&id="+id+"&menuType="+menuType,
           load:true

       });
   }

</script>
</body>
</html>
