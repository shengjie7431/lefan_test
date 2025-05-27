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
<html>
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
        .unit{
            position: absolute;
            top: 20px;
            left: 221px;
        }
        input.layui-upload-file{
            display: none!important;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="main">
        <form id="editForm" role="form" action="${ctx}/survey/pay/operate" method="post">
            <input type="hidden" name="btnCode" value="${btnCode}" />
            <input type="hidden" name="id" value="${surveyPayInfo.id}" />
            <input type="hidden" id="staffOrgId" value="${surveyPayInfo.organId}" />
            <input type="hidden" id="time" value='<fmt:formatDate value="${surveyPayInfo.appStartDate}" pattern="yyyy-MM-dd"/>' />
        <c:if test="${btnCode == 'ok-pay'}">
            <div class="form">
                <div class="cell">
                    <div class="c-label"><span>付款凭证</span></div>
                    <div class="c-value">
                        <div class='files' id="bankUrlDiv">
                            <input type="hidden" id="urls" name="urls" value="" />
                            <div class="file-add v-p-upload">
                                <input type="hidden" name="urls" />
                                <input type="file" id="fileUpload" style="display: none">
                                <div class="icon-add"></div>
                            </div>
                        </div>
                    </div>
                </div>
<%--                <div style="display: none">--%>
<%--                    <input id="materialFileupload" type="file" accept="image/*" name="file" multiple data-url="${ctx}/sftp/survey/uploadSftp?modelType=franchisee"><br>--%>
<%--                </div>--%>
                <input type="button" hidden id="uploadFile"></input>
                <div class="cell">
                    <div class="c-label"><span>申请付款金额</span></div>
                    <div class="c-value">
                        <input type="number" id="appPayMoney" step="0.01" readonly disabled value="${surveyPayInfo.appPayMoney}" name="payMoney" required />
<%--                        机构本月剩余报销额度：<span id="staffOrganMoney"><font color="red">加载中...</font></span>元--%>
                    </div>
                </div>
<%--                <div class="cell">--%>
<%--                    <div class="c-label"><span>手续费</span></div>--%>
<%--                    <div class="c-value">--%>
<%--                        <input type="number" step="0.01" value="0" name="handMoney" required />--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="cell">
                    <div class="c-label"><span>手续费率</span></div>
                    <div class="c-value" style="position: relative">
                        <input type="number" min="0" id="payTaxRate" name="payTaxRate" value="0" onchange="initRealIncomeMoney()" class="layui-input"><div class="unit">%</div>
                    </div>
                </div>
                <div class="cell">
                    <div class="c-label"><span>实际所得金额</span></div>
                    <div class="c-value">
                        <input style="background-color: #eee;" type="number" id="realIncomeMoney" step="0.01" value="" name="realIncomeMoney" readonly />
                    </div>
                </div>
                <div class="cell">
                    <div class="c-label"><span>实际支付金额</span></div>
                    <div class="c-value">
                        <input type="number" step="0.01" value="${surveyPayInfo.appPayMoney}" name="payMoney" required />
                    </div>
                </div>

                <div class="cell">
                    <div class="c-label"><span>实际付款时间</span></div>
                    <div class="c-value">
                        <input id="actualPaymentTime" name="actualPaymentTime" type="text" style="cursor: pointer;background-color: white;" required="required"  class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly >
                    </div>
                </div>
                <div class="cell">
                    <div class="c-btns">
                        <div class="c-btn c-btn__active" onclick="formSubmit()">确认提交</div>
                        <div class="c-btn" onclick="javascript:closeDialog();">关闭</div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${btnCode == 'veto-pay'}">
            <div class="form">
                <div class="cell">
                    <div class="c-label"><span>驳回原因</span></div>
                    <div class="c-value">
                        <textarea id="" cols="30" rows="10" name="reason"></textarea>
                    </div>
                </div>
                <div class="cell">
                    <div class="c-btns">
                        <div class="c-btn c-btn__active" onclick="formSubmit();">确认</div>
                        <div class="c-btn" onclick="javascript:closeDialog();">关闭</div>
                    </div>
                </div>
            </div>
        </c:if>
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

    function dateFormat(fmt, date) {
        let ret;
        const opt = {
            "Y+": date.getFullYear().toString(),        // 年
            "m+": (date.getMonth() + 1).toString(),     // 月
            "d+": date.getDate().toString(),            // 日
            "H+": date.getHours().toString(),           // 时
            "M+": date.getMinutes().toString(),         // 分
            "S+": date.getSeconds().toString()          // 秒
            // 有其他格式化字符需求可以继续添加，必须转化成字符串
        };
        for (let k in opt) {
            ret = new RegExp("(" + k + ")").exec(fmt);
            if (ret) {
                fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
            };
        };
        return fmt;
    }

    $(function () {

        var payTime=$("#actualPaymentTime").val();
        if(payTime == null || payTime =='' || payTime==undefined){
            let date = new Date()
            payTime=dateFormat('YYYY-mm-dd', date);
        }
        $("#actualPaymentTime").val(payTime);





        layui.use(['upload','jquery'], function () {
            var upload = layui.upload;
            var $ = layui.$;
            var uploadFile = upload.render({
                elem: '#uploadFile',
                url: '${ctx}/sftp/survey/uploadSftp', //改成您自己的上传接口
                multiple: true,
                accept: 'images', //只能上传图片
                acceptMime: 'image/*',
                before: function (obj) {
                    layer.load();
                },
                done: function (res) {
                    console.log("res:::"+JSON.stringify(res))
                    layer.closeAll('loading')
                    if (res.success == 'true'){
                        layer.alert('导入成功', {
                            icon: 1
                        })
                        id = id + 1;
                        var file = res.surveyFile;
                        var vSrc = file.filePath;
                        $("#bankUrlDiv").append("<div id='div_" + id + "' class='file' ><div class='icon-delete'><a href='javascript:;' onclick='deleteFile("+id+",\""+ vSrc + "\" )' class='icon-deleteX'>X</a></div><img class='image' class='picToBig' onclick='pic__click(this)' src='" + vSrc + "'/></div>");
                        value += file.filePath+ ',';
                        $("#urls").val(value);
                        $("#bankUrlDiv").show();
                    }else {
                        layer.alert('导入出错', {
                            icon: 2
                        })
                    }
                },
                allDone:function(obj){
                    console.log(obj)
                },
                error: function (index, upload) {
                    layer.closeAll('loading')
                }
            });
            $('.v-p-upload').on('click', function () {
                var _this = $(this)
                uploadFile.reload({
                    data: {
                        'modelType':'franchisee'
                    },
                })
                $('#uploadFile').click()
            })
        });
    })

    function formSubmit(){
        if ($("input[name=payMoney]").val() == ""){
            alert("实际付款金额不能为空")
            return ;
        }
        if ($("input[name=actualPaymentTime]").val() == ""){
            alert("实际付款时间不能为空");
            return;
        }
        $('#editForm').submit();
    }

    initRealIncomeMoney();
    function initRealIncomeMoney(){
        var appPayMoney = $("#appPayMoney").val();
        if (!appPayMoney)  appPayMoney = 0;
        var payTaxRate = $("#payTaxRate").val();
        if (!payTaxRate) payTaxRate = 0;
        var money = (appPayMoney - appPayMoney * payTaxRate / 100).toFixed(2);
        $("#realIncomeMoney").val(money);
    }

    //加载机构的剩余报销金额
    // initStaffOrganMoney();
    function initStaffOrganMoney(){
        var staffOrgId = $("#staffOrgId").val();
        var time = $("#time").val();
        $.ajax({
            url: '${ctx}/staff/operate',
            data: {
                operateCode: 'staff-organ-money',
                staffOrgId : staffOrgId,
                time : time
            },
            success: function (v,res) {
                res = res.data;
                console.log("res",res);
                if (res.isSuccess){
                    var value = res.results;
                    $("#staffOrganMoney").html(value)
                }
            }
        })
    }

    //输入框验证
    //只能输入数字和小数点
    // $("input[type=number]").keyup(function () {
    //
    //     var num = $(this).val();
    //     if (num.toString().indexOf(".") > 0 && Number(num.toString().split(".")[1].length) > 2){
    //         $(this).val(Math.round(num * 100)/100);
    //     }else {
    //         $(this).val(num.replace(/[^0-9.]/g, ''));
    //     };
    // }).bind("paste", function () { //CTR+V事件处理
    //     $(this).val($(this).val().replace(/[^0-9.]/g, ''));
    // }).css("ime-mode", "disabled"); //CSS设置输入法不可用

    $("input[type=number]").blur(function () {
        console.log($(this).val())
        var num = $(this).val();
        if (!checkPapers('money',num)){
            $(this).val(Math.round(num * 100)/100);
        }
    }).keyup(function (e) {
        if (e.keyCode == 13 && $(this).val()){
            console.log($(this).val())

            if (!checkPapers('money',num)){
                $(this).val(Math.round(num * 100)/100);
            }
        }
    })

    $("#editForm").bind('submit', function(event) {
        ajaxFormSubmit(this,myCallBack,null,null,myCallBack);
        event.preventDefault();
    });

    function s_click(){

    }


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


    $('#materialFileupload').fileupload({
        done: function (e, data) {
            id = id + 1;
            var r  = data.result;
            var file = r.surveyFile;
            var vSrc = "";
            var v = file.filePath;
            if(file.fileExt == "txt"){
                vSrc = "${ctx}/img/txt.png";
            }else if(file.fileExt == "docx" || file.fileExt == "doc"){
                vSrc = "${ctx}/img/word.png";
            }else if(file.fileExt == "xls" || file.fileExt == "xlsx"){
                vSrc = "${ctx}/img/excel.png";
            }else if(file.fileExt == "pdf"){
                vSrc = "${ctx}/img/pdf.jpg";
            }else{
                vSrc = file.filePath;
            }
//            $("#bankUrlDiv").append("<div id='div_" + id + "' class='col-sm-3'><img width='80' height='80' class='picToBig' onclick='pic__click(this)' src='" + vSrc + "' /><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id='" + id + "'  name='chknames' type='checkbox' value='" + v + "'> </div>");

            $("#bankUrlDiv").append("<div id='div_" + id + "' class='file' ><div class='icon-delete'><a href='javascript:;' onclick='deleteFile("+id+",\""+ vSrc + "\" )' class='icon-deleteX'>X</a></div><img class='image' class='picToBig' onclick='pic__click(this)' src='" + vSrc + "'/></div>");

            value += file.filePath+ ',';

            $("#urls").val(value);
            $("#bankUrlDiv").show();

        }
    });

    //上传中的图片删除
    function deleteFile(id,vSrc) {
        $("#div_" + id).remove();
        var fileList = $("#urls").val();
        var newList = [];
        for (var i = 0; i < fileList.split(",").length-1; i++) {
            if (vSrc != fileList.split(",")[i]) {
                newList.push(fileList.split(",")[i]);
            }
        }
        $("#urls").val(newList);
        value=newList+ ',';
    }

    //加载图片放大
    function pic__click(data){
        var bigImgSrc = data.src
        $('#pic_bigimg').find('img').attr('src', bigImgSrc)
        $('#pic_bigimg').show()
    }

    $("[name=actualPaymentTime]").text(getNowFormatDate());
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

        if (map.get(parama).test(paramb)) {
            return true
        } else {
            return false
        }
    }
</script>
</html>
