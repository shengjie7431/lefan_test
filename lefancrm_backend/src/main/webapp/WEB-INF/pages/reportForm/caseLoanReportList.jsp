<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>贷款报表</title>
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
        <h3>贷款报表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/reportForm/caseLoanReportList" method="post">
                    <div class="form-group">
                        机构名称:
                        <select name="orgId" class="form-control">
                            <option value="">全部</option>
                            <c:forEach items="${orgInfoDtos}" var="itemOrg">
                                <option <c:if test="${orgId == itemOrg.id}">selected="selected" </c:if> value="${itemOrg.id}" >${itemOrg.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <%--<div class="form-group">--%>
                        <%--时间：<input name="date" type="text" value="${date}" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >--%>
                    <%--</div>--%>
                    <div class="form-group">
                        日期：<input name="startDate" type="text" value="${startDate}" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <div class="form-group">
                        -- <input name="endDate" type="text" value="${endDate}" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        <button class="btn btn-default"><a href="${ctx}/reportForm/caseLoanReportListExport?orgId=${orgId}&startDate=${startDate}&endDate=${endDate}&export=1">导出</a></button>
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
                    <th style="min-width:200px" colspan="2">累计放款</th>
                    <th style="min-width:200px" colspan="2">累计回款</th>
                    <th style="min-width:200px" colspan="2">累计应收金额</th>
                    <th style="min-width:200px" colspan="2">当日放款金额</th>
                    <th style="min-width:200px" colspan="2">当日回款金额</th>
                    <th style="min-width:200px" colspan="2">当月放款金额</th>
                    <th style="min-width:200px" colspan="2">当月回款金额</th>
                    <th style="min-width:160px" rowspan="2">贷款周期4个月的案件</th>
                    <th style="min-width:160px" rowspan="2">贷款周期6个月的案件</th>
                    <th style="min-width:160px" rowspan="2">贷款周期8个月的案件</th>
                    <th style="min-width:160px" rowspan="2">贷款周期10个月的案件</th>
                    <th style="min-width:160px" rowspan="2">贷款周期12个月的案件</th>
                    <th style="min-width:120px" rowspan="2">超过12个月案件</th>
                    <th style="min-width:100px" rowspan="2">逾期案件数量</th>
                </tr>
                <tr>
                    <th style="min-width:100px">乐凡</th>
                    <th style="min-width:100px">苏宁</th>
                    <th style="min-width:100px">乐凡</th>
                    <th style="min-width:100px">苏宁</th>
                    <th style="min-width:120px">乐凡</th>
                    <th style="min-width:120px">苏宁</th>
                    <th style="min-width:120px">乐凡</th>
                    <th style="min-width:120px">苏宁</th>
                    <th style="min-width:120px">乐凡</th>
                    <th style="min-width:120px">苏宁</th>
                    <th style="min-width:120px">乐凡</th>
                    <th style="min-width:120px">苏宁</th>
                    <th style="min-width:120px">乐凡</th>
                    <th style="min-width:120px">苏宁</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${apiRsp.results}" var="item">
                    <tr>
                        <td>${item.orgName}</td>
                        <td><fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd"/></td>
                        <td>${item.totalLefanLoanMoney == null ? 0.00 : item.totalLefanLoanMoney}</td>
                        <td>${item.totalSuningLoanMoney == null ? 0.00 : item.totalSuningLoanMoney}</td>
                        <td>${item.totalLefanReturnMoney == null ? 0.00 : item.totalLefanReturnMoney}</td>
                        <td>${item.totalSuningReturnMoney == null ? 0.00 : item.totalSuningReturnMoney}</td>
                        <td>${item.totalLefanReceivableMoney == null ? 0.00 : item.totalLefanReceivableMoney}</td>
                        <td>${item.totalSuningReceivableMoney == null ? 0.00 : item.totalSuningReceivableMoney}</td>
                        <td>${item.totalLfloanDayMoney == null ? 0.00 : item.totalLfloanDayMoney}</td>
                        <td>${item.totalSnloanDayMoney == null ? 0.00 : item.totalSnloanDayMoney}</td>
                        <td>${item.totalLfreturnDayMoney == null ? 0.00 : item.totalLfreturnDayMoney}</td>
                        <td>${item.totalSnreturnDayMoney == null ? 0.00 : item.totalSnreturnDayMoney}</td>
                        <td>${item.totalLfloanMonthMoney == null ? 0.00 : item.totalLfloanMonthMoney}</td>
                        <td>${item.totalSnloanMonthMoney == null ? 0.00 : item.totalSnloanMonthMoney}</td>
                        <td>${item.totalLfreturnMonthMoney == null ? 0.00 : item.totalLfreturnMonthMoney}</td>
                        <td>${item.totalSnreturnMonthMoney == null ? 0.00 : item.totalSnreturnMonthMoney}</td>
                        <td>${item.loanFourNum == null ? 0 : item.loanFourNum}</td>
                        <td>${item.loanSixNum == null ? 0 : item.loanSixNum}</td>
                        <td>${item.loanEightNum == null ? 0 : item.loanEightNum}</td>
                        <td>${item.loanTenNum == null ? 0 : item.loanTenNum}</td>
                        <td>${item.loanDecNum == null ? 0 : item.loanDecNum}</td>
                        <td>${item.loanOutdecNum == null ? 0 : item.loanOutdecNum}</td>
                        <td>${item.loanExpectNum == null ? 0 : item.loanExpectNum}</td>
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
            <jsp:param name="requestUrl" value="${ctx}/reportForm/caseLoanReportList?orgId=${orgId}&startDate=${startDate}&endDate=${endDate}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script>
    var caseLoanReportView = function(id){
        openDialog({
            frame:true,
            title:"查看详情",
            height:650,
            width:1000,
            url:"${ctx}/reportForm/caseLoanReportView?id="+id
        });
    }
</script>
</body>
</html>
