<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>任务类型列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>任务类型数据</h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <form id="editForm" role="form" action="${ctx}/surveyFranchisee/choiceTaskInfo" method="post">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="80"><input type="checkbox" id="all" >全选</th>
                    <th width="100">任务类型名称</th>
                    <th width="150">描述</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${tasks}" var="item">
                    <tr>
                        <td>
                            <input type="checkbox" value="${item.id}" name="tasks">
                            <input type="hidden" value="${item.name}" id="name_${item.id}" />
                        </td>
                        <td>${item.name}</td>
                        <td>${item.remark}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="10">
                        <button id="batchOperateBtn" type="submit" onclick="return taskInfoOK()" class="btn btn-default">确定提交</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div><!--panel-info-->
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
    </div>
<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    $(document).ready(function(){
        $("#all").on('change',function(){
            $("input[name='tasks']").prop("checked",this.checked);
        })
    })

    $(function(){
        $("#editForm").bind('submit', function(event) {

        });
    });



    function taskInfoOK(){
        var includeTask = [];
        var includeTaskName = [];
        $("input:checkbox[name='tasks']:checked").each(function() { // 遍历name=safeCompanys的多选框
            includeTask.push($(this).val());
            includeTaskName.push($("#name_" + $(this).val()).val())
        });
        parent.$("#includeTaskName").val(includeTaskName);
        parent.$("#includeTask").val(includeTask);

        closeDialog();
    }
</script>
</body>
</html>
