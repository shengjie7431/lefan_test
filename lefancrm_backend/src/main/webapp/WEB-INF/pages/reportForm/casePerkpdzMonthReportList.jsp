<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>个人业务开票到账月报表</title>
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
        <h3>个人业务开票到账月报表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/reportForm/casePerkpdzMonthReportList" method="post">
                    <div class="form-group">
                        机构名称:
                        <select name="orgId" class="form-control">
                            <option value="">全部</option>
                            <c:forEach items="${orgInfoDtos}" var="itemOrg">
                                <option <c:if test="${orgId == itemOrg.id}">selected="selected" </c:if> value="${itemOrg.id}" >${itemOrg.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        时间：<input name="date" type="text" value="${date}" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly>
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        <button class="btn btn-default"><a href="${ctx}/reportForm/casePerkpdzMonthReportListExport?orgId=${orgId}&date=${date}&export=1">导出</a></button>
                    </div>
                </form>
            </div>
        </div>
        <div class="table-content">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th style="min-width:160px" rowspan="2">机构名称</th>
                    <th style="min-width:1120px" colspan="9">开票金额</th>
                    <th style="min-width:1120px" colspan="9">到账金额</th>
                    <th style="min-width:1120px" colspan="9">作废金额</th>
                    <th style="min-width:1120px" colspan="9">红冲金额</th>
                </tr>
                <tr>
                    <th style="min-width:120px">全程通</th>
                    <th style="min-width:120px">风险调查</th>
                    <th style="min-width:140px">交警队工作室</th>
                    <th style="min-width:140px">法院工作室</th>
                    <th style="min-width:120px">财产险</th>
                    <th style="min-width:120px">公估评估</th>
                    <th style="min-width:120px">其他</th>
                    <th style="min-width:120px">个人业务</th>
                    <th style="min-width:120px">合计</th>
                    <th style="min-width:120px">全程通</th>
                    <th style="min-width:120px">风险调查</th>
                    <th style="min-width:140px">交警工作室</th>
                    <th style="min-width:140px">法院工作室</th>
                    <th style="min-width:120px">财产险</th>
                    <th style="min-width:120px">公估评估</th>
                    <th style="min-width:120px">其他</th>
                    <th style="min-width:120px">个人业务</th>
                    <th style="min-width:120px">合计</th>
                    <th style="min-width:120px">全程通</th>
                    <th style="min-width:120px">风险调查</th>
                    <th style="min-width:140px">交警工作室</th>
                    <th style="min-width:140px">法院工作室</th>
                    <th style="min-width:120px">财产险</th>
                    <th style="min-width:120px">公估评估</th>
                    <th style="min-width:120px">其他</th>
                    <th style="min-width:120px">个人业务</th>
                    <th style="min-width:120px">合计</th>
                    <th style="min-width:120px">全程通</th>
                    <th style="min-width:120px">风险调查</th>
                    <th style="min-width:140px">交警工作室</th>
                    <th style="min-width:140px">法院工作室</th>
                    <th style="min-width:120px">财产险</th>
                    <th style="min-width:120px">公估评估</th>
                    <th style="min-width:120px">其他</th>
                    <th style="min-width:120px">个人业务</th>
                    <th style="min-width:120px">合计</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${list}" var="item">
                    <tr>
                        <td>${item.orgName}</td>
                        <td>${item.qctInvoiceMoney == null ? 0.00: item.qctInvoiceMoney}</td>
                        <td>${item.fxdcInvoiceMoney == null ? 0.00: item.fxdcInvoiceMoney}</td>
                        <td>${item.jjInvoiceMoney == null ? 0.00: item.jjInvoiceMoney}</td>
                        <td>${item.fyInvoiceMoney == null ? 0.00: item.fyInvoiceMoney}</td>
                        <td>${item.ccxInvoiceMoney == null ? 0.00: item.ccxInvoiceMoney}</td>
                        <td>${item.ggpgInvoiceMoney == null ? 0.00: item.ggpgInvoiceMoney}</td>
                        <td>${item.otherInvoiceMoney == null ? 0.00: item.otherInvoiceMoney}</td>
                        <td>${item.pbInvoiceMoney == null ? 0.00: item.pbInvoiceMoney}</td>
                        <td style="background-color:#e6e6d2">${item.invoiceTitle == null ? 0.00: item.invoiceTitle}</td>

                        <td>${item.qctReceivedMoney == null ? 0.00: item.qctReceivedMoney}</td>
                        <td>${item.fxdcReceivedMoney == null ? 0.00: item.fxdcReceivedMoney}</td>
                        <td>${item.jjReceivedMoney == null ? 0.00: item.jjReceivedMoney}</td>
                        <td>${item.fyReceivedMoney == null ? 0.00: item.fyReceivedMoney}</td>
                        <td>${item.ccxReceivedMoney == null ? 0.00: item.ccxReceivedMoney}</td>
                        <td>${item.ggpgReceivedMoney == null ? 0.00: item.ggpgReceivedMoney}</td>
                        <td>${item.otherReceivedMoney == null ? 0.00: item.otherReceivedMoney}</td>
                        <td>${item.pbReceivedMoney == null ? 0.00: item.pbReceivedMoney}</td>
                        <td style="background-color:#e6e6d2">${item.receivedTitle == null ? 0.00: item.receivedTitle}</td>

                        <td>${item.qctInvalidMoney == null ? 0.00: item.qctInvalidMoney}</td>
                        <td>${item.fxdcInvalidMoney == null ? 0.00: item.fxdcInvalidMoney}</td>
                        <td>${item.jjInvalidMoney == null ? 0.00: item.jjInvalidMoney}</td>
                        <td>${item.fyInvalidMoney == null ? 0.00: item.fyInvalidMoney}</td>
                        <td>${item.ccxInvalidMoney == null ? 0.00: item.ccxInvalidMoney}</td>
                        <td>${item.ggpgInvalidMoney == null ? 0.00: item.ggpgInvalidMoney}</td>
                        <td>${item.otherInvalidMoney == null ? 0.00: item.otherInvalidMoney}</td>
                        <td>${item.pbInvalidMoney == null ? 0.00: item.pbInvalidMoney}</td>
                        <td style="background-color:#e6e6d2">${item.invalidTitle == null ? 0.00: item.invalidTitle}</td>

                        <td>${item.qctRedrushMoney == null ? 0.00: item.qctRedrushMoney}</td>
                        <td>${item.fxdcRedrushMoney == null ? 0.00: item.fxdcRedrushMoney}</td>
                        <td>${item.jjRedrushMoney == null ? 0.00: item.jjRedrushMoney}</td>
                        <td>${item.fyRedrushMoney == null ? 0.00: item.fyRedrushMoney}</td>
                        <td>${item.ccxRedrushMoney == null ? 0.00: item.ccxRedrushMoney}</td>
                        <td>${item.ggpgRedrushMoney == null ? 0.00: item.ggpgRedrushMoney}</td>
                        <td>${item.otherRedrushMoney == null ? 0.00: item.otherRedrushMoney}</td>
                        <td>${item.pbRedrushMoney == null ? 0.00: item.pbRedrushMoney}</td>
                        <td style="background-color:#e6e6d2">${item.redrushTitle == null ? 0.00: item.redrushTitle}</td>
                    </tr>
                </c:forEach>
                <c:forEach items="${titleList}" var="item">
                    <tr  style="color: red">
                        <td>总计</td>
                        <td>${item.qctInvoiceMoney == null ? 0.00: item.qctInvoiceMoney}</td>
                        <td>${item.fxdcInvoiceMoney == null ? 0.00: item.fxdcInvoiceMoney}</td>
                        <td>${item.jjInvoiceMoney == null ? 0.00: item.jjInvoiceMoney}</td>
                        <td>${item.fyInvoiceMoney == null ? 0.00: item.fyInvoiceMoney}</td>
                        <td>${item.ccxInvoiceMoney == null ? 0.00: item.ccxInvoiceMoney}</td>
                        <td>${item.ggpgInvoiceMoney == null ? 0.00: item.ggpgInvoiceMoney}</td>
                        <td>${item.otherInvoiceMoney == null ? 0.00: item.otherInvoiceMoney}</td>
                        <td>${item.pbInvoiceMoney == null ? 0.00: item.pbInvoiceMoney}</td>
                        <td style="background-color:#e6e6d2">${item.invoiceTitle == null ? 0.00: item.invoiceTitle}</td>

                        <td>${item.qctReceivedMoney == null ? 0.00: item.qctReceivedMoney}</td>
                        <td>${item.fxdcReceivedMoney == null ? 0.00: item.fxdcReceivedMoney}</td>
                        <td>${item.jjReceivedMoney == null ? 0.00: item.jjReceivedMoney}</td>
                        <td>${item.fyReceivedMoney == null ? 0.00: item.fyReceivedMoney}</td>
                        <td>${item.ccxReceivedMoney == null ? 0.00: item.ccxReceivedMoney}</td>
                        <td>${item.ggpgReceivedMoney == null ? 0.00: item.ggpgReceivedMoney}</td>
                        <td>${item.otherReceivedMoney == null ? 0.00: item.otherReceivedMoney}</td>
                        <td>${item.pbReceivedMoney == null ? 0.00: item.pbReceivedMoney}</td>
                        <td style="background-color:#e6e6d2">${item.receivedTitle == null ? 0.00: item.receivedTitle}</td>

                        <td>${item.qctInvalidMoney == null ? 0.00: item.qctInvalidMoney}</td>
                        <td>${item.fxdcInvalidMoney == null ? 0.00: item.fxdcInvalidMoney}</td>
                        <td>${item.jjInvalidMoney == null ? 0.00: item.jjInvalidMoney}</td>
                        <td>${item.fyInvalidMoney == null ? 0.00: item.fyInvalidMoney}</td>
                        <td>${item.ccxInvalidMoney == null ? 0.00: item.ccxInvalidMoney}</td>
                        <td>${item.ggpgInvalidMoney == null ? 0.00: item.ggpgInvalidMoney}</td>
                        <td>${item.otherInvalidMoney == null ? 0.00: item.otherInvalidMoney}</td>
                        <td>${item.pbInvalidMoney == null ? 0.00: item.pbInvalidMoney}</td>
                        <td style="background-color:#e6e6d2">${item.invalidTitle == null ? 0.00: item.invalidTitle}</td>

                        <td>${item.qctRedrushMoney == null ? 0.00: item.qctRedrushMoney}</td>
                        <td>${item.fxdcRedrushMoney == null ? 0.00: item.fxdcRedrushMoney}</td>
                        <td>${item.jjRedrushMoney == null ? 0.00: item.jjRedrushMoney}</td>
                        <td>${item.fyRedrushMoney == null ? 0.00: item.fyRedrushMoney}</td>
                        <td>${item.ccxRedrushMoney == null ? 0.00: item.ccxRedrushMoney}</td>
                        <td>${item.ggpgRedrushMoney == null ? 0.00: item.ggpgRedrushMoney}</td>
                        <td>${item.otherRedrushMoney == null ? 0.00: item.otherRedrushMoney}</td>
                        <td>${item.pbRedrushMoney == null ? 0.00: item.pbRedrushMoney}</td>
                        <td style="background-color:#e6e6d2">${item.redrushTitle == null ? 0.00: item.redrushTitle}</td>
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
            <jsp:param name="requestUrl" value="${ctx}/reportForm/casePerkpdzMonthReportList?orgId=${orgId}&date=${date}" />
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
