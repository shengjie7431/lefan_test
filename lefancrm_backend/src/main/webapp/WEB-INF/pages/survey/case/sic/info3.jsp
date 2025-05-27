<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>详情</title>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">

    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/lefan23.css?v=1" type="text/css">

</head>
<style>
    .table-title{
        font-size: 14px;
        font-weight: bold;
        line-height: 40px;
    }
    table.spec-info tr td:nth-of-type(2n + 1) {
        width: 13%;
    }
    table.spec-info tr td:nth-of-type(2n) {
        width: 20%;
    }

    * {
        font-weight: normal;
    }

    .contain {
        width: 96%;
        /*padding: 30px 2%;*/
        margin: 10px auto;
        /*border: 1px solid #bbb;*/
    }

    .contain .d_title {
        width: 100%;
        /*height: 40px;*/
        margin: 0 auto;
        padding: 7px 0;
        border-bottom: 1px solid #bbb;
    }

    .contain .d_title .text {
        display: inline-block;
        padding: 0 7px;
        height: 38px;
        line-height: 38px;
        background-color: #45B4FE;
        color: #fff;
        font-size: 14px;
        text-align: center;
        cursor: pointer;
    }

    .contain .d_title div.text2 {
        height: 38px;
        line-height: 38px;
        background-color: rgba(69, 180, 254, 0.7);
    }

    .contain .task {
        width: 100%;
        padding: 16px 2%;
        margin: 20px 0;
        background-color: rgba(63, 181, 253, 0.1);
    }

    .contain .task .title {
        height: 40px;
        line-height: 40px;
        font-size: 16px;
        font-weight: bold;
        border: none;
    }

    .contain .task .details {
        width: 100%;
        padding: 16px 0;
        font-size: 12px;
        display: flex;
        flex-wrap: wrap;
    }

    .contain .task .details .detail1 {
        width: 78%;
        padding-right: 2%;
        display: flex;
        margin-bottom: 10px;
    }

    .contain .task .details .detail1 .name {
        width: 80px;
        min-width: 66px;
    }

    .contain .task .details .detail1 .value {}

    .contain .task .details .detail1 .value p {
        margin: 0;
        font-weight: 500;
    }

    .contain .task .details .detail2 {
        width: 20%;
        display: flex;
        margin-bottom: 10px;
    }

    .contain .task .details .detail2 .name {
        width: 80px;
        min-width: 66px;
    }

    .contain .task .details .detail2 .date {
        /* min-width: 86px; */
        color: #ff9800;
    }

    .contain .task .details span {
        display: inline-block;
        padding: 4px 6px;
        margin-bottom: 8px;
        /*height: 22px;*/
        line-height: 22px;
        font-size: 12px;
        /*background-color: rgba(0, 150, 136, 0.67);*/
        color: #fff
    }

    .contain .task .details span.color1 {
        background-color: rgba(229, 28, 35, 0.54)
    }

    .contain .task .details span.color2 {
        background-color: rgba(232, 96, 36, 0.66)
    }

    .contain .task .details span.color3 {
        background-color: rgba(37, 155, 36, 0.67);
    }

    .contain .task .details span.color4 {
        background-color: rgba(0, 150, 136, 0.72);
    }

    .contain .task .details span.color5 {
        background-color: rgba(150, 74, 230, 0.61);
    }

    .contain .task .details span.color6 {
        background-color: rgba(61, 58, 157, 0.66);
    }

    .contain .task .details span.color7 {
        background-color: rgba(47, 149, 162, 0.66);
    }

    .contain .task .details span.color8 {
        background-color: rgba(24, 172, 213, 0.66);
    }

    .contain .task .details span.color9 {
        background-color: rgba(221, 194, 14, 0.66);
    }

    .contain .caseInfo {
        width: 100%;
        padding: 16px 0;
        margin: 20px 0;
        display: flex;
        justify-content: space-between;
    }

    .contain .caseInfo .info1 {
        width: 70%;
        /* margin: 5%; */
        background-color: rgba(63, 181, 253, 0.1);
    }

    .contain .caseInfo .info2 {
        width: 28%;
        background-color: rgba(63, 181, 253, 0.1);

    }

    .contain .caseInfo .info1 .title {
        height: 48px;
        line-height: 48px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-bottom: 1px solid #fff;
    }

    .contain .caseInfo .info1 .title .text1 {
        margin-left: 40px;
        font-size: 16px;
        font-weight: bold;
    }

    .contain .caseInfo .info1 .title .text2 {
        padding: 0 6px;
        height: 26px;
        line-height: 26px;
        margin-right: 22px;
        font-size: 14px;
        background-color: rgba(63, 181, 253, 0.6);
        color: #fff;

    }

    .contain .caseInfo .info1 .table {
        width: 100%;
        overflow: auto;
    }

    .contain .caseInfo .info1 .table .tr {
        width: 100%;
        display: flex;
        border-bottom: 1px solid #fff;
    }

    .contain .caseInfo .info1 .table .tr .td {
        width: 38%;
        padding: 5px 0.5%;
        line-height: 20px;
        text-align: center;
        font-size: 14px;
        font-weight: 300;
        border-right: 1px solid #fff;
    }

    .contain .caseInfo .info1 .table .thead div.td {
        line-height: 30px;
        font-weight: 500;
    }

    .contain .caseInfo .info1 .table .tr div.wid {
        width: 12%;
        padding: 5px 0.5%;
    }

    .contain .caseInfo .info2 .i_title {
        height: 48px;
        line-height: 48px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .contain .caseInfo .info2 .i_title .text1 {
        margin-left: 13px;
        font-size: 16px;
        font-weight: bold;
    }

    .contain .caseInfo .info2 .i_title .text2 {
        padding: 0 4px;
        height: 26px;
        line-height: 26px;
        margin-right: 22px;
        font-size: 14px;
        background-color: rgba(63, 181, 253, 0.6);
        color: #fff;

    }

    .contain .caseInfo .info2 .bars {
        width: 100%;
        /* height: 110px; */
        padding-bottom: 10px;
        overflow: auto;
    }

    .contain .caseInfo .info2 .bars .bar {
        width: 100%;
        display: flex;
    }

    .contain .caseInfo .info2 .bars div.b_hide {
        display: none;
    }

    .contain .caseInfo .info2 .bars .bar .bar-date {
        min-width: 83px;
        /* max-width: 83px; */
        width: 28%;
        padding-right: 2%;
        font-size: 12px;
        text-align: right;
    }

    .contain .caseInfo .info2 .bars .bar .bar-contain {
        width: 70%;
    }

    .contain .caseInfo .info2 .bars .bar .bar-contain .title {
        width: 100%;
        display: flex;
        align-items: flex-start;
        border: none;
    }

    .contain .caseInfo .info2 .bars .bar .bar-contain .title .index {
        width: 18px;
        height: 18px;
        border: 3px solid #45B4FE;
        border-radius: 50%;
        background-color: #fff;
    }

    .contain .caseInfo .info2 .bars .bar .bar-contain .title div.index-btn {
        width: 18px;
        height: 18px;
        border: 3px solid #45B4FE;
        border-radius: 50%;
        background-color: #45B4FE;
        background-image:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAACgElEQVR4Xu1Z7TEEURDsiYAUREAGiMCJABEgAkTgRIAIyAAZkAERIIJRzWzV8+pub3ffhy07789VObsz09Pz8foEEz8y8fjhADgDJo6Al8DECeBN0EvAS2DiCHgJTJwAPgW8BLwEEhFQ1XUAZwA+AFyJCD+LHVXdAbAN4ElEHlMNJZeAqt4D2DNHngHslgJBVQ8BXAdBb4jIawoISQBY9t8jBwjCkYjwM9tR1WMA8+iFFyJynmIkCQAaVtUbAAeREywDMiELCKrKrDP78flbBhgA7AEEoSmDxkmCsJ9ap0uC/wRwIiK0m3SSGdBYX8IEfs1y6O2oldcdADa98DD4nVzsygZASzn0BsGCfwCwVTJ4vjsrAAZC3KmbGOYicrqKry3BvwCYpXb92H52AFaAcCMiR8tAUFVmnJlnXwkPgyfts+8YRQAwEFi73BHWomD4N/aFX8G0BP9kmc8efJESCIO1oLitxSD8WphUdWYLTpz5WxFZNP5WVVLn74sxIJgOpDWnwGbkFUHYty4fbnfNvxUPvjgDAhCYWTIhBoG0jrPOx5I3vK4UKM6ADiDEvg7aG7oGXGUKtDnTsjD13heGBh0+V40BUXOM7w9Zt7s+wGQBwG5qtMvG1WlcqeoJgEsAbzbmOl2cxqgHhNmclh5gCw/1gLCTux5g8pjrAa4H/LTjQXPd9YCfW6HrAcFMdz2g645RfRFaZNAWFtcD7BboeoDrAa4HfP+IukgUcT0gQGDQ0tSn6/+5IGIXqP+lBwzJwFA9YIittmeyCCK5nar5PgegJtpjtOUMGGNWavrkDKiJ9hhtOQPGmJWaPjkDaqI9RlvOgDFmpaZPzoCaaI/R1hc21DtQlmlM1gAAAABJRU5ErkJggg==);
        background-position: center center;
        background-size: contain;
    }


    .contain .caseInfo .info2 .bars .bar .bar-contain .title .text {
        /* width: 70%; */
        height: 18px;
        line-height: 18px;
        color: #333;
        font-size: 12px;
        margin-left: 13px;
        overflow: hidden;
    }

    .contain .caseInfo .info2 .bars .bar .bar-contain .title div.text-btn {
        margin-left: 13px;
        padding: 2px 6px;
        /*height: 14px;*/
        line-height: 14px;
        color: #45B4FE;
        border: 1px solid #45B4FE;
        font-size: 12px;
        text-align: center;
    }

    .contain .caseInfo .info2 .bars .bar .bar-contain .content {
        width: 70%;
        margin-left: 8px;
        padding-left: 20px;
        padding-bottom: 20px;
        border-left: 2px solid #45B4FE;
        color: #333;
        font-size: 12px;
    }

    .contain .caseInfo .info2 .bars .bar .bar-contain .content .text2{
        overflow: hidden;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
        text-overflow: ellipsis;
    }
    .table-main {
        position: relative;
        width: 100%;
        padding: 26px;
        border: 1px solid #bbb;
        font-size: 12px;
        margin: 20px auto;
    }

    .table-title {
        position: absolute;
        top: -14px;
        left: 10px;
        width: 110px;
        height: 26px;
        line-height: 26px;
        color: #45B4FE;
        background-color: #fff;
        border: 1px solid #45b4fe;
        text-align: center;
        font-size: 13px;
    }

    table.info tr {}

    table.info tr td {
        /*display: inline-block;*/
        width: 24%;
        line-height: 20px;
        padding: 10px 1% 10px 0;
        font-size: 12px;
    }
    .stepTitle {
        width: 100%;
        height: 0px;
        display: flex;
        justify-content: space-between;
    }
    .stepTitle .stepTitle-r {
        width: 80px;
        height: 24px;
        line-height: 24px;
        background-color: #45B4FE;
        color: #fff;
        font-size: 12px;
        text-align: center;
    }

    .table-title1{
        font-size: 16px;
        font-weight: bold;
        line-height: 40px;
    }
    table.table1 th{
        padding: 8px;
        text-align: center;
        font-weight: bold;
    }
    table.table1 tr td{
        padding: 8px;
    }

    .dalogs{
        display: none;
        position: absolute;
        bottom: 30%;
        left: 20%;
        width: 600px;
        height: 400px;
        background-color: #fff;
        box-shadow: 0 0 10px #999;
        color: #000;
    }

    .dalogsHuzhu{
        display: none;
        position: absolute;
        bottom: 4%;
        left: 4%;
        width: 90%;
        height: 90%;
        background-color: #fff;
        box-shadow: 0 0 12px 3px #999;
        color: #000;
        padding: 1%;
        pointer-events: auto;
    }
    .dalogsHuzhu .dalogsHuzhu-content{
        width: 100%;
        height: 97%;
        overflow: auto;
        position: relative;
    }
    .huzhu-table{
        width: 100%;
    }
    .huzhu-close{
        float: right;
        width: 23px;
        height: 23px;
    }
    .huzhu-close .close1 {
        position: relative;
        width: 2px;
        height: 20px;
        margin-left: 10px;
        background: #ff0000;
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        -o-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        transform: rotate(45deg);
        display: inline-block;
        cursor: pointer;
    }

    .huzhu-close .close1:after {
        content: "";
        position: absolute;
        top: 0;
        left: 0;
        width: 2px;
        height: 20px;
        background: #ff0000;
        -webkit-transform: rotate(270deg);
        -moz-transform: rotate(270deg);
        -o-transform: rotate(270deg);
        -ms-transform: rotate(270deg);
        transform: rotate(270deg);
    }

    .dalogsHuzhu .dalogsHuzhu-btns{
        width: 100%;
        text-align: right;
        padding: 20px 0;
    }
    .dalogsHuzhu .dalogsHuzhu-btns .dalogsHuzhu-btn{
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
    .dalogsHuzhu .dalogsHuzhu-btns .dalogsHuzhu-btn.dalogsHuzhu-btn0{
        background-color: #b7b6b6;
        color: #fff;
    }
    .dalogsHuzhu table.table1 tr th:nth-of-type(n+9){
        display: none;
    }
    .dalogsHuzhu table.table1 tr td:nth-of-type(n+9){
        display: none;
    }
    .po-none{
        pointer-events: none;
    }
    .iframe-content{
        width: 100%;
        height: 600px;
    }
    #iframeNW{
        width: 100%;
        height: 600px;
    }
    .synchrodata{
        position:fixed;
        top: 20px;
        right: 20px;
        width: 66px;
        height: 66px;
        border: 1px solid #eee;
        background: rgba(59,169,254,0.8);
        color: #fff;
        border-radius: 66px;
        text-align: center;
        cursor: pointer;
    }
    .synchrodata-icon{
        margin-top: 10px;
        display: inline-block;
        width: 20px;
        height: 20px;
        background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEQAAABACAYAAACjgtGkAAAFrUlEQVR4Xu1bi1FVMRDdrUCpQKxAqECtQKhAqUCtQKxAqUCoQKhAqUCtQKhAqGCdcyd5k7cvudl8bu6MkhmGYW4+m5P978K0whCR10S0T0Qv3PH+N/78TkQ/3c8VM9+NJJFHHSYiB0T0loiOiOhxwbnnRHTGzABp8bE4ICICTvgScEPtpS6J6D0z39RuYFm3KCAi8omI3lkIKZhzyswfC+YXTV0EEBGBSHwjIohJatw6fYEX9+KA+V63PJlZC245WUK/dAfEAMYFEZ0zM5RncogIdM0bInqVmAQQX/YGpSsgGTCuccFSHeCU8Wcieh4BpjsovQH5ERGTe+gRZoa1qB4iAm6BctbjkpmPqzdWC7sBIiJ4RZjVcACMF71MpuMWiNojdQ6sD85vHl0AERE4VlCii4HhN54B5WmpOMbQ6wUIwAi9TZx1zMywBt1HQny6iE4zIAnugGfZ2//YAlZEALa2QIet4tkDEE0Y9MZ+b3Oo2cx5wLAyoT65YGYo3+rRBIgj6rc6/SMzn1ZTVLAwosgRCEKXVAeErYDETGEX5WbBJfEgTbqrFRD4Fgjl/UC4Dg9z2BARiM2z4MAm/dUKCMQFsYcfw8QlMMPa/7lmZm3xzA/UCoiokxBbzMYoZsqME13M8zWczszV96pemJDfZrNnxGEzLWb2FwfEXR7BFVjRiwhC/K3wvoWQUiACkQENiKHC4bkUqQX84O9biyc7yyEOfShNk22vAUREtsSuxx4z4ELfQM8lzXIUEBfGfyjNdvW4TOkeCU95juEABrJuZ7FJO4AYEjypw+6ZuSR5PO3TiUNwSR0B56QwmkvZAsQABtxybORlFDrF65GjGgvTCRCItA//fRkDgHidl0pH7oCyAcSQ7UKSZ0gpIPe0pd9zWTdmPvR7hoAg/oDeCAc4Amm/RcL40ou1znc+C7xrLV4bh3ICxCGoTVfXbFfrZXqtTySYoIPgQ914QGK5heFeZ69L5/ZJWKYpdcBOd/xRmzQFSDmCwu89lGrJeYFDp5ngjpn3AEgshN9rySmUELgiIPC4dS7nBIBopIaG8GsB4nSnTh1cABDY7bAINDSEXxkQbVmvAYjOafyzylSLsoggEY6CvB83AGT1nEaJzuk5N5o6EBEdB/xPHKINyn1Mh3QrC/Z8zSX2EpGoDvmfrYwuzl89+CHbrDf5ITEHZZjpXcvsioguoQCaPR/LaF8EihbKdfFwfw1AErHMVL7wgMTaGZCcRQRYXRZcQhG27umiXXQr6OzeVHEM8yGaS3D2ME5pvahlveMM1HA0GJtgVmfMICKxdBvSc1i0aI+o5VI1cxxXIPkVK7P+cl1OkyTonCryo7GWJU+Hz6fOiRG+IUBcFDxnDMK6cgwr3Me3esa+Iwl2ENIay7rnQLE8klnUHBv7ZjpU7rOKPJHhs9AVzgFnID26dd5cXQYOW6wV0nqwyXSLSOgcmVIPEQ/TSpOfd+XA2OF0S+UO9nquqzhFDDqNs62YyuyaKvczLZo5YNAriyJVsiBvKnY7FkUgBHGChg77MTQRkEtwF8oWWZNdCQhoQBwCJZl6LF9DAn2gB015Wb1mAiQHe8v3GkBazsutfQBEITQEEBFBhzPaEXYKXhYOcetBOkoFWTHMccHc98UBERF4ht4h2ilv5AAREZhk347RpTl3bUB0inLL+swBosCY7lHaLlHKLSM4RKf6QeOmdTIFSMK0msxyKQjh/BGAxDzfjScbAyQBxpBa8+KAAP1E/mEqMKvqGRwnlAZ0eD4EjJ3groXVcmsTrw5xChv34DjB6dLhucnrzdFg+T6EQzwhlTHIMDCGckgASiyXmXq85v9usHDFUKUaIyhST45NGw7GKhzilCx0BCLOVJC4lcUqfeWW+UN1SEioa9SBEtX9XquBsRqHBPpE+yjDzGuKi1bjEAUKgj6IUbd/aa0Vm79SdcWv49IE2wAAAABJRU5ErkJggg==);
        background-size: cover;
        background-repeat: no-repeat;
        background-position: center;
    }
    .synchrodata-text{
        display: block;
        width: 100%;
        height: 20px;
        line-height: 20px;
        color: #fff;
        text-align: center;
        font-size: 10px;
    }

</style>
<style>
    /*body {*/
    /*width: 100%;*/
    /*height: 100%;*/
    /*}*/

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

    .colleagueType{
        display: inline-block;
        padding: 4px 6px;
        margin-bottom: 8px;
        /*height: 22px;*/
        line-height: 22px;
        font-size: 12px;
        /*background-color: rgba(0, 150, 136, 0.67);*/
        color: #fff
    }

    .colleagueTasks{
        padding: 0px 6px;
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
</head>

<body>
<c:if test="${(menuCode == 'assign-list' || menuCode == 'assign-org-list' || menuCode == 'survey-list' || menuCode == 'dcy-list' ) && showNwPage}">
    <input type="hidden" value="${nuanWaUser}" id="nuanWaUser">
    <input type="hidden" value="${nuanWaSign}" id="nuanWaSign">
    <input type="hidden" value="${nuanWaCompany}" id="nuanWaCompany">
    <input type="hidden" value="${handleId}" id="handleId">
    <div class="iframe-content">
        <iframe src="" id="iframeNW"></iframe>
        <div <c:if test="${menuCode == 'dcy-list' && dto.surveyAssignOrg.reportDate != null}">style="display: none;" </c:if> class="synchrodata">
            <span class="synchrodata-icon"></span>
            <div  class="synchrodata-text">同步数据</div>
        </div>
    </div>
</c:if>
<div class='contain'>
    <div class='d_title'>
        <input type="hidden" name="again" id ="again" value="${again}" />
        <input type="hidden" name="surveyInfoId" id ="surveyInfoId" value="${dto.surveyRiskCaseInfo.id}" />
        <input type="hidden" name="surveyCaseId" id ="surveyCaseId" value="${dto.id}" />
        <input type="hidden"  id ="zhongan" value="${dto.zhongan}" />
        <c:if test="${dto.surveyState == 0 && menuCode == 'dcy-list'}">
            <%--            <button class="butList active" onclick="operate(${dto.id},'accept',true)">接收</button>--%>
            <div class='text' onclick="operate(${dto.id},'accept',true)">接收 </div>
            <%--            <button class="butList active" onclick="operate(${dto.id},'refuse',false)">拒绝</button>--%>
            <div class='text' onclick="operate(${dto.id},'refuse',false)">拒绝 </div>
        </c:if>
        <c:if test="${menuCode == 'dcy-list'}">
            <%--            未完成调查方向--%>
            <c:if test="${dto.showAddDirectionBtn && (showNwPageEdit ||( dto.haveNwAccount == null || dto.haveNwAccount == 0))}">
                <%--<div class='text' onclick="showDirectionView(2,this)" id="initOpen">增加调查方向 </div>--%>
                <div class='text' onclick="addDirectionNew(${dto.id},'direction',false)" id="initOpen">增加调查方向 </div>
            </c:if>

            <!-- 互助 -->
            <c:if test="${dto.surveyRiskCaseInfo.surveyConsignor.orgAttr == 2}">
                <c:if test="${dto.isDirectionSuccess == 1 && dto.surveyState == 1}">
                    <%--<div class='text' onclick="operate(${dto.id},'huzhuCommit',false)">填写调查结论</div>--%>
                    <%--2020年9月14日18:52:19，0923版本：互助的流程去掉填写报告结论环节 所有调查员都是做完方向 提交审核 不写任何小结或结论--%>
                    <div class='text' onclick="operate(${dto.id},'huzhuCommit',true)">提交审核</div>
                </c:if>
            </c:if>
            <!-- 非互助 -->
            <c:if test="${dto.surveyRiskCaseInfo.surveyConsignor.orgAttr != 2}">
                <%--            机构内非最后一个人提交审核--%>
                <c:if test="${dto.showCommitBtn  && (showNwPageEdit ||( dto.haveNwAccount == null || dto.haveNwAccount == 0))}">
                    <%--                <button class="butList active" onclick="operate(${dto.id},'report-commit',true)">提交审核</button>--%>
                    <div class='text' onclick="operate(${dto.id},'report-commit',true,'valid')">提交审核 </div>
                </c:if>
                <%--            机构内是最后一个人提交审核  辅助机构--%>
                <c:if test="${dto.showOrgSummaryBtn  && (showNwPageEdit ||( dto.haveNwAccount == null || dto.haveNwAccount == 0))}">
                    <%--                <button class="butList active" onclick="operate(${dto.id},'last-org-summary',false)">填写机构小结</button>--%>
                    <div class='text' onclick="operate(${dto.id},'last-org-summary',false,'valid')">填写机构小结 </div>
                </c:if>
<%--                <div class='text' onclick="operate(${dto.id},'last-org-report-completion',false,'valid')">确认基础信息并填写报告结论 </div>--%>

                <%--            机构内是最后一个人提交审核  主机构--%>
                <c:if test="${dto.showReportCompletionBtn  && (showNwPageEdit ||( dto.haveNwAccount == null || dto.haveNwAccount == 0))}">
                    <%--                <button class="butList active" onclick="operate(${dto.id},'last-org-report-completion',false)">填写报告结论</button>--%>
                    <div class='text' onclick="operate(${dto.id},'last-org-report-completion',false,'valid')">确认基础信息并填写报告结论 </div>
                </c:if>
                <c:if test="${dto.showOrgCommitBtn  && (showNwPageEdit ||( dto.haveNwAccount == null || dto.haveNwAccount == 0))}">
                    <%--                <button class="butList active" onclick="operate(${dto.id},'org-commit',true)">提交审核</button>--%>
                    <div class='text' onclick="operate(${dto.id},'org-commit',true,'valid')">提交审核 </div>
                </c:if>
                <c:if test="${dto.surveyInvestigatorCases.size() > 1 && dto.surveyState != 0}">
                    <%--                <button class="butList" onclick="operate(${dto.id},'tasks',false)">其他任务信息（${dto.successNum}/${dto.allNum}）</button>--%>
                    <div class='text text2' onclick="operate(${dto.id},'tasks',false)">其他人的任务信息(${dto.successNum}/${dto.allNum})</div>
                </c:if>
                <c:if test="${dto.surveyAssignOrg.orgSurveyState == 2 || dto.surveyAssignOrg.orgSurveyState == 4}">
<%--                    <c:if test="${dto.surveyRiskCaseInfo.reportCompletion != null}">--%>
<%--                        <div class='text' onclick="operate(${dto.id},'last-org-report-completion-view',false)">查看报告结论</div>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${dto.surveyRiskCaseInfo.reportCompletion == null}">--%>
<%--                        <div class='text' onclick="operate(${dto.id},'last-org-summary-view',false)">查看机构小结 </div>--%>
<%--                    </c:if>--%>
                    <div class='text' onclick="operate(${dto.id},'last-org-summary-view',false)">查看机构小结/报告结论 </div>
                </c:if>
            </c:if>
        </c:if>
        <c:if test="${dto.surveyState == 3 && menuCode == 'report-list'}"> <!-- 审核中 且 是报告审核菜单-->
            <c:if test="${dto.isSun == 0}">
                <%--                <button class="butList active" onclick="operate(${dto.id},'sign',true)">标记阳性</button>--%>
                <%--                <div class='text' onclick="operate(${dto.id},'sign',true)">标记阳性 </div>--%>
            </c:if>
            <c:if test="${dto.isSun == 1}">
                <%--                <button class="butList active" onclick="operate(${dto.id},'resign',true)">取消阳性</button>--%>
                <%--                <div class='text'  onclick="operate(${dto.id},'resign',true)">取消阳性 </div>--%>
            </c:if>
            <%--            <button class="butList active" onclick="operate(${dto.id},'pass1',true)">审核通过</button>--%>
            <div class='text' onclick="operate(${dto.id},'pass1',true)">审核通过 </div>
            <%--            <button class="butList active" onclick="operate(${dto.id},'back',false)">退回</button>--%>
            <div class='text'  onclick="operate(${dto.id},'back',false)">退回 </div>
            <%--            <button class="butList active" onclick="operate(${dto.id},'direction',false)">增加调查方向</button>--%>
            <%--<div class='text'  onclick="operate(${dto.id},'direction',false)">增加调查方向 </div>--%>
            <%--<div class='text' onclick="showDirectionView(2,this)">增加调查方向 </div>--%>
            <div class='text' onclick="addDirectionNew(${dto.id},'direction',false)" id="initOpen">增加调查方向 </div>
        </c:if>
        <c:if test="${dto.surveyState == 6 && menuCode == 'report-review-list'}">
            <%--<button class="butList active" onclick="operate(${dto.id},'pass1',true)">审核通过</button>--%>
            <%--<button class="butList active" onclick="operate(${dto.id},'back1',false)">退回</button>--%>

        </c:if>
        <c:if test="${menuCode == 'refuse-list'}">
            <c:if test="${dto.surveyState == 5}">
                <%--                <button class="butList active" onclick="operate(${dto.id},'refuseYes',true)">有效</button>--%>
                <div class='text' onclick="operate(${dto.id},'refuseYes',true)">有效 </div>
                <%--                <button class="butList active" onclick="operate(${dto.id},'refuseNo',false)">无效</button>--%>
                <div class='text' onclick="operate(${dto.id},'refuseNo',false)">无效 </div>
            </c:if>
        </c:if>
        <c:if test="${display}">
<%--            <button class="butList active" onclick="caseClockDetails(${dto.surveyRiskCaseInfo.id},'caseClockDetails')">打卡足迹(${dto.surveyRiskCaseInfo.punchClockCount})</button>--%>
            <div class="text" onclick="caseClockDetails(${dto.surveyRiskCaseInfo.id},'caseClockDetails')">打卡足迹(${dto.surveyRiskCaseInfo.punchClockCount})</div>
        </c:if>
        <c:if test="${dto.surveyState != 4 && dto.isDirectionSuccess == 0}">
            <div class='text' style="float: right;background-color: #F0F0F0;color: #2f332a;border: 1px solid #F0F0F0; width: 50px;" onclick="operate(${dto.id},'case-return',false)">退回</div>
        </c:if>
        <c:if test="${dto.surveyState != 4 && dto.isDirectionSuccess == 1}">
            <div class='text' style="float: right;background-color: #F0F0F0;color: #2f332a;border: 1px solid #F0F0F0;" onclick="operate(${dto.id},'revoke',true)">有方向要添加或调整？点击撤回</div>
        </c:if>
    </div>
    <c:if test="${dto.surveyRemark !=null}">
        <div class="m-state">
            <div class="m-state__title">初审退回</div>
            <div class="m-state__reason">${dto.surveyRemark}</div>
        </div>
    </c:if>
    <div class='task' <c:if test="${menuCode == 'feeViewSurvey'}">style="display: none" </c:if> >
        <div class="title" id="title">我的任务</div>
        <div class="details">
            <div class="detail1">
                <div class="name">任务类型：</div>
                <div class="value">
                    <c:forEach items="${dto.tasks}" var="item">
                        <span class='' style="background-color: ${item.taskColor}">${item.taskName}</span>
                    </c:forEach>
                </div>
            </div>
            <div class="detail2">
                <div class="name">任务状态：</div>
                <div>${dto.surveyStateName}</div>
            </div>
            <div class="detail1">
                <div class="name">任务描述：</div>
                <div class="value" >
                    <pre style="background-color:rgba(0, 0, 0, 0);border:dashed 0px #fff; margin: -10px 0 10px; white-space: pre-wrap; word-wrap: break-word;font-weight :bold">${dto.surveyTaskRemark}</pre>
                    <%--<p style="font-weight :bold" >${dto.surveyTaskRemark}</p>--%>
                </div>
            </div>
            <div class="detail2">
                <div class="name">截止日期：</div>
                <div class="date" style="font-weight :bold" ><fmt:formatDate value="${dto.surveyEndTime}" pattern="yyyy-MM-dd"/></div>
            </div>
        </div>
    </div>
    <%--    调查方向--%>
    <c:if test="${dto.surveyCaseDirections !=null &&  dto.surveyCaseDirections.size()>0}">
        <div class='task1'>
            <div class="table-title1" style="padding: 0px 2%;">我的调查方向<label style="float:right;">
                <c:if test="${dto.mechanismType==1}">
                    (调查总积分:${dto.totalSurveyPoints}阳性总积分:${dto.positiveTotalScore}合计积分:${dto.totalSurveyPoints+dto.positiveTotalScore})
                </c:if>
            </label></div>
            <table class="table1" border="1" cellpadding="0" cellspacing="0" style="text-align: left;table-layout: fixed;">
                <thead>
                <th style="width:5%;">序号</th>
                <th style="width:9%;">区域</th>
                <th style="width:9%;">任务类型</th>
                <th style="width:12%;">任务子类</th>
                <th style="width:12%;">任务结果</th>
                <th style="width:10%;">方向名称</th>
                <c:if test="${dto.surveyRiskCaseInfo.surveyConsignor.orgAttr == 2}">
                    <th style="width:15%;">方向概述</th>
                </c:if>
                <th style="width:25%;">方向内容</th>
                <th style="width:10%;">方向附件</th>
                <c:if test="${dto.mechanismType==1}">
                    <th style="width:10%;">方向分值</th>
                </c:if>
               <%-- <c:if test="${dto.showExpenseReimbursementValue || dto.showBaoSi}">
                    <th style="width:9%;" >费用报销</th>
                </c:if>--%>
<%--                <c:if test="${dto.showExpenseReimbursementValue || dto.showBaoSi}">--%>
<%--                    <th style="width:9%;" >费用报销</th>--%>
<%--                </c:if>--%>
                <c:if test="${menuCode != 'sun-list'}">
                    <th style="width:11%;min-width: 100px;" align="center">操作</th>
                </c:if>
                </thead>
                <tbody>
                <c:forEach items="${dto.surveyCaseDirections}" var="item" varStatus="st">
                    <tr>
                        <td >${st.index + 1}</td>
                        <td >${item.areaName}</td>
                        <td ><span style="background-color: ${item.taskColor};color: #fff">&nbsp;&nbsp;${item.taskName}&nbsp;&nbsp;</span></td>
                        <td >
                            <c:if test="${dto.zhongan && dto.creportDate == null && menuCode == 'dcy-list'}">
                                <select class="form-control" id="sel_new_task_${item.id}" onchange="loadTaskResult(${item.id})">
                                    <option></option>
                                    <c:forEach items="${item.taskInfoContents}" var="content">
                                        <option <c:if test="${item.newId == content.id}">selected</c:if> value="${content.id}">${content.name}</option>
                                    </c:forEach>
                                </select>
                            </c:if>
                            <c:if test="${!(dto.zhongan && dto.creportDate == null && menuCode == 'dcy-list')}">
                                <span style="background-color: ${item.newColor};color: #fff">&nbsp;&nbsp;${item.newName}&nbsp;&nbsp;</span>
                            </c:if>
                        </td>
                        <td >
                            <c:if test="${dto.zhongan && dto.creportDate == null && menuCode == 'dcy-list'}">
                                <div class="div_task_result">
                                    <select class="form-control" id="sel_task_result_${item.id}" onchange="refreshDirection(${item.id})">
                                        <option></option>
                                        <c:forEach items="${item.resultTypes}" var="result">
                                            <option <c:if test="${item.directionResultTypeId == result.directionResultTypeId}">selected</c:if> value="${result.directionResultTypeId}">${result.directionResultTypeName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:if>
                            <c:if test="${!(dto.zhongan && dto.creportDate == null && menuCode == 'dcy-list')}">
                                ${item.directionResultTypeName}<c:if test="${item.medicalNumber >1}">（病历数量：${item.medicalNumber}份）</c:if>
                            </c:if>
                        </td>
                        <td >
                            <c:if test="${item.orgPoint != 1}">${item.directionName}</c:if>
                            <c:if test="${item.orgPoint == 1}"><span style="color: red;font-weight: bold">${item.directionName}</span></c:if>
                        </td>
                        <c:if test="${dto.surveyRiskCaseInfo.surveyConsignor.orgAttr == 2}">
                            <td ><pre style="background-color:rgba(0, 0, 0, 0);border:dashed 0px #fff; font-size: 14px;white-space: pre-wrap; word-wrap: break-word;">${item.huzhuColsRemark}</pre></td>
                        </c:if>
                        <td ><pre style="background-color:rgba(0, 0, 0, 0);border:dashed 0px #fff; font-size: 14px;white-space: pre-wrap; word-wrap: break-word;">${item.directionText}</pre></td>
                        <td align="center"><a href="javascript:void(0);" onclick="directionFiles(${item.id},'directionFiles','${dto.surveyRiskCaseInfo.surveyCno}')">查看附件(${item.directionFilesSize})</a>
                            <br/> <c:if test="${item.materRaw == 1}"><span style="color: #ff0000">有原件</span></c:if>
                        </td>
<%--                        <c:if test="${dto.showExpenseReimbursementValue || dto.showBaoSi}">--%>
<%--                            <td align="center">${item.expenseReimbursementValue==null?0: item.expenseReimbursementValue}</td>--%>
<%--                        </c:if>--%>
                        <c:if test="${dto.mechanismType==1}">
                            <td align="center">${item.score}</td>
                        </c:if>
                        <c:if test="${menuCode != 'sun-list'}">
                            <td align="center">
                                <c:if test="${menuCode == 'feeViewSurvey'}">
                                    <input type="hidden" id="feeOpr" value="${feeOpr}" />

                                    <a href="javascript:void(0);" onclick="updDirection(${dto.id},${item.id},'feeUpdDirection',false)"><c:if test="${feeOpr == 'edit'}">编辑</c:if><c:if test="${feeOpr != 'edit'}">查看</c:if></a>
                                </c:if>
                                <c:if test="${dto.id == item.surveyInvestigatorCaseId && menuCode != 'feeViewSurvey'}">
                                    <a href="javascript:void(0);"onclick="updDirection(${dto.id},${item.id},'updDirection',false)">
<%--                                        <c:if test="${dto.showExpenseReimbursementValue || dto.showBaoSi}">--%>
<%--                                            <c:if test="${dto.reState == null || dto.reState == 1}">编辑</c:if>&lt;%&ndash;没有报销 或者报销状态为待提交发票情况下&ndash;%&gt;--%>
<%--                                            <c:if test="${dto.reState != null && dto.reState > 1}">查看</c:if>&lt;%&ndash;有报销 并且报销状态大于1&ndash;%&gt;--%>
<%--                                        </c:if>--%>
<%--                                        <c:if test="${!dto.showExpenseReimbursementValue && !dto.showBaoSi}">--%>
<%--                                            <c:if test="${dto.surveyState == 1}">编辑</c:if>--%>
<%--                                            <c:if test="${dto.surveyState != 1}">查看</c:if>--%>
<%--                                        </c:if>--%>
                                        <c:if test="${dto.surveyState == 1 && (showNwPageEdit ||( dto.haveNwAccount == null || dto.haveNwAccount == 0))}">编辑</c:if>
                                        <c:if test="${dto.surveyState != 1}">查看</c:if>
                                    </a></p>
                                </c:if>
                                <c:if test="${dto.id == item.surveyInvestigatorCaseId && dto.surveyState == 1 && (showNwPageEdit ||( dto.haveNwAccount == null || dto.haveNwAccount == 0))}">
                                    <c:if test="${item.orgPoint != 1}">
                                        <a href="javascript:void(0);" onclick="delDirection(${dto.id},${item.id},'deldirection',true)">删除</a>
                                    </c:if>
                                </c:if>
                                <c:if test="${dto.zhongan && dto.creportDate == null && menuCode == 'dcy-list'}">
                                    <c:if test="${item.orgPoint == 0}">
                                        <a href="javascript:void(0);" onclick="oprDirection(${dto.id},${item.id},'signOrgPoint',true)">标记指定方向</a>
                                    </c:if>
                                    <c:if test="${item.orgPoint == 1}">
                                        <a href="javascript:void(0);" onclick="oprDirection(${dto.id},${item.id},'reOrgPoint',true)">取消指定方向</a>
                                    </c:if>
                                </c:if>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
    <c:if test="${menuCode == 'dcy-list'}">
        <c:if test="${surveyColleagueTaskDto.surveyInvestigatorCaseDto != null && surveyColleagueTaskDto.surveyInvestigatorCaseDto.size()>0}">
        <div class='task' style="background-color:rgb(238, 249, 240)">
            <div>
                <h4><strong>同事的任务</strong></h4>
                <table class="table">
                    <thead>
                    <tr>
                        <th  width="5%">调查员</th>
                        <th  width="35%">分配任务类型</th>
                        <th  width="5%">状态</th>
                        <th  width="5%">是否阳性</th>
                        <%--<th  width="20%">调查结论</th>
                        <th  width="10%">特殊情况说明</th>--%>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${surveyColleagueTaskDto.surveyInvestigatorCaseDto}" var="item">
                        <tr>
                            <th >${item.surveyUserName}</th>
                            <td>
                                <div class="value">
                                    <c:forEach items="${item.tasks}" var="items">
                                        <span class='colleagueType' style="background-color: ${items.taskColor};color: #FFFFFF">${items.taskName}</span>
                                    </c:forEach>
                                </div>
                            </td>
                            <td>${item.surveyStateName}</td>
                            <td><c:if test="${item.sunTime != null }">是</c:if><c:if test="${item.sunTime == null }">否</c:if></td>
                            <%--<td><c:if test="${item.surveySummary != null }">${item.surveySummary}</c:if><c:if test="${item.surveySummary == null }">无</c:if></td>
                            <td><c:if test="${item.specialRemark != null }">${item.specialRemark}</c:if><c:if test="${item.specialRemark == null }">无</c:if></td>--%>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        </c:if>
        <br>
        <c:if test="${surveyColleagueTaskDto.surveyCaseDirectionDto != null && surveyColleagueTaskDto.surveyCaseDirectionDto.size()>0}">
        <div class='task' style="background-color:rgba(238, 249, 240, 0.41)">
            <div>
                <h4><strong>同事的调查方向</strong></h4>
                <table class="table1" border="1" cellpadding="0" cellspacing="0" style="text-align: left;table-layout: fixed;">
                    <thead>
                    <tr align="center">
                        <th  width="5%">序号</th>
                        <th  width="7%">调查员</th>
                        <th  width="9%">区域</th>
                        <th width="8%">任务类型</th>
                        <th width="8%">任务子类</th>
                        <th  width="5%">任务结果</th>
                        <th  width="10%">方向名称</th>
                        <th width="15%">方向概述</th>
                        <th width="25%">方向内容</th>
                        <th width="8%">方向附件</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${surveyColleagueTaskDto.surveyCaseDirectionDto}" var="item" varStatus="status">
                        <tr>
                            <td scope="row" align="center">${status.count}</td>
                            <td scope="row" align="center">${item.surveyUserName}</td>
                            <td scope="row" align="center">${item.areaName}</td>
                            <td scope="row"><span class='colleagueTasks' style="background-color: ${item.taskColor};color: #FFFFFF;font-size: 15px">${item.taskName}</span></td>
                            <td scope="row"><span class='colleagueTasks' style="background-color: ${item.newColor};color: #FFFFFF;font-size: 15px">${item.newName}</span></td>
                            <td scope="row" align="center">${item.directionResultTypeName}</td>
                            <td scope="row" align="center">
                                <c:if test="${item.orgPoint != 1}">${item.directionName}</c:if>
                                <c:if test="${item.orgPoint == 1}"><span style="color: red;font-weight: bold">${item.directionName}</span></c:if>
                            </td>
                            <td scope="row"><pre style="background-color:rgba(0, 0, 0, 0);border:dashed 0px #fff; font-size: 14px;white-space: pre-wrap; word-wrap: break-word;">${item.huzhuColsRemark}</pre></td>
                            <td scope="row">${item.directionText}</td>
                            <td align="center"><a href="javascript:void(0);" onclick="directionFiles(${item.id},'directionFiles','${dto.surveyRiskCaseInfo.surveyCno}')">查看附件(${item.surveyCaseDirectionFilesSize})</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        </c:if>
    </c:if>
    <br>
    <%--案件基本信息--%>
    <div class="table-main">
        <div class="table-title">基础信息</div>
        <div class="stepTitle" style="justify-content: flex-end;">
            <div class="stepTitle-r"  style="cursor:pointer" onclick="operate(${dto.surveyRiskCaseInfo.id},'files',false)">查看材料(${dto.fileSize})</div>&nbsp;
            <c:if test="${menuCode != 'feeViewSurvey'}">
                <div class="stepTitle-r"  style="cursor:pointer" onclick="operate(${dto.surveyRiskCaseInfo.id},'survey-base-info-upd',false)">编 辑</div>
            </c:if>
            <input type="hidden" name="nullCode" value="${dto.nullCode}" id="nullCode">
        </div>
        <table class="info">
            <tbody>
            <tr>
                <td>案件编号：${dto.surveyRiskCase.surveyCaseNo}</td>
                <td>调查编号：${dto.surveyRiskCase.surveyNo}</td>
                <td>被调查人：${dto.surveyRiskCase.surveyPerson}<c:if test="${dto.surveyRiskCase.transferType != null}"><span style="color: red" >(${dto.surveyRiskCase.transferTypeName})</span></c:if></td>
                <td>联系号码：${dto.surveyRiskCase.surveryPersonTel}</td>
            </tr>
            <tr>
                <td>委托日期：<fmt:formatDate value="${dto.surveyRiskCase.entrustTime}" pattern="yyyy-MM-dd"/></td>
                <td>领域：${dto.surveyRiskCaseInfo.surveyBusName}</td>
                <td>业务类型：
                    <c:if test="${dto.surveyRiskCaseInfo.subServiceId ==null}">${dto.surveyRiskCaseInfo.servicesName}</c:if>
                    <c:if test="${dto.surveyRiskCaseInfo.subServiceId !=null}">
                        <c:if test="${dto.surveyRiskCaseInfo.subServiceId ==2}">核对调阅类</c:if>
                        <c:if test="${dto.surveyRiskCaseInfo.subServiceId ==3}">一般检索类</c:if>
                        <c:if test="${dto.surveyRiskCaseInfo.subServiceId ==4}">特殊检索类</c:if>
                        <c:if test="${dto.surveyRiskCaseInfo.subServiceId ==5}">疑难侦察类</c:if>
                        <c:if test="${dto.surveyRiskCaseInfo.subServiceId ==6}">攻坚侦察类</c:if>
                    </c:if>
                </td>
                <td>理赔申请金额：${dto.surveyRiskCase.claimsMoney} <c:if test="${dto.surveyRiskCase.claimsMoney !=null}">元</c:if> </td>
            </tr>
            <tr>
                <td>案件状态：${dto.surveyRiskCaseInfo.surveyStateName}
                    <%--<c:if test="${dto.surveyUserType == 1 && dto.surveyState != 0 && dto.surveyState != 5}">--%>
                    <%--${dto.surveyRiskCaseInfo.surveyStateName}--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${dto.surveyUserType == 1 && (dto.surveyState == 0 || dto.surveyState == 5)}">--%>
                    <%--${dto.surveyStateName}--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${dto.surveyUserType == 2}">--%>
                    <%--${dto.surveyStateName}--%>
                    <%--</c:if>--%>
                </td>

                <c:if test="${dto.surveyRiskCase.modelId ==4 || dto.surveyRiskCase.modelId ==3}">
                    <td>保险合同编号：${dto.surveyRiskCase.policyNo}</td>
                </c:if>
                <c:if test="${dto.surveyRiskCase.modelId ==1 || dto.surveyRiskCase.modelId ==2  || dto.surveyRiskCase.modelId == 6 || dto.surveyRiskCase.modelId == 7}">
                    <td>理赔编号：${dto.surveyRiskCase.claimsNo}</td>
                </c:if>

                <td>委托人机构名称：${dto.surveyRiskCase.entrustOrgName}</td>

                <c:if test="${dto.surveyRiskCase.modelId ==4}">
                    <td>委托人机构分公司：${dto.surveyRiskCase.departmentName}</td>
                </c:if>
                <c:if test="${dto.surveyRiskCase.modelId ==1 || dto.surveyRiskCase.modelId ==2  || dto.surveyRiskCase.modelId == 6 || dto.surveyRiskCase.modelId == 7}"></td>
                <td>保险种类：${dto.surveyRiskCase.insureName}
                    </c:if>
                    <c:if test="${dto.surveyRiskCase.modelId ==3}">
                <td>保单生效日：<fmt:formatDate value="${dto.surveyRiskCase.insureTakeTime}" pattern="yyyy-MM-dd"/></td>
                </c:if>
                <c:if test="${dto.surveyRiskCase.modelId ==5}">
                    <td>性别：
                        <c:if test="${dto.surveyRiskCase.sex ==1}">男</c:if>
                        <c:if test="${dto.surveyRiskCase.sex ==2}">女</c:if>
                    </td>
                    <td>年龄：${dto.surveyRiskCase.age} 周岁</td>
                </c:if>

            </tr>
            <tr>
                <c:if test="${dto.surveyRiskCase.modelId ==4}">
                <td colspan="8">委托人：${dto.surveyRiskCase.entrustUserName}
                    </c:if>
                    <c:if test="${dto.surveyRiskCase.modelId ==1 || dto.surveyRiskCase.modelId ==2  || dto.surveyRiskCase.modelId == 6 || dto.surveyRiskCase.modelId == 7}">
                    <c:if test="${dto.surveyRiskCaseInfo.servicesId != 11}">
                <td>投保日期：<fmt:formatDate value="${dto.surveyRiskCase.insureTime}" pattern="yyyy-MM-dd"/></td>
                <td>出险日期：<fmt:formatDate value="${dto.surveyRiskCase.dangerTime}" pattern="yyyy-MM-dd"/></td>
                <td>出险地点：${dto.surveyRiskCase.dangerAddress}</td>
                </c:if>
                <td>身份证号：${dto.surveyRiskCase.idNumber}</td>
                </c:if>
                <c:if test="${dto.surveyRiskCase.modelId ==3}">
                    <td>出险时间：<fmt:formatDate value="${dto.surveyRiskCase.dangerTime}" pattern="yyyy-MM-dd"/></td>
                    <td>身份证号：${dto.surveyRiskCase.idNumber}</td>
                </c:if>
            </tr>
            <c:if test="${dto.surveyRiskCase.modelId ==1 || dto.surveyRiskCase.modelId ==2  || dto.surveyRiskCase.modelId == 6 || dto.surveyRiskCase.modelId == 7}">
            <tr>
                <td>性别：
                    <c:if test="${dto.surveyRiskCase.sex ==1}">男</c:if>
                    <c:if test="${dto.surveyRiskCase.sex ==2}">女</c:if>
                </td>
                <td>年龄：${dto.surveyRiskCase.age} 周岁</td>
                <td>是否阳性：<c:if test="${dto.isSun == 0}">否</c:if>
                    <c:if test="${dto.isSun == 1}">是</c:if>
                </td>
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
                <td>加入时间：<fmt:formatDate value="${dto.surveyRiskCase.insureTime}" pattern="yyyy-MM-dd"/></td>
                <td>等待期截止日期：<fmt:formatDate value="${dto.surveyRiskCase.hzWaitEndTime}" pattern="yyyy-MM-dd"/></td>
                <td>出险日期：<fmt:formatDate value="${dto.surveyRiskCase.dangerTime}" pattern="yyyy-MM-dd"/></td>
            </tr>
            <tr>
                <td>常住地点：${dto.surveyRiskCase.hzLiveAddress}</td>
                <td>出险地点：${dto.surveyRiskCase.dangerAddress}</td>
                <td>确诊疾病：${dto.surveyRiskCase.hzConfirmDisease}</td>
                <td>首次跟踪信息:${dto.surveyRiskCase.hzFollowInfo}</td>
            </tr>
            </c:if>

            <tr>
                <td colspan="8">案件基本信息：${dto.surveyRiskCaseInfo.surveyInfo}</td>
            </tr>
        </table>
    </div>

    <c:if test="${dto.surveyRiskCaseInfo.guide ==1}">
        <div class="table-main" style="display: none">
            <div class="table-title">案件指导</div>
            <table class="info">
                <tbody>
                <tr>
                    <td colspan="8" style="color:red">可能的阳性点：${guideDto.maySun}</td>
                </tr>
                <tr>
                    <td colspan="8" style="color:red">重点调查方向：${guideDto.direction}</td>
                </tr>
                <tr>
                    <td colspan="8" style="color:red">注意事项：${guideDto.note}</td>
                </tr>
                <tr>
                    <td colspan="2" style="color:red">高度阳性：
                        <c:if test="${guideDto.isSun==0 || guideDto.isSun==''}">否</c:if>
                        <c:if test="${guideDto.isSun==1}">是</c:if>
                    </td>
                    <td colspan="2" style="color:red">指导人：${guideDto.guideByName}</td>
                    <td colspan="4" style="color:red">指导时间：<fmt:formatDate value="${guideDto.guideTime}" pattern="yyyy-MM-dd"/></td>
                </tr>
            </table>
        </div>
    </c:if>

    <%--协助调查信息--%>
<%--    <div class='caseInfo' <c:if test="${menuCode == 'feeViewSurvey'}">style="display: none" </c:if>>--%>
<%--        <div class="info1">--%>
<%--            <div class="title">--%>
<%--                <span class="text1" >协助调查信息</span>--%>
<%--                <span class="text2" onclick="operate(${dto.id},'appHelpCase',false)">申请协助调查</span>--%>
<%--            </div>--%>
<%--            <c:if test="${dto.surveyBackCases.size() > 0}">--%>
<%--                <div class="table">--%>
<%--                    <div class="tr thead">--%>
<%--                        <div class="td">申请描述</div>--%>
<%--                        <div class="td wid">状态</div>--%>
<%--                        <div class="td wid">分派状态</div>--%>
<%--                        <div class="td">审核原因</div>--%>
<%--                    </div>--%>
<%--                    <div class="tbody">--%>
<%--                        <c:forEach items="${dto.surveyBackCases}" var="item">--%>
<%--                            <div class="tr">--%>
<%--                                <div class="td">${item.backRemark}</div>--%>
<%--                                <div class="td wid">${item.backStateName}</div>--%>
<%--                                <div class="td wid">--%>
<%--                                    <c:if test="${item.backState == 1}">未分派</c:if>--%>
<%--                                    <c:if test="${item.backState == 2}">机构已分派${item.assSurveyUserName}</c:if>--%>
<%--                                    <c:if test="${item.backState == 3}">未分派 </c:if>--%>
<%--                                    <c:if test="${item.backState == 4}">未分派</c:if>--%>
<%--                                    <c:if test="${item.backState == 5}">平台已分派${item.assOrgName}</c:if>--%>
<%--                                    <c:if test="${item.backState == 6}">未分派</c:if>--%>
<%--                                </div>--%>
<%--                                <div class="td">${item.opinion}</div>--%>
<%--                            </div>--%>
<%--                        </c:forEach>--%>

<%--                    </div>--%>
<%--                </div>--%>
<%--            </c:if>--%>
<%--            <c:if test="${dto.surveyBackCases.size()==0}">--%>
<%--                <div style="text-align: center; height: 100px;line-height: 100px;">暂无协助调查记录</div>--%>
<%--            </c:if>--%>
<%--        </div>--%>

<%--        <div class="info2">--%>
<%--            <div class="i_title">--%>
<%--                <span class="text1">跟踪信息</span>--%>
<%--                <span class="text2" onclick="operate(${dto.surveyRiskCaseInfo.id},'follow',false)">添加跟踪</span>--%>
<%--            </div>--%>
<%--            <div class="bars">--%>
<%--                <c:if test="${follows.size() > 0}">--%>
<%--                    <c:forEach items="${follows}" var="item" varStatus="index">--%>
<%--                        <div <c:if test="${index.index == 0}">class="bar"</c:if>  <c:if test="${index.index > 0}">class="bar b_hide"</c:if>>--%>
<%--                            <div class="bar-date"><fmt:formatDate value="${item.followTime}" pattern="yyyy-MM-dd HH:mm"/></div>--%>
<%--                            <div class="bar-contain">--%>
<%--                                <div class="title">--%>
<%--                                    <div class="index"></div>--%>
<%--                                    <div class="text">跟踪人：${item.followUserName}</div>--%>
<%--                                </div>--%>
<%--                                <div class="content">--%>
<%--                                    <div class="text text2">内容：${item.contents}</div>--%>
<%--                                    <div class="text">下一个跟踪时间：<fmt:formatDate value="${item.nextFollowTime}" pattern="yyyy-MM-dd HH:mm"/></div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </c:forEach>--%>
<%--                    <div class="bar bar-btn">--%>
<%--                        <div class="bar-date"></div>--%>
<%--                        <div class="bar-contain">--%>
<%--                            <div class="title">--%>
<%--                                <div class="index index-btn"></div>--%>
<%--                                <div class="text-btn">查看全部${follows.size()}条跟踪</div>--%>
<%--                            </div>--%>
<%--                            <div class="content">--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </c:if>--%>
<%--                <c:if test="${follows.size() == 0}">--%>
<%--                    <div style="text-align: center;height: 100px;line-height: 100px;">暂无跟踪信息</div>--%>
<%--                </c:if>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
    <div class="dalogs" id="dalogs">
        <div class="close" onclick="hidDirectionView()"></div>
        <div class="d_content" style="padding: 20px;"></div>
    </div>
    <div class="dalogsHuzhu" id="dalogsHuzhu">
        <div class="huzhu-close" onclick="huzhuCommit(2)">
            <div class="close1"></div>
        </div>
        <div class="dalogsHuzhu-content">
            <form id="huzhuForm" role="form" action="${ctx}/survey/case/sic/operate" method="post">
                <input type="hidden" value="${dto.id}" name="id" />
                <input type="hidden" value="huzhuCommit" name="btnCode" />
                <div class="huzhu-table"></div>
                <div class="table-title1" style="padding: 0px 2%;">调查结论</div>
                <textarea class="form-control" rows="6" name="surveySummary" id="surveySummary"></textarea>
                <div class="table-title1" style="padding: 0px 2%;">特殊情况说明</div>
                <textarea class="form-control" rows="6" name="specialRemark"></textarea>
                <div class="dalogsHuzhu-btns">
                    <div class="dalogsHuzhu-btn" onclick="huzhuCommit(1)">提交</div>
                    <div class="dalogsHuzhu-btn dalogsHuzhu-btn0" onclick="huzhuCommit(2)">关闭</div>
                </div>
            </form>
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
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
</body>

<script>


    $("#editForm").bind('submit', function(event) {
        ajaxFormSubmit(this,reload,null,null,reload);
        event.preventDefault();
    });

    var sun = function(id,btnCode,signType,ajax){
        if(ajax){
            var url = "${ctx}/survey/case/sic/operate",param = {"id":id,"btnCode":btnCode,"signType":signType};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    location.reload();
                })
            }
        }
    }

    var operate = function(id,btnCode,ajax,valid){
        var f = true;
        if (valid && $("#zhongan").val() == 'true'){
            //验证我的调查方向 内容-子类以及结果是否都已填写
            var url = "${ctx}/survey/case/sic/operate",param = {"id":$("#surveyCaseId").val(),"btnCode":"valid"};
            ajaxSubmit(url,param,function(v,e,p){
                var results = e.data.results;
                if (!results){
                    f = false;
                    alert("请完整填写【我的调查方向】相关信息（方向内容、任务子类及结果）"); return;
                }else{
                    operate1(id,btnCode,ajax);
                }
            })
        }else{
            operate1(id,btnCode,ajax)
        }
    }
    var operate1 = function (id,btnCode,ajax){
        var height = $(parent.document).outerHeight()-90;
        var width = $(document.body).outerWidth() - 100;
        if(btnCode == 'commit' || btnCode == 'report-commit'){
            //不管是否是特殊机构 提交初审都可以不上传报告        报告不上传也可以提交
            <%--if(btnCode == 'report-commit'){--%>
            <%--if("${dto.creportState}" == 0 && "${dto.surveyFranchisee.isSpecial}" == 0){--%>
            <%--alert("报告未上传");return;--%>
            <%--}--%>
            <%--}--%>
        }
        if(ajax){
            var url = "${ctx}/survey/case/sic/operate",param = {"id":id,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    if(btnCode != 'accept'){
                        alert(e.data.msg);
                    }
                    location.reload();
                })
            }
        }
        else{
            var title = null,url = null;
            if(btnCode == 'follow'){
                title = "添加跟踪";
                url = "${ctx}/survey/case/addFollow?id=" + id + "&btnCode=" + btnCode + "&surveyCno=${dto.surveyRiskCaseInfo.surveyCno}";
            }else if(btnCode == 'files'){
                title = "查看资料";
                url = "${ctx}/survey/case/fileMidView?surveyInfoId=" + id + "&surveyId=${dto.surveyRiskCaseInfo.surveyId}&surveyCno=${dto.surveyRiskCaseInfo.surveyCno}";
            }else if(btnCode == 'details'){
                title = "任务详情"
                url = "${ctx}/survey/case/sic/details?id=" + id + "&btnCode=" + btnCode;
            }else if(btnCode == 'refuse' || btnCode == 'back' || btnCode == 'back1' || btnCode == 'refuseNo' || btnCode == 'appHelpCase' || btnCode == 'backPrimary'){
                title = '拒绝原因';
                if(btnCode == 'refuseNo'){
                    title = '无效原因'
                }
                if(btnCode == 'appHelpCase'){
                    title = '协助调查'
                }
                url = "${ctx}/survey/case/sic/back?id=" + id + "&btnCode=" + btnCode;
            }
                <%--else if(btnCode == 'direction'){--%>
                <%--height = 600,width=900;--%>
                <%--title = "新增调查方向"--%>
                <%--url = "${ctx}//baseSurvey/popup?id=" + id + "&surveyCode=investigatorCase&btnCode="+btnCode ;--%>
                <%--}--%>
            else if(btnCode == 'direction'){
                var directionName = $("#directionName").val().trim();
                if(directionName ==null || directionName ==""){
                    alert("请填写方向名称");return;
                }
                //验证方向名称是否存在
                var url = "${ctx}/survey/case/operate",param = {
                    "btnCode" : "validateDirectionName",
                    "id" : $("#surveyInfoId").val(),
                    "directionName" : directionName,
                    "directionId" : $("#directionId").val()
                };
                ajaxSubmit(url,param,function(v,e,p){
                    if(e.data.code==='0000'){
                        var results = e.data.results;
                        if(results){
                            if (results.length > 0){
                                alert("方向名称已存在");return;
                            }
                        }
                    }
                })

                height = $(parent.document).outerHeight()-90;
                width= $(document.body).outerWidth();
                title = ""
                var again = $("#again").val();
                url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode="+btnCode+ "&directionName="+directionName +"&menuCode="+'${menuCode}'+"&again="+again;
            }
            else if(btnCode =='survey-base-info-upd'){
                var surveyInvestigatorCaseId=${dto.id};
                title = "编辑",
                    url = "${ctx}/survey/case/info?id=" + id + "&menuCode=${menuCode}"+"&btnCode="+btnCode+"&surveyInvestigatorCaseId="+surveyInvestigatorCaseId;
                height = $(parent.document).outerHeight() - 20
                width= $(document.body).outerWidth();
            }
            else if(btnCode == 'case-return'){
                title = "退回",
                    url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode="+btnCode +"&roleCode=survey";
            }else if(btnCode == 'huzhuCommit'){
                $("#dalogsHuzhu .dalogsHuzhu-content .huzhu-table").html("");
                $(".task1").clone().prependTo("#dalogsHuzhu .dalogsHuzhu-content .huzhu-table");
                $("#dalogsHuzhu").show();
                $(".contain").css("pointer-events","none");
                return;
            }
            else{
                //上传报告 更新报告 增加方向 等操作
                title = "操作";
                url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode;
                if(btnCode == 'upload' || btnCode == 'primary'){
                    url += "&surveyCno=${dto.surveyRiskCaseInfo.surveyCno}"
                }
                if (btnCode == 'last-org-report-completion' || btnCode == 'last-org-summary' || btnCode == 'tasks' || btnCode == 'org-commit' || btnCode == 'last-org-summary-view' || btnCode == 'last-org-report-completion-view'){
                    if (btnCode == 'last-org-report-completion'){
                        width = $(document.body).outerWidth();
                        height = window.parent.outerHeight -50-27-31-20
                        console.log( window.parent.outerHeight -50-27-31-20)
                        var nullCode = $("#nullCode").val();
                        if (nullCode){
                            // var f = confirm("基础信息未完成，补充基础信息？");
                            // if (f){
                            //     var surveyInfoId = $("#surveyInfoId").val();
                            //     update(surveyInfoId);
                            // }
                            // return false;
                        }
                    }
                    url += "&surveyInfoId=${dto.surveyRiskCaseInfo.id}&surveyInvestigatorCaseId=${dto.id}&nullCode=${dto.nullCode}";
                    if (btnCode == 'last-org-summary-view' || btnCode == 'last-org-report-completion-view'){
                        url += "&view=view"
                    }
                }
            }
            console.log("tttttt",width,height)
            openDialog({
                frame:true,
                title:title,
                height:height,
                width:width,
                url:url,
                load:true
            });
        }
    }

    var caseClockDetails = function(id,btnCode){
        var url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=" + btnCode
        parent.parent.addTab("案件打卡足迹",url,true);
    }

    var huzhuCommit = function(type){
        if(type == 1){
            var surveySummary = $("#surveySummary").val();
            if(!surveySummary){
                alert("调查结论必填");
                return false;
            }
            $(".dalogsHuzhu-btn").addClass("po-none");
            $("#huzhuForm").submit();
        }else if (type == 2){
            $("#dalogsHuzhu").hide();
            $(".contain").css("pointer-events","auto");
        }
    }

    $("#huzhuForm").bind('submit', function(event) {
        ajaxFormSubmit(this,reload,null,null,function(event,param){
            var apiRsp=getApiJson(param.data);
            if(apiRsp && apiRsp.isSuccess){

            }else{
                alert(apiRsp.msg);return;
            }
        });
        event.preventDefault();
    });

    var addDirection = function(id,btnCode,ajax){
        var directionName = $("#directionName").val().trim();
        if(directionName ==null || directionName ==""){
            alert("请填写方向名称");return;
        }
        //验证方向名称是否存在
        var url = "${ctx}/survey/case/operate",param = {
            "btnCode" : "validateDirectionName",
            "id" : $("#surveyInfoId").val(),
            "directionName" : directionName,
            "directionId" : $("#directionId").val()
        };
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var results = e.data.results;
                if(results){
                    if (results.length > 0){
                        alert("方向名称已存在");return;
                    }
                }

                height = $(parent.document).outerHeight()-90;
                width= $(document.body).outerWidth();
                title = ""
                var again = $("#again").val();
                url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode="+btnCode+ "&directionName="+directionName +"&menuCode="+'${menuCode}'+"&again="+again+'&openType=addNew';
                openDialog({
                    frame:true,
                    title:title,
                    height:height,
                    width:width,
                    url:url,
                    load:true
                });
            }
        })

    }
    var addDirectionNew = function(id,btnCode,ajax){
        var height = $(parent.document).outerHeight()-80;
        var width = $(document.body).outerWidth();
        var title = "";
        var again = $("#again").val();
        var url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode="+btnCode+"&menuCode="+'${menuCode}'+"&again="+again+'&openType=addNew';
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:url
//            load:true
        });
    }
    var delDirection = function(id,directionId,btnCode,ajax){
        var url = "${ctx}/survey/case/sic/operate",param = {"id":id,"directionId" : directionId,"btnCode":btnCode};
        if(confirm('是否确认？')){
            ajaxSubmit(url,param,function(v,e,p){
//                alert(e.data.msg);
                location.reload();
            })
        }
    }
    var oprDirection = function(id,directionId,btnCode,ajax){
        var url = "${ctx}/survey/case/sic/operate",param = {"id":id,"directionId" : directionId,"btnCode":btnCode};
        if(confirm('是否确认？')){
            ajaxSubmit(url,param,function(v,e,p){
                location.reload();
            })
        }
    }


    var updDirection = function(id,directionId,btnCode,ajax){
        var height = $(parent.document).outerHeight()-80;
        var width = $(document.body).outerWidth();
        var title = "";
        var url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=direction&opr=upd&directionId=" + directionId+"&menuCode="+'${menuCode}';
        if (btnCode == 'feeUpdDirection'){
            var feeOpr = $("#feeOpr").val();
            url = "${ctx}/survey/case/sic/operateView?id=" + id + "&btnCode=direction&opr=upd&directionId=" + directionId+"&menuCode="+'${menuCode}&feeOpr=' + feeOpr;
        }
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:url
        });
    }

    var directionFiles = function(id,btnCode,surveyCno){
        var width= $(document.body).outerWidth();
        var height = $(parent.document).outerHeight()-90
        console.log('---',height)
        var title = "附件详情";
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:"${ctx}/survey/case/sic/directionFiles?directionId=" + id + "&surveyCno=" + surveyCno
        });

    }

    function showDirectionView(type,obj){
        var dialog = $(".dalogs");
        dialog.show();
//        var position = $(obj).position();以按钮为定位
        var position = $("#title").position(); //以id=“title”为定位
        $(".dalogs").offset({
            left: position.left + 420,
            top: position.top + 50

        });
        var html ="";
        var id = "${dto.id}";

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
        html += '<div class="c-btn c-btn2" type="submit" onclick="return addDirection('+id+',\'direction\',false)" style="cursor:pointer">确定</div>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
//        html += '<h3>请输入方向名称</h3>';
//
//        html += '<input type="text" id="directionName" name="directionName" value="">';
//        html += '<button type="button" onclick="javascript:hidDirectionView();">关闭</button>';
//
//        //两种传值方式
////        var html = "<button type='submit' onclick='return operate(" + id +",\"" + btnCode + "\",false)' value='提交'/>";
//        html += '<button type="submit" onclick="return operate('+id+',\'direction\',false)" ><span class="glyphicon glyphicon-ok"></span>提交</button>';
        $(".d_content").html(html);
        showInput();
    }

    function hidDirectionView(){
        location.href = "${ctx}/survey/case/sic/info?id=" + ${dto.id} + "&menuCode=dcy-list&display=true";
//        reload();
        var dialog = $(".dalogs");
        dialog.hide();
    }

    $(function () {
        $('.text-btn').on('click', function (){
            $('.bar-btn').hide()
            $('.b_hide').css({
                'display': 'flex'
            })
            $('.bars .text').removeClass("text2");
        })
        var _height = $('.bar').first().height()
        $('.bars').height(_height + 100);
        $('.info1 .tbody').height(_height);
    })

    var update = function(id){
        var height = $(document).outerHeight() - 20;
        var width = $(document.body).outerWidth();
        openDialog({
            frame:true,
            title:"详情",
            height:height,
            width:width-100,
            url:"${ctx}/survey/case/info?id=" + id + "&btnCode=survey-base-info-upd&menuCode=${menuCode}",
            load:true
        });
    }

    initShow();
    function initShow(){
        var again = sessionStorage.getItem("again")
        console.log("again----------"+again);
        if(again!=null && again==1){
            sessionStorage.setItem("again","");
            $("#initOpen").click();
        }
    }

    function showInput(){
        setTimeout("document.getElementById(\"directionName\").focus()",50);
    }

    $(function(){
        $("input:button").click(function() {
            pwd = $("<input type='password'>");     // 创建的input对象
            $("ul li:last").append(pwd);                        // 将创建的input添加到相应的位置
            $("ul li:last input").focus();                        // 获取新添加的input，使用focus()设置焦点
        });

        var userId = $('#nuanWaUser').val() || '',
            company = $('#nuanWaCompany').val() || '',
            sign = $('#nuanWaSign').val() || '',
            redirectUrl = '/insurance/offlineInvestigateTaskPool/detail',
            handleId = $('#handleId').val() || ''
        var rUrl = redirectUrl+'?handleId='+handleId
        // var _url = 'https://open-mdp-test.nuanwa.net/#/t/authorize?user='+userId+'&company='+company+'&sign='+sign+'&redirect='+encodeURIComponent(rUrl)
        var _url = 'https://open-mdp.nuanwa.net/#/t/authorize?user='+userId+'&company='+company+'&sign='+sign+'&redirect='+encodeURIComponent(rUrl)
        if ($('#iframeNW').length){
            $('#iframeNW').attr('src',_url )
            var _height = $(parent.document).height() - 70
            $('.iframe-content').css({
                height: _height+'px'
            })
            $('#iframeNW').css({
                height: _height+'px'
            })
        }
        $('.synchrodata').click(function () {
            $('.loading-bar').show()
            var _this = $(this)
            _this.addClass('poi-no')
            setTimeout(function () {
                _this.removeClass('poi-no')
            },3000)
            $.ajax({
                url: '${ctx}/survey/case/asyncNwCase',
                type: 'post',
                data: {
                    riskInfoId:$('#surveyInfoId').val()
                },
                success: function (data,e) {
                    $('.loading-bar').hide()
                    if (e.data.isSuccess){
                        location.reload();
                    }else {
                        $('.loading-bar').hide()
                        alert(e.data.results)
                    }
                }
            })
        })
    });

    var loadTaskResult = function (directionId){
        var newId = $("#sel_new_task_" +directionId).val();
        //验证方向名称是否存在
        var url = "${ctx}/survey/case/operate",param = {
            "btnCode" : "getTaskResults",
            newId : newId
        };
        ajaxSubmit(url,param,function(v,e,p){
            if(e.data.code==='0000'){
                var results = e.data.results;
                if(results){
                    $("#sel_task_result_" + directionId).empty();
                    $("#sel_task_result_" + directionId).append("<option></option>")
                    var sel = results.length == 1 ? true : false;
                    for(var i = 0 ; i< results.length ; i ++){
                        if (sel){
                            $("#sel_task_result_" + directionId).append("<option selected value='"+results[i].directionResultTypeId+"'>"+results[i].directionResultTypeName+"</option>")
                            refreshDirection(directionId);
                        }else{
                            $("#sel_task_result_" + directionId).append("<option value='"+results[i].directionResultTypeId+"'>"+results[i].directionResultTypeName+"</option>")
                        }
                    }
                }
            }
        })
    }
    var refreshDirection = function(directionId){
        var newId = $("#sel_new_task_" +directionId).val();
        var directionResultTypeId = $("#sel_task_result_" +directionId).val();
        var url = "${ctx}/survey/case/sic/operate",param = {"id":$("#surveyCaseId").val(),
                                "directionId" : directionId,
                                "btnCode":"selDirectionResult",
                                "newId" : newId,
                                "directionResultTypeId" : directionResultTypeId
        };
        ajaxSubmit(url,param,function(v,e,p){
            location.reload();
        })
    }


</script>

</html>