<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>订单列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>订单列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/orderInfo/list" method="post">
                    <div class="form-group">
                        订单编号: <input name="orderCode" type="text"  value="${orderCode}" class="form-control">
                    </div>
                    <div class="form-group">
                        用户名称: <input name="userName" type="text"  value="${userName}" class="form-control">
                    </div>
                    <div class="form-group">
                        真实姓名: <input name="realName" type="text"  value="${realName}" class="form-control">
                    </div>
                    <div class="form-group">
                        订单来源: <select name="orderSource"  class="form-control">
                            <option value="" >全部</option>
                            <option value="1" >官网</option>
                            <option value="2" >o2o平台</option>
                            <option value="3" >小程序</option>
                            <option value="4" >微站</option>
                        </select>
                        </div>
                    <div class="form-group">
                        订单类型: <select name="orderType"  class="form-control">
                        <option value="" >全部</option>
                        <option value="1" >会员卡</option>
                        <option value="2" >实物商品</option>
                        <option value="3" >会员卡实物</option>
                    </select>
                    </div>
                    <div class="form-group">
                        订单状态: <select name="orderState"  class="form-control">
                        <option value="" >全部</option>
                        <option value="1" >待支付</option>
                        <option value="2" >已支付</option>
                        <option value="3" >配送中</option>
                        <option value="4" >已配送</option>
                        <option value="5" >退货中</option>
                        <option value="6" >已退货</option>
                        <option value="7" >待激活</option>
                        <option value="8" >已激活</option>
                    </select>
                    </div>
                    <div class="form-group">
                        意外险激活列表: <select name="isOpenbk"  class="form-control">
                        <option value="" >全部</option>
                        <option value="0" >否</option>
                        <option value="1" >是</option>
                        <option value="2" >待激活</option>
                    </select>
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th class="th-checkbox">
                    <%--<input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选">--%>
                </th>
                <th width="150">订单编号</th>
                <th width="90">用户姓名</th>
                <th width="200">真实姓名</th>
                <th width="100">订单来源</th>
                <th width="300">订单类型</th>
                <th width="300">订单状态</th>
                <th width="300">订单价格</th>
                <th width="300">收货地址</th>
                <th width="200">创建时间</th>
                <th width="200">支付时间</th>
                <th width="300">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td>${item.orderCode}</td>
                    <td>${item.userName}</td>
                    <td>${item.realName}</td>
                    <td>
                        <c:if test="${item.orderSource == 1}">官网</c:if>
                        <c:if test="${item.orderSource == 2}">o2o平台</c:if>
                        <c:if test="${item.orderSource == 3}">小程序</c:if>
                        <c:if test="${item.orderSource == 3}">微站</c:if>
                    </td>
                    <td>
                        <c:if test="${item.orderType == 1}">会员卡</c:if>
                        <c:if test="${item.orderType == 2}">实物商品</c:if>
                        <c:if test="${item.orderType == 3}">会员卡实物</c:if>
                    </td>
                    <td>
                        <c:if test="${item.orderState == 1}">待支付</c:if>
                        <c:if test="${item.orderState == 2}">已支付</c:if>
                        <c:if test="${item.orderState == 3}">配送中</c:if>
                        <c:if test="${item.orderState == 4}">已配送</c:if>
                        <c:if test="${item.orderState == 5}">退货中</c:if>
                        <c:if test="${item.orderState == 6}">已退货</c:if>
                        <c:if test="${item.orderState == 7}">待激活</c:if>
                        <c:if test="${item.orderState == 8}">已激活</c:if>
                    </td>
                    <td>${item.orderPrice}</td>
                     <%--<td>${item.addressId}</td>--%>
                     <td>${item.addressDetail}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td><fmt:formatDate value="${item.payTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <a href="javascript:orderPro('${item.id}');">查询子订单</a>
                        <c:if test="${item.orderType == 1 || item.orderType == 3}"><a href="javascript:orderExtend('${item.id}');">查询会员卡</a></c:if>
                        <c:if test="${(item.orderType == 2 || item.orderType == 3) && (item.orderState>1&&item.orderState<4)}"><a href="javascript:distribution('${item.userId}','${item.orderCode}','${item.id}');">配送</a></c:if>
                        <c:if test="${(item.orderType == 2 || item.orderType == 3) && (item.orderState== 4)}"><a href="javascript:distributionDetail('${item.id}');">配送物流</a></c:if>
                       <%--根据订单编号,查询保酷意外险--%>
                        <a href="javascript:accidentList('${item.orderCode}');">意外险列表</a>
                        <c:if test="${item.isOpenbk== 2}"><a href="javascript:writeInvoiceNumber('${item.orderCode}');">意外险激活</a></c:if>
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
            <jsp:param name="requestUrl" value="${ctx}/orderInfo/list?orderCode=${orderCode}&userName=${userName}&realName=${realName}&orderSource=${orderSource}&orderType=${orderType}&orderState=${orderState}&isOpenbk=${isOpenbk}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/
   function distribution(userId,orderCode,orderId){
       openDialog({
           frame:true,
           title:"物流信息",
           height:400,
           width:600,
               url:"${ctx}/orderInfo/distriView?orderId="+orderId+"&userId="+userId+"&orderCode="+orderCode
       });
       <%--ajaxSubmit("${ctx}/orderInfo/distribution",{"userId":userId,"orderCode":orderCode},reload,"配送成功","确认配送吗？");--%>
   }

   function orderPro(id){
       openDialog({
           frame:true,
           title:"子订单列表",
           height:600,
           width:1000,
           url:"${ctx}/orderPro/list?orderId="+id
       });
   }

   function orderExtend(id){
       openDialog({
           frame:true,
           title:"会员卡列表",
           height:600,
           width:1000,
           url:"${ctx}/orderExtend/list?orderId="+id
       });
   }

   function distributionDetail(orderId){
       openDialog({
           frame:true,
           title:"配送物流",
           height:600,
           width:1000,
           url:"${ctx}/logisticOrder/detail?orderId="+orderId
       });
   }

   function accidentList(orderCode){
       openDialog({
           frame:true,
           title:"意外险列表",
           height:600,
           width:1000,
           url:"${ctx}/baokuAccident/list?orderCode="+orderCode
       });
   }

  function writeInvoiceNumber(orderCode){
       openDialog({
           frame:true,
           title:"意外险激活",
           height:600,
           width:1000,
           url:"${ctx}/baokuAccident/distriView?orderCode="+orderCode
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
