<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>机构押金充值记录</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>机构押金充值记录<small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/account/orgForegiftMoneyList" method="post">
                    <div class="form-group">
                        机构名称: <select name="orgId" class="form-control">
                        <option value="">全部</option>
                        <c:forEach items="${orgInfoDtos}" var="item1">
                            <option value="${item1.id}">${item1.orgName}</option>
                        </c:forEach>
                    </select>
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
                <th width="250">机构名</th>
                <th width="250">标题</th>
                <th width="250">金额</th>
                <th width="250">时间</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.orgName}</td>
                    <td>${item.title}</td>
                    <td>${item.money}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/account/orgForegiftMoneyList" />
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
