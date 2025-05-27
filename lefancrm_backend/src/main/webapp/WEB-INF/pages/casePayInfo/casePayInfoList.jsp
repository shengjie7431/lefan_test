<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>待支付项目列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>待支付项目列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/casePayInfo/casePayInfoList?pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <div class="form-group">
                       伤者姓名:<input name="userName" type="text" value="${userName}" class="form-control">
                    </div>
                    <div class="form-group">
                        案件标题:<input name="caseTitle" type="text" value="${caseTitle}" class="form-control">
                    </div>
                    <div class="form-group">
                        支付状态:
                        <select name="payState"  class="form-control">
                            <option value="" <c:if test="${payState == ''}">selected="selected" </c:if> >全部</option>
                            <option value="0" <c:if test="${payState == '0'}">selected="selected" </c:if> >待支付</option>
                            <option value="1" <c:if test="${payState == '1'}">selected="selected" </c:if> >支付中</option>
                            <option value="2" <c:if test="${payState == '2'}">selected="selected" </c:if> >已支付</option>
                        </select>
                    </div>
                    <div class="form-group">
                        审核状态:
                        <select name="auditState"  class="form-control">
                            <c:if test="${caseUserRole.isFinancial}">
                                <option value="" <c:if test="${auditState == ''}">selected="selected" </c:if> >全部</option>
                                <option value="1" <c:if test="${auditState == '1'}">selected="selected" </c:if> >待提交</option>
                                <option value="3" <c:if test="${auditState == '3'}">selected="selected" </c:if> >审核成功</option>
                                <option value="4" <c:if test="${auditState == '4'}">selected="selected" </c:if> >已驳回</option>
                            </c:if>
                            <option value="2" <c:if test="${auditState == '2'}">selected="selected" </c:if> >审核中</option>
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
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="200">案件标题</th>
                <th width="100">伤者姓名</th>
                <th width="100">支付金额</th>
                <th width="100">费用名称</th>
                <th width="100">账户名</th>
                <th width="100">开户行</th>
                <th width="150">卡号</th>
                <th width="100">审核状态</th>
                <th width="100">支付状态</th>
                <th width="100">支付人名称</th>
                <th width="150">支付时间</th>
                <th width="150">创建时间</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.caseTitle}</td>
                    <td>${item.userName}</td>
                    <td>${item.payMoney}</td>
                    <td>${item.payName}</td>
                    <td>${item.accountName}</td>
                    <td>${item.bankName}</td>
                    <td>${item.cardNo}</td>
                    <td>
                        <c:if test="${item.auditState == 1 || item.auditState ==null }">待提交</c:if>
                        <c:if test="${item.auditState == 2}">审核中</c:if>
                        <c:if test="${item.auditState == 3}">审核成功</c:if>
                        <c:if test="${item.auditState == 4}">已驳回</c:if>
                    </td>
                    <td>
                        <c:if test="${item.payState == 0}">待支付</c:if>
                        <c:if test="${item.payState == 1}">支付中</c:if>
                        <c:if test="${item.payState == 2}">已支付</c:if>
                    </td>
                    <td>${item.operatorName}</td>
                    <td>
                        <fmt:formatDate value="${item.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td><a href="javascript:casePayInfoView('${item.id}');">查看</a>
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
            <jsp:param name="requestUrl" value="${ctx}/casePayInfo/casePayInfoList?userName=${userName}&caseTitle=${caseTitle}&payState=${payState}&auditState=${auditState}&isTestcase=${isTestcase}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var casePayInfoView = function(id){
        openDialog({
            frame:true,
            title:"待支付项目",
            height:500,
            width:1000,
            url:"${ctx}/casePayInfo/casePayInfoView?id="+id,
            load:true
        });
    }

</script>
</body>
</html>
