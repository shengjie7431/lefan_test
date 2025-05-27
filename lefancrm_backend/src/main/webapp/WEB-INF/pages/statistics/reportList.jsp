<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title> 报表统计</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3> 报表统计 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/statistics/report" method="post">
                    <div class="form-group">
                        机构:<input name="orgName" type="text" value="${orgName}" class="form-control">
                        开始时间:<input name="startTime" type="text" value="${startTime}" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        结束时间:<input name="endTime" type="text" value="${endTime}" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
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
                <th width="150">机构</th>
                <th width="150">推广员注册数</th>
                <th width="150">案源有效数</th>
                <th width="150">案件成交数</th>
                <th width="150">理赔款测算数</th>
                <th width="150">伤残预估数</th>
                <th width="150">医疗垫付数</th>
                <th width="150">理赔垫付数</th>
                <th width="150">代办理赔数</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.orgName}</td>
                    <td>${item.promoteCount}</td>
                    <td>${item.paymentCount + item.invaliCount + item.agentCount +item.loanTypeCount +item.loanCount}</td>
                    <td>${item.caseGoodCount}</td>
                    <td>${item.paymentCount}</td>
                    <td>${item.invaliCount}</td>
                    <td>${item.loanCount}</td>
                    <td>${item.loanTypeCount}</td>
                    <td>${item.agentCount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/statistics/report" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    function enableUser(id){
        ajaxSubmit("${ctx}/user/changeStatus",{"userId":id, "userState":0},reload,"启用成功","确认启用？");
    }
    function lockUser(id){
        ajaxSubmit("${ctx}/user/changeStatus",{"userId":id, "userState":1},reload,"冻结成功","确认冻结？");
    }

    var insUserAddressList = function(userId){
        openDialog({
            frame:true,
            title:"保险员辖区列表",
            height:500,
            width:800,
            url:"${ctx}/insUserAddress/insUserAddressList?userId="+userId
        });
    }
</script>
</body>
</html>
