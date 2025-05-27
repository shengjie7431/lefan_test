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
        function goBack(){
            var val = $("#targetProvinceId").find("option:selected").text();
            var val1 = $("#targetCityId").find("option:selected").text();
            var val2 = $("#targetDistrictId").find("option:selected").text();
            $("#targetProvince").val(val);
            $("#targetCity").val(val1);
            $("#targetDistrict").val(val2);
        }
        function selectArea(){
            var orgProvinceId = $("#targetProvinceId").val();
            if(orgProvinceId == 0){
                return;
            }
            ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgProvinceId},function(v,e,p){
                $("#targetCityId option").remove();
                $("#targetCityId").append("<option value=''>请选择</option>");
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#targetCityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }
                $("#targetDistrictId option").remove();
                $("#targetDistrictId").append("<option value=''>请选择</option>");
            })
        }
        function  selectAreaCity(){
            var orgCityId = $("#targetCityId").val();
            if(orgCityId == 0){
                return;
            }
            ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgCityId},function(v,e,p){
                $("#targetDistrictId option").remove();
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#targetDistrictId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }
            })
        }
    </script>
</head>
<body>

<div class="container">
<form id="editForm" role="form" action="${ctx}/law/save" method="post">
    <div class="form-group">
        <table class="table">
            <tbody>
                <tr>
                    <th width="20%" class="active">委托人姓名</th>
                    <td width="80%">
                        <input type="text" id="entrustUserName" name="entrustUserName" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">委托人联系方式</th>
                    <td width="80%">
                        <input type="text" id="entrustUserTel" name="entrustUserTel" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">申请人姓名</th>
                    <td width="80%">
                        <input type="text" id="appUserName" name="appUserName" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">申请人联系方式</th>
                    <td width="80%">
                        <input type="text" id="appUserTel" name="appUserTel" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">联系人姓名</th>
                    <td width="80%">
                        <input type="text" id="linkName" name="linkName" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">联系人联系方式</th>
                    <td width="80%">
                        <input type="text" id="linkTel" name="linkTel" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">委托事项</th>
                    <td width="80%">
                        <select name="caseType" class="form-control" style="width: 400px;" required="required">
                            <option value="1">财产险评估</option>
                            <option value="2">医疗费用评估</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">标的金额</th>
                    <td width="80%">
                        <input type="number" step="0.01" id="targetAmount" name="targetAmount" onkeyup="money()" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">评估费用</th>
                    <td width="80%">
                        <input type="number" step="0.01" id="assessFee" name="assessFee" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">委托时间</th>
                    <td width="80%">
                        <input name="entrustTime" type="text" class="form-control" style="width: 400px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">标的所在地</th>
                    <td width="80%" class="form-inline">
                        <div class="form-group">
                            省 <select id = "targetProvinceId" name="targetProvinceId" style="width: 90px;" onchange="selectArea()" class="form-control" required="required">
                            <option value="">请选择</option>
                            <c:forEach items="${apiRsp.results}" var="area">
                                <option value="${area.areaId}">${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                        <div class="form-group">
                            市 <select id = "targetCityId" name="targetCityId" style="width: 90px;" onclick="selectAreaCity()" class="form-control" required="required">
                            <option value="">请选择</option>
                        </select>
                        </div>
                        <div class="form-group">
                            区 <select id = "targetDistrictId" name="targetDistrictId" style="width: 90px;" class="form-control" required="required">
                            <option value="">请选择</option>
                        </select>
                        </div>
                    </td>
                    <input type="hidden" id="targetProvince" name="targetProvince" value="">
                    <input type="hidden" id="targetCity" name="targetCity" value="">
                    <input type="hidden" id="targetDistrict" name="targetDistrict" value="">
                </tr>
                <tr>
                    <th width="20%" class="active">详细地址</th>
                    <td  width="80%">
                        <input type="text" id="targetAddress" name="targetAddress" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">委托需求描述</th>
                    <td width="80%">
                        <textarea  id="entrustDesc" name="entrustDesc" style="width: 400px; height: 150px" class="form-control"></textarea>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">备注说明</th>
                    <td width="80%">
                        <textarea  id="remark" name="remark" style="width: 400px; height: 150px" class="form-control"></textarea>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">材料</th>
                    <td>
                        <div>
                            <input type="hidden" id="uploadPaths" name="uploadPaths" value="" />
                            <div id="uploadDiv" style="display: none"></div>
                        </div>
                        <div>
                            <input id="lawFileupload" type="file" name="file" multiple data-url="${ctx}/uploadFile/"><br>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="modal-footer">
        <button type="submit" onclick="goBack()" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });


    //材料凭证
    var value = "";
    $('#lawFileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            var ext = pathImg.substring(pathImg.lastIndexOf(".") + 1, pathImg.length);
            if(ext == "txt"){
                $("#uploadDiv").append("<img width='80' height='80' src='../img/txt.png' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }else if(ext == "docx" || ext == "doc"){
                $("#uploadDiv").append("<img width='80' height='80' src='../img/word.png' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }else if(ext == "xls" || ext == "xlsx"){
                $("#uploadDiv").append("<img width='80' height='80' src='../img/excel.png' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }else if(ext == "pdf"){
                $("#uploadDiv").append("<img width='80' height='80' src='../img/pdf.jpg' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + p
                athImg + ',';
            }else{
                $("#uploadDiv").append("<img width='80' height='80' src='http://ddrapi.shlefan.com/sftp/files/"+pathImg+"' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
            }
            $("#uploadPaths").val(value);
            $("#uploadDiv").show();

        }
    });

    function money(){
        var targetAmount = $("#targetAmount").val();
        $("#assessFee").val((targetAmount * 0.05).toFixed(2));
    }
</script>
</body>
</html>
