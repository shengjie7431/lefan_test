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
    <form id="editForm" role="form" action="" method="post">
        <input type="hidden" name="id" value="${managerComrateInfo.id}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th></th>
                    <th width="150">1级管理佣金人均指标</th>
                    <th width="150">1级管理佣金提成比例</th>
                    <th width="150">2级管理佣金人均指标</th>
                    <th width="150">2级管理佣金提成比例</th>
                    <th width="150">3级管理佣金人均指标</th>
                    <th width="150">3级管理佣金提成比例</th>
                    <th width="150">4级管理佣金人均指标</th>
                    <th width="150">4级管理佣金提成比例</th>
                    <th width="150">5级管理佣金人均指标</th>
                    <th width="150">5级管理佣金提成比例</th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${apiRsp.results}" var="item">
                    <tr>
                        <td><input type="radio" name="radio" value="${item.id}" onclick="test()"></td>
                        <td>${item.onePerQuota}</td>
                        <td>${item.onePerComrate}</td>
                        <td>${item.twoPerQuota}</td>
                        <td>${item.twoPerComrate}</td>
                        <td>${item.threePerQuota}</td>
                        <td>${item.threePerComrate}</td>
                        <td>${item.fourPerQuota}</td>
                        <td>${item.fourPerComrate}</td>
                        <td>${item.fivePerQuota}</td>
                        <td>${item.fivePerComrate}</td>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialogRefresh();">确定提交</button>
            <%--<button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:openList();">确定提交</button>--%>

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

    function test(){
//        var managerComrateIn = $("#radio").val();
//        alert(managerComrateIn+".....第一个");

        var managerComrateInfoId = document.getElementsByName("radio");
        var strNew;
        for(var i=0;i<managerComrateInfoId.length;i++){
            if(managerComrateInfoId.item(i).checked){
                strNew=managerComrateInfoId.item(i).getAttribute("value");// item()方法:返回集合中的当前项
                break;
            }else{
                continue;
            }
        }

        $.ajax({
            url: '${ctx}/positionLevel/queryByManagerComrateInfoId?managerComrateInfoId='+strNew,
            method:'POST',
            success: function(res){
            }
        })


        <%--<%--%>
        <%--session.setAttribute("managerComrateInfoId",managerComrateInfoId);--%>
        <%--%>--%>
    }


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

    <%--function openList(id){--%>
        <%--parent.location.href="${ctx}/positionLevel/positionLevelAddAg"--%>
    <%--}--%>
</script>
</body>
</html>




