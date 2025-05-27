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
                <form class="form-inline" role="form" action="${ctx}/case/caseCenterInfoList" method="post">
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
                            <option value="3">已预约</option>
                            <option value="4">已放弃</option>
                            <option value="5">已签约</option>
                            <option value="6">待跟进</option>
                            <option value="7">虚假信息</option>
                            <option value="8">已受理</option>
                        </select>
                        </div>
                        <%----%>
                    <br>
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
                        <td></td>
                    </c:if>
                    <td>${item.caseStateStr}</td>
                    <td>${item.caseTitle}</td>
                    <td>${item.caseName}</td>
                    <td>${item.caseTel}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                         <a href="javascript:selectFileMid('${item.caseNo}');">查看单证</a>
                        <c:if test="${isRole == 0}">
                            <a href="javascript:caseMediation('${item.caseId}','${item.type}');">查看报告</a>
                            <a href="javascript:selectCaseDetails('${item.type}','${item.caseId}',1,'${item.caseNo}');">案件经办跟踪</a>
                        </c:if>
                        <c:if test="${isRole == 1}">
                            <c:if test="${item.caseState == 1}"><a href="javascript:distribution('${item.caseId}','${item.caseState}','${item.orgId}');">分配机构</a></c:if> &nbsp;
                            <a href="javascript:selectCaseDetails('${item.type}','${item.caseId}',1,'${item.caseNo}');">案件经办跟踪</a>&nbsp;
                            <c:if test="${item.caseState == 3}">
                                <a href="javascript:selectCaseBespeakInfo('${item.type}','${item.caseId}');">查看预约详情</a>
                            </c:if>
                            <c:if test="${item.caseState == 4}">
                                <a href="javascript:selectCaseEntrustInfo('${item.type}','${item.caseId}');">查看放弃详情</a>
                            </c:if>
                            <c:if test="${item.caseState == 5}">
                                <a href="javascript:selectCaseEntrustInfo('${item.type}','${item.caseId}');">查看签约详情</a>
                            </c:if>
                            <c:if test="${item.caseState == 6}">
                                <a href="javascript:selectCaseEntrustInfo('${item.type}','${item.caseId}');">查看待跟进详情</a>
                            </c:if>
                            <c:if test="${item.caseState == 7}">
                                <a href="javascript:selectCaseEntrustInfo('${item.type}','${item.caseId}');">查看虚假信息详情</a>
                            </c:if>
                            <a href="javascript:checkInsOfficer('${item.id}');">分配保险员</a>&nbsp
                        </c:if>
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
            <jsp:param name="requestUrl" value="${ctx}/case/caseCenterInfoList?type=${type}&caseState=${caseState}&orgName=${orgName}&orgUserName=${orgUserName}&caseNo=${caseNo}&caseName=${caseName}&caseTel=${caseTel}" />
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
           title:"",
           height:500,
           width:1000,
           url:"${ctx}/case/selectOrgInfo?caseId="+caseId
       });
   }
   function checkInsOfficer(caseId){
       openDialog({
           frame:true,
           title:"分配保险员",
           height:150,
           width:800,
           url:"${ctx}/case/toInsOfficerCheck?caseId="+caseId
       });
   }
   function selectCaseDetails(type,caseId,caseType,caseNo){
       openDialog({
           frame:true,
           title:"案件详情",
           height:500,
           width:1000,
           url:"${ctx}/case/selectCaseDetails?type="+type+"&caseId="+caseId+"&caseType="+caseType+"&caseNo="+caseNo
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

   function selectCaseBespeakInfo(type,caseId){
       openDialog({
           frame:true,
           title:"预约详情",
           height:500,
           width:1000,
           url:"${ctx}/case/selectCaseBespeakInfo?type="+type+"&caseId="+caseId
       });
   }
   function selectCaseEntrustInfo(type,caseId){
       openDialog({
           frame:true,
           title:"委托详情",
           height:500,
           width:1000,
           url:"${ctx}/case/selectCaseEntrustInfo?type="+type+"&caseId="+caseId
       });
   }
   function selectFileMid(caseNo){
       openDialog({
           frame:true,
           title:"查看单证",
           height:600,
           width:1000,
           url:"${ctx}/case/selectFileMid?caseNo="+caseNo
       });
   }
   function caseMediation(caseId,type){
       openDialog({
           frame:true,
           title:"查看调解报告",
           height:600,
           width:1000,
           url:"${ctx}/case/caseMediation?caseId="+caseId+"&caseType="+type
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
