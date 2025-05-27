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
        <input type="hidden" name="corporationId" value="${corporationId}">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th width="100">名称</th>
                    <c:if test="${type == 1 }">
                        <th width="100">操作</th>
                    </c:if>
                </tr>
            </thead>
            <tbody class="class-list">
                <c:forEach items="${myList}" var="item">
                    <c:if test="${type == 1 }">
                        <tr>
                            <td>
                                ${item.billingEnumName}
                            </td>
                            <td>
                                <a href="javascript:addEnum('${item.billingEnumId}','${corporationId}','applyCorporationEnum',1100);">设置项目</a>
                                <a href="javascript:addEnum('${item.billingEnumId}','${corporationId}','applyCorporationEnum',1200);">名下项目</a>
                            </td>
                        <tr>
                    </c:if>
                    <c:if test="${type == 2 }">
                        <tr>
                            <td>
                                ${item.billingItemName}
                            </td>
                        </tr>
                    </c:if>
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