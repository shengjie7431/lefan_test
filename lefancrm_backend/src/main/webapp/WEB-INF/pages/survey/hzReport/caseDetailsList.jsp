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
        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">案件编号</th>
                <th width="100">被调查人</th>
                <th width="100">互助平台</th>
                <th width="100">调查机构</th>
                <th width="100">区域</th>
                <th width="100">机构案件类型</th>
                <th width="100">分派机构日期</th>
                <th width="100">机构提交日期</th>
                <th width="100">机构截止日期</th>
                <th width="100">机构时效</th>

            </tr>
            </thead>
            <tbody class="class-list info-1129">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.surveyNo}</td>
                    <td>${item.surveyPerson}</td>
                    <td>${item.entrustOrgName}</td>
                    <td>${item.surveyOrgName}</td>
                    <td>
                        <c:if test="${item.areaType==0}">
                            直辖市(市区)
                        </c:if>
                        <c:if test="${item.areaType==1}">
                            直辖市(郊区)
                        </c:if>
                        <c:if test="${item.areaType==2}">
                            省会
                        </c:if>
                        <c:if test="${item.areaType==3}">
                            地级市
                        </c:if>
                        <c:if test="${item.areaType==4}">
                            县级市
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${item.caseState==1}">
                            单点
                        </c:if>
                        <c:if test="${item.caseState==2}">
                            单点+单点
                        </c:if>
                        <c:if test="${item.caseState==3}">
                            全案
                        </c:if>
                        <c:if test="${item.caseState==4}">
                            全案+单点
                        </c:if>
                        <c:if test="${item.caseState==5}">
                            全案+全案
                        </c:if>
                    </td>
                    <td width="100"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></td>
                    <td width="100"><fmt:formatDate value="${item.reportDate}" pattern="yyyy-MM-dd"/></td>
                    <td width="100"><fmt:formatDate value="${item.orgEndTime}" pattern="yyyy-MM-dd"/></td>
                    <td width="100">
                        <c:if test="${item.agingDay == 0}">
                           <label>${item.agingDay}天</label>
                        </c:if>
                        <c:if test="${item.agingDay < 0}">
                            <label style="color: red">${item.agingDay.replace('-', '')}天</label>
                        </c:if>
                        <c:if test="${item.agingDay > 0}">
                            <label style="color: #00ee00">${item.agingDay}天</label>
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
            <jsp:param name="requestUrl" value="${ctx}/survey/hzReport/popup?menuCode=regionalDistribution&areaType=${areaType}&regionType=${regionType}&platform=${platform}&caseStatus=${caseStatus}&caseType=${caseType}&startTime=${startTime}&endTime=${endTime}" />
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

    $('.info-1129').on('mouseover','.viewdesc',function () {
        var _this = this
        var content = $(this).attr("data-value") || "暂无数据";
        layer.tips(content, _this);
    })

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
