<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>方向区域分布报表</title>
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

        .layui-form-item .layui-input-inline{
            width: 150px!important;
        }

        .layui-form-item .area .layui-input-inline{
            width: 120px!important;
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
            z-index: 1298910170;
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
        .echarts-contain{
            width: 100%;
            padding: 10px 0;
        }
        .charts-map{
            width: 70%;
            height: 100%;
        }
        .chartsm-info {
            width: 30%;
            /* height: 96%; */
            /* padding-top: 16px; */
        }
        .charts{
            width: 100%;
            height: 700px;
            padding: 0;
            display: flex;
        }
        .list {
            width: 99%;
            margin: 0 auto;
            border: 1px solid #ddd;
        }

        .list #charts5_table{
            height: 611px;
            overflow: hidden;
        }

        .list .bar {
            /*display: none;*/
            /*height: 611px;*/
        }

        .list .li {
            width: 100%;
            padding: 15px 0;
            line-height: 20px;
            display: flex;
            border-bottom: 1px solid #ddd;
            cursor: pointer;
        }

        .list .li .name {
            width: 32%;
            padding: 0 1% 0 3%;
            color: #666;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .list .li.active .name{
            color:#3ba9ff;
        }

        .list .li .num-text {
            width: 15%;
            padding: 0 2%;
            text-align: center;
        }

        .list .li .num-line {
            width: 30%;
            padding: 0 1%;
            display: flex;
            align-items: center;
        }
        .list .li .num-rate {
            width: 18%;
            padding: 0 1%;
        }

        .list .li .num-line .line-icon {
            height: 6px;
            background-color: #c23531;
        }

        .list .li .btn-page {
            line-height: 30px;
            padding: 0 5px;
            color: #666;
        }

        .list .li .btn-page span {
            line-height: 30px;
            color: #666;
        }

        .list .li .btn-icon {
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 6px;
            padding-left: 2px;
            width: 22px;
            height: 28px;
            border: 1px solid #ddd;
            background-color: #fff;
            color: #666;
            cursor: pointer;
        }

        .list .li .btn-icon:hover {
            color: #333333;
        }

        .list .li .btn-icon .icon-next:hover {
            border-left: 7px solid #333;

        }

        .list .li .btn-icon .icon-next {
            width: 0;
            height: 0;
            border-top: 6px solid transparent;
            border-bottom: 6px solid transparent;
            border-left: 7px solid #666;
        }

        .list .li .btn-icon .icon-next2 {
            width: 0;
            height: 0;
            border-top: 6px solid transparent;
            border-bottom: 6px solid transparent;
            border-right: 7px solid #666;
        }

        .list .li input {
            padding-left: 10px;
            width: 58px;
            height: 28px;
            line-height: 28px;
            border: 1px solid #ddd;
            background-color: #fff;
        }

        .list .li .btn {
            padding: 0 10px;
            margin: 0 6px;
            height: 28px;
            line-height: 28px;
            border: 1px solid #ddd;
            background-color: #fff;
            color: #666;
            text-align: center;
            cursor: pointer;
        }
        .list .li .goback {
            padding: 0 10px;
            margin: 0 6px;
            height: 28px;
            line-height: 28px;
            border: 1px solid #ddd;
            background-color: #fff;
            color: #666;
            text-align: center;
            cursor: pointer;
        }
        .list .li.li-header{
            background-color: #f2f2f2;
            padding: 11px 0;
        }
        .list .li.li-footer{
            background-color: #f2f2f2;
            justify-content: space-between;
            padding: 7px 10px;
            border: none;
        }
        @media screen and (max-width:1250px){
            .charts{
                height: 496px;
            }
            .list #charts5_table{
                height: 408px;
                overflow: hidden;
            }
            .list .li {
                padding: 6px 0 7px 0;
            }
            .list .li.li-header{
                background-color: #f2f2f2;
                padding: 10px 0 11px 0;
            }
            .list .li.li-footer{
                background-color: #f2f2f2;
                justify-content: space-around;
                padding: 7px 0;
                border: none;
            }
        }


        .lf-none{
            margin: 50px auto;
            color: #666;
            text-align: center;
        }
    </style>
</head>

<body>
<div class="main">
    <input type="hidden" value='${params.consignorsJson}' id="consignorsJson" />
    <input type="hidden" value='${params.commonAreaList}' id="provinceData"/>
    <div class="layui-form searchs" lay-filter="search">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">互助平台</label>
                <div class="layui-input-inline">
                    <div id="entrustOrgIds" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">案件状态</label>
                <div class="layui-input-inline">
                    <div id="surveyState" class="selectMul"></div>
                </div>
            </div>
            <%--<div class="layui-inline">--%>
                <%--<label class="layui-form-label">案件类型</label>--%>
                <%--<div class="layui-input-inline">--%>
                    <%--<div id="caseState" class="selectMul"></div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="layui-inline area">
                <label class="layui-form-label">省</label>
                <div class="layui-input-inline">
                    <div id="provinceSel" class="selectMul"></div>
                </div>

                <label class="layui-form-label" style="width: auto;">市</label>
                <div class="layui-input-inline">
                    <div id="citySel" class="selectMul"></div>
                </div>
                <label class="layui-form-label" style="width: auto;">区</label>
                <div class="layui-input-inline">
                    <div id="districtSel" class="selectMul"></div>
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
            </div>
        </div>
    </div>
    <%--<div class="table-content">--%>
    <%--<table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg" style="margin: 0">--%>
    <%--</table>--%>
    <%--</div>--%>
    <div class="echarts-contain">
        <div class="charts">
            <div id="charts5" class="charts-map"></div>
            <div class="chartsm-info">
                <div class="list">
                    <div class="li li-header" >
                        <div class="name">地区</div>
                        <div class="num-text" style="display: flex; align-items: center;padding-right: 0;width: 45%;">
                            <span class="span-text" style="padding-right: 4px;">方向数</span>
                            <div class="icon-sort">
                                <div class="icon-up"></div>
                                <div class="icon-down"></div>
                            </div>
                        </div>
                        <div class="num-rate">占比</div>
                    </div>
                    <div id="charts5_table">
                        <div class="bar"></div>
                    </div>
                    <div class="li li-footer">
                        <div class="goback">返回上级</div>
                        <div style="display: flex;min-width:215px;">
                            <div class="btn-icon" data-index="1" data-page="1" id="div_page_total2">
                                <div class="icon-next2"></div>
                            </div>
                            <div class="btn-page"><span id="span_page_index">1</span>/<span id="span_page_total">1</span> </div>
                            <div class="btn-icon" data-index="1" data-page="1" id="div_page_total">
                                <div class="icon-next"></div>
                            </div>
                            <input type="number" value="" class="page-num" min="1">
                            <div class="btn">跳转</div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="regionName">
    {{#  if(d.orgType ==1){ }}
    <div class="nowrap-e" title="【A类】{{d.surveyOrgName}}"><span
            class="d_type d_type_a">【A类】</span>{{d.surveyOrgName}}</div>
    {{#  } else if(d.orgType ==2){ }}
    <div class="nowrap-e" title="【B类】{{d.surveyOrgName}}"><span
            class="d_type d_type_b">【B类】</span>{{d.surveyOrgName}}</div>
    {{#  } else { }}
    <div class="nowrap-e" title="{{d.surveyOrgName}}">{{d.surveyOrgName}}</div>
    {{#  } }}

</script>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script src="${ctx}/js/jquery.min.js" charset="utf-8"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/js/echars/echarts.min.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?ak=geF7zRxYTlIuHlqVTxtbKM70GqFSPTAj"></script>
<script type="text/javascript" src="${ctx}/js/echars/bmap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/china.js"></script>
<script src="${ctx}/js/chinaMap.js" charset="utf-8"></script>
<script>
    var mapData = getMapData()

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
            m = PrefixInteger(m, 2)
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
            layer = layui.layer,
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
        var demo4 = xmSelect.render({
            el: '#surveyState',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',

            radio: true,
            clickClose: true,
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
        // var demo5 = xmSelect.render({
        //     el: '#caseState',
        //     theme: {
        //         color: '#3BA9FF',
        //     },
        //     size: 'small',
        //     radio: true,
        //     model: {
        //         label: {
        //             type: 'block',
        //             block: {
        //                 //是否显示删除图标
        //                 showIcon: false,
        //             }
        //         }
        //     },
        //     data:[{
        //         name: '全部',
        //         value: '0'
        //     }, {
        //         name: '单点',
        //         value: 1
        //     },
        //         {
        //             name: '单点+单点',
        //             value: 2
        //         },
        //         {
        //             name: '全案',
        //             value: 3
        //         },
        //         {
        //             name: '全案+单点',
        //             value: 4
        //         },
        //         {
        //             name: '全案+全案',
        //             value: 5
        //         },
        //     ]
        // })

        var demo7 = xmSelect.render({
            el: '#provinceSel',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            radio: true,
            clickClose: true,
            on: function (data) {
                //更新数据源 hou
                if (data.arr.length && data.arr[0].value){
                    $.ajax({
                        url: '${ctx}/user/role/selectArea',
                        data: {
                            'parentId': data.arr[0].value
                        },
                        success: function(res){
                            res = JSON.parse(res)
                            if (res.isSuccess){
                                filterJson(demo8, res.results,'areaId', 'areaName', false, false)
                            }
                        }
                    })
                }else {
                    demo8.update({
                        data: []
                    })
                    demo9.update({
                        data: []
                    })

                }

            },
            model: {
                label: {
                    type: 'block',
                    block: {
                        //是否显示删除图标
                        showIcon: false,
                    }
                }
            },
            data:[]
        })
        var demo8 = xmSelect.render({
            el: '#citySel',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            radio: true,
            clickClose: true,
            on: function (data) {
                //更新数据源 hou
                if (data.arr.length && data.arr[0].value){
                    $.ajax({
                        url: '${ctx}/user/role/selectArea',
                        data: {
                            'parentId': data.arr[0].value
                        },
                        success: function(res){
                            res = JSON.parse(res)
                            if (res.isSuccess){
                                filterJson(demo9, res.results,'areaId', 'areaName', false, false)
                            }
                        }
                    })
                }else {
                    demo9.update({
                        data: []
                    })
                }

            },
            model: {
                label: {
                    type: 'block',
                    block: {
                        //是否显示删除图标
                        showIcon: false,
                    }
                }
            },
            data:[]
        })
        var demo9 = xmSelect.render({
            el: '#districtSel',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            radio: true,
            clickClose: true,
            model: {
                label: {
                    type: 'block',
                    block: {
                        //是否显示删除图标
                        showIcon: false,
                    }
                }
            },
            data:[]
        })

        //初始化赋
        demo4.setValue([24])
        // demo5.setValue([0])
        var provinceData = $("#provinceData").val();
        provinceData = JSON.parse(provinceData);
        filterJson(demo7, provinceData,'areaId','areaName',false, false)


        var consignorsJson = $("#consignorsJson").val();
        consignorsJson = JSON.parse(consignorsJson);
        filterJson(demo1, consignorsJson,'id','name',false, false)

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
            menuCode:'directionAreaDistribution',
            platform: '',
            caseStatus: '24',
            caseType:'',
            startTime: initVals[0],
            endTime: initVals[1],
            searchType: 'curMonth',
            // provinceId: '',
            // cityId:'',
            // districtId: ''
        }
        var oldProvinceId = '',oldProvinceName='', oldCityId='', oldCityName = '',oldDistrictId='',oldDistrictName='',level = 'province'

        form.on('submit(submit)', function (data) {
            var vals = data.field
            delete vals.select;
            Object.assign(vals, {
                menuCode:'directionAreaDistribution',
                platform: demo1.getValue('valueStr'),
                caseStatus: demo4.getValue('valueStr'),
                // caseType: demo5.getValue('valueStr') == '0' ? '' : demo5.getValue(
                //     'valueStr'),
                startTime: $('#startTime').val(),
                endTime: $('#endTime').val(),
                searchType: searchType,
                provinceId: demo7.getValue('valueStr'),
                cityId:demo8.getValue('valueStr'),
                districtId: demo9.getValue('valueStr')
            })
            geoCoordMap = filterMapSubmit({province: demo7.getValue('nameStr'),
                city:demo8.getValue('nameStr'),
                district: demo9.getValue('nameStr')})

            $('.goabck').css({
                'pointer-events': 'auto'
            })

            paramSubmit = vals
            viewArea = []
            if (paramSubmit.districtId && paramSubmit.provinceId && paramSubmit.cityId){
                oldProvinceId = paramSubmit.provinceId
                oldProvinceName = demo7.getValue('nameStr')

                oldCityId = paramSubmit.cityId
                oldCityName = demo8.getValue('nameStr')

                oldDistrictId = paramSubmit.districtId
                oldDistrictName = demo9.getValue('nameStr')
                level = 'district'
            } else if (paramSubmit.cityId && paramSubmit.provinceId && !paramSubmit.districtId){
                oldProvinceId = paramSubmit.provinceId
                oldProvinceName = demo7.getValue('nameStr')

                oldCityId = paramSubmit.cityId
                oldCityName = demo8.getValue('nameStr')

                level = 'city'
            } else if (paramSubmit.provinceId && !paramSubmit.cityId && !paramSubmit.districtId){
                oldProvinceId = paramSubmit.provinceId
                oldProvinceName = demo7.getValue('nameStr')
                level = 'province'
            }else if (!paramSubmit.provinceId && !paramSubmit.cityId && !paramSubmit.districtId){
                level = 'province'
            }
            init(paramSubmit)

        });

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
                field: 'regionName',
                minWidth: 170,
                title: '区域',
            },
                {
                    field: 'count',
                    minWidth: 270,
                    title: '案件数量',
                    sort: true,
                    style: 'color: #3BA9FF;cursor: pointer;',
                    event: 'getList'
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


        // setTable(_cols, param)

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
                initSort: {
                    field: 'count',
                    type: 'desc'
                },
                done: function (res) {
                    soulTable.render(this)
                    $('button.ll-submit').removeAttr('disabled')
                }
            })
            table.on('tool(test)', function (obj) {
                onRowEvent(obj)
            })

            $('.layui-export').off('click').on('click',function (e) {
                var _this = $('.layui-export')
                _this.attr('disabled',true)
                soulTable.export(myTable,{
                    filename: '区域案件分布报表.xlsx'
                })
                setTimeout(function () {
                    _this.removeAttr('disabled')
                },5000)
            })
        }

        function onRowEvent(obj, pobj) {
            if (obj.event == 'getList'){
                showCaseList(obj.data)
            }
        }

        function showCaseList (data,caseCode){
            var height = $(document).outerHeight()*0.9;
            var width = $(document.body).outerWidth()*0.9;
            var platform=demo1.getValue('valueStr');
            var  caseStatus= demo4.getValue('valueStr');
            // var   caseType= demo5.getValue('valueStr') == '0' ? '' : demo5.getValue(
            //     'valueStr');
            var startTime = $('#startTime').val();
            var endTime= $('#endTime').val();
            var searchType = searchType;
            var areaType=data.areaType;
            var regionType=data.regionType;
            openDialog({
                frame:true,
                title:"详情",
                height:height,
                width:width,
                url:"${ctx}/survey/hzReport/popup?menuCode=directionAreaDistribution&caseStatus="+caseStatus
                    + "&startTime="+startTime+ " 00:00:00&endTime="+endTime+ " 23:59:59&orgId="+platform+"&caseType="+caseType+"&areaType="+areaType+"&regionType="+regionType
            });
        }

        //iframe窗

        function openIframe(){
            layer.open({
                type: 2,
                title: '案件列表',
                area: [_width + 'px', _height + 'px'],
                content: ['./lefan41.html'], //iframe的url，no代表不显示滚动条
            });

        }

        function filterMapSubmit(params) {
            var returnData = []
            if (params.province){
                mapData.map(function(cur){
                    if (cur.name == params.province){
                        if (params.city){
                            var cMapData = cur.districts
                            cMapData.map(function(cur2){
                                if (cur2.name == params.city){
                                    if (params.district){
                                        var dMapData = cur2.districts
                                        dMapData.map(function(cur3){
                                            if (cur3.name == params.district){
                                                returnData =  dMapData
                                            }
                                        })
                                    }else {
                                        returnData =  cMapData
                                    }
                                }else {

                                }
                            })
                        } else {
                            returnData =mapData
                        }
                    } else {

                    }
                })
            } else {
                returnData = mapData
            }

            return returnData

        }
        function filterMap(params) {
            var returnData = []
            if (params.province){
                mapData.map(function(cur){
                    if (cur.name == params.province){
                        if (params.city){
                            var cMapData = cur.districts
                            cMapData.map(function(cur2){
                                if (cur2.name == params.city){
                                    if (params.district){
                                        var dMapData = cur2.districts
                                        dMapData.map(function(cur3){
                                            if (cur3.name == params.district){
                                                returnData =  cur3
                                            }
                                        })
                                    }else {
                                        returnData =  cur2.districts
                                    }
                                }else {

                                }
                            })
                        } else {
                            returnData =cur.districts
                        }
                    } else {

                    }
                })
            } else {
                returnData = mapData
            }

            return returnData

        }

        var viewArea = []
        var params ={
            province: "",
            city:"",
            district:''
        }
        var geoCoordMap = filterMap(params)
        console.log('-geoCoordMap-',geoCoordMap)
        function convertData(data,area){
            var res = [];
            for (var i = 0; i < data.length; i++) {
                geoCoordMap.map(function (cur) {
                    if (cur.name == data[i].name){
                        var geoCoord = cur.center.split(',');
                        res.push({
                            name: data[i].name,
                            value: geoCoord.concat(data[i].value),
                            level: cur.level,
                            adcode: cur.adcode,
                            areaId: data[i].areaId
                        });
                    }
                })
            }
            return res;
        };

        var newData = []
        var option5 = {
            title: {
                text: '方向区域分布报表',
                left: 20,
                top: 10,
                textStyle: {
                    fontSize: 16
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: function (param) {
                    return '方向数<br/>' + param.data.name + ' : ' + param.data.value[2]
                }
            },
            toolbox: {
                feature: {
                    myTool1: {
                        show: true,
                        title: '返回上级',
                        icon: 'image://${ctx}/img/back.png',
                        onclick: function (){
                            goBack()
                        }
                    },
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    restore: {},

                },
                right: 20
            },
            bmap: {
                center: [104.114129, 37.550339],
                zoom: 5,
                roam: true,
                mapStyle: {
                    styleJson: [{
                        'featureType': 'water',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#d1d1d1'
                        }
                    }, {
                        'featureType': 'land',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#f3f3f3'
                        }
                    }, {
                        'featureType': 'railway',
                        'elementType': 'all',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'highway',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#fdfdfd'
                        }
                    }, {
                        'featureType': 'highway',
                        'elementType': 'labels',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'arterial',
                        'elementType': 'geometry',
                        'stylers': {
                            'color': '#fefefe'
                        }
                    }, {
                        'featureType': 'arterial',
                        'elementType': 'geometry.fill',
                        'stylers': {
                            'color': '#fefefe'
                        }
                    }, {
                        'featureType': 'poi',
                        'elementType': 'all',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'green',
                        'elementType': 'all',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'subway',
                        'elementType': 'all',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'manmade',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#d1d1d1'
                        }
                    }, {
                        'featureType': 'local',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#d1d1d1'
                        }
                    }, {
                        'featureType': 'arterial',
                        'elementType': 'labels',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'boundary',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#fefefe'
                        }
                    }, {
                        'featureType': 'building',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#d1d1d1'
                        }
                    }, {
                        'featureType': 'label',
                        'elementType': 'labels.text.fill',
                        'stylers': {
                            'color': '#999999'
                        }
                    },{
                        "featureType": "city",
                        "elementType": "labels.icon",
                        "stylers": {
                            "visibility": "off"
                        }
                    }]
                }
            },
            series: [{
                name: '方向数',
                type: 'scatter',
                coordinateSystem: 'bmap',
                data: [],
                symbolSize: function (val) {
                    return val[2] / 10;
                },
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#c23531'
                    }
                }
            },
                {
                    name: '方向数',
                    type: 'effectScatter',
                    coordinateSystem: 'bmap',
                    data: [],
                    symbolSize: function (val) {
                        return val[2] / 10;
                    },
                    showEffectOn: 'render',
                    rippleEffect: {
                        brushType: 'stroke'
                    },
                    hoverAnimation: true,
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: true
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#c23531',
                            shadowBlur: 10,
                            shadowColor: '#333'
                        }
                    },
                    zlevel: 1
                }
            ]
        };

        var myChart5 = echarts.init(document.getElementById('charts5'));

        myChart5.on('click', function (e) {
            console.log(e.data)
            var paramSubmitCopy = JSON.parse(JSON.stringify(paramSubmit));
            if (e.data.level == 'province'){
                oldProvinceId = e.data.areaId
                oldProvinceName = e.data.name
                level = 'city'
                viewArea.push({province: e.data.name,provinceId:e.data.areaId,cityId: '',level: e.data.level})

                geoCoordMap = filterMap({province: e.data.name})
                Object.assign(paramSubmitCopy,{meunType:'regionalDistributionDetails',provinceId: e.data.areaId,cityId: ''})
                init(paramSubmitCopy)
            }else  if (e.data.level == 'city'){
                level = 'district'
                oldCityId = e.data.areaId
                oldCityName = e.data.name

                var adcode = e.data.adcode.slice(0,3) + '000'
                viewArea.push({province: oldProvinceName,city:e.data.name,provinceId: oldProvinceId, cityId:e.data.areaId,level:e.data.level})
                geoCoordMap = filterMap({province: oldProvinceName,city:e.data.name})
                Object.assign(paramSubmitCopy,{meunType:'regionalDistributionDetails',provinceId:oldProvinceId ,cityId: e.data.areaId})
                init(paramSubmitCopy)
            } else {
                return;
            }
            console.log('--1--level',level)

        })

        $(window).resize(function () { //这是能够让图表自适应的代码
            myChart5.resize();

        });
        init(paramSubmit)

        function init(param) {
            console.log('---init---viewArea',viewArea)
            console.log('---init---level',level)
            $.ajax({
                url: "${ctx}/survey/hzReport/getDetail",
                data: param,
                success: function (res) {
                    res = JSON.parse(res)
                    if (res.isSuccess){
                        var list = res.results.list
                        list.sort(sortByCount)
                        if (list.length){
                            var newLIst = list.map(function(cur){
                                return {
                                    name: cur.regionName,
                                    value: cur.count,
                                    areaId: cur.regionNameId
                                }
                            })
                            newLIst.sort(sortData)
                            var maxNum = newLIst[0].value
                            setMyEcharts(newLIst)

                            var html = "";
                            var newData = [];


                            for(var i = 0 ; i < list.length; i ++ ){
                                newData.push({
                                    name: list[i].regionName,
                                    value: list[i].count,
                                });
                                var active = 'active'
                                if((param.districtId && !param.meunType ) || (param.cityId  && param.meunType)){
                                    active = ''
                                }
                                html += "<div class='li "+ active+"' data-name='"+list[i].regionName+"' data-id='"+list[i].regionNameId+"'><div class='name' title='"+list[i].regionName+"'>"+list[i].regionName+"</div><div class='num-text'>"+list[i].count+"</div><div class='num-line'><div class='line-icon' style='width: "+(list[i].count/maxNum * 100 > 1 ? list[i].count/maxNum * 100 : 1).toFixed(2) + "%'></div></div><div class='num-rate'>"+list[i].proportion+"%</div></div>";
                            }
                            var pageTotal = Math.ceil(list.length / 12);
                            $("#span_page_total").text(pageTotal);
                            $("#span_page_index").text(1);
                            $("#div_page_total").attr("data-page",pageTotal);
                            $("#div_page_total2").attr("data-page",pageTotal);
                            $("#charts5_table .bar").html(html);

                            $("#div_page_total").attr("data-index",1);
                            $("#div_page_total2").attr("data-index",1);
                            $("#charts5_table .bar").attr('style','')
                        }else{
                            option5.series[0].data = []

                            option5.series[1].data = []

                            option5.bmap.center = [104.114129, 37.550339]
                            option5.bmap.zoom = 5
                            myChart5.setOption(option5);
                            $("#span_page_index").text(1)
                            $("#span_page_total").text(1);
                            $("#div_page_total").attr("data-page",1);
                            $("#div_page_total2").attr("data-page",1);
                            $("#charts5_table .bar").html('<div class="lf-none">暂无数据</div>');

                            $("#div_page_total").attr("data-index",1);
                            $("#div_page_total2").attr("data-index",1);
                            $("#charts5_table .bar").attr('style','')
                        }
                    }
                    $('.bar').css({
                        'pointer-events': 'auto'
                    })
                }
            })
        }

        function sortByCount(a, b) {
            return b.count - a.count
        }

        function setMyEcharts(newData) {
            var newDataBySort= newData.sort(sortData)
            var maxNum = 10
            if (newData.length){
                maxNum = newDataBySort[0].value
            }

            option5.series[0].symbolSize = function(val){
                return val[2] / (maxNum / 10);
            };

            option5.series[1].symbolSize = function(val){
                return val[2] / (maxNum / 20);
            };
            option5.series[1].data = convertData(newData.sort(function (a, b) {
                return b.value - a.value;
            }).slice(0, 6));

            var arrLoc = convertData(newData)
            var x_s = []
            var y_s = []
            arrLoc.map(function (m) {   x_s.push(m.value[0])
                y_s.push(m.value[1]) })
            x_s.sort(sortNumber)
            y_s.sort(sortNumber)
            var xMin = Number(x_s[0])
            var xMax = Number(x_s.reverse()[0])
            var yMin = Number(y_s[0])
            var yMax = Number(y_s.reverse()[0])
            option5.bmap.center = arrLoc.length > 0 ?[(xMax + xMin) / 2, (yMax + yMin) / 2] :[104.114129, 37.550339]
            option5.bmap.zoom = arrLoc.length > 0 ? getZoom(xMax, xMin, yMax, yMin) : 5;
            console.log('--zoom--',getZoom(xMax, xMin, yMax, yMin))
            option5.series[0].data = newData.length > 6 ? convertData(newData.sort(function (a, b) {
                return b.value - a.value;
            }).slice(6)) : [];
            myChart5.setOption(option5);
        }

        $('.btn-icon').click(function () {
            var _this = $(this)
            var index = Number(_this.attr('data-index'))
            var barH = 612
            var screenWidth = $(document).width()
            if (screenWidth < 1210){
                barH = 408
            }
            if(_this.attr('id') == 'div_page_total'){ //下一页

                if (_this.attr('data-page') <= index) {
                    alert('最后一页')
                    return;
                }
                var _bar = $('.bar')
                _bar.attr('style', '')

                _bar.css({
                    'transform': 'translateY(-' + barH * index + 'px)'
                })
                $('.btn-icon').attr('data-index', index + 1)
                $('.btn-page span').eq(0).text(index + 1)
            }
            if(_this.attr('id') == 'div_page_total2'){ //上一页
                if (index == 1) {
                    alert('第一页')
                    return;
                }
                var _bar = $('.bar')
                _bar.attr('style', '')
                _bar.css({
                    'transform': 'translateY(-' + barH * (index - 2) + 'px)'
                })
                $('.btn-icon').attr('data-index', index - 1)
                $('.btn-page span').eq(0).text(index - 1)
            }
        })
        $('.bar').on('click','.li', function (e) {
            var _this = $(this)
            var paramSubmitCopy = JSON.parse(JSON.stringify(paramSubmit))
            var areaName = _this.attr('data-name')
            var areaId = _this.attr('data-id')
            $('.bar').css({
                'pointer-events': 'none'
            })
            setTimeout(function(){
                $('.bar').css({
                    'pointer-events': 'auto'
                })
            }, 1500)
            console.log('--2--level',level)

            if (level == 'province'){
                level = 'city'
                oldProvinceId = areaId
                oldProvinceName = areaName

                viewArea.push({province: areaName,provinceId:areaId,cityId: '',level: 'province'})

                geoCoordMap = filterMap({province: areaName})
                Object.assign(paramSubmitCopy,{meunType:'regionalDistributionDetails',provinceId: areaId, cityId: ''})
            }else if(level == 'city'){
                level = 'district'
                oldCityId = areaId
                oldCityName = areaName

                viewArea.push({province: oldProvinceName,city:areaName,provinceId: oldProvinceId, cityId:areaId,level:'city'})

                geoCoordMap = filterMap({province: oldProvinceName,city:areaName})
                Object.assign(paramSubmitCopy,{meunType:'regionalDistributionDetails',provinceId:oldProvinceId,cityId: areaId})
            }else {
                return;
            }
            console.log('--2-2--level',level)
            console.log(paramSubmit)

            init(paramSubmitCopy)


        })
        $('.goback').click(function () {
            goBack()
        })
        function goBack(){
            console.log('--back2--level',level)
            console.log('/paramSubmit',paramSubmit)
            if (level == 'province'||(paramSubmit.provinceId && paramSubmit.cityId && !paramSubmit.districtId && level == 'city') ||( paramSubmit.provinceId && paramSubmit.cityId && paramSubmit.districtId && level == 'district')){
                layer.msg('已经是最高级了',{
                    time: 2000,
                    icon: 5
                })
                return;
            }
            var goabck = $('.goback')
            goabck.css({
                'pointer-events': 'none'
            })
            var paramSubmitCopy = JSON.parse(JSON.stringify(paramSubmit))
            var delArea = viewArea.pop()
            if (level== 'city'){
                level = 'province'
                geoCoordMap = filterMap({province: '',city:''})
                if (paramSubmit.provinceId){
                    paramSubmitCopy = JSON.parse(JSON.stringify(paramSubmit))
                }else if (paramSubmit.provinceId && !paramSubmit.cityId){
                    return;
                }else {
                    Object.assign(paramSubmitCopy,{meunType:'regionalDistributionDetails',provinceId: '', cityId: ''})
                }
                delete paramSubmitCopy.meunType

            }else if(level== 'district') {
                level = 'city'
                geoCoordMap = filterMap({province:delArea.province})
                if (paramSubmit.cityId){
                    paramSubmitCopy = JSON.parse(JSON.stringify(paramSubmit))
                }else if (paramSubmit.cityId && !paramSubmit.districtId){
                    return;
                }else {
                    Object.assign(paramSubmitCopy,{meunType:'regionalDistributionDetails',provinceId: delArea.provinceId, cityId: ''})
                }
            }
            console.log('--back2--2--level',level)
            init(paramSubmitCopy)
            setTimeout(function(){
                goabck.css({
                    'pointer-events': 'auto'
                })
            },500)
        }
        $('.chartsm-info .btn').click(function () {
            var i = Number($('input.page-num').val())
            if (!i) {
                alert('请输入页数')
                return;
            }
            var _this = $(this)
            var _bar = $('.bar')
            if (i > Number($('.btn-page span').eq(1).text())) {
                alert('没有这一页')
                return;
            }
            _bar.attr('style', '')
            _bar.css({
                'transform': 'translateY(-' + 611 * (i-1) + 'px)'
            })
            _this.attr('data-index', i)
            $('.btn-page span').eq(0).text(i);
            $('.btn-icon').attr('data-index', i);

        })

        function sortNumber(a, b) {
            return a - b
        }
        function sortData(a, b) {
            return b.value - a.value
        }
        function getZoom (maxLng, minLng, maxLat, minLat) {
            var map = new BMap.Map("container");
            // var zoom = ["50","100","200","500","1000","2000","5000","10000","20000","25000","50000","100000","200000","400000","800000","1500000"]//级别18到3。
            var zoom = ["1000","3000","6000","10000","20000","30000","40000","50000","60000","80000","100000","150000","200000","400000","800000","1500000"]//级别18到3。
            var pointA = new BMap.Point(maxLng,maxLat);
            var pointB = new BMap.Point(minLng,minLat);
            var distance = map.getDistance(pointA,pointB).toFixed(1);
            console.log(distance)
            for (var i = 0,zoomLen = zoom.length; i < zoomLen; i++) {
                if(zoom[i] - distance > 0){
                    return 18-i+3 > 11 ? 11: 18-i+3;
                }
            };
            return 5;
        }

        var chartsH = $(document).height() - $('.searchs').outerHeight() - 50
        $('.echarts-contain').height(chartsH)


    })
</script>

</body>

</html>