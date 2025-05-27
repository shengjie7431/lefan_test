<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
    <div class="main administrator">
        <div class="main-top">
            <%--        <h3>名下片区 <small>共<span>${apiRsp.count}</span>个</small></h3>--%>
        </div><!--main-top-->

        <div class="panel panel-info">
            <form id="editForm" role="form" action="${ctx}/baseSurvey/operate" method="post">
                <input type="hidden" name="investigatorIds" id="investigatorIds"/>
                <input type="hidden" name="franchiseeId" id="franchiseeId" value="${franchiseeId}"/>
                <input type="hidden" name="areaId" id="areaId" value="${areaId}"/>
                <input type="hidden" name="surveyCode" id="surveyCode" value="addAreaPersonnel"/>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <c:if test="${category == 'add'}">
                            <th width="80"><input type="checkbox" id="all" >全选</th>
                        </c:if>
                        <th width="80">用户姓名</th>
                        <th width="100">联系电话</th>
                        <th width="150">所在片区</th>
                        <c:if test="${category != 'add'}">
                            <th width="80">操作</th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody class="class-list">
                    <c:forEach items="${surveyInvestigatorDto}" var="item">
                        <tr>
                            <c:if test="${category == 'add'}">
                                <td>
                                    <input type="checkbox" value="${item.id}" name="tasks">
                                </td>
                            </c:if>
                            <td>${item.realName}</td>
                            <td>${item.tel}</td>
                            <td>${item.surveyAreaName}</td>
                            <c:if test="${category != 'add'}">
                                <td><a href="javascript:void(0)" onclick="delPianqu('${item.id}')">删除</a></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:if test="${category == 'add'}">
                    <div class="modal-footer">
                        <button id="batchOperateBtn" type="submit" onclick="return taskInfoOK()" class="btn btn-default">确定提交</button>
                    </div>
                </c:if>
            </form>
        </div><!--panel-info-->
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>

    </div><!--main end-->
    <%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
</body>
<script>

    $(document).ready(function(){
        $("#all").on('change',function(){
            $("input[name='tasks']").prop("checked",this.checked);
        })
    })

    $(function(){
        $("#editForm").bind('submit', function(event) {

            var investigatorIds=$("#investigatorIds").val();

            if(investigatorIds == ''){
                alert("请选择人员!");
                return false;
            }

            ajaxFormSubmit(this,function(v,e,p){
                if(e.data.count == 1){
                    alert(e.data.msg);
                    closeDialog();
                }
            },null,null,null)
            event.preventDefault();
        });
    });



    function taskInfoOK(){
        var includeTask = [];
        $("input:checkbox[name='tasks']:checked").each(function() { // 遍历name=safeCompanys的多选框
            includeTask.push($(this).val());
        });
        $("#investigatorIds").val(includeTask);
        // closeDialog();
    }

    function delPianqu(id) {
        $.ajax({
            type: "POST",//规定传输方式
            url: "${ctx}/baseSurvey/operate",//提交URL
            data: {'surveyCode':'delAreaPersonnel','id':id},//提交的数据
            success: function(data){
                alert("成功!");
                location.reload();
            },
            error:function(){
                alert("删除失败!");
            }
        });
    }
</script>
</html>
