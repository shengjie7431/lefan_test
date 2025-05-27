<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/case/center/operate" method="post">
        <div class="form-group">
            <table class="table">
                <tbody>
                <input type="hidden" id="id" name="id" value="${id}">
                <input type="hidden" id="btnCode" name="btnCode" value="${btnCode}">
                <c:if test="${btnCode == '77702' || btnCode == 'claim_jianding' || btnCode == 'legal_jianding'}">
                    <tr>
                        <th width="20%" class="active">鉴定类型</th>
                        <td width="80%">
                            <select name="appraiseType" required="required" class="form-control">
                                <option value="" selected>请选择</option>
                                <option value="0">免鉴定</option>
                                <option value="1">鉴定</option>
                                <option value="2">无伤残</option>
                            </select>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == '2401' || btnCode == 'claim_tiaojie'}">
                    <tr>
                        <th width="20%" class="active">调解类型</th>
                        <td width="80%">
                            <select name="mediateType" required="required" class="form-control">
                                <option value="" selected>请选择</option>
                                <option value="0">调解成功</option>
                                <option value="1">调解失败</option>
                            </select>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == 'legal_kaiting_start'}">
                    <tr>
                        <th width="20%" class="active">开庭时间</th>
                        <td width="80%">
                            <input id="kaitingTime" name="kaitingTime" type="text" class="form-control" required="required"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly>

                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">开庭地点</th>
                        <td  width="80%">
                            <input type="text" id="kaitingPlace" name="kaitingPlace"  class="form-control" required="required">
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">开庭内容</th>
                        <td  width="80%">
                            <input type="text" id="kaitingContent" name="kaitingContent"  class="form-control">
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == '1114'}">
                    <tr>
                        <th width="20%" class="active">结案方式</th>
                        <td width="80%">
                            <select name="closeCaseType" required="required" class="form-control">
                                <option value="" selected>请选择</option>
                                <option value="0">阶段结案</option>
                                <option value="1">结案</option>
                            </select>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == '666020101'}">
                    <tr>
                        <th width="20%" class="active">立案凭证</th>
                        <td>
                            <div>
                                <input type="hidden" name="catalogId" value="8" />  <%--诉讼材料--%>
                                <input type="hidden" name="catalogName" value="诉讼材料" />  <%--诉讼材料--%>
                                <input type="hidden" required="required" id="strImages" name="strImages" value="" />
                                <%--<input required id="adPic" type="hidden" value="" name="img">--%>
                                <%--<img hidden="hidden" id="infImg2" src="" width="80" height="80">--%>
                                <div id="imgDiv" style="display: none"></div>
                            </div>
                            <div>
                                <input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage"><br>
                            </div>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认</button>
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

    var value = "";
    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            if (r.success == true){
                $("#imgDiv").append("<img width='100' height='100' src='http://ddrapi.shlefan.com/sftp/files/"+pathImg+"' />");
                value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
                $("#strImages").val(value);
                $("#imgDiv").show();
//                $("#infImg2").attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
//                $("#adPic").val("http://ddrapi.shlefan.com/sftp/files/"+pathImg);
//                $("#infImg2").show();
            }else {
                alert("上传失败，请重试111");
            }
        }
    });
    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        $(this).find(":submit").attr("disabled","true");
        ajaxFormSubmit(this,reloadParent,null,null,function(v,e,p){
            alert(e.data.msg);
            reloadParent();
        });
        event.preventDefault();
    });
</script>
</body>
</html>