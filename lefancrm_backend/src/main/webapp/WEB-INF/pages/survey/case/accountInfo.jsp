<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

<!DOCTYPE html>
<html>
<head>
    <title>详情</title>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">

    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<style>
    .table-title{
        font-size: 14px;
        font-weight: bold;
        line-height: 40px;
    }
    table.spec-info tr td:nth-of-type(2n + 1) {
        width: 13%;
    }
    table.spec-info tr td:nth-of-type(2n) {
        width: 20%;
    }
</style>

<style>
    .stepList {
        margin: 0 32px;
    }

    .stepItem {
        display: flex;
    }

    .stepDate {
        width: 250px;
        /*font-size: 28px;*/
        color: #555;
    }

    .stepDot {
        width: 60px;
        position: relative;
        border-left: solid 1px #54b0ff;
    }

    .stepDot-dot {
        box-sizing: border-box;
        border: solid 1px #54b0ff;
        background: #fff;
        width: 15px;
        height: 15px;
        border-radius: 15px;
        position: absolute;
        left: -8px;
    }
    .stepDot-dot.finished{
        background: #54b0ff;
    }
    .stepDetail {
        padding-left: 30px;
        flex: 1;
        color: #555;
        padding-bottom: 50px;
    }

    .stepDetail-title {
        /*font-size: 34px;*/
        line-height: 1.5;
    }

    .stepDetail-detail {
        font-size: 24px;
    }

    .finished.stepDot-dot {
        background: #54b0ff;
    }
    .stepItem.omit {
        min-height: 96px;
    }
    .stepDot.omit {
        width: 60px;
        position: relative;
        border-left: dashed 1px #54b0ff;
    }

    .input-2 {
        width: 80px;
        border: none;
        border-bottom: 1px solid #555;
        text-align: center;
    }

    .div_source_ok{
        display: inline-block;
     }
</style>
<body>
<div class="main">
    <input type="hidden" id="menuCode" value="${menuCode}" />
    <div class="title">
        <button class="butList active" onclick="operate(${dto.id},'files',false)">查看材料</button>
        <button class="butList active" onclick="operate(${dto.id},'progress',false)">查看进度</button>
    </div>
    <div class="main-boy">
        <div>
            <div class="table-title">基础信息</div>
            <form id="editForm" role="form" action="${ctx}/survey/case/operate" method="post">
                <input type="hidden" value="${dto.id}" name="id" />
                <input type="hidden" value="${btnCode}" name="btnCode" id="btnCode" />
                <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>调查编号</td>
                    <td>${dto.surveyRiskCase.surveyNo}</td>
                    <td>被调查人</td>
                    <td>${dto.surveyRiskCase.surveyPerson}</td>
                    <td>联系号码</td>
                    <td>${dto.surveyRiskCase.surveryPersonTel}</td>
                </tr>
                <tr>
                    <td>领域</td>
                    <td>${dto.surveyBusName}</td>
                    <td>任务类型</td>
                    <td colspan="3">
                        <c:forEach items="${dto.surveyTaskTypes}" var="item">
                            ${item.taskName}&nbsp;
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td>业务类型</td>
                    <td>${dto.servicesName}</td>
                    <td>案件截至日期</td>
                    <td>
                        <div id="div_end_time_1">
                            <fmt:formatDate value="${dto.endTime}" pattern="yyyy-MM-dd"/>
                        </div>
                    </td>
                    <td>状态</td>
                    <td>${dto.surveyStateName}</td>
                </tr>
                <tr>
                    <td>委托人名称</td>
                    <td>${dto.surveyRiskCase.entrustUserName}</td>
                    <td>委托人机构名称</td>
                    <td colspan="3">${dto.surveyRiskCase.entrustOrgName}</td>
                </tr>
                <tr>
                    <td>保险合同编号</td>
                    <td>${dto.surveyRiskCase.policyNo}</td>
                    <td>理赔编号</td>
                    <td>${dto.surveyRiskCase.claimsNo}</td>
                    <td>理赔申请金额</td>
                    <td>${dto.surveyRiskCase.claimsMoney}</td>
                </tr>
                <tr>
                    <td>证件类型</td>
                    <td>
                        <c:if test="${dto.surveyRiskCase.idType ==1}">身份证</c:if>
                        <c:if test="${dto.surveyRiskCase.idType ==2}">驾驶证</c:if>
                    </td>
                    <td>证件号码</td>
                    <td colspan="3">${dto.surveyRiskCase.idNumber}</td>
                </tr>
                <tr>
                    <td>结算方式</td>
                    <td>
                        <c:if test="${dto.payType == 1}">一口价</c:if>
                        <c:if test="${dto.payType == 2}">基本费+减损</c:if>
                        <c:if test="${dto.payType == 3}">任务</c:if>
                        <c:if test="${dto.payType == 4}">任务+减损</c:if>
                    </td>
                    <td>结算详情</td>
                    <td colspan="4">
                        <c:if test="${dto.payType == 1}">(${dto.entrustMoney})元</c:if>
                        <c:if test="${dto.payType == 2}">基本费(${dto.entrustMoney})，减损描述(${dto.entrustReLossesRemark})</c:if>
                        <c:if test="${dto.payType == 3}">任务(${dto.surveyCaseDirections.size()})个</c:if>
                        <c:if test="${dto.payType == 4}">任务(${dto.surveyCaseDirections.size()})个，减损描述(${dto.entrustReLossesRemark})</c:if>
                    </td>
                </tr>
                <c:if test="${dto.entrustCredit == 0}">
                    <tr>
                        <td>是否授信</td>
                        <td>
                            <c:if test="${dto.entrustCredit == 0}">否</c:if>
                            <c:if test="${dto.entrustCredit == 1}">是</c:if>
                        </td>
                        <td>预付金额</td>
                        <td>
                            <div id="div_credit_money_1">
                                    ${dto.entrustCreditMoney}
                            </div>
                        </td>
                        <td>预付金额是否支付</td>
                        <td>
                            <c:if test="${dto.entrustCreditIsPay == 0}">待支付</c:if>
                            <c:if test="${dto.entrustCreditIsPay == 1}">已支付</c:if>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td>案源机构</td>
                    <td colspan="5">
                        <div class="div_source_ok" id="div_source_ok_1">${dto.sourceOrgName}
                            <c:if test="${dto.sourceSupportType==1}">市场一部（郑哲）</c:if>
                            <c:if test="${dto.sourceSupportType==2}">市场二部（曹刘强）</c:if>
                            <c:if test="${dto.sourceSupportType==3}">正言金融（郑哲）</c:if>
                            <c:if test="${dto.sourceSupportType==4}">互助</c:if>
                            <c:if test="${dto.sourceSupportType==5}">市场三部（韩正栋）</c:if>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>乐凡申请结算价格</td>
                    <td colspan="5">
                        <div id="div_entrust_1">基本费(${dto.entrustMoney})减损奖励(${dto.entrustReLosses})
                        </div>
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<td>委托方确认结算价格</td>--%>
                    <%--<td>--%>
                        <%--<div id="div_entrust_ok_1">基本费(${dto.entrustOkPrice1})减损奖励(${dto.entrustOkPrice2})--%>
                        <%--</div>--%>
                    <%--</td>--%>
                    <%--<td>开票金额</td>--%>
                    <%--<td>--%>
                        <%--<div id="div_entrust_ok_3"><c:if test="${dto.billingMoney ==null}">${dto.entrustOkPrice1 + dto.entrustOkPrice2}</c:if><c:if test="${dto.billingMoney !=null}">${dto.billingMoney}</c:if></div>--%>
                    <%--</td>--%>
                    <%--<td>到账金额</td>--%>
                    <%--<td>--%>
                        <%--<div id="div_entrust_ok_5"><c:if test="${dto.confirmAccountMoney ==null}">${dto.entrustOkPrice1 + dto.entrustOkPrice2}</c:if><c:if test="${dto.confirmAccountMoney !=null}">${dto.confirmAccountMoney}</c:if></div>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr>
                    <td>委托方确认结算价格</td>
                    <td>
                        <div id="div_entrust_ok_1">基本费(${dto.entrustOkPrice1})减损奖励(${dto.entrustOkPrice2})
                    </td>
                    <td>开票金额</td>
                    <td>
                        <div id="div_entrust_ok_3"><c:if test="${dto.billingMoney ==null}">${dto.entrustOkPrice1 + dto.entrustOkPrice2}</c:if><c:if test="${dto.billingMoney !=null}">${dto.billingMoney}</c:if>
                            <a onclick="cliUpdPrice(6)"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a>
                        </div>
                        <div id="div_entrust_ok_4" style="display: none">
                            <input type="number" class="input-2" step="0.00" name="billingMoney" value="<c:if test="${dto.billingMoney ==null}">${dto.entrustOkPrice1 + dto.entrustOkPrice2}</c:if><c:if test="${dto.billingMoney !=null}">${dto.billingMoney}</c:if>" />
                            <input type="submit" value="确定" onclick="oprOK(${dto.id},'900')" class="btn" style="display: inline-flex" />
                        </div>
                    </td>
                    <td>到账金额</td>
                    <td>
                        <div id="div_entrust_ok_5"><c:if test="${dto.confirmAccountMoney ==null}">${dto.entrustOkPrice1 + dto.entrustOkPrice2}</c:if><c:if test="${dto.confirmAccountMoney !=null}">${dto.confirmAccountMoney}</c:if>
                            <a onclick="cliUpdPrice(7)"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a>
                        </div>
                        <div id="div_entrust_ok_6" style="display: none">
                            <input type="number" class="input-2" step="0.00" name="confirmAccountMoney" value="<c:if test="${dto.confirmAccountMoney ==null}">${dto.entrustOkPrice1 + dto.entrustOkPrice2}</c:if><c:if test="${dto.confirmAccountMoney !=null}">${dto.billingMoney}</c:if>" />
                            <input type="submit" value="确定" onclick="oprOK(${dto.id},'901')" class="btn" style="display: inline-flex" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>调查方结算价格</td>
                    <td colspan="5">
                        <div id="div_survery_1">基本费(${dto.surveyMoney})减损奖励(${dto.surveryReLosses})
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>调查报告</td>
                    <td>
                        <c:if test="${dto.reportId == null}">
                            未上传
                        </c:if>
                        <c:if test="${dto.reportId != null}">
                            <a onclick="onlinePreview('${dto.commonFile.filePath}')" target="_blank">预览/下载</a>
                        </c:if>
                    </td>
                    <td>是否经典案例</td>
                    <td>
                        <c:if test="${dto.isClassic== 0}">否</c:if>
                        <c:if test="${dto.isClassic== 1}">是</c:if>
                    </td>
                    <td>是否阳性</td>
                    <td>
                        <c:if test="${dto.isSun == 0}">否</c:if>
                        <c:if test="${dto.isSun == 1}">是</c:if>
                    </td>
                </tr>
                <tr>
                    <td>是否寄送</td>
                    <td>
                        <c:if test="${dto.isSendReport== 0}"><span style="color: red;">未寄送</span></c:if>
                        <c:if test="${dto.isSendReport== 1}">已寄送</c:if>
                    </td>
                    <td>基本费是否结算</td>
                    <td>
                        <c:if test="${dto.price1IsCalc == 0}"><span style="color: red;">未结算</span></c:if>
                        <c:if test="${dto.price1IsCalc == 1}">已结算</c:if>
                    </td>
                    <td>减损奖励是否结算</td>
                    <td>
                        <c:if test="${dto.price2IsCalc == 0}"><span style="color: red;">未结算</span></c:if>
                        <c:if test="${dto.price2IsCalc == 1}">已结算</c:if>
                    </td>
                </tr>
                <tr>
                    <td>财务开票金额</td>
                    <td>${dto.billingApply.billingMoney}</td>
                    <td>财务开票状态</td>
                    <td>
                        <c:if test="${dto.billingApply.billingState == 1}">未申请</c:if>
                        <c:if test="${dto.billingApply.billingState == 2}">已申请</c:if>
                        <c:if test="${dto.billingApply.billingState == 3}">已开票</c:if>
                        <c:if test="${dto.billingApply.billingState == 4}">已退票</c:if>
                        <c:if test="${dto.billingApply.billingState == 5}">退票审核中</c:if>
                        <c:if test="${dto.billingApply.billingState == 6}">重开审核中</c:if>
                        <c:if test="${dto.billingApply.billingState == 7}">退票审核通过</c:if>
                        <c:if test="${dto.billingApply.billingState == 8}">重开审核通过</c:if>
                        <c:if test="${dto.billingApply.billingState == 9}">退票中</c:if>
                    </td>
                    <td>财务开票时间</td>
                    <td><fmt:formatDate value="${dto.billingApply.billingTime}" pattern="yyyy-MM-dd" /></td>
                </tr>
                <tr>
                    <td>财务到账金额</td>
                    <td>${dto.billingApply.confirmAccountMoney}</td>
                    <td>财务到账状态</td>
                    <td>
                        <c:if test="${dto.billingApply.confirmAccountState == 1 || dto.billingApply.confirmAccountState == null}">未到账</c:if>
                        <c:if test="${dto.billingApply.confirmAccountState == 2}">已到账</c:if>
                    </td>
                    <td>财务到账时间</td>
                    <td><fmt:formatDate value="${dto.billingApply.confirmAccountTime}" pattern="yyyy-MM-dd" /></td>
                </tr>
                <tr>
                    <td>调查基本信息</td>
                    <td colspan="5">${dto.surveyInfo}</td>
                </tr>
                <tr>
                    <td>调查事项</td>
                    <td colspan="5">${dto.surveyItem}</td>
                </tr>
                <tr>
                    <td>已分配机构</td>
                    <td colspan="5">
                        <c:forEach items="${dto.surveyAssignOrgs}" var="item">
                            ${item.surveyOrgName}
                                <c:if test="${item.orgSurveyState == 0}">【待接收】</c:if>
                                <c:if test="${item.orgSurveyState == 3}">【<span style="color: red;">已拒绝</span>】</c:if>
                                <c:if test="${item.orgSurveyState != 0 && item.orgSurveyState != 3}">【已接收】</c:if>
                                机构报告:(
                                <c:if test="${item.reportId != null}">
                                    <a onclick="onlinePreview('${item.commonFile.filePath}')" target="_blank">预览/下载</a>
                                </c:if>
                                <c:if test="${item.reportId == null}">未上传</c:if>)&nbsp;&nbsp;
                                <c:if test="${item.orgSurveyState == 3 || item.surveyInvestigatorCaseId == null}">
                                    <a href="javascript:void(0)" onclick="operate(${item.id},117,false)">改派</a>
                                </c:if>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:forEach>
                    </td>
                </tr>
                <c:if test="${dto.opinion != null}">
                    <tr>
                        <td><span style="color: red;">退回原因</span></td>
                        <td colspan="5">${dto.opinion}</td>
                    </tr>
                </c:if>
                <tr>
                    <td>调度审核原因</td>
                    <td colspan="5">${dto.taskDispatchRemark}</td>
                </tr>
                </tbody>
            </table>
            </form>
            <form id="editFormTask" role="form" action="${ctx}/survey/case/sic/operate" method="post">
                <input type="hidden" name="btnCode" value="updSurveyTaskMoney">
                <input type="hidden" name="surveyCaseId" id="surveyCaseId" value="" />
                <div class="table-title">任务详情</div>
                <table class="table" border="1" cellpadding="0" cellspacing="0" style="text-align: left">
                    <thead>
                        <th>调查员名称</th>
                        <th>已分配任务类型</th>
                        <th>状态</th>
                        <th>价格</th>
                        <th>报告信息</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${dto.surveyInvestigatorCases}" var="item">
                        <tr>
                            <td>${item.surveyUserName}</td>
                            <td>
                                <c:forEach items="${item.tasks}" var="task">
                                    ${task.taskName}&nbsp;
                                </c:forEach>
                            </td>
                            <td>
                                ${item.surveyStateName}
                            </td>
                            <td>
                                <div id="div_survery_11_${item.id}">基本费(${item.surveyTaskMoney})减损奖励(${item.surveryReLosses})</div>
                            </td>
                            <td>
                                <c:if test="${item.creportId == ''}">未上传</c:if>
                                <c:if test="${item.creportId != ''}">
                                    <a onclick="onlinePreview('${item.commonFile.filePath}')" target="_blank">预览/下载</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
            <div class="table-title">调查方向列表</div>
            <form id="editFormDirection" role="form" action="${ctx}/survey/case/sic/operate" method="post">
                <input type="hidden" value="updPricedirection" name="btnCode" />
                <input type="hidden" value="" name="directionId" id="directionId" />
                <input type="hidden" value="" name="updType" id="updType" />
                <table class="table" border="1" cellpadding="0" cellspacing="0" style="text-align: left">
                    <thead>
                        <th>机构</th>
                        <th>调查人</th>
                        <th>区域</th>
                        <th>任务类型</th>
                        <th>分值</th>
                        <th>方向名称</th>
                        <th>方向内容</th>
                        <th width="18%">调查方价格</th>
                        <th width="18%">委托方价格</th>
                        <th>附件</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${dto.surveyCaseDirections}" var="item">
                        <tr>
                            <td>${item.surveyInvestigatorCase.surveyFranchisee.name}</td>
                            <td>${item.surveyInvestigatorCase.surveyUserName}</td>
                            <td>${item.areaName}</td>
                            <td>${item.taskName}</td>
                            <td>${item.score}</td>
                            <td>${item.directionName}</td>
                            <td>${item.directionText}</td>
                            <td>
                                <input type="hidden" value="${item.surveyInvestigatorCaseId}" name="id" />
                                <div id="div_survey_money1${item.id}">${item.surveyMoney}
                                    <c:if test="${item.surveyPriceSource == 0}">
                                        (无来源)
                                    </c:if>
                                    <c:if test="${item.surveyPriceSource == 1}">
                                        (机构价格)
                                    </c:if>
                                    <c:if test="${item.surveyPriceSource == 2}">
                                        (默认价格)
                                    </c:if>
                                    <c:if test="${item.surveyPriceSource == 3}">
                                        (修改价格)
                                    </c:if>
                                </div>
                            </td>
                            <td>
                                <input type="hidden" value="${item.surveyInvestigatorCaseId}" name="id" />
                                <div id="div_entrust_money1${item.id}">${item.entrustMoney}
                                    <c:if test="${item.entrustPriceSource == 0}">
                                        (无来源)
                                    </c:if>
                                    <c:if test="${item.entrustPriceSource == 1}">
                                        (机构价格)
                                    </c:if>
                                    <c:if test="${item.entrustPriceSource == 2}">
                                        (默认价格)
                                    </c:if>
                                    <c:if test="${item.entrustPriceSource == 3}">
                                        (修改价格)
                                    </c:if>
                                </div>
                            </td>
                            <td><a href="javascript:void(0);" onclick="directionFiles(${item.id},'directionFiles')">详情</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
            <div class="table-title">费用清单</div>
            <table class="table" border="1" cellpadding="0" cellspacing="0" style="text-align: left">
                <tbody>
                <tr>
                    <td colspan="3" style="text-align: center;color: red;">委托方费用清单</td>
                </tr>
                <tr>
                    <th>任务类型</th>
                    <th>方向名称</th>
                    <th>价格</th>
                </tr>
                <c:forEach items="${dto.surveyFeeDetails}" var="item" varStatus="i">
                    <c:if test="${item.feeType == 1}">
                        <tr>
                            <td>${item.surveyCaseDirection.taskName}</td>
                            <td>${item.surveyCaseDirection.directionName}</td>
                            <td>${item.amount}</td>
                        </tr>
                    </c:if>
                </c:forEach>

                <tr>
                    <td colspan="3" style="text-align: center;color: red;">调查方费用清单</td>
                </tr>
                <tr>
                        <%--<th>费用类型</th>--%>
                    <th>任务类型</th>
                    <th>方向名称</th>
                    <th>价格</th>
                </tr>
                <c:forEach items="${dto.surveyFeeDetails}" var="item" varStatus="i">
                    <c:if test="${item.feeType == 2}">
                        <tr>
                                <%--<td>调查方费用</td>--%>
                            <td>${item.surveyCaseDirection.taskName}</td>
                            <td>${item.surveyCaseDirection.directionName}</td>
                            <td>${item.amount}</td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>

            <div class="table-title">跟踪信息</div>
            <c:if test="${follows != null}">
                <c:forEach items="${follows}" var="item">
                    <div class="stepItem" style="margin-left: 10px;margin-top: 20px">
                        <div class="stepDate"><fmt:formatDate value="${item.followTime}" pattern="yyyy-MM-dd HH:mm"/></div>
                        <div class="stepDot"><div class="stepDot-dot finished"></div></div>
                        <div class="stepDetail"><div class="stepDetail-title">跟踪人：${item.followUserName}
                            <br>内容：${item.contents}<br>
                            <c:if test="${item.nextFollowTime != null}">
                                下一次跟踪时间：<fmt:formatDate value="${item.nextFollowTime}" pattern="yyyy-MM-dd HH:mm"/><br/>
                            </c:if>
                            <br>
                            附件
                            <c:forEach items="${item.surveyFollowFiles}" var="f">
                                <a onclick="onlinePreview('${f.filePath}')" target="_blank">${f.fileName}</a>
                            </c:forEach>
                        </div><div class="stepDetail-detail"></div></div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${follows.size() == 0}">
                <div class="stepItem" style="margin-top: 10px;text-align:center;color: #ff0000;">
                    暂无跟踪记录
                </div>
            </c:if>


            <div class="table-title">反馈信息</div>
            <c:if test="${dto.surveyBackReplies != null}">
                <c:forEach items="${dto.surveyBackReplies}" var="item">
                    <div class="stepItem" style="margin-left: 10px;margin-top: 20px">
                        <div class="stepDate"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></div>
                        <div class="stepDot"><div class="stepDot-dot finished"></div></div>
                        <div class="stepDetail"><div class="stepDetail-title">反馈人：${item.createBy}
                            <br>内容：${item.contents}<br>
                        </div><div class="stepDetail-detail"></div></div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${dto.surveyBackReplies.size() == 0}">
                <div class="stepItem" style="margin-top: 10px;text-align:center;color: #ff0000;">
                    暂无反馈记录
                </div>
            </c:if>

        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </div>
</div>

</body>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script>
    $("#editForm").bind('submit', function(event) {
        ajaxFormSubmit(this,reload,null,null,reload);
        event.preventDefault();
    });
    $("#editFormDirection").bind('submit', function(event) {
        ajaxFormSubmit(this,reload,null,null,reload);
        event.preventDefault();
    });
    $("#editFormTask").bind('submit', function(event) {
        ajaxFormSubmit(this,reload,null,null,reload);
        event.preventDefault();
    });

    var updMoney = function(directionId,type){
        if(type == 1){
            $("#div_survey_money1" + directionId).hide();
            $("#div_survey_money2" + directionId).show();
        }else if(type == 2){
            $("#div_entrust_money1" + directionId).hide();
            $("#div_entrust_money2" + directionId).show();
        }
    }

    var operate = function(id,btnCode,ajax){
        var height = 400,width = 900;
        if(btnCode == '1500'){
            //验证报告寄送状态  验证开票状态  验证到账状态
            if("${dto.isSendReport}" == 0){
                alert("报告未寄送");return;
            }
            if("${dto.isPayEntrustFee}" == 0){
                alert("未申请开票");return;
            }
            if("${dto.isPayEntrustFee}" == 1){
                alert("开票未确认");return;
            }
            if("${dto.arrivalDate}" == null || "${dto.arrivalDate}" == ''){
                alert("开票未到账");return;
            }
        }else if(btnCode == '1200'){
            if("${dto.sourceOrgId}" ==null || "${dto.sourceOrgId}"==''){
                alert("请选择案源机构");return;
            }
        }
        if(ajax){
            var url = null,param = null;
            if(btnCode == 'sign' || btnCode == 'resign'){
                url = "${ctx}/survey/case/sic/operate";param = {"id":id,"btnCode":btnCode};
            }else if(btnCode == 'org1' || btnCode == 'org3'){//接收  审核通过
                url = "${ctx}/survey/case/operateAssign";param = {"id":id,"btnCode":btnCode};
            }else if(btnCode == 121){
                url = "${ctx}/survey/case/operate";param = {"id":id,"btnCode":btnCode,"backCaseId":"${dto.currentSurveyBackCase.id}"};
            }else{
                url = "${ctx}/survey/case/operate";param = {"id":id,"btnCode":btnCode};
            }
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    alert(e.data.msg);
                    location.reload();
                })
            }
        }else{
            var title = null,url = null;
            if(btnCode == 'score' || btnCode == 'punish'){
                title = "操作";
                url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode;
            }else if(btnCode == 'follow' || btnCode == 'backreply'){
                title = "添加跟踪";
                if(btnCode == 'backreply'){
                    title = "反馈回复"
                }
                url = "${ctx}/survey/case/addFollow?id=" + id + "&btnCode=" + btnCode;
            }else if(btnCode == 'files'){
                title = "查看资料";
                height = 600;
                url = "${ctx}/survey/case/fileMidView?surveyInfoId=" + id + "&surveyId=${dto.surveyId}&surveyCno=${dto.surveyCno}";
            }if(btnCode == 111 || btnCode == 112 || btnCode == 113 || btnCode == 115 || btnCode == 116 || btnCode == 117){
                var taskRemark = "";
                var backCaseId = "";
                if(btnCode == 112 || btnCode == 117){
                    <%--taskRemark = "${dto.surveyItem}";--%>
                    if(btnCode == 112){
                        backCaseId = "${dto.currentSurveyBackCase.id}";
                    }
                }
                if(btnCode == 113 || btnCode == 116){
                    taskRemark = "${dto.currentSurveyAssignOrg.orgTaskRemark}";
                    if(btnCode == 113){
                        backCaseId = "${dto.currentSurveyBackCase.id}";
                    }
                }
                if(btnCode == 111 || btnCode == 115){
                    <%--taskRemark = "${dto.surveyItem}";--%>
                }

                title = '分派';
                url = "${ctx}/survey/case/assignSurveyUser?id=" + id + "&btnCode=" + btnCode + "&taskRemark=" + taskRemark
                if(btnCode == 115 || btnCode == 116){
                    title = "改派";
                    url = "${ctx}/survey/case/assignSurveyUser?id=${dto.id}&btnCode=" + btnCode + "&taskRemark=" + taskRemark + "&surveyCaseId=" + id
                    if(btnCode == 116){
                        url = "${ctx}/survey/case/assignSurveyUser?id=${dto.id}&btnCode=" + btnCode + "&taskRemark=" + taskRemark + "&surveyCaseId=" + id;
                    }
                }else if(btnCode == 117){
                    title = "改派";
                    url = "${ctx}/survey/case/assignSurveyUser?id=${dto.id}&btnCode=" + btnCode + "&taskRemark=" + taskRemark + "&orgCaseId=" + id
                }else if(btnCode == 113 || btnCode == 112){
                    url = "${ctx}/survey/case/assignSurveyUser?id=" + id + "&btnCode=" + btnCode + "&taskRemark=" + taskRemark + "&backCaseId=" + backCaseId
                }
            }else if(btnCode == 1201 || btnCode == 1301 || btnCode == 1401 || btnCode == 1601
                    || btnCode == 'dispatchNo' ||btnCode == 'org2' || btnCode == 'org4'
                    || btnCode == 120 || btnCode == 122){
                height = 200;width = 700;
                title = '退回';
                url = "${ctx}/survey/case/back?id=" + id + "&btnCode=" + btnCode;
                if(btnCode == 120 || btnCode == 122){
                    url = "${ctx}/survey/case/back?id=" + id + "&btnCode=" + btnCode + "&backCaseId=${dto.currentSurveyBackCase.id}";
                }
            }else if(btnCode == 'fee'){

            }else if(btnCode == 1501){
                height = 700;width = 800;
                title  = "开票";
                url = "${ctx}/billingApply/billApplyEdit?id=" + id + "&copy=2" + "&companyName="+'${dto.entrustOrgName}' + "&companyId="+'${dto.entrustOrgId}';//此处的id是调查字表id
            }else if(btnCode == 'dispatch' || btnCode == 'progress' || btnCode == 'workflow'){
                title = "调度";
                if(btnCode == 'progress'){
                    height = 600;
                    title = "查看进度";
                }else if(btnCode == 'workflow'){
                    height = 600;
                    title = "查看时效";
                }
                url = "${ctx}/survey/case/operateView?id=" + id + "&btnCode=" + btnCode;
                if(btnCode == 'progress'){
                    url = "${ctx}/survey/case/operateView?surveyInfoId=" + id + "&btnCode=" + btnCode;
                }
            }else if(btnCode == 'primary' || btnCode == 'direction' || btnCode == 'orgPrimary'){
                title = "操作";
                url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode;
                if(btnCode == 'primary'){
                    url += "&surveyCno=${dto.surveyCno}&tsId=surveyInfoId";//tsId 特殊的ID，此处的ID为 案件字表ID
                }
                if(btnCode == 'orgPrimary'){
                    url += "&surveyCno=${dto.surveyCno}&tsId=orgAssignId";//tsId 特殊的ID  此处的id为  机构案件的ID
                }
            }else if(btnCode == 'updAssign'){
                title = "改派";
                url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode;
            }
            openDialog({
                frame:true,
                title:title,
                height:height,
                width:width,
                url:url
            });
        }
    }

    var directionOK = function(directionId,updType){
        $("#updType").val(updType);//1更改调查方价格  2更改委托方价格
        $("#directionId").val(directionId);
        return true;
    }

    var oprOK = function(id,btnCode){
        $("#btnCode").val(btnCode);
    }

    var cliUpdPrice = function(type){
        if(type == 1){
            $("#div_entrust_1").hide();
            $("#div_entrust_2").show();
        }else if(type == 2){
            $("#div_survery_1").hide();
            $("#div_survery_2").show();
        }else if(type == 3){
            $("#div_credit_money_1").hide();
            $("#div_credit_money_2").show();
        }else if(type == 4){
            $("#div_entrust_ok_1").hide();
            $("#div_entrust_ok_2").show();
        }else if(type == 5){
            $("#div_survery_11").hide();
            $("#div_survery_22").show();
        }else if(type == 6){
            $("#div_entrust_ok_3").hide();
            $("#div_entrust_ok_4").show();
        }else if(type == 7){
            $("#div_entrust_ok_5").hide();
            $("#div_entrust_ok_6").show();
        }else if(type == 8){
            $("#div_end_time_1").hide();
            $("#div_end_time_2").show();
        }
    }

    var updTaskOK = function(surveyCaseId){
        $("#surveyCaseId").val(surveyCaseId);
    }

    var cliUpdTaskPrice = function(id,type){
        $("#div_survery_11_" + id).hide();
        $("#div_survery_22_" + id).show();
    }

    var delDirection = function(id,directionId,btnCode,ajax){
        var url = "${ctx}/survey/case/sic/operate",param = {"id":id,"directionId" : directionId,"btnCode":btnCode};
        if(confirm('是否确认？')){
            ajaxSubmit(url,param,function(v,e,p){
                alert(e.data.msg);
                location.reload();
            })
        }
    }

    var updDirection = function(id,directionId,btnCode,ajax){
        var height = 400,width = 900;
        title = "操作";
        url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=direction&opr=upd&directionId=" + directionId;
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:url
        });
    }
    var directionFiles = function(id,btnCode){
        var height = 500,width = 800;
        title = "附件详情";
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:"${ctx}/survey/case/sic/directionFiles?directionId=" + id
        });
    }

    function changeOrg(){

        $("#div_source_ok_1").hide();
        $("#div_source_ok_2").show();
        $("#div_source_ok_4").show();
        ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId", {surveyCode: 'franchisee', menuType: 1,btnCode:1000}, function (v, e, p) {
            $("#sourceOrgName option").remove();
            $("#sourceOrgName").append("<option value=''>请选择</option>");
            console.log(e.data);
            for (var i = 0; i < e.data.results.length; i++) {
                var val = e.data.results[i];
                $("#sourceOrgName").append("<option value='" + val.id + ","+val.type+"'>" + val.name + "</option>");
            }
        });
    }

    function changeOrgType(obj){
        var result = obj.value.split(",");
        var franchiseeType = result[1] == '' ? 1 : result[1];
        if(franchiseeType == 1) {//直营
            $("#div_source_ok_3").show();
        }else{
            $("#div_source_ok_3").hide();
        }
        $("#sourceOrgId").val(result[0]);
    }
</script>
</body>
</html>
