<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>保险咨询案件管理</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="${ctx}/css/bootstrap-table-fixed-columns.css">
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">

    <style>
        .table-content {
            /*width: 1694px;*/
            overflow: auto;
            position: relative;
        }
        .table-thead{
            padding: 30px 0;
        }
        .fixed-table-toolbar {
            display: none !important;
        }
        .th-inner,
        .fht-cell, table td {
            width: 160px;
            text-align: center;
        }
        .panel{
            margin-bottom: 0!important;
        }
        .class-list a{
            color: #428bca;
        }
        .title_sort{
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
        }
        .icon-sort {
            display: inline-block;
            width: 10px;
            padding-left: 2px;
        }
        .icon-sort  .icon-up.active {
            border-bottom: 7px solid #333;
        }

        .icon-sort  .icon-down.active {
            border-top: 7px solid #333;
        }

        .icon-up {
            width: 0;
            height: 0;
            border-right: 5px solid transparent;
            border-left: 5px solid transparent;
            border-bottom: 7px solid #b3b3b3;
            margin-bottom: 2px;
        }

        .icon-down {
            width: 0;
            height: 0;
            border-right: 5px solid transparent;
            border-left: 5px solid transparent;
            border-top: 7px solid #b3b3b3;
        }

        .icon-up:hover {
            border-bottom: 7px solid #333333c2;
        }

        .icon-down:hover {
            border-top: 7px solid #333333c2;
        }

        .layui-table-body {
            overflow-y: overlay;
        }

        label.layui-form-label {
            width: auto;
            padding: 6px 0;
            margin-bottom: 0;
        }

        .selectMul {
            width: 160px;
        }

        .layui-inline{
            padding:0  10px;
            margin-bottom: 10px;
        }
        .paramTime{
            width: 180px;
            height: 32px;
            line-height: 32px;
        }
        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>保险咨询案件管理 <small>共<span class="sum"></span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info" style="border: none">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" >
                    <div class="form-group">
                        快捷查询:<input style="width: 350px" name="searchStr" type="text" value="${searchStr}" placeholder="客户名称，姓名，身份证号，手机号码" class="form-control">
                    </div>
                    <div class="form-group">
                        开票名称:<input style="width: 200px" name="kpmc" type="text" value="${kpmc}" placeholder="开票名称" class="form-control">
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">支付日期:</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input paramTime" readonly id="zfrq"
                                   placeholder="请选择日期" >
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">开票状态:</label>
                        <div class="layui-input-inline">
                            <div id="kpzt" class="selectMul"></div>
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">服务公司:</label>
                        <div class="layui-input-inline">
                            <div id="fwgs" class="selectMul"></div>
                        </div>
                    </div>
                    <div class="layui-inline">
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="button" class="btn btn-default">查询</button>
                        <button onclick="edit()" type="button" class="btn btn-default">添加</button>
<%--                        <button id="exportBtn" type="button" class="btn btn-default">导出</button>--%>
                    </div>
                    </div>
                    <div class="layui-inline">
<%--                    <button type="button" lay-submit="" class="layui-btn layui-btn-normal layui-btn-sm layui-btn-import" id="importOther"><i class="layui-icon"></i>导入</button>--%>
                    </div>


                </form>
            </div>
        </div>
        <div class="table-content">
            <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">
            </table>
        </div>

    </div>

</div><!--main end-->

<script type="text/html" id="barDemo">
    <a class=" layui-btn layui-btn-xs layui-btn" lay-event="open" >开票</a>
<%--    <a class=" layui-btn layui-btn-xs layui-btn-danger" lay-event="operate" >删除</a>--%>
    <a class=" layui-btn layui-btn-xs layui-btn-normal" lay-event="list" >跟踪信息</a>
</script>


<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<%--<script src="${ctx}/js/jquery-1.8.2.min.js"></script>--%>
<script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap-table-fixed-columns.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect.js?V=1"></script>
<script src="${ctx}/js/layui/layui.js"></script>


<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })

    layui.use(['table', 'util', 'xmSelect', 'laydate','jquery'], function () {
        var table = layui.table,
            util = layui.util,
            laydate = layui.laydate,
            xmSelect = layui.xmSelect,
            $ = layui.jquery



        document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==13){ // 按 enter
                // 查询方法();
                $("#batchOperateBtn").click();
            }
        }

        var cols = [
            [{
                field: 'khmc',
                width: 160, align: 'center',
                fixed: 'left',
                title: '客户名称',
                // style: 'color: #3BA9FF',
                // event: 'edit'
            },{
                field: 'kpmc',
                width: 200, align: 'center',
                title: '开票名称',
            },{
                field: 'zfrq',
                width: 120, align: 'center',
                title: '支付日期',
                templet: function (d) {
                    return d.zfrq ? util.toDateString(d.zfrq, 'yyyy-MM-dd') : ''
                }
            },{
                field: 'xm',
                width: 100, align: 'center',
                title: '姓名',
            },{
                field: 'sjhm',
                width: 150, align: 'center',
                title: '手机号码',
            },{
                field: 'sfzhm',
                width: 180, align: 'center',
                title: '身份证号码',
            },{
                field: 'skzh',
                width: 180, align: 'center',
                title: '收款账号',
            },{
                field: 'khyh',
                width: 200, align: 'center',
                title: '开户银行',
            },{
                field: 'srje',
                width: 100, align: 'center',
                title: '收入金额',
            },{
                field: 'je',
                width: 100, align: 'center',
                title: '金额',
            },{
                field: 'kpje',
                width: 100, align: 'center',
                title: '开票金额',
            },{
                field: 'gys',
                width: 160, align: 'center',
                title: '供应商',
            },{
                field: 'fwgs',
                width: 160, align: 'center',
                title: '服务公司',
            },{
                field: 'kpzt',
                width: 100, align: 'center',
                title: '开票状态',
                templet: function (d) {
                    var  text = ''
                    if (d.kpzt == 1){
                        text = '已开票'
                    } else if (d.kpzt == 2){
                        text = '未开票'
                    }
                    return text
                }
            },{
                width: 200,
                title: '操作',
                align: 'center',
                fixed: 'right',
                toolbar: '#barDemo'
            }
            ]]
        var param = {
            searchStr: $('input[name=searchStr]').val(),
            kpzt:$('select[name=kpzt]').val(),
        }
        setTable(cols, param)

        function setTable(_cols, param) {
            var _h = $('.main-top').outerHeight() + $('.panel-info').outerHeight() + 50
            var fullH = 'full-' + _h
            table.render({
                id: "test",
                elem: '#test',
                even: true,
                cols: _cols,
                height: fullH,
                drag: false,
                page: true,
                limit: 15,
                limits: [15,20,30,40,50],
                url: '${ctx}/fw/getDetail',
                where: param,
                autoSort: false,
                parseData: function (res) {
                    $('.sum').text(res.count)
                    return {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results,
                    }
                },
                request: {
                    pageName: 'page' //页码的参数名称，默认：page
                    ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
                },
                done: function (res) {
                    sessionStorage.setItem('yg',JSON.stringify(res.data))
                }
            })
            table.on('tool(test)', function (obj) {
                curObj = obj
                if (obj.event == 'list') {
                    progress(obj.data.id)
                }else if (obj.event == 'open') {
                    open(obj.data.id)
                }

            })
            table.on('sort(test)', function(obj) { //注：sort 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                console.log(obj.field); //当前排序的字段名
                console.log(obj.type); //当前排序类型：desc（降序）、asc（升序）、null（空对象，默认排序）
                console.log(this); //当前排序的 th 对象
                var paramOrder = {
                    order: '',
                    colSortType: ''
                }
                if (obj.field == 'entryTime'){
                    paramOrder.order = 1
                } else if (obj.field == 'quitTime'){
                    paramOrder.order = 2
                } else if (obj.field == 'createTime'){
                    paramOrder.order = 3
                }
                if (obj.type == 'asc'){
                    paramOrder.colSortType = 1
                } else if (obj.type == 'desc'){
                    paramOrder.colSortType = 2
                }
                Object.assign(param, paramOrder)
                table.reload('test', {
                    url: '${ctx}/fw/getDetail',
                    where: param,
                });
            })

        }
        $('#batchOperateBtn').click(function (e) {
            var zfrq = $('#zfrq').val()
            zfrq = zfrq.split('~')
            Object.assign(param, {
                order: '',
                colSortType: '',
                searchStr: $('input[name=searchStr]').val(),
                kpmc: $('input[name=kpmc]').val(),
                zfrqStart: zfrq[0] ? zfrq[0].trim() : '',
                zfrqEnd:zfrq[1] ? zfrq[1].trim() : '',
                kpzt: demo55.getValue('valueStr'),
                fwgs: demo56.getValue('valueStr')
            })

            $('.table-content').empty()
            $('.table-content').append('<table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">\n' +
                '            </table>')
            // table.render(tableRender)
            setTable(cols, param)


        })

        var demo55 = xmSelect.render({
            el: '#kpzt',
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
        var demo56 = xmSelect.render({
            el: '#fwgs',
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


        var kpztJson = [{"id" : 1,"name" : "已开票"},{"id" : 2,"name" : "未开票"}]
        filterJson(demo55, kpztJson, 'id', 'name', false, false)
        var s = [1,2];
        demo55.setValue(s)

        var fwgsJson =[{"id" : "公估","name" : "公估"},{"id" : "健康","name" : "健康"},{"id" : "金融","name" : "金融"},{"id" : "乐欲","name" : "乐欲"}]
        filterJson(demo56, fwgsJson, 'id', 'name', false, false)
        var s = ["公估","健康","金融","乐欲"];
        demo56.setValue(s)


        zfrq = laydate.render({
            elem: '#zfrq',
            range: '~',
            done: function (value, date) {
            }
        });


    })

    var open = function(id){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 10;
        openDialog({
            frame:true,
            title:"详情",
            height:height,
            width:1000,
            url: "${ctx}/billingApply/billApplyEdit?fwId=" + id + "&copy=8&id=" + id,
            load:true
        });
    }

    var progress = function(id){
        var width = $(document.body).outerWidth()*0.9;
        var height = $(document).outerHeight()*0.9;
        openDialog({
            frame:true,
            title:"跟踪记录",
            height:height,
            width:width,
            url:"${ctx}/fw/progress?fwId="+id+"&code=progress"
        });
    }


    function edit(){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 10;
        openDialog({
            frame:true,
            title:"详情",
            height:height,
            width:1000,
            url: "${ctx}/fw/info?fwId=",
            load:true
        });
    }
</script>



</body>
</html>
