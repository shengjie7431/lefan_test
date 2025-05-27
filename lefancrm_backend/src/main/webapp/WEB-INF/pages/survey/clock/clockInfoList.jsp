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

        .layui-table-main .layui-table-cell{
            height: auto;
        }

        .orgCell{
            display: flex;
            align-items: center;
        }
        .orgCell div{
            margin-right: 5px;
        }
    </style>
</head>

<body>
<div class="main">
    <input type="hidden" value='${startTime}'  id="startTime" />
    <input type="hidden" value='${endTime}' id="endTime" />
    <input type="hidden" value='${userId}' id="userId" />
    <input type="hidden" value='${orgId}' id="orgId" />

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
    })

    function PrefixInteger(num, m) {
        return (Array(m).join(0) + num).slice(-m);
    }


    // var franchiseesJson = $("#franchiseesJson").val();
    // franchiseesJson = JSON.parse(franchiseesJson);
    layui.use(['form', 'table', 'soulTable', 'laydate', 'jquery'], function () {
        var soulTable = layui.soulTable,
            table = layui.table,
            form = layui.form,
            laydate = layui.laydate,
            $ = layui.jquery

        var paramsSubmit = {
            btnCode:'clockInDetails',
            menuType: 'clockDetails',
            userId: $('#userId').val(),
            orgId: $('#orgId').val(),
            startTime: $('#startTime').val(),
            endTime: $('#endTime').val(),
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
                    field: 'clockTime',
                    width: 170,
                    title: '时间',
                    rowspan: 2,
                    templet: function (d) {
                        return layui.util.toDateString(d.clockTime, 'yyyy-MM-dd HH:mm:ss')
                    }
                },{
                    field: 'sameDaySort',
                    minWidth: 120,
                    title: '当日打卡编号',
                    rowspan: 2
                },{
                    field: 'address',
                    minWidth: 170,
                    title: '地点',
                    rowspan: 2
                },{
                    field: 'p3',
                    width: 150,
                    title: '关联被调查人',
                    event: 'user',
                    rowspan: 2,
                    templet: function (d) {
                        var _html = "";
                        if (d.surveyClockReInfo){
                            var scaseList = d.surveyClockReInfo.clockCaseList

                            if (scaseList.length >0) {
                                scaseList.map(function (cur) {
                                    _html += '<div class="orgCell">' +
                                        '<div class="orgName">' + cur.surveyPerson + ' </div>' +
                                        '</div>'
                                });
                            }
                        }

                        return _html
                    },
                },{
                    title: '费用报销',
                    colspan: 12,
                    align: 'center'
                }
                ],
            [ {
                field: 'cityinDrivingMoney',
                width: 190,
                title: '市内交通费',
                templet: function (d) {
                    var _html = ''
                    if (d.surveyClockReInfo) {
                        _html = getFixed(d.surveyClockReInfo.cityinDrivingMoney) + '元'
                    }
                    return _html
                }
            }, {
                field: 'medicalHistoryMoney',
                width: 190,
                title: '病史费（含复印费）',
                templet: function (d) {
                    var _html= ''
                    if (d.surveyClockReInfo) {
                        var scaseList = d.surveyClockReInfo.clockCaseList

                        if (scaseList.length >0){
                            scaseList.map(function (cur) {
                                _html += '<div class="orgCell">' +
                                    '<div class="orgName">'+cur.surveyPerson+':  </div>'+
                                    '<div class="orgState">'+cur.medicalHistoryMoney+'  元</div>' +
                                    '</div>'
                            })
                        }
                    }

                    return _html
                }
            }, {
                field: 'troubleshootingMoney',
                width: 190,
                title: '住院排查费用',
                templet: function (d) {
                    var _html= ''
                    if (d.surveyClockReInfo) {
                        var scaseList = d.surveyClockReInfo.clockCaseList
                        if (scaseList.length >0){
                            scaseList.map(function (cur) {
                                _html += '<div class="orgCell">' +
                                    '<div class="orgName">'+cur.surveyPerson+':  </div>'+
                                    '<div class="orgState">'+cur.troubleshootingMoney+'  元</div>' +
                                    '</div>'
                            })
                        }

                    }

                    return _html
                },
            }, {
                field: 'opcTroubleshootingMoney',
                width: 190,
                title: '门诊排查费用',
                templet: function (d) {
                    var _html= ''
                    if (d.surveyClockReInfo) {
                        var scaseList = d.surveyClockReInfo.clockCaseList
                        if (scaseList.length >0){
                            scaseList.map(function (cur) {
                                _html += '<div class="orgCell">' +
                                    '<div class="orgName">'+cur.surveyPerson+':  </div>'+
                                    '<div class="orgState">'+cur.opcTroubleshootingMoney+' 元</div>' +
                                    '</div>'
                            })
                        }
                    }

                    return _html
                },
            },  {
                field: 'printingMoney',
                width: 190,
                title: '体检报告打印费',
                templet: function (d) {
                    var _html= ''
                    if (d.surveyClockReInfo) {
                        var scaseList = d.surveyClockReInfo.clockCaseList
                        if (scaseList.length >0){
                            scaseList.map(function (cur) {
                                _html += '<div class="orgCell">' +
                                    '<div class="orgName">'+cur.surveyPerson+':  </div>'+
                                    '<div class="orgState">'+cur.printingMoney+'  元</div>' +
                                    '</div>'
                            })
                        }
                    }

                    return _html
                },
            }, {
                field: 'accommodatioMoney',
                width: 130,
                title: '住宿费',
                sort: true,
                templet: function (d) {
                    var _html = ''
                    if (d.surveyClockReInfo) {
                        _html = getFixed(d.surveyClockReInfo.accommodatioMoney) + '元'
                    }
                    return _html
                }
            }, {
                field: 'crossDrivingMoney',
                width: 260,
                title: '跨地市交通费（汽车、火车、飞机）',
                sort: true,
                templet: function (d) {
                    var _html = ''
                    if (d.surveyClockReInfo) {
                        _html = getFixed(d.surveyClockReInfo.crossDrivingMoney) + '元'
                    }
                    return _html
                }
            }, {
                field: 'selfDrivingMoney',
                width: 190,
                title: '跨地市交通费（自驾）',
                sort: true,
                templet: function (d) {
                    var _html = ''
                    if (d.surveyClockReInfo) {
                        _html = getFixed(d.surveyClockReInfo.selfDrivingMoney) + '元'
                    }
                    return _html
                }
            }, {
                field: 'tollMoney',
                width: 190,
                title: '过路费',
                sort: true,
                templet: function (d) {
                    var _html = ''
                    if (d.surveyClockReInfo) {
                        _html = getFixed(d.surveyClockReInfo.tollMoney) + '元'
                    }
                    return _html
                }
            },{
                field: 'otherMoney',
                width: 190,
                title: '其他费用',
                sort: true,
                templet: function (d) {
                    var _html = ''
                    if (d.surveyClockReInfo) {
                        _html = getFixed(d.surveyClockReInfo.otherMoney) + '元'
                    }
                    return _html
                }
            },  {
                field: 'otherMoney',
                width: 190,
                title: '合计',
                sort: true,
                templet: function (d) {
                    return getFixed(d.reTotalMoney) + '元'
                }
            },{
                field: 'clockDesc',
                width: 150,
                align: 'center',
                title: '备注',
            }]
        ]

        setTable(_cols, paramsSubmit)

        function setTable(_cols, param) {
            var _h = 40
            var fullH = 'full-' + _h
            var myTable = table.render({
                id: "test",
                elem: '#test',
                even: true,cols: _cols,
                page: false,
                limit: 200000,
                height: fullH,
                drag: false,
                url: '${ctx}/survey/userClock/details',
                where: param,
                parseData: function(res) {
                    return {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results,
                    }
                },
                done: function () {
                    soulTable.render(this)
                }
            })
            table.on('tool(test)', function (obj, pobj) {
            })

        }
        function getFixed(d) {
            var _d = Math.round(d * 100) / 100
            return _d
        }




    })

</script>

</body>

</html>