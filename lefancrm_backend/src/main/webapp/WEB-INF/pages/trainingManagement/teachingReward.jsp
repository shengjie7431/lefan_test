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
            width: 90px;
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

        .t-c {
            text-align: center;
        }

        .layui-layer.layui-layer-page .layui-layer-content {
            position: initial;
        }
        .refuse{
            display: none;
            width:98%;
            padding: 10px 1%;
            background-color:rgba(238, 136, 132, 0.26);
        }
        .refuse div{
            padding: 6px 0;
        }

        .layui-table-body {
            overflow-y: overlay;
        }

    </style>
</head>

<body>
<div class="main">
    <input type="hidden" value="${roleCode}" id="roleCode"/>
    <input type="hidden" value="${surveyTeachRewardListDtoList}" id="surveyTeachRewardListDtoList"/>
    <div class="layui-form searchs" lay-filter="search">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">清单名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="listName" placeholder="请输入清单名称" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">清单状态</label>
                <div class="layui-input-inline">
                    <div id="demo2" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="padding: 5px 15px;">
                    <button lay-submit class="layui-btn layui-btn-normal layui-btn-radius" lay-filter="submit"
                            >查询 <i class="layui-icon layui-icon-search"></i></button>
                </label>
            </div>
            <c:if test="${roleCode == 1 || roleCode == 3}">
                 <div class="layui-inline">
                    <label class="layui-form-label" style="padding: 5px 15px;">
                        <button lay-submit class="layui-btn layui-btn-normal layui-btn-radius" lay-filter="add"
                               >新增 <i class="layui-icon layui-icon-add-circle"></i></button>
                    </label>
                </div>
            </c:if>
        </div>
    </div>
    <div class="table-content">
        <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">
        </table>
    </div>
<%--    <div class="label-btns">--%>
<%--        <div class="label-btn">驳回</div>--%>
<%--        <div class="label-btn active">审核通过</div>--%>
<%--    </div>--%>
</div>
<%--<script type="text/html" id="barDemo">--%>
    <%--{{#  if(d.state == 1 || d.state == 4){ }}--%>
        <%--<a class="layui-btn layui-btn-xs" lay-event="edit">处理</a>--%>
    <%--<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete">删除</a>--%>
    <%--{{#  } else { }}--%>
    <%--<a class="layui-btn layui-btn-xs" lay-event="edit">处理</a>--%>
    <%--{{#  } }}--%>

<%--</script>--%>
<script type="text/html" id="table-content-child-h">
    <div class="main">
        <div class="refuse">
            <div>驳回原因：</div>
        </div>
        <div class="table-content-child">
            <table class="layui-table" id="test_child" lay-filter="test_child" lay-skin="line" lay-size="lg">
            </table>
        </div>
        <div class="layui-form layui-form-submit" style="margin-top:20px" lay-filter="check">
            <div class="layui-form-item">
                <div class="layui-inline" style="margin-right: 20px;">
                    <label class="layui-form-label">清单名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="qdname" required placeholder="请输入清单名称" autocomplete="off"
                               class="layui-input">
                    </div>
                </div>
                <div class="layui-inline form-check">
                    <button class="layui-btn layui-btn-primary" data-type="cancel" lay-submit
                            lay-filter="submitC">保存并关闭</button>
                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="submitC" data-type="submit">提交审核</button>
                </div>
            </div>
        </div>
    </div>
</script>
<script type="text/html" id="table-content-child-h2">
    <div class="main">
        <div class="table-content-child">
            <table class="layui-table" id="test_child" lay-filter="test_child" lay-skin="line" lay-size="lg">
            </table>
        </div>
        <div class="layui-form layui-form-submit" style="margin-top:20px" lay-filter="check">
            <div class="layui-form-item">
                <div class="layui-inline form-check" >
                    <button class="layui-btn layui-btn-primary" data-type="reject" lay-submit
                            lay-filter="submitC">驳回</button>
                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="submitC" data-type="adopt" >审核通过</button>
                </div>
            </div>
        </div>
    </div>
</script>
<script type="text/html" id="table-content-child-h3">
    <div class="main">
        <div class="table-content-child">
            <table class="layui-table" id="test_child" lay-filter="test_child" lay-skin="line" lay-size="lg">
            </table>
        </div>
    </div>
</script>
<script type="text/html" id="socre-list-c">
    <div class="main">
        <div class="table-content-score">
            <table class="layui-table" id="socre-list" lay-filter="socre-list" lay-skin="line" lay-size="lg">
            </table>
        </div>
    </div>
</script>

<script type="text/html" id="inputA">
    {{#  if(d.teacherRealReward == 'undefinded' || d.teacherRealReward == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <input value="{{d.teacherRealReward}}" class="layui-input" type="text" disabled>
    <div class="layui-input layui-input-td">{{d.teacherRealReward}}</div>
    {{#  } }}
</script>
<script type="text/html" id="inputB">
    {{#  if(d.rewardDesc == 'undefinded' || d.rewardDesc == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <input value="{{d.rewardDesc}}" title="{{d.rewardDesc}}" class="layui-input" type="text" disabled>
    <div class="layui-input layui-input-td" title="{{d.rewardDesc}}">{{d.rewardDesc}}</div>
    {{#  } }}
</script>
<script type="text/html" id="inputD">
    {{#  if(d.effecDays > 28){ }}
    <div class=" color1">{{d.effecDays}}天</div>
    {{#  } else if(d.effecDays > 0){ }}
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
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src="${ctx}/js/jquery.min.js" charset="utf-8"></script>
<script src="${ctx}/js/jquery-3.4.1.js" charset="utf-8"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        soulTable: 'soulTable',
        xmSelect: 'xm-select'
    })
    layui.use(['form', 'table', 'xmSelect', 'soulTable'], function () {
        var table = layui.table,
            form = layui.form,
            xmSelect = layui.xmSelect,
            soulTable = layui.soulTable;

        var demo2 = xmSelect.render({
            el: '#demo2',
            theme: {
                color: '#3BA9FF',
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
            data:[{
                'value': 1,
                'name': '待提交审核'
            }, {
                'value': 2,
                'name': '审核中'
            }, {
                'value': 3,
                'name': '已完成'
            }, {
                'value': 4,
                'name': '审核退回'
            }]
        })

        var paramSubmit = {
            listName: '',
            state: '',
            menuCode:'teachingReward'
        }

        form.on('submit(submit)', function (data) {
            var vals = data.field
            delete vals.select;
            Object.assign(vals, {
                state: demo2.getValue('valueStr'),
            })
            paramSubmit = vals
            table.reload('test', {
                page: false,
                where: paramSubmit
            });
        });

        form.on('submit(add)', function (data) {
            showChild({
                menuCode: 'teachingReward',
                btnCode: 'newAdd'
            })
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

        function setState(d) {
            var name = ''
            if (d == 1){
                name = '待提交审核'
            }else if (d == 2){
                name = '审核中'
            }else if (d == 3){
                name = '已完成'
            }else if (d == 4){
                name = '审核退回'
            }
            return '<div>'+name+'</div>'
        }

        function barDemo(d) {
            var _html = '<a class="layui-btn layui-btn-xs" lay-event="edit">处理</a>'
            var roleCode = $('#roleCode').val()
            if (roleCode == 1 || roleCode == 3){
                if (d.state == 1 || d.state == 4){
                    _html +=  '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete">删除</a>'
                }
            }
            return _html
        }

        var _cols = [
            [{
                field: 'listName',
                minWidth: 160,
                title: '清单名称',
            }, {
                field: 'teacherNum',
                minWidth: 100,
                title: '带教人数',
                sort: true,
                style: 'color:#3BA9FF',
                event: 'getList'
            }, {
                field: 'studentNum',
                minWidth: 100,
                title: '新人人数',
                sort: true,
            }, {
                field: 'teacherReward',
                minWidth: 120,
                title: '带教奖励金额',
                sort: true,
                templet: function (d) {
                    return getOfSignedNum(d.teacherReward)
                }
            }, {
                field: 'state',
                minWidth: 140,
                title: '清单状态',
                templet: function (d) {
                    return setState(d.state)
                }
            }, {
                field: 'craeteBy',
                minWidth: 120,
                title: '创建人',
            }, {
                field: 'createTime',
                minWidth: 170,
                title: '创建时间',
                sort: true,
                templet: function (d) {
                    return formatDate(d.createTime, 3)
                }
            }, {
                field: '',
                width: 130,
                title: '操作',
                templet: function (d) {
                    return barDemo(d)
                }
            }]
        ]

        var openIndex = ''

        var _h = $('.searchs').outerHeight() + $('.label-btns').outerHeight() + 50
        var fullH = 'full-' + _h
        table.render({
            id: "test",
            elem: '#test',
            cols: _cols,
            page: false,
            limit: 10000,
            height: fullH,
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
            done: function (res) {
                soulTable.render(this)
            }
        })
        //监听行工具事件
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            var param = {}
            if (obj.event === 'edit') {
                // qdId = obj.data.id
                Object.assign(param,{
                    id: obj.data.id,
                    btnCode:'handle',
                    menuCode: 'teachingReward'
                })
                showChild(param,obj.data)
            } else if (obj.event === 'getList') {
                // qdId = obj.data.id
                Object.assign(param,{
                    id: obj.data.id,
                    btnCode:'handle',
                    menuCode: 'teachingReward'
                })
                showChild(param,obj.data)
            }else if (obj.event === 'delete') {
                layer.confirm('确认删除清单：'+obj.data.listName+'？', {
                    btn: ['是','否'] //按钮
                }, function(){
                    operate({
                        btnCode: 'delete', //类型
                        id: obj.data.id,
                        menuCode:'teachingReward'
                    })
                }, function(){

                });
            }
        });


        function showChild(param, data) {
            var _cols_child = [
                [{
                    field: 'teacherUserId',
                    minWidth: 0,
                    title: '',
                    hide: true,
                    merge: true,
                },{
                    field: 'teacherUserName',
                    minWidth: 120,
                    title: '带教人',
                    fixed: 'left',
                    merge: ['teacherUserId', 'teacherUserName']
                }, {
                    field: 'teacherOrgName',
                    minWidth: 150,
                    title: '带教人机构',
                    merge: ['teacherUserId', 'teacherOrgName']
                },  {
                    field: 'realName',
                    minWidth: 120,
                    title: '新人',
                }, {
                    field: 'orgName',
                    minWidth: 150,
                    title: '新人机构',
                }, {
                    field: 'tel',
                    minWidth: 130,
                    title: '新人手机号',
                }, {
                    field: 'createTime',
                    minWidth: 130,
                    title: '入职时间',
                    templet: function (d) {
                        return formatDate(d.createTime)
                    }
                }, {
                    field: 'score',
                    minWidth: 140,
                    title: '达标时获得积分',
                    style: 'color:#3BA9FF',
                    event: 'scoreList',
                    templet: function (d) {
                        return getOfSignedNum(d.score)
                    }
                }, {
                    field: 'realQualifiedTime',
                    minWidth: 140,
                    title: '实际达标日期',
                    templet: function (d) {
                        return formatDate(d.realQualifiedTime)
                    }
                }, {
                    field: 'qualifiedEnfdTime',
                    minWidth: 140,
                    title: '达标截止日期',
                    templet: function (d) {
                        return formatDate(d.qualifiedEnfdTime)
                    }
                }, {
                    field: 'effecDays',
                    minWidth: 100,
                    title: '达标时效',
                    templet: '#inputD'
                }, {
                    field: 'teacherReward',
                    minWidth: 100,
                    title: '带教奖励',
                }, {
                    field: 'teacherRealReward',
                    minWidth: 110,
                    title: '实际奖励',
                    edit: 'text',
                    templet: '#inputA',
                }, {
                    field: 'rewardDesc',
                    minWidth: 150,
                    title: '备注',
                    edit: 'text',
                    templet: '#inputB',
                }, {
                    field: 'totalAmount',
                    minWidth: 150,
                    title: '带教奖励合计',
                    merge: ['teacherUserId', 'totalAmount']
                }]
            ]
            var _cols_child2 = [
                [{
                    field: 'teacherUserId',
                    minWidth: 0,
                    title: '',
                    hide: true,
                    merge: true,
                },{
                    field: 'teacherUserName',
                    minWidth: 120,
                    title: '带教人',
                    fixed: 'left',
                    merge: ['teacherUserId', 'teacherUserName']
                }, {
                    field: 'teacherOrgName',
                    minWidth: 150,
                    title: '带教人机构',
                    merge: ['teacherUserId', 'teacherOrgName']
                },  {
                    field: 'realName',
                    minWidth: 120,
                    title: '新人',
                }, {
                    field: 'orgName',
                    minWidth: 150,
                    title: '新人机构',
                }, {
                    field: 'tel',
                    minWidth: 130,
                    title: '新人手机号',
                }, {
                    field: 'createTime',
                    minWidth: 130,
                    title: '入职时间',
                    templet: function (d) {
                        return formatDate(d.createTime)
                    }
                }, {
                    field: 'score',
                    minWidth: 140,
                    title: '达标时获得积分',
                    style: 'color:#3BA9FF',
                    event: 'scoreList',
                    templet: function (d) {
                        return getOfSignedNum(d.score)
                    }
                }, {
                    field: 'realQualifiedTime',
                    minWidth: 140,
                    title: '实际达标日期',
                    templet: function (d) {
                        return formatDate(d.realQualifiedTime)
                    }
                }, {
                    field: 'qualifiedEnfdTime',
                    minWidth: 140,
                    title: '达标截止日期',
                    templet: function (d) {
                        return formatDate(d.qualifiedEnfdTime)
                    }
                }, {
                    field: 'effecDays',
                    minWidth: 100,
                    title: '达标时效',
                    templet: '#inputD'
                }, {
                    field: 'teacherReward',
                    minWidth: 100,
                    title: '带教奖励',
                }, {
                    field: 'teacherRealReward',
                    minWidth: 110,
                    title: '实际奖励',
                }, {
                    field: 'rewardDesc',
                    minWidth: 150,
                    title: '备注',
                }, {
                    field: 'totalAmount',
                    minWidth: 150,
                    title: '带教奖励合计',
                    merge: ['teacherUserId', 'totalAmount']
                }]
            ]

            var _height = $(document).height() * 0.9
            var _width = $(document).width() * 0.98
            var listName = data && data.listName || ''
            var roleCode = $('#roleCode').val()
            if ((data && (data.state == 1 || data.state == 4) && (roleCode == 1 || roleCode == 3)) || param.btnCode == 'newAdd'){
                openIndex = layer.open({
                    type: 1,
                    title: '清单【'+listName+'】列表数据',
                    area: [_width + 'px', _height + 'px'],
                    content: $('#table-content-child-h').html(),
                });
            } else if (data && data.state == 2 && (roleCode == 2 || roleCode == 3)){
                openIndex = layer.open({
                    type: 1,
                    title: '清单【'+listName+'】列表数据',
                    area: [_width + 'px', _height + 'px'],
                    content: $('#table-content-child-h2').html(),
                });
            } else if (data && (data.state == 3 || ((data.state == 1 || data.state == 4) && roleCode == 2) || ( data.state == 2 && roleCode == 1))){
                openIndex = layer.open({
                    type: 1,
                    title: '清单【'+listName+'】列表数据',
                    area: [_width + 'px', _height + 'px'],
                    content: $('#table-content-child-h3').html(),
                });
                _cols_child =  _cols_child2
            }
            if(data && (data.state == 4) && (roleCode == 1 || roleCode == 3)){
                $('.refuse div').text('驳回原因：'+ data.returnDesc)
                $('.refuse').show()

            }

            $('input[name=qdname]').val(listName)

            getChild(param,data || '',_cols_child)
        }

        function getChild(param,_data,_cols_child) {
            var _ch = $('.layui-form-submit').outerHeight() + 180 + $('.refuse').outerHeight()
            var fullcH = 'full-' + _ch
            var test_child = table.render({
                id: "test_child",
                elem: '#test_child',
                cols: _cols_child,
                page: false,
                limit: 10000,
                height: fullcH,
                drag: false,
                event: true,
                 url: '${ctx}/training/popup',
                 where: param,
                 parseData: function (res) {
                     return {
                         "code": res.isSuccess ? 0 : 1,
                         "msg": res.msg,
                         "count": res.count,
                         "data": res.results
                     }
                 },
                done: function (res) {
                    sessionStorage.setItem('qdChild', JSON.stringify(res.data))
                    soulTable.render(this)
                    var _body = $('.layui-layer.layui-layer-page .layui-layer-content .layui-table-main tbody tr')
                    var _fixed = $('.layui-layer.layui-layer-page .layui-layer-content .layui-table-fixed  .layui-table-body tbody tr')
                    _body.each(function(i, cur){
                        if ($(cur).find('td').length == 15){
                            $(cur).find('td:eq(1), td:eq(2), td:last').css({
                                'border-bottom-color': '#9999999e'
                            })
                            _body.eq(i-1).find('td').css({
                                'border-bottom-color': '#9999999e'
                            })

                            _fixed.eq(i).find('td').css({
                                'border-bottom-color': '#9999999e'
                            })
                            _fixed.eq(i-1).find('td').css({
                                'border-bottom-color': '#9999999e'
                            })
                        }
                    })

                },
                overflow: {
                    type: 'tips',
                    hoverTime: 100,
                    color: '#333',
                    bgColor: '#fff',
                    minWidth: 100,
                    maxWidth: 500,
                },
                initSort: {
                    field: 'teacherUserId',
                    type: 'desc'
                },
            })

            table.on('edit(test_child)', function (obj) {
                var value = obj.value.replace(/\s+/g, ""), //得到修改后的值
                    data = obj.data, //得到所在行所有键值
                    field = obj.field, //得到字段
                    qdChild = JSON.parse(sessionStorage.getItem('qdChild')) || []

                var fieldName = ''

                _cols_child[0].filter(function (cur, index) {
                    if (cur.field == field) {
                        fieldName = cur.title
                    }
                })

                var oldValue = ''
                qdChild.forEach(function (cur, index) {
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
                var paramE = {}
                if (field == 'teacherRealReward') {
                    paramE = {
                        "btnCode": "updMoney",
                        "menuCode": 'rookieManagement',
                        "money": value,
                        "ids": data.id,
                        'surveyTeachRewardListid': param.id//清单id
                    }

                } else {
                    paramE = {
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
                    data: paramE,
                    success: function (res) {
                        res = JSON.parse(res)
                        if (res.isSuccess) {
                            var dValue = value - oldValue
                            var i = $(obj.tr[0]).attr('data-index')
                            var _trs = $(
                                '.table-content-child .layui-table-box .layui-table-body table tbody tr'
                            )

                            var total = 0

                            if (field == 'teacherRealReward') {
                                for (var m = i; m >= 0; m--) {
                                    if (_trs.eq(m).find('td[data-field="totalAmount"]').length) {
                                        var oldTotal = _trs.eq(m).find('td[data-field=totalAmount]').children().text()
                                        total = Number(oldTotal) + dValue
                                        _trs.eq(m).find('td[data-field=totalAmount]').children().text(Math.round(total * 100) / 100)
                                        break;
                                    }
                                }
                            }

                            qdChild.forEach(function (cur, index) {
                                if (cur.id === data.id) {
                                    cur[field] = value
                                }
                                return cur
                            })
                            sessionStorage.setItem('qdChild', JSON.stringify(qdChild))

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
            table.on('tool(test_child)', function (obj) {
                var data = obj.data;
                if (obj.event === 'scoreList') {
                    showScoreList({
                        menuCode:'teachingRewardDetail',
                        id: obj.data.id
                    })
                }
            });

            form.on('submit(submitC)', function (data) {
                var qdChild = JSON.parse(sessionStorage.getItem('qdChild'))
                var ids = []
                qdChild.map(function (cur) {
                    ids.push(cur.id)
                })
                if (!ids.length){
                    layer.msg('当前清单无数据', {
                        time: 2000,
                        icon: 5
                    })
                    return;
                }
                var paramS = {
                    btnCode: data.elem.dataset.type, //类型
                    ids: ids.join(','),
                    id: param.id,
                    menuCode:'teachingReward'
                }
                var vals = data.field
                if (!vals.qdname && (data.elem.dataset.type == 'cancel' || data.elem.dataset.type == 'submit')) {
                    layer.msg('请输入清单名称', {
                        time: 2000,
                        icon: 5
                    })
                    return;
                }
                if (data.elem.dataset.type == 'reject'){
                    layer.prompt({title:"请输入驳回原因（必填项）",formType:2,shade: false},function (text,index) {
                        Object.assign(paramS, {
                            returnDesc: text
                        })
                        Object.assign(paramS)

                        operate(paramS)
                        return;
                    })

                }
                if (data.elem.dataset.type == 'adopt'){
                   layer.confirm('确认提交？', {
                        btn: ['是','否'] //按钮
                    }, function(){
                        Object.assign(paramS)
                        operate(paramS)
                    }, function(){

                    });
                }

                if (data.elem.dataset.type == 'cancel'){
                    layer.confirm('确认保存？', {
                        btn: ['是','否'] //按钮
                    }, function(){
                        if (param && param.btnCode == 'newAdd'){
                            delete paramS.id
                        }
                        Object.assign(paramS, {listName: vals.qdname})
                        operate(paramS)
                    }, function(){

                    });
                }
                if (data.elem.dataset.type == 'submit'){
                    layer.confirm('确认提交？', {
                        btn: ['是','否'] //按钮
                    }, function(){
                        if (param && param.btnCode == 'newAdd'){
                            delete paramS.id
                        }
                        Object.assign(paramS, {listName: vals.qdname})
                        operate(paramS)
                    }, function(){

                    });
                }

            });



        }

        //审核
        function operate(param, i) {
            $('.layui-layer-btn').css({
                'pointer-events': 'none'
            })
            setTimeout(function () {
                $('.layui-layer-btn').css({
                    'pointer-events': 'auto'
                })
            }, 6000)
            $.ajax({
                url: '${ctx}/training/getEdit',
                methods: 'post',
                data: param,
                success: function (res) {
                    res = JSON.parse(res)
                    $('.layui-layer-btn').css({
                        'pointer-events': 'auto'
                    })
                    if (res.isSuccess){
                        layer.msg('操作成功！',{
                            time: 2000,
                            icon: 1
                        },function () {
                            if (openIndex){
                                layer.close(openIndex)
                            }
                            location.reload()
                        })

                    } else {
                        layer.msg(res.msg,{
                            time: 2000,
                            icon: 5
                        },function () {
                            if (openIndex){
                                layer.close(openIndex)
                            }
                        })
                    }
                }
            })
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


        $('.label-btns .label-btn').click(function () {
            var data = table.checkStatus('test').data;
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

            if ($(this).hasClass('active')) {

            } else {

            }

        })
    })

    function isNull(d) {
        if (!d) {
            return '<div class="t-c">-</div>'
        } else {
            return '<div>' + d + '</div>'
        }
    }

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

    function getOfSignedNum(d) {
        var _d = Math.round(d * 100) / 100
        return '<div class="">' + _d + '</div>'

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