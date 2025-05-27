<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>CC人员职级职位数据列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>CC人员职级职位数据列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/userPoLevel/userPoLevelList" method="post">
                   <div class="form-group">
                       用户:<input name="userName" type="text" value="${userName}" class="form-control">
                   </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="userPoLevelAdd()" type="button" class="btn btn-default">添加数据</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">用户</th>
                <th width="150">职位</th>
                <th width="150">职级</th>
                <%--<th width="150">CC状态</th>--%>
                <th width="150">C状态</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>

                        <c:if test="${item.state == 1}"> <td><font color="green">${item.userName}</font></td></c:if>
                        <c:if test="${item.state == 2}"> <td><font color="yellow">${item.userName}</font></td></c:if>
                        <c:if test="${item.state == 3}"> <td><font color="orange">${item.userName}</font></td></c:if>
                        <c:if test="${item.state == 4}"> <td><font color="red">${item.userName}</font></td></c:if>
                        <c:if test="${item.state == 5}"> <td><font color="gray">${item.userName}</font></td></c:if>

                    <td>${item.positionName}</td>
                    <td>${item.positionLevelName}</td>
                    <td>
                        <c:if test="${item.stateName == 1}"> 正常</c:if>
                        <c:if test="${item.stateName == 2}"> 未达标</c:if>
                        <c:if test="${item.stateName == 3}"> 连续未达标</c:if>
                        <c:if test="${item.stateName == 4}"> 3个月未达标</c:if>
                        <c:if test="${item.stateName == 5}"> 开除</c:if>
                    </td>
                    <td><a href="javascript:userPoLevelEdit('${item.id}');">编辑</a>
                        <a href="javascript:userPoLevelDelete('${item.id}');">删除</a>

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
            <jsp:param name="requestUrl" value="${ctx}/userPoLevel/userPoLevelList?userName=${userName}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var userPoLevelEdit = function(id){
        openDialog({
            frame:true,
            title:"修改CC人员职级职位数据",
            height:500,
            width:800,
            url:"${ctx}/userPoLevel/userPoLevelEdit?id="+id
        });
    }

    var userPoLevelAdd = function(){
        openDialog({
            frame:true,
            title:"添加CC人员职级职位数据",
            height:500,
            width:800,
            url:"${ctx}/userPoLevel/userPoLevelAdd"
        });
    }

    function userPoLevelDelete(id){
        ajaxSubmit("${ctx}/userPoLevel/userPoLevelDelete",{"id":id},reload,"删除成功！","确认删除吗？");
    }
</script>
</body>
</html>
