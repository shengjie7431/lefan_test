<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>详情</title>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
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
        <div class="title">
            <input type="hidden" id="menuType" value="${menuType}" />
            <button class="butList active" onclick="operate(${dto.id},'1204',false)">添加跟踪</button>
            <button class="butList active" onclick="operate(${dto.id},'1205',false)">查看资料</button>
            <button class="butList active" onclick="operate(${dto.id},'1999',false)">查看进度</button>
            <!-- 客服中心 司法评估列表 -->
            <c:if test="${menuType == 1}">
                <%--<c:if test="${dto.isCursupplement == 1}">--%>
                <%--<button class="butList active" onclick="operate(${dto.id},'1100',true)">补充信息</button>--%>
                <%--</c:if>--%>
                <!-- 综合内勤 快递方案及请款函 -->
                <c:if test="${dto.isComplex}">
                    <c:if test="${dto.isUploadAssessPlan == 1 && (dto.isUploadReqmoneyLetter == 1 || dto.isUploadReqmoneyLetter == 4) && dto.isExpress == 0}">
                        <button class="butList active" onclick="operate(${dto.id},'1502',true)">已快递</button>
                    </c:if>
                    <c:if test="${(dto.isExpress == 1 && dto.isArrAssFee == 0 && dto.isUploadReqmoneyLetter == 1 && dto.payType == 1) || (dto.payType == 2 && dto.isUploadReqmoneyLetter == 1 && dto.flowState == 14)}">
                        <button class="butList active" onclick="operate(${dto.id},'1503',false)">催收</button>
                    </c:if>
                    <c:if test="${dto.flowState == 12}">
                        <c:if test="${dto.isSue != 1}">
                            <button class="butList active" onclick="operate(${dto.id},'1700',true)">签发报告</button>
                        </c:if>
                        <c:if test="${dto.isSue == 1}">
                            <button class="butList default" onclick="operate(${dto.id},'1701',true)">报告已签发</button>
                        </c:if>
                        <%--<button class="butList default" onclick="operate(${dto.id},'',false)">打印报告</button>--%>
                    </c:if>
                </c:if>
            </c:if>
            <c:if test="${menuType == 35}">
                <c:if test="${dto.flowState == 1}">
                    <button class="butList active" onclick="operate(${dto.id},'1000',true)">提交审核</button>
                </c:if>
            </c:if>
            <!-- 委托案件列表-->
            <c:if test="${menuType == 5}">
                <c:if test="${dto.flowState == 2}">
                    <button class="butList active" onclick="operate(${dto.id},'1200',true)">审核通过</button>
                    <button class="butList active" onclick="operate(${dto.id},'1201',false)">退回</button>
                    <c:if test="${dto.isCursupplement == 0}">
                        <button class="butList active" onclick="operate(${dto.id},'1203',true)">发送客服补充信息</button>
                    </c:if>
                </c:if>
            </c:if>
            <!-- 案件分派 -->
            <c:if test="${menuType == 10}">
                <button class="butList active" onclick="operate(${dto.id},'1300',false)">分派评估师</button>
            </c:if>
            <!-- 待接收案件列表-->
            <c:if test="${menuType == 15}">
                <c:if test="${dto.flowState == 5}">
                    <button class="butList active" onclick="operate(${dto.id},'1400',true)">接收</button>
                    <button class="butList active" onclick="operate(${dto.id},'1401',false)">拒绝</button>
                </c:if>
            </c:if>


            <c:if test="${menuType == 30 && dto.isComplex}">
                <c:if test="${(dto.retreatState == 0 || dto.retreatState == 3) && dto.stageState != 3 && dto.stageState != 4}">
                    <button class="butList active" onclick="operate(${dto.id},'1900',false)">分配机构</button>
                </c:if>
            </c:if>
            <c:if test="${menuType == 30 && dto.isAssess}">
                <c:if test="${(dto.retreatState == 0 || dto.retreatState == 3) && dto.flowState > 2 && dto.flowState <14}">
                    <button class="butList active" onclick="operate(${dto.id},'1800',false)">发起退案</button>
                </c:if>
                <c:if test="${dto.stageState == 4}">
                    <%--<button class="butList active" onclick="operate(${dto.id},'1508',false)">退费</button>--%>
                </c:if>
            </c:if>

            <%-- 评估计划 --%>
            <c:if test="${menuType == 18}">
                  <c:if test="${dto.isAssess}">
                      <c:if test="${dto.isUploadAssessPlan == 0}">
                          <button class="butList active" onclick="operate(${dto.id},'1500',false)">上传评估方案</button>
                      </c:if>
                      <c:if test="${dto.isUploadReqmoneyLetter == 0}">
                          <button class="butList active" onclick="operate(${dto.id},'1501',false)">上传请款函</button>
                      </c:if>
                      <c:if test="${dto.flowState == 6 && dto.isUploadAssessPlan == 1 && dto.isUploadReqmoneyLetter != 0}">
                          <!-- 计划完成，提交 -->
                          <button class="butList active" onclick="operate(${dto.id},'1505',true)">计划完成</button>
                      </c:if>
                  </c:if>
            </c:if>

            <!-- 评估处理-->
            <c:if test="${menuType == 20}">
                <!-- 评估师 -->
                <c:if test="${dto.isAssess}">
                    <c:if test="${dto.flowState == 8}">
                        <button class="butList active" onclick="operate(${dto.id},'1504',false)">制作报告</button>
                        <button class="butList active" onclick="operate(${dto.id},'1555',true)">提交审核</button>
                        <%--<c:if test="${dto.isReport == 0}">--%>
                            <%--<button class="butList active" onclick="operate(${dto.id},'1504',false)">制作报告</button>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${dto.isReport == 1}">--%>
                            <%--<button class="butList active" onclick="operate(${dto.id},'1555',true)">提交审核</button>--%>
                        <%--</c:if>--%>
                    </c:if>

                    <c:if test="${dto.flowState == 11}">
                        <%--<button class="butList active" onclick="operate(${dto.id},'1506',false)">确认案件信息</button>--%>
                    </c:if>
                    <%--<c:if test="${dto.flowState == 12 && dto.isSue == 1 && dto.isBill == 1}">--%>
                    <c:if test="${dto.flowState == 12}">
                        <button class="butList active" onclick="operate(${dto.id},'1507',true)">发起结案</button>
                        <c:if test="${dto.isBill == 0 && dto.payType == 1}">
                            <button class="butList active" onclick="operate(${dto.id},'1520',true)">申请开票</button>
                        </c:if>
                    </c:if>
                </c:if>

            </c:if>

            <!-- 评估审核  评估主管  评估经理-->
            <c:if test="${menuType == 25}">
                <c:if test="${dto.retreatState != 1}">
                    <c:if test="${dto.flowState == 9 && dto.isAssessSuper}"> <!-- 一审 -->
                        <button class="butList active" onclick="operate(${dto.id},'1600',true)">审核通过</button>
                        <button class="butList active" onclick="operate(${dto.id},'1601',false)">退回</button>
                    </c:if>
                    <c:if test="${dto.flowState == 10 && dto.isAssessManager}"> <!-- 二审 -->
                        <button class="butList active" onclick="operate(${dto.id},'1602',true)">审核通过</button>
                        <button class="butList active" onclick="operate(${dto.id},'1603',false)">退回</button>
                    </c:if>
                    <c:if test="${dto.flowState == 13 && dto.isAssessSuper}"> <!-- 结案审核 -->
                        <button class="butList active" onclick="operate(${dto.id},'1604',true)">审核通过</button>
                        <button class="butList active" onclick="operate(${dto.id},'1605',false)">退回</button>
                    </c:if>
                </c:if>
                <c:if test="${dto.retreatState == 1}"> <!-- 退案审核 -->
                    <button class="butList active" onclick="operate(${dto.id},'1801',true)">审核通过</button>
                    <button class="butList active" onclick="operate(${dto.id},'1802',false)">退回</button>
                </c:if>
            </c:if>

        </div>
        <div class="main-boy">
            <div>
                <c:if test="${dto.flowState == 6 && dto.isOkInfo == 0 && menuType == 18 && dto.isAssess}">
                    <div class="table-title">评估信息确认</div>
                    <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                        <tbody>
                        <form id="assessForm" name="assessForm" role="form" action="${ctx}/law/operate" target="_blank" method="post">
                            <tr>
                                <td >评估费</td>
                                <td><input type="number" id="checkAssessFee" name="checkAssessFee" onkeyup="reckonFinalFee()" value="${dto.assessFee}" required="required" class="form-control"/></td>
                                <td >差旅费</td>
                                <td><input type="number" id="travelFee" name="travelFee" onkeyup="reckonFinalFee()" class="form-control"/></td>
                                <td >合计评估费</td>
                                <td><input type="number" id="finalAssessFee" name="finalAssessFee" value="${dto.assessFee}" required="required"  class="form-control"/></td>
                            </tr>
                            <tr>
                                <td>付费方式</td>
                                <td>
                                    <select name="payType" class="form-control" required="required" onchange="payTypeChange(this)">
                                        <option value="">请选择</option>
                                        <option value="1">预付费</option>
                                        <option value="2">后付费</option>
                                    </select>
                                </td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr id="tr_address_one" style="display: none">
                                <td >开票对象</td>
                                <td>
                                    <input type="text" id="toOrg" name="toOrg"  class="form-control" style="display: inline-block; width: 70%"/>
                                    <input type="hidden" id="companyId" name="companyId">
                                    <input type="button" value="选  择" onclick="selectCompanyName()"/>
                                </td>
                                <td >收件人姓名</td>
                                <td>
                                    <input type="text" id="toName" name="toName" class="form-control" style="display: inline-block; width: 70%"/>
                                    <input type="button" value="选  择" onclick="selectRecipient()"/>
                                </td>
                                <td >收件人电话</td>
                                <td><input type="text" id="toTel" name="toTel"  class="form-control"/></td>

                            </tr>
                            <tr id="tr_address_two" style="display: none">
                                <td >收件人地址</td>
                                <td colspan="5">

                                    <select id = "toProvinceId" name="toProvinceId" style="width: 130px; display: inline-block;" onchange="selectArea()" class="form-control" required="required">
                                        <option value="">--请选择--</option>
                                        <c:forEach items="${apiRspArea.results}" var="area">
                                            <option value="${area.areaId}">${area.areaName}</option>
                                        </c:forEach>
                                    </select>
                                    &nbsp;省&nbsp;&nbsp;
                                    <div id="div_city" hidden="hidden"></div>
                                    <div id="div_district" hidden="hidden"></div>

                                    <select id = "toCityId" name="toCityId" style="width: 130px; display: inline-block;" onclick="selectAreaCity()" class="form-control" required="required">
                                        <option value="">--</option>
                                    </select>
                                    &nbsp;市&nbsp;&nbsp;
                                    <select id = "toDistrictId" name="toDistrictId" style="width: 130px; display: inline-block;" class="form-control" required="required">
                                        <option value="">--</option>
                                    </select>
                                    &nbsp;区&nbsp;&nbsp;
                                    <input type="text" id="toAddress" name="toAddress" class="form-control" style="display: inline-block;width: 47%"/>

                                    <input type="hidden" id="toProvince" name="toProvince" value="">
                                    <input type="hidden" id="toCity" name="toCity" value="">
                                    <input type="hidden" id="toDistrict" name="toDistrict" value="">
                                    <input type="hidden" id="shenId">
                                    <input type="hidden" id="shiId">
                                    <input type="hidden" id="quId">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" style="text-align: right;background-color: #ffffff">
                                    <input type="hidden" name="btnCode" value="1515" />
                                    <input type="hidden" name="id" value="${dto.id}" />
                                    <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>确认</button>
                                </td>
                            </tr>
                        </form>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${dto.flowState == 8 && dto.isOkInfo2 == 0 && menuType == 20 && dto.isAssess}">
                    <div class="table-title">信息确认</div>
                    <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                        <tbody>
                        <form id="infoForm" name="infoForm" role="form" action="${ctx}/law/operate" target="_blank" method="post">
                            <tr>
                                <td >最终评估金额</td>
                                <td><input type="number" id="finalAssessAmount" name="finalAssessAmount" value="${dto.targetAmount}" required="required"  class="form-control"/></td>
                                <td >评估说明</td>
                                <td colspan="3">
                                <textarea id="assessRemark" name="assessRemark" class="form-control"></textarea>
                                </td>
                            </tr>
                            <c:if test="${dto.payType == 1}">
                                <tr>
                                    <td >开票对象</td>
                                    <td>
                                        <input type="text" id="toOrg" name="toOrg"  class="form-control" style="display: inline-block; width: 70%"/>
                                        <input type="button" value="选  择" onclick="selectCompanyName()"/>
                                    </td>
                                    <td >收件人姓名</td>
                                    <td>
                                        <input type="text" id="toName" name="toName" class="form-control" style="display: inline-block; width: 70%"/>
                                        <input type="button" value="选  择" onclick="selectRecipient()"/>
                                    </td>
                                    <td >收件人电话</td>
                                    <td><input type="text" id="toTel" name="toTel"  class="form-control"/></td>
                                </tr>
                                <tr>
                                    <td >收件人地址</td>
                                    <td colspan="5">

                                        <select id = "toProvinceId" name="toProvinceId" style="width: 130px; display: inline-block;" onchange="selectArea()" class="form-control" required="required">
                                            <option value="">--请选择--</option>
                                            <c:forEach items="${apiRspArea.results}" var="area">
                                                <option value="${area.areaId}">${area.areaName}</option>
                                            </c:forEach>
                                        </select>
                                        &nbsp;省&nbsp;&nbsp;
                                        <div id="div_city" hidden="hidden"></div>
                                        <div id="div_district" hidden="hidden"></div>

                                        <select id = "toCityId" name="toCityId" style="width: 130px; display: inline-block;" onclick="selectAreaCity()" class="form-control" required="required">
                                            <option value="">--</option>
                                        </select>
                                        &nbsp;市&nbsp;&nbsp;
                                        <select id = "toDistrictId" name="toDistrictId" style="width: 130px; display: inline-block;" class="form-control" required="required">
                                            <option value="">--</option>
                                        </select>
                                        &nbsp;区&nbsp;&nbsp;
                                        <input type="text" id="toAddress" name="toAddress" class="form-control" style="display: inline-block;width: 47%"/>

                                        <input type="hidden" id="toProvince" name="toProvince" value="">
                                        <input type="hidden" id="toCity" name="toCity" value="">
                                        <input type="hidden" id="toDistrict" name="toDistrict" value="">
                                        <input type="hidden" id="shenId">
                                        <input type="hidden" id="shiId">
                                        <input type="hidden" id="quId">
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>补退标记</td>
                                <td>
                                    <select id="appraiseType" name="appraiseType" onchange="appraiseTypeChange(this)" required="required" class="form-control">
                                        <option value="" selected>请选择</option>
                                        <option value="1">正常</option>
                                        <option value="2">补费</option>
                                        <option value="3">退费</option>
                                    </select>
                                </td>
                                <td><span id="span_name">补退金额</span></td>
                                <td><input id="input_amount" type="number" name="amount" class="form-control"></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>补退说明</td>
                                <td colspan="5">
                                    <textarea id="refoundRemark" name="refoundRemark" class="form-control"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" style="text-align: right;background-color: #ffffff">
                                    <input type="hidden" name="btnCode" value="1556" />
                                    <input type="hidden" name="id" value="${dto.id}" />
                                    <button type="submit" onclick="goBack()" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>确认</button>
                                </td>
                            </tr>
                        </form>
                        </tbody>
                    </table>
                </c:if>
                <div class="table-title">基础信息</div>
                <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                    <tbody>
                    <tr>
                        <td>案件编号</td>
                        <td>${dto.caseNo}</td>
                        <td>公估编号</td>
                        <td>${dto.pubCaseNo}</td>
                        <td>机构</td>
                        <td>${dto.orgName}</td>
                    </tr>
                    <tr>
                        <td>案件状态</td>
                        <td>
                            <span style="color: red;">
                                <%--${dto.flowStateName}--%>
                                <c:if test="${dto.retreatState == 0 || dto.retreatState == 3}">
                                    ${dto.flowStateName}
                                </c:if>
                                <c:if test="${dto.retreatState == 1}">
                                    退案审核中
                                </c:if>
                                <c:if test="${dto.retreatState == 2}">
                                    已退案
                                </c:if>
                            </span>
                        </td>
                        <td>案件阶段</td>
                        <td>
                            <c:if test="${dto.stageState == 1}">委托阶段</c:if>
                            <c:if test="${dto.stageState == 2}">评估阶段</c:if>
                            <c:if test="${dto.stageState == 3}">结案</c:if>
                            <c:if test="${dto.stageState == 4}">退案</c:if>
                        </td>
                        <td>委托事项</td>
                        <td>
                            <c:if test="${dto.caseType == 1}">财产险评估</c:if>
                            <c:if test="${dto.caseType == 2}">医疗费用评估</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>标的金额</td>
                        <td>${dto.targetAmount}</td>
                        <td>评估费用</td>
                        <td>${dto.assessFee}</td>
                        <td>标的地址</td>
                        <td>${dto.targetProvince}${dto.targetCity}${dto.targetDistrict}${dto.targetAddress}</td>
                    </tr>
                    <tr>
                        <td>委托人姓名</td>
                        <td>${dto.entrustUserName}</td>
                        <td>委托人电话</td>
                        <td>${dto.entrustUserTel}</td>
                        <td>委托时间</td>
                        <td><fmt:formatDate value="${dto.entrustTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    </tr>
                    <tr>
                        <td>评估师姓名</td>
                        <td>${dto.assessName}</td>
                        <td>评估师电话</td>
                        <td>${dto.assessTel}</td>
                        <td>评估师接收时间</td>
                        <td><fmt:formatDate value="${dto.acceptTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    </tr>
                    <tr>
                        <td>联系人姓名</td>
                        <td>${dto.linkName}</td>
                        <td>联系人电话</td>
                        <td>${dto.linkTel}</td>
                        <td>创建时间</td>
                        <td><fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    </tr>
                    <tr>
                        <td>申请人姓名</td>
                        <td>${dto.appUserName}</td>
                        <td>申请人电话</td>
                        <td>${dto.appUserTel}</td>
                        <td>更新时间</td>
                        <td><fmt:formatDate value="${dto.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    </tr>
                    <tr>
                        <td>创建人</td>
                        <td>${dto.createByName}</td>
                        <td>委托需求描述</td>
                        <td colspan="3">${dto.entrustDesc}</td>
                    </tr>
                    </tbody>
                </table>


                <div class="table-title">附属信息</div>
                <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                    <tbody>

                    <%--<c:if test="${dto.flowState != 6 && dto.isExpress == 1 || menuType != 20}">--%>
                    <c:if test="${!(dto.flowState == 6 && dto.isOkInfo == 0 && menuType == 18 && dto.isAssess)}">
                        <tr>
                            <td>确认评估费用</td>
                            <td>${dto.checkAssessFee}</td>
                            <td>差旅费</td>
                            <td>${dto.travelFee}</td>
                            <td>最终评估费用</td>
                            <td>${dto.finalAssessFee}</td>
                        </tr>
                        <tr>
                            <td>付费方式</td>
                            <td>
                                <c:if test="${dto.payType == 1}">预付费</c:if>
                                <c:if test="${dto.payType == 2}">后付费</c:if>
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td >收件人姓名</td>
                            <td>${dto.toName}</td>
                            <td >收件人电话</td>
                            <td>${dto.toTel}</td>
                            <td >开票对象</td>
                            <td>${dto.toOrg}</td>
                        </tr>
                        <tr>
                            <td >收件人地址</td>
                            <td colspan="5">${dto.toProvince}${dto.toCity}${dto.toDistrict}${dto.toAddress}</td>
                        </tr>
                        <tr>
                            <td>最终评估金额</td>
                            <td>${dto.finalAssessAmount}</td>
                            <td width="20%">评估说明</td>
                            <td width="80%" colspan="3">${dto.assessRemark}</td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>评估方案是否上传</td>
                        <td>
                            <c:if test="${dto.isUploadAssessPlan == 0}">否</c:if>
                            <c:if test="${dto.isUploadAssessPlan == 1}">是</c:if>
                        </td>
                        <td>请款函状态</td>
                        <td>
                            <c:if test="${dto.isUploadReqmoneyLetter == 0}">未上传</c:if>
                            <c:if test="${dto.isUploadReqmoneyLetter == 1}">已上传</c:if>
                            <c:if test="${dto.isUploadReqmoneyLetter == 2}">催收中</c:if>
                            <c:if test="${dto.isUploadReqmoneyLetter == 3}">超期未支付</c:if>
                            <c:if test="${dto.isUploadReqmoneyLetter == 4}">已支付</c:if>
                        </td>
                        <td>是否快递</td>
                        <td>
                            <c:if test="${dto.isExpress == 0}">否</c:if>
                            <c:if test="${dto.isExpress == 1}">是</c:if>
                        </td>

                    </tr>
                    <tr>
                        <td>评估费是否到账</td>
                        <td>
                            <c:if test="${dto.isArrAssFee == 0}">否</c:if>
                            <c:if test="${dto.isArrAssFee == 1}">是</c:if>
                        </td>
                        <td>评估费用到账时间</td>
                        <td><fmt:formatDate value="${dto.assessRecTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td>快递时间</td>
                        <td><fmt:formatDate value="${dto.expressTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    </tr>
                    <tr>
                        <td>开票状态</td>
                        <td>
                            <c:if test="${dto.isBill == 0}">
                                未申请
                            </c:if>
                            <c:if test="${dto.isBill == 1}">
                                申请中
                            </c:if>
                            <c:if test="${dto.isBill == 2}">
                                已开票
                            </c:if>
                        </td>
                        <td>是否发送报告</td>
                        <td>
                            <c:if test="${dto.isSendReport == 0}">否</c:if>
                            <c:if test="${dto.isSendReport == 1}">是</c:if>
                        </td>
                        <td>发送报告时间</td>
                        <td><fmt:formatDate value="${dto.sendReportTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    </tr>
                    <tr>
                        <td>补退标记</td>
                        <td>
                            <c:if test="${dto.refundType == 1}">
                                正常
                            </c:if>
                            <c:if test="${dto.refundType == 2}">
                                补费
                            </c:if>
                            <c:if test="${dto.refundType == 3}">
                                退费
                            </c:if>
                        </td>
                        <td>补退金额</td>
                        <td>${dto.refundFee}</td>
                        <td>退案状态</td>
                        <td>
                            <c:if test="${dto.retreatState == 1}">
                                退案审核中
                            </c:if>
                            <c:if test="${dto.retreatState == 2}">
                                审核通过
                            </c:if>
                            <c:if test="${dto.retreatState == 3}">
                                审核不通过
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>补退说明</td>
                        <td colspan="5">${dto.refoundRemark}</td>
                    </tr>
                    <tr>
                        <td>备注说明</td>
                        <td colspan="5">${dto.remark}</td>
                    </tr>
                    <tr>
                        <td>审核意见</td>
                        <td colspan="5">${dto.oneCheckOpinionRemark}</td>
                    </tr>
                    <tr>
                        <td>催收备注</td>
                        <td colspan="5">${dto.collectionRemarks}</td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <c:if test="${caseFollows != null}">
                <c:forEach items="${caseFollows}" var="item">
                    <div class="stepItem" style="margin-left: 10px;margin-top: 20px">
                        <div class="stepDate"><fmt:formatDate value="${item.followTime}" pattern="yyyy-MM-dd HH:mm"/></div>
                        <div class="stepDot"><div class="stepDot-dot finished"></div></div>
                        <div class="stepDetail"><div class="stepDetail-title">跟踪人：${item.followBy}
                            <br>内容：${item.followDesc}<br>
                            <c:if test="${item.nextFollowTime != null}">
                                下一次跟踪时间：<fmt:formatDate value="${item.nextFollowTime}" pattern="yyyy-MM-dd HH:mm"/>
                            </c:if>
                        </div><div class="stepDetail-detail"></div></div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${caseFollows.size() == 0}">
                <div class="stepItem" style="margin-top: 10px;text-align:center;color: #ff0000;">
                    暂无跟踪记录
                </div>
            </c:if>


            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            </div>
        </div>
    </div>

</body>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    $("#assessForm").bind('submit', function(event) {
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
    $("#infoForm").bind('submit', function(event) {
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
    /**
     *
     * @param id        案件中心ID
     * @param btnCode    按钮CODE,与list_state一一对应
     * @param ajax       是否ajax请求,  true 弹出提示框   false 弹出界面
     */
    function operate(id,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/law/operate",param = {"id":id,"btnCode":btnCode};
            if(btnCode == "1515"){
//                var checkAssessFee = $("#checkAssessFee").val();
//                var finalAssessFee = $("#finalAssessFee").val();
//                var finalAssessAmount = $("#finalAssessAmount").val();
//                var travelFee = $("#travelFee").val();
//                var assessRemark = $("#assessRemark").val();
//
//                var toName = $("#toName").val();
//                var toTel = $("#toTel").val();
//                var toOrg = $("#toOrg").val();
//                var toAddress = $("#toAddress").val();
//                if(!checkAssessFee){
//                    alert("确认评估费不能为空");return;
//                }
//                if(!finalAssessFee){
//                    alert("最终评估费不能为空");return;
//                }
//                if(!finalAssessAmount){
//                    alert("最终评估金额不能为空");return;
//                }
//                if(!toName || !toTel || !toOrg){
//                    alert("请完整填写收件人信息");return;
//                }
//                param = {"id":id,"btnCode":btnCode,"checkAssessFee":checkAssessFee,"finalAssessFee":finalAssessFee,"finalAssessAmount":finalAssessAmount,
//                    "travelFee":travelFee,"assessRemark":assessRemark,"toName":toName,"toTel":toTel,"toOrg":toOrg,"toAddress":toAddress};
            }else if(btnCode == "1701"){
                return;
            }else if(btnCode == '1555'){
                if(${dto.isReport == 0}){
                    alert("报告未上传");return;
                }
            }
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    alert(e.data.msg);
                    location.reload();
                })
            }
        }else{
            var title = null,url = null;
            if(btnCode == '1300'){
                title = '分派评估师';
                url = "${ctx}/law/assessUsers?id=" + id + "&btnCode=" + btnCode + "&roleId=34"
            }else if(btnCode == '1204'){
                width = 650;
                height = 450;
                title = '添加跟踪';
                url = "${ctx}/law/followAdd?caseId=" + id;
//                alert("功能创建中...创建跟踪表进度表");return
            }else if(btnCode == "1500"){
                title = "上传方案";
                url = "${ctx}/law/uploadLawFile?id=" + id + "&btnCode=" + btnCode
            }else if(btnCode == "1501"){
                title = "上传请款函";
                url = "${ctx}/law/uploadLawFile?id=" + id + "&btnCode=" + btnCode
            }else if(btnCode == "1504"){
                title = "上传报告";
                url = "${ctx}/law/uploadLawFile?id=" + id + "&btnCode=" + btnCode
            }else if(btnCode == "1201" || btnCode == "1601" || btnCode == "1603" || btnCode == "1605" || btnCode == "1802" || btnCode == "1401"){
                title = "退回";
                url = "${ctx}/law/back?id=" + id + "&btnCode=" + btnCode
            }else if(btnCode == "1506" || btnCode == "1508" || btnCode == "1800"){
                width = 750;
                height = 300;
                title = btnCode == "1506" ? "确认案件信息" : "退案退费";
                url = "${ctx}/law/okLawCaseInfo?id=" + id + "&btnCode=" + btnCode
            }else if(btnCode == "1900"){
                height = 600;
                width = 1000;
                title = '分配机构';
                url = "${ctx}/law/selectOrgInfo?id="+id+"&btnCode="+btnCode;
            }else if(btnCode == "1205"){
                width = 1000;
                height = 600;
                title = "查看资料";
                url = "${ctx}/law/selectLawFile?id=" + id + "&btnCode=" + btnCode
            }else if(btnCode == "1503"){
                title = "催收备注";
                url = "${ctx}/law/back?id=" + id + "&btnCode=" + btnCode
            }else if(btnCode == "1999"){
                title = "查看进度";
                width = 1000;
                height = 600;
                url = "${ctx}/law/selectLawFile?type=5&caseId=" + id + "&viewType=progress";
            }else{
                alert("功能创建中...");return;
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

    function payTypeChange(obj){
        var value = obj.value;
        if(value == 2){
            $("#tr_address_one").show();
            $("#tr_address_two").show();
            $("#toName").attr("required","required");
            $("#toTel").attr("required","required");
            $("#toOrg").attr("required","required");
            $("#toAddress").attr("required","required");

            $("#toProvinceId").attr("required","required");
            $("#toCityId").attr("required","required");
            $("#toDistrictId").attr("required","required");
        }else{
            $("#tr_address_one").hide();
            $("#tr_address_two").hide();
            $("#toName").attr("required",null);
            $("#toTel").attr("required",null);
            $("#toOrg").attr("required",null);
            $("#toAddress").attr("required",null);

            $("#toProvinceId").attr("required",null);
            $("#toCityId").attr("required",null);
            $("#toDistrictId").attr("required",null);
        }
    }
    function appraiseTypeChange(obj){
        var value = obj.value;
        if(value == 1){
            $("#input_amount").attr("required",null);
        }else if(value == 2 || value ==3){
            $("#input_amount").attr("required","required");
        }
    }
    //自动计算“最终评估费”
    function reckonFinalFee(){
        var checkAssessFee = $("#checkAssessFee").val();
        var travelFee = $("#travelFee").val();
        $("#finalAssessFee").val((Number(checkAssessFee) + Number(travelFee)).toFixed(2));
    }

    //选择开票对象
    var selectCompanyName = function(){
        openDialog({
            frame:true,
            title:"选择开票对象",
            height:500,
            width:800,
            url:"${ctx}/billingApply/selectCompanyName?type=law"
        });
    }

    //选择收件人
    var selectRecipient = function(){
        openDialog({
            frame:true,
            title:"选择收件人",
            height:500,
            width:800,
            url:"${ctx}/billingApply/selectRecipient?type=law"
        });
    }

    $("#div_city").on("click",function(event,id){
        selectArea1(id);
    });

    $("#div_district").on("click",function(event,did){
        selectAreaCity1(did)
    });

    function selectArea(){
        var orgProvinceId = $("#toProvinceId").val();
        if(orgProvinceId == 0){
            return;
        }
        ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgProvinceId},function(v,e,p){
            $("#toCityId option").remove();
            $("#toCityId").append("<option value=''>请选择</option>");
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#toCityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
            }
            $("#toDistrictId option").remove();
            $("#toDistrictId").append("<option value=''>请选择</option>");
        });
    }
    function  selectAreaCity(){
        var orgCityId = $("#toCityId").val();
        if(orgCityId == 0){
            return;
        }
        ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgCityId},function(v,e,p){
            $("#toDistrictId option").remove();
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                $("#toDistrictId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
            }
        })
    }

    function selectArea1(){
        var shiId = $("#shiId").val();
        var quId = $("#quId").val();
        var orgProvinceId = $("#toProvinceId").val();
        if(orgProvinceId == 0){
            return;
        }
        ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":orgProvinceId},function(v,e,p){
            $("#toCityId option").remove();
            $("#toCityId").append("<option value=''>请选择</option>");
            console.log(e.data.results);
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                if(val.areaId == shiId){
                    $("#toCityId").append("<option selected='selected' value='"+val.areaId+"'>"+val.areaName+"</option>");
                }else{
                    $("#toCityId").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
                }
            }
            $("#toDistrictId option").remove();
            $("#toDistrictId").append("<option value=''>请选择</option>");
            selectAreaCity1(shiId,quId);
        });

    }

    function  selectAreaCity1(shiId,quId){
        ajaxSubmit("${ctx}/user/role/selectArea",{"parentId":shiId},function(v,e,p){
            $("#toDistrictId option").remove();
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                if(val.areaId == quId){
                    $("#toDistrictId").append("<option selected='selected' value='"+val.areaId+"'>"+val.areaName+"</option>");
                }else {
                    $("#toDistrictId").append("<option value='" + val.areaId + "'>" + val.areaName + "</option>");
                }
            }
        });
    }
</script>
</body>
</html>
