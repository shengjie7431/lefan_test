<%--
  Created by IntelliJ IDEA.
  User: lixianfeng
  Date: 2021/2/4
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html><html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=2">
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/layui/layui_exts/soulTable.css">
    <link rel="stylesheet" href="${ctx}/css/lefan23.css?v=1" type="text/css">
</head>
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

    .input-2 {
        width: 100px;
        border: none;
        border-bottom: 1px solid #555;
        text-align: center;
    }

    .div_source_ok{
        display: inline-block;
    }

    /*.dalogs{*/
    /*    display: none;*/
    /*    position: absolute;*/
    /*    bottom: 50%;*/
    /*    left: 20%;*/
    /*    width: 300px;*/
    /*    height: 200px;*/
    /*    background-color: #fff;*/
    /*    box-shadow: 0 0 10px #999;*/
    /*    color: #000;*/
    /*}*/
    .m-state {
        width: 100%;
        /*margin: 0 auto;*/
        margin-top: 15px;
        background-color: rgba(230, 138, 142, 0.24);
        padding: 10px 2%;
    }

    .m-state .m-state__title {
        font-weight: 600;
        font-size: 16px;
        line-height: 30px;
    }

    .m-state .m-state__reason {
        font-size: 14px;
        line-height: 26px;
    }
    /*.dalogsAdd{*/
    /*    display: none;*/
    /*    position: absolute;*/
    /*    top: 30%;*/
    /*    left: 39%;*/
    /*    width: 600px;*/
    /*    background-color: #fff;*/
    /*    box-shadow: 0 0 10px #999;*/
    /*    color: #000;*/
    /*}*/
    .dalogs{
        display: none;
        position: absolute;
        top: 5%;
        left: 5%;
        width: 90%;
        min-height: 400px;
        background-color: #fff;
        box-shadow: 0 0 10px #999;
        color: #000;
        padding: 20px;
    }

    .dalogsAdd{
        width: 600px;
        left: 30%;
    }
    .dalogsTip{
        width: 300px;
        height: 200px;
    }
    .close{
        margin-right: -10px;
        margin-top: -10px;
    }
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
    .icon-step{
        display: flex;
        align-items: center;
        justify-content: center;
        width: 100%;
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
    .icon-step .line{
        width: 100px;
        height: 6px;
        padding-left: 2px;
        /* border-radius: 50%; */
        background-color: #dfdfdf;
    }
    .icon-step div.active{
        background-color: #45B4FE;
    }
    .content-step{
        width: 70%;
        display: flex;
        align-items: center;
        justify-content: space-around;
        padding: 20px 0 30px 0;
        margin: 0 auto;
    }

    .content-step .c-text{
        font-size: 14px;
        line-height: 20px;
        padding: 0 3px;
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
    .content .c-title{
        width: 100%;
        line-height: 20px;
        padding: 10px 0;
        font-size: 14px;
        font-weight: 500;
        text-align: center;
    }
    .content .c-input{
        margin: 20px auto;
        width: 276px;
        height: 40px;
        font-size: 14px;
    }
    .content .c-input input{
        padding: 0 2%;
        width: 100%;
        height: 36px;
        line-height: 36px;
        padding: 0;
        font-size: 14px;
        border: 1px solid #bbb;
    }
    .content .btn-area {
        margin: 0 auto;
        width: 276px;
        display: flex;
        justify-content: space-between;
    }
    .content .btn-area .c-btn{
        width: 120px;
        height: 35px;
        line-height: 35px;
        color: #333;
        border:1px solid #bbb;
        font-size: 14px;
        text-align: center;
    }
    .content .btn-area .c-btn2{
        color: #fff;
        background-color: #259B24;
        border:1px solid #259B24;
    }

    .info {
        position: relative;
        /*width: 100%;*/
        padding: 20px 2%;
        border: 1px solid #bbb;
        font-size: 12px;
        margin: 20px auto;
    }

    .info-title {
        position: absolute;
        top: -14px;
        left: 10px;
        width: 120px;
        height: 26px;
        line-height: 26px;
        color: #45B4FE;
        background-color: #fff;
        border: 1px solid #45b4fe;
        text-align: center;
        font-size: 13px;
    }

    .info-btns {
        width: 100%;
        text-align: right;
    }

    .info-btns .info-btn {
        display: inline-block;
        padding: 0 10px;
        height: 26px;
        line-height: 26px;
        color: #fff;
        background-color: rgba(63, 181, 253, 0.6);
        cursor: pointer;
    }

    .info-content .info-table--title {
        line-height: 20px;
        padding: 10px 1% 10px 0;
        font-size: 12px;
    }

    .info-content .table-1 {
        width: 100%;
    }

    .info-content .table-1 div{
        display: inline-block;
    }

    .info-content .table-1 tr td {
        width: 24%;
        line-height: 20px;
        padding: 10px 1% 10px 0;
        font-size: 12px;
        /* vertical-align: top; */
    }

    .info-content .table-1 tr td .td-div__flex {
        width: 100%;
        display: flex;
        /*align-items : top;*/
    }



    .dalogs1 .dalogsHuzhu-btns{
        width: 100%;
        text-align: right;
        padding: 20px 0;
    }
    .dalogs1 .dalogsHuzhu-btns .dalogsHuzhu-btn{
        display: inline-block;
        padding: 0 7px;
        margin-right: 30px;
        width: 100px;
        height: 38px;
        line-height: 38px;
        background-color: #45B4FE;
        color: #fff;
        font-size: 14px;
        text-align: center;
        cursor: pointer;
    }
    .dalogs1 .dalogsHuzhu-btns .dalogsHuzhu-btn.dalogsHuzhu-btn0{
        background-color: #b7b6b6;
        color: #fff;
    }



    .dalogspub {
        display: none;
        position: fixed;
        margin: 0 auto;
        margin-top: 10px;
        padding: 20px;
        width: 70%;
        min-height: 300px;
        max-height: 88%;
        z-index: 999;
        background-color: #fff;
        top: 6%;
        left: 13%;
        /*overflow: auto;*/
    }

    .dalogspub-close {
        position: absolute;
        top: 0;
        right: 0;
        width: 50px;
        height: 50px;
    }

    .dalogspub-title {
        width: 100%;
        padding: 16px 30px;
        display: flex;
        justify-content: space-between;
        border: 1px solid #bbb;
        border-top-right-radius: 10px;
        border-top-left-radius: 10px;
        box-shadow: 0px -1px 3px #bbb;
    }

    .dalogspub-title .close {
        margin: 0px;
        position: relative;
        width: 2px;
        height: 20px;
        background: #6b6b6b;
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        -o-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        transform: rotate(45deg);
        display: inline-block;
    }

    .dalogspub-title .close:after {
        content: "";
        position: absolute;
        top: 0;
        left: 0;
        width: 2px;
        height: 20px;
        background: #6b6b6b;
        -webkit-transform: rotate(270deg);
        -moz-transform: rotate(270deg);
        -o-transform: rotate(270deg);
        -ms-transform: rotate(270deg);
        transform: rotate(270deg);
    }

    .dalogspub-title .text {
        font-size: 16px;
    }

    .dalogspub-content {
        width: 100%;
        margin: 0 auto;
        border: 1px solid #bbb;
        border-top: none;
        overflow: auto;
        background-color: #fff;
    }

    .dalogspub-content .form-groups {
        width: 96%;
        margin: 0 auto;
        padding: 20px 0;
    }

    .dalogspub-content .form-group {
        overflow: auto;
        margin: 0;
        padding: 10px 0;
        display: flex;
    }

    .label-bars {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
    }

    .label-bars .label-bar {
        padding: 0 12px;
        margin: 5px;
        min-width: 160px;
        height: 36px;
        line-height: 36px;
        color: #3BA9FF;
        border: 1px solid #3BA9FF;
        background-color: #fff;
        font-size: 14px;
        text-align: center;
        cursor: pointer;
    }

    .label-bars .label-bar.active {
        color: #fff;
        background-color: #3BA9FF;

    }


    .label-btns {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
    }

    .label-btns .label-btn {
        padding: 0 12px;
        margin: 8px;
        width: 130px;
        height: 36px;
        line-height: 36px;
        color: #3BA9FF;
        border: 1px solid #3BA9FF;
        background-color: #fff;
        font-size: 14px;
        text-align: center;
        cursor: pointer;
    }

    .label-btns .label-btn.active {
        color: #fff;
        background-color: #3BA9FF;
    }

    .label-btns .label-btn.active1 {
        color: #fff;
        border-color:#c7cbce;
        background-color:  #c7cbce;
    }

    .po-none{
        pointer-events: none;
    }
    .loading-bar {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        margin: 0 auto;
        padding-top: 30%;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.7);
        z-index: 9999;
    }

    .loading {
        margin: 0 auto;
    }

    .loading-text {
        width: 100%;
        height: 60px;
        line-height: 60px;
        font-size: 20px;
        color: #fff;
        text-align: center;
    }
    .downfile-loading {
        display: none;
        padding-top: 59%;
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        /*background: rgba(238, 238, 238, 0.3);*/
        pointer-events: none;
        display: flex;
        align-items: center;
        /* justify-content: center; */
        flex-direction:column
    }

    #loading3 {
        position: relative;
        width: 50px;
        height: 50px;
    }

    .loading3-text {
        padding-top: 20px;
        width: 100%;
        text-align: center;
        color: #fff;
        font-size: 16px;

    }

</style>
<body>
    <div class="main">
        <div class="title">
            <button class="butList active" onclick="setArea()">设置区域</button>
        </div>
        <div class="main-boy">
            <div class="info">
                <div class="info-title">城市信息</div>
                <div class="info-content">
                    <c:forEach items="${areas}" var="area">
                        <span>${area.areaName}</span>
                    </c:forEach>
                    <c:if test="${areas.size() == 0}">
                        <span style="color: #FF0000">暂无设置信息</span>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</body>
<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   function setArea(){
       var width = $(document.body).outerWidth();
       var height = $(document).outerHeight() - 20;
        openDialog({
            frame:true,
            title:"设置区域",
            height:height,
            width:width,
            url:"${ctx}/baseSurvey/popup?id=${id}&orgId=${id}&btnCode=6666&surveyCode=consignor"
        });
    }
</script>
</html>
