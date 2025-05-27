<%--
  Created by IntelliJ IDEA.
  User: lixianfeng
  Date: 2018/10/18
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
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
<form id="editForm" role="form" action="${ctx}/infoSafeCompany/save" method="post">
    <input type="hidden" name="id" value="${infoSafeCompany.id}">
    <div class="form-group">
        <table class="table">
            <tbody>
                <tr>
                    <th width="20%" class="active">公司名称</th>
                    <td width="80%">
                        <input type="text" id="safeName" name="safeName" value="${infoSafeCompany.safeName}" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">联系人</th>
                    <td width="80%">
                        <input type="text" id="safeUser" name="safeUser" value="${infoSafeCompany.safeUser}" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">联系电话</th>
                    <td width="80%">
                        <input type="text" id="safeTel" name="safeTel" value="${infoSafeCompany.safeTel}" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认</button>
    </div>
</form>
</div>

<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script type="text/javascript">
    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

</script>
</body>
</html>
