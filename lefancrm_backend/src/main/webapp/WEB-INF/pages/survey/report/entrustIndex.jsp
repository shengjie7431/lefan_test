<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">
    <link rel="stylesheet" href="${ctx}/css/lefan23.css?v=1" type="text/css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">

    <title>保司报表</title>
    <style>
        * {
            font-size: 14px;
            color: #101010;
        }

        .main {
            width: 100%;
        }

        .header {
            margin: 0 auto;
            padding: 10px 0;
            width: 90%;
            border-bottom: 1px solid #bbb;
            display: flex;
            justify-content: space-between;
        }

        .header .choose-in {
            /*padding-left: 50px;*/
            display: flex;
        }

        .header .choose-in .name {
            /*line-height: 40px;*/
            /*padding-right: 20px;*/

        }

        .header .choose-in select {
            width: 250px;
            height: 40px;
        }

        .header .header-bar {
            /*width: 60%;*/
            display: flex;
            justify-content: flex-end;
            height: 35px;
        }

        .header .header-bar-title {
            width: 60px;
            height: 38px;
            line-height: 38px;
            text-align: center;
        }

        .btn-png {
            width: 60px;
            height: 38px;
            line-height: 38px;
            text-align: center;
            border: 1px solid #bbb;
            cursor: pointer;
            margin-left: 7px;
        }

        .header .header-bar .selects {
            padding-left: 10px;
            display: flex;
            align-items: center;
        }

        .header .header-bar .selects select {
            width: 133px;
            height: 38px;
            line-height: 38px;
        }

        .header .header-bar .header-choose-bar {
            width: 60px;
            height: 38px;
            line-height: 38px;
            border: 1px solid #bbb;
            border-right: none;
            background-color: #fff;
            color: #333;
            text-align: center;
            cursor: pointer;
        }

        .header .header-bar div.active {
            border: 1px solid #3BA9FF;
            background-color: #3BA9FF;
            color: #fff;
        }

        .header .header-bar .border-r {
            border-right: 1px solid #bbb;
        }

        .contain {
            width: 90%;
            margin: 0 auto;
            padding-top: 10px;
        }

        .contain .cell {
            width: 100%;
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
        }

        .contain .cell .charts-all .title {
            width: 100%;
            height: 30px;
            line-height: 30px;
            font-size: 16px;
            font-weight: 600;
            padding-bottom: 10px;
        }

        .contain .cell .charts-all .content {
            width: 100%;
            display: flex;
            min-height: 310px;
        }

        .contain .cell .charts-all .charts-info {
            padding: 10px 1%;
            width: 49%;
            height: 310px;
            border-left: 1px solid #bbb;
        }

        .contain .cell .charts-all .charts-info .charts-tr {
            width: 100%;
            display: flex;
            height: 40px;
        }

        .contain .cell .charts-all .charts-info .charts-tr:first-of-type {
            font-weight: 600;
        }

        .contain .cell .charts-all .charts-info .charts-tr .charts-td {
            width: 28%;
            height: 20px;
            line-height: 20px;
            padding: 10px 0;
            text-align: center;
        }

        .contain .cell .charts-info .charts-tr .charts-td:first-of-type {
            width: 30%;
            text-align: left;
        }

        .contain .cell .charts {
            padding: 10px 1%;
            width: 22%;
            height: 400px;
            border: 1px solid #bbb;
        }

        .contain .cell .charts-map {
            width: 70%;
            height: 100%;
        }

        .contain .cell div.wid100 {
            padding: 10px 0;
            width: 100%;
        }

        .contain .cell div.wid70 {
            padding: 10px 1%;
            width: 68%;
        }

        .contain .cell div.wid50 {
            padding: 10px 1%;
            width: 49%;
        }

        .contain .cell div.wid22 {
            padding: 10px 1%;
            width: 22%;
        }

        .contain .cell div.wid69 {
            padding: 10px 1%;
            width: 69%;
        }

        .contain .cell div.wid29 {
            padding: 10px 1%;
            width: 29%;
        }

        .contain .cell .charts-half {
            padding: 10px 1%;
            width: 50%;
            /* height: 310px; */
        }

        .contain .cell .charts-100 {
            padding: 10px 1%;
            width: 98%;
            /* height: 310px; */
        }
        .baseInfo {
            width: 22%;
            padding: 10px 1%;
            border: 1px solid #bbb;

        }

        .baseInfo .title {
            width: 100%;
            text-align: left;
            font-size: 16px;
            font-weight: 600;

        }

        .baseInfo .infos {
            padding: 20px 0 0 0;
            width: 100%;
            display: flex;
            flex-wrap: wrap;
        }

        .baseInfo .infos .info {
            width: 49%;
            padding: 10px 0;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .baseInfo .infos a.wid98 {
            width: 98%;
        }

        .baseInfo .infos div.wid98 {
            width: 98%;
        }

        .keyWords {
            width: 52%;
            padding: 10px 1%;
            border: 1px solid #bbb;
        }

        .keyWords .title {
            width: 100%;
            text-align: left;
            font-size: 16px;
            font-weight: 600;

        }

        .keyWords .content {
            padding: 26px 0 0 0;
            width: 100%;
            display: flex;
            flex-wrap: wrap;

        }

        .keyWords .content .keybar {
            width: 16%;
            padding-bottom: 40px;
        }

        .keyWords .content .keybar.two {
            width: 16.6%;
        }

        .keyWords .content .keybar .name {
            width: 100%;
            line-height: 20px;
            padding-bottom: 14px;
            text-align: center;
            font-weight: 400;
        }

        .keyWords .content .keybar .num {
            width: 100%;
            line-height: 50px;
            font-size: 18px;
            font-weight: bold;
            text-align: center;
            word-break: break-word;
        }
        .keyWords .content .keybar .rate {
            width: 100%;
            font-size: 10px;
            text-align: center;
        }

        .keyWords .content .keybar .rate.color1 {
            color: #d70606!important;
        }
        .keyWords .content .keybar .rate.color2 {
            color: #398800!important;
        }

        .institution {
            width: 100%;
            border: 1px solid #bbb;
            padding-bottom: 10px;
        }

        .institution .i-header {
            padding: 20px 2% 20px 1%;
            width: 97%;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .institution .i-header .title a {
            line-height: 24px;
            font-size: 16px;
            color: #b3b3b3;
            padding-right: 10px;
            cursor: pointer;
        }

        .institution .i-header .title a.selected {
            color: #333;
        }

        .institution .i-header .search {
            display: flex;
        }

        .institution .i-header .search input {
            padding-left: 10px;
            width: 260px;
            height: 40px;
            line-height: 40px;
            border: 1px solid #bbb;
        }

        .institution .i-header .search .btn {
            width: 60px;
            line-height: 40px;
            background-color: #3BA9FF;
            color: #fff;
            text-align: center;
        }

        .institution .i-table {
            width: 100%;
            padding: 0 2% 0 3%;
        }

        .institution .i-table .i-thead {
            padding: 10px 0;
            display: flex;
            width: 100%;
            border-bottom: 1px solid #bbb;
        }

        .institution .i-table .i-thead .i-td {
            width: 14.2%;
            height: 20px;
            line-height: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        /* .institution .i-table .i-thead .i-td .i-td-text {
            height: 20px;
           line-height: 20px;
        } */
        .institution .i-table .i-tr {
            padding: 10px 0;
            display: flex;
            width: 100%;
        }

        .institution .i-table .i-tr .i-td {
            width: 14.2%;
            text-align: center;
        }

        .icon-sort {
            display: inline-block;
            width: 10px;
        }

        .icon-up {
            width: 0;
            height: 0;
            border-right: 5px solid transparent;
            border-left: 5px solid transparent;
            border-bottom: 7px solid #b3b3b3;
            margin-bottom: 2px;
        }

        .icon-down {
            width: 0;
            height: 0;
            border-right: 5px solid transparent;
            border-left: 5px solid transparent;
            border-top: 7px solid #b3b3b3;
        }

        .icon-up:hover {
            border-bottom: 7px solid #333;
        }

        .icon-down:hover {
            border-top: 7px solid #333;
        }

        .chartsm-info {
            width: 30%;
            /* height: 96%; */
            /* padding-top: 16px; */
        }

        .list {
            width: 99%;
            margin: 0 auto;
            border: 1px solid #ddd;
        }

        .list #charts5_table{
            height: 611px;
            overflow: hidden;
        }

        .list .bar {
            /*display: none;*/
            /*height: 611px;*/
        }

        .list .li {
            width: 100%;
            padding: 15px 0;
            line-height: 20px;
            display: flex;
            border-bottom: 1px solid #ddd;
        }

        .list .li .name {
            width: 25%;
            padding: 0 4%;
            color: #666;
            min-width: 180px;
        }

        .list .li .num-text {
            width: 18%;
            padding: 0 4%;
            text-align: center;
        }

        .list .li .num-line {
            width: 46%;
            padding: 0 1%;
            display: flex;
            align-items: center;
        }

        .list .li .num-line .line-icon {
            height: 6px;
            background-color: #c23531;
        }

        .list .li .btn-page {
            line-height: 30px;
            padding: 0 5px;
            color: #666;
        }

        .list .li .btn-page span {
            line-height: 30px;
            color: #666;
        }

        .list .li .btn-icon {
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 6px;
            padding-left: 2px;
            width: 22px;
            height: 28px;
            border: 1px solid #ddd;
            background-color: #fff;
            color: #666;
            cursor: pointer;
        }

        .list .li .btn-icon:hover {
            color: #333333;
        }

        .list .li .btn-icon .icon-next:hover {
            border-left: 7px solid #333;

        }

        .list .li .btn-icon .icon-next {
            width: 0;
            height: 0;
            border-top: 6px solid transparent;
            border-bottom: 6px solid transparent;
            border-left: 7px solid #666;
        }

        .list .li .btn-icon .icon-next2 {
            width: 0;
            height: 0;
            border-top: 6px solid transparent;
            border-bottom: 6px solid transparent;
            border-right: 7px solid #666;
        }

        .list .li input {
            padding-left: 10px;
            width: 58px;
            height: 28px;
            line-height: 28px;
            border: 1px solid #ddd;
            background-color: #fff;
        }

        .list .li .btn {
            padding: 0;
            margin: 0 6px;
            width: 48px;
            height: 28px;
            line-height: 28px;
            border: 1px solid #ddd;
            background-color: #fff;
            color: #666;
            text-align: center;
            cursor: pointer;
        }
        .charts-no-data{
            margin-top: 130px;width: 100%;text-align: center;font-size: 30px;color: #999;display: none
        }



        .charts-changes{
            position: absolute;
            top:10px;
            right: 40px;
            width: 200px;
            display: flex;
        }
        .charts-change{
            width: 70px;
            height: 30px;
            line-height: 30px;
            color: #333;
            background-color: #fff;
            border: 1px solid #bbb;
            text-align: center;
            cursor: pointer;
        }
        .charts-change_active{
            color:#fff;
            background-color: #3BA9FF;
            border: 1px solid #3BA9FF;
        }

        .dalogs {
            display: none;
            position: fixed;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.7);
        }

        .dalogs-contain {
            padding: 40px;
            margin: 0 auto;
            width: 100%;
            height: 600px;
            display: flex;
            justify-content: center;
        }

        .dalogs-downfile {
            position: relative;
            width: 560px;
            height: 100%;
            overflow: auto;
            background-color: #fff;
        }

        .dalogs-content {
            /* position: fixed;
            top: 26%;
            left: 30%; */
            padding: 0 40px;
            width: 50%;
            height: 100%;
            /* border: 1px solid #bbb; */
            background-color: #fff;
        }

        .dalogs .d-title {
            padding-top: 40px;
            padding-bottom: 20px;
            width: 100%;
            height: 24px;
            line-height: 24px;
            font-size: 20px;
            text-align: center;
        }

        .dalogs .d-label {
            width: 100%;
            height: 30px;
            line-height: 30px;
            font-size: 14px;
        }

        .dalogs .d-value {
            width: 100%;
            padding: 10px 0;
            display: flex;
        }

        .dalogs .d-value select {
            width: 100%;
            height: 43px;
            border: 1px solid #bbb;
        }

        .dalogs .d-radio {
            width: 135px;
            height: 43px;
            line-height: 43px;
            border: 1px solid #bbb;
            text-align: center;
            background-color: #fff;
            color: #333;
            cursor: pointer;
        }

        .dalogs div.active {
            color: #fff;
            background-color: #3BA9FF;
            border: 1px solid #3BA9FF;
        }

        .dalogs .mr20 {
            margin-right: 20px;
        }

        .dalogs .d-btns {
            padding-top: 30px;
            margin: 0 auto;
            width: 100%;
            display: flex;
            justify-content: space-around;
        }

        .dalogs .d-btn {
            width: 135px;
            height: 43px;
            line-height: 43px;
            border: 1px solid #bbb;
            text-align: center;
            background-color: #fff;
            color: #333;
            cursor: pointer;
        }

        .info-span{
            font-weight: bold;
            font-size: 17px;
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

        .demo3 {
            width: 4px;
            height: 4px;
            border-radius: 2px;
            background: #fff;
            position: absolute;
            animation: demo3 linear 0.8s infinite;
            -webkit-animation: demo3 linear 0.8s infinite;
        }

        .demo3:nth-child(1) {
            left: 24px;
            top: 2px;
            animation-delay: 0s;
        }

        .demo3:nth-child(2) {
            left: 40px;
            top: 8px;
            animation-delay: 0.1s;
        }

        .demo3:nth-child(3) {
            left: 47px;
            top: 24px;
            animation-delay: 0.1s;
        }

        .demo3:nth-child(4) {
            left: 40px;
            top: 40px;
            animation-delay: 0.2s;
        }

        .demo3:nth-child(5) {
            left: 24px;
            top: 47px;
            animation-delay: 0.4s;
        }

        .demo3:nth-child(6) {
            left: 8px;
            top: 40px;
            animation-delay: 0.5s;
        }

        .demo3:nth-child(7) {
            left: 2px;
            top: 24px;
            animation-delay: 0.6s;
        }

        .demo3:nth-child(8) {
            left: 8px;
            top: 8px;
            animation-delay: 0.7s;
        }

        @keyframes demo3 {
            0%,
            40%,
            100% {
                transform: scale(1);
            }

            20% {
                transform: scale(3);
            }
        }

        @-webkit-keyframes demo3 {
            0%,
            40%,
            100% {
                transform: scale(1);
            }

            20% {
                transform: scale(3);
            }
        }

        .d-value .csxzb-input {
            position: relative;
            width: 100%;
            height: 40px;
            line-height: 40px;
        }

        .d-value .csxzb-input #csxzb{
            width: 100%;
            padding-left: 10px;
            height:40px;
            line-height: 40px;
        }

        .d-value .csxzb-input span {
            position: absolute;
            top: 0;
            right: 40px;
        }

        .caseList{
            cursor: pointer;
            color: #c26049;
        }
        .caseList *{
            color: #c26049!important;
        }
        .caseList:hover{
            color: #FF0000;
        }
        .caseList:hover *{
            color: #FF0000;
        }

        .tabbars{
            width: 204px;
            display: flex;
            align-items: center;
        }
        .tabbars .tabbar{
            width: 100px;
            height: 24px;
            line-height: 24px;
            color: #000;
            background-color: #fff;
            border: 1px solid #eee;
            text-align: center;
            cursor: pointer;
        }
        .tabbars .tabbar.active{
            color: #fff;
            background-color:#3ba9ff;
            border: 1px solid #3ba9ff;
        }


        .institution .title{
            font-weight: bold;
            margin: 2px;
        }
        .institution .i-table2 .i-tr .i-td {
            width: 8.5%;
            text-align: center;
        }
        .institution .i-table2 .i-thead  .i-td:first-of-type{
            width: 11.5%;
        }
        .institution .i-table2 .i-tr .i-td:first-of-type{
            width: 6.5%;
        }
    </style>
</head>

<body>
<div class="main">
    <div class="header">
        <div class="choose-in">
            <div class="name">保险公司</div>
            <select name="entrustOrgId" id="entrustOrgId" class="singleSelect form-control" onchange="initData()">
                <c:forEach items="${consignors}" var="item">
                    <option <c:if test="${entrustOrgId == item.id}">selected="selected" </c:if> value="${item.id}" >${item.company}</option>
                </c:forEach>
            </select>
        </div>
        <div class="header-bar">
            <input type="hidden" name="searchType" id="searchType" value="${searchType}">
            <input type="hidden" name="sortAttr" id="sortAttr" value="1">
            <input type="hidden" name="sortType" id="sortType" value="2">
            <div class="header-bar-title">时间</div>
            <div class="header-choose-bar active" data-value="upMonth">上月</div>
            <div class="header-choose-bar" data-value="yesterday">昨天</div>
            <div class="header-choose-bar" data-value="today">今天</div>
            <div class="header-choose-bar" data-value="curWeek">本周</div>
            <div class="header-choose-bar" data-value="curMonth">本月</div>
            <div class="header-choose-bar border-r" data-value="all">全部</div>
            <div class="selects">
                <input type="text" class="layui-input paramTime" readonly id="seTime"
                       placeholder="请选择日期" style="width:200px;">
                <input type="hidden" value="${startTime}" id="startTime">
                <input type="hidden" value="${endTime}" id="endTime">
                <%--<input name="startTime" id="startTime" type="text" value="${startTime}" style="width: 150px;cursor: pointer" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>--%>
                <%--<span style="padding: 0 5px">至</span>--%>
                <%--<input name="endTime" id="endTime" type="text" value="${endTime}" style="width: 150px;cursor: pointer" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>--%>
            </div>
            <div class="selects">
                同比比较时间
                <input type="text" class="layui-input paramTime" readonly id="tbTime"
                       placeholder="请选择日期" style="width:200px;">

                <input type="hidden" value="${tbStartTime}" id="tbStartTime">
                <input type="hidden" value="${tbEndTime}" id="tbEndTime">
                <%--<input name="tbStartTime" id="tbStartTime" type="text" value="${tbStartTime}" style="width: 150px;cursor: pointer" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>--%>
                <%--<span style="padding: 0 5px">至</span>--%>
                <%--<input name="tbEndTime" id="tbEndTime" type="text" value="${tbEndTime}" style="width: 150px;cursor: pointer" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>--%>
            </div>
            <input type="button" onclick="initDateData()" value="查询"  style="color: #fff;    background-color: #3ba9ff;    width: 100px;    margin-left: 5px;    border: none;">
            <div class="btn-png">
                导出
            </div>
        </div>
    </div>
    <div class="contain">
        <div class="cell">
            <div class="baseInfo">
                <div class="title">基础信息</div>
                <div class="infos">

                </div>
            </div>
            <div class="keyWords">
                <div class="title">关键指标</div>
                <div class="content">

                </div>
            </div>
            <div id="charts1" class="charts wid22" style="height: 340px"></div>
        </div>
        <div class="cell">
            <div class="charts wid69">
                <div class="title" style="display: flex;justify-content: space-between;padding-bottom: 5px;">
                    <div style="font-size: 16px;font-weight: 600">案件走势</div>
                    <div class="tabbars">
                        <div class="tabbar active" data-table="trend2" data-type="1">最近一年</div>
                        <div class="tabbar" data-table="trend2" data-type="2">按筛选时间</div>
                    </div>
                </div>
                <div class="content">
                    <div id="charts2" style="width: 100%;height: 350px;"></div>
                </div>
            </div>
            <div id="myChartSource" class="charts wid29"></div>
        </div>
        <div class="cell">
            <div class="charts" style="padding:10px 1%;width: 100%;">
                <div class="title" style="display: flex;justify-content: space-between;padding-bottom: 5px;">
                    <div style="font-size: 16px;font-weight: 600">指标走势</div>
                    <div class="tabbars" style="width: auto;padding: 2px;">
                        <div>调查机构：
                            <select name="surveyOrgIdTrend" id="surveyOrgIdTrend" class="singleSelect form-control" onchange="initTrend22(false)">
                                <option value="-1">全部</option>
                                <c:forEach items="${franchisees}" var="item">
                                    <option value="${item.id}" >${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        &nbsp;&nbsp;<div class="tabbar active trend22" data-table="trend22" data-type="1">最近一年</div>
<%--                        <div class="tabbar trend22" data-table="trend22" data-type="2">按筛选时间</div>--%>
                    </div>
                </div>
                <div class="content">
                    <div id="charts22" style="width: 100%;height: 350px;"></div>
                </div>
            </div>
        </div>
        <div class="cell">
<%--            <div class="charts wid50" id="charts3">--%>
<%--            </div>--%>
            <div class="charts wid50" style="position: relative;">
                <div id="charts3" style="width: 100%;height: 100%">
                </div>
                <div class="charts-changes" style="display: none;">
                    <input type="hidden" id="orgAttr" value="1">
<%--                    <div class="charts-change" name="1" data-type="1">--%>
<%--                        保司--%>
<%--                    </div>--%>
<%--                    <div class="charts-change" name="2" data-type="2">--%>
<%--                        互助--%>
<%--                    </div>--%>
                </div>
            </div>
            <div class="charts wid50 charts-all">
                <div class="title">业务类型统计</div>
                <div class="content c-charts4">
                    <div id="charts4" class="charts-half"></div>
                    <div class="charts-info" id="charts4_table">
                        <div class='charts-tr'>
                            <div class='charts-td'>业务类型</div>
                            <div class='charts-td'>数量</div>
                            <div class='charts-td'>占比</div>
                        </div>
                        <div class='charts-tr'>
                            <div class='charts-td'>理赔调查</div>
                            <div class='charts-td'>200</div>
                            <div class='charts-td'>50%</div>
                        </div>
                        <div class='charts-tr'>
                            <div class='charts-td'>契约调查</div>
                            <div class='charts-td'>200</div>
                            <div class='charts-td'>50%</div>
                        </div>
                        <div class='charts-tr'>
                            <div class='charts-td'>深度案件</div>
                            <div class='charts-td'>200</div>
                            <div class='charts-td'>50%</div>
                        </div>
                    </div>
                    <div class="charts-no-data">暂无数据</div>
                </div>
            </div>
        </div>
        <div class="cell">
            <div class="wid100 institution" style="height: 700px;border: 1px solid #bbb;">
                <div class="title">调查机构排名</div>
                <div class="i-table i-table2" style="overflow: scroll;height: 665px;">
                    <div class="i-thead">
                        <div class="i-td">排名</div>
                        <div class="i-td">机构名称</div>
                        <div class="i-td" data-search-type="2" data-sort-attr="9">
                            <span class="i-td-text">新增委派</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="2" data-sort-attr="9" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="2" data-sort-attr="9" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="2" data-sort-attr="15">
                            <span class="i-td-text">环比</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="2" data-sort-attr="15" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="2" data-sort-attr="15" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="2" data-sort-attr="16">
                            <span class="i-td-text">同比</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="2" data-sort-attr="16" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="2" data-sort-attr="16" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="2" data-sort-attr="1">
                            <span class="i-td-text" style="font-weight: 600;">保司审核案件数(件)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="2" data-sort-attr="1" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="2" data-sort-attr="1" data-sort-type="2" style="border-top-color: #333"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="2" data-sort-attr="10">
                            <span class="i-td-text">保司审核方向数(个)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="2" data-sort-attr="10" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="2" data-sort-attr="10" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="2" data-sort-attr="2">
                            <span class="i-td-text">调查费(元)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="2" data-sort-attr="2" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="2" data-sort-attr="2" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="2" data-sort-attr="8">
                            <span class="i-td-text">委托方价格(元)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="2" data-sort-attr="8" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="2" data-sort-attr="8" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="2" data-sort-attr="3">
                            <span class="i-td-text">分值</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="2" data-sort-attr="3" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="2" data-sort-attr="3" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="2" data-sort-attr="4"><span class="i-td-text">阳性率(%)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="2" data-sort-attr="4" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="2" data-sort-attr="4" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="2" data-sort-attr="5"><span class="i-td-text">调查时效(天)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="2" data-sort-attr="5" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="2" data-sort-attr="5" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="2" data-sort-attr="6"><span class="i-td-text">超时效占比(%)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="2" data-sort-attr="6" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="2" data-sort-attr="6" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="2" data-sort-attr="7"><span class="i-td-text">退回率(%)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="2" data-sort-attr="7" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="2" data-sort-attr="7" data-sort-type="2"></div>
                            </div>
                        </div>
                    </div>
                    <div class="i-tbody" id="div_table_survey_trs">
                        <div class="i-tr">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="cell">
            <div class="charts" style="width: 100%;height: 700px;padding: 0;display: flex;">
                <div id="charts5" class="charts-map"></div>
                <div class="chartsm-info">
                    <div class="list">
                        <div class="li" style="background-color: #f2f2f2;padding: 11px 0;">
                            <div class="name">省份</div>
                            <div class="num-text" style="display: flex; align-items: center;padding-right: 0;">
                                <span class="span-text" style="padding-right: 4px;">案件数</span>
                                <div class="icon-sort">
                                    <div class="icon-up"></div>
                                    <div class="icon-down"></div>
                                </div>
                            </div>
                        </div>
                        <div id="charts5_table">
                            <div class="bar"></div>
                        </div>
                        <div class="li"
                             style="background-color: #f2f2f2;justify-content: flex-end;padding: 7px 0;border: none;">
                            <div class="btn-icon" data-index="1" data-page="1" id="div_page_total2">
                                <div class="icon-next2"></div>
                            </div>
                            <div class="btn-page"><span>1</span>/<span id="span_page_total">1</span> </div>
                            <div class="btn-icon" data-index="1" data-page="1" id="div_page_total">
                                <div class="icon-next"></div>
                            </div>
                            <input type="number" value="" class="page-num" min="1">
                            <div class="btn">跳转</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="cell">
<%--            <div id="charts6" class="charts wid50"></div>--%>
            <div class="charts wid50 charts-all">
                <div class="title">案件价格分布</div>
                <div class="content c-charts6">
                    <div id="charts6" class="wid100"></div>
                    <div class="charts-no-data">暂无数据</div>
                </div>
            </div>


            <div class="charts wid50 charts-all">
                <div class="title">案件跨区域统计</div>
                <div class="content c-charts7">
                    <div id="charts7" class="charts-half"></div>
                    <div class="charts-info" id="charts7_table"></div>
                    <div class="charts-no-data">暂无数据</div>
                </div>
            </div>
        </div>
        <div class="cell">
            <div class="wid100" style="height: 700px;border: 1px solid #bbb;">
                    <iframe id="iframe_acc" style="width: 100%;height: 100%;border: none"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="dalogs">
    <div class="dalogs-bg"></div>
    <div class="dalogs-contain">
        <div class="dalogs-downfile">
            <div class="downfile" id="downfile">
                <div class="header">
                    <div class="header-content">
                        <div class="header-logo"></div>
                        <div class="header-title"></div>
                        <div class="header-subtitle">XXXX年第X季度</div>
                    </div>
                    <div class="header-high"></div>
                </div>
                <div class="title-co">
                    <div class="title-co-name">XX公司</div>
                </div>
                <div class="info1">看看这个月在乐凡的数据如何</div>
                <div class="contain">
                    <div class="bar-type">
                        <div class="bar-type-l"></div>
                        <div class="bar-type-text">基础指标</div>
                        <div class="bar-type-r"></div>
                    </div>
                    <div class="keyWords keyWords2">
                        <div class="keyWord">
                            <div class="num">
                                <div class="num-text xzwt">0</div>
                                <div class="num-sub">件</div>
                            </div>
                            <div class="name">新增委托</div>
                        </div>
                        <div class="keyWord">
                            <div class="num">
                                <div class="num-text xzbs">0</div>
                                <div class="num-sub">件</div>
                            </div>
                            <div class="name">保司终审通过</div>
                        </div>
                        <div class="keyWord">
                            <div class="num">
                                <div class="num-text dcf">0</div>
                                <div class="num-sub">万元</div>
                            </div>
                            <div class="name">调查费</div>
                        </div>
                    </div>
                    <div class="prices">
                        <div class="price">
                            <div class="p-num ajjj">0.00元</div>
                            <div class="p-name">
                                <div class="p-name-con">案件均价</div>
                                <div class="p-name-icon"></div>
                            </div>
                        </div>
                        <div class="price">
                            <div class="p-num fxjj">00.00元</div>
                            <div class="p-name">
                                <div class="p-name-con">方向均价</div>
                                <div class="p-name-icon"></div>
                            </div>
                        </div>
                    </div>
                    <div class="cell">
                        <div id="charts31" class="charts" style="height: 340px;display: none"></div>
                    </div>
                    <div class="qualities">
                        <div class="border-high"></div>
                        <div class="border-high-b"></div>
                        <div class="qualities-title">调查质量数据</div>
                        <div class="qualities-content">
                            <div class="quality">
                                <div class="q-name">跨区域程度</div>
                                <div class="q-num kqycd">省</div>
                            </div>
                            <div class="quality">
                                <div class="q-name">调查时效</div>
                                <div class="q-num dcsx">天</div>
                            </div>
                            <div class="quality quality-csx">
                                <div class="q-name">超时效占比</div>
                                <div class="q-num csxzb">%</div>
                            </div>
                            <div class="quality quality-sun">
                                <div class="q-name">阳性率</div>
                                <div class="q-num yxl">%</div>
                            </div>
                        </div>
                    </div>
                    <div class="split-line split-line-32"></div>
                    <div class="bar-type bar-type-32">
                        <div class="bar-type-l"></div>
                        <div class="bar-type-text">案件走势</div>
                        <div class="bar-type-r"></div>
                    </div>
                    <div class="cell cell-32">
                        <div id="charts32" class="charts charts2"></div>
                    </div>
                    <div class="split-line split-line-33"></div>
                    <div class="bar-type bar-type-33">
                        <div class="bar-type-l"></div>
                        <div class="bar-type-text">任务统计</div>
                        <div class="bar-type-r"></div>
                    </div>
                    <div class="cell cell-33" style="margin: 0">
                        <div id="charts33" class="charts-pie"></div>
                    </div>
                    <div class="split-line split-line-34"></div>
                    <div class="bar-type bar-type-34">
                        <div class="bar-type-l"></div>
                        <div class="bar-type-text">业务类型统计</div>
                        <div class="bar-type-r"></div>
                    </div>
                    <div class="cell cell-34" style="margin: 0">
                        <div id="charts34" class="charts-pie"></div>
                    </div>
                    <div class="split-line split-line-35"></div>
                    <div class="bar-type2 bar-type2-35">
                        <div class="bar-type2-bg"></div>
                        <div class="bar-type2-text">案件地域分布</div>
                    </div>
                    <div class="cell cell-35">
                        <div id="charts35" class="charts-map"></div>
                    </div>
                    <div class="cell cell-36-01">
                        <div class="list-out">
                            <div class="border-high"></div>
                            <div class="border-high-b"></div>
                            <div class="list">
                                <div class="li" style="padding: 16px 0;border-bottom-width: 1.5px;">
                                    <div class="name" style="font-size: 20px;">省份</div>
                                    <div class="num-text" style="display: flex; align-items: center;padding-right: 0;">
                                        <span class="span-text" style="padding-right: 4px;font-size: 20px;">案件数</span>
                                        <div class="icon-sort">
                                            <div class="icon-up"></div>
                                            <div class="icon-down"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="list-content"></div>

                            </div>
                        </div>
                    </div>
                    <div class="bar-type bar-type-36">
                        <div class="bar-type-l"></div>
                        <div class="bar-type-text">案件价格分布</div>
                        <div class="bar-type-r"></div>
                    </div>
                    <div class="cell cell-36-02"  style="padding: 0;">
                        <div class="border-high"></div>
                        <div class="border-high-b"></div>
                        <div id="charts36" class="charts"></div>
                    </div>
                    <div class="bar-type bar-type-37">
                        <div class="bar-type-l"></div>
                        <div class="bar-type-text">案件跨区域统计</div>
                        <div class="bar-type-r"></div>
                    </div>
                    <div class="cell cell-37" style="margin: 0">
                        <div id="charts37" class="charts-pie"></div>
                    </div>
                </div>
                <div class="footer">
                    <div class="footer-content">
                        <div class="footer-logo">
                            <div class="footer-logo-con"></div>
                        </div>
                        <div class="footer-text">
                            <div class="footer-text-zi">
                                共创和谐美好生活
                            </div>
                        </div>
                    </div>
                    <div class="qrcode"></div>
                </div>
            </div>
            <div class="downfile-loading">
                <div id="loading3">
                    <div class="demo3"></div>
                    <div class="demo3"></div>
                    <div class="demo3"></div>
                    <div class="demo3"></div>
                    <div class="demo3"></div>
                    <div class="demo3"></div>
                    <div class="demo3"></div>
                    <div class="demo3"></div>
                </div>
                <div class="loading3-text">数据加载中</div>
            </div>
        </div>
        <div class="dalogs-content">
            <div class="d-title">导出</div>
            <div class="d-label">选择报表类型</div>
            <div class="d-value d-value1 chartsType">
                <div class="d-radio mr20 active" data-value="month">月度报表</div>
                <div class="d-radio" data-value="season">季度报表</div>
            </div>
            <div class="d-label">选择时间段</div>

            <div class="d-value" >
                <select name="quarter" id="quarter">

                </select>
            </div>
            <div class="d-label">是否显示阳性率</div>
            <div class="d-value d-value2">
                <div class="d-radio mr20 active" data-value="showsun">显示
                </div>
                <div class="d-radio" data-value="hidesun">隐藏</div>
            </div>
            <div class="d-label">超时效占比</div>
            <div class="d-value">
               <div class="csxzb-input">
                   <input type="number" id="csxzb" value="">
                   <span>%</span>
               </div>
            </div>
            <div class="d-btns">
                <div class="d-btn cancel">取消</div>
                <div class="d-btn active download">确认导出</div>
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
        <div class="loading-text">报告生成中···</div>
    </div>
</div>

<div id="canvasContainer"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/echars/echarts.min.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?ak=geF7zRxYTlIuHlqVTxtbKM70GqFSPTAj"></script>
<script type="text/javascript" src="${ctx}/js/echars/bmap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
<script type="text/javascript" src="${ctx}/js/china.js"></script>
<script type="text/javascript" src="${ctx}/js/html2canvas.js"></script>
<script src="${ctx}/js/layui/layui.js"></script>

<script>

    layui.use(['laydate','jquery'], function () {
        var table = layui.table,
            util = layui.util,
            laydate = layui.laydate,
            $ = layui.jquery

        seTime = laydate.render({
            elem: '#seTime',
            range: '~',
            done: function (value, date) {
                var seTime = value.split('~')
                $("#startTime").val(seTime[0].trim());
                $("#endTime").val(seTime[1].trim());

            }
        });

        tbTime = laydate.render({
            elem: '#tbTime',
            range: '~',
            done: function (value, date) {
                var tbTime = value.split('~')
                $("#tbStartTime").val(tbTime[0].trim());
                $("#tbEndTime").val(tbTime[1].trim());
            }
        });
    })

    $('body').on('click','.caseList',function () {
        var _this = $(this)
        var params = {
            searchType: $("#searchType").val(),
            startTime: $("#startTime").val(),
            endTime: $("#endTime").val(),
            entrustOrgId:$("#entrustOrgId").val(),
            dataType:"entrust",
            dataItem: 'dataItem',
            itemType: _this.attr('data-type'),
            itemValue: _this.attr('data-index')
        };
        if (_this.attr('data-type') == 'entrustOrg' || _this.attr('data-type') == 'surveyOrg'){
            Object.assign(params,{itemId: _this.attr('data-id')})
        }
        addCaseItem(params)
    })
    function addCaseItem(params){
        var params = JSON.stringify(params)
        params = encodeURIComponent(params)
        var url = "${ctx}/survey/report/index?menuCode=caseItem&entrustOrgId="+$("#entrustOrgId").val()+"&params="+params
        parent.addTab("案件列表",url,true);
    }

    var bsZoom = '',initZoom = '';


    initData();


    function initIframe(){
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var entrustOrgId = $("#entrustOrgId").val();
        // var entrustOrgName = $("#entrustOrgId").text();
        var url = "${ctx}/survey/report/acc?menuCode=entrust&startTime=" + startTime + "&endTime=" + endTime + "&entrustOrgId=" + entrustOrgId;
        $("#iframe_acc").attr("src",url);


        searchOrg(2)
    }

    function searchOrg(type){
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"entrust","dataTable" : type == 1 ? "searchEntrust" : "searchSurvey"};
        param["searchType"] = searchType;
        param["checkType"] = "2";
        param["startTime"] = $("#startTime").val();
        param["endTime"] = $("#endTime").val();
        param["tbStartTime"] = $("#tbStartTime").val();
        param["tbEndTime"] = $("#tbEndTime").val();
        param["entrustOrgId"] = $("#entrustOrgId").val();
        // param["surveyOrgId"] = $("#surveyOrgId").val();
        param["sortAttr"] = $("#sortAttr").val();
        param["sortType"] = $("#sortType").val();
        console.log(param);
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                if(type == 2){
                    //调查机构排名
                    var surveyOrgs = e.data.results.surveyOrgs;
                    html = "";
                    if(surveyOrgs){
                        for(var i = 0 ; i < surveyOrgs.length ; i ++ ){
                            var hbRate = "",hbHtml = "";
                            hbRate = (surveyOrgs[i].hbCaseNumRate * 100).toFixed(2);
                            if (hbRate > 0){
                                hbHtml = '<div class="rate color1">+'+hbRate+'%</div>'
                            }else if (hbRate < 0){
                                hbHtml = '<div class="rate color2">'+hbRate+'%</div>'
                            }else {
                                hbHtml = '<div class="rate">0%</div>'
                            }

                            var tbRate = "",tbHtml = "";
                            tbRate = (surveyOrgs[i].tbCaseNumRate * 100).toFixed(2);
                            if (tbRate > 0){
                                tbHtml = '<div class="rate color1">+'+tbRate+'%</div>'
                            }else if (tbRate < 0){
                                tbHtml = '<div class="rate color2">'+tbRate+'%</div>'
                            }else {
                                tbHtml = '<div class="rate">0%</div>'
                            }

                            var _index = i +1
                            html += '<div class="i-tr">' +
                                '<div class="i-td">'+_index+'</div>'+
                                '<div class="i-td" style="cursor: pointer;color:#3ba9ff" onclick="addSurveyPage('+surveyOrgs[i].surveyOrgId+',2)" title="'+surveyOrgs[i].surveyOrgName+'">'+ surveyOrgs[i].surveyOrgName +'</div>' +
                                '<div class="i-td caseList" data-index="8" data-id="'+surveyOrgs[i].surveyOrgId+'" data-type="surveyOrg">'+ surveyOrgs[i].newSend +'</div>' +
                                '<div class="i-td">'+hbHtml+'</div>' +
                                '<div class="i-td">'+tbHtml+'</div>' +
                                '<div class="i-td caseList" data-index="1" data-id="'+surveyOrgs[i].surveyOrgId+'" data-type="surveyOrg">'+ surveyOrgs[i].insuranceCheckCaseNum +'</div>' +
                                '<div class="i-td">'+ surveyOrgs[i].directionNum +'</div>' +
                                '<div class="i-td">'+surveyOrgs[i].investigationMoney.toFixed(2)+'</div>' +
                                '<div class="i-td">'+surveyOrgs[i].entrustOrgMoney.toFixed(2)+'</div>' +
                                '<div class="i-td">'+ surveyOrgs[i].score.toFixed(2) +'</div>' +
                                '<div class="i-td caseList" data-index="2" data-id="'+surveyOrgs[i].surveyOrgId+'" data-type="surveyOrg">'+(surveyOrgs[i].positiveRate * 100).toFixed(2)+'</div>' +
                                '<div class="i-td">'+surveyOrgs[i].efficiency.toFixed(1) +'</div>' +
                                '<div class="i-td caseList" data-index="3" data-id="'+surveyOrgs[i].surveyOrgId+'" data-type="surveyOrg">'+(surveyOrgs[i].lossEfficiencyRate * 100).toFixed(2)+'</div>' +
                                '<div class="i-td">'+(surveyOrgs[i].returnRate * 100).toFixed(2)+'</div>' +
                                '</div>'
                        }
                    }
                    $("#div_table_survey_trs").html(html);
                }
            }else{
                alert("数据异常");
            }
        });
    }


    function initDateData(){
        $('.header-choose-bar').removeClass('active');
        $("#searchType").val("date");
        initData();
    }

    function initData(){
        //选中class
        var searchType = $("#searchType").val();
        $('.header-choose-bar').each(function(i,v){
            if(searchType == $(v).attr("data-value")){
                $(v).addClass("active");
            }else{
                $(v).removeClass("active");
            }
        });

        initBas();
    }
    function initBas(){
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"entrust","dataTable":"entrustBas"};
        param["entrustOrgId"] = $("#entrustOrgId").val();
        param["searchType"] = searchType;
        param["startTime"] = $("#startTime").val();
        param["endTime"] = $("#endTime").val();
        param["tbStartTime"] = $("#tbStartTime").val();
        param["tbEndTime"] = $("#tbEndTime").val();
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var bas = e.data.results.bas;

                //显示日期
                var startTime = e.data.results.startTime;
                var endTime = e.data.results.endTime;
                var tbStartTime = e.data.results.tbStartTime;
                var tbEndTime = e.data.results.tbEndTime;
                $("#startTime").val(startTime);
                $("#endTime").val(endTime);
                $("#tbStartTime").val(tbStartTime);
                $("#tbEndTime").val(tbEndTime);

                $("#seTime").val(startTime + ' ~ ' + endTime);
                $("#tbTime").val(tbStartTime + ' ~ ' + tbEndTime);

                if(bas){
                    var busAttr = bas.surveyConsignor.orgAttr;
                    if (busAttr == 1){//保司
                        $("#orgAttr").val(1);
                        $(".charts-change[name=1]").addClass("charts-change_active");
                        $(".charts-change[name=2]").removeClass("charts-change_active");
                    } else{//互助
                        $("#orgAttr").val(2);
                        $(".charts-change[name=2]").addClass("charts-change_active");
                        $(".charts-change[name=1]").removeClass("charts-change_active");
                    }
                    var type = bas.surveyConsignor.type,typeName = null;
                    if(type == 1){
                        typeName = '合作伙伴';
                    }else if(type == 2){
                        typeName = '散户';
                    }
                    var areaType = bas.surveyConsignor.areaType,areaName = null;
                    if(type == 1){
                        areaName = '省';
                    }else if(type == 2){
                        areaName = '市';
                    }else if(type == 3){
                        areaName = '区';
                    }
                    var html = "";
                    html += '<div class="info" title="保司名称 '+bas.surveyConsignor.name+'">保司名称<span class="info-span"> '+bas.surveyConsignor.name+'</span></div>';
                    html += '<div class="info" title="委托方类别 '+typeName+'">委托方类别<span class="info-span"> '+typeName+'</span></div>';
                    html += '<div class="info" title="区域类别 '+areaName+'">区域类别<span class="info-span"> '+areaName+'</span></div>';
                    html += '<div class="info" title="开票未到账 '+bas.basBillNotAcc+'元">开票未到账<span class="info-span"> '+bas.basBillNotAcc+'元</span></div>';
                    html += '<div class="info wid98" title="委托方区域 '+bas.surveyConsignor.areaName+'">委托方区域<span class="info-span"> '+bas.surveyConsignor.areaName+'</span></div>';
                    html += '<div class="info caseList" data-index="2" data-type="bas" title="委托待受理 '+bas.basEntrustNotApv+'件">委托待受理<span class="info-span"> '+bas.basEntrustNotApv+'件</span></div>';
                    html += '<div class="info caseList" data-index="3" data-type="bas" title="平台待分派 '+bas.basLefanNotAss+'件">平台待分派<span class="info-span"> '+bas.basLefanNotAss+'件</span></div>';
                    html += '<div class="info caseList" data-index="4" data-type="bas" title="机构调查中 '+bas.basOrgSurveying+'件">机构调查中<span class="info-span"> '+bas.basOrgSurveying+'件</span></div>';
                    html += '<div class="info caseList" data-index="5" data-type="bas" title="平台待终审 '+bas.basLefanApv+'件">平台待终审<span class="info-span"> '+bas.basLefanApv+'件</span></div>';
                    html += '<div class="info caseList" data-index="6" data-type="bas" title="保司待审核 '+bas.basEntrustApv+'件">保司待审核<span class="info-span"> '+bas.basEntrustApv+'件</span></div>';
                    html += '<div class="info caseList" data-index="7" data-type="bas" title="财务待结案 '+bas.basNotClose+'件">财务待结案<span class="info-span"> '+bas.basNotClose+'件</span></div>';
                    $(".baseInfo .infos").html(html);

                    var surveyMoney = bas.surveyConsignorReport.surveyMoney;
                    var newCheckNum = bas.surveyConsignorReport.newCheckNum;
                    var price = 0;
                    if(newCheckNum != 0){
                        price = (surveyMoney / newCheckNum).toFixed(2);
                    }

                    html = "";

                    var rate = (bas.surveyConsignorReport.newSurveyCaseNumRate * 100).toFixed(2)
                    var _htmlRate = '<div class="rate">环比'+rate+'%</div>'
                    if (rate > 0){
                        _htmlRate = '<div class="rate color1">环比+' +rate+'%</div>'
                    } else if (rate < 0){
                        _htmlRate = '<div class="rate color2">环比' +rate+'%</div>'
                    }

                    var tbRate = (bas.surveyConsignorReport.tbNewSurveyCaseNumRate * 100).toFixed(2)
                    var _htmlTbRate = '<div class="rate">同比'+tbRate+'%</div>'
                    if (tbRate > 0){
                        _htmlTbRate = '<div class="rate color1">同比+' +tbRate+'%</div>'
                    } else if (tbRate < 0){
                        _htmlTbRate = '<div class="rate color2">同比' +tbRate+'%</div>'
                    }
                    html += '<div class="keybar caseList" data-index="1" data-type="tar"><div><div class="name">新增委托</div><div class="num">'+bas.surveyConsignorReport.newSurveyCaseNum+'件</div>'+_htmlRate+_htmlTbRate + '</div></div>';
                    html += '<div class="keybar caseList" data-index="9" data-type="tar"><div class="name">机构提交</div><div class="num">'+bas.surveyConsignorReport.orgCommitNum+'件</div></div>';
                    html += '<div class="keybar caseList" data-index="2" data-type="tar"><div class="name">保司终审通过</div><div class="num">'+bas.surveyConsignorReport.newCheckNum+'件</div></div>';
                    html += '<div class="keybar caseList" data-index="3" data-type="tar"><div class="name">调查费</div><div class="num">'+bas.surveyConsignorReport.surveyMoney+'元</div></div>';
                    html += '<div class="keybar caseList" data-index="4" data-type="tar"><div class="name">开票</div><div class="num">'+bas.surveyConsignorReport.surveyBilling.toFixed(2)+'元</div></div>';
                    html += '<div class="keybar caseList" data-index="5" data-type="tar"><div class="name">到账</div><div class="num">'+bas.surveyConsignorReport.surveyAccount.toFixed(2)+'元</div></div>';
                    html += '<div class="keybar two"><div class="name">案件均价</div><div class="num">'+price+'元</div></div>';
                    html += '<div class="keybar two"><div class="name">方向均价</div><div class="num">'+bas.surveyConsignorReport.directionAvgMoney.toFixed(0)+'元</div></div>';
                    html += '<div class="keybar two caseList" data-index="6" data-type="tar"><div class="name">阳性率</div><div class="num">'+(bas.surveyConsignorReport.positiveRate * 100).toFixed(2)+'%</div></div>';
                    html += '<div class="keybar two"><div class="name">跨区域程度</div><div class="num">'+bas.surveyConsignorReport.regional.toFixed(2)+'省</div></div>';
                    html += '<div class="keybar two"><div class="name">调查时效</div><div class="num">'+bas.surveyConsignorReport.efficiency.toFixed(2)+'天</div></div>';
                    html += '<div class="keybar two caseList" data-index="7" data-type="tar"><div class="name">超时效占比</div><div class="num">'+(bas.surveyConsignorReport.lossEfficiencyRate * 100).toFixed(2)+'%</div></div>';
                    $(".keyWords .content").html(html);
                }
                //
                initTask(true);
            }else{
                alert("加载数据异常");
            }
        });
    }

    $('.charts-change').on('click', function (e) {
        var _this = $(this)
        if (!_this.hasClass('charts-change_active')) {
            _this.addClass('charts-change_active')
            _this.siblings().removeClass('charts-change_active')
        }
        $("#orgAttr").val(_this.attr("data-type"));
        initTask(false)
    })

    function initEntrustSource(){
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"entrust","dataTable":"source"};
        param["entrustOrgId"] = $("#entrustOrgId").val();
        param["searchType"] = searchType;
        param["startTime"] = $("#startTime").val();
        param["endTime"] = $("#endTime").val();

        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var list = e.data.results.list;
                var total = e.data.results.total;
                if(list){
                    var newData = [];
                    for(var i = 0 ; i < list.length; i ++ ){
                        newData.push({
                            name: list[i].sourceName,
                            value: list[i].num
                        });
                    }
                    optionSource.series[0].data = newData;
                    myChartSource.hideLoading();
                    if(list.length == 0){
                        myChart2.showLoading({
                            text: '暂无数据',
                            color: '#ffffff',
                            textColor: '#8a8e91',
                            maskColor: 'rgba(255, 255, 255, 0.8)',
                        })
                    }
                    myChartSource.setOption(optionSource);

                    initArea();
                }
            }else{
                alert("加载委托来源数据异常");
            }
        });
    }
    function initTask(loadNext){
        console.log("orgAttr:",$("#orgAttr").val())
        var searchType = $("#searchType").val();
        //加载任务
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"entrust","dataTable":"task"};
        param["entrustOrgId"] = $("#entrustOrgId").val();
        param["searchType"] = searchType;
        param["startTime"] = $("#startTime").val();
        param["endTime"] = $("#endTime").val();
        param["orgAttr"] = $("#orgAttr").val();
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var list = e.data.results.list;
                if(list){
                    var legend = [];
                    var newData = [];
                    var newDataTasks = [];
                    // if(list.length == 0){
                    //     $("#charts3").hide();
                    //     $('.c-charts3 .charts-no-data').show();
                    // }else{
                    //     $("#charts3").show();
                    //     $('.c-charts3 .charts-no-data').hide();
                    // }
                    for(var i = 0 ; i < list.length; i ++ ){
                        legend.push(list[i].parentName);
                        newData.push({
                            name: list[i].parentName,
                            value: list[i].entrustCaseNum
                        });
                        var tasks = list[i].surveyTaskReports;
                        for(var j = 0 ; j < tasks.length ; j ++ ){
                            // legend.push(tasks[j].taskName);
                            newDataTasks.push({
                                name: tasks[j].taskName,
                                value: tasks[j].entrustCaseNum
                            })
                        }
                    }

                    option3.legend.data = legend;
                    option3.series[0].data = newData;
                    option3.series[1].data = newDataTasks;
                    myChart3.hideLoading();
                    if(list.length == 0){
                        myChart3.showLoading({
                            text: '暂无数据',
                            color: '#ffffff',
                            textColor: '#8a8e91',
                            maskColor: 'rgba(255, 255, 255, 0.8)',
                        })
                    }
                    myChart3.setOption(option3);
                }
                //加载跨区域数据
                if(loadNext){
                    initCross();
                }
            }else{
                alert("加载领域数据异常");
            }
        });
    }
    function initCross(){
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"entrust","dataTable":"cross"};
        param["entrustOrgId"] = $("#entrustOrgId").val();
        param["searchType"] = searchType;
        param["startTime"] = $("#startTime").val();
        param["endTime"] = $("#endTime").val();
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var list = e.data.results.list;
                var total = e.data.results.total;
                // if(list.length == 0){
                //     $("#charts7").hide();
                //     $("#charts7_table").hide();
                //     $('.c-charts7 .charts-no-data').show();
                // }else{
                //     $("#charts7").show();
                //     $("#charts7_table").show();
                //     $('.c-charts7 .charts-no-data').hide();
                // }
                if(list){
                    var html = "<div class='charts-tr'><div class='charts-td'>区域数量</div><div class='charts-td'>案件数</div><div class='charts-td'>占比</div></div>";
                    var newData = [];
                    for(var i = 0 ; i < list.length; i ++ ){
                        newData.push({
                            name: list[i].areaLevelName,
                            value: list[i].entrustCaseNum
                        });
                        html += "<div class='charts-tr caseList' data-index='"+list[i].areaLevel+"' data-type='cross'><div class='charts-td'>"+list[i].areaLevelName+"</div>" +
                            "<div class='charts-td'>"+list[i].entrustCaseNum+"</div><div class='charts-td'>" + (list[i].entrustCaseNum / total * 100).toFixed(2) + "%</div></div>";
                    }
                    option7.series[0].data = newData;
                    myChart7.hideLoading();
                    if(list.length == 0){
                        myChart7.showLoading({
                            text: '暂无数据',
                            color: '#ffffff',
                            textColor: '#8a8e91',
                            maskColor: 'rgba(255, 255, 255, 0.8)',
                        })
                    }
                    myChart7.setOption(option7);
                    $("#charts7_table").html(html);

                    initEntrustSource();
                }
            }else{
                alert("加载跨区域数据异常");
            }
        });
    }
    function initArea(){
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"entrust","dataTable":"area"};
        param["entrustOrgId"] = $("#entrustOrgId").val();
        param["searchType"] = searchType;
        param["startTime"] = $("#startTime").val();
        param["endTime"] = $("#endTime").val();
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var list = e.data.results.list;
                var total = e.data.results.total;
                var maxNum = e.data.results.maxNum == 0 ? 1 : e.data.results.maxNum;
                if(list){
                    var html = "";
                    var newData = [];

                    for(var i = 0 ; i < list.length; i ++ ){
                        newData.push({
                            // name: list[i].areaName2.substr(0,list[i].areaName2.length - 1),
                            name: list[i].areaName, //省名称
                            value: list[i].num
                        });
                        // html += "<div class='li'><div class='name'>"+list[i].areaName+"</div><div class='num-text'>"+list[i].num+"</div><div class='num-line'><div class='line-icon' style='width: "+(list[i].num/total).toFixed(2) + "%'></div></div></div>";
                        html += "<div class='li caseList' data-index='"+list[i].areaId+"' data-type='area'><div class='name'>"+list[i].areaName+"</div><div class='num-text'>"+list[i].num+"</div><div class='num-line'><div class='line-icon' style='width: "+(list[i].num/maxNum * 100 > 1 ? list[i].num/maxNum * 100 : 1).toFixed(2) + "%'></div></div></div>";
                    }
                    var pageTotal = Math.ceil(list.length / 13);
                    $("#span_page_total").text(pageTotal);
                    $("#div_page_total").attr("data-page",pageTotal);
                    $("#div_page_total2").attr("data-page",pageTotal);

                    option5.series[0].symbolSize = function(val){
                        return val[2] / (maxNum / 10);
                    };
                    option5.series[0].data = convertData(newData);

                    option5.series[1].symbolSize = function(val){
                        return val[2] / (maxNum / 20);
                    };
                    option5.series[1].data = convertData(newData.sort(function (a, b) {
                        return b.value - a.value;
                    }).slice(0, 6));
                    var arrLoc = convertData(newData)
                    var x_s = []
                    var y_s = []
                    arrLoc.map(function (m) {   x_s.push(m.value[0])
                        y_s.push(m.value[1]) })
                    x_s.sort(sortNumber)
                    y_s.sort(sortNumber)
                    var xMin = Number(x_s[0])
                    var xMax = Number(x_s.reverse()[0])
                    var yMin = Number(y_s[0])
                    var yMax = Number(y_s.reverse()[0])
                    option5.bmap.center = newData.length > 0 ?[(xMax + xMin) / 2, (yMax + yMin) / 2] :[104.114129, 37.550339]
                    option5.bmap.zoom = getZoom(xMax, xMin, yMax, yMin);
                    initZoom = getZoom(xMax, xMin, yMax, yMin);
                    option5.series[0].data = newData.length > 6 ? convertData(newData).slice(6) : [];
                    myChart5.hideLoading();
                    myChart5.setOption(option5);

                    //
                    //
                    // option5.series[0].data = convertData(newData);
                    // option5.series[1].data = convertData(newData.sort(function (a, b) {
                    //     return b.value - a.value;
                    // }).slice(0, 6));
                    // myChart5.hideLoading();
                    // myChart5.setOption(option5);
                    $("#charts5_table .bar").html(html);
                    initService();
                }
            }else{
                alert("加载数据异常");
            }
        });
    }

    function getZoom (maxLng, minLng, maxLat, minLat) {
        var map = new BMap.Map("container");
        var zoom = ["50","100","200","500","1000","2000","5000","10000","20000","25000","50000","100000","200000","500000","1500000","3000000"]//级别18到3。
        var pointA = new BMap.Point(maxLng,maxLat);
        var pointB = new BMap.Point(minLng,minLat);
        var distance = map.getDistance(pointA,pointB).toFixed(1);
        for (var i = 0,zoomLen = zoom.length; i < zoomLen; i++) {
            if(zoom[i] - distance > 0){
                return 18-i+3 > 10 ? 10 : 18-i+3;
            }
        };
        return 5;
    }


    function sortNumber(a, b) {
        return a - b
    }

    function initService(){
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"entrust","dataTable":"service"};
        param["entrustOrgId"] = $("#entrustOrgId").val();
        param["searchType"] = searchType;
        param["startTime"] = $("#startTime").val();
        param["endTime"] = $("#endTime").val();
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var list = e.data.results.list;
                var total = e.data.results.total;
                // if(list.length == 0){
                //     $("#charts4").hide();
                //     $("#charts4_table").hide();
                //     $('.c-charts4 .charts-no-data').show();
                // }else{
                //     $("#charts4").show();
                //     $("#charts4_table").show();
                //     $('.c-charts4 .charts-no-data').hide();
                // }
                if(list){
                    var html = "<div class='charts-tr'><div class='charts-td'>业务类型</div><div class='charts-td'>数量</div><div class='charts-td'>占比</div><div class='charts-td'>阳性案件</div><div class='charts-td'>阳性率</div><div class='charts-td'>调查时效</div></div>";
                    var newData = [];
                    for(var i = 0 ; i < list.length; i ++ ){
                        if(list[i].entrustCaseNum == null || list[i].entrustCaseNum == 0){
                            continue;
                        }
                        newData.push({
                            name: list[i].serviceTypeName,
                            value: list[i].entrustCaseNum
                        });
                        html += "<div class='charts-tr caseList' data-index='"+list[i].serviceTypeId+"' data-type='service'><div class='charts-td'>"+list[i].serviceTypeName+"</div>" +
                            "<div class='charts-td'>"+list[i].entrustCaseNum+"</div><div class='charts-td'>" + (list[i].entrustCaseNum / total * 100).toFixed(2) + "%</div>" +
                            "<div class='charts-td'>"+ list[i].positiveNum +"</div><div class='charts-td'>" + (list[i].positiveNum / list[i].entrustCaseNum * 100).toFixed(2) + "%</div><div class='charts-td'>"+list[i].agingDay.toFixed(1)+"</div></div>";
                    }
                    option4.series[0].data = newData;
                    myChart4.hideLoading();
                    if(newData.length == 0){
                        myChart4.showLoading({
                            text: '暂无数据',
                            color: '#ffffff',
                            textColor: '#8a8e91',
                            maskColor: 'rgba(255, 255, 255, 0.8)',
                        })
                    }
                    myChart4.setOption(option4);
                    $("#charts4_table").html(html);

                }

                initTrend()
            }else{
                alert("加载数据异常");
            }
        });
    }


    function initTrend(){
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"entrust","dataTable":"trend"};
        var trendType = $('.tabbars .tabbar.active').attr("data-type");
        if (trendType == 1){
            searchType = "soon";
        }
        param["entrustOrgId"] = $("#entrustOrgId").val();
        param["searchType"] = searchType;
        param["startTime"] = $("#startTime").val();
        param["endTime"] = $("#endTime").val();
        param["trendType"] = 1;
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var list = e.data.results.list;
                if(list){
                    var newData = [];
                    var newData1 = [];
                    var newData2 = [];
                    for(var i = 0 ; i < list.length; i ++ ){
                        newData.push(list[i].reportDateStr);
                        newData1.push(list[i].newSurveyCaseNum);
                        newData2.push(list[i].newCheckNum);
                    }
                    option2.xAxis.data = newData;
                    option2.series[0].data = newData1;
                    option2.series[1].data = newData2;
                    myChart2.hideLoading();
                    myChart2.setOption(option2);
                }

                initTrend22(true);
            }else{
                alert("加载案件走势数据异常");
            }
        });
    }

    function initTrend22(auto){
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"entrust","dataTable":"trend2"};
        var trendType = $('.tabbars .tabbar.active.trend22.active').attr("data-type");
        if (trendType == 1){
            searchType = "soon";
        }
        param["entrustOrgId"] = $("#entrustOrgId").val();
        param["searchType"] = searchType;
        param["startTime"] = $("#startTime").val();
        param["endTime"] = $("#endTime").val();
        param["trendType"] = trendType;
        param["surveyOrgIdTrend"] = $("#surveyOrgIdTrend").val() == -1 ? "" : $("#surveyOrgIdTrend").val();
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var list = e.data.results.list;
                if(list){
                    var newData = [];
                    var newData1 = [];
                    var newData2 = [];
                    for(var i = 0 ; i < list.length; i ++ ){
                        newData.push(list[i].reportDateStr);
                        newData1.push(list[i].positiveRate.toFixed(2));
                        newData2.push(list[i].lossEfficiencyRate.toFixed(2));
                    }
                    option22.xAxis.data = newData;
                    option22.series[0].data = newData1;
                    option22.series[1].data = newData2;
                    myChart22.hideLoading();
                    myChart22.setOption(option22);
                }
                if (auto){
                    initKeyTar();
                }
            }else{
                alert("加载案件走势数据异常");
            }
        });
    }


    $('.tabbars .tabbar').click(function () {
        var _this = $(this);
        var table = _this.attr("data-table");
        if (table == 'trend2'){
            _this.addClass('active')
            _this.siblings().removeClass('active')
            console.log(_this.attr('data-type'))
            var param = {}
            if (_this.attr('data-type') == 1){
                param = {
                    "dataType":"entrust",
                    "searchType" : "soon",
                    "dataTable":"trend",
                    "entrustOrgId": $("#entrustOrgId").val(),
                    "trendType" : 1
                }
            }else if (_this.attr('data-type') == 2){
                param = {
                    "dataType":"entrust",
                    "dataTable":"trend",
                    "entrustOrgId": $("#entrustOrgId").val(),
                    "searchType": $("#searchType").val(),
                    "startTime": $("#startTime").val(),
                    "endTime": $("#endTime").val(),
                    "trendType" : 2
                }
            }
            initTrend2(param)
        }else if (table == 'trend22'){
            _this.addClass('active')
            _this.siblings().removeClass('active')
            console.log(_this.attr('data-type'))
            var param = {}
            if (_this.attr('data-type') == 1){
                param = {
                    "dataType":"entrust",
                    "searchType" : "soon",
                    "dataTable":"trend2",
                    "entrustOrgId": $("#entrustOrgId").val(),
                    "trendType" : 1,
                    "surveyOrgIdTrend" : $("#surveyOrgIdTrend").val() == -1 ? "" : $("#surveyOrgIdTrend").val()
                }
            }else if (_this.attr('data-type') == 2){
                param = {
                    "dataType":"entrust",
                    "dataTable":"trend2",
                    "entrustOrgId": $("#entrustOrgId").val(),
                    "searchType": $("#searchType").val(),
                    "startTime": $("#startTime").val(),
                    "endTime": $("#endTime").val(),
                    "trendType" : 2,
                    "surveyOrgIdTrend" : $("#surveyOrgIdTrend").val() == -1 ? "" : $("#surveyOrgIdTrend").val()
                }
            }
            initTrend23(param)
        }
    })

    function initTrend2(param){
        var url = "${ctx}/survey/report/getData";
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var list = e.data.results.list;
                if(list){
                    var newData = [];
                    var newData1 = [];
                    var newData2 = [];
                    for(var i = 0 ; i < list.length; i ++ ){
                        newData.push(list[i].reportDateStr);
                        newData1.push(list[i].newSurveyCaseNum);
                        newData2.push(list[i].newCheckNum);
                    }
                    option2.xAxis.data = newData;
                    option2.series[0].data = newData1;
                    option2.series[1].data = newData2;
                    myChart2.hideLoading();
                    myChart2.setOption(option2);
                }
            }else{
                alert("加载案件走势数据异常");
            }
        });
    }
    function initTrend23(param){
        var url = "${ctx}/survey/report/getData";
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var list = e.data.results.list;
                if(list){
                    var newData = [];
                    var newData1 = [];
                    var newData2 = [];
                    for(var i = 0 ; i < list.length; i ++ ){
                        newData.push(list[i].reportDateStr);
                        newData1.push(list[i].positiveRate.toFixed(2));
                        newData2.push(list[i].lossEfficiencyRate.toFixed(2));
                    }
                    option22.xAxis.data = newData;
                    option22.series[0].data = newData1;
                    option22.series[1].data = newData2;
                    myChart22.hideLoading();
                    myChart22.setOption(option22);
                }
            }else{
                alert("加载指标走势数据异常");
            }
        });
    }

    function initKeyTar(){
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"entrust","dataTable":"keyTar"};
        param["entrustOrgId"] = $("#entrustOrgId").val();
        param["searchType"] = searchType;
        param["startTime"] = $("#startTime").val();
        param["endTime"] = $("#endTime").val();
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var list = e.data.results.list;
                var entrusts = e.data.results.entrusts;//机构数量
                if(list){
                    var maxData = [];
                    var value1 = [];
                    var value2 = [];
                    for(var i = 0 ; i < list.length ; i ++ ){
                        var item = list[i];
                        var surveyMoney = item.surveyMoney.toFixed(2);
                        var efficiency = item.efficiency.toFixed(2);
                        var regional = item.regional.toFixed(2);
                        var positiveRate = item.positiveRate.toFixed(2);
                        var lossEfficiencyRate = item.lossEfficiencyRate.toFixed(2);
                        var average = item.average.toFixed(2);
                        var newSurveyCaseNum = item.newSurveyCaseNum.toFixed(2);
                        if(item.returnType == 1){
                            console.log("average:",average)
                            value1.push(surveyMoney,efficiency,regional,positiveRate * 100,lossEfficiencyRate * 100,average,newSurveyCaseNum);
                        }else if(item.returnType == 2){
                            surveyMoney = (item.surveyMoney.toFixed(2) / entrusts).toFixed(2);
                            efficiency = item.efficiency.toFixed(2);
                            regional = item.regional.toFixed(2);
                            positiveRate = item.positiveRate.toFixed(2);
                            lossEfficiencyRate = item.lossEfficiencyRate.toFixed(2);
                            average = item.average.toFixed(2);
                            newSurveyCaseNum = (item.newSurveyCaseNum.toFixed(2) / entrusts).toFixed(2);
                            value2.push(surveyMoney,efficiency,regional,positiveRate * 100,lossEfficiencyRate * 100,average,newSurveyCaseNum);
                        }else if(item.returnType == 3){
                            if(surveyMoney <= 0){surveyMoney = 1}
                            if(efficiency <= 0){efficiency = 1}
                            if(regional <= 0){regional = 1}
                            if(positiveRate <= 0){positiveRate = 1}
                            if(lossEfficiencyRate <= 0){lossEfficiencyRate = 1}
                            if(average <= 0){average = 1}
                            if(newSurveyCaseNum <= 0){newSurveyCaseNum = 1}
                            maxData.push({"name" : "营收额","max" : surveyMoney},{"name" : "调查时效","max" : efficiency},{"name" : "跨区域程度","max" : regional},
                                {"name" : "阳性率","max" : positiveRate * 100},{"name" : "超时效占比","max" : lossEfficiencyRate * 100},{"name" : "件均","max" : average},{"name" : "新增委托","max" : newSurveyCaseNum})
                        }
                    }
                    option1.radar.indicator = maxData;
                    option1.series[0].data[0].value = value1;
                    option1.series[0].data[1].value = value2;
                    myChart1.hideLoading();
                    myChart1.setOption(option1);
                }

                initPrice();
            }else{
                alert("加载关键指标数据异常");
            }
        });
    }

    function initPrice(){
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"entrust","dataTable":"price"};
        param["entrustOrgId"] = $("#entrustOrgId").val();
        param["searchType"] = searchType;
        param["startTime"] = $("#startTime").val();
        param["endTime"] = $("#endTime").val();
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var list = e.data.results.list;
                // if(list.length == 0){
                //     $("#charts6").hide();
                //     $('.c-charts6 .charts-no-data').show();
                // }else{
                //     $("#charts6").show();
                //     $('.c-charts6 .charts-no-data').hide();
                // }
                if(list){
                    var newXData = [];
                    var newYData = [];
                    for(var i = 0 ; i < list.length ; i++){
                        newXData.push(list[i].pirce)
                        newYData.push(list[i].num)
                    }
                    option6.xAxis.data = newXData;
                    option6.series[0].data = newYData;
                    myChart6.hideLoading();
                    if(list.length == 0){
                        myChart6.showLoading({
                            text: '暂无数据',
                            color: '#ffffff',
                            textColor: '#8a8e91',
                            maskColor: 'rgba(255, 255, 255, 0.8)',
                        })
                    }
                    myChart6.setOption(option6);
                }

                initIframe();
            }else{
                alert("加载数据异常");
            }
        });
    }




    $(function () {
        var isSun = false
        $('.btn-png').on('click', function () {
//            stop()
            $('.dalogs').show()

            if (!$('.d-value .d-radio[data-value=month]').hasClass('active')){
                $('.d-value .d-radio[data-value=month]').addClass('active')
                $('.d-value .d-radio[data-value=season]').removeClass('active')
            }
            //获取月份
            var today = new Date()
            var currYear = today.getFullYear()
            var currMonth = today.getMonth() + 1
            var currQuarter = Math.floor((currMonth % 3 == 0 ? (currMonth / 3) : (currMonth / 3 + 1)));
            var _html = monthYear(currYear, currMonth)
            $('.dalogs select').html(_html)

            downFile()
            var $window = $(window)
            var d_width = $window.width()
            var d_height = $window.height()
            console.log('-----', d_width,d_height)
            $('.dalogs-contain').css({
                width: d_width,
                height: d_height,
                // marginTop: d_height * 0.1,
            })
            var dc_width = d_width * 0.9 - $('.dalogs-downfile ').width() - 80
            var dcCss = {}
            if (dc_width > 800) {
                dcCss = {
                    width: 800,
                    paddingLeft: (dc_width - 800) / 2 + 40,
                    paddingRight: (dc_width - 800) / 2 + 40

                }
            } else {
                dcCss = {
                    width: dc_width,
                }
            }
            $('.dalogs-content').css(dcCss)

        })
        $('.cancel').on('click', function () {
//            move()
//             $('#csxzb').val(undefined)
            $('.dalogs').hide()
            console.log($('.dalogs-downfile').scrollTop())
            $('.dalogs-downfile').scrollTop(0)
        })
        $('.download').on('click', function () {
            // $('#csxzb').val(undefined)
            var _this = $(this)
            $('.loading-bar').show()
            up()
            _this.css('pointer-events', 'none')
            $('.dalogs-downfile').scrollTop(0)
        })
        $('.dalogs select').change(function () {
          downFile()
        })
        $('.d-value2 .d-radio').on('click', function (e) {
            var _this = $(this)
            if (!_this.hasClass('active')) {
                _this.addClass('active')
                _this.siblings().removeClass('active')
            }

            var maxData = [];
            var value1 = [];
            var value2 = [];
            if (_this.attr('data-value') == 'showsun') {
                $('.qualities').removeClass('qualities2')
                isSun = true
                maxData = JSON.parse(sessionStorage.getItem("isSunMaxData"))
                value1 = sessionStorage.getItem("isSunData1").split(',')
                value2 = sessionStorage.getItem("isSunData2").split(',')
            } else if (_this.attr('data-value') == 'hidesun') {
                $('.qualities').addClass('qualities2')
                isSun = false
                maxData = JSON.parse(sessionStorage.getItem("noSunMaxData"))
                value1 = sessionStorage.getItem("noSunData1").split(',')
                value2 = sessionStorage.getItem("noSunData2").split(',')
            }
            option31.radar.indicator = maxData;
            option31.series[0].data[0].value = value1;
            option31.series[0].data[1].value = value2;
            var myChart31 = echarts.init(document.getElementById('charts31'));
            myChart31.hideLoading();
            myChart31.setOption(option31);

        })
        $('.d-value1 .d-radio').on('click', function (e) {
            var _this = $(this)
            if (!_this.hasClass('active')) {
                _this.addClass('active')
                _this.siblings().removeClass('active')
            }
            var today = new Date()
            var currYear = today.getFullYear()
            var currMonth = today.getMonth() + 1
            var currQuarter = Math.floor((currMonth % 3 == 0 ? (currMonth / 3) : (currMonth / 3 + 1)));
            if (_this.attr('data-value') == 'season') {
                $("#seasonType").show();
                $("#monthType").hide();
                var _html = quarter(currYear, currMonth, currQuarter)
                $('.dalogs select').html(_html)
            } else if (_this.attr('data-value') == 'month') {
                $("#seasonType").hide();
                $("#monthType").show();
                var _html = monthYear(currYear, currMonth)
                $('.dalogs select').html(_html)
            }

            downFile()

        })

        $('#csxzb').blur(function () {
            var num = $(this).val()
            num = (Math.round(num*100)/100).toFixed(2)
            $(this).val(num)
            $('.quality.quality-csx .csxzb').text( num + '%')
        })

        /*----------------------------------*/
        $('.singleSelect').select2();

        $('.btn-icon').click(function () {
            var _this = $(this)
            var index = Number(_this.attr('data-index'))
            if(_this.attr('id') == 'div_page_total'){ //下一页

                if (_this.attr('data-page') <= index) {
                    alert('最后一页')
                    return;
                }
                var _bar = $('.bar')
                _bar.attr('style', '')
                _bar.css({
                    'transform': 'translateY(-' + 611 * index + 'px)'
                })
                $('.btn-icon').attr('data-index', index + 1)
                $('.btn-page span').eq(0).text(index + 1)
            }
            if(_this.attr('id') == 'div_page_total2'){ //上一页
                if (index == 1) {
                    alert('第一页')
                    return;
                }
                var _bar = $('.bar')
                _bar.attr('style', '')
                _bar.css({
                    'transform': 'translateY(-' + 611 * (index - 2) + 'px)'
                })
                $('.btn-icon').attr('data-index', index - 1)
                $('.btn-page span').eq(0).text(index - 1)
            }
        })
        $('.chartsm-info .btn').click(function () {
            var i = Number($('input.page-num').val())
            if (!i) {
                alert('请输入页数')
                return;
            }
            var _this = $(this)
            var _bar = $('.bar')
            if (i > Number($('.btn-page span').eq(1).text())) {
                alert('没有这一页')
                return;
            }
            _bar.attr('style', '')
            _bar.css({
                'transform': 'translateY(-' + 611 * (i-1) + 'px)'
            })
            _this.attr('data-index', i)
            $('.btn-page span').eq(0).text(i);
            $('.btn-icon').attr('data-index', i);

        })

        $('.header-choose-bar').click(function () {
            var _this = $(this)
            _this.addClass('active')
            _this.siblings().removeClass('active')

            var searchType = _this.attr("data-value");
            $("#searchType").val(searchType);

            $("#tbStartTime").val("");
            $("#tbEndTime").val("");
            initData();
        })
        $('.i-table .icon-up').click(function () {
            var _this = $(this)
            $('.i-td-text').attr('style', '')
            $('.i-table .icon-up').attr('style', '')
            $('.i-table .icon-down').attr('style', '')
            _this.css({
                'border-bottom-color': '#333'
            })
            _this.parent().siblings().css({
                'font-weight': '600'
            })
        })
        $('.i-table .icon-down').click(function () {
            var _this = $(this)
            $('.i-td-text').attr('style', '')
            $('.i-table .icon-up').attr('style', '')
            $('.i-table .icon-down').attr('style', '')
            _this.css({
                'border-top-color': '#333'
            })
            _this.parent().siblings().css({
                'font-weight': '600'
            })
        })
        $('.i-header a').click(function () {
            var _this = $(this)
            _this.addClass('selected')
            _this.siblings().removeClass('selected')
        })

        $('.chartsm-info .icon-up').click(function () {
            var _this = $(this)
            $('.chartsm-info.icon-up').attr('style', '')
            $('.chartsm-info .icon-down').attr('style', '')
            _this.css({
                'border-bottom-color': '#333'
            })
            _this.parent().siblings().css({
                'font-weight': '600'
            })
        })
        $('.chartsm-info .icon-down').click(function () {
            var _this = $(this)
            $('.chartsm-info .icon-up').attr('style', '')
            $('.chartsm-info .icon-down').attr('style', '')
            _this.css({
                'border-top-color': '#333'
            })
            _this.parent().siblings().css({
                'font-weight': '600'
            })
        })



        $('.i-table .i-thead .i-td').click(function () {
            var _this = $(this)
            var i = $(this).index()
            $('.i-td-text').attr('style', '')
            $('.i-table .icon-up').attr('style', '')
            $('.i-table .icon-down').attr('style', '')
            _this.find('.i-td-text').css({
                'font-weight': '600'
            })
            if (_this.attr('data-sort') == 'up' ||  !_this.attr('data-sort')){
                _this.attr('data-sort', 'down')
                _this.find('.icon-down').css({
                    'border-top-color': '#333'
                })
                $("#sortType").val(2);
            } else if (_this.attr('data-sort') == 'down' ){
                _this.attr('data-sort', 'up')
                _this.find('.icon-up').css({
                    'border-bottom-color': '#333'
                })
                $("#sortType").val(1);
            }
            var sortAttr = _this.attr("data-sort-attr");
            $("#sortAttr").val(sortAttr);
            searchOrg(2);
        });
    })

    function quarter(currYear, currMonth, currQuarter) {
        var nums = ['零', '一', '二', '三', '四']
        var startYear = 2001
        var endYear = currYear
        var quarter = Number(currQuarter)
        var arr = []
        var _html = ''
        // if (quarter != 1) {
        //     for (var i = quarter - 1; i <= quarter && i > 0; i--) {
        //         _html += '<option value='+ endYear + '-' + i+'>' + endYear + '年第' + nums[i] + '季度</option>'
        //     }
        // }
        for (var j = endYear; j<= endYear && j > startYear; j--) {
            var curQ = quarter
            if (j < endYear){
                curQ = 4
            }
            for (let m = curQ; m <= curQ && m > 0; m--) {
                // arr.push(j + '年第' + nums[m] + '季度')
                _html += '<option value='+ j + '-' + m+'>' + j + '年第' + nums[m] + '季度</option>'
            }
        }
        return _html
    }


    function monthYear(currYear, curMonth) {
//        var nums = ['零', '一', '二', '三', '四','五',]
        var startYear = 2019
        var endYear = currYear
        var currMonth = Number(curMonth)
        var arr = []
        var _html = ''
        // if (currMonth != 1) {
        //     for (var i = currMonth - 1; i <= currMonth && i > 0; i--) {
        //         _html += '<option value='+ endYear + '-' + i+'>' + endYear + '年' + i + '月</option>'
        //     }
        // }
        for (var j = endYear; j <= endYear && j > startYear; j--) {
            var curM = currMonth
            if (j < endYear){
                curM = 12
            }
            for (let m = curM; m <= curM && m > 0; m--) {
                // arr.push(j + '年第' + nums[m] + '季度')
                _html += '<option value='+ j + '-' + m+'>' + j + '年' + m + '月</option>'
            }
        }
        return _html
    }
    /*-------数据源------------*/
    var optionData1 = {
        title: {
            text: '关键指标雷达图'
        },
        legend: {
            data: ['当前保司值', '保司平均值']
        },
        radar: {
            indicator: [{
                name: '营收额',
                max: 3000
            },
                {
                    name: '新增委托',
                    max: 250
                },
                {
                    name: '件均',
                    max: 1000
                },
                {
                    name: '阳性率',
                    max: 5
                },
                {
                    name: '超时效占比',
                    max: 2
                },
                {
                    name: '跨区域程度',
                    max: 10
                },
                {
                    name: '调查时效',
                    max: 5
                }
            ]
        },
        series: {
            data: [{
                value: [1000, 100, 500, 1, 1, 2, 2.2],
                name: '当前保司值',
                label: {
                    normal: {
                        show: false,
                        formatter: function (params) {
                            return params.value;
                        },
                    }
                },
                areaStyle: {
                    normal: {
                        opacity: 0.3
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#c23531',
                    }
                },
                lineStyle: {
                    normal: {
                        width: '2.5'
                    }
                },
                // emphasis: {
                //     label: {
                //         show: true,
                //         fontSize: 16
                //     }
                // }
            }, {
                value: [800, 120, 500, 0.61, 0.81, 1.7, 1.9],
                name: '保司平均值',
                label: {
                    normal: {
                        show: false,
                        formatter: function (params) {
                            return params.value;
                        }
                    }
                },
                areaStyle: {
                    normal: {
                        opacity: 0.1,
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#2f4554'
                    }
                },
                lineStyle: {
                    normal: {
                        width: '1'
                    }
                },
                // emphasis: {
                //     label: {
                //         show: true,
                //         fontSize: 16
                //     }
                // }
            }]
        }
    }

    var optionDataSource = {
        title: {
            text: '新增委托来源'
        },
        series: {
            data: []
        }
    }

    var optionData2 = {
        title: {
            text: '案件走势'
        },
        legend: {
            data: ['新增委托', '保司终审通过']
        },
        xAxis: {
            data: ['09.01', '09.02', '09.03', '09.04', '09.05', '09.06', '09.07', '09.08', '09.09', '09.10',
                '09.11', '09.12', '09.13', '09.14', '09.15', '09.16', '09.17', '09.18', '09.19'
            ]
        },
        series: {
            data: [{
                name: '新增委托',
                type: 'line',
                data: [120, 132, 101, 134, 90, 230, 200],
                smooth: true
            },
                {
                    name: '保司终审通过',
                    type: 'line',
                    data: [220, 182, 191, 234, 290, 330, 300],
                    smooth: true
                },
            ]
        }
    }
    var optionData22 = {
        title: {
            text: '指标走势'
        },
        legend: {
            data: ['阳性率', '超时效占比']
        },
        xAxis: {
            data: ['09.01', '09.02', '09.03', '09.04', '09.05', '09.06', '09.07', '09.08', '09.09', '09.10',
                '09.11', '09.12', '09.13', '09.14', '09.15', '09.16', '09.17', '09.18', '09.19'
            ]
        },
        series: {
            data: [{
                name: '阳性率',
                type: 'line',
                data: [120, 132, 101, 134, 90, 230, 200],
                smooth: true
            },
                {
                    name: '超时效占比',
                    type: 'line',
                    data: [220, 182, 191, 234, 290, 330, 300],
                    smooth: true
                },
            ]
        }
    }

    var optionData3 = {
        title: {
           text: '任务统计'
        },
        legend: {
            data: ['任务类型1', '任务类型2', '任务类型3', '2-子类A', '2-子类B', '2-子类C', '3-子类A', '3-子类B',
                '3-子类C', '3-子类D'
            ]
        },
        series1: {
            name: '',
            data: [{
                value: 335,
                name: '任务类型1',
            },
                {
                    value: 679,
                    name: '任务类型2'
                },
                {
                    value: 1548,
                    name: '任务类型3'
                }
            ]
        },
        series1: {
            name: '任务类型',
            data: [{
                value: 335,
                name: '任务类型1',
            },
                {
                    value: 679,
                    name: '任务类型2'
                },
                {
                    value: 1548,
                    name: '任务类型3'
                }
            ]
        },
        series2: {
            name: '任务子类',
            data: [{
                value: 335,
                name: '1-子类'
            },
                {
                    value: 310,
                    name: '2-子类A'
                },
                {
                    value: 234,
                    name: '2-子类B'
                },
                {
                    value: 135,
                    name: '2-子类C'
                },
                {
                    value: 1048,
                    name: '3-子类A'
                },
                {
                    value: 251,
                    name: '3-子类B'
                },
                {
                    value: 147,
                    name: '3-子类C'
                },
                {
                    value: 102,
                    name: '3-子类D'
                }
            ]
        }
    }

    var optionData4 = {
        series: {
            data: [{
                value: 535,
                name: '单区域'
            },
                {
                    value: 510,
                    name: '二区域'
                },
                {
                    value: 634,
                    name: '三区域'
                }
            ]
        }
    }

    var data = [
        {
        name: '青岛',
        value: 18
    },
        {
            name: '句容',
            value: 75
        },
        {
            name: '北京',
            value: 79
        },
        {
            name: '徐州',
            value: 79
        },
        {
            name: '乌鲁木齐',
            value: 84
        },
        {
            name: '枣庄',
            value: 84
        },
        {
            name: '杭州',
            value: 84
        },
        {
            name: '济南',
            value: 92
        },
        {
            name: '兰州',
            value: 99
        }
    ];

    var optionData6 = {
        title: {
            text: '案件价格分布'
        },
        xAxis: {
            data: [200, 221, 223, 224, 300, 303, 304, 500, 600, 200, 221, 223, 224, 300, 303, 304, 500, 600,
                200,
                221, 223, 224, 300, 303, 304, 500, 600, 200, 221, 223, 224, 300, 303, 304, 500, 600, 200,
                221,
                223, 224, 300, 303, 304, 500, 600, 200, 221, 223, 224, 300, 303, 304, 500, 600
            ]
        },
        series: {
            data: [100, 200, 400, 200, 400, 300, 299, 700, 600, 100, 200, 400, 200, 400, 300, 299, 700, 600,
                100,
                200, 400, 200, 400, 300, 299, 700, 600, 100, 200, 400, 200, 400, 300, 299, 700, 600, 100,
                200,
                400, 200, 400, 300, 299, 700, 600, 100, 200, 400, 200, 400, 300, 299, 700, 600
            ]
        }
    }

    var optionData7 = {
        series: {
            data: [{
                value: 535,
                name: '单区域'
            },
                {
                    value: 510,
                    name: '二区域'
                },
                {
                    value: 634,
                    name: '三区域'
                }
            ]
        }
    }

    /*-------图表------------*/

    var option1 = {
        backgroundColor: '#fff',
        title: {
            text: optionData1.title.text,
            textStyle: {
                fontSize: 16
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: function (params) {
                return params.name + '<br/>  营收额 ' + params.data.value[0] +
                    '元<br/>调查时效' + params.data.value[1] + '天<br/>跨区域程度 ' + params.data.value[
                        2] + '省<br/>阳性率 ' + params.data.value[3] + '%<br/> 超时效占比 ' + params
                        .data.value[4] + '%<br/>件均 ' + params.data.value[5] + '元<br/>新增委托 ' +
                    params.data.value[6] + '件<br/>'
            },
        },
        legend: {
            data: optionData1.legend.data,
            bottom: 0
        },
        radar: {
            center: ['54%', '50%'],
            radius: '60%',
            indicator: optionData1.radar.indicator,
            shape: 'circle',
            splitNumber: 5,
            name: {
                textStyle: {
                    color: '#2f4554'
                }
            },
            splitLine: {
                lineStyle: {
                    color: [
                        'rgba(64, 160, 210, 0.1)', 'rgba(26, 51, 151, 0.2)',
                        'rgba(64, 160, 210, 0.4)', 'rgba(26, 51, 151, 0.6)',
                        'rgba(64, 160, 210, 0.8)', 'rgba(26, 51, 151, 1)'
                    ].reverse()
                }
            },
            splitArea: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(64, 160, 210, 0.5)'
                }
            }
        },
        series: [{
            type: 'radar',
            lineStyle: {
                normal: {
                    width: 1,
                    opacity: 0.5
                }
            },
            data: optionData1.series.data
        }]
    };

    var optionSource = {
        title: {
            text: optionDataSource.title.text,
            x: 'left',
            textStyle: {
                fontSize: 16
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            },
            right: 20
        },
        series: [{
            name: '',
            type: 'pie',
            radius: '55%',
            center: ['50%', '50%'],
            data: optionDataSource.series.data,
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }]
    };


    var option2 = {
        // title: {
        //     text: optionData2.title.text,
        //     textStyle: {
        //         fontSize: 16
        //     }
        // },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: optionData2.legend.data
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                magicType: {
                    type: ['line', 'bar']
                },
                restore: {},
                saveAsImage: {}
            },
            right: 20
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: optionData2.xAxis.data

        },
        yAxis: {
            type: 'value'
        },
        series: optionData2.series.data
    };
    var option22 = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: optionData22.legend.data
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                magicType: {
                    type: ['line', 'bar']
                },
                restore: {},
                saveAsImage: {}
            },
            right: 20
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: optionData22.xAxis.data

        },
        yAxis: {
            type: 'value'
        },
        series: optionData22.series.data
    };

    var option3 = {
        title: {
            text: optionData3.title.text,
            x: 'left',
            textStyle: {
                fontSize: 16
            }
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            },
            right: 20
        },
        tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            top: 40,
            data: optionData3.legend.data
        },
        series: [{
            name: optionData3.series1.name,
            type: 'pie',
            selectedMode: 'single',
            radius: [0, '45%'],
            center: ['55%', '50%'],
            label: {
                normal: {
                    show: false,
                    formatter: "{a}: <br/>     {b}",
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: optionData3.series1.data
        },
            {
                name: optionData3.series2.name,
                type: 'pie',
                radius: ['60%', '80%'],
                center: ['55%', '50%'],
                label: {
                    normal: {
                        show: false
                    },
                    normal: {
                        formatter: ' {b}：{c}  ',
                        backgroundColor: '#eee',
                        borderColor: '#aaa',
                        borderWidth: 1,
                        borderRadius: 4,
                        padding: [5, 0],
                        rich: {
                            a: {
                                color: '#999',
                                lineHeight: 20,
                                align: 'center'
                            },
                            hr: {
                                borderColor: '#aaa',
                                width: '100%',
                                borderWidth: 0.5,
                                height: 0
                            },
                            b: {
                                fontSize: 13,
                                lineHeight: 20,
                            },
                            per: {
                                color: '#eee',
                                backgroundColor: '#334455',
                                padding: [2, 4],
                                borderRadius: 2
                            }
                        }
                    }
                },
                data: optionData3.series2.data
            }
        ],

    };

    var option4 = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            },
            right: 20
        },
        series: [{
            name: '',
            type: 'pie',
            radius: '55%',
            center: ['50%', '50%'],
            data: optionData4.series.data,
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }]
    };

    var geoCoordMap = {
        '河北省': [114.48, 38.03],
        '辽宁省': [123.38, 41.8],
        '黑龙江省': [126.63, 45.75],
        '浙江省': [120.19, 30.26],
        '福建省': [119.3, 26.08],
        '山东省': [117, 36.65],
        '广东省': [113.23, 23.16],
        '湖北省': [114.31, 30.52],
        '四川省': [104.06, 30.67],
        '云南省': [102.73, 25.04],
        '甘肃省': [103.73, 36.03],
        '广西壮族自治区': [108.33, 22.84],
        '宁夏回族自治区': [106.27, 38.47],
        '山西省': [112.53, 37.87],
        '吉林省': [125.35, 43.88],
        '江苏省': [118.78, 32.04],
        '安徽省': [117.27, 31.86],
        '江西省': [115.89, 28.68],
        '河南省': [113.65, 34.76],
        '湖南省': [113, 28.21],
        '海南省': [110.35, 20.02],
        '贵州省': [106.71, 26.57],
        '陕西省': [108.95, 34.27],
        '青海省': [101.74, 36.56],
        '内蒙古自治区': [111.65, 40.82],
        '西藏自治区': [91.11, 29.97],
        '新疆维吾尔自治区': [87.68, 43.77],

        '上海市': [121.48, 31.22],
        '北京市': [116.46, 39.92],
        '重庆市': [106.54, 29.59],
        '天津市': [117.2, 39.13],

        '临沧市': [100.09,23.89],
        '丽江市': [100.23,26.88],
        '保山市': [99.18,25.12],
        '大理白族自治州': [100.22,25.60],
        '德宏傣族景颇族自治州': [98.59,24.44],
        '怒江傈僳族自治州': [98.86,25.86],
        '文山壮族苗族自治州': [104.25,23.37],
        '大理白族自治州': [100.22,25.60],
        '昆明市': [102.73, 25.04],
        '昭通市': [103.73,27.34],
        '普洱市': [100.98,22.79],
        '曲靖市': [103.78,25.52],
        '楚雄彝族自治州': [101.53,25.07],
        '玉溪市': [102.55,24.37],
        '红河哈尼族彝族自治州': [103.38,23.37],
        '西双版纳傣族自治州': [100.80,22.01],
        '迪庆藏族自治州': [99.71,27.83],

        '乌兰察布': [113.11,41.02],
        '乌海市': [106.83,39.68],
        '兴安盟': [122.048166514,46.0837570652],
        '包头市': [109.846238532,40.6471194257],
        '呼伦贝尔市': [119.760821794,49.2016360546],
        '呼和浩特市': [111.66035052,40.8283188731],
        '巴彦淖尔市': [107.42380672,40.7691799024 ],
        '赤峰市': [118.87, 42.28],
        '通辽市': [122.260363263,43.633756073],
        '鄂尔多斯市': [109.993706251,39.8164895606],
        '锡林郭勒盟': [116.027339689,43.9397048423],
        '阿拉善盟': [105.695682871,38.8430752644],

        '吉林市': [126.564543989,43.8719883344],
        '四平市': [124.391382074,43.1755247011],
        '延边朝鲜族自治州': [129.485901958,42.8964136037],
        '松原市': [124.832994532,45.1360489701],
        '白城市': [122.840776679,45.6210862752],
        '白山市': [126.435797675,41.945859397],
        '辽源市': [125.133686052,42.9233026191],
        '通化市': [125.942650139,41.7363971299],
        '长春市': [125.35, 43.88],


        '乐山市': [125.35, 43.88],
        '内江市': [125.35, 43.88],
        '凉山彝族自治州': [125.35, 43.88],
        '南充市': [106.110698, 30.837793],
        '宜宾市': [104.56, 29.77],
        '巴中市': [106.757915842,31.8691891592],
        '广元市': [104.56, 29.77],
        '广安市': [104.56, 29.77],
        '德阳市': [104.37, 31.13],
        '成都市': [104.06, 30.67],
        '攀枝花市': [101.718637, 26.582347],
        '泸州市': [105.39, 28.91],
        '甘孜藏族自治州': [101.969232063,30.0551441144],
        '眉山市': [103.841429563,30.0611150799],
        '绵阳市': [104.73, 31.48],
        '自贡市': [104.778442, 29.33903],
        '资阳市': [104.635930302,30.132191434],
        '达州市': [107.494973447,31.2141988589],
        '遂宁市': [105.564887792,30.5574913504],
        '阿坝藏族羌族自治州': [102.228564689,31.9057628583],
        '雅安市': [103.009356466,29.9997163371],

        '中卫市': [105.196754199,37.5211241916],
        '吴忠市': [106.208254199,37.9935610029],
        '固原市': [106.285267996,36.0215234807],
        '石嘴山市': [106.379337202,39.0202232836],
        '银川市': [106.27, 38.47],

        '亳州市': [115.787928245,33.8712105653],
        '六安市': [116.505252683,31.7555583552],
        '合肥市': [117.27, 31.86],
        '安庆市': [117.058738772,30.5378978174],
        '宣城市': [118.752096311,30.9516423543],
        '宿州市': [116.988692412,33.6367723858],
        '池州市': [117.494476772,30.6600192482],
        '淮北市': [116.791447429,33.9600233054],
        '淮南市': [117.018638863,32.6428118237],
        '滁州市': [118.324570351,32.3173505954],
        '芜湖市': [118.384108423,31.3660197875],
        '蚌埠市': [117.357079866,32.9294989067],
        '铜陵市': [117.819428729,30.9409296947],
        '阜阳市': [115.820932259,32.9012113306],
        '马鞍山市': [118.515881847,31.6885281589],
        '黄山市': [118.293569632,29.7344348562],

        '东营市': [118.49, 37.46],
        '临沂市': [118.35, 35.05],
        '威海市': [122.1, 37.5],
        '德州市': [116.29, 37.45],
        '日照市': [119.46, 35.42],
        '枣庄市': [117.57, 34.86],
        '泰安市': [117.13, 36.18],
        '济南市': [117, 36.65],
        '济宁市': [116.59, 35.38],
        '淄博市': [118.05, 36.78],
        '滨州市': [118.03, 37.36],
        '潍坊市': [119.1, 36.62],
        '烟台市': [121.39, 37.52],
        '聊城市': [115.97, 36.45],
        '莱芜市': [117.67, 36.19],
        '菏泽市': [115.480656, 35.23375],
        '青岛市': [120.33, 36.07],

        '临汾市': [111.5, 36.08],
        '吕梁市': [111.143156602,37.527316097],
        '大同市': [113.3, 40.12],
        '太原市': [112.53, 37.87],
        '忻州市': [112.727938829,38.461030573],
        '晋中市': [112.7385144,37.6933615268],
        '晋城市': [112.867332758,35.4998344672],
        '朔州市': [112.479927727,39.3376719662],
        '运城市': [111.006853653,35.0388594798],
        '长治市': [113.08, 36.18],
        '阳泉市': [113.57, 37.85],


        '东莞市': [113.75, 23.04],
        '中山市': [113.38, 22.52],
        '云浮市': [112.02, 22.93],
        '佛山市': [113.11, 23.05],
        '广州市': [113.23, 23.16],
        '惠州市': [114.4, 23.09],
        '揭阳市': [116.35, 23.55],
        '梅州市': [116.1, 24.55],
        '汕头市': [116.69, 23.39],
        '汕尾市': [115.375279, 22.786211],
        '江门市': [113.06, 22.61],
        '河源市': [114.68, 23.73],
        '深圳市': [114.07, 22.62],
        '清远市': [113.01, 23.7],
        '湛江市': [110.359377, 21.270708],
        '潮州市': [116.63, 23.68],
        '珠海市': [113.52, 22.3],
        '肇庆市': [112.44, 23.05],
        '茂名市': [110.88, 21.68],
        '阳江市': [111.95, 21.85],
        '韶关市': [113.62, 24.84],

        '北海市': [109.12, 21.49],
        '南宁市': [108.33, 22.84],
        '崇左市': [107.357322038,22.4154552965],
        '来宾市': [109.231816505,23.7411659265],
        '柳州市': [109.4, 24.33],
        '桂林市': [110.28, 25.29],
        '梧州市': [111.30547195,23.4853946367],
        '河池市': [108.069947709,24.6995207829],
        '玉林市': [110.151676316,22.6439736084],
        '百色市': [106.631821404,23.9015123679],
        '贵港市': [109.613707557,23.1033731644],
        '贺州市': [111.552594179,24.4110535471],
        '钦州市': [108.638798056,21.9733504653],
        '防城港市': [108.351791153,21.6173984705],

        '乌鲁木齐市': [87.68, 43.77],
        '伊犁哈萨克自治州': [81.2978535304,43.9222480963],
        '克孜勒苏柯尔克孜自治州': [76.1375644775,39.7503455778],
        '克拉玛依市': [84.77, 45.59],
        '博尔塔拉蒙古自治州': [82.0524362672,44.9136513743],
        '吐鲁番地区': [89.1815948657,42.9604700169],
        '和田地区': [79.9302386372,37.1167744927],
        '哈密地区': [93.5283550928,42.8585963324],
        '喀什地区': [75.9929732675,39.4706271887],
        '塔城地区': [82.9748805837,46.7586836297],
        '昌吉回族自治州': [87.2960381257,44.0070578985],
        '阿克苏地区': [80.2698461793,41.1717309015],
        '阿勒泰地区': [88.1379154871,47.8397444862],

        '南京市': [118.78, 32.04],
        '南通市': [121.05, 32.08],
        '宿迁市': [118.3, 33.96],
        '常州市': [119.95, 31.79],
        '徐州市': [117.2, 34.26],
        '扬州市': [119.42, 32.39],
        '无锡市': [120.29, 31.59],
        '淮安市': [119.15, 33.5],
        '泰州市': [119.9, 32.49],
        '盐城市': [120.13, 33.38],
        '苏州市': [120.62, 31.32],
        '连云港市': [119.16, 34.59],
        '镇江市': [119.44, 32.2],


        '上饶市': [117.955463877,28.4576225539],
        '九江市': [115.97, 29.71],
        '南昌市': [115.89, 28.68],
        '吉安市': [114.992038711,27.1138476502],
        '宜春市': [114.400038672,27.8111298958],
        '抚州市': [116.360918867,27.9545451703],
        '新余市': [114.947117417,27.8223215586],
        '景德镇市': [117.186522625,29.3035627684],
        '萍乡市': [113.859917033,27.639544223],
        '赣州市': [114.935909079,25.8452955363],
        '鹰潭市': [117.035450186,28.2413095972],

        '保定市': [115.48, 38.85],
        '唐山市': [118.02, 39.63],
        '廊坊市': [116.7, 39.53],
        '张家口市': [114.87, 40.82],
        '承德市': [117.93, 40.97],
        '沧州市': [116.83, 38.33],
        '秦皇岛市': [119.57, 39.95],
        '石家庄市': [114.48, 38.03],
        '衡水市': [115.72, 37.72],
        '邢台市': [114.48, 37.05],
        '邯郸市': [114.47, 36.6],

        '三门峡市': [111.19, 34.76],
        '信阳市': [114.085490993,32.1285823075],
        '南阳市': [112.542841901,33.0114195691],
        '周口市': [114.654101942,33.6237408181],
        '商丘市': [115.641885688,34.4385886402],
        '安阳市': [114.35, 36.1],
        '平顶山市': [113.29, 33.75],
        '开封市': [114.35, 34.79],
        '新乡市': [113.912690161,35.3072575577],
        '洛阳市': [112.44, 34.7],
        '漯河市': [114.0460614,33.5762786885],
        '濮阳市': [115.026627441,35.7532978882],
        '焦作市': [113.21, 35.24],
        '许昌市': [113.83531246,34.0267395887],
        '郑州市': [113.65, 34.76],
        '驻马店市': [114.049153547,32.9831581541],
        '鹤壁市': [114.297769838,35.7554258742],

        '丽水市': [119.92, 28.45],
        '台州市': [121.420757, 28.656386],
        '嘉兴市': [120.76, 30.77],
        '宁波市': [121.56, 29.86],
        '杭州市': [120.19, 30.26],
        '温州市': [120.65, 28.01],
        '湖州市': [120.1, 30.86],
        '金华市': [119.64, 29.12],
        '舟山市': [122.207216, 29.985295],
        '绍兴市': [120.58, 30.01],
        '衢州市': [118.88, 28.97],

        '三亚市': [109.511909, 18.252847],
        '三沙市': [112.350383075,16.840062894],
        '海口市': [110.35, 20.02],

        '十堰市': [110.801228917,32.6369943395],
        '咸宁市': [114.300060592,29.8806567577],
        '孝感市': [113.935734392,30.9279547842],
        '宜昌市': [111.3, 30.7],
        '恩施土家族苗族自治州': [109.491923304,30.2858883166],
        '武汉市': [114.31, 30.52],
        '荆州市': [112.239741, 30.335165],
        '荆门市': [112.217330299,31.0426112029],
        '襄阳市': [112.250092848,32.2291685915],
        '鄂州市': [114.895594041,30.3844393228],
        '随州市': [113.379358364,31.7178576082],
        '黄冈市': [114.906618047,30.4461089379],
        '黄石市': [115.050683164,30.2161271277],

        '娄底市': [111.996396357,27.7410733023],
        '岳阳市': [113.09, 29.37],
        '常德市': [111.69, 29.05],
        '张家界市': [110.479191, 29.117096],
        '怀化市': [109.986958796,27.5574829012],
        '株洲市': [113.16, 27.83],
        '永州市': [111.614647686,26.4359716468],
        '湘潭市': [112.91, 27.87],
        '湘西土家族苗族自治州': [109.7457458,28.3179507937],
        '益阳市': [112.366546645,28.5880877799],
        '衡阳市': [112.583818811,26.8981644154],
        '邵阳市': [111.461525404,27.2368112449],
        '郴州市': [113.037704468,25.7822639757],
        '长沙市': [113, 28.21],

        '临夏回族自治州': [103.215249178,35.5985143488],
        '兰州市': [103.73, 36.03],
        '嘉峪关市': [98.289152, 39.77313],
        '天水市': [105.736931623,34.5843194189],
        '定西市': [104.626637601,35.5860562418],
        '平凉市': [106.688911157,35.55011019],
        '庆阳市': [107.644227087,35.7268007545],
        '张掖市': [100.459891869,38.939320297],
        '武威市': [102.640147343,37.9331721429],
        '甘南藏族自治州': [102.917442486,34.9922111784],
        '白银市': [104.171240904,36.5466817062],
        '酒泉市': [98.5084145062,39.7414737682],
        '金昌市': [102.188043, 38.520089],
        '陇南市': [104.934573406,33.3944799729],

        '三明市': [117.642193934,26.2708352794],
        '南平市': [118.181882949,26.6436264742],
        '厦门市': [118.1, 24.46],
        '宁德市': [119.54208215,26.6565274192],
        '泉州市': [118.58, 24.93],
        '漳州市': [117.676204679,24.5170647798],
        '福州市': [119.3, 26.08],
        '莆田市': [119.077730964,25.4484501367],
        '龙岩市': [117.017996739,25.0786854335],

        '拉萨市': [91.11, 29.97],

        '六盘水市': [104.85208676,26.5918660603],
        '安顺市': [105.928269966,26.2285945777],
        '毕节市': [105.333323371,27.4085621313],
        '遵义市': [106.9, 27.7],
        '贵阳市': [106.71, 26.57],
        '铜仁市': [109.168558028,27.6749026906],
        '黔东南苗族侗族自治州': [107.985352573,26.5839917665],
        '黔南布依族苗族自治州': [107.52320511,26.2645359974],
        '黔西南布依族苗族自治州': [104.900557798,25.0951480559],

        '丹东市': [124.37, 40.13],
        '大连市': [121.62, 38.92],
        '抚顺市': [123.97, 41.97],
        '朝阳市': [120.446162703,41.5718276679],
        '本溪市': [123.73, 41.3],
        '沈阳市': [123.38, 41.8],
        '盘锦市': [122.070714, 41.119997],
        '营口市': [122.18, 40.65],
        '葫芦岛市': [120.836932, 40.711052],
        '辽阳市': [123.172451205,41.2733392656],
        '铁岭市': [123.854849615,42.2997570121],
        '锦州市': [121.15, 41.13],
        '阜新市': [121.660822129,42.0192501071],
        '鞍山市': [122.85, 41.12],

        '咸阳市': [108.72, 34.36],
        '商洛市': [109.934208154,33.8739073951],
        '安康市': [109.038044563,32.70437045],
        '宝鸡市': [107.15, 34.38],
        '延安市': [109.47, 36.6],
        '榆林市': [109.745925744,38.2794392401],
        '汉中市': [107.045477629,33.0815689782],
        '渭南市': [109.5, 34.52],
        '西安市': [108.95, 34.27],
        '铜川市': [109.11, 35.09],

        '西宁市': [101.74, 36.56],
        '果洛藏族自治州': [100.223722769,34.4804845846],
        '海东地区': [102.085206987,36.5176101677],
        '海北藏族自治州': [100.879802174,36.9606541011],
        '海南藏族自治州': [100.624066094,36.2843638038],
        '海西蒙古族藏族自治州': [97.3426254153,37.3737990706],
        '玉树藏族自治州': [97.0133161374,33.0062399097],
        '黄南藏族自治州': [102.007600308,35.5228515517],

        '七台河市': [131.019048047,45.7750053686],
        '伊春市': [128.910765978,47.7346850751],
        '佳木斯市': [130.284734586,46.8137796047],
        '双鸭山市': [131.17140174,46.6551020625],
        '哈尔滨市': [126.63, 45.75],
        '大庆市': [125.03, 46.58],
        '牡丹江市': [129.58, 44.6],
        '绥化市': [126.989094572,46.646063927],
        '鸡西市': [130.941767273,45.3215398866],
        '鹤岗市': [130.292472051,47.3386659037],
        '黑河市': [127.500830295,50.2506900907],
        '齐齐哈尔市': [123.97, 47.33],

        '海门市': [121.15, 31.89],
        '招远市': [120.38, 37.35],
        '乳山市': [121.52, 36.89],
        '莱西市': [120.53, 36.86],
        '胶南市': [119.97, 35.88],
        '文登市': [122.05, 37.2],
        '太仓市': [121.1, 31.45],
        '曲靖市': [103.79, 25.51],
        '瓦房店市': [121.979603, 39.627114],
        '即墨市': [120.45, 36.38],
        '玉溪市': [102.52, 24.35],
        '莱州市': [119.942327, 37.177017],
        '昆山市': [120.95, 31.39],
        '荣成市': [122.41, 37.16],
        '常熟市': [120.74, 31.64],
        '江阴市': [120.26, 31.91],
        '蓬莱市': [120.75, 37.8],
        '义乌市': [120.06, 29.32],
        '寿光市': [118.73, 36.86],
        '平度市': [119.97, 36.77],
        '章丘市': [117.53, 36.72],
        '吴江市': [120.63, 31.16],
        '石嘴山市': [106.39, 39.04],
        '胶州市': [120.03336, 36.264622],
        '张家港市': [120.555821, 31.875428],
        '宜兴市': [119.82, 31.36],
        '金坛市': [119.56, 31.74],
        '句容市': [119.16, 31.95],
        '包头市': [110, 40.58],
        '溧阳市': [119.48, 31.43],
        '库尔勒市': [86.06, 41.68],
        '临安市': [119.72, 30.23],
        '富阳市': [119.95, 30.07],
        '诸暨市': [120.23, 29.71]
    };

    var convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[data[i].name];
            if (geoCoord) {
                res.push({
                    name: data[i].name,
                    value: geoCoord.concat(data[i].value)
                });
            }
        }
        return res;
    };

    var option5 = {
        title: {
            text: '案件地域分布',
            left: 20,
            top: 10,
            textStyle: {
                fontSize: 16
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: function (param) {
                return '案件数<br/>' + param.data.name + ' : ' + param.data.value[2]
            }
        },
        toolbox: {
            feature: {
                // dataZoom: {
                //     yAxisIndex: 'none'
                // },
                myTool1: {
                    show: true,
                    title: '放大',
                    icon: 'image://${ctx}/img/icon-big.png',
                    onclick: function (){
                        toBig()
                    }
                },
                myTool2: {
                    show: true,
                    title: '缩小',
                    icon: 'image://${ctx}/img/icon-small.png',
                    onclick: function (){
                        toSmall()
                    }
                },
                myTool3: {
                    show: true,
                    title: '还原',
                    icon: 'image://${ctx}/img/icon-restore.png',
                    onclick: function () {
                        toRestore()
                    }
                }
            },
            right: 20
        },
        bmap: {
            center: [104.114129, 37.550339],
            zoom: 5,
            roam: 'move',
            mapStyle: {
                styleJson: [{
                    'featureType': 'water',
                    'elementType': 'all',
                    'stylers': {
                        'color': '#d1d1d1'
                    }
                }, {
                    'featureType': 'land',
                    'elementType': 'all',
                    'stylers': {
                        'color': '#f3f3f3'
                    }
                }, {
                    'featureType': 'railway',
                    'elementType': 'all',
                    'stylers': {
                        'visibility': 'off'
                    }
                }, {
                    'featureType': 'highway',
                    'elementType': 'all',
                    'stylers': {
                        'color': '#fdfdfd'
                    }
                }, {
                    'featureType': 'highway',
                    'elementType': 'labels',
                    'stylers': {
                        'visibility': 'off'
                    }
                }, {
                    'featureType': 'arterial',
                    'elementType': 'geometry',
                    'stylers': {
                        'color': '#fefefe'
                    }
                }, {
                    'featureType': 'arterial',
                    'elementType': 'geometry.fill',
                    'stylers': {
                        'color': '#fefefe'
                    }
                }, {
                    'featureType': 'poi',
                    'elementType': 'all',
                    'stylers': {
                        'visibility': 'off'
                    }
                }, {
                    'featureType': 'green',
                    'elementType': 'all',
                    'stylers': {
                        'visibility': 'off'
                    }
                }, {
                    'featureType': 'subway',
                    'elementType': 'all',
                    'stylers': {
                        'visibility': 'off'
                    }
                }, {
                    'featureType': 'manmade',
                    'elementType': 'all',
                    'stylers': {
                        'color': '#d1d1d1'
                    }
                }, {
                    'featureType': 'local',
                    'elementType': 'all',
                    'stylers': {
                        'color': '#d1d1d1'
                    }
                }, {
                    'featureType': 'arterial',
                    'elementType': 'labels',
                    'stylers': {
                        'visibility': 'off'
                    }
                }, {
                    'featureType': 'boundary',
                    'elementType': 'all',
                    'stylers': {
                        'color': '#fefefe'
                    }
                }, {
                    'featureType': 'building',
                    'elementType': 'all',
                    'stylers': {
                        'color': '#d1d1d1'
                    }
                }, {
                    'featureType': 'label',
                    'elementType': 'labels.text.fill',
                    'stylers': {
                        'color': '#999999'
                    }
                },{
                    "featureType": "city",
                    "elementType": "labels.icon",
                    "stylers": {
                        "visibility": "off"
                    }
                }]
            }
        },
        series: [{
            name: '案件数',
            type: 'scatter',
            coordinateSystem: 'bmap',
            data: convertData(data),
            symbolSize: function (val) {
                return val[2] / 10;
            },
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: true
                },
                emphasis: {
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    color: '#c23531'
                }
            }
        },
            {
                name: '案件数',
                type: 'effectScatter',
                coordinateSystem: 'bmap',
                data: convertData(data.sort(function (a, b) {
                    return b.value - a.value;
                }).slice(0, 6)),
                symbolSize: function (val) {
                    return val[2] / 10;
                },
                showEffectOn: 'render',
                rippleEffect: {
                    brushType: 'stroke'
                },
                hoverAnimation: true,
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#c23531',
                        shadowBlur: 10,
                        shadowColor: '#333'
                    }
                },
                zlevel: 1
            }
        ]
    };

    var option6 = {
        // title: {
        //     text: optionData6.title.text,
        //     textStyle: {
        //         fontSize: 16
        //     }
        // },
        toolbox: {
            feature: {
                restore: {},
                saveAsImage: {
                    pixelRatio: 2
                }
            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            },
            formatter: "案件单价：{b} <br/>案件数量：{c}"
        },
        grid: {
            bottom: 90
        },
        dataZoom: [{
            type: 'inside'
        }, {
            type: 'slider'
        }],
        xAxis: {
            data: optionData6.xAxis.data,
            silent: false,
            splitLine: {
                show: false
            },
            splitArea: {
                show: false
            },
            triggerEvent:true
        },
        yAxis: {
            splitArea: {
                show: false
            },
            type: 'value',
            max: function (value) {
                return Math.ceil(value.max * 1.1);
            }
        },
        series: [{
            type: 'bar',
            data: optionData6.series.data,
            large: true
        }]
    };
    var option7 = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            },
            right: 20
        },
        series: [{
            name: '',
            type: 'pie',
            radius: '55%',
            center: ['50%', '50%'],
            data: optionData7.series.data,
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }]
    };


    var myChart1 = echarts.init(document.getElementById('charts1'));
    var myChart2 = echarts.init(document.getElementById('charts2'));
    var myChart22 = echarts.init(document.getElementById('charts22'));
    var myChartSource = echarts.init(document.getElementById('myChartSource'));
    var myChart3 = echarts.init(document.getElementById('charts3'));
    var myChart4 = echarts.init(document.getElementById('charts4'));
    var myChart5 = echarts.init(document.getElementById('charts5'));
    var myChart6 = echarts.init(document.getElementById('charts6'));
    var myChart7 = echarts.init(document.getElementById('charts7'));

    myChart6.on('click', function (e) {
        var params = {
            searchType: $("#searchType").val(),
            startTime: $("#startTime").val(),
            endTime: $("#endTime").val(),
            entrustOrgId:$("#entrustOrgId").val(),
            dataType:"entrust",
            dataItem: 'dataItem',
            itemType: 'price',
            itemValue: e.name
        };
        addCaseItem(params)
    });

    $(window).resize(function () { //这是能够让图表自适应的代码
        myChart1.resize();
        myChart2.resize();
        myChart22.resize();
        myChartSource.resize();
        myChart3.resize();
        myChart4.resize();
        myChart5.resize();
        myChart6.resize();
        myChart7.resize();

    });
    // myChart1.setOption(option1);
    // myChart2.setOption(option2);
    // myChart3.setOption(option3);
    // myChart4.setOption(option4);
    // myChart5.setOption(option5);
    // myChart6.setOption(option6);
    // myChart7.setOption(option7);
    // myChart8.setOption(option8);


    /*---------报表下载-----------*/
    function download() {
//        $('#downfile').show()

        downFile()
    }

    //base64转blob
    function base64ToBlob(code) {
        let parts = code.split(';base64,');
        let contentType = parts[0].split(':')[1];
        let raw = window.atob(parts[1]);
        let rawLength = raw.length;

        let uInt8Array = new Uint8Array(rawLength);

        for (let i = 0; i < rawLength; ++i) {
            uInt8Array[i] = raw.charCodeAt(i);
        }
        return new Blob([uInt8Array], {
            type: contentType
        });
    }

    function _fixType(type) {
        type = type.toLowerCase().replace(/jpg/i, 'jpeg');
        let r = type.match(/png|jpeg|bmp|gif/)[0];
        return 'image/' + r;
    };

    var yazi = function (e) {
        e.preventDefault();
    };

    function stop() {
        document.body.style.overflow = 'hidden';
        document.addEventListener("touchmove", yazi, false); //禁止页面滑动
    }
    /***取消滑动限制***/
    function move() {
        document.body.style.overflow = ''; //出现滚动条
        document.removeEventListener("touchmove", yazi, false);
    }




    var option31 = {
        title: {
            text: '关键指标雷达图',
            textStyle: {
                fontSize: 18,
                color: '#2ABAFC',
            },
            padding: [5, 20],
        },
        legend: {
            data: ['当前保司值', '平均保司值'],
            bottom: 0,
            textStyle: {
                color: '#fff',
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        radar: {
            center: ['50%', '50%'],
            radius: '60%',
            indicator: [],
            shape: 'circle',
            splitNumber: 5,
            name: {
                textStyle: {
                    color: '#fff',
                    fontSize: 12
                }
            },
            splitLine: {
                lineStyle: {
                    color: [
                        'rgba(64, 160, 210, 0.2)', 'rgba(64, 160, 210, 0.3)',
                        'rgba(64, 160, 210, 0.4)', 'rgba(64, 160, 210, 0.6)',
                        'rgba(64, 160, 210, 0.8)', 'rgba(64, 160, 210, 1)'
                    ].reverse()
                }
            },
            splitArea: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(64, 160, 210, 0.5)'
                }
            }
        },
        series: [{
            type: 'radar',
            lineStyle: {
                normal: {
                    width: 1,
                    opacity: 0.5
                }
            },
            symbol: 'circle',
            data: [{
                value: [],
                name: '当前保司值',
                areaStyle: {
                    normal: {
                        opacity: 0.3
                    }
                },
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1,
                                [{
                                    offset: 0,
                                    color: '#02C3FF'
                                }, {
                                    offset: 1,
                                    color: '#1159FF'
                                }], false)
                    }
                },
                lineStyle: {
                    normal: {
                        width: '2.5'
                    }
                },
                emphasis: {
                    label: {
                        fontSize: 16
                    }
                },
                areaStyle: {
                    normal: {
                        //右，下，左，上
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1,
                                [{
                                    offset: 0,
                                    color: '#02C3FF'
                                }, {
                                    offset: 1,
                                    color: '#1159FF'
                                }], false)
                    }
                },
            }, {
                value: [],
                name: '平均保司值',
                areaStyle: {
                    normal: {
                        opacity: 0.1,
                    }
                },
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1,
                                [{
                                    offset: 0,
                                    color: '#FDDB54'
                                }, {
                                    offset: 1,
                                    color: '#F08F39'
                                }], false)
                    }
                },
                lineStyle: {
                    normal: {
                        width: '1'
                    }
                },
                emphasis: {
                    label: {
                        // show: true,
                        fontSize: 16
                    }
                },
                areaStyle: {
                    normal: {
                        //右，下，左，上
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1,
                                [{
                                    offset: 0,
                                    color: '#FDDB54'
                                }, {
                                    offset: 1,
                                    color: '#F08F39'
                                }], false)
                    }
                },
            }]
        }]
    };

    function downFile() {
        $('.dalogs .d-btn.download').css('pointer-events','none')
        $('.downfile-loading').show()

        // 获取时间年、月、季度
        var time =  $('.d-value select').val();
        var arrT  = time.split('-')
        var year = arrT[0];
        var keyValue = arrT[1];

        var headerTitle = ''
        var subtitle = $(".dalogs select option:selected").text()
        var info1 = ''
        var params = {
            "dataType":"entrust",
            "startTime":'',
            "endTime": '',
            "entrustOrgId": $("#entrustOrgId").val(),
            "year": year,
            "keyValue": keyValue
        }
        if ($('.chartsType .active').attr('data-value') == 'season'){
            headerTitle = '季度报表'
            info1 = '看看这个季度在乐凡的数据如何'
            params.searchType='quarter';
        } else if ($('.chartsType .active').attr('data-value') == 'month'){
            headerTitle = '月度报表'
            info1 = '看看这个月在乐凡的数据如何'
            params.searchType='month';
        }


        $('.downfile .header-title').text(headerTitle)
        $('.downfile .header-subtitle').text(subtitle)
        $('.downfile .info1').text(info1)

        initBas_d(params)

        /*-------图表------------*/

        var option31 = {
            title: {
                text: '关键指标雷达图',
                textStyle: {
                    fontSize: 18,
                    color: '#2ABAFC',
                },
                padding: [5, 20],
            },
            legend: {
                data: ['当前保司值', '平均保司值'],
                bottom: 0,
                textStyle: {
                    color: '#fff',
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            radar: {
                center: ['50%', '50%'],
                radius: '60%',
                indicator: [],
                shape: 'circle',
                splitNumber: 5,
                name: {
                    textStyle: {
                        color: '#fff',
                        fontSize: 12
                    }
                },
                splitLine: {
                    lineStyle: {
                        color: [
                            'rgba(64, 160, 210, 0.2)', 'rgba(64, 160, 210, 0.3)',
                            'rgba(64, 160, 210, 0.4)', 'rgba(64, 160, 210, 0.6)',
                            'rgba(64, 160, 210, 0.8)', 'rgba(64, 160, 210, 1)'
                        ].reverse()
                    }
                },
                splitArea: {
                    show: false
                },
                axisLine: {
                    lineStyle: {
                        color: 'rgba(64, 160, 210, 0.5)'
                    }
                }
            },
            series: [{
                type: 'radar',
                lineStyle: {
                    normal: {
                        width: 1,
                        opacity: 0.5
                    }
                },
                symbol: 'circle',
                data: [{
                    value: [],
                    name: '当前保司值',
                    areaStyle: {
                        normal: {
                            opacity: 0.3
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1,
                                    [{
                                        offset: 0,
                                        color: '#02C3FF'
                                    }, {
                                        offset: 1,
                                        color: '#1159FF'
                                    }], false)
                        }
                    },
                    lineStyle: {
                        normal: {
                            width: '2.5'
                        }
                    },
                    emphasis: {
                        label: {
                            fontSize: 16
                        }
                    },
                    areaStyle: {
                        normal: {
                            //右，下，左，上
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1,
                                    [{
                                        offset: 0,
                                        color: '#02C3FF'
                                    }, {
                                        offset: 1,
                                        color: '#1159FF'
                                    }], false)
                        }
                    },
                }, {
                    value: [],
                    name: '平均保司值',
                    areaStyle: {
                        normal: {
                            opacity: 0.1,
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1,
                                    [{
                                        offset: 0,
                                        color: '#FDDB54'
                                    }, {
                                        offset: 1,
                                        color: '#F08F39'
                                    }], false)
                        }
                    },
                    lineStyle: {
                        normal: {
                            width: '1'
                        }
                    },
                    emphasis: {
                        label: {
                            // show: true,
                            fontSize: 16
                        }
                    },
                    areaStyle: {
                        normal: {
                            //右，下，左，上
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1,
                                    [{
                                        offset: 0,
                                        color: '#FDDB54'
                                    }, {
                                        offset: 1,
                                        color: '#F08F39'
                                    }], false)
                        }
                    },
                }]
            }]
        };
        var option32 = {
            legend: {
                data: ['新增委托', '保司终审通过'],
                bottom: 20,
                textStyle: {
                    color: '#fff',
                    fontSize: 18
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '20%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: ['09.01', '09.02', '09.03', '09.04', '09.05', '09.06', '09.07', '09.08', '09.09', '09.10',
                    '09.11', '09.12', '09.13', '09.14', '09.15', '09.16', '09.17', '09.18', '09.19'
                ],
                axisLabel: {
                    textStyle: {
                        color: '#52FFFF',
                        fontSize: 12
                    },
                },
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: 'rgba(18, 38, 57, 1)'
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: '#52FFFF'
                    }
                }
            },
            yAxis: {
                type: 'value',
                axisLabel: {
                    textStyle: {
                        color: '#52FFFF',
                        fontSize: 12
                    },
                },
                splitLine: {
                    lineStyle: {
                        color: 'rgba(18, 38, 57, 1)'
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: '#52FFFF'
                    }
                }
            },
            series: [{
                name: '新增委托',
                type: 'line',
                data: [120, 132, 101, 134, 90, 230, 200, 120, 132, 101, 134, 340, 350, 400],
                smooth: true,
                color: 'rgba(241,143,53, 1)',
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(241,143,53, 0.9)'
                        }, {
                            offset: 0.5,
                            color: 'rgba(241,143,53, 0)'
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                        shadowBlur: 10
                    }
                },
                symbol: 'circle',
            },
                {
                    name: '保司终审通过',
                    type: 'line',
                    data: [220, 182, 191, 234, 290, 330, 300, 220, 182, 191, 234, 290, 330, 300],
                    smooth: true,
                    color: 'rgba(54,135,251, 1)',
                    areaStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: 'rgba(54,135,251, 0.9)'
                            }, {
                                offset: 0.5,
                                color: 'rgba(54,135,251, 0.3)'
                            }], false),
                            shadowColor: 'rgba(0, 0, 0, 0.1)',
                            shadowBlur: 10
                        }
                    },
                    symbol: 'circle',
                },
            ]
        };

        var option33 = {
            roseType: 'radius',
            series: [{
                name: '',
                type: 'pie',
                selectedMode: 'single',
                radius: '55%',
                center: ['50%', '50%'],
                label: {
                    normal: {
                        show: true,
                        // color: '#52FFFF',
                        fontSize: 14,
                        formatter: '{b} \n{c} ({d}%)',
                    },
                },
                labelLine: {
                    normal: {
                        show: true,
                        // lineStyle: {
                        //     color: '#52FFFF',
                        // }
                    }
                },
                data: [
                    {
                        value: 335,
                        name: '任务类型1',
                    },
                    {
                        value: 679,
                        name: '任务类型2'
                    },
                    {
                        value: 1548,
                        name: '任务类型3'
                    },
                    {
                        value: 748,
                        name: '任务类型4'
                    },
                    {
                        value: 808,
                        name: '任务类型5'
                    },
                    {
                        value: 1348,
                        name: '任务类型6'
                    },
                    {
                        value: 1948,
                        name: '任务类型7'
                    },
                    {
                        value: 1548,
                        name: '任务类型8'
                    },
                    {
                        value: 1548,
                        name: '任务类型9'
                    },
                    {
                        value: 148,
                        name: '任务类型10'
                    },
                    {
                        value: 1248,
                        name: '任务类型11'
                    },
                    {
                        value: 1548,
                        name: '任务类型12'
                    }
                ].sort(function (a, b) {
                            return a.value - b.value
                        }),
            }],
            color: [
                "#dd6b66",
                "#759aa0",
                "#e69d87",
                "#8dc1a9",
                "#ea7e53",
                "#eedd78",
                "#73a373",
                "#73b9bc",
                "#7289ab",
                "#91ca8c",
                "#f49f42"
            ],
        };

        var option34 = {
            series: [{
                name: '',
                type: 'pie',
                radius: '55%',
                center: ['50%', '50%'],
                data: [{
                    value: 535,
                    name: '单区域'
                },
                    {
                        value: 510,
                        name: '二区域'
                    },
                    {
                        value: 634,
                        name: '三区域'
                    }
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                },
                label: {
                    normal: {
                        show: true,
                        // color: '#52FFFF',
                        fontSize: 15,
                        formatter: '{b} \n {c} ({d}%)',
                    },
                },
                labelLine: {
                    normal: {
                        show: true,
                        // lineStyle: {
                        //     color: '#52FFFF',
                        // }
                    }
                },
            }],
            color: [
                "#dd6b66",
                "#759aa0",
                "#e69d87",
                "#8dc1a9",
                "#ea7e53",
                "#eedd78",
                "#73a373",
                "#73b9bc",
                "#7289ab",
                "#91ca8c",
                "#f49f42"
            ],
        };

        var geoCoordMap = {
            '河北省': [114.48, 38.03],
            '辽宁省': [123.38, 41.8],
            '黑龙江省': [126.63, 45.75],
            '浙江省': [120.19, 30.26],
            '福建省': [119.3, 26.08],
            '山东省': [117, 36.65],
            '广东省': [113.23, 23.16],
            '湖北省': [114.31, 30.52],
            '四川省': [104.06, 30.67],
            '云南省': [102.73, 25.04],
            '甘肃省': [103.73, 36.03],
            '广西壮族自治区': [108.33, 22.84],
            '宁夏回族自治区': [106.27, 38.47],
            '山西省': [112.53, 37.87],
            '吉林省': [125.35, 43.88],
            '江苏省': [118.78, 32.04],
            '安徽省': [117.27, 31.86],
            '江西省': [115.89, 28.68],
            '河南省': [113.65, 34.76],
            '湖南省': [113, 28.21],
            '海南省': [110.35, 20.02],
            '贵州省': [106.71, 26.57],
            '陕西省': [108.95, 34.27],
            '青海省': [101.74, 36.56],
            '内蒙古自治区': [111.65, 40.82],
            '西藏自治区': [91.11, 29.97],
            '新疆维吾尔自治区': [87.68, 43.77],

            '上海市': [121.48, 31.22],
            '北京市': [116.46, 39.92],
            '重庆市': [106.54, 29.59],
            '天津市': [117.2, 39.13],

            '海门': [121.15, 31.89],
            '鄂尔多斯': [109.781327, 39.608266],
            '招远': [120.38, 37.35],
            '舟山': [122.207216, 29.985295],
            '齐齐哈尔': [123.97, 47.33],
            '盐城': [120.13, 33.38],
            '赤峰': [118.87, 42.28],
            '青岛': [120.33, 36.07],
            '乳山': [121.52, 36.89],
            '金昌': [102.188043, 38.520089],
            '泉州': [118.58, 24.93],
            '莱西': [120.53, 36.86],
            '日照': [119.46, 35.42],
            '胶南': [119.97, 35.88],
            '南通': [121.05, 32.08],
            '拉萨': [91.11, 29.97],
            '云浮': [112.02, 22.93],
            '梅州': [116.1, 24.55],
            '文登': [122.05, 37.2],
            '上海': [121.48, 31.22],
            '攀枝花': [101.718637, 26.582347],
            '威海': [122.1, 37.5],
            '承德': [117.93, 40.97],
            '厦门': [118.1, 24.46],
            '汕尾': [115.375279, 22.786211],
            '潮州': [116.63, 23.68],
            '丹东': [124.37, 40.13],
            '太仓': [121.1, 31.45],
            '曲靖': [103.79, 25.51],
            '烟台': [121.39, 37.52],
            '福州': [119.3, 26.08],
            '瓦房店': [121.979603, 39.627114],
            '即墨': [120.45, 36.38],
            '抚顺': [123.97, 41.97],
            '玉溪': [102.52, 24.35],
            '张家口': [114.87, 40.82],
            '阳泉': [113.57, 37.85],
            '莱州': [119.942327, 37.177017],
            '湖州': [120.1, 30.86],
            '汕头': [116.69, 23.39],
            '昆山': [120.95, 31.39],
            '宁波': [121.56, 29.86],
            '湛江': [110.359377, 21.270708],
            '揭阳': [116.35, 23.55],
            '荣成': [122.41, 37.16],
            '连云港': [119.16, 34.59],
            '葫芦岛': [120.836932, 40.711052],
            '常熟': [120.74, 31.64],
            '东莞': [113.75, 23.04],
            '河源': [114.68, 23.73],
            '淮安': [119.15, 33.5],
            '泰州': [119.9, 32.49],
            '南宁': [108.33, 22.84],
            '营口': [122.18, 40.65],
            '惠州': [114.4, 23.09],
            '江阴': [120.26, 31.91],
            '蓬莱': [120.75, 37.8],
            '韶关': [113.62, 24.84],
            '嘉峪关': [98.289152, 39.77313],
            '广州': [113.23, 23.16],
            '延安': [109.47, 36.6],
            '太原': [112.53, 37.87],
            '清远': [113.01, 23.7],
            '中山': [113.38, 22.52],
            '昆明': [102.73, 25.04],
            '寿光': [118.73, 36.86],
            '盘锦': [122.070714, 41.119997],
            '长治': [113.08, 36.18],
            '深圳': [114.07, 22.62],
            '珠海': [113.52, 22.3],
            '宿迁': [118.3, 33.96],
            '咸阳': [108.72, 34.36],
            '铜川': [109.11, 35.09],
            '平度': [119.97, 36.77],
            '佛山': [113.11, 23.05],
            '海口': [110.35, 20.02],
            '江门': [113.06, 22.61],
            '章丘': [117.53, 36.72],
            '肇庆': [112.44, 23.05],
            '大连': [121.62, 38.92],
            '临汾': [111.5, 36.08],
            '吴江': [120.63, 31.16],
            '石嘴山': [106.39, 39.04],
            '沈阳': [123.38, 41.8],
            '苏州': [120.62, 31.32],
            '茂名': [110.88, 21.68],
            '嘉兴': [120.76, 30.77],
            '长春': [125.35, 43.88],
            '胶州': [120.03336, 36.264622],
            '银川': [106.27, 38.47],
            '张家港': [120.555821, 31.875428],
            '三门峡': [111.19, 34.76],
            '锦州': [121.15, 41.13],
            '南昌': [115.89, 28.68],
            '柳州': [109.4, 24.33],
            '三亚': [109.511909, 18.252847],
            '自贡': [104.778442, 29.33903],
            '吉林': [126.57, 43.87],
            '阳江': [111.95, 21.85],
            '泸州': [105.39, 28.91],
            '西宁': [101.74, 36.56],
            '宜宾': [104.56, 29.77],
            '呼和浩特': [111.65, 40.82],
            '成都': [104.06, 30.67],
            '大同': [113.3, 40.12],
            '镇江': [119.44, 32.2],
            '桂林': [110.28, 25.29],
            '张家界': [110.479191, 29.117096],
            '宜兴': [119.82, 31.36],
            '北海': [109.12, 21.49],
            '西安': [108.95, 34.27],
            '金坛': [119.56, 31.74],
            '东营': [118.49, 37.46],
            '牡丹江': [129.58, 44.6],
            '遵义': [106.9, 27.7],
            '绍兴': [120.58, 30.01],
            '扬州': [119.42, 32.39],
            '常州': [119.95, 31.79],
            '潍坊': [119.1, 36.62],
            '重庆': [106.54, 29.59],
            '台州': [121.420757, 28.656386],
            '南京': [118.78, 32.04],
            '滨州': [118.03, 37.36],
            '贵阳': [106.71, 26.57],
            '无锡': [120.29, 31.59],
            '本溪': [123.73, 41.3],
            '克拉玛依': [84.77, 45.59],
            '渭南': [109.5, 34.52],
            '马鞍山': [118.48, 31.56],
            '宝鸡': [107.15, 34.38],
            '焦作': [113.21, 35.24],
            '句容': [119.16, 31.95],
            '北京': [116.46, 39.92],
            '徐州': [117.2, 34.26],
            '衡水': [115.72, 37.72],
            '包头': [110, 40.58],
            '绵阳': [104.73, 31.48],
            '乌鲁木齐': [87.68, 43.77],
            '枣庄': [117.57, 34.86],
            '杭州': [120.19, 30.26],
            '淄博': [118.05, 36.78],
            '鞍山': [122.85, 41.12],
            '溧阳': [119.48, 31.43],
            '库尔勒': [86.06, 41.68],
            '安阳': [114.35, 36.1],
            '开封': [114.35, 34.79],
            '济南': [117, 36.65],
            '德阳': [104.37, 31.13],
            '温州': [120.65, 28.01],
            '九江': [115.97, 29.71],
            '邯郸': [114.47, 36.6],
            '临安': [119.72, 30.23],
            '兰州': [103.73, 36.03],
            '沧州': [116.83, 38.33],
            '临沂': [118.35, 35.05],
            '南充': [106.110698, 30.837793],
            '天津': [117.2, 39.13],
            '富阳': [119.95, 30.07],
            '泰安': [117.13, 36.18],
            '诸暨': [120.23, 29.71],
            '郑州': [113.65, 34.76],
            '哈尔滨': [126.63, 45.75],
            '聊城': [115.97, 36.45],
            '芜湖': [118.38, 31.33],
            '唐山': [118.02, 39.63],
            '平顶山': [113.29, 33.75],
            '邢台': [114.48, 37.05],
            '德州': [116.29, 37.45],
            '济宁': [116.59, 35.38],
            '荆州': [112.239741, 30.335165],
            '宜昌': [111.3, 30.7],
            '义乌': [120.06, 29.32],
            '丽水': [119.92, 28.45],
            '洛阳': [112.44, 34.7],
            '秦皇岛': [119.57, 39.95],
            '株洲': [113.16, 27.83],
            '石家庄': [114.48, 38.03],
            '莱芜': [117.67, 36.19],
            '常德': [111.69, 29.05],
            '保定': [115.48, 38.85],
            '湘潭': [112.91, 27.87],
            '金华': [119.64, 29.12],
            '岳阳': [113.09, 29.37],
            '长沙': [113, 28.21],
            '衢州': [118.88, 28.97],
            '廊坊': [116.7, 39.53],
            '菏泽': [115.480656, 35.23375],
            '合肥': [117.27, 31.86],
            '武汉': [114.31, 30.52],
            '大庆': [125.03, 46.58]
        };

        var convertData = function (data) {
            var res = [];
            for (var i = 0; i < data.length; i++) {
                var geoCoord = geoCoordMap[data[i].name];
                if (geoCoord) {
                    res.push({
                        name: data[i].name,
                        value: geoCoord.concat(data[i].value)
                    });
                }
            }
            return res;
        };

        function sortNumber(a, b) {
            return a - b
        }
        var wh = $('#charts5')
        var arrLoc = convertData(data)
        var x_s = []
        var y_s = []
        arrLoc.map(function(m){
            x_s.push(m.value[0])
            y_s.push(m.value[1])
        })
        x_s.sort(sortNumber)
        y_s.sort(sortNumber)
        var xMin = x_s[0]
        var xMax = x_s.reverse()[0]
        var yMin = y_s[0]
        var yMax = y_s.reverse()[0]
        var map = new BMap.Map("container");

        function getZoom_d(maxLng, minLng, maxLat, minLat) {
            var zoom = ["50", "100", "200", "500", "1000", "2000", "5000", "10000", "20000", "25000", "50000",
                "100000","200000", "500000", "1300000", "1600000"
            ] //级别18到3。
            var pointA = new BMap.Point(maxLng, maxLat); // 创建点坐标A
            var pointB = new BMap.Point(minLng, minLat); // 创建点坐标B
            var distance = map.getDistance(pointA, pointB).toFixed(1); //获取两点距离,保留小数点后两位
            for (var i = 0, zoomLen = zoom.length; i < zoomLen; i++) {
                if (zoom[i] - distance > 0) {
                    return 18 - i + 3 > 10 ? 10 : 18 - i + 3; //之所以会多3，是因为地图范围常常是比例尺距离的10倍以上。所以级别会增加3。
                }
            };
            return 1.2;
        }
        var getzoom = getZoom_d(xMax, xMin, yMax, yMin)
        var mapZoom = getzoom * 0.3 + 1.2

        var option35 = {
            backgroundColor: '#051b4a',
            geo: {
                map: 'china',
                center: [(xMax + xMin) / 2, (yMax + yMin) / 2],
                zoom: mapZoom,
                label: {
                    emphasis: {
                        show: false
                    }
                },
                itemStyle: {
                    normal: {
                        borderColor: 'rgba(147, 235, 248, 1)',
                        borderWidth: 1,
                        areaColor: {
                            type: 'radial',
                            x: 0.5,
                            y: 0.5,
                            r: 0.8,
                            colorStops: [{
                                offset: 0,
                                color: 'rgba(147, 235, 248, 0)' // 0% 处的颜色
                            }, {
                                offset: 1,
                                color: 'rgba(147, 235, 248, .2)' // 100% 处的颜色
                            }],
                            globalCoord: false // 缺省为 false
                        },
                        shadowColor: 'rgba(128, 217, 248, 1)',
                        // shadowColor: 'rgba(255, 255, 255, 1)',
                        shadowOffsetX: -2,
                        shadowOffsetY: 2,
                        shadowBlur: 10
                    },
                    emphasis: {
                        areaColor: '#389BB7',
                        borderWidth: 0
                    }
                }
            },
            series: [{
                name: '案件数',
                type: 'scatter',
                coordinateSystem: 'geo',
                data: convertData(data),
                symbolSize: function (val) {
                    return val[2] / 10;
                },
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: true,
                        fontSize: 16
                    },
                    emphasis: {
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#1de9b6'
                    }
                }
            },
                {
                    name: '案件数',
                    type: 'effectScatter',
                    coordinateSystem: 'geo',
                    data: convertData(data.sort(function (a, b) {
                        return b.value - a.value;
                    }).slice(0, 6)),
                    symbolSize: function (val) {
                        return val[2] / 10;
                    },
                    showEffectOn: 'render',
                    rippleEffect: {
                        brushType: 'stroke'
                    },
                    hoverAnimation: true,
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: true,
                            fontSize: 16

                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#1de9b6',
                            shadowBlur: 10,
                            shadowColor: '#333'
                        }
                    },
                    zlevel: 1
                }
            ]
            // }]

        };

        var option36 = {
            grid: {
                bottom: 50
            },
            xAxis: {
                type: 'category',
                data: [200, 221, 223, 224, 300, 303, 304, 500, 600, 200, 221, 223, 224, 300, 303, 304, 500, 600,
                    200,
                    221, 223, 224, 300, 303, 304, 500, 600, 200, 221, 223, 224, 300, 303, 304, 500, 600,
                    200,
                    221,
                    223, 224, 300, 303, 304, 500, 600, 200, 221, 223, 224, 300, 303, 304, 500, 600
                ],
                show: true,
                axisLabel: {
                    textStyle: {
                        color: '#52FFFF'
                    },
                },
                splitLine: {
                    lineStyle: {
                        color: 'rgba(82, 255, 255, 0.2)'
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: '#52FFFF'
                    }
                }
            },
            yAxis: {
                type: 'value',
                max: function (value) {
                    return Math.round(value.max * 1.1);
                },
                axisLabel: {
                    textStyle: {
                        color: '#52FFFF'
                    },
                },
                splitLine: {
                    lineStyle: {
                        color: 'rgba(82, 255, 255, 0.2)'
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: '#52FFFF'
                    }
                }
            },
            series: [{
                type: 'bar',
                data: [100, 200, 400, 200, 400, 300, 299, 700, 600, 100, 200, 400, 200, 400, 300, 299,
                    700, 600,
                    100,
                    200, 400, 200, 400, 300, 299, 700, 600, 100, 200, 400, 200, 400, 300, 299, 700,
                    600, 100,
                    200,
                    400, 200, 400, 300, 299, 700, 600, 100, 200, 400, 200, 400, 300, 299, 700, 600
                ],
                large: true,
                itemStyle: {
                    color: '#52FFFF'
                }
            }]
        };

        var option37 = {
            series: [{
                name: '',
                type: 'pie',
                radius: '55%',
                center: ['50%', '50%'],
                data: [{
                    value: 535,
                    name: '单区域'
                },
                    {
                        value: 510,
                        name: '二区域'
                    },
                    {
                        value: 634,
                        name: '三区域'
                    }
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                },
                label: {
                    normal: {
                        show: true,
                        // color: '#52FFFF',
                        fontSize: 15,
                        formatter: '{b} \n {c} ({d}%)',
                    },
                },
                labelLine: {
                    normal: {
                        show: true,
                        // lineStyle: {
                        //     color: '#52FFFF',
                        // }
                    }
                },
            }],
            // color: ['#407FFF', 'rgba(216,69,85,1)', 'rgba(175,171,60,1)',
            //     'rgba(115,154,81,1)', 'rgba(60,163,201,1)', 'rgba(91,184,248,1)', 'rgba(192,82,85,1)'
            // ]
            color: [
                "#dd6b66",
                "#759aa0",
                "#e69d87",
                "#8dc1a9",
                "#ea7e53",
                "#eedd78",
                "#73a373",
                "#73b9bc",
                "#7289ab",
                "#91ca8c",
                "#f49f42"
            ],
        };

        var myChart31 = echarts.init(document.getElementById('charts31'));
        var myChart32 = echarts.init(document.getElementById('charts32'));
        var myChart33 = echarts.init(document.getElementById('charts33'));
        var myChart34 = echarts.init(document.getElementById('charts34'));
        var myChart35 = echarts.init(document.getElementById('charts35'));
        var myChart36 = echarts.init(document.getElementById('charts36'));
        var myChart37 = echarts.init(document.getElementById('charts37'));

        function initBas_d(param){
            var url = "${ctx}/survey/report/getData";
            param["dataTable"] = 'entrustBas';
            ajaxSubmit(url,param,function(v,e,p){
                if(e.data.code==='0000'){
                    var bas = e.data.results.bas;
                    //显示日期
//                    var startTime = e.data.results.startTime;
//                    var endTime = e.data.results.endTime;
//                    $("#startTime").val(startTime);
//                    $("#endTime").val(endTime);
                    if(bas){
                        var $name = $('.downfile .title-co-name')
                        var len = bas.surveyConsignor.name.length
                        switch (len) {
                            case 11:
                                $name.css('font-size', '33px')
                                break;
                            case 12:
                                $name.css('font-size', '29px')
                                break;
                            case 13:
                                $name.css('font-size', '27px')
                                break;
                            case 14:
                                $name.css('font-size', '25px')
                                break;
                            case 15:
                                $name.css('font-size', '24px')
                                break;
                            case 16:
                                $name.css('font-size', '23px')
                                break;
                            case 17:
                                $name.css('font-size', '23px')
                                break;
                            case 18:
                                $name.css('font-size', '21px')
                                break;
                            case 19:
                                $name.css('font-size', '20px')
                                break;
                            case 20:
                                $name.css('font-size', '20px')
                                break;
                            case 21:
                                $name.css('font-size', '20px')
                                break;
                            case 22:
                                $name.css('font-size', '19px')
                                break;
                            case 23:
                                $name.css('font-size', '18px')
                                break;
                        }
                        if (len >= 23) {
                            $name.css('font-size', '18px')
                        }
                        $('.downfile .title-co-name').text(bas.surveyConsignor.name)

                        $('.downfile .keyWord .xzwt').text(bas.surveyConsignorReport.newSurveyCaseNum)
                        $('.downfile .keyWord .xzbs').text(bas.surveyConsignorReport.newCheckNum)
                        $('.downfile .keyWord .dcf').text((bas.surveyConsignorReport.surveyMoney/10000).toFixed(2))

                        var dcf = bas.surveyConsignorReport.surveyMoney;
                        var xzbs = bas.surveyConsignorReport.newCheckNum;
                        var price = 0;
                        if(xzbs){
                            price = xzbs == 0 ? 0 : (dcf / xzbs).toFixed(0);
                        }
                        $('.downfile .price .ajjj').text(price+'元')
                        $('.downfile .price .fxjj').text(bas.surveyConsignorReport.directionAvgMoney.toFixed(0)+'元')

                        $('.downfile .quality .kqycd').text(bas.surveyConsignorReport.regional.toFixed(2)+'省')
                        $('.downfile .quality .dcsx').text(bas.surveyConsignorReport.efficiency.toFixed(2)+'天')
                        $('.downfile .quality .csxzb').text((bas.surveyConsignorReport.lossEfficiencyRate * 100).toFixed(2)+'%')
                        $('.downfile .quality .yxl').text((bas.surveyConsignorReport.positiveRate * 100).toFixed(2)+'%')

                        $('#csxzb').val((bas.surveyConsignorReport.lossEfficiencyRate * 100).toFixed(2))

                    }
                    // initKeyTar_d(param)
                    initTrend_d(param)
                }else{
                    alert("加载数据异常");
                }
            });
        }

        function initKeyTar_d(param){
            var url = "${ctx}/survey/report/getData";
            param["dataTable"] = 'keyTar';
            ajaxSubmit(url,param,function(v,e,p){
                if(e.data.code==='0000'){
                    var list = e.data.results.list;
                    var entrusts = e.data.results.entrusts;//机构数量
                    if(list){
                        var maxData = [];
                        var value1 = [];
                        var value2 = [];
                        var isSun = $('.d-value .d-radio[data-value="showsun"]').hasClass('active')
                        for(var i = 0 ; i < list.length ; i ++ ){
                            var item = list[i];
                            var surveyMoney = item.surveyMoney.toFixed(2);
                            var efficiency = item.efficiency.toFixed(2);
                            var regional = item.regional.toFixed(2);
                            var positiveRate = item.positiveRate.toFixed(2);
                            var lossEfficiencyRate = item.lossEfficiencyRate.toFixed(2);
                            var average = item.average.toFixed(2);
                            var newSurveyCaseNum = item.newSurveyCaseNum.toFixed(2);
                            if(item.returnType == 1){
                                sessionStorage.setItem('isSunData1',[surveyMoney,efficiency,regional,positiveRate * 100,lossEfficiencyRate * 100,average,newSurveyCaseNum])
                                sessionStorage.setItem('noSunData1',[surveyMoney,efficiency,regional,lossEfficiencyRate * 100,average,newSurveyCaseNum])
                                if (isSun){
                                    value1.push(surveyMoney,efficiency,regional,positiveRate * 100,lossEfficiencyRate * 100,average,newSurveyCaseNum);
                                }else {
                                    value1.push(surveyMoney,efficiency,regional,lossEfficiencyRate * 100,average,newSurveyCaseNum);
                                }
                            }else if(item.returnType == 2){
                                surveyMoney = (item.surveyMoney.toFixed(2) / entrusts).toFixed(2);
                                efficiency = item.efficiency.toFixed(2);
                                regional = item.regional.toFixed(2);
                                positiveRate = item.positiveRate.toFixed(2);
                                lossEfficiencyRate = item.lossEfficiencyRate.toFixed(2);
                                average = item.average.toFixed(2);
                                newSurveyCaseNum = (item.newSurveyCaseNum.toFixed(2) / entrusts).toFixed(2);
                                if (isSun){
                                    value2.push(surveyMoney,efficiency,regional,positiveRate * 100,lossEfficiencyRate * 100,average,newSurveyCaseNum);
                                }else {
                                    value2.push(surveyMoney,efficiency,regional,lossEfficiencyRate * 100,average,newSurveyCaseNum);
                                }
                                sessionStorage.setItem('isSunData2',[surveyMoney,efficiency,regional,positiveRate * 100,lossEfficiencyRate * 100,average,newSurveyCaseNum])
                                sessionStorage.setItem('noSunData2',[surveyMoney,efficiency,regional,lossEfficiencyRate * 100,average,newSurveyCaseNum])

                            }else if(item.returnType == 3){
                                if(surveyMoney <= 0){surveyMoney = 1}
                                if(efficiency <= 0){efficiency = 1}
                                if(regional <= 0){regional = 1}
                                if(positiveRate <= 0){positiveRate = 1}
                                if(lossEfficiencyRate <= 0){lossEfficiencyRate = 1}
                                if(average <= 0){average = 1}
                                if(newSurveyCaseNum <= 0){newSurveyCaseNum = 1}
                                if (isSun){
                                    maxData.push({"name" : "营收额","max" : surveyMoney},{"name" : "调查时效","max" : efficiency},{"name" : "跨区域程度","max" : regional},
                                            {"name" : "阳性率","max" : positiveRate * 100},{"name" : "超时效占比","max" : lossEfficiencyRate * 100},{"name" : "件均","max" : average},{"name" : "新增委托","max" : newSurveyCaseNum})

                                }else {
                                    maxData.push({"name" : "营收额","max" : surveyMoney},{"name" : "调查时效","max" : efficiency},{"name" : "跨区域程度","max" : regional},
                                           {"name" : "超时效占比","max" : lossEfficiencyRate * 100},{"name" : "件均","max" : average},{"name" : "新增委托","max" : newSurveyCaseNum})

                                }
                                sessionStorage.setItem('isSunMaxData',JSON.stringify([{"name" : "营收额","max" : surveyMoney},{"name" : "调查时效","max" : efficiency},{"name" : "跨区域程度","max" : regional},
                                        {"name" : "阳性率","max" : positiveRate * 100},{"name" : "超时效占比","max" : lossEfficiencyRate * 100},{"name" : "件均","max" : average},{"name" : "新增委托","max" : newSurveyCaseNum}]))
                                sessionStorage.setItem('noSunMaxData',JSON.stringify([{"name" : "营收额","max" : surveyMoney},{"name" : "调查时效","max" : efficiency},{"name" : "跨区域程度","max" : regional},
                                    {"name" : "超时效占比","max" : lossEfficiencyRate * 100},{"name" : "件均","max" : average},{"name" : "新增委托","max" : newSurveyCaseNum}]))
}
                        }
                        option31.radar.indicator = maxData;
                        option31.series[0].data[0].value = value1;
                        option31.series[0].data[1].value = value2;
                        myChart31.hideLoading();
                        myChart31.setOption(option31);
                    }

                initTrend_d(param);
                }else{
                    alert("加载关键指标数据异常");
                }
            });
        }

        function initTrend_d(param){
            var url = "${ctx}/survey/report/getData";
            param["dataTable"] = 'trend';
            // var trendType = $('.tabbars .tabbar.active.trend22.active').attr("data-type");
            // if (trendType == 1){
            //     // searchType = "soon";
            //     param["searchType"] = "soon";
            // }
            param["trendType"] = 2

            ajaxSubmit(url,param,function(v,e,p){
                if(e.data.code==='0000'){
                    var list = e.data.results.list;
                    if(list){
                        var newData = [];
                        var newData1 = [];
                        var newData2 = [];
                        var isShow = false;
                        for(var i = 0 ; i < list.length; i ++ ){
                            newData.push(list[i].reportDateStr);
                            newData1.push(list[i].newSurveyCaseNum);
                            newData2.push(list[i].newCheckNum);
                            if(list[i].newSurveyCaseNum != 0){
                                isShow = true;
                            }
                            if(list[i].newCheckNum != 0){
                                isShow = true;
                            }
                        }
                        option32.xAxis.data = newData;
                        option32.series[0].data = newData1;
                        option32.series[1].data = newData2;
                        myChart32.hideLoading();
                        myChart32.setOption(option32);

                        if(!isShow){
                            $('.downfile .split-line-32').hide()
                            $('.downfile .bar-type-32').hide()
                            $('.downfile .cell-32').hide()
                        } else {
                            $('.downfile .split-line-32').show()
                            $('.downfile .bar-type-32').show()
                            $('.downfile .cell-32').show()
                        }
                    }

                    initTask_d(param);
                }else{
                    alert("加载案件走势数据异常");
                }
            });
        }

        function initTask_d(param){
            //加载任务
            var url = "${ctx}/survey/report/getData";
            param["dataTable"] = 'task';
            param["orgAttr"] = $("#orgAttr").val();
            ajaxSubmit(url,param,function(v,e,p){
                if(e.data.code==='0000'){
                    var list = e.data.results.list;
                    if(list){
                        var legend = [];
                        var newData = [];
                        var newDataTasks = [];
                        for(var i = 0 ; i < list.length; i ++ ){
                            legend.push(list[i].parentName);
                            newData.push({
                                name: list[i].parentName,
                                value: list[i].entrustCaseNum
                            });
                            var tasks = list[i].surveyTaskReports;
                            console.log('-----', list[i].surveyTaskReports)
                            for(var j = 0 ; j < tasks.length ; j ++ ){
                                // legend.push(tasks[j].taskName);
                                newDataTasks.push({
                                    name: tasks[j].taskName,
                                    value: tasks[j].entrustCaseNum
                                })
                            }
                        }

                        option33.series[0].data = newData;
                        myChart33.hideLoading();
                        if(list.length == 0){
                            $('.downfile .split-line-33').hide()
                            $('.downfile .bar-type-33').hide()
                            $('.downfile .cell-33').hide()
                        } else {
                            $('.downfile .split-line-33').show()
                            $('.downfile .bar-type-33').show()
                            $('.downfile .cell-33').show()
                        }
                        myChart33.setOption(option33);
                    }

                    //加载跨区域数据
                    initService_d(param);
                }else{
                    alert("加载领域数据异常");
                }
            });
        }


        function initService_d(param){
            var url = "${ctx}/survey/report/getData";
            param["dataTable"] = 'service';
            ajaxSubmit(url,param,function(v,e,p){
                if(e.data.code==='0000'){
                    var list = e.data.results.list;
                    var total = e.data.results.total;
                    if(list){
                        var newData = [];
                        for(var i = 0 ; i < list.length; i ++ ){
                            if(list[i].entrustCaseNum == null || list[i].entrustCaseNum == 0){
                                continue;
                            }
                            newData.push({
                                name: list[i].serviceTypeName,
                                value: list[i].entrustCaseNum
                            });
                        }
                        option34.series[0].data = newData;
                        myChart34.hideLoading();
                        if(newData.length == 0){
                            $('.downfile .split-line-34').hide()
                            $('.downfile .bar-type-34').hide()
                            $('.downfile .cell-34').hide()
                        } else {
                            $('.downfile .split-line-34').show()
                            $('.downfile .bar-type-34').show()
                            $('.downfile .cell-34').show()
                        }
                        myChart34.setOption(option34);
                    }
                    initArea_d(param)
                }else{
                    alert("加载数据异常");
                }
            });
        }

        function initArea_d(param){
            var url = "${ctx}/survey/report/getData";
            param["dataTable"] = 'area';
            ajaxSubmit(url,param,function(v,e,p){
                if(e.data.code==='0000'){
                    var list = e.data.results.list;
                    var total = e.data.results.total;
                    var maxNum = e.data.results.maxNum == 0 ? 1 : e.data.results.maxNum;
                    if(list){
                        var html = "";
                        var newData = [];

                        for(var i = 0 ; i < list.length; i ++ ){
                            newData.push({
                                // name: list[i].areaName2.substr(0,list[i].areaName2.length - 1),
                                name: list[i].areaName, //省名称
                                value: list[i].num
                            });
                            // html += "<div class='li'><div class='name'>"+list[i].areaName+"</div><div class='num-text'>"+list[i].num+"</div><div class='num-line'><div class='line-icon' style='width: "+(list[i].num/total).toFixed(2) + "%'></div></div></div>";
                            html += "<div class='li'><div class='name'>"+list[i].areaName+"</div><div class='num-text'>"+list[i].num+"</div><div class='num-line'><div class='line-icon' style='width: "+(list[i].num/maxNum * 100 > 1 ? list[i].num/maxNum * 100 : 1).toFixed(2) + "%'></div></div></div>";
                        }
                        var pageTotal = Math.ceil(list.length / 13);
                        $("#span_page_total").text(pageTotal);
                        $("#div_page_total").attr("data-page",pageTotal);
                        $("#div_page_total2").attr("data-page",pageTotal);

                        option35.series[0].symbolSize = function(val){
                            return val[2] / (maxNum / 10);
                        };
                        option35.series[0].data = convertData(newData);

                        option35.series[1].symbolSize = function(val){
                            return val[2] / (maxNum / 20);
                        };
                        option35.series[1].data = convertData(newData.sort(function (a, b) {
                            return b.value - a.value;
                        }).slice(0, 6));
                        var arrLoc = convertData(newData)
                        var x_s = []
                        var y_s = []
                        arrLoc.map(function (m) {   x_s.push(m.value[0])
                            y_s.push(m.value[1]) })
                        x_s.sort(sortNumber)
                        y_s.sort(sortNumber)
                        var xMin = Number(x_s[0])
                        var xMax = Number(x_s.reverse()[0])
                        var yMin = Number(y_s[0])
                        var yMax = Number(y_s.reverse()[0])
                        option35.geo.center = newData.length > 0 ?[(xMax + xMin) / 2, (yMax + yMin) / 2] :[104.114129, 37.550339]
                        option35.geo.zoom = getZoom_d(xMax, xMin, yMax, yMin)* 0.3 + 1.2;
                        option35.series[0].data = newData.length > 6 ? convertData(newData).slice(6) : [];
                        myChart35.hideLoading();
                        myChart35.setOption(option35);
                        $(".list .list-content").html(html);

                        if(list.length==0){
                            $('.downfile .split-line-35').hide()
                            $('.downfile .bar-type2-35').hide()
                            $('.downfile .cell-35').hide()
                        } else {
                            $('.downfile .split-line-35').show()
                            $('.downfile .bar-type2-35').show()
                            $('.downfile .cell-35').show()
                        }
                    }
                    initPrice_d(param)
                }else{
                    alert("加载数据异常");
                }
            });
        }

        function initPrice_d(param){
            var url = "${ctx}/survey/report/getData";
            param["dataTable"] = 'price';
            ajaxSubmit(url,param,function(v,e,p){
                if(e.data.code==='0000'){
                    var list = e.data.results.list;
                    if(list){
                        var newXData = [];
                        var newYData = [];
                        for(var i = 0 ; i < list.length ; i++){
                            newXData.push(list[i].pirce)
                            newYData.push(list[i].num)
                        }
                        option36.xAxis.data = newXData;
                        option36.series[0].data = newYData;
                        myChart36.hideLoading();
                        if(list.length == 0){
                            $('.downfile .bar-type-36').hide()
                            $('.downfile .cell-36-01').hide()
                            $('.downfile .cell-36-02').hide()
                        } else {
                            $('.downfile .bar-type-36').show()
                            $('.downfile .cell-36-01').show()
                            $('.downfile .cell-36-02').show()
                        }
                        myChart36.setOption(option36);
                    }

                    initCross_d(param);
                }else{
                    alert("加载数据异常");
                }
            });
        }

        function initCross_d(param){
            var url = "${ctx}/survey/report/getData";
            param["dataTable"] = 'cross';
            ajaxSubmit(url,param,function(v,e,p){
                if(e.data.code==='0000'){
                    var list = e.data.results.list;
                    var total = e.data.results.total;
                    if(list){
                        var newData = [];
                        for(var i = 0 ; i < list.length; i ++ ){
                            newData.push({
                                name: list[i].areaLevelName,
                                value: list[i].entrustCaseNum
                            });
                        }
                        option37.series[0].data = newData;
                        myChart37.hideLoading();
                        if(list.length == 0){
                            $('.downfile .bar-type-37').hide()
                            $('.downfile .cell-37').hide()
                        } else {
                            $('.downfile .bar-type-37').show()
                            $('.downfile .cell-37').show()
                        }
                        myChart37.setOption(option37);
                        $('.downfile-loading').hide()
//                        $('.dalogs-downfile').css({
//                            'pointer-events':'initial',
//                        })
                    }
                }else{
                    alert("加载跨区域数据异常");
                }
                $('.dalogs .d-btn.download').css('pointer-events','auto')
            });
        }

    }

    function up(){
        setTimeout(function () {
            let oContainer = document.querySelector("#canvasContainer"); //装canvas的容器
            html2canvas(document.getElementById('downfile'), {
                background: "#fff",
                allowTaint: 'true',
                useCORS: true,
                taintTest: false,
                x: 0,
                y: 0,
                scrollX: 0,
                scrollY: 0,
                foreignObjectRendering: true,
                clone: true
            }).then(function(canvas) {
                oContainer.appendChild(canvas);
                //延迟执行确保万无一失，玄学
                setTimeout(function () {
                    var type = 'png'
                    var oCanvas = oContainer.getElementsByTagName("canvas")[0];
                    /*--------------base64------------------*/
                    var imgData = oCanvas.toDataURL(type); //canvas转换为图片
                    // // 加工image data，替换mime type，方便以后唤起浏览器下载
                    imgData = imgData.replace(_fixType(type), 'image/octet-stream');
                    // fileDownload(imgData);
                    /*---------------blob-------------------*/
                    let aLink = document.createElement('a');
                    let blob = base64ToBlob(imgData)
                    let evt = document.createEvent("HTMLEvents");
                    evt.initEvent("click", true,
                            true); //initEvent 不加后两个参数在FF下会报错  事件类型，是否冒泡，是否阻止浏览器的默认行为
                    aLink.download =  $('.downfile .title-co-name').text() +  $('.downfile .header-subtitle').text() + $('.downfile .header-title').text() + '.png';
                    aLink.href = URL.createObjectURL(blob);
                    aLink.dispatchEvent(new MouseEvent('click', {
                        bubbles: true,
                        cancelable: true,
                        view: window
                    })); //兼容火狐
//                    move()
                    $('#canvasContainer').empty()
                    $('.downfile').attr('style', '')
                    $('.download').attr('style', '')
                    $('.loading-bar').hide()
                    $('.dalogs').hide()
                    $('.dalogs-contain').show()
                }, 0)
            })
        }, 2000)
    }


    function toBig(){
        bsZoom = option5.bmap.zoom
        bsZoom += 1
        option5.bmap.zoom = bsZoom
        myChart5.setOption(option5);
    }
    function toSmall(){
        bsZoom = option5.bmap.zoom
        if (bsZoom < 5){
            alert('已至最小缩放比例')
            return;
        }
        bsZoom -= 1
        option5.bmap.zoom = bsZoom
        myChart5.setOption(option5);
    }
    function toRestore() {
        option5.bmap.zoom = initZoom
        myChart5.setOption(option5);
    }


</script>
</body>

</html>