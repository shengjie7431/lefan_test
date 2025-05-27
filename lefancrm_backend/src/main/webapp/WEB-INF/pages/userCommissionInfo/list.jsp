<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>CC佣金数据列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>CC佣金数据列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/userCommissionInfo/userCommissionInfoList" method="post">
                   <div class="form-group">
                       CC姓名:<input name="userName" type="text" value="${userName}" class="form-control">
                       月:<select id="month" name="month" class="form-control" onchange="monthSelected()">
                              <option value="${month}">${month}</option>
                          </select>
                       年:<select id="year" name="year" class="form-control" onchange="yearSelected()">
                              <option value="${year}">${year}</option>
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
                <th width="100">CC姓名</th>
                <th width="150">月份</th>
                <th width="150">年份</th>
                <th width="150">月新签单</th>
                <th width="150">月预收服务费</th>
                <th width="150">到账金额</th>
                <th width="150">佣金</th>
                <th width="150">应发工资</th>

            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.userName}</td>
                    <td>${item.month}</td>
                    <td>${item.year}</td>
                    <td><a href="javascript:searchmonthNewSignList('${item.userId}','${item.month}','${item.year}');">${item.monthNewSign}</a></td>
                    <td>${item.monthServiceMoney}</td>
                    <td>${item.arrivalMoney}</td>
                    <td><a href="javascript:searchCommissionLogList('${item.userId}','${item.month}','${item.year}');">${item.commissionMoney} </a></td>
                    <td>${item.wages}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/userCommissionInfo/userCommissionInfoList?userId=${userId}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    function searchCommissionLogList(userId,month,year){
        openDialog({
            frame:true,
            title:+year+'年'+month+"月的佣金记录",
            height:500,
            width:1000,
            url:"${ctx}/userCommissionInfo/searchCommissionLogList?userId="+userId+"&month="+month+"&year="+year
        });
    }

    function searchmonthNewSignList(userId,month,year){
        openDialog({
            frame:true,
            title:+year+'年'+month+"月的订单记录",
            height:500,
            width:1000,
            url:"${ctx}/userCommissionInfo/searchMonthNewSignList?userId="+userId+"&month="+month+"&year="+year
        });
    }

    $(function(){
        for( var i=0; i<6; i++ ){
            var yearOld = 2017+i;
            $("#year").append($("<option value="+yearOld+">"+yearOld+"</option>"));
        }
        for( var i=0; i<12; i++ ){
            var monthOld = 1+i;
            $("#month").append($("<option value="+monthOld+">"+monthOld+"</option>"));
        }
    });

    /* 获取选中的下拉框的值 */
    function yearSelected(){
        var valueSel = $("#year").find("option:selected").val();
        var textSel = $("#year").find("option:selected").text();
    }
    function monthSelected(){
        var valueSel = $("#month").find("option:selected").val();
        var textSel = $("#month").find("option:selected").text();
    }
</script>
</body>
</html>
