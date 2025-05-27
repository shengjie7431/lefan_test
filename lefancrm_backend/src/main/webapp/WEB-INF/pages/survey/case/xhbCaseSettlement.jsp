<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <style>
        .main {
            width: 98%;
            margin: 0 auto;
        }

        .main-header {
            padding: 10px 0;
        }

        label.layui-form-label {
            width: auto;
        }

        .layui-form-label-num {
            height: 38px;
            line-height: 38px;
            color: #666;
            font-size: 18px;
            font-weight: bold;
        }

        .layui-input.paramTime {
            widows: 110px;
        }
    </style>
</head>

<body>
<div class="main">
    <input type="text" id="copyText" hidden>
    <div class="main-header">
        <div class="layui-inline">
            <button class="layui-btn layui-btn-normal" style="width: 120px" id="importQD2">上传清单 <i
                    class="layui-icon layui-icon-upload"></i></button>
        </div>
    </div>
    <div class="table-content">
        <table class="layui-table" id="test" lay-filter="test" lay-skin="line" lay-size="lg">
        </table>
    </div>
    <div class="main-footer">
        <div class="layui-form" lay-filter="pass">
            <div class="layui-form-item">
                <div class="layui-inline" style="float: right">
                    <input type="hidden" id="surveyInfoIds" />
                    <input type="hidden" id="money" />
                    <input type="hidden" id="successData" />
                    <button class="ll-submit layui-btn layui-btn" onclick="javascript:closeDialog();">取消</button>
                    <button lay-submit class="ll-submit layui-btn layui-btn-normal" lay-filter="submit">全部标记已结算并开票</button>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
<script src='${ctx}/js/layui/layui.js'></script>
<script>
    var riskNum = 0, applyinsMoney = 0.0;
    layui.use(['form', 'table', 'laydate', 'jquery', 'upload'], function () {
        var form = layui.form,
            table = layui.table,
            laydate = layui.laydate,
            $ = layui.jquery,
            upload = layui.upload;

        var _data = []
        var _cols = [
            [ {
                field: 'claimsNo',
                minWidth: 170,
                title: '互助案件编号'
            }, {
                field: 'surveyPerson',
                minWidth: 130,
                title: '被调查人'
            }, {
                field: 'statusName',
                minWidth: 140,
                title: '当前状态',
            }, {
                field: 'settlementMoney',
                minWidth: 170,
                title: '对账清单结算价格',
            }]
        ]
        setTable(_cols, _data)
        var _h = $('.main-header').outerHeight() + $('.main-footer').outerHeight()+ 80
        var fullH = 'full-' + _h
        function setTable(_cols, _data) {
            table.render({
                id: "test",
                elem: '#test',
                cols: _cols,
                page: false,
                limit: 200,
                height: fullH,
                data: _data,
            })
        };

        form.on('submit(submit)', function (obj) {
            var params = {
                surveyInfoIds: $("#surveyInfoIds").val(),
                type : 'settlement',
                successData : $("#successData").val()
            }
            if(!$("#surveyInfoIds").val()){
                alert("无数据");return;
            }
            layer.confirm('确认结算并开票？', {
                btn: ['是', '否'] //按钮
            }, function () {
                layer.close(layer.index);
                $(this).attr("disabled",true);
                $.ajax({
                    url: '${ctx}/survey/case/xhbCaseBatch',
                    type: "POST",
                    dataType : 'json',
                    contentType:'application/json; charset=UTF-8',
                    data: JSON.stringify(params),
                    success: function (res, param) {
                        if (res.isSuccess){
                            //idArr长度过长，传值（前端报错）
                            var width = $(document.body).outerWidth();
                            var height = $(document).outerHeight() - 10;
                            var money = $("#money").val();
                            //此处的idList，通过获取父姐妹元素的方式获取。  billApplySurvey.jsp的init方法
                            var url="${ctx}/billingApply/billApplyEdit?idList=&billingMoney=" + money + "&btnCode=1501" + "&copy=3"
                                + "&companyName=" + "相互宝" + "&entrustOrgId=105";
                            openDialog({
                                frame:true,
                                title:"详情",
                                height:height,
                                width:1000,
                                url:  url,
                                load:true
                            });
                            // closeDialog();
                        }else {
                            alert("处理失败")
                        }
                    }
                })
            });
        });

        upload.render({
            elem: '#importQD2',
            url: '${ctx}/survey/case/xhbCaseBatchUpload', //改成您自己的上传接口
            data: {
                exportType: 'ddDataSettlement'
            },
            accept: 'file', //普通文件
            exts: 'xlsx|xls', //只允许上传压缩文件
            before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                layer.load(); //上传loading
            },
            done: function (res) {
                layer.closeAll('loading')
                if (res.isSuccess) {
                    setTable(_cols, res.successData)
                    $("#surveyInfoIds").val(res.successDataSurveyInfoIds);
                    $("#money").val(res.money);
                    $("#successData").val(JSON.stringify(res.successData));
                    var successMsg = '导入成功' + res.successNum + '条数据 \n' + '导入失败' + res.failNum + '条：' + res.failData;
                    layer.alert(successMsg, {
                        icon: 1
                    })
                } else {
                    layer.alert(res.msg, {
                        icon: 2
                    })
                }
            },
        });
    });
</script>
</body>

</html>