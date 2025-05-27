<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>互助超时列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>互助超时列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/survey/report/index" method="post">
                    <input type="hidden" name="menuCode" id="menuCode" value="${menuCode}"/>
                    <input type="hidden" name="surveyOrgIds" id="surveyOrgIds" />
                    <input type="hidden" name="entrustOrgIds" id="entrustOrgIds" />
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
                    <div class="form-group">
                        互助机构:
                        <select name="entrustOrgIdsChk" class="select form-control select-checkbox" data-select-name="entrustOrgIds" data-select-values="${entrustOrgIds}" multiple onchange="initData()">
                            <c:forEach items="${consignors}" var="item">
                                <option <c:if test="${entrustOrgId == item.id}">selected="selected" </c:if> value="${item.id}" >${item.company}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        期间：
                        <input name="beginDate" id="beginDate"  type="text" value="${beginDate}" style="width: 150px;cursor: pointer" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        <span style="padding: 0 5px">至</span>
                        <input name="endDate" id="endDate"  type="text" value="${endDate}" style="width: 150px;cursor: pointer" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        <button class="btn btn-default"><a href="${ctx}/survey/report/surveyOrgListExport?entrustOrgIds=${entrustOrgIds}&surveyOrgIds=${surveyOrgIds}&beginDate=${beginDate}&endDate=${endDate}">导出</a></button>
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="20%">调查机构</th>
                <th width="15%">案件数<span style="color: #004effa6;"><br/>总数:${objAvg.caseNum} 平均:${objAvg.caseAvg}</span></th>
                <th width="10%">超期数<span style="color: #004effa6;"><br/>总数:${objAvg.longTimeNum} 平均:${objAvg.longTimeAvg}</span></th>
                <th width="10%">超期率<span style="color: #004effa6;"><br/>互助超期率:${objAvg.longTimeRate}%</span></th>
                <th width="10%">驳回案件数<span style="color: #004effa6;"><br/>总数:${objAvg.vetoCaseNum} 平均:${objAvg.vetoCaseAvg}</span></th>
                <th width="10%">驳回次数<span style="color: #004effa6;"><br/>总数:${objAvg.vetoNum} 平均:${objAvg.vetoAvg}</span></th>
                <th width="10%">驳回率<span style="color: #004effa6;"><br/>互助驳回率:${objAvg.vetoRate}%</span></th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.surveyOrgName}</td>
                    <td><a href="javascript:huZhuList('${item.surveyOrgId}',0);">${item.caseNum}</a></td>
                    <td><a href="javascript:huZhuList('${item.surveyOrgId}',1);">${item.longNum}</a></td>
                    <td>${item.longRate}%</td>
                    <td><a href="javascript:huZhuList('${item.surveyOrgId}',2);">${item.vetoCaseNum}</a></td>
                    <td><a href="javascript:huZhuList('${item.surveyOrgId}',2);">${item.vetoNum}</a></td>
                    <td>${item.vetoRate}%</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/survey/report/index?entrustOrgIds=${entrustOrgIds}&surveyOrgIds=${surveyOrgIds}&menuCode=${menuCode}&beginDate=${beginDate}&endDate=${endDate}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect.js?V=1"></script>
<script>

    $("#batchOperateBtn").click(function(){
        //select 多选 赋值 到隐藏域。 便于后台传值
        $(".select-checkbox").each(function(){
            var name = $(this).attr("data-select-name");
            if(name){
                $("#" + name).val($(this).val());
            }
        })
    });

    $(document).ready(function () {
        $(".select-checkbox").each(function () {
            var value = $(this).attr("data-select-values");
            if (value) {
                $(this).val(value.replace(/\s*/g, '').split(','));
            } else {
                $(this).val("");
            }
        })
        $(".select-checkbox").UCFormSelect();
        $(".UCSelect[name=surveyOrgIdsChk]").width(320);
        $(".UCSelect[name=entrustOrgIdsChk]").width(320);
    })

    $(document).ready(function(){
        $("#check-btn").on('change',function(){
            $("input[name='list-checkbox']").prop("checked",this.checked);
        })
    })

    $(function(){
        $("#editForm").bind('submit', function(event) {

        });
    });


    var huZhuList = function(surveyOrgId,type){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"详情",
            height:height,
            width:width,
            url:"${ctx}/survey/report/huZhuList?surveyOrgId="+surveyOrgId+"&type="+type+"&beginDate="+$("#beginDate").val() + "&endDate=" + $("#endDate").val() + "&entrustOrgIds=${entrustOrgIds}",
            load:false
        });
    }
</script>
</body>
</html>
