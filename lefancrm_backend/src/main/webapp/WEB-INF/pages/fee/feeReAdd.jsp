<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<html>
<head>
    <title>费用报销下发的新增</title>

    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">
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

        .layui-form-label {
            width: 82px;
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
            height: 36px;
            line-height: 36px;
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
            width: 110px;
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

        /*.table-content{*/
        /*    padding-bottom: 90px;*/
        /*}*/

        .layui-table-body{
            overflow: overlay;
        }

        .sum{
            position: absolute;
            top: 0;
            right: 0;
            padding-right: 20px;
            text-align: right;
            font-size: 12px;
            font-family: Impact;
        }
        .sum span{
            font-size: 26px;
            font-weight: bold;
            color: #666;
        }

    </style>

</head>
<body>
<div class="main">

    <div class="layui-form searchs" lay-filter="search">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">快捷查询</label>
                <div class="layui-input-inline _input" style="width: 300px">
                    <input type="text"  name="searchStr" placeholder="可输入被调查人，案件编号" class="layui-input">
                </div>
            </div>
<%--            <div class="layui-inline">--%>
<%--                <label class="layui-form-label">被调查人</label>--%>
<%--                <div class="layui-input-inline _input">--%>
<%--                    <input type="text" name="surveyPerson" placeholder="请输入被调查人" class="layui-input">--%>
<%--                </div>--%>
<%--            </div>--%>
            <div class="layui-inline">
                <label class="layui-form-label">委托方机构</label>
                <div class="layui-input-inline">
                    <div id="entrustOrgIdsChk" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <div class="d-flex-wrap">
                    <label class="layui-form-label" style="width:115px;">保司终审通过时间</label>
                    <div class="data-choose">
                        <input type="text" class="layui-input paramTime" readonly id="startTime"
                               placeholder="请选择日期">
                        <span class="dc-span">至</span>
                        <input type="text" class="layui-input paramTime" readonly id="endTime" placeholder="请选择日期">
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">
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

    <div class="layui-form layui-form-submit" style="margin-top: 20px;position:relative">
<%--        style="margin-top: 20px;position: fixed;bottom: 0px;left: 0px;background: #fff;width: 100%;padding-top: 15px;box-shadow: 0 20px 40px #333;"--%>
        <div class="layui-form-item">
            <div class="layui-inline" >
                <label class="layui-form-label">清单名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="reName" required placeholder="请输入清单名称" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline form-check">
                <!-- <button class="layui-btn layui-btn-primary" data-type="close">取消</button> -->
                <button class="layui-btn layui-btn-normal" data-type="download">下发报销单</button>
            </div>
            <div class="sum">
                <div class="layui-inline"><i class="layui-icon layui-icon-rmb"></i> 合计（案件数量）：<span>0</span></div>
                <div class="layui-inline"><i class="layui-icon layui-icon-rmb"></i> 合计（委托方确认结算价格）：<span>0</span></div>
            </div>
        </div>

    </div>
</div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="getDetail">查看详情</a>
</script>

<%--<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>--%>

<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>

<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })

    var type = '1';
    var initVals = ['', ''];
    var entrustOkPrice = 0;//委托方确认结算价格总计
    var riskNum = 0;//案子总数

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

    layui.use(['form', 'table', 'laydate','xmSelect'], function () {
        var form = layui.form,
            laydate = layui.laydate,
            xmSelect = layui.xmSelect;

        table = layui.table;
        var demo1 = xmSelect.render({
            el: '#entrustOrgIdsChk',
            theme: {
                color: '#3BA9FF',
            },
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

        var upMonth = setDate('upMonth').toString();
        var obj= {};
        obj.startTime = upMonth.split(",")[0];
        obj.endTime = upMonth.split(",")[1];
        initVals[0] = obj.startTime;
        initVals[1] = obj.endTime;
        setTable(obj);


        var startTime = laydate.render({
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
            }
        });
        var endTime = laydate.render({
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
            }
        });

        form.on('submit(submit)', function (data) {
            layer.load(0, {
                time: 1 * 1000
            });
            var vals = data.field;
            Object.assign(vals, {
                entrustOrgIds: demo1.getValue('valueStr'),
                startTime: $('#startTime').val(),
                endTime: $('#endTime').val(),
            });

            reloadTable(vals);
            <%--table.reload('test', {--%>
            <%--    url:'${ctx}/fee/getData',--%>
            <%--    where: vals //设定异步数据接口的额外参数--%>
            <%--    //,height: 300--%>
            <%--});--%>
        });

        //重载表格数据
        function reloadTable(param) {
            $('.table_block').empty();
            $('.table_block').append(
                ' <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg"></table>'
            );
            setTable(param)
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
        function PrefixInteger(num, m) {
            return (Array(m).join(0) + num).slice(-m);
        }

        //checkbox选中事件
        table.on('checkbox(test)', function (obj) {
            if (obj.type == "one") {//单选
                if (obj.checked) {
                    riskNum = riskNum + 1;
                    entrustOkPrice = parseFloat(entrustOkPrice) + obj.data.entrustOkPrice;
                } else {
                    riskNum = riskNum - 1;
                    entrustOkPrice = parseFloat(entrustOkPrice) - obj.data.entrustOkPrice;
                }
            } else {//全选
                var checkStatus = table.checkStatus('test');
                if (checkStatus.data.length > 0) {
                    riskNum = checkStatus.data.length;
                    entrustOkPrice = 0;
                    checkStatus.data.forEach(function (item, index) {
                        entrustOkPrice += item.entrustOkPrice;
                    })
                } else {
                    riskNum = 0;
                    entrustOkPrice = 0;
                }
            }
            entrustOkPrice = parseFloat(entrustOkPrice).toFixed(2);
            $('.sum span:first').text(riskNum)
            $('.sum span:last').text(entrustOkPrice)

        });

        function setTable(param) {
            myTable = table.render({
                id: "test",
                elem: '#test',
                cols: [
                    [{
                        type: 'checkbox'
                    },
                        {
                            field: 'surveyInfoId',
                            width: 0,
                            title: '案件id',
                            hide: true
                        },
                        {
                            field: 'surveyCaseNo',
                            width: '25%',
                            title: '案件编号',
                        },
                        {
                            field: 'surveyPerson',
                            width: '10%',
                            title: '被调查人',
                        }, {
                        field: 'entrustOrgId',
                        width: 0,
                        title: '保险公司id',
                        hide: true
                    }, {
                        field: 'entrustOrgName',
                        width: '25%',
                        title: '委托方机构',
                    },  {
                        field: 'entrustOkPrice',
                        width: '15%',
                        title: '委托方确认结算价格',
                    },{
                        field: 'entrustOprDateStr',
                        width: '15%',
                        title: '保司终审通过时间',
                    }, {
                        // fixed: 'right',
                        title: '操作',
                        toolbar: '#barDemo',
                        width: '10%'
                    }
                    ]
                ],
                cellMinWidth: 80,
                url: '${ctx}/fee/getData',
                where: param || {},
                page: false,
                height: 'full-220',
                parseData: function (res) {
                    riskNum = 0;
                    entrustOkPrice = 0;
                    res.results.list.forEach(function (item, index) {
                        entrustOkPrice += item.entrustOkPrice;
                        riskNum = riskNum + 1;
                    });
                    entrustOkPrice = parseFloat(entrustOkPrice).toFixed(2);
                    $('.sum span:first').text(riskNum)
                    $('.sum span:last').text(entrustOkPrice)
                    var entrustOrgIds = res.results.entrustOrgIds;
                    if (entrustOrgIds == null){
                        var consignorList = res.results.consignorList;
                        filterJson(demo1, consignorList,'id','company',false, false)
                    }
                    // consignorList.forEach(function (item) {
                    //     var optionHtml = '<option value="'+item.id+'">'+item.company+'</option>';
                    //     console.log(optionHtml)
                    //     $("select[name=entrustOrgIdsChk]").append(optionHtml)
                    // });
                    // form.render();
                    return {
                        "code": res.isSuccess ? '0' : '1', //解析接口状态
                        "msg": res.msg, //解析提示文本
                        "count": res.count, //解析数据长度
                        "data": res.results.list //解析数据列表
                    };

                    <%--$("select[name=entrustOrgIdsChk]")--%>

                    <%--<c:forEach items="${consignorList}" var="item">--%>
                    <%--<option value="${item.id}" >${item.company}</option>--%>
                    <%--</c:forEach>--%>
                }

            });

            table.on('tool(test)', function(obj){
                if (obj.event == 'getDetail'){
                    console.log(obj.data);
                    parent.parent.addTab(obj.data.surveyPerson, "${ctx}/survey/case/info?menuCode=feeViewManager&id=" + obj.data.surveyInfoId , true);
                }

            });
        }

        $('.form-check').on('click', 'button', function () {
            var _this = $(this);
            var type = _this.attr('data-type');
            if (type == 'close') {
                alert('取消')
            } else if (type == 'download') {
                var reName = $('input[name=reName]').val();
                console.log(reName == '',!reName);
                if (!reName) {
                    layer.msg('请输入清单名称',{
                        icon: 3
                    })
                } else {
                    var checkStatus = table.checkStatus('test').data;
                    var ids = [];
                    checkStatus.filter(function (cur) {
                        ids.push(cur.surveyInfoId)
                    });

                    if (table.checkStatus('test').data.length > 0) {
                        //接口
                        //id_arr, reName
                        var url = "${ctx}/fee/operate",data = {"ids":ids.join(","),"btnCode":'sued',"reName" : reName};
                        layer.confirm('确认下发？', {
                            btn: ['是','否'] //按钮
                        }, function(){
                            var $ = layui.$;
                            $('.layui-layer-btn .layui-layer-btn0').css({
                                'pointer-events': 'none'
                            });
                            $.ajax({
                                url:url,
                                type:"post",
                                data :data,
                                success:function(res){
                                    console.log('ssssssssssssss',res);
                                    res = JSON.parse(res);
                                    if (res.isSuccess){
                                        // closeDialogRefresh();//关闭并刷新
                                        layer.msg(res.msg, {
                                            time: 2000,
                                            icon: 1
                                        },function () {
                                            location.reload();
                                        })

                                    } else{
                                        layer.msg(res.msg, {
                                            time: 2000,
                                            icon: 2
                                        })
                                    }
                                }
                            });
                        }, function(){

                        });
                    } else {
                        layer.msg('勾选数据不能为空！',{
                            icon: 3,
                            zIndex: layer.zIndex + 1000
                        })
                    }


                }
            }
        })
    });


    function openInfo(surveyInfoId){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"案件详情",
            height:height,
            width:width,
            url:"${ctx}/survey/case/info?id=" + surveyInfoId,
            load: true
        });
    }

</script>

</body>
</html>
