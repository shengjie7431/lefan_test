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
    <form id="editForm" role="form" action="${ctx}/cci/injuryInformationSave" method="post">
        <input type="hidden" name="customerId" value="${id}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>伤情名称</th>
                    <td width="80%">
                        <input type="text" id = "injuryName" name="injuryName" required="required" value="${injuryName}"   style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>已用医疗费</th>
                    <td width="80%">
                        <input type="number" id = "usedMedicalFee" name="usedMedicalFee" required="required" value="${usedMedicalFee}"   style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">欠的医疗费</th>
                    <td width="80%">
                        <input type="number" id = "oweMedicalFee" name="oweMedicalFee" value="${oweMedicalFee}"   style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">还需医疗费</th>
                    <td width="80%">
                        <input type="number" id = "neededMedicalFee" name="neededMedicalFee" value="${neededMedicalFee}"   style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>筹资方式</th>
                    <td width="80%">
                        <select name="financingType" required="required" class="form-control" style="width: 400px;">
                            <option value="0" <c:if test="${financingType==0}">selected="selected" </c:if>>自费</option>
                            <option value="1" <c:if test="${financingType==1}">selected="selected" </c:if>>保司</option>
                            <option value="2" <c:if test="${financingType==2}">selected="selected" </c:if>>道救救助基金垫付</option>
                            <option value="3" <c:if test="${financingType==3}">selected="selected" </c:if>>其他</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">就诊医院</th>
                    <td width="80%">
                        <input type="text" id = "visHospital" name="visHospital" value="${visHospital}"   style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>是否住院</th>
                    <td width="80%">
                        <select name="isInhospital" required="required" class="form-control" style="width: 400px;">
                            <option value="0" <c:if test="${isInhospital==0}">selected="selected" </c:if>>否</option>
                            <option value="1" <c:if test="${isInhospital==1}">selected="selected" </c:if>>是</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active"><strong class="necessary">*</strong>是否手术</th>
                    <td width="80%">
                        <select name="isOperation" required="required" class="form-control" style="width: 400px;">
                            <option value="0" <c:if test="${isOperation==0}">selected="selected" </c:if>>否</option>
                            <option value="1" <c:if test="${isOperation==1}">selected="selected" </c:if>>是</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">科室</th>
                    <td width="80%">
                        <input type="text" id = "hospitalDepartments" name="hospitalDepartments" value="${hospitalDepartments}"   style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">床位号</th>
                    <td width="80%">
                        <input type="number" id = "bedNumber" name="bedNumber" value="${bedNumber}"   style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">住院号</th>
                    <td width="80%">
                        <input type="number" id = "hospitalNumber" name="hospitalNumber" value="${hospitalNumber}"   style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">主治医生</th>
                    <td width="80%">
                        <input type="text" id = "doctor" name="doctor" value="${doctor}"   style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">医生联系电话</th>
                    <td width="80%">
                        <input type="text" id = "doctorTel" name="doctorTel" value="${doctorTel}"   style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">床位护士</th>
                    <td width="80%">
                        <input type="text" id = "nurse" name="nurse" value="${nurse}"   style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">护士联系电话</th>
                    <td width="80%">
                        <input type="text" id = "nurseTel" name="nurseTel" value="${nurseTel}"   style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">其他信息</th>
                    <td width="80%">
                        <textarea name="otherDesc" style="width: 400px;height: 100px" class="form-control">${otherDesc}</textarea>
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