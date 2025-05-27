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
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">
    <link rel="stylesheet" href="${ctx}/css/viewer.min.css?v=1">

    <script>

    </script>
    <style>
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

        .label-btns {
            width: 100%;
            display: flex;
            flex-wrap: wrap;
        }

        .label-btns .label-btn {
            padding: 0 12px;
            margin: 5px 10px;
            width: 80px;
            height: 32px;
            line-height: 32px;
            color: #000;
            border: 1px solid #bbb;
            background-color: #fff;
            font-size: 14px;
            text-align: center;
            cursor: pointer;
        }

        .label-btns .label-btn.active {
            color: #fff;
            background-color: #3BA9FF;
            border: 1px solid #3BA9FF;
        }

        .files {
            margin-top: 20px;
            width: 100%;
            display: flex;
            flex-wrap: wrap;
            /*max-height: 330px;*/
            /*overflow: auto;*/
        }

        .files .file {
            width: 100px;
            height: 100px;
            border: 1px solid #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;
            position: relative;
        }

        .files .file .image {
            width: 100%;
            height: 100%;
            display: block;
            margin: 0 auto;

        }

        .tr-email{
            display: none;
        }

        .email-files{
            width: 560px;
        }
        .email-files a{
            min-width: 50%;
            padding: 2px 0;
            display: inline-block;
            color: #3ba9ff!important;
        }
        .email-files-loading{
            display: none;
        }
    </style>

</head>
<body>

<div class="container">
    <form id="editForm" role="form" action="${ctx}/survey/case/operate" method="post">
        <input type="hidden" name="id" id="id" value="${id}" />
        <input type="hidden" name="btnCode" id="btnCode" value="${btnCode}" />
        <div class="form-group">
                <c:if test="${btnCode == 'extension'}">
                <div style="color: red; padding:20px 0;">延期审核通过，申请延期时间超过原案件截止时间，是否自动向保司发送邮件？</div>
                </c:if>
            <table class="table">
                <tbody>
                <c:if test="${btnCode == 'sendReport'}">
                    <tr>
                        <th>报告制作人</th>
                        <td colspan="8">
                            <select class="singleSelect form-control" required="required" name="sendReportUserId" id="sendReportUserId">
                                <option value="">请选择</option>
                                <c:forEach items="${users}" var="item">
                                    <option value="${item.userId}">${item.userName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == 'dispatch'}">
                    <tr>
                        <th>已分配任务类型</th>
                        <td colspan="8">
                            <c:forEach items="${surveyTaskTypes}" var="item">
                                ${item.taskName}&nbsp;&nbsp;&nbsp;&nbsp;
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <th>可分配任务类型</th>
                        <td colspan="8">
                            <c:forEach items="${surveyTaskInfos}" var="item">
                                <input type="checkbox"
                                       name="chkTaskIds" value="${item.id}">${item.name}
                                &nbsp;&nbsp;&nbsp;&nbsp;
                            </c:forEach>
                            <input type="hidden" name="taskIds" id="taskIds" />
                            <c:if test="${surveyTaskInfos.size() == 0}">
                                <span style="color: red;">无</span>
                            </c:if>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == '1300' || btnCode == '1400'}">
                    <tr>
                        <th>审核时间</th>
                        <td>
                            <input id="oprTime" name="oprTime" type="text" class="form-control" required="required" value="<fmt:formatDate value="${currentDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate :'<fmt:formatDate value="${minDate}" pattern="yyyy-MM-dd HH:mm:ss" />',maxDate:'<fmt:formatDate value="${maxDate}" pattern="yyyy-MM-dd HH:mm:ss" />'})" readonly>
                        </td>
                    </tr>
                    <tr>
                        <th>审核描述</th>
                        <td>
                            <textarea rows="4" name="remark" id="remark" class="form-control"></textarea>
                        </td>
                    </tr>
                    <c:if test="${btnCode == '1300'}">
                        <tr>
                            <th>同时发送至保司邮箱</th>
                            <td><input type="checkbox" id="emailChk" <c:if test="${emailInfo.send}">checked</c:if> /></td>
                        </tr>
                        <tr class="tr-email">
                            <th>保司收件邮箱地址</th>
                            <td><input type="text" class="form-control" name="toEmailAddress" id="toEmailAddress" value="${emailInfo.toEmailAddress}" /></td>
                        </tr>
                        <tr class="tr-email">
                            <th>抄送地址</th>
                            <td><input type="text" class="form-control" name="makeEmail" id="makeEmail" value="${emailInfo.makeEmail}" /></td>
                        </tr>
                        <tr class="tr-email">
                            <th>乐凡发件邮箱地址</th>
                            <td><input type="text" class="form-control" style="cursor:pointer;" readonly name="fromEmailAddress" id="fromEmailAddress" value="${emailInfo.emailAddress}" />
                                <input type="hidden" id="fromUserName" value="${emailInfo.emailUserName}" />
                                <input type="hidden" id="fromPwd" value="${emailInfo.emailPassword}" />
                            </td>
                        </tr>
                        <tr class="tr-email">
                            <th>乐凡申请结算价格</th>
                            <td>
                                <input type="text" id="price" value="${price}"  style="cursor:pointer;"  class="form-control" readonly />
                            </td>
                        </tr>
                        <tr class="tr-email">
                            <th>邮箱正文</th>
                            <td><textarea class="form-control" name="emailContent" id="emailContent"> </textarea> </td>
                        </tr>
                        <tr class="tr-email">
                            <th>附件</th>
                            <td>
                                <input type="hidden" id="maxSize" name="maxSize" value="${emailInfo.maxSize}" />
                                <input type="hidden" id="filesJSON" name="filesJSON" />
                                <input type="hidden" id="surveyCaseNo" name="surveyCaseNo" value="${surveyCaseNo}" />
                                <div class="email-files-loading"><img src="${ctx}/css/images/loading.gif" width="25" height="25" /></div>
                                <div class="email-files"></div>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${btnCode == '1400'}">
                        <c:if test="${surveyInvestigatorCaseDtoList.size()>0}">
                            <tr>
                                <th>阳性奖励</th>
                                <td>
                                    <c:forEach items="${surveyInvestigatorCaseDtoList}" var="item">
                                        <div style="display: flex;flex-wrap: wrap">
                                            <div style="width: 60%;color: #FF0000;font-size: 14px;font-weight: bold">${item.surveyOrgName}-${item.surveyUserName}</div>
                                            <div  id="divSunMoney1${item.id}" style="width: 40%;text-align: right" ><label id="modifySunMoney${item.id}">${item.sunMoney}</label>元<a onclick="updMoney(${item.id},1)"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a>&nbsp;</div>
                                            <div id="divSunMoney2${item.id}" style="display: none;">
                                                <input type="number" class="input-2" style="display: inline-flex;" step="0.01" value="${item.sunMoney}" id="updSunMoney${item.id}" />
                                                <a href="javascript:void(0);" onclick="updSunMoney('${item.id}','updSunMoney',true)">确定</a>&nbsp;&nbsp;
                                            </div>
                                        </div>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:if>
                    </c:if>
                </c:if>
                <%--查看时效--%>
                <c:if test="${btnCode == 'workflow'}">
                    <thead>
                    <tr>
                        <th width="250">步骤名称</th>
                        <th width="150">处理人名称</th>
                        <th width="250">步骤开始时间</th>
                        <th width="250">步骤结束时间</th>
                        <th width="150">步骤时效</th>
                    </tr>
                    </thead>
                    <tbody class="class-list">
                        <c:forEach items="${caseWorkflowList}" var="item">
                            <tr>
                                <td>${item.stepName}</td>
                                <td>${item.dealUserName}</td>
                                <td><fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                <td><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                <%--<td>${item.hours}</td>--%>
                                <td>${item.showTimeStr}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </c:if>
                <c:if test="${btnCode == 'belongUser'}">
                    <tr>
                        <th>归属人</th>
                        <td colspan="8">
                            <select class="singleSelect form-control" required="required" name="belongUserId" id="belongUserId">
                                <option value="">请选择</option>
                                <c:forEach items="${users}" var="item">
                                    <option value="${item.userId}">${item.userName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == 'services'}">
                    <tr>
                        <th>业务类型*</th>
                        <td colspan="8">

                            <c:if test="${dto.subServiceId ==null}">
                                <c:forEach items="${services}" var="item">
                                    <div
                                            <c:if test="${dto.servicesId !=null}">
                                                <c:if test="${dto.servicesId == item.id}">class="item active" </c:if>
                                                <c:if test="${!(dto.servicesId == item.id)}">class="item" </c:if>
                                            </c:if>
                                            <c:if test="${dto.servicesId == null}">
                                                <c:if test="${item.sort == 1}">class="item active" </c:if>
                                                <c:if test="${item.sort != 1}">class="item" </c:if>
                                            </c:if>
                                            onclick="choice('services',${item.id},'${item.name}',true)" id="services_${item.id}" name="servicesIds" data-code="${item.id},${item.name}">
                                            ${item.name}
                                    </div>
                                </c:forEach>
                            </c:if>

                            <c:if test="${dto.subServiceId !=null}">
                                <div class="item ${dto.subServiceId ==2 ?'active':''}" onclick="choiceSubServices('subServices',12,'单点调查',true,'2')" id="services_12_2" name="servicesIds" data-code="12,单点调查">
                                    核对调阅类
                                </div>
                                <div class="item ${dto.subServiceId ==3 ?'active':''}" onclick="choiceSubServices('subServices',13,'深度案件',true,'3')" id="services_13_3" name="servicesIds" data-code="13,深度案件">
                                    一般检索类
                                </div>
                                <div class="item ${dto.subServiceId ==4 ?'active':''}" onclick="choiceSubServices('subServices',13,'深度案件',true,'4')" id="services_13_4" name="servicesIds" data-code="13,深度案件">
                                    特殊检索类
                                </div>
                                <div class="item ${dto.subServiceId ==5 ?'active':''}" onclick="choiceSubServices('subServices',13,'深度案件',true,'5')" id="services_13_5" name="servicesIds" data-code="13,深度案件">
                                    疑难侦察类
                                </div>
                                <div class="item ${dto.subServiceId ==6 ?'active':''}" onclick="choiceSubServices('subServices',13,'深度案件',true,'6')" id="services_13_6" name="servicesIds" data-code="13,深度案件">
                                    攻坚侦察类
                                </div>
                            </c:if>

                            <input type="hidden" value="${dto.servicesId}" id="servicesId" name="servicesId" required="required"/>
                            <input type="hidden" value="${dto.servicesName}" id="servicesName" name="servicesName" />
                            <input type="hidden" value="${dto.subServiceId}" id="subServiceId" name="subServiceId"/>
                        </td>
                    </tr>
                    <tr id="pay" <c:if test="${dto.servicesId != 13}">style="display: none"</c:if>>
                        <th>结算方式*</th>
                        <td colspan="8">
                            <div <c:if test="${dto.payType !=null}">
                                <c:if test="${dto.payType == 1}">class="item"</c:if>
                                <c:if test="${dto.payType == 2}">class="item"</c:if>
                                <c:if test="${dto.payType == 3}">class="item active"</c:if>
                                <c:if test="${dto.payType == 4}">class="item"</c:if>
                            </c:if>
                                    <c:if test="${dto.servicesId !=null && dto.servicesId == 13}">style="display: none"</c:if>
                                    <c:if test="${dto.payType ==null}">class="item active"</c:if>
                                    onclick="choice('payType',3,'任务',true)" id="payType_3" name="payTypes" data-code="3">
                                任务
                            </div>
                            <div <c:if test="${dto.payType !=null}">
                                <c:if test="${dto.payType == 1}">class="item active"</c:if>
                                <c:if test="${dto.payType == 2}">class="item"</c:if>
                                <c:if test="${dto.payType == 3}">class="item"</c:if>
                                <c:if test="${dto.payType == 4}">class="item"</c:if>
                            </c:if>
                                    <c:if test="${dto.payType ==null}">class="item"</c:if>
                                    onclick="choice('payType',1,'一口价',true)" id="payType_1" name="payTypes" data-code="1">
                                一口价
                            </div>
                            <div <c:if test="${dto.payType !=null}">
                                <c:if test="${dto.payType == 1}">class="item"</c:if>
                                <c:if test="${dto.payType == 2}">class="item active"</c:if>
                                <c:if test="${dto.payType == 3}">class="item"</c:if>
                                <c:if test="${dto.payType == 4}">class="item"</c:if>
                            </c:if>
                                    <c:if test="${dto.payType ==null}">class="item"</c:if>
                                    onclick="choice('payType',2,'基本费+减损',true)" id="payType_2" name="payTypes" data-code="2">
                                基本费+减损
                            </div>
                            <div <c:if test="${dto.payType !=null}">
                                <c:if test="${dto.payType == 1}">class="item"</c:if>
                                <c:if test="${dto.payType == 2}">class="item"</c:if>
                                <c:if test="${dto.payType == 3}">class="item"</c:if>
                                <c:if test="${dto.payType == 4}">class="item active"</c:if>
                            </c:if>
                                    <c:if test="${dto.servicesId !=null && dto.servicesId == 13}">style="display: none"</c:if>
                                    <c:if test="${dto.payType ==null}">class="item"</c:if>
                                    onclick="choice('payType',4,'任务+减损',true)" id="payType_4" name="payTypes" data-code="4">
                                任务+减损
                            </div>
                            <input type="hidden" value="${dto.payType}" id="payType" name="payType" required="required"/>
                        </td>
                    </tr>
                    <tr  id="tr_entrustMoney" style="display: none">
                        <th>付费价格*</th>
                        <td colspan="8">
                            <input type="number" step="0.01" id="entrustMoney" name="entrustMoney" value="${dto.entrustMoney}"  class="form-control" style="width: 95% ; display: inline"> <span style="font-size: 14px;">&nbsp;元</span>
                        </td>
                    </tr>
                    <tr id="tr_entrustReLosses" style="display: none">
                        <th class="active">减损描述</th>
                        <td colspan="8">
                            <input type="text" id="entrustReLossesRemark" name="entrustReLossesRemark" value="${dto.entrustReLossesRemark}"  class="form-control">
                        </td>
                    </tr>
                </c:if>

                <c:if test="${btnCode == 'entrustUpdate'}">
                    <input type="hidden" id="oldEntrustOrgId" value="${dto.surveyRiskCase.entrustOrgId}" /> <%--委托机构--%>
                    <input type="hidden" id="oldEntrustDepartmentId" value="${dto.surveyRiskCase.departmentId}" />  <%--委托部门--%>
                    <input type="hidden" id="oldEntrustUserId" value="${dto.surveyRiskCase.entrustUserId}" />  <%--委托人--%>
                    <input type="hidden" id="updateType" name="updateType" value="${updateType}" />
                    <c:if test="${updateType == 'org'}">
                        <tr>
                            <th width="20%" class="active">委托人机构</th>
                            <td width="80%">
                                <select name="entrustOrg" id="entrustOrg" onchange="changeEntrustOrg(this)" class="singleSelect form-control">
                                    <c:forEach items="${consignors}" var="item">
                                        <option value="${item.id}" <c:if test="${item.id == dto.surveyRiskCase.entrustOrgId }">selected="selected" </c:if>>${item.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th width="20%" class="active">委托人部门</th>
                            <td width="80%">
                                <select name="entrustDepartment" id="entrustDepartment" class="form-control" onchange="changeEntrustDepartment()" required="required">
                                    <option value="">请选择部门</option>
                                </select>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <th width="20%" class="active">委托人</th>
                        <td width="80%">
                            <select name="entrustUser" id="entrustUser" class="form-control" required="required" >
                                <option value="" >请选择委托人</option>
                            </select>
                        </td>
                    </tr>
                </c:if>

                <c:if test="${btnCode == 'guide' || btnCode == 'guided'}">
                    <input type="hidden" name="guideId" value="${guideDto.id}" />
                    <input type="hidden" name="guideType" id="guideType"/>
                    <tr>
                        <th>确诊疾病</th>
                        <td colspan="8">
                            <textarea rows="4" name="disease" class="form-control">${guideDto.disease}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>可能的阳性点</th>
                        <td colspan="8">
                            <textarea rows="4" name="maySun" class="form-control">${guideDto.maySun}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>重点调查方向</th>
                        <td colspan="8">
                            <textarea rows="4" name="direction" class="form-control">${guideDto.direction}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>注意事项</th>
                        <td colspan="8">
                            <textarea rows="4" name="note" class="form-control">${guideDto.note}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>高度阳性</th>
                        <td colspan="8">
                            <%--<select name="isSun" class="form-control">--%>
                                <%--<option value="0" <c:if test="${guideDto.isSun==0}">selected="selected" </c:if>>否</option>--%>
                                <%--<option value="1" <c:if test="${guideDto.isSun==1}">selected="selected" </c:if>>是</option>--%>
                            <%--</select>--%>

                            <div class="label-btns guide">
                                <div class="label-btn <c:if test="${guideDto.isSun==0 || guideDto.isSun==null}">active</c:if> " >否</div>
                                <div class="label-btn <c:if test="${guideDto.isSun==1}">active </c:if>">是</div>
                            </div>
                            <input type="hidden" name="isSun" id="isSun" value="${guideDto.isSun}"/>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == 'visit'}">
                    <input type="hidden" name="visitId" value="${visitDto.id}" />
                    <input type="hidden" name="surveyInfoId" value="${id}" />
                    <input type="hidden" name="surveyAssorgCaseId" value="${surveyAssorgCaseId}" />
                    <tr>
                        <th>回访记录</th>
                        <td colspan="8">
                            <textarea rows="4" name="visitNote" class="form-control">${visitDto.visitNote}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>回访异常</th>
                        <td colspan="8">
                            <%--<select name="isAbnormal" class="form-control">--%>
                                <%--<option value="0" <c:if test="${visitDto.isAbnormal==0}">selected="selected" </c:if>>否</option>--%>
                                <%--<option value="1" <c:if test="${visitDto.isAbnormal==1}">selected="selected" </c:if>>是</option>--%>
                            <%--</select>--%>

                            <div class="label-btns visit">
                                <div class="label-btn <c:if test="${visitDto.isAbnormal==0 || visitDto.isAbnormal==null}">active</c:if> " >否</div>
                                <div class="label-btn <c:if test="${visitDto.isAbnormal==1}">active </c:if>">是</div>
                            </div>
                            <input type="hidden" name="isAbnormal" id="isAbnormal" value="${visitDto.isAbnormal}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>回访时间</th>
                        <td colspan="8">
                            <input id="visitTime" name="visitTime" type="text" class="form-control" style="cursor: auto"
                                   value="<fmt:formatDate value="${visitDto.visitTime}" pattern="yyyy-MM-dd"/>"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" disabled>
                        </td>
                    </tr>
                    <tr>
                        <th>回访人员</th>
                        <td colspan="8">
                            <input type="text" id="visitPerson" name="visitPerson" value="${visitDto.visitPerson}"  class="form-control" readonly="readonly">
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == 'updateTaskType'}">
                    修改任务类型:
                    <c:forEach items="${list}" var="item">
                        <c:if test="${returnType == 'updateCaseTaskType'}">
                            案件任务类型：
                            <td>${item.surveyBusinessTaskType.taskInfoName}--${item.selectType}</td>
                        </c:if>
                        <c:if test="${returnType == 'updateOrgTaskType'}">
                            <input type="hidden" name="assignOrgId" value="${assignOrgId}" />
                            机构任务类型：
                            <td>${item.surveyTaskType.taskName}--${item.selectType}</td>
                        </c:if>
                    </c:forEach>
                </c:if>

                <c:if test="${btnCode == 'extension'}">
                    <tr>
                        <th>保司收件邮箱地址</th>
                        <td><input type="text" class="form-control" name="toEmailAddress" id="toEmailAddress" value="${emailInfo.toEmailAddress}" /></td>
                    </tr>
                    <tr>
                        <th>抄送地址</th>
                        <td><input type="text" class="form-control" name="makeEmail" id="makeEmail" value="${emailInfo.makeEmail}" /></td>
                    </tr>
                    <tr>
                        <th>乐凡发件邮箱地址</th>
                        <td><input type="text" class="form-control" style="cursor:pointer;" readonly name="fromEmailAddress" id="fromEmailAddress" value="${emailInfo.emailAddress}" />
                            <input type="hidden" id="fromUserName" value="${emailInfo.emailUserName}" />
                            <input type="hidden" id="fromPwd" value="${emailInfo.emailPassword}" />
                        </td>
                    </tr>
                    <tr>
                        <th>邮箱正文</th>
                        <td><textarea class="form-control" name="emailContent" id="emailContent" style="height: 100px">${emailInfo.emailContent}</textarea></td>
                    </tr>
                    <tr>
                        <th>附件</th>
                        <td>
                            <input type="hidden" id="filesZip" name="filesZip" value='${filesZip}' />
                            <input type="hidden" id="surveyCaseNo" name="surveyCaseNo" value="${surveyCaseNo}" />
                            <div class='files' id="jq21" data-id="1">
                            <c:forEach items="${files}" var="item">
                                <div class="file" id="1">
                                <%--<a style="color: blue;" href="${item.filePath}" target="_blank">${item.fileName}</a>--%>
                                    <img src="${item.filePath}" class="image"/>
                                </div>
                            </c:forEach>
                            </div>
                        </td>
                    </tr>
                </c:if>
            </table>
        </div>
        <div class="modal-footer">
            <c:if test="${btnCode == 'workflow'}">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            </c:if>
            <c:if test="${btnCode == 'guide' || btnCode == 'guided'}">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">取消</button>
            </c:if>
            <c:if test="${btnCode == 'guide'}">
                <button type="submit" onclick="return validOpr('${btnCode}','guideSave');" class="btn btn-default" data-loading-text="Loading..." autocomplete="off">暂存</button>
            </c:if>
            <c:if test="${btnCode == 'extension'}">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDouble();">关闭</button>
            </c:if>
            <c:if test="${btnCode != 'workflow'}">
                <button type="submit" onclick="return validOpr('${btnCode}', null);" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>确认</button>
            </c:if>
        </div>
    </form>
</div>



<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script src="${ctx}/js/viewer.min.js"></script>


<script type="text/javascript">
    var editor1;
    layui.use(['layer'], function () {
        layer = layui.layer
    });
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $(document).ready(function(){
        $('.singleSelect').select2();
        //图片预览
        if($("#jq21 .file").length){
            viewer = new Viewer(document.getElementById('jq21'));
        }
    });

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
        event.preventDefault();
    });

    var updMoney=function(id,type){
        if(type == 1){
            $("#divSunMoney1" + id).hide();
            $("#divSunMoney2" + id).show();
        }
    }

    var email = true;
    loadingFiles();
    $("#emailChk").click(function(){
        loadingFiles();
    })
    function loadingFiles(){
        if ($("#btnCode").val() != '1300') {
            return;
        }
        var bool = $("#emailChk").is(":checked");
        if(!bool){
            $(".tr-email").hide();
            return;
        }else{
            $(".tr-email").show();
        }
        if (email) {
            $(".tr-email").show();
            $(".email-files-loading").show();
            //ajax加载邮件附件相关信息。
            $.ajax({
                url:"${ctx}/survey/case/ajaxData",
                data:{
                    id : $("#id").val(),//案件IDsurveyInfoId
                    btnCode : "1300",
                    maxSize : $("#maxSize").val()
                },
                success:function(res,param){
                    email = false;
                    $(".email-files-loading").hide();
                    var filesJSON = JSON.stringify(param.data.files);
                    $("#filesJSON").val(filesJSON)
                    var _html = "";
                    for (var i = 0; i < param.data.files.length; i++) {
                        var item = param.data.files[i];
                        _html += '<a href="' + item.httpFilePath + '" target="_blank">'+item.fileName+'</a>';
                    }
                    $(".email-files").html(_html);
                }
            })
        }
    }



    var updSunMoney=function(id,btnCode,ajax){
        var updSunMoney=$("#updSunMoney"+id).val();
        if(updSunMoney<0 || updSunMoney==null || updSunMoney == '' || updSunMoney == undefined){
            alert("请输入正确的金额!");
            return false;
        }
        $.ajax({
            url:"${ctx}/survey/case/sic/operate?id="+id+"&updSunMoney="+updSunMoney+"&btnCode="+btnCode,
            type:'POST',
            dataType:'json',
            success: function(v,data){
                console.log(v);
                console.log(data);
                if(data.data.msg=='成功'){
                    $("#divSunMoney1" + id).show();
                    $("#divSunMoney2" + id).hide();
                    $("#modifySunMoney"+id).text(updSunMoney);
                }
            }
        });
    }

    function validOpr(btnCode,type){
        if(btnCode == 'dispatch'){
            var size = "${surveyTaskInfos.size()}";
            if(size == 0){
                alert("无任务类型可调度!");
                return false;
            }
            var taskIds = [];
            $("input:checkbox[name='chkTaskIds']:checked").each(function() { // 遍历name=chkTaskIds的多选框
                taskIds.push(Number($(this).val()));
            });
            $("#taskIds").val(taskIds);
            if(!$("#taskIds").val()){
                alert("请选择任务类型");
                return false;
            }
        }else if(btnCode == 'guide' || btnCode == 'guided'){
            if(type !=null && type == 'guideSave'){
                $("#guideType").val(1);
            }
        }else if (btnCode == '1300'){
            if($("#emailChk") && $("#emailChk").prop("checked")){
                if(!$("#toEmailAddress").val()){
                    layer.msg("请联系运营人员添加保司收件邮箱地址！",{icon: 5});return false;
                }
                if(!$("#fromEmailAddress").val()){
                    layer.msg("请联系运营人员添加乐凡发件邮箱地址！",{icon: 5});return false;
                }
            }
            layer.load()
            $.ajax({
                url: '${ctx}/survey/case/operate',
                data: {
                    btnCode: '1300',
                    id : $("#id").val(),
                    oprTime : $("#oprTime").val(),
                    remark : $("#remark").val()
                },
                success: function (v,res) {
                    res = res.data
                    if (res.isSuccess){
                        if($("#emailChk") && $("#emailChk").prop("checked")){
                            //成功之后发邮件。
                            $.ajax({
                                url : '${ctx}/survey/case/operate',
                                data: {
                                    btnCode: 'sendEmail',
                                    id : $("#id").val(),
                                    toEmailAddress : $("#toEmailAddress").val(),
                                    makeEmail : $("#makeEmail").val(),
                                    fromEmailAddress : $("#fromEmailAddress").val(),
                                    fromUserName : $("#fromUserName").val(),
                                    fromPwd : $("#fromPwd").val(),
                                    emailContent : $("#emailContent").val(),
                                    filesJSON : $("#filesJSON").val(),
                                    surveyCaseNo : $("#surveyCaseNo").val()
                                },
                                success: function (v,res) {
                                    res = res.data
                                    layer.closeAll();
                                    if (res.isSuccess) {
                                        parent.parent.reloadParent();
                                    }else{
                                        layer.confirm('审核案件成功，发送邮件失败！', {
                                            btn: ['确定'] //按钮
                                        }, function () {
                                            parent.parent.reloadParent();
                                        });
                                    }
                                }
                            });
                        }else{
                            layer.closeAll();
                            parent.parent.reloadParent();
                        }
                    }else{
                        layer.closeAll();
                        layer.msg(res.msg,{icon: 5})
                    }
                }
            });
            return false;
        }else if(btnCode == 'extension'){
            $.ajax({
                url : '${ctx}/survey/case/operate',
                data: {
                    btnCode: 'extensionSendEmail',
                    toEmailAddress : $("#toEmailAddress").val(),
                    makeEmail : $("#makeEmail").val(),
                    fromEmailAddress : $("#fromEmailAddress").val(),
                    fromUserName : $("#fromUserName").val(),
                    fromPwd : $("#fromPwd").val(),
                    emailContent : $("#emailContent").val(),
                    filesZip : $("#filesZip").val(),
                    surveyCaseNo : "延期申请"
                },
                success: function (v,res) {
                    res = res.data
                    layer.closeAll();
                    if (res.isSuccess) {
                        parent.reloadParent();
                    }else{
                        layer.confirm('审核案件成功，发送邮件失败！', {
                            btn: ['确定'] //按钮
                        }, function () {
                            parent.reloadParent();
                        });
                    }
                }
            });
            return false;
        }
        return true;
    }

    function returnCallback(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){

        }else{
            alert(apiRsp.msg);return;
        }

        var btnCode = '${btnCode}';
        if(btnCode=='1400'){
            var closeBtn = $("#diglog_close_btn",window.parent.parent.document);
            closeBtn.click();
        }else if(btnCode=='1300'){
            var closeBtn = $("#diglog_close_btn",window.parent.parent.parent.document);
            closeBtn.click();
        }
        else{
            reloadParent();
        }
    }

    function choice(code,id,name,bool) {
        if(code=="services"){
            choiceServices(code,id,name,bool);//业务类型选择
        }else if(code=="surveyBus"){
            choiceSurveyBus(code,id,name,bool);//领域选择
        }else if(code=="payType"){
            choicePayType(code,id,name,bool);//结算方式
        }else if(code=="task"){
            choiceTask(code,id,name,bool);//任务类型
        }else if(code=="insureType")
            choiceInsureType(code,id,name,bool);//保险种类
    }
    //业务类型选择
    function choiceServices(code,id,name,bool) {
        $("#servicesName").val(name);
        $("#servicesId").val(id);
        var payType = $("#payType");
        //结算方式 选中清空
        $("[name=payTypes]").attr("class","item");

        //具体结算填写，清空
        $('#entrustMoney').val("");
        $('#tr_entrustMoney').hide();
        $('#entrustReLossesRemark').val("");
        $('#tr_entrustReLosses').hide();

        if(id == 13){//深度案件
            $("#pay").show();
            $("#payType").val('${dto.payType}');
            payTypeChange();
            $("#payType_3").hide();
            $("#payType_4").hide();
        }else{
//            单点调查与契约调查  结算方式只可以选择任务 隐藏结算方式
            $("#pay").hide();
            $("#payType").val(3);
        }

        if(!bool){
            $("#services_"+id).attr("class","item");
            $("#services_"+id).attr("onclick","choice('"+code+"',"+id+",'"+name+"',false)");
        }else{
            $("[name=servicesIds]").attr("class","item");
            $("#services_"+id).attr("class","item active");
            $("#services_"+id).attr("onclick","choice('"+code+"',"+id+",'"+name+"',true)");
        }
    }

    function choiceSubServices(code,id,name,bool,subId) {
        $("#servicesName").val(name);
        $("#servicesId").val(id);
        $("#subServiceId").val(subId);
        var payType = $("#payType");
        //结算方式 选中清空
        $("[name=payTypes]").attr("class","item");

        //具体结算填写，清空
        $('#entrustMoney').val("");
        $('#tr_entrustMoney').hide();
        $('#entrustReLossesRemark').val("");
        $('#tr_entrustReLosses').hide();

        if(id == 13){//深度案件
            $("#pay").show();
            $("#payType").val('${dto.payType}');
            payTypeChange();
            $("#payType_3").hide();
            $("#payType_4").hide();
        }else{
//            单点调查与契约调查  结算方式只可以选择任务 隐藏结算方式
            $("#pay").hide();
            $("#payType").val(3);
        }

        if(!bool){
            $("#services_"+id+"_"+subId).attr("class","item");
            $("#services_"+id+"_"+subId).attr("onclick","choiceSubServices('"+code+"',"+id+",'"+name+"',false, "+subId+")");
        }else{
            $("[name=servicesIds]").attr("class","item");
            $("#services_"+id+"_"+subId).attr("class","item active");
            $("#services_"+id+"_"+subId).attr("onclick","choiceSubServices('"+code+"',"+id+",'"+name+"',true, "+subId+")");
        }
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
        if (servicesId != 13){
            return;
        }
        var value = $("#payType").val();
        if(value == 1 || value == 2){
            $('#tr_entrustMoney').show();
        }else{
            $('#tr_entrustMoney').hide();
        }
        if(value == 2 || value == 4){
            $('#tr_entrustReLosses').show();
        }else{
            $('#tr_entrustReLosses').hide();
        }
        if(value == 1 || value == 2){
            $("#surveyMoney").attr("required","required");
        }else{
            $("#surveyMoney").attr("required",null);
        }
        if(value == 2 || value == 4){
            $("#surveyReLosses").attr("required","required");
        }else{
            $("#surveyReLosses").attr("required",null);
        }


    }

    changeEntrustOrg();
    var oldEntrustDepartmentId = $("#oldEntrustDepartmentId").val();
    console.log("oldEntrustDepartmentId",oldEntrustDepartmentId)
    function changeEntrustOrg(obj){
        var init = true;
        if(obj){
            init = false;
        }
        var consignorId = $("#entrustOrg").val();
        if(!consignorId){
            consignorId = $("#oldEntrustOrgId").val()
            if(!consignorId){
                return;
            }
        }
        $.ajax({
            url:"${ctx}/baseSurvey/selectInfoByRelationId",
            data:{"consignorId":consignorId,surveyCode:'consignor',btnCode:1000}
        }).done(
            function(e){
                $("#entrustDepartment option").remove();
                $("#entrustUser option").remove();
                $("#entrustUser").append("<option value=''>请选择委托人</option>");
                if(e.results.departmentList != null && e.results.departmentList.length > 1){
                    $("#entrustDepartment").append("<option value=''>请选择部门</option>");
                }
                for(var i = 0; i < e.results.departmentList.length; i++){
                    var val = e.results.departmentList[i];
                    if(init){
                        if(val.id == oldEntrustDepartmentId){
                            $("#entrustDepartment").append("<option value='" + val.id + "' selected = 'selected' >" + val.name + "</option>");
                        }else{
                            $("#entrustDepartment").append("<option value='" + val.id + "'>" + val.name + "</option>");
                        }
                    }else{
                        $("#entrustDepartment").append("<option value='" + val.id + "'>" + val.name + "</option>");
                    }
                }
                if(e.results.departmentList != null && e.results.departmentList.length == 1){
                    changeEntrustDepartment(e.results.departmentList[0].id,false)
                }
                if(init) {
                    changeEntrustDepartment(oldEntrustDepartmentId, true)
                }
            }
        );
        <%--ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId",{"consignorId":consignorId,surveyCode:'consignor',btnCode:1000},function(v,e,p){--%>


//        });
    }

    changeEntrustDepartment(oldEntrustDepartmentId, true)
    function changeEntrustDepartment(id,init){
        var departmentId = "";
        if(id!=null){
            departmentId = id;
        }else{
            departmentId = $("#entrustDepartment").val();
        }
        if(!departmentId){
            return;
        }
        $.ajax({
            url:"${ctx}/baseSurvey/selectInfoByRelationId",
            data:{"departmentId":departmentId,surveyCode:'consignorDepartment',btnCode:1000}
        }).done(
            function(e){
                var oldEntrustUserId = $("#oldEntrustUserId").val();
                $("#entrustUser option").remove();
                if(e.results != null && e.results.length > 1){
                    $("#entrustUser").append("<option value=''>请选择委托人</option>");
                }
                for(var i = 0; i < e.results.length; i++){
                    var val = e.results[i];
                    console.log("init",init);
                    console.log("val.userId",val.userId);
                    console.log("oldEntrustUserId",oldEntrustUserId);
                    if(init){
                        if(val.userId == oldEntrustUserId){
                            $("#entrustUser").append("<option value='" + val.userId + "' selected = 'selected'>" + val.userName + "        " + val.tel + "</option>");
                        }else{
                            $("#entrustUser").append("<option value='" + val.userId + "'>" + val.userName + "        " + val.tel + "</option>");
                        }
                    }else{
                        $("#entrustUser").append("<option value='" + val.userId + "'>" + val.userName + "        " + val.tel + "</option>");
                    }
                }
            }
        );

        <%--ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId",{"departmentId":departmentId,surveyCode:'consignorDepartment',btnCode:1000},function(v,e,p){--%>
            <%----%>
        <%--});--%>
    }

    $('.label-btns').on('click','.label-btn',function(){
        var _this = $(this)
        _this.siblings().removeClass('active')
        _this.addClass('active')

        if(_this.parent().hasClass('guide')){
            if(_this.text() == "是"){
                $("#isSun").val(1)
            }else if(_this.text() == "否"){
                $("#isSun").val(0)
            }
        }else if(_this.parent().hasClass('visit')){
            if(_this.text() == "是"){
                $("#isAbnormal").val(1)
            }else if(_this.text() == "否"){
                $("#isAbnormal").val(0)
            }
        }


    })

    function closeDouble(){
        var closeBtn = $("#diglog_close_btn",window.parent.parent.document);//关闭双层父级弹窗
        closeBtn.click();
        window.parent.parent.reload();
    }
</script>
</body>
</html>
