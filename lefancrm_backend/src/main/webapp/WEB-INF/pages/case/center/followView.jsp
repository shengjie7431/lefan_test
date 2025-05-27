<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<jsp:useBean id="dateValue" class="java.util.Date"/>
<!DOCTYPE html>
<html>
<head>
    <title>跟踪详情</title>
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
    </div>
    <c:if test="${caseFollows!=null}">
        <c:forEach items="${caseFollows}" var="item">
            <div class="stepItem" style="margin-left: 10px">
                <div class="stepDate"><fmt:formatDate value="${item.followTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                <div class="stepDot"><div class="stepDot-dot finished"></div></div>
                <div class="stepDetail">
                    <div class="stepDetail-title">
                        跟踪人：${item.followBy}
                        【
                        <c:if test="${item.gradationState  == 0}">业务员</c:if>
                        <c:if test="${item.gradationState  == 1}">客服</c:if>
                        <c:if test="${item.gradationState  == 2}">评估员</c:if>
                        <c:if test="${item.gradationState  == 3}">索赔员</c:if>
                        <c:if test="${item.gradationState  == 4}"></c:if>
                        <c:if test="${item.gradationState  == 5}"></c:if>
                        <c:if test="${item.gradationState  == 6}">诉讼员</c:if>】
                        <br>内容：${item.followDesc}<br>
                        <c:if test="${item.followType != 2}">
                            下一次跟踪时间：<fmt:formatDate value="${item.nextFollowTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </c:if>
                    </div>
                    <div class="stepDetail-detail"></div>
                </div>
            </div>
        </c:forEach>
    </c:if>
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/system/admin/list" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->


<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

</script>
</body>
</html>






















