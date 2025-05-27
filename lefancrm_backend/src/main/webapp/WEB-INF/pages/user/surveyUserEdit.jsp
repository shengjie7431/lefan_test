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
    <style>
        .myColSm3{
            width: 30%;!important;
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="container">
   <form id="editForm" role="form" class="form-horizontal" action="${ctx}/baseSurvey/update" method="post">
       <input type="hidden" name="userId" value="${userInfo.userId}">
       <input type="hidden" name="surveyCode" value="${surveyCode}">
        <div class="form-group">
            <table class="table" >
                <tbody>
                <tr>
                    <th width="30%" class="active">用户姓名</th>
                    <td width="70%">
                        <input type="text" id = "userName" name="userName" value="${userInfo.userName}" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">昵称</th>
                    <td width="70%">
                        <input type="text" id = "nickName" name="nickName" value="${userInfo.nickName}" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">用户电话</th>
                    <td width="70%">
                        <input type="text" id = "userTel" name="userTel"  value="${userInfo.userTel}" class="form-control"  readonly="readonly">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
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