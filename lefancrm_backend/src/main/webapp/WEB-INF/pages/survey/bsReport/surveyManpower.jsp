<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>调查员人力报表</title>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/layui/layui_exts/soulTable.css">
    <style>
        .main {
            width: 99%;
            margin: 10px auto;
        }

        .selectMul {
            width: 100%;
        }

        .searchs {
            padding: 10px 0 7px 0;
            background-color: #d9edf7;
        }

        .layui-form-item {
            margin: 0 !important;
        }

        label.layui-form-label {
            width: 86px;
            padding: 6px 15px;
            padding-left: 0;
            margin-bottom: 0;
        }

        .layui-form-select dl dd.layui-this {
            background-color: #f2f2f2 !important;
            color: #000 !important;
        }

        .layui-form-select dl dd.layui-select-tips {
            color: #999 !important;

        }

        .layui-form-select dl dd {
            color: #000;
        }

        .layui-table-view .layui-table {
            width: 100% !important;
        }

        .data-types {
            display: flex;
            justify-content: flex-end;
            background-color: #fff;
            margin-right: 10px;
        }

        .data-types .data-type {
            width: 60px;
            height: 32px;
            line-height: 32px;
            text-align: center;
            border: 1px solid #bbb;
            border-right: none;
            cursor: pointer;
        }

        .data-types .data-type:last-of-type {
            border-right: 1px solid #bbb;
        }

        .data-types .data-type.active {
            border: 1px solid #3BA9FF;
            background-color: #3BA9FF;
            color: #fff;
        }

        .data-choose {
            display: flex;
            align-items: center;
        }

        .data-choose input {
            width: 100px;
            height: 32px;
        }

        .data-choose .dc-span {
            width: 30px;
            text-align: center;
        }

        .d-flex-wrap {
            display: flex;
            flex-wrap: wrap;
        }

        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }

        .layui-layer.layui-layer-page {
            width: 98% !important;
            left: 1% !important;
            top: 20px !important;
        }

        .layui-table-body {
            overflow-y: overlay;
        }

        #dialogId {
            z-index: 198910170;
            position: fixed;
        }

        .layui-icon-spread-left {
            color: #3BA9FF;
        }

        .d_type_a {
            color: #FF3D00;
        }

        .d_type_b {
            color: #FB8C00;
        }

        .layui-layer-min,
        .layui-layer-max {
            display: none !important
        }

        .color1 {
            color: #FF3D00;
        }

        .color2 {
            color: #64DD17;
        }

        .nowrap-e {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        thead th {
            color: #000;
        }

        table thead tr:first-of-type th:nth-of-type(3n){
            border-right-color:#aeaeae;
        }
        table thead tr:first-of-type th:nth-of-type(4n){
            border-right-color:#aeaeae;
        }
        table thead tr:first-of-type th:nth-of-type(5n){
            border-right-color:#aeaeae;
        }
        table thead tr:nth-of-type(2n) th:nth-of-type(6n){
            border-right-color:#aeaeae;
        }

        .layui-table-hover{
            background-color: rgba(60, 169, 255, 0.13) !important;
        }

        .layui-table-total tr td{
            font-weight:800!important;
            color: #333!important;
        }
        .layui-table-tool{
            height: 40px;
            min-height: 40px;
            padding:4px 0;
        }
        .layui-table-tool .layui-table-tool-temp{
            float: right;
            padding-right: 60px;
        }
        .layui-table-tool .layui-table-tool-temp .layui-btn{
            height: 28px;
            line-height: 28px;
        }
        .theme-color {
            color: #3BA9FF;
        }
        .icon-about{
            display: inline-block;
            width: 18px;
            height: 18px;
            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAFD0lEQVR4Xu1bPXLbRhT+HjRDOUkR+QSWTxC6c8Ai1AkiNSZdST6B5ROEOoHlE0iuTLgRfQIpBRF3pk5g+QRBCmpszxAvswjkAVcA9hcIxyJa7r6fb9//Lgl3/KM7rj/WAKwt4I4jsHaBNg3g8Rvepg08IEZf5suMBAFmvMCn90/pqi25GrWA7hlv/fAF+0ToEzKltzQVSxi4IGAy7+DdbI8SzX3GyxoB4PEb7gcBnhOwayxRyQYGJmmKV++f0oUPekUaXgHIFX9JQNe3oBk9xsWCceQTCC8ACN8OAgjFvZy4CjxmnF5v4oUP13AG4NeIdwPgxMC/Vfrp/p4sUuy5WoMTAL2IXwI41JW4iXVMeBY/oVNb2tYAhGM+IcKBLWOf+4RLxEN6ZkPTCoBVUv5GaVsQjAEIx3xMhOc2aDe+h/FiOqRjEz5GAOQB78yEQdtrFyl2TAKjNgAi1W0E+OAr2jPwD4CZBFCXgJ8dQUvmHTzUTZHaAPTGfA66XcNbCPuaCcfxE5KVz0iFb7lLnGWWfQvaeb2ESTygPZ39WgD4MH1x4mmKXV3zzKvKia1F6LqCFgDhmD8SYVsH0bI1mbkT+lWnXkU3twbhduYf42I6pB3VRiUA4Vs+IM4qPesvBfb+GtCkSKAX8T4Du8R5h0iYzTs4kn03jHhEwB82zHWsQA1AxB9cmhsG/owHtNT/19QRCRN2ipaSB9+PNgAw4108pNr+pBYAF+bfBJZys9KiSky3N+YZCL/YgDDv4H5dRqgFoDfmQxBEvW/9MeFR8UTDMU+I8HsdwemAluQKIxbDkd+shFAUR7UAODHOpZWV6UX8t6qWWKR4WByLucihcoN6C4iYrVAvbEql2iFgKKc6Xi0ASKYDul+lRyUAIg9vBDh3BcB0PzNexUNaarF1rKaOj2xRxbWVACiDlalmOusZl/NN9ItBy0cRVpcOqwFwyL86ut5aU6K8WBM6pmFBg4GjeECjMrlWA4Aq5T0NXewAaKvvL1Fe3Cf8+BUnvoasKwsAMz5db6Jb9Plc+XOX6lM2dTsAWogBcnBqQvmVjQHi9OMhLXWYYcRnvsy+aAV2FuChC6zLBnK+99J3VDC0SoNNF0Lyqbi0vaq0K/cjWoWQ8MefvkLU7Y18bQEghjHxgCpvpVXdoHUb2ghqFkSdmqGVvgPQBcOpHf5vQms3k1MJyLhMCSNOkVCALWKIC5cHqm2mv9c1QoKWciTmMo2pElb45XUH28UCyGkAWsGobBwnL1UC0ERXWOWXvsHWuTlWApB1ZGO+8mmeVSfjOn5fKn5KCi2jbrC4uAkrkIsTH31/UeayUbw1AHlfbj+YrI5cxwwkYGz7fGug4/s3Imm5QAZAkxnBNLTXrM+v4Lq6bw21ARA8fYzJPepaSkon8GmVwlWC9iIW73Gsb24bBuD1dEBGz3aMLOBGeJ3LjYYVLSNvrLxWIVTGKR9ciKtru9sa/+hYKW8NwI38K+IO1so7A/B/Bsb8zcGhyxtBLwB8S5EpTm1vcE09QuR5EITypc9sTOhZBcEqBqJiRIqRz7JZLm8RYOR66k5pUAfdrHROIa7Wre70b/FgXHKQPayyfhJbJbdXC5CZiOoRKQ6yP0uYgiGUpux/Aqe6VZ3O4chrGgWgyEykznuf0d0gdJnyd0Gc/6+A8veChKt0gavP9zDTfedno3TjLuAqVJv7W7OANpUy4bUGwASt73Ht2gK+x1M10elf2OdYX4SLc6gAAAAASUVORK5CYII=);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }
        .layui-table-header tr:last-of-type .layui-table-cell{
            display: flex;
            align-items: center;
        }

    </style>
</head>

<body>
<div class="main">
    <div class="layui-form searchs" lay-filter="search">
        <input type="hidden" value='${params.franchiseesJson}'  id="franchiseesJson" />
        <input type="hidden" value='${params.investigatorsJson}' id="investigatorsJson" />
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">调查机构</label>
                <div class="layui-input-inline">
                    <div id="surveyOrgId" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <div class="d-flex-wrap">
                    <label class="layui-form-label">时间</label>
                    <div class="d-flex-wrap">
                        <div class="data-choose">
                            <input type="text" class="layui-input paramTime" readonly id="startTime"
                                   placeholder="请选择日期">
                            <span class="dc-span">至</span>
                            <input type="text" class="layui-input paramTime" readonly id="endTime"
                                   placeholder="请选择日期">
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 20px">
                <button lay-submit class="ll-submit layui-btn layui-btn-normal layui-btn-sm layui-btn-radius" lay-filter="submit"
                        style="width: 86px">查询 <i class="layui-icon layui-icon-search"></i></button>
                <button class="ll-submit layui-btn layui-btn-normal layui-btn-sm layui-btn-radius layui-export"
                        style="width: 86px">导出 <i class="layui-icon layui-icon-export"></i></button>
            </div>
        </div>
    </div>
    <div class="table-content">
        <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg" style="margin: 0">
        </table>
    </div>
</div>

<script type="text/html" id="table-content-child-h">
    <div class="table-content-child">
        <table class="layui-table" id="test-child" lay-filter="test-child" lay-skin="line" lay-size="lg" style="margin: 0">
        </table>
    </div>
</script>
<script type="text/html" id="surveyOrgName">
    {{#  if(d.orgType ==1 && d.orgLevel == 1){ }}
    <div class="nowrap-e" title="【A类】{{d.surveyOrgName}}"><span
            class="d_type d_type_a">【A类】</span>{{d.surveyOrgName}}</div>
    {{#  } else if(d.orgType ==0 && d.orgLevel == 1){ }}
    <div class="nowrap-e" title="【B类】{{d.surveyOrgName}}"><span
            class="d_type d_type_b">【B类】</span>{{d.surveyOrgName}}</div>
    {{#  } else { }}
    <div class="nowrap-e" title="{{d.surveyOrgName}}">{{d.surveyOrgName}}</div>
    {{#  } }}
</script>
<script src="${ctx}/js/jquery.min.js" charset="utf-8"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        soulTable: 'soulTable',
        xmSelect: 'xm-select'
    })
    var _height = $(document).height() * 0.9

    var first = '',
        second = ''

    vals = ['', '']

    function getYMD(date) {
        var today = new Date(date);
        return {
            'y': today.getFullYear(),
            'm': today.getMonth() + 1,
            'd': today.getDate(),
        }
    }

    function setDate(id) {
        var today = new Date(),
            y = today.getFullYear(),
            m = today.getMonth() + 1,
            d = today.getDate(),
            w = today.getDay(),
            millisecond = 1000 * 60 * 60 * 24;
        if (id == 'upMonth') {
            var y1 = y,
                m1 = m
            if (m == 1) {
                y1 = y - 1
                m1 = 12
            } else {
                m1 = m - 1
            }
            m1 = PrefixInteger(m1, 2)
            var days1 = new Date(y1, m1, 0).getDate()
            vals = [y1 + '-' + m1 + '-01', y1 + '-' + m1 + '-' + days1]
        } else if (id == 'yesterday') {
            var yesterDay = new Date(today.getTime() - millisecond);
            vals = [dateFormat(yesterDay), dateFormat(yesterDay)]
        } else if (id == 'today') {
            vals = [y + '-' + m + '-' + d, y + '-' + m + '-' + d]
        } else if (id == 'curWeek') {
            var minusDay = w != 0 ? w - 1 : 6;
            var monday = new Date(today.getTime() - (minusDay * millisecond));
            var sunday = new Date(monday.getTime() + (6 * millisecond));
            vals = [dateFormat(monday), y + '-' + m + '-' + d]
        } else if (id == 'curMonth') {
            vals = [y + '-' + m + '-01', y + '-' + m + '-' + d]
        } else if (id == 'all') {
            vals = ['2019-02-27', y + '-' + m + '-' + d]
        } else if (id == '7days') {
            var days7 = new Date(today.getTime() - millisecond * 7);
            var yesterDay = new Date(today.getTime() - millisecond);
            vals = [dateFormat(days7), dateFormat(yesterDay)]
        } else if (id == 'curQuarter') {
            var startm = 1
            if (m > 3 && m <= 6) {
                startm = 4
            } else if (m > 6 && m <= 9) {
                startm = 7
            } else if (9 < m) {
                startm = 10
            }
            vals = [y + '-' + startm + '-01', y + '-' + m + '-' + d]
        } else if (id == 'curYear') {
            vals = [y + '-01-01', y + '-' + m + '-' + d]
        } else if (id == '30days') {
            var days30 = new Date(today.getTime() - millisecond * 30);
            var yesterDay = new Date(today.getTime() - millisecond);
            vals = [dateFormat(days30), dateFormat(yesterDay)]
        }
        return vals
    }

    function dateFormat(time, format) {
        var t = new Date(time);
        var format = format || 'yyyy-MM-dd'
        var tf = function (i) {
            return (i < 10 ? '0' : '') + i
        };
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
            switch (a) {
                case 'yyyy':
                    return tf(t.getFullYear());
                    break;
                case 'MM':
                    return tf(t.getMonth() + 1);
                    break;
                case 'mm':
                    return tf(t.getMinutes());
                    break;
                case 'dd':
                    return tf(t.getDate());
                    break;
                case 'HH':
                    return tf(t.getHours());
                    break;
                case 'ss':
                    return tf(t.getSeconds());
                    break;
            }
        })
    };

    function PrefixInteger(num, m) {
        return (Array(m).join(0) + num).slice(-m);
    }

    var startTime = '',
        endTime = ''
    var franchiseesJson = $("#franchiseesJson").val();
    franchiseesJson = JSON.parse(franchiseesJson);
    layui.use(['form', 'table', 'soulTable', 'xmSelect', 'laydate'], function () {
        var soulTable = layui.soulTable,
            table = layui.table,
            form = layui.form,
            xmSelect = layui.xmSelect,
            laydate = layui.laydate;

        var demo2 = xmSelect.render({
            el: '#surveyOrgId',
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
                                        '<div class="xm-label-block lf-select-block">'+cur.name+'</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })

        filterJson(demo2, franchiseesJson,'id', 'name', false, false)
        function filterJson(demo, newJson, id, name, flag, selected) {
            var demoList = [],
                demoValues = []
            newJson.map(function (cur) {
                var _name = name ? cur[name] : cur.name
                var _id = id ? cur[id] : cur.id
                var param = {
                    name: _name,
                    value: _id,
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

        var initVals = setDate('30days')
        startTime = laydate.render({
            elem: '#startTime',
            value: initVals[0],
            max: initVals[0],
            min: initVals[0],
            isInitValue: true,
            showBottom: false
        });
        endTime = laydate.render({
            elem: '#endTime',
            value: initVals[1],
            min: initVals[1],
            max: initVals[1],
            isInitValue: true,
            showBottom: false
        });

        form.on('submit(submit)', function (data) {
            var vals = data.field
            delete vals.select;
            Object.assign(vals, {
                menuCode: "surveyManpowerBs",
                searchType : "last30Days",
                surveyOrgIds: demo2.getValue('valueStr'),
                startTime: $('#startTime').val(),
                endTime: $('#endTime').val(),

            })
            reloadTable(_cols, vals)
        });

        //重载表格数据
        function reloadTable(_cols, _data) {
            $('.table-content').empty()
            $('.table-content').append(
                ' <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg" style="margin: 0"></table>'
            )
            setTable(_cols, _data)
        }

        function getOfSigned(d, plus) {
            var _d = Math.round(d * 10000) / 100
            var _html = '<div class="">' + _d + '%</div>'
            if (plus) {
                return _html
            }
            if (d > 0) {
                _html = '<div class="color1">+' + _d + '%</div>'
            } else if (d < 0) {
                _html = '<div class="color2">' + _d + '%</div>'

            }
            return _html

        }
        var paramInitChild ={
            menuCode: "surveyManpowerBs-son",
            searchType : "last30Days"
        }
        var _cols1 = [
            [{
                title: '',
                show: 2,
                width: 60,
                lazy: true,
                owspan: 2,
                rowspan: 2,
                fixed: true,
                children: function (row) {
                    if (row.orgType == 1) {
                        return [{
                            title: function (row) {
                                first = row.surveyOrgName
                                return row.surveyOrgName + ' - 片区机构列表'
                            },
                            drag: false,
                            url: '${ctx}/survey/hzReport/getDetail',
                            where: {
                                menuCode: "surveyManpowerBs-son",
                                searchType : "last30Days",
                                orgId : row.surveyOrgId
                            },
                            even: true,
                            parseData: function(res){
                                return  {
                                    "code": res.isSuccess ? 0 : 1,
                                    "msg": res.msg,
                                    "count": res.count,
                                    "data": res.results.list
                                }
                            },
                            initSort: {
                                field: 'userNum1',
                                type: 'desc'
                            },
                            overflow: {
                                type: 'tips',
                                hoverTime: 90,
                                color: '#333',
                                bgColor: '#fff',
                                minWidth: 90,
                                maxWidth: 500,
                            },
                            page: false,
                            limit: 200,
                            height: _height * 0.96,
                            cols: [
                                [{
                                    title: '',
                                    show: 2,
                                    width: 60,
                                    lazy: true,
                                    owspan: 2,
                                    rowspan: 2,
                                    fixed:true,
                                    children: function (row) {
                                        return [{
                                            title: function (row) {
                                                second = row.surveyOrgName
                                                return first +
                                                    ' - ' +
                                                    row.surveyOrgName +
                                                    ' - 调查员列表'
                                            },
                                            drag: false,
                                            url: '${ctx}/survey/hzReport/getDetail',
                                            where: {
                                                menuCode: "surveyManpowerBs-son-item",
                                                searchType : "last30Days",
                                                orgId : row.surveyOrgId,
                                                son : row.orgLevel == 2 ? 2 : 3
                                            },
                                            even: true,
                                            parseData: function(res){
                                                return  {
                                                    "code": res.isSuccess ? 0 : 1,
                                                    "msg": res.msg,
                                                    "count": res.count,
                                                    "data": res.results.list
                                                }
                                            },
                                            initSort: {
                                                field: 'score',
                                                type: 'desc'
                                            },
                                            overflow: {
                                                type: 'tips',
                                                hoverTime: 90,
                                                color: '#333',
                                                bgColor: '#fff',
                                                minWidth: 90,
                                                maxWidth: 500,
                                            },
                                            page: false,
                                            limit: 200,
                                            height: _height * 0.96,
                                            cols: [[{
                                                field: 'surveyUserName',
                                                minWidth: 90,
                                                title: '调查员',
                                            }, {
                                                field: 'sonOrgName',
                                                minWidth: 220,
                                                title: '机构',
                                            }, {
                                                field: 'surveyTypeName',
                                                minWidth: 90,
                                                title: '调查员分类',
                                                style: 'color: #3BA9FF;cursor: pointer;',
                                                sort: true,
                                            }, {
                                                field: 'score',
                                                minWidth: 90,
                                                title: '积分',
                                                sort: true,
                                            }, {
                                                field: 'scoreRate',
                                                minWidth: 120,
                                                title: '环比',
                                                sort: true,
                                                templet: function (d) {
                                                    var _d = Math.round(d.scoreRate * 10000) / 100
                                                    var _html = '<div class="">' + _d + '%</div>'
                                                    if (_d > 0) {
                                                        _html = '<div class="color1">+' + _d + '%</div>'
                                                    } else if (_d < 0) {
                                                        _html = '<div class="color2">' + _d + '%</div>'

                                                    }
                                                    return _html
                                                }
                                            }]],
                                            done: function () {
                                                soulTable.render(this);
                                                showTitle('.layui-layer-page:last .layui-layer-content .layui-tab-content .layui-table-view .layui-table-box .layui-table-header:first table thead tr th', true, true)
                                            },
                                            toolEvent: function (obj, pobj) {
                                                onRowEvent(obj,pobj)
                                            },
                                            defaultToolbar:[],
                                            toolbar: '<div><a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="export">导出</a>',
                                            toolbarEvent: function (obj, pobj) {
                                                // obj 子表当前行对象
                                                // pobj 父表当前行对象
                                                onRowEventExport(obj, pobj, first +'-'+second+'-调查员')
                                            },
                                        }]
                                    }
                                },
                                {
                                    field: 'surveyOrgName',
                                    minWidth: 220,
                                    title: '调查机构',
                                    rowspan: 2,
                                    templet: '#surveyOrgName',
                                    fixed: true
                                },
                                {
                                    title: '在编调查员',
                                    colspan: 6,
                                    align: 'center'
                                },
                                {
                                    title: '活动调查员',
                                    colspan: 6,
                                    align: 'center'
                                },
                                {
                                    title: '正式调查员',
                                    colspan: 6,
                                    align: 'center'
                                }, {
                                    title: '试用调查员',
                                    colspan: 6,
                                    align: 'center'
                                },  {
                                    title: '沉默调查员',
                                    colspan: 6,
                                    align: 'center'
                                }
                                ],
                                [
                                    {
                                        field: 'userNum1',
                                        minWidth: 90,
                                        title: '人数',
                                        sort: true,
                                        totalRow: true,
                                        style: 'color: #3BA9FF;cursor: pointer;',
                                        event: 'getList_1'
                                    }, {
                                    field: 'num1Rate',
                                    minWidth: 120,
                                    title: '环比',
                                    sort: true,
                                    totalRow: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.num1Rate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                },{
                                    field: 'userCaseNum1',
                                    minWidth: 140,
                                    title: '人均案件数',
                                    sort: true,
                                    totalRow: true
                                }, {
                                    field: 'userCaseNum1Rate',
                                    minWidth: 120,
                                    title: '环比',
                                    sort: true,
                                    totalRow: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.userCaseNum1Rate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                }, {
                                    field: 'userScore1',
                                    minWidth: 120,
                                    title: '人均积分',
                                    sort: true,
                                    totalRow: true
                                }, {
                                    field: 'score1Rate',
                                    minWidth: 120,
                                    title: '环比',style: 'border-right-color:#aeaeae;',
                                    sort: true,
                                    totalRow: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.score1Rate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                },{
                                    field: 'userNum2',
                                    minWidth: 90,
                                    title: '人数',
                                    sort: true,
                                    totalRow: true,
                                    style: 'color: #3BA9FF;cursor: pointer;',
                                    event: 'getList_2'
                                }, {
                                    field: 'num2Rate',
                                    minWidth: 120,
                                    title: '环比',
                                    sort: true,
                                    totalRow: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.num2Rate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                },{
                                    field: 'userCaseNum2',
                                    minWidth: 140,
                                    title: '人均案件数',
                                    sort: true,
                                    totalRow: true
                                }, {
                                    field: 'userCaseNum2Rate',
                                    minWidth: 120,
                                    title: '环比',
                                    sort: true,
                                    totalRow: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.userCaseNum2Rate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                }, {
                                    field: 'userScore2',
                                    minWidth: 120,
                                    title: '人均积分',
                                    sort: true,
                                    totalRow: true,
                                }, {
                                    field: 'score2Rate',
                                    minWidth: 120,
                                    title: '环比',style: 'border-right-color:#aeaeae;',
                                    sort: true,
                                    totalRow: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.score2Rate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                },
                                    {
                                        field: 'userNum3',
                                        minWidth: 90,
                                        title: '人数',
                                        sort: true,
                                        totalRow: true,
                                        style: 'color: #3BA9FF;cursor: pointer;',
                                        event: 'getList_3'
                                    }, {
                                    field: 'num3Rate',
                                    minWidth: 120,
                                    title: '环比',
                                    sort: true,
                                    totalRow: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.num3Rate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                },{
                                    field: 'userCaseNum3',
                                    minWidth: 140,
                                    title: '人均案件数',
                                    sort: true,
                                    totalRow: true
                                }, {
                                    field: 'userCaseNum3Rate',
                                    minWidth: 120,
                                    title: '环比',
                                    sort: true,
                                    totalRow: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.userCaseNum3Rate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                }, {
                                    field: 'userScore3',
                                    minWidth: 120,
                                    title: '人均积分',
                                    sort: true,
                                }, {
                                    field: 'score3Rate',
                                    minWidth: 120,
                                    title: '环比',style: 'border-right-color:#aeaeae;',
                                    sort: true,
                                    totalRow: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.score3Rate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                }, {
                                    field: 'userNum4',
                                    minWidth: 90,
                                    title: '人数',
                                    sort: true,
                                    totalRow: true,
                                    style: 'color: #3BA9FF;cursor: pointer;',
                                    event: 'getList_4'
                                }, {
                                    field: 'num4Rate',
                                    minWidth: 120,
                                    title: '环比',
                                    sort: true,
                                    totalRow: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.num4Rate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                },{
                                    field: 'userCaseNum4',
                                    minWidth: 140,
                                    title: '人均案件数',
                                    sort: true,
                                    totalRow: true
                                }, {
                                    field: 'userCaseNum4Rate',
                                    minWidth: 120,
                                    title: '环比',
                                    sort: true,
                                    totalRow: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.userCaseNum4Rate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                }, {
                                    field: 'userScore4',
                                    minWidth: 120,
                                    title: '人均积分',
                                    sort: true,
                                    totalRow: true,
                                }, {
                                    field: 'score4Rate',
                                    minWidth: 120,
                                    title: '环比',style: 'border-right-color:#aeaeae;',
                                    sort: true,
                                    totalRow: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.score4Rate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                },
                                    {
                                        field: 'userNum5',
                                        minWidth: 90,
                                        title: '人数',
                                        sort: true,
                                        totalRow: true,
                                        style: 'color: #3BA9FF;cursor: pointer;',
                                        event: 'getList_5'
                                    }, {
                                    field: 'num5Rate',
                                    minWidth: 120,
                                    title: '环比',
                                    sort: true,
                                    totalRow: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.num5Rate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                },{
                                    field: 'userCaseNum5',
                                    minWidth: 140,
                                    title: '人均案件数',
                                    sort: true,
                                    totalRow: true
                                }, {
                                    field: 'userCaseNum5Rate',
                                    minWidth: 120,
                                    title: '环比',
                                    sort: true,
                                    totalRow: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.userCaseNum5Rate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                }, {
                                    field: 'userScore5',
                                    minWidth: 120,
                                    title: '人均积分',
                                    sort: true,
                                    totalRow: true,
                                }, {
                                    field: 'score5Rate',
                                    minWidth: 120,
                                    title: '环比',style: 'border-right-color:#aeaeae;',
                                    sort: true,
                                    totalRow: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.score5Rate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                },
                                ]
                            ],
                            done: function () {
                                soulTable.render(this);
                                showTitle('.layui-layer-page .layui-layer-content .layui-tab-content .layui-table-view .layui-table-box .layui-table-header:first table thead tr:last th')
                            },
                            toolEvent: function (obj, pobj) {
                                onRowEvent(obj, pobj)
                            },
                            defaultToolbar:[],
                            toolbar: '<div><a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="export">导出</a>',
                            toolbarEvent: function (obj, pobj) {
                                // obj 子表当前行对象
                                // pobj 父表当前行对象
                                onRowEventExport(obj, pobj, first +'-片区机构')
                            },
                        }]

                    } else if (row.orgType == 0) {
                        return [{
                            title: function (row) {
                                first = row.surveyOrgName
                                return row.surveyOrgName + ' - 调查员列表'
                            },
                            drag: false,
                            url: '${ctx}/survey/hzReport/getDetail',
                            where: {
                                menuCode: "surveyManpowerBs-son-item",
                                searchType : "last30Days",
                                orgId : row.surveyOrgId,
                                son : row.orgType
                            },
                            even: true,
                            parseData: function(res){
                                return  {
                                    "code": res.isSuccess ? 0 : 1,
                                    "msg": res.msg,
                                    "count": res.count,
                                    "data": res.results.list
                                }
                            },
                            initSort: {
                                field: 'score',
                                type: 'desc'
                            },
                            overflow: {
                                type: 'tips',
                                hoverTime: 90,
                                color: '#333',
                                bgColor: '#fff',
                                minWidth: 90,
                                maxWidth: 500,
                            },
                            page: false,
                            limit: 200,
                            height: _height * 0.96,
                            cols: [
                                [{
                                    field: 'surveyUserName',
                                    minWidth: 90,
                                    title: '调查员',
                                },{
                                    field: 'surveyTypeName',
                                    minWidth: 90,
                                    title: '调查员分类',
                                    style: 'color: #3BA9FF;cursor: pointer;',
                                    sort: true,
                                }, {
                                    field: 'score',
                                    minWidth: 90,
                                    title: '积分',
                                    sort: true,
                                }, {
                                    field: 'scoreRate',
                                    minWidth: 120,
                                    title: '环比',
                                    sort: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.scoreRate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                }]
                            ],
                            done: function () {
                                soulTable.render(this);
                                showTitle('.layui-layer-page .layui-layer-content .layui-tab-content .layui-table-view .layui-table-box .layui-table-header:first table thead tr th', true)

                            },
                            toolEvent: function (obj, pobj) {
                                onRowEvent(obj, pobj)
                            },
                            defaultToolbar:[],
                            toolbar: '<div><a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="export">导出</a>',
                            toolbarEvent: function (obj, pobj) {
                                // obj 子表当前行对象
                                // pobj 父表当前行对象
                                onRowEventExport(obj, pobj, first +'-调查员')
                            },
                        }]
                    }
                }
            },
                {
                    field: 'surveyOrgName',
                    minWidth: 220,
                    title: '调查机构',
                    rowspan: 2,
                    templet: '#surveyOrgName',
                    fixed:true,
                    totalRowText: '合计'
                },
                {
                    title: '在编调查员',
                    colspan: 6,
                    align: 'center'
                },
                {
                    title: '活动调查员',
                    colspan: 6,
                    align: 'center'
                },
                {
                    title: '正式调查员',
                    colspan: 6,
                    align: 'center'
                }, {
                    title: '试用调查员',
                    colspan: 6,
                    align: 'center'
                },  {
                    title: '沉默调查员',
                    colspan: 6,
                    align: 'center'
                }
            ],
            [
                {
                field: 'userNum1',
                minWidth: 90,
                title: '人数',
                sort: true,
                totalRow: true,
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'getList_1'
            }, {
                field: 'num1Rate',
                minWidth: 120,
                title: '环比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.num1Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            }, {
                field: 'userCaseNum1',
                minWidth: 140,
                title: '人均案件数',
                sort: true,
                totalRow: true
            }, {
                field: 'userCaseNum1Rate',
                minWidth: 120,
                title: '环比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.userCaseNum1Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            }, {
                field: 'userScore1',
                minWidth: 120,
                title: '人均积分',
                sort: true,
                totalRow: true
            }, {
                field: 'score1Rate',
                minWidth: 120,
                title: '环比',style: 'border-right-color:#aeaeae;',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.score1Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            },{
                field: 'userNum2',
                minWidth: 90,
                title: '人数',
                sort: true,
                totalRow: true,
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'getList_2'
            }, {
                field: 'num2Rate',
                minWidth: 120,
                title: '环比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.num2Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            },{
                field: 'userCaseNum2',
                minWidth: 140,
                title: '人均案件数',
                sort: true,
                totalRow: true
            }, {
                field: 'userCaseNum2Rate',
                minWidth: 120,
                title: '环比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.userCaseNum2Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            }, {
                field: 'userScore2',
                minWidth: 120,
                title: '人均积分',
                sort: true,
                totalRow: true,
            }, {
                field: 'score2Rate',
                minWidth: 120,
                title: '环比',style: 'border-right-color:#aeaeae;',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.score2Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            },
            {
                field: 'userNum3',
                minWidth: 90,
                title: '人数',
                sort: true,
                totalRow: true,
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'getList_3'
            }, {
                field: 'num3Rate',
                minWidth: 120,
                title: '环比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.num3Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            },{
                field: 'userCaseNum3',
                minWidth: 140,
                title: '人均案件数',
                sort: true,
                totalRow: true
            }, {
                field: 'userCaseNum3Rate',
                minWidth: 120,
                title: '环比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.userCaseNum3Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            }, {
                field: 'userScore3',
                minWidth: 120,
                title: '人均积分',
                sort: true,
            }, {
                field: 'score3Rate',
                minWidth: 120,
                title: '环比',style: 'border-right-color:#aeaeae;',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.score3Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            }, {
                field: 'userNum4',
                minWidth: 90,
                title: '人数',
                sort: true,
                totalRow: true,
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'getList_4'
            }, {
                field: 'num4Rate',
                minWidth: 120,
                title: '环比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.num4Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            },{
                field: 'userCaseNum4',
                minWidth: 140,
                title: '人均案件数',
                sort: true,
                totalRow: true
            }, {
                field: 'userCaseNum4Rate',
                minWidth: 120,
                title: '环比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.userCaseNum4Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            }, {
                field: 'userScore4',
                minWidth: 120,
                title: '人均积分',
                sort: true,
                totalRow: true,
            }, {
                field: 'score4Rate',
                minWidth: 120,
                title: '环比',style: 'border-right-color:#aeaeae;',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.score4Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            },
            {
                field: 'userNum5',
                minWidth: 90,
                title: '人数',
                sort: true,
                totalRow: true,
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'getList_5'
            }, {
                field: 'num5Rate',
                minWidth: 120,
                title: '环比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.num5Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            },{
                field: 'userCaseNum5',
                minWidth: 140,
                title: '人均案件数',
                sort: true,
                totalRow: true
            }, {
                field: 'userCaseNum5Rate',
                minWidth: 120,
                title: '环比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.userCaseNum5Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            }, {
                field: 'userScore5',
                minWidth: 120,
                title: '人均积分',
                sort: true,
                totalRow: true,
            }, {
                field: 'score5Rate',
                minWidth: 120,
                title: '环比',style: 'border-right-color:#aeaeae;',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.score5Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            },
            ]
        ]
        var _cols = _cols1
        var paramInit ={
            menuCode: "surveyManpowerBs",
            searchType : "last30Days"
        }
        setTable(_cols, paramInit)

        function setTable(_cols, param) {
            $('button.ll-submit').attr('disabled', true)
            setTimeout(function () {
                $('button.ll-submit').removeAttr('disabled')
            }, 6000)
            var _h = $('.searchs').outerHeight() + 40
            var fullH = 'full-' + _h
            var myTable = table.render({
                id: "test",
                elem: '#test',
                cols: _cols,
                page: false,
                limit: 10,
                height: fullH,
                drag: false,
                url: '${ctx}/survey/hzReport/getDetail',
                where: param,
                even: true,
                totalRow: true,
                parseData: function(res){
                    resultsTotal = {
                        "num1Rate": getRate(res.results.total.num1Rate) || '0%',
                        "num2Rate": getRate(res.results.total.num2Rate) || '0%',
                        "num3Rate": getRate(res.results.total.num3Rate) || '0%',
                        "num4Rate": getRate(res.results.total.num4Rate) || '0%',
                        "num5Rate": getRate(res.results.total.num5Rate) || '0%',

                        "score1Rate": getRate(res.results.total.score1Rate) || '0%',
                        "score2Rate": getRate(res.results.total.score2Rate) || '0%',
                        "score3Rate": getRate(res.results.total.score3Rate) || '0%',
                        "score4Rate": getRate(res.results.total.score4Rate) || '0%',
                        "score5Rate": getRate(res.results.total.score5Rate) || '0%',


                        "userCaseNum1Rate": getRate(res.results.total.userCaseNum1Rate) || '0%',
                        "userCaseNum2Rate": getRate(res.results.total.userCaseNum2Rate) || '0%',
                        "userCaseNum3Rate": getRate(res.results.total.userCaseNum3Rate) || '0%',
                        "userCaseNum4Rate": getRate(res.results.total.userCaseNum4Rate) || '0%',
                        "userCaseNum5Rate": getRate(res.results.total.userCaseNum5Rate) || '0%',


                    }
                    return {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results.list,
                        "totalRow": {
                            "userNum1": getFixed(res.results.total.userNum1) || '0',
                            "userNum2": getFixed(res.results.total.userNum2) || '0',
                            "userNum3": getFixed(res.results.total.userNum3) || '0',
                            "userNum4": getFixed(res.results.total.userNum4) || '0',
                            "userNum5": getFixed(res.results.total.userNum5) || '0',

                            "num1Rate": getRate(res.results.total.num1Rate) || '0%',
                            "num2Rate": getRate(res.results.total.num2Rate) || '0%',
                            "num3Rate": getRate(res.results.total.num3Rate) || '0%',
                            "num4Rate": getRate(res.results.total.num4Rate) || '0%',
                            "num5Rate": getRate(res.results.total.num5Rate) || '0%',

                            "userScore1": getFixed(res.results.total.userScore1) || '0',
                            "userScore2": getFixed(res.results.total.userScore2) || '0',
                            "userScore3": getFixed(res.results.total.userScore3) || '0',
                            "userScore4": getFixed(res.results.total.userScore4) || '0',
                            "userScore5": getFixed(res.results.total.userScore5) || '0',

                            "score1Rate": getRate(res.results.total.score1Rate) || '0%',
                            "score2Rate": getRate(res.results.total.score2Rate) || '0%',
                            "score3Rate": getRate(res.results.total.score3Rate) || '0%',
                            "score4Rate": getRate(res.results.total.score4Rate) || '0%',
                            "score5Rate": getRate(res.results.total.score5Rate) || '0%',


                            "userCaseNum1": getFixed(res.results.total.userCaseNum1) || '0',
                            "userCaseNum2": getFixed(res.results.total.userCaseNum2) || '0',
                            "userCaseNum3": getFixed(res.results.total.userCaseNum3) || '0',
                            "userCaseNum4": getFixed(res.results.total.userCaseNum4) || '0',
                            "userCaseNum5": getFixed(res.results.total.userCaseNum5) || '0',

                            "userCaseNum1Rate": getRate(res.results.total.userCaseNum1Rate) || '0%',
                            "userCaseNum2Rate": getRate(res.results.total.userCaseNum2Rate) || '0%',
                            "userCaseNum3Rate": getRate(res.results.total.userCaseNum3Rate) || '0%',
                            "userCaseNum4Rate": getRate(res.results.total.userCaseNum4Rate) || '0%',
                            "userCaseNum5Rate": getRate(res.results.total.userCaseNum5Rate) || '0%',

                        }
                    }
                },
                initSort: {
                    field: 'userNum1',
                    type: 'desc'
                },
                done: function () {
                    soulTable.render(this)
                    $('button.ll-submit').removeAttr('disabled')

                    var tt_tr = $('.layui-layer  .layui-table-header table thead tr')
                    if (tt_tr.eq(0).find('th').length < 6){
                        tt_tr.eq(0).find('th').css({
                            'border-right-color': '#e6e6e6'
                        })
                    }

                    showTitle('.table-content .layui-table-view .layui-table-box .layui-table-header:first table thead tr:last th')


                    if (resultsTotal.num1Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="num1Rate"] div').addClass('color2')
                    }else if (resultsTotal.num1Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="num1Rate"] div').addClass('color1')
                    }

                    if (resultsTotal.num2Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="num2Rate"] div').addClass('color2')
                    }else if (resultsTotal.num2Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="num2Rate"] div').addClass('color1')
                    }

                    if (resultsTotal.num3Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="num3Rate"] div').addClass('color2')
                    }else if (resultsTotal.num3Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="num3Rate"] div').addClass('color1')
                    }

                    if (resultsTotal.num4Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="num4Rate"] div').addClass('color2')
                    }else if (resultsTotal.num4Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="num4Rate"] div').addClass('color1')
                    }

                    if (resultsTotal.num5Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="num5Rate"] div').addClass('color2')
                    }else if (resultsTotal.num5Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="num5Rate"] div').addClass('color1')
                    }




                    if (resultsTotal.score1Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="score1Rate"] div').addClass('color2')
                    }else if (resultsTotal.score1Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="score1Rate"] div').addClass('color1')
                    }

                    if (resultsTotal.score2Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="score2Rate"] div').addClass('color2')
                    }else if (resultsTotal.score2Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="score2Rate"] div').addClass('color1')
                    }

                    if (resultsTotal.score3Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="score3Rate"] div').addClass('color2')
                    }else if (resultsTotal.score3Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="score3Rate"] div').addClass('color1')
                    }

                    if (resultsTotal.score4Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="score4Rate"] div').addClass('color2')
                    }else if (resultsTotal.score4Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="score4Rate"] div').addClass('color1')
                    }

                    if (resultsTotal.score5Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="score5Rate"] div').addClass('color2')
                    }else if (resultsTotal.score5Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="score5Rate"] div').addClass('color1')
                    }

                    if (resultsTotal.userCaseNum1Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="userCaseNum1Rate"] div').addClass('color2')
                    }else if (resultsTotal.userCaseNum1Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="userCaseNum1Rate"] div').addClass('color1')
                    }

                    if (resultsTotal.userCaseNum2Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="userCaseNum2Rate"] div').addClass('color2')
                    }else if (resultsTotal.userCaseNum2Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="userCaseNum2Rate"] div').addClass('color1')
                    }

                    if (resultsTotal.userCaseNum3Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="userCaseNum3Rate"] div').addClass('color2')
                    }else if (resultsTotal.userCaseNum3Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="userCaseNum3Rate"] div').addClass('color1')
                    }

                    if (resultsTotal.userCaseNum4Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="userCaseNum4Rate"] div').addClass('color2')
                    }else if (resultsTotal.userCaseNum4Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="userCaseNum4Rate"] div').addClass('color1')
                    }

                    if (resultsTotal.userCaseNum5Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="userCaseNum5Rate"] div').addClass('color2')
                    }else if (resultsTotal.userCaseNum5Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="userCaseNum5Rate"] div').addClass('color1')
                    }

                }
            })
            table.on('tool(test)', function (obj, pobj) {
                onRowEvent(obj, pobj)
            })

            $('.layui-export').off('click').on('click',function (e) {
                var _this = $('.layui-export')
                _this.attr('disabled',true)
                soulTable.export(myTable,{
                    filename: '调查员人力报表.xlsx'
                })
                setTimeout(function () {
                    _this.removeAttr('disabled')
                },5000)
            })
        }

        function getFixed(d) {
            var _d = Math.round(d * 100) / 100
            return _d
        }

        function getRate(d) {
            var _d = Math.round(d * 10000) / 100
            return _d + '%'
        }
        function onRowEventExport(obj, pobj,name) {
            if (obj.event === 'export') {
                soulTable.export(obj.config.id,{
                    filename: '调查员人力报表('+ name+').xlsx'
                })
            }
        }


        function onRowEvent(obj, pobj) {
            var type = obj.event.split('_')
            console.log(type, obj.data)
            var _width = $(document).width() * 0.98
            // $('.table-content-child').show()
            layer.open({
                type: 1,
                title: '调查员列表',
                area: [_width +'px', _height+ 'px'],
                content: $('#table-content-child-h').html(),
            });
            var param = {
                menuCode: "surveyManpowerBs-son-item",
                searchType : "last30Days",
                orgId : obj.data.surveyOrgId,
                // son : type[0] == 'getList0' ? 3 : obj.data.orgType,
                son : obj.data.orgLevel,
                colType : type[1]
            }

            getChild(param)


            var tt_tr = $('.layui-layer  .layui-table-header table thead tr')

            if (tt_tr.eq(0).find('th').length < 6){
                tt_tr.eq(0).find('th').css({
                    'border-right-color': '#e6e6e6'
                })
            }
        }
        setSortDesc()

        function setSortDesc(param,param2) {
            $('body').on('click', '.layui-table-col-special', function () {
                // var _this = $(this)
                // if (_this.parents('.main').length) {
                //     var _ll = $('.main').siblings('.layui-layer')
                //     _ll.find('th[data-field="' + param + '"] .layui-table-cell').click()
                //     _ll.find('th[data-field="' + param + '"] .layui-table-cell').click()
                // } else {
                //     var _ll = _this.parents('.layui-layer')
                //     if (_this.parents('.layui-layer').siblings('.layui-layer').length) {
                //         _ll = _this.parents('.layui-layer').siblings('.layui-layer')
                //     }
                //     _ll.find('th[data-field="' + param + '"] .layui-table-cell').click()
                //     _ll.find('th[data-field="' + param + '"] .layui-table-cell').click()
                // }
                var tt_tr = $('.layui-layer').last().find('.layui-table-header table thead tr')

                if (tt_tr.eq(0).find('th').length < 6){
                    tt_tr.eq(0).find('th').css({
                        'border-right-color': '#e6e6e6'
                    })
                }
            })


        }

        function getChild(param ){
            var _cols_child =  [
                [{
                    field: 'surveyUserName',
                    minWidth: 90,
                    title: '调查员',
                }, {
                    field: 'sonOrgName',
                    minWidth: 270,
                    title: '机构',
                }, {
                    field: 'surveyTypeName',
                    minWidth: 90,
                    title: '调查员分类',
                    style: 'color: #3BA9FF;cursor: pointer;',
                    sort: true,
                }, {
                    field: 'score',
                    minWidth: 90,
                    title: '积分',
                    sort: true,
                }, {
                    field: 'scoreRate',
                    minWidth: 120,
                    title: '环比',
                    sort: true,
                    templet: function (d) {
                        return getOfSigned(d.scoreRate)
                    }
                }]]
            if (param.son == 0){
                _cols_child =  [
                    [{
                        field: 'surveyUserName',
                        minWidth: 90,
                        title: '调查员',
                    },  {
                        field: 'surveyTypeName',
                        minWidth: 90,
                        title: '调查员分类',
                        style: 'color: #3BA9FF;cursor: pointer;',
                        sort: true,
                    }, {
                        field: 'score',
                        minWidth: 90,
                        title: '积分',
                        sort: true,
                    }, {
                        field: 'scoreRate',
                        minWidth: 90,
                        title: '环比',
                        sort: true,
                        templet: function (d) {
                            return getOfSigned(d.scoreRate)
                        }
                    }]]

            }
            table.render({
                id: "test-child",
                elem: '#test-child',
                cols: _cols_child,
                page: false,
                limit: 10000,
                height: 'full-50',
                drag: false,
                url: '${ctx}/survey/hzReport/getDetail',
                where: param,
                even: true,
                parseData: function(res){
                    console.log(res.results.list)
                    return  {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results.list
                    }
                },
                initSort: {
                    field: 'score',
                    type: 'desc'
                },
            })

        }

        function showTitle(thsJ, child,third ) {
            var icon_about = '<span class="icon-about">'
            var ths = $(thsJ)
            if (!child) {
                ths.find('.layui-table-cell span:first').after(icon_about)
                var _title1 = '当前做保司业务的机构内的全部调查员，包括合格调查员、有效调查员、调查新人、调查学员'
                ths.eq(0).find('.layui-table-cell').attr('title', _title1)

                var _title2 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数×100%'
                ths.eq(1).find('.layui-table-cell').attr('title', _title2)

                var _title3 = '最近30天（不包括当天），保司终审通过状态的全部调查员的平均案件数量'
                ths.eq(2).find('.layui-table-cell').attr('title', _title3)

                var _title4 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数×100%'
                ths.eq(3).find('.layui-table-cell').attr('title', _title4)

                var _title5 = '最近30天（不包括当天），保司终审通过状态的全部调查员的平均积分'
                ths.eq(4).find('.layui-table-cell').attr('title', _title5)

                var _title6 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数×100%'
                ths.eq(5).find('.layui-table-cell').attr('title', _title6)


                var _title7 = '最近30天（不包括当天），保司终审通过的调查积分大于0分的调查员的数量'
                ths.eq(6).find('.layui-table-cell').attr('title', _title7)

                var _title8 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数*100%'
                ths.eq(7).find('.layui-table-cell').attr('title', _title8)

                var _title9 = '最近30天（不包括当天），保司终审通过状态的全部活动调查员的平均案件数量'
                ths.eq(8).find('.layui-table-cell').attr('title', _title9)

                var _title10 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数*100%'
                ths.eq(9).find('.layui-table-cell').attr('title', _title10)

                var _title11 = '最近30天（不包括当天），保司终审通过状态的全部活动调查员的平均积分'
                ths.eq(10).find('.layui-table-cell').attr('title', _title11)

                var _title12 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数*100%'
                ths.eq(11).find('.layui-table-cell').attr('title', _title12)


                var _title13 = '最近30天（不包括当天），保司终审通过状态的调查积分大于等于70分的调查员的数量'
                ths.eq(12).find('.layui-table-cell').attr('title', _title13)

                var _title14 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数×100%'
                ths.eq(13).find('.layui-table-cell').attr('title', _title14)

                var _title15 = '最近30天（不包括当天），保司终审通过状态的全部合格调查员的平均案件数量'
                ths.eq(14).find('.layui-table-cell').attr('title', _title15)

                var _title16 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数×100%'
                ths.eq(15).find('.layui-table-cell').attr('title', _title16)

                var _title17 = '最近30天（不包括当天），保司终审通过状态的全部合格调查员的平均积分'
                ths.eq(16).find('.layui-table-cell').attr('title', _title17)

                var _title18 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数×100%'
                ths.eq(17).find('.layui-table-cell').attr('title', _title18)


                var _title19 = '最近30天（不包括当天），保司终审通过状态的调查积分大于0小于70的调查员的数量'
                ths.eq(18).find('.layui-table-cell').attr('title', _title19)

                var _title20 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数×100%'
                ths.eq(19).find('.layui-table-cell').attr('title', _title20)

                var _title21 = '最近30天（不包括当天），保司终审通过状态的全部有效调查员的平均案件数量'
                ths.eq(20).find('.layui-table-cell').attr('title', _title21)

                var _title22 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数×100%'
                ths.eq(21).find('.layui-table-cell').attr('title', _title22)

                var _title23 = '最近30天（不包括当天），保司终审通过状态的全部有效调查员的平均积分'
                ths.eq(22).find('.layui-table-cell').attr('title', _title23)

                var _title24 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数×100%'
                ths.eq(23).find('.layui-table-cell').attr('title', _title24)


                var _title25 = '最近30天（不包括当天），保司终审通过的调查积分等于0分的调查员的数量'
                ths.eq(24).find('.layui-table-cell').attr('title', _title25)

                var _title26 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数*100%'
                ths.eq(25).find('.layui-table-cell').attr('title', _title26)

                var _title27 = '最近30天（不包括当天），保司终审通过状态的全部沉默调查员的平均案件数量'
                ths.eq(26).find('.layui-table-cell').attr('title', _title27)

                var _title28 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数*100%'
                ths.eq(27).find('.layui-table-cell').attr('title', _title28)

                var _title29 = '最近30天（不包括当天），保司终审通过状态的全部沉默调查员的平均积分'
                ths.eq(28).find('.layui-table-cell').attr('title', _title29)

                var _title30 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数*100%'
                ths.eq(29).find('.layui-table-cell').attr('title', _title30)
            }else {
                if (third){
                    ths.eq(3).find('.layui-table-cell span:first').after(icon_about)
                    ths.eq(4).find('.layui-table-cell span:first').after(icon_about)

                    var _title1 = '最近30天（不包括当天），保司终审通过状态的该调查员的总积分'
                    ths.eq(3).find('.layui-table-cell').attr('title', _title1)

                    var _title2 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数×100%'
                    ths.eq(4).find('.layui-table-cell').attr('title', _title2)
                }else {
                    ths.eq(2).find('.layui-table-cell span:first').after(icon_about)
                    ths.eq(3).find('.layui-table-cell span:first').after(icon_about)

                    var _title1 = '最近30天（不包括当天），保司终审通过状态的该调查员的总积分'
                    ths.eq(2).find('.layui-table-cell').attr('title', _title1)

                    var _title2 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数×100%'
                    ths.eq(3).find('.layui-table-cell').attr('title', _title2)
                }

            }
        }

    })
</script>

</body>

</html>