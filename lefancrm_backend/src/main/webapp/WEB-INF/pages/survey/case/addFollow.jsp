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
    <form id="editForm" role="form" action="${ctx}/survey/case/addFollowOK" method="post">
        <input type="hidden" name="id" value="${id}" />
        <input type="hidden" name="btnCode" value="${btnCode}" />
        <input type="hidden" name="surveyCno" value="${surveyCno}" />
        <div class="form-group">
            <table class="table">
                <tbody>
                <c:if test="${btnCode == 'follow'}">
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
                    <tr>
                        <th>跟踪附件</th>
                        <td>
                            <div>
                                <input type="hidden" id="files" name="files"  value="" />
                                <input type="hidden" id="uploadPaths" name="uploadPaths"  value="" />
                                <div id="uploadDiv" style="display: none"></div>
                            </div>
                            <div>
                                <input id="fileupload" class="form-control" type="file" name="file" multiple data-url="${ctx}/sftp/survey/uploadSftp?modelType=follow"><br>
                            </div>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == 'backreply'}">
                    <tr>
                        <th>沟通内容</th>
                        <td colspan="3">
                            <textarea rows="5" name="contents" required="required" class="form-control"></textarea>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>确认</button>
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
        $("#files").val(JSON.stringify(files));
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

    var onlinePreview = function(path){
        var last = path.lastIndexOf(".");
        var ext = path.substr(last + 1);
        if(ext == 'doc' || ext == 'docx' || ext == 'xls' || ext == 'xlsx'){
            path = "https://view.officeapps.live.com/op/view.aspx?src=" + path;
        }
        openDialog({
            frame:true,
            title:"预览",
            height:700,
            width:800,
            url:path
        });
    }

    //材料凭证
    var files = [];
    $('#fileupload').fileupload({
        done: function (e, data) {
            var value = "";
            var r  = data.result;
            var file = r.surveyFile;
            var value = "";
            if(file.fileExt == 'png' || file.fileExt == 'jpg' || file.fileExt == 'jpeg' || file.fileExt == 'gif' || file.fileExt == 'bmp' ){
                 value = "<img width='80' height='80' src='"+ file.filePath + "' />"
            }else{
                value = "<a onclick='onlinePreview(\""+file.filePath+"\")' target='_blank'>" + file.fileName + "</a>";
            }
            $("#uploadDiv").empty();
            $("#uploadDiv").append(value);
            files.push({
                filePath : file.filePath,
                fileRealName : file.fileName
            });
            $("#uploadPaths").val(value);
            $("#uploadDiv").show();
        }
    });
</script>
</body>
</html>
