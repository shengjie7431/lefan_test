<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<jsp:useBean id="dateValue" class="java.util.Date"/>
<!DOCTYPE html>
<html>
<head>
    <title>公估报告</title>
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

    .row-20 {
        width: 20%;
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
    <form id="editForm" role="form" action="${ctx}/case/report/saveAssessmentReport" method="post">
        <input type="hidden" name="id" value="${apiRsp.caseAssessmentReport.id}">
        <input type="hidden" name="caseId" value="${apiRsp.caseAssessmentReport.caseId}">
        <input type="hidden" name="caseNo" value="${apiRsp.caseAssessmentReport.caseNo}">
        <h2 class="title">“人伤无忧”保险公估报告</h2>
        <p class="text-align-2">公估编号：${apiRsp.caseAssessmentReport.caseNo}</p>
        <table cellspacing="0">
            <tbody>
            <tr class="back-color-1 border-1 padding-1 height-1">
                <td colspan="5" class="table-title padding-2">
                    致：上海乐凡金融信息服务有限公司
                </td>
            </tr>
            <tr class="border-1 padding-1 height-1">
                <td colspan="5" class="text-color-1 table-title padding-2">
                    案件基本信息审核
                </td>
            </tr>
            <tr class='height-1'>
                <td rowspan="5" class="back-color-1 border-1 height-1 table-title text-align-1 row-10">
                    当<br />事<br />人<br />基<br />本<br />信<br />息
                </td>
                <td class="padding-2 back-color-2 border-2 row-20">被保险人</td>
                <td class="border-2 padding-3 row-25">
                    <input type="text" placeholder="请输入被保险人" class="input-1" name="insurantName" value="${apiRsp.caseAssessmentReport.insurantName}" />
                </td>
                <td class="padding-2 back-color-2 border-2 row-20">身份证号码</td>
                <td class="border-2 padding-3">
                    <input type="text" maxlength="18" placeholder="请输入身份证号码" class="input-1" name="insurantId" value="${apiRsp.caseAssessmentReport.insurantId}">
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2">车牌号码</td>
                <td class="border-2 padding-3">
                    <input type="text" placeholder="请输入车牌号码" class="input-1" name="carNumber" value="${apiRsp.caseAssessmentReport.carNumber}"/>
                </td>
                <td class="padding-2 border-2 back-color-2">手机号码</td>
                <td class="border-2 padding-3">
                    <input type="text" placeholder="请输入手机号码" class="input-1" name="insurantTel" value="${apiRsp.caseAssessmentReport.insurantTel}"/>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2">伤者/死者姓名</td>
                <td class="border-2 padding-3">
                    <input type="text" placeholder="请输入伤者/死者姓名" class="input-1" name="injuredName" value="${apiRsp.caseAssessmentReport.injuredName}"/>
                </td>
                <td class="padding-2 border-2 back-color-2">身份证号码</td>
                <td class="border-2 padding-3">
                    <input type="text" maxlength="18" placeholder="请输入手机身份证号码" class="input-1" name="injuredId" value="${apiRsp.caseAssessmentReport.injuredId}"/>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2">伤者/死者方代理人</td>
                <td class="border-2 padding-3">
                    <input type="text" placeholder="请输入伤者/死者方代理人" class="input-1" name="injuredAgentName" value="${apiRsp.caseAssessmentReport.injuredAgentName}"/>
                </td>
                <td class="padding-2 border-2 back-color-2">身份证号码</td>
                <td class="border-2 padding-3">
                    <input type="text" placeholder="请输入手机身份证号码" class="input-1" name="injuredAgentId" value="${apiRsp.caseAssessmentReport.injuredAgentId}"/>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2">与伤者关系</td>
                <td class="border-2 padding-3">
                    <input type="text" placeholder="请输入与伤者关系" class="input-1" name="relationship" value="${apiRsp.caseAssessmentReport.relationship}"/>
                </td>
                <td class="padding-2 border-2 back-color-2">手机号码</td>
                <td class="border-2 padding-3">
                    <input type="text" placeholder="请输入手机号码" class="input-1" name="injuredAgentTel" value="${apiRsp.caseAssessmentReport.injuredAgentTel}"/>
                </td>
            </tr>
            </tbody>
        </table>
        <table>
            <tbody>
            <tr class='height-1'>
                <td rowspan="8" class="back-color-1 border-1 height-1 table-title text-align-1 row-10">
                    事<br />故<br />信<br />息
                </td>
                <td class="padding-2 border-2 back-color-2 row-20">事故发生时间</td>
                <td class="border-2 padding-3 row-25">
                    <input name="accidentTime" type="text"  value="<fmt:formatDate value="${apiRsp.caseAssessmentReport.accidentTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control">
                </td>
                <td class="padding-2 border-2 back-color-2 row-20">事故发生地点</td>
                <td class="border-2 padding-3 row-25">
                    <input type="text" placeholder="请输入事故发生地点" class="input-1" name="accidentAddress" value="${apiRsp.caseAssessmentReport.accidentAddress}"/>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2">事故处理交警大队</td>
                <td class="border-2 padding-3">
                    <input type="text" placeholder="请输入事故处理交警大队" class="input-1" name="trafficPolice" value="${apiRsp.caseAssessmentReport.trafficPolice}"/>
                </td>
                <td class="padding-2 border-2 back-color-2">事故处理交警</td>
                <td class="border-2 padding-3">
                    <input type="text" placeholder="请输入事故处理交警" class="input-1" name="policeName" value="${apiRsp.caseAssessmentReport.policeName}"/>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">事故交警大队联系方式</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="text" placeholder="请输入事故交警大队联系方式" class="input-1" name="policeTel" value="${apiRsp.caseAssessmentReport.policeTel}"/>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">事故认定证书是否已出具</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="radio" name="isIdentification" value="1" <c:if test="${apiRsp.caseAssessmentReport.isIdentification == 1}">checked = "checked"</c:if>/>是
                    <input type="radio" name="isIdentification" value="0" <c:if test="${apiRsp.caseAssessmentReport.isIdentification == 0}">checked = "checked"</c:if>/>否
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">事故责任认定</td>
                <td class="border-2 padding-3" colspan="3">
                    <p>预计被保险人承担<input type="text" class="input-2" name="insurantResponsibility" value="${apiRsp.caseAssessmentReport.insurantResponsibility}">责任，伤/死者承担<input type="text" class="input-2" name="injuredResponsibility" value="${apiRsp.caseAssessmentReport.injuredResponsibility}">责任</p>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20" rowspan="2">交强险垫付通知书<br>/事故证明是否已出具</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="radio" name="isAdvanceNotice" value="1" <c:if test="${apiRsp.caseAssessmentReport.isAdvanceNotice == 1}">checked = "checked"</c:if>/>是
                    <input type="radio" name="isAdvanceNotice" value="0" <c:if test="${apiRsp.caseAssessmentReport.isAdvanceNotice == 0}">checked = "checked"</c:if>/>否
                </td>
            </tr>
            <tr class='height-1'>
                <td class="border-2 padding-3" colspan="3">
                    <p>预计被保险人承担<input type="text" class="input-2" name="insurantAccidentProof" value="${apiRsp.caseAssessmentReport.insurantAccidentProof}">责任，伤/死者承担<input type="text" class="input-2" name="injuredAccidentProof" value="${apiRsp.caseAssessmentReport.injuredAccidentProof}">责任</p>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">事故经过</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="text" placeholder="请输入事故经过" class="input-1" name="accidentDesc" value="${apiRsp.caseAssessmentReport.accidentDesc}"/>
                </td>
            </tr>
            </tbody>
        </table>
        <table>
            <tbody>
            <tr class='height-1'>
                <td rowspan="9" class="back-color-1 border-1 height-1 table-title text-align-1 row-10">
                    就<br />诊<br />信<br />息
                </td>
                <td class="padding-2 border-2 back-color-2 row-20">就诊医院</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="text" placeholder="请输入就诊医院" class="input-1" name="hospital" value="${apiRsp.caseAssessmentReport.hospital}"/>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2">床位主管医生</td>
                <td class="border-2 padding-3">
                    <input type="text" placeholder="请输入床位主管医生" class="input-1" name="doctor" value="${apiRsp.caseAssessmentReport.doctor}"/>
                </td>
                <td class="padding-2 border-2 back-color-2">联系方式</td>
                <td class="border-2 padding-3">
                    <input type="text" placeholder="请输入联系方式" class="input-1" name="doctorTel" value="${apiRsp.caseAssessmentReport.doctorTel}"/>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">主要诊断</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="text" placeholder="请输入主要诊断" class="input-1" name="mainDiagnosis" value="${apiRsp.caseAssessmentReport.mainDiagnosis}"/>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">既往病史</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="text" placeholder="请输入既往病史" class="input-1" name="illnessHistory" value="${apiRsp.caseAssessmentReport.illnessHistory}"/>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">
                    <%--<input type="checkbox" <c:if test="${apiRsp.caseAssessmentReport.operationName != null}">checked = "checked"</c:if>/>--%>
                    已、拟做手术名称</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="text" placeholder="请输入手术名称" class="input-1" name="operationName" value="${apiRsp.caseAssessmentReport.operationName}"/>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2">已产生医疗费用</td>
                <td class="border-2 padding-3">
                    <input type="number" step="0.01" min="0" placeholder="请输入已产生医疗费用" class="input-1" name="medicalFee" value="${apiRsp.caseAssessmentReport.medicalFee}"/>
                </td>
                <td class="padding-2 border-2 back-color-2">还需医疗费用</td>
                <td class="border-2 padding-3">
                    <input type="number" step="0.01" min="0" placeholder="请输入还需医疗费用" class="input-1" name="stillMedicalFee" value="${apiRsp.caseAssessmentReport.stillMedicalFee}"/>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2">伤者方支付医疗费用金额</td>
                <td class="border-2 padding-3">
                    <input type="number" step="0.01" step="0.01" placeholder="请输入伤者方支付医疗费用金额" class="input-1" name="injuredMedicalFee" value="${apiRsp.caseAssessmentReport.injuredMedicalFee}"/>
                </td>
                <td class="padding-2 border-2 back-color-2">被保险人方支付医疗费用金额</td>
                <td class="border-2 padding-3">
                    <input type="number" step="0.01" placeholder="请输入被保险人方支付医疗费用金额" class="input-1" name="insurantMedicalFee" value="${apiRsp.caseAssessmentReport.insurantMedicalFee}"/>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2">是否需要二次手术</td>
                <td class="border-2 padding-3">
                    <select id = "isAgainOperation" name="isAgainOperation" class="select-1">
                        <option value="">请选择</option>
                        <option value="0" <c:if test="${apiRsp.caseAssessmentReport.isAgainOperation == 0}">selected = selected</c:if>>否</option>
                        <option value="1" <c:if test="${apiRsp.caseAssessmentReport.isAgainOperation == 1}">selected = selected</c:if>>是</option>
                    </select>
                </td>
                <td class="padding-2 border-2 back-color-2">二次手术预估医疗费用</td>
                <td class="border-2 padding-3">
                    <input type="number" step="0.01" min="0" placeholder="请输入二次手术预估医疗费用" class="input-1" name="againOperationFee" value="${apiRsp.caseAssessmentReport.againOperationFee}"/>
                </td>
            </tr>
            </tbody>
        </table>
        <table>
            <tbody>
            <tr class='height-1'>
                <td rowspan="4" class="back-color-1 border-1 height-1 table-title text-align-1 row-10">
                    保<br />险<br />承<br />保<br />信<br />息
                </td>
                <td class="padding-2 border-2 back-color-2 row-20">肇事车投保公司</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="text" placeholder="请输入肇事车投保公司" class="input-1" name="insuranceCompany" value="${apiRsp.caseAssessmentReport.insuranceCompany}"/>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">交强险</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="radio" name="isCompulInsurance" value="1" <c:if test="${apiRsp.caseAssessmentReport.isCompulInsurance == 1}">checked = "checked"</c:if>/>是
                    <input type="radio" name="isCompulInsurance" value="0" <c:if test="${apiRsp.caseAssessmentReport.isCompulInsurance == 0}">checked = "checked"</c:if>/>否
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">商业三者险</td>
                <td class="border-2 padding-3" colspan="3">
                    投保<input type="number" step="0.01" min="0" class="input-2" name="commerInsurance" value="${apiRsp.caseAssessmentReport.commerInsurance}">万；不计免赔 ： <input type="radio" name="isDeductible" value="1" <c:if test="${apiRsp.caseAssessmentReport.isDeductible == 1}">checked = "checked"</c:if>/>有 <input type="radio" name="isDeductible" value="0" <c:if test="${apiRsp.caseAssessmentReport.isDeductible == 0}">checked = "checked"</c:if>/>无
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">保单特别约定</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="text" placeholder="请输入保单特别约定" class="input-1" name="policyAgreement" value="${apiRsp.caseAssessmentReport.policyAgreement}"/>
                </td>
            </tr>
            </tbody>
        </table>
        <table>
            <tbody>
            <tr class='height-1'>
                <td rowspan="4" class="back-color-1 border-1 height-1 table-title text-align-1 row-10">
                    历<br />史<br />赔<br />付<br />信<br />息
                </td>
                <td class="padding-2 border-2 back-color-2 row-20">有无交强险垫付</td>
                <td class="border-2 padding-3" colspan="3">
                    <p>
                        <input type="radio" name="isCompulAdvance" value="0" <c:if test="${apiRsp.caseAssessmentReport.isCompulAdvance == 0}">checked = "checked"</c:if>/>无
                        <input type="radio" name="isCompulAdvance" value="1" <c:if test="${apiRsp.caseAssessmentReport.isCompulAdvance == 1}">checked = "checked"</c:if>/>有
                        <span>（已垫付<input type="number" step="0.01" min="0" class="input-2" name="compulAdvanceFee" value="${apiRsp.caseAssessmentReport.compulAdvanceFee}" />元）</span>
                    </p>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">有无商业险预赔</td>
                <td class="border-2 padding-3" colspan="3">
                    <p>
                        <input type="radio" name="isCommerAdvance" value="0" <c:if test="${apiRsp.caseAssessmentReport.isCommerAdvance == 0}">checked = "checked"</c:if>/>无
                        <input type="radio" name="isCommerAdvance" value="1" <c:if test="${apiRsp.caseAssessmentReport.isCommerAdvance == 1}">checked = "checked"</c:if>/>有
                        <span>（已预赔<input type="number" step="0.01" min="0" class="input-2" name="commerAdvanceFee" value="${apiRsp.caseAssessmentReport.commerAdvanceFee}" />元）</span>
                    </p>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">有无路救基金垫付</td>
                <td class="border-2 padding-3" colspan="3">
                    <p>
                        <input type="radio" name="isRoadRef" value="0" <c:if test="${apiRsp.caseAssessmentReport.isRoadRef == 0}">checked = "checked"</c:if>/>无
                        <input type="radio" name="isRoadRef" value="1" <c:if test="${apiRsp.caseAssessmentReport.isRoadRef == 1}">checked = "checked"</c:if>/>有
                        <span>（已垫付<input type="number" step="0.01" min="0" class="input-2" name="roadRefFee" value="${apiRsp.caseAssessmentReport.roadRefFee}" />元）</span>
                    </p>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">有无其他赔付记录</td>
                <td class="border-2 padding-3" colspan="3">
                    <p>
                        <input type="radio" name="isOtherAdvance" value="0" <c:if test="${apiRsp.caseAssessmentReport.isOtherAdvance == 0}">checked = "checked"</c:if> />无
                        <input type="radio" name="isOtherAdvance" value="1" <c:if test="${apiRsp.caseAssessmentReport.isOtherAdvance == 1}">checked = "checked"</c:if> />有
                        <span>（已赔付<input type="number" step="0.01" min="0" class="input-2" name="otherAdvanceFee" value="${apiRsp.caseAssessmentReport.otherAdvanceFee}" />元）</span>
                    </p>
                </td>
            </tr>
            </tbody>
        </table>
        <table>
            <tbody>
            <tr class='height-1'>
                <td rowspan="2" class="back-color-1 border-1 height-1 table-title text-align-1 row-10">
                    定<br />损<br />信<br />息
                </td>
                <td class="padding-2 border-2 back-color-2 row-20">肇事车定损金额</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="number" step="0.01" min="0" class="input-2" name="accidentCarAmount" value="${apiRsp.caseAssessmentReport.accidentCarAmount}" />元
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">三者车定损金额</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="number" step="0.01" min="0" class="input-2" name="threeCarAmount" value="${apiRsp.caseAssessmentReport.threeCarAmount}" />元
                </td>
            </tr>
            </tbody>
        </table>
        <table>
            <tbody>
            <tr class='height-1'>
                <td rowspan="2" class="back-color-1 border-1 height-1 table-title text-align-1 row-10">
                    调<br />查<br />信<br />息
                </td>
                <td class="padding-2 border-2 back-color-2 row-20">是否发起风险调查</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="radio" name="isSurvey" value="0" <c:if test="${apiRsp.caseAssessmentReport.isSurvey == 0}">checked = "checked"</c:if>/>未调查
                    <input type="radio" name="isSurvey" value="1" <c:if test="${apiRsp.caseAssessmentReport.isSurvey == 1}">checked = "checked"</c:if>/>调查
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">调查结果</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="text" placeholder="请输入调查结果" class="input-1" name="surveyDesc" value="${apiRsp.caseAssessmentReport.surveyDesc}" />
                </td>
            </tr>
            </tbody>
        </table>
        <table>
            <tbody>
            <tr class="border-1 padding-1 height-1">
                <td colspan="5" class="text-color-1 table-title padding-2">
                    案件赔偿及保险理赔
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-30 text-align-1">是否属于保险事故责任</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="radio" name="isInsuranceAccident" value="1" <c:if test="${apiRsp.caseAssessmentReport.isInsuranceAccident == 1}">checked = "checked"</c:if>/>属于
                    <input type="radio" name="isInsuranceAccident" value="0" <c:if test="${apiRsp.caseAssessmentReport.isInsuranceAccident == 0}">checked = "checked"</c:if>/>不属于
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-30 text-align-1">是否存在保险拒赔或免赔行为</td>
                <td class="border-2 padding-3" colspan="3">
                    <p>
                        <input type="radio" name="isClaimsRejected" value="0" <c:if test="${apiRsp.caseAssessmentReport.isClaimsRejected == 0}">checked = "checked"</c:if>/>不存在
                        <input type="radio" name="isClaimsRejected" value="1" <c:if test="${apiRsp.caseAssessmentReport.isClaimsRejected == 1}">checked = "checked"</c:if>/>存在
                        <span>（<input type="text" class="input-3" name="claimsRejected" value="${apiRsp.caseAssessmentReport.claimsRejected}" />）</span>
                    </p>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-30 text-align-1">车架号核对是否正确</td>
                <td class="border-2 padding-3" colspan="3">
                    <p>
                        <input type="radio" name="isFrameNumber" value="1" <c:if test="${apiRsp.caseAssessmentReport.isFrameNumber == 1}">checked = "checked"</c:if>/>正确
                        <input type="radio" name="isFrameNumber" value="0" <c:if test="${apiRsp.caseAssessmentReport.isFrameNumber == 0}">checked = "checked"</c:if>/>有误
                        <span>（<input type="text" class="input-3" name="frameNumber" value="${apiRsp.caseAssessmentReport.frameNumber}" />）</span>
                    </p>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-30 text-align-1">行驶证、驾驶证是否有效</td>
                <td class="border-2 padding-3" colspan="3">
                    <p>
                        <input type="radio" name="isEffectiveCarNumber" value="1" <c:if test="${apiRsp.caseAssessmentReport.isEffectiveCarNumber == 1}">checked = "checked"</c:if>/>有效
                        <input type="radio" name="isEffectiveCarNumber" value="0" <c:if test="${apiRsp.caseAssessmentReport.isEffectiveCarNumber == 0}">checked = "checked"</c:if>/>无效
                        <span>（<input type="text" class="input-3" name="effectiveCarNumber" value="${apiRsp.caseAssessmentReport.effectiveCarNumber}" />）</span>
                    </p>
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-30 text-align-1">伤残/死亡赔偿金适用标准</td>
                <td class="border-2 padding-3" colspan="3">
                    <input type="radio" name="compensationStandard" value="1" <c:if test="${apiRsp.caseAssessmentReport.compensationStandard == 1}">checked = "checked"</c:if>/>城镇
                    <input type="radio" name="compensationStandard" value="2" <c:if test="${apiRsp.caseAssessmentReport.compensationStandard == 2}">checked = "checked"</c:if>/>农村
                </td>
            </tr>
            <tr class='height-1'>
                <td rowspan="4" class="back-color-1 border-1 padding-1 height-1 table-title text-align-1 row-30">
                    伤残等级/三期评估
                </td>
                <td class="padding-2 border-2 back-color-2 row-20">伤残等级</td>
                <td class="border-2 padding-3" colspan="2">
                    <input type="number" step="0" min="0" max="10" class="input-2" name="disabilityGrade" value="${apiRsp.caseAssessmentReport.disabilityGrade}" />级
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">误工期限</td>
                <td class="border-2 padding-3" colspan="2">
                    <input type="number" step="0" min="0" class="input-2" name="downtime" value="${apiRsp.caseAssessmentReport.downtime}" />天
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">护理期限</td>
                <td class="border-2 padding-3" colspan="2">
                    <input type="number" step="0" min="0" class="input-2" name="nursingTime" value="${apiRsp.caseAssessmentReport.nursingTime}" />天
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-20">营养期限</td>
                <td class="border-2 padding-3" colspan="2">
                    <input type="number" step="0" min="0" class="input-2" name="nutritionTime" value="${apiRsp.caseAssessmentReport.nutritionTime}" />天
                </td>
            </tr>
            <tr class='height-1'>
                <td class="padding-2 border-2 back-color-2 row-30 text-align-1">事故责任赔偿比例分担</td>
                <td class="border-2" colspan="3">
                    <table id="table-nei">
                        <tbody>
                        <tr class='height-1'>
                            <td class="text-align-1 border-2">
                                被保险人承担赔偿比例<input type="number" step="0" min="0" max="100" class="input-2" name="insurantResponsibilityRate" value="${apiRsp.caseAssessmentReport.insurantResponsibilityRate}" />%
                            </td>
                            <td class="text-align-1">
                                伤/死者承担赔偿比例<input type="number" step="0" min="0" max="100" class="input-2" name="injuredResponsibilityRate" value="${apiRsp.caseAssessmentReport.injuredResponsibilityRate}" />%
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
            </tbody>
        </table>
        <table>
            <tbody>
            <tr class='height-1'>
                <td class="border-1 back-color-1 row-30 text-align-1" rowspan="2" colspan="1">赔偿项目</td>
                <td class="border-1 back-color-1 row-20 text-align-1" rowspan="2">核损金额</td>
                <td class="border-1 back-color-1 row-25 text-align-1" rowspan="2">核损依据</td>
                <td class="border-1 back-color-1 row-25 text-align-1" colspan="2">
                    保险理赔金额
                </td>
            </tr>
            <tr class='height-1'>
                <td class="border-1 back-color-1 text-align-1 row-12">交强险</td>
                <td class="border-1 back-color-1 text-align-1 row-12">商业险</td>
            </tr>
            <c:forEach items="${dtos}" var="item">
                <tr class='height-1'>
                    <td class="padding-2 border-2 back-color-2 row-20">
                        ${item.projectName == "医疗小计" || item.projectName == "赔款小计" || item.projectName == "小计" ? "小计" : item.projectName}
                    </td>
                    <td class="border-2 row-20">
                        <input type="number" step="0.01" min="0" placeholder="请输入核损金额" <c:if test="${op == 'edit'}">onblur="blurUpdAsmObjReport(${apiRsp.caseAssessmentReport.id == null ? '-1' : apiRsp.caseAssessmentReport.id},${item.id},1,this,${apiRsp.caseAssessmentReport.caseId},'${apiRsp.caseAssessmentReport.caseNo}')"</c:if> class="input-1 text-align-1" name="checkAmount" value="${item.checkAmount}"/>
                    </td>
                    <td class="border-2 row-25">
                        <input type="text" placeholder="请输入核损依据" <c:if test="${op == 'edit'}">onblur="blurUpdAsmObjReport(${apiRsp.caseAssessmentReport.id == null ? '-1' : apiRsp.caseAssessmentReport.id},${item.id},2,this,${apiRsp.caseAssessmentReport.caseId},'${apiRsp.caseAssessmentReport.caseNo}')"</c:if> class="input-1 text-align-1" name="checkBasis" value="${item.checkBasis}" />
                    </td>
                    <td class="border-2">
                        <input type="number" min="0" step="0.01" placeholder="请输入交强险"  <c:if test="${op == 'edit'}">onblur="blurUpdAsmObjReport(${apiRsp.caseAssessmentReport.id == null ? '-1' : apiRsp.caseAssessmentReport.id},${item.id},3,this,${apiRsp.caseAssessmentReport.caseId},'${apiRsp.caseAssessmentReport.caseNo}')"</c:if> class="input-1 text-align-1" name="commerAmount" value="${item.commerAmount}"/>
                    </td>
                    <td class="border-2">
                        <input type="number" min="0" step="0.01" placeholder="请输入商业险" <c:if test="${op == 'edit'}">onblur="blurUpdAsmObjReport(${apiRsp.caseAssessmentReport.id == null ? '-1' : apiRsp.caseAssessmentReport.id},${item.id},4,this,${apiRsp.caseAssessmentReport.caseId},'${apiRsp.caseAssessmentReport.caseNo}')"</c:if> class="input-1 text-align-1" name="compulAmount" value="${item.compulAmount}"/>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${op != 'view'}">
                <tr>
                    <td colspan="5" style="text-align: right">
                        <button onclick="return refreshSave();" type="button" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 保存</button>
                    </td>
                </tr>
            </c:if>
            <c:if test="${dtos.size() == 0}">
                <tr class="height-1">
                    <td colspan="5" style="text-align: center;color: red;">无项目信息</td>
                </tr>
            </c:if>

            <tr>
                <td colspan="6" class="border-1 padding-2" style="padding-top: 10px;">
                    <span style="display: block;float: left;">案件赔偿及保险理赔评估金额说明：</span><textarea name="insuranceClaimsDesc">${apiRsp.caseAssessmentReport.insuranceClaimsDesc}</textarea>
                </td>
            </tr>
            </tbody>
        </table>
        <table id="table-nei">
            <tbody>
            <tr class='height-1'>
                <td class="border-1 back-color-1 text-align-1">
                    保险公估人
                </td>
                <td class="border-2">
                    <input type="text" class="input-1 text-align-1" name="assessorName" value="${apiRsp.caseAssessmentReport.assessorName}"/>
                </td>
                <td class="border-1 back-color-1 text-align-1">
                    公估执业证号
                </td>
                <td class="border-2">
                    <input type="text" class="input-1 text-align-1" name="assessorCertificate" value="${apiRsp.caseAssessmentReport.assessorCertificate}"/>
                </td>
                <td class="border-1 back-color-1 text-align-1">
                    公估日期
                </td>
                <td class="border-2">
                    <input name="assessmentDate" type="text"  value="<fmt:formatDate value="${apiRsp.caseAssessmentReport.assessmentDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control">
                </td>
            </tr>
            <tr class='height-1'>
                <td class="border-1 back-color-1 text-align-1">
                    公估审核人
                </td>
                <td class="border-2">
                    <input type="text" class="input-1 text-align-1" name="auditorName" value="${apiRsp.caseAssessmentReport.auditorName}"/>
                </td>
                <td class="border-1 back-color-1 text-align-1">
                    审核人公估执业证号
                </td>
                <td class="border-2">
                    <input type="text" class="input-1 text-align-1" name="auditorCertificate" value="${apiRsp.caseAssessmentReport.auditorCertificate}"/>
                </td>
                <td class="border-1 back-color-1 text-align-1">
                    签发日期
                </td>
                <td class="border-2">
                    <input name="assessmentIssueDate" type="text"  value="<fmt:formatDate value="${apiRsp.caseAssessmentReport.assessmentIssueDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control">

                </td>
            </tr>
            <tr class='height-1'>
                <td class="border-2 text-align-1" colspan="6">
                    加盖江苏乐凡赔偿保险公估有限公司业务专用章
                </td>
            </tr>
            </tbody>
        </table>
        <c:if test="${op == 'edit'}">
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
    function refreshSave(){
        location.reload();
    }
    function blurUpdAsmObjReport(reportId,id,type,obj,caseId,caseNo){
//        //输入的值为空则 不保存
//        if(obj.value == null || obj.value == 0){
//            return;
//        }
        if(reportId == -1){
            reportId = "";
        }
        ajaxSubmit("${ctx}/case/report/updCaseAssessmentObjReport?reportId="+reportId+"&id=" + id + "&type=" + type + "&value=" + obj.value+"&caseId="+caseId+"&caseNo="+caseNo,null,function(v,e,p){

        })
    }
</script>
</body>
</html>
