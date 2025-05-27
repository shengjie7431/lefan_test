<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
    <script>


    </script>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/baseSurvey/update" method="post">
        <input type="hidden" name="id" value="${surveyFranchisee.id}">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <c:if test="${surveyFranchisee.id !=null}">
                <tr>
                    <th width="30%" class="active">调查方code</th>
                    <td width="70%">
                        ${surveyFranchisee.code}
                    </td>
                </tr>
                </c:if>
                <tr>
                    <th width="30%" class="active">调查方名称</th>
                    <td width="70%">
                        <input type="text" id = "name" name="name" value="${surveyFranchisee.name}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">业务属性</th>
                    <td width="70%">
                        <select name="busType" class="form-control">
                            <c:if test="${surveyFranchisee.busType==null}">
                                <option value="1">互助</option>
                                <option value="2">保险</option>
                                <option value="3">互助+保险</option>
                            </c:if>
                            <c:if test="${surveyFranchisee.busType==1}">
                                <option value="1" selected>互助</option>
                                <option value="2">保险</option>
                                <option value="3">互助+保险</option>
                            </c:if>
                            <c:if test="${surveyFranchisee.busType==2}">
                                <option value="1">互助</option>
                                <option value="2" selected>保险</option>
                                <option value="3">互助+保险</option>
                            </c:if>
                            <c:if test="${surveyFranchisee.busType==3}">
                                <option value="1">互助</option>
                                <option value="2">保险</option>
                                <option value="3" selected>互助+保险</option>
                            </c:if>
                        </select>
                    </td>
                </tr>
<%--                <tr>--%>
<%--                    <th width="30%" class="active">区域类别</th>--%>
<%--                    <td width="70%">--%>
<%--                        <select name="areaType" id ="areaType" class="form-control" required="required" onchange="toBuild()">--%>
<%--                            <option value="3" <c:if test="${surveyFranchisee.areaType==3}">selected="selected" </c:if>>区</option>--%>
<%--                            <option value="2" <c:if test="${surveyFranchisee.areaType==2}">selected="selected" </c:if>>市</option>--%>
<%--                            <option value="1" <c:if test="${surveyFranchisee.areaType==1}">selected="selected" </c:if>>省</option>--%>
<%--                        </select>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th width="20%" class="active">区域</th>--%>
<%--                    <td width="80%" class="form-inline">--%>
<%--                        <div class="form-group" id="provinceDiv" style="display: none">--%>
<%--                            省 <select id = "provinceId" name="provinceId" style="width: 90px;" onchange="selectArea()" class="form-control" required="required">--%>
<%--                            <option value="">请选择</option>--%>
<%--                            <c:forEach items="${apiRsp.results}" var="area">--%>
<%--                                <option value="${area.areaId}" <c:if test="${surveyFranchisee.provinceId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </select>--%>
<%--                        </div>--%>
<%--                        <div class="form-group" id="cityDiv" style="display: none">--%>
<%--                            市 <select id = "cityId" name="cityId" style="width: 90px;" onclick="selectAreaCity()" class="form-control" >--%>
<%--                            <c:forEach items="${cityApiRsp.results}" var="area">--%>
<%--                                <option value="${area.areaId}" <c:if test="${surveyFranchisee.cityId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </select>--%>

<%--                        </div>--%>
<%--                        <div class="form-group" id="districtDiv" style="display: none">--%>
<%--                            区 <select id = "districtId" name="districtId" style="width: 90px;" onclick="selectAreaDistrict()" class="form-control" >--%>
<%--                            <c:forEach items="${districtApiRsp.results}" var="area">--%>
<%--                                <option value="${area.areaId}" <c:if test="${surveyFranchisee.districtId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </select>--%>
<%--                        </div>--%>
<%--                        <input type="hidden" id="areaTypeId" name="areaTypeId" value="">--%>
<%--                        <input type="hidden" id="areaName" name="areaName" value="">--%>
<%--                        <input type="hidden" id="province" name="province" value="">--%>
<%--                        <input type="hidden" id="city" name="city" value="">--%>
<%--                        <input type="hidden" id="district" name="district" value="">--%>
<%--                    </td>--%>
<%--                </tr>--%>
                </tr>
<%--                <tr>--%>
<%--                    <th width="20%" class="active">父级</th>--%>
<%--                    <td width="80%">--%>
<%--                        <input type="text" id="parentName" name="parentName" value="${surveyFranchisee.parentName}" style="width: 320px; display: inline-block;" class="form-control" placeholder="可输入">--%>
<%--                        <input type="hidden" id="parentId" name="parentId" value="${surveyFranchisee.parentId}">--%>
<%--                        <input type="button" value="选  择" onclick="selectFranchisee('${surveyCode}')"  style="width: 78px;  height: 32px;"/>--%>
<%--                    </td>--%>
<%--                </tr>--%>
                <tr>
                    <th width="30%" class="active">调查方类别(互助)</th>
                    <td width="70%">
                        <select name="type" class="form-control" required="required">
                            <option value="1" <c:if test="${surveyFranchisee.type==1}">selected="selected" </c:if>>直营</option>
                            <option value="2" <c:if test="${surveyFranchisee.type==2}">selected="selected" </c:if>>合伙</option>
                            <option value="3" <c:if test="${surveyFranchisee.type==3}">selected="selected" </c:if>>合作</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">调查方类别(保司)</th>
                    <td width="70%">
                        <select name="insuranceType" class="form-control" required="required">
                            <option value="1" <c:if test="${surveyFranchisee.insuranceType==1}">selected="selected" </c:if>>直营</option>
                            <option value="2" <c:if test="${surveyFranchisee.insuranceType==2}">selected="selected" </c:if>>合伙</option>
                            <option value="3" <c:if test="${surveyFranchisee.insuranceType==3}">selected="selected" </c:if>>合作</option>
                        </select>
                    </td>
                </tr>
<%--                <tr>--%>
<%--                    <th width="30%" class="active">深度案件价格(保司)</th>--%>
<%--                    <td width="70%">--%>
<%--                        <input type="number" step="0.01" id = "deepCasesPrice" name="deepCasesPrice" value="${surveyFranchisee.deepCasesPrice}"  class="form-control">--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th width="30%" class="active">是否特殊机构</th>--%>
<%--                    <td width="70%">--%>
<%--                        <select name="isSpecial" class="form-control" required="required">--%>
<%--                            <option value="0" <c:if test="${surveyFranchisee.isSpecial==0}">selected="selected" </c:if>>否</option>--%>
<%--                            <option value="1" <c:if test="${surveyFranchisee.isSpecial==1}">selected="selected" </c:if>>是</option>--%>
<%--                        </select>--%>
<%--                    </td>--%>
<%--                </tr>--%>
                <tr>
                    <th width="30%" class="active">申请人</th>
                    <td width="70%">
                        <input type="text" id = "appUser" name="appUser" value="${surveyFranchisee.appUser}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">审批人</th>
                    <td width="70%">
                        <input type="text" id = "apvUser" name="apvUser" value="${surveyFranchisee.apvUser}"  class="form-control">
                    </td>
                </tr>
<%--                <tr>--%>
<%--                    <th width="30%" class="active">公估业务是否独立开票</th>--%>
<%--                    <td width="70%">--%>
<%--                        <select name="aloneBill" class="form-control" required="required">--%>
<%--                            <option value="0" <c:if test="${surveyFranchisee.aloneBill==0}">selected="selected" </c:if>>否</option>--%>
<%--                            <option value="1" <c:if test="${surveyFranchisee.aloneBill==1}">selected="selected" </c:if>>是</option>--%>
<%--                        </select>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th width="30%" class="active">收款人姓名</th>--%>
<%--                    <td width="70%">--%>
<%--                        <input type="text" id = "acceptUser" name="acceptUser" value="${surveyFranchisee.acceptUser}"  class="form-control">--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th width="30%" class="active">收款人身份证号</th>--%>
<%--                    <td width="70%">--%>
<%--                        <input type="text" id = "acceptUserIdcard" name="acceptUserIdcard" value="${surveyFranchisee.acceptUserIdcard}"  class="form-control">--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th width="30%" class="active">收款人身份证照片</th>--%>
<%--                    <td>--%>
<%--                        <div id="idcardImgsDiv"></div>--%>
<%--                        <div>--%>
<%--                            <input type="hidden" id="acceptUserIdcardUrl" name="acceptUserIdcardUrl" value="${surveyFranchisee.acceptUserIdcardUrl}" />--%>
<%--                            <div id="idcardUrlDiv" style="display: none"></div>--%>
<%--                            <div id="idcardUrlDeleteDiv">--%>
<%--                                <input type="button" value="删除" onclick="idcardDel()"  style="width: 64px;  height: 22px;"/>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div>--%>
<%--                            <input id="materialFileupload2" type="file" name="file" multiple data-url="${ctx}/sftp/survey/uploadSftp?modelType=franchisee"><br>--%>
<%--                        </div>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th width="30%" class="active">银行卡号</th>--%>
<%--                    <td width="70%">--%>
<%--                        <input type="text" id = "bankCard" name="bankCard" value="${surveyFranchisee.bankCard}"  class="form-control">--%>
<%--                    </td>--%>
<%--                </tr>--%>

<%--                <tr>--%>
<%--                    <th width="30%" class="active">银行名称（含支行）</th>--%>
<%--                    <td width="70%">--%>
<%--                        <input type="text" id = "bankName" name="bankName" value="${surveyFranchisee.bankName}"  class="form-control">--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <th width="30%" class="active">银行卡照片</th>--%>
<%--                    <td>--%>
<%--                        <div id="bankImgsDiv"></div>--%>
<%--                        <div>--%>
<%--                            <input type="hidden" id="bankUrl" name="bankUrl" value="${surveyFranchisee.bankUrl}" />--%>
<%--                            <div id="bankUrlDiv" style="display: none"></div>--%>
<%--                            <div id="bankUrlDeleteDiv">--%>
<%--                                <input type="button" value="删除" onclick="del()"  style="width: 64px;  height: 22px;"/>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div>--%>
<%--                            <input id="materialFileupload" type="file" name="file" multiple data-url="${ctx}/sftp/survey/uploadSftp?modelType=franchisee"><br>--%>
<%--                        </div>--%>
<%--                    </td>--%>
<%--                </tr>--%>
                <tr>
                    <th width="30%" class="active">关联人事管理-机构/部门</th>
                    <td width="70%">
                        <select name="departmentId" class="form-control">
                            <option value="">请选择人事管理-机构/部门</option>
                            <c:forEach items="${staffOrganList}" var="item">
                                <option value="${item.id}" <c:if test="${surveyFranchisee.departmentId == item.id}">selected="selected"</c:if>>${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">机构状态</th>
                    <td width="70%">
                        <select name="orgState" class="form-control">
                            <option value="0" <c:if test="${surveyFranchisee.orgState==0}">selected="selected"</c:if>>启用</option>
                            <option value="1" <c:if test="${surveyFranchisee.orgState==1}">selected="selected"</c:if>>停用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">业务类型</th>
                    <td width="70%">
                        <select name="serviceType" class="form-control">
                            <option value="1" <c:if test="${surveyFranchisee.serviceType==1}">selected="selected"</c:if>>垫付</option>
                            <option value="2" <c:if test="${surveyFranchisee.serviceType==2}">selected="selected"</c:if>>调查</option>
                            <option value="3" <c:if test="${surveyFranchisee.serviceType==3}">selected="selected"</c:if>>垫付+调查</option>
                        </select>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit" onclick="goBack()"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
<script type="text/javascript">
    var editor1;
    KindEditor.ready(function(K) {
         editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        var deepCasesPrice=$("#deepCasesPrice").val();
        if(deepCasesPrice<0){
            alert("深度案件价格必须大于0!");
            return false;
        }
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

    var selectFranchisee = function(surveyCode){
        openDialog({
            frame:true,
            title:"选择调查方",
            height:500,
            width:850,
            url:"${ctx}/surveyFranchisee/selectFranchisee?surveyCode="+surveyCode+"&btnCode=franchisee"
        });
    }

    function selectArea(){
        var orgProvinceId = $("#provinceId").val();
        if(orgProvinceId == 0){
            return;
        }
        ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgProvinceId},function(v,e,p){
            $("#cityId option").remove();
            $("#cityId").append("<option value=''>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#cityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
            }
            $("#districtId option").remove();
            $("#districtId").append("<option value=''>请选择</option>");
        });

        //获取“区域名称”及“区域名称id”
        $("#areaTypeId").val(orgProvinceId);
        var val = $("#provinceId").find("option:selected").text();
        $("#areaName").val(val);
    }
    function  selectAreaCity(){
        var orgCityId = $("#cityId").val();
        if(orgCityId == 0){
            return;
        }
        ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgCityId},function(v,e,p){
            $("#districtId option").remove();
            $("#districtId").append("<option value=''>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#districtId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
            }
        });
        //获取“区域名称”及“区域名称id”
        $("#areaTypeId").val(orgCityId);
        var val = $("#provinceId").find("option:selected").text()+$("#cityId").find("option:selected").text();
        $("#areaName").val(val);
    }

    function  selectAreaDistrict(){
        //获取“区域名称”及“区域名称id”
        var orgDistrictId = $("#districtId").val();
        $("#areaTypeId").val(orgDistrictId);
        var val = $("#provinceId").find("option:selected").text()+$("#cityId").find("option:selected").text()+$("#districtId").find("option:selected").text();
        $("#areaName").val(val);
    }

    toBuild();
    function toBuild(){
        var objS = document.getElementById("areaType");
        var areaType = objS.options[objS.selectedIndex].value;
        if(areaType == 1){
            $("#provinceDiv").show();
            $("#cityDiv").hide();
            $("#districtDiv").hide();

            $("#areaTypeId").val($("#provinceId").val());
            var val = $("#provinceId").find("option:selected").text();
            $("#areaName").val(val);
        }else if(areaType == 2){
            $("#provinceDiv").show();
            $("#cityDiv").show();
            $("#districtDiv").hide();

            $("#areaTypeId").val($("#cityId").val());
            var val = $("#cityId").find("option:selected").text();
            $("#areaName").val(val);
        }else if(areaType == 3){
            $("#provinceDiv").show();
            $("#cityDiv").show();
            $("#districtDiv").show();

            $("#areaTypeId").val($("#districtId").val());
            var val = $("#districtId").find("option:selected").text();
            $("#areaName").val(val);
        }
    }

    function goBack(){
        var val = $("#provinceId").find("option:selected").text();
        var val1 = $("#cityId").find("option:selected").text();
        var val2 = $("#districtId").find("option:selected").text();
        $("#province").val(val);
        $("#city").val(val1);
        $("#district").val(val2);
    }

    var value = '${surveyFranchisee.acceptUserIdcardUrl}';
    var id = 0;
    $('#materialFileupload').fileupload({
        done: function (e, data) {
            id = id + 1;
            var r  = data.result;
            var file = r.surveyFile;
            var vSrc = "";
            var v = file.filePath;
            if(file.fileExt == "txt"){
                vSrc = "${ctx}/img/txt.png";
            }else if(file.fileExt == "docx" || file.fileExt == "doc"){
                vSrc = "${ctx}/img/word.png";
            }else if(file.fileExt == "xls" || file.fileExt == "xlsx"){
                vSrc = "${ctx}/img/excel.png";
            }else if(file.fileExt == "pdf"){
                vSrc = "${ctx}/img/pdf.jpg";
            }else{
                vSrc = file.filePath;
            }
            $("#bankUrlDiv").append("<div id='div_" + id + "' class='col-sm-3'><img width='80' height='80' class='picToBig' onclick='pic__click(this)' src='" + vSrc + "' /><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id='" + id + "'  name='chknames' type='checkbox' value='" + v + "'> </div>");
            value += file.filePath+ ',';

            $("#bankUrl").val(value);
            $("#bankUrlDiv").show();
            $("#bankUrlDeleteDiv").show();

        }
    });

    var divs = [];
    function del(){
        var chks = document.getElementsByName('chknames');
        for(var i = 0 ; i < chks.length ;i++){
            var chk = chks[i];
            if(chk.checked){
                value = value.replace(chk.value + ",","");
                divs.push($("#div_" + chk.id));
            }
        }
        for(var i = 0 ; i < divs.length ;i++){
            divs[i].remove();
        }
        $("#bankUrl").val(value);
    }

    var idcardValue = '${surveyFranchisee.acceptUserIdcardUrl}';
    var idcardId = 0;
    $('#materialFileupload2').fileupload({
        done: function (e, data) {
            idcardId = idcardId + 1;
            var r  = data.result;
            var file = r.surveyFile;
            var vSrc = "";
            var v = file.filePath;
            if(file.fileExt == "txt"){
                vSrc = "${ctx}/img/txt.png";
            }else if(file.fileExt == "docx" || file.fileExt == "doc"){
                vSrc = "${ctx}/img/word.png";
            }else if(file.fileExt == "xls" || file.fileExt == "xlsx"){
                vSrc = "${ctx}/img/excel.png";
            }else if(file.fileExt == "pdf"){
                vSrc = "${ctx}/img/pdf.jpg";
            }else{
                vSrc = file.filePath;
            }
            $("#idcardUrlDiv").append("<div id='div_idCard_" + idcardId + "' class='col-sm-3'><img width='80' height='80' class='picToBig' onclick='pic__click(this)' src='" + vSrc + "' /><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id='" + idcardId + "'  name='idcardChknames' type='checkbox' value='" + v + "'> </div>");
            idcardValue += file.filePath+ ',';

            $("#acceptUserIdcardUrl").val(idcardValue);
            $("#idcardUrlDiv").show();
            $("#idcardUrlDeleteDiv").show();

        }
    });

    var idcardDivs = [];
    function idcardDel(){
        var chks = document.getElementsByName('idcardChknames');
        for(var i = 0 ; i < chks.length ;i++){
            var chk = chks[i];
            if(chk.checked){
                value = value.replace(chk.value + ",","");
                idcardDivs.push($("#div_idCard_" + chk.id));
            }
        }
        for(var i = 0 ; i < idcardDivs.length ;i++){
            idcardDivs[i].remove();
        }
        $("#acceptUserIdcardUrl").val(value);
    }


    showIdcardImgs();
    function showIdcardImgs(){
        var idcardImgs = '${surveyFranchisee.acceptUserIdcardUrl}';
        if(idcardImgs!=null && idcardImgs!=''){
            idcardId = idcardImgs.split(",").length - 1;
            for(var i=0 ; i< idcardImgs.split(",").length - 1; i++){
                var j = i+1;
                $("#idcardImgsDiv").append("<div id='div_idCard_" + j + "' class='col-sm-3'><img width='80' height='80' class='picToBig' src='" + idcardImgs.split(",")[i] + "' /><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id='" + j + "'  name='idcardChknames' type='checkbox' value='" + idcardImgs.split(",")[i] + "'> </div>");

            }
        }
    }

    showBankImgs();
    function showBankImgs(){
        var bankImgs = '${surveyFranchisee.bankUrl}';
        if(bankImgs!=null && bankImgs!=''){
            id = bankImgs.split(",").length - 1;
            for(var i=0 ; i< bankImgs.split(",").length - 1; i++){
                var j = i+1;
                $("#bankImgsDiv").append("<div id='div_" + j + "' class='col-sm-3'><img width='80' height='80' class='picToBig' src='" + bankImgs.split(",")[i] + "' /><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id='" + j + "'  name='chknames' type='checkbox' value='" + bankImgs.split(",")[i] + "'> </div>");

            }
        }
    }

    //加载图片放大
    function pic__click(data){
        var bigImgSrc = data.src
        $('#pic_bigimg').find('img').attr('src', bigImgSrc)
        $('#pic_bigimg').show()
    }
</script>
</body>
</html>