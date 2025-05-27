<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>风控案件列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>风控案件列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/case/risk/caseRiskList?" method="post">
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
                    <div class="form-group">
                        机构名称: <input name="orgName" type="text"  value="${orgName}" class="form-control">
                        </div>
                    <div class="form-group">
                        案件编号: <input name="caseNo" type="text"  value="${caseNo}" class="form-control">
                    </div>
                    <br><br>
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
                    <input type="hidden" name="riskType" value="${riskType}">
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
                <th width="80">审核状态</th>
                <th width="110">审核原因</th>
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
                    <td>
                        <c:if test="${riskType == 1}">
                            <c:if test="${item.issuanceState == null}"> 未申请审核</c:if>
                            <c:if test="${item.issuanceState == 0}"> 未审核</c:if>
                            <c:if test="${item.issuanceState == 1}"> 一审通过</c:if>
                            <c:if test="${item.issuanceState == 2}"> 一审未通过</c:if>
                            <c:if test="${item.issuanceState == 3}"> 二审通过</c:if>
                            <c:if test="${item.issuanceState == 4}"> 二审未通过</c:if>
                        </c:if>
                        <c:if test="${riskType == 2}">
                            <c:if test="${item.claimState == null}"> 未申请审核</c:if>
                            <c:if test="${item.claimState == 0}"> 审核未通过</c:if>
                            <c:if test="${item.claimState == 1}"> 审核通过</c:if>
                            <c:if test="${item.claimState == 2}"> 审核中</c:if>
                        </c:if>
                        <c:if test="${riskType == 3}">
                            <c:if test="${item.closedState == 0 || item.closedState == null}"> 未申请结案</c:if>
                            <c:if test="${item.closedState == 1}"> 结案审核中</c:if>
                            <c:if test="${item.closedState == 2}"> 结案通过</c:if>
                            <c:if test="${item.closedState == 3}"> 结案不通过</c:if>
                        </c:if>
                    </td>
                    <td >
                        <c:if test="${riskType == 1}">
                            ${item.issuanceReason}
                        </c:if>
                        <c:if test="${riskType == 2}">
                            ${item.claimReason}
                        </c:if>
                        <c:if test="${riskType == 3}">
                            ${item.closedReason}
                        </c:if>
                    </td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <c:if test="${riskType == 1}">
                            <c:if test="${item.issuanceState == 0}">
                                <a href="javascript:editRiskState('${item.id}','${riskType}',1);">一审通过</a>
                                <a  href="javascript:updateReson('${item.id}','${riskType}',2);">驳回</a>
                            </c:if>
                            <c:if test="${item.issuanceState == 1}">
                                <a href="javascript:issuanceState('${item.id}',3);">二审通过</a>
                                <a  href="javascript:issuanceReason('${item.id}',4);">驳回</a>
                            </c:if>
                            <a href="javascript:selectPaymentEstimateApply('${item.type}','${item.caseId}');">案件理赔测算历史记录</a>
                        </c:if>
                        <c:if test="${riskType == 2}">
                            <c:if test="${item.claimState == 2}">
                                <a href="javascript:editRiskState('${item.id}','${riskType}',1);">审核</a>
                                <a  href="javascript:updateReson('${item.id}','${riskType}',0);">驳回</a>
                                <%--<a href="javascript:selectPaymentEstimateApply('${item.type}','${item.caseId}');">案件理赔测算历史记录</a>--%>
                            </c:if>
                            <a href="javascript:queryCaseClaim('${item.id}');">查看索赔方案</a>
                        </c:if>
                        <c:if test="${riskType == 3}">
                            <c:if test="${item.closedState == 1}">
                                <a href="javascript:editRiskState('${item.id}','${riskType}',2);">审核</a>
                                <a  href="javascript:updateReson('${item.id}','${riskType}',3);">驳回</a>
                            </c:if>
                            <a href="javascript:queryCaseClaim('${item.id}');">查看索赔方案</a>
                        </c:if>
                         <a href="javascript:selectFileMid('${item.caseNo}');">查看单证</a>
                         <a href="javascript:selectCaseDetails('${item.type}','${item.caseId}',1,'${item.caseNo}');">案件经办跟踪</a>
                         <a href="javascript:editRiskControl('${item.id}','${item.caseNo}');">评估报告</a>
                         <a href="javascript:editAssessmentReport('${item.id}','${item.caseNo}');">公估报告</a>
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
            <jsp:param name="requestUrl" value="${ctx}/case/risk/caseRiskList?riskType=${riskType}&type=${type}&caseState=${caseState}&orgName=${orgName}&orgUserName=${orgUserName}&caseNo=${caseNo}&caseName=${caseName}&caseTel=${caseTel}" />
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
   function selectPaymentEstimateApply(type,caseId){
       openDialog({
           frame:true,
           title:"案件理赔测算历史记录",
           height:700,
           width:1000,
           url:"${ctx}/case/risk/selectPaymentEstimateApply?type="+type+"&caseId="+caseId
       });
   }
   function editRiskState(id,riskType,state){
       if(confirm('确定审核通过？')){
           ajaxSubmit("${ctx}/case/risk/editRiskState",{"id":id,"riskType":riskType,"state":state},function(v,e,p){
               alert(e.data.msg);
               location.reload();
           })
       }
   }
   function issuanceState(id,state){
       if(confirm('确定审核通过？')){
           ajaxSubmit("${ctx}/case/risk/issuanceState",{"id":id,"state":state},function(v,e,p){
               alert(e.data.msg);
               location.reload();
           })
       }
   }
   var updateReson = function(id,riskType,state){
       openDialog({
           frame:true,
           title:"驳回风控案件",
           height:400,
           width:600,
           url:"${ctx}/case/risk/caseRiskReson?id="+id+"&riskType="+riskType+"&state="+state
       });
   }

   var issuanceReason = function(id,state){
       openDialog({
           frame:true,
           title:"驳回风控案件",
           height:400,
           width:600,
           url:"${ctx}/case/risk/issuanceReason?id="+id+"&state="+state
       });
   }
   var queryCaseClaim = function(id){
       openDialog({
           frame:true,
           title:"索赔方案",
           height:700,
           width:600,
           url:"${ctx}/case/risk/caseClaim?id="+id
       });
   }
   var editAssessmentReport = function (caseId,caseNo) {
       openDialog({
          frame:true,
          title:"公估报告",
           height:900,
           width:1000,
           url:"${ctx}/case/report/editAssessmentReport?caseId="+caseId+"&caseNo="+caseNo
       });
   }
   var editRiskControl = function (caseId,caseNo) {
       openDialog({
           frame:true,
           title:"评估报告",
           height:900,
           width:1000,
           url:"${ctx}/case/report/editRiskControl?caseId="+caseId+"&caseNo="+caseNo
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
