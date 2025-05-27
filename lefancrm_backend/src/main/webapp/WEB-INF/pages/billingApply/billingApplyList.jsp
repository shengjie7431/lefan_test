<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>开票列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/lefan14.css?a=1">
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="${ctx}/css/bootstrap-table-fixed-columns.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">

    <style ></style>
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
    </style>
    <style>
        .table-content {
            /*width: 1694px;*/
            overflow: auto;
            position: relative;
        }
        /*.tbody-div{*/
        /*overflow: auto;*/
        /*}*/

        /*.table-content td{*/
            /*word-break: break-all;*/
        /*}*/
        /*.table-content td:first-of-type{*/
            /*position: relative;*/
        /*}*/

        /*tbody {*/
            /*display:block;*/
            /*overflow:auto;*/
        /*}*/
        /*thead tr,tbody tr {*/
            /*display:table;*/
            /*table-layout: fixed;*/
        /*}*/
        /*thead{*/
            /*width: calc(100% - 1em);*/
        /*}*/

        .table-thead{
            padding: 30px 0;
        }

        .fixed-table-toolbar {
            display: none !important;
        }


        .th-inner,
        .fht-cell, table td {
            width: 160px;
            text-align: center;
        }

        .panel{
            margin-bottom: 0!important;
        }
        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }

        .export-btn{
            width: 90%;margin: 0 auto;text-align: center;display: flex;justify-content: center;
        }

    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>开票列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/billingApply/billingApplyList?pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <input type="hidden" name="menuType" value="${menuType}">
                    <input type="hidden" name="business" value="${business}">
                    <input type="hidden" name="size" id="size" value="${apiRsp.results.size()}">

                    <input type="hidden" name="businessTypeIds" id="businessTypeIds" />
                    <input type="hidden" name="staffOrgIds" id="staffOrgIds" value="${staffOrgIds}"/>
                    <input type="hidden" name="billingEnums" id="billingEnums" />
                    <input type="hidden" name="billingItems" id="billingItems" />
                    <input type="hidden" name="billingStates" id="billingStates" />
                    <input type="hidden" name="imgsStates" id="imgsStates" />
                    <input type="hidden" name="billingTypes" id="billingTypes" />
                    <input type="hidden" name="confirmAccountStates" id="confirmAccountStates" />

                    <div class="form-group">
                        <label class="title">案件编号:</label>
                        <input name="caseNo" type="text"  value="${caseNo}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="title">案件标题:</label>
                        <input name="caseTitle" type="text"  value="${caseTitle}" class="form-control">
                    </div>
<%--                    <div class="form-group">--%>
<%--                        <label class="title">公估机构:</label>--%>
<%--                        <div class="select-out-1101">--%>
<%--                            <select class="select form-control select-checkbox" name="orgIdGGsChk" data-select-name="orgIdGGs" data-select-values="${orgIdGGs}" multiple >--%>
<%--                                <c:forEach items="${orgInfoDtosGG}" var="itemOrg">--%>
<%--                                    <option value="${itemOrg.id}" >${itemOrg.orgName}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                        </div>--%>
<%--                    </div>--%>
                    <div class="form-group">
                        <label class="title" style="width: 100px;">收入归属机构:</label>
                        <div class="select-out-1101">
                            <select class="select form-control select-checkbox" name="staffOrgIdsChk" data-select-name="staffOrgIds" data-select-values="${staffOrgIds}" multiple >
                                <c:forEach items="${staffOrgans}" var="itemOrg">
                                    <option value="${itemOrg.id}" >${itemOrg.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

<%--                    <div class="form-group">--%>
<%--                        <label class="title">个人机构:</label>--%>
<%--                        <div class="select-out-1101">--%>
<%--                            <select class="select form-control select-checkbox" name="orgIdGRsChk" data-select-name="orgIdGRs"  data-select-values="${orgIdGRs}" multiple >--%>
<%--                                <c:forEach items="${orgInfoDtosGR}" var="itemOrg">--%>
<%--                                    <option value="${itemOrg.id}">${itemOrg.orgName}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                        </div>--%>
<%--                    </div>--%>
                    <div class="form-group">
                        <label class="title">开票公司:</label>
                        <div class="select-out-1101">
                            <select class="select form-control select-checkbox" name="businessTypeIdsChk" data-select-name="businessTypeIds"  data-select-values="${businessTypeIds}" multiple >
                                <c:forEach items="${corporations}" var="item">
                                    <option value="${item.id}">${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="title">到账状态:</label>
                        <div class="select-out-1101">
                            <select class="select form-control select-checkbox" name="confirmAccountStatesChk" data-select-name="confirmAccountStates"  data-select-values="${confirmAccountStates}" multiple >
                                <option value="1">未到账</option>
                                <option value="2">已到账</option>
                            </select>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <label class="title">开票产品:</label>
                        <div class="select-out-1101">
                            <select class="select form-control select-checkbox" name="billingEnumsChk" data-select-name="billingEnums"  data-select-values="${billingEnums}" multiple >
                                <c:forEach items="${bullingEnums}" var="item">
                                    <option value="${item.enumCode}">${item.enumName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="title">开票项目:</label>
                        <div class="select-out-1101">
                            <select class="select form-control select-checkbox" name="billingItemsChk" data-select-name="billingItems"  data-select-values="${billingItems}" multiple >
                                <c:forEach items="${bullingItems}" var="item">
                                    <option value="${item.enumCode}">${item.enumName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="title">开票类型:</label>
                        <div class="select-out-1101">
                            <select class="select form-control select-checkbox" name="billingTypesChk" data-select-name="billingTypes"  data-select-values="${billingTypes}" multiple >
                                <option value="1">专票</option>
                                <option value="2">普票</option>
                                <option value="3">电子普票</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="title">开票状态:</label>
                        <div class="select-out-1101">
                            <select class="select form-control select-checkbox" name="billingStatesChk" data-select-name="billingStates"  data-select-values="${billingStates}" multiple >
                                <c:if test="${menuType ==2}">
                                    <option value="1" >未申请</option>
                                </c:if>
                                <option value="2" >开票中</option>
                                <option value="3" >已开票</option>
                                <option value="9" >退票中</option>
                                <option value="4" >已退票</option>
                                <option value="5" >退票审核中</option>
                                <option value="6" >重开审核中</option>
                                <option value="7" >退票审核通过</option>
                                <option value="8" >重开审核通过</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="title">发票单号:</label>
                        <input name="billingCode" type="text"  value="${billingCode}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="title">发票状态:</label>
                        <div class="select-out-1101">
                            <select class="select form-control select-checkbox" name="imgsStatesChk" data-select-name="imgsStates"  data-select-values="${imgsStates}" multiple >
                                <option value="1">正常</option>
                                <option value="2">作废</option>
                                <option value="3">红冲</option>
                                <option value="4">待红冲</option>
                                <option value="5">待开票</option>
                            </select>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <label class="title">开票时间:</label>
                        <input name="startDate" type="text" value="${startDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="cursor: auto"  readonly>
                        <span>--</span>
                        <input name="endDate" type="text" value="${endDate}" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="cursor: auto"  readonly>
                    </div>
                    <c:if test="${menuType ==5 || menuType==2 || business == 2}">
                        <div class="form-group">
                            <label class="title">车牌号码:</label>
                            <input name="carNo" type="text"  value="${carNo}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="title">伤者姓名:</label>
                            <input name="woundedName" type="text"  value="${woundedName}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="title">被保人姓名:</label>
                            <input name="insuredName" type="text"  value="${insuredName}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label class="title">收件人姓名:</label>
                            <input name="recipientsName" type="text"  value="${recipientsName}" class="form-control">
                        </div>
                    </c:if>
                    <c:if test="${menuType ==5 || menuType==2 || business == 2 }">
                        <div class="form-group">
                            <label class="title">到账时间:</label>
                            <input name="confirmStartDate" type="text" value="${confirmStartDate}" class="form-control time" style="cursor: auto"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                            <span>--</span>
                            <input name="confirmEndDate" type="text" value="${confirmEndDate}" class="form-control time" style="cursor: auto"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        </div>
                        <div class="form-group">
                            <label class="title">开票对象:</label>
                            <input name="billingCompany" type="text"  value="${billingCompany}" class="form-control">
                        </div>
                    </c:if>
                    <div style="display: none;" class="form-group">
                        <label class="title">绩效所属:</label>
                        <input name="meritName" type="text"  value="${meritName}" class="form-control">
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        <c:if test="${menuType==2}">
                            <button onclick="addBillingApply()" type="button" class="btn btn-default">添加开票</button>
                        </c:if>
                        <button class="btn btn-default exportExcel" type="button" >导出</button>

                    <button class="btn btn-default" style="display: none"><a class="exportUrl" href="${ctx}/billingApply/billingApplyListExport?billingState=${billingState}&caseNo=${caseNo}&caseTitle=${caseTitle}&orgId=${orgId}&billingItem=${billingItem}&billingEnum=${billingEnum}&billingType=${billingType}&businessType=${businessType}&startDate=${startDate}&endDate=${endDate}&insuredName=${insuredName}&carNo=${carNo}&woundedName=${woundedName}&confirmStartDate=${confirmStartDate}&confirmEndDate=${confirmEndDate}&menuType=${menuType}&business=${business}&billingCode=${billingCode}&recipientsName=${recipientsName}&billingCompany=${billingCompany}&export=1&meritName=${meritName}&imgsState=${imgsState}&orgIdGR=${orgIdGR}&businessTypeIds=${businessTypeIds}&staffOrgIds=${staffOrgIds}&billingEnums=${billingEnums}&billingItems=${billingItems}&billingStates=${billingStates}&imgsStates=${imgsStates}&billingTypes=${billingTypes}&confirmAccountStates=${confirmAccountStates}">导出</a></button>
                    </div>
                </form>
            </div>
        </div>
        <div class="table-content">
            <table class="table table-striped " id="table">
                <thead class="thead-light">
                <tr id="tablehead">
                    <c:if test="${menuType==2}">
                        <th ></th>
                    </c:if>
                    <th >案件编号</th>
                    <th >案件标题</th>
                    <th >申请开票金额</th>
                    <th >申请开票金额(非税)</th>
                    <th >已开票金额</th>
                    <th >未开票金额</th>
                    <th >已到账金额</th>
                    <th >未到账金额</th>
                    <th >收入归属机构</th>
                    <th >开票状态</th>
                    <th >到账状态</th>
                    <th >开票产品</th>
                    <th >开票项目</th>
                    <c:if test="${menuType ==5 || business == 1 }">
                        <th >服务费金额</th>
                        <th >通道费</th>
                        <th >扣费金额</th>
                    </c:if>
                    <c:if test="${menuType ==5 || menuType==2 || business ==2 }">
                        <th >伤者姓名</th>
                        <th >被保人姓名</th>
                        <th >收件人姓名</th>
                    </c:if>
                    <th >开票类型</th>
                    <th >开票公司</th>
                    <c:if test="${menuType ==5 || menuType==2 || business ==2 }">
                        <th >开票对象</th>
                    </c:if>
<%--                    <th >所属机构</th>--%>
                    <c:if test="${menuType ==5 || menuType==2 || business ==2 }">
                        <th >车牌号</th>
                    </c:if>
                    <th >开票时间</th>
<%--                    <th >绩效所属</th>--%>
                    <th >操作</th>
                </tr>
                </thead>
                <tbody id="textTable" class="class-list">

                    <c:forEach items="${apiRsp.results}" var="item">
                        <tr <c:if test="${item.isRed}">style="color: red;" </c:if>>
                                <%--<td>${item.caseNo}</td>--%>
                            <c:if test="${menuType==2}">
                                <td ><a href="javascript:findBillingApplyDuplicate('${item.id}');">关联开票</a></td>
                            </c:if>
                            <td>
                                <c:if test="${menuType==2}">
                                    <a href="javascript:findBillingApplyView('${item.id}', 1, 2);">${item.caseNo}</a>
                                </c:if>
                                <c:if test="${menuType !=2}">
                                    <c:if test="${item.billingEnum == 7}">
                                        <a href="javascript:findBillApplyView('${item.id}','${item.caseId}','${item.caseNo}','${menuType}');">${item.caseNo}</a>
                                    </c:if>
                                    <c:if test="${item.billingEnum != 7}">
                                        <a href="javascript:findBillingApplyView('${item.id}',2,'${menuType}');">${item.caseNo}</a>
                                    </c:if>
                                </c:if>
                            </td>
                            <td >${item.caseTitle}</td>
                            <td ><fmt:formatNumber type="number" value="${item.billingMoney}" maxFractionDigits="2"/></td>
                            <c:if test="${item.taxRate != null}">
                                <td ><fmt:formatNumber type="number" value="${item.billingMoney/item.taxRate}" maxFractionDigits="2"/></td>
                            </c:if>
                            <c:if test="${item.taxRate == null}">
                                <td ><fmt:formatNumber type="number" value="${item.billingMoney}" maxFractionDigits="2"/></td>
                            </c:if>
<%--                            <td >${item.okBillMoney}</td>--%>
                            <td ><fmt:formatNumber type="number" value="${item.okBillMoney}" maxFractionDigits="2"/></td>
<%--                            <td >${item.noBillMoney}</td>--%>
                            <td ><fmt:formatNumber type="number" value="${item.noBillMoney}" maxFractionDigits="2"/></td>
<%--                            <td >${item.okAccountMoney}</td>--%>
                            <td ><fmt:formatNumber type="number" value="${item.okAccountMoney}" maxFractionDigits="2"/></td>
<%--                            <td >${item.noAccountMoney}</td>--%>
                           <td ><fmt:formatNumber type="number" value="${item.noAccountMoney}" maxFractionDigits="2"/></td>
                            <td >${item.staffOrgName}</td>
                            <td >
                                <c:if test="${item.billingItem != 12}">
                                    <c:if test="${item.billingState == 1}">未申请</c:if>
                                    <c:if test="${item.billingState == 2}">开票中</c:if>
                                    <c:if test="${item.billingState == 3}">已开票</c:if>
                                    <c:if test="${item.billingState == 9}">退票中</c:if>
                                    <c:if test="${item.billingState == 4}">已退票</c:if>
                                    <c:if test="${item.billingState == 5}">退票审核中</c:if>
                                    <c:if test="${item.billingState == 6}">重开审核中</c:if>
                                    <c:if test="${item.billingState == 7}">退票审核通过</c:if>
                                    <c:if test="${item.billingState == 8}">重开审核通过</c:if>
                                </c:if>
                                <c:if test="${item.billingItem == 12}">
                                    <c:if test="${item.billingState == 2}">待处理</c:if>
                                    <c:if test="${item.billingState != 2}">已处理</c:if>
                                </c:if>
                            </td>
                            <td >
                                <c:if test="${item.confirmAccountState == 1 || item.confirmAccountState == null}">未到账</c:if>
                                <c:if test="${item.confirmAccountState == 2}">已到账</c:if>
                                <c:if test="${item.confirmAccountState == 3}">部分到账</c:if>
                            </td>
                            <td >
                                <c:forEach items="${bullingEnums}" var="dto">
                                    <c:if test="${dto.enumCode == item.billingEnum}">
                                        ${dto.enumName}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td >
                                <c:forEach items="${bullingItems}" var="dto">
                                    <c:if test="${dto.enumCode == item.billingItem}">
                                        ${dto.enumName}
                                    </c:if>
                                </c:forEach>
                            </td>

                            <c:if test="${menuType ==5 || business == 1 }">
                                <td >${item.servcieMoney == null ? 0.0 : item.servcieMoney}</td>
                                <td >${item.channelMoney == null ? 0.0 : item.channelMoney}</td>
                                <td >${item.deductionMoney == null ? 0.0 : item.deductionMoney}</td>
                            </c:if>
                            <c:if test="${menuType ==5 || menuType==2 || business ==2 }">
                                <td >${item.woundedName}</td>
                                <td >${item.insuredName}</td>
                                <td >${item.recipientsName}</td>
                            </c:if>
                            <td >
                                <c:if test="${item.billingType == 1}">专票</c:if>
                                <c:if test="${item.billingType == 2}">普票</c:if>
                                <c:if test="${item.billingType == 3}">电子普票</c:if>
                            </td>
                            <td >
                                <c:forEach items="${corporations}" var="dto">
                                    <c:if test="${dto.id == item.businessType}">
                                        ${dto.name}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <c:if test="${menuType ==5 || menuType==2 || business ==2 }">
                                <td >${item.companyName}</td>
                            </c:if>
<%--                            <td >${item.orgName}</td>--%>

                            <c:if test="${menuType ==5 || menuType==2 || business ==2 }">
                                <td >${item.carNo}</td>
                            </c:if>
                            <td ><fmt:formatDate value="${item.billingTime}" pattern="yyyy-MM-dd"/></td>
<%--                            <td >${item.meritName}</td>--%>
                            <td >
                                <c:if test="${menuType==2}">
                                    <a href="javascript:findBillingApplyView('${item.id}', 1, 2);">查看</a>
                                    <c:if test="${item.billingState == 1}">
                                        <!--<a href="javascript:billingApplyDelete('${item.id}');">删除</a>-->
                                    </c:if>
                                    <a href="javascript:findBillingApplyDuplicate('${item.id}');">关联开票</a>
                                </c:if>
                                <c:if test="${menuType !=2}">
                                    <c:if test="${item.billingEnum == 7}">
                                        <a href="javascript:findBillApplyView('${item.id}','${item.caseId}','${item.caseNo}','${menuType}');">查看</a>
                                    </c:if>
                                    <c:if test="${item.billingEnum != 7}">
                                        <a href="javascript:findBillingApplyView('${item.id}',2,'${menuType}');">查看</a>
                                    </c:if>
                                </c:if>
                            </td>
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
            <jsp:param name="requestUrl" value="${ctx}/billingApply/billingApplyList?billingState=${billingState}&caseNo=${caseNo}&caseTitle=${caseTitle}&orgId=${orgId}&billingItem=${billingItem}&billingEnum=${billingEnum}&billingType=${billingType}&businessType=${businessType}&startDate=${startDate}&endDate=${endDate}&insuredName=${insuredName}&carNo=${carNo}&woundedName=${woundedName}&confirmStartDate=${confirmStartDate}&confirmEndDate=${confirmEndDate}&menuType=${menuType}&business=${business}&billingCode=${billingCode}&confirmAccountState=${confirmAccountState}&billingCompany=${billingCompany}&meritName=${meritName}&imgsState=${imgsState}&orgIdGR=${orgIdGR}&businessTypeIds=${businessTypeIds}&staffOrgIds=${staffOrgIds}&billingEnums=${billingEnums}&billingItems=${billingItems}&billingStates=${billingStates}&imgsStates=${imgsStates}&billingTypes=${billingTypes}&confirmAccountStates=${confirmAccountStates}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->
<script type="text/html" id="export-content">
    <div class="layui-form">
        <div class="layui-form-item">
            <div id="lf-export" class="selectMul" style="width: 80%;margin: 50px auto;"></div>
        </div>
        <div class="layui-inline export-btn">
            <label class="layui-form-label" >
                <button type="button" class="layui-btn s-btn layui-btn-primary" data-type="cancel">取消</button>
            </label>
            <label class="layui-form-label">
                <button type="button" class="layui-btn s-btn layui-btn-normal" data-type="export">确定</button>
            </label>
        </div>
    </div>
</script>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<%--<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>--%>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect.js?v=2"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap-table-fixed-columns.js"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })
   <%--var findBillingApplyView = function(id,mType) {--%>
       <%--openDialog({--%>
           <%--frame:true,--%>
           <%--title:"查看开票详情",--%>
           <%--height:800,--%>
           <%--width:1000,--%>
           <%--url:"${ctx}/billingApply/billingApplyView?id="+id+"&mType="+mType,--%>
           <%--load:true--%>

       <%--});--%>
   <%--}--%>

   layui.use(['layer','xmSelect'],function () {
       var layer = layui.layer,
           xmSelect = layui.xmSelect;
       $('.exportExcel').click(function () {
           openIndex = layer.open({
               type: 1,
               title: '导出',
               area: ['400px', '360px'],
               content: $('#export-content').html(),
               success: function () {
                   var exportDemo = xmSelect.render({
                       el: '#lf-export',
                       theme: {
                           color: '#3BA9FF',
                       },
                       radio: true,
                       model: {
                           label: {
                               type: 'xxxx', //自定义与下面的对应
                               xxxx: {
                                   template(data, sels) {

                                       if (sels.length == data.length) {
                                           return '<div>全部</div>'
                                       } else {
                                           var _html = ''
                                           sels.filter(function (cur) {
                                               _html +=
                                                   '<div class="xm-label-block lf-select-block">' + cur
                                                       .name + '</div>'
                                           })
                                           return _html
                                       }
                                   }
                               },
                           }
                       },
                       data: [{
                           'value': 'company',
                           'name': '按开票公司导出',
                           selected: true
                       }, {
                           'value': 'all',
                           'name': '总表导出'
                       }]
                   })
                   $('.export-btn button').click(function () {
                       var _this = $(this)
                       if (_this.attr('data-type') == 'cancel') {
                           layer.close(openIndex)
                       } else if (_this.attr('data-type') == 'export') {
                           if (!exportDemo.getValue('valueStr')){
                               layer.msg( '请选择',{
                                   time: 2000,
                                   icon: 5
                               })
                               return ;
                           }
                           var type = exportDemo.getValue('valueStr') == 'company' ? 'company' : ''

                           var aLink = document.createElement('a');
                           var _href = $('.exportUrl').attr('href')
                           _href += '&exportType='+type
                           aLink.href = _href
                           <%--aLink.href='${ctx}/billingApply/billingApplyListExport?billingState='+$('#billingState').val()+'&caseNo='+$('#caseNo').val()+'&caseTitle='+$('#caseTitle').val()+'&orgId='+$('#orgId').val()+'&billingItem='+$('#billingItem').val()+'&billingEnum='+$('#billingEnum').val()+'&billingType='+$('#billingType').val()+'&businessType='+$('#businessType').val()+'&startDate='+$('#startDate').val()+'&endDate='+$('#endDate').val()+'&insuredName='+$('#insuredName').val()+'&carNo='+$('#carNo').val()+'&woundedName='+$('#woundedName').val()+'&confirmStartDate='+$('#confirmStartDate').val()+'&confirmEndDate='+$('#confirmEndDate').val()+'&menuType='+$('#menuType').val()+'&business='+$('#business').val()+'&billingCode='+$('#billingCode').val()+'&recipientsName='+$('#recipientsName').val()+'&billingCompany='+$('#billingCompany').val()+'&export=1&meritName='+$('#meritName').val()+'&imgsState='+$('#imgsState').val()+'&orgIdGR='+$('#orgIdGR').val()+'&businessTypeIds='+$('#businessTypeIds').val()+'&orgIdGRs='+$('#orgIdGRs').val()+'&orgIdGGs='+$('#orgIdGGs').val()+'&billingEnums='+$('#billingEnums').val()+'&billingItems='+$('#billingItems').val()+'&billingStates='+$('#billingStates').val()+'&imgsStates='+$('#imgsStates').val()+'&billingTypes='+$('#billingTypes').val()+'&confirmAccountStates='+$('#confirmAccountStates').val()+'&exportType='+type--%>
                           aLink.dispatchEvent(new MouseEvent('click', {
                               bubbles: true,
                               cancelable: true,
                               view: window
                           }));
                           layer.close(openIndex)
                       }
                   })

               }
           });
       })

   })
   $("#batchOperateBtn").click(function(){
       //select 多选 赋值 到隐藏域。 便于后台传值
       $(".select-checkbox").each(function(){
           var selName = $(this).attr("name");
           var all = $(".UCSelect[name="+selName+"]").find(".UCSelectAll").hasClass("Selected");
           var name = $(this).attr("data-select-name");
           if(name){
               if (all){
                  $("#" + name).val("");
               }else{
                   $("#" + name).val($(this).val());
               }
           }
       })
   });

   var fixedNumber = '2';
   var menuType = $("#menuType").val();
   if(menuType == 2){
       fixedNumber = '3';
   }
    $(document).ready(function () {
        var table_height = $("#textTable").height();
        var height_doc = window.parent.innerHeight - 50 -27 -66 -$('.panel-heading').height() - 15 - 80 -10
        var height_body = table_height < height_doc ? table_height : height_doc;
        var size = $("#size").val();//size:根据案件的数量，决定table的高度
        if(size == 0){
            height_body = 80
        }
        if(size == 2){
            if ( height_body <= 100) {
                height_body = 100
            } else if (height_body > 100 && height_body < 150) {
                height_body = 150
            }else if (height_body >= 150 ) {
                height_body = 200
            }
        }
        if(size >= 3){
           if (height_body <= 200) {
                height_body = 200
            }else if (height_body > 200 && height_body <= 300 ) {
                height_body = 300
            }
        }
        $("#table").bootstrapTable('destroy').bootstrapTable({
            toolbar: "#toolbar",
            height: height_body,
            showColumns: true, //是否显示所有的列
            // pagination: true,
            fixedColumns: true,
            fixedNumber: fixedNumber,
            showHeader: true,
            theadClasses: 'table-thead',
            formatNoMatches : function(){
               return "暂无数据"
            }
        })
        //

        if(size ==1 ){
           $('.fixed-table-container').height($('.fixed-table-body tbody').height() +37)
        }

$('.table-content').scroll(function(){
    $('.table-content thead tr td:first-of-type').css({
        'position': 'absolate'
    })
    $('.table-content tbody tr td:first-of-type').css({
        'position': 'absolate'
    })
})
       //默认选中 select
       $(".select-checkbox").each(function(){
           var value = $(this).attr("data-select-values");
           if(value){
               $(this).val(value.replace(/\s*/g, '').split(','));
           }else{
               $(this).val("");
           }
       })
       $(".select-checkbox").UCFormSelect();

        // var orgIdGGs = $("#orgIdGGs").val()
        // var orgIdGRs = $("#orgIdGRs").val()
        // console.log("orgIdGGs",orgIdGGs);
        // console.log("orgIdGRs",orgIdGRs);
//        if(orgIdGRs!=null && orgIdGRs!=""){
//            $(".UCSelect[name='orgIdGGsChk']").find('.UCSelectAll').trigger("click");
//        }else{
//            $(".UCSelect[name='orgIdGRsChk']").find('.UCSelectAll').trigger("click"); // 初始化时:个人机构，默认不选中
//        }

       // $(".UCSelect[name='orgIdGGsChk']").find('.SelectBox dd').click(function(event){ // 选择公估机构时，个人机构全部不选中
       //     if (event && event.originalEvent && $("select[name='orgIdGRsChk']").val() != null){
       //         $(".UCSelect[name='orgIdGRsChk']").find('.UCSelectAll').addClass('Selected');
       //         $(".UCSelect[name='orgIdGRsChk']").find('.UCSelectAll').trigger("click");
       //     }
       // })
       //
       // $(".UCSelect[name='orgIdGRsChk']").find('.SelectBox dd').click(function(event){ // 选择个人机构时，公估机构全部不选中
       //     if (event && event.originalEvent && $("select[name='orgIdGGsChk']").val() != null){
       //         $(".UCSelect[name='orgIdGGsChk']").find('.UCSelectAll').addClass('Selected');
       //         $(".UCSelect[name='orgIdGGsChk']").find('.UCSelectAll').trigger("click");
       //     }
       // })

        // $(".UCSelect[name='staffOrgIdsChk']").find('.SelectBox dd').click(function(event){ // 选择个收入归属机构
        //     if (event && event.originalEvent && $("select[name='staffOrgIdsChk']").val() != null){
        //         $(".UCSelect[name='staffOrgIdsChk']").find('.UCSelectAll').addClass('Selected');
        //         $(".UCSelect[name='staffOrgIdsChk']").find('.UCSelectAll').trigger("click");
        //     }
        // })



   })

   //固定表格首列
   function freezing() {
      console.log( $(this).scrollLeft())
   }
   var addBillingApply = function(){
       var height = $(document).outerHeight() - 10;
       openDialog({
           frame:true,
           title:"添加开票",
           height:height,
           width:900,
           url:"${ctx}/billingApply/billingApplyAdd?billingSource=2"
       });
   }

   /**
    * 提交申请
    */
   function billingApplyDelete(id){
       <%--ajaxSubmit("${ctx}/billingApply/billingApplyDelete",{"id":id},reload,"删除成功！","确认删除？","删除失败！");--%>
       var url = "${ctx}/billingApply/billingApplyDelete",param = {"id":id};
       if(confirm('是否确认？')){
           ajaxSubmit(url,param,function(v,e,p){
            location.reload();
           })
       }
   }

   var findBillApplyView = function(id,caseId,caseNo,menuType) {
       openDialog({
           frame:true,
           title:"查看开票详情",
           height:850,
           width:1700,
           url:"${ctx}/suning/billApply/findBillApplyView?caseId="+caseId+"&caseNo="+caseNo+"&id="+id+"&menuType="+menuType,
           load:true

       });
   }

   var findBillingApplyView = function(id,mType,menuType) {
       var _height = $(document).outerHeight() * 0.96;
       var _width = $(document).outerWidth() * 0.96;
       openDialog({
           frame:true,
           title:"查看开票详情",
           height:_height,
           width:_width,
           url:"${ctx}/billingApply/billingApplyView?id="+id+"&mType="+mType+"&menuType="+menuType,
           load:true

       });
   }

   var findBillingApplyDuplicate = function(id) {
       var height = $(document).outerHeight() - 10;
       openDialog({
           frame:true,
           title:"新增开票",
           height:height,
           width:800,
           url:"${ctx}/billingApply/billApplyEdit?id="+id+"&copy="+1,
           load:true

       });
   }
</script>
</body>
</html>
