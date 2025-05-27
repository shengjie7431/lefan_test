<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>分值列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>分值列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/survey/case/listCaseDirection?pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <%--多选--%>
                    <input type="hidden" name="surveyOrgIds" id="surveyOrgIds" />
                    <input type="hidden" name="consignorIds" id="consignorIds" />
                    <input type="hidden" name="surveyUserIds" id="surveyUserIds" />

                    <div class="form-group">
                        调查机构:
                        <%--<select name="surveyOrgId" id="surveyOrgId" class="singleSelect form-control" onchange="selectUser()">
                            <option value="">全部</option>
                            <c:forEach items="${franchisee}" var="item">
                                <option <c:if test="${surveyOrgId == item.id}">selected="selected" </c:if> value="${item.id}" >${item.name}</option>
                            </c:forEach>
                        </select>--%>
                        <div>
                            <select class="select form-control select-checkbox" name="surveyOrgIdsChk" data-select-name="surveyOrgIds" data-select-values="${surveyOrgIds}" multiple >
                                <c:forEach items="${franchisee}" var="item">
                                    <option value="${item.id}" >${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        保险公司:
                        <div>
                            <select class="select form-control select-checkbox" name="consignorIdsChk" data-select-name="consignorIds" data-select-values="${consignorIds}" multiple >
                                <c:forEach items="${consignors}" var="item">
                                    <option value="${item.id}" >${item.company}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        调查员:
                        <%--<select name="surveyUserId" id="surveyUserId" class="form-control">
                            <option value="">请选择</option>
                            <c:forEach items="${investigator}" var="item">
                                <option <c:if test="${surveyUserId == item.userId}">selected="selected" </c:if> value="${item.userId}" >${item.realName}</option>
                            </c:forEach>
                        </select>--%>
                        <div>
                            <select class="select form-control select-checkbox" name="surveyUserIdsChk" data-select-name="surveyUserIds" data-select-values="${surveyUserIds}" multiple >
                                <c:forEach items="${investigator}" var="item">
                                    <option value="${item.userId}" >${item.realName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        平台复审时间：
                        <input name="reportStartDate" type="text" value="${reportStartDate}" style="width: 150px" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        <span>--</span>
                        <input name="reportEndDate" type="text" value="${reportEndDate}" style="width: 150px" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <div class="form-group">
                        保司终审时间：
                        <input name="entrReportStateDate" type="text" value="${entrReportStateDate}" style="width: 150px" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        <span>--</span>
                        <input name="entrReportEndDate" type="text" value="${entrReportEndDate}" style="width: 150px" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                        &nbsp; &nbsp;<button class="btn btn-default"><a href="${ctx}/survey/case/exportCaseDirection?surveyUserId=${surveyUserId}&surveyOrgId=${surveyOrgId}&entrReportStateDate=${entrReportStateDate}&entrReportEndDate=${entrReportEndDate}&reportStartDate=${reportStartDate}&reportEndDate=${reportEndDate}&surveyOrgIds=${surveyOrgIds}&surveyUserIds=${surveyUserIds}&consignorIds=${consignorIds}">导出</a></button>

                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th width="100">调查员</th>
                <th width="100">联系方式</th>
                <th width="100">所属机构</th>
                <th width="100">分值</th>
                <th width="80">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.surveyUserName}</td>
                    <td>${item.tel}</td>
                    <td>${item.surveyOrgName}</td>
                    <td>${item.scores}</td>
                    <td>
                        <a href="javascript:void(0);" onclick="info(${item.surveyUserId},${item.scores},'${surveyUserId}','${surveyOrgId}','${entrReportStateDate}','${entrReportEndDate}','${reportStartDate}','${reportEndDate}')">分值明细</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/survey/case/listCaseDirection?surveyUserId=${surveyUserId}&surveyOrgId=${surveyOrgId}&entrReportStateDate=${entrReportStateDate}&entrReportEndDate=${entrReportEndDate}&reportStartDate=${reportStartDate}&reportEndDate=${reportEndDate}&surveyOrgIds=${surveyOrgIds}&surveyUserIds=${surveyUserIds}&consignorIds=${consignorIds}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect.js?V=1"></script>
<script>
//    $(function () {
//        $('.singleSelect').select2();
//    });

$("#batchOperateBtn").click(function(){
    //select 多选 赋值 到隐藏域。 便于后台传值
    $(".select-checkbox").each(function(){
        var selName = $(this).attr("name");
        var all = $(".UCSelect[name="+selName+"]").find(".UCSelectAll").hasClass("Selected");
        var name = $(this).attr("data-select-name");
        if(name){
            $("#" + name).val($(this).val());
        }
    })
});

$(document).ready(function () {
    //默认选中 select
    $(".select-checkbox").each(function () {
        var value = $(this).attr("data-select-values");
        if (value) {
            $(this).val(value.replace(/\s*/g, '').split(','));
        } else {
            $(this).val("");
        }
    })
    $(".select-checkbox").UCFormSelect();

})
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
    var info = function(userId,scores,surveyUserId,surveyOrgId,entrReportStateDate,entrReportEndDate,reportStartDate,reportEndDate){
        var consignorIdsChk = $("select[name='consignorIdsChk'] option:selected")
        var ids = []
        consignorIdsChk.map(function(i,cur){
           ids.push($(cur).attr('value'))
        })
        var consignorIds = ids.join(',');
        openDialog({
            frame:true,
            title:"分值明细",
            height:800,
            width:1200,
            url:"${ctx}/survey/case/infoCaseDirection?userId=" + userId +"&scores=" + scores + "&surveyUserId="
                    + surveyUserId + "&surveyOrgId="+surveyOrgId+"&entrReportStateDate="+entrReportStateDate+"&entrReportEndDate+"+entrReportEndDate+"&reportStartDate="+reportStartDate+"&reportEndDate="+reportEndDate+"&consignorIds="+consignorIds,
            load:true
        });
    }

    function selectUser(){
        var surveyOrgId = $("#surveyOrgId").val();
        ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId",{"surveyOrgId":surveyOrgId,btnCode:1600,surveyCode:"franchisee"},function(v,e,p){
            $("#surveyUserId option").remove();
            $("#surveyUserId").append("<option value=''>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#surveyUserId").append("<option value='" + val.userId + "'>" + val.realName + "</option>");
            }
        });
    }
</script>
</body>
</html>
