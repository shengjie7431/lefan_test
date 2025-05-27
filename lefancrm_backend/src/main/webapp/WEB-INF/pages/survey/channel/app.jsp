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
            width: 560px;
        }

        .layui-inline {
            width: 810px;
            padding: 10px 0;
            margin: 0 auto;
        }

        .layui-form-label {
            width: 90px;
        }

        .layui-input-inline {
            width: 660px;
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
        .directions {
            background-color: #FFF;
            position: relative;
            border: 1px solid #E6E6E6;
            border-radius: 2px;
            display: block;
            width: 540px;
            padding: 0 10px;
            height: 32px;
            line-height: 32px;
            cursor: pointer;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .selectBar {
             display: none;
            border: 1px solid #E6E6E6;
        }

        .search,
        .barList {
            width: 96%;
            min-height: 36px;
            margin: 0 auto;
        }

        .searchStr {
            margin-top: 6px;
            background-color: #FFF;
            position: relative;
            border: 1px solid #E6E6E6;
            border-radius: 2px;
            display: block;
            width: 94%;
            padding: 0 3%;
            height: 28px;
            line-height: 28px;
            cursor: pointer;
        }

        .barLi {
            width: 96%;
            padding: 5px 2%;
            display: flex;
            align-items: center;
            position: relative;
        }
        .barLiNone{
            color: #999;
        }
        .display-none{
            display: none;
        }

        .li-content{
            width: 264px;
            display: flex;
            align-items: center;
            cursor: pointer;
        }
        .liCheckBox{
            width: 13px;
            height: 13px;
            border: 1px solid #919191;
            border-radius: 2px;
        }
        .liCheckBox.active{
            background: url(data:image/png;base64,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);
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
        }
        .liName {
            margin: 0 5px;
            width: 244px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .liPrice {
            display: none;
            margin: 0 2px;
            padding-left: 5px;
            width: 85px;
            height: 30px;
            line-height: 30px;
            border: 1px solid #E6E6E6;
        }
        .unit{
            display: none;
            position: absolute;
            right: 182px;
            top: 11px;
        }
        .liDesc {
            display: none;
            margin: 0 2px;
            padding-left: 5px;
            width: 160px;
            height: 30px;
            line-height: 30px;
            border: 1px solid #E6E6E6;
        }
        .display-b{
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="main">
    <div class="relevanceCells">
        <div class="layui-inline relevanceCell cellCase selected" style="margin-right: 20px;" data-id="1">
            <label class="layui-form-label">关联案件编号</label>
            <div class="layui-input-inline">
                <div id="demoo1" class="selectMul"></div>
                <div class="add active">新增案件</div>
            </div>
        </div>
        <div class="layui-inline relevanceCell cellDirec selected" style="margin-right: 20px;" data-id="1">
            <label class="layui-form-label">关联方向</label>
            <div class="layui-input-inline">
                <div class="selectMul directions"></div>
            </div>
            <div class="layui-input-inline">
                <div class="selectMul selectBar ">
                    <div class="search">
                        <input type="text" class="searchStr">
                    </div>
                    <div class="barList">
                        <div class="barLi barLiNone" >
                         暂无数据
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--<div class="layui-inline" style="margin-right: 20px;" data-id="1">--%>
        <%--<label class="layui-form-label">渠道金额</label>--%>
        <%--<div class="layui-input-inline" style="justify-content: normal">--%>
            <%--<input type="number" class="layui-input" name="chnannelMoney" value="${dto.chnannelMoney}"--%>
                   <%--placeholder="" style="margin-right: 10px">元--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="layui-inline " style="margin-right: 20px;" data-id="1">--%>
        <%--<label class="layui-form-label">备注</label>--%>
        <%--<div class="layui-input-inline">--%>
            <%--<textarea id="" class="layui-textarea" name="channelDesc" cols="30" rows="5">${dto.channelDesc}</textarea>--%>
        <%--</div>--%>
    <%--</div>--%>
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

        $('body').on('click','.directions',function () {
            var _this = $(this)
            var _index = $('.directions').index(_this)
            $('.selectBar').map(function (i,cur) {
                if (i == _index){
                    if ($(cur).attr('style')) {
                        $(cur).removeAttr('style')
                    } else {
                        $(cur).show()
                    }
                }else {
                    $(cur).removeAttr('style')
                }
                $(cur).find('.searchStr').val('')
                $(cur).find('.barList .barLi').removeClass('display-none')

            })

        })

        $('body').on('click','.li-content',function () {
            var _this = $(this)
            if (_this.find('.liCheckBox').hasClass('active')){
                _this.find('.liCheckBox').removeClass('active')
                _this.siblings('.liPrice').removeClass('display-b')
                _this.siblings('.unit').removeClass('display-b')
                _this.siblings('.liDesc').removeClass('display-b')
            }else {
                _this.find('.liCheckBox').addClass('active')
                _this.siblings('.liPrice').addClass('display-b')
                _this.siblings('.unit').addClass('display-b')
                _this.siblings('.liDesc').addClass('display-b')

            }
            var newSelected =  '',newArr = []
            _this.parents('.barList').find('.li-content').map(function (i,cur) {
                if ($(cur).find('.liCheckBox').hasClass('active')){
                    newArr.push({
                         liname: $(cur).find('.liName').text(),
                         liprice: $(cur).siblings('.liPrice').val() || '0'
                    })
                 }
            })
            if (newArr.length){
                newArr.map(function (cur,i) {
                    if (newArr.length -1 != i){
                        newSelected +=cur.liname + '【' +cur.liprice +'元】,'
                    }else {
                        newSelected +=cur.liname + '【' +cur.liprice +'元】'
                    }


                })
            }

            _this.parents('.layui-input-inline').siblings('.layui-input-inline').find('.directions').html(newSelected).attr('title',newSelected)

        })

        $('body').on('keyup','.liPrice',function (e) {
            if (e.keyCode != 13){
                return ;
            }
            var _this = $(this)

            if (_this.val() && !checkPapers('money', _this.val())){
                layer.msg('请输入两位小数内的数字!',{
                    time: 2000,
                    icon: 2
                })
                _this.val('')
                return;
            }
            var newSelected =  '',newArr = []
            _this.parents('.barList').find('.barLi').map(function (i,cur) {
                if ($(cur).find('.liCheckBox').hasClass('active')){
                    newArr.push({
                        liname: $(cur).find('.liName').text(),
                        liprice: $(cur).find('.liPrice').val() || '0'
                    })
                }
            })
            if (newArr.length){
                newArr.map(function (cur,i) {
                    if (newArr.length -1 != i){
                        newSelected +=cur.liname + '【' +cur.liprice +'元】,'
                    }else {
                        newSelected +=cur.liname + '【' +cur.liprice +'元】'
                    }


                })
            }

            _this.parents('.layui-input-inline').siblings('.layui-input-inline').find('.directions').html(newSelected)

        })

        $('body').on('keyup','.searchStr',function (e) {
            var _this = $(this)
            var _text = _this.val()
            console.log('---',_text)
            _this.parent().siblings('.barList').find('.barLi').map(function (i,cur) {
                var liname = $(cur).find('.liName').text()
                console.log(liname)
                if (liname.indexOf(_text) >-1){
                    $(cur).removeClass('display-none')
                }else {
                    $(cur).addClass('display-none')
                }
            })

        })


        demo1 = xmSelect.render({
            el: '#demoo1',
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
                if (arr.length){
                    getDirections(arr[0].value, 1)
                }else {
                    getDirections('', 1)
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

        $.ajax({
            url: '${ctx}/survey/channel/ajaxDataNew',
            data: {
               dataType: 'cases'
            },
            success: function (res) {
                res = JSON.parse(res)
                if (res.isSuccess){
                    caseList = res.results
                    filterJson(demo1, res.results,'surveyInfoId','surveyCaseNo',false, false)
                }
            }

        })
        $.ajax({
            url: '${ctx}/survey/channel/ajaxDataNew',
            data: {
                dataType: 'bank'
            },
            success: function (res) {
                res = JSON.parse(res)
                if (res.isSuccess){
                    caseList = res.results
                    filterJson(demoBank, res.results,'bankName','bankName',false, false)
                    demoBank.setValue([$('input[name=bankDeposit]').val()])

                }
            }

        })

        function getDirections(surveyInfoId,i) {
            $('.directions').eq(i-1).html('')
            $('.searchStr').val('')
            if (!surveyInfoId){
                var _html = '<div class="barLi barLiNone" >\n' +
                    '                         暂无数据\n' +
                    '                        </div>'
                $('.selectBar').eq(i-1).find('.barList').html(_html)
                return;
            }
            $.ajax({
                url: '${ctx}/survey/channel/ajaxDataNew',
                data: {
                    dataType: 'directions',
                    surveyInfoId: surveyInfoId
                },
                success: function (res) {
                    res = JSON.parse(res)
                    if (res.isSuccess && res.results.length){
                        var _html = ''
                        res.results.map(function (cur) {
                            var maxV = cur.channelFeeCur || ''
                            _html += ' <div class="barLi" data-id="'+cur.id+'">\n' +
                                '                            <div class="li-content">'+
                                '                                <div class="liCheckBox"></div>\n' +
                                '                                <div class="liName" title="'+cur.directionName+'">'+cur.directionName+'</div>\n' +
                                '                            </div>'+
                                '                                <input type="number" min="0" class="liPrice" placeholder="金额" data-value="'+maxV+'" value="'+maxV+'">\n' +
                                '                                <div class="unit">元</div>' +
                                '                                <input type="text" class="liDesc" placeholder="备注">\n' +
                                '                            </div>'
                        })
                        $('.selectBar').eq(i-1).find('.barList').html(_html)
                        priceBlur()
                    }else {
                        var _html = '<div class="barLi barLiNone" >\n' +
                            '                         暂无数据\n' +
                            '                        </div>'
                        $('.selectBar').eq(i-1).find('.barList').html(_html)
                    }

                }

            })
        }



        $('body').on('click', '.add', function () {
            var _this = $(this)
            var id = $('.relevanceCell:last').attr('data-id')
            var curId = Number(id) + 1
            var len = $('.relevanceCell').length
            var class1 = 'demo' + curId
            var class11 = 'demoo' + curId
            var newLen = $('.relevanceCell.selected').length
            if (!$('.relevanceCell.selected').eq(newLen - 2).find('.add').hasClass('active')) {
                $('.relevanceCell.selected').eq(newLen - 2).find('.add').addClass('active')
                $('.relevanceCell.selected').eq(newLen - 2).siblings().find('.add').removeClass('active')
            }
            $('.relevanceCell .delete').removeClass('active')

            var _html =
                '<div class="layui-inline relevanceCell cellCase selected" style="margin-right: 20px;" data-id="' +
                curId +
                '"><label class="layui-form-label">关联案件编号</label><div class="layui-input-inline"><div id="' +
                class11 +
                '" class="selectMul"></div><div class="add">添加案件</div><div class="delete active">删除案件</div></div></div><div class="layui-inline relevanceCell cellDirec selected" style="margin-right: 20px;" data-id="' +
                curId +
                '"><label class="layui-form-label">关联方向</label>' +
               '<div class="layui-input-inline">\n' +
                '                <div class="selectMul directions"></div>\n' +
                '            </div>\n' +
                '            <div class="layui-input-inline">\n' +
                '                <div class="selectMul selectBar ">\n' +
                '                    <div class="search">\n' +
                '                        <input type="text" class="searchStr">\n' +
                '                    </div>\n' +
                '                    <div class="barList">\n' +
                '                        <div class="barLi barLiNone" >\n' +
                '                         暂无数据\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            </div>'+
                '</div>'
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
                        getDirections(arr[0].value, curId)
                    }else {
                        getDirections('', curId)
                    }
                },
                data: filterJson(window[class1], caseList,'surveyInfoId','surveyCaseNo',true, false)
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
            var newLen = $('.relevanceCell.selected').length
            $('.relevanceCell.selected').eq(newLen - 2).find('.delete').addClass('active')
            $('.relevanceCell.selected').eq(newLen - 2).find('.add').removeClass('active')
            $('.relevanceCell.selected').eq(newLen - 4).find('.add').addClass('active')
            $('.relevanceCell.selected').eq(newLen - 4).find('.delete').removeClass('active')


        })

        function priceBlur(){
            $('.liPrice').blur(function (e) {

                var _this = $(this)
                if (_this.val() && !checkPapers('money', _this.val())){
                    layer.msg('请输入两位小数内的数字!',{
                        time: 2000,
                        icon: 2
                    })
                    _this.val('')
                    return;
                }

                var newSelected =  '',newArr = []
                _this.parents('.barList').find('.barLi').map(function (i,cur) {
                    if ($(cur).find('.liCheckBox').hasClass('active')){
                        newArr.push({
                            liname: $(cur).find('.liName').text(),
                            liprice: $(cur).find('.liPrice').val() || '0'
                        })
                    }
                })
                if (newArr.length){
                    newArr.map(function (cur,i) {
                        if (newArr.length -1 != i){
                            newSelected +=cur.liname + '【' +cur.liprice +'元】,'
                        }else {
                            newSelected +=cur.liname + '【' +cur.liprice +'元】'
                        }


                    })
                }

                _this.parents('.layui-input-inline').siblings('.layui-input-inline').find('.directions').html(newSelected)

            })

        }

        $('.lf-btn').click(function () {
            var _id = $('#id').val(),_state = $('#state').val()
            var _this = $(this)
            if (_this.hasClass('active')){
                var params ={
                    bankDeposit: demoBank.getValue('nameStr'),
                    payeeUserName: $('input[name=payeeUserName]').val(),
                    bankBranch:$('input[name=bankBranch]').val(),
                    bankNo:$('input[name=bankNo]').val(),
                }

                var arrFlag = []

                var len  = $('.relevanceCell').length
                var sumLen = 0,sumActive = 0

                var channelCases = [],caseIds=[],caseIdsCopy = []
                $('.cellCase').map(function (i,cur) {
                    if ($(cur).hasClass('selected')){
                        var demoBName = 'demo'+ $(cur).attr('data-id')
                        var caseInfoId = window[demoBName].getValue('valueStr')
                        caseIds.push(caseInfoId)
                        if (caseIdsCopy.indexOf(caseInfoId) == -1){
                            caseIdsCopy.push(caseInfoId)
                        }
                        var selList = []
                        $('.cellDirec').eq(i).find('.barList .barLi').map(function (i,cur) {
                            if ($(cur).find('.liCheckBox').hasClass('active')){
                                selList.push({
                                    id: $(cur).attr('data-id'),
                                    name: $(cur).find('.liName').text(),
                                    price: $(cur).find('.liPrice').val(),
                                    desc: $(cur).find('.liDesc').val(),
                                    maxMoney: $(cur).find('.liPrice').attr('data-value')
                                })
                            }
                        })
                        sumLen ++
                        if (selList.length){
                            sumActive ++
                            selList.map(function (cur,i) {
                                channelCases.push({
                                    surveyInfoId: window[demoBName].getValue('valueStr'),
                                    surveyCaseNo: window[demoBName].getValue('nameStr'),
                                    surveyDirectionId: cur.id,
                                    surveyDirectionName: cur.name,
                                    chnannelMoney: cur.price || 0,
                                    channelDesc: cur.desc,
                                    maxChannelMoney: cur.maxMoney
                                })
                            })
                        }
                    }

                })
                if (sumActive != sumLen){
                    layer.msg('请关联案件编号、方向！',{
                        time: 2000,icon: 5
                    })
                    return ;
                }

                if (caseIds.length > caseIdsCopy.length ){
                    layer.msg('案件编号重复！',{
                        time: 2000,icon: 5
                    })
                    return ;
                }

                Object.assign(params,{
                    btnCode: 'first-commit',
                    channelCases: JSON.stringify(channelCases)})


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
                var _flag = true
                layer.confirm( '确认提交？', {
                    title: '提示',
                    btn: ['确定','取消'] //按钮
                },function(index){
                    if (_flag){
                        _flag = false
                        $.ajax({
                            url: '${ctx}/survey/channel/operateNew',
                            data: params,
                            success: function (res) {
                                res = JSON.parse(res)
                                if (res.isSuccess){
                                    layer.msg('操作成功',{
                                        time: 1500,
                                        icon: 1
                                    },function(){
                                        parent.reload();
                                    })
                                }else{
                                    _flag = true
                                }
                            }

                        })
                    }

                });


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
                ['integer', /^[0-9]\d*$/],
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
