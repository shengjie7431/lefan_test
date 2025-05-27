<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/layui/layui_exts/soulTable.css">
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <style>
        .main {
            width: 500px;
            margin: 40px auto
        }

        .chooseCell {
            width: 100%;
        }

        .add {
            display: none;
            margin-left: 10px;
            width: 90px;
            height: 36px;
            line-height: 36px;
            border: 1px solid #eee;
            text-align: center;
            cursor: pointer;
        }

        .add.active,
        .delete.active {
            display: block
        }

        .delete {
            display: none;
            margin-left: 10px;
            width: 90px;
            height: 36px;
            line-height: 36px;
            border: 1px solid #eee;
            text-align: center;
            cursor: pointer;
        }

        .selectMul,.layui-input, .layui-textarea {
            width: 300px;
        }

        .layui-inline {
            width: 550px;
            padding: 10px 0;
        }

        .layui-form-label {
            width: 90px;
        }

        .layui-input-inline {
            width: 400px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }
        .lf-btns{
            width: 100%;
            display: flex;
        }
        .lf-btns .lf-btn{
            width: 160px;
            height: 40px;
            line-height: 40px;
            border:1px solid #3BA9FF;
            color: #3BA9FF;
            text-align: center;
            margin-right: 20px;
            cursor: pointer;
        }
        .lf-btns .lf-btn.active{
            background-color:#3BA9FF;
            color: #fff;
        }
        .poi-none{
            pointer-events: none;
        }
        .form{
            width: 60%;
            margin: 0 auto;
            margin-top: 30px;
        }
        .uploadBtn{
            color: #333;
        }
        .lf-btns{
            width: 100%;
            display: flex;
        }
        .lf-btns .lf-btn{
            width: 160px;
            height: 40px;
            line-height: 40px;
            border:1px solid #3BA9FF;
            color: #3BA9FF;
            text-align: center;
            margin-right: 20px;
            cursor: pointer;
        }
        .lf-btns .lf-btn.active{
            background-color:#3BA9FF;
            color: #fff;
        }
        .poi-none{
            pointer-events: none;
        }
    </style>
</head>
<body>
        <div class="form">
            <div class="layui-inline">
                <label class="layui-form-label">委托时间</label>
                <div class="layui-input-inline">
                    <input id="entrustTime" name="entrustTime" type="text" class="form-control" required="required" style="cursor: auto; background-color:#fff"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
                </div>
            </div>
            <div style="float: right">
                <a onclick="exportData(2)" style="cursor: pointer">下载模板</a>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">上传模版</label>
                <div class="layui-input-inline upload-div">
                    <button type="button" class="uploadBtn layui-input" id="uploadFile">
                        点击上传
                    </button>
                </div>
            </div>
            <div class="lf-btns layui-inline" style="justify-content: center">
                <div class="lf-btn " data-type="1">取消</div>
                <div class="lf-btn active" data-type="2" id="lf-btn-active">立即创建</div>
            </div>
        </div>
    <%--<input type="text" name="listName" value="${dto.listName}" />--%>
    <%--<input type="button" class="form-select-button" value="创建" onclick="create()">--%>
</body>


<script src="${ctx}/js/jquery-3.4.1.js" charset="utf-8"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script>
    var uploadFile = ''

    layui.use(['upload','layer'], function () {
        var upload = layui.upload,layer = layui.layer;
        //指定允许上传的文件类型
        function _done(res) {
        }
        upload.render({
            elem: '#uploadFile',
            url: '${ctx}/hobo/case/ajaxData', //改成您自己的上传接口
            data: {
                btnCode: 'import',
                entrustTime : $("#entrustTime").val()
            },
            auto: false,
            bindAction: '#lf-btn-active',
            accept: 'file', //普通文件
            exts: 'xlsx|xls', //只允许上传
            before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                layer.load(); //上传loading
                this.data={
                    btnCode: 'import',
                    entrustTime : $("#entrustTime").val()
                }
                console.log('-1-',obj)
            },
            choose: function(obj){
                console.log('-2-',obj)
                var files = obj.pushFile();

                obj.preview(function(index, file, result){
                    uploadFile = file
                    $('.upload-div').append('<span class="layui-inline layui-upload-choose">'+file.name+'</span>')
                });

            },
            done: function (res) {
                layer.closeAll('loading')
                if (res.isSuccess){
                    var msg =res.results.msg
                    layer.alert(msg,{icon:6},function(){
                        parent.reload();
                    })

                } else {
                    layer.msg('导入出错',{icon:2})
                }
            },
        });
        $('input[name=listName]').blur(function () {
            if ($(this).val()){
                $('.lf-btns .lf-btn.active').removeClass('poi-none')
            }else {
                $('.lf-btns .lf-btn.active').addClass('poi-none')
            }
        }).keyup(function (e) {
            if (e.keyCode == 13){
                if ($(this).val()){
                    $('.lf-btns .lf-btn.active').removeClass('poi-none')
                }else {
                    $('.lf-btns .lf-btn.active').addClass('poi-none')
                }
            }
        })
        $('.lf-btns .lf-btn').click(function (e) {
            var _this = $(this)
            if (_this.hasClass('active')){

                if (!uploadFile){
                    layer.msg('请上传模版',{
                        icon: 5
                    })
                    return;
                }
            }else {
                //取消
                console.log(2)
                parent.reload();
            }
        })

    })





    function exportData(type){
        var _href = '${ctx}/hobo/case/exportData?exportType=2'
        var aLink = document.createElement('a');
        aLink.href= _href
        aLink.dispatchEvent(new MouseEvent('click', {
            bubbles: true,
            cancelable: true,
            view: window
        }));
        setTimeout(function () {
            _this.removeAttr('disabled')
        },5000)
    }
</script>
</html>
