<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>带教奖励</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>

    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <style>
        .main {
            width: 99%;
            margin: 10px auto;
        }
        #dialogId{
            z-index: 198910170;
            position: fixed;
        }
        .selectMul {
            width: 100%;
        }

        .searchs {
            padding: 20px 0 10px 0;
            background-color: #d9edf7;
        }

        .layui-form-item {
            margin: 0 !important;
        }

        .layui-form-label {
            width: 116px;
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

        .label-btns {
            width: 100%;
            display: flex;
            flex-wrap: wrap;
        }

        .label-btns .label-btn {
            padding: 0 12px;
            margin: 5px 10px;
            width: 80px;
            height: 32px;
            line-height: 32px;
            color: #000;
            border: 1px solid #bbb;
            background-color: #fff;
            font-size: 14px;
            text-align: center;
            cursor: pointer;
        }

        .label-btns .label-btn.active {
            color: #fff;
            background-color: #3BA9FF;
            border: 1px solid #3BA9FF;
        }

        .date-choose {
            display: flex;
            align-items: center;
        }

        .date-choose input {
            width: 100px;
        }

        .date-choose .dc-span {
            display: block;
            padding: 0 4px;
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

        .layui-input-td {
            line-height: inherit;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .layui-table .layui-input {
            height: 100%;
        }

        .color1 {
            color: #FF3D00;
        }

        .color2 {
            color: #64DD17;
        }

        .t-c{
            text-align: center;
        }

        .layui-table-body {
            overflow-y: overlay;
        }
    </style>
</head>

<body>
<div class="main">
    <input type="hidden" value='${params.consignorsJson}' id="consignorsJson"/>
    <input type="hidden" value='${params.standardListJson}' id="standardListJson"/>
    <input type="hidden" value='${params.notStandardListJson}' id="notStandardListJson"/>
    <div class="layui-form searchs" lay-filter="search">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">新人</label>
                <div class="layui-input-inline">
                    <div id="d1" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">新人机构</label>
                <div class="layui-input-inline">
                    <div id="d2" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">带教人</label>
                <div class="layui-input-inline">
                    <div id="d3" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">实际达标日期</label>
                <div class="layui-input-inline date-choose">
                    <input type="text" class="layui-input paramTime" readonly id="startTime_1" placeholder="请选择日期">
                    <span class="dc-span">-</span>
                    <input type="text" class="layui-input paramTime" readonly id="endTime_1" placeholder="请选择日期">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">达标截止日期</label>
                <div class="layui-input-inline date-choose">
                    <input type="text" class="layui-input paramTime" readonly id="startTime_2" placeholder="请选择日期">
                    <span class="dc-span">-</span>
                    <input type="text" class="layui-input paramTime" readonly id="endTime_2" placeholder="请选择日期">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">是否标记达标</label>
                <div class="layui-input-inline">
                    <div id="d4" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline" style="padding-left: 30px;">
                <label class="layui-form-label">
                    <button lay-submit class="ll-submit layui-btn layui-btn-normal layui-btn-radius"
                            lay-filter="submit" style="width: 100px">查询 <i
                            class="layui-icon layui-icon-search"></i></button>
                </label>
            </div>
            <div class="layui-inline bj-btn">
                <label class="layui-form-label" style="padding: 5px 15px;">
                    <button lay-submit class="layui-btn layui-btn-normal layui-btn-radius" lay-filter="submit2"
                            style="width: 220px">标记新人达标并确认奖励
                        <i class="layui-icon layui-icon-star"></i></button>
                </label>
            </div>
        </div>
    </div>
    <div class="table-content">
        <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">
        </table>
    </div>
</div>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">处理</a>
</script>

<script type="text/html" id="inputA">
    {{#  if(d.teacherRealReward == 'undefinded' || d.teacherRealReward == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <input value="{{d.teacherRealReward}}" class="layui-input" type="text">
    <div class="layui-input layui-input-td">{{d.teacherRealReward}}</div>
    {{#  } }}
</script>
<script type="text/html" id="inputB">
    {{#  if(d.rewardDesc == 'undefinded' || d.rewardDesc == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <input value="{{d.rewardDesc}}" title="{{d.rewardDesc}}" class="layui-input" type="text">
    <div class="layui-input layui-input-td" title="{{d.rewardDesc}}">{{d.rewardDesc}}</div>
    {{#  } }}
</script>
<script type="text/html" id="inputD">
    {{#  if(d.effecDays > 28){ }}
    <div class=" color1">{{d.effecDays}}天</div>
    {{#  } else if(d.effecDays > 0){ }}
    <div class="">{{d.effecDays}}天</div>
    {{#  } else if(d.effecDays == 0){ }}
    <div class="">{{d.effecDays}}天</div>
    {{#  } else { }}
    <div class="t-c"> -</div>
    {{#  } }}
</script>
<script type="text/html" id="inputC">
    {{#  if(d.isNewPeople == 1){ }}
    <div>是</div>
    {{#  } else { }}
    <div>否</div>
    {{#  } }}
</script>

<script type="text/html" id="socre-list-c">
    <div class="main">
        <div class="table-content-score">
            <table class="layui-table" id="socre-list" lay-filter="socre-list" lay-skin="line" lay-size="lg">
            </table>
        </div>
    </div>
</script>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script src="${ctx}/js/jquery-3.4.1.js" charset="utf-8"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        soulTable: 'soulTable',
        xmSelect: 'xm-select'
    })
    layui.use(['form', 'table', 'xmSelect', 'soulTable', 'laydate', 'jquery', 'layer'], function () {
        var table = layui.table,
            layer = layui.layer,
            form = layui.form,
            laydate = layui.laydate,
            xmSelect = layui.xmSelect,
            $ = layui.jquery,
            soulTable = layui.soulTable;

        var demo1 = xmSelect.render({
            el: '#d1',
            theme: {
                color: '#3BA9FF',
            },
            filterable: true,
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
                                        '<div class="xm-label-block lf-select-block">' + cur
                                            .name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: [],
        })
        var demo2 = xmSelect.render({
            el: '#d2',
            theme: {
                color: '#3BA9FF',
            },
            filterable: true,
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
                                        '<div class="xm-label-block lf-select-block">' + cur
                                            .name + '</div>'
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
            el: '#d3',
            theme: {
                color: '#3BA9FF',
            },
            filterable: true,
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
                                        '<div class="xm-label-block lf-select-block">' + cur
                                            .name + '</div>'
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
            el: '#d4',
            theme: {
                color: '#3BA9FF',
            },
            on: function(data){
                if (data.arr.length && data.arr[0].value == 1){
                    demo1.setValue([])
                }
            },
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
                                        '<div class="xm-label-block lf-select-block">' + cur
                                            .name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: [{
                'name': '是',
                'value': 1,
            }, {
                'name': '否',
                'value': 0,
                'selected': true
            }]
        })
        var notStandardListJson = $("#notStandardListJson").val();
        notStandardListJson = JSON.parse(notStandardListJson);
        filterJson(demo1, notStandardListJson, 'userId', 'realName', false, true)
        var consignorsJson = $("#consignorsJson").val();
            consignorsJson = JSON.parse(consignorsJson);
        filterJson(demo2, consignorsJson, 'id', 'name', false, true)
        var standardListJson = $("#standardListJson").val();
            standardListJson = JSON.parse(standardListJson);
        filterJson(demo3, standardListJson, 'userId', 'realName', false, true)

        var initVals = ['', ''],
            initVals2 = ['', '']

        startTime_1 = laydate.render({
            elem: '#startTime_1',
            value: initVals[0],
            max: initVals[1],
            isInitValue: true,
            done: function (value, date) {
                endTime_1.config.min = {
                    year: date.year,
                    month: date.month - 1,
                    date: date.date
                }
            }
        });
        endTime_1 = laydate.render({
            elem: '#endTime_1',
            value: initVals[1],
            min: initVals[0],
            isInitValue: true,
            done: function (value, date) {
                if (value) {
                    startTime_1.config.max = {
                        year: date.year,
                        month: date.month - 1,
                        date: date.date
                    }
                } else {
                    startTime_1.config.max = {
                        year: 2100,
                        month: 1,
                        date: 1
                    }
                }
            }
        });
        startTime_2 = laydate.render({
            elem: '#startTime_2',
            value: initVals2[0],
            max: initVals2[1],
            isInitValue: true,
            done: function (value, date) {
                endTime_2.config.min = {
                    year: date.year,
                    month: date.month - 1,
                    date: date.date
                }
            }
        });
        endTime_2 = laydate.render({
            elem: '#endTime_2',
            value: initVals2[1],
            min: initVals2[0],
            isInitValue: true,
            done: function (value, date) {
                if (value) {
                    startTime_2.config.max = {
                        year: date.year,
                        month: date.month - 1,
                        date: date.date
                    }
                } else {
                    startTime_2.config.max = {
                        year: 2100,
                        month: 1,
                        date: 1
                    }
                }
            }
        });

        var paramSubmit = {
            menuCode: 'rookieManagement',
            userId: '',
            orgId: '',
            teacherUserId: '',
            isNewPeople: 0,
            startTargetTime: $('#startTime_1').val(),
            endTargetTime: $('#endTime_1').val(),
            startClosingTime: $('#startTime_2').val(),
            endClosingTime: $('#endTime_2').val(),
        }
        form.on('submit(submit)', function (data) {
            var vals = data.field
            delete vals.select;
            Object.assign(vals, {
                menuCode: 'rookieManagement',
                userId: demo4.getValue('valueStr') == 1 ? '' : demo1.getValue('valueStr'),
                orgId: demo2.getValue('valueStr'),
                teacherUserId: demo3.getValue('valueStr'),
                isNewPeople: demo4.getValue('valueStr') ? demo4.getValue('valueStr') : '0',
                startTargetTime: $('#startTime_1').val(),
                endTargetTime: $('#endTime_1').val(),
                startClosingTime: $('#startTime_2').val(),
                endClosingTime: $('#endTime_2').val(),
            })
            paramSubmit = vals

            $('.table-content').empty()
            $('.table-content').append(' <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg"></table>')
            if (demo4.getValue('valueStr') == 1){
                $('.bj-btn').hide()
                setData(_cols2)
            }else if (demo4.getValue('valueStr') == 0){
                $('.bj-btn').show()
                setData(_cols)
            }

            //
            // table.reload('test', {
            //     page: false,
            //     where: paramSubmit
            // });
        });

        form.on('submit(submit2)', function (data) {
            var checkedData= table.checkStatus('test').data
            var ids = []
            checkedData.map(function (cur) {
                ids.push(cur.id)
            })
            if (!ids.length){
                layer.msg( '至少选中一条数据',{
                    time: 2000,
                    icon: 5
                })
                return ;
            }
            layer.confirm('是否标记达标并确认奖励？', {
                btn: ['是','否'] //按钮
            }, function(){
                $.ajax({
                    url: '${ctx}/training/getEdit',
                    type: "post",
                    data: {
                        "btnCode": "updisNewPeople",
                        "menuCode": 'rookieManagement',
                        ids: ids.join(',')
                    },
                    success: function (res) {
                        res = JSON.parse(res)
                        if (res.isSuccess) {
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 1
                            },function () {
                                location.reload()
                            });
                        } else {
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 2
                            });
                        }
                    }
                });
            }, function(){

            });

        });

        function formatDate(d, type) {
            if (!d) {
                return '<div class="t-c">-</div>'
            }
            var curDay = new Date(d),
                y = curDay.getFullYear(),
                m = PrefixInteger(curDay.getMonth() + 1, 2),
                d = PrefixInteger(curDay.getDate(), 2),
                hh = PrefixInteger(curDay.getHours(), 2),
                mm = PrefixInteger(curDay.getMinutes(), 2),
                ss = PrefixInteger(curDay.getSeconds(), 2)
            if (type == 1 || !type) {
                return '<div>' + y + '年' + m + '月' + d + '日</div>'
            } else if (type == 2) {
                return '<div>' + y + '-' + m + '-' + d + '</div>'
            } else if (type == 3) {
                return '<div>' + y + '-' + m + '-' + d + ' ' + hh + ':' + mm + ':' + ss + '</div>'
            }
        }

        function isNull(d)
        {
            if (d !==0 && !d){
                return  '<div class="t-c">-</div>'
            }else {
                return  '<div>'+d+'</div>'
            }
        }


        function getOfSignedNum(d) {
            return Math.round(d * 100) / 100

        }

        var _cols = [
            [{
                type: 'checkbox',
                fixed: 'left'
            }, {
                field: 'realName',
                minWidth: 100,
                title: '新人',
                fixed: 'left'
            }, {
                field: 'orgName',
                minWidth: 170,
                title: '新人机构',
            }, {
                field: 'tel',
                minWidth: 130,
                title: '新人手机号',
            }, {
                field: 'createTime',
                minWidth: 130,
                title: '入职时间',
                sort: true,
                templet: function (d) {
                    return formatDate(d.createTime)
                }
            }, {
                field: 'score',
                minWidth: 150,
                title: '累计获得积分',
                sort: true,
                style: 'color: #3BA9FF',
                event: 'scoreList',
                templet: function (d) {
                    return getOfSignedNum(d.score)
                }
            }, {
                field: 'realQualifiedTime',
                minWidth: 140,
                title: '实际达标日期',
                sort: true,
                templet: function (d) {
                    return formatDate(d.realQualifiedTime)
                }
            }, {
                field: 'qualifiedEnfdTime',
                minWidth: 140,
                title: '达标截止日期',
                sort: true,
                templet: function (d) {
                    return formatDate(d.qualifiedEnfdTime)
                }
            }, {
                field: 'effecDays',
                minWidth: 110,
                title: '达标时效',
                sort: true,
                templet: '#inputD'
            }, {
                field: 'teacherUserName',
                minWidth: 100,
                title: '带教人',
            }, {
                field: 'teacherOrgName',
                minWidth: 170,
                title: '带教人机构',
            }, {
                field: 'teacherUserTel',
                minWidth: 150,
                title: '带教人手机号码',
                sort: true
            }, {
                field: 'teacherReward',
                minWidth: 110,
                title: '带教奖励',
                sort: true,
                templet: function (d) {
                    return isNull(d.teacherReward)
                }
            }, {
                field: 'teacherRealReward',
                minWidth: 110,
                title: '实际奖励',
                sort: true,
                edit: 'text',
                templet: '#inputA',
            }, {
                field: 'rewardDesc',
                minWidth: 160,
                title: '备注',
                edit: 'text',
                templet: '#inputB',
            }, {
                field: 'isNewPeople',
                minWidth: 150,
                title: '是否达标',
                sort: true,
                templet: '#inputC',
            }, {
                field: 'operateUserName',
                minWidth: 120,
                title: '操作人',
                sort: true
            }, {
                field: 'operateTime',
                minWidth: 150,
                title: '操作时间',
                sort: true,
                templet: function (d) {
                    return formatDate(d.operateTime, 3)
                }
            }]
        ]
        var _cols2 = [
            [{
                type: 'checkbox',
                fixed: 'left'
            }, {
                field: 'realName',
                minWidth: 100,
                title: '新人',
                fixed: 'left'
            }, {
                field: 'orgName',
                minWidth: 170,
                title: '新人机构',
            }, {
                field: 'tel',
                minWidth: 130,
                title: '新人手机号',
            }, {
                field: 'createTime',
                minWidth: 130,
                title: '入职时间',
                sort: true,
                templet: function (d) {
                    return formatDate(d.createTime)
                }
            }, {
                field: 'score',
                minWidth: 150,
                title: '累计获得积分',
                sort: true,
                style: 'color: #3BA9FF',
                event: 'scoreList',
                templet: function (d) {
                    return getOfSignedNum(d.score)
                }
            }, {
                field: 'realQualifiedTime',
                minWidth: 140,
                title: '实际达标日期',
                sort: true,
                templet: function (d) {
                    return formatDate(d.realQualifiedTime)
                }
            }, {
                field: 'qualifiedEnfdTime',
                minWidth: 140,
                title: '达标截止日期',
                sort: true,
                templet: function (d) {
                    return formatDate(d.qualifiedEnfdTime)
                }
            }, {
                field: 'effecDays',
                minWidth: 110,
                title: '达标时效',
                sort: true,
                templet: '#inputD'
            }, {
                field: 'teacherUserName',
                minWidth: 100,
                title: '带教人',
            }, {
                field: 'teacherOrgName',
                minWidth: 170,
                title: '带教人机构',
            }, {
                field: 'teacherUserTel',
                minWidth: 150,
                title: '带教人手机号码',
                sort: true
            }, {
                field: 'teacherReward',
                minWidth: 110,
                title: '带教奖励',
                sort: true,
                templet: function (d) {
                    return isNull(d.teacherReward)
                }
            }, {
                field: 'teacherRealReward',
                minWidth: 110,
                title: '实际奖励',
                sort: true,
            }, {
                field: 'rewardDesc',
                minWidth: 160,
                title: '备注',
            }, {
                field: 'isNewPeople',
                minWidth: 150,
                title: '是否达标',
                sort: true,
                templet: '#inputC',
            }, {
                field: 'operateUserName',
                minWidth: 120,
                title: '操作人',
                sort: true
            }, {
                field: 'operateTime',
                minWidth: 150,
                title: '操作时间',
                sort: true,
                templet: function (d) {
                    return formatDate(d.operateTime, 3)
                }
            }]
        ]

        var _h = $('.searchs').outerHeight() + 50
        var fullH = 'full-' + _h
        setData(_cols)
        function setData(_cols) {
           table.render({
               id: "test",
               elem: '#test',
               cols: _cols,
               page: false,
               limit: 10000,
               height: fullH,
               overflow: {
                   type: 'tips',
                   hoverTime: 100,
                   color: '#333',
                   bgColor: '#fff',
                   minWidth: 100,
                   maxWidth: 500,
               },
               even: true,
               url: '${ctx}/training/getDetail',
               where: paramSubmit,
               parseData: function(res){
                   return  {
                       "code": res.isSuccess ? 0 : 1,
                       "msg": res.msg,
                       "count": res.count,
                       "data": res.results
                   }
               },
               initSort: {
                   field: 'createTime',
                   type: 'desc'
               },
               done: function (res) {
                   sessionStorage.setItem('np', JSON.stringify(res.data))
                   if (demo4.getValue('valueStr') == 1) {
                       var _trs = $('.table-content .layui-table-box .layui-table-fixed .layui-table-body table tbody tr')
                       var _th = $('.table-content .layui-table-box .layui-table-fixed .layui-table-header table thead tr th:first')
                       _th.css('pointer-events', 'none')
                       _th.find('.laytable-cell-checkbox .layui-form-checkbox i').css('background', '#eee')
                       _trs.each(function (i, cur) {
                           var _cur = $(cur).find('td:first')
                           _cur.css('pointer-events', 'none')
                           _cur.find('.layui-form-checkbox i').css('background', '#eee')
                       })
                   }
               }
           })

            table.on('sort(test)', function(obj){
                if (demo4.getValue('valueStr') == 1) {
                    var _trs = $('.table-content .layui-table-box .layui-table-fixed .layui-table-body table tbody tr')
                    var _th = $('.table-content .layui-table-box .layui-table-fixed .layui-table-header table thead tr th:first')
                    _th.css('pointer-events', 'none')
                    _th.find('.laytable-cell-checkbox .layui-form-checkbox i').css('background', '#eee')
                    _trs.each(function (i, cur) {
                        var _cur = $(cur).find('td:first')
                        _cur.css('pointer-events', 'none')
                        _cur.find('.layui-form-checkbox i').css('background', '#eee')
                    })
                }
            })


            table.on('edit(test)', function (obj) {
               var value = obj.value.replace(/\s+/g, ""), //得到修改后的值
                   data = obj.data, //得到所在行所有键值
                   field = obj.field, //得到字段
                   np = JSON.parse(sessionStorage.getItem('np')) || []
               var fieldName = ''
               _cols[0].filter(function (cur, index) {
                   if (cur.field == field) {
                       fieldName = cur.title
                   }
               })
               var oldValue = ''
               np.forEach(function (cur, index) {
                   if (cur.id === data.id) {
                       oldValue = cur[field]
                   }
               })
               var flag = true
               if ((field == 'teacherRealReward') && value) { //正数
                   flag = checkPapers('money', value)
                   if (!flag) {
                       layer.msg('字段【' + fieldName + '】不符合规则，请输入小数点两位内的正数字', {
                           time: 2000,
                           icon: 2
                       })
                       obj.update({
                           [field]: oldValue
                       })
                       $(obj.tr[0]).children('td[data-field=' + field + ']').find('input').val(
                           oldValue)
                       return;
                   }
               }
               value = value || 0
               var param  = {}
               if (field == 'teacherRealReward'){
                   param  =  {
                       "btnCode": "updMoney",
                       "menuCode": 'rookieManagement',
                       "money": value,
                       "ids": data.id
                   }
               }else {
                   param  =  {
                       "btnCode": "updMoney",
                       "menuCode": 'rookieManagement',
                       "ids": data.id,
                       "rewardDesc": data.rewardDesc,
                   }
               }
               //请求后台，返回当条数据更新 修改值
               $.ajax({
                   url: '${ctx}/training/getEdit',
                   type: "post",
                   data: param,
                   success: function (res) {
                       res = JSON.parse(res)
                       if (res.isSuccess) {
                           layer.msg('【' + fieldName + ':' + value + '】 更新成功', {
                               time: 2000,
                               icon: 1
                           });
                       } else {
                           layer.msg(res.msg, {
                               time: 2000,
                               icon: 2
                           });
                       }
                   }
               });
           });

           //监听行工具事件
           table.on('tool(test)', function (obj) {
               var data = obj.data;
               if (obj.event === 'edit') {} else if (obj.event === 'getList') {
                   onRowEvent(obj)
               }

               if( obj.event === 'scoreList'){
                   showScoreList({
                       menuCode:'teachingRewardDetail',
                       id: obj.data.id
                   })
               }
           });


       }

        function onRowEvent(obj, pobj) {
            var _height = $(document).height() * 0.9
            var _width = $(document).width() * 0.98
            layer.open({
                type: 1,
                title: '列表',
                area: [_width + 'px', _height + 'px'],
                content: $('#table-content-child-h').html(),
            });
            var param = {}
            getChild(param)
        }

        function showScoreList(param){
            var _height = $(document).height() * 0.9
            var _width = $(document).width() * 0.98
            layer.open({
                type: 1,
                title: '列表',
                area: [_width + 'px', _height + 'px'],
                content: $('#socre-list-c').html(),
            });

            var _cols_score = [
                [ {
                    field: 'surveyNo',
                    minWidth: 100,
                    title: '案件编号',
                    fixed: 'left',
                    event: 'caseDetail',
                    style: 'color:#3BA9FF',
                }, {
                    field: 'surveyPerson',
                    minWidth: 100,
                    title: '被调查人',
                }, {
                    field: 'entrustOrgName',
                    minWidth: 100,
                    title: '互助平台',
                }, {
                    field: 'surveyUserName',
                    minWidth: 130,
                    title: '调查员',
                }, {
                    field: 'tasks',
                    minWidth: 160,
                    title: '分配任务类型',
                    templet: function (d) {
                        return eachTasks(d.tasks)
                    }
                }, {
                    field: 'servicesId',
                    minWidth: 120,
                    title: '机构案件类型',
                    templet: function (d) {
                        return eachServive(d.servicesId)
                    }
                }, {
                    field: 'entrustTime',
                    minWidth: 150,
                    title: '委托日期',
                    templet: function (d) {
                        return formatDate(d.entrustTime)
                    }
                }, {
                    field: 'reviewTime',
                    minWidth: 140,
                    title: '平台复审通过日期',
                    templet: function (d) {
                        return formatDate(d.reviewTime)
                    }
                }, {
                    field: 'score',
                    minWidth: 140,
                    title: '积分',
                }, {
                    field: 'cumulativePoints',
                    minWidth: 110,
                    title: '累计积分',
                    templet: function (d) {
                        return getOfSignedNum(d.cumulativePoints)
                    }
                }]
            ]
            table.render({
                id: "socre-list",
                elem: '#socre-list',
                cols: _cols_score,
                page: false,
                height: 'full-200',
                drag: false,
                event: true,
                url: '${ctx}/training/getDetail',
                where: param,
                request: {
                    pageName: 'pageIndex' //页码的参数名称，默认：page
                    ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
                },
                overflow: {
                    type: 'tips',
                    hoverTime: 100,
                    color: '#333',
                    bgColor: '#fff',
                    minWidth: 100,
                    maxWidth: 500,
                },
                parseData: function (res) {
                    return {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results
                    }
                },
                done: function () {
                    soulTable.render(this)
                }
            })

            //监听行工具事件
            table.on('tool(socre-list)', function (obj) {
                var data = obj.data;
                if (obj.event == 'caseDetail'){
                    jumpPage(obj.data.id)
                }
            });
        }
    })
    function eachServive(d) {
        if (d == 13) {
            return '<div>全案</div>'
        }else{
            return '<div>单点</div>'
        }
    }

    function eachTasks(d) {
        var _tasks = []
        d.map(function (cur) {
            _tasks.push(cur.taskName)
        })

        return _tasks.join(',')
    }

    // var consignorsJson = $("#consignorsJson").val();
    //     consignorsJson = JSON.parse(consignorsJson);
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

    function PrefixInteger(num, m) {
        return (Array(m).join(0) + num).slice(-m);
    }

    //正则匹配
    var checkPapers = function (parama, paramb) {
        var map = new Map([
            ['phone', /^[1][3,5,7,8,9][0-9]{9}$/],
            ['passport', !/^((1[45]\d{7})|(G\d{8})|(P\d{7})|(S\d{7,8}))?$/],
            ['identity', /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/],
            ['money', /^[0-9]+(\.[0-9]{1,2})?$/],
            ['moneyorMinus', /^(\-|\+)?\d+(\.\d{1,2})?$/],
            ['email', /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/],
            ['num_0_1', /^(0+(\.[0-9]{1,2})?|1|1.0|1.00?)$/]
        ])

        if (map.get(parama).test(paramb)) {
            return true
        } else {
            return false
        }
    }

    function jumpPage(id) {
        var height = $(document).outerHeight()*0.9;
        var width = $(document.body).outerWidth()*0.9;
        openDialog({
            frame:true,
            title:"详情",
            height:height,
            width:width,
            url:"${ctx}/survey/case/info?id="+id+"&menuCode=all-list&fromName=pointsDetails"
        });
    }
</script>
</body>

</html>