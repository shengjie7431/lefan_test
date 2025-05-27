<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>理赔测算报告</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <%--<h3>理赔测算报告</h3>--%>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <%--form class="form-inline" role="form" action="${ctx}/paymentEstimate/paymentEstimateReportList" method="post">
                    <div class="form-group">
                        <input type="hidden" name="id" value="${id}">
                    </div>
                </form>--%>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">赔偿项目名称</th>
                <th width="150">实际损失</th>
                <th width="150">交强险可赔偿金额</th>
                <th width="150">商业险可赔偿金额</th>
                <th width="150">肇事方可赔偿金额</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${paymentEstimateReportDtos}" var="item">
                <tr>
                    <td>${item.paymentProject}</td>
                    <td>${item.medicalFee}</td>
                    <td>${item.compulsoryInsuranceFee}</td>
                    <td>${item.commercialInsuranceFee}</td>
                    <td>${item.causeTroubleFee}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

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
   /* var scenariosDelete = function(id){
        ajaxSubmit("${ctx}/scenarios/scenariosInfoTodelete?id="+id,{},function(){location.reload();},"删除成功","确认此条用户案例删除吗？",null);
    }*/
    function scenariosDelete(id){
        ajaxSubmit("${ctx}/scenarios/scenariosInfoTodelete",{"id":id},reload,"删除成功","确认删除该条用户案例吗？");
    }
</script>
</body>
</html>
