<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>退货列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>退货列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/orderReturn/list" method="post">
                    <div class="form-group">
                        退货单编号: <input name="orderReturnCode" type="text"  value="${orderReturnCode}" class="form-control">
                    </div>
                    <div class="form-group">
                        订单编号: <input name="orderCode" type="text"  value="${orderCode}" class="form-control">
                    </div>
                    <div class="form-group">
                        用户名称: <input name="userName" type="text"  value="${userName}" class="form-control">
                    </div>
                    <div class="form-group">
                        退款状态: <select name="state"  class="form-control">
                            <option value="" >全部</option>
                            <option value="1" >待退款</option>
                            <option value="2" >退款中</option>
                            <option value="3" >已退款</option>
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
                <th width="150">退货单编号</th>
                <th width="150">订单编号</th>
                <th width="90">退款金额</th>
                <th width="200">退款时间</th>
                <th width="150">用户名称</th>
                <th width="100">退款状态</th>
                <th width="300">退款说明</th>
                <th width="200">创建时间</th>
                <th width="300">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td>${item.orderReturnCode}</td>
                    <td>${item.orderCode}</td>
                    <td>${item.returnPrice}</td>
                    <td><fmt:formatDate value="${item.returnTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>${item.userName}</td>
                    <td>
                        <c:if test="${item.state == 1}">待退款</c:if>
                        <c:if test="${item.state == 2}">退款中</c:if>
                        <c:if test="${item.state == 3}">已退款</c:if>
                    </td>
                    <td>${item.remart}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <c:if test="${item.state == 1}"><a href="#">在线退款</a></c:if>
                        <c:if test="${item.state == 1}"><a href="javascript:lineRefund('${item.id}');">线下退款</a></c:if>
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
            <jsp:param name="requestUrl" value="${ctx}/orderReturn/list?orderReturnCode=${orderReturnCode}&orderCode=${orderCode}&userName=${userName}&state=${state}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/
   function lineRefund(id){
       ajaxSubmit("${ctx}/orderReturn/lineRefund",{"id":id},reload,"线下退款成功","确认线下退款吗？");
   }
  /*  var commentDetail = function(commentid){
        openDialog({
            frame:true,
            title:"查看留言信息",
            height:600,
            width:500,
            url:"${ctx}/comment/detail?id="+commentid
        });
    }*/
</script>
</body>
</html>
