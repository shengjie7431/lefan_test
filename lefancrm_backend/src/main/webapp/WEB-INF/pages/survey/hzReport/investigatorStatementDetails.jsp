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
    </div>

    <div class="panel panel-info">
        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">案件编号</th>
                <th width="100">被调查人</th>
                <th width="100">互助平台</th>
                <th width="100">调查员</th>
                <th width="100">任务状态</th>
                <th width="100">分配任务类型</th>
                <th width="100">机构案件类型</th>
                <th width="100">分派调查员日期</th>
                <th width="100">提交审核日期</th>
                <th width="100">调查员截止日期</th>
                <th width="100">调查员时效</th>
                <th width="100">是否初审通过</th>
                <th width="100">是否标记阳性</th>

            </tr>
            </thead>
            <tbody class="class-list info-1129">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td><a onclick="jumpPage(${item.aId})" style="color: #3ba9ff; cursor: pointer">${item.surveyNo}</a></td>
                    <td>${item.surveyPerson}</td>
                    <td>${item.name}</td>
                    <td>${item.surveyUserName}</td>
                    <td>
                        <c:if test="${item.surveyState == 0}">
                            未接收
                        </c:if>
                        <c:if test="${item.surveyState == 1}">
                            调查中
                        </c:if>
                        <c:if test="${item.surveyState == 2}">
                            已拒绝
                        </c:if>
                        <c:if test="${item.surveyState == 3}">
                            审核中
                        </c:if>
                        <c:if test="${item.surveyState == 4}">
                            已提交
                        </c:if>
                    </td>
                    <td>
                        <c:if test ="${item.taskTypeList == null}">
                            <a data-value="${item.taskTypeList}" class="viewdesc" title="暂无数据">查看描述</a>
                        </c:if>
                        <c:if test ="${item.taskTypeList != null}">
                            <a data-value="<c:forEach items="${item.taskTypeList}" var="task" >${task.taskName}&nbsp;</c:forEach>" class="viewdesc" title="<c:forEach items="${item.taskTypeList}" var="task" >${task.taskName}&nbsp;</c:forEach>">查看描述</a>
                        </c:if>
                        <%--<c:forEach items="${item.taskTypeList}" var="items">
                            ${items.taskName}
                        </c:forEach>--%>
                    </td>
                    <td>
                        <c:if test="${item.servicesId == 11 || item.servicesId == 12}">
                            单点调查
                        </c:if>
                        <c:if test="${item.servicesId == 13}">
                            深度调查
                        </c:if>
                    </td>
                    <td width="100"><fmt:formatDate value="${item.assignDate}" pattern="yyyy-MM-dd"/></td>
                    <td width="100"><fmt:formatDate value="${item.reportDate}" pattern="yyyy-MM-dd"/></td>
                    <td width="100"><fmt:formatDate value="${item.surveyEndTime}" pattern="yyyy-MM-dd"/></td>
                    <td>
                        <c:if test="${item.agingReal >= 0}">
                            <label style="color: #64DD17">${item.agingReal}天</label>
                        </c:if>
                        <c:if test="${item.agingReal < 0}">
                            <label style="color: red">${item.agingReal}天</label>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${item.returnState!=null}">
                            是
                        </c:if>
                        <c:if test="${item.returnState==null}">
                            否
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${item.sunTime != null}">
                            是
                        </c:if>
                        <c:if test="${item.sunTime == null}">
                            否
                        </c:if>
                    </td>
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
            <jsp:param name="requestUrl" value="${ctx}/survey/hzReport/popup?menuCode=investigatorReport&investigatorType=${investigatorType}&startTime=${startTime}&endTime=${endTime}&userId=${userId}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
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

/*    $('.info-1129').on('mouseover','.viewdesc',function () {
        var _this = this
        var content = $(this).attr("data-value") || "暂无数据";
        layer.tips(content, _this);
    })*/

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
    
    function jumpPage(id) {
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"",
            height:height,
            width:width,
            url:"${ctx}/survey/case/sic/info?id="+id+"&menuCode=dcy-list"
        });
        /*var url="${ctx}/survey/case/sic/info?id="+id+"&menuCode=dcy-list";
        var title = "调查处理(互助版)";
        parent.parent.addTab(title,url,true);*/
    }
</script>
</body>
</html>
