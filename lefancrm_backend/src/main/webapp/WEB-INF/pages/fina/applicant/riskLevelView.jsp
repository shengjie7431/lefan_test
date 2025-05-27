<%--
  Created by IntelliJ IDEA.
  User: zhuxia
  Date: 2021/1/4
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<html>
<head>
<head>
    <title>文字识别</title>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/css/viewer.min.css">
    <style>
        .main {
            width: 100%;
            height: 100%;
        }

        .content {
            display: flex;
            justify-content: space-around;
            width: 98%;
            height: 100%;
        }

        .content-left {
            width: 49%;
            height: 100%;
            overflow: auto;
        }

        .content-right {
            width: 49%;
            height: 100%;
            overflow: auto;
            background-color: #eee;
        }

        .lf-none{
            width: 100%;
            color: #666;
            padding: 10px 0 ;
        }


    </style>
</head>
<body>
<input type="hidden" value='${allEnumJson}' name="allEnum" id="allEnum" />
<input type="hidden" value='${finaApplicantFilesJson}' name="finaApplicantFiles" id="finaApplicantFiles" />
<div class="main">
    <div class="content">
        <div class="content-left">
            <iframe src="${ctx}/fina/applicant/operateView?btnCode=risk-level-view-left-info&finaInfoId=${finaInfoId}" frameborder="0" width="100%" height="100%"></iframe>
        </div>
        <div class="content-right">
            <div class="png-text">
                <div class="lf-none">暂无数据</div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
