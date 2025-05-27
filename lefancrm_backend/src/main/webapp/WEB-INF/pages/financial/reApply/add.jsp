<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>每刻报销列表</title>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/layui/layui_exts/soulTable.css">
    <link rel="stylesheet" href="${ctx}/css/viewer.min.css">

    <style>
        .main {
            width: 98%;
            margin: 0 auto;
        }

        .main-header {
            padding: 10px 0;
        }

        label.layui-form-label {
            width:110px;
            text-align: left
        }

        .layui-form-label-num {
            height: 38px;
            line-height: 38px;
            color: #666;
            font-size: 18px;
            font-weight: bold;
        }


        .caseNo {
            color: #3BA9FF;
        }

        .searchs {
            width: 100%;
            display: flex;
        }

        .layui-form-item {
            width: 50%;
        }

        .layui-inline {
            width: 100%;
        }

        .layui-input-inline {
            width: 92% !important;
        }

        .lf-ii {
            width: 70% !important;
        }

        .layui-input-inline input,
        .layui-input-inline textarea {
            width: 100%;
        }

        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }

        .files {
            margin-top: 10px;
            width: 100%;
            display: flex;
            flex-wrap: wrap;
            /*max-height: 330px;*/
            /*overflow: auto;*/
        }

        .files .file {
            width: 60px;
            height: 60px;
            border: 1px solid #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;
            position: relative;
        }

        .files .file .image {
            width: 100%;
            height: 100%;
            display: block;
            margin: 0 auto;

        }

        .files div.file-add {
            width: 60px;
            height: 60px;
            border: 1px dashed #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;
        }

        .icon-add {
            margin-left: 30px;
            margin-top: 10px;
            display: inline-block;
            background: #aaa;
            height: 40px;
            position: relative;
            width: 2px;
        }

        .icon-add:after {
            background: #aaa;
            content: "";
            height: 40px;
            left: 0;
            position: absolute;
            top: 0;
            width: 2px;
            transform: rotateZ(90deg);
        }

        .file-title {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 20px;
            line-height: 20px;
            font-size: 10px;
            background-color: rgba(187, 187, 187, 0.7);
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .file-del {
            position: absolute;
            top: -8px;
            right: -9px;
            display: inline-block;
            width: 16px;
            height: 16px;
            background: url(data:image/png;base64,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);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }

        .content-bar {
            width: 100%;
            margin-top: 10px;
            border-bottom: 4px solid #f6f6f6;
        }

        .content-bar-title{
            padding: 10px 0;
        }
        .content-bar-title span {
            font-weight: bold;
        }

        .content-bar-btns {
            margin-left: 50px;
            display: inline-flex;
        }

        .content-bar-btns .content-bar-btn {
            margin-right: 10px;
            width: 100px;
            height: 30px;
            line-height: 30px;
            color: #3BA9FF;
            border: 1px solid #3BA9FF;
            text-align: center;
            cursor: pointer;
        }

        .i-table {
            width: 99%;
            margin: 10px auto;
        }

        .i-table .i-thead {
            padding: 10px 0;
            display: flex;
            width: 100%;
            border-bottom: 1px solid #bbb;
        }

        .i-table .i-tbody {
            width: 100%;
            padding: 10px 0;
        }

        .i-table .i-thead .i-td {
            width: 10%;
            height: 20px;
            line-height: 20px;
            display: flex;
            align-items: center;
        }

        /* .costTypes .i-table .i-thead .i-td .i-td-text {
            height: 20px;
           line-height: 20px;
        } */
        .i-table .i-tr {
            padding: 5px 0;
            display: flex;
            width: 100%;
        }

        .i-table .i-tr .i-td {
            width: 6%;
            text-align: left;
        }

        .i-table .i-thead .i-td.wid6,
        .i-table .i-tr .i-td.wid6 {
            width: 6%
        }

        .i-table .i-thead .i-td.wid15,
        .i-table .i-tr .i-td.wid15 {
            width: 15%
        }

        .i-table .i-thead .i-td.wid32,
        .i-table .i-tr .i-td.wid32 {
            width: 32%
        }

        .delCostType,
        .delBear {
            color: #3BA9FF;
            cursor: pointer;
        }

        .i-table .files {
            margin-top: 0
        }

        .i-table .files .file {
            width: 36px;
            height: 36px;
            border: 1px solid #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;
            position: relative;
        }

        .i-table .files div.file-add {
            width: 36px;
            height: 36px;
            border: 1px dashed #d0c9c9;
            margin-bottom: 10px;
            margin-right: 10px;
        }

        .i-table .icon-add {
            margin-left: 17px;
            margin-top: 5px;
            display: inline-block;
            background: #aaa;
            height: 24px;
            position: relative;
            width: 1px;
        }

        .i-table .icon-add:after {
            background: #aaa;
            content: "";
            height: 24px;
            left: 0;
            position: absolute;
            top: 0;
            width: 1px;
            transform: rotateZ(90deg);
        }

        .unit {
            position: absolute;
            right: 20px;
            top: 10px;
        }

        .lf-btns {
            padding: 10px 0;
            width: 100%;
            display: flex;
            justify-content: center;
        }

        .lf-btns .lf-btn {
            width: 180px;
            height: 40px;
            line-height: 40px;
            border: 1px solid #3BA9FF;
            color: #3BA9FF;
            text-align: center;
            margin-right: 40px;
            cursor: pointer;
        }

        .lf-btns .lf-btn.active {
            background-color: #3BA9FF;
            color: #fff;
        }
        .required{
            color: red;
        }
        .showLine{
            display:inline-block;
        }
        .hideLine{
            display: none;
        }
        .poi-no{
            pointer-events: none;
        }
        .files .file .img-file{
            background: url(data:image/png;base64,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);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            width: 59%;
            height: 59%;
            display: block;
            margin: 0 auto;
            margin-top: 7%;
        }
    </style>
</head>

<body>
<div class="main">
    <input type="hidden" value="${reType}" id="reType">
    <input type="hidden" value="${financialReApplyId}" id="financialReApplyId">
    <input type="hidden" value="${companyId}" id="companyId">
    <input type="hidden" value="${organId}" id="organId">
    <input type="hidden" value="${payeeName}" id="payeeNameInit">
    <input type="hidden" value="${payeeNo}" id="payeeNoInit">
    <input type="hidden" value="${branchBank}" id="branchbankInit">
    <input type="hidden" value="${bankId}" id="bankIdInit">
    <input type="hidden" value="${payUserId}" id="payUserId">
    <input type="hidden" value="${copy}" id="copy">
    <input type="hidden" value="${updateOrgan}" id="updateOrgan">
    <div class="content-bar">
        <%--<div class="content-bar-title">--%>
            <%--<span>--%>
                <%--${reType == 1 ? '新增日常费用报销':''}--%>
                <%--${reType == 2 ? '新增对公支付':''}--%>
                <%--${reType == 3 ? '新增借款单':''}--%>
            <%--</span>--%>
        <%--</div>--%>
        <div class="layui-form searchs" lay-filter="search">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><span class="required">*</span>事由:</label>
                    <div class="layui-input-inline  lf-ii">
                        <input type="text" name="reReasons" placeholder="请输入" class="reReasons layui-input">
                    </div>
                </div>
                <div class="layui-inline ${reType == 3 ? 'showLine' : 'hideLine'}" >
                    <label class="layui-form-label"><span class="required">*</span>借款金额:</label>
                    <div class="layui-input-inline  lf-ii">
                        <input type="number" id="repaymentMoney" name="repaymentMoney" placeholder="请输入" class="repaymentMoney layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span class="required">*</span>公司抬头:</label>
                    <div class="layui-input-inline  lf-ii">
                        <div id="surveyOrgId" class="selectMul"></div>
                    </div>
                </div>
<%--                <div class="layui-inline">--%>
<%--                    <label class="layui-form-label"><span class="required">*</span>承担部门:</label>--%>
<%--                    <div class="layui-input-inline  lf-ii">--%>
<%--                        <div id="bearOrgId" class="selectMul"></div>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="layui-inline">
                    <label class="layui-form-label">备注:</label>
                    <div class="layui-input-inline  lf-ii">
                        <textarea name="" id="" cols="30" rows="10" class="applyDesc layui-textarea"></textarea>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
<%--                <c:if test="${reType == 1 || reType == 3}">--%>
                    <div class="layui-inline" id="div_re_type_1_3">
                        <label class="layui-form-label"><span class="required">*</span>收款人:</label>
                        <div class="layui-input-inline  lf-ii">
                            <div id="payeeUserId" class="selectMul"></div>
                        </div>
                    </div>
<%--                </c:if>--%>
<%--                <c:if test="${reType == 2}">--%>
                    <div class="layui-inline" id="div_re_type_2">
                        <label class="layui-form-label"><span class="required">*</span>收款人:</label>
                        <div class="layui-input-inline  lf-ii">
                            <input type="text" name="payeeName" placeholder="请输入" class="payeeName layui-input">
                        </div>
                    </div>
<%--                </c:if>--%>
                <div class="layui-inline">
                    <label class="layui-form-label"><span class="required">*</span>收款账户:</label>
                    <div class="layui-input-inline  lf-ii">
                        <input type="number" name="payeeNo" placeholder="请输入" class="payeeNo layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span class="required">*</span>银行:</label>
                    <div class="layui-input-inline  lf-ii">
                        <div id="bankInfo" class="selectMul"></div>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label"><span class="required">*</span>支行:</label>
                    <div class="layui-input-inline  lf-ii">
                        <input type="text" name="branchbank" placeholder="请输入" class="branchbank layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">附件:</label>
                    <div class="layui-input-inline  lf-ii">
                        <div class='files' id="uploadFiles">
                            <div class="file-add uploadFile">
                                <div class="icon-add v-p-upload"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="content-bar costTypes ${reType == 3 ? 'hideLine' : 'showLine'}">
        <div class="content-bar-title">
            <span>费用明细</span>
            <div class="content-bar-btns">
                <div class="content-bar-btn addCostType">新增费用</div>
            </div>
        </div>
        <div class="i-table">
            <div class="i-thead">
                <div class="i-td wid15">
                    <span class="i-td-text "><span class="required">*</span>费用类型</span>
                </div>
                <div class="i-td wid15">
                    <span class="i-td-text "><span class="required">*</span>金额（合计：<span class="sumMoney">0</span>元）</span>
                </div>
                <div class="i-td wid32">
                    <span class="i-td-text ">备注</span>
                </div>
                <div class="i-td wid32">
                    <span class="i-td-text "><c:if test="${reType == 1}"><span class="required">*</span></c:if>发票</span>
                </div>
                <div class="i-td wid6">
                    <span class="i-td-text ">操作</span>
                </div>
            </div>
            <div class="i-tbody">

            </div>
        </div>
    </div>
    <div class="content-bar" style="margin-left: 100px;"><span style="font-weight: bold">合计大写：<span id="sumMoneyBlock">零元</span></span></div>
    <div class="content-bar bearInfo ${reType == 3 ? 'hideLine' : 'showLine'}">
        <div class="content-bar-title">
            <span>承担明细</span>
            <div class="content-bar-btns content-bar-btns2">
                <div class="content-bar-btn addBear">新增承担部门</div>
                <div class="content-bar-btn avgBear">平均分担</div>
            </div>
        </div>
        <div class="i-table">
            <div class="i-thead">
                <div class="i-td wid15">
                    <span class="i-td-text "><span class="required">*</span>承担部门</span>
                </div>
                <div class="i-td wid15">
                    <span class="i-td-text "><span class="required">*</span>金额</span>
                </div>
                <div class="i-td wid32">
                    <span class="i-td-text ">备注</span>
                </div>
                <div class="i-td wid6">
                    <span class="i-td-text ">操作</span>
                </div>
            </div>
            <div class="i-tbody">

            </div>
        </div>
    </div>
    <div class="lf-btns">
        <div class="lf-btn" data-id="1">取消</div>
        <div class="lf-btn" data-id="2">暂存并关闭</div>
        <div class="lf-btn active" data-id="3">提交</div>

    </div>
</div>

<script src="${ctx}/js/viewer.min.js" charset="utf-8"></script>

<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })

    layui.use(['form', 'xmSelect', 'laydate', 'jquery', 'upload', 'layer'], function () {
        var form = layui.form,
            xmSelect = layui.xmSelect,
            laydate = layui.laydate,
            $ = layui.jquery,
            layer = layui.layer,
            upload = layui.upload;
        var uploadFile = ''

        var financialReApplyId = $('#financialReApplyId').val()
        var costTypeJson = [],bearOrgJson=[]

        var loadIndex = layer.load()

        var demo1 = xmSelect.render({
            el: '#surveyOrgId',
            theme: {
                color: '#3BA9FF',
            },
            clickClose: true,
            radio: true,
            filterable: true,
            filterDone: function (val, list) {
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
                            var _html = ''
                            sels.filter(function (cur) {
                                _html +=
                                    '<div class="xm-label-block lf-select-block">' + cur
                                        .name + '</div>'
                            })
                            return _html
                        }
                    },
                }
            },
            data: []
        })


        var demo2 = xmSelect.render({
            el: '#bankInfo',
            theme: {
                color: '#3BA9FF',
            },
            radio: true,
            clickClose: true,
            filterable: true,
            filterDone: function (val, list) {
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
                            var _html = ''
                            sels.filter(function (cur) {
                                _html +=
                                    '<div class="xm-label-block lf-select-block">' + cur
                                        .name + '</div>'
                            })
                            return _html
                        }
                    },
                }
            },
            data: []
        })
        var demo6 = xmSelect.render({
            el: '#payeeUserId',
            theme: {
                color: '#3BA9FF',
            },
            radio: true,
            clickClose: true,
            filterable: true,
            filterDone: function (val, list) {
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
                            var _html = ''
                            sels.filter(function (cur) {
                                _html +=
                                    '<div class="xm-label-block lf-select-block">' + cur
                                        .name + '</div>'
                            })
                            return _html
                        }
                    },
                }
            },
            data: []
        })

        $.ajax({
            url: '${ctx}/financial/reApply/ajaxData',
            type: 'post',
            data: {
                dataType: 'bank-info-list'
            },
            success: function (res) {
                var res = JSON.parse(res)
                filterJson(demo2, res.results,'id','bankName',false, false)
                var _bankId = $('#bankIdInit').val()
                demo2.setValue([_bankId])
            }
        })
        $.ajax({
            url: '${ctx}/financial/reApply/ajaxData',
            type: 'post',
            data: {
                dataType: 'get-staff-users'
            },
            success: function (res) {
                var res = JSON.parse(res)
                filterJson(demo6, res.results,'userId','realName',false, false)
                var payUserId = $('#payUserId').val()
                demo6.setValue([payUserId])

                var reType = $("#reType").val();
                if (reType == 1 || reType == 3){
                    $("#div_re_type_1_3").show();
                    $("#div_re_type_2").hide();
                }else{
                    $("#div_re_type_1_3").hide();
                    $("#div_re_type_2").show();
                }
            }
        })


        var costTypeJson = [],bearOrgJson=[]
        $.ajax({
            url: '${ctx}/financial/reApply/ajaxData',
            type: 'post',
            data: {
            dataType: 'billing-apply-corporation',
            },
            success: function (res) {
            var res = JSON.parse(res)
            filterJson(demo1, res.results,'id','name',false, false)
                demo1.setValue([$('#companyId').val()])
                if ($('#updateOrgan').val() == '0'){
                    demo1.update({ disabled: true });
                }
            }
        })

        //费用类型
        $.ajax({
            url: '${ctx}/financial/reApply/ajaxData',
            type: 'post',
            async:false,
            data: {
            dataType: 'financial-cost-type-list',
            type: '1'
            },
            success: function (res) {
            var res = JSON.parse(res)
            costTypeJson = filterJson('', res.results,'id','costName',true, false)
            }
        })

        //承担部门
        $.ajax({
            url: '${ctx}/financial/reApply/ajaxData',
            type: 'post',
            async:false,
            data: {
            dataType: 'survey-organ-list',
            searchType: 'all'
            },
            success: function (res) {
            var res = JSON.parse(res)
            bearOrgJson = filterJson('', res.results,'id','name',true, false)
            // filterJson('', res.results,'id','name',false, false)
            //     demo3.setValue([$('#organId').val()])
            }
        })

        if (!financialReApplyId){
            if ($('#updateOrgan').val() == '0'){
                $('.content-bar-btns2').hide()
            }
            layer.close(loadIndex)
            var _htmlCost = '<div class="i-tr" data-id="1">\n' +
                '                    <div class="i-td wid15">\n' +
                '                        <div class="layui-input-inline">\n' +
                '                            <div id="costType1Temp" class="selectMul"></div>\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <div class="i-td wid15">\n' +
                '                        <div class="layui-input-inline">\n' +
                '                            <input type="number" name="costMoney" placeholder="请输入" class="costMoney layui-input">\n' +
                '                            <div class="unit">元</div>\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <div class="i-td wid32">\n' +
                '                        <div class="layui-input-inline">\n' +
                '                            <textarea name="costDesc" style="height: 50px" placeholder="请输入" class="costDesc layui-input" />\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <div class="i-td wid32">\n' +
                '                        <div class=\'files\' id="costFiles1">\n' +
                '                            <div class="file-add uploadFileAdd">\n' +
                '                                <div class="icon-add v-p-upload"></div>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <div class="i-td wid6"></div>\n' +
                '                </div>'
            $('.costTypes .i-table .i-tbody').append(_htmlCost)

            var _htmlBear = '  <div class="i-tr" data-id="1">\n' +
                '                    <div class="i-td wid15">\n' +
                '                        <div class="layui-input-inline">\n' +
                '                            <div id="bearOrgTemp" class="selectMul"></div>\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <div class="i-td wid15">\n' +
                '                        <div class="layui-input-inline">\n' +
                '                            <input type="number" name="bearMoney" placeholder="请输入" class="bearMoney layui-input">\n' +
                '                            <div class="unit">元</div>\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <div class="i-td wid32">\n' +
                '                        <div class="layui-input-inline">\n' +
                '                            <textarea name="bearDesc" style="height: 50px;" placeholder="请输入" class="bearDesc layui-input" />\n' +
                '                        </div>\n' +
                '                    </div>\n' +
                '                    <div class="i-td wid6"></div>\n' +
                '                </div>'
            $('.bearInfo .i-table .i-tbody').append(_htmlBear)

            democ1 = xmSelect.render({
                el: '#costType1Temp',
                theme: {
                    color: '#3BA9FF',
                },
                clickClose: true,
                radio: true,
                filterable: true,
                filterDone: function (val, list) {
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
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur
                                            .name + '</div>'
                                })
                                return _html
                            }
                        },
                    }
                },
                data: costTypeJson
            })

            demob1 = xmSelect.render({
                el: '#bearOrgTemp',
                theme: {
                    color: '#3BA9FF',
                },
                clickClose: true,
                radio: true,
                filterable: true,
                filterDone: function (val, list) {
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
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur
                                            .name + '</div>'
                                })
                                return _html
                            }
                        },
                    }
                },
                data: bearOrgJson
            })

            if ($('#updateOrgan').val() == '0'){
                demob1.update({ disabled: true });
            }

            var _payeeName = $('#payeeNameInit').val()
            var _payeeNo = $('#payeeNoInit').val()
            var _branchbank = $('#branchbankInit').val()

            $('.payeeName').val(_payeeName)
            $('.payeeNo').val(_payeeNo)
            $('.branchbank').val(_branchbank)
            demob1.setValue([$('#organId').val()])
            uploadFile = upload.render({
                elem: '.uploadFile',
                url: '${ctx}/sftp/survey/uploadSftp', //改成您自己的上传接口
                data: {
                    modelType: 'financial',
                    fileType: 'apply'
                },
                multiple: true,
                accept: 'file', //只能上传图片
                // acceptMime: 'image/*',
                before: function (obj) {
                    var _id = $(this.item).parent().attr('id')
                    layer.load();
                    fileLen = $('#'+_id+' .file').length
                },
                done: function (res) {
                    layer.closeAll('loading')
                    if (res.success == 'true') {
                        setFileHtml(res.surveyFile, $(this.item).parent().attr('id'),true);
                    } else {
                        layer.alert('导入出错', {
                            icon: 2
                        })
                    }
                },
                allDone: function (obj) {
                    var _id = $(this.item).parent().attr('id')
                    if (fileLen) {
                        window['viewer'+_id].destroy()
                    }
                    window['viewer'+_id] = new Viewer(document.getElementById(_id));
                },
                error: function (index, upload) {
                    layer.closeAll('loading')
                }
            });
            uploadFile = upload.render({
                elem: '.uploadFileAdd',
                url: '${ctx}/sftp/survey/uploadSftp', //改成您自己的上传接口
                data: {
                    modelType: 'financial',
                    fileType: 'details'
                },
                multiple: true,
                accept: 'file', //只能上传图片
                // acceptMime: 'image/*',
                before: function (obj) {
                    var _id = $(this.item).parent().attr('id')
                    layer.load();
                    fileLen = $('#'+_id+' .file').length
                },
                done: function (res) {
                    layer.closeAll('loading')
                    if (res.success == 'true') {
                        putSessionFiles(res.surveyFile, $(this.item).parent().attr('id'));
                    } else {
                        layer.alert('导入出错', {
                            icon: 2
                        })
                    }
                },
                allDone: function (obj) {
                    var _id = $(this.item).parent().attr('id')
                    if (fileLen) {
                        window['viewer'+_id].destroy()
                    }
                    window['viewer'+_id] = new Viewer(document.getElementById(_id));
                },
                error: function (index, upload) {
                    layer.closeAll('loading')
                }
            });


        }else if (financialReApplyId){
            $.ajax({
                url: '${ctx}/financial/reApply/ajaxData',
                type: 'post',
                data: {
                    dataType: 'get-info',
                    financialReApplyId: $('#financialReApplyId').val(),
                    copy : $("#copy").val()
                },
                success: function (res) {
                    layer.close(loadIndex)
                    var res = JSON.parse(res)
                    if (!res.isSuccess){
                        return ;
                    }
                    var results = res.results
                    $('.reReasons').val(results.reReasons)
                    demo1.setValue([results.companyId])
                    $('.applyDesc').val(results.applyDesc)
                    $('.payeeName').val(results.payeeName)
                    $('.payeeNo').val(results.payeeNo)
                    $('#updateOrgan').val(results.isCanModify)
                    if ($('#updateOrgan').val() == '0'){
                        $('.content-bar-btns2').hide()
                        demo1.update({ disabled: true });
                    }
                    var bankId = results.bankId || ''
                    demo2.setValue([bankId])
                    var payUserId = results.payUserId || ''
                    demo6.setValue([payUserId])
                    $('.branchbank').val(results.branchBank)

                    var sumMoney = results.reMoney || 0
                    $('.sumMoney').html(sumMoney)
                    $('#sumMoneyBlock').html(convertCurrency(sumMoney))
                    var fileHtml = '<div class="file-add uploadFile">\n' +
                        '                                <div class="icon-add v-p-upload"></div>\n' +
                        '                            </div>'
                    if (results.financialFileList && results.financialFileList.length){
                        results.financialFileList.map(function (cur) {
                            // fileHtml += ' <div class="file" data-id="'+cur.id+'">\n' +
                            //     '                        <img src="' + cur.filePath + '" class="image"></img>\n' +
                            //     '                        <div class="file-del"></div>'+
                            // '                    </div>'

                            var _htmlFile = '<img src="' + cur.filePath + '" class="image" />'
                            var _htmlName =  '<div class="file-title">' + cur.fileName + '</div>\n'
                            var _fileId = cur.id ||''
                            var _htmlDel = '<div class="file-del" data-id="'+_fileId+'"></div>'
                            var file = cur.filePath
                            var id = file.lastIndexOf('.')
                            var type = file.slice(id + 1);
                            var imgArr = ['png', 'jpg', 'gif', 'bmp', 'jpeg']
                            if (imgArr.indexOf(type) == -1) {
                                _htmlFile =  '<div target="_blank"  class="image  img-file"></div>'
                                _htmlName = '<a class="file-title" target="_blank"  href="'+cur.filePath+'" download="'+cur.filePath+'" title="'+cur.fileName+'">' + cur.fileName + '</a>\n'
                            }
                            var _html = ' <div class="file">' +
                                _htmlFile+
                                _htmlName+_htmlDel+
                                ' </div>'
                            fileHtml += _html
                        })

                    }
                    $('#uploadFiles').html(fileHtml)
                    if (results.financialFileList && results.financialFileList.length) {
                        window['vieweruploadFiles'] = new Viewer(document.getElementById('uploadFiles'));
                    }
                    var reType = $('#reType').val()

                    if (results.financialCostBearList && results.financialCostBearList.length){
                        var departmentId = results.financialCostBearList[0].departmentId || ''
                        // demo3.setValue([departmentId])

                    }
                    if (reType == 3){
                        $('.repaymentMoney').val(results.repaymentMoney)

                    }
                    if (reType == 1 || reType == 2){
                        if (results.financialCostDetails && results.financialCostDetails.length){
                            results.financialCostDetails.map(function (cur,i) {
                                mapCostType(cur,i+1)
                            })
                        }
                        if (results.financialCostBearList && results.financialCostBearList.length){
                            results.financialCostBearList.map(function (cur,i) {
                                mapBearCell(cur,i+1)
                            })
                        }
                        showBtn();
                    }
                     uploadFile = upload.render({
                        elem: '.uploadFile',
                         url: '${ctx}/sftp/survey/uploadSftp', //改成您自己的上传接口
                         data: {
                             modelType: 'financial',
                             fileType: 'apply'
                         },
                        multiple: true,
                        accept: 'file', //只能上传图片
                        // acceptMime: 'image/*',
                        before: function (obj) {
                            var _id = $(this.item).parent().attr('id')
                            layer.load();
                            fileLen = $('#'+_id+' .file').length
                        },
                        done: function (res) {
                            layer.closeAll('loading')
                            if (res.success == 'true') {
                                setFileHtml(res.surveyFile, $(this.item).parent().attr('id'),true);
                            } else {
                                layer.alert('导入出错', {
                                    icon: 2
                                })
                            }
                        },
                        allDone: function (obj) {
                            var _id = $(this.item).parent().attr('id')
                            if (fileLen) {
                                window['viewer'+_id].destroy()
                            }
                            window['viewer'+_id] = new Viewer(document.getElementById(_id));
                        },
                        error: function (index, upload) {
                            layer.closeAll('loading')
                        }
                    });
                    uploadFile = upload.render({
                        elem: '.uploadFileAdd',
                        url: '${ctx}/sftp/survey/uploadSftp', //改成您自己的上传接口
                        data: {
                            modelType: 'financial',
                            fileType: 'details'
                        },
                        multiple: true,
                        accept: 'file', //只能上传图片
                        // acceptMime: 'image/*',
                        before: function (obj) {
                            var _id = $(this.item).parent().attr('id')
                            layer.load();
                            fileLen = $('#'+_id+' .file').length
                        },
                        done: function (res) {
                            layer.closeAll('loading')
                            if (res.success == 'true') {
                                putSessionFiles(res.surveyFile, $(this.item).parent().attr('id'));
                            } else {
                                layer.alert('导入出错', {
                                    icon: 2
                                })
                            }
                        },
                        allDone: function (obj) {
                            var _id = $(this.item).parent().attr('id')
                            if (fileLen) {
                                window['viewer'+_id].destroy()
                            }
                            window['viewer'+_id] = new Viewer(document.getElementById(_id));
                        },
                        error: function (index, upload) {
                            layer.closeAll('loading')
                        }
                    });

                }
            })
        }



        $('.addCostType').click(function () {
            setCostType();
            showBtn();
        })
        $('.content-bar').on('click', '.delCostType', function () {
            var _this = $(this)
            _this.addClass('poi-no')
            setTimeout(function () {
                _this.removeClass('poi-no')
            },3000);
            
            var nowId = _this.parents('.i-tr').attr('data-cellid') || ''
            if (nowId){
                $.ajax({
                    url: "${ctx}/financial/reApply/operate",
                    type: 'post',
                    data: {
                        btnCode: 'delete-cost-details',
                        costDetailsId: nowId,
                        copy : $("#copy").val()
                    },
                    success: function (res) {
                        _this.removeClass('poi-no')
                        var res = JSON.parse(res);
                        if (res.isSuccess) {
                            layer.msg('成功', {
                                time: 1000,
                                icon: 1
                            }, function () {
                                _this.parents('.i-tr').detach()
                                var sum = 0
                                $('.costTypes .costMoney').map(function (i, cur) {
                                    sum = sum + Number($(cur).val())
                                })
                                $('.sumMoney').html(sum)
                                $('#sumMoneyBlock').html(convertCurrency(sum))

                                var _id = _this.parents('.i-tr').attr('id')
                                if ($('#costFile'+_id+' .file').length) {
                                    window['viewercostFiles'+_id].destroy()
                                }

                                showBtn()
                            })
                        } else {
                            layer.msg('失败', {
                                time: 1000,
                                icon: 2
                            })
                        }
                        showBtn();
                    }
                })
            }else {
                _this.parents('.i-tr').detach()
                var sum = 0
                $('.costTypes .costMoney').map(function (i, cur) {
                    sum = sum + Number($(cur).val())
                })
                $('.sumMoney').html(sum)
                $('#sumMoneyBlock').html(convertCurrency(sum))

                var _id = _this.parents('.i-tr').attr('id')
                if ($('#costFile'+_id+' .file').length) {
                    window['viewercostFiles'+_id].destroy()
                }
            }
            showBtn();
            avgBear();
        })
        $('.costTypes').on('blur', '.costMoney', function () {
            var _this = $(this)
            if (_this.val() && !checkPapers('money', _this.val())) {
                layer.msg('输入格式不符合规范(小数点两位内),已自动格式化', {
                    time: 2000,
                    icon: 7
                })
                _this.val(Math.round(_this.val() * 100) / 100)
            }
            var sum = 0
            $('.costTypes .costMoney').map(function (i, cur) {
                sum = sum + Number($(cur).val())
            })
            $('.sumMoney').html(sum)
            $('#sumMoneyBlock').html(convertCurrency(sum))
            avgBear()
        })


        showBtn();
        $('.addBear').click(function () {
            setBearCell()
            avgBear()
            showBtn();
        })
        function showBtn(){
            var costLen = $('.costTypes .i-tr').length;
            var bearOrgLen = $('.bearInfo .i-tr').length
            console.log("len",costLen,bearOrgLen);
            if(costLen == 1){
                $(".addBear").show();
                $(".avgBear").show();
            }
            if (bearOrgLen == 1){
                $(".addCostType").show();
            }
            if (costLen > 1){
                $(".addBear").hide();
                $(".avgBear").hide();
            }
            if (bearOrgLen > 1){
                $(".addCostType").hide();
            }
        }

        $('.avgBear').click(function () {
            avgBear()
        })
        $('.bearInfo').on('blur', '.bearRate', function () {
            var _this = $(this)
            if (_this.val() && !checkPapers('money', _this.val())) {
                layer.msg('输入格式不符合规范(小数点两位内),已自动格式化', {
                    time: 2000,
                    icon: 7
                })
                _this.val(Math.round(_this.val() * 100) / 100)
            }
        })

        $('.content-bar').on('click', '.delBear', function () {
            $(this).parents('.i-tr').detach()
            avgBear();
            showBtn();
        })

        function avgBear(){
            var len = $('.bearInfo .i-tr').length
            var avgRate = Math.round(100 / len * 100) / 100
            $('.bearInfo .bearRate').val(avgRate)
            var sum = 0
            $('.costTypes .costMoney').map(function (i, cur) {
                sum = sum + Number($(cur).val())
            })
            var avgMoney = Math.round(sum * 100 / len) / 100
            $('.bearInfo .bearMoney').val(avgMoney)
            if (len > 2) {
                var lastMoney = sum - avgMoney * (len - 1)
                lastMoney = Math.round(lastMoney * 100) / 100
                $('.bearInfo .bearMoney:last').val(lastMoney)
            }
        }

        function setCostType() {
            var lastId = Number($('.costTypes .i-tr:last').attr('data-id'))
            var curId = lastId + 1
            var len = $('.costTypes .i-tr').length
            var class1 = 'democ' + curId
            var class11 = 'costType' + curId
            var uploadFilesClass = 'costFiles' + curId
            var _html = '  <div class="i-tr" data-id="' + curId + '">\n' +
                '                        <div class="i-td wid15">\n' +
                '                            <div class="layui-input-inline">\n' +
                '                                <div id="' + class11 + '" class="selectMul"></div>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="i-td wid15">\n' +
                '                            <div class="layui-input-inline">\n' +
                '                                <input type="number" name="costMoney" placeholder="请输入" class="costMoney layui-input">\n' +
                '                                <div class="unit">元</div>' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="i-td wid32">\n' +
                '                            <div class="layui-input-inline">\n' +
                '                                <textarea name="costDesc" style="height: 50px" placeholder="请输入" class="costDesc layui-input" />\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="i-td wid32">\n' +
                '                            <div class=\'files\' id="' + uploadFilesClass + '">\n' +
                '                                <div class="file-add uploadFile add-'+uploadFilesClass+'" >\n' +
                '                                    <div class="icon-add v-p-upload"></div>\n' +
                '                                </div>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="i-td wid6"><span class="delCostType">删除</span></div>\n' +
                '                    </div>'
            '                </div>\n' +

            $('.costTypes .i-table .i-tbody').append(_html)
            window[class1] = xmSelect.render({
                el: '#' + class11,
                theme: {
                    color: '#3BA9FF',
                },
                clickClose: true,
                radio: true,
                filterable: true,
                filterDone: function (val, list) {
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
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur
                                            .name + '</div>'
                                })
                                return _html
                            }
                        },
                    }
                },
                data: costTypeJson
            })
            upload.render({
                elem: '.add-'+uploadFilesClass,
                url: '${ctx}/sftp/survey/uploadSftp', //改成您自己的上传接口
                data: {
                    modelType: 'financial',
                    fileType: 'details'
                },
                multiple: true,
                accept: 'file', //只能上传图片
                // acceptMime: 'image/*',
                before: function (obj) {
                    var _id = $(this.item).parent().attr('id')
                    layer.load();
                    fileLen = $('#'+_id+' .file').length
                },
                done: function (res) {
                    layer.closeAll('loading')

                    if (res.success == 'true') {
                        putSessionFiles(res.surveyFile, $(this.item).parent().attr('id'));
                    } else {
                        layer.alert('导入出错', {
                            icon: 2
                        })
                    }
                },
                allDone: function (obj) {
                    var _id = $(this.item).parent().attr('id')
                    if (fileLen) {
                        window['viewer'+_id].destroy()
                    }
                    window['viewer'+_id] = new Viewer(document.getElementById(_id));
                },
                error: function (index, upload) {
                    layer.closeAll('loading')
                }
            });
        }

        function setBearCell() {
            var lastId = Number($('.bearInfo .i-tr:last').attr('data-id'))
            if (!lastId){
                lastId = 0;
            }
            var curId = lastId + 1
            var len = $('.bearInfo .i-tr').length
            var class1 = 'demob' + curId
            var class11 = 'bearOrg' + curId
            var _html = '  <div class="i-tr" data-id="' + curId + '">\n' +
                '                        <div class="i-td wid15">\n' +
                '                            <div class="layui-input-inline">\n' +
                '                                <div id="' + class11 + '" class="selectMul"></div>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="i-td wid15">\n' +
                '                            <div class="layui-input-inline">\n' +
                '                                <input type="number" name="bearMoney" placeholder="请输入" class="bearMoney layui-input">\n' +
                '                                <div class="unit">元</div>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="i-td wid32">\n' +
                '                            <div class="layui-input-inline">\n' +
                '                                <textarea name="bearDesc" style="height: 50px" placeholder="请输入" class="bearDesc layui-input" />\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="i-td wid6"><span class="delBear">删除</span></div>\n' +
                '                    </div>'
            $('.bearInfo .i-table .i-tbody').append(_html)
            window[class1] = xmSelect.render({
                el: '#' + class11,
                theme: {
                    color: '#3BA9FF',
                },
                clickClose: true,
                radio: true,
                filterable: true,
                filterDone: function (val, list) {
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
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur
                                            .name + '</div>'
                                })
                                return _html
                            }
                        },
                    }
                },
                data: bearOrgJson
            })
        }

        $('.content-bar').on('click', '.file-del', function () {
            var _this = $(this)
            _this.addClass('poi-no')
            setTimeout(function () {
                _this.removeClass('poi-no')
            },3000)
            $.ajax({
                url: "${ctx}/financial/reApply/operate",
                type: 'post',
                data: {
                    btnCode: 'delete-file',
                    fileId: _this.parents('.file').attr('data-id') || ''
                },
                success: function (res) {
                    _this.removeClass('poi-no')
                    var res = JSON.parse(res);
                    if (res.isSuccess) {
                        layer.msg('成功', {
                            time: 1000,
                            icon: 1
                        }, function () {
                            var _id = _this.parents('.files').attr('id')
                            _this.parent().detach()
                            window['viewer'+_id].destroy()
                            if ($('#'+_id+' .file').length) {
                                window['viewer'+_id] = new Viewer(document.getElementById(_id));
                            }
                        })
                    } else {
                        layer.msg('失败', {
                            time: 1000,
                            icon: 2
                        })
                    }
                }
            })
        })

        function putSessionFiles(surveyFile, id) {
            if(!surveyFile){
                return;
            }
            // var _html = ' <div class="file" data-type="new">\n' +
            //     '                        <img src="' + surveyFile.filePath + '" class="image"></img>\n' +
            //     // '<div class="file-title">' + surveyFile.fileName + '</div>\n' +
            //     '                        <div class="file-del"></div>'+
            // '                    </div>';
            var file = surveyFile.filePath
            var type = file.slice(id + 1)
            var _htmlFile = '<img src="' + surveyFile.filePath + '" class="image" />'
            var _htmlName =  '<div class="file-title">' + surveyFile.fileName + '</div>\n'
            var _htmlDel = '<div class="file-del"></div>'
            var imgArr = ['png', 'jpg', 'gif', 'bmp', 'jpeg']
            if (imgArr.indexOf(type) == -1) {
                _htmlFile =  '<div target="_blank"  class="image  img-file"></div>'
                _htmlName = '<a class="file-title"  target="_blank"  href="'+surveyFile.filePath+'" download="'+surveyFile.filePath+'" title="'+surveyFile.fileName+'">' + surveyFile.fileName + '</a>\n'
            }
            var _html = ' <div class="file" data-type="new" >' +
                _htmlFile+
                _htmlName+ _htmlDel+
                ' </div>'
            $('#' + id).append(_html)
        }
        function setFileHtml(surveyFile,idName,isNewFlag) {
            if(!surveyFile){
                return;
            }
            var _htmlDel = ''
            var isNew = ''
            var isNewFlag = isNewFlag || false
            if (isNewFlag){
                isNew = 'data-type="new"'
            }
            var _fileId = surveyFile.id ||''

            _htmlDel = '<div class="file-del" data-id="'+_fileId+'"></div>'

            var _htmlFile = '<img src="' + surveyFile.filePath + '" class="image"></img>'
            var _htmlName =  '<div class="file-title">' + surveyFile.fileName + '</div>\n'

            var file = surveyFile.filePath
            var id = file.lastIndexOf('.')
            var type = file.slice(id + 1)
            var imgArr = ['png', 'jpg', 'gif', 'bmp', 'jpeg']
            var fileArr = ['png', 'jpg', 'gif', 'bmp', 'jpeg','xls','xlsx','doc','docx','pdf','ppt','pptx','zip','rar','7z','CAB','ARJ','LZH','TAR','GZ','ACE','UUE','BZ2','JAR','ISO']
            if (imgArr.indexOf(type) == -1) {
                _htmlFile =  '<div target="_blank"  class="image  img-file"></div>'
                var _htmlTarget = ''
                if (fileArr.indexOf(type) == -1) {
                    _htmlTarget ='target="_blank"'
                }
                _htmlName = '<a class="file-title"  target="_blank"  href="'+surveyFile.filePath+'" download="'+surveyFile.filePath+'" title="'+surveyFile.fileName+'">' + surveyFile.fileName + '</a>\n'
            }
            var _html = ' <div class="file" '+isNew+' >' +
                _htmlFile+
                _htmlName+ _htmlDel+
                ' </div>'
            $('#' + idName).append(_html)
        }



        $('.lf-btn').click(function () {
            var requiredFlag = true

            var _this = $(this)
            var _flag = true
            var costArr = [],
                bearArr = []


            var fileArr = $('#uploadFiles .file')
            var files = []
            fileArr.map(function (i, cur) {
                // if ($(cur).find('img').attr('src')  ) {
                //     files.push({
                //         filePath: $(cur).find('img').attr('src')
                //     })
                // }
                if ($(cur).find('img').length > 0 && $(cur).attr('data-type') == 'new'){
                    files.push({
                        filePath: $(cur).find('img').attr('src')
                    })
                }else  if ($(cur).find('a').length > 0 && $(cur).attr('data-type') == 'new'){
                    files.push({
                        filePath: $(cur).find('a').attr('href')
                    })
                }
            })

            var params = {
                btnCode: "add-info",
                reType: $('#reType').val(),
                financialReApplyId: $("#copy").val() == 'copy' ? "-1" : $('#financialReApplyId').val(),//如果是复制则ID传-1
                urgeType : '',
                reReasons: $('.reReasons').val(),
                companyId: demo1.getValue('valueStr'),
                applyDesc: $('.applyDesc').val(),
                payeeName: $('.payeeName').val(),
                payeeNo: $('.payeeNo').val(),
                bankId: demo2.getValue('valueStr'),
                branchBank: $('.branchbank').val(),
                files: JSON.stringify(files),
                copy : $("#copy").val()
                // , bearOrgId: demo3.getValue('valueStr')
            }
            if (params.reType == 1 || params.reType == 3){
                Object.assign(params,{
                    payUserId: demo6 ? demo6.getValue('valueStr') : ""
                })
            }

            var ids = []

            var idsS = []
           if (params.reType == 1 || params.reType == 2){
               var sumM1 = 0, sumM2 = 0
               $('.costTypes .i-tr').map(function (i, cur) {
                   var id = $(cur).attr('data-id')
                   var class1 = 'democ' + id
                   var fileArr = $(cur).find('.files .file')
                   var files = [],allFiles=[]
                   fileArr.map(function (i, cur) {
                       // if ($(cur).find('img').attr('src')) {
                       //     allFiles.push({
                       //         filePath: $(cur).find('img').attr('src')
                       //     })
                       //     if ($(cur).attr('data-type') == 'new'){
                       //         files.push({
                       //             filePath: $(cur).find('img').attr('src')
                       //         })
                       //     }
                       // }

                       if ($(cur).find('img').length > 0){
                           files.push({
                               filePath: $(cur).find('img').attr('src')
                           })
                       }else  if ($(cur).find('a').length > 0){
                           files.push({
                               filePath: $(cur).find('a').attr('href')
                           })
                       }
                   })
                   if (!$(cur).find('.costMoney').val() || !window[class1].getValue('valueStr') || (!files.length && params.reType == 1)){
                       requiredFlag = false
                   }
                   sumM1 += Number($(cur).find('.costMoney').val())
                   costArr.push({
                       id: $(cur).attr('data-cellId') || '',
                       financialReApplyId:$(cur).attr('data-financialReApplyId') || '',
                       costTypeId: window[class1].getValue('valueStr'),
                       costMoney: $(cur).find('.costMoney').val() || '',
                       costDesc: $(cur).find('.costDesc').val() || '',
                       financialFileList: files
                   })
               })
               $('.bearInfo .i-tr').map(function (i, cur) {
                   var id = $(cur).attr('data-id')
                   var class1 = 'demob' + id
                   ids.push(window[class1].getValue('valueStr'))
                   if (!$(cur).find('.bearMoney').val() || !window[class1].getValue('valueStr')){
                       requiredFlag = false
                   }
                   sumM2 += Number($(cur).find('.bearMoney').val())
                   bearArr.push({
                       id: $(cur).attr('data-cellId') || '',
                       financialReApplyId:$(cur).attr('data-financialReApplyId') || '',
                       departmentId: window[class1].getValue('valueStr'),
                       shareCost: $(cur).find('.bearMoney').val() || '',
                       costDesc: $(cur).find('.bearDesc').val() || '',
                   })
               })
               Object.assign(params,{
                   costTypes: JSON.stringify(costArr),
                   bearInfos: JSON.stringify(bearArr),
                   reMoney: sumM1
               })
               idsS = removeRepeat1(ids)
           }
            if (params.reType == 3){
                Object.assign(params,{
                    repaymentMoney: $('.repaymentMoney').val(),
                    reMoney: $('.repaymentMoney').val()
                })
            }

            if (_this.attr('data-id') == '1') {
                closeDialog()
                return;
            }
            if (_this.attr('data-id') == '2') {
                params.urgeType = 'storage'
            }
            if (_this.attr('data-id') == '3') {
                params.urgeType = 'add'
                // if (!params.bearOrgId){
                //     layer.msg('请选择承担部门！', {
                //         time: 1000,
                //         icon: 5
                //     })
                //     return
                // }
                if (!params.reReasons){
                    layer.msg('请填写事由！', {
                        time: 1000,
                        icon: 5
                    })
                    return
                }
                if (!params.companyId){
                    layer.msg('请选择公司抬头！', {
                        time: 1000,
                        icon: 5
                    })
                    return
                }
                if(params.reType == 1 || params.reType == 3){
                    if (!params.payUserId){
                        layer.msg('请填写收款人！', {
                            time: 1000,
                            icon: 5
                        })
                        return
                    }
                }else if(params.reType == 2){
                    if (!params.payeeName){
                        layer.msg('请填写收款人！', {
                            time: 1000,
                            icon: 5
                        })
                        return
                    }
                }
                if (!params.payeeNo){
                    layer.msg('请填写收款账户！', {
                        time: 1000,
                        icon: 5
                    })
                    return
                }
                if (!params.bankId){
                    layer.msg('请选择银行！', {
                        time: 1000,
                        icon: 5
                    })
                    return
                }
                if (!params.branchBank){
                    layer.msg('请填写支行！', {
                        time: 1000,
                        icon: 5
                    })
                    return
                }
                if (params.reType == 1 || params.reType == 2){
                    if (!requiredFlag){
                        layer.msg('请填写必填项(*)！', {
                            time: 1000,
                            icon: 5
                        })
                        return
                    }
                }
                if (params.reType == 3){
                    if (!params.repaymentMoney){
                        layer.msg('请填写借款金额！', {
                            time: 1000,
                            icon: 5
                        })
                        return
                    }

                }
            }

            if (params.reType == 1 || params.reType == 2){
               if (idsS.length != ids.length){
                   layer.msg('承担部门不可重复选择！', {
                       time: 1000,
                       icon: 2
                   })
                   return
               }
                if (sumM1.toFixed(2) != sumM2.toFixed(2)){
                    layer.msg('承担总金额必须等于费用总金额！', {
                        time: 1000,
                        icon: 2
                    })
                    return
                }
            }

            var _flag = true
            layer.confirm('确认操作？', ['取消', '确定'], function () {
                if (_flag) {
                    _flag = false
                    var loadIndex = layer.load()
                    $.ajax({
                        url: "${ctx}/financial/reApply/operate",
                        type: 'post',
                        data: params,
                        success: function (res) {
                            var res = JSON.parse(res);
                            if (res.isSuccess) {
                                layer.msg('成功', {
                                    time: 1000,
                                    icon: 1
                                }, function () {
                                    // parent.reload()
                                    closeDialog()
                                    $(parent.document).find('.ll-submit').click()
                                })
                            } else {
                                layer.close(loadIndex)
                                layer.msg('失败', {
                                    time: 1000,
                                    icon: 2
                                })
                            }
                        }
                    })
                }
            }, function () {
                _flag = true
            })

        })


        function mapCostType(item,i) {
            var financialFileList = item.financialFileList;
            var curId = i
            var len = $('.costTypes .i-tr').length
            var class1 = 'democ' + curId
            var class11 = 'costType' + curId
            var uploadFilesClass = 'costFiles' + curId
            var fileHtml =      '                            <div class="file-add uploadFileAdd">\n' +
                '                                <div class="icon-add v-p-upload"></div>\n' +
                '                            </div>\n'
            if (financialFileList && financialFileList.length){
                if ($("#copy").val() == 'copy'){
                    // item.financialFileList.removeAll();
                    financialFileList = [];
                }
                financialFileList.map(function (cur) {
                    var file = cur.filePath;
                    var type = file.substr(file.lastIndexOf(".") + 1);
                    var _htmlFile = '<img src="' + cur.filePath + '" class="image" />'
                    var _htmlName =  '<div class="file-title">' + cur.fileName + '</div>\n'
                    var _htmlDel = '<div class="file-del"></div>'
                    var imgArr = ['png', 'jpg', 'gif', 'bmp', 'jpeg']
                    if (imgArr.indexOf(type) == -1) {
                        _htmlFile =  '<div target="_blank"  class="image  img-file"></div>'
                        _htmlName = '<a class="file-title"  target="_blank"  href="'+cur.filePath+'" download="'+cur.filePath+'" title="'+cur.fileName+'">' + cur.fileName + '</a>\n'
                    }
                    fileHtml += ' <div class="file" data-id="'+cur.id+'" >' +
                        _htmlFile+
                        _htmlName+ _htmlDel+
                        ' </div>'

                    // fileHtml += ' <div class="file" data-id="'+cur.id+'">\n' +
                    //     '                        <img src="' + cur.filePath + '" class="image" />\n' +
                    //     '                        <div class="file-del"></div>'+
                    // '                    </div>'
                })
            }
            var desc = item.costDesc || ''
            var delHtml = '<span class="delCostType">删除</span>'
            if (i == 1){
                delHtml = ''
            }
            var _html = '  <div class="i-tr" data-id="' + curId + '" data-financialReApplyId="'+item.financialReApplyId+'" data-cellId="'+item.id+'">\n' +
                '                        <div class="i-td wid15">\n' +
                '                            <div class="layui-input-inline">\n' +
                '                                <div id="' + class11 + '" class="selectMul"></div>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="i-td wid15">\n' +
                '                            <div class="layui-input-inline">\n' +
                '                                <input type="number"  value="'+item.costMoney+'" name="costMoney" placeholder="" class="costMoney layui-input">\n' +
                '                                <div class="unit">元</div>' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="i-td wid32">\n' +
                '                            <div class="layui-input-inline">\n' +
                '                                <textarea name="costDesc"  style="height: 50px"  placeholder="" class="costDesc layui-input">'+desc+'</textarea>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="i-td wid32">\n' +
                '                            <div class=\'files\' id="' + uploadFilesClass + '">\n' +
                fileHtml+
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="i-td wid6">'+delHtml+'</div>\n' +
                '                    </div>'+
                '                </div>\n'

            $('.costTypes .i-table .i-tbody').append(_html)
            if (financialFileList && financialFileList.length) {
                window['viewer' + uploadFilesClass] = new Viewer(document.getElementById(uploadFilesClass));
            }
            window[class1] = xmSelect.render({
                el: '#' + class11,
                theme: {
                    color: '#3BA9FF',
                },
                clickClose: true,
                radio: true,
                filterable: true,
                filterDone: function (val, list) {
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
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur
                                            .name + '</div>'
                                })
                                return _html
                            }
                        },
                    }
                },
                data: costTypeJson
            })
            var class1Id = item.costTypeId || ''
            window[class1].setValue([class1Id])
        }

        function mapBearCell(item,i) {
            var curId = i
            var class1 = 'demob' + curId
            var class11 = 'bearOrg' + curId
                var desc = item.costDesc || ''
            var delHtml = '<span class="delBear">删除</span>'
            if (i == 1){
                delHtml = ''
            }
            var _html = '  <div class="i-tr" data-id="' + curId + '" data-financialReApplyId="'+item.financialReApplyId+'" data-cellId="'+item.id+'">\n' +
                '                        <div class="i-td wid15">\n' +
                '                            <div class="layui-input-inline">\n' +
                '                                <div id="' + class11 + '" class="selectMul"></div>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="i-td wid15">\n' +
                '                            <div class="layui-input-inline">\n' +
                '                                <input type="number"  value="'+item.shareCost+'" name="bearMoney" placeholder="" class="bearMoney layui-input">\n' +
                '                                <div class="unit">元</div>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="i-td wid32">\n' +
                '                            <div class="layui-input-inline">\n' +
                '                                <textarea  name="bearDesc"  style="height: 50px"  placeholder="" class="bearDesc layui-input">'+desc+'</textarea> \n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="i-td wid6">'+delHtml+'</div>\n' +
                '                    </div>'
            $('.bearInfo .i-table .i-tbody').append(_html)
            window[class1] = xmSelect.render({
                el: '#' + class11,
                theme: {
                    color: '#3BA9FF',
                },
                clickClose: true,
                radio: true,
                filterable: true,
                filterDone: function (val, list) {
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
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur
                                            .name + '</div>'
                                })
                                return _html
                            }
                        },
                    }
                },
                data: bearOrgJson
            })
            window[class1].setValue([item.departmentId])
            if ($('#updateOrgan').val() == '0'){
                window[class1].update({ disabled: true });
            }
        }

        function removeRepeat1(arr){
            var removeArr = [],obj = {};
            var leng = arr.length
            for(var i = 0, l = leng; i < l; i++){
                if(!obj[arr[i]]){
                    removeArr.push(arr[i]);
                    obj[arr[i]] = 1;
                }
            };
            return removeArr;
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
                ['email', /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/],
                ['num_0_1', /^(0+(\.[0-9]{1,2})?|1|1.0|1.00?)$/]
            ])
            if (map.get(parama).test(paramb)) {
                return true
            } else {
                return false
            }
        }

        function closeDialog(){
            var closeBtn = $("#diglog_close_btn");
            if(closeBtn.length == 0){
                closeBtn = $("#diglog_close_btn",window.parent.document);
            }
            closeBtn.click();
        }

        //代码如下所示：
        function convertCurrency(money) {
            //汉字的数字
            var cnNums = new Array('零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖');
            //基本单位
            var cnIntRadice = new Array('', '拾', '佰', '仟');
            //对应整数部分扩展单位
            var cnIntUnits = new Array('', '万', '亿', '兆');
            //对应小数部分单位
            var cnDecUnits = new Array('角', '分', '毫', '厘');
            //整数金额时后面跟的字符
            var cnInteger = '整';
            //整型完以后的单位
            var cnIntLast = '元';
            //最大处理的数字
            var maxNum = 999999999999999.9999;
            //金额整数部分
            var integerNum;
            //金额小数部分
            var decimalNum;
            //输出的中文金额字符串
            var chineseStr = '';
            //分离金额后用的数组，预定义
            var parts;
            if (money == '') { return ''; }
            money = parseFloat(money);
            if (money >= maxNum) {
                //超出最大处理数字
                return '';
            }
            if (money == 0) {
                chineseStr = cnNums[0] + cnIntLast + cnInteger;
                return chineseStr;
            }
            //转换为字符串
            money = money.toString();
            if (money.indexOf('.') == -1) {
                integerNum = money;
                decimalNum = '';
            } else {
                parts = money.split('.');
                integerNum = parts[0];
                decimalNum = parts[1].substr(0, 4);
            }
            //获取整型部分转换
            if (parseInt(integerNum, 10) > 0) {
                var zeroCount = 0;
                var IntLen = integerNum.length;
                for (var i = 0; i < IntLen; i++) {
                    var n = integerNum.substr(i, 1);
                    var p = IntLen - i - 1;
                    var q = p / 4;
                    var m = p % 4;
                    if (n == '0') {
                        zeroCount++;
                    } else {
                        if (zeroCount > 0) {
                            chineseStr += cnNums[0];
                        }
                        //归零
                        zeroCount = 0;
                        chineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
                    }
                    if (m == 0 && zeroCount < 4) {
                        chineseStr += cnIntUnits[q];
                    }
                }
                chineseStr += cnIntLast;
            }
            //小数部分
            if (decimalNum != '') {
                var decLen = decimalNum.length;
                for (var i = 0; i < decLen; i++) {
                    var n = decimalNum.substr(i, 1);
                    if (n != '0') {
                        chineseStr += cnNums[Number(n)] + cnDecUnits[i];
                    }
                }
            }
            if (chineseStr == '') {
                chineseStr += cnNums[0] + cnIntLast + cnInteger;
            } else if (decimalNum == '') {
                chineseStr += cnInteger;
            }
            return chineseStr;
        }


        $("#repaymentMoney").blur(function(){
            var _this = $(this);
            $('#sumMoneyBlock').html(convertCurrency(_this.val()))
        })


    })



</script>



</body>

</html>