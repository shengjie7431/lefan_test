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
<style>


</style>
<body>
<div class="main">
    <input type="hidden" name="id" value="${casePayInfo.id}">
    <div class="title">
        <c:if test="${casePayInfo.auditState == 1}">
            <c:if test="${caseUserRole.isFinancial}">
                <button class="butList active" onclick="operate('${casePayInfo.id}','1100',true);">提交审核</button>
            </c:if>
        </c:if>
        <c:if test="${casePayInfo.auditState == 2}">
            <c:if test="${caseUserRole.isFinancial}">
                <button class="butList defuelt" onclick=";">审核中</button>
            </c:if>
            <c:if test="${caseUserRole.isFinancialAuditor}">
                <button class="butList active" onclick="operate('${casePayInfo.id}','1101',true);">通过审核</button>
                <button class="butList active" onclick="operate('${casePayInfo.id}','1102',false);">驳回</button>
            </c:if>
        </c:if>
        <c:if test="${casePayInfo.auditState == 3}">
            <c:if test="${caseUserRole.isFinancial}">
                <c:if test="${casePayInfo.payState !=2 }">
                    <button class="butList active" onclick="uploadCasePayInfoImg('${casePayInfo.id}','1200');">确认支付</button>
                </c:if>
                <c:if test="${casePayInfo.payState ==2 }">
                    <button class="butList defuelt" onclick="">已支付</button>
                </c:if>
            </c:if>
            <button class="butList defuelt" onclick=";">已通过</button>
        </c:if>
        <c:if test="${casePayInfo.auditState == 4}">
            <c:if test="${caseUserRole.isFinancial}">
                <button class="butList active" onclick="operate('${casePayInfo.id}','1100',true);">再次提交</button>
            </c:if>
            <button class="butList defuelt" onclick=";">已驳回</button>
        </c:if>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>案件标题</td>
                    <td>${casePayInfo.caseTitle}</td>
                    <td>伤者姓名</td>
                    <td>${casePayInfo.userName}</td>
                    <td>支付金额</td>
                    <td>${casePayInfo.payMoney}</td>
                </tr>
                <tr>
                    <td>费用名称</td>
                    <td>${casePayInfo.payName}</td>
                    <td>账户号</td>
                    <td>${casePayInfo.accountName}</td>
                    <td>开户行</td>
                    <td>${casePayInfo.bankName}</td>
                </tr>
                <tr>
                    <td>卡号</td>
                    <td>${casePayInfo.cardNo}</td>
                    <td>支付人名称</td>
                    <td>${casePayInfo.operatorName}</td>
                    <td>支付状态</td>
                    <td><c:if test="${casePayInfo.payState == 0}">待支付</c:if>
                        <c:if test="${casePayInfo.payState == 1}">支付中</c:if>
                        <c:if test="${casePayInfo.payState == 2}">已支付</c:if>
                    </td>
                </tr>
                <tr>
                    <td>支付时间</td>
                    <td><fmt:formatDate value="${casePayInfo.payTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${casePayInfo.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td colspan="5">${casePayInfo.remarks}</td>
                </tr>
                <tr>
                    <td>审核状态</td>
                    <td>
                        <c:if test="${casePayInfo.auditState == 1}">待提交</c:if>
                        <c:if test="${casePayInfo.auditState == 2}">审核中</c:if>
                        <c:if test="${casePayInfo.auditState == 3}">审核成功</c:if>
                        <c:if test="${casePayInfo.auditState == 4}">已驳回</c:if>
                    </td>
                    <td>驳回原因</td>
                    <td colspan="3">
                        ${casePayInfo.auditReason}
                    </td>
                </tr>
                <tr>
                    <td>支付凭证</td>
                    <c:if test="${casePayInfo.img!=null}">
                        <td colspan="5"><img src="${casePayInfo.img}" width="75;" height="75;" class="picToBig"></td>
                    </c:if>
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
     * 开票页面
     */
    function uploadCasePayInfoImg(id,btnCode) {
        openDialog({
            frame: true,
            title: "支付凭证页面",
            height: 350,
            width: 650,
            url: "${ctx}/casePayInfo/uploadCasePayInfoImg?id="+ id+"&btnCode="+btnCode
        });
    }

    /**
     *
     * @param id        案件中心ID
     * @param btnCode    按钮CODE,与list_state一一对应
     * @param ajax       是否ajax请求,  true 弹出提示框   false 弹出界面
     */
    function operate(id,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/casePayInfo/casePayInfoUpd",param = {"id":id,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    alert(e.data.msg);
                    location.reload();
                })
            }
        }else{
            var title = null,url = null;
            if(btnCode == '1102'){
                title = '失败原因';
                url = "${ctx}/casePayInfo/uploadCasePayInfoImg?id="+id+"&btnCode="+btnCode;
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
</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>