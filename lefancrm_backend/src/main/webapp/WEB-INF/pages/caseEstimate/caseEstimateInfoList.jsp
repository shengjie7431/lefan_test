<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>伤残测算列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>伤残测算列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/caseEstimate/caseEstimateInfoList" method="post">
                    <div class="form-group">
                        案件类型:
                        <select name="type"  class="form-control">
                            <option value="">全部</option>
                            <option value="1"<c:if test="${type == 1}">selected="selected" </c:if>>伤残预估</option>
                            <option value="2"<c:if test="${type == 2}">selected="selected" </c:if>>赔付测算</option>
                        </select>
                    </div>
                    <div class="form-group">
                       申请人姓名:<input name="userName" type="text" value="${userName}" class="form-control">
                    </div>
                    <div class="form-group">
                        申请人电话:<input name="userPhone" type="text" value="${userPhone}" class="form-control">
                    </div>
                    <div class="form-group">
                        转办状态:
                        <select name="turnStatus"  class="form-control">
                            <option value="">全部</option>
                            <option value="1"<c:if test="${turnStatus == 1}">selected="selected" </c:if>>未转办</option>
                            <option value="2"<c:if test="${turnStatus == 2}">selected="selected" </c:if>>已转办</option>
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
                <th width="100">案件类型</th>
                <th width="150">案件申请人</th>
                <th width="150">用户电话</th>
                <th width="100">所在省</th>
                <th width="150">所在市</th>
                <th width="150">所在区</th>
                <th width="150">具体地址</th>
                <th width="150">是否已转办</th>
                <th width="150">创建时间</th>
                <th width="150">创建人</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>
                        <c:if test="${item.type == 1}"> 伤残预估</c:if>
                        <c:if test="${item.type == 2}"> 赔付测算</c:if>
                    </td>
                    <td>${item.userName}</td>
                    <td>${item.userPhone}</td>
                    <td>${item.accidentProvince}</td>
                    <td>${item.accidentCity}</td>
                    <td>${item.accidentDistrict}</td>
                    <td>${item.accidentAddress}</td>
                    <td>
                        ${item.turnStatusName}
                        <%--<c:if test="${item.turnStatus == 0}"> 未转办</c:if>--%>
                        <%--<c:if test="${item.turnStatus == 1}"> 已转办</c:if>--%>
                    </td>
                    <td>
                        <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${item.createBy}</td>
                    <td><a href="javascript:caseEstimateView('${item.estimateId}','${item.type}');">查看</a>
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
            <jsp:param name="requestUrl" value="${ctx}/caseEstimate/caseEstimateInfoList?userName=${userName}&type=${type}&userPhone=${userPhone}&turnStatus=${turnStatus}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var caseEstimateView = function(estimateId,type){
        openDialog({
            frame:true,
            title:"案件详情",
            height:800,
            width:1000,
            url:"${ctx}/caseEstimate/caseEstimateView?estimateId="+estimateId+"&type="+type,
            load:true
        });
    }

</script>
</body>
</html>
