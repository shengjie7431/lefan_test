<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/caseCenterInfoAllot/caseCenterInfoAllotSave" method="post">
        <input type="hidden" name="caseId" value="${caseId}">
        <input type="hidden" name="bsRoleId" value="${bsRoleId}">
        <input type="hidden" name="type" value="${type}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">分派人员类型:</th>
                    <td width="70%">
                        <c:if test="${gradationState == 1}"> 业务员</c:if>
                        <c:if test="${gradationState == 2}"> 评估员</c:if>
                        <c:if test="${gradationState == 3}"> 索赔员</c:if>
                        <c:if test="${gradationState == 6}"> 诉讼员</c:if>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">分派人:</th>
                    <td width="70%">
                        <select name="userId" class="form-control">
                            <option value="">全部</option>
                            <c:forEach items="${apiRsp.results}" var="item">
                                <option value="${item.userId}" <c:if test="${userPoLevel.userId == item.userId}">selected="selected" </c:if>>${item.userName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                </tbody>

            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<%--<script src="${ctx}/js/jquery.pin.js" type="text/javascript"></script>--%>
<%--<script src="${ctx}/js/jquery.tableDnD.js" type="text/javascript"></script>--%>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>


<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script type="text/javascript">
    $("#editForm").bind('submit', function(event) {
//        $("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
</script>
</body>
</html>