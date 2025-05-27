<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>费用报销清单</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/lefan14.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">


    <style>
        .td-spec{
            border-top: none!important;

        }
        table.table>tbody>tr>td{
            vertical-align: middle!important;
            padding-top: 12px;
            padding-bottom: 12px;
        }

        .class-list a{
            color: #428bca;
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
        <h3>费用报销清单 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/fee/list" method="post">
                    <input type="hidden" name="menuCode" value="${menuCode}">
                    <input type="hidden" id="searchCode" name="searchCode" value="${searchCode}">
                    <input type="hidden" name="reId" value="${reId}">
                    <input type="hidden" name="sortField" id="sortField" value="${sortField}">
                    <input type="hidden" name="sortType" id="sortType" value="${sortType}">

                    <input type="hidden" name="surveyOrgIds" id="surveyOrgIds" />
                    <input type="hidden" name="investigators" id="investigators" />
                    <div class="form-group">
                        清单名称:
                        <input name="feeReName" type="text" value="${feeReName}" style="width: 150px" class="form-control">
                    </div>
                    <c:if test="${show}">
                        <div class="form-group">
                            调查方机构:
                            <div>
                                <select class="select form-control select-checkbox" name="surveyOrgIds" data-select-name="surveyOrgIds" data-select-values="${surveyOrgIds}" multiple >
                                    <c:forEach items="${franchisee}" var="item">
                                        <option value="${item.id}" >${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            调查员:
                            <div>
                                <select class="select form-control select-checkbox" name="investigators" data-select-name="investigators" data-select-values="${investigators}" multiple >
                                    <c:forEach items="${investigator}" var="item">
                                        <option value="${item.userId}" >${item.realName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </c:if>
                    <div class="form-group">
                        报销状态:
                        <select onchange="javascript:$('#batchOperateBtn').click();" name="reState" class="form-control">
                            <option value="" <c:if test="${reState == ''}">selected="selected"</c:if>>全部</option>
                            <option value="1" <c:if test="${reState == '1'}">selected="selected"</c:if>>待提交发票</option>
                            <option value="2" <c:if test="${reState == '2'}">selected="selected"</c:if>>待机构审核</option>
                            <option value="3" <c:if test="${reState == '3'}">selected="selected"</c:if>>待财务审核</option>
                            <option value="4" <c:if test="${reState == '4'}">selected="selected"</c:if>>付款中</option>
                            <option value="5" <c:if test="${reState == '5'}">selected="selected"</c:if>>待确认到账</option>
                            <option value="6" <c:if test="${reState == '6'}">selected="selected"</c:if>>报销完成</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-spec">
            <thead>
            <tr>
                <c:if test="${show}">
                    <th width="5%">调查员</th>
                    <th width="7%">调查机构</th>
                </c:if>
                <th width="8%">清单名称</th>
                <th width="${ show ? '9%' : '10%'}">案件编号</th>
                <th width="${ show ? '6%' : '7%'}">被调查人</th>
                <th width="${ show ? '9%' : '10%'}">保险公司</th>
                <th width="8%">
                    <div class="title_sort" data-id="50" data-value="" data-field="entrustEndDate">
                        <span>保司终审时间</span>
                        <div class="icon-sort">
                            <div class="icon-up" ></div>
                            <div class="icon-down" ></div>
                        </div>
                    </div>
                </th>
                <th width="${ show ? '5%' : '6%'}">费用报销明细</th>
                <th width="${ show ? '6%' : '8%'}">费用报销合计</th>
                <th width="${ show ? '6%' : '7%'}">互助-基层员工案件资料调阅及复印费</th>
                <th width="${ show ? '6%' : '7%'}">互助-基层员工跨省跨市城际间差旅费报销-住宿费</th>
                <th width="${ show ? '6%' : '7%'}">互助-基层员工跨省跨市城际间差旅费报销-交通费</th>
                <th width="${ show ? '4%' : '5%'}">里程补贴</th>
                <th width="${ show ? '6%' : '7%'}">报销状态</th>
                <th width="${ show ? '9%' : '10%'}">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <c:set var="rows" value="${item.items.size()}"></c:set>
                <tr>
                    <c:if test="${show}">
                        <td <c:if test="${rows > 0}">rowspan="${rows}" </c:if>>${item.surveyUserName}</td>
                        <td <c:if test="${rows > 0}">rowspan="${rows}" </c:if>>${item.surveyOrgName}</td>
                    </c:if>
                    <td <c:if test="${rows > 0}">rowspan="${rows}" </c:if> >${item.feeReName}</td>
                    <td><a href="javascript:openInfo(${item.items.get(0).surveyInfoId},${item.surveyUserId},${item.reState})">${item.items.get(0).surveyCaseNo}</a></td>
                    <td>${item.items.get(0).surveyPerson}</td>
                    <td>${item.items.get(0).entrustOrgName}</td>
                    <td><fmt:formatDate value="${item.items.get(0).entrustOprDate}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>${item.items.get(0).money}</td>
                    <td <c:if test="${rows > 0}">rowspan="${rows}" </c:if> >${item.totalMoney}</td>
                    <td <c:if test="${rows > 0}">rowspan="${rows}" </c:if> >${item.dataFee}</td>
                    <td <c:if test="${rows > 0}">rowspan="${rows}" </c:if> >${item.hzAccommodationFee}</td>
                    <td <c:if test="${rows > 0}">rowspan="${rows}" </c:if> >${item.hzTransportationFee}</td>
                    <td <c:if test="${rows > 0}">rowspan="${rows}" </c:if> >${item.mileageSubsidy}</td>
                    <td <c:if test="${rows > 0}">rowspan="${rows}" </c:if> >
                            <c:if test="${item.preStateName != ''}">
                                ${item.preStateName}
                            </c:if>
                        <c:if test="${item.preStateName == ''}">
                            ${item.reStateName}
                        </c:if>
                            </td>
                    <td <c:if test="${rows > 0}">rowspan="${rows}" </c:if> >
                        <c:if test="${item.preStateName == ''}">
                            <c:if test="${item.totalMoney <= 0 && item.showBtn && menuCode == 'list'}">
                                <c:if test="${item.reState == 1}"><a href="javascript:;" onclick="operate('${item.id}','no-re',true,this);">提交审核</a></c:if>
                            </c:if>
                            <c:if test="${item.totalMoney > 0 && item.showBtn && menuCode == 'list'}">
                                <c:if test="${item.reState == 1 && item.showBtn}"><a href="javascript:;" onclick="operate('${item.id}','step-one',true,this);">提交机构审核</a></c:if>
                            </c:if>
                            <c:if test="${item.reState == 2 && item.showBtn && searchCode == 'reimbursement-manager-list'}"><a href="javascript:;" onclick="operate('${item.id}','step-two-yes',true,this);">审核通过</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="operate('${item.id}','step-two-no',false,this);">驳回</a></c:if>
                            <c:if test="${item.reState == 3 && item.showBtn && searchCode == 'reimbursement-manager-list'}"><a href="javascript:;" onclick="operate('${item.id}','step-three-yes',true,this);">审核通过</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="operate('${item.id}','step-three-no',false,this);">驳回</a></c:if>
                            <c:if test="${item.reState == 5 && item.showBtn && menuCode == 'list'}"><a href="javascript:;" onclick="operate('${item.id}','step-four',true,this);">确认到账</a></c:if>
                            <c:if test="${item.rejectDesc != null}">
                                <a class="viewReason">查看驳回原因</a>
                                <input type="hidden" value="${item.rejectDesc}" id="rejectDesc">
                            </c:if>
                        </c:if>

                    </td>
                </tr>
                <c:forEach items="${item.items}" var="line" varStatus="st">
                    <c:if test="${st.index > 0}">
                        <tr>
                            <td class="td-spec"><a  href="javascript:openInfo(${line.surveyInfoId},${item.surveyUserId},${item.reState})">${line.surveyCaseNo}</a></td>
                            <td class="td-spec">${line.surveyPerson}</td>
                            <td class="td-spec">${line.entrustOrgName}</td>
                            <td class="td-spec"><fmt:formatDate value="${line.entrustOprDate}" pattern="yyyy-MM-dd HH:mm"/></td>
                            <td class="td-spec">${line.money}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:forEach>
            </tbody>
        </table>
    </div><!--panel-info-->

    <div class="main-bottom">
        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">
            <jsp:param name="paginationObjectName" value="apiRsp" />
            <jsp:param name="pageNoName" value="" />
            <jsp:param name="requestUrl" value="${ctx}/fee/list?menuCode=${menuCode}&searchCode=${searchCode}&reId=${reId}&feeReName=${feeReName}&reState=${reState}&investigators=${investigators}&surveyOrgIds=${surveyOrgIds}&sortField=${sortField}&sortType=${sortType}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect.js?V=1"></script>
<script src="${ctx}/js/layui/layui.js"></script>
<script>

    layui.use('layer',function () {
        layer = layui.layer
    });
    $('.viewReason').click(function () {
        layer.alert($('#rejectDesc').val(),{ title: '驳回原因'})
    })

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

    function operate(id,btnCode,ajax,_this){
        var totalMoney = $(_this).parents("tr").find("td").eq(6).html();
        if(ajax){
            var url = "${ctx}/fee/operate",data = {"id":id,"btnCode":btnCode};
            layer.confirm(totalMoney!=0?'确认提交？':'该清单价格为0，提交之后将直接报销完成，确认没有任何需要报销的费用吗？', {
                btn: ['是','否'] //按钮
            }, function(){
                $('.layui-layer-btn .layui-layer-btn0').css({
                    'pointer-events': 'none'
                })
                $.ajax({
                    url:url,
                    type:"post",
                    data :data,
                    success:function(res){
                        res = JSON.parse(res)
                        if (res.isSuccess){
                            // closeDialogRefresh();//关闭并刷新
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 1
                            }, function () {
                                location.reload();
                            })

                        } else{
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 2
                            })
                        }
                    }
                });
            }, function(){

            });
        }else {
            layer.prompt({title:"请输入拒绝原因（必填项）",formType:2},function (text,index) {
                var url = "${ctx}/fee/operate",param = {"id":id,"btnCode":btnCode,"reason" : text};
                layer.close(index)
                $.ajax({
                    url:url,
                    type:"post",
                    data :param,
                    success:function(res){
                        res = JSON.parse(res)
                        if (res.isSuccess){
                            // closeDialogRefresh();//关闭并刷新
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 1
                            },function () {
                                location.reload();
                            })
                        } else{
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 2
                            })
                        }
                    }
                });
            })
        }
    }


    function openInfo(surveyInfoId,surveyUserId,state){
        var feeOpr = "view";
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;

        var searchCode = $("#searchCode").val();
        console.log(state,searchCode);
        if (state == 1 && searchCode == 'reimbursement-list'){
            feeOpr = "edit";
        }
        openDialog({
            frame:true,
            title:"案件详情",
            height:height,
            width:width,
            url:"${ctx}/survey/case/sic/info?surveyInfoId=" + surveyInfoId + "&surveyUserId=" + surveyUserId + "&menuCode=feeViewSurvey&feeOpr=" + feeOpr,
            load: true
        });
    }

</script>
</body>
</html>
