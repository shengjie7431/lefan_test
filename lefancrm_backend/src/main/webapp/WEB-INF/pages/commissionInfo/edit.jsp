<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>



    </script>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/commissionInfo/commissionInfoUpdate" method="post">
        <input type="hidden" name="id" value="${commissionInfo.id}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">职级名称</th>
                    <td width="70%">
                        <select name="levelId" class="form-control">
                            <c:forEach items="${positionLevel}" var="item1">
                                <option value="${item1.id}" <c:if test="${commissionInfo.levelId == item1.id}">selected="selected" </c:if>>${item1.levelCode}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">1级服务费指标</th>
                    <td width="70%">
                        <input type="number" step="0.01"  id = "oneQuota" name="oneQuota" value="${commissionInfo.oneQuota}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">1级佣金提成比例</th>
                    <td width="70%">
                        <input type="number" step="0.01"  id = "oneComrate" name="oneComrate" value="${commissionInfo.oneComrate}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">2级服务费指标</th>
                    <td width="70%">
                        <input type="number" step="0.01"  id = "twoQuota" name="twoQuota" value="${commissionInfo.twoQuota}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">2级佣金提成比例</th>
                    <td width="70%">
                        <input type="number" step="0.01"  id = "twoComrate" name="twoComrate" value="${commissionInfo.twoComrate}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">3级服务费指标</th>
                    <td width="70%">
                        <input type="number" step="0.01"  id = "threeQuota" name="threeQuota" value="${commissionInfo.threeQuota}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">3级佣金提成比例</th>
                    <td width="70%">
                        <input type="number" step="0.01"  id = "threeComrate" name="threeComrate" value="${commissionInfo.threeComrate}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">4级服务费指标</th>
                    <td width="70%">
                        <input type="number" step="0.01"  id = "fourQuota" name="fourQuota" value="${commissionInfo.fourQuota}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">4级佣金提成比例</th>
                    <td width="70%">
                        <input type="number" step="0.01"  id= "fourComrate" name="fourComrate" value="${commissionInfo.fourComrate}" style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">5级服务费指标</th>
                    <td width="70%">
                        <input type="number" step="0.01"  id = "fiveQuota" name="fiveQuota" value="${commissionInfo.fiveQuota}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">5级佣金提成比例</th>
                    <td width="70%">
                        <input type="number" step="0.01" id = "fiveComrate" name="fiveComrate" value="${commissionInfo.fiveComrate}" style="width: 400px;" class="form-control">
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认修改</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
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
    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

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
</script>
</body>
</html>