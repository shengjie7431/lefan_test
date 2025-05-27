<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>到账列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>到账列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">

            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/suning/billApply/list?" method="post">
                    <c:if test="${op != 'view'}">
                        <div class="form-group">
                            案件编号: <input name="caseNo" type="text"  value="${caseNo}" class="form-control">
                        </div>
                        <div class="form-group">
                            案件标题: <input name="caseTitle" type="text"  value="${caseTitle}" class="form-control">
                        </div>
                        <div class="btn-group">
                            <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                        </div>
                    </c:if>
                </form>
            </div>

        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th class="th-checkbox">
                    <%--<input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选">--%>
                </th>
                <th width="80">案件编号</th>
                <th width="300">案件标题</th>
                <th width="100">到账金额</th>
                <th width="200">到账时间</th>
                <th width="200">佣金归属人</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td>${item.caseNo}</td>
                    <td>${item.caseTitle}</td>
                    <td>${item.arrivalMoney}</td>
                    <td><fmt:formatDate value="${item.arrivalTime}" pattern="yyyy-MM-dd"/></td>
                    <td>${item.userCommissionName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/suning/billApply/list" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/


</script>
</body>
</html>
