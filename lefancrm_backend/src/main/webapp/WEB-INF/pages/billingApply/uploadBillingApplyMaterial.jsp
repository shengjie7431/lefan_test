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
    <form id="editForm" role="form" action="${ctx}/billingApply/saveMaterial" method="post">
        <input type="hidden" name="id" value="${id}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active">材料凭证</th>
                    <td>
                        <%--<input required id="adPic" type="hidden" name="img">--%>
                        <%--<img id="infImg" width="80" height="80">--%>
                        <%--<input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage?moduleName=ticket/${caseNo}">--%>
                        <div>

                            <input type="hidden" required="required" id="img" name="img" value="" />
                            <div id="imgDiv" style="display: none"></div>
                        </div>
                        <div>
                            <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadFile/"><br>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </form>
</div>

<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>


<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>


<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script type="text/javascript">
    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    var value = "";
    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;

            var ext = pathImg.substring(pathImg.lastIndexOf(".") + 1, pathImg.length);
//                $("#imgDiv").append("<img width='80' height='80' src='http://ddrapi.shlefan.com/sftp/files/"+pathImg+"' />");
            if(ext == "txt"){
                $("#imgDiv").append("<img width='80' height='80' src='../img/txt.png' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }else if(ext == "docx" || ext == "doc"){
                $("#imgDiv").append("<img width='80' height='80' src='../img/word.png' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }else if(ext == "xls" || ext == "xlsx"){
                $("#imgDiv").append("<img width='80' height='80' src='../img/excel.png' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }else if(ext == "pdf"){
                $("#imgDiv").append("<img width='80' height='80' src='../img/pdf.jpg' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }else {
                $("#imgDiv").append("<img width='80' height='80' src='http://ddrapi.shlefan.com/sftp/files/" + pathImg + "' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }
                $("#img").val(value);
                $("#imgDiv").show();



        }
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

</script>
</body>
</html>