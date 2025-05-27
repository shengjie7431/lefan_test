<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
    <style>
        .table tbody.class-list tr td{
            vertical-align: middle;
            border-right: 1px solid rgb(221, 221, 221);
        }
        .table tbody.class-list tr td span {
            padding: 5px 0;
            display: inline-block;
        }
        .table tbody.class-list tr td:last-of-type{
            border-right: none;
        }
        .table  tr th{
            border-right: 1px solid rgb(221, 221, 221);
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
            position: fixed;
            bottom: 0;
            width: 100%;
            background-color: #fff;
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

        .bred {
            background-color: #eca6a6;
        }

    </style>
</head>

<body>
<%--时效跟踪--%>
<div class="main administrator">
    <div class="main-top">
        <h3>案件列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/survey/case/listAssign?menuCode=${menuCode}&pageSize=${pageSize}" method="post">
                    <%--多选--%>
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
                    <input type="hidden" name="surveyOrgIds" id="surveyOrgIds" />
                    <input type="hidden" name="entrustOrgIds" id="entrustOrgIds" />
                    <input type="hidden" name="surveyStates" id="surveyStates" />
                    <input type="hidden" name="orgSurveyStates" id="orgSurveyStates" />
                    <input type="hidden" name="serviceTypes" id="serviceTypes" />
                    <input type="hidden" name="oprType" id="oprType" value="${oprType}" />
                        <input type="hidden" id="order" value="${order}"  name="order" />
                        <input type="hidden" id="colSortType" value="${colSortType}" name="colSortType" />
                        <input type="hidden" id="handquery" value="handquery" name="handquery" />
                    <%--<div class="form-group">
                        案件编号:<input name="surveyCaseNo" type="text" value="${surveyCaseNo}" class="form-control">
                    </div>
                    <div class="form-group">
                        调查编号:<input name="surveyNo" type="text" value="${surveyNo}" class="form-control">
                    </div>
                    <div class="form-group">
                        被调查人:<input name="surveyPerson" type="text" value="${surveyPerson}" style="width: 150px" class="form-control">
                    </div>
                    <div class="form-group">
                        联系方式:<input name="surveryPersonTel" type="text" value="${surveryPersonTel}" style="width: 150px" class="form-control">
                    </div>--%>
                    <div class="form-group">
                        快捷查询:<input style="width: 350px" name="searchStr" type="text" value="${searchStr}" placeholder="可输入被调查人，案件编号，调查编号，联系方式，身份证号" class="form-control">
                    </div>
                    <div class="form-group">
                        <c:if test="${oprType == 'help'}">
                            互助平台:
                        </c:if>
                        <c:if test="${oprType != 'help'}">
                            保险公司:
                        </c:if>
                        <div>
                            <select class="select form-control select-checkbox" name="entrustOrgIdsChk" data-select-name="entrustOrgIds" data-select-values="${entrustOrgIds}" multiple >
                                <c:forEach items="${consignors}" var="item">
                                    <c:if test="${oprType == 'help'}">
                                        <option value="${item.id}">${item.company}</option>
                                    </c:if>
                                    <c:if test="${oprType != 'help'}">
                                        <option value="${item.id}" data-name="调查中,初审中" data-number="${item.surveyNum},${item.checkNum}" >${item.company}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        调查方机构:
                        <div>
                            <select class="select form-control select-checkbox" name="surveyOrgIdsChk" data-select-name="surveyOrgIds" data-select-values="${surveyOrgIds}" multiple >
                                <c:forEach items="${franchisees}" var="item">
                                    <c:if test="${oprType == 'help'}">
                                        <option value="${item.id}">${item.name}</option>
                                    </c:if>
                                  <c:if test="${oprType != 'help'}">
                                      <option value="${item.id}" data-name="调查中,初审中" data-number="${item.surveyNum},${item.checkNum}">${item.name}</option>
                                  </c:if>

                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        案件阶段:
                        <select  name="surveyStateChk"  class="select form-control select-checkbox" data-select-name="surveyStates"  data-select-values="${surveyStates}" multiple >
                            <option value="12">调查中</option>
                            <option value="22">平台复审中</option>
                            <option value="24">保司终审中</option>
                            <option value="28">保司终审通过</option>
                            <option value="34">已结案</option>
                        </select>
                    </div>
                    <div class="form-group">
                        机构案件阶段:
                        <c:if test="${oprType == 'help'}">
                            <select  name="orgSurveyStateChk"  class="select form-control select-checkbox" data-select-name="orgSurveyStates"  data-select-values="${orgSurveyStates}" multiple >
                                <option value="1">调查中</option>
                                <option value="2">初审中</option>
                                <option value="7">复审中</option>
                                <option value="8">复审通过</option>
                                <option value="9">驳回调查中</option>
                            </select>
                        </c:if>
                        <c:if test="${oprType != 'help'}">
                            <select name="orgSurveyStateChk"  class="select form-control select-checkbox" data-select-name="orgSurveyStates"  data-select-values="${orgSurveyStates}" multiple >
                                <option value="1">调查中</option>
                                <option value="2">初审中</option>
                                <option value="4">初审通过</option>
                            </select>
                        </c:if>
                    </div>
                        <div class="form-group">
                            业务类型：
                            <select  name="serviceTypeChk" class="select form-control select-checkbox" data-select-name="serviceTypes"  data-select-values="${serviceTypes}" multiple>
                                <option value="12">单点调查</option>
                                <option value="13">深度调查</option>
                                <option value="11">契约调查</option>
                            </select>
                        </div>
                    <div class="form-group">
                        <select class="form-control" name="dateItem" >
                            <option value="1" <c:if test="${dateItem == 1}">selected</c:if> >委托时间</option>
                            <option value="2" <c:if test="${dateItem == 2}">selected</c:if> >机构提交时间</option>
                            <option value="3" <c:if test="${dateItem == 3}">selected</c:if> >机构截止时间</option>
                            <option value="5" <c:if test="${dateItem == 5}">selected</c:if> >保司终审时间</option>
                            <c:if test="${oprType == 'help'}">
                                <option value="4" <c:if test="${dateItem == 4}">selected</c:if> >复审通过时间</option>
                            </c:if>
                        </select>
                        <input name="startDate" type="text" value="${startDate}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        <span>--</span>
                        <input name="endDate" type="text" value="${endDate}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                    </div>
                    <div class="form-group">
                        时效状态：
                        <select class="form-control" name="agingType">
                            <option value="0" <c:if test="${agingType == 0}">selected</c:if>>全部</option>
                            <option value="1" <c:if test="${agingType == 1}">selected</c:if> >时效内</option>
                            <option value="2" <c:if test="${agingType == 2}">selected</c:if> >即将超期</option>
                            <option value="3" <c:if test="${agingType == 3}">selected</c:if> >已超期</option>
                            <option value="4" <c:if test="${agingType == 4}">selected</c:if> >超期5天之内</option>
                            <option value="5" <c:if test="${agingType == 5}">selected</c:if> >超期5天至10天</option>
                            <option value="6" <c:if test="${agingType == 6}">selected</c:if> >超期10天至20天</option>
                            <option value="7" <c:if test="${agingType == 7}">selected</c:if> >超期20天以上</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button  type="button" class="btn btn-default"><a href="${ctx}/survey/case/listAssignExport?menuCode=${menuCode}&entrustOrgIds=${entrustOrgIds}&surveyOrgIds=${surveyOrgIds}&operateState=${operateState}&orgSurveyState=${orgSurveyState}&surveyState=${surveyState}&startDate=${startDate}&endDate=${endDate}&oprType=${oprType}&searchStr=${searchStr}&surveyStates=${surveyStates}&orgSurveyStates=${orgSurveyStates}&agingType=${agingType}&dateItem=${dateItem}&handquery=${handquery}">导出</a> </button>
                    </div>
                </form>
            </div>
        </div>

        <div class="table-content">
            <table class="table table-hover" style="cursor: pointer;">
                <thead>
                <tr>
                    <th width="100">案件编号</th>
                    <th width="100">保险公司</th>
                    <th width="80">被调查人</th>
                    <c:if test="${oprType != 'safe'}">
                        <th width="100">联系方式</th>
                    </c:if>
                    <th width="80">案件阶段</th>
                    <th width="80">业务类型</th>
                    <th width="100"  >
                        <div class="title_sort" data-id="20" data-value="">
                            <span>委托时间</span>
                            <div class="icon-sort">
                                <div class="icon-up"></div>
                                <div class="icon-down"></div>
                            </div>
                        </div>
                    </th>
                    <th width="150">
                        <div class="title_sort" data-id="19" data-value="">
                            <span>调查机构</span>
                            <div class="icon-sort">
                                <div class="icon-up"></div>
                                <div class="icon-down"></div>
                            </div>
                        </div>
                    </th>
                    <th width="100">机构案件状态</th>
                    <th width="100" >
                        <div class="title_sort" data-id="21" data-value="">
                            <span>分派机构时间</span>
                            <div class="icon-sort">
                                <div class="icon-up"></div>
                                <div class="icon-down"></div>
                            </div>
                        </div>
                    </th>
                    <th width="100" >
                        <div class="title_sort" data-id="22" data-value="">
                            <span>机构提交时间</span>
                            <div class="icon-sort">
                                <div class="icon-up"></div>
                                <div class="icon-down"></div>
                            </div>
                        </div>
                    </th>
                    <th width="100" >
                        <div class="title_sort" data-id="23" data-value="">
                            <span>机构截止时间</span>
                            <div class="icon-sort">
                                <div class="icon-up"></div>
                                <div class="icon-down"></div>
                            </div>
                        </div>
                    </th>
                    <th width="80">机构时效</th>
                    <c:if test="${oprType == 'help'}">
                        <th width="100" >
                            <div class="title_sort" data-id="24" data-value="">
                                <span>复审通过时间</span>
                                <div class="icon-sort">
                                    <div class="icon-up"></div>
                                    <div class="icon-down"></div>
                                </div>
                            </div>
                        </th>
                    </c:if>
                    <th width="100" >
                        <div class="title_sort" data-id="25" data-value="">
                            <span>案件截止时间</span>
                            <div class="icon-sort">
                                <div class="icon-up"></div>
                                <div class="icon-down"></div>
                            </div>
                        </div>
                    </th>
                    <th width="100" >
                        <div class="title_sort" data-id="30" data-value="">
                            <span>保司终审时间</span>
                            <div class="icon-sort">
                                <div class="icon-up"></div>
                                <div class="icon-down"></div>
                            </div>
                        </div>
                    </th>
                    <th width="80">案件时效</th>
                    <c:if test="${oprType == 'safe'}">
                        <th width="100">跟踪信息</th>
                    </c:if>
                    <th width="50">操作</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${apiRsp.results}" var="item">
                    <c:if test="${oprType == 'safe'}">
                        <c:if test="${item.lastFollowContent != ' '}">
                            <tr onclick="info(${item.surveyRiskCaseInfoDto.id})">
                        </c:if>
                        <c:if test="${item.lastFollowContent == ' '}">
                            <tr class="bred" onclick="info(${item.surveyRiskCaseInfoDto.id})">
                        </c:if>
                    </c:if>
                    <c:if test="${oprType != 'safe'}">
                        <tr onclick="info(${item.surveyRiskCaseInfoDto.id})">
                    </c:if>

                    <td><a href="javascript:void(0);" onclick="info(${item.surveyRiskCaseInfoDto.id})">${item.surveyRiskCase.surveyCaseNo}</a></td>
                    <td>${item.surveyRiskCase.entrustOrgName}</td>
                    <td>${item.surveyRiskCase.surveyPerson}</td>
                    <c:if test="${oprType != 'safe'}">
                        <td>${item.surveyRiskCase.surveryPersonTel}</td>
                    </c:if>
                    <td>
                        <c:if test="${item.surveyRiskCaseInfoDto.supplementState == 1}">
                            <span style="color: red;">信息补充中</span>
                        </c:if>
                        <c:if test="${item.surveyRiskCaseInfoDto.supplementState != 1}">
                            ${item.surveyRiskCaseInfoDto.surveyStateName}
                        </c:if>
                    </td>
                    <td>${item.servicesName}</td>
                    <td><fmt:formatDate value="${item.surveyRiskCase.entrustTime}" pattern="yyyy-MM-dd"/></td>

                    <td>${item.surveyOrgName} <c:if test="${item.orgPrimaryType == 1}"></c:if></td>

                    <td>
                        <c:if test="${item.orgSurveyState == 0}">待接收</c:if>
                        <c:if test="${item.orgSurveyState == 1}">调查中</c:if>
                        <c:if test="${item.orgSurveyState == 2}">初审中</c:if>
                        <c:if test="${item.orgSurveyState == 3}">已拒绝</c:if>
                        <c:if test="${item.orgSurveyState == 4}">
                            <c:if test="${oprType == 'help'}">
                                <c:if test="${item.reviewTime == null}">
                                    复审中
                                </c:if>
                                <c:if test="${item.reviewTime != null}">
                                    复审通过
                                </c:if>
                            </c:if>
                            <c:if test="${oprType != 'help'}">
                                初审通过
                            </c:if>
                        </c:if>
                        <c:if test="${item.orgSurveyState == 5}">调查中</c:if>
                        <c:if test="${item.orgSurveyState == 6}">调查中</c:if>
                    </td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${item.reportDate}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${item.orgEndTime}" pattern="yyyy-MM-dd"/></td>
                    <td><span style="color: ${item.efficiencyStateColor}">${item.efficiencyState}</span></td>
                    <c:if test="${oprType == 'help'}">
                        <td><fmt:formatDate value="${item.reviewTime}" pattern="yyyy-MM-dd"/></td>
                    </c:if>
                    <td><fmt:formatDate value="${item.surveyRiskCaseInfoDto.endTime}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${item.surveyRiskCaseInfoDto.entrustReportEndDate}" pattern="yyyy-MM-dd"/></td>
                    <td style="color: ${item.surveyRiskCaseInfoDto.efficiencyStateColor}">${item.surveyRiskCaseInfoDto.efficiencyState}</td>
                    <c:if test="${oprType == 'safe'}">
                        <td style="color: red">${item.lastFollowContent}</td>
                    </c:if>
                    <td>
                        <a href="javascript:void(0);" onclick="info(${item.surveyRiskCaseInfoDto.id})">处理</a>
                        <c:if test="${oprType == 'safe'}">
                            <a href="javascript:void(0);" onclick="operate(${item.surveyInfoId},${item.surveyOrgId},'newfollow')">添加跟踪</a>
                        </c:if>
                    </td>
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
            <jsp:param name="requestUrl" value="${ctx}/survey/case/listAssign?menuCode=${menuCode}&oprType=${oprType}&order=${order}&colSortType=${colSortType}&searchStr=${searchStr}&entrustOrgIds=${entrustOrgIds}&surveyOrgIds=${surveyOrgIds}&orgSurveyStates=${orgSurveyStates}&serviceTypes=${serviceTypes}&dateItem=${dateItem}&startDate=${startDate}&endDate=${endDate}&agingType=${agingType}&order=${order}&colSortType=${colSortType}&surveyStates=${surveyStates}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div>
    <!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect2.js?V=1"></script>
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

    function sort(col){
        $("#order").val(col);
        var colSortType = $("#colSortType").val();
        if (colSortType == '1'){
            $("#colSortType").val("2");
        }else{
            $("#colSortType").val("1");
        }
        $('#batchOperateBtn').click();
    }

    $(document).ready(function () {
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
        $(".UCSelect[name=entrustOrgIdsChk]").width(500);
        $(".UCSelect[name=surveyOrgIdsChk]").width(460);

        $('.btn-1').off("click");
        $('.btn-1').click(function(e){
            $(".UCSelect").find('.SelectVal').removeClass('over');
            $(".UCSelect").find("select").UCFormSelect('close');
            // $("#batchOperateBtn").click();
        });

        $(document).off('mousedown');
        $(document).bind('mousedown', function (e) {
            var Event = e.target;
            $('.UCSelect .SelectBox').each(function () {
                var Select = $(this).parents(".UCSelect").find("select").get(0);
                var Events = $(Event).parents(".UCSelect").find("select").get(0);
                if (!(Event && Select && Select == Events)) {
                    if( $(this).parents(".UCSelect").find('.SelectVal').hasClass("over")){
                        // $("#batchOperateBtn").click();
                    }
                    $(this).parents(".UCSelect").find('.SelectVal').removeClass('over');
                    $(this).parents(".UCSelect").find("select").UCFormSelect('close');
                }
            });
        });

        var initId = $("#order").val(),sort = $("#colSortType").val() == 2 ? 'desc' : 'asc';

        $(".title_sort[data-id="+ initId+"]").attr('data-value',sort)
        if (sort == 'asc'){
            $(".title_sort[data-id="+ initId+"]").find('.icon-up').addClass('active')
        }  else if (sort == 'desc'){
            $(".title_sort[data-id="+ initId+"]").find('.icon-down').addClass('active')
        }

        $('.title_sort').click(function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            $('.title_sort').each(function (i,cur) {
                var _cur =  $(cur)
                if (_cur.attr('data-id')!= id){
                    _cur.attr('data-value','')
                    _cur.find('.icon-sort div').removeClass('active')
                }
            })
            if (_this.attr('data-value') == 'asc'){
                _this.find('.icon-up').removeClass('active')
                _this.find('.icon-down').addClass('active')
                _this.attr('data-value','desc')
            } else if (_this.attr('data-value') == 'desc'){
                _this.find('.icon-up').addClass('active')
                _this.find('.icon-down').removeClass('active')
                _this.attr('data-value','asc')
            }else  if (!_this.attr('data-value')){
                _this.find('.icon-up').addClass('active')
                _this.find('.icon-down').removeClass('active')
                _this.attr('data-value','asc')
            }
            var d_v = _this.attr('data-value')
            console.log(id, d_v)
            $("#order").val(id);
            $("#colSortType").val(d_v == 'desc' ? 2 : 1);
            $('#batchOperateBtn').click();

        })
    })

    var info = function(id){
        var selected = getselected();
        if (selected){
            return;
        }
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"详情",
            height:height,
            width:width,
            url:"${ctx}/survey/case/info?id=" + id + "&menuCode=${menuCode}"+"&oprType="+$("#oprType").val()+"&display=true",
            load:true
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



    function operate(id,surveyOrgId,btnCode){
        var height = 400, width = 900;
        openDialog({
            frame: true,
            height: height,
            width: width,
            title:"添加跟踪",
            url: "${ctx}/survey/case/addFollow?id=" + id + "&btnCode=" + btnCode + "&surveyOrgId="+surveyOrgId
        });
    }

</script>
</body>
</html>
