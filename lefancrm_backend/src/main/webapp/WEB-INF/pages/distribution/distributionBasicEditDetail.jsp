<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/distribution/updateDistributionBasic" method="post">
        <input type="hidden" name="id" value="${infoDetail.id}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">押金</th>
                    <td width="70%"><input required value="${infoDetail.foregiftMoney}" name="foregiftMoney"></td>
                </tr>
                <tr>
                    <th width="30%" class="active">一级推广金额</th>
                    <td width="70%"><input required value="${infoDetail.oneLevelMoney}" name="oneLevelMoney"></td>
                </tr>
                <tr>
                    <th width="30%" class="active">二级推广金额</th>
                    <td width="70%"><input required value="${infoDetail.twoLevelMoney}" name="twoLevelMoney"></td>
                </tr>
                <tr>
                    <th width="30%" class="active">三级推广金额</th>
                    <td width="70%"><input required value="${infoDetail.threeLevelMoney}" name="threeLevelMoney"></td>
                </tr>
                <tr>
                    <th width="30%" class="active">案源使用扣除金额</th>
                    <td width="70%"><input required value="${infoDetail.caseMoney}" name="caseMoney"></td>
                </tr>
                <tr>
                    <th width="30%" class="active">提现金额限额</th>
                    <td width="70%"><input required value="${infoDetail.withdrawalsMoney}" name="withdrawalsMoney"></td>
                </tr>
                <tr>
                    <th width="30%" class="active">一级案件成交金额</th>
                    <td width="70%"><input required value="${infoDetail.oneCaseMoney}" name="oneCaseMoney"></td>
                </tr>
                <tr>
                    <th width="30%" class="active">二级案件成交金额</th>
                    <td width="70%"><input required value="${infoDetail.twoCaseMoney}" name="twoCaseMoney"></td>
                </tr>
                <tr>
                    <th width="30%" class="active">三级案件成交金额</th>
                    <td width="70%"><input required value="${infoDetail.threeCaseMoney}" name="threeCaseMoney"></td>
                </tr>
                <tr>
                    <th width="30%" class="active">贷款利率</th>
                    <td width="70%"><input required value="${infoDetail.loanInterestRate}" name="loanInterestRate"></td>
                </tr>
                <tr>
                    <th width="30%" class="active">可贷款比例</th>
                    <td width="70%"><input required value="${infoDetail.loanRate}" name="loanRate"></td>
                </tr>
                <tr>
                    <th width="30%" class="active">一级保代公司推广金额</th>
                    <td width="70%"><input required value="${infoDetail.oneCompanyMoney}" name="oneCompanyMoney"></td>
                </tr>
                <tr>
                    <th width="30%" class="active">二级保代公司推广金额</th>
                    <td width="70%"><input required value="${infoDetail.twoCompanyMoney}" name="twoCompanyMoney"></td>
                </tr>
                <tr>
                    <th width="30%" class="active">三级保代公司推广金额</th>
                    <td width="70%"><input required value="${infoDetail.threeCompanyMoney}" name="threeCompanyMoney"></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<%--<script src="${ctx}/js/jquery.pin.js" type="text/javascript"></script>--%>
<%--<script src="${ctx}/js/jquery.tableDnD.js" type="text/javascript"></script>--%>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>
<script src="${ctx}/js/dialog.js" type="text/javascript"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>


<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script type="text/javascript">
   /* var editor1;
   // var url="https://openapi.shlefan.com/lefanfsapicenter/file/uploadImage";
    KindEditor.ready(function(K) {
         editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $('#fileupload').fileupload({

        done: function (e, data) {
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            if (r.success == true){
                $("#infImg").attr("src","http://openapi.shlefan.com/pic/images"+pathImg);
                $("#adPic").val(r.imgUrl);
            }else {
                alert("上传失败，请重试111");
            }
        }
    });*/
    $("#editForm").bind('submit', function(event) {
      /*  $("#content").text(editor1.html());*/
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });
</script>
</body>
</html>