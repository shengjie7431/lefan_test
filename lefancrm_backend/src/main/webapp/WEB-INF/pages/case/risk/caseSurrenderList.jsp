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
                <form class="form-inline" role="form" action="${ctx}/case/surrender" method="post">
                    <div class="form-group">
                        案件类型: <select name="type"  class="form-control">
                        <option value="" >全部</option>
                        <option value="1">贷款申请</option>
                        <option value="2">代理申请</option>
                        <option value="3">伤残预估</option>
                        <option value="4">其他</option>
                    </select>
                    </div>
                    <div class="form-group">
                       案件状态: <select name="caseState"  class="form-control">
                            <option value="" >全部</option>
                            <option value="1">待接收</option>
                            <option value="2">已接收</option>
                            <option value="30">已拒绝</option>
                            <option value="3">已预约</option>
                            <option value="4">已放弃</option>
                            <option value="5">已签约</option>
                            <option value="6">待跟进</option>
                            <option value="7">虚假信息</option>
                            <option value="8">已受理</option>
                        </select>
                        </div>
                        <%----%>
                    <div class="form-group">
                        机构名称: <input name="orgName" type="text"  value="${orgName}" class="form-control">
                        </div>
                    <div class="form-group">
                        被分配人名称: <input name="orgUserName" type="text"  value="${orgUserName}" class="form-control">
                        </div>
                    <br><br>
                    <div class="form-group">
                        案件编号: <input name="caseNo" type="text"  value="${caseNo}" class="form-control">
                        </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="form-group">
                        申请人姓名: <input name="caseName" type="text"  value="${caseName}" class="form-control">
                        </div>
                    &nbsp;&nbsp;
                    <div class="form-group">
                        申请人电话: <input name="caseTel" type="text"  value="${caseTel}" class="form-control">
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
                <th width="120">类型</th>
                <th width="90">阶段</th>
                <th width="90">状态</th>
                <th width="300">案件标题</th>
                <th width="100">申请人姓名</th>
                <th width="80">申请人电话</th>
                <th width="160">业务员姓名</th>
                <th width="200">被分配公司</th>
                <th width="80">被分配人</th>
                <th width="110">保险员</th>
                <th width="200">创建时间</th>
                <th width="300">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td></td>
                    <td>${item.caseNo}</td>
                    <td>
                        <c:if test="${item.type == 1}">贷款申请</c:if>
                        <c:if test="${item.type == 2}">代理申请</c:if>
                        <c:if test="${item.type == 3}">伤残预估</c:if>
                        <c:if test="${item.type == 4}">其他</c:if>
                    </td>
                    <c:if test="${item.gradationState == 1}">
                        <td style="color:#FF4500;">洽谈业务</td>
                    </c:if>
                    <c:if test="${item.gradationState == 2}">
                        <td style="color:#66CD00;">评估阶段</td>
                    </c:if>
                    <c:if test="${item.gradationState == 3}">
                        <td  style="color:#66CD00;">索赔阶段</td>
                    </c:if>
                    <c:if test="${item.gradationState == 4}">
                        <td  style="color:#1E90FF;">结案</td>
                    </c:if>
                    <c:if test="${item.gradationState == 5}">
                        <td  style="color:#1E90FF;">风控部门审核阶段</td>
                    </c:if>

                    <td>${item.caseStateStr}</td>
                    <td>${item.caseTitle}</td>
                    <td>${item.caseName}</td>
                    <td>${item.caseTel}</td>
                    <td>${item.salesmanName}</td>
                    <td>${item.orgName}</td>
                    <td>${item.orgUserName}</td>
                    <c:if test="${item.insOfficerId != null}">
                        <td style="color: green;">${item.insOfficerName}</td>
                    </c:if>
                    <c:if test="${item.insOfficerId == null}">
                        <td style="color: red;">待分配保险员</td>
                    </c:if>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                         <a href="javascript:dissolutionAudit('${item.id}',0);">审核通过</a>
                        <a href="javascript:dissolutionAudit('${item.id}',1);">审核不通过</a>
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
            <jsp:param name="requestUrl" value="${ctx}/case/surrender?type=${type}&caseState=${caseState}&orgName=${orgName}&orgUserName=${orgUserName}&caseNo=${caseNo}&caseName=${caseName}&caseTel=${caseTel}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/
   function dissolutionAudit(id,state){
       ajaxSubmit("${ctx}/case/dissolutionAudit?id="+id+"&state="+state,{},function(){location.reload();},state==0?"审核通过成功":"审核不通过成功",state==0?"确认审核通过吗？":"确认审核不通过吗？",null);
   }
</script>
</body>
</html>
