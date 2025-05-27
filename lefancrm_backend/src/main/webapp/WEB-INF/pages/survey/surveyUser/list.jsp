<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>平台终审人员列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>平台终审人员列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                    <div class="form-group">
                        人员名称:<input name="userName" type="text" value="${userName}" class="form-control">
                    </div>
                    <div class="form-group">
                        联系电话:<input name="userTel" type="text" value="${userTel}" class="form-control">
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
                <th width="152">人员名称</th>
                <th width="150">联系电话</th>
                <th width="300">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.userName}</td>
                    <td>${item.userTel}</td>
                    <td>
                        <a href="javascript:role('${item.userId}','${surveyCode}','1000','设置委托方');">设置委托方</a>
                        <a href="javascript:role('${item.userId}','${surveyCode}','1100','名下委托方');">名下委托方</a>
                        <a href="javascript:role('${item.userId}','${surveyCode}','2000','设置调查方');">设置调查方</a>
                        <a href="javascript:role('${item.userId}','${surveyCode}','2100','名下调查方');">名下调查方</a>
                        <a href="javascript:role('${item.userId}','${surveyCode}','2200','手写签名');">手写签名</a>
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
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&userTel=${userTel}&userName=${userName}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    var role = function(userId,surveyCode,btnCode,title){
        openDialog({
            frame:true,
            title:title,
            height:650,
            width:1000,
            url:"${ctx}/baseSurvey/popup?userId="+userId+"&surveyCode="+surveyCode+"&btnCode="+btnCode,
            load:true
        });
    }
</script>
</body>
</html>
