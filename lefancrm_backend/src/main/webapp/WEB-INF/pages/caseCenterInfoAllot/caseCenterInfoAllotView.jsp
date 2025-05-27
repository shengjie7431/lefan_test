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


        <button class="butList active" onclick="caseCenterInfoAllotAdd('${caseCenterInfo.id}','${caseCenterInfo.gradationState}');">案件分配</button>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <c:if test="${caseCenterInfo.isTestcase ==1}">
                    <tr>
                        <td colspan="6" style="color: #FF4500">测试案件</td>
                    </tr>
                </c:if>
                <tr>
                    <td>姓名</td>
                    <td>${caseCenterInfo.caseName}</td>
                    <td>案件编号</td>
                    <td>${caseCenterInfo.caseNo}</td>
                    <td>案件类型</td>
                    <td>
                        <c:if test="${caseCenterInfo.type == 1}">贷款申请</c:if>
                        <c:if test="${caseCenterInfo.type == 2}">代理申请</c:if>
                        <c:if test="${caseCenterInfo.type == 3}">伤残预估</c:if>
                        <c:if test="${caseCenterInfo.type == 4}">其他</c:if>
                    </td>
                </tr>
                <tr>
                    <td>案件标题</td>
                    <td>${caseCenterInfo.caseTitle}</td>
                    <td>用户电话</td>
                    <td>${caseCenterInfo.caseTel}</td>
                    <td>分配人名称</td>
                    <td>${caseCenterInfo.orgUserName}</td>
                </tr>
                <tr>
                    <td>业务员</td>
                    <td>${caseCenterInfo.operatorName}</td>
                    <td>当前经办人</td>
                    <td>${caseCenterInfo.orgUserName}</td>
                    <td>评估员</td>
                    <td>${caseCenterInfo.assessName}</td>
                </tr>
                <tr>
                    <td>索赔员</td>
                    <td>${caseCenterInfo.claimantName}</td>
                    <td>保险公司</td>
                    <td>${caseCenterInfo.insuranceCompany}</td>
                    <td>出险时间</td>
                    <td><fmt:formatDate value="${caseCenterInfo.dangerTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
                <tr>
                    <td>案件阶段</td>
                    <td>
                        <c:if test="${caseCenterInfo.gradationState == 1}"> 洽谈阶段</c:if>
                        <c:if test="${caseCenterInfo.gradationState == 2}"> 评估阶段</c:if>
                        <c:if test="${caseCenterInfo.gradationState == 3}"> 索赔阶段</c:if>
                        <c:if test="${caseCenterInfo.gradationState == 4}"> 结案</c:if>
                        <c:if test="${caseCenterInfo.gradationState == 5}"> 风控部门审核阶段</c:if>
                        <c:if test="${caseCenterInfo.gradationState == 6}"> 诉讼阶段</c:if>
                    </td>
                    <td>案件状态</td>
                    <td>${caseCenterInfo.caseStateStr}</td>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${caseCenterInfo.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
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
            width:800,
            url:"${ctx}/caseCenterInfoAllot/caseCenterInfoAllotAdd?caseId="+caseId+"&gradationState="+gradationState
        });
    }
</script>
</body>
</html>