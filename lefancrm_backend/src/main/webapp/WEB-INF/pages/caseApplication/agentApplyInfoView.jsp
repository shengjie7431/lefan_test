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
    <input type="hidden" name="id" value="${agentApplyInfo.id}">
    <div class="title">
        <c:if test="${agentApplyInfo.state == 1}">
            <button class="butList active" onclick="editAgentApplyInfoState('${agentApplyInfo.id}',2)">审核通过</button>
            <button class="butList active" onclick="editAgentApplyInfoReson('${agentApplyInfo.id}')">驳回</button>
        </c:if>
        <c:if test="${agentApplyInfo.isFined == 1}">
            <button class="butList defuelt" onclick="promotionOutlayView('${agentApplyInfo.agentNo}')">支付记录</button>
        </c:if>
        <c:if test="${agentApplyInfo.isFined == 0}">
            <button class="butList defuelt" onclick="caseCenterInfoFinedView('${agentApplyInfo.agentNo}')">扣罚记录</button>
        </c:if>
        <c:if test="${agentApplyInfo.isFined == null}">
            <button class="butList active" onclick="caseCenterInfoFinedAdd('${agentApplyInfo.id}')">确认支付</button>
        </c:if>
        <c:if test="${agentApplyInfo.state == 3}">
            <button class="butList defuelt" onclick="editAgentApplyInfoState('${agentApplyInfo.id}',2)">审核通过</button>
        </c:if>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <c:if test="${agentApplyInfo.isTestcase ==1}">
                    <tr>
                        <td colspan="6" style="color: #FF4500">测试案件</td>
                    </tr>
                </c:if>
                <tr>
                    <td>用户姓名</td>
                    <td>${agentApplyInfo.userName}</td>
                    <td>用户电话</td>
                    <td>${agentApplyInfo.userPhone}</td>
                    <td>案件编号</td>
                    <td>${agentApplyInfo.agentNo}</td>
                </tr>
                <tr>
                    <td>所在省</td>
                    <td>${agentApplyInfo.accidentProvince}</td>
                    <td>所在市</td>
                    <td>${agentApplyInfo.accidentCity}</td>
                    <td>所在县</td>
                    <td>${agentApplyInfo.accidentDistrict}</td>
                </tr>
                <tr>
                    <td>事故发生地</td>
                    <td>${agentApplyInfo.accidentAddress}</td>
                    <td>代理类型</td>
                    <td>
                        <c:if test="${agentApplyInfo.agentType == 1}">交通事故索赔</c:if>
                        <c:if test="${agentApplyInfo.agentType == 2}">工伤事故索赔</c:if>
                        <c:if test="${agentApplyInfo.agentType == 3}">寿险索赔</c:if>
                        <c:if test="${agentApplyInfo.agentType == 4}">车辆损失索赔</c:if>
                        <c:if test="${agentApplyInfo.agentType == 5}">保险拒赔</c:if>
                        <c:if test="${agentApplyInfo.agentType == 6}">意外保险</c:if>
                        <c:if test="${agentApplyInfo.agentType == 7}">其他侵权</c:if>
                        <c:if test="${agentApplyInfo.agentType == 8}">援助服务</c:if>
                    </td>
                    <td>案件状态</td>
                    <td>
                        <c:if test="${agentApplyInfo.state == 1}">待审核</c:if>
                        <c:if test="${agentApplyInfo.state == 2}">审核通过</c:if>
                        <c:if test="${agentApplyInfo.state == 3}">驳回</c:if>
                        <c:if test="${agentApplyInfo.state == 4}">已受理</c:if>
                        <c:if test="${agentApplyInfo.state == 10}">材料收集中</c:if>
                        <c:if test="${agentApplyInfo.state == 11}">诉前调解</c:if>
                        <c:if test="${agentApplyInfo.state == 12}">申请鉴定</c:if>
                        <c:if test="${agentApplyInfo.state == 13}">鉴定中</c:if>
                        <c:if test="${agentApplyInfo.state == 14}">待立案</c:if>
                        <c:if test="${agentApplyInfo.state == 15}">已立案</c:if>
                        <c:if test="${agentApplyInfo.state == 16}">开庭</c:if>
                        <c:if test="${agentApplyInfo.state == 17}">已调解/判决</c:if>
                        <c:if test="${agentApplyInfo.state == 18}">已上诉</c:if>
                        <c:if test="${agentApplyInfo.state == 19}">补充证据</c:if>
                        <c:if test="${agentApplyInfo.state == 20}">赔偿款已支付</c:if>
                        <c:if test="${agentApplyInfo.state == 21}">贷款已还款</c:if>
                        <c:if test="${agentApplyInfo.state == 22}">结案</c:if>
                    </td>
                </tr>
                <tr>
                    <td>创建人</td>
                    <td>${agentApplyInfo.createBy}</td>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${agentApplyInfo.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    <td>更新时间</td>
                    <td><fmt:formatDate value="${agentApplyInfo.updateTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                </tr>
                <tr>
                    <td>推广人姓名</td>
                    <td>${agentApplyInfo.userPromotedName}</td>
                    <td>推广人电话</td>
                    <td colspan="3">${agentApplyInfo.userPromotedPhone}</td>
                </tr>
                <tr>
                    <c:if test="${agentApplyInfo.state == 3}">
                        <td>驳回原因</td>
                        <td colspan="5">${agentApplyInfo.reson}</td>
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
     * 代理申请案件--审核通过
     */
    function editAgentApplyInfoState(id,state){
        ajaxSubmit("${ctx}/caseApplication/editAgentApplyInfoState",{"id":id,"state":state},reload,"审核成功！","确认通过审核？","审核失败！");
    }

    var caseCenterInfoFinedAdd = function(id){
        openDialog({
            frame:true,
            title:"确认支付",
            height:350,
            width:850,
            url:"${ctx}/caseCenterInfoFined/caseCenterInfoFinedAdd?caseId="+id+"&caseType="+2
        });
    }

    var editAgentApplyInfoReson = function(id){
        openDialog({
            frame:true,
            title:"驳回代理申请",
            height:350,
            width:850,
            url:"${ctx}/caseApplication/editAgentApplyInfoReson?id="+id
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