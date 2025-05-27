<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

<!DOCTYPE html>
<html>
<head>
    <title>详情</title>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=2">
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/layui/layui_exts/soulTable.css">
    <link rel="stylesheet" href="${ctx}/css/lefan23.css?v=1" type="text/css">

</head>
<style>
    .span-evaluate {
        display: block;
        width: fit-content;
        margin: 3px 1px 1px 1px;
        width: 44px;
        text-align: center;
        cursor: pointer;
    }

    .span-evaluate-one {
        background-color: #3BA9FF;
        color: white !important;
    }

    .span-evaluate-two {
        background-color: green;
        color: white !important;;
    }

    .span-evaluate-three {
        background-color: red;
        color: white !important;;
    }

    .span-evaluate-huise1 {
        background-color: #6b6b6b !important;;
        color: white !important;;
        border: 1px solid #6b6b6b !important;;
    }

    .span-evaluate-huise2 {
        background-color: white !important;
        color: #6b6b6b !important;
        border: 1px solid #6b6b6b !important;
    }


    .lf-select-block {
        padding: 0 8px !important;
        white-space: nowrap;
        background-color: #3BA9FF;
    }

    a {
        color: #428bca !important;
    }

    body {
        position: relative;
    }

    .table-title {
        font-size: 14px;
        font-weight: bold;
        line-height: 40px;
    }

    table.spec-info tr td:nth-of-type(2n + 1) {
        width: 13%;
    }

    table.spec-info tr td:nth-of-type(2n) {
        width: 20%;
        word-break: break-all;
    }

    table.new-bas-info tr td:nth-of-type(2n + 1) {
        width: 12%;
    }

    table.new-bas-info tr td:nth-of-type(2n) {
        width: 15%;
        word-break: break-all;
    }
</style>

<style>
    .layui-table-cell, .layui-table-tool-panel li {
        overflow: auto;
        white-space: initial;
    }

    .stepList {
        margin: 0 32px;
    }

    .layui-table-main .layui-table-cell {
        height: auto;
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

    .stepDot-dot.finished {
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

    .div_source_ok {
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
    .dalogs {
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

    .dalogsAdd {
        width: 600px;
        left: 30%;
    }

    .dalogsTip {
        width: 300px;
        height: 200px;
    }

    .close {
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

    .step-contain .title {
        padding: 20px 0;
        width: 100%;
        font-size: 16px;
        text-align: center;
        display: block;
        border: none;
    }

    .icon-step {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 100%;
    }

    .icon-step .index {
        width: 30px;
        height: 30px;
        line-height: 30px;
        border-radius: 50%;
        color: #fff;
        font-size: 14px;
        background-color: #dfdfdf;
        text-align: center;
    }

    .icon-step .line {
        width: 100px;
        height: 6px;
        padding-left: 2px;
        /* border-radius: 50%; */
        background-color: #dfdfdf;
    }

    .icon-step div.active {
        background-color: #45B4FE;
    }

    .content-step {
        width: 70%;
        display: flex;
        align-items: center;
        justify-content: space-around;
        padding: 20px 0 30px 0;
        margin: 0 auto;
    }

    .content-step .c-text {
        font-size: 14px;
        line-height: 20px;
        padding: 0 3px;
    }

    .border7 {
        width: 100%;
        height: 7px;
        background-color: rgba(223, 223, 223, 0.26);
    }

    .content {
        width: 100%;
        padding: 20px 0;
    }

    .content .c-title {
        width: 100%;
        line-height: 20px;
        padding: 10px 0;
        font-size: 14px;
        font-weight: 500;
        text-align: center;
    }

    .content .c-input {
        margin: 20px auto;
        width: 276px;
        height: 40px;
        font-size: 14px;
    }

    .content .c-input input {
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

    .content .btn-area .c-btn {
        width: 120px;
        height: 35px;
        line-height: 35px;
        color: #333;
        border: 1px solid #bbb;
        font-size: 14px;
        text-align: center;
    }

    .content .btn-area .c-btn2 {
        color: #fff;
        background-color: #259B24;
        border: 1px solid #259B24;
    }

    .info {
        position: relative;
        width: 100%;
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

    .info-content .table-1 div {
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


    .dalogs1 .dalogsHuzhu-btns {
        width: 100%;
        text-align: right;
        padding: 20px 0;
    }

    .dalogs1 .dalogsHuzhu-btns .dalogsHuzhu-btn {
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

    .dalogs1 .dalogsHuzhu-btns .dalogsHuzhu-btn.dalogsHuzhu-btn0 {
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
        border-color: #c7cbce;
        background-color: #c7cbce;
    }

    .po-none {
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
        flex-direction: column
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


<style>
    .dalogs-0108 {
        display: none;
        position: absolute;
        top: 200px;
        left: 30%;
        width: 567px;
        padding: 30px 0;
        margin: 0 auto;
        overflow: hidden;
        border: 1px solid #bbb;
        background-color: #fff;
    }

    .dalogs-0108 .close-bar {
        position: absolute;
        right: 20px;
        top: 16px;
        width: 20px;
        height: 20px;
    }

    .dalogs-0108 .close {
        margin-right: 10px;
        margin-top: 0 !important;
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

    .dalogs-0108 .close:after {
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

    .dalogs-0108 .d-title {
        /*padding-top: 20px;*/
        width: 100%;
        font-size: 14px;
        font-weight: bold;
        text-align: center;
    }

    .dalogs-0108 .d-title .text {
        height: 40px;
        line-height: 40px;
        font-size: 14px;
        text-align: center;
    }

    .dalogs-0108 .dalogs-content {
        width: 100%;
        margin: 0 auto;
    }

    .dalogs-0108 .dalogs-content .dc-p {
        display: block;
        margin: 20px auto;
        width: 90%;
        height: 30px;
        line-height: 30px;
    }

    .dalogs-0108 .d-btns {
        padding-top: 20px;
        width: 86%;
        margin: 0 auto;
        display: flex;
        justify-content: space-between;
    }

    .dalogs-0108 .d-btn {
        width: 220px;
        height: 45px;
        line-height: 45px;
        color: #3BA9FF;
        border: 1px solid #bbb;
        background-color: #fff;
        text-align: center;
        cursor: pointer;
    }

    .dalogs-0108 .d-btn-active {
        color: #fff;
        border: 1px solid #3BA9FF;
        background-color: #3BA9FF;
    }

    .ll-main {
        margin: 0 auto;
        margin-top: 4%;
        width: 91%;
        height: 80%;
        display: flex;
        align-items: center;
        flex-wrap: wrap;
    }

    .ll-main .label-btns {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        margin-top: 20px;
    }

    .ll-main .label-btns .label-btn {
        padding: 0 12px;
        margin: 5px 10px;
        width: 180px;
        height: 36px;
        line-height: 36px;
        color: #3BA9FF;
        border: 1px solid #3BA9FF;
        background-color: #fff;
        font-size: 14px;
        text-align: center;
        cursor: pointer;
    }

    .ll-main .label-btns .label-btn.active {
        color: #fff;
        background-color: #3BA9FF;
        border: 1px solid #3BA9FF;

    }

    .ll-main .label-btns .label-btn.disabled {
        pointer-events: none;
        background-color: #d3d3d3;
        border-color: #d3d3d3;
        color: #0000007a;
        cursor: not-allowed;
    }

    .ll-main .ll-btn {
        margin: 0 auto;
        margin-top: 30px;
        width: 120px;
        height: 36px;
        line-height: 36px;
        color: #fff;
        background-color: #3BA9FF;
        text-align: center;
        border-radius: 18px;
        display: flex;
        justify-content: center;
        align-items: center;
        cursor: pointer;
    }

    .ll-main .ll-btn span {
        padding-right: 8px;
    }

    .process {
        position: fixed;
        top: 20%;
        left: 20%;
        width: 50%;
        height: 440px;
        padding: 30px 20px;
        border: 1px solid #bbb;
        background: #fff;
        z-index: 9999;
    }

    .process .bars-title {
        width: 100%;
        text-align: center;
        font-weight: bold;
    }

    .process .bars {
        width: 100%;
        height: 360px;
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
        border: none;
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
        margin-left: 4px;
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

    .lf-btn {
        width: 120px;
        height: 30px;
        line-height: 30px;
        color: #3BA9FF;
        border: 1px solid #3BA9FF;
        background-color: #fff;
        text-align: center;
        cursor: pointer;
        float: right;
        margin-left: 10px;
        margin-bottom: 20px;
    }

    .lf-btn-active {
        width: 120px;
        height: 30px;
        line-height: 30px;
        color: #fff;
        border: 1px solid #3BA9FF;
        background-color: #3BA9FF;
        text-align: center;
        cursor: pointer;
    }

    .updateSubmit, .updateCancel {
        display: none;
    }

    #tableCopy {
        display: none;
    }

    #tableCopy thead tr {
        pointer-events: none;
    }

    #tableCopy tbody tr {
        background-color: rgba(59, 169, 254, 0.3)

    }

    #tableCopy tbody tr td {
        border-top: 1px solid #fff;
    }

    #tableCopy tbody tr td a {
        pointer-events: none;
    }

    .highlight {
        background-color: rgba(59, 169, 254, 0.7) !important;
    }

    div.record {
        display: none !important;
    }

    .record.active {
        margin-left: 6px;
        width: 30px;
        height: 30px;
        display: inline-flex !important;
        align-items: center;
        background-color: #fff;
        position: relative;
    }

    .record .icon-history {
        display: inline-block;
        width: 18px;
        height: 18px;
        background: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAANy0lEQVR4Xu1dbU4bSROuMuPNj4BeIu2CXilmyQmWnCDkBAFp4W/MCdacADhBnBOE/IVXgpwg5gRxThCCI60gKy2rsD/8wdSrGs+AMWNPV0/3fDH8iuKe/qh6urrq6epuhPLvQUsAH/Toy8FDCYAHDoISACUAHrgEHvjwSwswBoCfDzov+L8Q8J/vm0/bRcfHgwHA/NGfy05/8GsFYRWIlhFxmYBWEHBeRckEdImAHiBcoDZgpU0unf61WTtR+T6rZQoLAJ7JQ2XDGiKs2FQAEbQJqVUBaHWduZPL9SeXNtszWXdhAMAzvHp9/QJdWgOkVdWZbVKYQV0MCATY71adD5fr/z210YapOnMPgMXDs1dEWEeENVNCMVoP0Skh7vec2bdZtAy5BADP9p8Gg9dIVAfEZaMKs1gZEe27iO//2qi1LDYjqjpXAPAU3x+8yexsVxd9C4Ca5xtLH9Q/sVMyFwAYKr6/g4h1O2JIqVZeHiqwffH70nFKPcg2FVxYxd/Xdssl3E6Dd8isBVg47OwgwG5aMyONdtlH6FXntpN0FjMHgF8Ovq0g0DvbsXsaClZpkwknQNhKalnIFAAe4qyfBAoXqNl35vZsW4NMACCtWU8EnxHAY+0IYWJohgSrXhmmjhH/ozKTjZQhOnWhsm7TN0gdAAv/O1sDgne2mTsC+gCAbReghYSXukKdP/p73hlcrcwArhC4vJfA4PjViMJDKuElgajCDuK+jTZSBYBNk+/P7tY1wrFt4oWjlUeD6zUiYkbyNxuKYgfxYnNpy3TdqQCAZ9FP/R9vTMf1RPQPIBwTVZq6MzyugAMwAFDDtGWwESUkDoCh8q8+mvTyebYTYLNffXxs22mSAOTnw87qDMEuIHg5Bib+eKOpV519aWqciQLAtPJ5xhNUGrbWRxMK4zoYCBWgOgK+NlGnSRAkBgDjygfY6zmzTVMzwYRiouowaRFMgSARAJhUPnvzPafayPo++zQw/HLwrY7gNuOGlCZAkAgAFg87HwGGsbTuH6/zLkLDtkev2z/pd96kGPzYR8BX0m/HyrfON2ovdeuwDoDFg04TEP7Q7eDwO9w+33jajFdHNr82Yg0I3p5v1jjqEP9ZBQAProL0TtyrkQ9cwq2sO3lxxsffMhNaQeItYW1CSVdO1gDg0bvoftRl+HwPfzWteD6uUqXf85LwqH91HCdkdAmfS+VlBQDDwfz4pJuu5cf1delgpEJnkAK6ryuAK5wqToBtquDx99+fvpfWZar8wuEZ+wV64SLRabc691wSGVkBQJxBsPJ71dlVySB0hB9BQ7e6jrOVVqQRU37HF5u1dVWZGAeAF+sCsNcv/iOg9z1nrmFb+YuH3xoA9GZaBznEutisPRcPwtAHcUAg8QeMAiCW6Sc4Od+sxQoVVWQ/zCjuf1LzTdKNPnRBwDuIPWfumcpEMgqAhcPOLgLsqChitAw7fL3q3LJKh6V1j5fn7WckPFKqJyFQTuvL4kGnpeMYsjW92FiKTKI1BoDhLtjgi5JgxwrpeK867fA3UpCeb9SMyUinzz6L2tLZZr4GeBlFnBkbnK65SprkyRsAAp4AwW1JqWMVP8YIAHRnP/P6FxtLiR7pyiMAfBBokWpRDqERAOjM/qTCvXs+gNBPSXsJGO3/wuHZsXjvgOj0fHPp2aTlJzYAdGZ/mixfXi0AK9DPpDqVLgXTrEBsAGjNfoC9i41aKoc+8gwAz4mVRDHBtJ9iBWIBQHf2JxXyhZm9vAOAx6QTGhLSethhk1gAkAqTO08pzv48hoFhINaaeBMc7lgAWDw4+yLZ8EmS8Jnk9EhBmyUncMwhFG8adR3n2fj+hjYAdDj/tGd/USzA0CGUE29h8tcGgNT5y8LsLxIAhmMRbh2HOIM3APCyd8DduTHpRKeA6F1wRKP/Rjzl69FmppylCzO/WZj9RQOAjhUep4c9AOhUJOe1aa3rVD+ntcce9LcoPkAwnsXDDk9S9VSysfxBDwBSociVH/LFiFUJLl7szzz+YHtHUDrWrDqBtwCIzm0Ylf74/kB6AAjFBIiyWXSAWDQAeDkYg6u/JbIYjQY8AOh4lJIGJWUnERaSOqaVLRoAfGdQtEcwSg3fOIELB522zp6zKcUE9dh2FosIAGn6/WiyyN0oIGYOvwkwhJEVJuotqhOoY8E5ZexiY+kJf3sDAJ21xKRivLpinHBR7UsRLQCPXRoNBBPtDhEkJhZUpa5QLqn8gMICQHgEL+AD7gJAZ6tRQbkqRZLKCywqAOTbxMOM53tUsNSUqCg3qoxtx2+0/aICwD9f+ClK1je/+8vtfQAITYlyg5MKJpx6XVQA+H4AKevDl/s9ACTJCQw3iKorSdLDRQaAJJQPIoHQ3UBJRcqICy2Y/MmbIgNAminENHcoAKTEgg4I0kgJH7JmstNLWd8LiOvfhALANieQZm5ACYBbyHSd2ScTE0JscgK2+f5pFqkEwK10mAuYDABbnEACbF8JALVFeSoAdOhFhWa/dp3ZFdt7/iYBoHLAUmHciRSRkkHRADDMCWRBmNIlwNui8F7yqO4lGa7qIEaa2RUJAJOcQJJsn0kLENTlveQB2Mzq+386EU4kALxKDeQJ8EbPxWbN6vOtqjNGxwLcqZsfcahUdtO8SGrSWKVjUwKACU4gqY0eFRBIzeSUOlvXAHtRFzCo9MlUGSsAiM8JJM/2TROo7gnbiVsZGfIPpACYygPcZZiEBxBuF85ELn6SziCpoKLqz4p/YIwKHh+wNLzwPefEN3qiFBX8HufenaltpOwfSADAbOzF5tK88tEwaZ5AmmyfChCsgWDYeCr+weJhJ/528CThSW79TmujR0Xx42Uk45LWnyR/IE0ICXSkbAFUOYE0N3qkCgrKe8ID4mvtjb3tM84fXGzU9nT7p/KddJkOeBllAHicQMRp1DTv/lERUlQZX4j8LoH6WbuoSv3fbT37FjQvtWShSaFRY5l2pXnelX836unsAlFDehlTlPxssqELB51PkpfYQtPCowYQ/O4/fsT3+zG716YKtXoz1XbWuXLV8XE5/0mXpvbV7SGNSe7wlfZVcj4wiAC4DdESIOlUUcqa9g9sbIhJ2dpRJ70EgCJSfSHz1Xax/IOomzsVu3OnmPwCyVt2tgSAQOLDZeGqEcc/MG0BdKj60b2ZEgACAARF/TcHdnX8A+bfTSbESM0/AHw936gtB2MpAaABgDFnWP1tYAvpcFLvf/wAbgmAGAAYIZL4Ju+p/oENdlTK/nF/x7fmSwAYAMBt2HjfPxg+ae9lEhl/5ziKmAsZ2h3zX4aBhpQ/Xg3zJMH/DZzZtsk1f9QPEb/QErIElRbAEghsV6sx+8HoVbG2B1jWP1kCOms/TDiFXVqAHCJN5zV2K9fF51B2ue+ydNvXH/A956/kAXIIBX+D6ovao5e3A7T6ZEwO5ZjbLi8cdI4QQfrK2sTZX4aBOYKCBuXrjS5q86l0AnMAAvb6Ed2PUtM/yfMfHXIJgIwDwM9e/ijJ9gmGpLLzWAIg4wDQCfl4SIk/Hp1xOeayewsHZ+8QMfIF8PHBSTKzU7MAiwedP2Do0a5yrhwCtgnpbdjbdrnUXsxO6yrfm/0T3ggM61LiAIha04jguFed3bKxgRJTJ4l9Hkv5wge5EweAyprGz5oQ4Nb3zaftxKSekYbiKJ+zfaRX8CQKAEksy8uCC7iepfP3NjHi5/Yd8ZKo047uuYxEASDPXo0mMnSElbVvhqnn7pHkFdbxMUQRPpPGnCgApE/NBp22fawqTUCwM0xInGA6r9sP1ZAvdSdQcn79fmgDbRdhuyhLgu8Mv9Pg9u+IJo7yuaJELYBOFktIjLvfq85t5zlKMDHrvXCP4HOvOrsaRxaJAkArkyXEbrGDSFhpZPGmrmlm3L+gakfX0Rut24TyE7cA3KDh+3lSuYlDulabVLypmR+MIVELEDQqPcseKXB+hhaw2a3Ovo9jDiPbERZYPDx7BYANEzP+1iGOb/ZHh5EKALgDzAkguE2TZ/C927oIjgkqb9MikYbHxgavkageJ6wLwxofLuk5c3WTIE8NAD4IVhDclkkQjMwU3ltoVQBaXWfuxKTQxpXjHyF/EextCA2FWnELx8pS8QHGR2v5tq6b5jx62QAguL9O/+q3GYAVAlpBZu4Qbw5bqmlTVkqX5FFpJVULEHTQxm0cKoNnUCDCJT9fj4CXE8zufAXQu+d4qHB9wkalT+OePgHWbS5nmQBAMGiOEOKcvZcKONPlCd52q7O7NpeuTCwB95cEdqL6fDfPq0wryFLnvMOkFagnlReRKQswKlM/dt6PeyWLJT3ZqTahWZ+JMFBVgg9iWSA4cQEbNtf6SfLOrAUY7bB/JUvxlgWCk2uE3TQ3uHIBgAAMfn58AwjWbHAHqlYpbjnm8V2ERpqKD8aQKwCMho3V/r9rUdeyxFWUye/ZuUPA/W7VaWbpQs1cAmDcWawA1XVu7DKp4El1MX0LCPtJefXSMeUeAKNW4dHg3zqBu4pD4ibWhY5SQY4TOIi433Ue79uO4+P0M5M8QNwB3QLiz+Vq/3oV0QMEJ1raAsRX8pjESusaqJ2FdV0iw8JYgKhBcyQxBAQtA9AyEi4DAnP4SsDw13AvTZ0QWoDU7s3MtbI+w6Pk8mAAECWI4Pfghq+B45xmyVlT7b+0XAkAqcQKVr4EQMEUKh1OCQCpxApWvgRAwRQqHU4JAKnEClb+/3ya/JQdM5BsAAAAAElFTkSuQmCC');
        background-repeat: no-repeat;
        background-size: contain;
        background-position: center;

    }

    .record .num {
        position: absolute;
        top: 2px;
        right: 2px;
        color: #3ba9ff;
        font-size: 10px;
    }

    .case-resaon {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        background-color: rgba(254, 173, 59, 0.87);
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 20px 3%;
        z-index: 9999;
    }

    .case-resaon-green {
        background-color: rgba(41, 214, 49, 0.35) !important;
    }

    .case-reason-text {
        width: 80%;
    }

    .case-reason-text div {
        color: #000;
        line-height: 26px;
    }

    .case-reason-state {
        width: 130px;
        height: 40px;
        line-height: 40px;
        background-color: #fff;
        color: rgba(254, 173, 59, 1);
        text-align: center;
        cursor: pointer;
    }

    .poi-no {
        pointer-events: none;
    }

    .disabled1 {
        background-color: #faf2cc;
    }

    .disabled2 {
        background-color: rgba(0, 0, 0, 0.07) !important;
    }

    .iframe-content {
        width: 100%;
        height: 600px;
    }

    #iframeNW {
        width: 100%;
        height: 600px;
    }

    .synchrodata {
        position: fixed;
        top: 20px;
        right: 20px;
        width: 66px;
        height: 66px;
        border: 1px solid #eee;
        background: rgba(59, 169, 254, 0.8);
        color: #fff;
        border-radius: 66px;
        text-align: center;
        cursor: pointer;
    }

    .synchrodata-icon {
        margin-top: 10px;
        display: inline-block;
        width: 20px;
        height: 20px;
        background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEQAAABACAYAAACjgtGkAAAFrUlEQVR4Xu1bi1FVMRDdrUCpQKxAqECtQKhAqUCtQKxAqUCoQKhAqUCtQKhAqGCdcyd5k7cvudl8bu6MkhmGYW4+m5P978K0whCR10S0T0Qv3PH+N/78TkQ/3c8VM9+NJJFHHSYiB0T0loiOiOhxwbnnRHTGzABp8bE4ICICTvgScEPtpS6J6D0z39RuYFm3KCAi8omI3lkIKZhzyswfC+YXTV0EEBGBSHwjIohJatw6fYEX9+KA+V63PJlZC245WUK/dAfEAMYFEZ0zM5RncogIdM0bInqVmAQQX/YGpSsgGTCuccFSHeCU8Wcieh4BpjsovQH5ERGTe+gRZoa1qB4iAm6BctbjkpmPqzdWC7sBIiJ4RZjVcACMF71MpuMWiNojdQ6sD85vHl0AERE4VlCii4HhN54B5WmpOMbQ6wUIwAi9TZx1zMywBt1HQny6iE4zIAnugGfZ2//YAlZEALa2QIet4tkDEE0Y9MZ+b3Oo2cx5wLAyoT65YGYo3+rRBIgj6rc6/SMzn1ZTVLAwosgRCEKXVAeErYDETGEX5WbBJfEgTbqrFRD4Fgjl/UC4Dg9z2BARiM2z4MAm/dUKCMQFsYcfw8QlMMPa/7lmZm3xzA/UCoiokxBbzMYoZsqME13M8zWczszV96pemJDfZrNnxGEzLWb2FwfEXR7BFVjRiwhC/K3wvoWQUiACkQENiKHC4bkUqQX84O9biyc7yyEOfShNk22vAUREtsSuxx4z4ELfQM8lzXIUEBfGfyjNdvW4TOkeCU95juEABrJuZ7FJO4AYEjypw+6ZuSR5PO3TiUNwSR0B56QwmkvZAsQABtxybORlFDrF65GjGgvTCRCItA//fRkDgHidl0pH7oCyAcSQ7UKSZ0gpIPe0pd9zWTdmPvR7hoAg/oDeCAc4Amm/RcL40ou1znc+C7xrLV4bh3ICxCGoTVfXbFfrZXqtTySYoIPgQ914QGK5heFeZ69L5/ZJWKYpdcBOd/xRmzQFSDmCwu89lGrJeYFDp5ngjpn3AEgshN9rySmUELgiIPC4dS7nBIBopIaG8GsB4nSnTh1cABDY7bAINDSEXxkQbVmvAYjOafyzylSLsoggEY6CvB83AGT1nEaJzuk5N5o6EBEdB/xPHKINyn1Mh3QrC/Z8zSX2EpGoDvmfrYwuzl89+CHbrDf5ITEHZZjpXcvsioguoQCaPR/LaF8EihbKdfFwfw1AErHMVL7wgMTaGZCcRQRYXRZcQhG27umiXXQr6OzeVHEM8yGaS3D2ME5pvahlveMM1HA0GJtgVmfMICKxdBvSc1i0aI+o5VI1cxxXIPkVK7P+cl1OkyTonCryo7GWJU+Hz6fOiRG+IUBcFDxnDMK6cgwr3Me3esa+Iwl2ENIay7rnQLE8klnUHBv7ZjpU7rOKPJHhs9AVzgFnID26dd5cXQYOW6wV0nqwyXSLSOgcmVIPEQ/TSpOfd+XA2OF0S+UO9nquqzhFDDqNs62YyuyaKvczLZo5YNAriyJVsiBvKnY7FkUgBHGChg77MTQRkEtwF8oWWZNdCQhoQBwCJZl6LF9DAn2gB015Wb1mAiQHe8v3GkBazsutfQBEITQEEBFBhzPaEXYKXhYOcetBOkoFWTHMccHc98UBERF4ht4h2ilv5AAREZhk347RpTl3bUB0inLL+swBosCY7lHaLlHKLSM4RKf6QeOmdTIFSMK0msxyKQjh/BGAxDzfjScbAyQBxpBa8+KAAP1E/mEqMKvqGRwnlAZ0eD4EjJ3groXVcmsTrw5xChv34DjB6dLhucnrzdFg+T6EQzwhlTHIMDCGckgASiyXmXq85v9usHDFUKUaIyhST45NGw7GKhzilCx0BCLOVJC4lcUqfeWW+UN1SEioa9SBEtX9XquBsRqHBPpE+yjDzGuKi1bjEAUKgj6IUbd/aa0Vm79SdcWv49IE2wAAAABJRU5ErkJggg==);
        background-size: cover;
        background-repeat: no-repeat;
        background-position: center;
    }

    .synchrodata-text {
        display: block;
        width: 100%;
        height: 20px;
        line-height: 20px;
        color: #fff;
        text-align: center;
        font-size: 10px;
    }

    .layui-layer-shade, .layui-layer-page {
        z-index: 99 !important;
    }
</style>

<style>
    .dalogs-0512 {
        display: none;
        position: absolute;
        top: 200px;
        left: 30%;
        width: 800px;
        height: 400px;
        padding: 30px 0;
        margin: 0 auto;
        overflow: hidden;
        border: 1px solid #bbb;
        background-color: #fff;
    }

    .dalogs-0512 .close-bar {
        position: absolute;
        right: 20px;
        top: 16px;
        width: 20px;
        height: 20px;
    }

    .dalogs-0512 .close {
        margin-right: 10px;
        margin-top: -60 !important;
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

    .dalogs-0512 .close:after {
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

    .dalogs-0512 .d-title {
        /*padding-top: 20px;*/
        width: 100%;
        font-size: 14px;
        font-weight: bold;
        text-align: center;
    }

    .dalogs-0512 .d-title .text {
        height: 40px;
        line-height: 40px;
        font-size: 14px;
        text-align: center;
    }

    .dalogs-0512 .dalogs-content {
        width: 100%;
        margin: 0 auto;
    }

    .dalogs-0512 .dalogs-content .dc-p {
        display: block;
        margin: 20px auto;
        width: 90%;
        height: 30px;
        line-height: 30px;
    }

    .dalogs-0512 .d-btns {
        padding-top: 20px;
        width: 86%;
        margin: 0 auto;
        display: flex;
        justify-content: space-between;
    }

    .dalogs-0512 .d-btn {
        width: 220px;
        height: 45px;
        line-height: 45px;
        color: #3BA9FF;
        border: 1px solid #bbb;
        background-color: #fff;
        text-align: center;
        cursor: pointer;
    }

    .dalogs-0512 .d-btn-active {
        color: #fff;
        border: 1px solid #3BA9FF;
        background-color: #3BA9FF;
    }
</style>
<body>
<c:if test="${(menuCode == 'assign-list' || menuCode == 'assign-org-list' || menuCode == 'survey-list'  || menuCode == 'dcy-list') && showNwPage}">
    <input type="hidden" value="${nuanWaUser}" id="nuanWaUser">
    <input type="hidden" value="${nuanWaSign}" id="nuanWaSign">
    <input type="hidden" value="${nuanWaCompany}" id="nuanWaCompany">
    <input type="hidden" value="${handleId}" id="handleId">
    <div class="iframe-content">
        <iframe src="" id="iframeNW"></iframe>
        <c:if test="${showNwPageSyncBtn || menuCode != 'survey-list'}">
            <div class="synchrodata">
                <span class="synchrodata-icon"></span>
                <div class="synchrodata-text">同步数据</div>
            </div>
        </c:if>
    </div>
</c:if>

<c:if test="${showFrom == 'staff' && (roleCode =='superiorManager-step' || roleCode =='hrManage-step' || roleCode =='ceo-step' || roleCode =='end-step')}">
    <div class="case-resaon ${staffOpinionState == 0 ? '' : 'case-resaon-green'}">
        <div class="case-reason-text">
            <div class=""> 调查员:${surveyUserName}</div>
            <div class="">机构意见:${staffOpinion}</div>
        </div>
        <c:if test="${staffOpinionState ==0 && roleCode =='superiorManager-step'}">
            <div class="case-reason-state">
                标记为已处理
            </div>
        </c:if>
    </div>
</c:if>
<c:if test="${menuCode == 'assign-list' && auto == 1}"> <%--新增委托时，会自动弹窗， 控制X按钮的展示--%>
    <button type="button" class="close" style="margin-top:0; position:absolute; margin-right:0;top:0;right:0"
            data-dismiss="modal" aria-hidden="true" onclick="openAssignOrgList()">×
    </button>
</c:if>
<div class="main"
     style="${(showFrom == 'staff' && (roleCode =='superiorManager-step' || roleCode =='hrManage-step' || roleCode =='ceo-step' || roleCode =='end-step')) ? 'padding-top: 100px' : ''} ">
    <%--    <button class="butList active bianji">编辑</button>--%>

    <input type="hidden" id="menuCode" value="${menuCode}"/>
    <input type="hidden" id="surveyInfoId" name="surveyInfoId" value="${dto.id}"/>
    <input type="hidden" id="nextTransferType" value="${dto.surveyRiskCase.nextTransferType}"/>
    <input type="hidden" id="topSurveyId" value="${dto.surveyRiskCase.topSurveyId}"/>
    <input type="hidden" id="maxTime"/>
    <input type="hidden" id="minTime"/>
    <c:if test="${menuCode == 'assign-org-list'}">
        <input type="hidden" id="assignOrgId" value="${assignOrgId}"/>
        <input type="hidden" id="curSurveyOrgId" value="${curSurveyOrgId}"/>
    </c:if>
    <c:if test="${menuCode == 'assign-list'}">
        <input type="hidden" name="autoOpenInfo" id="autoOpenInfo"
               value="${autoOpenInfo}"/><%--使用场景：“代理委托”录入案件时，可直接“分派调查员”--%>
    </c:if>
    <c:if test="${menuCode == 'visit-list'}">
        <input type="hidden" id="surveyAssorgCaseId" name="surveyAssorgCaseId" value="${surveyAssorgCaseId}"/>
    </c:if>
    <div class="title">
        <c:if test="${maxRole}">
            <button class="butList active" onclick="javascript:reload();">刷新</button>
            <%--            <button class="butList active" onclick="operate(${dto.id},'sendEmail',true)">发送邮件</button>--%>
        </c:if>

        <c:if test="${menuCode == 'task-org-review' || menuCode == 'help-review'}">
            <c:if test="${dto.surveyConsignor.orgAttr == 2}">
                <button class="butList active" onclick="downFileReport(${dto.surveyRiskCase.modelId},'${dto.id}')">
                    下载报告
                </button>
                <button class="butList active" onclick="downFileEntrust(${dto.surveyRiskCase.modelId},'${dto.id}')">
                    下载报告+附件
                </button>
            </c:if>
        </c:if>
        <c:if test="${menuCode == 'all-list' || menuCode == 'survey-list'}">
            <button class="butList active" onclick="downFileReport(${dto.surveyRiskCase.modelId},'${dto.id}')">
                下载报告
            </button>
            <button class="butList active" onclick="downFileEntrust(${dto.surveyRiskCase.modelId},'${dto.id}','word')">
                下载WORD报告+附件
            </button>
            <button class="butList active" onclick="downFileEntrust(${dto.surveyRiskCase.modelId},'${dto.id}','pdf')">
                下载PDF报告+附件
            </button>
        </c:if>
        <c:if test="${menuCode != 'my-list' && menuCode != 'entrust-list' && oprType !='safe'}">
            <%--            <button class="butList active" onclick="operate(${dto.id},'follow',false)">添加跟踪</button>--%>
        </c:if>
        <c:if test="${menuCode == 'all-list'}">
            <button class="butList active" onclick="operate(${dto.id},'backreply',false)">案件沟通</button>
        </c:if>
        <c:if test="${menuCode == 'credit-list'}">
            <c:if test="${dto.entrustCredit == 0 && dto.entrustCreditIsPay == 0}">
                <button class="butList active" onclick="operate(${dto.id},1270,true)">标记支付</button>
            </c:if>
        </c:if>
        <c:if test="${menuCode == 'check-list'}">
            <c:if test="${dto.surveyState == 2}">
                <button class="butList active" onclick="operate(${dto.id},1200,true)">受理通过</button>
                <button class="butList active" onclick="operate(${dto.id},1201,false)">退回</button>
                <c:if test="${dto.supplementState == 0 || dto.supplementState == 2}">
                    <button class="butList active" onclick="operate(${dto.id},1000,true)">发送客服补充信息</button>
                </c:if>
            </c:if>
            <c:if test="${dto.surveyRiskCase.repetition != null && dto.surveyRiskCase.repetition}">
                <button class="butList active" onclick="operate2(${dto.surveyRiskCase.id})">查看重复案件</button>
            </c:if>
        </c:if>
        <c:if test="${menuCode == 'assign-list'}">
            <c:if test="${(dto.surveyState == 4 ||  dto.surveyState == 8 || dto.surveyState == 10 || dto.surveyState == 12 || dto.surveyState == 14
            ||dto.surveyState == 16 ||dto.surveyState == 22 ||dto.surveyState == 30 ||dto.surveyState == 24) && true}">
                <button class="butList active" onclick="operate(${dto.id},112,false)">分派</button>
            </c:if>
            <%--            <button class="butList active" onclick="operate(${dto.id},111,false)">分派</button>--%>
        </c:if>
        <c:if test="${menuCode == 'assign-org-list'}">
            <c:if test="${scoreRole != 'areaManger'}">
                <c:if test="${dto.currentSurveyAssignOrg.orgSurveyState == 0}">
                    <button class="butList active" onclick="operate(${dto.currentSurveyAssignOrg.id},'org1',true)">
                        接收
                    </button>
                    <button class="butList active" onclick="operate(${dto.currentSurveyAssignOrg.id},'org2',false)">
                        拒绝
                    </button>
                </c:if>
                <c:if test="${dataRoleCode != 'districtManger' && showNwPageEdit}">
                    <button class="butList active" onclick="operate(${dto.id},113,false)">
                        分派调查员${scoreRole}</button>
                </c:if>
            </c:if>
            <c:if test="${display}">
                <button class="butList active" onclick="caseClockDetails(${dto.id},'caseClockDetails')">
                    打卡足迹(${dto.punchClockCount})
                </button>
            </c:if>
            <c:if test="${dto.currentSurveyAssignOrg.extensionState == null || dto.currentSurveyAssignOrg.extensionState == 3 || dto.currentSurveyAssignOrg.extensionState == 2}">
                <c:if test="${dataRoleCode != 'districtManger'}">
                    <button class="butList"
                            style="margin-left: auto;background-color: #F0F0F0;color: #2f332a;border: 1px solid #F0F0F0; width: 100px;"
                            onclick="extensionTime(${dto.id},'extension-time',false,'first')">申请延期
                    </button>
                </c:if>
            </c:if>
            <c:if test="${dto.currentSurveyAssignOrg.extensionState == 1}">
                <button class="butList"
                        style="margin-left: auto;background-color: #F0F0F0;color: #2f332a;border: 1px solid #F0F0F0; width: 100px;"
                        onclick="extensionTime(${dto.id},'extension-time',false,'second')">延期审核中
                </button>
            </c:if>
            <c:if test="${dto.currentSurveyAssignOrg.orgSurveyState != 4}">
                <c:if test="${dataRoleCode != 'districtManger'}">
                    <%--                    <button class="butList" style="background-color: #F0F0F0;color: #2f332a;border: 1px solid #F0F0F0; width: 50px;" onclick="operate(${dto.id},'case-return',false)">退回</button>--%>
                </c:if>
            </c:if>

        </c:if>
        <c:if test="${menuCode == 'back-case-list'}">
            <c:if test="${dto.currentSurveyBackCase.backState == 1 || dto.currentSurveyBackCase.backState == 6 || dto.currentSurveyBackCase.backState == 5}">
                <button class="butList active" onclick="operate(${dto.id},113,false)">分派调查员</button>
                <button class="butList active" onclick="operate(${dto.id},120,false)">退回</button>
                <button class="butList active" onclick="operate(${dto.id},121,true)">反馈平台</button>
            </c:if>
            <c:if test="${dto.currentSurveyBackCase.backState == 4}">
                <button class="butList active" onclick="operate(${dto.id},112,false)">分派</button>
                <button class="butList active" onclick="operate(${dto.id},122,false)">退回</button>
            </c:if>
        </c:if>
        <c:if test="${menuCode == 'org-review-list'}">
            <c:if test="${dto.currentSurveyAssignOrg.orgSurveyState == 2 || dto.currentSurveyAssignOrg.orgSurveyState == 6}">
                <%--                如果是保险公司是互助类型。则显示初审通过按钮--%>
                <c:if test="${dto.surveyConsignor.orgAttr == 2}">
                    <button class="butList active"
                            onclick="operate(${dto.currentSurveyAssignOrg.id},'org-help-commit',true)">初审通过
                    </button>
                    <button class="butList active" onclick="operate(${dto.currentSurveyAssignOrg.id},'org4',false)">
                        初审退回
                    </button>
                </c:if>
                <c:if test="${dto.surveyConsignor.orgAttr != 2}">
                    <button class="butList active" onclick="operate(${dto.currentSurveyAssignOrg.id},'org3',true)">
                        初审通过
                    </button>
                    <button class="butList active" onclick="operate(${dto.currentSurveyAssignOrg.id},'org4',false)">
                        初审退回
                    </button>
                    <%--                    <c:if test="${dto.currentSurveyAssignOrg.orgPrimaryType == 2}">--%>
                    <%--                        <button class="butList active" onclick="operate(${dto.currentSurveyAssignOrg.id},'org3',true)">初审通过</button>--%>
                    <%--                        <button class="butList active" onclick="operate(${dto.currentSurveyAssignOrg.id},'org4',false)">初审退回</button>--%>
                    <%--                    </c:if>--%>
                    <%--                    <c:if test="${dto.currentSurveyAssignOrg.orgPrimaryType == 1}">--%>
                    <%--                        <c:if test="${dto.reportCompletion == null}">--%>
                    <%--                            <button class="butList active" onclick="operate(${dto.currentSurveyAssignOrg.id},'org3',true)">初审通过</button>--%>
                    <%--                            <button class="butList active" onclick="operate(${dto.currentSurveyAssignOrg.id},'org4',false)">初审退回</button>--%>
                    <%--                        </c:if>--%>
                    <%--                        <c:if test="${dto.reportCompletion != null}">--%>
                    <%--                            <button class="butList active" onclick="operate(${dto.currentSurveyAssignOrg.id},'org5',false)">审核报告</button>--%>
                    <%--                        </c:if>--%>
                    <%--                    </c:if>--%>
                </c:if>
            </c:if>
        </c:if>
        <c:if test="${menuCode == 'dispatch-list'}">
            <c:if test="${dto.taskDispatchState != 2}">
                <button class="butList active" onclick="operate(${dto.id},'dispatch',false)">调度</button>
            </c:if>
            <c:if test="${dto.taskDispatchState == 2}">
                <button class="butList default">调度中</button>
            </c:if>
        </c:if>
        <c:if test="${menuCode == 'dispatch-opr-list'}">
            <c:if test="${dto.taskDispatchState == 2}">
                <button class="butList active" onclick="operate(${dto.id},'dispatchYes',true)">审核通过</button>
                <button class="butList active" onclick="operate(${dto.id},'dispatchNo',false)">退回</button>
            </c:if>
        </c:if>
        <c:if test="${menuCode == 'survey-list'}">
            <%--调查审核--%>
            <c:if test="${dto.surveyState == 22 || dto.surveyState == 30}">
                <c:if test="${dto.lfCommit}">
                    <c:if test="${dto.isSun == 0}">
                        <%--                        <button class="butList active" onclick="operate(${dto.id},'sign1',true)">标记阳性</button>--%>
                        <c:if test="${dto.price1IsCalc== 0}">
                            <button class="butList active" onclick="operate(${dto.id},1505,true)">标记基本费结算
                            </button>
                        </c:if>
                    </c:if>
                    <c:if test="${dto.isSun == 1}">
                        <%--                        <button class="butList active" onclick="operate(${dto.id},'resign1',true)">取消阳性</button>--%>
                    </c:if>
                    <%--                    <c:if test="${dto.isClassic == 0}">--%>
                    <%--                        <button class="butList active" onclick="operate(${dto.id},'classic',true)">标记经典案例</button>--%>
                    <%--                    </c:if>--%>
                    <%--                    <c:if test="${dto.isClassic == 1}">--%>
                    <%--                        <button class="butList active" onclick="operate(${dto.id},'reclassic',true)">取消经典案例</button>--%>
                    <%--                    </c:if>--%>
                    <%--<c:if test="${dto.deleteFlag == 1}">--%>
                    <%--<button class="butList active" onclick="operate(${dto.id},'delSurveyInfo',true)">删除案件</button>--%>
                    <%--</c:if>--%>
                </c:if>
                <c:if test="${dto.lfCommit}">
                    <%--                    <c:if test="${dto.surveyConsignor.orgAttr == 2}">--%>
                    <%--                        <button class="butList active" onclick="operate(${dto.id},'lefan-commit',true)">平台复审</button>--%>
                    <%--                    </c:if>--%>
                    <%--                    <c:if test="${dto.surveyConsignor.orgAttr == 1}">--%>
                    <%--                        <button class="butList active" onclick="operate(${dto.id},'survey-report-opr',false)">审核报告</button>--%>
                    <%--                    </c:if>--%>
                    <c:if test="${dto.surveyConsignor.orgAttr == 1}">
                        <button class="butList active" onclick="operate(${dto.id},'survey-report-opr',false)">审核报告
                        </button>
                    </c:if>
                </c:if>
            </c:if>
        </c:if>
        <c:if test="${menuCode == 'make-report-list'}">
            <c:if test="${dto.lfUpload}">
                <c:if test="${dto.sendReportState == 1}">
                    <c:if test="${dto.reportState == 0}">
                        <button class="butList active" onclick="operate(${dto.id},'primary',false)">上传主报告</button>
                    </c:if>
                    <c:if test="${dto.reportState == 1}">
                        <button class="butList active" onclick="operate(${dto.id},'primary',false)">更新主报告</button>
                    </c:if>
                    <button class="butList active" onclick="operate(${dto.id},'sendReportCommit',true)">提交</button>
                </c:if>
            </c:if>
        </c:if>
        <c:if test="${menuCode == 'entrust-list'}">
            <c:if test="${dto.surveyState == 24}">
                <button class="butList active" onclick="operate(${dto.id},1400,false)">审核通过</button>
                <button class="butList active" onclick="operate(${dto.id},1401,false)">退回</button>
            </c:if>
        </c:if>

        <%--代理保司审核--%>
        <c:if test="${menuCode2 == 'agent-entrust-list'}">
            <c:if test="${dto.surveyState == 24}">
                <button class="butList active" onclick="operate(${dto.id},1400,false)">审核通过</button>
                <button class="butList active" onclick="operate(${dto.id},1401,false)">退回</button>
            </c:if>
        </c:if>

        <c:if test="${menuCode == 'all-list'}">
            <%--风控人员有删除案件得权限--%>
            <c:if test="${dto.deleteFlag == 0 && dto.lfCommit && dto.entrustReportEndDate ==null}">
                <button class="butList active" onclick="operate(${dto.id},'delSurveyInfo',true)">删除案件</button>
                <%--<button class="butList active" onclick="operate(${dto.id},'updOrgEntrust',true)">更改保险公司</button>--%>
            </c:if>
            <c:if test="${dto.deleteFlag == 0}">
                <c:if test="${dto.surveyState == 28 && dto.lfSuper}">
                    <c:if test="${dto.isSendReport== 0}">
                        <button class="butList active" onclick="operate(${dto.id},1502,true)">寄送</button>
                    </c:if>
                    <c:if test="${dto.price1IsCalc== 0}">
                        <button class="butList active" onclick="operate(${dto.id},1505,true)">标记基本费结算</button>
                    </c:if>
                    <c:if test="${dto.price2IsCalc== 0}">
                        <button class="butList active" onclick="operate(${dto.id},1506,true)">标记减损奖励结算</button>
                    </c:if>
                    <button class="butList active" onclick="operate(${dto.id},1500,true)">发起结案</button>
                </c:if>
                <c:if test="${dto.surveyState == 28 && dto.lfSuper}">
                </c:if>
                <c:if test="${dto.entrustReportEndDate !=null}">
                    <c:if test="${dto.isPayEntrustFee== 0 && ((dto.isSun==0 && dto.price1IsCalc==1) || (dto.isSun==1 &&dto.price1IsCalc==1 &&dto.price2IsCalc==1))}">
                        <button class="butList active" onclick="operate(${dto.id},1501,false)">开票</button>
                    </c:if>
                    <c:if test="${dto.isPayEntrustFee== 1}">
                        <button class="butList default">开票中</button>
                    </c:if>
                    <c:if test="${dto.isPayEntrustFee== 2}">
                        <button class="butList default">已开票</button>
                    </c:if>
                </c:if>
            </c:if>
        </c:if>

        <c:if test="${(menuCode == 'my-list' || menuCode == 'all-list') && dto.surveyRiskCase.isShowTransfer && dto.lefanReportDate != null && dto.surveyAgentEntrust}">
            <button class="butList active" onclick="operate(${dto.id},'transfer',false)">
                发起${dto.surveyRiskCase.nextTransferName}调
            </button>
        </c:if>
        <c:if test="${(menuCode == 'guide-list')}">
            <c:if test="${dto.guideState == 0}">
                <button class="butList active" onclick="operate(${dto.id},'guide',false)">案件指导</button>
            </c:if>
            <c:if test="${dto.guideState == 1}">
                <button class="butList defuelt" onclick="operate(${dto.id},'guided',false)">已指导</button>
            </c:if>
        </c:if>
        <c:if test="${menuCode == 'help-review'}">
            <c:if test="${dto.currentSurveyAssignOrg.reviewTime == null}">
                <%--<button class="butList active" onclick="operate(${dto.id},1333,true)">审核通过</button>--%>
            </c:if>
            <%--<button class="butList active" onclick="operate(${dto.id},1334,false)">退回</button>--%>
        </c:if>
        <c:if test="${(menuCode == 'visit-list')}">
            <c:if test="${visitDto.visitState == 0 || visitDto.visitState == null}">
                <button class="butList active" onclick="operate(${dto.id},'visit',false)">调查回访</button>
            </c:if>
            <c:if test="${visitDto.visitState == 1}">
                <button class="butList defuelt" onclick="operate(${dto.id},'visit',false)">已回访</button>
            </c:if>
        </c:if>
        <c:if test="${menuCode == 'time-track-list' || menuCode=='survey-list' || menuCode=='assign-list' || menuCode=='agent-entrust-list'
        || menuCode=='org-review-list' || menuCode=='help-review' || menuCode=='all-list'}">
            <c:if test="${display}">
                <button class="butList active" onclick="caseClockDetails(${dto.id},'caseClockDetails')">
                    打卡足迹(${dto.punchClockCount})
                </button>
            </c:if>
        </c:if>

        <c:if test="${menuCode == 'assign-list' || menuCode == 'all-list'}">
            <button class="butList"
                    style="margin-left: auto;background-color: #F0F0F0;color: #2f332a;border: 1px solid #F0F0F0; width: 100px;"
                    onclick="operate(${dto.id},'org-remind',false,'first')">机构提醒
            </button>
        </c:if>

    </div>


    <c:if test="${menuCode == 'org-review-list'}"> <%--报告复核菜单--%>
        <c:if test="${dto.currentSurveyAssignOrg.orgOpinion !=null}">
            <div class="m-state">
                <div class="m-state__title">平台复审退回</div>
                <div class="m-state__reason">${dto.currentSurveyAssignOrg.orgOpinion}</div>
            </div>
        </c:if>
    </c:if>
    <c:if test="${menuCode == 'survey-list'}"> <%--调查审核菜单--%>
        <c:if test="${dto.opinion !=null}">
            <div class="m-state">
                <div class="m-state__title">保司审核退回</div>
                <div class="m-state__reason">退回原因：${dto.opinion}</div>
            </div>
        </c:if>
    </c:if>
    <c:if test="${menuCode == 'assign-list'}">
        <c:if test="${dto.orgReturn == 1}">
            <div class="m-state">
                <div class="m-state__title">机构主动退回</div>
                <c:forEach items="${dto.returnAssignOrgs}" var="item">
                    <div class="m-state__reason">退回机构：${item.surveyOrgName}</div>
                    <div class="m-state__reason">退回原因：${item.orgOpinion}</div>
                </c:forEach>
            </div>
        </c:if>
    </c:if>
    <c:if test="${menuCode == 'assign-org-list'}">
        <c:if test="${dto.currentSurveyAssignOrg.surveyReturn == 1}">
            <div class="m-state">
                <div class="m-state__title">调查员主动退回</div>
                <c:forEach items="${dto.returnInvestigatorCases}" var="item">
                    <div class="m-state__reason">退回调查员：${item.surveyUserName}</div>
                    <div class="m-state__reason">退回原因：${item.surveyRemark}</div>
                </c:forEach>
            </div>
        </c:if>
        <c:if test="${dto.currentSurveyAssignOrg.extensionState == 3}">
            <div class="m-state">
                <div class="m-state__title">延期申请驳回</div>
                <div class="m-state__reason">驳回原因：${dto.currentSurveyAssignOrg.extensionBackReason}</div>
            </div>
        </c:if>
    </c:if>
    <div class="main-boy">
        <div>
            <c:if test="${menuCode == 'dispatch-opr-list' && dto.taskDispatchState == 2}">
                <div class="table-title">调度信息</div>
                <table class="table" border="1" cellpadding="0" cellspacing="0" style="text-align: left;">
                    <tr>
                        <td>申请调度任务</td>
                        <td colspan="8" width="80%"><span><strong>${dto.other2}</strong></span></td>
                    </tr>
                </table>
            </c:if>
            <form id="editForm" role="form" action="${ctx}/survey/case/operate" method="post">
                <div class="info">
                    <div class="info-title">委托信息</div>
                    <div class="info-btns">
                        <div class="info-btn" onclick="operate(${dto.id},'files',false)">查看材料(${dto.fileSize})</div>
                        <div class="info-btn" onclick="operate(${dto.id},'progress',false)">查看进度</div>
                        <c:if test="${menuCode == 'all-list' || menuCode == 'survey-list'}">
                            <div class="info-btn" onclick="operate(${dto.id},'workflow',false)">查看时效</div>
                        </c:if>
                    </div>
                    <div class="info-content">
                        <table class="table-1 new-bas-info" border="0" cellpadding="0" cellspacing="0">
                            <tbody>
                            <tr>
                                <td>案件编号：
                                    <div id="div_survey_case_no_1">${dto.surveyRiskCase.surveyCaseNo}
                                        <c:if test="${menuCode == 'all-list' || menuCode == 'survey-list' || maxRole}">
                                            <a onclick="cliUpdPrice(17)"><img height="25px" width="25px"
                                                                              src="${ctx}/img/pen.png"></a>
                                            <div class="record" data-attr="survey_risk_case_survey_case_no"><span
                                                    class="icon-history"></span><span class="num"></span></div>
                                        </c:if>
                                    </div>
                                    <div id="div_survey_case_no_2" style="display: none">
                                        <input type="text" class="input-2" style="width:200px" name="surveyCaseNo"
                                               value="${dto.surveyRiskCase.surveyCaseNo}"/><br/>
                                        更改备注：<textarea style="width: 225px;height: 70px;"
                                                           name="surveyCaseNoRemark"></textarea>
                                        <input type="submit" value="确定" onclick="oprOK(${dto.id},'1703')" class="btn"
                                               style="display: inline-flex"/>
                                    </div>
                                </td>
                                <td>调查编号：${dto.surveyRiskCase.surveyNo}</td>
                                <td>委托人机构：
                                    <div id="div_entrust_org_1">
                                        ${dto.surveyRiskCase.entrustOrgName}
                                        <c:if test="${(menuCode == 'all-list'|| menuCode == 'assign-list' || menuCode == 'survey-list') && !dto.openBill || maxRole}">
                                            <a onclick="cliUpdPrice(10)"><img height="25px" width="25px"
                                                                              src="${ctx}/img/pen.png"></a>
                                        </c:if>
                                    </div>
                                    <div id="div_entrust_org_2" style="display: none">
                                        <select class="singleSelect form-control" style="width: 300px;"
                                                name="entrustOrg" id="entrustOrg"></select>
                                        <input type="submit" value="确定" onclick="oprOK(${dto.id},'updEntrustOrg')"
                                               class="btn" style="display: inline-flex"/>
                                    </div>
                                </td>
                                <td>委托人：${dto.surveyRiskCase.entrustUserName}
                                    <c:if test="${(menuCode == 'all-list'|| menuCode == 'assign-list' || menuCode == 'survey-list') && dto.entrustReportEndDate == null || maxRole}">
                                        <a onclick="cliUpdPrice(13)"><img height="25px" width="25px"
                                                                          src="${ctx}/img/pen.png"></a>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>被调查人：
                                    <div id="div_survey_person_1">${dto.surveyRiskCase.surveyPerson}
                                        <c:if test="${dto.surveyRiskCase.transferType != null}"><span
                                                style="color: red">(${dto.surveyRiskCase.transferTypeName})</span></c:if>
                                        <c:if test="${(menuCode == 'all-list' || menuCode == 'survey-list') && dto.surveyPhase != 3 || maxRole}">
                                            <a onclick="cliUpdPrice(9)"><img height="25px" width="25px"
                                                                             src="${ctx}/img/pen.png"></a>
                                            <div class="record" data-attr="survey_risk_case_survey_person"><span
                                                    class="icon-history"></span><span class="num"></span></div>
                                        </c:if>
                                    </div>
                                    <div id="div_survey_person_2" style="display: none">
                                        <input type="text" class="input-2" name="surveyPerson"
                                               value="${dto.surveyRiskCase.surveyPerson}"/><br/>
                                        联系方式：<input type="text" class="input-2" name="surveryPersonTel"
                                                        value="${dto.surveyRiskCase.surveryPersonTel}"/>
                                        更改备注：<textarea style="width: 225px;height: 70px;"
                                                           name="surveyPersonRemark"></textarea>
                                        <input type="submit" value="确定" onclick="oprOK(${dto.id},'1008')" class="btn"
                                               style="display: inline-flex"/>
                                    </div>
                                </td>
                                <td>联系号码：
                                    <div id="div_survey_person_tel_1">${dto.surveyRiskCase.surveryPersonTel}
                                        <a onclick="cliUpdPrice('tel')"><img height="25px" width="25px"
                                                                             src="${ctx}/img/pen.png"></a>
                                        <div class="record" data-attr="survey_risk_case_survey_person_tel"><span
                                                class="icon-history"></span><span class="num"></span></div>
                                    </div>
                                    <div id="div_survey_person_tel_2" style="display: none">
                                        <input type="text" class="input-2" name="surveryPersonTel2"
                                               value="${dto.surveyRiskCase.surveryPersonTel}"/>
                                        <input type="submit" value="确定" onclick="oprOK(${dto.id},'1119')" class="btn"
                                               style="display: inline-flex"/>
                                    </div>

                                </td>
                                <td>性别：<c:if test="${dto.surveyRiskCase.sex ==1}">男</c:if>
                                    <c:if test="${dto.surveyRiskCase.sex ==2}">女</c:if>
                                </td>
                                <td>年龄：${dto.surveyRiskCase.age}</td>
                            </tr>

                            <!-- 委托人机构编号为 52时展示-->
                            <c:if test="${dto.surveyRiskCase.entrustOrgId == 52}">
                                <tr>
                                    <td>
                                        合作公司：
                                        <div id="div_survey_cooperative_company_1">${dto.surveyRiskCase.cooperativeCompany}
                                            <a onclick="cliUpdPrice('cooperativeCompany')">
                                                <img height="25px"
                                                     width="25px"
                                                     src="${ctx}/img/pen.png">
                                            </a>
                                            <div class="record" data-attr="survey_risk_case_cooperative_company"><span
                                                    class="icon-history"></span><span class="num"></span></div>
                                        </div>
                                        <div id="div_survey_cooperative_company_2" style="display: none">
                                            <input type="text" class="input-2" name="cooperativeCompany"
                                                   value="${dto.surveyRiskCase.cooperativeCompany}"/>
                                            <input type="submit" value="确定"
                                                   onclick="oprOK(${dto.id},'cooperativeCompany')"
                                                   class="btn"
                                                   style="display: inline-flex"/>
                                        </div>
                                    </td>
                                    <td>
                                        分公司：
                                        <div id="div_survey_subsidiary_company_1">${dto.surveyRiskCase.subsidiaryCompany}
                                            <a onclick="cliUpdPrice('subsidiaryCompany')">
                                                <img height="25px"
                                                     width="25px"
                                                     src="${ctx}/img/pen.png">
                                            </a>
                                            <div class="record"
                                                 data-attr="survey_risk_case_subsidiary_company"><span
                                                    class="icon-history"></span><span class="num"></span></div>
                                        </div>
                                        <div id="div_survey_subsidiary_company_2" style="display: none">
                                            <input type="text" class="input-2" name="subsidiaryCompany"
                                                   value="${dto.surveyRiskCase.subsidiaryCompany}"/>
                                            <input type="submit" value="确定"
                                                   onclick="oprOK(${dto.id},'subsidiaryCompany')"
                                                   class="btn"
                                                   style="display: inline-flex"/>
                                        </div>
                                    </td>
                                    <td>
                                        健康险公司：
                                        <div id="div_survey_health_insurance_company_1">${dto.surveyRiskCase.healthInsuranceCompany}
                                            <a onclick="cliUpdPrice('healthInsuranceCompany')">
                                                <img height="25px"
                                                     width="25px"
                                                     src="${ctx}/img/pen.png">
                                            </a>
                                            <div class="record"
                                                 data-attr="survey_risk_case_health_insurance_company"><span
                                                    class="icon-history"></span><span class="num"></span></div>
                                        </div>
                                        <div id="div_survey_health_insurance_company_2" style="display: none">
                                            <input type="text" class="input-2" name="healthInsuranceCompany"
                                                   value="${dto.surveyRiskCase.healthInsuranceCompany}"/>
                                            <input type="submit" value="确定"
                                                   onclick="oprOK(${dto.id},'healthInsuranceCompany')"
                                                   class="btn"
                                                   style="display: inline-flex"/>
                                        </div>
                                    </td>
                                    <td>
                                        任务号：
                                        <div id="div_survey_task_number_1">${dto.surveyRiskCase.taskNumber}
                                            <a onclick="cliUpdPrice('taskNumber')">
                                                <img height="25px"
                                                     width="25px"
                                                     src="${ctx}/img/pen.png">
                                            </a>
                                            <div class="record" data-attr="survey_risk_case_task_number"><span
                                                    class="icon-history"></span><span class="num"></span></div>
                                        </div>
                                        <div id="div_survey_task_number_2" style="display: none">
                                            <input type="text" class="input-2" name="taskNumber"
                                                   value="${dto.surveyRiskCase.taskNumber}"/>
                                            <input type="submit" value="确定"
                                                   onclick="oprOK(${dto.id},'taskNumber')"
                                                   class="btn"
                                                   style="display: inline-flex"/>
                                        </div>
                                    </td>
                                </tr>
                            </c:if>

                            <!-- 67 时展示-->
                            <c:if test="${dto.surveyRiskCase.entrustOrgId == 67}">
                                <tr>
                                    <td>
                                        委托类型：<c:if test="${dto.surveyRiskCase.delegationMode ==1}">系统委托</c:if>
                                        <c:if test="${dto.surveyRiskCase.delegationMode ==2}">邮件提调</c:if>
                                    </td>
                                    <td>
                                    </td>
                                    <td>
                                    </td>
                                    <td>
                                    </td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>
                                    <c:if test="${dto.surveyRiskCase.idType == 1}">
                                        身份证号：
                                    </c:if>
                                    <c:if test="${dto.surveyRiskCase.idType == 3}">
                                        护照号码：
                                    </c:if>
                                    <c:if test="${dto.surveyRiskCase.idType == 4}">
                                        其它证件号码：
                                    </c:if>

                                    <div id="div_survey_id_number_1">${dto.surveyRiskCase.idNumber}
                                        <c:if test="${(menuCode == 'all-list' || menuCode == 'survey-list') && dto.surveyPhase != 3 || maxRole}">
                                            <a onclick="cliUpdPrice(19)"><img height="25px" width="25px"
                                                                              src="${ctx}/img/pen.png"></a>
                                            <div class="record" data-attr="survey_risk_case_id_number"><span
                                                    class="icon-history"></span><span class="num"></span></div>
                                        </c:if>
                                    </div>
                                    <div id="div_survey_id_number_2" style="display: none">
                                        <input type="text" class="input-2" style="width: 180px;" name="idNumber"
                                               id="idNumber" value="${dto.surveyRiskCase.idNumber}"/>
                                        <input type="submit" value="确定" onclick="oprOK(${dto.id},'1009')" class="btn"
                                               style="display: inline-flex"/>
                                    </div>

                                </td>
                                <td>
                                    <c:if test="${menuCode == 'org-review-list' || menuCode == 'assign-org-list'}">
                                        分派机构日期： <fmt:formatDate value="${dto.currentSurveyAssignOrg.createTime}"
                                                                      pattern="yyyy-MM-dd"/>
                                    </c:if>
                                    <c:if test="${menuCode != 'org-review-list' && menuCode != 'assign-org-list'}">
                                        委托日期：<fmt:formatDate value="${dto.surveyRiskCase.entrustTime}"
                                                                 pattern="yyyy-MM-dd"/>
                                        <input type="hidden" id="timeEntrustment"
                                               value="${dto.surveyRiskCase.entrustTime}" pattern="yyyy-MM-dd"/>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${menuCode == 'org-review-list' || menuCode == 'assign-org-list'}">
                                        机构截止日期： <fmt:formatDate value="${dto.currentSurveyAssignOrg.orgEndTime}"
                                                                      pattern="yyyy-MM-dd"/>
                                    </c:if>
                                    <c:if test="${menuCode != 'org-review-list' && menuCode != 'assign-org-list'}">
                                        案件截止日期：
                                        <div id="div_end_time_1">
                                            <fmt:formatDate value="${dto.endTime}" pattern="yyyy-MM-dd"/>
                                            <c:if test="${(menuCode == 'all-list' || menuCode == 'survey-list' ||menuCode == 'assign-list') && dto.surveyPhase != 3 || maxRole}">
                                                <a onclick="cliUpdPrice(8)"><img height="25px" width="25px"
                                                                                 src="${ctx}/img/pen.png"></a>
                                                <div class="record" data-attr="survey_risk_case_info_end_time"><span
                                                        class="icon-history"></span><span class="num"></span></div>
                                            </c:if>
                                        </div>
                                        <div id="div_end_time_2" style="display: none">
                                            <input name="endTime" type="text" class="form-control"
                                                   value="<fmt:formatDate value="${dto.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" required
                                                   readonly style="cursor: auto">
                                            更改备注：<textarea style="width: 225px;height: 70px;"
                                                               name="endTimeRemark"></textarea>
                                            <input type="submit" value="确定" onclick="oprOK(${dto.id},'1001')"
                                                   class="btn" style="display: inline-flex"/>
                                        </div>

                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${menuCode != 'org-review-list' && menuCode != 'assign-org-list'}">
                                        创建时间：<fmt:formatDate value="${dto.createTime}" pattern="yyyy-MM-dd"/>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>领域：${dto.surveyBusName}</td>
                                <td>
                                    <c:if test="${menuCode == 'org-review-list' || menuCode == 'assign-org-list'}">
                                        业务类型：${dto.currentSurveyAssignOrg.servicesName}
                                    </c:if>
                                    <c:if test="${menuCode != 'org-review-list' && menuCode != 'assign-org-list'}">
                                        业务类型：
                                        <c:if test="${dto.subServiceId ==null}">${dto.servicesName}</c:if>
                                        <c:if test="${dto.subServiceId !=null}">
                                            <c:if test="${dto.subServiceId ==2}">核对调阅类</c:if>
                                            <c:if test="${dto.subServiceId ==3}">一般检索类</c:if>
                                            <c:if test="${dto.subServiceId ==4}">特殊检索类</c:if>
                                            <c:if test="${dto.subServiceId ==5}">疑难侦察类</c:if>
                                            <c:if test="${dto.subServiceId ==6}">攻坚侦察类</c:if>
                                        </c:if>
                                        <c:if test="${menuCode == 'all-list'}"><a onclick="cliUpdPrice(11)"><img
                                                height="25px" width="25px" src="${ctx}/img/pen.png"></a></c:if>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${dto.surveyRiskCase.modelId == 1 || dto.surveyRiskCase.modelId == 2 || dto.surveyRiskCase.modelId == 6 || dto.surveyRiskCase.modelId == 7}">
                                        理赔编号：
                                    </c:if>
                                    <c:if test="${dto.surveyRiskCase.modelId == 3 || dto.surveyRiskCase.modelId == 4}">
                                        保险合同编号：
                                    </c:if>
                                    <c:if test="${dto.surveyRiskCase.modelId == 1 || dto.surveyRiskCase.modelId == 2 || dto.surveyRiskCase.modelId == 6 || dto.surveyRiskCase.modelId == 7}">
                                        ${dto.surveyRiskCase.claimsNo}
                                    </c:if>
                                    <c:if test="${dto.surveyRiskCase.modelId == 3 || dto.surveyRiskCase.modelId == 4}">
                                        ${dto.surveyRiskCase.policyNo}
                                    </c:if>
                                </td>
                                <td>理赔金额：${dto.surveyRiskCase.claimsMoney}</td>
                            </tr>

                            <c:if test="${dto.surveyRiskCase.modelId == 1 || dto.surveyRiskCase.modelId == 2 || dto.surveyRiskCase.modelId == 4 || dto.surveyRiskCase.modelId == 6 || dto.surveyRiskCase.modelId == 7}">
                                <tr>
                                    <td>投保日期：
                                        <div id="div_insure_time_1">
                                            <fmt:formatDate value="${dto.surveyRiskCase.insureTime}"
                                                            pattern="yyyy-MM-dd"/>
                                            <c:if test="${(menuCode == 'assign-list') || menuCode == 'org-review-list' || ((menuCode == 'all-list' || menuCode == 'survey-list') && dto.surveyPhase != 3) || maxRole}">
                                                <a onclick="cliUpdPrice('updInsureTime')"><img height="25px"
                                                                                               width="25px"
                                                                                               src="${ctx}/img/pen.png"></a>
                                                <div class="record" data-attr="survey_risk_case_insure_time"><span
                                                        class="icon-history"></span><span class="num"></span></div>
                                            </c:if>
                                        </div>
                                        <div id="div_insure_time_2" style="display: none">
                                            <input name="insureTime" type="text" class="form-control"
                                                   value="<fmt:formatDate value="${dto.surveyRiskCase.insureTime}" pattern="yyyy-MM-dd"/>"
                                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" required readonly
                                                   style="cursor: auto">
                                            更改备注：<textarea style="width: 225px;height: 70px;"
                                                               name="insureTimeRemark"></textarea>
                                            <input type="submit" value="确定" onclick="oprOK(${dto.id},'updInsureTime')"
                                                   class="btn" style="display: inline-flex"/>
                                        </div>
                                    </td>
                                    <td>
                                        调查号：
                                        <div id="div_hz_contact_name_1">${dto.surveyRiskCase.hzContactName}
                                            <c:if test="${menuCode == 'all-list' && dto.surveyPhase != 3 || maxRole || menuCode == 'org-review-list' || menuCode == 'survey-list'}">
                                                <a onclick="cliUpdPrice('updHzContactName')"><img height="25px"
                                                                                                  width="25px"
                                                                                                  src="${ctx}/img/pen.png"></a>
                                                <div class="record" data-attr="survey_risk_case_hz_contact_name"><span
                                                        class="icon-history"></span><span class="num"></span></div>
                                            </c:if>
                                        </div>
                                        <div id="div_hz_contact_name_2" style="display: none">
                                            <input type="text" class="input-2" style="width: 180px;"
                                                   name="hzContactName" value="${dto.surveyRiskCase.hzContactName}"/>
                                            <input type="submit" value="确定"
                                                   onclick="oprOK(${dto.id},'updHzContactName')" class="btn"
                                                   style="display: inline-flex"/>
                                        </div>


                                    </td>
                                    <td>
                                        派案人：
                                        <div id="div_hz_contact_tel_1">${dto.surveyRiskCase.hzContactTel}
                                            <c:if test="${menuCode == 'all-list' && dto.surveyPhase != 3 || maxRole || menuCode == 'org-review-list' || menuCode == 'survey-list'}">
                                                <a onclick="cliUpdPrice('updHzContactTel')"><img height="25px"
                                                                                                  width="25px"
                                                                                                  src="${ctx}/img/pen.png"></a>
                                                <div class="record" data-attr="survey_risk_case_hz_contact_tel"><span
                                                        class="icon-history"></span><span class="num"></span></div>
                                            </c:if>
                                        </div>
                                        <div id="div_hz_contact_tel_2" style="display: none">
                                            <input type="text" class="input-2" style="width: 180px;"
                                                   name="hzContactTel" value="${dto.surveyRiskCase.hzContactTel}"/>
                                            <input type="submit" value="确定"
                                                   onclick="oprOK(${dto.id},'updHzContactTel')" class="btn"
                                                   style="display: inline-flex"/>
                                        </div>


                                    </td>
                                    <td>
                                        <c:if test="${showNwPage}">
                                            众安ID：
                                            <div id="div_handle_id_1">
                                                    ${dto.handleId}
                                                <c:if test="${menuCode == 'all-list'}">
                                                    <a onclick="cliUpdPrice('updHandleId')"><img height="25px"
                                                                                                 width="25px"
                                                                                                 src="${ctx}/img/pen.png"></a>
                                                    <div class="record" data-attr="survey_risk_case_info_handle_id">
                                                        <span class="icon-history"></span><span class="num"></span>
                                                    </div>
                                                </c:if>
                                            </div>
                                            <div id="div_handle_id_2" style="display: none">
                                                <input type="number" name="handleId" value="${dto.handleId}"
                                                       class="form-control"/>
                                                <input type="submit" value="确定"
                                                       onclick="oprOK(${dto.id},'updHandleId')" class="btn"
                                                       style="display: inline-flex"/>
                                            </div>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${dto.surveyRiskCase.modelId == 1 || dto.surveyRiskCase.modelId == 2 || dto.surveyRiskCase.modelId == 6 || dto.surveyRiskCase.modelId == 7}">
                                <tr>
                                    <td>出险日期：
                                        <div id="div_danger_time_1">
                                            <fmt:formatDate value="${dto.surveyRiskCase.dangerTime}"
                                                            pattern="yyyy-MM-dd"/>
                                            <c:if test="${(menuCode == 'assign-list') || menuCode == 'survey-list' || menuCode == 'org-review-list' || (menuCode == 'all-list'  && dto.surveyPhase != 3) || maxRole}">
                                                <a onclick="cliUpdPrice('updDangerTime')"><img height="25px"
                                                                                               width="25px"
                                                                                               src="${ctx}/img/pen.png"></a>
                                                <div class="record" data-attr="survey_risk_case_danger_time"><span
                                                        class="icon-history"></span><span class="num"></span></div>
                                            </c:if>
                                        </div>
                                        <div id="div_danger_time_2" style="display: none">
                                            <input name="dangerTime" type="text" class="form-control"
                                                   value="<fmt:formatDate value="${dto.surveyRiskCase.dangerTime}" pattern="yyyy-MM-dd"/>"
                                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" required readonly
                                                   style="cursor: auto">
                                            更改备注：<textarea style="width: 225px;height: 70px;"
                                                               name="dangerTimeRemark"></textarea>
                                            <input type="submit" value="确定" onclick="oprOK(${dto.id},'updDangerTime')"
                                                   class="btn" style="display: inline-flex"/>
                                        </div>
                                    </td>
                                    <td>
                                        出险地点：
                                        <div id="div_danger_address_1">${dto.surveyRiskCase.dangerAddress}
                                            <c:if test="${menuCode == 'all-list' && dto.surveyPhase != 3 || maxRole || menuCode == 'org-review-list' || menuCode == 'survey-list'}">
                                                <a onclick="cliUpdPrice('updDangerAddress')"><img height="25px"
                                                                                                  width="25px"
                                                                                                  src="${ctx}/img/pen.png"></a>
                                                <div class="record" data-attr="survey_risk_case_danger_address"><span
                                                        class="icon-history"></span><span class="num"></span></div>
                                            </c:if>
                                        </div>
                                        <div id="div_danger_address_2" style="display: none">
                                            <input type="text" class="input-2" style="width: 180px;"
                                                   name="dangerAddress" value="${dto.surveyRiskCase.dangerAddress}"/>
                                            <input type="submit" value="确定"
                                                   onclick="oprOK(${dto.id},'updDangerAddress')" class="btn"
                                                   style="display: inline-flex"/>
                                        </div>
                                    </td>
                                    <td>保险种类：
                                        <div id="div_insure_name_1">${dto.surveyRiskCase.insureName}
                                            <c:if test="${menuCode == 'all-list' && dto.surveyPhase != 3 || maxRole || menuCode == 'org-review-list' || menuCode == 'survey-list'}">
                                                <a onclick="cliUpdPrice('updInsureName')"><img height="25px"
                                                                                               width="25px"
                                                                                               src="${ctx}/img/pen.png"></a>
                                                <div class="record" data-attr="survey_risk_case_insure_name"><span
                                                        class="icon-history"></span><span class="num"></span></div>
                                            </c:if>
                                        </div>
                                        <div id="div_insure_name_2" style="display: none">
                                            <input type="text" class="input-2" style="width: 180px;" name="insureName"
                                                   value="${dto.surveyRiskCase.insureName}"/>
                                            <input type="submit" value="确定" onclick="oprOK(${dto.id},'updInsureName')"
                                                   class="btn" style="display: inline-flex"/>
                                        </div>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${dto.surveyRiskCase.modelId == 3}">
                                <tr>
                                    <td>保单生效日期：<fmt:formatDate value="${dto.surveyRiskCase.insureTakeTime}"
                                                                     pattern="yyyy-MM-dd"/></td>
                                    <td></td>
                                    <td>出险日期：<fmt:formatDate value="${dto.surveyRiskCase.dangerTime}"
                                                                 pattern="yyyy-MM-dd"/>
                                        <div id="div_danger_time_1">
                                            <fmt:formatDate value="${dto.surveyRiskCase.dangerTime}"
                                                            pattern="yyyy-MM-dd"/>
                                            <c:if test="${(menuCode == 'assign-list') || menuCode == 'survey-list' || menuCode == 'org-review-list' || (menuCode == 'all-list'  && dto.surveyPhase != 3) || maxRole}">
                                                <a onclick="cliUpdPrice('updDangerTime')"><img height="25px"
                                                                                               width="25px"
                                                                                               src="${ctx}/img/pen.png"></a>
                                                <div class="record" data-attr="survey_risk_case_danger_time"><span
                                                        class="icon-history"></span><span class="num"></span></div>
                                            </c:if>
                                        </div>
                                        <div id="div_danger_time_2" style="display: none">
                                            <input name="dangerTime" type="text" class="form-control"
                                                   value="<fmt:formatDate value="${dto.surveyRiskCase.dangerTime}" pattern="yyyy-MM-dd"/>"
                                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" required readonly
                                                   style="cursor: auto">
                                            更改备注：<textarea style="width: 225px;height: 70px;"
                                                               name="dangerTimeRemark"></textarea>
                                            <input type="submit" value="确定" onclick="oprOK(${dto.id},'updDangerTime')"
                                                   class="btn" style="display: inline-flex"/>
                                        </div>
                                    </td>
                                    <td></td>
                                </tr>
                            </c:if>
                            <c:if test="${dto.surveyRiskCase.modelId == 5}">
                                <tr>
                                    <td>联系人：${dto.surveyRiskCase.hzContactName}</td>
                                    <td>联系人电话：${dto.surveyRiskCase.hzContactTel}</td>
                                    <td>互助产品：
                                        <c:if test="${dto.surveyRiskCase.hzProduct == 1}">重大疾病</c:if>
                                        <c:if test="${dto.surveyRiskCase.hzProduct == 2}">老年防癌</c:if>
                                        <c:if test="${dto.surveyRiskCase.hzProduct == 3}">中青年互助计划</c:if>
                                        <c:if test="${dto.surveyRiskCase.hzProduct == 4}">老年互助计划</c:if>
                                        <c:if test="${dto.surveyRiskCase.hzProduct == 5}">百年终生互助计划</c:if>
                                        <c:if test="${dto.surveyRiskCase.hzProduct == 6}">少儿互助计划</c:if>
                                    </td>
                                    <td>
                                        互助案件编号：${dto.surveyRiskCase.claimsNo}
                                    </td>
                                </tr>
                                <tr>
                                    <td>互助金额：${dto.surveyRiskCase.claimsMoney}</td>
                                    <td>加入时间：<fmt:formatDate value="${dto.surveyRiskCase.insureTime}"
                                                                 pattern="yyyy-MM-dd"/></td>
                                    <td>等待期截止日期：<fmt:formatDate value="${dto.surveyRiskCase.hzWaitEndTime}"
                                                                       pattern="yyyy-MM-dd"/></td>
                                    <td>出险日期：<fmt:formatDate value="${dto.surveyRiskCase.dangerTime}"
                                                                 pattern="yyyy-MM-dd"/>
                                        <div id="div_danger_time_1">
                                            <fmt:formatDate value="${dto.surveyRiskCase.dangerTime}"
                                                            pattern="yyyy-MM-dd"/>
                                            <c:if test="${(menuCode == 'assign-list') || menuCode == 'survey-list' || menuCode == 'org-review-list' || (menuCode == 'all-list'  && dto.surveyPhase != 3) || maxRole}">
                                                <a onclick="cliUpdPrice('updDangerTime')"><img height="25px"
                                                                                               width="25px"
                                                                                               src="${ctx}/img/pen.png"></a>
                                                <div class="record" data-attr="survey_risk_case_danger_time"><span
                                                        class="icon-history"></span><span class="num"></span></div>
                                            </c:if>
                                        </div>
                                        <div id="div_danger_time_2" style="display: none">
                                            <input name="dangerTime" type="text" class="form-control"
                                                   value="<fmt:formatDate value="${dto.surveyRiskCase.dangerTime}" pattern="yyyy-MM-dd"/>"
                                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" required readonly
                                                   style="cursor: auto">
                                            更改备注：<textarea style="width: 225px;height: 70px;"
                                                               name="dangerTimeRemark"></textarea>
                                            <input type="submit" value="确定" onclick="oprOK(${dto.id},'updDangerTime')"
                                                   class="btn" style="display: inline-flex"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>常住地点：${dto.surveyRiskCase.hzLiveAddress}</td>
                                    <c:if test="${!dto.help}">
                                        <td>出险地点：
                                            <div id="div_danger_address_1">${dto.surveyRiskCase.dangerAddress}
                                                <c:if test="${menuCode == 'all-list' && dto.surveyPhase != 3 || maxRole || menuCode == 'org-review-list' || menuCode == 'survey-list'}">
                                                    <a onclick="cliUpdPrice('updDangerAddress')"><img height="25px"
                                                                                                      width="25px"
                                                                                                      src="${ctx}/img/pen.png"></a>
                                                    <div class="record" data-attr="survey_risk_case_danger_address">
                                                        <span class="icon-history"></span><span class="num"></span>
                                                    </div>
                                                </c:if>
                                            </div>
                                            <div id="div_danger_address_2" style="display: none">
                                                <input type="text" class="input-2" style="width: 180px;"
                                                       name="dangerAddress"
                                                       value="${dto.surveyRiskCase.dangerAddress}"/>
                                                <input type="submit" value="确定"
                                                       onclick="oprOK(${dto.id},'updDangerAddress')" class="btn"
                                                       style="display: inline-flex"/>
                                            </div>
                                        </td>
                                    </c:if>
                                    <td>确诊疾病：${dto.surveyRiskCase.hzConfirmDisease}</td>
                                    <td>首次跟踪信息:${dto.surveyRiskCase.hzFollowInfo}</td>
                                </tr>
                            </c:if>
                            <tr>
                                <td colspan="3">任务类型：
                                    <div id="div_task_type_1" style="width: 90%;">
                                        <c:forEach items="${dto.surveyTaskTypes}" var="item">
                                            <span style="background-color: ${item.taskColor};color: #fff;padding: 5px;margin:6px 0;margin-right: 5px;display: inline-block;">${item.taskName}&nbsp;</span>
                                        </c:forEach>
                                        <c:if test="${menuCode == 'assign-list' || menuCode == 'survey-list' || menuCode == 'all-list' || maxRole}">
                                            <%--<a onclick="cliUpdPrice(14)"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a>--%>
                                            <a href="javascript:void(0)"
                                               onclick="updTaskType(${dto.id},'','updateCaseTaskType','updateTaskType')">
                                                <img height="25px" width="25px" src="${ctx}/img/pen.png">
                                            </a>
                                        </c:if>
                                    </div>
                                    <div id="div_task_type_2" style="display: none">
                                        <div id="div_task_type_2_task"></div>
                                        <input type="submit" value="确定" onclick="oprOK(${dto.id},'updateTaskType')"
                                               class="btn" style="display: inline-flex"/>
                                        <input type="hidden" name="taskIds" id="taskIds"/>
                                    </div>
                                </td>
                                <td>案源机构：<c:if test="${dto.sourceSupportType == 1}">市场一部（郑哲）</c:if>
                                    <c:if test="${dto.sourceSupportType == 2}">市场二部（曹刘强）</c:if>
                                    <c:if test="${dto.sourceSupportType == 3}">互助</c:if>
                                    <c:if test="${dto.sourceSupportType == 4}">正言金融（郑哲）</c:if>
                                    <c:if test="${dto.sourceSupportType == 5}">市场三部（韩正栋）</c:if>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <div class="td-div__flex">
                                        <div class="td-label" style="width: 70px;">报案信息：</div>
                                        <div id="div_survey_info_1" style="width: 95%">
                                            <div class="td__bold" style="width: 100%;">
                                                <pre style="font-weight: bold;background-color:rgba(0, 0, 0, 0);border:dashed 0px #fff; font-size: 14px;white-space: pre-wrap; word-wrap: break-word;padding: 0px;">${dto.surveyInfo}</pre>
                                            </div>
                                            <c:if test="${menuCode == 'assign-list' || menuCode == 'org-review-list' || menuCode == 'survey-list' || menuCode == 'all-list' || maxRole}">
                                                <a onclick="cliUpdPrice(15)"><img height="25px" width="25px"
                                                                                  src="${ctx}/img/pen.png"></a>
                                                <div class="record" data-attr="survey_risk_case_info_survey_info"><span
                                                        class="icon-history"></span><span class="num"></span></div>
                                            </c:if>
                                        </div>
                                        <div id="div_survey_info_2" style="display: none;width: 95%">
                                            <textarea style="width: 100%;" name="surveyInfo" rows="8" cols="8"
                                                      class="form-control"
                                                      placeholder="请输入报案信息">${dto.surveyInfo}</textarea>
                                            更改备注：<textarea style="width: 100%;" name="surveyInfoRemark" rows="8"
                                                               cols="8" class="form-control"
                                                               placeholder="备注"></textarea>
                                            <input type="submit" value="确定" onclick="oprOK(${dto.id},'1701')"
                                                   class="btn" style="display: inline-flex"/>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <div class="td-div__flex">
                                        <%--                                        <c:if test="${menuCode == 'org-review-list' || menuCode == 'assign-org-list'}">--%>
                                        <%--                                            <div class="td-label" style="width: 70px;">调查要求：</div><div class="td__bold" style="width: 100%;"><pre style="font-weight: bold;background-color:rgba(0, 0, 0, 0);border:dashed 0px #fff; font-size: 14px;white-space: pre-wrap; word-wrap: break-word;padding: 0px;" >${dto.currentSurveyAssignOrg.orgTaskRemark}</pre></div>--%>
                                        <%--                                        </c:if>--%>
                                        <%--                                        <c:if test="${menuCode != 'org-review-list' && menuCode != 'assign-org-list'}">--%>
                                        <div class="td-label" style="width: 70px;">调查要求：</div>
                                        <div id="div_survey_item_1" style="width: 95%">
                                            <div class="td__bold" style="width: 100%;">
                                                <pre style="font-weight: bold;background-color:rgba(0, 0, 0, 0);border:dashed 0px #fff; font-size: 14px;white-space: pre-wrap; word-wrap: break-word;padding: 0px;">${dto.surveyItem}</pre>
                                            </div>
                                            <c:if test="${menuCode == 'assign-list' || menuCode == 'survey-list' || menuCode == 'org-review-list' || menuCode == 'all-list' || maxRole}">
                                                <a onclick="cliUpdPrice(16)"><img height="25px" width="25px"
                                                                                  src="${ctx}/img/pen.png"></a>
                                                <div class="record" data-attr="survey_risk_case_info_survey_item"><span
                                                        class="icon-history"></span><span class="num"></span></div>
                                            </c:if>
                                        </div>
                                        <div id="div_survey_item_2" style="display: none;width: 95%">
                                            <textarea style="width: 100%;" name="surveyItem" rows="8" cols="8"
                                                      class="form-control"
                                                      placeholder="请输入调查要求">${dto.surveyItem}</textarea>
                                            更改备注：<textarea style="width: 100%;" name="surveyItemRemark" rows="8"
                                                               cols="8" class="form-control"
                                                               placeholder="备注"></textarea>
                                            <input type="submit" value="确定" onclick="oprOK(${dto.id},'1702')"
                                                   class="btn" style="display: inline-flex"/>
                                        </div>
                                        <%--                                        </c:if>--%>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <c:if test="${dto.guide ==1 && dto.guideState == 1}">
                    <div style="display: none" class="info">
                        <div class="info-title">案件指导</div>
                        <div class="info-content">
                            <table class="table-1 new-bas-info" border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                <tr>
                                    <td colspan="4">
                                        <div class="td-div__flex">
                                            <div class="td-label" style="width: 100px; color:red">可能的阳性点：</div>
                                            <div class="td__bold"
                                                 style="width: 100%; color:red">${guideDto.maySun}</div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4">
                                        <div class="td-div__flex">
                                            <div class="td-label" style="width: 100px; color:red">重点调查方向：</div>
                                            <div class="td__bold"
                                                 style="width: 100%;  color:red">${guideDto.direction}</div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4">
                                        <div class="td-div__flex">
                                            <div class="td-label" style="width: 100px; color:red">注意事项：</div>
                                            <div class="td__bold" style="width: 100%;  color:red">${guideDto.note}</div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="1">
                                        <div class="td-div__flex">
                                            <div class="td-label" style="width: 100px; color:red">高度阳性：</div>
                                            <div class="td__bold" style="width: 100%; color:red">
                                                <c:if test="${guideDto.isSun==0 || guideDto.isSun==''}">否</c:if>
                                                <c:if test="${guideDto.isSun==1}">是</c:if>
                                            </div>
                                        </div>
                                    </td>
                                    <td colspan="1">
                                        <div class="td-div__flex">
                                            <div class="td-label" style="width: 100px; color:red">指导人：</div>
                                            <div class="td__bold"
                                                 style="width: 100%; color:red">${guideDto.guideByName}</div>
                                        </div>
                                    </td>
                                    <td colspan="2">
                                        <div class="td-div__flex">
                                            <div class="td-label" style="width: 70px; color:red">指导时间：</div>
                                            <div class="td__bold" style="width: 100%; color:red"><fmt:formatDate
                                                    value="${guideDto.guideTime}" pattern="yyyy-MM-dd"/></div>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>

                <div class="info">
                    <div class="info-title">状态信息</div>
                    <div class="info-content">
                        <table class="table-1 new-bas-info" border="0" cellpadding="0" cellspacing="0">
                            <tbody>
                            <c:if test="${menuCode == 'org-review-list'}">
                                <tr>
                                    <td>案件状态：<c:if
                                            test="${menuCode == 'org-review-list' && dto.currentSurveyAssignOrg.orgSurveyState != 4}">
                                        ${dto.currentSurveyAssignOrg.orgSurveyStateName}
                                    </c:if>
                                        <c:if test="${menuCode != 'org-review-list' || fromName!=null || dto.currentSurveyAssignOrg.orgSurveyState == 4}">
                                            ${dto.surveyStateName}
                                        </c:if>
                                    </td>
                                    <td>
                                        是否阳性：<c:if test="${dto.isSun == 0}">否</c:if>
                                        <c:if test="${dto.isSun == 1}"><span style="color: red;">是</span></c:if>
                                    </td>
                                    <td>
                                        <c:if test="${!dto.help}">
                                        平台复审人员：
                                        <div id="div_belong_user_1">
                                                ${dto.belongUserName}
                                            </c:if>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${menuCode != 'org-review-list'}">
                                <tr>
                                    <td>案件状态：<c:if
                                            test="${menuCode == 'org-review-list' && dto.currentSurveyAssignOrg.orgSurveyState != 4}">
                                        ${dto.currentSurveyAssignOrg.orgSurveyStateName}
                                    </c:if>
                                        <c:if test="${menuCode != 'org-review-list' || fromName!=null || dto.currentSurveyAssignOrg.orgSurveyState == 4}">
                                            ${dto.surveyStateName}
                                        </c:if>
                                    </td>
                                    <td>
                                        平台复审时间：<fmt:formatDate value="${dto.entrustReportStartDate}"
                                                                     pattern="yyyy-MM-dd"/>
                                    </td>
                                    <td>
                                        保司终审时间：<fmt:formatDate value="${dto.entrustReportEndDate}"
                                                                     pattern="yyyy-MM-dd"/>
                                    </td>
                                    <td>
                                        是否归档：<c:if test="${dto.archivesState== 0}">否</c:if>
                                        <c:if test="${dto.archivesState== 1}">是</c:if>
                                    </td>
                                </tr>
                                <tr>
                                        <%--                                <td>是否经典案例：<c:if test="${dto.isClassic== 0}">否</c:if>--%>
                                        <%--                                    <c:if test="${dto.isClassic== 1}">是</c:if></td>--%>
                                    <td>是否阳性：<c:if test="${dto.isSun == 0}">否</c:if>
                                        <c:if test="${dto.isSun == 1}"><span style="color: red;">是</span></c:if></td>
                                    <td>
                                        <c:if test="${!dto.help}">
                                        平台复审人员：
                                        <div id="div_belong_user_1">
                                                ${dto.belongUserName}
                                            <c:if test="${menuCode == 'assign-list' || menuCode == 'all-list' || maxRole}">
                                                <a onclick="cliUpdPrice(12)"><img height="25px" width="25px"
                                                                                  src="${ctx}/img/pen.png"></a>
                                                <div class="record" data-attr="survey_risk_case_info_belong_user_id">
                                                    <span class="icon-history"></span><span class="num"></span></div>
                                            </c:if>
                                            </c:if>
                                        </div>
                                        <div id="div_belong_user_2" style="display: none">
                                            <select class="singleSelect form-control" style="width: 150px;"
                                                    name="belongUserId" id="belongUserId"></select>
                                            更改备注：<textarea style="width: 100%;" name="belongUserIdRemark" rows="8"
                                                               cols="8" class="form-control"
                                                               placeholder="备注"></textarea>
                                            <input type="submit" value="确定" onclick="oprOK(${dto.id},'belongUser')"
                                                   class="btn" style="display: inline-flex"/>
                                        </div>
                                    </td>

                                    <td>是否寄送：<c:if test="${dto.isSendReport== 0}">未寄送</c:if>
                                        <c:if test="${dto.isSendReport== 1}">已寄送</c:if></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>基本费是否结算：<c:if test="${dto.price1IsCalc == 0}">未结算</c:if>
                                        <c:if test="${dto.price1IsCalc == 1}">已结算</c:if></td>
                                    <td>减损奖励是否结算：<c:if
                                            test="${dto.payType == 1 || dto.payType == 3}">无减损</c:if>
                                        <c:if test="${dto.payType == 2 || dto.payType == 4}">
                                            <c:if test="${dto.price2IsCalc == 0}">未结算</c:if>
                                            <c:if test="${dto.price2IsCalc == 1}">已结算</c:if>
                                        </c:if></td>
                                    <td>财务开票金额：${dto.billingApply.billingMoney}</td>
                                    <td>财务开票状态：<c:if test="${dto.billingApply.billingState == 1}">未申请</c:if>
                                        <c:if test="${dto.billingApply.billingState == 2}">已申请</c:if>
                                        <c:if test="${dto.billingApply.billingState == 3}">已开票</c:if>
                                        <c:if test="${dto.billingApply.billingState == 4}">已退票</c:if>
                                        <c:if test="${dto.billingApply.billingState == 5}">退票审核中</c:if>
                                        <c:if test="${dto.billingApply.billingState == 6}">重开审核中</c:if>
                                        <c:if test="${dto.billingApply.billingState == 7}">退票审核通过</c:if>
                                        <c:if test="${dto.billingApply.billingState == 8}">重开审核通过</c:if>
                                        <c:if test="${dto.billingApply.billingState == 9}">退票中</c:if></td>
                                </tr>
                                <tr>
                                    <td>财务开票时间：<fmt:formatDate value="${dto.billingApply.billingTime}"
                                                                     pattern="yyyy-MM-dd"/></td>
                                    <td>财务到账金额：${dto.billingApply.confirmAccountMoney}</td>
                                    <td>财务到账状态：<c:if
                                            test="${dto.billingApply.confirmAccountState == 1 || dto.billingApply.confirmAccountState == null}">未到账</c:if>
                                        <c:if test="${dto.billingApply.confirmAccountState == 2}">已到账</c:if></td>
                                    <td>财务到账时间：<fmt:formatDate value="${dto.billingApply.confirmAccountTime}"
                                                                     pattern="yyyy-MM-dd"/></td>
                                </tr>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
                <c:if test="${menuCode != 'assign-org-list' && menuCode != 'org-review-list'}">
                    <div class="info">
                        <div class="info-title">费用信息</div>
                        <div class="info-content">
                            <table class="table-1 new-bas-info" border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                <c:if test="${menuCode != 'assign-org-list' && menuCode != 'org-review-list'}">
                                    <tr>
                                        <td>结算方式：<c:if test="${dto.payType == 1}">一口价</c:if>
                                            <c:if test="${dto.payType == 2}">基本费+减损</c:if>
                                            <c:if test="${dto.payType == 3}">任务</c:if>
                                            <c:if test="${dto.payType == 4}">任务+减损</c:if></td>
                                        <td>结算详情：<c:if test="${dto.payType == 1}">(${dto.entrustMoney})元</c:if>
                                            <c:if test="${dto.payType == 2}">基本费(${dto.entrustMoney})，减损描述(${dto.entrustReLossesRemark})</c:if>
                                            <c:if test="${dto.payType == 3}">任务(${dto.surveyCaseDirections.size()})个</c:if>
                                            <c:if test="${dto.payType == 4}">任务(${dto.surveyCaseDirections.size()})个，减损描述(${dto.entrustReLossesRemark})</c:if></td>
                                        <td>
                                            乐凡申请结算价格：
                                            <div id="div_entrust_1">
                                                <c:if test="${!(menuCode == 'help-review' || (menuCode == 'all-list' && dto.help))}">
                                                    基本费<span style="color: red;">(${dto.entrustMoney})</span>减损奖励(${dto.entrustReLosses})
                                                    <c:if test="${menuCode == 'survey-list' && dto.lfCommit && (dto.surveyState == 22 || dto.surveyState == 30) && !dto.openBill || (menuCode == 'help-review' && !dto.openBill) || maxRole}">
                                                        <a onclick="cliUpdPrice(1)"><img height="25px" width="25px"
                                                                                         src="${ctx}/img/pen.png"></a>
                                                    </c:if>
                                                    <div class="record" data-attr="survey_risk_case_info_entrust_money">
                                                        <span class="icon-history"></span><span class="num"></span>
                                                    </div>
                                                    <%--                                            <c:if test="${menuCode != 'entrust-list'}">--%>
                                                    <%--                                                <a href = "javascript:void(0)" onclick = "adjustmentRecord(${dto.id},'adjustment','1')">调整记录</a>--%>
                                                    <%--                                            </c:if>--%>
                                                </c:if>
                                                <c:if test="${menuCode == 'help-review' || (menuCode == 'all-list' && dto.help)}">
                                                    <span style="color: red;">${dto.entrustMoney}</span>
                                                </c:if>
                                            </div>
                                            <div id="div_entrust_2" style="display: none">
                                                价格<input type="number" class="input-2" step="0.01" name="entrustMoney"
                                                           value="${dto.entrustMoney}"/>减损奖励<input type="number"
                                                                                                       class="input-2"
                                                                                                       step="0.01"
                                                                                                       name="entrustReLosses"
                                                                                                       value="${dto.entrustReLosses}"/>
                                                更改备注：<textarea style="width: 100%;" name="entrustMoneyRemark"
                                                                   rows="8" cols="8" class="form-control"
                                                                   placeholder="备注"></textarea>
                                                <input type="submit" value="确定" onclick="oprOK(${dto.id},'700')"
                                                       class="btn" style="display: inline-flex"/>
                                            </div>
                                        </td>
                                        <td>
                                            <c:if test="${dto.entrustReportEndDate != null || menuCode == 'entrust-list' || menuCode2 == 'agent-entrust-list' || maxRole}">
                                            委托方确认结算价格：
                                            <div id="div_entrust_ok_1">基本费<span
                                                    style="color: red;">(${dto.entrustOkPrice1})</span>减损奖励(${dto.entrustOkPrice2})
                                                <c:if test="${(menuCode == 'entrust-list' || menuCode2 == 'agent-entrust-list') && !dto.openBill}">
                                                    <a onclick="cliUpdPrice(4)"><img height="25px" width="25px"
                                                                                     src="${ctx}/img/pen.png"></a>
                                                </c:if>
                                                <div class="record"
                                                     data-attr="survey_risk_case_info_entrust_ok_price_1"><span
                                                        class="icon-history"></span><span class="num"></span></div>
                                                </c:if>
                                            </div>
                                            <div id="div_entrust_ok_2" style="display: none">
                                                价格<input type="number" class="input-2" step="0.01"
                                                           name="entrustOkPrice1" value="${dto.entrustOkPrice1}"/>减损奖励<input
                                                    type="number" class="input-2" step="0.01" name="entrustOkPrice2"
                                                    value="${dto.entrustOkPrice2}"/>
                                                更改备注：<textarea style="width: 100%;" name="entrustOkPrice1Remark"
                                                                   rows="8" cols="8" class="form-control"
                                                                   placeholder="备注"></textarea>

                                                <input type="submit" value="确定" onclick="oprOK(${dto.id},'800')"
                                                       class="btn" style="display: inline-flex"/>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${menuCode == 'all-list'}">
                                    <c:if test="${dto.showExpenseReimbursementValue}">
                                        <tr>
                                            <td>费用报销合计:${dto.totalMoney==null?0:dto.totalMoney}</td>
                                                <%--                                        <c:if test="${dto.reimState == null || dto.reimState != 2}"> <td>费用报销状态(案件):未完成</td></c:if>--%>
                                                <%--                                        <c:if test="${dto.reimState == 2}"> <td>费用报销状态(案件):报销完成</td></c:if>--%>
                                        </tr>
                                    </c:if>
                                </c:if>
                                <c:if test="${menuCode != 'my-list' && menuCode != 'entrust-list' && menuCode != 'org-review-list' && menuCode != 'assign-org-list' && menuCode != 'back-case-list'}">
                                    <tr>
                                        <td>是否结算绩效：
                                            <c:if test="${dto.performanceState == 0}">未结算</c:if>
                                            <c:if test="${dto.performanceState == 1}">结算中</c:if>
                                            <c:if test="${dto.performanceState == 2}">已结算</c:if>
                                        </td>
                                        <td>结算绩效日期：<fmt:formatDate value="${dto.performanceDate}"
                                                                         pattern="yyyy-MM-dd"/></td>
                                        <td>调查方结算价格<c:if
                                                test="${menuCode == 'all-list' || menuCode == 'survey-list' || menuCode == 'mark-list'}"><label
                                                style="color: red;">(供参考)</label></c:if>：
                                            <div id="div_survery_1">基本费<span
                                                    style="color: red;">(${dto.surveyMoney})</span>减损奖励(${dto.surveryReLosses})
                                                <c:if test="${(menuCode == 'survey-list' && dto.lfCommit && (dto.surveyState == 22 || dto.surveyState == 30)) || (menuCode == 'all-list' && dto.surveyState == 28) || menuCode == 'help-review' || maxRole}">
                                                    <a onclick="cliUpdPrice(2)"><img height="25px" width="25px"
                                                                                     src="${ctx}/img/pen.png"></a>
                                                </c:if>
                                                <div class="record" data-attr="survey_risk_case_info_survey_money"><span
                                                        class="icon-history"></span><span class="num"></span></div>
                                            </div>
                                            <div id="div_survery_2" style="display: none">
                                                基本费<input type="number" class="input-2" step="0.01"
                                                             name="surveyMoney" value="${dto.surveyMoney}"/>
                                                减损奖励<input type="number" class="input-2" step="0.01"
                                                               name="surveryReLosses" value="${dto.surveryReLosses}"/>
                                                更改备注：<textarea style="width: 100%;" name="surveyMoneyRemark"
                                                                   rows="8" cols="8" class="form-control"
                                                                   placeholder="备注"></textarea>
                                                <input type="submit" value="确定" onclick="oprOK(${dto.id},'701')"
                                                       class="btn" style="display: inline-flex"/>
                                            </div>
                                        </td>
                                        <td>调查方确认结算价格：基本费<span
                                                style="color: red;">(${dto.surveyOKMoney})</span>减损奖励(${dto.surveryOKReLosses})
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${menuCode == 'org-review-list' && dto.entrustReportStartDate != null}">
                                    <tr>
                                        <td>机构价格详情：基本费(${dto.orgPrice1})减损奖励(${dto.orgPrice2})</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>
                <input type="hidden" value="${dto.id}" name="id"/>
                <input type="hidden" value="${btnCode}" name="btnCode" id="btnCode"/>
                <input type="hidden"
                       value="<fmt:formatDate value="${dto.surveyRiskCase.entrustTime}" pattern="yyyy-MM-dd"/>"
                       name="entrustTime" id="entrustTime"/>
            </form>
            <c:if test="${menuCode == 'assign-list' || menuCode == 'org-review-list'
            || menuCode == 'survey-list' || menuCode == 'make-report-list' || menuCode == 'back-case-list'
             || menuCode == 'all-list' || menuCode == 'mark-list' || menuCode == 'time-track-list'
             || menuCode == 'task-user-review' || menuCode == 'task-org-review' || menuCode == 'help-review'
             || menuCode == 'guide-list' || menuCode == 'visit-list' || menuCode == 'assign-org-list'}">
                <form id="editFormOrg" role="form" action="${ctx}/survey/case/operate" method="post">
                    <input type="hidden" id="orgInfoBtnCode" name="btnCode" value="updOrgSurveyMoney">
                    <input type="hidden" id="caseId" name="caseId"/>
                    <input type="hidden" name="surveyOrgId" id="surveyOrgId" value=""/>
                    <input type="hidden" name="surveyAssignOrgId" id="surveyAssignOrgId" value=""/>
                    <div class="info info-1129">
                        <div class="info-title">已分配机构信息</div>
                        <div class="info-content">
                            <table class="table" border="0" cellpadding="0" cellspacing="0" style="text-align: left">
                                <thead>
                                <th width="10%">机构名称</th>
                                <th width="6%">分配任务类型</th>
                                <th width="5%">调查需求</th>
                                <th width="6%">分派机构日期</th>
                                <th width="6%">机构提交日期</th>
                                <th width="6%">机构截止日期</th>
                                <th width="5%">机构时效</th>
                                <th width="6%">超期考核绩效</th>
                                <th width="6%">机构案件状态</th>
                                <th width="8%">机构小结/报告结论</th>
                                <th width="4%">业务类型</th>
                                <c:if test="${menuCode == 'all-list' || menuCode == 'help-review' || menuCode == 'survey-list' || menuCode == 'agent-entrust-list'}">
                                    <th width="1%" style="display: none;">机构核算收入</th>
                                </c:if>
                                <c:if test="${(menuCode == 'all-list')
                                || (menuCode == 'survey-list')
                                || (menuCode == 'org-review-list' && dto.entrustReportStartDate != null) || menuCode == 'help-review'}">
                                    <th width="8%">
                                        机构价格
                                    </th>
                                    <th width="8%">
                                        考核后机构价格
                                    </th>
                                </c:if>
                                <c:if test="${menuCode == 'help-review' || (menuCode == 'all-list' && dto.help) || (menuCode2 == 'agent-entrust-list' && dto.help)}">
                                    <th width="6%">申请委托方价格</th>
                                </c:if>
                                <c:if test="${menuCode2 == 'agent-entrust-list' && dto.help || (menuCode == 'all-list' && dto.help) }">
                                    <th width="6%">
                                        委托方确认结算价格
                                    </th>
                                </c:if>
                                <c:if test="${dto.help}">
                                    <th width="5%">复审人员</th>
                                </c:if>
                                <c:if test="${(menuCode=='survey-list' || menuCode=='all-list') && (dto.surveyState == 22 || dto.surveyState == 24 || dto.surveyState == 28) && !dto.help}">
                                    <th width="5%">委托方价格</th>
                                </c:if>
                                <th width="8%">操作</th>
                                </thead>
                                <tbody>
                                <c:forEach items="${dto.surveyAssignOrgs}" var="item">
                                    <tr
                                            <c:if test="${item.reviewOff == 1}">bgcolor="#f5f5dc"</c:if> >
                                        <td>${item.surveyOrgName}
                                                <%--                                            <c:if test="${item.orgPrimaryType == 1}"><span style="color: #CC0000;">(主)</span></c:if>--%>
                                        </td>
                                        <td>
                                            <a data-value="<c:forEach items="${item.assignOrgTypes}" var="task">${task.taskName}&nbsp;</c:forEach>"
                                               onclick="showReLoossesRemark(1,this)"
                                               title="<c:forEach items="${item.assignOrgTypes}" var="task">${task.taskName}&nbsp;</c:forEach>"
                                               style="cursor: pointer;text-decoration: underline;">查看详情</a>
                                            <c:if test="${menuCode == 'all-list' || menuCode == 'assign-list' || maxRole}">
                                                <a onclick="updTaskType(${dto.id},${item.id},'updateOrgTaskType','updateTaskType')">
                                                    <img height="25px" width="25px" src="${ctx}/img/pen.png">
                                                </a>
                                            </c:if>
                                        </td>
                                        <td>
                                            <a data-value="${item.orgTaskRemark}" onclick="showReLoossesRemark(1,this)"
                                               title="${item.orgTaskRemark}"
                                               style="cursor: pointer;text-decoration: underline;">查看描述</a>
                                            <c:if test="${menuCode == 'all-list' || menuCode == 'assign-list' || maxRole}">
                                                <c:if test="${item.reviewTime == null }">
                                                    <a onclick="updOrgTaskRemark(${item.id})"><img height="25px"
                                                                                                   width="25px"
                                                                                                   src="${ctx}/img/pen.png"></a>
                                                </c:if>
                                                <div class="record"
                                                     data-attr="survey_assign_org_org_task_remark${item.id}"><span
                                                        class="icon-history"></span><span class="num"></span></div>
                                            </c:if>
                                            <div id="div_org_task_remark${item.id}" style="display: none;">
                                                调查需求：<textarea style="width: 225px;height: 70px;"
                                                                   name="surveyOrgTaskRemark${item.id}">${item.orgTaskRemark}</textarea>
                                                更改备注：<textarea style="width: 100%;"
                                                                   name="surveyOrgTaskRemarkRemark${item.id}" rows="8"
                                                                   cols="8" class="form-control"
                                                                   placeholder="备注"></textarea>
                                                <input type="submit" value="确定"
                                                       onclick="return orgTaskRemark(${dto.id},${item.id},${item.surveyOrgId},'updOrgTaskRemark')"
                                                       class="btn" style="display: inline-flex;margin-top: 10px;"/>
                                            </div>
                                        </td>
                                        <td><%--<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/>111--%>
                                            <div id="div_start_time_1${item.surveyOrgId}">
                                                <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/>
                                                <c:if test="${menuCode == 'all-list' || menuCode == 'assign-list' || maxRole}">
                                                    <c:if test="${dto.entrustReportStartDate == null }">
                                                        <a onclick="updPriceTime(${dto.id},${item.id},${item.surveyOrgId})"><img
                                                                height="25px" width="25px" src="${ctx}/img/pen.png"></a>
                                                    </c:if>
                                                    <div class="record"
                                                         data-attr="survey_assign_org_create_time${item.id}"><span
                                                            class="icon-history"></span><span class="num"></span></div>
                                                </c:if>
                                            </div>
                                            <div id="div_start_time_2${item.surveyOrgId}" style="display: none">
                                                <input name="endTime" type="text" class="form-control"
                                                       id="distributionAgencyTime${item.id}"
                                                       value="<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/>"
                                                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" required readonly
                                                       style="cursor: auto">
                                                更改备注：<textarea style="width: 100%;"
                                                                   name="distributionAgencyTimeRemark${item.id}"
                                                                   id="distributionAgencyTimeRemark${item.id}" rows="8"
                                                                   cols="8" class="form-control"
                                                                   placeholder="备注"></textarea>
                                                <input type="button" value="确定"
                                                       onclick="orgSurveyTimeOK(${dto.id},${item.id},${item.surveyOrgId},'updDistributionAgencyTime')"
                                                       class="btn" style="display: inline-flex"/>
                                            </div>
                                        </td>
                                        <td>
                                            <c:if test="${item.orgSurveyState == 4}">
                                                <fmt:formatDate value="${item.reportDate}" pattern="yyyy-MM-dd"/>
                                            </c:if>
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${item.orgEndTime}" pattern="yyyy-MM-dd"/>
                                            <div class="record" data-attr="survey_assign_org_org_end_time${item.id}">
                                                <span class="icon-history"></span><span class="num"></span></div>
                                        </td>
                                        <td>
                                            <div onclick="showOrgPreList(${item.id})"><span
                                                    style="color: ${item.efficiencyStateColor};">${item.efficiencyState}</span>
                                                <svg t="1592299710757" class="icon" viewBox="0 0 1024 1024"
                                                     version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2698"
                                                     width="16" height="16">
                                                    <path d="M510.99716 94.16259c-229.438553 0-414.760328 186.264241-414.760328 414.760328 0 229.483578 185.321776 414.760328 414.760328 414.760328 228.542136 0 414.760328-185.27675 414.760328-414.760328C925.757488 280.426831 739.539296 94.16259 510.99716 94.16259zM476.731752 768.855996c-29.598108 2.168386-51.090609-9.474792-53.069683-38.97978-2.545986-37.232996 21.1149-102.93437 25.969465-116.555599l48.263214-140.877541c4.806469-14.375405-3.062755-20.785396-9.66308-20.313651-30.399357 2.26253-65.324798 72.677253-80.07678 73.760934-5.79703 0.424672-11.075243-4.665253-11.405771-9.28548-0.942465-13.997805 33.039487-49.251727 43.032072-60.139705 30.918173-31.907711 72.158437-57.547671 119.857808-61.083194 35.349089-2.593058 74.56116 14.753005 50.052976 92.236727l-48.923246 155.676595c-4.148483 12.018731-11.82942 32.098046-10.981099 44.540426 0.329505 5.421476 4.00522 10.605545 10.556427 10.132778 24.698518-1.838881 65.418942-71.167876 76.966952-72.015174 4.101411-0.283456 10.17985 3.958148 10.652618 10.934027C649.469931 659.368372 559.826262 762.727415 476.731752 768.855996zM591.45154 364.93555c-33.746592 2.543939-57.360406-16.780175-59.527768-48.640814-2.355651-34.123168 27.007097-64.381309 63.204507-67.021439 32.897248-2.449795 58.207703 16.731056 60.376089 48.546669C657.907091 333.544609 628.450199 362.248348 591.45154 364.93555z"
                                                          p-id="2699" fill="#008000"></path>
                                                </svg>
                                            </div>
                                        </td>
                                        <div class="process orgPre${item.id}" style="display:none">
                                            <div class="bars-title">
                                                机构考核时效：${item.agingCheck}天 机构时效：${item.agingReal}天
                                                超期天数：${item.agingOver}天
                                            </div>
                                            <div class="close orgPreClose">
                                                <svg t="1592291150871" class="icon" viewBox="0 0 1024 1024"
                                                     version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6559"
                                                     width="16" height="16">
                                                    <path d="M583.168 523.776L958.464 148.48c18.944-18.944 18.944-50.176 0-69.12l-2.048-2.048c-18.944-18.944-50.176-18.944-69.12 0L512 453.12 136.704 77.312c-18.944-18.944-50.176-18.944-69.12 0l-2.048 2.048c-19.456 18.944-19.456 50.176 0 69.12l375.296 375.296L65.536 899.072c-18.944 18.944-18.944 50.176 0 69.12l2.048 2.048c18.944 18.944 50.176 18.944 69.12 0L512 594.944 887.296 970.24c18.944 18.944 50.176 18.944 69.12 0l2.048-2.048c18.944-18.944 18.944-50.176 0-69.12L583.168 523.776z"
                                                          p-id="6560" fill="#716d6d"></path>
                                                </svg>
                                            </div>
                                            <div class="bars">
                                                <c:forEach items="${item.orgPreList}" var="pre" varStatus="xh">
                                                    <div class="bar">
                                                        <div class="bar-date">${pre.k1}</div>
                                                        <div class="bar-contain">
                                                            <div class="title">
                                                                <div class="index"></div>
                                                            </div>
                                                            <div class="content">
                                                                <div class="text">${pre.k3}</div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="bar">
                                                        <div class="bar-date">${pre.k2}</div>
                                                        <div class="bar-contain">
                                                            <div class="title">
                                                                <div class="index"></div>
                                                            </div>
                                                            <c:if test="${!xh.last}">
                                                                <div class="content border-dotted">
                                                                    <div class="text"></div>
                                                                </div>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>

                                        <td>
                                            <div id="div_org_aging_rate_1_${item.id}">
                                                    ${item.overdueAgingRate}
                                                <c:if test="${(menuCode == 'help-review' || menuCode == 'survey-list') || maxRole}">
                                                    <a onclick="orgCaseUpd(${item.id},'agingRate')"><img height="25px"
                                                                                                         width="25px"
                                                                                                         src="${ctx}/img/pen.png"></a>
                                                </c:if>
                                                <div class="record"
                                                     data-attr="survey_assign_org_overdue_aging_rate${item.id}"><span
                                                        class="icon-history"></span><span class="num"></span></div>
                                            </div>
                                            <div id="div_org_aging_rate_2_${item.id}" style="display: none;">
                                                <input type="number" min="0" step="0.01" class="form-control"
                                                       name="agingRate${item.id}" value="${item.overdueAgingRate}"/>
                                                更改备注：<textarea style="width: 100%;" name="agingRateRemark${item.id}"
                                                                   rows="8" cols="8" class="form-control"
                                                                   placeholder="备注"></textarea>
                                                <input type="submit" value="确定"
                                                       onclick="return orgCaseOK(${item.id},'org-aging-rate')"
                                                       class="btn" style="display: inline-flex;margin-top: 10px;"/>
                                            </div>
                                        </td>
                                        <td>
                                            <c:if test="${item.orgSurveyState == 0}">待接收</c:if>
                                            <c:if test="${item.orgSurveyState == 1}">调查中</c:if>
                                            <c:if test="${item.orgSurveyState == 2}">初审中</c:if>
                                            <c:if test="${item.orgSurveyState == 3}"><span
                                                    style="color: red;">已拒绝</span></c:if>
                                                <%--<c:if test="${item.orgSurveyState == 4}">初审通过</c:if>--%>
                                            <c:if test="${item.orgSurveyState == 4}">
                                                <c:if test="${item.reviewUserId != null}">
                                                    <c:if test="${item.reviewTime != null}">
                                                        复审通过
                                                    </c:if>
                                                    <c:if test="${item.reviewTime == null}">
                                                        复审中
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${item.reviewUserId == null}">
                                                    初审通过
                                                </c:if>
                                            </c:if>
                                            <c:if test="${item.orgSurveyState == 5}">调查中</c:if>
                                            <c:if test="${item.orgSurveyState == 6}">调查中</c:if>
                                        </td>
                                        <td>
                                                <%--                                            <c:if test="${item.orgPrimaryType == 1}">--%>
                                                <%--                                                <c:if test="${dto.surveyConsignor.orgAttr == 2}">--%>
                                                <%--                                                    <a data-value="${item.orgSummary}" onclick="showReLoossesRemark(2,this)" title="${item.orgSummary}" style="cursor: pointer;text-decoration: underline;">查看描述</a>--%>
                                                <%--                                                <c:if test="${item.isCurOrg && menuCode == 'org-review-list'}">--%>
                                                <%--                                                    <c:if test="${item.reportDate == null }">--%>
                                                <%--                                                        <a onclick="updReLoossesRemark(${item.id})"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a>--%>
                                                <%--                                                    </c:if>--%>
                                                <%--                                                </c:if>--%>
                                                <%--                                                <div id="div_org_summary${item.id}" style="display: none;">--%>
                                                <%--                                                    机构小结/报告结论：<textarea style="width: 225px;height: 70px;" name="surveyOrgSummary${item.id}">${item.orgSummary}</textarea>--%>
                                                <%--                                                    <input type="submit" value="确定" onclick="return updOrgSummary(${dto.id},${item.id},${item.surveyOrgId},'updOrgSummary')" class="btn" style="display: inline-flex;margin-top: 10px;" />--%>
                                                <%--                                                </div>--%>
                                                <%--                                                </c:if>--%>
                                                <%--                                                <c:if test="${dto.surveyConsignor.orgAttr != 2}">--%>
                                                <%--                                                    <a data-value="${dto.reportCompletion}" onclick="showReLoossesRemark(2,this)" title="${dto.reportCompletion}" style="cursor: pointer;text-decoration: underline;">查看描述</a>--%>
                                                <%--                                                    <c:if test="${item.isCurOrg && menuCode == 'org-review-list'}">--%>
                                                <%--                                                        <c:if test="${item.reportDate == null }">--%>
                                                <%--                                                            <a onclick="updReLoossesRemark(${item.id})"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a>--%>
                                                <%--                                                        </c:if>--%>
                                                <%--                                                    </c:if>--%>
                                                <%--                                                    <div id="div_org_summary${item.id}" style="display: none;">--%>
                                                <%--                                                        机构小结/报告结论：<textarea style="width: 225px;height: 70px;" name="surveyReportCompletion${item.id}">${dto.reportCompletion}</textarea>--%>
                                                <%--                                                        <input type="submit" value="确定" onclick="return updOrgSummary(${dto.id},${item.id},${item.surveyOrgId},'updReportCompletion')" class="btn" style="display: inline-flex;margin-top: 10px;" />--%>
                                                <%--                                                    </div>--%>
                                                <%--                                                    </td>--%>
                                                <%--                                                </c:if>--%>
                                                <%--                                            </c:if>--%>
                                            <a data-value="${item.orgSummary}" onclick="showReLoossesRemark(2,this)"
                                               title="${item.orgSummary}"
                                               style="cursor: pointer;text-decoration: underline;">查看描述</a>
                                            <c:if test="${item.isCurOrg && menuCode == 'org-review-list' || maxRole}">
                                                <c:if test="${item.reportDate == null }">
                                                    <a onclick="updReLoossesRemark(${item.id})"><img height="25px"
                                                                                                     width="25px"
                                                                                                     src="${ctx}/img/pen.png"></a>
                                                </c:if>
                                                <div class="record" data-attr="survey_assign_org_org_summary${item.id}">
                                                    <span class="icon-history"></span><span class="num"></span></div>
                                            </c:if>
                                            <div id="div_org_summary${item.id}" style="display: none;">
                                                机构小结/报告结论：<textarea style="width: 225px;height: 70px;"
                                                                            name="surveyOrgSummary${item.id}">${item.orgSummary}</textarea>
                                                更改备注：<textarea style="width: 100%;"
                                                                   name="surveyOrgSummaryRemark${item.id}" rows="8"
                                                                   cols="8" class="form-control"
                                                                   placeholder="备注"></textarea>
                                                <input type="submit" value="确定"
                                                       onclick="return updOrgSummary(${dto.id},${item.id},${item.surveyOrgId},'updOrgSummary')"
                                                       class="btn" style="display: inline-flex;margin-top: 10px;"/>
                                            </div>
                                        </td>
                                        <td>
                                                ${item.servicesName}<br>
                                            <c:if test="${item.payType == 1}">(一口价)</c:if>
                                            <c:if test="${item.payType == 2}">(基本费+减损)</c:if>
                                            <c:if test="${item.payType == 3}">(任务)</c:if>
                                        </td>
                                        <c:if test="${menuCode == 'all-list' || menuCode == 'help-review' || menuCode == 'survey-list' || menuCode == 'agent-entrust-list'}">
                                            <td width="6%" style="display: none;">${item.accMoney}</td>
                                        </c:if>
                                        <c:if test="${(menuCode == 'all-list' )
                                || (menuCode == 'survey-list' )
                                    || (menuCode == 'org-review-list' && dto.entrustReportStartDate != null) || menuCode == 'help-review'}">
                                            <td>
                                                <div id="div_org_survey_money1${item.id}">
                                                    <c:if test="${menuCode != 'org-review-list'}">
                                                            <span>调查费(${item.assessOrgMoney})减损价格(${item.assessOrgLossesMoney})<br>
                                                            (<a data-value="${item.surveryReLoossesRemark}"
                                                                onclick="showReLoossesRemark(2,this)"
                                                                title="${item.surveryReLoossesRemark}"
                                                                style="cursor: pointer;text-decoration: underline;">减损描述</a>)</span>
                                                    </c:if>
                                                    <c:if test="${menuCode == 'org-review-list'}">
                                                        <c:if test="${item.isCurOrg}">
                                                    <span>调查费(${item.assessOrgMoney})减损价格(${item.assessOrgLossesMoney})<br>
                                                    (<a data-value="${item.surveryReLoossesRemark}"
                                                        onclick="showReLoossesRemark(2,this)"
                                                        title="${item.surveryReLoossesRemark}"
                                                        style="cursor: pointer;text-decoration: underline;">减损描述</a>)</span>
                                                        </c:if>
                                                        <c:if test="${!item.isCurOrg}">
                                                            ***
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${menuCode != 'org-review-list' && menuCode != 'all-list' || maxRole}">
                                                        <a onclick="updOrgSurveyMoney(${item.id})"><img height="25px"
                                                                                                        width="25px"
                                                                                                        src="${ctx}/img/pen.png"></a>
                                                    </c:if>
                                                    <c:if test="${menuCode != 'org-review-list'}">
                                                        <div class="record"
                                                             data-attr="survey_assign_org_org_assess_org_money${item.id}">
                                                            <span class="icon-history"></span><span class="num"></span>
                                                        </div>
                                                    </c:if>
                                                </div>
                                                <div id="div_org_survey_money2${item.id}" style="display: none;">
                                                    调查费：<input type="number" class="input-2"
                                                                  style="display: inline-flex;" step="0.01"
                                                                  value="${item.assessOrgMoney}"
                                                                  name="surveyMoney${item.id}"/>
                                                    减损价格：<input type="number" class="input-2"
                                                                    style="display: inline-flex;" step="0.01"
                                                                    value="${item.assessOrgLossesMoney}"
                                                                    name="surveryReLosses${item.id}"/>
                                                    <br>减损描述：<textarea style="width: 225px;height: 70px;"
                                                                           name="surveyMoneyRemark${item.id}">${item.surveryReLoossesRemark}</textarea>
                                                    更改备注：<textarea style="width: 100%;"
                                                                       name="surveyMoneyRemarkRemark${item.id}" rows="8"
                                                                       cols="8" class="form-control"
                                                                       placeholder="备注"></textarea>
                                                    <input type="submit" value="确定"
                                                           onclick="return orgSurveyMoneyOK(${dto.id},${item.id},${item.surveyOrgId},'updOrgSurveyMoney')"
                                                           class="btn" style="display: inline-flex;margin-top: 10px;"/>
                                                </div>
                                            </td>
                                            <td>
                                                <div>
                                                    <c:if test="${menuCode != 'org-review-list'}">
                                                        <span>调查费<span style="color: red;">(${item.surveyMoneySubmit})</span>减损价格(${item.surveryReLossesSubmit})<br>
                                                            (<a data-value="${item.surveryReLoossesRemark}"
                                                                onclick="showReLoossesRemark(2,this)"
                                                                title="${item.surveryReLoossesRemark}"
                                                                style="cursor: pointer;text-decoration: underline;">减损描述</a>)</span>
                                                    </c:if>
                                                    <c:if test="${menuCode == 'org-review-list'}">
                                                        <c:if test="${item.isCurOrg}">
                                                    <span>调查费(${item.surveyMoneySubmit})减损价格(${item.surveryReLossesSubmit})<br>
                                                    (<a data-value="${item.surveryReLoossesRemark}"
                                                        onclick="showReLoossesRemark(2,this)"
                                                        title="${item.surveryReLoossesRemark}"
                                                        style="cursor: pointer;text-decoration: underline;">减损描述</a>)</span>
                                                        </c:if>
                                                        <c:if test="${!item.isCurOrg}">
                                                            ***
                                                        </c:if>
                                                    </c:if>
                                                </div>
                                            </td>
                                        </c:if>
                                        <c:if test="${menuCode == 'help-review' || (menuCode == 'all-list' && dto.help) || (menuCode2 == 'agent-entrust-list' && dto.help)}">
                                            <td>

                                                <div id="div_org_survey2_money1${item.id}">
                                                    <c:if test="${item.inscompanyMoney == 0.00 || item.inscompanyMoney ==null ||  item.inscompanyMoney == '' }">
                                                        0.00
                                                    </c:if>
                                                    <c:if test="${item.inscompanyMoney != 0.00 && item.inscompanyMoney !=null && item.inscompanyMoney != '' }">
                                                        <del style="color: red">${item.oldInscompanyMoney}</del>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;<span>${item.inscompanyMoney}</span>
                                                        (<a data-value="${item.inscompanyMoneyDesc}" onclick="showReLoossesRemark(2,this)" title="${item.inscompanyMoneyDesc}" style="cursor: pointer;text-decoration: underline;">
                                                        价格说明
                                                        </a>)</span>

                                                    </c:if>
                                                    <c:if test="${menuCode == 'help-review' && userInfo.userId == item.reviewUserId || maxRole}">
                                                        <a onclick="updOrgSurvey2Money(${item.id})"><img height="25px"
                                                                                                         width="25px"
                                                                                                         src="${ctx}/img/pen.png"></a>
                                                    </c:if>
                                                    <div class="record"
                                                         data-attr="survey_assign_org_inscompany_money${item.id}"><span
                                                            class="icon-history"></span><span class="num"></span></div>
                                                </div>
                                                <div id="div_org_survey2_money2${item.id}" style="display: none;">
                                                    委托方价格：<input type="number" class="input-2"
                                                                      style="display: inline-flex;" step="0.01"
                                                                      value="${item.inscompanyMoney}"
                                                                      name="inscompanyMoney${item.id}"
                                                                      id="newInscompanyMoney${item.id}"/>
                                                    <br>备注：<textarea style="width: 225px;height: 70px;"
                                                                       name="inscompanyMoneyDesc${item.id}"></textarea>
                                                    <input type="submit" value="确定"
                                                           onclick="return orgSurveyMoneyOK(${dto.id},${item.id},${item.surveyOrgId},'updClientPrice')"
                                                           class="btn" style="display: inline-flex;margin-top: 10px;"/>
                                                </div>
                                            </td>
                                        </c:if>
                                        <c:if test="${menuCode2 == 'agent-entrust-list' && dto.help || (menuCode == 'all-list' && dto.help) }">
                                            <td>
                                                <div id="div_entrust_ok_1${item.id}">
                                                    <c:if test="${item.inscompanySubmitMoney == 0 || item.inscompanySubmitMoney==null}">
                                                        0.00
                                                    </c:if>
                                                    <c:if test="${item.inscompanySubmitMoney != 0 && item.inscompanySubmitMoney !=null}">
                                                        ${item.inscompanySubmitMoney}
                                                    </c:if>
                                                    <c:if test="${menuCode2 == 'agent-entrust-list' || maxRole}">
                                                        <a onclick="cliUpdPriceClient(${item.id})"><img height="25px"
                                                                                                        width="25px"
                                                                                                        src="${ctx}/img/pen.png"></a>
                                                    </c:if>
                                                    <div class="record"
                                                         data-attr="survey_assign_org_inscompany_submit_money${item.id}">
                                                        <span class="icon-history"></span><span class="num"></span>
                                                    </div>
                                                </div>
                                                <div id="div_entrust_ok_2${item.id}" style="display: none">
                                                    价格<input type="number" class="input-2" step="0.01"
                                                               id="entrustOkPrice1${item.id}"
                                                               value="${item.inscompanySubmitMoney}"/>
                                                    更改备注：<textarea style="width: 100%;"
                                                                       name="entrustOkPrice1Remark${item.id}"
                                                                       id="entrustOkPrice1Remark${item.id}" rows="8"
                                                                       cols="8" class="form-control"
                                                                       placeholder="备注"></textarea>
                                                    <input type="button" value="确定"
                                                           onclick="updOprOK(${dto.id},${item.id},'updClientMoney')"
                                                           class="btn" style="display: inline-flex"/>
                                                </div>
                                            </td>
                                        </c:if>
                                        <c:if test="${dto.help}">
                                            <td>
                                                <div id="div_org_survey_review1${item.id}">
                                                        ${item.reviewUserName}
                                                    <c:if test="${menuCode == 'assign-list' || menuCode == 'all-list' || maxRole}">
                                                        <a onclick="updOrgInfo(${item.id},${item.reviewUserId})"><img
                                                                height="25px" width="25px" src="${ctx}/img/pen.png"></a>
                                                    </c:if>
                                                    <div class="record"
                                                         data-attr="survey_assign_org_review_user_id${item.id}"><span
                                                            class="icon-history"></span><span class="num"></span></div>
                                                </div>
                                                <div id="div_org_survey_review2${item.id}" style="display: none">
                                                    <select class="singleSelect form-control" style="width: 150px;"
                                                            id="reviewUserId${item.id}"
                                                            name="reviewUserId${item.id}"></select>
                                                    更改备注：<textarea style="width: 100%;"
                                                                       name="reviewUserIdRemark${item.id}" rows="8"
                                                                       cols="8" class="form-control"
                                                                       placeholder="备注"></textarea>
                                                    <input type="submit" value="确定"
                                                           onclick="orgSurveyMoneyOK(${dto.id},${item.id},${item.surveyOrgId},'updOrgSurveyReview')"
                                                           class="btn" style="display: inline-flex"/>
                                                </div>
                                            </td>
                                        </c:if>
                                        <c:if test="${(menuCode=='survey-list'  || menuCode=='all-list') && (dto.surveyState == 22 || dto.surveyState == 24 || dto.surveyState == 28) && !dto.help}">
                                            <td>${item.inscompanyMoney}</td>
                                        </c:if>
                                        <td>
                                            <c:if test="${menuCode == 'assign-list'}">
                                                <c:if test="${item.orgSurveyState != 2 && item.orgSurveyState != 4}">
                                                    <%--&nbsp;&nbsp;<a href="javascript:void(0)" onclick="operate(${item.id},118,true)">删除</a>--%>
                                                    <a href="javascript:void(0)"
                                                       onclick="operate(${item.id},117,false)">改派</a>
                                                    <a href="javascript:void(0)"
                                                       onclick="operate(${item.id},118,false)">删除</a>
                                                </c:if>
                                                <c:if test="${item.orgSurveyState == 3 || item.surveyInvestigatorCaseId == null}">
                                                    <%--                                        <a href="javascript:void(0)" onclick="operate(${item.id},117,false)">改派</a>--%>
                                                </c:if>
                                            </c:if>
                                                <%--                                机构复核 且 初审通过   且未到风控审核  则可以撤回已经初审通过的机构 --%>
                                            <c:if test="${item.isCurOrg && menuCode == 'org-review-list' && item.orgSurveyState == 4 && dto.lefanReportDate == null}">
                                                <a href="javascript:void(0)"
                                                   onclick="operate(${item.id},123,true)">撤回</a>
                                            </c:if>
                                            <c:if test="${menuCode == 'task-org-review' && item.orgSurveyState == 4}">
                                                <c:if test="${item.reviewOff == 0}">
                                                    <a href="javascript:void(0)"
                                                       onclick="operate(${item.id},'review-org-yes',true)">预审通过</a>
                                                </c:if>
                                                <c:if test="${item.reviewOff == 1}">
                                                    <a href="javascript:void(0)"
                                                       onclick="operate(${item.id},'review-org-cancel',true)">取消预审通过</a>
                                                </c:if>
                                                <a href="javascript:void(0)"
                                                   onclick="operate(${item.id},'123',true)">撤回</a>
                                            </c:if>
                                            <c:if test="${item.review && menuCode == 'help-review'}">
                                                <a href="javascript:void(0)"
                                                   onclick="operate(${item.id},'review-help-yes',true)">审核通过</a>
                                                <a href="javascript:void(0)"
                                                   onclick="operate(${item.id},'review-help-no',false)">退回</a>
                                            </c:if>
                                            <c:if test="${item.reviewTime != null && dto.entrustReportEndDate == null && menuCode == 'help-review'}">
                                                <a href="javascript:void(0)"
                                                   onclick="operate(${item.id},'review-help-chehui',true)">撤回</a>
                                            </c:if>
                                            <c:if test="${oprType!=null && oprType =='safe'}">
                                                <a href="javascript:void(0)"
                                                   onclick="addFollow(${item.surveyInfoId},${item.surveyOrgId},'newfollow')">添加跟踪</a>
                                            </c:if>
                                            <c:if test="${menuCode == 'survey-list' || menuCode == 'all-list' || maxRole}">
                                                <div id="div_org_mark_error1${item.id}">
                                                    <c:if test="${item.markError == 0}">
                                                        <a href="javascript:void(0)"
                                                           onclick="mark(${item.id})">标记差错</a>
                                                    </c:if>
                                                    <c:if test="${item.markError != 0}">
                                                        <a style="color: #FF0000!important;" href="javascript:void(0)"
                                                           onclick="mark(${item.id})">编辑差错</a>
                                                    </c:if>
                                                </div>
                                                <div id="div_org_mark_error2${item.id}" style="display: none;">
                                                    差错类型:<select name="markType${item.id}" class="form-control">
                                                    <option value="1"
                                                            <c:if test="${item.markError == 1}">selected</c:if> >常规差错
                                                    </option>
                                                    <option value="2"
                                                            <c:if test="${item.markError == 2}">selected</c:if> >重大差错
                                                    </option>
                                                </select>
                                                    差错备注(<span style="color: #ff0000;">*</span>):<textarea
                                                        id="markRemark${item.id}" name="markRemark${item.id}"
                                                        style="width: 100%;" rows="8" cols="8" class="form-control"
                                                        placeholder="备注">${item.markErrorRemark}</textarea>
                                                    <input type="button" value="取消" onclick="reload();" class="btn"
                                                           style="display: inline-flex"/>
                                                    <input type="submit" value="确定"
                                                           onclick="return orgCaseOK(${item.id},'mark-error')"
                                                           class="btn" style="display: inline-flex"/>
                                                    <c:if test="${item.markError != 0}">
                                                        <input type="submit" value="取消标记差错"
                                                               onclick="return orgCaseOK(${item.id},'qx-mark-error')"
                                                               class="btn" style="display: inline-flex"/>
                                                    </c:if>
                                                </div>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </form>
            </c:if>
            <c:if test="${menuCode == 'assign-list' || menuCode == 'assign-org-list' || menuCode == 'org-review-list'
            || menuCode == 'survey-list' || menuCode == 'make-report-list' || menuCode == 'all-list' || menuCode == 'back-case-list'
             || menuCode == 'mark-list' || menuCode == 'time-track-list' || menuCode == 'task-user-review' || menuCode == 'task-org-review'
             || menuCode == 'help-review' || menuCode == 'guide-list' || menuCode == 'visit-list' || menuCode == 'feeViewManager'}">
                <form id="editFormTask" role="form" action="${ctx}/survey/case/sic/operate" method="post">
                    <input type="hidden" name="btnCode" id="surveyBtnCode" value="updSurveyTaskMoney">
                    <input type="hidden" name="surveyCaseId" id="surveyCaseId" value=""/>
                    <div class="info info-1129">
                        <div class="info-title">任务详情</div>
                        <div class="info-content">
                            <table class="table" border="0" cellpadding="0" cellspacing="0" style="text-align: left"
                                   id="assignInvestigatorsTimes">
                                <thead>
                                <th>调查员名称</th>
                                <th>机构</th>
                                <th>分配任务类型</th>
                                <th>任务描述</th>
                                <th>分派调查员日期</th>
                                <th>提交审核日期</th>
                                <th>调查员截止日期</th>
                                <th>调查员时效</th>
                                <th>超期考核绩效</th>
                                <th>调查总积分</th>
                                <th>
                                    <c:if test="${(!dto.help && (menuCode =='survey-list' || menuCode =='all-list')) || (menuCode =='time-track-list' && oprType=='safe')}">阳性奖励(元)</c:if>
                                    <c:if test="${(dto.help && menuCode =='survey-list') || (dto.help && menuCode =='all-list')
                                || menuCode =='help-review' || (menuCode =='time-track-list' && oprType=='help') || menuCode =='org-review-list'
                                || menuCode =='assign-org-list' || menuCode =='guide-list' || menuCode =='assign-list'
                                || menuCode =='task-user-review' || menuCode =='task-org-review'}">阳性总积分</c:if>
                                </th>
                                    <%--                            <th>分值</th>--%>
                                <th>是否阳性</th>
                                <th>状态</th>

                                <c:if test="${menuCode == 'help-review' || menuCode == 'org-review-list' || menuCode == 'all-list'}">
                                    <%--<th>调查结论</th>
                                <th>特殊情况说明</th>--%>
                                </c:if>
                                <c:if test="${dto.showExpenseReimbursementValue}">
                                    <th>费用报销</th>
                                    <%--                                <th>费用报销状态</th>--%>
                                </c:if>
                                <c:if test="${menuCode == 'survey-list' || menuCode == 'org-review-list' || menuCode == 'assign-list' || menuCode == 'assign-org-list' || menuCode == 'task-user-review' || menuCode == 'help-review' || menuCode == 'time-track-list'}">
                                    <c:if test="${(menuCode == 'survey-list' && dto.lfCommit) || menuCode != 'survey-list'}">
                                        <th>操作</th>
                                    </c:if>
                                </c:if>
                                </thead>
                                <tbody>
                                <c:forEach items="${dto.surveyInvestigatorCases}" var="item">
                                    <tr
                                            <c:if test="${item.reviewOff == 1}">bgcolor="#f5f5dc"</c:if> >
                                        <td>${item.surveyUserName}
                                            <c:if test="${item.showKey}">(主)</c:if>
                                                <%--<c:if test="${item.surveyUserType == 1}">(<span style="color: red">主</span>)</c:if>--%>
                                        </td>
                                        <td>
                                                ${item.surveyFranchisee.name}
                                                <%--                                        <span style="color: red;">【<c:if test="${item.surveyFranchisee.type == 1}">直营</c:if><c:if test="${item.surveyFranchisee.type == 2}">合伙</c:if><c:if test="${item.surveyFranchisee.type == 3}">合作</c:if>】</span>--%>
                                        </td>
                                        <td>
                                            <a data-value="<c:forEach items="${item.tasks}" var="task">${task.taskName}&nbsp;</c:forEach>"
                                               onclick="showReLoossesRemark(1,this)"
                                               title="<c:forEach items="${item.tasks}" var="task">${task.taskName}&nbsp;</c:forEach>"
                                               style="cursor: pointer;text-decoration: underline;">查看详情</a></td>
                                        </td>
                                        <td>
                                            <div id="div_survey_task_remark_1_${item.id}">
                                                <a data-value="${item.surveyTaskRemark}"
                                                   onclick="showReLoossesRemark(1,this)"
                                                   title="${item.surveyTaskRemark}"
                                                   style="cursor: pointer;text-decoration: underline;">查看描述</a>
                                                <c:if test="${menuCode == 'all-list' || menuCode == 'assign-list' || maxRole}">
                                                    <a onclick="surveyCaseUpd(${item.id},'surveyTaskRemark')"><img
                                                            height="25px" width="25px" src="${ctx}/img/pen.png"></a>
                                                </c:if>
                                                <div class="record"
                                                     data-attr="survey_investigator_case_survey_remark${item.id}"><span
                                                        class="icon-history"></span><span class="num"></span></div>
                                            </div>

                                            <div id="div_survey_task_remark_2_${item.id}" style="display: none">
                                                任务描述：<textarea style="width: 225px;height: 70px"
                                                                   name="surveyTaskRemark${item.id}">${item.surveyTaskRemark}</textarea>
                                                更改备注：<textarea style="width: 100%"
                                                                   name="surveyTaskRemarkRemark${item.id}" rows="8"
                                                                   cols="8" class="form-control"
                                                                   placeholder="备注"></textarea>
                                                <input type="submit" value="确定"
                                                       onclick="return surveyCaseOK(${item.id},'survey-task-remark')"
                                                       class="btn" style="display: inline-flex;margin-top: 10px;"/>
                                            </div>
                                        </td>
                                        </td>

                                        <td><fmt:formatDate value="${item.assignDate}" pattern="yyyy-MM-dd"/></td>
                                        <td><fmt:formatDate value="${item.creportDate}" pattern="yyyy-MM-dd"/></td>
                                        <td><fmt:formatDate value="${item.surveyEndTime}" pattern="yyyy-MM-dd"/></td>
                                        <td>
                                            <div onclick="showOrgPreList(${item.id})"><span
                                                    style="color: ${item.efficiencyStateColor};">${item.efficiencyState}</span>
                                                <svg t="1592299710757" class="icon" viewBox="0 0 1024 1024"
                                                     version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2698"
                                                     width="16" height="16">
                                                    <path d="M510.99716 94.16259c-229.438553 0-414.760328 186.264241-414.760328 414.760328 0 229.483578 185.321776 414.760328 414.760328 414.760328 228.542136 0 414.760328-185.27675 414.760328-414.760328C925.757488 280.426831 739.539296 94.16259 510.99716 94.16259zM476.731752 768.855996c-29.598108 2.168386-51.090609-9.474792-53.069683-38.97978-2.545986-37.232996 21.1149-102.93437 25.969465-116.555599l48.263214-140.877541c4.806469-14.375405-3.062755-20.785396-9.66308-20.313651-30.399357 2.26253-65.324798 72.677253-80.07678 73.760934-5.79703 0.424672-11.075243-4.665253-11.405771-9.28548-0.942465-13.997805 33.039487-49.251727 43.032072-60.139705 30.918173-31.907711 72.158437-57.547671 119.857808-61.083194 35.349089-2.593058 74.56116 14.753005 50.052976 92.236727l-48.923246 155.676595c-4.148483 12.018731-11.82942 32.098046-10.981099 44.540426 0.329505 5.421476 4.00522 10.605545 10.556427 10.132778 24.698518-1.838881 65.418942-71.167876 76.966952-72.015174 4.101411-0.283456 10.17985 3.958148 10.652618 10.934027C649.469931 659.368372 559.826262 762.727415 476.731752 768.855996zM591.45154 364.93555c-33.746592 2.543939-57.360406-16.780175-59.527768-48.640814-2.355651-34.123168 27.007097-64.381309 63.204507-67.021439 32.897248-2.449795 58.207703 16.731056 60.376089 48.546669C657.907091 333.544609 628.450199 362.248348 591.45154 364.93555z"
                                                          p-id="2699" fill="#008000"></path>
                                                </svg>
                                            </div>
                                        </td>
                                        <div class="process orgPre${item.id}" style="display:none">
                                            <div class="bars-title">
                                                调查员考核时效：${item.agingCheck}天 调查员时效：${item.agingReal}天
                                                超期天数：${item.agingOver}天
                                            </div>
                                            <div class="close orgPreClose">
                                                <svg t="1592291150871" class="icon" viewBox="0 0 1024 1024"
                                                     version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6559"
                                                     width="16" height="16">
                                                    <path d="M583.168 523.776L958.464 148.48c18.944-18.944 18.944-50.176 0-69.12l-2.048-2.048c-18.944-18.944-50.176-18.944-69.12 0L512 453.12 136.704 77.312c-18.944-18.944-50.176-18.944-69.12 0l-2.048 2.048c-19.456 18.944-19.456 50.176 0 69.12l375.296 375.296L65.536 899.072c-18.944 18.944-18.944 50.176 0 69.12l2.048 2.048c18.944 18.944 50.176 18.944 69.12 0L512 594.944 887.296 970.24c18.944 18.944 50.176 18.944 69.12 0l2.048-2.048c18.944-18.944 18.944-50.176 0-69.12L583.168 523.776z"
                                                          p-id="6560" fill="#716d6d"></path>
                                                </svg>
                                            </div>
                                            <div class="bars">
                                                <c:forEach items="${item.urgPreList}" var="pre" varStatus="xh">
                                                    <div class="bar">
                                                        <div class="bar-date">${pre.k1}</div>
                                                        <div class="bar-contain">
                                                            <div class="title">
                                                                <div class="index"></div>
                                                            </div>
                                                            <div class="content">
                                                                <div class="text">${pre.k3}</div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="bar">
                                                        <div class="bar-date">${pre.k2}</div>
                                                        <div class="bar-contain">
                                                            <div class="title">
                                                                <div class="index"></div>
                                                            </div>
                                                            <c:if test="${!xh.last}">
                                                                <div class="content border-dotted">
                                                                    <div class="text"></div>
                                                                </div>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <td>
                                            <div id="div_agingrate1_${item.id}">
                                                    ${item.overdueAgingRate}
                                                <c:if test="${item.rateEdit && (menuCode == 'help-review' || menuCode == 'survey-list') || maxRole}">
                                                    <a onclick="surveyCaseUpd(${item.id},'agingRate')"><img
                                                            height="25px" width="25px" src="${ctx}/img/pen.png"></a>
                                                </c:if>
                                                <div class="record"
                                                     data-attr="survey_investigator_case_overdue_aging_rate${item.id}">
                                                    <span class="icon-history"></span><span class="num"></span></div>
                                            </div>
                                            <div id="div_agingrate2_${item.id}" style="display: none;">
                                                <input type="number" min="0" step="0.01" class="form-control"
                                                       name="agingRate${item.id}" value="${item.overdueAgingRate}"/>
                                                更改备注：<textarea style="width: 100%;" name="agingRateRemark${item.id}"
                                                                   rows="8" cols="8" class="form-control"
                                                                   placeholder="备注"></textarea>
                                                <input type="submit" value="确定"
                                                       onclick="return surveyCaseOK(${item.id},'aging-rate')"
                                                       class="btn" style="display: inline-flex;margin-top: 10px;"/>
                                            </div>
                                        </td>
                                        <td>
                                            <c:if test="${item.assessBaseScore != item.baseScore}">
                                                <span style="color: red;text-decoration: line-through;">${item.assessBaseScore}</span>&nbsp;&nbsp;
                                            </c:if>
                                            <span>${item.baseScore}</span>
                                        </td>
                                        <td>
                                            <c:if test="${(!dto.help && (menuCode =='survey-list' || menuCode =='all-list')) || (menuCode =='time-track-list' && oprType=='safe')}">
                                                <div id="divSunMoney1${item.id}">
                                                    <c:if test="${item.sunMoney == null}">0.00</c:if>
                                                    <c:if test="${item.sunMoney != null}">${item.sunMoney}</c:if>
                                                    <c:if test="${item.surveyFranchisee.insuranceType == 1 || maxRole}">
                                                        <a onclick="updMoney(${item.id},7)"><img height="25px"
                                                                                                 width="25px"
                                                                                                 src="${ctx}/img/pen.png"></a>
                                                    </c:if>
                                                    <div class="record"
                                                         data-attr="survey_investigator_case_sun_money${item.id}"><span
                                                            class="icon-history"></span><span class="num"></span></div>
                                                </div>
                                                <div id="divSunMoney2${item.id}" style="display: none;">
                                                    <input type="number" class="input-2" style="display: inline-flex;"
                                                           step="0.01" value="${item.sunMoney}"
                                                           id="updSunMoney${item.id}"/>
                                                    更改备注：<textarea style="width: 100%;"
                                                                       name="updSunMoneyRemark${item.id}"
                                                                       id="updSunMoneyRemark${item.id}" rows="8"
                                                                       cols="8" class="form-control"
                                                                       placeholder="备注"></textarea>
                                                    <a href="javascript:void(0);"
                                                       onclick="updSunMoney('${item.id}','updSunMoney',true)">确定</a>&nbsp;&nbsp;
                                                </div>
                                            </c:if>
                                            <c:if test="${(dto.help && menuCode =='survey-list') || (dto.help && menuCode =='all-list')
                                        || menuCode =='help-review' || (menuCode =='time-track-list' && oprType=='help') || menuCode =='org-review-list'
                                        || menuCode =='assign-org-list' || menuCode =='guide-list' || menuCode =='assign-list'
                                        || menuCode =='task-user-review' || menuCode =='task-org-review'}">
                                                <c:if test="${item.assessSunScore != item.scoreSun}">
                                                    <span style="color: red;text-decoration: line-through;">${item.assessSunScore}</span>&nbsp;&nbsp;
                                                </c:if>
                                                <span>${item.scoreSun}</span>
                                            </c:if>
                                        </td>
                                            <%--                                    <td>基础分：${item.baseScore}<c:if test="${item.sunScore != null}">阳性分：${item.sunScore}</c:if></td>--%>
                                        <td>
                                            <c:if test="${item.isSun == 0}">否</c:if>
                                            <c:if test="${item.isSun == 1}"><span
                                                    style="color: red;">是</span>(<fmt:formatDate
                                                    value="${item.sunTime}" pattern="yyyy-MM-dd HH:mm:ss"/>)</c:if>
                                            <c:if test="${dto.help && menuCode == 'org-review-list' && dto.currentSurveyAssignOrg.orgSurveyState == 2 && item.isCurOrg}">
                                                <c:if test="${item.isSun == 0}">
                                                    <a href="javascript:void(0);"
                                                       onclick="sun(${item.id},'sign','lefan-help-ok',true)">切换</a>&nbsp;&nbsp;
                                                </c:if>
                                                <c:if test="${item.isSun == 1}">
                                                    <a href="javascript:void(0);"
                                                       onclick="sun(${item.id},'sign','lefan-help-quxiao',true)">切换</a>&nbsp;&nbsp;
                                                </c:if>
                                                <%--                                            <a onclick="updateIsSun(${item.id},${item.isSun})">切换</a>--%>
                                            </c:if>
                                            <c:if test="${!dto.help && item.isSun == 1 && (menuCode == 'org-review-list' || menuCode == 'survey-list' || menuCode == 'agent-entrust-list')}">
                                                <a href="javascript:void(0);"
                                                   onclick="sun(${item.id},'sign','lefan-bs-quxiao',true)">取消阳性</a>&nbsp;&nbsp;
                                            </c:if>
                                        </td>
                                        <td>
                                                ${item.surveyStateName}
                                        </td>

                                        <c:if test="${menuCode == 'help-review' || menuCode == 'org-review-list' || menuCode == 'all-list'}">
                                            <%--<td>
                                            <a data-value="${item.surveySummary}" onclick="showReLoossesRemark(2,this)" title="${item.surveySummary}"
                                               style="cursor: pointer;text-decoration: underline;">查看</a></td>
                                        </td>
                                        <td>
                                            <a data-value="${item.specialRemark}" onclick="showReLoossesRemark(2,this)" title="${item.specialRemark}"
                                               style="cursor: pointer;text-decoration: underline;">查看</a></td>
                                        </td>--%>
                                        </c:if>
                                        <c:if test="${dto.showExpenseReimbursementValue}">
                                            <td>${item.investigatorReMoney==null?0:item.investigatorReMoney}</td>
                                            <%--                                        <td>--%>
                                            <%--                                            <c:if test="${item.reStateStr != null && item.reStateStr != ''}"> ${item.reStateStr}</c:if>--%>
                                            <%--                                            <c:if test="${item.reStateStr == null || item.reStateStr == ''}"> 待财务通知</c:if>--%>
                                            <%--                                        </td>--%>
                                        </c:if>
                                        <c:if test="${menuCode == 'survey-list' || menuCode == 'org-review-list' || menuCode == 'assign-list' || menuCode == 'assign-org-list' || menuCode == 'task-user-review' || menuCode == 'help-review' || menuCode == 'time-track-list'}">
                                            <c:if test="${(menuCode == 'survey-list' && dto.lfCommit) || menuCode != 'survey-list'}">
                                                <td>
                                                    <c:if test="${menuCode == 'help-review' || menuCode == 'time-track-list'}">
                                                        <c:if test="${item.isSun == 0}">
                                                            <a href="javascript:void(0);"
                                                               onclick="sun(${item.id},'sign','lefan-help-ok',true)">标记阳性</a>&nbsp;&nbsp;
                                                        </c:if>
                                                        <c:if test="${item.isSun == 1}">
                                                            <a href="javascript:void(0);"
                                                               onclick="sun(${item.id},'sign','lefan-help-quxiao',true)">取消阳性</a>&nbsp;&nbsp;
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${(item.isCurOrg && menuCode != 'assign-list' && menuCode != 'assign-org-list') || (menuCode == 'survey-list' && (dto.surveyState == 22 || dto.surveyState == 30))}">
                                                        <c:if test="${menuCode == 'org-review-list' && dto.currentSurveyAssignOrg.orgSurveyState == 2 && item.surveyState == 4}">
                                                            <%--<a href="javascript:void(0);" onclick="operate(${item.id},'direction',false)">增加调查方向</a>&nbsp;&nbsp;--%>
                                                            <%--<a href="javascript:void(0);" onclick="showDirectionView(${item.id},this)">增加调查方向</a>&nbsp;&nbsp;--%>
                                                            <%--<div class='text' onclick="addDirectionNew(${item.id},'direction',false)" id="initOpen">增加调查方向 </div>--%>
                                                            <a href="javascript:void(0);"
                                                               onclick="addDirectionNew(${item.id},'direction',false, 1)"
                                                               id="initOpen">增加调查方向</a><br>
                                                            <c:if test="${menuCode != 'org-review-list'}">
                                                                <c:if test="${item.isSun == 0}">
                                                                    <a href="javascript:void(0);"
                                                                       onclick="sun(${item.id},'sign','lefan-ok',true)">标记阳性</a>&nbsp;&nbsp;
                                                                </c:if>
                                                                <c:if test="${item.isSun == 1}">
                                                                    <a href="javascript:void(0);"
                                                                       onclick="sun(${item.id},'sign','lefan-quxiao',true)">取消阳性</a>&nbsp;&nbsp;
                                                                </c:if>
                                                            </c:if>
                                                        </c:if>
                                                        <c:if test="${menuCode == 'org-review-list' && (item.surveyState == 6 || item.surveyState == 4) && dto.currentSurveyAssignOrg.orgSurveyState != 4}">
                                                            <a href="javascript:void(0);"
                                                               onclick="operate(${item.id},'orgReturn',false)">退回</a>&nbsp;&nbsp;
                                                        </c:if>
                                                        <c:if test="${menuCode != 'org-review-list' && item.surveyState != 2}">
                                                            <c:if test="${menuCode == 'survey-list' && (dto.surveyState == 22 || dto.surveyState == 30)}">
                                                                <%--<a href="javascript:void(0);" onclick="operate(${item.id},'direction',false)">增加调查方向</a>&nbsp;&nbsp;--%>
                                                                <%--<a href="javascript:void(0);" onclick="showDirectionView(${item.id},this)">增加调查方向</a>&nbsp;&nbsp;--%>
                                                                <a href="javascript:void(0);"
                                                                   onclick="addDirectionNew(${item.id},'direction',false, 2)"
                                                                   id="initOpen">增加调查方向</a>&nbsp;&nbsp;

                                                            </c:if>
                                                            <c:if test="${menuCode != 'survey-list'  && menuCode != 'help-review' && menuCode != 'time-track-list'}">
                                                                <%--<a href="javascript:void(0);" onclick="operate(${item.id},'direction',false)">增加调查方向</a>&nbsp;&nbsp;--%>
                                                                <%--<a href="javascript:void(0);" onclick="showDirectionView(${item.id},this)">增加调查方向</a>&nbsp;&nbsp;--%>
                                                                <div class='text'
                                                                     onclick="addDirectionNew(${item.id},'direction',false, 1)"
                                                                     id="initOpen">增加调查方向
                                                                </div>
                                                            </c:if>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${menuCode == 'survey-list' && dto.entrustReportEndDate == null}">
                                                        <%--<c:if test="${item.isSun == 0}">
                                                        <a href="javascript:void(0);" onclick="sun(${item.id},'sign','lefan-ok',true)">标记阳性</a>&nbsp;&nbsp;
                                                    </c:if>
                                                    <c:if test="${item.isSun == 1}">
                                                        <a href="javascript:void(0);" onclick="sun(${item.id},'sign','lefan-quxiao',true)">取消阳性</a>&nbsp;&nbsp;
                                                    </c:if>--%>
                                                        <%--                                                    <a href="javascript:void(0);" onclick="operate(${item.id},'punish',false)">处罚</a>&nbsp;&nbsp;--%>
                                                        <%--                                                    <c:if test="${item.scoreLevel == null || item.scoreLevel == ''}">--%>
                                                        <%--                                                        <a href="javascript:void(0);" onclick="operate(${item.id},'score',false)">评分</a>--%>
                                                        <%--                                                    </c:if>--%>
                                                        <%--                                                    <span style="color:red">--%>
                                                        <%--                                                        <c:if test="${item.scoreLevel == 1}">优质</c:if>--%>
                                                        <%--                                                        <c:if test="${item.scoreLevel == 2}">合格</c:if>--%>
                                                        <%--                                                        <c:if test="${item.scoreLevel == 3}">不合格</c:if>--%>
                                                        <%--                                                    </span>--%>
                                                    </c:if>
                                                    <c:if test="${menuCode == 'assign-list'}">
                                                        <%--                                                    <c:if test="${showNwPageEdit}">--%>
                                                        <a href="javascript:void(0);"
                                                           onclick="operate(${item.id},116,false)">改派</a>
                                                        <c:if test="${item.surveyState == 0 || item.surveyState == 1}">
                                                            <%--<a href="javascript:void(0);" onclick="operate(${item.id},'delCase',true)">删除</a>--%>
                                                            <a href="javascript:void(0);"
                                                               onclick="operate(${item.id},'delCase',false)">删除</a>
                                                        </c:if>
                                                        <%--                                                    </c:if>--%>
                                                    </c:if>
                                                    <c:if test="${item.isCurOrg && menuCode == 'assign-org-list' && scoreRole != 'areaManger'}">
                                                        <c:if test="${dataRoleCode != 'districtManger' && showNwPageEdit}">
                                                            <a href="javascript:void(0);"
                                                               onclick="operate(${item.id},116,false)">改派</a>
                                                            <c:if test="${item.surveyState == 0 || item.surveyState == 1}">
                                                                <%--<a href="javascript:void(0);" onclick="operate(${item.id},'delCase',true)">删除</a>--%>
                                                                <a href="javascript:void(0);"
                                                                   onclick="operate(${item.id},'delCase',false)">删除</a>
                                                            </c:if>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${item.isCurOrg && menuCode == 'task-user-review' && item.surveyState == 4}">
                                                        <c:if test="${item.reviewOff == 0}">
                                                            <a href="javascript:void(0);"
                                                               onclick="operate(${item.id},'review-user-yes',true)">预审通过</a>&nbsp;&nbsp;
                                                        </c:if>
                                                        <c:if test="${item.reviewOff == 1}">
                                                            <a href="javascript:void(0);"
                                                               onclick="operate(${item.id},'review-user-cancel',true)">取消预审通过</a>&nbsp;&nbsp;
                                                        </c:if>
                                                        <a href="javascript:void(0);"
                                                           onclick="operate(${item.id},'orgReturn',false)">退回</a>&nbsp;&nbsp;
                                                    </c:if>
                                                </td>
                                            </c:if>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </form>
            </c:if>
            <c:if test="${menuCode == 'my-list' || menuCode == 'survey-list' || menuCode == 'make-report-list'
             || menuCode == 'org-review-list' || menuCode == 'entrust-list' || menuCode == 'all-list'
             || menuCode == 'time-track-list' || menuCode == 'task-user-review' || menuCode == 'task-org-review' || menuCode == 'help-review'
             || menuCode == 'visit-list' || menuCode == 'feeViewManager' || menuCode == 'assign-org-list'}">
                <form id="editFormDirection" role="form" action="${ctx}/survey/case/sic/operate" method="post">
                    <input type="hidden" value="updPricedirection" name="btnCode"/>
                    <input type="hidden" value="" name="directionId" id="directionId"/>
                    <input type="hidden" value="" name="updType" id="updType"/>
                    <div class="info info-1129">
                        <div class="info-title">调查方向列表</div>
                        <div class="info-content">
                            <c:if test="${dto.reportCompletion != null}">
                                <div id="div_report_completion_1">
                                    <div style="color: red;font-size:16px;font-weight:bold">
                                        报告结论(${dto.reportCompletionOrgName})：${dto.reportCompletion}
                                        <c:if test="${dto.entrustReportEndDate == null}">
                                            <a onclick="updMoney(-1,20)"><img height="25px" width="25px"
                                                                              src="${ctx}/img/pen.png"></a>
                                        </c:if>
                                        <div class="record" data-attr="survey_risk_case_info_report_completion"><span
                                                class="icon-history"></span><span class="num"></span></div>
                                    </div>
                                </div>
                                <div id="div_report_completion_2" style="display: none">
                                    报告结论：<textarea name="reportCompletion" style="height: 130px;width: 600px;"
                                                       class="form-control">${dto.reportCompletion}</textarea>
                                    <input type="submit" value="确定" onclick="return directionOK(${dto.id},20)"
                                           class="btn" style="display: inline-flex;margin-top: 10px;"/>
                                </div>
                            </c:if>
                            <div class="lf-btn lf-btn-active updateOrder">调整方向顺序</div>
                            <div class="lf-btn lf-btn-active updateSubmit">保存</div>
                            <div class="lf-btn updateCancel">取消</div>
                            <table class="table" id="tablePro" border="0" cellpadding="0" cellspacing="0"
                                   style="text-align: left;">
                                <thead>
                                <th>序号</th>
                                <c:if test="${menuCode != 'entrust-list' && menuCode != 'my-list' && menuCode != 'credit-list'}">
                                    <th>调查员</th>
                                    <th width="8%">机构</th>
                                </c:if>
                                    <%--<c:if test="${menuCode == 'org-review-list'}">--%>
                                    <%--<th width="6%">调查人</th>--%>
                                    <%--</c:if>--%>
                                <th width="8%">区域</th>
                                <th>任务类型</th>
                                <th>任务子类</th>
                                <th>方向结果</th>
                                <c:if test="${menuCode == 'survey-list' || menuCode == 'all-list' || menuCode == 'org-review-list' || menuCode == 'help-review' || menuCode == 'assign-org-list'}">
                                    <th>分值</th>
                                </c:if>
                                <th width="8%">方向名称</th>
                                <c:if test="${dto.surveyConsignor.orgAttr == 2}">
                                    <th width="15%">方向概述</th>
                                </c:if>
                                <th>方向内容</th>
                                    <%--                            <c:if test="${!dto.help}">--%>
                                <c:if test="${menuCode == 'survey-list' || (menuCode == 'org-review-list' && dto.entrustReportStartDate != null) || menuCode == 'all-list'}">
                                    <th width="10%">调查方价格</th>
                                    <c:if test="${menuCode == 'survey-list' || menuCode == 'all-list'}">
                                        <th width="10%">委托方价格</th>
                                    </c:if>
                                </c:if>
                                <th>是否阳性</th>
                                    <%--                            </c:if>--%>
                                <c:if test="${menuCode == 'org-review-list' || menuCode == 'all-list' || menuCode == 'survey-list' || menuCode == 'help-review' || menuCode == 'assign-org-list'}">
                                    <th>渠道费用</th>
                                </c:if>
                                    <%--                            <c:if test="${dto.showExpenseReimbursementValue}"><th width="5%">费用报销</th></c:if>--%>
                                <th width="5%">附件</th>
                                <th width="5%">评价</th>
                                <c:if test="${menuCode == 'org-review-list' || menuCode == 'survey-list' || menuCode == 'help-review'}">
                                    <th width="8%">操作</th>
                                </c:if>
                                </thead>
                                <tbody>
                                <c:forEach items="${dto.surveyCaseDirections}" var="item" varStatus="status">
                                    <tr id="${item.id}"
                                        <c:if test="${item.invalidState ==1}">style="background-color: rgba(0, 0, 0, 0.07)!IMPORTANT;"</c:if>
                                        <c:if test="${item.reviewOff ==1 && menuCode == 'survey-list' && (dto.surveyState == 22 || dto.surveyState == 30)}">style="background-color: #faf2cc;"</c:if>
                                        <c:if test="${(menuCode == 'task-user-review' || menuCode == 'task-org-review' || menuCode == 'survey-list' || menuCode == 'all-list' || menuCode == 'org-review-list') && item.surveyInvestigatorCase.reviewOff == 1}">bgcolor="#f5f5dc" </c:if>
                                    >
                                        <td>${status.count}</td>
                                        <c:if test="${menuCode != 'entrust-list' && menuCode != 'my-list' && menuCode != 'credit-list'}">
                                            <td>${item.surveyInvestigatorCase.surveyUserName}</td>
                                            <td>${item.surveyInvestigatorCase.surveyFranchisee.name}</td>
                                        </c:if>
                                            <%--<c:if test="${menuCode == 'org-review-list'}">--%>
                                            <%--<td>${item.surveyInvestigatorCase.surveyUserName}</td>--%>
                                            <%--</c:if>--%>
                                        <td>${item.areaName}</td>
                                        <td>${item.taskName}</td>
                                        <td>${item.newName}</td>
                                        <td>
                                                ${item.directionResultTypeName}
                                            <c:if test="${item.medicalNumber >1}">（病历数量：${item.medicalNumber}份）</c:if>
                                            (${item.accScore})
                                        </td>
                                        <c:if test="${menuCode == 'survey-list' || menuCode == 'all-list' || menuCode == 'org-review-list' || menuCode == 'help-review' || menuCode == 'assign-org-list'}">
                                            <td>
                                                <div id="div_score1${item.id}">${item.score}
                                                        <%--                                                <c:if test="${item.review}">--%>
                                                        <%--                                                    <a onclick="updMoney(${item.id},3)"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a>--%>
                                                        <%--                                                </c:if>--%>
                                                    <c:if test="${menuCode != 'org-review-list' && menuCode != 'assign-org-list' || maxRole || (scoreUpd && dto.lefanReportDate == null)}">
                                                        <a onclick="updMoney(${item.id},3)"><img height="25px"
                                                                                                 width="25px"
                                                                                                 src="${ctx}/img/pen.png"></a>
                                                        <div class="record"
                                                             data-attr="survey_case_direction_score${item.id}"><span
                                                                class="icon-history"></span><span class="num"></span>
                                                        </div>
                                                    </c:if>
                                                </div>
                                                <div id="div_score2${item.id}" style="display: none;">
                                                    原始分值：<input id="old_score_${item.id}"
                                                                    onchange="javascript:$('#score_${item.id}').val(($(this).val() * $('#score_rate_${item.id}').val()).toFixed(2))"
                                                                    type="number" class="input-2"
                                                                    style="display: inline-flex;" step="0.01"
                                                                    value='<fmt:formatNumber value="${item.score / item.scoreRate}" pattern="0.00"/>'/><br/>
                                                    区域系数：<input id="score_rate_${item.id}" type="number"
                                                                    class="input-2"
                                                                    style="display: inline-flex; background-color: #a6a6a6 "
                                                                    step="0.01" value="${item.scoreRate}"
                                                                    readonly/><br/>
                                                    最终分值：<input id="score_${item.id}" type="number" class="input-2"
                                                                    style="display: inline-flex;; background-color: #a6a6a6"
                                                                    step="0.01" value="${item.score}"
                                                                    name="score${item.id}" readonly/><br/>
                                                    更改备注：<textarea style="width: 100%;" name="scoreRemark${item.id}"
                                                                       rows="8" cols="8" class="form-control"
                                                                       placeholder="备注"></textarea>
                                                    <input type="submit" value="确定"
                                                           onclick="return directionOK(${item.id},3)" class="btn"
                                                           style="display: inline-flex;margin-top: 10px;"/>
                                                </div>

                                            </td>
                                        </c:if>
                                        <td><c:if test="${item.invalidState == 1}"><span
                                                style="color: red;display: inline">【无效方向】</span></c:if>
                                            <c:if test="${item.orgPoint != 1}">${item.directionName}</c:if>
                                            <c:if test="${item.orgPoint == 1}"><span
                                                    style="color: red;font-weight: bold">${item.directionName}</span></c:if>
                                        </td>
                                        <c:if test="${dto.surveyConsignor.orgAttr == 2}">
                                            <td>
                                                <pre style="font-family: 'lucida Grande',Verdana,'Microsoft YaHei';background-color:rgba(0, 0, 0, 0);border:dashed 0px #fff; font-size: 12px;white-space: pre-wrap; word-wrap: break-word;padding: 0px;">${item.huzhuColsRemark}</pre>
                                            </td>
                                        </c:if>
                                        <td>
                                            <div id="div_directionText1${item.id}">
                                                <pre style="font-family: 'lucida Grande',Verdana,'Microsoft YaHei';background-color:rgba(0, 0, 0, 0);border:dashed 0px #fff; font-size: 12px;white-space: pre-wrap; word-wrap: break-word;padding: 0px;">${item.directionText}</pre>
                                                <c:if test="${dto.lfCommit && (dto.surveyState == 22 || dto.surveyState == 30) && menuCode != 'all-list'  || maxRole}">
                                                    <a onclick="updMoney(${item.id},21)"><img height="25px" width="25px"
                                                                                              src="${ctx}/img/pen.png"></a>
                                                    <div class="record"
                                                         data-attr="survey_case_direction_text${item.id}"><span
                                                            class="icon-history"></span><span class="num"></span></div>
                                                </c:if>
                                            </div>
                                            <div id="div_directionText2${item.id}" style="display: none;">
                                                <textarea name="directionText_${item.id}" id="directionText_${item.id}"
                                                          style="height: 250px"
                                                          class="form-control">${item.directionText}</textarea>
                                                <input type="submit" value="确定"
                                                       onclick="return directionOK(${item.id},21)" class="btn"
                                                       style="display: inline-flex;margin-top: 10px;"/>
                                            </div>
                                        </td>

                                            <%--                                    <c:if test="${!dto.help}">--%>
                                            <%--                                        <c:if test="${menuCode == 'survey-list' || (menuCode == 'org-review-list' && dto.entrustReportStartDate != null) || (menuCode == 'all-list' && !dto.help)}">--%>
                                        <c:if test="${menuCode == 'survey-list' || (menuCode == 'org-review-list' && dto.entrustReportStartDate != null) || menuCode == 'all-list'}">
                                            <td>
                                                    <%--<input type="hidden" value="${item.surveyInvestigatorCaseId}" name="id" />--%>
                                                <div id="div_survey_money1${item.id}">
                                                    <c:if test="${menuCode == 'org-review-list'}">
                                                        <c:if test="${item.surveyInvestigatorCase.isCurOrg}">
                                                            ${item.surveyMoney}
                                                        </c:if>
                                                        <c:if test="${!item.surveyInvestigatorCase.isCurOrg}">
                                                            ***
                                                        </c:if>
                                                    </c:if>
                                                        <%--<c:if test="${menuCode != 'org-review-list'}">--%>
                                                        <%--${item.surveyMoney}--%>
                                                        <%--</c:if>--%>
                                                    <c:if test="${menuCode == 'survey-list' || menuCode == 'all-list'}">
                                                        <span
                                                                <c:if test="${item.surveyPriceSource == 3}">style="color: red;" </c:if> >${item.surveyMoney}</span>
                                                        <c:if test="${dto.lfCommit && (dto.surveyState == 22 || dto.surveyState == 30) && menuCode != 'all-list'  || maxRole}">
                                                            <a onclick="updMoney(${item.id},1)"><img height="25px"
                                                                                                     width="25px"
                                                                                                     src="${ctx}/img/pen.png"></a>
                                                        </c:if>
                                                        <c:if test="${item.surveyPriceSource == 3}">
                                                            <c:if test="${item.surveyMoneyRemark != null}">
                                                                (<a data-value="${item.surveyMoneyRemark}" onclick="showMoneyRemark(2,this)" title="${item.surveyMoneyRemark}" style="cursor: pointer;color: red;text-decoration: underline;">价格说明</a>)
                                                            </c:if>
                                                            <br/><span
                                                                style="text-decoration: line-through;">${item.oldSurveyMoney}</span>
                                                            </span>
                                                        </c:if>
                                                        <div class="record"
                                                             data-attr="survey_case_direction_survey_money${item.id}">
                                                            <span class="icon-history"></span><span class="num"></span>
                                                        </div>
                                                    </c:if>
                                                </div>
                                                <div id="div_survey_money2${item.id}" style="display: none;">
                                                    价格：<input type="number" class="input-2"
                                                                style="display: inline-flex;" step="0.01"
                                                                value="${item.surveyMoney}"
                                                                name="surveyMoney${item.id}"/>
                                                    价格说明：<textarea style="width: 225px;height: 70px;"
                                                                       name="surveyMoneyRemark${item.id}">${item.surveyMoneyRemark}</textarea>
                                                    <input type="submit" value="确定"
                                                           onclick="return directionOK(${item.id},1,${item.surveyMoney})"
                                                           class="btn" style="display: inline-flex;margin-top: 10px;"/>
                                                </div>
                                            </td>
                                            <c:if test="${menuCode == 'survey-list' || menuCode == 'all-list'}">
                                                <td>
                                                    <div id="div_entrust_money1${item.id}">
                                                        <span
                                                                <c:if test="${item.entrustPriceSource == 3}">style="color: red;" </c:if> >${item.entrustMoney}</span>
                                                        <c:if test="${dto.lfCommit && (dto.surveyState == 22 || dto.surveyState == 30) && menuCode != 'all-list'  || maxRole}">
                                                            <a onclick="updMoney(${item.id},2)"><img height="25px"
                                                                                                     width="25px"
                                                                                                     src="${ctx}/img/pen.png"></a>
                                                        </c:if>
                                                        <c:if test="${item.entrustPriceSource == 3}">
                                                            <c:if test="${item.entrustMoneyRemark != null}">
                                                                (<a data-value="${item.entrustMoneyRemark}" onclick="showMoneyRemark(2,this)" title="${item.entrustMoneyRemark}" style="cursor: pointer;color: red;text-decoration: underline;">价格说明</a>)
                                                            </c:if>
                                                            <br/><span
                                                                style="text-decoration: line-through;">${item.oldEntrustMoney}</span>
                                                        </c:if>
                                                        <div class="record"
                                                             data-attr="survey_case_direction_entrust_money${item.id}">
                                                            <span class="icon-history"></span><span class="num"></span>
                                                        </div>
                                                    </div>
                                                    <div id="div_entrust_money2${item.id}" style="display: none;">
                                                        价格：<input type="number" class="input-2"
                                                                    style="display: inline-flex;" step="0.01"
                                                                    value="${item.entrustMoney}"
                                                                    name="entrustMoney${item.id}"/>
                                                        价格说明：<textarea style="width: 225px;height: 70px;"
                                                                           name="entrustMoneyRemark${item.id}">${item.entrustMoneyRemark}</textarea>
                                                        <input type="submit" value="确定"
                                                               onclick="return directionOK(${item.id},2,${item.entrustMoney})"
                                                               class="btn"
                                                               style="display: inline-flex;margin-top: 10px;"/>
                                                    </div>
                                                </td>
                                            </c:if>
                                            <%--                                        </c:if>--%>

                                        </c:if>
                                        <td>
                                            <c:if test="${item.sun == 0}">否</c:if>
                                            <c:if test="${item.sun == 1}"><span style="color: red;">是</span></c:if>
                                        </td>
                                        <c:if test="${menuCode == 'org-review-list' || menuCode == 'all-list' || menuCode == 'survey-list' || menuCode == 'help-review' || menuCode == 'assign-org-list'}">
                                            <td>
                                                <div id="div_channelFee1${item.id}">
                                                    <c:if test="${menuCode == 'org-review-list'}">
                                                        <c:if test="${!item.surveyInvestigatorCase.isCurOrg}">***</c:if>
                                                        <c:if test="${item.surveyInvestigatorCase.isCurOrg}">${item.channelFeeCur}</c:if>
                                                    </c:if>
                                                    <c:if test="${menuCode != 'org-review-list'}">
                                                        ${item.channelFeeCur}
                                                    </c:if>
                                                    <c:if test="${item.channelType == 1}">
                                                        <%--                                                    标记为渠道费用。且渠道费用为到财务之前都可以修改--%>
                                                        <c:if test="${(item.surveyChannelCostNew == null || item.surveyChannelCostNew.isProPay == 0) && menuCode == 'org-review-list' || maxRole}">
                                                            <c:if test="${maxRole || (dto.currentSurveyAssignOrg.reportDate == null && item.surveyInvestigatorCase.isCurOrg)}">
                                                                <a onclick="updMoney(${item.id},6)"><img height="25px"
                                                                                                         width="25px"
                                                                                                         src="${ctx}/img/pen.png"></a>
                                                            </c:if>
                                                        </c:if>
                                                    </c:if>
                                                </div>
                                                <div id="div_channelFee2${item.id}" style="display: none;">
                                                    <input type="number" class="input-2" style="display: inline-flex;"
                                                           step="0.01" value="${item.channelFeeCur}" min="0"
                                                           name="channelFee${item.id}"/>
                                                    <input type="hidden" value="${item.channelFeeSent}"
                                                           name="channelSent${item.id}"/>
                                                    <input type="submit" value="确定"
                                                           onclick="return directionOK(${item.id},6)" class="btn"
                                                           style="display: inline-flex;margin-top: 10px;"/>
                                                </div>
                                                <c:if test="${menuCode == 'org-review-list'}">
                                                    <c:if test="${item.surveyInvestigatorCase.isCurOrg}">
                                                        <div class="record"
                                                             data-attr="survey_case_direction_channel_fee_cur${item.id}">
                                                            <span class="icon-history"></span><span class="num"></span>
                                                        </div>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${menuCode != 'org-review-list'}">
                                                    <div class="record"
                                                         data-attr="survey_case_direction_channel_fee_cur${item.id}">
                                                        <span class="icon-history"></span><span class="num"></span>
                                                    </div>
                                                </c:if>
                                            </td>
                                        </c:if>
                                        <td>
                                            <a href="javascript:void(0);"
                                               onclick="directionFiles(${item.id},'directionFiles','${dto.surveyCno}')">详情(${item.directionFilesSize})</a>
                                            <c:if test="${menuCode == 'survey-list' && item.directionFilesSize > 0}">
                                                <br><a href="javascript:void(0);"
                                                       onclick="downFileDirectionFile('${item.id}','${item.directionName}','${dto.surveyCno}')">下载</a>
                                            </c:if>
                                            <c:if test="${menuCode == 'org-review-list' && item.surveyInvestigatorCase.isCurOrg}">
                                                <br><a href="javascript:void(0);"
                                                       onclick="downFileDirectionFile('${item.id}','${item.directionName}','${dto.surveyCno}')">下载</a>
                                            </c:if>
                                            <br/> <c:if test="${item.materRaw == 1}"><span
                                                style="color: #ff0000">有原件</span></c:if>
                                        </td>
                                        <td>
                                            <c:if test="${menuCode == 'org-review-list' || (menuCode == 'survey-list' && menuCode2 != 'agent-entrust-list') || menuCode == 'help-review'}">
                                                <div class="show-evaluate-one" data-direction-id="${item.id}"
                                                     data-evaluate="${item.evaluate}"
                                                     <c:if test="${menuCode == 'org-review-list' && !item.surveyInvestigatorCase.isCurOrg}">data-huise="yes"</c:if> >
                                                    <span data-item-id="${item.id}"
                                                          onclick="directionOK(${item.id},'you')" data-value="2"
                                                          class="span-evaluate"
                                                          style="color: green;padding: 2px;border: 1px solid green;">优</span>
                                                    <span data-item-id="${item.id}"
                                                          onclick="directionOK(${item.id},'hege')" data-value="1"
                                                          class="span-evaluate"
                                                          style="color: #3BA9FF;padding: 2px;border: 1px solid #3BA9FF;">合格</span>
                                                    <span data-item-id="${item.id}"
                                                          onclick="directionOK(${item.id},'cha')" data-value="3"
                                                          class="span-evaluate"
                                                          style="color: red;padding: 2px;border: 1px solid red;">差</span>
                                                </div>
                                            </c:if>
                                            <c:if test="${!(menuCode == 'org-review-list' || (menuCode == 'survey-list' && menuCode2 != 'agent-entrust-list') || menuCode == 'help-review')}">
                                                <c:if test="${item.evaluate == null || item.evaluate == 1}"><span
                                                        style="color: #3BA9FF">合格</span></c:if>
                                                <c:if test="${item.evaluate == 2}"><span
                                                        style="color: green;">优</span></c:if>
                                                <c:if test="${item.evaluate == 3}"><span
                                                        style="color: red;">差</span></c:if>
                                            </c:if>
                                            <div class="record" data-attr="survey_case_direction_evaluate${item.id}">
                                                <span class="icon-history"></span><span class="num"></span></div>
                                        </td>
                                        <c:if test="${menuCode == 'org-review-list' || menuCode == 'survey-list' || menuCode == 'help-review'}">
                                            <td>
                                                <c:if test="${(item.surveyInvestigatorCase.isCurOrg && dto.currentSurveyAssignOrg.orgSurveyState == 2)
                                || (menuCode == 'survey-list' && (dto.surveyState == 22 || dto.surveyState == 30)) || menuCode == 'help-review'}">
                                                    <c:if test="${item.review || menuCode == 'org-review-list'}">
                                                        <a href="javascript:void(0);"
                                                           onclick="delDirection(${item.surveyInvestigatorCase.id},${item.id},'deldirection',true)">删除</a>
                                                        <a href="javascript:void(0);"
                                                           onclick="updDirection(${item.surveyInvestigatorCase.id},${item.id},'updDirection',false,'${menuCode}')">修改</a>
                                                        <c:if test="${menuCode == 'survey-list' || menuCode == 'help-review'}">
                                                            <br/><c:if test="${item.reviewOff == 0}"><a
                                                                style="cursor: pointer;"
                                                                onclick="directionOK(${item.id},4)">标记已审核</a></c:if>
                                                            <c:if test="${item.reviewOff == 1}"><a
                                                                    style="cursor: pointer;"
                                                                    onclick="directionOK(${item.id},5)">取消已审核</a></c:if>

                                                            <br/><c:if test="${item.invalidState == 0}"><a
                                                                style="cursor: pointer;"
                                                                onclick="directionOK(${item.id},7)">标记为无效方向</a></c:if>
                                                            <c:if test="${item.invalidState == 1}"><a
                                                                    style="cursor: pointer;"
                                                                    onclick="directionOK(${item.id},8)">取消无效方向</a></c:if>
                                                        </c:if>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${!dto.help && (menuCode == 'survey-list' || menuCode == 'agent-entrust-list')}">
                                                    <br/><c:if test="${item.sun == 0}"><a style="cursor: pointer;"
                                                                                          onclick="directionOK(${item.id},9)">标记阳性</a></c:if>
                                                    <c:if test="${item.sun == 1}"><a style="cursor: pointer;"
                                                                                     onclick="directionOK(${item.id},10)">取消阳性</a></c:if>
                                                </c:if>
                                                <c:if test="${item.surveyInvestigatorCase.isCurOrg && !dto.help && (menuCode == 'org-review-list') && dto.currentSurveyAssignOrg.reportDate == null}">
                                                    <br/><c:if test="${item.sun == 0}"><a style="cursor: pointer;"
                                                                                          onclick="directionOK(${item.id},9)">标记阳性</a></c:if>
                                                    <c:if test="${item.sun == 1}"><a style="cursor: pointer;"
                                                                                     onclick="directionOK(${item.id},10)">取消阳性</a></c:if>
                                                    <c:if test="${dto.entrustOrgId == 94}">
                                                        <br/>
                                                        <c:if test="${item.orgPoint == 0}">
                                                            <a href="javascript:void(0);"
                                                               onclick="oprDirection(${item.surveyInvestigatorCase.id},${item.id},'signOrgPoint',true)">标记指定方向</a>
                                                        </c:if>
                                                        <c:if test="${item.orgPoint == 1}">
                                                            <a href="javascript:void(0);"
                                                               onclick="oprDirection(${item.surveyInvestigatorCase.id},${item.id},'reOrgPoint',true)">取消指定方向</a>
                                                        </c:if>
                                                    </c:if>
                                                </c:if>
                                                    <%--                                            <c:if test="${menuCode == 'org-review-list' && dto.currentSurveyAssignOrg.reportDate == null && item.surveyInvestigatorCase.isCurOrg || maxRole}">--%>
                                                    <%--                                                <c:if test="${item.channelType == 0}"><a style="cursor: pointer;" onclick="directionOK(${item.id},15)"><br/>标记为渠道费用</a></c:if>--%>
                                                    <%--                                                <c:if test="${item.channelType == 1}"><a style="cursor: pointer;" onclick="directionOK(${item.id},16)"><br/>取消标记为渠道费用</a></c:if>--%>
                                                    <%--                                            </c:if>--%>
                                            </td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <table class="table" id="tableCopy" border="0" cellpadding="0" cellspacing="0"
                                   style="text-align: left;">
                                <thead>
                                <th>序号</th>
                                <c:if test="${menuCode != 'entrust-list' && menuCode != 'my-list' && menuCode != 'credit-list'}">
                                    <th>调查员</th>
                                    <th width="8%">机构</th>
                                </c:if>
                                    <%--<c:if test="${menuCode == 'org-review-list'}">--%>
                                    <%--<th width="6%">调查人</th>--%>
                                    <%--</c:if>--%>
                                <th width="8%">区域</th>
                                <th>任务类型</th>
                                <th>任务子类</th>
                                <th>方向结果</th>
                                <c:if test="${menuCode == 'survey-list' || menuCode == 'all-list' || menuCode == 'org-review-list' || menuCode == 'help-review'}">
                                    <th>分值</th>
                                </c:if>
                                <th width="8%">方向名称</th>
                                <c:if test="${dto.surveyConsignor.orgAttr == 2}">
                                    <th width="15%">方向概述</th>
                                </c:if>
                                <th>方向内容</th>
                                <c:if test="${!dto.help}">
                                    <c:if test="${menuCode == 'survey-list' || (menuCode == 'org-review-list' && dto.entrustReportStartDate != null) || menuCode == 'all-list'}">
                                        <th width="10%">调查方价格</th>
                                        <c:if test="${menuCode == 'survey-list' || menuCode == 'all-list'}">
                                            <th width="10%">委托方价格</th>
                                        </c:if>
                                    </c:if>
                                </c:if>
                                <c:if test="${menuCode == 'org-review-list'}">
                                    <th>渠道费用</th>
                                </c:if>

                                    <%--                            <c:if test="${dto.showExpenseReimbursementValue}"><th width="5%">费用报销</th></c:if>--%>
                                <th width="5%">附件</th>
                                <c:if test="${menuCode == 'org-review-list' || menuCode == 'survey-list' || menuCode == 'help-review'}">
                                    <th width="8%">操作</th>
                                </c:if>
                                </thead>
                                <tbody>
                                <c:forEach items="${dto.surveyCaseDirections}" var="item" varStatus="status">
                                    <tr id="${item.id}"
                                        <c:if test="${item.reviewOff ==1 && menuCode == 'survey-list' && (dto.surveyState == 22 || dto.surveyState == 30)}">style="background-color: #faf2cc;"</c:if>
                                        <c:if test="${(menuCode == 'task-user-review' || menuCode == 'task-org-review' || menuCode == 'survey-list' || menuCode == 'all-list' || menuCode == 'org-review-list') && item.surveyInvestigatorCase.reviewOff == 1}">bgcolor="#f5f5dc" </c:if>
                                    >
                                        <td>${status.count}</td>
                                        <c:if test="${menuCode != 'entrust-list' && menuCode != 'my-list' && menuCode != 'credit-list'}">
                                            <td>${item.surveyInvestigatorCase.surveyUserName}</td>
                                            <td>${item.surveyInvestigatorCase.surveyFranchisee.name}</td>
                                        </c:if>
                                            <%--<c:if test="${menuCode == 'org-review-list'}">--%>
                                            <%--<td>${item.surveyInvestigatorCase.surveyUserName}</td>--%>
                                            <%--</c:if>--%>
                                        <td>${item.areaName}</td>
                                        <td>${item.taskName}</td>
                                        <td>${item.newName}</td>
                                        <td>
                                                ${item.directionResultTypeName}
                                            <c:if test="${item.medicalNumber >1}">（病历数量：${item.medicalNumber}份）</c:if>
                                        </td>
                                        <c:if test="${menuCode == 'survey-list' || menuCode == 'all-list' || menuCode == 'org-review-list' || menuCode == 'help-review'}">
                                            <td>
                                                <div id="div_score1${item.id}">${item.score}
                                                        <%--                                                <c:if test="${item.review}">--%>
                                                        <%--                                                    <a onclick="updMoney(${item.id},3)"><img height="25px" width="25px" src="${ctx}/img/pen.png"></a>--%>
                                                        <%--                                                </c:if>--%>
                                                    <c:if test="${menuCode != 'org-review-list'  || maxRole}">
                                                        <a onclick="updMoney(${item.id},3)"><img height="25px"
                                                                                                 width="25px"
                                                                                                 src="${ctx}/img/pen.png"></a>
                                                    </c:if>
                                                </div>
                                                <div id="div_score2${item.id}" style="display: none;">
                                                    <input type="number" class="input-2" style="display: inline-flex;"
                                                           step="0.01" value="${item.score}" name="score${item.id}"/>
                                                    <input type="submit" value="确定"
                                                           onclick="return directionOK(${item.id},3)" class="btn"
                                                           style="display: inline-flex;margin-top: 10px;"/>
                                                </div>

                                            </td>
                                        </c:if>
                                        <td>${item.directionName}</td>
                                        <c:if test="${dto.surveyConsignor.orgAttr == 2}">
                                            <td>
                                                <pre style="font-family: 'lucida Grande',Verdana,'Microsoft YaHei';background-color:rgba(0, 0, 0, 0);border:dashed 0px #fff; font-size: 12px;white-space: pre-wrap; word-wrap: break-word;padding: 0px;">${item.huzhuColsRemark}</pre>
                                            </td>
                                        </c:if>
                                        <td>
                                            <pre style="font-family: 'lucida Grande',Verdana,'Microsoft YaHei';background-color:rgba(0, 0, 0, 0);border:dashed 0px #fff; font-size: 12px;white-space: pre-wrap; word-wrap: break-word;padding: 0px;">${item.directionText}</pre>
                                        </td>

                                        <c:if test="${!dto.help}">
                                            <%--                                        <c:if test="${menuCode == 'survey-list' || (menuCode == 'org-review-list' && dto.entrustReportStartDate != null) || (menuCode == 'all-list' && !dto.help)}">--%>
                                            <c:if test="${menuCode == 'survey-list' || (menuCode == 'org-review-list' && dto.entrustReportStartDate != null) || menuCode == 'all-list'}">
                                                <td>
                                                        <%--<input type="hidden" value="${item.surveyInvestigatorCaseId}" name="id" />--%>
                                                    <div id="div_survey_money1${item.id}">
                                                        <c:if test="${menuCode == 'org-review-list'}">
                                                            <c:if test="${item.surveyInvestigatorCase.isCurOrg}">
                                                                ${item.surveyMoney}
                                                            </c:if>
                                                            <c:if test="${!item.surveyInvestigatorCase.isCurOrg}">
                                                                ***
                                                            </c:if>
                                                        </c:if>
                                                            <%--<c:if test="${menuCode != 'org-review-list'}">--%>
                                                            <%--${item.surveyMoney}--%>
                                                            <%--</c:if>--%>
                                                        <c:if test="${menuCode == 'survey-list' || (menuCode == 'all-list' && !dto.help)}">
                                                            <c:if test="${item.surveyPriceSource != 3}">
                                                                ${item.surveyMoney}
                                                            </c:if>
                                                            <c:if test="${item.surveyPriceSource == 3}">
                                                                <%--                                                            <span style="text-decoration: line-through;">${item.oldSurveyMoney}(修改价格)</span>--%>
                                                                <br/><span style="color: red;">${item.surveyMoney}(<a
                                                                    data-value="${item.surveyMoneyRemark}"
                                                                    onclick="showMoneyRemark(2,this)"
                                                                    title="${item.surveyMoneyRemark}"
                                                                    style="cursor: pointer;color: red;text-decoration: underline;">价格说明</a>)</span>
                                                            </c:if>
                                                            <c:if test="${dto.lfCommit && (dto.surveyState == 22 || dto.surveyState == 30) && menuCode != 'all-list'  || maxRole}">
                                                                <a onclick="updMoney(${item.id},1)"><img height="25px"
                                                                                                         width="25px"
                                                                                                         src="${ctx}/img/pen.png"></a>
                                                            </c:if>
                                                        </c:if>
                                                    </div>
                                                    <div id="div_survey_money2${item.id}" style="display: none;">
                                                        价格：<input type="number" class="input-2"
                                                                    style="display: inline-flex;" step="0.01"
                                                                    value="${item.surveyMoney}"
                                                                    name="surveyMoney${item.id}"/>
                                                        价格说明：<textarea style="width: 225px;height: 70px;"
                                                                           name="surveyMoneyRemark${item.id}">${item.surveyMoneyRemark}</textarea>
                                                        <input type="submit" value="确定"
                                                               onclick="return directionOK(${item.id},1,${item.surveyMoney})"
                                                               class="btn"
                                                               style="display: inline-flex;margin-top: 10px;"/>
                                                    </div>
                                                </td>
                                                <c:if test="${menuCode == 'survey-list' || (menuCode == 'all-list' && !dto.help)}">
                                                    <td>
                                                            <%--<input type="hidden" value="${item.surveyInvestigatorCaseId}" name="id" />--%>
                                                        <div id="div_entrust_money1${item.id}">
                                                            <c:if test="${item.surveyPriceSource != 3}">
                                                                ${item.entrustMoney}
                                                            </c:if>

                                                            <c:if test="${item.entrustPriceSource == 3}">
                                                                <%--                                                            <span style="text-decoration: line-through;">${item.oldEntrustMoney}(修改价格)</span>--%>
                                                                <br/><span style="color: red;">${item.entrustMoney}(<a
                                                                    data-value="${item.entrustMoneyRemark}"
                                                                    onclick="showMoneyRemark(2,this)"
                                                                    title="${item.entrustMoneyRemark}"
                                                                    style="cursor: pointer;color: red;text-decoration: underline;">价格说明</a>)</span>
                                                            </c:if>
                                                            <c:if test="${dto.lfCommit && (dto.surveyState == 22 || dto.surveyState == 30) && menuCode != 'all-list'  || maxRole}">
                                                                <a onclick="updMoney(${item.id},2)"><img height="25px"
                                                                                                         width="25px"
                                                                                                         src="${ctx}/img/pen.png"></a>
                                                            </c:if>
                                                        </div>
                                                        <div id="div_entrust_money2${item.id}" style="display: none;">
                                                            价格：<input type="number" class="input-2"
                                                                        style="display: inline-flex;" step="0.01"
                                                                        value="${item.entrustMoney}"
                                                                        name="entrustMoney${item.id}"/>
                                                            价格说明：<textarea style="width: 225px;height: 70px;"
                                                                               name="entrustMoneyRemark${item.id}">${item.entrustMoneyRemark}</textarea>
                                                            <input type="submit" value="确定"
                                                                   onclick="return directionOK(${item.id},2,${item.entrustMoney})"
                                                                   class="btn"
                                                                   style="display: inline-flex;margin-top: 10px;"/>
                                                        </div>
                                                    </td>
                                                </c:if>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${menuCode == 'org-review-list'}">
                                            <td>
                                                <div id="div_channelFee1${item.id}">${item.channelFeeCur}
                                                    <c:if test="${item.channelType == 1}">
                                                        <a onclick="updMoney(${item.id},6)"><img height="25px"
                                                                                                 width="25px"
                                                                                                 src="${ctx}/img/pen.png"></a>
                                                    </c:if>
                                                </div>
                                                <div id="div_channelFee2${item.id}" style="display: none;">
                                                    <input type="number" class="input-2" style="display: inline-flex;"
                                                           step="0.01" value="${item.channelFeeCur}" min="0"
                                                           name="channelFee${item.id}"/>
                                                    <input type="submit" value="确定"
                                                           onclick="return directionOK(${item.id},6)" class="btn"
                                                           style="display: inline-flex;margin-top: 10px;"/>
                                                </div>
                                            </td>
                                        </c:if>
                                        <td><a href="javascript:void(0);"
                                               onclick="directionFiles(${item.id},'directionFiles','${dto.surveyCno}')">详情(${item.directionFilesSize})</a>
                                        </td>
                                        <c:if test="${menuCode == 'org-review-list' || menuCode == 'survey-list' || menuCode == 'help-review'}">
                                            <td>
                                                <c:if test="${(item.surveyInvestigatorCase.isCurOrg && dto.currentSurveyAssignOrg.orgSurveyState == 2)
                                || (menuCode == 'survey-list' && (dto.surveyState == 22 || dto.surveyState == 30)) || menuCode == 'help-review'}">
                                                    <c:if test="${item.review || menuCode == 'org-review-list'}">
                                                        <a href="javascript:void(0);"
                                                           onclick="delDirection(${item.surveyInvestigatorCase.id},${item.id},'deldirection',true)">删除</a>
                                                        <a href="javascript:void(0);"
                                                           onclick="updDirection(${item.surveyInvestigatorCase.id},${item.id},'updDirection',false,'${menuCode}')">修改</a>
                                                        <c:if test="${menuCode == 'survey-list' || menuCode == 'help-review'}">
                                                            <br/><c:if test="${item.reviewOff == 0}"><a
                                                                style="cursor: pointer;"
                                                                onclick="directionOK(${item.id},4)">标记已审核</a></c:if>
                                                            <c:if test="${item.reviewOff == 1}"><a
                                                                    style="cursor: pointer;"
                                                                    onclick="directionOK(${item.id},5)">取消已审核</a></c:if>
                                                        </c:if>
                                                    </c:if>
                                                </c:if>

                                            </td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </form>
            </c:if>
            <c:if test="${menuCode =='survey-list' || menuCode =='all-list'}">
                <div class="table-title">案件沟通信息</div>
                <c:if test="${dto.surveyBackReplies != null}">
                    <c:forEach items="${dto.surveyBackReplies}" var="item">
                        <div class="stepItem" style="margin-left: 10px;margin-top: 20px">
                            <div class="stepDate"><fmt:formatDate value="${item.createTime}"
                                                                  pattern="yyyy-MM-dd HH:mm"/></div>
                            <div class="stepDot">
                                <div class="stepDot-dot finished"></div>
                            </div>
                            <div class="stepDetail">
                                <div class="stepDetail-title">沟通人：${item.createBy}
                                    <br>沟通内容：${item.contents}<br>
                                </div>
                                <div class="stepDetail-detail"></div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${dto.surveyBackReplies.size() == 0}">
                    <div class="stepItem" style="margin-top: 10px;text-align:center;color: #ff0000;">
                        暂无沟通记录
                    </div>
                </c:if>
            </c:if>
        </div>
        <div class="modal-footer">
            <%--            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>--%>
            <%--            <button type="button" id="btnTest" onclick="javascript:closeDialog()">测试关闭</button>--%>
        </div>
    </div>

    <div class="dalogs dalogsTip">
        <div class="close" onclick="javascript:$('.dalogsTip').hide()">×</div>
        <div class="d_content" style="padding: 20px;overflow: auto;height: 100%"></div>
    </div>

    <div class="dalogs dalogsAdd">
        <div class="close" onclick="javascript:$('.dalogsAdd').hide()">×</div>
        <div class="d_contentAdd" style="padding: 20px;"></div>
    </div>

    <div class="dalogs dalogs1">
        <div class="close" onclick="javascript:$('.dalogs1').hide()">×</div>
        <div class="d_content" style="padding: 20px;">
            <form id="dalogs1Form" role="form" action="${ctx}/survey/case/operateAssign" method="post">
                <input type="hidden" value="${dto.currentSurveyAssignOrg.id}" name="id"/>
                <input type="hidden" value="org-help-commit" name="btnCode"/>
                <input type="hidden" value="${dto.id}" name="surveyInfoId"/>
                <textarea rows="8" class="form-control" name="orgSummary"><c:forEach
                        items="${dto.surveyInvestigatorCases}" var="item" varStatus="index"><c:if
                        test="${item.isCurOrg}">${index.index + 1}：${item.surveySummary}</c:if></c:forEach></textarea>
                <div class="dalogsHuzhu-btns">
                    <div class="dalogsHuzhu-btn" onclick="commit(1)">提交</div>
                    <div class="dalogsHuzhu-btn dalogsHuzhu-btn0" onclick="commit(2)">关闭</div>
                </div>
            </form>
        </div>
    </div>

    <div class="dalogspub">
        <div class="dalogspub-title">
            <div class="text">操作</div>
            <div class="close" onclick="javascript:$('.dalogspub').hide()"></div>
        </div>
        <div class="dalogspub-content">
            <form id="dalogspubForm" role="form" action="${ctx}/survey/case/operateAssign">
                <input type="hidden" value="org-help-commit" name="btnCode"/>
                <input type="hidden" value="${dto.currentSurveyAssignOrg.id}" name="id"/>
                <div class="form-groups">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否阳性</label>
                        <div class="col-sm-10 radios">
                            <input type="hidden" name="sun" id="sun"/>
                            <div class="label-bars radios" data-id="sun">
                                <div class="label-bar active" data-id="p1" onclick="sunFlag(0)">否</div>
                                <div class="label-bar" data-id="p2" onclick="sunFlag(1)">是</div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group" id="div_sun1">
                        <label class="col-sm-2 control-label">阳性类型</label>
                        <div class="col-sm-10">
                            <select name="sunType" class="form-control">
                                <option value="患者不是成员本人">患者不是成员本人</option>
                                <option value="不在互助计划期间">不在互助计划期间</option>
                                <option value="等待期">等待期</option>
                                <option value="申领人故意编造未曾发生的事故">申领人故意编造未曾发生的事故</option>
                                <option value="申领人编造虚假的事故原因">申领人编造虚假的事故原因</option>
                                <option value="申领人故意制造互助事件">申领人故意制造互助事件</option>
                                <option value="材料虚假">材料虚假</option>
                                <option value="虚报年龄">虚报年龄</option>
                                <option value="未如实告知">未如实告知</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" id="div_sun2">
                        <label class="col-sm-2 control-label">阳性说明</label>
                        <div class="col-sm-10">
                        <textarea name="sunRemark" class="form-control" cols="30" rows="6"
                                  placeholder="请详细说明阳性信息"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">客户是否主动放弃申领</label>
                        <div class="col-sm-10 radios">
                            <input type="hidden" name="custGive" id="custGive"/>
                            <div class="label-bars radios" data-id="custGive">
                                <div class="label-bar active" data-id="p1">否</div>
                                <div class="label-bar" data-id="p2">是</div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">公估问题反馈</label>
                        <div class="col-sm-10 radios">
                            <input type="hidden" name="feedProblem" id="feedProblem"/>
                            <div id="div_feedProblem" class="label-bars radios" data-id="feedProblem">
                                <div class="label-bar active" data-id="1">正常案件</div>
                                <div class="label-bar" data-id="2">健告条款存在漏洞</div>
                                <div class="label-bar" data-id="3">不属于互助责任</div>
                                <div class="label-bar" data-id="4">暴力排查实务不可操作</div>
                                <div class="label-bar" data-id="5">不合理追溯</div>
                                <div class="label-bar" data-id="6">无限排查</div>
                                <div class="label-bar" data-id="7">职责不明</div>
                                <div class="label-bar" data-id="8">未收到委托</div>
                            </div>
                        </div>
                    </div>
                    <div id="div_problemRemark" style="display: none;" class="form-group">
                        <label class="col-sm-2 control-label">问题描述</label>
                        <div class="col-sm-10">
                        <textarea name="problemRemark" class="form-control" cols="30" rows="6"
                                  placeholder="请描述反馈的问题"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">调查整体说明</label>
                        <div class="col-sm-10">
                        <textarea name="surveyAllRemark" class="form-control" cols="30" rows="6" required
                                  placeholder="请描述调查整体说明"><c:forEach items="${dto.surveyInvestigatorCases}"
                                                                              var="item" varStatus="index"><c:if
                                test="${item.isCurOrg}">${item.surveySummary};</c:if></c:forEach></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <div class="label-btns" style="justify-content: center;">
                                <div class="label-btn active" onclick="oprCommit()">提交</div>
                                <%--                            <div class="label-btn active" onclick="oprBack(${dto.id},'survey-report-opr')" >退回</div>--%>
                                <div class="label-btn active1" onclick="javascript:$('.dalogspub').hide()">关闭</div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="dalogs-0108">
        <div class="d-title">
            <div class="text">价格预警</div>
            <div class="close-bar">
                <div class="close"></div>
            </div>
        </div>
        <div class="dalogs-content">
            <div class="dc-p">当前调查方价格超过委托方价格的75%(<span id="tipMoney" style="color: red;"></span>)，请重新检查价格是否合理!
            </div>
            <div class="dc-p">委托方价格：<span id="tipEntrustMoney">${dto.entrustMoney}</span>元</div>
            <div class="dc-p">调查方价格：<span id="tipSurveyMoney">${dto.surveyOKMoney}</span>元</div>
            <div class="d-btns">
                <div class="d-btn" id="cancel">价格没问题，继续审核通过</div>
                <div class="d-btn d-btn-active" id="commit">重新检查价格</div>
            </div>
        </div>
    </div>


    <div class="dalogs-0512">
        <input type="hidden" value='${selOrgJson}' id="selOrgJson">
        <div class="d-title">
            <div class="text">机构提醒</div>
            <div class="close-bar-0512">
                <div class="close"></div>
            </div>
        </div>
        <div class="dalogs-content">
            <table class="table">
                <tr>
                    <td width="15%" style="text-align: center;">选择机构:</td>
                    <td width="80%">
                        <div id="orgs"></div>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: center;">提醒内容:</td>
                    <td>
                        <textarea style="width: 100%" rows="4" cols="4" id="remindRemark"></textarea>
                    </td>
                </tr>
            </table>
            <input type="hidden" id="selOrgIds"/>
            <div class="d-btns">
                <div class="d-btn" data-id="1" id="cancel-1">取消</div>
                <div class="d-btn d-btn-active" data-id="2" id="commit-1">提交</div>
            </div>
        </div>
    </div>

</div>
<div class="loading-bar">
    <div class="loading animation-6">
        <div class="shape shape1"></div>
        <div class="shape shape2"></div>
        <div class="shape shape3"></div>
        <div class="shape shape4"></div>
    </div>
    <div class="loading-text">数据同步中···</div>
</div>
</body>
<script type="text/html" id="table-content-child-h">
    <div class="table-content-child">
        <table class="layui-table" id="test-child" lay-filter="test-child" lay-skin="line" lay-size="lg"
               style="margin: 0">
        </table>
    </div>
</script>

<script type="text/html" id="table-content-re">
    <div class="table-content-child">
        <table class="layui-table" id="test-re" lay-filter="test-re" lay-skin="line" lay-size="lg" style="margin: 0">
        </table>
    </div>
</script>

<script type="text/html" id="view-or-refundOK">
    <div class="layui-form" style="margin-top: 30px">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">初始分值</label>
                <div class="layui-input-inline _input">
                    <input type="text" class="lefanMoney1 layui-input" placeholder="" autocomplete="off">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">区域系数</label>
                <div class="layui-input-inline _input">
                    <input type="text" class="lefanMoney2 layui-input" placeholder="" autocomplete="off" disabled
                           style="background-color: #eee">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label label-input" style="width: 170px">最终分值</label>
                <div class="layui-input-inline _input">
                    <input type="text" class="lefanMoney3 layui-input" placeholder="" autocomplete="off" disabled
                           style="background-color: #eee">
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
                <button class="layui-btn layui-btn-normal submit-btn" style="width: 100px;">保存并关闭</button>
            </div>
        </div>
    </div>
</script>


<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script src="${ctx}/js/jquery.tablednd_0_5.js" charset="utf-8"></script>


<script>
    function showOrgPreList(orgPreList) {
        $(".orgPre" + orgPreList).show()
    }

    $(".orgPreClose").click(function () {
        $(this).parent().hide()
    })

    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        soulTable: 'soulTable',
        xmSelect: 'xm-select'
    })


    layui.use(['table', 'xmSelect', 'laydate', 'layer'], function () {
        var table = layui.table,
            xmSelect = layui.xmSelect

        layer = layui.layer
        // $ = layui.jquery

        var demo2 = xmSelect.render({
            el: '#orgs',
            selected: true,
            theme: {
                color: '#3BA9FF',
            },
            toolbar: {
                show: true
            },
            on: function (data) {
                var arr = data.arr;
                if (arr.length) {
                    var newValue = []
                    arr.map(function (item) {
                        newValue.push(item.value)
                    })
                    $('#selOrgIds').val(newValue.join(','))
                } else {
                    $('#selOrgIds').val('')
                }
            },
            // radio: true,
            filterable: true,
            filterDone: function (val, list) {
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            model: {
                label: {
                    type: 'templateSelf', //自定义与下面的对应
                    templateSelf: {
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
                    },
                }
            },
            data: []
        })

        var selOrgJson = $("#selOrgJson").val();
        if (selOrgJson) {
            selOrgJson = JSON.parse(selOrgJson);
            filterJson(demo2, selOrgJson, 'surveyOrgId', 'surveyOrgName', false, true, false)
        }

        function filterJson(demo, newJson, id, name, flag, selected, disabled) {
            var demoList = [],
                demoValues = []
            newJson.map(function (cur) {
                var _name = name ? cur[name] : cur.name
                var _id = id ? cur[id] : cur.id
                var param = {
                    name: _name,
                    value: _id,
                    disabled: disabled
                }
                if (selected) {
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

        demo2.setValue([$('#selOrgIds').val()])


    })


    // layui.use(['layer'],function () {
    //     var layer = layui.layer
    //
    //     var userId = $('#nuanWaUser').val() || '67003',
    //         company = 'lefan',
    //         sign = $('#nuanWaSign').val() || '462b2947d411f2009eecd15871e7a1af',
    //         redirectUrl = '/insurance/offlineInvestigateTaskPool/detail',
    //         handleId = '188'
    //     var _url = 'https://open-mdp-test.nuanwa.net/#/t/authorize?user='+userId+'&company='+company+'&sign='+sign+'&redirect='+redirectUrl+'?handleId='+handleId
    //
    //     var _width = $(document).width() * 0.98
    //     var _height = $(document).height() * 0.98
    //     layer.open({
    //         type: 2,
    //         area: [_width + 'px', _height + 'px'],
    //         content: _url,
    //         success: function () {
    //
    //         }
    //     })
    // })

    $(function () {
        $(".show-evaluate-one").map(function () {
            var _this = $(this);
            var itemId = _this.attr("data-direction-id");
            var evaluate = _this.attr("data-evaluate");
            var huise = _this.attr("data-huise");
            $(".show-evaluate-one span").map(function () {
                var _span = $(this);
                var directionId = _span.attr("data-item-id");
                var value = _span.attr("data-value");
                if (directionId == itemId && evaluate == value && evaluate == 1) {
                    _span.addClass("span-evaluate-one")
                } else if (directionId == itemId && evaluate == value && evaluate == 2) {
                    _span.addClass("span-evaluate-two")
                } else if (directionId == itemId && evaluate == value && evaluate == 3) {
                    _span.addClass("span-evaluate-three")
                }
                if (directionId == itemId && huise) {
                    if (value == evaluate) {
                        _span.addClass("span-evaluate-huise1")
                    } else {
                        _span.addClass("span-evaluate-huise2")
                    }
                    _span.removeAttr("onclick");
                }
            })
        })

        var userId = $('#nuanWaUser').val() || '',
            company = $('#nuanWaCompany').val() || '',
            sign = $('#nuanWaSign').val() || '',
            redirectUrl = '/insurance/offlineInvestigateTaskPool/detail',
            handleId = $('#handleId').val() || ''
        var rUrl = redirectUrl + '?handleId=' + handleId
        // var _url = 'https://open-mdp-test.nuanwa.net/#/t/authorize?user='+userId+'&company='+company+'&sign='+sign+'&redirect='+encodeURIComponent(rUrl)
        var _url = 'https://open-mdp.nuanwa.net/#/t/authorize?user=' + userId + '&company=' + company + '&sign=' + sign + '&redirect=' + encodeURIComponent(rUrl)
        if ($('#iframeNW').length) {
            $('#iframeNW').attr('src', _url)
            var _height = $(parent.document).height() - 70
            $('.iframe-content').css({
                height: _height + 'px'
            })
            $('#iframeNW').css({
                height: _height + 'px'
            })
        }
        $('.synchrodata').click(function () {
            $('.loading-bar').show()
            var _this = $(this)
            _this.addClass('poi-no')
            setTimeout(function () {
                _this.removeClass('poi-no')
            }, 3000)
            $.ajax({
                url: '${ctx}/survey/case/asyncNwCase',
                type: 'post',
                data: {
                    riskInfoId: $('#surveyInfoId').val()
                },
                success: function (data, e) {
                    $('.loading-bar').hide()
                    if (e.data.isSuccess) {
                        location.reload();
                    } else {
                        $('.loading-bar').hide()
                        alert(e.data.results)
                    }
                }
            })
        })
        $('.singleSelect').select2();
        $('.dalogspub-content').height(($(parent.document).outerHeight() - 66) * 0.88 - 56)


    });

    $('body').on('click', '.bianji', function () {
        inputIndex = layer.open({
            type: 1,
            title: "编辑",
            area: ['560px', '500px'],
            offset: '100px',
            content: $('#view-or-refundOK').html(),
            success: function () {
                $('.lefanMoney1').val(1)
                $('.lefanMoney2').val(2)
                $('.lefanMoney3').val(3)

                $('.submit-btn').click(function () {
                    $('.submit-btn').addClass('poi-no')
                    setTimeout(function () {
                        $('.submit-btn').removeClass('poi-no')
                    }, 6000)
                    var url = '${ctx}/fina/settlement/operate'

                    var params = {
                        btnCode: 'cw-refund-ok',
                        settlementId: $('#settlementId').val(),
                        lefanMoney1: $('.lefanMoney1').val(),
                        lefanMoney2: $('.lefanMoney2').val(),
                        lefanMone3: $('.lefanMone3').val(),
                        lefanDesc: $('.lefanDesc').val(),

                    }
                    $.ajax({
                        url: url,
                        type: 'post',
                        data: params,
                        success: function (res) {
                            var res = JSON.parse(res)
                            if (res.isSuccess) {
                                layer.msg('成功', {
                                    time: 1000,
                                    icon: 1
                                }, function () {
                                    location.reload()
                                })
                            } else {
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
    })

    function feeFilesLook(directionId) {
        var _this = $(this)
        // alert(_this.data("value"))
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        var title = "附件详情";
        openDialog({
            frame: true,
            title: title,
            height: height,
            width: width,
            url: "${ctx}/survey/case/sic/directionFiles?directionId=" + directionId + "&type=all" + "&isdelete=no"
        });

    }

    $('.label-bars').on('click', '.label-bar', function () {
        var _this = $(this)
        if (!_this.hasClass('active')) {
            _this.addClass('active')
            _this.siblings().removeClass('active')
            _this.parent().attr('data-value', _this.attr('data-id'))
        }
    })

    $('.dalogs-0108 .d-btn').on('click', function () {
        var _this = $(this)
        $('.dalogs-0108').hide()
        if (_this.attr('id') == 'cancel') {
            console.log(11111);
            var title = "操作";
            var url = "${ctx}/survey/case/operateView?id=${dto.id}&btnCode=survey-report-opr";
            // var width = $(document.body).outerWidth();
            // var height = window.parent.innerHeight - 56 - 40;
            var width = $(document.body).outerWidth();
            var height = window.parent.innerHeight - 70;
            if ($(window.parent).width() < 1300) {
                height = height + 30;
            }

            url += "&surveyInfoId=${dto.id}&assignOrgCaseId=${dto.currentSurveyAssignOrg.id}";
            openDialog({
                frame: true,
                title: title,
                height: height,
                width: width,
                url: url
            });
        }
    })


    $('.dalogs-0512 .d-btn').on('click', function () {
        var _this = $(this)
        var dataId = _this.attr('data-id');
        if (!$("#selOrgIds").val() && dataId == '2') {
            alert("请选择机构");
            return;
        }
        $('.dalogs-0512').hide()
        if (dataId == '2') {
            //发送机构ajax请求
            $.ajax({
                url: "${ctx}/survey/case/operate?id=${dto.id}&btnCode=org-remind&selOrgIds=" + $("#selOrgIds").val() + "&remindRemark=" + $("#remindRemark").val(),
                type: 'POST',
                dataType: 'json',
                success: function (v, data) {
                    location.reload();
                }
            });
        }
    })

    $('.close-bar').click(function () {
        $('.dalogs-0108').hide()
    })


    $('.close-bar-0512').click(function () {
        $('.dalogs-0512').hide()
    })

    sunFlag(0);

    function sunFlag(sun) {
        if (sun == 0) {
            $("#div_sun1").hide();
            $("#div_sun2").hide();
        } else if (sun == 1) {
            $("#div_sun1").show();
            $("#div_sun2").show();
        }
    }

    $("#editForm").bind('submit', function (event) {
        ajaxFormSubmit(this, reload, null, null, reload);
        event.preventDefault();
    });


    $("#editFormDirection").bind('submit', function (event) {
        ajaxFormSubmit(this, reload, null, null, reload);
        event.preventDefault();
    });
    $("#editFormTask").bind('submit', function (event) {
        ajaxFormSubmit(this, reload, null, null, reload);
        event.preventDefault();
    });
    $("#editFormOrg").bind('submit', function (event) {
        ajaxFormSubmit(this, reload, null, null, reload);
        event.preventDefault();
    });

    $("#dalogs1Form").bind('submit', function (event) {
        ajaxFormSubmit(this, reload, null, null, reload);
        event.preventDefault();
    });

    $("#dalogspubForm").bind('submit', function (event) {
        ajaxFormSubmit(this, reload, null, null, reload);
        event.preventDefault();
    });

    var orgSurveyTimeOK = function (surveyInfoId, surveyAssignOrgId, orgId, btnCode) {
        var minTime = $("#minTime").val();
        var maxTime = $("#maxTime").val();
        var distributionAgencyTime = $("#distributionAgencyTime" + surveyAssignOrgId).val();
        var distributionAgencyTimeRemark = $("#distributionAgencyTimeRemark" + surveyAssignOrgId).val();
        $.ajax({
            url: "${ctx}/survey/case/operate?surveyInfoId=" + surveyInfoId + "&surveyAssignOrgId=" + surveyAssignOrgId + "&orgId=" + orgId + "&btnCode=" + btnCode + "&endTime=" + distributionAgencyTime + "&distributionAgencyTimeRemark=" + distributionAgencyTimeRemark + "&minTime=" + minTime + "&maxTime=" + maxTime,
            type: 'POST',
            dataType: 'json',
            success: function (v, data) {
                console.log(data.data.msg);
                if (data.data.code == 1201) {
                    alert(data.data.msg);
                }
                location.reload();
            }
        });
    }

    var oprCommit = function () {
        $('.dalogspub .dalogspub-content .radios').each(function (index, obj) {
            var dataId = $(obj).attr("data-id");
            if (dataId) {
                $(obj).find(".label-bar").each(function (i, item) {
                    if ($(item).hasClass("active")) {
                        $("#" + dataId).val($(item).text());
                    }
                })
            }
        });
        $(".label-btn").addClass("po-none");
        $("#dalogspubForm").submit();
    }

    $('#div_feedProblem').on('click', '.label-bar', function () {
        var _this = $(this);
        var value = _this.attr("data-id");
        if (value == 1) {
            $("#div_problemRemark").hide();
        } else {
            $("#div_problemRemark").show();
        }
    });


    var commit = function (type) {
        if (type == 1) {
            $(".dalogsHuzhu-btn").attr("disabled", true);
            $("#dalogs1Form").submit();
        } else if (type == 2) {
            $(".dalogs1").hide();
        }
    }

    var sun = function (id, btnCode, signType, ajax) {
        if (ajax) {
            var url = "${ctx}/survey/case/sic/operate", param = {"id": id, "btnCode": btnCode, "signType": signType};
            if (confirm('是否确认？')) {
                ajaxSubmit(url, param, function (v, e, p) {
                    location.reload();
                })
            }
        }
    }

    var updSunMoney = function (id, btnCode, ajax) {
        var updSunMoney = $("#updSunMoney" + id).val();
        if (updSunMoney < 0 || updSunMoney == null || updSunMoney == '' || updSunMoney == undefined) {
            alert("请输入正确的金额!");
            return false;
        }
        var updSunMoneyRemark = $("#updSunMoneyRemark" + id).val();
        $.ajax({
            url: "${ctx}/survey/case/sic/operate?id=" + id + "&updSunMoney=" + updSunMoney + "&updSunMoneyRemark=" + updSunMoneyRemark + "&btnCode=" + btnCode,
            type: 'POST',
            dataType: 'json',
            success: function (v, data) {
                location.reload();
            }
        });
    }

    function addFollow(id, surveyOrgId, btnCode) {
        var height = 400, width = 900;
        openDialog({
            frame: true,
            height: height,
            width: width,
            title: "添加跟踪",
            url: "${ctx}/survey/case/addFollow?id=" + id + "&btnCode=" + btnCode + "&surveyOrgId=" + surveyOrgId
        });
    }

    var caseClockDetails = function (id, btnCode) {
        var url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode
        parent.parent.addTab("案件打卡足迹", url, true);
    }

    var operate = function (id, btnCode, ajax) {
        var msg = false;
        var height = 400, width = 900;
        if (btnCode == '1500') {
            //验证报告寄送状态  验证开票状态  验证到账状态
            if ("${dto.isSendReport}" == 0) {
                alert("报告未寄送");
                return;
            }
            if ("${dto.isPayEntrustFee}" == 0) {
                alert("未申请开票");
                return;
            }
            if ("${dto.isPayEntrustFee}" == 1) {
                alert("开票未确认");
                return;
            }
            if ("${dto.arrivalDate}" == null || "${dto.arrivalDate}" == '') {
                alert("开票未到账");
                return;
            }
        } else if (btnCode == '1200') {
            <%--if("${dto.sourceOrgId}" ==null || "${dto.sourceOrgId}"==''){--%>
            <%--    alert("请选择案源机构");return;--%>
            <%--}--%>
        } else if (btnCode == '1505') {
            if ("${dto.surveyOKMoney}" == 0) {
                msg = true;
                var c = confirm("调查方确认基本费结算价格为0，是否确认结算");
                if (!c) {
                    return;
                }
            } else {
                if ("${dto.surveyOKMoney}" != "${dto.surveyMoney}") {
                    msg = true;
                    var c = confirm("调查员确认基本费结算价格不等于参考价格，是否确认结算");
                    if (!c) {
                        return;
                    }
                }
            }
        } else if (btnCode == '1506') {
            if ("${dto.surveryOKReLosses}" == 0) {
                msg = true;
                var c = confirm("调查方确认减损奖励结算价格为0，是否确认结算");
                if (!c) {
                    return;
                }
            } else {
                if ("${dto.surveryOKReLosses}" != "${dto.surveryReLosses}") {
                    msg = true;
                    var c = confirm("调查员确认损奖励结算价格不等于参考价格，是否确认结算");
                    if (!c) {
                        return;
                    }
                }
            }
        } else if (btnCode == "org3" || btnCode == 'org4') {
            if ("${dto.currentSurveyAssignOrg.orgSurveyState}" == 6) {
                alert("有退回案件不可操作");
                return;
            }
        } else if (btnCode == 'sendReportCommit') {
            if (!"${dto.reportId}") {
                alert("请上传报告");
                return;
            }
        } else if (btnCode == '1300' || btnCode == '1301') {
            if ("${dto.sendReportState}" == 1) {
                alert("报告已发送制作人，报告制作中");
                return;
            }
        } else if (btnCode == 'org-help-commit') {
            $(".dalogspub").show();
            return;
        } else if (btnCode == 'lefan-commit') {
            $(".dalogspub").show();
            return;
        } else if (btnCode == 'survey-report-opr') {
            var entrustMoney = $("#tipEntrustMoney").text().trim();
            var surveyMoney = $("#tipSurveyMoney").text().trim();
            console.log(entrustMoney, surveyMoney, entrustMoney * 0.75, surveyMoney > entrustMoney * 0.75)
            if (surveyMoney > entrustMoney * 0.75) {
                $("#tipMoney").text((entrustMoney * 0.75).toFixed(2));
                $(".dalogs-0108").show();
                return;
            }
        } else if (btnCode == 'org-remind') {
            $(".dalogs-0512").show();
            return;
        }
        if (ajax) {
            var tipTitle = "是否确认？";
            var url = null, param = null;
            if (btnCode == 'sign' || btnCode == 'resign' || btnCode == 'delCase' || btnCode == 'review-user-yes' || btnCode == 'review-user-cancel') {
                url = "${ctx}/survey/case/sic/operate";
                param = {"id": id, "btnCode": btnCode};
            } else if (btnCode == 'org1' || btnCode == 'org3' || btnCode == '118' || btnCode == '123' || btnCode == 'review-org-yes'
                || btnCode == 'review-org-cancel' || btnCode == 'generateTest' || btnCode == 'review-help-yes' || btnCode == 'review-help-chehui'
                || btnCode == 'mark-error' || btnCode == 'qx-mark-error') {
                url = "${ctx}/survey/case/operateAssign";
                param = {"id": id, "btnCode": btnCode, "surveyInfoId": "${dto.id}"};
                if (btnCode == '118') {
                    tipTitle = "删除机构同时将删除该机构下的调查员任务及方向，是否确认？"
                }
                if (btnCode == 'mark-error' || btnCode == 'qx-mark-error') {
                    msg = true;
                }
            } else if (btnCode == 121) {
                url = "${ctx}/survey/case/operate";
                param = {"id": id, "btnCode": btnCode, "backCaseId": "${dto.currentSurveyBackCase.id}"};
            } else if (btnCode == 'generateReport') {
                url = "${ctx}/survey/case/operate";
                param = {"id": id, "btnCode": btnCode, "surveyInfoId": "${dto.id}"};
            } else {
                url = "${ctx}/survey/case/operate";
                param = {"id": id, "btnCode": btnCode};
            }
            if (!msg) {
                if (confirm(tipTitle)) {
                    ajaxSubmit(url, param, function (v, e, p) {
                        alert(e.data.msg);
                        location.reload();
                    })
                }
            } else {
                ajaxSubmit(url, param, function (v, e, p) {
                    alert(e.data.msg);
                    location.reload();
                })
            }
        } else {
            var title = null, url = null;
            if (btnCode == 'score' || btnCode == 'punish') {
                title = "操作";
                url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode;
            } else if (btnCode == 'follow' || btnCode == 'backreply') {
                title = "添加跟踪";
                if (btnCode == 'backreply') {
                    title = "案件沟通"
                }
                url = "${ctx}/survey/case/addFollow?id=" + id + "&btnCode=" + btnCode + "&surveyCno=${dto.surveyCno}";
            } else if (btnCode == 'files') {
                title = "查看资料";
                height = $(document).outerHeight() - 20;
                width = $(document.body).outerWidth();
                url = "${ctx}/survey/case/fileMidView?surveyInfoId=" + id + "&surveyId=${dto.surveyId}&surveyCno=${dto.surveyCno}";
            } else if (btnCode == 111 || btnCode == 112 || btnCode == 113 || btnCode == 115 || btnCode == 116 || btnCode == 117) {
                width = 1200;
                height = 750;
                var taskRemark = "";
                var backCaseId = "";
                var curSurveyOrgId = "";
                if (btnCode == 112 || btnCode == 117) {
                    <%--taskRemark = "${dto.surveyItem}";--%>
                    if (btnCode == 112) {
                        backCaseId = "${dto.currentSurveyBackCase.id}";
                    }
                }
                if (btnCode == 113 || btnCode == 116) {
                    <%--taskRemark = "${dto.currentSurveyAssignOrg.orgTaskRemark}";--%>
                    if (btnCode == 113) {
                        backCaseId = "${dto.currentSurveyBackCase.id}";
                    }
                    curSurveyOrgId = $("#curSurveyOrgId").val();
                }
                if (btnCode == 111 || btnCode == 115) {
                    <%--taskRemark = "${dto.surveyItem}";--%>
                }

                title = '分派';
                url = "${ctx}/survey/case/assignSurveyUser?id=" + id + "&btnCode=" + btnCode + "&taskRemark=" + taskRemark
                if (btnCode == 115 || btnCode == 116) {
                    title = "改派";
                    url = "${ctx}/survey/case/assignSurveyUser?id=${dto.id}&btnCode=" + btnCode + "&taskRemark=" + taskRemark + "&surveyCaseId=" + id
                    if (btnCode == 116) {
                        url = "${ctx}/survey/case/assignSurveyUser?id=${dto.id}&btnCode=" + btnCode + "&taskRemark=" + taskRemark + "&surveyCaseId=" + id + "&curSurveyOrgId=" + curSurveyOrgId;
                    }
                    console.log(url);
                } else if (btnCode == 117) {
                    title = "改派";
                    url = "${ctx}/survey/case/assignSurveyUser?id=${dto.id}&btnCode=" + btnCode + "&taskRemark=" + taskRemark + "&orgCaseId=" + id
                } else if (btnCode == 112) {
                    url = "${ctx}/survey/case/assignSurveyUser?id=" + id + "&btnCode=" + btnCode + "&taskRemark=" + taskRemark + "&backCaseId=" + backCaseId
                    var autoOpenInfo = $("#autoOpenInfo").val();
                    if (autoOpenInfo == 1) {
                        url = "${ctx}/survey/case/assignSurveyUser?id=" + id + "&btnCode=" + btnCode + "&taskRemark=" + taskRemark + "&backCaseId=" + backCaseId + "&autoOpenInfo=" + autoOpenInfo
                    }
                } else if (btnCode == 113) {
                    url = "${ctx}/survey/case/assignSurveyUser?id=" + id + "&btnCode=" + btnCode + "&taskRemark=" + taskRemark + "&backCaseId=" + backCaseId + "&curSurveyOrgId=" + curSurveyOrgId + "&assignOrgId=" + $("#assignOrgId").val();
                }
            } else if (btnCode == 1201 || btnCode == 1301 || btnCode == 1401 || btnCode == 1601
                || btnCode == 'dispatchNo' || btnCode == 'org2' || btnCode == 'org4'
                || btnCode == 120 || btnCode == 122 || btnCode == '1334' || btnCode == 'review-help-no') {
                height = 350;
                width = 700;
                title = '退回';
                url = "${ctx}/survey/case/back?id=" + id + "&btnCode=" + btnCode;
                if (btnCode == 120 || btnCode == 122) {
                    url = "${ctx}/survey/case/back?id=" + id + "&btnCode=" + btnCode + "&backCaseId=${dto.currentSurveyBackCase.id}";
                }
                if (btnCode == 'review-help-no') {
                    url = "${ctx}/survey/case/back?id=" + id + "&btnCode=" + btnCode;
                }
            } else if (btnCode == 'fee') {

            } else if (btnCode == 1501) {
                height = 700;
                width = 800;
                title = "开票";
                url = "${ctx}/billingApply/billApplyEdit?id=" + id + "&copy=2" + "&companyName=" + '${dto.entrustOrgName}' + "&companyId=" + '${dto.entrustOrgId}';//此处的id是调查字表id
            } else if (btnCode == 'dispatch' || btnCode == 'progress' || btnCode == 'workflow') {
                title = "调度";
                if (btnCode == 'progress') {
                    height = 750;
                    title = "查看进度";
                } else if (btnCode == 'workflow') {
                    height = 600;
                    title = "查看时效";
                }
                url = "${ctx}/survey/case/operateView?id=" + id + "&btnCode=" + btnCode;
                if (btnCode == 'progress') {
                    url = "${ctx}/survey/case/operateView?surveyInfoId=" + id + "&btnCode=" + btnCode;
                }
            } else if (btnCode == 'primary' || btnCode == 'direction' || btnCode == 'orgPrimary') {
                title = "操作";
                url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode;
                if (btnCode == 'primary') {
                    url += "&surveyCno=${dto.surveyCno}&tsId=surveyInfoId";//tsId 特殊的ID，此处的ID为 案件字表ID
                }
                if (btnCode == 'orgPrimary') {
                    url += "&surveyCno=${dto.surveyCno}&tsId=orgAssignId";//tsId 特殊的ID  此处的id为  机构案件的ID
                }
                if (btnCode == 'direction') {
                    url += "&orgReview=1";
                    height = $(document).outerHeight() - 20;
                    width = $(document.body).outerWidth();
                }
            } else if (btnCode == 'updAssign') {
                title = "改派";
                url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode;
            } else if (btnCode == 'orgReturn') {
                title = "退回";
                url = "${ctx}/survey/case/sic/back?id=" + id + "&btnCode=" + btnCode;
            } else if (btnCode == '1300' || btnCode == '1400' || btnCode == 'org5' || btnCode == 'survey-report-opr' || btnCode == 'survey-opr') {
                title = "操作";
                url = "${ctx}/survey/case/operateView?id=" + id + "&btnCode=" + btnCode;
                if (btnCode == '1400' || btnCode == '1300' || btnCode == 'survey-report-opr') {
                    url += "&entrustTime=" + $("#entrustTime").val()
                }
                if (btnCode == '1400') {
                    height = 700
                }
            } else if (btnCode == 'transfer') {
                title = "操作";
                height = $(document).outerHeight() - 20;
                width = $(document.body).outerWidth();
                url = "${ctx}/survey/case/edit?id=" + id + "&btnCode=" + btnCode + "&menuCode=" + '${menuCode}' + "&nextTransferType=" + '${dto.surveyRiskCase.nextTransferType}' + "&topSurveyId=" + '${dto.surveyRiskCase.topSurveyId}' + "&showTransfer=" + '${showTransfer}'
            } else if (btnCode == 118) {
                title = "删除分派机构";
                url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode + "&surveyInfoId=" +${dto.id};
            } else if (btnCode == "delCase") {
                title = "删除调查员";
                url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode;
            } else if (btnCode == 'case-return') {
                title = "退回";
                height = $(document).outerHeight() - 20;
                width = $(document.body).outerWidth();
                var assignOrgId = $("#assignOrgId").val();
                url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode + "&roleCode=org" + "&assignOrgId=" + assignOrgId;
            } else if (btnCode == 'extension-time') {
                title = "申请延期",
                    height = $(window.parent).outerHeight() - 90
                width = $(document.body).outerWidth() - 10;
                var assignOrgId = $("#assignOrgId").val();
                url = "${ctx}/survey/case/sic/operateView?id=" + assignOrgId + "&btnCode=" + btnCode + "&roleCode=orgManager";
            } else if (btnCode == 'guide' || btnCode == 'guided') {
                height = 700;
                title = "案件指导";
                url = "${ctx}/survey/case/operateView?id=" + id + "&btnCode=" + btnCode;
            } else if (btnCode == 'visit') {
                var surveyAssorgCaseId = $("#surveyAssorgCaseId").val();
                height = 600;
                title = "调查回访";
                url = "${ctx}/survey/case/operateView?id=" + id + "&btnCode=" + btnCode + "&surveyAssorgCaseId=" + surveyAssorgCaseId;
            }
            if (btnCode == 'org5' || btnCode == 'survey-report-opr' || btnCode == 'survey-opr') {
                width = $(document.body).outerWidth();
                height = window.parent.innerHeight - 70;
                if ($(window.parent).width() < 1300) {
                    height = height + 30;
                }

                url += "&surveyInfoId=${dto.id}&assignOrgCaseId=${dto.currentSurveyAssignOrg.id}";
            }
            openDialog({
                frame: true,
                title: title,
                height: height,
                width: width,
                url: url
                // load:true
            });
        }
    }

    var operate2 = function (riskId) {
        $.ajax({
            url: "${ctx}/survey/case/sic/operate",
            data: {
                btnCode: "repetition",
                riskId: riskId
            },
            success: function (res, obj) {
                if (obj.data.isSuccess) {
                    showReCaseList(obj.data.results)
                } else {
                    alert("未找到重复案件")
                }
            }
        })

    }

    function showReCaseList(list) {
        var _height = $(parent.document).height() * 0.7
        var _width = $(parent.document).width() * 0.7
        openIndex = layer.open({
            type: 1,
            title: '重复案件',
            area: [_width + 'px', _height + 'px'],
            offset: '100px',
            content: $('#table-content-re').html(),
        });
        var _cols_child = [
            [{
                field: 'surveyNo',
                minWidth: 200,
                title: '调查编号',
            }, {
                field: 'surveyPerson',
                minWidth: 160,
                title: '调查人',
            }, {
                field: 'surveryPersonTel',
                minWidth: 150,
                title: '联系方式',
            }, {
                field: 'entrustOrgName',
                minWidth: 200,
                title: '保险公司',
            }, {
                field: '',
                minWidth: 80,
                title: '操作',
                templet: function (d) {
                    console.log(d.riskInfoId)
                    var _html = '<a class="layui-btn layui-btn-xs operateMark" style="color: #fff!important" lay-event="operate">详情</a>'
                    return _html
                }
            }
            ]
        ]

        var childTable = table.render({
            id: "test-re",
            elem: '#test-re',
            cols: _cols_child,
            page: false,
            height: 'full-60',
            drag: false,
            event: true,
            data: list,
        })
        table.on('tool(test-re)', function (obj) {
            if (obj.event == 'operate') {
                var _height = $(document).height() * 0.99
                var _width = $(document).width() * 0.99
                openDialog({
                    frame: true,
                    title: "",
                    height: _height,
                    width: _width,
                    url: "${ctx}/survey/case/info?id=" + obj.data.riskInfoId + "&menuCode=all-list&display=true",
                });
            }
        })
    }

    var extensionTime = function (id, btnCode, ajax, type) {

        var title = "申请延期";
        var width = $(document.body).outerWidth();
        var height = $(parent.document).outerHeight() - 90;
        var assignOrgId = $("#assignOrgId").val();
        var url = "${ctx}/survey/case/sic/operateView?id=" + assignOrgId + "&btnCode=" + btnCode + "&roleCode=orgManager" + "&type=" + type;
        openDialog({
            frame: true,
            title: title,
            height: height,
            width: width,
            url: url
        });
    }


    var updMoney = function (directionId, type) {
        if (type == 1) {
            $("#div_survey_money1" + directionId).hide();
            $("#div_survey_money2" + directionId).show();
        } else if (type == 2) {
            $("#div_entrust_money1" + directionId).hide();
            $("#div_entrust_money2" + directionId).show();
        } else if (type == 3) {
            $("#div_score1" + directionId).hide();
            $("#div_score2" + directionId).show();
        } else if (type == 6) {
            $("#div_channelFee1" + directionId).hide();
            $("#div_channelFee2" + directionId).show();
        }
        if (type == 7) {
            $("#divSunMoney1" + directionId).hide();
            $("#divSunMoney2" + directionId).show();
        } else if (type == 20) {
            $("#div_report_completion_1").hide();
            $("#div_report_completion_2").show();
        } else if (type == 21) {
            $("#div_directionText1" + directionId).hide();
            $("#div_directionText2" + directionId).show();
        }
    }

    var oprDirection = function (id, directionId, btnCode, ajax) {
        var url = "${ctx}/survey/case/sic/operate", param = {"id": id, "directionId": directionId, "btnCode": btnCode};
        if (confirm('是否确认？')) {
            ajaxSubmit(url, param, function (v, e, p) {
                location.reload();
            })
        }
    }

    var directionOK = function (directionId, updType, oldMoney) {
        if (updType == 1) {
            var newMoney = $("input[name='surveyMoney" + directionId + "']")[0].value;
            if (newMoney < 80) {
                alert("调查方方向价格低于80元，请重新检查价格是否合理！");
            }
        }
        if (updType == 2) {
            var money = $("input[name='entrustMoney" + directionId + "']").val();
            if (money < 0) {
                alert("请输入正确的金额!");
                return false;
            }
        }
        if (updType == 6) {
            var curFee = $("input[name='channelFee" + directionId + "']").val();
            curFee = curFee == '' ? 0 : curFee;
            var maxFee = $("input[name='channelSent" + directionId + "']").val();
            maxFee = maxFee == '' ? 0 : maxFee;
            if (curFee > maxFee) {
                var msg = "当前渠道费用超过限额（限额" + maxFee + "元），将由分管总人工审核，是否确认？";
                if (!confirm(msg)) {
                    return false;
                }
            }
        }
        $("#updType").val(updType);//1更改调查方价格  2更改委托方价格  3更改方向分值
        $("#directionId").val(directionId);
        if (updType == 4 || updType == 5 || updType == 7 || updType == 8 || updType == 9 || updType == 10 || updType == 15 || updType == 16) {
            if (confirm("是否确认？")) {
                $("#editFormDirection").submit();
            }
        }
        if (updType == 'hege' || updType == 'you' || updType == 'cha') {
            $("#editFormDirection").submit();
        }
        return true;
    }

    var oprOK = function (id, btnCode) {
        if (btnCode == 701) {

        } else if (btnCode == '1009') {
            var idNumber = $("#idNumber").val()
            var regIdNo = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
            if (!regIdNo.test(idNumber)) {
                alert("身份证号填写有误");
                return false;
            }
        } else if (btnCode == 'updateTaskType') {
            var taskIds = [];
            $("input:checkbox[name='chkTaskIds']:checked").each(function () { // 遍历name=chkTaskIds的多选框
                taskIds.push($(this).val());
            });
            $("#taskIds").val(taskIds);
        }
        $("#btnCode").val(btnCode);
    }

    var updPriceTime = function (surveyInfoId, surveyAssignOrgId, orgId) {
        $("#div_start_time_1" + orgId).hide();
        $("#div_start_time_2" + orgId).show();
        $.ajax({
            url: "${ctx}/survey/case/operate?surveyInfoId=" + surveyInfoId + "&surveyAssignOrgId=" + surveyAssignOrgId + "&orgId=" + orgId + "&btnCode=getTime",
            type: 'POST',
            dataType: 'json',
            success: function (v, data, r) {
                var maxTime = data.data.results.maxTime;
                maxTime = new Date(maxTime);
                var minTime = data.data.results.minTime;
                minTime = new Date(minTime);
                $("#maxTime").val(dateFormat(maxTime));
                $("#minTime").val(dateFormat(minTime));
                console.log(dateFormat(maxTime), dateFormat(minTime));
                document.getElementById('distributionAgencyTime' + surveyAssignOrgId).onclick = function () {
                    WdatePicker({
                        dateFmt: 'yyyy-MM-dd',
                        maxDate: dateFormat(maxTime),
                        minDate: dateFormat(minTime)
                    })
                }
            }
        });
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

    function randomDate(newMinDate, newMaxDate) {
        WdatePicker({
            dateFmt: 'yyyy-MM-dd',
            maxDate: newMaxDate,
            minDate: newMinDate
        })
    }

    var cliUpdPriceClient = function (type) {
        $("#div_entrust_ok_1" + type).hide();
        $("#div_entrust_ok_2" + type).show();
    }

    var mark = function (id) {
        $("#div_org_mark_error1" + id).hide();
        $("#div_org_mark_error2" + id).show();
    }

    var updOprOK = function (id, itemId, btnCode) {
        var menuCode = $("#menuCode").val();
        var money = $("#entrustOkPrice1" + itemId).val();
        var entrustOkPrice1Remark = $("#entrustOkPrice1Remark" + itemId).val();
        if (money < 0) {
            alert("请输入正确的金额!");
            return false;
        }
        console.log(itemId);
        console.log(id);
        $.ajax({
            url: "${ctx}/survey/case/operate?id=" + id + "&itemId=" + itemId + "&menuCode=" + menuCode + "&money=" + money + "&moneyRemark=" + entrustOkPrice1Remark + "&btnCode=" + btnCode,
            type: 'POST',
            dataType: 'json',
            success: function (v, data) {
                console.log(data.data.msg);
                if (data.data.code == 1201) {
                    alert(data.data.msg);
                }
                location.reload();
            }
        });
    }

    var orgCaseUpd = function (itemId, type) {
        if (type == 'agingRate') {
            $("#div_org_aging_rate_1_" + itemId).hide();
            $("#div_org_aging_rate_2_" + itemId).show();
        }
    }
    var orgCaseOK = function (id, btnCode) {
        $("#surveyAssignOrgId").val(id);
        $("#orgInfoBtnCode").val(btnCode);
        if (btnCode == 'mark-error') {
            if (!$("#markRemark" + id).val()) {
                alert("差错备注必填");
                return false;
            }
        }
    }


    var surveyCaseUpd = function (itemId, type) {
        if (type == 'agingRate') {
            $("#div_agingrate1_" + itemId).hide();
            $("#div_agingrate2_" + itemId).show();
        } else if (type == 'surveyTaskRemark') {
            $("#div_survey_task_remark_1_" + itemId).hide();
            $("#div_survey_task_remark_2_" + itemId).show();
        }
    }

    var surveyCaseOK = function (id, btnCode) {
        $("#surveyCaseId").val(id);
        $("#surveyBtnCode").val(btnCode);
    }

    var cliUpdPrice = function (type) {
        if (type == 1) {
            $("#div_entrust_1").hide();
            $("#div_entrust_2").show();
        } else if (type == 2) {
            $("#div_survery_1").hide();
            $("#div_survery_2").show();
        } else if (type == 3) {
            $("#div_credit_money_1").hide();
            $("#div_credit_money_2").show();
        } else if (type == 4) {
            $("#div_entrust_ok_1").hide();
            $("#div_entrust_ok_2").show();
        } else if (type == 5) {
            $("#div_survery_11").hide();
            $("#div_survery_22").show();
        } else if (type == 6) {
            $("#div_entrust_ok_3").hide();
            $("#div_entrust_ok_4").show();
        } else if (type == 7) {
            $("#div_entrust_ok_5").hide();
            $("#div_entrust_ok_6").show();
        } else if (type == 8) {
            $("#div_end_time_1").hide();
            $("#div_end_time_2").show();
        } else if (type == 9) {
            $("#div_survey_person_1").hide();
            $("#div_survey_person_2").show();
        } else if (type == 'tel') {
            $("#div_survey_person_tel_1").hide();
            $("#div_survey_person_tel_2").show();
        } else if (type == 10) {
//            $("#div_entrust_org_1").hide();
//            $("#div_entrust_org_2").show();
            loadEntrustOrg();
            var height = 450;
            var width = 1200;
            var title = "修改委托机构";
            var url = "${ctx}/survey/case/operateView?id=${dto.id}&btnCode=entrustUpdate&updateType=org&entrustOrgId=" + '${dto.entrustOrgId}';
            openDialog({
                frame: true,
                title: title,
                height: height,
                width: width,
                url: url
            });
        } else if (type == 11) {
            var height = 450;
            var width = 1200;
            var title = "操作";
            var url = "${ctx}/survey/case/operateView?id=${dto.id}&btnCode=services";
            openDialog({
                frame: true,
                title: title,
                height: height,
                width: width,
                url: url
            });
        } else if (type == 12) {
            $("#div_belong_user_1").hide();
            $("#div_belong_user_2").show();
            loadBelongUser();
        } else if (type == 13) {
//            $("#div_entrust_org_1").hide();
//            $("#div_entrust_org_2").show();
//            loadEntrustOrg();
            var height = 450;
            var width = 1200;
            var title = "修改委托人";
            var url = "${ctx}/survey/case/operateView?id=${dto.id}&btnCode=entrustUpdate&updateType=user&entrustOrgId=" + '${dto.entrustOrgId}';
            openDialog({
                frame: true,
                title: title,
                height: height,
                width: width,
                url: url
            });
        } else if (type == 14) {
            $("#div_task_type_1").hide();
            $("#div_task_type_2").show();
            ajaxSubmit("${ctx}/survey/case/selectTaskByUserId", {
                "surveyInfoId":${dto.id},
                "returnType": "updateCaseTaskType",
                "btnCode": "updateTaskType"
            }, function (v, e, p) {
                var list = e.data.results.surveyTaskTypeDtoList;
                $("#div_task_type_2 label").remove();
                for (var i = 0; i < list.length; i++) {
                    var val = list[i].surveyBusinessTaskType;

                    var selectType = list[i].selectType;//1、未分配；2、已分配，没有做方向；3已分配，并做了方向
                    //分派调查员时：1、初次分派全部默认选中；2、同一调查员二次分派，根据selectType做区分
                    if (selectType == 1) {
                        $("#div_task_type_2_task").append("<label><input type='checkbox' name='chkTaskIds' value='" + val.taskInfoId + "'>" + val.taskInfoName + "</label> ");
                    } else if (selectType == 2) {
                        $("#div_task_type_2_task").append("<label><input type='checkbox' checked name='chkTaskIds' value='" + val.taskInfoId + "'>" + val.taskInfoName + "</label> ");
                    } else if (selectType == 3) {
                        $("#div_task_type_2_task").append("<label style='color:#808080' ><input type='checkbox' checked disabled='disabled' name='chkTaskIds' value='" + val.taskInfoId + "'>" + val.taskInfoName + "</label> ");
                    }
                }
            });
        } else if (type == 15) {
            $("#div_survey_info_1").hide();
            $("#div_survey_info_2").show();
        } else if (type == 16) {
            $("#div_survey_item_1").hide();
            $("#div_survey_item_2").show();
        } else if (type == 17) {
            $("#div_survey_case_no_1").hide();
            $("#div_survey_case_no_2").show();
        } else if (type == 18) {
            $("#div_end_time_3").show();
        } else if (type == 19) {
            $("#div_survey_id_number_1").hide();
            $("#div_survey_id_number_2").show();
        } else if (type == 'updInsureTime') {
            $("#div_insure_time_1").hide();
            $("#div_insure_time_2").show();
        } else if (type == 'updDangerTime') {
            $("#div_danger_time_1").hide();
            $("#div_danger_time_2").show();
        } else if (type == 'updDangerAddress') {
            $("#div_danger_address_1").hide();
            $("#div_danger_address_2").show();
        } else if (type == 'updInsureName') {
            $("#div_insure_name_1").hide();
            $("#div_insure_name_2").show();
        } else if (type == 'updHandleId') {
            $("#div_handle_id_1").hide();
            $("#div_handle_id_2").show();
        } else if (type == 'updHzContactName') {
            $("#div_hz_contact_name_1").hide();
            $("#div_hz_contact_name_2").show();
        }else if (type == 'updHzContactTel') {
            $("#div_hz_contact_tel_1").hide();
            $("#div_hz_contact_tel_2").show();
        } else if (type == 'cooperativeCompany') {
            $("#div_survey_cooperative_company_1").hide();
            $("#div_survey_cooperative_company_2").show();
        } else if (type == 'subsidiaryCompany') {
            $("#div_survey_subsidiary_company_1").hide();
            $("#div_survey_subsidiary_company_2").show();
        } else if (type == 'healthInsuranceCompany') {
            $("#div_survey_health_insurance_company_1").hide();
            $("#div_survey_health_insurance_company_2").show();
        } else if (type == 'taskNumber') {
            $("#div_survey_task_number_1").hide();
            $("#div_survey_task_number_2").show();
        }
    }

    var loadEntrustOrg = function () {
        var orgId = "${dto.entrustOrgId}";
        ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId", {
            surveyCode: 'consignor',
            menuType: 1,
            btnCode: 1001
        }, function (v, e, p) {
            $("#entrustOrg option").remove();
            $("#entrustOrg").append("<option value=''>请选择</option>");
            console.log(e.data);
            for (var i = 0; i < e.data.results.length; i++) {
                var val = e.data.results[i];
                if (orgId == val.id) {
                    $("#entrustOrg").append("<option selected='selected' value='" + val.id + "'>" + val.name + "</option>");
                } else {
                    $("#entrustOrg").append("<option value='" + val.id + "'>" + val.name + "</option>");
                }
            }
        });
    }

    var loadBelongUser = function () {
        var belongUserId = "${dto.belongUserId}";
        ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId", {
            surveyCode: 'finalJudgmentUser',
            menuType: 1,
            btnCode: 1000
        }, function (v, e, p) {
            $("#belongUserId option").remove();
            $("#belongUserId").append("<option value=''>请选择</option>");
            console.log(e.data);
            for (var i = 0; i < e.data.results.length; i++) {
                var val = e.data.results[i];
                if (belongUserId == val.userId) {
                    $("#belongUserId").append("<option selected='selected' value='" + val.userId + "'>" + val.userName + "</option>");
                } else {
                    $("#belongUserId").append("<option value='" + val.userId + "'>" + val.userName + "</option>");
                }
            }
        });
    }

    var loadReviewUser = function (itemId, reviewUserId) {
        ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId", {
            surveyCode: 'finalJudgmentUser',
            menuType: 1,
            btnCode: 1000
        }, function (v, e, p) {
            $("#reviewUserId" + itemId + " option").remove();
            $("#reviewUserId" + itemId).append("<option value=''>请选择</option>");
            console.log(e.data);
            for (var i = 0; i < e.data.results.length; i++) {
                var val = e.data.results[i];
                if (reviewUserId == val.userId) {
                    $("#reviewUserId" + itemId).append("<option selected='selected' value='" + val.userId + "'>" + val.userName + "</option>");
                } else {
                    $("#reviewUserId" + itemId).append("<option value='" + val.userId + "'>" + val.userName + "</option>");
                }
            }
        });
    }

    var updTaskOK = function (surveyCaseId) {
        $("#surveyCaseId").val(surveyCaseId);
    }

    var cliUpdTaskPrice = function (id, type) {
        $("#div_survery_11_" + id).hide();
        $("#div_survery_22_" + id).show();
        <%--if("${menuCode}" == 'survey-list'){--%>
        <%--$("#span_survey_price2_" + id).hide();--%>
        <%--}--%>
        if ("${dto.price1IsCalc}" == 1) {
            $("#span_survey_price1_" + id).hide();
        }
        if ("${dto.price2IsCalc}" == 1) {
            $("#span_survey_price2_" + id).hide();
        }
    }

    var delDirection = function (id, directionId, btnCode, ajax) {
        var url = "${ctx}/survey/case/sic/operate", param = {"id": id, "directionId": directionId, "btnCode": btnCode};
        if (confirm('是否确认？')) {
            ajaxSubmit(url, param, function (v, e, p) {
                alert(e.data.msg);
                location.reload();
            })
        }
    }
    var downFile = function (surveyCno, surveyInfoId) {
        var url = "${ctx}/sftp/survey/downSftp",
            param = {"surveyCno": surveyCno, "uploadType": "allFile", "surveyInfoId": surveyInfoId};
        if (confirm('是否确认下载？')) {
            ajaxSubmit(url, param, function (v, e, p) {
                var path = e.data.results.path;
                path = path.replace("/mnt/", "https://ddrapi.shlefan.com/");
//                window.open(path);
                window.location.href = path;
            })
        }
    }
    var downFileEntrust = function (modelId, surveyInfoId, downType) {
        //先刷新报告
        if (modelId == 5) {//互助
            if (confirm('是否确认下载？')) {
                var url = "${ctx}/survey/case/operateAssign";
                var param = {"btnCode": "generateTest", "surveyInfoId": surveyInfoId};
                ajaxSubmit(url, param, function (v, e, p) {
                    url = "${ctx}/sftp/survey/downSftp";
                    param = {"surveyInfoId": surveyInfoId, "uploadType": "entrust", "ext": "xlsx", "type": "help"};
                    ajaxSubmit(url, param, function (v, e, p) {
                        var path = e.data.results.path;
                        path = path.replace("/mnt/", "https://ddrapi.shlefan.com/");
                        window.location.href = path;
                    })
                })
            }
        } else {
            if (confirm('是否确认下载？')) {
                var url = "${ctx}/survey/case/operate";
                var param = {"id": "${dto.id}", "btnCode": "generateReport", "surveyInfoId": "${dto.id}"};
                ajaxSubmit(url, param, function (v, e, p) {
                    var returnCode = e.data.results.returnCode;
                    if (returnCode == 'downTransfer') {
                        var path = e.data.results.path; // 如果案件时二调案件，会直接返回路径
//                        path = path.replace("/mnt/","https://ddrapi.shlefan.com/");
                        window.location.href = path;
                    } else {
                        url = "${ctx}/sftp/survey/downSftp";
                        param = {
                            "surveyInfoId": surveyInfoId,
                            "uploadType": "entrust",
                            "ext": "doc",
                            "downType": downType ? downType : ""
                        };
                        ajaxSubmit(url, param, function (v, e, p) {
                            var path = e.data.results.path;
                            path = path.replace("/mnt/", "https://ddrapi.shlefan.com/");
                            window.location.href = path;
                        })
                    }
                })

                //新版本
                <%--$.ajax({--%>
                <%--    url:"${ctx}/survey/case/ajaxData",--%>
                <%--    data:{--%>
                <%--        id : surveyInfoId,//案件IDsurveyInfoId--%>
                <%--        btnCode : "1300",--%>
                <%--        downType : downType // word pdf--%>
                <%--    },--%>
                <%--    success:function(res,param){--%>
                <%--        console.log(param.data.files);--%>
                <%--        for (var i = 0; i < param.data.files.length; i++) {--%>
                <%--            var item = param.data.files[i];--%>
                <%--            var iframe = document.createElement("iframe");--%>
                <%--            iframe.style.display = "none"; // 防止影响页面--%>
                <%--            iframe.style.height = 0; // 防止影响页面--%>
                <%--            iframe.src = item.httpFilePath;--%>
                <%--            document.body.appendChild(iframe); // 这一行必须，iframe挂在到dom树上才会发请求--%>
                <%--            // // 5分钟之后删除（onload方法对于下载链接不起作用，就先抠脚一下吧）--%>
                <%--            // setTimeout(function(){--%>
                <%--            //     iframe.remove();--%>
                <%--            // }, 5 * 60 * 1000);--%>
                <%--        }--%>
                <%--    }--%>
                <%--})--%>
            }
        }
    }


    function _createIFrame(url, triggerDelay, removeDelay) {
        //动态添加iframe，设置src，然后删除
        setTimeout(function () {
            var frame = $('<iframe style="display: none;" class="multi-download"></iframe>');
            frame.attr('src', url);
            $(document.body).after(frame);
            setTimeout(function () {
                frame.remove();
            }, removeDelay);
        }, triggerDelay);
    }

    //仅下载报告
    var downFileReport = function (modelId, surveyInfoId) {
        //先刷新报告
        if (modelId == 5) {//互助
            if (confirm('是否确认下载？')) {
                var url = "${ctx}/survey/case/operateAssign";
                var param = {"btnCode": "downFileReport", "surveyInfoId": surveyInfoId};
                ajaxSubmit(url, param, function (v, e, p) {
                    var path = e.data.results[0];
                    path = path.replace("/mnt/", "https://ddrapi.shlefan.com/");
                    window.location.href = path;
                })
            }
        } else {
            if (confirm('是否确认下载？')) {
                var url = "${ctx}/survey/case/operateAssign";
                var param = {"id": "${dto.id}", "btnCode": "downFileReport", "surveyInfoId": "${dto.id}"};
                ajaxSubmit(url, param, function (v, e, p) {
                    var path = e.data.results[0];
                    path = path.replace("/mnt/", "https://ddrapi.shlefan.com/");
                    window.location.href = path;
                })
            }
        }
    }

    var updDirection = function (id, directionId, btnCode, ajax, menuCode) {
        var width = $(document.body).outerWidth();
        var height = $(parent.document).outerHeight() - 80;
        var title = "操作";
        var url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=direction&opr=upd&directionId=" + directionId + "&orgReview=1&menuCode=help-review";
        openDialog({
            frame: true,
            title: title,
            height: height,
            width: width,
            url: url
        });
    }
    var directionFiles = function (id, btnCode, surveyCno) {
        var width = $(document.body).outerWidth();
        // var height = $(document).outerHeight() - 20;
        var height = $(parent.document).outerHeight() - 80
        console.log('---', height)
        var title = "附件详情";
        openDialog({
            frame: true,
            title: title,
            height: height,
            width: width,
            url: "${ctx}/survey/case/sic/directionFiles?directionId=" + id + "&surveyCno=" + surveyCno
        });
    }
    var make = function (id, btnCode) {
        var height = 200, width = 800;
        title = "操作";
        url = "${ctx}/survey/case/operateView?id=" + id + "&btnCode=" + btnCode + "&roleId=60";//查询拥有制作报告权限的角色用户
        openDialog({
            frame: true,
            title: title,
            height: height,
            width: width,
            url: url
        });
    }

    function changeOrg() {

        $("#div_source_ok_1").hide();
        $("#div_source_ok_2").show();
        $("#div_source_ok_4").show();
        ajaxSubmit("${ctx}/baseSurvey/selectInfoByRelationId", {
            surveyCode: 'franchisee',
            menuType: 1,
            btnCode: 1000
        }, function (v, e, p) {
            $("#sourceOrgName option").remove();
            $("#sourceOrgName").append("<option value=''>请选择</option>");
            console.log(e.data);
            for (var i = 0; i < e.data.results.length; i++) {
                var val = e.data.results[i];
                $("#sourceOrgName").append("<option value='" + val.id + "," + val.type + "'>" + val.name + "</option>");
            }
        });
    }

    function loadSurveyOrg() {

    }

    function changeOrgType(obj) {
        var result = obj.value.split(",");
        var franchiseeType = result[1] == '' ? 1 : result[1];
        if (franchiseeType == 1) {//直营
            $("#div_source_ok_3").show();
        } else {
            $("#div_source_ok_3").hide();
        }
        $("#sourceOrgId").val(result[0]);
    }

    function showMoneyRemark(type, obj) {
        var dialog = $(".dalogsTip");
        if (dialog.is(":hidden")) {
            dialog.show();
        } else {
            dialog.hide();
        }
        var position = $(obj).position();
        var left = position.left;
        if (type == 2) {
            left = left - 360
        } else {
            left = left + 120
        }
        $(".dalogsTip").offset({
            left: left,
            top: $(obj).parents('.info-1129').position().top + position.top
        });
        var content = $(obj).attr("data-value");
        if (content != '') {
            $(".dalogsTip .d_content").html(content);
        } else {
            $(".dalogsTip .d_content").html("暂无数据");
        }
    }

    function showReLoossesRemark(type, obj) {
        var dialog = $(".dalogsTip");
        if (dialog.is(":hidden")) {
            dialog.show();
        } else {
            dialog.hide();
        }
        var position = $(obj).position();
        var left = position.left;
        if (type == 2) {
            left = left - 360
        } else {
            left = left + 120
        }
        $(".dalogsTip").offset({
            left: left,
            top: $(obj).parents('.info-1129').position().top + position.top
        });
        var content = $(obj).attr("data-value");
        if (content != '') {
            $(".dalogsTip .d_content").html(content);
        } else {
            $(".dalogsTip .d_content").html("暂无数据");
        }

    }

    var updOrgSurveyMoney = function (orgId) {
        $("#div_org_survey_money1" + orgId).hide();
        $("#div_org_survey_money2" + orgId).show();
    }

    var updOrgTaskRemark = function (orgid) {
        $("#div_org_task_remark" + orgid).show();
    }

    var updReLoossesRemark = function (orgid) {
        $("#div_org_summary" + orgid).show();
    }
    var updOrgSurvey2Money = function (orgId) {
        $("#div_org_survey2_money1" + orgId).hide();
        $("#div_org_survey2_money2" + orgId).show();
    }
    var updOrgInfo = function (itemId, reviewUserId) {
        $("#div_org_survey_review1" + itemId).hide();
        $("#div_org_survey_review2" + itemId).show();
        loadReviewUser(itemId, reviewUserId)
    }

    var orgSurveyMoneyOK = function (surveyInfoId, surveyAssignOrgId, orgId, btnCode) {
        $("#caseId").val(surveyInfoId);
        $("#surveyInfoId").val(surveyInfoId);
        $("#surveyAssignOrgId").val(surveyAssignOrgId);
        $("#surveyOrgId").val(orgId);
        $("#orgInfoBtnCode").val(btnCode);
        // var moneyRegular1=/^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/;
        // var moneyRegular=/^([1-9]\d{0,9})([.]?|(\.\d{1,2})?)$/;
        var newInscompanyMoney = $("#newInscompanyMoney" + surveyAssignOrgId).val();
        /*if(!moneyRegular.test(newInscompanyMoney)){
            alert("金额的输入格式不正确,请确认!");
            return false;
        }*/
        if (newInscompanyMoney < 0) {
            alert("金额的输入格式不正确,请确认!");
            return false;
        }
        if (newInscompanyMoney == "" || newInscompanyMoney == null || newInscompanyMoney == undefined) {
            $("#newInscompanyMoney" + surveyAssignOrgId).val(newInscompanyMoney);
        }
    }

    var orgTaskRemark = function (surveyInfoId, surveyAssignOrgId, orgId, btnCode) {
        $("#caseId").val(surveyInfoId);
        $("#surveyInfoId").val(surveyInfoId);
        $("#surveyAssignOrgId").val(surveyAssignOrgId);
        $("#surveyOrgId").val(orgId);
        $("#orgInfoBtnCode").val(btnCode);
    }

    var updOrgSummary = function (surveyInfoId, surveyAssignOrgId, orgId, btnCode) {
        $("#caseId").val(surveyInfoId);
        $("#surveyInfoId").val(surveyInfoId);
        $("#surveyAssignOrgId").val(surveyAssignOrgId);
        $("#surveyOrgId").val(orgId);
        $("#orgInfoBtnCode").val(btnCode);
    }

    function showDirectionView(id, obj) {
        var dialog = $(".dalogsAdd");
        dialog.show();
        var html = "";

        html += '<div class="main22">';
        html += '<div class="step-contain">';
        html += '<div class="title">新增调查方向操作流程</div>';
        html += '<div class="icon-step">';
        html += '<div class="index active">1</div>';
        html += '<div class="line"></div>';
        html += '<div class="index">2</div>';
        html += '<div class="line"></div>';
        html += '<div class="index">3</div>';
        html += '</div>';
        html += '<div class="content-step">';
        html += '<div class="c-text">输入方向名称</div>';
        html += '<div class="c-text">上传方向附件</div>';
        html += '<div class="c-text">填写方向内容</div>';
        html += '</div>';
        html += '<div class="border7"></div>';
        html += '<div class="content">';
        html += '<div class="c-title"><div class="icon-step" style="width: 30px;display: inline-block; margin-right: 8px;"><div class="index active">1</div></div>请输入方向名称</div>';
        html += '<div class="c-input"><input type="text" id="directionName" name="directionName" value="" autocomplete="off" style="padding-left:10px;"></div>';
        html += '<div class="btn-area">';
        html += '<div class="c-btn" onclick="javascript:hidDirectionView();" style="cursor:pointer" >取消</div>';
        html += '<div class="c-btn c-btn2" type="submit" onclick="return addDirection(' + id + ',\'direction\',false)" style="cursor:pointer">确定</div>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        $(".d_contentAdd").html(html);
        showInput();
    }

    var addDirectionNew = function (id, btnCode, ajax, typeNum) {
        var height = $(parent.document).outerHeight() - 80;
        var width = $(document.body).outerWidth();
        var title = "";
        var url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode + "&menuCode=" + '${menuCode}' + "&again=" + '${again}' + '&openType=addNew';
        if (typeNum == 2) {
            url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode + "&menuCode=" + '${menuCode}' + "&orgReview=1";
        }
        openDialog({
            frame: true,
            title: title,
            height: height,
            width: width,
            url: url,
            load: true
        });
    }

    function showInput() {
        setTimeout("document.getElementById(\"directionName\").focus()", 50);
    }

    function hidDirectionView() {
        var dialog = $(".dalogsAdd");
        dialog.hide();
    }

    var updateIsSun = function (id, isSun) {
        if (isSun == 0) {
            isSun = 1;
        } else if (isSun == 1) {
            isSun = 0;
        }
        $.ajax({
            url: "${ctx}/survey/case/operate?id=" + id + "&isSun=" + isSun + "&btnCode=updateIsSun",
            type: 'POST',
            dataType: 'json',
            success: function (v, data) {
                location.reload();
            }
        });
    }


    var addDirection = function (id, btnCode, ajax) {
        var directionName = $("#directionName").val().trim();
        if (directionName == null || directionName == "") {
            alert("请填写方向名称");
            return;
        }
        //验证方向名称是否存在
        var url = "${ctx}/survey/case/operate", param = {
            "btnCode": "validateDirectionName",
            "id": $("#surveyInfoId").val(),
            "directionName": directionName,
            "directionId": ""
        };
        ajaxSubmit(url, param, function (v, e, p) {
            if (e.data.code === '0000') {
                var results = e.data.results;
                if (results) {
                    if (results.length > 0) {
                        alert("方向名称已存在");
                        return;
                    }
                }

                height = $(parent.document).outerHeight() - 80;
                width = $(document.body).outerWidth();
                title = ""
                url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode + "&directionName=" + directionName + "&menuCode=" + '${menuCode}' + "&orgReview=1";
                openDialog({
                    frame: true,
                    title: title,
                    height: height,
                    width: width,
                    url: url,
                    load: true
                });
            }
        })

    }


    function oprBack(id, btnCode) {
        var height = 400, width = 900;
        var title = null, url = null;
        title = "退回";
        url = "${ctx}/survey/case/back?id=" + id + "&btnCode=1301";
        openDialog({
            frame: true,
            title: title,
            height: height,
            width: width,
            url: url,
            load: true
        });
    }

    //使用场景：“代理委托”录入案件时，可直接“分派调查员”
    autoOpenInfo();

    function autoOpenInfo() {
        var autoOpenInfo = $("#autoOpenInfo").val();
        var menuCode = '${menuCode}';
        var autoOpenInfoId = $("#surveyInfoId").val();
        if (menuCode == 'assign-list' && autoOpenInfo == 1) {
            //自动开分派页面
            operate(autoOpenInfoId, 112, false)
        }
    }

    function openAssignOrgList() {
        location.href = "${ctx}/survey/case/list?menuCode=assign-list&autoOpenInfo=0";
    }

    function adjustmentRecord(id, btnCode, codeType) {
        var height = 400, width = 900;
        var title = null, url = null;
        title = "价格调整记录";
        url = "${ctx}/survey/case/operateView?id=" + id + "&btnCode=" + btnCode + "&codeType=" + codeType + "&dataTimeDesc=true";
        openDialog({
            frame: true,
            title: title,
            height: height,
            width: width,
            url: url
        });
    }

    function updTaskType(surveyInfoId, assignOrgId, returnType, btnCode) {
        $.ajax({
            url: '${ctx}/survey/case/operate',
            data: {
                surveyInfoId: surveyInfoId,
                assignOrgId: assignOrgId,
                returnType: returnType,
                btnCode: btnCode
            },
            success: function (e, res) {
                res = res.data
                if (res.isSuccess) {
                    var data = res.results.surveyTaskTypeDtoList
                    openIframe(data, surveyInfoId, assignOrgId, returnType)
                }
            }
        })


    }

    function openIframe(data, surveyInfoId, assignOrgId, returnType) {

        var ids = []

        var html_1 = '<div class="ll-main"><div class="label-btns">',
            html_2 = '',
            html_3 = '</div><div class="ll-btn"><span>确定</span><svg t="1589009340081" class="icon" viewBox="0 0 1111 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="7189" width="24" height="24"><path d="M1081.979062 0.255787a3069.442132 3069.442132 0 0 0-693.182348 640.746045L119.367905 414.971524l-119.367194 102.314737 465.532056 506.713739A3064.667444 3064.667444 0 0 1 1110.115615 70.597169l-28.136553-70.597169" fill="#FFFFFF" p-id="7190"></path></svg></div></div>'

        data.map(function (cur, i) {
            var className = ''
            var id = ''
            var name = ''
            if (returnType == 'updateOrgTaskType') {
                id = cur.surveyTaskType.taskId
                name = cur.surveyTaskType.taskName
            } else if (returnType == 'updateCaseTaskType') {
                id = cur.surveyBusinessTaskType.taskInfoId
                name = cur.surveyBusinessTaskType.taskInfoName
            }
            if (cur.selectType == 1) {
                className = ''
            } else if (cur.selectType == 2) {
                className = 'active'
                ids.push(id.toString())
            } else if (cur.selectType == 3) {
                className = 'disabled'
                ids.push(id.toString())
            }
            html_2 += '<div class="label-btn ' + className + '" data-id="' + id + '">' + name + '</div>'

        })

        var _html = html_1 + html_2 + html_3
        layui.use('layer', function () {
            layer = layui.layer
            // var _width = $(document).width() * 0.9
            // _width = _width > 650 ? 650 : _width
            layer.open({
                type: 1,
                title: '请选择任务类型',
                area: ['660px', '600px'],
                offset: '100px',
                content: _html, //iframe的url，no代表不显示滚动条
                success: function () {
                    $('.ll-main .label-btns').on('click', '.label-btn', function () {
                        var _this = $(this)
                        var id = _this.attr('data-id')
                        if (_this.hasClass('active')) {
                            _this.removeClass('active')
                            var index = ids.indexOf(id)
                            if (index > -1) {
                                ids.splice(index, 1)
                            }
                        } else {
                            _this.addClass('active')
                            ids.push(id)
                        }
                    })
                    $('.ll-main .ll-btn').click(function () {
                        $.ajax({
                            url: '${ctx}/survey/case/operate',
                            data: {
                                btnCode: 'updateTaskTypeInfo',
                                id: surveyInfoId,
                                assignOrgId: assignOrgId,
                                returnType: returnType,
                                taskIds: ids.join(',')
                            },
                            success: function (e, res) {
                                layer.closeAll()
                                reload();
                            }
                        })

                    })
                }
            });
        })

    }

    $(function () {
        $('.case-reason-state').click(function () {
            var _this = $(this)
            _this.addClass('poi-no')
            setTimeout(function () {
                _this.removeClass('poi-no')
            }, 1500)
            $.ajax({
                url: "${ctx}/staff/operate?operateCode=performance&btnCode=upInvestigatorCaseOpinion&surveyInvestigatorCaseId=" + '${investigatorCaseIdS}',
                type: 'POST',
                dataType: 'json',
                success: function (v, data) {
                    $('.case-resaon').addClass('case-resaon-green');
//                    $('.main').removeAttr('style');
                    $('.case-reason-state').hide()
                }
            })
        })


        $('.updateOrder').click(function () {
            $('.updateOrder').hide()
            $('.updateSubmit').show()
            $('.updateCancel').show()
            $('#tablePro').hide()
            $('#tableCopy').show()
            $("#tableCopy").tableDnD({
                //滚动的速度
                scrollAmount: 10,
                onDragClass: 'highlight',
                //当拖动排序完成后
                onDrop: function (table, row) {
                    //获取id为table的元素
                    var table = document.getElementById("tableCopy");
                    //获取table元素所包含的tr元素集合
                    var tr = table.getElementsByTagName("tr");
                    //遍历所有的tr
                    for (var i = 0; i < tr.length; i++) {
                        //获取拖动排序结束后新表格中，row id的结果
                        var rowid = tr[i].getAttribute("id");
                        //console.log("排序完成后表格的第 " + (i+1) + " 行id为 : " + rowid);
                    }

                    //console.log( $('#table').tableDnDSerialize());
                },
                onDragStart: function (table, row) {
                    //console.log(row.id);
                },
            });
        })

        $('.updateCancel').click(function () {
            $('.updateOrder').show()
            $('.updateSubmit').hide()
            $('.updateCancel').hide()
            $('#tablePro').show()
            $('#tableCopy').hide()
            $('#tableCopy').html($('#tablePro').html())

        })

        $('.updateSubmit').click(function () {
            var ids = []
            $('#tableCopy tr').map(function (i, cur) {
                ids.push($(cur).attr('id'))
            })
            ids.shift()
            $('.updateOrder').show()
            $('.updateSubmit').hide()
            $('.updateCancel').hide()
            $('#tablePro').show()
            $('#tableCopy').hide()
            $('#tableCopy').html($('#tablePro').html())

            $.ajax({
                url: "${ctx}/survey/case/operate?ids=" + ids.join(',') + "&btnCode=updateDirectionSort&id=" + '${dto.id}',
                type: 'POST',
                dataType: 'json',
                success: function (v, data) {
                    location.reload();
                }
            });
        })

        layui.use(['table', 'layer'], function () {
            layer = layui.layer
            table = layui.table
        })
        $('body').on('click', '.record', function () {
            var atr = $(this).attr('data-attr')
            var list = newRecord[atr]

            showCaseList(list)

        })
        //加载记录数据
        var url = "${ctx}/survey/case/operate", param = {"id": $("#surveyInfoId").val(), "btnCode": "ajaxRecordData"};
        ajaxSubmit(url, param, function (v, e, p) {
            var records = e.data.results;
            newRecord = {}
            $('.record').map(function () {
                var _this = $(this)
                var attr = _this.attr('data-attr')
                var num = 0
                var list = []
                records.map(function (cur) {
                    if (cur.updAttr == attr) {
                        num++
                        list.push(cur)
                    }
                })
                Object.assign(newRecord, {[attr]: list})
                if (num > 0) {
                    _this.addClass('active')
                }
                _this.find('.num').text(num)

            })
        })

        function showCaseList(list) {
            var _height = $(document).height() * 0.9
            var _width = $(document).width() * 0.98
            openIndex = layer.open({
                type: 1,
                title: '记录列表',
                area: ['1000px', '600px'],
                offset: '100px',
                content: $('#table-content-child-h').html(),
            });
            var _cols_child = [
                [{
                    field: 'updUserName',
                    width: 120,
                    title: '更改人',
                }, {
                    field: 'updTime',
                    width: 180,
                    title: '更改时间',
                    templet: function (d) {
                        return layui.util.toDateString(d.updTime, 'yyyy-MM-dd HH:mm:ss')
                    }
                }, {
                    field: 'updBeforeValue',
                    minWidth: 150,
                    title: '更改前的值',
                }, {
                    field: 'updAfterValue',
                    minWidth: 150,
                    title: '更改后的值',
                }, {
                    field: 'updRemark',
                    minWidth: 140,
                    title: '备注',
                }
                ]
            ]

            var childTable = table.render({
                id: "test-child",
                elem: '#test-child',
                cols: _cols_child,
                page: false,
                height: '500px',
                drag: false,
                event: true,
                data: list,
            })
        }
    })

    var downFileDirectionFile = function (directionId, directionName, surveyCno) {
        var url = "${ctx}/sftp/survey/downSftp", param = {
            "directionId": directionId,
            "uploadType": "directionFile",
            "directionName": directionName,
            "surveyCno": surveyCno
        };
        if (confirm('是否确认下载？')) {
            ajaxSubmit(url, param, function (v, e, p) {
                var path = e.data.results;
//                path = path.replace("/mnt/sftp/files/","https://ddrapi.shlefan.com/");
//                window.open(path);
                window.location.href = path;
            })
        }
    }

</script>
</body>
</html>
