<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>个人业务案件财务台账</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <style>
        .table-content {
            /*width: 1694px;*/
            overflow: auto;
        }
        table th {
            text-align: center;
            border-left: 2px solid #ddd;
            vertical-align: middle!important;
        }
        thead{
            background-color: #ecf0f1;
        }
        table td {
            text-align: center;
            border-left: 1px solid #ddd;
        }
    </style>
    <style>
        .form-group{
            margin-bottom: 10px!important;
            padding-right: 16px;
        }
        .title{
            width: 85px;
            font-weight: normal;
        }
        .form-control{
            width: 160px!important;
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>个人业务案件财务台账 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/reportForm/casePersonalCwInfoList" method="post">
                    <div class="form-group">
                        <label class="title">案件编号:</label> <input name="caseNo" type="text" value="${caseNo}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="title">机构名称:</label>
                        <select name="orgId" class="form-control">
                            <option value="">全部</option>
                            <c:forEach items="${orgInfoDtos}" var="itemOrg">
                                <option <c:if test="${orgId == itemOrg.id}">selected="selected" </c:if> value="${itemOrg.id}" >${itemOrg.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">案件阶段:</label>
                        <select name="gradationState"  class="form-control">
                            <option value="" >全部</option>
                            <option value="洽谈阶段" <c:if test="${gradationState=='洽谈阶段'}">selected="selected" </c:if>>洽谈阶段</option>
                            <option value="评估阶段" <c:if test="${gradationState=='评估阶段'}">selected="selected" </c:if>>评估阶段</option>
                            <option value="索赔阶段" <c:if test="${gradationState=='索赔阶段'}">selected="selected" </c:if>>索赔阶段</option>
                            <option value="诉讼阶段" <c:if test="${gradationState=='诉讼阶段'}">selected="selected" </c:if>>诉讼阶段</option>
                            <option value="结案" <c:if test="${gradationState=='结案'}">selected="selected" </c:if>>结案</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="title">业务员:</label>
                        <input name="salesmanName" type="text" value="${salesmanName}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="title">委托人:</label>
                        <input name="clientName" type="text" value="${clientName}" class="form-control">
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        <button class="btn btn-default"><a href="${ctx}/reportForm/casePersonalCwInfoListExport?orgId=${orgId}&caseNo=${caseNo}&salesmanName=${salesmanName}&clientName=${clientName}&gradationState=${gradationState}&export=1">导出</a></button>
                    </div>
                </form>
            </div>
        </div>
        <div class="table-content">
            <table class="table table-striped">
                <thead>

                <tr>
                    <th style="min-width:100px" rowspan="2">案件编号</th>
                    <th style="min-width:180px" rowspan="2">案件状态</th>
                    <th style="min-width:100px" rowspan="2">案件阶段</th>

                    <th style="min-width:1200px" colspan="12">收入</th>
                    <th style="min-width:600px" colspan="6">成本&支出</th>
                    <th style="min-width:200px" colspan="2">医疗费垫付</th>


                    <th style="min-width:100px" rowspan="2">业务员姓名</th>
                    <th style="min-width:100px" rowspan="2">委托人</th>
                    <th style="min-width:100px" rowspan="2">索赔员姓名</th>
                    <th style="min-width:160px" rowspan="2">机构名称</th>
                    <th style="min-width:100px" rowspan="2">操作</th>
                </tr>


                <tr>
                    <th style="min-width:100px">审核服务费</th>
                    <th style="min-width:100px">应收基本费</th>
                    <th style="min-width:100px">应收尾款</th>
                    <th style="min-width:100px">应收利息</th>
                    <th style="min-width:100px">应收其他</th>
                    <th style="min-width:100px">收入小计</th>
                    <th style="min-width:100px">开票金额</th>
                    <th style="min-width:100px">发票号码</th>
                    <th style="min-width:100px">发票小计</th>
                    <th style="min-width:100px">基本费回款</th>
                    <th style="min-width:100px">服务费回款</th>
                    <th style="min-width:100px">回款小计</th>

                    <th style="min-width:100px">诉讼费金额</th>
                    <th style="min-width:100px">佣金金额</th>
                    <th style="min-width:100px">维护费金额</th>
                    <th style="min-width:100px">利息支出</th>
                    <th style="min-width:100px">其他支出</th>
                    <th style="min-width:100px">合计</th>

                    <th style="min-width:100px">垫付金额</th>
                    <th style="min-width:100px">回款金额</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${apiRsp.results}" var="item">
                    <tr>
                        <td><a href="javascript:casePersonalCwInfoView('${item.id}');">${item.caseNo}</a></td>
                        <td>${item.caseState}</td>
                        <td>${item.gradationState}</td>

                        <td>${item.checkServiceMoney ==null ?0.00 :item.checkServiceMoney}</td>
                        <td>${item.reBasicMoney ==null ?0.00 :item.reBasicMoney}</td>
                        <td>${item.reTailMoney ==null ?0.00 :item.reTailMoney}</td>
                        <td>${item.reInterestMoney ==null ?0.00 :item.reInterestMoney}</td>
                        <td>${item.reOtherMoney ==null ?0.00 :item.reOtherMoney}</td>
                        <td>${item.reTotalMoney ==null ?0.00 :item.reTotalMoney}</td>
                        <td>${item.billingMoney ==null ?0.00 :item.billingMoney}</td>
                        <td>${item.billingCode}</td>
                        <td>${item.billingTotalMoney ==null ?0.00 :item.billingTotalMoney}</td>
                        <td>${item.basicMoney ==null ?0.00 :item.basicMoney}</td>
                        <td>${item.returnMoney ==null ?0.00 :item.returnMoney}</td>
                        <td>${item.returnTotalMoney ==null ?0.00 :item.returnTotalMoney}</td>

                        <td>${item.litigationMoney ==null ?0.00 :item.litigationMoney}</td>
                        <td>${item.commissionMoney ==null ?0.00 :item.commissionMoney}</td>
                        <td>${item.maintainMoney ==null ?0.00 :item.maintainMoney}</td>
                        <td>${item.realInterestMoney ==null ?0.00 :item.realInterestMoney}</td>
                        <td>${item.otherCostMoney ==null ?0.00 :item.otherCostMoney}</td>
                        <td>${item.costTotle ==null ?0.00 :item.costTotle}</td>

                        <td>${item.realInterestMoney ==null ?0.00 :item.realInterestMoney}</td>
                        <td>${item.reLoanMoney ==null ?0.00 :item.reLoanMoney}</td>

                        <td>${item.salesmanName}</td>
                        <td>${item.clientName}</td>
                        <td>${item.claimName}</td>
                        <td>${item.orgName}</td>
                        <td><a href="javascript:casePersonalCwInfoView('${item.id}');">查看</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div><!--panel-info-->
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/reportForm/casePersonalCwInfoList?orgId=${orgId}&caseNo=${caseNo}&salesmanName=${salesmanName}&clientName=${clientName}&gradationState=${gradationState}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script>
    var casePersonalCwInfoView = function(id){
        openDialog({
            frame:true,
            title:"查看详情",
            height:650,
            width:1000,
            url:"${ctx}/reportForm/casePersonalCwInfoView?id="+id
        });
    }
</script>
</body>
</html>
