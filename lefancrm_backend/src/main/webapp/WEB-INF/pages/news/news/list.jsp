<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title> 新闻管理</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script>
        var updateInfo = function(id){
            openDialog({
                frame:true,
                title:"修改新闻信息",
                height:800,
                width:800,
                url:"${ctx}/news/queryById?id="+id
            });
        }
        var addinfo = function(){
            openDialog({
                frame:true,
                title:"添加新闻信息",
                height:800,
                width:800,
                url:"${ctx}/news/toAdd"
            });
        }

        var updateState = function(id,state){
            var val = "";
            var toVal ="";
            if(state == 1){
                val = "确定审核通过操作？"
                toVal = "审核成功！"
            }else if(state == 2){
                val = "确定驳回操作？"
                toVal = "驳回成功！"
            }
            ajaxSubmit("${ctx}/news/edit",{"id":id,"state":state},reload,toVal,val,"操作失败");
        }
        var del = function(id,deleteFlag){
            ajaxSubmit("${ctx}/news/edit",{"id":id,"deleteFlag":deleteFlag},reload,"删除成功！","确定删除操作！","操作失败");
        }

    </script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3> 新闻列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/news/list" method="post">
                    <div class="form-group">
                        新闻标题:<input name="title" type="text" value="${title}" class="form-control">
                        新闻类型:
                        <select id="catType" name="catType" class="form-control" onclick="queryCatAll()">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${catType == 1}">selected="selected" </c:if>>新闻动态</option>
                            <option value="2" <c:if test="${catType == 2}">selected="selected" </c:if>>服务案例</option>
                            <option value="3" <c:if test="${catType == 3}">selected="selected" </c:if>>招聘信息</option>
                        </select>
                        新闻子类型:
                            <select id = "subType"   style="width:120px;" onclick="queryCat()" class="form-control">
                                <option value="">请选择</option>
                            </select>
                        <input id="catId" name="catId" type="hidden" class="form-control" value="${catId}">
                        是否公开:
                        <select id="isPublish" name="isPublish" class="form-control" onclick="queryCatAll()">
                            <option value="" >全部</option>
                            <option value="1" <c:if test="${isPublish == 1}">selected="selected" </c:if>>是</option>
                            <option value="2" <c:if test="${isPublish == 2}">selected="selected" </c:if>>否</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="addinfo()" type="button" class="btn btn-default" class="btn btn-default">添加新闻</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="150">icon</th>
                <th width="150">新闻标题</th>
                <th width="150">新闻类型</th>
                <th width="150">新闻描述</th>
                <th width="150">是否显示</th>
                <th width="150">公开时间</th>
                <th width="150">状态</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td><a href="${item.newsImg}" target="_blank"><img src="${item.newsImg}" width="75px" height="75px"></a></td>
                    <td>${item.title}</td>
                    <td>
                        <c:forEach items="${categorys}" var="cat">
                            <c:if test="${cat.id == item.catId}">
                                ${cat.catName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td> ${item.outline}</td>
                    <td>
                        <c:if test="${item.isPublish == 2}">否</c:if>
                        <c:if test="${item.isPublish == 1}">是</c:if>
                    </td>
                    <td><fmt:formatDate value="${item.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <c:if test="${item.state == 0}">待审核</c:if>
                        <c:if test="${item.state == 1}">审核通过</c:if>
                        <c:if test="${item.state == 2}">驳回</c:if>
                    </td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <a  href="javascript:void(0)" onclick="updateInfo(${item.id})">编辑</a>
                        <a  href="javascript:void(0)" onclick="del(${item.id},1)">删除</a>
                        <c:if test="${item.state == 0}">
                            <a  href="javascript:void(0)" onclick="updateState(${item.id},1)">审核通过</a>
                            <a  href="javascript:void(0)" onclick="updateState(${item.id},2)">驳回</a>
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
            <jsp:param name="requestUrl" value="${ctx}/news/list?catId=${catId}&catType=${catType}&title=${title}&isPublish=${isPublish}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->
</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    $(document).ready(function(){
        var catType = $("#catType").val();
        if(catType == null || catType == ''){
            return;
        }
        ajaxSubmit("${ctx}/newsCategory/queryAll",{"catType":catType},function(v,e,p){
            $("#subType option").remove();
            $("#subType").append("<option value='0'>请选择</option>");
            var subType = $("#catId").val();
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                if(subType == val.id){
                    $("#subType").append("<option selected='selected' value='"+val.id+"'>"+val.catName+"</option>");
                }else{
                    $("#subType").append("<option value='"+val.id+"'>"+val.catName+"</option>");
                }
            }
        })
    });

    function  queryCatAll(){
        var catType = $("#catType").val();
        if(catType == null || catType == ''){
            return;
        }
        ajaxSubmit("${ctx}/newsCategory/queryAll",{"catType":catType},function(v,e,p){
            $("#subType option").remove();
            $("#subType").append("<option value='0'>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#subType").append("<option value='"+val.id+"'>"+val.catName+"</option>");
            }

        })
    }
    function  queryCat(){
        var subType = $("#subType").val();
        $("#catId").val(subType);
    }
</script>
</body>
</html>
