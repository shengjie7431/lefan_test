<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>退款列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>退款列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/case/selectCaseOrderReturn" method="post">
                    <div class="form-group">
                        案件类型: <select name="caseType" class="form-control">
                        <option value="" >全部</option>
                        <option value="1">医疗费垫付</option>
                        <option value="2">赔偿款垫付</option>
                        <option value="3">代办理赔</option>
                        <option value="4">其他</option>
                    </select>
                        </div>
                    <div class="form-group">
                       退款状态: <select name="state" class="form-control">
                            <option value="" >全部</option>
                            <option value="1">待审核</option>
                            <option value="2">驳回</option>
                            <option value="3">已退款</option>
                        </select>
                        </div>
                    <div class="form-group">
                        退款单号: <input name="returnCode" type="text"  value="${returnCode}" class="form-control">
                        </div>
                    <div class="form-group">
                        案件编号: <input name="caseNo" type="text"  value="${caseNo}" class="form-control">
                        </div><br><br>
                    <div class="form-group">
                        机构名称: <input name="orgName" type="text"  value="${orgName}" class="form-control">
                        </div>
                    <div class="form-group">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        用户名称: <input name="userName" type="text"  value="${userName}" class="form-control">
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
                <th width="200">案件编号</th>
                <th width="200">案件类型</th>
                <th width="100">退款单号</th>
                <th width="100">退款金额</th>
                <th width="100">退款状态</th>
                <th width="200">机构名称</th>
                <th width="100">用户名称</th>
                <th width="200">驳回原因</th>
                <th width="200">创建时间</th>
                <th width="200">退款时间</th>
                <th width="200">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td>${item.caseNo}</td>
                    <td>
                        <c:if test="${item.caseType == 1}">医疗费垫付</c:if>
                        <c:if test="${item.caseType == 2}">赔偿款垫付</c:if>
                        <c:if test="${item.caseType == 3}">代办理赔</c:if>

                    </td>
                    <td>${item.returnCode}</td>
                    <td>${item.money}</td>
                    <td>
                        <c:if test="${item.state == 1}">待审核</c:if>
                        <c:if test="${item.state == 2}">驳回</c:if>
                        <c:if test="${item.state == 3}">已退款</c:if>
                    </td>
                    <td>${item.orgName}</td>
                    <td>${item.userName}</td>
                    <td>${item.reson}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td><fmt:formatDate value="${item.returnTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <th><c:if test="${item.state == 1}">
                        <a href="javascript:caseOrderReturnAuditThrough('${item.caseNo}','${item.orgId}','${item.userId}');">审核通过</a>
                        <a href="javascript:showCaseOrderReturnReject('${item.caseNo}','${item.userId}');">驳回</a>
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
            <jsp:param name="requestUrl" value="${ctx}/case/selectCaseOrderReturn?caseType=${caseType}&state=${state}&returnCode=${returnCode}&orgName=${orgName}&userName=${userName}&caseNo=${caseNo}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/
   function showCaseOrderReturnReject(caseNo,userId){
       openDialog({
           frame:true,
           title:"驳回原因",
           height:500,
           width:1000,
           url:"${ctx}/case/showCaseOrderReturnReject?caseNo="+caseNo+"&userId="+userId
       });
   }
   function caseOrderReturnAuditThrough(caseNo,orgId,userId){
       if(confirm('确认审核通过吗？')){
           ajaxSubmit("${ctx}/case/caseOrderReturnAuditThrough",{"caseNo":caseNo,"orgId":orgId,"userId":userId},function(v,e,p){
               if(e.data.code==='0000'){
                   alert("审核通过成功");
                   window.location.reload();
               }else{
                   alert("审核通过失败");
               }
           })
       }
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
