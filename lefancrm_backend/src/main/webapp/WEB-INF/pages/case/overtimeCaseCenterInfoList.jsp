<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件超时列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>案件超时列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/case/overtimeCaseCenterInfoList" method="post">
                    <div class="form-group">
                        超时类型:
                        <select name="overTimeType"  class="form-control">
                            <option value="">全部</option>
                            <option value="1"<c:if test="${overTimeType == 1}">selected="selected" </c:if>>未接收案件</option>
                            <option value="2"<c:if test="${overTimeType == 2}">selected="selected" </c:if>>未跟踪案件</option>
                            <option value="3"<c:if test="${overTimeType == 3}">selected="selected" </c:if>>状态未变更</option>
                            <option value="4"<c:if test="${overTimeType == 4}">selected="selected" </c:if>>超时未签约</option>
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
                    <div class="form-group">
                        申请人姓名:<input name="caseName" type="text" value="${caseName}" class="form-control">
                    </div>
                    <br>
                    <div class="form-group">
                        案件编号:<input name="caseNo" type="text" value="${caseNo}" class="form-control">
                    </div>
                    <div class="form-group">
                        推广人姓名:<input name="salesmanName" type="text" value="${salesmanName}" class="form-control">
                    </div>
                    <div class="form-group">
                        推广人联系方式:<input name="salesmanPhone" type="text" value="${salesmanPhone}" class="form-control">
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
                <th width="100">案件申请人</th>
                <th width="100">案件类型</th>
                <th width="100">案件阶段</th>
                <th width="100">超时类型</th>
                <th width="100">推广人姓名</th>
                <th width="100">推广人电话</th>
                <th width="100">创建时间</th>
                <th width="50">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.caseNo}</td>
                    <td>${item.caseTitle}</td>
                    <td>${item.caseName}</td>
                    <td>
                        <c:if test="${item.type == 1}"> 代理</c:if>
                        <c:if test="${item.type == 2}"> 垫付</c:if>
                    </td>
                    <td>
                        <c:if test="${item.gradationState == 1}"> 洽谈阶段</c:if>
                        <c:if test="${item.gradationState == 2}"> 评估阶段</c:if>
                        <c:if test="${item.gradationState == 3}"> 索赔阶段</c:if>
                        <c:if test="${item.gradationState == 4}"> 结案</c:if>
                        <c:if test="${item.gradationState == 5}"> 风控部门审核阶段</c:if>
                        <c:if test="${item.gradationState == 6}"> 诉讼阶段</c:if>
                    </td>
                    <td>
                        <c:if test="${item.overTimeType == 1}"> 未接收案件</c:if>
                        <c:if test="${item.overTimeType == 2}"> 未跟踪案件</c:if>
                        <c:if test="${item.overTimeType == 3}"> 状态未变更</c:if>
                        <c:if test="${item.overTimeType == 4}"> 超时未签约</c:if>
                    </td>
                    <td>${item.salesmanName}</td>
                    <td>${item.salesmanPhone}</td>
                    <td>
                        <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/>
                    </td>
                    <td><a href="javascript:caseCenterInfoRepayView('${item.id}');">查看</a>
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
            <jsp:param name="requestUrl" value="${ctx}/case/overtimeCaseCenterInfoList?overTimeType=${overTimeType}&caseName=${caseName}&caseNo=${caseNo}&salesmanName=${salesmanName}&salesmanPhone=${salesmanPhone}&isTestcase=${isTestcase}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    <%--var caseCenterInfoRepayView = function(id){--%>
    <%--openDialog({--%>
    <%--frame:true,--%>
    <%--title:"还款详情",--%>
    <%--height:600,--%>
    <%--width:1000,--%>
    <%--url:"${ctx}/case/repayCaseCenterInfoView?id="+id--%>
    <%--});--%>
    <%--}--%>

    function caseCenterInfoRepayView(id){
        var url = "${ctx}/case/center/info?id="+id+"&menuType=105";
//       alert($("#tabs").tabs("exists","sss"));
//       addTab('案件信息',url,true);return;
        openDialog({
            frame:true,
            title:"案件信息",
            height:750,
            width:1200,
            url : url,
            load:true
        });
    }
</script>
</body>
</html>
