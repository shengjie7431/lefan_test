<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title> 用户列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3><small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/corg/queryCOrgUserList" method="post">
                    <div class="form-group">
                        真实姓名:<input name="userName" type="text" value="${userName}" class="form-control">
                        昵称:<input name="nickName" type="text" value="${nickName}" class="form-control">
                        电话:<input name="userTel" type="text" value="${userTel}" class="form-control">
                        <input type="hidden" value="${cOrgId}" name="cOrgId">
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
                <th width="50">真实姓名</th>
                <th width="50">手机号</th>
                <th width="50">昵称</th>
                <th width="50">状态</th>
                <th width="70">所属组织</th>
                <th width="70">组织用户类型</th>
                <th width="50">类型</th>
                <th width="50">性别</th>
                <th width="70">是否是推广人</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.userName}</td>
                    <td>${item.userTel}</td>
                    <td>${item.nickName}</td>
                    <td>
                        <c:if test="${item.userState!=1}">启用</c:if>
                        <c:if test="${item.userState==1}">冻结</c:if>
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
                        <c:if test="${item.userType == 2}">组织用户</c:if>
                    </td>
                    <td>
                        <c:if test="${item.sex==0}">男</c:if>
                        <c:if test="${item.sex==1}">女</c:if>
                    </td>
                    <td>
                        <c:if test="${item.isPromoter!=0}">否</c:if>
                        <c:if test="${item.isPromoter!=1}">是</c:if>
                    </td>
                    <td>
                            <%--<a  href="javascript:void(0)" onclick="disDsRole(${item.userId})">分配角色</a>--%>
                            <a  href="javascript:void(0)" onclick="delDsRole(${item.userId},${cOrgId},${cOrgType})">移除用户</a>
                    </td>
                </tr>
            </c:forEach>
            <%-- <tr>
                 <td colspan="10">
                     <c:if test="${page > 1}">
                         <a href="${ctx}/account/orderInfoList?page=${page-1}">上一页</a>&nbsp;
                     </c:if>
                     <a href="${ctx}/account/orderInfoList?page=${page+1}">下一页</a>
                 </td>
             </tr>--%>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/corg/queryCOrgUserList?cOrgId=${cOrgId}&userName=${userName}&nickName=${nickName}&userTel=${userTel}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var insUserAddressList = function(userId){
        openDialog({
            frame:true,
            title:"保险员辖区列表",
            height:500,
            width:800,
            url:"${ctx}/insUserAddress/insUserAddressList?userId="+userId
        });
    }
    var disDsRole = function(userId){
        openDialog({
            frame:true,
            title:"分配组织角色",
            height:500,
            width:1000,
            url:"${ctx}/org/disDsRole?userId="+userId
        });
    }

    function delDsRole(userId,cOrgId,cOrgType){
        ajaxSubmit("${ctx}/corg/removeUser",{"userId":userId,"cOrgType":cOrgType,"cOrgId":cOrgId},reload,"移除成功！","确定将该用户移除组织？",null);
    }

    function insuranceList(userId,orgId){
        openDialog({
            frame:true,
            title:"我的保险员",
            height:500,
            width:1000,
            url:"${ctx}/userInsurance/list?userId="+userId+"&orgId="+orgId
        });
    }
</script>
</body>
</html>
