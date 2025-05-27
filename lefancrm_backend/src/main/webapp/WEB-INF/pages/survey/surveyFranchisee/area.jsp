<%--
  Created by IntelliJ IDEA.
  User: lixianfeng
  Date: 2018/10/18
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>

    </script>
</head>
<body>

<div class="container">
    <form id="editForm" role="form" action="${ctx}/surveyFranchisee/choiceIncludeArea" method="post">
        <input type="hidden" id="selectionType" value="${selectionType}"/>
        <input type="hidden" name="id" value="${id}" />
        <input type="hidden" name="btnCode" value="${btnCode}" />
        <div class="form-group">
            <table class="table">
                <tbody>
                    <tr>
                        <th>区域</th>
                        <td>
                            <select name="areaType" id ="areaType" class="form-control" required="required" onchange="toBuild()">
                                <option value="1" >省级</option>
                                <option value="2" selected>市级</option>
                                <option value="3" >区级</option>
                            </select>
                            <input type="hidden" id="province" name="province" value="">
                            <input type="hidden" id="city" name="city" value="">
                            <input type="hidden" id="district" name="district" value="">
                        </td>
                    </tr>
                    <tr>
                        <th><span id="span_province">省</span></th>
                        <td><select id = "provinceId" name="provinceId" onchange="selectArea()" class="form-control" required="required">
                            <option value="">请选择</option>
                            <c:forEach items="${apiRsp.results}" var="area">
                            <option value="${area.areaId}">${area.areaName}</option>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <th><span id="span_city">市</span></th>
                        <td>
                            <select id = "cityId" name="cityId" onclick="selectAreaCity()" class="form-control" >
                                <option value="">请选择</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><span id="span_district">区</span></th>
                        <td>
                            <select id = "districtId" name="districtId" onclick="selectAreaDistrict()" class="form-control" >
                                <option value="">请选择</option>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" onclick="return businessOK()" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>确认</button>
        </div>
    </form>
</div>

<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
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
        ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
        event.preventDefault();
    });

    function validFile(btnCode){
        if(btnCode == 'upload' || btnCode == 'primary'){
            var path = $("#path").val();
            if(!path){
                alert("请上传报告");return false;
            }
        }else if(btnCode == 'direction'){
            var val = $("#provinceId").find("option:selected").text();
            var val1 = $("#cityId").find("option:selected").text();
            var val2 = $("#districtId").find("option:selected").text();
            $("#province").val(val);
            $("#city").val(val1);
            $("#district").val(val2);
        }
        return true;
    }

    toBuild();

    function toBuild(){
        var value = $("#areaType").val();
        if(value == 1){
            $("#provinceId").attr("required","required");
            $("#cityId").attr("required",null);
            $("#districtId").attr("required",null);
            $("#span_city").hide();
            $("#span_district").hide();
            $("#cityId").hide();
            $("#districtId").hide();
        }else if(value == 2){
            $("#provinceId").attr("required","required");
            $("#cityId").attr("required","required");
            $("#districtId").attr("required",null);
            $("#span_city").show();
            $("#span_district").hide();
            $("#cityId").show();
            $("#districtId").hide();
        }else if(value == 3){
            $("#provinceId").attr("required","required");
            $("#cityId").attr("required","required");
            $("#districtId").attr("required","required");
            $("#span_city").show();
            $("#span_district").show();
            $("#cityId").show();
            $("#districtId").show();
        }
    }

    function returnCallback(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){

        }else{
            alert(apiRsp.msg);return;
        }
        reloadParent();
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
        var val = $("#cityId").find("option:selected").text();
        $("#areaName").val(val);
    }

    function  selectAreaDistrict(){
        //获取“区域名称”及“区域名称id”
        var orgDistrictId = $("#districtId").val();
        $("#areaTypeId").val(orgDistrictId);
        var val = $("#districtId").find("option:selected").text();
        $("#areaName").val(val);
    }



    function businessOK(){
        var areaType = $("#areaType").val();
        var name =null;
        var id= null;
        if(areaType==1){
            name = $("#provinceId").find("option:selected").text();
            id =$("#provinceId").val();
            if(id==null || id==""){
                return;
            }
        }else if(areaType==2){
            name = $("#cityId").find("option:selected").text();
            id =$("#cityId").val();
            if(id==null || id==""){
                return;
            }
        }else if(areaType==3){
            name = $("#districtId").find("option:selected").text();
            id =$("#districtId").val();
            if(id==null || id==""){
                return;
            }
        }

        /*var ids = parent.$("#includeArea").val();
        if(ids ==""){
            parent.$("#includeArea").val(id);
        }else{
            parent.$("#includeArea").val(ids + "," + id);
        }

        var names = parent.$("#includeAreaName").val();
        if(names==""){
            parent.$("#includeAreaName").val(name);
        }else{
            parent.$("#includeAreaName").val(names + "," + name);
        }
*/
        var selectionType=$("#selectionType").val();
        if(selectionType == 1){
            var ids = parent.$("#includeArea").val();
            if(ids ==""){
                parent.$("#includeArea").val(id);
            }else{
                parent.$("#includeArea").val(ids + "," + id);
            }

            var names = parent.$("#includeAreaName").val();
            if(names==""){
                parent.$("#includeAreaName").val(name);
            }else{
                parent.$("#includeAreaName").val(names + "," + name);
            }
        }else if(selectionType == 2){
            var ids = parent.$("#resAreaId").val();
            if(ids ==""){
                parent.$("#resAreaId").val(id);
            }else{
                parent.$("#resAreaId").val(ids + "," + id);
            }

            var names = parent.$("#resArea").val();
            if(names==""){
                parent.$("#resArea").val(name);
            }else{
                parent.$("#resArea").val(names + "," + name);
            }
        }else if(selectionType == 3){
            var ids = parent.$("#otherOverlayAreaId").val();
            if(ids ==""){
                parent.$("#otherOverlayAreaId").val(id);
            }else{
                parent.$("#otherOverlayAreaId").val(ids + "," + id);
            }

            var names = parent.$("#otherOverlayArea").val();
            if(names==""){
                parent.$("#otherOverlayArea").val(name);
            }else{
                parent.$("#otherOverlayArea").val(names + "," + name);
            }
        }
        closeDialog();
    }
</script>
</body>
</html>
