<%--
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title> 物流详情</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">

    <div class="panel panel-info">



        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">物流单号</th>
                <th width="150">物流公司</th>
                <th width="150">订单编号</th>
                <th width="150">联系人</th>
                <th width="150">联系电话</th>
                <th width="150">送货地址</th>
                <th width="150">商品名称</th>
                <th width="150">商品数量</th>
                <th width="150">物流状态</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.logisticCode}</td>
                    <td>${item.logisticCompany}</td>
                    <td> ${item.orderCode}</td>
                    <td> ${item.linkMan}</td>
                    <td> ${item.linkTel}</td>
                    <td> ${item.userAddress}</td>
                    <td> ${item.productName}</td>
                    <td> ${item.productNum}</td>
                    <td>
                        <c:if test="${item.state == 1}">配送中</c:if>
                        <c:if test="${item.state == 2}">配送完成</c:if>
                    </td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <c:if test="${item.state == 1}">
                            <a  href="javascript:void(0)" onclick="updateState(${item.id},2,${item.orderId})">配送成功</a>
                        </c:if>
                        <a  href="javascript:void(0)" onclick="del(${item.id},1)">删除</a>
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
            <jsp:param name="requestUrl" value="${ctx}/logisticOrder/list?orderCode=${orderCode}&logisticCode=${logisticCode}&linkTel=${linkTel}&linkMan=${linkMan}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->
</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

</body>
</html>
--%>




<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title> 物流详情</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">

    <div class="panel panel-info">



        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">物流单号</th>
                <th width="150">物流公司</th>
                <th width="150">订单编号</th>
                <th width="150">联系人</th>
                <th width="150">联系电话</th>
                <th width="150">送货地址</th>
                <th width="150">商品名称</th>
                <th width="150">商品数量</th>
                <th width="150">物流状态</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <%--<c:forEach items="${apiRsp.results}" var="item">--%>
            <tr>
                <td>${apiRsp.results.logisticCode}</td>
                <td>${apiRsp.results.logisticCompany}</td>
                <td> ${apiRsp.results.orderCode}</td>
                <td> ${apiRsp.results.linkMan}</td>
                <td> ${apiRsp.results.linkTel}</td>
                <td> ${apiRsp.results.userAddress}</td>
                <td> ${apiRsp.results.productName}</td>
                <td> ${apiRsp.results.productNum}</td>
                <td>
                    <c:if test="${apiRsp.results.state == 1}">配送中</c:if>
                    <c:if test="${apiRsp.results.state == 2}">配送完成</c:if>
                </td>
                <td><fmt:formatDate value="${apiRsp.results.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>
                    <c:if test="${apiRsp.results.state == 1}">
                        <a  href="javascript:void(0)" onclick="updateState(${apiRsp.results.id

                                },2,${apiRsp.results.orderId})">配送成功</a>
                    </c:if>
                   <%-- <a  href="javascript:void(0)" onclick="del(${apiRsp.results.id

                            },1)">删除</a>--%>
                </td>
            </tr>
            <%--</c:forEach>--%>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/logisticOrder/list?orderCode=${orderCode}&logisticCode=${logisticCode}&linkTel=${linkTel}&linkMan=${linkMan}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->
</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

</body>
</html>