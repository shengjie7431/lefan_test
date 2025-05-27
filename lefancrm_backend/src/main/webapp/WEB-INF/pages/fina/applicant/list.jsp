<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>垫付案件列表</title>
<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
<%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
<link rel="stylesheet" href="${ctx}/js/layui/layui_exts/soulTable.css">
<style>
    .main {
        width: 99%;
        margin: 10px auto;
    }

    .selectMul {
        width: 100%;
    }

    .searchs {
        padding: 10px 0 7px 0;
        background-color: #d9edf7;
    }

    .layui-form-item {
        margin: 0 !important;
    }

    label.layui-form-label {
        width: 114px;
        padding: 6px 15px;
        padding-left: 0;
        margin-bottom: 0;
    }

    .layui-form-item .layui-input-inline {
        width: 320px !important;
    }

    .layui-input{
        height: 32px;
        line-height: 32px;
    }

    .layui-form-select dl dd.layui-this {
        background-color: #f2f2f2 !important;
        color: #000 !important;
    }

    .layui-form-select dl dd.layui-select-tips {
        color: #999 !important;

    }

    .layui-form-select dl dd {
        color: #000;
    }

    .layui-table-view .layui-table {
        width: 100% !important;
    }

    .data-types {
        display: flex;
        justify-content: flex-end;
        background-color: #fff;
        margin-right: 10px;
    }

    .data-types .data-type {
        width: 60px;
        height: 32px;
        line-height: 32px;
        text-align: center;
        border: 1px solid #bbb;
        border-right: none;
        cursor: pointer;
    }

    .data-types .data-type:last-of-type {
        border-right: 1px solid #bbb;
    }

    .data-types .data-type.active {
        border: 1px solid #3BA9FF;
        background-color: #3BA9FF;
        color: #fff;
    }

    .data-choose {
        display: flex;
        align-items: center;
    }

    .data-choose input {
        width: 100px;
        height: 32px;
    }

    .data-choose .dc-span {
        width: 30px;
        text-align: center;
    }

    .d-flex-wrap {
        display: flex;
        flex-wrap: wrap;
    }

    .lf-select-block {
        padding: 0 8px !important;
        white-space: nowrap;
        background-color: #3BA9FF;
    }

    /*.layui-layer.layui-layer-page {*/
    /*width: 98% !important;*/
    /*left: 1% !important;*/
    /*top: 20px !important;*/
    /*}*/

    .layui-table-body {
        overflow-y: overlay;
    }

    #dialogId {
        z-index: 1298910170;
        position: fixed;
    }

    .layui-icon-spread-left {
        color: #3BA9FF;
    }

    .d_type_a {
        color: #FF3D00;
    }

    .d_type_b {
        color: #FB8C00;
    }

    .layui-layer-min,
    .layui-layer-max {
        display: none !important
    }

    .color1 {
        color: #FF3D00;
    }

    .color2 {
        color: #64DD17;
    }

    .nowrap-e {
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    thead th {
        color: #000;
    }

    .layui-table-hover {
        background-color: rgba(60, 169, 255, 0.13) !important;
    }

    .layui-table-total tr td{
        font-weight:800!important;
        color: #333!important;
    }
    .layui-table-tool{
        height: 40px;
        min-height: 40px;
        padding:4px 0;
    }
    .layui-table-tool .layui-table-tool-temp{
        float: right;
        padding-right: 60px;
    }
    .layui-table-tool .layui-table-tool-temp .layui-btn{
        height: 28px;
        line-height: 28px;
    }
    .theme-color {
        color: #3BA9FF;
    }
    .icon-about{
        display: inline-block;
        width: 16px;
        height: 16px;
        padding: 1px;
        background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAFD0lEQVR4Xu1bPXLbRhT+HjRDOUkR+QSWTxC6c8Ai1AkiNSZdST6B5ROEOoHlE0iuTLgRfQIpBRF3pk5g+QRBCmpszxAvswjkAVcA9hcIxyJa7r6fb9//Lgl3/KM7rj/WAKwt4I4jsHaBNg3g8Rvepg08IEZf5suMBAFmvMCn90/pqi25GrWA7hlv/fAF+0ToEzKltzQVSxi4IGAy7+DdbI8SzX3GyxoB4PEb7gcBnhOwayxRyQYGJmmKV++f0oUPekUaXgHIFX9JQNe3oBk9xsWCceQTCC8ACN8OAgjFvZy4CjxmnF5v4oUP13AG4NeIdwPgxMC/Vfrp/p4sUuy5WoMTAL2IXwI41JW4iXVMeBY/oVNb2tYAhGM+IcKBLWOf+4RLxEN6ZkPTCoBVUv5GaVsQjAEIx3xMhOc2aDe+h/FiOqRjEz5GAOQB78yEQdtrFyl2TAKjNgAi1W0E+OAr2jPwD4CZBFCXgJ8dQUvmHTzUTZHaAPTGfA66XcNbCPuaCcfxE5KVz0iFb7lLnGWWfQvaeb2ESTygPZ39WgD4MH1x4mmKXV3zzKvKia1F6LqCFgDhmD8SYVsH0bI1mbkT+lWnXkU3twbhduYf42I6pB3VRiUA4Vs+IM4qPesvBfb+GtCkSKAX8T4Du8R5h0iYzTs4kn03jHhEwB82zHWsQA1AxB9cmhsG/owHtNT/19QRCRN2ipaSB9+PNgAw4108pNr+pBYAF+bfBJZys9KiSky3N+YZCL/YgDDv4H5dRqgFoDfmQxBEvW/9MeFR8UTDMU+I8HsdwemAluQKIxbDkd+shFAUR7UAODHOpZWV6UX8t6qWWKR4WByLucihcoN6C4iYrVAvbEql2iFgKKc6Xi0ASKYDul+lRyUAIg9vBDh3BcB0PzNexUNaarF1rKaOj2xRxbWVACiDlalmOusZl/NN9ItBy0cRVpcOqwFwyL86ut5aU6K8WBM6pmFBg4GjeECjMrlWA4Aq5T0NXewAaKvvL1Fe3Cf8+BUnvoasKwsAMz5db6Jb9Plc+XOX6lM2dTsAWogBcnBqQvmVjQHi9OMhLXWYYcRnvsy+aAV2FuChC6zLBnK+99J3VDC0SoNNF0Lyqbi0vaq0K/cjWoWQ8MefvkLU7Y18bQEghjHxgCpvpVXdoHUb2ghqFkSdmqGVvgPQBcOpHf5vQms3k1MJyLhMCSNOkVCALWKIC5cHqm2mv9c1QoKWciTmMo2pElb45XUH28UCyGkAWsGobBwnL1UC0ERXWOWXvsHWuTlWApB1ZGO+8mmeVSfjOn5fKn5KCi2jbrC4uAkrkIsTH31/UeayUbw1AHlfbj+YrI5cxwwkYGz7fGug4/s3Imm5QAZAkxnBNLTXrM+v4Lq6bw21ARA8fYzJPepaSkon8GmVwlWC9iIW73Gsb24bBuD1dEBGz3aMLOBGeJ3LjYYVLSNvrLxWIVTGKR9ciKtru9sa/+hYKW8NwI38K+IO1so7A/B/Bsb8zcGhyxtBLwB8S5EpTm1vcE09QuR5EITypc9sTOhZBcEqBqJiRIqRz7JZLm8RYOR66k5pUAfdrHROIa7Wre70b/FgXHKQPayyfhJbJbdXC5CZiOoRKQ6yP0uYgiGUpux/Aqe6VZ3O4chrGgWgyEykznuf0d0gdJnyd0Gc/6+A8veChKt0gavP9zDTfedno3TjLuAqVJv7W7OANpUy4bUGwASt73Ht2gK+x1M10elf2OdYX4SLc6gAAAAASUVORK5CYII=);
        background-position: center;
        background-repeat: no-repeat;
        background-size: cover;
    }

    .icon-about{
        margin-left: 6px;
        display: inline-block;
        width: 18px;
        height: 18px;
        background: url(data:image/png;base64,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);
        background-position: center;
        background-repeat: no-repeat;
        background-size: cover;
    }

    .process {
        padding:10px 0;
    }

    .process .bars-title {
        width: 100%;
        text-align: center;
        font-weight: bold;
    }

    .process .bars {
        width: 100%;
        height: 690px;
        margin-top: 20px;
        overflow: auto;
    }

    .process .bars .bar {
        width: 100%;
        display: flex;
    }

    .process .bars div.hide {
        display: none;
    }

    .process .bars .bar .bar-date {
        min-width: 83px;
        /* max-width: 83px; */
        width: 48%;
        padding-right: 2%;
        font-size: 12px;
        text-align: right;
    }

    .process .bars .bar .bar-contain {
        width: 50%;
    }

    .process .bars .bar .bar-contain .title {
        width: 100%;
        display: flex;
        align-items: flex-start;
        border:none;
    }

    .process .bars .bar .bar-contain .title .index {
        width: 12px;
        height: 12px;
        border: 3px solid #45B4FE;
        border-radius: 50%;
        background-color: #45B4FE;
    }

    .process .bars .bar .bar-contain .title .text {
        /* width: 70%; */
        height: 18px;
        line-height: 18px;
        color: #333;
        font-size: 12px;
        margin-left: 13px;
        overflow: hidden;
    }

    .process .bars .bar .bar-contain .title div.text-btn {
        margin-left: 13px;
        padding: 2px 6px;
        height: 14px;
        line-height: 14px;
        color: #45B4FE;
        border: 1px solid #45B4FE;
        font-size: 12px;
        text-align: center;
    }

    .process .bars .bar .bar-contain .content {
        width: 70%;
        height: 70px;
        line-height: 70px;
        margin-left: 7px;
        padding: 0;
        padding-left: 20px;
        border-left: 4px solid #45B4FE;
        color: #333;
        font-size: 12px;
    }

    .process .bars .bar .bar-contain .border-dotted {
        border-left: 4px dotted #45B4FE;
    }

    .process .bars .bar .bar-contain .content .text {
        overflow: hidden;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
        text-overflow: ellipsis;
    }

    .process .close {
        position: absolute;
        top: 20px;
        right: 20px;
        width: 20px;
        height: 20px;
    }

    .lf-btns{
        padding-top: 10px;
        width: 100%;
        display: flex;
        justify-content: center;
    }
    .lf-btns .lf-btn{
        width: 180px;
        height: 40px;
        line-height: 40px;
        border:1px solid #3BA9FF;
        color: #3BA9FF;
        text-align: center;
        margin-right: 40px;
        cursor: pointer;
    }
    .lf-btns .lf-btn.active{
        background-color:#3BA9FF;
        color: #fff;
    }

    .checkBoxH{
        display: inline-block;
        margin-right: 6px;
        width: 13px;
        height: 13px;
        border: 1px solid #333;
        border-radius: 4px;
        box-sizing: content-box;
    }
    .checkBoxH.active {
        display: inline-block;
        width: 15px;
        height: 15px;
        background: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAGT0lEQVR4Xu2d8XXbNhDGceQA9Qa1JyhLDlB5gjoTNJkg7gRxJog8QZwJ6k5gdQCi7ARxN3AGINB379GveoolgCQI84BP/wo8Hr7vxyMIiQApfLJWgLLuPTqvAEDmEAAAAJC5Apl3HxUAAGSuQObdRwUAAGEUqKrqnIh+LIqistaehYmKKPsKENGTMaaz1v7bdd1jCHVmVYCqqs6KonhPRFdKqSpEQojhp4C1lgG4M8bcdl335HfU960mA1DX9Qel1DUR4Wqfqn6A46y1bP5Wa/1xSrjRAPBVX5blH0qpzZQT4pjFFNj1ff9mbDUYBcBg/gPK/WImzg3c9X1/OQaCUQA0TcPm48qfa9Oyx+/atr30PYU3AHVd3xAR3/fxWbkC1tqPWusbnzS9ABhG+18x4POR9PXb8MDQGHPhcyvwAgBX/+ubOjYD3yrgBUDTNH9j4DfWgtdtz/MEWusLVxZOAHiGryzLr65A+H59CvR9z7eBkzOGPgBsyrLk0T8+whQYHgl3p9J2AlDXNc/2fRLWd6SrlLLW/q613s4FAI9/QnHyGQj6VAAAAAAwASSRAVQAia4FzBkABBRTYigAING1gDkDgIBiSgwFACS6FjBnABBQTImhAIBE1wLmDAACiikxFACQ6FrAnAFAQDElhgIAEl0LmDMACCimxFAAQKJrAXMGAAHFlBgKAEh0LWDOACCgmBJDAQCJrgXMGQAEFFNiKAAg0bWAOQOAgGJKDAUAJLoWMGcAEFBMiaEAgETXAuYMAAKKOSWUtfbWGHPXdV03rLFwRUT8qtYPU+KNPQYAjFUsYHtr7Tut9d1hyOFt6y4GBAAgoKFjQh0z/zlGrBduAcAY1wK1dZnPp6mqqirLkhfdWPQDABaV9/vgPuYPAERZdAMARATA13xOqWkaXlqXF9tc9AMAFpX3/+BjzB8AiLLmEgCIAMBY8+u6/kxEbyOkxiuEONcLxAIRM5xYs/ncLQAww1zXoWs3HwC4HJzxvQTzAcAMg08dKsV8ALAAAJLMBwCBAZBmPgAICIBE8wFAIACkmg8AAgDAv+drra99Q9V1/ZaIPvu2X7qdyHkA3hNv2A5tVxQF70XI8+a/Li3WC/G/tG3rPWO3NvNFVgBr7V/GmKvDnS6GH0/4zxVR/kmjlBJvvkQAvvV9f35sm5NYv6ClYr5EAP5s25bL/dFPhDKbxJX/LKCoMYBPstyxBSFIyvwkK8Az2QtAkJz54gAYtjr72bXHzQIQJGm+OAAGY+/btn3j+8gXoBIka75UAPhPDHda63cRIEjafLEADIkvDUHy5osGYGEIsjBfPAALQZCN+UkAMAWCpml4yvi3FOf2fcdFIieCTnVu7MDwBQiyuvKTA2BmJcjS/GRuAfuVwWcr1P32PE/w0ivax6pNgHmFsVV60fY+0+viXgwZ+w8dX4VTMz/JCrB3b3tx8QVfsw/bpWh+0gAMnQsCQarmJw9ACAhSNj8LAOZAkLr52QAwBYIczM8KgDEQ8Po8RVE8ENHZ1EGjlOOSfAx0zBieHBjmZH52FcD1iJib+dkCMHR8a4y55b+X8cKMRVG8JyLvN3yklHhXntndAlyC5PY9AMjN8YP+AgAAgFXCcmYAFSBn97FMXObuAwAAgFtA5gwAAACAp4CcGUAFyNl9DAIzdx8AAADcAjJnAAAAADwF5MwAKkDO7gccBF4T0afMtRTZfZ93KZ3vBlZVtSnL8kGkApkn3ff9Zdd1u1My+AAQZZfLzL1apPt931+4lt1zAsCZ1XXdEdFPi2SJoIsowKuua63PXcF9Abghog+uYPh+PQr4PAFwtl4AVFV1VpblY8Tl2tejpMxMTq68vt8lLwCG2wCqgBAYfK9+7wrw3O+6rndE9IsQHbJMkzfd0FpvfDvvXQE4IN8KiqJgCDAg9FU4Yjtr7T/GmM2xTTdeSmUUAHsQ3KMSRHTW41THtttxHToagL3bAY8J+H27WPv4uPqS6/ffrLVbrfXNFAEmA7BXDRiCK9wWpsg//Rgu90qpe2PMdkzJPzzjLAD2g/FbuEopfhO3Ukolv/jCdOtmHflkjOmUUo+uGT7fswQDwPeEaLcuBQDAuvyIng0AiC75uk4IANblR/RsAEB0ydd1QgCwLj+iZwMAoku+rhMCgHX5ET2b/wDIalLM9tqEAgAAAABJRU5ErkJggg==');
        background-position: center;
        background-repeat: no-repeat;
        background-size: contain;
    }
    .poi-no{
        pointer-events: none;
    }
</style>
</head>

<body>
<div class="main">
    <div class="layui-form searchs" lay-filter="search">
        <input type="hidden" value="${menuCode}" id="menuCode" name="menuCode">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">快捷查询</label>
                <div class="layui-input-inline _input">
                    <input type="text" id="searchStr" name="realName" placeholder="可输入案件编号，申请人姓名，被保险人姓名等"  autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">保险公司</label>
                <div class="layui-input-inline">
                    <div id="entrustOrgIds" class="selectMul"></div>
                </div>
            </div>
            <c:if test="${menuCode == 'register-list' || menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'
                    || menuCode == 'applicant-track-list' || menuCode == 'confirm-account-list'}">
                <div class="layui-inline">
                    <label class="layui-form-label">案件状态</label>
                    <div class="layui-input-inline">
                        <div id="applicantState" class="selectMul"></div>
                    </div>
                </div>
            </c:if>
            <c:if test="${menuCode == 'assign-list'}">
                <div class="layui-inline">
                    <label class="layui-form-label">机构状态</label>
                    <div class="layui-input-inline">
                        <div id="applicantState" class="selectMul"></div>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">机构分派状态</label>
                    <div class="layui-input-inline">
                        <div id="assignOrgState" class="selectMul"></div>
                    </div>
                </div>
            </c:if>
            <c:if test="${menuCode == 'assign-org-list'}">
                <div class="layui-inline">
                    <label class="layui-form-label">垫付员状态</label>
                    <div class="layui-input-inline">
                        <div id="applicantState" class="selectMul"></div>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">垫付员分派状态</label>
                    <div class="layui-input-inline">
                        <div id="assignUserState" class="selectMul"></div>
                    </div>
                </div>
            </c:if>
            <div class="layui-inline" style="margin-left: 20px;">
                <button lay-submit class="ll-submit layui-btn layui-btn-normal layui-btn-sm layui-btn-radius"
                        lay-filter="submit" style="width: 86px">查询 <i
                        class="layui-icon layui-icon-search"></i></button>
                <c:if test="${menuCode == 'register-list'}">
                    <button class="layui-btn layui-btn-normal layui-btn-sm layui-btn-radius add" style="width: 100px">代理委托 <i class="layui-icon layui-icon-add"></i></button>
                </c:if>
            </div>
        </div>
    </div>
    <div class="table-content">
        <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">
        </table>
    </div>
</div>


<script type="text/html" id="table-content-child-h">
    <div class="layui-form searchs" style="width: 99%;margin: 0 auto">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">快捷查询</label>
                <div class="layui-input-inline _input">
                    <input type="text" id="searchStrChild" name="realName" placeholder="可输入案件编号，申请人姓名，被保险人姓名等"  autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 20px;">
                <button lay-submit class="ll-submit layui-btn layui-btn-normal layui-btn-sm layui-btn-radius submitChild"
                        lay-filter="submitChild" style="width: 86px">查询 <i
                        class="layui-icon layui-icon-search"></i></button>
            </div>
        </div>

    </div>
    <div class="table-content-child" style="width: 99%;margin: 0 auto">
        <table class="layui-table" id="test-child" lay-filter="test-child" lay-skin="line" lay-size="lg" style="margin: 0">
        </table>
    </div>
    <div class="child-bottom">
        <div class="lf-btns">
            <div class="lf-btn" data-id="1">取消</div>
            <div class="lf-btn active" data-id="2">创建出院结算并通知调查员</div>
        </div>
    </div>
</script>
<script type="text/html" id="processes">
    <div class="process">
        <div class="bars-title">

        </div>
        <div class="bars">

        </div>
    </div>
</script>


<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        soulTable: 'soulTable',
        xmSelect: 'xm-select'
    })
    var menuCode = $("#menuCode").val();

    $('.data-types').on('click', '.data-type', function () {
        var _this = $(this)
        var id = _this.attr('data-id')
        if (!_this.hasClass('active')) {
            searchType = id
            _this.addClass('active').siblings().removeClass('active')
            var vals = setDate(id)
            $('#startTime').val(vals[0])
            $('#endTime').val(vals[1])
            var _min = getYMD(vals[0])
            var _max = getYMD(vals[1])
            startTime.config.max = {
                year: _max.y,
                month: _max.m - 1,
                date: _max.d
            }
            endTime.config.min = {
                year: _min.y,
                month: _min.m - 1,
                date: _min.d
            }
        }
    })

    function getYMD(date) {
        var today = new Date(date);
        return {
            'y': today.getFullYear(),
            'm': today.getMonth() + 1,
            'd': today.getDate()
        }
    }

    function setDate(id) {
        var today = new Date(),
                y = today.getFullYear(),
                m = today.getMonth() + 1,
                d = today.getDate(),
                w = today.getDay(),
                millisecond = 1000 * 60 * 60 * 24;
        if (id == 'upMonth') {
            var y1 = y,
                    m1 = m
            if (m == 1) {
                y1 = y - 1
                m1 = 12
            } else {
                m1 = m - 1
            }
            m1 = PrefixInteger(m1, 2)
            var days1 = new Date(y1, m1, 0).getDate()
            vals = [y1 + '-' + m1 + '-01', y1 + '-' + m1 + '-' + days1]
        } else if (id == 'yesterday') {
            var yesterDay = new Date(today.getTime() - millisecond);
            vals = [dateFormat(yesterDay), dateFormat(yesterDay)]
        } else if (id == 'today') {
            vals = [y + '-' + m + '-' + d, y + '-' + m + '-' + d]
        } else if (id == 'curWeek') {
            var minusDay = w != 0 ? w - 1 : 6;
            var monday = new Date(today.getTime() - (minusDay * millisecond));
            var sunday = new Date(monday.getTime() + (6 * millisecond));
            vals = [dateFormat(monday), y + '-' + m + '-' + d]
        } else if (id == 'curMonth') {
            vals = [y + '-' + m + '-01', y + '-' + m + '-' + d]
        } else if (id == 'all') {
            vals = ['2019-02-27', y + '-' + m + '-' + d]
        }
        return vals
    }

    function dateFormat(time, format) {
        var t = new Date(time);
        var format = format || 'yyyy-MM-dd'
        var tf = function (i) {
            return (i < 10 ? '0' : '') + i
        };
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
            switch (a) {
                case 'yyyy':
                    return tf(t.getFullYear());
                    break;
                case 'MM':
                    return tf(t.getMonth() + 1);
                    break;
                case 'mm':
                    return tf(t.getMinutes());
                    break;
                case 'dd':
                    return tf(t.getDate());
                    break;
                case 'HH':
                    return tf(t.getHours());
                    break;
                case 'ss':
                    return tf(t.getSeconds());
                    break;
            }
        })
    };

    function PrefixInteger(num, m) {
        return (Array(m).join(0) + num).slice(-m);
    }

layui.use(['form', 'table', 'soulTable', 'xmSelect', 'laydate', 'layer', 'util'], function () {
    var soulTable = layui.soulTable,
            table = layui.table,
            form = layui.form,
            xmSelect = layui.xmSelect,
            laydate = layui.laydate,
            layer = layui.layer,
            $ = layui.$,
            util = layui.util;

    var demo1 = xmSelect.render({
        el: '#entrustOrgIds',
        theme: {
            color: '#3BA9FF',
        },
        size: 'small',
        filterable: true,
        filterDone: function(val, list){
            $('.xm-option-content').each(function () {
                var _this = $(this)
                _this.attr('title', _this.text())
            })
        },
        toolbar: {
            show: true
        },
        model: {
            label: {
                type: 'xxxx', //自定义与下面的对应
                xxxx: {
                    template(data, sels) {
                        if (sels.length == data.length) {
                            return '<div>全部</div>'
                        } else {
                            var _html = ''
                            sels.filter(function (cur) {
                                _html += '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                            })
                            return _html
                        }
                    }
                }
            }
        },
        data: []
    })

    var demo2 = xmSelect.render({
        el: '#applicantState',
        theme: {
            color: '#3BA9FF',
        },
        size: 'small',

        toolbar: {
            show: true
        },
        filterable: true,
        filterDone: function(val, list){
            $('.xm-option-content').each(function () {
                var _this = $(this)
                _this.attr('title', _this.text())
            })
        },
        model: {
            label: {
                type: 'xxxx', //自定义与下面的对应
                xxxx: {
                    template(data, sels) {
                        if (sels.length == data.length) {
                            return '<div>全部</div>'
                        } else {
                            var _html = ''
                            sels.filter(function (cur) {
                                _html +='<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                            })
                            return _html
                        }
                    }
                },
            }
        },
        data: []
    })


    var paramSubmit = {
        menuCode: menuCode,
        searchStr: '',
        entrustOrgIds : '',
        applicantStates: ''
    }


    var _cols = [
        [
            {
                field: 'caseApplicantNo',
                minWidth: 270,
                title: '案件编号',
                fixed: 'left',
                style: 'color: #3BA9FF;cursor: pointer;',
                event: 'case',
                templet: function (d) {
                    var _html = '<a lay-event="operate">'+d.caseApplicantNo+'</a>'
                    return _html
                }
            },
            {
                field: 'entrustOrgName',
                minWidth: 250,
                title: '保险公司'
            },
            {
                field: 'finaUserName',
                minWidth: 150,
                title: '申请人姓名'
            },
            {
                field: 'finaUserTel',
                minWidth: 150,
                title: '申请人手机号'
            },
            {
                field: 'insuredName',
                minWidth: 160,
                title: '被保险人姓名'
            },
            {
                field: 'insuredTel',
                minWidth: 150,
                title: '被保险人手机号'
            },
            {
                field: 'applyAdvanceMoney',
                minWidth: 150,
                title: '申请垫付金额',
                templet: function (d) {
                    return getFixed(d.applyAdvanceMoney)
                }
            },
            {
                field: 'insurancePolicyNo',
                minWidth: 150,
                title: '保单号'
            },
            {
                field: 'hospitalName',
                minWidth: 150,
                title: '就诊医院'
            },
            {
                field: 'department',
                minWidth: 150,
                title: '就诊科室'
            },
            {
                field: 'inHospitalTime',
                minWidth: 150,
                title: '入院时间',
                sort: true,
                templet: function (d) {
                    var _html = d.inHospitalTime ? util.toDateString(d.inHospitalTime, 'yyyy-MM-dd') : ''
                    return _html
                }
            },
            {
                field: 'applicantStateStr',
                minWidth: 150,
                title: '案件状态'
            },



            {
                field: 'surveyOrgName',
                minWidth: 150,
                title: '垫付机构'
            },
            {
                field: 'surveyUserName',
                minWidth: 150,
                title: '垫付员'
            },
            {
                field: 'finaOrgState',
                minWidth: 150,
                title: '机构状态',
                templet: function (d) {
                    var _html = '';
                    if(d.finaOrgState == 1)_html = '作业中';
                    if(d.finaOrgState == 2)_html = '已提交';
                    return _html
                }
            },
            {
                field: 'orgAssignTime',
                minWidth: 150,
                title: '分派机构日期',sort: true,
                templet: function (d) {
                    var _html = d.orgAssignTime ? util.toDateString(d.orgAssignTime, 'yyyy-MM-dd') : ''
                    return _html
                }
            },
            {
                field: 'orgSubmitTime',
                minWidth: 150,
                title: '机构提交日期',
                sort: true,
                templet: function (d) {
                    var _html = d.orgSubmitTime ? util.toDateString(d.orgSubmitTime, 'yyyy-MM-dd') : ''
                    return _html
                }
            },
            {
                field: 'orgEndTime',
                minWidth: 150,
                title: '机构截止日期',
                sort: true,
                templet: function (d) {
                    var _html = d.orgEndTime ? util.toDateString(d.orgEndTime, 'yyyy-MM-dd') : ''
                    return _html
                }
            },
            {
                field: 'orgAging',
                minWidth: 150,
                title: '机构时效'
            },

            {
                field: 'userState',
                minWidth: 150,
                title: '垫付员状态',
                templet: function (d) {
                    var _html = '';
                    if(d.userState == 1)_html = '作业中';
                    if(d.userState == 2)_html = '已提交';
                    return _html
                }
            },
            {
                field: 'userAssignTime',
                minWidth: 150,
                title: '分派垫付员日期',sort: true,
                templet: function (d) {
                    var _html = d.userAssignTime ? util.toDateString(d.userAssignTime, 'yyyy-MM-dd') : ''
                    return _html
                }
            },
            {
                field: 'userSubmitTime',
                minWidth: 150,
                title: '垫付员提交日期',
                sort: true,
                templet: function (d) {
                    var _html = d.userSubmitTime ? util.toDateString(d.userSubmitTime, 'yyyy-MM-dd') : ''
                    return _html
                }
            },
            {
                field: 'userEndTime',
                minWidth: 150,
                title: '垫付员截止日期',
                sort: true,
                templet: function (d) {
                    var _html = d.userEndTime ? util.toDateString(d.userEndTime, 'yyyy-MM-dd') : ''
                    return _html
                }
            },
            {
                field: 'userAging',
                minWidth: 150,
                title: '垫付员时效'
            },
            {
                field: 'createBy',
                minWidth: 150,
                title: '创建人'
            },
            {
                field: 'createTime',
                minWidth: 200,
                title: '创建时间',
                sort: true,
                templet: function (d) {
                    var _html = d.createTime ? util.toDateString(d.createTime, 'yyyy-MM-dd HH:mm:ss') : ''
                    return _html
                }
            },
            {
                field: '',
                minWidth: 100,
                title: '操作',
                fixed: 'right',
                rowspan: 2,
                templet: function (d) {
                    var  _html = '<a class="layui-btn layui-btn-xs operateMark" lay-event="operate">处理</a>'
                    return _html
                }
            }
        ]
    ]



    $.ajax({
        url: '${ctx}/fina/pub/ajaxData',
        type: 'post',
        data: {
            dataType: 'entrust-org-list'
        },
        success: function (res) {
            var res = JSON.parse(res)
            filterJson(demo1, res.results,'id','name',false, false)
        }
    })



    if (menuCode == 'register-list' || menuCode == 'first-trial-list' || menuCode == 'review-trial-list' || menuCode == 'insurance-trial-list'
            || menuCode == 'applicant-track-list' || menuCode == 'confirm-account-list') {
        $.ajax({
            url: '${ctx}/fina/pub/ajaxData',
            type: 'post',
            data: {
                dataType: 'applicant-state-list',
                menuCode: menuCode
            },
            success: function (res) {
                var res = JSON.parse(res)
                filterJson(demo2, res.results, 'value', 'valueName', false, false)

                var _states = []
                res.results.map(function (cur) {
                    if (cur.checked) {
                        _states.push(cur.value)
                    }
                })
                Object.assign(paramSubmit, {
                    applicantStates: _states.join(',')
                })
                setTable(_cols, paramSubmit)
            }
        })
    }else if (menuCode == 'assign-list' || menuCode == 'assign-org-list'){
        demo2.update({
            data: [{
                value: '',
                name: '全部',
                selected: true
            },{
                value: '1',
                name: '作业中'
            },{
                value: '2',
                name: '已提交'
            }]
        })
        Object.assign(paramSubmit, {
            applicantStates: ""
        })

        if(menuCode == 'assign-list'){
            demo3 = xmSelect.render({
                el: '#assignOrgState',
                theme: {
                    color: '#3BA9FF'
                },
                size: 'small',
                radio: true,
                toolbar: {
                    show: true,
                    list: ['CLEAR']
                },
                filterable: true,
                filterDone: function(val, list){
                    $('.xm-option-content').each(function () {
                        var _this = $(this)
                        _this.attr('title', _this.text())
                    })
                },
                model: {
                    label: {
                        type: 'xxxx', //自定义与下面的对应
                        xxxx: {
                            template(data, sels) {
                                if (sels.length == data.length) {
                                    return '<div>全部</div>'
                                } else {
                                    var _html = ''
                                    sels.filter(function (cur) {
                                        _html +='<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                    })
                                    return _html
                                }
                            }
                        },
                    }
                },
                data: [
                    {
                        value:'',
                        name:'全部',
                    },
                    {
                        value:1,
                        name:'待分派',
                        selected : true
                    },
                    {
                        value:2,
                        name:'已分派'
                    }
                ]
            })

            Object.assign(paramSubmit, {
                assignOrgState: 1
            })
        }

        if(menuCode == 'assign-org-list'){
            demo3 = xmSelect.render({
                el: '#assignUserState',
                theme: {
                    color: '#3BA9FF'
                },
                size: 'small',
                radio: true,
                toolbar: {
                    show: true,
                    list: ['CLEAR']
                },
                filterable: true,
                filterDone: function(val, list){
                    $('.xm-option-content').each(function () {
                        var _this = $(this)
                        _this.attr('title', _this.text())
                    })
                },
                model: {
                    label: {
                        type: 'xxxx', //自定义与下面的对应
                        xxxx: {
                            template(data, sels) {
                                if (sels.length == data.length) {
                                    return '<div>全部</div>'
                                } else {
                                    var _html = ''
                                    sels.filter(function (cur) {
                                        _html +='<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                    })
                                    return _html
                                }
                            }
                        },
                    }
                },
                data: [
                    {
                        value:'',
                        name:'全部',
                    },
                    {
                        value:1,
                        name:'待分派',
                        selected : true
                    },
                    {
                        value:2,
                        name:'已分派'
                    }
                ]
            })
            Object.assign(paramSubmit, {
                assignUserState: 1
            })
        }
        setTable(_cols, paramSubmit)

    }

    if(menuCode == 'danger-list') {
        delete paramSubmit.applicantStates
        setTable(_cols, paramSubmit)
    }


    form.on('submit(submit)', function (data) {
        Object.assign(paramSubmit, {
            searchStr: $('#searchStr').val(),
            entrustOrgIds: demo1.getValue('valueStr'),
            applicantStates: demo2.getValue('valueStr')
        })
        if(menuCode == 'assign-list'){
            Object.assign(paramSubmit,{
                assignOrgState : demo3.getValue('valueStr')
            })
        }else if(menuCode == 'assign-org-list'){
            Object.assign(paramSubmit,{
                assignUserState : demo3.getValue('valueStr')
            })
        }
        reloadTable(_cols, paramSubmit)
    });

    function filterJson(demo, newJson, id, name, flag, selected) {
        var demoList = [],
                demoValues = []
        newJson.map(function (cur) {
            var _name = name ? cur[name] : cur.name
            var _id = id ? cur[id] : cur.id
            var param = {
                name: _name,
                value: _id,
            }
            if (selected) {
                Object.assign(param, {
                    selected: true
                })
            }
            if (cur.checked){
                Object.assign(param, {
                    selected: true
                })
            }
            demoList.push(param)
            demoValues.push(_id)
        })
        if (!flag) {
            demo.update({
                data: demoList
            })
        } else {
            return demoList
        }
        setTimeout(function () {
            $('.xm-option-content').each(function () {
                var _this = $(this)
                _this.attr('title', _this.text())
            })
        }, 200)
    }

    //重载表格数据
    function reloadTable(_cols, _data) {
        $('.table-content').empty()
        $('.table-content').append(
                ' <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg"></table>'
        )
        setTable(_cols, _data)
    }

    function getOfSigned(d, plus) {
        var _d = Math.round(d * 10000) / 100
        var _html = '<div class="">' + _d + '%</div>'
        if (plus) {
            return _html
        }
        if (_d > 0) {
            _html = '<div class="color1">' + _d + '%</div>'
        } else if (_d < 0) {
            _html = '<div class="color2">' + _d + '%</div>'

        }
        return _html

    }
    function getFixed(d) {
        return d ? Math.round(d * 100) / 100 : '0'

    }





    function setTable(_cols, param) {
        $('button.ll-submit').attr('disabled', true)
        setTimeout(function () {
            $('button.ll-submit').removeAttr('disabled')
        }, 6000)
        var _h = $('.searchs').outerHeight() + 50
        var fullH = 'full-' + _h
        var myTable = table.render({
            id: "test",
            elem: '#test',
            even: true,
            drag: false,
            cols: _cols,
            page:{
                theme: '#1e9fff',
                limit: 15,
                limits: [10,15,20,30,40,50]
            },
            request: {
                pageName: 'page',
                limitName: 'pageSize'
            },
            overflow: {
                type: 'tips'
                ,hoverTime: 300 // 悬停时间，单位ms, 悬停 hoverTime 后才会显示，默认为 0
                ,color: 'black' // 字体颜色
                ,bgColor: 'white' // 背景色
                ,minWidth: 250 // 最小宽度
                ,maxWidth: 500 // 最大宽度
            },
            height: fullH,
            url: '${ctx}/fina/applicant/getDetail',
            where: param,
            parseData: function (res) {
                return {
                    "code": res.isSuccess ? 0 : 1,
                    "msg": res.msg,
                    "count": res.count,
                    "data": res.results
                }
            },
            // initSort: {
            //     field: 'oprNum',
            //     type: 'desc'
            // },
            done: function (res) {
                soulTable.render(this)
                $('button.ll-submit').removeAttr('disabled')
            }
        })
        table.on('tool(test)', function (obj) {
            if (obj.event == 'case') {
                console.log(obj.event)
            }else if (obj.event == 'operate') {
                var _height = $(document).height() * 0.99
                var _width = $(document).width() * 0.99
                var url = "${ctx}/fina/applicant/info?finaInfoId=" + obj.data.id + "&display=true&menuCode="+menuCode;

                if(menuCode=='assign-org-list'){
                    var surveyOrgId =  obj.data.surveyOrgId;
                    url = url + "&surveyOrgId="+ surveyOrgId
                }


                openDialog({
                    frame:true,
                    title:"",
                    height:_height,
                    width:_width,
                    url:url
                });
            }
        })
    }

    $('.add').click(function () {
        $('button.add').attr('disabled', true)
        setTimeout(function () {
            $('button.add').removeAttr('disabled')
        }, 2000)
        showCaseList()
    })

    var showCaseList = function(obj){
        var _height = $(document).height() * 0.98
        var _width = $(document).width() * 0.98
        openDialog({
            frame:true,
            title:"新增",
            height:_height,
            width:_width,
            url:"${ctx}/fina/applicant/edit?btnCode=add-info&urgeType=add",
            load:true
        });
    }

})
</script>



</body>

</html>