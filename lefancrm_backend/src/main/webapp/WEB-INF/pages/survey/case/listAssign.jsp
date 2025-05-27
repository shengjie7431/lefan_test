<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">
    <style>
        .td-spec{
            border-top: none!important;

        }
        table.table>tbody>tr>td{
            vertical-align: middle!important;
            padding-top: 12px;
            padding-bottom: 12px;
        }
        .title_sort{
            display: flex;
            align-items: center;
            cursor: pointer;
        }
        .icon-sort {
            display: inline-block;
            width: 10px;
            padding-left: 2px;
        }
        .icon-sort  .icon-up.active {
            border-bottom: 7px solid #333;
        }

        .icon-sort  .icon-down.active {
            border-top: 7px solid #333;
        }
        .icon-up {
            width: 0;
            height: 0;
            border-right: 5px solid transparent;
            border-left: 5px solid transparent;
            border-bottom: 7px solid #b3b3b3;
            margin-bottom: 2px;
        }

        .icon-down {
            width: 0;
            height: 0;
            border-right: 5px solid transparent;
            border-left: 5px solid transparent;
            border-top: 7px solid #b3b3b3;
        }

        .icon-up:hover {
            border-bottom: 7px solid #333333c2;
        }

        .icon-down:hover {
            border-top: 7px solid #333333c2;
        }
        .edit-filterOpr{
            width: 90%;
            margin: 20px auto;
        }
        .edit-filterOpr .layui-form-checkbox {
            display: block;
        }
        .edit-filterOpr .input-content{
            height: 320px;
            width: 100%;
            overflow: auto;
        }
        .disabled{
            pointer-events: none;
        }
        .xm-select-demo{
            width: 200px;
        }
        a{
            color: #428bca;
            text-decoration: none;
            background: 0 0;
        }
        .panel-info{
            margin-bottom: 60px;
        }

        .span-sd{
            width: 60px;
            height: 25px;
            background-color: red;
            color: #ffffff;
            display: table-cell;
            text-align: center;
            vertical-align: middle;
        }
        .pagePosi{
            position: fixed;
            bottom: 0;
            width: 100%;
            background-color: #fff;
            padding:6px 0;
        }
    </style>
</head>
<body>
<%--机构初审 平台复审互助--%>
<div class="main administrator">
    <div class="main-top">
        <h3>案件列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/survey/case/listAssign?menuCode=${menuCode}&pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <input type="hidden" name="surveyStates" id="surveyStates" />
                    <input type="hidden" name="investigators" id="investigators" />
                    <input type="hidden" name="entrustOrgIds" id="entrustOrgIds" />
                    <input type="hidden" name="surveyOrgIds" id="surveyOrgIds" />
                    <input type="hidden" name="serviceTypes" id="serviceTypes" />
                    <input type="hidden" name="finalInfos" id="finalInfos" />

                    <input type="hidden" name="menuCode232323" id="menuCode" value="${menuCode}" />
                    <input type="hidden" name="sortField" id="sortField" value="${sortField}">
                    <input type="hidden" name="sortType" id="sortType" value="${sortType}">
                    <div class="form-group">
                        快捷查询:<input style="width: 500px" name="searchStr" type="text" value="${searchStr}" placeholder="可输入被调查人，案件编号，调查编号，联系方式，身份证号" class="form-control">
                    </div>

<%--                    <div class="form-group">--%>
<%--                        案件编号:<input name="surveyCaseNo" type="text" value="${surveyCaseNo}" class="form-control">--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        调查编号:<input name="surveyNo" type="text" value="${surveyNo}" class="form-control">--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                       被调查人:<input name="surveyPerson" type="text" value="${surveyPerson}" class="form-control">--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                       联系方式:<input name="surveryPersonTel" type="text" value="${surveryPersonTel}" class="form-control">--%>
<%--                    </div>--%>
                    <%--<div class="form-group">--%>
                        <%--保单号:<input name="policyNo" type="text" value="${policyNo}" class="form-control">--%>
                    <%--</div>--%>
                    <div class="form-group">
                        保险公司：
                        <div>
                            <select class="select form-control select-checkbox" name="entrustOrgIdsChk" data-select-name="entrustOrgIds" data-select-values="${entrustOrgIds}" multiple >
                                <c:forEach items="${consignors}" var="item">
                                    <option value="${item.id}" >${item.company}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <c:if test="${menuCode == 'help-review' || menuCode == 'assign-org-list'}">

                        <c:if test="${menuCode == 'help-review'}">
                            <div class="form-group">
                                调查机构:
                                <div>
                                    <select class="select form-control select-checkbox" name="surveyOrgIdsChk" data-select-name="surveyOrgIds" data-select-values="${surveyOrgIds}" multiple >
                                        <c:forEach items="${franchisees}" var="item">
                                            <option value="${item.id}" >${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </c:if>
                    </c:if>
                    <c:if test="${menuCode == 'extension-time'}">
                        <div class="form-group">
                            保险公司:
                            <div>
                                <select class="select form-control select-checkbox" name="entrustOrgIdsChk" data-select-name="entrustOrgIds" data-select-values="${entrustOrgIds}" multiple >
                                    <c:forEach items="${consignors}" var="item">
                                        <option value="${item.id}" >${item.company}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            调查方机构:
                            <div>
                                <select class="select form-control select-checkbox" name="surveyOrgIdsChk" data-select-name="surveyOrgIds" data-select-values="${surveyOrgIds}" multiple >
                                    <c:forEach items="${franchisees}" var="item">
                                        <option value="${item.id}" >${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </c:if>
                    <div class="form-group">
                        业务类型：
                        <select  name="serviceTypeChk" class="select form-control select-checkbox" data-select-name="serviceTypes"  data-select-values="${serviceTypes}" multiple>
                            <option value="12">单点调查</option>
                            <option value="13">深度调查</option>
                            <option value="11">契约调查</option>
                        </select>
                    </div>
                    <c:if test="${menuCode == 'task-org-review'}">
                        预审状态：
                        <select onchange="javascript:$('#batchOperateBtn').click();" name="reviewOff" class="form-control">
<%--                            <option value="" <c:if test="${reviewOff == ''}">selected="selected"</c:if>>全部</option>--%>
                            <option value="0" <c:if test="${reviewOff == '0'}">selected="selected"</c:if>>待预审</option>
                            <option value="1" <c:if test="${reviewOff == '1'}">selected="selected"</c:if>>已预审</option>
                        </select>
                    </c:if>
                    <c:if test="${menuCode == 'help-review'}">
                        审核状态：
                        <select onchange="javascript:$('#batchOperateBtn').click();" name="oprReview" class="form-control">
                            <option value="0" <c:if test="${oprReview == '0'}">selected="selected"</c:if>>待审核</option>
                            <option value="2" <c:if test="${oprReview == '2'}">selected="selected"</c:if>>超期未审核</option>
                            <option value="1" <c:if test="${oprReview == '1'}">selected="selected"</c:if>>已审核</option>
                        </select>
                    </c:if>

                    <c:if test="${menuCode == 'assign-org-list'}">
                        <div class="form-group">
                            分派状态
                            <select onchange="javascript:$('#batchOperateBtn').click();" name="surveyInvestigatorCaseIdState" class="form-control">
                                <option value="">全部</option>
                                <option value="0" <c:if test="${surveyInvestigatorCaseIdState == '0'}">selected="selected"</c:if>>未分派</option>
                                <option value="1" <c:if test="${surveyInvestigatorCaseIdState == '1'}">selected="selected"</c:if>>已分派</option>
                            </select>
                        </div>
                    </c:if>
                    <c:if test="${menuCode == 'org-review-list'}">
                        <div class="form-group">
                            初审状态
                            <select onchange="javascript:$('#batchOperateBtn').click();" name="operateState" class="form-control">
                                <option value="0" <c:if test="${operateState == '' || operateState == '0'}">selected="selected"</c:if>>初审中</option>
                                <option value="1" <c:if test="${operateState == '1'}">selected="selected"</c:if>>初审通过</option>
                            </select>
                        </div>
                        <%--<div class="form-group">
                            案件状态:
                            <select name="surveyStateChk"  class="select form-control select-checkbox" data-select-name="surveyStates"  data-select-values="${surveyStates}" multiple >
                                <option value="12">调查中</option>
                                <option value="22">平台复审中</option>
                                <option value="24">保司终审中</option>
                                <option value="28">保司终审通过</option>
                                <option value="34">已结案</option>
                            </select>
                        </div>--%>
                    </c:if>
                    <c:if test="${menuCode == 'assign-org-list'}">
                        <div class="form-group">
                            调查员:
                            <div>
                                <select class="select form-control select-checkbox" name="investigatorsChk" data-select-name="investigators" data-select-values="${investigators}" multiple style="width: 210px;">
                                    <c:forEach items="${investigatorList}" var="item">
                                        <option value="${item.userId}" data-name="调查中,超期" data-number="${item.opinionPoll},${item.overdue}" >${item.realName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            任务状态
                            <select onchange="javascript:$('#batchOperateBtn').click();" name="surveyOperateState" class="form-control">
                                <option value="">全部</option>
                                <option value="1" <c:if test="${surveyOperateState == '1'}">selected="selected"</c:if> >调查中</option>
                                <option value="2" <c:if test="${surveyOperateState == '2'}">selected="selected"</c:if> >已提交</option>
                            </select>
                        </div>
                        <div class="form-group">
                            调查员截止时间：
                            <input name="surveyStartDate" type="text" value="${surveyStartDate}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                            <span>--</span>
                            <input name="surveyEndDate" type="text" value="${surveyEndDate}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        </div>
                        <div class="form-group">
                            调查员时效状态：
                            <select class="form-control" name="agingType">
                                <option value="0" <c:if test="${agingType == 0}">selected</c:if>>全部</option>
                                <option value="1" <c:if test="${agingType == 1}">selected</c:if> >时效内</option>
                                <option value="2" <c:if test="${agingType == 2}">selected</c:if> >即将超期</option>
                                <option value="3" <c:if test="${agingType == 3}">selected</c:if> >已超期</option>
                                <option value="4" <c:if test="${agingType == 4}">selected</c:if> >超期5天之内</option>
                                <option value="5" <c:if test="${agingType == 5}">selected</c:if> >超期5天至10天</option>
                                <option value="6" <c:if test="${agingType == 6}">selected</c:if> >超期10天至20天</option>
                                <option value="7" <c:if test="${agingType == 7}">selected</c:if> >超期20天以上</option>
                            </select>
                        </div>
                    </c:if>
                    <c:if test="${menuCode == 'extension-time'}">
                        <div class="form-group">
                            当前状态
                            <select onchange="javascript:$('#batchOperateBtn').click();" name="extensionState" class="form-control">
                                <option value="1" <c:if test="${extensionState == '1'}">selected="selected"</c:if> >待审核</option>
                                <option value="2" <c:if test="${extensionState == '2'}">selected="selected"</c:if> >审核通过</option>
                                <option value="3" <c:if test="${extensionState == '3'}">selected="selected"</c:if> >已驳回</option>
                            </select>
                        </div>
                    </c:if>
<%--                    是否--%>
                    <c:if test="${menuCode == 'help-review' && showFinalUser}">
                        <div class="form-group chk-content">
                            复审人员:
                            <%--<div id="demo1" class="xm-select-demo"></div>--%>
                            <%--<div >--%>
                                <%--<select class="select form-control select-checkbox" name="finalInfosChk" data-select-name="finalInfos" data-select-values="${finalInfos}" multiple >--%>
                                    <%--<c:forEach items="${finalUserInfos}" var="item">--%>
                                        <%--<option value="${item.userId}" data-name="待审核,超期" data-number="${item.oprNum},${item.oprOverNum}" >${item.userName}</option>--%>
                                    <%--</c:forEach>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                        </div>
                    </c:if>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                    </div>
                    <c:if test="${menuCode == 'help-review' && showFinalUser}">
                        <div class="btn-group">
                            &nbsp; &nbsp;<button id="filterOpr" type="button" class="btn btn-default"><i class="layui-icon layui-icon-set"></i>  复审人员列表</button>&nbsp; &nbsp;
                        </div>
                    </c:if>
                    <input type="hidden" value='${finalUserInfosJson}' id="finalUserInfosJson">
                </form>
            </div>
        </div>
        <div class="table-content">
            <c:if test="${menuCode != 'assign-org-list'}">
                <table style="cursor: pointer;" class="table table-hover">
                    <thead>
                    <tr>
                        <th width="100">案件编号</th>
                        <c:if test="${ menuCode == 'help-review'}">
                            <%--                        <th width="100">调查编号</th>--%>
                        </c:if>
                        <th width="150">保险公司</th>
                        <c:if test="${menuCode == 'org-review-list'}">
<%--                            <th width="100">调查机构</th>--%>
                        </c:if>
                        <th width="100">被调查人</th>
                        <th width="150">领域</th>
                        <th width="150">业务类型</th>
                        <c:if test="${ menuCode == 'extension-time' || menuCode == 'org-review-list'}">
                            <c:if test="${menuCode == 'org-review-list'}">
                                <th width="150">调查员</th>
                            </c:if>
                            <c:if test="${menuCode != 'org-review-list' && menuCode != 'extension-time'}">
                                <th width="150">案件阶段</th>
                            </c:if>
                        </c:if>
                            <%--                    <c:if test="${ menuCode == 'extension-time'}">--%>
                            <%--                        <th width="150">案件状态</th>--%>
                            <%--                    </c:if>--%>
                            <%--<c:if test="${menuCode == 'org-review-list'}">
                                <th width="150">分派状态</th>
                            </c:if>--%>
                        <c:if test="${menuCode == 'extension-time' || menuCode == 'task-org-review' || menuCode == 'help-review'}">
                            <th width="150">调查机构</th>
                        </c:if>
                        <c:if test="${menuCode != 'extension-time'}"><th width="150">机构案件状态</th></c:if>
                        <c:if test="${menuCode == 'help-review'}">
                            <th width="150">委托时间</th>
                        </c:if>
                        <c:if test="${menuCode == 'extension-time'}">
                            <th width="150">
                                <div class="title_sort" data-id="50" data-value="" data-field="oldOrgEndTime">
                                    <span>原机构截止时间</span>
                                    <div class="icon-sort">
                                        <div class="icon-up" ></div>
                                        <div class="icon-down" ></div>
                                    </div>
                                </div>
                            </th>
                            <th width="150">
                                <div class="title_sort" data-id="50" data-value="" data-field="extensionTime">
                                    <span>申请时间至</span>
                                    <div class="icon-sort">
                                        <div class="icon-up" ></div>
                                        <div class="icon-down" ></div>
                                    </div>
                                </div>
                            </th>
                            <c:if test="${menuCode == 'extension-time'}">
                                <th width="150">
                                    <div class="title_sort" data-id="50" data-value="" data-field="endTime">
                                        <span>案件截止时间</span>
                                        <div class="icon-sort">
                                            <div class="icon-up" ></div>
                                            <div class="icon-down" ></div>
                                        </div>
                                    </div>
                                </th>
                                <th width="150">处理人</th>
                                <th width="150">
                                    <div class="title_sort" data-id="50" data-value="" data-field="oupdateTime">
                                        <span>处理时间</span>
                                        <div class="icon-sort">
                                            <div class="icon-up" ></div>
                                            <div class="icon-down" ></div>
                                        </div>
                                    </div>
                                </th>
                            </c:if>
                        </c:if>
                        <c:if test="${menuCode == 'task-org-review' || menuCode == 'org-review-list'}">
                            <c:if test="${menuCode != 'org-review-list'}">
                                <th width="100">预审状态</th>
                            </c:if>
                            <th width="100">
                                <div class="title_sort" data-id="" data-value="" data-field="dispatchTime">
                                    <span>分派机构时间</span>
                                    <div class="icon-sort">
                                        <div class="icon-up"></div>
                                        <div class="icon-down"></div>
                                    </div>
                                </div>
                            </th>
                            <c:if test="${menuCode != 'org-review-list'}">
                                <th width="100">
                                    <div class="title_sort" data-id="" data-value="" data-field="reportDate">
                                        <span>机构提交时间</span>
                                        <div class="icon-sort">
                                            <div class="icon-up"></div>
                                            <div class="icon-down"></div>
                                        </div>
                                    </div>
                                </th>
                            </c:if>
                            <th width="100">
                                <div class="title_sort" data-id="" data-value="" data-field="orgEndTime">
                                    <span>机构截止时间</span>
                                    <div class="icon-sort">
                                        <div class="icon-up"></div>
                                        <div class="icon-down"></div>
                                    </div>
                                </div>
                            </th>
                            <th width="100">机构时效</th>
                        </c:if>
                            <%-- <c:if test="${menuCode == 'org-review-list'}">
                                 &lt;%&ndash;<th width="150">保单号</th>&ndash;%&gt;
                                 <th width="150">
                                     <div class="title_sort" data-id="50" data-value="" data-field="createTime">
                                         <span>创建时间</span>
                                         <div class="icon-sort">
                                             <div class="icon-up" ></div>
                                             <div class="icon-down" ></div>
                                         </div>
                                     </div>
                                 </th>
                             </c:if>--%>
                        <c:if test="${menuCode == 'help-review'}">
                            <th width="150">
                                <div class="title_sort" data-id="50" data-value="" data-field="reportDate">
                                    <span>调查完成时间</span>
                                    <div class="icon-sort">
                                        <div class="icon-up" ></div>
                                        <div class="icon-down" ></div>
                                    </div>
                                </div>
                            </th>
                            <th width="150">复审人员</th>
                            <th width="150">审核时效</th>
                            <th width="150">
                                <div class="title_sort" data-id="50" data-value="" data-field="endTime">
                                    <span>案件截止时间</span>
                                    <div class="icon-sort">
                                        <div class="icon-up" ></div>
                                        <div class="icon-down" ></div>
                                    </div>
                                </div>
                            </th>
                        </c:if>
<%--                        <th width="80">操作</th>--%>
                    </tr>
                    </thead>
                    <tbody class="class-list">
                    <c:forEach items="${apiRsp.results}" var="item">
                        <c:if test="${item.isShow == true}">
                            <tr title="单击打开${item.surveyRiskCase.surveyCaseNo}详情"
                            <c:if test="${menuCode == 'extension-time'}">
                                onclick="examine(${item.surveyRiskCaseInfo.id},${item.id},${item.assignOrgExtensionId})"
                            </c:if>
                            <c:if test="${menuCode != 'extension-time'}">
                                onclick="info(${item.surveyRiskCaseInfo.id},${item.id})"
                            </c:if>
                                 <c:if test="${menuCode == 'org-review-list' && (item.orgOpinion != null)}">bgcolor="#f9e3e4"</c:if>
                                <c:if test="${item.isOverTime!=null && item.isOverTime == true}">style="color: red" </c:if>>
                                <td width="100">
                                    <c:if test="${item.urgent && menuCode == 'org-review-list'}"><span style="background-color: #FF0000;color: aliceblue;border: 1px solid red;width: 20px;display: block;float: left;text-align: center;margin-right: 2px">急</span></c:if>
                                    <a href="javascript:void(0);" onclick="info(${item.surveyRiskCaseInfo.id},${item.id})">${item.surveyRiskCase.surveyCaseNo}</a>
                                </td>
                                <c:if test="${menuCode == 'help-review'}">
                                    <%--                                <td width="100">${item.surveyRiskCaseInfo.surveyNo}</td>--%>
                                </c:if>
                                <td width="150">${item.surveyRiskCaseInfo.entrustOrgName}</td>
                                <c:if test="${menuCode == 'org-review-list'}">
<%--                                    <td width="100">${item.surveyOrgName}</td>--%>
                                </c:if>
                                <td width="100">${item.surveyRiskCase.surveyPerson}</td>
                                <td width="150">${item.surveyRiskCaseInfo.surveyBusName}</td>
                                <td width="150">
                                    <span <c:if test="${item.servicesId == 13}">class="span-sd"</c:if> >${item.servicesName}</span>
                                </td>
                                <c:if test="${ menuCode == 'extension-time' || menuCode == 'org-review-list'}">
                                    <c:if test="${menuCode == 'org-review-list'}">
                                        <td width="150">${item.surveyRiskCaseInfo.surveyUserName}</td>
                                    </c:if>
                                    <c:if test="${menuCode != 'org-review-list'  && menuCode != 'extension-time'}">
                                        <td width="150">
                                            <c:if test="${item.surveyRiskCaseInfo.surveyPhase == 1}">
                                                委托阶段
                                            </c:if>
                                            <c:if test="${item.surveyRiskCaseInfo.surveyPhase == 2}">
                                                调查阶段
                                            </c:if>
                                            <c:if test="${item.surveyRiskCaseInfo.surveyPhase == 3}">
                                                已结案
                                            </c:if>
                                        </td>
                                    </c:if>
                                </c:if>

                                    <%--                            <c:if test="${ menuCode == 'extension-time'}">--%>
                                    <%--                                <td width="150">${item.surveyRiskCaseInfo.surveyStateName}</td>--%>
                                    <%--                            </c:if>--%>
                                    <%-- <c:if test="${menuCode == 'org-review-list'}">
                                         <td width="150">
                                             <c:if test="${item.surveyInvestigatorCaseId != null}">
                                                 已分派
                                             </c:if>
                                             <c:if test="${item.surveyInvestigatorCaseId == null}">
                                                 未分派
                                             </c:if>
                                         </td>
                                     </c:if>--%>
                                <c:if test="${menuCode == 'extension-time' || menuCode == 'task-org-review' || menuCode == 'help-review'}">
                                    <td width="150">${item.surveyOrgName}</td>
                                </c:if>
                                <c:if test="${menuCode != 'extension-time'}">
                                    <td width="150">
                                        <c:if test="${item.orgSurveyState == 0}">待接收</c:if>
                                        <c:if test="${item.orgSurveyState == 1}">已接收</c:if>
                                        <c:if test="${item.orgSurveyState == 2}">初审中</c:if>
                                        <c:if test="${item.orgSurveyState == 3}">已拒绝</c:if>
                                            <%--<c:if test="${item.orgSurveyState == 4}">初审通过</c:if>--%>
                                        <c:if test="${item.orgSurveyState == 4}">
                                            <c:if test="${item.reviewUserId != null}">
                                                <c:if test="${item.reviewTime != null}">
                                                    复审通过
                                                </c:if>
                                                <c:if test="${item.reviewTime == null}">
                                                    复审中
                                                </c:if>
                                            </c:if>
                                            <c:if test="${item.reviewUserId == null}">
                                                初审通过
                                            </c:if>
                                        </c:if>
                                        <c:if test="${item.orgSurveyState == 5}">复核退回</c:if>
                                        <c:if test="${item.orgSurveyState == 6}">初审中(有退回)</c:if>
                                    </td>
                                </c:if>
                                <c:if test="${menuCode == 'help-review'}">
                                    <td width="150"><fmt:formatDate value="${item.surveyRiskCase.entrustTime}" pattern="yyyy-MM-dd"/></td>
                                </c:if>
                                <c:if test="${menuCode == 'extension-time'}">
                                    <td width="150"><fmt:formatDate value="${item.orgEndTime}" pattern="yyyy-MM-dd"/></td>
                                    <td width="150"><fmt:formatDate value="${item.extensionTime}" pattern="yyyy-MM-dd"/></td>
                                    <td width="150"><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd"/></td>
                                    <td width="150">${item.oupdateBy}</td>
                                    <td width="150"><fmt:formatDate value="${item.oupdateTime}" pattern="yyyy-MM-dd"/></td>
                                </c:if>
                                <c:if test="${menuCode == 'task-org-review' || menuCode == 'org-review-list'}">
                                    <c:if test="${menuCode != 'org-review-list'}">
                                        <td width="100">
                                            <c:if test="${item.reviewOff == 0}">待预审</c:if>
                                            <c:if test="${item.reviewOff == 1}">预审通过</c:if>
                                        </td>
                                    </c:if>
                                    <c:if test="${menuCode != 'org-review-list'}">
                                        <td width="100"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                    </c:if>
                                    <c:if test="${menuCode == 'org-review-list'}">
                                        <td width="100"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></td>
                                    </c:if>
                                    <c:if test="${menuCode != 'org-review-list'}">
                                        <td width="100"><fmt:formatDate value="${item.reportDate}" pattern="yyyy-MM-dd HH:mm"/></td>
                                    </c:if>
                                    <c:if test="${menuCode == 'org-review-list'}">
                                        <td width="100"><fmt:formatDate value="${item.orgEndTime}" pattern="yyyy-MM-dd"/></td>
                                    </c:if>
                                    <c:if test="${menuCode != 'org-review-list'}">
                                        <td width="100"><fmt:formatDate value="${item.orgEndTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                    </c:if>
                                    <td width="100">
                                        <span style="color: ${item.efficiencyStateColor};">${item.efficiencyState}</span>
                                    </td>
                                </c:if>
                                    <%--  <c:if test="${menuCode == 'org-review-list'}">
                                          &lt;%&ndash;<td width="150">${item.surveyRiskCase.policyNo}</td>&ndash;%&gt;
                                          <td width="150"><fmt:formatDate value="${item.surveyRiskCaseInfo.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                      </c:if>--%>
                                <c:if test="${menuCode == 'help-review'}">
                                    <td><fmt:formatDate value="${item.reportDate}" pattern="yyyy-MM-dd HH:mm"/></td>
                                    <td>${item.reviewUserName}</td>
                                    <td>
                                        <c:if test="${item.oprOver}"><span style="color: red;">${item.oprOverTimeStr}</span></c:if>
                                        <c:if test="${!item.oprOver}"><span style="color: green;">${item.oprOverTimeStr}</span></c:if>
                                    </td>
                                    <td><fmt:formatDate value="${item.surveyRiskCaseInfo.endTime}" pattern="yyyy-MM-dd"/></td>
                                </c:if>
<%--                                <td>--%>
<%--                                    <c:if test="${menuCode == 'extension-time'}">--%>
<%--                                        <a href="javascript:void(0);" onclick="examine(${item.surveyRiskCaseInfo.id},${item.id},${item.assignOrgExtensionId})">处理</a>--%>
<%--                                    </c:if>--%>
<%--                                    <c:if test="${menuCode != 'extension-time'}">--%>
<%--                                        <a href="javascript:void(0);" onclick="info(${item.surveyRiskCaseInfo.id},${item.id})">处理</a>--%>
<%--                                    </c:if>--%>
<%--                                </td>--%>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${menuCode == 'assign-org-list'}">
                <table style="cursor: pointer;" class="table table-spec table-hover">
                    <thead>
                    <tr>
                        <th width="100">案件编号</th>
                        <th width="150">保险公司</th>
                        <th width="150">调查机构</th>
                        <th width="150">被调查人</th>
                        <th width="150">业务类型</th>
                        <th width="150">机构案件状态</th>
                        <th width="150">
                            <div class="title_sort" data-id="50" data-value="" data-field="createTime">
                                <span>分派机构时间</span>
                                <div class="icon-sort">
                                    <div class="icon-up" ></div>
                                    <div class="icon-down" ></div>
                                </div>
                            </div>
                        </th>
                        <th width="150">分派状态</th>

                        <th width="150">调查员</th>
                        <th width="150">
                            <div class="title_sort" data-id="50" data-value="" data-field="assignDate">
                                <span>分派调查员时间</span>
                                <div class="icon-sort">
                                    <div class="icon-up" ></div>
                                    <div class="icon-down" ></div>
                                </div>
                            </div>
                        </th>
                        <th width="150">
                            <div class="title_sort" data-id="50" data-value="" data-field="creportDate">
                                <span>调查员完成时间</span>
                                <div class="icon-sort">
                                    <div class="icon-up" ></div>
                                    <div class="icon-down" ></div>
                                </div>
                            </div>
                        </th>
                        <th width="150">
                            <div class="title_sort" data-id="50" data-value="" data-field="surveyEndTime">
                                <span>调查员截止时间</span>
                                <div class="icon-sort">
                                    <div class="icon-up" ></div>
                                    <div class="icon-down" ></div>
                                </div>
                            </div>
                        </th>
                        <th width="150">调查员时效</th>
                        <th width="150">任务状态</th>

                            <%--<th width="150">机构提交时间</th>--%>
                        <th width="150">
                            <div class="title_sort" data-id="50" data-value="" data-field="orgEndTime">
                                <span>机构截止时间</span>
                                <div class="icon-sort">
                                    <div class="icon-up" ></div>
                                    <div class="icon-down" ></div>
                                </div>
                            </div>
                        </th>
                        <th width="150">机构时效</th>
<%--                        <th width="80">操作</th>--%>
                    </tr>
                    </thead>
                    <tbody class="class-list">
                    <c:forEach items="${apiRsp.results}" var="item">
                        <tr title="单击打开${item.surveyRiskCase.surveyCaseNo}详情" onclick="info(${item.surveyRiskCaseInfo.id},${item.id},${item.surveyOrgId})" <c:if test="${item.isOverTime!=null && item.isOverTime == true}">style="color: red" </c:if>
                            <c:if test="${item.surveyReturn == 1}">bgcolor="#f9e3e4" </c:if>
                        >
                            <td <c:if test="${item.investigatorCaseInfos.size() > 0}">rowspan="${item.investigatorCaseInfos.size()}" </c:if>>${item.surveyRiskCase.surveyCaseNo}</td>
                            <td <c:if test="${item.investigatorCaseInfos.size() > 0}">rowspan="${item.investigatorCaseInfos.size()}" </c:if>>${item.surveyRiskCaseInfo.entrustOrgName}</td>
                            <td <c:if test="${item.investigatorCaseInfos.size() > 0}">rowspan="${item.investigatorCaseInfos.size()}" </c:if>>${item.surveyOrgName}</td>
                            <td <c:if test="${item.investigatorCaseInfos.size() > 0}">rowspan="${item.investigatorCaseInfos.size()}" </c:if>><c:if test="${item.urgent && menuCode == 'assign-org-list'}"><span style="background-color: #FF0000;color: aliceblue;border: 1px solid red;width: 20px;display: block;float: left;text-align: center;margin-right: 2px">急</span></c:if><c:if test="${item.newCase == 1 && menuCode == 'assign-org-list'}"><span style="background-color: #FF0000;color: aliceblue;border: 1px solid red;width: 20px;display: block;float: left;text-align: center;">新</span></c:if>${item.surveyRiskCase.surveyPerson}</td>
                            <td <c:if test="${item.investigatorCaseInfos.size() > 0}">rowspan="${item.investigatorCaseInfos.size()}" </c:if>>
                                <span <c:if test="${item.servicesId == 13}">class="span-sd"</c:if> >${item.servicesName}</span>
                            </td>
                            <td <c:if test="${item.investigatorCaseInfos.size() > 0}">rowspan="${item.investigatorCaseInfos.size()}" </c:if>>
                                <c:if test="${item.orgSurveyState == 0}">待接收</c:if>
                                <c:if test="${item.orgSurveyState == 1}">调查中</c:if>
                                <c:if test="${item.orgSurveyState == 2}">初审中</c:if>
                                <c:if test="${item.orgSurveyState == 3}">已拒绝</c:if>
                                <c:if test="${item.orgSurveyState == 4}">初审通过</c:if>
                                <c:if test="${item.orgSurveyState == 5}">复核退回</c:if>
                                <c:if test="${item.orgSurveyState == 6}">初审中(有退回)</c:if>
                            </td>
                            <td <c:if test="${item.investigatorCaseInfos.size() > 0}">rowspan="${item.investigatorCaseInfos.size()}" </c:if>><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></td>
                            <td <c:if test="${item.investigatorCaseInfos.size() > 0}">rowspan="${item.investigatorCaseInfos.size()}" </c:if>>
                                <c:if test="${item.surveyInvestigatorCaseId != null}">
                                    已分派
                                </c:if>
                                <c:if test="${item.surveyInvestigatorCaseId == null}">
                                    未分派
                                </c:if>
                            </td>

                            <td><c:if test="${item.investigatorCaseInfos.size()>0}">${item.investigatorCaseInfos.get(0).surveyUserName}<c:if test="${item.investigatorCaseInfos.get(0).showKey}">（主）</c:if></c:if></td>
                            <td><c:if test="${item.investigatorCaseInfos.size()>0}"><fmt:formatDate value="${item.investigatorCaseInfos.get(0).assignDate}" pattern="yyyy-MM-dd"/></c:if></td>
                            <td><c:if test="${item.investigatorCaseInfos.size()>0}"><fmt:formatDate value="${item.investigatorCaseInfos.get(0).creportDate}" pattern="yyyy-MM-dd"/></c:if></td>
                            <td><c:if test="${item.investigatorCaseInfos.size()>0}"><fmt:formatDate value="${item.investigatorCaseInfos.get(0).surveyEndTime}" pattern="yyyy-MM-dd"/></c:if></td>
                            <td><c:if test="${item.investigatorCaseInfos.size()>0}"><span style="color: ${item.investigatorCaseInfos.get(0).efficiencyStateColor}">${item.investigatorCaseInfos.get(0).efficiencyState}</span></c:if></td>
                            <td><c:if test="${item.investigatorCaseInfos.size()>0}">${item.investigatorCaseInfos.get(0).surveyStateName}</c:if></td>

                                <%--<td <c:if test="${item.investigatorCaseInfos.size() > 0}">rowspan="${item.investigatorCaseInfos.size()}" </c:if>><fmt:formatDate value="${item.reportDate}" pattern="yyyy-MM-dd"/></td>--%>
                            <td <c:if test="${item.investigatorCaseInfos.size() > 0}">rowspan="${item.investigatorCaseInfos.size()}" </c:if>><fmt:formatDate value="${item.orgEndTime}" pattern="yyyy-MM-dd"/></td>
                            <td <c:if test="${item.investigatorCaseInfos.size() > 0}">rowspan="${item.investigatorCaseInfos.size()}" </c:if>><span style="color: ${item.efficiencyStateColor}">${item.efficiencyState}</span></td>
<%--                            <td <c:if test="${item.investigatorCaseInfos.size() > 0}">rowspan="${item.investigatorCaseInfos.size()}" </c:if>>--%>
<%--                                <a href="javascript:void(0);" onclick="info(${item.surveyRiskCaseInfo.id},${item.id},${item.surveyOrgId})">处理</a>--%>
<%--                            </td>--%>
                        </tr>
                        <c:forEach items="${item.investigatorCaseInfos}" var="cases" varStatus="st">
                            <c:if test="${st.index > 0}">
                                <tr>
                                    <td class="td-spec">${cases.surveyUserName}</td>
                                    <td class="td-spec"><fmt:formatDate value="${cases.assignDate}" pattern="yyyy-MM-dd"/></td>
                                    <td class="td-spec"><fmt:formatDate value="${cases.creportDate}" pattern="yyyy-MM-dd"/></td>
                                    <td class="td-spec"><fmt:formatDate value="${cases.surveyEndTime}" pattern="yyyy-MM-dd"/></td>
                                    <td class="td-spec"><span style="color: ${cases.efficiencyStateColor}">${cases.efficiencyState}</span></td>
                                    <td class="td-spec">${cases.surveyStateName}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div><!--panel-info-->

<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/survey/case/listAssign?menuCode=${menuCode}&searchStr=${searchStr}&surveyPerson=${surveyPerson}&orgSurveyState=${orgSurveyState}&surveyStates=${surveyStates}&surveyInvestigatorCaseIdState=${surveyInvestigatorCaseIdState}&surveyPhase=${surveyPhase}&entrustOrgName=${entrustOrgName}&assignState=${assignState}&operateState=${operateState}&policyNo=${policyNo}&surveyCaseNo=${surveyCaseNo}&surveyUserName=${surveyUserName}&surveyStartDate=${surveyStartDate}&surveyEndDate=${surveyEndDate}&surveyOperateState=${surveyOperateState}&extensionState=${extensionState}&investigators=${investigators}&entrustOrgIds=${entrustOrgIds}&surveyOrgIds=${surveyOrgIds}&serviceTypes=${serviceTypes}&oprReview=${oprReview}&finalInfos=${finalInfos}&agingType=${agingType}&sortField=${sortField}&sortType=${sortType}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->
<input type="hidden" value="${showFinalUserId}" id="showFinalUserId">
<script type="text/html" id="edit-filterOpr">
    <div class="edit-filterOpr">
        <div>请选择需要关注的复审人员</div>
        <div>（数据保存在浏览器本地，清除浏览器数据后记得重新勾选）</div>
        <form class="layui-form" lay-filter="formTest" >
            <div class="input-content layui-form-item" pane>


            </div>
            <div class="layui-form-item" >
                <div class="layui-input-block">
                    <div class="layui-btn layui-btn-primary close-reason">取消</div>
                    <div class="layui-btn layui-btn-normal submit-reason" lay-submit lay-filter="checkBoxs" >保存并关闭</div>
                </div>
            </div>
        </form>
    </div>
</script>

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect2.js?V=1"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/layui/layui.js"></script>
<script>
    var finalUserInfosJson = $("#finalUserInfosJson").val();
    if ($('#menuCode').val() == 'help-review'){
        finalUserInfosJson = JSON.parse(finalUserInfosJson);
    }else{
        finalUserInfosJson =[];
    }
    var showFinalUserId = $('#showFinalUserId').val()


    $(".title_sort").click(function () {
        // debugger
        var sortField = $(this).attr("data-field");
        var sortType = 'up';
        var $children = $(this).children();
        if ($children.find(".active").html()==undefined){
            $children.find(".icon-up").addClass("active");
        }else {
            var t = $children.find(".active").attr("class");
            if (t.toString().indexOf("up")>0){
                $children.find(".icon-up").removeClass("active");
                $children.find(".icon-down").addClass("active");
                sortType = 'down';
            }else {
                $children.find(".icon-up").addClass("active");
                $children.find(".icon-down").removeClass("active");
                sortType = 'up';
            }
        }
        $("#sortField").val(sortField);
        $("#sortType").val(sortType);
        $('#batchOperateBtn').click();
    });

    $(function () {
        var height_doc = window.parent.innerHeight - 50 -27 -66 -$('.panel-heading').height() - 70
        $(".table-content").height(height_doc).css({
            overflow: 'auto'
        });



        var sortType = $("#sortType").val();
        var sortField= $("#sortField").val();
        if (sortType!=null && sortType !=''){
            $("div[data-field="+sortField+"]").find(sortType=='up'?'.icon-up':'.icon-down').addClass("active");
        }
        //默认选中 select
        $(".select-checkbox").each(function () {
            var value = $(this).attr("data-select-values");
            if (value) {
                $(this).val(value.replace(/\s*/g, '').split(','));
            } else {
                $(this).val("");
            }
        })

        var checkedData = JSON.parse(localStorage.getItem(showFinalUserId)) || []
        $(".select-checkbox").UCFormSelect();
        $(".UCSelect[name=investigatorsChk]").width(350);
        $(".UCSelect[name=entrustOrgIdsChk]").width(320);
        setMS(checkedData,finalUserInfosJson)

        layui.use(['form','layer'],function () {
            var  layer = layui.layer,
                form = layui.form

            $('#filterOpr').click(function () {
                reasonIndex = layer.open({
                    type: 1,
                    title: '复审人员列表',
                    area: ['500px', '500px'],
                    content: $('#edit-filterOpr').html(),
                    success: function () {
                        var _html = ''
                        if ($('.input-content .list .layui-form-checked').length != finalUserInfosJson.length){
                             _html = '<div class="all"><input type="checkbox" name="all" lay-skin="primary" lay-filter="all" title="全选" /></div><div class="list">'

                        }else {
                             _html = '<div class="all"><input type="checkbox" name="all" lay-skin="primary" lay-filter="all" title="全选" checked /></div><div class="list">'

                        }
                        var ids = []
                        var trueList = {},falseList={}
                        if (!checkedData.length){
                            finalUserInfosJson.map(function (cur) {
                                Object.assign(trueList,{[cur.userId]: true})
                                Object.assign(falseList,{[cur.userId]: false})
                                _html +=  '<input class="inputCheck" type="checkbox" name="'+cur.userId+'" lay-skin="primary" lay-filter="one" title="'+cur.userName+'" checked>'
                            })
                        }else {
                            finalUserInfosJson.map(function (cur) {
                                Object.assign(trueList,{[cur.userId]: true})
                                Object.assign(falseList,{[cur.userId]: false})
                                var id = cur.userId.toString()
                                if (checkedData.indexOf(id) > -1){
                                    _html +=  '<input class="inputCheck"  type="checkbox" name="'+cur.userId+'" lay-skin="primary" lay-filter="one" title="'+cur.userName+'" checked>'
                                } else {
                                    _html +=  '<input class="inputCheck"  type="checkbox" name="'+cur.userId+'" lay-skin="primary" lay-filter="one"  title="'+cur.userName+'">'
                                }
                            })

                        }
                        _html += '</div>'
                        $('.input-content').html(_html)
                        form.render()
                        if (finalUserInfosJson.length == $('.input-content .list .layui-form-checked').length){
                            form.val('formTest',{all: true})
                        }else {
                            form.val('formTest',{all: false})
                        }
                        form.on('checkbox(all)', function(data){
                            if (data.elem.checked){
                                form.val('formTest',trueList)
                            }else{
                                console.log(falseList)
                                form.val('formTest',falseList)
                            }

                        });
                        form.on('checkbox(one)', function(data){
                            var len = finalUserInfosJson.length
                            var newLen = $('.input-content .list .layui-form-checked').length
                            if (len == newLen){
                                form.val('formTest',{all: true})
                            }else {
                                form.val('formTest',{all: false})
                            }

                        });
                        $('.submit-reason').click(function () {
                            $(this).addClass('disabled')
                            checkedData = []
                            $('.input-content .list .layui-form-checkbox').each(function (i,cur) {
                                if ($(cur).hasClass('layui-form-checked')){
                                    var spanText = $(cur).find('span').text()
                                    checkedData.push($('input[title='+spanText+']').attr('name'))
                                }
                            })
                            localStorage.setItem(showFinalUserId, JSON.stringify(checkedData))
                            setMS(checkedData)
                            $(this).removeClass('disabled')
                            layer.close(reasonIndex)
                        })
                        $('.close-reason').click(function () {
                            layer.close(reasonIndex)
                        })

                    }
                });

            })
        })

    });

    function setMS(checkedData){


        var newJson =[]
        var _html = '复审人员：'
        _html += '<div >' + '<select class="select form-control finalInfosChk select-checkbox" name="finalInfosChk" data-select-name="finalInfos" data-select-values="${finalInfos}" multiple >'
        if (checkedData.length){
            finalUserInfosJson.map(function(cur){
                var id = cur.userId.toString()
                if (checkedData.indexOf(id) > -1){
                    newJson.push(cur)
                    _html+= '<option value="'+cur.userId+'" data-name="待审核,超期" data-number="'+cur.oprNum+','+cur.oprOverNum+'" >'+cur.userName+'</option>'
                }
            })
        } else {
            finalUserInfosJson.map(function(cur){
                _html+= '<option value="'+cur.userId+'" data-name="待审核,超期" data-number="'+cur.oprNum+','+cur.oprOverNum+'" >'+cur.userName+'</option>'
            })
        }

        _html += '</select></div>'

        $('.chk-content').html(_html)

        var finalInfosChk= $(".finalInfosChk")

        var value = finalInfosChk.attr("data-select-values");
        if (value) {
            finalInfosChk.val(value.replace(/\s*/g, '').split(','));
        } else {
            finalInfosChk.val("");
        }
        finalInfosChk.UCFormSelect();
        $(".UCSelect[name=finalInfosChk]").width(350);

    }
    $("#batchOperateBtn").click(function(){
        //select 多选 赋值 到隐藏域。 便于后台传值
        $(".select-checkbox").each(function(){
            var selName = $(this).attr("name");
            var all = $(".UCSelect[name="+selName+"]").find(".UCSelectAll").hasClass("Selected");
            var name = $(this).attr("data-select-name");
            if(name){
                console.log(name, $(this).val())
                $("#" + name).val($(this).val());
            }
        })
    });



    $(document).ready(function () {



        $('.btn-1').off("click");
        $('.btn-1').click(function(e){
            $(".UCSelect").find('.SelectVal').removeClass('over');
            $(".UCSelect").find("select").UCFormSelect('close');
            $("#batchOperateBtn").click();
        });

        $(document).off('mousedown');
        $(document).bind('mousedown', function (e) {
            var Event = e.target;
            $('.UCSelect .SelectBox').each(function () {
                var Select = $(this).parents(".UCSelect").find("select").get(0);
                var Events = $(Event).parents(".UCSelect").find("select").get(0);
                if (!(Event && Select && Select == Events)) {
                    if( $(this).parents(".UCSelect").find('.SelectVal').hasClass("over")){
                        $("#batchOperateBtn").click();
                    }
                    $(this).parents(".UCSelect").find('.SelectVal').removeClass('over');
                    $(this).parents(".UCSelect").find("select").UCFormSelect('close');
                }
            });
        });


    })

    $('.title_sort').click(function () {
        // var _this = $(this)
        // var id = _this.attr('data-id')
        // $('.title_sort').each(function (i,cur) {
        //     var _cur =  $(cur)
        //     if (_cur.attr('data-id')!= id){
        //         _cur.attr('data-value','')
        //         _cur.find('.icon-sort div').removeClass('active')
        //     }
        // })
        // if (_this.attr('data-value') == '1'){
        //     _this.find('.icon-up').removeClass('active')
        //     _this.find('.icon-down').addClass('active')
        //     _this.attr('data-value','2')
        // } else if (_this.attr('data-value') == '2'){
        //     _this.find('.icon-up').removeClass('active')
        //     _this.find('.icon-down').removeClass('active')
        //     _this.attr('data-value','')
        // }else  if (!_this.attr('data-value')){
        //     _this.find('.icon-up').addClass('active')
        //     _this.find('.icon-down').removeClass('active')
        //     _this.attr('data-value','1')
        // }
        // var d_v = _this.attr('data-value')
        // var d_f = _this.attr('data-field')
        // $("#sortFiled").val(d_f);
        // // $("#sortType").val(d);
        // $('#batchOperateBtn').click();

    })

    var add = function(obj){
        var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"新增",
            height:height,
            width:1000,
            url:"${ctx}/survey/case/edit?obj=" + obj,
            load:true
        });
    }
    var info = function(id,assignOrgId,surveyOrgId){
        var selected = getselected();
        if (selected){
            return;
        }

        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"详情",
            height:height,
            width:width,
            url:"${ctx}/survey/case/info?id=" + id + "&menuCode=${menuCode}&assignOrgId=" + assignOrgId + "&curSurveyOrgId="+surveyOrgId+"&display=true",
            load:true
        });
    }

    function getselected(){
        if (window.getSelection){
            return window.getSelection().toString();
        }else if (document.getSelection){
            return document.getSelection().toString;
        }else{
            var selection = document.selection && document.selection.createRange();
            if (selection.text){
                return selection.text.toString;
            }
            return "";
        }
    }

    var examine = function(id,assignOrgId,assignOrgExtensionId){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"审核",
            height:height,
            width:width,
            url:"${ctx}/survey/case/sic/operateView?id="+assignOrgId+"&btnCode=${menuCode}&roleCode=lfManager&assignOrgExtensionId="+assignOrgExtensionId,
            load:true
        });
    }
    


</script>
</body>
</html>
