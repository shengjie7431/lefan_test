<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>会员卡列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>会员卡列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/orderExtend/list" method="post">
                    <input type="hidden" name="orderId" id="orderId" value="${orderId}"/>
                    <div class="form-group">
                        联系电话: <input name="phoneNumber" type="text"  value="${phoneNumber}" class="form-control">
                    </div>
                    <div class="form-group">
                        车牌号: <input name="carNumber" type="text"  value="${carNumber}" class="form-control">
                    </div>
                    <div class="form-group">
                        会员卡号: <input name="cardNumber" type="text"  value="${cardNumber}" class="form-control">
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
                <%--<th width="150">订单ID</th>--%>
                <th width="200">出生年月</th>
                <th width="200">联系电话</th>
                <th width="100">车牌号</th>
                <th width="300">会员卡号</th>
                <th width="300">会员卡状态</th>
                <th width="300">邮箱地址</th>
                <th width="200">创建时间</th>
                <th width="300">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <%--<td>${item.orderId}</td>--%>
                    <td><fmt:formatDate value="${item.bothTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>${item.phoneNumber}</td>
                    <td>${item.carNumber}</td>
                    <td>${item.cardNumber}</td>
                    <td>
                    <c:if test="${item.cardState == 1}">待激活</c:if>
                    <c:if test="${item.cardState == 2}">已激活</c:if>
                    </td>
                    <td>${item.eMail}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>

                    <td>
                        <%--<a href="#">查询子订单</a>--%>
                        <%--<c:if test="${item.orderType == 1}"><a href="#">查询会员卡</a></c:if>--%>
                        <%--<c:if test="${item.orderType == 2 && (item.orderState>1&&item.orderState<4)}"><a href="javascript:distribution('${item.userId}','${item.orderCode}');">配送</a></c:if>--%>
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
            <jsp:param name="requestUrl" value="${ctx}/orderExtend/list?phoneNumber=${phoneNumber}&carNumber=${carNumber}&cardNumber=${cardNumber}&orderId=${orderId}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/
   function distribution(userId,orderCode){
       ajaxSubmit("${ctx}/orderInfo/distribution",{"userId":userId,"orderCode":orderCode},reload,"配送成功","确认配送吗？");
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
