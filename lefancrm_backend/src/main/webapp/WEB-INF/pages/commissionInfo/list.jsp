<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>佣金指标数据列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>佣金指标数据列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/commissionInfo/commissionInfoList" method="post">
                   <div class="form-group">
                       职级名称:<input name="levelCode" type="text" value="${levelCode}" class="form-control">
                   </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="commissionInfoAdd()" type="button" class="btn btn-default">添加数据</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">职级名称</th>
                <th width="150">1级服务费指标</th>
                <th width="150">1级佣金提成比例</th>
                <th width="150">2级服务费指标</th>
                <th width="150">2级佣金提成比例</th>
                <th width="150">3级服务费指标</th>
                <th width="150">3级佣金提成比例</th>
                <th width="150">4级服务费指标</th>
                <th width="150">4级佣金提成比例</th>
                <th width="150">5级服务费指标</th>
                <th width="150">5级佣金提成比例</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.levelCode}</td>
                    <td>${item.oneQuota}</td>
                    <td>${item.oneComrate}</td>
                    <td>${item.twoQuota}</td>
                    <td>${item.twoComrate}</td>
                    <td>${item.threeQuota}</td>
                    <td>${item.threeComrate}</td>
                    <td>${item.fourQuota}</td>
                    <td>${item.fourComrate}</td>
                    <td>${item.fiveQuota}</td>
                    <td>${item.fiveComrate}</td>
                    <td><a href="javascript:commissionInfoEdit('${item.id}');">编辑</a>
                        <a href="javascript:commissionInfoDelete('${item.id}');">删除</a>

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
            <jsp:param name="requestUrl" value="${ctx}/commissionInfo/commissionInfoList?positionName=${positionName}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var commissionInfoEdit = function(id){
        openDialog({
            frame:true,
            title:"修改数据",
            height:500,
            width:800,
            url:"${ctx}/commissionInfo/commissionInfoEdit?id="+id
        });
    }

    var commissionInfoAdd = function(){
        openDialog({
            frame:true,
            title:"添加数据",
            height:500,
            width:800,
            url:"${ctx}/commissionInfo/commissionInfoAdd"
        });
    }

    function commissionInfoDelete(id){
        ajaxSubmit("${ctx}/commissionInfo/commissionInfoDelete",{"id":id},reload,"删除成功！","确认删除吗？");
    }
</script>
</body>
</html>
