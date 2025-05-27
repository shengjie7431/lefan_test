<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=2">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <style>
        .active2{
            color: #3ba9ff;
            border:1px solid #3ba9ff;
            background-color: #fff;
        }
        .main-info{
            padding: 10px 10px 0 10px;
        }
        .main-info .main-content{
            width: 100%;
            overflow: auto;
        }
        .main-info .info {
            position: relative;
            width: 100%;
            padding: 20px 1%;
            border: 1px solid #bbb;
            font-size: 12px;
            margin: 20px auto;
        }

        .main-info .info-title {
            position: absolute;
            top: -14px;
            left: 10px;
            width: 120px;
            height: 26px;
            line-height: 26px;
            color: #45B4FE;
            background-color: #fff;
            border: 1px solid #45b4fe;
            text-align: center;
            font-size: 13px;
        }

        .main-info .info-btns {
            width: 100%;
            text-align: right;
        }

        .main-info .info-btns .info-btn {
            display: inline-block;
            padding: 0 10px;
            height: 26px;
            line-height: 26px;
            color: #fff;
            background-color: rgba(63, 181, 253, 0.6);
            cursor: pointer;
        }

        .main-info .info-content .info-table--title {
            line-height: 20px;
            padding: 10px 1% 10px 0;
            font-size: 12px;
        }

        .main-info .info-content .table-1 {
            width: 100%;
        }

        .main-info .info-content .table-1 div{
            display: inline-block;
        }

        .main-info .info-content .table-1 tr td {
            width: 24%;
            line-height: 20px;
            padding: 10px 1% 10px 0;
            font-size: 12px;
            /* vertical-align: top; */
        }

        .main-info .info-content .table-1 tr td .td-div__flex {
            width: 100%;
            display: flex;
            /*align-items : top;*/
        }
        .unit{
            position: absolute;
            top: 10px;
            right: 10px;
        }
        .disabled{
            background-color: #eee;
        }
        .poi-no{
            pointer-events: none;
        }

        .ul-bars {
            width: 220px;
            display: flex;
            flex-wrap: wrap;
            border: 1px solid #eee;
            background-color: #fff;
            z-index: 9;
            box-shadow: 0 0 4px 2px rgba(222, 222, 222, 1);
        }

        .ul-bars .li-bar {
            width: 220px;
            height: 36px;
            line-height: 36px;
            border-bottom: 1px solid #eee;
            color: #333;
            text-align: center;
            cursor: pointer;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .ul-bars .li-bar:hover{
            background-color: #f7f7f7;

        }
        .ul-bars .li-bar.active {
            background-color: #3BA9FF;
            color: #fff;
        }

        .pre-table{
            border: none;
            background-color: #FFFFFF;
        }
    </style>
</head>

<body>
<div class="main main-info">
    <input type="hidden" value="${menuCode}" id="menuCode">
    <input type="hidden" value="${dto.id}" id="settlementId">
    <input type="hidden" value="${dto.curUser.userName}" id="curUserName">
    <input type="hidden" value="${dto.submitServicesMoney}" id="servicesMoney">

    <div class="popups"></div>
    <div class="title main-title">
        <button class="butList active" onclick="javascript:reload();">刷新</button>
        <button class="butList active" data-code="dfmx">垫付明细(${dto.settlementApplicants.size()})</button>
        <input  type="hidden" value='${dto.settlementApplicantsJSON}' id="settlementApplicantsJSON" />
        <button class="butList active" data-code="files">出院材料(${dto.fileSettlements.size()})</button>
        <button class="butList active" data-code="get-settlement-track">跟踪信息(${dto.settlementTracks.size()})</button>

        <c:if test="${menuCode == 'cyjs-list'}">
            <c:if test="${dto.settlementState == 1}">
<%--                <button class="butList active" data-code='commit-cyjs'>提交出院结算</button>--%>
            </c:if>
        </c:if>
        <c:if test="${menuCode == 'app-claims-list'}">
            <c:if test="${dto.settlementState == 2}">
                <button class="butList active" data-code='app-claims'>申请理赔</button>
            </c:if>
        </c:if>

        <c:if test="${menuCode == 'repay-app-list'}">
            <c:if test="${dto.settlementState == 3 && dto.showRepayAppBtn}">
                <button class="butList active" data-code="repay-app">还款申请</button>
            </c:if>
        </c:if>

        <c:if test="${menuCode == 'safe-list'}">
            <c:if test="${dto.settlementState == 4}">
                <button class="butList active" data-code="safe-opr-yes">保司终审通过</button>
            </c:if>
        </c:if>

        <c:if test="${menuCode == 'bad-list'}">
            <c:if test="${dto.isBad == 1}">
                <c:if test="${dto.showRepayAppBtn}">
                    <button class="butList active" data-code="operate-bad" data-urge="1">电话催收</button>
                    <button class="butList active" data-code="operate-bad" data-urge="2">上门催收</button>
                    <button class="butList active" data-code="operate-bad" data-urge="3">律师函</button>
                    <button class="butList active" data-code="operate-bad" data-urge="4">诉讼</button>
                    <button class="butList active" data-code="operate-bad" data-urge="5">催收终止</button>
                </c:if>
            </c:if>
        </c:if>
    </div>
    <div class="main-content">
        <div class="info">
            <div class="info-title">委托信息</div>
            <div class="info-content">
                <table class="table-1 new-bas-info" border="0" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr>
                        <td>案件编号：${dto.settlementNo}</td>
                        <td>保险公司：${dto.finaApplicantInfo.entrustOrgName}</td>
                        <td>申请人姓名：${dto.finaApplicant.finaUserName}</td>
                        <td>申请人身份证号：${dto.finaApplicant.finaUserIdcard}</td>
                    </tr>
                    <tr>
                        <td>申请人手机号：${dto.finaApplicant.finaUserTel}</td>
                        <td>被保险人姓名：${dto.finaApplicantInfo.insuredName}</td>
                        <td>被保险人身份证号：${dto.finaApplicantInfo.insuredIdcard}</td>
                        <td>被保险人年龄：${dto.finaApplicantInfo.insuredAge}</td>
                    </tr>
                    <tr>
                        <td>被保险人手机号：${dto.finaApplicantInfo.insuredTel}</td>
                        <td>申请人与被保险人关系：
                            <c:if test="${dto.finaApplicantInfo.relationship == 1}">本人</c:if>
                            <c:if test="${dto.finaApplicantInfo.relationship == 2}">投保人</c:if>
                            <c:if test="${dto.finaApplicantInfo.relationship == 3}">直属亲属</c:if>
                        </td>
                        <td>申请垫付总金额：${dto.lefanPaymentMoney}</td>
                        <td>保单号：${dto.finaApplicantInfo.insurancePolicyNo}</td>
                    </tr>
                    <tr>
                        <td>保额：${dto.finaApplicantInfo.insureMoney}</td>
                        <td>险种：${dto.finaApplicantInfo.insureType}</td>
                        <td>首次投保时间：<fmt:formatDate value="${dto.finaApplicantInfo.firstInsureTime}" pattern="yyyy-MM-dd" /></td>
                        <td>保险期限：<fmt:formatDate value="${dto.finaApplicantInfo.insureStartTime}" pattern="yyyy-MM-dd" />至<fmt:formatDate value="${dto.finaApplicantInfo.insureEndTime}" pattern="yyyy-MM-dd" /></td>
                    </tr>
                    <tr>
                        <td>免赔额：${dto.finaApplicantInfo.deductibleMoney}</td>
                        <td>出险原因：
                            <c:if test="${dto.finaApplicantInfo.outInsureType == 1}">意外</c:if>
                            <c:if test="${dto.finaApplicantInfo.outInsureType == 2}">疾病</c:if>
                            <c:if test="${dto.finaApplicantInfo.outInsureType == 3}">其他(${dto.finaApplicantInfo.outInsureReason})</c:if>
                        </td>
                        <td>就诊医院：${dto.finaApplicantInfo.hospitalName}</td>
                        <td>医院所在地：${dto.finaApplicantInfo.finaHospitalInfo.address}</td>
                    </tr>
                    <tr>
                        <td>就诊科室：${dto.finaApplicantInfo.department}</td>
                        <td>入院时间：<fmt:formatDate value="${dto.finaApplicantInfo.inHospitalTime}" pattern="yyyy-MM-dd" /></td>
                        <td>创建人：${dto.finaApplicantInfo.createBy}</td>
                        <td>创建时间：<fmt:formatDate value="${dto.finaApplicantInfo.createTime}" pattern="yyyy-MM-dd" /></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="info">
            <div class="info-title">垫付信息</div>
            <div class="info-content">
                <form id="settlementFormOne" role="form" action="${ctx}/fina/settlement/operate?settlementId=${dto.id}" method="post">
                    <input type="hidden" id="btnCode" name="btnCode">
                    <table class="table-1 new-bas-info" border="0" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr>
                        <td>结算状态：${dto.settlementStateStr}</td>
                        <td>实际放款总金额：${dto.realMoney}</td>
                        <td>医疗费用总金额：${dto.medicalMoney}</td>
                        <td>医保报销金额：${dto.medicalInsuranceMoney}</td>
                    </tr>
                    <tr>
                        <td>客户垫付总金额：${dto.customerPaymentMoney}</td>
                        <td>退款金额：${dto.refundMoney}</td>
                        <td>客户收到退款总金额：${dto.customerReceivesMoney}</td>
                        <td>我司收到退款总金额：${dto.lefanReceivesMoney}
                            <c:if test="${menuCode == 'repay-order-list' && dto.refundState == 0}">
                                <button class="butList active2" data-code="cw-refund-ok">待确认</button>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>退还途径：
                            <c:if test="${dto.refundChannel == 1}">现金</c:if>
                            <c:if test="${dto.refundChannel == 2}">客户银行卡</c:if>
                            <c:if test="${dto.refundChannel == 3}">原路返回</c:if>
                        </td>
                        <td>服务费：
                            <div id="div_submit_services_money_1">${dto.submitServicesMoney}
                                <c:if test="${dto.finshRepaymentTime != null && dto.finshPassedTime == null}">
                                    <a onclick="updDiv('submitServicesMoney')"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a>
                                </c:if>
                            </div>
                            <div id="div_submit_services_money_2" style="display: none">
                                <input type="number" step="0.01" class="input-2" name="submitServicesMoney" value="${dto.submitServicesMoney}" />
                                <input type="submit" value="确定" onclick="setBtnCode('submitServicesMoney')" class="btn" style="display: inline-flex" />
                            </div>
                        </td>
                        <td>客服人员：${dto.finaApplicantMoney.customerName}</td>
                        <td>审核人员：${dto.finaApplicantMoney.checkManName}</td>
                    </tr>
                    </tbody>
                </table>
                </form>
            </div>
        </div>
        <c:if test="${dto.repaymentInfos.size() > 0}">
            <div class="info">
                <div class="info-title">还款记录</div>
                <div class="info-content">
                    <table class="table-1 new-bas-info" border="0" cellpadding="0" cellspacing="0">
                        <tbody>
                        <tr>
                            <td colspan="4" style="font-weight: bold">还款状态：
                                <c:if test="${dto.repaymentState == 1}">待还款</c:if>
                                <c:if test="${dto.repaymentState == 2}">部分还款</c:if>
                                <c:if test="${dto.repaymentState == 3}">已还款</c:if>
                                &nbsp;&nbsp;&nbsp;&nbsp; 未还款金额：${dto.noRepaymentMoney}  &nbsp;&nbsp;&nbsp;&nbsp; 已还款金额：${dto.repaymentMoney}</td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <table class="table-1">
                                    <tr style="border-bottom:1px solid #bbb">
                                        <td style="width:11%">还款编号</td>
                                        <td style="width:10%">本次还款金额</td>
                                        <td style="width:6%">本次还款来源</td>
                                        <td style="width:7%">还款方式</td>
                                        <td style="width:8%">凭证</td>
                                        <td style="width:10%">还款时间</td>
                                        <td style="width:10%">申请人</td>
                                        <td style="width:10%">财务审核状态</td>
                                        <td style="width:8%">财务人员</td>
                                        <td style="width:10%">审核通过时间</td>
                                        <td style="width:10%">操作</td>
                                    </tr>
                                    <c:forEach items="${dto.repaymentInfos}" var="item" varStatus="st">
                                        <tr>
                                            <td style="width:11%">${item.repaymentNo}</td>
                                            <td style="width:10%">${item.repaymentMoney}</td>
                                            <td style="width:6%">
                                                <c:if test="${item.repaymentSource == 1}">保司还款</c:if>
                                                <c:if test="${item.repaymentSource == 2}">客户还款</c:if>
                                            </td>
                                            <td style="width:7%">
                                                <c:if test="${item.repaymentType == 1}">正常还款</c:if>
                                                <c:if test="${item.repaymentType == 2}">电话催收还款</c:if>
                                                <c:if test="${item.repaymentType == 3}">现场催收还款</c:if>
                                                <c:if test="${item.repaymentType == 4}">律师函</c:if>
                                                <c:if test="${item.repaymentType == 5}">诉讼</c:if>
                                            </td>
                                            <td style="width:8%"><a href="javascript:void(0)" class="repay-record" data-type="get-files"
                                                                    <c:if test="${item.repaymentType == 1}">data-id="${item.id}" data-code="FINA_REPAYMENT_INFO_ATTR"</c:if>
                                                                    <c:if test="${item.repaymentType == 2}">data-id="${item.urgeId}" data-code="FINA_URGE_INFO_ATTR_TEL"</c:if>
                                                                    <c:if test="${item.repaymentType == 3}">data-id="${item.urgeId}" data-code="FINA_URGE_INFO_ATTR_USER"</c:if>
                                                                    <c:if test="${item.repaymentType == 4}">data-id="${item.urgeId}" data-code="FINA_URGE_INFO_ATTR_LS_TWO"</c:if>
                                                                    <c:if test="${item.repaymentType == 5}">data-id="${item.urgeId}" data-code="FINA_URGE_INFO_ATTR_SS_TWO"</c:if>>凭证</a>
                                            </td>
                                            <td style="width:10%"><fmt:formatDate value="${item.repaymentTime}" pattern="yyyy-MM-dd" /></td>
                                            <td style="width:10%">${item.applyUserName}</td>
                                            <td style="width:10%">
                                                <c:if test="${item.repaymentState == 1}">财务待审核</c:if>
                                                <c:if test="${item.repaymentState == 2}">财务审核通过</c:if>
                                                <c:if test="${item.repaymentState == 3}">财务审核驳回</c:if>
                                            </td>
                                            <td style="width:8%">${item.financeUserName}</td>
                                            <td style="width:10%"><fmt:formatDate value="${item.repaymentPassedTime}" pattern="yyyy-MM-dd" /></td>
                                            <td style="width:10%">
                                                <c:if test="${menuCode == 'repay-order-list'}">
                                                    <c:if test="${item.repaymentState == 1}">
                                                        <a href="javascript:void(0)" class="repay-record" data-type="cw-yes" data-id="${item.id}">审核通过</a>
                                                        <a href="javascript:void(0)" class="repay-record" data-type="cw-no" data-id="${item.id}" >驳回</a>
                                                    </c:if>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>
        <div class="info">
            <div class="info-title">机构信息</div>
            <div class="info-content">
                <table class="table-1 new-bas-info" border="0" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr style="border-bottom:1px solid #bbb">
                        <td style="width: 15%">垫付机构名称</td>
                        <td style="width: 10%">垫付任务类型</td>
                        <td style="width: 10%">分派机构日期</td>
                        <td style="width: 10%">机构提交日期</td>
                        <td style="width: 10%">机构截止日期</td>
                        <td style="width: 10%">机构时效</td>
                        <td style="width: 10%">机构状态</td>
                        <td style="width: 10%">服务费</td>
                        <td style="width: 10%">操作</td>
                    </tr>
                    <c:forEach items="${dto.orgInfos}" var="item" varStatus="st">
                        <tr>
                            <td style="width: 15%">${item.surveyOrgName}</td>
                            <td style="width: 10%">${item.settlementTaskTypeStr}</td>
                            <td style="width: 10%"><fmt:formatDate value="${item.orgAssignTime}" pattern="yyyy-MM-dd" /></td>
                            <td style="width: 10%"><fmt:formatDate value="${item.orgSubmitTime}" pattern="yyyy-MM-dd" /></td>
                            <td style="width: 10%"><fmt:formatDate value="${item.orgEndTime}" pattern="yyyy-MM-dd" /></td>
                            <td style="width: 10%"><pre class="pre-table">${item.agingHtml}</pre></td>
                            <td style="width: 10%">
                                <c:if test="${item.settlementOrgState == 1}">作业中</c:if>
                                <c:if test="${item.settlementOrgState == 2}">已提交</c:if>
                            </td>
                            <td style="width: 10%">${item.serviceMoney}</td>
                            <td style="width: 10%"></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="info">
            <div class="info-title">垫付员信息</div>
            <div class="info-content">
                <table class="table-1 new-bas-info" border="0" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr style="border-bottom:1px solid #bbb">
                        <td style="width: 10%">垫付员</td>
                        <td style="width: 10%">垫付任务类型</td>
                        <td style="width: 10%">分派日期</td>
                        <td style="width: 10%">提交日期</td>
                        <td style="width: 10%">截止日期</td>
                        <td style="width: 10%">垫付员时效</td>
                        <td style="width: 10%">垫付员状态</td>
                        <td style="width: 10%">分值</td>
                        <td style="width: 10%">服务费</td>
                        <td style="width: 10%">操作</td>
                    </tr>
                    <c:forEach items="${dto.surveyUserInfos}" var="item" varStatus="st">
                        <tr>
                            <td style="width: 10%">${item.surveyUserName}</td>
                            <td style="width: 10%">${item.userTaskTypeStr}</td>
                            <td style="width: 10%"><fmt:formatDate value="${item.userAssignTime}" pattern="yyyy-MM-dd" /></td>
                            <td style="width: 10%"><fmt:formatDate value="${item.userSubmitTime}" pattern="yyyy-MM-dd" /></td>
                            <td style="width: 10%"><fmt:formatDate value="${item.userEndTime}" pattern="yyyy-MM-dd" /></td>
                            <td style="width: 10%"><pre class="pre-table">${item.agingHtml}</pre></td>
                            <td style="width: 10%">
                                <c:if test="${item.state == 1}">作业中</c:if>
                                <c:if test="${item.state == 2}">已提交</c:if>
                            </td>
                            <td style="width: 10%">${item.score}</td>
                            <td style="width: 10%">${item.serviceMoney}</td>
                            <td style="width: 10%"></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/html" id="view-or-textarea">
    <div class="layui-form" style="margin-top: 20px">
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin: 0">
                    <textarea class="reason-textarea layui-textarea" placeholder="请输入"
                              style="height: 150px;width: 90%;margin:0 auto;resize:none;"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 140px; margin-top: 20px">
                <button class="layui-btn layui-btn-primary close-btn" style="width: 100px;">取消</button>
                <button class="layui-btn layui-btn-normal submit-btn" style="width: 100px;">确定</button>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="view-or-input">
    <div class="layui-form" style="margin-top: 30px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input money-input-label" style="width: 130px">XX</label>
                <div class="layui-input-inline _input">
                    <input type="text" class="money-input layui-input" placeholder="" autocomplete="off">                     <div class="unit">元</div>

                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 140px;margin-top: 30px">
                <button class="layui-btn layui-btn-primary close-btn" style="width: 100px;">取消</button>
                <button class="layui-btn layui-btn-normal submit-btn" style="width: 100px;">确定</button>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="view-or-refundOK">
    <div class="layui-form" style="margin-top: 30px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">退款金额</label>
                <div class="layui-input-inline _input">
                    <input type="text" class="lefanRefundMoney layui-input" placeholder="" autocomplete="off">
                    <div class="unit">元</div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">确认人</label>
                <div class="layui-input-inline _input">
                    <input type="text" class="user-input layui-input disabled" placeholder="" autocomplete="off" disabled>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">确认时间</label>
                <div class="layui-input-inline _input">
                    <input type="text" class="time-input layui-input" id="refundOkTime" placeholder="" autocomplete="off">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 130px;margin-top: 30px">
                <button class="layui-btn layui-btn-primary close-btn" style="width: 100px;">取消</button>
                <button class="layui-btn layui-btn-normal submit-btn" style="width: 100px;">确定</button>
            </div>
        </div>
    </div>
</script>

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>

<script>
    layui.use(['jquery', 'layer', 'util', 'laydate'], function () {
        var layer = layui.layer,
            $ = layui.$,
            laydate = layui.laydate,
            util = layui.util;

        var _heightH = $('.main-title').length ?  $('.main-title').outerHeight() : 0
        var _height = $(parent.document).outerHeight() - _heightH -90

        $('.main-content').height(_height)
        $('body').on('click','.repay-record',function () {

            var _this = $(this)
            _this.addClass('poi-no')
            setTimeout(function () {
                _this.removeClass('poi-no')
            },2000)

            var type = $(this).attr('data-type'),id = $(this).attr('data-id'),keyCode = $(this).attr("data-code");
            if (type == 'cw-yes'){
                showModel({
                    title: '确认审核通过？',
                    url: '${ctx}/fina/settlement/operate',
                    param: {
                        btnCode: 'cw-yes',
                        settlementId: $('#settlementId').val(),
                        repaymentId:id,
                    }
                })
            }else if (type == 'cw-no'){
                var params = {
                    title: '确认驳回？',
                    value: '',
                    placeholder: '请输入驳回原因',
                    disabled: false,
                    url:'${ctx}/fina/settlement/operate',
                    param: {
                        btnCode: 'cw-no',
                        settlementId: $('#settlementId').val(),
                        repaymentId:id,
                    },
                    labelKeyName: 'oprRemark'
                }
                showModelT(params)
            }else if (type == 'get-files'){
                var _height =  $(document).height() * 0.9
                var _width = $(document).width() * 0.9
                openDialog({
                    frame:true,
                    title:"凭证",
                    height:_height,
                    width:_width,
                    url:"${ctx}/fina/settlement/operateView?settlementId=" + $('#settlementId').val() + "&menuCode=${menuCode}&btnCode=repay-files&opr=view&keyCode="+keyCode+"&keyId="+id
                });
            }

        })

        $('body').on('click','.butList',function () {
            var settlementId = $('#settlementId').val()
            var btnCode  = $(this).attr('data-code')
            var _height =  $(document).height() * 0.9
            var _width = $(document).width() * 0.9

            var _this = $(this)
            _this.attr('disabled',true)
            setTimeout(function () {
                _this.removeAttr('disabled')
            },2000)

            if (btnCode == 'commit-cyjs') {
                showModel({
                    title: '确认提交出院结算？',
                    url: '${ctx}/fina/settlement/operate',
                    param: {
                        settlementId:settlementId,
                        btnCode: btnCode,
                    }
                })
            }
            if (btnCode == 'app-claims') {
                showModel({
                    title: '确认申请理赔？',
                    url: '${ctx}/fina/settlement/operate',
                    param: {
                        settlementId:settlementId,
                        btnCode: btnCode,
                    }
                })
            }else if (btnCode == 'repay-app') {

                openDialog({
                    frame:true,
                    title:"还款",
                    height:_height,
                    width:_width,
                    url:"${ctx}/fina/settlement/operateView?settlementId=" + settlementId + "&menuCode=${menuCode}&btnCode=" + btnCode,
                    load:true
                });
            }else if (btnCode == 'safe-opr-yes') {
                var servicesMoney = $("#servicesMoney").val();
                var param = {
                    title: '确认保司终审通过？',
                    label: '服务费',
                    value: servicesMoney,
                    placeholder: '请输入',
                    disabled: false,
                    url: '${ctx}/fina/settlement/operate',
                    param: {
                        settlementId:settlementId,
                        btnCode: btnCode,
                    },
                    labelKeyName: 'servicesMoney'
                }
                showModelI(param)
            }else if (btnCode == 'operate-bad' && $(this).attr('data-urge') == '5') {
                var params = {
                    title: '确认催收终止？',
                    value: '',
                    placeholder: '请输入催收情况简介',
                    disabled: false,
                    url:'${ctx}/fina/settlement/operate',
                    param: {
                        btnCode: 'operate-bad',
                        settlementId: $('#settlementId').val(),
                        urgeType: 'stop-urge',
                    },
                    labelKeyName: 'urgeDesc'
                }
                showModelT(params)
            }else if (btnCode == 'get-settlement-track'){
                openDialog({
                    frame:true,
                    title:"跟踪信息",
                    height:_height,
                    width:_width,
                    url:"${ctx}/fina/settlement/operateView?settlementId=" + settlementId + "&menuCode=${menuCode}&btnCode=" + btnCode + "&dataType=settlement-track-save",
                });
            }else if (btnCode == 'operate-bad'){
                var dataUrge  = $(this).attr('data-urge')
                openDialog({
                    frame:true,
                    title:"坏账处理",
                    height:_height,
                    width:_width,
                    url:"${ctx}/fina/settlement/operateView?settlementId=" + settlementId + "&menuCode=${menuCode}&btnCode="+btnCode+"&dataUrge=" + dataUrge,
                });
            }else if (btnCode == 'cw-refund-ok') {
                var param = {
                    title: '待确认',
                    disabled: false,
                }
                showModelRO(param)
            }else if (btnCode == 'files'){
                openDialog({
                    frame:true,
                    title:"出院材料",
                    height:_height,
                    width:_width,
                    url:"${ctx}/fina/settlement/operateView?settlementId=" + settlementId + "&menuCode=${menuCode}&btnCode=" + btnCode +'&opr=view'
                });
            }else if (btnCode == 'dfmx'){
               var _this = $(this)
                if (!_this.attr('data-show')){
                    var _position = _this.position()
                    var _top = _position.top + 40
                    var _html = '<div class="ul-bars" style="position:fixed;left:'+_position.left+'px;top:'+_top+'px;display:none">'

                    var cases = JSON.parse($("#settlementApplicantsJSON").val());
                    cases.map(function(item){
                        _html += '<div class="li-bar" data-id="'+item.finaInfoId+'" title="'+item.caseApplicantNo+'">'+item.caseApplicantNo+'</div>'
                    });

                    _html += '</div>'
                    $('.popups').append(_html)

                    $(".ul-bars").slideToggle("2000");
                    _this.attr('data-show',1)

                    $('.popups').on('click','.li-bar',function () {
                        $(".ul-bars").slideToggle("2000");
                        _this.attr('data-show',1)
                        var _id = $(this).attr("data-id");
                        var _height = $(document).height() * 0.99
                        var _width = $(document).width() * 0.99
                        var menuCode = $("#menuCode").val();
                        var url = "${ctx}/fina/applicant/info?finaInfoId=" + _id + "&display=true&menuCode="+menuCode;
                        openDialog({
                            frame:true,
                            title:"",
                            height:_height,
                            width:_width,
                            url:url
                        });
                    })
                }else if (_this.attr('data-show') == '1'){
                    $(".ul-bars").slideUp("2000");
                    _this.attr('data-show',0)
                }else if (_this.attr('data-show') == '0'){
                    $(".ul-bars").slideToggle("2000");
                    _this.attr('data-show',1)

                }

            }
        })
        function showModel(params) {
            var _flag = true
            layer.confirm(params.title, {
                btn: ['确定', '取消'] //按钮
            }, function () {
                if (!_flag){
                    return;
                }
                _flag = false
                $.ajax({
                    url: params.url,
                    data:params.param,
                    success: function (res) {
                        var res = JSON.parse(res)
                        if (res.isSuccess){
                            layer.msg('操作成功', {
                                time: 1000,
                                icon: 1
                            },function () {
                                location.reload()
                            })
                        }else {
                            layer.msg(res.msg, {
                                time: 1000,
                                icon: 2
                            }, function () {

                            })
                        }
                    }
                })
            }, function () {

            });
        }
        function showModelI(params) {
            inputIndex = layer.open({
                type: 1,
                title: params.title,
                area: ['500px', '300px'],
                content: $('#view-or-input').html(),
                success: function () {
                    $('.money-input').attr('placeholder', params.placeholder)
                    if (params.disabled) {
                        $('.money-input').attr('disabled', true)
                    }
                    $('.money-input-label').html(params.label)
                    $('.submit-btn').click(function () {
                        $('.submit-btn').addClass('poi-no')
                        setTimeout(function () {
                            $('.submit-btn').removeClass('poi-no')
                        }, 6000)

                        var param = Object.assign(params.param,{
                            [params.labelKeyName]: $('.money-input').val(),
                        })
                        if (!checkPapers('money',$('.money-input').val())){
                            if (!params.urgeMoney){
                                layer.alert('请输入小数点两位内的数字！', {
                                    icon: 5
                                })
                                return;
                            }
                        }
                        $.ajax({
                            url: params.url,
                            type: 'post',
                            data: param,
                            success: function (res) {
                                var res = JSON.parse(res)
                                if (res.isSuccess){
                                    layer.msg('成功', {
                                        time: 1000,
                                        icon: 1
                                    },function(){
                                        location.reload()
                                    })
                                }else{
                                    _this.removeClass('poi-no')
                                    layer.msg(res.msg, {
                                        time: 1000,
                                        icon: 2
                                    })
                                }
                            }
                        })
                    })
                    $('.close-btn').click(function () {
                        layer.close(inputIndex)
                    })
                }
            });

        }
        function showModelT(params) {
            reasonIndex = layer.open({
                type: 1,
                title: params.title,
                area: ['500px', '300px'],
                content: $('#view-or-textarea').html(),
                success: function () {
                    $('.reason-textarea').attr('placeholder', params.placeholder)

                    $('.submit-btn').click(function () {
                        $('.submit-btn').addClass('poi-no')
                        setTimeout(function () {
                            $('.submit-btn').removeClass('poi-no')
                        }, 6000)
                        if (!$('.reason-textarea').val()){
                            layer.alert('请输入内容！', {
                                icon: 5
                            })
                            return;
                        }
                        var param = Object.assign(params.param,{
                            [params.labelKeyName]: $('.reason-textarea').val(),
                        })
                        $.ajax({
                            url: params.url,
                            type: 'post',
                            data: param,
                            success: function (res) {
                                var res = JSON.parse(res)
                                if (res.isSuccess){
                                    layer.msg('成功', {
                                        time: 1000,
                                        icon: 1
                                    },function(){
                                        location.reload()
                                    })
                                }else{
                                    _this.removeClass('poi-no')
                                    layer.msg(res.msg, {
                                        time: 1000,
                                        icon: 2
                                    })
                                }
                            }
                        })
                    })
                    $('.close-btn').click(function () {
                        layer.close(reasonIndex)
                    })
                }
            });

        }
        function showModelRO(param) {
            inputIndex = layer.open({
                type: 1,
                title: param.title,
                area: ['500px', '400px'],
                content: $('#view-or-refundOK').html(),
                success: function () {
                    refundOkTime = laydate.render({
                        elem: '#refundOkTime',
                        value:'',
                        type: 'datetime',
                        format: 'yyyy-MM-dd HH:mm:ss',
                        trigger: 'click'
                    });

                    $('.user-input').val($('#curUserName').val())
                    if (param.disabled) {
                        $('.layui-input').attr('disabled', true)
                        $('.layui-input').addClass('disabled')
                        $('.submit-btn').hide()
                        $('.close-btn').hide()
                    }

                    $('.submit-btn').click(function () {
                        $('.submit-btn').addClass('poi-no')
                        setTimeout(function () {
                            $('.submit-btn').removeClass('poi-no')
                        }, 6000)
                        var url = '${ctx}/fina/settlement/operate'

                        var params = {
                            btnCode: 'cw-refund-ok',
                            settlementId: $('#settlementId').val(),
                            lefanRefundMoney: $('.lefanRefundMoney').val(),
                            refundOkTime: $('#refundOkTime').val(),
                        }
                        $.ajax({
                            url: url,
                            type: 'post',
                            data: params,
                            success: function (res) {
                                var res = JSON.parse(res)
                                if (res.isSuccess){
                                    layer.msg('成功', {
                                        time: 1000,
                                        icon: 1
                                    },function(){
                                        location.reload()
                                    })
                                }else{
                                    _this.removeClass('poi-no')
                                    layer.msg(res.msg, {
                                        time: 1000,
                                        icon: 2
                                    })
                                }
                            }
                        })
                    })
                    $('.close-btn').click(function () {
                        layer.close(inputIndex)
                    })
                }
            });
        }

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


    $("#settlementFormOne").bind('submit', function(event) {
        ajaxFormSubmit(this,reload,null,null,reload);
        event.preventDefault();
    });

    var updDiv = function(btnCode){
        if (btnCode == 'submitServicesMoney'){
            $("#div_submit_services_money_1").hide();
            $("#div_submit_services_money_2").show();
        }
    }
    var setBtnCode = function(btnCode){
        $("#btnCode").val(btnCode);
    }

</script>

</html>
