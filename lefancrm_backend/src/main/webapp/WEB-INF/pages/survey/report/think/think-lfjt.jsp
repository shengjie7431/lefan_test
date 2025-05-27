<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>经营分析报表-乐凡集团</title>
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
    <input type="hidden" value='${organsJson}' id="organsJson"/>
    <input type="hidden" value='${orgManager}' id="orgManager"/>
    <div class="layui-form searchs" lay-filter="search">
        <div class="layui-form-item ">
            <%--<div class="layui-inline">--%>
                <%--<label class="layui-form-label">机构</label>--%>
                <%--<div class="layui-input-inline">--%>
                    <%--<div id="surveyOrgId" class="selectMul"></div>--%>
                <%--</div>--%>
            <%--</div>--%>
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
                <label class="layui-form-label">时间类型</label>
                <div class="layui-input-inline">
                    <div id="searchTimeTypeId" class="selectMul"></div>
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

        // var demo1 = xmSelect.render({
        //     el: '#surveyOrgId',
        //     theme: {
        //         color: '#3BA9FF',
        //     },
        //     size: 'small',
        //     toolbar: {
        //         show: true
        //     },
        //     filterable: true,
        //     filterDone: function(val, list){
        //         $('.xm-option-content').each(function () {
        //             var _this = $(this)
        //             _this.attr('title', _this.text())
        //         })
        //     },
        //     model: {
        //         label: {
        //             type: 'xxxx', //自定义与下面的对应
        //             xxxx: {
        //                 template(data, sels) {
        //
        //                     if (sels.length == data.length) {
        //                         return '<div>全部</div>'
        //                     } else {
        //                         var _html = ''
        //                         sels.filter(function (cur) {
        //                             _html +=
        //                                 '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
        //                         })
        //                         return _html
        //                     }
        //                 }
        //             },
        //         }
        //     },
        //     data: []
        // })
        var demo2 = xmSelect.render({
            el: '#searchTimeTypeId',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
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
                name: '平台复审',
                value: 1
            },{
                name: '保司终审',
                value: 2,
                selected: true
            }]
        })

        // var organsJson = $("#organsJson").val();
        // organsJson = JSON.parse(organsJson);
        // filterJson(demo1,organsJson,'id','name',false,false)


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
            dataType: 'think-lfjt',
            // orgIds: '',
            searchType: searchType,
            startTime: initVals[0]+'-01',
            endTime: initVals[1]+'-01',
            searchTimeType: 2
        }

        form.on('submit(submit)', function (data) {
            Object.assign(paramSubmit, {
                // orgIds: demo1.getValue('valueStr'),
                searchType: searchType,
                startTime: $('#startTime').val()+'-01',
                endTime: $('#endTime').val()+'-01',
                searchTimeType:demo2.getValue('valueStr'),
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
            // if (d > 0) {
            //     _html = '<div class="color1">+' + _d + '%</div>'
            // } else if (d < 0) {
            //     _html = '<div class="color2">' + _d + '%</div>'
            //
            // }
            return _html

        }

        function getOfSignedNum(d) {
            return Math.round(d * 100) / 100

        }

        var _cols = [
            [
                {
                    field: 'commpanyName',
                    minWidth: 200,
                    title: '公司名称',
                    rowspan: 2,
                    fixed:true,
                    merge: true,
                    totalRowText: '合计'

                },

                {
                    title: '收入',
                    colspan: 5,
                    align: 'center',
                },
                {
                    title: '支出',
                    colspan: 4,
                    align: 'center',
                },  {
                title: '利润',
                colspan: 12,
                align: 'center',
            },
                {
                    title: '其他',
                    colspan: 15,
                    align: 'center',
                },
            ],

            [{
                field: 'sr2',
                minWidth: 190,
                title: '开票含税收入',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.sr2 * 100) / 100
                }
            },{
                field: 'sr3',
                minWidth: 210,
                title: '开票非税收入',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.sr3 * 100) / 100
                }
            },{
                field: 'sr4',
                minWidth: 190,
                title: '开票税费金额',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.sr4 * 100) / 100
                }
            },{
                field: 'sr5',
                minWidth: 190,
                title: '业务回款收入',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.sr5 * 100) / 100
                }
            },{
                field: 'sr6Rate',
                minWidth: 180,
                title: '业务回款率',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.sr6Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    // if (_d > 0) {
                    //     _html = '<div class="color1">+' + _d + '%</div>'
                    // } else if (_d < 0) {
                    //     _html = '<div class="color2">' + _d + '%</div>'
                    //
                    // }
                    return _html
                }
            },
                {
                    field: 'zc1',
                    minWidth: 130,
                    title: '主营业务成本',
                    sort: true,
                    totalRow: true,
                    style:'color:#3ba9ff',
                    event: 'zyywcb',
                    templet: function (d) {
                        return Math.round(d.zc1 * 100) / 100
                    }
                },{
                field: 'zc2',
                minWidth: 120,
                title: '销售费用',
                sort: true,
                totalRow: true,
                style:'color:#3ba9ff',
                event: 'xsfy',
                templet: function (d) {
                    return Math.round(d.zc2 * 100) / 100
                }
            },{
                field: 'zc3',
                minWidth: 120,
                title: '管理费用',
                sort: true,
                totalRow: true,
                style:'color:#3ba9ff',
                event: 'glfy',
                templet: function (d) {
                    return Math.round(d.zc3 * 100) / 100
                }
            },{
                field: 'zc4',
                minWidth: 120,
                title: '合计',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round((d.zc1 + d.zc2 + d.zc3) * 100) / 100
                }

            },{
                field: 'lr1',
                minWidth: 120,
                title: '毛利润',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.lr1 * 100) / 100
                }
            },{
                field: 'lr1Rate',
                minWidth: 120,
                title: '毛利润率',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.lr1Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    // if (_d > 0) {
                    //     _html = '<div class="color1">+' + _d + '%</div>'
                    // } else if (_d < 0) {
                    //     _html = '<div class="color2">' + _d + '%</div>'
                    //
                    // }
                    return _html
                }
            },{
                field: 'lr2',
                minWidth: 120,
                title: '经营利润',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.lr2 * 100) / 100
                }
            },{
                field: 'lr2Rate',
                minWidth: 120,
                title: '经营利润率',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.lr2Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    // if (_d > 0) {
                    //     _html = '<div class="color1">+' + _d + '%</div>'
                    // } else if (_d < 0) {
                    //     _html = '<div class="color2">' + _d + '%</div>'
                    //
                    // }
                    return _html
                }
            },{
                field: 'lr4',
                minWidth: 120,
                title: '营业外回款收入',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.lr4 * 100) / 100
                }
            }, {
                field: 'lr5',
                minWidth: 120,
                title: '营业外支出',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.lr5 * 100) / 100
                }
            },{
                field: 'lr6',
                minWidth: 120,
                title: '营业外利润',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.lr6 * 100) / 100
                }
            },{
                field: 'lr7',
                minWidth: 120,
                title: '利润总额',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.lr7 * 100) / 100
                }
            },{
                field: 'lr7Rate',
                minWidth: 120,
                title: '利润总额率',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.lr7Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    // if (_d > 0) {
                    //     _html = '<div class="color1">+' + _d + '%</div>'
                    // } else if (_d < 0) {
                    //     _html = '<div class="color2">' + _d + '%</div>'
                    //
                    // }
                    return _html
                }
            },{
                field: 'lr8',
                minWidth: 120,
                title: '企业所得税',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.lr8 * 100) / 100
                }
            },{
                field: 'lr9',
                minWidth: 120,
                title: '净利润',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.lr9 * 100) / 100
                }
            },{
                field: 'lr9Rate',
                minWidth: 120,
                title: '净利润率',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.lr9Rate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    // if (_d > 0) {
                    //     _html = '<div class="color1">+' + _d + '%</div>'
                    // } else if (_d < 0) {
                    //     _html = '<div class="color2">' + _d + '%</div>'
                    //
                    // }
                    return _html
                }
            }, {
                field: 'qt1',
                minWidth: 150,
                title: '在编主营员工数',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.qt1 * 100) / 100
                }
            },{
                field: 'qt2',
                minWidth: 300,
                title: '在编主营员工人均产品线开票非税收收入',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.qt2 * 100) / 100
                }
            },{
                field: 'qt3',
                minWidth: 280,
                title: '在编主营员工人均产品线业务回款收入',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.qt3 * 100) / 100
                }
            },{
                field: 'qt4',
                minWidth: 220,
                title: '在编主营员工人均支出成本',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.qt4 * 100) / 100
                }
            },{
                field: 'qt5',
                minWidth: 200,
                title: '在编主营员工人均毛利润',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.qt5 * 100) / 100
                }
            },{
                field: 'qt6',
                minWidth: 210,
                title: '在编主营员工人均毛利润率',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.qt6 * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    // if (_d > 0) {
                    //     _html = '<div class="color1">+' + _d + '%</div>'
                    // } else if (_d < 0) {
                    //     _html = '<div class="color2">' + _d + '%</div>'
                    //
                    // }
                    return _html
                }
            },
                {
                    field: 'qt7',
                    minWidth: 150,
                    title: '在编总人员数',
                    sort: true,
                    totalRow: true,
                    templet: function (d) {
                        return Math.round(d.qt7 * 100) / 100
                    }
                },{
                field: 'qt8',
                minWidth: 290,
                title: '在编总人员人均产品线开票非税收收入',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.qt8 * 100) / 100
                }
            },{
                field: 'qt9',
                minWidth: 280,
                title: '在编总人员人均产品线业务回款收入',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.qt9 * 100) / 100
                }
            },{
                field: 'qt10',
                minWidth: 280,
                title: '在编总人员人均产品线业务核算收入',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.qt10 * 100) / 100
                }
            },{
                field: 'qt11',
                minWidth: 230,
                title: '在编总人员数人均支出成本',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.qt11 * 100) / 100
                }
            },{
                field: 'qt12',
                minWidth: 230,
                title: '在编总人员人均经营利润',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.qt12 * 100) / 100
                }
            },{
                field: 'qt13',
                minWidth: 230,
                title: '在编总人员人均经营利润率',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.qt13 * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    // if (_d > 0) {
                    //     _html = '<div class="color1">+' + _d + '%</div>'
                    // } else if (_d < 0) {
                    //     _html = '<div class="color2">' + _d + '%</div>'
                    //
                    // }
                    return _html
                }
            },{
                field: 'qt14',
                minWidth: 230,
                title: '在编总人员人均核算利润',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.round(d.qt14 * 100) / 100
                }

            },{
                field: 'qt15',
                minWidth: 230,
                title: '在编总人员人均核算利润率',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _d = Math.round(d.qt15 * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    // if (_d > 0) {
                    //     _html = '<div class="color1">+' + _d + '%</div>'
                    // } else if (_d < 0) {
                    //     _html = '<div class="color2">' + _d + '%</div>'
                    //
                    // }
                    return _html
                }
            },
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
                even: true,
                cols: _cols,
                page: false,
                totalRow: true,
                height: fullH,
                drag: false,
                url: '${ctx}/survey/report/getData',
                where: param,
                parseData: function (res) {
                    resultsTotal= {
                        "sr4Rate": getRate(res.results.total.sr4Rate),
                        "lr1Rate": getRate(res.results.total.lr1Rate),
                        "lr2Rate": getRate(res.results.total.lr2Rate),
                        "lr3Rate": getRate(res.results.total.lr3Rate),
                        "qt6": getRate(res.results.total.qt6),
                        "qt13": getRate(res.results.total.qt13),
                        "qt15": getRate(res.results.total.qt15),
                    }
                    return {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results.list,
                        "totalRow": {
                            "sr2": getFixed(res.results.total.sr2),
                            "sr3": getFixed(res.results.total.sr3),
                            "sr4": getFixed(res.results.total.sr4),
                            "sr5": getFixed(res.results.total.sr5),
                            "sr6Rate": getRate(res.results.total.sr6Rate),
                            "zc1": getFixed(res.results.total.zc1),
                            "zc2": getFixed(res.results.total.zc2),
                            "zc3": getFixed(res.results.total.zc3),
                            "zc4": getFixed(res.results.total.zc1 + res.results.total.zc2 + res.results.total.zc3),
                            "lr1": getFixed(res.results.total.lr1),
                            "lr1Rate": getRate(res.results.total.lr1Rate),
                            "lr2": getFixed(res.results.total.lr2),
                            "lr2Rate": getRate(res.results.total.lr2Rate),
                            "lr4": getFixed(res.results.total.lr4),
                            "lr5": getFixed(res.results.total.lr5),
                            "lr6": getFixed(res.results.total.lr6),
                            "lr7": getFixed(res.results.total.lr7),
                            "lr7Rate": getRate(res.results.total.lr7Rate),
                            "lr8": getFixed(res.results.total.lr8),
                            "lr9": getFixed(res.results.total.lr9),
                            "lr9Rate": getRate(res.results.total.lr9Rate),
                            "qt1": getFixed(res.results.total.qt1),
                            "qt2": getFixed(res.results.total.qt2),
                            "qt3": getFixed(res.results.total.qt3),
                            "qt4": getFixed(res.results.total.qt4),
                            "qt5": getFixed(res.results.total.qt5),
                            "qt6": getRate(res.results.total.qt6),
                            "qt7": getFixed(res.results.total.qt7),
                            "qt8": getFixed(res.results.total.qt8),
                            "qt9": getFixed(res.results.total.qt9),
                            "qt10": getFixed(res.results.total.qt10),
                            "qt11": getFixed(res.results.total.qt11),
                            "qt12": getFixed(res.results.total.qt12),
                            "qt13": getRate(res.results.total.qt13),
                            "qt14": getFixed(res.results.total.qt14),
                            "qt15": getRate(res.results.total.qt15),
                        }
                    }
                },
                done: function () {
                    soulTable.render(this)
                    $('button.ll-submit').removeAttr('disabled')

                    if (resultsTotal.sr4Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="sr4Rate"] div').addClass('color2')
                    }else if (resultsTotal.sr4Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="sr4Rate"] div').addClass('color1')
                    }
                    if (resultsTotal.lr1Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="lr1Rate"] div').addClass('color2')
                    }else if (resultsTotal.lr1Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="lr1Rate"] div').addClass('color1')
                    }
                    if (resultsTotal.lr2Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="lr2Rate"] div').addClass('color2')
                    }else if (resultsTotal.lr2Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="lr2Rate"] div').addClass('color1')
                    }
                    if (resultsTotal.lr3Rate.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="lr3Rate"] div').addClass('color2')
                    }else if (resultsTotal.lr3Rate != '0%'){
                        $('.layui-table-total tbody tr td[data-field="lr3Rate"] div').addClass('color1')
                    }

                    if (resultsTotal.qt6.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="qt6"] div').addClass('color2')
                    }else if (resultsTotal.qt6 != '0%'){
                        $('.layui-table-total tbody tr td[data-field="qt6"] div').addClass('color1')
                    }
                    if (resultsTotal.qt13.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="qt13"] div').addClass('color2')
                    }else if (resultsTotal.qt13 != '0%'){
                        $('.layui-table-total tbody tr td[data-field="qt13"] div').addClass('color1')
                    }
                    if (resultsTotal.qt15.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="qt15"] div').addClass('color2')
                    }else if (resultsTotal.qt15 != '0%'){
                        $('.layui-table-total tbody tr td[data-field="qt15"] div').addClass('color1')
                    }
                }
            })
            table.on('tool(test)', function (obj) {
                if (obj.event == 'zyywcb') {
                    var _colsChild = [
                        [
                            {
                                field: 'gz1',
                                minWidth: 130,
                                title: '实发工资',
                                sort: true,
                                templet: function (d) {
                                    return Math.round(d.gz1 * 100) / 100
                                }
                            }, {
                            field: 'gz2',
                            minWidth: 130,
                            title: '公司社保',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.gz2 * 100) / 100
                            }
                        },{
                            field: 'gz3',
                            minWidth: 130,
                            title: '个人社保',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.gz3 * 100) / 100
                            }
                        }, {
                            field: 'gz4',
                            minWidth: 130,
                            title: '个税',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.gz4 * 100) / 100
                            }
                        },{
                            field: 'jx1',
                            minWidth: 130,
                            title: '固定绩效',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx1 * 100) / 100
                            }
                        }, {
                            field: 'jx2',
                            minWidth: 130,
                            title: '驻外补贴',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx2 * 100) / 100
                            }
                        }, {
                            field: 'jx3',
                            minWidth: 130,
                            title: '岗位津贴',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx3* 100) / 100
                            }
                        }, {
                            field: 'jx4',
                            minWidth: 130,
                            title: '考核绩效',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.jx4 * 100) / 100
                            }
                        }, {
                            field: 'jx5',
                            minWidth: 130,
                            title: '积分绩效',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx5 * 100) / 100
                            }
                        }, {
                            field: 'jx6',
                            minWidth: 130,
                            title: '审核绩效',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx6 * 100) / 100
                            }
                        }, {
                            field: 'jx7',
                            minWidth: 130,
                            title: '人事扣款',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx7 * 100) / 100
                            }
                        },  {
                            field: 'jx8',
                            minWidth: 130,
                            title: '其他补扣款',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx8 * 100) / 100
                            }
                        }, {
                            field: 'dk1',
                            minWidth: 150,
                            title: '病史费(含复印费)',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.dk1 * 100) / 100
                            }
                        }, {
                            field: 'dk2',
                            minWidth: 150,
                            title: '住院排查费用',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.dk2 * 100) / 100
                            }
                        }, {
                            field: 'dk3',
                            minWidth: 150,
                            title: '门诊排查费用',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.dk3 * 100) / 100
                            }
                        }, {
                            field: 'dk4',
                            minWidth: 150,
                            title: '体检报告打印费',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.dk4 * 100) / 100
                            }
                        }, {
                            field: 'dk5',
                            minWidth: 260,
                            title: '跨地市交通费(汽车、火车、飞机)',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.dk5 * 100) / 100
                            }
                        }, {
                            field: 'dk6',
                            minWidth: 180,
                            title: '跨地市交通费(自驾)',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.dk6 * 100) / 100
                            }
                        }, {
                            field: 'dk7',
                            minWidth: 130,
                            title: '作业市内交通费',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.dk7 * 100) / 100
                            }
                        }, {
                            field: 'dk8',
                            minWidth: 130,
                            title: '作业住宿费',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.dk8 * 100) / 100
                            }
                        },{
                            field: 'qdMoney',
                            minWidth: 130,
                            title: '渠道作业费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.qdMoney * 100) / 100
                            }
                        }, {
                            field: 'bxCb1',
                            minWidth: 160,
                            title: '供应商作业费',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.bxCb1 * 100) / 100
                            }
                        },
                        ]
                    ]
                    showChild({
                        title: '主营业务成本',
                        cols: _colsChild,
                        data: [obj.data.zc1Detail]
                    })
                }else if (obj.event == 'glfy') {
                    var _colsChild = [[
                        {
                            field: 'gz1',
                            minWidth: 130,
                            title: '实发工资',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.gz1 * 100) / 100
                            }
                        }, {
                            field: 'gz2',
                            minWidth: 130,
                            title: '公司社保',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.gz2 * 100) / 100
                            }
                        },{
                            field: 'gz3',
                            minWidth: 130,
                            title: '个人社保',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.gz3 * 100) / 100
                            }
                        }, {
                            field: 'gz4',
                            minWidth: 130,
                            title: '个税',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.gz4 * 100) / 100
                            }
                        },{
                            field: 'jx1',
                            minWidth: 130,
                            title: '固定绩效',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx1 * 100) / 100
                            }
                        }, {
                            field: 'jx2',
                            minWidth: 130,
                            title: '驻外补贴',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx2 * 100) / 100
                            }
                        }, {
                            field: 'jx3',
                            minWidth: 130,
                            title: '岗位津贴',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx3* 100) / 100
                            }
                        }, {
                            field: 'jx4',
                            minWidth: 130,
                            title: '考核绩效',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.jx4 * 100) / 100
                            }
                        }, {
                            field: 'jx5',
                            minWidth: 130,
                            title: '积分绩效',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx5 * 100) / 100
                            }
                        }, {
                            field: 'jx6',
                            minWidth: 130,
                            title: '审核绩效',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx6 * 100) / 100
                            }
                        }, {
                            field: 'jx7',
                            minWidth: 130,
                            title: '人事扣款',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx7 * 100) / 100
                            }
                        },  {
                            field: 'jx8',
                            minWidth: 130,
                            title: '其他补扣款',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx8 * 100) / 100
                            }
                        },{
                            field: 'bxGl1',
                            minWidth: 260,
                            title: '管理交通费',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.bxGl1 * 100) / 100
                            }
                        }, {
                            field: 'bxGl2',
                            minWidth: 180,
                            title: '管理住宿费',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.bxGl2 * 100) / 100
                            }
                        }, {
                            field: 'bxGl3',
                            minWidth: 130,
                            title: '管理差旅费',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.bxGl3 * 100) / 100
                            }
                        }, {
                            field: 'bxGl4',
                            minWidth: 130,
                            title: '管理团建费',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.bxGl4 * 100) / 100
                            }
                        }, {
                            field: 'bxGl5',
                            minWidth: 150,
                            title: '管理培训费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl5 * 100) / 100
                            }
                        }, {
                            field: 'bxGl6',
                            minWidth: 150,
                            title: '体检费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl6 * 100) / 100
                            }
                        }, {
                            field: 'bxGl7',
                            minWidth: 200,
                            title: '年会及纪念日活动费',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.bxGl7 * 100) / 100
                            }
                        }, {
                            field: 'bxGl8',
                            minWidth: 150,
                            title: '招聘费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl8 * 100) / 100
                            }
                        }, {
                            field: 'bxGl9',
                            minWidth: 150,
                            title: '财务手续费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl9 * 100) / 100
                            }
                        }, {
                            field: 'bxGl10',
                            minWidth: 150,
                            title: '融资利息',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl10 * 100) / 100
                            }
                        }, {
                            field: 'bxGl11',
                            minWidth: 150,
                            title: '融资服务费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl11 * 100) / 100
                            }
                        }, {
                            field: 'bxGl12',
                            minWidth: 150,
                            title: '审计费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl12 * 100) / 100
                            }
                        },{
                            field: 'bxGl13',
                            minWidth: 150,
                            title: '软件开发费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl13 * 100) / 100
                            }
                        }, {
                            field: 'bxGl14',
                            minWidth: 150,
                            title: '职场租金',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl14 * 100) / 100
                            }
                        }, {
                            field: 'bxGl15',
                            minWidth: 150,
                            title: '职场装修费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl15 * 100) / 100
                            }
                        }, {
                            field: 'bxGl16',
                            minWidth: 150,
                            title: '职场水电费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl16 * 100) / 100
                            }
                        }, {
                            field: 'bxGl17',
                            minWidth: 150,
                            title: '通信费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl17 * 100) / 100
                            }
                        }, {
                            field: 'bxGl18',
                            minWidth: 150,
                            title: '办公物资',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl18 * 100) / 100
                            }
                        }, {
                            field: 'bxGl19',
                            minWidth: 150,
                            title: '快递费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl19 * 100) / 100
                            }
                        }, {
                            field: 'bxGl20',
                            minWidth: 150,
                            title: '电脑租赁费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl20 * 100) / 100
                            }
                        }, {
                            field: 'bxGl21',
                            minWidth: 150,
                            title: '打印和复印',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl21 * 100) / 100
                            }
                        },{
                            field: 'bxGl22',
                            minWidth: 150,
                            title: '异地补贴',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl22 * 100) / 100
                            }
                        }, {
                            field: 'bxGl23',
                            minWidth: 150,
                            title: '管理招待费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl23 * 100) / 100
                            }
                        }, {
                            field: 'bxGl24',
                            minWidth: 150,
                            title: '加班餐费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl24 * 100) / 100
                            }
                        }, {
                            field: 'bxGl25',
                            minWidth: 150,
                            title: '印刷费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl25 * 100) / 100
                            }
                        }, {
                            field: 'bxGl26',
                            minWidth: 150,
                            title: '宣传费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl26 * 100) / 100
                            }
                        }, {
                            field: 'bxGl27',
                            minWidth: 150,
                            title: '服务费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl27* 100) / 100
                            }
                        }, {
                            field: 'bxGl28',
                            minWidth: 150,
                            title: '会务费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl28 * 100) / 100
                            }
                        }, {
                            field: 'bxGl29',
                            minWidth: 150,
                            title: '维修费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl29 * 100) / 100
                            }
                        },  {
                            field: 'bxGl30',
                            minWidth: 150,
                            title: '代理费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl30 * 100) / 100
                            }
                        }, {
                            field: 'bxGl31',
                            minWidth: 150,
                            title: '其他管理费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl31 * 100) / 100
                            }
                        }, {
                            field: 'bxGl32',
                            minWidth: 150,
                            title: '品牌使用费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl32 * 100) / 100
                            }
                        }, {
                            field: 'bxGl33',
                            minWidth: 150,
                            title: '业务管理费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl33 * 100) / 100
                            }
                        }, {
                            field: 'bxGl34',
                            minWidth: 150,
                            title: '财务管理费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl34 * 100) / 100
                            }
                        }, {
                            field: 'bxGl35',
                            minWidth: 150,
                            title: '商业保险费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxGl35 * 100) / 100
                            }
                        }
                    ]]
                    showChild({
                        title: '管理费用',
                        cols: _colsChild,
                        data: [obj.data.zc3Detail]
                    })
                }
                else if (obj.event == 'xsfy') {
                    var _colsChild = [[
                        {
                            field: 'gz1',
                            minWidth: 130,
                            title: '实发工资',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.gz1 * 100) / 100
                            }
                        }, {
                            field: 'gz2',
                            minWidth: 130,
                            title: '公司社保',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.gz2 * 100) / 100
                            }
                        },{
                            field: 'gz3',
                            minWidth: 130,
                            title: '个人社保',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.gz3 * 100) / 100
                            }
                        }, {
                            field: 'gz4',
                            minWidth: 130,
                            title: '个税',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.gz4 * 100) / 100
                            }
                        },{
                            field: 'jx1',
                            minWidth: 130,
                            title: '固定绩效',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx1 * 100) / 100
                            }
                        }, {
                            field: 'jx2',
                            minWidth: 130,
                            title: '驻外补贴',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx2 * 100) / 100
                            }
                        }, {
                            field: 'jx3',
                            minWidth: 130,
                            title: '岗位津贴',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx3* 100) / 100
                            }
                        }, {
                            field: 'jx4',
                            minWidth: 130,
                            title: '考核绩效',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.jx4 * 100) / 100
                            }
                        }, {
                            field: 'jx5',
                            minWidth: 130,
                            title: '积分绩效',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx5 * 100) / 100
                            }
                        }, {
                            field: 'jx6',
                            minWidth: 130,
                            title: '审核绩效',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx6 * 100) / 100
                            }
                        }, {
                            field: 'jx7',
                            minWidth: 130,
                            title: '人事扣款',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx7 * 100) / 100
                            }
                        },  {
                            field: 'jx8',
                            minWidth: 130,
                            title: '其他补扣款',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.jx8 * 100) / 100
                            }
                        },
                        {
                            field: 'bxXs1',
                            minWidth: 150,
                            title: '佣金',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxXs1 * 100) / 100
                            }
                        }, {
                            field: 'bxXs2',
                            minWidth: 150,
                            title: '礼品费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxXs2 * 100) / 100
                            }
                        }, {
                            field: 'xsMone3',
                            minWidth: 150,
                            title: '招投标费',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.bxXs3 * 100) / 100
                            }
                        }, {
                            field: 'bxXs4',
                            minWidth: 150,
                            title: '广告费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxXs4 * 100) / 100
                            }
                        }, {
                            field: 'bxXs5',
                            minWidth: 150,
                            title: '赞助费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxXs5 * 100) / 100
                            }
                        }, {
                            field: 'bxXs6',
                            minWidth: 150,
                            title: '商务会务费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxXs6 * 100) / 100
                            }
                        }, {
                            field: 'bxXs7',
                            minWidth: 150,
                            title: '商务交通费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxXs7 * 100) / 100
                            }
                        }, {
                            field: 'bxXs8',
                            minWidth: 150,
                            title: '商务住宿费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxXs8 * 100) / 100
                            }
                        }, {
                            field: 'bxXs9',
                            minWidth: 150,
                            title: '商务差旅费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxXs9 * 100) / 100
                            }
                        }, {
                            field: 'bxXs10',
                            minWidth: 150,
                            title: '商务招待费',
                            sort: true,

                            templet: function (d) {
                                return Math.round(d.bxXs10 * 100) / 100
                            }
                        }
                    ]]
                    showChild({
                        title: '销售费用',
                        cols: _colsChild,
                        data: [obj.data.zc2Detail]
                    })
                }
            })
            $('.layui-export').off('click').on('click',function (e) {
                var _this = $('.layui-export')
                _this.attr('disabled',true)
                soulTable.export(myTable,{
                    filename: '产品经营分析(业务主营)报表.xlsx'
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