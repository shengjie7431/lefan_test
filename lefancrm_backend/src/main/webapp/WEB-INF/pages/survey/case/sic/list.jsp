<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>调查员案件列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">

    <style>

        .table-content {
            /*width: 1694px;*/
            overflow: auto;
            position: relative;
        }
        .redBackGroud td{
          background-color: #f9e3e4 !important;
        }
        .buleBackGroud td{
            background-color: #D5EDFF !important;
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

        .main-bottom{
            position: relative;
        }
        .panel-info{
            margin-bottom: 60px;
        }
        .pagePosi{
            position: fixed;
            bottom: 0;
            width: 100%;
            background-color: #fff;
            padding:6px 0;
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
    </style>
</head>
<body>
<%--调查处理--%>
<div class="main administrator">
    <div class="main-top">
        <h3>案件列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/survey/case/sic/list?menuCode=${menuCode}&pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <%--多选--%>
                    <input type="hidden" name="entrustOrgIds" id="entrustOrgIds" />
                    <input type="hidden" name="serviceTypes" id="serviceTypes" />
                    <input type="hidden" name="size" id="size" value="${apiRsp.results.size()}">
                    <input type="hidden" name="sortField" id="sortField" value="${sortField}">
                    <input type="hidden" name="sortType" id="sortType" value="${sortType}">
                    <div class="form-group">
                        快捷查询：<input style="width: 500px" name="searchStr" type="text" value="${searchStr}" placeholder="可输入被调查人，案件编号，调查编号，联系方式，身份证号" class="form-control">
                    </div>

<%--                    <div class="form-group">--%>
<%--                        案件编号:<input name="surveyCaseNo" type="text" value="${surveyCaseNo}" class="form-control">--%>
<%--                    </div>--%>
<%--                    <c:if test="${menuCode != 'dcy-list'}">--%>
<%--                        <div class="form-group">--%>
<%--                            调查编号:<input name="surveyNo" type="text" value="${surveyNo}" class="form-control">--%>
<%--                        </div>--%>
<%--                    </c:if>--%>
<%--                    <div class="form-group">--%>
<%--                        被调查人:<input name="surveyPerson" type="text" value="${surveyPerson}" class="form-control">--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        联系方式:<input name="surveryPersonTel" type="text" value="${surveryPersonTel}" class="form-control">--%>
<%--                    </div>--%>
                    <div class="form-group">
                        保险公司：
                        <%--<select name="entrustOrgId" id="entrustOrgId" class="singleSelect form-control">--%>
                            <%--<option value="">全部</option>--%>
                            <%--<c:forEach items="${consignors}" var="item">--%>
                                <%--<option <c:if test="${entrustOrgId == item.id}">selected="selected" </c:if> value="${item.id}" >${item.company}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                        <div>
                            <select class="select form-control select-checkbox"  name="entrustOrgIdsChk" data-select-name="entrustOrgIds" data-select-values="${entrustOrgIds}" multiple >
                                <c:forEach items="${consignors}" var="item">
                                    <option value="${item.id}" >${item.company}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        业务类型：
                        <select  name="serviceTypeChk" class="select form-control select-checkbox" data-select-name="serviceTypes"  data-select-values="${serviceTypes}" multiple>
                            <option value="12">单点调查</option>
                            <option value="13">深度调查</option>
                            <option value="11">契约调查</option>
                        </select>
                    </div>
                    <c:if test="${menuCode != 'dcy-list'}">
                        <br>
                    </c:if>
                        <c:if test="${menuCode == 'task-user-review'}">
                            预审状态：
                            <select onchange="javascript:$('#batchOperateBtn').click();" name="reviewOff" class="form-control">
<%--                                <option value="" <c:if test="${reviewOff == ''}">selected="selected"</c:if>>全部</option>--%>
                                <option value="0" <c:if test="${reviewOff == '0'}">selected="selected"</c:if>>待预审</option>
                                <option value="1" <c:if test="${reviewOff == '1'}">selected="selected"</c:if>>已预审</option>
                            </select>
                        </c:if>
                    <%--<div class="form-group">--%>
                        <%--案件阶段:--%>
                        <%--<select name="surveyPhase" class="form-control">--%>
                            <%--<option value="">全部</option>--%>
                            <%--<option value="1">委托阶段</option>--%>
                            <%--<option value="2">调查阶段</option>--%>
                            <%--<option value="3">结案</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <c:if test="${menuCode == 'dcy-list'}">
                            任务状态
                            <select onchange="javascript:$('#batchOperateBtn').click();" name="operateState" class="form-control">
<%--                                <option value="0" <c:if test="${operateState == null || operateState == '' || operateState == '0'}">selected="selected"</c:if>>待调查</option>--%>
<%--                                <option value="2" <c:if test="${operateState == '2'}">selected="selected"</c:if>>待接收</option>--%>
<%--                                <option value="3" <c:if test="${operateState == '3'}">selected="selected"</c:if>>调查中</option>--%>
<%--                                <option value="4" <c:if test="${operateState == '4'}">selected="selected"</c:if>>待提交报告结论</option>--%>
<%--                                &lt;%&ndash;<option value="5" <c:if test="${operateState == '5'}">selected="selected"</c:if>>审核通过</option>&ndash;%&gt;--%>
<%--                                &lt;%&ndash;<option value="6" <c:if test="${operateState == '6'}">selected="selected"</c:if>>已拒绝</option>&ndash;%&gt;--%>
<%--                                <option value="7" <c:if test="${operateState == '7'}">selected="selected"</c:if>>初审中</option>--%>
<%--                                <option value="8" <c:if test="${operateState == '8'}">selected="selected"</c:if>>平台复审中</option>--%>
<%--                                <option value="9" <c:if test="${operateState == '9'}">selected="selected"</c:if>>保司终审中</option>--%>
<%--                                <option value="10" <c:if test="${operateState == '10'}">selected="selected"</c:if>>保司终审通过</option>--%>
<%--                                <option value="1" <c:if test="${operateState == '1'}">selected="selected"</c:if>>已调查</option>--%>

                                <option value="1" <c:if test="${operateState == null || operateState == '' || operateState == '1'}">selected="selected"</c:if> >调查中</option>
                                <option value="2" <c:if test="${operateState == '2'}">selected="selected"</c:if> >已提交</option>
                            </select>
                        </c:if>
                        <c:if test="${menuCode == 'report-list'}">
                            案件状态
                            <select onchange="javascript:$('#batchOperateBtn').click();" name="operateState" class="form-control">
                                <option value="0" <c:if test="${operateState == '' || operateState == '0'}">selected="selected"</c:if>>审核中</option>
                                <option value="1" <c:if test="${operateState == '1'}">selected="selected"</c:if>>已审核</option>
                            </select>
                        </c:if>
                    </div>
                    <c:if test="${menuCode == 'report-list'}">
                        报告状态:
                        <select onchange="javascript:$('#batchOperateBtn').click();" name="creportState"  class="form-control">
                            <option value=""  <c:if test="${creportState == ''}">selected="selected" </c:if> >全部</option>
                            <option value="0" <c:if test="${creportState == '0'}">selected="selected" </c:if> >未上传</option>
                            <option value="1" <c:if test="${creportState == '1'}">selected="selected" </c:if> >已上传</option>
                        </select>
                    </c:if>
                    <c:if test="${menuCode == 'over-time-list'}">
                        是否超时:
                        <select onchange="javascript:$('#batchOperateBtn').click();" name="overTimeType"  class="form-control">
                            <option value=""  <c:if test="${overTimeType == ''}">selected="selected" </c:if> >全部</option>
                            <option value="1" <c:if test="${overTimeType == '1'}">selected="selected" </c:if> >已超时</option>
                            <option value="2" <c:if test="${overTimeType == '2'}">selected="selected" </c:if> >即将超时</option>
                        </select>
                    </c:if>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        <c:if test="${menuCode == 'my-list'}">
                            &nbsp; &nbsp;<button onclick="add()" type="button" class="btn btn-default">新增</button>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>

        <div class="table-content">
            <table class="table table-striped table-hover "id="table-survey-list" style="cursor: pointer;">
                <thead class="thead-light">
                    <tr>
                        <th width="100">案件编号</th>
                        <th width="100">保险公司</th>
                        <th width="100">被调查人</th>
                        <th width="100">联系方式</th>
                        <th width="150">领域</th>
                        <th width="100">业务类型</th>
                        <c:if test="${menuCode == 'sun-list'}"><th width="100">申请人</th></c:if>
                        <th width="100">调查员</th>
                        <c:if test="${menuCode == 'task-user-review'}">
                            <th width="100">预审状态</th>
                        </c:if>
                        <c:if test="${menuCode == 'dcy-list' || menuCode == 'task-user-review'}">
                            <th width="100">
                                <div class="title_sort" data-id="50" data-value="" data-field="assignDate">
                                    <span>分派时间</span>
                                    <div class="icon-sort">
                                        <div class="icon-up" ></div>
                                        <div class="icon-down" ></div>
                                    </div>
                                </div>
                            </th>
                            <th width="100">
                                <div class="title_sort" data-id="50" data-value="" data-field="creportDate">
                                    <span>提交审核时间</span>
                                    <div class="icon-sort">
                                        <div class="icon-up" ></div>
                                        <div class="icon-down" ></div>
                                    </div>
                                </div>
                            </th>
                            <th width="100">
                                <div class="title_sort" data-id="50" data-value="" data-field="surveyEndTime">
                                    <span>调查截止时间</span>
                                    <div class="icon-sort">
                                        <div class="icon-up" ></div>
                                        <div class="icon-down" ></div>
                                    </div>
                                </div>
                            </th>
                            <th width="100">调查员时效</th>
                            <th width="100">任务状态</th>
                        </c:if>
                        <c:if test="${menuCode == 'over-time-list'}">
                            <th width="150">
                                <div class="title_sort" data-id="50" data-value="" data-field="surveyEndTime">
                                    <span>截止时间</span>
                                    <div class="icon-sort">
                                        <div class="icon-up" ></div>
                                        <div class="icon-down" ></div>
                                    </div>
                                </div>
                            </th>
                            <th width="100">是否超时</th>
                        </c:if>
                        <th width="80">费用报销</th>
<%--                        <th width="80">报销状态</th>--%>
<%--                        <th width="80">操作</th>--%>
                    </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr  title="单击打开${item.surveyRiskCase.surveyCaseNo}详情"  <c:if test="${menuCode == 'task-user-review'}">
                    onclick="info2(${item.surveyRiskCaseInfo.id})"
                            </c:if>
                            <c:if test="${menuCode != 'task-user-review'}">
                                onclick="info(${item.id},'${menuCode}','${item.reStateStr}')"
                            </c:if>
                        <c:if test="${menuCode == 'dcy-list'}"> class="<c:if test="${item.backgroundColor =='#f9e3e4'}">redBackGroud</c:if>
                    <c:if test="${item.backgroundColor =='#D5EDFF'}">buleBackGroud</c:if>" </c:if>>
                    <td width="100">${item.surveyRiskCase.surveyCaseNo}</td>
                    <td width="100">${item.entrustOrgName}</td>
                    <td width="150">
                        <c:if test="${menuCode == 'dcy-list' && item.urgent}">
                            <span style="background-color: #FF0000;color: aliceblue;border: 1px solid red;width: 20px;display: block;float: left;text-align: center;margin-right: 2px;">急</span>
                        </c:if>
                        <c:if test="${item.newCase == 1 && menuCode == 'dcy-list'}">
                            <span style="background-color: #FF0000;color: aliceblue;border: 1px solid red;width: 20px;display: block;float: left;text-align: center;">新</span>
                        </c:if>
                            ${item.surveyRiskCase.surveyPerson}<c:if test="${item.surveyRiskCase.transferType != null}"><span style="color: red" >(${item.surveyRiskCase.transferTypeName})</span></c:if>
                    </td>
                    <td width="100">${item.surveyRiskCase.surveryPersonTel}</td>
                    <td width="150">${item.surveyRiskCaseInfo.surveyBusName}</td>
                    <td width="100">
                        <span <c:if test="${item.servicesId == 13}">class="span-sd"</c:if> >${item.servicesName}</span>
                    </td>
                    <c:if test="${menuCode == 'sun-list'}"><th width="100">${item.surveyUserName}</th></c:if>
                    <td width="100">${item.surveyUserName}<c:if test="${item.showKey}">（主）</c:if> </td>
                    <c:if test="${menuCode == 'task-user-review'}">
                        <td width="100">
                            <c:if test="${item.reviewOff == 0}">待预审</c:if>
                            <c:if test="${item.reviewOff == 1}">预审通过</c:if>
                        </td>
                    </c:if>
                    <c:if test="${menuCode == 'dcy-list' || menuCode == 'task-user-review'}">
                        <td width="100"><fmt:formatDate value="${item.assignDate}" pattern="yyyy-MM-dd"/></td>
                        <td width="100"><fmt:formatDate value="${item.creportDate}" pattern="yyyy-MM-dd"/></td>
                        <td width="100"><fmt:formatDate value="${item.surveyEndTime}" pattern="yyyy-MM-dd"/></td>
                        <td width="100"><span style="color: ${item.efficiencyStateColor};">${item.efficiencyState}</span></td>
                        <td width="100">
                            <c:if test="${operateState == null || operateState == '' || operateState == '1'}">调查中</c:if>
                            <c:if test="${operateState == '2'}">已提交</c:if>
                        </td>
                    </c:if>
                    <c:if test="${menuCode == 'over-time-list'}">
                        <td width="150"><fmt:formatDate value="${item.surveyEndTime}" pattern="yyyy-MM-dd"/></td>
                        <td width="100">
                            <c:if test="${item.overTimeType == 1}">已超时</c:if>
                            <c:if test="${item.overTimeType == 2}">即将超时</c:if>
                        </td>
                    </c:if>
                    <td width="80">
                        <c:if test="${item.showExpenseReimbursementValue || item.showBaoSi}">
                            ${item.expenseReimbursementValue==null?0:item.expenseReimbursementValue}
                        </c:if>
                    </td>
<%--                    <td width="80">--%>
<%--                        <c:if test="${item.showExpenseReimbursementValue || item.showBaoSi}">--%>
<%--                            <c:if test="${item.reStateStr != null && item.reStateStr != ''}"> ${item.reStateStr}</c:if>--%>
<%--                            <c:if test="${item.reStateStr == null || item.reStateStr == ''}"> 待财务通知</c:if>--%>
<%--                        </c:if>--%>
<%--                    </td>--%>
<%--                    <td width="80">--%>
<%--                        <c:if test="${menuCode == 'task-user-review'}">--%>
<%--                            <a href="javascript:void(0);" onclick="info2(${item.surveyRiskCaseInfo.id})">处理</a>--%>
<%--                        </c:if>--%>
<%--                        <c:if test="${menuCode != 'task-user-review'}">--%>
<%--                            <a href="javascript:void(0);" onclick="info(${item.id},'${menuCode}','${item.reStateStr}')">处理</a>--%>
<%--                        </c:if>--%>

<%--                    </td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
    </div><!--panel-info-->

<div class="main-bottom">
    <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
        <jsp:param name="paginationObjectName" value="apiRsp" />
        <jsp:param name="pageNoName" value="" />
        <jsp:param name="requestUrl" value="${ctx}/survey/case/sic/list?menuCode=${menuCode}&searchStr=${searchStr}&surveyPerson=${surveyPerson}&surveyPhase=${surveyPhase}&operateState=${operateState}&creportState=${creportState}&surveyNo=${surveyNo}&surveryPersonTel=${surveryPersonTel}&policyNo=${policyNo}&entrustOrgName=${entrustOrgName}&overTimeType=${overTimeType}&entrustOrgId=${entrustOrgId}&surveyCaseNo=${surveyCaseNo}&entrustOrgIds=${entrustOrgIds}&serviceTypes=${serviceTypes}&sortField=${sortField}&sortType=${sortType}" />
        <jsp:param name="refreshDiv" value="" />
    </jsp:include>

    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
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
    $(document).ready(function () {
        var sortType = $("#sortType").val();
        var sortField= $("#sortField").val();
        if (sortType!=null && sortType !=''){
            $("div[data-field="+sortField+"]").find(sortType=='up'?'.icon-up':'.icon-down').addClass("active");
        }
        var height_doc = window.parent.innerHeight - 50 -27 -66 -$('.panel-heading').height() - 70
        $(".table-content").height(height_doc).css({
            overflow: 'auto'
        });

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

        $(".UCSelect[name=entrustOrgIdsChk]").width(320);

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



//    $(document).ready(function(){
//        $('.singleSelect').select2();
//    })

    var add = function(){
        openDialog({
            frame:true,
            title:"新增",
            height:700,
            width:1000,
            url:"${ctx}/survey/case/edit"
        });
    }

    var info = function(id,menuCode,reStateStr){
        var selected = getselected();
        if (selected){
            return;
        }

        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        var title="详情";
        if(menuCode == 'dcy-list'){
            title="";
        }

        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:"${ctx}/survey/case/sic/info?id=" + id + "&menuCode=${menuCode}"+"&reStateStr="+reStateStr+"&display=true",
            load:true,

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

    var info2 = function(id){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        var title="详情";
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:"${ctx}/survey/case/info?id=" + id + "&menuCode=${menuCode}",
            load:true,

        });
    }
</script>
</body>
</html>
