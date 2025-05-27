<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>索赔报表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <style>
        .table-content {
            /*width: 1694px;*/
            overflow: auto;
        }
        table th {
            text-align: center;
            border-left: 2px solid #ddd;
            vertical-align: middle!important;
        }
        thead{
            background-color: #ecf0f1;
        }
        table td {
            text-align: center;
            border-left: 1px solid #ddd;
        }

    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>索赔报表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/reportForm/caseClaimReportList" method="post">
                    <div class="form-group">
                        机构名称:
                        <select name="orgId" id= "orgId" class="form-control">
                            <option value="">全部</option>
                            <c:forEach items="${orgInfoDtos}" var="itemOrg">
                                <option <c:if test="${orgId == itemOrg.id}">selected="selected" </c:if> value="${itemOrg.id}" >${itemOrg.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <%--<div class="form-group">--%>
                        <%--时间：<input name="date" id= "date" type="text" value="${date}" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >--%>
                    <%--</div>--%>
                    <div class="form-group">
                        日期：<input name="startDate" type="text" value="${startDate}" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <div class="form-group">
                        -- <input name="endDate" type="text" value="${endDate}" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;&nbsp; &nbsp;
                        <button class="btn btn-default"><a href="${ctx}/reportForm/caseClaimReportListExport?orgId=${orgId}&startDate=${startDate}&endDate=${endDate}&export=1">导出</a></button>
                    </div>
                </form>
            </div>
        </div>

        <div class="table-content">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th style="min-width:160px" rowspan="2">机构名称</th>
                    <th style="min-width:160px" rowspan="2">日期</th>
                    <th style="min-width:200px" colspan="2">当日结案数</th>
                    <th style="min-width:200px" colspan="2">当月结案数</th>
                    <th style="min-width:480px" colspan="3">当日回款金额</th>
                    <th style="min-width:480px" colspan="3">当月回款金额</th>
                    <th style="min-width:200px" colspan="2">解约案件数</th>
                    <th style="min-width:200px" colspan="2">累计未结案计数</th>
                    <th style="min-width:200px" colspan="2">累计已结案计数</th>
                    <th style="min-width:200px" colspan="2">累计结案类型</th>
                    <th style="min-width:160px" rowspan="2">总案件数</th>
                </tr>
                <tr>
                    <th style="min-width:100px">调解案件</th>
                    <th style="min-width:100px">诉讼案件</th>
                    <th style="min-width:100px">调解案件</th>
                    <th style="min-width:100px">诉讼案件</th>
                    <th style="min-width:160px">服务费</th>
                    <th style="min-width:160px">贷款本金</th>
                    <th style="min-width:160px">贷款利息</th>
                    <th style="min-width:160px">服务费</th>
                    <th style="min-width:160px">贷款本金</th>
                    <th style="min-width:160px">贷款利息</th>
                    <th style="min-width:100px">代理案件</th>
                    <th style="min-width:100px">贷款案件</th>
                    <th style="min-width:100px">代理案件</th>
                    <th style="min-width:100px">贷款案件</th>
                    <th style="min-width:100px">代理案件</th>
                    <th style="min-width:100px">贷款案件</th>
                    <th style="min-width:100px">调解案件</th>
                    <th style="min-width:100px">诉讼案件</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${apiRsp.results}" var="item">
                    <tr>
                        <td>${item.orgName}</td>
                        <td><fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd"/></td>
                        <td>${item.mediateClosedDayNum}</td>
                        <td>${item.litigationClosedDayNum}</td>
                        <td>${item.mediateClosedMonthNum}</td>
                        <td>${item.litigationClosedMonthNum}</td>
                        <td>${item.returnServiceDayMoney == null ? 0.00: item.returnServiceDayMoney}</td>
                        <td>${item.returnPrincipalDayMoney == null ? 0.00: item.returnPrincipalDayMoney}</td>
                        <td>${item.returnInterestDayMoney == null ? 0.00: item.returnInterestDayMoney}</td>
                        <td>${item.returnServiceMonthMoney == null ? 0.00: item.returnServiceMonthMoney}</td>
                        <td>${item.returnPrincipalMonthMoney == null ? 0.00: item.returnPrincipalMonthMoney}</td>
                        <td>${item.returnInterestMonthMoney == null ? 0.00: item.returnInterestMonthMoney}</td>
                        <td>${item.releaseAgentNum}</td>
                        <td>${item.releaseLoanNum}</td>
                        <td>${item.unclosedAgentNum}</td>
                        <td>${item.unclosedLoanNum}</td>
                        <td>${item.closedAgentNum}</td>
                        <td>${item.closedLoanNum}</td>
                        <td>${item.mediateClosedNum}</td>
                        <td>${item.litigationClosedNum}</td>
                        <td>${item.totalNum}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div><!--panel-info-->
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/reportForm/caseClaimReportList?orgId=${orgId}&startDate=${startDate}&endDate=${endDate}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script>

</script>
</body>
</html>
