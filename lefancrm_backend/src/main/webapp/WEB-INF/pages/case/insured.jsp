<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/case/center/operate" method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <input type="hidden" id="id" name="id" value="${id}"/>
                <input type="hidden" id="btnCode" name="btnCode" value="${btnCode}"/>
                <tr>
                    <th width="30%" class="active">保证保险费用</th>
                    <td width="70%">
                        <input type="number" required="required" step="0.01" min="1" class="form-control" name="insuredAmount" value="${dto.amount}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认</button>
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

    function returnCallback(event,param){
        $(this).find(":submit").removeAttr("disabled");
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){
            alert('投保成功');
        }else{
            alert(apiRsp.msg);return;
        }
        reloadParent();
    }

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        $(this).find(":submit").attr("disabled","true");
        ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
        event.preventDefault();
    });
</script>
</body>
</html>