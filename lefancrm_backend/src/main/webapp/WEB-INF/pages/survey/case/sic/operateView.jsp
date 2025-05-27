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
    <script>

    </script>
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
    </style>
</head>
<body>

<div class="container">
    <c:if test="${btnCode == '118'}">
        <form id="editForm" role="form" action="${ctx}/survey/case/operateAssign" method="post">
    </c:if>
    <c:if test="${btnCode != '118'}">
        <form id="editForm" role="form" action="${ctx}/survey/case/sic/operate" method="post">
    </c:if>
        <input type="hidden" name="id" value="${id}" />
        <input type="hidden" name="btnCode" value="${btnCode}" />
        <input type="hidden" name="tsId" value="${tsId}" />
        <c:if test="${btnCode == '118'}">
            <input type="hidden" name="surveyInfoId" value="${surveyInfoId}" />
        </c:if>
        <c:if test="${btnCode == 'case-return'}">
            <input type="hidden" name="roleCode" value="${roleCode}" />
            <input type="hidden" name="assignOrgId" value="${assignOrgId}" />
        </c:if>
        <c:if test="${btnCode == 'extension-time'}">
            <input type="hidden" name="roleCode" value="${roleCode}" />
            <input type="hidden" name="extensionId" id="extensionId" value="${extension.id}" />
            <input type="hidden" name="operateType" id="operateType" value="${operateType}" />
        </c:if>
        <c:if test="${btnCode == 'otherReply'}">
            <input type="hidden" name="orgCaseId" id="orgCaseId" value="${replyOrgCaseId}" />
            <input type="hidden" name="operateType" id="operateType" value="${operateType}" />
            <input type="hidden" name="type" id="type" value="${type}" />
        </c:if>
        <div class="form-group">
            <table class="table">
                <tbody>
                <c:if test="${btnCode == 'direction'}">
                    <tr>
                        <th>区域类别</th>
                        <td colspan="3">
                            <select class="form-control" id="areaType" name="areaType" onchange="toAddressData()" required="required">
                                <option <c:if test="${direction.areaType == 1}">selected="selected"</c:if> value="1">直辖市</option>
                                <option <c:if test="${direction.areaType == 2}">selected="selected"</c:if> value="2">省会</option>
                                <option <c:if test="${direction.areaType == 3}">selected="selected"</c:if> value="3">地级市</option>
                                <option <c:if test="${direction.areaType == 4}">selected="selected"</c:if> value="4">县级市</option>
                            </select>
                        </td>
                        <td>
                            <select id="regionType" name="regionType" onchange="selectAreaCity()" class="form-control">
                                <option <c:if test="${direction.regionType == 5}">selected="selected"</c:if> value="5">市区</option>
                                <option <c:if test="${direction.regionType == 6}">selected="selected"</c:if> value="6">郊区</option>
                            </select>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>地址信息</th>
                        <td>
                            <select id = "provinceId" name="provinceId" onchange="selectArea()" class="form-control" required="required">
                                <option value="">请选择</option>
                                <c:forEach items="${apiRsp.results}" var="area">
                                    <c:if test="${area.cityType == 1}">
                                        <option <c:if test="${area.areaId == direction.provinceId}">selected="selected"</c:if>  value="${area.areaId}">${area.areaName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <input type="hidden" id="province" name="province" value="">
                            <input type="hidden" id="city" name="city" value="">
                            <input type="hidden" id="district" name="district" value="">
                            <input type="hidden" id="directionId" name="directionId" value="${direction.id}">
                        </td>
                        <td>
                            <select id = "cityId" name="cityId" onclick="selectAreaCity()" class="form-control" >
                                <option value="">请选择</option>
                                <option value="${direction.cityId}">${direction.city}</option>
                            </select>
                        </td>
                        <td>
                            <select id = "districtId" name="districtId" onclick="selectAreaDistrict()" class="form-control" >
                                <option value="">请选择</option>
                            </select>
                        </td>
                        <td>

                        </td>
                        <td>

                        </td>
                        <td>

                        </td>
                        <td>

                        </td>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <th>任务类型</th>
                        <td colspan="3">
                            <div>
                                <select class="form-control" id="taskId" name="taskId" required="required" onchange="initNewName()">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dto.tasks}" var="item">
                                        <option <c:if test="${direction.taskId == item.taskId}">selected="selected" </c:if>  value="${item.taskId}">${item.taskName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>任务子类</th>
                        <td colspan="8">
                            <select class="form-control" id="newId" name="newId" required="required">
                                <option value="">请选择</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            方向名称
                        </th>
                        <td colspan="8">
                            <input type="text" id="directionName" name="directionName" class="form-control" value="${direction.directionName}" />
                        </td>
                    </tr>
                    <tr>
                        <th>方向内容</th>
                        <td colspan="8">
                            <textarea name="directionText" required="required" class="form-control">${direction.directionText}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>方向附件</th>
                        <td colspan="8">
                            <div>
                                <input type="hidden" id="files" name="files" value="" />
                                <input type="hidden" id="uploadFileDir" name="uploadFileDir" value="" />
                                <input type="hidden" id="path" name="path"  value="" />
                                <input type="hidden" id="fileRealName" name="fileRealName"  value="" />
                                <input type="hidden" id="uploadPaths" name="uploadPaths"  value="" />
                                <div id="uploadDiv" class="form-control" style="display: none"></div>
                            </div>
                            <div>
                                <%--<input id="fileuploadDirection" onclick="setDataURL()" class="form-control" type="file" name="file" multiple data-url="${ctx}/sftp/survey/uploadSftp?modelType=direction&surveyCno=${dto.surveyRiskCaseInfo.surveyCno}"><br>--%>
                                <input id="fileuploadDirection" onclick="return setDataURL()" class="form-control" type="file" name="file" multiple><br>
                            </div>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == 'upload' || btnCode == 'primary' || btnCode == 'orgPrimary'}">
                    <tr>
                        <th>报告</th>
                        <td>
                            <div>
                                <input type="hidden" id="path" name="path"  value="" />
                                <input type="hidden" id="fileRealName" name="fileRealName"  value="" />
                                <input type="hidden" id="uploadPaths" name="uploadPaths"  value="" />
                                <div id="uploadDiv" class="form-control" style="display: none"></div>
                            </div>
                            <div>
                                <input id="fileupload" class="form-control" type="file" name="file" multiple data-url="${ctx}/sftp/survey/uploadSftp?modelType=report&surveyCno=${surveyCno}"><br>
                            </div>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == 'punish'}">
                    <tr>
                        <th>处罚事由</th>
                        <td colspan="8">
                            <textarea name="remark" required="required" rows="4" class="form-control"></textarea>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == 'score'}">
                    <tr>
                        <th>评分等级</th>
                        <td colspan="8">
                            <select name="scoreLevel" id ="scoreLevel" class="form-control" required="required">
                                <option value="1">优质</option>
                                <option value="2" selected>合格</option>
                                <option value="3">不合格</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>评分描述</th>
                        <td colspan="8">
                            <textarea name="scoreRemark" rows="4" class="form-control"></textarea>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == 'updAssign'}">
                    <tr>
                        <td>分配机构</td>
                        <td colspan="2" style="text-align: left">
                            <select class="form-control" required="required" onchange="changeSurveyOrg()" style="display: inline-flex" name="surveyOrgId" id="surveyOrgId">
                                <option value="">请选择</option>
                                <c:forEach items="${surveyfranchisees}" var="item">
                                    <option value="${item.id}">${item.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>分配人</td>
                        <td colspan="2" style="text-align: left">
                            <input type="hidden" name="assignUserId" id="assignUserId" />
                            <select class="form-control" required="required" onchange="changeSurveyUser(this)" style="display: inline-flex" id="surveyUserId">
                                <option value="">请选择</option>
                            </select>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == '118'}">
                    <tr>
                        <th>注意事项：</th>
                        <td colspan="8" style="color: red">
                            删除机构同时将删除该机构下的调查员任务及方向！
                        </td>
                    </tr>
                    <tr>
                        <th>删除原因</th>
                        <td colspan="8">
                            <textarea name="orgOpinion" required="required" rows="4" class="form-control"></textarea>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == 'delCase'}">
                    <tr>
                        <th>删除原因</th>
                        <td colspan="8">
                            <textarea name="orgOpinion" required="required" rows="4" class="form-control"></textarea>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == 'case-return'}">
                    <tr>
                        <div class="dalogs-1128">
                            <div class="d-title">请选择退回原因</div>
                            <div class="d-type active" name="reasons" data-code="1">
                                不在我负责的区域范围
                            </div>
                            <div class="d-type" name="reasons" data-code="2">
                                工作量饱和，来不及调查
                            </div>
                            <div class="d-type" id="other" name="reasons" data-code="3">
                                其他
                            </div>
                            <div class="d-textarea">
                                <textarea name="reasonThree" id="reasonThree" cols="30" rows="10"></textarea>
                            </div>
                            <input type="hidden" id="reason" name="reason"  value="" />
                            <%--<div class="d-btns">
                                <div class="d-btn" id="cancel">取消</div>
                                <div class="d-btn d-btn-active" id="commit">提交</div>
                            </div>--%>
                        </div>
                    </tr>
                </c:if>
                <c:if test="${btnCode == 'primary-veto'}">
                    <input type="hidden" id="vetos" name="vetos" />
                    <c:forEach items="${vetos}" var="item">
                        <tr>
                            <td>
                                <c:if test="${item.type == 1}">机构名称</c:if>
                                <c:if test="${item.type == 2}">调查员名称</c:if>
                            </td>
                            <td>${item.name}</td>
                            <td>
                                <input type="checkbox" name="chkIds" data-type="${item.type}" class="form-control" value="${item.id}">
                            </td>
                        </tr>
                        <tr>
                            <td>退回原因</td>
                            <td colspan="2">
                                <textarea id="opinion${item.id}${item.type}" name="opinion${item.id}" class="form-control"></textarea>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${btnCode == 'extension-time'}">
                    <c:if test="${roleCode == 'orgManager' || roleCode == 'lfManager'}">
                        <c:if test="${roleCode == 'lfManager'}">
                            <tr>
                                <th>案件截止日期</th>
                                <td colspan="8">
                                    <input  type="text" class="form-control" required="required" style="cursor: auto; background-color:#fff"
                                           value="<fmt:formatDate value="${extension.endTime}" pattern="yyyy-MM-dd"/>" readonly>
                                </td>
                            </tr>
                            <tr>
                                <th>原机构截止日期</th>
                                <td colspan="8">
                                    <input id="orgEndTime" name="orgEndTime" type="text" class="form-control" required="required" style="cursor: auto; background-color:#fff"
                                           value="<fmt:formatDate value="${extension.oldOrgEndTime}" pattern="yyyy-MM-dd"/>" readonly>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${roleCode == 'orgManager'}">
                            <tr>
                                <th>原机构截止日期</th>
                                <td colspan="8">
                                    <input id="orgEndTime" name="orgEndTime" type="text" class="form-control" required="required" style="cursor: auto; background-color:#fff"
                                           value="<fmt:formatDate value="${assignOrg.orgEndTime}" pattern="yyyy-MM-dd"/>" readonly>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <th>申请延期至</th>
                            <td colspan="8">
                                <input id="extensionTime" name="extensionTime" type="text" class="form-control" required="required" style="cursor: auto; background-color:#fff"
                                    <c:if test="${(roleCode == 'orgManager' && assignOrg.extensionState != 2) || roleCode == 'lfManager'}"> value="<fmt:formatDate value="${extension.extensionTime}" pattern="yyyy-MM-dd 23:59:59"/>" </c:if>
                                    placeholder="请选择日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59'})" readonly>
                            </td>
                        </tr>
                        <tr>
                            <th>延期原因</th>
                            <td colspan="8">
                                <textarea name="extensionReason"  rows="4" class="form-control"><c:if test="${(roleCode == 'orgManager' && assignOrg.extensionState != 2) || roleCode == 'lfManager'}">${extension.extensionReason}</c:if></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th>证据附件</th>
                            <td colspan="8">
                                <div>
                                    <c:if test="${(roleCode == 'orgManager' && assignOrg.extensionState != 2) || roleCode == 'lfManager'}">
                                        <c:forEach items="${extension.commonFiles}" var="file">
                                               <img width="90px" height="60px" onclick="onlinePreview('${file.filePath}')"  class="picToBig" src="${file.filePath}"/>
                                        </c:forEach>
                                    </c:if>
                                </div>
                                <c:if test="${roleCode == 'orgManager'}">
                                <div>
                                    <%--onlinePreview(\""+file.filePath+"\")' target='_blank'--%>
                                    <input type="hidden" id="pathBackReason" name="pathBackReason"  value="" />
                                    <input type="hidden" id="fileRealName" name="fileRealName"  value="" />
                                    <input type="hidden" id="uploadPaths" name="uploadPaths"  value="" />
                                    <div id="uploadDiv" class="form-control" style="display: none"></div>
                                </div>
                                <div>
                                    <input id="fileupload" class="form-control" type="file" name="file" multiple data-url="${ctx}/sftp/survey/uploadSftp?modelType=extensionTime&surveyCno=${assignOrg.surveyRiskCaseInfoDto.surveyCno}&assignOrgId=${assignOrg.id}"><br>
                                </div>
                                </c:if>
                                <c:if test="${roleCode == 'lfManager'}">
                                    <c:if test="${extension.extensionFiles !=null}">
                                        <div>
                                            <a href="javascript:void(0);" onclick="downFileExtensionTime('${assignOrg.surveyRiskCaseInfoDto.surveyCno}','${assignOrg.id}')">下载全部附件</a>
                                        </div>
                                    </c:if>
                                    <c:if test="${extension.extensionFiles ==null}">暂无附件</c:if>
                                </c:if>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${roleCode == 'lfManager-back' || (roleCode == 'lfManager' && assignOrg.extensionState == 3)}">
                        <tr>
                            <th>驳回原因</th>
                            <td colspan="8">
                                <textarea name="extensionBackReason"  rows="4" class="form-control">${extension.extensionBackReason}</textarea>
                            </td>
                        </tr>
                    </c:if>

                </c:if>
                <c:if test="${btnCode == 'otherReply'}">
                    <tr>
                        <th>回复内容</th>
                        <td colspan="8">
                            <textarea name="replyContent" rows="4" class="form-control">${extension.extensionReason}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>证据附件</th>
                        <td colspan="8">
                            <div>
                                <c:if test="${(roleCode == 'orgManager' && assignOrg.extensionState != 2)}">
                                    <c:forEach items="${extension.commonFiles}" var="file">
                                        <img width="90px" height="60px" onclick="onlinePreview('${file.filePath}')"  class="picToBig" src="${file.filePath}"/>
                                    </c:forEach>
                                </c:if>
                            </div>
                            <div>
                                <input type="hidden" id="pathBackReason" name="pathBackReason"  value="" />
                                <input type="hidden" id="fileRealName" name="fileRealName"  value="" />
                                <input type="hidden" id="uploadPaths" name="uploadPaths"  value="" />
                                <div id="uploadDiv" class="form-control" style="display: none"></div>
                            </div>
                            <div>
                                <input id="fileupload" class="form-control" type="file" name="file" multiple
                                       data-url="${ctx}/sftp/survey/uploadSftp?modelType=otherReply&assignOrgId=${replyOrgCaseId}"><br>
                            </div>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <c:if test="${btnCode != 'extension-time' && (replyId == null || replyId == '')}">
                <button type="submit" onclick="return validFile('${btnCode}');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>确认</button>
            </c:if>
            <c:if test="${btnCode == 'extension-time'}">
                <c:if test="${roleCode == 'orgManager'}">
                    <c:if test="${extension.extensionState ==null || extension.extensionState == 3 || extension.extensionState == 2}">
                        <button type="submit" onclick="return validFile('${btnCode}');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>提交</button>
                    </c:if>
                    <c:if test="${extension.extensionState == 1}">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
                    </c:if>
                </c:if>
                <c:if test="${roleCode == 'lfManager' && extension.extensionState == 1}">
                    <button type="button" onclick="extensionBack('${btnCode}');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>驳回</button>
                    <button type="submit" onclick="return validFile('${btnCode}');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>审核通过</button>
                </c:if>
                <c:if test="${roleCode == 'lfManager-back'}">
                    <button type="submit" onclick="return validFile('${btnCode}');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>提交</button>
                </c:if>
            </c:if>
            <c:if test="${btnCode == 'direction'}">
                <input type="hidden" name="successDirection" id="successDirection" />
                <button type="submit" onclick="return validFile('successDirection');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>完成调查方向</button>
            </c:if>
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
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
<script type="text/javascript">
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
        if (btnCode == 'otherReply'){
            var type = $("input[name=type]").val();
            if (type == 4){
                var replyContent =  $("textArea[name=replyContent]").val();
                if (replyContent == ''){
                    alert("回复内容不能为空")
                    return false;
                }
            }
        }

        $("button[type=submit]").attr("disabled",true);
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
        event.preventDefault();
    });

    function validFile(btnCode){
        if(btnCode == 'upload' || btnCode == 'primary'){
            var path = $("#path").val();
            if(!path){
                alert("请上传报告");return false;
            }
        }else if(btnCode == 'direction' || btnCode == 'successDirection'){
            var val = $("#provinceId").find("option:selected").text();
            var val1 = $("#cityId").find("option:selected").text();
            var val2 = $("#districtId").find("option:selected").text();
            $("#province").val(val);
            $("#city").val(val1);
            $("#district").val(val2);
            $("#files").val(JSON.stringify(files));
            if(btnCode == 'successDirection'){
                $('#successDirection').val('success');
            }else{
                $('#successDirection').val(null);
            }
        }else if(btnCode == 'case-return'){
            var reasonType ="";
            $("div[name='reasons']").each(function(i, obj){
                if($(obj).attr("class") == 'd-type active'){
                    reasonType = $(obj).attr('data-code');
                }
            });
            if(reasonType == 1){
                $("#reason").val("不在我负责的区域范围");
            }else if(reasonType == 2){
                $("#reason").val("工作量饱和，来不及调查");
            }else if(reasonType == 3){
                var reasonThree = $("#reasonThree").val()
                if(!reasonThree){
                    alert("请输入退回原因");return false;
                }
                $("#reason").val(reasonThree);
            }
        }else if (btnCode == 'primary-veto') {
            var vetos = [];
            $("input:checkbox[name='chkIds']:checked").each(function() { // 遍历name
                var id = $(this).val();
                var type = $(this).attr("data-type");
                var opinion = $("#opinion" + id + "" + type).val()
                vetos.push({
                    id : id,
                    type : type,
                    opinion : opinion
                });
            });
            if (vetos.length == 0){
                alert("未选中任何数据");
                return false;
            }
            $("#vetos").val(JSON.stringify(vetos));
        }else if(btnCode == 'extension-time'){
            /*var pathBackReason = $("#pathBackReason").val();
            if(pathBackReason.length == 0){
                alert("证据附件必填");return false;
            }*/
        }
        return true;
    }
    // $("form").submit(function(e){
    //     $("button[type=submit]").attr("disabled",true);
    //     var operateType = $("#operateType").val();
    //     if (operateType != null && operateType != '' && operateType =='caseRemind'){
    //         parent.reload();
    //     }
    //
    // });
    toAddressData();

    function toAddressData(){
        var areas = new Array();
        var value = $("#areaType").val();
        $("#provinceId option").remove();
        $("#provinceId").append("<option value=''>请选择</option>");
        $("#cityId option").remove();
        $("#cityId").append("<option value=''>请选择</option>");
        $("#districtId option").remove();
        $("#districtId").append("<option value=''>请选择</option>");
        <c:forEach items="${apiRsp.results}" var="item">
            var area = {"areaId":${item.areaId},"areaName":"${item.areaName}","cityType":${item.cityType == null ? 0 : item.cityType}};
            areas.push(area);
        </c:forEach>
        for(var i = 0 ; i < areas.length ; i++){
            var area = areas[i];
            if(value == 1){
                if(area.cityType == 1){
                    $("#provinceId").append("<option value='"+area.areaId+"'>"+area.areaName+"</option>");
                }
            }else{
                if(area.cityType != 1){
                    $("#provinceId").append("<option value='"+area.areaId+"'>"+area.areaName+"</option>");
                }
            }
        }
        var provinceId = "${direction.provinceId}";
        if(provinceId != null && provinceId != ''){
            $("#provinceId").find("option[value=" + provinceId + "]").attr("selected",true);
            selectArea();
        }

        if(value == 1){
            $("#regionType").show();
        }else{
            $("#regionType").hide();
        }
    }

    toBuild(3);

    function toBuild(value){
//        var value = $("#areaType").val();
        if(value == 1){
            $("#provinceId").attr("required","required");
            $("#cityId").attr("required",null);
            $("#districtId").attr("required",null);
            $("#span_city").hide();
            $("#span_district").hide();
            $("#cityId").hide();
            $("#districtId").hide();
        }else if(value == 2){
            $("#provinceId").attr("required","required");
            $("#cityId").attr("required","required");
            $("#districtId").attr("required",null);
            $("#span_city").show();
            $("#span_district").hide();
            $("#cityId").show();
            $("#districtId").hide();
        }else if(value == 3){
            $("#provinceId").attr("required","required");
            $("#cityId").attr("required","required");
            $("#districtId").attr("required","required");
            $("#span_city").show();
            $("#span_district").show();
            $("#cityId").show();
            $("#districtId").show();
        }
    }

    function returnCallback(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){
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

    function setDataURL(){
        var taskValue = $("#taskId").val();
        var taskName = $("#taskId").find("option:selected").text();
        var directionName = $("#directionName").val();
        if(!taskValue || !taskName || !directionName){
            alert("请填写任务类型和方向名称");
            return false;
        }
        $('#fileuploadDirection').fileupload({
            url : "${ctx}/sftp/survey/uploadSftp?modelType=direction&surveyCno=${dto.surveyRiskCaseInfo.surveyCno}&taskName=" + taskName + "&directionName=" + directionName,
            done: function (e, data) {
                var value = "";
                var r  = data.result;
                var file = r.surveyFile;
                var value = "<a onclick='onlinePreview(\""+file.filePath+"\")' target='_blank'>" + file.fileName + "</a>&nbsp;&nbsp;&nbsp;&nbsp;"
                $("#uploadDiv").empty();
                values = values += value;
                $("#uploadDiv").append(values);
                $("#path").val(file.filePath);
                $("#fileRealName").val(file.fileName);
                $("#uploadPaths").val(value);
                $("#uploadDiv").show();

                var item = {
                    "fileName" : file.fileName,
                    "filePath" : file.filePath
                };
                files.push(item);
            }
        });
    }
    var files = [];
    var values = "";
    var pathBackReason =""; //extension-time'时的上传路径
    //材料凭证
    $('#fileupload').fileupload({
        done: function (e, data) {
            var value = "";
            var r  = data.result;
            var file = r.surveyFile;
            var value = "<a onclick='onlinePreview(\""+file.filePath+"\")' target='_blank'>" + file.fileName + "</a>&nbsp;&nbsp;&nbsp;&nbsp;"
            $("#uploadDiv").empty();
            values = values += value;
            $("#uploadDiv").append(values);
            $("#path").val(file.filePath);
            pathBackReason += file.filePath + ","  //extension-time'时的上传路径
            $("#pathBackReason").val(pathBackReason); //extension-time'时的上传路径
            $("#fileRealName").val(file.fileName);
            $("#uploadPaths").val(value);
            $("#uploadDiv").show();

            var item = {
                "fileName" : file.fileName,
                "filePath" : file.filePath
            };
            files.push(item);
        }
    });
    function selectArea(){

        var areaType = $("#areaType").val();
        var orgProvinceId = $("#provinceId").val();
        if(orgProvinceId == 0){
            return;
        }
        ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgProvinceId},function(v,e,p){
            $("#cityId option").remove();
            $("#cityId").append("<option value=''>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                if(areaType == 2){
                    if(val.cityType == 2){
                        $("#cityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                    }
                }else{
                    if(val.cityType != 2){
                        $("#cityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                    }
                }
            }
            var cityId = "${direction.cityId}";
            if(cityId != null){
                $("#cityId").find("option[value=" + cityId + "]").attr("selected",true);
                selectAreaCity();
            }
            $("#districtId option").remove();
            $("#districtId").append("<option value=''>请选择</option>");
        });

        //获取“区域名称”及“区域名称id”
        $("#areaTypeId").val(orgProvinceId);
        var val = $("#provinceId").find("option:selected").text();
        $("#areaName").val(val);
//        selectAreaCity();
    }
    function  selectAreaCity(){
        <%--if(${direction.cityId != null}){--%>
            <%--$("#cityId").find("option[value=${direction.cityId}]").attr("selected",true);--%>
        <%--}--%>

        var areaType = $("#areaType").val();
        var orgCityId = $("#cityId").val();
        if(orgCityId == 0){
            return;
        }
        ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgCityId},function(v,e,p){
            $("#districtId option").remove();
            $("#districtId").append("<option value=''>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                if(areaType == 3){
                    if(val.cityType == 3){
                        $("#districtId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                    }
                }
                if(areaType == 4){
                    if(val.cityType == 4){
                        $("#districtId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                    }
                }
                if(areaType == 1){
                    var regionType = $("#regionType").val();
                    if(regionType == 5){//市区
                        if(val.cityType == 5){
                            $("#districtId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                        }
                    }
                    if(regionType == 6){//郊区
                        if(val.cityType == 6){
                            $("#districtId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                        }
                    }
                }
                if(areaType != 3 && areaType != 4 && areaType != 1){
                    $("#districtId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }
            }

            var districtId = "${direction.districtId}";
            if(cityId != null){
                $("#districtId").find("option[value=" + districtId + "]").attr("selected",true);
                selectAreaDistrict();
            }
        });
        //获取“区域名称”及“区域名称id”
        $("#areaTypeId").val(orgCityId);
        var val = $("#cityId").find("option:selected").text();
        $("#areaName").val(val);

//        selectAreaDistrict();
    }

    function  selectAreaDistrict(){
        <%--if(${direction.districtId != null}){--%>
            <%--$("#districtId").find("option[value=${direction.districtId}]").attr("selected",true);--%>
        <%--}--%>

        //获取“区域名称”及“区域名称id”
        var orgDistrictId = $("#districtId").val();
        $("#areaTypeId").val(orgDistrictId);
        var val = $("#districtId").find("option:selected").text();
        $("#areaName").val(val);

        initNewName();
    }

    //加载方向名称下拉框
    function initNewName(){
        var taskId = $("#taskId").val();
        if(!taskId){
            return;
        }
        ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId",{"surveyCode":"taskInfo","btnCode":"1000","taskInfoId":taskId},function(v,e,p){
            $("#newId option").remove();
            $("#newId").append("<option value=''>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#newId").append("<option value='" + val.id + "'>" + val.name + "</option>");
            }
            var newId = "${direction.newId}";
            if(newId != null){
                $("#newId").find("option[value=" + newId + "]").attr("selected",true);
            }
        });
    }

    $(function () {
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

    var extensionBack = function(){
        openDialog({
            frame:true,
            title:"驳回",
            height:500,
            width:800,
            url:"${ctx}/survey/case/sic/operateView?id="+'${id}'+"&btnCode=${btnCode}&roleCode=lfManager-back&extensionId="+'${extension.id}',
            load:true
        });
    }

    var downFileExtensionTime = function(surveyCno,assignOrgId){
        var url = "${ctx}/sftp/survey/downSftp",param = {"surveyCno" : surveyCno,"assignOrgId" : assignOrgId,"uploadType" : "extensionTime"};
        if(confirm('是否确认下载？')){
            ajaxSubmit(url,param,function(v,e,p){
                var path = e.data.results;
//                path = path.replace("/mnt/sftp/files/","https://ddrapi.shlefan.com/");
//                window.open(path);
                window.location.href = path;
            })
        }
    }
</script>
</body>
</html>
