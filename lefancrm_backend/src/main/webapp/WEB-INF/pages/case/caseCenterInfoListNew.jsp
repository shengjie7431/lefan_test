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
                <form class="form-inline" role="form" action="${ctx}/case/list?listType=${listType}" method="post">
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
                <th width="200">审核原因</th>
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
                    <td>
                        ${item.operReason}
                        <%--<c:if test="${listType == 1}">--%>
                            <%--${item.issuanceReason}--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${listType == 6}">--%>
                            <%--${item.claimReason}--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${listType == 77}">--%>

                        <%--</c:if>--%>
                        <%--<c:if test="${listType == 9}">--%>
                            <%--${item.closedReason}--%>
                        <%--</c:if>--%>
                    </td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>
                        <c:if test="${listType == 1}">
                            <a href="javascript:operate(1,'${item.id}','${item.caseNo}','pass','${item.reportCaseNo}','${item.controlCaseNo}');">提交审核</a>
                        </c:if>
                        <c:if test="${listType == 2}">
                            <a href="javascript:operate(2,'${item.id}','${item.caseNo}','pass');">提交一审</a>
                            <a href="javascript:operate(2,'${item.id}','${item.caseNo}','back');">退回</a>
                        </c:if>
                        <c:if test="${listType == 3}">
                            <a href="javascript:operate(3,'${item.id}','${item.caseNo}','pass');">提交二审</a>
                            <a href="javascript:operate(3,'${item.id}','${item.caseNo}','back');">退回</a>
                        </c:if>
                        <c:if test="${listType == 4}">
                            <a href="javascript:operate(4,'${item.id}','${item.caseNo}','pass');">提交贷款</a>
                        </c:if>
                        <c:if test="${listType == 5}">
                            <a href="javascript:operate(5,'${item.id}','${item.caseNo}','pass');">放款确认</a>
                        </c:if>
                        <c:if test="${listType == 6}">
                            <a href="javascript:operate(6,'${item.id}','${item.caseNo}','pass');">提交审核</a>
                        </c:if>
                        <c:if test="${listType == 7}">
                            <a href="javascript:operate(7,'${item.id}','${item.caseNo}','pass');">通过</a>
                            <a href="javascript:operate(7,'${item.id}','${item.caseNo}','back');">退回</a>
                        </c:if>
                        <c:if test="${listType == 77}">
                            <a href="javascript:operate(77,'${item.id}','${item.caseNo}','pass','${item.closeCaseNo}');">提交审核</a>
                            <a href="javascript:editCloseCase('${item.id}','${item.caseNo}','edit');">制作结案报告</a>
                        </c:if>
                        <c:if test="${listType == 777}">
                            <a href="javascript:operate(777,'${item.id}','${item.caseNo}','pass');">通过</a>
                            <a href="javascript:operate(777,'${item.id}','${item.caseNo}','back');">退回</a>
                            <a href="javascript:editCloseCase('${item.id}','${item.caseNo}','edit');">结案报告</a>
                        </c:if>
                        <c:if test="${listType == 8}">
                            <a href="javascript:operate(8,'${item.id}','${item.caseNo}','pass');">发起代扣</a>
                            <a href="javascript:editCloseCase('${item.id}','${item.caseNo}','edit');">结案报告</a>
                        </c:if>
                        <c:if test="${listType == 9}">
                            <a href="javascript:operate(9,'${item.id}','${item.caseNo}','pass','${item.closeCaseNo}');">发起结案申请</a>
                            <a href="javascript:editCloseCase('${item.id}','${item.caseNo}','edit');">结案报告</a>
                        </c:if>
                        <c:if test="${listType == 10}">
                            <a href="javascript:operate(10,'${item.id}','${item.caseNo}','pass');">审核</a>
                            <a href="javascript:operate(10,'${item.id}','${item.caseNo}','back');">退回</a>
                            <a href="javascript:editCloseCase('${item.id}','${item.caseNo}','view');">结案报告</a>
                        </c:if>
                        <c:if test="${listType == 12}">
                            <a href="javascript:operate(12,'${item.id}','${item.caseNo}','pass');">补票</a>
                            <a href="javascript:operate(12,'${item.id}','${item.caseNo}','view');">查看开票记录</a>
                            <a href="javascript:editCloseCase('${item.id}','${item.caseNo}','view');">结案报告</a>
                        </c:if>
                        <c:if test="${listType == 13}">
                            <a href="javascript:operate(13,'${item.id}','${item.caseNo}','pass');">紧急代扣</a>
                            <a href="javascript:operate(13,'${item.id}','${item.caseNo}','editBank');">编辑银行卡</a>
                        </c:if>
                        <c:if test="${listType == 14}">
                            <a href="javascript:operate(14,'${item.id}','${item.caseNo}','pass');">再次审核</a>
                        </c:if>
                        <c:if test="${listType == 20}">
                            <a href="javascript:operate(20,'${item.id}','${item.caseNo}','pass');">接收</a>
                            <a href="javascript:operate(20,'${item.id}','${item.caseNo}','back');">拒绝</a>
                        </c:if>

                        <c:if test="${listType == 0 or listType == 1 or listType == 2 or listType == 3 or listType == 4 or listType == 5 or listType == 6 or listType == 7 or listType == 14}">
                            <a href="javascript:editRiskControl('${item.id}','${item.caseNo}','edit');">评估报告</a>
                            <a href="javascript:editAssessmentReport('${item.id}','${item.caseNo}','edit');">公估报告</a>
                            <a href="javascript:caseMediation('${item.id}','${item.caseNo}','edit');">索赔方案</a>
                            <a href="javascript:editCloseCase('${item.id}','${item.caseNo}','view');">结案报告</a>
                        </c:if>
                        <a href="javascript:selectFileMid('${item.caseNo}');">查看单证</a>
                        <a href="javascript:selectCaseDetails('${item.type}','${item.caseId}',1,'${item.caseNo}');">案件经办跟踪</a>
                        <%--<c:if test="${item.caseState == 3}">--%>
                            <%--<a href="javascript:selectCaseBespeakInfo('${item.type}','${item.caseId}');">查看预约详情</a>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${item.caseState == 4}">--%>
                            <%--<a href="javascript:selectCaseEntrustInfo('${item.type}','${item.caseId}');">查看放弃详情</a>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${item.caseState == 5}">--%>
                            <%--<a href="javascript:selectCaseEntrustInfo('${item.type}','${item.caseId}');">查看签约详情</a>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${item.caseState == 6}">--%>
                            <%--<a href="javascript:selectCaseEntrustInfo('${item.type}','${item.caseId}');">查看待跟进详情</a>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${item.caseState == 7}">--%>
                            <%--<a href="javascript:selectCaseEntrustInfo('${item.type}','${item.caseId}');">查看虚假信息详情</a>--%>
                        <%--</c:if>--%>
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
            <jsp:param name="requestUrl" value="${ctx}/case/list?listType=${listType}&caseNo=${caseNo}&caseName=${caseName}&caseTel=${caseTel}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->
</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    function operate(listType,id,caseNo,operateType,caseNo1,caseNo2){
        if(operateType == 'pass'){
            if(listType == 5 || listType == 13){//弹出放款确认界面
                openDialog({
                    frame:true,
                    title:listType == 5 ? "放款确认" : "紧急代扣",
                    height:500,
                    width:800,
                    url:"${ctx}/case/pass?id="+id+"&listType="+listType+"&caseNo="+caseNo//弹出通过界面
                });
            }else if(listType == 8){
                openDialog({
                    frame:true,
                    title:"发起代扣",
                    height:400,
                    width:600,
                    url:"${ctx}/suning/withholdApply/pass?passType=passSuning&id="+id+"&listType="+listType+"&caseNo="+caseNo//弹出通过界面
                });
            }else if(listType == 12){
                openDialog({
                    frame:true,
                    title:"补票",
                    height:400,
                    width:600,
                    url:"${ctx}/case/openBill?id="+id+"&listType="+listType+"&caseNo="+caseNo//弹出通过界面
                });
            }

            else{
                if(listType == 1){
                    if(caseNo1 == null || caseNo1 == ''){
                        alert('未填写公估报告');return;
                    }
                    if(caseNo2 == null || caseNo2 == ''){
                        alert('未填写评估报告');return;
                    }
                }
                if(listType == 9 || listType == 77){
                    if(caseNo1 == null || caseNo1 == ''){
                        alert('未填写结案报告');return;
                    }
                }

                if(confirm('确定？')){
                    ajaxSubmit("${ctx}/case/operate",{"id" : id,"caseNo":caseNo,"listType" : listType,"operateType" : operateType},function(v,e,p){
                        alert(e.data.msg);
                        location.reload();
                    })
                }
            }
        }else if ("view" == operateType){
            openDialog({
                frame:true,
                title:"开票列表",
                height:400,
                width:900,
                url:"${ctx}/suning/billApply/list?id="+id+"&listType="+listType+"&caseNo="+caseNo+"&op=view"//
            });
        }else if("editBank" == operateType){
            openDialog({
                frame:true,
                title:"银行卡信息",
                height:500,
                width:800,
                url:"${ctx}/suning/bankCardInfo/editBankCardInfo?caseId="+id+"&listType="+listType+"&caseNo="+caseNo+"&operateType="+operateType//弹出通过界面
            });
        }
        else{
            openDialog({
                frame:true,
                title:"退回原因",
                height:400,
                width:600,
                url:"${ctx}/case/back?id="+id+"&listType="+listType//弹出退回界面
            });
        }
    }
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
   function caseMediation(caseId,caseNo,op){
       openDialog({
           frame:true,
           title:"索赔方案",
           height:900,
           width:1000,
           url:"${ctx}/case/caseMediation?caseId="+caseId+"&caseNo="+caseNo+"&op="+op
       });
   }

    var editAssessmentReport = function (caseId,caseNo,op) {
        openDialog({
            frame:true,
            title:"公估报告",
            height:900,
            width:1000,
            url:"${ctx}/case/report/editAssessmentReport?caseId="+caseId+"&caseNo="+caseNo+"&op="+op
        });
    }
    var editRiskControl = function (caseId,caseNo,op) {
        openDialog({
            frame:true,
            title:"评估报告",
            height:900,
            width:1000,
            url:"${ctx}/case/report/editRiskControl?caseId="+caseId+"&caseNo="+caseNo+"&op="+op
        });
    }
    var editCloseCase = function (caseId,caseNo,op) {
        openDialog({
            frame:true,
            title:"结案报告",
            height:900,
            width:1000,
            url:"${ctx}/case/report/editCloseReport?caseId="+caseId+"&caseNo="+caseNo+"&op="+op
        });
    }
</script>
</body>
</html>
