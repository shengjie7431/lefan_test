<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>乐凡币规则</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>乐凡币规则列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                   <div class="form-group">
                       说明:<input name="remark" type="text" value="${remark}" class="form-control">
                   </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="add('${surveyCode}')" type="button" class="btn btn-default">添加</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">规则code</th>
                <th width="100">规则说明</th>
                <th width="100">乐凡币数量</th>
                <th width="150">创建人</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.code}</td>
                    <td>${item.remark}</td>
                    <td>${item.num}</td>
                    <td>${item.createByName}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <a href="javascript:info('${item.id}','${surveyCode}');">详情</a>
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
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&remark=${remark}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    var add = function(surveyCode){
        openDialog({
            frame:true,
            title:"添加",
            height:500,
            width:800,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode
        });
    }

    var info = function(id,surveyCode){
        openDialog({
            frame:true,
            title:"详情",
            height:650,
            width:1000,
            url:"${ctx}/baseSurvey/info?id="+id+"&surveyCode="+surveyCode,
            load:true
        });
    }
</script>
</body>
</html>
