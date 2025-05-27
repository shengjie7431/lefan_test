<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>邮箱模板</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>邮箱模板列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                   <div class="form-group">
                       邮箱用户名称:<input name="emailUserName" type="text" value="${emailUserName}" class="form-control">
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
                <th width="100">邮箱用户名称</th>
                <th width="100">邮箱地址</th>
                <th width="100">邮箱授权密码</th>
                <th width="100">邮箱密码</th>
                <th width="150">创建人</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.emailUserName}</td>
                    <td>${item.emailAddress}</td>
                    <td>${item.emailAuthPassword}</td>
                    <td>${item.emailPassword}</td>
                    <td>${item.createBy}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <a href="javascript:oper('${item.id}','${item.emailUserName}','emailInfoOrg','1000','设置委托方');">设置委托方</a>
                        <a href="javascript:oper('${item.id}','','emailInfoOrg','1100','名下委托方');">名下委托方</a>
                        <a href="javascript:info('${item.id}','${surveyCode}');">修改</a>
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
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&emailUserName=${emailUserName}" />
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
            title:"修改",
            height:650,
            width:1000,
            url:"${ctx}/baseSurvey/edit?id="+id+"&surveyCode="+surveyCode,
            load:true
        });
    }

    var oper = function(modelId,modelName,surveyCode,btnCode,title){
        openDialog({
            frame:true,
            title:title,
            height:650,
            width:1000,
            url:"${ctx}/baseSurvey/popup?modelId="+modelId+"&surveyCode="+surveyCode+"&btnCode="+btnCode+"&modelName="+modelName,
            load:true
        });
    }

</script>
</body>
</html>
