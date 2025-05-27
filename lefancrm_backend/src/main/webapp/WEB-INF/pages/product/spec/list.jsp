<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title> 商品规格管理</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script>
        var updateInfo = function(id){
            openDialog({
                frame:true,
                title:"修改商品规格信息",
                height:600,
                width:800,
                url:"${ctx}/productSpec/queryById?id="+id
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
            ajaxSubmit("${ctx}/newsCategory/edit",{"id":id,"state":state},reload,toVal,val,"操作失败");
        }
        var del = function(id,deleteFlag){
            ajaxSubmit("${ctx}/productSpec/edit",{"id":id,"deleteFlag":deleteFlag},reload,"删除成功","确定删除操作！","操作失败");
        }
    </script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3> 商品规格列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/productSpec/list" method="post">
                    <div class="form-group">
                        名称:<input name="specName" type="text" value="${specName}" class="form-control">
                        <input name="productId" type="hidden" value="${productId}" class="form-control">
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
                <th width="150">规格名称</th>
                <th width="150">规格展示图片</th>
                <th width="150">价格</th>
                <th width="150">成本价</th>
                <th width="150">库存</th>
                <th width="150">销量</th>
                <th width="150">创建时间</th>
                <th width="150">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.specName}</td>
                    <td><a href="${item.specImg}" target="_blank"><img src="${item.specImg}" width="75px" height="75px"></a></td>
                    <td> ${item.specPrice}</td>
                    <td>${item.specCost}</td>
                    <td>${item.specStock}</td>
                    <td>${item.specSales}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <a  href="javascript:void(0)" onclick="updateInfo(${item.id})">编辑</a>
                        <a  href="javascript:void(0)" onclick="del(${item.id},1)">删除</a>
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
            <jsp:param name="requestUrl" value="${ctx}/productSpec/list?specName=${specName}&productId=${productId}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->
</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>


    /* var getreport = function(id){
     openDialog({
     frame:true,
     title:"查看测算报告",
     height:1000,
     width:800,
     url:"${ctx}/paymentEstimate/paymentEstimateReportList?id="+id
     });
     }*/
</script>
</body>
</html>
