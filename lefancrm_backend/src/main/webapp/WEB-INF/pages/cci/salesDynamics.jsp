<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>客户信息列表</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<style>
    @charset "utf-8";
    body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, form, fieldset, input, textarea, p, blockquote, th, td, font { padding:0; margin: 0; font-family: "宋体"; font-size:14px; }
    .salse{
        background-color: #f0f0f2;
        padding: 12px;
        overflow: hidden;
    }
    .salse_border{
        border-left:1px solid #ccc;
        padding-left: 38px;
    }
    .salse .salse_cont{
        border: 1px solid #ccc;
        padding: 15px 8px;
        background-color: #fff;
        width: 400px;
        color: #282828;
        border-radius: 5px;
        position: relative;
        z-index: 1;
        margin-bottom: 20px;
    }
    .arrow {
        width:0;
        height:0;
        font-size:0;
        border:solid 10px;
        border-color:#f0f0f2 #fff #f0f0f2 #f0f0f2;
        position: absolute;
        z-index: 2;
        top:32px;
        left: -20px;
    }
    .salse .salse_cont h2{
        font-weight: normal;
        font-size: 16px;
    }
    .salse .salse_cont span{
        font-size: 14px;
    }
    .salse .salse_cont p{
        margin-top: 25px;
    }
    .salse_add{
        margin: 30px;
        float: right;
    }
    .cricle_w{
        width: 18px;
        height: 18px;
        border-radius: 18px;
        padding: 4px;
        background-color: #624844;
        position: absolute;
        z-index: 2;
        top: 30px;
        left: -52px;
    }
    .cricle_in1{
        width: 10px;
        height: 10px;
        border-radius: 10px;
        padding: 4px;
        background-color: #f0f0f2;
    }
    .cricle_in2{
        width: 2px;
        height: 2px;
        border-radius: 2px;
        padding: 4px;
        background-color: #624844;
    }
</style>
<body>

<div class="main administrator">
    <div class="salse">
        <img src="${ctx}/img/salse_add.png" onclick="add()" class="salse_add">
        <div class="salse_border">
<c:forEach items="${apiRsp.results}" var="item">
            <div class="salse_cont">
                <h2>${item.customerName} &rsaquo;&rsaquo; <span>
                    <c:if test="${item.followType==1}">电话</c:if>
                    <c:if test="${item.followType==2}">qq/微信</c:if>
                    <c:if test="${item.followType==3}">拜访</c:if>
                    <c:if test="${item.followType==4}">其他</c:if>
                </span></h2>
                <p>
                        ${item.followDesc}
                </p>
                <p>跟进时间 <fmt:formatDate value="${item.followTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                <div class="cricle_w">
                    <div class="cricle_in1">
                        <div class="cricle_in2">
                        </div>
                    </div>
                </div>
                <div class="arrow"></div>
            </div>

</c:forEach>
        </div>

    </div>
</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
   /* var commentDelete = function(commentid){
        ajaxSubmit("${ctx}/comment/delete?id="+commentid,{},function(){location.reload();},"删除成功","确认此条留言删除吗？",null);
    }*/
  /*  var commentDetail = function(commentid){
        openDialog({
            frame:true,
            title:"查看留言信息",
            height:600,
            width:500,
            url:"${ctx}/comment/detail?id="+commentid
        });
    }*/

   function add(a){
       openDialog({
           frame:true,
           title:"添加销售动态",
           height:400,
           width:1000,
           url:"${ctx}/cci/salesDynamicsAdd?customerId=${customerId}"
       });
   }
</script>
</body>
</html>
