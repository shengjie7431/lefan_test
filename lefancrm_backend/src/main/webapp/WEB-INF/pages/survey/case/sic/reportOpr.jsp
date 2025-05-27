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

</style>
<body>
<div class="main">
    <input type="hidden" id="menuCode" value="${menuCode}" />
    <div class="title">
        <button class="butList active" onclick="operate(${dto.surveyRiskCaseInfo.id},'files',false)">查看材料</button>
        <button class="butList active" onclick="operate(${dto.surveyRiskCaseInfo.id},'follow',false)">添加跟踪</button>
        <c:if test="${dto.surveyState == 0}">
            <button class="butList active" onclick="operate(${dto.id},'accept',true)">接收</button>
            <button class="butList active" onclick="operate(${dto.id},'refuse',false)">拒绝</button>
        </c:if>
        <c:if test="${menuCode == 'dcy-list'}">
            <c:if test="${dto.surveyState != 0 && dto.surveyState != 2}"> <!-- 已接收 且 不是拒绝状态  并且是主调查员显示任务详情-->
                <c:if test="${dto.surveyUserType == 1}">
                    <button class="butList active" onclick="operate(${dto.id},'details',false)">任务详情</button>
                </c:if>
            </c:if>
            <c:if test="${dto.surveyState == 1}">
                <c:if test="${dto.surveyUserType == 2}">
                    <c:if test="${dto.creportState == 0}">
                        <button class="butList active" onclick="operate(${dto.id},'upload',false)">上传报告</button>
                    </c:if>
                    <c:if test="${dto.creportState == 1}">
                        <button class="butList active" onclick="operate(${dto.id},'upload',false)">更新报告</button>
                        <button class="butList active" onclick="operate(${dto.id},'report-commit',true)">提交审核</button>
                    </c:if>
                    <button class="butList active" onclick="operate(${dto.id},'direction',false)">增加调查方向</button>
                </c:if>
            </c:if>
            <c:if test="${dto.surveyUserType == 1 && dto.surveyState == 4}"><!-- 审核通过  主调查员显示上传主报告/更新  和提交审核  辅助调查员显示增加方向 上传子报告/更新 -->
                <c:if test="${dto.surveyRiskCaseInfo.reportState == 0}">
                    <button class="butList active" onclick="operate(${dto.id},'primary',false)">上传主报告</button>
                </c:if>
                <c:if test="${dto.surveyRiskCaseInfo.reportState == 1}">
                    <button class="butList active" onclick="operate(${dto.id},'primary',false)">更新主报告</button>
                </c:if>
                <button class="butList active" onclick="operate(${dto.id},'commit',true)">提交审核</button>
            </c:if>
        </c:if>
        <c:if test="${dto.surveyState == 3 && menuCode == 'report-list'}"> <!-- 审核中 且 是报告审核菜单-->
            <%--<c:if test="${dto.isSun == 0}">--%>
                <%--<button class="butList active" onclick="operate(${dto.id},'sign',true)">标记阳性</button>--%>
            <%--</c:if>--%>
            <%--<c:if test="${dto.isSun == 1}">--%>
                <%--<button class="butList active" onclick="operate(${dto.id},'resign',true)">取消阳性</button>--%>
            <%--</c:if>--%>
            <%--<c:if test="${dto.surveyUserType == 1}">--%>
                <%--<c:if test="${dto.surveyRiskCaseInfo.isClassic == 0}">--%>
                    <%--<button class="butList active" onclick="operate(${dto.id},'classic',true)">标记经典案例</button>--%>
                <%--</c:if>--%>
                <%--<c:if test="${dto.surveyRiskCaseInfo.isClassic == 1}">--%>
                    <%--<button class="butList active" onclick="operate(${dto.id},'reclassic',true)">取消经典案例</button>--%>
                <%--</c:if>--%>
            <%--</c:if>--%>
            <%--<c:if test="${dto.scoreState == 0}"><button class="butList active" onclick="operate(${dto.id},'score',false)">评分</button></c:if>--%>
            <%--<button class="butList active" onclick="operate(${dto.id},'punish',false)">处罚</button>--%>
            <button class="butList active" onclick="operate(${dto.id},'pass1',true)">审核通过</button>
            <button class="butList active" onclick="operate(${dto.id},'back',false)">退回</button>
            <button class="butList active" onclick="operate(${dto.id},'direction',false)">增加调查方向</button>
        </c:if>
        <c:if test="${dto.surveyState == 6 && menuCode == 'report-review-list'}">
            <button class="butList active" onclick="operate(${dto.id},'pass1',true)">审核通过</button>
            <button class="butList active" onclick="operate(${dto.id},'back1',false)">退回</button>
            <button class="butList active" onclick="operate(${dto.id},'direction',false)">增加调查方向</button>
        </c:if>
    </div>
    <div class="main-boy">
        <div>
            <div class="table-title">基础信息</div>
            <form id="editForm" role="form" action="${ctx}/survey/case/sic/operate" method="post">
                <input type="hidden" value="${dto.id}" name="id" />
                <input type="hidden" value="" name="btnCode" id="btnCode" />
                <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>调查编号</td>
                    <td>${dto.surveyRiskCase.surveyNo}</td>
                    <td>被调查人</td>
                    <td>${dto.surveyRiskCase.surveyPerson}</td>
                    <td>联系号码</td>
                    <td>${dto.surveyRiskCase.surveryPersonTel}</td>
                </tr>
                <tr>
                    <td>领域</td>
                    <td>${dto.surveyRiskCaseInfo.surveyBusName}</td>
                    <td>任务类型</td>
                    <td colspan="3">
                        <c:forEach items="${dto.tasks}" var="item">
                            ${item.taskName}&nbsp;
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td>业务类型</td>
                    <td>${dto.surveyRiskCaseInfo.servicesName}</td>
                    <td>截至日期</td>
                    <td><fmt:formatDate value="${dto.surveyEndTime}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>状态</td>
                    <td><span style="color: red;">
                        ${dto.surveyStateName}
                    </td>
                </tr>
                <tr>
                    <td>委托人名称</td>
                    <td>${dto.surveyRiskCase.entrustUserName}</td>
                    <td>委托人机构名称</td>
                    <td colspan="3">${dto.surveyRiskCase.entrustOrgName}</td>
                </tr>
                <tr>
                    <td>保险合同编号</td>
                    <td>${dto.surveyRiskCase.policyNo}</td>
                    <td>理赔编号</td>
                    <td>${dto.surveyRiskCase.claimsNo}</td>
                    <td>理赔申请金额</td>
                    <td>${dto.surveyRiskCase.claimsMoney}</td>
                </tr>
                <tr>
                    <td>证件类型</td>
                    <td>
                        <c:if test="${dto.surveyRiskCase.idType ==1}">身份证</c:if>
                        <c:if test="${dto.surveyRiskCase.idType ==2}">驾驶证</c:if>
                    </td>
                    <td>证件号码</td>
                    <td colspan="3">${dto.surveyRiskCase.idNumber}</td>
                </tr>
                <tr>
                    <td>是否经典案例</td>
                    <td>
                        <c:if test="${dto.surveyRiskCaseInfo.isClassic == 0}">否</c:if>
                        <c:if test="${dto.surveyRiskCaseInfo.isClassic == 1}">是</c:if>
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <c:if test="${menuCode == 'dcy-list' || menuCode == 'report-list'}">
                    <tr>
                        <td>任务描述</td>
                        <td colspan="6">${dto.surveyTaskRemark}</td>
                    </tr>
                </c:if>
                <tr>
                    <td>调查基本信息</td>
                    <td colspan="5">${dto.surveyRiskCaseInfo.surveyInfo}</td>
                </tr>
                <tr>
                    <td>调查事项</td>
                    <td colspan="5">${dto.surveyRiskCaseInfo.surveyItem}</td>
                </tr>
                <tr>
                    <td>审核原因</td>
                    <td colspan="5">
                        ${dto.surveyRemark}
                    </td>
                </tr>
                <c:if test="${false}">
                    <tr>
                        <td>结算方式</td>
                        <td>
                            <c:if test="${dto.surveyRiskCaseInfo.payType == 1}">一口价</c:if>
                            <c:if test="${dto.surveyRiskCaseInfo.payType == 2}">基本费+减损</c:if>
                            <c:if test="${dto.surveyRiskCaseInfo.payType == 3}">任务</c:if>
                            <c:if test="${dto.surveyRiskCaseInfo.payType == 4}">任务+减损</c:if>
                        </td>
                        <td>结算详情</td>
                        <td>
                            <c:if test="${dto.surveyRiskCaseInfo.payType == 1}">(${dto.surveyTaskMoney})元</c:if>
                            <c:if test="${dto.surveyRiskCaseInfo.payType == 2}">基本费(${dto.surveyTaskMoney})，减损描述(${dto.surveryReLoossesRemark})</c:if>
                            <c:if test="${dto.surveyRiskCaseInfo.payType == 3}">任务(${dto.surveyCaseDirections.size()})个</c:if>
                            <c:if test="${dto.surveyRiskCaseInfo.payType == 4}">任务(${dto.surveyCaseDirections.size()})个，减损描述(${dto.surveryReLoossesRemark})</c:if>
                        </td>
                        <td>是否阳性</td>
                        <td>
                            <c:if test="${dto.isSun == 0}">非阳性</c:if>
                            <c:if test="${dto.isSun == 1}"><span style="color: red;">阳性</span></c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>调查员</td>
                        <td>${dto.surveyUserName}</td>
                        <td>价格</td>
                        <td>
                            <div id="div_price1">${dto.surveyTaskMoney}
                                    <%--<a onclick="cliPrice()"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a>--%>
                            </div>
                            <div id="div_price2" style="display: none">
                                <input type="number" class="form-control" style="display: inline-flex;width: 60%" step="0.00" value="${dto.surveyTaskMoney}" name="surveyTaskMoney" />
                                <input type="submit" value="确定" onclick="oprOK(${dto.id},'price')" class="btn" style="display: inline-flex" />
                            </div>
                        </td>
                        <td>减损奖励</td>
                        <td>
                            <div id="div_losses1">${dto.surveryReLosses}
                                    <%--<a onclick="cliLosses()"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a>--%>
                            </div>
                            <div id="div_losses2" style="display: none;">
                                <input type="number" class="form-control" style="display: inline-flex;width: 60%" step="0.00" value="${dto.surveryReLosses}" name="surveryReLosses" />
                                <input type="submit" value="确定" onclick="oprOK(${dto.id},'losses')" class="btn" style="display: inline-flex" />
                            </div>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td>
                        调查报告
                    </td>
                    <td><a onclick="onlinePreview('${dto.commonFile.filePath}')" target="_blank">
                        ${dto.creportName == null ? "报告" : dto.creportName}
                    </a></td>
                    <td>评分状态</td>
                    <td>
                        <c:if test="${dto.scoreState == 0}">未评分</c:if>
                        <c:if test="${dto.scoreState == 1}">已评分</c:if>
                    </td>
                    <td>评分等级</td>
                    <td>
                        <c:if test="${dto.scoreLevel == 1}">优质</c:if>
                        <c:if test="${dto.scoreLevel == 2}">合格</c:if>
                        <c:if test="${dto.scoreLevel == 3}">不合格</c:if>
                    </td>
                </tr>
                </tbody>
            </table>
            </form>
            <div class="table-title">调查方向列表</div>
            <!-- 模拟一个更改方向价格的form -->
            <form id="editFormDirection" role="form" action="${ctx}/survey/case/sic/operate" method="post">
                <input type="hidden" value="${dto.id}" name="id" />
                <input type="hidden" value="updPricedirection" name="btnCode" />
                <input type="hidden" value="" name="directionId" id="directionId" />
                <table class="table" border="1" cellpadding="0" cellspacing="0" style="text-align: left">
                    <thead>
                    <th>区域</th>
                    <th>任务类型</th>
                    <th>任务子类</th>
                    <th>方向名称</th>
                    <th>方向内容</th>
                    <th>附件</th>
                    <%--<th width="200">价格</th>--%>
                    <th>操作</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${dto.surveyCaseDirections}" var="item">
                        <tr>
                            <td>${item.areaName}</td>
                            <td>${item.taskName}</td>
                            <td>${item.newName}</td>
                            <td>${item.directionName}</td>
                            <td>${item.directionText}</td>
                            <td><a href="javascript:void(0);" onclick="directionFiles(${item.id},'directionFiles')">详情</a></td>
                        <%--<td>--%>
                                <%--<div id="div_survey_money1${item.id}">${item.surveyMoney}<a onclick="updSurveyMoney(${item.id})">--%>
                                        <%--<img height="25px" width="25px" src="${ctx}/img/pen.png"></a>--%>
                                <%--</div>--%>
                                <%--<div id="div_survey_money2${item.id}" style="display: none;">--%>
                                    <%--<input type="number" class="form-control" style="display: inline-flex;width: 60%" step="0.00" value="${item.surveyMoney}" name="surveyMoney${item.id}" />--%>
                                    <%--<input type="submit" value="确定" onclick="return directionOK(${item.id})" class="btn" style="display: inline-flex" />--%>
                                <%--</div>--%>
                            <%--</td>--%>
                            <td>
                                <c:if test="${dto.surveyState == 3 && menuCode == 'report-list'}">
                                    <a href="javascript:void(0);" onclick="delDirection(${dto.id},${item.id},'deldirection',true)">删除</a>
                                    <a href="javascript:void(0);" onclick="updDirection(${dto.id},${item.id},'updDirection',false)">修改</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
            <div class="table-title">跟踪信息</div>
            <c:if test="${follows != null}">
                <c:forEach items="${follows}" var="item">
                    <div class="stepItem" style="margin-left: 10px;margin-top: 20px">
                        <div class="stepDate"><fmt:formatDate value="${item.followTime}" pattern="yyyy-MM-dd HH:mm"/></div>
                        <div class="stepDot"><div class="stepDot-dot finished"></div></div>
                        <div class="stepDetail"><div class="stepDetail-title">跟踪人：${item.followUserName}
                            <br>内容：${item.contents}<br>
                            <c:if test="${item.nextFollowTime != null}">
                                下一次跟踪时间：<fmt:formatDate value="${item.nextFollowTime}" pattern="yyyy-MM-dd HH:mm"/><br/>
                            </c:if>
                            附件
                            <c:forEach items="${item.surveyFollowFiles}" var="f">
                                <a href="${f.filePath}" target="_blank">${f.fileName}</a>
                            </c:forEach>
                        </div><div class="stepDetail-detail"></div></div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${follows.size() == 0}">
                <div class="stepItem" style="margin-top: 10px;text-align:center;color: #ff0000;">
                    暂无跟踪记录
                </div>
            </c:if>
        </div>

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
    $("#editFormDirection").bind('submit', function(event) {
        ajaxFormSubmit(this,reload,null,null,reload);
        event.preventDefault();
    });
    var delDirection = function(id,directionId,btnCode,ajax){
        var url = "${ctx}/survey/case/sic/operate",param = {"id":id,"directionId" : directionId,"btnCode":btnCode};
        if(confirm('是否确认？')){
            ajaxSubmit(url,param,function(v,e,p){
                alert(e.data.msg);
                location.reload();
            })
        }
    }
    var updDirection = function(id,directionId,btnCode,ajax){
        var height = 400,width = 900;
        title = "操作";
        url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=direction&opr=upd&directionId=" + directionId;
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:url
        });
    }
    var operate = function(id,btnCode,ajax){
        var height = 400,width = 900;
        if(btnCode == 'commit'){
            if("${dto.surveyRiskCaseInfo.reportState}" == 0){
                alert("主报告未上传");return;
            }
        }
        if(ajax){
            var url = "${ctx}/survey/case/sic/operate",param = {"id":id,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    alert(e.data.msg);
                    location.reload();
                })
            }
        }else{
            var title = null,url = null;
            if(btnCode == 'follow'){
                title = "添加跟踪";
                url = "${ctx}/survey/case/addFollow?id=" + id + "&btnCode=" + btnCode + "&surveyCno=${dto.surveyRiskCaseInfo.surveyCno}";
            }else if(btnCode == 'files'){
                title = "查看资料";
                url = "${ctx}/survey/case/fileMidView?surveyInfoId=" + id + "&surveyId=${dto.surveyRiskCaseInfo.surveyId}&surveyCno=${dto.surveyRiskCaseInfo.surveyCno}";
            }else if(btnCode == 'details'){
                height = 600,width=900;
                title = "任务详情"
                url = "${ctx}/survey/case/sic/details?id=" + id + "&btnCode=" + btnCode;
            }else if(btnCode == 'refuse' || btnCode == 'back' || btnCode == 'back1'){
                height = 200;width = 700;
                title = '拒绝原因';
                url = "${ctx}/survey/case/sic/back?id=" + id + "&btnCode=" + btnCode;
            }else{
                //上传报告 更新报告 增加方向 等操作
                title = "操作";
                url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode;
                if(btnCode == 'upload' || btnCode == 'primary'){
                    url += "&surveyCno=${dto.surveyRiskCaseInfo.surveyCno}"
                }
            }
            openDialog({
                frame:true,
                title:title,
                height:height,
                width:width,
                url:url
            });
        }
    }

    var cliPrice = function(){
        $("#div_price1").hide();
        $("#div_price2").show();
    }
    var cliLosses = function(){
        $("#div_losses1").hide();
        $("#div_losses2").show();
    }

    var oprOK = function(id,btnCode){
        $("#btnCode").val(btnCode);
    }

    var updSurveyMoney = function(directionId){
        $("#div_survey_money1" + directionId).hide();
        $("#div_survey_money2" + directionId).show();
    }

    var directionOK = function(directionId){
        $("#directionId").val(directionId);
        return true;
    }

    $("#editForm").bind('submit', function(event) {
        $(this).find(":submit").attr("disabled","true");
        ajaxFormSubmit(this,reload,null,null,reload);
    });

    var directionFiles = function(id,btnCode){
        var height = 500,width = 800;
        title = "附件详情";
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:"${ctx}/survey/case/sic/directionFiles?directionId=" + id
        });
    }
</script>
</body>
</html>
