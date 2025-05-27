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
    <form id="editForm" role="form" action="${ctx}/userPoLevel/userPoLevelUpdate" method="post">
        <input type="hidden" name="id" value="${userPoLevel.id}">
        <div class="form-group">
            <table class="table">
                <tbody>

                <tr>
                    <th width="30%" class="active">用户</th>
                    <td width="70%">
                        <%--<input type="text" id = "userId" name="userId" value="${userPoLevel.userId}"  style="width: 400px;" class="form-control">--%>
                        <select name="userId" class="form-control">
                            <c:forEach items="${userInfo}" var="item">
                                <option value="${item.userId}" <c:if test="${userPoLevel.userId == item.userId}">selected="selected" </c:if>>${item.userName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">职位</th>
                    <td width="70%">
                        <%--<input type="text" id = "positionId" name="positionId" value="${userPoLevel.positionId}" style="width: 400px;" class="form-control">--%>
                        <select name="positionId" id="positionId" class="form-control" onchange="onChangePositionId()">
                            <c:forEach items="${positionInfo}" var="item1">
                                <option value="${item1.id}" <c:if test="${userPoLevel.positionId == item1.id}">selected="selected" </c:if>>${item1.positionName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">职别</th>
                    <td width="70%">
                        <%--<input type="text" id = "levelId" name="levelId" value="${userPoLevel.levelId}" style="width: 400px;" class="form-control">--%>
                        <select name="levelId" id="levelId" class="form-control" onchange="onChangeLevelId()">
                            <c:forEach items="${positionLevel}" var="item1">
                                <option value="${item1.id}" <c:if test="${userPoLevel.levelId == item1.id}">selected="selected" </c:if>>${item1.levelCode}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">CC状态</th>
                    <td width="70%">
                        <select id = "state" name="state"  style="width: 400px;" class="form-control"  readonly="true">
                            <option value="1" <c:if test="${userPoLevel.state == 1}">selected </c:if>>绿色</option>
                            <option value="2" <c:if test="${userPoLevel.state == 2}">selected </c:if>>黄色</option>
                            <option value="3" <c:if test="${userPoLevel.state == 3}">selected </c:if>>橙色</option>
                            <option value="4" <c:if test="${userPoLevel.state == 4}">selected </c:if>>红色</option>
                            <option value="5" <c:if test="${userPoLevel.state == 5}">selected </c:if>>灰色</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">C状态</th>
                    <td width="70%">
                        <select id = "stateName" name="stateName"  style="width: 400px;" class="form-control" onchange="change()">
                            <option value="1" <c:if test="${userPoLevel.stateName == 1}">selected </c:if>>正常</option>
                            <option value="2" <c:if test="${userPoLevel.stateName == 2}">selected </c:if>>未达标</option>
                            <option value="3" <c:if test="${userPoLevel.stateName == 3}">selected </c:if>>连续未达标</option>
                            <option value="4" <c:if test="${userPoLevel.stateName == 4}">selected </c:if>>3个月未达标</option>
                            <option value="5" <c:if test="${userPoLevel.stateName == 5}">selected </c:if>>开除</option>
                        </select>
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
        alert("修改成功");
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

    function change(){
        var objS = document.getElementById("stateName");
        var grade = objS.options[objS.selectedIndex].value;
        console.log(grade)
        if(grade == 5){
            $("#state").val("5");
        }else{
            $("#state").val("1");
        }
    }

    function onChangePositionId(){
        var objS = document.getElementById("positionId");
        var positionId = objS.options[objS.selectedIndex].value;

        $.ajax({
            url:'${ctx}/userPoLevel/searchInfoByPositionId?positionId='+positionId,
            type:"Get",
            success:function(res,param){
                $("#levelId").val(param.data.positionLevelId);

            }
        });
    }

    function onChangeLevelId(){
        var objS = document.getElementById("levelId");
        var levelId = objS.options[objS.selectedIndex].value;

        $.ajax({
            url:'${ctx}/userPoLevel/searchInfoByLevelId?levelId='+levelId,
            type:"Get",
            success:function(res,param){
                $("#positionId").val(param.data.id);
            }
        });
    }
</script>
</body>
</html>