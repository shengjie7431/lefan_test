<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>推广用户佣金记录</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>推广用户佣金记录<small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/account/userPromotionOutlayList" method="post">
                    <div class="form-group">
                        机构名称: <select name="orgId" class="form-control">
                        <option value="">全部</option>
                        <c:forEach items="${orgInfoDtos}" var="item1">
                            <option value="${item1.id}" >${item1.orgName}</option>
                        </c:forEach>

                    </select>
                    </div>
                    <div class="form-group">
                        案件编号: <input name="caseNo" type="text"  value="${caseNo}" class="form-control">
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                        &nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">案件编号</th>
                <th width="200">案件名称</th>
                <th width="150">用户名</th>
                <th width="150">电话号码</th>
                <th width="150">用户所属机构</th>
                <th width="150">金额</th>
                <th width="200">费用来源</th>
                <th width="150">创建时间</th>
                <th width="200">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.caseNo}</td>
                    <td>${item.caseName}</td>
                    <td>${item.userName}</td>
                    <td>${item.userTel}</td>
                    <td>${item.orgName}</td>
                    <td>${item.money}</td>
                    <td>
                        <c:if test="${item.type == 1}">用户推广案件费用</c:if>
                        <c:if test="${item.type == 2}">用户案件签约费用</c:if>
                    </td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td></td>
                </tr>
            </c:forEach>
           <%-- <tr>
                <td colspan="10">
                    <c:if test="${page > 1}">
                        <a href="${ctx}/account/userPromotionOutlayList?page=${page-1}">上一页</a>&nbsp;
                    </c:if>
                    <a href="${ctx}/account/userPromotionOutlayList?page=${page+1}">下一页</a>
                </td>
            </tr>--%>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/account/userPromotionOutlayList" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>


    var getreport = function(id){
        openDialog({
            frame:true,
            title:"查看测算报告",
            height:1000,
            width:800,
            url:"${ctx}/paymentEstimate/paymentEstimateReportList?id="+id
        });
    }
</script>
</body>
</html>
