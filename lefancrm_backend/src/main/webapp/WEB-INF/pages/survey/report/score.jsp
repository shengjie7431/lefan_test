<%--
  Created by IntelliJ IDEA.
  User: lixianfeng
  Date: 2019/9/10
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <style>
        .table tbody.class-list tr td{
            vertical-align: middle;
            border-right: 1px solid rgb(221, 221, 221);
        }
        .table tbody.class-list tr td:last-of-type{
            border-right: none;
        }
        .table  tr th{
            border-right: 1px solid rgb(221, 221, 221);
        }
    </style>
</head>
<body>
    <div class="main administrator">
        <div class="main-top">
            <h3>分值清单 <small>共<span>${apiRsp.count}</span>个</small></h3>
            <div style="text-align: right;margin-top: -34px;"><button class="btn btn-default"><a onclick="report('${menuCode}')" id="a_download">导出</a></button></div>
        </div><!--main-top-->
        <div class="panel panel-info">
            <input type="hidden" value="${menuCode}" name="menuCode" id="menuCode" />
            <table class="table" id="tab_temp">
                <thead>
                    <tr>
                        <th>案件编号</th>
                        <th>保险公司</th>
                        <th>被调查人</th>
                        <th>分派时间</th>
                        <th>调查完成时间</th>
                        <th>调查时效</th>
                        <th>是否阳性</th>
                        <th>是否退回</th>
                        <c:if test="${menuCode == 'org'}">
                            <th>调查员</th>
                        </c:if>
                        <th>方向编号</th>
                        <th>任务类型</th>
                        <th>任务子类</th>
                        <th>调查方向</th>
                        <th>评价</th>
                        <c:if test="${menuCode == 'survey'}">
                            <th>方向分值</th>
                            <th>总分值</th>
                        </c:if>
                        <c:if test="${menuCode == 'org' && orgType==1}">
                            <th>方向分值</th>
                            <th>总分值</th>
                        </c:if>
                        <c:if test="${menuCode == 'org' && (orgType==2 || orgType==3)}">
                            <th>方向金额</th>
                            <th>总金额</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${scores}" var="item">
                    <c:if test="${item.surveyCaseDirections.size() == 0}">
                        <tr>
                            <td>
                                <c:if test="${menuCode == 'org'}">
                                    <a onclick="info('${menuCode}',${item.caseInfoId},${item.surveyAssignCaseId},null)">${item.surveyCaseNo}</a>
                                </c:if>
                                <c:if test="${menuCode == 'survey'}">
                                    <a onclick="info('${menuCode}',${item.caseInfoId},null,${item.investigatorCaseId})">${item.surveyCaseNo}</a>
                                </c:if>
                            </td>
                            <td data-value="${item.surveyNo}">${item.entrustOrgName}</td>
                            <td data-value="${item.surveyNo}">${item.surveyPerson}</td>
                            <td data-value="${item.surveyNo}"><fmt:formatDate value="${item.assignDate}" pattern="yyyy-MM-dd"/></td>
                            <td data-value="${item.surveyNo}"><fmt:formatDate value="${item.entrustReportStartDate}" pattern="yyyy-MM-dd"/></td>
                            <td data-value="${item.surveyNo}" <c:if test="${item.isOverTime}"> style="color: red" </c:if>>${item.efficiency} 天</td>
                            <td data-value="${item.surveyNo}"><c:if test="${item.isSun == 0}">否</c:if><c:if test="${item.isSun == 1}">是</c:if></td>
                            <td data-value="${item.surveyNo}"><c:if test="${item.returnState == 0}">否</c:if><c:if test="${item.returnState == 1}">是</c:if></td>
                            <c:if test="${menuCode == 'org'}">
                                <td data-value="${item.surveyNo}"></td>
                            </c:if>
                            <td data-value="${item.surveyNo}"></td>
                            <td data-value="${item.surveyNo}"></td>
                            <td data-value="${item.surveyNo}"></td>
                            <td data-value="${item.surveyNo}"></td>
                            <td data-value="${item.surveyNo}"></td>
                            <td data-value="${item.surveyNo}"></td>
                        </tr>
                    </c:if>
                    <c:if test="${item.surveyCaseDirections.size() > 0}">
                        <c:forEach items="${item.surveyCaseDirections}" var="direction" varStatus="st">
                            <tr>
                                <td>
                                    <c:if test="${menuCode == 'org'}">
                                        <a onclick="info('${menuCode}',${item.caseInfoId},${item.surveyAssignCaseId},null)">${item.surveyCaseNo}</a>
                                    </c:if>
                                    <c:if test="${menuCode == 'survey'}">
                                        <a onclick="info('${menuCode}',${item.caseInfoId},null,${item.investigatorCaseId})">${item.surveyCaseNo}</a>
                                    </c:if>                                </td>
                                <td data-value="${item.surveyNo}">${item.entrustOrgName}</td>
                                <td data-value="${item.surveyNo}">${item.surveyPerson}</td>
                                <td data-value="${item.surveyNo}"><fmt:formatDate value="${item.assignDate}" pattern="yyyy-MM-dd"/></td>
                                <td data-value="${item.surveyNo}"><fmt:formatDate value="${item.entrustReportStartDate}" pattern="yyyy-MM-dd"/></td>
                                <td data-value="${item.surveyNo}" <c:if test="${item.isOverTime}"> style="color: red" </c:if>>${item.efficiency} 天</td>
                                <td data-value="${item.surveyNo}"><c:if test="${item.isSun == 0}">否</c:if><c:if test="${item.isSun == 1}">是</c:if></td>
                                <td data-value="${item.surveyNo}"><c:if test="${item.returnState == 0}">否</c:if><c:if test="${item.returnState == 1}">是</c:if></td>
                                <c:if test="${menuCode == 'org'}">
                                    <td data-value="${item.surveyNo}">${direction.surveyUserName}</td>
                                </c:if>
                                <td data-value="${item.surveyNo}">${st.index + 1}</td>
                                <td data-value="${item.surveyNo}">${direction.taskName}</td>
                                <td data-value="${item.surveyNo}">${direction.newName}</td>
                                <td data-value="${item.surveyNo}">${direction.directionName}</td>
                                <td data-value="${item.surveyNo}">
                                    <c:if test="${direction.evaluate == 1}"><span style="color: blue;">合格</span></c:if>
                                    <c:if test="${direction.evaluate == 2}"><span style="color: green;">优</span></c:if>
                                    <c:if test="${direction.evaluate == 3}"><span style="color: red;">差</span></c:if>
                                </td>
                                <td data-value="${item.surveyNo}">
                                    <c:if test="${(menuCode == 'org' && orgType==1) || menuCode == 'survey'}">
                                        ${direction.score}
                                    </c:if>
                                    <c:if test="${menuCode == 'org' && (orgType==2 || orgType==3)}">
                                        ${direction.surveyMoney}
                                    </c:if>
                                </td>
                                <td data-value="${item.surveyNo}">
                                    <c:if test="${(menuCode == 'org' && orgType==1) || menuCode == 'survey'}">
                                        ${item.score}
                                    </c:if>
                                    <c:if test="${menuCode == 'org' && (orgType==2 || orgType==3)}">
                                        ${item.entrustMoneySum}
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="main-bottom">
            <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
                <jsp:param name="paginationObjectName" value="apiRsp" />
                <jsp:param name="pageNoName" value="" />
                <jsp:param name="requestUrl" value="${ctx}/survey/report/tableList?menuCode=${menuCode}&startTime=${startTime}&endTime=${endTime}&orgType=${orgType}&surveyOrgId=${surveyOrgId}&checkType=${checkType}&entrustOrgId=${entrustOrgId}&surveyUserId=${surveyUserId}" />
                <jsp:param name="refreshDiv" value="" />
            </jsp:include>
        </div>
    </div>
    <%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
</body>
<script>
    function report(menuCode){
        if(menuCode == 'org'){
            var href = "${ctx}/survey/report/exportRoportScore?entrustOrgId=${entrustOrgId}&startTime=${startTime}&endTime=${endTime}&dataType=${menuCode}&surveyOrgId=${surveyOrgId}&orgType=${orgType}&checkType=${checkType}&entrustOrgIds=${entrustOrgIds}";
        }else if (menuCode == 'survey'){
            var href = "${ctx}/survey/report/exportRoportScore?entrustOrgId=${entrustOrgId}&startTime=${startTime}&endTime=${endTime}&dataType=${menuCode}&surveyOrgId=${surveyOrgId}&surveyUserId=${surveyUserId}&checkType=${checkType}&entrustOrgIds=${entrustOrgIds}";
        }
        $("#a_download").attr("href",href);
    }

    table_rowspan("#tab_temp",1);
    table_rowspan("#tab_temp",2);
    table_rowspan("#tab_temp",3);
    table_rowspan("#tab_temp",4);
    table_rowspan("#tab_temp",5);
    table_rowspan("#tab_temp",6);
    table_rowspan("#tab_temp",7);
    table_rowspan("#tab_temp",8);
    table_rowspan("#tab_temp",9);
    table_rowspan("#tab_temp",15);

    survey_table_rowspan();
    function survey_table_rowspan(){
        var menuCode = $("#menuCode").val();
        if(menuCode == "survey"){
            table_rowspan("#tab_temp",14);
        }
    }
    function table_rowspan(table_id, table_colnum) {
        table_firsttd = "";
        table_firsttdkey = "";
        table_currenttd = "";
        table_SpanNum = 0;
        colnum_Obj = $(table_id + " tr td:nth-child(" + table_colnum + ")");
        colnum_Obj.each(function (i) {
            if (i == 0) {
                table_firsttd = $(this);
                table_firsttdkey = $(this).attr("data-value");
                table_SpanNum = 1;
            } else {
                table_currenttd = $(this);
                var key = table_currenttd.attr("data-value");
                if (table_firsttd.text() == table_currenttd.text() && key == table_firsttdkey) {
                    table_SpanNum++;
                    table_currenttd.hide(); //remove();
                    table_firsttd.attr("rowspan", table_SpanNum);
                } else {
                    table_firsttd = $(this);
                    table_firsttdkey = $(this).attr("data-value");
                    table_SpanNum = 1;
                }
            }
        });
    }

    var info = function(dataType,surveyInfoId,surveyAssignCaseId,surveyInvestigatorCaseId){
        var menuCode = 'all-list';
        var url ='';
        if(dataType == 'org'){
            menuCode = 'org-review-list';
            url="${ctx}/survey/case/info?id=" + surveyInfoId + "&menuCode=" +menuCode+"&fromName=pointsDetails&assignOrgId="+surveyAssignCaseId
        }
        //调查员角色
        if(dataType == 'survey'){
            menuCode = 'dcy-list';
            url="${ctx}/survey/case/sic/info?id=" + surveyInvestigatorCaseId + "&menuCode="+menuCode
        }
        var title="案件详情";
        parent.parent.addTab(title,url,true);
        /*openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:url,
            load:true
        });*/
    }
</script>
</html>
