<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<jsp:useBean id="dateValue" class="java.util.Date"/>
<!DOCTYPE html>
<html>

	<head>
        <title>结案报告</title>
        <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
        <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
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
				width: 23.6%;
			}
			
			.row-25 {
				width: 18%;
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

            .input-5 {
                width: 100%;
                height: 100%;
                border: none;
                border-bottom: 1px solid #ccc;
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
	</head>

	<body>
		<div class="Presentation">
        <form id="editForm" role="form" action="${ctx}/case/report/saveCloseReport" method="post">
        <input type="hidden" name="id" value="${apiRsp.caseCloseReport.id}">
        <input type="hidden" name="caseId" value="${apiRsp.caseCloseReport.caseId}">
        <input type="hidden" name="caseNo" value="${apiRsp.caseCloseReport.caseNo}">
        <h2 class="title">结案报告</h2>
			<table>
				<tbody>
					<tr class="back-color-1 border-1 padding-1 height-1">
						<td colspan="4" class="table-title padding-2">
							基本情况
						</td>
					</tr>
					<tr class="height-1">
						<td class="back-color-2 border-2 row-20 padding-2">委托人</td>
						<td class="border-2 padding-2">
							<input type="text" class="input-1" name="clienteleName" value="${apiRsp.caseCloseReport.clienteleName}"/>
						</td>
						<td class="back-color-2 border-2 row-20 padding-2">承接机构</td>
						<td class="border-2 padding-2">
							<input type="text" class="input-1" name="orgName" value="${apiRsp.caseCloseReport.orgName}"/>
						</td>
					</tr>
					<tr class="height-1">
						<td class="back-color-2 border-2 row-20 padding-2">委托类型</td>
						<td class="border-2 padding-2">
							<input type="radio" name="entrustType" value="1" <c:if test="${apiRsp.caseCloseReport.entrustType == 1}">checked = "checked"</c:if>/>单纯代理
							<input type="radio" name="entrustType" value="2" <c:if test="${apiRsp.caseCloseReport.entrustType == 2}">checked = "checked"</c:if>/>贷款+代理
						</td>
						<td class="back-color-2 border-2 row-20 padding-2">委托时间</td>
						<td class="border-2 padding-2">
							<input type="text" class="input-1" name="entrustTime" value="<fmt:formatDate value="${apiRsp.caseCloseReport.entrustTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						</td>
					</tr>
					<tr class="height-1">
						<td class="back-color-2 border-2 row-20 padding-2">结案方式</td>
						<td class="border-2 padding-2">
							<input type="radio" name="closedType" value="1"  <c:if test="${apiRsp.caseCloseReport.closedType == 1}">checked = "checked"</c:if>/>非诉讼调解
							<input type="radio" name="closedType" value="2"  <c:if test="${apiRsp.caseCloseReport.closedType == 2}">checked = "checked"</c:if>/>诉讼
						</td>
						<td class="back-color-2 border-2 row-20 padding-2">事故赔偿比例</td>
						<td class="border-2 padding-2">
							<input type="number" step="0" min="0" max="100" class="input-2" name="compensateRate" onblur="compensateRageBlur()" value="${apiRsp.caseCloseReport.compensateRate}" />%
						</td>
					</tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 row-20 padding-2">结案阶段</td>
                        <td class="border-2 padding-2">
                            <input type="radio" name="stageType" value="1"  <c:if test="${apiRsp.caseCloseReport.stageType == 1}">checked = "checked"</c:if>/>一审
                            <input type="radio" name="stageType" value="2"  <c:if test="${apiRsp.caseCloseReport.stageType == 2}">checked = "checked"</c:if>/>二审
                        </td>
                        <td class="back-color-2 border-2 row-20 padding-2"></td>
                        <td class="border-2 padding-2">
                        </td>
                    </tr>
					<tr class="height-1">
						<td class="back-color-2 border-2row-20 padding-2">索赔员</td>
						<td class="border-2 padding-2">
							<input type="text" class="input-1"  name="claimantName" value="${apiRsp.caseCloseReport.claimantName}"/>
						</td>
                        <td class="back-color-2 border-2 row-20 padding-2">索赔开始时间</td>
                        <td class="border-2 padding-2">

                            <input type="text" class="input-1" name="claimBeginTime" value="<fmt:formatDate value="${apiRsp.caseCloseReport.claimBeginTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
                        </td>
					</tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 row-20 padding-2">诉讼专员</td>
                        <td class="border-2 padding-2">
                            <input type="text" class="input-1"  name="legalUser" value="${apiRsp.caseCloseReport.legalUser}"/>
                        </td>
                        <td class="back-color-2 border-2 row-20 padding-2">诉讼开始时间</td>
                        <td class="border-2 padding-2">
                            <input type="text" class="input-1" name="legalBeginTime" value="<fmt:formatDate value="${apiRsp.caseCloseReport.legalBeginTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
                        </td>
                    </tr>
					<tr class="height-1">
						<td class="back-color-2 border-2 row-20 padding-2">代理律师</td>
						<td class="border-2 padding-2">
							<input type="text" class="input-1"  name="agentLawyer" value="${apiRsp.caseCloseReport.agentLawyer}"/>
						</td>
                        <td class="back-color-2 border-2 row-20 padding-2">律师费</td>
                        <td class="border-2 padding-2">
                            <input type="text" class="input-1"  name="agentLawyerMoney" value="${apiRsp.caseCloseReport.agentLawyerMoney}"/>
                        </td>
					</tr>
				</tbody>
			</table>
			<table id="table-nei">
				<tbody>
					<tr class="back-color-1 border-1 padding-1 height-1">
						<td colspan="6" class="table-title padding-2">
							结案赔偿情况
						</td>
					</tr>
					<tr class="height-1">
						<td class="back-color-2 border-2 row-20 text-align-1">损失项目</td>
						<td class="back-color-2 border-2 row-20 text-align-1">确认损失金额</td>
                        <c:if test="${op == 'view' || op == 'audit'}">
                            <td class="back-color-2 border-2 row-20 text-align-1">审核金额</td>
                        </c:if>
                        <td class="back-color-2 border-2 row-15 text-align-1">交强险</td>
                        <td class="back-color-2 border-2 row-15 text-align-1">商业险</td>
                        <td class="back-color-2 border-2 row-15 text-align-1">肇事方</td>
					</tr>
                    <c:forEach items="${dtos}" var="item">
                        <tr class="height-1">
                            <td class="back-color-2 border-2 row-20 padding-2">${item.objectName}</td>
                            <td class="border-2 row-20 padding-2"><input type="number" step="0.01" placeholder="请输入金额" <c:if test="${op == 'edit'}">onblur="blurUpdCloseObjReport(${apiRsp.caseCloseReport.id == null ? 'null' : apiRsp.caseCloseReport.id},${item.id},1,this,${apiRsp.caseCloseReport.caseId},'${apiRsp.caseCloseReport.caseNo}')"</c:if> class="input-1" value="${item.opinionMoney}" /></td>
                            <c:if test="${op == 'audit'}">
                                <td class="border-2 row-20 padding-2"><input type="number" step="0.01" placeholder="请输入金额" onblur="blurUpdCloseObjReport(${apiRsp.caseCloseReport.id == null ? 'null' : apiRsp.caseCloseReport.id},${item.id},2,this,${apiRsp.caseCloseReport.caseId},'${apiRsp.caseCloseReport.caseNo}')" class="input-1" value="${item.insOmpanyMoney}"/></td>
                            </c:if>
                            <c:if test="${op == 'view'}">
                                <td class="border-2 row-20 padding-2"><input type="number" step="0.01" placeholder="请输入金额"  class="input-1" value="${item.insOmpanyMoney}"/></td>
                            </c:if>
                            <td class="border-2 row-15 padding-2">${item.commerMoney}</td>
                            <td class="border-2 row-15 padding-2">${item.compulMoney}</td>
                            <td class="border-2 row-15 padding-2">${item.driverMoney}</td>
                        </tr>
                    </c:forEach>
                    <c:if test="${op != 'view'}">
                        <tr>
                            <td colspan="6" style="text-align: right">
                                <button onclick="return refreshSave();" type="button" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 保存</button>
                            </td>
                        </tr>
                    </c:if>
					<tr>
						<td colspan="6" class="border-2 row-26 padding-2">保险公司最终赔偿金额（按责任）（指最终赔偿到伤者账户的赔偿金额）合计： <input type="number" step="0.01" class="input-2" name="compensateMoney" value="${apiRsp.caseCloseReport.compensateMoney}">元； </br>
							其中：交强险<input type="number" step="0.01" class="input-2"  name="compulsoryMoney" value="${apiRsp.caseCloseReport.compulsoryMoney}"> 元；商业险 <input type="number" step="0.01" class="input-2"  name="commercialMoney" value="${apiRsp.caseCloseReport.commercialMoney}">元；</br>
                            肇事方赔偿金额 <input type="number" step="0.01" class="input-2"  name="driverMoney" value="${apiRsp.caseCloseReport.driverMoney}">元； </br>
							伤者可获得的赔偿款总额：<input type="number" step="0.01" class="input-2"  name="injuredMoney" value="${apiRsp.caseCloseReport.injuredMoney}"> 元；</br>
                            支付伤者金额：<input type="number" step="0.01" class="input-2"  name="payWoundedMoney" value="${apiRsp.caseCloseReport.payWoundedMoney}"> 元；
                            支付驾驶员金额：<input type="number" step="0.01" class="input-2"  name="payDriverMoney" value="${apiRsp.caseCloseReport.payDriverMoney}"> 元；
                            支付保险公司金额：<input type="number" step="0.01" class="input-2"  name="paySafeMoney" value="${apiRsp.caseCloseReport.paySafeMoney}"> 元；
                        </td>
					</tr>
				</tbody>
			</table>
			<table id="table-nei">
				<tbody>
					<tr class="back-color-1 border-1 padding-1 height-1">
						<td colspan="4" class="table-title padding-2">
							结案费用汇总情况
						</td>
					</tr>
					<tr class="height-1">
						<td class="back-color-2 border-2 row-25 padding-2">约定收费方式</td>
						<td colspan="3" class="border-2 row-26 padding-2">
                            <input type="radio" onchange="onblurCharge()" name="chargeType" value="1"  <c:if test="${apiRsp.caseCloseReport.chargeType == 1}">checked = "checked"</c:if>/>固定收费金额<input type="number" step="0.01" class="input-2" id="fixedChargeMoney" name="fixedChargeMoney" onblur="onblurCharge()" value="${apiRsp.caseCloseReport.fixedChargeMoney}">元；<br>
                            <input type="radio" onchange="onblurCharge()" name="chargeType" value="2"  <c:if test="${apiRsp.caseCloseReport.chargeType == 2}">checked = "checked"</c:if>/>比例收费，比例基数<input type="number" step="0.01" class="input-2" id="proportionChargeMoney" name="proportionChargeMoney" onblur="onblurCharge()" value="${apiRsp.caseCloseReport.proportionChargeMoney}">元，收费比例<input type="number" step="0" min="0" max="100" class="input-2" id="proportionChargeRate" name="proportionChargeRate" onblur="onblurCharge()" value="${apiRsp.caseCloseReport.proportionChargeRate}">%<br>
                            <input type="radio" onchange="onblurCharge()" name="chargeType" value="3"  <c:if test="${apiRsp.caseCloseReport.chargeType == 3}">checked = "checked"</c:if>/>固定收费金额<input type="number" step="0.01" class="input-2" id="blendFixedChargeMoney" name="blendFixedChargeMoney" onblur="onblurCharge()" value="${apiRsp.caseCloseReport.blendFixedChargeMoney}">元；比例收费，比例基数<input type="number" step="0.01" class="input-2" id="blendProportionChargeMoney" name="blendProportionChargeMoney" onblur="onblurCharge()" value="${apiRsp.caseCloseReport.blendProportionChargeMoney}">元，收费比例<input type="number" step="0" min="0" max="100" class="input-2" id="blendProportionChargeRate" name="blendProportionChargeRate" onblur="onblurCharge()" value="${apiRsp.caseCloseReport.blendProportionChargeRate}">%
                        </td>
					</tr>
					<tr class="height-1">
						<td class="back-color-2 border-2 row-25 padding-2">收费相关约定注释</td>
						<td colspan="3" class="border-2 row-26 padding-2">
							<input type="text" class="input-1" name="chargeDesc" value="${apiRsp.caseCloseReport.chargeDesc}"/>
						</td>
					</tr>
					<tr class="height-1">
						<%--<td class="back-color-2 border-2 row-25 padding-2">计入收费赔偿总金额</td>--%>
						<%--<td class="border-2 row-26 padding-2">--%>
							<%--<input type="number" step="0.01" class="input-2" name="includedTotalMoney" value="${apiRsp.caseCloseReport.includedTotalMoney}"/>元--%>
						<%--</td>--%>
						<td class="back-color-2 border-2 row-25 padding-2">应收取服务费总金额</td>
						<td class="border-2 row-26 padding-2">
							<input type="number" step="0.01" class="input-2" onblur="calcAmount()" id="shouldTotalMoney" name="shouldTotalMoney" value="${apiRsp.caseCloseReport.shouldTotalMoney}"/>元
						</td>
                        <td class="back-color-2 border-2 row-25 padding-2">预收服务费总金额</td>
                        <td class="border-2 row-26 padding-2">
                            <input type="number" step="0.01" class="input-2" onblur="calcAmount()" id="preMoney" name="preMoney" value="${apiRsp.caseCloseReport.preMoney}">元
                        </td>
					</tr>
					<tr class="height-1">
						<td class="back-color-2 border-2 row-25 padding-2">剩余应收服务费总金额</td>
						<td class="border-2 row-26 padding-2">
							<input type="number" step="0.01" class="input-2" id="surplusTotalMoney" name="surplusTotalMoney" value="${apiRsp.caseCloseReport.surplusTotalMoney}">元
						</td>
                        <td class="back-color-2 border-2 row-25 padding-2">实际收取服务费总金额</td>
                        <td class="border-2 row-26 padding-2">
                            <input type="number" step="0.01" class="input-2" id="actualTotalMoney" name="actualTotalMoney" value="${apiRsp.caseCloseReport.actualTotalMoney}">元
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 row-25 padding-2">贷款通道费</td>
                        <td class="row-26 padding-2">
                            <input type="number" step="0.01"  min="0" class="input-2" name="loanChannelRate" value="${apiRsp.caseCloseReport.loanChannelRate}">%
                            <input type="number" step="0.01"  min="0" class="input-2" name="loanChannelMoney" value="${apiRsp.caseCloseReport.loanChannelMoney}">元
                        </td>
					</tr>
					<tr class="height-1">
						<td class="back-color-2 border-2 row-25 padding-2">贷款金额</td>
						<td class="border-2 row-26 padding-2">
							<input type="number" step="0.01" required="required" min="0" class="input-2" name="loanMoney" value="${apiRsp.caseCloseReport.loanMoney}">元
						</td>
                        <td class="back-color-2 border-2 row-25 padding-2">是否扣费放贷</td>
                        <td class="border-2 row-26 padding-2">
                            <input type="radio" value="1" name="isDeductedLoan" onclick="setValueDeductedLoanMoney(1)" <c:if test="${apiRsp.caseCloseReport.isDeductedLoan == 0}">disabled</c:if> <c:if test="${apiRsp.caseCloseReport.isDeductedLoan == 1}">checked = "checked"</c:if>>是（扣费金额<input type="number" step="0.01" class="input-2" id="deductedLoanMoney" name="deductedLoanMoney" value="${apiRsp.caseCloseReport.deductedLoanMoney}">元）、
                            <input type="radio" value="0" name="isDeductedLoan" onclick="setValueDeductedLoanMoney(2)" <c:if test="${apiRsp.caseCloseReport.isDeductedLoan == 0}">disabled</c:if> <c:if test="${apiRsp.caseCloseReport.isDeductedLoan == 0}">checked = "checked"</c:if>>否
                        </td>
						<%--<td class="back-color-2 border-2 row-25 padding-2">是否预收基本费</td>--%>
						<%--<td class="border-2 row-26 padding-2">--%>
                            <%--<input type="radio" value="1" name="isPre" <c:if test="${apiRsp.caseCloseReport.isPre == 1}">checked = "checked"</c:if>>是（预收金额<input type="number" step="0.01" class="input-2" name="preMoney" value="${apiRsp.caseCloseReport.preMoney}">元）、 <input type="radio" value="0" name="isPre" <c:if test="${apiRsp.caseCloseReport.isPre == 0}">checked = "checked"</c:if>>否--%>
                        <%--</td>--%>
					</tr>
					<tr class="height-1">
						<td class="back-color-2 border-2 row-25 padding-2">贷款方式</td>
						<td class="row-26 padding-2">
							<input type="radio" value="1" name="loanType" <c:if test="${apiRsp.caseCloseReport.loanType == 1}">checked = "checked"</c:if> />公司垫付
						</td>
						<td class="row-26 padding-2">
							<input type="radio" value="2" name="loanType" <c:if test="${apiRsp.caseCloseReport.loanType == 2}">checked = "checked"</c:if> />苏宁垫付
						</td>
                        <td class="row-26 padding-2">
                            <input type="radio" value="3" name="loanType" <c:if test="${apiRsp.caseCloseReport.loanType == 3}">checked = "checked"</c:if> />其他垫付
                        </td>
					</tr>
					<tr class="height-1">
						<td class="back-color-2 border-2 row-25 padding-2">实际贷款周期</td>
						<td class="border-2 row-26 padding-2">
							<input type="number" step="0" class="input-2" name="loanCycle" value="${apiRsp.caseCloseReport.loanCycle}">天
						</td>
                        <td class="back-color-2 border-2 row-25 padding-2">保证保险保费</td>
                        <td class="border-2 row-26 padding-2">
                            <input type="number" step="0.01" class="input-2" name="insuranceMoney" value="${apiRsp.caseCloseReport.insuranceMoney}">元
                        </td>
					</tr>
                    <tr class="height-1">
                        <td rowspan="4" class="back-color-2 border-2 row-25 padding-2">律师费</td>
                        <td rowspan="4" class="border-2 row-26 padding-2">
                            <input type="number" step="0.01" class="input-2" id="lawyerMoney" onblur="onblurByReportMoney()" name="lawyerMoney" value="${apiRsp.caseCloseReport.lawyerMoney}">元
                        </td>
                        <td class="back-color-2 border-2 padding-2 row-10">
                            账户名
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="lawyerMoneyAccName" value="${apiRsp.caseCloseReport.lawyerMoneyAccName}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 padding-2 row-10">
                            开户行
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="lawyerMoneyBankName" value="${apiRsp.caseCloseReport.lawyerMoneyBankName}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 padding-2 row-10">
                            账户号
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="lawyerMoneyCardNo" value="${apiRsp.caseCloseReport.lawyerMoneyCardNo}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 padding-2 row-10">
                            备注
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="lawyerMoneyRemarks" value="${apiRsp.caseCloseReport.lawyerMoneyRemarks}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td rowspan="4" class="back-color-2 border-2 row-25 padding-2">鉴定费</td>
                        <td rowspan="4" class="border-2 row-26 padding-2">
                            <input type="number" step="0.01" class="input-2" id="appraisalMoney" onblur="onblurByReportMoney()" name="appraisalMoney" value="${apiRsp.caseCloseReport.appraisalMoney}">元
                        </td>
                        <td class="back-color-2 border-2 padding-2 row-10">
                            账户名
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="appraisalMoneyAccName" value="${apiRsp.caseCloseReport.appraisalMoneyAccName}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 padding-2 row-10">
                            开户行
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="appraisalMoneyBankName" value="${apiRsp.caseCloseReport.appraisalMoneyBankName}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 padding-2 row-10">
                            账户号
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="appraisalMoneyCardNo" value="${apiRsp.caseCloseReport.appraisalMoneyCardNo}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 padding-2 row-10">
                            备注
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="appraisalMoneyRemarks" value="${apiRsp.caseCloseReport.appraisalMoneyRemarks}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td rowspan="4" class="back-color-2 border-2 row-25 padding-2">诉讼费</td>
                        <td rowspan="4" class="border-2 row-26 padding-2">
                            <input type="number" step="0.01" class="input-2" id="litigateMoney" onblur="onblurByReportMoney()" name="litigateMoney" value="${apiRsp.caseCloseReport.litigateMoney}">元
                        </td>
                        <td class="back-color-2 border-2 padding-2 row-10">
                            账户名
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="litigateMoneyAccName" value="${apiRsp.caseCloseReport.litigateMoneyAccName}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 padding-2 row-10">
                            开户行
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="litigateMoneyBankName" value="${apiRsp.caseCloseReport.litigateMoneyBankName}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 padding-2 row-10">
                            账户号
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="litigateMoneyCardNo" value="${apiRsp.caseCloseReport.litigateMoneyCardNo}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 padding-2 row-10">
                            备注
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="litigateMoneyRemarks" value="${apiRsp.caseCloseReport.litigateMoneyRemarks}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td rowspan="4" class="back-color-2 border-2 row-25 padding-2">索赔费用</td>
                        <td rowspan="4" class="border-2 row-26 padding-2">
                            <input type="number" step="0.01" class="input-2" id="claimMoney" onblur="onblurByReportMoney()" name="claimMoney" value="${apiRsp.caseCloseReport.claimMoney}">元
                        </td>
                        <td class="back-color-2 border-2 padding-2 row-10">
                            账户名
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="claimMoneyAccName" value="${apiRsp.caseCloseReport.claimMoneyAccName}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 padding-2 row-10">
                            开户行
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="claimMoneyBankName" value="${apiRsp.caseCloseReport.claimMoneyBankName}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 padding-2 row-10">
                            账户号
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="claimMoneyCardNo" value="${apiRsp.caseCloseReport.claimMoneyCardNo}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 padding-2 row-10">
                            备注
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="claimMoneyRemarks" value="${apiRsp.caseCloseReport.claimMoneyRemarks}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td rowspan="4" class="back-color-2 border-2 row-25 padding-2">其他费用</td>
                        <td rowspan="4" class="border-2 row-26 padding-2">
                            <input type="number" step="0.01" class="input-2" id="otherMoney" onblur="onblurByReportMoney()" name="otherMoney" value="${apiRsp.caseCloseReport.otherMoney}">元
                        </td>
                        <td class="back-color-2 border-2 padding-2 row-10">
                            账户名
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="otherMoneyAccName" value="${apiRsp.caseCloseReport.otherMoneyAccName}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 padding-2 row-10">
                            开户行
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="otherMoneyBankName" value="${apiRsp.caseCloseReport.otherMoneyBankName}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 padding-2 row-10">
                            账户号
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="otherMoneyCardNo" value="${apiRsp.caseCloseReport.otherMoneyCardNo}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 padding-2 row-10">
                            备注
                        </td>
                        <td colspan="3" class="border-2 padding-2">
                            <input type="text" class="input-5" name="otherMoneyRemarks" value="${apiRsp.caseCloseReport.otherMoneyRemarks}"/>
                        </td>
                    </tr>
                    <tr class="height-1">
                        <td class="back-color-2 border-2 row-25 padding-2">结案时需扣费</td>
                        <td colspan="3" class="border-2 row-26 padding-2">
                            <input type="number" step="0.01" class="input-2" id="closedReportDeMoney" name="closedReportDeMoney" value="${apiRsp.caseCloseReport.closedReportDeMoney}">元
                        </td>
                        <%--<td class="back-color-2 border-2 row-25 padding-2">保全费</td>--%>
                        <%--<td class="border-2 row-26 padding-2">--%>
                        <%--<input type="number" step="0.01" class="input-2" name="preserveMoney" value="${apiRsp.caseCloseReport.preserveMoney}">元--%>
                        <%--</td>--%>
                    </tr>
					<tr class="height-1">
						<td class="back-color-2 border-2 row-25 padding-2">其他支出费用备注：</td>
						<td colspan="3" class="border-2 row-26 padding-2">
							<textarea name="otherDesc">${apiRsp.caseCloseReport.otherDesc}</textarea>
						</td>
					</tr>
					<%--<tr class="height-1">--%>
						<%--<td class="back-color-2 border-2 row-25 padding-2">费用相关备注说明</td>--%>
						<%--<td colspan="3" class="border-2 row-26 padding-2">--%>
							<%--<textarea name="moneyDesc">${apiRsp.caseCloseReport.moneyDesc}</textarea>--%>
						<%--</td>--%>
					<%--</tr>--%>
				</tbody>
			</table>
			<table id="table-nei">
				<tbody>
					<tr class="height-1">
						<td class="back-color-2 border-2 padding-2 row-10">财务部签字确认</td>
						<td class="border-2 padding-2 row-15">
							<input type="text" class="input-1"  name="financeSign" value="${apiRsp.caseCloseReport.financeSign}" />
						</td>
						<td class="back-color-2 border-2 padding-2 row-10">日期</td>
						<td class="border-2 padding-2 row-15">
							<input type="text" class="input-1"  name="financeSignTime" value="<fmt:formatDate value="${apiRsp.caseCloseReport.financeSignTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						</td>
						<td class="back-color-2 border-2 padding-2 row-10">风控部签字确认：</td>
						<td class="border-2 padding-2 row-15">
							<input type="text" class="input-1" name="riskSign" value="${apiRsp.caseCloseReport.riskSign}" />
						</td>
						<td class="back-color-2 border-2 padding-2 row-10">日期</td>
						<td class="border-2 padding-2 row-15">
							<input type="text" class="input-1"  name="riskSignTime" value="<fmt:formatDate value="${apiRsp.caseCloseReport.riskSignTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						</td>
					</tr>
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
            function refreshSave(){
                location.reload();
            }
            function blurUpdCloseObjReport(reportId,id,type,obj,caseId,caseNo){
                if("${op}" == 'view'){
                    return;
                }
                ajaxSubmit("${ctx}/case/report/updCaseCloseObjReport?reportId="+reportId+"&id=" + id + "&type=" + type + "&value=" + obj.value+"&caseId="+caseId+"&caseNo="+caseNo,null,function(v,e,p){

                })
            }

            function onblurCharge(op){
                if("${op}" == 'view'){
                    return;
                }
                var a = $("input[name='chargeType']:checked").val();
                if(a == 1){
                    if(!$('#fixedChargeMoney').val()){
                        $('#fixedChargeMoney').val(0);
                    }
                    var fixedChargeMoney = $('#fixedChargeMoney').val();
                    $('#shouldTotalMoney').val(fixedChargeMoney);
                }else if(a == 2){
                    if(!$('#proportionChargeMoney').val()){
                        $('#proportionChargeMoney').val(0);
                    }
                    var proportionChargeMoney = $('#proportionChargeMoney').val();
                    if(!$('#proportionChargeRate').val()){
                        $('#proportionChargeRate').val(0);
                    }
                    var proportionChargeRate = $('#proportionChargeRate').val();
                    var value = (proportionChargeMoney * proportionChargeRate / 100).toFixed(2);
                    $('#shouldTotalMoney').val(value);
                }else if(a == 3){
                    if(!$('#blendFixedChargeMoney').val()){
                        $('#blendFixedChargeMoney').val(0);
                    }
                    var blendFixedChargeMoney = $('#blendFixedChargeMoney').val();
                    if(!$('#blendProportionChargeMoney').val()){
                        $('#blendProportionChargeMoney').val(0);
                    }
                    var blendProportionChargeMoney = $('#blendProportionChargeMoney').val();
                    if(!$('#blendProportionChargeRate').val()){
                        $('#blendProportionChargeRate').val(0);
                    }
                    var blendProportionChargeRate = $('#blendProportionChargeRate').val();
                    var value = (Number(blendFixedChargeMoney) + Number(blendProportionChargeMoney * blendProportionChargeRate /100)).toFixed(2);
                    $('#shouldTotalMoney').val(value);
                }
                calcAmount();
            }

            function onblurByReportMoney(){
                if("${op}" == 'view'){
                    return;
                }
                var lawyerMoney = Number($('#lawyerMoney').val() == false ? 0 : $('#lawyerMoney').val());
                var appraisalMoney = Number($('#appraisalMoney').val() == false ? 0 : $('#appraisalMoney').val());
                var litigateMoney = Number($('#litigateMoney').val() == false ? 0 : $('#litigateMoney').val());
                var claimMoney = Number($('#claimMoney').val() == false ? 0 : $('#claimMoney').val());
                var otherMoney = Number($('#otherMoney').val() == false ? 0 : $('#otherMoney').val());
                $('#closedReportDeMoney').val((lawyerMoney + appraisalMoney + litigateMoney + claimMoney + otherMoney).toFixed(2));
            }

            /**
            *事故责任比例 光标离开 调用保存方法
            * @param obj
             */
            function compensateRageBlur(){
                if(${op == 'view'}){
                    return;
                }
                ajaxFormSubmit($("#editForm"),function(v,e,p){location.reload();},null,null,function(v,e,p){
                    location.reload();
                });
            }

            function calcAmount(){
                if(${op == 'view'}){
                    return;
                }
                var shouldTotalMoney = $('#shouldTotalMoney').val();
                var preMoney = $('#preMoney').val();
                $('#surplusTotalMoney').val((Number(shouldTotalMoney) - Number(preMoney)).toFixed(2));
            }

            function setValueDeductedLoanMoney(type){
                if(${op == 'view'}){
                    return;
                }
                if(type == 1){
                    var value = Number($('#preMoney').val() == false ? 0 : $('#preMoney').val());
                    $('#deductedLoanMoney').val(value.toFixed(2));
                }else{
                    $('#deductedLoanMoney').val(0);
                }
            }
        </script>
	</body>

</html>