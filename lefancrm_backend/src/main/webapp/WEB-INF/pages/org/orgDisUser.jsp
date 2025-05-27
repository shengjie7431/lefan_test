<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户分配列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3><small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

       <%-- <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/org/orgDisUser" method="post">
                    <div class="form-group">
                    </div>
                    <div class="btn-group">
                    </div>
                </form>
            </div>
        </div>--%>
           <div class="panel-heading">
               <div class="pin">
                   <form class="form-inline" role="form" action="${ctx}/org/toDisUser" method="post">
                       <div class="form-group">
                           真实姓名:<input name="userName" type="text" value="${userName}" class="form-control">
                           昵称:<input name="nickName" type="text" value="${nickName}" class="form-control">
                           电话:<input name="userTel" type="text" value="${userTel}" class="form-control">
                       </div>
                       <input type="hidden" value="${orgId}" name="id">
                       <div class="btn-group">
                           &nbsp; &nbsp;<button id="batchOperateBtnTo" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                       </div>
                   </form>
               </div>
           </div>

           <form id="editForm" role="form" action="${ctx}/org/disUser" method="post">
        <table class="table table-hover">
            <thead>
            <tr>
                <th th width="80"><input type="checkbox" id="all" >全选</th>
                <th width="50">真实姓名</th>
                <th width="50">手机号</th>
                <th width="50">昵称</th>
                <th width="50">状态</th>
                <th width="70">所属机构</th>
                <th width="70">机构用户类型</th>
                <th width="50">类型</th>
                <th width="50">性别</th>
                <th width="70">是否是推广人</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <input type="hidden" value="${orgId}" name="orgId">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td><input type="checkbox" value="${item.userId}" name="users">
                    </td>
                    <td>${item.userName}</td>
                    <td>${item.userTel}</td>
                    <td>${item.nickName}</td>
                    <td>
                        <c:if test="${item.userState==0}">启用</c:if>
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
                        <c:if test="${item.userType == 2}">机构用户</c:if>
                    </td>
                    <td>
                        <c:if test="${item.sex==0}">男</c:if>
                        <c:if test="${item.sex==1}">女</c:if>
                    </td>
                    <td>
                        <c:if test="${item.isPromoter!=0}">否</c:if>
                        <c:if test="${item.isPromoter!=1}">是</c:if>
                    </td>
                </tr>
            </c:forEach>
          <tr>
                 <td colspan="10">
                     <button id="batchOperateBtn" type="submit" class="btn btn-default">确定提交</button>
                 </td>
             </tr>
            </tbody>
        </table>
        </form>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/org/toDisUser?id=${orgId}&userName=${userName}&nickName=${nickName}&userTel=${userTel}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->
</body>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    $(document).ready(function(){
        $("#all").on('change',function(){
            $("input[name='users']").prop("checked",this.checked);
        })
    })
    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
</script>
</body>
</html>
