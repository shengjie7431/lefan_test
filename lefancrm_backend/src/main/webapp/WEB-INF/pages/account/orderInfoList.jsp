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
                <form class="form-inline" role="form" action="${ctx}/account/orderInfoList" method="post">
                    <div class="form-group">
                        机构名称: <select name="orgId" class="form-control">
                        <option value="">全部</option>
                        <c:forEach items="${orgInfoDtos}" var="item1">
                            <option value="${item1.id}">${item1.orgName}</option>
                        </c:forEach>
                    </select>
                    </div>
                    <div class="form-group">
                        订单编号: <input name="orderCode" type="text"  value="${orderCode}" class="form-control">
                    </div>
                    <div class="form-group">
                        订单类型:
                        <select name="type" class="form-control">
                            <option value="">全部</option>
                            <option value="1">押金充值订单</option>
                            <option value="2">普通充值订单</option>
                            <option value="3">其他订单</option>
                        </select>
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
                <th width="150">订单编号</th>
                <th width="150">机构名称</th>
                <th width="150">用户名称</th>
                <th width="150">订单类型</th>
                <th width="150">订单金额</th>
                <th width="150">订单状态</th>
                <th width="150">付款时间</th>
                <th width="150">下单时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
               <tr>
                    <td>${item.orderCode}</td>
                    <td>${item.orgName}</td>
                    <td>${item.userName}</td>
                    <td>
                        <c:if test="${item.type==1}">押金充值</c:if>
                        <c:if test="${item.type==2}">普通充值</c:if>
                    </td>
                   <td>${item.price}</td>
                   <td>
                       <c:if test="${item.state == 1}">未付款</c:if>
                       <c:if test="${item.state == 2}">付款中</c:if>
                       <c:if test="${item.state == 3}">已经付款</c:if>
                   </td>
                    <td>
                        <fmt:formatDate value="${item.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                   <td>
                       <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                   </td>
                   <td>
                   </td>
                </tr>
            </c:forEach>
           <%-- <tr>
                <td colspan="10">
                    <c:if test="${page > 1}">
                        <a href="${ctx}/account/orderInfoList?page=${page-1}">上一页</a>&nbsp;
                    </c:if>
                    <a href="${ctx}/account/orderInfoList?page=${page+1}">下一页</a>
                </td>
            </tr>--%>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/account/orderInfoList" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>


   /* var getreport = function(id){
        openDialog({
            frame:true,
            title:"查看测算报告",
            height:1000,
            width:800,
            url:"${ctx}/paymentEstimate/paymentEstimateReportList?id="+id
        });
    }*/
</script>
</body>
</html>
