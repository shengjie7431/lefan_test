<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <%--<link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">--%>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <style>
        .dalogs{
            display: none;
            position: absolute;
            top: 5%;
            left: 5%;
            width: 90%;
            min-height: 400px;
            background-color: #fff;
            box-shadow: 0 0 10px #999;
            color: #000;
            padding: 20px;
        }

        .dalogsAdd{
            width: 600px;
            left: 30%;
        }
        .dalogsTip{
            width: 300px;
            height: 200px;
        }
        .viewdesc{
            cursor: pointer;
            text-decoration: underline;
            color: #3BA9FF;
        }
        .aColor{
            cursor: pointer;
            text-decoration: underline;
            color: #3BA9FF;
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>案件列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/survey/hzReport/popup" method="post">
                    <input type="hidden" name="menuCode" id="menuCode" value="${menuCode}"/>
                    <input type="hidden" name="caseCode" id="caseCode" value="${caseCode}"/>
                    <input type="hidden" name="scoreRole" id="scoreRole" value="${scoreRole}"/>
                    <input type="hidden" name="btnCode" id="btnCode" value="${btnCode}"/>
                    <input type="hidden" name="caseState" id="caseState" value="${caseState}"/>
                    <input type="hidden" name="entrustOrgIds" id="entrustOrgIds" value="${entrustOrgIds}"/>
                    <input type="hidden" name="orgCaseState" id="orgCaseState" value="${orgCaseState}"/>
                    <input type="hidden" name="surveyState" id="surveyState" value="${surveyState}"/>
                    <input type="hidden" name="surveyOrgId" id="surveyOrgId" value="${surveyOrgId}"/>
                    <input type="hidden" name="surveyUserId" id="surveyUserId" value="${surveyUserId}"/>
                    <input type="hidden" name="startTime" id="startTime" value="${startTime}"/>
                    <input type="hidden" name="endTime" id="endTime" value="${endTime}"/>
                    <input type="hidden" name="searchType" id="searchType" value="${searchType}"/>
                    <input type="hidden" name="orgAttr" id="orgAttr" value="${orgAttr}"/>
                    <input type="hidden" name="caseType" id="caseType" value="${caseType}"/>
                    <input type="hidden" name="orgLevel" id="orgLevel" value="${orgLevel}"/>

                    <div class="form-group">
                        快捷查询:<input style="width: 350px" name="searchStr" type="text" value="${searchStr}" placeholder="可输入被调查人，案件编号，调查编号，联系方式，身份证号" class="form-control">
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">案件编号</th>
                <th width="100">被调查人</th>
                <c:if test="${orgAttr==2}">
                    <th width="100">互助平台</th>
                </c:if>
                <c:if test="${orgAttr==1}">
                    <th width="100">保险公司</th>
                </c:if>
                <c:if test="${caseCode == 'org'}">
                    <th width="100">调查需求</th>
                    <c:if test="${scoreRole != 'areaManger' && orgAttr==2}">
                        <th width="100">机构案件状态</th>
                    </c:if>
                    <th width="100">分派机构日期</th>
                    <th width="100">机构提交日期</th>
                    <th width="100">机构截止日期</th>
                    <th width="100">机构时效</th>
                    <th width="100">调查总积分</th>
                    <th width="100">阳性总积分</th>
                    <th width="100">合计积分</th>
                </c:if>
                <c:if test="${caseCode == 'user'}">
                    <th width="100">分配任务类型</th>
                    <th width="100">分派调查员日期</th>
                    <th width="100">提交审核日期</th>
                    <th width="100">调查员截止日期</th>
                    <th width="100">调查员时效</th>
                    <th width="100">超期考核绩效</th>
                    <th width="100">调查总积分</th>
                    <th width="100">阳性总积分</th>
                    <th width="100">合计积分</th>
                </c:if>

            </tr>
            </thead>
            <tbody class="class-list info-1129">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>
                        <a href="javascript:void(0);" onclick="info(${item.surveyInfoId},${item.assignCaseId})" class="aColor">${item.surveyNo}</a>
                    </td>
                    <td>${item.surveyPerson}</td>
                    <td>${item.entrustOrgName}</td>
                    <c:if test="${caseCode == 'org'}">
                        <td>
                            <c:if test ="${item.orgTaskRemark == null}">
                                <a data-value="${item.orgTaskRemark}" class="viewdesc" title="暂无数据">查看描述</a></td>
                            </c:if>
                            <c:if test ="${item.orgTaskRemark != null}">
                                <a data-value="${item.orgTaskRemark}" class="viewdesc" title="${item.orgTaskRemark}">查看描述</a></td>
                            </c:if>
                            <c:if test="${scoreRole != 'areaManger' && orgAttr==2}">
                                <td width="100">${item.orgCaseStateName}</td>
                            </c:if>
                        <td width="100"><fmt:formatDate value="${item.orgAssignDate}" pattern="yyyy-MM-dd"/></td>
                        <td width="100"><fmt:formatDate value="${item.orgCreportDate}" pattern="yyyy-MM-dd"/></td>
                        <td width="100"><fmt:formatDate value="${item.orgEndTime}" pattern="yyyy-MM-dd"/></td>
                        <td width="100">
                            <c:if test="${item.orgDays == 0}">
                                <label>${item.orgDays}天</label>
                            </c:if>
                            <c:if test="${item.orgDays < 0}">
                                <label style="color: red">${item.orgDays.toString().replace('-','')}天</label>
                            </c:if>
                            <c:if test="${item.orgDays > 0}">
                                <label style="color: #00ee00">${item.orgDays}天</label>
                            </c:if>
                        </td>
                        <td width="100">${item.orgBaseScore}</td>
                        <td width="100">${item.orgSunScore}</td>
                        <td width="100">${item.orgScore}</td>
                    </c:if>

                    <c:if test="${caseCode == 'user'}">
                        <td>
                                <a data-value="<c:forEach items="${item.tasks}" var="task" >${task.taskName}&nbsp;</c:forEach>"
                                   title="<c:forEach items="${item.tasks}" var="task" >${task.taskName}&nbsp;</c:forEach>" class="viewdesc">查看详情</a>
                        </td>
                        <td width="100"><fmt:formatDate value="${item.userAssignDate}" pattern="yyyy-MM-dd"/></td>
                        <td width="100"><fmt:formatDate value="${item.userCreportDate}" pattern="yyyy-MM-dd"/></td>
                        <td width="100"><fmt:formatDate value="${item.userEndTime}" pattern="yyyy-MM-dd"/></td>
                        <td width="100">
                            <c:if test="${item.userDays == 0}">
                                <label>${item.userDays}天</label>
                            </c:if>
                            <c:if test="${item.userDays < 0}">
                                <label style="color: red">${item.userDays.toString().replace('-', '')}天</label>
                            </c:if>
                            <c:if test="${item.userDays > 0}">
                                <label style="color: #00ee00">${item.userDays}天</label>
                            </c:if>
                        </td>
                        <td width="100">${item.overdueAgingRate}</td>
                        <td width="100">
                            <c:if test="${item.assessScore != item.userBaseScore}">
                                <span style="color: red;text-decoration: line-through;">${item.assessScore}</span>&nbsp;&nbsp;
                            </c:if>
                            <span>${item.userBaseScore}</span>
                        </td>
                        <td width="100">
                            <c:if test="${item.assessSunScore != item.userSunScore}">
                                <span style="color: red;text-decoration: line-through;">${item.assessSunScore}</span>&nbsp;&nbsp;
                            </c:if>
                            <span>${item.userSunScore}</span>
                        </td>
                        <td width="100">
                            <c:if test="${(item.assessScore + item.assessSunScore) != item.userScore}">
                                <span style="color: red;text-decoration: line-through;">${item.assessScore + item.assessSunScore}</span>&nbsp;&nbsp;
                            </c:if>
                            <span>${item.userScore}</span>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="dalogs dalogsTip">
        <div class="close" onclick="javascript:$('.dalogsTip').hide()">×</div>
        <div class="d_content" style="padding: 20px;overflow: auto;height: 100%"></div>
    </div>
<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/survey/hzReport/popup?menuCode=${menuCode}&caseCode=${caseCode}&searchStr=${searchStr}&btnCode=${btnCode}&caseState=${caseState}&entrustOrgIds=${entrustOrgIds}&orgCaseState=${orgCaseState}&surveyState=${surveyState}&surveyOrgId=${surveyOrgId}&surveyUserId=${surveyUserId}&startTime=${startTime}&endTime=${endTime}&searchType=${searchType}&scoreRole=${scoreRole}&orgAttr=${orgAttr}&orgLevel=${orgLevel}&caseType=${caseType}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<%--<script src="${ctx}/js/jquery-1.8.2.min.js"></script>--%>
<script src="${ctx}/js/jquery.min.js" charset="utf-8"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script>
    var layer = ''
    layui.use('layer',function () {
        layer = layui.layer
    })
    function showReLoossesRemark(type,obj){
        var dialog = $(".dalogsTip");
        if(dialog.is(":hidden")){
            dialog.show();
        }else{
            dialog.hide();
        }
        var position = $(obj).position();
        var left = position.left;
        if (type ==2 ){
            left = left - 360
        }else{
            left = left + 120
        }
        $(".dalogsTip").offset({
            left: left,
            top: $(obj).parents('.info-1129').position().top + position.top
        });
        var content = $(obj).attr("data-value");
        if(content != ''){
            $(".dalogsTip .d_content").html(content);
        }else{
            $(".dalogsTip .d_content").html("暂无数据");
        }
    }
    //
    // $('.info-1129').on('mouseover','.viewdesc',function () {
    //     var _this = this
    //     var content = $(this).attr("data-value") || "暂无数据";
    //     layer.tips(content, _this, {
    //         tips: [2, '#666'],
    //         time: 2000
    //     });
    // })

    var info = function(id,assignCaseId){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        var scoreRole =$("#scoreRole").val();
        var menuCode ='';
        if(scoreRole == 'provincialManger' || scoreRole == 'areaManger'){
            menuCode = 'org-review-list';
        }else if(scoreRole == 'manger'){
            menuCode= 'all-list';
        }
        console.log('menuCode-----------',menuCode);
        openDialog({
            frame:true,
            title:"",
            height:height,
            width:width,
            url:"${ctx}/survey/case/info?id=" + id + "&menuCode=" +menuCode+"&fromName=pointsDetails&assignOrgId="+assignCaseId
        });
    }
</script>
</body>
</html>
