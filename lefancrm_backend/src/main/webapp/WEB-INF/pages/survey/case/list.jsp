<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <%--<link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">--%>
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">
    <style>
        .dalogs{
            display: none;
            position: absolute;
            bottom: 30%;
            left: 20%;
            width: 400px;
            height: 200px;
            background-color: #fff;
            box-shadow: 0 0 10px #999;
            color: #000;
        }

        .title_sort{
            display: flex;
            align-items: center;
            cursor: pointer;
        }
        .icon-sort {
            display: inline-block;
            width: 10px;
            padding-left: 2px;
        }
        .icon-sort  .icon-up.active {
            border-bottom: 7px solid #333;
        }

        .icon-sort  .icon-down.active {
            border-top: 7px solid #333;
        }

        .icon-up {
            width: 0;
            height: 0;
            border-right: 5px solid transparent;
            border-left: 5px solid transparent;
            border-bottom: 7px solid #b3b3b3;
            margin-bottom: 2px;
        }

        .icon-down {
            width: 0;
            height: 0;
            border-right: 5px solid transparent;
            border-left: 5px solid transparent;
            border-top: 7px solid #b3b3b3;
        }

        .icon-up:hover {
            border-bottom: 7px solid #333333c2;
        }

        .icon-down:hover {
            border-top: 7px solid #333333c2;
        }

        .dalog {
            display: none;
            margin: 0 auto;
            /*margin-top: 20%;*/
            width: 508px;
            height: 418px;
            line-height: 20px;
            text-align: center;
            border: 1px solid rgba(187, 187, 187, 1);
            font-size: 14px;
            z-index: 1001;
            background-color: #fff;
        }

        .dalog .d-title {
            width: 100%;
            height: 20px;
            line-height: 20px;
            padding: 30px 0;
            color: rgba(16, 16, 16, 1);
            font-size: 16px;
            text-align: center;
        }

        .dalog .d-cell {
            width: 90%;
            margin: 0 auto;
            padding: 10px;
            display: flex;
        }

        .dalog .d-cell .d-cell__label {
            display: inline-block;
            width: 150px;
            height: 30px;
            line-height: 30px;
            font-size: 14px;
            text-align: right;
        }

        .dalog .d-cell .d-cell__input {
            position: relative;
            display: inline-block;
            padding-left: 10px;
            width: 230px;
            height: 30px;
            line-height: 30px;
            font-size: 14px;
            display: flex;
        }
        .dalog .d-cell .d-cell__input--disabled input{
            background-color: rgba(179,179,179,0.25);
        }

        .dalog .d-cell .d-cell__input input {
            display: inline-block;
            width: 190px;
            /*padding: 0 10px;*/
            /*padding-right: 40px;*/
            height: 28px;
            line-height: 28px;
            border: 1px solid #bbb;
            font-size: 14px;
            text-align: right;
        }

        .dalog .d-cell .d-cell__input span {
            position: absolute;
            right: 10px;
            display: inline-block;
            width: 16px;
            height: 30px;
            line-height: 30px;
            font-size: 14px;
            text-align: center;
        }

        .dalog .d-cell .d-cell__textarea {
            display: inline-block;
            padding-left: 10px;
            width: 190px;
            height: 104px;
            font-size: 14px;
        }

        .dalog .d-cell .d-cell__textarea textarea {
            padding: 10px;
            width: 190px;
            height: 82px;
            line-height: 24px;
            border: 1px solid #bbb;
            font-size: 14px;
        }

        .dalog .d-btns {
            width: 320px;
            margin: 0 auto;
            padding-top: 30px;
            display: flex;
            justify-content: space-between;
        }

        .dalog .d-btns .d-btn {
            width: 130px;
            height: 34px;
            line-height: 34px;
            border: 1px solid #bbb;
            background-color: #fff;
            cursor: pointer;
        }

        .dalog .d-btns .d-btn:hover {
            box-shadow: 0px 0px 10px #bbb;
        }

        .dalog .d-btns .d-btn__active {
            background-color: #3ba9ff;
            color: #fff;
        }

        .dalog__1 {
            /* display: block; */
        }

        .sum{
            width: 100%;
        }
        .sum-name{

        }
        .sum-num{
            color: #FF0000;
        }

        .main-bottom{
            position: relative;
        }

        .panel-info{
            margin-bottom: 60px;
        }
        .pagePosi{
            position: fixed;
            bottom: 0;
            width: 100%;
            background-color: #fff;
            padding:6px 0;
        }


        .main-top h3{
            margin: 0!important;
        }

        .span-sd{
            width: 60px;
            height: 25px;
            background-color: red;
            color: #ffffff;
            display: table-cell;
            text-align: center;
            vertical-align: middle;
        }

        label.layui-form-label {
            width: auto;
            padding: 6px 0;
            margin-bottom: 0;
        }

    </style>
</head>
<body>
<%--平台复审-保司 代理保司终审--%>
<div class="main administrator">
    <div class="main-top">
        <h3>案件列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/survey/case/list?menuCode=${menuCode}&pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <%--多选--%>
                    <input type="hidden" name="surveyOrgIds" id="surveyOrgIds" />
                    <input type="hidden" name="entrustOrgIds" id="entrustOrgIds" />
                    <input type="hidden" name="surveyPhases" id="surveyPhases" />
                    <input type="hidden" name="orgAssigns" id="orgAssigns" />
                    <input type="hidden" name="assignStates" id="assignStates" />
                    <input type="hidden" name="isPayEntrustFees" id="isPayEntrustFees" />
                    <input type="hidden" name="surveyStates" id="surveyStates" />
                    <input type="hidden" name="serviceTypes" id="serviceTypes" />
                        <input type="hidden" name="finalInfos" id="finalInfos" />
                        <input type="hidden" id="order" value="${order}"  name="order" />
                        <input type="hidden" id="colSortType" value="${colSortType}" name="colSortType" />

                    <%--使用场景：“”录入案件时，可直接“分派调查员”--%>
                    <input type="hidden" name="autoOpenInfo" id="autoOpenInfo" value="${autoOpenInfo}"/>
                    <input type="hidden" name="autoOpenInfoId" id="autoOpenInfoId" value="${autoOpenInfoId}"/>

                    <div class="form-group">
                        快捷查询:<input style="width: 410px" name="searchStr" type="text" value="${searchStr}" placeholder="可输入被调查人，案件编号，调查编号，联系方式，身份证号" class="form-control">
                    </div>
<%--                    <div class="form-group">--%>
<%--                        案件编号:<input name="surveyCaseNo" type="text" value="${surveyCaseNo}" class="form-control">--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        调查编号:<input name="surveyNo" type="text" value="${surveyNo}" class="form-control">--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                       被调查人:<input name="surveyPerson" type="text" value="${surveyPerson}" class="form-control">--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                       联系方式:<input name="surveryPersonTel" type="text" value="${surveryPersonTel}" class="form-control">--%>
<%--                    </div>--%>
                    <div class="form-group">
                        <c:if test="${menuCode != 'guide-list'}">保险公司:</c:if>
                        <c:if test="${menuCode == 'guide-list'}">互助平台:</c:if>
                        <%--<select name="entrustOrgId" id="entrustOrgId" class="singleSelect form-control">
                            <option value="">全部</option>
                            <c:forEach items="${consignors}" var="item">
                                <option <c:if test="${entrustOrgId == item.id}">selected="selected" </c:if> value="${item.id}" >${item.company}</option>
                            </c:forEach>
                        </select>--%>
                        <div>
                            <select class="select form-control select-checkbox" name="entrustOrgIdsChk" data-select-name="entrustOrgIds" data-select-values="${entrustOrgIds}" multiple >
                                <c:forEach items="${consignors}" var="item">
                                    <option value="${item.id}" >${item.company}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <c:if test="${menuCode == 'agent-entrust-list' || menuCode == 'survey-list' || menuCode == 'all-list'}">
                        <div class="form-group">
                            平台复审通过时间：
                            <input name="reviewStartTime" type="text" value="${reviewStartTime}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                            <span>--</span>
                            <input name="reviewEndTime" type="text" value="${reviewEndTime}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        </div>
                    </c:if>

                    <c:if test="${menuCode == 'survey-list' || menuCode == 'belong-list' || menuCode == 'time-track-list' || menuCode == 'all-list'}">
                        <br/>
                        <div class="form-group">
                            调查机构:
                            <%--<select name="surveyOrgId" id="surveyOrgId" class="singleSelect form-control">
                                <option value="">全部</option>
                                <c:forEach items="${franchisees}" var="item">
                                    <option <c:if test="${surveyOrgId == item.id}">selected="selected" </c:if> value="${item.id}" >${item.name}</option>
                                </c:forEach>
                            </select>--%>
                            <div>
                                <select class="select form-control select-checkbox" name="surveyOrgIdsChk" data-select-name="surveyOrgIds" data-select-values="${surveyOrgIds}" multiple >
                                    <c:forEach items="${franchisees}" var="item">
                                        <option value="${item.id}" >${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${menuCode == 'survey-list'}">
                        审核状态:
                        <select onchange="javascript:$('#batchOperateBtn').click();" name="operateState" class="form-control">
                            <option value="0" <c:if test="${operateState == '' || operateState == '0'}">selected="selected"</c:if>>审核中</option>
                            <option value="1" <c:if test="${operateState == '1'}">selected="selected"</c:if>>已审核</option>
                        </select>
                        <c:if test="${surveyBelong}">
                            <div class="form-group">
                                复审人员:
<%--                                <input name="belongUserName" type="text" value="${belongUserName}" class="form-control">--%>
                                <select class="select form-control select-checkbox" style="width: 300px;" name="finalInfosChk" data-select-name="finalInfos" data-select-values="${finalInfos}" multiple >
                                    <c:forEach items="${finalUserInfos}" var="item">
                                        <option value="${item.userId}" data-name="复审中" data-number="${item.oprNum}" >${item.userName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                    </c:if>
                    <c:if test="${menuCode == 'all-list'}">
                        <div class="form-group" style="display: none;">
                            案件阶段:
                            <%--<select name="surveyPhase" class="form-control">
                                <option value="">全部</option>
                                <option value="1"<c:if test="${surveyPhase == '1'}">selected="selected" </c:if> >委托阶段</option>
                                <option value="2"<c:if test="${surveyPhase == '2'}">selected="selected" </c:if> >调查阶段</option>
                                <option value="3"<c:if test="${surveyPhase == '3'}">selected="selected" </c:if> >结案</option>
                            </select>--%>

                            <div>
                                <select class="select form-control select-checkbox" name="surveyPhasesChk" data-select-name="surveyPhases"  data-select-values="${surveyPhases}" multiple >
                                    <option value="1">委托阶段</option>
                                    <option value="2">调查阶段</option>
                                    <option value="3">结案</option>
                                </select>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${menuCode == 'mark-list'}">
                        费用类型:
                        <select onchange="javascript:$('#batchOperateBtn').click();"  name="priceIsCalcType"  class="form-control">
                            <option <c:if test="${priceIsCalcType == '' || priceIsCalcType == 1}">selected="selected"</c:if>  value="1">基本费未结算</option>
                            <option <c:if test="${priceIsCalcType == 2}">selected="selected"</c:if> value="2">减损奖励未结算</option>
                        </select>
                    </c:if>
                    <div class="form-group">
                        业务类型：
                        <select  name="serviceTypeChk" class="select form-control select-checkbox" data-select-name="serviceTypes"  data-select-values="${serviceTypes}" multiple>
                            <option value="12">单点调查</option>
                            <option value="13">深度调查</option>
                            <option value="11">契约调查</option>
                        </select>
                    </div>
                    <c:if test="${menuCode == 'all-list' || menuCode == 'my-list' || menuCode == 'time-track-list'}">
                        <div class="form-group">
                            案件状态:
                            <select name="surveyStateChk"  class="select form-control select-checkbox" data-select-name="surveyStates"  data-select-values="${surveyStates}" multiple >
                                <c:if test="${menuCode == 'all-list' || menuCode == 'my-list'}">
                                    <option value="2">待受理</option>
                                    <option value="6">受理不通过</option>
                                    <option value="4">待分派</option>
                                </c:if>
                                <option value="12">调查中</option>
                                <option value="22">平台复审中</option>
                                <option value="24">保司终审中</option>
                                <option value="28">保司终审通过</option>
                                <option value="34">已结案</option>
                            </select>
                        </div>
                    </c:if>
                    <c:if test="${menuCode == 'all-list'}">
<%--                        <div class="form-group">--%>
<%--                            是否寄送:--%>
<%--                            <select onchange="javascript:$('#batchOperateBtn').click();" name="isSendReport"  class="form-control">--%>
<%--                                <option value=""  <c:if test="${isSendReport == ''}">selected="selected" </c:if> >全部</option>--%>
<%--                                <option value="0" <c:if test="${isSendReport == '0'}">selected="selected" </c:if> >否</option>--%>
<%--                                <option value="1" <c:if test="${isSendReport == '1'}">selected="selected" </c:if> >是</option>--%>
<%--                            </select>--%>
<%--                        </div>--%>
                        <div class="form-group" style="display: none;">
                            基本费是否结算:
                            <select onchange="javascript:$('#batchOperateBtn').click();" name="price1IsCalc"  class="form-control">
                                <option value=""  <c:if test="${price1IsCalc == ''}">selected="selected" </c:if> >全部</option>
                                <option value="0" <c:if test="${price1IsCalc == '0'}">selected="selected" </c:if> >否</option>
                                <option value="1" <c:if test="${price1IsCalc == '1'}">selected="selected" </c:if> >是</option>
                            </select>
                        </div>
                        <div class="form-group" style="display: none;">
                            减损奖励是否结算:
                            <select onchange="javascript:$('#batchOperateBtn').click();" name="price2IsCalc"  class="form-control">
                                <option value=""  <c:if test="${price2IsCalc == ''}">selected="selected" </c:if> >全部</option>
                                <option value="0" <c:if test="${price2IsCalc == '0'}">selected="selected" </c:if> >否</option>
                                <option value="1" <c:if test="${price2IsCalc == '1'}">selected="selected" </c:if> >是</option>
                            </select>
                        </div>
                    </c:if>
                    <c:if test="${menuCode == 'assign-list'}">
                        <div class="form-group">
                            机构分派状态:
                            <select name="orgAssign" onchange="javascript:$('#batchOperateBtn').click();"  class="form-control">
                                <option value=""  <c:if test="${orgAssign == ''}">selected="selected" </c:if> >可分派</option>
                                <option value="0" <c:if test="${orgAssign == '0'}">selected="selected" </c:if> >未分派</option>
                                <option value="1" <c:if test="${orgAssign == '1'}">selected="selected" </c:if> >已分派</option>
                            </select>
                        </div>
                        <div class="form-group">
                            调查员分派状态:
                            <select name="assignState" onchange="javascript:$('#batchOperateBtn').click();"  class="form-control">
                                <option value=""  <c:if test="${assignState == ''}">selected="selected" </c:if> >可分派</option>
                                <option value="0" <c:if test="${assignState == '0'}">selected="selected" </c:if> >未分派</option>
                                <%--<option value="1" <c:if test="${assignState == '1'}">selected="selected" </c:if> >部分分派</option>--%>
                                <option value="2" <c:if test="${assignState == '2'}">selected="selected" </c:if> >已分派</option>
                            </select>
                        </div>

                    </c:if>
                    <c:if test="${menuCode == 'assign-list' || menuCode == 'all-list'}">
                        <div class="form-group">
                            委托时间：
                            <input name="entrustStateTime" type="text" value="${entrustStateTime}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                            <span>--</span>
                            <input name="entrustEndTime" type="text" value="${entrustEndTime}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        </div>
                    </c:if>
                    <c:if test="${menuCode == 'account-list'}">
                        <div class="form-group">
                            支付状态:
                            <%--<select name="isPayEntrustFee"  class="form-control">
                                <option value=""  <c:if test="${isPayEntrustFee == ''}">selected="selected" </c:if> >全部</option>
                                <option value="0" <c:if test="${isPayEntrustFee == '0'}">selected="selected" </c:if> >未申请</option>
                                <option value="1" <c:if test="${isPayEntrustFee == '1'}">selected="selected" </c:if> >开票中</option>
                                <option value="2" <c:if test="${isPayEntrustFee == '2'}">selected="selected" </c:if> >已开票</option>
                            </select>--%>
                            <div>
                                <select onchange="javascript:$('#batchOperateBtn').click();" class="select form-control select-checkbox" name="isPayEntrustFeesChk" data-select-name="isPayEntrustFees"  data-select-values="${isPayEntrustFees}" multiple >
                                    <option value="0">未申请</option>
                                    <option value="1">开票中</option>
                                    <option value="2">已开票</option>
                                </select>
                            </div>

                        </div>
                        <div class="form-group">
                            终审时间：
                            <input name="reportStartDate" type="text" value="${reportStartDate}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                            <span>--</span>
                            <input name="reportEndDate" type="text" value="${reportEndDate}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        </div>
                    </c:if>
                    <c:if test="${menuCode == 'account-list' || menuCode == 'mark-list' || menuCode == 'all-list'}">
                        <div class="form-group">
                            保司审核时间：
                            <input name="entrReportStateDate" type="text" value="${entrReportStateDate}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                            <span>--</span>
                            <input name="entrReportEndDate" type="text" value="${entrReportEndDate}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        </div>
                    </c:if>
                    <c:if test="${menuCode == 'account-list'}">
                        <div class="form-group">
                            是否阳性:
                            <select onchange="javascript:$('#batchOperateBtn').click();" name="isSun"  class="form-control">
                                <option value=""  <c:if test="${isSun == ''}">selected="selected" </c:if> >全部</option>
                                <option value="0" <c:if test="${isSun == '0'}">selected="selected" </c:if> >否</option>
                                <option value="1" <c:if test="${isSun == '1'}">selected="selected" </c:if> >是</option>
                            </select>
                        </div>
                    </c:if>
                    <c:if test="${menuCode == 'guide-list'}">
                        指导状态:
                        <select onchange="javascript:$('#batchOperateBtn').click();" name="guideState" class="form-control">
                            <option value="0" <c:if test="${guideState == '' || guideState == '0'}">selected="selected"</c:if>>待指导</option>
                            <option value="1" <c:if test="${guideState == '1'}">selected="selected"</c:if>>已指导</option>
                        </select>
                    </c:if>
<%--                    <div class="layui-inline">--%>
<%--                        <label class="layui-form-label">复审通过时间:</label>--%>
<%--                        <div class="layui-input-inline">--%>
<%--                            <input type="text" class="layui-input paramTime" readonly id="startTime"--%>
<%--                                   placeholder="请选择日期" >--%>
<%--                        </div>--%>
<%--                    </div>--%>

                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        <c:if test="${menuCode == 'agent-entrust-list'}">
                            <%--style="margin-left: 10px;border-radius: 4px;"--%>
                            <span class="btn btn-default"  onclick="showBatchProcess()">相互宝案件批量处理</span>
                            <span class="btn btn-default"  onclick="showBatchBsProcess()">保司案件批量处理</span>
                        </c:if>
                        <c:if test="${menuCode == 'my-list' && surveyEntrust}">
                            &nbsp; &nbsp;<button onclick="add('own')" type="button" class="btn btn-default">新增</button>
                        </c:if>
                        <c:if test="${menuCode == 'my-list' && surveyAgentEntrust}">
                            &nbsp; &nbsp;<button onclick="add('agent')" type="button" class="btn btn-default">代理委托</button>
                        </c:if>
                        <c:if test="${menuCode == 'account-list'}">
                            <%--&nbsp; &nbsp;<button class="btn btn-default" onclick="showDirectionView()" id="initOpen">导出</button>--%>
                            &nbsp; &nbsp;<button class="btn btn-default"><a href="${ctx}/survey/case/export?menuCode=${menuCode}&surveyPerson=${surveyPerson}&surveryPersonTel=${surveryPersonTel}&surveyNo=${surveyNo}&reportStartDate=${reportStartDate}&reportEndDate=${reportEndDate}&entrReportStateDate=${entrReportStateDate}&entrReportEndDate=${entrReportEndDate}&surveyCaseNo=${surveyCaseNo}&bookType=all&entrustOrgIds=${entrustOrgIds}&isPayEntrustFees=${isPayEntrustFees}&searchStr=${searchStr}&isFinalUserManage=${isFinalUserManage}">总表导出</a></button>
                            &nbsp; &nbsp;<button class="btn btn-default"><a href="${ctx}/survey/case/export?menuCode=${menuCode}&surveyPerson=${surveyPerson}&surveryPersonTel=${surveryPersonTel}&surveyNo=${surveyNo}&reportStartDate=${reportStartDate}&reportEndDate=${reportEndDate}&entrReportStateDate=${entrReportStateDate}&entrReportEndDate=${entrReportEndDate}&surveyCaseNo=${surveyCaseNo}&bookType=each&entrustOrgIds=${entrustOrgIds}&isPayEntrustFees=${isPayEntrustFees}&searchStr=${searchStr}&isFinalUserManage=${isFinalUserManage}">分表导出</a></button>
                            <%--<button class="butList active" onclick="tableToExcel()">测试导出</button>--%>
                            <%--<c:if test="${testRole}">
                                &nbsp; &nbsp;<button class="btn btn-default"><a href="${ctx}/survey/case/exportTest?menuCode=${menuCode}&surveyPerson=${surveyPerson}&surveryPersonTel=${surveryPersonTel}&surveyNo=${surveyNo}&reportStartDate=${reportStartDate}&reportEndDate=${reportEndDate}&entrReportStateDate=${entrReportStateDate}&entrReportEndDate=${entrReportEndDate}&surveyCaseNo=${surveyCaseNo}&bookType=all1&entrustOrgIds=${entrustOrgIds}&isPayEntrustFees=${isPayEntrustFees}">总表导出测试</a></button>
                                &nbsp; &nbsp;<button class="btn btn-default"><a href="${ctx}/survey/case/exportTest?menuCode=${menuCode}&surveyPerson=${surveyPerson}&surveryPersonTel=${surveryPersonTel}&surveyNo=${surveyNo}&reportStartDate=${reportStartDate}&reportEndDate=${reportEndDate}&entrReportStateDate=${entrReportStateDate}&entrReportEndDate=${entrReportEndDate}&surveyCaseNo=${surveyCaseNo}&bookType=all2&entrustOrgIds=${entrustOrgIds}&isPayEntrustFees=${isPayEntrustFees}">总表导出测试</a></button>
                            </c:if>--%>
                        </c:if>
                        <c:if test="${menuCode == 'mark-list'}">
                            &nbsp;&nbsp;&nbsp;<div id="btnPriceIsCalcType1" class="btn btn-default" style="margin-left: 10px;" onclick="markPrice(1)" >标记基本费结算</div>
                            &nbsp;&nbsp;&nbsp;<div id="btnPriceIsCalcType2" class="btn btn-default" style="margin-left: 10px;" onclick="markPrice(2)">标记减损奖励结算</div>
                                    <span class="btn btn-default"  onclick="showBatchProcessSettlement()">相互宝结算</span>
                        </c:if>

                        <c:if test="${menuCode == 'belong-list'}">
                            &nbsp;&nbsp;&nbsp;<button id="btnPriceIsCalcType1" class="btn btn-default" style="margin-left: 10px;" onclick="belong()" >归属本人名下</button>
                        </c:if>
                        <c:if test="${menuCode == 'assign-list'}">
                            &nbsp; &nbsp;<button class="btn btn-default"><a href="${ctx}/survey/case/export?menuCode=assign-list&menuType=condition-assign-list&searchStr=${searchStr}&entrustOrgIds=${entrustOrgIds}&orgAssign=${orgAssign}&assignState=${assignState}&entrustStateTime=${entrustStateTime}&entrustEndTime=${entrustEndTime}">按查询条件导出</a></button>
                            &nbsp; &nbsp;<button class="btn btn-default"><a href="${ctx}/survey/case/export?menuCode=${menuCode}&entrustOrgId=${entrustOrgId}&entrustOrgIds=${entrustOrgIds}&entrustStateTime=${entrustStateTime}&entrustEndTime=${entrustEndTime}">快速导出保司案件</a></button>
                            <%--&nbsp; &nbsp;<button class="btn btn-default"><a href="${ctx}/survey/case/export?menuCode=${menuCode}&surveyPerson=${surveyPerson}&surveryPersonTel=${surveryPersonTel}&surveyNo=${surveyNo}&assignState=${assignState}&surveyOrgId=${surveyOrgId}&entrustOrgId=${entrustOrgId}&surveyCaseNo=${surveyCaseNo}&entrustOrgIds=${entrustOrgIds}&orgAssign=${orgAssign}&entrustStateTime=${entrustStateTime}&entrustEndTime=${entrustEndTime}">导出</a></button>--%>
                        </c:if>
                        <c:if test="${menuCode == 'survey-list' || menuCode == 'all-list'}">
                            &nbsp; &nbsp;<button class="btn btn-default"><a href="${ctx}/survey/case/export?menuCode=${menuCode}&surveyOrgIds=${surveyOrgIds}&entrustOrgIds=${entrustOrgIds}&searchStr=${searchStr}&operateState=${operateState}&finalInfos=${finalInfos}&reviewStartTime=${reviewStartTime}&reviewEndTime=${reviewEndTime}&entrustStateTime=${entrustStateTime}&entrustEndTime=${entrustEndTime}&entrReportStateDate=${entrReportStateDate}&entrReportEndDate=${entrReportEndDate}&surveyStates=${surveyStates}&serviceTypes=${serviceTypes}">导出</a></button>
                        </c:if>
                        <c:if test="${menuCode == 'survey-list'}">
                            <c:if test="${surveyFinalUserManage}">
                                &nbsp; &nbsp;<button class="btn btn-default"><a href="javascript:void(0);" onclick="reass('${menuCode}','${surveyOrgIds}','${entrustOrgIds}','${searchStr}','${operateState}','reass')">批量改派复审人员</a></button>
                            </c:if>
                        </c:if>
                    </div>
                    <div class="dalogs">
                        <div class="close"></div>
                        <div class="d_content2" style="padding: 20px;"></div>
                    </div>
                </form>
            </div>
        </div>

        <div class="table-content">
            <table class="table table-hover" style="cursor: pointer;">
                <thead>
                <tr>
                    <c:if test="${menuCode == 'mark-list' || menuCode == 'belong-list'}">
                        <th class="th-checkbox">
                            <input type="checkbox" id="check-btn" class="tag" checked title="" data-original-title="全选/反选">
                        </th>
                    </c:if>
                    <th width="100">案件编号</th>
                    <%--                <c:if test="${menuCode != 'all-list' }">--%>
                    <%--                    <th width="100">调查编号</th>--%>
                    <%--                </c:if>--%>
                    <th width="150">保险公司</th>
                    <th width="100">被调查人</th>
                    <th width="130">领域</th>
                    <th width="150">业务类型</th>
                    <%--<th width="150">子调查编号</th>--%>
                    <c:if test="${menuCode != 'survey-list'}">
                        <c:if test="${menuCode != 'account-list' && menuCode != 'guide-list' && menuCode != 'all-list' && menuCode != 'my-list' && menuCode != 'mark-list' && menuCode != 'assign-list' && menuCode != 'agent-entrust-list'}">
                            <th width="100">案件阶段</th>
                        </c:if>
                    </c:if>
                    <c:if test="${menuCode == 'check-list'}">
                        <th width="100">受理状态</th>
                    </c:if>
                    <c:if test="${menuCode == 'assign-list'}">
                        <th width="100">分派机构状态</th>
                        <th width="100">分派调查员状态</th>
                        <%--<td width="100">接收状态</td>--%>
                    </c:if>
                    <c:if test="${menuCode != 'assign-list' && menuCode != 'check-list' && menuCode != 'survey-list' && menuCode != 'guide-list'}">
                        <th width="100">当前状态</th>
                    </c:if>
                    <c:if test="${menuCode == 'all-list' || menuCode == 'mark-list'}">
                        <%--                    <th width="80">是否寄送</th>--%>
<%--                        <th width="100">基本费是否结算</th>--%>
<%--                        <th width="120">减损奖励是否结算</th>--%>
                        <c:if test="${menuCode == 'mark-list'}">
                            <th width="140"><div class="sum"><div class="sum-name">结算金额</div><div class="sum-num"></div></div></th>
                        </c:if>
                    </c:if>
                    <%--<c:if test="${menuCode != 'survey-list' && menuCode != 'belong-list'  && menuCode != 'time-track-list'}">--%>
                    <%--<th width="150">保单号</th>--%>
                    <%--</c:if>--%>
                    <c:if test="${menuCode == 'survey-list' || menuCode == 'belong-list'}">
                        <th width="150">调查机构</th>
                    </c:if>

                    <c:if test="${menuCode == 'survey-list' && surveyBelong}">
                        <th width="100">复审人员</th>
                    </c:if>
                    <%--<c:if test="${menuCode == 'my-list' || menuCode == 'account-list'}">--%>
                    <%--<th width="200">委托机构</th>--%>
                    <%--</c:if>--%>
                    <c:if test="${menuCode == 'account-list'}">
                        <th width="100">支付状态</th>
                        <th width="100">是否阳性</th>
                        <th width="150">
                            <div class="title_sort" data-id="50" data-value="">
                                <span>委托时间</span>
                                <div class="icon-sort">
                                    <div class="icon-up"></div>
                                    <div class="icon-down"></div>
                                </div>
                            </div>
                        </th>
                        <th width="150">
                            <div class="title_sort" data-id="52" data-value="">
                                <span>平台复审时间</span>
                                <div class="icon-sort">
                                    <div class="icon-up"></div>
                                    <div class="icon-down"></div>
                                </div>
                            </div>
                        </th>
                        <th width="150">
                            <div class="title_sort" data-id="53" data-value="">
                                <span>保司审核时间</span>
                                <div class="icon-sort">
                                    <div class="icon-up"></div>
                                    <div class="icon-down"></div>
                                </div>
                            </div>
                        </th>
                        <th width="100">调查时效</th>
                    </c:if>

                    <c:if test="${menuCode == 'time-track-list'}">
                        <th width="150"> 截止日期</th>
                    </c:if>
                    <c:if test="${menuCode == 'all-list'}">
                        <th width="150">
                            <div class="title_sort" data-id="50" data-value="">
                                <span>委托时间</span>
                                <div class="icon-sort">
                                    <div class="icon-up"></div>
                                    <div class="icon-down"></div>
                                </div>
                            </div>
                        </th>
                    </c:if>
                    <c:if test="${menuCode != 'account-list'}">
                        <c:if test="${menuCode == 'agent-entrust-list'}">
                            <th width="150">
                                <div class="title_sort" data-id="51" data-value="">
                                    <span>委托时间</span>
                                    <div class="icon-sort">
                                        <div class="icon-up"></div>
                                        <div class="icon-down"></div>
                                    </div>
                                </div>
                            </th>
                            <th width="150">
                                <div class="title_sort" data-id="58" data-value="">
                                    <span>平台复审通过时间</span>
                                    <div class="icon-sort">
                                        <div class="icon-up"></div>
                                        <div class="icon-down"></div>
                                    </div>
                                </div>
                            </th>
                            <th width="150">
                                <div class="title_sort" data-id="66" data-value="">
                                    <span>案件截止时间</span>
                                    <div class="icon-sort">
                                        <div class="icon-up"></div>
                                        <div class="icon-down"></div>
                                    </div>
                                </div>
                            </th>
                            <th width="150">
                                案件时效
                            </th>
                        </c:if>
                        <c:if test="${menuCode != 'agent-entrust-list'  && menuCode != 'all-list'}">
                            <th width="150">
                                <div class="title_sort" data-id="51" data-value="">
                                <span>
                                    <%--<c:if test="${menuCode == 'my-list'}">委托时间</c:if>
                                    <c:if test="${menuCode != 'my-list'}">创建时间</c:if>--%>
                                    委托时间
                                </span>
                                    <div class="icon-sort">
                                        <div class="icon-up"></div>
                                        <div class="icon-down"></div>
                                    </div>
                                </div>
                            </th>
                        </c:if>
                    </c:if>
                    <c:if test="${menuCode == 'mark-list' || menuCode == 'all-list'}">
                        <th width="150">
                            <div class="title_sort" data-id="53" data-value="">
                                <span>保司审核时间</span>
                                <div class="icon-sort">
                                    <div class="icon-up"></div>
                                    <div class="icon-down"></div>
                                </div>
                            </div>
                        </th>
                    </c:if>
                    <c:if test="${menuCode == 'survey-list'}">
                        <th width="150">
                            <div class="title_sort" data-id="56" data-value="">
                                <span>调查完成时间</span>
                                <div class="icon-sort">
                                    <div class="icon-up"></div>
                                    <div class="icon-down"></div>
                                </div>
                            </div>
                        </th>
                        <th width="150">
                            <div class="title_sort" data-id="57" data-value="">
                                <span>案件截止时间</span>
                                <div class="icon-sort">
                                    <div class="icon-up"></div>
                                    <div class="icon-down"></div>
                                </div>
                            </div>
                        </th>
                    </c:if>
                    <c:if test="${menuCode == 'guide-list'}">
                        <th width="150">调查机构</th>
                        <th width="80">指导状态</th>
                    </c:if>
                    <c:if test="${menuCode == 'mark-list'}">
                        <th width="100">操作</th>
                    </c:if>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${apiRsp.results}" var="item">
                    <tr
                            <c:if test="${menuCode != 'mark-list'}">
                                title="单击打开${item.surveyRiskCase.surveyCaseNo}详情" onclick="info(${item.id})"
                            </c:if>
                            <c:if test="${item.isOverTime!=null && item.isOverTime == true}">style="color: red" </c:if>
                            <c:if test="${menuCode == 'survey-list'}">
                                <c:if test="${item.colorTimeType!=null && item.colorTimeType == 1}">style="color: red" </c:if>
                                <c:if test="${item.colorTimeType!=null && item.colorTimeType == 2}">style="color: #d7a50f" </c:if>
                                <c:if test="${item.colorTimeType!=null && item.colorTimeType == 3}"></c:if>
                                <c:if test="${item.opinion!=null}">bgcolor="#f9e3e4" </c:if>
                            </c:if>
                            <c:if test="${menuCode == 'assign-list'}">
                                <c:if test="${item.orgReturn == 1}">bgcolor="#f9e3e4" </c:if>
                            </c:if>
                    >
                        <c:if test="${menuCode == 'mark-list' || menuCode == 'belong-list'}">
                            <td>
                                <input type="checkbox" name="mark-checkbox" checked value="${item.id}">
                            </td>
                        </c:if>
                        <td>${item.surveyRiskCase.surveyCaseNo}</td>


                            <%--                    <c:if test="${menuCode != 'all-list' }">--%>
                            <%--                        <td>${item.surveyRiskCase.surveyNo}</td>--%>
                            <%--                    </c:if>--%>
                        <td>${item.entrustOrgName}</td>
                        <td>
                                ${item.surveyRiskCase.surveyPerson}
                            <c:if test="${menuCode=='check-list'}"><c:if test="${item.surveyRiskCase.repetition != null && item.surveyRiskCase.repetition}"><span style="color: red" >(重复案件)</span></c:if></c:if>

                            <c:if test="${item.surveyRiskCase.transferType != null}"><span style="color: red" >(${item.surveyRiskCase.transferTypeName})</span></c:if>
                        </td>
                            <%--<td>${item.surveyCno}</td>--%>
                        <td>${item.surveyBusName}</td>
                        <td>
                            <c:if test="${item.subServiceId ==null}">
                                <span <c:if test="${item.servicesId == 13}">class="span-sd"</c:if> >${item.servicesName}</span>
                            </c:if>
                            <c:if test="${item.subServiceId !=null}">
                                <c:if test="${item.subServiceId ==2}">核对调阅类</c:if>
                                <c:if test="${item.subServiceId ==3}">一般检索类</c:if>
                                <c:if test="${item.subServiceId ==4}">特殊检索类</c:if>
                                <c:if test="${item.subServiceId ==5}">疑难侦察类</c:if>
                                <c:if test="${item.subServiceId ==6}">攻坚侦察类</c:if>
                            </c:if>
                        </td>
                        <c:if test="${menuCode != 'survey-list'}">
                            <c:if test="${menuCode != 'account-list' && menuCode != 'guide-list' && menuCode != 'all-list' && menuCode != 'my-list' && menuCode != 'mark-list' && menuCode != 'assign-list' && menuCode != 'agent-entrust-list'}">
                                <td>
                                    <c:if test="${item.surveyPhase == 1}">
                                        委托阶段
                                    </c:if>
                                    <c:if test="${item.surveyPhase == 2}">
                                        调查阶段
                                    </c:if>
                                    <c:if test="${item.surveyPhase == 3}">
                                        已结案
                                    </c:if>
                                </td>
                            </c:if>
                        </c:if>
                        <c:if test="${menuCode == 'check-list'}">
                            <td>
                                <c:if test="${item.surveyState == 2}">
                                    待受理
                                </c:if>
                                <c:if test="${item.surveyState == 6}">
                                    受理不通过
                                </c:if>
                                <c:if test="${item.surveyState != 2 && item.surveyState != 6}">
                                    受理通过
                                </c:if>
                            </td>
                        </c:if>
                        <c:if test="${menuCode == 'assign-list'}">
                            <td>
                                <c:if test="${item.orgAssign != 1}">未分派</c:if>
                                <c:if test="${item.orgAssign == 1}">已分派</c:if>
                            </td>
                            <td>
                                <c:if test="${item.assignState == 0}">未分派</c:if>
                                    <%--<c:if test="${item.assignState == 1}">部分分派</c:if>--%>
                                <c:if test="${item.assignState == 2}">已分派</c:if>
                            </td>
                            <%--<td>--%>
                            <%--<c:if test="${item.acceptState == 0}">待接收</c:if>--%>
                            <%--<c:if test="${item.acceptState == 1}">部分接收</c:if>--%>
                            <%--<c:if test="${item.acceptState == 2}">全部接收</c:if>--%>
                            <%--<c:if test="${item.acceptState == 3}">已拒绝</c:if>--%>
                            <%--</td>--%>
                        </c:if>
                        <c:if test="${menuCode != 'assign-list' && menuCode != 'check-list' && menuCode != 'survey-list' && menuCode != 'guide-list'}">
                            <td>
                                <c:if test="${item.supplementState == 1}">
                                    <span style="color: red;">信息补充中</span>
                                </c:if>
                                <c:if test="${item.supplementState != 1}">
                                    ${item.surveyStateName}
                                </c:if>
                            </td>
                        </c:if>
                        <c:if test="${menuCode == 'all-list' || menuCode == 'mark-list'}">
                            <%--                        <td>--%>
                            <%--                            <c:if test="${item.isSendReport == 0}">否</c:if>--%>
                            <%--                            <c:if test="${item.isSendReport == 1}">是</c:if>--%>
                            <%--                        </td>--%>
<%--                            <td>--%>
<%--                                <c:if test="${item.price1IsCalc == 0}">否</c:if>--%>
<%--                                <c:if test="${item.price1IsCalc == 1}">是</c:if>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                <c:if test="${item.price2IsCalc == 0}">否</c:if>--%>
<%--                                <c:if test="${item.price2IsCalc == 1}">是</c:if>--%>
<%--                            </td>--%>
                            <c:if test="${menuCode == 'mark-list'}">
                                <td class="sum-one" data-id="${item.id}" >${item.entrustOkPrice1+item.entrustOkPrice2}<a onclick="updPayMoney(${item.id},${item.entrustOkPrice1+item.entrustOkPrice2})"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a></td>
                            </c:if>
                        </c:if>
                            <%--<c:if test="${menuCode != 'survey-list' && menuCode != 'belong-list' && menuCode != 'time-track-list'}">--%>
                            <%--<td>${item.surveyRiskCase.policyNo}</td>--%>
                            <%--</c:if>--%>
                        <c:if test="${menuCode == 'survey-list' || menuCode == 'belong-list'}">
                            <td>${item.surveyOrgName}</td>
                        </c:if>
                        <c:if test="${menuCode == 'survey-list' && surveyBelong}">
                            <td>${item.belongUserName}</td>
                        </c:if>
                            <%--<c:if test="${menuCode == 'my-list' || menuCode == 'account-list'}">--%>
                            <%--<td>${item.entrustOrgName}</td>--%>
                            <%--</c:if>--%>
                        <c:if test="${menuCode == 'account-list'}">
                            <td>
                                <c:if test="${item.isPayEntrustFee == 0}">
                                    未申请
                                </c:if>
                                <c:if test="${item.isPayEntrustFee == 1}">
                                    开票中
                                </c:if>
                                <c:if test="${item.isPayEntrustFee == 2}">
                                    已开票
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${item.isSun == 0}">
                                    否
                                </c:if>
                                <c:if test="${item.isSun == 1}">
                                    是
                                </c:if>
                            </td>
                            <td><fmt:formatDate value="${item.surveyRiskCase.entrustTime}" pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${item.entrustReportStartDate}" pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${item.entrustReportEndDate}" pattern="yyyy-MM-dd"/></td>
                            <td>${item.efficiency}天</td>
                        </c:if>

                        <c:if test="${menuCode == 'time-track-list'}">
                            <td><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                        </c:if>
                        <c:if test="${menuCode == 'all-list'}">
                            <td><fmt:formatDate value="${item.surveyRiskCase.entrustTime}" pattern="yyyy-MM-dd"/></td>
                        </c:if>
                        <c:if test="${menuCode != 'account-list'}">
                            <c:if test="${menuCode == 'agent-entrust-list'}">
                                <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatDate value="${item.entrustReportStartDate}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd"/></td>
                                <td>${item.agingDay}天</td>
                            </c:if>
                            <c:if test="${menuCode != 'agent-entrust-list'  && menuCode != 'all-list'}">
                                <td>
                                    <fmt:formatDate value="${item.surveyRiskCase.entrustTime}" pattern="yyyy-MM-dd"/>
                                        <%--<c:if test="${menuCode =='my-list'}"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></c:if>
                                        <c:if test="${menuCode !='my-list'}"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></c:if>--%>
                                </td>
                            </c:if>
                        </c:if>
                        <c:if test="${menuCode == 'mark-list' || menuCode == 'all-list'}">
                            <td><fmt:formatDate value="${item.entrustReportEndDate}" pattern="yyyy-MM-dd"/></td>
                        </c:if>
                        <c:if test="${menuCode == 'survey-list'}">
                            <td><fmt:formatDate value="${item.lefanReportDate}" pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd"/></td>
                        </c:if>
                        <c:if test="${menuCode == 'guide-list'}">
                            <th width="150">${item.surveyOrgName}</th>
                            <td>
                                <c:if test="${item.guideState == 0 || guideState == ''}">
                                    待指导
                                </c:if>
                                <c:if test="${item.guideState == 1}">
                                    已指导
                                </c:if>
                            </td>
                        </c:if>
                        <c:if test="${menuCode == 'mark-list'}">
                            <td>
                                <a href="javascript:void(0);" onclick="info(${item.id})">处理</a>
                            </td>
                        </c:if>
<%--                        <td>--%>
<%--                            <a href="javascript:void(0);" onclick="info(${item.id})">处理</a>--%>
<%--                            <c:if test="${menuCode == 'survey-list' && surveyBelong && operateState !=1}">--%>
<%--                                &lt;%&ndash;                            <a href="javascript:void(0);" onclick="belongUser(${item.id},'belongUser')">改派归属人</a>&ndash;%&gt;--%>
<%--                            </c:if>--%>
<%--                        </td>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div><!--panel-info-->
    <div class="dalog dalog__1" style="display: none">
        <div class="d-title">金额调整</div>
        <input value="" name="id" hidden>
        <div class="d-cell">
            <div class="d-cell__label">开票金额</div>
            <div class="d-cell__input">
                <input id="updPayMoney" type="number" value="">
                <span>元</span>
            </div>
        </div>
        <div class="d-btns">
            <div class="d-btn" data-id="1">取消</div>
            <div class="d-btn d-btn__active" data-id="2">确认</div>
        </div>
    </div>
    <form id="markForm" name="markForm" action="${ctx}/survey/case/operate" role="form" method="post">
        <input type="hidden" name="ids" id="markIds" />
        <input type="hidden" name="btnCode" value="mark" />
        <input type="hidden" name="type" id="markType" value="" />
    </form>
<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/survey/case/list?menuCode=${menuCode}&searchStr=${searchStr}&order=${order}&colSortType=${colSortType}&surveyState=${surveyState}&surveyPerson=${surveyPerson}&surveyPhase=${surveyPhase}&isSendReport=${isSendReport}&price1IsCalc=${price1IsCalc}&price2IsCalc=${price2IsCalc}&entrustOrgName=${entrustOrgName}&surveryPersonTel=${surveryPersonTel}&surveyNo=${surveyNo}&assignState=${assignState}&orgAssign=${orgAssign}&reportStartDate=${reportStartDate}&reportEndDate=${reportEndDate}&isPayEntrustFee=${isPayEntrustFee}&policyNo=${policyNo}&entrReportStateDate=${entrReportStateDate}&entrReportEndDate=${entrReportEndDate}&surveyOrgId=${surveyOrgId}&entrustOrgId=${entrustOrgId}&belongUserName=${belongUserName}&isSun=${isSun}&surveyCaseNo=${surveyCaseNo}&entrustOrgIds=${entrustOrgIds}&isPayEntrustFees=${isPayEntrustFees}&surveyOrgIds=${surveyOrgIds}&surveyPhases=${surveyPhases}&orgAssigns=${orgAssigns}&assignStates=${assignStates}&surveyStates=${surveyStates}&serviceTypes=${serviceTypes}&entrustStateTime=${entrustStateTime}&entrustEndTime=${entrustEndTime}&guideState=${guideState}&operateState=${operateState}&finalInfos=${finalInfos}&reviewEndTime=${reviewEndTime}&reviewStartTime=${reviewStartTime}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
</div>
    <!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<%--<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>--%>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect2.js?V=1"></script>
<script src="${ctx}/js/layui/layui.js"></script>
<script>
    // $('.pageSizeBars .pageSizeBar').click(function () {
    //     var  _this  = $(this)
    //     if (!_this.hasClass('active')){
    //         _this.addClass('active')
    //         _this.siblings().removeClass('active')
    //         localStorage.setItem("pageSize", _this.text());
    //     }
    // })
    //
    // var pageSize = localStorage.getItem("pageSize");
    // $('.pageSizeBars .pageSizeBar[data-num="'+pageSize+'"]').addClass('active').siblings().removeClass('active')

    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })

    layui.use(['table', 'util', 'xmSelect', 'laydate'], function () {
        var table = layui.table,
            util = layui.util,
            laydate = layui.laydate,
            xmSelect = layui.xmSelect;

        startTime = laydate.render({
            elem: '#startTime',
            range: '~',
            done: function (value, date) {
            }
        });
    })




    var updPayMoney = function(id,money){
        $(".dalog__1").find("input[name=id]").val(id);
        $(".dalog__1").find("#updPayMoney").val(money);
        $('.dalog__1').show()
    }

    $('.dalog__1 .d-btn').click(function(){
        if ($(this).attr('data-id') == 1){
            $('.dalog__1').hide()
        }
        if ($(this).attr('data-id') == 2){
            var updPayMoney = $("#updPayMoney").val();
            $.ajax({
                url: '${ctx}/survey/case/save',
                type: "POST",
                data:{
                    id:$("input[name=id]").val(),
                    menuCode:"markList",
                    updMoney:updPayMoney
                },
                success: function (res, param) {
                    var r = JSON.parse(res);
                    if(r.isSuccess){
                        $('.dalog__1').hide()
                    }
                    alert(JSON.parse(res).msg)
                    setTimeout(function () {
                        location.reload();
                    },1000);

                }
            });
        }
    })

    function showBatchProcess(){
        var width = $(document.body).outerWidth()*0.98;
        var height = $(document).outerHeight()*0.98;
        openDialog({
            frame:true,
            title:'',
            height:height,
            width:width,
            url:'${ctx}/survey/case/list?menuCode=xhbCase'
        });
    }

    function showBatchProcessSettlement(){
        var width = $(document.body).outerWidth()*0.98;
        var height = $(document).outerHeight()*0.98;
        openDialog({
            frame:true,
            title:'',
            height:height,
            width:width,
            url:'${ctx}/survey/case/list?menuCode=xhbCaseSettlement'
        });
    }

    function showBatchBsProcess(){
        var width = $(document.body).outerWidth()*0.98;
        var height = $(document).outerHeight()*0.98;
        openDialog({
            frame:true,
            title:'',
            height:height,
            width:width,
            url:'${ctx}/survey/case/list?menuCode=${menuCode}&btnCode=bsCase&entrustOrgIds=94'
        });
    }

    $("#batchOperateBtn").click(function(){
        //select 多选 赋值 到隐藏域。 便于后台传值
        $(".select-checkbox").each(function(){
            var selName = $(this).attr("name");
            var all = $(".UCSelect[name="+selName+"]").find(".UCSelectAll").hasClass("Selected");
            var name = $(this).attr("data-select-name");
            if(name){
                $("#" + name).val($(this).val());
            }
        })
    });

    $(document).ready(function () {
        var height_doc = window.parent.innerHeight - 50 -27 -66 -$('.panel-heading').height() - 40;
        if($(window.parent).width() > 1366){
            height_doc = height_doc - 28;
        }
        $(".table-content").height(height_doc).css({
            overflow: 'auto'
        });
        $("#markForm").bind('submit', function(event) {
            $.ajax({
                url: "${ctx}/survey/case/operate",
                type: "POST",
                data: $("#markForm").serialize(),//核心代码，form表单序列化
                dataType: "JSON",
                success: function(data) {
                    event.preventDefault();
                    alert(data.msg);
                    reload();
                },
                error : function(data){
                    event.preventDefault();
                    alert(data);
                }
            });
            //
            //
            // console.log(this);
            // ajaxFormSubmit(this,reload,null,null,reload);
            // event.preventDefault();
        });

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
        $(".UCSelect[name=entrustOrgIdsChk]").width(320);
        $(".UCSelect[name=surveyOrgIdsChk]").width(320);
        $(".UCSelect[name=finalInfosChk]").width(320);


        $('.btn-1').off("click");
        $('.btn-1').click(function(e){
            $(".UCSelect").find('.SelectVal').removeClass('over');
            $(".UCSelect").find("select").UCFormSelect('close');
            $("#batchOperateBtn").click();
        });

        $(document).off('mousedown');
        $(document).bind('mousedown', function (e) {
            var Event = e.target;
            $('.UCSelect .SelectBox').each(function () {
                var Select = $(this).parents(".UCSelect").find("select").get(0);
                var Events = $(Event).parents(".UCSelect").find("select").get(0);
                if (!(Event && Select && Select == Events)) {
                    if( $(this).parents(".UCSelect").find('.SelectVal').hasClass("over")){
                        $("#batchOperateBtn").click();
                    }
                    $(this).parents(".UCSelect").find('.SelectVal').removeClass('over');
                    $(this).parents(".UCSelect").find("select").UCFormSelect('close');
                }
            });
        });


    })

    $(document).ready(function(){
        /*$('.singleSelect').select2();*/
        $("#check-btn").on('change',function(){
            $("input[name='mark-checkbox']").prop("checked",this.checked);

        });
        setSum()
        function setSum() {
            var sum = 0
            $('.sum-one').map(function (i,cur) {
                console.log(i,$(this).text())
                sum += Number($(this).text())
            })
            $('.sum-num').html('('+sum.toFixed(2)+')')
        }
        $("input[name='mark-checkbox']").click(function () {
            var sum = 0
            $("input:checkbox[name='mark-checkbox']").each(function() { // 遍历name=safeCompanys的多选框
                if ($(this).prop('checked')){
                    var id = $(this).val()
                    sum += Number($('.sum-one[data-id="'+id+'"]').text())
                }
            });
            $('.sum-num').html('('+sum.toFixed(2)+')')
        })

        $("#check-btn").on('click',function(){
            var sum = 0
            console.log($(this).prop('checked'))
            if ($(this).prop('checked')){
                $("input:checkbox[name='mark-checkbox']").each(function(i, cur) { // 遍历name=safeCompanys的多选框
                    var id = $(cur).val()
                    sum += Number($('.sum-one[data-id="'+id+'"]').text())
                });
            }
            $('.sum-num').html('('+sum.toFixed(2)+')')


        });

        if("${priceIsCalcType}" == null || "${priceIsCalcType}" == "" || "${priceIsCalcType}" == 1){
            $("#btnPriceIsCalcType1").show();
            $("#btnPriceIsCalcType2").hide();
        }else if("${priceIsCalcType}" == 2){
            $("#btnPriceIsCalcType1").hide();
            $("#btnPriceIsCalcType2").show();
        }


        var initId = $("#order").val(),sort = $("#colSortType").val();

        $(".title_sort[data-id="+ initId+"]").attr('data-value',sort)
        if (sort == '1'){
            $(".title_sort[data-id="+ initId+"]").find('.icon-up').addClass('active')
        }  else if (sort == '2'){
            $(".title_sort[data-id="+ initId+"]").find('.icon-down').addClass('active')
        }

        $('.title_sort').click(function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            $('.title_sort').each(function (i,cur) {
                var _cur =  $(cur)
                if (_cur.attr('data-id')!= id){
                    _cur.attr('data-value','')
                    _cur.find('.icon-sort div').removeClass('active')
                }
            })
            if (_this.attr('data-value') == '1'){
                _this.find('.icon-up').removeClass('active')
                _this.find('.icon-down').addClass('active')
                _this.attr('data-value','2')
            } else if (_this.attr('data-value') == '2'){
                _this.find('.icon-up').addClass('active')
                _this.find('.icon-down').removeClass('active')
                _this.attr('data-value','1')
            }else  if (!_this.attr('data-value')){
                _this.find('.icon-up').addClass('active')
                _this.find('.icon-down').removeClass('active')
                _this.attr('data-value','1')
            }
            var d_v = _this.attr('data-value')
            console.log(id, d_v)
            $("#order").val(id);
            $("#colSortType").val(d_v);
            $('#batchOperateBtn').click();

        })

    })

    var add = function(obj){
        var height = $(document).outerHeight() - 10;
        var width = $(document.body).outerWidth();
        openDialog({
            frame:true,
            title:"新增",
            height:height,
            width:width,
            url:"${ctx}/survey/case/edit?obj=" + obj,
            load:true
        });
    }
    var info = function(id,autoOpenInfo){
        var selected = getselected();
        if (selected){
            return;
        }
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 10;
        openDialog({
            frame:true,
            title:"",
            height:height,
            width:width,
            url:"${ctx}/survey/case/info?id=" + id + "&menuCode=${menuCode}&display=true",
            load:true
        });
    }

    function getselected(){
        if (window.getSelection){
            return window.getSelection().toString();
        }else if (document.getSelection){
            return document.getSelection().toString;
        }else{
            var selection = document.selection && document.selection.createRange();
            if (selection.text){
                return selection.text.toString;
            }
            return "";
        }
    }

    var markPrice = function(type){
        var ids = [];
        var values = "";
        $("input:checkbox[name='mark-checkbox']:checked").each(function() { // 遍历name=safeCompanys的多选框
            ids.push($(this).val());
            values += $(this).val() + ",";
        });
        if(ids.length == 0){
            alert("未选中数据");
            return;
        }
        if(confirm('请认真核实费用准确性，是否确认？')){
            $("#markIds").val(values);
            $("#markType").val(type);
            $("form[name='markForm']").submit();
        }
        <%--var url = "${ctx}/survey/case/operate";--%>
        <%--var param = {"ids":ids.join(","),"btnCode":"mark","type":type};--%>
        <%--if(confirm('请认真核实费用准确性，是否确认？')){--%>
        <%--    ajaxSubmit(url,param,function(v,e,p){--%>
        <%--        alert(e.data.msg);--%>
        <%--        // console.log(window.location)--%>
        <%--        console.log('jjjjjjjjjjjjjjjjjjjjjjjjjjjjj')--%>
        <%--        location.reload();--%>
        <%--        // alert(e.data.msg);--%>
        <%--    })--%>
        <%--}--%>
    }

    // $("#markForm").bind('submit', function(event) {
    //     ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
    //     event.preventDefault();
    // });


    function returnCallback(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){
            alert("成功！");
        }else{
            alert(apiRsp.msg);return;
        }
        reload();
    }

    var belong = function(type){
        var ids = [];
        $("input:checkbox[name='mark-checkbox']:checked").each(function() { // 遍历name=safeCompanys的多选框
            ids.push($(this).val());
        });
        if(ids.length == 0){
            alert("未选中数据");
            return;
        }
        var url = "${ctx}/survey/case/operate";
        var param = {"ids":ids.join(","),"btnCode":"belong","type":type};
        if(confirm('请认真核实，是否确认？')){
            ajaxSubmit(url,param,function(v,e,p){
                alert(e.data.msg);
                reload();
            })
        }
    }

    var belongUser = function(id,btnCode){

        var height = 600,width = 800;
        var title = "操作";
        url = "${ctx}/survey/case/operateView?id=" + id + "&btnCode=" + btnCode;//查询拥有制作报告权限的角色用户
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:url
        });
    }

    function showDirectionView() {

        var dialog = $(".dalogs");
        dialog.show();
        var position = $("#initOpen").position();//以按钮为定位
        $(".dalogs").offset({
            left: position.left + 800,
            top: position.top + 250
        });
        var html = "";
        html += '<div class="main22">';
        html += '<div class="step-contain">';
        html += '<div class="title">选择要导出的表格类型</div>';
        html += '<div class="content">';
        html += '<div class="btn-area">';
        html += '<div class="c-btn" onclick="showDirectionView2(1);">总表</div>';
        html += '<div class="c-btn2" onclick="showDirectionView2(0);">分表</button>';
        html += '</div></div></div></div>';
        $(".d_content2").html(html);

    }

    //使用场景：“代理委托”录入案件时，可直接“分派调查员”
    /*autoOpenInfo();
    function autoOpenInfo(){
        var autoOpenInfo = $("#autoOpenInfo").val();
        $("#autoOpenInfo").val(0);
        var menuCode = '${menuCode}';
        var autoOpenInfoId = $("#autoOpenInfoId").val();
        if(menuCode == 'assign-list' && autoOpenInfo ==1){
            //自动开详情页面
            info(autoOpenInfoId,autoOpenInfo);
        }
    }*/

    var conditionalExport=function () {
        var searchStr=$('input[name="searchStr"]').val();
        var entrustOrgIdsChk=$('select[name="entrustOrgIdsChk"]').val();
        var orgAssign=$('select[name="orgAssign"]').val();
        var assignState=$('select[name="assignState"]').val();
        var entrustStateTime=$('select[name="entrustStateTime"]').val();
        var entrustEndTime=$('input[name="entrustEndTime"]').val();
        $.ajax({
            url: "${ctx}/survey/case/export",
            data: {
                menuCode:'assign-list',
                menuType:'condition-assign-list',
                searchStr: searchStr,
                entrustOrgIds : entrustOrgIdsChk,
                orgAssign: orgAssign,
                assignState: assignState,
                entrustStateTime: entrustStateTime,
                entrustEndTime: entrustEndTime
            },
            success: function(data) {
                alert(data.msg);
            },
            error : function(data){
                alert(data);
            }
        });
    }

    var reass = function(menuCode,surveyOrgIds,entrustOrgIds,searchStr,operateState,btnCode){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        var title = "操作";
        var url = "${ctx}/survey/case/operateView?menuCode=" + menuCode  + "&surveyOrgIds=" + surveyOrgIds + "&entrustOrgIds=" + entrustOrgIds + "&searchStr=" + searchStr + "&operateState=" + operateState+ "&btnCode=" + btnCode;
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:url
        });
    }
</script>
</body>
</html>
