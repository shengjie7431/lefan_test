<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%--编辑--%>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <%--<script src="${ctx}/js/progress.js"></script>--%>
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <style>
        .li-hover:hover{
            color: #fff;
            background-color: #3FB5FD;
        }
        .first {
            idth: 49%;
            min-width: 49%;
            height: 100%;
            min-height: 100%;
            overflow: auto;
            border-right: 1px solid #bbb;
            /*padding-bottom: 2%;*/
        }
        .second {
            width: 50%;
            float:left;
            height: 800px;
            border: 1px solid #f2f2f2;
        }
        .items{
            width: 600px;
            height: 252px;
            padding: 20px;
            padding-right: 0;
            text-align: center;
            border: 1px solid #66C8FF;
            margin: 20px auto;
        }
        .item{
            float: left;
            /*width: 180px;*/
            /*height: 40px;*/
            padding:4px 10px;
            line-height: 20px;
            border:1px solid #66C8FF;
            color: #3ba9ff;
            font-size: 10px;
            text-align: center;
            margin-right: 10px;
            margin-bottom: 10px;
            word-break:break-all;
        }
        div.active{
            background-color: #66C8FF !important;
            color: #fff;
        }
    </style>
    <style>
    .contain {
        display: flex;
        width: 100%;
        height: 100%;
        min-height: 100%;
        overflow: hidden;
    }

    .body-right {
        width: 50%;
        min-width: 50%;
        height: 100%;
        overflow: auto;
        /* padding-bottom: 2%; */

    }

    .body-right .title {
        padding: 20px 0;
        margin: 0 4%;
        width: 92%;
        border-bottom: 1px solid #bbb;
        font-size: 20px;
        color: #333;
        text-align: left;
    }

    .body-right .form {
        padding: 10px 0;
        margin: 0 4%;
        width: 92%;
    }

    .body-right .cell {
        display: flex;
        padding: 10px 0;
    }
    .body-right div.pd0 {
        padding-bottom: 0;
    }

    .body-right .cell .label {
        padding-right: 1%;
        width: 13%;
        min-width: 70px;
        height: 36px;
        line-height: 36px;
        color: #333;
    }

    .body-right .cell .value {
        width: 86%;
        position: relative;
    }

    .body-right .cell .value input {
        display: inline-block;
        width: 98%;
        height: 38px;
        /* line-height: 38rpx; */
        padding: 9px 4.5%;
        border: 1px solid #d0c9c9;

    }

    .body-right .cell .list {
        /* position: absolute; */
        width: 98%;
        height: 200px;
        overflow: auto;
        padding: 10px 1%;
        border: 1px solid #d0c9c9;
        background-color: #fff;
        z-index: 1000;

    }

    .body-right .cell .listHistory {
        /* position: absolute; */
        width: 98%;
        height: 175px;
        overflow: auto;
        padding: 10px 1%;
        border: 1px solid #d0c9c9;
        background-color: #fff;
        z-index: 1000;

    }

    .body-right .cell .listHistory .li {
        width: 100%;
        padding: 5px 4%;
        /*height: 20px;*/
        line-height: 20px;
    }
    .body-right .cell .listHistory .li span{
        float:right;

    }

    .body-right .cell .list .li {
        width: 92%;
        padding: 3px 4%;
        /*height: 20px;*/
        line-height: 20px;
    }
    .body-right .cell .list .li span{
        float:right;

    }

    .body-right .cell .value .btns {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        align-items: center;
    }

    .body-right .cell .value .btns .btn {
        margin-bottom: 10px;
        margin-right: 10px;
        padding: 3px 5px;
        width: 118px;
        height: 40px;
        line-height: 40px;
        color: #101010;
        border: 1px solid #d0c9c9;
        text-align: center;
        font-size: 14px;
        word-wrap: break-word;
        word-break: normal;

    }

    .body-right .cell .value .btns .item span {
        display: block;
        width: 100%;
        height: 20px;
        line-height: 20px;
        color: #101010;
    }

    .body-right .cell .value .btns div.active {
        color: #fff;
        background-color: #3FB5FD;
        border: 1px solid #3FB5FD;
    }

    .body-right .cell .value .btns div.active span {
        color: #fff;
        background-color: #3FB5FD;
    }

    .body-right .cell .value .texts {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        align-items: center;
        border: 1px solid #d0c9c9;
    }

    .body-right .cell .value .texts .text {
        padding: 6px 3%;
        width: 94%;
        line-height: 20px;
        font-size: 14px;
    }

    .body-right .form-btns {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        align-items: center;
        justify-content: center;
    }

    .body-right .form-btn {
        margin-bottom: 10px;
        margin-right: 10px;
        padding: 3px 5px;
        width: 158px;
        height: 45px;
        line-height: 40px;
        color: #3FB5FD;
        border: 1px solid #3FB5FD;
        text-align: center;
        font-size: 14px;
        word-wrap: break-word;
        word-break: normal;

    }

    .body-right .form-btns div.active {
        color: #fff;
        background-color: #3FB5FD;
        border: 1px solid #3FB5FD;
    }
    .dalogs{
        display: none;
        position: absolute;
        top: 30%;
        left: 39%;
        width: 400px;
        height: 300px;
        background-color: #fff;
        box-shadow: 0 0 10px #999;
        color: #000;
    }
    </style>
    <style>
        .main22 {
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            overflow: hidden;
        }

        .step-contain {
            width: 100%;
            height: 100%;
            margin: 0 auto;
            background-color: #fff;
        }
        .step-contain .title{
            padding: 20px 0;
            width: 100%;
            font-size: 16px;
            text-align: center;
            display: block;
            border: none;
        }
        .border7{
            width: 100%;
            height: 7px;
            background-color: rgba(223, 223, 223, 0.26);
        }
        .content{
            width: 100%;
            padding: 20px 0;
        }
        .content .btn-area {
            margin: 0 auto;
            width: 276px;
            display: flex;
            justify-content: space-between;
        }
        .content .btn-area .c-btn{
            width: 120px;
            height: 40px;
            line-height: 35px;
            color: #333;
            border:1px solid #bbb;
            font-size: 14px;
            text-align: center;
        }
        .content .btn-area .c-btn2{
            background-color: #259B24;
            width: 120px;
            height: 40px;
            line-height: 35px;
            color: #fff;
            border:1px solid #259B24;
            font-size: 14px;
            text-align: center;
        }
        .icon-step .index{
            width: 30px;
            height: 30px;
            line-height: 30px;
            border-radius: 50%;
            color: #fff;
            font-size: 14px;
            background-color: #dfdfdf;
            text-align: center;
        }
        .icon-step div.active{
            background-color: #45B4FE;
        }
        /* ---------0429--------- */
        .body-right .cell-bx {
            display: none;
        }

        .cell-bx .label{
            line-height: 14px!important;
            white-space: inherit;
        }

        .cell .value.d-flex {
            display: flex;
        }

        .cell .value .v-input {
            width: 79%;
            min-width: 335px;
            margin-right: 1%;
            display: flex;
        }

        .cell .value .v-operate {
            width: 20%;
            min-width: 140px;
            display: flex;
            align-items: center;
        }

        .cell .value .v-input input {
            padding: 9px 1.5%!important;
        }

        .cell .value .v-input input.input-bx-1 {
            width: 19%;
            margin-right: 1%;
        }

        .cell .value .v-input input.input-bx-2 {
            width: 80%;
        }

        .cell .value .v-input input.input-bx-3 {
            width: 19%;
            margin-right: 1%;
        }
        .cell .value .v-input input.input-bx-3_1{
            width: 20%;
            margin-right: 1%;
        }
        .cell .value .v-input input.input-bx-4 {
            width: 64%;
        }

        .cell .value .v-operate .v-p-upload {
            width: 76px;
            padding-left: 28px;
            margin-right: 10px;
            background:url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAB20lEQVRIS+2VMVLbQBiF3yOTeCYpICcIOUFUGrmIOQGmwaIKnCBwAvAJQk6AOiwa4ARxCmvoAjdQbmAKMwPM6DErY0WRdy2pSJetNLv//t/+b9+vJf7xYN38/rk8CFvzeALJ9A2ubrY5WZajEuCfaw8pjkis2xJJCFNhcL3LxLa+FNCJFAL4UqPKiYjNeIc35VgnoEHyeU4rxAowslA4rXHyv0IkJHHAj8VJO2CohMSHpgATL2I/3qGRNhsLAOMWCr8cF/o727QELuEqDthzAjpDHYD4tgAQbqctdM38uweMQHxyVDgZ9/neXUGkYwJHxc0C7tIU3tyK3oXW3j4iIbBqg4z7zJXJP9pnWiextQL0wNlJ8yEcjgOeFKeclQJYALy4xsiyVj5RWdPiuh9pROBz6TC344BeLlH7TN1XK/jhutT7FjzX78BmCAGDuM/jHOAPdWmkcV0YgFDAJE3x83qXIxM3/y9xVvFeuXLTDyDCuM8BO5FUx+8SvscBDzKAxQhWBYAGgELptQFCwgqJCkb6o21dQNaUyzq35I7RtIVtc+H+UCckvlZJaxyY9cFGpB6B0NU4VYlcDkyFbt5oWXc+oQfZH5ZGECK5f41LU23li9YosSX4P6BSwWfsQc2s7SSniQAAAABJRU5ErkJggg==');
            background-repeat: no-repeat;
            background-position: center left;
            color: #3BA9FF;
            cursor: pointer;
        }

        .cell .value .v-operate .v-p-files {
            color: #3BA9FF;
            cursor: pointer;
        }

        .div_disabled div{
            pointer-events: none;
            background-color: #eee;
        }
        .label-bars {
            width: 100%;
            display: flex;
            flex-wrap: wrap;
        }

        .label-bars .label-bar {
            padding: 0 12px;
            margin: 5px;
            height: 36px;
            line-height: 36px;
            color: #3BA9FF;
            border: 1px solid #3BA9FF;
            background-color: #fff;
            font-size: 14px;
            cursor: pointer;
        }

        .label-bars .label-bar.bType {
            display: none;
        }


        .label-bars .label-bar.active {
            color: #fff;
            background-color: #3BA9FF;
        }
        /* ---------0429--------- */

        .layui-layer-btn .layui-layer-btn0{
            color:#333!important;
            background-color: #fff!important;
            border-color: #dedede!important;
        }
        .layui-layer-btn .layui-layer-btn1{
            color: #fff!important;
            background-color: #1e90ff!important;
            border-color:#1e90ff!important;
        }
        .d-text{
            height: 250px!important;
            margin: 10px 0;
            overflow: auto;
        }
    </style>
</head>
<body>
<%--左侧页面信息--%>
<div class="contain">

    <div class="first">
        <iframe name="fileMidIframe" width="100%" height="99%" src="${ctx}/survey/case/sic/operateView?btnCode=directionFileMid&id=${id}&directionId=${directionId}&surveyInfoId=${dto.surveyRiskCaseInfo.id}&surveyCno=${dto.surveyRiskCaseInfo.surveyCno}&surveyId=${dto.surveyId}" style="border: none; "></iframe>
    </div>
    <div class="body-right">
        <form id="editForm" role="form" action="${ctx}/survey/case/sic/operate" method="post">
        <input type="hidden" name="id" id="cruxId" value="${id}" />
        <input type="hidden" name="btnCode" value="${btnCode}" />
        <input type="hidden" name="tsId" value="${tsId}" />
        <input type="hidden" name="menuCode" value="${menuCode}" />
        <input type="hidden" name="surveyState" value="${dto.surveyState}" />
        <input type="hidden" id="files" name="files"  value="">
        <%--<input type="hidden" id="isSun" name="isSun"  value="">--%>
        <input hidden name="feeOpr" id="feeOpr" value="${feeOpr}">
        <input hidden name="reState" value="${dto.reState}">
        <input hidden name="haveReimbursement" value="0">
        <input hidden name="directionhaveReimbursement" value="${direction.haveReimbursement}">
        <input type="hidden" name="showBaoSi" value="${showBaoSi}" />
        <input type="hidden" name="medicalHistoryFiles" value="" />
        <input type="hidden" name="troubleshootingFiles" value="" />
        <input type="hidden" name="printingFiles" value="" />
        <input type="hidden" name="accommodatioFiles" value="" />
        <input type="hidden" name="trainFiles" value="" />
        <input type="hidden" name="airFiles" value="" />
        <input type="hidden" name="carFiles" value="" />
        <input type="hidden" name="selfDrivingFiles" value="" />
        <input type="hidden" name="otherMoneyFiles" value="" />
        <input type="hidden" name="surveyCno" value="${dto.surveyRiskCaseInfo.surveyCno}" />
        <input type="hidden" name="surveyInfoId" id="surveyInfoId" value="${dto.surveyRiskCaseInfo.id}" />
        <div class="title"><div class="icon-step"  style="width: 30px;display: inline-block; margin-right: 8px;"><div class="index active">2</div></div>填写方向内容</div>
        <div class="form">
            <div class="cell" style="display: none;">
                <div id="l-map"></div>
                <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
            </div>
            <div class="cell">
                <div class="label">方向名称</div>
                <div class="value">
                    <c:if test="${direction.orgPoint == 1}">
                        <input type="text" style="background-color: #eee;" value="${direction.directionName}" readonly disabled />
                    </c:if>
                    <div <c:if test="${direction.orgPoint == 1}">style="display: none;" </c:if> >
                        <input type="text" id="directionName" class="direction" name="directionName" value="${direction.directionName}"  onblur="setSurveyReason(this)" />
                        <%--验证不重复的方向名称--%>
                        <input type="hidden" id="uploadDirectionName" name="uploadDirectionName" class="" value="${direction.directionName}" />
                    </div>
                </div>
            </div>
            <div class="cell">
                <div class="label">地址信息</div>
                <%--onblur="selectByAreaName()"--%>
                <div class="value"  data-id="address"><input type="text" class="direction" placeholder="请输入区县名或城市名"  id="areaName" name="areaName" value="${direction.district} ${direction.city} ${direction.province}" onfocus="selectByAreaName()"  autocomplete="off">
                    <div class="listHistory" style="display: none">
                        <c:forEach items="${directionAreaHistory}" var="item">
                            <div class="li li-hover" style="cursor:pointer" onclick="changeArea(${item.districtId},${item.cityId},${item.provinceId},'${item.district}','${item.city}','${item.province}',${item.cityType},${item.areaType},'${item.cityTypeName}')">
                                    ${item.district}${item.city}${item.province}<span>${item.cityTypeName}</span>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="list" id="tr_formerCase" style="display: none">

                    </div>
                </div>
                <%--预加载所有数据：如同list中保险公司的筛选方式--%>
                <%--<div class="value">--%>
                    <%--<select name="areaId" id="areaId" class="singleSelect form-control" onchange="changeArea1()">--%>

                        <%--<c:forEach items="${commonAreaDtos}" var="item">--%>
                            <%--<option--%>
                                    <%--<c:if test="${direction.districtId != 0}">--%>
                                        <%--<c:if test="${direction.districtId == item.areaId}">selected="selected" </c:if>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${direction.districtId == 0}">--%>
                                        <%--<c:if test="${direction.cityId == item.areaId2 && item.areaId==0}">selected="selected" </c:if>--%>
                                    <%--</c:if>--%>
                                <%--value="${item.areaId},${item.areaId2},${item.areaId3},${item.areaName1},${item.areaName2},${item.areaName3},${item.cityType},${item.areaType},${item.cityTypeName}" >${item.areaName1}${item.areaName2}${item.areaName3}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="float:right;">${item.cityTypeName}</span></option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                <%--</div>--%>
                <input type="hidden" id="province" name="province" value="${direction.province}">
                <input type="hidden" id="city" name="city" value="${direction.city}">
                <input type="hidden" id="district" name="district" value="${direction.district}">
                <input type="hidden" id="provinceId" name="provinceId" value="${direction.provinceId}">
                <input type="hidden" id="cityId" name="cityId" value="${direction.cityId}">
                <input type="hidden" id="districtId" name="districtId" value="${direction.districtId}">
                <input type="hidden" id="areaType" name="areaType" value="${direction.areaType}">
                <input type="hidden" id="regionType" name="regionType" value="${direction.regionType}">
                <input type="hidden" id="directionId" name="directionId" value="${direction.id}">
                <input type="hidden" id="orgPoint" name="orgPoint" value="${direction.orgPoint}">

                <input type="hidden" id="taskNameS" name="taskNameS" value="${dto.tasks}">

                <%--历史记录数据--%>
                <input type="hidden" id="hisCityType" name="hisCityType" value="">
                <input type="hidden" id="hisCityTypeName" name="hisCityTypeName" value="">
                <input type="hidden" id="hisAreaType" name="hisAreaType" value="">
            </div>
            <div class="cell pd0">
                <div class="label">任务类型</div>
                <div class="value">
                    <div class="btns radios">
                        <c:forEach items="${dto.tasks}" var="item">
                            <div <c:if test="${direction.taskId != null}">
                                    <c:if test="${item.taskId == direction.taskId}">class="item active" </c:if>
                                    <c:if test="${item.taskId != direction.taskId}">class="item" </c:if>
                                 </c:if>
                                 style="cursor:pointer"
                                 onclick="initNewName(${item.taskId})" id="task_${item.taskId}" data-value="${item.taskId}" name="taskIds" style="word-break: break-all; word-wrap: break-word;height: auto ">
                                    ${item.taskName}
                            </div>
                        </c:forEach>
                    </div>
                    <input type="hidden" value="${direction.taskId}" id="taskId" name="taskId" required="required"/>
                    <input type="hidden" value="${direction.taskId}" id="curTaskId" required="required"/>
                </div>
            </div>
            <div class="cell pd0" id="taskInfoContent" <c:if test="${direction.newId ==null || direction.newId ==0}"> style="display: none" </c:if>>
                <div class="label">任务子类</div>
                <div class="value">
                    <div class="btns radios" id="newIdList">
                        <c:forEach items="${taskInfoContents}" var="item">
                            <div
                                <c:if test="${item.id == direction.newId}">class="item active" </c:if>
                                <c:if test="${item.id != direction.newId}">class="item" </c:if>
                                style="cursor:pointer"
                                onclick="choiceNewIds(${item.id})" id="newId_${item.id}"  name="newIds" >
                                ${item.name}
                            </div>
                        </c:forEach>
                    </div>
                    <input type="hidden" value="${direction.newId}" id="newId" name="newId" required="required"/>
                </div>
            </div>

            <%--<div class="cell" <c:if test="${direction.newId !=301}">style="display: none" </c:if>  id="directionModel">--%>
                <%--<div class="label">内容模板</div>--%>
                <%--<div class="value">--%>
                    <%--<div class="btns">--%>
                        <%--<div class="item active">有结果</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="cell pd0" style="display: none" id="directionModel">
                <div class="label">任务结果</div>
                <div class="value">
                    <div class="btns radios" id="directionResultTypeIdList">
                        <input type="hidden" value="${direction.directionResultTypeId}" id="directionResultTypeId" name="directionResultTypeId"/>
                        <input type="hidden" value="${direction.directionResultTypeCode}" id="directionResultTypeCode" name="directionResultTypeCode"/>
                    </div>
                </div>
            </div>
            <div class="cell" id="directionScoreDiv" style="display:none">
                <div class="label">方向分值</div>
                <div class="value"><input name="directionScore" id="directionScore" type="text" class="form-control" disabled="disabled"></div>
            </div>
            <div class="cell" style="display: none" id="medical">
                <div class="label">病历数量</div>
                <div class="value">
                    <div class="btns radios">
                        <input id="min" name="" type="button" value="-" style="width: 40px;background-color: #3FB5FD;padding: 0;color: #fff"/>
                        <input id="text_box" name="" type="text" value="${direction.medicalNumber}" style="width: 50px; text-align: center;border:0px solid #3FB5FD;padding:0; " onblur="reckon()"/>
                        <input id="add" name="" type="button" value="+" style="width: 40px;background-color: #3FB5FD;padding: 0;color: #fff"/>
                    </div>
                    <input type="hidden" value="${direction.medicalNumber}" id="medicalNumber" name="medicalNumber"/>
                </div>
            </div>
            <div class="cell" style="display: none" id="recognition">
                <div class="label">识别结果</div>
                <div class="value">
                    <div class="texts">
                        <textarea name="recognitionResult" id ="recognitionResult" style="height: 150px;background-color:rgba(63, 181, 253, 0.05);cursor: auto " class="form-control" readonly onkeyup="adjustObjHeight(this, 50);"></textarea>
                    </div>
                    <div style="font: 10px; color:grey ">识别结果仅供复制粘贴使用，不保存在最终方向结果中</div>
                </div>
            </div>
            <div class="cell">
                <div class="label">方向内容</div>
                <div class="value d-content">
                    <div class="texts">
                        <textarea name="directionText" id ="directionText" style="height: 250px"  required="required" class="form-control">${direction.directionText}</textarea>
                    </div>
                </div>
            </div>
            <div class="cell">
                <div class="label">调查依据</div>
                <div class="value">
                    <input type="text" id="surveyReason"  class="direction" name="surveyReason" placeholder="必填，且不可和方向名称重复。如“华东医院病史2份”"  class="" autocomplete="off" value="${direction.surveyReason}" />
                </div>
            </div>
            <div class="cell">
                <div class="label">材料原件</div>
                <div class="value">
                    <input type="hidden" name="materRaw" value="${direction.materRaw}"/>
                    <div class="label-bars materRaw"  id="materRaw" data-value="${direction.materRaw}">
                        <div class="label-bar <c:if test="${direction.materRaw ==1}">active</c:if>" data-id="1">有</div>
                        <div class="label-bar <c:if test="${direction.materRaw ==0}">active</c:if>" data-id="0">无</div>
                    </div>
                </div>

            </div>
            <div class="cell">
                <div class="label">材料页数</div>
                <div class="value">
                    <input  type="number" id="materRawNumber" name="materRawNumber" value="${direction.materRawNumber}"/>
                </div>
            </div>
            <div class="cell">
                <div class="label">是否阳性</div>
                <div class="value">
                    <input type="hidden" name="isSun" value="${direction.sun}"/>
                    <div class="label-bars isSun"  id="isSun" data-value="${direction.sun}">
                        <div class="label-bar <c:if test="${direction.sun ==0}">active</c:if>" data-id="0">否</div>
                        <div class="label-bar <c:if test="${direction.sun ==1}">active</c:if>" data-id="1">是</div>
                    </div>
                </div>
            </div>
            <!-- /*------------0429-----------------*/ -->
<%--            <c:if test="${showBaoSi}">--%>
<%--                <div class="cell">--%>
<%--                    <div class="label">费用报销</div>--%>
<%--                    <div class="value">--%>
<%--                        <div class="btns-bx" data-type="bx">--%>
<%--                            <c:if test="${direction.haveReimbursement == 1}">--%>
<%--                                <div class="item" data-id="1">无</div>--%>
<%--                                <div class="item active" data-id="2">有</div>--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${direction.haveReimbursement == 0}">--%>
<%--                                <div class="item active" data-id="1">无</div>--%>
<%--                                <div class="item " data-id="2">有</div>--%>
<%--                            </c:if>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </c:if>--%>
<%--            <div class="cell cell-bx">--%>
<%--                <div class="label">病史费（含复印费）</div>--%>
<%--                <div class="value d-flex">--%>
<%--                    <div class="v-input">--%>
<%--                        <input type="number" name="medicalHistoryMoney" class="input-bx-1" placeholder="金额（选填）"--%>
<%--                               value="${surveyReimbursementInfo.medicalHistoryMoney!='0.0'?surveyReimbursementInfo.medicalHistoryMoney:''}"--%>

<%--                        >--%>
<%--                        <input type="text" name="medicalHistoryDesc" class="input-bx-2" placeholder="请输入备注信息（非必填）"  value="${surveyReimbursementInfo.medicalHistoryDesc}">--%>
<%--                    </div>--%>
<%--                    <div class="v-operate">--%>
<%--                        <!-- <div class="v-p-upload" lay-submit="" >上传凭证</div> -->--%>
<%--                        <button type="button"  data-id="1" class="layui-btn layui-btn-normal layui-btn-sm layui-btn-import v-p-upload"--%>
<%--                        >上传凭证</button>--%>
<%--                        <div class="v-p-files" data-id="1" data-type="medh" data-value="${surveyReimbursementInfo.surveyReimbursementFileDto.medhCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.medhCount:0}">凭证(${surveyReimbursementInfo.surveyReimbursementFileDto.medhCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.medhCount:0})</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="cell cell-bx">--%>
<%--                <div class="label">排查费用</div>--%>
<%--                <div class="value d-flex">--%>
<%--                    <div class="v-input">--%>
<%--                        <input type="number" name="troubleshootingMoney" class="input-bx-1" placeholder="金额（选填）"  value="${surveyReimbursementInfo.troubleshootingMoney!='0.0'?surveyReimbursementInfo.troubleshootingMoney:''}">--%>
<%--                        <input type="text" name="troubleshootingDesc" class="input-bx-2" placeholder="请输入备注信息（非必填）"  value="${surveyReimbursementInfo.troubleshootingDesc}">--%>
<%--                    </div>--%>
<%--                    <div class="v-operate" >--%>
<%--                        <button type="button" data-id="2"  class="layui-btn layui-btn-normal layui-btn-sm layui-btn-import v-p-upload" >上传凭证</button>--%>
<%--                        <div class="v-p-files" data-id="2" data-type="trou"  data-value="${surveyReimbursementInfo.surveyReimbursementFileDto.trouCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.trouCount:0}">凭证(${surveyReimbursementInfo.surveyReimbursementFileDto.trouCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.trouCount:0})</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="cell cell-bx">--%>
<%--                <div class="label">体检报告打印费</div>--%>
<%--                <div class="value d-flex">--%>
<%--                    <div class="v-input">--%>
<%--                        <input type="number" name="printingMoney" class="input-bx-1" placeholder="金额（选填）"  value="${surveyReimbursementInfo.printingMoney!='0.0'?surveyReimbursementInfo.printingMoney:''}">--%>
<%--                        <input type="text" name="printingDesc" class="input-bx-2" placeholder="请输入备注信息（非必填）"  value="${surveyReimbursementInfo.printingDesc}">--%>
<%--                    </div>--%>
<%--                    <div class="v-operate">--%>
<%--                        <button type="button" data-id="3" class="layui-btn layui-btn-normal layui-btn-sm layui-btn-import v-p-upload" >上传凭证</button>--%>
<%--                        <div class="v-p-files" data-id="3" data-type="print"  data-value="${surveyReimbursementInfo.surveyReimbursementFileDto.printCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.printCount:0}">凭证(${surveyReimbursementInfo.surveyReimbursementFileDto.printCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.printCount:0})</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="cell cell-bx">--%>
<%--                <div class="label">住宿费</div>--%>
<%--                <div class="value d-flex">--%>
<%--                    <div class="v-input">--%>
<%--                        <input type="text" name="accommodatioDays" class="input-bx-3_1" placeholder="天数(选填)"  value="${surveyReimbursementInfo.accommodatioDays!='0'?surveyReimbursementInfo.accommodatioDays:''}"  onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">--%>
<%--                        <input type="number" name="accommodatioMoney" class="input-bx-3_1" placeholder="金额(选填)"  value="${surveyReimbursementInfo.accommodatioMoney!='0.0'?surveyReimbursementInfo.accommodatioMoney:''}">--%>
<%--                        <input type="text" name="accommodatioDesc" class="input-bx-4" placeholder="请输入备注信息(非必填)"  value="${surveyReimbursementInfo.accommodatioDesc}">--%>
<%--                    </div>--%>
<%--                    <div class="v-operate">--%>
<%--                        <button type="button" data-id="4" class="layui-btn layui-btn-normal layui-btn-sm layui-btn-import v-p-upload" >上传凭证</button>--%>
<%--                        <div class="v-p-files" data-id="4" data-type="acco"  data-value="${surveyReimbursementInfo.surveyReimbursementFileDto.accoCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.accoCount:0}">凭证(${surveyReimbursementInfo.surveyReimbursementFileDto.accoCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.accoCount:0})</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="cell cell-bx">--%>
<%--                <div class="label">跨地市交通费（汽车）</div>--%>
<%--                <div class="value d-flex">--%>
<%--                    <div class="v-input">--%>
<%--                        <input type="number" name="carMoney" class="input-bx-1" placeholder="金额（选填）"  value="${surveyReimbursementInfo.carMoney!='0.0'?surveyReimbursementInfo.carMoney:''}">--%>
<%--                        <input type="text" name="carDesc" class="input-bx-2" placeholder="请输入备注信息（非必填）"  value="${surveyReimbursementInfo.carDesc}">--%>
<%--                    </div>--%>
<%--                    <div class="v-operate">--%>
<%--                        <button type="button"  data-id="5" class="layui-btn layui-btn-normal layui-btn-sm layui-btn-import v-p-upload" >上传凭证</button>--%>
<%--                        <div class="v-p-files"  data-id="5" data-type="car"  data-value="${surveyReimbursementInfo.surveyReimbursementFileDto.carCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.carCount:0}">凭证(${surveyReimbursementInfo.surveyReimbursementFileDto.carCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.carCount:0})</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="cell cell-bx">--%>
<%--                <div class="label">跨地市交通费（火车）</div>--%>
<%--                <div class="value d-flex">--%>
<%--                    <div class="v-input">--%>
<%--                        <input type="number" name="trainMoney" class="input-bx-1" placeholder="金额（选填）"  value="${surveyReimbursementInfo.trainMoney!='0.0'?surveyReimbursementInfo.trainMoney:''}">--%>
<%--                        <input type="text" name="trainDesc" class="input-bx-2" placeholder="请输入备注信息（非必填）"  value="${surveyReimbursementInfo.trainDesc}">--%>
<%--                    </div>--%>
<%--                    <div class="v-operate">--%>
<%--                        <button type="button" data-id="6" class="layui-btn layui-btn-normal layui-btn-sm layui-btn-import v-p-upload" >上传凭证</button>--%>
<%--                        <div class="v-p-files" data-id="6" data-type="train"  data-value="${surveyReimbursementInfo.surveyReimbursementFileDto.trainCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.trainCount:0}">凭证(${surveyReimbursementInfo.surveyReimbursementFileDto.trainCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.trainCount:0})</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="cell cell-bx">--%>
<%--                <div class="label">跨地市交通费（飞机）</div>--%>
<%--                <div class="value d-flex">--%>
<%--                    <div class="v-input">--%>
<%--                        <input type="number" name="aircraftMoney" class="input-bx-1" placeholder="金额（选填）"  value="${surveyReimbursementInfo.aircraftMoney!='0.0'?surveyReimbursementInfo.aircraftMoney:''}">--%>
<%--                        <input type="text" name="aircraftDesc" class="input-bx-2" placeholder="请输入备注信息（非必填）"  value="${surveyReimbursementInfo.aircraftDesc}">--%>
<%--                    </div>--%>
<%--                    <div class="v-operate" >--%>
<%--                        <button type="button" data-id="7" class="layui-btn layui-btn-normal layui-btn-sm layui-btn-import v-p-upload" >上传凭证</button>--%>
<%--                        <div class="v-p-files" data-id="7" data-type="aircraft"  data-value="${surveyReimbursementInfo.surveyReimbursementFileDto.aircraftCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.aircraftCount:0}">凭证(${surveyReimbursementInfo.surveyReimbursementFileDto.aircraftCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.aircraftCount:0})</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="cell cell-bx">--%>
<%--                <div class="label">跨地市交通费（自驾）</div>--%>
<%--                <div class="value d-flex">--%>
<%--                    <div class="v-input">--%>
<%--                        <input type="number" name="kilometresNum" class="input-bx-3" placeholder="公里数(选填)" step="0.01" value="${surveyReimbursementInfo.kilometresNum!='0'?surveyReimbursementInfo.kilometresNum:''}">--%>
<%--                        <input type="text" name="selfDrivingMoney" class="input-bx-3" readonly placeholder="金额(选填)"  value="${surveyReimbursementInfo.selfDrivingMoney!='0.0'?surveyReimbursementInfo.selfDrivingMoney:''}">--%>
<%--                        <input type="text" name="selfDrivingDesc" class="input-bx-4" placeholder="请输入备注信息（非必填）"  value="${surveyReimbursementInfo.selfDrivingDesc}">--%>
<%--                    </div>--%>
<%--                    <div class="v-operate">--%>
<%--                        <button type="button" data-id="8" class="layui-btn layui-btn-normal layui-btn-sm layui-btn-import v-p-upload" >上传凭证</button>--%>
<%--                        <div class="v-p-files" data-id="8" data-type="self"  data-value="${surveyReimbursementInfo.surveyReimbursementFileDto.selfCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.selfCount:0}">凭证(${surveyReimbursementInfo.surveyReimbursementFileDto.selfCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.selfCount:0})</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="cell cell-bx">--%>
<%--                <div class="label">其他费用</div>--%>
<%--                <div class="value d-flex">--%>
<%--                    <div class="v-input">--%>
<%--                        <input type="number" name="otherMoney" class="input-bx-1" placeholder="金额（选填）"   value="${surveyReimbursementInfo.otherMoney!='0.0'?surveyReimbursementInfo.otherMoney:''}">--%>
<%--                        <input type="text" name="otherDesc" class="input-bx-2" placeholder="请输入备注信息（存在其他费用时必填）"  value="${surveyReimbursementInfo.otherDesc}">--%>
<%--                    </div>--%>
<%--                    <div class="v-operate">--%>
<%--                        <button type="button" data-id="9"  id="othersss" class="layui-btn layui-btn-normal layui-btn-sm layui-btn-import v-p-upload" >上传凭证</button>--%>
<%--                        <div class="v-p-files" data-id="9" data-type="other"  data-value="${surveyReimbursementInfo.surveyReimbursementFileDto.otherCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.otherCount:0}">凭证(${surveyReimbursementInfo.surveyReimbursementFileDto.otherCount!=undefinde?surveyReimbursementInfo.surveyReimbursementFileDto.otherCount:0})</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
            <button type="button" class="layui-none" id="uploadFile"></button>
        </div>
        <div class="form-btns">
            <%--<div class='form-btn'>--%>
                <button type="button" name="btnUpload" onclick="return validFile('${btnCode}');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>确定</button>&nbsp&nbsp&nbsp
                <%--保存并关闭--%>
            <%--</div>--%>
                <button type="button" name="btnUpload" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>&nbsp&nbsp&nbsp

                <input type="hidden" name="successDirection" id="successDirection" />
                <c:if test="${menuCode!=null && menuCode == 'dcy-list'}">
                    <button type="button" name="btnUpload" id="successDirectionBtn" onclick="return validFile('successDirectionPopup');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>已完成全部方向</button>
                </c:if>
        </div>
            <br><br>
            <div class="dalogs">
                <div class="close" onclick="javascript:$('.dalogs').hide()">×</div>
                <div class="d_content" style="padding: 20px;"></div>
            </div>
        </form>

    </div>
</div>

<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/layui/layui.js"></script>

<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
<script type="text/javascript" src="//api.map.baidu.com/api?v=2.0&ak=QkRO8ju3UC9XVXbQITdaB4y7Vo73X89D"></script>

<script type="text/javascript">
    var t = $("input[name=showBaoSi]").val();
    var directionhaveReimbursement = $("input[name=directionhaveReimbursement]").val();
    if ( t == 'true' && directionhaveReimbursement == 1){
        $('.cell-bx').css({
            display: 'flex'
        })
    }else {
        $("input[name=haveReimbursement]").val(0);
    }
$(function () {
    var dNumber = 0
    var clientHeight = document.documentElement.clientHeight
    console.log(clientHeight);
    $('.contain').height(clientHeight - 20)
})
$(document).ready(function(){
    $('.singleSelect').select2();
});
    var editor1;
    <%--KindEditor.ready(function(K) {--%>
        <%--editor1 = K.create('textarea[name="content"]', {--%>
            <%--cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',--%>
            <%--uploadJson : '${ctx}/uploadFileForKindEditor'--%>
        <%--});--%>
    <%--});--%>

    $("#editForm").bind('submit', function(event) {
        $("[name=btnUpload]").attr("disabled","true");
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,returnCallback,null,null,function(event,param){
            var apiRsp=getApiJson(param.data);
            if(apiRsp && apiRsp.isSuccess){

            }else{
                $("[name=btnUpload]").removeAttr("disabled");
                alert(apiRsp.msg);return;
            }
        });
        event.preventDefault();
    });

    $('.isSun .label-bar').click(function(){
        var _this = $(this)
        if (!_this.hasClass('active')){
            _this.addClass('active')
            $('.isSun').attr('data-value',_this.attr('data-id'))
            _this.siblings().removeClass('active')
        }
    })

    $('.materRaw .label-bar').click(function(){
        var _this = $(this)
        if (!_this.hasClass('active')){
            _this.addClass('active')
            $('.materRaw').attr('data-value',_this.attr('data-id'))
            _this.siblings().removeClass('active')
        }
    })



    function validFile(btnCode){
        $('.form-btns button').attr('disabled',true)
        setTimeout(function () {
            $('.form-btns button').removeAttr('disabled')
        },3000)
        var isSun = $("#isSun").attr("data-value");
        $("input[name=isSun]").val(isSun);

        var materRaw = $("#materRaw").attr("data-value");
        $("input[name=materRaw]").val(materRaw);
        //验证方向名称是否存在
        var url = "${ctx}/survey/case/operate",param = {
            "btnCode" : "validateDirectionName",
            "id" : $("#surveyInfoId").val(),
            "directionName" : $("#directionName").val(),
            "directionId" : $("#directionId").val()
        };
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var results = e.data.results;
                if(results){
                    if (results.length > 0){
                        alert("方向名称已存在");
                        $(this).focus();
                        return;
                    }
                }

                if (!$("#surveyReason").val() || $("#surveyReason").val() == $("#directionName").val()) {
                    alert("调查依据不可和方向名称相同且调查依据必填");return false;
                }
                var materRaw = $("#materRaw").attr("data-value");
                if (materRaw == 1){
                    //
                    if($("#materRawNumber").val() <= 0){
                        alert("材料原件不能为0");return false;
                    }
                }

                if(btnCode == 'upload' || btnCode == 'primary'){
                    var path = $("#path").val();
                    if(!path){
                        alert("请上传报告");return false;
                    }
                }else if(btnCode == 'direction' || btnCode == 'successDirection' || btnCode == 'successDirectionPopup' || btnCode == 'directionAgain'){
                    //获取iframe页面的图片信息
                    var fileList = window.frames["fileMidIframe"].document.getElementById("fileList").value;
                    $("#files").val(fileList);

                    var areaType = $("#areaType").val();
                    if(!areaType || $("#areaType").val() == 0){
                        alert("请选择地址");return false;
                    }

                    var have = false;
                    $("div[name=taskIds]").each(function(){
                        if ($(this).attr("class") == "item active") {
                            have = true;
                            $("#taskId").val($(this).attr("data-value"));
                        }
                    })
                    if(!have){
                        alert("请选择任务类型");return false;
                    }
                    var newId = $("#newId").val();
                    if(!newId || newId == 0){
                        alert("请选择任务子类");return false;
                    }
                    var directionName = $("#directionName").val();
                    if(!directionName){
                        alert("请填写方向名称");return false;
                    }
                    var directionResultTypeId = $("#directionResultTypeId").val();
                    if(!directionResultTypeId || directionResultTypeId == 0){
                        alert("请选择任务结果");return false;
                    }
                    //判断是否添加了费用报销内容
                    $(".btns-bx div").each(function(){
                        var flag = $(this).hasClass("active");
                        if (flag && $(this).attr("data-id")==2){
                            if ($("input[name=otherMoney]").val()!='' && $("input[name=otherMarks]").val()==''){
                                alert("存在其他费用时，备注信息为必填");return false;
                            }
                            $("input[name=haveReimbursement]").val("1");
                        }
                    });

                    var haveReimbursement = $("input[name=haveReimbursement]").val();
                    if (haveReimbursement ==1){
                        //金额
                        var medicalHistoryMoney = $("input[name=medicalHistoryMoney]").val();
                        var troubleshootingMoney = $("input[name=troubleshootingMoney]").val();
                        var printingMoney = $("input[name=printingMoney]").val();
                        var accommodatioMoney = $("input[name=accommodatioMoney]").val();
                        var carMoney = $("input[name=carMoney]").val();
                        var trainMoney = $("input[name=trainMoney]").val();
                        var aircraftMoney = $("input[name=aircraftMoney]").val();
                        var selfDrivingMoney = $("input[name=selfDrivingMoney]").val();
                        var otherMoney = $("input[name=otherMoney]").val();
                        var otherDesc = $("input[name=otherDesc]").val();
                        var accommodatioDays = $("input[name=accommodatioDays]").val();
                        // debugger
                        var medicalHistoryFiles = $(".v-p-files").eq(0).attr("data-value");
                        var troubleshootingFiles = $(".v-p-files").eq(1).attr("data-value");
                        var printingFiles = $(".v-p-files").eq(2).attr("data-value");
                        var accommodatioFiles = $(".v-p-files").eq(3).attr("data-value");
                        var carFiles = $(".v-p-files").eq(4).attr("data-value");
                        var trainFiles = $(".v-p-files").eq(5).attr("data-value");
                        var airFiles = $(".v-p-files").eq(6).attr("data-value");
                        var selfDrivingFiles = $(".v-p-files").eq(7).attr("data-value");
                        var otherMoneyFiles = $(".v-p-files").eq(8).attr("data-value");
                        if (medicalHistoryMoney != '' && medicalHistoryFiles == '0'){
                            alert("病史费凭证不能为空")
                            return false;
                        }
                        if (medicalHistoryMoney == '' && medicalHistoryFiles != '0'){
                            alert("病史费不能为空")
                            return false;
                        }
                        if (troubleshootingMoney != '' && troubleshootingFiles == '0'){
                            alert("排查凭证不能为空")
                            return false;
                        }
                        if (troubleshootingMoney == '' && troubleshootingFiles != '0'){
                            alert("排查费不能为空")
                            return false;
                        }
                        if (printingMoney != '' && printingFiles == '0'){
                            alert("体检凭证不能为空")
                            return false;
                        }
                        if (printingMoney == '' && printingFiles != '0'){
                            alert("体检费不能为空")
                            return false;
                        }


                        if (accommodatioMoney != '' && accommodatioFiles == '0'){
                            alert("住宿凭证不能为空")
                            return false;
                        }
                        if (accommodatioMoney == '' && accommodatioFiles != '0'){
                            alert("住宿费不能为空")
                            return false;
                        }
                        if (accommodatioDays=='' && accommodatioMoney!=''){
                            alert("住宿天数不能为空")
                            return false;
                        }
                        if (accommodatioMoney=='' && accommodatioDays!=''){
                            alert("住宿金额不能为空")
                            return false;
                        }
                        if (parseFloat(accommodatioMoney)/parseFloat(accommodatioDays)>130){
                            alert("住宿费金额每天不可以超过130")
                            return false;
                        }
                        if (trainMoney != '' && trainFiles == '0'){
                            alert("火车凭证不能为空")
                            return false;
                        }
                        if (trainMoney == '' && trainFiles != '0'){
                            alert("火车费不能为空")
                            return false;
                        }
                        if (carMoney != '' && carFiles == '0'){
                            alert("汽车凭证不能为空")
                            return false;
                        }
                        if (carMoney == '' && carFiles != '0'){
                            alert("汽车费不能为空")
                            return false;
                        }
                        if (aircraftMoney != '' && airFiles == '0'){
                            alert("飞机凭证不能为空")
                            return false;
                        }
                        if (aircraftMoney == '' && airFiles != '0'){
                            alert("飞机费不能为空")
                            return false;
                        }
                        if (selfDrivingMoney != '' && selfDrivingFiles == '0'){
                            alert("自驾凭证不能为空")
                            return false;
                        }
                        if (selfDrivingMoney == '' && selfDrivingFiles != '0'){
                            alert("自驾费不能为空")
                            return false;
                        }
                        if (otherMoney != '' && otherDesc == ''){
                            alert("其他费用备注不能为空")
                            return false;
                        }
                        if (otherMoney != '' && otherMoneyFiles == '0'){
                            alert("其他费用凭证不能为空")
                            return false;
                        }
                        if (otherMoney == '' && otherMoneyFiles != '0'){
                            alert("其他费用不能为空")
                            return false;
                        }
                    }

//            $("#files").val(JSON.stringify(files));
//                    if(btnCode == 'successDirectionPopup') {
//                        showDirectionView();
//                    }
                    /*if(btnCode == 'successDirection'){
                        var isSun = $("#isSun").val();
                        if(!isSun){
                            alert("请选择阳性情况");return false;
                        }
                        $('#successDirection').val('success');
                    }else{
                        $('#successDirection').val(null);
                    }*/

                    if(btnCode == 'successDirectionPopup') {
                        $('#successDirection').val('success');
                    }else{
                        $('#successDirection').val(null);
                    }
                    $("[name=btnUpload]").attr("disabled","true");
                    /*if(btnCode != 'successDirectionPopup'){
                        $("#editForm").submit();
                    }*/
                    //--
                    $('.d-content .d-text').detach()
                    $.ajax({
                        url: '${ctx}/survey/case/ajaxData',
                        data: {
                            text: $('#directionText').val(),
                            btnCode: 'direction-error'
                        },
                        success: function (e,res) {
                            console.log(res,JSON.parse(res.data.json))
                            var _data = JSON.parse(res.data.json) || ''
                            if (_data && _data.item && _data.item.vec_fragment.length){
                                var _flag = true
                                layer.confirm('该文本中有<span style="color: red">'+_data.item.vec_fragment.length+'</span>处错别字,已高亮显示，请注意处理', {
                                    title:'错别字提醒',
                                    btn:['内容没问题，继续提交','查看错别字']
                                }, function(index){
                                    if (_flag){
                                        _flag = false
                                        $("#editForm").submit();
                                    }
                                },function () {
                                    var str = $('#directionText').val()
                                    var _html = '<div class="d-text form-control">'
                                    _data.item.vec_fragment.map(function (cur) {
                                        var _v = cur.ori_frag
                                        str = str.replaceAll(_v,'<span style="background: red;color: #fff;cursor:pointer; ">'+cur.correct_frag+'</span>')
                                    })
                                    _html += str +'</div>'
                                    $('.d-content').append(_html)
                                    layer.closeAll();
                                })
                            }else{
                                $("#editForm").submit();
                            }
                        }
                    })
                }
                return true;
            }
        })
    }

    function load(){
    return;
    var url = "${ctx}/survey/case/operate",param = {
        "btnCode" : "dircetionFindTasks",
        "id" : $("#surveyInfoId").val(),
        "directionName" : $("#directionName").val().trim(),
        "directionId" : $("#directionId").val()
    };
    ajaxSubmit(url,param,function(v,e,p){
        if(e.data.code==='0000'){
            var tasks = e.data.results;
            if(tasks){
                console.log("tasks---------",tasks)
                if(tasks.length == 0){
                    $("div[name=taskIds]").each(function(){
                        if ($("#taskId").val() == $(this).attr("data-value")) {
                            $(this).addClass("active");
                        }else{
                            $(this).removeClass("active");
                        }
                        $(this).attr("style","");
                    })
                }
                for (var i = 0 ;i<tasks.length ;i ++ ){
                    //给div取消click事件
                    $("div[name=taskIds]").each(function(){
                        var itemTaskId = $(this).attr("data-value")
                        console.log("style:",tasks[i].id,itemTaskId,$(this).attr("style"))
                        if(tasks[i].id == itemTaskId){
                            $(this).removeClass("active");
                            // $(this).prop("readonly",true);
                            $(this).css({
                                "pointer-events":"none",
                                "color":'#fff',
                                "background-color":"#c7c2c2",
                                "border-color":"#c7c2c2"
                            });
                        }else{
                            console.log("style:",tasks[i].id == itemTaskId,$(this).attr("style"))
                            $(this).attr("style","");
                        }
                    })
                }
            }
        }
    })
}

    function showDirectionView(obj){
        var dialog = $(".dalogs");
        dialog.show();
//        var position = $("#successDirectionBtn").position();//以按钮为定位
//        $(".dalogs").offset({
//            left: position.left -200,
//            top: position.top - 350
//        });
        var html ="";
        html += '<div class="main22">';
        html += '<div class="step-contain">';

        html += '<div class="title">选择阳性情况</div>';
        html += '<div class="content">';
        html += '<div class="btn-area">';
        html += '<div class="c-btn" id="sun_0" name="sunYes" onclick="saveSun(0,true);">非阳性</div>';
        html += '<div class="c-btn" id="sun_1" name="sunYes" onclick="saveSun(1,true);">阳性</div>';
        html += '</div></div>';

        html += '<div class="title"></div>';
        html += '<div class="content">';
        html += '<div class="btn-area" style="padding-left:190px">';
//        html += '<div class="c-btn" onclick="javascript:hidDirectionView();">取消</div>';
        html += '<button type="button" class="btn btn-success loading-btn" name="add-direction-success" onclick="return addDirectionSuccess(\'successDirection\');">确定提交</button>';
        html += '</div></div></div></div>';
        $(".d_content").html(html);
    }

    function saveSun(type,bool){
        if(!bool){
            $("#sun_"+type).attr("class","c-btn");
            $("#sun_"+type).attr("onclick","saveSun('"+type+"',false)");
        }else{
            $("[name=sunYes]").attr("class","c-btn");
            $("#sun_"+type).attr("class","c-btn2");
            $("#sun_"+type).attr("onclick","saveSun('"+type+"',true)");
            var isSun = $("#isSun").val(type);
        }

    }
    function addDirectionSuccess(){
        var isSun = $("#isSun").val();
        if(!isSun){
            alert("请选择阳性情况");return false;
        }
        $('#successDirection').val('success');
        $("[name=add-direction-success]").attr("disabled","true");
        $("#editForm").submit();
    }

    function hidDirectionView(){
        var dialog = $(".dalogs");
        dialog.hide();
        $("[name=btnUpload]").removeAttr("disabled");
    }

    function returnCallback(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){

        }else{
            alert(apiRsp.msg);return;
        }
        reloadParent();
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

    <%--function setDataURL(){--%>
        <%--var taskValue = $("#taskId").val();--%>
        <%--var taskName = $("#taskId").find("option:selected").text();--%>
        <%--var directionName = $("#directionName").val();--%>
        <%--if(!taskValue || !taskName || !directionName){--%>
            <%--alert("请填写任务类型和方向名称");--%>
            <%--return false;--%>
        <%--}--%>
        <%--$('#fileuploadDirection').fileupload({--%>
            <%--url : "${ctx}/sftp/survey/uploadSftp?modelType=direction&surveyCno=${dto.surveyRiskCaseInfo.surveyCno}&taskName=" + taskName + "&directionName=" + directionName,--%>
            <%--done: function (e, data) {--%>
                <%--var value = "";--%>
                <%--var r  = data.result;--%>
                <%--var file = r.surveyFile;--%>
                <%--var value = "<a onclick='onlinePreview(\""+file.filePath+"\")' target='_blank'>" + file.fileName + "</a>&nbsp;&nbsp;&nbsp;&nbsp;"--%>
                <%--$("#uploadDiv").empty();--%>
                <%--values = values += value;--%>
                <%--$("#uploadDiv").append(values);--%>
                <%--$("#path").val(file.filePath);--%>
                <%--$("#fileRealName").val(file.fileName);--%>
                <%--$("#uploadPaths").val(value);--%>
                <%--$("#uploadDiv").show();--%>

                <%--var item = {--%>
                    <%--"fileName" : file.fileName,--%>
                    <%--"filePath" : file.filePath--%>
                <%--};--%>
                <%--files.push(item);--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>
    var files = [];
    var values = "";
    //材料凭证
//    $('#fileupload').fileupload({
//        done: function (e, data) {
//            var value = "";
//            var r  = data.result;
//            var file = r.surveyFile;
//            var value = "<a onclick='onlinePreview(\""+file.filePath+"\")' target='_blank'>" + file.fileName + "</a>&nbsp;&nbsp;&nbsp;&nbsp;"
//            $("#uploadDiv").empty();
//            values = values += value;
//            $("#uploadDiv").append(values);
//            $("#path").val(file.filePath);
//            $("#fileRealName").val(file.fileName);
//            $("#uploadPaths").val(value);
//            $("#uploadDiv").show();
//
//            var item = {
//                "fileName" : file.fileName,
//                "filePath" : file.filePath
//            };
//            files.push(item);
//        }
//    });

    //点击方向名称：加载任务子类
    function initNewName(id){
        $("[name=taskIds]").attr("class","item");
        $("#task_"+id).attr("class","item active");

        var taskId = id;
        if(!taskId){
            return;
        }
        $("#taskId").val(id);
        ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId",{"surveyCode":"taskInfo","btnCode":"1000","taskInfoId":taskId},function(v,e,p){
            $("#newIdList div").remove();
            if(e.data.results.length > 0){
                var val = e.data.results[0];
                if(e.data.results.length == 1){//如果只有一个任务子类，并且showState状态为“不展示”
                    if(val.showState == 0){
                        $("#taskInfoContent").hide();
                    }else{
                        $("#taskInfoContent").show();
                    }
                }else{
                    $("#taskInfoContent").show();
                }
                choiceNewIds(val.id);
            }else{
                $("#newId").val(null);
                $("#taskInfoContent").hide();
            }
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                var sort = val.sort;
                var contentId = val.id;
                var item = "item";
                if(sort == 1){
                    item = "item active";
                    $("#newId").val(contentId);
                }
                $("#newIdList").append("<div class='"+item+"' style='cursor:pointer' onclick='choiceNewIds("+val.id+")' id='newId_"+val.id+"' name='newIds' value='" + val.id + "'>" + val.name +"</div>");
            }
        });
        //隐藏“病历数量”
        $("#medicalNumber").val(null);
        $("#medical").hide();
    }

    //任务子类
    var newId = $("#newId").val();
    var resultTypeId = $("#directionResultTypeId").val();
    choiceNewIds(newId);
    function choiceNewIds(id) {
        $("#directionScore").val("");
        var cruxId=$("#cruxId").val();
        $("#newId").val(id);
        $("[name=newIds]").attr("class","item");
        $("#newId_"+id).attr("class","item active");

        ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId",{"surveyCode":"taskInfoContent","btnCode":"1000","taskInfoContentId":id,"id":cruxId},function(v,e,p){
            $("#directionResultTypeIdList div").remove();
            if(e.data.results.length > 1){
                $("#directionModel").show();
            }else if(e.data.results.length == 1){
                $("#directionResultTypeId").val(e.data.results[0].directionResultTypeId);
                $("#directionModel").hide();
                resultTypeId = e.data.results[0].directionResultTypeId;
            }else{
                $("#directionModel").hide();
            }
            for(var i = 0; i < e.data.results.length; i++){
                var val = e.data.results[i];
                var it = "item";
                if(resultTypeId == val.directionResultTypeId){
                    it = "item active";
                }
                $("#directionResultTypeIdList").append("<div class='"+it+"' style='cursor:pointer' onclick='choiceResult("+id+","+val.directionResultTypeId+",\""+val.directionResultTypeCode+"\")' id='directionResultTypeId_"+val.directionResultTypeId+"' name='directionResultTypeIds' value='" + val.directionResultTypeId + "'>" + val.directionResultTypeName +"</div>");
            }
            ajaxTaskSubSorce(id,resultTypeId);
        });

        //隐藏“病历数量”
//        $("#medicalNumber").val(null);
        $("#medical").hide();
    }

    //任务结果
    function choiceResult(newId,directionResultTypeId,code) {//任务子类id，方向结果类型id
        $("#directionScore").val("");
        $("#directionResultTypeId").val(directionResultTypeId);
        $("[name=directionResultTypeIds]").attr("class", "item");
        $("#directionResultTypeId_" + directionResultTypeId).attr("class", "item active");

        if((code =="medical" || code =="medical01"||code =="medical02"||code =="medical03") && (newId == 301 || newId == 303 || newId == 300 || newId == 319)){
            $("#text_box").val(1);
            $("#medicalNumber").val(1);
            $("#medical").show();

        }else{
            $("#medicalNumber").val(null);
            $("#medical").hide();
        }
        ajaxTaskSubSorce(newId,directionResultTypeId);
    }


    function ajaxTaskSubSorce(newId,directionResultTypeId){
        var cruxId=$("#cruxId").val();
        console.log('----------------',newId,directionResultTypeId,cruxId);
        ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId",{"surveyCode":"taskInfoScore","taskInfoContentId":newId,
            "directionResultTypeId":directionResultTypeId,"id":cruxId,
            "areaType" : $("#areaType").val(),
            "cityId" : $("#cityId").val(),
            "districtId" : $("#districtId").val()

        },function(v,e,p){
            var type=e.data.count;
            if(type==1){
                $("#directionScoreDiv").show();
                var results = e.data.results;
                if (results.length > 0){
                    for(var i = 0; i < results.length; i++) {
                        var item = results[i];
                        if(item.taskInfoContentId==newId && item.directionResultTypeId==directionResultTypeId){
                            if ($("#orgPoint").val() == 1){
                                item.score = item.pointScore;
                            }
                            $("#directionScore").val(item.score * item.scoreRate);
                        }
                    }
                }
            }
        })
    }

    var resultTypeCode = $("#directionResultTypeCode").val();
    var medicalNum = $("#medicalNumber").val();
    showMedical(newId,resultTypeCode);
    function showMedical(newId,resultTypeCode) {//任务子类id，方向结果类型id
        if((resultTypeCode =="medical" || resultTypeCode =="medical01"||resultTypeCode =="medical02"||resultTypeCode =="medical03") && (newId == 301 || newId == 303 || newId == 300 || newId == 319)){
            $("#medical").show();
        }else{
            $("#medicalNumber").val(medicalNum);
            $("#medical").hide();
        }
    }

    //地址输入框 ：listHistory和list的展示，隐藏
    $(document).on('click',function(e){
        if ($(e.target.parentElement).attr('data-id') != 'address' && $(e.target.parentElement.parentElement).attr('data-id') != 'address'){
            var province = $("#province").val();
            if(!province) {
                $('.listHistory').hide()
                $('.list').hide()
                $("#areaName").val("");
            }
            var areaName = $("#areaName").val();
            if(!areaName) {
                $('.listHistory').hide()
                $('.list').hide()
            }
        }
    });

    //    光标选中触发
    function selectByAreaName(){
        var areaName = $("#areaName").val();
        if(!areaName){
            $('.listHistory').show();
            $('.list').hide();

            $("#province").val("");
            $("#provinceId").val("");
            $("#city").val("");
            $("#cityId").val("");
            $("#district").val("");
            $("#districtId").val("");
            $("#areaType").val("");
            $("#regionType").val("");
        }
    }


    //地区信息
    function changeArea(areaId,areaId2,areaId3,areaName1,areaName2,areaName3,cityType,areaType,cityTypeName) {
        if(areaType == 2){
            $("#areaName").val(areaName2+" "+areaName3);
        }else{
            $("#areaName").val(areaName1+" "+areaName2+" "+areaName3);
        }

        $("#province").val(areaName3);
        $("#provinceId").val(areaId3);

        $("#city").val(areaName2);
        $("#cityId").val(areaId2);

        $("#district").val(areaName1);
        $("#districtId").val(areaId);

        $("#hisCityType").val(cityType);
        $("#hisCityTypeName").val(cityTypeName);
        $("#hisAreaType").val(areaType);

        if(cityType == 5 || cityType == 6){
            $("#areaType").val(1);
            $("#regionType").val(cityType);
        }else if(cityType == 2 || cityType == 3 || cityType == 4){
            $("#areaType").val(cityType);
            $("#regionType").val(0);
        }
        $('.listHistory').hide();
        $('.list').hide();
    }

    //地区信息
    function changeArea1() {
        var qq = $("#areaId").val();
        var result=qq.split(",");

        var areaId = result[0];
        var areaId2 = result[1];
        var areaId3 = result[2];
        var areaName1 = result[3];
        var areaName2 = result[4];
        var areaName3 = result[5];
        var cityType = result[6];
        var areaType = result[7];
        var cityTypeName = result[8];

        if(areaType == 2){
            $("#areaName").val(areaName2+" "+areaName3);
        }else{
            $("#areaName").val(areaName1+" "+areaName2+" "+areaName3);
        }
        $("#province").val(areaName3);
        $("#provinceId").val(areaId3);

        $("#city").val(areaName2);
        $("#cityId").val(areaId2);

        $("#district").val(areaName1);
        $("#districtId").val(areaId);

        $("#hisCityType").val(cityType);
        $("#hisCityTypeName").val(cityTypeName);
        $("#hisAreaType").val(areaType);

        if(cityType == 5 || cityType == 6){
            $("#areaType").val(1);
            $("#regionType").val(cityType);
        }else if(cityType == 2 || cityType == 3 || cityType == 4){
            $("#areaType").val(cityType);
            $("#regionType").val(0);
        }
    }

    $().ready(function() {
        //输入框键盘按键松开事件
        $("#areaName").keyup(function() {
            $('.listHistory').hide();
            var areaName = $("#areaName").val();
            if(!areaName){
                $('.listHistory').show();
                $('.list').hide();
                return;
            }
            ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId",{"areaName":areaName,surveyCode:'commonArea',btnCode:1000},function(v,e,p){
                if(e.data.results.length >0){
                    $("#tr_formerCase div").remove();
                    for(var i = 0; i < e.data.results.length; i++){
                        $('.list').show();
                        var val = e.data.results[i];
                        var cityType = val.cityType;
//                    $("#tr_formerCase").append("<div class='li' onclick='changeArea("+val.areaId+","+val.areaId2+","+val.areaId3+",\""+val.areaName1+"\",\""+val.areaName2+"\",\""+val.areaName3+"\","+val.cityType+")'>"+val.areaName1+""+val.areaName2+""+val.areaName3+""+val.cityType+"     </div>");
                        $("#tr_formerCase").append("<div class='li li-hover' style='cursor:pointer' onclick='changeArea("+val.areaId+","+val.areaId2+","+val.areaId3+",\""+val.areaName1+"\",\""+val.areaName2+"\",\""+val.areaName3+"\","+val.cityType+", "+ val.areaType +",\""+val.cityTypeName+"\")'> "+val.areaName1+""+val.areaName2+""+val.areaName3+" <span>"+val.cityTypeName+"</span> </div>");

                    }
                }else{
                    $('.list').hide();
                }
            });
        })
    })

    //识别结果的高度，自适应方式
    function adjustObjHeight(obj, defaultHeight) {
        if(obj.scrollHeight > defaultHeight) {
            obj.style.height = (obj.scrollHeight+5) + 'px';
        } else {
            obj.style.height = defaultHeight + 'px';
        }
    }
</script>
<script>
    $(function(){
        var t = $("#text_box");
        $("#add").click(function(){
            t.val(parseInt(t.val())+1)
            $("#medicalNumber").val(parseInt(t.val()));
        })
        $("#min").click(function(){
            var num = parseInt(t.val())-1;
            if(num < 1){
                alert("数量不得低于1");
            }else{
                t.val(parseInt(t.val())-1)
                $("#medicalNumber").val(parseInt(t.val()));
            }
        })
    })

    function reckon(){
        var t = $("#text_box").val();
        $("#medicalNumber").val(t);
    }

    function setSurveyReason(cur){
        $("#surveyReason").val($(cur).val());
    }
</script>
<script>
    $(function () {
        //输入框验证
        //只能输入数字和小数点
        $("input[type=number]").keyup(function () {
            var num = $(this).val();
            if (num.toString().indexOf(".") > 0 && Number(num.toString().split(".")[1].length) > 2){
                $(this).val(Math.round(num * 100)/100);
            }else {
                // 通过正则过滤小数点后两位
                if(num != "" &&  -1 == num.indexOf('.')) {
                    //兼容火狐，数字输入框输入点 value 值自动隐藏‘.’，再进行正则会丢失‘.’
                    return
                }
                $(this).val(num.replace(/[^0-9.]/g, ''));
            };
        }).bind("paste", function () { //CTR+V事件处理
            $(this).val($(this).val().replace(/[^0-9.]/g, ''));
        }).css("ime-mode", "disabled"); //CSS设置输入法不可用

        //删除缓存中保存的凭证内容
        removeStorageLike("Files")
        function removeStorageLike(name) {   //模糊删除
            for (var k in sessionStorage) {
                if (k.indexOf(name) > -1)
                    sessionStorage.removeItem(k);
            }
        }

        var menuCode = $("input[name=menuCode]").val();
        var surveyState = $("input[name=surveyState]").val();
        // 调查处理页面 survey_state=4 则方向内容不可编辑
        if ((menuCode =='dcy-list' && surveyState == 4) || menuCode == 'feeViewSurvey'){
            $(".direction,.form-control").attr("disabled",true);
            $(".radios").addClass("div_disabled");
            //隐藏保存方向按钮
            // $("[name=btnUpload]:lt(2)").hide();
            $(".form-btns").hide();
        }
        // //费用报销状态
        // var reState = $("input[name=reState]").val();
        // if (menuCode !='help-review' && ((reState!='' && reState > 1) || $("#feeOpr").val() == 'view')){ //待机构审核
        //     $(".input-bx-1,.input-bx-2,.input-bx-3,.input-bx-4,.v-p-upload,.input-bx-3_1").attr("disabled",true);
        //     $(".btns-bx").addClass("div_disabled");
        //     //隐藏保存按钮
        //     $(".form-btns").hide();
        // }

        $('.btns-bx').on('click', '.item', function () {
            var _this = $(this);
            _this.siblings().removeClass('active');
            _this.addClass('active');
            var _id = _this.attr('data-id');
            if (_id == 2){
                $("input[name=haveReimbursement]").val("1");
                $('.cell-bx').css({
                    display: 'flex'
                })
            }else {
                $("input[name=haveReimbursement]").val("0");
                $('.cell-bx').hide()
            }
        })

        /*------------0429-----------------*/

        function putSessionFiles(name,filePath){
            var sessionFiles = sessionStorage.getItem(name);
            if (sessionFiles!=null){
                sessionFiles = sessionFiles.split(",");
                sessionFiles.push(filePath);
                sessionStorage.setItem(name,sessionFiles.toString());
                $("input[name="+name+"]").val(sessionFiles.toString());
            }else {
                sessionStorage.setItem(name,filePath);
                $("input[name="+name+"]").val(filePath);
            }
        }

        layui.use('upload', function () {
            var upload = layui.upload;
            var uploadFile = upload.render({
                elem: '#uploadFile',
                url: '${ctx}/sftp/survey/uploadSftp', //改成您自己的上传接口
                multiple: true,
                accept: 'images', //只能上传图片
                acceptMime: 'image/*',
                before: function (obj) {
                    layer.load();
                },
                done: function (res) {
                    console.log("res:::"+JSON.stringify(res))
                    layer.closeAll('loading')
                    if (res.success == 'true'){
                        layer.alert('导入成功', {
                            icon: 1
                        })
                        var _id = this.data.id
                        var _this = $('.v-p-files[data-id='+_id+']');
                        var olddata = parseInt(_this.attr('data-value'));
                        _this.attr("data-value",parseInt(olddata+1));
                        $('.v-p-files[data-id='+_id+']').text('凭证('+(olddata+1)+')');
                        var filePath = res.surveyFile.filePath;
                        if (_id ==1){
                            putSessionFiles("medicalHistoryFiles",filePath);
                        }else if (_id ==2){
                            putSessionFiles("troubleshootingFiles",filePath);
                        }else if (_id ==3){
                            putSessionFiles("printingFiles",filePath);
                        }else if (_id ==4){
                            putSessionFiles("accommodatioFiles",filePath);
                        }else if (_id ==5){
                            putSessionFiles("carFiles",filePath);
                        }else if (_id ==6){
                            putSessionFiles("trainFiles",filePath);
                        }else if (_id ==7){
                            putSessionFiles("airFiles",filePath);
                        }else if (_id ==8){
                            putSessionFiles("selfDrivingFiles",filePath);
                        }else if (_id ==9){
                            putSessionFiles("otherMoneyFiles",filePath);
                        }
                    }else {
                        layer.alert('导入出错', {
                            icon: 2
                        })
                    }

                },
                allDone:function(obj){
                    console.log(obj)
                },
                error: function (index, upload) {
                    layer.closeAll('loading')
                }
            });
            $('.v-p-upload').on('click', function () {
                var _this = $(this)
                uploadFile.reload({
                    data: {
                        'exportType': 'direction',
                        'modelType' : 'fee',
                        'surveyCno' : $("input[name=surveyCno]").val(),
                        'id': _this.attr('data-id')
                    }
                })
                $('#uploadFile').click()
            })
        });

        $('.cell-bx .v-operate .v-p-files').on('click', function () {
            var _this = $(this)
            // alert(_this.attr("data-id"))
            var width= $(document.body).outerWidth();
            var height = $(document).outerHeight() - 20;
            var title = "附件详情";
            var type = _this.data("type");
            //判断附件页面是否显示删除按钮
            var isdelete = $("input[name=reState]").val()>1?"no":"yes";
            openDialog({
                frame:true,
                title:title,
                height:height,
                width:width,
                url:"${ctx}/survey/case/sic/directionFiles?directionId=" + $("#directionId").val() + "&surveyCno=" + $("input[name=surveyCno]").val()+"&type="+type+"&isdelete="+isdelete+"&dataId="+_this.attr("data-id")
            });

            // alert(_this.attr("data-value"))
            // alert('凭证： ' + _this.data('value',parseInt(_this.data('value'))+1))
        });

        $(".input-bx-3").eq(0).blur(function () {
            var km = $(this).val();
            if (km!=''){
                $(".input-bx-3").eq(1).val((0.7 * km).toFixed(2));
            }else {
                $(".input-bx-3").eq(1).val("");
            }
        });

    });
</script>

<script type="text/javascript">
    // 百度地图API功能
    function G(id) {
        return document.getElementById(id);
    }

    var map = new BMap.Map("l-map");
    map.centerAndZoom("北京",12);                   // 初始化地图,设置城市和地图级别。

    var name = $("#directionName").val();
    var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
        {"input" : "directionName"
            ,"location" : map
        });
    ac.setInputValue(name);

    ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
        var str = "";
        var _value = e.fromitem.value;
        var value = "";
        if (e.fromitem.index > -1) {
            // value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
            value =_value.street +  _value.business;
        }
        str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

        value = "";
        if (e.toitem.index > -1) {
            _value = e.toitem.value;
            // value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
            value = _value.street +  _value.business;
        }
        str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
        G("searchResultPanel").innerHTML = str;
    });

    var myValue;
    ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
        var _value = e.item.value;
        myValue =  _value.business;
        G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
        if (_value.city && _value.district) {
            console.log(_value);
            //根据名称匹配省市区
            $.ajax({
                url: '${ctx}/survey/case/operate',
                data: {
                    name1 : _value.city,
                    name2 : _value.district,
                    btnCode : "match-address"
                },
                success: function (e,res) {
                    var _data = res.data.results;
                    if (!_data){
                        $("#areaName").val("");
                        $("#province").val("");
                        $("#provinceId").val("");
                        $("#city").val("");
                        $("#cityId").val("");
                        $("#district").val("");
                        $("#districtId").val("");
                        $("#areaType").val("");
                        $("#regionType").val("");
                        $("#hisAreaType").val("");
                        return;
                    }
                    var areaType = _data.areaType;
                    var areaName3 = _data.areaName3,areaId3 = _data.areaId3;//省名称
                    var areaName2 = _data.areaName2,areaId2 = _data.areaId2;//市名称
                    var areaName1 = _data.areaName1,areaId = _data.areaId;//区名称
                    var cityType = _data.cityType,cityTypeName = _data.cityTypeName;//区域级别（市区、郊区）
                    if(cityType == 3){
                        $("#areaName").val(areaName2+" "+areaName3);
                        // areaName1 = "",areaId = "";
                    }else{
                        $("#areaName").val(areaName1+" "+areaName2+" "+areaName3);
                    }
                    $("#province").val(areaName3);
                    $("#provinceId").val(areaId3);

                    $("#city").val(areaName2);
                    $("#cityId").val(areaId2);

                    $("#district").val(areaName1);
                    $("#districtId").val(areaId);

                    $("#hisCityType").val(cityType);
                    $("#hisCityTypeName").val(cityTypeName);
                    if(cityType == 5 || cityType == 6){
                        $("#areaType").val(1);
                        $("#regionType").val(cityType);
                        $("#hisAreaType").val(1);
                    }else if(cityType == 2 || cityType == 3 || cityType == 4){
                        $("#areaType").val(cityType);
                        $("#hisAreaType").val(cityType);
                        $("#regionType").val(0);
                    }
                }
            })
        }
        ac.setInputValue(myValue)
        setPlace();
    });

    function setPlace(){
        map.clearOverlays();    //清除地图上所有覆盖物
        function myFun(){
            var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
            map.centerAndZoom(pp, 18);
            map.addOverlay(new BMap.Marker(pp));    //添加标注
        }
        var local = new BMap.LocalSearch(map, { //智能搜索
            onSearchComplete: myFun
        });
        local.search(myValue);
    }
</script>
</body>
</html>