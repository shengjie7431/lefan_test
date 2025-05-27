<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>领域列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>领域数据</h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <form id="editForm" role="form" action="${ctx}/surveyFranchisee/choiceBusinessType" method="post">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="80"><input type="checkbox" id="all" >全选</th>
                    <th width="100">领域名称</th>
                    <th width="150">描述</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${buses}" var="item">
                    <tr>
                        <td>
                            <input type="checkbox" value="${item.id}" name="buss">
                            <input type="hidden" value="${item.name}" id="name_${item.id}" />
                        </td>
                        <td>${item.name}</td>
                        <td>${item.remark}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="10">
                        <button id="batchOperateBtn" type="submit" onclick="return businessOK()" class="btn btn-default">确定提交</button>
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
            <jsp:param name="requestUrl" value="${ctx}/surveyFranchisee/selectInvestigator?btnCode=${btnCode}&realName=${realName}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    $(document).ready(function(){
        $("#all").on('change',function(){
            $("input[name='buss']").prop("checked",this.checked);
        })
    })

    $(function(){
        $("#editForm").bind('submit', function(event) {

        });
    });



    function businessOK(){
        var includeBus = [];
        var includeBusName = [];
        $("input:checkbox[name='buss']:checked").each(function() { // 遍历name=safeCompanys的多选框
            includeBus.push($(this).val());
            includeBusName.push($("#name_" + $(this).val()).val())
        });
        parent.$("#includeBusName").val(includeBusName);
        parent.$("#includeBus").val(includeBus);

//        parent.$("#includeBusName").show();
//        parent.$("#includeBusName").append(includeBusName);
//        parent.$("#includeBus").append(includeBus);
        closeDialog();
    }
</script>
</body>
</html>
