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
        .ellipsis {
             overflow: hidden; /*自动隐藏文字*/
             text-overflow: ellipsis;/*文字隐藏后添加省略号*/
             white-space: nowrap;/*强制不换行*/
             width: 16em;/*不允许出现半汉字截断*/
         }
    </style>
</head>
<body>

<div class="main">
    <input type="hidden" name="id" value="${surveyCashInfo.id}">
    <input type="hidden" name="surveyCode" value="${surveyCode}">
    <div class="title">
        <c:if test="${surveyCashInfo.cashState == 2}">
            <%--<button class="butList active" onclick="operate('${surveyCashInfo.id}','${surveyCode}','1200',false);">在线提现</button>--%>
            <button class="butList active" onclick="operate('${surveyCashInfo.id}','${surveyCode}','1300',false);">提现</button>
            <button class="butList active" onclick="operate('${surveyCashInfo.id}','${surveyCode}','1400',false);">驳回提现</button>
        </c:if>
        <c:if test="${surveyCashInfo.cashState == 3}">
            <button class="butList defuelt" onclick="">提现成功</button>
            <c:if test="${surveyCashInfo.confirmAccountState == 1}">
                <button class="butList defuelt" onclick="">未确认到账</button>
            </c:if>
            <c:if test="${surveyCashInfo.confirmAccountState == 2}">
                <button class="butList defuelt" onclick="">已确认到账</button>
            </c:if>
        </c:if>
        <c:if test="${surveyCashInfo.cashState == 4}">
            <button class="butList defuelt" onclick="">提现失败</button>
        </c:if>
        <%--<c:if test="${surveyCashInfo.cashState == 3 && surveyCashInfo.confirmAccountState == 1 && (surveyCashInfo.orgMan || surveyCashInfo.lfYuying) }">--%>
        <c:if test="${surveyCashInfo.cashState == 3 && surveyCashInfo.confirmAccountState == 1 && (surveyCashInfo.orgMan) }">
            <button class="butList active" onclick="operate('${surveyCashInfo.id}','${surveyCode}','1500',true);">确认到账</button>
        </c:if>

    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>提现名称</td>
                    <td><div class="ellipsis" title="${surveyCashInfo.cashInfoName}">
                            ${surveyCashInfo.cashInfoName}
                        </div>
                    </td>
                    <td>提现单号</td>
                    <td>${surveyCashInfo.cashInfoCode}</td>
                    <td>提现机构</td>
                    <td>${surveyCashInfo.franchiseeName}</td>
                </tr>
                <tr>
                    <td>申请人姓名</td>
                    <td>${surveyCashInfo.applyUserName}</td>
                    <td>申请人电话</td>
                    <td>${surveyCashInfo.applyUserTel}</td>
                    <td>申请时间</td>
                    <td><fmt:formatDate value="${surveyCashInfo.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <td>提现金额</td>
                    <td>${surveyCashInfo.caseAmount}</td>
                    <td>手续费比例</td>
                    <td>${surveyCashInfo.feeRatio}%</td>
                    <td>手续费金额</td>
                    <td>${surveyCashInfo.feeAmount}</td>
                </tr>
                <tr>
                    <td>实际到账金额</td>
                    <td>${surveyCashInfo.realAmount}</td>
                    <td>到账状态</td>
                    <td>
                        <c:if test="${surveyCashInfo.confirmAccountState == 1}">未到账</c:if>
                        <c:if test="${surveyCashInfo.confirmAccountState == 2}">已到账</c:if>
                    </td>
                    <td>交易渠道</td>
                    <td>
                        <c:if test="${surveyCashInfo.paySource == 1}">第三方支付</c:if>
                        <c:if test="${surveyCashInfo.paySource == 2}">福优</c:if>
                        <c:if test="${surveyCashInfo.paySource == 3}">微信企业付款</c:if>
                        <c:if test="${surveyCashInfo.paySource == 4}">乐凡企业打款</c:if>
                    </td>
                </tr>
                <tr>
                    <td>提现状态</td>
                    <td>
                        <c:if test="${surveyCashInfo.cashState == 1}">待结算</c:if>
                        <c:if test="${surveyCashInfo.cashState == 2}">支付中</c:if>
                        <c:if test="${surveyCashInfo.cashState == 3}">已支付</c:if>
                        <c:if test="${surveyCashInfo.cashState == 4}">支付失败</c:if>
                    </td>
                    <td>支付失败原因</td>
                    <td colspan="3">${surveyCashInfo.reason}</td>
                </tr>
                <tr>
                    <td>交易类型</td>
                    <td>
                        <c:if test="${surveyCashInfo.tradeType == 1}">线上交易</c:if>
                        <c:if test="${surveyCashInfo.tradeType == 2}">线下交易</c:if>
                    </td>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${surveyCashInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>提现凭证</td>
                    <td>
                        <c:if test="${surveyCashInfo.unlineImg !=null}">
                            <img src="${surveyCashInfo.unlineImg}" width="75px;" height="75px;" class="picToBig">
                        </c:if>
                    </td>
                </tr>
                <%--<tr>--%>
                    <%----%>
                    <%--<td>交易备注</td>--%>
                    <%--<td colspan="3">${surveyCashInfo.tradeDesc}</td>--%>
                <%--</tr>--%>
                </tbody>
            </table>
        </div>

        <c:if test="${surveyBankCard != null}">
            <div>
                <div class="table-title">银行卡信息</div>
                <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr>
                        <td>卡号</td>
                        <td>${surveyBankCard.cardNo}</td>
                        <td>持卡人</td>
                        <td>${surveyBankCard.holderName}</td>
                        <td>开户行</td>
                        <td>${surveyBankCard.bankName}</td>
                    </tr>
                    <tr>
                        <td>开户支行</td>
                        <td>${surveyBankCard.bankBranchName}</td>
                        <td>图片</td>
                        <td colspan="3">
                            <c:if test="${surveyBankCard.cardImg !=null}">
                                <img src="${surveyBankCard.cardImg}" width="75;" height="75;" class="picToBig">
                            </c:if>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </c:if>

        <c:if test="${surveyCashInfoDetail != null}">
            <div class="table-title">提现明细</div>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="100">调查编号</th>
                    <th width="100">被调查人</th>
                    <th width="100">联系号码</th>
                    <th width="100">调查员姓名</th>
                    <th width="50">价格</th>
                    <th width="100">操作</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${surveyCashInfoDetail}" var="item">
                    <tr>
                        <td>${item.surveyNo}</td>
                        <td>${item.surveyPerson}</td>
                        <td>${item.surveyPersonTel}</td>
                        <td>${item.surveyUserName}</td>
                        <td>${item.surveyTaskMoney}</td>
                        <td>
                            <button class="butList defuelt" onclick="info(${item.surveyInvestigatorCaseId})">详情</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${surveyCashInfoDetail.size() == 0}">
            <div class="stepItem" style="margin-top: 10px;text-align:center;color: #ff0000;">
                暂无明细
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
     *
     */
    function operate(id,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"id":id,"surveyCode":surveyCode,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    if(btnCode == '9999'){
                        reloadParent();//删除时，需刷新父级
                    }else{
                        location.reload();
                    }
                })
            }
        }else {
            var title = null, url = null;
            if(btnCode == '1300' || btnCode == '1200'){
                height = 500;
                width = 800;
                title = '提现';
                url = "${ctx}/baseSurvey/popup?id="+id+"&surveyCode="+surveyCode+"&btnCode="+btnCode
            }else if(btnCode == '1400'){
                height = 500;
                width = 800;
                title = '驳回提现';
                url = "${ctx}/baseSurvey/popup?id="+id+"&surveyCode="+surveyCode+"&btnCode="+btnCode
            }
            openDialog({
                frame:true,
                title:title,
                height:height,
                width:width,
                url:url
            });
        }
    }
    var info = function(id){
        openDialog({
            frame:true,
            title:"详情",
            height:700,
            width:1100,
            url:"${ctx}/survey/case/sic/info?id=" + id + "&menuCode=cash-info",
            load:true
        });
    }
</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>