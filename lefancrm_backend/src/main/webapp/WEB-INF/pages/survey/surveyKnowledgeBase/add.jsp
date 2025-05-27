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
        <input type="hidden" name="id" value="${surveyKnowledgeBase.id}">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">标题</th>
                    <td width="70%">
                       <input type="text" id = "name" name="title"class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">摘要</th>
                    <td width="70%">
                        <input type="text" id = "remark" name="remark" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">类别</th>
                    <td width="70%">
                        <select name="knowledgeTypeId" id="knowledgeTypeId" class="form-control" required="required">
                            <c:forEach items="${typeList}" var="item">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <input type="hidden" id="knowledgeTypeName" name="knowledgeTypeName" value="">
                </tr>
                <tr>
                    <th width="30%" class="active">模板</th>
                    <td width="70%">
                        <select name="isWord" id="isWord" class="form-control" required="required" onclick="isWords()">
                            <option value="">请选择</option>
                            <option value="1">否</option>
                            <option value="2">是</option>
                        </select>
                    </td>
                </tr>

                <tr id="wordDiv" style="display: none">
                    <th width="30%" class="active">上传word</th>
                    <td>
                        <input type="hidden" id="fileImg" name="fileImg" value="" />
                        <div id="uploadDiv" style="display: none"></div>
                        <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/sftp/survey/uploadSftp?modelType=knowledge">
                    </td>
                </tr>
                <tr id="contentDiv" style="display: none">
                    <td width="30%">内容</th>
                    <td width="70%">
                        <textarea class="form-control" name="content" id="content" style="width: 250px;height: 400px;" ></textarea>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit" onclick="goBack()" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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
        $("#content").text(editor1.html());
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

    /**
     * 是否实模板
     */
    function isWords(){
        var isWord = $("#isWord").val();
        if(isWord == 1){
            $("#wordDiv").hide();
            $("#contentDiv").show();
            document.getElementById("infImg").value="";
        }else if(isWord == 2){
            $("#wordDiv").show();
            $("#contentDiv").hide();
            document.getElementById("content").value="";
        }
    }

    function goBack(){
        var val = $("#knowledgeTypeId").find("option:selected").text();
        $("#knowledgeTypeName").val(val);
    }

    //材料凭证
    var value = "";
    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var file=r.surveyFile;

            var value = file.filePath;
            $("#uploadDiv").empty();
            $("#uploadDiv").append(file.fileName);
            $("#fileImg").val(value);
            $("#uploadDiv").show();
        }
    });
</script>
</body>
</html>