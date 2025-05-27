<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <%--<script src="${ctx}/js/progress.js"></script>--%>
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">

    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
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
            background-color: #66C8FF!important;
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
        width: 51%;
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
        margin: 0 auto;
        width: 99%;
    }

    .body-right .cell {
        display: flex;
        padding: 5px 0;
    }

    .body-right .cell .label {
        white-space: normal;
        padding: 8px 0;
        padding-right: 1%;
        width: 13%;
        min-width: 70px;
        height: 20px;
        line-height: 20px;
        color: #333;
    }

    .body-right .cell .value {
        width: 87%;
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

    .body-right .cell .list .li {
        width: 92%;
        padding: 3px 4%;
        /*height: 20px;*/
        line-height: 20px;
    }
    .body-right .cell .list .li span{
        float:right;

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
    .dalogs2{
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
        /* ---------0429--------- */
    </style>
</head>
<body>
<%--左侧页面信息--%>
<div class="contain">

    <div class="first">
        <iframe name="fileMidIframe" width="100%" height="99%" src="${ctx}/survey/case/sic/operateView?btnCode=directionFileMid&id=${id}&surveyInfoId=${dto.surveyRiskCaseInfo.id}&surveyCno=${dto.surveyRiskCaseInfo.surveyCno}&surveyId=${dto.surveyId}" style="border: none; "></iframe>
    </div>
    <div class="body-right">
        <form id="editForm" role="form" action="${ctx}/survey/case/sic/operate" method="post">
        <input type="hidden" name="id" id="cruxId" value="${id}" />
        <input type="hidden" name="btnCode" value="${btnCode}" />
        <input type="hidden" name="tsId" value="${tsId}" />
        <input type="hidden" name="menuCode" value="${menuCode}" />
        <input type="hidden" name="surveyState" value="${dto.surveyState}" />
        <input type="hidden" id="files" name="files"  value="">
        <input type="hidden" name="surveyCno" value="${dto.surveyRiskCaseInfo.surveyCno}" />
        <input type="hidden" name="surveyInfoId" id="surveyInfoId" value="${dto.surveyRiskCaseInfo.id}" />
        <input type="hidden" name="orgReview" value="${orgReview}" /><%--报告复核info页，增加方向，不重定向--%>
        <input type="hidden" name="medicalHistoryFiles" value="" />
        <input type="hidden" name="troubleshootingFiles" value="" />
        <input type="hidden" name="printingFiles" value="" />
        <input type="hidden" name="accommodatioFiles" value="" />
        <input type="hidden" name="trainFiles" value="" />
        <input type="hidden" name="airFiles" value="" />
        <input type="hidden" name="carFiles" value="" />
        <input type="hidden" name="selfDrivingFiles" value="" />
        <input type="hidden" name="otherMoneyFiles" value="" />
        <input hidden name="haveReimbursement" value="0">
        <input hidden name="directionhaveReimbursement" value="${direction.haveReimbursement}">
        <input hidden name="surveyState" value="${dto.surveyState}">
        <input hidden name="isDirectionSuccess" value="${dto.isDirectionSuccess}">
        <input hidden name="clicktaskName" value="0">
        <input hidden name="feeOpr" id="feeOpr" value="${feeOpr}">
        <input hidden name="reState" value="${dto.reState}">
        <input hidden name="haveFile" value="${direction.haveFile}">
        <input hidden name="haveSound" value="${direction.haveSound}">
        <div class="title"><div class="icon-step"  style="width: 30px;display: inline-block; margin-right: 8px;"><div class="index active">2</div></div>填写方向内容</div>
        <div class="form">
            <div class="cell" style="display: none;">
                <div id="l-map"></div>
                <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
            </div>
            <div class="cell ">
                <div class="label">方向名称</div>
                <div class="value">
                    <c:if test="${direction.orgPoint == 1}">
                        <input type="text" style="background-color: #eee;" value="${direction.directionName}" readonly disabled />
                    </c:if>
                    <div <c:if test="${direction.orgPoint == 1}">style="display: none;" </c:if> >
                        <input  type="text" id="directionName" name="directionName" onblur="setSurveyReason(this)" class="direction" <c:if test="${direction.id !=null}"> value="${direction.directionName}" </c:if>/>
                    <%--验证不重复的方向名称--%>
                        <input type="hidden" id="uploadDirectionName" name="uploadDirectionName" class="" value="${direction.directionName}" />
                    </div>
<%--                    <input  type="text" id="directionName" name="directionName" onblur="setSurveyReason(this)" class="direction" <c:if test="${direction.id !=null}"> value="${direction.directionName}" </c:if>/>--%>
<%--                    &lt;%&ndash;验证不重复的方向名称&ndash;%&gt;--%>
<%--                    <input type="hidden" id="uploadDirectionName" name="uploadDirectionName" class=""  value="${direction.directionName}" />--%>
                </div>
            </div>
            <div class="cell ">
                <div class="label">地址信息</div>
                <div class="value" data-id="address">
                    <input type="text" autocomplete="off" placeholder="请输入区县名或城市名查询"  class="direction" id="areaName" name="areaName" required="required"
                           <c:if test="${direction.province!=null}">value="${direction.district} ${direction.city} ${direction.province}"</c:if>
                           onfocus="selectByAreaName()" >
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

                <%--历史记录数据--%>
                <input type="hidden" id="hisCityType" name="hisCityType" value="">
                <input type="hidden" id="hisCityTypeName" name="hisCityTypeName" value="">
                <input type="hidden" id="hisAreaType" name="hisAreaType" value="">
            </div>
            <div class="cell ">
                <div class="label">任务类型</div>
                <div class="value">
                    <div class="btns radios" data-code="tasks" data-id="taskId" data-key="${direction.taskId}" data-value="${direction.taskName}">
                        <c:forEach items="${dto.tasks}" var="item">
                            <div title="${item.taskRemark}" class="item" data-code="tasks" data-key="${item.taskId}" data-value="${item.taskName}">${item.taskName}</div>
                        </c:forEach>
                    </div>
                    <%-- 控制显示隐藏--%>
                    <input type="hidden" name="attrCode" id="attrCode" />
                    <input type="hidden" value="${direction.taskId}" id="taskId" name="taskId" required="required"/>
                    <input type="hidden" value="${direction.taskName}" id="taskName" name="taskName"  required="required"/>
                </div>
            </div>
            <div class="cell " id="taskInfoContent">
                <div class="label">任务子类</div>
                <div class="value">
                    <div class="btns radios" id="newIdList" data-id="newId" data-key="${direction.newId}" data-value="${direction.newName}"></div>
                    <input type="hidden" value="${direction.newId}" id="newId" name="newId" required="required"/>
                    <input type="hidden" value="${direction.newName}" id="newName" name="newName" required="required"/>
                </div>
            </div>
            <div class="cell" id="directionModel">
                <div class="label">任务结果</div>
                <div class="value">
                    <div class="btns radios" id="directionResultTypeIdList" data-id="directionResultTypeId" data-key="${direction.directionResultTypeId}" data-value="${direction.directionResultTypeName}"></div>
                    <input type="hidden" value="${direction.directionResultTypeId}" id="directionResultTypeId" name="directionResultTypeId"  required />
                    <input type="hidden" value="${direction.directionResultTypeName}" id="directionResultTypeName" name="directionResultTypeName" required />
                </div>
            </div>
            <div class="cell" id="directionScoreDiv" style="display:none">
                <div class="label">方向分值</div>
                <div class="value"><input name="directionScore" id="directionScore" type="text" class="form-control" disabled="disabled"></div>
            </div>
            <%--  第一类--%>
            <div class="cell huzhuattr attr1">
                <div class="label">面访时间</div>
                <div class="value"><input name="attr1Date" type="text" class="form-control" placeholder="面访时间" style="cursor: auto;background-color:#fff"
                                          value="<fmt:formatDate value="${direction.huzhuDate}" pattern="yyyy-MM-dd"/>" class="direction"
                                          onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly  ></div>
            </div>
            <div class="cell huzhuattr attr1">
                <div class="label" style="height: 20px;line-height: 20px; padding: 9px 0;white-space: inherit;">面访对象与患病成员关系</div>
                <div class="value">
                    <input type="hidden" id="attr1Obj" name="attr1Obj" />
                    <div class="btns radios" data-id="attr1Obj" data-value="${direction.attr1Obj}">
                        <div class="item">本人</div>
                        <div class="item">父母</div>
                        <div class="item">配偶</div>
                        <div class="item">子女</div>
                        <div class="item">其他</div>
                    </div>
                </div>
            </div>
            <div class="cell huzhuattr attr1">
                <div class="label">面访对象姓名</div>
                <div class="value">
                    <input type="text" name="attr1ObjName" class="form-control direction" value="${direction.attr1ObjName}" />
                </div>
            </div>

            <div class="cell huzhuattr attr1 sun" data-key="attr1" >
                <div class="label">是否阳性</div>
                <div class="value">
                    <input type="hidden" id="attr1Sun" name="attr1Sun" />
                    <div class="btns radios" data-code="sun" data-id="attr1Sun" data-value="${direction.huzhuSunStr}" data-key="attr1">
                        <div class="item" data-key="attr1">否</div>
                        <div class="item" data-key="attr1">是</div>
                    </div>
                </div>
            </div>
            <div class="cell huzhuattr attr1 sundesc">
                <div class="label">阳性说明</div>
                <div class="value">
                    <div class="texts">
                        <textarea rows="2" name="attr1SunRemark" class="form-control" >${direction.sunRemark}</textarea>
                    </div>
                </div>
            </div>

            <div class="cell huzhuattr attr1">
                <div class="label">面访经过说明</div>
                <div class="value">
                    <div class="texts">
                        <textarea rows="4" name="attr1Remark" class="form-control" placeholder="请详细说明面访经过和结论">${direction.directionText ==null ? '见报告':direction.directionText}</textarea>
                    </div>
                </div>
            </div>


            <%--  第二类--%>
            <div class="cell huzhuattr attr2">
                <div class="label">排查时间</div>
                <div class="value"><input name="attr2Date" type="text" class="form-control" placeholder="排查时间" style="cursor: auto;background-color:#fff"
                                          value="<fmt:formatDate value="${direction.huzhuDate}" pattern="yyyy-MM-dd"/>"
                                          onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly ></div>
            </div>
            <div class="cell huzhuattr attr2">
                <div class="label">排查类型</div>
                <div class="value">
                    <input type="hidden" id="attr2Type" name="attr2Type" />
                    <div class="btns radios " data-id="attr2Type" data-value="${direction.attr2Type}">
                        <div class="item">门诊</div>
                        <div class="item">住院</div>
                        <div class="item">门诊及住院</div>
                    </div>
                </div>
            </div>
            <div class="cell huzhuattr attr2">
                <div class="label">排查科室</div>
                <div class="value">
                    <input type="text" name="attr2His" class="form-control" value="${direction.attr2His}" />
                </div>
            </div>
            <div class="cell huzhuattr attr2 sun" data-key="attr2">
                <div class="label">是否阳性</div>
                <div class="value">
                    <input type="hidden" id="attr2Sun" name="attr2Sun" />
                    <div class="btns radios" data-code="sun" data-id="attr2Sun" data-value="${direction.huzhuSunStr}" data-key="attr2">
                        <div class="item" data-key="attr2">否</div>
                        <div class="item" data-key="attr2">是</div>
                    </div>
                </div>
            </div>
            <div class="cell huzhuattr attr2 sundesc">
                <div class="label">阳性说明</div>
                <div class="value">
                    <div class="texts">
                        <textarea rows="2" name="attr2SunRemark" class="form-control" >${direction.sunRemark}</textarea>
                    </div>
                </div>
            </div>
            <div class="cell huzhuattr attr2">
                <div class="label">医院排查结论</div>
                <div class="value">
                    <div class="texts">
                        <textarea rows="4" name="attr2Remark" class="form-control" >${direction.directionText ==null ? '见报告':direction.directionText}</textarea>
                    </div>
                </div>
            </div>


            <div class="cell huzhuattr attr3">
                <div class="label">走访对象</div>
                <div class="value">
                    <input type="text" name="attr3Obj" class="form-control" value="${direction.attr3Obj}" />
                </div>
            </div>
            <div class="cell huzhuattr attr3">
                <div class="label">走访时间</div>
                <div class="value"><input name="attr3Date" type="text" class="form-control" placeholder="走访时间" style="cursor: auto;background-color:#fff"
                                          value="<fmt:formatDate value="${direction.huzhuDate}" pattern="yyyy-MM-dd"/>"
                                          onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly ></div>
            </div>
            <div class="cell huzhuattr attr3 sun">
                <div class="label">是否阳性</div>
                <div class="value">
                    <input type="hidden" id="attr3Sun" name="attr3Sun" />
                    <div class="btns radios" data-code="sun" data-id="attr3Sun" data-value="${direction.huzhuSunStr}" data-key="attr3">
                        <div class="item" data-key="attr3">否</div>
                        <div class="item" data-key="attr3">是</div>
                    </div>
                </div>
            </div>
            <div class="cell huzhuattr attr3 sundesc">
                <div class="label">阳性说明</div>
                <div class="value">
                    <div class="texts">
                        <textarea rows="2" name="attr3SunRemark" class="form-control" >${direction.sunRemark}</textarea>
                    </div>
                </div>
            </div>
            <div class="cell huzhuattr attr3">
                <div class="label">走访结论</div>
                <div class="value">
                    <div class="texts">
                        <textarea rows="4" name="attr3Remark" class="form-control" >${direction.directionText ==null ? '见报告':direction.directionText}</textarea>
                    </div>
                </div>
            </div>


            <div class="cell huzhuattr attr4">
                <div class="label">商保排查</div>
                <div class="value">
                    <input type="text" name="attr4Name1" class="form-control" placeholder="请填写排查的商保公司名称，没有可不填" value="${direction.attr4Name1}" />
                </div>
            </div>
            <div class="cell huzhuattr attr4">
                <div class="label">商保排查结论</div>
                <div class="value">
                    <div class="texts">
                        <textarea rows="4" name="attr4Remark1" class="form-control" >${direction.attr4Remark1 ==null ? '见报告':direction.attr4Remark1}</textarea>
                    </div>
                </div>
            </div>
            <div class="cell huzhuattr attr4">
                <div class="label">互助排查</div>
                <div class="value">
                    <input type="text" name="attr4Name2" class="form-control" placeholder="请填写排查的互助名称，没有可不填" value="${direction.attr4Name2}" />
                </div>
            </div>
            <div class="cell huzhuattr attr4">
                <div class="label">互助排查结论</div>
                <div class="value">
                    <div class="texts">
                        <textarea rows="4" name="attr4Remark2" class="form-control" >${direction.attr4Remark2==null?'见报告':direction.attr4Remark2}</textarea>
                    </div>
                </div>
            </div>



            <div class="cell huzhuattr attr5">
                <div class="label">排查时间</div>
                <div class="value"><input name="attr5Date" type="text" class="form-control" placeholder="排查时间" style="cursor: auto;background-color:#fff"
                                          value="<fmt:formatDate value="${direction.huzhuDate}" pattern="yyyy-MM-dd"/>"
                                          onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly ></div>
            </div>
            <div class="cell huzhuattr attr5 sun">
                <div class="label">是否阳性</div>
                <div class="value">
                    <input type="hidden" id="attr5Sun" name="attr5Sun" />
                    <div class="btns radios" data-code="sun" data-id="attr5Sun" data-value="${direction.huzhuSunStr}" data-key="attr5">
                        <div class="item" data-key="attr5">否</div>
                        <div class="item" data-key="attr5">是</div>
                    </div>
                </div>
            </div>
            <div class="cell huzhuattr attr5 sundesc">
                <div class="label">阳性说明</div>
                <div class="value">
                    <div class="texts">
                        <textarea rows="2" name="attr5SunRemark" class="form-control" >${direction.sunRemark}</textarea>
                    </div>
                </div>
            </div>
            <div class="cell huzhuattr attr5">
                <div class="label">社保排查结论</div>
                <div class="value">
                    <div class="texts">
                        <textarea rows="4" name="attr5Remark" class="form-control" >${direction.directionText ==null ? '见报告':direction.directionText}</textarea>
                    </div>
                </div>
            </div>

            <div class="cell huzhuattr attr6">
                <div class="label">排查时间</div>
                <div class="value"><input name="attr6Date" type="text" class="form-control" placeholder="排查时间" style="cursor: auto;background-color:#fff"
                                          value="<fmt:formatDate value="${direction.huzhuDate}" pattern="yyyy-MM-dd"/>"
                                          onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly ></div>
            </div>
            <div class="cell huzhuattr attr6 sun">
                <div class="label">是否阳性</div>
                <div class="value">
                    <input type="hidden" id="attr6Sun" name="attr6Sun" />
                    <div class="btns radios" data-code="sun" data-id="attr6Sun" data-value="${direction.huzhuSunStr}" data-key="attr6">
                        <div class="item" data-key="attr6">否</div>
                        <div class="item" data-key="attr6">是</div>
                    </div>
                </div>
            </div>
            <div class="cell huzhuattr attr6 sundesc">
                <div class="label">阳性说明</div>
                <div class="value">
                    <div class="texts">
                        <textarea rows="2" name="attr6SunRemark" class="form-control" >${direction.sunRemark}</textarea>
                    </div>
                </div>
            </div>
            <div class="cell huzhuattr attr6">
                <div class="label">体检排查结论</div>
                <div class="value">
                    <div class="texts">
                        <textarea rows="4" name="attr6Remark" class="form-control" >${direction.directionText ==null ? '见报告':direction.directionText}</textarea>
                    </div>
                </div>
            </div>


            <div class="cell huzhuattr attr7 attr8">
                <div class="label">排查时间</div>
                <div class="value"><input name="attr7Date" type="text" class="form-control" placeholder="排查时间" style="cursor: auto;background-color:#fff"
                                          value="<fmt:formatDate value="${direction.huzhuDate}" pattern="yyyy-MM-dd"/>"
                                          onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly ></div>
            </div>
            <div class="cell huzhuattr attr7 attr8">
                <div class="label">排查结论</div>
                <div class="value">
                    <div class="texts">
                        <textarea rows="4" name="attr7Remark" class="form-control" >${direction.directionText ==null ? '见报告':direction.directionText}</textarea>
                    </div>
                </div>
            </div>




            <div class="cell" style="display: none" id="medical">
                <div class="label">病历数量</div>
                <div class="value">
                    <div class="btns">
                        <input id="min" name="" type="button" value="-" style="width: 40px;background-color: #3FB5FD;padding: 0;color: #fff"/>
                        <input id="text_box" name="" type="text" value="1" style="width: 50px; text-align: center;border:0px solid #3FB5FD;padding:0; "  onblur="reckon()"/>
                        <input id="add" name="" type="button" value="+" style="width: 40px;background-color: #3FB5FD;padding: 0;color: #fff"/>
                        <%--<button onclick="return add(1)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(1)"class="btn btn-default btn-xs btn-primary">—</button>--%>
                    </div>
                    <input type="hidden" value="1" id="medicalNumber" name="medicalNumber"/>
                </div>
            </div>
            <div class="cell" style="display: none" id="recognition">
                <div class="label">识别结果</div>
                <div class="value">
                    <div class="texts">
                        <textarea name="recognitionResult" id ="recognitionResult" style="height: 150px;background-color:rgba(63, 181, 253, 0.05); cursor: auto" class="form-control" onkeyup="adjustObjHeight(this, 50);" readonly ></textarea>
                    </div>
                    <div style="font-size: 10px; color:grey ">识别结果仅供复制粘贴使用，不保存在最终方向结果中</div>
                </div>
            </div>
            <div class="cell" style="display: none;">
                <div class="label">方向内容</div>
                <div class="value">
                    <div class="texts">
                        <textarea name="directionText" id ="directionText" style="height: 250px;"  required="required" class="form-control" ></textarea>
                    </div>

                </div>
            </div>
            <div class="cell" style="display: none;">
                <div class="label">调查依据</div>
                <div class="value">
                    <input type="text" id="surveyReason" name="surveyReason" placeholder="必填，且不可和方向名称重复。如“华东医院病史2份”" class="direction" autocomplete="off" value="${direction.surveyReason}" />
                </div>
            </div>
            <div class="cell" style="display: none" name="pporzz">
                <div class="label">是否获得屏拍或者纸质资料</div>
                <div class="value">
                    <div class="btns-bx radios" data-type="pporzz">
                        <div class="item ${(direction.haveFile!=null && direction.haveFile==0)?'active':''}" data-id="0">否</div>
                        <div class="item ${(direction.haveFile!=null && direction.haveFile==1)?'active':''}" data-id="1">是</div>
                    </div>
                </div>
            </div>
            <div class="cell">
                <div class="label">是否有录音</div>
                <div class="value">
                    <div class="btns-bx radios" data-type="ly">
                        <div class="item ${(direction.haveSound !=null && direction.haveSound == 0)?'active':''}" data-id="0">否</div>
                        <div class="item ${(direction.haveSound !=null && direction.haveSound == 1)?'active':''}" data-id="1">是</div>
                    </div>
                </div>
            </div>
            <!-- /*------------0429-----------------*/ -->
<%--            <c:if test="${showExpenseReimbursement}">--%>
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
<%--                        --%>
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
            <!-- /*------------0429-----------------*/ -->
        </div>
        <div class="form-btns">
            <c:if test="${dto.isDirectionSuccess == 0}">
                <button type="button" name="btnUpload" onclick="return validFile('directionAgain');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off" title="如果你还有其他方向要添加，请点我。"><span class="glyphicon glyphicon-ok"></span>保存并开始下一个方向</button>&nbsp&nbsp&nbsp
                <input type="hidden" name="successDirection" id="successDirection" />
                <c:if test="${menuCode!=null && menuCode == 'dcy-list'}">
                    <button type="button" name="btnUpload" id="successDirectionBtn" onclick="return validFile('successDirectionPopup');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off" title="如果你已经完成全部方向了，请点我。除非上级退回，否则你将无法再增加方向，请三思。"><span class="glyphicon glyphicon-ok"></span>已完成全部方向</button>
                </c:if>
            </c:if>
            <c:if test="${dto.isDirectionSuccess != 0}">
                <c:if test="${menuCode != 'feeViewSurvey'}">
                    <button type="button" name="btnUpload" onclick="return validFile('directionAgain');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off" title="如果你还有其他方向要添加，请点我。"><span class="glyphicon glyphicon-ok"></span>保存</button>&nbsp&nbsp&nbsp
                </c:if>
                <c:if test="${menuCode == 'feeViewSurvey' && feeOpr == 'edit'}">
                    <button type="button" name="btnUpload" onclick="return validFile('surveyFee');" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off" ><span class="glyphicon glyphicon-ok"></span>保存</button>&nbsp&nbsp&nbsp
                </c:if>
            </c:if>
        </div>
        <br><br>
        <div class="dalogs">
            <div class="close" onclick="hidDirectionView()">X</div>
            <div class="d_content" style="padding: 20px;"></div>
        </div>
        <div class="dalogs2">
            <div class="close" onclick="hidDirectionView2()">X</div>
            <div class="d_content2" style="padding: 20px;"></div>
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
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
<script src="${ctx}/js/layui/layui.js"></script>
<script type="text/javascript" src="//api.map.baidu.com/api?v=2.0&ak=QkRO8ju3UC9XVXbQITdaB4y7Vo73X89D"></script>
<script type="text/javascript">
    var pporzz=false;
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
        // debugger
        // 调查处理页面 survey_state=4 则方向内容不可编辑
        if ((menuCode =='dcy-list' && surveyState == 4) || menuCode == 'feeViewSurvey'){
            $(".direction,.form-control").attr("disabled",true);
            $(".radios").addClass("div_disabled");
            //隐藏保存方向按钮
            // $("[name=btnUpload]:lt(2)").hide();
        }
        // //费用报销状态
        // var reState = $("input[name=reState]").val();
        // if (menuCode !='help-review' && ((reState!='' && reState > 1) || $("#feeOpr").val() == 'view')){ //待机构审核
        //     $(".input-bx-1,.input-bx-2,.input-bx-3,.input-bx-4,.v-p-upload,.input-bx-3_1").attr("disabled",true);
        //     $(".btns-bx").addClass("div_disabled");
        //     //隐藏保存按钮
        //     $(".form-btns").hide();
        // }

        // else {
        //     //隐藏保存方向按钮
        //     $("[name=btnUpload]:lt(3)").hide();
        //     //显示保存按钮
        //     $("[name=btnUpload]:last").show();
        // }

        //判断是否添加了费用报销内容
        $(".btns-bx div").each(function(){
            var flag = $(this).hasClass("active");
            if (flag && $(this).attr("data-id")==2){
                /**
                 * 走访就诊医疗机构' || name == '走访出生医疗机构' || name == '居住地医疗机构排查'
                 || name == '工作地医疗机构排查' || name == '出险地医疗机构排查'|| name == '户籍所在地医疗机构排查'
                 */
                var taskName = $("#taskName").val();
                if ( taskName== '走访就诊医疗机构' || taskName== '走访出生医疗机构'
                    ||taskName== '居住地医疗机构排查' ||taskName== '工作地医疗机构排查'
                    ||taskName== '出险地医疗机构排查' ||taskName== '户籍所在地医疗机构排查' ){
                    $('.cell-bx').css({
                        display: 'flex'
                    })
                }else {
                    $('.cell-bx').slice(3,9).css({
                        display: 'flex'
                    })
                }
                if (taskName == '体检机构排查'){
                    $('.cell-bx').slice(2,3).css({
                        display: 'flex'
                    })
                }
            }
        });
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

        $('.btns-bx').on('click', '.item', function () {
            var _this = $(this);
            _this.siblings().removeClass('active');
            _this.addClass('active');
            var _id = _this.attr('data-id');
            if (_this.parent().attr('data-type') == 'bx') {
                if (_id == 2){
                    $("input[name=haveReimbursement]").val("1");
                    var taskName = $("input[name=clicktaskName]").val();
                    if ( taskName== '走访就诊医疗机构' || taskName== '走访出生医疗机构'
                        ||taskName== '居住地医疗机构排查' ||taskName== '工作地医疗机构排查'
                        ||taskName== '出险地医疗机构排查' ||taskName== '户籍所在地医疗机构排查' ){
                        $('.cell-bx').css({
                            display: 'flex'
                        })
                    }else {
                        $('.cell-bx').slice(3,9).css({
                            display: 'flex'
                        })
                    }
                    if (taskName == '体检机构排查'){
                        $('.cell-bx').slice(2,9).css({
                            display: 'flex'
                        })
                    }
                }else {
                    $("input[name=haveReimbursement]").val("0");
                    $('.cell-bx').hide()
                }

            }else if (_this.parent().attr('data-type') == 'pporzz'){
                if (_id==0){
                    $("input[name=haveFile]").val(0);
                }else {
                    $("input[name=haveFile]").val(1);
                }
            }else if (_this.parent().attr('data-type') == 'ly'){
                if (_id==0){
                    $("input[name=haveSound]").val(0);
                }else {
                    $("input[name=haveSound]").val(1);
                }
            }
        })

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
        })

        /*------------0429-----------------*/
        var dNumber = 0
        var clientHeight = document.documentElement.clientHeight
        $('.contain').height(clientHeight - 20)

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
    $(document).ready(function(){
        $('.singleSelect').select2();
    });

    $("#editForm").bind('submit', function(event) {
        $("[name=btnUpload]").attr("disabled","true");
        ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
        event.preventDefault();
    });

    $(".input-bx-3").eq(0).blur(function () {
        var km = $(this).val();
        if (km!=''){
            $(".input-bx-3").eq(1).val((0.7 * km).toFixed(2));
        }else {
            $(".input-bx-3").eq(1).val("");
        }
    })
    var again ="";
    var two="";//two = 1时是直接刷新父级，不需要--confirm('是否确认？')
    var i = 0;// 控制多次提交
    function validFile(btnCode){
        //验证方向名称是否存在
        var url = "${ctx}/survey/case/operate",param = {
            "btnCode" : "validateDirectionName",
            "id" : $("#surveyInfoId").val(),
            "directionName" : $("#directionName").val(),
            "directionId" : $("#directionId").val()
        };

        if (btnCode == 'successDirectionPopup'){
            layer.confirm('提交之后将不能新增其他方向，确定所有方向都已经完成了吗？', {
                btn: ['是，全部完成','不，再考虑下 '] //按钮
            }, function(){
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

                        //设置值
                        commitValue();

                        if(btnCode == 'direction' || btnCode == 'successDirection' || btnCode == 'successDirectionPopup' || btnCode == 'directionAgain' || btnCode == 'surveyFee'){
                            //获取iframe页面的图片信息
                            var fileList = window.frames["fileMidIframe"].document.getElementById("fileList").value;
                            if(!$("#areaType").val() || $("#areaType").val() == 0){
                                i = 0;
                                alert("请选择地址");return false;
                            }
                            if(!$("#taskId").val()){
                                alert("请选择任务类型");return false;
                            }
                            if(!$("#newId").val() || $("#newId").val() == 0){
                                alert("请选择任务子类");return false;
                            }
                            if(!$("#directionName").val()){
                                i = 0;
                                alert("请填写方向名称");return false;
                            }
                            if(!$("#directionResultTypeId").val() || $("#directionResultTypeId").val() == 0){
                                i = 0;
                                alert("请选择任务结果");return false;
                            }
                            $("#files").val(fileList);
                            if(btnCode == 'successDirectionPopup') { //已完成全部方向 按钮
                                $('#successDirection').val('success');
                            }
                            if(btnCode == 'directionAgain'){ //保存并开始下一个方向 按钮
                                again = "directionAgain";
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
                            if (pporzz && ($("input[name=haveFile]").val()==null || $("input[name=haveFile]").val() == '')){
                                alert("屏拍或者纸质资料不能为空");return false;
                            }
                            if ($("input[name=haveSound]").val()==null || $("input[name=haveSound]").val() == ''){
                                alert("是否录音不能为空");return false;
                            }
                            var haveReimbursement = $("input[name=haveReimbursement]").val();
                            if (haveReimbursement ==1){
                                verificationParameters();
                            }else {
                                $("#editForm").submit();
                            }
                        }
                        return true;
                    }
                })
            }, function(){

            });
        }else {
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
                    //设置值
                    commitValue();
                    if(btnCode == 'direction' || btnCode == 'successDirection' || btnCode == 'successDirectionPopup' || btnCode == 'directionAgain' || btnCode == 'surveyFee'){
                        //获取iframe页面的图片信息
                        var fileList = window.frames["fileMidIframe"].document.getElementById("fileList").value;
                        if(!$("#areaType").val() || $("#areaType").val() == 0){
                            i = 0;
                            alert("请选择地址");return false;
                        }
                        if(!$("#taskId").val()){
                            alert("请选择任务类型");return false;
                        }
                        if(!$("#newId").val() || $("#newId").val() == 0){
                            alert("请选择任务子类");return false;
                        }
                        if(!$("#directionName").val()){
                            i = 0;
                            alert("请填写方向名称");return false;
                        }
                        if(!$("#directionResultTypeId").val() || $("#directionResultTypeId").val() == 0){
                            i = 0;
                            alert("请选择任务结果");return false;
                        }
                        $("#files").val(fileList);
                        if(btnCode == 'successDirectionPopup') { //已完成全部方向 按钮
                            $('#successDirection').val('success');
                        }
                        if(btnCode == 'directionAgain'){ //保存并开始下一个方向 按钮
                            again = "directionAgain";
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
                        if (pporzz && ($("input[name=haveFile]").val()==null || $("input[name=haveFile]").val() == '')){
                            alert("屏拍或者纸质资料不能为空");return false;
                        }
                        if ($("input[name=haveSound]").val()==null || $("input[name=haveSound]").val() == ''){
                            alert("是否录音不能为空");return false;
                        }
                        var haveReimbursement = $("input[name=haveReimbursement]").val();
                        if (haveReimbursement ==1){
                            verificationParameters();
                        }else {
                            $("#editForm").submit();
                        }
                    }
                    return true;
                }
            })
        }
    }

    function verificationParameters() {
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

        var taskName = $("input[name=clicktaskName]").val();
        if ( taskName== '走访就诊医疗机构' || taskName== '走访出生医疗机构'
            ||taskName== '居住地医疗机构排查' ||taskName== '工作地医疗机构排查'
            ||taskName== '出险地医疗机构排查' ||taskName== '户籍所在地医疗机构排查' ){
            if (medicalHistoryMoney != '' && medicalHistoryFiles == '0'){
                alert("病史费凭证不能为空")
                return;
            }
            if (medicalHistoryMoney == '' && medicalHistoryFiles != '0'){
                alert("病史费不能为空")
                return;
            }
            if (troubleshootingMoney != '' && troubleshootingFiles == '0'){
                alert("排查凭证不能为空")
                return;
            }
            if (troubleshootingMoney == '' && troubleshootingFiles != '0'){
                alert("排查费不能为空")
                return;
            }
            if (printingMoney != '' && printingFiles == '0'){
                alert("体检凭证不能为空")
                return;
            }
            if (printingMoney == '' && printingFiles != '0'){
                alert("体检费不能为空")
                return;
            }
        }
        else {
            $("input[name=medicalHistoryMoney],input[name=medicalHistoryDesc],input[name=troubleshootingMoney],input[name=troubleshootingDesc]").val('');
            $("input[name=medicalHistoryFiles],input[name=troubleshootingFiles]").val('');
            if (taskName != '体检机构排查'){
                $("input[name=printingFiles],input[name=printingMoney],input[name=printingDesc]").val('');
            }else {
                if (printingMoney != '' && printingFiles == '0'){
                    alert("体检凭证不能为空")
                    return;
                }
                if (printingMoney == '' && printingFiles != '0'){
                    alert("体检费不能为空")
                    return;
                }
            }
        }

        if (accommodatioMoney != '' && accommodatioFiles == '0'){
            alert("住宿凭证不能为空")
            return;
        }
        if (accommodatioMoney == '' && accommodatioFiles != '0'){
            alert("住宿费不能为空")
            return;
        }
        if (accommodatioDays=='' && accommodatioMoney!=''){
            alert("住宿天数不能为空")
            return;
        }
        if (accommodatioMoney=='' && accommodatioDays!=''){
            alert("住宿金额不能为空")
            return;
        }
        if (parseFloat(accommodatioMoney)/parseFloat(accommodatioDays)>130){
            alert("住宿费金额每天不可以超过130")
            return;
        }
        if (trainMoney != '' && trainFiles == '0'){
            alert("火车凭证不能为空")
            return;
        }
        if (trainMoney == '' && trainFiles != '0'){
            alert("火车费不能为空")
            return;
        }
        if (carMoney != '' && carFiles == '0'){
            alert("汽车凭证不能为空")
            return ;
        }
        if (carMoney == '' && carFiles != '0'){
            alert("汽车费不能为空")
            return ;
        }
        if (aircraftMoney != '' && airFiles == '0'){
            alert("飞机凭证不能为空")
            return;
        }
        if (aircraftMoney == '' && airFiles != '0'){
            alert("飞机费不能为空")
            return;
        }
        if (selfDrivingMoney != '' && selfDrivingFiles == '0'){
            alert("自驾凭证不能为空")
            return;
        }
        if (selfDrivingMoney == '' && selfDrivingFiles != '0'){
            alert("自驾费不能为空")
            return;
        }
        if (otherMoney != '' && otherDesc == ''){
            alert("其他费用备注不能为空")
            return;
        }
        if (otherMoney != '' && otherMoneyFiles == '0'){
            alert("其他费用凭证不能为空")
            return ;
        }
        if (otherMoney == '' && otherMoneyFiles != '0'){
            alert("其他费用不能为空")
            return ;
        }
        $("#editForm").submit();
    }
    function returnCallback(event,param){
        if ($("#feeOpr").val() == 'edit'){
            reloadParent();return;
        }

        if(two ==1 ){
            reloadParent();
        }else{
            var apiRsp=getApiJson(param.data);
            if(apiRsp && apiRsp.isSuccess){

            }else{
                $("[name=btnUpload]").removeAttr("disabled");
                alert(apiRsp.msg);return;
            }
            var orgReview ='${orgReview}';//报告复核info页，增加方向，不重定向

            if(orgReview == 1){
                reloadParent();
            }else{
                //因“保存并开始下一个方向”按钮的功能，需传值again=1做判断，但是当无需again值的时候，需重定向传值
                if(again=="directionAgain"){
                    parent.location.href = "${ctx}/survey/case/sic/info?id=" + ${dto.id} + "&menuCode=dcy-list"+"&again=1";
                }else{
                    console.log("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk","${ctx}/survey/case/sic/info?id=" + ${dto.id} + "&menuCode=dcy-list")
                    parent.location.href = "${ctx}/survey/case/sic/info?id=" + ${dto.id} + "&menuCode=dcy-list";
                }
            }
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

    var files = [];
    var values = "";

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
        $('.listHistory').hide();
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


        if(cityType == 5 || cityType == 6){
            $("#areaType").val(1);
            $("#regionType").val(cityType);
            $("#hisAreaType").val(1);
        }else if(cityType == 2 || cityType == 3 || cityType == 4){
            $("#areaType").val(cityType);
            $("#hisAreaType").val(cityType);
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

        if(cityType == 5 || cityType == 6){
            $("#hisAreaType").val(1);
            $("#areaType").val(1);
            $("#regionType").val(cityType);
        }else if(cityType == 2 || cityType == 3 || cityType == 4){
            $("#areaType").val(cityType);
            $("#regionType").val(0);
            $("#hisAreaType").val(cityType);
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
//                        $("#tr_formerCase").append('<div class="li li-hover"  onclick="changeArea(\''+val.areaName1+'\',\'3\')">sdfasdfas</div>');
//                        $("#tr_formerCase").append('<div class="li" onMouseOver="this.style.background=\'#FB5FD\'" onMouseOut="this.style.background=\'#fff\'"  onclick="changeArea(\''+val.areaName1+'\',\'3\')">sdfasdfas</div>');
                        $("#tr_formerCase").append("<div class='li li-hover' style='cursor:pointer' onclick='changeArea("+val.areaId+","+val.areaId2+","+val.areaId3+",\""+val.areaName1+"\",\""+val.areaName2+"\",\""+val.areaName3+"\","+val.cityType+","+ val.areaType +",\""+val.cityTypeName+"\")'> "+val.areaName1+""+val.areaName2+""+val.areaName3+" <span>"+val.cityTypeName+"</span> </div>");
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


    function setSurveyReason(cur){
        $("#surveyReason").val($(cur).val());
    }
</script>


<script>
    init();
    function init(){
        setBindEvent();//绑定事件
        setValue();//设置显示属性值(选中与不选中)
        setAttr($("#taskName").val());//设置显示属性

        //根据任务类型加载子类及结果。
        ajaxTaskSubData($("#taskId").val());
    }

    function setBindEvent(){
        //阳性绑定事件
        $('.body-right .huzhuattr.sun .item').on('click', function () {
            console.log("item22222222222222222222222222222");
            var _this = $(this);
            var dataKey = _this.attr("data-key");
            $(".sundesc").hide();
            console.log("ttttttttt",$(".sundesc").length, $(".sundesc"))
            if(_this.text() == '是'){
                $("." + dataKey + ".sundesc").show()
            }
            console.log("sssssssssssss",$(".sundesc").length, $(".sundesc"))
            _this.parent().attr("data-value",_this.text());
        });

        //单选div绑定事件
        $('.body-right .btns.radios').on('click','.item', function () {
            var _this = $(this);
            console.log(_this);
            if (!_this.hasClass('active')) {
                _this.addClass('active');
                _this.siblings().removeClass('active')
            }
            var dataCode = _this.attr("data-code");
            var dataValue = _this.attr("data-value");
            if (dataCode) {
                console.log(dataCode)
                if (dataCode == 'tasks'){//如果是任务类型 。 则根据任务类型加载子类
                    ajaxTaskSubData(_this.attr("data-key"))
                }else if(dataCode == 'tasks-sub'){//如果是任务了类。 则根据任务子类加载结果
                    ajaxTaskSubResult(_this.attr("data-key"));
                }else if(dataCode == 'tasks-sub-result'){//如果是任务结果。 则根据任务结果加载分值
                    console.log(111111111111);
                    ajaxTaskSubSorce(_this.attr("data-key"));
                }
            }
        });
    }

    var taskIdTwo;

    //加载异步数据同时选中异步数据
    function ajaxTaskSubData(taskId){
        taskIdTwo=taskId;
        console.log('hhhhhhhhhhhhh:',taskId);
        $("#newIdList div").remove();
        ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId",{"surveyCode":"taskInfo","btnCode":"1000","taskInfoId":taskId},function(v,e,p){
            var _html = "";
            var results = e.data.results;
            if (results.length > 0){
                var directionId = $("#directionId").val();
                var selNewId = e.data.results[0].id;
                // var selNewName = e.data.results[0].name.trim();
                var taskInfoName = e.data.results[0].taskInfoName.trim();
                if (directionId != '') {
                    //当前选择的任务类型等于原始任务类型
                    if(taskId == $("#taskId").val()){
                        selNewId = $("#newId").val();
                        // selNewName = $("#newName").val();
                    }
                }
                for(var i = 0; i < results.length; i++){
                    var item = results[i];
                    if (item.id == selNewId){
                        _html += "<div class='item active' data-code='tasks-sub' data-key='"+item.id+"' data-value='"+item.name+"'>" + item.name +"</div>";
                    }else{
                        _html += "<div class='item' data-code='tasks-sub' data-key='"+item.id+"' data-value='"+item.name+"'>" + item.name +"</div>";
                    }
                }
                console.log('kkkkkkkkkkkkkkkk',taskInfoName,taskInfoName == '走访就诊医疗机构');
                setAttr(taskInfoName);
                console.log('ppppppppppppppppppp:',selNewId);
                //根据子类加载结果
                ajaxTaskSubResult(selNewId);
            }
            if(results.length == 1){
                $("#taskInfoContent").hide();
            }else{
                $("#taskInfoContent").show();
            }
            $("#newIdList").html(_html);
        });
    }

    var newIdTwo;
    function ajaxTaskSubResult(newId){
        var cruxId=$("#cruxId").val();
        newIdTwo=newId;
        ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId",{"surveyCode":"taskInfoContent","btnCode":"1000","taskInfoContentId":newId,"id":cruxId},function(v,e,p){
            var _html = "";
            var results = e.data.results;
            var type=e.data.count;
            if (results.length > 0){
                var directionId = $("#directionId").val();
                var selDirectionResultTypeId = e.data.results[0].directionResultTypeId;
                var selDirectionResultTypeName = e.data.results[0].directionResultTypeName;
                if (directionId != ''){
                    if(newId == $("#newId").val()){
                        selDirectionResultTypeId = $("#directionResultTypeId").val();
                        selDirectionResultTypeName = $("#directionResultTypeName").val();
                    }
                }
                for(var i = 0; i < results.length; i++){
                    var item = results[i];
                    if (item.directionResultTypeId == selDirectionResultTypeId){
                        if(type==1){
                            $("#directionScoreDiv").show();
                            if ($("#orgPoint").val() == 1){
                                item.score = item.pointScore;
                            }
                            $("#directionScore").val(item.score);
                        }
                        _html += "<div class='item active' data-code='tasks-sub-result' data-key='"+item.directionResultTypeId+"' data-value='"+item.directionResultTypeName+"'>" + item.directionResultTypeName +"</div>";
                    }else{
                        _html += "<div class='item' data-code='tasks-sub-result' data-key='"+item.directionResultTypeId+"' data-value='"+item.directionResultTypeName+"'>" + item.directionResultTypeName +"</div>";
                    }
                }
            }
            if(results.length == 1){
                $("#directionModel").hide();
            }else{
                $("#directionModel").show();
            }
            $("#directionResultTypeIdList").html(_html);
        });
    }

    function ajaxTaskSubSorce(directionResultTypeId){
        var cruxId=$("#cruxId").val();
        ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId",{"surveyCode":"taskInfoScore","taskInfoContentId":newIdTwo,
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
                        if(item.taskInfoContentId==newIdTwo && item.directionResultTypeId==directionResultTypeId){
                            $("#directionScore").val(item.score * item.scoreRate);
                        }
                    }
                }
            }
        })
    }

    function setValue() {
        $('.body-right .btns.radios').each(function(index,obj){
            var dataValue = $(obj).attr("data-value");
            var code = $(obj).attr("data-code");
            if (code){
                if (code == 'tasks'){// 任务类型
                    if (dataValue == ''){//默认选中
                        var curSelTask = $(obj).find(".item").eq(0);
                        curSelTask.addClass("active");
                        $("#taskId").val(curSelTask.attr("data-key"));
                        $("#taskName").val(curSelTask.text().trim());
                    }
                }
            }
            if(dataValue){
                $(obj).find(".item").each(function (i,item) {
                    if($(item).text().trim() == dataValue){
                        $(item).addClass("active");
                        $(item).siblings().removeClass('active')
                    }
                })
            }
        })
    }

    function setAttr(name) {
        // debugger
        var attrCode = "attr1";
        if(name == '面访患病成员及申请人' || name == '面访患病成员家属'){
            attrCode = "attr1";
            $("input[name=clicktaskName]").val('')
        }else if(name == '走访就诊医疗机构' || name == '走访出生医疗机构' || name == '居住地医疗机构排查'
            || name == '工作地医疗机构排查' || name == '出险地医疗机构排查'|| name == '户籍所在地医疗机构排查' || name == '走访街道办事处或村委会'){
            attrCode = "attr2";
            $("input[name=clicktaskName]").val(name)
        }else if(name == '走访居住地' || name == '走访户籍所在地' || name == '走访工作单位'
            || name == '走访疾控防疫中心' || name == '走访公检法等机关单位'|| name == '走访鉴定机构' || name == '走访材料出具机构'){
            attrCode = "attr3";
            $("input[name=clicktaskName]").val('')
        }else if(name == '商保排查'){
            attrCode = "attr4";
            $("input[name=clicktaskName]").val('')
        }else if(name == '社保排查' || name == '药店排查'){
            attrCode = "attr5";
            $("input[name=clicktaskName]").val('')
        }else if(name == '体检机构排查'){
            attrCode = "attr6";
            $("input[name=clicktaskName]").val(name)
        }else if(name == '事故地点排查'){
            attrCode = "attr7";
            $("input[name=clicktaskName]").val('')
        }else if(name == '事故处理机构排查'){
            attrCode = "attr8";
            $("input[name=clicktaskName]").val('')
        }
        if (name=="居住地医疗机构排查" || name=="走访就诊医疗机构" || name=="工作地医疗机构排查"
            || name=="社保排查" || name=="体检机构排查" || name=="商保排查" || name=="药店排查" || name== "出险地医疗机构排查" ||name=="户籍所在地医疗机构排查"){
            $("div[name=pporzz]").show();
            pporzz = true;
            // $("input[name=haveFile]").val('');
        }else {
            $("div[name=pporzz]").hide();
            pporzz = false;
            $("input[name=haveFile]").val('');
            $("div[data-type=pporzz]").children("div").removeClass("active");
        }
        var haveReimbursement = $("input[name=haveReimbursement]").val();
        var directionhaveReimbursement = $("input[name=directionhaveReimbursement]").val();
        if (haveReimbursement == 1 || directionhaveReimbursement ==1){
            var taskName = $("input[name=clicktaskName]").val();
            if ( taskName== '走访就诊医疗机构' || taskName== '走访出生医疗机构'
                ||taskName== '居住地医疗机构排查' ||taskName== '工作地医疗机构排查'
                ||taskName== '出险地医疗机构排查' ||taskName== '户籍所在地医疗机构排查' ){
                $('.cell-bx').css({
                    display: 'flex'
                })
            }else {
                $('.cell-bx').slice(0,3).css({
                    display: 'none'
                })
                $('.cell-bx').slice(3,9).css({
                    display: 'flex'
                })
            }
            if (taskName == '体检机构排查'){
                // $('.cell-bx').slice(0,1).css({
                //     display: 'flex'
                // })
                $('.cell-bx').slice(2,9).css({
                    display: 'flex'
                })
            }
        }

        $("#attrCode").val(attrCode);

        var classCode = $("#attrCode").val();

        // $(".sundesc").hide();
        $(".body-right .form .huzhuattr").each(function (index,obj ) {
            if($(obj).hasClass(classCode)){
                //阳性说明
                if($(obj).hasClass("sundesc")){
                    var sunValue = $("." + classCode + " .radios[data-code=sun]").attr("data-value");
                    if(sunValue && sunValue == '否'){
                        $(obj).hide();
                    }else{
                        $(obj).show();
                    }
                }else{
                    $(obj).show();
                }
            }else{
                $(obj).hide();
            }
        })

        // $(".body-right .form .huzhuattr.sun .radios").each(function (index,obj ) {
        //     var dataKey = $(obj).attr("data-key");
        //     var dataValue = $(obj).attr("data-value");
        //     if(dataValue == '是'){
        //         $("." + dataKey + ".sundesc").show();
        //     }else{
        //         $("." + dataKey + ".sundesc").hide();
        //     }
        // })
    }

    function commitValue() {
        $('.body-right .btns.radios').each(function(index,obj){
            var dataId = $(obj).attr("data-id");
            if(dataId){
                $(obj).find(".item").each(function (i,item) {
                    if($(item).hasClass("active")){
                        if(dataId == 'taskId'){
                            $("#" + dataId).val($(item).attr("data-key"))
                        }else if(dataId == 'newId'){
                            $("#" + dataId).val($(item).attr("data-key"))
                        }else if(dataId == 'directionResultTypeId'){
                            $("#" + dataId).val($(item).attr("data-key"))
                        }else{
                            $("#" + dataId).val($(item).text());
                        }
                    }
                })
            }
        })
    }

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