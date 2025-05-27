<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案源分值</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>案源分值 <small>共<span>${sourceList.size()}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/survey/case/sic/sourceScore" method="post">
                    <div class="form-group">
                        保司审核时间：
                        <input name="startDate" type="text" value="${searchStartDate}" style="width: 150px" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        <span>--</span>
                        <input name="endDate" type="text" value="${searchEndDate}" style="width: 150px" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <div class="form-group">
                        按月查询
                        <input name="searchMonth" type="text" value="${searchMonth}" style="width: 150px" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button class="btn btn-default"><a href="${ctx}/survey/case/sic/exportSourceScore?startDate=${searchStartDate}&endDate=${searchEndDate}&searchMonth=${searchMonth}">导出</a></button>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">案源机构</th>
                <th width="100">分值</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${sourceList}" var="item">
                    <tr>
                        <td>${item.name}</td>
                        <td><a href="javascript:void(0);" onclick="details(${item.type})">${item.value == null ? 0 : item.value}</a></td>
                    </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

<div class="main-bottom">

    </div><!--main-bottom-->
</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script>
    function details(type){
        openDialog({
            frame:true,
            title:"详情",
            height:700,
            width:1000,
            url:"${ctx}/survey/case/sic/sourceScoreDetail?searchSourceyType=" + type + "&searchStartDate=${searchStartDate}&searchEndDate=${searchEndDate}&searchMonth=${searchMonth}",
            load:true
        });
    }
</script>
</body>
</html>
