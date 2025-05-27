<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <style>
        .tableBilling{
            margin: 10px 0;
            border-bottom: 1px solid red!important;
        }

        .table tr:first-of-type th,.table tr:first-of-type td{
            border-top: none;
        }
    </style>
</head>

<body>
<div class="container">
    <form id="editForm">
        <input type="hidden" name="billsetTime" value="${createTime}">
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="yhc" value="${yhc}">
        <input type="hidden" name="type" value="${type}">
<%--        <input type="hidden" name="billMoney" value="${billMoney}">--%>
        <input type="hidden" name="operatorType" value="${operatorType}">
        <input type="hidden" name="fromType" value="${fromType}"><%--one:单个开票 two:多个开票--%>
        <input hidden name="billMoney" value="${billMoney}"><%--开票总金额--%>
        <input hidden name="oneBillMoney" value="${oneBillMoney}"><%--单个金额--%>
        <input hidden name="size" value="${size}"><%--开票张数--%>
        <input hidden name="billImg" value="${billImg}"><%--图片--%>
        <input hidden name="startBillCode" value="${startBillCode}"><%--起始号码--%>
        <div class="form-group tableBillings">
            <c:if test="${fromType == 'one'}">
                <table class="table tableBilling">
                    <tbody>
                    <tr>
                        <th width="20%" class="active">发票凭证</th>
                        <td>
                            <input required id="adPic" type="hidden" name="img" class="img">
                            <img id="infImg" class="infImg" width="80" height="80">
                            <input id="fileupload" class="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage?moduleName=ticket/${caseNo}">
                            <input type="hidden" name="billingImgs">
                        </td>

                    </tr>
                    <tr>
                        <th width="20%" class="active">发票单号</th>
                        <td>
                            <input type="number" name="billingCode" style="width: 400px;" class="form-control billingCode" required="required">
                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">发票金额</th>
                        <td>
                            <input type="number"  name="billingMoney" min="0.00" step="0.01" value="${billMoney}" style="width: 400px;" class="form-control billingMoney" >

                        </td>
                    </tr>
                    <tr>
                        <th width="20%" class="active">开票时间</th>
                        <td>
                            <input id="createTime" name="createTime" type="text" class="form-control" required="required" style="width: 400px;cursor: auto; background-color:#fff"
                                   value="2020-11-11"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </c:if>

<%--            <c:forEach var="x" begin="1" end="${size}" step="1" varStatus="status">--%>
<%--                <table class="table tableBilling" data-id="${status.index}">--%>
<%--                    <tbody>--%>
<%--                    <tr>--%>
<%--                        <th width="20%" class="active">发票凭证</th>--%>
<%--                        <td>--%>
<%--                                &lt;%&ndash;<input required id="adPic" type="hidden" value="${billingApply.img}" name="img">&ndash;%&gt;--%>
<%--                                &lt;%&ndash;<img id="infImg" src="${billingApply.img}" width="80" height="80">&ndash;%&gt;--%>
<%--                            <input required id="adPic" type="hidden" name="img" class="img">--%>
<%--                            <img id="infImg" class="infImg" width="80" height="80">--%>
<%--                            <input id="fileupload" class="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage?moduleName=ticket/${caseNo}">--%>
<%--                            <input type="hidden" name="billingImgs">--%>
<%--                        </td>--%>

<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <th width="20%" class="active">发票单号</th>--%>
<%--                        <td>--%>
<%--                                &lt;%&ndash;<input type="text" id = "billingCode" name="billingCode" value="${billingApply.billingCode}" style="width: 400px;" class="form-control">&ndash;%&gt;--%>
<%--                            <input type="text" name="billingCode" style="width: 400px;" class="form-control" required="required">--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <th width="20%" class="active">发票金额</th>--%>
<%--                        <td>--%>
<%--                                <input type="number"  name="billingMoney" min="0" max="100000" style="width: 400px;" class="form-control" required="required">--%>
<%--                                &lt;%&ndash;<input type="text" id = "billingCode" name="billingCode" value="${billingApply.billingCode}" style="width: 400px;" class="form-control">&ndash;%&gt;--%>

<%--                        </td>--%>
<%--                    </tr>--%>
<%--                    </tbody>--%>
<%--                </table>--%>
<%--            </c:forEach>--%>
        </div>
<%--        <table class="table">--%>
<%--            <tbody>--%>
<%--                <tr>--%>
<%--                    <th width="20%" class="">发票张数</th>--%>
<%--                    <td>--%>
<%--                        &lt;%&ndash;<input type="text" id = "billingCode" name="billingCode" value="${billingApply.billingCode}" style="width: 400px;" class="form-control">&ndash;%&gt;--%>
<%--                        <input type="number" value="${size}" name="number" style="width: 400px;" class="form-control" >--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </tbody>--%>
<%--        </table>--%>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn lf-submit" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </form>
</div>

<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>


<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>

<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script type="text/javascript">

    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });
    $('.fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            if (r.success == true){
                $(this).siblings('.img').val("http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                $(this).siblings('.infImg').attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                // $("#infImg").attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                // $("#adPic").val("http://ddrapi.shlefan.com/sftp/files/"+pathImg);
            }else {
                alert("上传失败，请重试111");
            }
        }
    });

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,function () {
            if ($('input[name=fromType]').val() == 'twoOk'){
                var closeBtn = $("#diglog_close_btn",window.parent.parent.document);
                closeBtn.click();
            }else if ($('input[name=fromType]').val() == 'one'){
                reloadParent()
            }
        },null,null,function (e,res) {
            alert(res.data.msg)
        });
        event.preventDefault();
    });
    $('input[name=number]').blur(function () {
        setTable()
    }).keyup(function (e) {
        if (e.keyCode == 13){
            setTable()
        }
    })

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

    $('body').on('blur','.billingCode',function () {
        var _this = $(this)
        var billId = _this.val()
        if (billId && !checkPapers('number', billId)){
            alert('请输入正确的发票号码')
            _this.val('')
            return;
        }

    })
    $('body').on('blur','.billingMoney',function () {
        var _this = $(this)
        var billingMoney = _this.val()
        if (billingMoney && !checkPapers('money', billingMoney)){
            alert('请输入正确的发票金额')
            _this.val(Math.round(billingMoney * 100) /100)
            return;
        }

    })



    if ($('input[name=fromType]').val() == 'twoOk'){
        console.log(111)
        setTable()
    }
    function setTable(){
        var billItems = JSON.parse(sessionStorage.getItem('billItems'))
console.log(billItems)
        var _html = ''
        var _url = "${ctx}/uploadImage?moduleName=ticket/${caseNo}"
        var imgUrl = $('input[name=billImg]').val()
        var billsetTime = $("input[name=billsetTime]").val();
       billItems.map(function (cur,i) {
           _html +='<table class="table tableBilling" data-id="'+(i+1)+'">\n' +
               '                    <tbody>\n' +
               '                    <tr>\n' +
               '                        <th width="20%" class="active">发票凭证</th>\n' +
               '                        <td>\n' +
               '                            <input required type="hidden" name="img" class="img">\n' +
               '                            <img class="infImg" width="80" height="80" src="'+imgUrl+'">\n' +
               '                            <input class="fileupload" type="file"  name="file" multiple  data-url="'+_url+'">\n' +
               '                            <input type="hidden" name="billingImgs">\n' +
               '                        </td>\n' +
               '\n' +
               '                    </tr>\n' +
               '                    <tr>\n' +
               '                        <th width="20%" class="active">发票单号</th>\n' +
               '                        <td>\n' +
               '                            <input type="number" name="billingCode" value="'+cur.billId+'" style="width: 400px;" class="form-control billingCode" required="required">\n' +
               '                        </td>\n' +
               '                    </tr>\n' +
               '                    <tr>\n' +
               '                        <th width="20%" class="active">发票金额</th>\n' +
               '                        <td><input hidden id="createTime" name="createTime" value="'+billsetTime+'"/>\n' +
               '                            <input type="number"  name="billingMoney" min="0.00" step="0.01"  value="'+cur.money+'" style="width: 400px;" class="form-control billingMoney" required="required">\n' +
               '                        </td>\n' +
               '                    </tr>\n' +
               '                    </tbody>\n' +
               '                </table>'
       })
        $('.tableBillings').append(_html)
        $('.fileupload').fileupload({
            done: function (e, data) {
                var r  = data.result;
                var img=r.images;
                var pathImg=img[0].userFilePath;
                if (r.success == true){
                    $(this).siblings('.img').val("http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                    $(this).siblings('.infImg').attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                    // $("#infImg").attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                    // $("#adPic").val("http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                }else {
                    alert("上传失败，请重试111");
                }
            }
        });
    }
    $('.lf-submit').click(function () {
        var billings = []
        var $table = $('.tableBilling')
        var sum = 0
        var flag = false
        for (var i=0;i<$table.length;i++){
            var $this = $table.eq(i)
            if (!$this.find('input[name=billingCode]').val()){
                return;
            }
            if ($this.find('input[name=billingMoney]').val()  < 0){
                flag = true
            }
            billings.push({
                billingImgs: $this.find('.infImg').attr('src'),
                billingCode: $this.find('input[name=billingCode]').val(),
                billingMoney: $this.find('input[name=billingMoney]').val(),
                operatorType: $this.find('input[name=operatorType]').val(),
                createTime : $this.find('input[name=createTime]').val(),
            })
            sum +=Number($this.find('input[name=billingMoney]').val())
        }

        var billMoney = Number($('input[name=billMoney]').val())
        if (sum > billMoney){
            alert('发票总金额不可超出'+billMoney+'元')
            return;
        }
        if (flag){
            // alert('发票金额不可为负数')
            return;
        }

        $.ajax({
            url: '${ctx}/suning/billApply/upd',
            data: {
                id:$("input[name=id]").val(),
                type:$("input[name=type]").val(),
                yhc:$("input[name=yhc]").val(),
                billings: JSON.stringify(billings)
            },
            success: function (res,r) {

            }
        })
    })

    //正则匹配
    var checkPapers = function (parama, paramb) {
        var map = new Map([
            ['phone', /^[1][3,4,5,7,8,9][0-9]{9}$/],
            ['passport', !/^((1[45]\d{7})|(G\d{8})|(P\d{7})|(S\d{7,8}))?$/],
            ['identity', /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/],
            ['money', /^[0-9]+(\.[0-9]{1,2})?$/],
            ['number', /^[0-9]*$/],
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


    /**
     * 关闭dialog
     */
    function closeDialog(){
        var closeBtn = $("#diglog_close_btn");
        if(closeBtn.size() == 0){
            closeBtn = $("#diglog_close_btn",window.parent.document);
        }
        closeBtn.click();
    }

    /**
     * 开票页面
     */
    function uploadBillApplyImg(id,caseNo) {
        openDialog({
            frame: true,
            title: "开票页面",
            height: 450,
            width: 650,
            url: "${ctx}/suning/billApply/uploadBillApplyImg?id="+ id + "&caseNo"+caseNo
        });
    }

</script>
</body>
</html>