<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>核保机构列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>核保机构列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/hb/hbOrgList" method="post">
                   <div class="form-group">
                       机构名称:<input name="hbOrgName" type="text" value="${hbOrgName}" class="form-control">
                   </div>
                    <div class="form-group">
                        机构状态:
                        <select name="hbOrgStatus"  class="form-control">
                            <option value=""  >全部</option>
                            <option <c:if test="${hbOrgStatus == 1}">selected="selected" </c:if> value="1" >启用</option>
                            <option <c:if test="${hbOrgStatus == 2}">selected="selected" </c:if> value="2" >停用</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="hbOrgInfoAdd()" type="button" class="btn btn-default">添加数据</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">Code</th>
                <th width="150">机构名称</th>
                <th width="150">机构状态</th>
                <th width="150">用户数量</th>
                <th width="150">创建人</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.hbOrgName}</td>
                    <td>
                        <c:if test="${item.hbOrgStatus == 1}"> 启用</c:if>
                        <c:if test="${item.hbOrgStatus == 2}"> 停用</c:if>
                    </td>
                    <td>${item.num}</td>
                    <td>${item.createBy}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td><a href="javascript:hbOrgDetails('${item.id}');">详情</a>

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
            <jsp:param name="requestUrl" value="${ctx}/hb/hbOrgList" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    var hbOrgDetails = function(id){
        openDialog({
            frame:true,
            title:"核保机构信息修改",
            height:630,
            width:1250,
            url:"${ctx}/hb/hbOrgDetails?id="+id
        });
    }

    var hbOrgInfoAdd = function(){
        openDialog({
            frame:true,
            title:"新增核保机构信息",
            height:500,
            width:800,
            url:"${ctx}/hb/hbOrgListToAdd"
        });
    }


</script>
</body>
</html>
