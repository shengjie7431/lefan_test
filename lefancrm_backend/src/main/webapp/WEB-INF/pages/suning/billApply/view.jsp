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
    <input type="hidden" name="id" value="${id}">
    <div class="title">
        <c:if test="${menuType == 5}">
            <c:if test="${billingApplyInfo.billingState == 3 && billingApplyInfo.orgManager}">
                <button class="butList active" onclick="deal('${id}','retreat','${menuType}');">退票</button>
                <button class="butList active" onclick="deal('${id}','renew','${menuType}');">重新开票</button>
            </c:if>
            <c:if test="${billingApplyInfo.billingState == 5 && billingApplyInfo.boss}">
                <button class="butList active" onclick="deal('${id}','retreatPass','${menuType}');">审核通过</button>
                <button class="butList active" onclick="deal('${id}','back','${menuType}');">退回</button>
            </c:if>
            <c:if test="${billingApplyInfo.billingState == 6 && billingApplyInfo.boss}">
                <button class="butList active" onclick="deal('${id}','renewPass','${menuType}');">审核通过</button>
                <button class="butList active" onclick="deal('${id}','back','${menuType}');">退回</button>
            </c:if>
        </c:if>
        <c:if test="${menuType != 5}">
            <c:if test="${billingApplyInfo.billingState == 2}">
                <c:if test="${billingApplyInfo.billingMoney < 0 && billingApplyInfo.billingItem != 12}">
                    <button class="butList active" onclick="billApplyStateUp('${id}',4);">退票</button>
                </c:if>
                <c:if test="${billingApplyInfo.billingMoney >= 0 || billingApplyInfo.billingItem == 12}">
                    <%--<c:if test="${billingApplyInfo.billingItem == 12}">--%>
                        <%--<button class="butList active" onclick="uploadBillApplyImgYhc('${billingApplyInfo.id}','${billingApplyInfo.caseNo}','yhc');">确认红冲</button>--%>
                    <%--</c:if>--%>
                    <c:if test="${billingApplyInfo.billingItem != 12}">
                        <button class="butList active" onclick="uploadBillApplyImg('${billingApplyInfo.id}','${billingApplyInfo.caseNo}');">确认开票</button>
                    </c:if>
                </c:if>
                <c:if test="${billingApplyInfo.billingMoney == null && billingApplyInfo.billingItem != 12}">
                    <button class="butList active" onclick="uploadBillApplyImg('${billingApplyInfo.id}','${billingApplyInfo.caseNo}');">确认开票</button>
                </c:if>
            </c:if>
            <c:if test="${billingApplyInfo.billingState == 3}">
                <%--<c:if test="${billingApplyInfo.billingItem == 12}">--%>
                    <%--<button class="butList active" onclick="uploadBillApplyImgYhc('${billingApplyInfo.id}','${billingApplyInfo.caseNo}','yhc');">确认红冲</button>--%>
                <%--</c:if>--%>
                <c:if test="${billingApplyInfo.billingItem != 12}">
                    <button class="butList active" onclick="uploadBillApplyImg('${billingApplyInfo.id}','${billingApplyInfo.caseNo}');">确认开票</button>
                </c:if>
            </c:if>
            <c:if test="${billingApplyInfo.billingState == 4}">
                <button class="butList defuelt" onclick="">已退票</button>
            </c:if>
            <c:if test="${billingApplyInfo.billingState == 5}">
                <button class="butList defuelt" onclick="">退票审核中</button>
            </c:if>
            <c:if test="${billingApplyInfo.billingState == 6}">
                <button class="butList defuelt" onclick="">重开审核中</button>
            </c:if>
            <c:if test="${billingApplyInfo.billingState == 7}">
                <button class="butList active" onclick="deal('${billingApplyInfo.id}','confirmRetreat',6);">确认退票</button>
            </c:if>
            <c:if test="${billingApplyInfo.billingState == 8}">
                <button class="butList active" onclick="deal('${billingApplyInfo.id}','confirmNew',6);">确认重开</button>
            </c:if>
            <c:if test="${billingApplyInfo.billingState == 9}">
                <button class="butList active" onclick="billApplyStateUp('${id}',4);">退票</button>
            </c:if>
            <c:if test="${billingApplyInfo.billingEnum != 7 && billingApplyInfo.confirmAccountState != 2}">
                <button class="butList active" onclick="confirmAccount('${billingApplyInfo.id}');">公估确认到账</button>
            </c:if>
        </c:if>
        <c:if test="${menuType == 2}">
            <button class="butList active" onclick="PGpass('${id}','PGpass','${menuType}');">确认通过</button>
        </c:if>
        <c:if test="${menuType == 3}">
            <button class="butList defuelt" onclick="uploadBillApplyImgYhc('${billingApplyInfo.id}','${billingApplyInfo.caseNo}','yhc');">确认红冲</button>
        </c:if>
        <%--<c:if test="${billingApply.billingState == 1}">--%>
            <%--<button class="butList defuelt" onclick="">已开票</button>--%>
        <%--</c:if>--%>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>

                <tr>
                    <td>案件编号</td>
                    <td>${billingApplyInfo.caseNo}</td>
                    <td>开票产品</td>
                    <td>
                        <c:forEach items="${bullingEnums}" var="dto">
                            <c:if test="${dto.enumCode == billingApplyInfo.billingEnum}">
                                ${dto.enumName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>开票项目</td>
                    <td>
                        <c:forEach items="${bullingItems}" var="dto">
                            <c:if test="${dto.enumCode == billingApplyInfo.billingItem}">
                                ${dto.enumName}
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td>开票状态</td>
                    <td>
                        <c:if test="${billingApplyInfo.billingItem != 12}">
                            <c:if test="${billingApplyInfo.billingState == 1}">未申请</c:if>
                            <c:if test="${billingApplyInfo.billingState == 2}">已申请</c:if>
                            <c:if test="${billingApplyInfo.billingState == 3}">已开票</c:if>
                            <c:if test="${billingApplyInfo.billingState == 4}">已退票</c:if>
                            <c:if test="${billingApplyInfo.billingState == 5}">退票审核中</c:if>
                            <c:if test="${billingApplyInfo.billingState == 6}">重开审核中</c:if>
                            <c:if test="${billingApplyInfo.billingState == 7}">退票审核通过</c:if>
                            <c:if test="${billingApplyInfo.billingState == 8}">重开审核通过</c:if>
                            <c:if test="${billingApplyInfo.billingState == 9}">退票中</c:if>
                        </c:if>
                        <c:if test="${billingApplyInfo.billingItem == 12}">
                            <c:if test="${billingApplyInfo.billingState == 2}">待处理</c:if>
                            <c:if test="${billingApplyInfo.billingState != 2}">已处理</c:if>
                        </c:if>
                    </td>
                    <td>所属公司</td>
                    <td>
                        <c:forEach items="${corporations}" var="dto">
                            <c:if test="${dto.id == billingApplyInfo.businessType}">
                                ${dto.name}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>所属机构</td>
                    <td>${billingApplyInfo.orgName}</td>
                </tr>
                <tr>
                    <td>开票类型</td>
                    <td>
                        <c:if test="${billingApplyInfo.billingType == 1}">专票</c:if>
                        <c:if test="${billingApplyInfo.billingType == 2}">普票</c:if>
                        <c:if test="${billingApplyInfo.billingType == 3}">电子普票</c:if>
                    </td>
                    <td>开票金额</td>
                    <td>${billingApplyInfo.billingMoney}</td>
                    <td>案件标题</td>
                    <td>${billingApplyInfo.caseTitle}</td>
                </tr>
                <tr>
                    <td>服务费</td>
                    <td>${billingApplyInfo.servcieMoney == null ? 0.0 : billingApplyInfo.servcieMoney}</td>
                    <td>通道费</td>
                    <td>${billingApplyInfo.channelMoney == null ? 0.0 : billingApplyInfo.channelMoney}</td>
                    <td>扣费金额</td>
                    <td>${billingApplyInfo.deductionMoney == null ? 0.0 : billingApplyInfo.deductionMoney}</td>
                </tr>
                <tr>
                    <td>车牌号</td>
                    <td>${billingApplyInfo.carNo}</td>
                    <td>伤者姓名</td>
                    <td>${billingApplyInfo.woundedName}</td>
                    <td>开票人</td>
                    <td>${billingApplyInfo.billingBy}</td>
                </tr>
                <tr>
                    <td>开票时间</td>
                    <td><fmt:formatDate value="${billingApplyInfo.billingTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${billingApplyInfo.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    <td>创建人</td>
                    <td>${billingApplyInfo.createBy}</td>
                </tr>
                <tr>
                    <td>被保险人姓名</td>
                    <td>${billingApplyInfo.insuredName}</td>
<%--                    <td>绩效所属</td>--%>
<%--                    <td>${billingApplyInfo.meritName}</td>--%>
                    <td></td>
                    <td></td>
                    <td>备注</td>
                    <td>${billingApplyInfo.remark}</td>
                </tr>
                <c:if test="${menuType == 5 && billingApplyInfo.operReason != null}">
                    <tr>
                        <td>退回原因</td>
                        <td colspan="5">${billingApplyInfo.operReason}</td>
                    </tr>
                </c:if>
                <c:if test="${billingApplyInfo.rejectReason != null}">
                    <tr>
                        <td>驳回申请原因</td>
                        <td colspan="5">${billingApplyInfo.rejectReason}</td>
                    </tr>
                </c:if>
                <c:if test="${billingApplyInfo.billingEnum != 7}">
                    <tr>
                        <td>公估到账金额</td>
                        <td>${billingApplyInfo.confirmAccountMoney}</td>
                        <td>公估到账状态</td>
                        <td>
                            <c:if test="${billingApplyInfo.confirmAccountState == 1}">未到账</c:if>
                            <c:if test="${billingApplyInfo.confirmAccountState == 2}">已到账</c:if>
                        </td>
                        <td>公估到账时间</td>
                        <td><fmt:formatDate value="${billingApplyInfo.confirmAccountTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    </tr>
                </c:if>
                <c:forEach items="${apiRsp.results}" var="item">
                    <tr>
                        <td>发票单号</td>
                        <td>${item.billingCode}</td>
                        <td>发票金额</td>
                        <td>${item.billingMoney}</td>
                        <td>发票凭证</td>
                        <td>
                            <div class="div1"><img src="${item.billingImgs}" width="75;" height="75;" class="picToBig"></div>
                            <div class="div1">
                            <c:if test="${menuType == 3}">
                                <c:if test="${billingApplyInfo.billingState == 3}">
                                    <c:if test="${item.state == 1}">
                                        <button class="butList defuelt" onclick="billApplyImgsDelete('${item.id}','billingImgs');">删除</button>
                                        <button class="butList defuelt" onclick="billApplyImgsUpd('${item.id}',2);">作废</button>
                                    </c:if>
                                    <c:if test="${item.state == 2}">
                                        <div class="pad5">
                                            <button class="butList defuelt" onclick="">已作废</button>
                                            <button class="butList defuelt" onclick="billApplyImgsDelete('${item.id}','billingImgs');">删除</button>
                                        </div>
                                        <div class="pad5">
                                            作废时间：<fmt:formatDate value="${item.stateUpdateTime}" pattern="yyyy-MM-dd HH:mm" />
                                        </div>
                                    </c:if>
                                    <c:if test="${item.state == 3}">
                                        <div class="pad5">
                                            <button class="butList defuelt" onclick="">已红冲</button>
                                            <button class="butList defuelt" onclick="billApplyImgsDelete('${item.id}','billingImgs');">删除</button>
                                        </div>
                                        <div class="pad5">
                                            红冲时间：<fmt:formatDate value="${item.stateUpdateTime}" pattern="yyyy-MM-dd HH:mm" />
                                        </div>
                                    </c:if>
                                    <c:if test="${item.state == 4}">
                                        <div class="pad5">
                                            <button class="butList defuelt" onclick="uploadBillApplyYhc('${item.id}','yhc',4);">待红冲</button>
                                            <button class="butList defuelt" onclick="billApplyImgsDelete('${item.id}','billingImgs');">删除</button>
                                        </div>
                                    </c:if>
                                    <c:if test="${item.state == 5}">
                                        <div class="pad5">
                                            <button class="butList defuelt" onclick="uploadBillApplyYhc('${item.id}','yhc',5);">待开票</button>
                                            <button class="butList defuelt" onclick="billApplyImgsDelete('${item.id}','billingImgs');">删除</button>
                                        </div>
                                    </c:if>
                                </c:if>
                            </c:if>
                            </div>

                        </td>
                    </tr>
                </c:forEach>
                <c:forEach items="${apiRsp2.results}" var="item">
                    <tr>
                        <td>材料凭证</td>
                        <td colspan="5"><img src="${item.materialImgs}" width="75;" height="75;" class="picToBig"></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
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
<script type="text/javascript">
    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
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
     * 开票页面
     */
    function uploadBillApplyImg(id,caseNo,yhc) {
        openDialog({
            frame: true,
            title: "开票页面",
            height: 350,
            width: 650,
            url: "${ctx}/suning/billApply/uploadBillApplyImg?id="+ id + "&caseNo="+caseNo
        });
    }
    function uploadBillApplyImgYhc(id,caseNo,yhc) {
        openDialog({
            frame: true,
            title: "开票页面",
            height: 350,
            width: 650,
            url: "${ctx}/suning/billApply/uploadBillApplyImg?id="+ id + "&caseNo="+caseNo+"&yhc=" + yhc
        });
    }

    /**
     * 修改开票金额
     */
    function PGpass(id,model,menuType) {
        openDialog({
            frame: true,
            title: "修改开票金额",
            height:150,
            width:600,
            url: "${ctx}/suning/billApply/PGpass?id="+ id + "&menuType="+menuType+"&model="+model
        });
    }
    function deal(id,model,menuType){
        //弹出界面
        if(model == 'back'){
            openDialog({
                frame: true,
                title: "退回",
                height: 350,
                width: 650,
                url: "${ctx}/billingApply/billApplyStateUpBack?id="+id+"&menuType="+menuType+"&model="+model
            });
        }else{
            var url = "${ctx}/billingApply/billApplyStateUp",param = {"id":id,"model":model,"menuType":menuType};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    alert(e.data.msg);
                    reloadParent();
                })
            }
        }
    }

    /**
     * 提交退票
     */
    function billApplyStateUp(id,billingState){
        ajaxSubmit("${ctx}/billingApply/billApplyStateUp",{"id":id, "billingState":billingState},reload,"提交成功！","确认提交？","提交失败！");
    }

    /**
     * 删除单条发票记录
     */
    function billApplyImgsDelete(id,type){
        ajaxSubmit("${ctx}/billingApply/billApplyImgsDelete",{"id":id,"type":type},reload,"删除成功！","确认删除？","删除失败！");
    }

    /**
     * 公估确认到账
     */
    function confirmAccount(id) {
        openDialog({
            frame: true,
            title: "公估确认到账",
            height:150,
            width:600,
            url: "${ctx}/billingApply/confirmAccount?id="+ id
        });
    }

    /**
     * 发票作废或红冲
     */
    function billApplyImgsUpd(id,billApplyImgsState){
        ajaxSubmit("${ctx}/billingApply/billApplyImgsUpd",{"id":id, "billApplyImgsState":billApplyImgsState},reload,"提交成功！","确认提交？","提交失败！");
    }

    /**
     * 待红冲，待开票
     */
    function uploadBillApplyYhc(id,yhc,type) {
        openDialog({
            frame: true,
            title: "开票页面",
            height: 350,
            width: 650,
            url: "${ctx}/suning/billApply/uploadBillApplyImg?id="+ id +"&yhc=" + yhc + "&type="+type
        });
    }
</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>