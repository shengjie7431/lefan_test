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
    <form id="editForm" role="form" action="${ctx}/staff/update" method="post">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <input type="hidden" id="id" name="id" value="${staffPaySlip.id}">
        <input type="hidden" id="lastWorkTime" name="lastWorkTime" value="${lastWorkTime}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">月份</th>
                    <td width="70%">
                        <input id="workTime" name="workTime" type="text" class="form-control" required="required" style="cursor: pointer"
                               value="${staffPaySlip.workTime}" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit"  name="btnUpload"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
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
        $("[name=btnUpload]").attr("disabled","disabled");
        ajaxFormSubmit(this,reloadParent,null,null,null);
        setTimeout(function(){
            $("[name=btnUpload]").removeAttr("disabled");
        },3000)
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

    gainWorkTime();
    function gainWorkTime(){
        var id = $("#id").val();
        if(id == null || id == ''){
            var lastWorkTime = $("#lastWorkTime").val();
            if(lastWorkTime != null && lastWorkTime!=''){
                $("#workTime").val(lastWorkTime);
            }else {
                var newDate = new Date();
                var _year = newDate.getFullYear(),
                        _month = newDate.getMonth() + 1
                $("#workTime").val(_year + '-0' + _month);
            }
        }
    }
</script>
</body>
</html>