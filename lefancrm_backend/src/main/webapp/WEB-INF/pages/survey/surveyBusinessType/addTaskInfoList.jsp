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
<div class="main administrator">
    <div class="main-top">
        <h3>列表</h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <form id="editForm" role="form" action="${ctx}/baseSurvey/operate" method="post">
            <input type="hidden" name="businessTypeId" value="${businessTypeId}">
            <input type="hidden" name="surveyCode" value="businessTaskType"> <%--修改surveyCode值，用于保存数据--%>
            <input type="hidden" id="taskInfoIds" name="taskInfoIds" value="${taskInfoIds}">
            <input type="hidden" name="btnCode" value="${btnCode}">
            <table class="table table-hover">
                <thead>
                <tr>
                    <c:if test="${btnCode == 2100}">
                        <th width="80"><input type="checkbox" id="all" >全选</th>
                    </c:if>
                    <th width="100">名称</th>
                    <c:if test="${btnCode == 2100}">
                        <th width="100">类型</th>
                    </c:if>
                    <c:if test="${btnCode == 2000}">
                        <th width="100">操作</th>
                    </c:if>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:if test="${btnCode == 2100}">
                    <c:forEach items="${taskInfos}" var="item">
                        <tr>
                            <td>
                                <input type="checkbox" name="buss" value="${item.id}"
                                        <c:forEach items="${myTaskInfos}" var="my">
                                            <c:if test="${my.taskInfoId == item.id }">
                                                checked="checked"
                                            </c:if>
                                        </c:forEach>
                                        />
                            </td>

                            <td>
                                ${item.name}
                            </td>
                            <td>
                                <c:if test="${item.type == 1}">保险类</c:if>
                                <c:if test="${item.type == 2}">互助类</c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="10">
                            <button id="batchOperateBtn" type="submit" class="btn btn-default">确定提交</button>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${btnCode == 2000}">
                    <c:forEach items="${myTaskInfos}" var="my">
                        <tr>
                            <c:forEach items="${taskInfos}" var="item">
                                <c:if test="${my.taskInfoId == item.id }"><td>${item.name}</td></c:if>
                            </c:forEach>

                            <td><a href="javascript:operate('${businessTypeId}','${my.taskInfoId}','businessTaskType','9999',true);">删除</a></td>
                        </tr>
                    </c:forEach>
                </c:if>

                </tbody>
            </table>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            </div>
        </form>
    </div>
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
    $(document).ready(function(){
        $("#all").on('change',function(){
            $("input[name='buss']").prop("checked",this.checked);
        })
    })

    $(function(){
        $("#editForm").bind('submit', function(event) {
            _checkRole();
            ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
            event.preventDefault();
        });

        do_list_checkbox();
    });

    function _checkRole(){
        var check_name = document.getElementsByName("buss");
        var idArr=new Array();
        for(var i=0;i<check_name.length;i++){
            if(check_name[i].checked){
                idArr.push(check_name[i].value);
            }
        }
        $("#taskInfoIds").val(idArr);
    }

    function do_list_checkbox(){
        var checkValue = "${usertaskInfoIds}";
        $("input[name=buss]").each(function (){
            var indexCode = checkValue.indexOf(","+$(this).val()+",");
            if(indexCode>-1){
                $(this).attr("checked","checked");
            }
        });
    }

    function operate(businessTypeId,taskInfoId,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"businessTypeId":businessTypeId,"taskInfoId":taskInfoId,"surveyCode":surveyCode,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    if(btnCode == '9999') {
                        location.reload();
                    }
                })
            }
        }else {
            var title = null, url = null;
            if(btnCode == '1000'){
                height = 500;
                width = 800;
                title = '修改';
                url = "${ctx}/baseSurvey/edit?id="+id+"&surveyCode="+surveyCode
            }
            openDialog({
                frame:true,
                title:title,
                height:height,
                width:width,
                url:url
            });
        }
    }
</script>
</body>
</html>