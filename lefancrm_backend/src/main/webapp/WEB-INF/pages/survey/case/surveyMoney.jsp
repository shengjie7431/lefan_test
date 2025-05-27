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
        .td-span-money{
            width: 100px;
            display: inline-block;
        }
        body{
            width: 100%;
            height: 100%;
        }
        .dalog-bg{
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4);
            position: absolute;
            top: 0;
            left: 0;
            z-index: 999;
        }
        .dalog {
            display: none;
            margin: 0 auto;
            /*margin-top: 20%;*/
            width: 508px;
            height: 418px;
            line-height: 20px;
            text-align: center;
            border: 1px solid rgba(187, 187, 187, 1);
            font-size: 14px;
            z-index: 1001;
            background-color: #fff;
        }

        .dalog .d-title {
            width: 100%;
            height: 20px;
            line-height: 20px;
            padding: 30px 0;
            color: rgba(16, 16, 16, 1);
            font-size: 16px;
            text-align: center;
        }

        .dalog .d-cell {
            width: 90%;
            margin: 0 auto;
            padding: 10px;
            display: flex;
        }

        .dalog .d-cell .d-cell__label {
            display: inline-block;
            width: 150px;
            height: 30px;
            line-height: 30px;
            font-size: 14px;
            text-align: right;
        }

        .dalog .d-cell .d-cell__input {
            position: relative;
            display: inline-block;
            padding-left: 10px;
            width: 230px;
            height: 30px;
            line-height: 30px;
            font-size: 14px;
            display: flex;
        }
        .dalog .d-cell .d-cell__input--disabled input{
            background-color: rgba(179,179,179,0.25);
        }

        .dalog .d-cell .d-cell__input input {
            display: inline-block;
            width: 190px;
            /*padding: 0 10px;*/
            /*padding-right: 40px;*/
            height: 28px;
            line-height: 28px;
            border: 1px solid #bbb;
            font-size: 14px;
            text-align: right;
        }

        .dalog .d-cell .d-cell__input span {
            position: absolute;
            right: 10px;
            display: inline-block;
            width: 16px;
            height: 30px;
            line-height: 30px;
            font-size: 14px;
            text-align: center;
        }

        .dalog .d-cell .d-cell__textarea {
            display: inline-block;
            padding-left: 10px;
            width: 190px;
            height: 104px;
            font-size: 14px;
        }

        .dalog .d-cell .d-cell__textarea textarea {
            padding: 10px;
            width: 190px;
            height: 82px;
            line-height: 24px;
            border: 1px solid #bbb;
            font-size: 14px;
        }

        .dalog .d-btns {
            width: 320px;
            margin: 0 auto;
            padding-top: 30px;
            display: flex;
            justify-content: space-between;
        }

        .dalog .d-btns .d-btn {
            width: 130px;
            height: 34px;
            line-height: 34px;
            border: 1px solid #bbb;
            background-color: #fff;
            cursor: pointer;
        }

        .dalog .d-btns .d-btn:hover {
            box-shadow: 0px 0px 10px #bbb;
        }

        .dalog .d-btns .d-btn__active {
            background-color: #3ba9ff;
            color: #fff;
        }

        .dalog__1 {
            /* display: block; */
        }

        .dalog__2 {
            /* display: block; */
            height: 285px;
        }

        .dalog__2 .d-title {
            padding: 50px 0;
        }

        .dalog__3 {
            position: absolute;
            top: 20%;
            left: 20%;
            height: 400px;
            margin: 0;
        }
        .dalog__3 .d-title {
            font-size: 20px;
            color: #3ba9ff;
        }

        .dalog__3 .d-content {
            width: 90%;
            margin: 0 auto;
        }

        .dalog__3 .d-content .d-content__text,
        .dalog__3 .d-info .d-info__text {
            width: 100%;
            line-height: 20px;
            padding: 3px 0;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .dalog .text__line2 {
            height: 40px;
            white-space: normal !important;
            overflow: hidden !important;
            display: -webkit-box !important;
            -webkit-box-orient: vertical !important;
            -webkit-line-clamp: 2 !important;
        }

        .dalog__3 .d-info {
            width: 90%;
            margin: 0 auto;
            padding-top: 20px;
        }
        .dalog__3 .d-btns{
            width: 400px;
            padding-top: 20px;
        }
        .dalog__3 .d-btns .d-btn{
            width: 174px;
            height: 50px;
            line-height: 50px;
        }
        .dalog .d-a_active{
            color: #3ba9ff;
            cursor: pointer;
        }

        .upd-span{
            color: red;
        }

        .span__item{
            float: left;
            padding: 0 10px;
            display: inline-block;
            height: 32px;
            line-height: 32px;
            background-color: #fff;
            color: #333;
            cursor: pointer;
        }
        .span__item:hover{
            /*background-color: rgb(59, 169, 255);*/
            /*color: #fff;*/
        }
        .__active{
            background-color: rgb(59, 169, 255);
            color: #fff;
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
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>调查费用清单 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="pin">
                <form id="searchForm" class="form-inline" role="form" action="${ctx}/survey/case/surveyMoney?searchType=${searchType}" method="post">
                    <%--多选--%>
                    <input type="hidden" name="surveyOrgIds" id="surveyOrgIds" />
                    <input type="hidden" name="sourceSupportTypes" id="sourceSupportTypes" />
                        <input type="hidden" name="sortField" id="sortField" value="${sortField}">
                        <input type="hidden" name="sortType" id="sortType" value="${sortType}">
                    <div class="form-group">
                        调查机构:
                        <div>
                            <select class="select form-control select-checkbox" name="surveyOrgIdsChk" data-select-name="surveyOrgIds" data-select-values="${surveyOrgIds}" multiple >
                                <c:forEach items="${franchisee}" var="item">
                                    <option value="${item.id}" >${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        案源机构:
<%--                        <select onchange="javascript:$('#batchOperateBtn').click();" class="form-control" name="sourceSupportType">--%>
<%--                            <option value="1" <c:if test="${sourceSupportType == 1}">selected</c:if>>市场营销一部</option>--%>
<%--                            <option value="2" <c:if test="${sourceSupportType == 2}">selected</c:if>>市场营销二部</option>--%>
<%--                            <option value="3" <c:if test="${sourceSupportType == 3}">selected</c:if>>互助</option>--%>
<%--                            <option value="4" <c:if test="${sourceSupportType == 4}">selected</c:if>>正言</option>--%>
<%--                        </select>--%>
                        <div>
                            <select class="select form-control select-checkbox" name="sourceSupportTypesChk" data-select-name="sourceSupportTypes"  data-select-values="${sourceSupportTypes}" multiple >
                                <option value="1">市场一部（郑哲）</option>
                                <option value="2">市场二部（曹刘强）</option>
                                <option value="3">互助</option>
                                <option value="4">正言金融（郑哲）</option>
                                <option value="5">市场三部（韩正栋）</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-control" style="padding: 0px;">
                        <span class="span__item" data-value="1">保司审核时间</span>
<%--                        <span class="span__item" data-value="2">平台复审时间</span>--%>

                    </div>
                    <div class="form-group">
                        时间：
                        <input name="startDate" type="text" value="${startDate}" style="width: 150px" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM',onpicked:search})" readonly>
                        <span>--</span>
                        <input name="endDate" type="text" value="${endDate}" style="width: 150px" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM',onpicked:search})" readonly>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" style="display: none;" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
<%--                        &nbsp; &nbsp;<button class="btn btn-default"><a href="${ctx}/survey/case/exportSurveyMoney?searchType=${searchType}&orgId=${orgId}&startDate=${startDate}&endDate=${endDate}&export=1&surveyOrgIds=${surveyOrgIds}">导出</a></button>--%>
                             <span id="span_btn_apps" class="btn btn-default" onclick="app(this)">批量申请付款</span>
                             <span class="btn btn-default" onclick="appList()">已申请付款清单</span>&nbsp;&nbsp;&nbsp;&nbsp;
<%--                             <span class="span__item __active" onclick="exportDetail()">导出机构明细</span>--%>

                        <button class="btn btn-default"><a href="${ctx}/survey/case/exportSurveyMoneyDetail?searchType=survey&surveyOrgIds=${surveyOrgIds}&startDate=${startDate}&endDate=${endDate}&detailType=1&export=1&sourceSupportTypes=${sourceSupportTypes}&dateType=1">导出</a></button>

                        <input type="hidden" value="${sourceSupportTypes}" id="dataSourceSupportTypes" />
                        <input type="hidden" name="dateType" id="dateType" value="${dateType}" />
                        <input type="hidden" value="${dateType}" id="dataDateType" />
                        <input type="hidden" value="${startDate}" id="dataStartDate" />
                        <input type="hidden" value="${endDate}" id="dataEndDate" />
                        <input type="hidden" value="${surveyOrgIds}" id="surveyOrgIds" />
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th class="th-checkbox">
                    <input type="checkbox" id="check-btn" class="tag" title="" data-original-title="全选/反选">
                </th>
                <th width="100">调查机构名称</th>
                <th width="100">
                    <div class="title_sort" data-id="50" data-value="" data-field="noPayMoney">
                        <span>未申请结算调查费<br/>(${surveyMoneyTotal})</span>
                        <div class="icon-sort">
                            <div class="icon-up" onclick="sortFun('up',this)"></div>
                            <div class="icon-down" onclick="sortFun('down',this)"></div>
                        </div>
                    </div>
                </th>
                <th width="100">未结算相关月份工资成本<br/>(${waitMoneyTotal})</th>
                <th width="100">利润<br/>(${fitMoneyTotal})</th>
                <th width="100">历史欠费<br/>(${hisMoneyTotal})</th>
                <th width="100">实际申请付款金额<br/>(${appMoneyTotal})</th>
                <th width="100">是否存在未确认到账</th>
                <%--<th width="50">操作</th>--%>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>
                        <c:if test="${item.payStateOk == 0}">
                            <input type="checkbox" <c:if test="${item.payStateOk == 1}">disabled</c:if> name="orgs-checkbox" data-pay-state="${item.payStateOk}" value="${item.orgId}">
                        </c:if>
                    </td>
                    <td width="100">${item.orgName}</td>
                    <td width="100">
                        <a href="javascript:void(0);" onclick="detail(${item.orgId})">
                            <span id="td_money1_${item.orgId}">${item.surveyMoney}</span>
                        </a>
                    </td>
                    <td><span id="td_wage_money_${item.orgId}">${item.wageMoney}</span></td>
                    <td><span id="td_fit_money_${item.orgId}">${item.fitMoney}</span></td>
                    <td>${item.hisOweMoney}</td>
                    <td width="100">
                            <span class="td-span-money" id="td_money_${item.orgId}">${item.appMoney}</span>
                            <span style="display: none" id="td_remark_${item.orgId}"></span>
                            <c:if test="${item.payStateOk == 0}">
                                <a onclick="updPayMoney(${item.orgId})"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a>
                            </c:if>
                    </td>
                    <td>
                        <c:if test="${item.payStateOk == 0}">否</c:if>
                        <c:if test="${item.payStateOk == 1}">是</c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="dalog dalog__1">
        <div class="d-title">金额调整</div>
        <div class="d-cell">
            <div class="d-cell__label">调查金额</div>
            <div class="d-cell__input d-cell__input--disabled">
                <input id="updPayMoney1" type="number" value="36000" disabled>
                <span>元</span>
            </div>
        </div>
        <div class="d-cell">
            <div class="d-cell__label">实际申请付款金额</div>
            <div class="d-cell__input">
                <input id="updPayMoney2" type="number" value="36000">
                <span>元</span>
            </div>
        </div>
        <div class="d-cell">
            <div class="d-cell__label">备注</div>
            <div class="d-cell__textarea">
                <textarea  name="" id="updRemark" cols="30" rows="10"></textarea>
            </div>
        </div>
        <div class="d-btns">
            <div class="d-btn" data-id="1">取消</div>
            <div class="d-btn d-btn__active" data-id="2">确认</div>
        </div>
    </div>
    <div class="dalog dalog__2">
        <div class="d-title">确认批量申请付款？</div>
        <div class="d-btns">
            <div class="d-btn" data-id="1">取消</div>
            <div class="d-btn d-btn__active" data-id="2">确认</div>
        </div>
    </div>



    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/survey/case/surveyMoney?searchType=${searchType}&dateType=${dateType}&startDate=${startDate}&endDate=${endDate}&surveyOrgIds=${surveyOrgIds}&sourceSupportTypes=${sourceSupportTypes}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect.js?v=2"></script>
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
    function sortFun(sortType,_this){
        $(".active").removeClass("active");
        $(_this).addClass("active");
        $("#sortField").val($(_this).parent().parent().attr("data-field"));
        $("#sortType").val(sortType);
        $('#batchOperateBtn').click();
    }
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
        $(".UCSelect[name=surveyOrgIdsChk]").width(320);

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
        $('.span__item').click(function () {
            var _this = $(this);
            _this.addClass('__active').siblings().removeClass('__active')
            $("#dateType").val(_this.attr("data-value"));
            $("#batchOperateBtn").click();
        });
        var dateType = $("#dateType").val();
        if(dateType == 1){
            $('.span__item').eq(0).addClass("__active");
        }else{
            $('.span__item').eq(1).addClass("__active");
        }

//        $('.singleSelect').select2();
        $("#check-btn").on('change',function(){
            var _this = this;
            $("input[name='orgs-checkbox']").each(function(){
                var payState = $(this).attr("data-pay-state");
                if (payState == 1){
                    $(this).parents('tr').attr("data-tag","");
                    $(this).prop("checked",false);
                    // $(this).removeProp("checked");
                }else{
                    if(_this.checked){
                        $(this).parents('tr').attr("data-tag","selected");
                    }else{
                        $(this).parents('tr').attr("data-tag","");
                    }
                    $(this).prop("checked",_this.checked);
                }
            })
        });

        var b_width = $('body').width(), b_height = document.documentElement.clientHeight
        var d_width = $('.dalog').width(), d_height = $('.dalog').height()
        var cssList = {'position':'fixed'}
        if (b_width - d_width > 0){
            cssList.left=( b_width - d_width ) / 2
        }
        if (b_height - d_height > 0){
            cssList.top = (b_height - d_height) / 2
        }
        $('.dalog').css(cssList)
        $('.dalog__1 .d-btn').click(function(){
            if ($(this).attr('data-id') == 1){
                $('.dalog__1').hide()
            }
            if ($(this).attr('data-id') == 2){
                // var updPayMoney1 = $("#updPayMoney1").val();
                var updPayMoney2 = $("#updPayMoney2").val();
                var updRemark = $("#updRemark").val();
                var orgId = $(this).attr("data-org-id");
                console.log("---------------",orgId,updPayMoney2,updRemark);
                $("#td_money_" + orgId).text(updPayMoney2);
                $("#td_money_" + orgId).addClass("upd-span");
                $("#td_remark_" + orgId).text(updRemark);
                $('.dalog__1').hide()
            }
        })
        $('.dalog__2 .d-btn').click(function(){
            if ($(this).attr('data-id') == 1){
                $('.dalog__2').hide()
                $("#span_btn_apps").removeAttr("disabled")
            }
            if ($(this).attr('data-id') == 2){
                $(this).css("pointer-events","none");
                //获取已选中的行
                var pays = [];
                $("input:checkbox[name='orgs-checkbox']:checked").each(function() {
                    var orgId = $(this).val();
                    var appMoney = $("#td_money_" + orgId).text();
                    var surveyMoney = $("#td_money1_" + orgId).text();
                    var wageMoney = $("#td_wage_money_" + orgId).text();
                    var remark = $("#td_remark_" + orgId).text();
                    var item = {
                        "orgId" : orgId,
                        "appMoney" : appMoney,
                        "surveyMoney" : surveyMoney,
                        "wageMoney" : wageMoney,
                        "remark" : remark
                    };
                    pays.push(item)
                });
                //发送ajax请求
                var sourceSupportTypes = $("#dataSourceSupportTypes").val();
                var dateType = $("#dataDateType").val();
                var startDate = $("#dataStartDate").val();
                var endDate = $("#dataEndDate").val();
                var url = "${ctx}/survey/pay/operate",param = {
                    "btnCode" : "apps",
                    "sourceSupportTypes" : sourceSupportTypes,
                    "dateType" : dateType,
                    "startDate" : startDate,
                    "endDate" : endDate,
                    "pays" : JSON.stringify(pays)
                };
                $.ajax({
                    url:url,
                    data:param,
                    success:function(res,param){
                        location.reload();
                    }
                });
            }
        })


        $(".table tr").slice(1).click(function(){
            var chks = $("input[type='checkbox']",this);
            var tag = $(this).attr("data-tag");
            if(tag=="selected"){            // 之前已选中，设置为未选中
                $(this).attr("data-tag","");
                chks.prop("checked",false);
            }else{            // 之前未选中，设置为选中
                $(this).attr("data-tag","selected");
                chks.prop("checked",true);
            }
        });
    })

    var search = function(){
        $('#batchOperateBtn').click()
    }

    var appList = function(){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"详情",
            height:height,
            width:width,
            url:"${ctx}/survey/pay/list?menuCode=appList"
            // load:true
        });
    }
    var detail = function(orgId){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        var sourceSupportTypes = $("#dataSourceSupportTypes").val();
        var dateType = $("#dataDateType").val();
        var startDate = $("#dataStartDate").val();
        var endDate = $("#dataEndDate").val();
        var url = "${ctx}/survey/case/surveyMoneyDetail?searchType=${searchType}&detailType=surveyMoneyDetail&orgId=" + orgId + "&startDate=" + startDate + "&endDate=" + endDate +"&dateType=" + dateType + "&sourceSupportTypes=" + sourceSupportTypes;
        openDialog({
            frame:true,
            title:"详情",
            height:height,
            width:width,
            url:url,
            load:true
        });
    }

    var app = function(obj){
        var have = false;
        $("input:checkbox[name='orgs-checkbox']:checked").each(function() {
            have = true;
        });
        if (!have){
            alert("未选中任何数据！");
            return;
        }
        $('.dalog__2').show()
        $("#span_btn_apps").attr("disabled","true");
    }

    var updPayMoney = function(orgId){
        var oldPayMoney = $("#td_money1_" + orgId).text();
        var curPayMoney = $("#td_money_" + orgId).text();
        var curRemark = $("#td_remark_" + orgId).text();
        $("#updPayMoney1").val(oldPayMoney);
        $("#updPayMoney2").val(curPayMoney);
        $("#updRemark").val(curRemark);
        $(".dalog__1 .d-btns .d-btn__active").attr("data-org-id",orgId);
        $('.dalog__1').show()
    }

</script>
</body>
</html>
