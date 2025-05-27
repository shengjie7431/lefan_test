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
</head>
<body>

<div class="main">
    <input type="hidden" name="id" value="${withdrawalsInfo.id}">
    <div class="title">
        <button class="butList defuelt" onclick="withdrawalsDetails('${withdrawalsInfo.userId}');">收支明细</button>
        <c:if test="${withdrawalsInfo.state ==2}">
            <button class="butList active" onclick="withdrawalsOnline('${withdrawalsInfo.id}');">在线提现</button>
            <button class="butList active" onclick="withdrawalsUnline('${withdrawalsInfo.id}');">线下提现</button>
        </c:if>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>提现单号</td>
                    <td>${withdrawalsInfo.widraCode}</td>
                    <td>用户名</td>
                    <td>${withdrawalsInfo.userName}</td>
                    <td>提现状态</td>
                    <td>
                        <c:if test="${withdrawalsInfo.state == 1}">待提现</c:if>
                        <c:if test="${withdrawalsInfo.state == 2}">提现中</c:if>
                        <c:if test="${withdrawalsInfo.state == 3}">提现成功</c:if>
                    </td>
                </tr>
                <tr>
                    <td>提现金额</td>
                    <td>${withdrawalsInfo.money}</td>
                    <td>微信号</td>
                    <td>${withdrawalsInfo.wechatId}</td>
                    <td>电话</td>
                    <td>${withdrawalsInfo.userTel}</td>
                </tr>
                <tr>
                    <td>机构</td>
                    <td>${withdrawalsInfo.orgName}</td>
                    <td>交易备注</td>
                    <td>${withdrawalsInfo.tradeDesc}</td>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${withdrawalsInfo.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
                <tr>
                    <td>提现凭证</td>
                    <td colspan="5">
                        <img src="${withdrawalsInfo.unlineImg}" width="75px;" height="75px;" class="picToBig">
                    </td>
                </tr>

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

    var withdrawalsDetails = function(userId){
        openDialog({
            frame:true,
            title:"收支明细记录",
            height:600,
            width:1000,
            url:"${ctx}/withdrawalsInfo/withdrawalsDetails?userId="+userId
        });
    }

    function withdrawalsOnline(id){
        ajaxSubmit("${ctx}/withdrawalsInfo/withdrawalsOnline",{"withdrawalsId":id},reload,"提现成功","确认提现吗？");
    }

    var withdrawalsUnline= function(id){
        openDialog({
            frame:true,
            title:"线下提现",
            height:400,
            width:800,
            url:"${ctx}/withdrawalsInfo/withdrawalsUnline?withdrawalsId="+id
        });
    }
</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>