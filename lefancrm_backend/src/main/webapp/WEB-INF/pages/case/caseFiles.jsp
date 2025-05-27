<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<jsp:useBean id="dateValue" class="java.util.Date"/>
<!DOCTYPE html>
<html>
<head>
    <title>案件详情</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body style="background-color:#F5F5F5;">
<div class="main administrator">
    <div class="main-top">
        <%--<h3>案件详情</h3>--%>
    </div><!--main-top-->



        <div style="padding-left: 20px;padding-top: 20px;">
            <c:forEach items="${caseFollowInfoFiles}" var="item" varStatus="index">
                <a href="http://openapi.shlefan.com/pic/images${item.filePath}" target="_blank"><img style="width: 200px;height: 200px;padding-top: 5px;padding-left: 5px;" src="http://openapi.shlefan.com/pic/images${item.filePath}"></a>
            </c:forEach>
        </div>
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/system/admin/list" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/


  /*  var commentDetail = function(commentid){
        openDialog({
            frame:true,
            title:"查看留言信息",
            height:600,
            width:500,
            url:"${ctx}/comment/detail?id="+commentid
        });
    }*/
</script>
</body>
</html>
