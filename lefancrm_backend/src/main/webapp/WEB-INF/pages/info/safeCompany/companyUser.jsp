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
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/infoSafeCompany/companyUser" method="post">
                    <input type="hidden" value="${safeCompanyId}" name="safeCompanyId">
                    <div class="form-group">
                        姓名:<input name="userName" type="text" value="${userName}" class="form-control">
                        电话:<input name="userTel" type="text" value="${userTel}" class="form-control">
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtnTo" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>

        <form id="editForm" role="form" action="${ctx}/infoSafeCompany/confirmUser" method="post">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="50">姓名</th>
                    <th width="50">手机号</th>
                    <th width="50">昵称</th>
                    <th width="70">所属机构</th>
                    <th width="50">性别</th>
                    <th width="50">操作</th>
                </tr>
                </thead>
                <tbody class="class-list">

                <c:forEach items="${apiRsp.results}" var="item">
                    <tr>
                        <td>${item.userName}</td>
                        <td>${item.userTel}</td>
                        <td>${item.nickName}</td>
                        <td>${item.orgName}</td>
                        <td>
                            <c:if test="${item.sex==0}">男</c:if>
                            <c:if test="${item.sex==1}">女</c:if>
                        </td>
                        <td><a href="javascript:remove('${item.infoSafeUserId}');">移除</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/infoSafeCompany/companyUser?userName=${userName}&userTel=${userTel}&safeCompanyId=${safeCompanyId}" />
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

    /**
     * 移除
     */
    function remove(id){
        ajaxSubmit("${ctx}/infoSafeCompany/remove",{"id":id},reload,"删除成功！","确认删除？","删除失败！");
    }
</script>
</body>
</html>
