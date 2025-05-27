<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>复审人员报表</title>
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
            padding: 20px 0 10px 0;
            background-color: #d9edf7;
        }

        .layui-form-item {
            margin: 0 !important;
        }

        .layui-form-label {
            width: 120px;
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

        .footer-flex{
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
        }
        .label-btns {
            width: 300px;
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

        .t-c {
            text-align: center;
        }

        .layui-layer.layui-layer-page .layui-layer-content {
            position: initial;
        }
    </style>
</head>

<body>
<div class="main">
    <input type="hidden" value='${myFinalUsersJson}' id="myFinalUsers" />
    <input type="hidden" value='${consignorsJson  }' id="consignors" />
    <input type="hidden" value='${finalUserInfosJson}' id="finalUserInfos" />

    <input type="hidden" value='${menuCode}' id="menuCode" />
    <input type="hidden" value='${btnCode}' id="btnCode" />
    <input type="hidden" value='${operateState}' id="operateState" />

    <div class="layui-form searchs" lay-filter="search">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">当前复审人员：</label>
                <div class="layui-input-inline">
                    <div id="demo1" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">保险公司：</label>
                <div class="layui-input-inline">
                    <div id="demo2" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="padding: 5px 15px;">
                    <button lay-submit class="layui-btn layui-btn-normal layui-btn-radius" lay-filter="submit"
                            style="width: 100px">查询 <i class="layui-icon layui-icon-search"></i></button>
                </label>

            </div>
        </div>
    </div>
    <div class="table-content">
        <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">
        </table>
    </div>
    <div class="footer-flex">
        <div class="layui-inline">
            <label class="layui-form-label">新复审人员：</label>
            <div class="layui-input-inline" style="width:190px">
                <div id="demo3" class="selectMul"></div>
            </div>
        </div>
        <div class="label-btns">
            <div class="label-btn">取消</div>
            <div class="label-btn active" >确定</div>
        </div>
    </div>

</div>

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

    layui.use(['form', 'table', 'xmSelect', 'soulTable'], function () {
        var table = layui.table,
            form = layui.form,
            xmSelect = layui.xmSelect,
            soulTable = layui.soulTable;
        var demo1 = xmSelect.render({
            el: '#demo1',
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
            el: '#demo2',
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

        var demo3 = xmSelect.render({
            el: '#demo3',
            theme: {
                color: '#3BA9FF',
            },
            filterable: true,
            clickClose: true,
            toolbar: {
                show: true
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
            data: [],
        })

        var myFinalUsers = $("#myFinalUsers").val();
        myFinalUsers = JSON.parse(myFinalUsers);
        var consignors = $("#consignors").val();
        consignors = JSON.parse(consignors);
        var finalUserInfos = $("#finalUserInfos").val();
        finalUserInfos = JSON.parse(finalUserInfos);
        filterJson(demo1, myFinalUsers, 'userId', 'userName', false, false)
        filterJson(demo2, consignors, 'id', 'company', false, false)
        filterJson(demo3, finalUserInfos, 'userId', 'userName', false, false)


        var paramSubmit = {
            menuCode:'survey-list',
            belongUserIds: demo1.getValue('valueStr'),
            entrustOrgIds: demo2.getValue('valueStr'),
            btnCode:$('#btnCode').val(),
        }
        form.on('submit(submit)', function (data) {
            Object.assign(paramSubmit, {
                belongUserIds: demo1.getValue('valueStr'),
                entrustOrgIds: demo2.getValue('valueStr'),
            })
            table.reload('test', {
                page: false,
                where: paramSubmit
            });
        });


        function getOfSigned(d, plus) {
            var _d = Math.round(d * 10000) / 100
            var _html = '<div class="">' + _d + '%</div>'
            if (plus) {
                return _html
            }
            if (d > 0) {
                _html = '<div class="color1">' + _d + '%</div>'
            } else if (d < 0) {
                _html = '<div class="color2">' + _d + '%</div>'

            }
            return _html

        }

        function PrefixInteger(num, m) {
            return (Array(m).join(0) + num).slice(-m);
        }

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

        var _cols = [
            [{
                type: 'checkbox'
            }, {
                field: 'surveyCaseNo',
                minWidth: 160,
                title: '案件编号',
            }, {
                field: 'entrustOrgName',
                minWidth: 160,
                title: '保险公司',
            }, {
                field: 'surveyPerson',
                minWidth: 160,
                title: '被调查人',
                templet:function(d){
                    return d.surveyRiskCase.surveyPerson
                }
            }, {
                field: 'belongUserName',
                minWidth: 160,
                title: '当前复审人员',
            }, {
                field: 'endTime',
                minWidth: 170,
                title: '案件截止时间',
                sort: true,
                templet: function(d){
                    return dateFormat(d.endTime,'yyyy-MM-dd')
                }
            }]
        ]

        var _h = $('.searchs').outerHeight() + $('.label-btns').outerHeight() + 50
        var fullH = 'full-' + _h
        table.render({
            id: "test",
            elem: '#test',
            cols: _cols,
            page: false,
            limit: 10000,
            height: fullH,
             url: '${ctx}/survey/case/operate',
             where: paramSubmit,
             parseData: function(res){
                 console.log(res.results.list)
                 return  {
                         "code": res.isSuccess ? 0 : 1,
                         "msg": res.msg,
                         "count": res.count,
                         "data": res.results
                     }
             },
            // initSort: {
            //     field: 'p_1',
            //     type: 'desc'
            // },
            done: function (res) {
                soulTable.render(this)
                // var _trs = $('.table-content .layui-table-box .layui-table-body table tbody tr')
                // var _th = $(
                //     '.table-content .layui-table-box .layui-table-header table thead tr th:first .laytable-cell-checkbox'
                //     )
                // _th.css('pointer-events', 'none')
                // _th.find('.layui-form-checkbox i').css('background', '#eee')
                // console.log(_th)
                // _trs.each(function (i, cur) {
                //     var _cur = $(cur).find('td:first')
                //     _cur.css('pointer-events', 'none')
                //     _cur.find('.layui-form-checkbox i').css('background', '#eee')
                // })
            }
        })
        //监听行工具事件
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                console.log('edit', obj.data.id)
            } else if (obj.event === 'getList') {
                var param = {}
            }
        });
        table.on('checkbox(test)', function(obj){
            console.log(obj.checked); //当前是否选中状态
            console.log(obj.data); //选中行的相关数据
            console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
        });

        $('.label-btns .label-btn').click(function () {
            var data = table.checkStatus('test').data;
            var _this = $(this)


            if (_this.hasClass('active')) {
                var ids = []
                data.map(function (cur) {
                    ids.push(cur.id)
                })
                if (!ids.length) {
                    layer.msg('至少选中一条数据', {
                        time: 2000,
                        icon: 5
                    })
                    return;
                }
                if (!demo3.getValue('valueStr')){
                    layer.msg('请选择新复审人员', {
                        time: 2000,
                        icon: 5
                    })
                    return;
                }
                _this.css({
                    'pointer-event': 'none'
                })
                $.ajax({
                    url: '${ctx}/survey/case/operate',
                    data: {
                        btnCode:'changeBelongUser',
                        newBelongUserId: demo3.getValue('valueStr'),
                        caseInfoIds: ids.join(',')
                    },
                    type: 'post',
                    success: function (res) {
                        res = JSON.parse(res)
                        _this.css({
                            'pointer-event': 'auto'
                        })
                        if(res.isSuccess){
                            layer.msg('操作成功',{
                                time: 2000,
                                icon:1
                            },function () {
                                parent.reload()
                            })
                        }else {
                            layer.msg('操作失败',{
                                time: 2000,
                                icon:2
                            })
                        }
                    }

                })
            } else {
                parent.reload()
            }

        })
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


</script>



</body>

</html>