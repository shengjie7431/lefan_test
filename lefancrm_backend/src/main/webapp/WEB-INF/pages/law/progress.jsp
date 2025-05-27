<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<jsp:useBean id="dateValue" class="java.util.Date"/>
<!DOCTYPE html>
<html>
<head>
    <title>案件详情</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<style>
    .stepList {
        margin: 0 32px;
    }

    .stepItem {
        display: flex;
    }

    .stepDate {
        width: 140px;
        /*font-size: 28px;*/
        color: #555;
    }

    .stepDot {
        width: 60px;
        position: relative;
        border-left: solid 1px #54b0ff;
    }

    .stepDot-dot {
        box-sizing: border-box;
        border: solid 1px #54b0ff;
        background: #fff;
        width: 15px;
        height: 15px;
        border-radius: 15px;
        position: absolute;
        left: -8px;
    }
    .stepDot-dot.finished{
        background: #54b0ff;
    }
    .stepDetail {
        padding-left: 30px;
        flex: 1;
        color: #555;
        padding-bottom: 50px;
    }

    .stepDetail-title {
        /*font-size: 34px;*/
        line-height: 1.5;
    }

    .stepDetail-detail {
        font-size: 24px;
    }

    .finished.stepDot-dot {
        background: #54b0ff;
    }
    .stepItem.omit {
        min-height: 96px;
    }
    .stepDot.omit {
        width: 60px;
        position: relative;
        border-left: dashed 1px #54b0ff;
    }

</style>
<body>
<div class="container">
    <div class="main-top">
        <%--<h3>案件详情</h3>--%>
    </div><!--main-top-->
    <c:forEach items="${list}" var="item">
        <div class="stepItem" style="margin-left: 10px">
            <div class="stepDate"><fmt:formatDate value="${item.followTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
            <div class="stepDot"><div class="stepDot-dot finished"></div></div>
            <div class="stepDetail"><div class="stepDetail-title">${item.caseStateStr}<br>(${item.followDesc == null ? "" : item.followDesc})</div><div class="stepDetail-detail"></div></div>
        </div>
    </c:forEach>
    <c:if test="${list.size() == 0}">
        <span style="color:red">无数据</span>
    </c:if>
</div><!--main end-->
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
</script>
</body>
</html>
