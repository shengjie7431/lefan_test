<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>伤残等级</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script>
        function add(val){
            var param = $("#invalidismGrade").val();
            var paramTo = $("#invalidismGradeTo").val();
            if(param.length > 0){
                param = param + ","+ val;
                paramTo = paramTo + ","+ val+"级";
            }else{
                param = val;
                paramTo =  val+"级";
            }
            $("#invalidismGrade").val(param);
            $("#invalidismGradeTo").val(paramTo);
            return false;
        }
        function del(val){
            var param = $("#invalidismGrade").val();
            var paramTo = $("#invalidismGradeTo").val();
            if(param.length > 0){
                var flag = param.indexOf(val);
                if(flag != -1){
                    var params = param.split(",");
                    param = "";
                    paramTo = "";
                    var index = -1;
                    for(var i = 0; i < params.length; i++){
                        if(params[i] == val && index != -1){
                            if(param == ""){
                                param = param + params[i];
                                paramTo = paramTo + params[i] + "级";
                            }else{
                                param = param + "," + params[i];
                                paramTo = paramTo + "," + params[i] + "级";
                            }
                        }else if(params[i] != val){
                            if(param == ""){
                                paramTo = paramTo + params[i] + "级";
                                param = param + params[i];
                            }else{
                                param = param + "," + params[i];
                                paramTo = paramTo + "," + params[i] + "级";
                            }
                        }else{
                            index = 0;
                        }
                    }
                    $("#invalidismGrade").val(param);
                    $("#invalidismGradeTo").val(paramTo);
                }
            }
            return false;
        }

    </script>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
    </div>
    <form id="editForm" role="form" action="${ctx}/case/center/injuredPartSubmit">
        <input type="hidden" id="ids" name="ids" value="${ids}">
        <input type="hidden" id="id" name="id" value="${id}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="20%" class="active">预估伤残等级</th>
                    <td width="80%">
                        一级 <button onclick="return add(1)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(1)"class="btn btn-default btn-xs btn-primary">—</button>
                        二级 <button onclick="return add(2)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(2)"class="btn btn-default btn-xs btn-primary">—</button>
                        三级 <button onclick="return add(3)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(3)"class="btn btn-default btn-xs btn-primary">—</button>
                        四级 <button onclick="return add(4)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(4)"class="btn btn-default btn-xs btn-primary">—</button>
                        五级 <button onclick="return add(5)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(5)"class="btn btn-default btn-xs btn-primary">—</button></br></br>
                        六级 <button onclick="return add(6)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(6)"class="btn btn-default btn-xs btn-primary">—</button>
                        七级 <button onclick="return add(7)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(7)"class="btn btn-default btn-xs btn-primary">—</button>
                        八级 <button onclick="return add(8)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(8)"class="btn btn-default btn-xs btn-primary">—</button>
                        九级 <button onclick="return add(9)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(9)"class="btn btn-default btn-xs btn-primary">—</button>
                        十级 <button onclick="return add(10)"class="btn btn-default btn-xs btn-primary">+</button> <button onclick="return del(10)"class="btn btn-default btn-xs btn-primary">—</button></br>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">已选伤残等级</th>
                    <td width="80%">
                        <%--<textarea id = "invalidismGrade"  style="width: 400px;height: 90px" readonly>${report.invalidismGrade}</textarea>--%>
                        <textarea class="form-control" id = "invalidismGradeTo"  style="width: 400px;height: 90px" readonly >${estimateReport.invalidismGrade}</textarea>
                        <input type="hidden" value="${estimateReport.invalidismGrade}" name="invalidismGrade" id = "invalidismGrade">
                    </td>

                </tr>

                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button id="batchOperateBtn" type="submit" onclick="return businessOK()" class="btn btn-success loading-btn" data-loading-text="Loading...">确定提交</button>
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </form>
</div><!--main end-->

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script>
    $(document).ready(function(){
        var val = $("#invalidismGradeTo").val();
        if(val == null || val == ""){
            return;
        }
        var arr = val.split(",");
        val = "";
        for(var i = 0;i<arr.length;i++){
            val = val + arr[i] + "级";
            if(i != arr.length - 1){
                val += ",";
            }
        }
        $("#invalidismGradeTo").val(val);
    })

    $(function(){
        $("#editForm").bind('submit', function(event) {

        });
    });

    function businessOK(){
        var invalidismGrade = $("#invalidismGrade").val();
        parent.$("#invalidismGrade").val(invalidismGrade);
        var invalidismGradeTo = $("#invalidismGradeTo").val();
        parent.$("#invalidismGradeStr").val(invalidismGradeTo);
        closeDialog();
    }

</script>
</body>
</html>
