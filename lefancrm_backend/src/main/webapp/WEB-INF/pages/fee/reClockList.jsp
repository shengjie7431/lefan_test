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
        .active {
            background: #3ba9ff;
            color: #fff;
            border: none;
        }

        .butList {
            height: 32px;
            padding: 0 10px;
            margin: 4px 8px;
            cursor: pointer;
            box-sizing: border-box;
        }

        .layui-form-label {
            box-sizing: content-box !important;
        }

        .main {
            width: 98%;
            margin: 0 auto;
        }

        /*.layui-form-item {*/
        /*margin: 0 !important;*/
        /*}*/
        .layui-form {
            margin-top: 10px;
        }

        .layui-form .layui-form-item .layui-inline {
            margin-right: 0;
        }

        .layui-form label {
            margin-bottom: 0;
        }

        .layui-form .layui-form-item .layui-inline .layui-form-label {
            width: 85px;
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




        .layui-form-label-a {
            width: auto !important;
            white-space: nowrap;
        }

        .layui-form-label a {
            color: #3BA9FF !important;
            white-space: nowrap;
        }

        .searchs {
            padding: 15px 0 5px 0;
            background-color: #d9edf7;
        }

        .layui-btn-import {
            color: #fff !important;
        }

        input {
            border-color: #e6e6e6 !important;
        }

        .top_reason {
            width: 100%;
            padding: 20px 2%;
            background-color: rgba(238, 136, 132, 0.26);
        }

        .top_reason div {
            padding: 10px 0;
        }

        .sum_content {
            padding: 10px 0;
            text-align: right;
        }

        .sum {
            display: inline-block;
            padding-right: 20px;
            text-align: right;
            font-size: 12px;
        }

        .sum.sum0 {
            padding-right: 2px;
        }

        .sum span {
            font-size: 26px;
            font-weight: bold;
            color: #666;
            font-family: Impact;
        }

        @media screen and (max-width: 1300px) {
            .sum {
                display: inline-block;
                padding-right: 8px;
                text-align: right;
                font-size: 11px;
            }

            .sum.sum0 {
                padding-right: 2px;
            }

            .sum span {
                font-size: 22px;
            }
        }

        .layui-input1 {
            width: 100%;
            height: 100%;
            padding-left: 10px;
            border: 1px solid #e6e6e6 !important;
            border-radius: 2px;
        }

        .layui-input-td {
            line-height: inherit;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .lf-export {
        }

        .layui-table-fixed-r .layui-table-cell {
            padding: 0;
        }

        /*.layui-table-fixed-r .layui-table-cell .operateBtn2{*/
        /*margin-left: 0;*/
        /*}*/
        .operateBtn {
            color: #3ba9ff;
            margin-left: 10px;
            cursor: pointer;
        }

        .operateBtn:hover {
            text-decoration: none;
            color: red;
        }


        .layui-table-hover {
            background-color: rgba(60, 169, 255, 0.13) !important;
        }

        .lf-none {
            display: none;
        }

        .layui-btn-xs {
            border-radius: 2px;
        }


        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }

        .export-btn {
            width: 90%;
            margin: 0 auto;
            text-align: center;
            display: flex;
            justify-content: center;
        }


        tr.active td {
            background-color: #ec807e;
        }

        .layui-input-block {
            width: 50%;
            margin: 0 auto;
        }

        .borer-none{
            border: none!important;
            background: none!important;
        }
        .header{
            width: 100%;
            padding-top: 10px;
            text-align: right;
        }
        .layui-table-main .layui-table-cell{
            display: flex;
        }
        .layui-table .layui-input {
            height: 100%;
            width: 50%;
            display: inline-block;
        }
        div.layui-table-fixed .layui-input {
            height: 100%;
            width: 96%!important;
            margin: 0 auto!important;
            display: inline-block;
        }

        .layui-table .layui-table-edit {
            height: 100%;
            width: 50%!important;
            display: inline-block;
        }
        div.layui-table-fixed .layui-table-edit {
            height: 100%;
            width: 96%!important;
            margin: 0 auto!important;
            display: inline-block;
        }
        .viewSign{
            display: inline-block;
            margin-left: 5px;
            padding: 2px;
            width:80px;
            height: 26px;
            cursor: pointer;
            background: url('data:image/png;base64,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');
            background-repeat: no-repeat;
            background-size: contain;
            background-position: center;
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
        .scase{
            color: #3BA9FF;
            cursor: pointer;
        }
        .scase-name{

        }
        .scase-money{

        }
        .update-content{
            min-height: 68px;
            max-height: 168px;
            overflow: auto;
        }
        .update-content .layui-form-label {
            width: 105px!important;
        }
        /*.table_block{*/
            /*width: 100%;*/
        /*}*/
        /*table.layui-table{*/
            /*width: 100% !important;*/
        /*}*/
    </style>
</head>
<body>
<div class="main">
    <c:if test="${createTime != null && createTime != ''}">
        <div class="header"><button class="butList active" onclick="caseClockDetails('${userId}','${createTime}')">查看打卡足迹</button>
     </c:if>
    </div>
    <div class="table_block">
        <table class="layui-table" id="test" lay-filter="test" lay-data="{id: 'test'}" lay-skin="line" lay-size="lg">
        </table>
    </div>
    <input hidden name="type" value="${type}">
    <input hidden name="reInfoId" value="${reInfoId}">
    <input hidden name="clockIds" value="${clockIds}">
    <input hidden name="preId" value="${preId}">
    <input hidden name="orgRole" value="${orgRole}">
    <input hidden name="invRole" value="${invRole}">
    <input hidden name="reState" value="${reState}">
    <input hidden name="createTime" value="${createTime}">
    <input hidden name="userId" value="${userId}">
</div>
<script type="text/html" id="export-content">
    <div class="layui-form">
        <div class="layui-form-item">
            <div id="lf-export" class="selectMul" style="width: 80%;margin: 50px auto;"></div>
        </div>
        <div class="layui-inline export-btn">
            <label class="layui-form-label">
                <button type="button" class="layui-btn s-btn layui-btn-primary" data-type="cancel">取消</button>
            </label>
            <label class="layui-form-label">
                <button type="button" class="layui-btn s-btn layui-btn-normal" data-type="export">确定</button>
            </label>
        </div>
    </div>
</script>
<script type="text/html" id="input1">
    {{#  if(d.surveyClockReInfo.cityinDrivingMoney == 'undefinded' || d.surveyClockReInfo.cityinDrivingMoney == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.surveyClockReInfo.cityinDrivingMoney}}</div>
    {{#  } }}
    {{#  if(d.signList[0].value){ }}
    <div class="viewSign" onclick="viewSign(1,{{d.signList[0].index}})"></div>
    {{#  } }}

</script>



<script type="text/html" id="input3">
    {{#  if(d.surveyClockReInfo.troubleshootingMoney == 'undefinded' || d.surveyClockReInfo.troubleshootingMoney == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.surveyClockReInfo.troubleshootingMoney}}</div>
    {{#  } }}
    {{#  if(d.signList[2].value){ }}
    <div class="viewSign" onclick="viewSign(3,{{d.signList[2].index}})"></div>
    {{#  } }}

</script>
<script type="text/html" id="input4">
    {{#  if(d.surveyClockReInfo.printingMoney == 'undefinded' || d.surveyClockReInfo.printingMoney == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.surveyClockReInfo.printingMoney}}</div>
    {{#  } }}
    {{#  if(d.signList[3].value){ }}
    <div class="viewSign" onclick="viewSign(4,{{d.signList[3].index}})"></div>
    {{#  } }}
</script>
<script type="text/html" id="input5">
    {{#  if(d.surveyClockReInfo.accommodatioMoney == 'undefinded' || d.surveyClockReInfo.accommodatioMoney == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.surveyClockReInfo.accommodatioMoney}}</div>
    {{#  } }}
    {{#  if(d.signList[4].value){ }}
    <div class="viewSign" onclick="viewSign(5,{{d.signList[4].index}})"></div>
    {{#  } }}
</script>
<script type="text/html" id="input6">
    {{#  if(d.surveyClockReInfo.crossDrivingMoney == 'undefinded' || d.surveyClockReInfo.crossDrivingMoney == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.surveyClockReInfo.crossDrivingMoney}}</div>
    {{#  } }}
    {{#  if(d.signList[5].value){ }}
    <div class="viewSign" onclick="viewSign(6,{{d.signList[5].index}})"></div>
    {{#  } }}

</script>
<script type="text/html" id="input7">
    {{#  if(d.surveyClockReInfo.selfDrivingMoney == 'undefinded' || d.surveyClockReInfo.selfDrivingMoney == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.surveyClockReInfo.selfDrivingMoney}}</div>

    {{#  } }}
</script>
<script type="text/html" id="input8">
    {{#  if(d.surveyClockReInfo.otherMoney == 'undefinded' || d.surveyClockReInfo.otherMoney == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.surveyClockReInfo.otherMoney}}</div>
    {{#  } }}
    {{#  if(d.signList[7].value){ }}
    <div class="viewSign" onclick="viewSign(8,{{d.signList[7].index}})"></div>
    {{#  } }}
</script>

<script type="text/html" id="input9">
    {{#  if(d.surveyClockReInfo.clockDesc == 'undefinded' || d.surveyClockReInfo.clockDesc == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td" title="{{d.surveyClockReInfo.clockDesc}}" style="width: 100%;">
        {{d.surveyClockReInfo.clockDesc}}
    </div>
    {{#  } }}
</script>

<script type="text/html" id="input10">
    {{#  if(d.surveyClockReInfo.selfDrivingMoney == 'undefinded' || d.surveyClockReInfo.selfDrivingMoney == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">
        {{d.surveyClockReInfo.selfDrivingMoney}}
    </div>
    {{#  } }}
</script>


<script type="text/html" id="input21">
    {{#  if(d.surveyClockReInfo.cityinDrivingMoney == 'undefinded' || d.surveyClockReInfo.cityinDrivingMoney == null){ }}
    <div class="layui-input layui-input-td  borer-none"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td  borer-none">{{d.surveyClockReInfo.cityinDrivingMoney}}</div>
    {{#  } }}
    {{#  if(d.signList[0].value){ }}
    <div class="viewSign" onclick="viewSign(1,{{d.signList[0].index}})"></div>
    {{#  } }}
</script>

<script type="text/html" id="input22">
    {{#  if(d.surveyClockReInfo.medicalHistoryMoney == 'undefinded' || d.surveyClockReInfo.medicalHistoryMoney == null){ }}
    <div class="layui-input layui-input-td  borer-none"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td  borer-none">{{d.surveyClockReInfo.medicalHistoryMoney}}</div>
    {{#  } }}
    {{#  if(d.signList[1].value){ }}
    <div class="viewSign" onclick="viewSign(2,{{d.signList[1].index}})"></div>
    {{#  } }}
</script>
<script type="text/html" id="input23">
    {{#  if(d.surveyClockReInfo.troubleshootingMoney == 'undefinded' || d.surveyClockReInfo.troubleshootingMoney == null){ }}
    <div class="layui-input layui-input-td  borer-none"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td  borer-none">{{d.surveyClockReInfo.troubleshootingMoney}}</div>
    {{#  } }}
    {{#  if(d.signList[2].value){ }}
    <div class="viewSign" onclick="viewSign(3,{{d.signList[2].index}})"></div>
    {{#  } }}

</script>
<script type="text/html" id="input24">
    {{#  if(d.surveyClockReInfo.printingMoney == 'undefinded' || d.surveyClockReInfo.printingMoney == null){ }}
    <div class="layui-input layui-input-td  borer-none"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td  borer-none">{{d.surveyClockReInfo.printingMoney}}</div>
    {{#  } }}
    {{#  if(d.signList[3].value){ }}
    <div class="viewSign" onclick="viewSign(4,{{d.signList[3].index}})"></div>
    {{#  } }}

</script>
<script type="text/html" id="input25">
    {{#  if(d.surveyClockReInfo.accommodatioMoney == 'undefinded' || d.surveyClockReInfo.accommodatioMoney == null){ }}
    <div class="layui-input layui-input-td  borer-none"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td  borer-none">{{d.surveyClockReInfo.accommodatioMoney}}</div>
    {{#  } }}
    {{#  if(d.signList[4].value){ }}
    <div class="viewSign" onclick="viewSign(5,{{d.signList[4].index}})"></div>
    {{#  } }}

</script>
<script type="text/html" id="input26">
    {{#  if(d.surveyClockReInfo.crossDrivingMoney == 'undefinded' || d.surveyClockReInfo.crossDrivingMoney == null){ }}
    <div class="layui-input layui-input-td  borer-none"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td  borer-none">{{d.surveyClockReInfo.crossDrivingMoney}}</div>
    {{#  } }}
    {{#  if(d.signList[5].value){ }}
    <div class="viewSign" onclick="viewSign(6,{{d.signList[5].index}})"></div>
    {{#  } }}

</script>
<script type="text/html" id="input27">
    {{#  if(d.surveyClockReInfo.selfDrivingMoney == 'undefinded' || d.surveyClockReInfo.selfDrivingMoney == null){ }}
    <div class="layui-input layui-input-td  borer-none"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td  borer-none">{{d.surveyClockReInfo.selfDrivingMoney}}</div>
    {{#  } }}
    <%--<div class="viewSign" onclick="viewSign(7)"></div>--%>
</script>
<script type="text/html" id="input28">
    {{#  if(d.surveyClockReInfo.otherMoney == 'undefinded' || d.surveyClockReInfo.otherMoney == null){ }}
    <div class="layui-input layui-input-td  borer-none"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td  borer-none">{{d.surveyClockReInfo.otherMoney}}</div>
    {{#  } }}
    {{#  if(d.signList[7].value){ }}
    <div class="viewSign" onclick="viewSign(8,{{d.signList[7].index}})"></div>
    {{#  } }}

</script>

<script type="text/html" id="input29">
    {{#  if(d.surveyClockReInfo.clockDesc == 'undefinded' || d.surveyClockReInfo.clockDesc == null){ }}
    <div class="layui-input layui-input-td  borer-none"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td  borer-none" title="{{d.surveyClockReInfo.clockDesc}}" style="width: 100%;">
        {{d.surveyClockReInfo.clockDesc}}
    </div>
    {{#  } }}
</script>

<script type="text/html" id="view-or-edit">
    <div class="layui-form selfDrive-content">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">公里</label>
                <div class="layui-input-inline _input">
                    <input type="text" name="kilometresNum" placeholder="请输入公里" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">金额</label>
                <div class="layui-input-inline _input">
                    <input type="text" name="selfDrivingMoney" disabled placeholder="输入公里数自动计算" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">过路费</label>
                <div class="layui-input-inline _input">
                    <input type="text" name="tollMoney" placeholder="请输入过路费" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-primary close-reason">取消</button>
                <button class="layui-btn layui-btn-normal submit-reason">保存并关闭</button>
            </div>
        </div>

    </div>
</script>
<script type="text/html" id="view-or-update">
    <div class="layui-form">
        <div class="layui-form-item update-content">

        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-primary submit-close">取消</button>
                <button class="layui-btn layui-btn-normal submit-update">保存并关闭</button>
            </div>
        </div>
    </div>
</script>
<script type="text/html" id="view-or">
    <div class="layui-form">
        <div class="layui-form-item update-content">

        </div>
    </div>
</script>
<script type="text/html" id="table-content-child">
    <div class="sign-info">

    </div>
</script>
<script src="${ctx}/js/jquery-3.4.1.js"></script>
<script src="${ctx}/js/layui/layui.js"></script>
<script>
    var $ = ''

    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        soulTable: 'soulTable'
    })
    
    var clockList = ''



    function viewSign(id,lineIndex){
        console.log(id)
        var e = window.event || arguments.callee.caller.arguments[0];
        e.stopPropagation()
        var signList = clockList[lineIndex].signList
        signList.map(function (cur) {
            if (cur.id == id){
                layer.open({
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
    }
    layui.use(['jquery', 'soulTable', 'layer', 'table'], function () {
        $ = jQuery = layui.$
        var orgRole = $("input[name=orgRole]").val();
        var type = $("input[name=type]").val();
        var reState = $("input[name=reState]").val();
        var clockIds = $("input[name=clockIds]").val();

        var soulTable = layui.soulTable,
            table = layui.table

        layer = layui.layer

        var ctx = "${ctx}";



        /****************************** 切换可编辑列表 -  角色: _cols1 行政主管，总部，_cols2 人事  _cols00 只读 *********************************/
        var _cols1 = [
            [{
                field: 'clockTime',
                width: 170,
                title: '打卡时间',
                templet: function (d) {
                    return layui.util.toDateString(d.clockTime, 'yyyy-MM-dd HH:mm:ss')
                }
            }, {
                field: 'addressName',
                width: 180,
                title: '打卡地点',
            }, {
                field: 'p3',
                width: 150,
                title: '关联被调查人',
                event: 'user',
                style: 'color: #3BA9FF;cursor: pointer;',
                templet: function (d) {
                    var str = "";
                    d.clockCaseList.forEach(function (e, index) {
                        str += "<span onclick='info(" + e.invCaseId + "," + e.orgCaseId + "," + d.surveyOrgId + "," + e.surveyInfoId + ")'>" + e.surveyPerson + "</span>" + (index < d.clockCaseList.length - 1 ? "," : "");
                    });
                    return str
                },
            }, {
                field: 'cityinDrivingMoney',
                width: 190,
                title: '市内交通费',
                sort: true,
                edit: 'text',
                templet: '#input1',
            }, {
                field: 'medicalHistoryMoney',
                width: 190,
                title: '病史费（含复印费）',
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'mhm',
                templet: function (d) {
                    var scaseList = d.clockCaseList
                    var _html= ''
                    if (scaseList.length >0){
                        scaseList.map(function (cur) {
                            // _html += '<div class="scase"><div class="scase-name">'+cur.surveyPerson + ' </div><div class="scase-money">' +cur.medicalHistoryMoney+';</div></div>'
                            _html += cur.surveyPerson + ': ' +cur.medicalHistoryMoney +'; '
                        })
                    }
                    return _html
                },
            },{
                field: 'troubleshootingMoney',
                width: 190,
                title: '住院排查费用',
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'tsm',
                templet: function (d) {
                    var scaseList = d.clockCaseList
                    var _html= ''
                    if (scaseList.length >0){
                        scaseList.map(function (cur) {
                            _html += cur.surveyPerson + ': ' +cur.troubleshootingMoney +'; '
                        })
                    }
                    return _html
                },
            }, {
                field: 'opcTroubleshootingMoney',
                width: 190,
                title: '门诊排查费用',
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'otsm',
                templet: function (d) {
                    var scaseList = d.clockCaseList
                    var _html= ''
                    if (scaseList.length >0){
                        scaseList.map(function (cur) {
                            _html += cur.surveyPerson + ': ' +cur.opcTroubleshootingMoney +'; '
                        })
                    }
                    return _html
                },
            }, {
                field: 'printingMoney',
                width: 190,
                title: '体检报告打印费',
                edit: 'text',
                sort: true,
                templet: '#input4',
            }, {
                field: 'accommodatioMoney',
                width: 190,
                title: '住宿费',
                sort: true,
                edit: 'text',
                templet: '#input5',
            }, {
                field: 'crossDrivingMoney',
                width: 240,
                title: '跨地市交通费（汽车、火车、飞机）',
                sort: true,
                edit: 'text',
                templet: '#input6',
            }, {
                field: 'selfDrivingMoney',
                width: 190,
                title: '跨地市交通费（自驾）',
                sort: true,
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'selfDriving',
                // templet: '#input10',
                templet: function (d) {
                    if (d.signList[6].value){
                        return parseFloat(d.surveyClockReInfo.selfDrivingMoney + d.surveyClockReInfo.tollMoney).toFixed(2)+' <div class="viewSign" onclick="viewSign(7,'+d.signList[6].index+')"></div>\n'
                    }else {
                        return parseFloat(d.surveyClockReInfo.selfDrivingMoney + d.surveyClockReInfo.tollMoney).toFixed(2)
                    }
                }
            },{
                field: 'otherMoney',
                width: 190,
                title: '其他费用',
                sort: true,
                edit: 'text',
                templet: '#input8',
            }, {
                field: 'clockDesc',
                width: 150,
                title: '备注',
                fixed: 'right',
                align: 'center',
                edit: 'text',
                templet: '#input9',
            }]
        ]
        var _cols0 = [
            [{
                field: 'clockTime',
                width: 170,
                title: '打卡时间',
                templet: function (d) {
                    return layui.util.toDateString(d.clockTime, 'yyyy-MM-dd HH:mm:ss')
                }
            }, {
                field: 'addressName',
                width: 180,
                title: '打卡地点',
            }, {
                field: 'p3',
                width: 150,
                title: '关联被调查人',
                event: 'user',
                style: 'color: #3BA9FF;cursor: pointer;',
                templet: function (d) {
                    var str = "";
                    d.clockCaseList.forEach(function (e, index) {
                        str += "<span onclick='info(" + e.invCaseId + "," + e.orgCaseId + "," + d.surveyOrgId + "," + e.surveyInfoId + ")'>" + e.surveyPerson + "</span>" + (index < d.clockCaseList.length - 1 ? "," : "");
                    });
                    return str
                },
            }, {
                field: 'cityinDrivingMoney',
                width: 190,
                title: '市内交通费',
                sort: true,
                templet: '#input21',
            }, {
                field: 'medicalHistoryMoney',
                width: 190,
                title: '病史费（含复印费）',
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'mhmD',
                templet: function (d) {
                    var scaseList = d.clockCaseList
                    var _html= ''
                    if (scaseList.length >0){
                        scaseList.map(function (cur) {
                            // _html += '<div class="scase"><div class="scase-name">'+cur.surveyPerson + ' </div><div class="scase-money">' +cur.medicalHistoryMoney+';</div></div>'
                            _html += cur.surveyPerson + ': ' +cur.medicalHistoryMoney +'; '
                        })
                    }
                    return _html
                }
            }, {
                field: 'troubleshootingMoney',
                width: 190,
                title: '住院排查费用',
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'tsmD',
                templet: function (d) {
                    var scaseList = d.clockCaseList
                    var _html= ''
                    if (scaseList.length >0){
                        scaseList.map(function (cur) {
                            _html += cur.surveyPerson + ': ' +cur.troubleshootingMoney +'; '
                        })
                    }
                    return _html
                },
            }, {
                field: 'opcTroubleshootingMoney',
                width: 190,
                title: '门诊排查费用',
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'otsmD',
                templet: function (d) {
                    var scaseList = d.clockCaseList
                    var _html= ''
                    if (scaseList.length >0){
                        scaseList.map(function (cur) {
                            _html += cur.surveyPerson + ': ' +cur.opcTroubleshootingMoney +'; '
                        })
                    }
                    return _html
                },
            },  {
                field: 'printingMoney',
                width: 190,
                title: '体检报告打印费',
                sort: true,
                templet: '#input24',
            }, {
                field: 'accommodatioMoney',
                width: 190,
                title: '住宿费',
                sort: true,
                templet: '#input25',
            }, {
                field: 'crossDrivingMoney',
                width: 240,
                title: '跨地市交通费（汽车、火车、飞机）',
                sort: true,
                templet: '#input26',
            }, {
                field: 'selfDrivingMoney',
                width: 190,
                title: '跨地市交通费（自驾）',
                sort: true,
                templet: function (d) {
                    if (d.signList[6].value){
                        return parseFloat(d.surveyClockReInfo.selfDrivingMoney+d.surveyClockReInfo.tollMoney).toFixed(2)+' <div class="viewSign" onclick="viewSign(7,'+d.signList[6].index+')"></div>\n'
                    }else {
                        return parseFloat(d.surveyClockReInfo.selfDrivingMoney+d.surveyClockReInfo.tollMoney).toFixed(2)
                    }
                }
            }, {
                field: 'otherMoney',
                width: 190,
                title: '其他费用',
                sort: true,
                templet: '#input28',
            }, {
                field: 'clockDesc',
                width: 150,
                fixed: 'right',
                align: 'center',

                title: '备注',
                templet: '#input29',
            }]
        ]
        _cols = _cols1

        if (clockIds == null || clockIds == ''){
            if (type != 'all' || orgRole != 'true' || (reState > 2 && reState < 8 ) ) {
                _cols = _cols0
            }
        }else {
            if (type != 'all' || orgRole != 'true' || (reState > 1 && reState < 4 ) ) {
                _cols = _cols0
            }
        }

        var _ajax = $.ajax
        var _h = $('.searchs').outerHeight() + $('.stepInfo').outerHeight() + $('.fileData').outerHeight() + $('.sum_content').outerHeight() + $('.top_reason').outerHeight() + 80 + $('.header').outerHeight()
        var fullH = 'full-' + _h
        var tableRender = {
            id: "test",
            elem: '#test',
            cols: _cols,
            url: '${ctx}/fee/getData',
            where: {
                btnCode: 'reClockList',
                reInfoId: $("input[name=reInfoId]").val(),
                type: $("input[name=type]").val(),
                clockIds: $("input[name=clockIds]").val()
            },
            page: false,
            limit: 10000,
            edit: 'text',
            drag: false,
            cellMinWidth: 80,
            height: fullH,
            even: true,
            overflow: {
                type: 'tips',
                hoverTime: 100,
                color: '#333',
                bgColor: '#fafafa',
                minWidth: 100,
                maxWidth: 500,
            },
            parseData: function (res) {
                 clockList = res.results
                clockList.map(function (cur,i) {
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
                    clockList[i].signList = signList
                })
                return {
                    "code": res.isSuccess ? 0 : 1,
                    "msg": res.msg,
                    "count": res.count,
                    "data": clockList
                }
            },
            done: function (res) {
                console.log(res)
                soulTable.render(this)
                sessionStorage.setItem('dkjl', JSON.stringify(res.data))
                if (type != 'all' || orgRole != 'true') {
                    $('.table_block .layui-input.layui-input-td').css({
                        border: 'none',
                        background: 'none'
                    })
                }
            }
        };

        setTable(tableRender);

        var editFlag = false;

        $('.table_block').on('focus', '.layui-table-edit', function () {
            $('.layui-table-header').css({
                'pointer-events': 'none'
            })
            $(this).select()
        })
        $('.table_block').on('blur', '.layui-table-edit', function () {
            setTimeout(function () {
                $('.layui-table-header').css({
                    'pointer-events': 'auto'
                })
            }, 1000)
        })

        $('.table_block').on('mouseleave', 'td[data-edit=text]', function (e) {
            setTimeout(function () {
                if (!editFlag && !$('.table_block').find('.layui-table-edit').length) {
                    $('.layui-table-header').css({
                        'pointer-events': 'auto'
                    })
                }
            }, 1000)

        })

        /****************************** 初始化单元格 *********************************/
        function setTable(tableRender) {
            var myTable = table.render(tableRender);
            /**
             * 监听单元格编辑
             * edit是固定事件名，
             * test是table原始容器的属性 lay-filter="对应的值"
             */
            table.on('edit(test)', function (obj) {
                editFlag = true
                var value = obj.value.replace(/\s+/g, ""), //得到修改后的值
                    data = obj.data, //得到所在行所有键值
                    field = obj.field //得到字段
                var flag = true
                var dkjl = JSON.parse(sessionStorage.getItem('dkjl')) || []
                var oldValue = ''
                var index = ''
                dkjl.map(function (cur,i) {
                    if (cur.id == obj.data.id){
                        oldValue = cur.surveyClockReInfo[field]
                        cur.surveyClockReInfo = obj.data
                        index = i
                    }
                })

                if (field != 'clockDesc' && value) {// 大于0,两位小数
                    flag = checkPapers('money', value)
                    if (!flag) {
                        layer.msg('字段不符合规则，请输入小数点两位内的正数字', {
                            time: 2000,
                            icon: 2
                        })
                        obj.data.surveyClockReInfo[field] = oldValue
                        console.log(obj)
                        $(obj.tr[0]).children('td[data-field='+field+']').find('.layui-input').val(oldValue)
                        obj.update(obj)
                        return;
                    }
                }
                var dataObj = {};
                dataObj.operateType = 'userClockRe';
                dataObj.clockReId = data.surveyClockReInfo.id;
                dataObj.reInfoId = data.reInfoId;
                dataObj.preId = $("input[name=preId]").val();

                if (field == 'medicalHistoryMoney') dataObj.medicalHistoryMoney = value || 0;
                if (field == 'cityinDrivingMoney') dataObj.cityinDrivingMoney = value || 0;
                if (field == 'troubleshootingMoney') dataObj.troubleshootingMoney = value || 0;
                if (field == 'printingMoney') dataObj.printingMoney = value || 0;
                if (field == 'accommodatioMoney') dataObj.accommodatioMoney = value || 0;
                if (field == 'crossDrivingMoney') dataObj.crossDrivingMoney = value || 0;
                if (field == 'selfDrivingMoney') dataObj.selfDrivingMoney = value || 0;
                if (field == 'otherMoney') dataObj.otherMoney = value || 0;
                if (field == 'clockDesc') dataObj.clockDesc = value || ' ';

                // var clockCaseList = obj.data.clockCaseList
                // var _medicalList = {},_troubleList ={}, _opcTroubleList={}
                // if (clockCaseList.length){
                //     clockCaseList.map(function (cur) {
                //         Object.assign(_medicalList,{[cur.id] : cur.medicalHistoryMoney})
                //         Object.assign(_troubleList,{[cur.id] : cur.troubleshootingMoney})
                //         Object.assign(_opcTroubleList,{[cur.id] : cur.opcTroubleshootingMoney})
                //     })
                // }
                // Object.assign(dataObj,{
                //     medicalList: JSON.stringify(_medicalList),
                //     troubleList: JSON.stringify(_troubleList),
                //     opcTroubleList: JSON.stringify(_opcTroubleList)})

                //请求后台，返回当条数据更新 修改值 及 涉及（实发工资）
                _ajax({
                    url: '${ctx}/fee/operate',
                    type: "post",
                    data: dataObj,
                    success: function (res) {
                        res = JSON.parse(res)
                        var objData = obj.data
                        objData.surveyClockReInfo= res.results
                        if (res.isSuccess) {
                            sessionStorage.setItem('dkjl', JSON.stringify(dkjl))
                            obj.update(objData)
                            layer.msg('更新成功',
                                {
                                    time: 2000,
                                    icon: 1
                                });
                        } else {
                            obj.data.surveyClockReInfo[field] = oldValue
                            obj.update(obj)
                            layer.msg(res.msg, {time: 2000, icon: 2});
                        }
                        $('.layui-table-header').css({
                            'pointer-events': 'auto'
                        })
                        editFlag = false
                    }
                });
            });

            table.on('tool(test)', function (obj) {
                console.log(obj)
                if (obj.event == 'selfDriving') {
                    reasonIndex = layer.open({
                        type: 1,
                        title: '跨地市交通费（自驾)',
                        area: ['400px', '300px'],
                        content: $('#view-or-edit').html(),
                        success: function () {
                            $("input[name=kilometresNum]").val(obj.data.surveyClockReInfo.kilometresNum);
                            $("input[name=selfDrivingMoney]").val(obj.data.surveyClockReInfo.selfDrivingMoney);
                            $("input[name=tollMoney]").val(obj.data.surveyClockReInfo.tollMoney);
                            $('.selfDrive-content input[name=kilometresNum]').on('blur', function (e) {
                                var _this = $(this)
                                if (!checkPapers('money', _this.val())) {
                                    layer.msg('请输入小数点两位内的数字,已格式化', {
                                        time: 1500,
                                        icon: 0
                                    })
                                }
                                var num = Math.round(_this.val() * 100) / 100
                                num = isNaN(num) ? '' : num
                                _this.val(num)
                                $('.selfDrive-content input[name=selfDrivingMoney]').val(Math.round(num * 0.7 * 100) / 100)
                            })
                            $('.selfDrive-content input[name=tollMoney]').on('blur', function (e) {
                                var _this = $(this)
                                if (!checkPapers('money', _this.val())) {
                                    layer.msg('请输入小数点两位内的数字,已格式化', {
                                        time: 1500,
                                        icon: 0
                                    })
                                }
                                var num = Math.round(_this.val() * 100) / 100
                                num = isNaN(num) ? '' : num
                                _this.val(num)
                            })

                            $('.submit-reason').click(function () {
                                $('.selfDrive-content input[name=selfDrivingMoney]').val($('.selfDrive-content input[name=kilometresNum]').val()*0.7);
                                var param = {
                                    operateType:"userClockRe",
                                    clockReId:obj.data.surveyClockReInfo.id,
                                    reInfoId:obj.data.reInfoId,
                                    kilometresNum: $('.selfDrive-content input[name=kilometresNum]').val(),
                                    selfDrivingMoney: $('.selfDrive-content input[name=selfDrivingMoney]').val(),
                                    tollMoney: $('.selfDrive-content input[name=tollMoney]').val()
                                }
                                // var clockCaseList = obj.data.clockCaseList
                                // var _medicalList = {},_troubleList ={}, _opcTroubleList={}
                                // if (clockCaseList.length){
                                //     clockCaseList.map(function (cur) {
                                //         Object.assign(_medicalList,{[cur.id] : cur.medicalHistoryMoney})
                                //         Object.assign(_troubleList,{[cur.id] : cur.troubleshootingMoney})
                                //         Object.assign(_opcTroubleList,{[cur.id] : cur.opcTroubleshootingMoney})
                                //     })
                                // }
                                // Object.assign(param,{
                                //     medicalList: JSON.stringify(_medicalList),
                                //     troubleList: JSON.stringify(_troubleList),
                                //     opcTroubleList: JSON.stringify(_opcTroubleList)})
                                _ajax({
                                    url: '${ctx}/fee/operate',
                                    type: "post",
                                    data: param,
                                    success: function (res) {
                                        res = JSON.parse(res)
                                        var objData = obj.data
                                        objData.surveyClockReInfo= res.results
                                        if (res.isSuccess) {
                                            obj.update (objData)
                                            var sum = Number(objData.surveyClockReInfo.selfDrivingMoney) + Number(objData.surveyClockReInfo.tollMoney)
                                            $(obj.tr[0]).children('td[data-field=selfDrivingMoney]').find('.layui-table-cell').text(Math.round(sum *100) /100)
                                            layer.msg('更新成功',
                                                {
                                                    time: 2000,
                                                    icon: 1
                                                });
                                        }
                                    }
                                });
                                layer.close(reasonIndex)
                            })
                            $('.close-reason').click(function () {
                                layer.close(reasonIndex)
                            })
                        }
                    });
                }else if (obj.event == 'mhm' || obj.event == 'tsm' || obj.event == 'otsm' ) {
                    var _event = obj.event,_title = ''
                    if (_event == 'mhm'){
                        _title = '病史费（含复印费）'
                    }else if (_event == 'tsm'){
                        _title = '住院排查费用'
                    }else if (_event == 'otsm'){
                        _title = '门诊排查费用'
                    }
                    reasonIndex = layer.open({
                        type: 1,
                        title: _title,
                        area: ['400px', '300px'],
                        content: $('#view-or-update').html(),
                        success: function () {
                            var clockCaseList = obj.data.clockCaseList
                            var param = {
                                operateType:"userClockRe",
                                clockReId:obj.data.surveyClockReInfo.id,
                                reInfoId:obj.data.reInfoId,
                                medicalList: {},
                                troubleList:{},
                                opcTroubleList: {}
                            }
                            var _medicalList = {},_troubleList ={}, _opcTroubleList={}

                            var _html = ''

                            if (clockCaseList.length){
                                clockCaseList.map(function (cur) {
                                    var _money = ''
                                    if (_event == 'mhm'){
                                        _money = cur.medicalHistoryMoney
                                    }else if (_event == 'tsm'){
                                        _money = cur.troubleshootingMoney
                                    }else if (_event == 'otsm'){
                                        _money = cur.opcTroubleshootingMoney
                                    }
                                    _html += '<div class="layui-inline">\n' +
                                        '                <label class="layui-form-label">'+cur.surveyPerson+'</label>\n' +
                                        '                <div class="layui-input-inline _input">\n' +
                                        '                    <input type="text" name="money" placeholder="输入金额（元）" autocomplete="off"\n' +
                                        '                           class="layui-input" value="'+_money+'" data-id="'+cur.id+'">\n' +
                                        '                </div>\n' +
                                        '            </div>'
                                    Object.assign(_medicalList,{[cur.id] : cur.medicalHistoryMoney})
                                    Object.assign(_troubleList,{[cur.id] : cur.troubleshootingMoney})
                                    Object.assign(_opcTroubleList,{[cur.id] : cur.opcTroubleshootingMoney})
                                })
                            }
                            $('.update-content').html(_html)
                            $('.update-content input[name=money]').on('blur', function (e) {
                                var _this = $(this)
                                if (!checkPapers('money', _this.val())) {
                                    layer.msg('请输入小数点两位内的数字,已格式化', {
                                        time: 1500,
                                        icon: 0
                                    })
                                }
                                var num = Math.round(_this.val() * 100) / 100
                                num = isNaN(num) ? '' : num
                                _this.val(num)
                            })

                            $('.submit-update').click(function () {
                                if (_event == 'mhm'){
                                    $('.update-content input[name=money]').map(function () {
                                        var _this = $(this)
                                        var _money = _this.val(), _id = _this.attr('data-id')
                                        console.log(_id,_money)
                                        Object.assign(_medicalList,{
                                            [_id]: _money
                                        })

                                    })
                                } else if (_event == 'tsm'){
                                    $('.update-content input[name=money]').map(function () {
                                        var _this = $(this)
                                        var _money = _this.val(), _id = _this.attr('data-id')
                                        Object.assign(_troubleList,{
                                            [_id]: _money
                                        })
                                    })
                                } else if (_event == 'otsm'){
                                    $('.update-content input[name=money]').map(function () {
                                        var _this = $(this)
                                        var _money = _this.val(), _id = _this.attr('data-id')
                                        Object.assign(_opcTroubleList,{
                                            [_id]: _money
                                        })

                                    })
                                }
                                Object.assign(param,{
                                    medicalList: JSON.stringify(_medicalList),
                                    troubleList: JSON.stringify(_troubleList),
                                    opcTroubleList: JSON.stringify(_opcTroubleList)})
                                _ajax({
                                    url: '${ctx}/fee/operate',
                                    type: "post",
                                    data: param,
                                    success: function (res) {
                                        res = JSON.parse(res)
                                        var objData = obj.data
                                        objData= res.results
                                        var  scaseList =  res.results.clockCaseList
                                        if (res.isSuccess) {
                                            obj.update(objData)
                                            var _html1 = '',_html2 = '',_html3 = ''
                                            scaseList.map(function (cur) {
                                                // _html += '<div class="scase"><div class="scase-name">'+cur.surveyPerson + ' </div><div class="scase-money">' +cur.medicalHistoryMoney+';</div></div>'
                                                _html1 += cur.surveyPerson + ': ' +cur.medicalHistoryMoney +'; '
                                                _html2 += cur.surveyPerson + ': ' +cur.troubleshootingMoney +'; '
                                                _html3 += cur.surveyPerson + ': ' +cur.opcTroubleshootingMoney +'; '

                                            })
                                            $(obj.tr[0]).children('td[data-field=medicalHistoryMoney]').find('.layui-table-cell').html(_html1)
                                            $(obj.tr[0]).children('td[data-field=troubleshootingMoney]').find('.layui-table-cell').html(_html2)
                                            $(obj.tr[0]).children('td[data-field=opcTroubleshootingMoney]').find('.layui-table-cell').html(_html3)
                                            layer.msg('更新成功',
                                                {
                                                    time: 2000,
                                                    icon: 1
                                                });
                                        }
                                    }
                                });
                                layer.close(reasonIndex)
                            })
                            $('.submit-close').click(function () {
                                layer.close(reasonIndex)
                            })



                        }
                    });
                }else if (obj.event == 'mhmD' || obj.event == 'tsmD' || obj.event == 'otsmD' ) {
                    var _event = obj.event,_title = ''
                    if (_event == 'mhmD'){
                        _title = '病史费（含复印费）'
                    }else if (_event == 'tsmD'){
                        _title = '住院排查费用'
                    }else if (_event == 'otsmD'){
                        _title = '门诊排查费用'
                    }
                    reasonIndex = layer.open({
                        type: 1,
                        title: _title,
                        area: ['400px', '300px'],
                        content: $('#view-or').html(),
                        success: function () {
                            var clockCaseList = obj.data.clockCaseList
                            var _html = ''

                            if (clockCaseList.length){
                                clockCaseList.map(function (cur) {
                                    var _money = ''
                                    if (_event == 'mhmD'){
                                        _money = cur.medicalHistoryMoney
                                    }else if (_event == 'tsmD'){
                                        _money = cur.troubleshootingMoney
                                    }else if (_event == 'otsmD'){
                                        _money = cur.opcTroubleshootingMoney
                                    }
                                    _html += '<div class="layui-inline">\n' +
                                        '                <label class="layui-form-label">'+cur.surveyPerson+'</label>\n' +
                                        '                <div class="layui-input-inline _input">\n' +
                                        '                    <input type="text" name="money" disabled placeholder="输入金额（元）" autocomplete="off"\n' +
                                        '                           class="layui-input" value="'+_money+'" data-id="'+cur.id+'">\n' +
                                        '                </div>\n' +
                                        '            </div>'
                                })
                            }
                            $('.update-content').html(_html)
                        }
                    });
                }
            })
        }

    });

    function info(invCaseId, orgCaseId, surveyOrgId, surveyInfoId) {
        var orgRole = $("input[name=orgRole]").val();
        var invRole = $("input[name=invRole]").val();
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        if (orgRole == 'true') {
            openDialog({
                frame: true,
                title: "详情",
                height: height,
                width: width,
                url: "${ctx}/survey/case/info?id=" + surveyInfoId + "&menuCode=rg-review-list&assignOrgId=" + orgCaseId + "&curSurveyOrgId=" + surveyOrgId,
                // load: true
            });
            return;
        }
        if (invRole == 'true'){
            openDialog({
                frame: true,
                title: "详情",
                height: height,
                width: width,
                url: "${ctx}/survey/case/sic/info?id=" + invCaseId + "&menuCode=dcy-list",
                // load: true,
            });
            return;
        }
        if (orgRole != 'true' && invRole != 'true'){
            openDialog({
                frame:true,
                title:"",
                height:height,
                width:width,
                url:"${ctx}/survey/case/info?id=" + surveyInfoId + "&menuCode=all-list",
                // load:true
            });
        }
    };

    function formatDate(d, type) {
        if (!d) {
            return ''
        }
        var curDay = new Date(d),
            y = curDay.getFullYear(),
            m = PrefixInteger(curDay.getMonth() + 1, 2),
            d = PrefixInteger(curDay.getDate(), 2),
            hh = PrefixInteger(curDay.getHours(), 2),
            mm = PrefixInteger(curDay.getMinutes(), 2),
            ss = PrefixInteger(curDay.getSeconds(), 2)
        if (type == 1 || !type) {
            return y + '年' + m + '月' + d + '日'
        } else if (type == 2) {
            return y + '-' + m + '-' + d
        } else if (type == 3) {
            return y + '-' + m + '-' + d + ' ' + hh + ':' + mm + ':' + ss
        }
    }

    function PrefixInteger(num, m) {
        return (Array(m).join(0) + num).slice(-m);
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

    var caseClockDetails = function(userId,createTime){
        var url = "${ctx}/fee/list?userId=" + userId + "&createTime=" + createTime+"&menuCode=caseClockDetails";
        parent.parent.parent.addTab("案件打卡足迹",url,true);
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
    var ctx = "${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

</body>
</html>