<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>加盟机构结算记录列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>加盟机构结算记录列表<small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/account/accountRecordList" method="post">
                    <div class="form-group">

                    </div>
                   <%-- <div class="btn-group">
                       &lt;%&ndash; <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&ndash;%&gt;
                       &nbsp; &nbsp; &nbsp; &nbsp; <button onclick="addinfo()" type="button" class="btn btn-default">添加用户案例信息</button>
                    </div>--%>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">交易方名称</th>
                <th width="150">交易方电话</th>
                <th width="150">付款方账号</th>
                <th width="150">收款方账号</th>
                <th width="150">总金额</th>
                <th width="150">是否付款</th>
                <th width="150">付款时间</th>
                <th width="150">结算状态</th>
                <th width="150">备注</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
               <tr>
                    <td>${item.payName}</td>
                    <td>${item.payTel}</td>
                    <td>${item.payAccount}</td>
                    <td>${item.receiveAccount}</td>
                    <td>${item.totalPrice}</td>
                    <td>
                        <c:if test="${item.payState == 0}">未付款</c:if>
                        <c:if test="${item.payState == 1}">已结付款</c:if>
                    </td>

                    <td>
                        <fmt:formatDate value="${item.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <c:if test="${item.accountState == 0}">待结算</c:if>
                        <c:if test="${item.accountState == 1}">已结算</c:if>
                    </td>
                    <td>${item.accountDesc}
                    </td>
                   <td><c:if test="${item.accountState == 0}"><a href="javascript:updateAccountState('${item.id}');">结算</a></c:if>
                       <a href="javascript:getAccountRecordDetail('${item.id}');">结算单明细</a>
                   </td>
                </tr>
            </c:forEach>
           <%-- <tr>
                <td colspan="10">
                    <c:if test="${page > 1}">
                        <a href="${ctx}/account/accountRecordList?page=${page-1}">上一页</a>&nbsp;
                    </c:if>
                    <a href="${ctx}/account/accountRecordList?page=${page+1}">下一页</a>
                </td>
            </tr>--%>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/account/accountRecordList" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>


    function updateAccountState(id){
        ajaxSubmit("${ctx}/account/updateAccountState",{"accountId":id},reload,"结算成功","确认结算吗？");
    }
    var getAccountRecordDetail = function(id){
        openDialog({
            frame:true,
            title:"结算单明细",
            height:1000,
            width:800,
            url:"${ctx}/account/accountRecordDetails?accountsId="+id
        });
    }


</script>
</body>
</html>
