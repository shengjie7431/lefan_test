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
    <div class="layui-inline" style="margin-right: 20px;" data-id="1">
        <label class="layui-form-label">案件编号</label>
        <div class="layui-input-inline" style="justify-content: normal">
            <input type="text" readonly class="layui-input" name="surveyCaseNo" value="${dto.surveyCaseNo}"
                   placeholder="" style="margin-right: 10px">
        </div>
    </div>
    <div class="layui-inline" style="margin-right: 20px;" data-id="1">
        <label class="layui-form-label">方向名称</label>
        <div class="layui-input-inline" style="justify-content: normal">
            <input type="text" readonly class="layui-input" name="surveyDirectionName" value="${dto.surveyDirectionName}"
                   placeholder="" style="margin-right: 10px">
        </div>
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

        $('.lf-btn').click(function () {
            var _id = $('#id').val(),_state = $('#state').val()
            var _this = $(this)
            if (_this.hasClass('active')){
                var params ={
                    btnCode : "edit",
                    id : $("#id").val(),
                    bankDeposit: demoBank.getValue('nameStr'),
                    chnannelMoney: $('input[name=chnannelMoney]').val(),
                    channelDesc: $('textarea[name=channelDesc]').val(),
                    payeeUserName: $('input[name=payeeUserName]').val(),
                    bankBranch:$('input[name=bankBranch]').val(),
                    bankNo:$('input[name=bankNo]').val(),
                }

                if (!params.payeeUserName){
                    layer.msg('请输入收款人姓名！',{
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
                            url: '${ctx}//survey/channel/operateNew',
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
