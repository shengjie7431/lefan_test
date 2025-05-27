<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>委托机构列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>委托机构列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/surveyConsignor/selectConsignor" method="post">
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                    <input type="hidden" name="type" value="${type}">
                   <div class="form-group">
                       名称:<input name="name" type="text" value="${name}" class="form-control">
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
                <th width="100">委托方名称</th>
                <th width="150">委托方单位</th>
                <th width="100">区域类别</th>
                <th width="150">区域名称</th>
                <th width="150">委托方类别</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.company}</td>
                    <td>
                        <c:if test="${item.areaType==1}">省</c:if>
                        <c:if test="${item.areaType==2}">市</c:if>
                        <c:if test="${item.areaType==3}">区</c:if>
                    </td>
                    <td>${item.areaName}</td>
                    <td>
                        <c:if test="${item.type==1}">合作伙伴</c:if>
                        <c:if test="${item.type==2}">散户</c:if>
                    </td>
                    <td>
                        <a href="javascript:allot('${item.id}','${surveyCode}');">选择</a>
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
            <jsp:param name="requestUrl" value="${ctx}/surveyConsignor/selectConsignor?surveyCode=${surveyCode}&name=${name}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    function allot(id,surveyCode){
        $.ajax({
            url:'${ctx}/surveyConsignor/choiceConsignor?id='+id+"&surveyCode="+surveyCode,
            type:"Get",
            success:function(res,param){
                var item=param.data.results;
                parent.document.getElementById("enturyName").value = item.name;
                parent.document.getElementById("enturyId").value = item.id;
                closeDialog();
            }
        });
    }

</script>
</body>
</html>
