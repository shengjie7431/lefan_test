<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">

    <style>
        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }
        .form-group{
            overflow: visible!important;
        }

        .data-choose {
            display: flex;
            align-items: center;
        }

        .data-choose input {
            width: 100px;
            height: 32px;
        }

        .data-choose .dc-span {
            width: 30px;
            text-align: center;
        }
        .layui-form {
            margin: 50px auto;
            width: 99%;
            max-height: 300px;
            overflow: auto;
        }

        .layui-form-item {
            text-align: center
        }

        .layui-inline {
            text-align: center
        }

        .c-cell {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .layui-input-inline {
            width: 270px !important;
        }

        .lf-btns {
            padding-top: 10px;
            width: 100%;
            display: flex;
            justify-content: center;
        }

        .lf-btns .lf-btn {
            width: 140px;
            height: 40px;
            line-height: 40px;
            border: 1px solid #3BA9FF;
            color: #3BA9FF;
            text-align: center;
            margin-left: 16px;
            cursor: pointer;
        }

        .lf-btns .lf-btn.active {
            background-color: #3BA9FF;
            color: #fff;
        }

        .checkBoxH {
            display: inline-block;
            width: 20px;
            height: 20px;
            border: 1px solid #333;
            border-radius: 13px;
            box-sizing: content-box;
        }

        .checkBoxH.active {
            display: inline-block;
            width: 22px;
            height: 22px;
            background: url('data:image/png;base64,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');
            background-position: center;
            background-repeat: no-repeat;
            background-size: contain;
        }




        .form {
            width: 70%;
            margin: 40px auto;
        }

        .form .form-group {
            overflow: auto;
            border-bottom: 1px solid #dadada;
            margin: 0;
            display: flex;
        }

        .form .form-group label {
            line-height: 34px;
            padding: 5px 15px;
            background-color: #eee;
            margin: 0;
        }

        .form .form-group.hide_group {
            display: none;
        }

        .form .form-group .col-sm-9 {
            line-height: 34px;
            padding: 10px;
        }

        /*.form .form-group .col-sm-9 div {*/
            /*z-index: 999;*/
        /*}*/

        .form .company {
            display: none;
        }

        .label-bars {
            width: 100%;
            display: flex;
            flex-wrap: wrap;
        }

        .label-bars .label-bar {
            padding: 0 12px;
            margin: 5px;
            height: 36px;
            line-height: 36px;
            color: #3BA9FF;
            border: 1px solid #3BA9FF;
            background-color: #fff;
            font-size: 14px;
            cursor: pointer;
        }

        .label-bars .label-bar.bType {
            display: none;
        }


        .label-bars .label-bar.active {
            color: #fff;
            background-color: #3BA9FF;
        }

        .close {
            position: relative;
            width: 4px;
            height: 30px;
            background: #ff0000;
            -webkit-transform: rotate(45deg);
            -moz-transform: rotate(45deg);
            -o-transform: rotate(45deg);
            -ms-transform: rotate(45deg);
            transform: rotate(45deg);
            display: inline-block;
        }

        .close:after {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            width: 4px;
            height: 30px;
            background: #ff0000;
            -webkit-transform: rotate(270deg);
            -moz-transform: rotate(270deg);
            -o-transform: rotate(270deg);
            -ms-transform: rotate(270deg);
            transform: rotate(270deg);
        }

        .buildSelect {
            background: #fff;
            margin: 0 5px;
            float: left;
            cursor: pointer;
            position: relative;
            display: none;
        }

        .sel_show {
            position: absolute;
            width: 100%;
            height: 24px;
            top: 0;
            left: 0;
            background: url(about:blank);
        }

        .text {
            width: 100%;
            height: 24px;
            border: 1px solid #dcdcdc;
            padding: 0 10px;
            color: #555;


        }

        .text i {
            display: inline-block;
            width: 14px;
            line-height: 23px;
            float: right;
            color: #d8d8d8;
            text-align: right;
            margin-left: 10px;
            font-size: 18px;
        }

        .on_cli {
            border-color: #333;
            color: #333;


        }

        .on_cli i {
            color: #333;
        }

        .selectList {
            width: 100%;
            display: none;
            padding: 5px 0;
            border: 1px solid #da2d29;
            border-top: none;


        }

        .selectList li {
            height: 26px;
            line-height: 26px;
            color: #555;
            padding-left: 10px;
            cursor: pointer;
        }

        .selectList li:hover {
            color: #333;
        }
        #div_btn_2{
            width:70%;
            margin: 0 auto;
        }
        .isZhongan{
            display: none;
        }
        .poi-no{
            pointer-events: none;
        }
        .disabled{
            background-color: #f6f6f6;
        }
    </style>

</head>
<body id="editBody">
    <div class="form">
        <form id="editForm" >
        <input type="hidden" value="${dto.id}" name="finaInfoId" id="finaInfoId" />
        <input type="hidden" value="${btnCode}" name="btnCode" id="btnCode" />
        <input type="hidden" value="${urgeType}" name="urgeType" id="urgeType" />
        <input type="hidden" value="${transferCloseType}" name="transferCloseType" id="transferCloseType" />
        <input type="hidden" value='${params.consignorJson}'  id="consignorJson" />
        <input type="hidden" value='${params.finaDiagnosisInfoJson}'  id="finaDiagnosisInfoJson" />

        <input type="hidden" value="${dto.entrustOrgId}" name="entrustOrgId" id="entrustOrgId" />
        <input type="hidden" value="${dto.finaDiagnosisTreatment.diagnosisId}" name="diagnosisId" id="diagnosisId" />
        <input type="hidden" value="${dto.hospitalId}" name="hospitalId" id="hospitalId" />
        <input type="hidden" value="${dto.hospitalName}" name="hospitalName" id="hospitalName" />


            <div class="form-group">
            <label class="col-sm-3 control-label">保险公司<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <%--<select name="entrustOrg" id="selectCompany" class="singleSelect form-control">--%>
                    <%--<option value="">请选择保险公司</option>--%>
                    <%--<c:forEach items="${consignors}" var="item">--%>
                        <%--<option value="${item.id}">${item.name}</option>--%>
                    <%--</c:forEach>--%>
                    <%--&lt;%&ndash;根据保险公司及保单号判断是否为相同案件，且历史案件的状态必须在出院结算中 之前 则弹出此框--%>
                    <%--接口：/fina/applicant/ajaxData?dataType=same-case&insurancePolicyNo=**&entrustOrgId=**&ndash;%&gt;--%>
                <%--</select>--%>
                <div id="entrustOrgIds" class="selectMul"></div>

            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">申请人姓名<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <input type="text" class="form-control updateCaseNo" name="finaUserName" id="finaUserName" value="${dto.finaUserName}" placeholder="请输入申请人姓名" required="required" onkeyup="this.value=this.value.replace(/\s+/g,'')" <c:if test="${urgeType =='transfer'}"> disabled="disabled"</c:if>>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">申请人身份证号<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <input type="text" class="form-control" name="finaUserIdcard" id="finaUserIdcard" value="${dto.finaUserIdcard}" placeholder="请输入申请人身份证号" required="required" <c:if test="${urgeType =='transfer'}"> disabled="disabled"</c:if>>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">申请人手机号<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <input type="text" class="form-control" name="finaUserTel" id="finaUserTel" value="${dto.finaUserTel}" placeholder="请输入申请人手机号" required="required" <c:if test="${urgeType =='transfer'}"> disabled="disabled"</c:if>>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">申请人与被保人关系<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <div class="label-bars" id="relationships" name="relationships">
                    <div class="label-bar" data-id="1">本人</div>
                    <div class="label-bar" data-id="2">投保人</div>
                    <div class="label-bar" data-id="3">直系亲属</div>
                </div>
            </div>
            <input type="hidden" value="${dto.relationship}" id="relationship" name="relationship" required="required"/>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">被保险人姓名<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <input type="text" class="form-control" name="insuredName" id="insuredName" value="${dto.insuredName}" placeholder="请输入被保险人姓名" required="required" <c:if test="${urgeType =='transfer'}"> disabled="disabled"</c:if>>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">被保险人身份证号<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <input type="text" class="form-control" name="insuredIdcard" id="insuredIdcard" value="${dto.insuredIdcard}" placeholder="请输入被保险人身份证号" required="required" <c:if test="${urgeType =='transfer'}"> disabled="disabled"</c:if>>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">被保险人手机号<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <input type="text" class="form-control" name="insuredTel" id="insuredTel" value="${dto.insuredTel}" placeholder="请输入被保险人手机号" required="required" <c:if test="${urgeType =='transfer'}"> disabled="disabled"</c:if>>
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-3 control-label">申请垫付金额</label>
            <div class="col-sm-9 input-group">
                <input type="text" class="form-control" step="0.01" id="applyAdvanceMoney" name="applyAdvanceMoney" value="${dto.applyAdvanceMoney}" placeholder="请输入申请垫付金额">
                <div class="input-group-addon">元</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">保单号<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <input type="text" id="insurancePolicyNo" name="insurancePolicyNo" value="${dto.insurancePolicyNo}" placeholder="请输入保单号" class="form-control updateCaseNo" <c:if test="${urgeType =='transfer'}"> disabled="disabled"</c:if>>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">首次投保日期<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <input type="text" class="layui-input" readonly id="firstInsureTime" name="firstInsureTime" <c:if test="${urgeType =='transfer'}"> disabled="disabled"</c:if>
                       value="<fmt:formatDate value="${dto.firstInsureTime}" pattern="yyyy-MM-dd"/>"
                       placeholder="请选择日期" style="width: 100%;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">保额</label>
            <div class="col-sm-9 input-group">
                <input type="text" class="form-control" step="0.01" id="insureMoney" name="insureMoney" value="${dto.insureMoney}" placeholder="请输入保额">
                <div class="input-group-addon">元</div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">险种<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <input type="text" id="insureType" name="insureType" value="${dto.insureType}" placeholder="请输入险种" class="form-control" <c:if test="${urgeType =='transfer'}"> disabled="disabled"</c:if>>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">保险期限<em style="color: red;">*</em></label>
            <div class="col-sm-9" style="display: flex;align-items: center">
                <div class="data-choose">
                    <input type="text" class="layui-input paramTime" readonly id="insureStartTime" <c:if test="${urgeType =='transfer'}"> disabled="disabled"</c:if>
                           value="<fmt:formatDate value="${dto.insureStartTime}" pattern="yyyy-MM-dd"/>"
                           placeholder="请选择日期" style="width: 200px">
                    <span class="dc-span">至</span>
                    <input type="text" class="layui-input paramTime" readonly id="insureEndTime" <c:if test="${urgeType =='transfer'}"> disabled="disabled"</c:if>
                           value="<fmt:formatDate value="${dto.insureEndTime}" pattern="yyyy-MM-dd"/>"
                           placeholder="请选择日期" style="width: 200px">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">免赔额</label>
            <div class="col-sm-9 input-group">
                <input type="text" class="form-control" step="0.01" id="deductibleMoney" name="deductibleMoney" value="${dto.insureMoney}" placeholder="请输入免赔额">
                <div class="input-group-addon">元</div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">出险原因<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <div class="label-bars" id="outInsureType" name="outInsureType">
                    <div class="label-bar" data-id="1">意外</div>
                    <div class="label-bar" data-id="2">疾病</div>
                    <div class="label-bar" data-id="3">其他</div>
                </div>
                <input type="hidden" value="${dto.outInsureType}" id="outInsureTypeId" name="outInsureTypeId" required="required"/>
                <input type="text" id="outInsureReason" name="outInsureReason" value="${dto.outInsureReason}" placeholder="请输入原因" class="form-control" style="display: none"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">主要诊断<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <%--<select name="entrustOrg" id="selectCompany" class="singleSelect form-control">--%>
                    <%--<option value="">请选择主要诊断</option>--%>
                    <%--<c:forEach items="${finaDiagnosisInfos}" var="item">--%>
                        <%--<option value="${item.id}">${item.diagnosisName}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select--%>
                <div id="diagnosisIds" class="selectMul"></div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">就诊医院<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <%--<select name="entrustOrg" id="selectCompany" class="singleSelect form-control">--%>
                    <%--<option value="">请选择就诊医院</option>--%>

                    <%--&lt;%&ndash;路径 /fina/pub/ajaxData?dataType=hospital-info-list&hospitalName= ,分页形式能否实现&ndash;%&gt;--%>
                <%--</select>--%>
                    <div id="hospitalIds" class="selectMul"></div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">就诊科室<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <input type="text" id="department" name="department" value="${dto.department}" placeholder="请输入就诊科室" class="form-control" <c:if test="${urgeType =='transfer'}"> disabled="disabled"</c:if>>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">入院时间<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <input type="text" class="layui-input inHospitalTime" readonly id="inHospitalTime" <c:if test="${urgeType =='transfer'}"> disabled="disabled"</c:if>
                       value="<fmt:formatDate value="${dto.inHospitalTime}" pattern="yyyy-MM-dd"/>"
                       placeholder="请选择日期" style="width: 100%;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">业务类型<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <div class="label-bars" id="businessTypes" name="businessTypes">
                    <div class="label-bar" data-id="1">仅垫付</div>
                    <div class="label-bar" data-id="2">垫付+调查</div>
                </div>
            </div>
            <input type="hidden" value="${dto.businessType}" id="businessType" name="businessType" required="required"/>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">首次联系信息<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <textarea name="firstContactDesc" id="firstContactDesc" class="form-control" cols="30" rows="6" placeholder="请输入首次联系信息" <c:if test="${urgeType =='transfer'}"> disabled="disabled"</c:if>>${dto.firstContactDesc}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">案件编号<em style="color: red;">*</em></label>
            <div class="col-sm-9">
                <input type="text" class="form-control" id="caseApplicantNo" name="caseApplicantNo" value="${dto.caseApplicantNo}" placeholder="请输入案件编号" required="required">
            </div>
        </div>

        <div id="div_btn_1" class="modal-footer">
                <c:if test="${urgeType == 'add' || urgeType == 'transfer'}">
                    <button type="button" lay-submit lay-filter="submit" name="btnUpload" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>下一步</button>
                </c:if>
                <c:if test="${urgeType == 'update'}">
                    <button type="button" lay-submit lay-filter="submit" name="btnUpload" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>提交</button>
                </c:if>
            </div>
    </form>
        <div id="div_btn_2" style="display: none" class="modal-footer">
            <c:if test="${urgeType == 'update'}">
                <button type="submit" lay-filter="submit" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            </c:if>
            <c:if test="${urgeType == 'add' || urgeType == 'transfer'}">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="operate(1200,true)">受理通过并关闭</button>
                <button type="button" class="btn btn btn-default" data-dismiss="modal" onclick="operate(1300,true)">受理通过并分派调查员</button>
            </c:if>
        </div>
    </div>
</div>

<script type="text/html" id="checkSame">
    <div class="layui-form">
    </div>
    <div class="lf-btns">
        <div class="lf-btn" data-id="1">取消</div>
        <div class="lf-btn active" data-id="2">是，补充垫付</div>
    </div>
</script>
    <%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<%--<script src="${ctx}/js/jquery-3.4.1.js" charset="utf-8"></script>--%>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })
    layui.use(['layer','laydate','xmSelect','form','jquery','util'],function () {
        var layer = layui.layer,
            laydate = layui.laydate,
            form = layui.form,
            xmSelect = layui.xmSelect,
            $ = layui.jquery,
            util = layui.util

        var demo1 = xmSelect.render({
            el: '#entrustOrgIds',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            radio: true,
            clickClose: true,
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            toolbar: {
                show: true,
                list: ['CLEAR']
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
            data: [],
            on: function(data){
                //arr:  当前多选已选中的数据
                var arr = data.arr;
                if (arr.length){
                    detectCaseNo(arr[0].value,'')
                }

            },
        })
        var demo2 = xmSelect.render({
            el: '#diagnosisIds',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            radio: true,
            clickClose: true,
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            toolbar: {
                show: true,
                list: ['CLEAR']
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
        var demo3 = xmSelect.render({
            el: '#hospitalIds',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            radio: true,
            clickClose: true,
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            paging: true,
            toolbar: {
                show: true,
                list: ['CLEAR']
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
            data: [],
            remoteSearch: true,
            remoteMethod: function(val, cb, show){
                //这里如果val为空, 则不触发搜索
                if(!val){
                    return cb([]);
                }
                $.ajax({
                    url: '${ctx}/fina/pub/ajaxData',
                    type: 'post',
                    data: {
                        dataType: 'hospital-info-list',
                        hospitalName: val
                    },
                    success: function (res) {
                        var res =JSON.parse(res)
                        var hospitals = filterJson(demo3,res.results,'id','hospitalName',true, false)
                        cb(hospitals)
                    }
                })
            }
        })

        var firstInsureTime = laydate.render({
            elem: '#firstInsureTime',
            value:'',
            trigger: 'click'
        });
        var insureStartTime = laydate.render({
            elem: '#insureStartTime',
            value:'',
            trigger: 'click'
        });
        var insureEndTime = laydate.render({
            elem: '#insureEndTime',
            value:'',
            trigger: 'click'
        });
        var inHospitalTime = laydate.render({
            elem: '#inHospitalTime',
            value:'',
            trigger: 'click'
        });

        var finaDiagnosisInfoJson = $("#finaDiagnosisInfoJson").val();
        finaDiagnosisInfoJson = JSON.parse(finaDiagnosisInfoJson);

        filterJson(demo2,finaDiagnosisInfoJson,'id','diagnosisName',false, false)
        demo2.setValue([$("#diagnosisId").val()])

        var hospitalList = []

        $.ajax({
            url: '${ctx}/fina/pub/ajaxData',
            type: 'post',
            data: {
                dataType: 'entrust-org-list',
            },
            success: function (res) {
                var res =JSON.parse(res)
                filterJson(demo1,res.results,'id','name',false, false)
                demo1.setValue([$("#entrustOrgId").val()])

            }
        })

        var _hName = $("#hospitalName").val()
        if (_hName){
            $.ajax({
                url: '${ctx}/fina/pub/ajaxData',
                type: 'post',
                data: {
                    dataType: 'hospital-info-list',
                    hospitalName: _hName
                },
                success: function (res) {
                    var res =JSON.parse(res)
                    filterJson(demo3,res.results,'id','hospitalName',false, false)
                    demo3.setValue([$("#hospitalId").val()])
                }
            })
        }


        $('#insurancePolicyNo').blur(function () {
            detectCaseNo()
        })

        <%--接口：/fina/applicant/ajaxData?dataType=same-case&=**&=**&ndash;%&gt;--%>

        function detectCaseNo(id,no) {
            var entrustOrgId = id || demo1.getValue('valueStr')
            var insurancePolicyNo = no || $('#insurancePolicyNo').val()
            console.log(entrustOrgId,insurancePolicyNo)
            if (!entrustOrgId || !insurancePolicyNo){
                return;
            }
            $.ajax({
                url: '${ctx}/fina/applicant/ajaxData',
                type: 'post',
                data: {
                    dataType: 'same-case',
                    entrustOrgId: entrustOrgId,
                    insurancePolicyNo: insurancePolicyNo,
                },
                success: function (res) {
                    var res =JSON.parse(res)
                    if (res.results.length){
                        checkSame(res.results)
                    }
                }
            })
        }
        var interH = ''

        function checkSame(list){
            layer.closeAll()
            openIndex = layer.open({
                type: 1,
                title: '',
                area: ['600px', '500px'],
                content: $('#checkSame').html(),
                success: function () {
                    var _html = ''
                    list.map(function (cur,index) {
                        var _active = ''
                        if (index == 0){
                            _active = 'active'
                        }
                        _html +=
                            '<div class="layui-form-item "><div class="layui-inline" data-id="' +
                            cur.id +
                            '"><div class="c-cell"><label class="layui-form-label" style="width: 100px">案件编号</label><div class="layui-input-inline _input"><input type="text" name="caseNo" value="' +
                            cur.caseApplicantNo +
                            '" autocomplete="off" class="layui-input" disabled></div><span class="checkBoxH '+_active+'"></span></div></div></div>'
                    })
                    $('.layui-form').append(_html)

                    $('.layui-form').on('click', '.layui-inline', function () {
                        var _this = $(this)
                        if (_this.find('.checkBoxH').hasClass('active')) {
                            _this.find('.checkBoxH').removeClass('active')
                        } else {
                            _this.find('.checkBoxH').addClass('active')
                            _this.parent().siblings().find('.checkBoxH').removeClass('active')
                        }
                    })
                    $('.lf-btn').click(function () {
                        var _this = $(this)
                        if (_this.attr('data-id') == 2){
                            var _id = ''
                            var list = $('.layui-form .checkBoxH.active')
                            _id = $('.layui-form .checkBoxH.active').parents('.layui-inline').attr('data-id')

                            if (!_id) {
                                layer.msg('至少选中一条数据', {
                                    time: 1000,
                                    icon: 5
                                })
                                return;
                            }

                            var _flag = true
                            layer.confirm('确认操作？',['取消','确定'],function () {
                                if (_flag){
                                    _flag = false
                                    var loadIndex = layer.load()
                                    $.ajax({
                                        url : "${ctx}/fina/applicant/ajaxData",
                                        type: 'post',
                                        data : {
                                            dataType: 'same-case-info',
                                            finaInfoId: _id,
                                        },
                                        success : function(res){
                                            var res = JSON.parse(res);
                                            if (res.isSuccess){
                                                var results = res.results
                                                 $('#finaInfoId').val(results.finaInfoId)
                                                 $('#urgeType').val('transfer')
                                                 $('#finaUserName').val(results.finaUserName)
                                                 $('#finaUserIdcard').val(results.finaUserIdcard)
                                                 $('#finaUserTel').val(results.finaUserTel)
                                                 $('#insuredName').val(results.insuredName)
                                                 $('#insuredIdcard').val(results.insuredIdcard)
                                                 $('#insuredTel').val(results.insuredTel)
                                                 $('#applyAdvanceMoney').val(results.applyAdvanceMoney)
                                                 $('#insurancePolicyNo').val(results.insurancePolicyNo)
                                                 $('#firstInsureTime').val(util.toDateString(results.firstInsureTime, 'yyyy-MM-dd'))
                                                 $('#insureMoney').val(results.insureMoney)
                                                 $('#insureType').val(results.insureType)
                                                 $('#insureStartTime').val(util.toDateString(results.insureStartTime, 'yyyy-MM-dd'))
                                                 $('#insureEndTime').val(util.toDateString(results.insureEndTime, 'yyyy-MM-dd'))
                                                 $('#deductibleMoney').val(results.deductibleMoney)
                                                 $('#outInsureReason').val(results.outInsureReason)
                                                 $('#department').val(results.department)
                                                 $('#inHospitalTime').val(util.toDateString(results.inHospitalTime, 'yyyy-MM-dd'))
                                                 $('#firstContactDesc').val(results.firstContactDesc)
                                                 $('#caseApplicantNo').val(results.caseApplicantNo)
                                                var _diagnosisId = ''

                                                if (results.finaDiagnosisTreatment && results.finaDiagnosisTreatment.diagnosisId){
                                                    _diagnosisId = results.finaDiagnosisTreatment.diagnosisId
                                                }

                                                demo1.setValue([results.entrustOrgId])
                                                demo2.setValue([_diagnosisId])
                                                demo3.setValue([results.hospitalId])
                                                // if (!hospitalList.length){
                                                //     interH = setInterval(function () {
                                                //        if (hospitalList.length){
                                                //            demo3.setValue([results.hospitalId])
                                                //            clearInterval(interH)
                                                //        }
                                                //    },1000)
                                                // }
                                                // setTimeout(function () {
                                                //     clearInterval(interH)
                                                // }, 30000)
                                                if (results.hospitalName){
                                                    $.ajax({
                                                        url: '${ctx}/fina/pub/ajaxData',
                                                        type: 'post',
                                                        data: {
                                                            dataType: 'hospital-info-list',
                                                            hospitalName: results.hospitalName
                                                        },
                                                        success: function (res) {
                                                            var res =JSON.parse(res)
                                                            filterJson(demo3,res.results,'id','hospitalName',false, false)
                                                            demo3.setValue([results.hospitalId])
                                                        }
                                                    })
                                                }
                                                setActive('#relationships .label-bar', results.relationship, '')
                                                setActive('#outInsureType .label-bar', results.outInsureType, '')
                                                setActive('#businessTypes .label-bar', results.businessType, '')
                                                layer.msg('成功', {
                                                    time: 1000,
                                                    icon: 1
                                                },function(){
                                                    layer.closeAll()
                                                })
                                            }else{
                                                layer.msg(res.msg, {
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
                        }else if (_this.attr('data-id') == 1){
                            layer.close(openIndex)
                        }
                    })

                }
            })
        }


        $('.label-bars').on('click', '.label-bar', function () {
            var _this = $(this)
            if (_this.hasClass('active')){
                _this.removeClass('active')
            }else{
                _this.addClass('active')
                _this.siblings().removeClass('active')
            }
            if (_this.parent().attr('name') == 'outInsureType'){
                if(_this.attr('data-id') == 3){
                    $('#outInsureReason').show()
                }else{
                    $('#outInsureReason').hide()

                }
            }
            if (_this.parent().attr('name') == 'relationships'){
                if(_this.attr('data-id') == 1){
                    $('#insuredName').addClass('disabled').attr('disabled',true).val($('#finaUserName').val())
                    $('#insuredIdcard').addClass('disabled').attr('disabled',true).val($('#finaUserIdcard').val())
                    $('#insuredTel').addClass('disabled').attr('disabled',true).val($('#finaUserTel').val())
                }else{
                    $('#insuredName').removeClass('disabled').removeAttr('disabled')
                    $('#insuredIdcard').removeClass('disabled').removeAttr('disabled')
                    $('#insuredTel').removeClass('disabled').removeAttr('disabled')
                }
            }
        })

        $('#finaUserName').blur(function () {
            if ($('#relationships .label-bar.active').attr('data-id') == 1){
                $('#insuredName').val($('#finaUserName').val())
            }
        })
        $('#finaUserIdcard').blur(function () {
            if ($('#relationships .label-bar.active').attr('data-id') == 1){
                $('#insuredIdcard').val($('#finaUserIdcard').val())
            }
        })
        $('#finaUserTel').blur(function () {
            if ($('#relationships .label-bar.active').attr('data-id') == 1){
                $('#insuredTel').val($('#finaUserTel').val())
            }
        })

        setActive('#relationships .label-bar', $("#relationship").val(), '')
        setActive('#outInsureType .label-bar', $("#outInsureTypeId").val(), '')
        setActive('#businessTypes .label-bar', $("#businessType").val(), '')

        if (!$("#relationship").val() || $("#relationship").val() == 1 ) {
            $('#insuredName').addClass('disabled').attr('disabled', true)
            $('#insuredIdcard').addClass('disabled').attr('disabled', true)
            $('#insuredTel').addClass('disabled').attr('disabled', true)
        }

        $('#insurancePolicyNo').blur(function () {
            applicantNo()
        })
        $('#finaUserName').blur(function () {
            applicantNo()
        })
        //案件号 = 保单号 + 申请人姓名 + 案
        function applicantNo(){
            var insurancePolicyNo = $("#insurancePolicyNo").val();
            var finaUserName = $("#finaUserName").val();
            var applicantNo = insurancePolicyNo + finaUserName+"案";
            $("#caseApplicantNo").val(applicantNo);
        }

        form.on('submit(submit)',function () {
            var param = {
                finaInfoId:$('#finaInfoId').val(),
                urgeType: $('#urgeType').val(),
                btnCode: $('#btnCode').val(),
                finaUserName: $('#finaUserName').val(),
                finaUserIdcard: $('#finaUserIdcard').val(),
                finaUserTel: $('#finaUserTel').val(),
                relationship: $('#relationships .label-bar.active').attr('data-id'),
                insuredName: $('#insuredName').val(),
                insuredIdcard: $('#insuredIdcard').val(),
                insuredTel: $('#insuredTel').val(),
                applyAdvanceMoney: $('#applyAdvanceMoney').val(),
                insurancePolicyNo: $('#insurancePolicyNo').val(),
                firstInsureTime: $('#firstInsureTime').val(),
                insureMoney: $('#insureMoney').val(),
                insureType: $('#insureType').val(),
                insureStartTime: $('#insureStartTime').val(),
                insureEndTime: $('#insureEndTime').val(),
                deductibleMoney: $('#deductibleMoney').val(),
                outInsureType: $('#outInsureType .label-bar.active').attr('data-id'),
                outInsureReason: $('#outInsureReason').val(),
                department: $('#department').val(),
                inHospitalTime: $('#inHospitalTime').val(),
                businessType: $('#businessTypes .label-bar.active').attr('data-id'),
                firstContactDesc: $('#firstContactDesc').val(),
                caseApplicantNo: $('#caseApplicantNo').val(),
                diagnosisId:demo2.getValue('valueStr'),
                hospitalId:demo3.getValue('valueStr'),
                entrustOrgId:demo1.getValue('valueStr')
            }
            if (!param.entrustOrgId){
                layer.alert('请选择保险公司！', {
                    icon: 5
                })
                return;
            }
            if (!param.finaUserName){
                layer.alert('请输入申请人姓名！', {
                    icon: 5
                })
                return;
            }
            if (!param.finaUserIdcard || !checkPapers('identity',param.finaUserIdcard)){
                layer.alert('请输入正确的申请人身份证号码！', {
                    icon: 5
                })
                return;
            }
            if (!param.finaUserTel){
                layer.alert('请输入申请人手机号！', {
                    icon: 5
                })
                return;
            }
            if (!param.insuredName){
                layer.alert('请输入被保险人姓名！', {
                    icon: 5
                })
                return;
            }
            if (!param.insuredIdcard || !checkPapers('identity',param.insuredIdcard)){
                layer.alert('请输入正确的被保险人身份证号码！', {
                    icon: 5
                })
                return;
            }
            if (!param.insuredTel){
                layer.alert('请输入被保险人手机号！', {
                    icon: 5
                })
                return;
            }
            if (!param.relationship){
                layer.alert('请选择申请人与被保人关系！', {
                    icon: 5
                })
                return;
            }
            if (!param.insurancePolicyNo){
                layer.alert('请输入保单号！', {
                    icon: 5
                })
                return;
            }
            if (!param.firstInsureTime){
                layer.alert('请选择首次投保日期！', {
                    icon: 5
                })
                return;
            }
            if (!param.insureType){
                layer.alert('请输入险种！', {
                    icon: 5
                })
                return;
            }
            if (!param.insureStartTime || !param.insureEndTime){
                layer.alert('请选择保险期限！', {
                    icon: 5
                })
                return;
            }
            if (!param.outInsureType){
                layer.alert('请选择出险原因！', {
                    icon: 5
                })
                return;
            }
            if (!param.diagnosisId){
                layer.alert('请选择主要诊断！', {
                    icon: 5
                })
                return;
            }
            if (!param.hospitalId){
                layer.alert('请选择就诊医院！', {
                    icon: 5
                })
                return;
            }
            if (!param.department){
                layer.alert('请输入就诊科室！', {
                    icon: 5
                })
                return;
            }
            if (!param.inHospitalTime){
                layer.alert('请选择入院时间！', {
                    icon: 5
                })
                return;
            }
            if (!param.businessType){
                layer.alert('请选择业务类型！', {
                    icon: 5
                })
                return;
            }
            if (!param.firstContactDesc){
                layer.alert('请输入首次联系信息！', {
                    icon: 5
                })
                return;
            }
            $('.btn-success').attr('disabled',true)
            $.ajax({
                url: '${ctx}/fina/applicant/operate',
                data: param,
                success:function (res) {
                    var res= JSON.parse(res)
                    if (res.isSuccess){
                        layer.msg('成功', {
                            time: 1000,
                            icon: 1
                        },function(){
                            // parent.location.reload()
                            var urgeType = $('#urgeType').val()
                            var transferCloseType = $('#transferCloseType').val()
                            var _height = $(parent.document).height() - 70
                            var _width = $(document).width() * 0.99
                            var url = "${ctx}/fina/applicant/operateView?finaInfoId=" + res.results.id + "&btnCode=get-applicant-files&opr=edit&caseApplicantNo=" + res.results.caseApplicantNo+"&urgeType="+urgeType+"&transferCloseType="+transferCloseType;
                            openDialog({
                                frame:true,
                                title:"附件信息",
                                height:_height,
                                width:_width,
                                url:url
                            });
                        })
                    }else{
                        $('.btn-success').attr('disabled',false)
                        layer.msg(res.msg, {
                            time: 1000,
                            icon: 2
                        })
                    }
                }
            })
        })

    })

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
    /*----初始化选中方法：单选，多选----*/
    function setActive(params, key, multiple) {
        var $params = $(params)
        var key = key
        if (!multiple) {//单选
            $params.removeClass('active')
            if (key) {
                $params.map(function () {
                    var param = $(this)
                    if (param.attr('data-id') == key) {
                        param.addClass('active')
                    } else {
                        param.removeClass('active')
                    }
                })
            } else {
                //--默认
                key = $params.eq(0).attr('data-id')
                $params.eq(0).addClass('active')
            }

        } else { //多选
            var keys = key
            $params.map(function () {
                var param = $(this)
                if (keys.indexOf(Number(param.attr('data-id'))) > -1) {
                    param.addClass('active')
                }
            })
            key = key.join(',')
        }
        //= 隐藏域
        $params.parent().attr('data-value', key)
    }


    function filterJson(demo, newJson, id, name, flag, selected) {
        var demoList = [],
            demoValues = []
        newJson.map(function (cur) {
            var _name = name ? cur[name] : cur.name
            var _id = id ? cur[id] : cur.id
            var param = {
                name: _name,
                value: _id
            }
            if (selected) {
                Object.assign(param, {
                    selected: true
                })
            }
            if (cur.checked){
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


</script>
</body>
</html>
