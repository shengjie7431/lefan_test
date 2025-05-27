<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

<!DOCTYPE html>
<html>

<head>
    <title>报告审核详情页面</title>
    <style type="text/css">
        * {
            color: #000;
        }

        .contain {
            display: flex;
            width: 100%;
            height: 100%;
            min-height: 100%;
            overflow: hidden;
        }

        .body-left {
            display: none;
            width: 49%;
            min-width: 49%;
            height: 100%!important;
            min-height: 100%;
            overflow: auto;
            border-right: 1px solid #bbb;
            padding-bottom: 0px!important;
        }

        .body-left-par {
            width: 49%;
            min-width: 49%;
            height: 98%;
            min-height: 100%;
            overflow: auto;
            border-right: 1px solid #bbb;
            padding-bottom: 2%;
        }

        .body-left-detail {
            display: none;
            width: 49%;
            min-width: 49%;
            height: 98%;
            min-height: 100%;
            overflow: auto;
            border-right: 1px solid #bbb;
            padding-bottom: 2%;
        }

        .body-right {
            width: 100%;
            min-width: 50%;
            height: 100%!important;
            overflow: auto;
            /* padding-bottom: 2%; */

        }

        .body-left .title {
            padding: 10px 0;
            margin: 0 4%;
            width: 92%;
            /* border-bottom: 1px solid #bbb; */
            font-size: 17px;
            color: #333;
            text-align: left;
        }

        .body-left .title span {
            font-size: 16px;
        }
        .body-left .title span:hover {
            font-weight: bold;
        }

        .body-left .files {
            padding: 10px 0;
            margin: 0 4%;
            width: 92%;
            display: flex;
            flex-wrap: wrap;
        }

        .body-left .files .file {
            width: 148px;
            height: 148px;
            border: 1px solid #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;

        }

        .body-left .files .file .image {
            width: 100%;
            height: 100%;
            display: block;
            margin: 0 auto;

        }

        .body-left .files div.file-add {
            width: 148px;
            height: 148px;
            border: 1px dashed #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;
        }

        .icon-add {
            margin-left: 74px;
            margin-top: 34px;
            display: inline-block;
            background: #aaa;
            height: 80px;
            position: relative;
            width: 4px;
        }

        .icon-add:after {
            background: #aaa;
            content: "";
            height: 80px;
            left: 0;
            position: absolute;
            top: 0;
            width: 4px;
            transform: rotateZ(90deg);
        }

        .body-right .title {
            padding: 20px 0;
            margin: 0 4%;
            width: 92%;
            border-bottom: 1px solid #bbb;
            font-size: 20px;
            color: #333;
            text-align: left;
        }

        .body-right .form {
            position: relative;
            padding: 10px 0;
            margin: 0 4%;
            width: 92%;
        }

        .body-right .cell {
            display: flex;
            padding: 6px 0;
        }

        .body-right div.wid50 {

            width: 60%;
        }

        .body-right .cell img {
            display: block;
            width: 300px;
            margin: 30px auto;
        }

        .body-right .cell .img-text {
            width: 300px;
            margin: 0 auto;
            margin-bottom: 20px;
            font-size: 30px;
            font-weight: 500;
            text-align: center;
            /* font-family: "KaiTi_GB2312"!important; */
        }

        .body-right .cell .label {
            padding: 0px;
            padding-right: 1%;
            /* width: 13%; */
            /* min-width: 70px; */
            height: 36px;
            line-height: 36px;
            white-space: nowrap;
            color: #000;
            font-size: 16px;
        }

        .body-right .cell .value {
            width: 86%;
            position: relative;
            font-size: 15px;
        }

        .body-right .cell div.value-text-s {
            width: 100%;
            position: relative;
        }

        .body-right .cell .value .value-text {
            display: inline-block;
            width: 100%;
            /*height: 20px;*/
            line-height: 20px;
            padding: 8px 0;
            border: none;
            color: #666;
        }

        .body-right .cell  div.value-sign .value-text {
            display: inline-block;
            line-height: 20px;
            padding: 8px 0;
            border: none;
            color: #666;
        }
        .body-right .cell .value div.value-text-date{
            /*border: 1px solid #d0c9c9;*/
            height: 36px;
            line-height: 36px;
            padding: 0;
        }

        .body-right .cell .value2 .blod{
            font-weight: 600;
        }

        .body-right .cell div.inline {
            word-wrap: break-word;
            word-break: break-all;
        }

        .body-right .cell div.inline .value-text {
            display: inline-block;
            width: auto;
            height: 20px;
            line-height: 20px;
            padding: 8px 0;
            border: none;
            color: #666;
        }

        .body-right .cell div.value-text-s .value-text {
            width: 100%;
            padding: 8px 0;
        }

        .body-right .cell .value input.value-box {
            display: none;
            width: 94%;
            /* height: 0px; */
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }
        .body-right .cell .value-sign input.value-box {
            display: none;
            width: 94%;
            /* height: 0px; */
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right .cell .value textarea.value-box {
            display: none;
            width: 94%;
            /* height: 0px; */
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right .cell .value select.value-box {
            display: none;
            /*width: 94%;*/
            /*!* height: 0px; *!*/
            /*line-height: 20px;*/
            /*padding: 8px 3%;*/
            /*border: 1px solid #d0c9c9;*/
            /*color: #666;*/
        }

        .body-right .cell .list {
            /* position: absolute; */
            width: 98%;
            height: 120px;
            overflow: auto;
            padding: 10px 1%;
            border: 1px solid #d0c9c9;
            background-color: #fff;
            z-index: 1000;

        }

        .body-right .cell .list .li {
            width: 92%;
            padding: 3px 4%;
            height: 20px;
            line-height: 20px;
        }

        .body-right .cell .value .btns {
            width: 100%;
            display: flex;
            flex-wrap: wrap;
            align-items: center;
        }

        .body-right .cell .value .btns .btn {
            margin-bottom: 10px;
            margin-right: 10px;
            padding: 3px 5px;
            width: 118px;
            height: 40px;
            line-height: 40px;
            color: #101010;
            border: 1px solid #d0c9c9;
            text-align: center;
            font-size: 14px;
            word-wrap: break-word;
            word-break: normal;

        }

        .body-right .cell .value .btns .btn span {
            display: block;
            width: 100%;
            height: 20px;
            line-height: 20px;
            color: #101010;
        }

        .body-right .cell .value .btns div.active {
            color: #fff;
            background-color: #3FB5FD;
            border: 1px solid #3FB5FD;
        }

        .body-right .cell .value .btns div.active span {
            color: #fff;
            background-color: #3FB5FD;
        }

        .body-right .cell .value .texts {
            width: 100%;
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            border: 1px solid #d0c9c9;
        }

        .body-right .cell .value .texts .text {
            padding: 6px 3%;
            width: 94%;
            line-height: 20px;
            font-size: 14px;
        }

        .body-right .form-btns {
            width: 100%;
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            justify-content: center;
        }

        .body-right .form-btn {
            margin-bottom: 10px;
            margin-right: 10px;
            padding: 3px 5px;
            width: 158px;
            height: 40px;
            line-height: 40px;
            color: #3FB5FD;
            border: 1px solid #3FB5FD;
            text-align: center;
            font-size: 14px;
            word-wrap: break-word;
            word-break: normal;

        }

        .body-right .form-btns div.active {
            color: #fff;
            background-color: #3FB5FD;
            border: 1px solid #3FB5FD;
        }

        .body-left-detail .img-detail {
            width: 98%;
            height: 80%;
            position: relative;
            /* float: left; */
            display: inline-block;
            padding-top: 40px;
        }

        .body-left-detail .img-detail .img-src {
            width: 100%;
            height: 100%;
            overflow: auto;
            border: 1px solid #d0c9c9;
        }

        .body-left-detail .img-detail .img-src img {
            width: 100%;
            height: 100%;
            display: block;
            margin: 0 auto;
        }

        .body-left-detail .img-pre {
            /* float: left; */
            position: absolute;
            left: 1%;
            top: 0;
            display: inline-block;
            width: 10%;
            height: 100%;
            background: url('${ctx}/img/report/icon-left.png');
            background-position: center center;
            background-repeat: no-repeat;
            background-size: contain;
            cursor: pointer;
            opacity: 0.6;
        }

        .body-left-detail .img-pre:hover {
            opacity: 1;
        }

        .body-left-detail .img-next:hover {
            opacity: 1;
        }

        .body-left-detail .img-next {
            /* float: left; */
            position: absolute;
            right: 1%;
            top: 0;
            display: inline-block;
            width: 10%;
            height: 100%;
            background: url('${ctx}/img/report/icon-right.png');
            background-position: center center;
            background-repeat: no-repeat;
            background-size: contain;
            cursor: pointer;
            opacity: 0.6;
        }

        .body-left-detail .img-btns {
            position: absolute;
            bottom: 30px;
            width: 90%;
            min-width: 404px;
            left: 5%;
            display: flex;
            align-items: center;
            justify-content: space-around;
            background-color: rgba(0, 0, 0, 0.5);
            border-radius: 4px;
        }

        .body-left-detail .img-btns .img-btn {
            /* width: 130px; */
            height: 40px;
            line-height: 40px;
            padding: 0 10px;
            color: #fff;
            font-size: 16px;
            display: flex;
            align-items: center;
            cursor: pointer;
        }

        .body-left-detail .img-btns .img-btn span {
            height: 30px;
            line-height: 30px;
            color: #fff;
            font-size: 14px;
            font-weight: 200;
        }

        .body-left-detail .img-btns .img-btn:hover span {
            height: 30px;
            line-height: 30px;
            color: #fff;
            font-size: 14px;
            font-weight: 600;
        }

        .body-left-detail .img-btns .img-btn img {
            width: 18px;
            height: 18px;
            display: inline-block;
            margin-right: 5px;
        }


        .body-left-par .title {
            padding: 10px 0;
            margin: 0 4%;
            width: 92%;
            /* border-bottom: 1px solid #bbb; */
            font-size: 20px;
            color: #333;
            text-align: left;
        }

        .body-left-par .title span {
            font-size: 16px;
        }

        .body-left-par .bars {
            /*padding: 10px 0;*/
            margin: 0 4%;
            width: 92%;
            display: flex;
            flex-wrap: wrap;
        }

        .body-left-par div.mb-40 {
            margin-bottom: 40px;
        }

        .body-left-par .bars .bar {
            margin-bottom: 20px;
            margin-right: 20px;
        }

        .body-left-par .bars .bar .bar-img {
            width: 100px;
            height: 100px;
            border: 1px solid #d0c9c9;
            margin-bottom: 9px;
            background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAG4AAABkCAYAAABnwAWdAAAED0lEQVR4Xu2dUVLaUBSGzyHTxLe6g+IKKisoTkn0TbuC1hUUV6CuQLuC6gpK34QwI90BO9Ad1L4RWjmd1Dq1M0ISuPeSk/y8kpzz3+/LwUwkgQkvlQRYZWqEJohTehBAXNXF7V1LU+6nx0TSFOJtJtpc15qF6I6Jeuz5p1c7fLuuHOvsm2vionh6xizddQZ9rncqkIgOB52gV7ZstvNkitsdTq6JuG07yCr1hehd3eQtFBfFyQkzHa8C1cW+6eQ1PL9Vp4/NueLa17IZ3E+/uwBvpoeM+p2NHTO1yl9lrrjdeNIl5rPyL+Ffwjp9ZC4Ql/SIaV+ZuLup52+Ndjg9aan0a7644eSGiJv6Vs+f+h2/dGfApjkuEJeI6Wau6s1m3Iojf+yq3zr6VFKcCI0HYdBaB1BXPSspLoU3Yz6M3/oXrkC67lNZcQ9XVbg76PiXrqG66FdZcY/wHq5rSsn/3qXXW2WceMFl3jPiyotzcfSb6pEeZMJ8lOcjHuJMUTdZR+SoH26cLyoJcSaBG6qVTl7WhQSIMwTbdJmss2KIM03cUD0ROh2Ewcm8chBnCLTpMiLybRBuzP0/KMSZJm6oHsQZAum6DMS5Jm6oH8QZAum6DMS5Jm6oH8QZAum6DMS5Jm6oH8QZAum6DMS5Jm6on1NxIvSDiM4b1BgZyq++jLAcEMnHogtxJi6VJsLtqn9Jp6iAdPtomBww0Zci+zoTR1SPr8UVgf9022iYjJnodd79nYnLupqdN3BVt4uGyQUTvc+7PojLS8rydlE8GTHzm7xtnIkjktvEC1p5v+ySdwFV2O7vTaE3RdbiUFwaS0bsBYd1ut0pS0Y4mG4zy2dm2s7a9un7jsU9tpb09t5a3uL7H/wVbrlek7gixxa2fY4AxCk9LiAO4pQSUBobEwdxSgkojY2JgzilBJTGxsRBnFICSmNj4iBOKQGlsTFxEKeUgNLYmDiIU0pAaWxMHMQpJaA0NiYO4pQSUBobEwdxSgkojY2JgzilBJTGxsRBnFICSmNj4iBOKQGlsTFxEKeUgNLYmDiIU0pAaWxMHMQpJaA0NiYO4pQSUBobEwdxSgkojY2JgzilBJTGxsRBnFICSmNj4iBOKQGlsVeYuMktEb9Sum71sZcWV/TBmOpJlWwBS4vbjZMeMe2XbD21ibOCuEmXmM9qQ6pkC816xvXcH/5LH0c7+zUdM9PLkq2pFnHY87cWPWl3rriUThQnJ8x0XAtSpVpk9k8BLBT3R17Bx66Xav0awwh97YfBQVb0THFpgb34Z3vGsw8k0swqiPeXI8DcGLNw7yp8kevnbXKJWy4K9rJJAOJs0rVYG+IswrVZGuJs0rVYG+IswrVZGuJs0rVYG+IswrVZGuJs0rVY+zcnJPKDQOgKcwAAAABJRU5ErkJggg==);
            background-position: center;
            background-repeat: no-repeat;
        }

        .body-left-par .bars .bar .bar-title {
            line-height: 20px;
            font-size: 14px;
            text-align: center;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            width: 100px;
        }

        .top-right {
            position: absolute;
            top: 245px;
            right: 0;
            width: 40%;
            height: 600px;
            background: url('${ctx}/img/report/zhbx-data.png');
            background-repeat: no-repeat;
            background-size: contain;
        }

        .top-right .city {
            padding-top: 2px;
            padding-left: 18%;
            font-size: 12px;
            width: 200px;
            height: 30px;
        }

        .top-right .city-box {
            display: none;
        }

        .body-right-zh {
            font-family: "KaiTi" !important;
        }

        .body-right-albx {
            /* display: none; */
            width: 96%;
            margin: 0 auto;
            padding-bottom: 2%;
            font-family: "STSong" !important;
        }

        .body-right-albx .table {
            width: 92%;
            margin: 0 auto;
            border: 1px solid #000;
            overflow: hidden;
        }

        .body-right-albx .table .b_title {
            padding: 20px 0;
            border-bottom: 1px solid #000;
            font-size: 20px;
            font-weight: 600;
            text-align: center;
            font-family: "STHeiti" !important;
        }

        .body-right-albx .table .b_tr {
            display: flex;
            width: 100%;
            font-size: 16px;
            border-bottom: 1px solid #000;
        }

        .body-right-albx .table .b_tr .b_td {
            width: 99%;
            line-height: 20px;
            padding: 8px 0.5%;
            border-right: 1px solid #000;
            overflow: hidden;
            word-wrap: break-word;
            word-break: break-all;
            /* white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis; */
        }

        .body-right-albx .table .b_tr .b_td div.report,  .body-right-albx .table .b_tr .b_td div.report-read{
            line-height: 20px;
            font-weight: 300;
            display: inline-block;
            min-width: 50%;
            min-height: 20px;
        }

        .body-right-albx .table .b_tr .b_td input.value-box {
            display: none;
            width: 94%;
            /* height: 0px; */
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right-albx .table .b_tr .b_td textarea.value-box {
            display: none;
            width: 94%;
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }


        .body-right-albx .table .b_tr .b_td input.value-box2 {
            display: none;
            width: 94%;
            /* height: 0px; */
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right-albx .table .b_tr .b_td textarea.value-box2, .body-right-albx .table .b_tr .b_td textarea.value-box6 {
            display: none;
            width: 95%;
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right-albx .table .b_tr .b_td input.value-box5 {
            display: none;
            width: 94%;
            /* height: 0px; */
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right-albx .table .b_tr .b_td textarea.value-box5 {
            display: none;
            width: 80%;
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right-albx .table .b_tr:last-child {
            border: none;
        }

        .body-right-albx .table .b_tr .b_td:last-child {
            border: none;
        }

        .body-right-albx .table div.border-none {
            border: none;
        }

        .body-right-albx .table .b_tr div.b_wid1 {
            width: 30%;
        }

        .body-right-albx .table .b_tr div.b_wid2 {
            width: 67%;
        }

        .body-right-albx .table .b_tr div.b_wid3 {
            width: 33%;
        }

        .body-right-albx .table .b_tr div.b_wid4 {
            width: 24%;
        }

        .body-right-albx .table .b_tr .b_td_title {
            text-align: center;
            padding: 10px 0;
        }

        .body-right-albx .table .b_tr .b_td_center {
            text-align: center;
        }

        .body-right-lf {
            display: block;
            font-family: "STFangsong" !important;
        }

        /* .body-right-lf *{
            font-family:"KaiTi_GB2312 "!important;
        } */
        .body-right-lf .lf-title {
            width: 80%;
            margin: 0 auto;
            padding-bottom: 60px;
        }

        .body-right-lf .lf-title .lf-title-logo {
            width: 300px;
            height: 200px;
            background: url('${ctx}/img/report/lf-logo.jpg');
            background-repeat: no-repeat;
            background-size: contain;
            background-position: center;
            margin: 20px auto;
        }
        .body-right-lf .lf-title .lf-title-logo-zy {
            width: 300px;
            height: 200px;
            background: url('${ctx}/img/report/zy-logo.jpg');
            background-repeat: no-repeat;
            background-size: contain;
            background-position: center;
            margin: 20px auto;
        }
        .body-right-lf .lf-title .lf-title-text1 {
            width: 100%;
            text-align: center;
            font-size: 20px;
            font-weight: 600;
            padding: 50px 0;
        }

        .body-right-lf .lf-title .lf-title-text2 {
            padding: 20px 0;
            padding-top: 100px;
            margin-bottom: 60px;
            width: 100%;
            text-align: center;
            font-size: 16px;
            font-weight: 600;
            border-bottom: 1px solid blue;

        }

        .body-right-lf .lf-title .lf-title-text3 {
            width: 100%;
            text-align: center;
            font-size: 14px;
            font-weight: 400;
            padding: 10px 0;
        }

        .body-right .form .form-header {
            display: block;
            line-height: 40px;
            font-size: 20px;
            font-weight: 600;
            text-align: left;
        }

        .body-right .form .form-header-content {
            line-height: 26px;
            font-size: 20px;
            font-weight: 600;
            text-align: left;
            text-indent: 2em;
            padding-bottom: 10px;
        }

        .body-right .form .cell-title {
            display: block;
            line-height: 40px;
            font-size: 20px;
            font-weight: 600;
            text-align: left;
        }

        .body-right .info {
            width: 100%;
            line-height: 36px;
            text-indent: 2em;
            word-wrap: break-word;
            word-break: break-all;
            font-size: 14px;
            font-weight: 400;
        }

        .body-right .info-bot {
            width: 100%;
            font-size: 16px;
            line-height: 36px;
            text-align: center;
        }

        .body-right .info-sign {
            font-size: 20px;
            text-align: right;
            white-space: nowrap;
        }

        .body-right .info-sign .value-text {
            display: inline;
            color: #666;
        }

        .body-right .info-sign input.value-box {
            display: none;
            width: 30%;
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right .info-sign textarea.value-box {
            display: none;
            width: 30%;
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right-lf .cell2 {
            display: block;
            padding: 5px 0;
        }

        .body-right-lf .cell2 .value2 {
            width: 100%;
            position: relative;
            padding-right: 1%;
            height: 20px;
            line-height: 20px;
            font-weight: 500;
            white-space: nowrap;
        }

        .body-right-lf .cell2 .value {
            width: 100%;
            position: relative;
        }

        .body-right-lf .cell2 div.value-dcfx {
            display: inline-block;
            width: auto;
        }

        .body-right-lf .cell2 .value .value-text {
            padding: 2px 0;
        }

        .body-right-lf .cell2 input.value-box3 {
            display: none;
            width: 94%;
            /* height: 0px; */
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right-lf .cell2 textarea.value-box3 {
            display: none;
            width: 80%;
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right-lf .cell2 input.value-box4 {
            display: none;
            width: 94%;
            /* height: 0px; */
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right-lf .cell2 textarea.value-box4 {
            display: none;
            width: 80%;
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right-lf .lf-title .value-text {
            display: inline;
            color: #666;
        }

        .body-right-lf .lf-title input.value-box {
            display: none;
            width: 94%;
            /* height: 0px; */
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right-lf .lf-title textarea.value-box {
            display: none;
            width: 80%;
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right-lf .form-header .value-text,
        .body-right-lf .form-header-content .value-text {
            display: inline;
            color: #666;
        }

        .body-right-lf .form-header input.value-box,
        .body-right-lf .form-header-content input.value-box {
            display: none;
            width: 94%;
            /* height: 0px; */
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right-lf .form-header textarea.value-box,
        .body-right-lf .form-header-content textarea.value-box {
            display: none;
            width: 80%;
            line-height: 20px;
            padding: 8px 3%;
            border: 1px solid #d0c9c9;
            color: #666;
        }

        .body-right-title {
            padding: 10px 0;
            margin: 0 auto;
            width: 92%;
            font-size: 20px;
            line-height: 30px;
            border-bottom: 1px solid rgba(187, 187, 187, 1)
        }

        .body-right-update {
            padding: 10px 0;
            margin: 0 auto;
            width: 92%;
            background-color: antiquewhite;
            border: none;
        }

        .body-right-update .body-right-update-name {
            font-size:16px;
            font-weight:500;
        }

        .body-right-update .body-right-update-t {
            width: 100%;
            height: 60px;
            line-height: 20px;
            font-size: 14px;
            /*margin: 10px 0;*/
            background-color: antiquewhite;
            border: none;
        }

        .body-right-btns {
            position: fixed;
            top: 17px;
            right: 20px;
            width: 200px;
            display: flex;
            justify-content: space-between;
            z-index: 99;
        }

        .body-right-btns .btns {
            display: flex;
            width: 200px;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        .body-right-btns .btns .btn {
            margin-bottom: 10px;
            /*width: 146px;*/
            height: 30px;
            padding: 0 10px;
            line-height: 30px;
            color: #fff;
            background-color: #3FB5FD;
            border: 1px solid #3FB5FD;
            text-align: center;
            font-size: 14px;
        }

        .body-right-btns .btns .btn2 {
            /*width: 96px;*/
            color: #3FB5FD;
            background-color: #fff;
        }

        #select {
            display: none;
        }

        .report,
        .report2,
        .report3,
        .report4,
        .report5,
        .report6{
            color: #666;
        }

        .b_tr .blue {
            color: blue;
        }


        .body-right-lf, .body-right-albx, .body-right-zh{
            display: none;
        }
    </style>
</head>

<body>
<div class="contain">
    <div class="body-left-par" id="body-left">
        <div class="title" id="directionFilesTitle" >方向附件</div>
        <div class="bars">
            <jsp:include page="showInfoOprLeft.jsp" />

<%--            <c:forEach items="${oprInfo.directions}" var="item">--%>
<%--                <div class="bar" data-type="directions" data-value="${item.id}" data-name="${item.directionName}" data-size="${item.surveyCaseDirectionFiles.size()}">--%>
<%--                    <div class="bar-img"></div>--%>
<%--                    <div class="bar-title" title="${item.directionName}">${item.directionName}（${item.surveyCaseDirectionFiles.size()}）</div>--%>
<%--                </div>--%>
<%--            </c:forEach>--%>
        </div>
        <div class="title">查看材料</div>
        <div class="bars mb-40">
            <c:forEach items="${oprInfo.files}" var="item">
                <div class="bar" data-type="files" data-value="${item.catalogId}" data-name="${item.catalogName}" data-size="${item.num}">
                    <div class="bar-img"></div>
                    <div class="bar-title">${item.catalogName}（${item.num}）</div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="body-left" id="body-left">
        <div class="title"><span onclick="showIndex()" style="cursor: pointer;" id="span_tip_1">方向附件</span> > <span id="span_tip_2">瑞金医院（10）</span></div>
        <div class='files'>

        </div>
    </div>
    <div class="body-left-detail">
        <div class="img-detail">
            <div class="img-src" data-id='1'><img src="${ctx}/img/report/test1.jpg" class="image" id="imgOne"></img></div>
            <div class="img-btns">
                <div class="img-btn rolateleft"><img src="${ctx}/img/report/icon-rolate-0.png" alt=""><span>向左旋转</span> </div>
                <div class="img-btn rolateright"><img src="${ctx}/img/report/icon-rolate-1.png" alt=""><span>向右旋转</span></div>
                <div class="img-btn img-btn3"><img src="${ctx}/img/report/icon-text.png" alt=""><span>文字识别</span></div>
                <div class="img-btn goback"><img src="${ctx}/img/report/icon-goback.png" alt=""><span>返回上级</span></div>
            </div>
            <div class="img-pre"></div>
            <div class="img-next"></div>

        </div>
    </div>
    <form id="editForm" role="form"
          <c:if test="${btnCode == 'org5'}">action="${ctx}/survey/case/operateAssign"</c:if>
          <c:if test="${btnCode == 'survey-report-opr'}">action="${ctx}/survey/case/operate"</c:if>
          method="post" style="width: 100%;">
        <input type="hidden" id="surveyInfoId" name="surveyInfoId" value="${oprInfo.surveyRiskInfo.id}" />
        <input type="hidden" name="id" id="id"
               <c:if test="${btnCode == 'org5'}">value="${oprInfo.surveyAssignOrg.id}"</c:if>
               <c:if test="${btnCode == 'survey-report-opr'}">value="${oprInfo.surveyRiskInfo.id}"</c:if> />
        <input type="hidden" name="btnCode" value="${btnCode}" id="btnCode" />
        <div class="body-right-btns">
            <div class="btns">
                <div class="btn btn2" onclick="view(this)">编辑</div>
                <c:if test="${btnCode == 'org5'}">
                    <c:if test="${oprInfo.surveyAssignOrg.orgSurveyState == 2 || oprInfo.surveyAssignOrg.orgSurveyState == 6}">
                        <div class="btn" onclick="submitForm()">审核通过</div>
                        <div class="btn btn2" onclick="oprBack(${oprInfo.surveyAssignOrg.id},'org4')">退回</div>
                    </c:if>
                </c:if>
                <c:if test="${btnCode == 'survey-report-opr'}">
                    <c:if test="${oprInfo.surveyRiskInfo.surveyState == 22 || oprInfo.surveyRiskInfo.surveyState == 30}">
                        <input type="hidden" value="<fmt:formatDate value="${oprInfo.surveyRiskInfo.surveyRiskCase.entrustTime}" pattern="yyyy-MM-dd"/>" id="entrustTime" />
                        <div class="btn" onclick="agreen(${oprInfo.surveyRiskInfo.id},1300)">审核通过</div>
                        <div class="btn btn2" onclick="oprBack(${oprInfo.surveyRiskInfo.id},'${btnCode}')">退回</div>
                    </c:if>
                </c:if>
                <div class="btn" style="display: none;">保存并通过</div>
            </div>
        </div>
        <%--style="margin-top: 53px"--%>
        <div class="body-right word-pre" >
<%--            <iframe style="height: 100%;width: 100%;" src="https://view.officeapps.live.com/op/view.aspx?src=https://ddrapi.shlefan.com/sftp/files/product/ddr/cno/cwt1051568012526553/report/朱小喜案.doc&v=1"></iframe>--%>
            <iframe style="height: 100%;width: 100%;" src="https://view.officeapps.live.com/op/view.aspx?src=${generateReportPath}&v=1"></iframe>
        </div>
    <%--    乐凡--%>
        <c:if test="${oprInfo.templateData.model.id == 1 || oprInfo.templateData.model.id == 6 || oprInfo.templateData.model.id == 7}">
            <div class="body-right body-right-lf">
                <div>
                    <div class="body-right-title">调查报告</div>
                    <c:if test="${btnCode == 'org5'}">
                        <div class="body-right-update">
                            <div class="body-right-update-name">审核意见</div>
                            <textarea class="body-right-update-t" name="orgOprOpinion" id="orgOprOpinion"  cols="30" rows="3"  placeholder="请输入审核意见，若无意见可不填..."></textarea>
                        </div>
                    </c:if>
                    <c:if test="${btnCode != 'org5' && oprInfo.surveyRiskInfo.orgOprOpinion != null}">
                        <div class="body-right-update">
                            <div class="body-right-update-name">主机构审核意见</div>
                            <textarea class="body-right-update-t" name="" cols="30" rows="3" readonly>${oprInfo.surveyRiskInfo.orgOprOpinion}</textarea>
                        </div>
                    </c:if>
                </div>
                <div class="lf-title">
                    <div class="lf-title-logo"></div>
                    <div class="lf-title-text1">
                        <div class="value-text report-read" data-id='1'>${oprInfo.templateData.templateDataLF.entrustOrgName}</div>
                        <input class="value-box" type="text" data-id="1" placeholder="" value="${oprInfo.templateData.templateDataLF.entrustOrgName}">
                    </div>
                    <div class="lf-title-text1">
                        <div class="value-text report-read" data-id='2'>《${oprInfo.templateData.templateDataLF.entrustUserName}调查报告》</div>
                        <input class="value-box" type="text" data-id="2" placeholder="" value="${oprInfo.templateData.templateDataLF.entrustUserName}调查报告">
                    </div>
                    <div class="lf-title-text2">
                        <div class="value-text report-read" data-id='3'>${oprInfo.templateData.templateDataLF.trusteeOrgName}</div>
                        <input class="value-box" type="text" data-id="3" placeholder="" value="${oprInfo.templateData.templateDataLF.trusteeOrgName}">
                    </div>
                    <div class="lf-title-text3">地址：上海市静安区共和新路340号210室</div>
                    <div class="lf-title-text3">电话：021-52659080 传真：021-52659081</div>
                </div>
                <div class="form">
                    <div class="cell">
                        <div class="img-text">调查报告</div>
                    </div>
                    <div class="form-header">致：
                        <div class="value-text report-read" data-id='4'>${oprInfo.templateData.templateDataLF.entrustOrgName}</div>
                        <input class="value-box" type="text" data-id="4" placeholder="" value="${oprInfo.templateData.templateDataLF.entrustOrgName}">
                    </div>
                    <div class="form-header-content">
                        <div class="value-text report-read" data-id='5'>${oprInfo.templateData.templateDataLF.trusteeOrgName}</div>
                        <input class="value-box" type="text" data-id="5" placeholder="" value="${oprInfo.templateData.templateDataLF.trusteeOrgName}">
                        受贵公司委托，根据国家有关法律规定，本着客观、公正和实事求是的原则，按照公认的保险基本原则和保险公估方法，对下列事项进行保险查勘，现报告如下：
                    </div>
                    <div class='cell-title'>一、事件梗概</div>
                    <div class="cell">
                        <div class="label">委托人：</div>
                        <div class="value">
                            <div class="value-text report-read" data-id='6'>${oprInfo.templateData.templateDataLF.entrustOrgName}</div>
                            <input class="value-box" type="text" data-id="6"  placeholder="" value="${oprInfo.templateData.templateDataLF.entrustOrgName}">
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">案件号：</div>
                        <div class="value">
                            <div class="value-text report" data-id='7'>${oprInfo.templateData.templateDataLF.surveyCno}</div>
                            <input class="value-box" type="text" data-id="7" data-code="upd-survey-case-no" placeholder="" value="${oprInfo.templateData.templateDataLF.surveyCno}">
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">受托日期：</div>
                        <div class="value">
                            <div class="value-text-date report-date" data-id='8' data-code="upd-entrust-time" onclick="WdatePicker({dateFmt:'yyyy年MM月dd日',onpicked:pickerEd})">${oprInfo.templateData.templateDataLF.entrustDate}</div>
<%--                            <input class="value-box" type="text" data-id="8" data-code="entrustDate" placeholder="" value="${oprInfo.templateData.templateDataLF.entrustDate}">--%>
<%--                            <input id="insureTime" name="insureTime"  data-id="8"  data-code="entrustDate" placeholder=""  type="text" class="value-box-test"--%>
<%--                                   value="<fmt:formatDate value="${oprInfo.surveyRiskInfo.surveyRiskCase.entrustTime}" pattern="yyyy年MM月dd日"/>"--%>
<%--                                   onclick="WdatePicker({dateFmt:'yyyy年MM月dd日',onpicked:pickerEd})" readonly>--%>
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">保险种类：</div>
                        <div class="value">
                            <div class="value-text report" data-id='9'>${oprInfo.templateData.templateDataLF.safeTypeStr}</div>
                            <%--<select class="value-box form-control" data-id="9" data-code="upd-insure-type" placeholder="" >
                                <option value="医疗" <c:if test="${oprInfo.templateData.templateDataLF.safeTypeStr == '医疗'}">selected</c:if> >医疗</option>
                                <option value="重疾" <c:if test="${oprInfo.templateData.templateDataLF.safeTypeStr == '重疾'}">selected</c:if> >重疾</option>
                                <option value="身故" <c:if test="${oprInfo.templateData.templateDataLF.safeTypeStr == '身故'}">selected</c:if> >身故</option>
                                <option value="其他" <c:if test="${oprInfo.templateData.templateDataLF.safeTypeStr == '其他'}">selected</c:if> >其他</option>
                            </select>--%>
                            <input class="value-box" type="text" data-id="9" data-code="upd-insure-type" placeholder="" value="${oprInfo.templateData.templateDataLF.safeTypeStr}">
<%--                            <input class="value-box" type="text" data-id="9" data-code="safeTypeStr" placeholder="" value="${oprInfo.templateData.templateDataLF.safeTypeStr}">--%>
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">投保日期：</div>
                        <div class="value">
                            <div class="value-text-date report-date" data-id='10' data-code="upd-insure-time" onclick="WdatePicker({dateFmt:'yyyy年MM月dd日',onpicked:pickerEd})">${oprInfo.templateData.templateDataLF.safeDate}</div>
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">出险人：</div>
                        <div class="value">
                            <div class="value-text report" data-id='11'>${oprInfo.templateData.templateDataLF.entrustUserName}</div>
                            <input class="value-box" type="text" data-id="11" data-code="upd-survey-person" placeholder="" value="${oprInfo.templateData.templateDataLF.entrustUserName}">
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">出险时间：</div>
                        <div class="value">
                            <div class="value-text-date report-date" data-id='12' data-code="upd-danger-time" onclick="WdatePicker({dateFmt:'yyyy年MM月dd日',onpicked:pickerEd})">${oprInfo.templateData.templateDataLF.surveyUserDate}</div>
<%--                            <input id="surveyUserDate" name="surveyUserDate"  data-id="12"  data-code="surveyUserDate" placeholder=""  type="text" class="value-box"--%>
<%--                                   value="<fmt:formatDate value="${oprInfo.surveyRiskInfo.surveyRiskCase.dangerTime}" pattern="yyyy年MM月dd日"/>"--%>
<%--                                   onclick="WdatePicker({dateFmt:'yyyy年MM月dd日'})" readonly>--%>
<%--                            <input class="value-box" type="text" data-id="12" placeholder="" value="${oprInfo.templateData.templateDataLF.surveyUserDate}">--%>
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">出险地点：</div>
                        <div class="value">
                            <div class="value-text report" data-id='13'>${oprInfo.templateData.templateDataLF.surveyUserAddress}
                            </div>
                            <input class="value-box" type="text" data-id="13" data-code="upd-danger-address" placeholder=""
                                   value="${oprInfo.templateData.templateDataLF.surveyUserAddress}">
                        </div>
                    </div>
                    <div class='cell-title'>二、委托事项</div>
                    <div class="cell">
                        <div class="value">
                            <div class="value-text report" data-id='14'>${oprInfo.templateData.templateDataLF.surveyItem}</div>
                            <textarea class="value-box" name="" data-id="14" data-code="upd-survey-item" cols="30" rows="9">${oprInfo.templateData.templateDataLF.surveyItem}</textarea>
                        </div>
                    </div>
                    <div class='cell-title'>三、事故经过</div>
                    <div class="cell">
                        <div class="value">
                            <div class="value-text report" data-id='15'>${oprInfo.templateData.templateDataLF.accidentInfo}</div>
                            <textarea class="value-box" name="" data-id="15" data-code="upd-survey-info" cols="30" rows="9">${oprInfo.templateData.templateDataLF.accidentInfo}</textarea>
                        </div>
                    </div>
                    <div class='cell-title'>四、被保人基本信息：</div>
                    <div class="cell">
                        <div class="value inline">
                            被保险人
                            <div class="value-text report" data-id='16'>${oprInfo.templateData.templateDataLF.entrustUserName}</div>,
                            <input class="value-box" type="text" data-id="16" data-code="upd-survey-person" placeholder="" value="${oprInfo.templateData.templateDataLF.entrustUserName}">
                            <div class="value-text report" data-id='17'>${oprInfo.templateData.templateDataLF.sexStr}</div>,
                            <select class="value-box form-control" data-id="17" data-code="upd-sex" placeholder="" >
                                <option value="男" <c:if test="${oprInfo.templateData.templateDataZY.sexStr == '男'}">selected</c:if> >男</option>
                                <option value="女" <c:if test="${oprInfo.templateData.templateDataZY.sexStr == '女'}">selected</c:if> >女</option>
                            </select>
                            <div class="value-text report" data-id='18'>${oprInfo.templateData.templateDataLF.nation}</div>,
                            <input class="value-box" type="number" data-id="18" data-code="upd-nation" placeholder="" value="${oprInfo.templateData.templateDataLF.age}">
                            身份证号：
                            <div class="value-text report" data-id="19">${oprInfo.templateData.templateDataLF.idNumber}</div>
                            <input class="value-box" type="text" data-id="19" data-code="upd-id-number" placeholder="" value="${oprInfo.templateData.templateDataLF.idNumber}">
                            。
                        </div>
                    </div>
                    <div class='cell-title'>五、调查方向</div>
                    <div class="cell cell2">
                        <c:forEach items="${oprInfo.directions}" var="item" varStatus="st">
                            <div class="value-dcfx">
                                <div class="value-text report3" data-id="${st.index + 1}">${st.index + 1}、${item.directionName};</div>
                                <textarea class="value-box3" name="" data-id="${st.index + 1}" data-code="upd-direction-name" data-direction-id="${item.id}" cols="30" rows="9">${item.directionName}</textarea>
                            </div>
                        </c:forEach>
                    </div>
                    <div class='cell-title'>六、调查情况</div>
                    <c:forEach items="${oprInfo.directions}" var="item" varStatus="st">
                        <div class="cell cell2">
                            <div class="value2">
                                <div class="value-text report4 blod" data-id='${st.index + 1}'>${item.directionName}</div>
                                <input class="value-box4" type="text" data-id="${st.index + 1}" data-code="upd-direction-name" data-direction-id="${item.id}" placeholder="" value="${item.directionName}">
                            </div>
                        </div>
                        <div class="cell cell2">
                            <div class="value">
                                <div class="value-text report4" data-id='${oprInfo.directions.size() + st.index + 1}'>${item.directionInfo}</div>
                                <textarea class="value-box4" name="" data-id="${oprInfo.directions.size() + st.index + 1}" data-code="upd-direction-info" data-direction-id="${item.id}" cols="30" rows="9">${item.directionInfo}</textarea>
                            </div>
                        </div>
                    </c:forEach>
                    <div class='cell-title'>七、调查结论</div>
                    <div class="cell">
                        <div class="value">
                            <div class="value-text report" data-id='20'>${oprInfo.templateData.templateDataLF.directionResult}</div>
                            <textarea class="value-box" name="" data-id="20" data-code="upd-report-completion" cols="30" rows="9">${oprInfo.templateData.templateDataLF.directionResult}</textarea>
                        </div>
                    </div>
                    <div class='cell-title'>八、调查依据</div>
                    <div class="cell">
                        <div class="value">
                            <div class="value-text report-read" data-id='21'>${oprInfo.templateData.templateDataLF.fileMidInfo}</div>
                            <textarea class="value-box" name="" data-id="21" cols="30" rows="9">${oprInfo.templateData.templateDataLF.fileMidInfo}</textarea>
                        </div>
                    </div>
                    <div class='cell-title'>九、说明事项</div>
                    <div class="info">
                        1、保险合同各方对所提供资料真实性、完整性、合法性及其产生的后果负责
                    </div>
                    <div class="info">
                        2、本报告仅对本次委托有效，不作它用。未经我公司同意，不得向委托方和当事人之外的任何单位和个人提供调查报告书的全部或部分内容，亦不得公开发表，否则，将承担相应的法律责任。
                    </div>
                    <div class="info">
                        3、如发现本报告内的文字、数字因校印或其他原因出现误差，请委托人通知我们更正。
                    </div>
                    <div class="info-bot">（以下无正文）</div>

                    <div class='info-sign'>
                        调查员：
                        <div class="value-text report-read" data-id='22'>${oprInfo.templateData.templateDataLF.surveyUserName}</div>
                        <input class="value-box" type="text" data-id="22" placeholder="" value="${oprInfo.templateData.templateDataLF.surveyUserName}">
                    </div>
                    <div class='info-sign'>
                        签发人:
                        <div class="value-text report-read" data-id='23'>${oprInfo.templateData.templateDataLF.surveyManagerName}</div>
                        <input class="value-box" type="text" data-id="23" placeholder="" value="${oprInfo.templateData.templateDataLF.surveyManagerName}">
                    </div>
                    <div class='info-sign'>
                        <div class="value-text report-read" data-id='24'>${oprInfo.templateData.templateDataLF.trusteeOrgName}</div>
                        <input class="value-box" type="text" data-id="24" placeholder="" value="${oprInfo.templateData.templateDataLF.trusteeOrgName}">
                    </div>
                    <div class='info-sign'>
                        <div class="value-text report-read" data-id='25'>${oprInfo.templateData.templateDataLF.userSignDate}</div>
                        <input class="value-box" type="text" data-id="25" placeholder="" value="${oprInfo.templateData.templateDataLF.userSignDate}">
                    </div>
                </div>
            </div>
        </c:if>
    <%--    正言--%>
        <c:if test="${oprInfo.templateData.model.id == 2}">
            <div class="body-right body-right-lf">
                <div>
                    <div class="body-right-title">调查报告</div>
                    <c:if test="${btnCode == 'org5'}">
                        <div class="body-right-update">
                            <div class="body-right-update-name">审核意见</div>
                            <textarea class="body-right-update-t" name="orgOprOpinion" id="orgOprOpinion"  cols="30" rows="3" placeholder="请输入审核意见，若无意见可不填..."></textarea>
                        </div>
                    </c:if>
                    <c:if test="${btnCode != 'org5' && oprInfo.surveyRiskInfo.orgOprOpinion != null}">
                        <div class="body-right-update">
                            <div class="body-right-update-name">主机构审核意见</div>
                            <textarea class="body-right-update-t" name="" cols="30" rows="3" readonly>${oprInfo.surveyRiskInfo.orgOprOpinion}</textarea>
                        </div>
                    </c:if>
                </div>
                <div class="lf-title">
                    <div class="lf-title-logo-zy"></div>
                    <div class="lf-title-text1">
                        <div class="value-text report-read" data-id='1'>${oprInfo.templateData.templateDataZY.entrustOrgName}</div>
                        <input class="value-box" type="text" data-id="1" placeholder="" value="${oprInfo.templateData.templateDataZY.entrustOrgName}">
                    </div>
                    <div class="lf-title-text1">
                        <div class="value-text report-read" data-id='2'>《${oprInfo.templateData.templateDataZY.entrustUserName}调查报告》</div>
                        <input class="value-box" type="text" data-id="2" placeholder="" value="${oprInfo.templateData.templateDataZY.entrustUserName}调查报告">
                    </div>
                    <div class="lf-title-text2">
                        <div class="value-text report-read" data-id='3'>${oprInfo.templateData.templateDataZY.trusteeOrgName}</div>
                        <input class="value-box" type="text" data-id="3" placeholder="" value="${oprInfo.templateData.templateDataZY.trusteeOrgName}">
                    </div>
                    <div class="lf-title-text3">地址：上海市静安区共和新路340号210室</div>
                    <div class="lf-title-text3">电话：021-52659080 传真：021-52659081</div>
                </div>
                <div class="form">
                    <div class="cell">
                        <div class="img-text">调查报告</div>
                    </div>
                    <div class="form-header">致：
                        <div class="value-text report-read" data-id='4'>${oprInfo.templateData.templateDataZY.entrustOrgName}</div>
                        <input class="value-box" type="text" data-id="4" placeholder="" value="${oprInfo.templateData.templateDataZY.entrustOrgName}">
                    </div>
                    <div class="form-header-content">
                        <div class="value-text report-read" data-id='5'>${oprInfo.templateData.templateDataZY.trusteeOrgName}</div>
                        <input class="value-box" type="text" data-id="5" placeholder="" value="${oprInfo.templateData.templateDataZY.trusteeOrgName}">
                        受贵公司委托，根据国家有关法律规定，本着客观、公正和实事求是的原则，按照公认的保险基本原则和保险公估方法，对下列事项进行保险查勘，现报告如下：
                    </div>
                    <div class='cell-title'>一、事件梗概</div>
                    <div class="cell">
                        <div class="label">委托人：</div>
                        <div class="value">
                            <div class="value-text report-read" data-id='6'>${oprInfo.templateData.templateDataZY.entrustOrgName}</div>
                            <input class="value-box" type="text" data-id="6"  placeholder="" value="${oprInfo.templateData.templateDataZY.entrustOrgName}">
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">案件号：</div>
                        <div class="value">
                            <div class="value-text report" data-id='7'>${oprInfo.templateData.templateDataZY.surveyCno}</div>
                            <input class="value-box" type="text" data-id="7" data-code="upd-survey-case-no" placeholder="" value="${oprInfo.templateData.templateDataZY.surveyCno}">
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">受托日期：</div>
                        <div class="value">
                            <div class="value-text-date report-date" data-id='8' data-code="upd-entrust-time" onclick="WdatePicker({dateFmt:'yyyy年MM月dd日',onpicked:pickerEd})">${oprInfo.templateData.templateDataZY.entrustDate}</div>
                                <%--                            <input class="value-box" type="text" data-id="8" data-code="entrustDate" placeholder="" value="${oprInfo.templateData.templateDataZY.entrustDate}">--%>
                                <%--                            <input id="insureTime" name="insureTime"  data-id="8"  data-code="entrustDate" placeholder=""  type="text" class="value-box-test"--%>
                                <%--                                   value="<fmt:formatDate value="${oprInfo.surveyRiskInfo.surveyRiskCase.entrustTime}" pattern="yyyy年MM月dd日"/>"--%>
                                <%--                                   onclick="WdatePicker({dateFmt:'yyyy年MM月dd日',onpicked:pickerEd})" readonly>--%>
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">保险种类：</div>
                        <div class="value">
                            <div class="value-text report" data-id='9'>${oprInfo.templateData.templateDataZY.safeTypeStr}</div>
                            <%--<select class="value-box form-control" data-id="9" data-code="upd-insure-type" placeholder="" >
                                <option value="医疗" <c:if test="${oprInfo.templateData.templateDataZY.safeTypeStr == '医疗'}">selected</c:if> >医疗</option>
                                <option value="重疾" <c:if test="${oprInfo.templateData.templateDataZY.safeTypeStr == '重疾'}">selected</c:if> >重疾</option>
                                <option value="身故" <c:if test="${oprInfo.templateData.templateDataZY.safeTypeStr == '身故'}">selected</c:if> >身故</option>
                                <option value="其他" <c:if test="${oprInfo.templateData.templateDataZY.safeTypeStr == '其他'}">selected</c:if> >其他</option>
                            </select>--%>
                            <input class="value-box" type="text" data-id="9" data-code="upd-insure-type" placeholder="" value="${oprInfo.templateData.templateDataLF.safeTypeStr}">
                                <%--                            <input class="value-box" type="text" data-id="9" data-code="safeTypeStr" placeholder="" value="${oprInfo.templateData.templateDataZY.safeTypeStr}">--%>
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">投保日期：</div>
                        <div class="value">
                            <div class="value-text-date report-date" data-id='10' data-code="upd-insure-time" onclick="WdatePicker({dateFmt:'yyyy年MM月dd日',onpicked:pickerEd})">${oprInfo.templateData.templateDataZY.safeDate}</div>
                                <%--                            <input id="safeDate" name="safeDate"  data-id="10"  data-code="safeDate" placeholder=""  type="text" class="value-box"--%>
                                <%--                                   value="<fmt:formatDate value="${oprInfo.surveyRiskInfo.surveyRiskCase.insureTime}" pattern="yyyy年MM月dd日"/>"--%>
                                <%--                                   onclick="WdatePicker({dateFmt:'yyyy年MM月dd日'})" readonly>--%>
                                <%--                            <input class="value-box" type="text" data-id="10" placeholder="" value="${oprInfo.templateData.templateDataZY.safeDate}">--%>
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">出险人：</div>
                        <div class="value">
                            <div class="value-text report" data-id='11'>${oprInfo.templateData.templateDataZY.entrustUserName}</div>
                            <input class="value-box" type="text" data-id="11" data-code="upd-survey-person" placeholder="" value="${oprInfo.templateData.templateDataZY.entrustUserName}">
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">出险时间：</div>
                        <div class="value">
                            <div class="value-text-date report-date" data-id='12' data-code="upd-danger-time" onclick="WdatePicker({dateFmt:'yyyy年MM月dd日',onpicked:pickerEd})">${oprInfo.templateData.templateDataZY.safeUserDate}</div>
                                <%--                            <input id="surveyUserDate" name="surveyUserDate"  data-id="12"  data-code="surveyUserDate" placeholder=""  type="text" class="value-box"--%>
                                <%--                                   value="<fmt:formatDate value="${oprInfo.surveyRiskInfo.surveyRiskCase.dangerTime}" pattern="yyyy年MM月dd日"/>"--%>
                                <%--                                   onclick="WdatePicker({dateFmt:'yyyy年MM月dd日'})" readonly>--%>
                                <%--                            <input class="value-box" type="text" data-id="12" placeholder="" value="${oprInfo.templateData.templateDataZY.surveyUserDate}">--%>
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">出险地点：</div>
                        <div class="value">
                            <div class="value-text report" data-id='13'>${oprInfo.templateData.templateDataZY.safeUserAddress}
                            </div>
                            <input class="value-box" type="text" data-id="13" data-code="upd-danger-address" placeholder=""
                                   value="${oprInfo.templateData.templateDataZY.safeUserAddress}">
                        </div>
                    </div>
                    <div class='cell-title'>二、委托事项</div>
                    <div class="cell">
                        <div class="value">
                            <div class="value-text report" data-id='14'>${oprInfo.templateData.templateDataZY.surveyItem}</div>
                            <textarea class="value-box" name="" data-id="14" data-code="upd-survey-item" cols="30" rows="9">${oprInfo.templateData.templateDataZY.surveyItem}</textarea>
                        </div>
                    </div>
                    <div class='cell-title'>三、事故经过</div>
                    <div class="cell">
                        <div class="value">
                            <div class="value-text report" data-id='15'>${oprInfo.templateData.templateDataZY.accidentInfo}</div>
                            <textarea class="value-box" name="" data-id="15" data-code="upd-survey-info" cols="30" rows="9">${oprInfo.templateData.templateDataZY.accidentInfo}</textarea>
                        </div>
                    </div>
                    <div class='cell-title'>四、被保人基本信息：</div>
                    <div class="cell">
                        <div class="value inline">
                            被保险人
                            <div class="value-text report" data-id='16'>${oprInfo.templateData.templateDataZY.entrustUserName}</div>,
                            <input class="value-box" type="text" data-id="16" data-code="upd-survey-person" placeholder="" value="${oprInfo.templateData.templateDataZY.entrustUserName}">
                            <div class="value-text report" data-id='17'>${oprInfo.templateData.templateDataZY.sexStr}</div>,
                            <input class="value-box" type="text" data-id="17" data-code="sexStr" placeholder="" value="${oprInfo.templateData.templateDataZY.sexStr}">
                            <select class="value-box form-control" data-id="17" data-code="upd-sex" placeholder="" >
                                <option value="男" <c:if test="${oprInfo.templateData.templateDataZY.sexStr == '男'}">selected</c:if> >男</option>
                                <option value="女" <c:if test="${oprInfo.templateData.templateDataZY.sexStr == '女'}">selected</c:if> >女</option>
                            </select>
                            <div class="value-text report" data-id='18'>${oprInfo.templateData.templateDataZY.nation}</div>,
                            <input class="value-box" type="number" data-id="18" data-code="upd-nation" placeholder="" value="${oprInfo.templateData.templateDataZY.age}">
                            身份证号：
                            <div class="value-text report" data-id="19">${oprInfo.templateData.templateDataZY.idNumber}</div>
                            <input class="value-box" type="text" data-id="19" data-code="upd-id-number" placeholder="" value="${oprInfo.templateData.templateDataZY.idNumber}">
                            。
                        </div>
                    </div>
                    <div class='cell-title'>五、调查方向</div>
                    <div class="cell cell2">
                        <c:forEach items="${oprInfo.directions}" var="item" varStatus="st">
                            <div class="value-dcfx">
                                <div class="value-text report3" data-id="${st.index + 1}">${st.index + 1}、${item.directionName};</div>
                                <textarea class="value-box3" name="" data-id="${st.index + 1}" data-code="upd-direction-name" data-direction-id="${item.id}" cols="30" rows="9">${item.directionName}</textarea>
                            </div>
                        </c:forEach>
                    </div>
                    <div class='cell-title'>六、调查情况</div>
                    <c:forEach items="${oprInfo.directions}" var="item" varStatus="st">
                        <div class="cell cell2">
                            <div class="value2">
                                <div class="value-text report4 blod" data-id='${st.index + 1}'>${item.directionName}</div>
                                <input class="value-box4" type="text" data-id="${st.index + 1}" data-code="upd-direction-name" data-direction-id="${item.id}" placeholder="" value="${item.directionName}">
                            </div>
                        </div>
                        <div class="cell cell2">
                            <div class="value">
                                <div class="value-text report4" data-id='${oprInfo.directions.size() + st.index + 1}'>${item.directionInfo}</div>
                                <textarea class="value-box4" name="" data-id="${oprInfo.directions.size() + st.index + 1}" data-code="upd-direction-info" data-direction-id="${item.id}" cols="30" rows="9">${item.directionInfo}</textarea>
                            </div>
                        </div>
                    </c:forEach>
                    <div class='cell-title'>七、调查结论</div>
                    <div class="cell">
                        <div class="value">
                            <div class="value-text report" data-id='20'>${oprInfo.templateData.templateDataZY.directionResult}</div>
                            <textarea class="value-box" name="" data-id="20" data-code="upd-report-completion" cols="30" rows="9">${oprInfo.templateData.templateDataZY.directionResult}</textarea>
                        </div>
                    </div>
                    <div class='cell-title'>八、调查依据</div>
                    <div class="cell">
                        <div class="value">
                            <div class="value-text report-read" data-id='21'>${oprInfo.templateData.templateDataZY.directionBasis}</div>
                            <textarea class="value-box" name="" data-id="21" cols="30" rows="9">${oprInfo.templateData.templateDataZY.directionBasis}</textarea>
                        </div>
                    </div>
                    <div class='cell-title'>九、说明事项</div>
                    <div class="info">
                        1、保险合同各方对所提供资料真实性、完整性、合法性及其产生的后果负责
                    </div>
                    <div class="info">
                        2、本报告仅对本次委托有效，不作它用。未经我公司同意，不得向委托方和当事人之外的任何单位和个人提供调查报告书的全部或部分内容，亦不得公开发表，否则，将承担相应的法律责任。
                    </div>
                    <div class="info">
                        3、如发现本报告内的文字、数字因校印或其他原因出现误差，请委托人通知我们更正。
                    </div>
                    <div class="info-bot">（以下无正文）</div>

                    <div class='info-sign'>
                        调查员：
                        <div class="value-text report-read" data-id='22'>${oprInfo.templateData.templateDataZY.names}</div>
                        <input class="value-box" type="text" data-id="22" placeholder="" value="${oprInfo.templateData.templateDataZY.names}">
                    </div>
                    <div class='info-sign'>
                        签发人:
                        <div class="value-text report-read" data-id='23'>${oprInfo.templateData.templateDataZY.qfUserName}</div>
                        <input class="value-box" type="text" data-id="23" placeholder="" value="${oprInfo.templateData.templateDataZY.qfUserName}">
                    </div>
                    <div class='info-sign'>
                        <div class="value-text report-read" data-id='24'>${oprInfo.templateData.templateDataZY.trusteeOrgName}</div>
                        <input class="value-box" type="text" data-id="24" placeholder="" value="${oprInfo.templateData.templateDataZY.trusteeOrgName}">
                    </div>
                    <div class='info-sign'>
                        <div class="value-text report-read" data-id='25'>${oprInfo.templateData.templateDataZY.datestr}</div>
                        <input class="value-box" type="text" data-id="25" placeholder="" value="${oprInfo.templateData.templateDataZY.datestr}">
                    </div>
                </div>
            </div>
        </c:if>
    <%--    中德--%>
        <c:if test="${oprInfo.templateData.model.id == 3}">
            <div class="body-right body-right-albx">
                <div>
                    <div class="body-right-title">调查报告</div>
                    <c:if test="${btnCode == 'org5'}">
                        <div class="body-right-update">
                            <div class="body-right-update-name">审核意见</div>
                            <textarea class="body-right-update-t" name="orgOprOpinion" id="orgOprOpinion"  cols="30" rows="3"  placeholder="请输入审核意见，若无意见可不填..."></textarea>
                        </div>
                    </c:if>
                    <c:if test="${btnCode != 'org5' && oprInfo.surveyRiskInfo.orgOprOpinion != null}">
                        <div class="body-right-update">
                            <div class="body-right-update-name">主机构审核意见</div>
                            <textarea class="body-right-update-t" name="" cols="30" rows="3" readonly>${oprInfo.surveyRiskInfo.orgOprOpinion}</textarea>
                        </div>
                    </c:if>
                </div>
                <div class="table">
                    <div class="b_title">调查报告书 </div>
                    <div class="b_tr">
                        <div class="b_td">调查公司:
                            <div class="report-read" data-id="1">${oprInfo.templateData.templateDataZD.trusteeOrgName}</div>
                            <input class="value-box" type="text" data-id="1" placeholder="" value="${oprInfo.templateData.templateDataZD.trusteeOrgName}"></div>
                    </div>
                    <div class="b_tr">
                        <div class="b_td b_wid1">受托日期: <div class="report-read" data-id="2" data-code="upd-entrust-time" onclick="WdatePicker({dateFmt:'yyyy年MM月dd日',onpicked:pickerEd})" >${oprInfo.templateData.templateDataZD.entrustDate}</div>
                                <%--                            <input class="value-box" type="text" data-id="2" data-code="upd-entrust-time"  placeholder="" value="${oprInfo.templateData.templateDataZD.entrustDate}">--%>
                        </div>
                        <div class="b_td b_wid2">调查完成日期: <div class="report-read" data-id="3" data-code="upd-lefan-report-date" onclick="WdatePicker({dateFmt:'yyyy年MM月dd日',onpicked:pickerEd})" >${oprInfo.templateData.templateDataZD.endDate}</div>
                                <%--                            <input class="value-box" type="text" data-id="3" data-code="upd-lefan-report-date" placeholder="" value="${oprInfo.templateData.templateDataZD.endDate}">--%>
                        </div>
                    </div>
                    <div class="b_tr">
                        <div class="b_td b_wid1">被保险人: <div class="report" data-id="4">${oprInfo.templateData.templateDataZD.entrustUserName}</div>
                            <input class="value-box" type="text" data-id="4" data-code="upd-survey-person" placeholder="" value="${oprInfo.templateData.templateDataZD.entrustUserName}"></div>
                        <div class="b_td b_wid1">身份证号: <div class="report"
                                                            data-id="5">${oprInfo.templateData.templateDataZD.idNumber}</div>
                            <input class="value-box" type="text" data-id="5" data-code="upd-id-number" placeholder="" value="${oprInfo.templateData.templateDataZD.idNumber}"></div>
                        <div class="b_td b_wid3">保单号: <div class="report" data-id="6">${oprInfo.templateData.templateDataZD.claimsNo}</div>
                            <input class="value-box" type="text" data-id="6" data-code="upd-policy-no" placeholder="" value="${oprInfo.templateData.templateDataZD.claimsNo}">
                        </div>
                    </div>
                    <div class="b_tr">
                        <div class="b_td b_wid1">保单生效日: <div class="report-read" data-id="7" data-code="upd-insure-take-time" onclick="WdatePicker({dateFmt:'yyyy年MM月dd日',onpicked:pickerEd})">${oprInfo.templateData.templateDataZD.safeDate}</div>
<%--                            <input class="value-box" type="text" data-id="7" placeholder="" value="${oprInfo.templateData.templateDataZD.safeDate}">--%>
                        </div>
                        <div class="b_td b_wid2">事故发生日: <div class="report-read" data-id="8" data-code="upd-danger-time" onclick="WdatePicker({dateFmt:'yyyy年MM月dd日',onpicked:pickerEd})">${oprInfo.templateData.templateDataZD.surveyUserDate}</div>
<%--                            <input class="value-box" type="text" data-id="8" placeholder="" value="${oprInfo.templateData.templateDataZD.surveyUserDate}">--%>
                        </div>
                    </div>
                    <div class="b_tr border-none">
                        <div class="b_td">调查要求: </div>
                    </div>
                    <div class="b_tr ">
                        <div class="b_td"><div class="report" data-id="9">${oprInfo.templateData.templateDataZD.surveyItem}</div>
                            <textarea class="value-box" name="" data-id="9" data-code="upd-survey-item" cols="30" rows="9">${oprInfo.templateData.templateDataZD.surveyItem}</textarea>
                        </div>
                    </div>
                    <div class="b_tr">
                        <div class="b_td b_td_title">实际调查列表 </div>
                    </div>
                    <div class="b_tr">
                        <div class="b_td b_wid1 b_td_center">调查时间 </div>
                        <div class="b_td b_wid1 b_td_center">调查地点</div>
                        <div class="b_td b_wid3 b_td_center">调查内容 </div>
                    </div>
                    <c:forEach items="${oprInfo.directions}" var="item" varStatus="st">
                        <div class="b_tr">
                            <div class="b_td b_wid1"><div class="report2-read" data-id="${st.index + 1}">${item.itemDateStr}</div>
                                <input class="value-box2" type="text" data-id="${st.index + 1}" placeholder="" value="${item.itemDateStr}"> </div>
                            <div class="b_td b_wid1"><div class="report2-read" data-id="${oprInfo.directions.size() + st.index + 1}">${item.directionName}</div>
                                <input class="value-box2" type="text" data-id="${oprInfo.directions.size() + st.index + 1}" placeholder="" value="${item.directionName}"></div>
                            <div class="b_td b_wid3"><div class="report2-read"
                                                          data-id="${oprInfo.directions.size() * 2 + st.index + 2}">${item.taskName}</div>
                                <textarea class="value-box2" name="" data-id="${oprInfo.directions.size() * 2 + st.index + 2}" data-code="upd-direction-name" data-direction-id="${item.id}" cols="30"
                                          rows="9">${item.taskName}</textarea>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="b_tr border-none">
                        <div class="b_td">调查经过及结果: </div>
                    </div>
                    <div class="b_tr ">
                        <div class="b_td">
                            <c:forEach items="${oprInfo.directions}" var="item" varStatus="st">
                                    <div class="report6-read" data-id="${st.index}">${st.index + 1}、${item.directionInfo}</div>
                                    <textarea class="value-box6" name="" data-id="${st.index}" cols="30" rows="9">${item.directionInfo}</textarea><br/>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="b_tr border-none">
                        <div class="b_td">调查结论: </div>
                    </div>
                    <div class="b_tr ">
                        <div class="b_td"><div class="report" data-id="11">${oprInfo.templateData.templateDataZD.directionResult}</div>
                            <textarea class="value-box" name="" data-id="11" data-code="upd-report-completion" cols="30" rows="9">${oprInfo.templateData.templateDataZD.directionResult}</textarea>
                        </div>
                    </div>

                    <div class="b_tr">
                        <div class="b_td b_wid4 b_td_center blue">调查地 </div>
                        <div class="b_td b_wid4 b_td_center blue">调查点</div>
                        <div class="b_td b_wid4 b_td_center blue">相应收费</div>
                            <%--                        <div class="b_td b_wid4 b_td_center blue">总收费</div>--%>
                    </div>
                    <c:forEach items="${oprInfo.directions}" var="item" varStatus="st">
                        <div class="b_tr">
                            <div class="b_td b_wid4"><div class="report5-read" data-id="${st.index + 1}">${item.cityStr}</div>
                                <input class="value-box5" type="text" data-id="${st.index + 1}" placeholder="" value="${item.cityStr}"> </div>
                            <div class="b_td b_wid4"><div class="report5-read" data-id="${oprInfo.directions.size() + st.index + 1}">${item.directionName}</div>
                                <input class="value-box5" type="text" data-id="${oprInfo.directions.size() + st.index + 1}" placeholder="" value="${item.directionName}"></div>
                            <div class="b_td b_wid4"><div class="report5-read" data-id="${oprInfo.directions.size() * 2 + st.index + 2}">
                                <c:if test="${btnCode == 'org5'}">****</c:if><c:if test="${btnCode != 'org5'}">${item.money1}</c:if></div>
                                <input class="value-box5" type="text" data-id="${oprInfo.directions.size() * 2 + st.index + 2}" placeholder="" value="${item.money1}"> </div>
                                <%--                            <div class="b_td b_wid4"><div class="report5" data-id="${oprInfo.directions.size() * 3 + st.index + 3}">${oprInfo.templateData.templateDataZD}</div>--%>
                                <%--                                <input class="value-box5" type="text" data-id="${oprInfo.directions.size() * 3 + st.index + 3}" placeholder="" value="${oprInfo.templateData.templateDataZD}"></div>--%>
                        </div>
                    </c:forEach>
                    <div class="b_tr">
                        <div class="b_td">总收费: <div class="report-read" data-id="11.5"><c:if test="${btnCode == 'org5'}">****</c:if><c:if test="${btnCode != 'org5'}">${oprInfo.templateData.templateDataZD.totalMoney}</c:if></div>
                            <input class="value-box" type="text" data-id="11.5" placeholder="" value="${oprInfo.templateData.templateDataZD.totalMoney}">
                        </div>
                    </div>
                    <div class="b_tr">
                        <div class="b_td">附件内容及页数: <div class="report-read" data-id="12">${oprInfo.templateData.templateDataZD.fileMidInfo}</div>
                            <textarea class="value-box" name="" data-id="12" cols="30" rows="9">${oprInfo.templateData.templateDataZD.fileMidInfo}</textarea>

                        </div>
                    </div>

                    <div class="b_tr">
                        <div class="b_td">调查人签名: <div class="report-read" data-id="13">${oprInfo.templateData.templateDataZD.surveyUserName}</div>
                            <input class="value-box" type="text" data-id="13" placeholder="" value="${oprInfo.templateData.templateDataZD.surveyUserName}">
                        </div>
                        <div class="b_td">日期: <div class="report-read" data-id="14">${oprInfo.templateData.templateDataZD.userSignDate}</div>
                            <input class="value-box" type="text" data-id="14" placeholder="" value="${oprInfo.templateData.templateDataZD.userSignDate}">
                        </div>
                    </div>
                    <div class="b_tr">
                        <div class="b_td">调查主管确认签名: <div class="report-read" data-id="15">${oprInfo.templateData.templateDataZD.surveyManagerName}</div>
                            <input class="value-box" type="text" data-id="15" placeholder="" value="${oprInfo.templateData.templateDataZD.surveyManagerName}">
                        </div>
                        <div class="b_td">日期: <div class="report-read" data-id="16">${oprInfo.templateData.templateDataZD.managerSignDate}</div>
                            <input class="value-box" type="text" data-id="16" placeholder="" value="${oprInfo.templateData.templateDataZD.managerSignDate}">
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    <%--    中宏--%>
        <c:if test="${oprInfo.templateData.model.id == 4}">
            <div class="body-right body-right-zh">
                <div>
                    <div class="body-right-title">调查报告</div>
                    <c:if test="${btnCode == 'org5'}">
                        <div class="body-right-update">
                            <div class="body-right-update-name">审核意见</div>
                            <textarea class="body-right-update-t" name="orgOprOpinion" id="orgOprOpinion"  cols="30" rows="3"  placeholder="请输入审核意见，若无意见可不填..."></textarea>
                        </div>
                    </c:if>
                    <c:if test="${btnCode != 'org5' && oprInfo.surveyRiskInfo.orgOprOpinion != null}">
                        <div class="body-right-update">
                            <div class="body-right-update-name">主机构审核意见</div>
                            <textarea class="body-right-update-t" name="" cols="30" rows="3" readonly>${oprInfo.surveyRiskInfo.orgOprOpinion}</textarea>
                        </div>
                    </c:if>
                </div>
                <div class="form">
                    <div class="top-right">
                        <div class="city">${oprInfo.templateData.templateDataZH.annexCom}</div>
                        <input class="city-box" type="text" data-id="city" placeholder="" value="">
                    </div>
                    <div class="cell">
                        <img style="height: 100px;" src="${ctx}/img/report/zhbx.png" alt="">
                    </div>
                    <div class="cell">
                        <div class="img-text">调查报告书</div>
                    </div>
                    <div class="cell wid50">
                        <div class="label">保单号：</div>
                        <div class="value">
                            <div class="value-text report" data-id='1'>${oprInfo.templateData.templateDataZH.policyNo}</div>
                            <input class="value-box" type="text" data-id="1" data-code="upd-policy-no" placeholder="" value="${oprInfo.templateData.templateDataZH.policyNo}">
                        </div>
                    </div>
                    <div class="cell wid50">
                        <div class="label">委托方：</div>
                        <div class="value">
                            <div class="value-text report-read" data-id='2'>${oprInfo.templateData.templateDataZH.entrustOrgName}</div>
                            <input class="value-box" type="text" data-id="2" placeholder="" value="${oprInfo.templateData.templateDataZH.entrustOrgName}">
                        </div>
                    </div>
                    <div class="cell wid50">
                        <div class="label">受托方：</div>
                        <div class="value">
                            <div class="value-text report-read" data-id='3'>${oprInfo.templateData.templateDataZH.trusteeOrgName}</div>
                            <input class="value-box" type="text" data-id="3" placeholder="" value="${oprInfo.templateData.templateDataZH.trusteeOrgName}">
                        </div>
                    </div>
                    <div class="cell wid50">
                        <div class="label">委托人：</div>
                        <div class="value">
                            <div class="value-text report-read" data-id='4'>${oprInfo.templateData.templateDataZH.entrustUserName}</div>
                            <input class="value-box" type="text" data-id="4" placeholder="" value="${oprInfo.templateData.templateDataZH.entrustUserName}">
                        </div>
                    </div>

                    <div class="cell wid50">
                        <div class="label">委托日：</div>
                        <div class="value">
                            <div class="value-text-date report-date" data-id='5' data-code="upd-entrust-time" onclick="WdatePicker({dateFmt:'yyyy年MM月dd日',onpicked:pickerEd})">${oprInfo.templateData.templateDataZH.entrustDate}</div>
<%--                            <input class="value-box" type="text" data-id="5" placeholder="" value="2019年05月2日">--%>
                        </div>
                    </div>
                    <div class="cell wid50">
                        <div class="label">结案日：</div>
                        <div class="value">
                            <div class="value-text-date report-date" data-id='6' data-code="upd-entrust-report-start-date" onclick="WdatePicker({dateFmt:'yyyy年MM月dd日',onpicked:pickerEd})">${oprInfo.templateData.templateDataZH.endDate}</div>
<%--                            <input class="value-box" type="text" data-id="6" placeholder="" value="${oprInfo.templateData.templateDataZH.policyNo}">--%>
                        </div>
                    </div>
                    <div class="cell wid50">
                        <div class="label">合同号：</div>
                        <div class="value">
                            <div class="value-text report" data-id='7'>${oprInfo.templateData.templateDataZH.claimsNo}</div>
                            <input class="value-box" type="text" data-id="7" data-code="upd-claims-no" placeholder="" value="${oprInfo.templateData.templateDataZH.claimsNo}">
                        </div>
                    </div>
                    <div class="cell wid50">
                        <div class="label">投保日期：</div>
                        <div class="value">
                            <div class="value-text-date report-date" data-id='55' data-code="upd-insure-time" onclick="WdatePicker({dateFmt:'yyyy年MM月dd日',onpicked:pickerEd})">${oprInfo.templateData.templateDataZH.safeDate}</div>
                                <%--                            <input class="value-box" type="text" data-id="5" placeholder="" value="2019年05月2日">--%>
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">被调查人：</div>
                        <div class="value">
                            <div class="value-text report" data-id='8'>${oprInfo.templateData.templateDataZH.surveyPerson}</div>
                            <input class="value-box" type="text" data-id="8" data-code="upd-survey-person" placeholder="" value="${oprInfo.templateData.templateDataZH.surveyPerson}">
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">案由：</div>
                        <div class="value">
                            <div class="value-text report" data-id='9'>${oprInfo.templateData.templateDataZH.surveyReason}
                            </div>
                            <textarea class="value-box" name="" data-id="9" data-code="upd-survey-info" cols="30"
                                      rows="9">${oprInfo.templateData.templateDataZH.surveyReason}</textarea>
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">调查要求：</div>
                        <div class="value">
                            <div class="value-text report" data-id='10'>${oprInfo.templateData.templateDataZH.surveyDemand}</div>
                            <textarea class="value-box" name="" data-id="10" data-code="upd-survey-item" cols="30" rows="9">${oprInfo.templateData.templateDataZH.surveyDemand}</textarea>

                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">调查地点：</div>
                        <div class="value">
                            <div class="value-text report-read" data-id="zh-dirction-name-${st.index + 1}">
                                    ${oprInfo.templateData.templateDataZH.directionName}
                            </div>
                            <textarea class="value-box direction" name="" data-id="zh-dirction-name-${st.index + 1}" data-code="upd-direction-name" data-direction-id="${item.id}" data-id='11' cols="30"
                                      rows="9">${oprInfo.templateData.templateDataZH.directionName}</textarea>

                        </div>
                    </div>
                    <div class="cell">
                        <div class="label">调查经过及内容：</div>
                    </div>
                    <c:forEach items="${oprInfo.directions}" var="item" varStatus="st">
                        <div class="cell">
                            <div class="value value-text-s"><div class="value-text report" data-id='zh-dirction-info-${st.index + 1}'>${st.index + 1}、${item.directionInfo}</div>
                                <textarea class="value-box direction" name="" data-id="zh-dirction-info-${st.index + 1}" data-index="${st.index + 1}" data-code="upd-direction-info" data-direction-id="${item.id}" cols="30" rows="9">${item.directionInfo}</textarea>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="cell">
                        <div class="label">调查结论：</div>
                    </div>
                    <div class="cell">
                        <div class="value value-text-s">
                            <div class="value-text report" data-id='13'>
                                    ${oprInfo.templateData.templateDataZH.directionResult}
                            </div>
                            <!-- <input class="value-box" type="text" data-id="12" placeholder="" value="调查经过及内容"> -->
                            <textarea class="value-box" name="" data-id="13" data-code="upd-report-completion" cols="30" rows="9"
                                      value="">${oprInfo.templateData.templateDataZH.directionResult}</textarea>

                        </div>
                    </div>
                    <div class="cell ">
                        <div class="label">介绍信使用情况：</div>
                        <div class="value">
                            <div class="value-text report-read" data-id='14'>${oprInfo.templateData.templateDataZH.isUseLetter}</div>
                            <input class="value-box" type="text" data-id="14" data-code="upd-claims-no" placeholder="" value="${oprInfo.templateData.templateDataZH.isUseLetter}">
                        </div>
                    </div>
                    <div class="cell ">
                        <div class="label">内容材料：</div>
                        <div class="value">
                            <div class="value-text report-read" data-id='15'>${oprInfo.templateData.templateDataZH.fileMidSize}</div>
                            <input class="value-box" type="text" data-id="15" data-code="upd-claims-no" placeholder="" value="${oprInfo.templateData.templateDataZH.fileMidSize}">
                        </div>
                    </div>
                    <div class="cell ">
                        <div class="label">调查点：</div>
                        <div class="value">
                            <div class="value-text report-read" data-id='16'>${oprInfo.templateData.templateDataZH.directionNameSize}</div>
                            <input class="value-box" type="text" data-id="16" data-code="upd-claims-no" placeholder="" value="${oprInfo.templateData.templateDataZH.directionNameSize}">
                        </div>
                    </div>
                    <div class="cell">
                        <div class="label" style="margin-left: 100px;">(以下空白)</div>
                    </div>
                    <div class="cell" style="flex-flow: row-reverse;">
                        <div class="value-sign">
                            <div class="label" data-id='17'>${oprInfo.templateData.templateDataZH.surveyUserName}/${oprInfo.templateData.templateDataZH.userSignDate}</div>
                        </div>
                    </div>
                    <div class="cell" style="flex-flow: row-reverse;">
                        <div class="value-sign">
                            <div class="label" data-id='17'>调查员签名/日期</div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </form>
</div>

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<%--<script type="text/javascript">--%>
<%--    var ctx="${ctx}";--%>
<%--</script>--%>
<%--<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>--%>
<%--<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>--%>

<script>
    function updDataInfo(surveyInfoId,btnCode,updCode,updValue,directionId){
        var url = "${ctx}/survey/case/operate";
        var param = {"id":surveyInfoId,"btnCode":btnCode,"updCode":updCode,"updValue":updValue,"directionId":directionId};
        ajaxSubmit(url,param,function(v,e,p){

        })
    }

    function view(obj){
        if($(obj).text() == '编辑'){
            $(".body-right-lf, .body-right-albx ,.body-right-zh").show();
            $(".word-pre").hide();
            $(obj).text("预览")
        }else{
            $(".body-right-lf, .body-right-albx ,.body-right-zh").hide();
            $(".word-pre").show();
            $(obj).text("编辑")
            reload();
        }
    }
    function pickerEd(){
        var _this = $(this);
        var surveyInfoId = $("#surveyInfoId").val();
        var updCode = _this.attr('data-code');
        var updValue = $dp.cal.getDateStr("yyyy-MM-dd HH:mm:ss");
        var url = "${ctx}/survey/case/operate";
        var param = {"id":surveyInfoId,"btnCode":"updInfoDate","updCode":updCode,"updValue":updValue,"directionId":null};
        ajaxSubmit(url,param,function(v,e,p){

        })
    }
    function showIndex(){
        $('.body-left').hide()
        $('.body-left-par').show()
    }
    // $("#editForm").bind('submit', function(event) {
    //     ajaxFormSubmit(this,reloadParent(),null,null,reload);
    //     // event.preventDefault();
    // });

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,returnCallback,null,null,returnCallback);
        event.preventDefault();
    });
    function returnCallback(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){
            // console.log(window.parent.parent.location)
            // window.parent.parent.reload();
        }else{
            alert(apiRsp.msg);return;
        }
    }

    function agreen(id,btnCode){
        // var height = $(document).outerHeight()*0.98;
        var height = $(document).outerHeight()*0.98,width = 900;
        var title = null,url = null;
        title = "操作";
        url = "${ctx}/survey/case/operateView?id=" + id + "&btnCode=" + btnCode + "&entrustTime=" + $("#entrustTime").val();
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:url,
            load:true
        });
    }
    function oprBack(id,btnCode){
        var height = 400,width = 900;
        var title = null,url = null;
        title = "退回";
        if(btnCode == 'org4'){
            url = "${ctx}/survey/case/back?id=" + id + "&btnCode=" + btnCode;
        }else{
            url = "${ctx}/survey/case/back?id=" + id + "&btnCode=1301";
        }
        openDialog({
            frame:true,
            title:title,
            height:height,
            width:width,
            url:url,
            load:true
        });
    }
    function submitForm(){
        if(confirm('是否确认？')){
            var url = "${ctx}/survey/case/operateAssign";
            var param = {"surveyInfoId":$("#surveyInfoId").val(),"btnCode":$("#btnCode").val(),"id":$("#id").val(),"orgOprOpinion":$("#orgOprOpinion").val()};
            ajaxSubmit(url,param,function(v,e,p){
                var closeBtn = $("#diglog_close_btn",window.parent.parent.document);//关闭双层父级弹窗
                closeBtn.click();
                window.parent.parent.reload();
            });

           //  $("#editForm").submit();
           // // reloadParent();
           //  var closeBtn = $("#diglog_close_btn",window.parent.parent.document);//关闭双层父级弹窗
           //  closeBtn.click();
            // window.parent.parent.reload();
            // reload();
        }
    }

    $(function () {
        $('.bar').on('dblclick', function () {
            var type = $(this).attr("data-type");
            var value = $(this).attr("data-value");
            var name = $(this).attr("data-name");
            var size = $(this).attr("data-size");
            $("#span_tip_1").html("材料附件");
            $("#span_tip_2").html(name + "(" + size + ")");
            $(".files").html("");
            var param = {"surveyInfoId":$("#surveyInfoId").val(),"catalogId":value==null?null:(value),"viewType":"treeClick"};
            if(type == 'directions'){
                $("#span_tip_1").html("方向附件");
                param = {"surveyInfoId":$("#surveyInfoId").val(),"directionId":value==null?null:(value),"viewType":"treeClick","type":"direction"};
            }
            ajaxSubmit("${ctx}/survey/case/fileMid",param,function(v,e,p){
                if(e.data.code=='0000'){
                    var files = "";
                    if(type == 'directions'){
                        files = e.data.results.directionFile;
                    }else{
                        files = e.data.results.surveyCaseFiles;
                    }
                    var content = "";
                    for(var i = 0; i < files.length; i++){
                        var json = files[i];
                        content += "<div class=\"file\" data-id=\"" + (i + 1)+ "\">\n" +
                            "                <img src=\"" + json.commonFile.filePath + "\" class=\"image\" data-id=\"" + (i + 1) + "\"></img>\n" +
                            "            </div>";
                    }
                    $(".files").html(content);

                    $('.files .image').on('click', function (e) {
                        $('.body-left').hide()
                        $('.body-left-detail').show()
                        var id = $(this).attr('data-id')
                        var url = $(this).attr('src')
                        $('.img-src').attr('data-id', id)
                        $('.img-src img').attr('src', url)
                        AutoSize(url)
                    });
                }else{

                }
            });
            $('.body-left').show()
            $('.body-left-par').hide()
        })

        $('.body-right-lf .report').on('click', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            _this.hide()
            $('.body-right-lf .value-box').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.css({
                        'display': 'inline-block'
                    }).focus()
                }
            })

        })
        $('.body-right-lf .value-box').on('blur', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            var val = _this.val()
            $('.body-right-lf .report').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.text(val).show()
                }
            })
            _this.hide();
            var code = _this.attr("data-code");
            updDataInfo($("#surveyInfoId").val(),'updInfoStr',code,val,null);
        })


        $('.body-right-lf .report3').on('click', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            _this.hide()
            $('.body-right-lf .value-box3').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.css({
                        'display': 'inline-block'
                    }).focus()
                }
            })
        })
        $('.body-right-lf .value-box3').on('blur', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            var val = _this.val()

            $('.body-right-lf .report3').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.text(val).show()
                }
            })
            _this.hide()
            var code = _this.attr("data-code");
            var directionId = _this.attr("data-direction-id");
            updDataInfo($("#surveyInfoId").val(),'updInfoStr',code,val,directionId);
        })

        $('.body-right-lf .report4').on('click', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            _this.hide()
            // $('.body-right-lf .value-box4').eq(Number(id - 1)).css({
            //     'display': 'inline-block'
            // }).focus()
            $('.body-right-lf .value-box4').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.css({
                        'display': 'inline-block'
                    }).focus()
                }
            })
        })
        $('.body-right-lf .value-box4').on('blur', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            var val = _this.val()

            $('.body-right-lf .report4').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.text(val).show()
                }
            })
            _this.hide()
            var code = _this.attr("data-code");
            var directionId = _this.attr("data-direction-id");
            updDataInfo($("#surveyInfoId").val(),'updInfoStr',code,val,directionId);
        })

        $('.body-right-zh .report').on('click', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            _this.hide()
            $('.body-right-zh .value-box').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.css({
                        'display': 'inline-block'
                    }).focus()
                }
            })
        })
        $('.body-right-zh .value-box').on('blur', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            var val = _this.val()
            $('.body-right-zh .report').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.text(val).show()
                }
            })
            _this.hide();
            var code = _this.attr("data-code");
            updDataInfo($("#surveyInfoId").val(),'updInfoStr',code,val,null);
        })

        $('.body-right-zh .value-box3').on('blur', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            var val = _this.val()

            $('.body-right-zh .report3').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.text(val).show()
                }
            })
            _this.hide()
            var code = _this.attr("data-code");
            var directionId = _this.attr("data-direction-id");
            updDataInfo($("#surveyInfoId").val(),'updInfoStr',code,val,directionId);
        })

        $('.body-right-albx .report').on('click', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            _this.hide()
            $('.body-right-albx .value-box').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.css({
                        'display': 'inline-block'
                    }).focus()
                }
            })
        })
        $('.body-right-albx .value-box').on('blur', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            var val = _this.val()
            $('.body-right-albx .report').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.text(val).show()
                }
            })
            _this.hide()
            var code = _this.attr("data-code");
            updDataInfo($("#surveyInfoId").val(),'updInfoStr',code,val,null);
        })

        $('.body-right-albx .report2').on('click', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            _this.hide()
            // $('.body-right-albx .value-box2').eq(Number(id - 1)).css({
            //     'display': 'inline-block'
            // }).focus()
            $('.body-right-albx .value-box2').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.css({
                        'display': 'inline-block'
                    }).focus()
                }
            })
        })
        $('.body-right-albx .value-box2').on('blur', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            var val = _this.val()
            // $('.body-right-albx .report2').eq(Number(id) - 1).text(val).show()
            $('.body-right-albx .report2').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.text(val).show()
                }
            })
            _this.hide()

            var code = _this.attr("data-code");
            var directionId = _this.attr("data-direction-id");
            updDataInfo($("#surveyInfoId").val(),'updInfoStr',code,val,directionId);
        })

        $('.body-right-albx .report5').on('click', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            _this.hide()
            // $('.body-right-albx .value-box5').eq(Number(id - 1)).css({
            //     'display': 'inline-block'
            // }).focus()
            $('.body-right-albx .value-box5').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.css({
                        'display': 'inline-block'
                    }).focus()
                }
            })
        })
        $('.body-right-albx .value-box5').on('blur', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            var val = _this.val()
            // $('.body-right-albx .report5').eq(Number(id) - 1).text(val).show()
            $('.body-right-albx .report5').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.text(val).show()
                }
            })
            _this.hide()
        })

        $('.body-right-albx .report6').on('click', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            _this.hide()
            // $('.body-right-albx .value-box5').eq(Number(id - 1)).css({
            //     'display': 'inline-block'
            // }).focus()
            $('.body-right-albx .value-box6').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.css({
                        'display': 'inline-block'
                    }).focus()
                }
            })
        })
        $('.body-right-albx .value-box6').on('blur', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            var val = _this.val()
            // $('.body-right-albx .report5').eq(Number(id) - 1).text(val).show()
            $('.body-right-albx .report6').each(function (e) {
                var _this = $(this)
                if (_this.attr('data-id') == id) {
                    _this.text(val).show()
                }
            })
            _this.hide()
        })




        $('.direction').on('blur', function () {
            var _this = $(this)
            var id = _this.attr('data-id')
            var val = _this.val()
            var dataIndex = _this.attr("data-index") ? (_this.attr("data-index") + "、") : "";
            $('.report[data-id='+id+']').text(dataIndex + val).show();
            _this.hide()
            var code = _this.attr("data-code");
            var directionId = _this.attr("data-direction-id");
            updDataInfo($("#surveyInfoId").val(),'updInfoStr',code,val,directionId);
        })



        var dNumber = 0
        var clientHeight = document.documentElement.clientHeight
        $('.contain').height(clientHeight - 20)
        $('.body-right').height((clientHeight - 20) * 0.96)
        $('.img-src').height(clientHeight - 100)
        $('.img-src .image').height(clientHeight - 100)
        $('.img-detail').height(clientHeight - 100)

        $(window).resize(function () {
            var clientHeight = document.documentElement.clientHeight
            $('.contain').height(clientHeight - 20)
            $('.body-right').height(clientHeight - 20)
            $('.img-src').height(clientHeight - 100)
            $('.img-src .image').height(clientHeight - 100)
            $('.img-detail').height(clientHeight - 100)
        })
        $('.files .image').each(function (i) {
            var url = $(this).attr('src')
            var width, height, arr = getNaturalWH(url)
            var _width = arr[0],
                _height = arr[1]
            if (1 <= _width / _height) {
                width = 148; //以框的宽度为标准
                height = 148 * (_height / _width);
            } else {
                width = 148 * (_width / _height);
                height = 148; //以框的高度为标准
            }
            var cssArr = {
                'width': width,
                'height': height
            }
            if (width == 148) {
                cssArr = {
                    'width': width,
                    'height': height,
                    'margin-top': (148 - height) / 2
                }
            }
            $(this).css(cssArr)
        })

        function getNaturalWH(src) {
            var image = new Image();
            image.src = src;
            return [image.width, image.height];
        }
        $('.img-pre').on('click', function (e) {
            var curIndex = Number($('.img-src').attr('data-id'))
            var $lis = $('.files .image')
            if (curIndex > 1) {
                var nextUrl = $lis.eq(curIndex - 2).attr('src')
                $('.img-src').attr('data-id', curIndex - 1)
                $('.img-src img').attr({
                    'src': nextUrl,
                    'style': ''
                })
                dNumber = 0
                AutoSize(nextUrl)
            }
            if (curIndex == 1) {
                alert('已经是第一张了')
            }
        })
        $('.img-next').on('click', function (e) {
            var curIndex = Number($('.img-src').attr('data-id'))
            var $lis = $('.files .image')
            if (curIndex != $lis.length) {
                var nextUrl = $lis.eq(curIndex).attr('src')
                $('.img-src').attr('data-id', curIndex + 1)
                $('.img-src img').attr({
                    'src': nextUrl,
                    'style': ''
                })
                dNumber = 0
                AutoSize(nextUrl)
            }
            if (curIndex == $lis.length) {
                alert('已经是最后一张了')
            }
        })
        $('.files .image').on('click', function (e) {
            $('.body-left').hide()
            $('.body-left-detail').show()
            var id = $(this).attr('data-id')
            var url = $(this).attr('src')
            $('.img-src').attr('data-id', id)
            $('.img-src img').attr('src', url)
            AutoSize(url)
        })
        $('.goback').on('click', function () {
            $('.body-left').show()
            $('.body-left-detail').hide()
            $('.img-src img').attr({
                'style': ''
            })
            dNumber = 0
        })
        $('.rolateleft').on('click', function () {
            dNumber = dNumber - 90
            $('.img-src img').attr({
                'style': ''
            })
            var url = $('.img-src img').attr('src')
            AutoSize(url)
            $('.img-src img').css({
                'transform': 'rotate(' + dNumber + 'deg)',
                '-ms-transform': 'rotate(' + dNumber + 'deg)',
                '-webkit-transform': 'rotate(' + dNumber + 'deg)',
            })

        })
        $('.rolateright').on('click', function () {
            dNumber = dNumber + 90
            $('.img-src img').attr({
                'style': ''
            })
            var url = $('.img-src img').attr('src')
            AutoSize(url)
            $('.img-src img').css({
                'transform': 'rotate(' + dNumber + 'deg)',
                '-ms-transform': 'rotate(' + dNumber + 'deg)',
                '-webkit-transform': 'rotate(' + dNumber + 'deg)',
            })
        })

        function AutoSize(src) {
            var maxWidth = $('.img-src').width(),
                maxHeight = $('.img-src').height(),
                width, height, res_height, res_width
            var image = new Image();
            image.src = src;
            if (image.width < maxWidth && image.height < maxHeight) {
                width = image.width;
                height = image.height;
            } else //原图片宽高比例 大于 图片框宽高比例,则以框的宽为标准缩放，反之以框的高为标准缩放
            {
                if (dNumber % 180 != 0) {
                    if (maxWidth / maxHeight <= image.height / image.width) //原图片宽高比例 大于 图片框宽高比例
                    {
                        height = maxWidth; //以框的宽度为标准
                        width = maxWidth * (image.width / image.height);
                        if (width > height) {
                            $('.img-src img').css('margin-left', (maxWidth - width) / 2)
                        }
                    } else { //原图片宽高比例 小于 图片框宽高比例
                        height = maxHeight * (image.height / image.width);
                        width = maxHeight; //以框的高度为标准
                        $('.img-src img').css('margin-left', -(maxHeight - height) / 2 + (
                            maxWidth -
                            height) /
                            2)
                    }

                } else {
                    if (maxWidth / maxHeight <= image.width / image.height) //原图片宽高比例 大于 图片框宽高比例
                    {
                        width = maxWidth; //以框的宽度为标准
                        height = maxWidth * (image.height / image.width);
                        // $('.img-src img').css({
                        //     'margin-left': -(maxHeight - height) / 2 + (maxWidth - height) / 2,
                        // })
                    } else { //原图片宽高比例 小于 图片框宽高比例
                        width = maxHeight * (image.width / image.height);
                        height = maxHeight; //以框的高度为标准
                    }
                }
            }

            if (height < maxHeight) {
                $('.img-src img').css({
                    'margin-top': (maxHeight - height) / 2,
                })
            } else {
                $('.img-src img').css({
                    'margin-top': 0,
                })
            }

            $('.img-src img').css({
                'width': width,
                'height': height
            })
        }
    })
</script>
</body>

</html>