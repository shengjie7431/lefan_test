<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>角色列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script>

    </script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>角色列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                    <div class="form-group">
                        角色名:<input name="roleName" type="text" value="${roleName}" class="form-control">
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">角色名</th>
                <th width="150">角色描述</th>
                <th width="150">创建人</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.roleName}</td>
                    <td>${item.roleDesc}</td>
                    <td>${item.adminName}</td>
                    <td>${item.createTimes}</td>
                    <td>
                        <a href="javascript:role('${item.id}','${surveyCode}','product');">产品类型</a>
                        <a href="javascript:role('${item.id}','${surveyCode}','org');">业务来源</a>
                        <a href="javascript:role('${item.id}','${surveyCode}','corp');">开票公司</a>
                        <a href="javascript:role('${item.id}','${surveyCode}','enum');">开票产品</a>
                        <a href="javascript:role('${item.id}','${surveyCode}','item');">开票项目</a>
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
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?roleName=${roleName}&surveyCode=${surveyCode}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var role = function(id,surveyCode,searchType){
        openDialog({
            frame:true,
            title:"设置权限",
            height:650,
            width:1000,
            url:"${ctx}/baseSurvey/popup?roleId="+id+"&surveyCode="+surveyCode+"&searchType="+searchType,
            load:true
        });
    }
</script>
</body>
</html>
