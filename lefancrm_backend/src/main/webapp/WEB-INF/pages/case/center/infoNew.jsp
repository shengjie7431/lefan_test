<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>案件列表</title>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<style>
    .table-main {
        position: relative;
        width: 98%;
        padding: 26px;
        border: 1px solid #bbb;
        font-size: 12px;
        margin: 20px auto;
    }

    .table-title {
        position: absolute;
        top: -14px;
        left: 10px;
        width: 110px;
        height: 26px;
        line-height: 26px;
        color: #45B4FE;
        background-color: #fff;
        border: 1px solid #45b4fe;
        text-align: center;
        font-size: 13px;
    }

    table tr {}

    table tr td {
        display: inline-block;
        width: 24%;
        line-height: 20px;
        padding: 10px 1% 10px 0;
        font-size: 12px;
    }
</style>
<style>
    .table-main2 {
        position: relative;
        width: 98%;
        padding: 26px;
        border: 1px solid #bbb;
        font-size: 12px;
        margin: 20px auto;
    }

    .table-title2 {
        position: absolute;
        top: -14px;
        left: 10px;
        width: 110px;
        height: 26px;
        line-height: 26px;
        color: #45B4FE;
        background-color: #fff;
        border: 1px solid #45b4fe;
        text-align: center;
        font-size: 13px;
    }

    table tr {}

    table tr td {
        display: table-cell;
        width: 24%;
        line-height: 20px;
        padding: 10px 1% 10px 0;
        font-size: 12px;
    }
</style>
<style>
    .table-main3 {
        position: relative;
        width: 98%;
        padding: 26px;
        border: 1px solid #bbb;
        font-size: 12px;
        margin: 20px auto;
    }

    .table-title3 {
        position: absolute;
        top: -14px;
        left: 10px;
        width: 110px;
        height: 26px;
        line-height: 26px;
        color: #45B4FE;
        background-color: #fff;
        border: 1px solid #45b4fe;
        text-align: center;
        font-size: 13px;
    }

    table tr {}

    table tr td {
        display: table-cell;
        width: 16%;
        line-height: 20px;
        padding: 10px 1% 10px 0;
        font-size: 12px;
    }
</style>
<style>
    .contain {
        display: flex;
        overflow: hidden;
    }

    .header {
        margin-right: 10px;
    }

    .step-out {
        position: relative;
        width: 100%;
    }

    .step {
        position: relative;
        width: 100%;
        overflow: auto;
    }

    .stepContent {
        position: relative;
        /* overflow: hidden; */
        width: 1000px;
        height: 150px;
        /* display: flex; */
    }

    .bar {
        width: 83px;
        overflow: auto;
        display: inline-block;
        float: left;
    }

    .s_active div.name {
        color: #333;
        font-weight: bold;
    }

    .stepbg div.s_active {
        background-color: #45B4FE;
    }

    .stepbg div.s_active {
        background-color: #45B4FE
    }

    .stepContent .name {
        line-height: 36px;
        color: #777;
        font-size: 15px;
        text-align: center;
        padding-bottom: 50px;
    }

    .stepContent .date {
        height: 34px;
        line-height: 16px;
        color: #777777;
        font-size: 12px;
        text-align: center;
    }

    .stepbg {
        display: flex;
        align-items: center;
        position: absolute;
        top: 40px;
        left: 24px;
    }

    .stepbg .index {
        width: 30px;
        height: 30px;
        line-height: 30px;
        border-radius: 50%;
        background-color: #ACDEFF;
        color: #ffffff;
        font-size: 14px;
        text-align: center;
    }

    .stepbg .line {
        width: 53px;
        height: 2px;
        background-color: #ACDEFF;
    }

    /* .stepbg .line1 {
        width: 55px;
    } */
    .stepbg .line2 {
        width: 58px;
    }

    .stepTitle {
        width: 100%;
        height: 30px;
        display: flex;
        justify-content: space-between;
    }

    .stepTitle .stepTitle-l {
        color: #333;
        font-size: 16px;
        font-weight: bold;
    }

    .stepTitle .stepTitle-r {
        width: 62px;
        height: 24px;
        line-height: 24px;
        background-color: #45B4FE;
        color: #fff;
        font-size: 12px;
        text-align: center;
    }

    .header {
        width: 1110px;
        padding: 20px;
        background-color: #F0FAFF;
    }

    /* ------- */
    .header-r {
        width: 415px;
        padding: 20px;
        background-color: #F0FAFF;
    }

    .step2 {
        height: 116px;
        margin-top: 20px;
        overflow: auto;
    }

    .stepTitle2 {
        width: 366px;
        height: 30px;
        display: flex;
        justify-content: space-between;
    }

    .stepTitle2-l {
        color: #333;
        font-size: 16px;
        font-weight: bold;
    }

    .stepTitle2-btn {
        width: 134px;
        display: flex;
        justify-content: space-between;
    }

    .stepTitle2-btn .s_btn {
        width: 62px;
        height: 24px;
        line-height: 24px;
        background-color: #45B4FE;
        color: #fff;
        font-size: 12px;
        text-align: center;
    }

    .step2-block {
        display: flex;
    }

    .step2-block .date {
        min-width: 70px;
        width: 70px;
        padding-right: 10px;
        color: #666;
        font-size: 12px
    }

    .step2-block-r .s_title {
        display: flex;
        align-items: flex-start;
    }

    .step2-block-r .s_title .index {
        width: 12px;
        height: 12px;
        border: 3px solid #45B4FE;
        border-radius: 50%;
    }

    .step2-block-r .s_title .text {
        width: 257px;
        line-height: 12px;
        color: #333;
        font-size: 12px;
        font-weight: 500;
        margin-left: 15px;
    }

    .step2 .content {
        width: 257px;
        margin-left: 5px;
        padding-top: 4px;
        padding-left: 20px;
        padding-bottom: 20px;
        border-left: 2px solid #45B4FE;
        color: #333;
        font-size: 12px;
        font-weight: 500;
    }

    .step2 .content .text {
        display: inline-block;
        line-height: 14px;
        color: #333;
        font-size: 12px;
        font-weight: 500;
        width: 100%;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
    }

    .step2-block-r .title .index {
        width: 12px;
        height: 12px;
        border: 3px solid #45B4FE;
        background-color: #fff;
        border-radius: 50%;
    }

    .step2-block-r .title2 {
        position: relative;
    }

    .step2-block-r .title2 .index {
        width: 12px;
        height: 12px;
        border: 3px solid #45B4FE;
        background-color: #45B4FE;
        border-radius: 50%;
    }

    .step2-block-r .title2 .s_btn2 {
        position: absolute;
        top: 0;
        left: 16px;
        margin-left: 13px;
        width: 126px;
        height: 26px;
        line-height: 26px;
        color: #45B4FE;
        border: 2px solid #45B4FE;
        font-size: 12px;
        text-align: center;
    }

    .show {
        display: none;
    }

    .header-r2 {
        margin-left: 10px;
        width: 260px;
        padding: 20px;
        background-color: #F0FAFF;
    }

    .stepCurUser {
        color: #333;
        font-size: 12px;
        font-weight: bold;
    }

    .stepUsers {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        padding-top: 20px;
    }

    .stepUser {
        width: 50%;
        overflow: hidden;
        line-height: 20px;
        color: #333;
        font-size: 12px;
        font-weight: 500;
    }
</style>
<style>
    .title{
        border-bottom: 2px solid #ddd;
        display: flex;
        flex-wrap: wrap;
        align-items: center;
        position: fixed;
        width: 100%;
        background-color: #fff;
        top: 0;
        left: 0;
        z-index: 10000;
    }
    .butList {
        height: 32px;
        padding: 0 10px;
        margin: 4px 8px;
        cursor: pointer;
        box-sizing: border-box;
    }
    .minor{
        background: #ACDEFF;
        color: #fff;
        border: none;
    }
</style>

<body>
    <div class="main">
        <div class="title">
            <button class="butList defuelt" onclick="view(2,'${caseInfo.id}','${caseInfo.caseNo}','view')">查看单证(${caseFileMidSize})</button>

            <%--索赔清单--%>
            <c:if test="${menuType == 30}">
                <c:if test="${caseInfo.gradationState == 3}">
                    <c:if test="${caseInfo.claimState == 1}">

                    </c:if>
                    <c:if test="${caseInfo.claimState == 2}">
                        <button class="butList active" onclick="operate('${caseInfo.id}','claim_ccjiaoliu',true)">CC交流完成</button>
                        <%--<button class="butList active" onclick="operate('${caseInfo.id}','claim_to_legal',true)">转诉讼</button>--%>
                    </c:if>
                    <c:if test="${caseInfo.claimState == 3}">
                        <button class="butList active" onclick="operate('${caseInfo.id}','claim_jianmian',true)">见面伤者完成</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState == 4}">
                        <button class="butList active" onclick="operate('${caseInfo.id}','claim_cailiao',true)">材料收集完成</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState == 5}">
                        <button class="butList active" onclick="operate('${caseInfo.id}','claim_jianding',false)">鉴定情况</button>
                        <button class="butList minor" onclick="operate('${caseInfo.id}','claim_to_legal',true)">转诉讼</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState == 6}">
                        <button class="butList active" onclick="report(33,'${caseInfo.id}','${caseInfo.caseNo}','edit')">索赔预案</button>
                        <%--<button class="butList active" onclick="operate('${caseInfo.id}','claim_yuan',false)">索赔预案</button>--%>
                        <button class="butList minor" onclick="operate('${caseInfo.id}','claim_to_legal',true)">转诉讼</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState > 6}">
                        <button class="butList defuelt" onclick="report(3,'${caseInfo.id}','${caseInfo.caseNo}','view')">索赔预案</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState == 8}">
                        <button class="butList active" onclick="operate('${caseInfo.id}','claim_tiaojie',false)">调解</button>
                        <button class="butList minor" onclick="operate('${caseInfo.id}','claim_to_legal',true)">转诉讼</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState == 9}">
                        <button class="butList active" onclick="report(44,'${caseInfo.id}','${caseInfo.caseNo}','edit')">结案报告</button>
                        <%--<button class="butList active" onclick="operate('${caseInfo.id}','claim_jiean_report',false)">结案报告</button>--%>
                        <button class="butList minor" onclick="operate('${caseInfo.id}','claim_to_legal',true)">转诉讼</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState < 10}">
                        <button class="butList minor" onclick="operate('${caseInfo.id}','3001',false)">解约</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState > 9}">
                        <button class="butList active" onclick="report(4,'${caseInfo.id}','${caseInfo.caseNo}','view')">结案报告</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState == 11}">
                        <button class="butList active" onclick="operate('${caseInfo.id}','claim_fuwufei',false)">收取服务费</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState == 12}">
                        <button class="butList active" onclick="operate('${caseInfo.id}','claim_jiean_start',true)">发起结案</button>
                    </c:if>
                </c:if>
            </c:if>
            <%--预案审核--%>
            <c:if test="${menuType == 35}">
                <c:if test="${caseInfo.gradationState == 3}">
                    <c:if test="${caseInfo.claimState == 7}">
                        <button class="butList active" onclick="report(3,'${caseInfo.id}','${caseInfo.caseNo}','audit')">索赔预案</button>
                        <button class="butList active" onclick="operate('${caseInfo.id}','claim_yuan_agree',true)">审核通过</button>
                        <button class="butList active" onclick="operate('${caseInfo.id}','claim_yuan_veto',false)">退回</button>
                        <button class="butList minor" onclick="operate('${caseInfo.id}','claim_to_legal',true)">转诉讼</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState == 10}">
                        <button class="butList active" onclick="report(4,'${caseInfo.id}','${caseInfo.caseNo}','audit')">结案报告</button>
                        <button class="butList active" onclick="operate('${caseInfo.id}','claim_jiean_report_agree',true)">审核通过</button>
                        <button class="butList active" onclick="operate('${caseInfo.id}','claim_jiean_report_veto',false)">退回</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState == 13}">
                        <button class="butList active" onclick="operate('${caseInfo.id}','claim_jiean_agree',true)">审核通过</button>
                        <button class="butList active" onclick="operate('${caseInfo.id}','claim_jiean_veto',false)">退回</button>
                    </c:if>
                </c:if>
            </c:if>
            <%--诉讼清单--%>
            <c:if test="${menuType == 45}">
                <c:if test="${caseInfo.gradationState == 6}">
                    <c:if test="${caseInfo.claimState == 5}">
                        <button class="butList active" onclick="operate('${caseInfo.id}','legal_jianding',false)">鉴定情况</button>
                        <button class="butList minor" onclick="operate('${caseInfo.id}','legal_to_claim',true)">转索赔</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState == 6}">
                        <%--<button class="butList active" onclick="operate('${caseInfo.id}','legal_yuan',false)">诉讼预案</button>--%>
                        <button class="butList active" onclick="report(55,'${caseInfo.id}','${caseInfo.caseNo}','edit')">诉讼预案</button>
                        <button class="butList minor" onclick="operate('${caseInfo.id}','legal_to_claim',true)">转索赔</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState > 6}">
                        <button class="butList defuelt" onclick="report(5,'${caseInfo.id}','${caseInfo.caseNo}','view')">诉讼预案</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState == 8}">
                        <button class="butList active" onclick="operate('${caseInfo.id}','legal_lian',true)">立案</button>
                        <button class="butList minor" onclick="operate('${caseInfo.id}','legal_to_claim',true)">转索赔</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState == 9}">
                        <c:if test="${caseInfo.caseKaitingInfos ==null || caseInfo.caseKaitingInfos.size() == 0}">
                            <button class="butList active" onclick="operate('${caseInfo.id}','legal_kaiting_start',false)">开庭</button>
                        </c:if>
                        <c:if test="${caseInfo.caseKaitingInfos!=null && caseInfo.caseKaitingInfos.size()>0}">
                            <button class="butList active" onclick="operate('${caseInfo.id}','legal_kaiting_start',false)">新增开庭</button>
                            <button class="butList active" onclick="operate('${caseInfo.id}','legal_kaiting_end',true)">结束开庭</button>
                        </c:if>
                        <button class="butList minor" onclick="operate('${caseInfo.id}','legal_to_claim',true)">转索赔</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState < 10}">
                        <button class="butList minor" onclick="operate('${caseInfo.id}','3001',false)">解约</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState == 10}">
                        <button class="butList active" onclick="report(44,'${caseInfo.id}','${caseInfo.caseNo}','edit')">结案报告</button>
                        <%--<button class="butList active" onclick="operate('${caseInfo.id}','legal_jiean_report',false)">结案报告</button>--%>
                        <button class="butList minor" onclick="operate('${caseInfo.id}','legal_to_claim',true)">转索赔</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState == 12}">
                        <button class="butList active" onclick="operate('${caseInfo.id}','legal_fuwufei',false)">收取服务费</button>
                    </c:if>
                    <c:if test="${caseInfo.claimState == 13}">
                        <button class="butList active" onclick="operate('${caseInfo.id}','legal_jiean_start',true)">发起结案</button>
                    </c:if>
                </c:if>
            </c:if>
            <%--诉讼审核--%>
            <c:if test="${menuType == 50}">
                <c:if test="${caseInfo.gradationState == 6}">
                    <c:if test="${caseInfo.claimState == 7}">
                        <button class="butList active" onclick="report(5,'${caseInfo.id}','${caseInfo.caseNo}','audit')">诉讼预案</button>
                        <button class="butList active" onclick="operate('${caseInfo.id}','legal_yuan_agree',true)">审核通过</button>
                        <button class="butList active" onclick="operate('${caseInfo.id}','legal_yuan_veto',true)">退回</button>
                        <button class="butList minor" onclick="operate('${caseInfo.id}','legal_to_claim',true)">转索赔</button>
                    </c:if>

                    <c:if test="${caseInfo.claimState == 11}">
                        <button class="butList active" onclick="report(4,'${caseInfo.id}','${caseInfo.caseNo}','audit')">结案报告</button>
                        <button class="butList active" onclick="operate('${caseInfo.id}','legal_jiean_report_agree',true)">审核通过</button>
                        <button class="butList active" onclick="operate('${caseInfo.id}','legal_jiean_report_veto',false)">退回</button>
                    </c:if>

                    <c:if test="${caseInfo.claimState == 14}">
                        <button class="butList active" onclick="operate('${caseInfo.id}','legal_jiean_agree',true)">审核通过</button>
                        <button class="butList active" onclick="operate('${caseInfo.id}','legal_jiean_veto',false)">退回</button>
                    </c:if>
                </c:if>
            </c:if>

            <%--<c:if test="${menuType == 1}">--%>
                <%--<c:if test="${caseInfo.gradationState == 2}">--%>
                    <%--<button class="butList defuelt" onclick="report(1,'${caseInfo.id}','${caseInfo.caseNo}','view')">公估报告</button>--%>
                    <%--<button class="butList defuelt" onclick="report(2,'${caseInfo.id}','${caseInfo.caseNo}','view')">评估报告</button>--%>
                <%--</c:if>--%>
                <%--<c:if test="${caseInfo.gradationState == 3}">--%>
                    <%--<button class="butList defuelt" onclick="report(3,'${caseInfo.id}','${caseInfo.caseNo}','view')">索赔预案</button>--%>
                    <%--<button class="butList defuelt" onclick="report(4,'${caseInfo.id}','${caseInfo.caseNo}','view')">结案报告</button>--%>
                <%--</c:if>--%>
                <%--<c:if test="${caseInfo.gradationState == 6 || caseInfo.gradationState == 4}">--%>
                    <%--<button class="butList defuelt" onclick="report(3,'${caseInfo.id}','${caseInfo.caseNo}','view')">索赔预案</button>--%>
                    <%--<button class="butList defuelt" onclick="report(5,'${caseInfo.id}','${caseInfo.caseNo}','view')">诉讼预案</button>--%>
                    <%--<button class="butList defuelt" onclick="report(4,'${caseInfo.id}','${caseInfo.caseNo}','view')">结案报告</button>--%>
                <%--</c:if>--%>
            <%--</c:if>--%>
            <%--评估中心-综合查询--%>
            <c:if test="${menuType == 11}">
                <button class="butList defuelt" onclick="report(2,'${caseInfo.id}','${caseInfo.caseNo}','view')">评估报告</button>
            </c:if>
            <%--索赔中心-综合查询--%>
            <c:if test="${menuType == 111}">
                <button class="butList defuelt" onclick="report(3,'${caseInfo.id}','${caseInfo.caseNo}','view')">索赔预案</button>
                <button class="butList defuelt" onclick="report(4,'${caseInfo.id}','${caseInfo.caseNo}','view')">结案报告</button>
            </c:if>
            <%--法务中心-综合查询--%>
            <c:if test="${menuType == 1111}">
                <button class="butList defuelt" onclick="report(3,'${caseInfo.id}','${caseInfo.caseNo}','view')">索赔预案</button>
                <button class="butList defuelt" onclick="report(5,'${caseInfo.id}','${caseInfo.caseNo}','view')">诉讼预案</button>
                <button class="butList defuelt" onclick="report(4,'${caseInfo.id}','${caseInfo.caseNo}','view')">结案报告</button>
            </c:if>
            <%--紧急代扣--%>
            <c:if test="${menuType == 2 && (caseUserRole.claims || caseUserRole.claimsManager)}">
                <button class="butList active" onclick="operate('${caseInfo.id}','1100',false)">紧急代扣</button>
                <button class="butList active" onclick="operate('${caseInfo.id}','11082',false)">编辑银行卡</button>
            </c:if>
            <%--客服中心-案件列表--%>
            <c:if test="${menuType == 53}">
                <c:if test="${caseInfo.orgUserId != null}">
                    <button class="butList active" onclick="caseCenterInfoAllotAdd('${caseInfo.id}','${caseInfo.gradationState}')">改派</button>
                </c:if>
                <button class="butList active" onclick="operate('${caseInfo.id}','5300',false)">分配机构</button>
                <%--<button class="butList active" onclick="operate('${dto.id}','5301',false)">分配工作室</button>--%>
                <button class="butList active" onclick="selectOperator('${caseInfo.id}','${caseInfo.operatorName}','${caseInfo.orgId}')">修改业务员</button>
                <c:if test="${caseUserRole.customerManager}">
                    <button class="butList active" onclick="operate('${caseInfo.id}','9999',true)">删除案件</button>
                    <c:if test="${caseInfo.isTestcase != 1}">
                        <button class="butList active" onclick="operate('${caseInfo.id}','9998',true)">转测试案件</button>
                    </c:if>
                </c:if>
            </c:if>
            <%--评估中心-待接收案件--%>
            <c:if test="${menuType == 5 && caseUserRole.assessor && caseInfo.gradationState == 2 && caseInfo.caseState == 1}">
                <button class="butList active" onclick="operate('${caseInfo.id}','1109',true)">接收</button>
                <button class="butList active" onclick="operate('${caseInfo.id}','1110',false)">拒绝</button>
            </c:if>
            <%--索赔中心-待接收案件--%>
            <c:if test="${menuType == 55 && caseUserRole.claims && caseInfo.gradationState == 3 && caseInfo.caseState == 1}">
                <button class="butList active" onclick="operate('${caseInfo.id}','1109',true)">接收</button>
                <button class="butList active" onclick="operate('${caseInfo.id}','1110',false)">拒绝</button>
            </c:if>
            <%--法务中心-待接收案件--%>
            <c:if test="${menuType == 555 && caseUserRole.legal && caseInfo.gradationState == 6 && caseInfo.caseState == 1}">
                <button class="butList active" onclick="operate('${caseInfo.id}','1109',true)">接收</button>
                <button class="butList active" onclick="operate('${caseInfo.id}','1110',false)">拒绝</button>
            </c:if>
            <%--评估审核--%>
            <%--<c:if test="${menuType == 15 && caseUserRole.assessorManager && caseInfo.listState == 2}">--%>
                <%--<button class="butList active" onclick="operate('${caseInfo.id}','1102',true)">审核通过</button>--%>
                <%--<button class="butList active" onclick="operate('${caseInfo.id}','1103',false)">退回</button>--%>
            <%--</c:if>--%>
            <%--<c:if test="${menuType == 15 && caseUserRole.riskSuper && caseInfo.listState == 4}">--%>
                <%--<button class="butList active" onclick="operate('${caseInfo.id}','1104',true)">审核通过</button>--%>
                <%--<button class="butList active" onclick="operate('${caseInfo.id}','1105',false)">退回</button>--%>
            <%--</c:if>--%>
            <%--保证保险--%>
            <%--<c:if test="${menuType == 20}">--%>
                <%--<c:if test="${caseInfo.listState == 12}">--%>
                    <%--<button class="butList active" onclick="operate('${caseInfo.id}','1107',false)">投保</button>--%>
                <%--</c:if>--%>
            <%--</c:if>--%>
            <%--保险费支出--%>
            <%--<c:if test="${menuType == 21}">--%>
                <%--<c:if test="${caseInfo.listState == 14}">--%>
                    <%--<button class="butList active" onclick="operate('${caseInfo.id}','11081',true)">保险费支付</button>--%>
                <%--</c:if>--%>
            <%--</c:if>--%>
            <%--贷款管理--%>
            <%--<c:if test="${menuType == 25}">--%>
                <%--<c:if test="${caseInfo.listState == 16}">--%>
                    <%--<button class="butList active" onclick="operate('${caseInfo.id}','1108',false)">放款确认</button>--%>
                    <%--<button class="butList active" onclick="operate('${caseInfo.id}','11082',false)">编辑银行卡</button>--%>
                <%--</c:if>--%>
            <%--</c:if>--%>
            <%--确认服务费--%>
            <c:if test="${menuType == 27 && (caseUserRole.assessor || caseUserRole.assessorManager)}">
                <c:if test="${caseInfo.defineState != 1}">
                    <button class="butList active" onclick="operate('${caseInfo.id}','11085',false)">确认服务费</button>
                </c:if>
            </c:if>
            <%--？？？？--%>
            <c:if test="${menuType == 104 && caseInfo.repay != 1 && caseInfo.handOutFlag == 2}" >
                <button class="butList active" onclick="haldleRepayCaseCenterInfo('${caseInfo.id}')">操作还款</button>
            </c:if>
            <%--？？？？--%>
            <c:if test="${menuType == 103}">
                <button class="butList active" onclick="updCaseCenterInfoConfirmRepay('${caseInfo.id}')">确认还款</button>
            </c:if>
            <%--跟踪--%>
            <c:if test="${menuType == 26 && caseInfo.assessFlowState != 2}">
                <c:if test="${dto.gradationState == 2}">
                    <button class="butList active" onclick="operate('${caseInfo.id}','2601',false)">提交状态</button>
                </c:if>
            </c:if>
            <c:if test="${menuType == 36 && caseInfo.claimFlowState != 2}">
                <c:if test="${dto.gradationState == 3}">
                    <button class="butList active" onclick="operate('${caseInfo.id}','3601',false)">提交状态</button>
                </c:if>
            </c:if>
            <c:if test="${menuType == 51 && caseInfo.legalFlowState != 2}">
                <c:if test="${dto.gradationState == 6}">
                    <button class="butList active" onclick="operate('${caseInfo.id}','5101',false)">提交状态</button>
                </c:if>
            </c:if>
            <c:if test="${menuType == 52 && caseInfo.customerFlowState != 2}">
            </c:if>
            <%--解约审核--%>
            <c:if test="${menuType == 56}">
                <c:if test="${caseUserRole.customer && caseInfo.listState == 3001}">
                    <button class="butList active" onclick="operate('${caseInfo.id}','3004',true)">通过审核</button>
                    <button class="butList active" onclick="operate('${caseInfo.id}','3003',false)">退回审核</button>
                </c:if>
                <c:if test="${caseUserRole.marketingManager && caseInfo.listState == 3004}">
                    <button class="butList active" onclick="operate('${caseInfo.id}','3002',true)">通过审核</button>
                    <button class="butList active" onclick="operate('${caseInfo.id}','3003',false)">退回审核</button>
                </c:if>
            </c:if>
            <button class="butList defuelt" style="position:absolute;right:50px;bottom: 0px;" onclick="popup('${caseInfo.id}','export')">导  出</button>
        </div>
        <div class="main-boy">
            <div class="contain">
                <div class="header">
                    <div class="stepTitle">
                        <div class="stepTitle-l">索赔进度</div>
                        <div class="stepTitle-r"  onclick="view(3,'${caseInfo.id}','${caseInfo.caseNo}','view')" style="cursor:pointer">查看详情</div>
                    </div>
                    <div class="step">
                        <div class="stepContent">
                            <input type="hidden" id="stepsSize" value="${caseInfo.caseStepsDTOs.size()}">
                            <c:forEach items="${caseInfo.caseStepsDTOs}" var="item">
                                <div <c:if test="${item.isBule}">class="bar s_active"</c:if> <c:if test="${!item.isBule}">class="bar"</c:if>>
                                    <div class="name">${item.stepName}</div>
                                    <div class="date"><fmt:formatDate value="${item.showTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="stepbg">
                            <c:forEach items="${caseInfo.caseStepsDTOs}" var="item" varStatus="indexs">
                                <c:if test="${indexs.index == 0}">
                                    <div <c:if test="${item.isBule}">class="index s_active"</c:if> <c:if test="${!item.isBule}">class="index"</c:if>>${indexs.index + 1}</div>
                                </c:if>
                                <c:if test="${indexs.index > 0}">
                                    <div <c:if test="${item.isBule}">class="line s_active"</c:if> <c:if test="${!item.isBule}">class="line"</c:if>></div>
                                    <div <c:if test="${item.isBule}">class="index s_active"</c:if> <c:if test="${!item.isBule}">class="index"</c:if>>${indexs.index + 1}</div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="header-r">
                    <div class="stepTitle2">
                        <div class="stepTitle2-l">案件跟踪</div>
                        <div class="stepTitle2-btn">
                            <%--评估中心 --案件跟踪--%>
                            <c:if test="${(menuType == 26 || menuType == 5||menuType == 10||menuType == 15||menuType == 11) && caseInfo.assessFlowState != 2}">
                                <div class="s_btn" onclick="follow('${caseInfo.id}','add','2101','PG')" style="cursor:pointer">添加跟踪</div>
                                <div class="s_btn" onclick="follow('${caseInfo.id}','stop','2100','PG')" style="cursor:pointer">结束跟踪</div>
                            </c:if>
                            <%--索赔中心 --案件跟踪--%>
                            <c:if test="${(menuType == 36 ||menuType == 30||menuType == 35||menuType == 111||menuType == 55) && caseInfo.claimFlowState != 2}">
                                <div class="s_btn" onclick="follow('${caseInfo.id}','add','2101','SP')" style="cursor:pointer">添加跟踪</div>
                                <div class="s_btn" onclick="follow('${caseInfo.id}','stop','2100','SP')" style="cursor:pointer">结束跟踪</div>
                            </c:if>
                            <%--诉讼中心 --案件跟踪--%>
                            <c:if test="${(menuType == 51||menuType == 555||menuType == 45||menuType == 50||menuType == 1111) && caseInfo.legalFlowState != 2}">
                                <div class="s_btn" onclick="follow('${caseInfo.id}','add','2101','SS')" style="cursor:pointer">添加跟踪</div>
                                <div class="s_btn" onclick="follow('${caseInfo.id}','stop','2100','SS')" style="cursor:pointer">结束跟踪</div>
                            </c:if>
                            <%--客服中心 --案件跟踪--%>
                            <c:if test="${(menuType == 52||menuType == 53) && caseInfo.customerFlowState != 2}">
                                <div class="s_btn" onclick="follow('${caseInfo.id}','add','2101','KF')" style="cursor:pointer">添加跟踪</div>
                                <div class="s_btn" onclick="follow('${caseInfo.id}','stop','2100','KF')" style="cursor:pointer">结束跟踪</div>
                            </c:if>
                        </div>
                    </div>
                    <div class="step2">
                        <c:if test="${caseFollows != null}">
                            <div class="step2-block">
                                <div class="date"><fmt:formatDate value="${item.followTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                                <div class="step2-block-r">
                                    <div class="s_title">
                                        <div class="index"></div>
                                        <div class="text">跟踪人：${caseFollows.followBy}
                                            【
                                            <c:if test="${caseFollows.gradationState  == 0}">业务员</c:if>
                                            <c:if test="${caseFollows.gradationState  == 1}">客服</c:if>
                                            <c:if test="${caseFollows.gradationState  == 2}">评估员</c:if>
                                            <c:if test="${caseFollows.gradationState  == 3}">索赔员</c:if>
                                            <c:if test="${caseFollows.gradationState  == 4}"></c:if>
                                            <c:if test="${caseFollows.gradationState  == 5}"></c:if>
                                            <c:if test="${caseFollows.gradationState  == 6}">诉讼员</c:if>】
                                        </div>
                                    </div>
                                    <div class="content">
                                        <div class="text">内容：${caseFollows.followDesc}</div>
                                        <div class="text">
                                            <c:if test="${caseFollows.followType != 2}">
                                                下一次跟踪时间：<fmt:formatDate value="${caseFollows.nextFollowTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="step2-block" id="step2-block-all">
                                <div class="date"></div>
                                <div class="step2-block-r">
                                    <div class="s_title title2">
                                        <div class="index"></div>
                                        <div class="s_btn2" onclick="popup('${caseInfo.id}','follow')">查看全部${caseFollowSize}条跟踪记录</div>
                                    </div>
                                    <div class="content">

                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${caseFollows==null}">
                            <div class="step2-block">
                                暂无跟踪记录
                            </div>
                        </c:if>

                    </div>
                </div>
                <div class="header-r2">
                    <div class="stepTitle2">
                        <div class="stepTitle2-l">作业人员</div>
                    </div>
                    <div class="step2">
                        <c:if test="${caseInfo.gradationState ==1 || caseInfo.gradationState ==3 || caseInfo.gradationState ==6}">
                            <div class="stepCurUser">当前经办人：
                                <c:if test="${caseInfo.gradationState ==1}">${caseInfo.orgUserName}【业务员】</c:if>
                                <c:if test="${caseInfo.gradationState ==3}">
                                    <c:if test="${caseInfo.claimState != 7 && caseInfo.claimState != 10 && caseInfo.claimState != 13}">
                                        ${caseInfo.orgUserName}【索赔员】
                                    </c:if>
                                    <c:if test="${caseInfo.claimState == 7 || caseInfo.claimState == 10 || caseInfo.claimState == 13}">
                                        【索赔主管】
                                    </c:if>
                                </c:if>
                                <c:if test="${caseInfo.gradationState ==6}">
                                    <c:if test="${caseInfo.claimState != 7 && caseInfo.claimState != 11 && caseInfo.claimState != 14}">
                                        ${caseInfo.orgUserName}【诉讼员】
                                    </c:if>
                                    <c:if test="${caseInfo.claimState == 7 || caseInfo.claimState == 11 ||caseInfo.claimState == 14}">
                                        【诉讼主管】
                                    </c:if>
                                </c:if>
                            </div>
                        </c:if>
                        <div class="stepUsers">
                            <div class="stepUser">业务员：${caseInfo.operatorName}</div>
                            <div class="stepUser">索赔员：${caseInfo.claimantName}</div>
                            <div class="stepUser">诉讼员：${caseInfo.legalUserName}</div>
                            <div class="stepUser">推广员：${caseInfo.salesmanName}</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="table-main">
                <div class="table-title">案件基本信息</div>
                <%--<button class="butList defuelt" onclick="popup('${caseInfo.id}','caseInfo')">编辑</button>--%>
                <c:if test="${caseUserRole.customer || caseUserRole.claims}">
                    <div class="stepTitle" style="justify-content: flex-end;">
                        <div class="stepTitle-r"  style="cursor:pointer" onclick="popup('${caseInfo.id}','caseInfo')">编 辑</div>
                    </div>
                </c:if>
                <table>
                    <tbody>
                    <tr>
                        <td>案件编号：${caseInfo.caseNo}</td>
                        <td>事故发生地：${caseEntrustInput.accidentProvince}${caseEntrustInput.accidentCity}${caseEntrustInput.accidentDistrict}${caseEntrustInput.accidentAddress}</td>
                        <td>处理交警队：${caseInfo.extend2.policeTeam}</td>
                        <td>委托人：${caseEntrustInput.injuredPerson}</td>
                    </tr>
                    <tr>
                        <td>联系电话：${caseEntrustInput.injuredTel}</td>
                        <td>身份证号：${caseInfo.extend2.idCard}</td>
                        <td>性别：
                            <c:if test="${caseInfo.extend2.sex==1}">男</c:if>
                            <c:if test="${caseInfo.extend2.sex==2}">女</c:if>
                        </td>
                        <td>年龄：
                            <c:if test="${caseInfo.extend2.age !=null}">${caseInfo.extend2.age} 周岁</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>婚姻情况：
                            <c:if test="${caseInfo.extend2.maritalStatus==1}">已婚</c:if>
                            <c:if test="${caseInfo.extend2.maritalStatus==2}">离异</c:if>
                            <c:if test="${caseInfo.extend2.maritalStatus==3}">丧偶</c:if>
                            <c:if test="${caseInfo.extend2.maritalStatus==4}">未婚</c:if>
                        </td>
                        <td>工作单位：${caseInfo.extend2.workUnit}</td>
                        <td>入职时间：<fmt:formatDate value="${caseInfo.extend2.entryTime}" pattern="yyyy-MM-dd" /></td>
                        <td>收入情况：
                            <c:if test="${caseInfo.extend2.wages != null}">
                                    ${caseInfo.extend2.wages} 元/月
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>是否缴税：
                            <c:if test="${caseInfo.extend2.taxCertificate==1}">是</c:if>
                            <c:if test="${caseInfo.extend2.taxCertificate==2}">否</c:if>
                        </td>
                        <td>是否缴纳社保：
                            <c:if test="${caseInfo.extend2.paySocialSecurity==0}">有</c:if>
                            <c:if test="${caseInfo.extend2.paySocialSecurity==1}">无</c:if>
                        </td>
                        <td>工资发放形式：
                            <c:if test="${caseInfo.extend2.bankInfo==1}">打卡</c:if>
                            <c:if test="${caseInfo.extend2.bankInfo==2}">现金</c:if>
                        </td>
                        <td>单位地址：${caseInfo.extend2.unitProvince}${caseInfo.extend2.unitCity}${caseInfo.extend2.unitDistrict}${caseInfo.extend2.unitAddress}</td>
                    </tr>
                    <tr>
                        <td>居住地址：${caseInfo.extend2.liveProvince}${caseInfo.extend2.liveCity}${caseInfo.extend2.liveDistrict}${caseInfo.extend2.liveAddress}</td>
                        <td>居住时间：${caseInfo.extend2.liveTime}<c:if test="${caseInfo.extend2.liveTimeType==1}">年</c:if><c:if test="${caseInfo.extend2.liveTimeType==2}">个月</c:if>
                        </td>
                        <td>户籍地址：${caseInfo.extend2.domicileProvince}${caseInfo.extend2.domicileCity}${caseInfo.extend2.domicileDistrict}${caseInfo.extend2.domicileAddress}</td>
                        <td>户籍性质：
                            <c:if test="${caseInfo.extend2.domicile==1}">农业</c:if>
                            <c:if test="${caseInfo.extend2.domicile==2}">非农业</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>拆迁/征地等情况：
                            <c:if test="${caseInfo.extend2.landExpropriation==1}">有</c:if>
                            <c:if test="${caseInfo.extend2.landExpropriation==2}">无</c:if>
                        </td>
                        <td <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState <4}"> colspan="5" </c:if>>就诊医院：${caseInfo.extend2.visitingHospital}</td>
                        <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=4}">
                            <td>受伤部位：${apply.injuredPartStr}</td>
                            <td>治疗方式：
                                <c:if test="${apply.treatmentMethod==1}">门诊</c:if>
                                <c:if test="${apply.treatmentMethod==2}">急诊留观</c:if>
                                <c:if test="${apply.treatmentMethod==3}">住院</c:if>
                                <c:if test="${apply.treatmentMethod==4}">未就诊</c:if>
                            </td>
                        </c:if>
                    </tr>
                    <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=3}">
                        <tr>
                            <td colspan="8">伤情诊断：${estimateReport.injuryDiagnose}</td>
                        </tr>
                    </c:if>
                    <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=3}">
                        <tr>
                            <td <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState < 7}">colspan="7" </c:if>>伤残等级：
                                ${estimateReport.invalidismGradeStr}
                            </td>
                            <c:if test="${caseInfo.extend2.processState >=7}">
                                <td>是否已做鉴定：
                                    <c:if test="${claim.caseMediationClaim.determineType==0}">否</c:if>
                                    <c:if test="${claim.caseMediationClaim.determineType==1}">是</c:if>
                                </td>
                                <td>肇事方：${claim.caseMediationClaim.partyName}</td>
                                <td>肇事方电话：${claim.caseMediationClaim.partyTel}</td>
                            </c:if>
                        </tr>
                    </c:if>
                    <tr>
                        <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=7}">
                            <td>车牌号码：${claim.caseMediationClaim.cardNumber}</td>
                        </c:if>
                        <td>出险时间：
                            <fmt:formatDate value="${caseEntrustInput.accidentTime}" pattern="yyyy-MM-dd" />
                        </td>
                        <td>交强险保险公司：${caseInfo.extend2.insuranceCompany}</td>
                        <td <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState <7}">colspan="3" </c:if>>商业险保险公司：${caseInfo.extend2.insuranceCompany2}</td>
                    </tr>
                    <tr>
                        <td>是否多车事故：
                            <c:if test="${caseInfo.extend2.accidentType==1}">单方事故</c:if>
                            <c:if test="${caseInfo.extend2.accidentType==2}">双方事故</c:if>
                            <c:if test="${caseInfo.extend2.accidentType==3}">多方事故</c:if>
                        </td>
                        <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=4}">
                            <td>伤者交通状态：
                                <c:if test="${apply.myStatus == 1}">

                                    <c:if test="${apply.otherTarfficStatus==1}">机动车</c:if>
                                    <c:if test="${apply.otherTarfficStatus==2}">非机动车</c:if>
                                    <%--<c:if test="${apply.otherTarfficStatus==3}">行人</c:if>--%>
                                </c:if>
                                <c:if test="${apply.myStatus == 2}">
                                    <c:if test="${apply.myTarfficStatus==1}">机动车</c:if>
                                    <c:if test="${apply.myTarfficStatus==2}">非机动车</c:if>
                                    <%--<c:if test="${apply.myTarfficStatus==3}">行人</c:if>--%>
                                </c:if>
                            </td>
                            <td>肇事方交通状态：
                                <c:if test="${apply.myStatus == 2}">
                                    <c:if test="${apply.otherTarfficStatus==1}">机动车</c:if>
                                    <c:if test="${apply.otherTarfficStatus==2}">非机动车</c:if>
                                    <%--<c:if test="${apply.otherTarfficStatus==3}">行人</c:if>--%>
                                </c:if>
                                <c:if test="${apply.myStatus == 1}">
                                    <c:if test="${apply.myTarfficStatus==1}">机动车</c:if>
                                    <c:if test="${apply.myTarfficStatus==2}">非机动车</c:if>
                                    <%--<c:if test="${apply.myTarfficStatus==3}">行人</c:if>--%>
                                </c:if>
                            </td>
                        </c:if>
                        <td <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState <4}"> colspan="5" </c:if>>委托人事故责任：
                            <c:if test="${caseInfo.extend2.ourResponsibilities==1}">全责</c:if>
                            <c:if test="${caseInfo.extend2.ourResponsibilities==2}">主责</c:if>
                            <c:if test="${caseInfo.extend2.ourResponsibilities==3}">同责</c:if>
                            <c:if test="${caseInfo.extend2.ourResponsibilities==4}">次责</c:if>
                            <c:if test="${caseInfo.extend2.ourResponsibilities==5}">无责</c:if>
                            <c:if test="${caseInfo.extend2.ourResponsibilities==6}">责任无法认定</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>肇事方赔偿比例：${caseInfo.extend2.responsiblePartyPayRatio}%
                        </td>
                        <td colspan="5">肇事方有无免责情形：
                            <c:if test="${claim.caseMediationClaim.disclaimerType==0}">无</c:if>
                            <c:if test="${claim.caseMediationClaim.disclaimerType==1}">有</c:if>
                        </td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td>当前经办人：${caseInfo.orgUserName}</td>--%>
                        <%--<td>推广员：${caseInfo.salesmanName}</td>--%>
                        <%--<td>所属机构：${caseInfo.orgName}</td>--%>
                        <%--<td>案件状态：${caseInfo.listStateName}</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>业务员：${caseInfo.operatorName}</td>--%>
                        <%--<td>评估员：${caseInfo.assessName}</td>--%>
                        <%--<td>索赔员：${caseInfo.claimantName}</td>--%>
                        <%--<td>诉讼员：${caseInfo.legalUserName}</td>--%>
                    <%--</tr>--%>
                </table>
            </div>


<c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=6}">
            </br>
            <div class="table-main">
                <div class="table-title">产品类型</div>
                <table>
                    <tbody>
                    <tr>
                        <td colspan="7">产品类型：
                            <c:if test="${caseInfo.type==1}">乐赔宝</c:if>
                            <c:if test="${caseInfo.type==2}">索赔通</c:if>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            </br>
            <div class="table-main">
                <div class="table-title">收费方式</div>
                <table>
                    <tbody>
                    <c:if test="${caseInfo.type==1}">
                        <tr>
                            <td>产品价格：${payEstimateInquiry.agentServiceFee}</td>
                            <td colspan="7">服务费金额：${payEstimateInquiry.agentServiceFee}</td>
                        </tr>
                    </c:if>
                    <c:if test="${caseInfo.type==2}">
                        <c:if test="${payEstimateInquiry.calculationType == 1}">
                            <tr>
                                <td>服务费方式：固定收费</td>
                                <td>服务费金额：${payEstimateInquiry.agentServiceFee}</td>
                                <td colspan="3">预收金额：${payEstimateInquiry.realDeFee}</td>
                            </tr>
                        </c:if>
                        <c:if test="${payEstimateInquiry.calculationType == 2}">
                            <tr>
                                <td>服务费方式：比例收费</td>
                                <td>比例收费基数：
                                    <c:if test="${payEstimateInquiry.rateBaseType==1}">赔偿金额</c:if>
                                    <c:if test="${payEstimateInquiry.rateBaseType==2}">赔偿款-医疗费</c:if>
                                    <c:if test="${payEstimateInquiry.rateBaseType==3}">伤残赔偿金+精神损失费</c:if>
                                </td>
                                <td>收费比例：${payEstimateInquiry.serviceRate}</td>
                                <td>服务费金额：${payEstimateInquiry.agentServiceFee}</td>
                            </tr>
                            <tr>
                                <td colspan="7">预收金额：${payEstimateInquiry.realDeFee}</td>
                            </tr>
                        </c:if>
                    </c:if>
                    </tbody>
                </table>
            </div>
</c:if>


<c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=2}">
            </br>
            <div class="table-main">
                <div class="table-title">签约指导</div>
                <table>
                <tbody>
                <tr>
                    <td colspan="8" style="font-weight:bold">基本信息</td>
                </tr>
                <tr>
                    <td>委托人：${caseEntrustInput.injuredPerson}</td>
                    <td>性别：
                        <c:if test="${caseInfo.extend2.sex==1}">男</c:if>
                        <c:if test="${caseInfo.extend2.sex==2}">女</c:if>
                    </td>
                    <td>年龄：${caseInfo.extend2.age}</td>
                    <td>婚姻情况：
                        <c:if test="${caseInfo.extend2.maritalStatus==1}">已婚</c:if>
                        <c:if test="${caseInfo.extend2.maritalStatus==2}">离异</c:if>
                        <c:if test="${caseInfo.extend2.maritalStatus==3}">丧偶</c:if>
                        <c:if test="${caseInfo.extend2.maritalStatus==4}">未婚</c:if>
                    </td>
                </tr>
                <tr>
                    <td colspan="8" style="font-weight:bold">户籍信息</td>
                </tr>
                <tr>
                    <td>户籍性质：
                        <c:if test="${caseInfo.extend2.domicile==1}">农业</c:if>
                        <c:if test="${caseInfo.extend2.domicile==2}">非农业</c:if>
                    </td>
                    <td colspan="5">户籍地址：${caseInfo.extend2.domicileProvince}${caseInfo.extend2.domicileCity}${caseInfo.extend2.domicileDistrict}${caseInfo.extend2.domicileAddress}
                    </td>
                </tr>
                <tr>
                    <td colspan="8" style="font-weight:bold">工作情况</td>
                </tr>
                <tr>
                    <td>工作是否一年以上：
                        <c:if test="${caseInfo.extend2.isJobSatisfy==1}">是</c:if>
                        <c:if test="${caseInfo.extend2.isJobSatisfy==2}">否</c:if>
                    </td>
                    <td>是否公务员、事业单位：
                        <c:if test="${caseInfo.extend2.isServant==1}">是</c:if>
                        <c:if test="${caseInfo.extend2.isServant==2}">否</c:if>
                    </td>
                    <td>有无劳动合同：
                        <c:if test="${caseInfo.extend2.laborContract==1}">有</c:if>
                        <c:if test="${caseInfo.extend2.laborContract==2}">无</c:if>
                    </td>
                    <td>有无银行流水：
                        <c:if test="${caseInfo.extend2.bankInfo==1}">有</c:if>
                        <c:if test="${caseInfo.extend2.bankInfo==2}">无</c:if>
                    </td>
                </tr>
                <tr>
                    <td>有无纳税证明：
                        <c:if test="${caseInfo.extend2.taxCertificate==1}">有</c:if>
                        <c:if test="${caseInfo.extend2.taxCertificate==2}">无</c:if>
                    </td>
                    <td>有无误工证明：
                        <c:if test="${caseInfo.extend2.workCertificate==1}">有</c:if>
                        <c:if test="${caseInfo.extend2.workCertificate==2}">无</c:if>
                    </td>
                    <td>工资：${caseInfo.extend2.wages}</td>
                    <td>其他：${caseInfo.extend2.otherWorkInfo}</td>
                </tr>
                <tr>
                    <td colspan="8" style="font-weight:bold">居住情况</td>
                </tr>
                <tr>
                    <td>居住区域：
                        <c:if test="${caseInfo.extend2.habitation==1}">城镇</c:if>
                        <c:if test="${caseInfo.extend2.habitation==2}">农村</c:if>
                    </td>
                    <td>是否城镇居住一年以上：
                        <c:if test="${caseInfo.extend2.isTownSatisfy==1}">是</c:if>
                        <c:if test="${caseInfo.extend2.isTownSatisfy==2}">否</c:if>
                    </td>
                    <td>有无居住证/暂住证：
                        <c:if test="${caseInfo.extend2.residencePermit==1}">有</c:if>
                        <c:if test="${caseInfo.extend2.residencePermit==2}">无</c:if>
                    </td>
                    <td>是否征地/拆迁：
                        <c:if test="${caseInfo.extend2.landExpropriation==1}">是</c:if>
                        <c:if test="${caseInfo.extend2.landExpropriation==2}">否</c:if>
                    </td>
                </tr>
                <tr>
                    <td colspan="8">其他：${caseInfo.extend2.otherLive}</td>
                </tr>
                <tr>
                    <td colspan="8" style="font-weight:bold">事故责任</td>
                </tr>
                <tr>
                    <td>事故类型：
                        <c:if test="${caseInfo.extend2.accidentType==1}">单方事故</c:if>
                        <c:if test="${caseInfo.extend2.accidentType==2}">双发事故</c:if>
                        <c:if test="${caseInfo.extend2.accidentType==3}">多方事故</c:if>
                    </td>
                    <td>对方是否机动车：
                        <c:if test="${caseInfo.extend2.isMotorVehicle==1}">是</c:if>
                        <c:if test="${caseInfo.extend2.isMotorVehicle==2}">否</c:if>
                    </td>
                    <td>对方是否有人伤：
                        <c:if test="${caseInfo.extend2.isHumanInjury==1}">有</c:if>
                        <c:if test="${caseInfo.extend2.isHumanInjury==2}">无</c:if>
                    </td>
                    <td>对方是否有车物损：
                        <c:if test="${caseInfo.extend2.isVehicleDamage==1}">是</c:if>
                        <c:if test="${caseInfo.extend2.isVehicleDamage==2}">否</c:if>
                    </td>
                </tr>
                <tr>
                    <td>我方责任：
                        <c:if test="${caseInfo.extend2.ourResponsibilities==1}">全责</c:if>
                        <c:if test="${caseInfo.extend2.ourResponsibilities==2}">主责</c:if>
                        <c:if test="${caseInfo.extend2.ourResponsibilities==3}">同责</c:if>
                        <c:if test="${caseInfo.extend2.ourResponsibilities==4}">次责</c:if>
                        <c:if test="${caseInfo.extend2.ourResponsibilities==5}">无责</c:if>
                        <c:if test="${caseInfo.extend2.ourResponsibilities==6}">责任无法认定</c:if>
                    </td>
                    <td>本方交通方式：
                        <c:if test="${caseInfo.extend2.ourTransportation==1}">机动车</c:if>
                        <c:if test="${caseInfo.extend2.ourTransportation==2}">非机动车</c:if>
                    </td>
                    <td>处理交警队：${caseInfo.extend2.policeTeam}</td>
                    <td>事故责任其他：${caseInfo.extend2.otherAccidentLiability}</td>
                </tr>
                <tr>
                    <td colspan="8" style="font-weight:bold">承保信息</td>
                </tr>
                <tr>
                    <td>有无保险：
                        <c:if test="${caseInfo.extend2.acceptInsurance==1}">有</c:if>
                        <c:if test="${caseInfo.extend2.acceptInsurance==2}">无</c:if>
                    </td>
                    <td>交强险保险公司：${caseInfo.extend2.insuranceCompany}</td>
                    <td>商业险保险公司：${caseInfo.extend2.insuranceCompany2}</td>
                    <td>承保地：${caseInfo.extend2.underwritingPlace}</td>
                </tr>
                <tr>
                    <td>商业险：${caseInfo.extend2.commercialInsurance}</td>
                    <td colspan="6">有无不计免赔：
                        <c:if test="${caseInfo.extend2.excludingDeductible==1}">有</c:if>
                        <c:if test="${caseInfo.extend2.excludingDeductible==2}">无</c:if>
                    </td>
                </tr>
                <tr>
                    <td colspan="8" style="font-weight:bold">其他信息</td>
                </tr>
                <tr>
                    <td>伤情诊断：${caseInfo.extend2.injuryDiagnosis}</td>
                    <td>就诊医院：${caseInfo.extend2.visitingHospital}</td>
                    <td>是否同意鉴定：
                        <c:if test="${caseInfo.extend2.isAgreeAppraisal==1}">是</c:if>
                        <c:if test="${caseInfo.extend2.isAgreeAppraisal==2}">否</c:if>
                    </td>
                    <td>是否同意诉讼：
                        <c:if test="${caseInfo.extend2.isAgreeLitigation==1}">是</c:if>
                        <c:if test="${caseInfo.extend2.isAgreeLitigation==2}">否</c:if>
                    </td>
                </tr>
                <tr>
                    <td colspan="8">客户意向：${caseInfo.extend2.customerIntention}</td>
                </tr>
                <tr>
                    <td colspan="8" style="font-weight:bold">指导结果</td>
                </tr>
                <tr>
                    <td>赔偿标准分类：
                        <c:if test="${caseSignGuidance.compensatePlan==1}">A</c:if>
                        <c:if test="${caseSignGuidance.compensatePlan==2}">B</c:if>
                        <c:if test="${caseSignGuidance.compensatePlan==3}">C</c:if>
                        <c:if test="${caseSignGuidance.compensatePlan==4}">D</c:if>
                    </td>
                    <td>客户分类：
                        <c:if test="${caseSignGuidance.customerType==1}">A</c:if>
                        <c:if test="${caseSignGuidance.customerType==2}">B</c:if>
                        <c:if test="${caseSignGuidance.customerType==3}">C</c:if>
                        <c:if test="${caseSignGuidance.customerType==4}">D</c:if>
                    </td>
                    <td>案件产品归类：
                        <c:if test="${caseSignGuidance.signProduct==1}">乐赔宝</c:if>
                        <c:if test="${caseSignGuidance.signProduct==2}">索赔通</c:if>
                    </td>
                </tr>
                <tr>
                    <td colspan="8">签约指导意见：${caseSignGuidance.signGuidance}</td>
                </tr>
                </tbody>
                </table>
            </div>
            </c:if>

            <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=3}">
            </br>
            <div class="table-main">
                <div class="table-title">伤残预估</div>
                <table>
                    <tbody>
                    <tr>
                        <td>预估时间：<fmt:formatDate value="${estimateReport.applyTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td>治疗方式：
                            <c:if test="${estimateReport.isOperation == 0}">非手术</c:if>
                            <c:if test="${estimateReport.isOperation == 1}">手术</c:if>
                        </td>
                        <td colspan="4">备注：${estimateReport.reportDesc}</td>
                    </tr>
                    <tr>
                        <td>评残依据：${estimateReport.reportBasis}</td>
                        <td>伤情诊断：${estimateReport.injuryDiagnose}</td>
                    </tr>
                    <tr>
                        <td colspan="8">预估伤残等级：${estimateReport.invalidismGradeStr}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            </c:if>

            <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=4}">
            </br>
            <div class="table-main3">
                <div class="table-title3">测算报告</div>
                <table>
                    <div class="form-group">
                        事故责任:
                        <c:if test="${apply.myAccidentLiability == 1}">全部责任</c:if>
                        <c:if test="${apply.myAccidentLiability == 2}">主要责任</c:if>
                        <c:if test="${apply.myAccidentLiability == 3}">同等责任</c:if>
                        <c:if test="${apply.myAccidentLiability == 4}">次要责任</c:if>
                        <c:if test="${apply.myAccidentLiability == 5}">无责任</c:if>
                        <c:if test="${apply.myAccidentLiability == 6}">责任无法认定</c:if>
                    </div>
                    <tr>
                        <th width="100">赔偿项目名称</th>
                        <th width="150">实际损失</th>
                        <th width="150">实际损失审核金额</th>
                        <th width="150">交强险可赔偿金额</th>
                        <th width="150">商业险可赔偿金额</th>
                        <th width="150">肇事方可赔偿金额</th>
                    </tr>

                    <tbody class="class-list">
                    <c:forEach items="${paymentEstimateReportDtos}" var="item" varStatus="s">
                        <tr>
                            <td>${item.paymentProject}</td>
                            <td>${item.medicalFee}</td>
                            <c:if test="${s.isLast()}">
                                <c:if test="${item.checkMedicalFee!=null}">
                                    <td>${item.checkMedicalFee}</td>
                                </c:if>
                                <c:if test="${item.checkMedicalFee==null}">
                                    <td>${item.medicalFee}</td>
                                </c:if>

                            </c:if>
                            <c:if test="${!s.isLast()}">
                                <c:if test="${item.checkMedicalFee!=null}">
                                    <td>${item.checkMedicalFee}</td>
                                </c:if>
                                <c:if test="${item.checkMedicalFee==null}">
                                    <td>${item.medicalFee}</td>
                                </c:if>
                            </c:if>
                            <td>${item.compulsoryInsuranceFee}</td>
                            <td>${item.commercialInsuranceFee}</td>
                            <td>${item.causeTroubleFee}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            </c:if>

            <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=5}">
            </br>
            <div class="table-main">
                <div class="table-title">评估服务费</div>
                <table>
                    <tbody>
                    <tr>
                        <td colspan="8">服务费金额：${payEstimateInquiry.agentServiceFee}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            </c:if>

            <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=7}">
            </br>
            <div class="table-main">
                <div class="table-title">索赔指导</div>
                <table>
                    <tbody>
                    <tr>
                        <td  colspan="8">伤残情况：${caseClaimGuidance.disability}</td>
                    </tr>
                    <tr>
                        <td colspan="8">户籍情况：${caseClaimGuidance.domicile}</td>
                    </tr>
                    <tr>
                        <td colspan="8">误工费情况：${caseClaimGuidance.delayWork}</td>
                    </tr>
                    <tr>
                        <td colspan="8">事故性质情况：${caseClaimGuidance.accidentProperty}</td>
                    </tr>
                    <tr>
                        <td colspan="8">保险责任情况：${caseClaimGuidance.insuranceLiability}</td>
                    </tr>
                    <tr>
                        <td colspan="8">其他：${caseClaimGuidance.other}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            </br>
            <div class="table-main">
                <div class="table-title">索赔预案</div>
                <table>
                    <tbody>
                    <tr>
                        <td>索赔员：${caseInfo.claimantName}</td>
                        <td>索赔受理时间：<fmt:formatDate value="${caseInfo.claimantDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td colspan="4">适用标准：
                            <c:if test="${claim.caseMediationClaim.appStandType == null ||claim.caseMediationClaim.appStandType == ''}">未知</c:if>
                            <c:if test="${claim.caseMediationClaim.appStandType == 1}">城镇</c:if>
                            <c:if test="${claim.caseMediationClaim.appStandType == 2}">农村</c:if>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            </br>
            <div class="table-main2">
                <div class="table-title2">赔偿预案调解金额</div>
                <table>
                    <tr>
                        <th width="100">赔偿项目</th>
                        <th width="150">预案金额</th>
                        <th width="150">预案审核</th>
                        <th width="150">核损依据</th>
                    </tr>
                    <tbody>
                    <c:forEach items="${claimReports}" var="item">
                        <tr class="height-1" >
                            <td class="back-color-2 border-2 row-20 padding-2">${item.projectName}</td>
                            <td class="border-2 row-26 padding-2" colspan="1">${item.opinionMoney}</td>
                            <td class="border-2 row-26 padding-2" colspan="1">${item.auditingMoney}</td>
                            <td class="border-2 row-26 padding-2" colspan="1">${item.checkBasis}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tr class="height-1">
                        <td class="padding-2 back-color-2 border-2 row-20">交强险：${claim.caseMediationClaim.cpsMoney}</td>
                        <td class="padding-2 back-color-2 border-2 row-20">商业三者险：${claim.caseMediationClaim.cocMoney}</td>
                    </tr>
                    <tr class="height-1">
                        <td class="padding-2 back-color-2 border-2 row-20">案件备注：${claim.caseMediationClaim.caseDesc}</td>
                    </tr>
                </table>
            </div>
            </c:if>
            <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=7 && caseInfo.legalUserId !=null}">
            </br>
            <div class="table-main">
                <div class="table-title">诉讼预案</div>
                <table>
                    <tbody>
                    <tr>
                        <td>诉讼员：${caseInfo.legalUserName}</td>
                        <td>诉讼受理时间：<fmt:formatDate value="${caseInfo.legalDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td colspan="4">适用标准：
                            <c:if test="${claimLegal.caseMediationClaim.appStandType == null ||claimLegal.caseMediationClaim.appStandType == ''}">未知</c:if>
                            <c:if test="${claimLegal.caseMediationClaim.appStandType == 1}">城镇</c:if>
                            <c:if test="${claimLegal.caseMediationClaim.appStandType == 2}">农村</c:if>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            </br>
            <div class="table-main2">
                <div class="table-title2">赔偿预案诉讼金额</div>
                <table>
                    <tr>
                        <th width="100">赔偿项目</th>
                        <th width="150">预案金额</th>
                        <th width="150">预案审核</th>
                        <th width="150">核损依据</th>
                    </tr>
                    <c:forEach items="${claimReportLegals}" var="item">
                        <tr class="height-1" >
                            <td class="back-color-2 border-2 row-20 padding-2">${item.projectName}</td>
                            <td class="border-2 row-26 padding-2" colspan="1">${item.opinionMoney}</td>
                            <td class="border-2 row-26 padding-2" colspan="1">${item.auditingMoney}</td>
                            <td class="border-2 row-26 padding-2" colspan="1">${item.checkBasis}</td>
                        </tr>
                    </c:forEach>
                    <tr class="height-1">
                        <td class="padding-2 back-color-2 border-2 row-20">交强险：${claimLegal.caseMediationClaim.cpsMoney}</td>
                        <td class="padding-2 back-color-2 border-2 row-20">商业险：${claimLegal.caseMediationClaim.cocMoney}</td>
                    </tr>
                    <tr class="height-1">
                        <td class="padding-2 back-color-2 border-2 row-20">案件备注：${claimLegal.caseMediationClaim.caseDesc}</td>
                    </tr>
                </table>
            </div>
            </c:if>

        </div>
    </div>

</body>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>

   /**
   *
    * @param id        案件中心ID
    * @param btnCode    按钮CODE,与list_state一一对应
    * @param ajax       是否ajax请求,  true 弹出提示框   false 弹出界面
    */
   function operate(id,btnCode,ajax){
       var height = 400,width = 800;
       if(!validate(btnCode)){
           return;
       }
       if(ajax){
           var url = "${ctx}/case/center/operate",param = {"id":id,"btnCode":btnCode};
           if(confirm('是否确认？')){
               ajaxSubmit(url,param,function(v,e,p){
                   alert(e.data.msg);
                   if(btnCode == '9999'){
                       reloadParent();
                   }else{
                       location.reload();
                   }
               })
           }
       }else{
           var title = null,url = null;
           if(btnCode == '1100'){
               title = '紧急代扣';
               url = "${ctx}/case/center/pass?id="+id+"&btnCode="+btnCode
           }else if(btnCode == '1103' || btnCode == '1105' || btnCode == '11061' || btnCode == '1110'
                   || btnCode == '1113' || btnCode == '1116' || btnCode == '1120'
                   || btnCode == '6660202' || btnCode == '3001' || btnCode == '3003'
                    || btnCode == 'claim_yuan_veto' || btnCode == 'claim_jiean_report_veto' || btnCode == 'claim_jiean_veto'
                    || btnCode == 'legal_yuan_veto'|| btnCode == 'legal_jiean_report_veto'|| btnCode == 'legal_jiean_veto'){
               title = '原因';
               url = '${ctx}/case/center/back?id='+id+"&btnCode="+btnCode;
           }else if(btnCode == '1107'){
               height = 200;
               width = 500;
               title = '投保';
               url = '${ctx}/case/center/insured?id='+id+"&btnCode="+btnCode;
           }else if(btnCode == '1108'){
               title = '放款确认';
               url = "${ctx}/case/center/pass?id="+id+"&btnCode="+btnCode;
           }else if(btnCode == '1117' || btnCode == 'claim_fuwufei' || btnCode == 'legal_fuwufei'){
               height = 500;
               width = 800;
               title = '收取服务费';
               url = '${ctx}/case/center/start?id='+id+"&passType=passSuning&btnCode="+btnCode;
           }else if(btnCode == '11082'){
               title = '编辑银行卡';
               url = "${ctx}/case/center/editBankCardInfo?caseId="+id+"&btnCode="+btnCode;
           }else if(btnCode == '5300'){
               height = 600;
               width = 1000;
               title = '分配机构';
               url = "${ctx}/case/center/selectOrgInfo?caseId="+id+"&btnCode="+btnCode;
           }else if(btnCode == '5301'){
               height = 600;
               width = 1000;
               title = '分配工作室';
               url = "";
               return;
           }else if(btnCode == '11085'){
               height = 200;
               width = 500;
               title = '确认服务费';
               url = '${ctx}/case/center/okServiceFee?id='+id+"&btnCode="+btnCode;
           }else if(btnCode == '2601' || btnCode == '3601' || btnCode == '5101'){
               //评估阶段提交状态
               title = '提交案件状态';
               url = '${ctx}/case/center/commitState?id='+id+"&btnCode="+btnCode;
           }else if(btnCode == '77702' || btnCode == 'claim_jianding' || btnCode == 'legal_jianding'){
               height = 200;
               width = 500;
               title = '鉴定';
               url = '${ctx}/case/center/listStateSelect?id='+id+"&btnCode="+btnCode;
           }else if(btnCode == '2401' || btnCode == 'claim_tiaojie'){
               height = 200;
               width = 500;
               title = '调解';
               url = '${ctx}/case/center/listStateSelect?id='+id+"&btnCode="+btnCode;
           }else if(btnCode == '1114'){
               height = 200;
               width = 500;
               title = '结案';
               url = '${ctx}/case/center/listStateSelect?id='+id+"&btnCode="+btnCode;
           }else if(btnCode == '666020101'){
               height = 300;
               width = 600;
               title = '立案';
               url = '${ctx}/case/center/listStateSelect?id='+id+"&btnCode="+btnCode;
           }else if(btnCode == 'legal_kaiting_start'){
               height = 300;
               width = 600;
               title = '新增开庭';
               url = '${ctx}/case/center/listStateSelect?id='+id+"&btnCode="+btnCode;
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
   function validate(btnCode){
       if(btnCode == 1100 || btnCode == 1108){
           //验证银行卡信息是否已存在
       }else if(btnCode == 1101){
           //验证公估报告 评估报告是否已填写
           if('${caseInfo.assessmentReportId}' == ''){
               alert('公估报告未填写');return false;
           }
           if('${caseInfo.riskReportId}' == ''){
               alert('评估报告未填写');return false;
           }
       }else if(btnCode == 1111){
           //验证索赔预案是否已填写
           if('${caseInfo.mediationReportId}' == ''){
               alert('索赔预案未填写');return false;
           }
       }else if(btnCode == 1114){
           //验证结案报告是否已填写
           if('${caseInfo.closeReportId}' == ''){
               alert('结案报告未填写');return false;
           }
       }else if(btnCode == 11112){
           //诉讼预案是否已填写
       }
       return true;
   }
   function view(type,caseId,caseNo,op){
       if(type == 1){
           viewEstimateInquiry(caseId)
       }else if(type ==2){
           selectFileMid(caseNo,caseId);
       }else if(type ==3){
           selectCaseDetails('${caseInfo.type}','${caseInfo.caseId}',caseNo,1);
       }else if(type ==4){
           selectReport('${caseInfo.id}',caseNo);
       }else if(type ==5){
           updateReson('${caseInfo.id}',caseNo);
       }
   }
   function report(type,caseId,caseNo,op){
        var title = null,url = null;
        if(type == 1){
            title = "公估报告";
            url = "${ctx}/case/report/editAssessmentReport?caseId="+caseId+"&caseNo="+caseNo+"&op="+op;
        }else if(type == 2){
            title = "评估报告";
            url = "${ctx}/case/report/editRiskControl?caseId="+caseId+"&caseNo="+caseNo+"&op="+op;
        }else if(type == 3){
            title = "索赔预案";
            url = "${ctx}/case/caseMediation?caseId="+caseId+"&caseNo="+caseNo+"&op="+op+"&type="+type;
        }else if(type == 4){
            title = "结案报告";
            url = "${ctx}/case/report/editCloseReport?caseId="+caseId+"&caseNo="+caseNo+"&op="+op+"&type="+type;
        }else if(type == 5){
            title = "诉讼预案";
            url = "${ctx}/case/report/caseMediationLegal?caseId="+caseId+"&caseNo="+caseNo+"&op="+op+"&type="+type;
        }else if(type == 33){
            //新页面
            title = "索赔预案";
            url = "${ctx}/case/caseMediation?caseId="+caseId+"&caseNo="+caseNo+"&op="+op+"&type="+type;
        }else if(type == 55){
            //新页面
            title = "诉讼预案";
            url = "${ctx}/case/report/caseMediationLegal?caseId="+caseId+"&caseNo="+caseNo+"&op="+op+"&type="+type;
        }else if(type == 44){
            //新页面
            title = "结案报告";
            url = "${ctx}/case/report/editCloseReport?caseId="+caseId+"&caseNo="+caseNo+"&op="+op+"&type="+type;
        }
        openDialog({
            frame:true,
            title:title,
            height:650,
            width:1000,
            url:url
        });
    }
   function commitState(){

   }
   function follow(id,type,btnCode,choose){
       openDialog({
           frame: true,
           title: "添加跟踪记录",
           height: 450,
           width: 650,
           url: "${ctx}/caseCenterInfoFollow/caseCenterInfoFollowAdd?caseId="+ id+"&choose="+choose+"&type="+type
       });
   }
   function viewEstimateInquiry(caseId){
       openDialog({
           frame:true,
           title:"查看报价",
           height:600,
           width:1000,
           url:"${ctx}/case/center/viewEstimateInquiry?id="+caseId
       });
   }
   function selectFileMid(caseNo,caseId){
       openDialog({
           frame:true,
           title:"查看单证",
           height:650,
           width:1500,
           url:"${ctx}/case/selectFileMid?caseNo="+caseNo+"&caseId="+caseId
       });
   }
   function selectCaseDetails(type,caseId,caseNo,caseType){
       openDialog({
           frame:true,
           title:"查看进度",
           height:500,
           width:1000,
           url:"${ctx}/case/selectCaseDetails?type="+type+"&caseId="+caseId+"&caseType="+caseType+"&caseNo="+caseNo
       });
   }
   function selectReport(id,caseNo){
       openDialog({
           frame:true,
           title:"测算报告",
           height:600,
           width:1000,
           url:"${ctx}/case/risk/paymentEstimateReportList?caseNo="+caseNo+"&caseId="+id+"&mType=" +1,
           load : true
       });
   }

   function updateReson(id,caseNo){
       openDialog({
           frame:true,
           title:"伤残报告",
           height:650,
           width:1100,
           url:"${ctx}/invalidism/toReport?caseNo="+caseNo+"&caseId="+id+"&op=viewNo"
       });
   }

   var haldleRepayCaseCenterInfo = function(id){
       openDialog({
           frame:true,
           title:"操作还款",
           height:500,
           width:800,
           url:"${ctx}/case/haldleRepayCaseCenterInfo?id="+id
       });
   }

   var caseCenterInfoAllotAdd = function(caseId,gradationState){
       openDialog({
           frame:true,
           title:"改派",
           height:500,
           width:800,
           url:"${ctx}/caseCenterInfoAllot/caseCenterInfoAllotAdd?caseId="+caseId+"&gradationState="+gradationState+"&type=2"
       });
   }

   var selectOperator = function(caseId,operatorName,orgId){
       openDialog({
           frame:true,
           title:"修改业务员",
           height:500,
           width:800,
           url:"${ctx}/caseCenterInfoAllot/selectOperator?caseId="+caseId+"&operatorName="+operatorName+"&orgId="+orgId
       });
   }
   /**
    * 确认还款
    */
   function updCaseCenterInfoConfirmRepay(id){
       ajaxSubmit("${ctx}/case/updCaseCenterInfoConfirmRepay",{"id":id},reload,"确认成功！","确认通过还款？","确认失败！");
   }

   function selectCaseName() {
       $("#caseName2").show();
       $("#caseName1").hide();
   }
   function updateCaseName(id) {
       var caseName = document.getElementById("caseName").value;
       ajaxSubmit("${ctx}/case/center/operate",{"id":id,"btnCode":9997,"caseName":caseName},reload,"修改成功！","确认修改？","修改失败！");
   }

   function popup(id,code) {
       openDialog({
           frame: true,
           title: "案件基本信息",
           height: 600,
           width: 1000,
           url: "${ctx}/case/center/popup?id=" + id+"&code="+code
       });
   }
</script>

<script>
    var width = $(document.body).innerWidth() > 1000 ? $(document.body).innerWidth() : 1000
    var widthA = width * 0.98 - $('.header-r').innerWidth() - $('.header-r2').innerWidth() - 80
    $('.contain').width(width * 0.98)
    $('.header').width(widthA)
    window.onresize = function () {
        var width = $(document.body).width() > 1000 ? $(document.body).width() : 1000
        var widthA = width * 0.98 - $('.header-r').innerWidth() - $('.header-r2').innerWidth() - 80
        $('.contain').width(width * 0.98)
        $('.header').width(widthA)
    }


    var left = 0;
    var size = $("#stepsSize").val();
    if(size > 9){
        left = size - 6;
    }
    <%--document.getElementsByClassName('step')[0].scrollLeft = 80 * ${dto.caseStepsDTOs.size()}--%>
    document.getElementsByClassName('step')[0].scrollLeft = 85 * left;
    console.log($('.bar').length, $('.bar').width())
    var _width = ($('.bar').length) * $('.bar').width()
    $('.stepContent').css({
        width: _width + 'px'
    })

</script>
</body>
</html>
