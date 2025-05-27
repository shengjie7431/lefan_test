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
        <input type="hidden" name="id" value="${surveyTaskInfoContent.id}">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <input type="hidden" name="taskInfoId" value="${taskInfoId}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">任务子类名称</th>
                    <td width="70%">
                        <textarea id = "name" name="name" style="height: 150px;" class="form-control">${surveyTaskInfoContent.name}</textarea>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">排序</th>
                    <td width="70%">
                        <input type="text" id = "sort" name="sort" value="${surveyTaskInfoContent.sort}" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">颜色</th>
                    <td width="70%">
                        <input type="color" id = "color" name="color" value="${surveyTaskInfoContent.color}" class="form-control"/>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">是否展示</th>
                    <td width="70%">
                        <select name="showState" id="showState" class="form-control" required="required">
                            <option value="">请选择</option>
                            <option value="0" <c:if test="${surveyTaskInfoContent.showState == 0}">selected="selected" </c:if>>否</option>
                            <option value="1" <c:if test="${surveyTaskInfoContent.showState == 1}">selected="selected" </c:if>>是</option>
                        </select>
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
</script>
</body>
</html>