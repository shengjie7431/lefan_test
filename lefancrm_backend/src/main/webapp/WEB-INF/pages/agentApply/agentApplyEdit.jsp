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
            var val = $("#accidentProvinceId").find("option:selected").text();
            var val1 = $("#accidentCityId").find("option:selected").text();
            var val2 = $("#accidentDistrictId").find("option:selected").text();
            $("#accidentProvince").val(val);
            $("#accidentCity").val(val1);
            $("#accidentDistrict").val(val2);
        }
        function selectArea(){
            var accidentProvinceId = $("#accidentProvinceId").val();
            if(accidentProvinceId == 0){
                return;
            }
            ajaxSubmit("${ctx}/org/selectArea",{"parentId":accidentProvinceId},function(v,e,p){
                $("#accidentCityId option").remove();
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#accidentCityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }

            })
        }
        function  selectAreaCity(){
            var accidentCityId = $("#accidentCityId").val();
            if(accidentCityId == 0){
                return;
            }
            ajaxSubmit("${ctx}/org/selectArea",{"parentId":accidentCityId},function(v,e,p){
                $("#accidentDistrictId option").remove();
                for(var i = 0; i < e.data.results.length; i++){
                    var val = e.data.results[i];
                    $("#accidentDistrictId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }

            })
        }
    </script>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/agent/edit" method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <input type="hidden" id="id" name="id" value="${agent.id}">
                <tr>
                    <th width="20%" class="active">用户姓名</th>
                    <td width="80%">
                       <input type="text" id = "userName" name="userName" class="form-control"  style="width: 400px;" value="${agent.userName}">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">用户手机号</th>
                    <td width="80%">
                        <input type="text" id = "userPhone" name="userPhone" class="form-control" style="width: 400px;" value="${agent.userPhone}">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">代理类型</th>
                    <td width="80%">
                        <select id = "agentType" name="agentType" class="form-control" style="width: 150px;">
                            <option value="1" <c:if test="${agent.agentType == 1}">selected="selected" </c:if>>交通事故索赔</option>
                            <option value="2" <c:if test="${agent.agentType == 2}">selected="selected" </c:if>>工伤事故索赔</option>
                            <option value="3" <c:if test="${agent.agentType == 3}">selected="selected" </c:if>>寿险索赔</option>
                            <option value="4" <c:if test="${agent.agentType == 4}">selected="selected" </c:if>>车辆损失索赔</option>
                            <option value="5" <c:if test="${agent.agentType == 5}">selected="selected" </c:if>>保险拒赔</option>
                            <option value="6" <c:if test="${agent.agentType == 6}">selected="selected" </c:if>>意外保险</option>
                            <option value="7" <c:if test="${agent.agentType == 7}">selected="selected" </c:if>>其他侵权</option>
                            <option value="8" <c:if test="${agent.agentType == 8}">selected="selected" </c:if>>援助服务</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">事故所在地</th>
                    <td width="30%" class="form-inline">
                        <div class="form-group">
                    省
                        <select id = "accidentProvinceId" name="accidentProvinceId"  style="width: 75px;"  onclick="selectArea()" class="form-control">
                            <option value="0">请选择</option>
                            <c:forEach items="${apiRsp1.results}" var="area">
                                <option value="${area.areaId}" <c:if test="${area.areaId == agent.accidentProvinceId}">selected="selected" </c:if>>${area.areaName}</option>
                            </c:forEach>
                        </select>
                            </div>
                        <div class="form-group">
                    市
                        <select id = "accidentCityId" name="accidentCityId"  style="width: 75px;" onclick="selectAreaCity()" class="form-control">
                            <option value="${agent.accidentCityId}">${agent.accidentCity}</option>
                        </select>
                            </div>
                        <div class="form-group">
                    区
                        <select id = "accidentDistrictId" name="accidentDistrictId"  style="width: 75px;" class="form-control">
                            <option value="${agent.accidentDistrictId}">${agent.accidentDistrict}</option>
                        </select>
                            </div>
                    </td>
                    <input type="hidden" id="accidentProvince" name="accidentProvince" value="">
                    <input type="hidden" id="accidentCity" name="accidentCity" value="">
                    <input type="hidden" id="accidentDistrict" name="accidentDistrict" value="">
                </tr>
                <tr>
                    <th width="20%" class="active">详细地址</th>
                    <td width="80%">
                        <input type="text" id = "accidentAddress" class="form-control" name="accidentAddress" style="width: 400px;"value="${agent.accidentAddress}">
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
                $("#infImg").attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                $("#adPic").val(r.imgUrl);
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
</script>
</body>
</html>