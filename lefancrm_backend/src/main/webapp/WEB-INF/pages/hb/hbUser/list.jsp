<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>核保人员列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>核保人员列表 <small>共<span>${apiFinalResponse1.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/hb/hbUserList" method="post">
                   <div class="form-group">
                       用户姓名:<input name="hbUserName" type="text" value="${hbUserName}" class="form-control">
                   </div>
                    <div class="form-group">
                        联系电话:<input name="hbUserTel" type="text" value="${hbUserTel}" class="form-control">
                    </div>
                    <div class="form-group">
                        用户状态:
                        <select name="hbUserStatus"  class="form-control">
                            <option value=""  >全部</option>
                            <option <c:if test="${hbUserStatus == 1}">selected="selected" </c:if> value="1" >启用</option>
                            <option <c:if test="${hbUserStatus == 2}">selected="selected" </c:if> value="2" >停用</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="hbOrgInfoAdd()" type="button" class="btn btn-default">添加数据</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">用户姓名</th>
                <th width="150">联系电话</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiFinalResponse1.results}" var="item">
                <tr>
                    <td>${item.hbUserName}</td>
                    <td>${item.hbUserTel}</td>
                    <td>
                        <a href="javascript:hbUserrRemove('${item.userId}');">移除</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/hb/hbUserList" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    function hbUserrRemove(id){
        ajaxSubmit("${ctx}/hb/hbUserDelete",{"userId":id},reload,"移除成功！","确认移除吗？");
    }




</script>
</body>
</html>
