<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>年度汇总报表</title>
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
            width: 80px;
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

        /*.layui-table-header .layui-table-cell{*/
        /*display: flex;*/
        /*align-items: center;*/
        /*}*/

        .icon-about{
            margin-left: 6px;
            display: inline-block;
            width: 18px;
            height: 18px;
            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAALHklEQVR4Xu1dTXYbNxKuapOZSTbDPFFr0ztTm1FOYPoEppcTap7lE1g+QZgTmDmB6BcxXkY+gZkTRNqI2llZi3phNqJDUl156CYlqsWfRgGNBtjNnZ/REFD1of5RQMh/maYAZnr3+eYhB0DGQZADIAdAximQ8e3nEiAHQMYpkPHt5xIgB0DGKZDx7ecSIAdAximQ8e1nUgJUDkulf/+r8N953p9/3/8ti1jYSADsdLZ3AfzHALhLALtAUELEWmwGEw0I4AQAThBh4CN0ASd/nP9vcBF7DkcGbgQABMMJ4BkA1ZCgBoilJOhPABcI0PXJ734Z33y8eD0YJPF3TM7pLAACphO9AsQ6AlRMEm3ub50QQHs4Gr93FQxOAeDph1IF/eILBNgHIdot+hHQMREdn+9dvbdoWWuX4gQABOM9v/gDhIy3+ifUBJHfdEVFWA0Alxj/AJVEA0BoXY8mP9msHqwEgHDTvi4WfkDEA6uPe5zFEQ18oANbVYN1AHh6tPXKA2wlZcnH4VkSY4ioi4hvzxqXwr205mcNAAID76ZwKOWvW0PG+AshotZwPPnRFrVgBQCqv2zV0cfDTTv1K2AhpMBrG6RB6gCoHpXfbYSujy8EwpHCNkB6e964ast+qnN8agAIw7VwaJs/r5O4MedqnzUuX8ccq31YKgAImE/0KUMifyXjhIE4HE9epmEXGAdAzvylWDi5Ho2fmwaBUQA87Wzte4Tv8pO/HAS+N35pMutoDACBpU/er9qV2IZNKELJw9H4O1OSwAgAcrEvjVJj6iBxAOTMl2b+7AMjIEgUACKm/02x8DnX+WwQJO4iJgqA6lH5k8uhXQL6A0Se38fjeRaiR3UAUYiCj9msjf0h/XjW6DdjD5ccmBgAdjrlJgCKHL5zPwL6Cwiavb1+a9Xiq52tFoL3JukN+j49P/9/v5vE30kEAE9/Ltc8Dz8lseCk5xSnHgHrceP01aPyASK+S3RdRIPr8eRJEp6BdgC4rPfFyUfAWlzmz5he7ZQvklYHIlrY2+s/1w007QCoHm23EeGV7oWamY+nb4MAF3gir5Hozwf/te7kkVYAOC76/xqOJhWOmJ0mtn5PlPti8gRUgVYAVDvbn1Ms0VakP308a/Tr3El2OtvE/VbmOwL/p17jSlupnDYAuGz1hwzgiX/xZWD3fFX8U4aRimO/k7VTlv09LQBw2fC7IwwfAKbzHDoNQi0AcP/0C/UK73t7l6x7B2kYvrpiA8oA2IzTDyCycL3G5RNZ0Ty9u/BZ9jv18Wo2y+zvKwNgE07/jBiybtZU94uAVyrX1Hxv/ES1dkAZAG5b/pFzGNzmwedxDKy0mR96hXy1pUUCmDZ+1MVmjBlEtS7By1Wxd2sur2iICyhJgDSMnxgs1DJE2ARAdCwaRADQiWg2AYAVIKrblN6WVVtR4rABkILvq4WxmzaJqkvIBoCp+PemMSyJ/agYg2wA7HTKxwD4IokN5XPKUYCI3q6rXdAeCTQV+5YjhdpoAvoNCC8Q6bYZFAGVzFX/cNfPjwmwJIDLWb8oiYUrBZ5/3Pv+6l7ZV3Sc8HiAsI2A/+GyKbHviAZne/1vOfOzAOB68Ccs/Ai6d7Rk0r9hNzLq2ggCbmiYBYBqpyyI8IyDuLS/ESd+OB4fyDB+fs32gp+XzGIBwEX9L2r9yId91eLK9GL/q4+OsF96jX78ZpjT6aQBYCsBVpFHEGc4mtS5pz46t40HgJvMkgeAYxW/OuLlCwAgyr9SSQCtAvpZ41Kan9IfGCmD1mQoLGL+TmfrhU9QUunaZaMEmJJMulJIHgCGLkOoYiDKfOG6ooeHs5pFQv/lOtdv2RpsBQBnTwwA2O8BPGD+grJtDrEEIIxVALNOgLwnsHEAiFrDy1LW16Pxtxyj0G4VmHUAEJxej8e1GWOXX03nh07tzoEYAMBOZ9tKCzh6rStoN/tV8fdF9xRUcug7R+U/baoHmNcUnDsD0irAWgMokhFbdTWdLf4tb3PDCQZtBABElK/X6N8+GrH62jZf/NteAZVZAADcMXVdoYqK+Le9ANYIAExchZb2gKYt2T3ECgCu7KbBFf92u38hxThRT2kV4HImcF5SyILMVDcQ2XXdH2/AC3AZAJss/qcyQLqfUGYkgHATe40+6zk5F8S/OQA42gGEox9n4tXu6N+dEuBIOGkJYG9FzJqCCbXkj5XBr+iOOWVh0gBwsSBURfy7VABjpB7AHX14dz6yIP65IJeWANOUqJF+OGou0RwAlMS/GxdgOEEgQSEeAI62TwDh3vPrupilex7uyRDrcOn+IycRxAaAG0ERfnRsBsJ1YWXdYFWZj1vgwpIALvUF4BImVHVuiH+xVm6ImwUAV0RjNEsoe8JsTX1H98HV/2wVEJwOB+wArl4U+1sn5YRtQUAHSF4t/da48jmAGYhYEiAgkIku2bJH9uF46TLpu+jf8p7H89VHlkhD9j7ZALA9QKIs/leUfs3bFWnHRVT3yQZAIAUsviSqFPxZUfoVbcZQ7ZR/Fe8LqAsr3gwqzSGUbADxsc1ukor1v6z0KwqqdXYCj6VyX6m0h1EGgCX6byHFuG5RaOAuqPwlOD3bu7y9D2hDh1QV61/ZCIxjLMlhWePoCLNkZl5o20TuG4QxgvTL4znZvygtlGyAQA18KFU8v5hCr9zlbFVx/x5kOxczX7wOwmosLQPGVWNVjT9tEiB0Ce16JoZTGDEjyDygBZCGo5vm/BWync526swXa1XZ4zywlCWAjVJAVTQKEHz5AoPo3UFbmK/r9CsbgfNIskkKqBiAy8SuLcwX61PxcLTbALMJw7t4BfF8Wupt1DiVMcsYP93XYZq+/vzadFj+2lXAnUdg4BHFGFaULgBMo3xC51vTDkbV709MAswmtiFJpAMA1c72GyRq2nUTmJ/0WXZutBiB85NbUjTKTo4IAxD9wjtbRP6MtsLwG44mu5ymFquEpnYAhEGSdB+O5sQBwqhm4Q0QHNh16m/Zxwa1cQAEsYGUE0Vx/eTQ7y+8spjxoJrwSQUANngFRNRCxPfRN4DCnr/wDIBqton6h8zi9zOIYS/zqoLjTBwEiCxqKik6aSJRyVLxvpikC8LQcWkfd1wiNsA9o9DQy9pxN+zKOO5T9rL7SxwAgSTIQSDFF1PMF4syAoDQKNxqIXhvpCiR3cGJWPyLyGkMAAEILMsa2oavWaXxeeOqbWptRgEQgsCOcLEpAsf9OybF/vyajAMgtwkWOHrBEzZYi/NkbVxAxR2XCgBmIEDAlg3Zw7jESmQcwan/aFxXfQSau7bUACAWHPbyhbYrN425RF72ner7RTrWkyoAxAaCiGGx2Er/epUOcsabIw1jb9nKUgfAbGFWv8sXj6/xRhGcAsJ+Gvo+dTdwHYU2WRqIUw8ETe4Tr+tox/1/ayTA/AaCHAJia1NsAxt0vfUqYNECRQgZAZsI+JiL8DS/E/V75ENT9a3CJPdgpQSIbtg1IIgTT0Rtmxk/o7ETAJg3FJFw38Zn60Mdj8f0aNxMy6fnSAqnADDbYGgsFoR62E/fTqCPhNTmPkHHYZrOb5wEwD2D8UOpAv6jmgdYJwBR4ZPsvQSCU0K/Cwjd4d83Xd1FmjqZG2cu5wEQ3WRQ7kVUAyTxeISo56+wjcjAZ6cLADjxgS7Au+m6JN4zCYBlmxbA8H26bRePCLuIEPw7YK6PgtHhrzC52DRGO+kGxkFwPkaNAhunAtTIkb2vcwBkj+f3dpwDIAdAximQ8e3nEiAHQMYpkPHt5xIgB0DGKZDx7ecSIAdAximQ8e3nEiDjAPgHdLv2zPFLC+sAAAAASUVORK5CYII=);
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
            height: 500px;
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

        tr.active td{
            background-color: #b7d8f0;
        }
        tr.active2 td, .active2{
            background-color: #9ed286!important;
        }

        .tc-title{
            line-height: 30px;
            font-size: 14px;
            font-weight: bold;
        }
        .layui-table{
            margin-top: 0!important;
        }
        .export-btn{
            width: 90%;margin: 0 auto;text-align: center;display: flex;justify-content: center;
        }
    </style>
</head>

<body>
<div class="main">
    <input type="hidden" value='${orgInfoDtosGG}' id="orgInfoDtosGG" />
    <input type="hidden" value='${orgInfoDtosGR}' id="orgInfoDtosGR" />
    <input type="hidden" value='${bullingEnums}' id="bullingEnums" />
    <input type="hidden" value='${corporations}' id="corporations" />
    <div class="layui-form searchs" lay-filter="search">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">公估机构:</label>
                <div class="layui-input-inline">
                    <div id="companys" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">个人机构:</label>
                <div class="layui-input-inline">
                    <div id="organs" class="selectMul"></div>
                </div>

            </div>
            <div class="layui-inline">
                <label class="layui-form-label">开票产品:</label>
                <div class="layui-input-inline">
                    <div id="departments" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">开票公司:</label>
                <div class="layui-input-inline">
                    <div id="businessType" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <div class="d-flex-wrap">
                    <label class="layui-form-label">截止时间</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input paramTime" readonly id="endTime"
                               placeholder="请选择日期">
                    </div>
                </div>
            </div>
            <div class="layui-inline" style="margin-left: 20px;">
                <button lay-submit class="ll-submit layui-btn layui-btn-normal layui-btn-sm layui-btn-radius"
                        lay-filter="submit" style="width: 86px">查询 <i
                        class="layui-icon layui-icon-search"></i></button>
                <c:if test="${finance}">
                    <button  class="s-btn layui-btn layui-btn-normal  layui-btn-sm layui-btn-radius" style="width: 86px" data-type="all" id="all">总表 <i class="layui-icon layui-icon-rate" ></i></button>
                </c:if>
                <button  class="s-btn layui-btn layui-btn-normal  layui-btn-sm layui-btn-radius" style="width: 86px" data-type="export" id="export">导出 <i class="layui-icon layui-icon-export" ></i></button>
            </div>
        </div>
    </div>
    <div class="table-content">
        <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">
        </table>
    </div>
    <%--<div class="tc-title">上年汇总表</div>--%>
    <%--<div class="table-content table-content1">--%>
        <%--<table class="layui-table" id="testLast" lay-filter="testLast" lay-skin="line" lay-size="lg">--%>
        <%--</table>--%>
    <%--</div>--%>
    <%--<div class="tc-title">本年汇总表</div>--%>
    <%--<div class="table-content table-content2">--%>
        <%--<table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">--%>
        <%--</table>--%>
    <%--</div>--%>
    <%--<div class="tc-title">本年度累计开票/到账表</div>--%>
    <%--<div class="table-content table-content3">--%>
        <%--<table class="layui-table" id="testSum" lay-filter="testSum" lay-skin="line" lay-size="lg">--%>
        <%--</table>--%>
    <%--</div>--%>
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

<script type="text/html" id="table-content-child-h">
    <div class="table-content-child">
        <table class="layui-table" id="test-child" lay-filter="test-child" lay-skin="line" lay-size="lg" style="margin: 0">
        </table>
    </div>
</script>

<script src="${ctx}/js/jquery-3.4.1.js" charset="utf-8"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jQuery.UCSelect2.js?V=1"></script>
<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        soulTable: 'soulTable',
        xmSelect: 'xm-select'
    })

    var searchType = 'upMonth'
    vals = ['', '']


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
            // vals = [y1 + '-' + m1 + '-01', y1 + '-' + m1 + '-' + days1]
            vals = [y1 + '-' + m1, y1 + '-' + m1 ]

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
            vals = [y + '-' + PrefixInteger(m,2)+'-01' , y + '-' + PrefixInteger(m,2) + '-'+d ]
        } else if (id == 'all') {
            vals = ['2019-02-27', y + '-' + m + '-' + d]
        } else if (id == 'initMonth') {
            vals = [y + '-01-01',y + '-01-01']
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

    //调查员数量
    var _len = 0

    var startTime = '',
        endTime = ''
    layui.use(['form', 'table', 'soulTable', 'xmSelect', 'laydate', 'layer'], function () {
        var soulTable = layui.soulTable,
            table = layui.table,
            form = layui.form,
            xmSelect = layui.xmSelect,
            laydate = layui.laydate,
            layer = layui.layer,
            $ = layui.$;

        var demo1 = xmSelect.render({
            el: '#companys',
            theme: {
                color: '#3BA9FF',
            },
            radio: true,
            size: 'small',
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
                            var _html = ''
                            sels.filter(function (cur) {
                                _html +=
                                    '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                            })
                            return _html
                        }
                    },
                }
            },
            on: function(data){
                var arr = data.arr;
                if (arr.length){
                    demo2.setValue([])
                }
            },
            data: []
        })
        var demo2 = xmSelect.render({
            el: '#organs',
            theme: {
                color: '#3BA9FF',
            },
            radio: true,
            size: 'small',
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
                            var _html = ''
                            sels.filter(function (cur) {
                                _html +=
                                    '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                            })
                            return _html
                        }
                    },
                }
            },
            on: function(data){
                var arr = data.arr;
                if (arr.length){
                    demo1.setValue([])
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
            el: '#businessType',
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

        var orgInfoDtosGG = $("#orgInfoDtosGG").val();
        orgInfoDtosGG = JSON.parse(orgInfoDtosGG);
        filterJson(demo1, orgInfoDtosGG, 'id', 'orgName', false, false)
        demo1.setValue([orgInfoDtosGG[0].id])

        var orgInfoDtosGR = $("#orgInfoDtosGR").val();
        orgInfoDtosGR = JSON.parse(orgInfoDtosGR);
        filterJson(demo2, orgInfoDtosGR, 'id', 'orgName', false, false)

        var bullingEnums = $("#bullingEnums").val();
        bullingEnums = JSON.parse(bullingEnums);
        filterJson(demo3, bullingEnums, 'enumCode', 'enumName', false, false)

    var corporations = $("#corporations").val();
    corporations = JSON.parse(corporations);
    filterJson(demo4, corporations, 'id', 'name', false, false)

        var initVals = setDate('upMonth')
        var curVals = setDate('curMonth')
        var initMonth = setDate('initMonth')
        endTime = laydate.render({
            elem: '#endTime',
            value: initVals[0],
            // min: initMonth[0],
            max: curVals[1],
            type: 'month',
        });

        form.on('submit(submit)', function (data) {
            var ids1 = demo1.getValue('valueStr') ? demo1.getValue('valueStr').split(',') : []
            var ids2 = demo2.getValue('valueStr') ? demo2.getValue('valueStr').split(',') : []
            var ids = ids1.concat(ids2)
            paramSubmit = {
                orgId: ids.toString(),
                enumCode: demo3.getValue('valueStr'),
                dateTimes: $('#endTime').val(),
                businessType: demo4.getValue('valueStr'),
                annual: '12'
            }
            setTable(paramSubmit)
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
        // function reloadTable(paramSubmit) {
        //     $('.table-content1').empty()
        //     $('.table-content1').append(
        //         ' <table class="layui-table" id="testLast" lay-filter="testLast" lay-skin="line" lay-size="lg"></table>'
        //     )
        //     $('.table-content2').empty()
        //     $('.table-content2').append(
        //         ' <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg"></table>'
        //     )
        //     $('.table-content3').empty()
        //     $('.table-content3').append(
        //         ' <table class="layui-table" id="testSum" lay-filter="testSum" lay-skin="line" lay-size="lg"></table>'
        //     )
        //     setTable(paramSubmit)
        // }


        var paramSubmit = {
            orgId: demo1.getValue('valueStr'),
            enumCode: demo3.getValue('valueStr'),
            dateTimes: initVals[1],
            businessType: demo4.getValue('valueStr'),
            annual: '12',
        }

        function setDates(){
            var today = new Date(),
                y = today.getFullYear(),
                m = today.getMonth() + 1,
                list = []

             for(i=1;i<m+1;i++){
                 list.push({
                     ym: y+PrefixInteger(m,2),
                     m: m
                 })
             }
             return list
        }

        var sumList = []

        var _cols2 = [
            {
                field: 'imgMoney',
                minWidth: 120,
                title: '本月开票金额',
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.imgMoney ? Number(d.imgMoney).toLocaleString('en-US') : '';
                }
            },
            {
                field: 'monthImgMoneyHBRate',
                minWidth: 100,
                title: '环比',
                templet: function (d) {
                    var _html =  Math.round(d.monthImgMoneyHBRate * 10000) /100+ '%'
                    return _html
                },
            },
            {
                field: 'monthImgMoneyTBRate',
                minWidth: 120,
                title: '同比',
                templet: function (d) {
                    var _html =  Math.round(d.monthImgMoneyTBRate * 10000) /100+ '%'
                    return _html
                },
            },
            {
                field: 'accMoney',
                minWidth: 120,
                title: '本月到账金额'
            },
            {
                field: 'monthAccMoneyHBRate',
                minWidth: 100,
                title: '环比',
                templet: function (d) {
                    var _html =  Math.round(d.monthAccMoneyHBRate * 10000) /100+ '%'
                    return _html
                },
            },
            {
                field: 'monthAccMoneyTBRate',
                minWidth: 120,
                title: '同比',
                templet: function (d) {
                    var _html =  Math.round(d.monthAccMoneyTBRate * 10000) /100+ '%'
                    return _html
                },
            },
            {
                field: 'yearImgMoneyYear',
                minWidth: 150,
                title: '本年累计开票金额',
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.yearImgMoneyYear ? Number(d.yearImgMoneyYear).toLocaleString('en-US') : '';
                }

            },
            {
                field: 'yearImgMoneyTBRate',
                minWidth: 120,
                title: '同比',
                templet: function (d) {
                    var _html =  Math.round(d.yearImgMoneyTBRate * 10000) /100+ '%'
                    return _html
                },
            },
            {
                field: 'yearAccMoneyYear',
                minWidth: 150,
                title: '本年累计到账金额',
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.yearAccMoneyYear ? Number(d.yearAccMoneyYear).toLocaleString('en-US') : '';
                }
            },
            {
                field: 'yearAccMoneyTBRate',
                minWidth: 120,
                title: '同比',
                templet: function (d) {
                    var _html =  Math.round(d.yearAccMoneyTBRate * 10000) /100+ '%'
                    return _html
                },
            },
            {
                field: 'hisReceMoneyYear',
                minWidth: 140,
                title: '历史应收金额',
                templet: function(d) {
                    // 添加千分位分隔符
                    return d.hisReceMoneyYear ? Number(d.hisReceMoneyYear).toLocaleString('en-US') : '';
                }
            },
            /*{
                field: 'hisReceMoneyTBRate',
                minWidth: 120,
                title: '同比',
                templet: function (d) {
                    var _html =  Math.round(d.hisReceMoneyTBRate * 10000) /100+ '%'
                    return _html
                },
            },*/
        ]
        setTable(paramSubmit)


        function setTable(param,allFlag) {
            $('.table-content').empty()
            $('.table-content').append(
                ' <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg"></table>'
            )
            $('button.ll-submit').attr('disabled', true)
            setTimeout(function () {
                $('button.ll-submit').removeAttr('disabled')
            }, 6000)
            layer.load()
            /*if (!allFlag && !param.orgId){
                layer.msg('请选择机构！',{icon: 5},function () {
                    layer.closeAll()
                })
                return;
            }*/
            $.ajax({
                url: '${ctx}/billingApplyUnmatch/claim',
                data: param,
                success: function (res) {
                    layer.closeAll()
                    res = JSON.parse(res)
                    $('button.ll-submit').removeAttr('disabled')
                    if (allFlag){
                        _cols2 = [
                            {
                                field: 'unmatchMoney',
                                minWidth: 120,
                                title: '未匹配收款'
                            },
                            {
                                field: 'imgMoney',
                                minWidth: 120,
                                title: '本月开票金额',
                                templet: function(d) {
                                    // 添加千分位分隔符
                                    return d.imgMoney ? Number(d.imgMoney).toLocaleString('en-US') : '';
                                }
                            },
                            {
                                field: 'monthImgMoneyHBRate',
                                minWidth: 100,
                                title: '环比',
                                templet: function (d) {
                                    var _html =  Math.round(d.monthImgMoneyHBRate * 10000) /100+ '%'
                                    return _html
                                },
                            },
                            {
                                field: 'monthImgMoneyTBRate',
                                minWidth: 120,
                                title: '同比',
                                templet: function (d) {
                                    var _html =  Math.round(d.monthImgMoneyTBRate * 10000) /100+ '%'
                                    return _html
                                },
                            },
                            {
                                field: 'accMoney',
                                minWidth: 120,
                                title: '本月到账金额'
                            },
                            {
                                field: 'monthAccMoneyHBRate',
                                minWidth: 100,
                                title: '环比',
                                templet: function (d) {
                                    var _html =  Math.round(d.monthAccMoneyHBRate * 10000) /100+ '%'
                                    return _html
                                },
                            },
                            {
                                field: 'monthAccMoneyTBRate',
                                minWidth: 120,
                                title: '同比',
                                templet: function (d) {
                                    var _html =  Math.round(d.monthAccMoneyTBRate * 10000) /100+ '%'
                                    return _html
                                },
                            },
                            {
                                field: 'yearImgMoneyYear',
                                minWidth: 150,
                                title: '本年累计开票金额',
                                templet: function(d) {
                                    // 添加千分位分隔符
                                    return d.yearImgMoneyYear ? Number(d.yearImgMoneyYear).toLocaleString('en-US') : '';
                                }
                            },
                            {
                                field: 'yearImgMoneyTBRate',
                                minWidth: 120,
                                title: '同比',
                                templet: function (d) {
                                    var _html =  Math.round(d.yearImgMoneyTBRate * 10000) /100+ '%'
                                    return _html
                                },
                            },
                            {
                                field: 'yearAccMoneyYear',
                                minWidth: 150,
                                title: '本年累计到账金额',
                                templet: function(d) {
                                    // 添加千分位分隔符
                                    return d.yearAccMoneyYear ? Number(d.yearAccMoneyYear).toLocaleString('en-US') : '';
                                }
                            },
                            {
                                field: 'yearAccMoneyTBRate',
                                minWidth: 120,
                                title: '同比',
                                templet: function (d) {
                                    var _html =  Math.round(d.yearAccMoneyTBRate * 10000) /100+ '%'
                                    return _html
                                },
                            },
                            {
                                field: 'hisReceMoneyYear',
                                minWidth: 140,
                                title: '历史应收金额',
                                templet: function(d) {
                                    // 添加千分位分隔符
                                    return d.hisReceMoneyYear ? Number(d.hisReceMoneyYear).toLocaleString('en-US') : '';
                                }
                            },
                            /*{
                                field: 'hisReceMoneyTBRate',
                                minWidth: 120,
                                title: '同比',
                                templet: function (d) {
                                    var _html =  Math.round(d.hisReceMoneyTBRate * 10000) /100+ '%'
                                    return _html
                                },
                            },*/
                        ]
                        _cols1 = [
                            {
                                field: 'enumName',
                                width:120,
                                title: '机构/产品',
                                align: 'center',
                                rowspan: 2,
                                fixed: 'left'
                            },
                                {
                                    title: '总表',
                                    colspan: 13,
                                    align: 'center'
                                }
                            ]
                        var _cols = [, _cols2]
                    }else {
                        _cols2 = [
                            {
                                field: 'imgMoney',
                                minWidth: 120,
                                title: '本月开票金额',
                                templet: function(d) {
                                    // 添加千分位分隔符
                                    return d.imgMoney ? Number(d.imgMoney).toLocaleString('en-US') : '';
                                }
                            },
                            {
                                field: 'monthImgMoneyHBRate',
                                minWidth: 100,
                                title: '环比',
                                templet: function (d) {
                                    var _html =  Math.round(d.monthImgMoneyHBRate * 10000) /100+ '%'
                                    return _html
                                },
                            },
                            {
                                field: 'monthImgMoneyTBRate',
                                minWidth: 120,
                                title: '同比',
                                templet: function (d) {
                                    var _html =  Math.round(d.monthImgMoneyTBRate * 10000) /100+ '%'
                                    return _html
                                },
                            },
                            {
                                field: 'accMoney',
                                minWidth: 120,
                                title: '本月到账金额'
                            },
                            {
                                field: 'monthAccMoneyHBRate',
                                minWidth: 100,
                                title: '环比',
                                templet: function (d) {
                                    var _html =  Math.round(d.monthAccMoneyHBRate * 10000) /100+ '%'
                                    return _html
                                },
                            },
                            {
                                field: 'monthAccMoneyTBRate',
                                minWidth: 120,
                                title: '同比',
                                templet: function (d) {
                                    var _html =  Math.round(d.monthAccMoneyTBRate * 10000) /100+ '%'
                                    return _html
                                },
                            },
                            {
                                field: 'yearImgMoneyYear',
                                minWidth: 150,
                                title: '本年累计开票金额',
                                templet: function(d) {
                                    // 添加千分位分隔符
                                    return d.yearImgMoneyYear ? Number(d.yearImgMoneyYear).toLocaleString('en-US') : '';
                                }
                            },
                            {
                                field: 'yearImgMoneyTBRate',
                                minWidth: 120,
                                title: '同比',
                                templet: function (d) {
                                    var _html =  Math.round(d.yearImgMoneyTBRate * 10000) /100+ '%'
                                    return _html
                                },
                            },
                            {
                                field: 'yearAccMoneyYear',
                                minWidth: 150,
                                title: '本年累计到账金额',
                                templet: function(d) {
                                    // 添加千分位分隔符
                                    return d.yearAccMoneyYear ? Number(d.yearAccMoneyYear).toLocaleString('en-US') : '';
                                }
                            },
                            {
                                field: 'yearAccMoneyTBRate',
                                minWidth: 120,
                                title: '同比',
                                templet: function (d) {
                                    var _html =  Math.round(d.yearAccMoneyTBRate * 10000) /100+ '%'
                                    return _html
                                },
                            },
                            {
                                field: 'hisReceMoneyYear',
                                minWidth: 140,
                                title: '历史应收金额',
                                templet: function(d) {
                                    // 添加千分位分隔符
                                    return d.hisReceMoneyYear ? Number(d.hisReceMoneyYear).toLocaleString('en-US') : '';
                                }
                            },
                            /*{
                                field: 'hisReceMoneyTBRate',
                                minWidth: 120,
                                title: '同比',
                                templet: function (d) {
                                    var _html =  Math.round(d.hisReceMoneyTBRate * 10000) /100+ '%'
                                    return _html
                                },
                            },*/
                        ]
                        _cols1 = [
                            {
                                field: 'enumName',
                                width:120,
                                title: '机构/产品',
                                align: 'center',
                                rowspan: 2,
                                fixed: 'left'
                            },
                            {
                                title: res.results.orgName,
                                colspan: 12,
                                align: 'center'
                            }
                        ]
                    }



                    var _h = $('.searchs').outerHeight() + 50
                    var fullH = 'full-' + _h

                    var list = res.results.enumMoneyList
                    var sumAll = '',sumIndex=''
                    list.map(function (cur,i) {
                        if (cur.enumCode == 99999){
                            sumAll = cur
                            sumIndex = i
                        }
                    })

                    console.log(sumAll)
                    if (sumAll){
                        _cols2.map(function (cur,i) {
                            _cols2[i].totalRow = true
                        })
                        _cols1[0].totalRowText = sumAll.enumName
                        var newList = [].concat(list)
                        newList.splice(sumIndex,1)
                        myTable = table.render({
                            id: "test",
                            elem: '#test',
                            even: true,
                            cols: [_cols1,_cols2],
                            height: fullH,
                            data: newList,
                            page: false,
                            limit: 1000000000,
                            drag: false,
                            totalRow: true,
                            parseData: function (res) {
                                return {
                                    "code": res.isSuccess ? 0 : 1,
                                    "msg": res.msg,
                                    "count": res.count,
                                    "data": res.results,
                                    "totalRow": sumAll
                                }
                            },
                            done: function (res) {
                                $('.layui-table-fixed .layui-table-body tr').map(function (i,cur) {
                                    var _this = $(this)
                                    var _text  = _this.find('td .layui-table-cell').text()
                                    if (_text.indexOf('开票总金额') > -1 || _text.indexOf('到账总金额') > -1){
                                        $('.layui-table-fixed .layui-table-body tr').eq(i).addClass('active')
                                        $('.layui-table-main tbody tr').eq(i).addClass('active')

                                    }
                                })
                                $('.layui-table-view .layui-table-total tr td').addClass('active2')

                                soulTable.render(this)
                            }
                        })
                    }else {
                        myTable = table.render({
                            id: "test",
                            elem: '#test',
                            even: true,
                            cols: [_cols1,_cols2],
                            height: fullH,
                            data: res.results.enumMoneyList,
                            page: false,
                            limit: 1000000000,
                            drag: false,
                            parseData: function (res) {

                                return {
                                    "code": res.isSuccess ? 0 : 1,
                                    "msg": res.msg,
                                    "count": res.count,
                                    "data": res.results,
                                }
                            },
                            done: function (res) {
                                $('.layui-table-fixed .layui-table-body tr').map(function (i,cur) {
                                    var _this = $(this)
                                    var _text  = _this.find('td .layui-table-cell').text()
                                    if (_text.indexOf('开票总金额') > -1 || _text.indexOf('到账总金额') > -1){
                                        $('.layui-table-fixed .layui-table-body tr').eq(i).addClass('active')
                                        $('.layui-table-main tbody tr').eq(i).addClass('active')

                                    }
                                    // if (_text.indexOf('到账总金额') > -1){
                                    //     $('.layui-table-fixed .layui-table-body tr').eq(i).addClass('active2')
                                    //     $('.layui-table-main tbody tr').eq(i).addClass('active2')
                                    //
                                    // }
                                })
                                soulTable.render(this)
                            }
                        })
                    }

                }
            })

        }

        /******************************  导出 总表  *********************************/
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
                    data: [
                        {
                            'value': 'all',
                            'name': '总表导出',
                            selected: true
                        },
                        {
                            'value': 'fen',
                            'name': '按开票公司导出'
                        }
                    ]
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
                            dateTimes:$('#endTime').val()
                        }

                        var url =  '${ctx}/billingApplyUnmatch/export?type=single&dateTimes='+param.dateTimes+'&bookType='+exportDemo.getValue('valueStr')
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
            }else if (type == 'all'){
                demo1.setValue([])
                demo2.setValue([])
                paramSubmit = {
                    orgId: '',
                    enumCode: demo3.getValue('valueStr'),
                    dateTimes: $('#endTime').val(),
                    businessType: demo4.getValue('valueStr'),
                    annual: '12'
                }
                setTable(paramSubmit,true)
            }
        })


        function initData2(paramSubmit) {
            loadIndex = layer.load()
            $('button.ll-submit').attr('disabled', true)
            setTimeout(function () {
                $('button.ll-submit').removeAttr('disabled')
            }, 6000)
            var _data = []
            var _oldData = []
            var _cols =[]
            var firsts = [
                {
                    field: '0',
                    width:60,
                    title: '月份',
                    align: 'center',
                    rowspan: 2,
                },
                {
                    field: '0',
                    width:120,
                    title: '机构/产品',
                    align: 'center',
                    rowspan: 2,
                },
                // {
                //     title: '公估板块',
                //     colspan: 0,
                //     align: 'center'
                // },{
                //     title: '个人业务模块',
                //     colspan: 0,
                //     align: 'center'
                // }
                // ,{
                //     field: '',
                //     width:120,
                //     title: '待分配回款',
                //     rowspan: 2,
                // },{
                //     field: '',
                //     width:120,
                //     title: '总计',
                //     rowspan: 2,
                // }
            ]

            function setTable3(_cols, _data) {
                var _h = $('.searchs').outerHeight() + 50
                var fullH = 'full-' + _h
                var myTable = table.render({
                    id: "testLast",
                    elem: '#testLast',
                    even: true,
                    cols: _cols,
                    data: _oldData,
                    page: false,
                    limit: 1000000000,
                    drag: false,
                    done: function (res) {
                        soulTable.render(this)
                        $('button.ll-submit').removeAttr('disabled')
                        $('.layui-table-fixed .layui-table-body tr').map(function (i,cur) {
                            var _this = $(this)
                            var _text  = _this.find('td').eq(1).text()
                            if (_text.indexOf('总金额') > -1){
                                $('.layui-table-fixed .layui-table-body tr').eq(i).addClass('active')
                                $('.layui-table-main tbody tr').eq(i).addClass('active')

                            }
                            if (_text.indexOf('应收账款余额') > -1){
                                $('.layui-table-fixed .layui-table-body tr').eq(i).addClass('active2')
                                $('.layui-table-main tbody tr').eq(i).addClass('active2')

                            }
                        })
                    }
                })
            }

            $.ajax({
                url: '${ctx}/billingApplyUnmatch/claim',
                async: false,
                data: Object.assign(paramSubmit,{
                    annual: '12',
                    type:'all'
                }),
                success: function (res) {
                    var titles =[]
                    var res = JSON.parse(res)
                    var list = res.results
                    var sum1 = 0,sum2=0
                    len = list.length

                    firsts[1] =   {
                        field: 'name0',
                        width:180,
                        fixed: 'left',
                        title: '机构/产品',
                        align: 'center',
                        rowspan: 2,
                        templet: function (d) {
                            return d.name0[0]
                        }
                    }
                    firsts[0] =   {
                        field: 'date0',
                        width:60,
                        width:60,
                        fixed: 'left',
                        title: '月份',
                        align: 'center',
                        rowspan: 2,
                        templet: function (d) {
                            return d.date0[0]
                        }
                    }
                    list.sort(function (a,b) {
                        return Number(b.orgParentid) - Number(a.orgParentid)
                    })
                    list.map(function (cur,j) {
                        if (cur.orgParentid == 106){
                            sum1 ++;
                        }else if (cur.orgParentid == 1){
                            sum2 ++;
                        }
                        var _name = 'nameCom'+(j+1)
                        titles.push({
                            field: _name,
                            minWidth: 120,
                            title: cur.orgName,
                            templet: function (d) {
                                return d[_name][0]
                            }
                        })

                        cur.enumMoneyList.map(function (item,i){
                            var params =_data[2*i] ? _data[2*i] :{}
                            // var arrD = item.dateTime.split('-')
                            // var text_date = arrD[1] + '月'

                            var text_date = ''
                            //开票
                            if (item.enumCode == '-2') {
                                Object.assign(params,
                                    {
                                        'date0': [text_date,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode],
                                        'name0':[item.enumName,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode]
                                    },{
                                        [_name]:[item.imgSum,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode]
                                    },{
                                        unmatchMoney: [item.unmatchMoney,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode]
                                    }
                                )
                            }else {
                                Object.assign(params,
                                    {
                                        'date0': [text_date,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode],
                                        'name0':[item.enumName,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode]
                                    },{
                                        [_name]:[item.imgMoney,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode]
                                    },{
                                        unmatchMoney: [item.unmatchMoney,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode]
                                    }
                                )
                            }

                            Object.assign(params,
                                {
                                    sumAll: [0,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode]
                                }
                            )
                            _data[2*i] = params


                            var params2 =_data[2*i+1] ? _data[2*i+1] :{}

                            //到账
                            if (item.enumCode == '-1'){

                                Object.assign(params2,
                                    {
                                        'date0': [text_date,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode],
                                        'name0':[item.enumName,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    },{
                                        [_name]:[item.accSum,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    },{
                                        'unmatchMoney': [item.unmatchMoney,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    }
                                )
                            }else if (item.enumCode == '99999'){

                                Object.assign(params2,
                                    {
                                        'date0': [text_date,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode],
                                        'name0':[item.enumName,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    },{
                                        [_name]:[item.receMoney,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    },{
                                        'unmatchMoney': [item.unmatchMoney,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    }
                                )
                            } else{
                                Object.assign(params2,
                                    {
                                        'date0': [text_date,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode],
                                        'name0':[item.enumName,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    },{
                                        [_name]:[item.accMoney,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    },{
                                        'unmatchMoney': [item.unmatchMoney,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    }
                                )
                            }
                            Object.assign(params2,
                                {
                                    sumAll: [0,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                }
                            )
                            _data[2*i+1] = params2

                        })

                        var params3 =_oldData[0] ? _oldData[0] :{}
                        var _money = cur.money ? cur.money : 0
                        Object.assign(params3,
                            {
                                'date0': ['',cur.orgName,'',cur.orgParentid,1,0],
                                'name0':['2019年应收账款余额','','',cur.orgParentid,1,0]
                            },{
                                [_name]:[_money,cur.orgName,'',cur.orgParentid,1,0]
                            },{
                                'unmatchMoney': [cur.unmatchMoney,'','',cur.orgParentid,1,0]
                            },{
                                sumAll: [0,cur.orgName,'',cur.orgParentid,1,0]
                            }
                        )
                        _oldData[0] = params3
                    })

                    _data.sort(function(a,b){
                        var bb = b.nameCom1
                        var aa = a.nameCom1
                        return bb[4] - aa[4];
                    })
                    _data.sort(function(a,b){
                        var bb = b.nameCom1[2]
                        var aa = a.nameCom1[2]
                        var newbb = new Date(bb)
                        var newaa = new Date(aa)

                        return newaa.getTime() - newbb.getTime() ;
                    })
                    // var dates = setDates()
                    _data.map(function (cur,i) {
                        if (cur.name0[5] == '-2' && cur.name0[4] == 1){
                            _data[i] = ''
                        }else if ((cur.name0[5] == '-1'||cur.name0[5] == '99999') && cur.name0[4] == 2){
                            _data[i] = ''
                        }else {
                            var sumLine = 0
                            for(var j=1;j<len+1;j++){
                                var _name = 'nameCom'+j
                                sumLine = sumLine + Number(cur[_name][0])
                            }
                            var p = _data[i].sumAll
                            p[0] = Number(sumLine)+ Number(cur.unmatchMoney[0])
                            _data[i].sumAll = p
                        }
                    })
                    _oldData.map(function (cur,i) {
                        var sumLine = 0
                        for(var m=1;m<len+1;m++){
                            var _name = 'nameCom'+m
                            sumLine = sumLine + Number(cur[_name][0])
                        }
                        var p = _oldData[i].sumAll
                        p[0] = Number(sumLine) + Number(cur.unmatchMoney[0])
                        _oldData[i].sumAll = p

                    })


                    if (sum1 >1 && sum2 >1){
                        firsts[2] = {
                                title: '公估板块',
                                colspan: sum1,
                                align: 'center'
                            }
                        firsts[3] = {
                            title: '个人业务板块',
                            colspan: sum2,
                            align: 'center'
                        }
                        firsts[4] = {
                            field: 'unmatchMoney',
                            width:120,
                            title: '待分配回款',
                            rowspan: 2,
                            templet: function (d) {
                                return d.unmatchMoney[0] || 0
                            }
                        }
                        firsts[5] = {
                            field: 'sumAll',
                            width:120,
                            title: '总计',
                            rowspan: 2,
                            templet: function (d) {
                                return d.sumAll[0]
                            }
                        }
                        _cols = [firsts,titles]

                    }else if (sum1 == 1 && sum2 >1){
                        firsts[2] = Object.assign(titles[0], {rowspan: 2})
                        firsts[3] = {
                            title: '个人业务板块',
                            colspan: sum2,
                            align: 'center'
                        }
                        firsts[4] = {
                            field: 'unmatchMoney',
                            width:120,
                            title: '待分配回款',
                            rowspan: 2,
                            templet: function (d) {
                                return d.unmatchMoney[0] || 0
                            }
                        }
                        firsts[5] = {
                            field: 'sumAll',
                            width:120,
                            title: '总计',
                            rowspan: 2,
                            templet: function (d) {
                                return d.sumAll[0]
                            }
                        }
                        titles.splice(0,1)
                        _cols = [firsts,titles]
                    }else if (sum1 > 1 && sum2 ==1){
                        firsts[2] = {
                            title: '公估板块',
                            colspan: sum1,
                            align: 'center'
                        }
                        firsts[3] = Object.assign(titles[titles.length - 1], {rowspan: 2})
                        firsts[4] = {
                            field: 'unmatchMoney',
                            width:120,
                            title: '待分配回款',
                            rowspan: 2,
                            templet: function (d) {
                                return d.unmatchMoney[0] || 0
                            }
                        }
                        firsts[5] = {
                            field: 'sumAll',
                            width:120,
                            title: '总计',
                            rowspan: 2,
                            templet: function (d) {
                                return d.sumAll[0]
                            }
                        }
                        titles.splice(titles.length -1,1)
                        _cols = [firsts,titles]
                    }


                    if (sum1 >1 && sum2 ==0){
                        firsts[2] = {
                            title: '公估板块',
                            colspan: sum1,
                            align: 'center'
                        }
                        firsts[3] = {
                            field: 'unmatchMoney',
                            width:120,
                            title: '待分配回款',
                            rowspan: 2,
                            templet: function (d) {
                                return d.unmatchMoney[0] || 0
                            }
                        }
                        firsts[4] = {
                            field: 'sumAll',
                            width:120,
                            title: '总计',
                            rowspan: 2,
                            templet: function (d) {
                                return d.sumAll[0]
                            }
                        }
                        _cols = [firsts,titles]
                    }else if (sum1  == 1 && sum2 ==0){
                        firsts[2] = titles[0]
                        firsts[3] = {
                            field: 'unmatchMoney',
                            width:120,
                            title: '待分配回款',
                            rowspan: 2,
                            templet: function (d) {
                                return d.unmatchMoney[0] || 0
                            }
                        }
                        firsts[4] = {
                            field: 'sumAll',
                            width:120,
                            title: '总计',
                            rowspan: 2,
                            templet: function (d) {
                                return d.sumAll[0]
                            }
                        }
                        _cols = [firsts]

                    }

                    if (sum1  == 0 && sum2 >1){
                        firsts[2] = {
                            title: '个人业务板块',
                            colspan: sum2,
                            align: 'center'
                        }
                        firsts[3] = {
                            field: 'unmatchMoney',
                            width:120,
                            title: '待分配回款',
                            rowspan: 2,
                            templet: function (d) {
                                return d.unmatchMoney[0] || 0
                            }
                        }
                        firsts[4] = {
                            field: 'sumAll',
                            width:120,
                            title: '总计',
                            rowspan: 2,
                            templet: function (d) {
                                return d.sumAll[0]
                            }
                        }
                        _cols = [firsts,titles]

                    }else if (sum1  == 0 && sum2 == 1){
                        firsts[2] = titles[0]
                        firsts[3] = {
                            field: 'unmatchMoney',
                            width:120,
                            title: '待分配回款',
                            rowspan: 1,
                            templet: function (d) {
                                return d.unmatchMoney[0] || 0
                            }
                        }
                        firsts[4] = {
                            field: 'sumAll',
                            width:120,
                            title: '总计',
                            rowspan: 1,
                            templet: function (d) {
                                return d.sumAll[0]
                            }
                        }
                        _cols = [firsts]

                    }else if (sum1  == 1 && sum2 == 1){
                        firsts[2] = titles[0]
                        firsts[3] = titles[1]
                        firsts[4] = {
                            field: 'unmatchMoney',
                            width:120,
                            title: '待分配回款',
                            rowspan: 1,
                            templet: function (d) {
                                return d.unmatchMoney[0] || 0
                            }
                        }
                        firsts[5] = {
                            field: 'sumAll',
                            width:120,
                            title: '总计',
                            rowspan: 1,
                            templet: function (d) {
                                return d.sumAll[0]
                            }
                        }
                        _cols = [firsts]

                    }
                    setTable2(_cols, _data)
                    setTable3(_cols, _oldData)
                }
            })
            function setTable2(_cols, _data) {

                var _h = $('.searchs').outerHeight() + 50
                var fullH = 'full-' + _h
                var myTable = table.render({
                    id: "testSum",
                    elem: '#testSum',
                    even: true,
                    cols: _cols,
                    data: _data,
                    page: false,
                    limit: 1000000000,
                    height: 600,
                    drag: false,
                    done: function (res) {
                        soulTable.render(this)
                        $('button.ll-submit').removeAttr('disabled')
                        $('.layui-table-fixed .layui-table-body tr').map(function (i,cur) {
                            var _this = $(this)
                            var _text  = _this.find('td').eq(1).text()
                            if (_text.indexOf('总金额') > -1){
                                $('.layui-table-fixed .layui-table-body tr').eq(i).addClass('active')
                                $('.layui-table-main tbody tr').eq(i).addClass('active')

                            }
                            if (_text.indexOf('应收账款余额') > -1){
                                $('.layui-table-fixed .layui-table-body tr').eq(i).addClass('active2')
                                $('.layui-table-main tbody tr').eq(i).addClass('active2')

                            }
                        })
                    }
                })


            }
            $.ajax({
                url: '${ctx}/billingApplyUnmatch/claim',
                async: false,
                data: Object.assign(paramSubmit,{
                    annual: '12',
                    type:'single'
                }),
                success: function (res) {
                    var titles =[]
                    var res = JSON.parse(res)
                    var list = res.results
                    var sum1 = 0,sum2=0
                    len = list.length
                    firsts[1] =   {
                        field: 'name0',
                        width:180,
                        fixed: 'left',
                        title: '机构/产品',
                        align: 'center',
                        rowspan: 2,
                        templet: function (d) {
                            return d.name0[0]
                        }
                    }
                    firsts[0] =   {
                        field: 'date0',
                        width:60,
                        fixed: 'left',
                        title: '月份',
                        align: 'center',
                        rowspan: 2,
                        templet: function (d) {
                            return d.date0[0]
                        }
                    }
                    list.sort(function (a,b) {
                        return Number(b.orgParentid) - Number(a.orgParentid)
                    })
                    list.map(function (cur,j) {
                        if (cur.orgParentid == 106){
                            sum1 ++;
                        }else if (cur.orgParentid == 1){
                            sum2 ++;
                        }
                        var _name = 'nameCom'+(j+1)
                        titles.push({
                            field: _name,
                            minWidth: 120,
                            title: cur.orgName,
                            templet: function (d) {
                                return d[_name][0]
                            }
                        })

                        cur.enumMoneyList.map(function (item,i){
                            var params =_data[2*i] ? _data[2*i] :{}
                            var arrD = item.dateTime.split('-')
                            var text_date = arrD[1] + '月'
                            //开票
                            if (item.enumCode == '-2') {
                                Object.assign(params,
                                    {
                                        'date0': [text_date,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode],
                                        'name0':[item.enumName,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode]
                                    },{
                                        [_name]:[item.imgSum,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode]
                                    },{
                                        unmatchMoney: [item.unmatchMoney,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode]
                                    }
                                )
                            }else {
                                Object.assign(params,
                                    {
                                        'date0': [text_date,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode],
                                        'name0':[item.enumName,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode]
                                    },{
                                        [_name]:[item.imgMoney,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode]
                                    },{
                                        unmatchMoney: [item.unmatchMoney,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode]
                                    }
                                )
                            }

                            Object.assign(params,
                                {
                                    sumAll: [0,cur.orgName,item.dateTime,cur.orgParentid,2,item.enumCode]
                                }
                            )
                            _data[2*i] = params


                            var params2 =_data[2*i+1] ? _data[2*i+1] :{}

                            //到账
                            if (item.enumCode == '-1'){

                                Object.assign(params2,
                                    {
                                        'date0': [text_date,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode],
                                        'name0':[item.enumName,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    },{
                                        [_name]:[item.accSum,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    },{
                                        'unmatchMoney': [item.unmatchMoney,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    }
                                )
                            }else if (item.enumCode == '99999'){

                                Object.assign(params2,
                                    {
                                        'date0': [text_date,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode],
                                        'name0':[item.enumName,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    },{
                                        [_name]:[item.receMoney,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    },{
                                        'unmatchMoney': [item.unmatchMoney,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    }
                                )
                            } else{
                                Object.assign(params2,
                                    {
                                        'date0': [text_date,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode],
                                        'name0':[item.enumName,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    },{
                                        [_name]:[item.accMoney,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    },{
                                        'unmatchMoney': [item.unmatchMoney,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                    }
                                )
                            }
                            Object.assign(params2,
                                {
                                    sumAll: [0,cur.orgName,item.dateTime,cur.orgParentid,1,item.enumCode]
                                }
                            )
                            _data[2*i+1] = params2



                        })



                    })


                    _data.sort(function(a,b){
                        var bb = b.nameCom1
                        var aa = a.nameCom1
                        return bb[4] - aa[4];
                    })
                    _data.sort(function(a,b){
                        var bb = b.nameCom1[2]
                        var aa = a.nameCom1[2]
                        var newbb = new Date(bb)
                        var newaa = new Date(aa)

                        return newaa.getTime() - newbb.getTime() ;
                    })
                    // var dates = setDates()
                    _data.map(function (cur,i) {
                        if (cur.name0[5] == '-2' && cur.name0[4] == 1){
                            _data[i] = ''
                        }else if ((cur.name0[5] == '-1'||cur.name0[5] == '99999') && cur.name0[4] == 2){
                            _data[i] = ''
                        }else {
                            var sumLine = 0
                            for(var j=1;j<len+1;j++){
                                var _name = 'nameCom'+j
                                sumLine = sumLine + Number(cur[_name][0])
                            }
                            var p = _data[i].sumAll
                            p[0] = Number(sumLine) + Number(cur.unmatchMoney[0])
                            _data[i].sumAll = p
                        }

                    })

                    if (sum1 >1 && sum2 >1){
                        firsts[2] = {
                            title: '公估板块',
                            colspan: sum1,
                            align: 'center'
                        }
                        firsts[3] = {
                            title: '个人业务板块',
                            colspan: sum2,
                            align: 'center'
                        }
                        firsts[4] = {
                            field: 'unmatchMoney',
                            width:120,
                            title: '待分配回款',
                            rowspan: 2,
                            templet: function (d) {
                                return d.unmatchMoney[0] || 0
                            }
                        }
                        firsts[5] = {
                            field: 'sumAll',
                            width:120,
                            title: '总计',
                            rowspan: 2,
                            templet: function (d) {
                                return d.sumAll[0]
                            }
                        }
                        _cols = [firsts,titles]

                    }else if (sum1 == 1 && sum2 >1){
                        firsts[2] = Object.assign(titles[0], {rowspan: 2})
                        firsts[3] = {
                            title: '个人业务板块',
                            colspan: sum2,
                            align: 'center'
                        }
                        firsts[4] = {
                            field: 'unmatchMoney',
                            width:120,
                            title: '待分配回款',
                            rowspan: 2,
                            templet: function (d) {
                                return d.unmatchMoney[0] || 0
                            }
                        }
                        firsts[5] = {
                            field: 'sumAll',
                            width:120,
                            title: '总计',
                            rowspan: 2,
                            templet: function (d) {
                                return d.sumAll[0]
                            }
                        }
                        titles.splice(0,1)
                        _cols = [firsts,titles]
                    }else if (sum1 > 1 && sum2 ==1){
                        firsts[2] = {
                            title: '公估板块',
                            colspan: sum1,
                            align: 'center'
                        }
                        firsts[3] = Object.assign(titles[titles.length - 1], {rowspan: 2})
                        firsts[4] = {
                            field: 'unmatchMoney',
                            width:120,
                            title: '待分配回款',
                            rowspan: 2,
                            templet: function (d) {
                                return d.unmatchMoney[0] || 0
                            }
                        }
                        firsts[5] = {
                            field: 'sumAll',
                            width:120,
                            title: '总计',
                            rowspan: 2,
                            templet: function (d) {
                                return d.sumAll[0]
                            }
                        }
                        titles.splice(titles.length -1,1)
                        _cols = [firsts,titles]
                    }


                    if (sum1 >1 && sum2 ==0){
                        firsts[2] = {
                            title: '公估板块',
                            colspan: sum1,
                            align: 'center'
                        }
                        firsts[3] = {
                            field: 'unmatchMoney',
                            width:120,
                            title: '待分配回款',
                            rowspan: 2,
                            templet: function (d) {
                                return d.unmatchMoney[0] || 0
                            }
                        }
                        firsts[4] = {
                            field: 'sumAll',
                            width:120,
                            title: '总计',
                            rowspan: 2,
                            templet: function (d) {
                                return d.sumAll[0]
                            }
                        }
                        _cols = [firsts,titles]
                    }else if (sum1  == 1 && sum2 ==0){
                        firsts[2] = titles[0]
                        firsts[3] = {
                            field: 'unmatchMoney',
                            width:120,
                            title: '待分配回款',
                            rowspan: 2,
                            templet: function (d) {
                                return d.unmatchMoney[0] || 0
                            }
                        }
                        firsts[4] = {
                            field: 'sumAll',
                            width:120,
                            title: '总计',
                            rowspan: 2,
                            templet: function (d) {
                                return d.sumAll[0]
                            }
                        }
                        _cols = [firsts]

                    }

                    if (sum1  == 0 && sum2 >1){
                        firsts[2] = {
                            title: '个人业务板块',
                            colspan: sum2,
                            align: 'center'
                        }
                        firsts[3] = {
                            field: 'unmatchMoney',
                            width:120,
                            title: '待分配回款',
                            rowspan: 2,
                            templet: function (d) {
                                return d.unmatchMoney[0] || 0
                            }
                        }
                        firsts[4] = {
                            field: 'sumAll',
                            width:120,
                            title: '总计',
                            rowspan: 2,
                            templet: function (d) {
                                return d.sumAll[0]
                            }
                        }
                        _cols = [firsts,titles]

                    }else if (sum1  == 0 && sum2 == 1){
                        firsts[2] = titles[0]
                        firsts[3] = {
                            field: 'unmatchMoney',
                            width:120,
                            title: '待分配回款',
                            rowspan: 1,
                            templet: function (d) {
                                return d.unmatchMoney[0] || 0
                            }
                        }
                        firsts[4] = {
                            field: 'sumAll',
                            width:120,
                            title: '总计',
                            rowspan: 1,
                            templet: function (d) {
                                return d.sumAll[0]
                            }
                        }
                        _cols = [firsts]

                    }else if (sum1  == 1 && sum2 == 1){
                        firsts[2] = titles[0]
                        firsts[3] = titles[1]
                        firsts[4] = {
                            field: 'unmatchMoney',
                            width:120,
                            title: '待分配回款',
                            rowspan: 1,
                            templet: function (d) {
                                return d.unmatchMoney[0] || 0
                            }
                        }
                        firsts[5] = {
                            field: 'sumAll',
                            width:120,
                            title: '总计',
                            rowspan: 1,
                            templet: function (d) {
                                return d.sumAll[0]
                            }
                        }
                        _cols = [firsts]

                    }
                    setTable(_cols, _data)

                }
            })


            function setTable(_cols, _data) {
                layer.close(loadIndex)
                $('button.ll-submit').removeAttr('disabled')
                var _h = $('.searchs').outerHeight() + 50
                var fullH = 'full-' + _h
                var myTable = table.render({
                    id: "test",
                    elem: '#test',
                    even: true,
                    cols: _cols,
                    data: _data,
                    page: false,
                    limit: 1000000000,
                    height: 600,
                    drag: false,
                    done: function (res) {
                        soulTable.render(this)
                        $('button.ll-submit').removeAttr('disabled')
                        $('.layui-table-fixed .layui-table-body tr').map(function (i,cur) {
                            var _this = $(this)
                            var _text  = _this.find('td').eq(1).text()
                            if (_text.indexOf('总金额') > -1){
                                $('.layui-table-fixed .layui-table-body tr').eq(i).addClass('active')
                                $('.layui-table-main tbody tr').eq(i).addClass('active')

                            }
                            if (_text.indexOf('应收账款余额') > -1){
                                $('.layui-table-fixed .layui-table-body tr').eq(i).addClass('active2')
                                $('.layui-table-main tbody tr').eq(i).addClass('active2')

                            }
                        })
                    }
                })
            }
        }


    })
</script>



</body>

</html>