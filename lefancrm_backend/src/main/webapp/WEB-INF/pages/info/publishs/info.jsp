<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>详情</title>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">

    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<style>
    .table-title{
        font-size: 14px;
        font-weight: bold;
        line-height: 40px;
    }
    table.spec-info tr td:nth-of-type(2n + 1) {
        width: 13%;
    }
    table.spec-info tr td:nth-of-type(2n) {
        width: 20%;
    }
</style>
<style>
    .stepList {
        margin: 0 32px;
    }

    .stepItem {
        display: flex;
    }

    .stepDate {
        width: 250px;
        /*font-size: 28px;*/
        color: #555;
    }

    .stepDot {
        width: 60px;
        position: relative;
        border-left: solid 1px #54b0ff;
    }

    .stepDot-dot {
        box-sizing: border-box;
        border: solid 1px #54b0ff;
        background: #fff;
        width: 15px;
        height: 15px;
        border-radius: 15px;
        position: absolute;
        left: -8px;
    }
    .stepDot-dot.finished{
        background: #54b0ff;
    }
    .stepDetail {
        padding-left: 30px;
        flex: 1;
        color: #555;
        padding-bottom: 50px;
    }

    .stepDetail-title {
        /*font-size: 34px;*/
        line-height: 1.5;
    }

    .stepDetail-detail {
        font-size: 24px;
    }

    .finished.stepDot-dot {
        background: #54b0ff;
    }
    .stepItem.omit {
        min-height: 96px;
    }
    .stepDot.omit {
        width: 60px;
        position: relative;
        border-left: dashed 1px #54b0ff;
    }
    .addfile{
        background-image: url("${ctx}/img/file.png");
        background-repeat: no-repeat;
        background-position: left center;
        padding-left: 21px;
    }
    a, a:hover, a:visited, a:active{
        text-decoration: none!important;
    }
    .btn2{
        width: 100%;
        height: 50px;
        border: none;
        background-color: #428bca;
        color: #fff;
    }

</style>
<body>
    <div class="main">
        <input type="hidden" id="menuType" value="${menuType}" />
        <c:if test="${menuType == 2}">
            <div class="title">
                    <c:if test="${dto.isAssignRole}">
                    </c:if>
                    <button class="butList active" onclick="selectCompany(${dto.id},'upd')">分配协助保司</button>
                    <button class="butList active" onclick="selectCompany(${dto.id},'del')">移除协助保司</button>
                    <button class="butList active" onclick="updInfo(${dto.id},'del')">修改基础信息</button>
            </div>
        </c:if>
        <div class="main-boy">
            <div>
                <div class="table-title">基础信息</div>
                <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr>
                        <td>姓名</td>
                        <td>${dto.userName}</td>
                        <td>手机号</td>
                        <td>${dto.userTel}</td>
                        <td>身份证号</td>
                        <td>${dto.userCardid}</td>
                    </tr>
                    <tr>
                        <td>协助保司</td>
                        <td colspan="5">${dto.safeCompanyNames}</td>
                    </tr>
                    <tr>
                        <td>其他信息</td>
                        <td colspan="5">${dto.userOtherInfo}</td>
                    </tr>
                    <tr>
                        <td>问题描述</td>
                        <td colspan="5">${dto.problemRemark}</td>
                    </tr>
                    </tbody>
                </table>
                <c:if test="${menuType != 6}">
                    <form id="editForm" role="form" action="${ctx}/info/publishs/saveForum" method="post">
                        <input type="hidden" name="publishsId" value="${dto.id}">
                        <div class="table-title">回复区</div>
                        <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                            <tbody>
                            <tr>
                                <td colspan="6">
                                    <textarea required="required"  id="forumRemark" name="forumRemark" style="width: 100%; height: 150px" placeholder="请输入......" class="form-control"></textarea>
                                </td>
                            </tr>
                            <tr style="height:50px;">
                                <td style="width: 3%"><a href="javascript:void(0);" onclick="addFile()" class="addfile">添加附件</a></td>
                                <td colspan="4" style="border-right:none;">
                                    <div id="uploadDiv" style="display: none"></div>
                                </td>
                                <td style="width: 3%;background-color:#428bca;color:#fff;border-left:none;text-align: center;cursor: pointer;">
                                    <button type="submit" class="btn2" data-loading-text="Loading..." autocomplete="off">回复</button>
                                </td>
                            </tr>
                            <tr style="display:none;">
                                <td style="width: 3%">
                                    附件
                                </td>
                                <td>
                                    <div>
                                        <input type="hidden" id="uploadPaths" name="uploadPaths" value="" />
                                    </div>
                                    <div>
                                        <input id="fileupload" type="file" name="file" multiple data-url="${ctx}/uploadFile/"><br>
                                    </div>
                                </td>
                            </tr>
                                <%--<tr>--%>
                                <%--<td colspan="6" style="text-align: right;">--%>
                                <%--<button type="submit" class="btn2" data-loading-text="Loading..." autocomplete="off">回复</button>--%>
                                <%--</td>--%>
                                <%--</tr>--%>
                            </tbody>
                        </table>
                    </form>
                </c:if>
            </div>
            <c:if test="${dto.infoPublishsForumDtos != null}">
                <c:forEach items="${dto.infoPublishsForumDtos}" var="item">
                    <div class="stepItem" style="margin-left: 10px;margin-top: 20px">
                        <div class="stepDate"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></div>
                        <div class="stepDot"><div class="stepDot-dot finished"></div></div>
                        <div class="stepDetail"><div class="stepDetail-title">回复人：${item.createName}
                            <br>内容：${item.forumRemark}<br>
                            附件
                            <c:forEach items="${item.infoPublishsForumFiles}" var="f" varStatus="st">
                                <c:if test="${f.fileType == 'gif' || f.fileType == 'jpg' ||f.fileType == 'jpeg' ||f.fileType == 'bmp' ||f.fileType == 'png' }">
                                    <img src="${f.filePath}" width="75;" height="75;" class="picToBig" onclick="openImgs(${f.forumId},${st.index})">
                                </c:if>
                                <c:if test="${f.fileType == 'txt'}">
                                    <img src="${ctx}/img/txt.png" width="75;" height="75;" class="picToBig" onclick="openImgs(${f.forumId},${st.index})">
                                </c:if>
                                <c:if test="${f.fileType == 'doc' || f.fileType == 'docx'}">
                                    <img src="${ctx}/img/word.png" width="75;" height="75;" class="picToBig" onclick="openImgs(${f.forumId},${st.index})">
                                </c:if>
                                <c:if test="${f.fileType == 'xls' || f.fileType == 'xlsx'}">
                                    <img src="${ctx}/img/excel.png" width="75;" height="75;" class="picToBig" onclick="openImgs(${f.forumId},${st.index})">
                                </c:if>
                                <c:if test="${f.fileType == 'pdf'}">
                                    <img src="${ctx}/img/pdf.jpg" width="75;" height="75;" class="picToBig" onclick="openImgs(${f.forumId},${st.index})">
                                </c:if>
                                <a href='${ctx}/download?files=${f.filePath}'>下载</a>
                            </c:forEach>
                        </div><div class="stepDetail-detail"></div></div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${dto.infoPublishsForumDtos.size() == 0}">
                <div class="stepItem" style="margin-top: 10px;text-align:center;color: #ff0000;">
                    暂无回复记录
                </div>
            </c:if>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            </div>
        </div>
    </div>

</body>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script>
    $("#editForm").bind('submit', function(event) {
        ajaxFormSubmit(this,reload,null,null,reload);
        event.preventDefault();
    });

    function addFile(){
        $("#fileupload").trigger("click");
    }
    function backMessage(){
        $("#editForm").submit();
    }

   function selectCompany(id,choose){
        openDialog({
            frame:true,
            title:"协助保司",
            height:500,
            width:900,
            url:"${ctx}/info/publishs/selectCompany?id="+id+"&choose=" + choose
        });
    }

    function updInfo(id){
        openDialog({
            frame:true,
            title:"修改基础信息",
            height:600,
            width:800,
            url:"${ctx}/info/publishs/upd?id="+id
        });
    }

    function openImgs(forumId,index){
        openDialog({
            frame:true,
            title:"查看图片",
            height:500,
            width:900,
            url:"${ctx}/info/publishs/fileShow?index="+index+"&forumId="+forumId
        });
    }

    var value = "";
    $('#fileupload').on('click', function(){
        $('#fileupload').fileupload({
            done: function (e, data) {
                var r  = data.result;
                var img=r.images;
                var pathImg=img[0].userFilePath;
                var ext = pathImg.substring(pathImg.lastIndexOf(".") + 1, pathImg.length);
                if(ext == "txt"){
                    $("#uploadDiv").append("<img width='40' height='40' src='../img/txt.png' />");
                    value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
                }else if(ext == "docx" || ext == "doc"){
                    $("#uploadDiv").append("<img width='40' height='40' src='../img/word.png' />");
                    value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
                }else if(ext == "xls" || ext == "xlsx"){
                    $("#uploadDiv").append("<img width='40' height='40' src='../img/excel.png' />");
                    value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
                }else if(ext == "pdf"){
                    $("#uploadDiv").append("<img width='40' height='40' src='../img/pdf.jpg' />");
                    value += 'http://ddrapi.shlefan.com/sftp/files/' + p
                    athImg + ',';
                }else{
                    $("#uploadDiv").append("<img width='40' height='40' src='http://ddrapi.shlefan.com/sftp/files/"+pathImg+"' />");
                    value += 'http://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';
                }
                $("#uploadPaths").val(value);
                $("#uploadDiv").show();
            }
        });
    })


</script>
</body>
</html>
