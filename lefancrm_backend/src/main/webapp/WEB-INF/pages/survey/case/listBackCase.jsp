<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <style>
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
                <form class="form-inline" role="form" action="${ctx}/survey/case/listBackCase?menuCode=${menuCode}&pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <input type="hidden" name="sortField" id="sortField" value="${sortField}">
                    <input type="hidden" name="sortType" id="sortType" value="${sortType}">
                    <div class="form-group">
                        案件编号:<input name="surveyCaseNo" type="text" value="${surveyCaseNo}" class="form-control">
                    </div>
                    <div class="form-group">
                        调查编号:<input name="surveyNo" type="text" value="${surveyNo}" class="form-control">
                    </div>
                    <div class="form-group">
                       被调查人:<input name="surveyPerson" type="text" value="${surveyPerson}" class="form-control">
                    </div>
                    <div class="form-group">
                       联系方式:<input name="surveryPersonTel" type="text" value="${surveryPersonTel}" class="form-control">
                    </div>
                    <div class="form-group">
                        保单号:<input name="policyNo" type="text" value="${policyNo}" class="form-control">
                    </div>
                    <div class="form-group">
                        状态
                        <select name="operateState" class="form-control">
                            <option value="0" <c:if test="${operateState == '' || operateState == '0'}">selected="selected"</c:if>>待处理</option>
                            <option value="1" <c:if test="${operateState == '1'}">selected="selected"</c:if>>已处理</option>
                        </select>
                    </div>
                    <br>
                    <div class="form-group">
                        保险公司:<input name="entrustOrgName" type="text" value="${entrustOrgName}" class="form-control">
                    </div>

                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">案件编号</th>
                <th width="100">调查编号</th>
                <th width="100">被调查人</th>
                <th width="100">联系方式</th>
                <th width="100">申请机构</th>
                <th width="100">申请人</th>
                <th width="200">申请描述</th>
                <th width="100">
                    <div class="title_sort" data-id="" data-value="" data-field="createTime">
                        <span>申请时间</span>
                        <div class="icon-sort">
                            <div class="icon-up"></div>
                            <div class="icon-down"></div>
                        </div>
                    </div>
                </th>
                <th width="100">状态</th>
                <th width="100">保单号</th>
                <th width="150">保险公司</th>
                <th width="80">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <c:if test="${item.isShow == true}">
                    <tr onclick="info(${item.surveyRiskCaseInfo.id},${item.id})">
                        <td>${item.surveyRiskCase.surveyCaseNo}</td>
                        <td>${item.surveyRiskCase.surveyNo}</td>
                        <td>${item.surveyRiskCase.surveyPerson}</td>
                        <td>${item.surveyRiskCase.surveryPersonTel}</td>
                        <td>${item.orgName}</td>
                        <td>${item.surveyUserName}</td>
                        <td>${item.backRemark}</td>
                        <td>
                            <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/>
                        </td>
                        <td>${item.backStateName}</td>
                        <td>${item.surveyRiskCase.policyNo}</td>
                        <td>${item.surveyRiskCase.entrustOrgName}</td>
                        <td>
                            <a href="javascript:void(0);" onclick="info(${item.surveyRiskCaseInfo.id},${item.id})">处理</a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/survey/case/listBackCase?menuCode=${menuCode}&surveyPerson=${surveyPerson}&surveyPhase=${surveyPhase}&isSendReport=${isSendReport}&price1IsCalc=${price1IsCalc}&price2IsCalc=${price2IsCalc}&entrustOrgName=${entrustOrgName}&surveryPersonTel=${surveryPersonTel}&surveyNo=${surveyNo}&assignState=${assignState}&operateState=${operateState}&policyNo=${policyNo}&entrustOrgName=${entrustOrgName}&surveyCaseNo=${surveyCaseNo}&sortField=${sortField}&sortType=${sortType}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

    $(".title_sort").click(function () {
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
        var sortType = $("#sortType").val();
        var sortField= $("#sortField").val();
        if (sortType!=null && sortType !=''){
            $("div[data-field="+sortField+"]").find(sortType=='up'?'.icon-up':'.icon-down').addClass("active");
        }
    });

    var add = function(obj){
        openDialog({
            frame:true,
            title:"新增",
            height:700,
            width:1000,
            url:"${ctx}/survey/case/edit?obj=" + obj,
            load:true
        });
    }
    var info = function(id,backCaseId){
        var width = $(document.body).outerWidth();
        openDialog({
            frame:true,
            title:"详情",
            height:800,
            width:width,
            url:"${ctx}/survey/case/info?id=" + id + "&menuCode=${menuCode}&backCaseId=" + backCaseId,
            load:true
        });
    }
</script>
</body>
</html>
