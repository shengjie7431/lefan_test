<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>变更历史</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>变更历史 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <input type="hidden" name="staffPersonnelInfoId" value="${staffPersonnelInfoId}">

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">变更字段</th>
                <th width="300">变更前</th>
                <th width="300">变更后</th>
                <th width="100">操作人</th>
                <th width="150">操作时间</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.fieldName}</td>
                    <td>${item.oldName}</td>
                    <td>${item.newName}</td>
                    <td>${item.createByName}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/staff/list?surveyCode=${surveyCode}&staffPersonnelInfoId=${staffPersonnelInfoId}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->
</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
</body>
</html>
