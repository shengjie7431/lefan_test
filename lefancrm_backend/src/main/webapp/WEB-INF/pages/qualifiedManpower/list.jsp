<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>合格人力奖金指标数据列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>合格人力奖金指标数据列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/qualifiedManpower/qualifiedManpowerList" method="post">
                   <div class="form-group">
                       职级名称:<input name="levelCode" type="text" value="${levelCode}" class="form-control">
                   </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="qualifiedManpowerAdd()" type="button" class="btn btn-default">添加数据</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">职级名称</th>
                <th width="150">合格人力指标</th>
                <th width="150">合格人力奖金/人/月</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.levelCode}</td>
                    <td>${item.qualifiedMpNum}</td>
                    <td>${item.qualifiedMpMoney}</td>
                    <td><a href="javascript:qualifiedManpowerEdit('${item.id}');">编辑</a>
                        <a href="javascript:qualifiedManpowerDelete('${item.id}');">删除</a>

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
            <jsp:param name="requestUrl" value="${ctx}/qualifiedManpower/qualifiedManpowerList?positionName=${positionName}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var qualifiedManpowerEdit = function(id){
        openDialog({
            frame:true,
            title:"修改数据",
            height:500,
            width:800,
            url:"${ctx}/qualifiedManpower/qualifiedManpowerEdit?id="+id
        });
    }

    var qualifiedManpowerAdd = function(){
        openDialog({
            frame:true,
            title:"添加数据",
            height:500,
            width:800,
            url:"${ctx}/qualifiedManpower/qualifiedManpowerAdd"
        });
    }

    function qualifiedManpowerDelete(id){
        ajaxSubmit("${ctx}/qualifiedManpower/qualifiedManpowerDelete",{"id":id},reload,"删除成功！","确认删除吗？");
    }
</script>
</body>
</html>
