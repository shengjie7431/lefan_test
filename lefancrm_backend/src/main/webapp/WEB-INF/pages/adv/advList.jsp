<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>广告列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
	<div class="main-top">    
            <h3>广告列表 <small>共<span>${apiRsp.count}</span>个</small></h3>              
    </div><!--main-top-->

	<div class="panel panel-info">
		  <div class="panel-heading">
		  		<div class="pin">
                    <form class="form-inline" role="form" action="${ctx}/adv/advList" method="post">
                        <div class="form-group">
                            名称:<input name="adTitle" type="text" value="${adTitle}" class="form-control">
                            类型: <select id="adCode" name="adCode" class="form-control">
                            <option value="">全部</option>
                            <option value="banner" <c:if test="${adCode == 'banner'}">selected </c:if>>首页banner</option>
                            <option value="company" <c:if test="${adCode == 'company'}">selected </c:if>>公司服务介绍</option>
                            <option value="scenarios" <c:if test="${adCode == 'scenarios'}">selected </c:if>>案例</option>
                        </select>
                        </div>
                        <div class="btn-group">
                            &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                            &nbsp; &nbsp;<button onclick="javascript:createAdv();" type="button" class="btn btn-default">添加广告</button>&nbsp; &nbsp;
                        </div>
                    </form>
		        </div>
		  </div>
          <table class="table table-hover">
            <thead>
              <tr>
                <th>ID</th>
              	<th>封面</th>
                <th>广告位</th>
                <th>名称</th>
                <th>链接</th>
                <th>顺序号</th>
                <th class="th-time">创建时间</th>
              </tr>
            </thead>
			  <c:set var="pagingUrl" value="${ctx}/adv/advList?"/>
			  <c:set var="nextUrl" value=""/>
			  <c:if test="${not empty apiRsp and apiRsp.curPage!=apiRsp.totalPages}">
			  <c:set var="nextUrl" value="${pagingUrl}&page=${apiRsp.curPage+1}"/>
			  </c:if>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
              <tr>
                <td>${item.id}</td>
                <td><a href="${item.adPic}" target="_blank"><img src="${item.adPic}" width="75px" height="75px"></a></td>
                <td>
                	<c:choose>
                		<c:when test="${item.adCode eq 'mall-home'}">商城首页广告</c:when>
                		<c:when test="${item.adCode eq 'info-ad'}">资讯首页广告</c:when>
                		<c:when test="${item.adCode eq 'outdoorActivity-home'}">户外活动首页广告</c:when>
                		<c:otherwise>${item.adCode}</c:otherwise>
                	</c:choose>
                </td>
                <td>${item.adTitle}
                   	<span class="row-actions">
                		<a href="javascript:editAdv('${item.id}');"><span class="glyphicon glyphicon-edit"></span>编辑</a>
                		<a href="javascript:unpublishAdv('${item.id}');"><span class="glyphicon glyphicon-remove"></span>下架</a>
                	</span>
                </td>
                <td><a href="${item.adHerf}" target="_blank">${item.adHerf}</a></td>
                <td>${item.adOrder}</td>
                <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
              </tr>
            </c:forEach>  
            </tbody>
          </table>
	</div><!--panel-info-->
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/adv/advList" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div>
</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript">
/*function createAdv(){
	parent.addTab("添加广告","${ctx}/adv/createAdv");
}*/
<%--function editAdv(id){--%>
	<%--parent.addTab("编辑广告"+id,"${ctx}/adv/editAdv/"+id);--%>
<%--}--%>
function unpublishAdv(id){
	ajaxSubmit("${ctx}/adv/changeStatus",{"advId":id, "deleteFlag":1},reload,"下架成功","确认下架？");
}

var createAdv = function(){
    openDialog({
        frame:true,
        title:"添加广告",
        height:500,
        width:600,
        url:"${ctx}/adv/createAdv"
    });
}
var editAdv = function(id){
    openDialog({
        frame:true,
        title:"编辑广告",
        height:500,
        width:600,
        url:"${ctx}/adv/editAdv/"+id
    });
}
$(function(){
});
</script>
</body>
</html>
