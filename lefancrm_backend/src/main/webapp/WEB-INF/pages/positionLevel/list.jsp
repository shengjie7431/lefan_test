<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>职级数据列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>职级数据列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/positionLevel/positionLevelList" method="post">
                   <div class="form-group">
                       职级代码:<input name="levelCode" type="text" value="${levelCode}" class="form-control">
                   </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="positionLevelAdd()" type="button" class="btn btn-default">添加职级数据</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">职级代码</th>
                <th width="150">职级描述</th>
                <th width="150">基本工资</th>
                <th width="150">考评工资</th>
                <th width="150">父级</th>
                <th width="150">管理佣金指标</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.levelCode}</td>
                    <td>${item.levelDesc}</td>
                    <td>${item.baseWages}</td>
                    <td>${item.evaWages}</td>
                    <td>${item.parentName}</td>
                    <td>${item.managerComrateId}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td><a href="javascript:positionLevelEdit('${item.id}');">编辑</a>
                        <a href="javascript:positionLevelDelete('${item.id}');">删除</a>

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
            <jsp:param name="requestUrl" value="${ctx}/positionLevel/positionLevelList?levelCode=${levelCode}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var positionLevelEdit = function(id){
        openDialog({
            frame:true,
            title:"修改职级数据",
            height:650,
            width:1300,
            url:"${ctx}/positionLevel/positionLevelEdit?id="+id
        });
    }

    var positionLevelAdd = function(){
        openDialog({
            frame:true,
            title:"添加职级数据",
            height:650,
            width:1300,
            url:"${ctx}/positionLevel/positionLevelAdd?isShow=1"
        });
    }

    function positionLevelDelete(id){
        ajaxSubmit("${ctx}/positionLevel/positionLevelDelete",{"id":id},reload,"删除成功！","确认删除吗？");
    }
</script>
</body>
</html>
