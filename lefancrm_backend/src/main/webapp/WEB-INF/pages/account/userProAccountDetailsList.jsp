<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>推广用户收支明细<small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin" style="color: red;">
                用户账户总金额：${userAccountDto.userRecharge}&nbsp;&nbsp;&nbsp;&nbsp;
                可提现金额：${userAccountDto.withdrawDeposit}&nbsp;&nbsp;&nbsp;&nbsp;
                已经提现金额：${userAccountDto.isWithdrawDeposit}
               <%-- <form class="form-inline" role="form" action="${ctx}/account/accountRecordList" method="post">
                    <div class="form-group">

                    </div>

                </form>--%>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">收支说明</th>
                <th width="150">金额</th>
                <th width="150">产生时间</th>
                <th width="150">案件编号</th>
                <th width="150">案件名称</th>
                <th width="150">电话号码</th>

              <%--  <th width="150">操作</th>--%>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
               <tr>
                    <td>${item.title}</td>
                    <td>${item.money}</td>
                   <td>
                       <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                   </td>
                    <td>${item.caseNo}</td>
                    <td>${item.caseName}</td>
                    <td>${item.userTel}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/account/userProAccountDetailsList" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>


    /*function updateAccountState(id){
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
    }*/


</script>
</body>
</html>
