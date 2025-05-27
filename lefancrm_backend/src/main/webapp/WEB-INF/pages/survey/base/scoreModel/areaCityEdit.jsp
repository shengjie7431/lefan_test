<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <style>
        .container{
            width: 98%;
            margin: 0 auto;
        }
        .areaBtns {
            display: flex;
            flex-wrap: wrap;
        }

        .areaBtns .areaBtn {
            width: 180px;
            height: 40px;
            line-height: 40px;
            border: 1px solid #bbb;
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
            margin-right: 20px;
            cursor: pointer;
        }

        .areaBtns .areaBtn .areaName {
            width: 110px;
            text-align: center;
            display: flex;
        }

        .areaBtns .areaBtn .areaName .name {
            width: 50px;
            display: inline-block;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .areaBtns .areaBtn .areaName.color1 {
            color: #F76D00;
        }

        .areaBtns .areaBtn .areaName.color2 {
            color: #00b340;
        }

        .areaBtns .areaBtn .areaInfo {
            color: #1E9FFF;
        }
        .areaTable {
            margin-bottom: 60px;
            width: 96%;
            padding: 20px 2%;

        }

        @media screen and (max-width: 1100px) {
            .areaTable {
                margin-top:120px;
                margin-bottom: 126px;
            }

        }



        .areaTr {
            width: 100%;
            border-left: 1px solid #bbb;
            display: flex;
        }



        .areaTr .areaKey {
            width: 180px;
            min-width: 180px;
            border-top: 1px solid #bbb;
            border-right: 1px solid #bbb;
            border-bottom: 1px solid #bbb;
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            align-content: center;
        }

        .areaTr .areaKey .areaCity {
            width: 100%;
            padding-left: 30px;
            display: flex;
            align-items: center;
            cursor: pointer;
        }

        .areaTr .areaKey .areaCity .cityBar {
            display: inline-block;
            margin-right: 6px;
            width: 13px;
            height: 13px;
            border: 1px solid #333;
            border-radius: 4px;
            box-sizing: content-box;
        }
        .areaTr .areaKey .areaCity .cityName {
            max-width: 86px;
            display: inline-block;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .areaTr .areaKey .areaCity .cityBar span {
            display: none;
        }

        .areaTr .areaKey .areaCity .cityBar.active span {
            display: inline-block;
            width: 9px;
            height: 9px;
            margin: 2px;
            background-color: #333;
            border-radius: 2px;
        }

        .areaTr .areaKey .areaCity .cityBar.activeAll {
            border: none;
            width: 15px;
            height: 15px;
        }

        .areaTr .areaKey .areaCity .cityBar.activeAll span {
            display: inline-block;
            width: 15px;
            height: 15px;
            background: url('data:image/png;base64,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');
            background-position: center;
            background-repeat: no-repeat;
            background-size: contain;
            border-radius: 10px;
        }
        .checkBox{
            display: inline-block;
            margin-right: 6px;
            width: 13px;
            height: 13px;
            border: 1px solid #333;
            border-radius: 4px;
            box-sizing: content-box;
        }
        .checkBox.active {
            display: inline-block;
            width: 15px;
            height: 15px;
            background: url('data:image/png;base64,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');
            background-position: center;
            background-repeat: no-repeat;
            background-size: contain;
        }

        .areaTr .areaKey .chooseBtns {
            padding-top: 4px;
            width: 100%;
            display: flex;
        }

        .areaTr .areaKey .chooseBtns .chooseBtn {
            margin-left: 12px;
            width: 72px;
            color: #fff;
            background-color: #1E9FFF;
            text-align: center;
            cursor: pointer;
        }

        .areaTr .areaValue {}

        .areaTr .areaValue .chooseBars {
            display: flex;
            flex-wrap: wrap;
        }

        .areaTr .areaValue .chooseBars .chooseBar {
            display: flex;
            align-items: center;
            padding-left: 10px;
            width: 210px;
            height: 58px;
            border-top: 1px solid #bbb;
            border-right: 1px solid #bbb;
            border-bottom: 1px solid #bbb;
            cursor: pointer;
            font-size: 12px;
        }

        .areaTr .areaValue .chooseBars .chooseBar .chooseBarName {
            margin-left: 6px;
            max-width: 86px;
            min-width: 40px;
            display: inline-block;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .areaTr .areaValue .chooseBars .chooseBar .typeName{
            max-width: 120px;
            min-width: 40px;
            display: inline-block;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .header-bar-content{
            width: 96%;
            padding: 4px 2%;
            display: flex;
            align-items: center;
            position: fixed;
            left: 2%;
            top:0;
            background-color: #fff;
        }
        .header-content-title{
            /*padding-left: 50px;*/
            min-width: 80px;
        }
        .header-bar-content .header-bars{
            margin-left: 30px;
            display: flex;
            flex-wrap: wrap;
            align-items: center;
        }
        .header-bar-content .header-bars .header-bar{
            height: 40px;
            line-height: 40px;
            padding:0 20px;
            margin-right: 20px;
            margin-bottom: 10px;
            border:1px solid #eee;
            cursor: pointer;
        }
        .header-bar-content .header-bars .header-bar.active{
            background-color: #3ba9ff;
            color: #fff;
        }
        .c-level{
            width: 18px;
            height: 20px;
            line-height: 20px;
            color: #fff;
            background-color: #3ba9ff;
            border-radius: 4px;
            font-size: 10px;
            text-align: center;
        }
        .city-content{
            position: relative;
        }
        .btn-bottom{
            position: fixed;
            left: 2%;
            bottom: 0;
            width: 96%;
            background-color: #fff;
            text-align: center;
            padding: 10px 0;
        }

        .quick-bars{
            /*margin-left: 30px;*/
            display: flex;
            flex-wrap: wrap;
            align-items: center;
        }
        .quick-bars .quick-bar{
            height: 40px;
            line-height: 40px;
            padding:0 20px;
            margin-right: 20px;
            margin-bottom: 10px;
            border:1px solid #eee;
            cursor: pointer;
        }
        .quick-bars .quick-bar.active{
            background-color: #3ba9ff;
            color: #fff;
        }


        .provinceTable{
            margin: 20px;
            display: flex;
            flex-wrap: wrap;
            align-items: center;
        }
        .provinceTable .provinceBtn{
            width: 140px;
            display: inline-block;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            text-align: center;
            height: 40px;
            line-height: 40px;
            padding:0 20px;
            margin-right: 20px;
            margin-bottom: 10px;
            border:1px solid #eee;
            cursor: pointer;
        }
        .provinceTable .provinceBtn.active{
            background-color: #3ba9ff;
            color: #fff;
        }
        .poi-no{
            pointer-events: none;
        }
    </style>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/baseSurvey/update" method="post">
        <input type="hidden" name="id" value="${scoreModelArea.id}">
        <input type="hidden" name="modelId" value="${modelId}">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <input type="hidden" name="cityIds" value="">
        <input type="hidden" name="fromType" value="${fromType}">

        <div class="form-group">
            <table class="table">
                <tbody>

                <tr>
                    <th width="10%" class="active">区域分值名称</th>
                    <td width="90%">
                        <input type="text" id = "name" name="name" value="${scoreModelArea.name}" class="form-control" required>
                    </td>
                </tr>
                <tr>
                    <th width="10%" class="active">快速选择</th>
                    <td width="90%">
                        <div class="quick-bars">
                            <div class="quick-bar" data-type="1">快速选中全部城市（含省会、地级市、县级市）</div>
                            <div class="quick-bar" data-type="2">快速选中省会（不含省会下属的县级市）</div>
                            <div class="quick-bar" data-type="3">快速选中地级市（不含地级市下属的县级市）</div>
                            <div class="quick-bar" data-type="4">快速选中县级市</div>
                            <div class="quick-bar" data-type="5">清空全部选择</div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th width="10%" class="active">区域</th>
                    <td width="90%">
<%--                        <c:forEach items="${efficiencyModelArea.areaCitys}" var="areaCitys">--%>
<%--                            ${areaCitys.areaName}--%>
<%--                        </c:forEach>--%>
                        <div class="areaBtns">
                            <c:forEach items="${commonAreaList}" var="areaCitys">
                                <div class="areaBtn">
                                    <c:if test="${areaCitys.selectedChildrenNum == areaCitys.allChildrenNum}">
                                        <div class="areaName color2" data-id="${areaCitys.areaId}"><span class="name"> ${areaCitys.areaName}</span>(<span class="num">${areaCitys.selectedChildrenNum}</span>/<span class="sum">${areaCitys.allChildrenNum}</span>) </div>

                                    </c:if>
                                    <c:if test="${areaCitys.selectedChildrenNum < areaCitys.allChildrenNum && (areaCitys.selectedChildrenNum != null && areaCitys.selectedChildrenNum != '')}">
                                        <div class="areaName color1 " data-id="${areaCitys.areaId}"><span class="name"> ${areaCitys.areaName}</span>(<span class="num">${areaCitys.selectedChildrenNum}</span>/<span class="sum">${areaCitys.allChildrenNum}</span>) </div>
                                    </c:if>
                                    <c:if test="${areaCitys.selectedChildrenNum == null || areaCitys.selectedChildrenNum == ''}">
                                        <div class="areaName" data-id="${areaCitys.areaId}"><span class="name"> ${areaCitys.areaName}</span>(<span class="num">${areaCitys.selectedChildrenNum}</span>/<span class="sum">${areaCitys.allChildrenNum}</span>) </div>
                                    </c:if>
                                    <div class="areaInfo" data-id="${areaCitys.areaId}" data-selectId="${areaCitys.selectAreaIds}">详情</div>
                                </div>
                            </c:forEach>
                        </div>

                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
        </div>
    </form>
</div>
<script type="text/html" id="cityContent">
    <div class="city-content">
        <div class="header-bar-content">
            <div class="header-content-title">快速选择</div>
            <div class="header-bars">
                <div class="header-bar" data-type="5">一键选中全部城市</div>
                <div class="header-bar" data-type="4">一键选中省会（不含省会下属县级市）</div>
                <div class="header-bar" data-type="1">一键选中地级市（不含地级市下属县级市）</div>
                <div class="header-bar" data-type="2">一键选中所有县级市</div>
                <div class="header-bar" data-type="3">清空全部选择</div>

            </div>
        </div>
        <div class="areaTable">

        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="text-align: center">
                <button class="layui-btn layui-btn-primary close-reason" style="margin-right: 100px;width: 160px">取消</button>
                <button class="layui-btn layui-btn-normal submit-reason" style="width: 160px">保存并关闭</button>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="provinceContent">
    <div class="city-content">
        <div class="provinceTable">
            <c:forEach items="${commonAreaList}" var="areaCitys">
                <div class="provinceBtn">
                    <div class="areaName" data-id="${areaCitys.areaId}"><span class="name"> ${areaCitys.areaName}</span></div>
                </div>
            </c:forEach>
        </div>
        <div class="layui-form-item btn-bottom">
            <div class="layui-input-block ">
                <button class="layui-btn layui-btn-primary close-province" style="margin-right: 100px;width: 160px">取消</button>
                <button class="layui-btn layui-btn-normal submit-province" style="width: 160px">保存并关闭</button>
            </div>
        </div>
    </div>
</script>

<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>

<script type="text/javascript">
    var idsAll = {},idsObj ={}
    $('body ').on('click','.provinceBtn',function () {
        var _this = $(this)
        if (_this.hasClass('active')) {
            _this.removeClass('active')
        }else{
            _this.addClass('active')

        }

    })
    $('body ').on('click','.header-bar',function () {
        var  _this  = $(this)
        if (!_this.hasClass('active')){
            _this.addClass('active')
            _this.siblings().removeClass('active')
        }
        var type = _this.attr('data-type')
        if (type == 5){
            $('.areaTr .areaKey .areaCity .cityBar').addClass('activeAll activePort')
            $('.areaTr .areaKey .areaCity  .cityBar').addClass('active')
            $('.areaTr .chooseBars .chooseBar .checkBox').addClass('active')
        }else if (type == 4){
            $('.areaTable .areaTr').map(function (i,cur) {
                var _this = $(this)
                _this.find('.chooseBar').map(function (j,item) {
                    if ($(item).attr('data-clevel') != 4 && _this.find('.areaCity .cityName').attr('data-clevel') == '2'){
                        $(item).find('.checkBox').addClass('active')
                    }else {
                        $(item).find('.checkBox').removeClass('active')
                    }
                })
                setActive2(_this, _this.find('.chooseBar').length,
                    _this.find('.checkBox.active').length)


            })
        }else if (type == 1){
            $('.areaTable .areaTr').map(function (i,cur) {
                var _this = $(this)
                _this.find('.chooseBar').map(function (j,item) {
                    if ($(item).attr('data-clevel') != 4 && _this.find('.areaCity .cityName').attr('data-clevel') != '2'){
                        $(item).find('.checkBox').addClass('active')
                    }else {
                        $(item).find('.checkBox').removeClass('active')
                    }
                })
                setActive2(_this, _this.find('.chooseBar').length,
                    _this.find('.checkBox.active').length)

            })

        }else if (type == 2){
            $('.areaTable .areaTr').map(function (i,cur) {
                var _this = $(this)
                _this.find('.chooseBar').map(function (j,item) {
                    if ($(item).attr('data-clevel') == 4){
                        $(item).find('.checkBox').addClass('active')
                    }else {
                        $(item).find('.checkBox').removeClass('active')
                    }
                })
                setActive2(_this, _this.find('.chooseBar').length,
                    _this.find('.checkBox.active').length)
            })

        }else if (type == 3){
            $('.areaTr .areaKey .areaCity .cityBar').removeClass('activeAll activePort')
            $('.areaTr .areaKey .areaCity  .cityBar').removeClass('active')
            $('.areaTr .chooseBars .chooseBar .checkBox').removeClass('active')
        }
    })


    layui.use(['layer','jquery'], function () {
        var layer = layui.layer,
            $ = layui.jquery


        $('.areaBtns .areaInfo').click(function () {
            var areaId = $(this).attr('data-id')
            var _height = $(document).height() * 0.96,
                _width = $(document).width() * 0.96
            layerIndex2 = layer.open({
                type: 1,
                title: '选择区域',
                area: [_width + 'px', _height + 'px'],
                content: $('#cityContent').html(),
                success: function () {
                    $.ajax({
                        url: '${ctx}/baseSurvey/selectInfoByRelationId',
                        type: "POST",
                        data:{
                            areaId:areaId,
                            scoreModelCaId: $("input[name=id]").val(),
                            surveyCode:"commonAreaScore",
                            btnCode:"2000",
                            modelId:$("input[name=modelId]").val()
                        },
                        success: function (res) {
                            res = JSON.parse(res)
                            var _html = ''
                            var idArr = idsObj[areaId] || []
                            idArr = idArr.join(',').split(',')
                            var init =[]
                            res.results.map(function (cur,i) {
                                var num = ''
                                _html +=  '<div class="areaTr" data-index="'+i+'">\n' +
                                    '                                    <div class="areaKey">\n' +
                                    '                                    <div class="areaCity">\n' +
                                    '                                    <div class="cityBar" data-id="'+cur.areaId+'"><span></span> </div><span class="cityName" data-clevel="'+cur.cityType+'" title="'+cur.areaName+'"> \n'+cur.areaName +
                                    '                                </span></div>\n' +
                                    '                                <div class="chooseBtns">\n' +
                                    '                                    <div class="chooseBtn chooseAll">强制全选</div>\n' +
                                    '                                    <div class="chooseBtn chooseSurplus">剩余全选</div>\n' +
                                    '                                    </div>\n' +
                                    '                                    </div>\n' +
                                    '                                    <div class="areaValue"><div class="chooseBars">'

                                cur.childrens.map(function (item) {
                                    var sel = ''
                                    if (item.selected || idArr.indexOf(item.areaId.toString()) > -1){
                                        sel = 'active'
                                        num ++
                                    }
                                    var typeName = item.typeName ? '（'+item.typeName+'）' : ''
                                    var c_level = item.cityType == 4 ? '<span class="c-level">县</span>' : ''
                                    _html += '<div class="chooseBar" data-type="'+typeName+'" data-id="'+item.areaId+'" data-clevel="'+item.cityType+'">\n' +
                                    '                                        <span class="checkBox '+sel+'"></span>'+c_level+' <span\n' +
                                    '                                class="chooseBarName" title="'+item.areaName+'">'+item.areaName+'</span><span class="typeName" title="'+typeName+'">'+typeName+'</span>\n' +
                                    '                                    </div>'

                                })
                                init.push({
                                    num: cur.childrens.length,
                                    selected: num
                                })
                                _html += '</div></div></div>'
                            })
                            $('.areaTable').prepend(_html)
                            init.map(function (cur,i) {
                                if (cur.selected && cur.selected < cur.num){
                                    $('.areaTr').eq(i).find('.areaKey .cityBar').addClass('active')
                                } else if (cur.selected && cur.selected == cur.num){
                                    $('.areaTr').eq(i).find('.areaKey .cityBar').addClass('activeAll')
                                }
                            })

                            $('.chooseBtns').on('click', '.chooseBtn',
                                function () {
                                    var $this = $(this)
                                    if ($this.hasClass('chooseAll')) {
                                        if ($this.parents('.areaTr').find('.areaKey .cityBar').hasClass('activeAll')) {
                                            $this.parents('.areaTr').find('.areaKey .cityBar').removeClass('activeAll')
                                            $this.parents('.areaTr').find('.areaKey .cityBar').removeClass('active')
                                            $this.parents('.areaTr').find('.chooseBars .chooseBar .checkBox').removeClass('active')
                                        } else {
                                            $this.parents('.areaTr').find('.areaKey .cityBar').addClass('activeAll')
                                            $this.parents('.areaTr').find('.areaKey .cityBar').removeClass('active')
                                            $this.parents('.areaTr').find('.chooseBars .chooseBar .checkBox').addClass('active')
                                        }
                                    } else if ($this.hasClass('chooseSurplus')) {
                                        if ($this.parents('.areaTr').find('.areaKey .cityBar').hasClass('activePort')) {
                                            $this.parents('.areaTr').find('.areaKey .cityBar').removeClass('activePort')
                                            $this.parents('.areaTr').find('.chooseBars .chooseBar[data-type=""] .checkBox').removeClass('active')
                                        }else {
                                            $this.parents('.areaTr').find('.areaKey .cityBar').addClass('activePort')
                                            $this.parents('.areaTr').find('.chooseBars .chooseBar[data-type=""] .checkBox').addClass('active')
                                        }
                                        var sumLen = $this.parents('.areaTr').find('.chooseBars .chooseBar .checkBox').length
                                        var len =  $this.parents('.areaTr').find('.chooseBars .chooseBar .checkBox.active').length
                                        setActive($this, sumLen, len)

                                    }
                                })
                            $('.chooseBars').on('click', '.chooseBar',
                                function () {
                                    var $this = $(this)
                                    if ($this.find('.checkBox').hasClass('active')) {
                                        $this.find('.checkBox').removeClass('active')
                                        // if ($this.attr('data-clevel') == 3){
                                        //     $this.siblings().map(function (i,cur) {
                                        //         if ($(cur).attr('data-clevel') == 3){
                                        //             $(cur).find('.checkBox').removeClass('active')
                                        //         }
                                        //     })
                                        // }
                                    } else {
                                        $this.find('.checkBox').addClass('active')
                                        // if ($this.attr('data-clevel') == 3){
                                        //     $this.siblings().map(function (i,cur) {
                                        //         if ($(cur).attr('data-clevel') == 3){
                                        //             $(cur).find('.checkBox').addClass('active')
                                        //         }
                                        //     })
                                        // }
                                    }
                                    setActive($this, $this.parent().find('.chooseBar').length,
                                        $this.parent().find('.checkBox.active').length)
                                })


                        }
                    });
                    function setActive($this, len1, len2) {
                        if (len1 == len2) {
                            $this.parents('.areaTr').find('.areaKey .cityBar').addClass('activeAll')
                            $this.parents('.areaTr').find('.areaKey .cityBar').removeClass('active')
                        } else if (len2) {
                            $this.parents('.areaTr').find('.areaKey .cityBar').addClass('active')
                            $this.parents('.areaTr').find('.areaKey .cityBar').removeClass('activeAll')
                        } else {
                            $this.parents('.areaTr').find('.areaKey .cityBar').removeClass('active')
                            $this.parents('.areaTr').find('.areaKey .cityBar').removeClass('activeAll')
                        }
                    }
                    $('.submit-reason').click(
                        function () {
                            var ids = [],num = 0

                            $('.chooseBar .checkBox').map(function(i,cur){
                                    if ($(cur).hasClass('active')){
                                        ids.push($(cur).parent().attr('data-id'))
                                    }
                            })
                            $('.areaCity .cityBar').map(function (i,cur) {
                                if ($(cur).hasClass('active') || $(cur).hasClass('activeAll')){
                                    num ++
                                    var chooseBars = $(cur).parents('.areaKey').siblings('.areaValue').find('.chooseBar[data-clevel=3]')
                                    var len = chooseBars.length
                                    var clen = 0
                                    chooseBars.map(function (j,bar) {
                                        if ($(bar).find('.checkBox').hasClass('active')){
                                            clen++
                                        }

                                    })
                                    if (len == clen){
                                        ids.push($(cur).attr('data-id'))
                                    }
                                }
                            })
                            if (Number($('.areaName[data-id='+areaId+'] .sum').text()) == num && num){
                                $('.areaName[data-id='+areaId+']').removeClass('color1')
                                $('.areaName[data-id='+areaId+']').addClass('color2')
                            }else if (Number($('.areaName[data-id='+areaId+'] .sum').text()) > num && num){
                                $('.areaName[data-id='+areaId+']').addClass('color1')
                                $('.areaName[data-id='+areaId+']').removeClass('color2')
                            }else if (num == 0){
                                $('.areaName[data-id='+areaId+']').removeClass('color1')
                                $('.areaName[data-id='+areaId+']').removeClass('color2')

                            }

                            console.log(idsObj, {[areaId]: ids})
                            $('.areaName[data-id='+areaId+'] .num').text(num)
                            Object.assign(idsObj, {[areaId]: ids})
                            layer.close(layerIndex2)
                        })
                    $('.close-reason').click(
                        function () {
                            layer.close(layerIndex2)
                        })


                }
            });
        })


        $('body ').on('click','.quick-bar',function () {
            var _this = $(this)
            if (!_this.hasClass('active')) {
                _this.addClass('active')
                _this.siblings().removeClass('active')
            }
            var type = _this.attr('data-type')
            if (type == 5){
                layer.confirm('确定操作？', {
                    offset: 't',
                    btn: ['确定','取消'] //按钮
                }, function(){
                    getIds({
                        surveyCode:'getAreaIdList',
                        btnCode: type,
                        cityIds: ''
                    })
                    layer.closeAll()
                }, function(){

                });

            }else{
                var _height = $(document).height() * 0.96,
                    _width = $(document).width() * 0.96
                layerIndex2 = layer.open({
                    type: 1,
                    title: '快速选择',
                    area: [_width + 'px', _height + 'px'],
                    content: $('#provinceContent').html(),
                    success: function () {
                        console.log(idsObj)
                        $('.submit-province').click(
                            function () {
                                $('.submit-province').addClass('poi-no')
                                setTimeout(function () {
                                    $('.submit-province').removeClass('poi-no')
                                },2000)
                                var ids = []
                                $('.provinceBtn').map(function(i,cur){
                                    if ($(this).hasClass('active')){
                                        ids.push($(this).find('.areaName').attr('data-id'))
                                    }
                                })
                                if (!ids.length){
                                    layer.msg('请选择省份！', {
                                        icon: 5
                                    })
                                    return;
                                }
                                console.log(ids)

                                getIds({
                                    surveyCode:'getAreaIdList',
                                    btnCode: type,
                                    cityIds: ids.join(',')
                                })
                                layer.close(layerIndex2)
                            })
                        $('.close-province').click(
                            function () {
                                layer.close(layerIndex2)
                            })
                    }
                })
            }
        })

        function getIds(param){
            $.ajax({
                url:'${ctx}/baseSurvey/selectInfoByRelationId',
                data: param,
                success: function (res) {
                    res = JSON.parse(res)
                    if (res.isSuccess){
                        if (param.btnCode == 5){
                            idsObj = {}
                            $('.areaInfo').map(function (i,cur) {
                                $(cur).attr('data-selectId','')
                            })
                            $('.areaBtn .areaName .num').html('0')
                            $('.areaBtn .areaName').removeClass('color1 color2')
                        }else {
                            var _idsObj = res.results
                            for(let key  in _idsObj){
                                var _ids = _idsObj[key]
                                if (_ids.k1.length){
                                    Object.assign(idsObj,{
                                        [key]: _ids.k1
                                    })
                                    var _sum =$('.areaBtn .areaName[data-id="'+key+'"] .sum').text()
                                    if (Number(_ids.k2) < Number(_sum)){
                                        $('.areaBtn .areaName[data-id="'+key+'"]').addClass('color1').removeClass('color2')
                                    }else if (Number(_ids.k2) == Number(_sum)){
                                        $('.areaBtn .areaName[data-id="'+key+'"]').addClass('color2').removeClass('color1')
                                    }
                                    $('.areaBtn .areaName[data-id="'+key+'"] .num').html(_ids.k2)
                                }else {
                                    $('.areaBtn .areaName[data-id="'+key+'"]').removeClass('color1 color2')
                                    $('.areaBtn .areaName[data-id="'+key+'"] .num').html('0')
                                }

                            }

                        }
                    }
                }
            })
        }
    })

    function setActive2($this, len1, len2) {
        if (len1 == len2) {
            $this.find('.areaKey .cityBar').addClass('activeAll')
            $this.find('.areaKey .cityBar').removeClass('active')
        } else if (len2) {
            $this.find('.areaKey .cityBar').addClass('active')
            $this.find('.areaKey .cityBar').removeClass('activeAll')
        } else {
            $this.find('.areaKey .cityBar').removeClass('active')
            $this.find('.areaKey .cityBar').removeClass('activeAll')
        }
    }
    
    var editor1;
    KindEditor.ready(function(K) {
         editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        $('.btn-success').attr("disabled",true);
        var newIds = []
        $('.areaInfo').map(function (i,cur) {
            var areaId = $(cur).attr('data-id'),
                selectedIds = $(cur).attr('data-selectId')
            if (areaId in idsObj){
                newIds = newIds.concat(idsObj[areaId])
            }else {
                selectedIds = selectedIds ? selectedIds.split(',') : []
                newIds = newIds.concat(selectedIds)
            }

        })

        $('input[name=cityIds]').val(newIds.join(','))
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

    /**
     * 关闭dialog
     */
    function closeDialog(){
        var closeBtn = $("#diglog_close_btn");
        if(closeBtn.size() == 0){
            closeBtn = $("#diglog_close_btn",window.parent.document);
        }
        closeBtn.click();
    }
    function info(areaId) {

    }
</script>
</body>
</html>