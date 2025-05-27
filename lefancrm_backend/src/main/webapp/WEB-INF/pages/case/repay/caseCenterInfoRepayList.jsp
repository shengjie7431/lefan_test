<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>还款清单列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>还款清单列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/case/repayCaseCenterInfoList?pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <div class="form-group">
                        案件阶段:
                        <select name="gradationState"  class="form-control">
                            <option value="" <c:if test="${gradationState == ''}">selected="selected" </c:if>>请选择</option>
                            <option value="1" <c:if test="${gradationState == '1'}">selected="selected" </c:if>>洽谈阶段</option>
                            <option value="2" <c:if test="${gradationState == '2'}">selected="selected" </c:if>>评估阶段</option>
                            <option value="3" <c:if test="${gradationState == '3'}">selected="selected" </c:if>>索赔阶段</option>
                            <option value="4" <c:if test="${gradationState == '4'}">selected="selected" </c:if>>结案</option>
                        </select>
                    </div>
                    <div class="form-group">
                        还款阶段:
                        <select name="handOutFlag"  class="form-control">
                            <option value="" <c:if test="${handOutFlag == ''}">selected="selected" </c:if>>请选择</option>
                            <option value="2" <c:if test="${handOutFlag == '2'}">selected="selected" </c:if>>未提交还款</option>
                            <option value="5" <c:if test="${handOutFlag == '5'}">selected="selected" </c:if>>还款确认中</option>
                            <option value="6" <c:if test="${handOutFlag == '6'}">selected="selected" </c:if>>还款完成</option>
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
                    <div class="form-group">
                        案件编号:<input name="caseNo" type="text" value="${caseNo}" class="form-control">
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
                <th width="150">还款阶段</th>
                <th width="150">创建时间</th>
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
                        <c:if test="${item.handOutFlag == 2}"> 未提交还款</c:if>
                        <c:if test="${item.handOutFlag == 5}"> 还款确认中</c:if>
                        <c:if test="${item.handOutFlag == 6}"> 还款完成</c:if>
                    </td>
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
            <jsp:param name="requestUrl" value="${ctx}/case/repayCaseCenterInfoList?gradationState=${gradationState}&orgId=${orgId}&caseName=${caseName}&caseNo=${caseNo}&handOutFlag=${handOutFlag}&isTestcase=${isTestcase}" />
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
        var url = "${ctx}/case/center/info?id="+id+"&menuType=104";
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
