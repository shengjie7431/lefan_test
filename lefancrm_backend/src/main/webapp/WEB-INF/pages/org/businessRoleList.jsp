<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title> 机构角色列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script>
        var updateInfo = function(id){
            openDialog({
                frame:true,
                title:"修改角色信息",
                height:400,
                width:600,
                url:"${ctx}/org/businessRoleUpdateTo?id="+id
            });
        }

        var addinfo = function(){
            openDialog({
                frame:true,
                title:"添加角色信息",
                height:400,
                width:600,
                url:"${ctx}/org/businessRoleAddTo"
            });
        }
    </script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>机构角色列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/org/businessRoleList" method="post">
                    <div class="form-group">
                        角色名:<input name="roleName" type="text" value="${roleName}" class="form-control">
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="addinfo()" type="button" class="btn btn-default">添加角色</button>&nbsp; &nbsp;
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
                        <a  href="javascript:void(0)" onclick="updateInfo(${item.id})">编辑</a>
                        <c:if test="${item.id > 8}"><a  href="javascript:void(0)" onclick="del(${item.id})">删除</a></c:if>
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
            <jsp:param name="requestUrl" value="${ctx}/org/businessRoleList?roleName=${roleName}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    function del(id){
        ajaxSubmit("${ctx}/org/businessRoleDel",{"id":id},reload,"删除成功","确定删除操作？","删除失败");
    }
</script>
</body>
</html>
