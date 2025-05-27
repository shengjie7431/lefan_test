<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>职位数据列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>职位表数据列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/positionInfo/positionInfoList" method="post">
                   <div class="form-group">
                       职位名称:<input name="positionName" type="text" value="${positionName}" class="form-control">
                   </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="positionInfoAdd()" type="button" class="btn btn-default">添加职位数据</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">职位名称</th>
                <th width="150">职位描述</th>
                <th width="150">职级名称</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.positionName}</td>
                    <td>${item.positionDesc}</td>
                    <td>${item.positionLevelName}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td><a href="javascript:positionInfoEdit('${item.id}');">编辑</a>
                        <a href="javascript:positionInfoDelete('${item.id}');">删除</a>

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
            <jsp:param name="requestUrl" value="${ctx}/positionInfo/positionInfoList?positionName=${positionName}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var positionInfoEdit = function(id){
        openDialog({
            frame:true,
            title:"修改职位数据",
            height:500,
            width:800,
            url:"${ctx}/positionInfo/positionInfoEdit?id="+id
        });
    }

    var positionInfoAdd = function(){
        openDialog({
            frame:true,
            title:"添加职位数据",
            height:500,
            width:800,
            url:"${ctx}/positionInfo/positionInfoAdd"
        });
    }

    function positionInfoDelete(id){
        ajaxSubmit("${ctx}/positionInfo/positionInfoDelete",{"id":id},reload,"删除成功！","确认删除吗？");
    }
</script>
</body>
</html>
