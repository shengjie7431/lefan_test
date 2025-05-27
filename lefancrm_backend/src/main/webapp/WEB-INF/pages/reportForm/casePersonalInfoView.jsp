<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
    <script>

    </script>
    <style>
        .table-title{
            font-size: 14px;
            font-weight: bold;
            line-height: 40px;
        }
        table.spec-info tr td:nth-of-type(2n + 1) {
            width: 20%;
        }
        table.spec-info tr td:nth-of-type(2n) {
            width: 13%;
        }
    </style>
</head>
<body>

<div class="main">
    <input type="hidden" name="id" value="${casePersonalInfo.id}">
    <div style="font-size: 16px; text-align:center;font-weight: bold;">
        ${casePersonalInfo.orgName}&nbsp;&nbsp;|&nbsp;&nbsp;
        <c:if test="${casePersonalInfo.caseType ==1}">
            垫付案件
        </c:if>
        <c:if test="${casePersonalInfo.caseType ==2}">
            代理案件
        </c:if> &nbsp;&nbsp;${casePersonalInfo.caseNo}
    </div>
    <div class="main-boy">
        <div>
            <div class="table-title">客户信息</div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>姓名</td>
                    <td>${casePersonalInfo.userName}</td>
                    <td>就诊医院</td>
                    <td>${casePersonalInfo.visitHospital}</td>
                    <td>出险地</td>
                    <td>${casePersonalInfo.dangerAddress}</td>
                </tr>
                <tr>
                    <td>身份证号</td>
                    <td>${casePersonalInfo.idCard}</td>
                    <td>床位号</td>
                    <td>${casePersonalInfo.bedNumber}</td>
                    <td>出险时间</td>
                    <td><fmt:formatDate value="${casePersonalInfo.dangerTime}" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td>电话</td>
                    <td>${casePersonalInfo.userTel}</td>
                    <td>伤情描述</td>
                    <td colspan="3">${casePersonalInfo.injuryDesc}</td>
                </tr>
                </tbody>
            </table>

            <div class="table-title">CC信息</div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td style="width: 20%">录入人</td>
                    <td style="width: 13%">${casePersonalInfo.salesmanName}</td>
                    <td style="width: 20%">录入时间</td>
                    <td style="width: 47%"><fmt:formatDate value="${casePersonalInfo.intoTime}" pattern="yyyy-MM-dd"/></td>
                </tr>
                </tbody>
            </table>

            <div class="table-title">品控客服审核</div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>是否是目标客户</td>
                    <td>
                        <c:if test="${casePersonalInfo.isTarget ==0 || casePersonalInfo.isTarget ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isTarget ==1}">
                            是
                        </c:if>
                    </td>
                    <td>业务员是否接触客户</td>
                    <td>
                        <c:if test="${casePersonalInfo.isContact ==0 || casePersonalInfo.isContact ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isContact ==1}">
                            是
                        </c:if>
                    </td>
                    <td>是否留下业务员联系方式</td>
                    <td>
                        <c:if test="${casePersonalInfo.isKeepTel ==0 || casePersonalInfo.isKeepTel == null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isKeepTel ==1}">
                            是
                        </c:if>
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="table-title">风控评估</div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td style="width: 20%">伤残等级</td>
                    <td style="width: 80%">${casePersonalInfo.disabilityGrade}</td>
                </tr>
                </tbody>
            </table>

            <div class="table-title">涉残案件跟进状态</div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>潜在客户</td>
                    <td>
                        <c:if test="${casePersonalInfo.isPotential ==0 || casePersonalInfo.isPotential ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isPotential ==1}">
                            是
                        </c:if>
                    </td>
                    <td>意向客户</td>
                    <td>
                        <c:if test="${casePersonalInfo.isIntention ==0 || casePersonalInfo.isIntention ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isIntention ==1}">
                            是
                        </c:if>
                    </td>
                    <td>签约客户</td>
                    <td>
                        <c:if test="${casePersonalInfo.isSign ==0 || casePersonalInfo.isSign ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isSign ==1}">
                            是
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>超时案件</td>
                    <td>
                        <c:if test="${casePersonalInfo.isOvertime ==0 || casePersonalInfo.isOvertime ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isOvertime ==1}">
                            是
                        </c:if>
                    </td>
                    <td>超时天数</td>
                    <td colspan="3">${casePersonalInfo.timeoutDay}</td>
                </tr>
                </tbody>
            </table>

            <div class="table-title">签约材料审核</div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>服务协议是否规范</td>
                    <td>
                        <c:if test="${casePersonalInfo.serviceAgreement ==0 || casePersonalInfo.serviceAgreement ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.serviceAgreement ==1}">
                            是
                        </c:if>
                    </td>
                    <td>代办协议是否规范</td>
                    <td>
                        <c:if test="${casePersonalInfo.agentAgreement ==0 || casePersonalInfo.agentAgreement ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.agentAgreement ==1}">
                            是
                        </c:if>
                    </td>
                    <td>委托协议是否规范</td>
                    <td>
                        <c:if test="${casePersonalInfo.entrustAgreement ==0 || casePersonalInfo.entrustAgreement ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.entrustAgreement ==1}">
                            是
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>信息登记表填写是否规范</td>
                    <td>
                        <c:if test="${casePersonalInfo.isRegistration ==0 || casePersonalInfo.isRegistration ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isRegistration ==1}">
                            是
                        </c:if>
                    </td>
                    <td>审核是否通过</td>
                    <td colspan="3">
                        <c:if test="${casePersonalInfo.isPassed ==0 || casePersonalInfo.isPassed ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isPassed ==1}">
                            是
                        </c:if>
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="table-title">案件费用</div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>预估签约服务费</td>
                    <td>${casePersonalInfo.estimateServiceMoney}</td>
                    <td>预收基本费</td>
                    <td>${casePersonalInfo.advanceBasicMoney}</td>
                    <td>收费方式</td>
                    <td>
                        <c:if test="${casePersonalInfo.chargingType ==1}">
                            现金
                        </c:if>
                        <c:if test="${casePersonalInfo.chargingType ==2}">
                            转账
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>审核服务费</td>
                    <td>${casePersonalInfo.auditServiceMoney}</td>
                    <td>审核意见</td>
                    <td>${casePersonalInfo.auditDesc}</td>
                    <td>应收服务费金额</td>
                    <td>${casePersonalInfo.receivableServiceMoney}</td>
                </tr>
                <tr>
                    <td>差异原因</td>
                    <td>${casePersonalInfo.differenceCause}</td>
                    <td>到账金额</td>
                    <td>${casePersonalInfo.receivedMoney}</td>
                    <td>到账时间</td>
                    <td><fmt:formatDate value="${casePersonalInfo.receivedTime}" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td>佣金是否结算</td>
                    <td colspan="5">
                        <c:if test="${casePersonalInfo.isSettlementCommission ==0 || casePersonalInfo.isSettlementCommission ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isSettlementCommission ==1}">
                            是
                        </c:if>
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="table-title">会销信息</div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>电话邀约是否成功</td>
                    <td>
                        <c:if test="${casePersonalInfo.isCallInvitation ==0 || casePersonalInfo.isCallInvitation ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isCallInvitation ==1}">
                            是
                        </c:if>
                    </td>
                    <td>现场邀约是否成功</td>
                    <td>
                        <c:if test="${casePersonalInfo.isSiteInvitation ==0 || casePersonalInfo.isSiteInvitation ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isSiteInvitation ==1}">
                            是
                        </c:if>
                    </td>
                    <td>客户信息表是否提供</td>
                    <td>
                        <c:if test="${casePersonalInfo.isInformationSubmit ==0  || casePersonalInfo.isInformationSubmit ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isInformationSubmit ==1}">
                            是
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>邀会码是否发送</td>
                    <td>
                        <c:if test="${casePersonalInfo.isSendInvitationCode ==0 || casePersonalInfo.isSendInvitationCode ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isSendInvitationCode ==1}">
                            是
                        </c:if>
                    </td>
                    <td>预约参会时间</td>
                    <td><fmt:formatDate value="${casePersonalInfo.bespokeMeetingTime}" pattern="yyyy-MM-dd"/></td>
                    <td>跟进CC</td>
                    <td>${casePersonalInfo.followCc}</td>
                </tr>
                <tr>
                    <td>是否参会</td>
                    <td>
                        <c:if test="${casePersonalInfo.isMeeting ==0 || casePersonalInfo.isMeeting ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isMeeting ==1}">
                            是
                        </c:if>
                    </td>
                    <td>签约是否成功</td>
                    <td>
                        <c:if test="${casePersonalInfo.isSaleSign ==0 || casePersonalInfo.isSaleSign ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isSaleSign ==1}">
                            是
                        </c:if>
                    </td>
                    <td>服务协议是否规范</td>
                    <td>
                        <c:if test="${casePersonalInfo.meetingServiceAgreement ==0  || casePersonalInfo.meetingServiceAgreement ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.meetingServiceAgreement ==1}">
                            是
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>代办协议是否规范</td>
                    <td>
                        <c:if test="${casePersonalInfo.meetingAgentAgreement ==0  || casePersonalInfo.meetingAgentAgreement ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.meetingAgentAgreement ==1}">
                            是
                        </c:if>
                    </td>
                    <td>委托协议是否规范</td>
                    <td>
                        <c:if test="${casePersonalInfo.meetingEntrustAgreement ==0 || casePersonalInfo.meetingEntrustAgreement ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.meetingEntrustAgreement ==1}">
                            是
                        </c:if>
                    </td>
                    <td>援助申请协议是否规范</td>
                    <td>
                        <c:if test="${casePersonalInfo.assistanceApplyAgreement ==0 || casePersonalInfo.assistanceApplyAgreement ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.assistanceApplyAgreement ==1}">
                            是
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>保密协议是否规范</td>
                    <td>
                        <c:if test="${casePersonalInfo.meetingSecrecyAgreement ==0 || casePersonalInfo.meetingSecrecyAgreement ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.meetingSecrecyAgreement ==1}">
                            是
                        </c:if>
                    </td>
                    <td>服务费是否支付</td>
                    <td>
                        <c:if test="${casePersonalInfo.isMeetingServiceMoney ==0 || casePersonalInfo.isMeetingServiceMoney ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isMeetingServiceMoney ==1}">
                            是
                        </c:if>
                    </td>
                    <td>签约手续是否完成</td>
                    <td>
                        <c:if test="${casePersonalInfo.isMeetingSign ==0 || casePersonalInfo.isMeetingSign ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isMeetingSign ==1}">
                            是
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>会销签约服务费是否到账</td>
                    <td>
                        <c:if test="${casePersonalInfo.isMeetingReceived ==0 || casePersonalInfo.isMeetingReceived ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isMeetingReceived ==1}">
                            是
                        </c:if>
                    </td>
                    <td>会销佣金是否结算</td>
                    <td colspan="3">
                        <c:if test="${casePersonalInfo.isMeetingSettCommission ==0 || casePersonalInfo.isMeetingSettCommission ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isMeetingSettCommission ==1}">
                            是
                        </c:if>
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="table-title">金融信息状态</div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>是否申请金融</td>
                    <td>
                        <c:if test="${casePersonalInfo.isApplyFinance ==0 || casePersonalInfo.isApplyFinance ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isApplyFinance ==1}">
                            是
                        </c:if>
                    </td>
                    <td>申请贷款金额</td>
                    <td>${casePersonalInfo.applyLoanMoney}</td>
                    <td>申请垫付时间</td>
                    <td><fmt:formatDate value="${casePersonalInfo.applyLoanTime}" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td>垫付资料是否齐全</td>
                    <td>
                        <c:if test="${casePersonalInfo.isSucLoanFile ==0 || casePersonalInfo.isSucLoanFile ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isSucLoanFile ==1}">
                            是
                        </c:if>
                    </td>
                    <td>审核贷款金额</td>
                    <td>${casePersonalInfo.checkLoanMoney}</td>
                    <td>实际放款金额</td>
                    <td>${casePersonalInfo.loanMoney}</td>
                </tr>
                <tr>
                    <td>放款时间</td>
                    <td><fmt:formatDate value="${casePersonalInfo.loanTime}" pattern="yyyy-MM-dd"/></td>
                    <td>放款资方</td>
                    <td>
                        <c:if test="${casePersonalInfo.loanCapitalName ==1 || casePersonalInfo.loanCapitalName ==null}">
                            苏宁
                        </c:if>
                        <c:if test="${casePersonalInfo.loanCapitalName ==2}">
                            乐凡
                        </c:if>
                    </td>
                    <td>预扣通道费</td>
                    <td>${casePersonalInfo.advanceChannelMoney}</td>
                </tr>
                <tr>
                    <td>预收利息</td>
                    <td>${casePersonalInfo.advanceInterestMoney}</td>
                    <td>是否公司免费垫付</td>
                    <td>
                        <c:if test="${casePersonalInfo.isFreeLoan ==0 || casePersonalInfo.isFreeLoan ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isFreeLoan ==1}">
                            是
                        </c:if>
                    </td>
                    <td>是否公司转接垫付</td>
                    <td>
                        <c:if test="${casePersonalInfo.isTransferLoan ==0 || casePersonalInfo.isTransferLoan ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isTransferLoan ==1}">
                            是
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>是否第三方公司垫付金融</td>
                    <td>
                        <c:if test="${casePersonalInfo.isThirdLoan ==0 || casePersonalInfo.isThirdLoan ==null}">
                            否
                        </c:if>
                        <c:if test="${casePersonalInfo.isThirdLoan ==1}">
                            是
                        </c:if>
                    </td>
                    <td>本金</td>
                    <td>${casePersonalInfo.capitalMoney}</td>
                    <td>利息</td>
                    <td>${casePersonalInfo.interestMoney}</td>

                </tr>
                <tr>
                    <td>通道费</td>
                    <td>${casePersonalInfo.channelMoney}</td>
                    <td>贷款回款金额</td>
                    <td>${casePersonalInfo.loanReturnMoney}</td>
                    <td>贷款回款时间</td>
                    <td><fmt:formatDate value="${casePersonalInfo.loanReturnTime}" pattern="yyyy-MM-dd"/></td>
                </tr>
                </tbody>
            </table>

        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </div>
</div>

<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script type="text/javascript">
    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

    /**
     * 关闭dialog
     */
    function closeDialog(){
        var closeBtn = $("#diglog_close_btn");
        if(closeBtn.size() == 0){
            closeBtn = $("#diglog_close_btn",window.parent.document);
        }
        closeBtn.click();
    }

</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>