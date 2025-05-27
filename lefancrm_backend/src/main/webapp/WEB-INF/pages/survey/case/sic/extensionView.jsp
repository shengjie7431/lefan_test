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
    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctx}/css/viewer.min.css?v=1">
    <style>
        .dalogs-1128 {
            position: relative;
            width: 567px;
            height: 488px;
            margin: 0 auto;
            margin-top: 10px;
            border: 1px solid #bbb;
        }

        .dalogs-1128 .d-title {
            padding-top: 20px;
            width: 100%;
            height: 40px;
            line-height: 40px;
            font-size: 14px;
            font-weight: bold;
            text-align: center;
        }

        .dalogs-1128 .d-type {
            margin: 24px auto;
            width: 86%;
            height: 42px;
            line-height: 42px;
            border: 1px solid #bbb;
            color: #333;
            background-color: #fff;
            text-align: center;
            cursor: pointer;
        }

        .dalogs-1128 .d-textarea{
            margin: 24px auto;
            width: 86%;
        }

        .dalogs-1128 textarea {
            visibility: hidden;
            width: 100%;
            padding: 20px 5%;
            height: 143px;
        }

        .dalogs-1128 .active {
            color: #fff;
            background-color: #3BA9FF;
            border-color: #3BA9FF;
        }

        .dalogs-1128 .d-btns {
            width: 86%;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
        }

        .dalogs-1128 .d-btn {
            width: 190px;
            height: 45px;
            line-height: 45px;
            color: #3BA9FF;
            border: 1px solid #bbb;
            background-color: #fff;
            text-align: center;
            cursor: pointer;
        }
        .dalogs-1128 .d-btn-active{
            color: #fff;
            border: 1px solid #3BA9FF;
            background-color: #3BA9FF;
        }

        .files {
            margin-top: 20px;
            width: 100%;
            display: flex;
            flex-wrap: wrap;
            /*max-height: 330px;*/
            /*overflow: auto;*/
        }

        .files .file {
            width: 100px;
            height: 100px;
            border: 1px solid #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;
            position: relative;
        }

        .files .file .image {
            width: 100%;
            height: 100%;
            display: block;
            margin: 0 auto;

        }

        .files div.file-add {
            width: 100px;
            height: 100px;
            border: 1px dashed #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;
        }

        .icon-add {
            margin-left: 48px;
            margin-top: 19px;
            display: inline-block;
            background: #aaa;
            height: 60px;
            position: relative;
            width: 4px;
        }

        .icon-add:after {
            background: #aaa;
            content: "";
            height: 60px;
            left: 0;
            position: absolute;
            top: 0;
            width: 4px;
            transform: rotateZ(90deg);
        }
        .file-title{
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 20px;
            line-height: 20px;
            font-size: 10px;
            background-color: rgba(187,187,187,0.7);
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .file-del{
            position: absolute;
            top: 2px;
            right: 2px;
            display: inline-block;
            width: 16px;
            height: 16px;
            background: url(data:image/png;base64,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);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }
        .point-no{
            pointer-events: none;
        }
        .main{
            width: 96%;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
        }
        .main-left{
            width: 46%;
            height: 100%;
            overflow-y: auto;
            border-right: 2px solid #eee;
        }
        .main-left-title{
            width: 100%;
            line-height: 46px;
            font-size: 18px;
            padding-left: 10px;
        }
        .main-left-content{
            width: 90%;
        }
        .main-left-content td{
            width: 65%;

        }
        .main-left-content th{
            width: 35%;
        }
        .tableBar{
            width: 100%;
            border: 1px solid #eee;
            border-radius: 4px;
            margin: 10px 0;
            padding: 6px;
        }

        .main-right{
            width: 50%
        }
        .td-state{
            color: #FF0000;
            display: inline-block;
        }
        .downA{
            color: #3ba9ff;
        }
        .downA:hover{
            color: #3ba9ff;
        }
    </style>
</head>
<body>

<div class="main">
<c:if test="${roleCode != 'lfManager-recall' &&  roleCode != 'lfManager-back'}">
    <div class="main-left">
        <div class="main-left-title">申请历史</div>
        <div class="main-left-content">
            <input type="hidden" value="${assignOrg.extensionList.size()}" id="listLen">
            <c:forEach items="${assignOrg.extensionList}" var="extension" varStatus="st" >
                <div class="tableBar">
                    <table class="table">
                        <tbody>
                        <c:if test="${btnCode == 'extension-time'}">
                            <c:if test="${roleCode == 'orgManager' || roleCode == 'lfManager'}">
                                <c:if test="${roleCode == 'lfManager'}">
                                    <tr>
                                        <th style="border:none">案件截止日期</th>
                                        <td colspan="8" style="border:none">
                                            <fmt:formatDate value="${extension.endTime}" pattern="yyyy-MM-dd"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>原机构截止日期</th>
                                        <td colspan="8">
                                            <fmt:formatDate value="${extension.oldOrgEndTime}" pattern="yyyy-MM-dd"/>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${roleCode == 'orgManager'}">
                                    <tr>
                                        <th style="border:none">原机构截止日期</th>
                                        <td colspan="8" style="border:none">
                                            <fmt:formatDate value="${extension.oldOrgEndTime}" pattern="yyyy-MM-dd"/>
                                        </td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <th>申请延期至</th>

                                    <td colspan="8">
                                        <fmt:formatDate value="${extension.extensionTime}" pattern="yyyy-MM-dd"/>
                                        <div class="td-state">( ${extension.extensionState == 1 ? '待审核' : ''}${extension.extensionState == 2 ? '审核通过' : ''}${extension.extensionState == 3 ? '已驳回' : ''} )
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th>延期原因</th>
                                    <td colspan="8">
                                            ${extension.extensionReason}
                                    </td>
                                </tr>
                                <tr>
                                    <th>证据附件</th>
                                    <td colspan="8" class="filestd${st.index}" data-len="${extension.commonFiles.size()}">
                                        <c:if test="${extension.commonFiles !=null}">
                                            <div class='files' id="files${st.index}" data-id="1">
                                                <c:forEach items="${extension.commonFiles}" var="file">
                                                    <div class="file" id="1">
                                                        <img src="${file.filePath}" class="image"/>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                        <c:if test="${extension.extensionFiles !=null}">
                                            <div>
                                                <a class="downA" href="javascript:void(0);" onclick="downFileExtensionTime('${assignOrg.surveyRiskCaseInfoDto.surveyCno}','${assignOrg.id}','${extension.id}')">下载全部附件</a>
                                            </div>
                                        </c:if>
                                        <c:if test="${extension.extensionFiles ==null}">暂无附件</c:if>

                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${extension.extensionState == 3}">
                                <tr>
                                    <th>驳回原因</th>
                                    <td colspan="8">
                                        ${extension.extensionBackReason}
                                    </td>
                                </tr>
                            </c:if>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </c:forEach>
        </div>

    </div>
</c:if>
    <div class="main-right" <c:if test="${roleCode == 'lfManager-recall' ||  roleCode == 'lfManager-back'}">style="margin: 0 auto" </c:if>>
      <form id="editForm" role="form" action="${ctx}/survey/case/sic/operate" method="post">
        <input type="hidden" name="id" value="${id}" />
        <input type="hidden" name="btnCode" value="${btnCode}" />
        <input type="hidden" name="tsId" value="${tsId}" />
        <c:if test="${btnCode == 'extension-time'}">
            <input type="hidden" name="roleCode" value="${roleCode}" />
            <input type="hidden" name="extensionId" id="extensionId" value="${extension.id}" />
            <input type="hidden" name="operateType" id="operateType" value="${operateType}" />
            <input type="hidden" name="extensionFileId" id="extensionFileId" value="${extensionFileId}" />
            <input type="hidden" name="surveyCno" id="surveyCno" value="${assignOrg.surveyRiskCaseInfoDto.surveyCno}" />
            <input type="hidden" name="sendEmail" id ="sendEmail" value="${sendEmail}" />
        </c:if>
        <div class="form-group" style="margin-top: 57px">
            <table class="table">
                <tbody>
                <c:if test="${btnCode == 'extension-time'}">
                    <c:if test="${roleCode == 'orgManager' || roleCode == 'lfManager'}">
                        <c:if test="${roleCode == 'lfManager'}">
                            <tr>
                                <th>保险公司</th>
                                <td colspan="8" style="line-height: 33px">${assignOrg.surveyRiskCase.entrustOrgName}</td>
                            </tr>
                            <tr>
                                <th>案件编号</th>
                                <td colspan="8" style="line-height: 33px"><a  class="downA" onclick="info(${assignOrg.surveyRiskCaseInfoDto.id})">${assignOrg.surveyRiskCase.surveyCaseNo}</a></td>
                            </tr>
                            <tr>
                                <th>调查机构</th>
                                <td colspan="8" style="line-height: 33px">${assignOrg.surveyOrgName}</td>
                            </tr>
                            <tr>
                                <th>案件截止日期</th>
                                <td colspan="8">
                                    <fmt:formatDate value="${extension.endTime}" pattern="yyyy-MM-dd"/>
                                </td>
                            </tr>
                            <tr>
                                <th>原机构截止日期</th>
                                <td colspan="8">
                                    <fmt:formatDate value="${extension.oldOrgEndTime}" pattern="yyyy-MM-dd"/>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${roleCode == 'orgManager'}">
                            <tr>
                                <th>原机构截止日期</th>
                                <td colspan="8">
                                    <fmt:formatDate value="${assignOrg.orgEndTime}" pattern="yyyy-MM-dd"/>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <th>申请延期至</th>
                            <td colspan="8">
                                <input id="extensionTime" name="extensionTime" type="text" class="form-control" required="required" style="cursor: auto; background-color:#fff"
                                    <c:if test="${(roleCode == 'orgManager' && assignOrg.extensionState != 2) || roleCode == 'lfManager'}"> value="<fmt:formatDate value="${extension.extensionTime}" pattern="yyyy-MM-dd 23:59:59"/>" </c:if>
                                    placeholder="请选择日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',minDate :'<fmt:formatDate value="${assignOrg.orgEndTime}" pattern="yyyy-MM-dd HH:mm:ss" />'})" readonly>
                            </td>
                        </tr>
                        <tr>
                            <th>机构延期原因</th>
                            <td colspan="8">
                                <textarea name="extensionReason"  rows="4" class="form-control"><c:if test="${(roleCode == 'orgManager' && assignOrg.extensionState != 2) || roleCode == 'lfManager'}">${extension.extensionReason}</c:if></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th title="提供给保险公司的延期说明">延期结论</th>
                            <td colspan="8">
                                <textarea name="extensionReasonResult"  rows="4" class="form-control"><c:if test="${(roleCode == 'orgManager' && assignOrg.extensionState != 2) || roleCode == 'lfManager'}">${extension.extensionReason}</c:if></textarea>
                            </td>
                        </tr>
                        <%--<tr>--%>
                            <%--<th>证据附件</th>--%>
                            <%--<td colspan="8">--%>
                                <%--<div>--%>
                                    <%--<c:if test="${(roleCode == 'orgManager' && assignOrg.extensionState != 2) || roleCode == 'lfManager'}">--%>
                                        <%--<c:forEach items="${extension.commonFiles}" var="file">--%>
                                               <%--<img width="90px" height="60px" onclick="onlinePreview('${file.filePath}')"  class="picToBig" src="${file.filePath}"/>--%>
                                        <%--</c:forEach>--%>
                                    <%--</c:if>--%>
                                <%--</div>--%>
                                <%--<c:if test="${roleCode == 'orgManager'}">--%>
                                <%--<div>--%>
                                    <%--&lt;%&ndash;onlinePreview(\""+file.filePath+"\")' target='_blank'&ndash;%&gt;--%>
                                    <%--<input type="hidden" id="pathBackReason" name="pathBackReason"  value="" />--%>
                                    <%--<input type="hidden" id="fileRealName" name="fileRealName"  value="" />--%>
                                    <%--<input type="hidden" id="uploadPaths" name="uploadPaths"  value="" />--%>
                                    <%--<div id="uploadDiv" class="form-control" style="display: none"></div>--%>
                                <%--</div>--%>
                                <%--<div>--%>
                                    <%--<input id="fileupload" class="form-control" type="file" name="file" multiple data-url="${ctx}/sftp/survey/uploadSftp?modelType=extensionTime&surveyCno=${assignOrg.surveyRiskCaseInfoDto.surveyCno}&assignOrgId=${assignOrg.id}"><br>--%>
                                <%--</div>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${roleCode == 'lfManager'}">--%>
                                    <%--<c:if test="${extension.extensionFiles !=null}">--%>
                                        <%--<div>--%>
                                            <%--<a href="javascript:void(0);" onclick="downFileExtensionTime('${assignOrg.surveyRiskCaseInfoDto.surveyCno}','${assignOrg.id}')">下载全部附件</a>--%>
                                        <%--</div>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${extension.extensionFiles ==null}">暂无附件</c:if>--%>
                                <%--</c:if>--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                        <tr>
                            <th>证据附件</th>
                            <td colspan="8">

                                <c:if test="${roleCode == 'orgManager' || roleCode == 'lfManager'}">
                                    <div class='files' id="jq21" data-id="1">
                                        <c:if test="${roleCode == 'orgManager' && (type =='first' || view)}">
                                            <div class="file-add uploadFile">
                                                <div class="icon-add v-p-upload" ></div>
                                            </div>
                                        </c:if>
                                        <input type="hidden" value="${extension.commonFiles}" id="cfileLen">
                                        <c:forEach items="${extension.commonFiles}" var="file">
                                            <div class="file" id="1">
                                                <img src="${file.filePath}" class="image"/>
                                                <c:if test="${roleCode == 'orgManager' && type =='first'}">
                                                <div class="file-del"></div>
                                                </c:if>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <input style="display: none" id="fileupload" class="form-control"  type="file" name="file" multiple data-url="${ctx}/sftp/survey/uploadSftp?modelType=extensionTime&surveyCno=${assignOrg.surveyRiskCaseInfoDto.surveyCno}&assignOrgId=${assignOrg.id}&extensionFileId=${extensionFileId}"><br>

                                    <div>
                                        <input type="hidden" id="pathBackReason" name="pathBackReason"  value="" />
                                        <input type="hidden" id="fileRealName" name="fileRealName"  value="" />
                                        <input type="hidden" id="uploadPaths" name="uploadPaths"  value="" />
                                        <div id="uploadDiv" class="form-control" style="display: none"></div>
                                    </div>
                                </c:if>
                                <c:if test="${roleCode == 'lfManager'}">
                                    <c:if test="${extension.extensionFiles !=null}">
                                        <div>
                                            <a class="downA" href="javascript:void(0);" onclick="downFileExtensionTime('${assignOrg.surveyRiskCaseInfoDto.surveyCno}','${assignOrg.id}','${extension.id}')">下载全部附件</a>
                                        </div>
                                    </c:if>
                                    <c:if test="${extension.extensionFiles ==null}">暂无附件</c:if>
                                </c:if>

                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${roleCode == 'lfManager-back' || (roleCode == 'lfManager' && extension.extensionState == 3)}">
                        <tr>
                            <th>驳回原因</th>
                            <td colspan="8">
                                <textarea name="extensionBackReason"  rows="4" class="form-control">${extension.extensionBackReason}</textarea>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${roleCode == 'lfManager-recall'}">
                        <tr>
                            <th>撤回原因</th>
                            <td colspan="8">
                                <textarea name="extensionBackReason"  rows="4" class="form-control">${extension.extensionBackReason}</textarea>
                            </td>
                        </tr>
                    </c:if>
                </c:if>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <c:if test="${btnCode == 'extension-time'}">
                <c:if test="${roleCode == 'orgManager'}">
                    <c:if test="${extension.extensionState ==null || extension.extensionState == 3 || extension.extensionState == 2}">
                        <button type="submit" onclick="return validFile('${btnCode}');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>提交</button>
                    </c:if>
                    <c:if test="${extension.extensionState == 1}">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
                    </c:if>
                </c:if>
                <c:if test="${roleCode == 'lfManager'}">
                    <c:if test="${extension.extensionState == 1}">
                        <button type="button" onclick="extensionBack('${btnCode}','lfManager-back');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>驳回</button>
                        <button type="submit" onclick="return validFile('${btnCode}');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>审核通过</button>
                    </c:if>
                    <c:if test="${extension.extensionState == 2 && extension.newestInfo}">
                        <button type="button" onclick="extensionBack('${btnCode}','lfManager-recall');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>撤回</button>
                    </c:if>
                </c:if>
                <c:if test="${roleCode == 'lfManager-back' || roleCode == 'lfManager-recall'}">
                    <button type="submit" onclick="return validFile('${btnCode}');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>提交</button>
                </c:if>
            </c:if>
        </div>
    </form>
    </div>
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
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
<script src="${ctx}/js/viewer.min.js"></script>
<script src="${ctx}/js/layui/layui.js"></script>


<script type="text/javascript">
    // layui.use(['layer'], function () {
    //     var layer = layui.layer
    //
    //     $('body').on('click','.file-del',function(){
    //         var _this = $(this)
    //
    //         layer.confirm('确定删除？', {
    //             btn: ['确定','取消'] //按钮
    //         }, function(){
    //             _this.parent().detach()
    //             viewer = new Viewer(document.getElementById('jq21'));
    //             layer.closeAll()
    //         }, function(){
    //
    //         });
    //     })
    // })
    $('body').on('click','.file-del',function(){
        var _this = $(this)

        var _index = $('#jq21 .file-del').index(_this)
        var newFiles = []

        files.splice(_index,1)
        files.map(function (cur,i) {
            newFiles.push(cur.filePath)
        })

        $('#pathBackReason').val(newFiles.join(',')+',')

        _this.parent().detach()

        viewer.destroy()
        viewer = new Viewer(document.getElementById('jq21'));

    })

    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        var btnCode = $("input[name=btnCode]").val();
        if (btnCode == 'extension-time'){
            var extensionTime =  $("input[name=extensionTime]").val();
            if (extensionTime == ''){
                alert("延期日期不能为空")
                return false;
            }
        }

        $("button[type=submit]").attr("disabled",true);
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
        event.preventDefault();
    });

    function validFile(btnCode){
        if(btnCode == 'extension-time'){
            /*var pathBackReason = $("#pathBackReason").val();
            if(pathBackReason.length == 0){
                alert("证据附件必填");return false;
            }*/
        }
        return true;
    }

    function returnCallback(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){

            var sendEmail = $("#sendEmail").val();
            if(sendEmail == 'true'){
                sendEmailInfo("extension-time","lfManager");
                return;
            }

            if('${btnCode}' == 'primary-veto' || '${btnCode}' == 'case-return' || '${roleCode}' == 'lfManager-back'){
                var closeBtn = $("#diglog_close_btn",window.parent.parent.document);
                closeBtn.click();
            }else{
                reloadParent();
            }
        }else{
            alert(apiRsp.msg);return;
        }

    }
    var onlinePreview = function(path){
        var last = path.lastIndexOf(".");
        var ext = path.substr(last + 1);
        if(ext == 'doc' || ext == 'docx' || ext == 'xls' || ext == 'xlsx'){
            path = "https://view.officeapps.live.com/op/view.aspx?src=" + path;
        }
        openDialog({
            frame:true,
            title:"预览",
            height:700,
            width:800,
            url:path
        });
    }



    var info = function(id){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight();
        openDialog({
            frame:true,
            title:"详情",
            height:height,
            width:width,
            url:"${ctx}/survey/case/info?id=" + id + "&menuCode=all-list",
            load:true
        });
    }

    var files = [];
    var values = "";
    // var pathBackReason =""; //extension-time'时的上传路径
    //材料凭证
    $('body').on('click','.uploadFile',function () {
        $('.uploadFile').addClass('point-no')
        setTimeout(function () {
            $('.uploadFile').removeClass('point-no')
        },500)
        $('#fileupload').trigger('click')
    })
    var fileLen = ''
    $('#fileupload').on('change',function () {
        fileLen = this.files.length
    })
    var fileIndex = 0
    $('#fileupload').fileupload({
        done: function (e, data) {
            fileIndex ++
            $('.uploadFile').removeClass('point-no')
            var value = "";
            var r  = data.result;
            var file = r.surveyFile;
            // var value = "<a onclick='onlinePreview(\""+file.filePath+"\")' target='_blank'>" + file.fileName + "</a>&nbsp;&nbsp;&nbsp;&nbsp;"
            // $("#uploadDiv").empty();
            // values = values += value;
            // $("#uploadDiv").append(values);
            // $("#path").val(file.filePath);
            var pathBackReason =  $("#pathBackReason").val()+ file.filePath + ","  //extension-time'时的上传路径
            $("#pathBackReason").val(pathBackReason); //extension-time'时的上传路径
            // $("#fileRealName").val(file.fileName);
            // $("#uploadPaths").val(value);
            // $("#uploadDiv").show();


            console.log(fileIndex,fileLen)
            if ($('.main-right .files .file').length && fileIndex == fileLen){
                $('.viewer-container').detach()
                viewer.destroy()
            }
            var _html = ' <div class="file">\n' +
                '                        <img src="'+file.filePath+'" class="image"></img>\n' +
                '<div class="file-title">'+file.fileName+'</div>\n' +
                '                        <div class="file-del"></div>'
            '                    </div>'
            $('.main-right .files').append(_html)

            if(fileIndex == fileLen){
                viewer = new Viewer(document.getElementById('jq21'));
                fileIndex = 0
            }


            var item = {
                "fileName" : file.fileName,
                "filePath" : file.filePath
            };
            files.push(item);
        }
    });
    // $('body').on('click', '.viewer-close', function () {
    //     var _this = $(this)
    //     _this.parents('.viewer-container').detach()
    //     viewer.destroy()
    // })





    $(function () {

        var listLen = $('#listLen').val()
        for(var i = 0;i<listLen;i++){
            var _name= 'files'+i
            var _id = 'filestd'+i
            var _num = $('.'+_id).attr('data-len')
            if (_num){
                viewer = new Viewer(document.getElementById(_name));

            }
        }
        if ($('#cfileLen').val()){
            viewer = new Viewer(document.getElementById('jq21'));
        }
        $('.dalogs-1128 .d-type').on('click', function () {
            var _this = $(this)
            if (!_this.hasClass('active')) {
                _this.addClass('active')
                _this.siblings().removeClass('active')
            }
            if (!_this.attr('id')) {
                $('textarea').css('visibility','hidden')
            }
            if (_this.attr('id') && _this.attr('id') == "other") {
                $('textarea').css('visibility','visible')
            }
        })
        $('.dalogs-1128 .d-btn').on('click', function(){
            var _this = $(this)
            $('.dalogs-1128').hide()
            if (_this.attr('id') == 'cancel'){
            }
        })
    })

    var extensionBack = function(btnCode, roleCode){
        openDialog({
            frame:true,
            title:"驳回",
            height:500,
            width:1000,
            url:"${ctx}/survey/case/sic/operateView?id="+'${id}'+"&btnCode=${btnCode}&roleCode="+roleCode+"&extensionId="+'${extension.id}'+"&assignOrgExtensionId="+'${extension.id}',
            load:true
        });
    }

    var downFileExtensionTime = function(surveyCno,assignOrgId,extensionFileId){
        var url = "${ctx}/sftp/survey/downSftp",param = {"surveyCno" : surveyCno,"assignOrgId" : assignOrgId,"uploadType" : "extensionTime","extensionFileId":extensionFileId};
        if(confirm('是否确认下载？')){
            ajaxSubmit(url,param,function(v,e,p){
                var path = e.data.results;
//                path = path.replace("/mnt/sftp/files/","https://ddrapi.shlefan.com/");
//                window.open(path);
                window.location.href = path;
            })
        }
    }

    var sendEmailInfo = function(btnCode, roleCode){
        var extensionTime = $("#extensionTime").val();
        var extensionReason = $("#extensionReason").val();
        var _height = $(parent.document).height() * 0.9
        var _width = $(document).width() * 0.98
        openDialog({
            frame:true,
            title:"发送邮件",
            height:_height,
            width:_width,
            url:"${ctx}/survey/case/sic/operateView?id="+'${id}'+"&btnCode="+btnCode+"&roleCode="+roleCode+"&extensionId="+'${extension.id}'+
                    "&assignOrgExtensionId="+'${extension.id}'+"&sendEmail=true&extensionTime="+extensionTime+"&extensionReason="+extensionReason,
            load:true
        });
    }
</script>
</body>
</html>
