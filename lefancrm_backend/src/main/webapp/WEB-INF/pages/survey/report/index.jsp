<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/search-select2.css?v=${resourceVersion}">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">

    <style>
        * {
            font-size: 14px;
            color: #101010;
            padding: 0;
            margin: 0;
        }

        .main {
            width: 100%;
        }

        .header {
            margin: 0 auto;
            padding: 10px 0;
            width: 96%;
            border-bottom: 1px solid #bbb;
        }

        .header .header-bar {
            display: flex;
            justify-content: flex-end;
        }

        .header .header-bar-title {
            width: 60px;
            height: 38px;
            line-height: 38px;
            text-align: center;
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
            width: 96%;
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
            width: 40%;
            text-align: left;
        }

        .contain .cell .charts {
            padding: 10px 1%;
            width: 22%;
            height: 400px;
            border: 1px solid #bbb;
        }
        .contain .cell .chartsAJ {
            /*padding: 10px 1%;*/
            width: 100%;
            height: 350px;
            /*border: 1px solid #bbb;*/
        }

        .contain .cell .charts-map {
            width: 70%;
            height: 100%;
        }

        .contain .cell div.wid100 {
            padding: 10px 1%;
            width: 98%;
        }

        .contain .cell div.wid70 {
            padding: 10px 1%;
            width: 68%;
        }

        .contain .cell div.wid69 {
            padding: 10px 1%;
            width: 69%;
        }

        .contain .cell div.wid50 {
            padding: 10px 1%;
            width: 49%;
        }

        .contain .cell div.wid29 {
            padding: 10px 1%;
            width: 29%;
        }

        .contain .cell div.wid22 {
            padding: 10px 1%;
            width: 22%;
        }

        .contain .cell .charts-half {
            padding: 10px 1%;
            width: 50%;
            /* height: 310px; */
        }


        .baseInfo {
            width: 32%;
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
        }

        .keyWords {
            width: 66%;
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
            width: 20%;
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
            /*padding-bottom: 40px;*/
            width: 100%;
            line-height: 30px;
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
        .color1{
            color: #d70606!important;
        }
        .color2{
            color: #398800!important;
        }

        .institution {
            width: 100%;
            border: 1px solid #bbb;
            padding-bottom: 10px;
        }

        .institution .i-header {
            padding: 5px 2% 5px 1%;
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
            font-weight: 600;
        }

        .institution .i-header .search-out{
            height: 34px;
            overflow: hidden;
        }
        .institution .i-header .search {
            /*display: flex;*/
            width: 300px;
        }

        /*.institution .i-header .search input {*/
        /*    padding-left: 10px;*/
        /*    width: 260px;*/
        /*    height: 40px;*/
        /*    line-height: 40px;*/
        /*    border: 1px solid #bbb;*/
        /*}*/

        /*.institution .i-header .search .btn {*/
        /*    width: 70px;*/
        /*    line-height: 40px;*/
        /*    background-color: #3BA9FF;*/
        /*    color: #fff;*/
        /*    text-align: center;*/
        /*    cursor: pointer;*/
        /*}*/

        .institution .i-table {
            display: none;
            width: 100%;
            /*padding: 0 2% 0 3%;*/
            margin: 0 auto;
        }

        .institution .i-table .i-thead {
            padding: 10px 0;
            display: flex;
            width: 100%;
            border-bottom: 1px solid #bbb;
        }

        .institution .i-table .i-tbody {
            width: 100%;
            margin: 0 auto;
            height:600px;
            overflow: auto;
        }

        .institution .i-table .i-thead .i-td {
            width: 9.5%;
            height: 20px;
            line-height: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
        }
        .institution .i-table .i-thead .i-td:nth-of-type(100n+1){
            width: 5%;
        }
        .institution .i-table .i-tr {
            padding: 10px 0;
            display: flex;
            width: 100%;
        }

        .institution .i-table .i-tr .i-td {
            width: 9.5%;
            text-align: center;
        }
        .institution .i-table .i-tr .i-td:nth-of-type(100n+1){
            width: 5%;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .institution .i-table2 .i-tr .i-td {
            width: 8.5%;
            text-align: center;
        }
         .institution .i-table2 .i-thead  .i-td:first-of-type{
            width: 6.5%;
        }
        .institution .i-table2 .i-tr .i-td:first-of-type{
            width: 6.5%;
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

        /*.icon-up:hover {*/
        /*    border-bottom: 7px solid #333;*/
        /*}*/

        /*.icon-down:hover {*/
        /*    border-top: 7px solid #333;*/
        /*}*/

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

        .list #charts7_table{
            height: 611px;
            overflow: hidden;
        }

        .list .bar {
            /*display: none;*/
            /*height: 611px;*/
        }

        .list .li {
            width: 100%;
            padding: 13px 0;
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
            width: 68px;
            height: 28px;
            line-height: 28px;
            border: 1px solid #ddd;
            background-color: #fff;
        }

        .list .li .btn {
            margin: 0 6px;
            width: 48px;
            height: 28px;
            /*line-height: 28px;*/
            border: 1px solid #ddd;
            background-color: #fff;
            color: #666;
            text-align: center;
            cursor: pointer;
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

        .info-span{
            font-weight: bold;
            font-size: 17px;
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
    </style>
</head>

<body>
<div class="main">
    <div class="header">
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
        </div>
        <div class="cell">
            <div class="charts wid69">
                <div class="title" style="display: flex;justify-content: space-between;padding-bottom: 5px;">
                    <div style="font-size: 16px;font-weight: 600">案件走势</div>
                    <div class="tabbars">
                        <div class="tabbar active" data-type="1">最近一年</div>
                        <div class="tabbar" data-type="2">按筛选时间</div>
                    </div>
                </div>
                <div class="content " >
                    <div id="charts1" class="chartsAJ"></div>
                </div>
            </div>
            <%--<div id="charts1" class="charts wid69"></div>--%>
            <div id="charts2" class="charts wid29"></div>
        </div>

        <div class="cell">
            <div class="charts wid50 charts-all">
                <div class="title">领域统计</div>
                <div class="content">
                    <div id="charts3" class="charts-half"></div>
                    <div class="charts-info" id="charts3_table">
                        <div class='charts-tr'>
                            <div class='charts-td'>领域</div>
                            <div class='charts-td'>案件数</div>
                            <div class='charts-td'>占比</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="charts wid50 charts-all">
                <div class="title">业务类型统计</div>
                <div class="content">
                    <div id="charts4" class="charts-half"></div>
                    <div class="charts-info" id="charts4_table">

                    </div>
                </div>
            </div>
        </div>
        <div class="cell">
            <div class="charts wid50" style="position: relative;">
                <div id="charts5" style="width: 100%;height: 100%">
                </div>
                <div class="charts-changes">
                    <input type="hidden" id="orgAttr" value="1">
                    <%--                    <div class="charts-change charts-change_active" data-type="1">--%>
                    <%--                        保司--%>
                    <%--                    </div>--%>
                    <%--                    <div class="charts-change" data-type="2">--%>
                    <%--                        互助--%>
                    <%--                    </div>--%>
                </div>
            </div>
            <div class="charts wid50 charts-all">
                <div class="title">案件跨区域统计</div>
                <div class="content">
                    <div id="charts6" class="charts-half"></div>
                    <div class="charts-info" id="charts6_table">

                    </div>
                </div>
            </div>
        </div>
        <div class="cell">
            <div class="charts" style="width: 100%;height: 700px;padding: 0;display: flex;">
                <div id="charts7" class="charts-map"></div>
                <div class="chartsm-info">
                    <div class="list">
                        <div class="li" style="background-color: #f2f2f2;padding: 11px 0;">
                            <div class="name">省份</div>
                            <div class="num-text" style="display: flex; align-items: center;padding-right: 0;">
                                <span class="span-text" style="padding-right: 4px;">案件数</span>
                                <div class="icon-sort">
                                    <div class="icon-up" data-sort-attr="1" data-sort-type="1"></div>
                                    <div class="icon-down" data-sort-attr="1" data-sort-type="2"></div>
                                </div>
                            </div>
                        </div>
                        <div id="charts7_table">
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
            <div class="institution">
                <div class="i-header">
                    <div class="title"><a class="selected">委托机构排名</a><a>调查机构排名</a></div>
                    <div class="search-out">
                        <div class='search'>
                            <select name="entrustOrgId" id="entrustOrgId" class="singleSelect form-control" onchange="searchOrg(1)">
                                <option value="-1" selected>全部</option>
                                <c:forEach items="${consignors}" var="item">
                                    <option <c:if test="${entrustOrgId == item.id}">selected="selected" </c:if> value="${item.id}" >${item.company}</option>
                                </c:forEach>
                            </select>
<%--                            <select name="franchisees" id="surveyOrgId" class="singleSelect form-control" onchange="searchOrg(2)">--%>
<%--                                <option value="-1" selected>全部</option>--%>
<%--                                <c:forEach items="${franchisees}" var="item">--%>
<%--                                    <option <c:if test="${surveyOrgId == item.id}">selected="selected" </c:if> value="${item.id}" >${item.name}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
                            <select name="entrustOrgId2" id="entrustOrgId2" class="singleSelect form-control" onchange="searchOrg(2)">
                                <option value="-1" selected>全部</option>
                                <c:forEach items="${consignors}" var="item">
                                    <option <c:if test="${entrustOrgId == item.id}">selected="selected" </c:if> value="${item.id}" >${item.company}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <input id="searchExportBtn" type="button" onclick="searchOrg(3)" value="导出"  style="display: none;color: #fff;    background-color: #3ba9ff;    width: 100px;    border: none;    float: right;    margin-right: 84px;    height: 37px;">
                <input id="searchMarkErrorExportBtn" onclick="markErrorExport()" type="button" value="差错率明细导出" style="display: none;color: #fff;    background-color: #3ba9ff;    width: 119px;    border: none;    float: right;    margin-right: 9px;    height: 37px;">
                <input id="searchReturnExportBtn" onclick="returnExport()" type="button" value="退回率明细导出" style="display: none;color: #fff;    background-color: #3ba9ff;    width: 119px;    border: none;    float: right;    margin-right: 9px;    height: 37px;">
                <div class="i-table" style="display: block;">
                    <div class="i-thead">
                        <div class="i-td">排名</div>
                        <div class="i-td">机构名称</div>
                        <div class="i-td" data-search-type="1" data-sort-attr="0">
                            <span class="i-td-text">新增委托(件)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="1" data-sort-attr="0" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="1" data-sort-attr="0" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="1" data-sort-attr="15">
                            <span class="i-td-text">环比</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="1" data-sort-attr="15" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="1" data-sort-attr="15" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="1" data-sort-attr="16">
                            <span class="i-td-text">同比</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="1" data-sort-attr="16" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="1" data-sort-attr="16" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="1" data-sort-attr="1">
                            <span class="i-td-text" style="font-weight: 600;">保司审核案件数(件)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="1" data-sort-attr="1" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="1" data-sort-attr="1" data-sort-type="2" style="border-top-color: #333"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="1" data-sort-attr="2">
                            <span class="i-td-text">调查费(元)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="1" data-sort-attr="2" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="1" data-sort-attr="2" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="1" data-sort-attr="3">
                            <span class="i-td-text">案件均价(元/件)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="1" data-sort-attr="3" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="1" data-sort-attr="3" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="1" data-sort-attr="8">
                            <span class="i-td-text">方向均价(元/件)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="1" data-sort-attr="8" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="1" data-sort-attr="8" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="1" data-sort-attr="4"><span class="i-td-text">阳性率(%)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="1" data-sort-attr="4" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="1" data-sort-attr="4" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="1" data-sort-attr="5"><span class="i-td-text">跨区域程度(省)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="1" data-sort-attr="5" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="1" data-sort-attr="5" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="1" data-sort-attr="6"><span class="i-td-text">调查时效(天)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="1" data-sort-attr="6" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="1" data-sort-attr="6" data-sort-type="2"></div>
                            </div>
                        </div>
                        <div class="i-td" data-search-type="1" data-sort-attr="7"><span class="i-td-text">超时效占比(%)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="1" data-sort-attr="7" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="1" data-sort-attr="7" data-sort-type="2"></div>
                            </div>
                        </div>
                    </div>
                    <div class="i-tbody" id="div_table_entrust_trs">

                    </div>
                </div>
                <div class="i-table i-table2">
                    <div class="i-thead">
                        <div class="i-td">排名</div>
                        <div class="i-td">机构名称</div>
                        <div class="i-td" data-search-type="2" data-sort-attr="9">
                            <span class="i-td-text" style="font-weight: 600;">新增委派</span>
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
                        <div class="i-td" data-search-type="2" data-sort-attr="11"><span class="i-td-text">差错率(%)</span>
                            <div class="icon-sort">
                                <div class="icon-up" data-search-type="2" data-sort-attr="11" data-sort-type="1"></div>
                                <div class="icon-down" data-search-type="2" data-sort-attr="11" data-sort-type="2"></div>
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
    </div>
</div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/echars/echarts.min.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?ak=geF7zRxYTlIuHlqVTxtbKM70GqFSPTAj"></script>
<script type="text/javascript" src="${ctx}/js/echars/bmap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
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
            dataType:"all",
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
        var url = "${ctx}/survey/report/index?menuCode=caseItem&params="+params
        parent.addTab("案件列表",url,true);
    }

    function markErrorExport(){
        var params = {
            searchType: $("#searchType").val(),
            startTime: $("#startTime").val(),
            endTime: $("#endTime").val(),
            dataType:"org",
            dataItem: 'dataItem',
            checkType: '2',
            itemType: 'tar',
            itemValue: '9',
            from : "indexToOrg"
        };
        var params = JSON.stringify(params)
        params = encodeURIComponent(params)
        var url = "${ctx}/survey/report/index?menuCode=caseItem&params="+params
        parent.addTab("案件列表",url,true);
    }
    function returnExport(){
        var params = {
            searchType: $("#searchType").val(),
            startTime: $("#startTime").val(),
            endTime: $("#endTime").val(),
            dataType:"org",
            dataItem: 'dataItem',
            checkType: '2',
            itemType: 'tar',
            itemValue: '6',
            from : "indexToOrgReturn"
        };
        var params = JSON.stringify(params)
        params = encodeURIComponent(params)
        var url = "${ctx}/survey/report/index?menuCode=caseItem&params="+params
        parent.addTab("案件列表",url,true);
    }
    var myChart = echarts.init(document.getElementById('charts1'));
    var myChart2 = echarts.init(document.getElementById('charts2'));
    var myChart3 = echarts.init(document.getElementById('charts3'));
    var myChart4 = echarts.init(document.getElementById('charts4'));
    var myChart5 = echarts.init(document.getElementById('charts5'));
    var myChart6 = echarts.init(document.getElementById('charts6'));
    var myChart7 = echarts.init(document.getElementById('charts7'));

    function initDateData(){
        $('.header-choose-bar').removeClass('active')
        $("#searchType").val("date");
        initData();
    }

    function initData() {
        //加载任务
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"all","dataTable":"bas"};
        param["searchType"] = searchType;
        param["startTime"] = $("#startTime").val();
        param["endTime"] = $("#endTime").val();
        param["tbStartTime"] = $("#tbStartTime").val();
        param["tbEndTime"] = $("#tbEndTime").val();
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
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

                var bas = e.data.results.bas;
                if(bas){
                    var html = "";
                    html += '<div class="info" title="委托方机构 '+bas.basEntrustOrgs+'家">委托方机构<span class="info-span"> '+bas.basEntrustOrgs+'家</span></div>';
                    html += '<div class="info" title="调查方机构 '+bas.basSurveyOrgs+'家">调查方机构<span class="info-span">  '+bas.basSurveyOrgs+'家</span></div>';
                    html += '<div class="info" title="调查员 '+bas.basSurveyUsers+'人">调查员<span class="info-span">  '+bas.basSurveyUsers+'人</span></div>';
                    html += '<div class="info caseList" data-index="1" data-type="bas" title="开票未到账 '+bas.basBillNotAcc+'元">开票未到账<span class="info-span">  '+bas.basBillNotAcc.toFixed(2)+'元</span></div>';
                    html += '<div class="info caseList" data-index="2" data-type="bas" title="委托待受理 '+bas.basEntrustNotApv+'件">委托待受理<span class="info-span">  '+bas.basEntrustNotApv+'件</span></div>';
                    html += '<div class="info caseList" data-index="3" data-type="bas" title="平台待分派 '+bas.basLefanNotAss+'件">平台待分派<span class="info-span">  '+bas.basLefanNotAss+'件</span></div>';
                    html += '<div class="info caseList" data-index="4" data-type="bas" title="机构调查中 '+bas.basOrgSurveying+'件">机构调查中 <span class="info-span"> '+bas.basOrgSurveying+'件</span></div>';
                    html += '<div class="info caseList" data-index="5" data-type="bas" title="平台待终审 '+bas.basLefanApv+'件">平台待终审 <span class="info-span"> '+bas.basLefanApv+'件</span></div>';
                    html += '<div class="info caseList" data-index="6" data-type="bas" title="保司待审核 '+bas.basEntrustApv+'件">保司待审核 <span class="info-span"> '+bas.basEntrustApv+'件</span></div>';
                    html += '<div class="info caseList" data-index="7" data-type="bas" title="财务待结案 '+bas.basNotClose+'件">财务待结案 <span class="info-span"> '+bas.basNotClose+'件</span></div>';
                    $(".baseInfo .infos").html(html);

                    html = "";
                    var rate = (bas.tarNewEntrustRate * 100).toFixed(2)
                    var _htmlRate = '<div class="rate">环比'+rate+'%</div>'
                    if (rate > 0){
                        _htmlRate = '<div class="rate color1">环比+' +rate+'%</div>'
                    } else if (rate < 0){
                        _htmlRate = '<div class="rate color2">环比' +rate+'%</div>'
                    }
                    var tbRate = (bas.tarTbNewEntrustRate * 100).toFixed(2)
                    var _htmlTbRate = '<div class="rate">同比'+tbRate+'%</div>'
                    if (tbRate > 0){
                        _htmlTbRate = '<div class="rate color1">同比+' +tbRate+'%</div>'
                    } else if (tbRate < 0){
                        _htmlTbRate = '<div class="rate color2">同比' +tbRate+'%</div>'
                    }
                    html += '<div class="keybar caseList" data-index="1" data-type="tar"><div><div class="name">新增委托</div><div class="num">'+bas.tarNewEntrust+'件</div>'+_htmlRate+_htmlTbRate+'</div></div>';
                    html += '<div class="keybar caseList" data-index="2" data-type="tar"><div class="name">保司终审通过</div><div class="num">'+bas.tarNewEntrustOK+'件</div></div>';
                    html += '<div class="keybar caseList" data-index="3" data-type="tar"><div class="name">调查费</div><div class="num">'+bas.tarEntrustMoney.toFixed(2)+'元</div></div>';
                    html += '<div class="keybar caseList" data-index="4" data-type="tar"><div class="name">开票</div><div class="num">'+bas.tarBillMoney.toFixed(2)+'元</div></div>';
                    html += '<div class="keybar" data-index="5" data-type="tar"><div class="name">到账</div><div class="num">'+bas.tarAccMoney.toFixed(2)+'元</div></div>';
                    html += '<div class="keybar two"><div class="name">案件均价</div><div class="num">'+bas.tarAvgMoney.toFixed(0)+'元</div></div>';
                    html += '<div class="keybar two"><div class="name">方向均价</div><div class="num">'+bas.tarAvgDirectionMoney.toFixed(0)+'元</div></div>';
                    html += '<div class="keybar two caseList" data-index="6" data-type="tar"><div class="name">阳性率</div><div class="num">'+bas.tarSunRate.toFixed(2)+'%</div></div>';
                    html += '<div class="keybar two"><div class="name">跨区域程度</div><div class="num">'+bas.tarAraeRate.toFixed(2)+'省</div></div>';
                    html += '<div class="keybar two"><div class="name">调查时效</div><div class="num">'+bas.tarEffic+'天</div></div>';
                    html += '<div class="keybar two caseList" data-index="7" data-type="tar"><div class="name">超时效占比</div><div class="num">'+bas.tarEfficRate.toFixed(2)+'%</div></div>';
                    $(".keyWords .content").html(html);
                }
                //委托机构排名
                var entrustOrgs = e.data.results.entrustOrgs;
                var html = "";
                if(entrustOrgs){
                    for(var i = 0 ; i < entrustOrgs.length ; i ++ ){
                        var hbRate = "",hbHtml = "";
                        hbRate = (entrustOrgs[i].hbNewSurveyCaseNumRate * 100).toFixed(2);
                        if (hbRate > 0){
                            hbHtml = '<div class="rate color1">+'+hbRate+'%</div>'
                        }else if (hbRate < 0){
                            hbHtml = '<div class="rate color2">'+hbRate+'%</div>'
                        }else {
                            hbHtml = '<div class="rate">0%</div>'
                        }

                        var tbRate = "",tbHtml = "";
                        tbRate = (entrustOrgs[i].tbNewSurveyCaseNumRate * 100).toFixed(2);
                        if (tbRate > 0){
                            tbHtml = '<div class="rate color1">+'+tbRate+'%</div>'
                        }else if (tbRate < 0){
                            tbHtml = '<div class="rate color2">'+tbRate+'%</div>'
                        }else {
                            tbHtml = '<div class="rate">0%</div>'
                        }

                        var avg = 0;
                        if(entrustOrgs[i].newCheckNum.toFixed(2) > 0){
                            avg = entrustOrgs[i].surveyMoney.toFixed(2) / entrustOrgs[i].newCheckNum.toFixed(2);
                        }
                        var directionAvg = 0;
                        if(entrustOrgs[i].directionNum.toFixed(2) > 0){
                            directionAvg = entrustOrgs[i].surveyMoney.toFixed(2) / entrustOrgs[i].directionNum.toFixed(2);
                        }
                        var _index = i +1
                        html += '<div class="i-tr">' +
                            '<div class="i-td">'+_index+'</div>' +
                            '<div class="i-td" style="cursor: pointer;color:#3ba9ff" onclick="addSurveyPage('+entrustOrgs[i].consignorOrgId+',1)" title="'+entrustOrgs[i].consignorOrgName+'">'+ entrustOrgs[i].consignorOrgName +'</div>' +
                            '<div class="i-td caseList" data-index="1" data-id="'+entrustOrgs[i].consignorOrgId+'" data-type="entrustOrg">'+entrustOrgs[i].newSurveyCaseNum+'</div>' +
                            '<div class="i-td">'+hbHtml+'</div>' +
                            '<div class="i-td">'+tbHtml+'</div>' +
                            '<div class="i-td caseList" data-index="2" data-id="'+entrustOrgs[i].consignorOrgId+'" data-type="entrustOrg">'+ entrustOrgs[i].newCheckNum+'</div>' +
                            '<div class="i-td" data-index="3" data-id="'+entrustOrgs[i].consignorOrgId+'" data-type="entrustOrg">'+entrustOrgs[i].surveyMoney.toFixed(2)+'</div>' +
                            '<div class="i-td">'+ avg.toFixed(0)+'</div><div class="i-td">'+directionAvg.toFixed(2)+'</div>' +
                            '<div class="i-td caseList" data-index="4" data-id="'+entrustOrgs[i].consignorOrgId+'" data-type="entrustOrg">'+(entrustOrgs[i].positiveRate * 100).toFixed(2)+'</div>' +
                            '<div class="i-td">'+entrustOrgs[i].regional.toFixed(2)
                            +'</div><div class="i-td">'+entrustOrgs[i].efficiency.toFixed(1)+'</div>' +
                            '<div class="i-td caseList" data-index="5" data-id="'+entrustOrgs[i].consignorOrgId+'" data-type="entrustOrg">'+(entrustOrgs[i].lossEfficiencyRate * 100).toFixed(2)+'</div></div>'
                    }
                }
                $("#div_table_entrust_trs").html(html);
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
                            '<div class="i-td">'+surveyOrgs[i].entrustOrgMoney.toFixed(2)+'</div>'+
                            '<div class="i-td">'+ surveyOrgs[i].score.toFixed(2) +'</div>' +
                            '<div class="i-td caseList" data-index="4" data-id="'+surveyOrgs[i].surveyOrgId+'" data-type="surveyOrg">'+(surveyOrgs[i].positiveRate * 100).toFixed(2)+'</div>' +
                            '<div class="i-td">'+surveyOrgs[i].efficiency.toFixed(1) +'</div>' +
                            '<div class="i-td caseList" data-index="5" data-id="'+surveyOrgs[i].surveyOrgId+'" data-type="surveyOrg">'+(surveyOrgs[i].lossEfficiencyRate * 100).toFixed(2)+'</div>' +
                            '<div class="i-td caseList" data-index="6" data-id="'+surveyOrgs[i].surveyOrgId+'" data-type="surveyOrg">'+(surveyOrgs[i].returnRate * 100).toFixed(2)+'</div>' +
                            '<div class="i-td caseList" data-index="7" data-id="'+surveyOrgs[i].surveyOrgId+'" data-type="surveyOrg">'+(surveyOrgs[i].markErrorRate * 100).toFixed(2)+'</div>' +
                            '</div>'
                    }
                }
                $("#div_table_survey_trs").html(html);

                //领域
                initBusiness();
            }else{
                alert("加载基础数据异常");
            }
        });
    }

    function addSurveyPage(orgId,type){
        var searchType = $("#searchType").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        if (type == 1){
            parent.addTab("保司报表","${ctx}/survey/report/index?menuCode=entrust&entrustOrgId=" + orgId + "&searchType=" + searchType + "&startTime=" + startTime + "&endTime=" + endTime,true);
        } else{
            parent.addTab("机构报表","${ctx}/survey/report/index?menuCode=org&surveyOrgId=" + orgId + "&searchType=" + searchType + "&startTime=" + startTime + "&endTime=" + endTime,true);
        }
    }


    function searchOrg(type){
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"all","dataTable" : type == 1 ? "searchEntrust" : "searchSurvey"};
        param["searchType"] = searchType;
        param["startTime"] = $("#startTime").val();
        param["endTime"] = $("#endTime").val();
        param["tbStartTime"] = $("#tbStartTime").val();
        param["tbEndTime"] = $("#tbEndTime").val();
        if(type == 1){
            param["entrustOrgId"] = $("#entrustOrgId").val();
        }else{
            param["surveyOrgId"] = -1;
            param["entrustOrgId2"] = $("#entrustOrgId2").val();
        }
        param["sortAttr"] = $("#sortAttr").val();
        param["sortType"] = $("#sortType").val();
        console.log(param);
        if (type == 3) {
            param["exportType"] = "index-survey-data-export";
            var _href = '${ctx}/survey/report/surveyOrgListExport'
            for(var key in param){
                if (key == 'dataType'){
                    _href +='?'+key +'='+param[key]
                }else{
                    _href +='&'+key +'='+param[key]
                }
            }
            var aLink = document.createElement('a');
            aLink.href= _href
            aLink.dispatchEvent(new MouseEvent('click', {
                bubbles: true,
                cancelable: true,
                view: window
            }));
            return;
        }
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                if(type == 1){
                    //委托机构排名
                    var entrustOrgs = e.data.results.entrustOrgs;
                    var html = "";
                    if(entrustOrgs){
                        for(var i = 0 ; i < entrustOrgs.length ; i ++ ){
                            var hbRate = "",hbHtml = "";
                            hbRate = (entrustOrgs[i].hbNewSurveyCaseNumRate * 100).toFixed(2);
                            if (hbRate > 0){
                                hbHtml = '<div class="rate color1">+'+hbRate+'%</div>'
                            }else if (hbRate < 0){
                                hbHtml = '<div class="rate color2">'+hbRate+'%</div>'
                            }else {
                                hbHtml = '<div class="rate">0%</div>'
                            }

                            var tbRate = "",tbHtml = "";
                            tbRate = (entrustOrgs[i].tbNewSurveyCaseNumRate * 100).toFixed(2);
                            if (tbRate > 0){
                                tbHtml = '<div class="rate color1">+'+tbRate+'%</div>'
                            }else if (tbRate < 0){
                                tbHtml = '<div class="rate color2">'+tbRate+'%</div>'
                            }else {
                                tbHtml = '<div class="rate">0%</div>'
                            }


                            var avg = 0;
                            if(entrustOrgs[i].newCheckNum.toFixed(2) > 0){
                                avg = entrustOrgs[i].surveyMoney.toFixed(2) / entrustOrgs[i].newCheckNum.toFixed(2);
                            }
                            var directionAvg = 0;
                            if(entrustOrgs[i].directionNum.toFixed(2) > 0){
                                directionAvg = entrustOrgs[i].surveyMoney.toFixed(2) / entrustOrgs[i].directionNum.toFixed(2);
                            }
                            var _index = i +1
                            html += '<div class="i-tr">' +
                                '<div class="i-td">'+_index+'</div>' +
                                '<div class="i-td" style="cursor: pointer;color:#3ba9ff" onclick="addSurveyPage('+entrustOrgs[i].consignorOrgId+',1)" title="'+entrustOrgs[i].consignorOrgName+'" >'+ entrustOrgs[i].consignorOrgName +'</div>' +
                                '<div class="i-td caseList" data-index="1" data-id="'+entrustOrgs[i].consignorOrgId+'" data-type="entrustOrg">'+entrustOrgs[i].newSurveyCaseNum+'</div>' +
                                '<div class="i-td">'+hbHtml+'</div>' +
                                '<div class="i-td">'+tbHtml+'</div>' +
                                '<div class="i-td caseList" data-index="2" data-id="'+entrustOrgs[i].consignorOrgId+'" data-type="entrustOrg">'+ entrustOrgs[i].newCheckNum
                                +'</div><div class="i-td caseList" data-index="3" data-id="'+entrustOrgs[i].consignorOrgId+'" data-type="entrustOrg">'+entrustOrgs[i].surveyMoney.toFixed(2)+'</div>' +
                                '<div class="i-td">'+ avg.toFixed(2)
                                +'</div><div class="i-td">'+directionAvg.toFixed(2)+'</div>' +
                                '<div class="i-td caseList" data-index="4" data-id="'+entrustOrgs[i].consignorOrgId+'" data-type="entrustOrg">'+(entrustOrgs[i].positiveRate * 100).toFixed(2)+'</div>' +
                                '<div class="i-td">'+entrustOrgs[i].regional.toFixed(2)
                                +'</div><div class="i-td">'+entrustOrgs[i].efficiency.toFixed(1)+'</div>' +
                                '<div class="i-td caseList" data-index="5" data-id="'+entrustOrgs[i].consignorOrgId+'" data-type="entrustOrg">'+(entrustOrgs[i].lossEfficiencyRate * 100).toFixed(2)+'</div></div>'
                        }
                    }
                    $("#div_table_entrust_trs").html(html);
                }
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

    function initBusiness(){
        // var url = null,param = {};
        //加载领域
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"all","dataTable":"business"};
        param["searchType"] = searchType;
        param["startTime"] = $("#startTime").val();
        param["endTime"] = $("#endTime").val();
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var list = e.data.results.list;
                var total = e.data.results.total;

                if(list){
                    var html = "<div class='charts-tr'><div class='charts-td'>领域</div><div class='charts-td'>案件数</div><div class='charts-td'>占比</div></div>";
                    var newData = [];
                    for(var i = 0 ; i < list.length; i ++ ){
                        newData.push({
                            name: list[i].businessName,
                            value: list[i].entrustCaseNum
                        });
                        html += "<div class='charts-tr caseList' data-index='"+ list[i].businessId+"' data-type='bus'><div class='charts-td'>"+list[i].businessName+"</div>" +
                            "<div class='charts-td'>"+list[i].entrustCaseNum+"</div><div class='charts-td'>" + (list[i].entrustCaseNum / total * 100).toFixed(2) + "%</div></div>";
                    }
                    option3.series[0].data = newData;
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
                    $("#charts3_table").html(html);
                }
                //加载任务
                initTask(true);
            }else{
                alert("加载领域数据异常");
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

    function initTask(loadNext){
        console.log(loadNext,$("#orgAttr").val());
        //加载任务
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"all","dataTable":"task"};
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
                    option5.legend.data = legend;
                    option5.series[0].data = newData;
                    option5.series[1].data = newDataTasks;
                    myChart5.hideLoading();
                    if(list.length == 0){
                        myChart5.showLoading({
                            text: '暂无数据',
                            color: '#ffffff',
                            textColor: '#8a8e91',
                            maskColor: 'rgba(255, 255, 255, 0.8)',
                        })
                    }
                    myChart5.setOption(option5);
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
        var param = {"dataType":"all","dataTable":"cross"};
        param["searchType"] = searchType;
        param["startTime"] = $("#startTime").val();
        param["endTime"] = $("#endTime").val();
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var list = e.data.results.list;
                var total = e.data.results.total;
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
                    option6.series[0].data = newData;
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
                    $("#charts6_table").html(html);

                    initArea();
                }
            }else{
                alert("加载跨区域数据异常");
            }
        });
    }

    function initArea(){
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"all","dataTable":"area"};
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
                            // name: list[i].areaName2.substr(0,list[i].areaName.length - 1), // 省会名称
                            name: list[i].areaName, //省名称
                            value: list[i].num
                        });
                        html += "<div class='li caseList' data-index='"+list[i].areaId+"' data-type='area'><div class='name'>"+list[i].areaName+"</div><div class='num-text'>"+list[i].num+"</div><div class='num-line'><div class='line-icon' style='width: "+(list[i].num/maxNum * 100 > 1 ? list[i].num/maxNum * 100 : 1).toFixed(2) + "%'></div></div></div>";
                    }
                    var pageTotal = Math.ceil(list.length / 13);
                    $("#span_page_total").text(pageTotal);
                    $("#div_page_total").attr("data-page",pageTotal);
                    $("#div_page_total2").attr("data-page",pageTotal);
                    console.log("maxNum",maxNum);
                    option7.series[0].symbolSize = function(val){
                        console.log("val1",val);
                        return val[2] / (maxNum / 10);
                    };
                    option7.series[0].data = convertData(newData);

                    option7.series[1].symbolSize = function(val){
                        console.log("val2",val);
                        return val[2] / (maxNum / 20);
                    };
                    option7.series[1].data = convertData(newData.sort(function (a, b) {
                        return b.value - a.value;
                    }).slice(0, 6));
                    var arrLoc = convertData(newData)
                    var x_s = []
                    var y_s = []
                    arrLoc.map(function (m) {   x_s.push(m.value[0])
                        y_s.push(m.value[1]) })
                    x_s.sort(sortNumber)
                    y_s.sort(sortNumber)
                    console.log(x_s,y_s);
                    var xMin = Number(x_s[0])
                    var xMax = Number(x_s.reverse()[0])
                    var yMin = Number(y_s[0])
                    var yMax = Number(y_s.reverse()[0])

                    option7.bmap.center = newData.length > 0 ?[(xMax + xMin) / 2, (yMax + yMin) / 2] :[104.114129, 37.550339]
                    option7.series[0].data = newData.length > 6 ? convertData(newData).slice(6) : [];
                    myChart7.hideLoading();
                    myChart7.setOption(option7);
                    $("#charts7_table .bar").html(html);

                    initService();
                }
            }else{
                alert("加载数据异常");
            }
        });
    }

    function initService(){
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"all","dataTable":"service"};
        param["searchType"] = searchType;
        param["startTime"] = $("#startTime").val();
        param["endTime"] = $("#endTime").val();
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var list = e.data.results.list;
                var total = e.data.results.total;
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
                            "<div class='charts-td'>"+list[i].entrustCaseNum+"</div><div class='charts-td'>" + (list[i].entrustCaseNum / total * 100).toFixed(2) + "%</div><div class='charts-td'>"+ list[i].positiveNum +"</div>" +
                            "<div class='charts-td'>" + (list[i].positiveNum / list[i].entrustCaseNum * 100).toFixed(2) + "%</div><div class='charts-td'>"+list[i].agingDay.toFixed(1)+"</div></div>";
                    }
                    option4.series[0].data = newData;
                    myChart4.hideLoading();
                    if(list.length == 0){
                        myChart4.showLoading({
                            text: '暂无数据',
                            color: '#ffffff',
                            textColor: '#8a8e91',
                            maskColor: 'rgba(255, 255, 255, 0.8)',
                        })
                    }
                    myChart4.setOption(option4);
                    $("#charts4_table").html(html);

                    initAttr();
                }
            }else{
                alert("加载跨区域数据异常");
            }
        });
    }

    function initAttr(){
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"all","dataTable":"attr"};
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
                            name: list[i].businessAttributesName,
                            value: list[i].entrustCaseNum
                        });
                    }
                    option2.series[0].data = newData;
                    myChart2.hideLoading();
                    if(list.length == 0){
                        myChart2.showLoading({
                            text: '暂无数据',
                            color: '#ffffff',
                            textColor: '#8a8e91',
                            maskColor: 'rgba(255, 255, 255, 0.8)',
                        })
                    }
                    myChart2.setOption(option2);
                }

                initTrend();
            }else{
                alert("加载跨区域数据异常");
            }
        });
    }

    function initTrend(){
        var searchType = $("#searchType").val();
        var url = "${ctx}/survey/report/getData";
        var param = {"dataType":"all","dataTable":"trend"};
        var trendType = $('.tabbars .tabbar.active').attr("data-type");
        if (trendType == 1){
            searchType = "soon";
        }
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
                    option.xAxis.data = newData;
                    option.series[0].data = newData1;
                    option.series[1].data = newData2;
                    myChart.hideLoading();
                    myChart.setOption(option);
                }
            }else{
                alert("加载案件走势数据异常");
            }
        });
    }

    $('.tabbars .tabbar').click(function () {
        var _this = $(this)
        // if (!_this.hasClass('active')){
            _this.addClass('active')
            _this.siblings().removeClass('active')
            console.log(_this.attr('data-type'))
            var param = {}
            if (_this.attr('data-type') == 1){
                param = {
                    "dataType":"all",
                    "searchType" : "soon",
                    "dataTable":"trend",
                    "trendType" : 1
                }
            }else if (_this.attr('data-type') == 2){
                param = {
                    "dataType":"all",
                    "dataTable":"trend",
                    "searchType": $("#searchType").val(),
                    "startTime": $("#startTime").val(),
                    "endTime": $("#endTime").val(),
                    "trendType" : 2
                }
            }
            initTrend2(param)
        // }
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
                    option.xAxis.data = newData;
                    option.series[0].data = newData1;
                    option.series[1].data = newData2;
                    myChart.hideLoading();
                    myChart.setOption(option);
                }
            }else{
                alert("加载案件走势数据异常");
            }
        });
    }


    $(function () {
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
            var searchType = _this.attr("data-search-type");
            var sortAttr = _this.attr("data-sort-attr");
            $("#sortAttr").val(sortAttr);
            searchOrg(searchType);
        });
        // $('.i-table .icon-up').click(function () {
        //     var _this = $(this)
        //     $('.i-td-text').attr('style', '')
        //     $('.i-table .icon-up').attr('style', '')
        //     $('.i-table .icon-down').attr('style', '')
        //     _this.css({
        //         'border-bottom-color': '#333'
        //     })
        //     _this.parent().siblings().css({
        //         'font-weight': '600'
        //     })
        //     var searchType = _this.attr("data-search-type");
        //     var sortAttr = _this.attr("data-sort-attr");
        //     var sortType = _this.attr("data-sort-type");
        //     $("#sortAttr").val(sortAttr);
        //     $("#sortType").val(sortType);
        //     searchOrg(searchType);
        // })
        // $('.i-table .icon-down').click(function () {
        //     var _this = $(this)
        //     $('.i-td-text').attr('style', '')
        //     $('.i-table .icon-up').attr('style', '')
        //     $('.i-table .icon-down').attr('style', '')
        //     _this.css({
        //         'border-top-color': '#333'
        //     })
        //     _this.parent().siblings().css({
        //         'font-weight': '600'
        //     })
        //     var searchType = _this.attr("data-search-type");
        //     var sortAttr = _this.attr("data-sort-attr");
        //     var sortType = _this.attr("data-sort-type");
        //     $("#sortAttr").val(sortAttr);
        //     $("#sortType").val(sortType);
        //     searchOrg(searchType);
        // })
        $('.i-header a').click(function () {
            var _this = $(this)
            var i = $(this).index()
            _this.addClass('selected')
            _this.siblings().removeClass('selected');
            $('.i-table').attr('style', '')
            $('.i-table').eq(i).css({
                'display': 'block'
            })
            if(i == 0){
                $(".i-header .search").attr('style', '')
                $("#searchExportBtn").hide();
                $("#searchMarkErrorExportBtn").hide();
                $("#searchReturnExportBtn").hide();
            }else{
                $(".i-header .search").css({
                    transform:'translateY(-34px)'
                })
                $("#searchExportBtn").show();
                $("#searchMarkErrorExportBtn").show();
                $("#searchReturnExportBtn").show();
            }
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
    })
    /*-------数据源------------*/
    var optionData1 = {
        title:{
            text: '案件走势'
        },
        legend:{
            data: ['新增委托', '保司终审通过']
        },
        xAxis:{
            data: []
        },
        series: [{
            name: '新增委托',
            type: 'line',
            data: [],
            smooth: true

        },
            {
                name: '保司终审通过',
                type: 'line',
                data: [],
                smooth: true

            },
        ]
    }
    var optionData2 = {
        title: {
            text: '新增案件来源'
        },
        series: {
            data: [{
                value: 535,
                name: '市场一部（郑哲）'
            },
                {
                    value: 510,
                    name: '市场二部（曹刘强）'
                },
                {
                    value: 634,
                    name: '互助'
                },
                {
                    value: 735,
                    name: '正言'
                }
            ]
        }
    }
    var optionData3 = {
        series: {
            data: [{
                value: 535,
                name: '意健险调查'
            },
                {
                    value: 510,
                    name: '责任险调查'
                },
                {
                    value: 634,
                    name: '互助调查'
                },
                {
                    value: 735,
                    name: '寿险调查'
                },
                {
                    value: 735,
                    name: '车损险调查'
                },
                {
                    value: 735,
                    name: '其他调查'
                }
            ]
        }
    }

    var optionData4 = {
        series: {
            data: [{
                value: 535,
                name: '理赔调查'
            },
                {
                    value: 510,
                    name: '契约调查'
                },
                {
                    value: 634,
                    name: '深度案件'
                }
            ]
        }
    }

    var optionData5 = {
        title: {
            text: '任务统计'
        },
        legend: {
            data: ['任务类型1', '任务类型2', '任务类型3', '2-子类A', '2-子类A2', '2-子类A3', '2-子类A4', '2-子类A5', '2-子类B', '2-子类C', '3-子类A', '3-子类B',
                '3-子类C', '3-子类D', '3-子类D2', '3-子类D3', '3-子类D4', '3-子类D5'
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
                    value: 310,
                    name: '2-子类A2'
                },
                {
                    value: 310,
                    name: '2-子类A3'
                },
                {
                    value: 310,
                    name: '2-子类A4'
                },
                {
                    value: 310,
                    name: '2-子类A5'
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
                },
                {
                    value: 102,
                    name: '3-子类D1'
                },
                {
                    value: 102,
                    name: '3-子类D2'
                },
                {
                    value: 102,
                    name: '3-子类D3'
                },
                {
                    value: 102,
                    name: '3-子类D4'
                },
                {
                    value: 102,
                    name: '3-子类D5'
                }
            ]
        }
    }

    var optionData6 = {
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
    var data = [{
        name: '盐城',
        value: 15
    },
        {
            name: '青岛',
            value: 18
        },
        {
            name: '南通',
            value: 23
        },
        {
            name: '上海',
            value: 225
        },
    ]




    /*-------图表设置------------*/
    var option = {
        // title: {
        //     text: optionData1.title.text,
        //     textStyle: {
        //         fontSize: 16
        //     }
        // },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: optionData1.legend.data
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            // magicType: {
            //     type: ['line', 'bar']
            // },
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
            data: optionData1.xAxis.data

        },
        yAxis: {
            type: 'value'
        },
        series: optionData1.series
    };

    var option2 = {
        title: {
            text: optionData2.title.text,
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
            data: optionData2.series.data,
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }]
    };

    var option3 = {
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
            data: [],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }]
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

    var option5 = {
        title: {
            text: optionData5.title.text,
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
            data: optionData5.legend.data
        },
        series: [{
            name: optionData5.series1.name,
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
            data: optionData5.series1.data
        },
            {
                name: optionData5.series2.name,
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
                data: optionData5.series2.data
            }
        ],

    };

    var option6 = {
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
            data: optionData6.series.data,
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

    function sortNumber(a, b) {
        return a - b
    }
    var wh = $('#charts7');
    var arrLoc = convertData(data);
    var x_s = [];
    var y_s = [];
    // arrLoc.map(m => {
    //     x_s.push(m.value[0])
    //     y_s.push(m.value[1])
    // })
    arrLoc.map(function(m){
        x_s.push(m.value[0]);
        y_s.push(m.value[1]);
    })
    x_s.sort(sortNumber);
    y_s.sort(sortNumber);
    console.log(x_s,y_s);
    var xMin = x_s[0];
    var xMax = x_s.reverse()[0];
    var yMin = y_s[0];
    var yMax = y_s.reverse()[0];
    console.log(xMax,xMin, yMax , yMin);
    var option7 = {
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
                // dataView: {
                //     readOnly: false
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
            center: [(xMax + xMin) / 2, (yMax + yMin) / 2],
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
                //return val[2] / 7;
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
                    //return val[2] / 5;
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

    //ajax 加载数据
    initData();

    // myChart.setOption(option);
    // myChart2.setOption(option2);
    // myChart3.setOption(option3);
    // myChart4.setOption(option4);
    // myChart5.setOption(option5);
    // myChart6.setOption(option6);
    // myChart7.setOption(option7);
    var bsZoom = ''

    function toBig(){
        bsZoom = option7.bmap.zoom
        bsZoom += 1
        option7.bmap.zoom = bsZoom
        myChart7.setOption(option7);
    }
    function toSmall(){
        bsZoom = option7.bmap.zoom
        if (bsZoom < 5){
            alert('已至最小缩放比例')
            return;
        }
        bsZoom -= 1
        option7.bmap.zoom = bsZoom
        myChart7.setOption(option7);
    }
    function toRestore() {
        option7.bmap.zoom = 5
        myChart7.setOption(option7);
    }

</script>
</body>

</html>