<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>理赔测算历史记录</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>理赔测算历史记录 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/case/selectPaymentEstimateApply?type=${caseType}&caseId=${caseId}" method="post">
                        <%----%>
                        <div class="form-group">
                            用户姓名: <input name="userName" type="text"  value="${userName}" class="form-control">
                        </div>
                        <div class="form-group">
                            用户手机号: <input name="userPhone" type="text"  value="${userPhone}" class="form-control">
                        </div>
                        <div class="btn-group">
                            <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                        </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">用户姓名</th>
                <th width="150">手机号码</th>
               <%-- <th width="150">推广人姓名</th>--%>
                <th width="150">我方身份</th>
                <th width="150">对方交通状态</th>
                <th width="150">我方交通状态</th>
                <th width="150">我方事故责任</th>
                <th width="150">已用医疗费</th>
                <th width="150">案件类型</th>
                <th width="200">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
           <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.userName}</td>
                    <td>${item.userPhone}</td>
                    <%--<td>${item.promoterName}</td>--%>
                    <td>
                        <c:if test="${item.myStatus == 1}">肇事方</c:if>
                        <c:if test="${item.myStatus == 2}">受害方</c:if>
                    </td>
                    <td>
                        <c:if test="${item.otherTarfficStatus == 1}">机动车</c:if>
                        <c:if test="${item.otherTarfficStatus == 2}">非机动车</c:if>
                        <c:if test="${item.otherTarfficStatus == 3}">行人</c:if>
                    </td>
                    <td>
                        <c:if test="${item.myTarfficStatus == 1}">机动车</c:if>
                        <c:if test="${item.myTarfficStatus == 2}">非机动车</c:if>
                        <c:if test="${item.myTarfficStatus == 3}">行人</c:if>
                    </td>
                    <td>
                        <c:if test="${aitem.myAccidentLiability == 1}">全部责任</c:if>
                        <c:if test="${item.myAccidentLiability == 2}">主要责任</c:if>
                        <c:if test="${item.myAccidentLiability == 3}">同等责任</c:if>
                        <c:if test="${item.myAccidentLiability == 4}">次要责任</c:if>
                        <c:if test="${item.myAccidentLiability == 5}">无责任</c:if>
                        <c:if test="${item.myAccidentLiability == 6}">责任无法认定</c:if>
                    </td>
                    <td>${item.medicalFee}</td>
                    <td>
                        <c:if test="${item.caseType == 1}">医疗费垫付</c:if>
                        <c:if test="${item.caseType == 2}">理赔款垫付</c:if>
                    </td>
                    <%--<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>--%>
                    <td> <a href="javascript:getreport('${item.id}');">查看测算报告</a>
                        <%--<a href="javascript:getInquiry('${item.id}');">查看报价</a>
                        <c:if test="${item.turnStatus == 0}">
                            <a href="javascript:forward('${item.id}','${item.userName}','${item.userPhone}',
                                '${item.accidentProvince}','${item.accidentProvinceId}','${item.accidentCity}','${item.accidentCityId}','${item.accidentDistrict}',
                                '${item.accidentDistrictId}','${item.accidentAddress}',null,'${item.userId}')">转办案件中心</a>
                        </c:if>--%>
                    </td>
                </tr>
           </c:forEach>
            <%--<tr>
                <td colspan="10">
                    <c:if test="${page > 1}">
                        <a href="${ctx}/paymentEstimate/paymentEstimateApplyList?page=${page-1}">上一页</a>&nbsp;
                    </c:if>
                    <a href="${ctx}/paymentEstimate/paymentEstimateApplyList?page=${page+1}">下一页</a>
                </td>
            </tr>--%>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/case/selectPaymentEstimateApply?type=${caseType}&caseId=${caseId}&userName=${userName}&userPhone=${userPhone}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>


    var getreport = function(id){
        openDialog({
            frame:true,
            title:"查看测算报告",
            height:500,
            width:900,
            url:"${ctx}/case/risk/paymentEstimateReportList?id="+id

        });
    }
    var getInquiry = function(id){
        openDialog({
            frame:true,
            title:"查看报价",
            height:500,
            width:900,
            url:"${ctx}/paymentEstimate/paymentEstimateInquiry?id="+id
        });
    }
    function forward(id,userName,phone,caseProvince,caseProvinceId,caseCity,caseCityId,caseDistrict,caseDistrictId,caseAddress,dangerTime,userId){
        openDialog({
            frame:true,
            title:"转办案件中心",
            height:500,
            width:1000,
            url:"${ctx}/caseApply/forward?id="+id+"&userName="+userName+"&phone="+phone+"&caseProvince="+caseProvince+"&caseProvinceId="+caseProvinceId+"&" +
                    "caseCity="+caseCity+"&caseCityId="+caseCityId+"&caseDistrict="+caseDistrict+"&caseDistrictId="+caseDistrictId+"&caseAddress="+caseAddress+"" +
                    "&dangerTime="+dangerTime+"&isDangerTime="+true+"&type="+2+"&userId="+userId
        });
    }
</script>
</body>
</html>
