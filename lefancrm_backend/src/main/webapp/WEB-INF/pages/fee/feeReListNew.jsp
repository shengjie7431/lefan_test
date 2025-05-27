<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>费用报销清单</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
<%--    <link rel="stylesheet" href="${ctx}/css/lefan14.css">--%>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">


    <style>
        .td-spec {
            border-top: none !important;

        }

        table.table > tbody > tr > td {
            vertical-align: middle !important;
            padding-top: 12px;
            padding-bottom: 12px;
        }

        .class-list a {
            color: #428bca;
        }

        .title_sort {
            display: flex;
            align-items: center;
            cursor: pointer;
        }

        .icon-sort {
            display: inline-block;
            width: 10px;
            padding-left: 2px;
        }

        .icon-sort .icon-up.active {
            border-bottom: 7px solid #333;
        }

        .icon-sort .icon-down.active {
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
        .editA{
            padding: 0 4px;
            color: #3ba9ff!important;
        }
        .layui-form-label{
            width: auto;
            margin: 0;
        }
        .selectMul{
            width: 160px;
        }
        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }
        .form-inline{
            margin: 0!important;
        }

        .tip-info{
            float: right;
            padding: 8px 0;
            height: 16px;
            line-height: 16px;
            color: red;
        }
        @media screen and (max-width:1300px) {
            .tip-info {
                font-size: 10px;

            }
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>费用报销清单 <small>共<span class="tableCount"></span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <%-- role="form" action="${ctx}/fee/list" method="post"--%>
                <form class="form-inline" >
                    <input type="hidden" name="menuCode" value="${menuCode}">
                    <input type="hidden" id="searchCode" name="searchCode" value="${searchCode}">
                    <input type="hidden" name="rId" value="${rId}">
                    <input type="hidden" name="sortField" id="sortField" value="${sortField}">
                    <input type="hidden" name="sortType" id="sortType" value="${sortType}">
                    <input type="hidden" name="createTime" id="createTime" value="${createTime}">
                    <input type="hidden" name="surveyOrgIds" id="surveyOrgIds"/>
                    <input type="hidden" name="investigators" id="investigators"/>
                    <div class="form-group">
                        清单名称:
                        <input name="reName" type="text" value="${reName}" style="width: 150px"
                               class="form-control">
                    </div>
                    <c:if test="${show}">
                        <%--<div class="form-group">--%>
                            <%--调查方机构:--%>
                            <%--<div>--%>
                                <%--<select class="select form-control select-checkbox" name="surveyOrgIds"--%>
                                        <%--data-select-name="surveyOrgIds" data-select-values="${surveyOrgIds}" multiple>--%>
                                    <%--<c:forEach items="${franchisee}" var="item">--%>
                                        <%--<option value="${item.id}">${item.name}</option>--%>
                                    <%--</c:forEach>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--调查员:--%>
                            <%--<div>--%>
                                <%--<select class="select form-control select-checkbox" name="investigators"--%>
                                        <%--data-select-name="investigators" data-select-values="${investigators}" multiple>--%>
                                    <%--<c:forEach items="${investigator}" var="item">--%>
                                        <%--<option value="${item.userId}">${item.realName}</option>--%>
                                    <%--</c:forEach>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="layui-inline form-group">
                            <label class="layui-form-label">调查方机构:</label>
                            <div class="layui-input-inline">
                                <div id="surveyOrgIdss" class="selectMul"></div>
                            </div>
                        </div>
                        <div class="layui-inline form-group">
                            <label class="layui-form-label">调查员:</label>
                            <div class="layui-input-inline">
                                <div id="investigatorss" class="selectMul"></div>
                            </div>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <div class="layui-inline">
                            <label class="layui-form-label">报销状态:</label>
                            <div class="layui-input-inline">
                                <div id="reStates" class="selectMul"></div>
                            </div>
                        </div>
                        <%--onchange="javascript:$('#batchOperateBtn').click();"--%>
<%--                        <select  name="reState"--%>
<%--                                class="form-control">--%>
<%--                            <option value="" <c:if test="${reState == ''}">selected="selected"</c:if>>全部</option>--%>
<%--                            <option value="1" <c:if test="${reState == '1'}">selected="selected"</c:if>>待提交发票</option>--%>
<%--                            <option value="2" <c:if test="${reState == '2'}">selected="selected"</c:if>>待机构审核</option>--%>
<%--                            <option value="3" <c:if test="${reState == '3'}">selected="selected"</c:if>>待财务审核</option>--%>
<%--                            <option value="5" <c:if test="${reState == '5'}">selected="selected"</c:if>>付款中</option>--%>
<%--                            <option value="6" <c:if test="${reState == '6'}">selected="selected"</c:if>>待确认到账</option>--%>
<%--                            <option value="7" <c:if test="${reState == '7'}">selected="selected"</c:if>>报销完成</option>--%>
<%--                        </select>--%>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="button" class="btn btn-default">查询</button>&nbsp;
                        &nbsp;
                    </div>

                </form>
                <c:if test="${!show}">
                    <div class="form-group tip-info" >
                        如果你对当前报销金额有疑义，比如漏打卡、金额填错等，请先线下联系机构负责人协助调整，直到金额正确再提交发票。&nbsp;

                    </div>
                </c:if>
            </div>
        </div>

        <%--<table class="table table-spec">--%>
            <%--<thead>--%>
            <%--<tr>--%>
                <%--<c:if test="${show}">--%>
                    <%--<th width="5%">调查员</th>--%>
                    <%--<th width="7%">调查机构</th>--%>
                <%--</c:if>--%>
                <%--<th width="8%">清单名称</th>--%>
                <%--<th width="9%">案件关联数</th>--%>
                <%--<th width="6%">费用报销合计</th>--%>
                <%--<th width="9%">均件报销金额</th>--%>
                <%--<th width="6%">费用报销（保险）</th>--%>
                <%--<th width="6%">费用报销（互助）</th>--%>
<%--&lt;%&ndash;                <th width="6%">互助基层员工地级市交通费</th>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <th width="6%">互助-基层员工案件资料调阅及复印费</th>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <th width="6%">互助-基层员工跨省跨市城际间差旅费报销-住宿费</th>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <th width="6%">互助-基层员工跨省跨市城际间差旅费报销-交通费</th>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <th width="4%">里程补贴</th>&ndash;%&gt;--%>
                <%--<th width="6%">报销状态</th>--%>
                <%--<th width="9%">操作</th>--%>
            <%--</tr>--%>
            <%--</thead>--%>
            <%--<tbody class="class-list">--%>
            <%--<c:forEach items="${apiRsp.results}" var="item">--%>
                <%--<tr>--%>
                    <%--<c:if test="${show}">--%>
                        <%--<td>${item.surveyUserName}</td>--%>
                        <%--<td>${item.surveyOrgName}</td>--%>
                    <%--</c:if>--%>
                    <%--<td>${item.reName}</td>--%>
                    <%--<td>${item.totalCaseNum}</td>--%>
                    <%--<td><a onclick="openInfo('${item.id}','all','${item.reState}','${item.surveyUserId}','${createTime}')">${item.totalMoney}</a></td>--%>
                    <%--<td>${item.avgMoney}元/件</td>--%>
                    <%--<td><a onclick="openInfo('${item.id}','ins','${item.reState}','','')">${item.insMoney}</a></td>--%>
                    <%--<td><a onclick="openInfo('${item.id}','help','${item.reState}','','')">${item.helpMoney}</a></td>--%>

<%--&lt;%&ndash;                    <td>${item.trafficMoney}</td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <td>${item.fileMoney}</td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <td>${item.stayMoney}</td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <td>${item.crossTrafficMoney}</td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <td>${item.mileageMoney}</td>&ndash;%&gt;--%>
                    <%--<td>${item.reStateStr}</td>--%>
                    <%--<td>--%>
                        <%--<c:if test="${menuCode == 'list'}">--%>
                            <%--<c:if test="${item.reState == 1 && (item.surveyUserId == currentUserId)}">--%>
                                <%--<a href="javascript:;" onclick="operate('${item.id}','org_submit',true,this);">提交机构审核</a>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${item.reState == 2 && orgRole && !reSupervisor}">--%>
                                <%--<a href="javascript:;" onclick="operate('${item.id}','finance_submit',true,this);">提交财务审核</a>--%>
<%--&lt;%&ndash;                                <a href="javascript:;" onclick="operate('${item.id}','org_reject',false,this);">驳回</a>&ndash;%&gt;--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${item.reState == 3 && finance && !reSupervisor}">--%>
                                <%--<a href="javascript:;" onclick="operate('${item.id}','finance_pass',true,this);">审核通过</a>--%>
                                <%--<a href="javascript:;" onclick="operate('${item.id}','finance_reject',false,this);">驳回</a>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${item.reState == 6 && (item.surveyUserId == currentUserId)}">--%>
                                <%--<a href="javascript:;" onclick="operate('${item.id}','pay_ok',true,this);">确认到账</a>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${item.reState == 8 && orgRole && !reSupervisor}">--%>
                                <%--<a onclick="showReason('${item.rejectDesc}');">查看驳回原因</a>--%>
                                <%--<a href="javascript:;" onclick="operate('${item.id}','finance_submit',true,this);">提交财务审核</a>--%>
                            <%--</c:if>--%>
<%--&lt;%&ndash;                            <c:if test="${item.reState == 9 && (item.surveyUserId == currentUserId)}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <a onclick="showReason('${item.rejectDesc}');">查看驳回原因</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <a href="javascript:;" onclick="operate('${item.id}','org_submit',true,this);">提交机构审核</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            </c:if>&ndash;%&gt;--%>
                        <%--</c:if>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</c:forEach>--%>
            <%--</tbody>--%>
        <%--</table>--%>
        <div class="table-content">
            <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">
            </table>
        </div>
    </div><!--panel-info-->

    <div class="main-bottom">
        <%--<jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">--%>
            <%--<jsp:param name="paginationObjectName" value="apiRsp"/>--%>
            <%--<jsp:param name="pageNoName" value=""/>--%>
            <%--<jsp:param name="requestUrl"--%>
                       <%--value="${ctx}/fee/list?menuCode=${menuCode}&searchCode=${searchCode}&reId=${reId}&reName=${reName}&reState=${reState}&investigators=${investigators}&surveyOrgIds=${surveyOrgIds}&sortField=${sortField}&sortType=${sortType}"/>--%>
            <%--<jsp:param name="refreshDiv" value=""/>--%>
        <%--</jsp:include>--%>
    </div><!--main-bottom-->



    <input type="hidden" name="orgRole" value="${orgRole}">
    <input type="hidden" name="reSupervisor" value="${reSupervisor}">
    <input type="hidden" name="currentUserId" value="${currentUserId}">
    <input type="hidden" name="finance" value="${finance}">

    <input type="hidden" name="alllist" id="alllist" value='${list}'>
    <input type="hidden" name="isShow" id="isShow" value="${show}">




</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<%--<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect.js?V=1"></script>--%>
<script src="${ctx}/js/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        soulTable: 'soulTable',
        xmSelect: 'xm-select'
    })

    layui.use(['layer','form', 'table','xmSelect','soulTable'], function () {
        layer = layui.layer

        var table = layui.table,
            form = layui.form,
            xmSelect = layui.xmSelect,
            soulTable = layui.soulTable


        var demo1 =  '',demo2 = '',demo55 = ''

        if ($('#isShow').val() == 'true'){
             demo1 = xmSelect.render({
                el: '#surveyOrgIdss',
                theme: {
                    color: '#3BA9FF',
                },
                toolbar: {
                    show: true
                },
                filterable: true,
                filterDone: function(val, list){
                    $('.xm-option-content').each(function () {
                        var _this = $(this)
                        _this.attr('title', _this.text())
                    })
                },
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
                                            '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                    })
                                    return _html
                                }
                            }
                        },
                    }
                },
                data: []
            })
             demo2 = xmSelect.render({
                el: '#investigatorss',
                theme: {
                    color: '#3BA9FF',
                },
                toolbar: {
                    show: true
                },
                filterable: true,
                filterDone: function(val, list){
                    $('.xm-option-content').each(function () {
                        var _this = $(this)
                        _this.attr('title', _this.text())
                    })
                },
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
                                            '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                    })
                                    return _html
                                }
                            }
                        },
                    }
                },
                data: []
            })


            var alllist = $("#alllist").val();
            alllist = JSON.parse(alllist);
            filterJson(demo1, alllist.franseList, 'id', 'name', false, false)

            filterJson(demo2, alllist.investigator, 'userId', 'realName', false, false)
        }

        demo55 = xmSelect.render({
            el: '#reStates',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            toolbar: {
                show: true
            },
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
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
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })
        var reStateJson = [{"id" : 1,"name" : "待提交发票"},{"id" : 2,"name" : "待机构审核"},{"id" : 3,"name" : "待财务审核"},{"id" : 5,"name" : "付款中"},{"id" : 7,"name" : "报销完成"}]
        filterJson(demo55, reStateJson, 'id', 'name', false, false)
        var s = [2];
        var orgRole = $('input[name=orgRole]').val()
        var finance = $('input[name=finance]').val()
        if (orgRole == 'true' && finance == 'false'){
            demo55.setValue(s)
        }
        function filterJson(demo, newJson, id, name, flag, selected) {
            var demoList = [],
                demoValues = []
            console.log(newJson)
            newJson.map(function (cur) {
                var _name = name ? cur[name] : cur.name
                var _id = id ? cur[id] : cur.id
                var param = {
                    name: _name,
                    value: _id,
                }
                if (selected) {
                    Object.assign(param, {selected: true})
                }
                demoList.push(param)
                demoValues.push(_id)
            })
            if (!flag) {
                demo.update({
                    data: demoList
                })
            } else {
                return demoList
            }
            setTimeout(function () {
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            }, 200)
        }





        $('#batchOperateBtn').click(function () {
            var param = {
                btnCode: 'reInfoList',
                // reState: $('select[name=reState] option:selected').val(),
                reName: $('input[name=reName]').val(),
                rId: $('input[name=rId]').val(),
                reStates: demo55.getValue('valueStr')
            }
            if ($('#isShow').val() == 'true'){
                Object.assign(param,{
                    surveyOrgIds: demo1.getValue('valueStr'),
                    investigators:  demo2.getValue('valueStr')
                })
            }
            setTable(_cols, param)
        })

        function barDemo(d) {
            var _html = ''
            var menuCode = $('input[name=menuCode]').val(),
                orgRole = $('input[name=orgRole]').val(),
                reSupervisor = $('input[name=reSupervisor]').val(),
                currentUserId= $('input[name=currentUserId]').val(),
                finance= $('input[name=finance]').val()

            if (menuCode == 'list'){
                if (d.reState == 1 && (d.surveyUserId == currentUserId)){
                    _html = '<a class="editA" lay-event="org_submit">提交发票</a>'
                }
                if (d.reState != 1){
                    _html += '<a class="editA" lay-event="view_bill">查看发票</a>'
                }
                if (d.reState == 2 && orgRole == 'true' && reSupervisor == 'false') {
                    _html += '<a class="editA" lay-event="finance_submit">提交财务审核</a><a class="editA" lay-event="org_reject">驳回</a>'
                }
                if (d.reState == 3 && finance == 'true' && reSupervisor == 'false'){
                    _html += '<a class="editA" lay-event="finance_pass">审核通过</a>'+
                            '<a class="editA" lay-event="finance_reject">驳回</a>'
                }
                if (d.reState == 6 && (d.surveyUserId == currentUserId)){
                    _html += '<a class="editA" lay-event="pay_ok">确认到账</a>'
                }
                // if (d.reState == 8 && orgRole == 'true' && reSupervisor == 'false'){
                //     _html += '<a class="editA" lay-event="showReason" >查看驳回原因</a>'+
                //             '<a class="editA" lay-event="finance_submit">提交财务审核</a>'
                // }
                if((d.reState == 9 || d.reState == 8) && (d.surveyUserId == currentUserId)){
                    _html += '<a class="editA" lay-event="showReason" >查看驳回原因</a><a class="editA" lay-event="bill_upd">提交机构审核</a>'
                }
            }

            return  _html
        }
        var _cols = [
            [{
                    field: 'surveyUserName',
                    minWidth: 130,
                    title: '调查员',
                    fixed: 'left',
                },
                {
                    field: 'surveyOrgName',
                    minWidth: 180,
                    title: '调查机构',
                },
                {
                    field: 'reName',
                    minWidth: 150,
                    title: '清单名称',
                },
                {
                    field: 'reStateStr',
                    minWidth: 150,
                    title: '报销状态',
                },
                {
                    field: 'totalCaseNum',
                    minWidth: 140,
                    title: '关联案件数',
                    sort: true
                },
                {
                    field: 'totalMoney',
                    minWidth: 140,
                    title: '费用报销合计',
                    style: 'color: #3BA9FF;cursor: pointer;',
                    event: 'moneysum',
                    sort: true
                },
                {
                    field: 'avgMoney',
                    minWidth: 150,
                    title: '件均报销金额',
                    templet: function (d) {
                        var _html =  Math.round(d.avgMoney * 100) /100 + '元/件'
                        return _html
                    },
                    sort: true
                },
                {
                    field: 'huanbiMoney',
                    minWidth: 130,
                    title: '件均环比上月',
                    sort: true,
                    templet: function (d) {
                        var _html =  d.huanbiMoney ? d.huanbiMoney + '%' : '0'
                        return _html
                    },
                },
                {
                    field: 'orgAvgMoney',
                    minWidth: 120,
                    title: '机构件均',
                    templet: function (d) {
                        var _html =  Math.round(d.orgAvgMoney * 100) /100 + '元/件'
                        return _html
                    },
                    sort: true
                },
                {
                    field: 'cityinDrivingMoney',
                    minWidth: 120,
                    title: '市内交通费',
                    sort: true
                },
                {
                    field: 'medicalHistoryMoney',
                    minWidth: 170,
                    title: '病史费（含复印费）',
                    sort: true
                },
                {
                    field: 'troubleshootingMoney',
                    minWidth: 150,
                    title: '住院排查费用',
                    sort: true
                },
                {
                    field: 'opcTroubleshootingMoney',
                    minWidth: 150,
                    title: '门诊排查费用',
                    sort: true
                },
                {
                    field: 'printingMoney',
                    minWidth: 150,
                    title: '体检报告打印费',
                    sort: true
                },
                {
                    field: 'accommodatioMoney',
                    minWidth: 150,
                    title: '住宿费',
                    sort: true
                },
                {
                    field: 'crossDrivingMoney',
                    minWidth: 150,
                    title: '跨地市交通费（汽车、火车、飞机）',
                    sort: true
                },
                {
                    field: 'selfDrivingMoney',
                    minWidth: 150,
                    title: '跨地市交通费（自驾）',
                    sort: true
                },
                {
                    field: 'otherMoney',
                    minWidth: 150,
                    title: '其他费用',
                    sort: true
                },
                {
                    field: '',
                    minWidth: 280,
                    fixed: 'right',
                    title: '操作',
                    templet: function (d) {
                        return barDemo(d)
                    }
                },
            ]
        ]



        var param = {
            btnCode: 'reInfoList',
            reStates: orgRole == 'true' && finance == 'false' ? 2 : '',
            rId: $('input[name=rId]').val(),
        }
        setTable(_cols, param)

        function setTable(_cols, param) {
            console.log(param);
            $('button.ll-submit').attr('disabled', true)
            setTimeout(function () {
                $('button.ll-submit').removeAttr('disabled')
            }, 6000)
            var _h = $('.panel-heading').outerHeight() +100
            var fullH = 'full-' + _h
            var myTable = table.render({
                id: "test",
                elem: '#test',
                even: true,
                cols: _cols,
                page: true,
                limit: 10,
                limits: [10,15,20,30,40,50],
                height: fullH,
                drag: false,
                totalRow: true,
                url: '${ctx}/fee/getData',
                where: param,
                request: {
                    pageName: 'pageNum' //页码的参数名称，默认：page
                    ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
                },
                parseData: function (res) {
                    $(".tableCount").html(res.count)
                    return {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results,
                    }
                },
                initSort: {
                    field: 'avgMoney',
                    type: 'desc'
                },
                done: function (res) {
                    soulTable.render(this)
                    $('button.ll-submit').removeAttr('disabled')
                }
            })
            table.on('tool(test)', function (obj) {
                if (obj.event == 'moneysum') {
                    var downTime = new Date(obj.data.downTime)
                    var _month = downTime.getMonth()+1
                    var startStr = downTime.getFullYear() + '-'+ PrefixInteger(_month, 2)
                    var url = "${ctx}/fee/list?userId=" + obj.data.surveyUserId + "&startTime=" + startStr+"&menuCode=userClockDetails&orgId="+obj.data.surveyOrgId;
                    parent.parent.parent.addTab("案件打卡足迹",url,true);
                }else if(obj.event == 'finance_submit' || obj.event == 'finance_pass' || obj.event == 'pay_ok' || obj.event == 'finance_submit') {
                    operate(obj.data.id,obj.event,true,obj.data.totalMoney)
                }else if (obj.event == 'finance_reject' || obj.event == 'org_reject' ){
                    operate(obj.data.id,obj.event,false,obj.data.totalMoney)
                }else if (obj.event == 'showReason'){
                    showReason(obj.data.rejectDesc)
                } else if (obj.event == 'org_submit'){
                    var _height = $(document).height() * 0.96,
                        _width = $(document).width() * 0.96
                    openDialog({
                        frame: true,
                        title: "上传发票",
                        height: _height,
                        width: _width,
                        url: '${ctx}/fee/add?btnCode=add&id='+obj.data.id+'&cityinDrivingMoney='+(obj.data.cityinDrivingMoney+obj.data.crossDrivingMoney+obj.data.selfDrivingMoney)+'&medicalHistoryMoney='+(obj.data.medicalHistoryMoney+obj.data.troubleshootingMoney+obj.data.opcTroubleshootingMoney+obj.data.printingMoney+obj.data.otherMoney)+'&accommodatioMoney='+obj.data.accommodatioMoney,
                        // load: true
                    });
                }else if (obj.event == 'view_bill'){
                    var _height = $(document).height() * 0.96,
                        _width = $(document).width() * 0.96
                    openDialog({
                        frame: true,
                        title: "查看发票",
                        height: _height,
                        width: _width,
                        url: '${ctx}/fee/add?btnCode=view&id='+obj.data.id+'&cityinDrivingMoney='+(obj.data.cityinDrivingMoney+obj.data.crossDrivingMoney+obj.data.selfDrivingMoney)+'&medicalHistoryMoney='+(obj.data.medicalHistoryMoney+obj.data.troubleshootingMoney+obj.data.opcTroubleshootingMoney+obj.data.printingMoney+obj.data.otherMoney)+'&accommodatioMoney='+obj.data.accommodatioMoney,
                        // load: true
                    });
                }else if (obj.event == 'bill_upd'){
                    var _height = $(document).height() * 0.96,
                        _width = $(document).width() * 0.96
                    openDialog({
                        frame: true,
                        title: "查看发票",
                        height: _height,
                        width: _width,
                        url: '${ctx}/fee/add?btnCode=billUpd&id='+obj.data.id+'&cityinDrivingMoney='+(obj.data.cityinDrivingMoney+obj.data.crossDrivingMoney+obj.data.selfDrivingMoney)+'&medicalHistoryMoney='+(obj.data.medicalHistoryMoney+obj.data.troubleshootingMoney+obj.data.opcTroubleshootingMoney+obj.data.printingMoney+obj.data.otherMoney)+'&accommodatioMoney='+obj.data.accommodatioMoney,
                        // load: true
                    });
                }
            })
        }

    });
    function showReason(reason) {
        layer.open({
            title: '驳回原因'
            ,content:  reason
        });
    }


    function PrefixInteger(num, m) {
        return (Array(m).join(0) + num).slice(-m);
    }
    function operate(id, btnCode, ajax, _this) {
        // var totalMoney = $(_this).parents("tr").find("td").eq(2).html();
        var totalMoney = _this

        if (parseFloat(totalMoney)<=0){
            btnCode = "no_re_money";
        }
        if (ajax) {
            var url = "${ctx}/fee/operate", data = {"id": id, "btnCode": btnCode,"operateType":"reNew"};
            layer.confirm(totalMoney != 0 ? '确认提交？' : '该清单价格为0，提交之后将直接报销完成，确认没有任何需要报销的费用吗？', {
                btn: ['是', '否'] //按钮
            }, function () {
                $('.layui-layer-btn .layui-layer-btn0').css({
                    'pointer-events': 'none'
                })
                $.ajax({
                    url: url,
                    type: "post",
                    data: data,
                    success: function (res) {
                        res = JSON.parse(res)
                        if (res.isSuccess) {
                            // closeDialogRefresh();//关闭并刷新
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 1
                            }, function () {
                                location.reload();
                            })

                        } else {
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 2
                            })
                        }
                    }
                });
            }, function () {

            });
        } else {
            layer.prompt({title: "请输入拒绝原因（必填项）", formType: 2}, function (text, index) {
                var url = "${ctx}/fee/operate", param = {"id": id, "btnCode": btnCode, "reason": text,"operateType":"reNew"};
                layer.close(index)
                $.ajax({
                    url: url,
                    type: "post",
                    data: param,
                    success: function (res) {
                        res = JSON.parse(res)
                        if (res.isSuccess) {
                            // closeDialogRefresh();//关闭并刷新
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 1
                            }, function () {
                                location.reload();
                            })
                        } else {
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 2
                            })
                        }
                    }
                });
            })
        }
    }


    function openInfo(id,type,reState,surveyUserId,createTime) {
        openDialog({
            frame: true,
            title: "打卡列表",
            height: 800,
            width: 1500,
            url: "${ctx}/fee/list?menuCode=re_clock_list&reInfoId="+id+"&type="+type+"&reState="+reState+"&userId="+surveyUserId+"&createTime="+createTime,
            load: true
        });
    }
</script>
</body>
</html>
