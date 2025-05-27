<%--
  Created by IntelliJ IDEA.
  User: lixianfeng
  Date: 2019/10/29
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
<!DOCTYPE html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <style>
        .main {
            width: 40%;
            margin: 0 auto;
            font-size: 16px;
        }

        .form {
            width: 94%;
            padding: 20px 3%;
            border: 1px solid #bbb;
            margin: 50px 0;
        }

        .cell {
            width: 100%;
            display: flex;
            border-bottom: 1px solid #d0c9c9;
        }

        .cell:last-of-type {
            border-bottom: none;
        }

        .border__none {
            border-bottom: none;
        }
        .cell .c-label {
            padding: 16px 0;
            width: 18%;
            background-color: #eee;
        }

        .cell .c-label span {
            width: 86%;
            padding: 10px 7%;
            /* display: inline-block;
            text-align:justify;
            text-justify:distribute-all-lines;
            text-align-last:justify;
            -moz-text-align-last: justify; */
        }

        .cell .c-value {
            padding: 16px 0;
            width: 82%;
        }

        .files {
            /* padding: 10px 0; */
            margin: 0 4%;
            width: 92%;
            display: flex;
            flex-wrap: wrap;
            max-height: 330px;
            overflow: auto;
        }

        .files .file {
            width: 128px;
            height: 128px;
            border: 1px solid #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;
            position: relative;
        }

        .files .file .image {
            width: 100%;
            height: 100%;
            display: block;
            margin: 0 auto;

        }

        .files div.file-add {
            width: 128px;
            height: 128px;
            border: 1px dashed #d0c9c9;
            margin-bottom: 20px;
            margin-right: 20px;
        }

        .icon-add {
            margin-left: 63px;
            margin-top: 24px;
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

        .cell .c-value input {
            width: 260px;
            height: 30px;
            line-height: 30px;
            margin-left: 4%;
            padding: 0 10px;
            font-size: 14px;
        }

        .cell .c-value textarea {
            width:400px;
            height: 90px;
            line-height: 24px;
            margin-left: 4%;
            border-color: #d0c9c9;
            padding: 10px;
            font-size: 14px;
        }

        .c-btns {
            width: 320px;
            margin: 0 auto;
            padding-top: 30px;
            display: flex;
            justify-content: space-between;
        }

        .c-btns .c-btn {
            width: 130px;
            height: 34px;
            line-height: 34px;
            border: 1px solid #bbb;
            background-color: #fff;
            cursor: pointer;
            text-align: center;
        }

        .c-btns .c-btn:hover {
            box-shadow: 0px 0px 10px #bbb;
        }

        .c-btns .c-btn__active {
            background-color: #3ba9ff;
            border: 1px solid #3ba9ff;
            color: #fff;
        }

        .icon-delete{
            position: absolute;
            top: 1px;
            right: 1px;
            width: 17px;
            height: 17px;
            background: red;
            border-radius: 50%;
        }
        .icon-deleteX{
            display: block;
            color: #fff;
            line-height: 17px;
            text-align: center;
        }
        input.layui-upload-file{
            display: none!important;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="main">
        <form id="editForm" role="form" action="${ctx}/fee/operate" method="post">
            <input type="hidden" name="id" value="${id}" />
            <input type="hidden" name="btnCode" value="pass" />
            <input type="hidden" name="operateType" value="pre" />
            <div class="form">

                <div class="cell">
                    <div class="c-label"><span>应付款金额</span></div>
                    <div class="c-value">
                        <input type="number" value="${money}" readonly />
                    </div>
                </div>
                <div class="cell">
                    <div class="c-label"><span>实际付款金额</span></div>
                    <div class="c-value">
                        <input type="number" value="${money}" name="payMoney" required />
                    </div>
                </div>
                <div class="cell">
                    <div class="c-label"><span>实际付款时间</span></div>
                    <div class="c-value">
                        <input name="payDate" type="text"  class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
                    </div>
                </div>
                <div class="cell">
                    <div class="c-btns">
                        <div class="c-btn" onclick="javascript:closeDialog();">取消</div>
                        <div class="c-btn c-btn__active" onclick="formSubmit()">提交</div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
    <div id="dialogId"></div>
    <script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${ctx}/js/common.js" type="text/javascript"></script>
    <script src="${ctx}/js/dialog.js" type="text/javascript"></script>
    <script type="text/javascript">
        var ctx="${ctx}";
    </script>
    <script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
    <script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
    <script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
    <script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/js/search-select2.js?v=${resourceVersion}"></script>
    <script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>

<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx}/js/layui/layui.js"></script>
<script>

    var value = "";
    var id = 0;

    function formSubmit(){
        if ($("input[name=payMoney]").val() == ""){
            alert("实际付款金额不能为空")
            return ;
        }
        if ($("input[name=payDate]").val() == ""){
            alert("实际付款时间不能为空");
            return;
        }
        $('#editForm').submit();
    }

    //输入框验证
    //只能输入数字和小数点
    $("input[type=number]").keyup(function () {
        var num = $(this).val();
        if (num.toString().indexOf(".") > 0 && Number(num.toString().split(".")[1].length) > 2){
            $(this).val(Math.round(num * 100)/100);
        }else {
            $(this).val(num.replace(/[^0-9.]/g, ''));
        };
    }).bind("paste", function () { //CTR+V事件处理
        $(this).val($(this).val().replace(/[^0-9.]/g, ''));
    }).css("ime-mode", "disabled"); //CSS设置输入法不可用

    $("#editForm").bind('submit', function(event) {
        ajaxFormSubmit(this,myCallBack,null,null,myCallBack);
        event.preventDefault();
    });


    function myCallBack(event,param){
        var apiRsp=getApiJson(param.data);
        if(apiRsp && apiRsp.isSuccess){
            reloadParent();
        }else{
            alert(apiRsp.msg);return;
        }
    }


    $(function () {
        function getNaturalWH(src) {
            var image = new Image();
            image.src = src;
            return [image.width, image.height];
        }

        $('.files .image').each(function (i) {
            var SIZE = 128
            var url = $(this).attr('src')
            var width, height, arr = getNaturalWH(url)
            var _width = arr[0],
                _height = arr[1]
            if (1 <= _width / _height) {
                width = SIZE; //以框的宽度为标准
                height = SIZE * (_height / _width);
            } else {
                width = SIZE * (_width / _height);
                height = SIZE; //以框的高度为标准
            }
            var cssArr = {
                'width': width,
                'height': height
            }
            if (width == SIZE) {
                cssArr = {
                    'width': width,
                    'height': height,
                    'margin-top': (SIZE - height) / 2
                }
            }
            $(this).css(cssArr)
        })
    })




    $("[name=payDate]").text(getNowFormatDate());
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        return currentdate;
    }
</script>
</html>
