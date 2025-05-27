<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <link rel="stylesheet" href="${ctx}/css/viewer.min.css?v=1">

    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">


    <style>
        .unit{
            position: absolute;
            top: 10px;
            right: 10px;
        }
        .table-title {
            font-size: 14px;
            font-weight: bold;
            line-height: 40px;
        }

        table.spec-info tbody tr td:nth-of-type(2n + 1) {
            width: 10%;
        }

        table.spec-info tbody tr td:nth-of-type(2n) {
            width: 20%;
        }

        table.spec-info tbody tr td:nth-of-type(6n) {
            width: 30%;
        }

        .div1 {
            float: left;
        }

        .div1 button {
            margin: 0 1px;
            padding: 0 9px;
        }

        td div.pad5 {
            padding: 5px;
        }

        .viewDesc, .viewImg {
            height: 16px;
            display: block;
            color: #3BA9FF;
            cursor: pointer;
        }

        .viewNo {
            height: 16px;
            display: block;
            color: #3BA9FF;
            cursor: pointer;
        }

        .viewPJ {
            height: 16px;
            display: inline-block;
            display: inline-block;
            color: #3BA9FF;
            cursor: pointer;
        }

        .viewImgContent, .viewPJContent {
            width: 1px;
            height: 1px;
            overflow: hidden;
        }
    </style>
</head>
<body>

<div class="main">
    <input type="hidden" name="id" value="${billingApplyInfo.id}">
    <div class="title">
        <c:if test="${mType ==1}">
            <c:if test="${billingApplyInfo.billingState == 1}">
                <button class="butList active"
                        onclick="uploadBillingApplyMaterial('${billingApplyInfo.id}','${billingApplyInfo.caseNo}');">
                    提交材料
                </button>
                <button class="butList active"
                        onclick="uploadBillApplyImg('${billingApplyInfo.id}','${billingApplyInfo.caseNo}');">上传发票
                </button>
                <button class="butList active" onclick="billApplyStateUp('${billingApplyInfo.id}',2);">提交申请</button>
                <button class="butList active" onclick="billApplyEdit('${billingApplyInfo.id}');">修改</button>
                <button class="butList defuelt" onclick="billingApplyDelete('${billingApplyInfo.id}');">删除</button>
            </c:if>
            <c:if test="${billingApplyInfo.billingState != 1}">
                <button class="butList defuelt" onclick="">已申请</button>
            </c:if>
        </c:if>
        <c:if test="${mType ==2}">
            <c:if test="${menuType == 5}">
                <c:if test="${billingApplyInfo.billingState == 3 && billingApplyInfo.orgManager}">
                    <button class="butList active" onclick="deal('${billingApplyInfo.id}','retreat','${menuType}');">
                        退票
                    </button>
                    <button class="butList active" onclick="deal('${billingApplyInfo.id}','renew','${menuType}');">
                        重新开票
                    </button>
                </c:if>
                <c:if test="${billingApplyInfo.billingState == 5 && billingApplyInfo.boss}">
                    <button class="butList active"
                            onclick="deal('${billingApplyInfo.id}','retreatPass','${menuType}');">审核通过
                    </button>
                    <button class="butList active" onclick="deal('${billingApplyInfo.id}','back','${menuType}');">退回
                    </button>
                </c:if>
                <c:if test="${billingApplyInfo.billingState == 6 && billingApplyInfo.boss}">
                    <button class="butList active" onclick="deal('${billingApplyInfo.id}','renewPass','${menuType}');">
                        审核通过
                    </button>
                    <button class="butList active" onclick="deal('${billingApplyInfo.id}','back','${menuType}');">退回
                    </button>
                </c:if>
            </c:if>
            <c:if test="${menuType != 5}">
                <c:if test="${billingApplyInfo.billingState == 2}">
                    <button class="butList active"
                            onclick="uploadBillApplyImg('${billingApplyInfo.id}','${billingApplyInfo.caseNo}','${billingApplyInfo.noBillMoney}','one');">
                        单张开票
                    </button>
                    <button class="butList active"
                            onclick="uploadBillApplyImg('${billingApplyInfo.id}','${billingApplyInfo.caseNo}','${billingApplyInfo.noBillMoney}','two');">
                        批量开票
                    </button>
                    <button class="butList defuelt" onclick="billApplyReject('${billingApplyInfo.id}');">驳回申请</button>
                </c:if>
                <c:if test="${billingApplyInfo.billingState == 3}">
                    <button class="butList active"
                            onclick="uploadBillApplyImg('${billingApplyInfo.id}','${billingApplyInfo.caseNo}','${billingApplyInfo.noBillMoney}','one');">
                        单张开票
                    </button>
                    <button class="butList active"
                            onclick="uploadBillApplyImg('${billingApplyInfo.id}','${billingApplyInfo.caseNo}','${billingApplyInfo.noBillMoney}','two');">
                        批量开票
                    </button>
                </c:if>
                <c:if test="${billingApplyInfo.billingState == 4}">
                    <button class="butList defuelt" onclick="">已退票</button>
                </c:if>
                <c:if test="${billingApplyInfo.billingState == 5}">
                    <button class="butList defuelt" onclick="">退票审核中</button>
                </c:if>
                <c:if test="${billingApplyInfo.billingState == 6}">
                    <button class="butList defuelt" onclick="">重开审核中</button>
                </c:if>
                <c:if test="${billingApplyInfo.billingState == 7}">
                    <button class="butList active" onclick="deal('${billingApplyInfo.id}','confirmRetreat',6);">确认退票
                    </button>
                </c:if>
                <c:if test="${billingApplyInfo.billingState == 8}">
                    <button class="butList active" onclick="deal('${billingApplyInfo.id}','confirmNew',6);">确认重开
                    </button>
                </c:if>
                <%--                <c:if test="${billingApplyInfo.billingEnum !=7 && menuType != 8}">--%>
                <%--                    <button class="butList active" onclick="confirmAccount('${billingApplyInfo.id}');">公估确认到账</button>--%>
                <%--                </c:if>--%>
            </c:if>
            <%--            <c:if test="${menuType == 3}">--%>
            <%--                <button class="butList defuelt" onclick="uploadBillApplyImgYhc('${billingApplyInfo.id}','${billingApplyInfo.caseNo}','yhc');">确认红冲</button>--%>
            <%--            </c:if>--%>
            <button class="butList active" onclick="deal('${billingApplyInfo.id}','tuifei',6);">退费
            </button>
        </c:if>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>案件编号</td>
                    <td>${billingApplyInfo.caseNo}</td>
                    <td>开票产品</td>
                    <td>
                        <c:forEach items="${bullingEnums}" var="dto">
                            <c:if test="${dto.enumCode == billingApplyInfo.billingEnum}">
                                ${dto.enumName}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>开票项目</td>
                    <td>
                        <c:forEach items="${bullingItems}" var="dto">
                            <c:if test="${dto.enumCode == billingApplyInfo.billingItem}">
                                ${dto.enumName}
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td>开票状态</td>
                    <td>
                        <c:if test="${billingApplyInfo.billingState == 1}">未申请</c:if>
                        <c:if test="${billingApplyInfo.billingState == 2}">已申请</c:if>
                        <c:if test="${billingApplyInfo.billingState == 3}">已开票</c:if>
                        <c:if test="${billingApplyInfo.billingState == 4}">已退票</c:if>
                        <c:if test="${billingApplyInfo.billingState == 5}">退票审核中</c:if>
                        <c:if test="${billingApplyInfo.billingState == 6}">重开审核中</c:if>
                        <c:if test="${billingApplyInfo.billingState == 7}">退票审核通过</c:if>
                        <c:if test="${billingApplyInfo.billingState == 8}">重开审核通过</c:if>
                        <c:if test="${billingApplyInfo.billingState == 9}">退票中</c:if>
                    </td>
                    <td>开票公司</td>
                    <td>
                        <c:forEach items="${corporations}" var="dto">
                            <c:if test="${dto.id == billingApplyInfo.businessType}">
                                ${dto.name}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td></td>
                    <td></td>
<%--                    <td>所属机构</td>--%>
<%--                    <td>${billingApplyInfo.orgName}</td>--%>
                </tr>
                <tr>
                    <td>开票类型</td>
                    <td>
                        <c:if test="${billingApplyInfo.billingType == 1}">专票</c:if>
                        <c:if test="${billingApplyInfo.billingType == 2}">普票</c:if>
                        <c:if test="${billingApplyInfo.billingType == 3}">电子普票</c:if>
                    </td>
                    <td>开票金额</td>
<%--                    <td>${billingApplyInfo.billingMoney}</td>--%>
                    <td ><fmt:formatNumber type="number" value="${billingApplyInfo.billingMoney}" maxFractionDigits="2"/></td>
                    <td>案件标题</td>
                    <td>${billingApplyInfo.caseTitle}</td>
                </tr>
                <tr>
                    <td>已开票金额</td>
<%--                    <td>${billingApplyInfo.okBillMoney}</td>--%>
                    <td ><fmt:formatNumber type="number" value="${billingApplyInfo.okBillMoney}" maxFractionDigits="2"/></td>
                    <td>未开票金额</td>
<%--                    <td>${billingApplyInfo.noBillMoney}</td>--%>
                    <td ><fmt:formatNumber type="number" value="${billingApplyInfo.noBillMoney}" maxFractionDigits="2"/></td>
                    <td>已到账金额</td>
<%--                    <td>${billingApplyInfo.okAccountMoney}</td>--%>
                    <td ><fmt:formatNumber type="number" value="${billingApplyInfo.okAccountMoney}" maxFractionDigits="2"/></td>
                </tr>
                <tr>
                    <td>未到账金额</td>
<%--                    <td>${billingApplyInfo.noAccountMoney}</td>--%>
                    <td ><fmt:formatNumber type="number" value="${billingApplyInfo.noAccountMoney}" maxFractionDigits="2"/></td>
                    <td>收入归属机构</td>
                    <td>${billingApplyInfo.staffOrgName}</td>
                </tr>
                <tr>
                    <td>收件人姓名</td>
                    <td>${billingApplyInfo.recipientsName}</td>
                    <td>收件人电话</td>
                    <td>${billingApplyInfo.recipientsPhone}</td>
                    <td>收件人地址</td>
                    <td>${billingApplyInfo.province}${billingApplyInfo.city}${billingApplyInfo.district}${billingApplyInfo.address}</td>
                </tr>
                <tr>
                    <td>保单号</td>
                    <td>${billingApplyInfo.policyNo}</td>
                    <td>报案号</td>
                    <td>${billingApplyInfo.reportNo}</td>
                    <td>开票对象</td>
                    <td>${billingApplyInfo.companyName}</td>
                </tr>
                <tr>
                    <td>车牌号</td>
                    <td>${billingApplyInfo.carNo}</td>
                    <td>伤者姓名</td>
                    <td>${billingApplyInfo.woundedName}</td>
                    <%--                    <td>开票人</td>--%>
                    <%--                    <td>${billingApplyInfo.billingBy}</td>--%>
                </tr>
                <tr>
                    <td>创建人</td>
                    <td>${billingApplyInfo.createBy}</td>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${billingApplyInfo.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <%--                    <td>开票时间</td>--%>
                    <%--                    <td><fmt:formatDate value="${billingApplyInfo.billingTime}" pattern="yyyy-MM-dd HH:mm" /></td>--%>
                </tr>
                <tr>
                    <td>被保险人姓名</td>
                    <td>${billingApplyInfo.insuredName}</td>
<%--                    <td>绩效所属</td>--%>
<%--                    <td>${billingApplyInfo.meritName}</td>--%>
                    <td></td>
                    <td></td>
                    <td>备注</td>
                    <td>${billingApplyInfo.remark}</td>
                </tr>
                <c:if test="${billingApplyInfo.billingSource ==3}">
                    <tr>
                        <td>开票主体</td>
                        <td colspan="5">${billingApplyInfo.surveyBillSubjectName}</td>
                    </tr>
                </c:if>
                <c:if test="${menuType == 5 && billingApplyInfo.operReason != null}">
                    <tr>
                        <td>退回原因</td>
                        <td colspan="5">${billingApplyInfo.operReason}</td>
                    </tr>
                </c:if>
                <c:if test="${billingApplyInfo.rejectReason != null}">
                    <tr>
                        <td>驳回申请原因</td>
                        <td colspan="5">${billingApplyInfo.rejectReason}</td>
                    </tr>
                </c:if>
                <%--                <c:if test="${billingApplyInfo.billingEnum != 7 && billingApplyInfo.billingEnum !=11}">--%>
                <%--                    <tr>--%>
                <%--                        <td>公估到账状态</td>--%>
                <%--                        <td colspan="5">--%>
                <%--                            <c:if test="${billingApplyInfo.confirmAccountState == 1 || billingApplyInfo.confirmAccountState == null}">未到账</c:if>--%>
                <%--                            <c:if test="${billingApplyInfo.confirmAccountState == 2}">已到账</c:if>--%>
                <%--                        </td>--%>
                <%--                    </tr>--%>
                <%--                </c:if>--%>

                <%--                <c:forEach items="${apiRsp6.results}" var="item6">--%>
                <%--                    <tr>--%>
                <%--                        <td>公估到账金额</td>--%>
                <%--                        <td>${item6.money}</td>--%>
                <%--                        <td>公估到账时间</td>--%>
                <%--                        <td><fmt:formatDate value="${item6.createTime}" pattern="yyyy-MM-dd" /></td>--%>
                <%--                        <td>票据凭证</td>--%>
                <%--                        <td>--%>
                <%--                            <c:if test="${item6.imgs != null}">--%>
                <%--                                <div> <img src="${item6.imgs}" width="75;" height="75;" class="picToBig"></div>--%>
                <%--                            </c:if>--%>
                <%--                            <div>--%>
                <%--                            <button class="butList defuelt" onclick="billApplyImgsDelete('${item6.id}','accounts');">删除</button>--%>
                <%--                            </div>--%>
                <%--                        </td>--%>
                <%--                    </tr>--%>
                <%--                </c:forEach>--%>
                <%--                <c:forEach items="${apiRsp2.results}" var="item2">--%>
                <%--                    <tr>--%>
                <%--                        <td>发票号码</td>--%>
                <%--                        <td>${item2.billingCode}</td>--%>
                <%--                        <td>发票金额</td>--%>
                <%--                        <td>${item2.billingMoney}</td>--%>
                <%--                        <td>票据凭证</td>--%>
                <%--                        <td>--%>
                <%--                            <div class="div1">--%>
                <%--                                <img src="${item2.billingImgs}" width="75;" height="75;" class="picToBig">--%>
                <%--                            </div>--%>
                <%--                            <div class="div1">--%>
                <%--                            <c:if test="${menuType == 3}">--%>
                <%--                                <c:if test="${billingApplyInfo.billingState == 3}">--%>
                <%--                                    <c:if test="${item2.state == 1}">--%>
                <%--                                        <button class="butList defuelt" onclick="billApplyImgsDelete('${item2.id}','billingImgs');">删除</button>--%>
                <%--                                        <button class="butList defuelt" onclick="billApplyImgsUpd('${item2.id}',2);">作废</button>--%>
                <%--                                    </c:if>--%>
                <%--                                    <c:if test="${item2.state == 2}">--%>
                <%--                                        <div class="pad5">--%>
                <%--                                            <button class="butList defuelt" onclick="">已作废</button>--%>
                <%--                                            <button class="butList defuelt" onclick="billApplyImgsDelete('${item2.id}','billingImgs');">删除</button>--%>
                <%--                                        </div>--%>
                <%--                                        <div class="pad5">--%>
                <%--                                            作废时间：<fmt:formatDate value="${item2.stateUpdateTime}" pattern="yyyy-MM-dd HH:mm" />--%>
                <%--                                        </div>--%>
                <%--                                    </c:if>--%>
                <%--                                    <c:if test="${item2.state == 3}">--%>
                <%--                                        <div class="pad5">--%>
                <%--                                            <button class="butList defuelt" onclick="">已红冲</button>--%>
                <%--                                            <button class="butList defuelt" onclick="billApplyImgsDelete('${item2.id}','billingImgs');">删除</button>--%>
                <%--                                        </div>--%>
                <%--                                        <div class="pad5">--%>
                <%--                                            红冲时间：<fmt:formatDate value="${item2.stateUpdateTime}" pattern="yyyy-MM-dd HH:mm" />--%>
                <%--                                        </div>--%>
                <%--                                    </c:if>--%>
                <%--                                    <c:if test="${item2.state == 4}">--%>
                <%--                                        <div class="pad5">--%>
                <%--                                            <button class="butList defuelt" onclick="uploadBillApplyYhc('${item2.id}','yhc',4);">待红冲</button>--%>
                <%--                                            <button class="butList defuelt" onclick="billApplyImgsDelete('${item2.id}','billingImgs');">删除</button>--%>
                <%--                                        </div>--%>
                <%--                                    </c:if>--%>
                <%--                                    <c:if test="${item2.state == 5}">--%>
                <%--                                        <div class="pad5">--%>
                <%--                                            <button class="butList defuelt" onclick="uploadBillApplyYhc('${item2.id}','yhc',5);">待开票</button>--%>
                <%--                                            <button class="butList defuelt" onclick="billApplyImgsDelete('${item2.id}','billingImgs');">删除</button>--%>
                <%--                                        </div>--%>
                <%--                                    </c:if>--%>
                <%--                                </c:if>--%>
                <%--                            </c:if>--%>
                <%--                                <c:if test="${menuType == 2 || menuType == 5}">--%>
                <%--                                    <c:if test="${billingApplyInfo.billingState == 3}">--%>
                <%--                                        <c:if test="${item2.state == 1}">--%>
                <%--                                            <button class="butList defuelt" onclick="">正常</button>--%>
                <%--                                        </c:if>--%>
                <%--                                        <c:if test="${item2.state == 2}">--%>
                <%--                                            <button class="butList defuelt" onclick="">已作废</button>--%>
                <%--                                            作废时间：<fmt:formatDate value="${item2.stateUpdateTime}" pattern="yyyy-MM-dd HH:mm" />--%>
                <%--                                        </c:if>--%>
                <%--                                        <c:if test="${item2.state == 3}">--%>
                <%--                                            <button class="butList defuelt" onclick="">已红冲</button>--%>
                <%--                                            红冲时间：<fmt:formatDate value="${item2.stateUpdateTime}" pattern="yyyy-MM-dd HH:mm" />--%>
                <%--                                        </c:if>--%>
                <%--                                    </c:if>--%>
                <%--                                </c:if>--%>
                <%--                            </div>--%>
                <%--                        </td>--%>
                <%--                    </tr>--%>
                <%--                </c:forEach>--%>
                <c:forEach items="${apiRsp.results}" var="item">
                    <tr>
                        <td>材料凭证</td>
                        <td style="padding: 10px" colspan="5">
                            <c:if test="${item.fileType == 'gif' || item.fileType == 'jpg' ||item.fileType == 'jpeg' ||item.fileType == 'bmp' ||item.fileType == 'png' }">
                                <img src="${item.materialImgs}" width="75;" height="75;" class="picToBig">
                            </c:if>
                            <c:if test="${item.fileType == 'txt'}">
                                <img src="../img/txt.png" width="75;" height="75;" class="picToBig">
                            </c:if>
                            <c:if test="${item.fileType == 'doc' || item.fileType == 'docx'}">
                                <img src="../img/word.png" width="75;" height="75;" class="picToBig">
                            </c:if>
                            <c:if test="${item.fileType == 'xls' || item.fileType == 'xlsx'}">
                                <img src="../img/excel.png" width="75;" height="75;" class="picToBig">
                            </c:if>
                            <c:if test="${item.fileType == 'pdf'}">
                                <img src="../img/pdf.jpg" width="75;" height="75;" class="picToBig">
                            </c:if>
                            <c:if test="${(billingApplyInfo.billingState == 1 && menuType == 2)}">
                                <button class="butList defuelt"
                                        onclick="billApplyImgsDelete('${item.id}','materialImgs');">删除
                                </button>
                            </c:if>
                            <a href='${ctx}/download?files=${item.materialImgs}'>下载</a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>发票号码</th>
                    <th>发票金额</th>
                    <th>票据凭证</th>
                    <th>发票状态</th>
                    <th>到账状态</th>
                    <th>开票人</th>
                    <th>开票时间</th>
                    <th>到账明细</th>
                    <th>到账凭证</th>
                    <th>到账时间</th>
                    <th>备注</th>
                    <th>已到账金额</th>
                    <th>未到账金额</th>
                    <c:if test="${menuType == '3'}">
                        <th>操作</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${apiRsp666.results}" var="itema">
                    <tr>
                        <td width="12%">${itema.billingCode}</td>
<%--                        <td width="5%">${itema.billingMoney}</td>--%>
                        <td width="5%"><fmt:formatNumber type="number" value="${itema.billingMoney}" maxFractionDigits="2"/></td>
                        <td width="8%">
                            <c:if test="${itema.billingImgs != '' && itema.billingImgs != null}">
                                <div class="viewPJ" data-name="jq${itema.id}">查看</div>
                                <div class="viewPJContent" style="display: none">
                                    <ul id="jq${itema.id}">
                                        <li><img src="${itema.billingImgs}" alt="图片1"></li>
                                    </ul>
                                </div>
                            </c:if>
                            <c:if test="${itema.billingImgs == null || itema.billingImgs == ''}">
                                <div class="viewNo" data-name="jq${itema.id}"></div>
                            </c:if>
                        </td>
                        <td width="5%">
                            <c:if test="${itema.state == 1}">正常</c:if>
                            <c:if test="${itema.state == 2}">作废</c:if>
                            <c:if test="${itema.state == 3}">已红冲</c:if>
                        </td>
                        <td width="5%">
                            <c:if test="${itema.payState == 0}">未到账</c:if>
                            <c:if test="${itema.payState == 1}">部分到账</c:if>
                            <c:if test="${itema.payState == 2}">全部到账</c:if>
                        </td>
                        <td width="5%">${itema.createBy}</td>
                        <td width="8%"><fmt:formatDate value="${itema.createTime}" pattern="yyyy-MM-dd"/></td>
                        <td width="5%">
                            <c:forEach items="${itema.billingApplyAccountsList}" var="info">
                                ${info.money}<br/>
                            </c:forEach>
                        </td>
                        <td width="5%">
                            <c:forEach items="${itema.billingApplyAccountsList}" var="info">
                                <c:if test="${info.imgs != null && info.imgs != ''}">
                                    <div class="viewImg" data-name="jq2${info.id}">查看</div>
                                    <div class="viewImgContent" style="display: none">
                                        <ul id="jq2${info.id}">
                                            <li><img src="${info.imgs}"></li>
                                        </ul>
                                    </div>
                                </c:if>
                                <c:if test="${info.imgs == null || info.imgs == ''}">
                                    <div class="viewNo" data-name="jq2${info.id}"></div>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td width="8%">
                            <c:forEach items="${itema.billingApplyAccountsList}" var="info">
                                <fmt:formatDate value="${info.accountTime}" pattern="yyyy-MM-dd"/><br/>
                            </c:forEach>
                        </td>
                        <td width="4%">
                            <c:forEach items="${itema.billingApplyAccountsList}" var="item">
                                <c:if test="${item.remark != null && item.remark != ''}">
                                    <div class="viewDesc" data-title="${item.remark}">查看</div>
                                    <%--<div class="viewDescContent" style="display: none">--%>
                                    <%--${item.remark}--%>
                                    <%--</div>--%>
                                </c:if>
                                <c:if test="${item.remark == null || item.remark == ''}">
                                    <div class="viewNo" data-title=""></div>
                                </c:if>
                            </c:forEach>

                        </td>
<%--                        <td width="5%">${itema.okAccountMoney}</td>--%>
                        <td width="5%"><fmt:formatNumber type="number" value="${itema.okAccountMoney}" maxFractionDigits="2"/></td>
<%--                        <td width="5%">${itema.noAccountMoney}</td>--%>
                        <td width="5%"><fmt:formatNumber type="number" value="${itema.noAccountMoney}" maxFractionDigits="2"/></td>
                        <c:if test="${menuType == '3'}">
                            <td width="10%">
                                <a onclick="uploadBillApplyImg2('${itema.id}','${billingApplyInfo.caseNo}','${billingApplyInfo.billingMoney}','upd','${itema.billingMoney}','${itema.billingCode}','${itema.billingImgs}','${itema.createTime}');">编辑</a>
                                <a onclick="billApplyImgsUpd('${itema.id}',2)">&nbsp;&nbsp;作废</a>
                                <a onclick="uploadBillApplyImg2('${itema.id}','${billingApplyInfo.caseNo}','${itema.billingMoney}','hc','','','','${itema.createTime}');">&nbsp;&nbsp;红冲</a>
<%--                                <c:if test="${itema.billingApplyAccountsList.size()  == 0 && itema.state != 2 && itema.state != 3}">--%>

<%--                                </c:if>--%>
                                <c:if test="${itema.state != 2 && itema.state != 3 && itema.payState != 2}">
                                    <a onclick="confirmAccount2('${itema.billId}','${itema.id}','${itema.noAccountMoney}');">&nbsp;&nbsp;添加到账</a></a>
                                </c:if>
                                <c:if test="${itema.billingApplyAccountsList.size() > 0}">
                                    <a onclick="billApplyImgsUpd('${itema.id}',9)">&nbsp;&nbsp;撤销到账</a>
                                </c:if>
                            </td>
                        </c:if>

                    </tr>

                </c:forEach>
                </tbody>
            </table>
            <div style="margin: 5px 5px 5px 5px;font-size: large"><h3>退费明细</h3></div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>退费金额</th>
                        <th>退费时间</th>
                        <th>备注</th>
                        <th>操作人</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${billingApplyInfo.refunds}" var="item">
                        <tr>
                            <td>${item.refundMoney}</td>
                            <td><fmt:formatDate value="${item.refundTime}" pattern="yyyy-MM-dd"/></td>
                            <td>${item.remark}</td>
                            <td>${item.refundUserBy}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js"
                    onclick="javascript:closeDialog();">关闭
            </button>
        </div>
    </div>
</div>

<div id="dialogId"></div>
<script type="text/html" id="view-or-refundOK">
    <div class="layui-form" style="margin-top: 30px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">已到账金额</label>
                <div class="layui-input-inline _input">
                    <input type="text" class="lefanMoney1 layui-input" placeholder="" autocomplete="off" disabled style="background-color: #eee">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">退费金额</label>
                <div class="layui-input-inline _input">
                    <input type="text" class="lefanMoney2 layui-input" placeholder="" autocomplete="off">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">退费时间</label>
                <div class="layui-input-inline _input">
                    <input type="text" class="time-input layui-input" id="refundOkTime" placeholder="" autocomplete="off">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">备注</label>
                <div class="layui-input-inline _input">
                     <textarea class="reason-textarea layui-textarea lefanDesc" placeholder="请输入"
                               style="height: 60px;width: 100%;margin:0 auto;resize:none;"></textarea>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 130px;margin-top: 30px">
                <button class="layui-btn layui-btn-primary close-btn" style="width: 100px;">取消</button>
                <button class="layui-btn layui-btn-normal submit-btn" style="width: 100px;">确定</button>
            </div>
        </div>
    </div>
</script>


<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src="${ctx}/js/viewer.min.js"></script>
<script src="${ctx}/js/layui/layui.js"></script>


<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<script type="text/javascript">
    $("#editForm").bind('submit', function (event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this, reloadParent, null, null, reloadParent);
        event.preventDefault();
    });
    var arryViewer = []

    layui.use(['layer','laydate'], function () {
        var layer = layui.layer

        laydate = layui.laydate

        $('.table-hover').on('click', '.viewImg', function () {
            var _this = $(this)
            if (_this.siblings().find('ul').size() > 0) {
                var name = _this.attr('data-name')
                _this.siblings().show()
                viewer = new Viewer(document.getElementById(name));
                viewer.show()
            }

        })

        $('.table-hover').on('click', '.viewPJ', function () {
            var _this = $(this)
            var name = _this.attr('data-name')
            _this.siblings().show()
            viewer = new Viewer(document.getElementById(name));
            viewer.show()
        })
        $('.table-hover').on('click', '.viewer-close', function () {
            var _this = $(this)
            _this.parents('.viewer-container').detach()
            viewer.destroy()
        })


        $('.table-hover').on('mouseover', '.viewDesc', function () {
            var _this = $(this)
            var descText = _this.attr('data-title').trim()
            layer.tips(descText, _this, {
                tips: [1, '#666'],
            });
        })

    })

     function dateToString(date){
         date = new Date(date);
         date = date.getTime() - 60 * 60 * 14 * 1000
         date = new Date(date);
         var y = date.getFullYear();
         var m = date.getMonth() + 1;
         var d = date.getDate();
         m = PrefixInteger(m, 2);
         d = PrefixInteger(d, 2);
         return y + '-' + m + '-' + d;
    }
    function PrefixInteger(num, m) {
        return (Array(m).join(0) + num).slice(-m);
    }


    /**
     * 关闭dialog
     */
    function closeDialog() {
        var closeBtn = $("#diglog_close_btn");
        if (closeBtn.size() == 0) {
            closeBtn = $("#diglog_close_btn", window.parent.document);
        }
        closeBtn.click();
    }

    /**
     * 提交申请
     */
    function billApplyStateUp(id, billingState) {
        ajaxSubmit("${ctx}/billingApply/billApplyStateUp", {
            "id": id,
            "billingState": billingState
        }, reload, "提交成功！", "确认提交申请？", "提交失败！");
    }

    /**
     * 驳回申请
     */
    function billApplyReject(id) {
        openDialog({
            frame: true,
            title: "驳回申请原因",
            height: 350,
            width: 650,
            url: "${ctx}/billingApply/billApplyRejectReason?id=" + id
        });
    }

    /**
     * 提交开票材料
     */
    function uploadBillingApplyMaterial(id, caseNo) {
        openDialog({
            frame: true,
            title: "提交材料页面",
            height: 350,
            width: 650,
            url: "${ctx}/billingApply/uploadBillingApplyMaterial?id=" + id + "&caseNo=" + caseNo
        });
    }

    /**
     * 修改
     */
    function billApplyEdit(id) {
        openDialog({
            frame: true,
            title: "修改页面",
            height: 650,
            width: 800,
            url: "${ctx}/billingApply/billApplyEdit?id=" + id
        });
    }

    /**
     * 开票页面
     */
    function uploadBillApplyImg(id, caseNo, billMoney, fromType) {
        var _width = $(document).width() * 0.9
        var _height = $(document).height() * 0.9
        openDialog({
            frame: true,
            title: "开票页面",
            height: 650,
            width: _width,
            url: "${ctx}/suning/billApply/uploadBillApplyImg?id=" + id + "&caseNo=" + caseNo + "&billMoney=" + billMoney + "&fromType="+fromType,
            load : true
        });
    }

    function uploadBillApplyImg2(id, caseNo, billMoney, type, money, code, img,createTime) {
        createTime = dateToString(createTime);
        console.log(createTime)
        openDialog({
            frame: true,
            title: "开票页面",
            height: 650,
            width: 850,
            url: "${ctx}/suning/billApply/uploadBillApplyImg?id=" + id + "&caseNo=" + caseNo + "&billMoney=" + billMoney + "&operatorType=" + type + "&money=" + money + "&code=" + code + "&img=" + img+"&createTime="+createTime
        });
    }

    function deal(id, model, menuType) {
        //弹出界面
        if (model == 'back') {
            openDialog({
                frame: true,
                title: "退回",
                height: 350,
                width: 650,
                url: "${ctx}/billingApply/billApplyStateUpBack?id=" + id + "&menuType=" + menuType + "&model=" + model
            });
        }else  if (model == 'tuifei') {
            inputIndex = layer.open({
                type: 1,
                title: "退费",
                area: ['560px', '500px'],
                content: $('#view-or-refundOK').html(),
                success: function () {
                    refundOkTime = laydate.render({
                        elem: '#refundOkTime',
                        value: new Date(),
                        type: 'date',
                        format: 'yyyy-MM-dd',
                        trigger: 'click'
                    });

                    $('.lefanMoney1').val(${billingApplyInfo.okAccountMoney})
                    $('.lefanMoney2').val(0)

                    $('.submit-btn').click(function () {
                        if (!$('.lefanMoney2').val() || $('.lefanMoney2').val() == 0){
                            layer.msg("退费金额 必填", {
                                time: 1000,
                                icon: 2
                            })
                            return;
                        }
                        if (Number($('.lefanMoney2').val()).toFixed(2) > Number($('.lefanMoney1').val()).toFixed(2)){
                            layer.msg("退费金额不可大于已到账金额！", {
                                time: 1000,
                                icon: 2
                            })
                            return;
                        }
                        $('.submit-btn').addClass('poi-no')
                        setTimeout(function () {
                            $('.submit-btn').removeClass('poi-no')
                        }, 6000)
                        var url = '${ctx}/billingApply/billingApplySave'

                        var params = {
                            btnCode: 'save-refund',
                            billId: ${billingApplyInfo.id},
                            lefanMoney1: $('.lefanMoney1').val(),
                            refundMoney: $('.lefanMoney2').val(),
                            refundTime: $('#refundOkTime').val() + " 00:00:00",
                            remark: $('.lefanDesc').val(),

                        }
                        $.ajax({
                            url: url,
                            type: 'post',
                            data: params,
                            success: function (res,v) {
                                // console.log(v);
                                var res = v.data
                                if (res.isSuccess){
                                    layer.msg('成功', {
                                        time: 1000,
                                        icon: 1
                                    },function(){
                                        location.reload()
                                    })
                                }else{
                                    _this.removeClass('poi-no')
                                    layer.msg(res.msg, {
                                        time: 1000,
                                        icon: 2
                                    })
                                }
                            }
                        })
                    })
                    $('.close-btn').click(function () {
                        layer.close(inputIndex)
                    })
                }
            });
        }else {
            var url = "${ctx}/billingApply/billApplyStateUp", param = {"id": id, "model": model, "menuType": menuType};
            if (confirm('是否确认？')) {
                ajaxSubmit(url, param, function (v, e, p) {
                    alert(e.data.msg);
                    reloadParent();
                })
            }
        }
    }

    /**
     * 删除单条发票或材料记录
     */
    function billApplyImgsDelete(id, type) {
        ajaxSubmit("${ctx}/billingApply/billApplyImgsDelete", {
            "id": id,
            "type": type
        }, reload, "删除成功！", "确认删除？", "删除失败！");
    }

    /**
     * 公估确认到账
     */
    function confirmAccount(id, caseNo) {
        openDialog({
            frame: true,
            title: "公估确认到账",
            height: 450,
            width: 600,
            url: "${ctx}/billingApply/confirmAccount?id=" + id + "&caseNo=" + caseNo
        });
    }

    function confirmAccount2(billId, imgsId, noAccountMoney) {
        openDialog({
            frame: true,
            title: "公估确认到账",
            height: 450,
            width: 600,
            url: "${ctx}/billingApply/confirmAccount?id=" + billId + "&imgsId=" + imgsId + "&noAccountMoney=" + noAccountMoney
        });
    }

    /**
     * 发票作废或红冲
     */
    function billApplyImgsUpd(id, billApplyImgsState) {
        var param = {
            "id": id,
            "billApplyImgsState": billApplyImgsState
        }
        if (billApplyImgsState == 9){
            param["oprType"] = "chexiao-acc";
        }
        ajaxSubmit("${ctx}/billingApply/billApplyImgsUpd", param , reload, "操作成功！", "确认？", "操作失败！");
    }

    /**
     * 待红冲，待开票
     */
    function uploadBillApplyYhc(id, yhc, type) {
        openDialog({
            frame: true,
            title: "开票页面",
            height: 350,
            width: 650,
            url: "${ctx}/suning/billApply/uploadBillApplyImg?id=" + id + "&yhc=" + yhc + "&type=" + type
        });
    }

    /**
     * 确认红冲
     */
    function uploadBillApplyImgYhc(id, caseNo, yhc) {
        openDialog({
            frame: true,
            title: "开票页面",
            height: 350,
            width: 650,
            url: "${ctx}/suning/billApply/uploadBillApplyImg?id=" + id + "&caseNo=" + caseNo + "&yhc=" + yhc
        });
    }

    /**
     * 提交申请
     */
    function billingApplyDelete(id) {
        ajaxSubmit("${ctx}/billingApply/billingApplyDelete", {"id": id}, closeDialogRefresh, "删除成功！", "确认删除？", "删除失败！");
    }
</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js"></script>
</body>
</html>