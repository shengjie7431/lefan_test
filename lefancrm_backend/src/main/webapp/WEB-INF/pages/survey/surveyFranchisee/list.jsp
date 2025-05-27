<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>调查方列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>调查方列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/baseSurvey/list?pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                   <div class="form-group">
                       名称:<input name="name" type="text" value="${name}" class="form-control">
                   </div>

<%--                    <div class="form-group">--%>
<%--                        区域类别:--%>
<%--                        <select name="areaType"  class="form-control">--%>
<%--                            <option value=""  <c:if test="${areaType == ''}">selected="selected" </c:if> >全部</option>--%>
<%--                            <option value="1" <c:if test="${areaType == '1'}">selected="selected" </c:if> >省</option>--%>
<%--                            <option value="2" <c:if test="${areaType == '2'}">selected="selected" </c:if> >市</option>--%>
<%--                            <option value="3" <c:if test="${areaType == '3'}">selected="selected" </c:if> >区</option>--%>
<%--                        </select>--%>
<%--                    </div>--%>
                    <div class="form-group">
                        业务属性:
                        <select name="busType"  class="form-control">
                            <option value=""  <c:if test="${busType == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${busType == '1'}">selected="selected" </c:if> >互助</option>
                            <option value="2" <c:if test="${busType == '2'}">selected="selected" </c:if> >保险</option>
                            <option value="3" <c:if test="${busType == '3'}">selected="selected" </c:if> >互助+保险</option>
                        </select>
                    </div>
                    <div class="form-group">
                        调查方类别:
                        <select name="type"  class="form-control">
                            <option value=""  <c:if test="${type == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${type == '1'}">selected="selected" </c:if> >直营</option>
                            <option value="2" <c:if test="${type == '2'}">selected="selected" </c:if> >合伙</option>
                            <option value="3" <c:if test="${type == '3'}">selected="selected" </c:if> >合作</option>
                        </select>
                    </div>
                    <div class="form-group">
                        机构状态:
                        <select name="orgState"  class="form-control">
                            <option value=""  <c:if test="${orgState == ''}">selected="selected" </c:if> >全部</option>
                            <option value="0" <c:if test="${orgState == '0'}">selected="selected" </c:if> >启用</option>
                            <option value="1" <c:if test="${orgState == '1'}">selected="selected" </c:if> >停用</option>
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
<%--                <th width="80">code</th>--%>
                <th width="120">调查方名称</th>
                <th width="150">关联人事机构名称</th>
                <th width="80">业务属性</th>
<%--                <th width="80">区域名称</th>--%>
                <th width="80">调查方类别(互助)</th>
                <th width="80">调查方类别(保司)</th>
                <%--<th width="80">父级名称类别</th>--%>
                <th width="80">机构状态</th>
                <th width="80">申请人</th>
                <th width="80">审批人</th>
                <th width="80">创建人</th>
                <th width="100">创建时间</th>
                <th width="80">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
<%--                    <td>${item.code}</td>--%>
                    <td>${item.name}</td>
                    <td>
                        ${item.departmentName}
                    </td>
                    <td>
                        <c:if test="${item.busType==1}">互助</c:if>
                        <c:if test="${item.busType==2}">保险</c:if>
                        <c:if test="${item.busType==3}">互助+保险</c:if>
                    </td>
<%--                    <td>${item.areaName}</td>--%>
                    <td>
                        <c:if test="${item.type==1}">直营</c:if>
                        <c:if test="${item.type==2}">合伙</c:if>
                        <c:if test="${item.type==3}">合作</c:if>
                    </td>
                    <td>
                        <c:if test="${item.insuranceType==1}">直营</c:if>
                        <c:if test="${item.insuranceType==2}">合伙</c:if>
                        <c:if test="${item.insuranceType==3}">合作</c:if>
                    </td>
                    <%--<td>${item.parentName}</td>--%>
                    <td>
                        <c:if test="${item.orgState==0}">启用</c:if>
                        <c:if test="${item.orgState==1}">停用</c:if>
                    </td>
                    <td>${item.appUser}</td>
                    <td>${item.apvUser}</td>
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
            <jsp:param name="requestUrl" value="${ctx}/baseSurvey/list?surveyCode=${surveyCode}&busType=${busType}&name=${name}&areaType=${areaType}&type=${type}&orgState=${orgState}" />
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
            height:650,
            width:1000,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode
        });
    }

    var info = function(id,surveyCode){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"详情",
            height:height,
            width:width,
            url:"${ctx}/baseSurvey/info?id="+id+"&surveyCode="+surveyCode,
            load:true
        });
    }
</script>
</body>
</html>
