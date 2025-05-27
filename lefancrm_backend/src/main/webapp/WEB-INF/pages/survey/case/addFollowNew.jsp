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
    <form id="editForm" role="form">
        <input type="hidden" name="id" value="${id}" />
        <input type="hidden" name="btnCode" value="${btnCode}" />
        <input type="hidden" name="surveyCno" value="${surveyCno}" />
        <input type="hidden" name="surveyOrgId" value="${surveyOrgId}" />
        <div class="form-group">
            <table class="table">
                <tbody>

                    <tr>
                        <th>跟踪内容</th>
                        <td colspan="3">
                            <textarea name="contents" required="required" class="form-control"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>下一次跟踪时间</th>
                        <td colspan="3">
                            <input name="nextFollowTime" type="text" class="form-control"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly>
                        </td>
                    </tr>

                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off" onclick="iformSubmit(this)"><span class="glyphicon glyphicon-ok"></span>确认</button>
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
    var flag = true;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    function iformSubmit(_this){

        if ($("textarea[name=contents]").val()=='')return false;
        $(_this).attr("disabled",true);

        $.ajax({
            type: "POST",
            url: "${ctx}/survey/case/addFollowOK",
            data: {
                type: '0610addFollow',
                id: $("input[name=id]").val(),
                btnCode: $("input[name=btnCode]").val(),
                surveyCno: $("input[name=surveyCno]").val(),
                surveyOrgId: $("input[name=surveyOrgId]").val(),
                contents :$("textarea[name=contents]").val(),
                nextFollowTime:$("input[name=nextFollowTime]").val()
            },
            dataType: "json",
            success: function (data) {
                closeDialog();
                parent.location.reload();
            }
        });
    }




</script>
</body>
</html>
