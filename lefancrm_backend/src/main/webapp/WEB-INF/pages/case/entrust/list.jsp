<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件审核列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>案件审核列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/caseEntrustInput/list" method="post">
                    <div class="form-group">
                        案件类型:
                        <select name="agentType"  class="form-control">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${agentType == 1}">selected="selected" </c:if>>交通事故索赔</option>
                            <option value="2" <c:if test="${agentType == 2}">selected="selected" </c:if>>工伤事故索赔</option>
                        </select>
                    </div>
                    <div class="form-group">
                        审核状态:
                        <select name="checkState"  class="form-control">
                            <option value="">全部</option>
                            <option value="0" <c:if test="${checkState == 0}">selected="selected" </c:if>>待审核</option>
                            <option value="1" <c:if test="${checkState == 1}">selected="selected" </c:if>>审核通过</option>
                            <option value="2" <c:if test="${checkState == 2}">selected="selected" </c:if>>驳回</option>
                        </select>
                    </div>
                    <div class="form-group">
                        申请人姓名:<input name="injuredPerson" type="text" value="${injuredPerson}" class="form-control">
                    </div>
                    <div class="form-group">
                        申请人电话:<input name="injuredTel" type="text" value="${injuredTel}" class="form-control">
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
                <th width="100">案件类型</th>
                <th width="150">申请人姓名</th>
                <th width="150">申请人电话</th>
                <th width="150">审核状态</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>
                        <c:if test="${item.agentType == 1}"> 交通事故索赔</c:if>
                        <c:if test="${item.agentType == 2}"> 工伤事故索赔</c:if>
                    </td>
                    <td>${item.injuredPerson}</td>
                    <td>${item.injuredTel}</td>
                    <td>
                        <c:if test="${item.checkState == 0}">待审核</c:if>
                        <c:if test="${item.checkState == 1}">审核通过</c:if>
                        <c:if test="${item.checkState == 2}">已驳回</c:if>
                    </td>
                    <td>
                        <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <a href="javascript:info('${item.id}');">详情</a>
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
            <jsp:param name="requestUrl" value="${ctx}/caseEntrustInput/list?agentType=${agentType}&checkState=${checkState}&injuredPerson=${injuredPerson}&injuredTel=${injuredTel}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var info = function(id){
        openDialog({
            frame:true,
            title:"案件审核详情",
            height:500,
            width:1000,
            url:"${ctx}/caseEntrustInput/info?id="+id,
            load:true
        });
    }

</script>
</body>
</html>
