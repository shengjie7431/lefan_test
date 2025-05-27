</html>
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
    <input hidden id="unmatchId" value="${unmatchId}">
    <input hidden id="unmatchMoney" value="${unmatchMoney}"><%--未认领金额 申请退费金额不可大于此金额--%>
    <input hidden id="apply" value="${apply}">
    <div class="layui-inline" style="margin-right: 20px;" data-id="1">
        <label class="layui-form-label">未认领金额</label>
        <div class="layui-input-inline" style="justify-content: normal">
            <input type="number" class="layui-input" name="qw" value="${unmatchMoney}"
                   placeholder="" style="margin-right: 10px;background-color: #C9C5C5" disabled>元
        </div>
    </div>
    <div class="layui-inline" style="margin-right: 20px;" data-id="1">
        <label class="layui-form-label">退费金额</label>
        <div class="layui-input-inline" style="justify-content: normal">
            <input type="number" class="layui-input tfmoney" name="refundMoney" value="${unmatchMoney}"
                   placeholder="" style="margin-right: 10px">元
        </div>
    </div>
    <div class="layui-inline " style="margin-right: 20px;" data-id="1">
        <label class="layui-form-label">收款人</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" name="payee"
                   placeholder="" required>
        </div>
    </div>
    <div class="layui-inline " style="margin-right: 20px;" data-id="1">
        <label class="layui-form-label">银行卡号</label>
        <div class="layui-input-inline">
            <input type="number" class="layui-input" name="bankCarNo"
                   placeholder="">
        </div>
    </div>
    <div class="layui-inline " style="margin-right: 20px;" data-id="1">
        <label class="layui-form-label">银行名称</label>
        <div class="layui-input-inline">
            <div id="demoBank" class="selectMul"></div>
        </div>
    </div>
    <div class="layui-inline " style="margin-right: 20px;" data-id="1">
        <label class="layui-form-label">银行支行</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" name="bankBranch"
                   placeholder="">
        </div>
    </div>

    <div class="rejectionType lf-btns layui-inline" style="justify-content: center">
        <div class="lf-btn " data-type="1">取消</div>
        <div class="lf-btn active submit" data-type="2">${apply == 'true' ? '提交至财务审核' : '提交至付款管理'}</div>
    </div>
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
            url: '${ctx}/billingApplyUnmatch/claim',
            data: {
                dataType: 'bank',
                unmatchBank:"true"
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

        $('.tfmoney').blur(function () {
            var _this = $(this)
            var _val = _this.val()
            var unmatchMoney = $('#unmatchMoney').val()
            if (_val != null && _val != ''){
                _val = parseFloat(_val)
            }
            if (unmatchMoney != null && unmatchMoney != ''){
                unmatchMoney = parseFloat(unmatchMoney)
            }
            if (!checkPapers('money',_val)){
                layer.msg('退费金额：请输入小数点两位内的数字',{
                    time: 2000,
                    icon:2
                })
                _this.val(Math.round(_val * 100) /100)

            }
            if (_val > unmatchMoney){
                layer.msg('退费金额：不可大于未认领金额',{
                    time: 2000,
                    icon:2
                })
                _this.val(unmatchMoney)
            }
        })

        $('.lf-btn').click(function () {
            var _id = $('#id').val(),_state = $('#state').val()
            var _this = $(this)
            if (_this.hasClass('active')){
                var params ={
                    bankName: demoBank.getValue('nameStr'),
                    matchId: $("#unmatchId").val(),
                    money: $('input[name=refundMoney]').val(),
                    payee: $('input[name=payee]').val(),
                    bankCarNo: $('input[name=bankCarNo]').val(),
                    bankBranch:$('input[name=bankBranch]').val(),
                    apply:$("#apply").val(),
                    meunCode:'refundAdd'
                }

                if (!checkPapers('money',params.money)){
                    layer.msg('退款金额请输入两位小数点内数字！',{
                        time: 2000,icon: 5
                    })
                    return ;
                }
                if (!params.payee){
                    layer.msg('请输入收款人姓名！',{
                        time: 2000,icon: 5
                    })
                    return ;
                }
                if (!params.bankCarNo){
                    layer.msg('请输入银行帐号',{
                        time: 2000,icon: 5
                    })
                    return ;
                }
                if (!params.bankName){
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
                $('.lf-btn').addClass('poi-none')
                $.ajax({
                    url: '${ctx}/billingApplyUnmatch/update',
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
