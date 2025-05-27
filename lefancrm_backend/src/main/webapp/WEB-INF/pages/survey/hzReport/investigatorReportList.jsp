<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>调查员报表</title>
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">

    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/layui/layui_exts/soulTable.css">
    <style>
        body{
            display: flex;

        }
        .zw{
            min-width: 180px;
            height: 100%;
        }
        .zz{

        }
        .main {
            width: 99%;
            min-width: 878px;
            margin: 10px auto;
        }


        .searchs {
            width: 98%;
            margin: 0 auto;
            padding: 10px 0;
            background-color: #fff;
            border-bottom: 1px solid #bbb;
        }
        .searchs .layui-form-item-lf {
            width: 98%;
            padding-left: 2%;
            margin-bottom: 0!important;
            display: flex; justify-content: space-between
        }

        .layui-form-label {
            width: auto;
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
            align-items: center;
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

        .layui-layer.layui-layer-page {
            width: 98% !important;
            left: 1% !important;
            top: 20px !important;
        }

        .layui-table-body {
            overflow: overlay;
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
        .i_tab{
            /* width: 170px; */
            display: flex;
        }
        .i_header{
            width: 130px;
            background-color: #fff;
            color: #3BA9FF;
            align-self: flex-end;
        }
        .i_header.hz{
            margin-right: 8px;
            height: 38px;
            line-height: 38px;
            background-color: #3BA9FF;
            color: #fff;
            text-align: center;
        }
        .i_header.bs{
            cursor: pointer;
        }
        .content{
            width: 98%;
            margin: 0 auto;
            padding-top: 20px;
        }

        .content .l-block{
            width: 98%;
            padding-left: 2%;
        }
        .l-items{
            /* min-width: 880px; */
            display: flex;
            flex-wrap: wrap;

        }
        .l-items .l-item.l-item-1{
            background-color: #c2e5c9;
        }
        .l-items .l-item {
            box-sizing:border-box;
            width: 23%;
            min-width: 260px;
            max-width: 400px;
            padding: 20px;
            border-radius: 5px;
            background-color: #eee;
            margin-right: 18px;
            margin-bottom: 18px;
            cursor: pointer;
        }
        /*.l-items2 .l-item{*/
            /*width: 23%;*/
        /*}*/
        .l-items .l-item .l-item-title{
            padding: 10px 0;
            display: flex;
            align-items: center;
        }
        .l-items .l-item .l-item-title .l-item-title-t{
            font-size: 20px;
            font-weight: bold;
        }
        .l-items .l-item .l-item-title .l-item-title-i{
            font-size: 14px;
            color: #FB8C00;
        }
        .l-item-num {
            display: flex;
            align-items: center;
            justify-content: space-between;
            height: 40px;
        }
        .l-item-num .l-item-num-t{
            font-size: 40px;
            font-weight: bold;
            color:#3BA9FF;
            /*padding-right: 20px;*/
        }

        .l-item-num .l-item-num-i{
            align-self: flex-end;
            width: 101px;
            display: flex;
            align-items: center;

        }
        .l-item-num .l-item-num-i span{
            font-size: 14px;
        }
        .l-item-num .l-item-num-i .l-item-num-i-r{
            color: #333;
            padding: 0 8px;
        }

        .l-item-num .l-item-num-i .l-item-num-i-r.color1 {
            color: #FF3D00;
        }

        .l-item-num .l-item-num-i .l-item-num-i-r.color2 {
            color: #439011;
        }


        .layui-form-label{
            padding: 9px 5px;
        }
        .selectMul {
            width: 140px;
        }

        label{
            margin-bottom: 0;
        }

        /*-180*/
        @media screen and (max-width:1220px){
            .l-items .l-item{
                padding: 10px 10px 10px 20px;
                margin-bottom: 10px;
                margin-right: 10px;
            }

            .data-choose input {
                width: 94px;
            }

            .i_header{
                width: 100px;
            }
            .l-item-num .l-item-num-t{
                font-size: 32px;
                /*padding-right: 10px;*/
            }

            .selectMul {
                width: 119px;
            }

            .data-types .data-type {
                width: 48px;
            }
        }

        @media screen and (max-width:1020px){
            .l-item-num .l-item-num-i .l-item-num-i-r{
                padding: 0 4px;
            }
            .i_header{
                width: 100px;
            }

        }
        @media screen and (max-width:969px) {
            .l-items .l-item .l-item-title .l-item-title-t{
                font-size: 19px;
            }
            .l-items .l-item .l-item-title .l-item-title-i{
                font-size: 13px;
            }
            /*.l-item-num .l-item-num-t{*/
                /*font-size: 38px;*/
            /*}*/
        }

        @media screen and (max-width:920px){

            /*.l-items .l-item:nth-of-type(3n){*/
                /*margin-right: 0;*/
            /*}*/
            .ll-time{
                padding: 9px 5px;
            }


        }


    </style>
</head>

<body>
<div class="main">
    <input type="hidden" value='${params.franchiseesJson}'  id="franchiseesJson" />
    <input type="hidden" value='${params.investigatorsJson}' id="investigatorsJson" />
    <input type="hidden" value='${params.consignorsJson}' id="consignorsJson" />
    <input type="hidden" value="${organizationType}" id="organizationType"/>
    <input type="hidden"  id="currentUserId"/>
    <div class="layui-form searchs" lay-filter="search">
        <div class="layui-form-item-lf">
            <div class="layui-inline">
                <div class="i_tab">
                    <c:if test="${organizationType==1 || organizationType ==3}">
                        <div class="i_header hz">互助版</div>
                    </c:if>
                    <c:if test="${organizationType==2 || organizationType ==3}">
                        <div class="i_header bs" onclick="openInvestigatorReport()">切至保司版</div>
                    </c:if>
                </div>
            </div>
          <div style="display: flex">
              <div class="layui-inline" >
                  <label class="layui-form-label">互助机构</label>
                  <div class="layui-input-inline">
                      <div id="surveyOrgId" class="selectMul"></div>
                  </div>
              </div>
              <div class="layui-inline" >
                  <label class="layui-form-label">调查员</label>
                  <div class="layui-input-inline">
                      <div id="surveyInvestigators" class="selectMul"></div>
                  </div>
              </div>
          </div>
           <div class="layui-inline">
                   <div class="d-flex-wrap">
                       <label class="layui-form-label ll-time">时间</label>
                       <div class="d-flex-wrap">
                           <div class="data-types">
                               <div class="data-type " data-id="upMonth">上月</div>
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
        </div>
    </div>
    <div class="content">
        <div class="l-block">
            <div class="l-items">
                <div class='l-item l-item-1' data-id='1' data-name=''>
                    <div class="l-item-title">
                        <div class="l-item-title-t">在途调查案件数</div>
                        <div class="l-item-title-i">（实时）</div>
                    </div>
                    <div class="l-item-num">
                        <div class="l-item-num-t"></div>
                    </div>
                </div>
                <div class='l-item l-item-1' data-id='2'>
                    <div class="l-item-title">
                        <div class="l-item-title-t">在途超期案件数</div>
                        <div class="l-item-title-i">（实时）</div>
                    </div>
                    <div class="l-item-num">
                        <div class="l-item-num-t"></div>

                    </div>
                </div>
                <div class='l-item l-item-1' data-id='3'>
                    <div class="l-item-title">
                        <div class="l-item-title-t">职称等级</div>
                        <div class="l-item-title-i">（实时）</div>
                    </div>
                    <div class="l-item-num">
                        <div class="l-item-num-t" style="font-size: 30px"></div>
                    </div>
                </div>
            </div>
            <div class="l-items l-items2">
                <div class='l-item'  data-id='4'>
                    <div class="l-item-title">
                        <div class="l-item-title-t">承接案件数</div>
                    </div>
                    <div class="l-item-num">
                        <div class="l-item-num-t"></div>
                        <div class="l-item-num-i">
                            <span>环比</span>
                            <span class="l-item-num-i-r"></span>
                        </div>
                    </div>
                </div>
                <div class='l-item'  data-id='5'>
                    <div class="l-item-title">
                        <div class="l-item-title-t">平台复审通过件数
                        </div>
                    </div>
                    <div class="l-item-num">
                        <div class="l-item-num-t"></div>
                        <div class="l-item-num-i">
                            <span>环比</span>
                            <span class="l-item-num-i-r"></span>
                        </div>
                    </div>
                </div>
                <div class='l-item'  data-id='6'>
                    <div class="l-item-title">
                        <div class="l-item-title-t">保司终审通过件数</div>
                    </div>
                    <div class="l-item-num">
                        <div class="l-item-num-t"></div>
                        <div class="l-item-num-i">
                            <span>环比</span>
                            <span class="l-item-num-i-r"></span>
                        </div>
                    </div>
                </div>
                <div class='l-item'  data-id='7'>
                    <div class="l-item-title">
                        <div class="l-item-title-t">平台复审通过任务积分</div>
                    </div>
                    <div class="l-item-num">
                        <div class="l-item-num-t"></div>
                        <div class="l-item-num-i">
                            <span>环比</span>
                            <span class="l-item-num-i-r"></span>
                        </div>
                    </div>
                </div>
                <div class='l-item'  data-id='8'>
                    <div class="l-item-title">
                        <div class="l-item-title-t">保司终审通过任务积分</div>
                    </div>
                    <div class="l-item-num">
                        <div class="l-item-num-t"></div>
                        <div class="l-item-num-i">
                            <span>环比</span>
                            <span class="l-item-num-i-r"></span>
                        </div>
                    </div>
                </div>
                <div class='l-item'  data-id='16'>
                    <div class="l-item-title">
                        <div class="l-item-title-t">查得率</div>
                    </div>
                    <div class="l-item-num">
                        <div class="l-item-num-t"></div>
                        <div class="l-item-num-i">
                            <span>环比</span>
                            <span class="l-item-num-i-r"></span>
                        </div>
                    </div>
                </div>
                <div class='l-item'  data-id='9'>
                    <div class="l-item-title">
                        <div class="l-item-title-t">超期案件数</div>
                    </div>
                    <div class="l-item-num">
                        <div class="l-item-num-t"></div>

                    </div>
                </div>
                <div class='l-item'  data-id='10'>
                    <div class="l-item-title">
                        <div class="l-item-title-t">超期率</div>
                    </div>
                    <div class="l-item-num">
                        <div class="l-item-num-t"></div>
                        <div class="l-item-num-i">
                            <span>环比</span>
                            <span class="l-item-num-i-r"></span>
                        </div>
                    </div>
                </div>
                <div class='l-item'  data-id='11'>
                    <div class="l-item-title">
                        <div class="l-item-title-t">案件驳回数</div>
                    </div>
                    <div class="l-item-num">
                        <div class="l-item-num-t"></div>

                    </div>
                </div>
                <div class='l-item'  data-id='12'>
                    <div class="l-item-title">
                        <div class="l-item-title-t">驳回率</div>
                    </div>
                    <div class="l-item-num">
                        <div class="l-item-num-t"></div>
                        <div class="l-item-num-i">
                            <span>环比</span>
                            <span class="l-item-num-i-r"></span>
                        </div>
                    </div>
                </div>
                <div class='l-item'  data-id='13'>
                    <div class="l-item-title">
                        <div class="l-item-title-t">阳性案件数</div>
                    </div>
                    <div class="l-item-num">
                        <div class="l-item-num-t"></div>

                    </div>
                </div>
                <div class='l-item'  data-id='14'>
                    <div class="l-item-title">
                        <div class="l-item-title-t">阳性率</div>
                    </div>
                    <div class="l-item-num">
                        <div class="l-item-num-t"></div>
                        <div class="l-item-num-i">
                            <span>环比</span>
                            <span class="l-item-num-i-r"></span>
                        </div>
                    </div>
                </div>
                <div class='l-item'  data-id='15'>
                    <div class="l-item-title">
                        <div class="l-item-title-t">件均时效</div>
                    </div>
                    <div class="l-item-num">
                        <div class="l-item-num-t"></div>
                        <div class="l-item-num-i">
                            <span>环比</span>
                            <span class="l-item-num-i-r"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


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
    reimbursementReminder();
    //调查员费用报销提醒
    function reimbursementReminder() {
        var url = "${ctx}/survey/report/getData";
        var param = {"requestExpenseReimbursementInfo":"1"};
        $.ajax({
            url: url,
            method: 'post',
            data: param,
            success: function (res) {
                res = JSON.parse(res)
                if(res.isSuccess) {

                    var result = res.results;
                    var flag = false;
                    if(result!=null){
                        var reState = result.reState;
                        var payState = result.payState;
                        //待提交发票弹窗
                        if (reState!=null){
                            window.parent.$(".dalog-bg").show();
                            window.parent.$(".dalog__3").eq(1).show();
                            window.parent.$(".dalog__3").eq(1).find(".span_value").eq(0).text(result.reName);
                            window.parent.$(".dalog__3").eq(1).find(".span_value").eq(1).text(result.totalMoney+"元");
                            flag = true;
                        }
                        //待确认到账弹窗
                        if (payState !=null){
                            window.parent.$(".dalog-bg").show();
                            window.parent.$(".dalog__3").eq(2).show();
                            window.parent.$(".dalog__3").eq(2).find(".span_value").eq(0).text(result.realPayMoney);
                            window.parent.$(".dalog__3").eq(2).find(".span_value").eq(1).text(result.investigatorCount);
                            window.parent.$(".dalog__3").eq(2).find(".span_value").eq(2).text(result.remark);
                            window.parent.$(".dalog__3").eq(2).find(".span_value").eq(3).text(result.createBy);
                            window.parent.$("#index_pay_id").val(result.id);
                            flag = true;
                        }
                    }
                    // //机构弹窗
                    // if (result == null || !flag){
                    //     initBas();
                    // }
                }
            }
        })
    }


    var _height = $(document).height() * 0.9
    $('.i_header.bs').click(function(){
        console.log('bs')
    })
    var searchType = 'curMonth'
    vals = ['', '']



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
            d = PrefixInteger(d, 2)
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

    var startTime = '',
        endTime = ''
    var initVals = setDate('curMonth')
    layui.use(['form', 'laydate', 'xmSelect', 'layer'], function () {
        var form = layui.form,
            laydate = layui.laydate,
            xmSelect = layui.xmSelect,
            layer = layui.layer;

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
                updateItems()
            }
        })

        $('.l-items').on('click', '.l-item', function(){
            var _this = $(this)
            var _id = _this.attr('data-id'),
                startTime= $('#startTime').val(),
                endTime= $('#endTime').val(),
                currentUserId=$("#currentUserId").val();
            var height = $(document).outerHeight()*0.9;
            var width = $(document.body).outerWidth()*0.9;
            if (_id != 3){
                console.log(_id)
                openDialog({
                    frame:true,
                    title:"",
                    height:height,
                    width:width,
                    url:"${ctx}/survey/hzReport/popup?investigatorType=" + _id + "&menuCode=investigatorReport&startTime="+startTime+" 00:00:00&endTime="+endTime+ " 23:59:59&currentUserId=" +currentUserId+'&userId='+demo3.getValue('valueStr')
                });

            }
        })

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
                updateItems()
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
                updateItems()
            }
        });

        var franchiseesJson = $("#franchiseesJson").val();
        franchiseesJson = JSON.parse(franchiseesJson);

        var demo2 = xmSelect.render({
            el: '#surveyOrgId',
            theme: {
                color: '#3BA9FF'
            },
            radio: true,
            clickClose: true,
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
               if (arr.length){
                   $.ajax({
                       url: '${ctx}/baseSurvey/selectInfoByRelationId',
                       data: {
                           'surveyCode': 'investigator',
                           'btnCode':3000,
                           'surveyOrgIds':valArr.join(',')
                       },
                       success: function(res){
                           res = JSON.parse(res)
                           if (res.isSuccess){
                               var slist = res.results
                               _len = res.results.length
                               filterJson(demo3, slist,'userId', 'realName', false, false)
                               console.log(slist)
                               demo3.setValue([slist[0].userId])

                           }
                       }
                   })
               }


            },
            model: {
                label: {
                    type: 'templateSelf', //自定义与下面的对应
                    templateSelf: {
                        template(data, sels) {
                            var _html = ''
                            sels.filter(function (cur) {
                                _html +='<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                            })
                            return _html
                        }
                    },
                }
            },
            data: filterJson(demo2, franchiseesJson,'id', 'name', true, false)
        })


        var demo3 = xmSelect.render({
            el: '#surveyInvestigators',
            theme: {
                color: '#3BA9FF',
            },
            radio: true,
            clickClose: true,
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
                            var _html = ''
                            sels.filter(function (cur) {
                                _html +=   '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                            })
                            return _html
                        }
                    },
                }
            },
            data: [],
            on: function (data) {
                //arr:  当前多选已选中的数据
                var arr = data.arr;
                  if (arr.length){
                      var param = {
                          menuCode:'investigatorReport',
                          startTime:$('#startTime').val(),
                          endTime:$('#endTime').val(),
                          searchType: searchType,
                          userId: arr[0].value
                      }
                      getInfo(param)
                  }
            },
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

        var investigatorsJson = $("#investigatorsJson").val();
        investigatorsJson = JSON.parse(investigatorsJson);
        _len = investigatorsJson.length

        filterJson(demo3, investigatorsJson,'userId','realName',false, false)

        demo2.setValue([franchiseesJson[0].id])

        demo3.setValue([investigatorsJson[0].userId])

        var param = {
            menuCode:'investigatorReport',
            startTime:initVals[0],
            endTime:initVals[1],
            searchType: 'curMonth',
            userId: demo3.getValue('valueStr')
        }
        getInfo(param)

        function updateItems(){
            layer.load({
                shade: true
            })
            var param = {
                menuCode:'investigatorReport',
                startTime:$('#startTime').val(),
                endTime:$('#endTime').val(),
                searchType: searchType,
                userId: demo3.getValue('valueStr')
            }
            getInfo(param)

        }

        function getInfo(param) {
            $.ajax({
                url: '${ctx}/survey/hzReport/getDetail',
                method: 'post',
                data: param,
                success: function (res) {
                    res = JSON.parse(res)
                    if(res.isSuccess){
                        console.log(param, res.results)
                        $("#currentUserId").val(res.results.currentUserId);
                        setItems(res.results.list)
                    }
                    layer.closeAll('loading')
                }
            })
        }

        function setItems(params) {
            $('.l-items .l-item').each(function () {
                var _this = $(this)
                var id = _this.attr('data-id')
                switch (id) {
                    case '1':
                        setItem(id,params.investigatedNumber);
                        break;
                    case '2':
                        setItem(id,params.overdueNumber);
                        break;
                    case '3':
                        setItem(id,params.titleGrade);
                        break;
                    case '4':
                        setItem(id,params.acceptedNumber, params.acceptedNumberChainRatio);
                        break;
                    case '5':
                        setItem(id,params.platformReviewNumber, params.platformReviewNumberChainRatio);
                        break;
                    case '6':
                        setItem(id,params.insuranceCompanyNumber, params.insuranceCompanyNumberChainRatio);
                        break;
                    case '7':
                        setItem(id,params.platformTaskPoints, params.platformTaskPointsChainRatio);
                        break;
                    case '8':
                        setItem(id,params.missionPoints, params.missionPointsChainRatio);
                        break;
                    case '9':
                        setItem(id,params.overdueCasesNumber);
                        break;
                    case '10':
                        setItem(id,params.overdueRate, params.overdueRateChainRatio, true);
                        break;
                    case '11':
                        setItem(id,params.rejectedCasesNumber);
                        break;
                    case '12':
                        setItem(id,params.rejectionRate, params.rejectionRateChainRatio, true);
                        break;
                    case '13':
                        setItem(id,params.positiveCasesNumber);
                        break;
                    case '14':
                        setItem(id,params.positiveRate, params.positiveRateChainRatio, true);
                        break;
                    case '15':
                        setItem(id,params.averageAging, params.averageAgingRatio);
                        break;
                    case '16':
                        setItem(id,params.searchRate, params.searchRateRatio, true);
                        break;

                }

            })
            showTitle()
        }

        function setItem(id,num,rate,flag) {
            var l_item = $('.l-item[data-id='+ id+']')
            l_item.find('.l-item-num-t').text(num)
            if (flag){
                l_item.find('.l-item-num-t').text(num+'%')
            }
            if (id == 15){
                l_item.find('.l-item-num-t').text(num+'天')
            }
            if(rate < 0){
                l_item.find('.l-item-num-i-r').text(rate + '%').removeClass('color1 color2').addClass('color2')
            }else if(rate > 0){
                l_item.find('.l-item-num-i-r').text('+' +rate + '%').removeClass('color1 color2').addClass('color1')
            }else if(rate == 0){
                l_item.find('.l-item-num-i-r').text(rate + '%').removeClass('color1 color2')
            }else if(rate  === '' || rate === null){

            }

        }



        function showTitle(thsJ, child, iter) {
            var icon_about = '<span class="icon-about">'
            var ths = $('.l-items .l-item')

            var startTime = dateFormat($('#startTime').val(), 'yyyy年MM月dd日')
            var endTime = dateFormat($('#endTime').val(), 'yyyy年MM月dd日')

            var _title3 = '当前还在调查中的案件数量（实时数据，与上方选择的时间范围无关）'
            ths.eq(0).attr('title', _title3)

            var _title4 ='当前还在调查中且已经超过调查截止日期的案件数量（实时数据，与上方选择的时间范围无关）'
            ths.eq(1).attr('title', _title4)

            var _title5 = '根据调查员最近30天（与上方选择的时间范围无关）平台复审通过的总积分对应的调查员等级（调查学员=小于50分，调查新人=大于等于50分小于150分，有效调查员=大于等于150分小于250分，合格调查员=大于等于250分）'
            ths.eq(2).attr('title', _title5)

            var _title6 = '在'+startTime+ '至' + endTime +'之间机构分派给你的互助案件数量'
            ths.eq(3).attr('title', _title6)

            var _title7 ='在'+startTime+ '至' + endTime +'之间的你参与的互助案件中，平台复审通过的案件数量'
            ths.eq(4).attr('title', _title7)

            var _title8 = '在'+startTime+ '至' + endTime +'之间的你参与的互助案件中，保司终审通过的案件数量'
            ths.eq(5).attr('title', _title8)

            var _title9 = '在'+startTime+ '至' + endTime +'之间的互助案件中，你获得的平台复审通过的案件积分'
            ths.eq(6).attr('title', _title9)

            var _title10 = '在'+startTime+ '至' + endTime +'之间的互助案件中，你获得的保司终审通过的案件积分'
            ths.eq(7).attr('title', _title10)

            var _title17 = '平台复审通过时间在'+startTime+ '至' + endTime +'的互助案件中，获得屏拍或纸质材料的任务数量除以需要选择是否获得屏拍或纸质材料的任务的总数（包含的任务类型为：居住地医疗机构排查、走访就诊医疗机构、工作地医疗机构排查、社保排查、体检机构排查、商保排查 、药店排查、出险地医疗机构排查、户籍所在地医疗机构排查）'
            ths.eq(8).attr('title', _title17)

            var _title11 = '在'+startTime+ '至' + endTime +'之间的互助案件中，超过机构负责人给你要求的调查截止时间的案件数量（包括已经提交但超期的以及未提交已超期的）'
            ths.eq(9).attr('title', _title11)

            var _title12 = '在'+startTime+ '至' + endTime +'之间的超期案件数与你此期间接到的全部互助案件数量的比值'
            ths.eq(10).attr('title', _title12)

            var _title13 = '你在'+startTime+ '至' + endTime +'之间提交的互助案件中，曾经被机构负责人初审退回的案件数量（多次退回算一次）'
            ths.eq(11).attr('title', _title13)

            var _title14 = '在'+startTime+ '至' + endTime +'之间的驳回案件数与你此期间提交的全部互助案件数量的比值'
            ths.eq(12).attr('title', _title14)

            var _title15 = '你在'+startTime+ '至' + endTime +'之间复审通过的互助案件中，由你第一个发现阳性的案件数量'
            ths.eq(13).attr('title', _title15)

            var _title16 = '在'+startTime+ '至' + endTime +'之间的阳性案件数与你此期间复审通过的全部互助案件数量的比值'
            ths.eq(14).attr('title', _title16)

            var _title18 = '在'+startTime+ '至' + endTime +'提交的案件，调查员调查的总时效除以提交的案件数'
            ths.eq(15).attr('title', _title18)

        }

    })

    function openInvestigatorReport() {
        var url="${ctx}/survey/report/index?menuCode=survey";
        var title = "调查员报表(保司版)";
        parent.addTab(title,url,true);
    }









</script>

</body>

</html>