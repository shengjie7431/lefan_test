<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/css/lefan14.css" rel="stylesheet" type="text/css" />

    <style>
        .UCSelect{
            width: 100%;
        }

        .UCSelect .SelectVal{
            border-radius: 4px;
        }


        .UCSelect .SelectVal div{
            padding-left: 12px;
        }
    </style>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/baseSurvey/update" method="post">
        <input type="hidden" name="id" value="${surveyInvestigator.id}">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <div class="form-group">
            <table class="table">
                <tbody>

                <tr>
                    <th width="30%" class="active">姓名</th>
                    <td width="70%">
                        <input type="text" id = "realName" name="realName" value="${surveyInvestigator.realName}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">昵称</th>
                    <td width="70%">
                        <input type="text" id = "nickName" name="nickName" value="${surveyInvestigator.nickName}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">手机号</th>
                    <td width="70%">
                        <input type="text" id = "tel" name="tel" value="${surveyInvestigator.tel}" <c:if test="${surveyInvestigator.id!=null}">readonly="readonly" </c:if>  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">身份证</th>
                    <td width="70%">
                        <input type="text" id = "idcard" name="idcard" value="${surveyInvestigator.idcard}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">称号信息</th>
                    <td width="70%">
                        <select name="titleId" id="titleId" class="form-control">
                            <c:forEach items="${levelInfo}" var="item">
                                <option value="${item.id}" <c:if test="${surveyInvestigator.titleId == item.id}"> selected="selected" </c:if>>${item.name}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" id="titleName" name="titleName" value="">
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<th width="30%" class="active">乐凡币</th>--%>
                    <%--<td width="70%">--%>
                        <%--<input type="text" id = "lefanCurrency" name="lefanCurrency" value="${surveyInvestigator.lefanCurrency}"  class="form-control">--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<th width="30%" class="active">成就点</th>--%>
                    <%--<td width="70%">--%>
                        <%--<input type="text" id = "achPoint" name="achPoint" value="${surveyInvestigator.achPoint}"  class="form-control">--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr>
                    <th width="20%" class="active">所在区域</th>
                    <td width="80%" class="form-inline">
                        <div class="form-group">
                            省 <select id = "provinceId" name="provinceId" style="width: 90px;" onchange="selectArea()" class="form-control" required="required">
                            <c:forEach items="${apiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${surveyInvestigator.provinceId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                            <div id="div_city"></div>
                            <div id="div_district"></div>
                        </div>
                        <div class="form-group">
                            市 <select id = "cityId" name="cityId" style="width: 90px;" onclick="selectAreaCity()" class="form-control" required="required">
                            <c:forEach items="${cityApiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${surveyInvestigator.cityId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>

                        </div>
                        <div class="form-group">
                            区 <select id = "districtId" name="districtId" style="width: 90px;" class="form-control" required="required">
                            <c:forEach items="${districtApiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${surveyInvestigator.districtId == area.areaId}"> selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                    </td>
                    <input type="hidden" id="divCityId" name="divCityId" value="">
                    <input type="hidden" id="province" name="province" value="">
                    <input type="hidden" id="city" name="city" value="">
                    <input type="hidden" id="district" name="district" value="">
                </tr>
                <tr>
                    <th width="20%" class="active">详细地址</th>
                    <td  width="80%">
                        <input type="text" id="address" name="address" value="${surveyInvestigator.address}" class="form-control" required="required">
                    </td>
                </tr>
                <%--<tr>
                    <th width="30%" class="active">覆盖区域</th>
                    <td width="70%">
                        <input type="text" id = "includeAreaName" name="includeAreaName" value="${surveyInvestigator.includeAreaName}"  style="width: 420px; display: inline-block;" class="form-control">
                        <input type="button" value="选  择" onclick="selectIncludeArea(1)"  style="width: 58px;  height: 32px;"/>
                        <input type="hidden" name="includeArea" id="includeArea" value="${surveyInvestigator.includeArea}"/>
                    </td>
                </tr>--%>
                <tr>
                    <th width="30%" class="active">第一责任区</th>
                    <td width="70%">
                        <input type="text" id = "resArea" name="resArea" value="${surveyInvestigator.resArea}" style="width: 420px; display: inline-block;" class="form-control">
                        <input type="button" value="选  择" onclick="selectIncludeArea(2)"  style="width: 58px;  height: 32px;"/>
                        <input type="hidden" name="resAreaId" id="resAreaId" value="${surveyInvestigator.resAreaId}"/>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">其它覆盖区域</th>
                    <td width="70%">
                        <input type="text" id = "otherOverlayArea" name="otherOverlayArea" value="${surveyInvestigator.otherOverlayArea}" style="width: 420px; display: inline-block;" class="form-control">
                        <input type="button" value="选  择" onclick="selectIncludeArea(3)"  style="width: 58px;  height: 32px;"/>
                        <input type="hidden" name="otherOverlayAreaId" id="otherOverlayAreaId" value="${surveyInvestigator.otherOverlayAreaId}"/>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">擅长领域</th>
                    <td width="70%">
                        <input type="text" id = "includeBusName" name="includeBusName" value="${surveyInvestigator.includeBusName}" style="width: 420px; display: inline-block;" class="form-control">
                        <input type="hidden" id="includeBus" name="includeBus" value="${surveyInvestigator.includeBus}">
                        <input type="button" value="选  择" onclick="selectIncludeBus()"  style="width: 58px;  height: 32px;"/>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">擅长任务类型</th>
                    <td width="70%">
                        <input type="text" id = "includeTaskName" name="includeTaskName" value="${surveyInvestigator.includeTaskName}" style="width: 420px; display: inline-block;" class="form-control">
                        <input type="hidden" id="includeTask" name="includeTask" value="${surveyInvestigator.includeTask}">
                        <input type="button" value="选  择" onclick="selectIncludeTask()"  style="width: 58px;  height: 32px;"/>
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<th width="30%" class="active">认证状态</th>--%>
                    <%--<td width="70%">--%>
                        <%--<select name="authType" id="authType" class="form-control" required="required">--%>

                            <%--<option value="0" <c:if test="${surveyInvestigator.authType == 0}">selected="selected" </c:if>>未认证</option>--%>
                            <%--<option value="1" <c:if test="${surveyInvestigator.authType == 1}">selected="selected" </c:if>>认证中</option>--%>
                            <%--<option value="2" <c:if test="${surveyInvestigator.authType == 2}">selected="selected" </c:if>>认证通过</option>--%>
                            <%--<option value="3" <c:if test="${surveyInvestigator.authType == 3}">selected="selected" </c:if>>认证不通过</option>--%>
                        <%--</select>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr>
                    <th width="30%" class="active">调查员类型</th>
                    <td width="70%">
                        <select name="type" id="type" class="form-control" required="required">

                            <option value="1" <c:if test="${surveyInvestigator.type == 1}">selected="selected" </c:if>>直营</option>
                            <option value="2" <c:if test="${surveyInvestigator.type == 2}">selected="selected" </c:if>>合伙</option>
                            <option value="3" <c:if test="${surveyInvestigator.type == 3}">selected="selected" </c:if>>合作</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">是否渠道调查员</th>
                    <td width="70%">
                        <select name="channelType" id="channelType" class="form-control" required="required">
                            <option value="0" <c:if test="${surveyInvestigator.channelType == 0}">selected="selected" </c:if>>否</option>
                            <option value="1" <c:if test="${surveyInvestigator.channelType == 1}">selected="selected" </c:if>>是</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">带教老师</th>
                    <td width="70%">
                        <div>
                            <input type="hidden" id="teaUserId" value="${surveyInvestigator.teacherUserId}"/>
                            <select name="teacherUserId" id="teacherUserId" class="form-control isNewPeople" required="required">
                                <option value="0">请选择带教老师</option>
                            <c:forEach items="${allInvestigatorsList}" var="item">
                                <option ${item.userId eq surveyInvestigator.teacherUserId ?'selected':''}  value="${item.userId}" data-name="${item.orgName}">${item.realName}</option>
                            </c:forEach>
                        </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">基础积分</th>
                    <td width="70%">
                        <input type="number" id="basicIntegral" min="0" step="0.01"  name="basicIntegral" value="${surveyInvestigator.basicIntegral}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">材料凭证</th>
                    <td>
                        <c:forEach items="${apiRspFile.results}" var="item">
                                <img src="${item.filePath}" width="75;" height="75;" class="picToBig">
                        </c:forEach>

                        <%--<div>--%>
                            <%--<input type="hidden" id="materialImgs" name="materialImgs" value="" />--%>
                            <%--<div id="materialImgsDiv" style="display: none"></div>--%>
                            <%--<div id="deleteDiv" style="display: none">--%>
                                <%--<input type="button" value="删除" onclick="del()"  style="width: 64px;  height: 22px;"/>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div>--%>
                            <%--<input id="materialFileupload" type="file" name="file" multiple data-url="${ctx}/uploadFile/"><br>--%>
                        <%--</div>--%>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">手写签名</th>
                    <td>
                        <input required id="adPic" type="hidden" name="proFile">
                        <c:if test="${surveyInvestigator.signUrl!=null}">
                            <img  width="80" height="80" src="${surveyInvestigator.signUrl}" class="picToBig">
                        </c:if>
                        <img id="infImg" width="80" height="80" src="" class="picToBig" style="display: none">
                        <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/sftp/survey/uploadSftp?modelType=investigator">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">银行卡号</th>
                    <td width="70%">
                        <input type="number" id = "bankNo" name="bankNo" value="${surveyInvestigator.bankNo}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">银行名称(含支行)</th>
                    <td width="70%">
                        <input type="text" id = "bankName" name="bankName" value="${surveyInvestigator.bankName}"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">是否有暖哇账号</th>
                    <td width="70%">
                        <select name="haveNwAccount" id="haveNwAccount" class="form-control" required="required">

                            <option value="0" <c:if test="${surveyInvestigator.haveNwAccount == null || surveyInvestigator.haveNwAccount == 0}">selected="selected" </c:if>>无</option>
                            <option value="1" <c:if test="${surveyInvestigator.haveNwAccount != null && surveyInvestigator.haveNwAccount == 1}">selected="selected" </c:if>>有</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">登记执业证</th>
                    <td width="70%">
                        <input type="text" id = "registerNo" name="registerNo" value="${surveyInvestigator.registerNo}"  class="form-control">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit"  onclick="goBack()" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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
<script src="${ctx}/js/jQuery.UCSelect2.js" type="text/javascript"></script>
<script type="text/javascript">

    /*$(function () {
        var teaUserId=$("#teaUserId").val();
        var teacherUserId=$("#teacherUserId").val();

    })*/

    var editor1;
    KindEditor.ready(function(K) {
         editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $(function () {

        $('select[name=teacherUserId]').UCFormSelect();
    })

    $("#editForm").bind('submit', function(event) {
        var teacherUserId=$("#teacherUserId").val();
        if(teacherUserId == 0 ){
            alert("请选择带教老师！")
            return false;
        }
        var resArea=$("#resArea").val();
        resArea=resArea.replace(/(^\s*)|(\s*$)/g, "");
        if(resArea == "" || resArea == null || resArea == undefined){
            alert("请选择第一责任区！")
            return false;
        }

        var bankNo=$("#bankNo").val();
        if(bankNo != "" && bankNo != null && bankNo != undefined){
            var pattern = /^([1-9]{1})(\d{15}|\d{16}|\d{17}|\d{18}|\d{19}|\d{20}|\d{21}|\d{22})$/;
            var bankNo=$("#bankNo").val().replace(/\s+/g, "");
            if (!pattern.test(bankNo)) {
                alert("请正确输入银行卡号！")
                return false;
            }
        }

        var basicIntegral=$("#basicIntegral").val();
        if(basicIntegral == "" || basicIntegral == null || basicIntegral == undefined){
            alert("基础积分不能为空！")
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

    var selectIncludeBus = function(){
        openDialog({
            frame:true,
            title:"选择领域",
            height:450,
            width:1000,
            url:"${ctx}/surveyFranchisee/selectBusinessType"
        });
    }

    var selectIncludeArea = function(type){
        openDialog({
            frame:true,
            title:"选择区域",
            height:450,
            width:1000,
            url:"${ctx}/surveyFranchisee/selectIncludeArea?selectionType="+type
        });
    }

    var selectIncludeTask = function(){
        openDialog({
            frame:true,
            title:"选择任务类型",
            height:450,
            width:1000,
            url:"${ctx}/surveyFranchisee/selectIncludeTask"
        });
    }

    function goBack(){
        var val = $("#provinceId").find("option:selected").text();
        var val1 = $("#cityId").find("option:selected").text();
        var val2 = $("#districtId").find("option:selected").text();
        $("#province").val(val);
        $("#city").val(val1);
        $("#district").val(val2);

        var val3 = $("#titleId").find("option:selected").text();
        $("#titleName").val(val3);


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
        })
    }
    function  selectAreaCity(){
        var orgCityId = $("#cityId").val();
        if(orgCityId == 0){
            return;
        }
        ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgCityId},function(v,e,p){
            $("#districtId option").remove();
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#districtId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
            }
        })
    }

    function selectArea1(){
        var shiId = $("#shiId").val();
        var quId = $("#quId").val();
        var orgProvinceId = $("#provinceId").val();
        if(orgProvinceId == 0){
            return;
        }
        ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgProvinceId},function(v,e,p){
            $("#cityId option").remove();
            $("#cityId").append("<option value=''>请选择</option>");
            console.log(e.data.results);
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                if(val.areaId == shiId){
                    $("#cityId").append("<option selected='selected' value='"+val.areaId+"'>"+val.areaName+"</option>");
                }else{
                    $("#cityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }
            }
            $("#districtId option").remove();
            $("#districtId").append("<option value=''>请选择</option>");
            selectAreaCity1(shiId,quId);
        });

    }

    function  selectAreaCity1(shiId,quId){
        ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":shiId},function(v,e,p){
            $("#districtId option").remove();
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                if(val.areaId == quId){
                    $("#districtId").append("<option selected='selected' value='"+val.areaId+"'>"+val.areaName+"</option>");
                }else {
                    $("#districtId").append("<option value='" + val.areaId + "'>" + val.areaName + "</option>");
                }
            }
        });
    }

    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var file=r.surveyFile;
            $("#infImg").show()
            $("#infImg").attr("src",file.filePath);
            $("#adPic").val(file.filePath);
        }
    });
</script>
</body>
</html>