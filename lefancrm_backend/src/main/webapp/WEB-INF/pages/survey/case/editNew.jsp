<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: lixianfeng
  Date: 2018/10/18
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
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
    <style>
        .form {
            width: 70%;
            margin: 40px auto;
        }

        .form .form-group {
            overflow: auto;
            border-bottom: 1px solid #dadada;
            margin: 0;
            display: flex;
        }

        .form .form-group label {
            line-height: 34px;
            padding: 5px 15px;
            background-color: #eee;
            margin: 0;
        }

        .form .form-group.hide_group {
            display: none;
        }

        .form .form-group .col-sm-9 {
            line-height: 34px;
            padding: 10px;
        }

        .form .form-group .col-sm-9 div {
            z-index: 999;
        }

        .form .company {
            display: none;
        }

        .label-bars {
            width: 100%;
            display: flex;
            flex-wrap: wrap;
        }

        .label-bars .label-bar {
            padding: 0 12px;
            margin: 5px;
            height: 36px;
            line-height: 36px;
            color: #3BA9FF;
            border: 1px solid #3BA9FF;
            background-color: #fff;
            font-size: 14px;
            cursor: pointer;
        }

        .label-bars .label-bar.bType {
            display: none;
        }


        .label-bars .label-bar.active {
            color: #fff;
            background-color: #3BA9FF;
        }

        .close {
            position: relative;
            width: 4px;
            height: 30px;
            background: #ff0000;
            -webkit-transform: rotate(45deg);
            -moz-transform: rotate(45deg);
            -o-transform: rotate(45deg);
            -ms-transform: rotate(45deg);
            transform: rotate(45deg);
            display: inline-block;
        }

        .close:after {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            width: 4px;
            height: 30px;
            background: #ff0000;
            -webkit-transform: rotate(270deg);
            -moz-transform: rotate(270deg);
            -o-transform: rotate(270deg);
            -ms-transform: rotate(270deg);
            transform: rotate(270deg);
        }

        .buildSelect {
            background: #fff;
            margin: 0 5px;
            float: left;
            cursor: pointer;
            position: relative;
            display: none;
        }

        .sel_show {
            position: absolute;
            width: 100%;
            height: 24px;
            top: 0;
            left: 0;
            background: url(about:blank);
        }

        .text {
            width: 100%;
            height: 24px;
            border: 1px solid #dcdcdc;
            padding: 0 10px;
            color: #555;


        }

        .text i {
            display: inline-block;
            width: 14px;
            line-height: 23px;
            float: right;
            color: #d8d8d8;
            text-align: right;
            margin-left: 10px;
            font-size: 18px;
        }

        .on_cli {
            border-color: #333;
            color: #333;


        }

        .on_cli i {
            color: #333;
        }

        .selectList {
            width: 100%;
            display: none;
            padding: 5px 0;
            border: 1px solid #da2d29;
            border-top: none;


        }

        .selectList li {
            height: 26px;
            line-height: 26px;
            color: #555;
            padding-left: 10px;
            cursor: pointer;
        }

        .selectList li:hover {
            color: #333;
        }

        #div_btn_2 {
            width: 70%;
            margin: 0 auto;
        }

        .isZhongan {
            display: none;
        }

        .poi-no {
            pointer-events: none;
        }
    </style>

</head>
<body id="editBody">
<div class="form">
    <form id="editForm" role="form" action="${ctx}/survey/case/save" method="post">
        <input type="hidden" value="${dto.id}" name="id" id="id"/>
        <input type="hidden" value="${obj}" name="obj" id="obj"/>
        <input type="hidden" value="${oprType}" name="oprType"/>
        <input type="hidden" value="${orgAttr}" name="orgAttr"/>
        <input type="hidden" value="${dto.surveyRiskCase.modelId}" id="modelId" name="modelId">
        <input type="hidden" value="${btnCode}" name="btnCode" id="btnCode"/>
        <input type="hidden" value="${nextTransferType}" name="nextTransferType"/><%--下一个“二调”的类型--%>
        <input type="hidden" value="${topSurveyId}" name="topSurveyId"/><%--“发起二调”的顶级 调查id--%>
        <input type="hidden" value="${dto.surveyConsignorReportRule}" name="one" id="one"/>
        <input type="hidden" value="${dto.surveyConsignorReportRule.oneRule}" name="oneRule" id="oneRule"/>
        <input type="hidden" value="${dto.surveyConsignorReportRule.twoRule}" name="twoRule" id="twoRule"/>
        <input type="hidden" value="${dto.surveyConsignorReportRule.threeRule}" name="threeRule" id="threeRule"/>
        <input type="hidden" value="${dto.surveyConsignorReportRule.fourRule}" name="fourRule" id="fourRule"/>
        <input type="hidden" value="${dto.surveyConsignorReportRule.oneRuleStr}" name="oneRuleStr" id="oneRuleStr"/>
        <input type="hidden" value="${dto.surveyConsignorReportRule.twoRuleStr}" name="twoRuleStr" id="twoRuleStr"/>
        <input type="hidden" value="${dto.surveyConsignorReportRule.threeRuleStr}" name="threeRuleStr"
               id="threeRuleStr"/>
        <input type="hidden" value="${dto.surveyConsignorReportRule.fourRuleStr}" name="fourRuleStr" id="fourRuleStr"/>
        <input type="hidden" value="${dto.surveyRiskCase.entrustOrgName}" name="entrustOrgName" id="entrustOrgName"/>
        <input type="hidden" value='${params.consignorJson}' id="consignorJson"/>
        <input type="hidden" name="surveyArea" id="surveyAreas"/>

        <div id="div_1"
             <c:if test="${menuCode == 'dcy-list'}">style="${surveyInvestigatorCaseDto.creportDate != null ? 'pointer-events: none;' : ''}"</c:if> >
            <c:if test="${obj == 'agent' || btnCode == 'transfer'}">
                <c:if test="${obj == 'agent'}">
                    <div class="form-group 1111">
                        <label class="col-sm-3 control-label">保险公司<em style="color: red;">*</em></label>
                        <div class="col-sm-9">
                            <c:if test="${btnCode != 'to-lefan-survey'}">
                                <select name="entrustOrg" id="selectCompany" class="singleSelect form-control"
                                        onchange="onchange_selectCompany(this.value)"
                                        <c:if test="${obj == 'agent'}">required="required"</c:if>>
                                    <option value="">请选择保险公司</option>
                                    <c:forEach items="${consignors}" var="item">
                                        <option value="${item.id}"
                                                <c:if test="${dto.surveyRiskCase.entrustOrgId == item.id}">selected="selected" </c:if>>${item.name}</option>
                                    </c:forEach>
                                </select>
                            </c:if>
                            <c:if test="${btnCode == 'to-lefan-survey'}">
                                <input type="text" class="form-control updateCaseNo" name="entrustOrgName"
                                       value="${dto.surveyRiskCase.entrustOrgName}" required="required"
                                       disabled="disabled" style="cursor: pointer">
                                <input type="hidden" value="${dto.surveyRiskCase.entrustOrgId}" name="entrustOrgId"
                                       id="entrustOrgId"/>
                                <input type="hidden" value="${finaInfoId}" name="finaInfoId"
                                       id="finaInfoId"/> <%--垫付案件的id--%>
                            </c:if>
                        </div>
                    </div>
                </c:if>
                <c:if test="${btnCode == 'transfer'}">
                    <div class="form-group 2222">
                        <label class="col-sm-3 control-label">保险公司<em style="color: red;">*</em></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control updateCaseNo" name="entrustOrgName"
                                   value="${dto.surveyRiskCase.entrustOrgName}" required="required" disabled="disabled"
                                   style="cursor: pointer">
                            <input type="hidden" value="${dto.surveyRiskCase.entrustOrgId}" name="entrustOrgId"
                                   id="entrustOrgId"/>
                        </div>
                    </div>
                </c:if>

                <div class="form-group">
                    <label class="col-sm-3 control-label">部门<em style="color: red;">*</em></label>
                    <div class="col-sm-9">
                        <select name="entrustDepartment" id="entrustDepartment" class="form-control" required="required"
                                onchange="changeEntrustDepartment()">
                            <option value="">请选择部门</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">委托人<em style="color: red;">*</em></label>
                    <div class="col-sm-9">
                        <select name="entrustUser" id="entrustUser" class="form-control select" required="required">
                            <option value="">请选择委托人</option>
                        </select>
                    </div>
                </div>
            </c:if>
            <div class="form-group" style="width: 100%">
                <div id="divSport" style="display: none;width: 100%">
                    <div style="width: 100%">
                        <textarea id="spotText" class="form-control" rows="25"
                                  placeholder="粘贴内容，自动填充信息"></textarea>
                        <input style="float: right;margin: 2px;" type="button" value="智能识别" onclick="spot()"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">被调查人<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control updateCaseNo" name="surveyPerson" id="surveyPerson"
                           value="${dto.surveyRiskCase.surveyPerson}" placeholder="请输入被调查人姓名"
                           required="required">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">联系号码<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="surveryPersonTel" id="surveryPersonTel"
                           value="${dto.surveyRiskCase.surveryPersonTel}" placeholder="请输入被调查人联系号码"
                           required="required">
                </div>
            </div>

            <div class="tr_formerCase1" style="display: none; text-align: center; color: red; padding-top:6px">
                重复案件
            </div>
            <div class="tr_formerCase1" style="display: none; padding: 10px">
                <table id="tab_temp" class="table" border="1" cellpadding="0" cellspacing="0" style="text-align: left">
                    <thead>
                    <tr>
                        <th width="15%">调查编号</th>
                        <th width="15%">调查人</th>
                        <th width="15%">联系方式</th>
                        <th width="30%">保险公司</th>
                        <th width="10%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">证件类型<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <input type="hidden" name="idType" value="${dto.surveyRiskCase.idType}"/>
                    <c:if test="${dto.surveyRiskCase.idType == 1 || dto.surveyRiskCase.idType == null }">
                        <div class="label-bars idType" id="idType" data-value="1">
                            <div class="label-bar active" data-id="1">身份证号</div>
                            <div class="label-bar " data-id="3">护照号码</div>
                            <div class="label-bar " data-id="4">其它证件号码</div>
                        </div>
                    </c:if>
                    <c:if test="${dto.surveyRiskCase.idType == 3}">
                        <div class="label-bars idType" id="idType" data-value="3">
                            <div class="label-bar " data-id="1">身份证号</div>
                            <div class="label-bar active" data-id="3">护照号码</div>
                            <div class="label-bar " data-id="4">其它证件号码</div>
                        </div>
                    </c:if>
                    <c:if test="${dto.surveyRiskCase.idType == 4}">
                        <div class="label-bars idType" id="idType" data-value="4">
                            <div class="label-bar " data-id="1">身份证号</div>
                            <div class="label-bar " data-id="3">护照号码</div>
                            <div class="label-bar active" data-id="4">其它证件号码</div>
                        </div>
                    </c:if>

                    <%--<input type="text" class="form-control" name="idNumber" id="idNumber" value="${dto.surveyRiskCase.idNumber}" onblur="selectCase()" placeholder="请输入被调查人身份证号" required="required">--%>
                    <%-- <c:if test="${btnCode == 'transfer'}">
                         <input type="text" name="idNumber" id="idNumber" value="${dto.surveyRiskCase.idNumber}" class="form-control" readonly="true" placeholder="请输入被调查人身份证号" style="cursor: pointer">
                     </c:if>
                     <c:if test="${btnCode != 'transfer'}">
                         <input type="text" name="idNumber" id="idNumber" value="${dto.surveyRiskCase.idNumber}" class="form-control" onblur="selectCase()" placeholder="请输入被调查人身份证号" required="required">
                     </c:if>--%>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">证件号码<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <%--<input type="text" class="form-control" name="idNumber" id="idNumber" value="${dto.surveyRiskCase.idNumber}" onblur="selectCase()" placeholder="请输入被调查人身份证号" required="required">--%>
                    <c:if test="${btnCode == 'transfer'}">
                        <input type="text" name="idNumber" id="idNumber" value="${dto.surveyRiskCase.idNumber}"
                               class="form-control" readonly="true" placeholder="请输入被调查人身份证号"
                               style="cursor: pointer">
                    </c:if>
                    <c:if test="${btnCode != 'transfer'}">
                        <input type="text" name="idNumber" id="idNumber" value="${dto.surveyRiskCase.idNumber}"
                               class="form-control" onblur="selectCase()" placeholder="请输入被调查人身份证号"
                               required="required">
                    </c:if>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">年龄<em style="color: red;">*</em></label>
                <div class="col-sm-9 input-group">
                    <input type="text" class="form-control" id="age" name="age" value="${dto.surveyRiskCase.age}"
                           onblur="selectHzClaimsMoney()" placeholder="请输入被调查人年龄" required="required">
                    <div class="input-group-addon">周岁</div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">性别<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <select name="sex" id="sex" class="form-control select" required="required">
                        <option value="">请选择</option>
                        <option value="1" <c:if test="${dto.surveyRiskCase.sex == 1}">selected="selected"</c:if>>男
                        </option>
                        <option value="2" <c:if test="${dto.surveyRiskCase.sex == 2}">selected="selected"</c:if>>女
                        </option>
                    </select>
                </div>
            </div>

            <div class="form-group" style="display: none;" name="divShow">
                <label class="col-sm-3 control-label">合作公司</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control updateCaseNo" name="cooperativeCompany"
                           id="cooperativeCompany"
                           value="${dto.surveyRiskCase.cooperativeCompany}" placeholder="请输入合作公司">
                </div>
            </div>
            <div class="form-group" style="display: none;" name="divShow">
                <label class="col-sm-3 control-label">分公司</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control updateCaseNo" name="subsidiaryCompany" id="subsidiaryCompany"
                           value="${dto.surveyRiskCase.subsidiaryCompany}" placeholder="请输入分公司">
                </div>
            </div>
            <div class="form-group" style="display: none;" name="divShow">
                <label class="col-sm-3 control-label">健康险公司</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control updateCaseNo" name="healthInsuranceCompany"
                           id="healthInsuranceCompany"
                           value="${dto.surveyRiskCase.healthInsuranceCompany}" placeholder="请输入健康险公司">
                </div>
            </div>
            <div class="form-group" style="display: none;" name="divShow">
                <label class="col-sm-3 control-label">任务号</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control updateCaseNo" name="taskNumber" id="taskNumber"
                           value="${dto.surveyRiskCase.taskNumber}" placeholder="请输入任务号">
                </div>
            </div>

            <div class="form-group" style="display: none;" name="divShow67">
                <label class="col-sm-3 control-label">委托方式</label>
                <div class="col-sm-9">
                    <select name="delegationMode" id="delegationMode" class="form-control select">
                        <option value="">请选择</option>
                        <option value="1"
                                <c:if test="${dto.surveyRiskCase.delegationMode == 1}">selected="selected"</c:if>>
                            系统委托
                        </option>
                        <option value="2"
                                <c:if test="${dto.surveyRiskCase.delegationMode == 2}">selected="selected"</c:if>>
                            邮件提调
                        </option>
                    </select>
                </div>
            </div>

            <%--中宏模板--%>
            <div class="form-group company company_zh" data-type="4">
                <label class="col-sm-3 control-label">理赔编号<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <input type="text" id="policyNo" name="policyNo" value="${dto.surveyRiskCase.policyNo}"
                           placeholder="请输入理赔编号" class="form-control updateCaseNo editInfo">
                </div>
            </div>

            <%--正言、乐凡模板--%>
            <div class="form-group company company_lf" data-type="1">
                <label class="col-sm-3 control-label">保险种类</label>
                <div class="col-sm-9">
                    <input type="text" id="insureName" name="insureName" value="${dto.surveyRiskCase.insureName}"
                           placeholder="请输入保险种类" class="form-control editInfo">
                    <div id="insureBars" class="label-bars" data-name="none">
                        <div class="label-bar">身份证号</div>
                        <div class="label-bar">护照号码</div>
                        <div class="label-bar">其它证件号码</div>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label">主险代码<em style="color: red;">*</em></label>
                <div class="col-sm-9 input-group">
                    <input type="text" class="form-control" id="mainInsurance" name="mainInsurance" value="${dto.surveyRiskCase.mainInsurance}"
                            placeholder="请输入主险代码" required="required">
                </div>
            </div>


            <div class="form-group company company_lf" data-type="1">
                <label class="col-sm-3 control-label">理赔编号<em style="color: red;">*</em></label>
                <div class="col-sm-3">
                    <input type="text" name="claimsNo" id="claimsNo" value="${dto.surveyRiskCase.claimsNo}"
                           placeholder="请输入理赔编号" class="form-control updateCaseNo editInfo">
                </div>

                <label class="col-sm-2 control-label">调查号</label>
                <div class="col-sm-2">
                    <input type="text" name="hzContactName" id="lfhzContactName"
                           value="${dto.surveyRiskCase.hzContactName}" placeholder="调查号"
                           class="form-control">
                </div>
                <label class="col-sm-1 control-label">派案人</label>
                <div class="col-sm-1">
                    <input type="text" name="hzContactTel" id="lfhzContactTel"
                           value="${dto.surveyRiskCase.hzContactTel}" placeholder="派案人"
                           class="form-control">
                </div>
            </div>

            <%--中德模板--%>
            <div class="form-group company company_zd" data-type="3">
                <label class="col-sm-3 control-label">理赔编号<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <input type="text" id="policyNo4" name="policyNo4" value="${dto.surveyRiskCase.policyNo}"
                           placeholder="请输入理赔编号" class="form-control updateCaseNo editInfo">
                </div>
            </div>
            <div class="form-group company company_zd" data-type="3">
                <label class="col-sm-3 control-label">保单生效日<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <input type="text" id="insureTakeTime" name="insureTakeTime" class="form-control editInfo"
                           placeholder="请选择保单生效日期" style="cursor: auto; background-color:#fff"
                           value="<fmt:formatDate value="${dto.surveyRiskCase.insureTakeTime}" pattern="yyyy-MM-dd"/>"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                </div>
            </div>

            <%--互助模板--%>
            <div class="form-group company company_hz" data-type="5">
                <label class="col-sm-3 control-label">联系人<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control editInfo" name="hzContactName" id="hzContactName"
                           value="${dto.surveyRiskCase.hzContactName}" placeholder="请输入联系人姓名">
                </div>
            </div>
            <div class="form-group company company_hz" data-type="5">
                <label class="col-sm-3 control-label">联系人号码<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control editInfo" name="hzContactTel" id="hzContactTel"
                           value="${dto.surveyRiskCase.hzContactTel}" placeholder="请输入联系人号码">
                </div>
            </div>
            <div class="form-group company company_hz" data-type="5">
                <label class="col-sm-3 control-label">互助产品<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <div class="label-bars" id="hzProducts" name="hzProducts" data-value="">
                        <div class="label-bar" data-id="1">重大疾病</div>
                        <div class="label-bar" data-id="2">老年防癌</div>
                        <div class="label-bar" data-id="3">中青年互助计划</div>
                        <div class="label-bar" data-id="4">老年互助计划</div>
                        <div class="label-bar" data-id="5">百年终生互助计划</div>
                        <div class="label-bar" data-id="6">少儿互助计划</div>
                    </div>
                </div>
                <input type="hidden" value="${dto.surveyRiskCase.hzProduct}" id="hzProduct" name="hzProduct"
                       required="required"/>
            </div>
            <div class="form-group company company_hz" data-type="5">
                <label class="col-sm-3 control-label">互助案件编号<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control updateCaseNo editInfo" name="claimsNoHz" id="claimsNoHz"
                           value="${dto.surveyRiskCase.claimsNo}" placeholder="请输入互助案件编号">
                </div>
            </div>
            <div class="form-group company company_hz" data-type="5">
                <label class="col-sm-3 control-label">加入日期<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <input type="text" id="insureTime5" class="form-control editInfo" placeholder="请选择加入日期"
                           style="cursor: auto ; background-color:#fff"
                           value="<fmt:formatDate value="${dto.surveyRiskCase.insureTime}" pattern="yyyy-MM-dd"/>"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                </div>
            </div>
            <div class="form-group company company_hz" data-type="5">
                <label class="col-sm-3 control-label">等待期截止日期<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <input type="text" id="hzWaitEndTime" name="hzWaitEndTime" class="form-control editInfo"
                           placeholder="请选择等待期截止日期" style="cursor: auto; background-color:#fff"
                           value="<fmt:formatDate value="${dto.surveyRiskCase.hzWaitEndTime}" pattern="yyyy-MM-dd"/>"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                </div>
            </div>
            <div class="form-group company company_hz" data-type="5">
                <label class="col-sm-3 control-label">出险日期<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <input type="text" id="dangerTime5" autocomplete="off" class="form-control editInfo"
                           placeholder="请选择出险日期" style="cursor: auto; background-color:#fff"
                           value="<fmt:formatDate value="${dto.surveyRiskCase.dangerTime}" pattern="yyyy-MM-dd"/>"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                </div>
            </div>
            <div class="form-group company company_hz" data-type="5">
                <label class="col-sm-3 control-label">互助金额</label>
                <div class="col-sm-9 input-group">
                    <input type="text" class="form-control editInfo" step="0.01" id="claimsMoney" name="claimsMoney"
                           value="${dto.surveyRiskCase.claimsMoney}" placeholder="请输入互助金额">
                    <div class="input-group-addon">元</div>
                </div>
            </div>
            <div class="form-group company company_hz" data-type="5">
                <label class="col-sm-3 control-label">常住地点<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control editInfo" id="hzLiveAddress" name="hzLiveAddress"
                           value="${dto.surveyRiskCase.hzLiveAddress}" placeholder="请输入常住地点">
                </div>
            </div>
            <%--            <div class="form-group company company_hz" data-type="5">--%>
            <%--                <label class="col-sm-3 control-label">出险地点<em style="color: red;">*</em></label>--%>
            <%--                <div class="col-sm-9">--%>
            <%--                    <input type="text" class="form-control editInfo" id="dangerAddress5" value="${dto.surveyRiskCase.dangerAddress}" placeholder="请输入出险地点">--%>
            <%--                </div>--%>
            <%--            </div>--%>
            <div class="form-group company company_hz" data-type="5">
                <label class="col-sm-3 control-label">确诊疾病<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control editInfo" id="hzConfirmDisease" name="hzConfirmDisease"
                           value="${dto.surveyRiskCase.hzConfirmDisease}" placeholder="请输入确诊疾病">
                </div>
            </div>
            <div class="form-group company company_hz" data-type="5">
                <label class="col-sm-3 control-label">首次跟踪信息<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <%--<input type="text" class="form-control" id="hzFollowInfo" name="hzFollowInfo" value="${dto.surveyRiskCase.hzFollowInfo}"  placeholder="请输入首次跟踪信息">--%>
                    <textarea name="hzFollowInfo" id="hzFollowInfo" class="form-control editInfo" cols="30" rows="6"
                              placeholder="请输入首次跟踪信息">${dto.surveyRiskCase.hzFollowInfo}</textarea>
                </div>
            </div>


            <div class="form-group">
                <label class="col-sm-3 control-label">案件基本信息</label>
                <div class="col-sm-9">
                    <textarea id="surveyInfo" name="surveyInfo" class="form-control" cols="30" rows="6"
                              placeholder="请输入案件基本信息">${dto.surveyInfo}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">调查事项</label>
                <div class="col-sm-9">
                    <textarea id="surveyItem" name="surveyItem" class="form-control" cols="30" rows="6"
                              placeholder="请输入调查事项（多事项建议换行并加序号输入）">${dto.surveyItem}</textarea>
                </div>
            </div>

            <c:if test="${menuCode != 'dcy-list'}">
                <div class="form-group">
                    <label class="col-sm-3 control-label">领域<em style="color: red;">*</em></label>
                    <div class="col-sm-9">
                        <div class="label-bars surveyBusIds" id="surveyBusIds">
                            <c:forEach items="${buses}" var="item">
                                <div class="label-bar" data-id="${item.id}" data-type="${item.type}">${item.name}</div>
                            </c:forEach>

                        </div>
                    </div>
                    <input type="hidden" value="${dto.surveyBusId}" id="surveyBusId" name="surveyBusId"
                           required="required"/>
                    <input type="hidden" value="${dto.surveyBusName}" id="surveyBusName" name="surveyBusName"/>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">业务类型<em style="color: red;">*</em></label>
                    <div class="col-sm-9">
                        <div class="label-bars servicesIds" id="servicesIds">
                            <c:forEach items="${services}" var="item">
                                <div class="label-bar noZhongan" data-id="${item.id}">${item.name}</div>
                            </c:forEach>

                            <div class="label-bar isZhongan" data-id="12" data-sub-service-id="2">核对调阅类</div>
                            <div class="label-bar isZhongan" data-id="13" data-sub-service-id="3">一般检索类</div>
                            <div class="label-bar isZhongan" data-id="13" data-sub-service-id="4">特殊检索类</div>
                            <div class="label-bar isZhongan" data-id="13" data-sub-service-id="5">疑难侦察类</div>
                            <div class="label-bar isZhongan" data-id="13" data-sub-service-id="6">攻坚侦察类</div>
                        </div>
                    </div>
                    <input type="hidden" value="${dto.servicesId}" id="servicesId" name="servicesId"
                           required="required"/>
                    <input type="hidden" value="${dto.subServiceId}" id="subServiceId" name="subServiceId"
                           required="required"/>
                    <input type="hidden" value="${dto.servicesName}" id="servicesName" name="servicesName"/>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">结算方式<em style="color: red;">*</em></label>
                    <div class="col-sm-9">
                        <div class="label-bars payTypes" id="payTypes">
                            <div class="label-bar bType bType1 bType3 bbb1" data-id="3">任务</div>
                            <div class="label-bar bType bType2 bType3 bbb2" data-id="2">基本+减损</div>
                            <div class="label-bar bType bType1 bType2 bbb3" data-id="1">一口价</div>
                        </div>
                    </div>
                    <input type="hidden" value="${dto.payType}" id="payType" name="payType" required="required"/>

                </div>


                <div class="form-group hide_group ykj jbjs">
                    <label class="col-sm-3 control-label">付费价格<em style="color: red;">*</em></label>
                    <div class="col-sm-9 input-group">
                        <input type="number" step="0.01" id="entrustMoney" name="entrustMoney"
                               value="${dto.entrustMoney}" class="form-control">
                        <div class="input-group-addon">元</div>
                    </div>
                </div>
                <!-- 基本 + 减损 -->
                <%--<div class="form-group hide_group jbjs">
                    <label class="col-sm-3 control-label">减损描述<em style="color: red;">*</em></label>
                    <div class="col-sm-9">
                        <input type="text" id="entrustReLossesRemark" name="entrustReLossesRemark" value="${dto.entrustReLossesRemark}" class="form-control">
                    </div>
                </div>--%>

                <div class="form-group businessShow hide_group zh_show">
                    <label class="col-sm-3 control-label">投保日期</label>
                    <div class="col-sm-9">
                        <input id="insureTime" name="insureTime" autocomplete="off" type="text" class="form-control"
                               placeholder="请选择投保日期" style="cursor: auto; background-color:#fff"
                               value="<fmt:formatDate value="${dto.surveyRiskCase.insureTime}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                    </div>
                </div>
                <div class="form-group businessShow hide_group">
                    <label class="col-sm-3 control-label">出险日期</label>
                    <div class="col-sm-9">
                        <input id="dangerTime" name="dangerTime" autocomplete="off" type="text" class="form-control"
                               placeholder="请选择出险日期" style="cursor: auto; background-color:#fff"
                               value="<fmt:formatDate value="${dto.surveyRiskCase.dangerTime}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'}) ">
                    </div>
                </div>
                <div class="form-group businessShow hide_group">
                    <label class="col-sm-3 control-label">出险地点</label>
                    <div class="col-sm-9">
                        <input type="text" name="dangerAddress" id="dangerAddress"
                               value="${dto.surveyRiskCase.dangerAddress}" class="form-control"
                               placeholder="请输入出险地点">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">委托时间<em style="color: red;">*</em></label>
                    <div class="col-sm-9">
                        <input id="entrustTime" name="entrustTime" type="text" class="form-control" required="required"
                               style="cursor: auto; background-color:#fff"
                               value="<fmt:formatDate value="${dto.surveyRiskCase.entrustTime}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicking:function(dp) {
                            ddd(dp)
                           }})" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">调查区域<em style="color: red;">*</em></label>
                    <div class="col-sm-9">
                        <div class="label-bars surveyArea" id="surveyArea">
                            <div class="label-bar" data-id="1">直辖市(市区)</div>
                            <div class="label-bar" data-id="2">直辖市(郊区)</div>
                            <div class="label-bar" data-id="3">省会</div>
                            <div class="label-bar" data-id="4">地级市</div>
                            <div class="label-bar" data-id="5">县级市</div>
                        </div>
                    </div>
                        <%--                <input type="hidden" value="${dto.payType}" id="" name="payType" required="required"/>--%>

                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">调查截止时间<em style="color: red;">*</em></label>
                    <div class="col-sm-9">
                        <input id="endTime" name="endTime" type="text" class="form-control" required="required"
                               placeholder="请选择调查截止时间"
                               style="cursor: auto; background-color:#fff"
                               value="<fmt:formatDate value="${dto.endTime}" pattern="yyyy-MM-dd 23:59:59"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',minDate :'<fmt:formatDate
                                       value="${dto.minEndDate}" pattern="yyyy-MM-dd 23:59:59"/>'})" readonly>
                    </div>
                </div>

                <div class="form-group showByDomains">
                    <label class="col-sm-3 control-label">任务类型<em style="color: red;">*</em></label>
                    <div class="col-sm-9">
                        <div class="label-bars" id="tasks" data-name="multiple">
                            <c:forEach items="${tasks}" var="item">
                                <c:if test="${item.type ==2}">
                                    <div class="label-bar" data-id="${item.id}">${item.name}</div>
                                </c:if>
                            </c:forEach>
                            <input type="hidden" value="${dto.surveyTaskTypes}" id="chkTaskIds" name="chkTaskIds"/>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="form-group">
                <label class="col-sm-3 control-label">案件编号<em style="color: red;">*</em></label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="surveyCaseNo" name="surveyCaseNo"
                           value="${dto.surveyRiskCase.surveyCaseNo}" placeholder="请输入案件编号" required="required">
                </div>
            </div>
        </div>

        <div id="div_btn_1" class="modal-footer">
            <c:if test="${dto.id == null}">
                <button type="submit" name="btnUpload" onclick="return toValid()" class="btn btn-success loading-btn"
                        data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>下一步
                </button>
            </c:if>
            <c:if test="${dto.id != null}">
                <c:if test="${surveyInvestigatorCaseDto.creportDate == null && menuCode == 'dcy-list'}">
                    <button type="submit" name="btnUpload" onclick="return toValid()"
                            class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span
                            class="glyphicon glyphicon-ok"></span>重新提交
                    </button>
                </c:if>
            </c:if>
        </div>
    </form>
    <div id="div_2" style="display: none" class="form-group">
        <jsp:include page="fileMid.jsp"></jsp:include>
    </div>
    <div id="div_btn_2" style="display: none" class="modal-footer">
        <c:if test="${obj != 'agent'}">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js"
                    onclick="javascript:closeDialog();">关闭
            </button>
        </c:if>
        <c:if test="${obj == 'agent'}">
            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="operate(1200,true)">
                受理通过并关闭
            </button>
            <button type="button" class="btn btn-success btn btn-default" data-dismiss="modal"
                    onclick="operate(1300,true)">受理通过并分派调查员
            </button>
        </c:if>
    </div>
</div>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">var ctx = "${ctx}";</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
<script type="text/javascript">

    var isZhongAnCase = false;
    var entrustOrgId;

    function ddd(dp) {
        var orgAttr = $("input[name=orgAttr]").val();
        if (orgAttr == null || orgAttr == '' || orgAttr == 1) {//保司
            //业务类型
            var servicesIds = $("#servicesIds").attr("data-value");
            var sArea = $("#surveyArea").attr("data-value");
            if (isZhongAnCase) {
                deadline2(servicesIds, sArea, $("#surveyArea"), dp.cal.getNewDateStr())
            } else {
                // deadline(servicesIds,sArea,dp.cal.getNewDateStr() );
            }
        }
        getEndTime({"entrustTime": dp.cal.getNewDateStr()});
        <%--if (orgAttr == 2){//互助--%>
        <%--  var param = {--%>
        <%--      entrustTime:dp.cal.getNewDateStr(),--%>
        <%--      entrustOrgId: $("#selectCompany").val(),--%>
        <%--      serviceId: $("#servicesIds").attr('data-value'),--%>
        <%--      surveyCode: "entrustEndTime",--%>
        <%--      btnCode: 1000--%>
        <%--  }--%>
        <%--  $.post("${ctx}/baseSurvey/selectInfoByRelationId",param,function (res,e) {--%>
        <%--      $("#endTime").val(e.data.results);--%>
        <%--  })--%>

        <%--}--%>
    }

    $(function () {
        $('.singleSelect').select2();


        //
        var consignorJson = $("#consignorJson").val();
        consignorJson = JSON.parse(consignorJson);
        <%--var consignorJson = ${params.consignorJson} ? ${params.consignorJson} : ''; //consignorJson委托公司、busesJson领域、tasksJson任务类型、servicesJson业务类型--%>
        init(consignorJson);

        $('#selectCompany').on('change', function () {
            var _this = $(this)
            changeEntrustOrg(_this.val(), consignorJson)
        })

        $('.label-bars').on('click', '.label-bar', function () {
            var _this = $(this)

            /*------------领域关联任务类型  start------------*/
            if (_this.parent().hasClass('surveyBusIds') && !_this.hasClass('active')) {
                var key = _this.attr('data-id')
                showByDomains(key)
            }

            /*------------业务类型关联结算方式  start------------*/
            if (_this.parent().hasClass('servicesIds') && !_this.hasClass('active')) {
                var key = _this.attr('data-id')
                showPaytypeBybType(key)
                var orgAttr = $("input[name=orgAttr]").val()
                //保司计算截止时间
                if (orgAttr == null || orgAttr == '' || orgAttr == 1) {
                    var sArea = $("#surveyArea").attr("data-value");
                    if (isZhongAnCase) {
                        deadline2(key, sArea, _this)
                    } else {
                        // deadline(key,sArea,null,_this);
                    }
                }
                if ($("#selectCompany").val() == 94) {
                    getEndTime({'serviceId': _this.attr('data-sub-service-id')});
                } else {
                    getEndTime({'serviceId': _this.attr('data-id')});
                }


            }

            /*------------结算方式关联一口价。。。  start------------*/
            if (_this.parent().hasClass('payTypes') && !_this.hasClass('active')) {
                var key = _this.attr('data-id')
                showByPayType(key)
            }

            /*------------根据调查区域与业务类型与委托时间 自动计算截止时间  可以修改------------*/
            if (_this.parent().hasClass('surveyArea') && !_this.hasClass('active')) {
                //业务类型
                var servicesIds = $("#servicesIds").attr("data-value");
                var key = _this.attr('data-id'); //业务类型
                $("#surveyAreas").val(key);

                if (isZhongAnCase) {
                    deadline2(key, sArea, _this)
                } else {
                    // deadline(servicesIds,key,null,_this)
                }
                getEndTime({'surveyAreaId': _this.attr('data-id'), 'dataType': _this.attr('data-type')});
            }

            /*------------单选 or 多选  start------------*/
            if (!_this.parent().attr('data-name')) { //单选
                if (!_this.hasClass('active')) {
                    _this.addClass('active')
                    _this.siblings().removeClass('active')
                    _this.parent().attr('data-value', _this.attr('data-id'))
                }
            } else if (_this.parent().attr('data-name') == 'multiple') { //多选
                var oldValue = _this.parent().attr('data-value')
                var curId = _this.attr('data-id')
                if (!_this.hasClass('active')) {
                    _this.addClass('active')
                    _this.parent().attr('data-value', oldValue ? oldValue + ',' + curId : oldValue +
                        curId)
                } else {
                    var curIndex = 0
                    var arrValue = oldValue.split(',')
                    arrValue.map(function (item, index) {
                        if (item == curId) {
                            curIndex = index
                        }
                    })
                    arrValue.splice(curIndex, 1)
                    _this.parent().attr('data-value', arrValue.join(','))
                    _this.removeClass('active')
                }
            }


        })
        //拼凑案件编号
        $('.updateCaseNo').on('blur', function () {
            demoNameA();
        })

    })

    var btnCode = $("#btnCode").val();

    function init(params, val) {
        var params = params,
            value = ''
        var info = ${params.dtoJson} ? ${params.dtoJson} : '';
        var results = {
            companyId: info.entrustOrgId ? info.entrustOrgId : '', //保险公司
            product: info.surveyRiskCase && info.surveyRiskCase.hzProduct ? info.surveyRiskCase.hzProduct : '',  //互助产品
            tasks: info.surveyTaskTypes ? info.surveyTaskTypes : '', // 任务类型
            businessType: info.servicesId ? info.servicesId : '',  //业务类型
            surveyBus: info.surveyBusId ? info.surveyBusId : '',   // 领域
            payType: info.payType ? info.payType : '', //结算方式
            modelType: info.surveyRiskCase && info.surveyRiskCase.modelId ? info.surveyRiskCase.modelId : '', //模板数据 1、乐凡 2、正言  3、中德  4、中宏 5、互助
            orgAttr: info.surveyConsignor && info.surveyConsignor.orgAttr ? info.surveyConsignor.orgAttr : '', //公司属性（1：保险公司；2、互助机构）
            autoFillState: info.autoFillState && info.surveyConsignor.autoFillState ? info.surveyConsignor.autoFillState : '',
            finaOrgId: info.surveyRiskCase.entrustOrgId,
            top3insures: info.top3insures ? info.top3insures : ''
        }

        // setActive('#surveyArea .label-bar')

        //遍历保险公司
        params.map(function (item) {
            if (item.id == results.companyId) {
                value = item.id
            } else if (item.id == results.finaOrgId) {
                value = item.id
            }
        })

        $('#selectCompany').val([value])
        //“保险公司”查询
        var obj = $("#obj").val();

        //新增时直接获取当前登录人的机构模板 和 代理委托不同的
        if (obj == 'agent' || btnCode == 'transfer') {
            changeEntrustOrg(value, params)

        } else {
            var modelType = results.modelType;
            if (modelType == 2 || modelType == 6) {
                modelType = 1;
            }
            $('.company').attr('style', '')
            $('.company[data-type=' + modelType + ']').css('display', 'flex')

            $('.company .editInfo').removeAttr('required')
            $('.company[data-type=' + modelType + '] .editInfo').attr('required', 'required')
            if (modelType == 1 || modelType == 6) {
                $("#insureName").removeAttr('required') //保险种类不需要必填
            }
            if (modelType == 5) {
                $("#claimsMoney").removeAttr('required') //互助金额不需要必填
            }
            //获取机构：保司，互助 用于关联领域的展示
            showSurveyBusByOrg(results.orgAttr);

            //获取保险公司对应的保险种类
            if ($("#insureName")) {
                var html = "";
                results.top3insures.map(function (item) {
                    html += '<div class="label-bar">' + item + '</div>';
                });
                $("#insureBars").html(html)
            }
        }

        //单选
        setActive('.company  #hzProducts .label-bar', results.product)
        //多选
        var taskInfo = [];
        if (results.tasks != null && results.tasks != '') {
            taskInfo = results.tasks.map(function (item) {
                return item.taskId
            })
        }

        setActive('#tasks .label-bar', taskInfo, 'multiple')

        //--默认  业务类型 结算方式 ｜ 单点调查(bType1), 任务
        if (!results.businessType) {
            $('#servicesIds .label-bar').map(function (index) {
                var _this = $(this);
                if (_this.attr('data-id') == '12' && !_this.hasClass("isZhongan")) {
                    $(this).addClass('active')
                    $('#payTypes .bType').attr('style', '')
                    $('#payTypes .bType1').show().eq(0).addClass('active').siblings().removeClass('active')
                    $('#servicesIds').attr('data-value', _this.attr('data-id'))
                    var modelType = $("#modelId").val();//报告模板
                    if (modelType == 1 || modelType == 2 || modelType == 6 || modelType == 7) {
                        $('.businessShow').css('display', 'flex')
                    }
                    showByPayType('3')
                }
            })
        } else {
            setActive('#servicesIds .label-bar', results.businessType)
            showPaytypeBybType(results.businessType, results.payType)
        }

        if (obj == 'agent') {
            if (!results.surveyBus) {
                $('#surveyBusIds .label-bar').map(function (index) {
                    var _this = $(this)
                    if (_this.attr('data-id') == '13') {
                        $(this).addClass('active')
                        $('#surveyBusIds').attr('data-value', _this.attr('data-id'))
                        showByDomains(_this.attr('data-id'))
                    }
                })
            } else {
                setActive('#surveyBusIds .label-bar', results.surveyBus)
                //领域关联任务类型
                showByDomains(results.surveyBus)
            }
        }
        if (obj == 'own') {
            //新增时，需要初始化
            demoNameA();
        }
    }

    $('#insureBars').on('click', '.label-bar', function () {
        $("#insureName").val($(this).html());
    })


    /*----根据保险公司显示字段----*/
    function changeEntrustOrg(val, params) {
        var params = params,
            val = val
        params.map(function (item) {
            var modelType = item.modelType
            if (modelType == 2 || modelType == 6 || modelType == 7) {
                modelType = 1;
            }
            if (val == item.id) {
                var autoFillState = item.autoFillState
                if (autoFillState == 1) {
                    $("#divSport").show()
                } else {
                    $("#divSport").hide()
                }
                $('.company').attr('style', '')
                $('.company[data-type=' + modelType + ']').css('display', 'flex')
                $("#modelId").val(modelType);

                $('.company .editInfo').removeAttr('required')
                $('.company[data-type=' + modelType + '] .editInfo').attr('required', 'required')

                var servicesIds = $("#servicesIds").attr('data-value');
                if ((modelType == 1 || modelType == 2 || modelType == 6 || modelType == 7) && (servicesIds == 12 || servicesIds == 13)) {
                    $('.businessShow').css('display', 'flex')
                } else {
                    $('.businessShow').attr('style', '')
                }
                if (modelType == 4) {
                    $(".zh_show").css('display', 'flex');
                }
                if (modelType == 1 || modelType == 6 || modelType == 7) {
                    $("#insureName").removeAttr('required') //保险种类不需要必填
                }
                if (modelType == 5) {
                    $("#claimsMoney").removeAttr('required') //互助金额不需要必填
                }
                // 根据机构（公司属性），展示领域
                showSurveyBusByOrg(item.orgAttr);
            }

        })

        var consignorId = $("#selectCompany").val();
        if (!consignorId) {
            if (btnCode == 'transfer' || btnCode == 'to-lefan-survey') {
                consignorId = $("#entrustOrgId").val();
            } else {
                return;
            }
        }


        if (consignorId == 94) {//选择众安机构众安机构
            isZhongAnCase = true;
            $(".isZhongan").show();
            $(".noZhongan").hide();
            $(".isZhongan").removeClass("active");
            $(".noZhongan").removeClass("active");
            $(".isZhongan").eq(0).addClass("active");

            $("#servicesIds").attr("data-value", 12);
            $(".bbb3").addClass("poi-no")
            $(".bbb1,.bbb3").show();
            $(".bbb2").hide();
            $(".bbb2,.bbb3").removeClass("active");
            $(".bbb1").addClass("active");
            $("#payTypes").attr("data-value", 3);
            $("#subServiceId").val(2);
        } else {
            isZhongAnCase = false;
            $(".noZhongan").show();
            $(".isZhongan").hide();

            $(".noZhongan").removeClass("active");
            $(".isZhongan").removeClass("active");
            $(".noZhongan").eq(0).addClass("active");
            $("#servicesIds").attr("data-value", 12);
            $(".bbb1,.bbb2,.bbb3").removeClass("poi-no");
            $("#subServiceId").val('');
        }


        ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId", {
            "consignorId": consignorId,
            surveyCode: 'consignor',
            btnCode: 1000
        }, function (v, e, p) {
            //获取保险公司对应的保险种类
            if ($("#insureName")) {
                var html = "";
                $("#insureName").val("");
                if (e.data.results.top3insures) {
                    for (var i = 0; i < e.data.results.top3insures.length; i++) {
                        html += '<div class="label-bar">' + e.data.results.top3insures[i] + '</div>';
                    }
                }
                $("#insureBars").html(html)
            }

            var departmentId = '${dto.surveyRiskCase.departmentId}';
            $("#entrustDepartment option").remove();
            $("#entrustUser option").remove();
            $("#entrustUser").append("<option value=''>请选择委托人</option>");
            if (e.data.results.departmentList != null && e.data.results.departmentList.length > 1) {
                $("#entrustDepartment").append("<option value=''>请选择部门</option>");
            }
            for (var i = 0; i < e.data.results.departmentList.length; i++) {
                var val = e.data.results.departmentList[i];
                if (btnCode == 'transfer' && val.id == departmentId) {
                    var se = true;
                    $("#entrustDepartment").append("<option value='" + val.id + "'selected ='" + se + "' >" + val.name + "</option>");
                } else {
                    if (val.sort == 1) {
                        $("#entrustDepartment").append("<option value='" + val.id + "' selected =true >" + val.name + "</option>");
                        changeEntrustDepartment(val.id)
                    } else {
                        $("#entrustDepartment").append("<option value='" + val.id + "'>" + val.name + "</option>");
                    }
                }

            }
            if (e.data.results.departmentList != null && e.data.results.departmentList.length == 1) {
                changeEntrustDepartment(e.data.results.departmentList[0].id)
            }
            if (btnCode == 'transfer') {
                changeEntrustDepartment(departmentId)
            }
            var mode = e.data.results.surveyConsignorModel;
            $("#entrustOrgName").val(mode.consignorName);

            //报告命名规则
            rule = e.data.results.surveyConsignorReportRule;
            if (rule) {
                $("#oneRule").val(rule.oneRule);
                $("#twoRule").val(rule.twoRule);
                $("#threeRule").val(rule.threeRule);
                $("#fourRule").val(rule.fourRule);
                $("#oneRuleStr").val(rule.oneRuleStr);
                $("#twoRuleStr").val(rule.twoRuleStr);
                $("#threeRuleStr").val(rule.threeRuleStr);
                $("#fourRuleStr").val(rule.fourRuleStr);
            }

            demoNameA();

            //隐藏
            $('.tr_formerCase1').hide();

            //根据“委托机构”，“业务类型”获取“截止时间”
            var servicesIds = $("#servicesIds").attr('data-value');
            //展示调查区域
            surveyArea(consignorId)

        });
    }

    function surveyArea(entrustOrgId) {
        var html = "";
        //根据保司ID获取区域类型
        $.ajax({
            url: "${ctx}/baseSurvey/selectInfoByRelationId",
            data: {"entrustOrgId": entrustOrgId, surveyCode: 'getSurveyArea'}
        }).done(
            function (e) {
                $("#surveyArea").html(html);
                var length = e.results.length;
                for (var i = 0; i < length; i++) {
                    var val = e.results[i];
                    if (i == length - 1) {
                        html += '<div class="label-bar active" data-type="1" data-id="' + val.id + '">' + val.name + '</div>';
                    } else {
                        html += '<div class="label-bar" data-type="1" data-id="' + val.id + '">' + val.name + '</div>';
                    }
                    // html += '<div class="label-bar" data-type="1" data-id="' + val.id + '">' + val.name + '</div>';
                    $("#surveyArea").attr("data-value", val.id);
                    $("#surveyAreas").val(val.id);
                }
                if (e.results.length == 0) {
                    html += '<div class="label-bar" data-type="2" data-id="1">直辖市(市区)</div>';
                    html += '<div class="label-bar" data-type="2" data-id="2">直辖市(郊区)</div>';
                    html += '<div class="label-bar" data-type="2" data-id="3">省会</div>';
                    html += '<div class="label-bar" data-type="2" data-id="4">地级市</div>';
                    html += '<div class="label-bar active" data-type="2" data-id="5">县级市</div>';

                    $("#surveyArea").attr("data-value", 5);
                    $("#surveyAreas").val(5);
                }
                $("#surveyArea").html(html);

                getEndTime()
            }
        );

    }

    /*----根据部门显示人员----*/
    function changeEntrustDepartment(id) {
        var departmentId = "";
        if (id != null) {
            departmentId = id;
        } else {
            departmentId = $("#entrustDepartment").val();
        }
        if (!departmentId) {
            return;
        }

        $.ajax({
            url: "${ctx}/baseSurvey/selectInfoByRelationId",
            data: {"departmentId": departmentId, surveyCode: 'consignorDepartment', btnCode: 1000}
        }).done(
            function (e) {
                $("#entrustUser option").remove();
                if (e.results != null && e.results.length > 1) {
                    $("#entrustUser").append("<option value=''>请选择委托人</option>");
                }
                //相互宝机构的数据特殊处理
                var consignorId = $("#selectCompany").val(); //机构
                if (consignorId == 105) {
                    $("#entrustUser option").remove();
                }

                if (btnCode == 'transfer') {
                    var entrustUserId = '${dto.surveyRiskCase.entrustUserId}';
                }
                for (var i = 0; i < e.results.length; i++) {
                    var val = e.results[i];
                    if (consignorId == 105) {
                        var userName = val.userName;
                        if (userName.indexOf('相互宝') != -1) {
                            $("#entrustUser").append("<option value='" + val.userId + "' selected = 'selected'>" + val.userName + "        " + val.tel + "</option>");
                        } else {
                            $("#entrustUser").append("<option value='" + val.userId + "'>" + val.userName + "        " + val.tel + "</option>");
                        }
                    } else {
                        if (btnCode == 'transfer' && val.userId == entrustUserId) {
                            var se = true;
                            $("#entrustUser").append("<option value='" + val.userId + "'selected='" + se + "'>" + val.userName + "        " + val.tel + "</option>");
                        } else {
                            $("#entrustUser").append("<option value='" + val.userId + "'>" + val.userName + "        " + val.tel + "</option>");
                        }

                    }
                }
                demoNameA();
            }
        );


        /*ajaxSubmit("




























        

























        ${ctx}/baseSurvey/selectInfoByRelationId",{"departmentId":departmentId,surveyCode:'consignorDepartment',btnCode:1000},function(v,e,p){
            $("#entrustUser option").remove();
            if(e.data.results != null && e.data.results.length > 1){
                $("#entrustUser").append("<option value=''>请选择委托人</option>");
            }
            //相互宝机构的数据特殊处理
            var consignorId = $("#selectCompany").val(); //机构
            if(consignorId == 105){
                $("#entrustUser option").remove();
            }
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                if(consignorId == 105){
                    var userName = val.userName;
                    if(userName.indexOf('相互宝')!=-1){
                        $("#entrustUser").append("<option value='" + val.userId + "'>" + val.userName + "        " + val.tel + "</option>");
                    }
                }else {
                    $("#entrustUser").append("<option value='" + val.userId + "'>" + val.userName + "        " + val.tel + "</option>");
                }
            }
        });*/
    }

    /*----初始化选中方法：单选，多选----*/
    function setActive(params, key, multiple) {
        var $params = $(params)
        var key = key
        if (!multiple) {//单选
            if (key) {
                $params.map(function () {
                    var param = $(this)
                    if (param.attr('data-id') == key) {
                        param.addClass('active')
                    } else {
                        param.removeClass('active')
                    }
                })
            } else {
                //--默认
                key = $params.eq(0).attr('data-id')
                $params.eq(0).addClass('active')
            }

        } else { //多选
            var keys = key
            $params.map(function () {
                var param = $(this)
                if (keys.indexOf(Number(param.attr('data-id'))) > -1) {
                    param.addClass('active')
                }
            })
            key = key.join(',')
        }
        //= 隐藏域
        $params.parent().attr('data-value', key)
    }

    /*----根据领域显示任务类型----*/
    function showByDomains(param) {
        $('#surveyBusIds').attr('data-value', param)
        if (param == '17') { //互助调查
            $('.showByDomains').show()

            var servicesIds = $("#servicesIds").attr('data-value');
            var taskInfo = [];
            if (servicesIds == 13) {
                //深度案件 为 33,35,37,44,49,50,51,52
                taskInfo = [33, 35, 37, 44, 49, 50, 51, 52];
            } else {
                //单点案件 赋值 33,35,37,49,50,52
                taskInfo = [33, 35, 37, 49, 50, 52];
            }

            $('#tasks').attr('data-value', '');
            $('#tasks .label-bar').removeClass('active');
            setActive('#tasks .label-bar', taskInfo, 'multiple')
        } else {
            $('.showByDomains').hide()
            //取消默认选中任务类型  赋值
            $('#tasks').attr('data-value', '');
            $('#tasks .label-bar').removeClass('active');

        }
    }

    /*----根据业务类型显示结算方式----*/
    function showPaytypeBybType(key, payType) {
        var modelType = $("#modelId").val();//报告模板
        $('#payTypes .bType').attr('style', '')
        var key = key, //业务类型['11','12','13]
            payType = payType, //结算方式['1','2','3']
            payTypeCopy = payType
        if (key == '12') {
            $('#payTypes .bType1').show().eq(0).addClass('active').siblings().removeClass(
                'active')
            //投保日期，出险日期，出险地点 显示或隐藏 并且是“乐凡模板、正言模板”
            if (modelType == 1 || modelType == 2 || modelType == 6 || modelType == 7) {
                $('.businessShow').css('display', 'flex')
            } else {
                $('.businessShow').attr('style', '')
            }
            payType = payType || '3'
        } else if (key == '13') {
            $('#payTypes .bType2').show().eq(0).addClass('active').siblings().removeClass(
                'active')
            if (modelType == 1 || modelType == 2 || modelType == 6 || modelType == 7) {
                $('.businessShow').css('display', 'flex')
            } else {
                $('.businessShow').attr('style', '')
            }
            payType = payType || '2'
        } else if (key == '11') {
            $('#payTypes .bType1').show().eq(0).addClass('active').siblings().removeClass(
                'active')
            //投保日期，出险日期，出险地点 显示或隐藏 并且是“乐凡模板、正言模板”
            $('.businessShow').attr('style', '')
            payType = payType || '3'
        }

        if (payTypeCopy) {
            $('#payTypes .bType').map(function () {
                //判断相等
                if ($(this).attr('data-id') == payTypeCopy) {
                    $('#payTypes .bType').removeClass('active')
                    $(this).addClass('active')
                }
            })
        }
        $('#servicesIds').attr('data-value', key)
        showByPayType(payType)

        //同时更新任务类型
        var surveyBusIds = $("#surveyBusIds").attr('data-value');
        showByDomains(surveyBusIds);
    }

    //根据结算方式显示一口价。。。。
    function showByPayType(bType) {
        $('#payTypes').attr('data-value', bType)
        $('.form-group.jbjs').attr('style', '')
        if (bType == '1') {
            $('.form-group.ykj').css('display', 'flex')
        }
//        else if (bType == '2') {
//            $('.form-group.jbjs').css('display', 'flex')
//        }
    }

    //计算截止日期
    function deadline(sType, sArea, dpTime, _this) {
        sType = parseInt(sType);
        sArea = parseInt(sArea);
        var days = 0;
        var entrustTime = dpTime != null ? dpTime : $("#entrustTime").val();//委托时间
        var date = new Date(entrustTime);
        var cityType;
        var servicesIds;
        if (dpTime == null) {
            if (_this.parent().attr('id') == 'servicesIds') {
                cityType = $("#surveyArea").attr("data-value");
                servicesIds = _this.attr("data-id");
            } else if (_this.parent().attr('id') == 'surveyArea') {
                cityType = _this.attr("data-id");
                servicesIds = $("#servicesIds").attr("data-value");
            }
        } else {
            cityType = $("#surveyArea").attr("data-value");
            servicesIds = $("#servicesIds").attr("data-value");
        }

        <%--$.post("${ctx}/survey/case/getDeadline",{startTime:entrustTime,cityType:cityType,serviceId:servicesIds,entrustOrgId:entrustOrgId},function (res,e) {--%>
        <%--    $("#endTime").val(e.data.data)--%>
        <%--})--%>

    }

    //众安案件计算截止时间
    function deadline2(sType, sArea, _this, dpTime) {
        sType = parseInt(sType);
        sArea = parseInt(sArea);
        var days = 0;
        var entrustTime = dpTime != null ? dpTime : $("#entrustTime").val();//委托时间
        var subServiceIdl;
        var cityType;
        if (dpTime == null) {
            if (_this.parent().attr('id') == 'servicesIds') {
                subServiceId = _this.attr("data-sub-service-id") || $(".isZhongan.active").attr("data-sub-service-id");
                cityType = $("#surveyArea").attr("data-value");
            } else if (_this.parent().attr('id') == 'surveyArea') {
                subServiceId = _this.attr("data-sub-service-id") || $(".isZhongan.active").attr("data-sub-service-id");
                cityType = _this.attr("data-id");
            }
        } else {
            cityType = $("#surveyArea").attr("data-value");
            servicesIds = $("#servicesIds").attr("data-value");
        }

        //bbb1 任务  bbb2基本费减损  bbb3 一口价
        if (subServiceId == 2) {
            $(".bbb3").addClass("poi-no");
            $(".bbb1,.bbb2").removeClass("poi-no");
            $("#payTypes").attr("data-value", 3);
            $(".jbjs").hide();
            $("#entrustMoney").val(0);
            <%--$.post("${ctx}/survey/case/getDeadline",{startTime:entrustTime,subServiceId:subServiceId,cityType:cityType,entrustOrgId:entrustOrgId},function (res,e) {--%>
            <%--    $("#endTime").val(e.data.data)--%>
            <%--})--%>
        }
        if (subServiceId > 2 && subServiceId < 6) {
            $("#payTypes").attr("data-value", 2);
            $(".bbb1,.bbb3").removeClass("active");
            $(".bbb2").addClass("active");
            $(".bbb3").addClass("poi-no");
            $(".jbjs").hide();
            $("#entrustMoney").val(0);
            <%--$.post("${ctx}/survey/case/getDeadline",{startTime:entrustTime,subServiceId:subServiceId,cityType:cityType,entrustOrgId:entrustOrgId},function (res,e) {--%>
            <%--    $("#endTime").val(e.data.data)--%>
            <%--})--%>
        }
        if (subServiceId == 6) {
            $(".bbb2").addClass("poi-no");
            $(".bbb1,.bbb3").removeClass("poi-no");
            $("#payTypes").attr("data-value", 1);
            $(".bbb1,.bbb2").removeClass("active");
            $(".bbb3").addClass("active");
            $(".jbjs").css({
                display: 'flex'
            });
            <%--$.post("${ctx}/survey/case/getDeadline",{startTime:entrustTime,subServiceId:subServiceId,cityType:cityType,entrustOrgId:entrustOrgId},function (res,e) {--%>
            <%--    $("#endTime").val(e.data.data)--%>
            <%--})--%>
        }
    }

    //根据机构（公司属性），展示领域
    function showSurveyBusByOrg(orgAttr) {
        $("input[name=orgAttr]").val(orgAttr);
        $(".label-bars.surveyBusIds .label-bar").hide().removeClass('active');
        $(".label-bars.surveyBusIds .label-bar[data-type = " + orgAttr + "]").show();
        if (orgAttr == 1) {
            $('#surveyBusIds .label-bar[data-id=13]').show().addClass('active');
            $('#surveyBusIds').attr('data-value', 13);
            $("#surveyArea").parent().parent().show();
            showByDomains('13');
        } else if (orgAttr == 2) {
            $('#surveyBusIds .label-bar').hide().removeClass('active');
            $('#surveyBusIds .label-bar[data-id=17]').show().addClass('active');
            $('#surveyBusIds').attr('data-value', 17);
            // $("#surveyArea").parent().parent().hide();
            showByDomains('17');
        }
        //第二次
        /*
        $(".label-bars.surveyBusIds .label-bar").hide().removeClass('active');
        $(".label-bars.surveyBusIds .label-bar[data-type = "+orgAttr+"]").show();
        $('#surveyBusIds').attr('data-value','');
        //
        $('.showByDomains').hide();
        $('#tasks').attr('data-value','');
        $('#tasks .label-bar').removeClass('active');
        */


        //第一次
//        if(orgAttr == 1){
//            $('#surveyBusIds .label-bar').show();
//            $('#surveyBusIds .label-bar[data-id=17]').hide();
//            $('#surveyBusIds .label-bar[data-id=12]').show().addClass('active');
//            $('#surveyBusIds').attr('data-value',12);
//            showByDomains('12');
//        }else{
//            $('#surveyBusIds .label-bar').hide().removeClass('active');
//            $('#surveyBusIds .label-bar[data-id=17]').show().addClass('active');
//            $('#surveyBusIds').attr('data-value',17);
//            showByDomains('17');
//        }
    }

    //通过身份证查询‘重复案件’
    var idNumberCase = "";

    function selectCase() {
        idNumberCase = false;
        var idNumber = $("#idNumber").val();
        if (!idNumber) {
            return;
        }
        ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId", {
            "idNumber": idNumber,
            surveyCode: 'riskCase',
            btnCode: 1000
        }, function (v, e, p) {

            if (e.data.results.length > 0) {
                $("#tab_temp tbody").html("");
                for (var i = 0; i < e.data.results.length; i++) {
                    idNumberCase = true;
                    $('.tr_formerCase1').show();
                    var val = e.data.results[i];
                    $("#tab_temp tbody").append("<tr><td>" + val.surveyNo + "</td><td>" + val.surveyRiskCase.surveyPerson + "</td>" +
                        "<td>" + val.surveyRiskCase.surveryPersonTel + "</td>" +
                        "<td>" + val.entrustOrgName + "</td><td><input type='button' value='详  情' onclick='info(" + val.id + ")' /></td>" +
                        "</tr>");
                }
            } else {
                $('.tr_formerCase1').hide();
            }
        });
        //通过身份证获取性别，年龄
        if (parseInt(idNumber.substr(16, 1)) % 2 == 1) {
            $("#sex").val(1);//男
        } else {
            $("#sex").val(2);//女
        }
        //获取年龄
        var myDate = new Date();
        var month = myDate.getMonth() + 1;
        var day = myDate.getDate();
        var age = myDate.getFullYear() - idNumber.substring(6, 10) - 1;
        if (idNumber.substring(10, 12) < month || idNumber.substring(10, 12) == month && idNumber.substring(12, 14) <= day) {
            age++;
        }
        $("#age").val(age);
        if (age < 40) {
            //30W
            $("#claimsMoney").val(300000)
        } else {
            //10W
            $("#claimsMoney").val(100000)
        }
    }


    function toValid() {

        //证件类型
        var idType = $("#idType").attr("data-value");
        $("input[name=idType]").val(idType);

        //互助产品
        var hzProducts = $("#hzProducts").attr("data-value");
        $("#hzProduct").val(hzProducts);

        var btnCode = $("#btnCode").val();
        if (btnCode == "survey-base-info-upd") {
            return true;
        }

        var endTime = $("#endTime").val();
        if (!endTime) {
            alert("截至日期必填");
            return false;
        }

        var entrustTime = $("#entrustTime").val();
        if (!entrustTime) {
            alert("委托日期必填");
            return false;
        }


        //结算方式
        var payTypes = $("#payTypes").attr("data-value");
        $("#payType").val(payTypes);

        //领域
        var surveyBusIds = $("#surveyBusIds").attr("data-value");
        $("#surveyBusId").val(surveyBusIds);
        if (!surveyBusIds) {
            alert("领域必填");
            return false;
        }

        //业务类型
        var servicesIds = $("#servicesIds").attr("data-value");
        $("#servicesId").val(servicesIds);
        if (isZhongAnCase) {
            var subServiceId = $(".isZhongan.active").attr("data-sub-service-id");
            $("#subServiceId").val(subServiceId);
        }
        //任务类型
        var taskIds = []
        var tasks = $("#tasks").attr("data-value");
        $("#chkTaskIds").val(tasks);

        if (surveyBusIds == 17) {
            if (!tasks) {
                alert("任务类型必填");
                return false;
            }
        }
        //保司
        if ($("input[name=orgAttr]").val() == 1) {
            var sArea = $("#surveyArea").attr("data-value");
            if (sArea == null || sArea == '') {
                alert("调查区域必填");
                return false;
            }
        }

        //互助类型
        var modelId = $("#modelId").val();
        if (modelId == 5) {
            var hzWaitEndTime = $("#hzWaitEndTime").val();
            if (!hzWaitEndTime) {
                alert("等待截止日期必填");
                return false;
            }
            var insureTime5 = $("#insureTime5").val();
            $("#insureTime").val(insureTime5);
            if (!insureTime5) {
                alert("加入日期必填");
                return false;
            }
            var dangerTime5 = $("#dangerTime5").val();
            $("#dangerTime").val(dangerTime5);
            if (!dangerTime5) {
                alert("出险日期必填");
                return false;
            }
            var dangerAddress5 = $("#dangerAddress5").val();
            $("#dangerAddress").val(dangerAddress5);
        }

        if (idNumberCase) {
            var message = confirm("该身份证号下有重复案件，是否确认继续提交？");
            if (!message) {
                return false;
            }
        }

        return true;
    }

    function spot() {
        var text = $("#spotText").val().replace(new RegExp("：\n", "g"), "：");
        $("#spotText").val(text)
        $("#div_1").attr("title", text);
        var entrustOrgId = $("#selectCompany").val();
        if (entrustOrgId == 105 || entrustOrgId == 220) { // 相互宝，蚂蚁保险
            $("#surveyPerson").val(replaceSport('成员姓名：', text));
            $("#surveryPersonTel").val(replaceSport('联系电话：', text));
            $("#idNumber").val(replaceSport('证件号码：', text));
            $("#hzContactTel").val(replaceSport('联系电话：', text));
            $("#hzContactName").val(replaceSport('申请人姓名：', text));
            $("#surveyInfo").val(replaceSport('事故说明：', text));
            $("#hzLiveAddress").val(replaceSport('常住住址：', text));
            $("#dangerAddress").val(replaceSport('确诊医院：', text));
            $("#hzConfirmDisease").val(replaceSport('确诊疾病：', text));
            $("#insureTime5").val(replaceSport('生效时间：', text, 'ago'));
            $("#hzWaitEndTime").val(replaceSport('生效时间：', text, 'after'));
            $("#dangerTime5").val(replaceSport('确诊时间：', text));
            selectCase();
        } else if (entrustOrgId == 155) { // picc
            $("#claimsNo").val(replaceSportPicc('调查号：', text, '调查来源'));
            $("#idType").val(1);
            $("#idNumber").val(replaceSportPicc('身份证号：', text, '关联保单数'));
            $("#surveryPersonTel").val(replaceSportPicc('申请人手机号：', text, '与出险人关系'));
            $("#dangerTime").val(replaceSportPicc('出险日期：', text, '就诊日期'));
            selectCase();
        } else if (entrustOrgId == 94) { //众安

            $("#dangerTime").val(replaceSportZa('出险日期', text));
            $("#surveryPersonTel").val(replaceSportZa('索赔人手机号码', text));
            $("#dangerAddress").val(replaceSportZa('出险地区', text));
            $("#surveyInfo").val(replaceSportZa('出险经过', text));
            $("#surveyPerson").val(replaceSportZa('事故者信息', text));
            $("#claimsNo").val(replaceSportZa('赔案号', text));
            replaceSportZa('整体难度', text);
            $("#surveyItem").val(replaceSportZa('提调项目类型', text));
            $("#insureTime").val(replaceSportZa('首保生效日', text));

        }
        demoNameA();

    }

    function replaceSport(name, text, type) {
        var data = text.split("\n");
        for (var i = 0; i < data.length; i++) {
            if (data[i].indexOf(name) > -1) {
                if (name == '生效时间：') {
                    if (type == 'ago') {
                        return data[i].substring(data[i].indexOf(name) + name.length, data[i].indexOf(name) + name.length + 10).trim();
                    } else if (type == 'after') {
                        return data[i].substring(data[i].indexOf(name) + name.length + 11, data[i].indexOf(name) + name.length + 11 + 10).trim();
                    }
                } else if (name == "事故说明：") {
                    return text.substring(text.indexOf(name) + name.length, text.indexOf("申请人信息")).trim()
                }
                return data[i].substring(data[i].indexOf(name) + name.length).trim();
            }
        }
        return "";
    }

    //picc 智能识别
    function replaceSportPicc(name, text, lastName) {
        var data = text.split("\n");
        for (var i = 0; i < data.length; i++) {
            if (data[i].indexOf(name) > -1) {
                return data[i].substring(data[i].indexOf(name) + name.length, data[i].indexOf(lastName)).trim();
            }
            if (i == 3) {
                $("#surveyPerson").val(data[i]);
            }
            if (i == 5) {
                $("#insureName").val(data[i]);
            }
        }
        return "";
    }

    //众安 智能识别
    function replaceSportZa(name, text) {
        var data = text.split("\n");
        for (var i = 0; i < data.length; i++) {
            if (data[i].indexOf(name) > -1) {
                if (name == "事故者信息") {
                    return data[i + 2];
                } else if (name == "出险经过") {
                    return text.substring(text.indexOf(name) + name.length, text.indexOf("报案时间")).trim()
                } else if (name == "提调项目类型") {
                    var info1 = text.substring(text.indexOf(name) + name.length - 6, text.indexOf("最晚回销日期")).trim()
                    name = '调查区域及途径';
                    var info2 = text.substring(text.indexOf(name) + name.length - 7).trim()
                    name = '疑点类型';
                    var info3 = text.substring(text.indexOf(name) + name.length - 4, text.indexOf("整体难度")).trim()
                    var info = info1 + '\n' + info2 + '\n' + info3;
                    return info;
                } else if (name == "整体难度") {
                    var info = data[i + 1];
                    if (info == '一般搜索类') {
                        info = '一般检索类';
                    }
                    $('#servicesIds .label-bar').map(function (i, cur) {
                        if ($(cur).text() == info) {
                            $('#servicesIds .label-bar').eq(i).click()
                        }
                    })
                    return;
                } else if (name == "首保生效日") {
                    return text.substring(text.indexOf(name) + name.length, text.indexOf(name) + name.length + 10).trim()
                }
                return data[i + 1];
            }
        }
    }


    $("#editForm").bind('submit', function (event) {
        $("[name=btnUpload]").attr("disabled", "true");
        ajaxFormSubmit(this, myCallBack, null, null, myCallBack);
        event.preventDefault();
    });

    function myCallBack(event, param) {
        var apiRsp = getApiJson(param.data);
        if (apiRsp && apiRsp.isSuccess) {
            if (!$("#id").val()) {
                $("#surveyInfoId").val(apiRsp.results.id);
                $("#surveyId").val(apiRsp.results.surveyId);
                $("#surveyCno").val(apiRsp.results.surveyCno);
                $("#div_1").hide();
                $("#div_btn_1").hide();
                $("#div_2").show();
                $("#div_btn_2").show();
            } else {
                reloadParent();
            }
        } else {
            $("[name=btnUpload]").attr("disabled", "false");
            alert(apiRsp.msg);
            return;
        }
    }

    var info = function (id) {
        if (!id) {
            return;
        }
        openDialog({
            frame: true,
            title: "详情",
            height: 700,
            width: 1100,
            url: "${ctx}/survey/case/info?id=" + id + "&menuCode=my-list" + "&showTransfer=1"
        });
    }


    //"案件编号"命名规则
    var rule = "";
    var demoNameAll = "";
    var demoName1 = "";
    var demoName2 = "";
    var demoName3 = "";
    var demoName4 = "";
    var modelType = "";

    function demoNameA() {
        var oneRule = $("#oneRule").val();
        var twoRule = $("#twoRule").val();
        var threeRule = $("#threeRule").val();
        var fourRule = $("#fourRule").val();
        var oneRuleStr = $("#oneRuleStr").val();
        var twoRuleStr = $("#twoRuleStr").val();
        var threeRuleStr = $("#threeRuleStr").val();
        var fourRuleStr = $("#fourRuleStr").val();

        var entrustOrgName = $("#entrustOrgName").val();
        var departmentName = $("#entrustDepartment").find("option:selected").text();

        var policyNo = "";
        var claimsNo = "";
        var modelId = $("#modelId").val();
        if (modelId == 3) {
            claimsNo = $("#policyNo4").val();
        } else if (modelId == 4) {
            claimsNo = $("#policyNo").val();
        }
        if (modelId == 5) {
            claimsNo = $("#claimsNoHz").val()
        } else if (modelId == 1 || modelId == 2 || modelId == 6 || modelId == 7) {
            claimsNo = $("#claimsNo").val();
        }

        var surveyPerson = $("#surveyPerson").val();


        if (oneRule == 1) {
            demoName1 = policyNo;
        } else if (oneRule == 2) {
            demoName1 = surveyPerson;
        } else if (oneRule == 3) {
            demoName1 = claimsNo;
        } else if (oneRule == 4) {
            demoName1 = entrustOrgName;
        } else if (oneRule == 6) {
            demoName1 = oneRuleStr;
        } else if (oneRule == 7) {
            demoName1 = departmentName;
        }

        if (twoRule == 1) {
            demoName2 = policyNo;
        } else if (twoRule == 2) {
            demoName2 = surveyPerson;
        } else if (twoRule == 3) {
            demoName2 = claimsNo;
        } else if (twoRule == 4) {
            demoName2 = entrustOrgName;
        } else if (twoRule == 6) {
            demoName2 = twoRuleStr;
        } else if (twoRule == 7) {
            demoName2 = departmentName;
        }

        if (threeRule == 1) {
            demoName3 = policyNo;
        } else if (threeRule == 2) {
            demoName3 = surveyPerson;
        } else if (threeRule == 3) {
            demoName3 = claimsNo;
        } else if (threeRule == 4) {
            demoName3 = entrustOrgName;
        } else if (threeRule == 6) {
            demoName3 = threeRuleStr;
        } else if (threeRule == 7) {
            demoName3 = departmentName;
        }

        if (fourRule == 1) {
            demoName4 = policyNo;
        } else if (fourRule == 2) {
            demoName4 = surveyPerson;
        } else if (fourRule == 3) {
            demoName4 = claimsNo;
        } else if (fourRule == 4) {
            demoName4 = entrustOrgName;
        } else if (fourRule == 6) {
            demoName4 = fourRuleStr;
        } else if (fourRule == 7) {
            demoName4 = departmentName;
        }

        //
        var selectCompany = $("#selectCompany").val();
        var demoName0 = "";
        if (selectCompany == 52) {
            var taskNumber = $("#taskNumber").val();
            var healthInsuranceCompany = $("#healthInsuranceCompany").val();
            var cooperativeCompany = $("#cooperativeCompany").val();
            var subsidiaryCompany = $("#subsidiaryCompany").val();
            demoName0 = taskNumber + healthInsuranceCompany + cooperativeCompany + subsidiaryCompany;
        }


        demoNameAll = demoName0 + demoName1 + demoName2 + demoName3 + demoName4 + "案";
        $("#surveyCaseNo").val(demoNameAll);
    }

    var operate = function (btnCode, ajax) {
        var msg = false;
        var id = $("#surveyInfoId").val();
        var height = 400, width = 900;
        if (ajax) {
            var tipTitle = "是否确认？";
            var url = null, param = null;
            if (btnCode == '1200' || btnCode == '1300') {
                url = "${ctx}/survey/case/operate";
                param = {"id": id, "btnCode": 1200};
            }
            if (!msg) {
                if (confirm(tipTitle)) {
                    ajaxSubmit(url, param, function (v, e, p) {
                        if (btnCode == '1200') {
                            alert(e.data.msg);
                            parent.location.reload();
                        } else if (btnCode == '1300') {
                            parent.location.reload();
                            openlist();
                        } else {
                            location.reload();
                        }
                    })
                }
            } else {
                ajaxSubmit(url, param, function (v, e, p) {
                    alert(e.data.msg);
                    location.reload();
                })
            }
        } else {
            var title = null, url = null;
        }
    }

    //受理通过并分派调查员：自动分派
    function openlist() {
        var id = $("#surveyInfoId").val();
        <%--var url = "${ctx}/survey/case/list?menuCode=assign-list&autoOpenInfo=1&autoOpenInfoId="+id;--%>
        var url = "${ctx}/survey/case/info?id=" + id + "&menuCode=assign-list&autoOpenInfo=1&auto=1";
        var title = "案件分派";
        parent.parent.parent.addTab(title, url, true);
    }

    //根据“委托机构”，“业务类型”获取“截止时间”
    function getEndTime(param) {
        var entrustOrgId = $("#selectCompany").val();
        if (entrustOrgId) {
            //保司ID，委托时间，业务类型ID(众安的时候注意)，调查区域ID（data-type=1，区域ID。   data-type=2.后台写死一套数据)  获取天数。得到案件截止日期。
            //ajax请求获取案件截止日期。
            var entrustOrgId = $("#selectCompany").val();
            var startTime = $("#entrustTime").val();//委托时间
            if (param && param.entrustTime) {
                startTime = param.entrustTime;
            }


            //业务类型
            var serviceId = $("#serviceId").val();
            var subServiceId = $("#subServiceId").val();
            var areaCategoriesId = "", cityType = "";
            if (entrustOrgId == 94) {
                subServiceId = $("#servicesIds .isZhongan.active").attr("data-sub-service-id");
            } else {
                serviceId = $("#servicesIds .noZhongan.active").attr("data-id");
            }
            if (param && param.serviceId) {
                if (entrustOrgId == 94) {
                    subServiceId = param.serviceId
                } else {
                    serviceId = param.serviceId
                }
            }

            //区域
            var selArea = $("#surveyArea .label-bar.active");
            var dataType = selArea.attr("data-type");
            var selAreaId = selArea.attr("data-id");
            if (param && param.surveyAreaId) {
                dataType = param.dataType
                selAreaId = param.surveyAreaId
            }

            if (entrustOrgId != 105) {
                if (!dataType) {
                    return;
                }
            }
            if (dataType == 2) {
                areaCategoriesId = "";
                cityType = selAreaId
            } else if (dataType == 1) {
                areaCategoriesId = selAreaId
            }

            if (!entrustOrgId || !startTime) {
                return;
            }
            if (entrustOrgId == 94) {
                if (!subServiceId) {
                    return;
                }
            } else {
                if (!serviceId) {
                    return;
                }
            }

            var param = {
                "entrustOrgId": entrustOrgId,
                "startTime": startTime,
                "serviceId": serviceId,
                "subServiceId": subServiceId,
                "areaCategoriesId": areaCategoriesId,
                "cityType": cityType  //默认的时候才存在改字段。
            }
            $.post("${ctx}/survey/case/getDeadline", param, function (res, e) {
                $("#endTime").val(e.data.data)
            })
        }
    }

    function selectHzClaimsMoney() {
        var modelType = $("#modelId").val();//报告模板
        if (modelType == 5) {
            var age = $("#age").val();
            if (age < 40) {
                //30W
                $("#claimsMoney").val(300000)
            } else {
                //10W
                $("#claimsMoney").val(100000)
            }
        }
    }

    function onchange_selectCompany(id) {
        // 太平洋健康
        if (52 == id) {
            divShow("divShow");
            divHidden("divShow67")

            var keys = ["delegationMode"];
            clearValue(keys);
        } else if (67 == id) {
            divShow("divShow67");
            divHidden("divShow")

            var keys = ["cooperativeCompany", "subsidiaryCompany", "healthInsuranceCompany", "taskNumber"];
            clearValue(keys);
        } else {
            divHidden("divShow67");
            divHidden("divShow")

            var keys = ["cooperativeCompany", "subsidiaryCompany", "healthInsuranceCompany", "taskNumber", "delegationMode"];
            clearValue(keys);
        }
    }

    function divShow(element) {
        var elementsByNames = document.getElementsByName(element);
        for (var i = 0; i < elementsByNames.length; i++) {
            elementsByNames[i].style.display = 'block';
        }
    }

    function divHidden(element) {
        var elementsByNames = document.getElementsByName(element);
        for (var i = 0; i < elementsByNames.length; i++) {
            elementsByNames[i].style.display = 'none';
        }
    }

    function clearValue(keys) {
        for (var i = 0; i < keys.length; i++) {
            $("#" + keys[i] + "").val("");
        }
    }
</script>
</body>
</html>
