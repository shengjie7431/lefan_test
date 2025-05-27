<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
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
    <script>
        function groBack(){
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
            ajaxSubmit("${ctx}/org/selectArea",{"parentId":orgProvinceId},function(v,e,p){
                $("#orgCityId option").remove();
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#cityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }

            })
        }
        function  selectAreaCity(){
            var orgCityId = $("#cityId").val();
            if(orgCityId == 0){
                return;
            }
            ajaxSubmit("${ctx}/org/selectArea",{"parentId":orgCityId},function(v,e,p){
                $("#orgDistrictId option").remove();
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#districtId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }

            })
        }
    </script>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/corg/add" method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active">组织名称</th>
                    <td width="80%">
                       <input type="text" id = "cOrgName" name="cOrgName"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">联系人</th>
                    <td width="80%">
                        <input type="text" id = "linkName" name="linkName"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">联系人电话</th>
                    <td width="80%">
                        <input type="text" id = "lineTel" name="lineTel"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">所在地</th>
                    <td width="80%" class="form-inline">
                        <div class="form-group">
                        省 <select id = "provinceId" name="provinceId"  style="width: 90px;"  onclick="selectArea()" class="form-control">
                            <option value="0">请选择</option>
                            <c:forEach items="${apiRsp.results}" var="area">
                                <option value="${area.areaId}">${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                         <div class="form-group">
                        市 <select id = "cityId" name="cityId"  style="width: 90px;" onclick="selectAreaCity()" class="form-control">
                            <option value="0">请选择</option>
                        </select>
                            </div>
                                <div class="form-group">
                        区 <select id = "districtId" name="districtId"  style="width: 90px;" class="form-control">
                            <option value="0">请选择</option>
                        </select>
                                </div>
                    </td>
                    <input type="hidden" id="province" name="province" value="">
                    <input type="hidden" id="city" name="city" value="">
                    <input type="hidden" id="district" name="district" value="">
                    <input type="hidden" id="cOrgType" name="cOrgType" value="${cOrgType}">
                </tr>
                <tr>
                    <th width="20%" class="active">详细地址</th>
                    <td width="80%">
                        <input type="text" id = "address" name="address" style="width: 400px;" class="form-control">
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
                var val = "<div class='imgWrap'><img  src='http://ddrapi.shlefan.com/sftp/files/"+pathImg+"' width='100' height='100'>" +
                        "<i class='glyphicon glyphicon-remove'  onclick='deleteImage(this,-1)'></i></div>";
               $("#d1").append(val);
//                $("#infImg").attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
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
    function deleteImage(dom,dex){
        var r=confirm("你确定删除此图片!");
        if (r==true) {
            var img_url = $("#adPic").val();
            var arr = img_url.split(",");
            var del = $(dom).siblings('img').attr("src");
            img_url = "";
            for (var i = 0; i < arr.length; i++) {
                if (arr[i] != del) {
                    img_url += arr[i] + ",";
                }
                if (i == (arr.length - 1)) {
                    img_url = img_url.substring(0, img_url.length - 1);
                }
            }
            $(dom).parent().remove();
            $("#adPic").val(img_url);
            alert("删除成功！");
        }
    }
</script>
</body>
</html>