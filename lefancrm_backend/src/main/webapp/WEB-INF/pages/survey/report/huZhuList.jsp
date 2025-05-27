<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>互助超时列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>互助超时列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <input type="hidden" name="surveyOrgId" id="surveyOrgId" />
                <input type="hidden" name="type" id="type" />
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="200">案件编号</th>
                <th width="200">保险公司</th>
                <th width="100">被调查人</th>
                <th width="100">联系方式</th>
                <th width="100">案件状态</th>
                <th width="100">委托时间</th>
                <th width="200">调查机构</th>
                <th width="100">机构案件状态</th>
                <th width="100">分派机构时间</th>
                <th width="100">机构提交时间</th>
                <th width="100">机构截止时间</th>
                <th width="100">机构时效</th>
                <th width="100">驳回次数</th>
                <th width="100">案件截止时间</th>
                <th width="100">案件时效</th>
                <th width="80">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.surveyCaseNo}</td>
                    <td>${item.entrustOrgName}</td>
                    <td>${item.surveyPerson}</td>
                    <td>${item.surveryPersonTel}</td>
                    <td>${item.surveyStateName}</td>
                    <td ><fmt:formatDate value="${item.entrustTime}" pattern="yyyy-MM-dd"/></td>
                    <td>${item.surveyOrgName}</td>
                    <td>${item.orgSurveyStateName}</td>
                    <td ><fmt:formatDate value="${item.orgAssignDate}" pattern="yyyy-MM-dd"/></td>
                    <td ><fmt:formatDate value="${item.orgReportDate}" pattern="yyyy-MM-dd"/></td>
                    <td ><fmt:formatDate value="${item.orgEndTime}" pattern="yyyy-MM-dd"/></td>
                    <td style="color: ${item.orgEfficiencyStateColor}">${item.orgEfficiencyState}</td>
                    <td>${item.vetoNum}</td>
                    <td ><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd"/></td>
                    <td style="color: ${item.efficiencyStateColor}">${item.efficiencyState}</td>
                    <td><a href="javascript:info('${item.surveyInfoId}');">处理 </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/survey/report/huZhuList?surveyOrgId=${surveyOrgId}&type=${type}&entrustOrgIds=${entrustOrgIds}&beginDate=${beginDate}&endDate=${endDate}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect.js?V=1"></script>
<script>

    var info = function(id){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"详情",
            height:height,
            width:width,
            url:"${ctx}/survey/case/info?id="+id,
            load:true
        });
    }
</script>
</body>
</html>
