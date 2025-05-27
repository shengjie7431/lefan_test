<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>调查费用明细</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>调查费用明细 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
<%--                <button class="btn btn-default"><a href="${ctx}/survey/case/exportSurveyMoneyDetail?searchType=${searchType}&orgId=${orgId}&startDate=${startDate}&endDate=${endDate}&detailType=1&export=1&sourceSupportType=${sourceSupportType}&dateType=1">导出</a></button>--%>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
<%--                <th width="100">调查编号</th>--%>
                <th width="100">案件编号</th>
                <c:if test="${detailType =='surveyMoneyDetail'}">
                    <th width="100">调查机构</th>
                </c:if>
                <th width="100">被调查人</th>
<%--                <th width="100">联系方式</th>--%>
                <th width="100">委托公司</th>
                <c:if test="${detailType =='surveyMoneyDetail'}">
                    <th width="50">调查金额</th>
                    <th width="50">分派机构时间</th>
                    <th width="50">机构提交时间</th>
                    <th width="50">机构截止时间</th>
                    <th width="50">区域类别</th>
                    <th width="50">机构调查时效</th>
                    <th width="50">考核时效</th>
                    <th width="50">超期天数</th>
                    <th width="50">案件委托时间</th>
                    <th width="50">平台复审时间</th>
                    <th width="50">保司审核时间</th>
<%--                    <th width="50">案件时效</th>--%>
                </c:if>
                <c:if test="${detailType =='entrustMoneyDetail'}">
                    <th width="50">委托方金额</th>
                </c:if>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
<%--                    <td><a href="javascript:void(0);" onclick="info(${item.surveyInvestigatorCaseId})">${item.surveyNo}</a></td>--%>
<%--                    <td>${item.surveyNo}</td>--%>
                    <td>${item.surveyCaseNo}</td>
                    <c:if test="${detailType =='surveyMoneyDetail'}">
                        <td>${item.orgName}</td>
                    </c:if>
                    <td>${item.surveyPerson}</td>
<%--                    <td>${item.surveryPersonTel}</td>--%>
                    <td>${item.entrustOrgName}</td>
                    <c:if test="${detailType == 'surveyMoneyDetail'}">
                        <td>${item.money}</td>
                        <td>${item.orgCreateTimeStr}</td>
                        <td>${item.orgStartTimeStr}</td>
                        <td>${item.orgEndTimeStr}</td>
                        <td>${item.areaName}</td>
                        <td>
                            时效：${item.orgDays}天
                        </td>
                        <td>
                            时效：${item.agingCheck}天
                        </td>
                        <td>
                            时效：${item.agingOver}天
                        </td>
                        <td>${item.caseStartTimeStr}</td>
                        <td>${item.surveyDateStr}</td>
                        <td>${item.entrustDateStr}</td>
<%--                        <td>--%>
<%--                            <c:if test="${item.caseDays >= 0}">--%>
<%--                                时效：${item.caseDays1}天--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${item.caseDays < 0}">--%>
<%--                                超时：${item.caseDays1}天--%>
<%--                            </c:if>--%>
<%--                        </td>--%>
                    </c:if>
                    <c:if test="${detailType =='entrustMoneyDetail'}">
                        <td>${item.entrustMoney}</td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/survey/case/surveyMoneyDetail?detailType=${detailType}&searchType=${searchType}&orgId=${orgId}&startDate=${startDate}&endDate=${endDate}&dateType=${dateType}&sourceSupportType=${sourceSupportType}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script>

    $(document).ready(function(){

    })

    var info = function(id){
        openDialog({
            frame:true,
            title:"详情",
            height:600,
            width:900,
            url:"${ctx}/survey/case/sic/info?id=" + id + "&menuCode=surveyMoney",
            load:true
        });
    }
</script>
</body>
</html>
