<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/layui/layui_exts/soulTable.css">
    <style>
        #dialogId{
            z-index: 198910170;
            position: fixed;
        }
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
            width: 130px;
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
        .table-content-child{
            width: 98%;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<div class="main">
    <div class="layui-form searchs" lay-filter="search">
        <input type="hidden" value='${dropDownJson.franchiseesJson}'  id="franchiseesJson" />
        <input type="hidden" value='${dropDownJson.investigatorJson}' id="investigatorsJson" />

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
                <div class="d-flex-wrap">
                    <label class="layui-form-label" style="width: 128px;">保司终审通过时间</label>
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
            <div class="layui-inline" style="margin-left: 20px;">
                <button lay-submit class="ll-submit layui-btn layui-btn-normal  layui-btn-sm layui-btn-radius" lay-filter="submit"
                        style="width: 86px">查询 <i class="layui-icon layui-icon-search"></i></button>
                <button class="ll-submit layui-btn layui-btn-normal layui-btn-sm layui-btn-radius layui-export"
                        style="width: 86px">导出 <i class="layui-icon layui-icon-export"></i></button>
            </div>
            <!-- <div class="layui-inline">
                <label class="layui-form-label">
                    <button type="reset" class="layui-btn s-btn" data-type="reset">重置</button>
                </label>
            </div> -->
        </div>
    </div>
    <div class="table-content">
        <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg" style="margin: 0">
        </table>
    </div>
</div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/html" id="table-content-child-h">
    <div class="table-content-child">
        <table class="layui-table" id="test-child" lay-filter="test-child" lay-skin="line" lay-size="lg" style="margin: 0">
        </table>
    </div>
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
            'd': today.getDate()
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
        endTime = '';


    layui.use(['form', 'table', 'soulTable', 'xmSelect', 'laydate'], function () {
        var soulTable = layui.soulTable,
            table = layui.table,
            form = layui.form,
            xmSelect = layui.xmSelect,
            laydate = layui.laydate,
            $ = layui.$;

        var demo2 = xmSelect.render({
            el: '#surveyOrgId',
            theme: {
                color: '#3BA9FF'
            },
            toolbar: {
                show: true
            },
            size: 'small',

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
                    url: '${ctx}/survey/report/getDataList',
                    data: {
                        menuCode: 'positiveReward',
                        menuType: 'investigator',
                        orgIds: valArr.join(',')
                    },
                    success: function(res){
                        res = JSON.parse(res)
                        if (res.isSuccess){
                            _len = res.results.length
                            filterJson(demo3, res.results,'userId', 'realName', false, true)
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
            toolbar: {
                show: true
            },
            size: 'small',

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
        //初始化赋值
        //需要后台返回

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
        filterJson(demo2, franchiseesJson,'id', 'name', false, true)

        var investigatorsJson = $("#investigatorsJson").val();
        investigatorsJson = JSON.parse(investigatorsJson);
        filterJson(demo3, investigatorsJson,'userId','realName',false, true)

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
        var paramSubmit = {
            menuCode : 'positiveReward',
            menuType:'reportdata',
            orgIds:'',
            userIds:'',
            startTime: $('#startTime').val(),
            endTime: $('#endTime').val(),
        }
        form.on('submit(submit)', function (data) {
            var vals = data.field
            delete vals.select;
            var claimsman = demo3.getValue('valueStr')
            claimsman = claimsman ? claimsman.split(',') : []
            Object.assign(vals, {
                menuCode : 'positiveReward',
                menuType:'reportdata',
                orgIds: demo2.getValue('valueStr'),
                userIds: claimsman.length == 0 ? '' : demo3.getValue('valueStr'),
                startTime: $('#startTime').val(),
                endTime: $('#endTime').val(),
            })
            paramSubmit = vals
            reloadTable(_cols, vals)

        });

        //重载表格数据
        function reloadTable(_cols, param) {
            $('.table-content').empty()
            $('.table-content').append(
                ' <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg" style="margin: 0"></table>'
            )
            setTable(_cols, param)
        }

        var _cols = [
            [{
                field: 'realName',
                minWidth: 200,
                title: '调查员',
            },{
                field: 'investigationName',
                minWidth: 270,
                title: '机构名称',
            },
               {
                field: 'sunMoney',
                minWidth: 200,
                title: '阳性奖励',
                sort: true,
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'getListU',
            },
            ]
        ]



        var myTable = ''
        setTable(_cols, paramSubmit)
        function setTable(_cols, param) {
            $('button.ll-submit').attr('disabled', true)
            setTimeout(function () {
                $('button.ll-submit').removeAttr('disabled')
            }, 6000)
            var _h = $('.searchs').outerHeight() + 40
            var fullH = 'full-' + _h
            myTable = table.render({
                id: "test",
                elem: '#test',
                drag:false,
                even: true,drag: false,
                cols: _cols,
                cellMinWidth: 80,
                page:false,
                limit: 10,
                height: fullH,
                url: '${ctx}/survey/report/getDataList',
                where: param,
                parseData: function(res){
                    return  {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results
                    }
                },
                overflow: {
                    type: 'tips',
                    hoverTime: 100,
                    color: '#333',
                    bgColor: '#fafafa',
                    minWidth: 100,
                    maxWidth: 500,
                },
                done: function () {
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
                // table.exportFile(myTable.config.id, myTable.config.data, 'xlsx')
                soulTable.export(myTable,{
                    filename: '阳性奖励报表.xlsx'
                })
                setTimeout(function () {
                    _this.removeAttr('disabled')
                },5000)
            })

        }

        function onRowEvent(obj, pobj,name) {
            if (obj.event == "getListU") {
                getChild({
                    menuCode : 'positiveReward',
                    menuType:'reportdataDetails',
                    userId: obj.data.userId,
                    startTime: $('#startTime').val(),
                    endTime: $('#endTime').val(),})
            }

        }

        function getChild(param){
            var _height = $(document).height() * 0.9
            var _width = $(document).width() * 0.98
            openIndex = layer.open({
                type: 1,
                title: '案件列表',
                area: [_width + 'px', _height + 'px'],
                content: $('#table-content-child-h').html(),
            });
            var _cols_child =  [
                [{
                    field: 'surveyCaseNo',
                    minWidth: 150,
                    title: '案件编号',
                    style: 'color: #3BA9FF;cursor: pointer;',
                    event: 'caseInfo',
                }, {
                    field: 'surveyPerson',
                    minWidth: 150,
                    title: '被调查人',
                }, {
                    field: 'entrustedName',
                    minWidth: 150,
                    title: '保险公司',
                }, {
                    field: 'sunMoney',
                    minWidth: 150,
                    title: '阳性奖励',
                    sort: true,
                }, {
                    field: 'sunTime',
                    minWidth: 150,
                    title: '发现阳性时间',
                    sort: true,
                    templet: function (d) {
                       return d.sunTime ? dateFormat(d.sunTime, 'yyyy-MM-dd HH:mm:ss') : ''
                    }
                }, {
                    field: 'entrustReportEndDate',
                    minWidth: 150,
                    title: '保司终审通过时间',
                    sort: true,
                    templet: function (d) {
                        return d.entrustReportEndDate ? dateFormat(d.entrustReportEndDate, 'yyyy-MM-dd HH:mm:ss') : ''
                    }
                }]]
            table.render({
                id: "test-child",
                elem: '#test-child',
                cols: _cols_child,
                page: false,
                limit: 10000,
                height: 'full-100',
                drag: false,
                url: '${ctx}/survey/report/getDataList',
                where: param,
                even: true,
                parseData: function(res){
                    return  {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results
                    }
                },
            })

        }

        table.on('tool(test-child)', function (obj) {
            if (obj.event == 'caseInfo') {
                var height = $(document).outerHeight()*0.9;
                var width = $(document.body).outerWidth()*0.9;
                openDialog({
                    frame:true,
                    title:"详情",
                    height:height,
                    width:width,
                    url:"${ctx}/survey/case/info?id="+obj.data.surveyInfoId+"&menuCode=all-list&display=true"
                });
            }
        });
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
    })

</script>
</body>
</html>
