<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>开票list</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">

    <style>
        .submit-container{
            position: fixed;
            right: 0;
            bottom:20px;
            width: 100%;
            height: 40px;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .submit{
            padding: 0;
            margin-left: 15px;
            width: 120px;
            height: 40px;
            line-height: 40px;
            color: #fff;
            background-color: #3ba9ff;
            text-align: center;
            z-index: 999;
        }

        .poi-no{
            pointer-events: none;
        }
        td{
            word-wrap:break-word;
            word-break: break-all;
        }
        td .claimMoney{
            min-width: 90%;
        }

    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>开票list <small>共<span>${apiRsp.count}</span>个</small></h3>
    </div><!--main-top-->
    <input type="hidden" value="${unmatchId}" id="unmatchId">
    <div class="panel panel-info">

        <div class="panel-heading">
            <div class="pin">
                <form class="form-inline" role="form" action="${ctx}/billingApplyUnmatch/billingList" method="post">
                    <input type="hidden" id="unmatchId" name="unmatchId" value="${unmatchId}" >
                    <input type="hidden" id="money" name="money" value="${money}" >
                    <input type="hidden" id="menuType" name="menuType" value="101" >
                    <input type="hidden" id="apply" name="apply" value="${apply}" >
                    <input type="hidden" id="clickBtn" name="clickBtn" value="true" >
                    <div class="form-group">
                        案件标题: <input name="caseTitle" type="text"  value="${caseTitle}" class="form-control" style="width: 150px"/>
                        案件编号: <input name="caseNo" type="text"  value="${caseNo}" class="form-control" style="width: 150px"/>
                        发票单号: <input name="billingCode" type="text"  value="${billingCode}"  class="form-control" style="width: 150px"/>
                    </div>
                    <div class="btn-group">
                        <button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>
                    </div>
                </form>
            </div>
        </div>

        <table class="table table-striped">
            <thead>
            <tr>
                    <%--<th width="20"><input type="checkbox" class="checkAll"></th>--%>
                <th width="9%">案件编号</th>
                <th width="9%">案件标题</th>
                <th width="9%">开票产品</th>
                <th width="8%">开票项目</th>
                <th width="9%">发票单号</th>
                <th width="8%">开票金额</th>
                <th width="11%">开票对象</th>
                <th width="9%">开票时间</th>
                <th width="6%">收入归属机构</th>
                <th width="7%">已到账金额</th>
                <th width="7%">未到账金额</th>
                <th width="8%">本次认领金额</th>
<%--                <th width="80">操作</th>--%>
            </tr>
            </thead>
            <tbody class="class-list">
            <c:forEach items="${apiRsp.results}" var="item">
                <tr>
                        <%--<td class="checkTd">--%>
                            <%--<input type="checkbox" class="checkOne" disabled data-disabled="false"  name="checkItem" data-billingId="${item.billingId}"  data-billingImgsId="${item.billingImgsId}">--%>
                        <%--</td>--%>
                    <td>${item.caseNo}</td>
                    <td>${item.caseTitle}</td>
                    <td>
                        <c:forEach items="${bullingEnums}" var="dto">
                            <c:if test="${dto.enumCode == item.billingEnum}">
                                ${dto.enumName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${bullingItems}" var="dto">
                            <c:if test="${dto.enumCode == item.billingItem}">
                                ${dto.enumName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${item.billingCode}</td>
                    <td>${item.billingMoney}</td>
                    <td>${item.companyName}</td>
                    <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    <td>${item.staffOrgName}</td>
                    <td><c:if test="${item.theAccountMoney == null}">0.00</c:if><c:if test="${item.theAccountMoney != null}">${item.theAccountMoney}</c:if> </td>
                    <td>${item.billingMoney-item.theAccountMoney}</td>
                    <td class="claimTd"><input type="number" min="0" max="${item.billingMoney-item.theAccountMoney}" class="claimMoney" data-billingId="${item.billingId}"  data-billingImgsId="${item.billingImgsId}"/></td>
<%--                    <th>--%>
<%--                        <a href="javascript:claim('${item.id}','${unmatchId}','${money}');">选择</a>--%>
<%--                    </th>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
            <div class="submit-container">
                    <div class="layui-input-inline" style="width: 260px;border: 1px solid #eee;padding: 0 10px;background-color: #fff">
                        认领时间
                        <input type="text" class="layui-input paramTime" readonly id="startTime" style="border:none;    width: 154px;
    display: inline-block;"
                               placeholder="请选择日期" disabled>
                    </div>
                <div class="submit">确认</div>
            </div>
    </div><!--panel-info-->

<%--    <div class="main-bottom">--%>
<%--        <jsp:include page="/WEB-INF/pages/common/pagination.jsp" flush="true">--%>
<%--            <jsp:param name="paginationObjectName" value="apiRsp" />--%>
<%--            <jsp:param name="pageNoName" value="" />--%>
<%--            <jsp:param name="requestUrl" value="${ctx}/billingApplyUnmatch/billingList?unmatchId=${unmatchId}&billingCode=${billingCode}&caseNo=${caseNo}&caseTitle=${caseTitle}" />--%>
<%--            <jsp:param name="refreshDiv" value="" />--%>
<%--        </jsp:include>--%>
<%--    </div><!--main-bottom-->--%>

</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script>
    function claim(billId,unmatchId,money) {
        ajaxSubmit("${ctx}/billingApplyUnmatch/claim",{"billId":billId,"unmatchId":unmatchId,"money":money,"menuType":10},reloadParent,"认领成功！","确认认领？","认领失败！");
    }

    $(function () {


        $('.checkAll').click(function () {
            var _this = $(this)
            console.log(_this,_this.attr('checked'))
            if (_this.attr('checked') == 'checked' ){
                $('.class-list .checkOne[data-disabled=true]').attr('checked','checked')
            }else {
                $('.class-list .checkOne[data-disabled=true]').removeAttr('checked')
            }
        })
        function setDate(id) {
            var today = new Date(),
                y = today.getFullYear(),
                m = today.getMonth() + 1,
                d = today.getDate(),
                w = today.getDay(),
                h = today.getHours(),
                mi = today.getMinutes(),
                ms = today.getSeconds(),
                millisecond = 1000 * 60 * 60 * 24;
            if (id == 'upMonth') {
                var y1 = y,
                    m1 = m
                if (m == 1) {
                    y1 = y - 1
                    m1 = 12
                } else {
                    m1 = m - 1
                }
                m1 = PrefixInteger(m1, 2)
                var days1 = new Date(y1, m1, 0).getDate()
                vals = [y1 + '-' + m1 + '-01', y1 + '-' + m1 + '-' + days1]
            } else if (id == 'yesterday') {
                var yesterDay = new Date(today.getTime() - millisecond);
                vals = [dateFormat(yesterDay), dateFormat(yesterDay)]
            } else if (id == 'today') {
                vals = [y + '-' + m + '-' + d, y + '-' + m + '-' + d]
            } else if (id == 'curWeek') {
                var minusDay = w != 0 ? w - 1 : 6;
                var monday = new Date(today.getTime() - (minusDay * millisecond));
                var sunday = new Date(monday.getTime() + (6 * millisecond));
                vals = [dateFormat(monday), y + '-' + m + '-' + d]
            } else if (id == 'curMonth') {
                vals = [y + '-' + m + '-01', y + '-' + m + '-' + d]
            } else if (id == 'all') {
                vals = ['2019-02-27', y + '-' + m + '-' + d]
            }else if (id == 'curTime') {
                vals = [y + '-' + m + '-' + d + ' ' + h + ':' + mi + ':' + ms, y + '-' + m + '-' + d]
            }
            return vals
        }

        function dateFormat(time, format) {
            var t = new Date(time);
            var format = format || 'yyyy-MM-dd'
            var tf = function (i) {
                return (i < 10 ? '0' : '') + i
            };
            return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
                switch (a) {
                    case 'yyyy':
                        return tf(t.getFullYear());
                        break;
                    case 'MM':
                        return tf(t.getMonth() + 1);
                        break;
                    case 'mm':
                        return tf(t.getMinutes());
                        break;
                    case 'dd':
                        return tf(t.getDate());
                        break;
                    case 'HH':
                        return tf(t.getHours());
                        break;
                    case 'ss':
                        return tf(t.getSeconds());
                        break;
                }
            })
        };

        function PrefixInteger(num, m) {
            return (Array(m).join(0) + num).slice(-m);
        }
        layui.use(['layer','laydate','jquery'],function () {
            var layer = layui.layer,
                laydate = layui.laydate,
                layui$ = layui.jquery

            var initVals = setDate('curTime')
            startTime = laydate.render({
                elem: '#startTime',
                type: 'datetime',
                btns: ['now', 'confirm'],
                value: initVals[0],
            });

            $('body').on('blur','.claimMoney',function () {
                var _this =$(this)
                if (Number(_this.val()) > Number(_this.attr('max'))){
                    // _this.val(_this.attr('max'))
                    layer.msg('最大不可超过未到账金额',{icon: 6});
                }
            }).on('keyup','.claimMoney',function (e) {
                var _this =$(this)
                if (e.keyCode == 13){
                    var _this =$(this)
                    if (Number(_this.val()) > Number(_this.attr('max'))){
                        // _this.val(_this.attr('max'))
                        layer.msg('最大不可超过未到账金额',{icon: 6});
                    }
                }
            })

            $('.submit').click(function () {
                var checkList = $('.claimMoney')
                var idMs = []
                var _flag= true
                checkList.map(function (i,cur) {
                    if ($(cur).val() && Number($(cur).val()) >0){
                        if (Number($(cur).val()) > Number($(cur).attr('max'))){
                            _flag = false
                        }
                        idMs.push({
                            billingId: $(cur).attr('data-billingId'),
                            billingImgsId: $(cur).attr('data-billingImgsId'),
                            receiveMoney: $(cur).val()
                        })
                    }
                })
                if (!_flag){
                    layer.msg('最大不可超过未到账金额',{icon: 6});
                    return;
                }
                if (idMs.length){
                    $('.submit').addClass('poi-no')
                    layui$.ajax({
                        url: '${ctx}/billingApplyUnmatch/update',
                        data: {
                            meunCode: 'claim',
                            billingReceiveInfoList: JSON.stringify(idMs),
                            billingMatchId:$('#unmatchId').val(),
                            startTime: $("#startTime").val(),
                            apply:$("#apply").val()
                        },
                        success: function (res) {
                            res = JSON.parse(res)
                            if (res.isSuccess){
                                layer.msg(res.msg,{icon: 1},function () {
                                    location.reload()
                                })
                            }else {
                                layer.msg(res.results,{icon: 5})
                                $('.submit').removeClass('poi-no')
                            }
                        }

                    })

                }else {
                    $('.submit').removeClass('poi-no')
                    layer.msg('至少勾选一行数据',{icon: 5});
                }
            })
        })
    })

</script>
</body>
</html>
