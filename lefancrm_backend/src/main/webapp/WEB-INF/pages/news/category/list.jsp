<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title> 新闻栏目管理</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script>
        var updateInfo = function(id){
            openDialog({
                frame:true,
                title:"修改栏目信息",
                height:600,
                width:800,
                url:"${ctx}/newsCategory/queryById?id="+id
            });
        }
        var addinfo = function(){
            openDialog({
                frame:true,
                title:"添加栏目信息",
                height:600,
                width:800,
                url:"${ctx}/newsCategory/toAdd"
            });
        }

        var updateState = function(id,state){
            var val = "";
            var toVal ="";
            if(state == 1){
                val = "确定审核通过操作？"
                toVal = "审核成功！"
            }else if(state == 2){
                val = "确定驳回操作？"
                toVal = "驳回成功！"
            }
            ajaxSubmit("${ctx}/newsCategory/edit",{"id":id,"state":state},reload,toVal,val,"操作失败");
        }

        var del = function(id,deleteFlag){
            ajaxSubmit("${ctx}/newsCategory/edit",{"id":id,"deleteFlag":deleteFlag},reload,"删除成功！","确定删除操作！","操作失败");
        }

    </script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3> 新闻栏目列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/newsCategory/list" method="post">
                    <div class="form-group">
                        栏目名称:<input name="catName" type="text" value="${catName}" class="form-control">
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="addinfo()" type="button" class="btn btn-default" class="btn btn-default">添加新闻栏目</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">新闻分类名称</th>
                <th width="150">新闻分类说明</th>
                <th width="150">分类排序</th>
                <th width="150">新闻分类类别</th>
                <th width="150">状态</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.catName}</td>
                    <td>${item.catDesc}</td>
                    <td> ${item.catSort}</td>
                    <td>
                        <c:if test="${item.catType == 1}">新闻动态</c:if>
                        <c:if test="${item.catType == 2}">服务案例</c:if>
                        <c:if test="${item.catType == 3}">招聘信息</c:if>
                    </td>
                    <td>
                        <c:if test="${item.state == 0}">待审核</c:if>
                        <c:if test="${item.state == 1}">审核通过</c:if>
                        <c:if test="${item.state == 2}">驳回</c:if>
                    </td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <a  href="javascript:void(0)" onclick="updateInfo(${item.id})">编辑</a>
                        <a  href="javascript:void(0)" onclick="del(${item.id},1)">删除</a>
                        <c:if test="${item.state == 0}">
                            <a  href="javascript:void(0)" onclick="updateState(${item.id},1)">审核通过</a>
                            <a  href="javascript:void(0)" onclick="updateState(${item.id},2)">驳回</a>
                        </c:if>
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
            <jsp:param name="requestUrl" value="${ctx}/newsCategory/list?catName=${catName}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->
</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>


    /* var getreport = function(id){
     openDialog({
     frame:true,
     title:"查看测算报告",
     height:1000,
     width:800,
     url:"${ctx}/paymentEstimate/paymentEstimateReportList?id="+id
     });
     }*/
</script>
</body>
</html>
