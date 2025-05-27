<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>

<link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">

<style>
    .layui-form {
        margin-top: 10px;
    }

    .layui-form-item {
        width: 800px;
        margin: 0 auto;
        margin-bottom: 15px;
        display: flex;
    }

    .layui-form-label {
        width: 26%;
        line-height: 30px;
    }

    .layui-input-block {
        width: 74%;
        padding: 0 30px;
        margin: 0;
        position: relative;
    }

    .layui-disable {
        background-color: #eee;
    }

    .layui-btn {
        width: 140px;
    }

    .required-icon {
        color: red;
        padding: 0 6px;
        font-size: 12px
    }

    .unit {
        position: absolute;
        right: 60px;
        top: 8px;
    }

    .lf-btns {
        width: 100%;
        display: flex;
    }

    .lf-btns .lf-btn {
        width: 160px;
        height: 40px;
        line-height: 40px;
        border: 1px solid #3BA9FF;
        color: #3BA9FF;
        text-align: center;
        margin-right: 20px;
        cursor: pointer;
    }

    .lf-btns .lf-btn.active {
        background-color: #3BA9FF;
        color: #fff;
    }
</style>
<body>
<input type="hidden" name="id" value="${id}">
<input type="hidden" name="caseNo" value="${caseNo}">
<input type="hidden" name="fromType" value="${fromType}"><%--one:单个开票 two:多个开票--%>
<input hidden name="billMoney" value="${billMoney}"><%--开票总金额--%>
<input hidden name="oneBillMoney" value="${oneBillMoney}"><%--单个金额--%>
<input hidden name="size" value="${size}"><%--开票张数--%>
<input hidden name="billImg" value="${billImg}"><%--图片--%>
<input hidden name="startBillCode" value="${startBillCode}"><%--起始号码--%>
    <div class="layui-form-item">
        <label class="layui-form-label">开票总金额 <span class="required-icon">*</span></label>
        <div class="layui-input-block">
            <input type="number" name="billSum" lay-verify="title" autocomplete="off" placeholder="请输入标题"
                   class="layui-input billSum" value="${billMoney}" disabled>
            <span class="unit">元</span>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单张发票面额 <span class="required-icon">*</span></label>
        <div class="layui-input-block">
            <input type="number" name="billOne" lay-verify="title" autocomplete="off" placeholder="请单张发票面额"
                   class="layui-input billOne" value="${oneBillMoney}">
            <span class="unit">元</span>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">本次开票张数 </label>
        <div class="layui-input-block">
            <input type="number" name="billNum" lay-verify="title" autocomplete="off" placeholder=""
                   class="layui-input layui-disable billNum" value="" disabled>
            <span class="unit">张</span>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"> </label>
        <div class="layui-input-block" style="color: #101010">
            （若无法整除，则最后一张自动计算零头）
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">起始发票号码 </label>
        <div class="layui-input-block">
            <input type="number" name="billId" lay-verify="title" autocomplete="off"
                   placeholder="请输入起始发票号码，默认按照连号规则自动生成剩余号码，选填" class="layui-input billId" value="">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">开票时间 </label>
        <div class="layui-input-block" style="display: flex;align-items: center">
            <input id="createTime" name="createTime" type="text" class="form-control" required="required" style="cursor: auto; background-color:#fff"
                   value=""
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">批量上传 </label>
        <div class="layui-input-block">
            <input required id="adPic" type="hidden" name="img" class="img">
            <img id="infImg" class="infImg" width="80" height="80">
            <input id="fileupload" class="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage?moduleName=ticket/${caseNo}">
            <input type="hidden" name="billingImgs">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"> </label>
        <div class="layui-input-block" style="color: #101010">
            （选填，若所有发票都共用一个凭证，则在此上传；若每个发票凭证都不同，则在下个页面单独上传）
        </div>
    </div>

    <div class="layui-form-item">
        <div class="lf-btns layui-inline" style="justify-content: center">
            <div class="lf-btn " data-type="1">取消</div>
            <div class="lf-btn active" data-type="2">确定</div>
        </div>
    </div>
</body>
<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script src="${ctx}/js/layui/layui.js"></script>

<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

<script>
    $(function () {

        var currentDay = new Date();
        var y = currentDay.getFullYear();
        var m = currentDay.getMonth() + 1;
        var d = currentDay.getDate();
        m = PrefixInteger(m, 2);
        d = PrefixInteger(d, 2);
        $("#createTime").val(y+'-'+m+'-'+d);
        function PrefixInteger(num, m) {
            return (Array(m).join(0) + num).slice(-m);
        }

        var billItems =[]
        layui.use('layer', function () {
            layer = layui.layer
            setBillNum('first')
        })
        $('.lf-btns .lf-btn').click(function () {
            var _type = $(this).attr('data-type')
            if (_type == 2) {
                var param = {
                    billMoney: $('.billSum').val(),
                    oneBillMoney: $('.billOne').val(),
                    billItems: JSON.stringify(billItems),
                    billImg:$('.infImg').attr('src')
                }
                if (!checkPapers('moneyorMinus', param.billMoney) || !param.billMoney || Number(param.billMoney) == 0) {
                    layer.msg('请输入正确的开票总金额！', {
                        icon: 5
                    })
                    return;
                }
                if (Number($('.billSum').val()) > Number($('input[name=billMoney]').val())){
                    layer.msg('开票总金额不可大于未开票金额 '+$('input[name=billMoney]').val(), {
                        icon: 5
                    })
                    return;
                }
                if (!checkPapers('moneyorMinus', param.oneBillMoney) || !param.oneBillMoney || Number(param.oneBillMoney) == 0) {
                    layer.msg('请输入正确的单张发票面额！', {
                        icon: 5
                    })
                    return;
                }

                var billMoney = Number($('input[name=billMoney]').val())
                if (Number($('.billOne').val()) > billMoney){
                    layer.msg('单张开票金额不可大于开票总金额 '+$('input[name=billMoney]').val(), {
                        icon: 5
                    })
                    return;
                }
                var billId = $('.billId').val()


                if (billId && !checkPapers('number', billId) ){
                    layer.msg('请输入正确的起始发票号码！', {
                        icon: 5
                    })
                    return;
                }
                console.log(param)
                uploadBillApplyImg(param)
            }else if (_type == 1){
                var closeBtn = $("#diglog_close_btn",window.parent.document);
                closeBtn.click();
            }
        })
        $('.billSum').blur(function () {
            var billMoney = Number($('input[name=billMoney]').val())
            if (Number($('.billSum').val()) > billMoney){
                layer.msg('开票总金额不可大于未开票金额 '+$('input[name=billMoney]').val(), {
                    icon: 5
                })
                return;
            }
            setBillNum()
        })
        $('.billOne').blur(function () {
            if (Number($('.billOne').val()) == 0) {
                layer.msg('请输入正确的单张发票面额！', {
                    icon: 5
                })
                return;
            }
            var billMoney = Number($('input[name=billMoney]').val())
            if (Number($('.billOne').val()) > billMoney){
                layer.msg('单张开票金额不可大于开票总金额 '+$('input[name=billMoney]').val(), {
                    icon: 5
                })
                return;
            }
            setBillNum()
        })
        $('.billId').blur(function () {
            setBillNum()
        })
        function setBillNum(firstFlag) {
            var billSum = $('.billSum').val()
            var billOne = $('.billOne').val()
            var billId = $('.billId').val()
            if (!firstFlag) {
                if (!checkPapers('moneyorMinus', billSum)) {
                    layer.msg('请输入正确的开票总金额！', {
                        icon: 5
                    })
                    return;
                }
                if (!checkPapers('moneyorMinus', billOne)) {
                    layer.msg('请输入正确的单张发票面额！', {
                        icon: 5
                    })
                    return;
                }
                if (billId && !checkPapers('number', billId)){
                    layer.msg('请输入正确的起始发票号码！', {
                        icon: 5
                    })
                    return;
                }
            }
            var billList = []
            if (billSum && billOne) {
                var billSumN = Number(billSum)
                var billOneN = Number(billOne)
                var billOneN = Number(billOne)

                var billNum = Math.ceil(billSumN / billOneN)
                $('.billNum').val(billNum)

                if (billId) {
                    var len = billId.length
                    var billId = Number(billId)
                    for (var i = 0; i < billNum; i++) {
                        var _billId = billId + i
                        if (i < billNum - 1) {
                            billList.push({
                                money: billOneN,
                                billId: PrefixInteger(_billId, len)
                            })
                        } else {
                            billList.push({
                                money: billSumN % billOneN == 0 ? billOneN : billSumN % billOneN ,
                                billId: PrefixInteger(_billId, len)
                            })
                        }

                    }
                } else {
                    for (var i = 0; i < billNum; i++) {
                        if (i < billNum - 1) {
                            billList.push({
                                money: billOneN,
                                billId: ''
                            })
                        } else {
                            billList.push({
                                money:  billSumN % billOneN == 0 ? billOneN : billSumN % billOneN,
                                billId: ''
                            })
                        }

                    }
                }
                billItems = billList
            }
        }

        $('.fileupload').fileupload({
            done: function (e, data) {
                var r  = data.result
                var img=r.images;
                console.log(r)
                var pathImg=img[0].userFilePath;
                if (r.success == true){
                    // $(this).siblings('.img').val("http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                    // $(this).siblings('.infImg').attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                    $("#infImg").attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                    // $("#adPic").val("http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                }else {
                    alert("上传失败，请重试111");
                }
            }
        });

        //正则匹配
        function checkPapers(parama, paramb) {
            var map = new Map([
                ['phone', /^[1][3,4,5,7,8,9][0-9]{9}$/],
                ['passport', !/^((1[45]\d{7})|(G\d{8})|(P\d{7})|(S\d{7,8}))?$/],
                ['identity', /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/],
                ['money', /^[0-9]+(\.[0-9]{1,2})?$/],
                ['number', /^[0-9]*$/],
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

        function PrefixInteger(num, m) {
            var len = num.toString().length;
            while (len < m) {
                num = "0" + num;
                len++;
            }
            return num;
        }
    })
    /**
     * 开票页面
     */
    function uploadBillApplyImg(param) {
        var id = $('input[name=id]').val(),caseNo =$('input[name=caseNo]').val()
        sessionStorage.setItem("billItems",param.billItems);
        var _width = $(document).width() * 0.9
        var _height = $(document).height() * 0.9
        openDialog({
            frame: true,
            title: "开票页面",
            height: _height,
            width: _width,
            url: "${ctx}/suning/billApply/uploadBillApplyImg?id="+ id + "&caseNo"+caseNo +"&fromType=twoOk"+"&billMoney="+param.billMoney+"&oneBillMoney="+param.oneBillMoney+"&billImg="+param.billImg+"&createTime="+$("input[name=createTime]").val()
    });
    }
</script>

</html>