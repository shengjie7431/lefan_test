<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <script>

    </script>
</head>
<body>

<div class="main">
    <input type="hidden" name="id" value="${loanApplication.id}">
    <div class="title">
        <c:if test="${loanApplication.state == 1}">
            <button class="butList active" onclick="editLoanApplicationInfoState('${loanApplication.id}',2,${loanApplication.userId},'${loanApplication.userName}','${loanApplication.accidentCityName}')">审核通过</button>
            <button class="butList active" onclick="editLoanApplicationInfoReson('${loanApplication.id}')">驳回</button>
        </c:if>
        <c:if test="${loanApplication.isFined == 1}">
            <button class="butList defuelt" onclick="promotionOutlayView('${loanApplication.loanNo}')">支付记录</button>
        </c:if>
        <c:if test="${loanApplication.isFined == 0}">
            <button class="butList defuelt" onclick="caseCenterInfoFinedView('${loanApplication.loanNo}')">扣罚记录</button>
        </c:if>
        <c:if test="${loanApplication.isFined == null}">
            <button class="butList active" onclick="caseCenterInfoFinedAdd('${loanApplication.id}')">确认支付</button>
        </c:if>
        <c:if test="${loanApplication.state == 3}">
            <button class="butList defuelt" onclick="editLoanApplicationInfoState('${loanApplication.id}',2,${loanApplication.userId},'${loanApplication.userName}','${loanApplication.accidentCityName}')">审核通过</button>
        </c:if>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <c:if test="${loanApplication.isTestcase ==1}">
                    <tr>
                        <td colspan="6" style="color: #FF4500">测试案件</td>
                    </tr>
                </c:if>
                <tr>
                    <td>用户姓名</td>
                    <td>${loanApplication.userName}</td>
                    <td>用户电话</td>
                    <td>${loanApplication.userPhone}</td>
                    <td>贷款申请编号</td>
                    <td>${loanApplication.loanNo}</td>
                </tr>
                <tr>
                    <td>事故发生地</td>
                    <td>${loanApplication.accidentAddress}</td>
                    <td>贷款金额</td>
                    <td>${loanApplication.loanMoney}</td>
                    <td>贷款用途</td>
                    <td>
                        <c:if test="${loanApplication.loanPurpose == 1}">医疗费用垫付</c:if>
                        <c:if test="${loanApplication.loanPurpose == 2}">生活康复支出</c:if>
                    </td>

                </tr>
                <tr>
                    <td>案件状态</td>
                    <td>
                        <c:if test="${loanApplication.state == 1}">待审核</c:if>
                        <c:if test="${loanApplication.state == 2}">审核通过</c:if>
                        <c:if test="${loanApplication.state == 3}">驳回</c:if>
                        <c:if test="${loanApplication.state == 4}">已受理</c:if>
                        <c:if test="${loanApplication.state == 5}">贷款评估</c:if>
                        <c:if test="${loanApplication.state == 6}">贷款面签</c:if>
                        <c:if test="${loanApplication.state == 7}">贷款审批</c:if>
                        <c:if test="${loanApplication.state == 8}">保证保险投保</c:if>
                        <c:if test="${loanApplication.state == 9}">贷款发放</c:if>
                        <c:if test="${loanApplication.state == 22}">结案</c:if>
                    </td>
                    <td>是否为交通事故</td>
                    <td>
                        <c:if test="${loanApplication.isTrafficAccident == 0}">否</c:if>
                        <c:if test="${loanApplication.isTrafficAccident == 1}">是</c:if>
                    </td>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${loanApplication.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                </tr>
                <tr>
                    <td>推广人姓名</td>
                    <td>${loanApplication.userPromotedName}</td>
                    <td>推广人电话</td>
                    <td colspan="3">${loanApplication.userPromotedPhone}</td>
                </tr>
                <tr>
                    <c:if test="${loanApplication.state == 3}">
                        <td>驳回原因</td>
                        <td colspan="5">${loanApplication.reson}</td>
                    </c:if>
                </tr>
                </tbody>
            </table>
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

    /**
     * 贷款申请审核通过
     */
    function editLoanApplicationInfoState(id,state,userId,userName,accidentCity){
        ajaxSubmit("${ctx}/caseApplication/editLoanApplicationInfoState",{"id":id,"state":state,"userId":userId,"userName":userName,"accidentCity":accidentCity},reload,"审核成功！","确认通过审核？","审核失败！");
    }

    var caseCenterInfoFinedAdd = function(id){
        openDialog({
            frame:true,
            title:"确认支付",
            height:350,
            width:850,
            url:"${ctx}/caseCenterInfoFined/caseCenterInfoFinedAdd?caseId="+id+"&caseType="+1
        });
    }

    var editLoanApplicationInfoReson = function(id){
        openDialog({
            frame:true,
            title:"驳回贷款申请",
            height:350,
            width:850,
            url:"${ctx}/caseApplication/editLoanApplicationInfoReson?id="+id
        });
    }

    var caseCenterInfoFinedView = function(caseNo){
        openDialog({
            frame:true,
            title:"扣罚详情",
            height:350,
            width:850,
            url:"${ctx}/caseCenterInfoFined/caseCenterInfoFinedView?caseNo="+caseNo
        });
    }

    var promotionOutlayView = function(caseNo){
        openDialog({
            frame:true,
            title:"支付详情",
            height:350,
            width:850,
            url:"${ctx}/promotionOutlay/promotionOutlayView?caseNo="+caseNo
        });
    }
</script>
</body>
</html>