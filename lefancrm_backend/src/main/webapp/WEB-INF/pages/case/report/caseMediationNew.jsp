<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">

    <script>

    </script>
    <style>
        .Presentation {
            width: 100%;
            padding: 20px;
        }

        textarea {
            width: 80%;
            height: 80px;
            border: none;
            resize: none;
        }

        table {
            border-collapse: collapse;
            margin-top: 10px;
            width: 100%;
        }

        #table-nei {
            margin: auto;
        }

        .title {
            color: #555;
            text-align: center;
        }

        .back-color-1 {
            background: #d9edf7;
        }

        .back-color-2 {
            background: #f0f3f8;
        }

        .border-1 {
            border: 1px solid #a9e1f4;
        }

        .border-2 {
            border: 1px solid #e3e6eb;
        }

        .padding-2 {
            padding-left: 10px;
        }

        .padding-3 {
            padding-left: 20px;
        }

        .padding-1 {
            padding: 10px 20px;
        }

        .text-color-1 {
            color: #00a0e9;
        }

        .text-align-1 {
            text-align: center;
        }

        .text-align-2 {
            text-align: right;
        }

        .table-title {
            font-weight: bold;
        }

        .row-10 {
            width: 10%;
        }

        .row-15 {
            width: 15%;
        }

        .row-20 {
            width: 20%;
        }

        .row-26 {
            width: 26.66%;
        }

        .row-25 {
            width: 25%;
        }

        .row-12 {
            width: 12.5%;
        }

        .row-30 {
            width: 30%;
        }

        .height-1 {
            height: 55px;
        }

        .input-1 {
            width: 100%;
            height: 100%;
            border: none;
        }

        .input-2 {
            width: 80px;
            border: none;
            border-bottom: 1px solid #555;
            text-align: center;
        }

        .input-3 {
            width: 280px;
            border: none;
            border-bottom: 1px solid #555;
        }

        .select-1 {
            border: none;
        }
    </style>
    <style>
        .items {
            width: 1100px;
            padding: 0 40px;
            background-color: #f5f7fa;
            display: flex;
        }

        .item {
            display: flex;
            align-items: center;
            width: 255px;
            height: 50px;
            line-height: 50px;
        }

        .item .item-index {
            width: 20px;
            height: 20px;
            line-height: 16px;
            border-radius: 50%;
            border: 2px solid #999;
            color: #999;
            font-size: 13px;
            text-align: center;
        }

        .item .item-title {
            padding-left: 20px;
            padding-right: 40px;
            color: #999;
            font-size: 15px;
        }
        .item .item-icon{
            width: 8px;
            height: 8px;
            border-right: 1px solid #bbb;
            border-bottom: 1px solid #bbb;
            transform: rotate(-45deg)
        }
        div.active .item-index{
            color: #333;
            border-color: #333;
        }
        div.active .item-title{
            color: #333;
        }
    </style>
</head>
<body>
    <div class="Presentation">
        <form id="editForm" role="form" action="${ctx}/case/saveCaseMediation"  method="post">
            <input type="hidden" name="id" value="${apiRsp.caseMediationClaim.id}">
            <input type="hidden" name="caseId" value="${apiRsp.caseMediationClaim.caseId}">
            <input type="hidden" name="claimerId" value="${apiRsp.caseMediationClaim.claimerId}">
            <input type="hidden" name="noNext" id="noNext" value="">
            <input type="hidden" name="stepCode" id="stepCode" value="${stepCode}">
            <div class="items">
                <div class="item active" id="item1">
                    <div class="item-index">1</div>
                    <div class="item-title">索赔方案调解报告</div>
                    <div class="item-icon"></div>
                </div>
                <div class="item" id="item2">
                    <div class="item-index">2</div>
                    <div class="item-title">赔偿预案调解金额</div>
                    <div class="item-icon"></div>
                </div>
                <div class="item" id="item3">
                    <div class="item-index">3</div>
                    <div class="item-title">保险公司最终赔偿金额</div>
                    <div class="item-icon"></div>
                </div>
                <div class="item" id="item4">
                    <div class="item-index">4</div>
                    <div class="item-title">提交审核</div>
                </div>
            </div>

            <div id="div_1" class="form-group">
                <table class="table">
                    <tbody>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">委托人</td>
                        <td class="border-2 padding-3 row-25" colspan="1">
                            <input type="text" placeholder="请输入伤者姓名" class="input-1" name="userName" value="${apiRsp.caseMediationClaim.userName}" />
                        </td>
                        <td class="padding-2 back-color-2 border-2 row-20">贷款金额</td>
                        <td class="border-2 padding-3" colspan="3">
                            <input type="number" step="0.01" placeholder="请输入贷款金额" class="input-1" name="loanMoney" value="${apiRsp.caseMediationClaim.loanMoney}">
                        </td>
                    </tr>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">肇事方</td>
                        <td class="border-2 padding-3 row-25" colspan="1">
                            <input type="text" placeholder="请输入肇事方" class="input-1" name="partyName" value="${apiRsp.caseMediationClaim.partyName}" />
                        </td>
                        <td class="padding-2 back-color-2 border-2 row-20">肇事方电话</td>
                        <td class="border-2 padding-3" colspan="3">
                            <input type="text" placeholder="请输入肇事方" class="input-1" name="partyTel" value="${apiRsp.caseMediationClaim.partyTel}" />
                        </td>
                    </tr>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">索赔员</td>
                        <td class="border-2 padding-3 row-25" colspan="1">
                            <input type="text" placeholder="请输入索赔员" class="input-1" name="claimerName" value="${apiRsp.caseMediationClaim.claimerName}" />
                        </td>
                        <td class="padding-2 back-color-2 border-2 row-20">索赔受理时间</td>
                        <td class="border-2 padding-2"colspan="3">
                            <input name="claimTime" type="text" placeholder="请输入索赔受理时间" value="<fmt:formatDate value="${apiRsp.caseMediationClaim.claimTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control">
                        </td>
                    </tr>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">车牌号码</td>
                        <td class="border-2 padding-3 row-25" colspan="1">
                            <input type="text" placeholder="请输入车牌号码" class="input-1" name="cardNumber" value="${apiRsp.caseMediationClaim.cardNumber}" />
                        </td>
                        <td class="padding-2 back-color-2 border-2 row-20">出险时间</td>
                        <td class="border-2 padding-2"colspan="3">
                            <input name="outInsuranceTime" type="text" placeholder="请输入出险时间" value="<fmt:formatDate value="${apiRsp.caseMediationClaim.outInsuranceTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control">
                        </td>
                    </tr>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">适用标准</td>
                        <td class="border-2 padding-3 row-25" colspan="1">
                            <select id = "appStandType" name="appStandType" class="form-control">
                                <option value="">请选择</option>
                                <option value="1" <c:if test="${apiRsp.caseMediationClaim.appStandType == 1}">selected = selected</c:if>>城市</option>
                                <option value="2" <c:if test="${apiRsp.caseMediationClaim.appStandType == 2}">selected = selected</c:if>>农村</option>
                            </select>
                        </td>
                        <td class="padding-2 back-color-2 border-2 row-20">事故责任</td>
                        <td class="border-2 padding-2"colspan="3">
                            <select id = "accidentDutyType" name="accidentDutyType" class="form-control">
                                <option value="">请选择</option>
                                <option value="1" <c:if test="${apiRsp.caseMediationClaim.accidentDutyType == 1}">selected = selected</c:if>>全部责任</option>
                                <option value="2" <c:if test="${apiRsp.caseMediationClaim.accidentDutyType == 2}">selected = selected</c:if>>主要责任</option>
                                <option value="3" <c:if test="${apiRsp.caseMediationClaim.accidentDutyType == 3}">selected = selected</c:if>>同等责任</option>
                                <option value="4" <c:if test="${apiRsp.caseMediationClaim.accidentDutyType == 4}">selected = selected</c:if>>次要责任</option>
                                <option value="5" <c:if test="${apiRsp.caseMediationClaim.accidentDutyType == 5}">selected = selected</c:if>>无责任</option>
                                <option value="6" <c:if test="${apiRsp.caseMediationClaim.accidentDutyType == 6}">selected = selected</c:if>>责任无法认定</option>
                            </select>
                        </td>
                    </tr>

                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">保险公司</td>
                        <td class="border-2 padding-3 row-25" colspan="1">
                            <input type="text" placeholder="请输入保险公司" class="input-1" name="insuranceCompany" value="${apiRsp.caseMediationClaim.insuranceCompany}" />
                        </td>
                        <td class="padding-2 back-color-2 border-2 row-20">事故责任比例</td>
                        <td class="border-2 padding-2" >
                            <input type="number" step="0" min="0" max="100" placeholder="请输入..." class="input-2" name="liabilityRatio" onblur="compensateRageBlur()" value="${apiRsp.caseMediationClaim.liabilityRatio}" />%
                        </td>
                    </tr>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">是否已鉴定</td>
                        <td class="border-2 padding-3 row-25" colspan="1">
                            <select id = "determineType" name="determineType" class="form-control">
                                <option value="">请选择</option>
                                <option value="0" <c:if test="${apiRsp.caseMediationClaim.determineType == 0}">selected = selected</c:if>>否</option>
                                <option value="1" <c:if test="${apiRsp.caseMediationClaim.determineType == 1}">selected = selected</c:if>>是</option>
                            </select>
                        </td>
                        <td class="padding-2 back-color-2 border-2 row-20">伤残等级</td>
                        <td class="border-2 padding-2" >
                            <input type="text" placeholder="请输入伤残等级" class="input-1" name="invalidismGrade" value="${apiRsp.caseMediationClaim.invalidismGrade}" />
                        </td>
                    </tr>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">伤者交通状态</td>
                        <td class="border-2 padding-3 row-25" colspan="1">
                            <select id = "hurtTrafficType" name="hurtTrafficType" class="form-control">
                                <option value="">请选择</option>
                                <option value="1" <c:if test="${apiRsp.caseMediationClaim.hurtTrafficType == 1}">selected = selected</c:if>>机动车</option>
                                <option value="2" <c:if test="${apiRsp.caseMediationClaim.hurtTrafficType == 2}">selected = selected</c:if>>非机动车</option>
                                <option value="3" <c:if test="${apiRsp.caseMediationClaim.hurtTrafficType == 3}">selected = selected</c:if>>行人</option>
                                <option value="4" <c:if test="${apiRsp.caseMediationClaim.hurtTrafficType == 4}">selected = selected</c:if>>其他</option>
                            </select>
                        </td>
                        <td class="padding-2 back-color-2 border-2 row-20">肇事方交通状态</td>
                        <td class="border-2 padding-2" >
                            <select id = "partyTrafficeType" name="partyTrafficeType" class="form-control">
                                <option value="">请选择</option>
                                <option value="1" <c:if test="${apiRsp.caseMediationClaim.partyTrafficeType == 1}">selected = selected</c:if>>机动车</option>
                                <option value="2" <c:if test="${apiRsp.caseMediationClaim.partyTrafficeType == 2}">selected = selected</c:if>>非机动车</option>
                            </select>
                        </td>
                    </tr>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">是否投保交强险</td>
                        <td class="border-2 padding-3 row-25" colspan="1">
                            <select id = "payInsuranceType" name="payInsuranceType" class="form-control">
                                <option value="">请选择</option>
                                <option value="0" <c:if test="${apiRsp.caseMediationClaim.payInsuranceType == 0}">selected = selected</c:if>>未投保</option>
                                <option value="1" <c:if test="${apiRsp.caseMediationClaim.payInsuranceType == 1}">selected = selected</c:if>>投保</option>
                            </select>
                        </td>
                        <td class="padding-2 back-color-2 border-2 row-20">商业三者险限额</td>
                        <td class="border-2 padding-2" >
                            <input type="number" step="0.00" placeholder="请输入商业三者险限额" class="input-2" name="tradeAmount" value="${apiRsp.caseMediationClaim.tradeAmount}" />元
                        </td>
                    </tr>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">是否多车事故</td>
                        <td class="border-2 padding-3 row-25" colspan="1">
                            <select id = "someCarType" name="someCarType" class="form-control">
                                <option value="">请选择</option>
                                <option value="0" <c:if test="${apiRsp.caseMediationClaim.someCarType == 0}">selected = selected</c:if>>否</option>
                                <option value="1" <c:if test="${apiRsp.caseMediationClaim.someCarType == 1}">selected = selected</c:if>>是</option>
                            </select>
                            <c:if test="${apiRsp.caseMediationClaim.someCarType == 1}">
                                ${apiRsp.caseMediationClaim.disclaimerDesc}
                            </c:if>
                        </td>
                        <td class="padding-2 back-color-2 border-2 row-20">肇事方有无免责情形</td>
                        <td class="border-2 padding-2" >
                            <select id = "disclaimerType" name="disclaimerType" class="form-control">
                                <option value="">请选择</option>
                                <option value="0" <c:if test="${apiRsp.caseMediationClaim.disclaimerType == 0}">selected = selected</c:if>>无</option>
                                <option value="1" <c:if test="${apiRsp.caseMediationClaim.disclaimerType == 1}">selected = selected</c:if>>有</option>
                            </select>
                            <c:if test="${apiRsp.caseMediationClaim.disclaimerType == 1}">
                                ${apiRsp.caseMediationClaim.disclaimerDesc}
                            </c:if>
                        </td>
                    </tr>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">保险公司调解员</td>
                        <td class="border-2 padding-3 row-25" colspan="1">
                            <input type="text" placeholder="请输入保险员" class="input-1" name="insOfficerName" value="${apiRsp.caseMediationClaim.insOfficerName}" />
                        </td>
                        <td class="padding-2 back-color-2 border-2 row-20">保险公司调解员电话</td>
                        <td class="border-2 padding-3" colspan="3">
                            <input type="text" placeholder="请输入保险员电话" class="input-1" name="insOfficerTel" value="${apiRsp.caseMediationClaim.insOfficerTel}">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div id="div_btn_1" class="modal-footer">
                <button type="submit" onclick="return toValid(1,'')" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>保存</button>
                <button type="submit" onclick="return toValid(2,2)" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>保存并下一步</button>
            </div>


            <div id="div_2" style="display: none" class="form-group">
                <table>
                    <tbody>
                    <tr class="height-1" colspan="6">
                        <td class="back-color-2 border-2 row-20 text-align-1" colspan="1">赔偿项目</td>
                        <td class="back-color-2 border-2 row-26 text-align-1" colspan="1">预案金额</td>
                        <c:if test="${op == 'view' || op == 'audit'}">
                            <td class="back-color-2 border-2 row-26 text-align-1" colspan="1">预案审核</td>
                        </c:if>
                        <td class="back-color-2 border-2 row-26 text-align-1" colspan="1">核损依据</td>
                    </tr>
                    <c:forEach items="${dtos}" var="item">
                        <tr class="height-1" >
                            <td class="back-color-2 border-2 row-20 padding-2">${item.projectName}</td>
                            <td class="border-2 row-26 padding-2" colspan="1"><input type="number" step="0.01" placeholder="请输入金额" <c:if test="${op == 'edit'}">onblur="blurUpdMediationClaimReport(${apiRsp.caseMediationClaim.id == null ? 'null' : apiRsp.caseMediationClaim.id},${item.id},1,this,${apiRsp.caseMediationClaim.caseId})"</c:if> class="input-1" value="${item.opinionMoney}" /></td>
                            <%--<td class="border-2 row-26 padding-2" colspan="1"><input type="number" step="0.01" placeholder="请输入金额" onblur="blurUpdMediationClaimReport(${apiRsp.caseMediationClaim.id == null ? 'null' : apiRsp.caseMediationClaim.id},${item.id},1,this,${apiRsp.caseMediationClaim.caseId})" class="input-1" value="${item.opinionMoney}" /></td>--%>
                            <c:if test="${op == 'audit'}">
                                <td class="border-2 row-26 padding-2" colspan="1"><input type="number" step="0.01" placeholder="请输入金额" onblur="blurUpdMediationClaimReport(${apiRsp.caseMediationClaim.id == null ? 'null' : apiRsp.caseMediationClaim.id},${item.id},2,this,${apiRsp.caseMediationClaim.caseId})" class="input-1" value="${item.auditingMoney}"/></td>
                            </c:if>
                            <c:if test="${op == 'view'}">
                                <td class="border-2 row-26 padding-2" colspan="1"><input type="number" step="0.01" placeholder="请输入金额"  class="input-1" value="${item.auditingMoney}"/></td>
                            </c:if>
                            <td class="border-2 row-26 padding-2" colspan="1"><input type="text" placeholder="请输入核损依据" onblur="blurUpdMediationClaimReport(${apiRsp.caseMediationClaim.id == null ? 'null' : apiRsp.caseMediationClaim.id},${item.id},4,this,${apiRsp.caseMediationClaim.caseId})" class="input-1" value="${item.checkBasis}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div id="div_btn_2" style="display: none" class="modal-footer">
                <button type="submit" onclick="return toValid(2,1)" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>上一步</button>
                <button type="submit" onclick="return toValid(1,'')" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>保存</button>
                <button type="submit" onclick="return toValid(2,3)" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>保存并下一步</button>

            </div>


            <div id="div_3" style="display: none" class="form-group">
                <table>
                    <tbody>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">合计</td>
                        <td class="border-2 padding-3 row-25" colspan="1">
                            <input type="number" step="0.01" placeholder="请输入合计金额" class="input-1" name="cptMoney" value="${apiRsp.caseMediationClaim.cptMoney}" />
                        </td>
                        <td class="padding-2 back-color-2 border-2 row-20">交强险</td>
                        <td class="border-2 padding-3" colspan="3">
                            <input type="number" step="0.01" placeholder="请输入交强险金额" class="input-1" name="cpsMoney" value="${apiRsp.caseMediationClaim.cpsMoney}">
                        </td>
                    </tr>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">商业险</td>
                        <td class="border-2 padding-3 row-25" colspan="1">
                            <input type="number" step="0.01" placeholder="请输入商业险金额" class="input-1" name="cocMoney" value="${apiRsp.caseMediationClaim.cocMoney}" />
                        </td>
                        <td class="padding-2 back-color-2 border-2 row-20">肇事方赔偿</td>
                        <td class="border-2 padding-3" colspan="3">

                            <input type="number" step="0.01" placeholder="请输入肇事方赔偿" class="input-1" name="finalLoanMoney" value="${apiRsp.caseMediationClaim.finalLoanMoney}">
                        </td>
                    </tr>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">支付伤者金额</td>
                        <td class="border-2 padding-3 row-25" colspan="1">
                            <input type="number" step="0.01" placeholder="支付伤者金额" class="input-1" name="payWoundedMoney" value="${apiRsp.caseMediationClaim.payWoundedMoney}" />
                        </td>
                        <td class="padding-2 back-color-2 border-2 row-20">支付驾驶员金额</td>
                        <td class="border-2 padding-3" colspan="3">
                            <input type="number" step="0.01" placeholder="支付驾驶员金额" class="input-1" name="payDriverMoney" value="${apiRsp.caseMediationClaim.payDriverMoney}">
                        </td>
                    </tr>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">支付保险公司金额</td>
                        <td class="border-2 padding-3 row-25" colspan="1">
                            <input type="number" step="0.01" placeholder="支付保险公司金额" class="input-1" name="paySafeMoney" value="${apiRsp.caseMediationClaim.paySafeMoney}" />
                        </td>
                        <td class="padding-2 back-color-2 border-2 row-20"></td>
                        <td class="border-2 padding-3" colspan="3">
                        </td>
                    </tr>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">案件备注</td>
                        <td class="border-2 padding-3" colspan="3">
                            <textarea name="caseDesc" placeholder="请输入..." >${apiRsp.caseMediationClaim.caseDesc}</textarea>
                        </td>
                    </tr>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">预案审核意见</td>
                        <td class="border-2 padding-3" colspan="3">
                            <textarea placeholder="" name="planReviewDesc" >${apiRsp.caseMediationClaim.planReviewDesc}</textarea>
                        </td>
                    </tr>
                    <tr class="height-1" colspan="6">
                        <td class="padding-2 back-color-2 border-2 row-20">保司审核意见</td>
                        <td class="border-2 padding-3" colspan="3">
                            <textarea name="insurerReviewDesc" placeholder="" >${apiRsp.caseMediationClaim.insurerReviewDesc}</textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div id="div_btn_3" style="display: none" class="modal-footer">
                <button type="submit" onclick="return toValid(2,2)" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>上一步</button>
                <button type="submit" onclick="return toValid(1,'')" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>保存</button>
                <button type="submit" onclick="return toValid(3,'')" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>保存并提交审核</button>
            </div>
        </form>



    </div>

<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>


<script type="text/javascript">
    $(function () {
        $('.singleSelect').select2();
    });

    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        ajaxFormSubmit(this,myCallBack,null,null,myCallBack);
        event.preventDefault();
    });

    function myCallBack(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp){
            if(apiRsp.results.noNext == 1 || apiRsp.results.noNext == 3){
                reloadParent();
            }else if(apiRsp.results.noNext == 2){
                var url = "${ctx}/case/caseMediation?caseId="+'${caseId}'+"&caseNo="+'${caseNo}'+"&op="+'${op}'+"&type=33"+"&stepCode="+apiRsp.results.stepCode+"&noNext="+apiRsp.results.noNext;
                location.replace(url);
            }
        }else{
            alert(apiRsp.msg);return;
        }
    }

    var stepCode=$("#stepCode").val();
    if(stepCode == 1){
        $("#div_1").show();
        $("#div_btn_1").show();
        $("#div_2").hide();
        $("#div_btn_2").hide();
        $("#div_3").hide();
        $("#div_btn_3").hide();

        $("#item1").attr("class", "item active");
        $("#item2").attr("class", "item");
        $("#item3").attr("class", "item");
        $("#item4").attr("class", "item");
    }else if(stepCode == 2) {
        $("#div_2").show();
        $("#div_btn_2").show();
        $("#div_1").hide();
        $("#div_btn_1").hide();
        $("#div_3").hide();
        $("#div_btn_3").hide();

        $("#item2").attr("class", "item active");
        $("#item1").attr("class", "item");
        $("#item3").attr("class", "item");
        $("#item4").attr("class", "item");
    }else if(stepCode == 3) {
        $("#div_3").show();
        $("#div_btn_3").show();
        $("#div_1").hide();
        $("#div_btn_1").hide();
        $("#div_2").hide();
        $("#div_btn_2").hide();

        $("#item3").attr("class", "item active");
        $("#item1").attr("class", "item");
        $("#item2").attr("class", "item");
        $("#item4").attr("class", "item");
    }

    function toValid(noNext,stepCode){
        $("#noNext").val(noNext);//关闭页面、进入下一个页面
        $("#stepCode").val(stepCode);//回调函数返回 （进入第stepCode个页面）
        return true;
    }

    function blurUpdMediationClaimReport(claimId,id,type,obj,caseId){
        ajaxSubmit("${ctx}/case/updMediationClaimReport?claimId="+claimId+"&id=" + id + "&type=" + type + "&value=" + obj.value+"&caseId="+caseId,null,function(v,e,p){
        })
    }
    </script>
</body>