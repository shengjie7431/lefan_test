<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    .div_remark{
        position: fixed;
        bottom: 0px;
        width: 100%;
        height: auto;
        background-color: #fff;
        box-shadow: 0-2px 3px #eee;
    }
    .table-content{
        overflow: auto;
        max-height: 450px;
        border: 1px solid #333;
    }


    /*.table-content td{*/
    /*    word-break: break-all;*/
    /*}*/

    /*.table-content tbody {*/
    /*    display:block;*/
    /*    overflow:auto;*/
    /*}*/
    /*.table-content thead tr,tbody tr {*/
    /*    display:table;*/
    /*    table-layout: fixed;*/
    /*}*/
    /*.table-content thead{*/
    /*    width: calc(100% - 1em);*/
    /*}*/
</style>

<body>
<%--<div class="container">--%>
    <form id="editForm" role="form" action="${ctx}/survey/case/sic/operate" method="post" style="padding: 10px;" >
        <%--//1单机构单人任务提交    2单机构多人任务的最后一位提交    3多机构 辅助机构最后一位提交    4多机构  主机构最后一位提交--%>
        <c:if test="${showInfo.type == 1 || showInfo.type == 2 || showInfo.type == 3}">
            <div class="table-title">方向详情</div>
            <div class="table-content">
                <table class="table" border="1" cellpadding="0" cellspacing="0" style="text-align: left">
                    <thead>
                    <c:if test="${showInfo.type == 1 || showInfo.type == 3 || showInfo.type == 2}">
                        <th width="100">调查员</th>
                    </c:if>
                    <th  width="150">区域</th>
                    <th width="100">任务类型</th>
                    <th width="100">任务子类</th>
                    <th width="100">方向名称</th>
                    <th>方向内容</th>
                    <th width="100">操作</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${showInfo.directionDtos}" var="item">
                        <tr>
                            <c:if test="${showInfo.type == 1 || showInfo.type == 3 || showInfo.type == 2}">
                                <td width="100">${item.surveyInvestigatorCase.surveyUserName}</td>
                            </c:if>
                            <td width="150">${item.areaName}</td>
                            <td width="100"><span style="background-color: ${item.taskColor};color: #fff">&nbsp;&nbsp;${item.taskName}&nbsp;&nbsp;</span></td>
                            <td width="100"><span style="background-color: ${item.newColor};color: #fff">&nbsp;&nbsp;${item.newName}&nbsp;&nbsp;</span></td>
                            <td width="100">${item.directionName}</td>
                            <td >${item.directionText}</td>
                            <td width="100">
                                <a href="javascript:void(0);" onclick="directionFiles(${item.id},'directionFiles','${showInfo.surveyRiskCaseInfo.surveyCno}')">详情(${item.surveyCaseDirectionFiles.size()})</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <c:if test="${showInfo.type == 4}">
            <table class="table" border="1" cellpadding="0" cellspacing="0" style="text-align: left;border: none">
                <thead>
                <th>机构</th>
                <th>调查员名称</th>
                <th>状态<span style="color: red;">(${showInfo.successNum}/${showInfo.tasks.size()})</span></th>
                <th>机构小结</th>
                <th>操作</th>
                </thead>
                <tbody>
                <c:forEach items="${showInfo.tasks}" var="item" varStatus="nos">
                    <tr>
                        <td>${item.surveyOrgName}</td>
                        <td>
                            <c:forEach items="${item.cases}" var="c">
                                ${c.surveyUserName}<br/>
                            </c:forEach>
                        </td>
                        <td>
                            <c:if test="${item.orgSurveyState == 0}">待接收</c:if>
                            <c:if test="${item.orgSurveyState == 1}">已接收(调查中)</c:if>
                            <c:if test="${item.orgSurveyState == 2}">初审中</c:if>
                            <c:if test="${item.orgSurveyState == 3}">已拒绝</c:if>
                            <c:if test="${item.orgSurveyState == 4}">初审通过</c:if>
                            <c:if test="${item.orgSurveyState == 5}">复核退回</c:if>
                            <c:if test="${item.orgSurveyState == 6}">初审中有退回</c:if>
                        </td>
                        <td>${item.orgSummary}</td>
                        <td><a href="javascript:void(0);" onclick="showDirectionDetail(${item.id},${item.directions.size()})">
                                <span id="span_btn_${item.id}">
                                    <c:if test="${item.orgPrimaryType == 1 && item.directions.size() > 0}">收起</c:if>
                                <c:if test="${item.orgPrimaryType != 1 || item.directions.size() == 0}">查看方向详情(${item.directions.size()})</c:if>
                                </span>
                            </a>
                        </td>
                    </tr>
                    <tr id="tr_infos_${item.id}" <c:if test="${item.orgPrimaryType != 1 || item.directions.size() == 0}">style="display: none;"</c:if>>
                        <td colspan="5">
                            <table class="table" border="1" cellpadding="0" cellspacing="0" style="text-align: left;background-color: #faf2cc">
                                <thead>
                                <th width="150">区域</th>
                                <th width="100">任务类型</th>
                                <th width="100">任务子类</th>
                                <th width="150">方向名称</th>
                                <th>方向内容</th>
                                <th width="100">附件</th>
                                </thead>
                                <tbody>
                                <c:forEach items="${item.directions}" var="direction">
                                    <tr>
                                        <td>${direction.areaName}</td>
                                        <td><span style="background-color: ${direction.taskColor};color: #fff">&nbsp;&nbsp;${direction.taskName}&nbsp;&nbsp;</span></td>
                                        <td><span style="background-color: ${direction.newColor};color: #fff">&nbsp;&nbsp;${direction.newName}&nbsp;&nbsp;</span></td>
                                        <td>${direction.directionName}</td>
                                        <td>${direction.directionText}</td>
                                        <td><a href="javascript:void(0);" onclick="directionFiles(${direction.id},'directionFiles','${showInfo.surveyRiskCaseInfo.surveyCno}')">详情(${direction.surveyCaseDirectionFiles.size()})</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
            <div class="div_remark">
                <c:if test="${btnCode != 'tasks'}">
                    <c:if test="${showInfo.type == 1 || showInfo.type == 2 || showInfo.type == 4}">
                        <div class="table-title">机构小结</div>
                        <textarea class="form-control" style="width: 98%;margin: 0 auto;"
                            <%--                              <c:if test="${!showInfo.allSuccess}">placeholder="请等待其他机构审核通过后再填写报告结论（其他机构审核通过后，系统会在消息中心通知，请注意查看）" readonly</c:if>--%>
<%--                                  <c:if test="${showInfo.allSuccess}">placeholder="请填写报告结论"</c:if>--%>
                                  placeholder="请填写机构小结" <c:if test="${view == 'view'}">readonly</c:if>
                                  name="orgSummary"  rows="6" required >${showInfo.surveyAssignOrg.orgSummary}</textarea>
                    </c:if>
                    <c:if test="${showInfo.type == 3}">
                        <div class="table-title">机构小结</div>
                        <textarea class="form-control" placeholder="请填写机构小结" <c:if test="${view == 'view'}">readonly</c:if>
                                  name="orgSummary" rows="6" required>${showInfo.surveyAssignOrg.orgSummary}</textarea>
                    </c:if>
                </c:if>
                <c:if test="${btnCode != 'tasks' && view != 'view'}">
                    <%--                <c:if test="${showInfo.allSuccess}">--%>
                    <%--                    <div class="modal-footer">--%>
                    <%--                        <input type="hidden" name="id" value="${id}"/>--%>
                    <%--                        <input type="hidden" name="surveyInfoId" value="${surveyInfoId}"/>--%>
                    <%--                        <input type="hidden" name="btnCode" id="btnCode" />--%>
                    <%--                        <input type="hidden" name="typeCode" id="typeCode" />--%>
                    <%--                        <button type="submit" onclick="setParam(${showInfo.type})" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>确认</button>--%>
                    <%--                    </div>--%>
                    <%--                </c:if>--%>
                    <%--                <c:if test="${!showInfo.allSuccess}">--%>
                    <%--                    <div class="modal-footer">--%>
                    <%--                        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:closeDialog();">我知道了</button>--%>
                    <%--                    </div>--%>
                    <%--                </c:if>--%>
                    <div class="modal-footer" style="border-top: none">
                        <input type="hidden" name="id" value="${id}"/>
                        <input type="hidden" name="surveyInfoId" id="surveyInfoId" value="${surveyInfoId}"/>
                        <input type="hidden" name="btnCode" id="btnCode" />
                        <input type="hidden" name="typeCode" id="typeCode" />
                        <input type="hidden" name="nullCode" id="nullCode" value="${nullCode}" />
                        <button type="submit" onclick="return setParam(${showInfo.type})" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>提交审核</button>
                    </div>
                </c:if>
                <c:if test="${btnCode == 'tasks'}">
                    <div class="modal-footer" style="display: none;">
                        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:closeDialog();">关闭</button>
                    </div>
                </c:if>
            </div>
    </form>
<%--</div>--%>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    function showDirectionDetail(id,size){
        if (size == 0){
            return;
        }
        var value = $("#span_btn_" + id).text().trim();
        if (value == '收起'){
            $("#tr_infos_" + id).hide();
            $("#span_btn_" + id).text("查看方向详情(" + size + ")");
        }else{
            $("#tr_infos_" + id).show();
            $("#span_btn_" + id).text("收起");
        }
    }
    function setParam(type){
        $("#btnCode").val("commit-to-org");
        if (type == 1 || type == 2 || type ==4){
            $("#typeCode").val("last-org-primary");
            // var nullCode = $("#nullCode").val();
            // if (nullCode){
            //     var f = confirm("基础信息未完成，补充基础信息？");
            //     if (f){
            //         update($("#surveyInfoId").val());
            //     }
            //     return false;
            // }
            // 提示确认
            var f = confirm("是否确认提交？")
            if (!f){
                return false;
            }
        }else if(type == 3){
            $("#typeCode").val("last-org-help");
        }
        return true;
    }

    var update = function(id){
        var width = $(document.body).outerWidth();
        openDialog({
            frame:true,
            title:"详情",
            height:700,
            width:width-100,
            url:"${ctx}/survey/case/info?id=" + id + "&menuCode=dcy-list"+"&btnCode=survey-base-info-upd",
            load:true
        });
    }

    $("#editForm").bind('submit', function(event) {
        $(this).find(":submit").attr("disabled","true");
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
        event.preventDefault();
    });
    function returnCallback(event,param){
        $(this).find(":submit").removeAttr("disabled");
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){

        }else{
            alert(apiRsp.msg);return;
        }
        reloadParent();
    }

    <%--var directionFiles = function(id,btnCode){--%>
    <%--    var height = 500,width = 800;--%>
    <%--    var title = "附件详情";--%>
    <%--    var url = "${ctx}/survey/case/sic/directionFiles?directionId=" + id;--%>
    <%--    openDialog({--%>
    <%--        frame:true,--%>
    <%--        title:title,--%>
    <%--        height:height,--%>
    <%--        width:width,--%>
    <%--        url:url--%>
    <%--    });--%>
    <%--}--%>
    var directionFiles = function(id,btnCode,surveyCno){
        var height = 600,width = 1000;
        title = "附件详情";
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:"${ctx}/survey/case/sic/directionFiles?directionId=" + id + "&surveyCno=" + surveyCno
        });
    }

    var add = function(surveyCode){
        openDialog({
            frame:true,
            title:"添加",
            height:500,
            width:800,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode
        });
    }
</script>
</body>
</html>