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
            <button class="layui-btn layui-btn-normal" style="width: 150px"><a href="${ctx}/doc/互助清单模板.xlsx" style="color: #fff">下载清单模板</a>
                <i
                        class="layui-icon layui-icon-export"></i></button>
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
                <div class="layui-inline">
                    <label class="layui-form-label">案件数量合计：</label>
                    <div class="layui-input-inline layui-form-label-num caseNum">0</div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">申请委托方价格合计：</label>
                    <div class="layui-input-inline layui-form-label-num price">0.0</div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">保司终审通过时间</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input paramTime" readonly id="passTime" placeholder="请选择日期">
                    </div>
                </div>
                <div class="layui-inline">
                    <!-- <button lay-submit class="ll-submit layui-btn layui-btn-primary" lay-filter="submit">取消</button> -->
                    <button lay-submit class="ll-submit layui-btn layui-btn-normal" lay-filter="submit">确认终审通过
                    </button>

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

        var passTime = laydate.render({
            elem: '#passTime',
            isInitValue: true,
            type:'datetime'
        });
        var checked = []
        var _data = []
        var _cols = [
            [{
                type: 'checkbox',
                LAY_CHECKED: true
            }, {
                field: 'claimsNo',
                minWidth: 170,
                title: '互助案件编号'
            }, {
                field: 'surveyPerson',
                minWidth: 130,
                title: '被调查人',
                templet: function (d) {
                    return d.surveyPerson
                }
            }, {
                field: 'serviceName',
                minWidth: 140,
                title: '业务类型',
            }, {
                field: 'applyinsMoney',
                minWidth: 170,
                title: '申请委托方价格',
            }, {
                field: 'createTime',
                minWidth: 170,
                title: '委托时间',
                templet: function (d) {
                    return layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss");
                }
            }, {
                field: 'reviewTime',
                minWidth: 170,
                title: '平台复审通过时间',
                templet: function (d) {
                    return layui.util.toDateString(d.reviewTime, "yyyy-MM-dd HH:mm:ss");
                }
            }, {
                field: 'endTime',
                minWidth: 170,
                title: '案件截止时间',
                templet: function (d) {
                    return layui.util.toDateString(d.endTime, "yyyy-MM-dd HH:mm:ss");
                }
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
            table.on('checkbox(test)', function (obj) {
                var caseNum = $('.caseNum'),
                    price = $('.price')
                if (obj.type == "one") {//单选
                    if (obj.checked) {
                        riskNum = riskNum + 1;
                        applyinsMoney = parseFloat(applyinsMoney) + obj.data.applyinsMoney;
                    } else {
                        riskNum = riskNum - 1;
                        applyinsMoney = parseFloat(applyinsMoney) - obj.data.applyinsMoney;
                    }
                } else {//全选
                    var checkStatus = table.checkStatus('test');
                    if (checkStatus.data.length > 0) {
                        riskNum = checkStatus.data.length;
                        applyinsMoney = 0;
                        checkStatus.data.forEach(function (item, index) {
                            applyinsMoney += item.applyinsMoney;
                        })
                    } else {
                        riskNum = 0;
                        applyinsMoney = 0;
                    }
                }
                applyinsMoney = parseFloat(applyinsMoney).toFixed(2);
                caseNum.text(riskNum);
                price.text(applyinsMoney);
            });
        };

        form.on('submit(submit)', function (obj) {
            var passTime = $('#passTime').val()
            var data = table.checkStatus('test').data;
            if (!data.length) {
                layer.msg('至少勾选一条数据！', {
                    time: 2000,
                    icon: 5
                })
                return;
            }
            if (!passTime) {
                layer.msg('保司终审通过时间不可为空！', {
                    time: 2000,
                    icon: 5
                })
                return;
            }
            for (let i = 0; i <data.length ; i++) {
                checked.push(data[i].id);
            }
            for (let i = 0; i <20 ; i++) {
                checked.push(2);
            }

            var params = {
                passtime: passTime,
                caseData: checked
            }
            layer.confirm('确认终审通过？', {
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
                        if ( res.isSuccess){
                            alert("处理成功");
                            closeDialog();
                            parent.location.reload();
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
                exportType: 'ddData',
                staffPaySlipId: $('#id').val()
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
                    let successMsg = '导入成功' + res.successNum + '条数据 \n' + '导入失败' + res.failNum + '条：' + res.failData;
                    layer.alert(successMsg, {
                        icon: 1
                    })
                    var caseTotal = res.successData.length;
                    var appMoney = 0.0;
                    for (let i = 0; i < res.successData.length; i++) {
                        appMoney += res.successData[i].applyinsMoney;
                    }
                    riskNum = caseTotal;
                    applyinsMoney = appMoney.toFixed(2);
                    $(".caseNum").text(caseTotal);
                    $(".price").text(appMoney.toFixed(2));

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