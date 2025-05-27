<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">
    <style>
        .poi-no {
            pointer-events: none;
        }
        .layui-form-item{
            width: 58%;
            min-width: 520px;
            margin: 0 auto;
            margin-bottom: 15px;
        }
        .layui-input-inline{
            width: 400px!important;
        }
    </style>
</head>
<body>
<div class="layui-form" style="margin-top: 30px">
    <input hidden id="id" value="${surveyConsignor.id}">
    <div class="layui-form-item">
        <label class="layui-form-label">收件人</label>
        <div class="layui-input-inline _input">
            <input type="text" name="receiver" value="${surveyConsignor.receiver}" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">

        <label class="layui-form-label">收件人电话</label>
        <div class="layui-input-inline _input">
            <input type="number" name="receiverTel" value="${surveyConsignor.receiverTel}" autocomplete="off"
                   class="layui-input">
        </div>

    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">收件人地址</label>
        <div class="layui-input-inline _input">
            <textarea type="text" name="receiverAddress" autocomplete="off"
                      class="layui-input">${surveyConsignor.receiverAddress}</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-primary close-reason">取消</button>
            <button class="layui-btn layui-btn-normal submit-reason">保存并关闭</button>
        </div>
    </div>
</div>
<script src="${ctx}/js/jquery-3.4.1.js"></script>
<script src="${ctx}/js/layui/layui.js"></script>

<script>
    layui.use(['jquery', 'layer'], function () {
        var $ = jQuery = layui.$
        var layer = layui.layer

        $('.submit-reason').click(function () {
            $('.submit-reason').addClass('poi-no')
            var param = {
                id:$("#id").val(),
                receiver:$("input[name=receiver]").val(),
                receiverTel:$("input[name=receiverTel]").val(),
                receiverAddress: $("textarea[name=receiverAddress]").val(),
                btnCode:"6000",
                surveyCode:"consignor"
            }
            $.ajax({
                url: "${ctx}/baseSurvey/operate",
                type: "post",
                data: param,
                success: function (res) {
                    res = JSON.parse(res)
                    $('.submit-reason').removeClass('poi-no')
                    if (res.isSuccess) {
                        parent.location.reload();
                    }
                }
            });
        })
        $('.close-reason').click(function () {
            closeDialog()
        })
        function closeDialog(){
            var closeBtn = $("#diglog_close_btn",window.parent.document);
            closeBtn.click();
        }
    })
</script>
</body>
</html>
