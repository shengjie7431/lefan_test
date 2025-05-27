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
            <input type="hidden" name="taskInfoContentId" value="${taskInfoContentId}">
            <input type="hidden" name="surveyCode" value="taskDirectionResult"> <%--修改surveyCode值，用于保存数据--%>
            <input type="hidden" id="directionResultTypeIds" name="directionResultTypeIds" value="${directionResultTypeIds}">
            <input type="hidden" name="btnCode" value="${btnCode}">
            <input type="hidden" name="scoreList" id="scoreList" value="">
            <input type="hidden" name="pointScoreList" id="pointScoreList" value="">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="80"><input type="checkbox" id="all" >全选</th>
                    <th width="100">名称</th>
                    <th width="100">自查分值</th>
                    <th width="100">指定方向分值</th>
                </tr>
                </thead>
                <tbody class="class-list">
                    <c:forEach items="${directionResultTypes}" var="item">
                        <tr>
                            <td>
                                <input type="checkbox" name="buss" value="${item.id}"
                                    <c:forEach items="${myTaskDirectionResults}" var="my">
                                        <c:if test="${my.directionResultTypeId == item.id }">
                                            checked="checked"
                                        </c:if>
                                    </c:forEach>
                                />
                            </td>
                            <td>
                                ${item.name}
                            </td>
                            <td>
                                <input type="number" class="input-2" step="0.01" id="score_${item.id}" name="score"
                                        <c:forEach items="${myTaskDirectionResults}" var="my">
                                            <c:if test="${my.directionResultTypeId == item.id }">
                                                value="${my.score}"
                                            </c:if>
                                        </c:forEach>
                                    style="width:60px;border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom-color:#f5e6e6"/>
                            </td>
                            <td>
                                <input type="number" class="input-2" step="0.01" id="point_score_${item.id}" name="point_score"
                                        <c:forEach items="${myTaskDirectionResults}" var="my">
                                            <c:if test="${my.directionResultTypeId == item.id }">
                                                value="${my.pointScore}"
                                            </c:if>
                                        </c:forEach>
                                       style="width:60px;border-left-width:0px;border-top-width:0px;border-right-width:0px;border-bottom-color:#f5e6e6"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="10">
                            <button id="batchOperateBtn" type="submit" class="btn btn-default">确定提交</button>
                        </td>
                    </tr>
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

    });

    function _checkRole(){
        var ids = [];
        var scoreArr = [];
        $("input:checkbox[name='buss']:checked").each(function() { // 遍历name=safeCompanys的多选框
            ids.push($(this).val());

            var id = $(this).val();
            var value = $("#score_" + id).val();
            scoreArr.push({
                directionResultTypeId : id,
                score : value,
                pointScore : $("#point_score_" + id).val()
            });
        });
        $("#directionResultTypeIds").val(ids);
        $("#scoreList").val(JSON.stringify(scoreArr));
    }

    function operate(taskInfoContentId,directionResultTypeId,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"taskInfoContentId":taskInfoContentId,"directionResultTypeId":directionResultTypeId,"surveyCode":surveyCode,"btnCode":btnCode};
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