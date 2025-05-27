<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>每刻报销列表</title>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/layui/layui_exts/soulTable.css">
    <style>
        .main {
            width: 99%;
            margin: 10px auto;
        }

        .selectMul {
            width: 100%;
        }

        .searchs, .searchs-child, .searchs-child2, .searchs-child3 {
            padding: 10px 0 7px 0;
            background-color: #d9edf7;
        }

        .layui-form-item {
            margin: 0 !important;
        }

        label.layui-form-label {
            width: auto;
            padding: 6px 15px;
            margin-bottom: 0;
        }

        .layui-form-item .layui-input-inline {
            width: 150px !important;
        }

        .layui-input{
            height: 32px;
            line-height: 32px;
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
            width: 110px;
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

        .lf-btns{
            padding-top: 10px;
            width: 100%;
            display: flex;
            justify-content: center;
        }
        .lf-btns .lf-btn{
            width: 180px;
            height: 40px;
            line-height: 40px;
            border:1px solid #3BA9FF;
            color: #3BA9FF;
            text-align: center;
            margin-right: 40px;
            cursor: pointer;
        }
        .lf-btns .lf-btn.active{
            background-color:#3BA9FF;
            color: #fff;
        }

        .checkBoxH{
            display: inline-block;
            margin-right: 6px;
            width: 13px;
            height: 13px;
            border: 1px solid #333;
            border-radius: 4px;
            box-sizing: content-box;
        }
        .checkBoxH.active {
            display: inline-block;
            width: 15px;
            height: 15px;
            background: url('data:image/png;base64,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');
            background-position: center;
            background-repeat: no-repeat;
            background-size: contain;
        }
        .poi-no{
            pointer-events: none;
        }
        .clock-content{
            width: 100%;
            display: flex;
            align-items: center;
        }
        .clock-num{
            width: 136px;
            display: inline-block;
            /*color: #3ba9ff;*/
        }
        .map-icon{
            display: inline-block;
            width: 20px;
            height: 20px;
            background: url(data:image/png;base64,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);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }
        tr.active td{
            background-color: rgba(236,128,126,0.4);
        }
        .operateA{
            display: inline-block;
            color: #3ba9ff;
            width: 42px;
        }
        .editBud, .editBudYear{
            width: 100%;
            display: flex;
            flex-wrap: wrap;
        }
        .editBud .layui-form-label, .editBudYear .layui-form-labe{
            width: 140px!important;
        }
        .editBud .layui-form-item .layui-input-inline,.editBudYear .layui-form-item .layui-input-inline  {
            width: 320px !important;
        }

        .lf-bars {
            width: 100%;
            display: flex;
        }

        .lf-bars .lf-bar {
            width: 160px;
            height: 32px;
            line-height: 32px;
            border: 1px solid #3BA9FF;
            color: #3BA9FF;
            text-align: center;
            cursor: pointer;
        }

        .lf-bars .lf-bar.active {
            background-color: #3BA9FF;
            color: #fff;
        }

        .disabled{
            background-color: #f6f6f6;
        }
    </style>
</head>

<body>
<div class="main">
    <div class="layui-form searchs" lay-filter="search">
        <input type="hidden" value="${menuCode}" id="menuCode" name="menuCode">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">年份</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" readonly id="yearTime"
                           placeholder="请选择" >
                </div>
            </div>

            <div class="layui-inline" style="margin-left: 20px;">
                <button lay-submit class="ll-submit layui-btn layui-btn-normal layui-btn-sm layui-btn-radius"
                        lay-filter="submit" style="width: 86px">查询 <i
                        class="layui-icon layui-icon-search"></i></button>
                <button class=" layui-btn layui-btn-normal layui-btn-sm layui-btn-radius layui-export"
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
    <div class="layui-form searchs-child" lay-filter="search">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">预算归属公司</label>
                <div class="layui-input-inline">
                    <div id="companyId" class="selectMul"></div>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">机构</label>
                <div class="layui-input-inline">
                    <div id="surveyOrgId" class="selectMul"></div>
                </div>
            </div>

            <div class="layui-inline" style="margin-left: 20px;">
                <button lay-submit class="ll-submit-child layui-btn layui-btn-normal layui-btn-sm layui-btn-radius"
                        lay-filter="submitChild" style="width: 86px">查询 <i
                        class="layui-icon layui-icon-search"></i></button>
               <button class=" layui-btn layui-btn-normal layui-btn-sm layui-btn-radius layui-export-child"
                        style="width: 86px">导出 <i class="layui-icon layui-icon-export"></i></button>
            </div>
        </div>
    </div>
    <div class="table-content-child">
        <table class="layui-table" id="test-child" lay-filter="test-child" lay-skin="line" lay-size="lg" style="margin: 0">
        </table>
    </div>
</script>
<script type="text/html" id="table-content-child-h2">
    <div class="layui-form searchs-child2" lay-filter="search">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">费用类型</label>
                <div class="layui-input-inline">
                    <div id="costTypeId2" class="selectMul"></div>
                </div>
            </div>

            <div class="layui-inline" style="margin-left: 20px;">
                <button lay-submit class="ll-submit-child2 layui-btn layui-btn-normal layui-btn-sm layui-btn-radius"
                        lay-filter="submitChild2" style="width: 86px">查询 <i
                        class="layui-icon layui-icon-search"></i></button>
                <button class=" layui-btn layui-btn-normal layui-btn-sm layui-btn-radius layui-export-child2"
                        style="width: 86px">导出 <i class="layui-icon layui-icon-export"></i></button>
            </div>
        </div>
    </div>
    <div class="table-content-child">
        <table class="layui-table" id="test-child2" lay-filter="test-child2" lay-skin="line" lay-size="lg" style="margin: 0">
        </table>
    </div>
</script>

<script type="text/html" id="table-content-child-h3">
    <div class="layui-form searchs-child3" lay-filter="search">
        <div class="layui-form-item ">
            <div class="layui-inline">
                <label class="layui-form-label">费用类型</label>
                <div class="layui-input-inline">
                    <div id="costTypeId3" class="selectMul"></div>
                </div>
            </div>

            <div class="layui-inline" style="margin-left: 20px;">
                <button lay-submit class="ll-submit-child3 layui-btn layui-btn-normal layui-btn-sm layui-btn-radius"
                        lay-filter="submitChild3" style="width: 86px">查询 <i
                        class="layui-icon layui-icon-search"></i></button>
               <button class=" layui-btn layui-btn-normal layui-btn-sm layui-btn-radius layui-export-child3"
                        style="width: 86px">导出 <i class="layui-icon layui-icon-export"></i></button>
            </div>
        </div>
    </div>
    <div class="table-content-child">
        <table class="layui-table" id="test-child3" lay-filter="test-child3" lay-skin="line" lay-size="lg" style="margin: 0">
        </table>
    </div>
</script>

<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        soulTable: 'soulTable',
        xmSelect: 'xm-select'
    })

    function getYMD(date) {
        var today = new Date(date);
        return {
            'y': today.getFullYear(),
            'm': today.getMonth() + 1,
            'd': today.getDate()
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

    layui.use(['form', 'table', 'soulTable', 'xmSelect', 'laydate', 'layer', 'util'], function () {
        var soulTable = layui.soulTable,
            table = layui.table,
            form = layui.form,
            xmSelect = layui.xmSelect,
            laydate = layui.laydate,
            layer = layui.layer,
            $ = layui.$,
            util = layui.util;

        var yearTime = laydate.render({
            elem: '#yearTime',
            type: 'year'
        });

        var paramSubmit = {
            searchCode: 'type1',
            year: '',

        }
        var _cols = [
            [
                {
                    field: 'year',
                    minWidth:120,
                    title: '年份',
                    fixed: 'left',
                    style: 'color: #3BA9FF;cursor: pointer;',
                    event: 'case',
                }, {
                field: 'yearMoney',
                minWidth: 160,
                title: '年度预算总额',
                templet: function (d) {
                    return Math.round(d.yearMoney * 100) / 100
                }
            },  {
                field: 'useYearMoney',
                minWidth: 150,
                title: '已占用',
                templet: function (d) {
                    return Math.round(d.useYearMoney * 100) / 100
                }
            }, {
                field: 'surplusYearMoney',
                minWidth: 150,
                title: '剩余预算',
                templet: function (d) {
                    return Math.round(d.surplusYearMoney * 100) / 100
                }
            },{
                field: 'january',
                minWidth: 150,
                title: '1月',
                templet: function (d) {
                    var muse = Math.round(d.januaryUes * 100) / 100
                    var m = Math.round(d.january * 100) / 100
                    return muse + '/' + m
                }
            },{
                field: 'february',
                minWidth: 150,
                title: '2月',
                templet: function (d) {
                    var muse = Math.round(d.februaryUes * 100) / 100
                    var m = Math.round(d.february * 100) / 100
                    return muse + '/' + m
                }
            },{
                field: 'march',
                minWidth: 150,
                title: '3月',
                templet: function (d) {
                    var muse = Math.round(d.marchUes * 100) / 100
                    var m = Math.round(d.march * 100) / 100
                    return muse + '/' + m
                }
            },{
                field: 'april',
                minWidth: 150,
                title: '4月',
                templet: function (d) {
                    var muse = Math.round(d.aprilUes * 100) / 100
                    var m = Math.round(d.april * 100) / 100
                    return muse + '/' + m
                }
            },{
                field: 'may',
                minWidth: 150,
                title: '5月',
                templet: function (d) {
                    var muse = Math.round(d.mayUes * 100) / 100
                    var m = Math.round(d.may * 100) / 100
                    return muse + '/' + m
                }
            },{
                field: 'june',
                minWidth: 150,
                title: '6月',
                templet: function (d) {
                    var muse = Math.round(d.juneUes * 100) / 100
                    var m = Math.round(d.june * 100) / 100
                    return muse + '/' + m
                }
            },{
                field: 'july',
                minWidth: 150,
                title: '7月',
                templet: function (d) {
                    var muse = Math.round(d.julyUes * 100) / 100
                    var m = Math.round(d.july * 100) / 100
                    return muse + '/' + m
                }
            },{
                field: 'august',
                minWidth: 150,
                title: '8月',
                templet: function (d) {
                    var muse = Math.round(d.augustUes * 100) / 100
                    var m = Math.round(d.august * 100) / 100
                    return muse + '/' + m
                }
            },{
                field: 'september',
                minWidth: 150,
                title: '9月',
                templet: function (d) {
                    var muse = Math.round(d.septemberUes * 100) / 100
                    var m = Math.round(d.september * 100) / 100
                    return muse + '/' + m
                }
            },{
                field: 'october',
                minWidth: 150,
                title: '10月',
                templet: function (d) {
                    var muse = Math.round(d.octoberUes * 100) / 100
                    var m = Math.round(d.october * 100) / 100
                    return muse + '/' + m
                }
            },{
                field: 'november',
                minWidth: 150,
                title: '11月',
                templet: function (d) {
                    var muse = Math.round(d.novemberUes * 100) / 100
                    var m = Math.round(d.november * 100) / 100
                    return muse + '/' + m
                }
            },{
                field: 'december',
                minWidth: 150,
                title: '12月',
                templet: function (d) {
                    var muse = Math.round(d.decemberUes * 100) / 100
                    var m = Math.round(d.december * 100) / 100
                    return muse + '/' + m
                }
            }
            ]
        ]
        setTable(_cols, paramSubmit)

        form.on('submit(submit)', function (data) {
            Object.assign(paramSubmit, {
                searchCode: 'type1',
                year: $('#yearTime').val(),
            })
            reloadTable(_cols, paramSubmit)
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
                if (cur.checked){
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
                ' <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg" style="margin: 0"></table>'
            )
            setTable(_cols, _data)
        }

        function reloadTableChild(_cols, _data) {
            $('.table-content-child').empty()
            $('.table-content-child').append(
                ' <table class="layui-table" id="test-child" lay-filter="test-child" lay-skin="line" lay-size="lg" style="margin: 0"></table>'
            )
            setTableChild(_cols, _data)
        }
        function reloadTableChild2(_cols, _data) {
            $('.table-content-child2').empty()
            $('.table-content-child2').append(
                ' <table class="layui-table" id="test-child2" lay-filter="test-child2" lay-skin="line" lay-size="lg" style="margin: 0"></table>'
            )
            setTableChild2(_cols, _data)
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
        function getFixed(d) {
            return d ? Math.round(d * 100) / 100 : '0'

        }

        $('.add').click(function () {
            $('button.add').attr('disabled', true)
            setTimeout(function () {
                $('button.add').removeAttr('disabled')
            }, 2000)
        })

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
                drag: false,
                cols: _cols,
                page:false,
                overflow: {
                    type: 'tips'
                    ,hoverTime: 300 // 悬停时间，单位ms, 悬停 hoverTime 后才会显示，默认为 0
                    ,color: 'black' // 字体颜色
                    ,bgColor: 'white' // 背景色
                    ,minWidth: 250 // 最小宽度
                    ,maxWidth: 500 // 最大宽度
                },
                height: fullH,
                url: '${ctx}/financial/budget/budgetReportAjaxData',
                where: param,
                parseData: function (res) {
                    return {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results
                    }
                },
                done: function (res) {
                    soulTable.render(this)
                    $('button.ll-submit').removeAttr('disabled')
                }
            })
            table.on('tool(test)', function (obj) {
                if (obj.event == 'case') {
                    var param = {
                        searchCode: 'type2',
                        budgetId: obj.data.id,
                        ascriptionCompanyId: '',
                        ascriptionOrganId: '',
                    }
                    showCaseChildList(param)
                }
            })
            $('.layui-export-child').off('click').on('click',function (e) {
                var _this = $('.layui-export')
                _this.attr('disabled',true)

                setTimeout(function () {
                    _this.removeAttr('disabled')
                },5000)

                soulTable.export(myTable,{
                    filename: '预算报表.xlsx'
                })

            })
        }

        function setTableChild(_colsChild,param){
            $('button.ll-submit-child').attr('disabled', true)
            setTimeout(function () {
                $('button.ll-submit-child').removeAttr('disabled')
            }, 6000)

            var _h = $('.searchs-child').outerHeight() + 160
            var fullH = 'full-' + _h
            var myTable = table.render({
                id: "test-child",
                elem: '#test-child',
                even: true,
                drag: false,
                cols: _colsChild,
                page:{
                    theme: '#1e9fff',
                    limit: 15,
                    limits: [10,15,20,30,40,50]
                },
                request: {
                    pageName: 'page',
                    limitName: 'pageSize'
                },
                overflow: {
                    type: 'tips'
                    ,hoverTime: 300 // 悬停时间，单位ms, 悬停 hoverTime 后才会显示，默认为 0
                    ,color: 'black' // 字体颜色
                    ,bgColor: 'white' // 背景色
                    ,minWidth: 250 // 最小宽度
                    ,maxWidth: 500 // 最大宽度
                },
                height: fullH,
                url: '${ctx}/financial/budget/budgetReportAjaxData',
                where: param,
                parseData: function (res) {
                    return {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results
                    }
                },
                done: function (res) {
                    soulTable.render(this)
                    $('button.ll-submit-child').removeAttr('disabled')
                }
            })
            table.on('tool(test-child)', function (obj) {
                if (obj.event == 'org') {
                    var param = {
                        searchCode: 'type3',
                        budgetId: obj.data.budgetId,
                        ascriptionOrganId: obj.data.ascriptionOrganId,
                        costTypeId: ''
                    }
                    showCaseChild2List(param)
                }
            })

            // $('.layui-export-child').off('click').on('click',function (e) {
            //     var _this = $(this)
            //     _this.attr('disabled',true)
            //     soulTable.export(myTable,{
            //         filename: '年度预算报表.xlsx'
            //     })
            //
            // })
        }

        function setTableChild2(_colsChild,param){
            $('button.ll-submit-child2').attr('disabled', true)
            setTimeout(function () {
                $('button.ll-submit-child2').removeAttr('disabled')
            }, 6000)

            var _h = $('.searchs-child2').outerHeight() + 160
            var fullH = 'full-' + _h
            var myTable = table.render({
                id: "test-child2",
                elem: '#test-child2',
                even: true,
                drag: false,
                cols: _colsChild,
                page:{
                    theme: '#1e9fff',
                    limit: 15,
                    limits: [10,15,20,30,40,50]
                },
                request: {
                    pageName: 'page',
                    limitName: 'pageSize'
                },
                overflow: {
                    type: 'tips'
                    ,hoverTime: 300 // 悬停时间，单位ms, 悬停 hoverTime 后才会显示，默认为 0
                    ,color: 'black' // 字体颜色
                    ,bgColor: 'white' // 背景色
                    ,minWidth: 250 // 最小宽度
                    ,maxWidth: 500 // 最大宽度
                },
                height: fullH,
                url: '${ctx}/financial/budget/budgetReportAjaxData',
                where: param,
                parseData: function (res) {
                    return {
                        "code": res.isSuccess ? 0 : 1,
                        "msg": res.msg,
                        "count": res.count,
                        "data": res.results
                    }
                },
                done: function (res) {
                    soulTable.render(this)
                    $('button.ll-submit-child2').removeAttr('disabled')
                }
            })
            // $('.layui-export-child2').off('click').on('click',function (e) {
            //     var _this = $(this)
            //     _this.attr('disabled',true)
            //     soulTable.export(myTable,{
            //         filename: '机构预算报表.xlsx'
            //     })
            // })
        }


        function showCaseChildList(param) {
            var param = param
            var _height = $(document).height() * 0.9
            var _width = $(document).width() * 0.98
            openIndex = layer.open({
                type: 1,
                title: '预算-年度',
                area: [_width + 'px', _height + 'px'],
                content: $('#table-content-child-h').html(),
                success: function () {
                    var companyData = [],orgData=[];
                    costTypeData = []

                    var demo1 = xmSelect.render({
                        el: '#companyId',
                        theme: {
                            color: '#3BA9FF',
                        },
                        size: 'small',
                        filterable: true,
                        filterDone: function(val, list){
                            $('.xm-option-content').each(function () {
                                var _this = $(this)
                                _this.attr('title', _this.text())
                            })
                        },
                        toolbar: {
                            show: true
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
                                                _html += '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                            })
                                            return _html
                                        }
                                    }
                                }
                            }
                        },
                        data: [{
                            name: '日常费用报销',
                            value: '1'
                        },{
                            name: '对公支付',
                            value: '2'
                        },{
                            name: '借款单',
                            value: '3'
                        }]
                    })
                    var demo2 = xmSelect.render({
                        el: '#surveyOrgId',
                        theme: {
                            color: '#3BA9FF',
                        },
                        size: 'small',
                        filterable: true,
                        filterDone: function(val, list){
                            $('.xm-option-content').each(function () {
                                var _this = $(this)
                                _this.attr('title', _this.text())
                            })
                        },
                        toolbar: {
                            show: true
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
                                                _html += '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                            })
                                            return _html
                                        }
                                    }
                                }
                            }
                        },
                        data: [{
                            name: '日常费用报销',
                            value: '1'
                        },{
                            name: '对公支付',
                            value: '2'
                        },{
                            name: '借款单',
                            value: '3'
                        }]
                    })

                    $.ajax({
                        url: '${ctx}/financial/budget/budgetAjaxData',
                        type: 'post',
                        data: {
                            searchCode: 'budget-page-data',
                        },
                        success: function (res) {
                            var results = JSON.parse(res.results)
                            companyData = filterJson('', results.staffBudgetCompanyList,'id','name',true, false)
                            orgData = filterJson('', results.organList,'id','name',true, false)
                            costTypeData = filterJson('', results.costTypeList,'id','costName',true, false)
                            demo1.update({
                                data: companyData
                            })
                            demo2.update({
                                data: orgData
                            })
                        }
                    })
                    var _colsChild = [
                        [
                            {
                                field: 'year',
                                minWidth:120,
                                title: '年份',
                                fixed: 'left',
                                event: 'case',
                            },
                         {
                            field: 'ascriptionCompany',
                            minWidth: 150,
                            title: '预算归属公司',
                        }, {
                            field: 'ascriptionOrgan',
                            minWidth: 150,
                            style: 'color: #3BA9FF;cursor: pointer;',
                            title: '机构',
                            event: 'org'
                        },
                            {
                            field: 'yearMoney',
                            minWidth: 160,
                            title: '年度预算总额',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.yearMoney * 100) / 100
                            }
                        },  {
                            field: 'useYearMoney',
                            minWidth: 150,
                            title: '已占用',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.useYearMoney * 100) / 100
                            }
                        }, {
                            field: 'surplusYearMoney',
                            minWidth: 150,
                            title: '剩余预算',
                            sort: true,
                            templet: function (d) {
                                return Math.round(d.surplusYearMoney * 100) / 100
                            }
                        },{
                            field: 'january',
                            minWidth: 150,
                            title: '1月',
                            templet: function (d) {
                                var muse = Math.round(d.januaryUes * 100) / 100
                                var m = Math.round(d.january * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'february',
                            minWidth: 150,
                            title: '2月',
                            templet: function (d) {
                                var muse = Math.round(d.februaryUes * 100) / 100
                                var m = Math.round(d.february * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'march',
                            minWidth: 150,
                            title: '3月',
                            templet: function (d) {
                                var muse = Math.round(d.marchUes * 100) / 100
                                var m = Math.round(d.march * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'april',
                            minWidth: 150,
                            title: '4月',
                            templet: function (d) {
                                var muse = Math.round(d.aprilUes * 100) / 100
                                var m = Math.round(d.april * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'may',
                            minWidth: 150,
                            title: '5月',
                            templet: function (d) {
                                var muse = Math.round(d.mayUes * 100) / 100
                                var m = Math.round(d.may * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'june',
                            minWidth: 150,
                            title: '6月',
                            templet: function (d) {
                                var muse = Math.round(d.juneUes * 100) / 100
                                var m = Math.round(d.june * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'july',
                            minWidth: 150,
                            title: '7月',
                            templet: function (d) {
                                var muse = Math.round(d.julyUes * 100) / 100
                                var m = Math.round(d.july * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'august',
                            minWidth: 150,
                            title: '8月',
                            templet: function (d) {
                                var muse = Math.round(d.augustUes * 100) / 100
                                var m = Math.round(d.august * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'september',
                            minWidth: 150,
                            title: '9月',
                            templet: function (d) {
                                var muse = Math.round(d.septemberUes * 100) / 100
                                var m = Math.round(d.september * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'october',
                            minWidth: 150,
                            title: '10月',
                            templet: function (d) {
                                var muse = Math.round(d.octoberUes * 100) / 100
                                var m = Math.round(d.october * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'november',
                            minWidth: 150,
                            title: '11月',
                            templet: function (d) {
                                var muse = Math.round(d.novemberUes * 100) / 100
                                var m = Math.round(d.november * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'december',
                            minWidth: 150,
                            title: '12月',
                            templet: function (d) {
                                var muse = Math.round(d.decemberUes * 100) / 100
                                var m = Math.round(d.december * 100) / 100
                                return muse + '/' + m
                            }
                        }
                        ]
                    ]

                    setTableChild(_colsChild, param)

                    form.on('submit(submitChild)', function (data) {
                        Object.assign(param, {
                            ascriptionCompanyId: demo1.getValue('valueStr'),
                            ascriptionOrganId: demo2.getValue('valueStr'),
                        })
                        reloadTableChild(_colsChild, param)
                    });

                    $('.layui-export-child').off('click').on('click',function (e) {
                        var _this = $('.layui-export')
                        _this.attr('disabled',true)
                        var aLink = document.createElement('a');
                        aLink.href='${ctx}/financial/budget/budgetReportExport?searchCode=type2&budgetId='+param.budgetId+'&ascriptionCompanyId='+param.ascriptionCompanyId+'&ascriptionOrganId='+param.ascriptionOrganId
                        console.log(aLink.href)
                        aLink.dispatchEvent(new MouseEvent('click', {
                            bubbles: true,
                            cancelable: true,
                            view: window
                        }));
                    })


                }
            });

        }

        function showCaseChild2List(param) {
            var param = param
            var _height = $(document).height() * 0.9
            var _width = $(document).width() * 0.98
            openIndex = layer.open({
                type: 1,
                title: '预算-公司',
                area: [_width + 'px', _height + 'px'],
                content: $('#table-content-child-h2').html(),
                success: function () {

                    var demo3 = xmSelect.render({
                        el: '#costTypeId2',
                        theme: {
                            color: '#3BA9FF',
                        },
                        size: 'small',
                        filterable: true,
                        filterDone: function(val, list){
                            $('.xm-option-content').each(function () {
                                var _this = $(this)
                                _this.attr('title', _this.text())
                            })
                        },
                        toolbar: {
                            show: true
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
                                                _html += '<div class="xm-label-block lf-select-block">' + cur.name + '</div>'
                                            })
                                            return _html
                                        }
                                    }
                                }
                            }
                        },
                        data: costTypeData
                    })

                    var _colsChild = [
                        [
                        {
                            field: 'year',
                            minWidth:120,
                            title: '年份',
                            fixed: 'left',
                            event: 'case',
                        },
                        {
                            field: 'ascriptionCompany',
                            minWidth: 150,
                            title: '预算归属公司',
                        }, {
                            field: 'ascriptionOrgan',
                            minWidth: 150,
                            title: '机构',
                        }, {
                            field: 'costTypeName',
                            minWidth: 150,
                            title: '费用类型',
                        },
                        {
                            field: 'yearMoney',
                            minWidth: 160,
                            title: '年度预算总额',
                            templet: function (d) {
                                return Math.round(d.yearMoney * 100) / 100
                            }
                        },  {
                            field: 'useYearMoney',
                            minWidth: 150,
                            title: '已占用',
                            templet: function (d) {
                                return Math.round(d.useYearMoney * 100) / 100
                            }
                        }, {
                            field: 'surplusYearMoney',
                            minWidth: 150,
                            title: '剩余预算',
                            templet: function (d) {
                                return Math.round(d.surplusYearMoney * 100) / 100
                            }
                        },{
                            field: 'january',
                            minWidth: 150,
                            title: '1月',
                            templet: function (d) {
                                var muse = Math.round(d.januaryUes * 100) / 100
                                var m = Math.round(d.january * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'february',
                            minWidth: 150,
                            title: '2月',
                            templet: function (d) {
                                var muse = Math.round(d.februaryUes * 100) / 100
                                var m = Math.round(d.february * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'march',
                            minWidth: 150,
                            title: '3月',
                            templet: function (d) {
                                var muse = Math.round(d.marchUes * 100) / 100
                                var m = Math.round(d.march * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'april',
                            minWidth: 150,
                            title: '4月',
                            templet: function (d) {
                                var muse = Math.round(d.aprilUes * 100) / 100
                                var m = Math.round(d.april * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'may',
                            minWidth: 150,
                            title: '5月',
                            templet: function (d) {
                                var muse = Math.round(d.mayUes * 100) / 100
                                var m = Math.round(d.may * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'june',
                            minWidth: 150,
                            title: '6月',
                            templet: function (d) {
                                var muse = Math.round(d.juneUes * 100) / 100
                                var m = Math.round(d.june * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'july',
                            minWidth: 150,
                            title: '7月',
                            templet: function (d) {
                                var muse = Math.round(d.julyUes * 100) / 100
                                var m = Math.round(d.july * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'august',
                            minWidth: 150,
                            title: '8月',
                            templet: function (d) {
                                var muse = Math.round(d.augustUes * 100) / 100
                                var m = Math.round(d.august * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'september',
                            minWidth: 150,
                            title: '9月',
                            templet: function (d) {
                                var muse = Math.round(d.septemberUes * 100) / 100
                                var m = Math.round(d.september * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'october',
                            minWidth: 150,
                            title: '10月',
                            templet: function (d) {
                                var muse = Math.round(d.octoberUes * 100) / 100
                                var m = Math.round(d.october * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'november',
                            minWidth: 150,
                            title: '11月',
                            templet: function (d) {
                                var muse = Math.round(d.novemberUes * 100) / 100
                                var m = Math.round(d.november * 100) / 100
                                return muse + '/' + m
                            }
                        },{
                            field: 'december',
                            minWidth: 150,
                            title: '12月',
                            templet: function (d) {
                                var muse = Math.round(d.decemberUes * 100) / 100
                                var m = Math.round(d.december * 100) / 100
                                return muse + '/' + m
                            }
                        }
                        ]
                    ]

                    setTableChild2(_colsChild, param)

                    form.on('submit(submitChild2)', function (data) {
                        Object.assign(param, {
                            costTypeId:demo3.getValue('valueStr'),
                        })
                        reloadTableChild2(_colsChild, param)
                    });

                    $('.layui-export-child2').off('click').on('click',function (e) {
                        var _this = $('.layui-export')
                        _this.attr('disabled',true)
                        var aLink = document.createElement('a');
                        aLink.href='${ctx}/financial/budget/budgetReportExport?searchCode=type3&budgetId='+param.budgetId+'&ascriptionOrganId='+param.ascriptionOrganId+'&costTypeId='+param.costTypeId
                        console.log(aLink.href)
                        aLink.dispatchEvent(new MouseEvent('click', {
                            bubbles: true,
                            cancelable: true,
                            view: window
                        }));
                    })
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
            ['email', /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/],
            ['num_0_1', /^(0+(\.[0-9]{1,2})?|1|1.0|1.00?)$/]
        ])
        if (map.get(parama).test(paramb)) {
            return true
        } else {
            return false
        }
    }
</script>



</body>

</html>