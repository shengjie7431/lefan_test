<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>

    <title>案件列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <%--<link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">--%>
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
    <style>
        .dalogs{
            display: none;
            position: absolute;
            bottom: 30%;
            left: 20%;
            width: 400px;
            height: 200px;
            background-color: #fff;
            box-shadow: 0 0 10px #999;
            color: #000;
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
                <form class="form-inline" role="form" action="${ctx}/survey/case/listCaseVisit?menuCode=${menuCode}&pageSize=${pageSize}" method="post">
                    <%--多选--%>
                        <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <input type="hidden" name="surveyOrgIds" id="surveyOrgIds" />
                    <input type="hidden" name="entrustOrgIds" id="entrustOrgIds" />
                    <input type="hidden" name="visitPersons" id="visitPersons" />
                        <input type="hidden" name="sortField" id="sortField" value="${sortField}">
                        <input type="hidden" name="sortType" id="sortType" value="${sortType}">
                        <div class="form-group">
                            快捷查询:<input style="width: 500px" name="searchStr" type="text" value="${searchStr}" placeholder="可输入被调查人，案件编号，调查编号，联系方式，身份证号" class="form-control">
                        </div>

<%--                    <div class="form-group">--%>
<%--                        案件编号:<input name="surveyCaseNo" type="text" value="${surveyCaseNo}" class="form-control">--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        调查编号:<input name="surveyNo" type="text" value="${surveyNo}" class="form-control">--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                       被调查人:<input name="surveyPerson" type="text" value="${surveyPerson}" class="form-control">--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                       联系方式:<input name="surveryPersonTel" type="text" value="${surveryPersonTel}" class="form-control">--%>
<%--                    </div>--%>
                    <div class="form-group">
                        保险公司:
                        <div>
                            <select class="select form-control select-checkbox" name="entrustOrgIdsChk" data-select-name="entrustOrgIds" data-select-values="${entrustOrgIds}" multiple >
                                <c:forEach items="${consignors}" var="item">
                                    <option value="${item.id}" >${item.company}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <br>
                    <c:if test="${menuCode == 'visit-list'}">
                        <div class="form-group">
                            回访状态:
                            <select onchange="javascript:$('#batchOperateBtn').click();" name="visitState"  class="form-control">
                                <option value="0" <c:if test="${visitState == '0'}">selected="selected" </c:if> >待回访</option>
                                <option value="1" <c:if test="${visitState == '1'}">selected="selected" </c:if> >已回访</option>
                            </select>
                        </div>
                        <div class="form-group">
                            回访异常:
                            <select onchange="javascript:$('#batchOperateBtn').click();" name="isAbnormal"  class="form-control">
                                <option value="" <c:if test="${isAbnormal == ''}">selected="selected" </c:if> >全部</option>
                                <option value="0" <c:if test="${isAbnormal == '0'}">selected="selected" </c:if> >否</option>
                                <option value="1" <c:if test="${isAbnormal == '1'}">selected="selected" </c:if> >是</option>
                            </select>
                        </div>
                        <div class="form-group">
                            回访人员:
                            <div>
                                <select class="select form-control select-checkbox" name="visitPersonsChk" data-select-name="visitPersons" data-select-values="${visitPersons}" multiple >
                                    <c:forEach items="${userInfos}" var="item">
                                        <option value="${item.userId}" >${item.userName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            回访时间：
                            <input name="visitStateTime" type="text" value="${visitStateTime}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                            <span>--</span>
                            <input name="visitEndTime" type="text" value="${visitEndTime}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        </div>
                    </c:if>
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
<%--                <th width="100">调查编号</th>--%>
                <th width="150">保险公司</th>
                <th width="100">被调查人</th>
                <th width="100">联系方式</th>
                <c:if test="${menuCode == 'visit-list'}">
                    <%--<th width="150">调查机构</th>--%>
                    <th width="150">调查机构-调查员</th>
                    <th width="150">
                        <div class="title_sort" data-id="50" data-value="" data-field="reportDate">
                            <span>复审通过时间</span>
                            <div class="icon-sort">
                                <div class="icon-up" ></div>
                                <div class="icon-down" ></div>
                            </div>
                        </div>
                    </th>
                    <th width="80">回访状态</th>
                    <th width="80">回访人员</th>
                    <th width="80">回访异常</th>
                    <th width="150">
                        <div class="title_sort" data-id="50" data-value="" data-field="visitTime">
                            <span>回访时间</span>
                            <div class="icon-sort">
                                <div class="icon-up" ></div>
                                <div class="icon-down" ></div>
                            </div>
                        </div>
                    </th>
                </c:if>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr onclick="info(${item.surveyInfoId},${item.id})">
                    <td>${item.surveyRiskCase.surveyCaseNo}</td>
                    <td>${item.surveyRiskCase.entrustOrgName}</td>
                <%--                    <td>${item.surveyRiskCase.surveyNo}</td>--%>
                    <td>${item.surveyRiskCase.surveyPerson}</td>
                    <td>${item.surveyRiskCase.surveryPersonTel}</td>
                    <c:if test="${menuCode == 'visit-list'}">
                        <%--<td>${item.surveyOrgName}</td>--%>
                        <td>${item.surveyUserNames}</td>
                        <td><fmt:formatDate value="${item.reportDate}" pattern="yyyy-MM-dd"/></td>
                        <td>
                            <c:if test="${item.visitState == 0 || item.visitState == null}">
                                待回访
                            </c:if>
                            <c:if test="${item.visitState == 1}">
                                已回访
                            </c:if>
                        </td>
                        <td>${item.visitPerson}</td>
                        <td>
                            <c:if test="${item.isAbnormal == 0}">
                                否
                            </c:if>
                            <c:if test="${item.isAbnormal == 1}">
                                是
                            </c:if>
                        </td>
                        <td><fmt:formatDate value="${item.visitTime}" pattern="yyyy-MM-dd"/></td>
                    </c:if>
                    <td>
                        <a href="javascript:void(0);" onclick="info(${item.surveyInfoId},${item.id})">处理</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
<div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/survey/case/listCaseVisit?menuCode=${menuCode}&searchStr=${searchStr}&surveyPerson=${surveyPerson}&entrustOrgName=${entrustOrgName}&surveryPersonTel=${surveryPersonTel}&surveyNo=${surveyNo}&visitStateTime=${visitStateTime}&visitEndTime=${visitEndTime}&surveyCaseNo=${surveyCaseNo}&entrustOrgIds=${entrustOrgIds}&surveyOrgIds=${surveyOrgIds}&visitPersons=${visitPersons}&isAbnormal=${isAbnormal}&visitState=${visitState}&sortField=${sortField}&sortType=${sortType}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<%--<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>--%>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect.js?V=1"></script>
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
        var sortType = $("#sortType").val();
        var sortField= $("#sortField").val();
        if (sortType!=null && sortType !=''){
            $("div[data-field="+sortField+"]").find(sortType=='up'?'.icon-up':'.icon-down').addClass("active");
        }

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
        $(".UCSelect[name=visitPersonsChk]").width(320);

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

    var info = function(id,surveyAssorgCaseId){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"",
            height:height,
            width:width,
            url:"${ctx}/survey/case/info?id=" + id + "&menuCode=${menuCode}"+"&surveyAssorgCaseId="+surveyAssorgCaseId,
            load:true
        });
    }


    function returnCallback(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){
            alert("成功！");
        }else{
            alert(apiRsp.msg);return;
        }
        reload();
    }

</script>
</body>
</html>
