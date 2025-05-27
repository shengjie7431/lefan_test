<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/case/center/saveBankCardInfo" method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <input type="hidden" id="id" name="id" value="${bankCardInfo.id}">
                <input type="hidden" id="btnCode" name="btnCode" value="${btnCode}">
                <input type="hidden" id="caseId" name="caseId" value="${caseId}">
                <input type="hidden" id="certType" name="certType" value="01">
                <tr>
                    <th width="30%" class="active">银行</th>
                    <td colspan="4" width="70%">
                        <select required="required" name="bankId" id="bankId" class="form-control">
                            <c:forEach items="${bankInfos}" var="item">
                                <option value="${item.id}" <c:if test="${bankCardInfo.bankCode == item.bankCode}">selected="selected" </c:if>>${item.bankName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">持卡人姓名</th>
                    <td  colspan="4" width="70%">
                        <input required="required" type="text" id="cardHolderName" name="cardHolderName" value="${bankCardInfo.cardHolderName}" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">持卡人身份证号</th>
                    <td colspan="4" width="70%">
                        <input required="required" type="text" id="certNo" name="certNo" value="${bankCardInfo.certNo}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">手机号码</th>
                    <td colspan="4" width="70%">
                        <input required="required" type="text" id="mobileNo" name="mobileNo" value="${bankCardInfo.mobileNo}" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">银行卡卡号</th>
                    <td colspan="4" width="70%">
                        <input required="required" type="text" id="cardNo" name="cardNo" value="${bankCardInfo.cardNo}" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">银行卡类型</th>
                    <td colspan="4" width="70%">
                        <select required="required" id="cardType" name="cardType" class="form-control">
                            <option value="1" <c:if test="${bankCardInfo.cardType == 1}">selected </c:if>>储蓄卡</option>
                            <option value="2" <c:if test="${bankCardInfo.cardType == 2}">selected </c:if>>信用卡</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">银行卡有效期</th>
                    <td width="1%" style="text-align: center;vertical-align: middle;">
                        年
                    </td>
                    <td width="30%">
                        <select required="required" id="expYear" name="expYear" class="form-control" onchange="yearSelected()">
                            <option value="${bankCardInfo.expYear}">${bankCardInfo.expYear}</option>
                        </select>
                    </td>
                    <td width="1%" style="text-align: center;vertical-align: middle;">
                        月
                    </td>
                    <td width="30%">
                        <select required="required" id="expMonth" name="expMonth" class="form-control" onchange="monthSelected()">
                            <option value="${bankCardInfo.expMonth}">${bankCardInfo.expMonth}</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th widt
                        h="30%" class="active">银行卡校验值(CVV)</th>
                    <td colspan="4" width="70%">
                        <input required="required" type="text" id="cvv" name="cvv" value="${bankCardInfo.cvv}" class="form-control">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script type="text/javascript">
    var editor1;
    KindEditor.ready(function(K) {
         editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        $(this).find(":submit").attr("disabled","true");
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

    /*向下拉框中填入数据*/
    $(function(){
        var currentYear = 01;
        for( var i=0; i<99; i++ ){
            var yearOld = currentYear+i;
            $("#expYear").append($("<option value="+yearOld+">"+yearOld+"</option>"));
        }
        for( var i=0; i<12; i++ ){
            var monthOld = currentYear+i;
            $("#expMonth").append($("<option value="+monthOld+">"+monthOld+"</option>"));
        }
    });
    /* 获取选中的下拉框的值 */
    function yearSelected(){
        var valueSel = $("#expYear").find("option:selected").val();
        var textSel = $("#expYear").find("option:selected").text();
    }
    function monthSelected(){
        var valueSel = $("#expMonth").find("option:selected").val();
        var textSel = $("#expMonth").find("option:selected").text();
    }
</script>
</body>
</html>