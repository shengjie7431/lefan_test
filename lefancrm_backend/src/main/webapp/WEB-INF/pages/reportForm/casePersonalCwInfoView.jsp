<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
    <script>

    </script>
    <style>
        .table-title{
            font-size: 14px;
            font-weight: bold;
            line-height: 40px;
        }
        table.spec-info tr td:nth-of-type(2n + 1) {
            width: 20%;
        }
        table.spec-info tr td:nth-of-type(2n) {
            width: 13%;
        }
    </style>
</head>
<body>

<div class="main">
    <input type="hidden" name="id" value="${casePersonalCwInfo.id}">
    <div class="main-boy">
        <div>
            <div class="table-title">案件基本信息</div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>机构</td>
                    <td>${casePersonalCwInfo.orgName}</td>
                    <td>业务员</td>
                    <td>${casePersonalCwInfo.salesmanName}</td>
                    <td>案件录入时间</td>
                    <td><fmt:formatDate value="${casePersonalCwInfo.caseEntryTime}" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td>案件编号</td>
                    <td>${casePersonalCwInfo.caseNo}</td>
                    <td>委托人</td>
                    <td>${casePersonalCwInfo.clientName}</td>
                    <td>签约日期</td>
                    <td><fmt:formatDate value="${casePersonalCwInfo.signTime}" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td>案件阶段</td>
                    <td>${casePersonalCwInfo.gradationState}</td>
                    <td>案件状态</td>
                    <td>${casePersonalCwInfo.caseState}</td>
                    <td>转办索赔日期</td>
                    <td><fmt:formatDate value="${casePersonalCwInfo.claimTime}" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td>索赔员</td>
                    <td>${casePersonalCwInfo.claimName}</td>
                    <td>是否结案</td>
                    <td>
                        <c:if test="${casePersonalCwInfo.isClosed == 0 ||casePersonalCwInfo.isClosed == null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalCwInfo.isClosed == 1}">
                            是
                        </c:if>
                    </td>
                    <td>结案日期</td>
                    <td><fmt:formatDate value="${casePersonalCwInfo.closedTime}" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td>合同摘要</td>
                    <td colspan="5">${casePersonalCwInfo.contractSummary}</td>
                </tr>
                </tbody>
            </table>


            <div class="table-title">收入</div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>审核服务费金额</td>
                    <td>${casePersonalCwInfo.checkServiceMoney}</td>
                    <td>应收基本费</td>
                    <td>${casePersonalCwInfo.reBasicMoney}</td>
                    <td>应收尾款</td>
                    <td>${casePersonalCwInfo.reTailMoney}</td>
                </tr>
                <tr>
                    <td>应收利息</td>
                    <td>${casePersonalCwInfo.reInterestMoney}</td>
                    <td>应收其他</td>
                    <td>${casePersonalCwInfo.reOtherMoney}</td>
                    <td>收入小计</td>
                    <td>${casePersonalCwInfo.reTotalMoney}</td>
                </tr>
                <tr>
                    <td>开票金额</td>
                    <td>${casePersonalCwInfo.billingMoney}</td>
                    <td>发票号码</td>
                    <td>${casePersonalCwInfo.billingCode}</td>
                    <td>开票日期</td>
                    <td><fmt:formatDate value="${casePersonalCwInfo.billingTime}" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td>发票小计</td>
                    <td>${casePersonalCwInfo.billingTotalMoney}</td>
                    <td>基本费回款金额</td>
                    <td>${casePersonalCwInfo.basicMoney}</td>
                    <td>基本费回款日期</td>
                    <td><fmt:formatDate value="${casePersonalCwInfo.basicMoneyTime}" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td>服务费回款金额</td>
                    <td>${casePersonalCwInfo.returnMoney}</td>
                    <td>服务费回款日期</td>
                    <td><fmt:formatDate value="${casePersonalCwInfo.returnMoneyTime}" pattern="yyyy-MM-dd"/></td>
                    <td>回款合计</td>
                    <td>${casePersonalCwInfo.returnTotalMoney}</td>
                </tr>
                </tbody>
            </table>


            <div class="table-title">成本&支出</div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>诉讼费金额</td>
                    <td>${casePersonalCwInfo.litigationMoney}</td>
                    <td>诉讼费支付日期</td>
                    <td><fmt:formatDate value="${casePersonalCwInfo.litigationTime}" pattern="yyyy-MM-dd"/></td>
                    <td>佣金金额</td>
                    <td>${casePersonalCwInfo.commissionMoney}</td>
                </tr>
                <tr>
                    <td>佣金支付日期</td>
                    <td><fmt:formatDate value="${casePersonalCwInfo.commissionTime}" pattern="yyyy-MM-dd"/></td>
                    <td>维护费金额</td>
                    <td>${casePersonalCwInfo.maintainMoney}</td>
                    <td>维护费支付日期</td>
                    <td><fmt:formatDate value="${casePersonalCwInfo.maintainTime}" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td>利息支出</td>
                    <td>${casePersonalCwInfo.realInterestMoney}</td>
                    <%--<td>利息支出日期</td>--%>
                    <%--<td>.....................</td>--%>
                    <td>其他支出</td>
                    <td>${casePersonalCwInfo.otherCostMoney}</td>
                    <td>其他支出日期</td>
                    <td><fmt:formatDate value="${casePersonalCwInfo.otherCostTime}" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td>合计</td>
                    <td colspan="5">${casePersonalCwInfo.costTotle}</td>
                </tr>
                </tbody>
            </table>

            <c:if test="${casePersonalCwInfo.isLoan == 1}">
                <div class="table-title">医疗费垫付</div>
                <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr>
                        <td>垫付金额</td>
                        <td>${casePersonalCwInfo.realInterestMoney}</td>
                        <td>垫付日期</td>
                        <td><fmt:formatDate value="${casePersonalCwInfo.loanTime}" pattern="yyyy-MM-dd"/></td>
                        <td>回款金额</td>
                        <td>${casePersonalCwInfo.reLoanMoney}</td>
                    </tr>
                    <tr>
                        <td>回款日期</td>
                        <td colspan="5"><fmt:formatDate value="${casePersonalCwInfo.reLoanTime}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                    </tbody>
                </table>
            </c:if>

        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </div>
</div>

<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script type="text/javascript">
    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

    /**
     * 关闭dialog
     */
    function closeDialog(){
        var closeBtn = $("#diglog_close_btn");
        if(closeBtn.size() == 0){
            closeBtn = $("#diglog_close_btn",window.parent.document);
        }
        closeBtn.click();
    }

</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>