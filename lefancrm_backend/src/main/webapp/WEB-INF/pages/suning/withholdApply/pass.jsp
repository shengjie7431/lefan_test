<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/case/center/operate" method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <input type="hidden" id="id" name="id" value="${id}">
                <%--<input type="hidden" id="caseNo" name="caseNo" value="${caseNo}">--%>
                <input type="hidden" id="btnCode" name="btnCode" value="${btnCode}">
                <tr>
                    <th width="20%" class="active">本金</th>
                    <td width="80%">
                        ${payEstimateInquiry.assetsAmount}
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">利息</th>
                    <td width="80%">
                        ${payEstimateInquiry.interstAmount}
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">剩余服务费</th>
                    <td width="80%">
                        ${payEstimateInquiry.laveServiceAmount}
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">周期</th>
                    <td width="80%">
                        ${payEstimateInquiry.cycleDays}天
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<th width="20%" class="active">可贴息贷款金额</th>--%>
                    <%--<td width="80%">--%>
                        <%--${payEstimateInquiry.discountLoanFee}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th width="20%" class="active">申请贴息贷款金额</th>--%>
                    <%--<td width="80%">--%>
                        <%--${payEstimateInquiry.applyDiscountLoanFee}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th width="20%" class="active">应扣金额</th>--%>
                    <%--<td width="80%">--%>
                        <%--${payEstimateInquiry.shouldDeFee}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th width="20%" class="active">实际扣费金额</th>--%>
                    <%--<td width="80%">--%>
                        <%--${payEstimateInquiry.realDeFee}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th width="20%" class="active">还需贷款</th>--%>
                    <%--<td width="80%">--%>
                        <%--${payEstimateInquiry.stillNeedFee}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th width="20%" class="active">贷款费用</th>--%>
                    <%--<td width="80%">--%>
                        <%--${payEstimateInquiry.loanFee}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th width="20%" class="active">保险费</th>--%>
                    <%--<td width="80%">--%>
                        <%--${payEstimateInquiry.insuranceFee}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th width="20%" class="active">贷款总金额</th>--%>
                    <%--<td width="80%">--%>
                        <%--${payEstimateInquiry.totalLoanFee}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th width="20%" class="active">扣费总金额</th>--%>
                    <%--<td width="80%">--%>
                        <%--${payEstimateInquiry.totalDeFee}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr>
                    <th width="20%" class="active">是否贴息</th>
                    <td width="80%">
                        <select required="required" name="isInterst" onchange="interstChange(this)" class="form-control">
                            <option value="1">是</option>
                            <option value="0" selected>否</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">代扣费用</th>
                    <td width="80%">
                        <input type="hidden" id="hidAmount" name="hidAmount" value="${payEstimateInquiry.amount - payEstimateInquiry.laveServiceAmount}">
                        <input type="number" step="0.01" required="required" min="0" class="form-control" id="amount" name="amount" value="${payEstimateInquiry.amount}"/>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">代扣方式</th>
                    <td width="80%">
                        <select required="required" name="withholdType" class="form-control">
                            <option value="" selected>请选择</option>
                            <option value="1">现金</option>
                            <option value="3">转账</option>
                            <option value="2">苏宁代扣</option>
                            <option value="4">平安代扣</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">还款时间</th>
                    <td width="80%">
                        <input name="handInTime" type="text"  value="<fmt:formatDate value="${handInTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认</button>
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

    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            if (r.success == true){
                $("#infImg").attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                $("#adPic").val(r.imgUrl);
            }else {
                alert("上传失败，请重试111");
            }
        }
    });

    function returnCallback(event,param){
        $(this).find(":submit").removeAttr("disabled");
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){
            alert('代扣成功');
        }else{
            alert(apiRsp.msg);return;
        }
        reloadParent();
    }
    function interstChange(obj){
        var interstAmount = '${payEstimateInquiry.interstAmount}';
        var amount = '${payEstimateInquiry.amount}';
        var laveServiceAmount = '${payEstimateInquiry.laveServiceAmount}';
        if(obj.value == 0){
            $('#amount').val(amount);
            $('#hidAmount').val(amount - laveServiceAmount);
        }else{
            $('#amount').val((amount - interstAmount).toFixed(2));
            $('#hidAmount').val((amount - interstAmount - laveServiceAmount).toFixed(2));
        }
    }

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        $(this).find(":submit").attr("disabled","true");
        ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
        event.preventDefault();
    });
</script>
</body>
</html>