<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>调查员级别列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>级别列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <form id="editForm" role="form" action="${ctx}/surveyProduct/choiceLevel" method="post">
            <input type="hidden" name="surveyCode" value="${surveyCode}">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="80"><input type="checkbox" id="all" >全选</th>
                    <th width="100">级别名称</th>
                    <th width="100">级别code</th>
                    <th width="150">级别描述</th>
                    <th width="150">成就点</th>
                    <th width="150">标的金额</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${apiRsp.results}" var="item">
                    <tr>
                        <td>
                            <input type="checkbox" value="${item.id}" name="levels">
                            <input type="hidden" value="${item.name}" id="name_${item.id}" />
                        </td>
                        <td>${item.name}</td>
                        <td>${item.code}</td>
                        <td>${item.remark}</td>
                        <td>${item.successPoint}</td>
                        <td>${item.amount}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="10">
                        <button id="batchOperateBtn" type="submit"  onclick="return levelOK()" class="btn btn-default">确定提交</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>


<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&name=${name}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    $(document).ready(function(){
        $("#all").on('change',function(){
            $("input[name='levels']").prop("checked",this.checked);
        })
    })

    $(function(){
        $("#editForm").bind('submit', function(event) {
        });

    });

    function levelOK(){
        var levelId = [];
        var levelName = [];
        $("input:checkbox[name='levels']:checked").each(function() { // 遍历name=levels的多选框
            levelId.push($(this).val());
            levelName.push($("#name_" + $(this).val()).val())
        });
        parent.$("#levelName").val(levelName);
        parent.$("#levelId").val(levelId);
        closeDialog();
    }

</script>
</body>
</html>
