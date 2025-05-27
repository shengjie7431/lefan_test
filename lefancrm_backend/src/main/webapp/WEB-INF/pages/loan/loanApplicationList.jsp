<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>贷款申请</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>贷款申请 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/loan/loanApplicationList" method="post">
                    <div class="form-group">
                       贷款状态: <select name="state" class="form-control">
                            <option value="" >全部</option>
                            <option value="1">报案</option>
                            <option value="2">受理</option>
                            <option value="3">驳回</option>
                            <option value="4">贷款申请中</option>
                            <option value="5">完成</option>
                        </select>
                        </div>
                    <div class="form-group">
                       交通事故: <select name="isTrafficAccident" class="form-control">
                            <option value="">全部</option>
                            <option value="0">否</option>
                            <option value="1">是</option>
                        </select>
                    </div>
                    手机号码: <input name="userPhone" type="text"  value="${userPhone}" class="form-control">
                    案件编号: <input name="loanNo" type="text"  value="${loanNo}" class="form-control">
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th class="th-checkbox"></th>
                <th width="100">案件编号</th>
                <th width="100">用户姓名</th>
                <th width="150">手机号码</th>
                <th width="100">推广人姓名</th>
                <th width="100">交通事故</th>
                <th width="100">贷款金额</th>
                <th width="100">贷款用途</th>
                <th width="100">状态</th>
                <th width="300">详细地址</th>
                <th width="200">驳回原因</th>
                <th width="200">时间</th>
                <th width="280">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td>${item.loanNo}</td>
                    <td>${item.userName}</td>
                    <td>${item.userPhone}</td>
                    <td>${item.promoterName}</td>
                    <td>
                        <c:if test="${item.isTrafficAccident == 0}">否</c:if>
                        <c:if test="${item.isTrafficAccident == 1}">是</c:if>
                    </td>
                    <td>${item.loanMoney}</td>
                    <td>
                        <c:if test="${item.loanPurpose == 1}">医疗费垫付</c:if>
                        <c:if test="${item.loanPurpose == 2}">赔偿款垫付</c:if>
                    </td>
                    <td>
                        <%--6:贷款评估,7:贷款面签,8:贷款审批,9:保证保险投保,10:保证保险出单,11:贷款发放,12:贷款还款--%>
                        <c:if test="${item.state == 1}">待审核</c:if>
                        <c:if test="${item.state == 2}">审核通过</c:if>
                        <c:if test="${item.state == 3}">驳回</c:if>
                        <c:if test="${item.state == 4}">已受理</c:if>
                        <c:if test="${item.state == 5}">贷款评估</c:if>
                        <c:if test="${item.state == 6}">贷款面签</c:if>
                        <c:if test="${item.state == 7}">贷款审批</c:if>
                        <c:if test="${item.state == 8}">保证保险投保</c:if>
                        <c:if test="${item.state == 9}">贷款发放</c:if>
                        <c:if test="${item.state == 22}">结案</c:if>
                    </td>
                    <td>${item.accidentProvinceName}${item.accidentCityName}${item.accidentDistrictName}${item.accidentAddress}</td>
                    <td>${item.reson}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <th>
                        <c:if test="${item.state == 1}">
                        <a href="javascript:loanDelete('${item.id}');">删除</a>
                        <a href="javascript:loanEdit('${item.id}','${item.userPhone}','${item.loanMoney}','${item.userName}'
                        ,'${item.accidentAddress}','${item.loanPurpose}','${item.accidentProvince}','${item.accidentCity}','${item.accidentDistrict}','${item.isTrafficAccident}');">编辑</a>
                        <a href="javascript:loanUpdate('${item.id}',2,'${item.userId}','${item.userName}','${item.accidentCityName}');">审核通过</a>
                        <a href="javascript:updateReson('${item.id}');">驳回</a>
                        </c:if>
                        <c:if test="${item.state != 1}">
                            <a href="javascript:loanEdit('${item.id}','${item.userPhone}','${item.loanMoney}','${item.userName}'
                        ,'${item.accidentAddress}','${item.loanPurpose}','${item.accidentProvince}','${item.accidentCity}','${item.accidentDistrict}','${item.isTrafficAccident}');">编辑</a>
                        </c:if>
                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/loan/loanApplicationList?state=${state}&isTrafficAccident=${isTrafficAccident}&userPhone=${userPhone}&loanNo=${loanNo}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/
   function loanDelete(id){
       ajaxSubmit("${ctx}/loan/loanApplicationTodelete",{"id":id},reload,"删除成功","确认删除该条信息吗？");
   }

   function loanUpdate(id,state,userId,userName,accidentCity){
       ajaxSubmit("${ctx}/loan/loanApplicationToUpdate",{"id":id,"state":state,"userId":userId,"userName":userName,"accidentCity":accidentCity},reload,"提交成功","确认提交该条信息吗？");
   }
   function loanEdit(id,userPhone,loanMoney,userName,accidentAddress,loanPurpose,accidentProvince,accidentCity,accidentDistrict,isTrafficAccident){
       openDialog({
           frame:true,
           title:"编辑",
           height:500,
           width:1000,

           url:"${ctx}/loan/loanApplicationEdit?id="+id+"&userPhone="+userPhone+"&loanMoney="+loanMoney+"&userName="+userName+"&accidentAddress="+accidentAddress+"&loanPurpose="+loanPurpose+"&accidentProvince="+accidentProvince
           +"&accidentCity="+accidentCity+"&accidentDistrict="+accidentDistrict+"&isTrafficAccident="+isTrafficAccident
       });
   }
   var updateReson = function(id){
       openDialog({
           frame:true,
           title:"驳回贷款申请",
           height:400,
           width:600,
           url:"${ctx}/loan/toReson?id="+id
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
