<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案源分值明细</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>案源分值明细 <small>共<span>${sourceListMx.size()}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">调查员</th>
                <th width="100">分值</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${sourceListMx}" var="item">
                <tr>
                    <td>${item.userName}</td>
                    <td>${item.score}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">

    </div><!--main-bottom-->
</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

</script>
</body>
</html>
