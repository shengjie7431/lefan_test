<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        #d1:after{
            content: ' ';
            height: 0;
            clear:both;
        }
        .imgWrap{
            position: relative;
            width: 100px;
            height: 100px;
            float: left;
            margin: 15px;
        }
        .imgWrap img{
            width: 100px;
            height: 100px;
        }
        .imgWrap i{
            position: absolute;
            top: 0;
            right: 0;
            font-size: 18px;
            border-radius: 50%;
            padding: 5px;
            line-height: 1;
            cursor: pointer;
            color: #f00;
            display: none;
            background:rgba(33,33,33,.15);
        }
        .imgWrap:hover i{
            display: block;
        }
    </style>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>
        function groBack(){
            var val = $("#orgProvinceId").find("option:selected").text();
            var val1 = $("#orgCityId").find("option:selected").text();
            var val2 = $("#orgDistrictId").find("option:selected").text();
            $("#orgProvince").val(val);
            $("#orgCity").val(val1);
            $("#orgDistrict").val(val2);
        }
        function selectArea(){
            var orgProvinceId = $("#orgProvinceId").val();
            if(orgProvinceId == 0){
                return;
            }
            ajaxSubmit("${ctx}/org/selectArea",{"parentId":orgProvinceId},function(v,e,p){
                $("#orgCityId option").remove();
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#orgCityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }

            })
        }
        function  selectAreaCity(){
            var orgCityId = $("#orgCityId").val();
            if(orgCityId == 0){
                return;
            }
            ajaxSubmit("${ctx}/org/selectArea",{"parentId":orgCityId},function(v,e,p){
                $("#orgDistrictId option").remove();
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#orgDistrictId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }

            })
        }
    </script>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/org/edit" method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <input type="hidden" id="orgId" name="orgId" value="${org.id}">
                <tr>
                    <th width="20%" class="active">机构名称</th>
                    <td width="80%">
                       <input type="text" id = "orgName" name="orgName"  style="width: 400px;" value="${org.orgName}" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">机构电话</th>
                    <td width="80%">
                        <input type="text" id = "orgTel" name="orgTel" style="width: 400px;" value="${org.orgTel}" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">联系人</th>
                    <td width="80%">
                        <input type="text" id = "linkName" name="linkName"  style="width: 400px;" value="${org.linkName}" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">联系人电话</th>
                    <td width="80%">
                        <input type="text" id = "linkTel" name="linkTel"  style="width: 400px;" value="${org.linkTel}" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">机构所在地</th>
                    <td width="80%" class="form-inline">
                        <div class="form-group">
                    省
                        <select id = "orgProvinceId" name="orgProvinceId"  style="width: 90px;"  onclick="selectArea()" class="form-control">
                            <option value="0">请选择</option>
                            <c:forEach items="${apiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${area.areaId == org.orgProvinceId}">selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                            <div class="form-group">
                    市
                        <select id = "orgCityId" name="orgCityId"  style="width: 90px;" onclick="selectAreaCity()" class="form-control">
                            <option value="${org.orgCityId}">${org.orgCity}</option>
                        </select>
                            </div>
                                <div class="form-group">
                    区
                        <select id = "orgDistrictId" name="orgDistrictId"  style="width: 90px;" class="form-control">
                            <option value="${org.orgDistrictId}">${org.orgDistrict}</option>
                        </select>
                                </div>
                    </td>
                    <input type="hidden" id="orgProvince" name="orgProvince" value="">
                    <input type="hidden" id="orgCity" name="orgCity" value="">
                    <input type="hidden" id="orgDistrict" name="orgDistrict" value="">
                </tr>
                <tr>
                    <th width="20%" class="active">机构所在详细地址</th>
                    <td width="80%">
                        <input type="text" id = "orgAddress" name="orgAddress" style="width: 400px;"value="${org.orgAddress}" class="form-control">
                    </td>
                </tr>
             <tr>
                    <th width="20%" class="active">机构资料</th>
                    <td>
                         <input required id="adPic" type="hidden" value="" name="img">
                        <div id="d1">
                            <c:forEach items="${org.files}" var="fi">
                                <div class='imgWrap'>
                                    <a href="${fi.filePath}" target="_blank"><img src="${fi.filePath}" width="100" height="100"></a>
                                    <i class='glyphicon glyphicon-remove'  onclick='deleteImage(this,-1,${fi.id})'></i>
                                 </div>
                            </c:forEach>

                        </div>
                        <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit"  onclick="groBack()" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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
                var val = "<div class='imgWrap'><a href='http://ddrapi.shlefan.com/sftp/files/"+pathImg+"' target='_blank'>" +
                        "<img  src='http://ddrapi.shlefan.com/sftp/files/"+pathImg+"' width='100' height='100'>" +
                        "<i class='glyphicon glyphicon-remove'  onclick='deleteImage(this,-1,null)'></i></div>";
                $("#d1").append(val);
                var img_url = $("#adPic").val();
                if(img_url == null || img_url == ""){
                    img_url = "http://ddrapi.shlefan.com/sftp/files/"+pathImg;
                    $("#adPic").val(img_url);
                }else{
                    img_url += ",http://ddrapi.shlefan.com/sftp/files/"+pathImg;
                    $("#adPic").val(img_url);
                }
            }else {
                alert("上传失败，请重试111");
            }
        }
    });
    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

    function deleteImage(dom,dex,fileId){
        if(fileId != null){
            ajaxSubmit("${ctx}/org/delFile",{"fileId":fileId},reload,"删除成功！","你确定删除此图片？",null);
        }else{
            var r=confirm("你确定删除此图片!");
            if (r==true)
            {
                var img_url = $("#adPic").val();
                var arr = img_url.split(",");
                var del = $(dom).siblings('img').attr("src");
                img_url = "";
                for(var i = 0; i < arr.length; i++){
                    if(arr[i] != del){
                        img_url+=arr[i]+",";
                    }
                    if(i == (arr.length - 1)){
                        img_url = img_url.substring(0,img_url.length -1);
                    }
                }
                $(dom).parent().remove();
                $("#adPic").val(img_url);
                alert("删除成功");
            }
        }
    }
</script>
</body>
</html>