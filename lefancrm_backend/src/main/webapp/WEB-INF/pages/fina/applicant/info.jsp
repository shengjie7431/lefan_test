<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>

    <style>
        .main-title{
            justify-content: space-between;
        }

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
            width: 160px;
            display: flex;
            flex-wrap: wrap;
            border: 1px solid #eee;
            background-color: #fff;
            z-index: 9;
            box-shadow: 0 0 4px 2px rgba(222, 222, 222, 1);
        }

        .ul-bars .li-bar {
            width: 160px;
            height: 36px;
            line-height: 36px;
            border-bottom: 1px solid #eee;
            color: #333;
            text-align: center;
            cursor: pointer;
        }
        .ul-bars .li-bar:hover{
            background-color: #f7f7f7;

        }
        .ul-bars .li-bar.active {
            background-color: #3BA9FF;
            color: #fff;
        }
        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }
        .new-bas-info th{
            line-height: 20px;
            padding: 10px 1% 10px 0;
            font-size: 12px;
        }
        .icons{
            margin-left: 10px;
        }
        .editShow{
            display: inline-block;
            min-width: 140px;
            cursor: pointer;
        }
        .layui-icon-add-circle{
            font-size: 20px;
            vertical-align: middle;
        }
        .layui-icon-close-fill{
            font-size: 22px;
            vertical-align: middle;
        }
        div.record{
            /*display: none!important;*/
            position: relative;
        }

        .record.active{
            margin-left: 6px;
            width: 30px;
            height: 30px;
            display: inline-flex!important;
            align-items: center;
            background-color: #fff;
            position: relative;
        }

        .record .icon-history{
            display: inline-block;
            width: 20px;
            height: 20px;
            background: url('data:image/png;base64,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');
            background-repeat: no-repeat;
            background-size: contain;
            background-position: center;

        }
        .record .num{
            position: absolute;
            top: -8px;
            right: -13px;
            width: 17px;
            height: 17px;
            line-height: 15px;
            border-radius: 15px;
            background-color: red;
            color: #FFF;
            text-align: center;
            font-size: 10px;
        }
    </style>
</head>

<body>
<div class="main main-info">
    <input type="hidden" value="${dto.id}" id="finaInfoId">
    <input type="hidden" value="${dto.caseApplicantNo}" id="caseApplicantNo">
    <input type="hidden" value="${dto.finaApplicantMoney.proposalMoney}" id="proposalMoney" name="proposalMoney">
    <div class="title main-title">

       <div>
           <button class="butList active" onclick="javascript:reload();">刷新</button>
           <button class="butList active" data-code='get-applicant-files'>查看附件(${dto.applicantFileNum})</button>
           <button class="butList active" data-code='get-applicant-track'>跟踪信息(${dto.finaApplicantTrackList.size()})</button>
           <c:if test="${dto.surveyInfoId !=null}">
               <button class="butList active" data-code='survey-case-info'>查看调查情况</button>
               <input type="hidden" value="${dto.surveyInfoId}" id="surveyInfoId" name="surveyInfoId">
           </c:if>
           <c:if test="${menuCode == 'register-list' || menuCode == 'applicant-track-list'}">
               <c:if test="${dto.applicantState == 1}">
                   <button class="butList active" onclick="edit(${dto.id},'add-info','update',null)">编辑</button>
               </c:if>
               <c:if test="${dto.finshStatementTime == null}"><%--出院结算完成时间，在此之前，可以补充垫付--%>
                   <button class="butList defuelt" onclick="edit(${dto.id},'add-info','transfer','transferCloseThree')">补充垫付</button>
               </c:if>
           </c:if>

           <c:if test="${menuCode == 'assign-list'}">
               <c:if test="${!dto.assignOrg}">
                   <button class="butList active" data-code='commit-fpjg'>分派机构</button>
               </c:if>
           </c:if>

           <c:if test="${menuCode == 'assign-org-list'}">
               <input type="hidden" value="${surveyOrgId}" id="surveyOrgId" name ="surveyOrgId">
               <c:if test="${!dto.assignUser}">
                   <button class="butList active" data-code='commit-fpdfy'>分派垫付员</button>
               </c:if>
               <c:if test="${currentUserId==5665}">
                <button class="butList defuelt" data-code='user-submit'>小程序端提交-测试</button>
               </c:if>
           </c:if>

           <c:if test="${menuCode == 'first-trial-list'}">
               <c:if test="${dto.applicantState == 2 && dto.surveyInfoId == null}">
                   <button class="butList active" data-code='to-lefan-survey'>发起乐凡调查</button>
                   <button class="butList active" data-code='first-trial' data-type="no" >退回资料收集</button>
                   <button class="butList active" data-code='refuse-to-visit'>拒绝垫付</button>
                   <button class="butList active" data-code='first-trial' data-type="yes">无需乐凡调查，审核通过</button>
               </c:if>
               <c:if test="${dto.surveyInfoId !=null}">
                   <c:if test="${dto.applicantState == 2}">
                       <button class="butList active" data-code='first-trial' data-type="yes">审核通过</button>
                   </c:if>
                   <button class="butList active" data-code='refuse-to-visit' >拒绝垫付</button>
               </c:if>
           </c:if>

           <c:if test="${menuCode == 'review-trial-list'}">
               <c:if test="${dto.applicantState == 4}">
                    <button class="butList active" data-code='review-trial' data-type="yes">审核通过</button>
               </c:if>
               <button class="butList active" data-code='refuse-to-visit' >拒绝垫付</button>
           </c:if>

           <c:if test="${menuCode == 'insurance-trial-list'}">
               <c:if test="${dto.applicantState == 5}">
                    <button class="butList active" data-code='insurance-trial' data-type="yes" >审核通过</button>
               </c:if>
               <button class="butList active" data-code='refuse-to-visit'>拒绝垫付</button>
           </c:if>

           <c:if test="${menuCode == 'applicant-track-list'}">
               <input type="hidden" value="${dto.finaApplicantInvestigator.surveyUserName}" id="surveyUserName" name ="surveyUserName">
               <c:if test="${dto.applicantState == 16}">
                   <button class="butList active" data-code='submit-visit-endCase'>确认已回访并结案</button>
               </c:if>
               <c:if test="${dto.applicantState != 16}">
                    <button class="butList active" data-code='assignation' data-type="assignOrgUser">发起现场跟踪</button>
               </c:if>
           </c:if>

           <c:if test="${menuCode == 'confirm-account-list'}">
                <c:if test="${dto.applicantState == 7}">
                    <button class="butList active" data-code='confirm-account'>确认到账</button>
                </c:if>
               <c:if test="${dto.haveAccount == 'true'}">
                   <button class="butList defuelt" data-code='confirm-account'>已确认到账</button>
               </c:if>
           </c:if>

           <c:if test="${menuCode == 'danger-list'}">
               <c:if test="${dto.caseType == 2 || dto.caseType == 3}">
                   <button class="butList active" data-code='convert-normal'>转正常案件</button>
               </c:if>
               <c:if test="${dto.finaSettlementInfo != null && dto.finaSettlementInfo.isBad == 0 && dto.finaSettlementInfo.finshClaimsTime != null}">
                   <button class="butList active" data-code='convert-bad'>转坏账处理</button>
               </c:if>
               <button class="butList active" data-code='assignation' data-type="assignOrgUser">发起现场跟踪</button>
           </c:if>
       </div>

        <div>
            <c:if test="${menuCode == 'applicant-track-list' || menuCode == 'confirm-account-list'}">
                <button class="butList active " data-code='convert-danger'>转风险案件</button>
                <button class="butList active " data-code='refuse-endCase'>拒绝垫付结案</button>
                <button class="butList active " data-code='give-up-endCase'>放弃垫付结案</button>
            </c:if>
        </div>
    </div>
   <div class="main-content">
       <div class="info">
           <div class="info-title">委托信息</div>
           <div class="info-content">
               <table class="table-1 new-bas-info" border="0" cellpadding="0" cellspacing="0">
                   <tbody>
                   <tr>
                       <td>案件编号：${dto.caseApplicantNo}</td>
                       <td>保险公司：${dto.entrustOrgName}</td>
                       <td>申请人姓名：${dto.finaUserName}</td>
                       <td>申请人身份证号：${dto.finaUserIdcard}</td>
                   </tr>
                   <tr>
                       <td>申请人手机号：${dto.finaUserTel}</td>
                       <td>被保险人姓名：${dto.insuredName}</td>
                       <td>被保险人身份证号：${dto.insuredIdcard}</td>
                       <td>被保险人年龄：${dto.insuredAge}</td>
                   </tr>
                   <tr>
                       <td>被保险人手机号：${dto.insuredTel}</td>
                       <td>申请人与被保险人关系：
                           <c:if test="${dto.relationship ==1}">本人</c:if>
                           <c:if test="${dto.relationship ==2}">投保人</c:if>
                           <c:if test="${dto.relationship ==3}">直系亲属</c:if>
                       </td>
                       <td>申请垫付金额：${dto.applyAdvanceMoney}</td>
                       <td>保单号：${dto.insurancePolicyNo}</td>
                   </tr>
                   <tr>
                       <td>保额：${dto.insureMoney}</td>
                       <td>险种：${dto.insureType}</td>
                       <td>首次投保时间：<fmt:formatDate value="${dto.firstInsureTime}" pattern="yyyy-MM-dd" /></td>
                       <td>保险期限：<fmt:formatDate value="${dto.insureStartTime}" pattern="yyyy-MM-dd" />至<fmt:formatDate value="${dto.insureEndTime}" pattern="yyyy-MM-dd" /></td>
                   </tr>
                   <tr>
                       <%--<td>免赔额：${dto.deductibleMoney}</td>--%>
                       <td>出险原因：
                           <c:if test="${dto.outInsureType ==1}">意外</c:if>
                           <c:if test="${dto.outInsureType ==2}">疾病</c:if>
                           <c:if test="${dto.outInsureType ==3}">${dto.outInsureReason}</c:if>
                       </td>
                       <td>就诊医院：${dto.hospitalName}</td>
                       <td>医院所在地：${dto.finaHospitalInfo.address}</td>
                       <td>就诊科室：${dto.department}</td>
                   </tr>
                   <tr>

                       <td>入院时间：<fmt:formatDate value="${dto.inHospitalTime}" pattern="yyyy-MM-dd" /></td>
                       <td>业务类型：
                           <c:if test="${dto.businessType ==1}">垫付</c:if>
                           <c:if test="${dto.businessType ==2}">垫付+调查</c:if>
                       </td>
                       <td>创建人：${dto.createBy}</td>
                       <td>创建时间：<fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd" /></td>
                   </tr>
                   <tr>
                       <td>免赔额：${dto.deductibleMoney}</td>
                   </tr>
                   <tr>
                       <td>
                           <span <c:if test="${menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'}">class="editShow"</c:if> data-id="${dto.finaDiagnosisTreatment.id}" data-value="${dto.finaDiagnosisTreatment.diagnosisId}" data-type="1">
                               主要诊断：${dto.finaDiagnosisTreatment.diagnosisName}<c:if test="${menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'}"><img height="25px" width="25px" src="${ctx}/img/pen.png"></c:if>
                           </span>
                       </td>
                       <td>
                           <span <c:if test="${menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'}">class="editShow"</c:if> data-id="${dto.finaDiagnosisTreatment.id}" data-value="${dto.finaDiagnosisTreatment.treatmentId}" data-type="2">
                               治疗方案：${dto.finaDiagnosisTreatment.treatmentName}<c:if test="${menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'}"><img height="25px" width="25px" src="${ctx}/img/pen.png"></c:if>
                           </span>
                       <c:if test="${menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'}">
                           <c:if test="${dto.finaDiagnosisTreatmentList.size() == 0}">
                               <span class="icons">
                                  <i class="layui-icon layui-icon-add-circle" data-type="main"></i>
                               </span>
                           </c:if>
                       </c:if>
                       </td>
                       <td></td>
                       <td></td>
                   </tr>
                   <c:forEach items="${dto.finaDiagnosisTreatmentList}" var="item" varStatus="st">
                       <tr class="otherDiagnosis">
                           <td>
                               <span <c:if test="${menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'}">class="editShow"</c:if> data-id="${item.id}" data-value="${item.diagnosisId}" data-type="1">
                                   其他诊断${st.index + 1}：${item.diagnosisName}<c:if test="${menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'}"><img height="25px" width="25px" src="${ctx}/img/pen.png"></c:if>
                               </span>
                           </td>
                           <td>
                               <span <c:if test="${menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'}">class="editShow"</c:if> data-id="${item.id}" data-value="${item.treatmentId}" data-type="2">
                                   治疗方案${st.index + 1}：${item.treatmentName}<c:if test="${menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'}"><img height="25px" width="25px" src="${ctx}/img/pen.png"></c:if>
                               </span>
                           </td>
                           <td>
                               <span <c:if test="${menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'}">class="editShow"</c:if> data-id="${item.id}" data-value="${item.diagnosisMoneyRate}" data-type="3">
                                   诊断费用比例：${item.diagnosisMoneyRate * 100}%<c:if test="${menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'}"><img height="25px" width="25px" src="${ctx}/img/pen.png"></c:if>
                               </span>
                               <c:if test="${menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'}">
                                   <span class="icons">
                                      <i class="layui-icon layui-icon-close-fill"></i>
                                      <c:if test="${dto.finaDiagnosisTreatmentList.size() == st.index +1}">
                                        <i class="layui-icon layui-icon-add-circle" data-type="other"></i>
                                      </c:if>
                                   </span>
                               </c:if>
                           </td>

                       </tr>

                   </c:forEach>
                   <tr>
                       <td>首次联系信息：${dto.firstContactDesc}</td>
                   </tr>
                   </tbody>
               </table>
           </div>
       </div>
       <div class="info">
           <form id="editFormOrg" role="form" action="${ctx}/fina/applicant/operate" method="post">
               <input type="hidden" value="${btnCode}" id="btnCode" name="btnCode">
               <input type="hidden" value="${dto.id}" id="finaInfoId" name="finaInfoId">
           <div class="info-title">垫付信息</div>
           <div class="info-content">
               <table class="table-1 new-bas-info" border="0" cellpadding="0" cellspacing="0">
                   <tbody>
                   <tr>
                       <td>案件状态：${dto.applicantStateStr}</td>
                       <td>医院收款人：${dto.finaHospitalAccount.accountName}</td>
                       <td>医院账号：${dto.finaHospitalAccount.hospitalAccount}</td>
                       <td>开户行：${dto.finaHospitalAccount.bankName}</td>
                   </tr>
                   <tr>
                       <td>
                           预估医疗费用：
                           <div id="div_estimate_money_1">${dto.finaApplicantMoney.estimateMoney}
                               <c:if test="${menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'}">
                                   <a onclick="cliUpdInfo(1)"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a>
                               </c:if>
                           </div>
                           <div id="div_estimate_money_2" style="display: none">
                               <input type="text" class="input-2" name="estimateMoney" value="${dto.finaApplicantMoney.estimateMoney}" />
                               <input type="submit" value="确定" onclick="oprOK(${dto.id},'estimate-money')" class="btn" style="display: inline-flex" />
                           </div>
                       </td>
                       <td >
                           <div class="editFXTS" data-id="${dto.finaApplicantMoney.riskLevel}">风险提示：
                               <c:if test="${dto.finaApplicantMoney.riskLevel == 0 || dto.finaApplicantMoney.riskLevel == null}">无</c:if>
                               <c:if test="${dto.finaApplicantMoney.riskLevel == 1}"><span style="color: red">有</span></c:if>
                               <c:if test="${menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'}"><img height="25px" width="25px" src="${ctx}/img/pen.png"></c:if>
                           </div>
                           <button class="butList active" data-code='risk-level-info'>详情</button>
                       </td>
                       <td>
                           建议垫付金额：
                           <div id="div_proposal_money_1">${dto.finaApplicantMoney.proposalMoney}
                               <c:if test="${menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'}">
                                   <a onclick="cliUpdInfo(3)"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a>
                               </c:if>
                           </div>
                           <div id="div_proposal_money_2" style="display: none">
                               <input type="text" class="input-2" name="proposalMoney" value="${dto.finaApplicantMoney.proposalMoney}" />
                               <input type="submit" value="确定" onclick="oprOK(${dto.id},'proposal-money')" class="btn" style="display: inline-flex" />
                           </div>
                           <c:if test="${dto.progressNum > 0}">
                               <div class="record" data-attr="get-fina-progress"><span class="icon-history"></span><span class="num">${dto.progressNum}</span></div>
                           </c:if>
                           <%--<button class="butList active" data-code='get-fina-progress'>详情</button>--%>
                       </td>
                       <td>实际放款金额：${dto.finaApplicantMoney.actualMoney}</td>
                   </tr>
                   <tr>
                       <td>放款时间：<fmt:formatDate value="${dto.finaApplicantMoney.realLoanTime}" pattern="yyyy-MM-dd" /></td>
                       <td>确认方式：
                           <c:if test="${dto.finaApplicantMoney.confirmType == 1}">电话确认</c:if>
                           <c:if test="${dto.finaApplicantMoney.confirmType == 2}">其他</c:if>
                       </td>
                       <td>审核人员：${dto.finaApplicantMoney.checkManName}</td>
                       <td>客服人员：${dto.finaApplicantMoney.customerName}</td>
                   </tr>
                   </tbody>
               </table>
           </div>
           </form>
       </div>
       <div class="info">
           <div class="info-title">机构信息</div>
           <div class="info-content">
               <table class="table-1 new-bas-info" border="0" cellpadding="0" cellspacing="0">
                   <thead style="border-bottom:1px solid #bbb">
                   <th style="width: 15%">垫付机构名称</th>
                   <th style="width: 11%">垫付任务类型</th>
                   <th style="width: 10%">分派机构日期</th>
                   <th style="width: 10%">机构提交日期</th>
                   <th style="width: 10%">机构截止日期</th>
                   <th style="width: 11%">机构时效</th>
                   <th style="width: 11%">机构状态</th>
                   <th style="width: 11%">服务费</th>
                   <th style="width: 11%">操作</th>
                   </thead>
                   <tbody>
                   <c:forEach items="${dto.finaApplicantOrgList}" var="item">
                       <tr>
                           <td style="width: 15%">${item.surveyOrgName}</td>
                           <td style="width: 11%">${item.orgTaskTypeStr}</td>
                           <td style="width: 10%"><fmt:formatDate value="${item.orgAssignTime}" pattern="yyyy-MM-dd"/></td>
                           <td style="width: 10%"><fmt:formatDate value="${item.orgSubmitTime}" pattern="yyyy-MM-dd"/></td>
                           <td style="width: 10%"><fmt:formatDate value="${item.orgEndTime}" pattern="yyyy-MM-dd"/></td>
                           <td style="width: 11%">${item.agingHtml}</td>
                           <td style="width: 11%">
                               <c:if test="${item.finaOrgState == 1}">作业中</c:if>
                               <c:if test="${item.finaOrgState == 2}">已提交</c:if>
                           </td>
                           <td style="width: 11%">${item.serviceMoney}</td>
                           <td style="width: 11%">
                               <c:if test="${menuCode == 'assign-list'  && dto.finshCollectTime == null}">
                                   <a href="javascript:void(0)" class="operate-org" data-code="assignation" data-type="updAssignOrg" data-id="${item.id}">改派</a>
                                   <a href="javascript:void(0)" class="operate-org" data-code="delete-assign-org-case" data-id="${item.id}">删除</a>
                               </c:if>
                           </td>
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
                   <thead style="border-bottom:1px solid #bbb">
                   <th style="width: 11%">垫付员</th>
                   <th style="width: 10%">垫付任务类型</th>
                   <th style="width: 10%">分派日期</th>
                   <th style="width: 10%">提交日期</th>
                   <th style="width: 10%">截止日期</th>
                   <th style="width: 10%">垫付员时效</th>
                   <th style="width: 10%">垫付员状态</th>
                   <th style="width: 9%">分值</th>
                   <th style="width: 9%">服务费</th>
                   <th style="width: 11%">操作</th>
                   </thead>
                   <tbody>
                   <c:forEach items="${dto.finaApplicantInvestigatorList}" var="item">
                       <tr>
                           <td style="width: 11%">${item.surveyUserName}</td>
                           <td style="width: 10%">${item.userTaskTypeStr}</td>
                           <td style="width: 10%"><fmt:formatDate value="${item.userAssignTime}" pattern="yyyy-MM-dd"/></td>
                           <td style="width: 10%"><fmt:formatDate value="${item.userSubmitTime}" pattern="yyyy-MM-dd"/></td>
                           <td style="width: 10%"><fmt:formatDate value="${item.orgEndTime}" pattern="yyyy-MM-dd"/></td>
                           <td style="width: 10%">${item.agingHtml}</td>
                           <td style="width: 10%">
                               <c:if test="${item.state == 1}">作业中</c:if>
                               <c:if test="${item.state == 2}">已提交</c:if>
                           </td>
                           <td style="width: 9%">${item.score}</td>
                           <td style="width: 9%">${item.serviceMoney}</td>
                           <td style="width: 11%">
                               <c:if test="${menuCode == 'assign-org-list' && dto.finshCollectTime == null}">
                                   <a href="javascript:void(0)" class="operate-org" data-code="assignation" data-type="updAssignUser" data-id="${item.id}" data-org-case-id ="${item.applicantOrgId}">改派</a>
                                   <a href="javascript:void(0)" class="operate-org" data-code="delete-assign-user-case" data-id="${item.id}" >删除</a>
                               </c:if>
                           </td>
                       </tr>
                   </c:forEach>
                   </tbody>
               </table>
           </div>
       </div>
   </div>
</div>
</body>

<script type="text/html" id="editDiagnosis">
    <div class="layui-form" style="margin-top: 30px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">诊断</label>
                <div class="layui-input-inline">
                    <div id="diagnoseIds" class="selectMul" style="width: 300px;" ></div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">治疗方案</label>
                <div class="layui-input-inline">
                    <div id="treatIds" class="selectMul" style="width: 300px;" ></div>
                </div>
            </div>
        </div>
        <div class="layui-form-item moneyRateItem" >
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">诊断费用比例</label>
                <div class="layui-input-inline ">
                    <input type="number" class="layui-input moneyRate" placeholder="请输入百分比" autocomplete="off" style="width: 300px;">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 130px;margin-top: 30px">
                <button class="layui-btn layui-btn-primary close-btn" style="width: 120px;">取消</button>
                <button class="layui-btn layui-btn-normal submit-btn" style="width: 120px;">确定</button>
            </div>
        </div>
    </div>
</script>
<script type="text/html" id="assignOrg">
    <div class="layui-form" style="margin-top: 30px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">机构名称</label>
                <div class="layui-input-inline">
                    <div id="orgIds" class="selectMul" style="width: 300px;" ></div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">任务描述</label>
                <div class="layui-input-inline ">
                    <textarea class="remark-textarea layui-textarea" id="taskDesc" placeholder="请输入"
                              style="height: 150px;width: 300px;margin:0 auto;resize:none;"></textarea>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">截止时间</label>
                <div class="layui-input-inline ">
                    <input type="text" class="time-input layui-input" id="endTime" readonly placeholder="请选择日期" autocomplete="off" style="width: 300px;">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 130px;margin-top: 30px">
                <button class="layui-btn layui-btn-primary close-btn" style="width: 120px;">取消</button>
                <button class="layui-btn layui-btn-normal submit-btn" style="width: 120px;">确定</button>
            </div>
        </div>
    </div>
</script>
<script type="text/html" id="assignSurvey">
    <div class="layui-form" style="margin-top: 30px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">垫付员</label>
                <div class="layui-input-inline">
                    <div id="surveyIds" class="selectMul" style="width: 300px;" ></div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">任务描述</label>
                <div class="layui-input-inline ">
                    <textarea class="remark-textarea layui-textarea" id="taskDesc" placeholder="请输入"
                              style="height: 150px;width: 300px;margin:0 auto;resize:none;"></textarea>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">截止时间</label>
                <div class="layui-input-inline ">
                    <input type="text" class="time-input layui-input" id="endTime" readonly placeholder="请选择日期" autocomplete="off" style="width: 300px;">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 130px;margin-top: 30px">
                <button class="layui-btn layui-btn-primary close-btn" style="width: 120px;">取消</button>
                <button class="layui-btn layui-btn-normal submit-btn" style="width: 120px;">确定</button>
            </div>
        </div>
    </div>
</script>
<script type="text/html" id="view-or-selects">
    <div class="layui-form" style="margin-top: 30px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 130px">风险提示</label>
                <div class="layui-input-inline">
                    <div id="fxtsIds" class="selectMul" style="width: 300px;" ></div>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 130px;margin-top: 30px">
                <button class="layui-btn layui-btn-primary close-btn" style="width: 120px;">取消</button>
                <button class="layui-btn layui-btn-normal submit-btn" style="width: 120px;">确定</button>
            </div>
        </div>
    </div>
</script>
<script type="text/html" id="view-or-input">
    <div class="layui-form" style="margin-top: 30px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input money-input-label" style="width: 130px;margin-left: 60px">XX</label>
                <div class="layui-input-inline _input">
                    <input type="text" class="money-input layui-input" placeholder="" autocomplete="off">                     <div class="unit">元</div>

                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 140px;margin-top: 30px">
                <button class="layui-btn layui-btn-primary close-btn" style="width: 120px;">取消</button>
                <button class="layui-btn layui-btn-normal submit-btn" style="width: 120px;">确定</button>
            </div>
        </div>
    </div>
</script>
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
                <button class="layui-btn layui-btn-primary close-btn" style="width: 120px;">取消</button>
                <button class="layui-btn layui-btn-normal submit-btn" style="width: 120px;">确定</button>
            </div>
        </div>
    </div>
</script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })
    layui.use(['jquery', 'layer', 'util', 'laydate','xmSelect'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            laydate = layui.laydate,
            xmSelect = layui.xmSelect,
            util = layui.util;

        var _heightH = $('.main-title').length ?  $('.main-title').outerHeight() : 0
        var _height = $(parent.document).outerHeight() - _heightH -90
        $('.main-content').height(_height)

        var finaInfoId = $('#finaInfoId').val()
        var caseApplicantNo = $('#caseApplicantNo').val()

        var initDate = getDate('today')

        $('body').on('click','.layui-icon-add-circle', function () {
            var _this = $(this)
            var type = _this.attr('data-type')
            showModel({
                title: '确认新增？',
                url: '${ctx}/fina/applicant/operate',
                param: {
                    finaInfoId:finaInfoId,
                    btnCode: 'diagnosis-treatment-info',
                    urgeType: 'add'
                }
            },callback)
            function callback (results){
                var _index = $('.otherDiagnosis').length +1
                var _html = '<tr class="otherDiagnosis">\n' +
                    '                           <td><span class="editShow" data-id="'+results.id+'" data-value=""  data-type="1"> 其他诊断'+_index+'：'+''+'  <img height="25px" width="25px" src="${ctx}/img/pen.png"></span></td>\n' +
                    '                           <td><span class="editShow" data-id="'+results.id+'" data-value="" data-type="2">治疗方案'+_index+'：'+''+' <img height="25px" width="25px" src="${ctx}/img/pen.png"> </span></td>\n' +
                    '                           <td><span class="editShow" data-id="'+results.id+'" data-value="0.2" data-type="3">诊断治疗费用：'+'20'+'% <img height="25px" width="25px" src="${ctx}/img/pen.png"></span>\n' +
                    '                               <span class="icons">\n' +
                    '                                  <i class="layui-icon layui-icon-close-fill"></i>\n' +
                    '                                  <i class="layui-icon layui-icon-add-circle" data-type="other"></i>\n' +
                    '                               </span>\n' +
                    '                           </td>\n'
                _this.hide()
                _this.parents('tr').after(_html)
            }

        })
        $('body').on('click','.layui-icon-close-fill', function () {
            var _this = $(this)
            showModel({
                title: '确认删除'+_this.parents('td').siblings().eq(0).html()+'?',
                url: '${ctx}/fina/applicant/operate',
                param: {
                    finaInfoId:finaInfoId,
                    btnCode: 'diagnosis-treatment-info',
                    urgeType: 'delete',
                    diagnosisTreatmentId: _this.parent().siblings().attr('data-id')
                }
            })
        })
        $('body').on('click','.editShow', function () {
            var _this = $(this)
            var editShows = _this.parents('tr')
            var param = {
                id: _this.attr('data-id'),
                value1: editShows.find('.editShow[data-type=1]').attr('data-value'),
                value2: editShows.find('.editShow[data-type=2]').attr('data-value')
            }
            if(editShows.hasClass('otherDiagnosis')){
                Object.assign(param,{
                    rate: editShows.find('.editShow[data-type=3]').attr('data-value'),
                    type: 'other'
                })
            }
            showModelEditDiagnosis(param)
        })

        $('body').on('click','.editFXTS', function () {
            var _this = $(this)
            var param = {
                id: _this.attr('data-id') || 0 ,
                btnCode: 'risk-level',
                finaInfoId: finaInfoId
            }
            showModelEditFXTS(param)
        })

        $('body').on('click','.operate-org',function () {
            var _this = $(this)
            var btnCode  = _this.attr('data-code')
            var dataType  = _this.attr('data-type')
            var _height =  $(document).height() * 0.9
            var _width = $(document).width() * 0.9

            _this.addClass('poi-no')
            setTimeout(function () {
                _this.removeClass('poi-no')
            },4000)

            if (btnCode == 'assignation' && dataType == 'updAssignOrg') {
                $.ajax({
                    url:'${ctx}/fina/applicant/ajaxData',
                    data: {
                        dataType:'get-org-case-end-time',
                        finaInfoId:finaInfoId,
                        urgeType: 'updAssignOrg',
                        applicantOrgId: _this.attr('data-id')
                    },
                    success: function (res) {
                        var res=JSON.parse(res)
                        if(res.isSuccess){
                            showModelAssignOrg({
                                btnCode:'assignation',
                                urgeType: 'updAssignOrg',
                                addKey: 'applicantOrgId',
                                addValue: _this.attr('data-id'),
                                endTime:res.results.endTimeD,
                                taskDesc:res.results.taskDesc,
                                surveyOrgId:res.results.surveyOrgId,
                                myEndTime:res.results.myEndTime
                            })
                        }

                    }
                })

            }else  if (btnCode == 'assignation' && dataType == 'updAssignUser') {
                $.ajax({
                    url:'${ctx}/fina/applicant/ajaxData',
                    data: {
                        dataType:'get-org-case-end-time',
                        finaInfoId:finaInfoId,
                        urgeType: 'updAssignUser',
                        applicantInvestigatorId: _this.attr('data-id')
                    },
                    success: function (res) {
                        var res=JSON.parse(res)
                        if(res.isSuccess){
                            showModelAssignSurvey({
                                btnCode:'assignation',
                                urgeType: 'updAssignUser',
                                addKey: 'applicantInvestigatorId',
                                addValue: _this.attr('data-id'),
                                endTime:res.results.endTimeD,
                                taskDesc:res.results.taskDesc,
                                surveyUserId:res.results.surveyUserId,
                                myEndTime:res.results.myEndTime,
                                applicantOrgId: _this.attr('data-org-case-id')
                            })
                        }

                    }
                })
            }else  if (btnCode == 'delete-assign-org-case') {
                showModel({
                    title: '确认删除？',
                    url: '${ctx}/fina/applicant/operate',
                    param: {
                        finaInfoId:finaInfoId,
                        btnCode: btnCode,
                        applicantOrgId:_this.attr('data-id')
                    }
                })
            }else  if (btnCode == 'delete-assign-user-case') {
                showModel({
                    title: '确认删除？',
                    url: '${ctx}/fina/applicant/operate',
                    param: {
                        finaInfoId:finaInfoId,
                        btnCode: btnCode,
                        applicantInvestigatorId: _this.attr('data-id')
                    }
                })
            }
        })

        $('body').on('click','.butList',function () {
            var btnCode  = $(this).attr('data-code')
            var dataType  = $(this).attr('data-type')

            var _this = $(this)
            _this.attr('disabled',true)
            setTimeout(function () {
                _this.removeAttr('disabled')
            },4000)

            var _height =  $(document).height() * 0.9
            var _width = $(document).width() * 0.9
            if (btnCode == 'commit-fpjg') {
                $.ajax({
                    url:'${ctx}/fina/applicant/ajaxData',
                    data: {
                        dataType:'get-org-case-end-time',
                        finaInfoId:finaInfoId,
                        urgeType:'assignOrg'
                    },
                    success: function (res) {
                        var res=JSON.parse(res)
                        if(res.isSuccess){
                            showModelAssignOrg({
                                btnCode:'assignation',
                                urgeType: 'assignOrg',
                                endTime:res.results.endTimeD,
                                myEndTime:res.results.myEndTime
                            })
                        }

                    }
                })

            }else  if (btnCode == 'commit-fpdfy') {
                $.ajax({
                    url:'${ctx}/fina/applicant/ajaxData',
                    data: {
                        dataType:'get-org-case-end-time',
                        finaInfoId:finaInfoId,
                        urgeType: 'assignUser'
                    },
                    success: function (res) {
                        var res=JSON.parse(res)
                        if(res.isSuccess){
                            showModelAssignSurvey({
                                btnCode:'assignation',
                                urgeType: 'assignUser',
                                endTime:res.results.endTimeD,
                                myEndTime:res.results.myEndTime,
                                taskDesc:res.results.orgTaskDesc
                            })
                        }

                    }
                })
            }else  if (btnCode == 'to-lefan-survey') {
                openDialog({
                    frame:true,
                    title:"查看调查情况",
                    height:_height,
                    width:_width,
                    url:"${ctx}/survey/case/edit?finaInfoId=" + finaInfoId + "&btnCode=" + btnCode,
                    load:true
                });
                /*showModel({
                    title: '确认发起乐凡调查？',
                    url: '${ctx}/survey/case/info',
                    param: {
                        finaInfoId:finaInfoId,
                        btnCode: btnCode,
                    }
                })*/
            }
            else  if (btnCode == 'get-applicant-files') {
                openDialog({
                    frame:true,
                    title:"查看附件",
                    height:_height,
                    width:_width,
                    url:"${ctx}/fina/applicant/operateView?finaInfoId=" + finaInfoId + "&btnCode=" + btnCode+"&opr=view&caseApplicantNo=" + caseApplicantNo,
                    load:true
                });

            }
            else  if (btnCode == 'risk-level-info') {
                openDialog({
                    frame:true,
                    title:"查看风险案件详情",
                    height:_height,
                    width:_width,
                    url:"${ctx}/fina/applicant/operateView?finaInfoId=" + finaInfoId + "&btnCode=" + btnCode,
                    load:true
                });

            }
            else  if (btnCode == 'user-submit') {
                showModel({
                    title: '确认提交？',
                    url: '${ctx}/fina/applicant/operate',
                    param: {
                        finaInfoId:finaInfoId,
                        btnCode: btnCode
                    }
                })
            }
            else  if (btnCode == 'first-trial' && dataType == 'no') {
                var params = {
                    title: '退回资料收集',
                    value: '',
                    placeholder: '请输入退回原因',
                    disabled: false,
                    url:'${ctx}/fina/applicant/operate',
                    param: {
                        finaInfoId:finaInfoId,
                        btnCode: btnCode,
                        urgeType:dataType
                    },
                    labelKeyName: 'firstReturnReason'
                }
                showModelT(params)
            }else  if (btnCode == 'refuse-to-visit') {
                var params = {
                    title: '拒绝垫付',
                    value: '',
                    placeholder: '请输入拒绝垫付原因',
                    disabled: false,
                    url:'${ctx}/fina/applicant/operate',
                    param: {
                        finaInfoId:finaInfoId,
                        btnCode: btnCode,
                    },
                    labelKeyName: 'refuseApplicant'
                }
                showModelT(params)
            }else  if (btnCode == 'first-trial' && dataType == 'yes') {
                var param = {
                    title: '确认审核通过？',
                    label: '建议垫付金额',
                    value: $("#proposalMoney").val(),
                    placeholder: '请输入',
                    disabled: false,
                    url: '${ctx}/fina/applicant/operate',
                    param: {
                        finaInfoId:finaInfoId,
                        btnCode: btnCode,
                        urgeType:dataType
                    },
                    labelKeyName: 'proposalMoney'
                }
                showModelI(param)
            }else if (btnCode == 'survey-case-info') {
                var surveyInfoId = $("#surveyInfoId").val()
                openDialog({
                    frame:true,
                    title:"查看调查情况",
                    height:_height,
                    width:_width,
                    url:"${ctx}/survey/case/info?id=" + surveyInfoId + "&menuCode=all-list&display=true",
                    load:true
                });
            }else  if (btnCode == 'review-trial' && dataType == 'yes') {
                var param = {
                    title: '确认审核通过？',
                    label: '建议垫付金额',
                    value: $("#proposalMoney").val(),
                    placeholder: '请输入',
                    disabled: false,
                    url: '${ctx}/fina/applicant/operate',
                    param: {
                        finaInfoId:finaInfoId,
                        btnCode: btnCode,
                        urgeType:dataType
                    },
                    labelKeyName: 'proposalMoney'
                }
                showModelI(param)
            }else  if (btnCode == 'insurance-trial' && dataType == 'yes') {
                var param = {
                    title: '确认审核通过？',
                    label: '建议垫付金额',
                    value: $("#proposalMoney").val(),
                    placeholder: '请输入',
                    disabled: false,
                    url: '${ctx}/fina/applicant/operate',
                    param: {
                        finaInfoId:finaInfoId,
                        btnCode: btnCode,
                        urgeType:dataType
                    },
                    labelKeyName: 'proposalMoney'
                }
                showModelI(param)
            }else if (btnCode == 'confirm-account') {
                openDialog({
                    frame:true,
                    title:"确认到账",
                    height:_height,
                    width:_width,
                    url:"${ctx}/fina/applicant/operateView?finaInfoId=" + finaInfoId + "&menuCode=${menuCode}&btnCode=" + btnCode+"&haveAccount="+${dto.haveAccount},
                    load:true
                });
            }else  if (btnCode == 'convert-danger') {
                showModel({
                    title: '确认转风险案件？',
                    url: '${ctx}/fina/applicant/operate',
                    param: {
                        finaInfoId:finaInfoId,
                        btnCode: btnCode,
                    }
                })
            }else  if (btnCode == 'refuse-endCase') {
                showModel({
                    title: '确认拒绝垫付结案？',
                    url: '${ctx}/fina/applicant/operate',
                    param: {
                        finaInfoId:finaInfoId,
                        btnCode: btnCode,
                    }
                })
            }else  if (btnCode == 'give-up-endCase') {
                showModel({
                    title: '确认放弃垫付结案？',
                    url: '${ctx}/fina/applicant/operate',
                    param: {
                        finaInfoId:finaInfoId,
                        btnCode: btnCode,
                    }
                })
            }else  if (btnCode == 'submit-visit-endCase') {
                showModel({
                    title: '确认已回访并结案？',
                    url: '${ctx}/fina/applicant/operate',
                    param: {
                        finaInfoId:finaInfoId,
                        btnCode: btnCode,
                    }
                })
            }else  if (btnCode == 'assignation'&& dataType == 'assignOrgUser') {
                var param = {
                    title: '确认发起现场跟踪？',
                    label: '垫付员',
                    value: $("#surveyUserName").val(),
                    placeholder: '请输入',
                    disabled: true,
                    url: '${ctx}/fina/applicant/operate',
                    param: {
                        finaInfoId:finaInfoId,
                        btnCode: btnCode,
                        urgeType: dataType,
                        orgTaskType: 2
                    },
                    btns:['取消','分派至调查员'],
                    noMoney: true,
                    labelKeyName: ''
                }
                showModelI(param)
            }else  if (btnCode == 'get-applicant-track') {
                openDialog({
                    frame:true,
                    title:"跟踪信息",
                    height:_height,
                    width:_width,
                    url:"${ctx}/fina/applicant/operateView/?finaInfoId=" + finaInfoId + "&btnCode=" + btnCode,
                    load:true
                });
            }else  if (btnCode == 'convert-normal') {
                showModel({
                    title: '确认转正常案件？',
                    url: '${ctx}/fina/applicant/operate',
                    param: {
                        finaInfoId:finaInfoId,
                        btnCode: btnCode,
                    }
                })
            }else  if (btnCode == 'convert-bad') {
                showModel({
                    title: '确认转坏账处理？',
                    url: '${ctx}/fina/applicant/operate',
                    param: {
                        finaInfoId:finaInfoId,
                        btnCode: btnCode,
                    }
                })
            }
        })

        $('body').on('click','.record', function () {
            var btnCode = $(this).attr('data-attr')
            var _height =  $(document).height() * 0.9
            var _width = $(document).width() * 0.9
            if (btnCode == 'get-fina-progress') {
                openDialog({
                    frame:true,
                    title:"历史记录",
                    height:_height,
                    width:_width,
                    url:"${ctx}/fina/applicant/operateView?finaInfoId=" + finaInfoId + "&btnCode=" + btnCode + "&keyCode=PROPOSAL-MONEY",
                    load:true
                });

            }

        })
        function showModel(params,callback) {
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
                                if (callback && typeof callback === "function" ){
                                    callback(res.results)
                                }else{
                                    location.reload()
                                }
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
        function showModelAssignOrg(params) {
            inputIndex = layer.open({
                type: 1,
                title: '分派机构',
                area: ['600px', '500px'],
                content: $('#assignOrg').html(),
                success: function () {
                   var demo1 = xmSelect.render({
                        el: '#orgIds',
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
                    $.ajax({
                        url: '${ctx}/fina/pub/ajaxData',
                        type: 'post',
                        data: {
                            dataType: 'franchisee-list',
                        },
                        success: function (res) {
                            var res = JSON.parse(res)
                            filterJson(demo1, res.results,'id','name',false, false)

                            if(params.surveyOrgId){
                                demo1.setValue([params.surveyOrgId])
                            }

                        }
                    })

                    endTime = laydate.render({
                        elem: '#endTime',
                        value: params.myEndTime,
//                        max: params.endTime,
                        trigger: 'click'
                    });
                    if(params.taskDesc){
                        $('#taskDesc').val(params.taskDesc)
                    }
                    $('.submit-btn').click(function () {
                        var param ={
                            btnCode:params.btnCode,
                            urgeType: params.urgeType,
                            finaInfoId: finaInfoId,
                            surveyOrgId: demo1.getValue('valueStr'),
                            endTime: $('#endTime').val() ? $('#endTime').val()+' 23:59:59' : '',
                            taskDesc: $('#taskDesc').val()
                        }
                        if (params.addKey){
                            Object.assign(param,{
                                [params.addKey]: params.addValue
                            })
                        }
                        if (!param.surveyOrgId){
                            layer.alert('请选择机构！', {
                                icon: 5
                            })
                            return;
                        }
                        if (!param.taskDesc){
                            layer.alert('请填写任务描述！', {
                                icon: 5
                            })
                            return;
                        }
                        if (!param.endTime){
                            layer.alert('请选择截止日期！', {
                                icon: 5
                            })
                            return;
                        }

                        $('.submit-btn').addClass('poi-no')
                        setTimeout(function () {
                            $('.submit-btn').removeClass('poi-no')
                        }, 6000)

                        $.ajax({
                            url: '${ctx}/fina/applicant/operate',
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
                                    $('.submit-btn').removeClass('poi-no')
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
        function showModelAssignSurvey(params) {
            inputIndex = layer.open({
                type: 1,
                title: '分派垫付员',
                area: ['600px', '500px'],
                content: $('#assignSurvey').html(),
                success: function () {
                    var demo1 = xmSelect.render({
                        el: '#surveyIds',
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
                    $.ajax({
                        url: '${ctx}/fina/pub/ajaxData',
                        type: 'post',
                        data: {
                            dataType: 'investigator-list',
                            surveyOrgId: $("#surveyOrgId").val()
                        },
                        success: function (res) {
                            var res = JSON.parse(res)
                            filterJson(demo1, res.results,'userId','realName',false, false)
                            if(params.surveyUserId){
                                demo1.setValue([params.surveyUserId])
                            }
                        }
                    })

                    endTime = laydate.render({
                        elem: '#endTime',
                        value:params.myEndTime,
                        max: params.endTime,
                        trigger: 'click'
                    });
                    if(params.taskDesc){
                        $('#taskDesc').val(params.taskDesc)
                    }
                    $('.submit-btn').click(function () {
                        var param ={
                            btnCode:params.btnCode,
                            urgeType: params.urgeType,
                            finaInfoId: finaInfoId,
                            assignUserId: demo1.getValue('valueStr'),
                            endTime: $('#endTime').val() ? $('#endTime').val()+' 23:59:59' : '',
                            taskDesc: $('#taskDesc').val()
                        }
                        if (params.addKey){
                            Object.assign(param,{
                                [params.addKey]: params.addValue
                            })
                        }

                        if (params.applicantOrgId){
                            Object.assign(param,{
                               applicantOrgId: params.applicantOrgId
                            })
                        }
                        if (!param.assignUserId){
                            layer.alert('请选择垫付员！', {
                                icon: 5
                            })
                            return;
                        }
                        if (!param.taskDesc){
                            layer.alert('请填写任务描述！', {
                                icon: 5
                            })
                            return;
                        }
                        if (!param.endTime){
                            layer.alert('请选择截止日期！', {
                                icon: 5
                            })
                            return;
                        }

                        $('.submit-btn').addClass('poi-no')
                        setTimeout(function () {
                            $('.submit-btn').removeClass('poi-no')
                        }, 6000)

                        $.ajax({
                            url: '${ctx}/fina/applicant/operate',
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
                                    $('.submit-btn').removeClass('poi-no')
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
        function showModelI(params) {
            inputIndex = layer.open({
                type: 1,
                title: params.title,
                area: ['500px', '300px'],
                content: $('#view-or-input').html(),
                success: function () {
                    $('.money-input').attr('placeholder', params.placeholder)
                    $('.money-input').val(params.value)
                    if (params.disabled) {
                        $('.money-input').attr('disabled', true).addClass('disabled')
                    }
                    if (params.btns){
                        $('.submit-btn').text(params.btns[1])
                    }
                    if (params.noMoney){
                        $('.unit').hide()
                    }

                    $('.money-input-label').html(params.label)
                    $('.submit-btn').click(function () {
                        $('.submit-btn').addClass('poi-no')
                        setTimeout(function () {
                            $('.submit-btn').removeClass('poi-no')
                        }, 6000)

                        var param = params.param
                        if (params.labelKeyName){
                            param = Object.assign(params.param,{
                                [params.labelKeyName]: $('.money-input').val(),
                            })
                        }

                        if (!checkPapers('money',$('.money-input').val()) && !params.noMoney){
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

        function showModelEditDiagnosis(params) {
            inputIndex = layer.open({
                type: 1,
                title: '编辑诊断治疗方案',
                area: ['600px', '400px'],
                content: $('#editDiagnosis').html(),
                success: function () {
                    if (!params.type){
                        $('.moneyRateItem').hide()
                    }

                    var demo1 = xmSelect.render({
                        el: '#diagnoseIds',
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
                        on: function (data) {
                            if(data.arr.length){
                                $.ajax({
                                    url: '${ctx}/fina/pub/ajaxData',
                                    type: 'post',
                                    data: {
                                        dataType: 'treatment-info-list',
                                        diagnosisId:data.arr[0].value
                                    },
                                    success: function (res) {
                                        var res = JSON.parse(res)
                                        filterJson(demo2, res.results,'id','treatmentName',false, false)
                                        if(res.results.length == 1){
                                            demo2.setValue([res.results[0].id])
                                        }else {
                                            demo2.setValue([])
                                        }

                                    }
                                })
                            }else{
                                demo2.setValue([])
                            }
                        }
                    })
                    $.ajax({
                        url: '${ctx}/fina/pub/ajaxData',
                        type: 'post',
                        data: {
                            dataType: 'diagnosis-info-list',
                        },
                        success: function (res) {
                            var res = JSON.parse(res)
                            filterJson(demo1, res.results,'id','diagnosisName',false, false)
                            demo1.setValue([params.value1])
                            demo2.setValue([params.value2])
                        }
                    })
                    var demo2 = xmSelect.render({
                        el: '#treatIds',
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

                    if (params.value1){
                        $.ajax({
                            url: '${ctx}/fina/pub/ajaxData',
                            type: 'post',
                            data: {
                                dataType: 'treatment-info-list',
                                diagnosisId:params.value1
                            },
                            success: function (res) {
                                var res = JSON.parse(res)
                                filterJson(demo2, res.results,'id','treatmentName',false, false)
                            }
                        })
                    }

                    $('.moneyRate').val(params.rate)
                    $('.submit-btn').click(function () {
                        var param ={
                            btnCode: 'diagnosis-treatment-info',
                            urgeType: 'update',
                            finaInfoId:finaInfoId,
                            diagnosisTreatmentId: params.id,
                            diagnosisId: demo1.getValue('valueStr'),
                            treatmentId: demo2.getValue('valueStr'),
                        }
                        if (!param.diagnosisId){
                            layer.alert('请选择诊断！', {
                                icon: 5
                            })
                            return;
                        }
                        if (!param.treatmentId){
                            layer.alert('请选择治疗方案！', {
                                icon: 5
                            })
                            return;
                        }
                        if (params.type == 'other'){
                            Object.assign(param,{
                                diagnosisMoneyRate: $('.moneyRate').val()
                            })
                            if (!checkPapers('num_0_1_4',param.diagnosisMoneyRate)){
                                layer.alert('请输入0-1间的小数点两位内的数字', {
                                    icon: 5
                                })
                                return;
                            }
                        }



                        $('.submit-btn').addClass('poi-no')
                        setTimeout(function () {
                            $('.submit-btn').removeClass('poi-no')
                        }, 6000)
                        $.ajax({
                            url: '${ctx}/fina/applicant/operate',
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
                                    $('.submit-btn').removeClass('poi-no')
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
        function showModelEditFXTS(params) {
            inputIndex = layer.open({
                type: 1,
                title: '编辑',
                area: ['500px', '400px'],
                content: $('#view-or-selects').html(),
                success: function () {
                    var demo1 = xmSelect.render({
                        el: '#fxtsIds',
                        theme: {
                            color: '#3BA9FF',
                        },
                        size: 'small',
                        radio: true,
                        clickClose: true,
                        toolbar: {
                            show: false,
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
                        data: [{
                            name: '有',
                            value: '1'
                        },{
                            name: '无',
                            value: '0'
                        }]
                    })
                        demo1.setValue([params.id])
                    $('.submit-btn').click(function () {
                        var param ={
                            btnCode:params.btnCode,
                            riskLevel: demo1.getValue('valueStr'),
                            finaInfoId: finaInfoId,
                        }

                        if (!param.riskLevel){
                            layer.alert('请选择风险提示！', {
                                icon: 5
                            })
                            return;
                        }

                        $('.submit-btn').addClass('poi-no')
                        setTimeout(function () {
                            $('.submit-btn').removeClass('poi-no')
                        }, 6000)

                        $.ajax({
                            url: '${ctx}/fina/applicant/operate',
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
                                    $('.submit-btn').removeClass('poi-no')
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

    function getDate(id) {
        var today = new Date(),
            y = today.getFullYear(),
            m = today.getMonth() + 1,
            d = today.getDate(),
            w = today.getDay(),
            millisecond = 1000 * 60 * 60 * 24;
        if (id == 'upMonth') {
            var y1 = y,
                m1 = m
            if (m == 1) {
                y1 = y - 1
                m1 = 12
            } else {
                m1 = m - 1
            }
            m1 = PrefixInteger(m1, 2)
            var days1 = new Date(y1, m1, 0).getDate()
            vals = [y1 + '-' + m1 + '-01', y1 + '-' + m1 + '-' + days1]
        } else if (id == 'yesterday') {
            var yesterDay = new Date(today.getTime() - millisecond);
            vals = [dateFormat(yesterDay), dateFormat(yesterDay)]
        } else if (id == 'today') {
            var m1 = PrefixInteger(m, 2)
            var d1 = PrefixInteger(d, 2)
            vals = [y + '-' + m1 + '-' + d1, y + '-' + m1 + '-' + d1]
        } else if (id == 'curWeek') {
            var minusDay = w != 0 ? w - 1 : 6;
            var monday = new Date(today.getTime() - (minusDay * millisecond));
            var sunday = new Date(monday.getTime() + (6 * millisecond));
            vals = [dateFormat(monday), y + '-' + m + '-' + d]
        } else if (id == 'curMonth') {
            vals = [y + '-' + m + '-01', y + '-' + m + '-' + d]
        } else if (id == 'all') {
            vals = ['2019-02-27', y + '-' + m + '-' + d]
        }
        return vals
    }

    function dateFormat(time, format) {
        var t = new Date(time);
        var format = format || 'yyyy-MM-dd'
        var tf = function (i) {
            return (i < 10 ? '0' : '') + i
        };
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
            switch (a) {
                case 'yyyy':
                    return tf(t.getFullYear());
                    break;
                case 'MM':
                    return tf(t.getMonth() + 1);
                    break;
                case 'mm':
                    return tf(t.getMinutes());
                    break;
                case 'dd':
                    return tf(t.getDate());
                    break;
                case 'HH':
                    return tf(t.getHours());
                    break;
                case 'ss':
                    return tf(t.getSeconds());
                    break;
            }
        })
    };

    function PrefixInteger(num, m) {
        return (Array(m).join(0) + num).slice(-m);
    }
    function filterJson(demo, newJson, id, name, flag, selected) {
        var demoList = [],
            demoValues = []
        newJson.map(function (cur) {
            var _name = name ? cur[name] : cur.name
            var _id = id ? cur[id] : cur.id
            var param = {
                name: _name,
                value: _id,
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
    //正则匹配
    var checkPapers = function (parama, paramb) {
        var map = new Map([
            ['phone', /^[1][3,4,5,7,8,9][0-9]{9}$/],
            ['passport', !/^((1[45]\d{7})|(G\d{8})|(P\d{7})|(S\d{7,8}))?$/],
            ['identity', /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/],
            ['money', /^[0-9]+(\.[0-9]{1,2})?$/],
            ['moneyorMinus', /^(\-|\+)?\d+(\.\d{1,2})?$/],
            ['moneyor4', /^(\-|\+)?\d+(\.\d{1,4})?$/],
            ['email',/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/],
            ['num_0_1',/^(0+(\.[0-9]{1,2})?|1|1.0|1.00?)$/],
            ['num_0_1_4',/^(0+(\.[0-9]{1,4})?|1|1.0|1.00|1.000|1.0000?)$/],
        ])
        if (map.get(parama).test(paramb)) {
            return true
        } else {
            return false
        }
    }


    var operate2 = function(finaInfoId,btnCode,ajax,urgeType){
        if (ajax){
            $.ajax({
                url: '${ctx}/fina/applicant/operate',
                data: {
                    finaInfoId : finaInfoId,
                    btnCode : btnCode,
                    urgeType : urgeType
                },
                success: function (res) {
                    var res = JSON.parse(res)
                    if (res.isSuccess){
                        layer.msg('操作成功', {
                            time: 1000,
                            icon: 1
                        },function () {

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
        }else{
            var title = null,url = null;
            if(btnCode == 'survey-case-info'){
                var surveyInfoId = $("#surveyInfoId").val();
                url= "${ctx}/survey/case/info?id=" + surveyInfoId + "&menuCode=all-list&display=true"
            }else{
                url= "${ctx}/fina/applicant/operateView?finaInfoId=" + finaInfoId + "&menuCode=${menuCode}&btnCode=" + btnCode
            }

            var _height = $(document).height() * 0.98;
            var _width = $(document).width() * 0.98;
            openDialog({
                frame:true,
                title:title,
                height:_height,
                width:_width,
                url:url,
                load:true
            });
        }
    }

    var edit = function(finaInfoId,btnCode,urgeType,transferCloseType){
        var _height = $(document).height() * 0.98;
        var _width = $(document).width() * 0.98;
        openDialog({
            frame:true,
            title:"新增",
            height:_height,
            width:_width,
            url:"${ctx}/fina/applicant/edit?btnCode="+btnCode+"&urgeType="+urgeType+"&finaInfoId="+finaInfoId+"&transferCloseType="+transferCloseType,
            load:true
        });
    }

    var cliUpdInfo = function(type){
        if(type == 1){
            $("#div_estimate_money_1").hide();
            $("#div_estimate_money_2").show();
        }else if(type == 2){
            $("#div_survery_1").hide();
            $("#div_survery_2").show();
        }else if(type == 3){
            $("#div_proposal_money_1").hide();
            $("#div_proposal_money_2").show();
        }
    }

    var oprOK = function(id,btnCode){

        $("#btnCode").val(btnCode);
    }

    $("#editFormOrg").bind('submit', function(event) {
        ajaxFormSubmit(this,reload,null,null,reload);
        event.preventDefault();
    });
</script>

</html>
