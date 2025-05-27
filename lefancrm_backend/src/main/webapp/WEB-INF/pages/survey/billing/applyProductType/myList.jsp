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
<div class="main administrator">
    <div class="main-top">
        <h3>列表</h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th width="100">名称</th>
                </tr>
            </thead>
            <tbody class="class-list">
                <c:forEach items="${myList}" var="item">
                    <tr>
                        <td>
                            ${item.orgName}
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </div>
</div>


</div><!--main end-->

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
    var addEnum = function(billingEnumId,applyCorporationId,surveyCode,btnCode){
        openDialog({
            frame:true,
            title:"项目数据",
            height:650,
            width:1000,
            url:"${ctx}/baseSurvey/popup?surveyCode="+surveyCode+"&applyCorporationId="+applyCorporationId+"&btnCode="+btnCode+"&billingEnumId="+billingEnumId
        });
    }
</script>
</body>
</html>