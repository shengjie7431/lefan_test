<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>案件列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/apply/list" method="post">
                    <div class="form-group">
                        案件类型: <select name="applyType" class="form-control">
                        <option value="" >全部</option>
                        <option value="1">医疗费垫付</option>
                        <option value="2">赔偿款垫付</option>
                        <option value="3">代理申请</option>
                        <option value="4">赔偿测算</option>
                        <option value="5">伤残预估</option>
                        <option value="6">推广大使</option>
                        <option value="7">提现申请</option>
                    </select>
                        申请人姓名: <input name="userName" type="text"  value="${userName}" class="form-control">
                        申请人电话: <input name="userTel" type="text"  value="${userTel}" class="form-control"><br>
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
                <th width="100">申请人姓名</th>
                <th width="100">申请人电话</th>
                <th width="100">案件类型</th>
                <th width="100">申请名称</th>
                <th width="100">申请时间</th>
                <th width="300">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td>${item.userName}</td>
                    <td>${item.userTel}</td>
                    <td>
                        <c:if test="${item.applyType == 1}">医疗费垫付</c:if>
                        <c:if test="${item.applyType == 2}">赔偿款垫付</c:if>
                        <c:if test="${item.applyType == 3}">代理申请</c:if>
                        <c:if test="${item.applyType == 4}">赔偿测算</c:if>
                        <c:if test="${item.applyType == 5}">伤残预估</c:if>
                        <c:if test="${item.applyType == 6}">推广大使</c:if>
                        <c:if test="${item.applyType == 7}">提现申请</c:if>
                    </td>
                    <td>${item.applyName}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <th>
                        <%--<c:if test="${item.caseState == 1}">--%>
                        <%--<a href="javascript:distribution('${item.caseId}','${item.caseState}','${item.orgId}');">分配</a></c:if> &nbsp;--%>
                        <%--<a href="javascript:selectCaseDetails('${item.type}','${item.caseId}',1);">案件经办跟踪</a>&nbsp;--%>
                        <a href="javascript:selectCaseDetails('${item.applyType}','${item.applyId}');">客服跟踪记录</a>&nbsp;
                        <a href="javascript:showOperatorFollowInfoEdit('${item.applyType}','${item.applyId}');">客服跟踪</a>
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
            <jsp:param name="requestUrl" value="${ctx}/apply/list?userName=${userName}&userTel=${userTel}&applyType=${applyType}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/
   function distribution(caseId){
       openDialog({
           frame:true,
           title:"案件分配",
           height:500,
           width:1000,
           url:"${ctx}/case/selectOrgInfo?caseId="+caseId
       });
   }

   function selectCaseDetails(type,caseId){
       openDialog({
           frame:true,
           title:"客服跟踪记录",
           height:500,
           width:1000,
           url:"${ctx}/apply/queryOperatorByTypeAndCaseId?type="+type+"&caseId="+caseId
       });
   }

   function showOperatorFollowInfoEdit(type,caseId){
       openDialog({
           frame:true,
           title:"添加状态",
           height:500,
           width:1000,
           url:"${ctx}/case/showOperatorFollowInfoEdit?type="+type+"&caseId="+caseId
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
