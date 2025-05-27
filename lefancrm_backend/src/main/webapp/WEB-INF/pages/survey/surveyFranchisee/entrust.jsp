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
    <form id="editForm" role="form" action="${ctx}/baseSurvey/operate" method="post">
        <input type="hidden" name="userId" value="${userId}">
        <input type="hidden" name="btnCode" value="${btnCode}">
        <input type="hidden" name="surveyCode" value="${surveyCode}">
        <input type="hidden" name="franchiseeId" value="${franchiseeId}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">调查人姓名</th>
                    <td width="70%">
                        ${userName}
                    </td>
                </tr>
                <tr>
                <tr>
                    <th width="30%" class="active">身份证</th>
                    <td width="70%">
                        <input type="text" id = "idcard" name="idcard" value="${surveyInvestigator.idcard}" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">所在区域</th>
                    <td width="80%" class="form-inline">
                        <div class="form-group">
                            省 <select id = "provinceId" name="provinceId" style="width: 90px;" onchange="selectArea()" class="form-control" required="required">
                            <option value="">请选择</option>
                            <c:forEach items="${apiRsp.results}" var="area">
                                <option value="${area.areaId}">${area.areaName}</option>
                            </c:forEach>
                        </select>
                            <div id="div_city"></div>
                            <div id="div_district"></div>
                        </div>
                        <div class="form-group">
                            市 <select id = "cityId" name="cityId" style="width: 90px;" onclick="selectAreaCity()" class="form-control" required="required">
                            <option value="">请选择</option>
                        </select>

                        </div>
                        <div class="form-group">
                            区 <select id = "districtId" name="districtId" style="width: 90px;" class="form-control" required="required">
                            <option value="">请选择</option>
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
                        <input type="text" id="address" name="address" class="form-control" value="${surveyInvestigator.address}" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">调查员等级</th>
                    <td width="70%">
                        <select name="levelId" id="levelId" class="form-control" required="required">
                            <c:forEach items="${surveyLevels}" var="item">
                                <option value="${item.id}" <c:if test="${surveyInvestigator.titleId == item.id}"> selected="selected" </c:if>>${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">基础积分</th>
                    <td width="70%">
                        <input type="number" name="basicIntegral" id="basicIntegral" value="85" class="form-control"/>
                    </td>
                </tr>
                <%--<tr>
                    <th width="20%" class="active">覆盖区域</th>
                    <td  width="80%">
                        <input type="text" id="includeAreaName" name="includeAreaName" value="${surveyInvestigator.includeAreaName}" style="width: 420px; display: inline-block;" class="form-control" readonly>
                        <input type="hidden" value="" name="includeArea" id="includeArea" value="${surveyInvestigator.includeArea}"/>
                        <input type="button" value="选  择" onclick="selectIncludeArea()"  style="width: 58px;  height: 32px;"/>
                    </td>
                </tr>--%>
                <tr>
                    <th width="30%" class="active">第一责任区</th>
                    <td width="70%">
                        <input type="text" id = "resArea" name="resArea" value="" style="width: 420px; display: inline-block;" class="form-control">
                        <input type="button" value="选  择" onclick="selectIncludeArea(2)"  style="width: 58px;  height: 32px;"/>
                        <input type="hidden" name="resAreaId" id="resAreaId" value=""/>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">其它覆盖区域</th>
                    <td width="70%">
                        <input type="text" id = "otherOverlayArea" name="otherOverlayArea" value="" style="width: 420px; display: inline-block;" class="form-control">
                        <input type="button" value="选  择" onclick="selectIncludeArea(3)"  style="width: 58px;  height: 32px;"/>
                        <input type="hidden" name="otherOverlayAreaId" id="otherOverlayAreaId" value=""/>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">擅长领域</th>
                    <td  width="80%">
                        <input type="text" id="includeBusName" name="includeBusName" value="${surveyInvestigator.includeBusName}" style="width: 420px; display: inline-block;" class="form-control" readonly>
                        <input type="hidden" id="includeBus" name="includeBus" value="${surveyInvestigator.includeBus}">
                        <input type="button" value="选  择" onclick="selectIncludeBus()"  style="width: 58px;  height: 32px;"/>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">擅长任务类型</th>
                    <td  width="80%">
                        <input type="text" id="includeTaskName" name="includeTaskName" value="${surveyInvestigator.includeTaskName}" style="width: 420px; display: inline-block;" class="form-control" readonly>
                        <input type="hidden" id="includeTask" name="includeTask" value="${surveyInvestigator.includeTask}">
                        <input type="button" value="选  择" onclick="selectIncludeTask()"  style="width: 58px;  height: 32px;"/>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">是否需要带教老师</th>
                    <td width="70%">
                        <div>
                            <select name="isNecessary" id="isNecessary" class="form-control isNewPeople" required="required">
                                <option value="0">是</option>
                                <option value="1">否</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr id="teachingTeacher">
                    <th width="20%" class="active">带教老师</th>
                    <td width="70%">
                        <div>
                            <select name="teacherUserId" id="teacherUserId" class="form-control isNewPeople" required="required">
                                <option value="0">请选择带教老师</option>
                                <c:forEach items="${allInvestigatorsList}" var="item">
                                    <option  value="${item.userId}" data-name="${item.orgName}">${item.realName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">材料凭证</th>
                    <td>
                        <div>
                            <input type="hidden" id="materialImgs" name="materialImgs" value="" />
                            <div id="materialImgsDiv" style="display: none"></div>
                            <div id="deleteDiv" style="display: none">
                                <input type="button" value="删除" onclick="del()"  style="width: 64px;  height: 22px;"/>
                            </div>
                        </div>
                        <div>
                            <input id="materialFileupload" type="file" name="file" multiple data-url="${ctx}/sftp/survey/uploadSftp?modelType=entrust"><br>
                            <%--<input type="file" multiple>--%>
                        </div>

                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">手写签名</th>
                    <td>
                        <input required id="adPic" type="hidden" name="proFile">
                        <img id="infImg" width="80" height="80" src="" class="picToBig" style="display: none">
                        <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/sftp/survey/uploadSftp?modelType=investigator">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">银行卡号</th>
                    <td width="70%">
                        <input type="number" id = "bankNo"  name="bankNo" value=""  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">银行名称(含支行)</th>
                    <td width="70%">
                        <input type="text" id = "bankName" name="bankName" value=""  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">登记执业证</th>
                    <td width="70%">
                        <input type="text" id = "registerNo" name="registerNo" value=""  class="form-control">
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
<script src="${ctx}/js/jQuery.UCSelect2.js" type="text/javascript"></script>
<script type="text/javascript">
    var editor1;
    KindEditor.ready(function(K) {
         editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $(function () {

        $('select[name=teacherUserId]').UCFormSelect();

        $("#isNecessary").change(function () {
            var isNecessary=$("#isNecessary").val();
            if(isNecessary==1){
                $("#teachingTeacher").hide();
            }else{
                $("#teachingTeacher").show();
            }
        });
    })


    var submit=0;
    $("#editForm").bind('submit', function(event) {
        submit=submit+1;
        if(submit>1){
            alert("请不要重复提交！")
            return false;
        }

        var isNecessary=$("#isNecessary").val();
        if(isNecessary==0){
            var teacherUserId=$("#teacherUserId").val();
            if(teacherUserId == 0){
                alert("请选择带教老师！")
                submit=0;
                return false;
            }
        }
        var resArea=$("#resArea").val();
        resArea=resArea.replace(/(^\s*)|(\s*$)/g, "");
        if(resArea == "" || resArea == null || resArea == undefined){
            alert("请选择第一责任区！")
            submit=0;
            return false;
        }

        var basicIntegral=$("#basicIntegral").val();
        if(basicIntegral<0){
            alert("基础积分不能小于0！");
            submit=0;
            return false;
        }

        var bankNo=$("#bankNo").val();
        if(bankNo != "" && bankNo != null && bankNo != undefined){
            var pattern = /^([1-9]{1})(\d{15}|\d{16}|\d{17}|\d{18}|\d{19}|\d{20}|\d{21}|\d{22})$/;
            var bankNo=$("#bankNo").val().replace(/\s+/g, "");
            if (!pattern.test(bankNo)) {
                alert("请正确输入银行卡号！")
                submit=0;
                return false;
            }
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

    //材料凭证
    var value = "";
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
            $("#materialImgsDiv").append("<div id='div_" + id + "' class='col-sm-3'><img width='80' height='80' src='" + vSrc + "' /><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id='" + id + "'  name='chknames' type='checkbox' value='" + v + "'> </div>");
            value += file.filePath+ ',';

            $("#materialImgs").val(value);
            $("#materialImgsDiv").show();
            $("#deleteDiv").show();

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
        $("#materialImgs").val(value);
    }

    function goBack(){
        var val = $("#provinceId").find("option:selected").text();
        var val1 = $("#cityId").find("option:selected").text();
        var val2 = $("#districtId").find("option:selected").text();
        $("#province").val(val);
        $("#city").val(val1);
        $("#district").val(val2);
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

    var selectIncludeBus = function(){
        openDialog({
            frame:true,
            title:"选择领域",
            height:420,
            width:900,
            url:"${ctx}/surveyFranchisee/selectBusinessType"
        });
    }

    var selectIncludeArea = function(type){
        openDialog({
            frame:true,
            title:"选择区域",
            height:420,
            width:900,
            url:"${ctx}/surveyFranchisee/selectIncludeArea?selectionType="+type
        });
    }



    var selectIncludeTask = function(){
        openDialog({
            frame:true,
            title:"选择任务类型",
            height:420,
            width:900,
            url:"${ctx}/surveyFranchisee/selectIncludeTask"
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