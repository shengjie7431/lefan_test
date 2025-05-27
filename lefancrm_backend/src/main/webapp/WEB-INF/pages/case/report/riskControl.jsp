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

    .row-15 {
        width: 15%;
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
    <form id="editForm" role="form" action="${ctx}/case/report/saveRiskControl" method="post">
        <input type="hidden" name="id" value="${apiRsp.caseRiskControl.id}">
        <input type="hidden" name="caseId" value="${apiRsp.caseRiskControl.caseId}">
        <input type="hidden" name="caseNo" value="${apiRsp.caseRiskControl.caseNo}">
            <h2 class="title">“人伤全无忧”保险评估报告</h2>
            <p class="text-align-2">评估编号：${apiRsp.caseRiskControl.caseNo}</p>
            <table>
                <tbody>
                <tr class="back-color-1 border-1 padding-1 height-1">
                    <td colspan="6" class="table-title padding-2">
                        致：长安责任保险股份有限公司
                    </td>
                </tr>
                <tr class="border-1 padding-1 height-1">
                    <td colspan="6" class="text-color-1 table-title padding-2">
                        借贷人信息
                    </td>
                </tr>
                <tr class="height-1">
                    <td rowspan="4" class="back-color-1 border-1 table-title text-align-1 row-10">
                        身<br />份<br />信<br />息
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-25">
                        姓名
                    </td>
                    <td colspan="2" class="padding-2 row-30 border-2">
                        <input type="text" class="input-1" name="userName" value="${apiRsp.caseRiskControl.userName}"/>
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-15">
                        性别
                    </td>
                    <td class="border-2 padding-2">
                        <select id = "sex" name="sex" class="select-1">
                            <option value="">请选择</option>
                            <option value="1" <c:if test="${apiRsp.caseRiskControl.sex == 1}">selected = selected</c:if>>男</option>
                            <option value="2" <c:if test="${apiRsp.caseRiskControl.sex == 2}">selected = selected</c:if>>女</option>
                        </select>
                    </td>
                </tr>
                <tr class="height-1">
                    <td class="back-color-2 border-2 padding-2 row-25">
                        身份证号码
                    </td>
                    <td colspan="2" class="padding-2 row-30 border-2">
                        <input type="text" class="input-1" name="idCard" value="${apiRsp.caseRiskControl.idCard}"/>
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-15">
                        联系电话
                    </td>
                    <td class="border-2 padding-2">
                        <input type="text" class="input-1" name="userTel" value="${apiRsp.caseRiskControl.userTel}"/>
                    </td>
                </tr>
                <tr class="height-1">
                    <td class="back-color-2 border-2 padding-2 row-25">
                        身份证地址
                    </td>
                    <td colspan="4" class="padding-2 row-30 border-2">
                        <input type="text" class="input-1" name="idAddress" value="${apiRsp.caseRiskControl.idAddress}"/>
                    </td>
                </tr>
                <tr class="height-1">
                    <td class="back-color-2 border-2 padding-2 row-25">
                        现居住地址
                    </td>
                    <td colspan="4" class="padding-2 row-30 border-2">
                        <input type="text" class="input-1" name="residentialAddress" value="${apiRsp.caseRiskControl.residentialAddress}"/>
                    </td>
                </tr>
                <tr class="height-1">
                    <td rowspan="3" class="back-color-1 border-1 table-title text-align-1 row-10">
                        工<br />作<br />信<br />息
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-25">
                        工作单位名称
                    </td>
                    <td colspan="2" class="padding-2 row-30 border-2">
                        <input type="text" class="input-1" name="employeName" value="${apiRsp.caseRiskControl.employeName}"/>
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-15">
                        职位
                    </td>
                    <td class="border-2 padding-2">
                        <input type="text" class="input-1" name="employePosition" value="${apiRsp.caseRiskControl.employePosition}"/>
                    </td>
                </tr>
                <tr class="height-1">
                    <td class="back-color-2 border-2 padding-2 row-25">
                        工作单位地址
                    </td>
                    <td colspan="4" class="padding-2 row-30 border-2">
                        <input type="text" class="input-1" name="employeAddress" value="${apiRsp.caseRiskControl.employeAddress}"/>
                    </td>
                </tr>
                <tr class="height-1">
                    <td class="back-color-2 border-2 padding-2 row-25">
                        现工作单位年限
                    </td>
                    <td colspan="2" class="padding-2 row-30 border-2">
                        <input type="number" step="0" min="0" class="input-2" name="workYear" value="${apiRsp.caseRiskControl.workYear}"/>
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-15">
                        月税后工资
                    </td>
                    <td class="border-2 padding-2">
                        <input type="number" step="0.01" min="0"  class="input-2" name="wages" value="${apiRsp.caseRiskControl.wages}"/>元
                    </td>
                </tr>
                <tr class="height-1">
                    <td rowspan="3" class="back-color-1 border-1 table-title text-align-1 row-10">
                        家<br />庭<br />信<br />息
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-25">
                        婚姻情况
                    </td>
                    <td colspan="2" class="padding-2 row-30 border-2">
                        <input type="radio" name="maritalStatus" value="1" <c:if test="${apiRsp.caseRiskControl.maritalStatus == 1}">checked = "checked"</c:if>/>已婚
                        <input type="radio" name="maritalStatus" value="2" <c:if test="${apiRsp.caseRiskControl.maritalStatus == 2}">checked = "checked"</c:if>/>未婚
                        <input type="radio" name="maritalStatus" value="3" <c:if test="${apiRsp.caseRiskControl.maritalStatus == 3}">checked = "checked"</c:if>/>离异
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-15">
                        其他贷款及月供
                    </td>
                    <td class="border-2 padding-2">
                        <input type="text" class="input-1" name="otherLoan" value="${apiRsp.caseRiskControl.otherLoan}"/>
                    </td>
                </tr>
                <tr class="height-1">
                    <td class="back-color-2 border-2 padding-2 row-25">
                        配偶姓名
                    </td>
                    <td colspan="2" class="padding-2 row-30 border-2">
                        <input type="text" class="input-1" name="spouseName" value="${apiRsp.caseRiskControl.spouseName}"/>
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-15">
                        配偶电话
                    </td>
                    <td class="border-2 padding-2">
                        <input type="text" class="input-1" name="spouseTel" value="${apiRsp.caseRiskControl.spouseTel}"/>
                    </td>
                </tr>
                <tr class="height-1">
                    <td class="back-color-2 border-2 padding-2 row-25">
                        紧急联系人
                    </td>
                    <td colspan="2" class="padding-2 row-30 border-2">
                        <input type="text" class="input-1" name="egyLinkman" value="${apiRsp.caseRiskControl.egyLinkman}"/>
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-15">
                        紧急联系人电话
                    </td>
                    <td class="border-2 padding-2">
                        <input type="text" class="input-1" name="egyLinkmanTel" value="${apiRsp.caseRiskControl.egyLinkmanTel}"/>
                    </td>
                </tr>
                <tr class="border-1 padding-1 height-1">
                    <td colspan="5" class="text-color-1 table-title padding-2">
                        借贷信息
                    </td>
                </tr>
                <tr class="height-1">
                    <td rowspan="3" class="back-color-1 border-1 table-title text-align-1 row-10">
                        借<br />贷<br />申<br />请
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-25">
                        借贷用途
                    </td>
                    <td colspan="4" class="padding-2 row-30 border-2">
                        <input type="radio" name="loanPurpose" value="1" <c:if test="${apiRsp.caseRiskControl.loanPurpose == 1}">checked = "checked"</c:if>/>医疗费垫付
                        <input type="radio" name="loanPurpose" value="2" <c:if test="${apiRsp.caseRiskControl.loanPurpose == 2}">checked = "checked"</c:if>/>赔偿费垫付
                        <input type="radio" name="loanPurpose" value="3" <c:if test="${apiRsp.caseRiskControl.loanPurpose == 3}">checked = "checked"</c:if>/>其他
                    </td>
                </tr>
                <tr class="height-1">
                    <td class="back-color-2 border-2 padding-2 row-25">
                        借贷金额
                    </td>
                    <td colspan="2" class="padding-2 row-30 border-2">
                        <input type="number" step="0.01" min="0" class="input-2" name="loanMoney" value="${apiRsp.caseRiskControl.loanMoney}"/>元
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-15">
                        借贷周期
                    </td>
                    <td class="border-2 padding-2">
                        <input type="number" step="0" min="0" class="input-2" name="lendingCycle" value="${apiRsp.caseRiskControl.lendingCycle}"/>个月
                    </td>
                </tr>
                <tr class="height-1">
                    <td class="back-color-2 border-2 padding-2 row-25">
                        还款方式
                    </td>
                    <td colspan="4" class="padding-2 row-30 border-2">
                        <%--<input type="text" class="input-1" name="repaymentMethod" value="${apiRsp.caseRiskControl.repaymentMethod}"/>--%>
                        <select name="repaymentMethod" class="form-control" style="width: 400px;">
                            <option value="">请选择</option>
                            <option value="扣费放款" <c:if test="${apiRsp.caseRiskControl.repaymentMethod == '扣费放款'}">selected="selected" </c:if> >扣费放款</option>
                            <option value="到期还本利息"<c:if test="${apiRsp.caseRiskControl.repaymentMethod == '到期还本利息'}">selected="selected" </c:if> >到期还本利息</option>
                        </select>
                    </td>
                </tr>
                <tr class="border-1 padding-1 height-1">
                    <td colspan="5" class="text-color-1 table-title padding-2">
                        保险公估信息
                    </td>
                </tr>
                <tr class="height-1">
                    <td colspan="2" class="back-color-2 border-2 padding-2 row-25">
                        人伤事故是否真实
                    </td>
                    <td colspan="2" class="padding-2 row-30 border-2">
                        <input type="radio" name="isReal" value="1" <c:if test="${apiRsp.caseRiskControl.isReal == 1}">checked = "checked"</c:if>/>真实
                        <input type="radio" name="isReal" value="0" <c:if test="${apiRsp.caseRiskControl.isReal == 0}">checked = "checked"</c:if>/>不真实
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-15">
                        是否属于保险事故责任
                    </td>
                    <td class="border-2 padding-2">
                        <input type="radio" name="isInsurance" value="1" <c:if test="${apiRsp.caseRiskControl.isInsurance == 1}">checked = "checked"</c:if>/>属于
                        <input type="radio" name="isInsurance" value="0" <c:if test="${apiRsp.caseRiskControl.isInsurance == 0}">checked = "checked"</c:if>/>不属于
                    </td>
                </tr>
                <tr class="height-1">
                    <td colspan="2" class="back-color-2 border-2 padding-2 row-25">
                        是否存在拒赔或免赔行为
                    </td>
                    <td colspan="4" class="padding-2 row-30 border-2">
                        <p>
                            <input type="radio" name="isExclusions" value="0" <c:if test="${apiRsp.caseRiskControl.isExclusions == 0}">checked = "checked"</c:if>/>不存在
                            <input type="radio" name="isExclusions" value="1" <c:if test="${apiRsp.caseRiskControl.isExclusions == 1}">checked = "checked"</c:if>/>存在
                            <span>（<input type="text" class="input-3" name="exclusionsDesc" value="${apiRsp.caseRiskControl.exclusionsDesc}"/>）</span></p>
                    </td>
                </tr>
                <tr class="height-1">
                    <td colspan="2" class="back-color-2 border-2 padding-2 row-25">
                        保险理赔金额评估
                    </td>
                    <td colspan="2" class="padding-2 row-30 border-2">
                        <input type="number" step="0.01" min="0" class="input-2" name="assessmentAmount" value="${apiRsp.caseRiskControl.assessmentAmount}"/>元
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-15">
                        保险理赔结案周期评估
                    </td>
                    <td class="border-2 padding-2">
                        <input type="number" step="0" class="input-2" name="assessmentCycle" value="${apiRsp.caseRiskControl.assessmentCycle}"/>个月
                    </td>
                </tr>
                <tr class="border-1 padding-1 height-1">
                    <td colspan="5" class="text-color-1 table-title padding-2">
                        风险评估意见
                    </td>
                </tr>
                <tr class="height-1">
                    <td colspan="2" class="back-color-2 border-2 padding-2 row-25">
                        借贷人信息是否真实
                    </td>
                    <td colspan="2" class="padding-2 row-30 border-2">
                        <input type="radio" name="isRealMan" value="1" <c:if test="${apiRsp.caseRiskControl.isRealMan == 1}">checked = "checked"</c:if>/>真实
                        <input type="radio" name="isRealMan" value="0" <c:if test="${apiRsp.caseRiskControl.isRealMan == 0}">checked = "checked"</c:if>/>不真实
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-15">
                        事由的场景是否真实
                    </td>
                    <td class="border-2 padding-2">
                        <input type="radio" name="isRealReason" value="1" <c:if test="${apiRsp.caseRiskControl.isRealReason == 1}">checked = "checked"</c:if>/>真实
                        <input type="radio" name="isRealReason" value="0" <c:if test="${apiRsp.caseRiskControl.isRealReason == 0}">checked = "checked"</c:if>/>不真实
                    </td>
                </tr>
                <tr class="height-1">
                    <td colspan="2" class="back-color-2 border-2 padding-2 row-25">
                        借贷人信息是否符合条件
                    </td>
                    <td colspan="2" class="padding-2 row-30 border-2">
                        <input type="radio" name="isSatisfy" value="1" <c:if test="${apiRsp.caseRiskControl.isSatisfy == 1}">checked = "checked"</c:if>/>符合
                        <input type="radio" name="isSatisfy" value="0" <c:if test="${apiRsp.caseRiskControl.isSatisfy == 0}">checked = "checked"</c:if>/>不符合
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-15">
                        申请金额/评估可保险理赔金额
                    </td>
                    <td class="border-2 padding-2">
                        <input type="number" step="0.01" class="input-1" name="settlementMoney" value="${apiRsp.caseRiskControl.settlementMoney}"/>
                    </td>
                </tr>
                <tr class="height-1">
                    <td colspan="2" class="back-color-2 border-2 padding-2 row-25">
                        乐凡金融匹配借贷产品
                    </td>
                    <td colspan="4" class="padding-2 row-30 border-2">
                        <input type="radio" name="matchingProduct" value="1" <c:if test="${apiRsp.caseRiskControl.matchingProduct == 1}">checked = "checked"</c:if>/>人伤全无忧保证贷款
                        <input type="radio" name="matchingProduct" value="2" <c:if test="${apiRsp.caseRiskControl.matchingProduct == 2}">checked = "checked"</c:if>/>人伤全无忧担保结算
                        <input type="radio" name="matchingProduct" value="3" <c:if test="${apiRsp.caseRiskControl.matchingProduct == 3}">checked = "checked"</c:if>/>人伤全无忧资金垫付
                    </td>
                </tr>
                <tr class="height-1">
                    <td colspan="2" class="back-color-2 border-2 padding-2 row-25">
                        实际放款
                    </td>
                    <td colspan="2" class="padding-2 row-30">
                        金额：<input type="number" step="0.01" class="input-2" name="lefanProposalMoney" value="${apiRsp.caseRiskControl.lefanProposalMoney}"/>元
                    </td>
                    <td colspan="2" class="padding-2 row-30">
                        周期：<input type="number" step="0" class="input-2" name="lefanProposalCycle" value="${apiRsp.caseRiskControl.lefanProposalCycle}"/>个月
                    </td>
                </tr>
                <tr class="height-1">
                    <td colspan="2" rowspan="4" class="back-color-2 border-2 padding-2 row-25">
                        资金放款账户信息
                    </td>
                    <td class="back-color-2 border-2 padding-2 row-15">
                        账户名
                    </td>
                    <td colspan="3" class="border-2 padding-2">
                        <input type="text" class="input-1" name="advanceName" value="${apiRsp.caseRiskControl.advanceName}"/>
                    </td>
                </tr>
                <tr class="height-1">
                    <td class="back-color-2 border-2 padding-2 row-15">
                        开户行
                    </td>
                    <td colspan="3" class="border-2 padding-2">
                        <input type="text" class="input-1" name="advanceBank" value="${apiRsp.caseRiskControl.advanceBank}"/>
                    </td>
                </tr>
                <tr class="height-1">
                    <td class="back-color-2 border-2 padding-2 row-15">
                        账户号
                    </td>
                    <td colspan="3" class="border-2 padding-2">
                        <input type="text" class="input-1" name="advanceAccount" value="${apiRsp.caseRiskControl.advanceAccount}"/>
                    </td>
                </tr>
                <tr class="height-1">
                    <td class="back-color-2 border-2 padding-2 row-15">
                        备注
                    </td>
                    <td colspan="3" class="border-2 padding-2">
                        <input type="text" class="input-1" name="advanceRemarks" value="${apiRsp.caseRiskControl.advanceRemarks}"/>
                    </td>
                </tr>
                <tr class="height-1">
                    <td colspan="2" class="back-color-2 border-2 padding-2 row-15">
                        乐凡金融风控其他意见
                    </td>
                    <td colspan="4" class="border-2 padding-2">
                        <input type="text" class="input-1" name="riskOpinion" value="${apiRsp.caseRiskControl.riskOpinion}"/>
                    </td>
                </tr>
                </tbody>
            </table>
            <table id="table-nei">
                <tbody>
                <tr class="height-1">
                    <td class="back-color-1 border-1 padding-2">风控专员签名</td>
                    <td class="border-2 padding-2"><input type="text" class="input-1" name="riskCommissioner" value="${apiRsp.caseRiskControl.riskCommissioner}"/></td>
                    <td class="back-color-1 border-1 padding-2">风控专员手机</td>
                    <td class="border-2 padding-2"><input type="text" class="input-1" name="riskCommissionerTel" value="${apiRsp.caseRiskControl.riskCommissionerTel}"/></td>
                    <td class="back-color-1 border-1 padding-2">审核日期</td>
                    <td class="border-2 padding-2">
                        <input name="checkTime" type="text"  value="<fmt:formatDate value="${apiRsp.caseRiskControl.checkTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control">
                    </td>
                </tr>
                <tr class="height-1">
                    <td class="back-color-1 border-1 padding-2">风控负责人签名</td>
                    <td class="border-2 padding-2"><input type="text" class="input-1" name="riskHead" value="${apiRsp.caseRiskControl.riskHead}"/></td>
                    <td class="back-color-1 border-1 padding-2">风控负责人手机</td>
                    <td class="border-2 padding-2"><input type="text" class="input-1" name="riskHeadTel" value="${apiRsp.caseRiskControl.riskHeadTel}"/></td>
                    <td class="back-color-1 border-1 padding-2">签发日期</td>
                    <td class="border-2 padding-2">
                        <input name="issueTime" type="text"  value="<fmt:formatDate value="${apiRsp.caseRiskControl.issueTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control">
                    </td>
                </tr>
                <tr class='height-1'>
                    <td class="border-2 text-align-1" colspan="6">
                        加盖上海乐凡金融信息服务有限公司业务专用章
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
</script>
</body>
</html>
