<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>任务分布报表</title>
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

        .layui-form-item {
            margin: 0!important;
        }


        label.layui-form-label {
            width: 106px;
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
    <input type="hidden" value='${params.consignorsJson}' id="consignorsJson" />

    <div class="layui-form searchs" lay-filter="search">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">互助平台</label>
                <div class="layui-input-inline">
                    <div id="entrustOrgIds" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">区域</label>
                <div class="layui-input-inline">
                    <div id="area" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">案件状态</label>
                <div class="layui-input-inline">
                    <div id="surveyState" class="selectMul"></div>
                </div>
            </div>

            <div class="layui-inline">
                <div class="d-flex-wrap">
                    <label class="layui-form-label">时间</label>
                    <div class="d-flex-wrap">
                        <div class="data-types">
                            <div class="data-type" data-id="upMonth">上月</div>
                            <div class="data-type" data-id="yesterday">昨天</div>
                            <div class="data-type" data-id="today">今天</div>
                            <div class="data-type" data-id="curWeek">本周</div>
                            <div class="data-type active" data-id="curMonth">本月</div>
                            <div class="data-type" data-id="all">全部</div>
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
    var _width = $(document).width() * 0.9


    var first = '',
        second = ''

    var searchType = 'curMonth'
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

    //调查员数量
    var _len = 0

    var startTime = '',
        endTime = ''
    layui.use(['form', 'table', 'soulTable', 'xmSelect', 'laydate', 'layer'], function () {
        var soulTable = layui.soulTable,
            table = layui.table,
            form = layui.form,
            xmSelect = layui.xmSelect,
            laydate = layui.laydate,
            layer = layui.layer;

        var demo1 = xmSelect.render({
            el: '#entrustOrgIds',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            toolbar: {
                show: true
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
        var demo4 = xmSelect.render({
            el: '#surveyState',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',

            radio: true,
            model: {
                label: {
                    type: 'block',
                    block: {
                        //是否显示删除图标
                        showIcon: false,
                    }
                }
            },
            data: [{
                name: '保司终审通过',
                value: 28
            },
                {
                    name: '平台复审通过',
                    value: 24
                }
            ]
        })
        var demo7 = xmSelect.render({
            el: '#area',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            toolbar: {
                show: true
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
            data: [{
                name: '直辖市(市区)',
                value: '1_5',
            },
                {
                    name: '直辖市(郊区)',
                    value: '1_6',
                },
                {
                    name: '省会',
                    value: '2_0',
                },
                {
                    name: '地级市',
                    value: '3_0',
                },
                {
                    name: '县级市',
                    value: '4_0',
                },
            ]
        })

        demo4.setValue([24])

        var consignorsJson = $("#consignorsJson").val();
        consignorsJson = JSON.parse(consignorsJson);
        filterJson(demo1, consignorsJson,'id','name',false, false)

        function filterJson(demo, newJson,id, name, flag, selected){
            var demoList = [],
                demoValues = []
            newJson.map(function(cur){
                var _name = name ? cur[name] : cur.name
                var _id = id ? cur[id] : cur.id
                var param ={
                    name: _name,
                    value: _id,
                }
                if (selected){
                    Object.assign(param,{selected: true})
                }
                demoList.push(param)
                demoValues.push(_id)
            })
            if (!flag){
                demo.update({
                    data: demoList
                })
            }else {
                return demoList
            }
            setTimeout(function(){
                $('.xm-option-content').each(function(){
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },200)
        }

        var initVals = setDate('curMonth')
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

        form.on('submit(submit)', function (data) {
            var vals = data.field
            delete vals.select;
            Object.assign(vals, {
                menuCode:'taskDistribution',
                platform: demo1.getValue('valueStr'),
                caseStatus: demo4.getValue('valueStr'),
                region: demo7.getValue('valueStr') ,
                startTime: $('#startTime').val(),
                endTime: $('#endTime').val(),
                searchType: searchType
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
            var _html = '<div class="">' + d + '%</div>'
            if (plus) {
                return _html
            }
            if (d > 0) {
                _html = '<div class="color1">' + d + '%</div>'
            } else if (d < 0) {
                _html = '<div class="color2">' + d + '%</div>'

            }
            return _html

        }

        var _cols1 = [
            [{
                field: 'taskClassification',
                minWidth: 170,
                title: '任务分类',
                totalRowText: '合计'
            },
                {
                    field: 'taskNumber',
                    minWidth: 270,
                    title: '任务数',
                    sort: true,
                    totalRow: true,
                },
                {
                    field: 'proportion',
                    minWidth: 155,
                    title: '占比',
                    sort: true,
                    templet: function (d) {
                        var _d = Math.round(d.proportion * 100) / 100
                        var _html = '<div class="">' + _d + '%</div>'
                        return _html
                    }
                }
            ]
        ]

        var _cols = _cols1

        var param = {
             menuCode:'taskDistribution',
             platform: '',
             caseStatus: '24',
             caseType:'',
             startTime: initVals[0],
             endTime: initVals[1],
             searchType: 'curMonth'
        }
        setTable(_cols, param)

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
                even: true,cols: _cols,
                page: false,
                limit: 200,
                height: fullH,
                drag: false,
                url: '${ctx}/survey/hzReport/getDetail',
                where: param,
                parseData: function(res){
                    return  {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results.list
                    }
                },
                totalRow: true,
                fixTotal: true,
                initSort: {
                    field: 'taskNumber',
                    type: 'desc'
                },
                done: function () {
                    soulTable.render(this)
                    $('button.ll-submit').removeAttr('disabled')
                    showTitle('.table-content .layui-table-view .layui-table-box .layui-table-header:first table thead tr th')

                }
            })
            table.on('tool(test)', function (obj, pobj) {
                onRowEvent(obj, pobj)
            })

            $('.layui-export').off('click').on('click',function (e) {
                var _this = $('.layui-export')
                _this.attr('disabled',true)
                soulTable.export(myTable,{
                    filename: '任务分布报表.xlsx',
                })
                setTimeout(function () {
                    _this.removeAttr('disabled')
                },5000)
            })
        }

        function onRowEvent(obj, pobj) {
            if (obj.event == 'getList'){
                openIframe()
            }
        }

        function showTitle(thsJ, child, iter) {
            var icon_about = '<span class="icon-about">'
            var ths = $(thsJ)
            var startTime = dateFormat($('#startTime').val(), 'yyyy年MM月dd日')
            var endTime = dateFormat($('#endTime').val(), 'yyyy年MM月dd日')
            ths.eq(1).find('.layui-table-cell span:first').after(icon_about)
            ths.eq(2).find('.layui-table-cell span:first').after(icon_about)

            var _title4 =demo4.getValue('nameStr')+ '时间在'+startTime+ '至' + endTime +'的互助案件中，不同类型的任务的数量'
            ths.eq(1).find('.layui-table-cell').attr('title', _title4)

            var _title5 = demo4.getValue('nameStr')+ '时间在'+startTime+ '至' + endTime +'的互助案件中，不同类型的任务的数量占全部任务数量的百分比'
            ths.eq(2).find('.layui-table-cell').attr('title', _title5)



        }
    })
</script>

</body>

</html>