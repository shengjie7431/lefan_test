<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">

</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>统计 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/hobo/case/reportData" method="post">
                    <div class="form-group">
                        委托时间：
                        <input name="entrustTimeStart" type="text" value="${entrustTimeStart}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        <span>--</span>
                        <input name="entrustTimeEnd" type="text" value="${entrustTimeEnd}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                </form>
            </div>
        </div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>产品名称</th>
                <th>数量</th>
                <th>单价</th>
                <th>总计</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td >${item.proName}</td>
                    <td>${item.num}</td>
                    <td>${item.price}</td>
                    <td>${item.num * item.price}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
</div>


<script>

</script>
</body>
</html>
