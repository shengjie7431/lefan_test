<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">
    <style>
        .layui-form-label{
            box-sizing: content-box!important;
        }
        .main {
            width: 98%;
            margin: 0 auto;
        }

        .layui-form {
            margin-top: 10px;
        }

        .layui-form .layui-form-item .layui-inline {
            margin-right: 0;
        }

        .layui-form .layui-form-item .layui-inline .layui-form-label {
            width: 60px;
        }

        .layui-form-submit .layui-form-item .layui-inline .layui-form-label {
            width: 100px;
        }

        .layui-form .layui-inline .layui-form-item .layui-input-inline {
            width: 170px;
        }

        .layui-table-edit {
            width: 100% !important;
        }


        .layui-table .layui-input {
            height: 100%;
        }

        .layui-form-label-a {
            width: auto !important;
            white-space: nowrap;
        }

        .layui-form-label a {
            color: #3BA9FF !important;
            white-space: nowrap;
        }

        .searchs {
            padding: 20px 0 10px 0;
            background-color: #d9edf7;
        }

        .layui-btn-import{
            color:#fff!important;
        }
        input{
            border-color:#e6e6e6!important;
        }
        .top_reason{
            width: 100%;
            padding: 20px 2%;
            background-color:rgba(238, 136, 132, 0.26);
        }
        .top_reason div{
            padding: 10px 0;
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

        .layui-table-fixed-l table thead tr:first-of-type th:first-of-type,
        .layui-table-header table thead tr:first-of-type th:first-of-type,
        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+2),
        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+3){
            background-color: rgba(78, 183, 195, 0.3);
        }
        .layui-table-fixed-r table thead tr:first-of-type th:first-of-type{
            background-color: #f2f2f2;
        }

        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+4){
            border-right-color:rgba(101, 206, 114, 0.3);
            background-color: rgba(101, 206, 114, 0.3);

        }

        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+5){
            border-right-color:rgba(236, 170, 62, 0.3);
            background-color: rgba(236, 170, 62, 0.3);

        }

        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+6){
            border-right-color:rgba(179, 157, 219, 0.3);
            background-color: rgba(179, 157, 219, 0.3);
        }


        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+7),
        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+8),
        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+9){
            background-color: rgba(230, 152, 152, 0.3);
        }

        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 0),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 1),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 2),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 3),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 4),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 5),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 6),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 7),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 8),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 9),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 10),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 11),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 12),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 13),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 14),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 15),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 16){
            border-right-color:rgba(78, 183, 195, 0.3);
            background-color: rgba(78, 183, 195, 0.3);

        }


        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 17),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 18),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 19),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 20),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 21),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 22){
            border-right-color:rgba(101, 206, 114, 0.3);
            background-color: rgba(101, 206, 114, 0.3);

        }


        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 23),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 24),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 25),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 26),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 27),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 28),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 29),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 30),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 31){
            border-right-color:rgba(236, 170, 62, 0.3);
            background-color: rgba(236, 170, 62, 0.3);

        }
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 32),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 33),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 34),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 35),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 36){
            border-right-color:rgba(179, 157, 219, 0.3);
            background-color: rgba(179, 157, 219, 0.3);
        }
    </style>
</head>
<body>
<div class="main">
    <c:if test="${staffPerformance.reason != null}">
        <div class="top_reason">
            <div>驳回原因：${staffPerformance.reason}</div>
        </div>
    </c:if>
    <div class="layui-form searchs" lay-filter="search">
        <div class="layui-form-item " style="margin-bottom: 0">

            <div class="layui-inline">
                <label class="layui-form-label">工号</label>
                <div class="layui-input-inline _input">
                    <input type="text" name="jobNo" placeholder="请输入工号"  autocomplete="off" class="layui-input ">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-inline _input">
                    <input type="text" name="realName" placeholder="请输入姓名"  autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">入职时间</label>
                <div class="layui-input-inline _input">
                    <input type="text" name="entryTimeStr" class="layui-input" id="entryTimeStr" placeholder="请选择入职时间" style="cursor: pointer" readonly>
                </div>
            </div>
            <div class="layui-inline" style="padding-left: 80px">
                <label class="layui-form-label">
                    <button type="reset" class="layui-btn s-btn layui-btn-primary" data-type="reset"> 重置 <i class="layui-icon layui-icon-refresh" ></i></button>
                </label>
            </div>
        </div>
    </div>

    <div class="table_block">
        <table class="layui-table" id="test" lay-filter="test" lay-data="{id: 'test'}" lay-skin="line" lay-size="lg">
        </table>
    </div>
    <div class="layui-form layui-form-submit" style="margin-top:20px;position:relative">
        <div class="sum"><i class="layui-icon layui-icon-rmb"></i> 合计（实发绩效）：<span></span></div>
    </div>
    <div style="display: none">
        <input type="hidden" id="id" value="${staffPerformance.id}" />
        <input type="hidden" id="performanceState" value="${staffPerformance.performanceState}" />
        <input type="hidden" id="roleCode" value="${staffPerformance.roleCode}" />
    </div>
</div>
<script type="text/html" id="moneyInput">
    {{#  if(d.rate == 'undefinded' || d.rate == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <input value="{{d.rate}}" class="layui-input" type="text">
    {{#  } }}
</script>
<script type="text/html" id="moneyInput2">
    {{#  if(d.assessKpi == 'undefinded' || d.assessKpi == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <input value="{{d.assessKpi}}" class="layui-input" type="text">
    {{#  } }}
</script>
<script type="text/html" id="moneyInput3">
    {{#  if(d.integral == 'undefinded' || d.integral == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <input value="{{d.integral}}" class="layui-input" type="text">
    {{#  } }}
</script>
<script type="text/html" id="sickLeave">
    {{#  if(d.integralPay == 'undefinded' || d.integralPay == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <input value="{{d.integralPay}}" class="layui-input" type="text">
    {{#  } }}
</script>
<script type="text/html" id="moneyInput4">
    {{#  if(d.lateEarlyMoney == 'undefinded' || d.lateEarlyMoney == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <input value="{{d.lateEarlyMoney}}" class="layui-input" type="text">
    {{#  } }}
</script>
<script type="text/html" id="moneyInput5">
    {{#  if(d.absenteeismMoney == 'undefinded' || d.absenteeismMoney == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <input value="{{d.absenteeismMoney}}" class="layui-input" type="text">
    {{#  } }}
</script>
<script type="text/html" id="moneyInput6">
    {{#  if(d.leaveMoney == 'undefinded' || d.leaveMoney == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <input value="{{d.leaveMoney}}" class="layui-input" type="text">
    {{#  } }}
</script>
<script type="text/html" id="moneyInput7">
    {{#  if(d.sickLeaveTime == 'undefinded' || d.sickLeaveTime == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <input value="{{d.sickLeaveTime}}" class="layui-input" type="text">
    {{#  } }}
</script>
<script type="text/html" id="moneyInput8">
    {{#  if(d.sickLeaveMoney == 'undefinded' || d.sickLeaveMoney == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <input value="{{d.sickLeaveMoney}}" class="layui-input" type="text">
    {{#  } }}
</script>
<script type="text/html" id="moneyInput9">
    {{#  if(d.otherPay == 'undefinded' || d.otherPay == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <input value="{{d.otherPay}}" class="layui-input" type="text">
    {{#  } }}
</script>
<script type="text/html" id="moneyInput10">
    {{#  if(d.realPay == 'undefinded' || d.realPay == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <input value="{{d.realPay}}" class="layui-input" type="text">
    {{#  } }}
</script>
<script type="text/html" id="moneyInput11">
    {{#  if(d.remarks == 'undefinded' || d.remarks == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <input value="{{d.remarks}}" class="layui-input" type="text">
    {{#  } }}
</script>

<script type="text/html" id="moneyInput21">
    {{#  if(d.bsScore  == 'undefinded' || d.bsScore == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.bsScore}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput22">
    {{#  if(d.otherScore == 'undefinded' || d.otherScore == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.otherScore}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput23">
    {{#  if(d.sunScore == 'undefinded' || d.sunScore == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.sunScore}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput24">
    {{#  if(d.trafficSubsidy == 'undefinded' || d.trafficSubsidy == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.trafficSubsidy}}</div>
    {{#  } }}
</script>

<script src="${ctx}/js/layui/layui.js"></script>
<script>
    var $ = ''
    layui.use(['jquery'], function () {
        $ = jQuery = layui.$
        var ctx="${ctx}";
        /****************************** 切换可编辑列表 -  角色: _cols1 行政，_cols2 财务，_cols3 总部 _cols00 只读 *********************************/
        var _cols00 = [
            [{
                field: '',
                width: 50,
                title: '',
                fixed: 'left',
                rowspan: 2,
                type: 'numbers'
            },{
                field: 'realName',
                width: 100,
                title: '姓名',
                fixed: 'left',
                rowspan: 2,
            },{
                title: '员工信息',
                colspan: 16,
                align: 'center',
            }, {
                title: '基础与浮动绩效',
                colspan: 6,
                align: 'center'
            }, {
                title: '调查绩效与补贴',
                colspan: 9,
                align: 'center'
            }, {
                title: '考核绩效',
                colspan: 5,
                align: 'center'
            },{
                field: 'otherPay',
                width: 150,
                title: '其他补扣款',
                sort: true,
                rowspan: 2,
            },{
                field: 'remarks',
                width: 190,
                title: '备注',
                rowspan: 2,
            }, {
                field: 'realPay',
                width: 160,
                title: '实发绩效',
                sort: true,
                rowspan: 2,
                style: 'background:#eeeeee36',
            }],
            [{
                field: 'company',
                width: 200,
                title: '公司',
            },{
                field: 'organ',
                width: 150,
                title: '机构/部门',
            },{
                field: 'department',
                width: 130,
                title: '科室',
            },{
                field: 'team',
                width: 130,
                title: '小组',
            },{
                field: 'jobNo',
                width: 100,
                title: '工号',
            },{
                field: 'userTel',
                width: 120,
                title: '手机号',

            },{
                field: 'jobPost',
                width: 135,
                title: '岗位',
            },{
                field: 'entryTimeStr',
                width: 130,
                title: '入职时间',
            },{
                field: 'fixedPerfPayBase',
                width: 130,
                title: '固定绩效基数',
                sort: true,
            },{
                field: 'travelAllowancePayBase',
                width: 130,
                title: '驻外补贴基数',
                sort: true,
            },{
                field: 'managePerfPayBase',
                width: 130,
                title: '管理绩效基数',
                sort: true,
            },{
                field: 'basicIntegral',
                width: 120,
                title: '基础积分',
                sort: true,
            },{
                field: 'isNewPeople',
                width: 130,
                title: '是否新人达标',
                templet: '#isNewPeople',
            },
                {
                    field: 'realWorkingDays',
                    width: 130,
                    title: '实际出勤天数',
                    sort: true,
                },{
                field: 'workingDays',
                width: 140,
                title: '当月应上班天数',
                sort: true,
            },{
                field: 'rate',
                width: 100,
                title: '百分比',
                sort: true,
            }, {
                field: 'fixedPerfPay',
                width: 130,
                title: '实发固定绩效',
                sort: true,
            },{
                field: 'travelAllowancePay',
                width: 130,
                title: '实发驻外补贴',
                sort: true,
            },{
                field: 'managePerfPay',
                width: 130,
                title: '实发管理绩效',
                sort: true,
            },{
                field: 'assesPerfPay',
                width: 110,
                title: '考核绩效',
                sort: true,
            },{
                field: 'assessKpi',
                width: 130,
                title: '绩效考核系数',
                sort: true,
            },{
                field: 'realAssessKpi',
                width: 110,
                title: '实际绩效',
                sort: true,
            },
                {
                    field: 'bsScoreHz',
                    width: 160,
                    title: '互助积分（京沪）',
                    sort: true,
                },
                {
                    field: 'otherScoreHz',
                    width: 190,
                    title: '互助积分（非京沪）',
                    sort: true,
                },
                {
                    field: 'sunScoreHz',
                    width: 160,
                    title: '互助阳性积分',
                    sort: true,
                },
                {
                    field: 'bsScoreBs',
                    width: 160,
                    title: '保司积分（京沪）',
                    sort: true,
                },
                {
                    field: 'otherScoreBs',
                    width: 190,
                    title: '保司积分（非京沪）',
                    sort: true,
                },
                {
                    field: 'sunScoreBs',
                    width: 160,
                    title: '保司阳性积分',
                    sort: true,
                },
                {
                    field: 'monthBasicIntegral',
                    width: 170,
                    title: '当月有效基础积分',
                    sort: true,
                },
                {
                    field: 'integralPay',
                    width: 110,
                    title: '积分绩效',
                    sort: true,
                },
                {
                    field: 'trafficSubsidy',
                    width: 150,
                    title: '市内交通补贴',
                    sort: true,
                },
                {
                    field: 'lateEarlyMoney',
                    width: 150,
                    title: '迟到/早退扣款',
                    sort: true,
                }, {
                field: 'absenteeismMoney',
                width: 150,
                title: '旷工扣款',
                sort: true,
            }, {
                field: 'leaveMoney',
                width: 150,
                title: '事假扣款',
                sort: true,
            }, {
                field: 'sickLeaveTime',
                width: 140,
                title: '病假时长',
                sort: true,
            }, {
                field: 'sickLeaveMoney',
                width: 150,
                title: '病假扣款',
                sort: true,
            }
            ]
        ]
        var _cols = _cols00

        var param = JSON.parse(sessionStorage.getItem('param'))
        var paramFocus = {
            jobNo: '',
            realName: ''
        }
        var _ajax = $.ajax
        var _data = ''
        // 初始化、刷新排序
        var sort_field = JSON.parse(sessionStorage.getItem('sort_field'))
        if (sort_field) {
            Object.assign(tableRender, {
                initSort: {
                    field: sort_field.field, //排序字段，对应 cols 设定的各字段名
                    type: sort_field.type //排序方式  asc: 升序、desc: 降序、null: 默认排序
                }
            })
        }

        var _h = $('.searchs').outerHeight() + $('.stepInfo').outerHeight()+ $('.fileData').outerHeight() + $('.sum_content').outerHeight() + $('.top_reason').outerHeight() + 80
        var fullH = 'full-' + _h
        var tableRender = {
            id: "test",
            elem: '#test',
            cols: _cols,
            page: false,
            limit: 10000,
            height: fullH,
            even: true,
            cellMinWidth: 80,
        }

        // 首次打开
        if (window.name == "" || window.name == "refresh" || window.name == "mainFrame") {
            //首次被加载 清空查询条件
            sessionStorage.removeItem('param')
            sessionStorage.removeItem('jx_New')
            sessionStorage.removeItem('sort_field')
            console.log("首次被加载",  window.name);
            window.name = "isReload"; // 在首次进入页面时我们可以给window.name设置一个固定值
            initTable()
        } else if (window.name == "isReload") {
            console.log("页面被刷新",  window.name);
            var _data = JSON.parse(sessionStorage.getItem('jx_New'))
            Object.assign(tableRender,{data: _data})
            reloadTable(tableRender)
        }

        function initTable(){
            $.ajax({
                url:'${ctx}/staff/getDetail',
                type:"post",
                data : {"dataCode":"performance","id":$("#id").val(),"type":"view"},
                success:function(res){
                    res = JSON.parse(res)
                    if (res.isSuccess){
                        var list = res.results.data
                        var sum = 0
                        list.map(function (cur,i) {
                            if (cur.realPay){
                                sum += cur.realPay
                            }
                        })
                        $('.sum span').text(sum.toFixed(2))
                        Object.assign(tableRender,{
                            data: list
                        })
                        setTable(tableRender)
                        sessionStorage.setItem('jx_', JSON.stringify(list))
                        sessionStorage.setItem('jx_New', JSON.stringify(list))
                    }

                }
            });
        }


        /****************************** 初始化单元格 *********************************/
        function setTable(tableRender){
            layui.use('table', function () {
                table = layui.table
                table.render(tableRender);
            });
        }

        /****************************** 筛选 选择框、输入框、日期选择 初始化*********************************/
        function setForm1(){
            layui.use('form', function () {
                var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
                var _vals= {}
                if (param && param.businessUnit){
                    Object.assign(_vals,{
                        businessUnit: param.businessUnit
                    })
                }
                if (param && param.company){
                    Object.assign(_vals,{
                        company: param.company
                    })
                }
                if (param && param.organ){
                    Object.assign(_vals,{
                        organ: param.organ
                    })
                }
                if (param && param.department){
                    Object.assign(_vals,{
                        department: param.department
                    })
                }
                console.log('_vals',_vals)
                form.val('search',_vals)
                form.on('select(select)', function (data) {
                    var key = data.elem.getAttribute('name'),
                        value = data.value
                    var _html =" <option value=''>请选择</option>"
                    if (key == 'businessUnit'){
                        if (param && param.company){
                            param.company = ''
                            $('.searchs select[name=company]').html(_html)
                        }
                        if (param && param.organ){
                            param.organ = ''
                            $('.searchs select[name=organ]').html(_html)
                        }
                        if(param && param.department){
                            param.department = ''
                            $('.searchs select[name=department]').html(_html)
                        }
                    }else if (key == 'company'){
                        if (param && param.organ){
                            param.organ = ''
                            $('.searchs select[name=organ]').html(_html)
                        }
                        if (param && param.department){
                            param.department = ''
                            $('.searchs select[name=department]').html(_html)
                        }
                    }else if (key == 'organ'){
                        if (param && param.department){
                            param.department = ''
                            $('.searchs select[name=department]').html(_html)
                        }
                    }
                    form.val('search',_vals)
                    sessionStorage.setItem('param', JSON.stringify(param))
                    searchByParam(key,value)
                });
            })
        }
        var form = ''
        layui.use('form', function () {
            form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
            form.on('select(select)', function (data) {
                var key = data.elem.getAttribute('name'),
                    value = data.value
                var _html =" <option value=''>请选择</option>"

                var param = JSON.parse(sessionStorage.getItem('param')) || {}

                //更新选择框基础数据 与 值
                if (key == 'businessUnit'){
                    $('.searchs select[name=company]').html(_html)
                    $('.searchs select[name=organ]').html(_html)
                    $('.searchs select[name=department]').html(_html)
                    Object.assign(param,{
                        businessUnit: value,
                        company: '',
                        organ: '',
                        department: ''
                    })
                    if (value){
                        getParam(form,{
                            businessUnitId:param.businessUnit,
                            surveyCode:"businessUnit",
                            btnCode:1000}, 'businessUnit')
                    }
                }else if (key == 'company'){
                    $('.searchs select[name=organ]').html(_html)
                    $('.searchs select[name=department]').html(_html)
                    Object.assign(param,{
                        company:value,
                        organ: '',
                        department: ''
                    })
                    if (value){
                        getParam(form,{
                            businessUnitId:param.businessUnit,
                            companyId:param.company,
                            surveyCode:"company",
                            btnCode:1000}, 'company')
                    }

                }else if (key == 'organ'){
                    $('.searchs select[name=department]').html(_html)
                    Object.assign(param,{
                        organ: value,
                        department: ''
                    })
                    if (value){
                        getParam(form,{businessUnitId:param.businessUnit,
                            companyId:param.company,
                            organId:param.organ,
                            surveyCode:"organ",
                            btnCode:1000}, 'organ')
                    }

                }else if (key == 'department'){
                    Object.assign(param,{
                        department: value
                    })
                }
                //更新渲染选择框
                form.val('search',param)
                searchByParam(key,value,param)

            });
        })

        $('.searchs  ._input input').on('blur',function(){
            var _this = $(this)
            var key = _this.attr('name')
            var value = _this.val()
            console.log(' blur:',paramFocus[key], '-',value)
            if (paramFocus[key] !== value){
                searchByParam(key,value)
                Object.assign(paramFocus,{
                    [key]: value
                })
                console.log(paramFocus)
            }
        }).on('keyup', function(e){
            if (e.keyCode == 13){
                var _this = $(this)
                var key = _this.attr('name')
                var value = _this.val()
                console.log(' keyup:',paramFocus[key],'-',value)
                if (paramFocus[key] !== value){
                    searchByParam(key,value)
                    Object.assign(paramFocus,{
                        [key]: value
                    })
                    console.log(paramFocus)
                }

            }
        })
        var laydat= ''
        layui.use('laydate', function(){
            laydate = layui.laydate;
            var _value = param && param.entryTimeStr ? param.entryTimeStr : ''
            laydate.render({
                elem: '#entryTimeStr',
                value: _value,
                isInitValue: true,
                done: function(value, date){
                    var key = 'entryTimeStr'
                    console.log('-laydate-',paramFocus,key,value)
                    if (paramFocus[key] != value){
                        searchByParam(key,value)
                    }
                }
            });
        })

        /****************************** 提交审核 相关 *********************************/
        $('.form-check').on('click', 'button', function(){
            var _this = $(this)
            var type = _this.attr('data-type')
            if (type == 'check_hr'){
                var pw = $('input[name=password]').val()
                if (!pw){
                    layer.msg('请输入查询密码')
                }else {
                    operate({
                        id:$('#id').val(),
                        btnCode:"hr-step",
                        password: pw
                    })

                    $('input[name=password]').attr('disabled',true)
                    $('.lf-import button').attr('disabled',true)
                    _this.attr('disabled',true)
                }
            }else  if (type == 'check_fd'){
                operate({
                    id:$('#id').val(),
                    btnCode:"finance-step",
                    stepType: "yes"
                })
                $('.lf-import button').attr('disabled',true)
                _this.attr('disabled',true)
            }else  if (type == 'check_dp'){
                operate({
                    id:$('#id').val(),
                    btnCode:"division-step",
                    stepType: "yes"
                })
                $('.lf-import button').attr('disabled',true)
                _this.attr('disabled',true)
            }else  if (type == 'check_ceo'){
                operate({
                    id:$('#id').val(),
                    btnCode:"ceo-step",
                    stepType: "yes"
                })
                $('.lf-import button').attr('disabled',true)
                _this.attr('disabled',true)
            }else if (type == 'reject'){
                layer.prompt({title:"请输入拒绝原因",formType:2},function (text,index) {
                    layer.close(index)
                    operate({
                        id:$('#id').val(),
                        btnCode:$('#roleCode').val(),
                        stepType: "no",
                        reason: text
                    })
                    $('.lf-import button').attr('disabled',true)
                    _this.attr('disabled',true)
                })

            }
        })

        function  operate(data, key) {
            console.log(data)
            data["operateCode"] = "performance";//绩效
            _ajax({
                url:'${ctx}/staff/operate',
                type:"post",
                data :data,
                success:function(res){
                    res = JSON.parse(res)
                    $('.lf-import button').removeAttr('disabled')
                    if (res.isSuccess){
                        layer.msg(res.msg, {
                            time: 2000
                        },function () {
                            sessionStorage.removeItem('param')
                            sessionStorage.removeItem('jx_New')
                            sessionStorage.removeItem('sort_field')
                            closeDialogRefresh()
                        })
                        // Object.assign(tableRender,{cols: _cols4})
                        // reloadTable(tableRender)
                    } else{
                        layer.msg(res.msg, {
                            time: 2000
                        })
                    }
                }
            });
        }

        /****************************** 重置 *********************************/
        $('.searchs .s-btn').on('click', function () {
            var _this = $(this)
            var type = _this.attr('data-type')
            if (type == 'reset') {//重置
                sessionStorage.removeItem('param')
                sessionStorage.removeItem('jx_New')
                sessionStorage.removeItem('sort_field')

                var _html =" <option value=''>请选择</option>"
                laydate.render({
                    elem: '#entryTimeStr',
                    value: '',
                    isInitValue: true,})
                $('.searchs input[name=realName]').val('')
                $('.searchs input[name=jobNo]').val('')
                $('.searchs select[name=company]').html(_html)
                $('.searchs select[name=organ]').html(_html)
                $('.searchs select[name=department]').html(_html)
                var param = {
                    businessUnit: '',
                    company: '',
                    organ: '',
                    department: '',
                    jobNo: '',
                    realName: '',
                    entryTimeStr: ''
                }
                sessionStorage.setItem('param',JSON.stringify(param))
                form.val('search',param)
                var jx_ = JSON.parse(sessionStorage.getItem('jx_'))

                var sum = 0
                jx_.map(function (cur,i) {
                    if (cur.realPay){
                        sum += cur.realPay
                    }
                })
                $('.sum span').text(sum.toFixed(2))

                sessionStorage.setItem('jx_New', JSON.stringify(jx_))
                Object.assign(tableRender,{data: jx_})
                reloadTable(tableRender)
            }
        })

        // 更新表格数据
        function searchByParam(key, value, paramNow){
            var param = {},
                jx_ = JSON.parse(sessionStorage.getItem('jx_')),
                jx_New = '';
            if (paramNow){
                param = paramNow
            } else{
                param = JSON.parse(sessionStorage.getItem('param')) || {}
            }
            Object.assign(param, {
                [key]: value
            })
            console.log('param',param)
            if (!value && !param.realName && !param.businessUnit && !param.company && !param
                .department && !param.jobNo && !param.entryTimeStr) {
                jx_New = jx_
            } else {
                jx_New = jx_.filter(function (cur, index) {
                    var flag = true
                    if (param.realName) {
                        var str = cur.realName
                        var str2 = param.realName
                        if (str){
                            flag = flag && str.indexOf(str2) > -1
                        } else {
                            flag = false
                        }
                    }
                    if (param.businessUnit) {
                        flag = flag && cur.businessUnitId == param.businessUnit
                    }
                    if (param.company) {
                        flag = flag && cur.companyId  == param.company
                    }
                    if (param.organ) {
                        flag = flag && cur.organId  == param.organ
                    }
                    if (param.department) {
                        flag = flag && cur.departmentId  == param.department
                    }
                    if (param.jobNo) {
                        var str = cur.jobNo
                        var str2 = param.jobNo
                        if (str){
                            flag = flag && str.indexOf(str2) > -1
                        } else {
                            flag = false
                        }
                    }
                    if (param.entryTimeStr) {
                        flag = flag && cur.entryTimeStr == param.entryTimeStr
                    }
                    return flag
                })
            }

            var sum = 0
            jx_New.map(function (cur,i) {
                if (cur.realPay){
                    sum += cur.realPay
                }
            })
            $('.sum span').text(sum.toFixed(2))

            sessionStorage.setItem('param', JSON.stringify(param))
            sessionStorage.setItem('jx_New', JSON.stringify(jx_New))

            Object.assign(tableRender,{data: jx_New, cols: _cols})
            reloadTable(tableRender)
            <%--table.reload('test',{--%>
            <%--    url: '${ctx}/staff/getDetail',--%>
            <%--    method: 'post',--%>
            <%--    initSort: {--%>
            <%--        field: "basePay", //排序字段，对应 cols 设定的各字段名--%>
            <%--        type: "asc" //排序方式  asc: 升序、desc: 降序、null: 默认排序--%>
            <%--    },--%>
            <%--    cols: _cols4,--%>
            <%--    where: {"dataCode":"performance","id":$("#id").val()}--%>
            <%--},'data')--%>
        }

        //更新查询条件基础数据
        function  getParam(form,data, key) {
            $.ajax({
                url:'${ctx}/staff/selectStaffInfoByRelationId',
                type:"post",
                data :data,
                async: false,
                success:function(res){
                    res = JSON.parse(res)
                    console.log('------', res)
                    var _html =" <option value=''>请选择</option>"
                    if (key == 'businessUnit'){
                        res.results.map(function (cur,index) {
                            _html += "<option value='"+ cur.companyId+ "'>"+cur.companyName+"</option>"
                        })
                        $('.searchs select[name=company]').html(_html)
                    }else if (key == 'company'){
                        res.results.map(function (cur,index) {
                            _html += "<option value='"+ cur.organId+ "'>"+cur.organName+"</option>"
                        })
                        $('.searchs select[name=organ]').html(_html)
                    }else if (key == 'organ'){
                        res.results.map(function (cur,index) {
                            _html += "<option value='"+ cur.departmentId+ "'>"+cur.departmentName+"</option>"
                        })
                        $('.searchs select[name=department]').html(_html)
                    }
                    form.render('select', 'search');
                }
            });
        }

        //重载表格数据
        function reloadTable(tableRender) {
            $('.table_block').empty()
            $('.table_block').append(' <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg"></table>')
            // table.render(tableRender)
            setTable(tableRender)

        }
    })

    //正则匹配
    var checkPapers = function (parama, paramb) {
        var map = new Map([
            ['phone', /^[1][3,5,7,8,9][0-9]{9}$/],
            ['passport', !/^((1[45]\d{7})|(G\d{8})|(P\d{7})|(S\d{7,8}))?$/],
            ['identity', /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/],
            ['money', /^[0-9]+(\.[0-9]{1,2})?$/],
            ['moneyorMinus', /^(\-|\+)?[0-9]+(.[0-9]{1,2})?$/],
            ['moneyorMinus', /^(\-|\+)?\d+(\.\d{1,2})?$/],
            ['email',/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/],
            ['num_0_1',/^(0+(\.[0-9]{1,2})?|1|1.0|1.00?)$/]
        ])
        if (map.get(parama).test(paramb)) {
            return true
        } else {
            return false
        }
    }

</script>



<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>


<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script type="text/javascript">
    </body>
    </html>