<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>机构账户列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>机构账户列表<small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/account/orgAccountList" method="post">
                    <div class="form-group">
                        机构名称: <select name="orgId" class="form-control">
                        <option value="">全部</option>
                        <c:forEach items="${orgInfoDtos}" var="item1">
                            <option value="${item1.id}" >${item1.orgName}</option>
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

                <th width="150">机构名称</th>
                <th width="150">机构电话</th>
                <th width="150">机构联系人</th>
                <th width="150">联系人电话</th>
                <th width="150">充值总金额</th>
                <th width="150">可用余额</th>
                <th width="150">充值押金</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>

                    <td>${item.orgName}</td>
                    <td>${item.orgTel}</td>
                    <td>${item.linkName}</td>
                    <td>${item.linkTel}</td>
                    <td>${item.userRecharge}</td>
                    <td>${item.userUsable}</td>
                    <td>${item.userForegift}</td>
                    <td>
                        <a href="javascript:orgAccountRecharge('${item.orgId}');">充值金额</a>
                    </td>
                </tr>
            </c:forEach>
            <%--<tr>
                <td colspan="10">
                    <c:if test="${page > 1}">
                        <a href="${ctx}/account/withdrawalsInfoList?page=${page-1}">上一页</a>&nbsp;
                    </c:if>
                    <a href="${ctx}/account/withdrawalsInfoList?page=${page+1}">下一页</a>
                </td>
            </tr>--%>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/account/orgAccountList" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>



    var orgAccountRecharge= function(id){
        openDialog({
            frame:true,
            title:"机构账户充值",
            height:400,
            width:800,
            url:"${ctx}/account/toorgAccountRecharge?orgId="+id
        });
    }


</script>
</body>
</html>
