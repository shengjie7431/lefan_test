<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>苏宁代扣申请列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>苏宁代扣申请列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">

            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/suning/withholdApply/list?type=${type}&pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <%--<div class="form-group">--%>
                         <%--代扣状态: <select name="withholdState"  class="form-control">--%>
                                    <%--<option value="" <c:if test="${withholdState == ''}">selected="selected"</c:if>>全部</option>--%>
                                    <%--<option value="0" <c:if test="${withholdState == '0'}">selected="selected"</c:if>>代扣申请</option>--%>
                                    <%--<option value="1" <c:if test="${withholdState == '1'}">selected="selected"</c:if>>代扣中</option>--%>
                                    <%--<option value="2" <c:if test="${withholdState == '2'}">selected="selected"</c:if>>代扣成功</option>--%>
                                    <%--<option value="3" <c:if test="${withholdState == '3'}">selected="selected"</c:if>>代扣失败</option>--%>
                                <%--</select>--%>
                    <%--</div>--%>
                    <input type="hidden" name="numberType" value="${numberType}">
                    <div class="form-group">
                        案件编号: <input name="caseNo" type="text"  value="${caseNo}" class="form-control">
                    </div>
                    <div class="form-group">
                        案件标题: <input name="caseTitle" type="text"  value="${caseTitle}" class="form-control">
                    </div>
                    <div class="form-group">
                        订单号: <input name="orderCode" type="text"  value="${orderCode}" class="form-control">
                    </div>
                    <div class="form-group">
                        是否到账:
                        <select name="accountState"  class="form-control">
                            <option value="">全部</option>
                            <option value="0" <c:if test="${accountState == 0}">selected="selected" </c:if>>未到账 </option>
                            <option value="1" <c:if test="${accountState == 1}">selected="selected" </c:if>>已到账</option>
                        </select>
                    </div>
                    <div class="form-group">
                        是否生产案件:
                        <select name="isTestcase"  class="form-control">
                            <option value="-1">全部</option>
                            <option value="0" <c:if test="${isTestcase == 0}">selected="selected" </c:if>>是</option>
                            <option value="1" <c:if test="${isTestcase == 1}">selected="selected" </c:if>>否</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                    </div>
                </form>
            </div>

        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th class="th-checkbox">
                    <%--<input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选">--%>
                </th>
                <th width="80">案件编号</th>
                <th width="200">案件标题</th>
                <th width="100">代扣金额</th>
                <th width="160">代扣时间</th>
                <th width="100">代扣状态</th>
                <th width="100">是否到账</th>
                <th width="100">代扣类型</th>
                <th width="100">代扣申请类型</th>
                <th width="100">订单号</th>
                <th width="200">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td>${item.caseNo}</td>
                    <td>${item.caseTitle}</td>
                    <td>${item.withholdMoney}</td>
                    <td><fmt:formatDate value="${item.withholdTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td >
                        <c:if test="${item.withholdState == 0}">
                            代扣申请
                        </c:if>
                        <c:if test="${item.withholdState == 1}">
                            代扣中
                        </c:if>
                        <c:if test="${item.withholdState == 2}">
                            代扣成功
                        </c:if>
                        <c:if test="${item.withholdState == 3}">
                            <span style="color: #ff0000">代扣失败</span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${item.accountState == 0 || item.accountState == null}">
                            未到账
                        </c:if>
                        <c:if test="${item.accountState == 1}">
                            已到账
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${item.withholdType == 1}">
                            现金
                        </c:if>
                        <c:if test="${item.withholdType == 3}">
                            转账
                        </c:if>
                        <c:if test="${item.withholdType == 2}">
                            苏宁代扣
                        </c:if>
                        <c:if test="${item.withholdType == 4}">
                            平安代扣
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${item.applyType == 0}">
                            放款代扣
                        </c:if>
                        <c:if test="${item.applyType == 1}">
                            还款代扣
                        </c:if>
                        <c:if test="${item.applyType == 4}">
                            还款代扣(服务费)
                        </c:if>
                        <c:if test="${item.applyType == 2}">
                            紧急代扣
                        </c:if>
                        <c:if test="${item.applyType == 3}">
                            预收定金
                        </c:if>
                        <c:if test="${item.applyType == 5}">
                            司法评估
                        </c:if>
                        <c:if test="${item.applyType == 6}">
                            司法评估补费
                        </c:if>
                    </td>
                    <td>${item.orderCode}</td>
                    <td>
                        <a href="javascript:findWithholdApplyView('${item.id}','${type}','${item.caseId}');">查看</a>
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
            <jsp:param name="requestUrl" value="${ctx}/suning/withholdApply/list?type=${type}&caseNo=${caseNo}&caseTitle=${caseTitle}&orderCode=${orderCode}&numberType=${numberType}&isTestcase=${isTestcase}&accountState=${accountState}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

   var findWithholdApplyView = function (id,type,caseId) {
       openDialog({
           frame:true,
           title:"操作",
           height:600,
           width:1000,
           url:"${ctx}/suning/withholdApply/findWithholdApplyView?id="+id+"&type="+type+"&caseId="+caseId,
           load:true
       });
   }


</script>
</body>
</html>
