<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>CC佣金记录</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/userCommissionInfo/userCommissionInfoList" method="post">

                </form>
            </div>
        </div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">CC姓名</th>
                <th width="100">案件标题</th>
                <th width="150">佣金类型</th>
                <th width="150">本次到账金额</th>
                <th width="150">佣金金额</th>
                <th width="100">案件ID</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.userName}</td>
                    <td>${item.caseTitle}</td>
                    <td>
                        <c:if test="${item.commissionType == 1}"> CC佣金</c:if>
                        <c:if test="${item.commissionType == 2}"> 管理佣金</c:if>
                        <c:if test="${item.commissionType == 3}"> 工作室佣金</c:if>
                        <c:if test="${item.commissionType == 4}"> 扣除案件签约费用</c:if>
                        <c:if test="${item.commissionType == 5}"> 贷款服务费</c:if>
                    </td>
                    <td>${item.arrivalMoney}</td>
                    <td>${item.comMoney}</td>
                    <td>${item.caseId}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
    </div>
<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/userCommissionInfo/userCommissionInfoList?userId=${userId}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

</script>
</body>
</html>
