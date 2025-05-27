<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>管理佣金指标和提成比例数据列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>管理佣金指标和提成比例数据列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/managerComrateInfo/managerComrateInfoList" method="post">
                   <%--<div class="form-group">--%>
                       <%--职位名称:<input name="positionName" type="text" value="${positionName}" class="form-control">--%>
                   <%--</div>--%>
                    <div class="btn-group">
                        <%--&nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;--%>
                        &nbsp; &nbsp;<button onclick="managerComrateInfoAdd()" type="button" class="btn btn-default">添加职位数据</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">1级管理佣金人均指标</th>
                <th width="150">1级管理佣金提成比例</th>
                <th width="150">2级管理佣金人均指标</th>
                <th width="150">2级管理佣金提成比例</th>
                <th width="150">3级管理佣金人均指标</th>
                <th width="150">3级管理佣金提成比例</th>
                <th width="150">4级管理佣金人均指标</th>
                <th width="150">4级管理佣金提成比例</th>
                <th width="150">5级管理佣金人均指标</th>
                <th width="150">5级管理佣金提成比例</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.onePerQuota}</td>
                    <td>${item.onePerComrate}</td>
                    <td>${item.twoPerQuota}</td>
                    <td>${item.twoPerComrate}</td>
                    <td>${item.threePerQuota}</td>
                    <td>${item.threePerComrate}</td>
                    <td>${item.fourPerQuota}</td>
                    <td>${item.fourPerComrate}</td>
                    <td>${item.fivePerQuota}</td>
                    <td>${item.fivePerComrate}</td>
                    <td><a href="javascript:managerComrateInfoEdit('${item.id}');">编辑</a>
                        <a href="javascript:managerComrateInfoDelete('${item.id}');">删除</a>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/managerComrateInfo/managerComrateInfo" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var managerComrateInfoEdit = function(id){
        openDialog({
            frame:true,
            title:"修改数据",
            height:500,
            width:800,
            url:"${ctx}/managerComrateInfo/managerComrateInfoEdit?id="+id
        });
    }

    var managerComrateInfoAdd = function(){
        openDialog({
            frame:true,
            title:"添加数据",
            height:500,
            width:800,
            url:"${ctx}/managerComrateInfo/managerComrateInfoAdd"
        });
    }

    function managerComrateInfoDelete(id){
        ajaxSubmit("${ctx}/managerComrateInfo/managerComrateInfoDelete",{"id":id},reload,"删除成功！","确认删除吗？");
    }
</script>
</body>
</html>
