<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>分派调查员</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">

    <style>
        .form-group{
            margin-bottom: 10px!important;
            padding-right: 16px;
        }
        .time{
            width: 130px!important;
        }
        .title{
            width: 85px;
            font-weight: normal;
        }
        .form-control{

        }

        .span-must{
            color: red;
            font-weight: bold;
        }
    </style>
    <style>
        .table-content {
            /*width: 1694px;*/
            overflow: auto;
        }
        table th {
            text-align: center;
            border-left: 2px solid #ddd;
            vertical-align: middle!important;
        }
        thead{
            background-color: #ecf0f1;
        }
        table td {
            /*text-align: center;*/
            border-left: 1px solid #ddd;
        }

    </style>
    <style>
        .items{
            width: 600px;
            height: 252px;
            padding: 20px;
            padding-right: 0;
            text-align: center;
            border: 1px solid #66C8FF;
            margin: 20px auto;
        }
        .item{
            float: left;
            width: 160px;
            /*height: 40px;*/
            padding: 10px 0;
            line-height: 20px;
            border:1px solid #66C8FF;
            color: #3ba9ff;
            font-size: 10px;
            text-align: center;
            margin-right: 20px;
            margin-bottom: 20px;
            word-break:break-all;
        }
        div.active{
            background-color: #66C8FF;
            color: #fff;
        }
        .disable {
            pointer-events: none;
        }



        .address-span{
            position: relative;
            left: 253px;
            top: -24px;
            width: 80px;
            cursor: pointer;
            border: 1px solid lightslategrey;
            padding: 2px;
        }
    </style>

    <style type="text/css">
        body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
        #l-map{height:200px;width:100%;}
        #r-result{width:100%;}
    </style>
</head>
<body>
<div class="main administrator">
<div class="panel panel-info">
<div class="panel-heading">
    <div class="pin">
        <%--<form class="form-inline" role="form" action="${ctx}/info/publishs/selectCompany?id=${publishsId}&choose=${choose}" method="post">--%>
            <%--<div class="form-group">--%>
                <%--<label class="title">姓名:</label>--%>
                <%--<input name="safeName" type="text"  value="${safeName}" class="form-control">--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label class="title">电话:</label>--%>
                <%--<input name="safeUser" type="text"  value="${safeUser}" class="form-control">--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label class="title">机构名称:</label>--%>
                <%--<input name="safeTel" type="text"  value="${safeTel}" class="form-control">--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label class="title">称号名称:</label>--%>
                <%--<input name="safeTel" type="text"  value="${safeTel}" class="form-control">--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label class="title">擅长领域:</label>--%>
                <%--<input name="safeTel" type="text"  value="${safeTel}" class="form-control">--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label class="title">覆盖区域:</label>--%>
                <%--<input name="safeTel" type="text"  value="${safeTel}" class="form-control">--%>
            <%--</div>--%>
            <%--<div class="btn-group">--%>
                <%--<button id="batchOperateBtnTo" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;--%>
            <%--</div>--%>
        <%--</form>--%>
    </div>
</div>
<div class="table-content">
    <form id="editForm" role="form" action="${ctx}/survey/case/assignSurveyUserOK" method="post">
        <input type="hidden" name="id" id="id" value="${dto.id}" />
        <input type="hidden" name="entrustOrgId" id="entrustOrgId" value="${dto.entrustOrgId}" />
        <input type="hidden" name="btnCode" id="btnCode" value="${btnCode}" />
        <input type="hidden" name="deepCasesPrice" id="deepCasesPrice">
        <c:if test="${btnCode == '115' || btnCode == '116'}">
            <input type="hidden" name="surveyCaseId" value="${surveyCaseId}" />
        </c:if>
<%--        <c:if test="${btnCode == '113'}">--%>
<%--            <input type="hidden" value="${dto.servicesId}" id="servicesId" name="servicesId" required="required"/>--%>
<%--        </c:if>--%>
        <c:if test="${btnCode == '116' || btnCode == '113'}">
            <input type="hidden" name="curSurveyOrgId" id="curSurveyOrgId" value="${curSurveyOrgId}" />
            <input type="hidden" value="${dto.servicesId}" id="servicesId" name="servicesId" required="required"/>
        </c:if>
        <c:if test="${btnCode == '117'}">
            <input type="hidden" name="orgCaseId" value="${orgCaseId}" />
        </c:if>
        <c:if test="${btnCode == '112' || btnCode == '113'}">
            <input type="hidden" name="backCaseId" value="${backCaseId}" />
        </c:if>
        <c:if test="${btnCode == '112' || btnCode == '117'}">
            <input type="hidden" name="entrustTime" id="entrustTime" value="${dto.surveyRiskCase.entrustTime}" />
            <input type="hidden" name="orgAttr" id="orgAttr" value="${dto.surveyConsignor.orgAttr}" />
            <%--三个时间 为切换业务类型--%>
            <input type="hidden" name="hzEndDate" id="hzEndDate" value="${hzEndDate}" />
            <input type="hidden" name="hzSixDate" id="hzSixDate" value="${hzSixDate}" />
            <input type="hidden" name="hzTenDate" id="hzTenDate" value="${hzTenDate}" />
            <input type="hidden" name="maxDateTime" id="maxDateTime" value="${maxDateTime}" />

            <input type="hidden" name="autoOpenInfo" id="autoOpenInfo" value="${autoOpenInfo}" />

        </c:if>

        <table class="table table-striped">
            <tbody class="class-list">
            <c:if test="${btnCode == '111' || btnCode == '115' || btnCode == '116'}">
                <c:if test="${btnCode == '116'}">
                    <tr>
                        <td>改派原因<span class="span-must">*</span></td>
                        <td colspan="3">
                            <textarea class="form-control" name="orgOpinion" id="orgOpinion" rows="6" value ="" required="required" style="height: 50px"></textarea>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td width="100">任务类型</td>
                    <td colspan="2" style="text-align: left" id="taskTd">
                        <%--<c:forEach items="${dto.surveyTaskTypes}" var="item">
                            <input type="checkbox" checked name="chkTaskIds" value="${item.taskId}">${item.taskName}
                            &nbsp;&nbsp;&nbsp;&nbsp;
                        </c:forEach>--%>
                        <input type="hidden" name="taskIds" id="taskIds" />
                    </td>
                </tr>
                <tr>
                    <td>任务描述</td>
                    <td colspan="2">
                        <textarea class="form-control" name="taskRemark" rows="6">${taskRemark}</textarea>
                    </td>
                </tr>
                <tr style="display: none"><td colspan="3"></td></tr>
                <tr>
                    <td>截止时间</td>
                    <td colspan="2">
                        <input name="endTime" type="text" class="form-control" required="required"
                            value="<fmt:formatDate value="${maxDate}" pattern="yyyy-MM-dd 23:59:59"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate :'<fmt:formatDate value="${minDate}" pattern="yyyy-MM-dd HH:mm:ss" />'})" onfocus="this.blur();"  />
                    </td>
                </tr>
                <c:if test="${btnCode == '111' || btnCode == '115'}">
                    <tr style="display: none"><td colspan="3"></td></tr>
                    <tr>
                        <td width="100">调查机构</td>
                        <td colspan="2" style="text-align: left">
                            <select class="singleSelect form-control" required="required" onchange="changeSurveyOrg('${btnCode}','${dto.id}','surveyOrg')" style="display: inline-flex" name="surveyOrgId" id="surveyOrgId">
                                <option value="">请选择</option>
                                <c:forEach items="${surveyfranchisees}" var="item">
                                    <option value="${item.id}">${item.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </c:if>
                <tr style="display: none"><td colspan="3"></td></tr>
                <tr>
                    <td>调查员</td>
                    <td colspan="2" style="text-align: left">
                        <input type="hidden" name="assignUserId" id="assignUserId" />
                        <select class="form-control" required="required" onchange="changeSurveyUser(this,'surveyUser')" style="display: inline-flex" id="surveyUserId">
                            <option value="">请选择</option>
                            <c:if test="${btnCode == '113' || btnCode == '116'}">
                                <%--<c:forEach items="${surveyUsers}" var="item">--%>
                                    <%--<option value="${item.userId},${item.type}">${item.realName}&nbsp;&nbsp;&nbsp;&nbsp;(调查中案件数：${item.dcyCaseNum})</option>--%>
                                <%--</c:forEach>--%>



                            </c:if>
                        </select>
                    </td>
                </tr>
                <tr style="display: none"><td colspan="3"></td></tr>
                <tr style="display: none;" id="tr_survey_user_type">
                    <td>调查员类型</td>
                    <td style="text-align: left;">
                        <span id="span_survey_primary" style="font-size: 15px;font-weight: bold;">&nbsp;&nbsp;${isSurveyPrimary ? '主调查员' : '辅助调查员'}</span>
                    </td>
                </tr>
                <tr style="display: none"><td colspan="3"></td></tr>
                <c:if test="${btnCode == '111' || btnCode == '115'}">
                    <tr id="tr_payType" style="display: none">
                        <td>付费方式</td>
                        <td colspan="2">
                            <select class="form-control" id="payType" name="payType" disabled>
                                <option value="1" <c:if test="${dto.payType == 1}">selected="selected" </c:if> >一口价</option>
                                <option value="2" <c:if test="${dto.payType == 2}">selected="selected" </c:if> >基本费+减损</option>
                                <option value="3" <c:if test="${dto.payType == 3}">selected="selected" </c:if> >任务</option>
                                <option value="4" <c:if test="${dto.payType == 4}">selected="selected" </c:if> >任务+减损</option>
                            </select>
                        </td>
                    </tr>
                    <tr style="display: none"><td colspan="3"></td></tr>
                    <tr id="tr_payInfo" style="display: none">
                        <td>付费价格</td>
                        <td colspan="2"><input type="number" step="0.00" placeholder="一口价/基本费" id="surveyMoney" name="surveyMoney" class="form-control"></td>
                    </tr>
                    <tr style="display: none"><td colspan="3"></td></tr>
                    <tr id="tr_payInfo2" style="display: none">
                        <td>减损描述</td>
                        <td colspan="2">
                            <textarea rows="2" id="surveryReLoossesRemark" name="surveryReLoossesRemark" class="form-control"></textarea>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td colspan="3" style="text-align: right;margin-right: 20px;">
                        <input type="submit" onclick="return toValid()" class="btn btn-primary" value="确认分派" />
                    </td>
                </tr>
            </c:if>
            <c:if test="${btnCode == '113'}">
                <tr>
                    <td width="100">调查员</td>
                    <td colspan="2" style="text-align: left">
                        <input type="hidden" name="assignUserId" id="assignUserId" />
                        <select class="form-control" required="required" onchange="changeSurveyUser(this,'surveyUser')" style="display: inline-flex" id="surveyUserId">
                            <option value="">请选择</option>
                            <%--<c:forEach items="${surveyUsers}" var="item">--%>
                                <%--<option value="${item.userId},${item.type},${btnCode}">${item.realName}&nbsp;&nbsp;&nbsp;&nbsp;(调查中案件数：${item.dcyCaseNum})</option>--%>
                            <%--</c:forEach>--%>
                        </select>
                    </td>
                </tr>
                <tr style="display: none"><td colspan="3"></td></tr>
                <tr>
                    <td width="100">任务类型</td>
                    <td colspan="2" style="text-align: left" id="taskTd">
                        <%--<c:forEach items="${dto.surveyTaskTypes}" var="item">--%>
                            <%--<input type="checkbox" checked name="chkTaskIds" value="${item.taskId}">${item.taskName}--%>
                            <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
                        <%--</c:forEach>--%>
                        <input type="hidden" name="taskIds" id="taskIds" />
                    </td>
                </tr>
                <tr>
                    <td>任务描述</td>
                    <td colspan="2">
                        <textarea class="form-control" name="taskRemark" rows="6">${taskRemark}</textarea>
                    </td>
                </tr>
                <tr style="display: none"><td colspan="3"></td></tr>
                <tr>
                    <td>截止时间</td>
                    <td colspan="2">
                        <input name="endTime" type="text" class="form-control" required="required"
                               value="<fmt:formatDate value="${maxDate}" pattern="yyyy-MM-dd 23:59:59"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate :'<fmt:formatDate value="${minDate}" pattern="yyyy-MM-dd HH:mm:ss" />'})" onfocus="this.blur();"  />
                    </td>
                </tr>
                <tr style="display: none"><td colspan="3"></td></tr>

                <tr style="display: none;" id="tr_survey_user_type">
                    <td>调查员类型</td>
                    <td style="text-align: left;">
                        <span id="span_survey_primary" style="font-size: 15px;font-weight: bold;">&nbsp;&nbsp;${isSurveyPrimary ? '主调查员' : '辅助调查员'}</span>
                    </td>
                </tr>
                <tr style="display: none"><td colspan="3"></td></tr>
                <tr>
                    <td>指定调查地点</td>
                    <td style="display: flex;">
                        <input type="hidden" name="addressJSON" id="addressJSON" />
                        <input type="hidden" name="addressDetailJSON" id="addressDetailJSON" />
                        <div id="address" class="cell" style="width: 40%;float: left;">
                            <input type="text" id="address1" class="form-control default" name="address1" placeholder="可输入指定调查地点，并在右侧地图核对位置" />
                        </div>
                        <div class="cell" style="width: 10%;">
                            <input type="button" class="btn btn-primary"  value="再加一个" onclick="addAddress()" />
                        </div>
                        <div class="cell" style="width: 50%;height: 200px;float: right">
                            <div id="l-map"></div>
                            <div id="searchResultPanel" style="width:150px;height:auto;display: none;"></div>
                        </div>
                    </td>
                </tr>
                <%--<tr id="tr_payType" style="display: none">
                    <td>付费方式</td>
                    <td colspan="2">
                        <select class="form-control" id="payType" name="payType" disabled>
                            <option value="1" <c:if test="${dto.payType == 1}">selected="selected" </c:if> >一口价</option>
                            <option value="2" <c:if test="${dto.payType == 2}">selected="selected" </c:if> >基本费+减损</option>
                            <option value="3" <c:if test="${dto.payType == 3}">selected="selected" </c:if> >任务</option>
                            <option value="4" <c:if test="${dto.payType == 4}">selected="selected" </c:if> >任务+减损</option>
                        </select>
                    </td>
                </tr>
                <tr style="display: none"><td colspan="3"></td></tr>
                <tr id="tr_payInfo" style="display: none">
                    <td>付费价格</td>
                    <td colspan="2"><input type="number" step="0.00" placeholder="一口价/基本费" id="surveyMoney" name="surveyMoney" class="form-control"></td>
                </tr>
                <tr style="display: none"><td colspan="3"></td></tr>
                <tr id="tr_payInfo2" style="display: none">
                    <td>减损描述</td>
                    <td colspan="2">
                        <textarea rows="2" id="surveryReLoossesRemark" name="surveryReLoossesRemark" class="form-control"></textarea>
                    </td>
                </tr>--%>
                <tr>
                    <td colspan="3" style="text-align: right;margin-right: 20px;">
                        <input type="submit" onclick="return toValid()" class="btn btn-primary" value="确认分派" />
                    </td>
                </tr>
            </c:if>
            <c:if test="${btnCode == '112' || btnCode == '117'}">
                <c:if test="${btnCode == '117'}">
                    <tr>
                        <td>改派原因<span class="span-must">*</span></td>
                        <td colspan="3">
                            <textarea class="form-control" name="orgOpinion" id="orgOpinion" rows="6" value ="" required="required" style="height: 50px"></textarea>
                        </td>
                    </tr>
                </c:if>

                <tr>
                    <td width="15%">调查机构<span class="span-must">*</span></td>
                    <td width="35%" style="text-align: left">
                        <select class="singleSelect form-control" required="required" onchange="changeSurveyOrg('${btnCode}','${dto.id}','surveyOrg')" style="display: inline-flex" name="surveyOrgId" id="surveyOrgId">
                            <option value="">请选择</option>
                            <c:forEach items="${surveyfranchisees}" var="item">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="15%">调查员<span style="color: blue;">(非必选)</span></td>
                    <td width="35%">
                        <input type="hidden" name="assignUserId" id="assignUserId" />
                        <select class="form-control" onchange="changeSurveyUser(this,'surveyOrg')" style="display: inline-flex" id="surveyUserId">
                            <option value="">请选择</option>
                            <%--<c:forEach items="${surveyUsers}" var="item">--%>
                                <%--<option value="${item.userId},${item.type},${btnCode}">${item.realName}&nbsp;&nbsp;&nbsp;&nbsp;${item.titleName}</option>--%>
                            <%--</c:forEach>--%>
                        </select>
                    </td>
                </tr>
                <tr id="tr_task" style="display: none">
                    <td width="100">任务类型<span class="span-must">*</span><input type="hidden" name="taskIds" id="taskIds" /></td>
                    <td colspan="3" style="text-align: left" id="taskTd">

                    </td>
                </tr>
                <tr>
                    <td>任务描述</td>
                    <td colspan="3">
                        <textarea class="form-control" name="taskRemark" id="taskRemark" rows="6" value ="${taskRemark}">${taskRemark}</textarea>
                        <input type="hidden" id="taskRemarkHis" value="${taskRemark}" />
                    </td>
                </tr>
                <tr>
                    <td>业务类型<span class="span-must">*</span></td>
                    <td colspan="8">
                        <c:forEach items="${services}" var="item">
                            <div  class="item
                                    <c:if test="${dto.servicesId == item.id}">active </c:if>
                                    " onclick="choice('services',${item.id},'${item.name}',true)" id="services_${item.id}" name="servicesIds" data-code="${item.id},${item.name}">
                                    ${item.name}
                            </div>
                        </c:forEach>
                        <input type="hidden" value="${dto.servicesId}" id="servicesId" name="servicesId" required="required"/>
                        <input type="hidden" value="${dto.servicesName}" id="servicesName" name="servicesName" />
                    </td>
                </tr>

                <tr id="pay">
                    <td>结算方式<span class="span-must">*</span></td>
                    <td colspan="8" id=payTd>
                        <div
                                <c:if test="${dto.payType !=null}">
                                    <c:if test="${dto.payType == 1}">class="item"</c:if>
                                    <c:if test="${dto.payType == 2}">class="item"</c:if>
                                    <c:if test="${dto.payType == 3}">class="item active"</c:if>
                                    <c:if test="${dto.payType == 4}">class="item"</c:if>
                                </c:if>
                                <c:if test="${dto.servicesId !=null && dto.servicesId == 13}">style="display: none"</c:if>
                                onclick="choice('payType',3,'任务',true)" id="payType_3" name="payTypes" data-code="3">
                            任务
                        </div>
                        <div
                                <c:if test="${dto.payType !=null}">
                                    <c:if test="${dto.payType == 1}">class="item"</c:if>
                                    <c:if test="${dto.payType == 2}">class="item active"</c:if>
                                    <c:if test="${dto.payType == 3}">class="item"</c:if>
                                    <c:if test="${dto.payType == 4}">class="item"</c:if>
                                </c:if>
                                onclick="choice('payType',2,'基本费+减损',true)" id="payType_2" name="payTypes" data-code="2">
                            基本费+减损
                        </div>
                        <div
                                <c:if test="${dto.payType !=null}">
                                    <c:if test="${dto.payType == 1}">class="item active"</c:if>
                                    <c:if test="${dto.payType == 2}">class="item"</c:if>
                                    <c:if test="${dto.payType == 3}">class="item"</c:if>
                                    <c:if test="${dto.payType == 4}">class="item"</c:if>
                                </c:if>
                                onclick="choice('payType',1,'一口价',true)" id="payType_1" name="payTypes" data-code="1">
                            一口价
                        </div>
                        <input type="hidden" value="${dto.payType}" id="payType" name="payType" required="required"/>
                    </td>
                </tr>

                <tr id="tr_payInfo" style="display: none">
                    <td>付费价格</td>
                    <td colspan="3"><input type="number" step="0.00" placeholder="一口价/基本费" id="surveyMoney" name="surveyMoney" class="form-control"></td>
                </tr>
                <tr id="tr_payInfo2" style="display: none">
                    <td>减损描述</td>
                    <td colspan="3">
                        <textarea rows="2" id="surveryReLoossesRemark" name="surveryReLoossesRemark" class="form-control"></textarea>
                    </td>
                </tr>

                <tr>
                    <td width="100">截止时间<span class="span-must">*</span></td>
                    <td colspan="3" id = "endTimeIuput">
                        <input name="endTime" type="text" class="form-control" required="required" id="endTime"
                               value="<fmt:formatDate value="${maxDate}" pattern="yyyy-MM-dd 23:59:59"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate :'<fmt:formatDate value="${minDate}" pattern="yyyy-MM-dd HH:mm:ss" />'})"
                               onfocus="this.blur();"/>
                    </td>
                </tr>
                <tr>
                    <td>终审人员<span class="span-must">*</span></td>
                    <%--<td colspan="3">--%>
                        <%--<select class="form-control" id="oprUser" name="oprUser"></select>--%>
                    <%--</td>--%>

                    <td colspan="3" style="text-align: left">
                        <select class="singleSelect form-control" style="display: inline-flex" name="oprUser" id="oprUser">
                            <option value="">请选择</option>

                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="text-align: right;margin-right: 20px;">
                        <input type="submit" onclick="return toValid()"  class="btn btn-primary" value="确认分派" />
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </form>
</div>
</div>
<%--    <c:if test="${btnCode == '111' || btnCode == '115'}">--%>
<%--        <div class="form-group" style="text-align: center">--%>
<%--            <label>排行榜</label>--%>
<%--            <table class="table">--%>
<%--                <thead>--%>
<%--                <th>姓名</th>--%>
<%--                <th>类型</th>--%>
<%--                <th>称号</th>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:forEach items="${surveyUsers}" var="item">--%>
<%--                    <tr>--%>
<%--                        <td>${item.realName}</td>--%>
<%--                        <td>--%>
<%--                            <c:if test="${item.type ==1 || item.type == null}">--%>
<%--                                直营--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${item.type ==2}">--%>
<%--                                合伙--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${item.type ==3}">--%>
<%--                                合作--%>
<%--                            </c:if>--%>
<%--                        </td>--%>
<%--                        <td>${item.titleName}</td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--    </c:if>--%>
</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
<script type="text/javascript" src="//api.map.baidu.com/api?v=2.0&ak=QkRO8ju3UC9XVXbQITdaB4y7Vo73X89D"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=1.0&type=webgl&ak=Odbi0Om0OfcFia4cNzKfFoHIX55IhHTe"></script>

<script>

    var entrustOrgId = $("#entrustOrgId").val();
    if (entrustOrgId == 94){//委托方为众安
        $("[name=servicesIds]").eq(2).css("pointer-events","none");
    }
    $(document).ready(function(){
        $('.singleSelect').select2();

        var btnCode = "${btnCode}";
        if(btnCode == '113'){
            $("#tr_survey_user_type").hide();
        }else{
            $("#tr_survey_user_type").hide();
        }
    });

    $("#editForm").bind('submit', function(event) {
        $(this).find(":submit").attr("disabled","true");
        ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
        event.preventDefault();
    });
    function returnCallback(event,param){
        $(this).find(":submit").removeAttr("disabled");
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){

        }else{
            alert(apiRsp.msg);return;
        }
        var autoOpenInfo = $("#autoOpenInfo").val();
        if(autoOpenInfo ==1){
            parent.location.href = "${ctx}/survey/case/info?id=" + ${dto.id} + "&menuCode=assign-list"+"&autoOpenInfo=0&auto=1";
        }else{
            reloadParent();
        }

//        reloadParent();
    }

<%--    <c:if test="${btnCode == '111' || btnCode == '115'}">--%>
<%--        surveyUserId.oninput=function(){--%>
<%--            surveyUserId.setCustomValidity("");--%>
<%--        };--%>
<%--        surveyUserId.oninvalid=function(){--%>
<%--            surveyUserId.setCustomValidity("请选择分派人");--%>
<%--        };--%>
<%--    </c:if>--%>

    function toValid(){
        var taskIds = [];
        $("input:checkbox[name='chkTaskIds']:checked").each(function() { // 遍历name=chkTaskIds的多选框
            taskIds.push($(this).val());
        });
        $("#taskIds").val(taskIds);

        var assignUserId = $("#assignUserId").val();
        if(assignUserId != ""){
            if(!$("#taskIds").val()){
                alert("请选择任务类型");
                return false;
            }
        }

        //业务类型
        $("div[name='servicesIds']").each(function(i, obj){
            if($(obj).attr("class") == 'item active'){
                var all = $(obj).attr('data-code');
                var result = all.split(",");
                $("#servicesIdName").val(result[1]);
                $("#servicesId").val(result[0]);
            }
        });

        //结算方式
        var payType ="";
        $("div[name='payTypes']").each(function(i, obj){
            if($(obj).attr("class") == 'item active'){
                payType = $(obj).attr('data-code');
            }
        });
        $("#payType").val(payType);

        var btnCode = $("#btnCode").val();
        if(btnCode == 112 || btnCode==117) {
            if (!$("#payType").val()) {
                alert("请选择结算方式");
                return false;
            }
        }

        var json = [];
        if ($("#addressJSON")){
            $("#address").find("input").each(function(index,item){
                var value = $(item).val();
                if(json.indexOf(value) < 0 ){
                    json.push(value);
                }
            })
            $("#addressJSON").val(json);
            if (addressDetail.length > 0){
                $("#addressDetailJSON").val(JSON.stringify(addressDetail));
            }
        }
        return true;
    }




    function changeSurveyUser(obj,returnType){
        var result = obj.value.split(",");
        if (obj.value == ""){
            $("#taskRemark").val($("#taskRemarkHis").val());
        }
        $("#assignUserId").val(result[0]);
        var userType = result[1] == '' ? 1 : result[1];
        var payType = $("#payType").val();
        if(userType == 2 || userType == 3){//合伙 和  合作
            $("#tr_payType").show();
            if(payType == 1 || payType == 2){
                $("#tr_payInfo").show();
                $("#surveyMoney").attr("required","required");
                $("#surveryReLoossesRemark").attr("required",null);
            }else{
                $("#surveyMoney").attr("required",null);
            }
            if(payType == 2 || payType == 4){
                $("#tr_payInfo2").show();
                $("#surveryReLoossesRemark").attr("required","required");
            }else{
                $("#surveryReLoossesRemark").attr("required",null);
            }
        }else{
            $("#tr_payType").hide();
            $("#tr_payInfo").hide();
            $("#tr_payInfo2").hide();
            $("#surveyMoney").attr("required",null);
            $("#surveryReLoossesRemark").attr("required",null);
        }

        //机构分派调查员时，查询该人员在此案件中的任务类型
        var btnCode = result[2];
        if(btnCode == 113 || btnCode == 112 || btnCode == 117 || btnCode == 116){
            //调查员对应的任务类型
            var userId = result[0]=='null'?'':result[0];
            findTaskType(userId,returnType);
        }
    }


    function changeSurveyOrg(btnCode,id,returnType){

        $("#surveyMoney").val("");

        var surveyOrgId = $("#surveyOrgId").val();
        if(btnCode == 111){
            if(surveyOrgId){
                $("#tr_survey_user_type").hide();
            }else{
                $("#tr_survey_user_type").hide();
            }
        }
        if(btnCode == 116 || btnCode == 113){
            surveyOrgId = $("#curSurveyOrgId").val();
        }
        if(!surveyOrgId){
            $("#surveyUserId option").remove();
            $("#surveyUserId").append("<option value=''>请选择</option>");
            $('#surveyUserId').trigger("change");
            return;
        }

        $("#taskTd label").remove();
        $("#tr_task").show()

        $.ajax({
            url:"${ctx}/surveyInvestigator/selectInvestigatorByOrgId",
            data:{"orgId":surveyOrgId,"surveyInfoId":id}
        }).done(
                function(e) {
                    $("#deepCasesPrice").val(e.results.deepCasesPrice);
                    var isSurveyPrimary = e.results.isSurveyPrimary;
                    var list = e.results.list;
                    $("#span_survey_primary").html(isSurveyPrimary ? "主调查员" : "辅助调查员");
                    $("#surveyUserId option").remove();
                    $("#surveyUserId").append("<option value =''>请选择</option>");
                    //--------
                    var _html = ''
                    var a = /[\u4e00-\u9fa5]/g;
                    var s = /[()（）^%&',;=?$\x22]/g;
                    var strArr = [],
                            spcArr = [],
                            engArr = [],
                            newOptions=[]
                    for(var i = 0; i < list.length; i++){
                        var item = list[i].realName.trim()
                        var strLen = item.match(a) ? item.match(a).length : 0;
                        var spcLen = item.match(s) ? item.match(s).length : 0;
                        var engLen = item.length - strLen - spcLen
                        strArr.push(strLen)
                        spcArr.push(spcLen)
                        engArr.push(engLen)
                        newOptions.push(item)
                    }
                    options = newOptions
                    var maxStr = strArr.sort(function (a, b) {
                        return b - a;
                    })[0];
                    var maxSpc = spcArr.sort(function (a, b) {
                        return b - a;
                    })[0];
                    var maxEng = engArr.sort(function (a, b) {
                        return b - a;
                    })[0];

                    for(var i = 0; i < list.length; i++){
                        var val = list[i];
                        //
                        var option = val.realName.trim()
                        var strLen = option.match(a) ? option.match(a).length : 0
                        var spcLen = option.match(s) ? option.match(s).length : 0
                        var engLen = option.length - strLen - spcLen
                        if (maxSpc - spcLen > 0) {
                            for (var n = 0; n < maxSpc - spcLen; n++) {
                                option = option + '&emsp;'
                            }
                        }
                        if (maxStr - strLen > 0) {
                            for (var m = 0; m < maxStr - strLen; m++) {
                                option = option + '&emsp;'
                            }
                        }
                        if (maxEng - engLen > 0) {
                            for (var n = 0; n < maxEng - engLen; n++) {
                                option = option + '&ensp;'
                            }
                        }
                        //
                        if(list.length == 1){
                            $("#surveyUserId").append("<option selected value='" + val.userId + ","+val.type+",${btnCode}'>" + option + "&emsp;&emsp;&emsp;&emsp;&emsp;(调查中案件数："+ val.dcyCaseNum +")"+  "</option>");
                        }else{
                            $("#surveyUserId").append("<option value='" + val.userId + ","+val.type+",${btnCode}'>" + option + "&emsp;&emsp;&emsp;&emsp;&emsp;(调查中案件数："+ val.dcyCaseNum +")"+  "</option>");
                        }

                    }
                    $('#surveyUserId').trigger("change");
                    //
                    var nextFinalUser = e.results.nextFinalUser
                    var oprUsers = e.results.oprUsers;
                    $("#oprUser option").remove();
                    if(nextFinalUser != null){
                        $("#oprUser").append("<option selected value='"+nextFinalUser.userId+"'>"+nextFinalUser.userName+"</option>");
                    }
                    for(var i = 0; i < oprUsers.length; i++){
                        var val = oprUsers[i];
                        $("#oprUser").append("<option value='"+val.userId+"'>"+val.userName+"</option>");
                    }

                    var surveyAssignOrg = e.results.surveyAssignOrg;
                    if(surveyAssignOrg !=null && surveyAssignOrg.payType != null){
                        //全部不可选择，赋值数据
                        $("[name=servicesIds]").attr("class","item");
                        $("[name=servicesIds]").css("pointer-events","none");
                        var servicesId = surveyAssignOrg.servicesId;
                        $("#services_"+servicesId).attr("class","item active");
                        $("#servicesId").val(servicesId);
                        $("#servicesName").val(surveyAssignOrg.servicesName);

                        if(servicesId == 13){//深度案件
                            $("#payType_1").show();
                            $("#payType_2").show();
                            $("#payType_3").hide();
                        }else{
                            //单点调查与契约调查  结算方式只可以选择任务 隐藏结算方式
                            $("#payType_2").hide();
                            $("#payType_1").show();
                            $("#payType_3").show();
                        }

                        $("[name=payTypes]").attr("class","item");
                        $("[name=payTypes]").css("pointer-events","none");
                        $("#payType_"+surveyAssignOrg.payType).attr("class","item active");
                        $("#payType").val(surveyAssignOrg.payType);

                        //赋值时间 (已经分派的机构：最大时间为机构截止时间，最小时间为当前)
//                        var orgEndTime = surveyAssignOrg.orgEndTime;
//                        setMaxDate(formatDate(orgEndTime));
                    }else{
                        $("[name=servicesIds]").css("pointer-events","auto");
                        $("[name=payTypes]").css("pointer-events","auto");

                        //重置原案件“任务类型”、“结算方式”
                        var oldServicesId ='${dto.servicesId}'
                        var oldServicesName ='${dto.servicesName}'
                        $("[name=servicesIds]").attr("class","item");
                        $("#services_"+oldServicesId).attr("class","item active");
                        $("#servicesId").val(oldServicesId);
                        $("#servicesName").val(oldServicesName);

                        $("[name=payTypes]").attr("class","item");
                        if(oldServicesId == 13){//深度案件
                            $("#payType_1").show();
                            $("#payType_2").show();
                            $("#payType_3").hide();
                            $("#payType_2").attr("class","item active");
                            $("#payType").val(2);
                        }else{
                            //  单点调查与契约调查  结算方式只可以选择任务 隐藏结算方式
                            $("#payType_2").hide();
                            $("#payType_1").show();
                            $("#payType_3").show();
                            $("#payType_3").attr("class","item active");
                            $("#payType").val(3);
                        }


                        //赋值时间
//                        var maxDateD = $("#maxDateTime").val();
//                        setMaxDate(formatDate(maxDateD));
                    }
                    findMaxDate();
                    //机构对应的任务类型
                    findTaskType(null,returnType);
                }
        );
    }

    function findTaskType(userId, returnType){
        var surveyOrgId = $("#surveyOrgId").val();
        if(btnCode == 116 || btnCode == 113){
            surveyOrgId = $("#curSurveyOrgId").val();
        }
        $.ajax({
            url:"${ctx}/survey/case/selectTaskByUserId",
            data:{"userId":userId,"surveyInfoId":${dto.id},"returnType":returnType,"surveyOrgId":surveyOrgId,"btnCode":btnCode}
        }).done(
            function(e) {
                var list = e.results.surveyTaskTypeDtoList;
                var isHaveAssign = e.results.isHaveAssign;
                var taskRemark = e.results.taskRemark;
                $("#taskTd label").remove();
                for(var i = 0; i < list.length; i++){
                    if(returnType == "surveyOrg"){
                        var val = list[i].surveyTaskType;
                    }else if(returnType == "surveyUser"){
                        var taskType = e.results.taskType;
                        //分人员时，会有两种情况：1、机构已存在任务类型；2、机构不存在任务类型
                        if(taskType == "orgCaseType"){
                            var val = list[i].surveyAssignOrgType;
                        }else if(taskType == "caseType"){
                            var val = list[i].surveyTaskType;
                        }
                    }

                    var selectType = list[i].selectType;//1、未分配；2、已分配，没有做方向；3已分配，并做了方向
                    //分派调查员时：1、初次分派全部默认选中；2、同一调查员二次分派，根据selectType做区分
                    if(isHaveAssign){
                        if(selectType==1){
                            $("#taskTd").append("<label><input type='checkbox' name='chkTaskIds' value='" + val.taskId + "'>" + val.taskName +"</label> " );
                        }else if(selectType==2){
                            $("#taskTd").append("<label><input type='checkbox' checked name='chkTaskIds' value='" + val.taskId + "'>" + val.taskName +"</label> " );
                        }else if(selectType==3){
                            $("#taskTd").append("<label style='color:#808080' ><input type='checkbox' checked disabled='disabled' name='chkTaskIds' value='" + val.taskId + "'>" + val.taskName +"</label> " );
                        }
                        if(taskRemark !=null){
                            $("#taskRemark").val(taskRemark);
                        }
                    }else{
                        $("#taskTd").append("<label><input type='checkbox' checked name='chkTaskIds' value='" + val.taskId + "'>" + val.taskName +"</label> " );
                    }
                }
            }
        );
        $.post("${ctx}/survey/case/getDeadline",{btnCode:$("#btnCode").val(),assignType:'org',id:$("input[name=id]").val(),serviceId:$("#servicesId").val(),surveyCaseId:$("input[name=surveyCaseId]").val()},function (res,e) {
            if (e.data.data != undefined){
                setMaxDate(e.data.data)
            }
        });

    }

    //委托时间、委托机构的公司属性：获取截止时间（互助的截取时间，单点、契约根据委托时间往后6个自然日；深度根据委托时间往后10个自然日）
    var orgAttr = $("#orgAttr").val();
    var entrustTime = $("#entrustTime").val();

    function choice(code,id,name,bool) {
        if(code=="services"){
            choiceServices(code,id,name,bool);//业务类型选择
        }else if(code=="payType"){
            choicePayType(code,id,name,bool);//结算方式
        }
    }
    //业务类型选择
    function choiceServices(code,id,name,bool) {
        var servicesId = '${dto.servicesId}';
        if((servicesId == 11 || servicesId == 12) && id ==13){
            return;
        }
        $("#servicesName").val(name);
        $("#servicesId").val(id);
        var payType = $("#payType");
        //结算方式 选中清空
        $("[name=payTypes]").attr("class","item");
        $("#payType").val(null)

        //具体结算填写，清空
        $('#entrustMoney').val("");
        $('#tr_entrustMoney').hide();
        $('#entrustReLossesRemark').val("");
        $('#tr_entrustReLosses').hide();

        if(id == 13){//深度案件
            $("#pay").show();
            $("#payType_2").attr("class","item active");
            $("#payType").val(2);
            payTypeChange();
            $("#payType_1").show();
            $("#payType_2").show();
            $("#payType_3").hide();
        }else{
            //单点调查与契约调查  结算方式只可以选择任务 隐藏结算方式
            $("#pay").show();
            $("#payType_3").attr("class","item active");
            $("#payType").val(3);
            payTypeChange();
            $("#payType_2").hide();
            $("#payType_1").show();
            $("#payType_3").show();
        }

        //互助的截取时间，单点、契约根据委托时间往后6个自然日；深度根据委托时间往后10个自然日
        // if(orgAttr == 2){
        //     var hzEndDate = $("#hzEndDate").val();
        //     var hzSixDate = $("#hzSixDate").val();
        //     var hzTenDate = $("#hzTenDate").val();
        //
        //     var newMaxDate =''; //用于赋值最大值
        //     if(id == 13){//深度案件
        //         if(hzEndDate > hzTenDate){
        //             newMaxDate = formatDate(hzTenDate);
        //         }else{
        //             newMaxDate = formatDate(hzEndDate);
        //         }
        //     }else{
        //         if(hzEndDate > hzSixDate){
        //             newMaxDate = formatDate(hzSixDate);
        //         }else{
        //             newMaxDate = formatDate(hzEndDate);
        //         }
        //     }
        //
        //     //用于赋值最大值
        //     setMaxDate(newMaxDate);
        // }

        if(!bool){
            $("#services_"+id).attr("class","item");
            $("#services_"+id).attr("onclick","choice('"+code+"',"+id+",'"+name+"',false)");
        }else{
            $("[name=servicesIds]").attr("class","item");
            $("#services_"+id).attr("class","item active");
            $("#services_"+id).attr("onclick","choice('"+code+"',"+id+",'"+name+"',true)");
        }

        console.log(11)
        $.post("${ctx}/survey/case/getDeadline",{btnCode:$("#btnCode").val(),assignType:'org',id:$("input[name=id]").val(),serviceId:id,surveyCaseId:$("input[name=surveyCaseId]").val()},function (res,e) {
            setMaxDate(e.data.data)
        })
    }
    //结算方式
    function choicePayType(code,id,name,bool) {
        $("#payType").val(id);

        if(!bool){
            $("#payType_"+id).attr("class","item");
            $("#payType_"+id).attr("onclick","choice('"+code+"',"+id+",'"+name+"',false)");
        }else{
            $("[name=payTypes]").attr("class","item");
            $("#payType_"+id).attr("class","item active");
            $("#payType_"+id).attr("onclick","choice('"+code+"',"+id+",'"+name+"',true)");
        }
        payTypeChange();
    }
    payTypeChange();

    function payTypeChange(){
        var servicesId = $("#servicesId").val();
        var value = $("#payType").val();
        if(value == 1){
            $("#tr_payInfo").show();
            $("#tr_payInfo2").hide();
            $("#surveyMoney").attr("required","required");
            $("#surveryReLoossesRemark").attr("required",null);
        }
//        else if(value == 2){
//            $("#tr_payInfo").show();
//            $("#tr_payInfo2").show();
//            $("#surveryReLoossesRemark").attr("required","required");
//            $("#surveyMoney").attr("required","required");
//        }
        else if(value == 3 || value == 2){
            $("#tr_payInfo").hide();
            $("#tr_payInfo2").hide();
            $("#surveyMoney").attr("required",null);
            $("#surveryReLoossesRemark").attr("required",null);
        }

        if(servicesId == 13){//深度案件
            var deepCasesPrice=$("#deepCasesPrice").val();
            var payType=$("#payType").val();
            if(deepCasesPrice!=null && deepCasesPrice != '' && deepCasesPrice != undefined){
                $("#surveyMoney").val(deepCasesPrice);
            }
            $("#payType_1").show();
            $("#payType_2").show();
            $("#payType_3").hide();
        }else{
            //单点调查与契约调查  结算方式只可以选择任务 隐藏结算方式
            $("#payType_2").hide();
            $("#payType_1").show();
            $("#payType_3").show();
        }
    }

    var btnCode = $("#btnCode").val();
    surveyUsersJson();
    function surveyUsersJson(){
        if(!(btnCode == '113' || btnCode == '116')) {
            return;
        }
        var id = $("#id").val();
        if(btnCode == '116'){
            changeSurveyOrg(btnCode,id,'surveyUser');
        }else if(btnCode == '113'){
            changeSurveyOrg(btnCode,id,'surveyUser');
        }
    }

    function formatDate(time){
        var newDate = new Date(parseInt(time))
        var _year = newDate.getFullYear(),
                _month = newDate.getMonth() + 1,
                _date = newDate.getDate()
        return _year + '-' + _month + '-'+ _date + ' 23:59:59'
    }

    function randomDate(newMaxDate){
        WdatePicker({
            dateFmt:'yyyy-MM-dd HH:mm:ss',
            maxDate:newMaxDate})
    }
    function setMaxDate(newMaxDate){
        var _html = '<input name="endTime" type="text" class="form-control" required="required" id="endTime" value="'+newMaxDate+'" onclick="randomDate(\'' + newMaxDate + '\')" onfocus="this.blur();"/> '
        $("#endTimeIuput").empty().append(_html)
    }

    function findMaxDate(userId, returnType){
        var surveyOrgId = $("#surveyOrgId").val();
        if(btnCode == 116 || btnCode == 113){
            surveyOrgId = $("#curSurveyOrgId").val();
        }
        $.ajax({
            url:"${ctx}/survey/case/getDeadline",
            data:{"btnCode":btnCode, assignType:'org',id:$("input[name=id]").val(),serviceId:$("#servicesId").val(),surveyCaseId:$("input[name=surveyCaseId]").val(),"surveyOrgId":surveyOrgId}
        }).done(
            function(e) {
                if (e.data.data != undefined){
                    setMaxDate(e.data.data)
                }
            }
        );
    }
</script>

<script type="text/javascript">
    function addAddress(){
        var inputs = $("#address").find("input");
        var length = inputs.length;
        $("#address").append('<div id="div_address_add'+(length + 1)+'" style="height: 35px;"><input style="margin-top: 3px;" type="text" id="address'+(length + 1)+'" ' +
            'class="form-control add" name="address'+(length + 1)+'" placeholder="可输入指定调查地点，并在右侧地图核对位置" /><button style="position: relative;left: 412px;top:-34px;" data-key="'+(length + 1)+'" ' +
            ' type="button" class="btn btn-primary address-btn"  value="">删除此地点</button></div>')
        $("#address").find("input.add").each(function(index,item){
            if (index == length - 1){
                initMap($(item).attr("name"),'l-map');
            }
        })
    }

    $('#address').on('click', '.address-btn', function () {
        var key = $(this).attr("data-key");
        $("#div_address_add" + key).remove();
    })

    initMap('address1','l-map');

    var addressDetail = [];

    function initMap(name,mapD){
        // 百度地图API功能
        function G(id) {
            return document.getElementById(id);
        }

        var map = new BMap.Map(mapD);
        map.centerAndZoom("北京",12);                   // 初始化地图,设置城市和地图级别。

        var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
            {"input" : name
                ,"location" : map
            });

        ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
            var str = "";
            var _value = e.fromitem.value;
            var value = "";
            if (e.fromitem.index > -1) {
                // value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
                value =_value.street +  _value.business;
            }
            str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

            value = "";
            if (e.toitem.index > -1) {
                _value = e.toitem.value;
                // value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
                value = _value.street +  _value.business;
            }
            str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
            G("searchResultPanel").innerHTML = str;
        });

        var myValue;
        ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
            var _value = e.item.value;
            console.log('--------------------------------',e);
            myValue =  _value.business;
            G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
            if (_value.city && _value.district) {
                console.log(_value);
                //根据名称匹配省市区
                $.ajax({
                    url: '${ctx}/survey/case/operate',
                    data: {
                        name1 : _value.city,
                        name2 : _value.district,
                        btnCode : "match-address"
                    },
                    success: function (e,res) {
                        var _data = res.data.results;
                        if (_data){
                            var areaType = _data.areaType;
                            var areaName3 = _data.areaName3,areaId3 = _data.areaId3;//省名称
                            var areaName2 = _data.areaName2,areaId2 = _data.areaId2;//市名称
                            var areaName1 = _data.areaName1,areaId = _data.areaId;//区名称
                            var cityType = _data.cityType,cityTypeName = _data.cityTypeName;//区域级别（市区、郊区）

                            var areaName = "",areaType = "",regionType = "";
                            if (cityType == 3){
                                areaName = areaName2 + " " + areaName3
                            }else{
                                areaName = areaName1+" "+areaName2+" "+areaName3
                            }
                            if (cityType == 5 || cityType == 6) {
                                areaType = 1;
                                regionType = cityType;
                            }else{
                                areaType = cityType;
                                regionType = 0
                            }
                            addressDetail.push({
                                province : areaName3,
                                provinceId : areaId3,
                                areaName : areaName,
                                city : areaName2,
                                cityId : areaId2,
                                district : areaName1,
                                districtId : areaId,
                                areaType : areaType,
                                regionType : regionType,
                                address : myValue
                            })
                        }
                    }
                })
            }
            ac.setInputValue(myValue)
            setPlace();
        });

        function setPlace(){
            map.clearOverlays();    //清除地图上所有覆盖物
            function myFun(){
                var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
                map.centerAndZoom(pp, 18);
                map.addOverlay(new BMap.Marker(pp));    //添加标注
            }
            var local = new BMap.LocalSearch(map, { //智能搜索
                onSearchComplete: myFun
            });
            local.search(myValue);
        }
    }



</script>
</body>
</html>
