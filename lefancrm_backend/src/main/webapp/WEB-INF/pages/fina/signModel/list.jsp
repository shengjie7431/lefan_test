<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>签约模板</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>签约模板列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/finaManager/list" method="post">
                    <input type="hidden" name="surveyCode" value="signModel">
                    <div class="form-group">
                        模板名称:<input name="modelName" type="text" value="${modelName}" class="form-control">
                    </div>
                    <div class="form-group">
                        模板类型:
                        <select name="modelType"  class="form-control">
                            <option value=""  <c:if test="${modelType == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${modelType == '1'}">selected="selected" </c:if> >垫付协议</option>
                            <option value="2" <c:if test="${modelType == '2'}">selected="selected" </c:if> >保险理赔授权委托书</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="add('signModel')" type="button" class="btn btn-default">添加</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">模板名称</th>
                <th width="150">模板类型</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.modelName}</td>
                    <td>
                        <c:if test="${item.modelType == 1}">垫付协议</c:if>
                        <c:if test="${item.modelType == 2}">保险理赔授权委托书</c:if>
                    </td>
                    <td>
                        <a href="javascript:oper(${item.id});">关联委托方</a>
                        <a href="javascript:edit(${item.id});">编辑</a>
                        <a href="javascript:del(${item.id});">删除</a>
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
            <jsp:param name="requestUrl" value="${ctx}/finaManager/list?surveyCode=${surveyCode}&name=${name}&modelName=${modelName}&modelType=${modelType}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    var del  = function(id){
        var url = "${ctx}/finaManager/update",param = {"id":id,"surveyCode":"signModelDel"};
        if(confirm('是否确认？')){
            ajaxSubmit(url,param,function(v,e,p){
                location.reload();
            })
        }
    }

    var add = function(surveyCode){
        openDialog({
            frame:true,
            title:"添加",
            height:600,
            width:1000,
            url:"${ctx}/finaManager/add?surveyCode="+surveyCode
        });
    }

    var oper = function(modelId){
        openDialog({
            frame:true,
            title:"关联委托方",
            height:650,
            width:1000,
            url:"${ctx}/finaManager/popup?id="+modelId+"&surveyCode=signModel&btnCode=2000",
            load:true
        });
    }
    var edit = function(modelId){
        openDialog({
            frame:true,
            title:"编辑",
            height:650,
            width:1000,
            url:"${ctx}/finaManager/edit?id="+modelId+"&surveyCode=signModel",
            load:true
        });
    }
</script>
</body>
</html>
