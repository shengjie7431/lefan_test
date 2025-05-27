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
        #dialogId{
            z-index: 198910170;
            position: fixed;
        }

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
            width: 100%;
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
            padding: 20px 0 10px 0;
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
        .sum{
            position: absolute;
            top: 0;
            right: 0;
            padding-right: 20px;
            text-align: right;
            font-size: 12px;
            font-family: Impact;
        }
        .sum span{
            font-size: 26px;
            font-weight: bold;
            color: #666;
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
        .table_block .layui-table-fixed-l table thead tr:first-of-type th:first-of-type,
        .table_block .layui-table-header table thead tr:first-of-type th:first-of-type,
        .table_block .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+2),
        .table_block .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+3){
            background-color: rgba(78, 183, 195, 0.3);
        }
        .table_block .layui-table-fixed-r table thead tr:first-of-type th:first-of-type{
            background-color: #f2f2f2;
        }

        .table_block .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+4){
            /*border-right-color:rgba(101, 206, 114, 0.3);*/
            background-color: rgba(101, 206, 114, 0.3);

        }

        .table_block .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+5){
            /*border-right-color:rgba(236, 170, 62, 0.3);*/
            background-color: rgba(236, 170, 62, 0.3);

        }

        .table_block .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+6){
            /*border-right-color:rgba(179, 157, 219, 0.3);*/
            background-color: rgba(151, 176, 221, 0.3);
        }
        .table_block .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+7){
            /*border-right-color:rgba(179, 157, 219, 0.3);*/
            background-color: rgba(179, 157, 219, 0.3);
        }


        .table_block .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+8),
        .table_block .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+9),
        .table_block .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+10),
        .table_block .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+11),
        .table_block .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+12),
        .table_block .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+13),
        .table_block .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+14){
            background-color: rgba(230, 152, 152, 0.3);
        }

        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 0),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 1),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 2),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 3),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 4),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 5),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 6),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 7),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 8),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 9),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 10),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 11),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 12),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 13),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 14)
        {
            /*border-right-color:rgba(78, 183, 195, 0.3);*/
            background-color: rgba(78, 183, 195, 0.3);

        }




        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 15),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 16),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 17),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 18),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 19),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 20)
        {
            /*border-right-color:rgba(101, 206, 114, 0.3);*/
            background-color: rgba(101, 206, 114, 0.3);

        }
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 21),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 22),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 23),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 24),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 25),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 26),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 27)

        {
            /*border-right-color:rgba(236, 170, 62, 0.3);*/
            background-color: rgba(236, 170, 62, 0.3);

        }


        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 28),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 29),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 30){
            /*border-right-color:rgba(179, 157, 219, 0.3);*/
            background-color: rgba(151, 176, 221, 0.3);
        }
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 31),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 32),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 33),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 34),
        .table_block  .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 35){
            /*border-right-color:rgba(179, 157, 219, 0.3);*/
            background-color: rgba(179, 157, 219, 0.3);
        }
        .layui-table-fixed-r .layui-table-cell{
            padding: 0;
        }

        .layui-btn-xs{
            border-radius: 2px;
        }

        .lf-none{
            display: none;
        }

        tr.active td{
            background-color: rgba(236,128,126,0.4);
        }

        tr.active2 td{
            background-color: rgba(254, 173, 59, 0.87)!important;
        }
        tr.active3 td{
            background-color: #81C784!important;
        }

        .layui-input-block{
            width: 90%;
            margin: 0 auto;
        }

        .export-btn{
            width: 90%;margin: 0 auto;text-align: center;display: flex;justify-content: center;
        }

        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }

        .user-tip{
            font-size: 13px;
            display: inline-block;
            width: auto;
            line-height: 40px;
        }

        .po-none{
            pointer-events: none;
        }

        .searchs .layui-input{
            height: 32px;
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

        .layui-table-header tr:last-of-type .layui-table-cell{
            display: flex;
            align-items: center;
        }

        .viewClock{
            display: inline-block;
            margin-left: 5px;
            padding: 2px;
            width:40px;
            height: 26px;
            cursor: pointer;
            color: #3ba9ff;
            background: url(data:image/png;base64,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);
            background-repeat: no-repeat;
            background-size: contain;
            background-position: center;
        }
        .yyi{
            margin-left: 10px;
            display: inline-block;
            width:20px;
            height: 20px;
            background: url(data:image/png;base64,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);
            background-repeat: no-repeat;
            background-size: contain;
            background-position: center;
        }

        .layui-table-main{
            overflow-y: overlay;
        }

        .scoreFlex{
            width: 100%;
            display: flex;
            align-items: center;
        }
    </style>
</head>
<body>
<div class="main">
    <c:if test="${staffPerformance.reason != null && (staffPerformance.hrManageRole || staffPerformance.ceoRole)}">
        <div class="top_reason">
            <div>驳回原因：${staffPerformance.reason}</div>
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
                <c:if test="${(staffPerformance.roleCode == 'hr-step' && staffPerformance.hrRole) || (staffPerformance.roleCode == 'hrManage-step' && staffPerformance.hrManageRole)}">
                    <button  class="s-btn layui-btn layui-btn-normal  layui-btn-sm layui-btn-radius" style="width: 86px" id="import">导入 <i class="layui-icon layui-icon-upload" ></i></button>
                </c:if>
                <c:if test="${(staffPerformance.roleCode == 'hr-step' && staffPerformance.hrRole) || (staffPerformance.hrManageRole) || (staffPerformance.roleCode == 'end-step' && staffPerformance.financeRole) || (staffPerformance.ceoRole)}">
                    <button  class="s-btn layui-btn layui-btn-normal  layui-btn-sm layui-btn-radius" style="width: 86px" data-type="export" id="export">导出 <i class="layui-icon layui-icon-export" ></i></button>
                </c:if>
            </div>
        </div>
    </div>

    <div class="table_block">
        <table class="layui-table" id="test" lay-filter="test" lay-data="{id: 'test'}" lay-skin="line" lay-size="lg" style="margin: 0">
        </table>
    </div>

    <div class="layui-form layui-form-submit" style="margin-top:20px;position:relative">
        <c:if test="${staffPerformance.roleCode == 'surveyUser-step'}">
            <div class="user-tip">
                若对于绩效有异议，请填写意见，保存即可，系统会在24小时之内自动提交到机构经理处，无须手动提交。
            </div>
        </c:if>
        <div class="layui-form-item password_block">
            <c:if test="${staffPerformance.roleCode == 'hr-step'}">
                <div class="layui-inline" style="margin-right: 20px;">
                    <label class="layui-form-label">设置查询密码</label>
                    <div class="layui-input-inline"  style="width: 120px;">
                        <input type="text" name="password" required placeholder="请输入密码"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
            </c:if>
            <div class="layui-inline form-check">
                <c:if test="${staffPerformance.roleCode == 'hr-step' && staffPerformance.hrRole}">
                    <button  class="layui-btn layui-btn-normal" data-type="check_hr">提交分管总一审
                    </button>
                </c:if>
                <c:if test="${staffPerformance.roleCode == 'superiorManager-first-step' && staffPerformance.superiorManagerRole && staffPerformance.staffPerformanceManager.state == 0}">
                    <button  class="layui-btn layui-btn-normal" data-type="check_superiorManager_1">提交机构负责人审核
                    </button>
                </c:if>

                <c:if test="${staffPerformance.roleCode == 'organManager-step' && staffPerformance.organManagerRole && staffPerformance.staffPerformanceManager.state == 0}">
                    <button  class="layui-btn layui-btn-normal" data-type="check_organManager">提交上级分管总审核（剩余${staffPerformance.timeRemaining}）
                    </button>
                </c:if>
                <c:if test="${staffPerformance.roleCode == 'superiorManager-step' && staffPerformance.superiorManagerRole && staffPerformance.staffPerformanceManager.state == 0}">
                    <button  class="layui-btn layui-btn-normal" data-type="check_superiorManager">提交人事主管处理
                    </button>
                </c:if>
                <c:if test="${staffPerformance.roleCode == 'hrManage-step' && staffPerformance.hrManageRole }">
                    <button  class="layui-btn layui-btn-normal" data-type="check_hrManage">提交总部审核
                    </button>
                </c:if>
                <c:if test="${staffPerformance.roleCode == 'ceo-step' && staffPerformance.ceoRole}">
                    <button  class="layui-btn layui-btn-primary" data-type="reject">全部退回</button>
                    <button  class="layui-btn layui-btn-primary lf-none" data-type="rejectPart">部分退回</button>
                    <button  class="layui-btn layui-btn-normal" data-type="check_ceo">审核通过</button>
                </c:if>
            </div>
        </div>
        <div class="sum"><i class="layui-icon layui-icon-rmb"></i> 合计（实发绩效）：<span></span></div>
    </div>
    <div style="display: none">
        <input type="hidden" id="id" value="${staffPerformance.id}" />
        <input type="hidden" id="performanceState" value="${staffPerformance.performanceState}" />
        <input type="hidden" id="roleCode" value="${staffPerformance.roleCode}" />
        <input type="hidden" id="hrRole" value="${staffPerformance.hrRole}" />
        <input type="hidden" id="surveyUserRole" value="${staffPerformance.surveyUserRole}" />
        <input type="hidden" id="organManagerRole" value="${staffPerformance.organManagerRole}" />
        <input type="hidden" id="superiorManagerRole" value="${staffPerformance.superiorManagerRole}" />
        <input type="hidden" id="hrManageRole" value="${staffPerformance.hrManageRole}"/>
        <input type="hidden" id="ceoRole" value="${staffPerformance.ceoRole}" />
        <input type="hidden" id="selfState" value="${staffPerformance.staffPerformanceManager.state}" />
        <input type="hidden" id="backState" value="${staffPerformance.backState}" />
        <input type="hidden" id="backReason" value="${staffPerformance.reason}" />


        <input type="hidden" value='${params.companysJson}' id="companysJson">
        <input type="hidden" value='${params.budgetCompanysJson}' id="budgetCompanysJson">
        <input type="hidden" value='${params.organsJson}' id="organsJson">
        <input type="hidden" value='${params.departmentsJson}' id="departmentsJson">
        <input type="hidden" value='${params.jobPostsJson}' id="jobPostsJson">
    </div>
</div>
<script type="text/html" id="moneyInput">
    {{#  if(d.rate == 'undefinded' || d.rate == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.rate}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput2">
    {{#  if(d.assessKpi == 'undefinded' || d.assessKpi == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.assessKpi}}</div>
    {{#  } }}
</script>
<script type="text/html" id="assesPerfPay">
    {{#  if(d.assesPerfPay == 'undefinded' || d.assesPerfPay == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.assesPerfPay}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput3">
    {{#  if(d.integral == 'undefinded' || d.integral == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.integral}}</div>
    {{#  } }}
</script>
<script type="text/html" id="sickLeave">
    {{#  if(d.integralPay == 'undefinded' || d.integralPay == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.integralPay}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput4">
    {{#  if(d.lateEarlyMoney == 'undefinded' || d.lateEarlyMoney == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.lateEarlyMoney}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput5">
    {{#  if(d.absenteeismMoney == 'undefinded' || d.absenteeismMoney == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td" style="width: 50%;display: inline-block;float: left">{{d.absenteeismMoney}}</div>
    {{#  if(d.jobPost == '调查员'){ }}
    <div class="viewClock" onclick="viewClock({{d.userId}},'{{d.workTime}}')"></div>
    {{#  } }}
    {{#  } }}
</script>
<script type="text/html" id="moneyInput500">
    {{#  if(d.absenteeismMoney == 'undefinded' || d.absenteeismMoney == null){ }}
    <input value="{{''}}" class="layui-input" style="border:none;background: none" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td" style="width: 50%;display: inline-block;float: left;border:none;background: none">{{d.absenteeismMoney}}</div>
    {{#  if(d.jobPost == '调查员'){ }}
    <div class="viewClock" onclick="viewClock({{d.userId}},'{{d.workTime}}')"></div>
    {{#  } }}
    {{#  } }}
</script>
<script type="text/html" id="moneyInput6">
    {{#  if(d.leaveMoney == 'undefinded' || d.leaveMoney == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.leaveMoney}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput7">
    {{#  if(d.sickLeaveTime == 'undefinded' || d.sickLeaveTime == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.sickLeaveTime}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput8">
    {{#  if(d.sickLeaveMoney == 'undefinded' || d.sickLeaveMoney == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.sickLeaveMoney}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput9">
    {{#  if(d.otherPay == 'undefinded' || d.otherPay == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.otherPay}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput10">
    {{#  if(d.realPay == 'undefinded' || d.realPay == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.realPay}}</div>
    {{#  } }}
</script>

<script type="text/html" id="moneyInput90">
    {{#  if(d.otherCutPay == 'undefinded' || d.otherCutPay == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.otherCutPay}}</div>
    {{#  } }}
</script>

<script type="text/html" id="moneyInput110">
    {{#  if(d.otherCutRemarks == 'undefinded' || d.otherCutRemarks == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td" title="{{d.otherCutRemarks}}">{{d.otherCutRemarks}}</div>
    {{#  } }}
</script>

<script type="text/html" id="moneyInput11">
    {{#  if(d.remarks == 'undefinded' || d.remarks == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td" title="{{d.remarks}}">{{d.remarks}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput12">
    {{#  if(d.welfarePay == 'undefinded' || d.welfarePay == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td" title="{{d.welfarePay}}">{{d.welfarePay}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput13">
    {{#  if(d.welfareRemark == 'undefinded' || d.welfareRemark == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td" title="{{d.welfareRemark}}">{{d.welfareRemark}}</div>
    {{#  } }}
</script>

<script type="text/html" id="moneyInput14">
    {{#  if(d.managePerfPaySize == 'undefinded' || d.managePerfPaySize == null ){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else if(d.manageCaseNum === '' || d.manageCaseNum === null) { }}
    <div class="nullClass" data-num="{{d.manageCaseNum}}" title="{{d.managePerfPaySize}}">{{d.managePerfPaySize}}</div>
    {{#  } else { }}
    <div class="layui-input layui-input-td" title="{{d.managePerfPaySize}}">{{d.managePerfPaySize}}</div>
    {{#  } }}
</script>

<script type="text/html" id="moneyInput141">
    {{#  if(d.managePerfPaySizeHz == 'undefinded' || d.managePerfPaySizeHz == null ){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else if(d.manageCaseNumHz === '' || d.manageCaseNumHz === null) { }}
    <div class="nullClass" data-num="{{d.manageCaseNumHz}}" title="{{d.managePerfPaySizeHz}}">{{d.managePerfPaySizeHz}}</div>
    {{#  } else { }}
    <div class="layui-input layui-input-td" title="{{d.managePerfPaySizeHz}}">{{d.managePerfPaySizeHz}}</div>
    {{#  } }}
</script>

<script type="text/html" id="moneyInput21">
    {{#  if(d.bsScore  == 'undefinded' || d.bsScore == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.bsScore}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput22">
    {{#  if(d.otherScore == 'undefinded' || d.otherScore == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.otherScore}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput23">
    {{#  if(d.sunScore == 'undefinded' || d.sunScore == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.sunScore}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput24">
    {{#  if(d.trafficSubsidy == 'undefinded' || d.trafficSubsidy == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.trafficSubsidy}}</div>
    {{#  } }}
</script>
<script type="text/html" id="moneyInput25">
    {{#  if(d.assesPerfPay == 'undefinded' || d.assesPerfPay == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.assesPerfPay}}</div>
    {{#  } }}
</script>

<script type="text/html" id="realWorkingDaysInput">
    {{#  if(d.realWorkingDays == 'undefinded' || d.realWorkingDays == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.realWorkingDays}}</div>
    {{#  } }}
</script>
<script type="text/html" id="isNewPeople">
    {{#  if(d.isNewPeople == 1){ }}
    <div>是</div>
    {{#  } else { }}
    <div>否</div>
    {{#  } }}
</script>

<script type="text/html" id="bsScoreHzInput">
    {{#  if(d.bsScoreHz == 'undefinded' || d.bsScoreHz == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.bsScoreHz}}</div>
    {{#  } }}
</script>
<script type="text/html" id="otherScoreHzInput">
    {{#  if(d.otherScoreHz == 'undefinded' || d.otherScoreHz == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.otherScoreHz}}</div>
    {{#  } }}
</script>
<script type="text/html" id="sunScoreHzInput">
    {{#  if(d.sunScoreHz == 'undefinded' || d.sunScoreHz == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.sunScoreHz}}</div>
    {{#  } }}
</script>
<script type="text/html" id="bsScoreBsInput">
    {{#  if(d.bsScoreBs == 'undefinded' || d.bsScoreBs == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.bsScoreBs}}</div>
    {{#  } }}
</script>
<script type="text/html" id="otherScoreBsInput">
    {{#  if(d.otherScoreBs == 'undefinded' || d.otherScoreBs == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.otherScoreBs}}</div>
    {{#  } }}
</script>
<script type="text/html" id="sunScoreBsInput">
    {{#  if(d.sunScoreBs == 'undefinded' || d.sunScoreBs == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.sunScoreBs}}</div>
    {{#  } }}
</script>


<script type="text/html" id="shjxxsInput">
    {{#  if(d.examineRate == 'undefinded' || d.examineRate == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td" title="{{d.examineRate}}">{{d.examineRate}}</div>
    {{#  } }}
</script>
<script type="text/html" id="shjxInput">
    {{#  if(d.examinePay == 'undefinded' || d.examinePay == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td" title="{{d.examinePay}}">{{d.examinePay}}</div>
    {{#  } }}
</script>


<script type="text/html" id="jsInput1">
    {{#  if(d.fixedPerfPayBase == 'undefinded' || d.fixedPerfPayBase == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.fixedPerfPayBase}}</div>
    {{#  } }}
</script>
<script type="text/html" id="jsInput2">
    {{#  if(d.travelAllowancePayBase == 'undefinded' || d.travelAllowancePayBase == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.travelAllowancePayBase}}</div>
    {{#  } }}
</script>
<script type="text/html" id="jsInput3">
    {{#  if(d.managePerfPayBase == 'undefinded' || d.managePerfPayBase == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.managePerfPayBase}}</div>
    {{#  } }}
</script>
<script type="text/html" id="jsInput4">
    {{#  if(d.assesPerfBasePay == 'undefinded' || d.assesPerfBasePay == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.assesPerfBasePay}}</div>
    {{#  } }}
</script>
<script type="text/html" id="caseSubMoneyInput">
    {{#  if(d.caseSubMoney == 'undefinded' || d.caseSubMoney == null){ }}
    <input value="{{''}}" class="layui-input" type="text">
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.caseSubMoney}}</div>
    {{#  } }}
</script>


<script type="text/html" id="socre-list-c">
    <div class="main">
        <div class="table-content-score">
            <table class="layui-table" id="socre-list" lay-filter="socre-list" lay-skin="line" lay-size="lg">
            </table>
        </div>
    </div>
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


<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src="${ctx}/js/layui/layui.js"></script>

<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        soulTable: 'soulTable',
        xmSelect: 'xm-select'
    })
    var $ = ''

    function viewClock(id,time) {
        var e = window.event || arguments.callee.caller.arguments[0];
        e.stopPropagation()
        var url = "${ctx}/fee/list?userId=" + id + "&createTime=" + time+"&menuCode=preClockDetails&orgId=''";
        parent.parent.parent.addTab("打卡足迹",url,true);
    }


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
        var surveyUserRole = $('#surveyUserRole').val()
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

            if (roleCode == 'surveyUser-step' && surveyUserRole == 'true'){
                _html = ''
                if (d.organOpinion){
                    _html = '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason1" title="'+d.organOpinion+'">查看意见</a>' +
                        '<a class=" layui-btn layui-btn-xs layui-btn-danger operateBtn1" lay-event="sendBack" style="display: none">填写意见</a>'

                }else {
                    _html = '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason1" style="display: none" title="'+d.organOpinion+'">查看意见</a>' +
                        '<a class=" layui-btn layui-btn-xs layui-btn-danger operateBtn1" lay-event="sendBack">填写意见</a>'
                }
            }

            if (roleCode != 'surveyUser-step' && surveyUserRole == 'true'){
                if ( d.organOpinion){
                    _html = '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason1" title="'+d.organOpinion+'">查看意见</a>'
                } else {
                    _html = ''
                }
            }

            if (roleCode == 'hr-step' && hrRole  == 'true'){

            }
            if (roleCode == 'organManager-step' && organManagerRole  == 'true'){
                _html = ''
                if (d.organOpinion){
                    _html = '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason1" title="'+d.organOpinion+'">查看机构意见</a>' +
                        '<a class=" layui-btn layui-btn-xs layui-btn-danger operateBtn1" lay-event="sendBack" style="display: none" >填写机构意见</a>'

                }else {
                    _html = '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason1" style="display: none" title="'+d.organOpinion+'">查看机构意见</a>' +
                        '<a class=" layui-btn layui-btn-xs layui-btn-danger operateBtn1" lay-event="sendBack">填写机构意见</a>'
                }
            }
            if ((selfState == 1 || roleCode != 'organManager-step') && organManagerRole  == 'true' && roleCode != 'surveyUser-step' ){
                if ( d.organOpinion){
                    _html = '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason1" title="'+d.organOpinion+'">查看机构意见</a>'
                } else {
                    _html = ''
                }
            }

            if (roleCode == 'superiorManager-step' && superiorManagerRole  == 'true'){
                _html = ''
                if (d.organOpinion){
                    _html = '<a class=" layui-btn layui-btn-xs operateBtn4" lay-event="viewReason1" title="'+d.organOpinion+'">查看机构意见</a>'
                }
                if (d.superiorOpinion){
                    _html += '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason2" title="'+d.superiorOpinion+'">查看分管总意见</a>' +
                        '<a class=" layui-btn layui-btn-xs layui-btn-danger operateBtn1" lay-event="sendBack" style="display: none">填写分管总意见</a>'
                }else {
                    _html += '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason2" style="display: none" title="'+d.superiorOpinion+'">查看分管总意见</a>' +
                        '<a class=" layui-btn layui-btn-xs layui-btn-danger operateBtn1" lay-event="sendBack">填写分管总意见</a>'
                }
            }

            if ((selfState == 1 || (roleCode != 'superiorManager-step' && roleCode != 'organManager-step')) && superiorManagerRole  == 'true' && roleCode != 'surveyUser-step'){
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
                    _html = '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason1" title="'+d.organOpinion+'">查看机构意见</a>'
                }
                if (d.superiorOpinion){
                    _html += '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason2" title="'+d.superiorOpinion+'">查看分管总意见</a>'
                }
                if (d.bossOpinion){
                    _html += '<a class=" layui-btn layui-btn-xs operateBtn2" lay-event="viewReason3" title="'+d.bossOpinion+'">查看总部意见</a>'
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

        /****************************** 切换可编辑列表 -  角色: _cols1 行政，_cols2 财务，_cols3 总部 _cols00 只读 *********************************/
        var _cols1 = [
            [{
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
                rowspan: 2,totalRowText: "合计:"
            },{
                title: '员工信息',
                colspan: 14,
                align: 'center',
            }, {
                title: '基础与浮动绩效',
                colspan: 6,
                align: 'center'
            }, {
                title: '调查绩效与补贴',
                colspan: 7,
                align: 'center'
            }, {
                title: '审核绩效与补贴',
                colspan: 3,
                align: 'center'
            }, {
                title: '人事扣款',
                colspan: 5,
                align: 'center'
            },{
                field: 'otherPay',
                width: 150,
                title: '其他补发',
                sort: true,
                rowspan: 2,
                edit: 'text',
                templet: '#moneyInput9',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.otherPay ? Number(d.otherPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'remarks',
                width: 190,
                title: '其他补发备注',
                rowspan: 2,
                edit: 'text',
                templet: '#moneyInput11'
            },{
                field: 'otherCutPay',
                width: 150,
                title: '其他扣款',
                sort: true,
                rowspan: 2,
                edit: 'text',
                templet: '#moneyInput90',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.otherCutPay ? Number(d.otherCutPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'otherCutRemarks',
                width: 190,
                title: '其他扣款备注',
                rowspan: 2,
                edit: 'text',
                templet: '#moneyInput110'
            }, {
                field: 'realPay',
                width: 160,
                title: '实发绩效',
                sort: true,
                rowspan: 2,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.realPay ? Number(d.realPay).toLocaleString('en-US') : '';
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
            }],
            [{
                field: 'socialSecurityCompany',
                width: 200,
                title: '社保缴纳公司',
            },{
                field: 'company',
                width: 200,
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
                field: 'jobPost',
                width: 135,
                title: '岗位',
            },{
                field: 'entryTimeStr',
                width: 130,
                title: '入职时间',
                sort : true
            },{
                field: 'fixedPerfPayBase',
                width: 130,
                title: '固定绩效基数',
                sort: true,
                edit: 'text',
                templet: '#jsInput1',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.fixedPerfPayBase ? Number(d.fixedPerfPayBase).toLocaleString('en-US') : '';
                }
            },{
                field: 'travelAllowancePayBase',
                width: 130,
                title: '驻外补贴基数',
                sort: true,
                edit: 'text',
                templet: '#jsInput2',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.travelAllowancePayBase ? Number(d.travelAllowancePayBase).toLocaleString('en-US') : '';
                }
            },{
                field: 'managePerfPayBase',
                width: 160,
                title: '岗位津贴基数',
                sort: true,
                edit: 'text',
                templet: '#jsInput3',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.managePerfPayBase ? Number(d.managePerfPayBase).toLocaleString('en-US') : '';
                }
            },{
                field: 'assesPerfBasePay',
                width: 160,
                title: '考核绩效基数',
                sort: true,
                edit: 'text',
                templet: '#jsInput4',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.assesPerfBasePay ? Number(d.assesPerfBasePay).toLocaleString('en-US') : '';
                }
            },{
                field: 'basicIntegral',
                width: 120,
                title: '基础积分',
                sort: true,totalRow: true
            },
                {
                    field: 'realWorkingDays',
                    width: 150,
                    title: '实际出勤天数',
                    sort: true,
                    edit: 'text',
                    templet: '#realWorkingDaysInput',
                },{
                field: 'workingDays',
                width: 160,
                title: '当月应上班天数',
                sort: true,
            },{
                field: 'rate',
                width: 130,
                title: '百分比',
                sort: true,
            }, {
                field: 'fixedPerfPay',
                width: 150,
                title: '实发固定绩效',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.fixedPerfPay ? Number(d.fixedPerfPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'travelAllowancePay',
                width: 150,
                title: '实发驻外补贴',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.travelAllowancePay ? Number(d.travelAllowancePay).toLocaleString('en-US') : '';
                }
            },{
                field: 'managePerfPay',
                width: 150,
                title: '实发岗位津贴',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.managePerfPay ? Number(d.managePerfPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'assesPerfPay',
                width: 180,
                title: '综合考核绩效基数',
                sort: true,
                edit: 'text',
               templet: '#moneyInput25',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.assesPerfPay ? Number(d.assesPerfPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'assessKpi',
                width: 150,
                title: '绩效考核系数',
                sort: true,
                edit: 'text',
                templet: '#moneyInput2',totalRow: true
            },{
                field: 'realAssessKpi',
                width: 150,
                title: '实际考核绩效',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.realAssessKpi ? Number(d.realAssessKpi).toLocaleString('en-US') : '';
                }
            },
                {
                    field: 'scoreHz',
                    width: 160,
                    title: '互助调查积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    // event: 'hzScore1',
                    templet: function (d) {
                        var scoreHz = d.scoreHz || '0'
                        var _html = '<div lay-event="hzScore1"  class="scoreFlex"><span style="color:#3BA9FF">'+scoreHz+'</span>'
                        if (d.hzStaffOpinionState == '0'){
                            _html += '<span class="yyi"></span>'

                        }
                        _html += '</div>'
                        return _html
                    },totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.scoreHz ? Number(d.scoreHz).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'sunScoreHz',
                    width: 160,
                    title: '互助阳性积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    event: 'hzScore2',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sunScoreHz ? Number(d.sunScoreHz).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'scoreBs',
                    width: 160,
                    title: '保司调查积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    // event: 'bsScore1'
                    templet: function (d) {
                        var scoreBs = d.scoreBs || '0'
                        var _html = '<div lay-event="bsScore1"  class="scoreFlex"><span style="color:#3BA9FF">'+scoreBs+'</span>'
                        if (d.bsStaffOpinionState == '0'){
                            _html += '<span class="yyi"></span>'

                        }
                        _html += '</div>'
                        return _html
                    },totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.scoreBs ? Number(d.scoreBs).toLocaleString('en-US') : '';
                    }
                },
                /*{
                    field: 'sunScoreBs',
                    width: 160,
                    title: '保司阳性积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    event: 'bsScore2'
                },*/
                {
                    field: 'sunMoneyBs',
                    width: 160,
                    title: '保司阳性奖励',
                    sort: true,
                    style: 'color:#3BA9FF',
                    event: 'bsScore2',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sunMoneyBs ? Number(d.sunMoneyBs).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'caseSubMoney',
                    width: 160,
                    title: '个案减损奖励',
                    sort: true,
                    edit: 'text',
                    templet: '#caseSubMoneyInput',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.caseSubMoney ? Number(d.caseSubMoney).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'monthBasicIntegral',
                    width: 170,
                    title: '实际基础积分',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.monthBasicIntegral ? Number(d.monthBasicIntegral).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'integralPay',
                    width: 120,
                    title: '积分绩效',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.integralPay ? Number(d.integralPay).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'examineScore',
                    width: 140,
                    event:'examineCaseNum',
                    style: 'color:#3BA9FF',
                    title: '审核总积分',
                    sort: true,totalRow: true
                },
                {
                    field: 'examineRate',
                    width: 120,
                    title: '审核绩效系数',
                    sort: true,
                    edit: 'text',
                    templet: '#shjxxsInput',totalRow: true
                },
                {
                    field: 'examinePay',
                    width: 120,
                    title: '审核绩效',
                    sort: true,
                    edit: 'text',
                    templet: '#shjxInput',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.examinePay ? Number(d.examinePay).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'lateEarlyMoney',
                    width: 150,
                    title: '迟到/早退扣款',
                    sort: true,
                    edit: 'text',
                    templet: '#moneyInput4',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.lateEarlyMoney ? Number(d.lateEarlyMoney).toLocaleString('en-US') : '';
                    }
                }, {
                field: 'absenteeismMoney',
                width: 150,
                title: '旷工扣款',
                sort: true,
                edit: 'text',
                templet: '#moneyInput5',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.absenteeismMoney ? Number(d.absenteeismMoney).toLocaleString('en-US') : '';
                }
            }, {
                field: 'leaveMoney',
                width: 150,
                title: '事假扣款',
                sort: true,
                edit: 'text',
                templet: '#moneyInput6',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.leaveMoney ? Number(d.leaveMoney).toLocaleString('en-US') : '';
                }
            },
                /*{
                field: 'sickLeaveTime',
                width: 140,
                title: '病假时长',
                sort: true,
                edit: 'text',
                templet: '#moneyInput7'
            }, */
                {
                    field: 'sickLeaveMoney',
                    width: 150,
                    title: '病假扣款',
                    sort: true,
                    edit: 'text',
                    templet: '#moneyInput8',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sickLeaveMoney ? Number(d.sickLeaveMoney).toLocaleString('en-US') : '';
                    }
                }
            ]
        ]
        var _cols3 = [
            [{
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
                rowspan: 2,totalRowText: "合计:"
            },{
                title: '员工信息',
                colspan: 14,
                align: 'center',
            }, {
                title: '基础与浮动绩效',
                colspan: 6,
                align: 'center'
            }, {
                title: '调查绩效与补贴',
                colspan: 7,
                align: 'center'
            },{
                title: '审核绩效与补贴',
                colspan: 3,
                align: 'center'
            }, {
                title: '人事扣款',
                colspan: 5,
                align: 'center'
            },{
                field: 'otherPay',
                width: 150,
                title: '其他补发',
                sort: true,
                rowspan: 2,
                edit: 'text',
                templet: '#moneyInput9',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.otherPay ? Number(d.otherPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'remarks',
                width: 190,
                title: '其他补发备注',
                rowspan: 2,
                edit: 'text',
                templet: '#moneyInput11'
            },{
                field: 'otherCutPay',
                width: 150,
                title: '其他扣款',
                sort: true,
                rowspan: 2,
                edit: 'text',
                templet: '#moneyInput90',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.otherCutPay ? Number(d.otherCutPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'otherCutRemarks',
                width: 190,
                title: '其他扣款备注',
                rowspan: 2,
                edit: 'text',
                templet: '#moneyInput110'
            }, {
                field: 'realPay',
                width: 160,
                title: '实发绩效',
                sort: true,
                rowspan: 2,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.realPay ? Number(d.realPay).toLocaleString('en-US') : '';
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
            }],
            [{
                field: 'socialSecurityCompany',
                width: 200,
                title: '社保缴纳公司',
            },{
                field: 'company',
                width: 200,
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
                field: 'jobPost',
                width: 135,
                title: '岗位',
            },{
                field: 'entryTimeStr',
                width: 130,
                title: '入职时间',
                sort : true
            },{
                field: 'fixedPerfPayBase',
                width: 130,
                title: '固定绩效基数',
                sort: true,
                edit: 'text',

                templet: '#jsInput1',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.fixedPerfPayBase ? Number(d.fixedPerfPayBase).toLocaleString('en-US') : '';
                }
            },{
                field: 'travelAllowancePayBase',
                width: 130,
                title: '驻外补贴基数',
                sort: true,
                edit: 'text',

                templet: '#jsInput2',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.travelAllowancePayBase ? Number(d.travelAllowancePayBase).toLocaleString('en-US') : '';
                }

            },{
                field: 'managePerfPayBase',
                width: 160,
                title: '岗位津贴基数',
                sort: true,
                edit: 'text',

                templet: '#jsInput3',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.managePerfPayBase ? Number(d.managePerfPayBase).toLocaleString('en-US') : '';
                }

            },{
                field: 'assesPerfBasePay',
                width: 160,
                title: '考核绩效基数',
                sort: true,
                edit: 'text',

                templet: '#jsInput4',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.assesPerfBasePay ? Number(d.assesPerfBasePay).toLocaleString('en-US') : '';
                }
            },{
                field: 'basicIntegral',
                width: 120,
                title: '基础积分',
                sort: true,totalRow: true

            },
                {
                    field: 'realWorkingDays',
                    width: 150,
                    title: '实际出勤天数',
                    sort: true,
                    edit: 'text',
                    templet: '#realWorkingDaysInput',
                },{
                field: 'workingDays',
                width: 160,
                title: '当月应上班天数',
                sort: true,
            },{
                field: 'rate',
                width: 130,
                title: '百分比',
                sort: true,
            }, {
                field: 'fixedPerfPay',
                width: 150,
                title: '实发固定绩效',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.fixedPerfPay ? Number(d.fixedPerfPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'travelAllowancePay',
                width: 150,
                title: '实发驻外补贴',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.travelAllowancePay ? Number(d.travelAllowancePay).toLocaleString('en-US') : '';
                }
            },{
                field: 'managePerfPay',
                width: 150,
                title: '实发岗位津贴',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.managePerfPay ? Number(d.managePerfPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'assesPerfPay',
                width: 180,
                title: '综合考核绩效基数',
                sort: true,
                edit: 'text',
               templet: '#moneyInput25',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.assesPerfPay ? Number(d.assesPerfPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'assessKpi',
                width: 150,
                title: '绩效考核系数',
                sort: true,
                edit: 'text',
                templet: '#moneyInput2',totalRow: true
            },{
                field: 'realAssessKpi',
                width: 150,
                title: '实际考核绩效',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.realAssessKpi ? Number(d.realAssessKpi).toLocaleString('en-US') : '';
                }
            },
                {
                    field: 'scoreHz',
                    width: 160,
                    title: '互助调查积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    // event: 'hzScore1'
                    templet: function (d) {
                        var scoreHz = d.scoreHz || '0'
                        var _html = '<div lay-event="hzScore1"  class="scoreFlex"><span style="color:#3BA9FF">'+scoreHz+'</span>'
                        if (d.hzStaffOpinionState == '0'){
                            _html += '<span class="yyi"></span>'

                        }
                        _html += '</div>'
                        return _html
                    },totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.scoreHz ? Number(d.scoreHz).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'sunScoreHz',
                    width: 160,
                    title: '互助阳性积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    event: 'hzScore2',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sunScoreHz ? Number(d.sunScoreHz).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'scoreBs',
                    width: 160,
                    title: '保司调查积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    // event: 'bsScore1'
                    templet: function (d) {
                        var scoreBs = d.scoreBs || '0'
                        var _html = '<div lay-event="bsScore1"  class="scoreFlex"><span style="color:#3BA9FF">'+scoreBs+'</span>'
                        if (d.bsStaffOpinionState == '0'){
                            _html += '<span class="yyi"></span>'

                        }
                        _html += '</div>'
                        return _html
                    },totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.scoreBs ? Number(d.scoreBs).toLocaleString('en-US') : '';
                    }
                },
                /*{
                    field: 'sunScoreBs',
                    width: 160,
                    title: '保司阳性积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    event: 'bsScore2'
                },*/
                {
                    field: 'sunMoneyBs',
                    width: 160,
                    title: '保司阳性奖励',
                    sort: true,
                    style: 'color:#3BA9FF',
                    event: 'bsScore2',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sunMoneyBs ? Number(d.sunMoneyBs).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'caseSubMoney',
                    width: 160,
                    title: '个案减损奖励',
                    sort: true,
                    edit: 'text',
                    templet: '#caseSubMoneyInput',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.caseSubMoney ? Number(d.caseSubMoney).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'monthBasicIntegral',
                    width: 170,
                    title: '实际基础积分',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.monthBasicIntegral ? Number(d.monthBasicIntegral).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'integralPay',
                    width: 120,
                    title: '积分绩效',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.integralPay ? Number(d.integralPay).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'examineScore',
                    width: 140,
                    event:'examineCaseNum',
                    style: 'color:#3BA9FF',
                    title: '审核总积分',
                    sort: true,totalRow: true
                },
                {
                    field: 'examineRate',
                    width: 120,
                    title: '审核绩效系数',
                    sort: true,
                    edit: 'text',
                    templet: '#shjxxsInput',totalRow: true
                },
                {
                    field: 'examinePay',
                    width: 120,
                    title: '审核绩效',
                    sort: true,
                    edit: 'text',
                    templet: '#shjxInput',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.examinePay ? Number(d.examinePay).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'lateEarlyMoney',
                    width: 150,
                    title: '迟到/早退扣款',
                    sort: true,
                    edit: 'text',
                    templet: '#moneyInput4',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.lateEarlyMoney ? Number(d.lateEarlyMoney).toLocaleString('en-US') : '';
                    }
                }, {
                field: 'absenteeismMoney',
                width: 150,
                title: '旷工扣款',
                sort: true,
                edit: 'text',
                templet: '#moneyInput5',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.absenteeismMoney ? Number(d.absenteeismMoney).toLocaleString('en-US') : '';
                }
            }, {
                field: 'leaveMoney',
                width: 150,
                title: '事假扣款',
                sort: true,
                edit: 'text',
                templet: '#moneyInput6',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.leaveMoney ? Number(d.leaveMoney).toLocaleString('en-US') : '';
                }
            },
                /*{
                field: 'sickLeaveTime',
                width: 140,
                title: '病假时长',
                sort: true,
                edit: 'text',
                templet: '#moneyInput7'
            }, */
                {
                    field: 'sickLeaveMoney',
                    width: 150,
                    title: '病假扣款',
                    sort: true,
                    edit: 'text',
                    templet: '#moneyInput8',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sickLeaveMoney ? Number(d.sickLeaveMoney).toLocaleString('en-US') : '';
                    }
                }
            ]
        ]
        var _cols2 = [
            [{
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
                rowspan: 2,totalRowText: "合计:"
            },{
                title: '员工信息',
                colspan: 14,
                align: 'center',
            }, {
                title: '基础与浮动绩效',
                colspan: 6,
                align: 'center'
            }, {
                title: '调查绩效与补贴',
                colspan: 7,
                align: 'center'
            },{
                title: '审核绩效与补贴',
                colspan: 3,
                align: 'center'
            }, {
                title: '人事扣款',
                colspan: 5,
                align: 'center'
            },{
                field: 'otherPay',
                width: 150,
                title: '其他补发',
                sort: true,
                rowspan: 2,
                edit: 'text',
                templet: '#moneyInput9',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.otherPay ? Number(d.otherPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'remarks',
                width: 190,
                title: '其他补发备注',
                rowspan: 2,
                edit: 'text',
                templet: '#moneyInput11'
            },{
                field: 'otherCutPay',
                width: 150,
                title: '其他扣款',
                sort: true,
                rowspan: 2,
                edit: 'text',
                templet: '#moneyInput90',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.otherCutPay ? Number(d.otherCutPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'otherCutRemarks',
                width: 190,
                title: '其他扣款备注',
                rowspan: 2,
                edit: 'text',
                templet: '#moneyInput110'
            },{
                field: 'realPay',
                width: 160,
                title: '实发绩效',
                sort: true,
                rowspan: 2,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.realPay ? Number(d.realPay).toLocaleString('en-US') : '';
                }
            }],
            [{
                field: 'socialSecurityCompany',
                width: 200,
                title: '社保缴纳公司',
            },{
                field: 'company',
                width: 200,
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
                field: 'jobPost',
                width: 135,
                title: '岗位',
            },{
                field: 'entryTimeStr',
                width: 130,
                title: '入职时间',
                sort : true
            },{
                field: 'fixedPerfPayBase',
                width: 130,
                title: '固定绩效基数',
                sort: true,
                edit: 'text',

                templet: '#jsInput1',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.fixedPerfPayBase ? Number(d.fixedPerfPayBase).toLocaleString('en-US') : '';
                }

            },{
                field: 'travelAllowancePayBase',
                width: 130,
                title: '驻外补贴基数',
                sort: true,
                edit: 'text',

                templet: '#jsInput2',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.travelAllowancePayBase ? Number(d.travelAllowancePayBase).toLocaleString('en-US') : '';
                }

            },{
                field: 'managePerfPayBase',
                width: 160,
                title: '岗位津贴基数',
                sort: true,
                edit: 'text',

                templet: '#jsInput3',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.managePerfPayBase ? Number(d.managePerfPayBase).toLocaleString('en-US') : '';
                }

            },{
                field: 'assesPerfBasePay',
                width: 160,
                title: '考核绩效基数',
                sort: true,
                edit: 'text',

                templet: '#jsInput4',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.assesPerfBasePay ? Number(d.assesPerfBasePay).toLocaleString('en-US') : '';
                }
            },{
                field: 'basicIntegral',
                width: 120,
                title: '基础积分',
                sort: true,totalRow: true
            },
                {
                    field: 'realWorkingDays',
                    width: 150,
                    title: '实际出勤天数',
                    sort: true,
                    edit: 'text',
                    templet: '#realWorkingDaysInput',totalRow: true
                },{
                field: 'workingDays',
                width: 160,
                title: '当月应上班天数',
                sort: true,
            },{
                field: 'rate',
                width: 130,
                title: '百分比',
                sort: true,
            }, {
                field: 'fixedPerfPay',
                width: 150,
                title: '实发固定绩效',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.fixedPerfPay ? Number(d.fixedPerfPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'travelAllowancePay',
                width: 150,
                title: '实发驻外补贴',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.travelAllowancePay ? Number(d.travelAllowancePay).toLocaleString('en-US') : '';
                }
            },{
                field: 'managePerfPay',
                width: 150,
                title: '实发岗位津贴',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.managePerfPay ? Number(d.managePerfPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'assesPerfPay',
                width: 180,
                title: '综合考核绩效基数',
                sort: true,
                edit: 'text',
               templet: '#moneyInput25',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.assesPerfPay ? Number(d.assesPerfPay).toLocaleString('en-US') : '';
                }

            },{
                field: 'assessKpi',
                width: 150,
                title: '绩效考核系数',
                sort: true,
                edit: 'text',
                templet: '#moneyInput2',totalRow: true
            },{
                field: 'realAssessKpi',
                width: 150,
                title: '实际考核绩效',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.realAssessKpi ? Number(d.realAssessKpi).toLocaleString('en-US') : '';
                }
            },
                {
                    field: 'scoreHz',
                    width: 160,
                    title: '互助调查积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    // event: 'hzScore1',
                    templet: function (d) {
                        var scoreHz = d.scoreHz || '0'
                        var _html = '<div lay-event="hzScore1"  class="scoreFlex"><span style="color:#3BA9FF">'+scoreHz+'</span>'
                        if (d.hzStaffOpinionState == '0'){
                            _html += '<span class="yyi"></span>'

                        }
                        _html += '</div>'
                        return _html
                    },totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.scoreHz ? Number(d.scoreHz).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'sunScoreHz',
                    width: 160,
                    title: '互助阳性积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    event: 'hzScore2',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sunScoreHz ? Number(d.sunScoreHz).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'scoreBs',
                    width: 160,
                    title: '保司调查积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    // event: 'bsScore1'
                    templet: function (d) {
                        var scoreBs = d.scoreBs || '0'
                        var _html = '<div lay-event="bsScore1"  class="scoreFlex"><span style="color:#3BA9FF">'+scoreBs+'</span>'
                        if (d.bsStaffOpinionState == '0'){
                            _html += '<span class="yyi"></span>'

                        }
                        _html += '</div>'
                        return _html
                    },totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.scoreBs ? Number(d.scoreBs).toLocaleString('en-US') : '';
                    }
                },
                /*{
                    field: 'sunScoreBs',
                    width: 160,
                    title: '保司阳性积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    event: 'bsScore2'
                },*/
                {
                    field: 'sunMoneyBs',
                    width: 160,
                    title: '保司阳性奖励',
                    sort: true,
                    style: 'color:#3BA9FF',
                    event: 'bsScore2',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sunMoneyBs ? Number(d.sunMoneyBs).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'caseSubMoney',
                    width: 160,
                    title: '个案减损奖励',
                    sort: true,
                    edit: 'text',
                    templet: '#caseSubMoneyInput',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.caseSubMoney ? Number(d.caseSubMoney).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'monthBasicIntegral',
                    width: 170,
                    title: '实际基础积分',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.monthBasicIntegral ? Number(d.monthBasicIntegral).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'integralPay',
                    width: 120,
                    title: '积分绩效',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.integralPay ? Number(d.integralPay).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'examineScore',
                    width: 140,
                    event:'examineCaseNum',
                    style: 'color:#3BA9FF',
                    title: '审核总积分',
                    sort: true,totalRow: true
                },
                {
                    field: 'examineRate',
                    width: 120,
                    title: '审核绩效系数',
                    sort: true,
                    edit: 'text',
                    templet: '#shjxxsInput',totalRow: true
                },
                {
                    field: 'examinePay',
                    width: 120,
                    title: '审核绩效',
                    sort: true,
                    edit: 'text',
                    templet: '#shjxInput',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.examinePay ? Number(d.examinePay).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'lateEarlyMoney',
                    width: 150,
                    title: '迟到/早退扣款',
                    sort: true,
                    edit: 'text',
                    templet: '#moneyInput4',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.lateEarlyMoney ? Number(d.lateEarlyMoney).toLocaleString('en-US') : '';
                    }
                }, {
                field: 'absenteeismMoney',
                width: 150,
                title: '旷工扣款',
                sort: true,
                edit: 'text',
                templet: '#moneyInput5',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.absenteeismMoney ? Number(d.absenteeismMoney).toLocaleString('en-US') : '';
                }
            }, {
                field: 'leaveMoney',
                width: 150,
                title: '事假扣款',
                sort: true,
                edit: 'text',
                templet: '#moneyInput6',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.leaveMoney ? Number(d.leaveMoney).toLocaleString('en-US') : '';
                }
            },
                /*{
                field: 'sickLeaveTime',
                width: 140,
                title: '病假时长',
                sort: true,
                edit: 'text',
                templet: '#moneyInput7'
            }, */
                {
                    field: 'sickLeaveMoney',
                    width: 150,
                    title: '病假扣款',
                    sort: true,
                    edit: 'text',
                    templet: '#moneyInput8',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sickLeaveMoney ? Number(d.sickLeaveMoney).toLocaleString('en-US') : '';
                    }
                }
            ]
        ]
        var _cols00 = [
            [{
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
                rowspan: 2,totalRowText: "合计:"
            },{
                title: '员工信息',
                colspan: 14,
                align: 'center',
            }, {
                title: '基础与浮动绩效',
                colspan: 6,
                align: 'center'
            }, {
                title: '调查绩效与补贴',
                colspan: 7,
                align: 'center'
            },{
                title: '审核绩效与补贴',
                colspan: 3,
                align: 'center'
            }, {
                title: '人事扣款',
                colspan: 5,
                align: 'center'
            },{
                field: 'otherPay',
                width: 150,
                title: '其他补发',
                sort: true,
                rowspan: 2,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.otherPay ? Number(d.otherPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'remarks',
                width: 190,
                title: '其他补发备注',
                rowspan: 2,
            },{
                field: 'otherCutPay',
                width: 150,
                title: '其他扣款',
                sort: true,
                rowspan: 2,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.otherCutPay ? Number(d.otherCutPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'otherCutRemarks',
                width: 190,
                title: '其他扣款备注',
                rowspan: 2,
            }, {
                field: 'realPay',
                width: 160,
                title: '实发绩效',
                sort: true,
                rowspan: 2,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.realPay ? Number(d.realPay).toLocaleString('en-US') : '';
                }
            }],
            [{
                field: 'socialSecurityCompany',
                width: 200,
                title: '社保缴纳公司',
            },{
                field: 'company',
                width: 200,
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
                field: 'jobPost',
                width: 135,
                title: '岗位',
            },{
                field: 'entryTimeStr',
                width: 130,
                title: '入职时间',
                sort : true
            },{
                field: 'fixedPerfPayBase',
                width: 130,
                title: '固定绩效基数',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.fixedPerfPayBase ? Number(d.fixedPerfPayBase).toLocaleString('en-US') : '';
                }
            },{
                field: 'travelAllowancePayBase',
                width: 130,
                title: '驻外补贴基数',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.travelAllowancePayBase ? Number(d.travelAllowancePayBase).toLocaleString('en-US') : '';
                }
            },{
                field: 'managePerfPayBase',
                width: 160,
                title: '岗位津贴基数',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.managePerfPayBase ? Number(d.managePerfPayBase).toLocaleString('en-US') : '';
                }
            },{
                field: 'assesPerfBasePay',
                width: 160,
                title: '考核绩效基数',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.assesPerfBasePay ? Number(d.assesPerfBasePay).toLocaleString('en-US') : '';
                }
            },{
                field: 'basicIntegral',
                width: 120,
                title: '基础积分',
                sort: true,totalRow: true
            },
                {
                    field: 'realWorkingDays',
                    width: 150,
                    title: '实际出勤天数',
                    sort: true,
                },{
                field: 'workingDays',
                width: 160,
                title: '当月应上班天数',
                sort: true,
            },{
                field: 'rate',
                width: 130,
                title: '百分比',
                sort: true,
            }, {
                field: 'fixedPerfPay',
                width: 150,
                title: '实发固定绩效',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.fixedPerfPay ? Number(d.fixedPerfPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'travelAllowancePay',
                width: 150,
                title: '实发驻外补贴',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.travelAllowancePay ? Number(d.travelAllowancePay).toLocaleString('en-US') : '';
                }
            },{
                field: 'managePerfPay',
                width: 150,
                title: '实发岗位津贴',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.managePerfPay ? Number(d.managePerfPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'assesPerfPay',
                width: 180,
                title: '综合考核绩效基数',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.assesPerfPay ? Number(d.assesPerfPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'assessKpi',
                width: 150,
                title: '绩效考核系数',
                sort: true,totalRow: true
            },{
                field: 'realAssessKpi',
                width: 150,
                title: '实际考核绩效',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.realAssessKpi ? Number(d.realAssessKpi).toLocaleString('en-US') : '';
                }
            },
                {
                    field: 'scoreHz',
                    width: 160,
                    title: '互助调查积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    // event: 'hzScore1',
                    templet: function (d) {
                        var scoreHz = d.scoreHz || '0'
                        var _html = '<div lay-event="hzScore1"  class="scoreFlex"><span style="color:#3BA9FF">'+scoreHz+'</span>'
                        if (d.hzStaffOpinionState == '0'){
                            _html += '<span class="yyi"></span>'

                        }
                        _html += '</div>'
                        return _html
                    },totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.scoreHz ? Number(d.scoreHz).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'sunScoreHz',
                    width: 160,
                    title: '互助阳性积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    event: 'hzScore2',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sunScoreHz ? Number(d.sunScoreHz).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'scoreBs',
                    width: 160,
                    title: '保司调查积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    // event: 'bsScore1'
                    templet: function (d) {
                        var scoreBs = d.scoreBs || '0'
                        var _html = '<div lay-event="bsScore1"  class="scoreFlex"><span style="color:#3BA9FF">'+scoreBs+'</span>'
                        if (d.bsStaffOpinionState == '0'){
                            _html += '<span class="yyi"></span>'

                        }
                        _html += '</div>'
                        return _html
                    },totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.scoreBs ? Number(d.scoreBs).toLocaleString('en-US') : '';
                    }
                },
                /*{
                    field: 'sunScoreBs',
                    width: 160,
                    title: '保司阳性积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    event: 'bsScore2'
                },*/
                {
                    field: 'sunMoneyBs',
                    width: 160,
                    title: '保司阳性奖励',
                    sort: true,
                    style: 'color:#3BA9FF',
                    event: 'bsScore2',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sunMoneyBs ? Number(d.sunMoneyBs).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'caseSubMoney',
                    width: 160,
                    title: '个案减损奖励',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.caseSubMoney ? Number(d.caseSubMoney).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'monthBasicIntegral',
                    width: 170,
                    title: '实际基础积分',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.monthBasicIntegral ? Number(d.monthBasicIntegral).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'integralPay',
                    width: 120,
                    title: '积分绩效',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.integralPay ? Number(d.integralPay).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'examineScore',
                    width: 140,
                    event:'examineCaseNum',
                    style: 'color:#3BA9FF',
                    title: '审核总积分',
                    sort: true,totalRow: true
                },
                {
                    field: 'examineRate',
                    width: 120,
                    title: '审核绩效系数',
                    sort: true,totalRow: true
                },
                {
                    field: 'examinePay',
                    width: 120,
                    title: '审核绩效',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.examinePay ? Number(d.examinePay).toLocaleString('en-US') : '';
                    }
                },
                {
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
                width: 150,
                title: '旷工扣款',
                sort: true,
                templet: '#moneyInput500',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.absenteeismMoney ? Number(d.absenteeismMoney).toLocaleString('en-US') : '';
                }
            }, {
                field: 'leaveMoney',
                width: 150,
                title: '事假扣款',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.leaveMoney ? Number(d.leaveMoney).toLocaleString('en-US') : '';
                }
            },
                /*{
                field: 'sickLeaveTime',
                width: 140,
                title: '病假时长',
                sort: true,
            }, */
                {
                    field: 'sickLeaveMoney',
                    width: 150,
                    title: '病假扣款',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sickLeaveMoney ? Number(d.sickLeaveMoney).toLocaleString('en-US') : '';
                    }
                }
            ]
        ]
        var _cols01 = [
            [{
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
                rowspan: 2,totalRowText: "合计:"
            },{
                title: '员工信息',
                colspan: 14,
                align: 'center',
            }, {
                title: '基础与浮动绩效',
                colspan: 6,
                align: 'center'
            }, {
                title: '调查绩效与补贴',
                colspan: 7,
                align: 'center'
            },{
                title: '审核绩效与补贴',
                colspan: 3,
                align: 'center'
            }, {
                title: '人事扣款',
                colspan: 5,
                align: 'center'
            },{
                field: 'otherPay',
                width: 150,
                title: '其他补发',
                sort: true,
                rowspan: 2,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.otherPay ? Number(d.otherPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'remarks',
                width: 190,
                title: '其他补发备注',
                rowspan: 2,
            },{
                field: 'otherCutPay',
                width: 150,
                title: '其他扣款',
                sort: true,
                rowspan: 2,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.otherCutPay ? Number(d.otherCutPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'otherCutRemarks',
                width: 190,
                title: '其他扣款备注',
                rowspan: 2,
            }, {
                field: 'realPay',
                width: 160,
                title: '实发绩效',
                sort: true,
                rowspan: 2,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.realPay ? Number(d.realPay).toLocaleString('en-US') : '';
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
            }],
            [{
                field: 'socialSecurityCompany',
                width: 200,
                title: '社保缴纳公司',
            },{
                field: 'company',
                width: 200,
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
                field: 'jobPost',
                width: 135,
                title: '岗位',
            },{
                field: 'entryTimeStr',
                width: 130,
                title: '入职时间',
                sort : true
            },{
                field: 'fixedPerfPayBase',
                width: 130,
                title: '固定绩效基数',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.fixedPerfPayBase ? Number(d.fixedPerfPayBase).toLocaleString('en-US') : '';
                }
            },{
                field: 'travelAllowancePayBase',
                width: 130,
                title: '驻外补贴基数',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.travelAllowancePayBase ? Number(d.travelAllowancePayBase).toLocaleString('en-US') : '';
                }
            },{
                field: 'managePerfPayBase',
                width: 160,
                title: '岗位津贴基数',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.managePerfPayBase ? Number(d.managePerfPayBase).toLocaleString('en-US') : '';
                }
            },{
                field: 'assesPerfBasePay',
                width: 160,
                title: '考核绩效基数',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.assesPerfBasePay ? Number(d.assesPerfBasePay).toLocaleString('en-US') : '';
                }
            },{
                field: 'basicIntegral',
                width: 120,
                title: '基础积分',
                sort: true,totalRow: true
            },
                {
                    field: 'realWorkingDays',
                    width: 150,
                    title: '实际出勤天数',
                    sort: true,
                },{
                field: 'workingDays',
                width: 160,
                title: '当月应上班天数',
                sort: true,
            },{
                field: 'rate',
                width: 130,
                title: '百分比',
                sort: true,
            }, {
                field: 'fixedPerfPay',
                width: 150,
                title: '实发固定绩效',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.fixedPerfPay ? Number(d.fixedPerfPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'travelAllowancePay',
                width: 150,
                title: '实发驻外补贴',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.travelAllowancePay ? Number(d.travelAllowancePay).toLocaleString('en-US') : '';
                }
            },{
                field: 'managePerfPay',
                width: 150,
                title: '实发岗位津贴',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.managePerfPay ? Number(d.managePerfPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'assesPerfPay',
                width: 180,
                title: '综合考核绩效基数',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.assesPerfPay ? Number(d.assesPerfPay).toLocaleString('en-US') : '';
                }
            },{
                field: 'assessKpi',
                width: 150,
                title: '绩效考核系数',
                sort: true,totalRow: true
            },{
                field: 'realAssessKpi',
                width: 150,
                title: '实际考核绩效',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.realAssessKpi ? Number(d.realAssessKpi).toLocaleString('en-US') : '';
                }
            },
                {
                    field: 'scoreHz',
                    width: 160,
                    title: '互助调查积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    // event: 'hzScore1'
                    templet: function (d) {
                        var scoreHz = d.scoreHz || '0'
                        var _html = '<div lay-event="hzScore1"  class="scoreFlex"><span style="color:#3BA9FF">'+scoreHz+'</span>'
                        if (d.hzStaffOpinionState == '0'){
                            _html += '<span class="yyi"></span>'

                        }
                        _html += '</div>'
                        return _html
                    },totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.scoreHz ? Number(d.scoreHz).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'sunScoreHz',
                    width: 160,
                    title: '互助阳性积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    event: 'hzScore2',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sunScoreHz ? Number(d.sunScoreHz).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'scoreBs',
                    width: 160,
                    title: '保司调查积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    // event: 'bsScore1'
                    templet: function (d) {
                        var scoreBs = d.scoreBs || '0'
                        var _html = '<div lay-event="bsScore1"  class="scoreFlex"><span style="color:#3BA9FF">'+scoreBs+'</span>'
                        if (d.bsStaffOpinionState == '0'){
                            _html += '<span class="yyi"></span>'

                        }
                        _html += '</div>'
                        return _html
                    },totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.scoreBs ? Number(d.scoreBs).toLocaleString('en-US') : '';
                    }
                },
                /*{
                    field: 'sunScoreBs',
                    width: 160,
                    title: '保司阳性积分',
                    sort: true,
                    style: 'color:#3BA9FF',
                    event: 'bsScore2'
                },*/
                {
                    field: 'sunMoneyBs',
                    width: 160,
                    title: '保司阳性奖励',
                    sort: true,
                    style: 'color:#3BA9FF',
                    event: 'bsScore2',totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sunMoneyBs ? Number(d.sunMoneyBs).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'caseSubMoney',
                    width: 160,
                    title: '个案减损奖励',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.caseSubMoney ? Number(d.caseSubMoney).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'monthBasicIntegral',
                    width: 170,
                    title: '实际基础积分',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.monthBasicIntegral ? Number(d.monthBasicIntegral).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'integralPay',
                    width: 120,
                    title: '积分绩效',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.integralPay ? Number(d.integralPay).toLocaleString('en-US') : '';
                    }
                },
                {
                    field: 'examineScore',
                    width: 140,
                    event:'examineCaseNum',
                    style: 'color:#3BA9FF',
                    title: '审核总积分',
                    sort: true,totalRow: true
                },
                {
                    field: 'examineRate',
                    width: 120,
                    title: '审核绩效系数',
                    sort: true,totalRow: true
                },
                {
                    field: 'examinePay',
                    width: 120,
                    title: '审核绩效',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.examinePay ? Number(d.examinePay).toLocaleString('en-US') : '';
                    }
                },
                {
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
                width: 150,
                title: '旷工扣款',
                sort: true,
                templet: '#moneyInput500',totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.absenteeismMoney ? Number(d.absenteeismMoney).toLocaleString('en-US') : '';
                }
            }, {
                field: 'leaveMoney',
                width: 150,
                title: '事假扣款',
                sort: true,totalRow: true,
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.leaveMoney ? Number(d.leaveMoney).toLocaleString('en-US') : '';
                }
            },
                /*{
                field: 'sickLeaveTime',
                width: 140,
                title: '病假时长',
                sort: true,
            }, */
                {
                    field: 'sickLeaveMoney',
                    width: 150,
                    title: '病假扣款',
                    sort: true,totalRow: true,
                    templet: function(d) {
                        // 添加千分位分隔符
                        return d.sickLeaveMoney ? Number(d.sickLeaveMoney).toLocaleString('en-US') : '';
                    }
                }
            ]
        ]

        if (roleCode != 'ceo-step' && roleCode != 'end-step' && hrManageRole == 'true'){
            _cols = _cols1
            if(backReason){
                _cols = _cols2
            }
        }else if ((roleCode == 'superiorManager-first-step' && superiorManagerRole == 'true') || (roleCode == 'superiorManager-step' && superiorManagerRole == 'true')){
            _cols = _cols1
        }else if (roleCode == 'ceo-step' && ceoRole == 'true'){
            _cols = _cols3
        }else if (roleCode == 'hr-step' && hrRole == 'true'){
            _cols = _cols2
        }else if ((surveyUserRole == 'true') || (roleCode == 'organManager-step' && organManagerRole == 'true') ){
            _cols = _cols01
        }else {
            _cols = _cols00
        }


        var _ajax = $.ajax
        var _h = $('.searchs').outerHeight() + $('.sum').outerHeight() + $('.top_reason').outerHeight() + 80
        var fullH = 'full-' + _h
        var partArr = []
        var tableRender = {
            id: "test",
            elem: '#test',
            cols: _cols,
            page:false,
            limit: 10000,
            edit: 'text',
            even: true,
            cellMinWidth: 80,
            height: fullH,
            totalRow: true,
            done: function (res) {
                soulTable.render(this)
                res.data.map(function (cur,i) {
                    if (cur.organOpinion ||cur.superiorOpinion || (cur.bossOpinion && (hrRole == 'true' || hrManageRole == 'true' || ceoRole == 'true'))){
                        $('.layui-table-main.layui-table-body table tbody tr').eq(i).addClass('active')
                        $('.layui-table-fixed-l .layui-table-body table tbody tr').eq(i).addClass('active')
                    }
                    //调查员意见
                    //cur.organOpinion
                    if (false){
                        $('.layui-table-main.layui-table-body table tbody tr').eq(i).addClass('active2')
                        $('.layui-table-fixed-l .layui-table-body table tbody tr').eq(i).addClass('active2')
                    }


                })
                showTitle('.table_block .layui-table-view .layui-table-box .layui-table-header table thead tr')


                $('.nullClass').parents('td').css({
                    'pointer-events': 'none'
                })
            }
        }
        function showTitle(thsJ) {
            var icon_about= '<span class="icon-about">'
            var ths = $(thsJ).eq(1).find('th')
            var ths0 = $(thsJ).eq(0).find('th')

            ths.eq('11').find('.layui-table-cell span:first').after(icon_about)
            ths.eq('12').find('.layui-table-cell span:first').after(icon_about)
            ths.eq('13').find('.layui-table-cell span:first').after(icon_about)
            ths.eq('14').find('.layui-table-cell span:first').after(icon_about)
            ths.eq('15').find('.layui-table-cell span:first').after(icon_about)
            ths.eq('16').find('.layui-table-cell span:first').after(icon_about)

            ths.eq('19').find('.layui-table-cell span:first').after(icon_about)
            ths.eq('20').find('.layui-table-cell span:first').after(icon_about)
            ths.eq('21').find('.layui-table-cell span:first').after(icon_about)
            ths.eq('22').find('.layui-table-cell span:first').after(icon_about)
            ths.eq('23').find('.layui-table-cell span:first').after(icon_about)

            ths.eq('25').find('.layui-table-cell span:first').after(icon_about)
            ths.eq('26').find('.layui-table-cell span:first').after(icon_about)

            ths.eq('27').find('.layui-table-cell span:first').after(icon_about)
            ths.eq('29').find('.layui-table-cell span:first').after(icon_about)


            ths0.eq('11').find('.layui-table-cell span:first').after(icon_about)


            ths.eq('11').find('.layui-table-cell').attr('title', '该员工当月实际应出勤的天数，仅与其入离职时间有关，与请假旷工无关')
            ths.eq('12').find('.layui-table-cell').attr('title', '所有员工当月最长应出勤天数')
            ths.eq('13').find('.layui-table-cell').attr('title', '实际出勤天数/当月应上班天数')
            ths.eq('14').find('.layui-table-cell').attr('title', '固定绩效基数*百分比')
            ths.eq('15').find('.layui-table-cell').attr('title', '驻外补贴基数*百分比')
            ths.eq('16').find('.layui-table-cell').attr('title', '岗位津贴基数*百分比')

            ths.eq('19').find('.layui-table-cell').attr('title', '实际考核绩效=综合考核绩效基数*绩效考核系数')
            ths.eq('20').find('.layui-table-cell').attr('title', '当月结案的互助案件的调查积分（仅对调查员）')
            ths.eq('21').find('.layui-table-cell').attr('title', '当月结案的互助案件的阳性奖励积分（仅对调查员）')
            ths.eq('22').find('.layui-table-cell').attr('title', '当月结案的保司案件的调查积分（仅对调查员）')
            ths.eq('23').find('.layui-table-cell').attr('title', '当月结案的保司案件的阳性奖励金额（仅对调查员）')

            ths.eq('25').find('.layui-table-cell').attr('title', '当月需要拿到积分绩效的最低门槛，超过该分值才有积分绩效。（仅对调查员）')
            ths.eq('26').find('.layui-table-cell').attr('title', '积分绩效=(互助调查积分+互助阳性积分+保司调查积分-实际基础积分)*30+保司阳性奖励+个案减损奖励')

            ths.eq('27').find('.layui-table-cell').attr('title', '当月结案的互助案件，由该复审人员审核的调查分+阳性分 （仅对互助审核员A）')
            ths.eq('29').find('.layui-table-cell').attr('title', '审核绩效=审核总积分*审核绩效系数*2（仅对互助审核员A）')

            ths0.eq('11').find('.layui-table-cell').attr('title', '实发绩效=实发固定绩效+实发驻外补贴+实发岗位津贴+实际考核绩效+积分绩效+审核绩效+迟到/早退扣款+旷工扣款+事假扣款+病假扣款+其他补发+其他扣款')
            ths0.eq('11').find('.layui-table-cell').css({
                'display': 'flex',
                'align-items': 'center'
            })


        }


        var paramSubmit = {"dataCode":"performance","id":$("#id").val(),"roleCode":$("#roleCode").val()}
        initTable(paramSubmit)

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


        function initTable(param){
            $.ajax({
                url:'${ctx}/staff/getDetail',
                type:"post",
                data: param,
                success:function(res){
                    layer.closeAll()
                    res = JSON.parse(res)
                    if (res.isSuccess){
                        var list = res.results.data

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

                        var sum = 0
                        list.map(function (cur,i) {
                            if (cur.realPay){
                                sum += cur.realPay
                            }
                        })
                        // $('.sum span').text(sum.toFixed(2))
                        $('.sum span').text(Number(sum).toLocaleString('en-US'))
                        Object.assign(tableRender,{
                            data: list
                        })
                        setTable(tableRender)
                        sessionStorage.setItem('jx', JSON.stringify(list))
                        sessionStorage.setItem('jxNew', JSON.stringify(list))
                    }

                }
            });
        }


        /****************************** 初始化单元格 *********************************/
        function setTable(tableRender){
            table.render(tableRender);
            /**
             * 监听单元格编辑
             * edit是固定事件名，
             * test是table原始容器的属性 lay-filter="对应的值"
             */
            table.on('edit(test)', function (obj) {
                editFlag = true
                console.log(obj)
                var value = obj.value.trim(), //得到修改后的值
                    data = obj.data, //得到所在行所有键值
                    field = obj.field, //得到字段
                    jx = JSON.parse(sessionStorage.getItem('jx')) || [],
                    jxNew = JSON.parse(sessionStorage.getItem('jxNew')) || []
                var fieldName = ''
                _cols[1].filter(function (cur,index) {
                    if (cur.field == field){
                        fieldName = cur.title
                    }
                })

                var oldValue = ''
                jx.forEach(function (cur, index) {
                    if (cur.jobNo === data.jobNo) {
                        oldValue = cur[field]
                    }
                })

                var flag = true
                if (field == 'rate'){// 百分比 0-1
                    flag = checkPapers('num_0_1', value)
                    if (!flag){
                        layer.msg('字段【' + fieldName + '】不符合规则，请输入0-1内且小数点两位内的数字',{
                            time: 2000,
                            icon:2
                        })
                        obj.update({
                            [field]: oldValue
                        })
                        $(obj.tr[0]).children('td[data-field='+field+']').find('input').val(oldValue)
                        return;
                    }
                }else if ((field == 'assesPerfPay' || field == 'integral' || field == 'integralPay'|| field == 'sickLeaveTime'|| field == 'bsScoreHz'|| field == 'otherScoreHz' || field == 'sunScoreHz'|| field == 'trafficSubsidy' || field == 'realWorkingDays'
                    || field == 'bsScoreBs'|| field == 'otherScoreBs'|| field == 'sunScoreBs' || field == 'otherPay' || field == 'fixedPerfPayBase' || field == 'travelAllowancePayBase' || field == 'managePerfPayBase' || field == 'assesPerfBasePay' || field == 'examinePay' || field == 'caseSubMoney') && value){//绩效 正数


                    flag = checkPapers('money', value)
                    if (!flag){
                        layer.msg('字段【' + fieldName + '】不符合规则，请输入小数点两位内的正数字',{
                            time: 2000,
                            icon:2
                        })
                        obj.update({
                            [field]: oldValue
                        })
                        $(obj.tr[0]).children('td[data-field='+field+']').find('input').val(oldValue)
                        return;
                    }
                }else if ((field == 'assessKpi' || field == 'managePerfPaySize' || field == 'managePerfPaySizeHz' || field == 'welfarePay'  || field == 'examineRate') && value){//绩效系数  正数
                    flag = checkPapers('moneyor4', value)
                    if (!flag){
                        layer.msg('字段【' + fieldName + '】不符合规则，请输入小数四位内的正数字',{
                            time: 2000,
                            icon:2
                        })
                        obj.update({
                            [field]: oldValue
                        })
                        $(obj.tr[0]).children('td[data-field='+field+']').find('input').val(oldValue)
                        return;
                    }
                }
                // else if (field == 'otherPay' && value){//其他补扣款 正负数
                //     flag = checkPapers('moneyorMinus', value)
                //     if (!flag){
                //         layer.msg('字段【' + fieldName + '】不符合规则，请输入小数点两位内的数字',{
                //             time: 2000,
                //             icon:2
                //         })
                //         obj.update({
                //             [field]: oldValue
                //         })
                //         $(obj.tr[0]).children('td[data-field='+field+']').find('input').val(oldValue)
                //         return;
                //     }
                // }
                else if ((field == 'remarks' || field == 'welfareRemark' || field == 'otherCutRemarks') && value){// 备注

                }else if (value){// 两位小数
                    flag = checkPapers('moneyorMinus', value)
                    if (!flag){
                        layer.msg('字段【' + fieldName + '】不符合规则，请输入小数点两位内的数字',{
                            time: 2000,
                            icon:2
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
                        "btnCode":"itemSave",
                        "operateCode": 'performance',
                        "id":$("#id").val(),
                        "jobNo":data.jobNo,
                        "value":value,
                        "colCode": field
                    } ,
                    success:function(res){
                        res = JSON.parse(res)
                        if (res.isSuccess){
                            layer.msg('【'+fieldName+ ':' + value +'】 更新成功',{time:2000,icon:1});
                            var newValue = res.results.staffPerformancePersonnel
                            obj.update(newValue)
                            var jx2 = [],jxNew2 = []
                            var sum = 0

                            jx2 = jx.map(function (cur, index) {
                                if (cur.jobNo === data.jobNo) {
                                    cur = newValue
                                }
                                return cur
                            })
                            jxNew2 = jxNew.map(function (cur, index) {
                                if (cur.jobNo === data.jobNo) {
                                    cur = newValue
                                }

                                if (cur.realPay){
                                    sum += cur.realPay
                                }
                                return cur
                            })

                            // $('.sum span').text(sum.toFixed(2))
                            $('.sum span').text(Number(sum).toLocaleString('en-US'))
                            sessionStorage.setItem('jx', JSON.stringify(jx2))
                            sessionStorage.setItem('jxNew', JSON.stringify(jxNew2))
                        } else {
                            obj.update({
                                [field]: oldValue
                            })
                            layer.msg(res.msg,{time:2000,icon:2});
                        }
                        $('.layui-table-header').css({
                            'pointer-events': 'auto'
                        })
                        editFlag = false
                    }
                });

            });

            table.on('tool(test)', function(obj){
                curObj =  obj
                console.log('----',curObj)
                var param = {}
                if(obj.event =='caseNum2' && obj.data.manageCaseNum !== '' && obj.data.manageCaseNum !== null){
                    jumpPage({
                        surveyCode: 'performance',
                        btnCode: 'caseNum',
                        staffPerformanceId: $("#id").val(),
                        userId: obj.data.userId,
                        id : obj.data.id,
                        orgAttr: 1
                    })
                }else  if(obj.event =='caseNum' && obj.data.manageCaseNumHz !== '' && obj.data.manageCaseNumHz !== null){
                    jumpPage({
                        surveyCode: 'performance',
                        btnCode: 'caseNum',
                        staffPerformanceId: $("#id").val(),
                        userId: obj.data.userId,
                        id : obj.data.id,
                        orgAttr: 2
                    })
                }else  if(obj.event =='examineCaseNum' && obj.data.examineScore !== '' && obj.data.examineScore !== null){
                    jumpPage({
                        surveyCode: 'performance',
                        btnCode: 'examineCaseNum',
                        staffPerformanceId: $("#id").val(),
                        userId: obj.data.userId,
                        id : obj.data.id
                    })
                }

                if (obj.event == 'bsScore1' && obj.data.scoreHz !=='' && obj.data.scoreHz !==null || obj.event == 'bsScore2' && obj.data.sunScoreHz !==''  && obj.data.sunScoreHz !==null){
                    var param = {
                        surveyCode: 'performance',
                        btnCode: 'case',
                        staffPerformanceId: $("#id").val(),
                        userId: obj.data.userId,
                        orgAttr: '1',
                        id : obj.data.id,
                        type : ''
                    }
                    if (obj.event == 'bsScore2'){
                        Object.assign(param,{
                            type : '1'
                        })
                    }
                    jumpPage(param)
                } else if (obj.event == 'hzScore1' && obj.data.scoreBs !=='' && obj.data.scoreBs !==null || obj.event == 'hzScore2' && obj.data.sunScoreBs !=='' && obj.data.sunScoreBs !==null){
                    jumpPage({
                        surveyCode: 'performance',
                        btnCode: 'case',
                        staffPerformanceId: $("#id").val(),
                        userId: obj.data.userId,
                        orgAttr: '2',
                        id : obj.data.id,
                        type : ''
                    })
                }

                if (obj.event == 'sendBack'){
                    var  title = '当前行【'+ obj.data.realName + '】 审核人意见'
                    if (surveyUserRole == 'true'){
                        title = '意见'
                    }
                    openReasonEdit(obj, '')

                    // layer.prompt({title: title,formType:2,btn: ['确定' ,'取消']},function (text,index) {
                    //     layer.close(index)
                    //     operatePerson({
                    //         roleCode: roleCode,
                    //         operateCode: 'performance',
                    //         btnCode:"back",
                    //         id: $('#id').val(),
                    //         staffPerformancePersonnelId: obj.data.id,
                    //         backReason: text
                    //     },obj)
                    // })
                }else  if (obj.event == 'viewReason1'){
                    if ((roleCode == 'organManager-step' && organManagerRole == 'true' && selfState == 0) || (roleCode == 'surveyUser-step' && surveyUserRole) || (roleCode == 'superiorManager-step' && superiorManagerRole == 'true' && selfState == 0)){
                        if (roleCode == 'superiorManager-step' && superiorManagerRole == 'true' && selfState == 0){
                            openReasonEdit(obj, 'organOpinion', 'organManager-step')
                        }else {
                            openReasonEdit(obj, 'organOpinion')
                        }
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
                            operateCode: 'performance',
                            btnCode:"removeBack",
                            id: $('#id').val(),
                            staffPerformancePersonnelId: obj.data.id
                        },obj)
                    }, function(){

                    });

                }
            })

            function openReasonEdit(obj,field, step) {
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
                var step = step || ''
                var roleCodeCopy = roleCode
                if (step){
                    roleCodeCopy = step
                }
                reasonIndex = layer.open({
                    type: 1,
                    title: title,
                    area: ['500px', '300px'],
                    content: $('#view-or-edit').html()
                });
                $('textarea[name=reason-textarea]').val(obj.data[field])

                $('textarea[name=reason-textarea]').attr('placeholder', '仅当对此条数据中非积分部分的内容有异议时，才需要在此填写意见，若无异议，请不要在此填写“同意”、“确认无误”等任何内容，空置即可。若对积分有意见，请点击积分进入案件列表对具体案件单独填写意见。')
                $('.submit-reason').click(function () {
                    var backReason = $('textarea[name=reason-textarea]').val()
                    if (backReason){
                        operatePerson({
                            roleCode: roleCodeCopy,
                            operateCode: 'performance',
                            btnCode:"back",
                            id: $('#id').val(),
                            staffPerformancePersonnelId: obj.data.id,
                            backReason:backReason
                        },obj,step)
                    }else {
                        operatePerson({
                            roleCode: roleCodeCopy,
                            operateCode: 'performance',
                            btnCode:"removeBack",
                            id: $('#id').val(),
                            staffPerformancePersonnelId: obj.data.id
                        },obj,step)
                    }
                    layer.close(reasonIndex)
                })
                $('.close-reason').click(function () {
                    layer.close(reasonIndex)
                })
            }

            function operatePerson(param,obj,step) {
                console.log(param)
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
                                var newValue = res.results.staffPerformancePersonnel

                                var  jxNew = JSON.parse(sessionStorage.getItem('jxNew')) || []
                                var  jx = JSON.parse(sessionStorage.getItem('jx')) || []
                                var jx2 = [],jxNew2 = []
                                var index = ''
                                jxNew2 = jxNew.map(function (cur,i) {
                                    if (cur.jobNo == obj.data.jobNo){
                                        index = i;
                                        cur = newValue
                                    }
                                    return cur
                                })

                                jx2 = jx.map(function (cur,i) {
                                    if (cur.jobNo == obj.data.jobNo){
                                        cur = newValue
                                    }
                                    return cur
                                })
                                sessionStorage.setItem('jxNew', JSON.stringify(jxNew2))
                                sessionStorage.setItem('jx', JSON.stringify(jx2))

                                var rejectPart =$('.form-check .layui-btn[data-type="rejectPart"]')
                                var reject =$('.form-check .layui-btn[data-type="reject"]')
                                var checkTo =$('.form-check .layui-btn-normal')

                                obj.update({
                                    organOpinion: newValue.organOpinion,
                                    superiorOpinion: newValue.superiorOpinion,
                                    bossOpinion: newValue.bossOpinion,
                                })
                                console.log('-step-',step)
                                if (step){
                                    if (param.backReason){
                                    } else{
                                        $('.layui-table-fixed-r .operateBtn4').eq(index).hide()
                                        if (!newValue.organOpinion && !newValue.superiorOpinion && !newValue.bossOpinion) {
                                            $('.layui-table-main.layui-table-body table tbody tr').eq(index).removeClass('active')
                                            $('.layui-table-fixed-l .layui-table-body table tbody tr').eq(index).removeClass('active')
                                        }
                                    }
                                } else {
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
                                }
                                if (roleCode == 'organManager-step' && ceoRole || roleCode == 'surveyUser-step' && surveyUserRole){
                                    $('.layui-table-fixed-r .operateBtn2').eq(index).attr('title',newValue.organOpinion)

                                }
                                if (roleCode == 'superiorManager-step' && ceoRole){
                                    $('.layui-table-fixed-r .operateBtn2').attr('title',newValue.superiorOpinion)

                                }

                                if (roleCode == 'ceo-step' && ceoRole){
                                    if (param.backReason){
                                        if (partArr.indexOf(obj.data.jobNo) == -1){
                                            partArr.push(obj.data.jobNo)
                                        }
                                        rejectPart.removeClass('lf-none')
                                        reject.addClass('lf-none')
                                        checkTo.addClass('lf-none')
                                    } else{
                                        var m =partArr.indexOf(obj.data.jobNo)
                                        partArr.splice(m,1)
                                        if (!partArr.length){
                                            rejectPart.addClass('lf-none')
                                            reject.removeClass('lf-none')
                                            checkTo.removeClass('lf-none')
                                        }
                                    }
                                    $('.layui-table-fixed-r .operateBtn2').attr('title',newValue.bossOpinion)
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




        function jumpPage(param) {
            var height = $(document).outerHeight()*0.9;
            var width = $(document.body).outerWidth()*0.9;
            var pageSize = localStorage.getItem("pageSize") || 20;
            openDialog({
                frame:true,
                title:"详情",
                height:height,
                width:width,
                update: true,
                url:"${ctx}/staff/popup?surveyCode=performance&btnCode="+param.btnCode + "&staffPerformanceId="+param.staffPerformanceId + "&userId="+param.userId + "&orgAttr="+param.orgAttr + "&id="+param.id+"&roleCode="+$('#roleCode').val()+"&type="+param.type+"&pageSize="+pageSize
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


        /****************************** 上传文件 *********************************/

        //指定允许上传的文件类型
        function _done(res) {

        }
        upload.render({
            elem: '#import',
            url: '${ctx}/staff/export', //改成您自己的上传接口
            data: {
                exportType: 'performancePersonnelData',
                staffPerformanceId: $('#id').val()
            },
            accept: 'file', //普通文件
            exts: 'xlsx|xls', //只允许上传压缩文件
            before: function(obj){
                layer.load();
            },
            done: function (res) {
                layer.closeAll('loading')
                console.log(res)
                if (res.isSuccess){
                    layer.alert(res.message,{icon:1})
                    improtInit()
                } else {
                    layer.alert('导入出错',{icon:2})
                }
            },
        });

        function improtInit() {
            $.ajax({
                url:'${ctx}/staff/getDetail',
                type:"post",
                data : {"dataCode":"performance","id":$("#id").val(),"roleCode":$("#roleCode").val()},
                success:function(res,param){
                    paramSubmit = {"dataCode":"performance","id":$("#id").val(),"roleCode":$("#roleCode").val()}
                    res = JSON.parse(res)
                    if(res.isSuccess){
                        var list = res.results.data
                        var sum = 0
                        list.map(function (cur,i) {
                            if (cur.realPay){
                                sum += cur.realPay
                            }
                        })
                        // $('.sum span').text(sum.toFixed(2))
                        $('.sum span').text(Number(sum).toLocaleString('en-US'))
                        Object.assign(tableRender,{data: res.results.data})
                        reloadTable(tableRender)
                        sessionStorage.setItem('jx', JSON.stringify(res.results.data))
                        sessionStorage.setItem('jxNew', JSON.stringify(res.results.data))


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
                            scompany: '',
                            company: '',
                            organ: '',
                            department: '',
                            // team: '',
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


        /****************************** 提交审核 相关 *********************************/
        $('.form-check').on('click', 'button', function(){
            var _this = $(this)
            var type = _this.attr('data-type')
            if (type == 'check_hr'){
                var pw = $('input[name=password]').val()
                if (!pw){
                    layer.msg('请输入查询密码',{icon:3})
                }else if(pw.indexOf(' ') > -1){
                    layer.msg('密码不可含有空格',{
                        icon: 3
                    })
                }else {
                    operate({
                        operateCode: 'performance',
                        id:$('#id').val(),
                        btnCode:"hr-step",
                        password: pw,
                    })
                }
            }else  if (type == 'check_superiorManager_1'){
                operate({
                    operateCode: 'performance',
                    id:$('#id').val(),
                    btnCode:"superiorManager-first-step",
                })
            } else  if (type == 'check_organManager'){
                operate({
                    operateCode: 'performance',
                    id:$('#id').val(),
                    btnCode:"organManager-step",
                })
            }else  if (type == 'check_superiorManager'){
                operate({
                    operateCode: 'performance',
                    id:$('#id').val(),
                    btnCode:"superiorManager-step",
                })
            }else  if (type == 'check_hrManage'){
                operate({
                    operateCode: 'performance',
                    id:$('#id').val(),
                    btnCode:"hrManage-step",
                })
            }else  if (type == 'check_ceo'){
                operate({
                    operateCode: 'performance',
                    id:$('#id').val(),
                    btnCode:"ceo-step",
                    stepType: "yes"
                })
            }else if (type == 'reject'){
                layer.prompt({title:"请输入拒绝原因（必填项）",formType:2},function (text,index) {
                    layer.close(index)
                    operate({
                        operateCode: 'performance',
                        id:$('#id').val(),
                        btnCode:$('#roleCode').val(),
                        stepType: "no",
                        reason: text
                    })
                })
            }else if (type == 'rejectPart'){
                operate({
                    operateCode: 'performance',
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
                    'hr-step': '提交成功，待分管总一审',
                    'superiorManager-first-step': '提交成功，待机构负责人审核',
                    'organManager-step': '提交成功，待上级分管总审核',
                    'superiorManager-step': '提交成功，待人事主管审核',
                    'hrManage-step': '提交成功，待总部审核',
                    'ceo-step': '绩效审核完成！',
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
            }else if (data.stepType == 'partReturn'){
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
                            icon:1
                        },function () {
                            sessionStorage.removeItem('param')
                            sessionStorage.removeItem('jxNew')
                            sessionStorage.removeItem('sort_field')
                            closeDialogRefresh()
                        })
                    } else{
                        layer.msg(res.msg, {
                            time: 2000,
                            icon:2
                        })
                    }
                }
            });
        }

        /****************************** 重置 导出 刷新  *********************************/
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
                        'value': 1,
                        'name': '导出绩效考核系数、其他补扣款、备注',
                        selected: true
                    }, {
                        'value': 2,
                        'name': '导出全部字段'
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

                        var surveyCode = 'performance'
                        if (exportDemo.getValue('valueStr') == 1){
                            surveyCode = 'performance';
                        }else if(exportDemo.getValue('valueStr') == 2){
                            surveyCode = 'performanceWhole';
                        }
                        var url =  '${ctx}/staff/downLoad?staffPerformanceId=' + ${staffPerformance.id}+'&socialSecurityCompanyIds='+param.socialSecurityCompanyIds+'&companyIds='+param.companyIds+'&organIds='+param.organIds+'&departmentIds='+param.departmentIds+'&jobPostIds='+param.jobPostIds+'&jobNo='+param.jobNo+'&realName='+param.realName+'&entryTime='+param.entryTime+'&surveyCode='+surveyCode
                        var aLink = document.createElement('a');
                        aLink.href=url
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
                jx = JSON.parse(sessionStorage.getItem('jx')),
                jxNew = '';
            if (paramNow){
                param = paramNow
            } else{
                param = JSON.parse(sessionStorage.getItem('param')) || {}
            }
            Object.assign(param, {
                [key]: value
            })
            console.log('param',param)
            if (!value && !param.realName && !param.scompany && !param.company && !param.organ && !param
                .department && !param.team && !param.jobNo && !param.entryTimeStr && !param.entryTimeEndStr) {
                jxNew = jx
            } else {
                jxNew = jx.filter(function (cur, index) {
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
                    if (param.jobNo) {
                        var str = cur.jobNo
                        var str2 = param.jobNo
                        if (str){
                            flag = flag && str.indexOf(str2) > -1
                        } else {
                            flag = false
                        }
                    }
                    if (param.entryTimeStr) {
                        flag = flag && cur.entryTimeStr == param.entryTimeStr
                    }
                    if (param.entryTimeEndStr) {
                        flag = flag && cur.entryTimeEndStr == param.entryTimeEndStr
                    }
                    return flag
                })
            }

            var sum = 0
            jxNew.map(function (cur,i) {
                if (cur.realPay){
                    sum += cur.realPay
                }
            })
            // $('.sum span').text(sum.toFixed(2))
            $('.sum span').text(Number(sum).toLocaleString('en-US'))
            sessionStorage.setItem('param', JSON.stringify(param))
            sessionStorage.setItem('jxNew', JSON.stringify(jxNew))

            Object.assign(tableRender,{data: jxNew, cols: _cols})
            reloadTable(tableRender)
            <%--table.reload('test',{--%>
            <%--    url: '${ctx}/staff/getDetail',--%>
            <%--    method: 'post',--%>
            <%--    initSort: {--%>
            <%--        field: "basePay", //排序字段，对应 cols 设定的各字段名--%>
            <%--        type: "asc" //排序方式  asc: 升序、desc: 降序、null: 默认排序--%>
            <%--    },--%>
            <%--    cols: _cols4,--%>
            <%--    where: {"dataCode":"performance","id":$("#id").val()}--%>
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
                    }
                    else if (key == 'department'){
                        res.results.map(function (cur,index) {
                            _html += "<option value='"+ cur.teamId+ "'>"+cur.teamName+"</option>"
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
            $('.table_block').append(' <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg" style="margin: 0"></table>')
            // table.render(tableRender)
            setTable(tableRender)

        }

        $('body').on('click', '#diglog_close_btn', function(){
            if ((roleCode == 'superiorManager-step' && superiorManagerRole == 'true' && selfState == 0
                || roleCode == 'organManager-step' && organManagerRole == 'true'
                || roleCode == 'hrManage-step' && hrManageRole == 'true'
                || roleCode == 'ceo-step' && ceoRole == 'true'
                || roleCode == 'hr-step' && hrRole == 'true')
                || (roleCode == 'superiorManager-first-step' && superiorManagerRole == 'true')){
                updateObj()
            }
        })
        function updateObj() {
            _ajax({
                url:'${ctx}/staff/operate',
                type:"post",
                data :{
                    "btnCode":"itemSave",
                    "operateCode": 'performance',
                    "id":$("#id").val(),
                    "jobNo":curObj.data.jobNo,
                    "value":'123',
                    "colCode": '123'
                },
                success:function(res){
                    res = JSON.parse(res)
                    if (res.isSuccess){
                        var jx = JSON.parse(sessionStorage.getItem('jx')) || [],
                            jxNew = JSON.parse(sessionStorage.getItem('jxNew')) || []
                        var newValue = res.results.staffPerformancePersonnel
                        var index= ''


                        var jx2 = [],jxNew2 = []
                        var sum = 0

                        jx2 = jx.map(function (cur, index) {
                            if (cur.jobNo === curObj.data.jobNo) {
                                cur = newValue
                            }
                            return cur
                        })
                        jxNew2 = jxNew.map(function (cur, i) {
                            if (cur.jobNo === curObj.data.jobNo) {
                                cur = newValue
                                index = i
                            }

                            if (cur.realPay){
                                sum += cur.realPay
                            }
                            return cur
                        })

                        if (roleCode == 'surveyUser-step' && surveyUserRole == 'true'){
                            if (curObj.data.organOpinion && !newValue.organOpinion){
                                $('.layui-table-fixed-r .operateBtn1').eq(index).show()
                                $('.layui-table-fixed-r .operateBtn2').eq(index).hide()
                                $('.layui-table-main.layui-table-body table tbody tr').eq(index).removeClass('active')
                                $('.layui-table-fixed-l .layui-table-body table tbody tr').eq(index).removeClass('active')
                            } else if (!curObj.data.organOpinion && newValue.organOpinion){
                                $('.layui-table-fixed-r .operateBtn1').eq(index).hide() //填写意见
                                $('.layui-table-fixed-r .operateBtn2').eq(index).show()
                                $('.layui-table-main.layui-table-body table tbody tr').eq(index).addClass('active')
                                $('.layui-table-fixed-l .layui-table-body table tbody tr').eq(index).addClass('active')
                            }
                        }
                        curObj.update(newValue)

                        // $('.sum span').text(sum.toFixed(2))
                        $('.sum span').text(Number(sum).toLocaleString('en-US'))
                        sessionStorage.setItem('jx', JSON.stringify(jx2))
                        sessionStorage.setItem('jxNew', JSON.stringify(jxNew2))
                    }
                }
            });
        }
    })

    //正则匹配
    var checkPapers = function (parama, paramb) {
        var map = new Map([
            ['phone', /^[1][3,4,5,7,8,9][0-9]{9}$/],
            ['passport', !/^((1[45]\d{7})|(G\d{8})|(P\d{7})|(S\d{7,8}))?$/],
            ['identity', /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/],
            ['money', /^[0-9]+(\.[0-9]{1,2})?$/],
            ['moneyorMinus', /^(\-|\+)?\d+(\.\d{1,2})?$/],
            ['moneyor4', /^(\-|\+)?\d+(\.\d{1,4})?$/],
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