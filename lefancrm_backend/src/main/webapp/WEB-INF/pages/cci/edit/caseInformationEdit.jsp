<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/cci/caseInformationSave" method="post">
        <input type="hidden" name="customerId" value="${customerId}">
        <input type="hidden" name="customerName" value="${customerName}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tbody>
                <tr>
                    <th width="20%" class="active">案件来源</th>
                    <td width="80%">
                        <select name="caseSource" class="form-control" style="width: 400px;">
                            <option value="0" <c:if test="${caseSource==0}">selected="selected" </c:if>>医院</option>
                            <option value="1" <c:if test="${caseSource==1}">selected="selected" </c:if>>小程序</option>
                            <option value="2" <c:if test="${caseSource==2}">selected="selected" </c:if>>工作室</option>
                            <option value="3" <c:if test="${caseSource==3}">selected="selected" </c:if>>保司</option>
                            <option value="4" <c:if test="${caseSource==4}">selected="selected" </c:if>>交警</option>
                            <option value="5" <c:if test="${caseSource==5}">selected="selected" </c:if>>陌拜</option>
                            <option value="6" <c:if test="${caseSource==6}">selected="selected" </c:if>>护工</option>
                            <option value="7" <c:if test="${caseSource==7}">selected="selected" </c:if>>转介</option>
                            <option value="8" <c:if test="${caseSource==8}">selected="selected" </c:if>>其他</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">是否意向</th>
                    <td width="80%">
                        <select name="isIntention" class="form-control" style="width: 400px;">
                            <option value="0" <c:if test="${isIntention==0}">selected="selected" </c:if>>否</option>
                            <option value="1" <c:if test="${isIntention==1}">selected="selected" </c:if>>是</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">案件类型</th>
                    <td width="80%">
                        <select name="caseType" class="form-control" style="width: 400px;">
                            <option value="1" <c:if test="${caseType==1}">selected="selected" </c:if>>简易代理</option>
                            <option value="2" <c:if test="${caseType==2}">selected="selected" </c:if>>案件代理</option>
                            <option value="3" <c:if test="${caseType==3}">selected="selected" </c:if>>代理+垫付</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">案件进度</th>
                    <td width="80%">
                        <select name="caseProgress" class="form-control" style="width: 400px;">
                            <option value="1" <c:if test="${caseProgress==1}">selected="selected" </c:if>>初访</option>
                            <option value="2" <c:if test="${caseProgress==2}">selected="selected" </c:if>>洽谈中</option>
                            <option value="3" <c:if test="${caseProgress==3}">selected="selected" </c:if>>待签约</option>
                            <option value="4" <c:if test="${caseProgress==4}">selected="selected" </c:if>>已签约</option>
                            <option value="5" <c:if test="${caseProgress==5}">selected="selected" </c:if>>暂时搁置</option>
                            <option value="6" <c:if test="${caseProgress==6}">selected="selected" </c:if>>已放弃</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">案件索赔金额</th>
                    <td width="80%">
                        <input type="number" id = "claimFee" name="claimFee" value="${claimFee}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">贷款金额</th>
                    <td width="80%">
                        <input type="number" id = "loanFee" name="loanFee" value="${loanFee}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">案件服务费金额</th>
                    <td width="80%">
                        <input type="number" id = "serviceFee" name="serviceFee" value="${loanFee}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>跟进时间</th>
                    <td width="80%">
                        <input name="followTime"  style="width: 400px;" type="text" required="required" value="${followTime}"  class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly>
                    </td>
                </tr>

                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>跟进地点</th>
                    <td width="80%">
                        <input type="text" id = "followAddress" name="followAddress" required="required" value="${followAddress}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>下次跟进时间</th>
                    <td width="80%">
                        <input name="nextFollowTime"  style="width: 400px;" type="text" required="required" value="${nextFollowTime}"  class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>备注</th>
                    <td width="80%">
                        <textarea name="followDesc" style="width: 400px;height: 100px" required="required" class="form-control">${followDesc}</textarea>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<%--<script src="${ctx}/js/jquery.pin.js" type="text/javascript"></script>--%>
<%--<script src="${ctx}/js/jquery.tableDnD.js" type="text/javascript"></script>--%>
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
    $("#editForm").bind('submit', function(event) {
//        $("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
</script>
</body>
</html>