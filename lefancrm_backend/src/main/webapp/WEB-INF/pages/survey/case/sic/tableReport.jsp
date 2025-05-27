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
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
</head>

<style>
    body{
        height: 100%;
    }
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

    .contain {
        display: flex;
        width: 100%;
        height: 100%;
        min-height: 100%;
        overflow: hidden;
        justify-content: space-between;
    }

    .body-left {
        width: 40%;
        min-width: 40%;
        height: 100%;
        min-height: 100%;
        border-right: 5px solid #bbb;
    }

    .body-right {
        width: 58%;
        min-width: 58%;
        height: 100%;
        min-height: 100%;
        overflow: auto;

    }

</style>

<body id="body">
    <div class="contain">
        <div class="body-left">
            <iframe id="iframeReport" width="100%" height="100%" src="https://view.officeapps.live.com/op/view.aspx?src=${httpReportPath}"></iframe>
        </div>
        <div class="body-right">
            <div>
                <table class="table">
                    <tbody>
                        <tr>
                            <th>被调查人</th>
                            <td><input type="text" class="form-control bas-info valid" data-valid="被调查人" data-code="upd-survey-person" value="${dto.surveyRiskCase.surveyPerson}"></td>
                        </tr>
                        <tr>
                            <th>联系号码</th>
                            <td><input type="text" class="form-control bas-info valid" data-valid="联系号码" data-code="upd-survey-person-tel" value="${dto.surveyRiskCase.surveryPersonTel}"></td>
                        </tr>
                        <tr>
                            <th>身份证号</th>
                            <td><input type="text" class="form-control bas-info valid" data-valid="身份证号" data-code="upd-id-number" value="${dto.surveyRiskCase.idNumber}"></td>
                        </tr>
                        <tr>
                            <th>年龄</th>
                            <td><input type="number" class="form-control bas-info valid" data-valid="年龄" data-code="upd-age" value="${dto.surveyRiskCase.age}"></td>
                        </tr>
                        <tr>
                            <th>性别</th>
                            <td>
                                <select class="form-control bas-info" data-code="upd-sex-new" >
                                    <option value="1" <c:if test="${dto.surveyRiskCase.sex == 1}">selected="selected"</c:if>>男</option>
                                    <option value="2" <c:if test="${dto.surveyRiskCase.sex == 2}">selected="selected"</c:if>>女</option>
                                </select>
                            </td>
                        </tr>
                        <%-- 乐凡、正言--%>
                        <c:if test="${dto.surveyRiskCase.modelId == 1 || dto.surveyRiskCase.modelId == 2 || dto.surveyRiskCase.modelId == 6 || dto.surveyRiskCase.modelId == 7}">
                            <tr>
                                <th>保险种类</th>
                                <td><input type="text" class="form-control bas-info valid" data-valid="保险种类"  data-code="upd-insure-type" value="${dto.surveyRiskCase.insureName}"></td>
                            </tr>
                            <tr>
                                <th>保额</th>
                                <td><input type="number" class="form-control bas-info" data-valid="保额"  data-code="upd-claims-money" value="${dto.surveyRiskCase.claimsMoney}"></td>
                            </tr>
                            <tr>
                                <th>调查号</th>
                                <td><input type="text" class="form-control bas-info" data-valid="调查号"  data-code="upd-hz-contact-name" value="${dto.surveyRiskCase.hzContactName}"></td>
                            </tr>
                            <tr>
                                <th>派案人</th>
                                <td><input type="text" class="form-control bas-info" data-valid="派案人"  data-code="upd-hz-contact-tel" value="${dto.surveyRiskCase.hzContactTel}"></td>
                            </tr>
                            <tr>
                                <th>投保日期</th>
                                <td>
                                    <input type="text" class="form-control bas-info-date valid" data-valid="投保日期" data-code="upd-insure-time" value="<fmt:formatDate value="${dto.surveyRiskCase.insureTime}" pattern="yyyy-MM-dd"/>"
                                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:pickerEd})" style="cursor: pointer" readonly  >
                                </td>
                            </tr>
                            <tr>
                                <th>出险日期</th>
                                <td>
                                        <input type="text" class="form-control bas-info-date valid" data-valid="出险日期" data-code="upd-danger-time" value="<fmt:formatDate value="${dto.surveyRiskCase.dangerTime}" pattern="yyyy-MM-dd"/>"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:pickerEd})" style="cursor: pointer" readonly  >
                                </td>
                            </tr>
                            <tr>
                                <th>出险地点</th>
                                <td><input type="text" class="form-control bas-info valid" data-valid="出险地点"  data-code="upd-danger-address" value="${dto.surveyRiskCase.dangerAddress}"></td>
                            </tr>
                        </c:if>
                        <%-- 中德--%>
                        <c:if test="${dto.surveyRiskCase.modelId == 3}">
                            <tr>
                                <th>保险合同编号</th>
                                <td><input type="text" class="form-control bas-info valid" data-valid="保险合同编号"   data-code="upd-policy-no" value="${dto.surveyRiskCase.policyNo}"></td>
                            </tr>
                            <tr>
                                <th>保单生效日</th>
                                <td><input type="text" class="form-control bas-info-date valid" data-valid="保单生效日"  data-code="upd-insure-take-time" value="<fmt:formatDate value="${dto.surveyRiskCase.dangerTime}" pattern="yyyy-MM-dd"/>"
                                        onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:pickerEd})" style="cursor: pointer" readonly  >
                                </td>
                            </tr>
                            <tr>
                                <th>出险日期</th>
                                <td>
                                    <input type="text" class="form-control bas-info-date valid" data-valid="出险日期" data-code="upd-danger-time" value="<fmt:formatDate value="${dto.surveyRiskCase.dangerTime}" pattern="yyyy-MM-dd"/>"
                                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:pickerEd})" style="cursor: pointer" readonly  >
                                </td>
                            </tr>
                        </c:if>
                        <%-- 中宏--%>
                        <c:if test="${dto.surveyRiskCase.modelId == 4}">
                            <tr>
                                <th>保险合同编号</th>
                                <td><input type="text" class="form-control bas-info valid" data-valid="保险合同编号" data-code="upd-policy-no" value="${dto.surveyRiskCase.policyNo}"></td>
                            </tr>
                            <tr>
                                <th>投保日期</th>
                                <td>
                                    <input type="text" class="form-control bas-info-date valid" data-valid="投保日期" data-code="upd-insure-time" value="<fmt:formatDate value="${dto.surveyRiskCase.insureTime}" pattern="yyyy-MM-dd"/>"
                                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:pickerEd})" style="cursor: pointer" readonly  >
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <th>案件基本信息</th>
                            <td>
                                <textarea class="form-control bas-info valid" id="surveyInfo" data-valid="案件基本信息" data-code="upd-survey-info"  rows="4" >${dto.surveyInfo}</textarea>
<%--                                <input type="text" class="form-control bas-info" data-code="upd-survey-info" value="${dto.surveyInfo}">--%>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 15%">调查事项</th>
                            <td><textarea class="form-control bas-info valid" id="surveyItem" data-valid="调查事项"  rows="4" data-code="upd-survey-item">${dto.surveyItem}</textarea>
<%--                                <input type="text" class="form-control bas-info" data-code="upd-survey-item" value="${dto.surveyItem}">--%>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
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
                            <div class="table-title">报告结论</div>
                            <textarea class="form-control bas-info" data-code="upd-survey-report-completion" style="width: 98%;margin: 0 auto;"
                                <%--                              <c:if test="${!showInfo.allSuccess}">placeholder="请等待其他机构审核通过后再填写报告结论（其他机构审核通过后，系统会在消息中心通知，请注意查看）" readonly</c:if>--%>
                                <%--                                  <c:if test="${showInfo.allSuccess}">placeholder="请填写报告结论"</c:if>--%>
                                      placeholder="请填写报告结论" <c:if test="${view == 'view'}">readonly</c:if>
                                      name="reportCompletion"  rows="6" required >${showInfo.surveyAssignOrg.orgSummary}</textarea>
                        </c:if>
                        <c:if test="${showInfo.type == 3}">
                            <div class="table-title">报告结论</div>
                            <textarea class="form-control" placeholder="请填写报告结论" <c:if test="${view == 'view'}">readonly</c:if>
                                      name="orgSummary" rows="6" required>${showInfo.surveyAssignOrg.orgSummary}</textarea>
                        </c:if>
                    </c:if>
                    <c:if test="${btnCode != 'tasks' && view != 'view'}">
                        <div class="modal-footer" style="border-top: none;position: fixed;bottom: 0px;right: 0px;">
                            <input type="hidden" name="id" value="${id}"/>
                            <input type="hidden" name="surveyInfoId" id="surveyInfoId" value="${surveyInfoId}"/>
                            <input type="hidden" name="btnCode" id="btnCode" />
                            <input type="hidden" name="typeCode" id="typeCode" />
                            <input type="hidden" name="nullCode" id="nullCode" value="${nullCode}" />
                            <c:if test="${showInfo.type != 1}">
                                <button type="button" class="btn" onclick="veto(${id})">退回</button>
                            </c:if>
                            <button type="button" class="btn" onclick="reloadReport()" >刷新报告</button>
                            <button type="submit" onclick="return setParam(${showInfo.type})" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>提交审核</button>
                        </div>
                    </c:if>
                    <c:if test="${btnCode == 'tasks'}">
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:closeDialog();">关闭</button>
                        </div>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
<%--<div class="container">--%>

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
        var valids = $('.valid');
        for(var i=0;i<valids.length; i++){
            if (!valids.eq(i).val()){
                alert(valids.eq(i).attr('data-valid') + "不能为空！")
                return false;
            }
        }
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
        // reloadParent();
    });
    function returnCallback(event,param){
        $(this).find(":submit").removeAttr("disabled");
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){

        }else{
            alert(apiRsp.msg);return;
        }
        var closeBtn = $("#diglog_close_btn",window.parent.parent.document);
        closeBtn.click();
        // reloadParent();
    }
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
    $(function () {
        $("body").height($(document).height());
        $("body").width($(document).width());

        $(".bas-info").on('blur',function(){
            var _this = $(this);
            var val = _this.val();
            var code = _this.attr("data-code");
            var url = "${ctx}/survey/case/operate";
            var param = {"id":$("#surveyInfoId").val(),"btnCode":"updInfoStr","updCode":code,"updValue":val,"reloadReport":"reloadReport"};
            ajaxSubmit(url,param,function(v,e,p){
                if(e.data.code==='0000'){
                    //重新生成报告。刷新页面
                    // reloadReport();
                }
            });
        });

        console.log("1",$("#iframeReport").contents())
        console.log("2",$("#iframeReport").contents().find("#wacframe").contents())
        console.log("3",$("#iframeReport").contents().find("#wacframe").contents().find("#WACContainer"));
        // $("#iframeReport").contents().find("#wacframe").contents().find("#WACContainer").scrollTop
        // setTimeout(function(){
        //     console.log($("#iframeReport").contents().find("#wacframe").contents().find("#WACContainer").attr('class'))
        //     $("#iframeReport").contents().find("#wacframe").contents().find("#WACContainer").attr('class');
        // },2000);

    })

    function pickerEd(){
        var _this = $(this);
        var updValue = $dp.cal.getDateStr("yyyy-MM-dd HH:mm:ss");
        var code = _this.attr("data-code");
        var url = "${ctx}/survey/case/operate";
        var param = {"id":$("#surveyInfoId").val(),"btnCode":"updInfoDate","updCode":code,"updValue":updValue,"reloadReport":"reloadReport"};
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code=='0000'){
                //重新生成报告。刷新页面
                // reloadReport();
            }
        });
    }

    function reloadReport(){
        var url = "${ctx}/survey/case/sic/operate";
        var data = {"surveyInfoId":$("#surveyInfoId").val(),"btnCode":"reloadReport"};
        $.ajax({
            url:url,
            data:data,
            success:function(event,param){
                var v = (new Date).getTime();
                if(param.data.generateReportPath){
                    var src = "https://view.officeapps.live.com/op/view.aspx?src=" + param.data.generateReportPath + "?v=" + v;
                    $('#iframeReport').attr('src', src);
                }
            }
        });
    }

    function veto(id){
        var height = 600;
        var width = $(document.body).outerWidth();
        var title = "退回";
        var url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=primary-veto";
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:url
        });
    }
</script>
</body>
</html>