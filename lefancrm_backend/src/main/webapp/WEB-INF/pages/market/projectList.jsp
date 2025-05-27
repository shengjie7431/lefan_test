<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>项目管理</title>
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
            width: auto;
            padding: 6px 15px;
            padding-left: 0;
            margin-bottom: 0;
        }

        .layui-form-item .layui-input-inline {
            width: 150px !important;
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

        /*.layui-layer.layui-layer-page {*/
        /*width: 98% !important;*/
        /*left: 1% !important;*/
        /*top: 20px !important;*/
        /*}*/

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
            color: #EC1515;
        }

        .color2 {
            color: #FEAD3B;
        }
        .color3 {
            color: #398800;
        }

        .color4 {
            color: #00C3AF;
        }

        .color5 {
            color: #3ba9ff;
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
            width: 16px;
            height: 16px;
            padding: 1px;
            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAFD0lEQVR4Xu1bPXLbRhT+HjRDOUkR+QSWTxC6c8Ai1AkiNSZdST6B5ROEOoHlE0iuTLgRfQIpBRF3pk5g+QRBCmpszxAvswjkAVcA9hcIxyJa7r6fb9//Lgl3/KM7rj/WAKwt4I4jsHaBNg3g8Rvepg08IEZf5suMBAFmvMCn90/pqi25GrWA7hlv/fAF+0ToEzKltzQVSxi4IGAy7+DdbI8SzX3GyxoB4PEb7gcBnhOwayxRyQYGJmmKV++f0oUPekUaXgHIFX9JQNe3oBk9xsWCceQTCC8ACN8OAgjFvZy4CjxmnF5v4oUP13AG4NeIdwPgxMC/Vfrp/p4sUuy5WoMTAL2IXwI41JW4iXVMeBY/oVNb2tYAhGM+IcKBLWOf+4RLxEN6ZkPTCoBVUv5GaVsQjAEIx3xMhOc2aDe+h/FiOqRjEz5GAOQB78yEQdtrFyl2TAKjNgAi1W0E+OAr2jPwD4CZBFCXgJ8dQUvmHTzUTZHaAPTGfA66XcNbCPuaCcfxE5KVz0iFb7lLnGWWfQvaeb2ESTygPZ39WgD4MH1x4mmKXV3zzKvKia1F6LqCFgDhmD8SYVsH0bI1mbkT+lWnXkU3twbhduYf42I6pB3VRiUA4Vs+IM4qPesvBfb+GtCkSKAX8T4Du8R5h0iYzTs4kn03jHhEwB82zHWsQA1AxB9cmhsG/owHtNT/19QRCRN2ipaSB9+PNgAw4108pNr+pBYAF+bfBJZys9KiSky3N+YZCL/YgDDv4H5dRqgFoDfmQxBEvW/9MeFR8UTDMU+I8HsdwemAluQKIxbDkd+shFAUR7UAODHOpZWV6UX8t6qWWKR4WByLucihcoN6C4iYrVAvbEql2iFgKKc6Xi0ASKYDul+lRyUAIg9vBDh3BcB0PzNexUNaarF1rKaOj2xRxbWVACiDlalmOusZl/NN9ItBy0cRVpcOqwFwyL86ut5aU6K8WBM6pmFBg4GjeECjMrlWA4Aq5T0NXewAaKvvL1Fe3Cf8+BUnvoasKwsAMz5db6Jb9Plc+XOX6lM2dTsAWogBcnBqQvmVjQHi9OMhLXWYYcRnvsy+aAV2FuChC6zLBnK+99J3VDC0SoNNF0Lyqbi0vaq0K/cjWoWQ8MefvkLU7Y18bQEghjHxgCpvpVXdoHUb2ghqFkSdmqGVvgPQBcOpHf5vQms3k1MJyLhMCSNOkVCALWKIC5cHqm2mv9c1QoKWciTmMo2pElb45XUH28UCyGkAWsGobBwnL1UC0ERXWOWXvsHWuTlWApB1ZGO+8mmeVSfjOn5fKn5KCi2jbrC4uAkrkIsTH31/UeayUbw1AHlfbj+YrI5cxwwkYGz7fGug4/s3Imm5QAZAkxnBNLTXrM+v4Lq6bw21ARA8fYzJPepaSkon8GmVwlWC9iIW73Gsb24bBuD1dEBGz3aMLOBGeJ3LjYYVLSNvrLxWIVTGKR9ciKtru9sa/+hYKW8NwI38K+IO1so7A/B/Bsb8zcGhyxtBLwB8S5EpTm1vcE09QuR5EITypc9sTOhZBcEqBqJiRIqRz7JZLm8RYOR66k5pUAfdrHROIa7Wre70b/FgXHKQPayyfhJbJbdXC5CZiOoRKQ6yP0uYgiGUpux/Aqe6VZ3O4chrGgWgyEykznuf0d0gdJnyd0Gc/6+A8veChKt0gavP9zDTfedno3TjLuAqVJv7W7OANpUy4bUGwASt73Ht2gK+x1M10elf2OdYX4SLc6gAAAAASUVORK5CYII=);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }

        .layui-table-header .layui-table-cell{
            display: flex;
            align-items: center;
        }

        .icon-about{
            margin-left: 6px;
            display: inline-block;
            width: 18px;
            height: 18px;
            background: url(data:image/png;base64,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);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }

        .project{
            width: 400px;
            margin: 0 auto;
            padding: 10px 0;
        }
        .project .layui-inline{
            display: block;
            padding: 10px 0;
        }
        .project .layui-input-inline{
            width: 200px;
        }
        .project .layui-input{
            height: 32px;
            line-height: 32px;
        }
        .edit{
            color: #45B4FE;
        }
        .lf-btns{
            padding-top: 30px;
            width: 100%;
            display: flex;
        }
        .lf-btns .lf-btn{
            width: 130px;
            height: 32px;
            line-height: 32px;
            border:1px solid #3BA9FF;
            color: #3BA9FF;
            text-align: center;
            margin-right: 20px;
            cursor: pointer;
        }
        .lf-btns .lf-btn.active{
            background-color:#3BA9FF;
            color: #fff;
        }
        .poi-no{
            pointer-events: none;
        }
        .paramTime{
            width: 180px
        }
    </style>
</head>

<body>
<div class="main">
    <input type="hidden" value='${params.surveyInvestigatorDtoListJson}' id="surveyInvestigatorDtoListJson" />
    <div class="layui-form searchs" lay-filter="search">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">项目名称</label>
                <div class="layui-input-inline _input">
                    <input type="text" name="projectName" placeholder="请输入项目名称"  autocomplete="off" class="layui-input projectName">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">行业归属:</label>
                <div class="layui-input-inline">
                    <div id="projects1" class="selectMul"></div>
                </div>

            </div>
            <div class="layui-inline">
                <label class="layui-form-label">司内业务属性:</label>
                <div class="layui-input-inline">
                    <div id="projects2" class="selectMul"></div>
                </div>

            </div>
            <div class="layui-inline">
                <label class="layui-form-label">项目经理:</label>
                <div class="layui-input-inline">
                    <div id="projects3" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">项目进度:</label>
                <div class="layui-input-inline">
                    <div id="projects4" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">时间:</label>
                <div class="layui-input-inline">
                    <div class="data-choose">
                        <input type="text" class="layui-input paramTime" readonly id="startTime"
                               placeholder="请选择日期">
                        <span class="dc-span">至</span>
                        <input type="text" class="layui-input paramTime" readonly id="endTime"
                               placeholder="请选择日期">
                    </div>
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 20px;">
                <button lay-submit class="ll-submit layui-btn layui-btn-normal layui-btn-sm layui-btn-radius"
                        lay-filter="submit" style="width: 86px">查询 <i
                        class="layui-icon layui-icon-search"></i></button>
                <button class="ll-submit layui-btn layui-btn-normal layui-btn-sm layui-btn-radius layui-btn-add"
                        style="width: 86px">新增 <i class="layui-icon llayui-icon-add-1"></i></button>
                <button class="ll-submit layui-btn layui-btn-normal layui-btn-sm layui-btn-radius layui-btn-export"
                        style="width: 86px">导出 <i class="layui-icon llayui-icon-add-1"></i></button>
            </div>
        </div>
    </div>
    <div class="table-content">
        <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">
        </table>
    </div>
    <%--<input type="hidden" value='${params.companysJson}' id="companysJson">--%>
</div>


<script type="text/html" id="table-content-child-h">
    <div class="table-content-child">
        <table class="layui-table" id="test-child" lay-filter="test-child" lay-skin="line" lay-size="lg" style="margin: 0">
        </table>
    </div>
</script>
<script type="text/html" id="addProject">
    <div class="project">
        <div class="layui-form">
            <div class="layui-inline">
                <label class="layui-form-label">项目名称</label>
                <div class="layui-input-inline _input">
                    <input type="text" name="projectName" placeholder="请输入项目名称"  autocomplete="off" class="layui-input projectNameD">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">行业归属:</label>
                <div class="layui-input-inline">
                    <div id="projects21" class="selectMul"></div>
                </div>

            </div>
            <div class="layui-inline">
                <label class="layui-form-label">司内业务属性:</label>
                <div class="layui-input-inline">
                    <div id="projects22" class="selectMul"></div>
                </div>

            </div>
            <div class="layui-inline">
                <label class="layui-form-label">项目经理:</label>
                <div class="layui-input-inline">
                    <div id="projects23" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">项目进度:</label>
                <div class="layui-input-inline">
                    <div id="projects24" class="selectMul"></div>
                </div>
            </div>
            <div class="lf-btns" style="justify-content: center">
                <div class="lf-btn " data-type="1">取消</div>
                <div class="lf-btn active" data-type="2" id="lf-btn-active">保存并关闭</div>
            </div>
        </div>

    </div>
</script>


<script src="${ctx}/js/jquery-3.4.1.js" charset="utf-8"></script>
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
            el: '#projects1',
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
            el: '#projects2',
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

        var demo3 = xmSelect.render({
            el: '#projects3',
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
        var demo4 = xmSelect.render({
            el: '#projects4',
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

        filterJson(demo1, [{
            name: '保险公司-寿险公司',
            value:1
        },{
            name: '保险公司-产险公司',
            value:2
        },{
            name: '保险公司-健康险公司',
            value:3
        },{
            name: '互助计划',
            value:4
        }], 'value', 'name', false, false)
        filterJson(demo2, [{
            name: '保险公司',
            value:1
        },{
            name: '互助机构',
            value: 2
        }], 'value', 'name', false, false)
        filterJson(demo4, [{
            name: '已接洽',
            value:1
        },{
            name: '已投标（谈判）',
            value: 2
        },{
            name: '已签约',
            value: 3
        },{
            name: '已移交',
            value: 4
        }], 'value', 'name', false, false)

        var surveyInvestigatorDtoListJson = $("#surveyInvestigatorDtoListJson").val();
        surveyInvestigatorDtoListJson = JSON.parse(surveyInvestigatorDtoListJson);
        filterJson(demo3, surveyInvestigatorDtoListJson, 'userId', 'realName', false, false)
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
                if (value){
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
            Object.assign(paramSubmit, {
                menuCode: 'queryAll',
                projectNameLike:$('.projectName').val(),
                industryAttributesIds:demo1.getValue('valueStr'),
                businessAttributesIds:demo2.getValue('valueStr'),
                managerIds:demo3.getValue('valueStr'),
                managerStates:demo4.getValue('valueStr'),
                startTime: $('#startTime').val(),
                endTime: $('#endTime').val(),
            })
            reloadTable(_cols, paramSubmit)
        });

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

        //重载表格数据
        function reloadTable(_cols, _data) {
            $('.table-content').empty()
            $('.table-content').append(
                ' <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg"></table>'
            )
            setTable(_cols, _data)
        }


        var _cols = [
            [ {
                field: 'projectName',
                minWidth: 150,
                title: '项目名称',
                templet: function (d) {
                    if (d.businessAttributesId == 1 && d.managerState == 4){
                        return '<div class="color5" lay-event="viewReason1">'+d.projectName+'</div>'
                    }else {
                        return d.projectName
                    }
                }
            }, {
                field: 'industryAttributes',
                minWidth: 150,
                title: '行业归属',
            }, {
                field: 'businessAttributes',
                minWidth: 150,
                title: '司内业务属性',
            }, {
                field: 'managerName',
                minWidth: 150,
                title: '项目经理',
            }, {
                field: 'managerState',
                minWidth: 150,
                title: '项目进度',
                templet: function (d) {
                    var _html = ''
                    if  (d.managerState == 1){
                        _html = '<div class="color1">已接洽</div>'
                    }else if  (d.managerState == 2){
                        _html = '<div class="color2">已投标（谈判）</div>'
                    }else if  (d.managerState == 3){
                        _html = '<div class="color3">已签约</div>'
                    }else if  (d.managerState == 4){
                        _html = '<div class="color4">已移交</div>'
                    }
                    return _html
                }
            }, {
                field: 'entrustCaseNum',
                minWidth: 150,
                title: '委托案件数',
                sort: true
            }, {
                field: 'finishCaseNum',
                minWidth: 150,
                title: '已结案件数',
                sort: true
            }, {
                field: 'imgMoney',
                minWidth: 150,
                title: '已开票金额',
                sort: true
            }, {
                field: 'imgAccountMoney',
                minWidth: 150,
                title: '开票已到账金额',
                sort: true
            }, {
                field: 'imgNoAccountMoney',
                minWidth: 150,
                title: '开票未到账金额',
                sort: true
            },{
                field: 'createTime',
                minWidth: 180,
                title: '创建时间',
                sort: true,
                templet: function (d) {
                    return layui.util.toDateString(d.createTime, 'yyyy-MM-dd HH:mm:ss')
                }
            }, {
                title: '操作',
                width: 150,
                event: 'edit',
                fixed: 'right',
                templet: function (d) {
                    if (d.managerState != 4){
                        return '<div class="edit">编辑</div>'
                    }else {
                        return '';
                    }
                }
            }]
        ]
        var paramSubmit= {
            menuCode: 'queryAll',
            projectNameLike:$('.projectName').val(),
            industryAttributesIds:demo1.getValue('valueStr'),
            businessAttributesIds:demo2.getValue('valueStr'),
            managerIds:demo3.getValue('valueStr'),
            managerStates:demo4.getValue('valueStr'),
        }
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
                even: true,
                cols: _cols,
                page: false,
                limit: 200,
                height: fullH,
                drag: false,
                totalRow: true,
                url: '${ctx}/projectManagement/details',
                where: param,
                parseData: function (res) {
                    return {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results,
                    }
                },
                done: function (res) {
                    soulTable.render(this)
                    $('button.ll-submit').removeAttr('disabled')
                }
            })
            table.on('tool(test)', function (obj) {
                if (obj.event == 'edit' && obj.data.managerState != 4){
                    openIndex = layer.open({
                        type: 1,
                        title: '编辑项目',
                        area: ['500px','600px'],
                        content: $('#addProject').html(),
                        success: function () {
                            setProject('updateOne',obj)
                        }
                    });
                }
                if (obj.event == 'viewReason1'){
                    var url = "${ctx}/survey/report/index?menuCode=entrust&pageSize=50&entrustOrgId="+obj.data.entrustOrgId
                    parent.addTab("总部报表",url,true);
                }
            })


        }

        function setProject(type, obj){
            demo21 = xmSelect.render({
                el: '#projects21',
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
                data: []
            })

            demo22 = xmSelect.render({
                el: '#projects22',
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
                data: []
            })

            demo23 = xmSelect.render({
                el: '#projects23',
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
                data: []
            })
            demo24 = xmSelect.render({
                el: '#projects24',
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
                data: []
            })

            filterJson(demo21, [
                {
                    name: '保险公司-寿险公司',
                    value:1
                },{
                    name: '保险公司-产险公司',
                    value:2
                },{
                    name: '保险公司-健康险公司',
                    value:3
                },{
                    name: '互助计划',
                    value:4
                }], 'value', 'name', false, false)
            filterJson(demo22, [
                {
                    name: '保险公司',
                    value:1
                },{
                    name: '互助机构',
                    value: 2
                }], 'value', 'name', false, false)
            filterJson(demo24, [
                {
                    name: '已接洽',
                    value:1
                },{
                    name: '已投标（谈判）',
                    value: 2
                },{
                    name: '已签约',
                    value: 3
                },{
                    name: '已移交',
                    value: 4
                }], 'value', 'name', false, false)

            var surveyInvestigatorDtoListJson = $("#surveyInvestigatorDtoListJson").val();
            surveyInvestigatorDtoListJson = JSON.parse(surveyInvestigatorDtoListJson);
            filterJson(demo23, surveyInvestigatorDtoListJson, 'userId', 'realName', false, false)
            $('.lf-btns .lf-btn.active').attr('data-type',type)
            if (obj){
                $('.lf-btns .lf-btn.active').attr('data-id',obj.data.id)
                $('.projectNameD').val(obj.data.projectName)
                demo21.setValue([obj.data.industryAttributesId])
                demo22.setValue([obj.data.businessAttributesId])
                demo23.setValue([obj.data.managerId])
                demo24.setValue([obj.data.managerState])
            }
        }
        $('body').on('click', '.lf-btn', function () {
            var _this = $(this)
            if (_this.hasClass('active')){
                var param ={
                    menuCode:_this.attr('data-type'),
                    projectName:$('.projectNameD').val(),
                    industryAttributesId:demo21.getValue('valueStr'),
                    businessAttributesId:demo22.getValue('valueStr'),
                    managerId:demo23.getValue('valueStr'),
                    managerState:demo24.getValue('valueStr'),
                    industryAttributes:demo21.getValue('nameStr'),
                    businessAttributes:demo22.getValue('nameStr'),
                    managerName:demo23.getValue('nameStr'),
                }
                if (_this.attr('data-type') == 'updateOne'){
                    Object.assign(param,{id: _this.attr('data-id')})
                }
                if (!param.projectName){
                    layer.msg('请输入项目名称！',{icon: 5})
                    return;
                }
                if (!param.industryAttributesId){
                    layer.msg('请选择行业归属！',{icon: 5})
                    return;
                }
                if (!param.businessAttributesId){
                    layer.msg('请选择司内业务属性！',{icon: 5})
                    return;
                }
                if (!param.managerId){
                    layer.msg('请选择项目经理！',{icon: 5})
                    return;
                }
                if (!param.managerState){
                    layer.msg('请选择项目进度！',{icon: 5})
                    return;
                }
                _this.addClass('poi-no')
                setTimeout(function () {
                    _this.removeClass('poi-no')
                }, 1000)
                $.ajax({
                    url: '${ctx}/projectManagement/details',
                    data: param,
                    success: function (res) {
                        res = JSON.parse(res)
                        _this.removeClass('poi-no')
                        if (res.isSuccess){
                            layer.msg('操作成功',{icon: 1, time: 1500},function () {
                                location.reload()
                            })


                        }else {
                            layer.msg(res.results,{icon: 5, time: 1500})
                        }

                    }
                })
            }else{

                layer.close(openIndex)
            }
        })
        $('.layui-btn-add').click(function () {
            console.log(123131)
            openIndex = layer.open({
                type: 1,
                title: '新增项目',
                area: ['500px','600px'],
                content: $('#addProject').html(),
                success: function () {
                    setProject('insertOne')
                }
            });
        })

        $('.layui-btn-export').click(function () {
            Object.assign(paramSubmit, {
                menuCode: 'queryAll',
                projectNameLike:$('.projectName').val(),
                industryAttributesIds:demo1.getValue('valueStr'),
                businessAttributesIds:demo2.getValue('valueStr'),
                managerIds:demo3.getValue('valueStr'),
                managerStates:demo4.getValue('valueStr'),
            })
            location.href = '${ctx}/projectManagement/export?menuCode=queryAll&projectNameLike='
                +paramSubmit.projectNameLike+"&industryAttributesIds="+paramSubmit.industryAttributesIds+"&businessAttributesIds="+paramSubmit.businessAttributesIds
                +"&managerIds="+paramSubmit.managerIds+"&managerStates="+paramSubmit.managerStates+"&startTime="+$('#startTime').val()+"&endTime="+$('#endTime').val();
        })
    })
</script>



</body>

</html>