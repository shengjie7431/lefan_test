<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>平台QA问题</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>平台QA问题列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                   <div class="form-group">
                       内容:<input name="question" type="text" value="${question}" class="form-control">
                   </div>
                    <div class="form-group">
                        类型:
                        <select name="questionType"  class="form-control">
                            <option value=""  <c:if test="${questionType == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${questionType == '1'}">selected="selected" </c:if> >系统</option>
                            <option value="2" <c:if test="${questionType == '2'}">selected="selected" </c:if> >用户</option>
                        </select>
                    </div>
                    <div class="form-group">
                        问题状态:
                        <select name="questionState"  class="form-control">
                            <option value=""  <c:if test="${questionState == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${questionState == '1'}">selected="selected" </c:if> >未回复</option>
                            <option value="2" <c:if test="${questionState == '2'}">selected="selected" </c:if> >已回复</option>
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
                <th width="100">类型</th>
                <th width="200">问题</th>
                <th width="200">答案</th>
                <th width="80">提问者</th>
                <th width="120">提问时间</th>
                <th width="80">回答者</th>
                <th width="120">回答时间</th>
                <th width="100">问题状态</th>
                <th width="100">问题标签</th>
                <th width="100">是否置顶</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>
                        <c:if test="${item.questionType ==1}">系统</c:if>
                        <c:if test="${item.questionType ==2}">用户</c:if>
                    </td>
                    <td>${item.question}</td>
                    <td>${item.answer}</td>
                    <td>${item.questionUserName}</td>
                    <td><fmt:formatDate value="${item.questionTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>${item.answerUserName}</td>
                    <td><fmt:formatDate value="${item.answerTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <c:if test="${item.questionState ==1}">未回复</c:if>
                        <c:if test="${item.questionState ==2}">已回复</c:if>
                    </td>
                    <td>
                        <c:forEach items="${surveyQas}" var="dto">
                            <c:if test="${dto.enumCode == item.questionLabel}">
                                ${dto.enumName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:if test="${item.isTop ==0}">否</c:if>
                        <c:if test="${item.isTop ==1}">是</c:if>
                    </td>
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
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&question=${question}&questionState=${questionState}&questionType=${questionType}" />
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
