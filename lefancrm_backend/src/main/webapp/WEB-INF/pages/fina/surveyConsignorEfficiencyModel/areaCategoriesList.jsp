<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all">
   <style>
       .lf-select-block {
           padding: 0 8px !important;
           white-space: nowrap;
           background-color: #3BA9FF;
       }
       .export-btn{
           width: 90%;margin: 0 auto;text-align: center;display: flex;justify-content: center;
       }

   </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>列表</h3>
    </div><!--main-top-->

    <div class="panel panel-info">
        <form id="editForm" role="form" action="${ctx}/finaManager/operate" method="post">
            <input type="hidden" name="surveyCode" value="${surveyCode}">
            <input type="hidden" name="btnCode" value="${btnCode}">
            <input type="hidden" name="id" id="id" value="${id}">
            <table class="table table-hover">
                <thead>
                <button onclick="add('${id}','consignorEfficiencyModelArea')" type="button" class="btn btn-default">添加区域类别</button>
                <button onclick="addPrice('${id}','surveyEfficiency','${type}')" type="button" class="btn btn-default">设置时效</button>
                <button data-id="${id}" type="button" class="btn btn-default copyPrice">快速从价格复制区域类别</button>
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
                        <c:forEach items="${item.areaCities}" var="citys">
                            ${citys.areaName}
                        </c:forEach>
                        </td>
                        <td>
                            <a href="javascript:operate('${item.id}','consignorEfficiencyModelArea','1000',false);">修改</a>
                            <a href="javascript:operate('${item.id}','modelAreaDel','9999',true);">删除</a>
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


<script type="text/html" id="export-content">
    <div class="layui-form">
        <div class="layui-form-item">
            <div id="lf-export" class="selectMul" style="width: 80%;margin: 50px auto;"></div>
        </div>
        <div class="layui-inline export-btn">
            <label class="layui-form-label" >
                <button type="button" class="layui-btn s-btn layui-btn-primary" data-type="cancel">取消</button>
            </label>
            <label class="layui-form-label">
                <button type="button" class="layui-btn s-btn layui-btn-normal" data-type="export">生成区域类别</button>
            </label>
        </div>
    </div>
</script>



<div id="dialogId"></div>
<script src="${ctx}/js/layui/layui.js"></script>
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
            url:"${ctx}/finaManager/add?surveyCode="+surveyCode+"&modelId="+id
        });
    }

    function operate(id,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/finaManager/operate",param = {"id":id,"surveyCode":surveyCode,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    location.reload();
                })
            }
        }else {
            var title = null, url = null;
            if(btnCode == '1000'){
                height = $(parent.document).outerHeight() - 90,
                width = $(document.body).outerWidth() - 20;
                title = '修改';
                url = "${ctx}/finaManager/edit?id="+id+"&surveyCode="+surveyCode+"&modelId="+'${id}'
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
            title:"添加区域类别时效",
            height:800,
            width:$(document.body).outerWidth() - 20,
            url:"${ctx}/finaManager/edit?surveyCode="+surveyCode+"&modelId="+id+"&type="+type
        });
    }
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })
    layui.use(['xmSelect', 'layer', 'form'], function () {
        var xmSelect = layui.xmSelect,
            layer = layui.layer,
            form = layui.form
        $('.copyPrice').on('click', function () {
            var _this = $(this)
            openIndex = layer.open({
                type: 1,
                title: '价格模板',
                area: ['400px', '360px'],
                content: $('#export-content').html(),
            });
            var exportDemo = xmSelect.render({
                el: '#lf-export',
                theme: {
                    color: '#3BA9FF',
                },
                clickClose: true,
                radio: true,
                model: {
                    label: {
                        type: 'xxxx', //自定义与下面的对应
                        xxxx: {
                            template(data, sels) {

                                if (sels.length == data.length) {
                                    return '<div>全部</div>'
                                } else {
                                    var _html = ''
                                    sels.filter(function (cur) {
                                        _html +=
                                            '<div class="xm-label-block lf-select-block">' + cur
                                                .name + '</div>'
                                    })
                                    return _html
                                }
                            }
                        },
                    }
                },
                data: []
            })
            $.ajax({
                url: '${ctx}/finaManager/selectInfoByRelationId',
                type: "POST",
                data:{
                    modelId: $("#id").val(),
                    surveyCode:"copyPrice"
                },
                success: function (v,res) {
                    console.log(res.data)
                    // res = JSON.parse(res.data)
                    res = res.data;
                    var _data = []
                    res.results.map(function(cur){
                        _data.push({
                            name: cur.name,
                            value: cur.id
                        })
                    })
                    exportDemo.update({
                        data : _data
                    })

                }
            });
            $('.export-btn button').click(function () {
                var _this = $(this)
                if (_this.attr('data-type') == 'cancel') {
                    layer.close(openIndex)
                } else if (_this.attr('data-type') == 'export') {
                    if (!exportDemo.getValue('valueStr')){
                        layer.msg( '请选择',{
                            time: 2000,
                            icon: 5
                        })
                        return ;
                    }
                    console.log($("#id").val(),exportDemo.getValue('valueStr'));
                    var url = "${ctx}/finaManager/operate",param = {"modelId":$("#id").val(),"surveyCode":"copyPrice","selPriceModelId":exportDemo.getValue('valueStr')};
                    if(confirm('是否确认？')){
                        ajaxSubmit(url,param,function(v,e,p){
                            location.reload();
                        })
                    }
                }
            })
        })

    })

    var copyPrice = function(id){

    }
</script>
</body>
</html>