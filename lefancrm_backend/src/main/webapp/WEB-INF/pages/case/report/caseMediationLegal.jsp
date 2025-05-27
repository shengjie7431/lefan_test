
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<jsp:useBean id="dateValue" class="java.util.Date"/>
<!DOCTYPE html>
<html>
<head>
    <title>诉讼报告</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
</head>
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
<body>
<div class="Presentation">
    <form id="editForm" role="form" action="${ctx}/case/report/saveCaseMediationLegal" method="post">
        <input type="hidden" name="id" value="${apiRsp.caseMediationClaim.id}">
        <input type="hidden" name="caseId" value="${apiRsp.caseMediationClaim.caseId}">
        <input type="hidden" name="claimerId" value="${apiRsp.caseMediationClaim.claimerId}">

        <h2 class="title">诉讼方案报告</h2>
        <table>
            <tbody>
            <tr class="back-color-1 border-1 padding-1 height-1">
                <td colspan="6" class="table-title padding-2">
                    诉讼方案调解报告
                </td>
            </tr>
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
                <td class="padding-2 back-color-2 border-2 row-20">诉讼员</td>
                <td class="border-2 padding-3 row-25" colspan="1">
                    <input type="text" placeholder="请输入诉讼员" class="input-1" name="claimerName" value="${apiRsp.caseMediationClaim.claimerName}" />
                </td>
                <td class="padding-2 back-color-2 border-2 row-20">诉讼受理时间</td>
                <td class="border-2 padding-2"colspan="3">
                    <input name="claimTime" type="text" placeholder="请输入诉讼受理时间" value="<fmt:formatDate value="${apiRsp.caseMediationClaim.claimTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control">
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
            <%--<tr class="height-1" colspan="6">--%>
                <%--<td class="padding-2 back-color-2 border-2 row-20">调解成功时间</td>--%>
                <%--<td class="border-2 padding-2"colspan="1">--%>
                    <%--<input name="mediateDate" type="text" placeholder="请输入调解成功时间" value="<fmt:formatDate value="${apiRsp.caseMediationClaim.mediateDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control">--%>
                <%--</td>--%>
                <%--<td class="padding-2 back-color-2 border-2 row-20">赔款预计到账时间</td>--%>
                <%--<td class="border-2 padding-2"colspan="3">--%>
                    <%--<input name="arrivalTime" type="text" placeholder="请输入赔款预计到账时间" value="<fmt:formatDate value="${apiRsp.caseMediationClaim.arrivalTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control">--%>
                <%--</td>--%>
            <%--</tr>--%>
            </tbody>
        </table>
        <table id="table-nei">
            <tbody>
            <tr class="back-color-1 border-1 padding-1 height-1">
                <td colspan="6" class="table-title padding-2">
                    赔偿预案调解金额
                </td>
            </tr>
            <tr class="height-1" colspan="6">
                <td class="back-color-2 border-2 row-20 text-align-1" colspan="1">赔偿项目</td>
                <td class="back-color-2 border-2 row-26 text-align-1" colspan="1">预案金额</td>
                <c:if test="${op == 'view' || op == 'audit'}">
                    <td class="back-color-2 border-2 row-26 text-align-1" colspan="1">预案审核</td>
                </c:if>
                <%--<td class="back-color-2 border-2 row-26 text-align-1" colspan="2">保司审核</td>--%>
                <td class="back-color-2 border-2 row-26 text-align-1" colspan="1">核损依据</td>
            </tr>
            <c:forEach items="${dtos}" var="item">
                <tr class="height-1" >
                    <td class="back-color-2 border-2 row-20 padding-2">${item.projectName}</td>
                    <td class="border-2 row-26 padding-2" colspan="1"><input type="number" step="0.01" placeholder="请输入金额" <c:if test="${op == 'edit'}">onblur="blurUpdMediationClaimReport(${apiRsp.caseMediationClaim.id == null ? 'null' : apiRsp.caseMediationClaim.id},${item.id},1,this,${apiRsp.caseMediationClaim.caseId})"</c:if> class="input-1" value="${item.opinionMoney}" /></td>
                    <c:if test="${op == 'audit'}">
                        <td class="border-2 row-26 padding-2" colspan="1"><input type="number" step="0.01" placeholder="请输入金额" onblur="blurUpdMediationClaimReport(${apiRsp.caseMediationClaim.id == null ? 'null' : apiRsp.caseMediationClaim.id},${item.id},2,this,${apiRsp.caseMediationClaim.caseId})" class="input-1" value="${item.auditingMoney}"/></td>
                    </c:if>
                    <c:if test="${op == 'view'}">
                        <td class="border-2 row-26 padding-2" colspan="1"><input type="number" step="0.01" class="input-1" value="${item.auditingMoney}"/></td>
                    </c:if>
                    <td class="border-2 row-26 padding-2" colspan="1"><input type="text" placeholder="请输入核损依据" onblur="blurUpdMediationClaimReport(${apiRsp.caseMediationClaim.id == null ? 'null' : apiRsp.caseMediationClaim.id},${item.id},4,this,${apiRsp.caseMediationClaim.caseId})" class="input-1" value="${item.checkBasis}"/></td>
                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${op != 'view'}">
                <tr>
                    <td colspan="5" style="text-align: right">
                        <button onclick="return refreshSave();" type="button" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 保存</button>
                    </td>
                </tr>
            </c:if>
        </table>
        <table>
            <tbody>
            <tr class="back-color-1 border-1 padding-1 height-1">
                <td colspan="6" class="table-title padding-2">
                    保险公司最终赔偿金额(指最终赔偿到伤者账户的赔偿金额)
                </td>
            </tr>
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
            <%--<tr class="height-1" colspan="6">--%>
                <%--<td class="padding-2 back-color-2 border-2 row-20">已还款金额</td>--%>
                <%--<td class="border-2 padding-3 row-25" colspan="1">--%>
                    <%--<input type="number" step="0.01" placeholder="请输入已还款金额" class="input-1" name="repaymentMoney" value="${apiRsp.caseMediationClaim.repaymentMoney}" />--%>
                <%--</td>--%>
                <%--<td class="padding-2 back-color-2 border-2 row-20">已收取服务费金额</td>--%>
                <%--<td class="border-2 padding-3" colspan="3">--%>
                    <%--<input type="number" step="0.01" placeholder="请输入已收取服务费金额" class="input-1" name="serviceMoney" value="${apiRsp.caseMediationClaim.serviceMoney}">--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr class="height-1" colspan="6">--%>
                <%--<td class="padding-2 back-color-2 border-2 row-20">调解过程备注(沟通时间、沟通对象、沟通内容、沟通结果)</td>--%>
                <%--<td class="border-2 padding-3 row-25" colspan="3">--%>
                    <%--<textarea name ="mediateDesc" placeholder="请输入..." >${apiRsp.caseMediationClaim.mediateDesc}</textarea>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr class="height-1" colspan="6">--%>
                <%--<td class="padding-2 back-color-2 border-2 row-20">调解失败原因归纳</td>--%>
                <%--<td class="border-2 padding-3" colspan="3">--%>
                    <%--<textarea name="mediateFailDesc" placeholder="请输入..." >${apiRsp.caseMediationClaim.mediateFailDesc}</textarea>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <tr class="height-1" colspan="6">
                <td class="padding-2 back-color-2 border-2 row-20">案件备注</td>
                <td class="border-2 padding-3" colspan="3">
                    <textarea name="caseDesc" placeholder="请输入..." >${apiRsp.caseMediationClaim.caseDesc}</textarea>
                </td>
            </tr>
            <%--<tr class="height-1" colspan="6">--%>
                <%--<td class="border-2 padding-3" colspan="2"></td>--%>
                <%--<td class="padding-2 back-color-2 border-2 row-20">提交时间</td>--%>
                <%--<td class="border-2 padding-3">--%>
                    <%--<input name="caseCommitTime" type="text" placeholder="提交时间" value="<fmt:formatDate value="${apiRsp.caseMediationClaim.caseCommitTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control">--%>
                <%--</td>--%>
            <%--</tr>--%>
            <tr class="height-1" colspan="6">
                <td class="padding-2 back-color-2 border-2 row-20">预案审核意见</td>
                <td class="border-2 padding-3" colspan="3">
                    <textarea placeholder="" name="planReviewDesc" >${apiRsp.caseMediationClaim.planReviewDesc}</textarea>
                </td>
            </tr>
            <%--<tr class="height-1" colspan="6">--%>
                <%--<td class="padding-2 back-color-2 border-2 row-20">审核人</td>--%>
                <%--<td class="border-2 padding-3">--%>
                    <%--<input type="text" placeholder="" class="input-1" name="planReviewPerson" value="${apiRsp.caseMediationClaim.planReviewPerson}" />--%>
                <%--</td>--%>
                <%--<td class="padding-2 back-color-2 border-2 row-20">审核时间</td>--%>
                <%--<td class="border-2 padding-3">--%>
                     <%--<input name="planReviewTime" type="text" placeholder="" value="<fmt:formatDate value="${apiRsp.caseMediationClaim.planReviewTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control">--%>
                <%--</td>--%>
            <%--</tr>--%>
            <tr class="height-1" colspan="6">
                <td class="padding-2 back-color-2 border-2 row-20">保司审核意见</td>
                <td class="border-2 padding-3" colspan="3">
                    <textarea name="insurerReviewDesc" placeholder="" >${apiRsp.caseMediationClaim.insurerReviewDesc}</textarea>
                </td>
            </tr>
            <%--<tr class="height-1" colspan="6">--%>
                <%--<td class="padding-2 back-color-2 border-2 row-20">审核人</td>--%>
                <%--<td class="border-2 padding-3">--%>
                    <%--<input type="text" placeholder="" class="input-1" name="insurerReviewPerson" value="${apiRsp.caseMediationClaim.insurerReviewPerson}" />--%>
                <%--</td>--%>
                <%--<td class="padding-2 back-color-2 border-2 row-20">审核时间</td>--%>
                <%--<td class="border-2 padding-3">--%>
                    <%--<input name="insurerReviewTime" type="text" placeholder="" value="<fmt:formatDate value="${apiRsp.caseMediationClaim.insurerReviewTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control">--%>
                <%--</td>--%>
            <%--</tr>--%>
            </tbody>
        </table>
        <c:if test="${op == 'edit' || op == 'audit'}">
            <div class="modal-footer">
                <button onclick="return onBack();" type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 保存</button>
            </div>
        </c:if>
    </form>
</div>
<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<%--<script src="${ctx}/js/jquery.pin.js" type="text/javascript"></script>--%>
<%--<script src="${ctx}/js/jquery.tableDnD.js" type="text/javascript"></script>--%>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script type="text/javascript">
    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        $(this).find(":submit").attr("disabled","true");
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
    function onBack(){
        return true;
    }

    /**
     *事故责任比例 光标离开 调用保存方法
     * @param obj
     */
    function compensateRageBlur(){
        if("${op}" == 'view'){
            return;
        }
        ajaxFormSubmit($("#editForm"),function(v,e,p){location.reload();},null,null,function(v,e,p){
            location.reload();
        });
    }
    function refreshSave(){
        location.reload();
    }
    function blurUpdMediationClaimReport(claimId,id,type,obj,caseId){
        ajaxSubmit("${ctx}/case/report/updMediationClaimReportLegal?claimId="+claimId+"&id=" + id + "&type=" + type + "&value=" + obj.value+"&caseId="+caseId,null,function(v,e,p){
        })
    }
</script>
</body>
</html>
