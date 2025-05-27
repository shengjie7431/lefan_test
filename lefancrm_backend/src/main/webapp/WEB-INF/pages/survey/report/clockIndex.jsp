<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>打卡报表</title>
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
            width: 84px;
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

        .clock-content{
            width: 100%;
            display: flex;
            align-items: center;
        }
        .clock-num{
            width: 64px;
            display: inline-block;
            color: #3ba9ff;
        }
        .map-icon{
            display: inline-block;
            width: 20px;
            height: 20px;
            background: url(data:image/png;base64,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);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }
    </style>
</head>

<body>
<div class="main">

    <div class="layui-form searchs" lay-filter="search">
        <input type="hidden" value='${franchiseesJson}'  id="franchiseesJson" />
        <input type="hidden" value='${investigatorsJson}' id="investigatorsJson" />
        <input type="hidden" value='${scoreRole}' id="scoreRole" /> <%--provincialManger省级机构负责人，areaManger片区机构负责人，manger平台人员--%>

        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">调查机构</label>
                <div class="layui-input-inline">
                    <div id="surveyOrgId" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">调查员</label>
                <div class="layui-input-inline">
                    <div id="surveyInvestigators" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 124px;display: flex;align-items: center" >离职截止日期 <span class="icon-about" title="不看【离职截止日期】前离职的调查员"></span> </label>
                <div class="layui-input-inline" style="width: 120px;">
                    <input type="text" class="layui-input paramTime" readonly id="endQuitTime"
                           placeholder="请选择日期" style="width: 120px;height: 32px">
                </div>
            </div>

            <div class="layui-inline">
                <div class="d-flex-wrap">
                    <label class="layui-form-label">时间</label>
                    <div class="d-flex-wrap">
                        <div class="data-types">
                            <div class="data-type" data-id="upMonth">上月</div>
                            <div class="data-type" data-id="yesterday">昨天</div>
                            <div class="data-type active" data-id="today">今天</div>
                            <div class="data-type" data-id="curWeek">本周</div>
                            <div class="data-type" data-id="curMonth">本月</div>
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
            <div class="layui-inline">
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

    var first = '',
        second = ''

    var searchType = 'today'
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
            vals = [y + '-' + PrefixInteger(m, 2) + '-' + d, y + '-' + PrefixInteger(m, 2) + '-' + d]
        } else if (id == 'curWeek') {
            var minusDay = w != 0 ? w - 1 : 6;
            var monday = new Date(today.getTime() - (minusDay * millisecond));
            var sunday = new Date(monday.getTime() + (6 * millisecond));
            vals = [dateFormat(monday), y + '-' + m + '-' + d]
        } else if (id == 'curMonth') {
            m = PrefixInteger(m, 2)
            d = PrefixInteger(d, 2)
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

    //调查员数量
    var _len = 3
    var startTime = '', endTime = ''

    var franchiseesJson = $("#franchiseesJson").val();
    console.log(franchiseesJson)
    if (franchiseesJson == 'null'){
        franchiseesJson = []
    }else {
        franchiseesJson = JSON.parse(franchiseesJson)
    }

    layui.use(['form', 'table', 'soulTable', 'xmSelect', 'laydate', 'jquery'], function () {
        var soulTable = layui.soulTable,
            table = layui.table,
            form = layui.form,
            xmSelect = layui.xmSelect,
            laydate = layui.laydate,
            $ = layui.jquery

        var demo2 = xmSelect.render({
            el: '#surveyOrgId',
            theme: {
                color: '#3BA9FF'
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
            on: function (data) {
                //arr:  当前多选已选中的数据
                var arr = data.arr;
                var valArr = []
                arr.filter(function (cur, i) {
                    valArr.push(cur.value)
                })
                $.ajax({
                    url: '${ctx}/baseSurvey/selectInfoByRelationId',
                    data: {
                        'surveyCode': 'investigator',
                        'btnCode':3000,
                        'surveyOrgIds':valArr.join(',')
                    },
                    success: function (res) {
                        res = JSON.parse(res)
                        if (res.isSuccess){
                            filterJson(demo3, res.results,'userId','realName',false, true)
                            // Object.assign(paramsSubmit, {
                            //     menuCode: 'clock',
                            //     dataType: 'clock',
                            //     surveyOrgIds: demo2.getValue('valueStr'),
                            //     surveyUserIds: demo3.getValue('valueStr'),
                            //     endQuitTime: $('#endQuitTime').val(),
                            //     startTime:initVals[0],
                            //     endTime: initVals[1],
                            //     searchType: searchType
                            // })
                            // setTable(_cols, paramsSubmit)
                        }
                    }

                })

            },
            model: {
                label: {
                    type: 'templateSelf', //自定义与下面的对应
                    templateSelf: {
                        template(data, sels) {
                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +='<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
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
            el: '#surveyInvestigators',
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
                    type: 'templateSelf', //自定义与下面的对应
                    templateSelf: {
                        template(data, sels) {
                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=   '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })


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


        filterJson(demo2, franchiseesJson,'id', 'name', false, true)
        //
        // var investigatorsJson = $("#investigatorsJson").val();
        // investigatorsJson = JSON.parse(investigatorsJson);
        // _len = investigatorsJson.length
        // filterJson(demo3, investigatorsJson,'userId','realName',false, true)
        var entryVals = setDate('upMonth')

        endQuitTime = laydate.render({
            elem: '#endQuitTime',
            value: entryVals[0],
            isInitValue: true,
        });

        var initVals = setDate('today')
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

        var paramsSubmit = {
            menuCode: 'clock',
            dataType: 'clock',
            surveyOrgIds: '',
            surveyUserIds: '',
            startTime:initVals[0],
            endTime: initVals[1],
            endQuitTime: entryVals[0],
            searchType : 'today',
        }
        form.on('submit(submit)', function (data) {
            Object.assign(paramsSubmit, {
                menuCode: 'clock',
                dataType: 'clock',
                surveyOrgIds: demo2.getValue('valueStr'),
                surveyUserIds: demo3.getValue('valueStr'),
                endQuitTime: $('#endQuitTime').val(),
                startTime: $('#startTime').val(),
                endTime: $('#endTime').val(),
                searchType: searchType
            })
            reloadTable(_cols, paramsSubmit)
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
            var _d = Math.round(d*10000)/100
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

        var _cols = [
            [{
                field: 'surveyOrgName',
                minWidth: 140,
                title: '调查机构',
            },{
                field: 'surveyUserName',
                minWidth: 120,
                title: '调查员',
                totalRowText: '合计'
            },{
                field: 'clockNum',
                minWidth: 120,
                title: '打卡总次数',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    var _html = '<div class="clock-content">'+
                        '<div class="clock-num" lay-event="clocknum">'+d.clockNum+'</div>'+
                        '<div class="map-icon" lay-event="tolist"></div>'+
                        '</div>'
                    return _html
                }
            },{
                field: 'avgEveryDayClockNum',
                minWidth: 140,
                title: '平均每日打卡次数',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.ceil(d.avgEveryDayClockNum * 100) / 100
                }
            },{
                field: 'avgCaseClockNum',
                minWidth: 140,
                title: '平均每案打卡次数',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return Math.ceil(d.avgCaseClockNum * 100) / 100
                }
            },{
                field: 'firstClockTimestamp',
                minWidth: 150,
                title: '首次打卡时间',
                sort: true,
                totalRow: true,
                templet: function (d) {
                    return d.firstClockTime || ''
                }
            },{
                field: 'lackClockNum',
                minWidth: 140,
                title: '缺卡天数',
                sort: true,
                totalRow: true,
            },{
                field: 'totalMoney',
                minWidth: 140,
                title: '合计报销金额',
                sort: true,
                totalRow: true,
            },{
                field: 'distance',
                minWidth: 140,
                title: '公里数',
                sort: true,
                totalRow: true,
            },{
                field: 'tempDistance',
                minWidth: 140,
                title: '公里数/案件方向数',
                sort: true,
                totalRow: true,
            },{
                field: 'caseNum',
                minWidth: 120,
                title: '案件数',
                sort: true,
                totalRow: true,
            },{
                field: 'tempMoney',
                minWidth: 200,
                title: '打卡总次数/案件方向数',
                sort: true,
                totalRow: true,
            },{
                field: 'avgCaseMoney',
                minWidth: 140,
                title: '件均报销金额',
                sort: true,
                totalRow: true,
            },{
                field: 'money1',
                minWidth: 120,
                title: '市内交通费',
                sort: true,
                totalRow: true,
            },{
                field: 'money2',
                minWidth: 150,
                title: '病史费（含复印费）',
                sort: true,
                totalRow: true,
            },{
                field: 'money3',
                minWidth: 140,
                title: '住院排查费用',
                sort: true,
                totalRow: true,
            },{
                field: 'money4',
                minWidth: 140,
                title: '门诊排查费用',
                sort: true,
                totalRow: true,
            },{
                field: 'money5',
                minWidth: 160,
                title: '体检报告打印费',
                sort: true,
                totalRow: true,
            },{
                field: 'money6',
                minWidth: 160,
                title: '住宿费',
                sort: true,
                totalRow: true,
            },{
                field: 'money7',
                minWidth: 210,
                title: '跨地市交通费（汽车、火车、飞机）',
                sort: true,
                totalRow: true,
            },{
                field: 'money8',
                minWidth: 190,
                title: '跨地市交通费（自驾）',
                sort: true,
                totalRow: true,
            },{
                field: 'money9',
                minWidth: 120,
                title: '其他费用',
                sort: true,
                totalRow: true,
            }
            ]
        ]


        setTable(_cols, paramsSubmit)

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
                limit: 200000,
                height: fullH,
                drag: false,
                url: '${ctx}/survey/report/getData',
                totalRow: true,
                where: param,
                parseData: function(res){
                    return  {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results.clocks,
                        "totalRow": {
                            clockNum: getFixed(res.results.totalData.total1) || '0',
                            avgEveryDayClockNum: getFixed(res.results.totalData.total2) || '0',
                            avgCaseClockNum: getFixed(res.results.totalData.total3) || '0',
                            firstClockTimestamp: res.results.totalData.total4,
                            lackClockNum: getFixed(res.results.totalData.total5) || '0',
                            totalMoney: getFixed(res.results.totalData.total6) || '0',
                            distance: getFixed(res.results.totalData.distance) || '0',
                            tempDistance: getFixed(res.results.totalData.tempDistance) || '0',
                            caseNum: getFixed(res.results.totalData.total7) || '0',
                            tempMoney: getFixed(res.results.totalData.total18) || '0',
                            avgCaseMoney: getFixed(res.results.totalData.total8) || '0',
                            money1: getFixed(res.results.totalData.total9) || '0',
                            money2: getFixed(res.results.totalData.total10) || '0',
                            money3: getFixed(res.results.totalData.total11) || '0',
                            money4: getFixed(res.results.totalData.total12) || '0',
                            money5: getFixed(res.results.totalData.total13) || '0',
                            money6: getFixed(res.results.totalData.total14) || '0',
                            money7: getFixed(res.results.totalData.total15) || '0',
                            money8: getFixed(res.results.totalData.total16) || '0',
                            money9: getFixed(res.results.totalData.total17) || '0',
                        }
                    }
                },

                // initSort: {
                //     field: 'totalMoney',
                //     type: 'desc'
                // },

                done: function () {
                    $('button.ll-submit').removeAttr('disabled')
                    soulTable.render(this)
                    showTitle('.table-content .layui-table-view .layui-table-box .layui-table-header:first table thead tr th:eq(5)')

                }
            })
            table.on('tool(test)', function (obj, pobj) {
                onRowEvent(obj, pobj)
            })

            $('.layui-export').off('click').on('click',function (e) {
                var _this = $('.layui-export')
                _this.attr('disabled',true)
                soulTable.export(myTable,{
                    filename: '打卡报表.xlsx'
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
        function onRowEvent(obj, pobj, name) {
            if (obj.event == "clocknum") {
                // 处理你的业务逻辑
                var url = "${ctx}/fee/list?userId=" + obj.data.surveyUserId + "&createTime=" + paramsSubmit.startTime + "&startTime=" + paramsSubmit.startTime + "&endTime=" + paramsSubmit.endTime+"&menuCode=preClockList";
                parent.parent.parent.addTab("案件打卡列表",url,true);
            }else if (obj.event == 'tolist'){
                var url = "${ctx}/fee/list?userId=" + obj.data.surveyUserId + "&createTime=" + paramsSubmit.startTime+"&menuCode=preClockDetails";
                parent.parent.parent.addTab("案件打卡足迹",url,true);
            }
        }

        function showTitle(thsJ, child, iter) {
            var icon_about = '<span class="icon-about">'
            var ths = $(thsJ)
            ths.find('.layui-table-cell span:first').after(icon_about)
            var _title3 = '当天首次打卡的时间，若筛选的时间为一段时间，如上月，则指上月每天首次打卡的平均时间'
            ths.eq(0).find('.layui-table-cell').attr('title', _title3)


        }

    })

</script>

</body>

</html>