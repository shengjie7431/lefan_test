<%--
  Created by IntelliJ IDEA.
  User: lixianfeng
  Date: 2020/8/4
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/layui/layui_exts/soulTable.css">
    <style>
        .layui-table-main .layui-table-cell{
            height: auto;
        }
        .layui-form-item {
            margin: 0 !important;
        }

        .searchs {
            padding: 10px 0 7px 0;
            background-color: #d9edf7;
        }

        label.layui-form-label {
            width: 100px;
            padding: 6px 15px;
            padding-left: 0;
            margin-bottom: 0;
        }
        .layui-form-item .layui-input-inline {
            width: 530px !important;
        }

        .orgCell{
            display: flex;
            align-items: center;
        }
        .orgCell div{
            margin-right: 5px;
        }
        .orgName{
            width: 184px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;

        }
        .orgState{
            width: 84px;
        }
        .orgDate{
            width: 110px;
        }
        /*.itemName{*/
            /*width: 280px;*/
            /*white-space: nowrap;*/
            /*overflow: hidden;*/
            /*text-overflow: ellipsis;*/
        /*}*/
        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }
        .orgBar{
            display: none;
        }
        /*.layui-export{display: none}*/
    </style>
</head>
<body>
<input type="hidden" value='${params}' id="params">
<input type="hidden" value='${haveReport}' id="haveReport">
<input type="hidden" value='${franchiseesJson}' id="franchiseesJson">

<input type="hidden" value='${consignorsJson}' id="consignorsJson">

<div class="layui-form searchs" lay-filter="search">
    <div class="layui-form-item ">
        <div class="layui-inline orgBar orgBar2">
            <label class="layui-form-label label-name">保险公司</label>
            <div class="layui-input-inline">
                <div id="consignorsIds" class="selectMul"></div>
            </div>
        </div>
        <div class="layui-inline orgBar orgBar3">
            <label class="layui-form-label label-name">部门</label>
            <div class="layui-input-inline">
                <div id="departmentsNames" class="selectMul"></div>
            </div>
        </div>
        <div class="layui-inline orgBar orgBar1">
            <label class="layui-form-label label-name">调查机构</label>
            <div class="layui-input-inline">
                <div id="entrustOrgIds" class="selectMul"></div>
            </div>
        </div>
        <div class="layui-inline" style="margin-left: 20px;">
            <button lay-submit class="ll-submit layui-btn layui-btn-normal layui-btn-sm layui-btn-radius"
                    lay-filter="submit" style="width: 86px;display: none">查询 <i
                    class="layui-icon layui-icon-search"></i></button>
            <button class="layui-btn layui-btn-normal layui-btn-sm layui-btn-radius layui-export"
                    style="width: 86px">导出 <i class="layui-icon layui-icon-export"></i></button>
        </div>
    </div>
</div>

<div class="table-content">
    <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">
    </table>
</div>
<script src="${ctx}/js/jquery-3.4.1.js" charset="utf-8"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>

<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        soulTable: 'soulTable',
        xmSelect: 'xm-select'
    })

    var tableData = ''
    var params =  $('#params').val() ? JSON.parse($('#params').val()) : {}


    layui.use(['layer', 'table', 'soulTable', 'jquery', 'util', 'xmSelect', 'form'], function () {
            var layer = layui.layer,
                table = layui.table,
                soulTable = layui.soulTable,
                $ = layui.jquery,
                util = layui.util,
                xmSelect = layui.xmSelect,
                form = layui.form

            var demo1 = xmSelect.render({
                el: '#entrustOrgIds',
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
                template({ item, sels, name }){
                    return '<span class="itemName"> '+item.name+ '</span><span style="position: absolute; right: 10px; color: #8799a3">'+item.infoName+'</span>'
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
                                            '<div class="xm-label-block lf-select-block">' + cur
                                                .name + '</div>'
                                    })
                                    return _html
                                }
                            }
                        },
                    }
                },
                data: []
            })
            var demo2 = xmSelect.render({
            el: '#consignorsIds',
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
            template({ item, sels, name }){
                return '<span class="itemName"> '+item.name+ '</span><span style="position: absolute; right: 10px; color: #8799a3">'+item.infoName+'</span>'
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
                                        '<div class="xm-label-block lf-select-block">' + cur
                                            .name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })
            var demo3 = xmSelect.render({
            el: '#departmentsNames',
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
            template({ item, sels, name }){
                return '<span class="itemName"> '+item.name+ '</span><span style="position: absolute; right: 10px; color: #8799a3">'+item.infoName+'</span>'
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
                                        '<div class="xm-label-block lf-select-block">' + cur
                                            .name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })


            form.on('submit(submit)', function (data) {
                var ids = []
                var departmentNames = []
                var newTableData =[]

                if(params.dataType == 'entrust'){
                   ids = demo1.getValue('value')
                    departmentNames = demo3.getValue('value');
                }else  if(params.dataType == 'org'){
                   ids = demo2.getValue('value')
                }

                if ((!ids.length || ids.length == listJson.length) && !departmentNames.length){
                    newTableData = [].concat(tableData)
                }else{
                    tableData.map(function (cur) {
                        var _items = []
                        if(params.dataType == 'entrust'){
                            if (ids.length && departmentNames.length){
                                if (cur.surveyOrgs){
                                    cur.surveyOrgs.map(function (item) {
                                        if (ids.indexOf(item.surveyOrgId) > -1){
                                            _items.push(item)
                                        }
                                    })
                                    if (_items.length && departmentNames.indexOf(cur.entrustDepartmentName) > -1){
                                        var newCell = {}
                                        Object.assign(newCell,cur,{surveyOrgs:_items})
                                        newTableData.push(newCell)
                                    }
                                }
                            }
                            else if (ids.length){
                                if (cur.surveyOrgs){
                                    cur.surveyOrgs.map(function (item) {
                                        if (ids.indexOf(item.surveyOrgId) > -1){
                                            _items.push(item)
                                        }
                                    })
                                    if (_items.length){
                                        var newCell = {}
                                        Object.assign(newCell,cur,{surveyOrgs:_items})
                                        newTableData.push(newCell)
                                    }
                                }
                            } else if (departmentNames.length){
                                if(departmentNames.indexOf(cur.entrustDepartmentName) > -1) {
                                    newTableData.push(cur)
                                }
                            }
                        }else  if(params.dataType == 'org'){
                            if (ids.indexOf(cur.entrustOrgId) > -1){
                                newTableData.push(cur)
                            }

                        }


                    })
                }


                reloadTable(newTableData)
            });
            var listJson = ''
            if(params.dataType == 'entrust'){
                $('.orgBar1').show()
                $('.ll-submit').show()
                var listJson = $("#franchiseesJson").val();
                listJson = JSON.parse(listJson);
                filterJson(demo1, listJson,'id','name',false, false)
            }else  if(params.dataType == 'org'){
                $('.orgBar2').show()
                $('.ll-submit').show()

                var listJson = $("#consignorsJson").val();
                listJson = JSON.parse(listJson);
                filterJson(demo2, listJson,'id','company',false, false)
            }
            function filterJson(demo, newJson, id, name, flag, selected) {
                var demoList = [],
                    demoValues = []
                newJson.map(function (cur) {
                    var _name = name ? cur[name] : cur.name
                    var _id = id ? cur[id] : cur.id
                    var _showName = '(调查中:'+cur.surveyNum+' 初审中:'+cur.checkNum+' )'
                    var param = {
                        name: _name,
                        value: _id,
                        infoName:_showName
                    }
                    if (selected) {
                        Object.assign(param, {
                            selected: true
                        })
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


        function filterJson2(demo, newJson, id, name, flag, selected) {
            var demoList = [],
                demoValues = []
            newJson.map(function (cur) {
                var _name = name ? cur[name] : cur.name
                var _id = id ? cur[id] : cur.id
                var _showName = '(案件数:'+cur.num+' )'
                var param = {
                    name: _name,
                    value: _id,
                    infoName:_showName
                }
                if (selected) {
                    Object.assign(param, {
                        selected: true
                    })
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

            //重载表格数据
            function reloadTable(_data) {
                $('.table-content').empty()
                $('.table-content').append(
                    ' <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg"></table>'
                )
                setTable(_data)
            }

            //总部
            var _cols1 = [
            [{
                field: 'surveyCaseNo',
                minWidth: 120,
                style: 'color: #3BA9FF!important;cursor: pointer;',
                event: 'edit',
                title: '案件编号',
            }, {
                field: 'entrustOrgName',
                minWidth: 140,
                title: '保险公司',
            },
                {
                    field: 'entrustDepartmentName',
                    minWidth: 140,
                    title: '部门',
                },
                {
                    field: 'surveyPerson',
                    minWidth: 120,
                    title: '被调查人',
                },

                {
                    field: 'sex',
                    minWidth: 80,
                    title: '性别',
                    templet: function (d) {
                        var _html = ''
                        if (d.sex == 1){
                            _html='男'
                        }else if (d.sex == 2){
                            _html='女'
                        }
                        return _html
                    }
                },
                {
                    field: 'idType',
                    minWidth: 100,
                    title: '证件类型',
                    templet: function (d) {
                        var _html = ''
                        if (d.idType == 1){
                            _html='身份证'
                        }else if (d.idType == 2){
                            _html='驾驶证'
                        }else if(d.idType == 3){
                            _html = '护照号码'
                        }else{
                            _html = "其他证件"
                        }
                        return _html
                    }
                },
                {
                    field: 'idNumber',
                    minWidth: 180,
                    title: '证件号码',
                },
                {
                    field: 'age',
                    minWidth: 70,
                    title: '年龄',
                },
                {
                    field: 'insureType',
                    minWidth: 100,
                    title: '保险种类',
                    templet: function (d) {
                        var _html = ''
                        if (d.idType == 1){
                            _html='医疗'
                        }else if (d.idType == 2){
                            _html='重疾'
                        }else if (d.idType == 3){
                            _html='身故'
                        }else if (d.idType == 4){
                            _html='其他'
                        }
                        return _html
                    }
                },
                {
                    field: 'insureTime',
                    minWidth: 140,
                    title: '投保日期',
                    templet: function (d) {
                        var _html = d.insureTime ? util.toDateString(d.insureTime, 'yyyy年MM月dd日') : ''
                        return _html
                    }

                },
                {
                    field: 'dangerTime',
                    minWidth: 140,
                    title: '出险日期',
                    templet: function (d) {
                        var _html = d.dangerTime ? util.toDateString(d.dangerTime, 'yyyy年MM月dd日') : ''
                        return _html
                    }
                },
                {
                    field: 'dangerAddress',
                    minWidth: 120,
                    title: '出险地点',
                },
                {
                    field: 'insureTakeTime',
                    minWidth: 140,
                    title: '保单生效日期',
                    templet: function (d) {
                        var _html = d.insureTakeTime ? util.toDateString(d.insureTakeTime, 'yyyy年MM月dd日') : ''
                        return _html
                    }
                },
                {
                    field: 'claimsNo',
                    minWidth: 140,
                    title: '理赔编号',
                },
                {
                    field: 'busName',
                    minWidth: 120,
                    title: '领域',
                },
                {
                    field: 'serviceName',
                    minWidth: 120,
                    title: '业务类型',
                },
                {
                    field: 'surveyInfo',
                    minWidth: 120,
                    title: '案件基本信息',
                },
                {
                    field: 'surveyItem',
                    minWidth: 120,
                    title: '调查事项',
                },
                {
                    field: 'payType',
                    minWidth: 120,
                    title: '结算方式',
                    templet: function (d) {
                        var _html = ''
                        if (d.idType == 1){
                            _html='一口价'
                        }else if (d.idType == 2){
                            _html='基本费+减损奖励'
                        }else if (d.idType == 3){
                            _html='任务'
                        }else if (d.idType == 4){
                            _html='任务+减损奖励'
                        }
                        return _html
                    }
                },
                {
                    field: 'investigationArea',
                    minWidth: 120,
                    title: '调查区域',
                    templet: function (d) {
                        var _html = ''
                        if (d.idType == 1){
                            _html='直辖市（市区）'
                        }else if (d.idType == 2){
                            _html='直辖市（郊区）'
                        }else if (d.idType == 3){
                            _html='省会'
                        }else if (d.idType == 4){
                            _html='地级市'
                        }else if (d.idType == 5){
                            _html='县级市'
                        }
                        return _html
                    }
                },
                {
                    field: 'surveyStateName',
                    minWidth: 120,
                    title: '当前状态',
                },
                {
                    field: 'surveyOrgName',
                    minWidth: 624,
                    title: '调查机构,机构案件状态,分派机构时间,机构提交时间,机构截止时间',
                    templet: function (d) {
                        var orgs = d.surveyOrgs
                        var _html = ''
                        orgs.map(function (cur,i) {
                            var state = ''
                            if(cur.orgSurveyState == 1){
                                state = "调查中";
                            }else if(cur.orgSurveyState == 2 || cur.orgSurveyState == 5){
                                state = "初审中";
                            }else if(cur.orgSurveyState == 4){
                                state = "已提交";
                            }
                            var createTime = cur.createTime ? util.toDateString(cur.createTime, 'yyyy年MM月dd日') : ''
                            var reportDate = cur.reportDate ? util.toDateString(cur.reportDate, 'yyyy年MM月dd日') : ''
                            var orgEndTime = cur.orgEndTime ? util.toDateString(cur.orgEndTime, 'yyyy年MM月dd日') : ''

                            _html += '<div class="orgCell"><div class="orgName" title="'+cur.surveyOrgName+'">'+cur.surveyOrgName+'  </div>'+
                                '<div class="orgState">'+state+'  </div>' +
                                '<div class="orgDate">'+createTime+'  </div>' +
                                '<div class="orgDate">'+reportDate+'  </div>' +
                                '<div class="orgDate">'+orgEndTime+'  </div></div>'



                        })
                        return _html
                    }
                },
                // {
                //     field: 'orgSurveyState',
                //     minWidth: 120,
                //     title: '机构案件状态',
                //     templet: function (d) {
                //         var _html = ''
                //         if (d.reportDate){
                //             _html = '调查中'
                //         } else{
                //             _html = '已提交'
                //
                //         }
                //         return _html
                //     }
                // },
                // {
                //     field: 'createTime',
                //     minWidth: 120,
                //     title: '分派机构时间',
                //     templet: function (d) {
                //         var _html = d.createTime ? util.toDateString(d.createTime, 'yyyy年MM月dd日') : ''
                //         return _html
                //     }
                // },
                // {
                //     field: 'reportDate',
                //     minWidth: 120,
                //     title: '机构提交时间',
                //     templet: function (d) {
                //         var _html = d.reportDate ? util.toDateString(d.reportDate, 'yyyy年MM月dd日') : ''
                //         return _html
                //     }
                // },
                // {
                //     field: 'orgEndTime',
                //     minWidth: 120,
                //     title: '机构截止时间',
                //     templet: function (d) {
                //         var _html = d.orgEndTime ? util.toDateString(d.orgEndTime, 'yyyy年MM月dd日') : ''
                //         return _html
                //     }
                // },
                {
                    field: 'entrustDate',
                    minWidth: 140,
                    title: '委托时间',
                    templet: function (d) {
                        var _html = d.entrustDate ? util.toDateString(d.entrustDate, 'yyyy年MM月dd日') : ''
                        return _html
                    }
                },
                {
                    field: 'lefanReportDate',
                    minWidth: 140,
                    title: '复审开始时间',
                    templet: function (d) {
                        var _html = d.lefanReportDate ? util.toDateString(d.lefanReportDate, 'yyyy年MM月dd日') : ''
                        return _html
                    }
                },
                {
                    field: 'entrustReportStartDate',
                    minWidth: 140,
                    title: '复审通过时间',
                    templet: function (d) {
                        var _html = d.entrustReportStartDate ? util.toDateString(d.entrustReportStartDate, 'yyyy年MM月dd日') : ''
                        return _html
                    }
                },
                {
                    field: 'entrustReportEndDate',
                    minWidth: 140,
                    title: '保司通过时间',
                    templet: function (d) {
                        var _html = d.entrustReportEndDate ? util.toDateString(d.entrustReportEndDate, 'yyyy年MM月dd日') : ''
                        return _html
                    }
                },
                {
                    field: 'endTime',
                    minWidth: 140,
                    title: '调查截止日期',
                    templet: function (d) {
                        var _html = d.endTime ? util.toDateString(d.endTime, 'yyyy年MM月dd日') : ''
                        return _html
                    }
                },
                {
                    field: 'agingDay',
                    minWidth: 120,
                    title: '时效天数',
                },
                {
                    field: 'reportCompletion',
                    minWidth: 120,
                    title: '调查结论',
                },
                {
                    field: 'entrustMoney',
                    minWidth: 200,
                    title: '乐凡申请结算价格',
                },
                {
                    field: 'entrustOkPrice1',
                    minWidth: 200,
                    title: '委托方确认结算价格基本费',
                },
                {
                    field: 'entrustOkPrice2',
                    minWidth: 200,
                    title: '委托方确认结算价格减损奖励',
                },
                {
                    field: 'price1IsCalc',
                    minWidth: 160,
                    title: '基本费是否结算',
                    templet: function (d) {
                        var _html = ''
                        if (d.price1IsCalc == 0){
                            _html='否'
                        }else if (d.price1IsCalc == 1){
                            _html='是'
                        }
                        return _html
                    }
                },
                {
                    field: 'price2IsCalc',
                    minWidth: 160,
                    title: '减损奖励是否结算',
                    templet: function (d) {
                        var _html = ''
                        if (d.price2IsCalc == 0){
                            _html='否'
                        }else if (d.price2IsCalc == 1){
                            _html='是'
                        }
                        return _html
                    }
                },
                {
                    field: 'isSun',
                    minWidth: 120,
                    title: '是否阳性',
                    templet: function (d) {
                        var _html = ''
                        if (d.isSun == 0){
                            _html='否'
                        }else if (d.isSun == 1){
                            _html='是'
                        }
                        return _html
                    }
                }]]

            //调查员 机构
            var _cols2 = [
            [{
                field: 'surveyCaseNo',
                minWidth: 120,
                style: 'color: #3BA9FF!important;cursor: pointer;',
                event: 'edit',
                title: '案件编号',
            }, {
                field: 'entrustOrgName',
                minWidth: 140,
                title: '保险公司',
            },
                {
                    field: 'surveyPerson',
                    minWidth: 120,
                    title: '被调查人',
                },
                {
                    field: 'busName',
                    minWidth: 120,
                    title: '领域',
                },
                {
                    field: 'serviceName',
                    minWidth: 120,
                    title: '业务类型',
                },
                {
                    field: 'surveyStateName',
                    minWidth: 120,
                    title: '当前状态',
                },

                {
                    field: 'entrustDate',
                    minWidth: 140,
                    title: '委托时间',
                    templet: function (d) {
                        var _html = d.entrustDate ? util.toDateString(d.entrustDate, 'yyyy年MM月dd日') : ''
                        return _html
                    }
                }]]

            //机构报表
            var _cols3 = [
            [{
                field: 'surveyCaseNo',
                minWidth: 120,
                style: 'color: #3BA9FF!important;cursor: pointer;',
                event: 'edit',
                title: '案件编号',
            }, {
                field: 'entrustOrgName',
                minWidth: 140,
                title: '保险公司',
            },
                {
                    field: 'surveyPerson',
                    minWidth: 120,
                    title: '被调查人',
                },
                {
                    field: 'busName',
                    minWidth: 120,
                    title: '领域',
                },
                {
                    field: 'serviceName',
                    minWidth: 120,
                    title: '业务类型',
                },
                {
                    field: 'surveyStateName',
                    minWidth: 120,
                    title: '当前状态',
                },

                {
                    field: 'entrustDate',
                    minWidth: 140,
                    title: '委托时间',
                    templet: function (d) {
                        var _html = d.entrustDate ? util.toDateString(d.entrustDate, 'yyyy年MM月dd日') : ''
                        return _html
                    }
                }
            ]]
        if (params.from == 'indexToOrg') {
            _cols3 = [
                [{
                    field: 'surveyCaseNo',
                    minWidth: 120,
                    style: 'color: #3BA9FF!important;cursor: pointer;',
                    event: 'edit',
                    title: '案件编号',
                }, {
                    field: 'entrustOrgName',
                    minWidth: 140,
                    title: '保险公司',
                },
                    {
                        field: 'surveyPerson',
                        minWidth: 120,
                        title: '被调查人',
                    },
                    {
                        field: 'busName',
                        minWidth: 120,
                        title: '领域',
                    },
                    {
                        field: 'serviceName',
                        minWidth: 120,
                        title: '业务类型',
                    },
                    {
                        field: 'surveyStateName',
                        minWidth: 120,
                        title: '当前状态',
                    },

                    {
                        field: 'entrustDate',
                        minWidth: 140,
                        title: '委托时间',
                        templet: function (d) {
                            var _html = d.entrustDate ? util.toDateString(d.entrustDate, 'yyyy年MM月dd日') : ''
                            return _html
                        }
                    },
                    {
                        field: 'surveyOrgName1',
                        minWidth: 140,
                        title: '调查机构',
                        templet : function(d){
                            return d.surveyOrgName
                        }
                    }, {
                    field : 'markError',
                    minWidth: 140,
                    title: '差错类型',
                    templet : function(d){
                        var _html = "";
                        if(d.markError == 1 || d.markError == 2){
                            _html = d.markError == 1 ? "常规差错" : "重大差错"
                        }
                        return _html;
                    }
                },
                    {
                        field : 'markErrorRemark',
                        minWidth: 140,
                        title: '差错备注',
                    }
                ]]
        }
        if (params.from == 'indexToOrgReturn'){
            _cols3 = [
                [{
                    field: 'surveyCaseNo',
                    minWidth: 120,
                    style: 'color: #3BA9FF!important;cursor: pointer;',
                    event: 'edit',
                    title: '案件编号',
                }, {
                    field: 'entrustOrgName',
                    minWidth: 140,
                    title: '保险公司',
                },
                    {
                        field: 'surveyPerson',
                        minWidth: 120,
                        title: '被调查人',
                    },
                    {
                        field: 'busName',
                        minWidth: 120,
                        title: '领域',
                    },
                    {
                        field: 'serviceName',
                        minWidth: 120,
                        title: '业务类型',
                    },
                    {
                        field: 'surveyStateName',
                        minWidth: 120,
                        title: '当前状态',
                    },

                    {
                        field: 'entrustDate',
                        minWidth: 140,
                        title: '委托时间',
                        templet: function (d) {
                            var _html = d.entrustDate ? util.toDateString(d.entrustDate, 'yyyy年MM月dd日') : ''
                            return _html
                        }
                    },
                    {
                        field: 'surveyOrgName1',
                        minWidth: 140,
                        title: '调查机构',
                        templet : function(d){
                            return d.surveyOrgName
                        }
                    },
                    {
                        field : 'returnRemark',
                        minWidth: 140,
                        title: '退回原因',
                    }
                ]]
        }

            _cols = _cols2

            var haveReport = $('#haveReport').val()

            if (haveReport == 'false'){
                _cols = _cols2
                if(params.dataType == 'survey'){
                    $('.searchs').hide()
                }
            }else if (haveReport == 'true'){
                _cols = _cols1
                $('.layui-export').show()

            }

            if (params.dataType == 'org'){
                _cols = _cols3;
            }

            var myTable = ''
             layer.load()
            $.ajax({
                url:'${ctx}/survey/report/getData',
                type: 'post',
                data: params,
                success: function (res) {
                    layer.closeAll()
                    res = JSON.parse(res)
                    tableData = res.results.cases
                    setTable(res.results.cases)

                    if (params.dataType == 'entrust') {
                        $('.orgBar3').show()
                        var json = res.results.departments;
                        filterJson2(demo3, json,'departmentName','departmentName',false, false)
                    }
                }
            })

            $('.layui-export').on('click', function (e) {
                var _this = $('.layui-export')
                _this.attr('disabled', true)
                soulTable.export(myTable, {
                    filename: '案件列表.xlsx'
                })
                setTimeout(function () {
                    _this.removeAttr('disabled')
                }, 5000)
            })
            function setTable(_data) {
                 myTable = table.render({
                    id: "test",
                    elem: '#test',
                    cols: _cols,
                    page: true,
                    limit: 20,
                    limits:[20,30,40,50,60,70,80,90],
                    height: 'full-100',
                    drag: false,
                    data: _data,
                    even: true,
                     done: function (res) {
                         var moreCell = $('.layui-table-box .layui-table-header thead th[data-field=surveyOrgName] .layui-table-cell')
                         var _names = moreCell.find('span').text()
                         var _nameArr = _names.split(',')
                         var _html = '<div class="orgCell"><div class="orgName">'+_nameArr[0]+'  </div>'+
                             '<div class="orgState">'+_nameArr[1]+'</div>' +
                             '<div class="orgDate">'+_nameArr[2]+'</div>' +
                             '<div class="orgDate">'+_nameArr[3]+'</div>' +
                             '<div class="orgDate">'+_nameArr[4]+'</div></div>'

                         moreCell.html(_html)

                     }
                })
                table.on('tool(test)', function (obj) {
                    if (obj.event == 'edit'){
                        info(params.dataType, obj.data.surveyInfoId, obj.data.surveyAssorgCaseId, obj.data.surveyInvestigatorCaseId);
                    }
                })
            }

        })

        var info = function(dataType,surveyInfoId,surveyAssignCaseId,surveyInvestigatorCaseId){
            var menuCode = 'all-list';
            var url ='';
            //平台角色
            if(dataType == 'all'){
                url="${ctx}/survey/case/info?id=" + surveyInfoId + "&menuCode=" +menuCode

            }
            if (dataType  == 'entrust'){
                url="${ctx}/survey/case/info?id=" + surveyInfoId + "&menuCode=my-list"
            }
            //机构角色
            if(dataType == 'org'){
                menuCode = 'org-review-list';
                url="${ctx}/survey/case/info?id=" + surveyInfoId + "&menuCode=" +menuCode+"&fromName=pointsDetails&assignOrgId="+surveyAssignCaseId
            }
            //调查员角色
            if(dataType == 'survey'){
                menuCode = 'dcy-list';
                url="${ctx}/survey/case/sic/info?id=" + surveyInvestigatorCaseId + "&menuCode="+menuCode
            }

            var width = $(document.body).outerWidth();
            var height = $(document).outerHeight() - 20;
            var title="详情";
            openDialog({
                frame:true,
                title:title,
                height:height,
                width:width,
                url:url,
                // load:true
            });
        }

</script>
</body>

</html>
