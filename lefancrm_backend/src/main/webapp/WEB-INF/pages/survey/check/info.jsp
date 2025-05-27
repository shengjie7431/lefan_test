<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/js/layui/layui_exts/soulTable.css">
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
                <label class="layui-form-label">清单名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="listName" value="${dto.listName}" placeholder="请输入清单名称"  autocomplete="off" class="layui-input " required>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">结算投产比</label>
                <div class="layui-input-inline">
                    <input type="number" name="tcb" value="5.5" placeholder="投产比"  autocomplete="off" class="layui-input " required>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">清单模版</label>
                <div class="layui-input-inline">
                    <button type="button" class="uploadBtn layui-input" id="test1">
                        <a href="https://ddrapi.shlefan.com/sftp/files/product/ddr/zhongan/zhonganV1.xlsx" target="_blank">点击下载</a>
                    </button>

                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">上传模版</label>
                <div class="layui-input-inline upload-div">
                    <button type="button" class="uploadBtn layui-input" id="uploadFile">
                        点击上传
                    </button>
                    <%--<input type="text" name="name" value="点击上传"  autocomplete="off" class="layui-input ">--%>
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
            url: '${ctx}/survey/check/ajaxData', //改成您自己的上传接口
            data: {
                btnCode: 'create'
            },
            auto: false,
            bindAction: '#lf-btn-active',
            accept: 'file', //普通文件
            exts: 'xlsx|xls', //只允许上传压缩文件
            before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                layer.load(); //上传loading
                this.data={
                    btnCode: 'create',
                    listName: $('input[name=listName]').val(),
                    tcb: $('input[name=tcb]').val()
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

                if (!$('input[name=listName]').val()){
                    layer.msg('请输入清单名称',{
                        icon: 6
                    })
                    $('.lf-btns .lf-btn.active').addClass('poi-none')
                }
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
                if (!$('input[name=listName]').val()){
                    layer.msg('请输入清单名称',{
                        icon: 5
                    })
                    return false;
                }
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


    function create(){
        var params ={
            btnCode: 'create',
            listName: $('input[name=listName]').val(),
            tcb: $('input[name=tcb]').val()
        }

        $.ajax({
            url: '${ctx}/survey/check/operate',
            data: params,
            success: function (res) {
                res = JSON.parse(res)
                if (res.isSuccess){
                    layer.msg('操作成功',{
                        time: 1500,
                        icon: 1
                    },function(){
                        $('.lf-btn').removeClass('poi-none')
                        parent.reload();
                    })
                }
            }

        })
    }
</script>
</html>
