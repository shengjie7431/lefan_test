<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<jsp:useBean id="dateValue" class="java.util.Date"/>
<!DOCTYPE html>
<html>
<head>
    <title>预报销管理</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <%--<link rel="stylesheet" href="${ctx}/css/lefan14.css">--%>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">


    <style>
        .td-spec {
            border-top: none !important;

        }

        table.table > tbody > tr > td {
            vertical-align: middle !important;
            padding-top: 12px;
            padding-bottom: 12px;
        }

        .class-list a {
            color: #428bca;
        }

        .title_sort {
            display: flex;
            align-items: center;
            cursor: pointer;
        }

        .icon-sort {
            display: inline-block;
            width: 10px;
            padding-left: 2px;
        }

        .icon-sort .icon-up.active {
            border-bottom: 7px solid #333;
        }

        .icon-sort .icon-down.active {
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
        .dalogs-search {
            width: 98%;
            margin: 0 auto;
            padding: 50px 0;
        }

        .dalogs-search .layui-inline {
            width: 92%;
            padding: 10px 2%;
        }

        .dalogs-search .layui-inline label.layui-form-label {
            width: 116px;
        }

        .dalogs-search .layui-inline .layui-input {
            width: 200px;
        }

        .selectMul {
            width: 200px;
        }

        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }

        .layui-form-label{
            width: auto;
            margin: 0;
        }
        .selectMul{
            width: 160px;
        }
        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }
        .editA{
            padding: 0 4px;
            color: #3ba9ff!important;
        }
    </style>

</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>预报销管理</h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline">
                    <input type="hidden" name="menuCode" value="${menuCode}">
                    <input type="hidden" id="searchCode" name="searchCode" value="${searchCode}">
                    <input type="hidden" name="sortField" id="sortField" value="${sortField}">
                    <input type="hidden" name="sortType" id="sortType" value="${sortType}">
                    <input type="hidden" name="surveyOrgIds" id="surveyOrgIds"/>
                    <input type="hidden" name="investigators" id="investigators"/>
                    <%--<div class="form-group">--%>
                        <%--机构:--%>
                        <%--<div>--%>
                            <%--<select class="select form-control select-checkbox" name="surveyOrgIds"--%>
                                    <%--data-select-name="surveyOrgIds" data-select-values="${surveyOrgIds}" multiple>--%>
                                <%--<c:forEach items="${apiRsp.franseList}" var="item">--%>
                                    <%--<option value="${item.id}">${item.name}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--调查员:--%>
                        <%--<div>--%>
                            <%--<select class="select form-control select-checkbox" name="investigators"--%>
                                    <%--data-select-name="investigators" data-select-values="${investigators}" multiple>--%>
                                <%--<c:forEach items="${apiRsp.investigators}" var="item">--%>
                                    <%--<option value="${item.userId}">${item.realName}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="layui-inline form-group">
                        <label class="layui-form-label">调查方机构:</label>
                        <div class="layui-input-inline">
                            <div id="surveyOrgIdss" class="selectMul"></div>
                        </div>
                    </div>
                    <div class="layui-inline form-group">
                        <label class="layui-form-label">调查员:</label>
                        <div class="layui-input-inline">
                            <div id="investigatorss" class="selectMul"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        报销状态:
                        <select name="preState"
                                class="form-control">
                            <option value="" <c:if test="${preState == ''}">selected="selected"</c:if>>全部</option>
                            <option value="1" <c:if test="${preState == '1'}">selected="selected"</c:if>>待提交财务审核</option>
                            <option value="2" <c:if test="${preState == '2'}">selected="selected"</c:if>>待财务审核</option>
                            <option value="3" <c:if test="${preState == '3'}">selected="selected"</c:if>>财务审核通过</option>
                            <option value="4" <c:if test="${preState == '4'}">selected="selected"</c:if>>财务审核驳回</option>
                        </select>
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="button" class="btn btn-default">查询</button>&nbsp;
                        <c:if test="${!finance}">
                            <button id="add" type="button" class="btn btn-default">新增</button>&nbsp;
                        </c:if>

                    </div>
                </form>
            </div>
        </div>

        <%--<table class="table table-spec">--%>
            <%--<thead>--%>
            <%--<tr>--%>
                <%--<th width="5%">调查员</th>--%>
                <%--<th width="7%">机构</th>--%>
                <%--<th width="9%'">案件关联数</th>--%>
                <%--<th width="9%">费用报销合计</th>--%>
                <%--<th width="9%">均件报销金额</th>--%>
                <%--<th width="9%">费用报销（保险）</th>--%>
                <%--<th width="9%">费用报销（互助）</th>--%>
                <%--<th width="9%">报销状态</th>--%>
                <%--<th width="9%">操作</th>--%>
            <%--</tr>--%>
            <%--</thead>--%>
            <%--<tbody class="class-list">--%>
            <%--<c:forEach items="${apiRsp.surveyPreReimbursements}" var="item" varStatus="xh">--%>
                <%--<tr>--%>
                    <%--<td>${item.surveyUserName}</td>--%>
                    <%--<td>${item.orgName}</td>--%>
                    <%--<td>${item.caseCount}</td>--%>
                    <%--<td><a onclick="openInfo2('${item.id}','${item.clockIds}','all','${item.state}')">${item.totalMoney}</a></td>--%>
                    <%--<td>${item.avgMoney}</td>--%>
                    <%--<td><a onclick="openInfo2('${item.id}','${item.clockIds}','ins','${item.state}')">${item.insMoney}</a></td>--%>
                    <%--<td><a onclick="openInfo2('${item.id}','${item.clockIds}','help','${item.state}')">${item.helpMoney}</a></td>--%>
                    <%--<td>--%>
                        <%--<c:if test="${item.state == 1}"> 待提交财务审核</c:if>--%>
                        <%--<c:if test="${item.state == 2}"> 待财务审核</c:if>--%>
                        <%--<c:if test="${item.state == 3}"> 财务审核通过</c:if>--%>
                        <%--<c:if test="${item.state == 4}"> 财务审核驳回</c:if>--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--<c:if test="${item.state == 1 || item.state == 4}"> <a onclick="operate(${item.id},'submit',true,this)">提交财务审核</a></br>
                        <a onclick="operate(${item.id},'preDelete',true,this)">撤销</a></c:if>--%>
                        <%--<c:if test="${item.state == 2 && finance}"> <a class="operator2" data-id="${item.id}" data-money="${item.totalMoney}">审核通过</a></br>
                         <a onclick="operate(${item.id},'reject',false,this)">驳回</a></c:if>--%>
                        <%--<c:if test="${item.state == 4}"></br><a onclick="showReason('${item.returnText}')">驳回原因</a></c:if>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</c:forEach>--%>
            <%--</tbody>--%>
        <%--</table>--%>
        <div class="table-content">
            <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">
            </table>
        </div>
    </div><!--panel-info-->

    <%--<div class="main-bottom">--%>
        <%--<jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">--%>
            <%--<jsp:param name="paginationObjectName" value="apiRsp"/>--%>
            <%--<jsp:param name="pageNoName" value=""/>--%>
            <%--<jsp:param name="requestUrl"--%>
                       <%--value="${ctx}/fee/list?menuCode=${menuCode}&searchCode=${searchCode}&reId=${reId}&feeReName=${feeReName}&reState=${reState}&investigators=${investigators}&surveyOrgIds=${surveyOrgIds}&sortField=${sortField}&sortType=${sortType}"/>--%>
            <%--<jsp:param name="refreshDiv" value=""/>--%>
        <%--</jsp:include>--%>
    <%--</div><!--main-bottom-->--%>
    <input type="hidden" name="finance" value="${finance}">

    <%--<input type="hidden" name="alllist" id="alllist" value='${list}'>--%>

</div><!--main end-->

<script type="text/html" id="dalogs1">
    <div class="layui-form dalogs-search" lay-filter="search">
        <div class="layui-form-items" style="padding: 12px 0;">
            <div class="layui-inline">
                <label class="layui-form-label">选择调查员</label>
                <div class="layui-input-inline">
                    <div id="surveyInvestigators" class="selectMul"></div>
                </div>
            </div>
        </div>

        <div class="layui-inline" style="text-align: center">
            <button class="ll-submit layui-btn layui-btn-primary " style="width: 86px" lay-submit lay-filter="cancel">取消 </button>
            <button class="ll-submit layui-btn layui-btn-normal" lay-submit lay-filter="submit"
                    style="width: 86px">确定
            </button>
        </div>
    </div>
</script>
<script type="text/html" id="dalogs2">
    <div class="layui-form dalogs-search" lay-filter="search">
        <div class="layui-form-items">
            <input name="id" type="hidden" value="">
            <div class="layui-inline">
                <label class="layui-form-label">应付款金额</label>
                <div class="layui-input-inline _input">
                    <input type="text" name="money1" placeholder="请输入应付款金额" autocomplete="off" class="layui-input" disabled>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">实际付款金额</label>
                <div class="layui-input-inline _input">
                    <input type="text" name="money2" placeholder="请输入实际付款金额" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <div class="d-flex-wrap">
                    <label class="layui-form-label">付款日期</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input payTime" readonly id="payTime" placeholder="请选择日期">
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-inline" style="margin-left: 20px">
            <button class="ll-submit layui-btn layui-btn-primary " style="width: 86px" lay-submit lay-filter="cancel">取消 </button>
            <button class="ll-submit layui-btn layui-btn-normal" lay-submit lay-filter="submit2"
                    style="width: 86px">确定
            </button>
        </div>
    </div>
</script>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<%--<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect.js?V=1"></script>--%>
<script src="${ctx}/js/layui/layui.js"></script>
<script>

    // $(".select-checkbox").UCFormSelect();
    // $(".title_sort").click(function () {
    //     var sortField = $(this).attr("data-field");
    //     var sortType = 'up';
    //     var $children = $(this).children();
    //     if ($children.find(".active").html() == undefined) {
    //         $children.find(".icon-up").addClass("active");
    //     } else {
    //         var t = $children.find(".active").attr("class");
    //         if (t.toString().indexOf("up") > 0) {
    //             $children.find(".icon-up").removeClass("active");
    //             $children.find(".icon-down").addClass("active");
    //             sortType = 'down';
    //         } else {
    //             $children.find(".icon-up").addClass("active");
    //             $children.find(".icon-down").removeClass("active");
    //             sortType = 'up';
    //         }
    //     }
    //     $("#sortField").val(sortField);
    //     $("#sortType").val(sortType);
    //     $('#batchOperateBtn').click();
    // });





    // $("#batchOperateBtn").click(function(){
    //     //select 多选 赋值 到隐藏域。 便于后台传值
    //     $(".select-checkbox").each(function(){
    //         var selName = $(this).attr("name");
    //         var all = $(".UCSelect[name="+selName+"]").find(".UCSelectAll").hasClass("Selected");
    //         var name = $(this).attr("data-select-name");
    //         if(name){
    //             $("#" + name).val($(this).val());
    //         }
    //     })
    // });

    // $(document).ready(function () {
    //     var sortType = $("#sortType").val();
    //     var sortField= $("#sortField").val();
    //     if (sortType!=null && sortType !=''){
    //         $("div[data-field="+sortField+"]").find(sortType=='up'?'.icon-up':'.icon-down').addClass("active");
    //     }
    //     //默认选中 select
    //     $(".select-checkbox").each(function () {
    //         var value = $(this).attr("data-select-values");
    //         if (value) {
    //             $(this).val(value.replace(/\s*/g, '').split(','));
    //         } else {
    //             $(this).val("");
    //         }
    //     })
    //     $(".select-checkbox").UCFormSelect();
    //
    //     $('.btn-1').off("click");
    //     $('.btn-1').click(function(e){
    //         $(".UCSelect").find('.SelectVal').removeClass('over');
    //         $(".UCSelect").find("select").UCFormSelect('close');
    //         $("#batchOperateBtn").click();
    //     });
    //
    //     $(document).off('mousedown');
    //     $(document).bind('mousedown', function (e) {
    //         var Event = e.target;
    //         $('.UCSelect .SelectBox').each(function () {
    //             var Select = $(this).parents(".UCSelect").find("select").get(0);
    //             var Events = $(Event).parents(".UCSelect").find("select").get(0);
    //             if (!(Event && Select && Select == Events)) {
    //                 if( $(this).parents(".UCSelect").find('.SelectVal').hasClass("over")){
    //                     $("#batchOperateBtn").click();
    //                 }
    //                 $(this).parents(".UCSelect").find('.SelectVal').removeClass('over');
    //                 $(this).parents(".UCSelect").find("select").UCFormSelect('close');
    //             }
    //         });
    //     });
    //
    // });

    function showReason(reason) {
        layer.open({
            title: '驳回原因'
            ,content:  reason
        });
    }

    function operate(id, btnCode, ajax, _this) {

        var msg = 'preDelete' == btnCode ? '确认删除?'  : '确认提交?';

        if (ajax) {
            var url = "${ctx}/fee/operate", data = {"id": id, "btnCode": btnCode, "operateType": "pre"};
            layer.confirm(msg, {
                btn: ['是', '否'] //按钮
            }, function () {
                $('.layui-layer-btn .layui-layer-btn0').css({
                    'pointer-events': 'none'
                })
                $.ajax({
                    url: url,
                    type: "post",
                    data: data,
                    success: function (res) {
                        res = JSON.parse(res)
                        if (res.isSuccess) {
                            // closeDialogRefresh();//关闭并刷新
                            layer.msg(res.msg, {
                                time: 1000,
                                icon: 1
                            }, function () {
                                location.reload();
                            })

                        } else {
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 2
                            })
                        }
                    }
                });
            }, function () {

            });
        } else {
            layer.prompt({title: "请输入驳回原因（必填项）", formType: 2}, function (text, index) {
                var url = "${ctx}/fee/operate", param = {"id": id, "btnCode": btnCode, "operateType": "pre","returnText":text};
                layer.close(index)
                $.ajax({
                    url: url,
                    type: "post",
                    data: param,
                    success: function (res) {
                        res = JSON.parse(res)
                        if (res.isSuccess) {
                            // closeDialogRefresh();//关闭并刷新
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 1
                            }, function () {
                                location.reload();
                            })
                        } else {
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 2
                            })
                        }
                    }
                });
            })
        }
    };

    function openInfo2(preId,clockIds,type,reState) {
        openDialog({
            frame: true,
            title: "打卡列表",
            height: 800,
            width: 1500,
            url: "${ctx}/fee/list?menuCode=re_clock_list&clockIds="+clockIds+"&type="+type+"&reState="+reState+"&preId="+preId,
            load: true
        });
    }

    function openInfo(surveyInfoId,surveyUserId,state){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;

        openDialog({
            frame:true,
            title:"案件详情",
            height:height,
            width:width,
            url:"${ctx}/survey/case/sic/info?surveyInfoId=" + surveyInfoId + "&surveyUserId=" + surveyUserId+ "&menuCode=feeViewSurvey&feeOpr=view" ,
            load: true
        });
    };

    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select',
        soulTable: 'soulTable'
    })
    layui.use(['form', 'laydate', 'jquery', 'xmSelect', 'layer','table','soulTable'], function () {
        var form = layui.form,
            laydate = layui.laydate,
            xmSelect = layui.xmSelect,
            $ = layui.jquery,
            layer = layui.layer,
            table = layui.table,
            soulTable = layui.soulTable


        var demo1 = xmSelect.render({
            el: '#surveyOrgIdss',
            theme: {
                color: '#3BA9FF',
            },
            toolbar: {
                show: true
            },
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {
                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })
        var demo2 = xmSelect.render({
            el: '#investigatorss',
            theme: {
                color: '#3BA9FF',
            },
            toolbar: {
                show: true
            },
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {

                            if (sels.length == data.length) {
                                return '<div>全部</div>'
                            } else {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })

        function filterJson(demo, newJson, id, name, flag, selected) {
            var demoList = [],
                demoValues = []
            console.log(newJson)
            newJson.map(function (cur) {
                var _name = name ? cur[name] : cur.name
                var _id = id ? cur[id] : cur.id
                var param = {
                    name: _name,
                    value: _id,
                }
                if (selected) {
                    Object.assign(param, {selected: true})
                }
                demoList.push(param)
                demoValues.push(_id)
            })
            if (!flag) {
                demo.update({
                    data: demoList
                })
            } else {
                return demoList
            }
            setTimeout(function () {
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            }, 200)
        }



        $('#batchOperateBtn').click(function () {
            var param = {
                btnCode: 'preInfoList',
                preState: $('select[name=preState] option:selected').val(),
                surveyOrgIds: demo1.getValue('valueStr'),
                investigators: demo2.getValue('valueStr'),
            }
            setTable(_cols, param)
        })

        function barDemo(d) {
            console.log(d)
            var _html = ''
            var menuCode = $('input[name=menuCode]').val(),
                orgRole = $('input[name=orgRole]').val(),
                reSupervisor = $('input[name=reSupervisor]').val(),
                currentUserId= $('input[name=currentUserId]').val(),
                finance= $('input[name=finance]').val()

                if (d.state == 1 || d.state == 4){
                    _html += '<a class="editA" lay-event="submit">提交财务审核</a>'
                    _html += '<a class="editA" lay-event="preDelete">撤销</a>'
                }

                if (d.state == 2 && finance == 'true'){
                    _html += '<a class="editA" lay-event="operator2">审核通过</a>'+
                        '<a class="editA" lay-event="reject">驳回</a>'
                }

                if (d.state == 4){
                    _html += '<a class="editA"lay-event="showReason" >查看驳回原因</a>'
                }

            return  _html
        }
        var _cols = [
            [{
                field: 'surveyUserName',
                minWidth: 130,
                title: '调查员',
                fixed: 'left',
            },
                {
                    field: 'orgName',
                    minWidth: 180,
                    title: '调查机构',
                },
                {
                    field: 'state',
                    minWidth: 150,
                    title: '报销状态',
                    templet: function (d) {
                        // 报销状态(1.,2.,3.,4.,5.)
                        var _html =  ''
                        if (d.state == 1){
                            _html = '待提交财务审核'
                        }else if (d.state == 2){
                            _html = '待财务审核'
                        }else if (d.state == 3){
                            _html = '预报销完成'
                        }else if (d.state == 4){
                            _html = '财务审核驳回'
                        }
                        return _html
                    },
                },
                {
                    field: 'caseCount',
                    minWidth: 140,
                    title: '案件关联数',
                    sort: true
                },
                {
                    field: 'totalMoney',
                    minWidth: 140,
                    title: '费用报销合计',
                    style: 'color: #3BA9FF;cursor: pointer;',
                    event: 'moneysum',
                    sort: true
                },
                {
                    field: 'avgMoney',
                    minWidth: 150,
                    title: '件均报销金额',
                    templet: function (d) {
                        var _html =  Math.round(d.avgMoney * 100) /100 + '元/件'
                        return _html
                    },
                    sort: true
                },
                // {
                //     field: 'huanbiMoney',
                //     minWidth: 130,
                //     title: '件均环比上月',
                //     sort: true,
                //     templet: function (d) {
                //         var _html =  d.huanbiMoney ? d.huanbiMoney + '%' : '0'
                //         return _html
                //     },
                // },
                // {
                //     field: 'orgAvgMoney',
                //     minWidth: 120,
                //     title: '机构件均',
                //     templet: function (d) {
                //         var _html =  Math.round(d.orgAvgMoney * 100) /100 + '元/件'
                //         return _html
                //     },
                //     sort: true
                // },
                {
                    field: 'cityinDrivingMoney',
                    minWidth: 120,
                    title: '市内交通费',
                    sort: true
                },
                {
                    field: 'medicalHistoryMoney',
                    minWidth: 170,
                    title: '病史费（含复印费）',
                    sort: true
                },
                {
                    field: 'troubleshootingMoney',
                    minWidth: 150,
                    title: '住院排查费用',
                    sort: true
                },
                {
                    field: 'opcTroubleshootingMoney',
                    minWidth: 150,
                    title: '门诊排查费用',
                    sort: true
                },
                {
                    field: 'printingMoney',
                    minWidth: 150,
                    title: '体检报告打印费',
                    sort: true
                },
                {
                    field: 'accommodatioMoney',
                    minWidth: 150,
                    title: '住宿费',
                    sort: true
                },
                {
                    field: 'crossDrivingMoney',
                    minWidth: 150,
                    title: '跨地市交通费（汽车、火车、飞机）',
                    sort: true
                },
                {
                    field: 'selfDrivingMoney',
                    minWidth: 150,
                    title: '跨地市交通费（自驾）',
                    sort: true
                },
                {
                    field: 'otherMoney',
                    minWidth: 150,
                    title: '其他费用',
                    sort: true
                },
                {
                    field: '',
                    minWidth: 280,
                    fixed: 'right',
                    title: '操作',
                    templet: function (d) {
                        return barDemo(d)
                    }
                },
            ]
        ]

        var param = {
            btnCode: 'preInfoList',
            preState: $('select[name=preState] option:selected').val(),
        }
        setTable(_cols, param,true)

        function setTable(_cols, param,flag) {
            $('button.ll-submit').attr('disabled', true)
            setTimeout(function () {
                $('button.ll-submit').removeAttr('disabled')
            }, 6000)
            var _h = $('.panel-heading').outerHeight() +100
            var fullH = 'full-' + _h
            var myTable = table.render({
                id: "test",
                elem: '#test',
                even: true,
                cols: _cols,
                page: true,
                limit: 15,
                limits: [15,20,30,40,50],
                height: fullH,
                drag: false,
                totalRow: true,
                url: '${ctx}/fee/getData',
                where: param,
                request: {
                    pageName: 'pageNum' //页码的参数名称，默认：page
                    ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
                },
                parseData: function (res) {
                    if (flag){
                        filterJson(demo1, res.results.franseList, 'id', 'name', false, false)
                    }
                    var demo2Value = demo2.getValue('value')
                    filterJson(demo2, res.results.investigators, 'userId', 'realName', false, false)
                    demo2.setValue(demo2Value)
                    return {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results.surveyPreReimbursements,
                    }
                },
                initSort: {
                    field: 'avgMoney',
                    type: 'desc'
                },
                done: function (res) {
                    soulTable.render(this)
                    $('button.ll-submit').removeAttr('disabled')


                }
            })
            table.on('tool(test)', function (obj) {
                if (obj.event == 'moneysum') {
                    var downTime = new Date(obj.data.createTime)
                    var _month = downTime.getMonth()+1
                    var startStr = downTime.getFullYear() + '-'+ PrefixInteger(_month, 2)
                    var url = "${ctx}/fee/list?userId=" + obj.data.surveyUserId + "&createTime=" + startStr+"&menuCode=preClockDetails&orgId="+obj.data.surveyOrgId;
                    parent.parent.parent.addTab("案件打卡足迹",url,true);
                }
                if(obj.event == 'submit' || obj.event == 'preDelete') {
                    operate(obj.data.id,obj.event,true,obj.data.totalMoney)
                }else if (obj.event == 'reject'){
                    operate(obj.data.id,obj.event,false,obj.data.totalMoney)
                } else if (obj.event == 'showReason'){
                    showReason(obj.data.returnText)
                } else if (obj.event == 'operator2'){
                    layer.confirm('确认提交？', {
                        btn: ['是', '否'] //按钮
                    }, function () {
                        $.post("${ctx}/fee/operate?id="+obj.data.id+"&btnCode=pass&operateType=pre",{},function (res) {
                            var res = JSON.parse(res);
                            if (res.isSuccess){
                                location.href='${ctx}/fee/list?menuCode=preList';
                            }

                        })
                    });
                }
            })
        }

        var menuCode = $("input[name=menuCode]").val();
        if (menuCode == 'preSelectUser'){
            var _width = $(document).width() * 0.98
            var _height = $(document).height() * 0.98
            openIndex = layer.open({
                type: 1,
                title: '',
                area: ['400px', '400px'],
                content: $('#dalogs1').html(),
                success: function (layero, index) {
                    init()
                }
            });
        }
        var openIndex = '';
        $('#add').click(function () {
            $.ajax({
                url:'${ctx}/fee/getData',
                type:"post",
                data : {"btnCode":"preSelectUser"},
                success:function(res,param){
                    var userData = [];
                    JSON.parse(res).results.forEach(function (item) {
                        var obj = {};
                        obj.value = item.userId;
                        obj.name = item.realName;
                        userData.push(obj);
                    });
                    openIndex = layer.open({
                        type: 1,
                        title: '',
                        area: ['400px', '400px'],
                        content: $('#dalogs1').html(),
                        success: function (layero, index) {
                            init(userData);
                        }
                    });
                }
            });
        });


        function PrefixInteger(num, m) {
            return (Array(m).join(0) + num).slice(-m);
        }
        $('.operator2').click(function () {
            var id = $(this).attr("data-id");
            layer.confirm('确认提交？', {
                btn: ['是', '否'] //按钮
            }, function () {
                $.post("${ctx}/fee/operate?id="+id+"&btnCode=pass&operateType=pre",{},function (res) {
                    var res = JSON.parse(res);
                    if (res.isSuccess){
                        location.href='${ctx}/fee/list?menuCode=preList';
                    }

                })
            });



            // var id = $(this).attr("data-id");
            // var money = $(this).attr("data-money");
            // openIndex = layer.open({
            //     type: 1,
            //     title: '',
            //     area: ['400px', '400px'],
            //     content: $('#dalogs2').html(),
            //     success: function (layero, index) {
            //         init2(id,money)
            //     }
            // });
        });
        function getNowFormatDate() {
            var date = new Date();
            var seperator1 = "-";
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            var currentdate = year + seperator1 + month + seperator1 + strDate;
            return currentdate;
        };

        function init( userData) {
            var demo1 = xmSelect.render({
                el: '#surveyInvestigators',
                theme: {
                    color: '#3BA9FF',
                },
                radio: true,
                toolbar: {
                    show: true
                },
                filterable: true,
                filterDone: function (val, list) {
                    $('.xm-option-content').each(function () {
                        var _this = $(this)
                        _this.attr('title', _this.text())
                    })
                },
                model: {
                    label: {
                        type: 'templateSelf',
                        templateSelf: {
                            template(data, sels) {
                                var _html = ''
                                sels.filter(function (cur) {
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' +
                                        cur
                                            .name + '</div>'
                                })
                                return _html
                            }
                        },
                    }
                },
                data: []
            })
            demo1.update({
                data: userData
            })
        }
        function init2(id,money){
            $("input[name=money1]").val(money);
            $("input[name=money2]").val(money);
            $("input[name=id]").val(id);
            $("#payTime").val(getNowFormatDate());
            var payTime = laydate.render({
                elem: '#payTime',
            });
        }

        form.on('submit(submit2)', function (data) {
            var vals = data.field
            var params = Object.assign(vals, {
                payTime: $('#payTime').val()
            })
            if (!params.money1){
                layer.msg('请输入应付款金额',{
                    icon: 5
                })
                return;
            }
            if (!params.money2){
                layer.msg('请输入实际付款金额',{
                    icon: 5
                })
                return;
            }
            if (!params.payTime){
                layer.msg('请选择付款日期',{
                    icon: 5
                })
                return;
            }
            $(this).attr("disabled",true);
            // console.log(params)
            // layer.close(openIndex)
            $.post("${ctx}/fee/operate?id="+params.id+"&btnCode=pass&operateType=pre&payMoney="+params.money2+"&payDate="+params.payTime,{},function (res) {
                var res = JSON.parse(res);
                if (res.isSuccess){
                    location.href='${ctx}/fee/list?menuCode=preList';
                }

            })
        })

        form.on('submit(submit)', function (data) {
            var vals = data.field
            if (!vals.select){
                layer.msg('请选择调查员',{
                    icon: 5
                })
                return;
            }
            console.log(vals)
            $(this).attr("disabled",true);
            $.ajax({
                url:'${ctx}/fee/operate',
                type:"post",
                data : {
                    "btnCode":"addPre",
                    "userId":vals.select
                },
                success:function(res,param){
                    console.log(res)
                    res = JSON.parse(res);
                    layer.close(openIndex)
                    if (res.count == 0){
                        layer.msg("该调查员已提交过报销或者没有任何需要预报销的案件");
                    }else {
                        location.href='${ctx}/fee/list?menuCode=preList';
                    }
                }
            });

        });
        form.on('submit(cancel)', function (data) {
            layer.close(openIndex)
        })
    });
</script>
</body>
</html>
