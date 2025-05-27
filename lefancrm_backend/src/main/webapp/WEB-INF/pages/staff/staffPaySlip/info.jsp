<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">
    <style>
        .layui-form-label{
            box-sizing: content-box!important;
        }
        .main {
            width: 98%;
            margin: 0 auto;
        }
        /*.layui-form-item {*/
            /*margin: 0 !important;*/
        /*}*/
        .layui-form {
            margin-top: 10px;
        }

        .layui-form .layui-form-item .layui-inline {
            margin-right: 0;
            margin-bottom: 0;
        }
        .layui-form label{
            margin-bottom: 0;
        }

        .layui-form .layui-form-item .layui-inline .layui-form-label {
            width: 90px;
        }

        .layui-form-submit .layui-form-item .layui-inline .layui-form-label {
            width: 100px;
        }

        .layui-form .layui-inline .layui-form-item .layui-input-inline {
            width: 170px;
        }

        .layui-table-edit {
            width: 100% !important;
        }


        .layui-table .layui-input {
            height: 100%;
        }

        .layui-form-label-a {
            width: auto !important;
            white-space: nowrap;
        }

        .layui-form-label a {
            color: #3BA9FF !important;
            white-space: nowrap;
        }

        .searchs {
            padding: 15px 0 5px 0;
            background-color: #d9edf7;
        }

        .layui-btn-import{
            color:#fff!important;
        }
        input{
            border-color:#e6e6e6!important;
        }
        .top_reason{
            width: 100%;
            padding: 20px 2%;
            background-color:rgba(238, 136, 132, 0.26);
        }
        .top_reason div{
            padding: 10px 0;
        }
        .sum_content{
            padding: 10px 0;
            text-align: right;
        }
        .sum{
            display: inline-block;
            padding-right: 20px;
            text-align: right;
            font-size: 12px;
        }
        .sum.sum0{
            padding-right: 2px;
        }
        .sum span{
            font-size: 26px;
            font-weight: bold;
            color: #666;
            font-family: Impact;
        }

        @media screen and (max-width: 1300px){
            .sum{
                display: inline-block;
                padding-right: 8px;
                text-align: right;
                font-size: 11px;
            }
            .sum.sum0{
                padding-right: 2px;
            }
            .sum span{
                font-size: 22px;
            }
        }

        .layui-input1{
            width: 100%;
            height: 100%;
            padding-left: 10px;
            border: 1px solid #e6e6e6!important;
            border-radius: 2px;
        }
        .layui-input-td{
            line-height: inherit;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .lf-export{
        }

        .layui-table-fixed-r .layui-table-cell{
            padding: 0;
        }

        /*.layui-table-fixed-r .layui-table-cell .operateBtn2{*/
            /*margin-left: 0;*/
        /*}*/
        .operateBtn{
            color: #3ba9ff;
            margin-left: 10px;
            cursor: pointer;
        }
        .operateBtn:hover{
            text-decoration: none;
            color: red;
        }


        .layui-table-fixed-l table thead tr:first-of-type th:first-of-type,
        .layui-table-header table thead tr:first-of-type th:first-of-type,
        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+2),
        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+3){
            background-color: rgba(78, 183, 195, 0.3);
        }
        .layui-table-fixed-r table thead tr:first-of-type th:first-of-type{
            background-color: #f2f2f2;
        }

        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+4){
            /*border-right-color:rgba(101, 206, 114, 0.3);*/
            background-color: rgba(101, 206, 114, 0.3);

        }

        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+5){
            /*border-right-color:rgba(236, 170, 62, 0.3);*/
            background-color: rgba(236, 170, 62, 0.3);

        }

        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+6),
        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+7),
        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+8),
        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+9),
        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+10){
            background-color: rgba(230, 152, 152, 0.3);
        }

        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 0),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 1),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 2),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 3),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 4),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 5),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 6),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 7),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 8){
            border-right-color:rgba(78, 183, 195, 0.3);
            background-color: rgba(78, 183, 195, 0.3);

        }


        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 9),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 10),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 11),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 12),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 13),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 14),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 15),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 16),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 17),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 18),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 19),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 20),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 21),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 22),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 23),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 24),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 25),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 26){
            border-right-color:rgba(101, 206, 114, 0.3);
            background-color: rgba(101, 206, 114, 0.3);

        }




        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 27),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 28),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 29),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 30),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 31),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 32),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 33),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 34),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 35),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 36),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 37),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 38),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 39),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 40),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 41),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 42),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 43){
            border-right-color:rgba(236, 170, 62, 0.3);
            background-color: rgba(236, 170, 62, 0.3);

        }

        .layui-table-hover{
            background-color: rgba(60, 169, 255, 0.13) !important;
        }

        .lf-none{
            display: none;
        }

        .layui-btn-xs{
            border-radius: 2px;
        }


        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }

        .export-btn{
            width: 90%;margin: 0 auto;text-align: center;display: flex;justify-content: center;
        }


        tr.active td{
            background-color: rgba(236,128,126,0.4);
        }

        .layui-input-block{
            width: 90%;
            margin: 0 auto;
        }

        .po-none{
            pointer-events: none;
        }

        .searchs .layui-input{
            height: 32px;
        }
        .viewClock{
            display: inline-block;
            margin-left: 5px;
            padding: 2px;
            width:40px;
            height: 26px;
            cursor: pointer;
            color: #3ba9ff;
            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAJF0lEQVR4Xu2dUVLbSBCGuxWT7NvaXGChKuY1cII4J4hzgiWPMVsVOEHgBJCqtXmEnCDkBPGeIM4rShXkAuB9WydGvTUCeV2spemRRsxYar961DP99zejmdGohSC/WiuAtfZenAcBoOYQCAACQM0VqLn7MgIIADVXoObuywggANRcgZq7LyOAAFBzBWruvowAAoAfCjQPL5rw5OZlgLSWtCi6gdH4j/YnP1qYrxXNP8OXwSPYnPlEeAmTR5/Ge+vjfBbtXuV8BFCBDx7/PATE7UWuEcEYAziK/mm890U0XQhin36ZvqUIdhGhubA80WkUrByM36xf6uyV+b9TAJr9800E/Jwq0pznRDQigNfjnY1RmYIUtX3rE5wg4qzXp9lUcBPi63Hv6VnRevNe7wwAk+AnzsWC/Wis+zoSNI8v1jCafuEAPR+wCOHF+E17mDeIRa5zAoAaIvHJzy8IOLvfc50ggOF1r/2CW/4hy7UG4WcE6JjW6RJsJwCs9sN9QHhnKtR/EylSt4LTvNeXcV2zf74dIJ7kt00frnobC+dB+W3qr3QCQKsfXpsOk/Ou+DgK5O39rm9vDw5A8zjsBASfUydGQF+RgiEhbSPAr2nlrnrtB297Vn9aHYSUMdn7joBnBNRFhN/SyrmYCxQSMV67r0yfz69zdYMOEahZcndROQL6et3biGfPapIYIH5JtUd0CgE6XULN2hbRWtoyVpWJsLGulnvx3OfxdJQGgRrZEIE9GYws7CnkAiCe7dL0MC2QOgjSgwoHVzvt/eT/rF6Vuw4HF86PVq1BOESA5zabQQBnRHSQZ4lsDMDqIDwEgF2bDsxskQBQUNejq157z8SGEQCr/fOTrKHOpOKFZQUACxLCkCaNV9y9EjYApQdfuS4AFAYgNkB0erWz8ZpjjAVAc/CtGwB95BgsVEYAKCTf/MUR8fZKWAC0BucXeXbtjL0RAIwly1h6srbNtQBwer9avqm1OwBpH3ESQidtFkxAlzRZ2VL3L91+AQB9ALUM8uWXsbMZEW2pGbpuC5wA/kLiLQOzdEwk4YwCWgBW+9+OAOltms4R4CuTp1m6wCoIAPAS1H5B2qNUAFiyjaAxIIwAaC1rJDXWUnNrJoJP1zvthXsuSTy1AGSuW+8N2ZzOeNsLppdZu3w6O6qnXPfaxg9ddHaL/N/qh2eI8LKIjWjSaHFn70k9uucquo7CACD9/p9361I3quhENO0pOns2/teNbPo68j0M0u2YFgYgazdOZzzN6SKjgI+9P/Ez7yhAAH/TpLFm2vtno0DGcwhdJ9WOAGUAoBoeHwhBVNuiqQ987gOkJps0WenkFUrfC4uVuJvkDRHwmYklXZB0trJipLPtDIAYguOwgwRnHAh8D34SpLvRTfmk3e+Pez5Ct+hpoKUFIIYgPhR6s5+20ohFItr17QCIrleqAyIIuJ/++Jc+RLiyb+NQ6FIDMC9kPJGKbo9URQhjIBrmecKlC85D/q9udYCozkDEp4MjxJHJspnT1soAwHFWyvxfAQGg5lQIAAJA6nE0r1cBNY+bNfdlBLAm5XIacgZAlOMliOWU2O9WB5D+BLHUW4Dfskjr7pbTma+dFdoJFIn9V0BGAP9jVGoLBYBS5fXfuADgf4xKbaEAUKq8/hsXAPyPUaktLBWAvCeCSvW4hsadbQQJAH7QJgD4EQdnrRAAnEnvR8UCgB9xcNYKAcCZ9H5ULAD4EQdnrRAAnEnvR8UCgB9xcNYKAcCZ9H5ULAD4EQdnrRAAnEnvR8UCgB9xcNYKAcCZ9H5ULAD4EQdnrVgKAOK3gJ9M3xFRh/M1jftqqtxBKoFS9GNlj5sf4O7183e6fEOLIqdy+Ku8PoRwwH19e/apGIoTYRmnsElyBV+9aR+Y0OQ9ALrsWCbOxh9XCBpbuteqm4NwNwBQaW0L/yKAvXGvfZRlyK6PNLre2djiNtx7AFYH56cA+DvXIV05XfYrlcw6oOmFzo7J/0nG77RrWoPwo9Xk2QYJuLwHoOgHIhaJnnUYxWbvn9WtCYjtzObqI1ncUcB7AGyLo4KSlVJNlzrNpOdzACieIWxxi7gnrrwHoNUPL7O+lGEaEJU25rrXXvw9PpV2xuL9P2mbbh5gG/L5j2fo9PEeAOs9kvD91c7T1G8W3H3QYsRJPqUTV/3PSeOWN0Vcav1VmgMoJ219KUPlCaRJo6tbCsZJmhCPikIQBx9wW5fXR/c5GA5oSRnTXIjejwCJY3eZs7qQkQM4o0eMCejMJFtYvBqIbnYJSfsVz0X1IuEoCh4d6ZacM//ijGfTXU4i5/v1KdAAYEREpyY+KjtLA4BJL5CyfAUEAL5WlSwpAFQyrHynBAC+VpUsKQBUMqx8pwQAvlaVLCkAVDKsfKcEAL5WlSwpAFQyrHynBAC+VpUsKQBUMqx8pwQAvlaVLCkAVDKsfKcEAL5WlSwpAFQyrHynlgKAOrwXwA+Z3ZLeA2D3zLyf7wXYDamZNe8BqMN7AWYhs1vaewDq8F6A3ZCaWfMeANtHppU8Pr0XYBYu+6W9B6AO7wXYDyvfovcA1OG9AH647Jf0HgDlctXfC7AfVr7FpQBAuVP19wL4IbNbcmkAsOu2WEsUEABqzoIAIADIx6PrzICMAHWOvrwcWvPoCwACQKm3gNYgHKclWdB9k05CU74CuvxEujxD2q+HZ+7gGaQxKV+Ketag22YvDEDWs/zbpI3wiptJs54hKs9rXe/nJJrSjgDNwbduAPQxyw0COEOEUXmuiuX7ChDBpi4xpS6zmbKpBSB+kGM5zZuE82EU0GU3ZQOgHuIEiCcP02ypxYoCzPkZawRQDbJ9rs+Kk2JkoQLq3k+TlY4ulR57BFAF7072DhHwmejurwJE8J2CRoeb2o49AiQuy0jgcfCZSTTnPTAGIB4NjsMOEuwjwHN/5ahPy+JeD7RvmmDS6BawSE6ViRNo2g0ImgSwmSsDaH3iZNVT9fUUZTC6zZ6aewmeawSw6okYc6qAAOBUfveVCwDuY+C0BQKAU/ndVy4AuI+B0xYIAE7ld1+5AOA+Bk5bIAA4ld995QKA+xg4bYEA4FR+95ULAO5j4LQFAoBT+d1XLgC4j4HTFvwL5hCl24FtLxUAAAAASUVORK5CYII=);
            background-repeat: no-repeat;
            background-size: contain;
            background-position: center;
        }

        .layui-table-main{
            overflow-y: overlay;
        }

    </style>
</head>
<body>
<div class="main">
    <c:if test="${staffPaySlip.reason != null && (staffPaySlip.hrManageRole || staffPaySlip.ceoRole) }">
        <div class="top_reason">
            <div>驳回原因：${staffPaySlip.reason}</div>
        </div>
    </c:if>
    <div class="layui-form searchs" lay-filter="search">
        <div class="layui-form-item " style="margin-bottom: 0">
            <div class="layui-inline">
                <label class="layui-form-label">社保缴纳公司:</label>
                <div class="layui-input-inline">
                    <div id="sCompanys" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">成本归属公司:</label>
                <div class="layui-input-inline">
                    <div id="companys" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">机构/部门:</label>
                <div class="layui-input-inline">
                    <div id="organs" class="selectMul"></div>
                </div>

            </div>
            <div class="layui-inline">
                <label class="layui-form-label">科室:</label>
                <div class="layui-input-inline">
                    <div id="departments" class="selectMul"></div>
                </div>

            </div>
            <div class="layui-inline">
                <label class="layui-form-label">岗位:</label>
                <div class="layui-input-inline">
                    <div id="jobPosts" class="selectMul"></div>
                </div>

            </div>
            <%--<div class="layui-inline">--%>
                <%--<label class="layui-form-label">小组:</label>--%>
                <%--<div class="layui-input-inline">--%>
                    <%--<div id="teams" class="selectMul"></div>--%>
                <%--</div>--%>

            <%--</div>--%>
            <%--<div class="layui-inline">--%>
                <%--<label class="layui-form-label">工号</label>--%>
                <%--<div class="layui-input-inline _input">--%>
                    <%--<input type="text" name="jobNo" placeholder="请输入工号"  autocomplete="off" class="layui-input ">--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="layui-inline">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-inline _input">
                    <input type="text" name="realName" placeholder="请输入姓名"  autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">入职时间</label>
                <div class="layui-input-inline _input">
                    <input type="text" name="entryTimeStr" class="layui-input" id="entryTimeStr" placeholder="入职开始时间" style="cursor: pointer" readonly>
                </div>
                <div class="layui-input-inline _input">
                    <input type="text" name="entryTimeEndStr" class="layui-input" id="entryTimeEndStr" placeholder="入职截至时间" style="cursor: pointer" readonly>
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 20px;">
                <button id="searchSubmit" lay-submit class="ll-submit layui-btn layui-btn-normal  layui-btn-sm layui-btn-radius" lay-filter="submit"
                        style="width: 86px">查询 <i class="layui-icon layui-icon-search"></i></button>
                <%--<button lay-submit class="ll-reset layui-btn layui-btn-normal  layui-btn-sm layui-btn-radius" lay-filter="reset"
                        style="width: 86px">重置 <i class="layui-icon layui-icon-loading" ></i></button>--%>
                <c:if test="${staffPaySlip.hrRole || staffPaySlip.hrManageRole || staffPaySlip.ceoRole || (staffPaySlip.financeRole && staffPaySlip.roleCode == 'end-step')}">
                    <button type="btn" class="layui-btn layui-btn-sm s-btn layui-btn-normal layui-btn-radius" data-type="export" style="width: 86px">  <i class="layui-icon layui-icon-export" ></i> 导出</button>
                </c:if>
            </div>
        </div>
    </div>
    <c:if test="${(staffPaySlip.roleCode == 'hr-step' && staffPaySlip.hrRole) || (staffPaySlip.roleCode == 'hrManage-step' && staffPaySlip.hrManageRole)}">
    <div class="layui-form layui-form-submit fileData">
        <div style="display: flex;justify-content: space-between;">
            <div class="layui-inline lf-import-a">
                <label class="layui-form-label layui-form-label-a">钉钉数据：<a href="${staffPaySlip.ddUrl}" title="${staffPaySlip.ddUrlName}">${staffPaySlip.ddUrlName}</a></label>
                <button type="submit" lay-submit="" class="layui-btn layui-btn-normal layui-btn-sm layui-btn-import"
                        id="importOther"><i class="layui-icon"></i>导入考勤与其他数据</button>
            </div>

            <div class="layui-inline lf-export">
                <button class="layui-btn layui-btn-normal layui-btn-sm layui-btn-import"
                        id="exportOther"><i class="layui-icon layui-icon-download-circle"></i>考勤与其他数据模版下载</button>
            </div>

        </div>
    </div>
    </c:if>

    <div class="table_block">
       <table class="layui-table" id="test" lay-filter="test" lay-data="{id: 'test'}" lay-skin="line" lay-size="lg">
       </table>
   </div>
    <div class="sum_content">
        <div class="sum sum1"><i class="layui-icon layui-icon-rmb"></i> 合计(应发工资)：<span></span></div>
        <div class="sum sum2"><i class="layui-icon layui-icon-rmb"></i> 合计(社保公司)：<span></span></div>
        <div class="sum sum3"><i class="layui-icon layui-icon-rmb"></i> 合计(社保个人)：<span></span></div>
        <div class="sum sum4"><i class="layui-icon layui-icon-rmb"></i> 合计(税前工资)：<span></span></div>
        <div class="sum sum0"><i class="layui-icon layui-icon-rmb"></i> 合计(实发工资)：<span></span></div>
    </div>
    <div class="layui-form layui-form-submit stepInfo" style="margin-top:10px;margin-bottom:10px;">
        <div class="layui-form-item password_block">
            <c:if test="${staffPaySlip.roleCode == 'hr-step' && staffPaySlip.hrRole}">
                <div class="layui-inline" style="margin-right: 20px;">
                    <label class="layui-form-label">设置查询密码</label>
                    <div class="layui-input-inline" style="width: 120px;">
                        <input type="text" name="password" required placeholder="请输入密码"
                               autocomplete="off" class="layui-input" >
                    </div>
                </div>
            </c:if>
            <div class="layui-inline form-check">
                <c:if test="${staffPaySlip.roleCode == 'hr-step' && staffPaySlip.hrRole}">
                    <button  class="layui-btn layui-btn-normal" data-type="check_hr">提交机构负责人审核
                    </button>
                </c:if>

                <c:if test="${staffPaySlip.roleCode == 'organManager-step' && staffPaySlip.organManagerRole && staffPaySlip.staffPaySlipManager.state == 0}">
                    <button  class="layui-btn layui-btn-normal" data-type="check_organManager">提交上级分管总审核（剩余${staffPaySlip.timeRemaining}）
                    </button>
                </c:if>
                <c:if test="${staffPaySlip.roleCode == 'superiorManager-step' && staffPaySlip.superiorManagerRole && staffPaySlip.staffPaySlipManager.state == 0}">
                    <button  class="layui-btn layui-btn-normal" data-type="check_superiorManager">提交人事主管处理
                    </button>
                </c:if>
                <c:if test="${staffPaySlip.roleCode == 'hrManage-step' && staffPaySlip.hrManageRole }">
                    <button  class="layui-btn layui-btn-normal" data-type="check_hrManage">提交总部审核
                    </button>
                 </c:if>
                <c:if test="${staffPaySlip.roleCode == 'ceo-step' && staffPaySlip.ceoRole}">
                        <button  class="layui-btn layui-btn-primary" data-type="reject">全部退回</button>
                        <button  class="layui-btn layui-btn-primary lf-none" data-type="rejectPart">部分退回</button>
                    <button  class="layui-btn layui-btn-normal" data-type="check_ceo">审核通过</button>
                </c:if>
            </div>
        </div>
    </div>
    <div style="display: none">
        <input type="hidden" id="id" value="${staffPaySlip.id}" />
        <input type="hidden" id="slipState" value="${staffPaySlip.slipState}" />
        <input type="hidden" id="roleCode" value="${staffPaySlip.roleCode}" />
        <input type="hidden" id="hrRole" value="${staffPaySlip.hrRole}" />
        <input type="hidden" id="organManagerRole" value="${staffPaySlip.organManagerRole}" />
        <input type="hidden" id="superiorManagerRole" value="${staffPaySlip.superiorManagerRole}" />
        <input type="hidden" id="hrManageRole" value="${staffPaySlip.hrManageRole}"/>
        <input type="hidden" id="ceoRole" value="${staffPaySlip.ceoRole}" />
        <input type="hidden" id="backState" value="${staffPaySlip.backState}" />
        <input type="hidden" id="backReason" value="${staffPaySlip.reason}" />
        <input type="hidden" id="selfState" value="${staffPaySlip.staffPaySlipManager.state}" />

        <input type="hidden" value='${params.companysJson}' id="companysJson">
        <input type="hidden" value='${params.budgetCompanysJson}' id="budgetCompanysJson">
        <input type="hidden" value='${params.organsJson}' id="organsJson">
        <input type="hidden" value='${params.departmentsJson}' id="departmentsJson">
        <input type="hidden" value='${params.jobPostsJson}' id="jobPostsJson">
    </div>
</div>
<script type="text/html" id="export-content">
    <div class="layui-form">
        <div class="layui-form-item">
            <div id="lf-export" class="selectMul" style="width: 80%;margin: 50px auto;"></div>
        </div>
        <div class="layui-inline export-btn">
            <label class="layui-form-label" >
                <button type="button" class="layui-btn s-btn layui-btn-primary" data-type="cancel">取消</button>
            </label>
            <label class="layui-form-label">
                <button type="button" class="layui-btn s-btn layui-btn-normal" data-type="export">确定</button>
            </label>
        </div>
    </div>
</script>
<script type="text/html" id="moneyInput">
    {{#  if(d.lateEarlyMoney == 'undefinded' || d.lateEarlyMoney == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <input value="{{d.lateEarlyMoney}}" class="layui-input" type="text">
    <div class="layui-input layui-input-td">{{d.lateEarlyMoney}}</div>
    {{#  } }}

</script>
<script type="text/html" id="moneyInput2">
    {{#  if(d.absenteeismMoney == 'undefinded' || d.absenteeismMoney == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td" style="width: 50%;display: inline-block;float: left">{{d.absenteeismMoney}}</div>
    {{#  if(d.jobPost == '调查员'){ }}
    <div class="viewClock" onclick="viewClock({{d.userId}},'{{d.workTime}}')"></div>
    {{#  } }}
    {{#  } }}

</script>
<script type="text/html" id="moneyInput200">
    {{#  if(d.absenteeismMoney == 'undefinded' || d.absenteeismMoney == null){ }}
    <div class="layui-input layui-input-td" style="border:none;background: none"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td" style="width: 50%;display: inline-block;float: left;border:none;background: none">{{d.absenteeismMoney}}</div>
    {{#  if(d.jobPost == '调查员'){ }}
    <div class="viewClock" onclick="viewClock({{d.userId}},'{{d.workTime}}')"></div>
    {{#  } }}
    {{#  } }}
</script>
<script type="text/html" id="moneyInput3">
    {{#  if(d.leaveMoney == 'undefinded' || d.absenteeismMoney == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.leaveMoney}}</div>
    {{#  } }}

</script>
<script type="text/html" id="sickLeave">
    {{#  if(d.sickLeaveTime == 'undefinded' || d.sickLeaveTime == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.sickLeaveTime}}</div>
    {{#  } }}

</script>
<script type="text/html" id="moneyInput4">
    {{#  if(d.sickLeaveMoney == 'undefinded' || d.sickLeaveMoney == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.sickLeaveMoney}}</div>
    {{#  } }}

</script>
<script type="text/html" id="moneyInput5">
    {{#  if(d.conpanyFundMoney == 'undefinded' || d.conpanyFundMoney == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.conpanyFundMoney}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput6">
    {{#  if(d.personalFundMoney == 'undefinded' || d.personalFundMoney == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.personalFundMoney}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput7">
    {{#  if(d.companyPensionBenefits == 'undefinded' || d.companyPensionBenefits == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.companyPensionBenefits}}</div>
    {{#  } }}

</script>
<script type="text/html" id="moneyInput8">
    {{#  if(d.personalPensionBenefits == 'undefinded' || d.personalPensionBenefits == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.personalPensionBenefits}}</div>
    {{#  } }}

</script>
<script type="text/html" id="moneyInput9">
    {{#  if(d.companyMedicalInsurance == 'undefinded' || d.companyMedicalInsurance == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.companyMedicalInsurance}}</div>
    {{#  } }}

</script>
<script type="text/html" id="moneyInput10">
    {{#  if(d.personalMedicalInsurance == 'undefinded' || d.personalMedicalInsurance == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.personalMedicalInsurance}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput11">
    {{#  if(d.companyUnemploymentInsurance == 'undefinded' || d.companyUnemploymentInsurance == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.companyUnemploymentInsurance}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput12">
    {{#  if(d.personalUnemploymentInsurance == 'undefinded' || d.personalUnemploymentInsurance == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.personalUnemploymentInsurance}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput13">
    {{#  if(d.companyBirthInsurance == 'undefinded' || d.companyBirthInsurance == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.companyBirthInsurance}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput14">
    {{#  if(d.companyInjuryInsurance == 'undefinded' || d.companyInjuryInsurance == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.companyInjuryInsurance}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput15">
    {{#  if(d.overtimePay == 'undefinded' || d.overtimePay == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.overtimePay}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput16">
    {{#  if(d.otherPay == 'undefinded' || d.otherPay == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.otherPay}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput161">
    {{#  if(d.welfarePay == 'undefinded' || d.welfarePay == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.welfarePay}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput162">
    {{#  if(d.otherCutPay  == 'undefinded' || d.otherCutPay  == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.otherCutPay}}</div>
    {{#  } }}
</script>


<script type="text/html" id="moneyInput17">
    {{#  if(d.companySickSubsidy == 'undefinded' || d.companySickSubsidy == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.companySickSubsidy}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput18">
    {{#  if(d.personalSickSubsidy == 'undefinded' || d.personalSickSubsidy == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.personalSickSubsidy}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput19">
    {{#  if(d.disabilityInsurance == 'undefinded' || d.disabilityInsurance == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.disabilityInsurance}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput20">
    {{#  if(d.serviceFee == 'undefinded' || d.serviceFee == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.serviceFee}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput21">
    {{#  if(d.socialRemark == 'undefinded' || d.socialRemark == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td" title="{{d.socialRemark}}">{{d.socialRemark}}</div>
    {{#  } }}
</script>

<%--<script type="text/html" id="individualTaxInput">--%>
    <%--{{#  if(d.individualTax == 'undefinded' || d.individualTax == null){ }}--%>
    <%--<div class="layui-input layui-input-td"></div>--%>
    <%--{{#  } else { }}--%>
    <%--<div class="layui-input layui-input-td">{{d.individualTax}}</div>--%>
    <%--{{#  } }}--%>
<%--</script>--%>
<script type="text/html" id="individualTaxChangeInput">
    {{#  if(d.individualTaxChange == 'undefinded' || d.individualTaxChange == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.individualTaxChange}}</div>
    {{#  } }}
</script>
<script type="text/html" id="realWagesInput">
    {{#  if(d.realWages == 'undefinded' || d.realWages == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.realWages}}</div>
    {{#  } }}
</script>
<script type="text/html" id="remarksInput">
    {{#  if(d.remarks == 'undefinded' || d.remarks == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td" title="{{d.remarks}}">{{d.remarks}}</div>
    {{#  } }}
</script>
<script type="text/html" id="remarks1Input">
    {{#  if(d.welfareRemark  == 'undefinded' || d.welfareRemark  == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td" title="{{d.welfareRemark}}">{{d.welfareRemark}}</div>
    {{#  } }}
</script>
<script type="text/html" id="remarks2Input">
    {{#  if(d.otherCutRemarks  == 'undefinded' || d.otherCutRemarks  == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td" title="{{d.otherCutRemarks}}">{{d.otherCutRemarks}}</div>
    {{#  } }}
</script>
<script type="text/html" id="rateInput">
    {{#  if(d.realWorkingDays == 'undefinded' || d.realWorkingDays == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.realWorkingDays}}</div>
    {{#  } }}
</script>

<script type="text/html" id="moneyInput_officeSubsidies">
    {{#  if(d.officeSubsidies == 'undefinded' || d.officeSubsidies == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.officeSubsidies}}</div>
    {{#  } }}

</script>

<script type="text/html" id="quitCostInput">
    {{#  if(d.quitCost == 'undefinded' || d.quitCost == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.quitCost}}</div>
    {{#  } }}
</script>


<script type="text/html" id="view-or-edit">
    <div class="layui-form">
        <div class="layui-form-item layui-form-text">
            <div class="layui-input-block">
                <textarea name="reason-textarea" placeholder="请输入内容" class="layui-textarea" style="height: 150px;"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-primary close-reason">取消</button>
                <button class="layui-btn layui-btn-normal submit-reason">保存并关闭</button>
            </div>
        </div>
    </div>
</script>

<script src="${ctx}/js/layui/layui.js"></script>
<script>
    var $ = ''

    function viewClock(id,time) {
        var e = window.event || arguments.callee.caller.arguments[0];
        e.stopPropagation()
        var url = "${ctx}/fee/list?userId=" + id + "&createTime=" + time+"&menuCode=preClockDetails&orgId=''";
        parent.parent.parent.addTab("打卡足迹",url,true);
    }

    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        soulTable: 'soulTable',
        xmSelect: 'xm-select'
    })

    layui.use(['jquery','soulTable','xmSelect', 'layer', 'upload', 'form', 'laydate', 'table'], function () {
         $ = jQuery = layui.$

        var soulTable = layui.soulTable,
            xmSelect = layui.xmSelect,
            layer = layui.layer,
            upload = layui.upload,
            form = layui.form,
            laydate = layui.laydate,
            table = layui.table

        var ctx="${ctx}";



        document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==13){ // 按 enter
                // 查询方法();
                $("#searchSubmit").click();
            }
        }



        var demo0 = xmSelect.render({
            el: '#sCompanys',
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
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })
        var demo1 = xmSelect.render({
            el: '#companys',
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
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })
        var demo2 = xmSelect.render({
            el: '#organs',
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
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })
        var demo3 = xmSelect.render({
            el: '#departments',
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
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })
        var demo4 = xmSelect.render({
            el: '#jobPosts',
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
                                    _html +=
                                        '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                })
                                return _html
                            }
                        }
                    },
                }
            },
            data: []
        })

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
                    Object.assign(param, {selected: true})
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

        var companysJson = $("#companysJson").val();
        companysJson = JSON.parse(companysJson);
        filterJson(demo0, companysJson, 'id', 'name', false, false)

        var budgetCompanysJson = $("#budgetCompanysJson").val();
        budgetCompanysJson = JSON.parse(budgetCompanysJson);
        filterJson(demo1, budgetCompanysJson, 'id', 'name', false, false)

        var organsJson = $("#organsJson").val();
        organsJson = JSON.parse(organsJson);
        filterJson(demo2, organsJson, 'id', 'name', false, false)

        var departmentsJson = $("#departmentsJson").val();
        departmentsJson = JSON.parse(departmentsJson);
        filterJson(demo3, departmentsJson, 'id', 'name', false, false)

        var jobPostsJson = $("#jobPostsJson").val();
        jobPostsJson = JSON.parse(jobPostsJson);
        filterJson(demo4, jobPostsJson, 'id', 'name', false, false)

        laydate.render({
            elem: '#entryTimeStr',
        });
        laydate.render({
            elem: '#entryTimeEndStr',
        });

        var roleCode = $('#roleCode').val()
        var hrRole = $('#hrRole').val()
        var organManagerRole = $('#organManagerRole').val()
        var superiorManagerRole = $('#superiorManagerRole').val()
        var hrManageRole = $('#hrManageRole').val()
        var ceoRole = $('#ceoRole').val()
        var backState = $('#backState').val()
        var backReason = $('#backReason').val()

        var selfState = $('#selfState').val()

        function barDemo(d) {
            var _html = ''

            if (roleCode == 'hr-step' && hrRole  == 'true'){

            }
            if (roleCode == 'organManager-step' && organManagerRole  == 'true'){
                _html = ''
                if (d.organOpinion){
                    _html = '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason1" title="'+d.organOpinion+'">查看机构意见</a>' +
                        '<a class=" layui-btn layui-btn-xs layui-btn-danger operateBtn1" lay-event="sendBack" style="display: none">填写机构意见</a>'

                }else {
                    _html = '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason1" style="display: none" title="'+d.organOpinion+'">查看机构意见</a>' +
                        '<a class=" layui-btn layui-btn-xs layui-btn-danger operateBtn1" lay-event="sendBack">填写机构意见</a>'
                }
            }

            if ((selfState == 1 || roleCode != 'organManager-step')  && organManagerRole  == 'true'){
                if ( d.organOpinion){
                    _html = '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason1"  title="'+d.organOpinion+'">查看机构意见</a>'
                } else {
                    _html = ''
                }
            }

            if (roleCode == 'superiorManager-step' && superiorManagerRole  == 'true'){
                _html = ''
                if (d.organOpinion){
                    _html = '<a class=" layui-btn layui-btn-xs operateBtn4" lay-event="viewReason1"  title="'+d.organOpinion+'">查看机构意见</a>'
                }
                if (d.superiorOpinion){
                    _html += '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason2" title="'+d.superiorOpinion+'">查看分管总意见</a>' +
                    '<a class=" layui-btn layui-btn-xs layui-btn-danger operateBtn1" lay-event="sendBack" style="display: none">填写分管总意见</a>'
                }else {
                    _html += '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason2" style="display: none" title="'+d.superiorOpinion+'">查看分管总意见</a>' +
                        '<a class=" layui-btn layui-btn-xs layui-btn-danger operateBtn1" lay-event="sendBack">填写分管总意见</a>'
                }
            }
            if ((selfState == 1 || (roleCode != 'superiorManager-step' && roleCode != 'organManager-step'))  && superiorManagerRole  == 'true'){
                if (d.organOpinion){
                    _html = '<a class=" layui-btn layui-btn-xs operateBtn4" lay-event="viewReason1" title="'+d.organOpinion+'">查看机构意见</a>'
                } else {
                    _html = ''
                }
                if (d.superiorOpinion){
                    _html += '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason2" title="'+d.superiorOpinion+'" >查看分管总意见</a>'
                } else {
                    _html += ''
                }
            }

            if (roleCode == 'hrManage-step' && hrManageRole  == 'true'){
                _html = ''
                if (d.organOpinion){
                    _html = '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason1" title="'+d.organOpinion+'" >查看机构意见</a>'
                }
                if (d.superiorOpinion){
                    _html += '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason2" title="'+d.superiorOpinion+'" >查看分管总意见</a>'
                }
                if (d.bossOpinion){
                    _html += '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason3" title="'+d.bossOpinion+'"  >查看总部意见</a>'
                }

            }
            if (roleCode == 'ceo-step' && ceoRole  == 'true'){
                _html = ''
                if (d.bossOpinion){
                    _html = '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason3" title="'+d.bossOpinion+'">查看总部意见</a>' +
                        '<a class=" layui-btn layui-btn-xs layui-btn-danger operateBtn1" lay-event="sendBack" style="display: none">填写总部意见</a>'
                }else {
                    _html = '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason3" style="display: none" title="'+d.bossOpinion+'">查看总部意见</a>' +
                        '<a class=" layui-btn layui-btn-xs layui-btn-danger operateBtn1" lay-event="sendBack">填写总部意见</a>'
                }

            }


            return _html
        }

        /****************************** 切换可编辑列表 -  角色: _cols1 行政主管，总部，_cols2 人事  _cols00 只读 *********************************/
        var _cols1 = [
            [
                {
                field: '',
                width: 50,
                title: '',
                fixed: 'left',
                rowspan: 2,
                type: 'numbers'
            },{
                field: 'realName',
                width: 100,
                title: '姓名',
                fixed: 'left',
                rowspan: 2,
                totalRowText: "合计:"
            },{
                title: '员工信息',
                colspan: 8,
                align: 'center',
            }, {
                title: '应发工资',
                colspan: 18,
                align: 'center'
            }, {
                title: '社保公积金',
                colspan: 17,
                align: 'center'
            },{
                field: 'grossPay',
                width: 120,
                title: '税前工资',
                sort: true,
                rowspan: 2,
                style: 'font-weight: bold',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.grossPay ? Number(d.grossPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'individualTax',
                width: 120,
                title: '个人所得税',
                sort: true,
                rowspan: 2,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.individualTax ? Number(d.individualTax).toLocaleString('en-US') : '';
                }
            }, {
                field: 'individualTaxChange',
                width: 120,
                title: '个税调整',
                sort: true,
                rowspan: 2,
                edit: 'text',
                templet: '#individualTaxChangeInput',totalRow: true
            },{
                field: 'realWages',
                width: 120,
                title: '实发工资',
                sort: true,
                rowspan: 2,
                style: 'font-weight: bold',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.realWages ? Number(d.realWages).toLocaleString('en-US') : '';
                }
            }, {
                field: 'type',
                width: 200,
                title: '操作',
                align: 'center',
                fixed: 'right',
                templet: function (d) {
                    return barDemo(d)
                }
            }],[ {
                field: 'socialSecurityCompany',
                width: 180,
                title: '社保缴纳公司',
            },{
                field: 'company',
                width: 180,
                title: '成本归属公司',
            },{
                field: 'organ',
                width: 150,
                title: '机构/部门',
            },{
                field: 'department',
                width: 130,
                title: '科室',
            },{
                field: 'idCard',
                width: 180,
                title: '身份证号',
            },{
                field: 'payAddress',
                width: 150,
                title: '社保公积金缴纳地',
                style: 'border-right-color:rgba(78, 183, 195, 0.8) ',
            },{
                field: 'entryTime',
                width: 120,
                title: '入职时间',
                templet: function (d) {
                    return formatDate(d.entryTime, 2)
                },
                sort: true
            },{
                field: 'jobPost',
                width: 120,
                title: '岗位',
            },{
                field: 'basePay',
                width: 110,
                title: '基本工资1',
                sort: true,
                totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.basePay ? Number(d.basePay).toLocaleString('en-US') : '';
                }
            },{
                field: 'realWorkingDays',
                width: 130,
                title: '本人应出勤天数',
                edit: 'text',
                templet: '#rateInput',
            },{
                field: 'workingDays',
                width: 130,
                title: '当月应上班天数',
                sort: true,
            },{
                field: 'rate',
                width: 90,
                title: '百分比',
                sort: true,
            }, {
                field: 'leaveMoney',
                width: 120,
                title: '事假扣款',
                sort: true,
                edit: 'text',
                templet: '#moneyInput3',
                totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.leaveMoney ? Number(d.leaveMoney).toLocaleString('en-US') : '';
                }
            },
                /*{
                field: 'sickLeaveTime',
                width: 120,
                title: '病假时长',
                sort: true,
                edit: 'text',
                templet: '#sickLeave',
            }, */
                {
                field: 'sickLeaveMoney',
                width: 120,
                title: '病假扣款',
                sort: true,
                edit: 'text',
                templet: '#moneyInput4',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sickLeaveMoney ? Number(d.sickLeaveMoney).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'officeSubsidies',
                width: 120,
                title: '补贴',
                sort: true,
                edit: 'text',
                templet: '#moneyInput_officeSubsidies',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.officeSubsidies ? Number(d.officeSubsidies).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'welfarePay',
                width: 130,
                title: '浮动绩效',
                sort: true,
                edit: 'text',
                templet: '#moneyInput161',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.welfarePay ? Number(d.welfarePay).toLocaleString('en-US') : '';
                    }
            },  {
                field: 'welfareRemark',
                width: 130,
                title: '浮动绩效备注',
                rowspan: 2,
                edit: 'text',
                templet: '#remarks1Input'
            },{
                    field: 'lateEarlyMoney',
                    width: 150,
                    title: '迟到/早退扣款',
                    sort: true,
                    edit: 'text',
                    templet: '#moneyInput',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.lateEarlyMoney ? Number(d.lateEarlyMoney).toLocaleString('en-US') : '';
                    }
                }, {
                    field: 'absenteeismMoney',
                    width: 140,
                    title: '旷工扣款',
                    sort: true,
                    edit: 'text',
                    templet: '#moneyInput2',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.absenteeismMoney ? Number(d.absenteeismMoney).toLocaleString('en-US') : '';
                    }
                },{
                    field: 'overtimePay',
                    width: 120,
                    title: '加班工资',
                    sort: true,
                    edit: 'text',
                    templet: '#moneyInput15',totalRow: true,
          templet: function(d) {
              // 添加千分位分隔符
              return d.overtimePay ? Number(d.overtimePay).toLocaleString('en-US') : '';
          }
                },{
                field: 'otherPay',
                width: 130,
                title: '其他补发',
                sort: true,
                edit: 'text',
                templet: '#moneyInput16',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.otherPay ? Number(d.otherPay).toLocaleString('en-US') : '';
                    }
            },  {
                field: 'remarks',
                width: 130,
                title: '其他补发备注',
                rowspan: 2,
                edit: 'text',
                templet: '#remarksInput'
            },{
                field: 'otherCutPay',
                width: 130,
                title: '其他扣款',
                sort: true,
                edit: 'text',
                templet: '#moneyInput162',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.otherCutPay ? Number(d.otherCutPay).toLocaleString('en-US') : '';
                    }
            },  {
                field: 'otherCutRemarks',
                width: 130,
                title: '其他扣款备注',
                rowspan: 2,
                edit: 'text',
                templet: '#remarks2Input'
            },{
                    field: 'quitCost',
                    width: 130,
                    title: '离职成本',
                    sort: true,
                    edit: 'text',
                    templet: '#quitCostInput',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.quitCost ? Number(d.quitCost).toLocaleString('en-US') : '';
                    }
                }, {
                field: 'wagesPaySub',
                width: 140,
                title: '应发工资小计',
                sort: true,
                style: 'border-right-color:rgba(101, 206, 114, 0.8);font-weight: bold ',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.wagesPaySub ? Number(d.wagesPaySub).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'conpanyFundMoney',
                width: 150,
                title: '公积金公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput5',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.conpanyFundMoney ? Number(d.conpanyFundMoney).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalFundMoney',
                width: 150,
                title: '公积金个人部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput6',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalFundMoney ? Number(d.personalFundMoney).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyPensionBenefits',
                width: 160,
                title: '养老保险公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput7',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyPensionBenefits ? Number(d.companyPensionBenefits).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalPensionBenefits',
                width: 160,
                title: '养老保险个人部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput8',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalPensionBenefits ? Number(d.personalPensionBenefits).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyMedicalInsurance',
                width: 160,
                title: '医疗保险公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput9',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyMedicalInsurance ? Number(d.companyMedicalInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalMedicalInsurance',
                width: 160,
                title: '医疗保险个人部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput10',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalMedicalInsurance ? Number(d.personalMedicalInsurance).toLocaleString('en-US') : '';
                    }
            },{
                field: 'companyUnemploymentInsurance',
                width: 190,
                title: '个人失业保险公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput11',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyUnemploymentInsurance ? Number(d.companyUnemploymentInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalUnemploymentInsurance',
                width: 190,
                title: '个人失业保险个人部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput12',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalUnemploymentInsurance ? Number(d.personalUnemploymentInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyBirthInsurance',
                width: 150,
                title: '生育险公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput13',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyBirthInsurance ? Number(d.companyBirthInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyInjuryInsurance',
                width: 160,
                title: '工伤保险公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput14',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyInjuryInsurance ? Number(d.companyInjuryInsurance).toLocaleString('en-US') : '';
                    }
            },  {
                field: 'companySickSubsidy',
                width: 160,
                title: '大病补助公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput17',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companySickSubsidy ? Number(d.companySickSubsidy).toLocaleString('en-US') : '';
                    }
            },{
                field: 'personalSickSubsidy',
                width: 160,
                title: '大病补助个人部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput18',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalSickSubsidy ? Number(d.personalSickSubsidy).toLocaleString('en-US') : '';
                    }
            },{
                field: 'disabilityInsurance',
                width: 100,
                title: '残保金',
                sort: true,
                edit: 'text',
                templet: '#moneyInput19',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.disabilityInsurance ? Number(d.disabilityInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'serviceFee',
                width: 100,
                title: '服务费',
                sort: true,
                edit: 'text',
                templet: '#moneyInput20',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.serviceFee ? Number(d.serviceFee).toLocaleString('en-US') : '';
                    }
            },{
                field: 'socialRemark',
                width: 130,
                title: '社保备注',
                edit: 'text',
                templet: '#moneyInput21'
            },{
                field: 'companyMoneySub',
                width: 210,
                title: '社保公积金公司部分小计',
                sort: true,
                style: 'font-weight: bold',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyMoneySub ? Number(d.companyMoneySub).toLocaleString('en-US') : '';
                    }

            },{
                field: 'personalMoneySub',
                width:210,
                title: '社保公积金个人部分小计',
                sort: true,
                style: 'border-right-color: rgba(236, 170, 62, 0.8);font-weight: bold',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalMoneySub ? Number(d.personalMoneySub).toLocaleString('en-US') : '';
                    }
            }
            ]
        ]
        var _cols3 = [
            [
                {
                    field: '',
                    width: 50,
                    title: '',
                    fixed: 'left',
                    rowspan: 2,
                    type: 'numbers'
                },{
                field: 'realName',
                width: 100,
                title: '姓名',
                fixed: 'left',
                rowspan: 2,
                totalRowText: "合计:"
            },{
                title: '员工信息',
                colspan: 8,
                align: 'center',
            }, {
                title: '应发工资',
                colspan: 18,
                align: 'center'
            }, {
                title: '社保公积金',
                colspan: 17,
                align: 'center'
            },{
                field: 'grossPay',
                width: 120,
                title: '税前工资',
                sort: true,
                rowspan: 2,
                style: 'font-weight: bold',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.grossPay ? Number(d.grossPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'individualTax',
                width: 120,
                title: '个人所得税',
                sort: true,
                rowspan: 2,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.individualTax ? Number(d.individualTax).toLocaleString('en-US') : '';
                }
            }, {
                field: 'individualTaxChange',
                width: 120,
                title: '个税调整',
                sort: true,
                rowspan: 2,
                edit: 'text',
                templet: '#individualTaxChangeInput',totalRow: true
            },{
                field: 'realWages',
                width: 120,
                title: '实发工资',
                sort: true,
                rowspan: 2,
                style: 'font-weight: bold',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.realWages ? Number(d.realWages).toLocaleString('en-US') : '';
                }
            }, {
                field: 'type',
                width: 160,
                title: '操作',
                align: 'center',
                fixed: 'right',
                templet: function (d) {
                    return barDemo(d)
                }
            }],[ {
                field: 'socialSecurityCompany',
                width: 180,
                title: '社保缴纳公司',
            },{
                field: 'company',
                width: 180,
                title: '成本归属公司',
            },{
                field: 'organ',
                width: 150,
                title: '机构/部门',
            },{
                field: 'department',
                width: 130,
                title: '科室',
            },{
                field: 'idCard',
                width: 180,
                title: '身份证号',
            },{
                field: 'payAddress',
                width: 150,
                title: '社保公积金缴纳地',
                style: 'border-right-color:rgba(78, 183, 195, 0.8) ',
            },{
                field: 'entryTime',
                width: 120,
                title: '入职时间',
                templet: function (d) {
                    return formatDate(d.entryTime, 2)
                },
                sort: true
            },{
                field: 'jobPost',
                width: 120,
                title: '岗位',
            },{
                field: 'basePay',
                width: 110,
                title: '基本工资2',
                sort: true,
                totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.basePay ? Number(d.basePay).toLocaleString('en-US') : '';
                }
            },{
                field: 'realWorkingDays',
                width: 130,
                title: '本人应出勤天数',
                edit: 'text',
                templet: '#rateInput',
            },{
                field: 'workingDays',
                width: 130,
                title: '当月应上班天数',
                sort: true,
            },{
                field: 'rate',
                width: 90,
                title: '百分比',
                sort: true,
            }, {
                field: 'leaveMoney',
                width: 120,
                title: '事假扣款',
                sort: true,
                edit: 'text',
                templet: '#moneyInput3',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.leaveMoney ? Number(d.leaveMoney).toLocaleString('en-US') : '';
                }
            },
                /*{
                field: 'sickLeaveTime',
                width: 120,
                title: '病假时长',
                sort: true,
                edit: 'text',
                templet: '#sickLeave',
            }, */
                {
                field: 'sickLeaveMoney',
                width: 120,
                title: '病假扣款',
                sort: true,
                edit: 'text',
                templet: '#moneyInput4',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sickLeaveMoney ? Number(d.sickLeaveMoney).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'officeSubsidies',
                width: 120,
                title: '补贴',
                sort: true,
                edit: 'text',
                templet: '#moneyInput_officeSubsidies',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.officeSubsidies ? Number(d.officeSubsidies).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'welfarePay',
                width: 130,
                title: '浮动绩效',
                sort: true,
                edit: 'text',
                templet: '#moneyInput161',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.welfarePay ? Number(d.welfarePay).toLocaleString('en-US') : '';
                    }
            },  {
                field: 'welfareRemark',
                width: 130,
                title: '浮动绩效备注',
                rowspan: 2,
                edit: 'text',
                templet: '#remarks1Input'
            },{
                    field: 'lateEarlyMoney',
                    width: 150,
                    title: '迟到/早退扣款',
                    sort: true,
                    edit: 'text',
                    templet: '#moneyInput',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.lateEarlyMoney ? Number(d.lateEarlyMoney).toLocaleString('en-US') : '';
                    }
                }, {
                    field: 'absenteeismMoney',
                    width: 140,
                    title: '旷工扣款',
                    sort: true,
                    edit: 'text',
                    templet: '#moneyInput2',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.absenteeismMoney ? Number(d.absenteeismMoney).toLocaleString('en-US') : '';
                    }
                },{
                    field: 'overtimePay',
                    width: 120,
                    title: '加班工资',
                    sort: true,
                    edit: 'text',
                    templet: '#moneyInput15',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.overtimePay ? Number(d.overtimePay).toLocaleString('en-US') : '';
                    }
                },{
                field: 'otherPay',
                width: 130,
                title: '其他补发',
                sort: true,
                edit: 'text',
                templet: '#moneyInput16',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.otherPay ? Number(d.otherPay).toLocaleString('en-US') : '';
                    }
            },  {
                field: 'remarks',
                width: 130,
                title: '其他补发备注',
                rowspan: 2,
                edit: 'text',
                templet: '#remarksInput'
            },{
                field: 'otherCutPay',
                width: 130,
                title: '其他扣款',
                sort: true,
                edit: 'text',
                templet: '#moneyInput162',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.otherCutPay ? Number(d.otherCutPay).toLocaleString('en-US') : '';
                    }
            },  {
                field: 'otherCutRemarks',
                width: 130,
                title: '其他扣款备注',
                rowspan: 2,
                edit: 'text',
                templet: '#remarks2Input'
            },{
                    field: 'quitCost',
                    width: 130,
                    title: '离职成本',
                    sort: true,
                    edit: 'text',
                    templet: '#quitCostInput',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.quitCost ? Number(d.quitCost).toLocaleString('en-US') : '';
                    }
                }, {
                field: 'wagesPaySub',
                width: 140,
                title: '应发工资小计',
                sort: true,
                style: 'border-right-color:rgba(101, 206, 114, 0.8);font-weight: bold ',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.wagesPaySub ? Number(d.wagesPaySub).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'conpanyFundMoney',
                width: 150,
                title: '公积金公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput5',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.conpanyFundMoney ? Number(d.conpanyFundMoney).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalFundMoney',
                width: 150,
                title: '公积金个人部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput6',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalFundMoney ? Number(d.personalFundMoney).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyPensionBenefits',
                width: 160,
                title: '养老保险公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput7',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyPensionBenefits ? Number(d.companyPensionBenefits).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalPensionBenefits',
                width: 160,
                title: '养老保险个人部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput8',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalPensionBenefits ? Number(d.personalPensionBenefits).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyMedicalInsurance',
                width: 160,
                title: '医疗保险公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput9',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyMedicalInsurance ? Number(d.companyMedicalInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalMedicalInsurance',
                width: 160,
                title: '医疗保险个人部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput10',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalMedicalInsurance ? Number(d.personalMedicalInsurance).toLocaleString('en-US') : '';
                    }
            },{
                field: 'companyUnemploymentInsurance',
                width: 190,
                title: '个人失业保险公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput11',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyUnemploymentInsurance ? Number(d.companyUnemploymentInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalUnemploymentInsurance',
                width: 190,
                title: '个人失业保险个人部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput12',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalUnemploymentInsurance ? Number(d.personalUnemploymentInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyBirthInsurance',
                width: 150,
                title: '生育险公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput13',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyBirthInsurance ? Number(d.companyBirthInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyInjuryInsurance',
                width: 160,
                title: '工伤保险公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput14',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyInjuryInsurance ? Number(d.companyInjuryInsurance).toLocaleString('en-US') : '';
                    }
            },  {
                field: 'companySickSubsidy',
                width: 160,
                title: '大病补助公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput17',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companySickSubsidy ? Number(d.companySickSubsidy).toLocaleString('en-US') : '';
                    }
            },{
                field: 'personalSickSubsidy',
                width: 160,
                title: '大病补助个人部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput18',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalSickSubsidy ? Number(d.personalSickSubsidy).toLocaleString('en-US') : '';
                    }
            },{
                field: 'disabilityInsurance',
                width: 100,
                title: '残保金',
                sort: true,
                edit: 'text',
                templet: '#moneyInput19',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.disabilityInsurance ? Number(d.disabilityInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'serviceFee',
                width: 100,
                title: '服务费',
                sort: true,
                edit: 'text',
                templet: '#moneyInput20',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.serviceFee ? Number(d.serviceFee).toLocaleString('en-US') : '';
                    }
            },{
                field: 'socialRemark',
                width: 130,
                title: '社保备注',
                edit: 'text',
                templet: '#moneyInput21'
            },{
                field: 'companyMoneySub',
                width: 210,
                title: '社保公积金公司部分小计',
                sort: true,
                style: 'font-weight: bold',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyMoneySub ? Number(d.companyMoneySub).toLocaleString('en-US') : '';
                    }

            },{
                field: 'personalMoneySub',
                width:210,
                title: '社保公积金个人部分小计',
                sort: true,
                style: 'border-right-color: rgba(236, 170, 62, 0.8);font-weight: bold',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalMoneySub ? Number(d.personalMoneySub).toLocaleString('en-US') : '';
                    }
            }
            ]
        ]
        var _cols2 = [
            [ {
                field: '',
                width: 50,
                title: '',
                fixed: 'left',
                rowspan: 2,
                type: 'numbers'
            },{
                field: 'realName',
                width: 100,
                title: '姓名',
                fixed: 'left',
                rowspan: 2,
                totalRowText: "合计:"
            },{
                title: '员工信息',
                colspan: 8,
                align: 'center',
            }, {
                title: '应发工资',
                colspan: 18,
                align: 'center'
            }, {
                title: '社保公积金',
                colspan: 17,
                align: 'center'
            },{
                field: 'grossPay',
                width: 120,
                title: '税前工资',
                sort: true,
                rowspan: 2,
                style: 'font-weight: bold',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.grossPay ? Number(d.grossPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'individualTax',
                width: 120,
                title: '个人所得税',
                sort: true,
                rowspan: 2,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.individualTax ? Number(d.individualTax).toLocaleString('en-US') : '';
                }
            }, {
                field: 'individualTaxChange',
                width: 120,
                title: '个税调整',
                sort: true,
                rowspan: 2,
                edit: 'text',
                templet: '#individualTaxChangeInput',totalRow: true
            },{
                field: 'realWages',
                width: 120,
                title: '实发工资',
                sort: true,
                rowspan: 2,
                style: 'font-weight: bold',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.realWages ? Number(d.realWages).toLocaleString('en-US') : '';
                }
            }, ],[{
                field: 'socialSecurityCompany',
                width: 180,
                title: '社保缴纳公司',
            },{
                field: 'company',
                width: 180,
                title: '成本归属公司',
            },{
                field: 'organ',
                width: 150,
                title: '机构/部门',
            },{
                field: 'department',
                width: 130,
                title: '科室',
            },{
                field: 'idCard',
                width: 180,
                title: '身份证号',
            },{
                field: 'payAddress',
                width: 150,
                title: '社保公积金缴纳地',
                style: 'border-right-color:rgba(78, 183, 195, 0.8) ',
            },{
                field: 'entryTime',
                width: 120,
                title: '入职时间',
                templet: function (d) {
                    return formatDate(d.entryTime, 2)
                },
                sort: true
            },{
                field: 'jobPost',
                width: 120,
                title: '岗位',
            },{
                field: 'basePay',
                width: 110,
                title: '基本工资3',
                sort: true,
                totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.basePay ? Number(d.basePay).toLocaleString('en-US') : '';
                }
            },{
                field: 'realWorkingDays',
                width: 130,
                title: '本人应出勤天数',
                edit: 'text',
                templet: '#rateInput',
            },{
                field: 'workingDays',
                width: 130,
                title: '当月应上班天数',
                sort: true,
            },{
                field: 'rate',
                width: 90,
                title: '百分比',
                sort: true,
            }, {
                field: 'leaveMoney',
                width: 120,
                title: '事假扣款',
                sort: true,
                edit: 'text',
                templet: '#moneyInput3',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.leaveMoney ? Number(d.leaveMoney).toLocaleString('en-US') : '';
                }
            },
                /*{
                field: 'sickLeaveTime',
                width: 120,
                title: '病假时长',
                sort: true,
                edit: 'text',
                templet: '#sickLeave',
            }, */
                {
                field: 'sickLeaveMoney',
                width: 120,
                title: '病假扣款',
                sort: true,
                edit: 'text',
                templet: '#moneyInput4',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sickLeaveMoney ? Number(d.sickLeaveMoney).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'officeSubsidies',
                width: 120,
                title: '补贴',
                sort: true,
                edit: 'text',
                templet: '#moneyInput_officeSubsidies',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.officeSubsidies ? Number(d.officeSubsidies).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'welfarePay',
                width: 130,
                title: '浮动绩效',
                sort: true,
                edit: 'text',
                templet: '#moneyInput161',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.welfarePay ? Number(d.welfarePay).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'welfareRemark',
                width: 130,
                title: '浮动绩效备注',
                rowspan: 2,
                edit: 'text',
                templet: '#remarks1Input'
            },{
                    field: 'lateEarlyMoney',
                    width: 150,
                    title: '迟到/早退扣款',
                    sort: true,
                    edit: 'text',
                    templet: '#moneyInput',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.lateEarlyMoney ? Number(d.lateEarlyMoney).toLocaleString('en-US') : '';
                    }
                }, {
                    field: 'absenteeismMoney',
                    width: 140,
                    title: '旷工扣款',
                    sort: true,
                    edit: 'text',
                    templet: '#moneyInput2',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.absenteeismMoney ? Number(d.absenteeismMoney).toLocaleString('en-US') : '';
                    }
                },{
                    field: 'overtimePay',
                    width: 120,
                    title: '加班工资',
                    sort: true,
                    edit: 'text',
                    templet: '#moneyInput15',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.overtimePay ? Number(d.overtimePay).toLocaleString('en-US') : '';
                    }
                }, {
                field: 'otherPay',
                width: 130,
                title: '其他补发',
                sort: true,
                edit: 'text',
                templet: '#moneyInput16',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.otherPay ? Number(d.otherPay).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'remarks',
                width: 130,
                title: '其他补发备注',
                rowspan: 2,
                edit: 'text',
                templet: '#remarksInput'
            }, {
                field: 'otherCutPay',
                width: 130,
                title: '其他扣款',
                sort: true,
                edit: 'text',
                templet: '#moneyInput162',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.otherCutPay ? Number(d.otherCutPay).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'otherCutRemarks',
                width: 130,
                title: '其他扣款备注',
                rowspan: 2,
                edit: 'text',
                templet: '#remarks2Input'
            }, {
                    field: 'quitCost',
                    width: 130,
                    title: '离职成本',
                    sort: true,
                    edit: 'text',
                    templet: '#quitCostInput',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.quitCost ? Number(d.quitCost).toLocaleString('en-US') : '';
                    }
                }, {
                field: 'wagesPaySub',
                width: 140,
                title: '应发工资小计',
                sort: true,
                style: 'border-right-color:rgba(101, 206, 114, 0.8);font-weight: bold ',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.wagesPaySub ? Number(d.wagesPaySub).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'conpanyFundMoney',
                width: 150,
                title: '公积金公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput5',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.conpanyFundMoney ? Number(d.conpanyFundMoney).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalFundMoney',
                width: 150,
                title: '公积金个人部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput6',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalFundMoney ? Number(d.personalFundMoney).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyPensionBenefits',
                width: 160,
                title: '养老保险公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput7',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyPensionBenefits ? Number(d.companyPensionBenefits).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalPensionBenefits',
                width: 160,
                title: '养老保险个人部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput8',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalPensionBenefits ? Number(d.personalPensionBenefits).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyMedicalInsurance',
                width: 160,
                title: '医疗保险公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput9',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyMedicalInsurance ? Number(d.companyMedicalInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalMedicalInsurance',
                width: 160,
                title: '医疗保险个人部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput10',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalMedicalInsurance ? Number(d.personalMedicalInsurance).toLocaleString('en-US') : '';
                    }
            },{
                field: 'companyUnemploymentInsurance',
                width: 190,
                title: '个人失业保险公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput11',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyUnemploymentInsurance ? Number(d.companyUnemploymentInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalUnemploymentInsurance',
                width: 190,
                title: '个人失业保险个人部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput12',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalUnemploymentInsurance ? Number(d.personalUnemploymentInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyBirthInsurance',
                width: 150,
                title: '生育险公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput13',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyBirthInsurance ? Number(d.companyBirthInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyInjuryInsurance',
                width: 160,
                title: '工伤保险公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput14',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyInjuryInsurance ? Number(d.companyInjuryInsurance).toLocaleString('en-US') : '';
                    }
            },  {
                field: 'companySickSubsidy',
                width: 160,
                title: '大病补助公司部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput17',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companySickSubsidy ? Number(d.companySickSubsidy).toLocaleString('en-US') : '';
                    }
            },{
                field: 'personalSickSubsidy',
                width: 160,
                title: '大病补助个人部分',
                sort: true,
                edit: 'text',
                templet: '#moneyInput18',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalSickSubsidy ? Number(d.personalSickSubsidy).toLocaleString('en-US') : '';
                    }
            },{
                field: 'disabilityInsurance',
                width: 100,
                title: '残保金',
                sort: true,
                edit: 'text',
                templet: '#moneyInput19',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.disabilityInsurance ? Number(d.disabilityInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'serviceFee',
                width: 100,
                title: '服务费',
                sort: true,
                edit: 'text',
                templet: '#moneyInput20',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.serviceFee ? Number(d.serviceFee).toLocaleString('en-US') : '';
                    }
            },{
                field: 'socialRemark',
                width: 130,
                title: '社保备注',
                edit: 'text',
                templet: '#moneyInput21'
            },{
                field: 'companyMoneySub',
                width: 210,
                title: '社保公积金公司部分小计',
                sort: true,
                style: 'font-weight: bold',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyMoneySub ? Number(d.companyMoneySub).toLocaleString('en-US') : '';
                    }

            },{
                field: 'personalMoneySub',
                width:210,
                title: '社保公积金个人部分小计',
                sort: true,
                style: 'border-right-color: rgba(236, 170, 62, 0.8);font-weight: bold',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalMoneySub ? Number(d.personalMoneySub).toLocaleString('en-US') : '';
                    }
            }
            ]
        ]
        var _cols00 = [
            [  {
                field: '',
                width: 50,
                title: '',
                fixed: 'left',
                rowspan: 2,
                type: 'numbers'
            },{
                field: 'realName',
                width: 100,
                title: '姓名',
                fixed: 'left',
                rowspan: 2,
                totalRowText: "合计:"
            },{
                title: '员工信息',
                colspan: 8,
                align: 'center'
            }, {
                title: '应发工资',
                colspan: 18,
                align: 'center'
            }, {
                title: '社保公积金',
                colspan: 17,
                align: 'center'
            }, {
                field: 'grossPay',
                width: 120,
                title: '税前工资',
                sort: true,
                rowspan: 2,
                style: 'font-weight: bold',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.grossPay ? Number(d.grossPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'individualTax',
                width: 120,
                title: '个人所得税',
                sort: true,
                rowspan: 2,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.individualTax ? Number(d.individualTax).toLocaleString('en-US') : '';
                }
            }, {
                field: 'individualTaxChange',
                width: 120,
                title: '个税调整',
                sort: true,
                rowspan: 2,totalRow: true
            },{
                field: 'realWages',
                width: 120,
                title: '实发工资',
                sort: true,
                rowspan: 2,
                style: 'font-weight: bold',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.realWages ? Number(d.realWages).toLocaleString('en-US') : '';
                }
            },],[{
                field: 'socialSecurityCompany',
                width: 180,
                title: '社保缴纳公司',
            },{
                field: 'company',
                width: 180,
                title: '成本归属公司',
            },{
                field: 'organ',
                width: 150,
                title: '机构/部门',
            },{
                field: 'department',
                width: 130,
                title: '科室',
            },{
                field: 'idCard',
                width: 180,
                title: '身份证号',
            },{
                field: 'payAddress',
                width: 150,
                title: '社保公积金缴纳地',
            },{
                field: 'entryTime',
                width: 120,
                title: '入职时间',
                templet: function (d) {
                    return formatDate(d.entryTime, 2)
                },
                sort: true
            },{
                field: 'jobPost',
                width: 120,
                title: '岗位',
            },{
                field: 'basePay',
                width: 110,
                title: '基本工资4',
                totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.basePay ? Number(d.basePay).toLocaleString('en-US') : '';
                }
            },{
                field: 'realWorkingDays',
                width: 130,
                title: '本人应出勤天数',
            },{
                field: 'workingDays',
                width: 130,
                title: '当月应上班天数',
                sort: true
            },{
                field: 'rate',
                width: 90,
                title: '百分比',
                sort: true
            }, {
                field: 'leaveMoney',
                width: 120,
                title: '事假扣款',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.leaveMoney ? Number(d.leaveMoney).toLocaleString('en-US') : '';
                }
            },
                /*{
                field: 'sickLeaveTime',
                width: 120,
                title: '病假时长',
                sort: true,
            }, */
                {
                field: 'sickLeaveMoney',
                width: 120,
                title: '病假扣款',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sickLeaveMoney ? Number(d.sickLeaveMoney).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'officeSubsidies',
                width: 120,
                title: '补贴',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.officeSubsidies ? Number(d.officeSubsidies).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'welfarePay',
                width: 130,
                title: '浮动绩效',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.welfarePay ? Number(d.welfarePay).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'welfareRemark',
                width: 130,
                title: '浮动绩效备注',
                rowspan: 2
            },{
                    field: 'lateEarlyMoney',
                    width: 150,
                    title: '迟到/早退扣款',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.lateEarlyMoney ? Number(d.lateEarlyMoney).toLocaleString('en-US') : '';
                    }
                }, {
                    field: 'absenteeismMoney',
                    width: 140,
                    title: '旷工扣款',
                    sort: true,
                    templet: '#moneyInput200',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.absenteeismMoney ? Number(d.absenteeismMoney).toLocaleString('en-US') : '';
                    }
                },{
                    field: 'overtimePay',
                    width: 120,
                    title: '加班工资',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.overtimePay ? Number(d.overtimePay).toLocaleString('en-US') : '';
                    }
                }, {
                field: 'otherPay',
                width: 130,
                title: '其他补发',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.otherPay ? Number(d.otherPay).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'remarks',
                width: 130,
                title: '其他补发备注',
                rowspan: 2
            }, {
                field: 'otherCutPay',
                width: 130,
                title: '其他扣款',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.otherCutPay ? Number(d.otherCutPay).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'otherCutRemarks',
                width: 130,
                title: '其他扣款备注',
                rowspan: 2
            }, {
                    field: 'quitCost',
                    width: 130,
                    title: '离职成本',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.quitCost ? Number(d.quitCost).toLocaleString('en-US') : '';
                    }
                },  {
                field: 'wagesPaySub',
                width: 140,
                title: '应发工资小计',
                sort: true,
                style: 'font-weight: bold',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.wagesPaySub ? Number(d.wagesPaySub).toLocaleString('en-US') : '';
                    }
            },{
                field: 'conpanyFundMoney',
                width: 150,
                title: '公积金公司部分',
                sort: true,
                templet: function(d) {
            // 添加千分位分隔符
            return d.conpanyFundMoney ? Number(d.conpanyFundMoney).toLocaleString('en-US') : '';
        }
            }, {
                field: 'personalFundMoney',
                width: 150,
                title: '公积金个人部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalFundMoney ? Number(d.personalFundMoney).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyPensionBenefits',
                width: 160,
                title: '养老保险公司部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyPensionBenefits ? Number(d.companyPensionBenefits).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalPensionBenefits',
                width: 160,
                title: '养老保险个人部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalPensionBenefits ? Number(d.personalPensionBenefits).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyMedicalInsurance',
                width: 160,
                title: '医疗保险公司部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyMedicalInsurance ? Number(d.companyMedicalInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalMedicalInsurance',
                width: 160,
                title: '医疗保险个人部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalMedicalInsurance ? Number(d.personalMedicalInsurance).toLocaleString('en-US') : '';
                    }
            },{
                field: 'companyUnemploymentInsurance',
                width: 190,
                title: '个人失业保险公司部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyUnemploymentInsurance ? Number(d.companyUnemploymentInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalUnemploymentInsurance',
                width: 190,
                title: '个人失业保险个人部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalUnemploymentInsurance ? Number(d.personalUnemploymentInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyBirthInsurance',
                width: 150,
                title: '生育险公司部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyBirthInsurance ? Number(d.companyBirthInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyInjuryInsurance',
                width: 160,
                title: '工伤保险公司部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyInjuryInsurance ? Number(d.companyInjuryInsurance).toLocaleString('en-US') : '';
                    }
            },
                {
                field: 'companySickSubsidy',
                width: 160,
                title: '大病补助公司部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companySickSubsidy ? Number(d.companySickSubsidy).toLocaleString('en-US') : '';
                    }
            },{
                field: 'personalSickSubsidy',
                width: 160,
                title: '大病补助个人部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalSickSubsidy ? Number(d.personalSickSubsidy).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'disabilityInsurance',
                width: 100,
                title: '残保金',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.disabilityInsurance ? Number(d.disabilityInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'serviceFee',
                width: 100,
                title: '服务费',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.serviceFee ? Number(d.serviceFee).toLocaleString('en-US') : '';
                    }
            },{
                field: 'socialRemark',
                width: 130,
                title: '社保备注',
            },{
                field: 'companyMoneySub',
                width: 210,
                title: '社保公积金公司部分小计',
                sort: true,
                style: 'font-weight: bold',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyMoneySub ? Number(d.companyMoneySub).toLocaleString('en-US') : '';
                    }
            },{
                field: 'personalMoneySub',
                width:210,
                title: '社保公积金个人部分小计',
                sort: true,
                style: 'font-weight: bold',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalMoneySub ? Number(d.personalMoneySub).toLocaleString('en-US') : '';
                    }
            }
            ]
        ]
        var _cols01 = [
            [  {
                field: '',
                width: 50,
                title: '',
                fixed: 'left',
                rowspan: 2,
                type: 'numbers'
            },{
                field: 'realName',
                width: 100,
                title: '姓名',
                fixed: 'left',
                rowspan: 2,
                totalRowText: "合计:"
            },{
                title: '员工信息',
                colspan: 8,
                align: 'center'
            }, {
                title: '应发工资',
                colspan: 18,
                align: 'center'
            }, {
                title: '社保公积金',
                colspan: 17,
                align: 'center'
            }, {
                field: 'grossPay',
                width: 120,
                title: '税前工资',
                sort: true,
                rowspan: 2,
                style: 'font-weight: bold',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.grossPay ? Number(d.grossPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'individualTax',
                width: 120,
                title: '个人所得税',
                sort: true,
                rowspan: 2,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.individualTax ? Number(d.individualTax).toLocaleString('en-US') : '';
                }
            }, {
                field: 'individualTaxChange',
                width: 120,
                title: '个税调整',
                sort: true,
                rowspan: 2,totalRow: true
            },{
                field: 'realWages',
                width: 120,
                title: '实发工资',
                sort: true,
                rowspan: 2,
                style: 'font-weight: bold',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.realWages ? Number(d.realWages).toLocaleString('en-US') : '';
                }
            },  {
                field: 'type',
                width: 200,
                title: '操作',
                align: 'center',
                fixed: 'right',
                templet: function (d) {
                    return barDemo(d)
                }
            }],[{
                field: 'socialSecurityCompany',
                width: 180,
                title: '社保缴纳公司',
            },{
                field: 'company',
                width: 180,
                title: '成本归属公司',
            },{
                field: 'organ',
                width: 150,
                title: '机构/部门',
            },{
                field: 'department',
                width: 130,
                title: '科室',
            },{
                field: 'idCard',
                width: 180,
                title: '身份证号',
            },{
                field: 'payAddress',
                width: 150,
                title: '社保公积金缴纳地',
            },{
                field: 'entryTime',
                width: 120,
                title: '入职时间',
                templet: function (d) {
                    return formatDate(d.entryTime, 2)
                },
                sort: true
            },{
                field: 'jobPost',
                width: 120,
                title: '岗位',
            },{
                field: 'basePay',
                width: 110,
                title: '基本工资5',
                totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.basePay ? Number(d.basePay).toLocaleString('en-US') : '';
                }
            },{
                field: 'realWorkingDays',
                width: 130,
                title: '本人应出勤天数',
            },{
                field: 'workingDays',
                width: 130,
                title: '当月应上班天数',
                sort: true
            },{
                field: 'rate',
                width: 90,
                title: '百分比',
                sort: true
            }, {
                field: 'leaveMoney',
                width: 120,
                title: '事假扣款',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.leaveMoney ? Number(d.leaveMoney).toLocaleString('en-US') : '';
                }
            },
                /*{
                field: 'sickLeaveTime',
                width: 120,
                title: '病假时长',
                sort: true,
            }, */
                {
                field: 'sickLeaveMoney',
                width: 120,
                title: '病假扣款',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sickLeaveMoney ? Number(d.sickLeaveMoney).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'officeSubsidies',
                width: 120,
                title: '补贴',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.officeSubsidies ? Number(d.officeSubsidies).toLocaleString('en-US') : '';
                    }
            },{
                field: 'welfarePay',
                width: 130,
                title: '浮动绩效',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.welfarePay ? Number(d.welfarePay).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'welfareRemark',
                width: 130,
                title: '浮动绩效备注',
                rowspan: 2
            },{
                    field: 'lateEarlyMoney',
                    width: 150,
                    title: '迟到/早退扣款',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.lateEarlyMoney ? Number(d.lateEarlyMoney).toLocaleString('en-US') : '';
                    }
                }, {
                    field: 'absenteeismMoney',
                    width: 140,
                    title: '旷工扣款',
                    sort: true,
                    templet: '#moneyInput200',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.absenteeismMoney ? Number(d.absenteeismMoney).toLocaleString('en-US') : '';
                    }
                },{
                    field: 'overtimePay',
                    width: 120,
                    title: '加班工资',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.overtimePay ? Number(d.overtimePay).toLocaleString('en-US') : '';
                    }
                }, {
                field: 'otherPay',
                width: 130,
                title: '其他补发',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.otherPay ? Number(d.otherPay).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'remarks',
                width: 130,
                title: '其他补发备注',
                rowspan: 2
            }, {
                field: 'otherCutPay',
                width: 130,
                title: '其他扣款',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.otherCutPay ? Number(d.otherCutPay).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'otherCutRemarks',
                width: 130,
                title: '其他扣款备注',
                rowspan: 2
            }, {
                    field: 'quitCost',
                    width: 130,
                    title: '离职成本',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.quitCost ? Number(d.quitCost).toLocaleString('en-US') : '';
                    }
                },{
                field: 'wagesPaySub',
                width: 140,
                title: '应发工资小计',
                sort: true,
                style: 'font-weight: bold',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.wagesPaySub ? Number(d.wagesPaySub).toLocaleString('en-US') : '';
                    }
            },{
                field: 'conpanyFundMoney',
                width: 150,
                title: '公积金公司部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.conpanyFundMoney ? Number(d.conpanyFundMoney).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalFundMoney',
                width: 150,
                title: '公积金个人部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalFundMoney ? Number(d.personalFundMoney).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyPensionBenefits',
                width: 160,
                title: '养老保险公司部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyPensionBenefits ? Number(d.companyPensionBenefits).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalPensionBenefits',
                width: 160,
                title: '养老保险个人部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalPensionBenefits ? Number(d.personalPensionBenefits).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyMedicalInsurance',
                width: 160,
                title: '医疗保险公司部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyMedicalInsurance ? Number(d.companyMedicalInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalMedicalInsurance',
                width: 160,
                title: '医疗保险个人部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalMedicalInsurance ? Number(d.personalMedicalInsurance).toLocaleString('en-US') : '';
                    }
            },{
                field: 'companyUnemploymentInsurance',
                width: 190,
                title: '个人失业保险公司部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyUnemploymentInsurance ? Number(d.companyUnemploymentInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'personalUnemploymentInsurance',
                width: 190,
                title: '个人失业保险个人部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalUnemploymentInsurance ? Number(d.personalUnemploymentInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyBirthInsurance',
                width: 150,
                title: '生育险公司部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyBirthInsurance ? Number(d.companyBirthInsurance).toLocaleString('en-US') : '';
                    }
            }, {
                field: 'companyInjuryInsurance',
                width: 160,
                title: '工伤保险公司部分',
                sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyInjuryInsurance ? Number(d.companyInjuryInsurance).toLocaleString('en-US') : '';
                    }
            },
                {
                    field: 'companySickSubsidy',
                    width: 160,
                    title: '大病补助公司部分',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companySickSubsidy ? Number(d.companySickSubsidy).toLocaleString('en-US') : '';
                    }
                },{
                    field: 'personalSickSubsidy',
                    width: 160,
                    title: '大病补助个人部分',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalSickSubsidy ? Number(d.personalSickSubsidy).toLocaleString('en-US') : '';
                    }
                }, {
                    field: 'disabilityInsurance',
                    width: 100,
                    title: '残保金',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.disabilityInsurance ? Number(d.disabilityInsurance).toLocaleString('en-US') : '';
                    }
                }, {
                    field: 'serviceFee',
                    width: 100,
                    title: '服务费',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.serviceFee ? Number(d.serviceFee).toLocaleString('en-US') : '';
                    }
                },{
                    field: 'socialRemark',
                    width: 130,
                    title: '社保备注',
                },{
                    field: 'companyMoneySub',
                    width: 210,
                    title: '社保公积金公司部分小计',
                    sort: true,
                    style: 'font-weight: bold',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.companyMoneySub ? Number(d.companyMoneySub).toLocaleString('en-US') : '';
                    }
                },{
                    field: 'personalMoneySub',
                    width:210,
                    title: '社保公积金个人部分小计',
                    sort: true,
                    style: 'font-weight: bold',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.personalMoneySub ? Number(d.personalMoneySub).toLocaleString('en-US') : '';
                    }
                }
            ]
        ]
        var _cols = _cols00

        if (roleCode != 'ceo-step' && roleCode != 'end-step' && hrManageRole == 'true'){
            _cols = _cols1
            if(backReason){
                _cols = _cols2
            }
        }else if (roleCode == 'ceo-step' && ceoRole == 'true'){
            _cols = _cols3
        }else if (roleCode == 'hr-step' && hrRole == 'true'){
            _cols = _cols2
        }else if ((roleCode == 'organManager-step' && organManagerRole == 'true') || (roleCode == 'superiorManager-step' && superiorManagerRole == 'true')){
            _cols = _cols01
        }else {
            _cols = _cols00
        }
        var _ajax = $.ajax
        var partArr = []

        var _h = $('.searchs').outerHeight() + $('.stepInfo').outerHeight()+ $('.fileData').outerHeight() + $('.sum_content').outerHeight() + $('.top_reason').outerHeight() + 80
        var fullH = 'full-' + _h
        var tableRender = {
            id: "test",
            elem: '#test',
            cols: _cols,
            page: false,
            limit: 10000,
            edit: 'text',
            cellMinWidth: 80,
            height: fullH,
            even: true,
            totalRow: true,
            done: function (res) {
                soulTable.render(this)
                if (!((roleCode == 'hr-step' && hrRole == 'true') || (roleCode == 'hrManage-step' && hrManageRole == 'true') || (roleCode == 'ceo-step' && ceoRole == 'true') || (roleCode == 'division-step' && division == 'true'))){
                    $('.layui-table-header table thead tr:first th').eq(3).css({
                        'background-color': 'rgba(101, 206, 114, 0.3)'
                    })

                    $('.layui-table-header table thead tr:first th').eq(4).css({
                        'border-right-color':'rgba(236, 170, 62, 0.3)',
                        'background-color':'rgba(236, 170, 62, 0.3)'
                    })

                    $('.layui-table-header table thead tr:first th').eq(5).css({
                        'background-color': 'rgba(230, 152, 152, 0.3)'
                    })
                }
                res.data.map(function (cur,i) {
                    if (cur.organOpinion ||cur.superiorOpinion || (cur.bossOpinion && (hrRole == 'true' || hrManageRole == 'true' || ceoRole == 'true'))){
                        $('.layui-table-main.layui-table-body table tbody tr').eq(i).addClass('active')
                        $('.layui-table-fixed-l .layui-table-body table tbody tr').eq(i).addClass('active')
                    }
                })
                var sum0 = 0,sum1 = 0,sum2 = 0,sum3 = 0,sum4 = 0

                var list = res.data
                list.map(function (cur,i) {
                    sum0 += cur.realWages || 0
                    sum1 += cur.wagesPaySub || 0
                    sum2 += cur.companyMoneySub || 0
                    sum3 += cur.personalMoneySub || 0
                    sum4 += cur.grossPay || 0
                })

                // $('.sum0 span').text(sum0.toFixed(2))
                // $('.sum1 span').text(sum1.toFixed(2))
                // $('.sum2 span').text(sum2.toFixed(2))
                // $('.sum3 span').text(sum3.toFixed(2))
                // $('.sum4 span').text(sum4.toFixed(2))
                $('.sum0 span').text(Number(sum0).toLocaleString('en-US'))
                $('.sum1 span').text(Number(sum1).toLocaleString('en-US'))  // 应发工资合计
                $('.sum2 span').text(Number(sum2).toLocaleString('en-US'))
                $('.sum3 span').text(Number(sum3).toLocaleString('en-US'))
                $('.sum4 span').text(Number(sum4).toLocaleString('en-US'))
            }
        }


        var editFlag = false

        $('.table_block').on('focus','.layui-table-edit', function () {
            $('.layui-table-header').css({
                'pointer-events': 'none'
            })
            $(this).select()
        })
        $('.table_block').on('blur','.layui-table-edit', function () {
            setTimeout(function () {
                $('.layui-table-header').css({
                    'pointer-events': 'auto'
                })
            },1000)
        })

        $('.table_block').on('mouseleave','td[data-edit=text]',function (e) {
            setTimeout(function () {
                // console.log(editFlag,'mouseleave',$('.table_block').find('.layui-table-edit').length )
                if (!editFlag && !$('.table_block').find('.layui-table-edit').length){
                    $('.layui-table-header').css({
                        'pointer-events': 'auto'
                    })
                }
            },1000)

        })


        /****************************** 初始化单元格 *********************************/
        var paramSubmit = {"dataCode":"slip","id":$("#id").val(),"roleCode":$("#roleCode").val()}
        initTable(paramSubmit)
        function initTable(param){
            $.ajax({
                url:'${ctx}/staff/getDetail',
                type:"post",
                data: param,
                success:function(res){
                    res = JSON.parse(res)
                    if (res.isSuccess){
                        var list = res.results.slips
                        if (roleCode == 'ceo-step' && ceoRole){
                            var rejectPart =$('.form-check .layui-btn[data-type="rejectPart"]')
                            var reject =$('.form-check .layui-btn[data-type="reject"]')
                            var checkTo =$('.form-check .layui-btn-normal')
                            list.map(function (cur,i) {
                                if (cur.bossOpinion){
                                    partArr.push(cur.jobNo)
                                }
                            })
                            if (partArr.length){
                                rejectPart.removeClass('lf-none')
                                reject.addClass('lf-none')
                                checkTo.addClass('lf-none')
                            }
                        }


                        Object.assign(tableRender,{
                            data: list
                        })
                        setTable(tableRender)
                        sessionStorage.setItem('gz', JSON.stringify(list))
                        sessionStorage.setItem('gzNew', JSON.stringify(list))
                    }else {
                        layer.msg(res.msg,{time:2000})
                    }

                }
            });
        }
        function setTable(tableRender){
            layer.closeAll()
            var myTable =  table.render(tableRender);
            /**
             * 监听单元格编辑
             * edit是固定事件名，
             * test是table原始容器的属性 lay-filter="对应的值"
             */
            table.on('edit(test)', function (obj) {
                editFlag = true
                var value = obj.value.replace(/\s+/g, ""), //得到修改后的值
                    data = obj.data, //得到所在行所有键值
                    field = obj.field, //得到字段
                    gz = JSON.parse(sessionStorage.getItem('gz')) || [],
                    gzNew = JSON.parse(sessionStorage.getItem('gzNew')) || []
                var fieldName = ''
                _cols[0].filter(function (cur,index) {
                    if (cur.field == field){
                        fieldName = cur.title
                    }
                })
                _cols[1].filter(function (cur,index) {
                    if (cur.field == field){
                        fieldName = cur.title
                    }
                })
                var oldValue = ''
                gz.forEach(function (cur, index) {
                    if (cur.jobNo === data.jobNo) {
                        oldValue = cur[field]
                    }
                })
                var flag = true
                if ((field == 'overtimePay' || field == 'sickLeaveTime'  || field == 'realWorkingDays' || field == 'officeSubsidies' || field == 'welfarePay' || field == 'otherPay' || field == 'quitCost') && value){//加班工资,病假时长,实际出勤天数,补贴,浮动绩效，其他补发 正数
                    flag = checkPapers('money', value)
                    if (!flag){
                        layer.msg('字段【' + fieldName + '】不符合规则，请输入小数点两位内的正数字',{
                            time: 2000,
                            icon: 2
                        })
                        obj.update({
                            [field]: oldValue
                        })
                        $(obj.tr[0]).children('td[data-field='+field+']').find('input').val(oldValue)
                        return;
                    }
                }else if ((field == 'individualTaxChange'
                    || field == 'conpanyFundMoney' || field == 'personalFundMoney' || field == 'companyPensionBenefits'
                    || field == 'personalPensionBenefits' || field == 'companyMedicalInsurance' || field == 'personalMedicalInsurance'
                    || field == 'companyUnemploymentInsurance' || field == 'personalUnemploymentInsurance' || field == 'companyBirthInsurance'
                    || field == 'companyInjuryInsurance' || field == 'companySickSubsidy' || field == 'personalSickSubsidy'
                    || field == 'disabilityInsurance' || field == 'serviceFee') && value){ //个税调整 公积金之类 正负数

                    flag = checkPapers('moneyorMinus', value)
                    if (!flag){
                        layer.msg('字段【' + fieldName + '】不符合规则，请输入小数点两位内的数字',{
                            time: 2000,
                            icon: 2
                        })
                        obj.update({
                            [field]: oldValue
                        })
                        $(obj.tr[0]).children('td[data-field='+field+']').find('input').val(oldValue)
                        return;
                    }
                }else if ((field == 'remarks' || field == 'socialRemark' || field == 'welfareRemark' || field == 'otherCutRemarks')&&value){// 备注

                }else if (field == 'conpanyFundMoney' || field == 'personalFundMoney'){
                    flag = checkPapers('integer', Math.abs(value))
                    if (!flag){
                        layer.msg('字段【' + fieldName + '】不符合规则，请输入整数',{
                            time: 2000,
                            icon: 2
                        })
                        obj.update({
                            [field]: oldValue
                        })
                        $(obj.tr[0]).children('td[data-field='+field+']').find('input').val(oldValue)
                        return;
                    }
                    if(Math.abs(value) != 0){
                        value = '-'+Math.abs(value)
                    }else {
                        value = 0
                    }
                }else if (value){// 大于0,两位小数
                    flag = checkPapers('moneyorMinus', value)
                    if (!flag){
                        layer.msg('字段【' + fieldName + '】不符合规则，请输入小数点两位内的数字',{
                            time: 2000,
                            icon: 2
                        })
                        obj.update({
                            [field]: oldValue
                        })
                        $(obj.tr[0]).children('td[data-field='+field+']').find('input').val(oldValue)
                        return;
                    }
                    if(Math.abs(value) != 0){
                        value = '-'+Math.abs(value)
                    }else {
                        value = 0
                    }
                }


                //请求后台，返回当条数据更新 修改值 及 涉及（实发工资）
                _ajax({
                    url:'${ctx}/staff/operate',
                    type:"post",
                    data :{
                        "btnCode":"slipItemSave",
                        "operateCode": 'slip',
                        "id":$("#id").val(),
                        "jobNo":data.jobNo,
                        "value":value,
                        "colCode": field
                    } ,
                    success:function(res){
                        res = JSON.parse(res)
                        if (res.isSuccess){
                            layer.msg('【'+fieldName+ ':' + value +'】 更新成功',
                                {  time:2000,
                                    icon: 1
                                });
                            var newValue = res.results.staffPayPersonnelSlip
                            obj.update(newValue)
                            var sum0 = 0,sum1 = 0,sum2 = 0,sum3 = 0,sum4 = 0
                            var gz2 = [], gzNew2 =[]

                            gz2 = gz.map(function (cur, index) {
                                if (cur.jobNo === data.jobNo) {
                                    cur = newValue
                                }
                                return cur
                            })
                            gzNew2 = gzNew.map(function (cur, index) {
                                if (cur.jobNo === data.jobNo) {
                                    cur = newValue
                                }
                                sum1 += cur.wagesPaySub || 0
                                sum2 += cur.companyMoneySub || 0
                                sum3 += cur.personalMoneySub || 0
                                sum4 += cur.grossPay || 0
                                sum0 += cur.realWages || 0
                                return cur
                            })

                            // $('.sum0 span').text(sum0.toFixed(2))
                            // $('.sum1 span').text(sum1.toFixed(2))
                            // $('.sum2 span').text(sum2.toFixed(2))
                            // $('.sum3 span').text(sum3.toFixed(2))
                            // $('.sum4 span').text(sum4.toFixed(2))
                            $('.sum0 span').text(Number(sum0).toLocaleString('en-US'))
                            $('.sum1 span').text(Number(sum1).toLocaleString('en-US'))  // 应发工资合计
                            $('.sum2 span').text(Number(sum2).toLocaleString('en-US'))
                            $('.sum3 span').text(Number(sum3).toLocaleString('en-US'))
                            $('.sum4 span').text(Number(sum4).toLocaleString('en-US'))

                            sessionStorage.setItem('gz', JSON.stringify(gz2))
                            sessionStorage.setItem('gzNew', JSON.stringify(gzNew2))
                        } else {
                            obj.update({
                                [field]: oldValue
                            })
                            layer.msg(res.msg,{time:2000,icon: 2});
                        }
                        $('.layui-table-header').css({
                            'pointer-events': 'auto'
                        })
                        editFlag = false
                    }
                });
            });

            table.on('tool(test)', function(obj){
                console.log(obj)
                if (obj.event == 'sendBack'){

                    openReasonEdit(obj, '')

                    // layer.prompt({title: '当前行【'+ obj.data.realName + '】 审核人意见',formType:2,btn: ['确定' ,'取消']},function (text,index) {
                    //     layer.close(index)
                    //     operatePerson({
                    //         roleCode: roleCode,
                    //         operateCode: 'slip',
                    //         btnCode:"back",
                    //         id: $('#id').val(),
                    //         staffPayPersonnelSlipId: obj.data.id,
                    //         backReason: text
                    //     },obj)
                    // })
                }else  if (obj.event == 'viewReason1'){
                    if (roleCode == 'organManager-step' && organManagerRole == 'true' && selfState == 0){
                        openReasonEdit(obj, 'organOpinion')
                    }else {
                        layer.confirm( ''+ obj.data.organOpinion +'', {
                            title: '员工【'+ obj.data.realName + '】工资条的审核意见',
                            btn: ['知道了'] //按钮
                        },function(index){
                            layer.close(index)
                        });
                    }

                }else  if (obj.event == 'viewReason2'){
                    if (roleCode == 'superiorManager-step' && superiorManagerRole == 'true' && selfState == 0){
                        openReasonEdit(obj, 'superiorOpinion')
                    }else {
                        layer.confirm( ''+ obj.data.superiorOpinion +'', {
                            title: '员工【'+ obj.data.realName + '】工资条的审核意见',
                            btn: ['知道了'] //按钮
                        },function(index){
                            layer.close(index)
                        });
                    }

                }else  if (obj.event == 'viewReason3'){
                    if (roleCode == 'ceo-step' && ceoRole == 'true'){
                        openReasonEdit(obj, 'bossOpinion')
                    }else {
                        layer.confirm( ''+ obj.data.bossOpinion +'', {
                            title: '员工【'+ obj.data.realName + '】工资条的审核意见',
                            btn: ['知道了'] //按钮
                        },function(index){
                            layer.close(index)
                        });
                    }

                }else if (obj.event == 'repeal'){
                    layer.confirm('确认撤销员工【'+obj.data.realName+'】工资条的审核意见？', {
                        btn: ['是','否'] //按钮
                    }, function(){
                        operatePerson({
                            roleCode: roleCode,
                            operateCode: 'slip',
                            btnCode:"removeBack",
                            id: $('#id').val(),
                            staffPayPersonnelSlipId: obj.data.id
                        },obj)
                    }, function(){

                    });

                }
            })

            function openReasonEdit(obj,field) {
                var reasonIndex = ''
                var title = ''
                if (field == 'organOpinion'){
                    title = '机构意见'
                } else if (field == 'superiorOpinion'){
                    title = '分管总意见'
                } else if (field == 'bossOpinion'){
                    title = '总部意见'
                } else {
                    title = '当前行【'+ obj.data.realName + '】  审核人意见'
                }

                reasonIndex = layer.open({
                    type: 1,
                    title: title,
                    area: ['500px', '300px'],
                    content: $('#view-or-edit').html()
                });
                $('textarea[name=reason-textarea]').val(obj.data[field])
                $('textarea[name=reason-textarea]').attr('placeholder', '仅当对此条数据中的内容有异议时，才需要填写意见，若无异议，请不要在此填写“同意”、“确认无误”等任何内容，空置即可。')
                $('.submit-reason').click(function () {
                    var backReason = $('textarea[name=reason-textarea]').val()
                    operatePerson({
                        roleCode: roleCode,
                        operateCode: 'slip',
                        btnCode:"back",
                        id: $('#id').val(),
                        staffPayPersonnelSlipId: obj.data.id,
                        backReason: backReason
                    },obj)

                    // if (backReason){
                    //
                    // }else {
                    //     operatePerson({
                    //         roleCode: roleCode,
                    //         operateCode: 'slip',
                    //         btnCode:"removeBack",
                    //         id: $('#id').val(),
                    //         staffPayPersonnelSlipId: obj.data.id
                    //     },obj)
                    // }

                    layer.close(reasonIndex)
                })
                $('.close-reason').click(function () {
                    layer.close(reasonIndex)
                })
            }

            function operatePerson(param,obj) {
                    _ajax({
                        url:'${ctx}/staff/operate',
                        type:"post",
                        data :param,
                        success:function(res){
                            res = JSON.parse(res)
                            $('.lf-import button').removeAttr('disabled')
                            if (res.isSuccess){
                                layer.msg('操作成功', {
                                    time: 2000,
                                    icon: 1
                                },function () {
                                    var newValue = res.results.staffPayPersonnelSlip

                                    var gzNew = JSON.parse(sessionStorage.getItem('gzNew')) || []
                                    var gz = JSON.parse(sessionStorage.getItem('gz')) || []
                                    var gz2 = [],gzNew2 = []
                                    var index = ''
                                    gzNew2 = gzNew.map(function (cur,i) {
                                        if (cur.jobNo == obj.data.jobNo){
                                            index = i;
                                            cur = newValue
                                        }
                                        return cur
                                    })

                                    gz2 = gz.map(function (cur,i) {
                                        if (cur.jobNo == obj.data.jobNo){
                                            cur = newValue
                                        }
                                        return cur
                                    })
                                    sessionStorage.setItem('gzNew', JSON.stringify(gzNew2))
                                    sessionStorage.setItem('gz', JSON.stringify(gz2))

                                    var rejectPart =$('.form-check .layui-btn[data-type="rejectPart"]')
                                    var reject =$('.form-check .layui-btn[data-type="reject"]')
                                    var checkTo =$('.form-check .layui-btn-normal')

                                    obj.update({
                                        organOpinion: newValue.organOpinion,
                                        superiorOpinion: newValue.superiorOpinion,
                                        bossOpinion: newValue.bossOpinion,
                                    })
                                    if (param.backReason){
                                        $('.layui-table-fixed-r .operateBtn1').eq(index).hide()
                                        $('.layui-table-fixed-r .operateBtn2').eq(index).show()
                                        // $('.layui-table-fixed-r .operateBtn3').eq(index).show()
                                        $('.layui-table-main.layui-table-body table tbody tr').eq(index).addClass('active')
                                        $('.layui-table-fixed-l .layui-table-body table tbody tr').eq(index).addClass('active')
                                    } else{
                                        $('.layui-table-fixed-r .operateBtn1').eq(index).show()
                                        $('.layui-table-fixed-r .operateBtn2').eq(index).hide()
                                        // $('.layui-table-fixed-r .operateBtn3').eq(index).hide()
                                        if (!newValue.organOpinion && !newValue.superiorOpinion && !newValue.bossOpinion) {
                                            $('.layui-table-main.layui-table-body table tbody tr').eq(index).removeClass('active')
                                            $('.layui-table-fixed-l .layui-table-body table tbody tr').eq(index).removeClass('active')
                                        }
                                    }
                                    if (roleCode == 'organManager-step' && ceoRole){
                                        $('.layui-table-fixed-r .operateBtn2').eq(index).attr('title',newValue.organOpinion)

                                    }
                                    if (roleCode == 'superiorManager-step' && ceoRole){
                                        $('.layui-table-fixed-r .operateBtn2').eq(index).attr('title',newValue.superiorOpinion)

                                    }
                                     if (roleCode == 'ceo-step' && ceoRole){
                                         if (param.backReason){
                                             if (partArr.indexOf(obj.data.jobNo) == -1){
                                                 partArr.push(obj.data.jobNo)
                                             }
                                             rejectPart.removeClass('lf-none')
                                             reject.addClass('lf-none')
                                             checkTo.addClass('lf-none')
                                         } else {
                                             var m =partArr.indexOf(obj.data.jobNo)
                                             partArr.splice(m,1)
                                             if (!partArr.length){
                                                 rejectPart.addClass('lf-none')
                                                 reject.removeClass('lf-none')
                                                 checkTo.removeClass('lf-none')
                                             }
                                         }
                                         $('.layui-table-fixed-r .operateBtn2').eq(index).attr('title',newValue.bossOpinion)
                                     }
                                })
                            } else{
                                layer.msg(res.msg, {
                                    time: 2000,
                                    icon: 2
                                })
                            }
                        }
                    });
                }
        }

        /****************************** 上传文件********************************/
        //指定允许上传的文件类型
        function _done(res) {

        }
        upload.render({
            elem: '#importOther',
            url: '${ctx}/staff/export', //改成您自己的上传接口
            data: {
                exportType: 'ddData',
                staffPaySlipId: $('#id').val()
            },
            accept: 'file', //普通文件
            exts: 'xlsx|xls', //只允许上传压缩文件
            before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                layer.load(); //上传loading
            },
            done: function (res) {
                layer.closeAll('loading')
                console.log(res)
                if (res.isSuccess){
                    // layer.msg(res.results.message,{time: 2000,icon:1})
                    layer.alert(res.message,{icon:1})
                    $('.lf-import-a a').eq(0).attr({
                        title: res.ddUrlName,
                        href: res.ddUrl
                    }).text(res.ddUrlName)
                    improtInit()
                } else {
                    // layer.msg(res.results.message,{time: 2000,icon:2})
                    layer.alert('导入出错',{icon:2})

                }



            },
        });
        upload.render({
            elem: '#importDD',
            url: '${ctx}/staff/export', //改成您自己的上传接口
            data: {
                exportType: 'ddData',
                staffPaySlipId: $('#id').val()
            },
            accept: 'file', //普通文件
            exts: 'xlsx|xls', //只允许上传压缩文件
            before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                console.log('before',obj)
                layer.load(); //上传loading

            },
            done: function (res) {
                layer.closeAll('loading')
                console.log(res)
                if (res.isSuccess){
                    // layer.msg(res.results.message,{time: 2000,icon:1})
                    layer.alert(res.results.message,{icon:1})
                    $('.lf-import-a a').eq(0).attr({
                        title: res.results.data.ddUrlName,
                        href: res.results.data.ddUrl
                    }).text(res.results.data.ddUrlName)
                    improtInit()
                } else {
                    // layer.msg(res.results.message,{time: 2000,icon:2})
                    layer.alert('导入出错',{icon:2})
                }
            },
            error: function (index, upload) {
                layer.closeAll('loading')
                console.log('error',index,upload)
            }
        });
        upload.render({
            elem: '#importJS',
            url: '${ctx}/staff/export', //改成您自己的上传接口
            data: {
                exportType: 'jsData',
                staffPaySlipId: $('#id').val()
            },
            accept: 'file', //普通文件
            exts: 'xlsx', //只允许上传压缩文件
            before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                console.log('before',obj)
                layer.load(); //上传loading

            },
            done: function (res) {
                layer.closeAll('loading')
                console.log(res)
                if (res.isSuccess){
                    // layer.msg(res.results.message,{time: 2000,icon:1})
                    layer.alert(res.message,{icon:1})

                    improtInit()
                } else {
                    // layer.msg(res.results.message,{time: 2000,icon:2})
                    layer.alert('导入出错',{icon:2})
                }
            },
            error: function (index, upload) {
                layer.closeAll('loading')
                console.log('error',index,upload)
            }
        });
        function improtInit() {
            $.ajax({
                url:'${ctx}/staff/getDetail',
                type:"post",
                data: {"dataCode":"slip","id":$("#id").val(),"roleCode":$("#roleCode").val()},
                success:function(res,param){
                    paramSubmit = {"dataCode":"slip","id":$("#id").val(),"roleCode":$("#roleCode").val()}
                    res = JSON.parse(res)
                    if(res.isSuccess){
                        var list = res.results.slips
                        var sum0 = 0,sum1 = 0,sum2 = 0,sum3 = 0,sum4 = 0
                        list.map(function (cur,i) {
                            sum1 += cur.wagesPaySub || 0
                            sum2 += cur.companyMoneySub || 0
                            sum3 += cur.personalMoneySub || 0
                            sum4 += cur.grossPay || 0
                            sum0 += cur.realWages || 0
                        })
                        // $('.sum0 span').text(sum0.toFixed(2))
                        // $('.sum1 span').text(sum1.toFixed(2))
                        // $('.sum2 span').text(sum2.toFixed(2))
                        // $('.sum3 span').text(sum3.toFixed(2))
                        // $('.sum4 span').text(sum4.toFixed(2))
                        $('.sum0 span').text(Number(sum0).toLocaleString('en-US'))
                        $('.sum1 span').text(Number(sum1).toLocaleString('en-US'))  // 应发工资合计
                        $('.sum2 span').text(Number(sum2).toLocaleString('en-US'))
                        $('.sum3 span').text(Number(sum3).toLocaleString('en-US'))
                        $('.sum4 span').text(Number(sum4).toLocaleString('en-US'))
                        Object.assign(tableRender,{data: res.results.slips})
                        reloadTable(tableRender)
                        sessionStorage.setItem('gz', JSON.stringify(res.results.slips))
                        sessionStorage.setItem('gzNew', JSON.stringify(res.results.slips))


                        sessionStorage.removeItem('sort_field')
                        var _html =" <option value=''>请选择</option>"
                        laydate.render({
                            elem: '#entryTimeStr',
                            value: '',
                            isInitValue: true,})
                        laydate.render({
                            elem: '#entryTimeEndStr',
                            value: '',
                            isInitValue: true,})
                        $('.searchs input[name=realName]').val('')
                        // $('.searchs input[name=jobNo]').val('')
                        // $('.searchs select[name=company]').html(_html)
                        // $('.searchs select[name=organ]').html(_html)
                        // $('.searchs select[name=department]').html(_html)
                        var param = {
                            businessUnit: '',
                            company: '',
                            scompany: '',
                            organ: '',
                            department: '',
                            // team: '',
                            // jobNo: '',
                            realName: '',
                            entryTime: ''
                        }
                        paramFocus = {
                            // jobNo: '',
                            realName: ''
                        }
                        sessionStorage.setItem('param',JSON.stringify(param))
                        form.val('search',param)
                    } else {
                        layer.msg(res.msg,{time:2000})
                    }
                }
            });
        }
        /****************************** 查询 重置*********************************/
        form.on('submit(submit)', function () {
            var _this = $('.ll-submit')
            _this.attr('disabled','disabled')
            setTimeout(function () {
                _this.removeAttr('disabled')
            },2000)
            layer.load()
            Object.assign(paramSubmit, {
                socialSecurityCompanyIds: demo0.getValue('valueStr'),
                companyIds: demo1.getValue('valueStr'),
                organIds: demo2.getValue('valueStr'),
                departmentIds: demo3.getValue('valueStr'),
                jobPostIds: demo4.getValue('valueStr'),
                // jobNo: $('input[name=jobNo]').val(),
                realName: $('input[name=realName]').val(),
                entryTime: $('#entryTimeStr').val(),
                entryTimeEnd: $('#entryTimeEndStr').val()
            })
            initTable(paramSubmit)
        });
        form.on('submit(reset)', function () {
            var _this = $('.ll-reset')
            _this.attr('disabled','disabled')
            setTimeout(function () {
                _this.removeAttr('disabled')
            },2000)
            layer.load()

            demo0.setValue([])
            demo1.setValue([])
            demo2.setValue([])
            demo3.setValue([])
            demo4.setValue([])
            // $('input[name=jobNo]').val('')
            $('input[name=realName]').val('')
            $('#entryTimeStr').val('')
            $('#entryTimeEndStr').val('')
            Object.assign(paramSubmit, {
                socialSecurityCompanyIds: '',
                companyIds: '',
                organIds: '',
                departmentIds: '',
                jobPostIds: '',
                // jobNo: '',
                realName: '',
                entryTime: '',
                entryTimeEnd: '',
            })
            initTable(paramSubmit)
        });

        //重载表格数据
        function reloadTable(_cols, param) {
            $('.table-content').empty()
            $('.table-content').append(
                ' <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg" style="margin: 0"></table>'
            )
            setTable(_cols, param)
        }

        $('#exportOther').on('click',function () {
            var aLink = document.createElement('a');
            aLink.href='${ctx}/staff/downLoad?surveyCode=ddDataModel'
                aLink.dispatchEvent(new MouseEvent('click', {
                    bubbles: true,
                    cancelable: true,
                    view: window
                }));
        })

        /****************************** 提交审核 相关 *********************************/
        $('.form-check').on('click', 'button', function(){
            var _this = $(this)
            var type = _this.attr('data-type')
            if (type == 'check_hr'){
                var pw = $('input[name=password]').val()
                if (!pw){
                    layer.msg('请输入查询密码',{
                        icon: 3
                    })
                }else if(pw.indexOf(' ') > -1){
                    layer.msg('密码不可含有空格',{
                        icon: 3
                    })
                }else {
                    operate({
                        operateCode: 'slip',
                        id:$('#id').val(),
                        btnCode:"hr-step",
                        password: pw
                    })
                }
            }else  if (type == 'check_organManager'){
                operate({
                    operateCode: 'slip',
                    id:$('#id').val(),
                    btnCode:"organManager-step",
                })

            }else  if (type == 'check_superiorManager'){
                operate({
                    operateCode: 'slip',
                    id:$('#id').val(),
                    btnCode:"superiorManager-step",
                })

            }else  if (type == 'check_hrManage'){
                operate({
                    operateCode: 'slip',
                    id:$('#id').val(),
                    btnCode:"hrManage-step",
                })

            }else  if (type == 'check_ceo'){
                operate({
                    operateCode: 'slip',
                    id:$('#id').val(),
                    btnCode:"ceo-step",
                    stepType: "yes"
                })
            }else if (type == 'reject'){
                layer.prompt({title:"请输入拒绝原因（必填项）",formType:2},function (text,index) {
                    layer.close(index)
                    operate({
                        operateCode: 'slip',
                        id:$('#id').val(),
                        btnCode:$('#roleCode').val(),
                        stepType: "no",
                        reason: text
                    })
                })
            }else if (type == 'rejectPart'){
                operate({
                    operateCode: 'slip',
                    id:$('#id').val(),
                    btnCode:$('#roleCode').val(),
                    stepType: "partReturn"
                })
            }
        })

        function  operate(data) {
            var mgsLIst = {}
            var _btnCode = data.btnCode
            if (data.stepType == 'yes' || !data.stepType){
                mgsLIst = {
                    'hr-step': '提交成功，待机构负责人审核',
                    'organManager-step': '提交成功，上级分管总审核',
                    'superiorManager-step': '提交成功，人事主管审核',
                    'hrManage-step': '提交成功，待总部审核',
                    'ceo-step': '工资条审核完成！',
                }
                layer.confirm('确认提交审核？', {
                    btn: ['是','否'] //按钮
                }, function(){
                    operate_ajax(data,mgsLIst[_btnCode])
                }, function(){

                });
            } else if(data.stepType == 'no'){
                mgsLIst = {
                    'ceo-step': '已驳回，待人事主管处理',
                }
                operate_ajax(data,mgsLIst[_btnCode])
            } else if (data.stepType == 'partReturn'){
                mgsLIst = {
                    'ceo-step': '已驳回，待人事主管处理',
                }
                layer.confirm('确认退回？', {
                    btn: ['是','否'] //按钮
                }, function(){
                    operate_ajax(data,mgsLIst[_btnCode])
                }, function(){

                });
            }

        }
        function operate_ajax(data,_msg){
            $('.layui-layer-dialog .layui-layer-btn0').addClass('po-none')
            _ajax({
                url:'${ctx}/staff/operate',
                type:"post",
                data :data,
                success:function(res){
                    res = JSON.parse(res)
                    $('.lf-import button').removeAttr('disabled')
                    if (res.isSuccess){
                        layer.msg(_msg, {
                            time: 2000,
                            icon: 1
                        },function () {
                            sessionStorage.removeItem('param')
                            sessionStorage.removeItem('gzNew')
                            sessionStorage.removeItem('sort_field')
                            closeDialogRefresh()
                        })
                        // Object.assign(tableRender,{cols: _cols4})
                        // reloadTable(tableRender)
                    } else{
                        layer.msg(res.msg, {
                            time: 2000,
                            icon: 2
                        })
                    }
                }
            });
        }

        /****************************** 导出 *********************************/
        $('.searchs .s-btn').on('click', function () {
            var _this = $(this)
            var type = _this.attr('data-type')
            if (type == 'export') {
                openIndex = layer.open({
                    type: 1,
                    title: '导出',
                    area: ['400px', '360px'],
                    content: $('#export-content').html(),
                });
                var exportDemo = xmSelect.render({
                    el: '#lf-export',
                    theme: {
                        color: '#3BA9FF',
                    },
                    radio: true,
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
                                            _html +=
                                                '<div class="xm-label-block lf-select-block">' + cur
                                                    .name + '</div>'
                                        })
                                        return _html
                                    }
                                }
                            },
                        }
                    },
                    data: [{
                        'value': 5,
                        'name': '按社保缴纳公司导出',
                        selected: true
                    }, {
                        'value': 1,
                        'name': '按成本归属公司导出'
                    }, {
                        'value': 3,
                        'name': '总表导出（不显示社保公积金公司部分）'
                    }, {
                        'value': 4,
                        'name': '总表导出（显示社保公积金公司部分）'
                    }]
                })
                $('.export-btn button').click(function () {
                    var _this = $(this)
                    if (_this.attr('data-type') == 'cancel') {
                        layer.close(openIndex)
                    } else if (_this.attr('data-type') == 'export') {
                        if (!exportDemo.getValue('valueStr')){
                            layer.msg( '请选择',{
                                time: 2000,
                                icon: 5
                            })
                            return ;
                        }
                        var param = {
                            socialSecurityCompanyIds: '',
                            companyIds: '',
                            organIds: '',
                            departmentIds: '',
                            jobPostIds: '',
                            jobNo: '',
                            realName: '',
                            entryTime: ''
                        }

                        Object.assign(param,paramSubmit)
                        var aLink = document.createElement('a');
                        aLink.href='${ctx}/staff/downLoad?surveyCode=paySlip&bookType=' + exportDemo.getValue('valueStr')+'&companyIds='+param.companyIds +'&socialSecurityCompanyId='+param.socialSecurityCompanyIds+'&organIds='+param.organIds+'&departmentIds='+param.departmentIds+'&jobPostIds='+param.jobPostIds+'&jobNo='+param.jobNo+'&realName='+param.realName+'&entryTime='+param.entryTime+'&staffPaySlipId='+${staffPaySlip.id}
                            console.log(aLink.href)
                        aLink.dispatchEvent(new MouseEvent('click', {
                            bubbles: true,
                            cancelable: true,
                            view: window
                        }));
                        layer.close(openIndex)
                    }
                })
            }
        })

        // 更新表格数据
        function searchByParam(key, value, paramNow){
            var param = {},
                gz = JSON.parse(sessionStorage.getItem('gz')),
                gzNew = '';
            if (paramNow){
                param = paramNow
            } else{
                param = JSON.parse(sessionStorage.getItem('param')) || {}
            }
            Object.assign(param, {
                [key]: value
            })
            if (!value && !param.realName && !param.scompany && !param.company && !param.organ && !param.department && !param.team && !param.jobNo && !param.entryTimeStr && !param.entryTimeEndStr) {
                gzNew = gz
            } else {
                gzNew = gz.filter(function (cur, index) {
                    var flag = true
                    if (param.realName) {
                        var str = cur.realName
                        var str2 = param.realName
                        if (str){
                            flag = flag && str.indexOf(str2) > -1
                        } else {
                            flag = false
                        }
                    }
                    // if (param.businessUnit) {
                    //     flag = flag && cur.businessUnitId == param.businessUnit
                    // }
                    if (param.scompany) {
                        flag = flag && cur.socialSecurityCompanyId  == param.scompany
                    }
                    if (param.company) {
                        flag = flag && cur.companyId  == param.company
                    }
                    if (param.organ) {
                        flag = flag && cur.organId  == param.organ
                    }
                    if (param.department) {
                        flag = flag && cur.departmentId  == param.department
                    }
                    if (param.team) {
                        flag = flag && cur.teamId  == param.team
                    }
                    // if (param.jobNo) {
                    //     var str = cur.jobNo
                    //     var str2 = param.jobNo
                    //     if (str){
                    //         flag = flag && str.indexOf(str2) > -1
                    //     } else {
                    //         flag = false
                    //     }
                    // }
                    var entryTimeStr = formatDate(cur.entryTime, 2)
                    var entryTimeEndStr = formatDate(cur.entryTime, 2)
                    if (param.entryTimeStr) {
                        flag = flag && entryTimeStr == param.entryTimeStr
                    }
                    if (param.entryTimeEndStr) {
                        flag = flag && entryTimeEndStr == param.entryTimeEndStr
                    }
                    return flag
                })
            }

            var sum0 = 0,sum1 = 0,sum2 = 0,sum3 = 0,sum4 = 0
            gzNew.map(function (cur,i) {
                if (cur.realWages){
                    sum1 += cur.wagesPaySub || 0
                    sum2 += cur.companyMoneySub || 0
                    sum3 += cur.personalMoneySub || 0
                    sum4 += cur.grossPay || 0
                    sum0 += cur.realWages || 0
                }
            })
            // $('.sum0 span').text(sum0.toFixed(2))
            // $('.sum1 span').text(sum1.toFixed(2))
            // $('.sum2 span').text(sum2.toFixed(2))
            // $('.sum3 span').text(sum3.toFixed(2))
            // $('.sum4 span').text(sum4.toFixed(2))
            $('.sum0 span').text(Number(sum0).toLocaleString('en-US'))
            $('.sum1 span').text(Number(sum1).toLocaleString('en-US'))  // 应发工资合计
            $('.sum2 span').text(Number(sum2).toLocaleString('en-US'))
            $('.sum3 span').text(Number(sum3).toLocaleString('en-US'))
            $('.sum4 span').text(Number(sum4).toLocaleString('en-US'))


            sessionStorage.setItem('param', JSON.stringify(param))
            sessionStorage.setItem('gzNew', JSON.stringify(gzNew))

            Object.assign(tableRender,{data: gzNew, cols: _cols})
            reloadTable(tableRender)
            <%--table.reload('test',{--%>
            <%--    url: '${ctx}/staff/getDetail',--%>
            <%--    method: 'post',--%>
            <%--    initSort: {--%>
            <%--        field: "basePay", //排序字段，对应 cols 设定的各字段名--%>
            <%--        type: "asc" //排序方式  asc: 升序、desc: 降序、null: 默认排序--%>
            <%--    },--%>
            <%--    cols: _cols4,--%>
            <%--    where: {"dataCode":"slip","id":$("#id").val()}--%>
            <%--},'data')--%>
        }

        //更新查询条件基础数据
        function  getParam(form,data, key) {
            $.ajax({
                url:'${ctx}/staff/selectStaffInfoByRelationId',
                type:"post",
                data :data,
                async: false,
                success:function(res){
                    res = JSON.parse(res)
                    console.log('------', res)
                    var _html =" <option value=''>请选择</option>"
                    if (key == 'businessUnit'){
                        res.results.map(function (cur,index) {
                            _html += "<option value='"+ cur.companyId+ "'>"+cur.companyName+"</option>"
                        })
                        $('.searchs select[name=company]').html(_html)
                    }else if (key == 'company'){
                        res.results.map(function (cur,index) {
                            _html += "<option value='"+ cur.organId+ "'>"+cur.organName+"</option>"
                        })
                        $('.searchs select[name=organ]').html(_html)
                    }else if (key == 'organ'){
                        res.results.map(function (cur,index) {
                            _html += "<option value='"+ cur.departmentId+ "'>"+cur.departmentName+"</option>"
                        })
                        $('.searchs select[name=department]').html(_html)
                    }else if (key == 'department'){
                        res.results.map(function (cur,index) {
                            _html += "<option value='"+ cur.teamId + "'>"+cur.teamName+"</option>"
                        })
                        $('.searchs select[name=team]').html(_html)
                    }
                    form.render('select', 'search');
                }
            });
        }

        //重载表格数据
        function reloadTable(tableRender) {
            $('.table_block').empty()
            $('.table_block').append(' <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg"></table>')
            // table.render(tableRender)
            setTable(tableRender)

        }
    })

    function formatDate(d, type) {
        if (!d) {
            return ''
        }
        var curDay = new Date(d),
            y = curDay.getFullYear(),
            m = PrefixInteger(curDay.getMonth() + 1, 2),
            d = PrefixInteger(curDay.getDate(), 2),
            hh = PrefixInteger(curDay.getHours(), 2),
            mm = PrefixInteger(curDay.getMinutes(), 2),
            ss = PrefixInteger(curDay.getSeconds(), 2)
        if (type == 1 || !type) {
            return y + '年' + m + '月' + d + '日'
        } else if (type == 2) {
            return y + '-' + m + '-' + d
        } else if (type == 3) {
            return y + '-' + m + '-' + d + ' ' + hh + ':' + mm + ':' + ss
        }
    }
    function PrefixInteger(num, m) {
        return (Array(m).join(0) + num).slice(-m);
    }
//正则匹配
var checkPapers = function (parama, paramb) {
    var map = new Map([
        ['phone', /^[1][3,4,5,7,8,9][0-9]{9}$/],
        ['passport', !/^((1[45]\d{7})|(G\d{8})|(P\d{7})|(S\d{7,8}))?$/],
        ['identity', /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/],
        ['money', /^[0-9]+(\.[0-9]{1,2})?$/],
        ['moneyorMinus', /^(\-|\+)?\d+(\.\d{1,2})?$/],
        ['integer', /^[0-9]\d*$/],
        ['email',/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/],
        ['num_0_1',/^(0+(\.[0-9]{1,2})?|1|1.0|1.00?)$/]
    ])

    if (map.get(parama).test(paramb)) {
        return true
    } else {
        return false
    }
}

</script>

<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>


<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

</body>
</html>