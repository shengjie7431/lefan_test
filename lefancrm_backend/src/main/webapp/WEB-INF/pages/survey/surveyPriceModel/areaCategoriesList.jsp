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
            <input type="hidden" name="surveyCode" value="${surveyCode}">
            <input type="hidden" name="btnCode" value="${btnCode}">
            <input type="hidden" name="id" value="${id}">
            <table class="table table-hover">
                <thead>
                <button onclick="add('${id}','priceModelAreaCategories')" type="button" class="btn btn-default">添加区域类别</button>
                <button onclick="addPrice('${id}','surveyPrice','${type}')" type="button" class="btn btn-default">设置价格</button>
                <tr>
                    <th width="100">区域类别名称</th>
                    <th width="300">城市</th>
                    <th width="100">操作</th>
                </tr>
                </thead>
                <tbody class="class-list">

                <c:forEach items="${infos}" var="item">
                    <tr>
                        <td>
                            ${item.name}
                        </td>
                        <td>
                        <c:forEach items="${item.areaCitys}" var="citys">
                            ${citys.areaName}
                        </c:forEach>
                        </td>
                        <td>
                            <a href="javascript:operate('${item.id}','priceModelAreaCategories','1000',false);">修改</a>

                            <a href="javascript:operate('${item.id}','priceModelAreaCategoriesDel','9999',true);">删除</a>
                        </td>
                    </tr>
                </c:forEach>
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
            $("#batchOperateBtn").attr("disabled",true);
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
        $("#roleIds").val(idArr);
    }

    function do_list_checkbox(){
        var checkValue = "${userRoleIds}";
        $("input[name=buss]").each(function (){
            var indexCode = checkValue.indexOf(","+$(this).val()+",");
            if(indexCode>-1){
                $(this).attr("checked","checked");
            }
        });
    }

    var add = function(id,surveyCode){
        openDialog({
            frame:true,
            title:"添加区域类别",
            height: $(parent.document).outerHeight() - 90,
            width: $(document.body).outerWidth() - 20,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode+"&priceModelId="+id
        });
    }

    function operate(id,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"id":id,"surveyCode":surveyCode,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    location.reload();
                })
            }
        }else {
            var title = null, url = null;
            if(btnCode == '1000'){
                height = $(parent.document).outerHeight() - 90;
                width = $(document.body).outerWidth() - 20;
                title = '修改';
                url = "${ctx}/baseSurvey/edit?id="+id+"&surveyCode="+surveyCode+"&priceModelId="+'${id}'
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

    var addPrice = function(id,surveyCode,type){
        openDialog({
            frame:true,
            title:"添加区域类别价格",
            height:800,
            width:$(document.body).outerWidth() - 20,
            url:"${ctx}/baseSurvey/edit?surveyCode="+surveyCode+"&priceModelId="+id+"&type="+type
        });
    }
</script>
</body>
</html>