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
    <style>
        .items{
            width: 502px;
            height: 252px;
            padding: 20px;
            padding-right: 0;
            text-align: center;
            border: 1px solid #66C8FF;
            margin: 20px auto;
        }
        .item{
            float: left;
            width: 100px;
            height: 40px;
            line-height: 40px;
            border:1px solid #66C8FF;
            color: #3ba9ff;
            font-size: 14px;
            text-align: center;
            margin-right: 20px;
            margin-bottom: 20px;
        }
        div.active{
            background-color: #66C8FF;
            color: #fff;
        }
        .uuu{
            text-align: center;
            height: 50px;
            line-height: 64px;
            font-size: 18px;
        }
    </style>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/case/center/caseFileMidOperate" method="post">
        <input type="hidden" id="code" name="code" value="${code}">
        <input type="hidden" id="files" name="files" value="${files}">
        <input type="hidden" id="caseId" name="caseId" value="${caseId}">
        <div class="form-group">
            <table class="table">
                <div class="uuu">
                您希望将选中的图片移送至
                </div>
                <div class="items">
                    <c:forEach items="${commonEnumDtos}" var="item">
                        <div class="item" onclick="choice('${item.enumCode}')" id="${item.enumCode}" name="enumCode">
                                ${item.enumName}
                        </div>
                        <input type="hidden" name="catalog" id="catalog" value="">
                    </c:forEach>
                </div>
            </table>
        </div>

        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" style="background-color: #66C8FF" data-loading-text="Loading..." autocomplete="off">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">取消</button>
        </div>
    </form>
</div>


<div id="dial
ogId"></div>
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

    function choice(enumCode) {
        for(var i = 1; i < ${commonEnumDtos.size()+1}; i++) {
            $("#"+i).attr("class","item");
        }
        $("#"+enumCode).addClass("active");
        $("#catalog").val(enumCode);
    }

</script>
</body>
</html>