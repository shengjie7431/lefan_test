<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>扣罚记录列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>扣罚记录列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/caseCenterInfoFined/caseCenterInfoFinedList" method="post">
                    <div class="form-group">
                        扣罚类目:
                        <select name="finedType" class="form-control">
                            <option value="">全部</option>
                            <c:forEach items="${commonFineEnumDto}" var="item1">
                                <option <c:if test="${finedType == item1.id}">selected="selected" </c:if> value="${item1.id}" >${item1.fineEnumName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        案件类型:
                        <select name="caseType"  class="form-control">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${caseType == 1}">selected="selected" </c:if>>垫付</option>
                            <option value="2" <c:if test="${caseType == 2}">selected="selected" </c:if>>代理</option>
                        </select>
                    </div>
                    <div class="form-group">
                        是否生产案件:
                        <select name="isTestcase"  class="form-control">
                            <option value="-1">全部</option>
                            <option value="0" <c:if test="${isTestcase == 0}">selected="selected" </c:if>>是</option>
                            <option value="1" <c:if test="${isTestcase == 1}">selected="selected" </c:if>>否</option>
                        </select>
                    </div>
                    <br>
                    <div class="form-group">
                        案件编号:<input name="caseNo" type="text" value="${caseNo}" class="form-control">
                    </div>
                    <div class="form-group">
                        申请人姓名:<input name="caseName" type="text" value="${caseName}" class="form-control">
                    </div>
                    <div class="form-group">
                        案件标题:<input name="caseTitle" type="text" value="${caseTitle}" class="form-control">
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
                <th width="150">扣罚类目</th>
                <th width="150">扣罚金额</th>
                <th width="150">扣罚操作人</th>
                <th width="150">扣罚时间</th>
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
                    <td>${item.finedTypeName}</td>
                    <td>${item.finedMoney}</td>
                    <td>${item.finedBy}</td>
                    <td><fmt:formatDate value="${item.finedTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/caseCenterInfoFined/caseCenterInfoFinedList?caseName=${caseName}&finedType=${finedType}&caseNo=${caseNo}&caseTitle=${caseTitle}&caseType=${caseType}&isTestcase=${isTestcase}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

</script>
</body>
</html>
