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
    <form id="editForm" role="form" action="${ctx}/baseSurvey/operate" method="post">
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <input type="hidden" name="btnCode" value="${btnCode}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                <tr>
                    <th width="30%" class="active">交易渠道</th>
                    <td width="70%">
                        <select name="paySource" id ="paySource" class="form-control" required="required">
                            <option value="1" >第三方支付</option>
                            <option value="2" >福优</option>
                            <option value="3" >微信企业付款</option>
                            <option value="4" >乐凡企业打款</option>
                        </select>
                    </td>
                </tr>
                    <c:if test="${btnCode == 1300}">
                        <th class="active"><strong class="necessary">*</strong>提现凭证</th>
                        <td>
                            <input required id="adPic" type="hidden" name="unlineImg">
                            <img id="infImg" width="80" height="80">
                            <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/sftp/survey/uploadSftp?modelType=cash">
                        </td>
                    </c:if>
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
    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

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

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
</script>
</body>
</html>