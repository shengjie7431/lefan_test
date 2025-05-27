<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>任务类型列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>任务类型列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                   <div class="form-group">
                       名称:<input name="name" type="text" value="${name}" class="form-control">
                   </div>
                    <div class="form-group">
                        类别:
                        <select name="type"  class="form-control">
                            <option value=""  <c:if test="${type == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${type == '1'}">selected="selected" </c:if> >保险类</option>
                            <option value="2" <c:if test="${type == '2'}">selected="selected" </c:if> >互助类</option>
                        </select>
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
                <th width="100">名称</th>
                <%--<th width="100">分值</th>--%>
                <th width="250">描述</th>
                <th width="100">类别</th>
                <th width="150">创建人</th>
                <th width="150">创建时间</th>
                <th width="100">排序</th>
                <th width="100">颜色</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <%--<td>${item.score}</td>--%>
                    <td>${item.remark}</td>

                    <td>
                        <c:if test="${item.type==1}">保险类</c:if>
                        <c:if test="${item.type==2}">互助类</c:if>
                    </td>
                    <td>${item.createByName}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>${item.sort}</td>
                    <td><span style="background-color: ${item.color}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
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
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&name=${name}&type=${type}" />
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
            height:800,
            width:1200,
            url:"${ctx}/baseSurvey/info?id="+id+"&surveyCode="+surveyCode,
            load:true
        });
    }
</script>
</body>
</html>
