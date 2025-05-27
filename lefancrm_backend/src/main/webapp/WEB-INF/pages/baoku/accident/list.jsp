
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>意外险列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>意外险列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

       <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baokuAccident/list" method="post">
                    <input type="hidden" name="orderCode" id="orderCode" value="${orderCode}"/>
                    <div class="form-group">
                        电话: <input name="mobilePhone" type="text"  value="${mobilePhone}" class="form-control">
                    </div>
                    <div class="form-group">
                        投保人姓名: <input name="policyHolder" type="text"  value="${policyHolder}" class="form-control">
                    </div>
                    <div class="form-group">
                        被保人姓名: <input name="insuredName" type="text"  value="${insuredName}" class="form-control">
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <%--<th width="100">保酷产品编号</th>--%>
                <th width="100">投保人姓名</th>
                <th width="100">投保人证件号</th>
                <th width="150">投保人证件类型</th>
                <th width="100">被保人姓名</th>
                <th width="100">被保人证件号</th>
               <%-- <th width="100">被保人身份证有效性</th>--%>
               <%-- <th width="100">被保人生日</th>--%>
                <th width="100">起保日期</th>
                <%--<th width="50">保险份数</th>--%>
                <th width="100">手机号码</th>
                <%--<th width="50">备注</th>--%>
                <th width="100">职业</th>
               <%-- <th width="100">创建时间</th>--%>
                <th width="100">保险保单号</th>
               <%-- <th width="100">保险凭证</th>--%>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                   <%-- <td>${item.productCode}</td>--%>
                    <td>${item.policyHolder}</td>
                    <td> ${item.policyHolderId}</td>
                    <td> ${item.policyHolderIdType}</td>
                    <td> ${item.insuredName}</td>
                    <td> ${item.insuredNameId}</td>
                   <%-- <td> ${item.insuredNameIdType}</td>--%>
                   <%-- <td><fmt:formatDate value="${item.insuredNameBirthday}" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
                    <td><fmt:formatDate value="${item.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <%--<td> ${item.insuredCount}</td>--%>
                    <td> ${item.mobilePhone}</td>
                    <%--<td> ${item.remark}</td>--%>
                    <td>
                        <c:if test="${item.occupationCategory == 0}">一般职业</c:if>
                        <c:if test="${item.occupationCategory == 1}">农牧业</c:if>
                        <c:if test="${item.occupationCategory == 2}">渔业</c:if>
                        <c:if test="${item.occupationCategory == 3}">木材深林业</c:if>
                        <c:if test="${item.occupationCategory == 4}">矿业采石业</c:if>
                        <c:if test="${item.occupationCategory == 5}">交通运输业</c:if>
                        <c:if test="${item.occupationCategory == 6}">餐旅业</c:if>
                        <c:if test="${item.occupationCategory == 7}">建筑工程业</c:if>
                        <c:if test="${item.occupationCategory == 8}">制造业</c:if>
                        <c:if test="${item.occupationCategory == 9}">新闻出版广告业</c:if>
                        <c:if test="${item.occupationCategory == 10}">卫生</c:if>
                        <c:if test="${item.occupationCategory == 11}">娱乐业</c:if>
                        <c:if test="${item.occupationCategory == 12}">文教</c:if>
                        <c:if test="${item.occupationCategory == 13}">宗教</c:if>
                        <c:if test="${item.occupationCategory == 14}">公共事业</c:if>
                        <c:if test="${item.occupationCategory == 15}">商业</c:if>
                        <c:if test="${item.occupationCategory == 16}">金融保险业</c:if>
                        <c:if test="${item.occupationCategory == 17}">服务业</c:if>
                        <c:if test="${item.occupationCategory == 18}">家庭管理</c:if>
                        <c:if test="${item.occupationCategory == 19}">治安人员</c:if>
                        <c:if test="${item.occupationCategory == 20}">体育</c:if>
                        <c:if test="${item.occupationCategory == 21}">其他</c:if>
                    </td>
                    <%--<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
                    <td> ${item.invoiceNumber}</td>
                   <%-- <td> ${item.insuranceCertificateUrl}</td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/baokuAccident/list?orderCode=${orderCode}&mobilePhone=${mobilePhone}&policyHolder=${policyHolder}&insuredName=${insuredName}&" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

</body>
</html>


