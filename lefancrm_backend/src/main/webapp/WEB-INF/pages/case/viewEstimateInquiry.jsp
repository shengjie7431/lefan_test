<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
</head>
<body>
<div class="container">
        <div class="form-group">
            <table class="table">
                <tbody>
                <input type="hidden" id="id" name="id" value="${id}">
                <input type="hidden" id="btnCode" name="btnCode" value="${btnCode}">
                <c:if test="${payEstimateInquiry == null}">
                    <span style="color: red;">无报价信息</span>
                </c:if>
                <!-- 贷款 -->
                <c:if test="${payEstimateInquiry.caseType == 1}">
                    <tr>
                        <th width="20%" class="active">医疗费金额</th>
                        <td width="80%">
                                ${payEstimateInquiry.medicalFee}
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">理赔款金额</th>
                        <td width="80%">
                                ${payEstimateInquiry.payableFee}
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">可贷款金额金额</th>
                        <td width="80%">
                                ${payEstimateInquiry.freePrePayFee}
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">代理服务费</th>
                        <td width="80%">
                                ${payEstimateInquiry.agentServiceFee}
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">收费比例</th>
                        <td width="80%">
                                ${payEstimateInquiry.serviceRate * 100}%
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">可贴息贷款金额</th>
                        <td width="80%">
                                ${payEstimateInquiry.discountLoanFee}
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">申请贴息贷款金额</th>
                        <td width="80%">
                                ${payEstimateInquiry.applyDiscountLoanFee}
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">应扣费金额</th>
                        <td width="80%">
                                ${payEstimateInquiry.shouldDeFee}
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">实际扣费金额</th>
                        <td width="80%">
                                ${payEstimateInquiry.realDeFee}
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">是否还需贷款</th>
                        <td width="80%">
                                ${payEstimateInquiry.isNeedLoan == 1 ? '是' : '否'}
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">还需贷款金额</th>
                        <td width="80%">
                                ${payEstimateInquiry.stillNeedFee}
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">通道费比例</th>
                        <td width="80%">
                                ${payEstimateInquiry.loanRate * 100}%
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">通道费</th>
                        <td width="80%">
                                ${payEstimateInquiry.loanFee}
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">贷款总金额</th>
                        <td width="80%">
                                ${payEstimateInquiry.totalLoanFee}
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">收费总金额</th>
                        <td width="80%">
                                ${payEstimateInquiry.totalDeFee}
                        </td>
                    </tr>
                </c:if>
                <c:if test="${payEstimateInquiry.caseType == 2}">
                    <c:if test="${payEstimateInquiry.isWorkInjury}">
                        <tr>
                            <th width="20%" class="active">代理服务费</th>
                            <td width="80%">
                                    ${payEstimateInquiry.agentServiceFee}
                            </td>
                        </tr>
                        <tr>
                            <th width="20%" class="active">
                                <c:if test="${payEstimateInquiry.advcanceType == 2}">
                                预收服务费
                                </c:if>
                                <c:if test="${payEstimateInquiry.advcanceType != 2}">
                                预收定金
                                </c:if>
                            <td width="80%">
                                    ${payEstimateInquiry.realDeFee}
                            </td>
                            </th>
                        </tr>
                        <tr>
                            <th width="20%" class="active">收费总金额</th>
                            <td width="80%">
                                    ${payEstimateInquiry.totalDeFee}
                            </td>
                        </tr>
                        <tr>
                            <th width="20%" class="active">收费方式</th>
                            <td width="80%">
                                <c:if test="${payEstimateInquiry.chargeType == 0}">现金</c:if>
                                <c:if test="${payEstimateInquiry.chargeType != 0}">转账</c:if>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!payEstimateInquiry.isWorkInjury}">
                        <tr>
                            <th width="20%" class="active">医疗费金额</th>
                            <td width="80%">
                                    ${payEstimateInquiry.medicalFee}
                            </td>
                        </tr>
                        <tr>
                            <th width="20%" class="active">理赔款金额</th>
                            <td width="80%">
                                    ${payEstimateInquiry.payableFee}
                            </td>
                        </tr>
                        <tr>
                            <th width="20%" class="active">代理服务费</th>
                            <td width="80%">
                                    ${payEstimateInquiry.agentServiceFee}
                            </td>
                        </tr>
                        <tr>
                            <th width="20%" class="active">收费比例</th>
                            <td width="80%">
                                    ${payEstimateInquiry.serviceRate * 100}%
                            </td>
                        </tr>
                        <tr>
                            <th width="20%" class="active">应扣费金额</th>
                            <td width="80%">
                                    ${payEstimateInquiry.shouldDeFee}
                            </td>
                        </tr>
                        <tr>
                            <th width="20%" class="active">实际扣费金额</th>
                            <td width="80%">
                                    ${payEstimateInquiry.realDeFee}
                            </td>
                        </tr>
                        <tr>
                            <th width="20%" class="active">
                                <c:if test="${payEstimateInquiry.advcanceType == 2}">
                                预收服务费
                                </c:if>
                                <c:if test="${payEstimateInquiry.advcanceType != 2}">
                                预收定金
                                </c:if>
                            <td width="80%">
                                    ${payEstimateInquiry.realDeFee}
                            </td>
                            </th>
                        </tr>
                        <tr>
                            <th width="20%" class="active">收费总金额</th>
                            <td width="80%">
                                    ${payEstimateInquiry.totalDeFee}
                            </td>
                        </tr>
                        <tr>
                            <th width="20%" class="active">收费方式</th>
                            <td width="80%">
                                <c:if test="${payEstimateInquiry.chargeType == 0}">现金</c:if>
                                <c:if test="${payEstimateInquiry.chargeType != 0}">转账</c:if>
                            </td>
                        </tr>
                    </c:if>
                </c:if>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
        </div>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>


<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script type="text/javascript">

</script>
</body>
</html>