<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
    <script>


    </script>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/baseSurvey/operate" method="post">
        <input type="hidden" name="surveyOrgId" value="${franchiseeId}">
        <input type="hidden" name="type" value="${type}">
        <input type="hidden" name="surveyCode" value="updAreaInformation">
        <input type="hidden" name="id" value="${surveyOrgAreaDto.id}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">片区名称</th>
                    <td width="70%">
                        <input type="text" id = "surveyAreaName" name="surveyAreaName" value="${surveyOrgAreaDto.surveyAreaName}"  class="form-control">
                    </td>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
<script type="text/javascript">
    $("#editForm").bind('submit', function(event) {

        var surveyAreaName=$("#surveyAreaName").val();
        surveyAreaName=surveyAreaName.trim();
        if(surveyAreaName == '' ){
            alert("片区名称不能为空!")
            return false;
        }
        ajaxFormSubmit(this,function(v,e,p){
            console.log(e);
            alert(e.data.msg);
            closeDialog();
        },null,null,function(v,e,p) {
            console.log(e);
            if(e.data.results != null){
                alert(e.data.results);
            }else{
                alert(e.data.msg);
            }
        });
        event.preventDefault();
    });
</script>
</body>
</html>