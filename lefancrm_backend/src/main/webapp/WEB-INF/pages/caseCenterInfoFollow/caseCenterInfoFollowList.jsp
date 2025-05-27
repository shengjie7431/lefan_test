<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件跟踪列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <%--<h3>案件申请列表 <small>共<span>${apiRsp.count}</span>个</small></h3>--%>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/caseCenterInfoFollow/caseCenterInfoFollowList" method="post">
                    <div class="form-group">
                        案件阶段:
                        <select name="gradationState"  class="form-control">
                            <option value="">请选择</option>
                            <option value="1">洽谈阶段</option>
                            <option value="2">评估阶段</option>
                            <option value="3">索赔阶段</option>
                            <option value="4">结案</option>
                        </select>
                    </div>
                    <div class="form-group">
                        机构:
                        <select name="orgId"  class="form-control">
                            <option value="">请选择</option>
                            <option value="8">徐州机构</option>
                        </select>
                    </div>
                    <div class="form-group">
                        申请人姓名:<input name="caseName" type="text" value="${caseName}" class="form-control">
                    </div>
                    <div class="form-group">
                        案件编号:<input name="caseNo" type="text" value="${caseNo}" class="form-control">
                    </div>
                    <div class="form-group">
                        签约状态:
                        <select name="caseState"  class="form-control">
                            <%--<option value="1">未签约</option>--%>
                            <option value="">请选择</option>
                            <option value="5">已签约</option>
                            <option value="4">已放弃</option>
                            <option value="28">已解约</option>
                        </select>
                    </div>
                    <div class="form-group">
                        跟进状态:
                        <select name="followType"  class="form-control">
                            <option value="">请选择</option>
                            <option value="0">未跟进</option>
                            <option value="1">跟进中</option>
                            <option value="2">跟进结束</option>
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
                <th width="100">案件编号</th>
                <th width="100">案件标题</th>
                <th width="150">案件申请人</th>
                <th width="150">案件类型</th>
                <th width="150">案件阶段</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.caseNo}</td>
                    <td>${item.caseTitle}</td>
                    <td>${item.caseName}</td>
                    <td>
                        <c:if test="${item.caseType == 1}"> 垫付</c:if>
                        <c:if test="${item.caseType == 2}"> 代理</c:if>
                    </td>
                    <td>
                        <c:if test="${item.gradationState == 1}"> 洽谈阶段</c:if>
                        <c:if test="${item.gradationState == 2}"> 评估阶段</c:if>
                        <c:if test="${item.gradationState == 3}"> 索赔阶段</c:if>
                        <c:if test="${item.gradationState == 4}"> 结案</c:if>
                        <c:if test="${item.gradationState == 5}"> 风控部门审核阶段</c:if>
                        <c:if test="${item.gradationState == 6}"> 诉讼阶段</c:if>
                    </td>
                    <td><a href="javascript:caseCenterInfoFollowView('${item.caseId}');">查看</a>
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
            <jsp:param name="requestUrl" value="${ctx}/caseCenterInfoFollow/caseCenterInfoFollowList?followType=${followType}&gradationState=${gradationState}&orgId=${orgId}&caseName=${caseName}&caseNo=${caseNo}&caseState=${caseState}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var caseCenterInfoFollowView = function(caseId){
        openDialog({
            frame:true,
            title:"案件审核详情",
            height:500,
            width:800,
            url:"${ctx}/caseCenterInfoFollow/caseCenterInfoFollowView?caseId="+caseId
        });
    }

</script>
</body>
</html>
