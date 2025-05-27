
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>



    </script>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="" method="post">
        <input type="hidden" name="id" value="${caseCenterInfo.id}">

        <a href="javascript:updCaseCenterInfoConfirmRepay('${caseCenterInfo.id}');">确认还款</a>
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">姓名</th>
                    <td width="70%">
                        ${caseCenterInfo.caseName}
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">案件编号</th>
                    <td width="70%">
                        ${caseCenterInfo.caseNo}
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">案件类型</th>
                    <td width="70%">
                        <c:if test="${caseCenterInfo.type == 1}">贷款申请</c:if>
                        <c:if test="${caseCenterInfo.type == 2}">代理申请</c:if>
                        <c:if test="${caseCenterInfo.type == 3}">伤残预估</c:if>
                        <c:if test="${caseCenterInfo.type == 4}">其他</c:if>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">案件阶段</th>
                    <td width="70%">
                        <c:if test="${caseCenterInfo.gradationState == 1}"> 洽谈阶段</c:if>
                        <c:if test="${caseCenterInfo.gradationState == 2}"> 评估阶段</c:if>
                        <c:if test="${caseCenterInfo.gradationState == 3}"> 索赔阶段</c:if>
                        <c:if test="${caseCenterInfo.gradationState == 4}"> 结案</c:if>
                        <c:if test="${caseCenterInfo.gradationState == 5}"> 风控部门审核阶段</c:if>
                        <c:if test="${caseCenterInfo.gradationState == 6}"> 诉讼阶段</c:if>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">用户电话</th>
                    <td width="70%">
                        ${caseCenterInfo.caseTel}
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">创建时间</th>
                    <td width="70%">
                        <fmt:formatDate value="${caseCenterInfo.createTime}" pattern="yyyy-MM-dd HH:mm"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </form>
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
     * 确认还款
     */
    function updCaseCenterInfoConfirmRepay(id){
        ajaxSubmit("${ctx}/case/updCaseCenterInfoConfirmRepay",{"id":id},reload,"确认成功！","确认通过还款？","确认失败！");
    }
</script>
</body>
</html>