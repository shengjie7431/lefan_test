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
    <input type="hidden" name="id" value="${dto.id}">
    <div class="title">
        <button class="butList defuelt" onclick="view(1,'${dto.caseId}','${dto.caseNo}','view')">查看报价</button>
        <button class="butList defuelt" onclick="view(2,'${dto.caseId}','${dto.caseNo}','view')">查看单证</button>
        <c:if test="${type == 1}">
            <c:if test="${dto.withholdState == 3}">
                <button class="butList active" onclick="agentPay('${dto.caseId}','${dto.id}');">再次代扣</button>
            </c:if>
        </c:if>
        <c:if test="${type == 2}">
            <c:if test="${dto.withholdState == 3}">
                <button class="butList active" onclick="agentPay('${dto.caseId}','${dto.id}');">再次代扣</button>
                <button class="butList active" onclick="editBank('${dto.caseId}','11082')">编辑银行卡</button>

            </c:if>
            <%-- 暂时不要转结案按钮--%>
            <%--<c:if test="${dto.showConvertCloseState == 'true'}">--%>
                <%--<button class="butList active" onclick="okApply(3,'${dto.id}','${dto.caseNo}');">转结案</button>--%>
            <%--</c:if>--%>
        </c:if>
        <c:if test="${type == 3}">
            <button class="butList active" onclick="pass('${dto.id}','${dto.caseNo}','${dto.applyType}');">确认到账</button>
            <button class="butList active" onclick="searchArrivalInfo('${dto.id}','${dto.caseNo}'),'${dto.applyType}'">历史到账</button>
        </c:if>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>案件编号</td>
                    <td>${dto.caseNo}</td>
                    <td>案件标题</td>
                    <td>${dto.caseTitle}</td>
                    <td>代扣金额</td>
                    <td>${dto.withholdMoney}</td>
                </tr>
                <tr>
                    <td>代扣时间</td>
                    <td><fmt:formatDate value="${dto.withholdTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>代扣状态</td>
                    <td>
                        <c:if test="${dto.withholdState == 0}">申请代扣</c:if>
                        <c:if test="${dto.withholdState == 1}">代扣中</c:if>
                        <c:if test="${dto.withholdState == 2}">代扣成功</c:if>
                        <c:if test="${dto.withholdState == 3}">代扣失败</c:if>
                    </td>
                    <td>代扣类型</td>
                    <td>
                        <c:if test="${dto.withholdType == 1}">现金</c:if>
                        <c:if test="${dto.withholdType == 3}">转账</c:if>
                        <c:if test="${dto.withholdType == 2}">苏宁代扣</c:if>
                        <c:if test="${dto.withholdType == 4}">平安代扣</c:if>
                    </td>
                </tr>
                <tr>
                    <td>到账状态</td>
                    <td>
                        <c:if test="${dto.accountState != 1}">
                            未到账
                        </c:if>
                        <c:if test="${dto.accountState ==1}">
                            已到账
                        </c:if>
                    </td>
                    <td>代扣申请类型</td>
                    <td>
                        <c:if test="${dto.applyType == 0}">放款代扣</c:if>
                        <c:if test="${dto.applyType == 1}">还款代扣</c:if>
                        <c:if test="${dto.applyType == 2}">紧急代扣</c:if>
                        <c:if test="${dto.applyType == 3}">预收定金</c:if>
                        <c:if test="${dto.applyType == 4}">还款代扣(服务费)</c:if>
                        <c:if test="${dto.applyType == 5}">司法评估</c:if>
                        <c:if test="${dto.applyType == 6}">司法评估补费</c:if>
                    </td>
                    <td>订单号</td>
                    <td>${dto.orderCode}</td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td colspan="5">${dto.remark}</td>
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

    function editBank(caseId,btnCode){
        openDialog({
            frame:true,
            title:"编辑银行卡",
            height:400,
            width:800,
            url:"${ctx}/case/center/editBankCardInfo?caseId="+caseId+"&btnCode="+btnCode
        });
    }

    function agentPay(caseId,applyId){
        openDialog({
            frame:true,
            title:"再次代扣",
            height:700,
            width:600,
            url:"${ctx}/case/center/pass?id="+caseId+"&btnCode=11083&applyId="+applyId
        });
    }

    /**
     * 审核通过
     */
    function editState(id,state,userId){
        ajaxSubmit("${ctx}/agent/editState",{"id":id,"state":state,"userId":userId},reload,"审核成功！","确认通过审核？","审核失败！");
    }

    var caseCenterInfoAllotAdd = function(caseId,gradationState){
        openDialog({
            frame:true,
            title:"案件审核详情",
            height:500,
            width:600,
            url:"${ctx}/caseCenterInfoAllot/caseCenterInfoAllotAdd?caseId="+caseId+"&gradationState="+gradationState
        });
    }

    /*
    *   确认到账
    * */
    var pass = function(id,caseNo,applyType) {
        openDialog({
            frame:true,
            title:"到账确认",
            height:350,
            width:600,
            url:"${ctx}/suning/withholdApply/pass?passType=passCommission&id="+id+"&caseNo="+caseNo+"&applyType="+applyType//弹出到账界面
        });
    }

    /*
    * 历史到账
    * */
    var searchArrivalInfo = function(id,caseNo,applyType) {
        openDialog({
            frame: true,
            title: "历史到账",
            height: 500,
            width: 850,
            url: "${ctx}/suning/arrivalInfo/list?op=view&id=" + id + "&caseNo=" + caseNo + "&applyType=" + applyType//弹出到账详情
        });
    }

    /**
     * 转结案
     */
    function okApply(type,id,caseNo){
        ajaxSubmit("${ctx}/suning/withholdApply/okApply",{"type":type,"id":id,"caseNo":caseNo},reload,"转结案成功！","确认转结案？","转结案失败！");
    }

    function view(type,caseId,caseNo){
        if(type == 1){
            viewEstimateInquiry(caseId)
        }else if(type ==2){
            selectFileMid(caseNo);
        }
    }
    function viewEstimateInquiry(caseId){
        openDialog({
            frame:true,
            title:"查看报价",
            height:400,
            width:600,
            url:"${ctx}/case/center/viewEstimateInquiry?id="+caseId
        });
    }
    function selectFileMid(caseNo){
        openDialog({
            frame:true,
            title:"查看单证",
            height:400,
            width:800,
            url:"${ctx}/case/selectFileMid?caseNo="+caseNo
        });
    }
</script>
</body>
</html>