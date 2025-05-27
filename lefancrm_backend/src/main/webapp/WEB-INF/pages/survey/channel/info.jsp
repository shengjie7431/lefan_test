<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/layui/layui_exts/soulTable.css">
    <style>
        .main {
            width: 800px;
            margin: 40px auto
        }

        .chooseCell {
            width: 100%;
        }

        .add {
            display: none;
            margin-left: 10px;
            width: 90px;
            height: 36px;
            line-height: 36px;
            border: 1px solid #eee;
            text-align: center;
            cursor: pointer;
        }

        .add.active,
        .delete.active {
            display: block
        }

        .delete {
            display: none;
            margin-left: 10px;
            width: 90px;
            height: 36px;
            line-height: 36px;
            border: 1px solid #eee;
            text-align: center;
            cursor: pointer;
        }

        .selectMul,.layui-input, .layui-textarea {
            width: 500px;
        }

        .layui-inline {
            width: 750px;
            padding: 10px 0;
            margin: 0 auto;
        }

        .layui-form-label {
            width: 90px;
        }

        .layui-input-inline {
            width: 600px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }
        .lf-btns{
            width: 100%;
            display: flex;
        }
        .lf-btns .lf-btn{
            width: 160px;
            height: 40px;
            line-height: 40px;
            border:1px solid #3BA9FF;
            color: #3BA9FF;
            text-align: center;
            margin-right: 20px;
            cursor: pointer;
        }
        .lf-btns .lf-btn.active{
            background-color:#3BA9FF;
            color: #fff;
        }
        .poi-none{
            pointer-events: none;
        }
        .display-none{
            display: none!important;
        }
    </style>
</head>
<body>
<div class="main">
    <div class="relevanceCells">
        <c:if test="${dto.id != null &&  dto.state != 1}">
            <div class="layui-inline relevanceCell selected" style="margin-right: 20px;" data-id="1">
                <label class="layui-form-label">关联案件编号</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="demoo1" value=""
                           placeholder="" readonly data-id="">
                        <div class="delete active">删除案件</div>
                </div>
            </div>
            <div class="layui-inline relevanceCell selected" style="margin-right: 20px;" data-id="1">
                <label class="layui-form-label">关联方向</label>
                <div class="layui-input-inline">
                    <div id="demoo2" class="selectMul"></div>
                </div>
            </div>
        </c:if>
        <c:if test="${dto.id == null || dto.state == 1}">
            <div class="layui-inline relevanceCell" style="margin-right: 20px;" data-id="1">
                <label class="layui-form-label">关联案件编号</label>
                <div class="layui-input-inline">
                    <div id="demoo1" class="selectMul"></div>
                        <%--<div class="add active">添加案件</div>--%>
                </div>
            </div>
        </c:if>
        <%--<div class="layui-inline relevanceCell" style="margin-right: 20px;" data-id="1">--%>
            <%--<label class="layui-form-label">关联案件编号</label>--%>
            <%--<div class="layui-input-inline">--%>
                <%--<div id="demoo1" class="selectMul"></div>--%>
                <%--&lt;%&ndash;<div class="add active">添加案件</div>&ndash;%&gt;--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="layui-inline relevanceCell" style="margin-right: 20px;" data-id="1">--%>
            <%--<label class="layui-form-label">关联方向</label>--%>
            <%--<div class="layui-input-inline">--%>
                <%--<div id="demoo2" class="selectMul"></div>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>
    <div class="layui-inline" style="margin-right: 20px;" data-id="1">
        <label class="layui-form-label">渠道金额</label>
        <div class="layui-input-inline" style="justify-content: normal">
            <input type="number" class="layui-input" name="chnannelMoney" value="${dto.chnannelMoney}"
                   placeholder="" style="margin-right: 10px">元
        </div>
    </div>
    <div class="layui-inline " style="margin-right: 20px;" data-id="1">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-inline">
            <textarea id="" class="layui-textarea" name="channelDesc" cols="30" rows="5">${dto.channelDesc}</textarea>
        </div>
    </div>
    <div class="layui-inline " style="margin-right: 20px;" data-id="1">
        <label class="layui-form-label">收款人姓名</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" name="payeeUserName" value="${dto.payeeUserName}"
                   placeholder="" required>
        </div>
    </div>
    <div class="layui-inline " style="margin-right: 20px;" data-id="1">
        <label class="layui-form-label">开户行</label>
        <div class="layui-input-inline">
            <div id="demoBank" class="selectMul"></div>
        </div>
    </div>
    <div class="layui-inline " style="margin-right: 20px;" data-id="1">
        <label class="layui-form-label">支行</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" name="bankBranch" value="${dto.bankBranch}"
                   placeholder="">
        </div>
    </div>
    <div class="layui-inline " style="margin-right: 20px;" data-id="1">
        <label class="layui-form-label">银行账号</label>
        <div class="layui-input-inline">
            <input type="number" class="layui-input" name="bankNo" value="${dto.bankNo}"
                   placeholder="">
        </div>
    </div>
    <div class="rejectionType lf-btns layui-inline" style="justify-content: center">
        <div class="lf-btn " data-type="1">取消</div>
        <div class="lf-btn active" data-type="2">提交审核</div>
    </div>
    <input type="hidden" name="bankDeposit" value="${dto.bankDeposit}">
    <input type="hidden" name="channelCases" value='${dto.surveyChannelCasesJson}'>
    <input type="hidden" name="id" id="id" value='${dto.id}'>
    <input type="hidden" name="state" id="state" value='${dto.state}'>
</div>

<script src="${ctx}/js/jquery-3.4.1.js" charset="utf-8"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })
    var caseList = []
    layui.use(['xmSelect', 'form','layer'], function () {
        var xmSelect = layui.xmSelect,
            form = layui.form,
            layer = layui.layer


         demo1 = xmSelect.render({
            el: '#demoo1',
            // radio: true,
            // clickClose: true,
            filterable: true,
            filterDone: function (val, list) {
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
                    }
                }
            },
            on: function(data){
                //arr:  当前多选已选中的数据
                var arr = data.arr;
                // if (arr.length){
                //     getDec(arr[0].value,demo2)
                // }else {
                //     demo2.setValue([])
                //     demo2.update({
                //         data: []
                //     })
                // }
            },
            data: []
        })
         demo2 = xmSelect.render({
            el: '#demoo2',
            filterable: true,
            filterDone: function (val, list) {
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
                        template(data, sels) {
                            var _html = ''
                            sels.filter(function (cur) {
                                _html += '<div class="xm-label-block lf-select-block">' +
                                    cur.name + '</div>'
                            })
                            return _html
                        }
                    }
                }
            },
            data: []
        })
        var demoBank = xmSelect.render({
            el: '#demoBank',
            radio: true,
            clickClose: true,
            filterable: true,
            filterDone: function (val, list) {
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
                        template(data, sels) {
                            var _html = ''
                            sels.filter(function (cur) {
                                _html += '<div class="xm-label-block lf-select-block">' +
                                    cur.name + '</div>'
                            })
                            return _html
                        }
                    }
                }
            },
            data: []
        })


        var channelCases = $('input[name=channelCases]').val()
        channelCases = JSON.parse(channelCases);
        console.log(channelCases)
        $.ajax({
            url: '${ctx}/survey/channel/ajaxData',
            data: {
               dataType: 'cases'
            },
            success: function (res) {
                res = JSON.parse(res)
                if (res.isSuccess){
                    caseList = res.results
                    filterJson(demo1, res.results,'surveyInfoId','surveyCaseNo',false, false)
                    if ($("#state").val() == 1) {
                        setChannelCases(res.results)
                    }else if (channelCases){
                        console.log(123)
                        setChannelCases2(res.results)
                    }
                }
            }

        })
        $.ajax({
            url: '${ctx}/survey/channel/ajaxData',
            data: {
                dataType: 'bank'
            },
            success: function (res) {
                res = JSON.parse(res)
                if (res.isSuccess){
                    caseList = res.results
                    filterJson(demoBank, res.results,'bankName','bankName',false, false)
                    demoBank.setValue([$('input[name=bankDeposit]').val()])
                    // demoBank.setvalue([$('input[name=bankDeposit]').val()])

                }
            }

        })

        function setChannelCases(caseList) {
            var len = channelCases.length
            var sIds = []
            channelCases.map(function (cur) {
                sIds.push(cur.surveyInfoId)
            })
            demo1.setValue(sIds)
            filterJson(demo2, channelCases[0].allDirections,'surveyDirectionId','surveyDirectionName',false, false)
            demo2.setValue(channelCases[0].directionsStr.split(','))
            if (len == 1){
                return;
            }
            for(var i=1;i<len;i++){
                var curId = i + 1
                var class1 = 'demo' + (2*i + 1),
                    class2 = 'demo' + (2*i + 2)
                var class11 = 'demoo' + (2*i + 1),
                    class22= 'demoo' + (2*i + 2)

                var deleteActive = '',addActive = ''
                if (i == len - 1){
                    deleteActive = 'active'
                }
                if (i == len - 2){
                    addActive = 'active'
                    $('.relevanceCell:first .add').removeClass('active')
                }

                var _html =
                    '<div class="layui-inline relevanceCell" style="margin-right: 20px;" data-id="' +
                    curId +
                    '"><label class="layui-form-label">关联案件编号</label><div class="layui-input-inline"><div id="' +
                    class11 +
                    '" class="selectMul"></div><div class="add '+addActive+'">添加案件</div><div class="delete '+deleteActive+'">删除案件</div></div></div><div class="layui-inline relevanceCell" style="margin-right: 20px;" data-id="' +
                    curId +
                    '"><label class="layui-form-label">关联方向</label><div class="layui-input-inline"><div id="' +
                    class22 + '" class="selectMul"></div></div></div>'
                $('.relevanceCells').append(_html)
                window[class1] = xmSelect.render({
                    el: '#' + class11,
                    radio: true,
                    clickClose: true,
                    filterable: true,
                    filterDone: function (val, list) {
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
                                template(data, sels) {
                                    var _html = ''
                                    sels.filter(function (cur) {
                                        _html +=
                                            '<div class="xm-label-block lf-select-block">' +
                                            cur.name + '</div>'
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
                            getDec(arr[0].value, window[class2])
                        }else {
                            window[class2].setValue([])
                            window[class2].update({
                                data: []
                            })
                        }
                    },
                    data: filterJson(window[class1], caseList,'surveyInfoId','surveyCaseNo',true, false)
                })

                window[class2] = xmSelect.render({
                    el: '#' + class22,
                    filterable: true,
                    filterDone: function (val, list) {
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
                                template(data, sels) {
                                    var _html = ''
                                    sels.filter(function (cur) {
                                        _html +=
                                            '<div class="xm-label-block lf-select-block">' +
                                            cur.name + '</div>'
                                    })
                                    return _html
                                }
                            }
                        }
                    },
                    data: filterJson(window[class2], channelCases[i].allDirections,'surveyDirectionId','surveyDirectionName',true, false)
                })

                window[class1].setValue([channelCases[i].surveyInfoId])
                window[class2].setValue(channelCases[i].directionsStr.split(','))
            }
        }

        function setChannelCases2(caseList) {
            var len = channelCases.length
            $('#demoo1').val([channelCases[0].surveyNo])
            $('#demoo1').attr('data-id',[channelCases[0].surveyInfoId])

            filterJson(demo2, channelCases[0].allDirections,'surveyDirectionId','surveyDirectionName',false, false)
            var selD = []
            if (channelCases[0].selDirections.length){
                channelCases[0].selDirections.map(function (cur) {
                    selD.push(cur.surveyDirectionId)
                })
            }
            demo2.setValue(selD)
            if (len == 1){
                return;
            }
            for(var i=1;i<len;i++){
                var curId = i + 1
                var class1 = 'demo' + (2*i + 1),
                    class2 = 'demo' + (2*i + 2)
                var class11 = 'demoo' + (2*i + 1),
                    class22= 'demoo' + (2*i + 2)

                var del = '<div class="delete active">删除案件</div>'
                var _html =
                    '<div class="layui-inline relevanceCell selected" style="margin-right: 20px;" data-id="' +
                    curId +
                    '"><label class="layui-form-label">关联案件编号</label><div class="layui-input-inline"><input title="'+channelCases[i].surveyNo+'" value="'+channelCases[i].surveyNo+'" id="'+class11+'" data-id="'+channelCases[i].surveyInfoId+'" readonly class="layui-input">'+del+'</div></div><div class="layui-inline relevanceCell selected" style="margin-right: 20px;" data-id="' +
                    curId +
                    '"><label class="layui-form-label">关联方向</label><div class="layui-input-inline"><div id="' +
                    class22 + '" class="selectMul"></div></div></div>'
                $('.relevanceCells').append(_html)

                window[class2] = xmSelect.render({
                    el: '#' + class22,
                    filterable: true,
                    filterDone: function (val, list) {
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
                                template(data, sels) {
                                    var _html = ''
                                    sels.filter(function (cur) {
                                        _html +=
                                            '<div class="xm-label-block lf-select-block">' +
                                            cur.name + '</div>'
                                    })
                                    return _html
                                }
                            }
                        }
                    },
                    data: filterJson(window[class2], channelCases[i].allDirections,'surveyDirectionId','surveyDirectionName',true, false)
                })

                var selDi = []
                if (channelCases[i].selDirections.length){
                    channelCases[i].selDirections.map(function (cur) {
                        selDi.push(cur.surveyDirectionId)
                    })
                }
                window[class2].setValue(selDi)
            }
        }
        function getDec(surveyInfoId,demo){
            $.ajax({
                url: '${ctx}/survey/channel/ajaxData',
                data: {
                    dataType: 'directions',
                    surveyInfoId: surveyInfoId
                },
                success: function (res) {
                    res = JSON.parse(res)
                    if (res.isSuccess){
                        filterJson(demo, res.results,'id','directionName',false, false)
                    }
                }

            })
        }
        $('body').on('click', '.add', function () {
            var _this = $(this)
            var id = $('.relevanceCell:last').attr('data-id')
            var curId = Number(id) + 1
            var len = $('.relevanceCell').length
            var class1 = 'demo' + (len + 1),
                class2 = 'demo' + (len + 2)
            var class11 = 'demoo' + (len + 1),
                class22= 'demoo' + (len + 2)
            if (!$('.relevanceCell').eq(len - 2).find('.add').hasClass('active')) {
                $('.relevanceCell').eq(len - 2).find('.add').addClass('active')
                $('.relevanceCell').eq(len - 2).siblings().find('.add').removeClass('active')
            }
            $('.relevanceCell .delete').removeClass('active')

            var _html =
                '<div class="layui-inline relevanceCell" style="margin-right: 20px;" data-id="' +
                curId +
                '"><label class="layui-form-label">关联案件编号</label><div class="layui-input-inline"><div id="' +
                class11 +
                '" class="selectMul"></div><div class="add">添加案件</div><div class="delete active">删除案件</div></div></div><div class="layui-inline relevanceCell" style="margin-right: 20px;" data-id="' +
                curId +
                '"><label class="layui-form-label">关联方向</label><div class="layui-input-inline"><div id="' +
                class22 + '" class="selectMul"></div></div></div>'
            $('.relevanceCells').append(_html)
            window[class1] = xmSelect.render({
                el: '#' + class11,
                radio: true,
                clickClose: true,
                filterable: true,
                filterDone: function (val, list) {
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
                            template(data, sels) {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' +
                                        cur.name + '</div>'
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
                        getDec(arr[0].value, window[class2])
                    }else {
                        window[class2].setValue([])
                        window[class2].update({
                            data: []
                        })
                    }
                },
                data: filterJson(window[class2], caseList,'surveyInfoId','surveyCaseNo',true, false)
            })

            window[class2] = xmSelect.render({
                el: '#' + class22,
                filterable: true,
                filterDone: function (val, list) {
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
                            template(data, sels) {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' +
                                        cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    }
                },
                data: []
            })
        })
        $('body').on('click', '.delete', function () {
            var _this = $(this)
            if ($('.relevanceCell.selected').length == 2){
                layer.msg('至少留一个，不可删除！',{
                    time: 2000,icon: 5
                })
                return;
            }
            var id = _this.parents('.relevanceCell').attr('data-id')
            $('.relevanceCell[data-id="' + id + '"]').addClass('display-none')
            $('.relevanceCell[data-id="' + id + '"]').removeClass('selected')

            // var _this = $(this)
            // var id = _this.parents('.relevanceCell').attr('data-id')
            // if (id != 2) {
            //     $('.relevanceCell[data-id="'+(id-1)+'"]').find('.delete').addClass('active')
            //     $('.relevanceCell[data-id="'+(id-1)+'"]').find('.add').removeClass('active')
            //     $('.relevanceCell[data-id="'+(id-2)+'"]').find('.add').addClass('active')
            // }
            // $('.relevanceCell[data-id="' + id + '"]').detach()

            // var len = $('.relevanceCell').length
            // if (len != 4) {
            //     $('.relevanceCell').eq(len - 2).find('.delete').addClass('active')
            //     $('.relevanceCell').eq(len - 2).find('.add').removeClass('active')
            //     $('.relevanceCell').eq(len - 4).find('.add').addClass('active')
            // }
            // $('.relevanceCell').eq(len - 1).detach()
            // $('.relevanceCell').eq(len).detach()

        })
        $('.lf-btn').click(function () {
            var _id = $('#id').val(),_state = $('#state').val()
            var _this = $(this)
            if (_this.hasClass('active')){
                var params ={
                    bankDeposit: demoBank.getValue('nameStr'),
                    chnannelMoney: $('input[name=chnannelMoney]').val(),
                    channelDesc: $('textarea[name=channelDesc]').val(),
                    payeeUserName: $('input[name=payeeUserName]').val(),
                    bankBranch:$('input[name=bankBranch]').val(),
                    bankNo:$('input[name=bankNo]').val(),
                }

                var arrFlag = []

                var len  = $('.relevanceCell').length
                var sumLen = 0,sumActive = 0

                var channelCases = [],caseIds=[]
                for(var i=1;i<len +1;i++){
                    if (i % 2 == 0){
                        var demoBName = 'demo'+ i
                        var demoOName = 'demoo'+ Number(i -1)
                        var ids = window[demoBName].getValue('value')
                        var names = window[demoBName].getValue('name')
                        var caseNo = window['demo'+ (i-1)]
                            // caseIds.push(caseNo.getValue('valueStr'))
                        if (!$('#'+demoOName).parents('.relevanceCell').hasClass('display-none')){
                            sumLen ++
                        }
                        var flagS = false
                        if (ids.length && !$('#'+demoOName).parents('.relevanceCell').hasClass('display-none')){
                            sumActive ++
                            ids.map(function (cur,i) {
                                    channelCases.push({
                                        surveyInfoId: $('#'+demoOName).attr('data-id'),
                                        surveyNo: $('#'+demoOName).val(),
                                        surveyDirectionId: cur,
                                        surveyDirectionName: names[i]
                                    })
                            })
                        }

                    }
                }
                if(!_id){
                    Object.assign(params,{
                        surveyCaseNos: demo1.getValue('nameStr'),
                        surveyInfoIds: demo1.getValue('valueStr'),
                        btnCode: 'first-commit',
                    })
                    if (!params.surveyInfoIds){
                        layer.msg('请选择关联案件编号！',{
                            time: 2000,icon: 5
                        })
                        return ;
                    }
                }else  if( _state == 1){
                    Object.assign(params,{
                        surveyInfoIds: demo1.getValue('valueStr'),
                        btnCode: 'first-commit',
                        id: $('#id').val()
                    })
                    if (!params.surveyInfoIds){
                        layer.msg('请选择关联案件编号！',{
                            time: 2000,icon: 5
                        })
                        return ;
                    }
                }else if(_id && _state != 1){
                    if (sumActive != sumLen){
                        layer.msg('请关联案件编号、方向！',{
                            time: 2000,icon: 5
                        })
                        return ;
                    }
                    Object.assign(params,{
                        btnCode: 'app',
                        id: $("#id").val(),
                        channelCases: JSON.stringify(channelCases)})

                }
                console.log('params ',params)

                if (!checkPapers('money',params.chnannelMoney)){
                    layer.msg('渠道金额请输入两位小数点内数字！',{
                        time: 2000,icon: 5
                    })
                    return ;
                }
                if (!params.payeeUserName){
                    layer.msg('请输入收款人姓名！',{
                        time: 2000,icon: 5
                    })
                    return ;
                }
                if (!params.bankDeposit){
                    layer.msg('请选择开户行！',{
                        time: 2000,icon: 5
                    })
                    return ;
                }
                if (!params.bankBranch){
                    layer.msg('请输入支行名称！',{
                        time: 2000,icon: 5
                    })
                    return ;
                }
                if (!params.bankNo){
                    layer.msg('请输入银行帐号',{
                        time: 2000,icon: 5
                    })
                    return ;
                }
                $('.lf-btn').addClass('poi-none')
                $.ajax({
                    url: '${ctx}//survey/channel/operate',
                    data: params,
                    success: function (res) {
                        res = JSON.parse(res)
                        if (res.isSuccess){
                            layer.msg('操作成功',{
                                time: 1500,
                                icon: 1
                            },function(){
                                $('.lf-btn').removeClass('poi-none')
                                parent.reload();
                            })
                        }
                    }

                })

            }else{
                //关闭页面
                parent.reload();
                // var closeBtn = $("#diglog_close_btn",window.parent.document);
                // closeBtn.click();
            }
        })
        function isRepeat(ary) {
            var ary =ary ;
            var nary = ary.sort();
            for(var i = 0; i < nary.length - 1; i++) {
                if(nary[i] == nary[i + 1]) {
                   return true
                }
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

        //正则匹配
        var checkPapers = function (parama, paramb) {
            var map = new Map([
                ['phone', /^[1][3,4,5,7,8,9][0-9]{9}$/],
                ['passport', !/^((1[45]\d{7})|(G\d{8})|(P\d{7})|(S\d{7,8}))?$/],
                ['identity', /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/],
                ['money', /^[0-9]+(\.[0-9]{1,2})?$/],
                ['moneyorMinus', /^(\-|\+)?\d+(\.\d{1,2})?$/],
                ['moneyor4', /^(\-|\+)?\d+(\.\d{1,4})?$/],
                ['email',/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/],
                ['num_0_1',/^(0+(\.[0-9]{1,2})?|1|1.0|1.00?)$/]
            ])
            if (map.get(parama).test(paramb)) {
                return true
            } else {
                return false
            }
        }

    })
</script>
</body>
</html>
