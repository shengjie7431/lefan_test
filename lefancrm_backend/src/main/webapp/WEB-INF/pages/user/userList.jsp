<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title> 用户列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script>
        var updateInfo = function(id){
            openDialog({
                frame:true,
                title:"修改用户信息",
                height:700,
                width:600,
                url:"${ctx}/user/userInfoEdit?userId="+id
            });
        }

        var addPromoted = function(id){
            openDialog({
                frame:true,
                title:"添加推广信息",
                height:700,
                width:800,
                url:"${ctx}/user/toAddPromoted?userId="+id
            });
        }

        var addinfo = function(){
            openDialog({
                frame:true,
                title:"添加用户信息",
                height:740,
                width:600,
                url:"${ctx}/user/toAddUser"
            });
        }
    </script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3> 用户列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/user/userList" method="post">
                    <div class="form-group">
                        昵称:<input name="nickName" type="text" value="${nickName}" class="form-control">
                        电话:<input name="userTel" type="text" value="${userTel}" class="form-control">
                        所属机构:<input name="orgName" type="text" value="${orgName}" class="form-control">
                        类型: <select name="type" class="form-control">
                        <option value="">全部</option>
                        <option value="1" <c:if test="${type == 1}">selected="selected" </c:if>>普通用户</option>
                        <option value="2" <c:if test="${type == 2}">selected="selected" </c:if>>机构用户</option>
                        <option value="3" <c:if test="${type == 3}">selected="selected" </c:if>>测试用户</option>
                        <option value="4" <c:if test="${type == 4}">selected="selected" </c:if>>其他</option>
                    </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="addinfo()" type="button" class="btn btn-default">添加用户信息</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">头像</th>
                <th width="150">真实姓名</th>
                <th width="150">手机号</th>
                <th width="150">注册时间</th>
                <th width="150">昵称</th>
                <th width="150">所属机构</th>
                <th width="150">机构角色</th>
                <th width="150">类型</th>
                <th width="150">性别</th>
                <th width="150">是否是推广人</th>
                <th width="150">状态</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td><a href="${item.img}" target="_blank"><img src="${item.img}" width="75px" height="75px"></a></td>
                    <td>${item.userName}</td>
                    <td>${item.userTel}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>${item.nickName}
                   	<span class="row-actions">
                		<c:if test="${item.userState!=0}"><a href="javascript:enableUser('${item.userId}');"><span class="glyphicon glyphicon-ok"></span>启用</a></c:if>
                		<c:if test="${item.userState==0}"><a href="javascript:lockUser('${item.userId}');"><span class="glyphicon glyphicon-remove"></span>冻结</a></c:if>
                	</span>
                    </td>
                    <td>${item.orgName}</td>
                    <td>
                        <c:forEach items="${bsInfo}" var="bs">
                            <c:forEach items="${item.busUserRoles}" var="roles">
                                <c:if test="${roles.roleId ==bs.id}">${bs.roleName}<br/></c:if>
                            </c:forEach>
                        </c:forEach>
                    </td>
                    <td><c:if test="${item.userType == 1}">普通用户</c:if>
                        <c:if test="${item.userType == 2}">机构用户</c:if>
                        <c:if test="${item.userType == 3}">测试用户</c:if>
                        <c:if test="${item.userType == 4}">其他</c:if>
                    </td>
                    <td>
                        <c:if test="${item.sex==0}">男</c:if>
                        <c:if test="${item.sex==1}">女</c:if>
                    </td>
                    <td>
                        <c:if test="${item.isPromoter==0}">否</c:if>
                        <c:if test="${item.isPromoter==1}">是</c:if>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${item.userState==0}"><span class="glyphicon glyphicon-ok-sign text-success" title="正常"></span></c:when>
                            <c:when test="${item.userState==1}"><span class="glyphicon glyphicon-remove-sign text-danger" title="冻结"></span></c:when>
                        </c:choose>
                    </td>
                    <td>
                        <a  href="javascript:void(0)" onclick="updateInfo(${item.userId})">编辑</a>
                        <c:if test="${item.isPromoter == 0}"><a  href="javascript:void(0)" onclick="addPromoted(${item.userId})">添加推广信息</a></c:if>
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
            <jsp:param name="requestUrl" value="${ctx}/user/userList?nickName=${nickName}&userTel=${userTel}&orgName=${orgName}&type=${type}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    function enableUser(id){
        ajaxSubmit("${ctx}/user/changeStatus",{"userId":id, "userState":0},reload,"启用成功","确认启用？");
    }
    function lockUser(id){
        ajaxSubmit("${ctx}/user/changeStatus",{"userId":id, "userState":1},reload,"冻结成功","确认冻结？");
    }

    var insUserAddressList = function(userId){
        openDialog({
            frame:true,
            title:"保险员辖区列表",
            height:500,
            width:800,
            url:"${ctx}/insUserAddress/insUserAddressList?userId="+userId
        });
    }
</script>
</body>
</html>
