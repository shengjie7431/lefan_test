<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>error</title>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<%--    ${msg}--%>
</body>

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script>
    alert("${msg}");
    reloadParent();
</script>
</html>
