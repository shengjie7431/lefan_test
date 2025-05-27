<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>机构案件列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3><small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/org/queryCase" method="post">
                    <div class="form-group">
                        <input type="hidden" name="id" value="${id}">
                        案件编号:<input name="caseNo" type="text" value="${caseNo}" class="form-control">
                        申请人:<input name="caseName" type="text" value="${caseName}" class="form-control">
                        申请人电话:<input name="caseTel" type="text" value="${caseTel}" class="form-control"><br>
                        案件标题:<input name="caseTitle" type="text" value="${caseTitle}" class="form-control">
                        分配人:<input name="orgUserName" type="text" value="${orgUserName}" class="form-control">
                        案件类型:&nbsp; &nbsp;<select name="type" class="form-control" style="width: 205px">
                        <option value="">全部</option>
                        <option value="1" <c:if test="${type == 1}">selected="selected" </c:if>>贷款申请</option>
                        <option value="2" <c:if test="${type == 2}">selected="selected" </c:if>>代理申请</option>
                        <option value="3" <c:if test="${type == 3}">selected="selected" </c:if>>伤残预估</option>
                        <option value="4" <c:if test="${type == 4}">selected="selected" </c:if>>其他</option>
                    </select><br>
                        案件状态:<select name="caseState" class="form-control" style="width: 205px">
                        <option value="">全部</option>
                        <option value="1" <c:if test="${caseState == 1}">selected="selected" </c:if>>待接收</option>
                        <option value="2" <c:if test="${caseState == 2}">selected="selected" </c:if>>已接收</option>
                        <option value="3" <c:if test="${caseState == 3}">selected="selected" </c:if>>已预约</option>
                        <option value="4" <c:if test="${caseState == 4}">selected="selected" </c:if>>已放弃</option>
                        <option value="5" <c:if test="${caseState == 5}">selected="selected" </c:if>>已签约</option>
                        <option value="6" <c:if test="${caseState == 6}">selected="selected" </c:if>>待跟进</option>
                        <option value="7" <c:if test="${caseState == 7}">selected="selected" </c:if>>虚假信息</option>
                        <option value="8" <c:if test="${caseState == 8}">selected="selected" </c:if>>已受理</option>
                        <option value="9" <c:if test="${caseState == 9}">selected="selected" </c:if>>已结案</option>
                    </select>
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                        &nbsp; &nbsp; &nbsp; &nbsp;
                        <button id="batchOperateBtn" type="submit" class="btn btn-default" style="width: 102px">查询</button>&nbsp; &nbsp;
                    </div>
                    <div class="btn-group">
                        <%--&nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;--%>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="50">案件编号</th>
                <th width="40">申请人</th>
                <th width="30">电话</th>
                <th width="60">案件类型</th>
                <th width="60">状态</th>
                <th width="150">标题</th>
                <th width="80">分配公司名称</th>
                <th width="70">分配人名称</th>
                <th width="80">创建时间</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${caseCenterInfoList}" var="item">
                <tr>
                    <td>${item.caseNo}</td>
                    <td>${item.caseName}</td>
                    <td>${item.caseTel}</td>
                    <td>
                        <c:if test="${item.type == 1}">贷款申请</c:if>
                        <c:if test="${item.type == 2}">代理申请</c:if>
                        <c:if test="${item.type == 3}">伤残预估</c:if>
                        <c:if test="${item.type == 4}">其他</c:if>
                    </td>
                    <td>${item.caseStateStr}</td>
                    <td>${item.caseTitle}</td>
                    <td>${item.orgName}</td>
                    <td>${item.orgUserName}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
            </c:forEach>
            <%-- <tr>
                 <td colspan="10">
                     <c:if test="${page > 1}">
                         <a href="${ctx}/account/orderInfoList?page=${page-1}">上一页</a>&nbsp;
                     </c:if>
                     <a href="${ctx}/account/orderInfoList?page=${page+1}">下一页</a>
                 </td>
             </tr>--%>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/org/queryCase?id=${id}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>


    /* var getreport = function(id){
     openDialog({
     frame:true,
     title:"查看测算报告",
     height:1000,
     width:800,
     url:"${ctx}/paymentEstimate/paymentEstimateReportList?id="+id
     });
     }*/
</script>
</body>
</html>
