<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>产品经营分析报表</title>
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
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
        .searchs *{
            font-size: 14px;
        }

        .layui-form-item {
            margin: 0 !important;
        }

        label.layui-form-label {
            width: auto;
            padding: 6px 15px;
            /*padding-left: 0;*/
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

        .layui-form-item .layui-inline{
            margin-bottom:3px!important;
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

        .layui-table-hover {
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
    <input type="hidden" value='${proThinksJson}' id="proThinksJson"/>
    <div class="layui-form searchs" lay-filter="search">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">产品类型</label>
                <div class="layui-input-inline">
                    <div id="pinkId" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">时间</label>
                <div class="d-flex-wrap">
                    <div class="d-flex-wrap">
                        <div class="data-types">
                            <div class="data-type active" data-id="upMonth">上月</div>
                            <div class="data-type" data-id="curMonth">当月</div>
                            <div class="data-type" data-id="curQuarter">本季度</div>
                            <div class="data-type" data-id="curYear">本年度</div>
                        </div>
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
            <div class="layui-inline">
                <label class="layui-form-label">工资条、绩效时间点</label>
                <div class="layui-input-inline">
                    <div id="timeType1" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">打卡、渠道费、报销时间点</label>
                <div class="layui-input-inline">
                    <div id="timeType2" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 20px;">
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

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
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

    var searchType = 'upMonth'
    vals = ['', '']

    $('.data-types').on('click', '.data-type', function () {
        var _this = $(this)
        var id = _this.attr('data-id')
        if (!_this.hasClass('active')) {
            searchType = id
            _this.addClass('active').siblings().removeClass('active')
            var vals = setDate(id)
            $('#startTime').val(vals[0])
            $('#endTime').val(vals[1])
            var _min = getYMD(vals[0])
            var _max = getYMD(vals[1])
            startTime.config.max = {
                year: _max.y,
                month: _max.m - 1,
            }
            endTime.config.min = {
                year: _min.y,
                month: _min.m - 1,
            }
        }
    })

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
            vals = [y1 + '-' + m1, y1 + '-' + m1]
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
            vals = [y + '-' + m, y + '-' + m ]
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
            vals = [y + '-' + startm, y + '-' + m ]
        } else if (id == 'curYear') {
            vals = [y + '-01', y + '-' + m]
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
    layui.use(['form', 'table', 'soulTable', 'xmSelect', 'laydate'], function () {
        var soulTable = layui.soulTable,
            table = layui.table,
            form = layui.form,
            xmSelect = layui.xmSelect,
            laydate = layui.laydate;

        var demo1 = xmSelect.render({
            el: '#pinkId',
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
        var demo2 = xmSelect.render({
            el: '#timeType1',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            clickClose: true,
            radio: true,
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
                            var _html = ''
                            sels.filter(function (cur) {
                                _html +=
                                    '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                            })
                            return _html
                        }
                    },
                }
            },
            data: [{
                name: '发生月份',
                value: 1,
                selected: true
            },{
                name: '审核通过月份',
                value: 2
            },{
                name: '支付月份',
                value: 3
            }]
        })
        var demo3 = xmSelect.render({
            el: '#timeType2',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            clickClose: true,
            radio: true,
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
                            var _html = ''
                            sels.filter(function (cur) {
                                _html +=
                                    '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                            })
                            return _html
                        }
                    },
                }
            },
            data: [{
                name: '发生月份',
                value: 1,
                selected: true
            },{
                name: '审核通过月份',
                value: 2
            },{
                name: '支付月份',
                value: 3
            }]
        })

        var proThinksJson = $("#proThinksJson").val();
        proThinksJson = JSON.parse(proThinksJson);
        filterJson(demo1,proThinksJson,'proId','proName',false,false)

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

        var initVals = setDate('upMonth')
        startTime = laydate.render({
            type: 'month',
            elem: '#startTime',
            value: initVals[0],
            max: initVals[1],
            isInitValue: true,
            done: function (value, date) {
                endTime.config.min = {
                    year: date.year,
                    month: date.month - 1,
                }
                searchType = 'date'
                $('.data-types .data-type').removeClass('active')
            }
        });
        endTime = laydate.render({
            type: 'month',
            elem: '#endTime',
            value: initVals[1],
            min: initVals[0],
            isInitValue: true,
            done: function (value, date) {
                if (value) {
                    startTime.config.max = {
                        year: date.year,
                        month: date.month,
                    }
                } else {
                    startTime.config.max = {
                        year: 2100,
                        month: 1,
                    }
                }
                searchType = 'date'
                $('.data-types .data-type').removeClass('active')
            }
        });

        var paramSubmit = {
            dataType: 'think-ywzy',
            proIds: '',
            searchType: searchType,
            startTime: initVals[0]+'-01',
            endTime: initVals[1]+'-01',
            staffType: '1',
            reType: '1',
        }

        form.on('submit(submit)', function (data) {
            Object.assign(paramSubmit, {
                proIds: demo1.getValue('valueStr'),
                searchType: searchType,
                startTime: $('#startTime').val()+'-01',
                endTime: $('#endTime').val()+'-01',
                staffType: demo2.getValue('valueStr'),
                reType: demo3.getValue('valueStr'),
            })
            reloadTable(_cols, paramSubmit)
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

        function getOfSignedNum(d) {
            return Math.round(d * 100) / 100

        }

        var _cols = [
            [
                {
                    field: 'proName',
                    minWidth: 200,
                    title: '产品类型',
                    rowspan: 2,
                    fixed:true,
                    totalRowText: '合计'
                },
                {
                    title: '收入',
                    colspan: 12,
                    align: 'center',
                },
                {
                    field: 'inMoney',
                    minWidth: 180,
                    title: '主营业务成本',
                    event: 'cost',
                    style: 'color: #3BA9FF;cursor: pointer;',
                    rowspan: 2,
                    totalRow: true,
                    templet: function (d) {
                        return Math.round(d.inMoney * 100) / 100
                    }
                },
                {
                    field: 'outMoney',
                    minWidth: 180,
                    title: '销售费用',
                    event: 'market',
                    style: 'color: #3BA9FF;cursor: pointer;',
                    rowspan: 2,
                    totalRow: true,
                    templet: function (d) {
                        return Math.round(d.outMoney * 100) / 100
                    }
                },
                {
                    field: 'cwMoney',
                    minWidth: 180,
                    title: '财务费用',
                    rowspan: 2,
                    totalRow: true,
                    templet: function (d) {
                        return Math.round(d.cwMoney * 100) / 100
                    }
                },
                {
                    field: 'zchj',
                    minWidth: 180,
                    title: '支出合计',
                    rowspan: 2,
                    totalRow: true,
                    templet: function (d) {
                        return Math.round((d.inMoney + d.outMoney +d.cwMoney) * 100) / 100
                    }
                },
                {
                    field: 'lrptfstg',
                    minWidth: 220,
                    title: '利润（平台复审通过）',
                    rowspan: 2,
                    totalRow: true,
                    templet: function (d) {
                        return Math.round((d.fsMoney -d.inMoney - d.outMoney -d.cwMoney) * 100) / 100
                    }
                },
                {
                    field: 'lrkp',
                    minWidth: 180,
                    title: '利润（开票）',
                    rowspan: 2,
                    totalRow: true,
                    templet: function (d) {
                        return Math.round((d.kpMoney -d.inMoney - d.outMoney -d.cwMoney) * 100) / 100

                    }
                },
                {
                    field: 'lrdz',
                    minWidth: 180,
                    title: '利润（到账）',
                    rowspan: 2,
                    totalRow: true,
                    templet: function (d) {
                        return Math.round((d.dzMoney -d.inMoney - d.outMoney -d.cwMoney) * 100) / 100

                    }
                },
            ],
            [
            {
                field: 'fsMoney',
                minWidth: 150,
                title: '平台复审通过收入',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.fsMoney * 100) / 100
                }
            }, {
                field: 'fsRateHb',
                minWidth: 180,
                title: '平台复审通过收入环比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.fsRateHb * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            }, {
                field: 'fsRateTb',
                minWidth: 180,
                title: '平台复审通过收入同比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.fsRateTb * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            },{
                field: 'zsMoney',
                minWidth: 150,
                title: '保司终审通过收入',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.zsMoney * 100) / 100
                }
            }, {
                field: 'zsRateHb',
                minWidth: 180,
                title: '保司终审通过收入环比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.zsRateHb * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            }, {
                field: 'zsRateTb',
                minWidth: 180,
                title: '保司终审通过收入同比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.zsRateTb * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            },{
                field: 'kpMoney',
                minWidth: 150,
                title: '开票收入',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.kpMoney * 100) / 100
                }
            }, {
                field: 'kpRateHb',
                minWidth: 180,
                title: '开票收入环比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.kpRateHb * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            }, {
                field: 'kpRateTb',
                minWidth: 180,
                title: '开票收入同比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.kpRateTb * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            },{
                field: 'dzMoney',
                minWidth: 150,
                title: '到账收入',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.dzMoney * 100) / 100
                }
            }, {
                field: 'dzRateHb',
                minWidth: 180,
                title: '到账收入环比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.dzRateHb * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            }, {
                field: 'dzRateTb',
                minWidth: 180,
                title: '到账收入同比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.dzRateTb * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            }
            ]
        ]


        setTable(_cols, paramSubmit)

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
                even: true, cols: _cols,
                page: false,
                totalRow: true,
                height: fullH,
                drag: false,
                url: '${ctx}/survey/report/getData',
                where: param,
                parseData: function (res) {
                    resultsTotal = {
                        "fsRateHb": getRate(res.results.total.fsRateHb),
                        "fsRateTb": getRate(res.results.total.fsRateHb),
                        "zsRateHb": getRate(res.results.total.zsRateHb),
                        "zsRateTb": getRate(res.results.total.zsRateHb),
                        "kpRateHb": getRate(res.results.total.kpRateHb),
                        "kpRateTb": getRate(res.results.total.kpRateHb),
                        "dzRateHb": getRate(res.results.total.dzRateHb),
                        "dzRateTb": getRate(res.results.total.dzRateHb),
                    }
                    return {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results.list,
                        "totalRow": {
                            "fsMoney": getFixed(res.results.total.fsMoney),
                            "fsRateHb": getRate(res.results.total.fsRateHb),
                            "fsRateTb": getRate(res.results.total.fsRateHb),
                            "zsMoney": getFixed(res.results.total.zsMoney),
                            "zsRateHb": getRate(res.results.total.zsRateHb),
                            "zsRateTb": getRate(res.results.total.zsRateHb),
                            "kpMoney": getFixed(res.results.total.kpMoney),
                            "kpRateHb": getRate(res.results.total.kpRateHb),
                            "kpRateTb": getRate(res.results.total.kpRateHb),
                            "dzMoney": getFixed(res.results.total.dzMoney),
                            "dzRateHb": getRate(res.results.total.dzRateHb),
                            "dzRateTb": getRate(res.results.total.dzRateHb),
                            "inMoney": getFixed(res.results.total.inMoney),
                            "outMoney": getFixed(res.results.total.outMoney),
                            "cwMoney": getFixed(res.results.total.cwMoney),
                            "zchj": getFixed(res.results.total.inMoney + res.results.total.outMoney +res.results.total.cwMoney),
                            "lrptfstg": getFixed(res.results.total.fsMoney -res.results.total.inMoney - res.results.total.outMoney -res.results.total.cwMoney),
                            "lrkp": getFixed(res.results.total.kpMoney -res.results.total.inMoney - res.results.total.outMoney -res.results.total.cwMoney),
                            "lrdz": getFixed(res.results.total.dzMoney -res.results.total.inMoney - res.results.total.outMoney -res.results.total.cwMoney),
                        }
                    }
                },
                done: function () {
                    soulTable.render(this)
                    $('button.ll-submit').removeAttr('disabled')

                    if (resultsTotal.fsRateHb.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="fsRateHb"] div').addClass('color2')
                    }else if (resultsTotal.fsRateHb != '0%'){
                        $('.layui-table-total tbody tr td[data-field="fsRateHb"] div').addClass('color1')
                    }
                    if (resultsTotal.fsRateTb.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="fsRateTb"] div').addClass('color2')
                    }else if (resultsTotal.fsRateTb != '0%'){
                        $('.layui-table-total tbody tr td[data-field="fsRateTb"] div').addClass('color1')
                    }

                    if (resultsTotal.zsRateHb.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="zsRateHb"] div').addClass('color2')
                    }else if (resultsTotal.zsRateHb != '0%'){
                        $('.layui-table-total tbody tr td[data-field="zsRateHb"] div').addClass('color1')
                    }
                    if (resultsTotal.zsRateTb.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="zsRateTb"] div').addClass('color2')
                    }else if (resultsTotal.zsRateTb != '0%'){
                        $('.layui-table-total tbody tr td[data-field="zsRateTb"] div').addClass('color1')
                    }

                    if (resultsTotal.kpRateHb.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="kpRateHb"] div').addClass('color2')
                    }else if (resultsTotal.kpRateHb != '0%'){
                        $('.layui-table-total tbody tr td[data-field="kpRateHb"] div').addClass('color1')
                    }
                    if (resultsTotal.kpRateTb.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="kpRateTb"] div').addClass('color2')
                    }else if (resultsTotal.kpRateTb != '0%'){
                        $('.layui-table-total tbody tr td[data-field="kpRateTb"] div').addClass('color1')
                    }

                    if (resultsTotal.dzRateHb.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="dzRateHb"] div').addClass('color2')
                    }else if (resultsTotal.dzRateHb != '0%'){
                        $('.layui-table-total tbody tr td[data-field="dzRateHb"] div').addClass('color1')
                    }
                    if (resultsTotal.dzRateTb.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="dzRateTb"] div').addClass('color2')
                    }else if (resultsTotal.dzRateTb != '0%'){
                        $('.layui-table-total tbody tr td[data-field="dzRateTb"] div').addClass('color1')
                    }

                }
            })
            table.on('tool(test)', function (obj) {
                if (obj.event == 'cost') {
                    var _colsChild = [
                        [
                            {
                                field: 'gzMoney1',
                                minWidth: 130,
                                title: '基本工资',
                                sort: true,
                                templet: function (d) {
                                    return Math.round(d.gzMoney1 * 100) / 100
                                }
                            }, {
                            field: 'gzMoney2',
                            minWidth: 130,
                            title: '社保',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.gzMoney2 * 100) / 100
                            }
                        }, {
                            field: 'jxMoney1',
                            minWidth: 130,
                            title: '固定绩效',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jxMoney1 * 100) / 100
                            }
                        }, {
                            field: 'jxMoney2',
                            minWidth: 130,
                            title: '驻外补贴',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jxMoney2 * 100) / 100
                            }
                        }, {
                            field: 'jxMoney3',
                            minWidth: 130,
                            title: '岗位津贴',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jxMoney3* 100) / 100
                            }
                        }, {
                            field: 'jxMoney4',
                            minWidth: 130,
                            title: '考核绩效',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.jxMoney4 * 100) / 100
                            }
                        }, {
                            field: 'jxMoney5',
                            minWidth: 130,
                            title: '积分绩效',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jxMoney5 * 100) / 100
                            }
                        }, {
                            field: 'jxMoney6',
                            minWidth: 130,
                            title: '审核绩效',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jxMoney6 * 100) / 100
                            }
                        }, {
                            field: 'jxMoney7',
                            minWidth: 130,
                            title: '人事扣款',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jxMoney7 * 100) / 100
                            }
                        },  {
                            field: 'jxMoney8',
                            minWidth: 130,
                            title: '其他补扣款',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jxMoney8 * 100) / 100
                            }
                        }, {
                            field: 'bxMoney1',
                            minWidth: 150,
                            title: '病史费(含复印费)',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.bxMoney1 * 100) / 100
                            }
                        }, {
                            field: 'bxMoney2',
                            minWidth: 150,
                            title: '住院排查费用',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.bxMoney2 * 100) / 100
                            }
                        }, {
                            field: 'bxMoney3',
                            minWidth: 150,
                            title: '门诊排查费用',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.bxMoney3 * 100) / 100
                            }
                        }, {
                            field: 'bxMoney4',
                            minWidth: 150,
                            title: '体检报告打印费',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.bxMoney4 * 100) / 100
                            }
                        }, {
                            field: 'bxMoney5',
                            minWidth: 260,
                            title: '跨地市交通费(汽车、火车、飞机)A',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.bxMoney5 * 100) / 100
                            }
                        }, {
                            field: 'bxMoney6',
                            minWidth: 180,
                            title: '跨地市交通费(自驾)A',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.bxMoney6 * 100) / 100
                            }
                        }, {
                            field: 'bxMoney7',
                            minWidth: 130,
                            title: '市内交通费A',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.bxMoney7 * 100) / 100
                            }
                        }, {
                            field: 'bxMoney8',
                            minWidth: 130,
                            title: '住宿费A',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.bxMoney8 * 100) / 100
                            }
                        },{
                            field: 'qdMoney',
                            minWidth: 130,
                            title: '渠道费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.qdMoney * 100) / 100
                            }
                        }, {
                            field: 'aCostMoney',
                            minWidth: 150,
                            title: '供应商货款结算',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.aCostMoney * 100) / 100
                            }
                        },
                        ]
                    ]
                    showChild({
                        title: '主营业务成本',
                        cols: _colsChild,
                        data: [obj.data.detail]
                    })
                }else if (obj.event == 'market') {
                    var _colsChild = [[
                        {
                            field: 'xsMoney1',
                            minWidth: 260,
                            title: '跨地市交通费(汽车、火车、飞机)B',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.xsMoney1 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney2',
                            minWidth: 180,
                            title: '跨地市交通费(自驾)B',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.xsMoney2 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney3',
                            minWidth: 130,
                            title: '市内交通费B',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.xsMoney3 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney4',
                            minWidth: 130,
                            title: '住宿费B',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.xsMoney4 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney5',
                            minWidth: 150,
                            title: '团建费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney5 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney6',
                            minWidth: 150,
                            title: '奖金',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney6 * 100) / 100
                            }
                        }, {
                            field: 'xsMone7',
                            minWidth: 150,
                            title: '商业保险费',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.xsMoney7 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney8',
                            minWidth: 150,
                            title: '新人首月成本',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney8 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney9',
                            minWidth: 150,
                            title: '培训费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney9 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney10',
                            minWidth: 150,
                            title: '体检',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney10 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney11',
                            minWidth: 150,
                            title: '年会及纪念活动',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney11 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney12',
                            minWidth: 150,
                            title: '招聘',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney12 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney13',
                            minWidth: 150,
                            title: '职场租金',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney13 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney14',
                            minWidth: 150,
                            title: '职场装修费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney14 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney15',
                            minWidth: 150,
                            title: '职场水电费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney15 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney16',
                            minWidth: 150,
                            title: '通讯费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney16 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney17',
                            minWidth: 150,
                            title: '办公物资',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney17 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney18',
                            minWidth: 150,
                            title: '快递费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney18 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney19',
                            minWidth: 150,
                            title: '电脑租赁费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney19 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney20',
                            minWidth: 150,
                            title: '打印和复印',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney20 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney21',
                            minWidth: 150,
                            title: '差旅餐费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney21 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney22',
                            minWidth: 150,
                            title: '异地补贴',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney22 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney23',
                            minWidth: 150,
                            title: '招待费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney23 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney24',
                            minWidth: 150,
                            title: '加班餐费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney24 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney25',
                            minWidth: 150,
                            title: '广告费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney25 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney26',
                            minWidth: 150,
                            title: '印刷费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney26 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney27',
                            minWidth: 150,
                            title: '宣传费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney27 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney28',
                            minWidth: 150,
                            title: '服务费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney28 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney29',
                            minWidth: 150,
                            title: '会务费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney29 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney30',
                            minWidth: 150,
                            title: '维修费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney30 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney31',
                            minWidth: 150,
                            title: '招投标费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney31 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney32',
                            minWidth: 150,
                            title: '代理费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney32 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney33',
                            minWidth: 150,
                            title: '审计费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney33 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney34',
                            minWidth: 150,
                            title: '软件开发费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney34 * 100) / 100
                            }
                        }, {
                            field: 'xsMoney35',
                            minWidth: 150,
                            title: '其他',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.xsMoney35 * 100) / 100
                            }
                        }
                    ]]
                    showChild({
                        title: '销售费用',
                        cols: _colsChild,
                        data: [obj.data.outDetail]
                    })
                }
            })
            $('.layui-export').off('click').on('click',function (e) {
                var _this = $('.layui-export')
                _this.attr('disabled',true)
                soulTable.export(myTable,{
                    filename: '产品经营分析报表.xlsx'
                })
                setTimeout(function () {
                    _this.removeAttr('disabled')
                },5000)
            })
        }


        function getRate(d) {
            var _d = d ? Math.round(d * 10000) / 100 : '0'
            return _d + '%'
        }
        function getFixed(d) {
            var _d = d ? Math.round(d * 100) / 100 : '0'
            return _d
        }

        function showChild(param) {
            var _height = $(document).height() * 0.9
            var _width = $(document).width() * 0.98
            openIndex = layer.open({
                type: 1,
                title: param.title,
                area: [_width + 'px', _height + 'px'],
                content: $('#table-content-child-h').html(),
                success: function () {
                    console.log(param.cols, param.data)
                    setTableChild(param.cols, param.data)

                }
            });

        }
        function setTableChild(_colsChild,data){
            $('button.ll-submit-child').attr('disabled', true)
            setTimeout(function () {
                $('button.ll-submit-child').removeAttr('disabled')
            }, 6000)

            var _h = $('.searchs-child').outerHeight() + 150
            var fullH = 'full-' + _h
            var myTable = table.render({
                id: "test-child",
                elem: '#test-child',
                even: true,
                drag: false,
                cols: _colsChild,
                page:false,
                overflow: {
                    type: 'tips'
                    ,hoverTime: 300 // 悬停时间，单位ms, 悬停 hoverTime 后才会显示，默认为 0
                    ,color: 'black' // 字体颜色
                    ,bgColor: 'white' // 背景色
                    ,minWidth: 250 // 最小宽度
                    ,maxWidth: 500 // 最大宽度
                },
                height: fullH,
                data: data,
                done: function (res) {
                    soulTable.render(this)
                }
            })
        }
    })
</script>

</body>

</html>