<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>商品分类列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>商品分类列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/proCategory/list" method="post">
                    <div class="form-group">
                        分类标题: <input name="catName" type="text"  value="${catName}" class="form-control">
                    </div>
                    <div class="form-group">
                       分类类别: <select name="catType"  class="form-control">
                            <option value="" >全部</option>
                            <option value="1" >虚拟商品</option>
                            <option value="2" >实物商品</option>
                        </select>
                        </div>
                    <div class="form-group">
                        状态: <select name="state"  class="form-control">
                        <option value="" >全部</option>
                        <option value="0" >待审核</option>
                        <option value="1" >审核通过</option>
                        <option value="2" >驳回</option>
                    </select>
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                    </div>
                    <button id="createAdminBtn" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>添加</button>
                </form>
            </div>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th class="th-checkbox">
                    <%--<input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选">--%>
                </th>
                <th width="80">分类标题</th>
                <th width="120">分类描述</th>
                <th width="90">分类排序</th>
                <th width="90">分类类别</th>
                <th width="300">创建时间</th>
                <th width="300">修改时间</th>
                <th width="80">状态</th>
                <th width="300">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td>${item.catName}</td>
                    <td>${item.catDesc}</td>
                    <td>${item.catSort}</td>
                    <td>
                        <c:if test="${item.catType == 1}">会员卡</c:if>
                        <c:if test="${item.catType == 2}">实物商品</c:if>
                        <c:if test="${item.catType == 3}">会员卡实物</c:if>
                    </td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td><fmt:formatDate value="${item.modifyTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <c:if test="${item.state == 0}">待审核</c:if>
                        <c:if test="${item.state == 1}">审核通过</c:if>
                        <c:if test="${item.state == 2}">驳回</c:if>
                    </td>
                    <td>
                        <c:if test="${item.state == 0}"><a href="javascript:examine(1,'${item.id}',1);">审核</a></c:if>
                        <c:if test="${item.state == 0}"><a href="javascript:examine(2,'${item.id}',2);">驳回</a></c:if>
                        <a href="javascript:updateView('${item.id}','${item.catName}','${item.catDesc}','${item.catSort}','${item.catType}');">修改</a>
                        <a href="javascript:deletePro('${item.id}');">删除</a>
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
            <jsp:param name="requestUrl" value="${ctx}/proCategory/list?catName=${catName}&catType=${catType}&state=${state}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/


   $("#createAdminBtn").on("click",function(){
       openDialog({
           frame:true,
           title:"添加商品分类",
           height:400,
           width:700,
           url:"${ctx}/proCategory/addView"
       });
   });


   function examine(type,id,state){
       ajaxSubmit("${ctx}/proCategory/examine",{"id":id,"state":state},reload,type==1?"审核成功！":"驳回成功！",type==1?"确认审核吗？":"确认驳回吗？");
   }
   function deletePro(id){
       ajaxSubmit("${ctx}/proCategory/delete",{"id":id},reload,"删除成功！","确认删除吗？");
   }
   function updateView(id,catName,catDesc,catSort,catType){
       openDialog({
           frame:true,
           title:"修改商品分类",
           height:400,
           width:700,
           url:"${ctx}/proCategory/updateView?id="+id+"&catName="+catName+"&catDesc="+catDesc+"&catSort="+catSort+"&catType="+catType
       });
   }

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
