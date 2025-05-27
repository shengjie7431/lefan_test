<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>付款列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">
    <style>
        .form-group{
            margin-bottom: 10px!important;
            padding-right: 16px;
        }
        .time{
            width: 130px!important;
        }
        .title{
            width: 85px;
            font-weight: normal;
        }
        .form-control{
            width: 160px!important;
        }
        .dalogs{
            display: none;
            position: absolute;
            bottom: 30%;
            left: 20%;
            width: 300px;
            height: 200px;
            background-color: #fff;
            box-shadow: 0 0 10px #999;
            color: #000;
            z-index: 9;
        }

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
        .pot-none{
            pointer-events: none;
        }
        .unit{
            position: absolute;
            top: 17px;
            right: 40px;
        }
        .pageSizeContent{
            display: none!important;
        }
        .v-p-files{
            color: #3ba9ff;
        }
    </style>
</head>
<body>
<div class="main administrator">
<div class="main-top">
    <h3>付款列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
</div><!--main-top-->
<div class="panel panel-info">
<div class="panel-heading">
    <div class="pin">
        <form class="form-inline" role="form" action="${ctx}/survey/pay/list" method="post">
            <input type="hidden" name="menuCode" value="${menuCode}">

            <%--多选--%>
            <input type="hidden" name="surveyOrgIds" id="surveyOrgIds" />
            <input type="hidden" name="sourceSupportTypes" id="sourceSupportTypes" />
            <input type="hidden" name="payStates" id="payStates" />
            <input type="hidden" name="companyIds" id="companyIds" />
            <input type="hidden" name="organIds" id="organIds" />
            <input type="hidden" name="departmentIds" id="departmentIds" />
            <input type="hidden" name="teamIds" id="teamIds" />
            <input type="hidden" name="jobPostIds" id="jobPostIds" />
            <input type="hidden" name="payTypes" id="payTypes" />

            <div class="form-group">
                付款编号:<input name="payNo" type="text"  value="${payNo}" class="form-control">
            </div>
            <%--<div class="form-group">
                调查机构:
                &lt;%&ndash;<select name="orgId" id="orgId" class="singleSelect form-control">
                    <option value="">全部</option>
                    <c:forEach items="${franchisees}" var="item">
                        <option <c:if test="${orgId == item.id}">selected="selected" </c:if> value="${item.id}" >${item.name}</option>
                    </c:forEach>
                </select>&ndash;%&gt;
                <div>
                    <select class="select form-control select-checkbox" name="surveyOrgIdsChk" data-select-name="surveyOrgIds" data-select-values="${surveyOrgIds}" multiple >
                        <c:forEach items="${franchisees}" var="item">
                            <option value="${item.id}" >${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>--%>
            <div class="form-group">
                公司:
                <div>
                    <select class="select form-control select-checkbox" name="companyIdsChk" data-select-name="companyIds" data-select-values="${companyIds}" multiple >
                        <c:forEach items="${companys}" var="item">
                            <option value="${item.id}" >${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                机构/部门:
                <div>
                    <select class="select form-control select-checkbox" name="organIdsChk" data-select-name="organIds" data-select-values="${organIds}" multiple >
                        <c:forEach items="${organs}" var="item">
                            <option value="${item.id}" >${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                科室:
                <div>
                    <select class="select form-control select-checkbox" name="departmentIdsChk" data-select-name="departmentIds" data-select-values="${departmentIds}" multiple >
                        <c:forEach items="${departments}" var="item">
                            <option value="${item.id}" >${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                小组:
                <div>
                    <select class="select form-control select-checkbox" name="teamsChk" data-select-name="teamIds" data-select-values="${teamIds}" multiple >
                        <c:forEach items="${teams}" var="item">
                            <option value="${item.id}" >${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                岗位:
                <div>
                    <select class="select form-control select-checkbox" name="jobPostIdsChk" data-select-name="jobPostIds" data-select-values="${jobPostIds}" multiple >
                        <c:forEach items="${jobPosts}" var="item">
                            <option value="${item.id}" >${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                姓名:<input name="realName" type="text"  value="${realName}" class="form-control">
            </div>
            <br>
<%--            <div class="form-group">--%>
<%--                案源机构:--%>
<%--                &lt;%&ndash;<select name="sourceSupportType"  class="form-control">--%>
<%--                    <option value=""  <c:if test="${sourceSupportType == ''}">selected="selected" </c:if> >全部</option>--%>
<%--                    <option value="1" <c:if test="${sourceSupportType == '1'}">selected="selected" </c:if> >市场营销一部</option>--%>
<%--                    <option value="2" <c:if test="${sourceSupportType == '2'}">selected="selected" </c:if> >市场营销二部</option>--%>
<%--                    <option value="3" <c:if test="${sourceSupportType == '3'}">selected="selected" </c:if> >互助</option>--%>
<%--                    <option value="4" <c:if test="${sourceSupportType == '4'}">selected="selected" </c:if> >正言</option>--%>
<%--                </select>&ndash;%&gt;--%>
<%--                <div>--%>
<%--                    <select class="select form-control select-checkbox" name="sourceSupportTypesChk" data-select-name="sourceSupportTypes"  data-select-values="${sourceSupportTypes}" multiple >--%>
<%--                        <option value="1">市场营销一部</option>--%>
<%--                        <option value="2">市场营销二部</option>--%>
<%--                        <option value="3">互助</option>--%>
<%--                        <option value="4">正言</option>--%>
<%--                    </select>--%>
<%--                </div>--%>
<%--            </div>--%>
            <div class="form-group">
                付款类型:
                <div>
                    <select class="select form-control select-checkbox" name="payTypeChk" data-select-name="payTypes"  data-select-values="${payTypes}" multiple >
                        <option value="1">调查费</option>
                        <option value="2">费用报销</option>
                        <option value="3">渠道费用报销</option>
                        <option value="4">员工工资</option>
                        <option value="5">员工绩效</option>
                        <option value="6">离职预报销</option>
                        <option value="7">退费</option>
                        <option value="8">垫付业务</option>
                        <option value="9">日常费用报销</option>
                        <option value="10">对公支付</option>
                        <option value="11">借款单</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                付款状态:
                <%--<select name="payState"  class="form-control">
                    <option value=""  <c:if test="${payState == ''}">selected="selected" </c:if> >全部</option>
                    <option value="1" <c:if test="${payState == '1'}">selected="selected" </c:if> >待付款</option>
                    <option value="2" <c:if test="${payState == '2'}">selected="selected" </c:if> >待确认到账</option>
                    <option value="3" <c:if test="${payState == '3'}">selected="selected" </c:if> >已确认到账</option>
                    <option value="4" <c:if test="${payState == '4'}">selected="selected" </c:if> >已驳回</option>
                </select>--%>
                <div>
                    <select class="select form-control select-checkbox" name="payStatesChk" data-select-name="payStates"  data-select-values="${payStates}" multiple >
                        <option value="1">待付款</option>
<%--                        <option value="2">待确认到账</option>--%>
                        <option value="3">已确认到账</option>
                        <%--<option value="4">已驳回</option>--%>
                    </select>
                </div>
            </div>

            <div class="form-group">
                申请时间:
                <input name="startCreateDate" type="text" value="${startCreateDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly>
                <span>--</span>
                <input name="endCreateDate" type="text" value="${endCreateDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
            </div>
            <div class="form-group">
                付款时间:
                <input name="startPayTime" type="text" value="${startPayTime}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly>
                <span>--</span>
                <input name="endPayTime" type="text" value="${endPayTime}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
            </div>
            <div class="btn-group">
                <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                <button id="payMore" type="button" class="btn btn-default">批量付款</button>&nbsp; &nbsp;
                <button class="btn btn-default"><a class="exportUrl" href="${ctx}/survey/pay/export?payNo=${payNo}&realName=${realName}&orgId=${orgId}&payState=${payState}&sourceSupportType=${sourceSupportType}&startCreateDate=${startCreateDate}&endCreateDate=${endCreateDate}&startPayTime=${startPayTime}&endPayTime=${endPayTime}&menuCode=${menuCode}&surveyOrgIds=${surveyOrgIds}&sourceSupportTypes=${sourceSupportTypes}&payStates=${payStates}&companyIds=${companyIds}&organIds=${organIds}&departmentIds=${departmentIds}&teamIds=${teamIds}&jobPostIds=${jobPostIds}&payTypes=${payTypes}">导出</a></button>
                <button class="btn btn-default"><a class="exportUrl" href="${ctx}/survey/pay/export?payNo=${payNo}&realName=${realName}&orgId=${orgId}&payState=${payState}&sourceSupportType=${sourceSupportType}&startCreateDate=${startCreateDate}&endCreateDate=${endCreateDate}&startPayTime=${startPayTime}&endPayTime=${endPayTime}&menuCode=${menuCode}&surveyOrgIds=${surveyOrgIds}&sourceSupportTypes=${sourceSupportTypes}&payStates=${payStates}&companyIds=${companyIds}&organIds=${organIds}&departmentIds=${departmentIds}&teamIds=${teamIds}&jobPostIds=${jobPostIds}&payTypes=${payTypes}&exportNew=yes">导出(新)</a></button>
                <button class="btn btn-default"><a class="exportUrl" href="${ctx}/survey/pay/export?payNo=${payNo}&realName=${realName}&orgId=${orgId}&payState=${payState}&sourceSupportType=${sourceSupportType}&startCreateDate=${startCreateDate}&endCreateDate=${endCreateDate}&startPayTime=${startPayTime}&endPayTime=${endPayTime}&menuCode=${menuCode}&surveyOrgIds=${surveyOrgIds}&sourceSupportTypes=${sourceSupportTypes}&payStates=${payStates}&companyIds=${companyIds}&organIds=${organIds}&departmentIds=${departmentIds}&teamIds=${teamIds}&jobPostIds=${jobPostIds}&payTypes=${payTypes}&exportNew=yesPrint">批量打印</a></button>

            </div>
        </form>
    </div>
</div>
<div class="table-content">
    <table class="table table-striped">
        <thead>
        <tr>
            <th><input type="checkbox" class="checkAll"></th>
            <c:if test="${menuCode == 'payInfo'}">
                <th style="min-width:150px">操作</th>
            </c:if>
            <th style="min-width:120px">付款编号</th>
            <th style="min-width:120px">付款类型</th>
            <%--<th style="min-width:200px">调查机构</th>
            <th style="min-width:120px">调查员</th>--%>
<%--            <th style="min-width:100px">案源机构</th>--%>
            <th style="min-width:120px">公司</th>
            <th style="min-width:120px">机构/部门</th>
            <th style="min-width:100px">收款人姓名</th>
            <th style="min-width:100px">银行名称</th>
            <th style="min-width:100px">银行账号</th>
            <th style="min-width:120px">姓名</th>
            <th style="min-width:150px">申请付款金额</th>
            <th style="min-width:120px">备注</th>
            <th style="min-width:100px">实际所得金额</th>
            <th style="min-width:100px">实际支付金额</th>
<%--            <th style="min-width:100px">税费</th>--%>
            <th style="min-width:100px">付款状态</th>
            <th style="min-width:120px">科室</th>
            <th style="min-width:120px">小组</th>
            <th style="min-width:120px">岗位</th>
            <th style="min-width:100px">申请时间</th>
            <th style="min-width:100px">实际付款时间</th>
            <th style="min-width:100px">财务人员</th>

            <c:if test="${menuCode == 'appList'}">
                <th>驳回原因</th>
            </c:if>
        </tr>
        </thead>
        <tbody class="class-list">
        <c:forEach items="${apiRsp.results}" var="item">
            <tr>
            <td>
                <c:if test="${item.payState == 1}">
                    <input type="checkbox" class="checkOne"  name="checkItem" data-id="${item.id}">
                </c:if>
                <c:if test="${item.payState == 2 || item.payState == 3}">
                    <input type="checkbox"  name="checkItem" data-id="${item.id}" disabled>
                </c:if>
            </td>
            <c:if test="${menuCode == 'payInfo'}">
                <td>
                    <c:if test="${item.payState == 1}">
                        <a href="javascript:void(0);" onclick="operate(${item.id},'ok-pay')" style="color:#3BA9FF">确认付款</a>
                        <%--                        <a href="javascript:void(0);" onclick="operate(${item.id},'veto-pay')">驳回</a>--%>
                    </c:if>
                    <c:if test="${item.payState == 2 || item.payState == 3}">
                        <a href="javascript:void(0);" class="v-p-files" data-img="${item.payImgUrl}">查看凭证(${item.images!=null?item.images.size():0})</a>
                    </c:if>
                    <c:if test="${item.payType == 9 || item.payType == 10 || item.payType == 11}">
                        <br/><a style="color:#3BA9FF" href="javascript:void(0);" class="financial-a" data-id="${item.id}" data-type="1">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
                             <a style="color:#3BA9FF" href="javascript:void(0);" class="financial-a" data-id="${item.id}"  data-type="2">打印</a>
                    </c:if>
                </td>
            </c:if>
            <td>${item.payNo}</td>
            <td>
                <c:if test="${item.payType == 1}">调查费</c:if>
                <c:if test="${item.payType == 2}">费用报销</c:if>
                <c:if test="${item.payType == 3}">渠道费用报销</c:if>
                <c:if test="${item.payType == 4}">员工工资</c:if>
                <c:if test="${item.payType == 5}">员工绩效</c:if>
                <c:if test="${item.payType == 6}">离职预报销</c:if>
                <c:if test="${item.payType == 7}">退费</c:if>
                <c:if test="${item.payType == 8}">垫付业务</c:if>
                <c:if test="${item.payType == 9}">日常费用报销</c:if>
                <c:if test="${item.payType == 10}">对公支付</c:if>
                <c:if test="${item.payType == 11}">借款单</c:if>
            </td>
            <%--<td>${item.orgName}</td>
            <td>${item.paySurveyUserName}</td>--%>
<%--            <td>--%>
<%--                <c:if test="${item.sourceSupportType == 1}">市场营销一部</c:if>--%>
<%--                <c:if test="${item.sourceSupportType == 2}">市场营销二部</c:if>--%>
<%--                <c:if test="${item.sourceSupportType == 3}">互助</c:if>--%>
<%--                <c:if test="${item.sourceSupportType == 4}">正言</c:if>--%>
<%--            </td>--%>
            <td>${item.socialSecurityCompany}</td>
            <td>${item.organ}</td>
            <td>
                <c:if test="${item.payType==null || item.payType==1}">${item.surveyFranchisee.acceptUser}</c:if>
                <c:if test="${item.payType==2 || item.payType == 3 || item.payType == 7}">${item.surveyInvestigatorDto.realName}</c:if>
                <c:if test="${item.payType== 8}">${item.finaHospitalAccount.accountName}</c:if>
                <c:if test="${item.payType==9 || item.payType == 10 || item.payType == 11}">${item.financialReApply.payeeName}</c:if>
            </td>
            <td>
                <c:if test="${item.payType==null || item.payType==1}">${item.surveyFranchisee.bankName}</c:if>
                <c:if test="${item.payType==2 || item.payType == 3 || item.payType == 7}">${item.surveyInvestigatorDto.bankName}</c:if>
                <c:if test="${item.payType== 8}">${item.finaHospitalAccount.bankName}</c:if>
                <c:if test="${item.payType==9 || item.payType == 10 || item.payType == 11}">${item.financialReApply.bankName}</c:if>
            </td>
            <td>
                <c:if test="${item.payType==null || item.payType==1}">${item.surveyFranchisee.bankCard}</c:if>
                <c:if test="${item.payType==2 || item.payType == 3 || item.payType == 7}">${item.surveyInvestigatorDto.bankNo}</c:if>
                <c:if test="${item.payType== 8}">${item.finaHospitalAccount.hospitalAccount}</c:if>
                <c:if test="${item.payType==9 || item.payType == 10 || item.payType == 11}">${item.financialReApply.payeeNo}</c:if>
            </td>
            <td>${item.realName}</td>
<%--            <td>${item.appPayMoney}</td>--%>
                <td><fmt:formatNumber value="${item.appPayMoney}" pattern="#,##0.00"/></td>
            <td  title="${item.remark}">
                <c:if test="${item.payType != 8}">${item.remark}</c:if>
                <c:if test="${item.payType == 8}">
                    <a href="javascript:void(0);" onclick="openFinaInfo(${item.payKeyId})" style="color:#3BA9FF">查看详情</a>
                </c:if>

            </td>
            <td>${item.realIncomeMoney}</td>
            <td>${item.realPayMoney}</td>
<%--            <td>${item.payTax}</td>--%>
            <td>
                <c:if test="${item.payState == 1}">待付款</c:if>
                <c:if test="${item.payState == 2}">待确认到账</c:if>
                <c:if test="${item.payState == 3}">已确认到账</c:if>
                <%--<c:if test="${item.payState == 4}">已驳回</c:if>--%>
            </td>
<%--            <c:if test="${item.payType==null || item.payType==1}">
                <td>${item.surveyFranchisee.acceptUser}</td>
                <td>${item.surveyFranchisee.bankName}</td>
                <td>${item.surveyFranchisee.bankCard}</td>
            </c:if>
            <c:if test="${item.payType==2 || item.payType == 3}">
                <td>${item.surveyInvestigatorDto.realName}</td>
                <td>${item.surveyInvestigatorDto.bankName}</td>
                <td>${item.surveyInvestigatorDto.bankNo}</td>
            </c:if>--%>
            <td>${item.department}</td>
            <td>${item.team}</td>
            <td>${item.jobPost}</td>
            <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatDate value="${item.payTime}" pattern="yyyy-MM-dd"/></td>
            <td>${item.payUserName}</td>

            <c:if test="${menuCode == 'appList'}">
                <td>
                    <c:if test="${item.opinion != null && item.opinion != ''}">
                        <a href="javascript:void(0);" title="${item.opinion}" onclick="showRemark(2,this)" data-value="${item.opinion}">详情</a>
                    </c:if>
                </td>
            </c:if>
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
        <jsp:param name="requestUrl" value="${ctx}/survey/pay/list?payNo=${payNo}&orgId=${orgId}&payState=${payState}&sourceSupportType=${sourceSupportType}&startCreateDate=${startCreateDate}&endCreateDate=${endCreateDate}&startPayTime=${startPayTime}&endPayTime=${endPayTime}&menuCode=${menuCode}&surveyOrgIds=${surveyOrgIds}&sourceSupportTypes=${sourceSupportTypes}&payStates=${payStates}&companyIds=${companyIds}&organIds=${organIds}&departmentIds=${departmentIds}&teamIds=${teamIds}&jobPostIds=${jobPostIds}&payTypes=${payTypes}&realName=${realName}" />
        <jsp:param name="refreshDiv" value="" />
    </jsp:include>
</div><!--main-bottom-->
    <div class="dalogs">
        <div class="close" onclick="javascript:$('.dalogs').hide()">×</div>
        <div class="d_content" style="padding: 20px;"></div>
    </div>

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script type="text/html" id="payMoreContent">
    <div class="layui-form" style="padding-top: 30px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 160px;">手续费率:</label>
                <div class="layui-input-inline">
                    <input type="number" min="0" id="payTaxRate" name="payTaxRate" value="5.8" class="layui-input"><div class="unit">%</div>
                </div>
            </div>
            <div class="layui-inline">
                <%--<label class="layui-form-label" style="width: 160px;">实际付款金额:</label>--%>
                <%--<div class="layui-input-inline">--%>
                    <%--<input type="text" value="与申请付款金额相同" class="layui-input" readonly>--%>
                <%--</div>--%>
                <label class="layui-form-label" style="width: 160px;">实际付款金额百分比:</label>
                <div class="layui-input-inline">
                    <input type="number" min="0" id="payRate" name="payRate" value="100" class="layui-input"><div class="unit">%</div>
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label" style="width: 160px;">实际付款时间:</label>
                <div class="layui-input-inline">
                    <input type="text" name="payTime" class="layui-input" id="payTimeStr" placeholder="请选择实际付款时间" style="cursor: pointer" readonly>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-primary close-reason">取消</button>
                <button class="layui-btn layui-btn-normal submit-reason">确认批量付款
                </button>
            </div>
        </div>
    </div>
</script>


<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect.js?V=1"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>

<script>
//    $(function () {
//        $('.singleSelect').select2();
//    });
$('.checkAll').click(function () {
    var _this = $(this)
    if (_this.attr('checked') == 'checked'){
        $('.class-list .checkOne').attr('checked','checked')
    }else {
        $('.class-list .checkOne').removeAttr('checked')
    }
})

layui.use(['layer','laydate','jquery'],function () {
    var layer = layui.layer,
        laydate = layui.laydate,
        layui$ = layui.jquery

    $('#payMore').click(function () {
        var checkList = $('.class-list .checkOne')
        var ids = []
        checkList.map(function (i,cur) {
            if ($(cur).attr('checked') == 'checked'){
                ids.push($(cur).attr('data-id'))
            }
        })
        if (ids.length){
            reasonIndex = layer.open({
                type: 1,
                title: '批量付款',
                area: ['500px', '300px'],
                content: $('#payMoreContent').html(),
                success: function () {
                    payTime = laydate.render({
                        elem: '#payTimeStr',
                        value: new Date()
                    });
                }
            });
            $('.submit-reason').click(function () {
                $('.submit-reason').addClass('pot-none')
                setTimeout(function () {
                    $('.submit-reason').removeClass('pot-none')
                },5000)
                var params = {
                    ids: ids.join(','),
                    actualPaymentTime: $('#payTimeStr').val(),
                    batchOkPay: 'batchOkPay',
                    btnCode: 'ok-pay',
                    payRate : $('#payRate').val(),
                    payTaxRate : $('#payTaxRate').val()
                }
                if (!$('#payTimeStr').val()){
                    layer.msg('请选择实际付款时间',{icon: 0});
                    $('.submit-reason').removeClass('pot-none')
                    return;
                }
                layui$.ajax({
                    url: '${ctx}/survey/pay/operate',
                    type:'post',
                    data: params,
                    success: function (res) {
                        res = JSON.parse(res)
                        if (res.isSuccess){
                            layer.msg(res.msg,{icon: 1});
                        }else {
                            layer.msg(res.msg,{icon: 2});
                        }
                        location.href='${ctx}/survey/pay/list?payNo=${payNo}&orgId=${orgId}&payState=${payState}&sourceSupportType=${sourceSupportType}&startCreateDate=${startCreateDate}&endCreateDate=${endCreateDate}&startPayTime=${startPayTime}&endPayTime=${endPayTime}&menuCode=${menuCode}&surveyOrgIds=${surveyOrgIds}&sourceSupportTypes=${sourceSupportTypes}&payStates=${payStates}&companyIds=${companyIds}&organIds=${organIds}&departmentIds=${departmentIds}&teamIds=${teamIds}&jobPostIds=${jobPostIds}&payTypes=${payTypes}';
                    }

                })
                layer.close(reasonIndex)
            })
            $('.close-reason').click(function () {
                layer.close(reasonIndex)
            })
        }else {
            layer.msg('至少勾选一行数据',{icon: 5});
        }
    })
    $(".financial-a").click(function () {
        var _this = $(this);
        var _id = _this.attr("data-id");
        var _type = _this.attr("data-type");
        layui$.ajax({
            url: '${ctx}/survey/pay/export',
            type:'post',
            data: {
                id : _id,
                type : _type,
                dataType : "financial"
            },
            success: function (res) {
                res = JSON.parse(res)
                if (res){
                    if (res.fileUrl){
                        var tempwindow = window.open('_blank');
                        tempwindow.location = res.fileUrl
                        // window.location.href = res.fileUrl;
                    }
                }else{
                    alert("下载失败");
                }
            }
        })
    })
})


$('.v-p-files').on('click', function () {
    var width= $(document.body).outerWidth();
    var height = $(document).outerHeight() - 20;
    var title = "凭证详情";
    var imgStr = $(this).attr("data-img");
    sessionStorage.setItem("imgStr",imgStr);
    openDialog({
        frame:true,
        title:title,
        height:height,
        width:width,
        url:"${ctx}/survey/pay/operateView?btnCode=filesView"
    });
});
$("#batchOperateBtn").click(function(){
    //select 多选 赋值 到隐藏域。 便于后台传值
    $(".select-checkbox").each(function(){
        var name = $(this).attr("data-select-name");
        if(name){
            $("#" + name).val($(this).val());
        }
    })
});

$(document).ready(function () {
    //默认选中 select
    $(".select-checkbox").each(function () {
        var value = $(this).attr("data-select-values");
        if (value) {
            $(this).val(value.replace(/\s*/g, '').split(','));
        } else {
            $(this).val("");
        }
    })
    $(".select-checkbox").UCFormSelect();

    var height_doc = window.parent.innerHeight - 50 -27 -66 -$('.panel-heading').height() - 60
    $(".table-content").height(height_doc).css({
        overflow: 'auto'
    });

})
    var operate = function(id,btnCode){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        var title = "";
        if(btnCode == 'ok-pay'){
            title = "确认付款";
        }else if(btnCode == 'veto-pay'){
            title = "驳回原因"
        }
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:"${ctx}/survey/pay/operateView?id=" + id + "&btnCode=" + btnCode,
            load:true
        });
    }

    function showRemark(type,obj){
        var dialog = $(".dalogs");
        if(dialog.is(":hidden")){
            dialog.show();
        }else{
            dialog.hide();
        }
        var position = $(obj).position();
        var left = position.left;
        if (type ==2 ){
            left = left - 360
        }else{
            left = left + 60
        }
        $(".dalogs").offset({
            left: left,
            top: position.top - 60
        });
        var content = $(obj).attr("data-value");
        $(".d_content").html(content);
    }

    var openFinaInfo = function(finaInfoId){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        var title = "垫付案件详情";
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:"${ctx}/fina/applicant/info?finaInfoId=" + finaInfoId + "&display=true",
            load:true
        });
    }
</script>
</body>
</html>
