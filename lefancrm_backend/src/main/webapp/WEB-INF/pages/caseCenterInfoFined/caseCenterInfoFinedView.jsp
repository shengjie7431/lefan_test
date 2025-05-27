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
    <input type="hidden" name="id" value="${caseCenterInfoFined.id}">

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>案件编号</td>
                    <td>${caseCenterInfoFined.caseNo}</td>
                    <td>扣罚类目</td>
                    <td>${caseCenterInfoFined.finedTypeName}</td>
                </tr>
                <tr>
                    <td>扣罚金额</td>
                    <td>${caseCenterInfoFined.finedMoney}</td>
                    <td>操作人</td>
                    <td>${caseCenterInfoFined.finedBy}</td>
                </tr>
                <tr>
                    <td>扣罚时间</td>
                    <td colspan="3"><fmt:formatDate value="${caseCenterInfoFined.finedTime}" pattern="yyyy-MM-dd HH:mm" /></td>
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

</script>
</body>
</html>