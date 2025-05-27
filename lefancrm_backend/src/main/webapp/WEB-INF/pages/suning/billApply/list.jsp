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
                        <label class="title">开票时间:</label>
                        <input name="startDate" type="text" value="${startDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        <span>--</span>
                        <input name="endDate" type="text" value="${endDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <br>
                    <div class="form-group">
                        <label class="title">开票产品:</label>
                        <select name="billingEnum" class="form-control">
                            <option value="">全部</option>
                            <c:forEach items="${bullingEnums}" var="item">
                                <option <c:if test="${billingEnum == item.id}">selected="selected" </c:if> value="${item.id}" >${item.enumName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">开票项目:</label>
                        <select name="billingItem" class="form-control">
                            <option value="">全部</option>
                            <c:forEach items="${bullingItems}" var="item">
                                <option <c:if test="${billingItem == item.id}">selected="selected" </c:if> value="${item.id}" >${item.enumName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">开票公司:</label>
                        <select name="businessType" class="form-control">
                            <option value="">全部</option>
                            <c:forEach items="${corporations}" var="item">
                                <option <c:if test="${businessType == item.id}">selected="selected" </c:if> value="${item.id}" >${item.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">发票状态:</label>
                        <select name="billingState"  class="form-control">
                            <option value="" <c:if test="${billingState == ''}">selected="selected" </c:if> >全部</option>
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
                    <div class="form-group">
                        <label class="title">开票类型:</label>
                        <select name="billingType"  class="form-control">
                            <option value=""  <c:if test="${billingType == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${billingType == '1'}">selected="selected" </c:if> >专票</option>
                            <option value="2" <c:if test="${billingType == '2'}">selected="selected" </c:if> >普票</option>
                            <option value="3" <c:if test="${billingType == '3'}">selected="selected" </c:if> >电子普票</option>
                        </select>
                    </div>
                    <br>
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
                    <div class="form-group">
                        <label class="title">发票单号:</label>
                        <input name="billingCode" type="text"  value="${billingCode}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="title">开票对象:</label>
                        <input name="billingCompany" type="text"  value="${billingCompany}" class="form-control">
                    </div>
                    <c:if test="${op != 'view'}">
                        <div class="btn-group">
                            <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                            <button class="btn btn-default"><a href="${ctx}/billingApply/billingApplyListExport?billingState=${billingState}&caseNo=${caseNo}&caseTitle=${caseTitle}&orgId=${orgId}&billingItem=${billingItem}&billingEnum=${billingEnum}&billingType=${billingType}&businessType=${businessType}&startDate=${startDate}&endDate=${endDate}&insuredName=${insuredName}&carNo=${carNo}&woundedName=${woundedName}&menuType=${menuType}&billingCode=${billingCode}&billingCompany=${billingCompany}&export=1">导出</a></button>
                        </div>
                    </c:if>
                </form>
            </div>

        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th width="150">案件编号</th>
                <th width="300">案件标题</th>
                <th width="150">开票类目</th>
                <th width="150">开票项目</th>
                <th width="150">开票类型</th>
                <th width="150">所属公司</th>
                <th width="150">服务费金额</th>
                <th width="150">通道费</th>
                <th width="150">扣费金额</th>
                <th width="150">开票金额</th>
                <c:if test="${menuType != 2}">
                    <th width="200">开票时间</th>
                    <th width="200">发票状态</th>
                </c:if>
                <th width="200">收入归属机构</th>
                <th width="200">车牌号</th>
                <th width="200">伤者姓名</th>
                <th width="200">被保人姓名</th>
                <c:if test="${op != 'view'}">
                    <th width="300">操作</th>
                </c:if>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.caseNo}</td>
                    <td>${item.caseTitle}</td>
                    <td>
                        <c:forEach items="${bullingEnums}" var="dto">
                            <c:if test="${dto.enumCode == item.billingEnum}">
                                ${dto.enumName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${bullingItems}" var="dto">
                            <c:if test="${dto.enumCode == item.billingItem}">
                                ${dto.enumName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:if test="${item.billingType == 1}">专票</c:if>
                        <c:if test="${item.billingType == 2}">普票</c:if>
                        <c:if test="${item.billingType == 3}">电子普票</c:if>
                    </td>
                    <td>
                        <c:forEach items="${corporations}" var="dto">
                            <c:if test="${dto.id == item.businessType}">
                                ${dto.name}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${item.servcieMoney == null ? 0.0 : item.servcieMoney}</td>
                    <td>${item.channelMoney == null ? 0.0 : item.channelMoney}</td>
                    <td>${item.deductionMoney == null ? 0.0 : item.deductionMoney}</td>
                    <td>${item.billingMoney == null ? 0.0 : item.billingMoney}</td>
                    <c:if test="${menuType != 2}">
                        <td><fmt:formatDate value="${item.billingTime}" pattern="yyyy-MM-dd"/></td>
                        <td >
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
                    </c:if>
                    <td>${item.staffOrgName}</td>
                    <td>${item.carNo}</td>
                    <td>${item.woundedName}</td>
                    <td>${item.insuredName}</td>
                    <%--<c:if test="${op != 'view'}">--%>
                        <%--<td>--%>
                            <%--<c:if test="${item.billingState == 0}">--%>
                                <%--<a href="javascript:okApply(0,'${item.id}','${item.caseNo}');">确认开票</a>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${item.billingState == 1}">--%>
                                <%--<!--<a href="javascript:okApply(1,'${item.id}','${item.caseNo}');">补票</a>-->--%>
                            <%--</c:if>--%>
                        <%--</td>--%>
                    <%--</c:if>--%>
                    <td>
                        <c:if test="${item.billingEnum == 7}">
                            <a href="javascript:findBillApplyView('${item.id}','${item.caseId}','${item.caseNo}','${menuType}');">查看</a>
                        </c:if>
                        <c:if test="${item.billingEnum != 7}">
                            <a href="javascript:billingApplyView('${item.id}',2,'${menuType}');">查看</a>
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
            <jsp:param name="requestUrl" value="${ctx}/billingApply/billingApplyList?billingState=${billingState}&caseNo=${caseNo}&caseTitle=${caseTitle}&billingItem=${billingItem}&billingEnum=${billingEnum}&billingType=${billingType}&menuType=${menuType}&billingType=${billingType}&businessType=${businessType}&startDate=${startDate}&endDate=${endDate}&insuredName=${insuredName}&carNo=${carNo}&woundedName=${woundedName}&billingCode=${billingCode}&billingCompany=${billingCompany}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script>
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

   var billingApplyView = function(id,mType,menuType) {
       openDialog({
           frame:true,
           title:"查看开票详情",
           height:800,
           width:1000,
           url:"${ctx}/billingApply/billingApplyView?id="+id+"&mType="+mType+"&menuType="+menuType,
           load:true
       });
   }
</script>
</body>
</html>
