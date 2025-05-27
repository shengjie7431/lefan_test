<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title> 物流管理</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script>
        var updateInfo = function(id){
            openDialog({
                frame:true,
                title:"修改栏目信息",
                height:600,
                width:800,
                url:"${ctx}/newsCategory/queryById?id="+id
            });
        }
        var addinfo = function(){
            openDialog({
                frame:true,
                title:"添加栏目信息",
                height:600,
                width:800,
                url:"${ctx}/newsCategory/toAdd"
            });
        }

        var updateState = function(id,state,orderId){
                var val = "确定配送成功操作？"
                var toVal = "配送成功！"
            ajaxSubmit("${ctx}/logisticOrder/edit",{"id":id,"state":state,"orderId":orderId},reload,toVal,val,"操作失败");
        }
        var del = function(id,deleteFlag){
            ajaxSubmit("${ctx}/logisticOrder/edit",{"id":id,"deleteFlag":deleteFlag},reload,"删除成功","确定删除操作！","操作失败");
        }
    </script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3> 物流列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/logisticOrder/list" method="post">
                    <div class="form-group">
                        订单编号:<input name="orderCode" type="text" value="${orderCode}" class="form-control">
                        物流编号:<input name="logisticCode" type="text" value="${logisticCode}" class="form-control">
                        联系电话:<input name="linkTel" type="text" value="${linkTel}" class="form-control">
                        联系人:<input name="linkMan" type="text" value="${linkMan}" class="form-control">
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
                <th width="150">物流单号</th>
                <th width="150">物流公司</th>
                <th width="150">订单编号</th>
                <th width="150">联系人</th>
                <th width="150">联系电话</th>
                <th width="150">送货地址</th>
                <th width="150">商品名称</th>
                <th width="150">商品数量</th>
                <th width="150">物流状态</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.logisticCode}</td>
                    <td>${item.logisticCompany}</td>
                    <td> ${item.orderCode}</td>
                    <td> ${item.linkMan}</td>
                    <td> ${item.linkTel}</td>
                    <td> ${item.userAddress}</td>
                    <td> ${item.productName}</td>
                    <td> ${item.productNum}</td>
                    <td>
                        <c:if test="${item.state == 1}">配送中</c:if>
                        <c:if test="${item.state == 2}">配送完成</c:if>
                    </td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <c:if test="${item.state == 1}">
                            <a  href="javascript:void(0)" onclick="updateState(${item.id},2,${item.orderId})">配送成功</a>
                        </c:if>
                        <a  href="javascript:void(0)" onclick="del(${item.id},1)">删除</a>
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
            <jsp:param name="requestUrl" value="${ctx}/logisticOrder/list?orderCode=${orderCode}&logisticCode=${logisticCode}&linkTel=${linkTel}&linkMan=${linkMan}" />
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
