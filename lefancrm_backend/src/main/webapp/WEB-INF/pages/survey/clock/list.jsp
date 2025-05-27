<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <style>
        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }
        .searchs{
            padding-top: 9px;
            background-color: #d9edf7;
        }

        .main {
            width: 99%;
            margin: 0 auto;
        }

        .main .main-content {
            width: 100%;
            height: 600px;
            padding-top: 10px;
            display: flex;
        }

        .main .main-content .left {
            width: 70%;
            height: 100%;

        }

        .main .main-content .right {
            margin-left: 1%;
            width: 29%;
            height: 100%;

        }


        .marker {
            color: #f0645a;
            font-size: 10px;
            font-weight: bolder;
        }
        .marker2{
            color: #F76D00!important;
        }
        .marker2 .markerIcon{
            border: 2px solid #F76D00!important;
            color: #F76D00!important;
        }
        .markerIcon {
            display: inline-block;
            width: 16px;
            height: 16px;
            line-height: 16px;
            border-radius: 16px;
            border: 2px solid #f44336;
            color: #f44336;
            background-color: #fff;
            margin-right: 8px;
            font-size: 12px;
            position: absolute;
            left: 10px;
            top: 15px;
        }
        .markerTime{
        }

        .calendar {
            width: 99%;
            margin: 0 auto;
            border-radius: 6px;
            box-shadow: 0 0 8px 4px rgba(222, 222, 222, 1);
        }

        .calendar .calendar-top {
            padding: 2px 0;
            width: 100%;
            height: 40px;
            line-height: 40px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .calendar .calendar-top .previous {
            width: 60px;
            height: 20px;
            background: url('data:image/png;base64,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');
            background-position: center;
            background-repeat: no-repeat;
            background-size: contain;
        }

        .calendar .calendar-top .previous:hover {
            background: url('data:image/png;base64,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');
            background-position: center;
            background-repeat: no-repeat;
            background-size: contain;
        }

        .calendar .calendar-top .next:hover {
            background: url('data:image/png;base64,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');
            background-position: center;
            background-repeat: no-repeat;
            background-size: contain;
        }

        .calendar .calendar-top .next {
            width: 60px;
            height: 20px;
            background: url('data:image/png;base64,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');
            background-position: center;
            background-repeat: no-repeat;
            background-size: contain;
        }

        .calendar .calendar-top .now {
            width: 50%;
            text-align: center;
            font-weight: bold;
        }

        .calendar .calendar-bottom {
            padding: 2px 0;
            width: 100%;
            height: 40px;
            line-height: 40px;
            text-align: center;
            color: #3BA9FF;
            cursor: pointer;
        }

        .calendar-content {
            width: 100%;
        }

        .calendar-content .calendar-table {
            width: 100%;
            border-bottom: 1px solid rgba(234, 234, 234, 1);
            border-top: 1px solid rgba(234, 234, 234, 1);
        }

        .calendar-content .calendar-table .calendar-thead {
            width: 100%;
            height: 30px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 9px 0;
        }

        .calendar-content .calendar-table .calendar-thead .calendar-th {
            width: 14%;
            text-align: center;
            color: #000;
        }

        .calendar-content .calendar-table .calendar-tbody {
            width: 100%;
        }

        .calendar-content .calendar-table .calendar-tbody .calendar-tr {
            width: 100%;
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            justify-content: start;

        }

        .calendar-content .calendar-table .calendar-tbody .calendar-td {
            width: 14%;
            padding: 9px 0;
            text-align: center;
            color: #000;
            cursor: pointer;
            position: relative;
        }

        .calendar-content .calendar-table .calendar-tbody .calendar-td.tint {
            color: #c1c1c1;
        }

        .calendar-content .calendar-table .calendar-tbody .calendar-td:hover {
            color: #3BA9FF;
        }

        .calendar-content .calendar-table .calendar-tbody .calendar-td.selected {
            color: #fff!important;
            background-color: #3BA9FF!important;
            border: none!important;
        }
        .calendar-content .calendar-table .calendar-tbody .calendar-td.red {
            color: #333;
            background-color: rgba(255, 0, 0, 0.09);
            /*border: 1px solid #EE0707;*/
        }

        .calendar-content .calendar-table .calendar-tbody .calendar-td .circle-num {
            display: inline-block;
            width: 18px;
            height: 18px;
            line-height: 18px;
            border-radius: 18px;
            color: #fff;
            background-color: #FF0000;
            font-size: 10px;
            position: absolute;
            top: 3px;
            right: 7px;
        }

        .calendar-content .calendar-table .calendar-tbody .calendar-td .circle-num.color2 {
            background-color: #139B22;
        }

        .calendar-content .calendar-table .calendar-tbody .calendar-td .circle-num.color3 {
            background-color: #F76D00;
        }

        .list {
            width: 99%;
            overflow: auto;
            margin: 0 auto;
            margin-top: 10px;
            border-radius: 6px;
            box-shadow: 0 0 8px 4px rgba(222, 222, 222, 1);
        }
        .list.tipList{
            width: 97%;
            /*height: 100%;*/
            overflow: auto;
            margin: 0 auto;
            margin-top: 10px;
            border-radius: none;
            box-shadow: none;
        }
        .list.signList{
            width: 99%;
            height: 93%;
            overflow: auto;
            margin: 0 auto;
            margin-top: 30px;
            border-radius: none;
            box-shadow: none;
        }

        .list.tipList .li{
            padding: 0 2%!important;
            border-bottom: none!important;

        }
        .list .li {
            width: 96%;
            padding: 10px 2%;
            border-bottom: 6px solid rgba(223, 222, 222, 0.16);
        }

        .list .li.red, .list.tipList .li .cell.red{
            background-color: rgba(255, 0, 0, 0.09);
        }
        .list.tipList .li .cell{
            padding: 6px 0!important;
        }
        .list .li .cell {
            width: 100%;
            padding: 10px 0;
            display: flex;
            align-items: center;
            justify-content: space-between;
            border-bottom: 1px solid #eee;
        }

        .list .li .cell:last-of-type {
            border: none;
        }

        .list .li .cell .btn-read{
            width: 99%;
            height: 30px;
            line-height: 30px;
            padding: 4px 0;
            color: #fff;
            background-color: rgba(19, 155, 34, 0.42);
            border: 1px solid #bbb;
            text-align: center;
        }
        .list .li .cell .cell-label {
            width: 30%;
            line-height: 24px;
            font-weight: bold;
            text-align: left;
        }

        .list .li .cell .cell-value {
            max-width: 70%;
            width: 70%;
            line-height: 24px;
            display: flex;
            align-items: center;
            justify-content: flex-end;
        }

        .list .li .cell .cell-value.price {
            color: #3BA9FF;
            cursor: pointer;
        }

        .list .li .cell .cell-value .loc {
            display: inline-block;
            width: 20px;
            height: 20px;
            background: url('data:image/png;base64,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');
            background-position: center;
            background-repeat: no-repeat;
            background-size: contain;
        }
        .poi-none{
            pointer-events: none!important;
        }
        .lf-none{
            margin-top: 50px;
            width: 100%;
            color:#666;
            text-align: center;
        }
        .xm-body{
            z-index: 99999!important;
        }

        @media screen and (max-width:1540px){
            .main .main-content .left {
                width: 65%;
                height: 100%;

            }

            .main .main-content .right {
                margin-left: 1%;
                width: 34%;
                height: 100%;

            }

        }
        @media screen and (max-width:1280px){
            .main .main-content .left {
                width: 60%;
                height: 100%;

            }

            .main .main-content .right {
                margin-left: 1%;
                width: 39%;
                height: 100%;

            }

        }
        @media screen and (max-width:1220px){
            .main .main-content .left {
                width: 57%;
                height: 100%;

            }

            .main .main-content .right {
                margin-left: 1%;
                width: 42%;
                height: 100%;

            }

        }
        .viewSign{
            cursor: pointer;
            color: #3ba9ff;
        }


        .sign-info {
            padding-top: 20px;
            width: 100%;
        }

        .sign-info .info-cell {
            width: 92%;
            padding: 10px 0;
            margin: 0 auto;
            border-bottom: 1px solid #bbb;
            display: flex;
            flex-wrap: wrap;
            align-items: center;
        }

        .sign-info .info-cell .info-cell-label {
            width: 50%;
            line-height: 26px;
            font-weight: bold;
        }
        .sign-info .info-cell .info-cell-label text{
            font-weight: normal;
            font-size: 12px;
        }
        .sign-info .info-cell .info-cell-value {
            width: 50%;
            text-align: right;
            display: flex;
            align-items: center;
            justify-content: flex-end;
        }
        .wxui-ddr-btn{
            width: 100%;
            padding: 10px 0 15px 0;
            background-color: #fff;
            text-align: center;
        }
        .wxui-ddr-btn button{
            width: 92%;
            margin: 0 auto;
            height:42px;
            line-height: 42px;
            background-color: #3BA9FF;
            color: #fff;
            font-size: 14px;
        }
        .wxui-ddr-btn button.btn3{
            background-color: #8ad89d;
            color: #fff;
            border: 1px solid #bbb;
        }
        .clockCases{
            width: 100%;
        }
        .clockCase{
            width: 100%;
            display: flex;
            justify-content: flex-end;
        }
        .clockCase .clockCase-person{
            padding-right: 16px;
        }
        .ckprice{
            color: #666;
        }
        .editValue{
            width: 60px;
            height: 20px;
            line-height: 20px;
            border:1px solid  #eee;
            text-align: center;
        }
        .editText{
            width: 90%;
            height: 100px;
            line-height: 20px;
            text-align: right;
        }
    </style>
</head>
<body>
<input type="hidden" value='${userId}' id="userOneId">
<input type="hidden" value='${orgId}' id="orgId">
<input type="hidden" value='${dateTime}' id="dateTime">
<input type="hidden" value='${params.surveyFranchiseeDtoJson}' id="surveyFranchiseeDtoJson">
<div class="main">
    <div class="header">
    <c:if test="${userId == '' || userId == null}">
            <div class="layui-form searchs" lay-filter="search">
                <div class="layui-form-item ">
                    <div class="layui-inline">
                        <label class="layui-form-label">机构</label>
                        <div class="layui-input-inline">
                            <div id="orgIds" class="selectMul"></div>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">调查员</label>
                        <div class="layui-input-inline">
                            <div id="userIds" class="selectMul"></div>
                        </div>
                    </div>
                </div>
            </div>
    </c:if>
    </div>
    <div class="main-content">
        <div class="left map" id="container"></div>
        <div class="right">
            <div class="calendar">
                <div class="calendar-top">
                    <div class="change previous"></div>
                    <div class="now"></div>
                    <div class="change next"></div>
                </div>
                <div class="calendar-content">
                    <div class="calendar-table">
                        <div class="calendar-thead">
                            <div class="calendar-th">日</div>
                            <div class="calendar-th">一</div>
                            <div class="calendar-th">二</div>
                            <div class="calendar-th">三</div>
                            <div class="calendar-th">四</div>
                            <div class="calendar-th">五</div>
                            <div class="calendar-th">六</div>
                        </div>
                        <div class="calendar-tbody">
                            <div class="calendar-tr">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="calendar-bottom">
                    今天
                </div>
            </div>
            <div class="list">
            </div>
        </div>
    </div>
</div>
<script type="text/html" id="table-content-child">
    <div class="sign-info">

    </div>
</script>
<script src="${ctx}/js/jquery-3.4.1.js" charset="utf-8"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script src="https://map.qq.com/api/gljs?v=1.exp&key=VOZBZ-FPYKP-R33D4-VHJCM-FTNOE-2LBEB"></script>
<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })
    var params = {}
    var openIndex = '',clickIndex = '';
    var orgId = $("#orgId").val();
    var userOneId = $('#userOneId').val()
    var _height = $(document).height() - $('.header').height() - 60
    $('.main-content').height(_height)
    layui.use(['layer','form','xmSelect'], function () {

        var form = layui.form,
            xmSelect = layui.xmSelect;
        layer = layui.layer

        if (!$('#userOneId').val()){
            demo1 = xmSelect.render({
                el: '#orgIds',
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
                size: 'small',
                model: {
                    label: {
                        type: 'templateSelf', //自定义与下面的对应
                        templateSelf: {
                            template(data, sels){
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +='<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    }
                },
                on: function(data){
                    //arr:  当前多选已选中的数据
                    var arr = data.arr;
                    if (arr.length){
                        orgId = arr[0].value;
                        $.ajax({
                            url: '${ctx}/survey/userClock/details',
                            type: 'post',
                            data: {
                                menuType:'dropDownDetails',
                                btnCode:'Investigator',
                                orgId: arr[0].value
                            },
                            success: function (res) {
                                res = JSON.parse(res)
                                if (res.isSuccess){
                                    filterJson(demo2, res.results.surveyInvestigatorList,'userId','realName',false, false)
                                }
                            }

                        })
                    }else {
                        demo2.update({
                            data: []
                        })
                    }
                },
                data: []
            })

            demo2 = xmSelect.render({
                el: '#userIds',
                radio: true,
                clickClose: true,
                theme: {
                    color: '#3BA9FF'
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
                            template(data, sels){
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +='<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    }
                },
                on: function(data){
                    //arr:  当前多选已选中的数据
                    var arr = data.arr;
                    if (arr.length){
                        userOneId = arr[0].value
                        getInfo(arr[0].value)
                    }else {

                    }
                },
                data: []
            })
            var surveyFranchiseeDtoJson = $("#surveyFranchiseeDtoJson").val();
            surveyFranchiseeDtoJson = JSON.parse(surveyFranchiseeDtoJson);
            filterJson(demo1, surveyFranchiseeDtoJson,'id','name',false, false)
            demo1.setValue([surveyFranchiseeDtoJson[0].id])
            $.ajax({
                url: '${ctx}/survey/userClock/details',
                type: 'post',
                data: {
                    menuType:'dropDownDetails',
                    btnCode:'Investigator',
                    orgId: surveyFranchiseeDtoJson[0].id
                },
                success: function (res) {
                    res = JSON.parse(res)
                    if (res.isSuccess){
                        if (res.results.surveyInvestigatorList.length){
                            filterJson(demo2, res.results.surveyInvestigatorList,'userId','realName',false, false)
                            demo2.setValue([res.results.surveyInvestigatorList[0].userId])
                            userOneId = res.results.surveyInvestigatorList[0].userId
                            getInfo(res.results.surveyInvestigatorList[0].userId)
                        }else {
                            var date2 = dateFormat(new Date(), 'yyyy-MM-dd')
                            initCalendar(date2,{
                                surveyUserClockDtoList: [],
                                previousList: [],
                                nextList: []
                            },true)
                            initMap([])
                        }


                    }
                }
            })
            orgId =  demo1.getValue('valueStr');
        }else {
            getInfo($('#userOneId').val(),$('#dateTime').val())
        }

        function getInfo(userId,initDate) {
            var date = dateFormat(initDate || new Date(), 'yyyy-MM')
            var date2 = dateFormat(initDate || new Date(), 'yyyy-MM-dd')
            $.ajax({
                url: '${ctx}/survey/userClock/details',
                type: 'post',
                data: {
                    menuType:'dropDownDetails',
                    btnCode:'punchInDate',
                    userId: userId,
                    dateTime: date,
                    orgId:orgId
                },
                success: function (res) {
                    res = JSON.parse(res)
                    if (res.isSuccess){
                        initCalendar(date2,res.results)
                    }
                    $('.ll-submit').removeAttr('disabled')
                }
            })
            params = {
                menuType:'clockDetails',
                btnCode:'clockInDetails',
                userId: userId,
                dateTime: date2,
                orgId:orgId
            }
            getClockInfos(params)

        }



    })
    var clockInfos = ''

    $('body').on('click','.viewSign',function () {
        var id = $(this).attr('data-id')
        var  lineIndex = $(this).attr('data-index')
        var e = window.event || arguments.callee.caller.arguments[0];
        e.stopPropagation()
        var signList = clockInfos[lineIndex].signList
        signList.map(function (cur) {
            if (cur.id == id){
                signIndex = layer.open({
                    type: 1,
                    title: '签报信息',
                    area: [ '500px', '600px'],
                    content: $('#table-content-child').html(),
                    success: function (res) {
                        var _html = '  <div class="info-cell">\n' +
                            '            <div class="info-cell-label">被调查人</div>\n' +
                            '            <div class="info-cell-value">\n' +
                            '                '+cur.value.surveyPersons+'\n' +
                            '            </div>\n' +
                            '        </div>\n' +
                            '        <div class="info-cell">\n' +
                            '            <div class="info-cell-label">费用类型</div>\n' +
                            '            <div class="info-cell-value">\n' +
                            '                '+cur.value.costTypeName+'\n' +
                            '            </div>\n' +
                            '        </div>\n'
                        if (id == 7){
                            _html += '    <div class="info-cell">\n' +
                                '        <div class="info-cell-label" style="width: 28%">起始点</div>\n' +
                                '        <div class="info-cell-value" style="width: 72%">\n' +
                                '            '+cur.value.startAddress+'\n' +
                                '        </div>\n' +
                                '    </div>\n' +
                                '    <div class="info-cell">\n' +
                                '        <div class="info-cell-label" style="width: 28%">终点</div>\n' +
                                '        <div class="info-cell-value" style="width: 72%">\n' +
                                '            '+cur.value.endAddress+'\n' +
                                '        </div>\n' +
                                '    </div>\n' +
                                '    <div class="info-cell">\n' +
                                '        <div class="info-cell-label">往返公里数 <text>（参考）</text></div>\n' +
                                '        <div class="info-cell-value">\n' +
                                '            '+cur.value.kmNum+' 公里\n' +
                                '        </div>\n' +
                                '    </div>\n' +
                                '    <div class="info-cell">\n' +
                                '        <div class="info-cell-label">自驾费用<text>（参考）（不含过路费）</text></div>\n' +
                                '        <div class="info-cell-value">\n' +
                                '            '+cur.value.kmNumMoney+' 元\n' +
                                '        </div>\n' +
                                '    </div>'
                        }else {
                            _html +=   '        <div class="info-cell">\n' +
                                '            <div class="info-cell-label">签报金额</div>\n' +
                                '            <div class="info-cell-value">\n' +
                                '                '+cur.value.applyMoney+' 元\n' +
                                '            </div>\n' +
                                '        </div>\n'
                        }
                        var produceDate = dateFormat(cur.value.produceDate, 'yyyy-MM-dd')
                        var signDesc = cur.value.costDesc ? cur.value.costDesc : '无'
                        var reviewerUserName=cur.value.reviewerUserName ? cur.value.reviewerUserName : ''
                        var TwoReviewerUserName = cur.value.twoReviewerUserName ? ' , '+cur.value.twoReviewerUserName : ''
                        _html +=       '        <div class="info-cell">\n' +
                            '            <div class="info-cell-label" style="width: 30%">费用产生的日期</div>\n' +
                            '            <div class="info-cell-value" style="width: 70%">\n' +
                            '                '+produceDate+'\n' +
                            '            </div>\n' +
                            '        </div>\n' +
                            '        <div class="info-cell" style="align-items: flex-start;">\n' +
                            '            <div class="info-cell-label" style="width: 25%">备注</div>\n' +
                            '            <div class="info-cell-value" style="width: 75%">\n' +
                            '                '+signDesc+'\n' +
                            '            </div>\n' +
                            '        </div>'+
                            ' <div class="wxui-ddr-btn">\n' +
                            '            <button class="btn3">已审核通过（审核人：'+reviewerUserName +TwoReviewerUserName+'）\n' +
                            '            </button>\n' +
                            '        </div>'

                        $('.sign-info').html(_html)
                    }
                })
            }
        })

    })

    function getClockInfos(param){
        loadIndex = layer.load()
        $.ajax({
            url: '${ctx}/survey/userClock/details',
            type: 'post',
            data:param,
            success: function (res) {
                layer.close(loadIndex);
                res = JSON.parse(res)
                if (res.isSuccess){
                    var _html = ''
                    var markers = []
                    if (!res.results.length){
                        _html = '<div class="lf-none">暂无数据</div>'
                    } else{
                        clockInfos = res.results
                        res.results.map(function(cur,i){
                            var clockTime = dateFormat(cur.clockTime, 'yyyy-MM-dd HH:mm:ss')
                            var reTotalMoney = cur.reTotalMoney ? cur.reTotalMoney : 0

                            if (cur.surveyPerson){

                                var desc_html = ''
                                if (cur.addressDesc){
                                    desc_html =  '                        <div class="cell">\n' +
                                        '                            <div class="cell-label">地点备注</div>\n' +
                                        '                            <div class="cell-value">\n' +
                                        '                                '+cur.addressDesc+'\n' +
                                        '                            </div>\n' +
                                        '                        </div>\n'
                                }
                                var _redClass= ''
                                if (cur.red){
                                    _redClass ='red'
                                }
                                _html += '<div class="li '+_redClass+'">\n' +
                                    '                        <div class="cell">\n' +
                                    '                            <div class="cell-label">第'+convertToChinaNum(i+1)+'次打卡</div>\n' +
                                    '                            <div class="cell-value">'+clockTime+'</div>\n' +
                                    '                        </div>\n' +
                                    '                        <div class="cell">\n' +
                                    '                            <div class="cell-label">地点</div>\n' +
                                    '                            <div class="cell-value">\n' +
                                    '                                <div class="loc"></div>'+cur.address+'\n' +
                                    '                            </div>\n' +
                                    '                        </div>\n' +
                                    desc_html+
                                    '                        <div class="cell">\n' +
                                    '                            <div class="cell-label">关联被调查人</div>\n' +
                                    '                            <div class="cell-value">'+cur.surveyPerson+'</div>\n' +
                                    '                        </div>\n' +
                                    '                        <div class="cell">\n' +
                                    '                            <div class="cell-label">费用报销</div>\n' +
                                    '                            <div class="cell-value price" data-id="'+i+'">'+reTotalMoney+'元</div>\n' +
                                    '                        </div>\n' +
                                    '                    </div>'


                                markers.push({
                                    id: cur.id,
                                    time: dateFormat(cur.clockTime, 'HH:mm'),
                                    name: cur.address,
                                    price: cur.reTotalMoney || '0',
                                    lat: cur.clockLbsX,
                                    lng: cur.clockLbsY,
                                    check: false
                                })

                                var signList= [
                                    {
                                        id:1,
                                        index: i,
                                        value: '',
                                    },{
                                        id:2,
                                        index: i,
                                        value: '',
                                    },{
                                        id:3,
                                        index: i,
                                        value: '',
                                    },{
                                        id:4,
                                        index: i,
                                        value: '',
                                    },{
                                        id:5,
                                        index: i,
                                        value: '',
                                    },{
                                        id:6,
                                        index: i,
                                        value: '',
                                    },{
                                        id:7,
                                        index: i,
                                        value: '',
                                    },{
                                        id:8,
                                        index: i,
                                        value: '',
                                    }]
                                if (cur.surveyCostApplyDto.length){
                                    cur.surveyCostApplyDto.map(function (item) {
                                        signList[Number(item.costType) -1].value = item
                                    })
                                }
                                clockInfos[i].signList = signList
                            }else {
                                _html += '<div class="li">\n' +
                                    '                        <div class="cell">\n' +
                                    '                            <div class="cell-label">考勤打卡</div>\n' +
                                    '                            <div class="cell-value">'+clockTime+'</div>\n' +
                                    '                        </div>\n' +
                                    '                        <div class="cell">\n' +
                                    '                            <div class="cell-label">地点</div>\n' +
                                    '                            <div class="cell-value">\n' +
                                    '                                <div class="loc"></div>'+cur.address+'\n' +
                                    '                            </div>\n' +
                                    '                        </div>\n' +
                                    '                    </div>'

                                markers.push({
                                    id: cur.id,
                                    time: dateFormat(cur.clockTime, 'HH:mm'),
                                    name: cur.address,
                                    price: cur.reTotalMoney || '0',
                                    lat: cur.clockLbsX,
                                    lng: cur.clockLbsY,
                                    check: true
                                })
                            }

                        })
                    }
                     openIndex = '';
                        clickIndex = '';
                    $('.main-content .list').html(_html)
                    $('.main-content .list .price').mouseover(function () {
                        var layer = layui.layer;
                        var clockId = $(this).attr('data-id')
                        var infos = clockInfos[clockId].surveyClockReInfo
                        var signList = clockInfos[clockId].signList
                        var desc = infos.clockDesc ? infos.clockDesc : '无'
                        var signHtml1 = signList[0].value ? '<span class="viewSign" data-id="'+signList[0].id+'" data-index="'+signList[0].index+'">（查看签报）</span>':''
                        var signHtml2 = signList[1].value ? '<span class="viewSign" data-id="'+signList[1].id+'" data-index="'+signList[1].index+'">（查看签报）</span>':''
                        var signHtml3 = signList[2].value ? '<span class="viewSign" data-id="'+signList[2].id+'" data-index="'+signList[2].index+'">（查看签报）</span>':''
                        var signHtml4 = signList[3].value ? '<span class="viewSign" data-id="'+signList[3].id+'" data-index="'+signList[3].index+'">（查看签报）</span>':''
                        var signHtml5 = signList[4].value ? '<span class="viewSign" data-id="'+signList[4].id+'" data-index="'+signList[4].index+'">（查看签报）</span>':''
                        var signHtml6 = signList[5].value ? '<span class="viewSign" data-id="'+signList[5].id+'" data-index="'+signList[5].index+'">（查看签报）</span>':''
                        var signHtml7 = signList[6].value ? '<span class="viewSign" data-id="'+signList[6].id+'" data-index="'+signList[6].index+'">（查看签报）</span>':''
                        var signHtml8 = signList[7].value ? '<span class="viewSign" data-id="'+signList[7].id+'" data-index="'+signList[7].index+'">（查看签报）</span>':''

                        var caseList = clockInfos[clockId].clockCaseList
                        var _html1 = '<div class="clockCases">',_html2='<div class="clockCases">',_html3='<div class="clockCases">',_html4='<div class="clockCases">'
                        caseList.map(function(cur){
                            _html1 += '<div class="clockCase"><div class="clockCase-person">'+cur.surveyPerson+'</div><div>'+cur.medicalHistoryMoney+'  元</div></div>'
                            _html2 += '<div class="clockCase"><div class="clockCase-person">'+cur.surveyPerson+'</div><div>'+cur.troubleshootingMoney+'  元</div></div>'
                            _html3 += '<div class="clockCase"><div class="clockCase-person">'+cur.surveyPerson+'</div><div>'+cur.opcTroubleshootingMoney+'  元</div></div>'
                            _html4 += '<div class="clockCase"><div class="clockCase-person">'+cur.surveyPerson+'</div><div>'+cur.printingMoney+'  元</div></div>'

                        })
                        _html1 += '</div>'
                        _html2 += '</div>'
                        _html3 += '</div>'
                        _html4 += '</div>'


                        var _redClass= ''
                        if (infos.referenceMoney && Number(infos.cityinDrivingMoney) > Number(infos.referenceMoney) *1.5 ){
                            _redClass = 'red'
                        }
                        var caseHtml1 = infos.referenceMoney ? '<div class="ckprice">（参考价'+infos.referenceMoney+'元）</div>' : ''

                        _html = '<div class="list tipList"><div class="li">' +
                            '<div class="cell '+_redClass+'"><div class="cell-label">市内交通费</div><div class="cell-value">' +
                            signHtml1+caseHtml1+

                            infos.cityinDrivingMoney + ' 元</div></div>' +
                            '<div class="cell"><div class="cell-label">病史费（含复印费）</div><div class="cell-value">' +

                            _html1 + '</div></div>' +
                            '<div class="cell"><div class="cell-label">住院排查费用</div><div class="cell-value">' +

                            _html2 + '</div></div>' +
                            '<div class="cell"><div class="cell-label">门诊排查费用</div><div class="cell-value">' +

                            _html3 + '</div></div>' +
                            '<div class="cell"><div class="cell-label">体检报告打印费</div><div class="cell-value">' +
                            _html4 + ' </div></div>' +
                            '<div class="cell"><div class="cell-label">住宿费</div><div class="cell-value">' +
                            signHtml5+

                            infos.accommodatioMoney + ' 元</div></div>' +
                            '<div class="cell"><div class="cell-label">跨地市交通费（汽车、火车、飞机）</div><div class="cell-value">' +
                            signHtml6+

                            infos.crossDrivingMoney + ' 元</div></div>' +
                            '<div class="cell" style="border: none"><div class="cell-label" >跨地市交通费（自驾）</div><div class="cell-value">' +
                            signHtml7+

                            infos.selfDrivingMoney + ' 元</div></div>' +
                            '<div class="cell"><div class="cell-label" ></div><div class="cell-value">' +
                            infos.tollMoney + ' 元 (过路费)</div></div>' +
                            '<div class="cell"><div class="cell-label">其他费用</div><div class="cell-value">' +
                            signHtml8+

                            infos.otherMoney + ' 元</div></div>' +
                            '<div class="cell"><div class="cell-label">备注</div><div class="cell-value">' +
                            desc+ '</div></div>' +
                            '</div></div>'
                        openIndex = layer.open({
                            type: 1,
                            title: '费用报销',
                            closeBtn: 0,
                            shade: 0,
                            offset: ['10px', '800px'],
                            area: ['500px', '800px'],
                            content: _html
                        });
                    })
                    $('.main-content .list .price').mouseout(function () {
                        layer.close(openIndex)
                    })
                    $('.main-content .list .price').click(function () {
                        var layer = layui.layer;

                        if(clickIndex){
                            layer.close(clickIndex)
                        }
                        var clockId = $(this).attr('data-id')
                        var infos = clockInfos[clockId].surveyClockReInfo
                        var signList = clockInfos[clockId].signList
                        var desc = infos.clockDesc ? infos.clockDesc : '无'
                        var signHtml1 = signList[0].value ? '<span class="viewSign" data-id="'+signList[0].id+'" data-index="'+signList[0].index+'">（查看签报）</span>':''
                        var signHtml2 = signList[1].value ? '<span class="viewSign" data-id="'+signList[1].id+'" data-index="'+signList[1].index+'">（查看签报）</span>':''
                        var signHtml3 = signList[2].value ? '<span class="viewSign" data-id="'+signList[2].id+'" data-index="'+signList[2].index+'">（查看签报）</span>':''
                        var signHtml4 = signList[3].value ? '<span class="viewSign" data-id="'+signList[3].id+'" data-index="'+signList[3].index+'">（查看签报）</span>':''
                        var signHtml5 = signList[4].value ? '<span class="viewSign" data-id="'+signList[4].id+'" data-index="'+signList[4].index+'">（查看签报）</span>':''
                        var signHtml6 = signList[5].value ? '<span class="viewSign" data-id="'+signList[5].id+'" data-index="'+signList[5].index+'">（查看签报）</span>':''
                        var signHtml7 = signList[6].value ? '<span class="viewSign" data-id="'+signList[6].id+'" data-index="'+signList[6].index+'">（查看签报）</span>':''
                        var signHtml8 = signList[7].value ? '<span class="viewSign" data-id="'+signList[7].id+'" data-index="'+signList[7].index+'">（查看签报）</span>':''

                        var caseList = clockInfos[clockId].clockCaseList
                        var _html1 = '<div class="clockCases">',_html2='<div class="clockCases">',_html3='<div class="clockCases">',_html4='<div class="clockCases">'
                       if (!clockInfos[clockId].edit){
                           caseList.map(function(cur){
                               _html1 += '<div class="clockCase"><div class="clockCase-person">'+cur.surveyPerson+'</div><div>'+cur.medicalHistoryMoney+'  元</div></div>'
                               _html2 += '<div class="clockCase"><div class="clockCase-person">'+cur.surveyPerson+'</div><div>'+cur.troubleshootingMoney+'  元</div></div>'
                               _html3 += '<div class="clockCase"><div class="clockCase-person">'+cur.surveyPerson+'</div><div>'+cur.opcTroubleshootingMoney+'  元</div></div>'
                               _html4 += '<div class="clockCase"><div class="clockCase-person">'+cur.surveyPerson+'</div><div>'+cur.printingMoney+'  元</div></div>'

                           })
                       }else {
                           caseList.map(function(cur){
                               _html1 += '<div class="clockCase"><div class="clockCase-person">'+cur.surveyPerson+'</div><div>'+
                                   '<input value="'+cur.medicalHistoryMoney+'" class="editValue" data-name="medicalHistoryMoney" data-id="'+cur.id+'">'+
                                   ' 元</div></div>'
                               _html2 += '<div class="clockCase"><div class="clockCase-person">'+cur.surveyPerson+'</div><div>'+
                                   '<input value="'+cur.troubleshootingMoney+'" class="editValue" data-name="troubleshootingMoney" data-id="'+cur.id+'">'+
                                   ' 元</div></div>'
                               _html3 += '<div class="clockCase"><div class="clockCase-person">'+cur.surveyPerson+'</div><div>'+
                                   '<input value="'+cur.opcTroubleshootingMoney+'" class="editValue" data-name="opcTroubleshootingMoney" data-id="'+cur.id+'">'+
                                   ' 元</div></div>'
                               _html4 += '<div class="clockCase"><div class="clockCase-person">'+cur.surveyPerson+'</div><div>'+
                                   '<input value="'+cur.printingMoney+'" class="editValue" data-name="printingMoney" data-id="'+cur.id+'">'+
                                   ' 元</div></div>'
                           })
                       }
                        _html1 += '</div>'
                        _html2 += '</div>'
                        _html3 += '</div>'
                        _html4 += '</div>'


                        var caseHtml1 = infos.referenceMoney ? '<div class="ckprice">（参考价'+infos.referenceMoney+'元）</div>' : ''
                        var _redClass= ''
                        if (infos.referenceMoney && Number(infos.cityinDrivingMoney) > Number(infos.referenceMoney) *1.5 ){
                            _redClass = 'red'
                        }

                        if (!clockInfos[clockId].edit){
                            _html = '<div class="list tipList"><div class="li">' +
                                '<div class="cell '+_redClass+'"><div class="cell-label">市内交通费</div><div class="cell-value">' +
                                signHtml1+caseHtml1+

                                infos.cityinDrivingMoney + ' 元</div></div>' +
                                '<div class="cell"><div class="cell-label">病史费（含复印费）</div><div class="cell-value">' +

                                _html1 + '</div></div>' +
                                '<div class="cell"><div class="cell-label">住院排查费用</div><div class="cell-value">' +

                                _html2 + '</div></div>' +
                                '<div class="cell"><div class="cell-label">门诊排查费用</div><div class="cell-value">' +

                                _html3 + '</div></div>' +
                                '<div class="cell"><div class="cell-label">体检报告打印费</div><div class="cell-value">' +
                                _html4 + ' </div></div>' +
                                '<div class="cell"><div class="cell-label">住宿费</div><div class="cell-value">' +
                                signHtml5+

                                infos.accommodatioMoney + ' 元</div></div>' +
                                '<div class="cell"><div class="cell-label">跨地市交通费（汽车、火车、飞机）</div><div class="cell-value">' +
                                signHtml6+

                                infos.crossDrivingMoney + ' 元</div></div>' +
                                '<div class="cell" style="border: none"><div class="cell-label" >跨地市交通费（自驾）</div><div class="cell-value">' +
                                signHtml7+

                                infos.selfDrivingMoney + ' 元</div></div>' +
                                '<div class="cell"><div class="cell-label" ></div><div class="cell-value">' +
                                infos.tollMoney + ' 元 (过路费)</div></div>' +
                                '<div class="cell"><div class="cell-label">其他费用</div><div class="cell-value">' +
                                signHtml8+

                                infos.otherMoney + ' 元</div></div>' +
                                '<div class="cell"><div class="cell-label">备注</div><div class="cell-value">' +
                                desc+ '</div></div>' +
                                '</div></div>'
                        }else {
                            _html = '<div class="list tipList"><div class="li">' +
                                '<div class="cell '+_redClass+'"><div class="cell-label">市内交通费</div><div class="cell-value">' +
                                signHtml1+caseHtml1+
                                '<input value="'+infos.cityinDrivingMoney+'" class="editValue" data-name="cityinDrivingMoney">'+ ' 元</div></div>' +
                                '<div class="cell"><div class="cell-label">病史费（含复印费）</div><div class="cell-value">' +

                                _html1 + '</div></div>' +
                                '<div class="cell"><div class="cell-label">住院排查费用</div><div class="cell-value">' +

                                _html2 + '</div></div>' +
                                '<div class="cell"><div class="cell-label">门诊排查费用</div><div class="cell-value">' +

                                _html3 + '</div></div>' +
                                '<div class="cell"><div class="cell-label">体检报告打印费</div><div class="cell-value">' +
                                _html4 + ' </div></div>' +
                                '<div class="cell"><div class="cell-label">住宿费</div><div class="cell-value">' +
                                signHtml5+
                                '<input value="'+infos.accommodatioMoney+'" class="editValue" data-name="accommodatioMoney">'+
                                ' 元</div></div>' +
                                '<div class="cell"><div class="cell-label">跨地市交通费（汽车、火车、飞机）</div><div class="cell-value">' +
                                signHtml6+
                                '<input value="'+infos.crossDrivingMoney+'" class="editValue" data-name="crossDrivingMoney">'+

                                ' 元</div></div>' +
                                '<div class="cell" style="border: none"><div class="cell-label" >跨地市交通费（自驾）</div><div class="cell-value">' +
                                signHtml7+
                                '<input value="'+infos.selfDrivingMoney+'" class="editValue" data-name="selfDrivingMoney">'+

                                ' 元</div></div>' +
                                '<div class="cell"><div class="cell-label"></div><div class="cell-value">' +
                                '<input value="'+infos.tollMoney+'" class="editValue" data-name="tollMoney">'+

                                ' 元 (过路费)</div></div>' +
                                '<div class="cell"><div class="cell-label">其他费用</div><div class="cell-value">' +
                                signHtml8+
                                '<input value="'+infos.otherMoney+'" class="editValue" data-name="otherMoney">'+

                                ' 元</div></div>' +
                                '<div class="cell"><div class="cell-label">备注</div><div class="cell-value">' +
                                '<textarea class="editValue editText" data-name="clockDesc" >'+desc+'</textarea>'+ '</div></div>' +
                                '</div></div>'
                        }

                        clickIndex = layer.open({
                            type: 1,
                            title: '费用报销',
                            shade: 0,
                            offset: ['10px', '800px'],
                            area: ['500px', '800px'],
                            content: _html,
                            success:function () {
                                layer.close(openIndex)
                                $('.editValue').focus(function(){
                                    $(this).select()
                                }).blur(function () {
                                    var _this = $(this)
                                    var _name = _this.attr('data-name')
                                    var _value = _this.val()
                                    if (!checkPapers('money', _value) && _name != 'clockDesc' && _value) {
                                        layer.msg('字段不符合规则，请输入小数点两位内的正数字', {
                                            time: 2000,
                                            icon: 2
                                        })
                                        return;
                                    }else if (!_value && _name != 'clockDesc'){
                                        _value = 0
                                    }

                                    var dataObj = {
                                        operateType : 'userClockRe',
                                        clockReId : infos.id,
                                        // preId : $("input[name=preId]").val()
                                    }
                                    var _medicalList = {}, _troubleList = {}, _opcTroubleList={},_printList = {}

                                    if (_name == 'medicalHistoryMoney'){
                                        var _id = _this.attr('data-id')
                                        Object.assign(_medicalList,{
                                            [_id]: _value
                                        })
                                        Object.assign(dataObj,{
                                            medicalList: JSON.stringify(_medicalList)})
                                    }else if (_name == 'troubleshootingMoney'){
                                        var _id = _this.attr('data-id')
                                        Object.assign(_troubleList,{
                                            [_id]: _value
                                        })
                                        Object.assign(dataObj,{
                                            troubleList: JSON.stringify(_troubleList)})
                                    }else if (_name == 'opcTroubleshootingMoney'){
                                        var _id = _this.attr('data-id')
                                        Object.assign(_opcTroubleList,{
                                            [_id]: _value
                                        })
                                        Object.assign(dataObj,{
                                            opcTroubleList: JSON.stringify(_opcTroubleList)})
                                    }else if (_name == 'printingMoney'){
                                        var _id = _this.attr('data-id')
                                        Object.assign(_printList,{
                                            [_id]: _value
                                        })
                                        Object.assign(dataObj,{
                                            printList: JSON.stringify(_printList)})
                                    }else {
                                        Object.assign(dataObj,{
                                            [_name]: _value
                                        })
                                    }

                                    $.ajax({
                                        url: '${ctx}/fee/operate',
                                        type: "post",
                                        data: dataObj,
                                        success: function (res) {
                                            res = JSON.parse(res)
                                            if (res.isSuccess) {
                                                layer.msg('更新成功',
                                                    {
                                                        time: 2000,
                                                        icon: 1
                                                    });
                                            } else {
                                                layer.msg(res.msg, {time: 2000, icon: 2});
                                            }

                                        }
                                    });
                                })
                                $('.layui-layer-close').click(function () {
                                    layer.close(clickIndex)
                                })
                            }
                        });
                        console.log('--2--',clickIndex)

                    })

                    initMap(markers)

                }
            }
        })
        setTimeout(function () {
            layer.close(loadIndex);
        },1000)
    }

    $('.calendar-tbody .calendar-tr').on('click', '.calendar-td', function () {
        if (!$('#userOneId').val() && !demo2.getValue('valueStr')){
            return;
        }
        var _this = $(this)
        _this.addClass('poi-none selected')
        _this.siblings().removeClass('selected')
        Object.assign(params,{dateTime: _this.attr('data-value')})

        getClockInfos(params)
        setTimeout(function () {
            _this.removeClass('poi-none')
        },1000)

    })
    $('.calendar-bottom').on('click', function () {
        if (!$('#userOneId').val() && !demo2.getValue('valueStr')){
            return;
        }
        var today = new Date()
        var todayYMD = dateFormat(today,'yyyy-MM-dd')
        getCalendar(todayYMD)
        var _this = $('.calendar-td.cur[data-value="'+todayYMD+'"]')
        _this.addClass('poi-none selected')
        _this.siblings().removeClass('selected')
        setTimeout(function () {
            _this.removeClass('poi-none')
        },1000)

    })
    $('.calendar-top').on('click', '.change', function () {
        if (!$('#userOneId').val() && !demo2.getValue('valueStr')){
            return;
        }
        var today = new Date($('.calendar-top .now').attr('data-date'))
        var year = today.getFullYear(),
            month = today.getMonth() + 1,
            date = today.getDate(),
            type = ''

        if ($(this).hasClass('previous')) {
            type = 'last'
        } else if ($(this).hasClass('next')) {
            type = 'next'
        }
        var {y,m} = getYM(year, month, type)
        $('.calendar-top .now').html(y + '年' + m + '月').attr('data-date', y + '-' + m)
        // var dd = $('.calendar-tbody .calendar-tr .calendar-td.selected').text()
        var curDate =  $('.calendar-tbody .calendar-tr .calendar-td.selected').attr('data-value')
        var arrYMD = curDate.split('-')
        getCalendar(y + '-' + m + '-'+ PrefixInteger(arrYMD[2],2))
    })
    function getCalendar(date) {
        $.ajax({
            url: '${ctx}/survey/userClock/details',
            type: 'post',
            data: {
                menuType:'dropDownDetails',
                btnCode:'punchInDate',
                userId: userOneId,
                orgId:orgId,
                dateTime: dateFormat(date,'yyyy-MM')
            },
            success: function (res) {
                res = JSON.parse(res)
                if (res.isSuccess){
                    initCalendar(date,res.results)
                }
                $('.ll-submit').removeAttr('disabled')
            }
        })

        params = {
            menuType:'clockDetails',
            btnCode:'clockInDetails',
            userId: userOneId,
            dateTime: date,
            orgId:orgId
        }
        getClockInfos(params)

    }

    function initCalendar(date,list,iconHide) {
        var thisDay = date ? new Date(date) : new Date()
        var thatDay = date ? new Date(date) : new Date()

        var today = new Date()
        var now = dateFormat(thisDay, 'yyyy年MM月dd日')
        var year = thisDay.getFullYear(),
            month = thisDay.getMonth() + 1,
            date = thisDay.getDate();
        var copy = thisDay
        var sumDays = [],
            sup1 = [],
            sup2 = [];

        $('.calendar-top .now').html(dateFormat(thisDay, 'yyyy年MM月')).attr('data-date', year + '-' +
            month + '-' + date)

        var days = getDays(year, month) //当月多少天
        var dates = setDays(days, 'cur',year, month)

        var curFirst = new Date(thatDay.setDate(1))
        var weekday1 = curFirst.getDay() //1号周几 0-6

        var curLast = new Date(thatDay.setDate(days))
        var weekday2 = curLast.getDay() //月末周几

        if (weekday1 != 0) {
            var y = 0,
                m = 0,
                d = 0;
            var num = weekday1
            var {y,m} = getYM(year, month, 'last')
            var lastdays = getYM(year, month, 'last', 'getDays') //当月多少天
            var latsdates = setDays(lastdays, 'last' ,y,m)
            sup1 = latsdates.slice(latsdates.length - num)
        }
        if (weekday2 != 6) {
            var y = 0,
                m = 0,
                d = 0;
            var num = 6 - weekday2
            if (weekday2 == 0) {
                num = 6
            }
            var {y,m} = getYM(year, month, 'next')
            var nextdays = getYM(year, month, 'next', 'getDays') //当月多少天
            var nextdates = setDays(nextdays, 'next', y,m)
            sup2 = nextdates.slice(0, num)
        }

        sumDays = sumDays.concat(sup1, dates, sup2)
        var _html = ''
        sumDays.map(function (cur) {
            var _class = '',_tdclass = '',count = 0
            var icon_html = ''
            var _redClass= ''

            if (cur.content == dateFormat(thisDay, 'yyyy-MM-dd')){
                _tdclass = 'selected'
            }
            var curDate = new Date(cur.content)

            if (cur.type == 'cur') {
                if (list.surveyUserClockDtoList.length){
                    list.surveyUserClockDtoList.map(function (item) {
                        var dd =  dateFormat(item.clockTime, 'dd')
                        if (Number(dd) == Number(cur.value)){
                            count = item.recordCount
                            if (item.caseCount == 0 &&  item.recordCount > 0){
                                _class = 'circle-num color3'
                            }
                            if (item.caseCount > 0 &&  item.recordCount > 0){
                                _class = 'circle-num color2'
                            }
                            if (item.red){
                                _redClass = 'red'
                            }
                        }
                    })
                }
                if (!_class && (cur.week == 0 || cur.week == 6)){

                }else if (curDate.getTime() < today.getTime()){
                    icon_html = '<div class="circle-num '+_class+'">' + count + '</div>'
                }
            }
            if (cur.type == 'last'){
                _tdclass = 'tint'
                //红色
                //黄色 caseCount = 0  recordCount > 0
                //绿色 caseCount > 0  recordCount > 0 color2
                if (list.previousList.length){
                    list.previousList.map(function (item) {
                        var dd =  dateFormat(item.clockTime, 'dd')
                        if (Number(dd) == Number(cur.value)){
                            count = item.recordCount
                            if (item.caseCount == 0 &&  item.recordCount > 0){
                                _class = 'circle-num color3'
                            }
                            if (item.caseCount > 0 &&  item.recordCount > 0){
                                _class = 'circle-num color2'
                            }
                            if (item.red){
                                _redClass = 'red'
                            }
                        }

                    })
                }
                if (!_class && (cur.week == 0 || cur.week == 6)){

                }else if (curDate.getTime() < today.getTime()){
                    icon_html = '<div class="circle-num '+_class+'">' + count + '</div>'
                }
            }
            if (cur.type == 'next'){
                _tdclass = 'tint'
                if (list.nextList.length){
                    list.nextList.map(function (item) {
                        var dd =  dateFormat(item.clockTime, 'dd')
                        if (Number(dd) == Number(cur.value)){
                            count = item.recordCount
                            if (item.caseCount == 0 &&  item.recordCount > 0){
                                _class = 'circle-num color3'
                            }
                            if (item.caseCount > 0 &&  item.recordCount > 0){
                                _class = 'circle-num color2'
                            }
                            if (item.red){
                                _redClass = 'red'
                            }
                        }
                    })
                }
                if (!_class && (cur.week == 0 || cur.week == 6)){

                }else if (curDate.getTime() < today.getTime()){
                    icon_html = '<div class="circle-num '+_class+'">' + count + '</div>'
                }
            }

            if (iconHide){
                icon_html = ''
            }
            _html += '<div class="calendar-td ' + _tdclass+ ' '+ _redClass + ' '+ cur.type + '" data-value="'+cur.content+'">' + icon_html + cur.value +
                '</div>'
        })
        $('.calendar-tbody .calendar-tr').html(_html)
        var listH = _height - $('.calendar').height() -20
        $('.main-content .list').height(listH)

    }

    function getYM(year, month, type, result) {
        var y = year || 0,
            m = month || 0;
        if (type == 'last') {
            if (month == 1) {
                y = year - 1
                m = 12
            } else {
                y = year
                m = m - 1
            }
        } else if (type == 'next') {
            if (month == 12) {
                y = year + 1
                m = 1
            } else {
                y = year
                m = m + 1
            }
        }
        if (result == 'getDays') {
            return getDays(y, m)
        } else {
            m = PrefixInteger(m, 2)
            return {
                y,
                m
            }
        }
    }

    function PrefixInteger(num, m) {
        return (Array(m).join(0) + num).slice(-m);
    }

    function setDays(days, type,y,m) {
        var dates = []
        for (var i = 1; i <= days; i++) {
            if (y){
                var newDate = y + '-' + PrefixInteger(m,2) + '-' + PrefixInteger(i,2)
                var week = (new Date(newDate)).getDay()
                dates.push({
                    type: type,
                    value: i,
                    month: m,
                    year: y,
                    date: i,
                    content: newDate,
                    week:week
                })
            }else {
                dates.push({
                    type: type,
                    value: i,
                    month: m,
                    year: y
                })
            }
        }
        return dates
    }

    function getDays(year, month) {
        var d = new Date(year, month, 0);
        return d.getDate();
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
    function convertToChinaNum (num) {
        var arr1 = new Array('零', '一', '二', '三', '四', '五', '六', '七', '八', '九');
        var arr2 = new Array('', '十', '百', '千', '万', '十', '百', '千', '亿', '十', '百', '千', '万', '十', '百', '千', '亿'); //可继续追加更高位转换值
        if (!num || isNaN(num)) {
            return "零";
        }
        var english = num.toString().split("")
        var result = "";
        for (var i = 0; i < english.length; i++) {
            var des_i = english.length - 1 - i; //倒序排列设值
            result = arr2[i] + result;
            var arr1_index = english[des_i];
            result = arr1[arr1_index] + result;
        }
        result = result.replace(/零(千|百|十)/g, '零').replace(/十零/g, '十');
        result = result.replace(/零+/g, '零');
        result = result.replace(/零亿/g, '亿').replace(/零万/g, '万');
        result = result.replace(/亿万/g, '亿');
        result = result.replace(/零+$/, '')
        //result = result.replace(/零一十/g, '零十');//貌似正规读法是零一十
        result = result.replace(/^一十/g, '十');
        return result;
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

    //正则匹配
    var checkPapers = function (parama, paramb) {
        var map = new Map([
            ['phone', /^[1][3,4,5,7,8,9][0-9]{9}$/],
            ['passport', !/^((1[45]\d{7})|(G\d{8})|(P\d{7})|(S\d{7,8}))?$/],
            ['identity', /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/],
            ['money', /^[0-9]+(\.[0-9]{1,2})?$/],
            ['moneyorMinus', /^(\-|\+)?\d+(\.\d{1,2})?$/],
            ['integer', /^[0-9]\d*$/],
            ['email', /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/],
            ['num_0_1', /^(0+(\.[0-9]{1,2})?|1|1.0|1.00?)$/]
        ])

        if (map.get(parama).test(paramb)) {
            return true
        } else {
            return false
        }
    }

    var map = ''
    function initMap(markers) {
        // $('#container').html('').removeAttr('style')
        console.log('-map-',map)
        if (map){
            map.destroy()
        }
        var center,markerLayer,latlngBounds;
        var Xs = 0,
            Ys = 0,
            len = markers.length
        if (!len){
            center = new TMap.LatLng(37.550339 , 104.114129)
            //定义map变量，调用 TMap.Map() 构造函数创建地图
            map = new TMap.Map(document.getElementById('container'), {
                center: center, //设置地图中心点坐标
                zoom: 4,
                viewMode:'2D',
                baseMap: {			//底图设置（参数为：VectorBaseMap对象）
                    type: 'vector',	//类型：失量底图
                    features: ['base', 'building2d','point','label']
                }
            });
            return;
        }


        var geometries = [],
            paths = []

        markers.map(function (cur) {
            Xs += Number(cur.lat)
            Ys += Number(cur.lng)
            geometries.push({
                "id": cur.id, //点标记唯一标识，后续如果有删除、修改位置等操作，都需要此id
                "styleId": 'myStyle', //指定样式id
                "position": new TMap.LatLng(cur.lat, cur.lng), //点标记坐标位置
                "properties": { //自定义属性
                    "title": "marker1"
                }
            })
            paths.push(new TMap.LatLng(cur.lat, cur.lng))

        })
        center = new TMap.LatLng((Xs / len).toFixed(5), (Ys / len).toFixed(5))
        map = new TMap.Map(document.getElementById('container'), {
            center: center,
            viewMode:'2D',
            baseMap: {			//底图设置（参数为：VectorBaseMap对象）
                type: 'vector',	//类型：失量底图
                features: ['base', 'building2d','point','label']
            }
        });
        markerLayer = new TMap.MultiMarker({
            map: map,
            styles: {
                "myStyle": new TMap.MarkerStyle({
                    "width": 20, // 点标记样式宽度（像素）
                    "height": 30, // 点标记样式高度（像素）
                })
            },
            geometries: geometries
        });

        latlngBounds = new TMap.LatLngBounds();
        for (var i = 0; i < paths.length; i++) {
            latlngBounds.extend(paths[i]);
        }
        var latNum = Math.abs(latlngBounds._ne.lat - latlngBounds._sw.lat)
        var lngNum = Math.abs(latlngBounds._ne.lng - latlngBounds._sw.lng)

        latlngBounds._ne.lat += latNum * 0.3
        latlngBounds._ne.lng += lngNum * 0.2
        latlngBounds._sw.lat -= latNum * 0.1
        latlngBounds._sw.lng -= lngNum * 0.2

        map.fitBounds(latlngBounds);

        markers.map(function (cur, i) {
            var index = i + 1
            var _html = ''
            if (!cur.check){
                _html = '<div class="marker"><div><div class="markerIcon">' + index + '</div><div class="markerTime">' + cur.time +
                    '</div></div><div>' + cur.name + '</div><div>' +
                    cur.price +
                    ' 元</div></div>'
            }else {
                _html = '<div class="marker marker2 "><div><div class="markerIcon">' + index + '</div><div class="markerTime">' + cur.time +
                    '</div></div><div>' + cur.name + '</div></div>'
            }
            new TMap.InfoWindow({
                map: map,
                position: new TMap.LatLng(cur.lat, cur.lng),
                content: _html,
                offset: {
                    x: 0,
                    y: -32
                }
            });
            if (i < len - 1) {
                var start = cur.lat + ',' + cur.lng
                var end = markers[i + 1].lat + ',' + markers[i + 1].lng
                setRoute(start, end)
            }
        })

        function setRoute(start, end) {
            //WebServiceAPI请求URL（驾车路线规划默认会参考实时路况进行计算）
            var url = "https://apis.map.qq.com/ws/direction/v1/transit/"; //请求路径
            url += "?from=" + start; //起点坐标
            url += "&to=" + end; //终点坐标
            url += "&output=jsonp&callback=cb"; //指定JSONP回调函数名，本例为cb
            url += "&key=VOZBZ-FPYKP-R33D4-VHJCM-FTNOE-2LBEB"; //开发key，可在控制台自助创建

            //发起JSONP请求，获取路线规划结果
            jsonp_request(url);
        }


    }
    //浏览器调用WebServiceAPI需要通过Jsonp的方式，此处定义了发送JOSNP请求的函数
    function jsonp_request(url) {
        var script = document.createElement('script');
        script.src = url;
        document.body.appendChild(script);
    }
    var rounteType = 'transit' //driving transit
    //定义请求回调函数
    function cb(ret) {
        if (ret.status == '0'){
            getPl(ret)
        }

    }
    function getPl(ret) {
        //从结果中取出路线坐标串
        var coors = '',
            pl = [],
            price = 0,
        sum = 0;
        //坐标解压（返回的点串坐标，通过前向差分进行压缩，因此需要解压）
        var kr = 1000000;
        if (rounteType == 'driving') {
            coors = ret.result.routes[0].polyline;
            for (var i = 2; i < coors.length; i++) {
                coors[i] = Number(coors[i - 2]) + Number(coors[i]) / kr;
            }
            //将解压后的坐标生成LatLng数组
            for (var i = 0; i < coors.length; i += 2) {
                pl.push(new TMap.LatLng(coors[i], coors[i + 1]));
            }
        } else if (rounteType == 'transit') {

            coors = ret.result.routes[0].steps.map(function (item) {
                if (item.mode == 'WALKING') {
                    coors = item.polyline
                    for (var i = 2; i < coors.length; i++) {
                        coors[i] = Number(coors[i - 2]) + Number(coors[i]) / kr;
                    }
                    for (var i = 0; i < coors.length; i += 2) {
                        pl.push(new TMap.LatLng(coors[i], coors[i + 1]));
                    }
                } else if (item.mode == 'TRANSIT') {
                    coors = item.lines[0].polyline
                    var price = item.lines[0].price == '-1'? 0 : item.lines[0].price
                    for (var i = 2; i < coors.length; i++) {
                        coors[i] = Number(coors[i - 2]) + Number(coors[i]) / kr;
                    }
                    for (var i = 0; i < coors.length; i += 2) {
                        pl.push(new TMap.LatLng(coors[i], coors[i + 1]));
                    }
                }

            })
        }
        display_polyline(pl) //显示路线
    }

    function display_polyline(pl) {
        var curTime = new Date()
        var polylineLayer = new TMap.MultiPolyline({
            id: 'polyline-layer' + curTime.getTime(),
            map: map,
            styles: {
                'style_blue': new TMap.PolylineStyle({
                    'color': '#3777FF', //线填充色3777FF
                    'width': 8, //折线宽度
                    'borderWidth': 1, //边线宽度
                    'borderColor': '#FFF', //边线颜色
                    'lineCap': 'round', //线端头方式
                    'showArrow': true
                })
            },
            //折线数据定义
            geometries: [{
                'id': 'pl_1', //折线唯一标识，删除时使用
                'styleId': 'style_blue', //绑定样式名
                'paths': pl
            }]
        });
    }

    function initMap2(markers) {
        var prices=[]
        var center,markerLayer,latlngBounds;
        var Xs = 0,
            Ys = 0,
            len = markers.length
        if (!len){
            map = new AMap.Map('container', {
                resizeEnable: true,
                center: [104.114129, 37.550339],
                zoom: 4
            });
            return;
        }


        var geometries = [],
            paths = []

        markers.map(function (cur) {
            Ys += Number(cur.lat)
            Xs += Number(cur.lng)
        })
        map = new AMap.Map('container', {
            resizeEnable: true,
            center: [(Xs / len).toFixed(5), (Ys / len).toFixed(5)],
            // zoom: 4
        });
        AMap.plugin(['AMap.ToolBar','AMap.Transfer'],function(){//异步同时加载多个插件
            toolbar = new AMap.ToolBar();
            map.addControl(toolbar);
        });

        var markerList = [];
        markers.map(function (cur, i) {
            var index = i + 1
            var _html = '',price= ''

            if (!cur.check){
                _html = '<div class="marker" data-inedx="'+i+'"><div><div class="markerIcon">' + index + '</div><div class="markerTime">' + cur.time +
                    '</div></div><div>' + cur.name + '</div><div class="price">' +
                    cur.price +
                    ' 元</div></div>'
            }else {
                _html = '<div class="marker marker2 "><div><div class="markerIcon">' + index + '</div><div class="markerTime">' + cur.time +
                    '</div></div><div>' + cur.name + '</div></div>'
            }
            var marker = new AMap.Marker({
                position:  [cur.lng, cur.lat], // 基点位置
                icon: '${ctx}/img/icon-marker.png',
                offset: new AMap.Pixel(-13, -30), // 相对于基点的偏移位置
                label: {
                    offset: new AMap.Pixel(20, -6),  //设置文本标注偏移量
                    content: _html, //设置文本标注内容
                    direction: 'top' //设置文本标注方位
                }
            });
            markerList.push(marker)
            if (i < len - 1) {
                var transOptions = {
                    map: map,
                    city: '上海市',
                    // panel: 'panel',
                    cityd:'上海市',
                    extensions: 'all',
                    policy: AMap.TransferPolicy.LEAST_TIME
                };
                var transfer = new AMap.Transfer(transOptions)
                var startLngLat = new AMap.LngLat(cur.lng,cur.lat)
                var endLngLat = new AMap.LngLat(markers[i + 1].lng,markers[i + 1].lat)
                console.log(startLngLat, endLngLat)
                transfer.search(startLngLat, endLngLat, function (status, result) {
                    // 未出错时，result即是对应的路线规划方案
                    console.log(status,result)
                    if (status == 'complete' && result.info =='OK'){
                        price  = result.plans[0].cost
                        prices.push(result.plans[0].cost)
                        console.log(prices)

                        $('.marker[data-index="'+i+'"] .price').append('预计('+price+')元')
                    }
                })
            }
        })

        map.add(markerList);
        var newCenter = map.setFitView();
    }
</script>
</body>
</html>
