<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>订单列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>订单列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                   <div class="form-group">
                       订单编号:<input name="orderCode" type="text" value="${orderCode}" class="form-control">
                   </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">订单编号</th>
                <th width="100">商品code</th>
                <th width="100">兑换人名称</th>
                <th width="100">兑换人手机号</th>
                <th width="100">物流单号</th>
                <th width="100">订单状态</th>
                <th width="100">是否支付</th>
                <th width="100">支付时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.orderCode}</td>
                    <td>${item.proCode}</td>
                    <td>${item.allowUserName}</td>
                    <td>${item.allowUserTel}</td>
                    <td>${item.logisticsCode}</td>
                    <td>
                        <c:if test="${item.orderState== 1}">待付款</c:if>
                        <c:if test="${item.orderState== 2}">待发货</c:if>
                        <c:if test="${item.orderState== 3}">已发货</c:if>
                        <c:if test="${item.orderState== 4}">已收货</c:if>
                    </td>
                    <td>
                        <c:if test="${item.isPay== 0}">否</c:if>
                        <c:if test="${item.isPay== 1}">是</c:if>
                    </td>
                    <td><fmt:formatDate value="${item.payTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <a href="javascript:info('${item.id}','${surveyCode}');">详情</a>
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
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&orderCode=${orderCode}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    var info = function(id,surveyCode){
        openDialog({
            frame:true,
            title:"详情",
            height:650,
            width:1000,
            url:"${ctx}/baseSurvey/info?id="+id+"&surveyCode="+surveyCode,
            load:true
        });
    }
</script>
</body>
</html>
