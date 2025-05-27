<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>
        function groBack(){

//            var val = $("#orgId").find("option:selected").text();
            //$("#orgName").attr("value",val);
//            $("#orgName").val(val);
        }

    </script>
</head>
<body>
<div class="container">
    <%--<form id="editForm" role="form" action="${ctx}/user/userInfoUpdate" method="post">--%>
        <%--<input type="hidden" name="userId" value="${user.userId}">--%>
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active">案件编号</th>
                    <td>
                        <input type="text" disabled="true" id = "caseCode" name="caseCode" value="${sccd.caseCode}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">案件类型</th>
                    <td width="80%">
                        <select name="caseType"style="width: 400px;" class="form-control" disabled="true">
                            <option value="1" <c:if test="${sccd.caseType == 1}">selected="selected" </c:if>>个人调解</option>
                            <option value="2" <c:if test="${sccd.caseType == 2}">selected="selected" </c:if>>交警调解</option>
                            <option value="3" <c:if test="${sccd.caseType == 3}">selected="selected" </c:if>>法院调解</option>
                            <option value="4" <c:if test="${sccd.caseType == 4}">selected="selected" </c:if>>保险公司调解</option>
                            <option value="4" <c:if test="${sccd.caseType == 5}">selected="selected" </c:if>>专调</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">发起人类型</th>
                    <td width="80%">
                        <select name="launchType"style="width: 400px;" class="form-control" disabled="true">
                            <option value="1" <c:if test="${sccd.launchType == 1}">selected="selected" </c:if>>原告/律师发起</option>
                            <option value="2" <c:if test="${sccd.launchType == 2}">selected="selected" </c:if>>保险公司</option>
                            <option value="3" <c:if test="${sccd.launchType == 3}">selected="selected" </c:if>>法院调解员</option>
                            <option value="4" <c:if test="${sccd.launchType == 4}">selected="selected" </c:if>>交警队</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">发起人姓名</th>
                    <td width="80%">
                        <input type="text" disabled="true" id = "launchName" name="launchName" value="${sccd.launchName}"  style="width: 400px;" class="form-control">
                </td>
                <tr>
                    <th width="20%" class="active">用户姓名</th>
                    <td width="80%">
                        <input type="text" disabled="true" id = "userName" name="userName" value="${sccd.userName}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">手机号码</th>
                    <td width="80%">
                        <input type="text" disabled="true" id = "userPhone" name="userPhone" value="${sccd.userPhone}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">是否交通事故</th>
                    <td width="80%">
                        <select id = "isTraffic" disabled="true" name="isTraffic"  style="width: 400px;" class="form-control">
                            <option value="0" <c:if test="${sccd.isTraffic == 0}">selected = selected</c:if>>否</option>
                            <option value="1" <c:if test="${sccd.isTraffic == 1}">selected = selected</c:if>>是</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">保险公司名称</th>
                    <td width="80%">
                    <input type="text" disabled="true" id = "icName" name="icName" value="${sccd.icName}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">案件状态</th>
                    <td width="80%">
                        <select id = "state" disabled="true" name="state"  style="width: 400px;" class="form-control">
                            <option value="0" <c:if test="${sccd.state == 0}">selected = selected</c:if>>待审核</option>
                            <option value="1" <c:if test="${sccd.state == 1}">selected = selected</c:if>>审核通过待调解</option>
                            <option value="2" <c:if test="${sccd.state == 2}">selected = selected</c:if>>驳回</option>
                            <option value="3" <c:if test="${sccd.state == 3}">selected = selected</c:if>>调解中</option>
                            <option value="4" <c:if test="${sccd.state == 4}">selected = selected</c:if>>调解方案已确认</option>
                            <option value="5" <c:if test="${sccd.state == 5}">selected = selected</c:if>>调解成功</option>
                            <option value="6" <c:if test="${sccd.state == 6}">selected = selected</c:if>>调解失败</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">伤者方/原告是否同意</th>
                    <td width="80%">
                        <select id = "plaintiffOpinion" disabled="true" name="plaintiffOpinion"  style="width: 400px;" class="form-control">
                            <option value="0" <c:if test="${sccd.plaintiffOpinion == 0}">selected = selected</c:if>>否</option>
                            <option value="1" <c:if test="${sccd.plaintiffOpinion == 1}">selected = selected</c:if>>是</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">驾驶员是否同意</th>
                    <td width="80%">
                        <select id = "driverOpinion" disabled="true" name="driverOpinion"  style="width: 400px;" class="form-control">
                            <option value="0" <c:if test="${sccd.driverOpinion == 0}">selected = selected</c:if>>否</option>
                            <option value="1" <c:if test="${sccd.driverOpinion == 1}">selected = selected</c:if>>是</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">保险公司是否同意</th>
                    <td width="80%">
                        <select id = "inOpinion" disabled="true" name="inOpinion"  style="width: 400px;" class="form-control">
                            <option value="0" <c:if test="${sccd.inOpinion == 0}">selected = selected</c:if>>否</option>
                            <option value="1" <c:if test="${sccd.inOpinion == 1}">selected = selected</c:if>>是</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">案件备注</th>
                    <td width="80%">
                        <textarea disabled="true" style="width: 400px;height: 100px;" class="form-control">${sccd.caseDesc}</textarea>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">车牌号</th>
                    <td width="80%">
                        <input type="text" disabled="true" id = "carNumber" name="carNumber" value="${sccd.carNumber}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">保险员姓名</th>
                    <td width="80%">
                        <input type="text" disabled="true" id = "inOfficerName" name="inOfficerName" value="${sccd.inOfficerName}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">保险员手机号码</th>
                    <td width="80%">
                        <input type="text" disabled="true" id = "inOfficerPhone" name="inOfficerPhone" value="${sccd.inOfficerPhone}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">对方姓名</th>
                    <td width="80%">
                        <input type="text" disabled="true" id = "otherName" name="otherName" value="${sccd.otherName}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">对方手机号码</th>
                    <td width="80%">
                        <input type="text" disabled="true" id = "otherPhone" name="otherPhone" value="${sccd.otherPhone}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">公用组织机构名称</th>
                    <td width="80%">
                        <input type="text" disabled="true" id = "cOrgName" name="cOrgName" value="${sccd.cOrgName}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">公用组织机构类型</th>
                    <td width="80%">
                        <select name="cOrgType"style="width: 400px;" class="form-control" disabled="true">
                            <option value="1" <c:if test="${sccd.cOrgType == 1}">selected="selected" </c:if>>法院</option>
                            <option value="2" <c:if test="${sccd.cOrgType == 2}">selected="selected" </c:if>>交警大队</option>
                            <option value="3" <c:if test="${sccd.cOrgType == 3}">selected="selected" </c:if>>调解组织</option>
                            <option value="4" <c:if test="${sccd.cOrgType == 4}">selected="selected" </c:if>>律所</option>
                            <option value="5" <c:if test="${sccd.cOrgType == 5}">selected="selected" </c:if>>保司</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">出险时间</th>
                    <td width="80%">
                        <input type="text" disabled="true" id = "accidentTime" name="accidentTime" value="<fmt:formatDate value="${sccd.accidentTime}" pattern="yyyy-MM-dd HH:mm"/>" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">出险地点</th>
                    <td width="80%">
                        <input type="text" disabled="true" id = "accidentAddress" name="accidentAddress" value="${sccd.accidentAddress}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">出险省</th>
                    <td width="80%">
                        <input type="text" disabled="true" id = "accidentProvince" name="accidentProvince" value="${sccd.accidentProvince}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">出险市</th>
                    <td width="80%">
                        <input type="text" disabled="true" id = "accidentCity" name="accidentCity" value="${sccd.accidentCity}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">出险区</th>
                    <td width="80%">
                        <input type="text" disabled="true" id = "accidentDistrict" name="accidentDistrict" value="${sccd.accidentDistrict}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">驳回原因</th>
                    <td width="80%">
                        <textarea disabled="true" style="width: 400px;height: 100px;" class="form-control">${sccd.reson}</textarea>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">创建时间</th>
                    <td width="80%">
                        <input type="text" disabled="true" id = "createTime" name="createTime" value="<fmt:formatDate value="${sccd.createTime}" pattern="yyyy-MM-dd HH:mm"/>" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">创建人</th>
                    <td width="80%">
                        <input type="text" disabled="true" id = "createBy" name="createBy" value="${sccd.createBy}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<th width="20%" class="active">所属机构</th>--%>
                    <%--<td width="80%">--%>
                        <%--<select name="orgId" onclick="groBack()" id="orgId" style="width: 400px;" class="form-control" <c:if test="${user.bsRoleId == 1}" >disabled</c:if>>--%>
                            <%--<option value="0">未分配</option>--%>
                            <%--<c:forEach items="${orgs}" var="org">--%>
                                <%--<option value="${org.id}"<c:if test="${user.orgId == org.id}">selected = selected</c:if>>${org.orgName}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                        <%--<input type="hidden" name="orgName" id="orgName" value="">--%>
                    <%--</td>--%>
                <%--</tr>--%>
                </tbody>
            </table>
        </div>
        <%--<div class="modal-footer">--%>
            <%--<button type="submit" onclick="groBack()" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>--%>
        <%--</div>--%>
    <%--</form>--%>
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
    <%--var editor1;--%>
    <%--KindEditor.ready(function(K) {--%>
         <%--editor1 = K.create('textarea[name="content"]', {--%>
            <%--cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',--%>
            <%--uploadJson : '${ctx}/uploadFileForKindEditor'--%>
        <%--});--%>
    <%--});--%>
//
//    $('#fileupload').fileupload({
//        done: function (e, data) {
//            var r  = data.result;
//            var img=r.images;
//            var pathImg=img[0].userFilePath;
//            if (r.success == true){
//                $("#infImg").attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
//                $("#adPic").val("http://ddrapi.shlefan.com/sftp/files/"+pathImg);
//            }else {
//                alert("上传失败，请重试111");
//            }
//        }
//    });
//    $("#editForm").bind('submit', function(event) {
//        //$("#content").text(editor1.html());
//        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
//        event.preventDefault();
//    });
</script>
</body>
</html>