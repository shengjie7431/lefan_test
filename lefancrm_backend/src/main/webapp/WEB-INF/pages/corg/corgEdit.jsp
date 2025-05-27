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
            var val = $("#provinceId").find("option:selected").text();
            var val1 = $("#cityId").find("option:selected").text();
            var val2 = $("#districtId").find("option:selected").text();
            $("#province").val(val);
            $("#city").val(val1);
            $("#district").val(val2);
        }
        function selectArea(){
            var provinceId = $("#provinceId").val();
            if(provinceId == 0){
                return;
            }
            ajaxSubmit("${ctx}/org/selectArea",{"parentId":provinceId},function(v,e,p){
                $("#cityId option").remove();
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#cityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }

            })
        }
        function  selectAreaCity(){
            var cityId = $("#cityId").val();
            if(cityId == 0){
                return;
            }
            ajaxSubmit("${ctx}/org/selectArea",{"parentId":cityId},function(v,e,p){
                $("#districtId option").remove();
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
    <form id="editForm" role="form" action="${ctx}/corg/edit" method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <input type="hidden" id="id" name="id" value="${apiRsp1.results.id}">
                <tr>
                    <th width="20%" class="active">组织名称</th>
                    <td width="80%">
                       <input type="text" id = "cOrgName" name="cOrgName"  style="width: 400px;" value="${apiRsp1.results.cOrgName}" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">联系人</th>
                    <td width="80%">
                        <input type="text" id = "linkName" name="linkName"  style="width: 400px;" value="${apiRsp1.results.linkName}" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">联系人电话</th>
                    <td width="80%">
                        <input type="text" id = "lineTel" name="lineTel"  style="width: 400px;" value="${apiRsp1.results.lineTel}" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">所在地</th>
                    <td width="80%" class="form-inline">
                        <div class="form-group">
                    省
                        <select id = "provinceId" name="provinceId"  style="width: 90px;"  onclick="selectArea()" class="form-control">
                            <option value="0">请选择</option>
                            <c:forEach items="${apiRsp.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${area.areaId == apiRsp1.results.provinceId}">selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                        </div>
                            <div class="form-group">
                    市
                        <select id = "cityId" name="cityId"  style="width: 90px;" onclick="selectAreaCity()" class="form-control">
                            <option value="${apiRsp1.results.cityId}">${apiRsp1.results.city}</option>
                        </select>
                            </div>
                                <div class="form-group">
                    区
                        <select id = "districtId" name="districtId"  style="width: 90px;" class="form-control">
                            <option value="${apiRsp1.results.districtId}">${apiRsp1.results.district}</option>
                        </select>
                                </div>
                    </td>
                    <input type="hidden" id="province" name="province" value="">
                    <input type="hidden" id="city" name="city" value="">
                    <input type="hidden" id="district" name="district" value="">
                </tr>
                <tr>
                    <th width="20%" class="active">详细地址</th>
                    <td width="80%">
                        <input type="text" id = "address" name="address" style="width: 400px;"value="${apiRsp1.results.address}" class="form-control">
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