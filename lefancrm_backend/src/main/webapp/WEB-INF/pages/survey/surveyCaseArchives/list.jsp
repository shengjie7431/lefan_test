<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>归档管理报表</title>
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
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
            width: 100px;
            padding: 6px 15px;
            padding-left: 0;
            margin-bottom: 0;
        }

        .layui-form-item .layui-input-inline {
            width: 150px !important;
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
        .lf-select-block2 {
            padding: 0 8px !important;
            white-space: nowrap;
            color: #333333 !important;
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
        .layui-input{
            height: 32px ;
        }

        .layui-table-edit {
            width: 100% !important;
        }


        .layui-table .layui-input {
            height: 100%;
        }

        .layui-input-td {
            line-height: inherit;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }


        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+7),
        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+8),
        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+9),
        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+10),
        .layui-table-header table thead tr:first-of-type th:nth-of-type(100n+11),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 1),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 4),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 8),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 11){
            background-color: #b7d7f0
        }


        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 2),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 3),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 5),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 6),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 7),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 9),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 10),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 12),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 13),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 14),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 15),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 16),
        .layui-table-header table thead tr:nth-of-type(2n) th:nth-of-type(100n + 17) {
            background-color: #bfe2af;
        }


        .poi-no{
            pointer-events: none;
        }
    </style>
</head>

<body>
<div class="main">

    <input type="hidden" value='${params.consignorsJson}' id="consignorsJson">
    <input type="hidden" value='${params.franchiseesJson}' id="franchiseesJson">

    <div class="layui-form searchs" lay-filter="search">
        <div class="layui-form-item ">
            <div class="layui-inline" style="width: 431px;">
                <label class="layui-form-label">快捷查询</label>
                <div class="layui-input-inline _input" >
                    <input style="width: 325px;" type="text" name="surveyCaseNo" placeholder="可输入被调查人、案件编号、联系号码、身份证号"  autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">委托方机构</label>
                <div class="layui-input-inline">
                    <div id="consignor" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">调查机构</label>
                <div class="layui-input-inline">
                    <div id="franchisees" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <div class="d-flex-wrap">
                    <label class="layui-form-label">平台复审时间</label>
                    <div class="d-flex-wrap">
                        <div class="data-choose">
                            <input type="text" class="layui-input paramTime" readonly id="lefanStartTime"
                                   placeholder="请选择日期">
                            <span class="dc-span">至</span>
                            <input type="text" class="layui-input paramTime" readonly id="lefanEndTime"
                                   placeholder="请选择日期">
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <div class="d-flex-wrap">
                    <label class="layui-form-label">保司终审时间</label>
                    <div class="d-flex-wrap">
                        <div class="data-choose">
                            <input type="text" class="layui-input paramTime" readonly id="startTime"
                                   placeholder="请选择日期">
                            <span class="dc-span">至</span>
                            <input type="text" class="layui-input paramTime" readonly id="endTime"
                                   placeholder="请选择日期">
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <div class="d-flex-wrap">
                    <label class="layui-form-label">更新时间</label>
                    <div class="d-flex-wrap">
                        <div class="data-choose">
                            <input type="text" class="layui-input paramTime" readonly id="updStartTime"
                                   placeholder="请选择日期">
                            <span class="dc-span">至</span>
                            <input type="text" class="layui-input paramTime" readonly id="updEndTime"
                                   placeholder="请选择日期">
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">归档状态</label>
                <div class="layui-input-inline">
                    <div id="archivesState" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 20px;">
                <button lay-submit class="ll-submit layui-btn layui-btn-normal layui-btn-sm layui-btn-radius"
                        lay-filter="submit" style="width: 86px">查询 <i
                        class="layui-icon layui-icon-search"></i></button>

                <button class="ll-submit layui-btn layui-btn-normal layui-btn-sm layui-btn-radius layui-mark"
                        style="width: 110px">批量标记归档 <i class="layui-icon layui-icon-star"></i></button>
                <button class="ll-submit layui-btn layui-btn-normal layui-btn-sm layui-btn-radius layui-export"
                        style="width: 86px">导出 <i class="layui-icon layui-icon-export"></i></button>
            </div>
        </div>
    </div>
    <div class="table-content">
        <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">
        </table>
    </div>
</div>


<script type="text/html" id="table-content-child-h">
    <div class="table-content-child">
        <table class="layui-table" id="test-child" lay-filter="test-child" lay-skin="line" lay-size="lg" style="margin: 0">
        </table>
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
<script type="text/html" id="editRemark">
    {{#  if(d.remark == 'undefinded' || d.remark == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div title={{d.remark}} class="layui-input layui-input-td">{{d.remark}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput1">
    {{#  if(d.interviewRecord == 'undefinded' || d.interviewRecord == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.interviewRecord}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput2">
    {{#  if(d.medicalRecord == 'undefinded' || d.medicalRecord == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.medicalRecord}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput3">
    {{#  if(d.medicalReport == 'undefinded' || d.medicalReport == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.medicalReport}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput4">
    {{#  if(d.socialInsurance == 'undefinded' || d.socialInsurance == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.socialInsurance}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput5">
    {{#  if(d.isDeath == 'undefinded' || d.isDeath == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.isDeath}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput6">
    {{#  if(d.legalHeir == 'undefinded' || d.legalHeir == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.legalHeir}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput7">
    {{#  if(d.legalHeirRelationship == 'undefinded' || d.legalHeirRelationship == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.legalHeirRelationship}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput8">
    {{#  if(d.deathCertificate == 'undefinded' || d.deathCertificate == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.deathCertificate}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput9">
    {{#  if(d.isGiveUp == 'undefinded' || d.isGiveUp == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.isGiveUp}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput10">
    {{#  if(d.claimsGiveUp == 'undefinded' || d.claimsGiveUp == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.claimsGiveUp}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput11">
    {{#  if(d.otherStatement == 'undefinded' || d.otherStatement == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.otherStatement}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput12">
    {{#  if(d.isAccident == 'undefinded' || d.isAccident == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.isAccident}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput13">
    {{#  if(d.anAccident == 'undefinded' || d.anAccident == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.anAccident}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput14">
    {{#  if(d.noAccident == 'undefinded' || d.noAccident == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.noAccident}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput15">
    {{#  if(d.publicInspection == 'undefinded' || d.publicInspection == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.publicInspection}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput16">
    {{#  if(d.appraisalReport == 'undefinded' || d.appraisalReport == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.appraisalReport}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput17">
    {{#  if(d.legalInstrument == 'undefinded' || d.legalInstrument == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.legalInstrument}}</div>
    {{#  } }}
</script>
<script type="text/html" id="editInput18">
    {{#  if(d.otherItems == 'undefinded' || d.otherItems == null){ }}
    <div class="layui-input layui-input-td"></div>
    {{#  } else { }}
    <div class="layui-input layui-input-td">{{d.otherItems}}</div>
    <div class="layui-input layui-input-td">{{d.otherItems}}</div>
    {{#  } }}
</script>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>


<script src="${ctx}/js/jquery.min.js" charset="utf-8"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>

<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        soulTable: 'soulTable',
        xmSelect: 'xm-select'
    })

    var _height = $(document).height() * 0.9
    var _width = $(document).width() * 0.9

    function getYMD(date) {
        var today = new Date(date);
        return {
            'y': today.getFullYear(),
            'm': today.getMonth() + 1,
            'd': today.getDate(),
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


    var startTime = '',
        endTime = '',
        updStartTime = '',
        updEndTime = '',
        lefanStartTime = '',
        lefanEndTime = ''
    layui.use(['form', 'table', 'soulTable', 'xmSelect', 'laydate', 'layer'], function () {
        var soulTable = layui.soulTable,
            table = layui.table,
            form = layui.form,
            xmSelect = layui.xmSelect,
            laydate = layui.laydate,
            layer = layui.layer,
            $ = layui.$;

        var demo1 = xmSelect.render({
            el: '#consignor',
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
            el: '#franchisees',
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
            el: '#archivesState',
            theme: {
                color: '#3BA9FF',
            },
            size: 'small',
            radio: true,
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {
                            var _html = ''
                            sels.filter(function (cur) {
                                _html +=
                                    '<div class="xm-label-block lf-select-block">' + cur
                                        .name + '</div>'
                            })
                            return _html
                        }
                    },
                }
            },
            data: []
        })


        var consignorsJson = $("#consignorsJson").val();
        consignorsJson = JSON.parse(consignorsJson);
        filterJson(demo1, consignorsJson, 'id', 'company', false, true)

        var franchiseesJson = $("#franchiseesJson").val();
        franchiseesJson = JSON.parse(franchiseesJson);
        filterJson(demo2, franchiseesJson, 'id', 'name', false, true)

        demo3.update({
            data: [{
                name: '全部',
                value: ''
            },{
                name: '未归档',
                value: '0'
            },{
                name: '归档完成',
                value: '1'
            }]
        })

        demo3.setValue(['0'])


        // var initVals = setDate('upMonth')
        startTime = laydate.render({
            elem: '#startTime',
            done: function (value, date) {
                endTime.config.min = {
                    year: date.year,
                    month: date.month - 1,
                    date: date.date
                }
            }
        });
        endTime = laydate.render({
            elem: '#endTime',
            done: function (value, date) {
                if (value) {
                    startTime.config.max = {
                        year: date.year,
                        month: date.month - 1,
                        date: date.date
                    }
                } else {
                    startTime.config.max = {
                        year: 2100,
                        month: 1,
                        date: 1
                    }
                }
            }
        });
        updStartTime = laydate.render({
            elem: '#updStartTime',
            done: function (value, date) {
                endTime.config.min = {
                    year: date.year,
                    month: date.month - 1,
                    date: date.date
                }
            }
        });
        updEndTime = laydate.render({
            elem: '#updEndTime',
            done: function (value, date) {
                if (value) {
                    startTime.config.max = {
                        year: date.year,
                        month: date.month - 1,
                        date: date.date
                    }
                } else {
                    startTime.config.max = {
                        year: 2100,
                        month: 1,
                        date: 1
                    }
                }
            }
        });

        lefanStartTime = laydate.render({
            elem: '#lefanStartTime',
            done: function (value, date) {
                endTime.config.min = {
                    year: date.year,
                    month: date.month - 1,
                    date: date.date
                }
            }
        });
        lefanEndTime = laydate.render({
            elem: '#lefanEndTime',
            done: function (value, date) {
                if (value) {
                    startTime.config.max = {
                        year: date.year,
                        month: date.month - 1,
                        date: date.date
                    }
                } else {
                    startTime.config.max = {
                        year: 2100,
                        month: 1,
                        date: 1
                    }
                }
            }
        });

        form.on('submit(submit)', function (data) {
            Object.assign(paramSumbit, {
                surveyCaseNo: $('input[name=surveyCaseNo]').val(),
                archivesState: demo3.getValue('valueStr'),
                entrustOrgIds: demo1.getValue('valueStr'),
                surveyOrgIds: demo2.getValue('valueStr'),
                startTime: $('#startTime').val(),
                endTime: $('#endTime').val(),
                updStartTime: $('#updStartTime').val(),
                updEndTime: $('#updEndTime').val(),
                lefanStartTime: $('#lefanStartTime').val(),
                lefanEndTime: $('#lefanEndTime').val(),
            })
            reloadTable(_cols, paramSumbit)
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

        var _cols = [
            [
            {   type: 'checkbox',
                fixed: 'left',
                rowspan: 2,

            },{
                field: 'surveyCaseNo',
                minWidth: 150,
                title: '案件编号',
                fixed: 'left',
                rowspan: 2,
                event: 'case',
                style: 'color: #3ba9ff',
                templet : function(d){
                    return "<div title='"+d.surveyCaseNo+"'>"+d.surveyCaseNo+"</div>"
                }
            },{
                field: 'surveyPerson',
                minWidth: 150,
                title: '被调查人',
                rowspan: 2,
            },{
                field: 'entrustOrgName',
                minWidth: 150,
                title: '委托方机构',
                rowspan: 2,
                templet : function(d){
                    return "<div title='"+d.entrustOrgName+"'>"+d.entrustOrgName+"</div>"
                }
            },{
                field: 'surveyOrgName',
                minWidth: 150,
                title: '调查机构',
                rowspan: 2,
                templet : function(d){
                    return "<div title='"+d.surveyOrgName+"'>"+d.surveyOrgName+"</div>"
                }
            },{
                field: 'archivesTime',
                minWidth: 120,
                title: '更新时间',
                rowspan: 2,
                sort: true,
                templet: function (d) {
                    return d.archivesTime ? layui.util.toDateString(d.archivesTime, 'yyyy-MM-dd') : ''
                }
            },
                {
                    field: 'remark',
                    minWidth: 250,
                    title: '备注',
                    edit: 'text', templet: '#editRemark',
                    rowspan: 2,
                },
                {
                field: 'interviewRecord',
                minWidth: 100,
                title: '面访笔录',
                edit: 'text', templet: '#editInput1',
                rowspan: 2,
            },{
                title: '调取的医院、社保、体检资料',
                colspan: 3,
                align: 'center'
            },{
                title: '被保人身故资料',
                colspan: 4,
                align: 'center'
            },{
                title: '放弃理赔',
                colspan: 3,
                align: 'center'
            },{
                title: '其他调取资料',
                colspan: 7,
                align: 'center'
            },{
                field: 'entrustReportStartDate',
                minWidth: 150,
                title: '平台复审时间',
                rowspan: 2,
                sort: true,
                templet: function (d) {
                    return d.entrustReportStartDate ? layui.util.toDateString(d.entrustReportStartDate, 'yyyy-MM-dd') : ''
                }
            },{
                field: 'entrustReportEndDate',
                minWidth: 150,
                title: '保司终审时间',
                rowspan: 2,
                sort: true,
                templet: function (d) {
                    return d.entrustReportEndDate ? layui.util.toDateString(d.entrustReportEndDate, 'yyyy-MM-dd') : ''
                }
            },{
                field: 'archivesState',
                minWidth: 120,
                title: '归档状态',
                rowspan: 2,
                templet: function (d) {
                    var _html = ''
                    if(d.archivesState == '0'){
                        _html = '未归档'
                    }else if(d.archivesState == '1'){
                        _html = '归档完成'
                    }
                    return _html
                }
            },{
                field: 'archivesBy',
                minWidth: 120,
                title: '归档人',
                rowspan: 2,
            },{
                field: '',
                minWidth: 150,
                title: '操作',
                fixed: 'right',
                rowspan: 2,
                templet: function (d) {
                    var  _html = ''
                    if (d.archivesState == '0'){
                        _html = '<a class=" layui-btn layui-btn-xs operateMark" lay-event="mark">标记为归档完成</a>'

                    }else if (d.archivesState == '1'){
                        _html = '<a class=" layui-btn layui-btn-xs layui-btn-danger operateCancel" lay-event="cancel">撤销标记完成</a>'

                    }
                    return _html
                }
            }
            ],[
                {
                    field: 'medicalRecord',
                    minWidth: 100,
                    title: '病历档案',
                    edit: 'text', templet: '#editInput2',
                    
                }, {
                    field: 'medicalReport',
                    minWidth: 100,
                    title: '体检报告',
                    edit: 'text', templet: '#editInput3',

                }, {
                    field: 'socialInsurance',
                    minWidth: 150,
                    title: '社保医疗费用明细',
                    edit: 'text', templet: '#editInput4',
                    
                },{
                    field: 'isDeath',
                    minWidth: 130,
                    title: '是否身故',
                    templet: function(d){
                        return '<div id="XM-isDeath-' + d.id + '" ></div>'
                    }

                }, {
                    field: 'legalHeir',
                    minWidth: 150,
                    title: '法定继承人声明书',
                    edit: 'text', templet: '#editInput6',
                    
                }, {
                    field: 'legalHeirRelationship',
                    minWidth: 160,
                    title: '法定继承人关系证明',
                    edit: 'text', templet: '#editInput7',
                    
                },{
                    field: 'deathCertificate',
                    minWidth: 170,
                    title: '户籍注销或死亡证明',
                    edit: 'text', templet: '#editInput8',
                    
                },{
                    field: 'isGiveUp',
                    minWidth: 130,
                    title: '是否放弃',
                    templet: function(d){
                        return '<div id="XM-isGiveUp-' + d.id + '" ></div>'
                    }
                    
                },{
                    field: 'claimsGiveUp',
                    minWidth: 120,
                    title: '理赔放弃声明',
                    edit: 'text', templet: '#editInput10',
                    
                },{
                    field: 'otherStatement',
                    minWidth: 120,
                    title: '其他声明文件',
                    edit: 'text', templet: '#editInput11',
                    
                },{
                    field: 'isAccident',
                    minWidth: 130,
                    title: '是否意外',
                    templet: function(d){
                        return '<div id="XM-isAccident-' + d.id + '" ></div>'
                    }
                    
                },{
                    field: 'anAccident',
                    minWidth: 120,
                    title: '意外事故证明',
                    edit: 'text', templet: '#editInput13',
                    
                },{
                    field: 'noAccident',
                    minWidth: 170,
                    title: '无意外证明的原因',
                    edit: 'text', templet: '#editInput14',
                    
                },{
                    field: 'publicInspection',
                    minWidth: 160,
                    title: '公检法、单位等证明',
                    edit: 'text', templet: '#editInput15',
                    
                },{
                    field: 'appraisalReport',
                    minWidth: 100,
                    title: '鉴定报告',
                    edit: 'text', templet: '#editInput16',
                    
                },{
                    field: 'legalInstrument',
                    minWidth: 100,
                    title: '法律文书',
                    edit: 'text', templet: '#editInput17',
                    
                },{
                    field: 'otherItems',
                    minWidth: 90,
                    title: '其他项',
                    edit: 'text', templet: '#editInput18',
                    
                },

            ]
        ]

        var paramSumbit = {
            surveyCaseNo: '',
            archivesState: '0',
            entrustOrgIds: '',
            surveyOrgIds: '',
            startTime: '',
            endTime: '' ,
            updStartTime: '',
            updEndTime: '',
            lefanStartTime: '',
            lefanEndTime: ''
        }
        setTable(_cols, paramSumbit)

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
                cols: _cols,
                page: true,
                limit: 15,
                limits: [15,20,30,40,50],
                request: {
                    pageName: 'page' //页码的参数名称，默认：page
                    ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
                },
                height: fullH,
                drag: false,
                url: '${ctx}/surveyCaseArchives/getDetail',
                where: param,
                autoSort: false,
                parseData: function (res) {
                    sessionStorage.setItem('gdgl', JSON.stringify(res.results))
                    sessionStorage.setItem('gdglNew', JSON.stringify(res.results))
                    var results = res.results
                    return {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": results
                    }
                },
                done: function (res) {
                    soulTable.render(this)
                    $('button.ll-submit').removeAttr('disabled')

                    var _trs = $('.table-content .layui-table-box .layui-table-fixed .layui-table-body table tbody tr')

                    var _th = $('.table-content .layui-table-box .layui-table-fixed .layui-table-header table thead tr:first th:first')

                    res.data.map(function (cur,i) {
                        if (cur.archivesState == 1){
                            _trs.eq(i).find('td:first').css('pointer-events', 'none')
                            _trs.eq(i).find('td:first .laytable-cell-checkbox .layui-form-checkbox i').css('background', '#eee')
                        }
                    })
                    if (param.archivesState == '1' || param.archivesState == ''){
                        _th.css('pointer-events', 'none')
                        _th.find('.laytable-cell-checkbox .layui-form-checkbox i').css('background', '#eee')
                    }


                    $('.layui-table-main tbody td[data-field=isDeath] .layui-table-cell').css({
                        overflow: 'unset'
                    })
                    $('.layui-table-main tbody td[data-field=isGiveUp] .layui-table-cell').css({
                        overflow: 'unset'
                    })
                    $('.layui-table-main tbody td[data-field=isAccident] .layui-table-cell').css({
                        overflow: 'unset'
                    })

                    //渲染多选
                    res.data.forEach(item =>  {
                        var xm = xmSelect.render({
                            el: '#XM-isDeath-' + item.id,
                            autoRow: true,
                            radio: true,
                            size: 'small',
                            clickClose: true,
                            data: [
                                {name: '是', value: 1, xmName: 'isDeath',xmId: item.id },
                                {name: '否', value: 0, xmName: 'isDeath',xmId: item.id },
                            ],
                            on: function (data) {
                                //arr:  当前多选已选中的数据
                                var arr = data.arr;
                                //change, 此次选择变化的数据,数组
                                var change = data.change;
                                if (arr.length){
                                    operateXM({
                                        btnCode:"itemUpdate",
                                        colCode: arr[0].xmName,
                                        id: arr[0].xmId,
                                        value: arr[0].value
                                    })
                                }else{
                                    operateXM({
                                        btnCode:"itemUpdate",
                                        colCode: 'isDeath',
                                        id: item.id,
                                        value: ''
                                    })
                                }

                            },
                            model: {

                                    label: {
                                    type: 'xxxx', //自定义与下面的对应
                                    xxxx: {
                                        template(data, sels) {
                                            var _html = ''
                                            sels.filter(function (cur) {
                                                _html +=
                                                    '<div class="xm-label-block lf-select-block2">' + cur
                                                        .name + '</div>'
                                            })
                                            return _html
                                        }
                                    },
                                }
                            },
                        })
                        if (item.isDeath == 0 || item.isDeath == 1){
                            xm.setValue([item.isDeath])
                        }

                        item.__xm = xm;

                        var xm2 = xmSelect.render({
                            el: '#XM-isGiveUp-' + item.id,
                            autoRow: true,
                            radio: true,
                            clickClose: true,
                            size: 'small',
                            data: [
                                {name: '是', value: 1, xmName: 'isGiveUp',xmId: item.id },
                                {name: '否', value: 0, xmName: 'isGiveUp',xmId: item.id },
                            ],
                            on: function (data) {
                                //arr:  当前多选已选中的数据
                                var arr = data.arr;
                                //change, 此次选择变化的数据,数组
                                var change = data.change;
                                if (arr.length){
                                    operateXM({
                                        btnCode:"itemUpdate",
                                        colCode: arr[0].xmName,
                                        id: arr[0].xmId,
                                        value: arr[0].value
                                    })
                                }else{
                                    operateXM({
                                        btnCode:"itemUpdate",
                                        colCode: 'isGiveUp',
                                        id: item.id,
                                        value: ''
                                    })
                                }

                            },
                            model: {
                                label: {
                                    type: 'xxxx', //自定义与下面的对应
                                    xxxx: {
                                        template(data, sels) {
                                            var _html = ''
                                            sels.filter(function (cur) {
                                                _html +=
                                                    '<div class="xm-label-block lf-select-block2">' + cur
                                                        .name + '</div>'
                                            })
                                            return _html
                                        }
                                    },
                                }
                            },
                        })

                        if (item.isGiveUp == 0 || item.isGiveUp == 1){
                            xm2.setValue([item.isGiveUp])
                        }

                        item.__xm2 = xm2;

                        var xm3 = xmSelect.render({
                            el: '#XM-isAccident-' + item.id,
                            autoRow: true,
                            radio: true,
                            clickClose: true,
                            size: 'small',
                            data: [
                                {name: '是', value: 1, xmName: 'isAccident',xmId: item.id },
                                {name: '否', value: 0, xmName: 'isAccident',xmId: item.id },
                            ],
                            on: function (data) {
                                //arr:  当前多选已选中的数据
                                var arr = data.arr;
                                //change, 此次选择变化的数据,数组
                                var change = data.change;
                                if (arr.length){
                                    operateXM({
                                        btnCode:"itemUpdate",
                                        colCode: arr[0].xmName,
                                        id: arr[0].xmId,
                                        value: arr[0].value
                                    })
                                }else{
                                    operateXM({
                                        btnCode:"itemUpdate",
                                        colCode: 'isAccident',
                                        id: item.id,
                                        value: ''
                                    })
                                }

                            },
                            model: {

                                    label: {
                                    type: 'xxxx', //自定义与下面的对应
                                    xxxx: {
                                        template(data, sels) {
                                            var _html = ''
                                            sels.filter(function (cur) {
                                                _html +=
                                                    '<div class="xm-label-block lf-select-block2">' + cur
                                                        .name + '</div>'
                                            })
                                            return _html
                                        }
                                    },
                                }
                            },
                        })
                        if (item.isAccident == 0 || item.isAccident == 1){
                            xm3.setValue([item.isAccident])
                        }

                        item.__xm3 = xm3;
                    })
                }
            })

            function operateXM(params){
                $.ajax({
                    url:'${ctx}/surveyCaseArchives/operate',
                    type:"post",
                    data :params ,
                    success:function(res){
                        res = JSON.parse(res)
                        if (res.isSuccess){
                            layer.msg('更新成功',
                                {  time:2000,
                                    icon: 1
                                });
                            var newValue = res.results
                            var gdgl2 = []
                            var gdgl = JSON.parse(sessionStorage.getItem('gdgl'))

                            gdgl2 = gdgl.map(function (cur, index) {
                                if (cur.id === params.id) {
                                    cur = newValue
                                }
                                return cur
                            })

                            sessionStorage.setItem('gdgl', JSON.stringify(gdgl2))
                        } else {
                            layer.msg(res.msg,{time:2000,icon: 2});
                        }
                    }
                });
            }
            table.on('edit(test)', function (obj) {
                var value = obj.value.replace(/\s+/g, ""), //得到修改后的值
                    data = obj.data, //得到所在行所有键值
                    field = obj.field, //得到字段
                    gdgl = JSON.parse(sessionStorage.getItem('gdgl')) || []
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
                gdgl.forEach(function (cur, index) {
                    if (cur.id === data.id) {
                        oldValue = cur[field]
                    }
                })
                var flag = true
                if (field != 'remark'){
                    if (value || value == 0){// >=0
                        flag = checkPapers('integer', value)
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
                            value = Math.abs(value)
                        }
                    }
                }


                //请求后台，返回当条数据更新 修改值 及 涉及（实发工资）
                $.ajax({
                    url:'${ctx}/surveyCaseArchives/operate',
                    type:"post",
                    data :{
                        btnCode:"itemUpdate",
                        colCode: field,
                        id: data.id,
                        value: value,
                        surveyInfoId : data.surveyInfoId
                    } ,
                    success:function(res){
                        res = JSON.parse(res)
                        if (res.isSuccess){
                            layer.msg('【'+fieldName+ ':' + value +'】 更新成功',
                                {  time:2000,
                                    icon: 1
                                });
                            var newValue = res.results
                            console.log('----------------------------',newValue)
                            // obj.update(newValue)
                            obj.update({
                                [field]: newValue[field]
                            })
                            var gdgl2 = []

                            gdgl2 = gdgl.map(function (cur, index) {
                                if (cur.id === data.id) {
                                    cur = newValue
                                }
                                return cur
                            })

                            sessionStorage.setItem('gdgl', JSON.stringify(gdgl2))
                            reloadTable(_cols, paramSumbit)
                        } else {
                            obj.update({
                                [field]: oldValue
                            })
                            layer.msg(res.msg,{time:2000,icon: 2});
                        }
                    }
                });
            });
            table.on('tool(test)', function(obj){
                var _this = $(this)

                if (obj.event == 'mark'){
                    operate({
                        id: obj.data.id,
                        btnCode: 'finish',
                    })
                    _this.addClass('poi-no')
                    setTimeout(function () {
                        _this.removeClass('poi-no')

                    },1000)
                }else if (obj.event == 'cancel'){
                    operate({
                        id: obj.data.id,
                        btnCode: 'revoke',
                    })
                    _this.addClass('poi-no')
                    setTimeout(function () {
                        _this.removeClass('poi-no')

                    },1000)
                }else if (obj.event == 'case'){
                    jumpPage(obj.data.surveyInfoId)
                }
            })

            table.on('sort(test)', function(obj){ //注：sort 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                console.log(obj.field); //当前排序的字段名
                console.log(obj.type); //当前排序类型：desc（降序）、asc（升序）、null（空对象，默认排序）

                var order = ''
                if (obj.field == 'entrustReportEndDate'){
                    order = 50
                }else if (obj.field == 'archivesTime'){
                    order = 51
                }else if (obj.field == 'entrustReportStartDate') {
                    order = 52
                }
                var colSortType = ''
                if (obj.type == 'desc'){
                    colSortType = 2
                }else if (obj.type == 'asc'){
                    colSortType = 1
                }
                var param = Object({
                    order: order,
                    colSortType:colSortType
                },paramSumbit)

                table.reload('test', {
                    initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。
                    ,where: param
                });

            });

            function operate(param) {
                $.ajax({
                    url: '${ctx}/surveyCaseArchives/operate',
                    data: param,
                    success: function (res) {
                        var res = JSON.parse(res)
                        if (res.isSuccess){
                            layer.msg('操作成功', {
                                time: 2000,
                                icon: 1
                            },function () {
                                reloadTable(_cols, paramSumbit)
                            })
                        }else {
                            layer.msg(res.msg, {
                                time: 2000,
                                icon: 2
                            }, function () {
                                reloadTable(_cols, paramSumbit)
                            })
                        }

                    }
                })
            }

            $('.layui-mark').off('click').on('click',function (e) {
                var _this = $('.layui-mark')

                var data = table.checkStatus('test').data;
                var ids = []
                data.map(function (cur) {
                    if (cur.archivesState == '0'){
                        ids.push(cur.id)
                    }

                })
                if (!ids.length) {
                    layer.msg('至少选中一条数据', {
                        time: 2000,
                        icon: 5
                    })
                    return;
                }
                var _flag = true
                layer.confirm('确定批量标记归档？', {
                    btn: ['确定','取消'] //按钮
                }, function(){
                    if (_flag){
                        _flag = false
                        operate({
                            ids: ids.join(','),
                            btnCode: 'finishAll',
                        })
                    }

                }, function(){

                });

            })

            $('.layui-export').off('click').on('click',function (e) {
                var _this = $('.layui-export')
                _this.attr('disabled',true)
                var _href = '${ctx}/surveyCaseArchives/export'
                for(var key in paramSumbit){
                    if (key == 'surveyCaseNo'){
                        _href +='?'+key +'='+paramSumbit[key]
                    }else{
                        _href +='&'+key +'='+paramSumbit[key]

                    }
                }
                var aLink = document.createElement('a');
                aLink.href= _href
                aLink.dispatchEvent(new MouseEvent('click', {
                    bubbles: true,
                    cancelable: true,
                    view: window
                }));
                setTimeout(function () {
                    _this.removeAttr('disabled')
                },5000)
            })
        }


        function getPrescription(d) {
            var day = parseInt(d / (60 * 60 * 24))
            var second = d % (60 * 60 * 24)
            var hour = parseInt(second / (60 * 60))
            var third = second % (60 * 60)
            var minute = parseInt(third / 60)
            var _html = day+ '天' +hour + '小时' + minute + '分钟'
            return _html
        }
        function getFixed(d) {
            var _d = Math.round(d * 100) / 100
            return _d
        }

        function getRate(d) {
            var _d = Math.round(d * 10000) / 100
            return _d + '%'
        }

        function onRowEvent(obj, pobj,name) {
            if (obj.event === 'export') {
                soulTable.export(obj.config.id,{
                    filename: '积分报表('+ name+').xlsx'
                })
            }
        }

        function getOfTime(d) {
            if (d > 0) {
                _html = '<div class="color2">' + d + '天</div>'
            } else if (d < 0) {
                _html = '<div class="color1">' + Math.abs(d) + '天</div>'

            } else if (d == 0){
                _html = '0天'
            }
            return _html

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
            if (!paramb){
                return true;
            }
            if (map.get(parama).test(paramb)) {
                return true
            } else {
                return false
            }
        }




    })

    var jumpPage = function(id){
        var width = $(document.body).outerWidth();
        var height = $(document).outerHeight() - 20;
        openDialog({
            title:'案件详情',
            frame:true,
            height:height,
            width:width,
            url:"${ctx}/survey/case/info?id=" + id + "&menuCode=all-list",
        });
    }
</script>



</body>

</html>