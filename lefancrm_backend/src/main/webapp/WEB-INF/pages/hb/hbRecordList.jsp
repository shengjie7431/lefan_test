<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>核保记录列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>核保记录列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/hb/hbReCordList" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <div class="form-group">
                        用户姓名:<input name="hbUserName" type="text" value="${hbUserName}" class="form-control">
                    </div>
                    <div class="form-group">
                        产品名称:
                        <select name="productName"  class="form-control">
                            <option value="">全部</option>
                            <option value="LFCI">重疾险</option>
                            <option value=" LFMI">医疗险</option>
                            <option value="LFLI">寿险</option>
                        </select>
                    </div>
                    <div class="form-group">
                        使用时间:
                        <select name="hbOrgId"  class="form-control">
                         <c:forEach items="${apiFinalResponse2.results}" var="item">
                            <option <c:if test="${item.id == hbOrgId}">selected="selected" </c:if> value="${item.id}" >${item.hbOrgName}</option>
                         </c:forEach>
                        </select>
                        <label class="title">委托时间:</label>
                        <input name="startDate" type="text" value="${startDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly>
                        <span>--</span>
                        <input name="endDate" type="text" value="${endDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                    </div>
                </form>
            </div>
        </div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">Code</th>
                <th width="150">机构名称</th>
                <th width="150">用户名称</th>
                <th width="150">产品名称</th>
                <th width="150">使用时间</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.hbOrgName}</td>
                    <td>${item.hbUserName}</td>
                    <td>
                        <c:if test="${item.productName=='LFCI'}">重疾险</c:if>
                        <c:if test="${item.productName=='LFMI'}">医疗险</c:if>
                        <c:if test="${item.productName=='LFLI'}">寿险</c:if>
                    </td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/hb/hbReCordList" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

<script>





</script>
</body>
</html>
