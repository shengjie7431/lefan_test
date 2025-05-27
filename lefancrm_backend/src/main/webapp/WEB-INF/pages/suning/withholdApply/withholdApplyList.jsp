<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>苏宁代扣确认到账列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>苏宁代扣确认到账列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">

            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/suning/withholdApply/list?" method="post">
                    <div class="form-group">
                        案件编号: <input name="caseNo" type="text"  value="${caseNo}" class="form-control">
                    </div>
                    <div class="form-group">
                        案件标题: <input name="caseTitle" type="text"  value="${caseTitle}" class="form-control">
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
                <th width="80">案件编号</th>
                <th width="300">案件标题</th>
                <th width="100">代扣金额</th>
                <th width="160">代扣时间</th>
                <th width="100">代扣状态</th>
                <th width="100">到账类型</th>
                <th width="100">代扣类型</th>
                <th width="200">备注</th>
                <th width="200">到账操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td>${item.caseNo}</td>
                    <td>${item.caseTitle}</td>
                    <td>${item.withholdMoney}</td>
                    <td><fmt:formatDate value="${item.withholdTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td >
                        <c:if test="${item.withholdState == 0}">
                            代扣申请
                        </c:if>
                        <c:if test="${item.withholdState == 1}">
                            代扣中
                        </c:if>
                        <c:if test="${item.withholdState == 2}">
                            代扣成功
                        </c:if>
                        <c:if test="${item.withholdState == 3}">
                            <span style="color: #ff0000">代扣失败</span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${item.accountState == 0}">
                            未确认
                        </c:if>
                        <c:if test="${item.accountState == 1}">
                            已确认
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${item.withholdType == 1}">
                            现金
                        </c:if>
                        <c:if test="${item.withholdType == 3}">
                            转账
                        </c:if>
                        <c:if test="${item.withholdType == 2}">
                            苏宁代扣
                        </c:if>
                        <c:if test="${item.withholdType == 4}">
                            平安代扣
                        </c:if>
                    </td>
                    <td>
                        ${item.remark}
                    </td>
                    <td>
                        <a href="javascript:findWithholdApplyView('${item.id}','${item.caseNo}');">查看</a>
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
            <jsp:param name="requestUrl" value="${ctx}/suning/withholdApply/findWithholdApplyListByWithHoldState" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    /* var commentDelete = function(commentid){
     ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
     }*/

    var findWithholdApplyView = function (id,caseNo) {
        openDialog({
            frame:true,
            title:"查看确认到账详情",
            height:600,
            width:1000,
            url:"${ctx}/suning/withholdApply/findWithholdApplyView?id="+id+"&caseNo="+caseNo
        });
    }





</script>
</body>
</html>
