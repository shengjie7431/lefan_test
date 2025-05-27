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
<form id="editForm" role="form" action="${ctx}/law/operate" method="post">
    <input type="hidden" name="id" value="${id}" />
    <input type="hidden" name="btnCode" value="${btnCode}" />
    <input type="hidden" name="type" value="${type}"> <!-- 文件类型 -->
    <div class="form-group">
        <table class="table">
            <tbody>
                <tr>
                    <%--<c:if test="${btnCode == ''}"></c:if>--%>
                    <th width="20%" class="active">资料</th>
                    <td>
                        <div>
                            <input type="hidden" id="uploadPaths" name="uploadPaths" value="" />
                            <div id="uploadDiv" style="display: none"></div>
                        </div>
                        <div>
                            <input id="lawFileupload" type="file" name="file" multiple data-url="${ctx}/uploadFile/"><br>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="modal-footer">
        <button type="submit" onclick="goBack()" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>确认提交</button>
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


    //材料凭证
    var value = "";
    $('#lawFileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            var ext = pathImg.substring(pathImg.lastIndexOf(".") + 1, pathImg.length);
            if(ext == "txt"){
                $("#uploadDiv").append("<img width='80' height='80' src='../img/txt.png' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }else if(ext == "docx" || ext == "doc"){
                $("#uploadDiv").append("<img width='80' height='80' src='../img/word.png' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }else if(ext == "xls" || ext == "xlsx"){
                $("#uploadDiv").append("<img width='80' height='80' src='../img/excel.png' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }else if(ext == "pdf"){
                $("#uploadDiv").append("<img width='80' height='80' src='../img/pdf.jpg' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }else{
                $("#uploadDiv").append("<img width='80' height='80' src='http://ddrapi.shlefan.com/sftp/files/"+pathImg+"' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }
            $("#uploadPaths").val(value);
            $("#uploadDiv").show();

        }
    });
</script>
</body>
</html>
