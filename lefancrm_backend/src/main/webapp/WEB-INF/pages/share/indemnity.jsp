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

//            var val = $("#orgId").find("option:selected").text();
            //$("#orgName").attr("value",val);
//            $("#orgName").val(val);
        }

    </script>
</head>
<body>
<div class="main administrator">
    <%--<form id="editForm" role="form" action="${ctx}/user/userInfoUpdate" method="post">--%>
        <%--<input type="hidden" name="userId" value="${user.userId}">--%>


        <div class="panel panel-info">

            <div class="panel-heading">
                <div class="pin">
                    <%--form class="form-inline" role="form" action="${ctx}/paymentEstimate/paymentEstimateReportList" method="post">
                        <div class="form-group">
                            <input type="hidden" name="id" value="${id}">
                        </div>
                    </form>--%>
                </div>
            </div>

            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="400">打包价</th>
                    <th width="1000">调解情况（主张）</th>
                    <th width="400">伤者获得赔偿款</th>
                    <th width="400">保险公司赔偿</th>
                    <th width="400">肇事方赔偿</th>
                    <th width="1000">调解情况（保险公司）</th>
                    <th width="800">创建时间</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${smdd}" var="item">
                    <tr>
                        <td>${item.onePrice}</td>
                        <td>${item.mediateDesc}</td>
                        <td>${item.injuredFee}</td>
                        <td>${item.companyFee}</td>
                        <td>${item.accidentFee}</td>
                        <td>${item.insuranceDesc}</td>
                        <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    </tr>
                </c:forEach>
                </tbody>
                </tbody>
            </table>
        </div>
        <%--<div class="form-group">--%>


            <%--<table class="table table-hover">--%>
                <%--<thead>--%>
                <%--<tr>--%>
                    <%--<th width="300">调解情况（主张）</th>--%>
                    <%--<th width="150">打包价</th>--%>
                    <%--<th width="150">伤者获得赔偿款</th>--%>
                    <%--<th width="150">保险公司赔偿</th>--%>
                    <%--<ht width="150">肇事方赔偿</ht>--%>
                    <%--<ht width="300">调解情况（保险公司）</ht>--%>
                <%--</tr>--%>
                <%--</thead>--%>
                <%--<tbody class="class-list">--%>
                <%--<c:forEach items="${smdd}" var="item">--%>
                    <%--<tr>--%>
                        <%--<td>${item.mediateDesc}</td>--%>
                        <%--<td>${item.onePrice}</td>--%>
                        <%--<td>${item.injuredFee}</td>--%>
                        <%--<td>${item.companyFee}</td>--%>
                        <%--<td>${item.accidentFee}</td>--%>
                        <%--<td>${item.insuranceDesc}</td>--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
                <%--</tbody>--%>
            <%--</table>--%>
        <%--</div>--%>
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
    <%--var editor1;--%>
    <%--KindEditor.ready(function(K) {--%>
         <%--editor1 = K.create('textarea[name="content"]', {--%>
            <%--cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',--%>
            <%--uploadJson : '${ctx}/uploadFileForKindEditor'--%>
        <%--});--%>
    <%--});--%>
//
//    $('#fileupload').fileupload({
//        done: function (e, data) {
//            var r  = data.result;
//            var img=r.images;
//            var pathImg=img[0].userFilePath;
//            if (r.success == true){
//                $("#infImg").attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
//                $("#adPic").val("http://ddrapi.shlefan.com/sftp/files/"+pathImg);
//            }else {
//                alert("上传失败，请重试111");
//            }
//        }
//    });
//    $("#editForm").bind('submit', function(event) {
//        //$("#content").text(editor1.html());
//        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
//        event.preventDefault();
//    });
</script>
</body>
</html>