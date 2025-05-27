<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title> 会员卡状态查询</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script>
        var updateInfo = function(id){
            openDialog({
                frame:true,
                title:"修改栏目信息",
                height:600,
                width:800,
                url:"${ctx}/newsCategory/queryById?id="+id
            });
        }
        var addinfo = function(){
            openDialog({
                frame:true,
                title:"添加栏目信息",
                height:600,
                width:800,
                url:"${ctx}/newsCategory/toAdd"
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

    </script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3> 会员卡列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/cardorder/list" method="post">
                    <div class="form-group">
                        会员卡状态:
                        <select id="cardSate" name="cardSate" class="form-control">
                            <option value="">全部</option>
                            <option value="待领取" <c:if test="${cardSate == '待领取'}">selected="selected" </c:if>>待领取</option>
                            <option value="已激活" <c:if test="${cardSate == '已激活'}">selected="selected" </c:if>>已激活</option>
                            <option value="已领取" <c:if test="${cardSate == '已领取'}">selected="selected" </c:if>>已领取</option>
                            <option value="已过期" <c:if test="${cardSate == '已过期'}">selected="selected" </c:if>>已过期</option>
                            <option value="丢失" <c:if test="${cardSate == '丢失'}">selected="selected" </c:if>>丢失</option>
                        </select>
                        会员卡类别:
                        <select id="type" name="type" class="form-control">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${type == 1}">selected="selected" </c:if>>实物卡</option>
                            <option value="2" <c:if test="${type == 2}">selected="selected" </c:if>>虚拟卡</option>
                        </select>
                        会员卡类型:
                        <select id="cardType" name="cardType" class="form-control">
                            <option value="">全部</option>
                            <option value="乐驾卡" <c:if test="${cardType == '乐驾卡'}">selected="selected" </c:if>>乐驾卡</option>
                            <option value="乐享卡" <c:if test="${cardType == '乐享卡'}">selected="selected" </c:if>>乐享卡</option>
                            <option value="乐尊卡" <c:if test="${cardType == '乐尊卡'}">selected="selected" </c:if>>乐尊卡</option>
                            <option value="车险人伤“及时雨”服务卡" <c:if test="${cardType == '车险人伤“及时雨”服务卡'}">selected="selected" </c:if>>车险人伤“及时雨”服务卡</option>
                            <option value="免费咨询卡" <c:if test="${cardType == '免费咨询卡'}">selected="selected" </c:if>>免费咨询卡</option>
                            <option value="车险人伤贴心宝" <c:if test="${cardType == '车险人伤贴心宝'}">selected="selected" </c:if>>车险人伤贴心宝</option>
                        </select>
                        车牌号：<input name="carNumber" type="text" value="${carNumber}" class="form-control">
                        卡号：<input name="cardNumber" type="text" value="${cardNumber}" class="form-control">
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
                <th width="150">姓名</th>
                <th width="150">车牌号</th>
                <th width="150">电话</th>
                <th width="150">会员卡号</th>
                <th width="150">会员卡类型</th>
                <th width="150">会员卡来源</th>
                <th width="150">出生时间</th>
                <th width="150">激活时间</th>
                <th width="150">会员卡状态</th>
                <th width="150">类别</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.carNumber}</td>
                    <td> ${item.phoneNumber}</td>
                    <td>${item.cardNumber}</td>
                    <td>${item.cardType}</td>
                    <td>${item.orderSource}</td>
                    <td>${item.bothTime}</td>
                    <%--<td><fmt:formatDate value="${item.bothTime}" pattern="yyyy-MM-dd"/></td>--%>
                    <%--<td><fmt:formatDate value="${item.payTime}" pattern="yyyy-MM-dd"/></td>--%>
                    <td>${item.payTime}</td>
                    <td>${item.cardSate}</td>
                    <c:if test="${item.type==1}">
                    <td>实物卡</td>
                    </c:if>
                    <c:if test="${item.type==2}">
                       <td>虚拟卡</td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/cardorder/list?cardSate=${cardSate}&type=${type}&cardType=${cardType}&carNumber=${carNumber}&orderCode=${orderCode}"/>
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
