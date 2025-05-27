<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>子订单列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>子订单列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/orderPro/list" method="post">
                    <input type="hidden" name="orderId" id="orderId" value="${orderId}"/>
                    <%--<div class="form-group">--%>
                        <%--订单ID: <input name="orderId" type="text"  value="${orderId}" class="form-control">--%>
                    <%--</div>--%>
                    <div class="form-group">
                        商品编号: <input name="productCode" type="text"  value="${productCode}" class="form-control">
                    </div>
                    <div class="form-group">
                        商品类型: <select name="productType"  class="form-control">
                            <option value="" >全部</option>
                            <option value="1" >乐驾卡</option>
                            <option value="2" >乐行卡</option>
                            <option value="3" >乐骑卡</option>
                            <option value="4" >及时雨</option>
                            <option value="5" >管家卡</option>
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
                <%--<th width="150">订单ID</th>--%>
                <th width="90">商品编号</th>
                <th width="200">商品名称</th>
                <th width="100">商品价格</th>
                <th width="300">商品类型</th>
                <th width="300">商品图标</th>
                <th width="300">套餐名称</th>
                <th width="300">套餐价格</th>
                <th width="300">套餐图标</th>
                <th width="300">状态</th>
                <th width="300">创建时间</th>
                <th width="300">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <%--<td>${item.orderId}</td>--%>
                    <td>${item.productCode}</td>
                    <td>${item.productName}</td>
                    <td>${item.productPrice}</td>
                    <td>
                        <c:if test="${item.productType == 1}">乐驾卡</c:if>
                        <c:if test="${item.productType == 2}">乐行卡</c:if>
                        <c:if test="${item.productType == 3}">乐骑卡</c:if>
                        <c:if test="${item.productType == 4}">及时雨</c:if>
                        <c:if test="${item.productType == 5}">管家卡</c:if>
                    </td>
                    <td><img id="productPic" src="${item.productPic}" width="80" height="80"></td>
                    <td>${item.specName}</td>
                    <td>${item.specPrice}</td>
                    <td><img id="specPrice" src="${item.specImg}" width="80" height="80"></td>
                    <td>${item.proState}</td>

                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>

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
            <jsp:param name="requestUrl" value="${ctx}/orderPro/list?orderId=${orderId}&productCode=${productCode}&productType=${productType}" />
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
