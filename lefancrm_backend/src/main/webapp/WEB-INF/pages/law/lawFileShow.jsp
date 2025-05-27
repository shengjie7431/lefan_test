<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>相册</title>
    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="c_body">
    <!--轮播过程-->
    <div class="detail_context_pic">
        <div class="detail_context_pic_top">
            <!-- <div class="imgContainer"><img src="" alt="" id="pic1" curindex="0" /></div> -->
            <img src="" alt="" id="pic1" curindex="0" />
            <a id="preArrow" href="javascript:void(0)" class="contextDiv" title="上一张"><span id="preArrow_A"></span></a>
            <a id="nextArrow" href="javascript:void(0)" class="contextDiv" title="下一张"><span id="nextArrow_A"></span></a>
            <div class="tools">
                <a href="javascript:;" class="rotateAW" title ="逆时针旋转90°"></a>
                <a href="javascript:;" class="rotateCW" title ="顺时针旋转90°"></a>
                <a href="javascript:;" class="magnify" title ="放大"></a>
            </div>
        </div>
        <!--图片轮播-->
        <div class="detail_context_pic_bot">
            <div class="detail_picbot_left"> <a href="javascript:void(0)" id="preArrow_B"><img src="${ctx}/caseMid/img/left1.jpg" title="上一个" /></a> </div>
            <div class="detail_picbot_mid">
                <ul>
                    <c:forEach items="${caseFile}" var="file">
                        <li>
                            <a href='javascript:void(0);'>
                                <c:if test="${file.documentType == 1}">
                                    <img src='../img/pdf.jpg' width='90px' height='60px'  bigimg='../img/pdf.jpg' />
                                </c:if>
                                <c:if test="${file.documentType == 2}">
                                    <img src='../img/word.png' width='90px' height='60px'  bigimg='../img/word.png' />
                                </c:if>
                                <c:if test="${file.documentType == 3}">
                                    <img src='${file.commonFile.filePath}' width='90px' height='60px'  bigimg='${file.commonFile.filePath}' />
                                </c:if>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="detail_picbot_right"> <a href="javascript:void(0)" id="nextArrow_B"><img src="${ctx}/caseMid/img/right1.jpg" title="下一个" /></a> </div>
        </div>
    </div>
    <!--图片轮换结束-->
</div>
<div id="pic2">
    <!-- <div class="p_bg"></div> -->
    <div class="p_content">
        <div class="p_img">
            <img src=""  id="pic2_img" data-flag="" curindex=""/>
        </div>
        <div class="toolsMax">
            <a href="javascript:;" class="icon-to-big" title ="放大"></a>
            <a href="javascript:;" class="icon-to-small" title ="缩小"></a>
            <a href="javascript:;" class="p_restore" title ="还原"></a>
            <a href="javascript:;" class="rotateAW" title ="逆时针旋转90°"></a>
            <a href="javascript:;" class="rotateCW" title ="顺时针旋转90°"></a>
            <a class="icon-to-close" title ="关闭"></a>
        </div>
    </div>
</div>
<script src="${ctx}/caseMid/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctx}/caseMid/js/xiangce.js" type="text/javascript"></script>
</body>
</html>
