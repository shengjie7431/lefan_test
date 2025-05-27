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
                <form class="form-inline" role="form" action="${ctx}/survey/case/list?menuCode=${menuCode}" method="post">
                    <%--多选--%>
                        <input type="hidden" name="surveyInfoIds" id="surveyInfoIds" />
                        <input type="hidden" name="entrustOrgIds" id="entrustOrgIds" />
                        <input type="hidden" name="departmentIds" id="departmentIds" />
                        <input type="hidden" name="sortField" id="sortField" value="${sortField}">
                        <input type="hidden" name="sortType" id="sortType" value="${sortType}">

                        <div class="form-group">
                            快捷查询:<input style="width: 500px" name="searchStr" type="text" value="${searchStr}" placeholder="可输入被调查人，案件编号，调查编号，联系方式，身份证号" class="form-control">
                        </div>
<%--                    <div class="form-group">--%>
<%--                        调查编号:<input name="surveyNo" type="text" value="${surveyNo}" style="width: 150px" class="form-control">--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        被调查人:<input name="surveyPerson" type="text" value="${surveyPerson}" style="width: 150px" class="form-control">--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        联系方式:<input name="surveryPersonTel" type="text" value="${surveryPersonTel}" style="width: 150px" class="form-control">--%>
<%--                    </div>--%>
                    <div class="form-group">
                        保险公司:
                        <%--<select name="entrustOrgId" id="entrustOrgId" class="singleSelect form-control">
                            <option value="">全部</option>
                            <c:forEach items="${consignors}" var="item">
                                <option <c:if test="${entrustOrgId == item.id}">selected="selected" </c:if> value="${item.id}" >${item.company}</option>
                            </c:forEach>
                        </select>--%>
                        <div>
                            <select class="select form-control select-checkbox" id="entrustOrgIdsChk" name="entrustOrgIdsChk" data-select-name="entrustOrgIds" data-select-values="${entrustOrgIds}" >
                                <c:forEach items="${consignors}" var="item">
                                    <option value="${item.id}" >${item.company}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        部门
                        <select class="select form-control" id="departmentIdChk" name="departmentIdChk" data-select-name="departmentIds" data-select-values="${departmentIds}" multiple >

                        </select>
                    </div>
                    <div class="form-group">
                        创建时间:<input name="date" type="text" value="${date}" style="width: 150px" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly>
                    </div>
                    <br>
                    <div class="form-group">
                        终审时间:
                        <%--<input name="reportEndDate" type="text" value="${reportEndDate}" style="width: 150px" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly>--%>
                        <input name="entrReportStateDate" type="text" value="${entrReportStateDate}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        <span>--</span>
                        <input name="entrReportEndDate" type="text" value="${entrReportEndDate}" style="width: 150px;cursor: auto" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>

                    </div>
                    <div class="form-group">
                        支付状态:
                        <select onchange="javascript:$('#batchOperateBtn').click();" name="isPayEntrustFee"  class="form-control">
                            <option value=""  <c:if test="${isPayEntrustFee == ''}">selected="selected" </c:if> >全部</option>
                            <option value="0" <c:if test="${isPayEntrustFee == '0'}">selected="selected" </c:if> >未申请</option>
                            <option value="1" <c:if test="${isPayEntrustFee == '1'}">selected="selected" </c:if> >开票中</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="operateList()" type="button" class="btn btn-default">批量开票</button>
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
                <th width="100">调查编号</th>
                <th width="100">被调查人</th>
                <%--<th width="100">联系方式</th>--%>
                <th width="100">开票金额</th>
                <th width="150">领域</th>
                <th width="200">保险公司</th>
                <th width="200">部门</th>
                <%--<th width="100">案件阶段</th>--%>
                <th width="80">支付状态</th>
                <%--<th width="100">到账时间</th>--%>
                <th width="100">
                    <div class="title_sort" data-id="50" data-value="" data-field="createTime">
                        <span>创建时间</span>
                        <div class="icon-sort">
                            <div class="icon-up" ></div>
                            <div class="icon-down" ></div>
                        </div>
                    </div>
                </th>
                <th width="100">
                    <div class="title_sort" data-id="50" data-value="" data-field="entrustReportEndDate">
                        <span>终审时间</span>
                        <div class="icon-sort">
                            <div class="icon-up" ></div>
                            <div class="icon-down" ></div>
                        </div>
                    </div>
                </th>
                <th width="80">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>

                    <td>
                        <c:if test="${item.isPayEntrustFee == 0}">
                            <input type="checkbox" name="list-checkbox" value="${item.id}">
                            <input type="hidden" value="${item.entrustOrgId}" name="entrustOrgIdList" />
                            <input type="hidden" value="${item.entrustOrgName}" name="entrustOrgNameList" />
                        </c:if>
                    </td>

                    <td><a href="javascript:void(0);" onclick="info(${item.id})">${item.surveyRiskCase.surveyNo}</a></td>
                    <td>${item.surveyRiskCase.surveyPerson}</td>
                    <%--<td>${item.surveyRiskCase.surveryPersonTel}</td>--%>
                    <td>${item.billingMoney}<a onclick="updPayMoney(${item.id},${item.billingMoney})"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a></td>
                    <td>${item.surveyBusName}</td>
                    <td>${item.entrustOrgName}</td>
                    <td>${item.surveyRiskCase.departmentName}</td>
                    <%--<td>--%>
                        <%--<c:if test="${item.surveyPhase == 1}">--%>
                            <%--委托阶段--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${item.surveyPhase == 2}">--%>
                            <%--调查阶段--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${item.surveyPhase == 3}">--%>
                            <%--已结案--%>
                        <%--</c:if>--%>
                    <%--</td>--%>
                    <td>
                        <c:if test="${item.isPayEntrustFee == 0}">
                            未申请
                        </c:if>
                        <c:if test="${item.isPayEntrustFee == 1}">
                            开票中
                        </c:if>
                        <c:if test="${item.isPayEntrustFee == 2}">
                            已开票
                        </c:if>
                    </td>
                        <%--<td><fmt:formatDate value="${item.arrivalDate}" pattern="yyyy-MM-dd HH:mm"/></td>--%>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${item.entrustReportEndDate}" pattern="yyyy-MM-dd"/></td>
                    <td>
                        <c:if test="${item.isPayEntrustFee == 0}">
                            <a href="javascript:void(0);" onclick="operate(${item.id},1501,2,${item.entrustOrgId},'${item.entrustOrgName}')">开票</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->
    <div class="dalog dalog__1" style="display: none">
        <div class="d-title">金额调整</div>
        <input value="" name="id" hidden>
        <div class="d-cell">
            <div class="d-cell__label">开票金额</div>
            <div class="d-cell__input">
                <input id="updPayMoney" type="number" value="">
                <span>元</span>
            </div>
        </div>
        <div class="d-btns">
            <div class="d-btn" data-id="1">取消</div>
            <div class="d-btn d-btn__active" data-id="2">确认</div>
        </div>
    </div>
    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/survey/case/list?menuCode=${menuCode}&searchStr=${searchStr}&entrustOrgName=${entrustOrgName}&data=${data}&isPayEntrustFee=${isPayEntrustFee}&surveyPerson=${surveyPerson}&surveryPersonTel=${surveryPersonTel}&surveyNo=${surveyNo}&entrReportStateDate=${entrReportStateDate}&entrReportEndDate=${entrReportEndDate}&entrustOrgId=${entrustOrgId}&entrustOrgIds=${entrustOrgIds}" />
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
    $("#batchOperateBtn").click(function(){
        //select 多选 赋值 到隐藏域。 便于后台传值
        $(".select-checkbox").each(function(){
            var name = $(this).attr("data-select-name");
            if(name){
                $("#" + name).val($(this).val());
            }
        })
    });

    //根据机构加载部门
    loadDepartment();
    function loadDepartment(){
        var value = $("#entrustOrgIdsChk").attr("data-select-values");
        $.ajax({
            url:'${ctx}/baseSurvey/selectInfoByRelationId',
            type:"POST",
            data : {"consignorId":value,"surveyCode":"consignor","btnCode" : 1000},
            success:function(res){
                var r = JSON.parse(res).results;
                for(var i = 0; i < r.departmentList.length; i++){
                    var sel = false;
                    var val = r.departmentList[i];
                    var values = $("#departmentIdChk").attr("data-select-values");
                    if(values){
                        values.split(",").find(function(e){
                            if (e == val.id) {
                                sel = true;
                            }
                        })
                    }
                    if (sel){
                        $("#departmentIdChk").append("<option selected value='" + val.id + "'>" + val.name + "</option>");
                    } else{
                        $("#departmentIdChk").append("<option value='" + val.id + "'>" + val.name + "</option>");
                    }
                }
                $("#departmentIdChk").addClass("select-checkbox");
                $("#departmentIdChk").UCFormSelect();
                $(".UCSelect[name=departmentIdChk]").width(300);
            }
        });
    }


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

    $(document).ready(function(){
//        $('.singleSelect').select2();
        $("#check-btn").on('change',function(){
            $("input[name='list-checkbox']").prop("checked",this.checked);
        })
    })

    $(function(){
        $("#editForm").bind('submit', function(event) {

        });
    });

    var info = function(id){
        openDialog({
            frame:true,
            title:"详情",
            height:700,
            width:1000,
            url:"${ctx}/survey/case/info?id=" + id + "&menuCode=${menuCode}",
            load:true
        });
    }

    var operate = function(id,btnCode,copy,entrustOrgId,entrustOrgName){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 10;
        openDialog({
            frame:true,
            title:"详情",
            height:height,
            width:1000,
            url: "${ctx}/billingApply/billApplyEdit?id=" + id + "&btnCode="+btnCode+"&copy="+copy+"&companyName="+entrustOrgName+"&companyId="+entrustOrgId+"&entrustOrgId=" + entrustOrgId,    //此处的id是调查字表id
            load:true
        });
    }

    var operateList =function(){
        var check_name = document.getElementsByName("list-checkbox");
        var check_orgId = document.getElementsByName("entrustOrgIdList");
        var check_orgName= document.getElementsByName("entrustOrgNameList");
        console.log(check_name,check_orgId,check_orgName);
        var idArr=new Array();
        var orgIdArr=new Array();
        var entrustOrgId="";
        var entrustOrgName="";
        for(var i=0;i<check_name.length;i++){
            if(check_name[i].checked){
                idArr.push(check_name[i].value);
                orgIdArr.push(check_orgId[i].value);
                entrustOrgId=check_orgId[i].value;
                entrustOrgName=check_orgName[i].value;
            }
        }
        if(idArr.length == 0){
            alert("未选中数据");
            return;
        }
        for (var num =0; num < orgIdArr.length-1; num++) {
            if(orgIdArr[num]!=orgIdArr[num+1]){
                var msg="被选中的，有不同机构数据";
                alert(msg);
                return;
            }
        }

        if(idArr.length>0){
            batchOperate(idArr,orgIdArr,entrustOrgId,entrustOrgName);
        }
    }

    function batchOperate(idArr,orgIdArr,entrustOrgId,entrustOrgName){
        //idArr长度过长，传值（前端报错）
        $.ajax({
            url:'${ctx}/billingApply/selectInfoByRelationId',
            type:"POST",
            data : {"ids":JSON.stringify(idArr),"btnCode":"1300"},
            success:function(res,param){
                var width = $(document.body).outerWidth();
                var height = $(document).outerHeight() - 10;
                var r = JSON.parse(res).results;
                var billingMoney = r.billingMoney;
                var surveyNo = r.surveyNo;
                $("#surveyInfoIds").val(idArr.join(","))
                //此处的idList，通过获取父姐妹元素的方式获取。  billApplySurvey.jsp的init方法
                var url="${ctx}/billingApply/billApplyEdit?idList=&billingMoney=" + billingMoney + "&btnCode=1501" + "&copy=3" + "&companyName=" + entrustOrgName + "&companyId=" + entrustOrgId + "&surveyNo=" + surveyNo+"&entrustOrgId="+entrustOrgId;
                openDialog({
                    frame:true,
                    title:"详情",
                    height:height,
                    width:1000,
                    url:  url,
                    load:true
                });
            }
        });
    }
    var updPayMoney = function(id,money){
        // var oldPayMoney = $("#td_money1_" + orgId).text();
        // var curPayMoney = $("#td_money_" + orgId).text();
        // var curRemark = $("#td_remark_" + orgId).text();
        // $("#updPayMoney1").val(oldPayMoney);
        // $("#updPayMoney2").val(curPayMoney);
        // $("#updRemark").val(curRemark);
        // $(".dalog__1 .d-btns .d-btn__active").attr("data-org-id",orgId);
        $(".dalog__1").find("input[name=id]").val(id);
        $(".dalog__1").find("#updPayMoney").val(money);
        $('.dalog__1').show()
    }

    $('.dalog__1 .d-btn').click(function(){
        if ($(this).attr('data-id') == 1){
            $('.dalog__1').hide()
        }
        if ($(this).attr('data-id') == 2){
            var updPayMoney = $("#updPayMoney").val();
            $.ajax({
                url: '${ctx}/survey/case/save',
                type: "POST",
                data:{
                    id:$("input[name=id]").val(),
                    menuCode:"batchKp",
                    updMoney:updPayMoney
                },
                success: function (res, param) {
                    var r = JSON.parse(res);
                    if(r.isSuccess){
                        $('.dalog__1').hide()
                    }
                    alert(JSON.parse(res).msg)
                    setTimeout(function () {
                        location.reload();
                    },1000);

                }
            });
        }
    })
</script>
</body>
</html>
