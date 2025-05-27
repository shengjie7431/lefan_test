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
    <input type="hidden" name="id" value="${lawFeeDetailDto.id}">
    <div class="title">
        <c:if test="${lawFeeDetailDto.state ==1}">
            <button class="butList active" onclick="feeDetailUpd('${lawFeeDetailDto.id}');">确认退费</button>
        </c:if>
        <c:if test="${lawFeeDetailDto.state ==2}">
            <button class="butList default">已确认</button>
        </c:if>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                    <tr>
                        <td>案件编号</td>
                        <td>${lawFeeDetailDto.caseNo}</td>
                        <td>金额</td>
                        <td>${lawFeeDetailDto.money}</td>
                        <td>退费状态</td>
                        <td>
                            <c:if test="${lawFeeDetailDto.type == 1}">正常退费</c:if>
                            <c:if test="${lawFeeDetailDto.type == 2}">补费</c:if>
                            <c:if test="${lawFeeDetailDto.type == 3}">退案退费</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>委托人姓名</td>
                        <td>${lawFeeDetailDto.entrustUserName}</td>
                        <td>委托人电话</td>
                        <td>${lawFeeDetailDto.entrustUserTel}</td>
                        <td>是否已确认退费</td>
                        <td>
                            <c:if test="${lawFeeDetailDto.state == 1}">未确认</c:if>
                            <c:if test="${lawFeeDetailDto.state == 2}">已确认</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>退费时间</td>
                        <td>
                            <fmt:formatDate value="${lawFeeDetailDto.retreatTime}" pattern="yyyy-MM-dd HH:mm" />
                        </td>
                        <td>到账时间</td>
                        <td>
                            <fmt:formatDate value="${lawFeeDetailDto.arrTime}" pattern="yyyy-MM-dd HH:mm" />
                        </td>
                        <td>创建时间</td>
                        <td>
                            <fmt:formatDate value="${lawFeeDetailDto.createTime}" pattern="yyyy-MM-dd HH:mm" />
                        </td>
                    </tr>
                    <tr>
                        <td>退费说明</td>
                        <td colspan="5">${lawFeeDetailDto.refoundRemark}</td>
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


    /**
     * 确认退费
     */
    function feeDetailUpd(id){
        ajaxSubmit("${ctx}/law/feeDetail/upd",{"id":id},reload,"提交成功！","确认提交？","提交失败！");
    }

</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>