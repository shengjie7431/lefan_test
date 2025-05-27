<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>推广费用记录表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>推广费用记录表</h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">案件编号</th>
                <th width="150">案件名称</th>
                <th width="50">用户姓名</th>
                <th width="100">费用类型</th>
                <th width="50">费用金额</th>
                <th width="150">产生时间</th>

            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.caseNo}</td>
                    <td>${item.caseName}</td>
                    <td>${item.userName}</td>
                    <td>
                        <c:if test="${item.type == 1}"> 推广案件费用</c:if>
                        <c:if test="${item.type == 2}"> 案件成交费用</c:if>
                    </td>
                    <td>${item.money}</td>
                    <td>
                        <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>

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
            <jsp:param name="requestUrl" value="${ctx}/caseApplication/caseApplicationInfoList?userName=${userName}&state=${state}&caseNo=${caseNo}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

</script>
</body>
</html>
