<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
    <script>

    </script>

    <style>
        .table-title{
            font-size: 14px;
            font-weight: bold;
            line-height: 40px;
        }
        table.spec-info tbody tr td:nth-of-type(2n + 1) {
            width: 10%;
        }
        table.spec-info tbody tr td:nth-of-type(2n) {
            width: 20%;
        }
        table.spec-info tbody tr td:nth-of-type(6n) {
            width: 30%;
        }
        .div1{
            float: left;
        }
        .div1 button{
            margin: 0 1px;
            padding: 0 9px;
        }
        td div.pad5{
            padding: 5px;
        }
    </style>
</head>
<body>

<div class="main">
    <input type="hidden" name="id" value="${surveyOrder.id}">
    <input type="hidden" name="surveyCode" value="${surveyCode}">
    <div class="title">
        <c:if test="${surveyOrder.orderState ==1}">
            <button class="butList defuelt">等待付款</button>
        </c:if>
        <c:if test="${surveyOrder.orderState ==2}">
            <button class="butList active" onclick="operate('${surveyOrder.id}','${surveyCode}','1100',false);">确认发货</button>
        </c:if>
        <c:if test="${surveyOrder.orderState ==3}">
            <button class="butList defuelt">已发货</button>
        </c:if>
        <c:if test="${surveyOrder.orderState ==4}">
            <button class="butList defuelt">已收货</button>
        </c:if>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>订单编号</td>
                    <td>${surveyOrder.orderCode}</td>
                    <td>兑换人名称</td>
                    <td>${surveyOrder.allowUserName}</td>
                    <td>兑换人手机号</td>
                    <td>${surveyOrder.allowUserTel}</td>
                </tr>
                <tr>
                    <td>订单状态</td>
                    <td>
                        <c:if test="${surveyOrder.orderState== 1}">待付款</c:if>
                        <c:if test="${surveyOrder.orderState== 2}">待发货</c:if>
                        <c:if test="${surveyOrder.orderState== 3}">已发货</c:if>
                        <c:if test="${surveyOrder.orderState== 4}">已收货</c:if>
                    </td>
                    <td>物流单号</td>
                    <td>${surveyOrder.logisticsCode}</td>
                    <td>兑换人地址</td>
                    <td>${surveyOrder.allowUserAddress}</td>
                </tr>
                <tr>
                    <td>商品图片</td>
                    <td><img src="${surveyOrder.proImage}" width="75;" height="75;" class="picToBig"></td>
                    <td>商品code</td>
                    <td>${surveyOrder.proCode}</td>
                    <td>商品价格</td>
                    <td>${surveyOrder.proPrice}</td>
                </tr>
                <tr>
                    <td>商品乐凡币</td>
                    <td>${surveyOrder.lefanCoin}</td>
                    <td>实际购买价格</td>
                    <td>${surveyOrder.realBuyPrice}</td>
                    <td>实际兑换乐凡币</td>
                    <td>${surveyOrder.realBuyCoin}</td>
                </tr>
                <tr>
                    <td>是否支付</td>
                    <td>
                        <c:if test="${surveyOrder.isPay== 0}">否</c:if>
                        <c:if test="${surveyOrder.isPay== 1}">是</c:if>
                    </td>
                    <td>支付时间</td>
                    <td><fmt:formatDate value="${surveyOrder.payTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>下单时间</td>
                    <td><fmt:formatDate value="${surveyOrder.orderTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </div>
</div>

<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script type="text/javascript">
    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

    /**
     * 关闭dialog
     */
    function closeDialog(){
        var closeBtn = $("#diglog_close_btn");
        if(closeBtn.size() == 0){
            closeBtn = $("#diglog_close_btn",window.parent.document);
        }
        closeBtn.click();
    }

    /**
     *
     */
    function operate(id,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"id":id,"surveyCode":surveyCode,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    if(btnCode == '9999'){
                        reloadParent();//删除时，需刷新父级
                    }else{
                        location.reload();
                    }
                })
            }
        }else {
            var title = null, url = null;
            if(btnCode == '1100'){
                height = 500;
                width = 800;
                title = '发货';
                url = "${ctx}/surveyOrder/deliveryPro?id="+id+"&surveyCode="+surveyCode + "&btnCode=" + btnCode
            }
            openDialog({
                frame:true,
                title:title,
                height:height,
                width:width,
                url:url
            });
        }
    }

</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>