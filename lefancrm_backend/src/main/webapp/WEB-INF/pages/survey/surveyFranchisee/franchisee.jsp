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
                <form class="form-inline" role="form" action="${ctx}/surveyFranchisee/selectFranchisee?pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <input type="hidden" name="surveyCode" value="${surveyCode}">
                    <input type="hidden" name="type" value="${type}">
                    <input type="hidden" name="btnCode" value="${btnCode}">
                    <input type="hidden" name="userId" id="userId" value="${userId}">
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
                <th width="100">调查方名称</th>
<%--                <th width="150">调查方code</th>--%>
                <th width="100">调查方级别</th>
                <th width="150">区域名称</th>
                <th width="150">区域类别</th>
                <th width="100">调查方类别</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.name}</td>
<%--                    <td>${item.code}</td>--%>
                    <td>${item.level}</td>
                    <td>${item.areaName}</td>
                    <td>
                        <c:if test="${item.areaType==1}">省</c:if>
                        <c:if test="${item.areaType==2}">市</c:if>
                        <c:if test="${item.areaType==3}">区</c:if>
                    </td>
                    <td>
                        <c:if test="${item.type==1}">直营</c:if>
                        <c:if test="${item.type==2}">合伙</c:if>
                        <c:if test="${item.type==3}">合作</c:if>
                    </td>
                    <td>
                        <c:if test="${btnCode == 'franchisee'}">
                            <a href="javascript:allot('${item.id}','${surveyCode}','${type}');">选择</a>
                        </c:if>
                        <c:if test="${btnCode == 'changeFranchisee'}">
                            <a href="javascript:changeFranchisee('${item.id}','${userId}','${surveyCode}','${btnCode}');">更改</a>
                        </c:if>
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
            <jsp:param name="requestUrl" value="${ctx}/surveyFranchisee/selectFranchisee?surveyCode=${surveyCode}&name=${name}&btnCode=${btnCode}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    function allot(id,surveyCode,type){
        $.ajax({
            url:'${ctx}/surveyFranchisee/choiceFranchisee?id='+id+"&surveyCode="+surveyCode,
            type:"Get",
            success:function(res,param){
                var item=param.data.results;

                    //调查方父级
//                    if(type=="surveyFranchisee"){
                        parent.document.getElementById("parentName").value = item.name;
                        parent.document.getElementById("parentId").value = item.id;
//                    }
                    //调查方价格
//                    else if(type=="surveyFranchiseePrice"){
//                        parent.document.getElementById("franchiseeName").value = item.name;
//                        parent.document.getElementById("franchiseeId").value = item.id;
//                    }
                closeDialog();
            }
        });
    }

    function changeFranchisee(id,userId,surveyCode,btnCode) {
        ajaxSubmit("${ctx}/baseSurvey/operate",{"id":id,"userId":userId,"btnCode":btnCode,"surveyCode":surveyCode},reload,"更改成功！","确认更改？","更改失败！");
    }
</script>
</body>
</html>
