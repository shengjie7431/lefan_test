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
        table.spec-info tbody tr td:nth-of-type(2n + 1) {
            width: 10%;
        }
        table.spec-info tbody tr td:nth-of-type(2n) {
            width: 20%;
        }
        table.spec-info tbody tr td:nth-of-type(6n) {
            width: 30%;
        }
    </style>
</head>
<body>

<div class="main">

<div class="main-boy">
    <div class="table-title">提现信息</div>
    <div>
        <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
            <tbody>
            <tr>
                <td>提现名称</td>
                <td><div class="ellipsis" title="${info.surveyCashInfo.cashInfoName}">
                    ${info.surveyCashInfo.cashInfoName}
                </div>
                </td>
                <td>提现单号</td>
                <td>${info.surveyCashInfo.cashInfoCode}</td>
                <td>提现金额</td>
                <td>${info.surveyCashInfo.caseAmount}</td>
            </tr>
            <tr>
                <td>手续费比例</td>
                <td>${info.surveyCashInfo.feeRatio}%</td>
                <td>手续费金额</td>
                <td>${info.surveyCashInfo.feeAmount}</td>
                <td>实际到账金额</td>
                <td>${info.surveyCashInfo.realAmount}</td>
            </tr>

            </tbody>
        </table>
    </div>
    <br>

    <div class="table-title">银行卡信息</div>
    <div>
        <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
            <tbody>
            <tr>
                <td>银行卡号</td>
                <td>${info.surveyBankCard.cardNo}</td>
                <td>开户行</td>
                <td>${info.surveyBankCard.bankName}</td>
                <td>开户支行</td>
                <td>${info.surveyBankCard.bankBranchName}</td>
            </tr>
            <tr>
                <td>持卡人名称</td>
                <td>${info.surveyBankCard.holderName}</td>
                <td>银行卡图片</td>
                <td colspan="3"><img src="${info.surveyBankCard.cardImg}" width="75px;" height="75px;" class="picToBig"></td>
            </tr>
            </tbody>
        </table>
    </div>
    <br>

    <br>
    <div class="table-title">提现明细</div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th width="150">调查编号</th>
            <th width="150">被调查人</th>
            <th width="100">联系电话</th>
            <th width="150">任务价格</th>
        </tr>
        </thead>
        <tbody class="class-list">
        <c:forEach items="${info.surveyCashInfoDetails}" var="item">
            <tr>
                <td>${item.surveyNo}</td>
                <td>${item.surveyPerson}</td>
                <td>${item.surveyPersonTel}</td>
                <td>${item.surveyTaskMoney}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
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