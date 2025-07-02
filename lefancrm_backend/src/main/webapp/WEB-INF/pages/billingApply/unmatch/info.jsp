<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
    <script>

    </script>

    <style>
        .table-title{
            font-size: 14px;
            font-weight: bold;
            line-height: 40px;
        }
        .table-main{
            padding: 20px 8px;
            border: 1px solid #eee;
        }
        table.spec-info tbody tr td:nth-of-type(2n + 1) {
            width: 10%;
        }
        table.spec-info tbody tr td:nth-of-type(2n) {
            width: 20%;
        }
        table.spec-info tbody tr td:nth-of-type(6n) {
            width: 30%;
        }
        .div1{
            float: left;
        }
        .div1 button{
            margin: 0 1px;
            padding: 0 9px;
        }
        td div.pad5{
            padding: 5px;
        }
    </style>
</head>
<body>

<div class="main">
    <input type="hidden" name="id" value="${info.id}">
    <div class="title">
        <c:if test="${info.state !=2}">
            <c:if test="${france}">
                <button class="butList active" onclick="edit('${info.id}');">修改</button>
                <button class="butList active" onclick="claimTwo('${info.id}','${info.money}');">已开票认领</button>
                <c:if test="${info.unmatchMoney != null and info.unmatchMoney>0}">
                    <button class="butList active" onclick="claimFour('${info.id}','${info.unmatchMoney}','false');">退费</button>
                </c:if>
            </c:if>
            <c:if test="${haveMenu}">
                <button class="butList active" onclick="claimThree('${info.id}','${info.money}');">申请认领</button>
                <c:if test="${info.unmatchMoney != null and info.unmatchMoney>0}">
                    <button class="butList active" onclick="claimFour('${info.id}','${info.unmatchMoney}','true');">申请退费</button>
                </c:if>
            </c:if>
        </c:if>
        <c:if test="${info.state ==2}">
            <button class="butList defuelt">已认领</button>
        </c:if>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>编号</td>
                    <td>${info.unmatchNo}</td>
                    <td>金额</td>
                    <td>${info.money}</td>
                    <td>交易时间</td>
                    <td><fmt:formatDate value="${info.payTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                </tr>
                <tr>
                    <td>状态</td>
                    <td>
                        <c:if test="${info.state == 1}">未认领</c:if>
                        <c:if test="${info.state == 2}">已认领</c:if>
                        <c:if test="${info.state == 3}">部分认领</c:if>
                    </td>
                    <td>已认领金额</td>
                    <td>${info.matchMoney == null?0:info.matchMoney}</td>
                    <td>未认领金额</td>
                    <td>${info.unmatchMoney== null?0:info.unmatchMoney}</td>
                </tr>
                <tr>
                    <td>付款方</td>
                    <td>${info.payer}</td>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${info.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    <td>创建人</td>
                    <td>${info.createBy}</td>
                </tr>
                <tr>
                    <td>附言</td>
                    <td>${info.remark}</td>
                    <td>到账公司</td>
                    <td>${info.receivingCompanyName}</td>
                    <td></td>
                    <td></td>
<%--                    <td>开票产品</td>--%>
<%--                    <td>${info.billingItemsName}</td>--%>
                </tr>
                <tr>
                    <td>备注</td>
                    <td colspan="5">${info.comments}</td>
                </tr>
                <tr>
                    <td>退费金额</td>
                    <td colspan="5">${info.refundMoney}</td>
                </tr>
                <c:if test="${info.state ==2}">
                    <tr>
                        <td>开票编号</td>
                        <td>${info.billNo}</td>
                        <td>认领人</td>
                        <td>${info.claimBy}</td>
                        <td>认领时间</td>
                        <td><fmt:formatDate value="${info.claimTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
        <div class="table-title">开票认领</div>
        <div class="table-main">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th width="100">案件编号</th>
                    <th width="100">案件标题</th>
                    <th width="100">开票产品</th>
                    <th width="100">开票项目</th>
                    <th width="100">发票单号</th>
                    <th width="100">开票金额</th>
                    <th width="100">开票对象</th>
                    <th width="100">开票时间</th>
                    <th width="100">收入归属机构</th>
                    <th width="100">认领金额</th>
                    <th width="150">认领人</th>
                    <th width="100">认领时间</th>
                    <th width="100">申请认领状态</th>
                    <th width="100">操作</th>
                </tr>
                </thead>
                <tbody class="class-list">
                    <c:forEach  items="${BillingApplyDtoList}" var="item">
                        <tr>
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
                            <td>${item.theAccountMoney}</td>
                            <td>${item.receiveUserName}</td>
                            <td><fmt:formatDate value="${item.receiveTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                            <td>
                                <c:if test="${item.receiveType == null || item.receiveType == 1}">无</c:if>
                                <c:if test="${item.receiveType == 2}">
                                    <c:if test="${item.receiveStatus == 1}">待财务审核</c:if>
                                    <c:if test="${item.receiveStatus == 2}">财务审核通过</c:if>
                                    <c:if test="${item.receiveStatus == 3}">财务审核驳回</c:if>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${item.receiveType == 2}">
                                    <c:if test="${item.receiveStatus == 1}">
                                        <c:if test="${france}">
                                            <a onclick="operate(${item.receiveId},'pass')">审核通过</a>
                                            <a onclick="operate(${item.receiveId},'reject')">驳回</a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${item.receiveStatus == 3}">
                                        <c:if test="${item.receiveUserId == currentUserId}">
                                            <input type="hidden" id="rejectReason" value="${item.rejectReason}">
                                            <a class="viewReason">驳回原因</a>
                                            <a onclick="operate(${item.receiveId},'re')">重新发起申请</a>
                                            <a onclick="operate(${item.receiveId},'del')">删除</a>
                                        </c:if>
                                    </c:if>
                                </c:if>
                                <c:if test="${france}">
                                    <a onclick="operate(${item.receiveId},'chexiao')">撤销认领</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <c:if test="${billingRefundInfoDtoList != null and billingRefundInfoDtoList.size()>0}">
            <div class="table-title">退费详情</div>
            <div class="table-main">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th width="100">退费金额</th>
                        <th width="100">收款人</th>
                        <th width="100">银行卡号</th>
                        <th width="100">银行名称</th>
                        <th width="100">银行支行</th>
                        <th width="100">退款发起者</th>
                        <th width="100">创建时间</th>
                        <th width="100">申请退费状态</th>
                        <th width="100">操作</th>
                    </tr>
                    </thead>
                    <tbody class="class-list">
                    <c:forEach  items="${billingRefundInfoDtoList}" var="item">
                        <tr>
                            <td>${item.refundMoney}</td>
                            <td>${item.payee}</td>
                            <td>${item.bankCarNo}</td>
                            <td>${item.bankName}</td>
                            <td>${item.bankBranch}</td>
                            <td>${item.refundUserName}</td>
                            <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                            <c:if test="${item.refundType == null || item.refundType == 1}">
                                <td>无</td>
                                <td></td>
                            </c:if>
                            <c:if test="${item.refundType == 2}">
                                <c:if test="${item.refundStatus == 1}">
                                    <td>待财务审核</td>
                                    <td>
                                        <c:if test="${france}">
                                            <a onclick="operate2(${item.id},'pass')">审核通过</a><a
                                                onclick="operate2(${item.id},'reject')">驳回</a>
                                        </c:if>
                                    </td>
                                </c:if>
                                <c:if test="${item.refundStatus == 2}"><td>财务审核通过</td><td></td></c:if>
                                <c:if test="${item.refundStatus == 3}">
                                    <td>财务审核驳回</td>
                                    <td>
                                        <c:if test="${item.refundUserId == currentUserId}">
                                            <input type="hidden" id="refundReason" value="${item.refundReason}">
                                            <a class="viewReason2">驳回原因</a>
                                            <a onclick="operate2(${item.id},'re')">重新发起申请</a>
                                            <a onclick="operate2(${item.id},'del')">删除</a>
                                        </c:if>
                                    </td>
                                </c:if>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </div>
</div>

<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/layui/layui.js"></script>
<script type="text/javascript">
    console.log(11111111111)
    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select',
        soulTable: 'soulTable'
    })
    layui.use(['form', 'laydate', 'jquery', 'xmSelect', 'layer','table','soulTable'], function () {
        var form = layui.form,
            $ = layui.jquery,
            layer = layui.layer

        $('.viewReason').click(function () {
            layer.alert($(this).prev().val(),{ title: '驳回原因'})
        })
        $('.viewReason2').click(function () {
            layer.alert($(this).prev().val(),{ title: '驳回原因'})
        })
    });
    /**
     * 关闭dialog
     */
    function closeDialog(){
        var closeBtn = $("#diglog_close_btn");
        if(closeBtn.size() == 0){
            closeBtn = $("#diglog_close_btn",window.parent.document);
        }
        closeBtn.click();
    }

    /**
     * 修改
     */
    function edit(id) {
        openDialog({
            frame: true,
            title: "修改页面",
            height: 500,
            width: 800,
            url: "${ctx}/billingApplyUnmatch/edit?id="+ id
        });
    }

    /**
     * 金额详情
     */
    function amountDetails(billingMatchId,receiveTime){
        var _height = $(window).height() * 0.98
        var _width = $(document).width() * 0.98
        openDialog({
            frame: true,
            title: "已开票认领",
            height: _height,
            width: _width,
            url: "${ctx}/billingApplyUnmatch/billingList?unmatchId="+ billingMatchId +"&receiveTime="+receiveTime+"&menuType=101&muenCode=amountDetails"
        });
    }

    /**
     * 认领
     */
    function claim(id,money) {
        openDialog({
            frame: true,
            title: "认领",
            height: 650,
            width: 800,
            url: "${ctx}/billingApply/billingApplyAdd?unmatchId="+ id +"&money="+money +"&claim="+1+"&billingSource=6"
        });
    }

    /**
     * 已开票认领
     */
    function claimTwo(id,money) {
        var _height = $(window).height() * 0.98
        var _width = $(document).width() * 0.98
        openDialog({
            frame: true,
            title: "已开票认领",
            height: _height,
            width: _width,
            load:true,
            url: "${ctx}/billingApplyUnmatch/billingList?unmatchId="+ id +"&money="+money+"&menuType=101&muenCode=claimByInvoice"

        });
    }

    /**
     * 申请认领
     */
    function claimThree(id,money) {
        var _height = $(window).height() * 0.98
        var _width = $(document).width() * 0.98
        openDialog({
            frame: true,
            title: "申请认领",
            height: _height,
            width: _width,
            load:true,
            url: "${ctx}/billingApplyUnmatch/billingList?unmatchId="+ id +"&money="+money+"&menuType=101&muenCode=claimByInvoice&apply=true"

        });
    }

    /**
     * 退费
     */
    function claimFour(id,money,type) {
        var _height = $(window).height() * 0.98
        var _width = $(document).width() * 0.98
        openDialog({
            frame: true,
            title: "退费",
            height: _height,
            width: _width,
            load:true,
            url: "${ctx}/billingApplyUnmatch/add?unmatchId="+ id +"&money="+money+"&btnCode=refund&apply="+type

        });
    }

    var flag = true
    function operate(receiveId,type) {
        if ('reject' == type){
            layer.prompt({title: "请输入驳回原因（必填项）", formType: 2}, function (text, index) {
                if(flag){
                    flag = false
                }else{
                    return;
                }
                $.ajax({
                    type:'post',
                    url:'${ctx}/billingApplyUnmatch/update',
                    data:{
                        meunCode:'unminfo',
                        receiveId:receiveId,
                        type:type,
                        rejectReason:text
                    },
                    success:function (res) {
                        layer.msg("成功");
                        setTimeout(function () {
                            location.reload()
                        },500)
                    }
                })
            });
        }else {
            layer.confirm('确认?',function () {
                if(flag){
                    flag = false
                }else{
                    return;
                }
                $.ajax({
                    type:'post',
                    url:'${ctx}/billingApplyUnmatch/update',
                    data:{
                        meunCode:'unminfo',
                        receiveId:receiveId,
                        type:type
                    },
                    success:function (res) {
                        layer.msg("成功");
                        setTimeout(function () {
                            location.reload()
                        },500)
                    }
                })
            });
        }
    }

    function operate2(id,type) {
        if ('reject' == type){
            layer.prompt({title: "请输入驳回原因（必填项）", formType: 2}, function (text, index) {
                if(flag){
                    flag = false
                }else{
                    return;
                }
                $.ajax({
                    type:'post',
                    url:'${ctx}/billingApplyUnmatch/update',
                    data:{
                        meunCode:'refundInfo',
                        id:id,
                        type:type,
                        rejectReason:text
                    },
                    success:function (res) {
                        layer.msg("成功");
                        setTimeout(function () {
                            location.reload()
                        },500)
                    }
                })
            });
        }else {
            layer.confirm('确认提交?',function () {
                if(flag){
                    flag = false
                }else{
                    return;
                }
                $.ajax({
                    type:'post',
                    url:'${ctx}/billingApplyUnmatch/update',
                    data:{
                        meunCode:'refundInfo',
                        id:id,
                        type:type
                    },
                    success:function (res) {
                        layer.msg("成功");
                        setTimeout(function () {
                            location.reload()
                        },500)
                    }
                })
            });
        }
    }

</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>