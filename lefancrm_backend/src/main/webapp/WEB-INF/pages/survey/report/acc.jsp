<%--
  Created by IntelliJ IDEA.
  User: lixianfeng
  Date: 2019/9/10
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <style>
        .table tbody.class-list tr td{
            vertical-align: middle;
            border-right: 1px solid rgb(221, 221, 221);
        }
        .table tbody.class-list tr td:last-of-type{
            border-right: none;
        }
        .table  tr th{
            border-right: 1px solid rgb(221, 221, 221);
        }
    </style>
</head>
<body>
    <div class="main administrator">
        <div class="main-top">
            <h3>对账清单 <small>共<span>${apiRsp.count}</span>个</small></h3>
            <div style="text-align: right;margin-top: -34px;"><button class="btn btn-default"><a onclick="report()" id="a_download">导出</a></button></div>
        </div><!--main-top-->
        <div class="panel panel-info">
            <table class="table">
                <thead>
                    <tr>
                        <th>案件编号</th>
                        <th>被调查人</th>
                        <th>开票金额</th>
                        <th>是否阳性</th>
                        <th>委托时间</th>
                        <th>终审时间</th>
                        <th>案件时效</th>
                        <th>方向编号</th>
                        <th>调查方向</th>
                        <th>方向金额</th>
                    </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${accs}" var="item">
                    <tr>
                        <td <c:if test="${item.directions.size() > 0}">rowspan="${item.directions.size()}"</c:if>>
                            <a onclick="info(${item.id})">${item.surveyCaseNo}</a>
                        </td>
                        <td <c:if test="${item.directions.size() > 0}">rowspan="${item.directions.size()}"</c:if>>${item.surveyPerson}</td>
                        <td <c:if test="${item.directions.size() > 0}">rowspan="${item.directions.size()}"</c:if>>${item.billMoney}</td>
                        <td <c:if test="${item.directions.size() > 0}">rowspan="${item.directions.size()}"</c:if>><c:if test="${item.sun == 0}">否</c:if><c:if test="${item.sun == 1}">是</c:if></td>
                        <td <c:if test="${item.directions.size() > 0}">rowspan="${item.directions.size()}"</c:if>><fmt:formatDate value="${item.entrustTime}" pattern="yyyy-MM-dd"/></td>
                        <td <c:if test="${item.directions.size() > 0}">rowspan="${item.directions.size()}"</c:if>><fmt:formatDate value="${item.surveyEndTime}" pattern="yyyy-MM-dd"/></td>
                        <td <c:if test="${item.directions.size() > 0}">rowspan="${item.directions.size()}"</c:if>>${item.days}天</td>
                        <td>1</td>
                        <td><c:if test="${item.directions.size()>0}">${item.directions.get(0).directionName}</c:if></td>
                        <td><c:if test="${item.directions.size()>0}">${item.directions.get(0).entrustMoney}</c:if></td>
                    </tr>
                    <c:forEach items="${item.directions}" var="direction" varStatus="st">
                        <c:if test="${st.index > 0}">
                            <tr>
                                <td>${st.index + 1}</td>
                                <td>${direction.directionName}</td>
                                <td>${direction.entrustMoney}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="main-bottom">
            <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
                <jsp:param name="paginationObjectName" value="apiRsp" />
                <jsp:param name="pageNoName" value="" />
                <jsp:param name="requestUrl" value="${ctx}/survey/report/acc?menuCode=entrust&startTime=${startTime}&endTime=${endTime}&entrustOrgId=${entrustOrgId}" />
                <jsp:param name="refreshDiv" value="" />
            </jsp:include>
        </div>
    </div>
    <%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
</body>
<script>
    function report(){
        var href = "${ctx}/survey/case/export?menuCode=account-list&reportType=entrust&entrustOrgId=${entrustOrgId}&bookType=each&entrReportStateDate=${startTime}&entrReportEndDate=${endTime}";
        $("#a_download").attr("href",href);
    }

    var info = function(surveyInfoId){
        var url ="${ctx}/survey/case/info?id=" + surveyInfoId + "&menuCode=my-list";
        var title="案件详情";
        parent.parent.addTab(title,url,true);
        /*openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:url,
            load:true
        });*/
    }
</script>
</html>
