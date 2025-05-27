<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>未匹配收款</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">

    <style>

        label.layui-form-label {
            width: auto;
            padding: 6px 0;
            margin-bottom: 0;
        }

        .selectMul {
            width: 160px;
        }

        .layui-inline{
            padding:0  10px;
            margin-bottom: 10px;
        }
        .paramTime{
            width: 140px;
            height: 32px;
            line-height: 32px;
        }
        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }

        input{
            color: #333!important;
        }
        .hovertd{
            overflow: hidden;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 3;
            cursor: default;
            word-break:break-all;
            word-wrap:break-word
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>未匹配收款列表 <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->

    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/billingApplyUnmatch/list?pageSize=${pageSize}" method="post">
                    <input type="hidden" name="pageSize" id="pageSize" value="20" />
<%--                    <div class="layui-inline">--%>
<%--                        <label class="layui-form-label">开票产品:</label>--%>
<%--                        <div class="layui-input-inline">--%>
<%--                            <div id="bullingItems" class="selectMul"></div>--%>
<%--                        </div>--%>
<%--                        <input type="hidden" value='${billingItemsIds}' id="billingItemsIds2" name="billingItemsIds2">--%>
<%--                        <input type="hidden" value='${billingItemsIds}' id="billingItemsIds" name="billingItemsIds">--%>
<%--                    </div>--%>
                    <div class="layui-inline">
                        <label class="layui-form-label">到账公司:</label>
                        <div class="layui-input-inline">
                            <div id="corporations" class="selectMul"></div>
                        </div>
                        <input type="hidden" value='${receivingCompanyIds}' id="receivingCompanyIds2" name="receivingCompanyIds2">
                        <input type="hidden" value='${receivingCompanyIds}' id="receivingCompanyIds" name="receivingCompanyIds">
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">交易日期:</label>
                        <div class="layui-input-inline" style="width: 310px;display: flex;align-items: center;justify-content: space-around;">
                            <input type="text" class="layui-input paramTime" readonly id="payTimeStart" name="payTimeStart" value="${payTimeStart}"
                                   placeholder="请选择日期">
                            <span class="dc-span">至</span>
                            <input type="text" class="layui-input paramTime" readonly id="payTimeEnd" name="payTimeEnd" value="${payTimeEnd}"
                                   placeholder="请选择日期">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">付款方:</label>
                        <div class="layui-input-inline">
                            <input type="text" name="payer" value="${payer}" placeholder="请输入付款人姓名"  autocomplete="off" class="layui-input paramTime">
                        </div>
                    </div>

                    <div class="layui-inline">
                        编号:<input name="unmatchNo" type="text" value="${unmatchNo}" class="form-control">
                    </div>
                    <div class="layui-inline">
                        附言:<input name="remark" type="text" value="${remark}" class="form-control">
                    </div>
                    <div class="layui-inline">
                        认领人:<input name="claimBy" type="text" value="${claimBy}" class="form-control">
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">状态:</label>
                        <div class="layui-input-inline">
                            <div id="state" class="selectMul"></div>
                        </div>
                        <input type="hidden" value='${states}' id="states2" name="states2">
                        <input type="hidden" value='${states}' id="states" name="states">
                        <%--状态:--%>
                        <%--<select name="state"  class="form-control">--%>
                            <%--<option value=""  <c:if test="${state == ''}">selected="selected" </c:if> >全部</option>--%>
                            <%--<option value="1" <c:if test="${state == '1'}">selected="selected" </c:if> >未认领</option>--%>
                            <%--<option value="2" <c:if test="${state == '2'}">selected="selected" </c:if> >已认领</option>--%>
                        <%--</select>--%>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">待财务审核:</label>
                        <div class="layui-input-inline">
                            <div id="waitChecks" class="selectMul"></div>
                            <input type="hidden" value='${waitCheck}' id="waitCheck2" name="waitCheck2">
                            <input type="hidden" value='${waitCheck}' id="waitCheck" name="waitCheck">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">认领时间:</label>
                        <div class="layui-input-inline" style="width: 310px;display: flex;align-items: center;justify-content: space-around;">
                            <input type="text" class="layui-input paramTime" readonly id="startReceiveTime" name="startReceiveTime" value="${startReceiveTime}"
                                   placeholder="请选择日期">
                            <span class="dc-span">至</span>
                            <input type="text" class="layui-input paramTime" readonly id="endReceiveTime" name="endReceiveTime" value="${endReceiveTime}"
                                   placeholder="请选择日期">
                        </div>
                    </div>
                    <div class="btn-group" style="margin-bottom: 10px">
                        &nbsp; &nbsp;<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button onclick="add()" type="button" class="btn btn-default">添加</button>&nbsp; &nbsp;
                        &nbsp; &nbsp;<button type="button" class="btn btn-default"><a class="exportUnmatch">导出</a></button>&nbsp; &nbsp;
                    </div>
                </form>
            </div>
        </div>


        <table class="table table-hover">
            <thead>
            <tr>
                <th width="6%">到账公司</th>
                <th width="6%">编号</th>
<%--                <th width="4%">开票产品</th>--%>
                <th width="6%">金额</th>
                <th width="9%">附言</th>
                <th width="3%">备注</th>
                <th width="6%">认领状态</th>
                <th width="6%">待财务审核</th>
                <th width="6%">付款方</th>
                <th width="6%">交易时间</th>
               <%-- <th width="6%">创建人</th>
                <th width="6%">创建时间</th>
                <th width="6%">认领编号</th>--%>
<%--                <th width="5%">认领人</th>--%>
<%--                <th width="6%">认领时间</th>--%>
                <th width="6%">已认领金额</th>
                <th width="15%">认领明细</th>
                <th width="6%">未认领金额</th>
                <th width="6%">退费金额</th>
                <th width="5%">操作</th>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                    <td>${item.receivingCompanyName}</td>
                    <td>${item.unmatchNo}</td>
<%--                    <td>${item.billingItemsName}</td>--%>
<%--                    <td>${item.money}</td>--%>
                    <td ><fmt:formatNumber type="number" value="${item.money}" maxFractionDigits="2"/></td>
                    <td><div class="hovertd" title="${item.remark}">${item.remark}</div></td>
                    <td><div class="hovertd" title="${item.comments}">${item.comments}</div></td>
                    <td>
                        <c:if test="${item.state ==1}">未认领</c:if>
                        <c:if test="${item.state ==2}">已认领</c:if>
                        <c:if test="${item.state ==3}">部分认领</c:if>
                    </td>
                    <td>${item.waitCheck?'有':'无'}</td>
                    <td>${item.payer}</td>
                    <td><fmt:formatDate value="${item.payTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <%--<td>${item.createBy}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>${item.billNo}</td>--%>
<%--                    <td>${item.claimBy}</td>--%>
<%--                    <td><fmt:formatDate value="${item.claimTime}" pattern="yyyy-MM-dd HH:mm"/></td>--%>
<%--                    <td>${item.matchMoney ==null?0:item.matchMoney}</td>--%>
                    <td ><fmt:formatNumber type="number" value="${item.matchMoney ==null?0:item.matchMoney}" maxFractionDigits="2"/></td>
                    <td>
                        <c:forEach items="${item.receiveInfos}" var="receive">
                            金额:${receive.receiveMoney}&nbsp;日期<fmt:formatDate value="${receive.receiveTime}" pattern="yyyy-MM-dd HH:mm"/><br/>
                        </c:forEach>
                    </td>
<%--                    <td>${item.unmatchMoney ==null?0:item.unmatchMoney}</td>--%>
                    <td ><fmt:formatNumber type="number" value="${item.unmatchMoney ==null?0:item.unmatchMoney}" maxFractionDigits="2"/></td>
<%--                    <td>${item.unmatchMoney ==null?0:item.refundMoney}</td>--%>
                    <td ><fmt:formatNumber type="number" value="${item.unmatchMoney ==null?0:item.refundMoney}" maxFractionDigits="2"/></td>
                    <td><a href="javascript:info('${item.id}');" style="color: #3ba9ff">详情</a>
                        <c:if test="${item.state !=2}">
                            <a href="javascript:del('${item.id}');" style="color: #3ba9ff">删除</a>
                        </c:if>
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
            <jsp:param name="requestUrl" value="${ctx}/billingApplyUnmatch/list?unmatchNo=${unmatchNo}&claimBy=${claimBy}&state=${state}&remark=${remark}&startReceiveTime=${startReceiveTime}&endReceiveTime=${endReceiveTime}" />
            <jsp:param name="refreshDiv" value="" />
        </jsp:include>
    </div><!--main-bottom-->

</div><!--main end-->
<%--<input type="hidden" value='${params.bullingItems}' id="bullingItemsJson">--%>
<input type="hidden" value='${params.corporations}' id="corporationsJson">

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src="${ctx}/js/layui/layui.js"></script>

<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })

    layui.use(['xmSelect', 'laydate','jquery'], function () {
        var laydate = layui.laydate,
            xmSelect = layui.xmSelect,
            $ = layui.jquery

        // var demo1 = xmSelect.render({
        //     el: '#bullingItems',
        //     theme: {
        //         color: '#3BA9FF',
        //     },
        //     size: 'small',
        //     toolbar: {
        //         show: true
        //     },
        //     filterable: true,
        //     filterDone: function(val, list){
        //         $('.xm-option-content').each(function () {
        //             var _this = $(this)
        //             _this.attr('title', _this.text())
        //         })
        //     },
        //     model: {
        //         label: {
        //             type: 'xxxx', //自定义与下面的对应
        //             xxxx: {
        //                 template(data, sels) {
        //
        //                     if (sels.length == data.length) {
        //                         return '<div>全部</div>'
        //                     } else {
        //                         var _html = ''
        //                         sels.filter(function (cur) {
        //                             _html +=
        //                                 '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
        //                         })
        //                         return _html
        //                     }
        //                 }
        //             },
        //         }
        //     },
        //     data: [],
        //     on: function(data){
        //         //arr:  当前多选已选中的数据
        //         var arr = data.arr;
        //         var ids = []
        //         arr.map(function (cur) {
        //             ids.push(cur.value)
        //         })
        //         $('#billingItemsIds').val(ids.join(','))
        //     },
        // })
        var demo2 = xmSelect.render({
            el: '#corporations',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
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
            data: [],
            on: function(data){
                //arr:  当前多选已选中的数据
                var arr = data.arr;
                var ids = []
                arr.map(function (cur) {
                    ids.push(cur.value)
                })
                $('#receivingCompanyIds').val(ids.join(','))
            },
        })
        var demo3 = xmSelect.render({
            el: '#state',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
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
            data: [{
                name: '未认领',
                value: 1,
            },{
                name: '已认领',
                value: 2
            },{
                name: '部分认领',
                value: 3
            }],
            on: function(data){
                //arr:  当前多选已选中的数据
                var arr = data.arr;
                var ids = []
                arr.map(function (cur) {
                    ids.push(cur.value)
                })
                $('#states').val(ids.join(','))
            },
        })
        var demo4 = xmSelect.render({
            el: '#waitChecks',
            theme: {
                color: '#3BA9FF',
            },
            radio:true,
            size: 'small',
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {

                            var _html = ''
                            sels.filter(function (cur) {
                                _html +=
                                    '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                            })
                            return _html
                        }
                    },
                }
            },
            data: [   {name: '全部',value: ''},
                {name: '有',value: '1'},
                {name: '无',value: '0'}],
            on: function(data){
                //arr:  当前多选已选中的数据
                var arr = data.arr;
                var ids = []
                arr.map(function (cur) {
                    ids.push(cur.value)
                })
                $('#waitCheck').val(ids.join(','))
            },
        })
        function filterJson(demo, newJson, id, name, flag, selected) {
            var demoList = [],
                demoValues = []
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

        // var bullingItemsJson = $("#bullingItemsJson").val();
        // bullingItemsJson = JSON.parse(bullingItemsJson);
        // filterJson(demo1, bullingItemsJson, 'id', 'enumText', false, false)

        var corporationsJson = $("#corporationsJson").val();
        corporationsJson = JSON.parse(corporationsJson);
        filterJson(demo2, corporationsJson, 'id', 'name', false, false)

        // demo1.setValue($('#billingItemsIds2').val().split(','))
        // $('#billingItemsIds').val($('#billingItemsIds2').val())

        demo2.setValue($('#receivingCompanyIds2').val().split(','))
        $('#receivingCompanyIds').val($('#receivingCompanyIds2').val())

        demo3.setValue($('#states2').val().split(','))
        $('#states').val($('#states2').val())


        demo4.setValue($('#waitCheck2').val().split(','))
        $('#waitCheck').val($('#waitCheck2').val())


        startTime = laydate.render({
            elem: '#payTimeStart',
            done: function (value, date) {
                endTime.config.min = {
                    year: date.year,
                    month: date.month - 1,
                    date: date.date
                }
            }
        });
        endTime = laydate.render({
            elem: '#payTimeEnd',
            done: function (value, date) {
                if (value){
                    startTime.config.max = {
                        year: date.year,
                        month: date.month - 1,
                        date: date.date
                    }
                } else {
                    startTime.config.max = {
                        year: 2100,
                        month: 1,
                        date: 1
                    }
                }
            }
        });


        startReceiveTime = laydate.render({
            elem: '#startReceiveTime',
            done: function (value, date) {
                endReceiveTime.config.min = {
                    year: date.year,
                    month: date.month - 1,
                    date: date.date
                }
            }
        });

        endReceiveTime = laydate.render({
            elem: '#endReceiveTime',
            done: function (value, date) {
                if (value){
                    startReceiveTime.config.max = {
                        year: date.year,
                        month: date.month - 1,
                        date: date.date
                    }
                } else {
                    startReceiveTime.config.max = {
                        year: 2100,
                        month: 1,
                        date: 1
                    }
                }
            }
        });

        $(".exportUnmatch").click(function () {
            location.href="${ctx}/billingApplyUnmatch/export?ummatch=true&billingItemsIds="
                +"&receivingCompanyIds="+demo2.getValue('valueStr')+"&payTimeStart="+$("#payTimeStart").val()+"&payTimeEnd="+$("#payTimeEnd").val()
                +"&payer="+$("input[name=payer]").val()+"&unmatchNo="+$("input[name=unmatchNo]").val()
                +"&claimBy="+$("input[name=claimBy]").val()+"&states="+demo3.getValue('valueStr')
                +"&waitCheck="+demo4.getValue('valueStr')+"&remark="+$("input[name=remark]").val()
                +"&startReceiveTime=" + $("#startReceiveTime").val()
                +"&endReceiveTime=" + $("#endReceiveTime").val()
        })

    })
    var info = function(id){
        var _height = $(window).height() * 0.98
        var _width = $(document).width() * 0.98
        openDialog({
            frame:true,
            title:"详情",
            height:_height,
            width:_width,
            url:"${ctx}/billingApplyUnmatch/info?id="+id,
            load:true
        });
    }

    var add = function(){
        openDialog({
            frame:true,
            title:"添加",
            height:600,
            width:800,
            url:"${ctx}/billingApplyUnmatch/add"
        });
    }


    /**
     * 删除
     */
    function del(id){
        ajaxSubmit("${ctx}/billingApplyUnmatch/delete",{"id":id},reload,"删除成功！","确认删除？","删除失败！");
    }
</script>
</body>
</html>
