<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>商品列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>商品列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/product/list" method="post">
                    <div class="form-group">
                        商品编号: <input name="productCode" type="text"  value="${productCode}" class="form-control">
                    </div>
                    <div class="form-group">
                        商品名称: <input name="productName" type="text"  value="${productName}" class="form-control">
                    </div>
                    <div class="form-group">
                        商品上架: <select name="isShelves"  class="form-control">
                            <option value="" >全部</option>
                            <option value="0" >上架</option>
                            <option value="1" >下架</option>
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
                <th width="80">商品编号</th>
                <th width="120">商品名称</th>
                <th width="90">商品价格</th>
                <th width="90">商品图片</th>
                <th width="300">商品优惠价格</th>
                <th width="90">计量单位</th>
                <th width="80">商品上下架</th>
                <th width="80">商品总库存</th>
                <th width="80">排序</th>
                <th width="80">创建时间</th>
                <th width="80">修改时间</th>
                <th width="300">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td>${item.productCode}</td>
                    <td>${item.productName}</td>
                    <td>${item.productPrice}</td>
                    <td><img id="infImg" src="${item.productPic}" width="80" height="80"></td>
                    <td>${item.disPrice}</td>
                    <td>${item.unit}</td>
                    <td>
                        <c:if test="${item.isShelves == 0}">上架</c:if>
                        <c:if test="${item.isShelves == 1}">下架</c:if>
                    </td>
                    <td>${item.stock}</td>
                    <td>${item.sort}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td><fmt:formatDate value="${item.modifyTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                         <a href="javascript:updateView('${item.id}');">修改</a>
                        <a href="javascript:deleteProduct('${item.id}');">删除</a>
                        <c:if test="${item.isShelves == 0}"><a href="javascript:upAndDownFrame('${item.id}',1);">下架</a></c:if>
                        <c:if test="${item.isShelves == 1}"><a href="javascript:upAndDownFrame('${item.id}',0);">上架</a></c:if>
                        <a href="javascript:addProductSpec('${item.id}');">添加规格</a>
                        <a href="javascript:queryProductSpec('${item.id}');">查询规格列表</a>

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
            <jsp:param name="requestUrl" value="${ctx}/product/list?productCode=${productCode}&productName=${productName}&isShelves=${isShelves}" />
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
           title:"添加商品",
           height:500,
           width:800,
           url:"${ctx}/product/addView"
       });
   });
   var addProductSpec = function(id){
       openDialog({
           frame:true,
           title:"添加商品规格信息",
           height:600,
           width:800,
           url:"${ctx}/productSpec/toAdd?productId="+id
       });
   }

   var queryProductSpec = function(id){
       openDialog({
           frame:true,
           title:"查询商品规格信息",
           height:700,
           width:1400,
           url:"${ctx}/productSpec/list?productId="+id
       });
   }

   function updateView(id,productCode,productName,productPrice,productPic,disPrice,productType,unit,stock,sort){
       openDialog({
           frame:true,
           title:"修改商品",
           height:500,
           width:800,
           url:"${ctx}/product/updateView?id="+id
       });
   }

   function deleteProduct(id){
       ajaxSubmit("${ctx}/product/delete",{"id":id},reload,"删除成功！","确认删除吗？");
   }
   function upAndDownFrame(id,isShelves){
       ajaxSubmit("${ctx}/product/upAndDownFrame",{"id":id,"isShelves":isShelves},reload,isShelves==0?"上架成功！":"下架成功！",isShelves==0?"确认上架吗？":"确认下架吗？");
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
