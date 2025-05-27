<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>考核指标报表</title>
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
        .layui-layer.layui-layer-page{
            width:98%!important;
            left: 1%!important;
            top: 20px!important;
        }
        .layui-table-body{
            overflow-y: overlay;
        }

        #dialogId{
            z-index: 198910170;
            position: fixed;
        }
        .layui-icon-spread-left{
            color: #3BA9FF;
        }

        .d_type_a {
            color: #FF3D00;
        }

        .d_type_b {
            color: #FB8C00;
        }
        .layui-layer-min, .layui-layer-max{
            display: none!important
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

    </style>
</head>

<body>
<div class="main">
    <input type="hidden" value='${params.franchiseesJson}' id="franchiseesJson"/>
    <input type="hidden" value='${params.investigatorsJson}' id="investigatorsJson"/>
    <input type="hidden" value='${params.consignorsJson}' id="consignorsJson" />
    <input type="hidden" value='${scoreRole}'
           id="scoreRole"/> <%--provincialManger省级机构负责人，areaManger片区机构负责人，manger平台人员--%>
    <div class="layui-form searchs" lay-filter="search">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">互助平台</label>
                <div class="layui-input-inline">
                    <div id="entrustOrgIds" class="selectMul"></div>
                </div>
            </div>
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
            <%--                <div class="layui-inline">--%>
            <%--                    <label class="layui-form-label">案件状态</label>--%>
            <%--                    <div class="layui-input-inline">--%>
            <%--                        <div id="surveyState" class="selectMul"></div>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <c:if test="${scoreRole == 'manger'}">
                <div class="layui-inline">
                    <label class="layui-form-label">案件类型</label>
                    <div class="layui-input-inline">
                        <div id="caseState" class="selectMul"></div>
                    </div>
                </div>
            </c:if>

            <div class="layui-inline">
                <label class="layui-form-label">机构案件类型</label>
                <div class="layui-input-inline">
                    <div id="orgCaseState" class="selectMul"></div>
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
                            style="width: 100px">查询 <i class="layui-icon layui-icon-search"></i></button>
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

            m = PrefixInteger(m, 2)
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
    var franchiseesJson = $("#franchiseesJson").val();
    franchiseesJson = JSON.parse(franchiseesJson);
    layui.use(['form', 'table', 'soulTable', 'xmSelect', 'laydate'], function () {
        var soulTable = layui.soulTable,
            table = layui.table,
            form = layui.form,
            xmSelect = layui.xmSelect,
            laydate = layui.laydate,
            $ = layui.$;

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
            on: function (data) {
                //arr:  当前多选已选中的数据
                var arr = data.arr;
                var valArr = []
                arr.filter(function (cur, i) {
                    valArr.push(cur.value)
                })
                //更新数据源 hou
                $.ajax({
                    url: '${ctx}/baseSurvey/selectInfoByRelationId',
                    data: {
                        'surveyCode': 'investigator',
                        'btnCode': 3000,
                        'surveyOrgIds': valArr.join(',')
                    },
                    success: function (res) {
                        res = JSON.parse(res)
                        if (res.isSuccess) {
                            _len = res.results.length
                            filterJson(demo3, res.results, 'userId', 'realName', false, false)
                        }
                    }
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
            data: filterJson(demo2, franchiseesJson, 'id', 'name', true, false)
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

        var demo5 = ''
        var scoreRole = $("#scoreRole").val();
        if (scoreRole == 'manger') {
            demo5 = xmSelect.render({
                el: '#caseState',
                theme: {
                    color: '#3BA9FF',
                },
                size: 'small',
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
                                            '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                    })
                                    return _html
                                }
                            }
                        },
                    }
                },
                data: [{
                    name: '全部',
                    value: '0'
                }, {
                    name: '单点',
                    value: 1
                },
                    {
                        name: '单点+单点',
                        value: 2
                    },
                    {
                        name: '全案',
                        value: 3
                    },
                    {
                        name: '全案+单点',
                        value: 4
                    },
                    {
                        name: '全案+全案',
                        value: 5
                    },
                ]
            })
            demo5.setValue([0])
        }
        var demo6 = xmSelect.render({
            el: '#orgCaseState',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
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
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: [{
                name: '全部',
                value: '0'
            }, {
                name: '单点',
                value: 1
            },
                {
                    name: '全案',
                    value: 2
                }
            ]
        })

        //初始化赋值
        var consignorsJson = $("#consignorsJson").val();
        consignorsJson = JSON.parse(consignorsJson);
        filterJson(demo1, consignorsJson,'id','name',false, false)
        demo6.setValue([0])

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

        var investigatorsJson = $("#investigatorsJson").val();
        investigatorsJson = JSON.parse(investigatorsJson);
        _len = investigatorsJson.length
        filterJson(demo3, investigatorsJson, 'userId', 'realName', false, false)

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
        var paramSubmit = {
            menuCode: 'assessmentIndex',
            searchType: 'curMonth',
            surveyState: '24',
            entrustOrgIds: demo1.getValue('valueStr'),
            orgCaseState: '0',
            caseState: '0',
            orgCaseState: '0'
        }
        form.on('submit(submit)', function (data) {

            var vals = data.field
            delete vals.select;
            var claimsman = demo3.getValue('valueStr')
            claimsman = claimsman ? claimsman.split(',') : []
            Object.assign(vals, {
                menuCode: 'assessmentIndex',
                entrustOrgIds: demo1.getValue('valueStr'),
                surveyOrgIds: demo2.getValue('valueStr'),
                surveyInvestigators: claimsman.length == 0 ? '' : demo3.getValue('valueStr'),
                caseState: scoreRole == 'manger' ? demo5.getValue('valueStr') : 0,
                orgCaseState: demo6.getValue('valueStr'),
                startTime: $('#startTime').val(),
                endTime: $('#endTime').val(),
                searchType: searchType
            })
            paramSubmit = vals
            if (!claimsman || claimsman.length == 0) {
                reloadTable(_cols, vals)
            } else {
                reloadTable(_cols2, vals)
            }
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

        var _cols1 = [
            [{
                title: '',
                field: 'id',
                show: 2,
                width: 60,
                lazy: true,
                owspan: 2,
                rowspan: 2,
                fixed: true,
                children: function (row) {
                    if (row.orgType == 1) {
                        delete paramSubmit.clickPqSurv;
                        Object.assign(paramSubmit, {clickPq: 'yes',surveyOrgIds: row.surveyOrgId})
                        return [{
                            title: function (row) {
                                first = row.surveyOrgName
                                return row.surveyOrgName + ' - 片区机构列表'
                            },
                            url: '${ctx}/survey/hzReport/getDetail',
                            where:paramSubmit,
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
                            page: false,
                            limit: 200,
                            height: _height * 0.96,
                            even: true,cols: [
                                [{
                                    title: '',
                                    show: 2,
                                    width: 60,
                                    lazy: true,
                                    owspan: 2,
                                    rowspan: 2,
                                    fixed:true,
                                    children: function (row) {
                                        Object.assign(paramSubmit,{clickPqSurv: 'yes',surveyOrgIds: row.surveyOrgId,surveyAreaId:row.surveyAreaId})
                                        return [{
                                            title: function (row) {
                                                second = row.surveyOrgName
                                                return first +
                                                    ' - ' +
                                                    row
                                                        .surveyOrgName +
                                                    ' - 调查员列表'
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
                                            page: false,
                                            limit: 200,
                                            height: _height * 0.96,
                                            even: true,cols: [
                                                [
                                                    {
                                                        field: 'surveyUserName',
                                                        minWidth: 170,
                                                        title: '调查员',
                                                        rowspan: 2,
                                                        fixed:true,

                                                    },
                                                    {
                                                        title: '超期',
                                                        colspan: 3,
                                                        align: 'center'
                                                    },
                                                    {
                                                        title: '驳回(按件数)',
                                                        colspan: 3,
                                                        align: 'center'
                                                    },
                                                    {
                                                        title: '驳回(按次数)',
                                                        colspan: 3,
                                                        align: 'center'
                                                    },
                                                    {
                                                        title: '阳性',
                                                        colspan: 3,
                                                        align: 'center'
                                                    }
                                                ],
                                                [{
                                                    field: 'overdueNum',
                                                    minWidth: 110,
                                                    title: '超期件数',
                                                    sort: true,
                                                    style: 'color: #3BA9FF;cursor: pointer;',
                                                    event: 'getListU_overdueNum'
                                                }, {
                                                    field: 'overdueRate',
                                                    minWidth: 90,
                                                    title: '超期率',
                                                    sort: true,
                                                    templet: function (d) {
                                                        var _d = Math.round(d.overdueRate * 10000) / 100
                                                        var _html = '<div class="">' + _d + '%</div>'
                                                        return _html
                                                    }
                                                }, {
                                                    field: 'overdueChain',
                                                    minWidth: 90,
                                                    title: '环比',
                                                    sort: true,
                                                    templet: function (d) {
                                                        var _d = Math.round(d.overdueChain * 10000) / 100
                                                        var _html = '<div class="">' + _d + '%</div>'
                                                        if (_d > 0) {
                                                            _html = '<div class="color1">+' + _d + '%</div>'
                                                        } else if (_d < 0) {
                                                            _html = '<div class="color2">' + _d + '%</div>'

                                                        }
                                                        return _html
                                                    }

                                                }, {
                                                    field: 'rejectedNum',
                                                    minWidth: 110,
                                                    title: '驳回件数',
                                                    sort: true,
                                                    style: 'color: #3BA9FF;cursor: pointer;',
                                                    event: 'getListU_rejectedNum'
                                                }, {
                                                    field: 'rejectionRate',
                                                    minWidth: 90,
                                                    title: '驳回率',
                                                    sort: true,
                                                    templet: function (d) {
                                                        var _d = Math.round(d.rejectionRate * 10000) / 100
                                                        var _html = '<div class="">' + _d + '%</div>'
                                                        return _html
                                                    }
                                                }, {
                                                    field: 'dismissedQoQ',
                                                    minWidth: 90,
                                                    title: '环比',
                                                    sort: true,
                                                    templet: function (d) {
                                                        var _d = Math.round(d.dismissedQoQ * 10000) / 100
                                                        var _html = '<div class="">' + _d + '%</div>'
                                                        if (_d > 0) {
                                                            _html = '<div class="color1">+' + _d + '%</div>'
                                                        } else if (_d < 0) {
                                                            _html = '<div class="color2">' + _d + '%</div>'

                                                        }
                                                        return _html
                                                    }
                                                },{
                                                    field: 'rejectedNumForRm',
                                                    minWidth: 120,
                                                    title: '驳回次数',
                                                    sort: true,
                                                    totalRow: true,

                                                    style: 'color: #3BA9FF;cursor: pointer;',
                                                    event: 'getList_rejectedNum'
                                                }, {
                                                    field: 'rejectionRateForRm',
                                                    minWidth: 120,
                                                    title: '驳回率',
                                                    sort: true,
                                                    totalRow: true,

                                                    templet: function (d) {
                                                        var _d = Math.round(d.rejectionRateForRm * 10000) / 100
                                                        var _html = '<div class="">' + _d + '%</div>'
                                                        return _html
                                                    }
                                                }, {
                                                    field: 'dismissedQoQForRm',
                                                    minWidth: 120,
                                                    title: '环比',
                                                    sort: true,
                                                    totalRow: true,

                                                    templet: function (d) {
                                                        var _d = Math.round(d.dismissedQoQForRm * 10000) / 100
                                                        var _html = '<div class="">' + _d + '%</div>'
                                                        if (_d > 0) {
                                                            _html = '<div class="color1">+' + _d + '%</div>'
                                                        } else if (_d < 0) {
                                                            _html = '<div class="color2">' + _d + '%</div>'

                                                        }
                                                        return _html
                                                    }
                                                }, {
                                                    field: 'positivepNum',
                                                    minWidth: 110,
                                                    title: '阳性件数',
                                                    sort: true,
                                                    style: 'color: #3BA9FF;cursor: pointer;',
                                                    event: 'getListU_positivepNum'
                                                }, {
                                                    field: 'positiveRate',
                                                    minWidth: 90,
                                                    title: '阳性率',
                                                    sort: true,
                                                    templet: function (d) {
                                                        var _d = Math.round(d.positiveRate * 10000) / 100
                                                        var _html = '<div class="">' + _d + '%</div>'
                                                        return _html
                                                    }
                                                }, {
                                                    field: 'positiveRatio',
                                                    minWidth: 90,
                                                    title: '环比',
                                                    sort: true,
                                                    templet: function (d) {
                                                        var _d = Math.round(d.positiveRatio * 10000) / 100
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
                                                soulTable
                                                    .render(
                                                        this);
                                                showTitle('.layui-layer-page:last .layui-layer-content .layui-tab-content .layui-table-view .layui-table-box .layui-table-header:first table thead tr:last th', true)
                                            },
                                            toolEvent: function (
                                                obj, pobj) {
                                                onRowEvent(obj,
                                                    pobj,3)
                                            },
                                            defaultToolbar:[],
                                            toolbar: '<div><a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="export">导出</a>',
                                            toolbarEvent: function (obj, pobj) {
                                                // obj 子表当前行对象
                                                // pobj 父表当前行对象
                                                onRowEvent(obj, pobj, first+'-'+second +'-调查员')
                                            },
                                        }]
                                    }
                                },
                                    {
                                        field: 'surveyOrgName',
                                        minWidth: 160,
                                        title: '调查机构',
                                        rowspan: 2,
                                        fixed:true
                                    },
                                    {
                                        title: '超期',
                                        colspan: 3,
                                        align: 'center'
                                    },
                                    {
                                        title: '驳回(按件数)',
                                        colspan: 3,
                                        align: 'center'
                                    },
                                    {
                                        title: '驳回(按次数)',
                                        colspan: 3,
                                        align: 'center'
                                    },
                                    {
                                        title: '阳性',
                                        colspan: 3,
                                        align: 'center'
                                    }
                                ],
                                [{
                                    field: 'overdueNum',
                                    minWidth: 110,
                                    title: '超期件数',
                                    sort: true,
                                    style: 'color: #3BA9FF;cursor: pointer;',
                                    event: 'getListU_overdueNum'
                                }, {
                                    field: 'overdueRate',
                                    minWidth: 90,
                                    title: '超期率',
                                    sort: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.overdueRate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        return _html
                                    }
                                }, {
                                    field: 'overdueChain',
                                    minWidth: 90,
                                    title: '环比',
                                    sort: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.overdueChain * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }

                                }, {
                                    field: 'rejectedNum',
                                    minWidth: 110,
                                    title: '驳回件数',
                                    sort: true,
                                    style: 'color: #3BA9FF;cursor: pointer;',
                                    event: 'getListU_rejectedNum'
                                }, {
                                    field: 'rejectionRate',
                                    minWidth: 90,
                                    title: '驳回率',
                                    sort: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.rejectionRate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        return _html
                                    }
                                }, {
                                    field: 'dismissedQoQ',
                                    minWidth: 90,
                                    title: '环比',
                                    sort: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.dismissedQoQ * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                }, {
                                    field: 'rejectedNumForRm',
                                    minWidth: 120,
                                    title: '驳回次数',
                                    sort: true,
                                    totalRow: true,

                                    style: 'color: #3BA9FF;cursor: pointer;',
                                    event: 'getListU_rejectedNum'
                                }, {
                                    field: 'rejectionRateForRm',
                                    minWidth: 120,
                                    title: '驳回率',
                                    sort: true,
                                    totalRow: true,

                                    templet: function (d) {
                                        var _d = Math.round(d.rejectionRateForRm * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        return _html
                                    }
                                }, {
                                    field: 'dismissedQoQForRm',
                                    minWidth: 120,
                                    title: '环比',
                                    sort: true,
                                    totalRow: true,

                                    templet: function (d) {
                                        var _d = Math.round(d.dismissedQoQForRm * 10000) / 100
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
                                    field: 'positivepNum',
                                    minWidth: 110,
                                    title: '阳性件数',
                                    sort: true,
                                    style: 'color: #3BA9FF;cursor: pointer;',
                                    event: 'getListU_positivepNum'
                                }, {
                                    field: 'positiveRate',
                                    minWidth: 90,
                                    title: '阳性率',
                                    sort: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.positiveRate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        return _html
                                    }
                                }, {
                                    field: 'positiveRatio',
                                    minWidth: 90,
                                    title: '环比',
                                    sort: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.positiveRatio * 10000) / 100
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
                                showTitle('.layui-layer-page .layui-layer-content .layui-tab-content .layui-table-view .layui-table-box .layui-table-header:first table thead tr:last th')

                            },
                            toolEvent: function (obj, pobj) {
                                onRowEvent(obj, pobj,3)
                            },
                            defaultToolbar:[],
                            toolbar: '<div><a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="export">导出</a>',
                            toolbarEvent: function (obj, pobj) {
                                // obj 子表当前行对象
                                // pobj 父表当前行对象
                                onRowEvent(obj, pobj, first +'-片区机构')
                            },
                        }]

                    } else if (row.orgType == '2') {
                        Object.assign(paramSubmit,{clickPqSurv: 'yes',surveyOrgIds: row.surveyOrgId})
                        return [{
                            title: function (row) {
                                first = row.surveyOrgName
                                return row.surveyOrgName + ' - 调查员列表'
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
                            page: false,
                            limit: 200,
                            height: _height * 0.96,
                            even: true,cols: [
                                [{
                                    field: 'surveyUserName',
                                    minWidth: 170,
                                    title: '调查员',
                                    rowspan: 2,
                                    fixed:true
                                },
                                    {
                                        title: '超期',
                                        colspan: 3,
                                        align: 'center'
                                    },
                                    {
                                        title: '驳回(按件数)',
                                        colspan: 3,
                                        align: 'center'
                                    },
                                    {
                                        title: '驳回(按次数)',
                                        colspan: 3,
                                        align: 'center'
                                    },
                                    {
                                        title: '阳性',
                                        colspan: 3,
                                        align: 'center'
                                    }
                                ],
                                [{
                                    field: 'overdueNum',
                                    minWidth: 110,
                                    title: '超期件数',
                                    sort: true,
                                    style: 'color: #3BA9FF;cursor: pointer;',
                                    event: 'getListU_overdueNum'
                                }, {
                                    field: 'overdueRate',
                                    minWidth: 90,
                                    title: '超期率',
                                    sort: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.overdueRate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        return _html
                                    }
                                }, {
                                    field: 'overdueChain',
                                    minWidth: 90,
                                    title: '环比',
                                    sort: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.overdueChain * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }

                                }, {
                                    field: 'rejectedNum',
                                    minWidth: 110,
                                    title: '驳回件数',
                                    sort: true,
                                    style: 'color: #3BA9FF;cursor: pointer;',
                                    event: 'getListU_rejectedNum'
                                }, {
                                    field: 'rejectionRate',
                                    minWidth: 90,
                                    title: '驳回率',
                                    sort: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.rejectionRate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        return _html
                                    }
                                }, {
                                    field: 'dismissedQoQ',
                                    minWidth: 90,
                                    title: '环比',
                                    sort: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.dismissedQoQ * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                }, {
                                    field: 'rejectedNumForRm',
                                    minWidth: 120,
                                    title: '驳回次数',
                                    sort: true,
                                    totalRow: true,

                                    style: 'color: #3BA9FF;cursor: pointer;',
                                    event: 'getList_rejectedNum'
                                }, {
                                    field: 'rejectionRateForRm',
                                    minWidth: 120,
                                    title: '驳回率',
                                    sort: true,
                                    totalRow: true,

                                    templet: function (d) {
                                        var _d = Math.round(d.rejectionRateForRm * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        return _html
                                    }
                                }, {
                                    field: 'dismissedQoQForRm',
                                    minWidth: 120,
                                    title: '环比',
                                    sort: true,
                                    totalRow: true,

                                    templet: function (d) {
                                        var _d = Math.round(d.dismissedQoQForRm * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        if (_d > 0) {
                                            _html = '<div class="color1">+' + _d + '%</div>'
                                        } else if (_d < 0) {
                                            _html = '<div class="color2">' + _d + '%</div>'

                                        }
                                        return _html
                                    }
                                },{
                                    field: 'positivepNum',
                                    minWidth: 110,
                                    title: '阳性件数',
                                    sort: true,
                                    style: 'color: #3BA9FF;cursor: pointer;',
                                    event: 'getListU_positivepNum'
                                }, {
                                    field: 'positiveRate',
                                    minWidth: 90,
                                    title: '阳性率',
                                    sort: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.positiveRate * 10000) / 100
                                        var _html = '<div class="">' + _d + '%</div>'
                                        return _html
                                    }
                                }, {
                                    field: 'positiveRatio',
                                    minWidth: 90,
                                    title: '环比',
                                    sort: true,
                                    templet: function (d) {
                                        var _d = Math.round(d.positiveRatio * 10000) / 100
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
                                showTitle('.layui-layer-page .layui-layer-content .layui-tab-content .layui-table-view .layui-table-box .layui-table-header:first table thead tr:last th', true)
                            },
                            toolEvent: function (obj, pobj) {
                                onRowEvent(obj, pobj,3)

                            },
                            defaultToolbar:[],
                            toolbar: '<div><a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="export">导出</a>',
                            toolbarEvent: function (obj, pobj) {
                                // obj 子表当前行对象
                                // pobj 父表当前行对象
                                onRowEvent(obj, pobj, first +'-调查员')
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
                    fixed: true,
                    totalRowText: '合计',

                },

                {
                    title: '超期',
                    colspan: 3,
                    align: 'center'
                },
                {
                    title: '驳回(按件数)',
                    colspan: 3,
                    align: 'center'
                },
                {
                    title: '驳回(按次数)',
                    colspan: 3,
                    align: 'center'
                },
                {
                    title: '阳性',
                    colspan: 3,
                    align: 'center',
                }
            ],
            [{
                field: 'overdueNum',
                minWidth: 120,
                title: '超期件数',
                sort: true,
                totalRow: true,

                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'getList_overdueNum'
            }, {
                field: 'overdueRate',
                minWidth: 120,
                title: '超期率',
                sort: true,
                templet: function (d) {
                    var _d = Math.round(d.overdueRate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    return _html
                }
            }, {
                field: 'overdueChain',
                minWidth: 120,
                title: '环比',
                sort: true,
                totalRow: true,

                templet: function (d) {
                    var _d = Math.round(d.overdueChain * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }

            }, {
                field: 'rejectedNum',
                minWidth: 120,
                title: '驳回件数',
                sort: true,
                totalRow: true,

                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'getList_rejectedNum'
            }, {
                field: 'rejectionRate',
                minWidth: 120,
                title: '驳回率',
                sort: true,
                totalRow: true,

                templet: function (d) {
                    var _d = Math.round(d.rejectionRate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    return _html
                }
            }, {
                field: 'dismissedQoQ',
                minWidth: 120,
                title: '环比',
                sort: true,
                totalRow: true,

                templet: function (d) {
                    var _d = Math.round(d.dismissedQoQ * 10000) / 100
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
                    field: 'rejectedNumForRm',
                    minWidth: 120,
                    title: '驳回次数',
                    sort: true,
                    totalRow: true,

                    style: 'color: #3BA9FF;cursor: pointer;',
                    event: 'getList_rejectedNum'
                }, {
                field: 'rejectionRateForRm',
                minWidth: 120,
                title: '驳回率',
                sort: true,
                totalRow: true,

                templet: function (d) {
                    var _d = Math.round(d.rejectionRateForRm * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    return _html
                }
            }, {
                field: 'dismissedQoQForRm',
                minWidth: 120,
                title: '环比',
                sort: true,
                totalRow: true,

                templet: function (d) {
                    var _d = Math.round(d.dismissedQoQForRm * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            },{
                field: 'positivepNum',
                minWidth: 120,
                title: '阳性件数',
                sort: true,
                totalRow: true,

                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'getList_positivepNum'
            }, {
                field: 'positiveRate',
                minWidth: 120,
                title: '阳性率',
                sort: true,
                totalRow: true,

                templet: function (d) {
                    var _d = Math.round(d.positiveRate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    return _html
                }
            }, {
                field: 'positiveRatio',
                minWidth: 120,
                title: '环比',
                sort: true,
                totalRow: true,

                templet: function (d) {
                    var _d = Math.round(d.positiveRatio * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            }]
        ]
        var _cols2 = [
            [{
                field: 'surveyUserName',
                minWidth: 170,
                title: '调查员',
                rowspan: 2,
                fixed: true,
                totalRowText: '合计'
            },{
                field: 'surveyOrgName',
                minWidth: 220,
                title: '机构名称',
                rowspan: 2,
                templet: '#surveyOrgName',
            },
                {
                    title: '超期',
                    colspan: 3,
                    align: 'center'
                },
                {
                title: '驳回(按件数)',
                colspan: 3,
                align: 'center'
            },
                {
                    title: '驳回(按次数)',
                    colspan: 3,
                    align: 'center'
                },
                {
                    title: '阳性',
                    colspan: 3,
                    align: 'center',
                }
            ],
            [{
                field: 'overdueNum',
                minWidth: 120,
                title: '超期件数',
                sort: true,
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'getListU_overdueNum'
            }, {
                field: 'overdueRate',
                minWidth: 120,
                title: '超期率',
                sort: true,
                templet: function (d) {
                    var _d = Math.round(d.overdueRate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    return _html
                }
            }, {
                field: 'overdueChain',
                minWidth: 120,
                title: '环比',
                sort: true,
                templet: function (d) {
                    var _d = Math.round(d.overdueChain * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }

            }, {
                field: 'rejectedNum',
                minWidth: 120,
                title: '驳回件数',
                sort: true,
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'getListU_rejectedNum'
            }, {
                field: 'rejectionRate',
                minWidth: 120,
                title: '驳回率',
                sort: true,
                templet: function (d) {
                    var _d = Math.round(d.rejectionRate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    return _html
                }
            }, {
                field: 'dismissedQoQ',
                minWidth: 120,
                title: '环比',
                sort: true,
                templet: function (d) {
                    var _d = Math.round(d.dismissedQoQ * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            },{
                field: 'rejectedNumForRm',
                minWidth: 120,
                title: '驳回次数',
                sort: true,
                totalRow: true,

                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'getList_rejectedNum'
            }, {
                field: 'rejectionRateForRm',
                minWidth: 120,
                title: '驳回率',
                sort: true,
                totalRow: true,

                templet: function (d) {
                    var _d = Math.round(d.rejectionRateForRm * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    return _html
                }
            }, {
                field: 'dismissedQoQForRm',
                minWidth: 120,
                title: '环比',
                sort: true,
                totalRow: true,

                templet: function (d) {
                    var _d = Math.round(d.dismissedQoQForRm * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            }, {
                field: 'positivepNum',
                minWidth: 120,
                title: '阳性件数',
                sort: true,
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'getListU_positivepNum'
            }, {
                field: 'positiveRate',
                minWidth: 120,
                title: '阳性率',
                sort: true,
                templet: function (d) {
                    var _d = Math.round(d.positiveRate * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    return _html
                }
            }, {
                field: 'positiveRatio',
                minWidth: 120,
                title: '环比',
                sort: true,
                templet: function (d) {
                    var _d = Math.round(d.positiveRatio * 10000) / 100
                    var _html = '<div class="">' + _d + '%</div>'
                    if (_d > 0) {
                        _html = '<div class="color1">+' + _d + '%</div>'
                    } else if (_d < 0) {
                        _html = '<div class="color2">' + _d + '%</div>'

                    }
                    return _html
                }
            }]
        ]
        var _cols = _cols1

        var ha1 = $('.haha').html()
        var ha2 = $('.haha').text()
        console.log(ha1,ha2)
        setTable(_cols, paramSubmit)

        function setTable(_cols, param) {
            $('button.ll-submit').attr('disabled', true)
            setTimeout(function () {
                $('button.ll-submit').removeAttr('disabled')
            }, 6000)
            var _h = $('.searchs').outerHeight() + 50
            var fullH = 'full-' + _h
            var myTable = table.render({
                id: "test",
                elem: '#test',
                even: true,cols: _cols,
                page: false,
                totalRow: true,
                limit: 200,
                height: fullH,
                url: '${ctx}/survey/hzReport/getDetail',
                where: param,
                parseData: function (res) {
                    console.log(res.results.list)
                    resultsTotal = {
                        "overdueNum": res.results.overTotal || '0' ,
                        "overdueRate": res.results.overTotalRate || '0%' ,
                        "overdueChain": res.results.momOverTotalRate || '0%' ,
                        "rejectedNum": res.results.rejectedTotal || '0' ,
                        "rejectionRate": res.results.rejectedTotaRate || '0%' ,
                        "dismissedQoQ": res.results.momRejectedTotaRate || '0%' ,
                        "rejectedNumForRm": res.results.rejectedTotalForRm || '0' ,
                        "rejectionRateForRm": res.results.rejectedTotaRateForRm || '0%' ,
                        "dismissedQoQForRm": res.results.momRejectedTotaRateForRm || '0%' ,
                        "positivepNum": res.results.positiveTotal || '0' ,
                        "positiveRate": res.results.positiveTotalRate || '0%' ,
                        "positiveRatio": res.results.momPositiveTotalRate || '0%'
                    }
                    return {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results.list,
                        "totalRow": {
                            "overdueNum": res.results.overTotal || '0' ,
                            "overdueRate": res.results.overTotalRate || '0%' ,
                            "overdueChain": res.results.momOverTotalRate || '0%' ,
                            "rejectedNum": res.results.rejectedTotal || '0' ,
                            "rejectionRate": res.results.rejectedTotaRate || '0%' ,
                            "dismissedQoQ": res.results.momRejectedTotaRate || '0%' ,
                            "rejectedNumForRm": res.results.rejectedTotalForRm || '0' ,
                            "rejectionRateForRm": res.results.rejectedTotaRateForRm || '0%' ,
                            "dismissedQoQForRm": res.results.momRejectedTotaRateForRm || '0%' ,
                            "positivepNum": res.results.positiveTotal || '0' ,
                            "positiveRate": res.results.positiveTotalRate || '0%' ,
                            "positiveRatio": res.results.momPositiveTotalRate || '0%'
                        }
                    }
                },
                initSort: {
                    field: 'overdueRate',
                    type: 'desc'
                },
                title: '考核指标报表',
                done: function () {
                    soulTable.render(this)
                    $('button.ll-submit').removeAttr('disabled')
                    showTitle('.table-content .layui-table-view .layui-table-box .layui-table-header:first table thead tr:last th')
                    if (resultsTotal.overdueChain.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="overdueChain"] div').addClass('color2')
                    }else if (resultsTotal.overdueChain != '0%'){
                        $('.layui-table-total tbody tr td[data-field="overdueChain"] div').addClass('color1')
                    }

                    if (resultsTotal.dismissedQoQ.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="dismissedQoQ"] div').addClass('color2')
                    }else if (resultsTotal.dismissedQoQ != '0%'){
                        $('.layui-table-total tbody tr td[data-field="dismissedQoQ"] div').addClass('color1')
                    }

                    if (resultsTotal.dismissedQoQForRm.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="dismissedQoQForRm"] div').addClass('color2')
                    }else if (resultsTotal.dismissedQoQForRm != '0%'){
                        $('.layui-table-total tbody tr td[data-field="dismissedQoQForRm"] div').addClass('color1')
                    }

                    if (resultsTotal.positiveRatio.indexOf('-') > -1){
                        $('.layui-table-total tbody tr td[data-field="positiveRatio"] div').addClass('color2')
                    }else if (resultsTotal.positiveRatio != '0%'){
                        $('.layui-table-total tbody tr td[data-field="positiveRatio"] div').addClass('color1')
                    }

                }
            })

            table.on('tool(test)', function (obj, pobj) {
                onRowEvent(obj, pobj,1)
            })

            $('.layui-export').off('click').on('click',function (e) {
                var _this = $('.layui-export')
                _this.attr('disabled',true)
                // table.exportFile(myTable.config.id, myTable.config.data, 'xlsx')
                soulTable.export(myTable,{
                    filename: '考核指标报表.xlsx'
                })
                setTimeout(function () {
                    _this.removeAttr('disabled')
                },5000)
            })
        }

        // function onRowEvent(obj, pobj) {
        //     var type = obj.event.split('_')
        //     console.log(type)
        // }
        function onRowEvent(obj, pobj,num) {
            if (obj.event.split("_")[0] == "getList") {
                // 处理你的业务逻辑
                console.log('表 ',obj.data)
                showCaseList(obj.data,'org',obj.event.split("_")[1],num)
            } else if (obj.event.split("_")[0] == "getListU") {
                // 处理你的业务逻辑
                console.log('调查员 ',obj.data)
                showCaseList(obj.data,'user',obj.event.split("_")[1],num)
            } else if (obj.event === 'export') {
                soulTable.export(obj.config.id,{
                    filename: '考核指标报表('+ num+').xlsx'
                })
            }
        }

        function showCaseList (data,caseCode,type,num){
            var scoreRole = $("#scoreRole").val();
            var height = $(document).outerHeight()*0.9;
            var width = $(document.body).outerWidth()*0.9;
            var entrustOrgIds = demo1.getValue('valueStr');
            var caseState= scoreRole == 'manger' ? demo5.getValue('valueStr') : 0;
            var orgCaseState= demo6.getValue('valueStr');
            var surveyState = data.surveyState;
            var surveyOrgId = data.surveyOrgId;
            var surveyAreaId = data.surveyAreaId;
            var surveyUserId = data.surveyUserId == null?'':data.surveyUserId;
            var startTime = $('#startTime').val();
            var endTime= $('#endTime').val();
            var searchType = searchType;
            var url = "${ctx}/survey/hzReport/popup?menuCode=assessmentIndex&btnCode=case&caseCode=" + caseCode+ "&caseState="+caseState+"&entrustOrgIds="+entrustOrgIds
                + "&orgCaseState="+orgCaseState+ "&surveyState="+surveyState+ "&surveyOrgId="+surveyOrgId+ "&surveyUserId="+surveyUserId
                + "&startTime="+startTime+ "&endTime="+endTime+ "&searchType="+searchType+"&scoreRole="+scoreRole+"&caseType="+type+"&num="+num;
            if (surveyAreaId != null){
                url += "&surveyAreaId="+surveyAreaId;
            }
            openDialog({
                frame:true,
                title:"详情",
                height:height,
                width:width,
                url:url
            });
        }

        setSortDesc('overdueRate')
        function setSortDesc(param){
            $('body').on('click', '.layui-table-col-special',function(){
                var _this = $(this)
                if (_this.parents('.main').length){
                    var _ll = $('.main').siblings('.layui-layer')
                    _ll.find('th[data-field="'+param+'"] .layui-table-cell').click()
                    _ll.find('th[data-field="'+param+'"] .layui-table-cell').click()
                }else {
                    var _ll = _this.parents('.layui-layer')
                    if (_this.parents('.layui-layer').siblings('.layui-layer').length){
                        _ll = _this.parents('.layui-layer').siblings('.layui-layer')
                    }
                    _ll.find('th[data-field="'+param+'"] .layui-table-cell').click()
                    _ll.find('th[data-field="'+param+'"] .layui-table-cell').click()
                }
            })
        }

        function showTitle(thsJ, child, iter) {
            var icon_about = '<span class="icon-about">'
            var ths = $(thsJ)
            var startTime = dateFormat($('#startTime').val(), 'yyyy年MM月dd日')
            var endTime = dateFormat($('#endTime').val(), 'yyyy年MM月dd日')
            ths.find('.layui-table-cell span:first').after(icon_about)

            var claimsman = demo3.getValue('valueStr')
            claimsman = claimsman ? claimsman.split(',') : []
            console.log(child)
            if ((!claimsman || claimsman.length == 0) && !child) {
                var _title1 ='委托时间在'+startTime+ '至' + endTime +'的互助案件中，超过机构截止日期的案件（包括已经提交平台复审但超期的以及未提交但超期的）'
                ths.eq(0).find('.layui-table-cell').attr('title', _title1)

                var _title2 = '该机构互助超期件数占该机构全部委托时间在'+startTime+ '至' + endTime +'的互助案件中的比例'
                ths.eq(1).find('.layui-table-cell').attr('title', _title2)

                var _title3 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=本期超期率-上期超期率'
                ths.eq(2).find('.layui-table-cell').attr('title', _title3)


                var _title4 ='该机构平台复审通过时间在'+startTime+ '至' + endTime +'的互助案件中，曾被平台复审驳回的案件数量（多次被驳回算做一次）'
                ths.eq(3).find('.layui-table-cell').attr('title', _title4)

                var _title5 = '该机构驳回件数占该机构全部平台复审通过时间在'+startTime+ '至' + endTime +'的互助案件中的比例'
                ths.eq(4).find('.layui-table-cell').attr('title', _title5)

                var _title6 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=本期驳回率-上期驳回率'
                ths.eq(5).find('.layui-table-cell').attr('title', _title6)


                var _title7 ='该机构平台复审通过时间在'+startTime+ '至' + endTime +'的互助案件中，曾被平台复审驳回的次数。（多次被驳回算作多次）'
                ths.eq(6).find('.layui-table-cell').attr('title', _title7)

                var _title8 = '该机构驳回次数占该机构全部平台复审通过时间在'+startTime+ '至' + endTime +'的互助案件中的比例'
                ths.eq(7).find('.layui-table-cell').attr('title', _title8)

                var _title9 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=本期驳回率-上期驳回率'
                ths.eq(8).find('.layui-table-cell').attr('title', _title9)


                var _title10 ='该机构平台复审通过时间在'+startTime+ '至' + endTime +'的互助案件中，该机构下的调查员拿到了阳性积分的案件数量（不是最早发现阳性的不算）'
                ths.eq(9).find('.layui-table-cell').attr('title', _title10)

                var _title11 = '该机构阳性件数占该机构全部平台复审通过时间在'+startTime+ '至' + endTime +'的互助案件中的比例'
                ths.eq(10).find('.layui-table-cell').attr('title', _title11)

                var _title12 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=本期阳性率-上期阳性率'
                ths.eq(11).find('.layui-table-cell').attr('title', _title12)
            } else {


                var _title1 ='委托时间在'+startTime+ '至' + endTime +'的互助案件中，超过调查员截止日期的案件（包括已经提交机构初审但超期的以及未提交但超期的）'
                ths.eq(0).find('.layui-table-cell').attr('title', _title1)

                var _title2 = '该调查员超期件数占该调查员全部委托时间在'+startTime+ '至' + endTime +'的互助案件中的比例'
                ths.eq(1).find('.layui-table-cell').attr('title', _title2)

                var _title3 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=本期超期率-上期超期率'
                ths.eq(2).find('.layui-table-cell').attr('title', _title3)


                var _title4 ='该调查员机构初审通过时间在'+startTime+ '至' + endTime +'的互助案件中，曾被机构初审驳回的案件数量（多次被驳回算做一次）'
                ths.eq(3).find('.layui-table-cell').attr('title', _title4)

                var _title5 = '该调查员驳回件数占该调查员全部机构初审通过时间在'+startTime+ '至' + endTime +'的互助案件中的比例'
                ths.eq(4).find('.layui-table-cell').attr('title', _title5)

                var _title6 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=本期驳回率-上期驳回率'
                ths.eq(5).find('.layui-table-cell').attr('title', _title6)

                var _title7 ='该调查员平台复审通过时间在'+startTime+ '至' + endTime +'的互助案件中，曾被机构初审驳回的次数。（多次被驳回算作多次）'
                ths.eq(6).find('.layui-table-cell').attr('title', _title7)

                var _title8 = '该调查员驳回次数占该调查员全部平台复审通过时间在'+startTime+ '至' + endTime +'的互助案件中的比例'
                ths.eq(7).find('.layui-table-cell').attr('title', _title8)

                var _title9 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=本期驳回率-上期驳回率'
                ths.eq(8).find('.layui-table-cell').attr('title', _title9)


                var _title10 ='平台复审通过时间在'+startTime+ '至' + endTime +'的互助案件中，该调查员拿到了阳性积分的案件数量（不是最早发现阳性的不算）'
                ths.eq(9).find('.layui-table-cell').attr('title', _title10)

                var _title11 = '该调查员阳性件数占该调查员全部平台复审通过时间在'+startTime+ '至' + endTime +'的互助案件中的比例'
                ths.eq(10).find('.layui-table-cell').attr('title', _title11)

                var _title12 = '连续2个统计周期（比如连续两月）内的量的变化比，环比=本期阳性率-上期阳性率'
                ths.eq(11).find('.layui-table-cell').attr('title', _title12)
            }


        }

    })
</script>

</body>

</html>