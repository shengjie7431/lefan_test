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
    <form id="editForm" role="form" action="${ctx}/promoted/userPromotedInfoAddOrUpdate" method="post">
        <div class="form-group">
            <table class="table">
                <input type="hidden" value="${state}" name="state">
                <input type="hidden" value="${id}" name="id">
                <input type="hidden" value="${promotedType}" name="promotedType">
                <input type="hidden" value="${isOpen}" name="isOpen" id="isOpen">
                申请的是保代公司，是否可向下发展下级<span style="color: #ff0000">(PS:如果是一级保代公司请选择开启)</span>：
                <div class="btn-group" data-toggle="buttons">
                    <button type="button" class="btn btn-default" onclick="backTo(1)" id="bt1">开启</button>
                    <button type="button" class="btn btn-warning" onclick="backTo(0)" id="bt2">关闭</button>
                </div>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." ><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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

    function backTo(type){
        if(type == 1){
            document.getElementById("bt1").className = "btn btn-success";
            document.getElementById("bt2").className = "btn btn-default";
        }else if(type == 0){
            document.getElementById("bt2").className = "btn btn-warning";
            document.getElementById("bt1").className = "btn btn-default";
        }
        $('#isOpen').val(type);
    }
</script>
</body>
</html>