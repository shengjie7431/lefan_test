<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>
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
            ajaxSubmit("${ctx}/org/selectArea",{"parentId":orgProvinceId},function(v,e,p){
                $("#cityId option").remove();
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
    <form id="editForm" role="form" action="${ctx}/user/addPromoted" method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <input type="hidden" value="${userId}" name="userId">
                    <th width="20%" class="active">真实姓名</th>
                    <td width="80%">
                        <input type="text" id = "realName" name="realName"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">推广电话</th>
                    <td width="80%">
                        <input type="text" id = "phone" name="phone" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">职业</th>
                    <td width="80%">
                        <select name="occupation" class="form-control" style="width: 400px;">
                            <option value="1">护士</option>
                            <option value="2">医生</option>
                            <option value="3">护工</option>
                            <option value="4">调解员</option>
                            <option value="5">业务员</option>
                            <option value="6">其他</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">推广微信号</th>
                    <td width="80%">
                        <input type="text" id = "wechatId" name="wechatId"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">申请地</th>
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

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
</script>
</body>
</html>