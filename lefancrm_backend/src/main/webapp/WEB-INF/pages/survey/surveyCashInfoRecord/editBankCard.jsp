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
    <form id="editForm" role="form" action="${ctx}/baseSurvey/update" method="post">
        <input type="hidden" name="id" value="${surveyBankCard.id}">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">银行卡号</th>
                    <td width="70%">
                        <input type="text" id = "cardNo" name="cardNo" value="${surveyBankCard.cardNo}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">开户行</th>
                    <td width="70%">
                        <input type="text" id = "bankName" name="bankName" value="${surveyBankCard.bankName}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">开户支行</th>
                    <td width="70%">
                        <input type="text" id = "bankBranchName" name="bankBranchName" value="${surveyBankCard.bankBranchName}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">持卡人</th>
                    <td width="70%">
                        <input type="text" id = "holderName" name="holderName" value="${surveyBankCard.holderName}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">图片</th>
                    <td>
                        <%--<img src="${surveyBankCard.cardImg}" width="75;" height="75;" class="picToBig">--%>
                        <input required id="adPic" type="hidden" name="proFile">
                        <img src="${surveyBankCard.cardImg}" id="infImg" width="80" height="80">
                        <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/sftp/survey/uploadSftp?modelType=bankcard">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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

    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var file=r.surveyFile;
//            if (r.success == true){
            $("#infImg").attr("src",file.filePath);
            $("#adPic").val(file.filePath);
//            }else {
//                alert("上传失败，请重试111");
//            }
        }
    });
</script>
</body>
</html>