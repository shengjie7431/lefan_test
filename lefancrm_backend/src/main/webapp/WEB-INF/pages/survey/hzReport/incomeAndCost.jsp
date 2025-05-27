<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>收入与成本报表</title>
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
    <input type="hidden" value='${params.franchiseesJson}' id="franchiseesJson"/>
    <input type="hidden" value='${params.investigatorsJson}' id="investigatorsJson"/>
    <input type="hidden" value='${scoreRole}'/>
    <div class="layui-form searchs" lay-filter="search">
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
                        <div class="data-types">
                            <div class="data-type active" data-id="upMonth">上月</div>
                            <div class="data-type" data-id="7days">7日</div>
                            <div class="data-type" data-id="today">今天</div>
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

<script type="text/html" id="surveyOrgName">
    {{#  if(d.orgType ==1){ }}
    <div class="nowrap-e" title="【A类】{{d.surveyOrgName}}"><span
            class="d_type d_type_a">【A类】</span>{{d.surveyOrgName}}
    </div>
    {{#  } else if(d.orgType ==2){ }}
    <div class="nowrap-e" title="【B类】{{d.surveyOrgName}}"><span
            class="d_type d_type_b">【B类】</span>{{d.surveyOrgName}}
    </div>
    {{#  } else { }}
    <div class="nowrap-e" title="{{d.surveyOrgName}}">{{d.surveyOrgName}}</div>
    {{#  } }}
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
                date: _max.d
            }
            endTime.config.min = {
                year: _min.y,
                month: _min.m - 1,
                date: _min.d
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

        var franchiseesJson = $("#franchiseesJson").val();
        franchiseesJson = JSON.parse(franchiseesJson);

        filterJson(demo2, franchiseesJson, 'id', 'name', false, false)


        var initVals = setDate('upMonth')
        startTime = laydate.render({
            elem: '#startTime',
            value: initVals[0],
            max: initVals[1],
            isInitValue: true,
            done: function (value, date) {
                endTime.config.min = {
                    year: date.year,
                    month: date.month - 1,
                    date: date.date
                }
                searchType = 'date'
                $('.data-types .data-type').removeClass('active')
            }
        });
        endTime = laydate.render({
            elem: '#endTime',
            value: initVals[1],
            min: initVals[0],
            isInitValue: true,
            done: function (value, date) {
                if (value) {
                    startTime.config.max = {
                        year: date.year,
                        month: date.month - 1,
                        date: date.date
                    }
                } else {
                    startTime.config.max = {
                        year: 2100,
                        month: 1,
                        date: 1
                    }
                }
                searchType = 'date'
                $('.data-types .data-type').removeClass('active')
            }
        });

        var paramSubmit = {
            menuCode: 'incomeAndCost',
            entrustOrgIds: '',
            startTime: initVals[0],
            endTime: initVals[1],
            searchType: searchType,
        }
        form.on('submit(submit)', function (data) {
            var vals = data.field
            delete vals.select;
            Object.assign(vals, {
                menuCode: 'incomeAndCost',
                surveyOrgIds: demo2.getValue('valueStr'),
                startTime: $('#startTime').val(),
                endTime: $('#endTime').val(),
                searchType: searchType
            })
            paramSubmit = vals
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

        function getOfSignedNum(d) {
            return Math.round(d * 100) / 100

        }

        var _cols1 = [
            [{
                field: 'surveyOrgName',
                minWidth: 220,
                title: '调查机构',
                templet: '#surveyOrgName',
                fixed: true,
                totalRowText: '合计',
            }, {
                title: '',
                show: 2,
                width: 60,
                lazy: true,
                isChild: function (row) {
                    return row.orgType == 1 ? true : false;
                },
                children: function (row) {
                    Object.assign(paramSubmit, {clickPq: 'yew', surveyOrgIds: row.surveyOrgId});
                    return [{
                        title: function (row) {
                            first = row.surveyOrgName
                            return row.surveyOrgName + ' - 片区机构列表'
                        },
                        url: '${ctx}/survey/hzReport/getDetail',
                        where: paramSubmit,
                        parseData: function (res) {
                            return {
                                "code": res.isSuccess ? 0 : 1,
                                "msg": res.msg,
                                "count": res.count,
                                "data": res.results.list
                            }
                        },
                        initSort: {
                            field: 'insuranceCompanyConfirmsRevenue',
                            type: 'desc'
                        },
                        overflow: {
                            type: 'tips',
                            hoverTime: 100,
                            color: '#333',
                            bgColor: '#fff',
                            minWidth: 100,
                            maxWidth: 500,
                        },
                        drag: false,
                        page: false,
                        limit: 200,
                        height: _height * 0.96,
                        even: true, cols: [
                            [{
                                field: 'surveyOrgName',
                                minWidth: 220,
                                title: '调查机构',
                                fixed: true,

                            },
                                // {
                                //     field: 'platformApplicationIncome',
                                //     minWidth: 150,
                                //     title: '平台申请收入',
                                //     sort: true,
                                //     templet: function (d) {
                                //         return Math.round(d.platformApplicationIncome * 100) / 100
                                //     }
                                // },
                                // {
                                //     field: 'platformApplicationIncomeRate',
                                //     minWidth: 180,
                                //     title: '平台申请收入环比',
                                //     sort: true,
                                //     templet: function (d) {
                                //         var _d = Math.round(d.platformApplicationIncomeRate * 10000) / 100
                                //         var _html = '<div class="">' + _d + '%</div>'
                                //         if (_d > 0) {
                                //             _html = '<div class="color1">+' + _d + '%</div>'
                                //         } else if (_d < 0) {
                                //             _html = '<div class="color2">' + _d + '%</div>'
                                //
                                //         }
                                //         return _html
                                //     }
                                // },
                            //     {
                            //         field: 'insuranceCompanyConfirmsRevenue',
                            //         minWidth: 150,
                            //         title: '保司确认收入',
                            //         sort: true,
                            //         templet: function (d) {
                            //             return Math.round(d.insuranceCompanyConfirmsRevenue * 100) / 100
                            //         }
                            //     }, {
                            //     field: 'insuranceCompanyConfirmsRevenueRate',
                            //     minWidth: 180,
                            //     title: '保司确认收入环比',
                            //     sort: true,
                            //     templet: function (d) {
                            //         var _d = Math.round(d.insuranceCompanyConfirmsRevenueRate * 10000) / 100
                            //         var _html = '<div class="">' + _d + '%</div>'
                            //         if (_d > 0) {
                            //             _html = '<div class="color1">+' + _d + '%</div>'
                            //         } else if (_d < 0) {
                            //             _html = '<div class="color2">' + _d + '%</div>'
                            //
                            //         }
                            //         return _html
                            //     }
                            // },
                                {
                                field: 'cityTransportationSubsidies',
                                minWidth: 180,
                                title: '市内交通费',
                                sort: true,
                                templet: function (d) {
                                    return Math.round(d.cityTransportationSubsidies * 100) / 100
                                }
                            }, {
                                field: 'medicalHistoryMoney',
                                minWidth: 130,
                                title: '病史费',
                                sort: true,
                                templet: function (d) {
                                    return Math.round(d.medicalHistoryMoney * 100) / 100
                                }
                            }, {
                                field: 'troubleshootingMoney',
                                minWidth: 150,
                                title: '住院排查费用',
                                sort: true,
                                templet: function (d) {
                                    return Math.round(d.troubleshootingMoney * 100) / 100
                                }
                            }, {
                                field: 'opcTroubleshootingMoney',
                                minWidth: 150,
                                title: '门诊排查费用',
                                sort: true,
                                templet: function (d) {
                                    return Math.round(d.opcTroubleshootingMoney * 100) / 100
                                }
                            }, {
                                field: 'printingMoney',
                                minWidth: 180,
                                title: '体检报告打印费',
                                sort: true,
                                templet: function (d) {
                                    return Math.round(d.printingMoney * 100) / 100
                                }
                            }, {
                                field: 'accommodatioMoney',
                                minWidth: 130,
                                title: '住宿费',
                                sort: true,
                                templet: function (d) {
                                    return Math.round(d.accommodatioMoney * 100) / 100
                                }
                            }, {
                                field: 'crossCityTransportationFees',
                                minWidth: 160,
                                title: '跨地市交通费',
                                sort: true,
                                templet: function (d) {
                                    return Math.round(d.crossCityTransportationFees * 100) / 100
                                }
                            }, {
                                field: 'otherFee',
                                minWidth: 130,
                                title: '其他费用',
                                sort: true,
                                templet: function (d) {
                                    return Math.round(d.otherFee * 100) / 100
                                }
                            }, {
                                field: 'totalMoney',
                                minWidth: 150,
                                title: '合计费用',
                                sort: true,
                                templet: function (d) {
                                    return Math.round(d.totalMoney * 100) / 100
                                }
                            }
                            ]
                        ],
                        done: function () {
                            soulTable.render(this);
                            showTitle('.layui-layer-page .layui-layer-content .layui-tab-content .layui-table-view .layui-table-box .layui-table-header:first table thead tr:last th:gt(0)',true)
                        },
                        defaultToolbar:[],
                        toolbar: '<div><a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="export">导出</a>',
                        toolbarEvent: function (obj, pobj) {
                            // obj 子表当前行对象
                            // pobj 父表当前行对象
                            onRowEvent(obj, pobj, first +'-片区机构')
                        },
                    }]
                }
            },

                // {
                //     field: 'platformApplicationIncome',
                //     minWidth: 150,
                //     title: '平台申请收入',
                //     sort: true,
                //     totalRow: true,
                //     templet: function (d) {
                //         return Math.round(d.platformApplicationIncome * 100) / 100
                //     }
                // },
                // {
                //     field: 'platformApplicationIncomeRate',
                //     minWidth: 180,
                //     title: '平台申请收入环比',
                //     sort: true,
                //     totalRow: true,
                //     templet: function (d) {
                //         var _d = Math.round(d.platformApplicationIncomeRate * 10000) / 100
                //         var _html = '<div class="">' + _d + '%</div>'
                //         if (_d > 0) {
                //             _html = '<div class="color1">+' + _d + '%</div>'
                //         } else if (_d < 0) {
                //             _html = '<div class="color2">' + _d + '%</div>'
                //
                //         }
                //         return _html
                //     }
                // },
                {
                    field: 'insuranceCompanyConfirmsRevenue',
                    minWidth: 150,
                    title: '保司确认收入',
                    sort: true,
                    totalRow: true,
                    templet: function (d) {
                        return Math.round(d.insuranceCompanyConfirmsRevenue * 100) / 100
                    }
                }, {
                field: 'insuranceCompanyConfirmsRevenueRate',
                minWidth: 180,
                title: '保司确认收入环比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.insuranceCompanyConfirmsRevenueRate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            }, {
                field: 'surveyCompanyConfirmsRevenue',
                minWidth: 150,
                title: '调查确认收入',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.surveyCompanyConfirmsRevenue * 100) / 100
                }
            }, {
                field: 'surveyCompanyConfirmsRevenueRate',
                minWidth: 180,
                title: '调查确认收入环比',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.surveyCompanyConfirmsRevenueRate * 10000) / 100
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
                field: 'cityTransportationSubsidies',
                minWidth: 180,
                title: '市内交通费',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.cityTransportationSubsidies * 100) / 100
                }
            }, {
                field: 'medicalHistoryMoney',
                minWidth: 130,
                title: '病史费',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.medicalHistoryMoney * 100) / 100
                }
            }, {
                field: 'troubleshootingMoney',
                minWidth: 150,
                title: '住院排查费用',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.troubleshootingMoney * 100) / 100
                }
            }, {
                field: 'opcTroubleshootingMoney',
                minWidth: 150,
                title: '门诊排查费用',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.opcTroubleshootingMoney * 100) / 100
                }
            }, {
                field: 'printingMoney',
                minWidth: 180,
                title: '体检报告打印费',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.printingMoney* 100) / 100
                }
            }, {
                field: 'accommodatioMoney',
                minWidth: 130,
                title: '住宿费',
                sort: true,
                templet: function (d) {
                    return Math.round(d.accommodatioMoney * 100) / 100
                }
            }, {
                field: 'crossCityTransportationFees',
                minWidth: 140,
                title: '跨地市交通费',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.crossCityTransportationFees * 100) / 100
                }
            }, {
                field: 'otherFee',
                minWidth: 130,
                title: '其他费用',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.otherFee * 100) / 100
                }
            }, {
                field: 'totalMoney',
                minWidth: 150,
                title: '合计费用',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.totalMoney * 100) / 100
                }
            }
            ]
        ]
        var _cols = _cols1


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
                limit: 200,
                height: fullH,
                drag: false,
                url: '${ctx}/survey/hzReport/getDetail',
                where: param,
                parseData: function (res) {
                    resultsTotal = {
                        "platformApplicationIncomeRate": getRate(res.results.platTotalRate),
                        "insuranceCompanyConfirmsRevenueRate": getRate(res.results.invTotalRate),
                    }
                    return {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results.list,
                        "totalRow": {
                            // "platformApplicationIncome": res.results.platTotal || '0' ,
                            // "platformApplicationIncomeRate": getRate(res.results.platTotalRate),
                            "insuranceCompanyConfirmsRevenue": res.results.invTotal || '0' ,
                            "insuranceCompanyConfirmsRevenueRate": getRate(res.results.invTotalRate),
                            "cityTransportationSubsidies": getFixed(res.results.cityInnerTotalMoney) || '0' ,
                            "medicalHistoryMoney": getFixed(res.results.medicalHistoryTotalMoney) || '0' ,
                            "troubleshootingMoney": getFixed(res.results.troubleshootingTotalMoney) || '0' ,
                            "opcTroubleshootingMoney": getFixed(res.results.opcTroubleshootingMoney) || '0' ,
                            "printingMoney": getFixed(res.results.printingTotalMoney) || '0' ,
                            "accommodatioMoney": getFixed(res.results.accommodatioTotalMoney) || '0' ,
                            "crossCityTransportationFees": getFixed(res.results.crossCityTransportationTotalMoney) || '0' ,
                            "otherFee": getFixed(res.results.otherTotalMoney) || '0' ,
                            "totalMoney": getFixed(res.results.finalTotalMoney) || '0'
                        }
                    }
                },
                initSort: {
                    field: 'insuranceCompanyConfirmsRevenue',
                    type: 'desc'
                },
                done: function () {
                    soulTable.render(this)
                    $('button.ll-submit').removeAttr('disabled')
                    showTitle('.table-content .layui-table-view .layui-table-box .layui-table-header:first table thead tr th:gt(1)')

                    if (resultsTotal.platformApplicationIncomeRate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="platformApplicationIncomeRate"] div').addClass('color2')
                    }else if (resultsTotal.platformApplicationIncomeRate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="platformApplicationIncomeRate"] div').addClass('color1')
                    }

                    if (resultsTotal.insuranceCompanyConfirmsRevenueRate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="insuranceCompanyConfirmsRevenueRate"] div').addClass('color2')
                    }else if (resultsTotal.insuranceCompanyConfirmsRevenueRate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="insuranceCompanyConfirmsRevenueRate"] div').addClass('color1')
                    }
                }
            })



            $('.layui-export').off('click').on('click',function (e) {
                var _this = $('.layui-export')
                _this.attr('disabled',true)
                soulTable.export(myTable,{
                    filename: '收入与成本报表.xlsx'
                })
                setTimeout(function () {
                    _this.removeAttr('disabled')
                },5000)
            })
        }

        function onRowEvent(obj, pobj,num) {
            if (obj.event === 'export') {
                soulTable.export(obj.config.id,{
                    filename: '收入与成本报表('+ num+').xlsx'
                })
            }
        }

        function getRate(d) {
            var _d = Math.round(d * 10000) / 100
            return _d + '%'
        }
        function getFixed(d) {
            var _d = Math.round(d * 100) / 100
            return _d
        }
        setSortDesc('insuranceCompanyConfirmsRevenue')

        function setSortDesc(param) {
            $('body').on('click', '.layui-table-col-special', function () {
                var _this = $(this)
                if (_this.parents('.main').length) {
                    var _ll = $('.main').siblings('.layui-layer')
                    _ll.find('th[data-field="' + param + '"] .layui-table-cell').click()
                    _ll.find('th[data-field="' + param + '"] .layui-table-cell').click()
                } else {
                    var _ll = _this.parents('.layui-layer')
                    if (_this.parents('.layui-layer').siblings('.layui-layer').length) {
                        _ll = _this.parents('.layui-layer').siblings('.layui-layer')
                    }
                    _ll.find('th[data-field="' + param + '"] .layui-table-cell').click()
                    _ll.find('th[data-field="' + param + '"] .layui-table-cell').click()
                }
            })
        }


        function showTitle(thsJ, child, iter) {
            var icon_about = '<span class="icon-about">'
            var ths = $(thsJ)
            var startTime = dateFormat($('#startTime').val(), 'yyyy年MM月dd日')
            var endTime = dateFormat($('#endTime').val(), 'yyyy年MM月dd日')
            ths.find('.layui-table-cell span:first').after(icon_about)

            // var _title3 = '平台复审通过时间在'+startTime+ '至' + endTime +'的互助案件中，各机构向委托方申请的对应委托方价格之和'
            // ths.eq(0).find('.layui-table-cell').attr('title', _title3)
            //
            // var _title4 ='连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数×100%'
            // ths.eq(1).find('.layui-table-cell').attr('title', _title4)

            if (child){

                var _title7 ='打卡时间在'+startTime+ '至' + endTime +'的互助案件中，机构内的调查员录入的市内交通费报销金额'
                ths.eq(0).find('.layui-table-cell').attr('title', _title7)

                var _title8 = '打卡时间在'+startTime+ '至' + endTime +'的互助案件中，机构内的调查员录入的病史费金额'
                ths.eq(1).find('.layui-table-cell').attr('title', _title8)

                var _title9 = '打卡时间在'+startTime+ '至' + endTime +'的互助案件中，机构内的调查员录入的住院排查费用金额'
                ths.eq(2).find('.layui-table-cell').attr('title', _title9)

                var _title99 = '打卡时间在'+startTime+ '至' + endTime +'的互助案件中，机构内的调查员录入的门诊排查费用金额'
                ths.eq(3).find('.layui-table-cell').attr('title', _title99)

                var _title10 = '打卡时间在'+startTime+ '至' + endTime +'的互助案件中，机构内的调查员录入的体检报告打印费金额'
                ths.eq(4).find('.layui-table-cell').attr('title', _title10)

                var _title11 = '打卡时间在'+startTime+ '至' + endTime +'的互助案件中，机构内的调查员录入的住宿费金额'
                ths.eq(5).find('.layui-table-cell').attr('title', _title11)

                var _title12 = '打卡时间在'+startTime+ '至' + endTime +'的互助案件中，机构内的调查员录入的跨地市交通费金额'
                ths.eq(6).find('.layui-table-cell').attr('title', _title12)

                var _title13 = '打卡时间在'+startTime+ '至' + endTime +'的互助案件中，机构内的调查员录入的其他费用金额'
                ths.eq(7).find('.layui-table-cell').attr('title', _title13)

                var _title14 = '市内交通费+病史费+排查费用+体检报告打印费+住宿费+跨地市交通费+其他费用'
                ths.eq(8).find('.layui-table-cell').attr('title', _title14)
            }else{
                var _title5 = '保司终审通过时间在'+startTime+ '至' + endTime +'的互助案件中，各机构对应的委托方确认价格之和'
                ths.eq(0).find('.layui-table-cell').attr('title', _title5)

                var _title6 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=（本期数-上期数）/上期数×100%'
                ths.eq(1).find('.layui-table-cell').attr('title', _title6)

                var _title7 ='打卡时间在'+startTime+ '至' + endTime +'的互助案件中，机构内的调查员录入的市内交通费报销金额'
                ths.eq(2).find('.layui-table-cell').attr('title', _title7)

                var _title8 = '打卡时间在'+startTime+ '至' + endTime +'的互助案件中，机构内的调查员录入的病史费金额'
                ths.eq(3).find('.layui-table-cell').attr('title', _title8)

                var _title9 = '打卡时间在'+startTime+ '至' + endTime +'的互助案件中，机构内的调查员录入的住院排查费用金额'
                ths.eq(4).find('.layui-table-cell').attr('title', _title9)

                var _title99 = '打卡时间在'+startTime+ '至' + endTime +'的互助案件中，机构内的调查员录入的门诊排查费用金额'
                ths.eq(5).find('.layui-table-cell').attr('title', _title99)

                var _title10 = '打卡时间在'+startTime+ '至' + endTime +'的互助案件中，机构内的调查员录入的体检报告打印费金额'
                ths.eq(6).find('.layui-table-cell').attr('title', _title10)

                var _title11 = '打卡时间在'+startTime+ '至' + endTime +'的互助案件中，机构内的调查员录入的住宿费金额'
                ths.eq(7).find('.layui-table-cell').attr('title', _title11)

                var _title12 = '打卡时间在'+startTime+ '至' + endTime +'的互助案件中，机构内的调查员录入的跨地市交通费金额'
                ths.eq(8).find('.layui-table-cell').attr('title', _title12)

                var _title13 = '打卡时间在'+startTime+ '至' + endTime +'的互助案件中，机构内的调查员录入的其他费用金额'
                ths.eq(9).find('.layui-table-cell').attr('title', _title13)

                var _title14 = '市内交通费+病史费+排查费用+体检报告打印费+住宿费+跨地市交通费+其他费用'
                ths.eq(10).find('.layui-table-cell').attr('title', _title14)
            }



        }



    })
</script>

</body>

</html>