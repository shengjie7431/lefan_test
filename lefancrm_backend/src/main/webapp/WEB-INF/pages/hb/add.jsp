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
    <form id="editForm" role="form" action="${ctx}/hb/hbOrgInfoUpdate" method="post">

        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">机构名称</th>
                    <td width="70%">
                        <input type="text" id = "hbOrgName" name="hbOrgName"   style="width: 400px;" class="form-control">

                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">核保机构状态</th>
                    <td width="70%">
                        <select id = "state" name="state"  style="width: 400px;" class="form-control" readonly="true">
                            <option value="1" >启用</option>
                            <option value="2" >停用</option>
                        </select>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span>确认提交</button>
        </div>
    </form>
</div>


<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script type="text/javascript">
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