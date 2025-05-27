<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/css/viewer.min.css">
    <style>
        .layui-form-content{
            width: 600px;
            margin: 30px auto;
        }

        .lf-select-block {
            padding: 0 8px !important;
            white-space: nowrap;
            background-color: #3BA9FF;
        }
        .lf-bars {
            width: 100%;
            display: flex;
        }

        .lf-bars .lf-bar {
            width: 160px;
            height: 36px;
            line-height: 36px;
            border: 1px solid #3BA9FF;
            color: #3BA9FF;
            text-align: center;
            cursor: pointer;
        }

        .lf-bars .lf-bar.active {
            background-color: #3BA9FF;
            color: #fff;
        }
        .submit-btn2, .submit-btn3{
            display: none;
        }
        .layui-btn{
            margin-right: 16px;
        }

        .files {
            margin-top: 10px;
            width: 360px;
            display: flex;
            flex-wrap: wrap;
            /*max-height: 330px;*/
            overflow: auto;
        }

        .files .file {
            width: 70px;
            height:70px;
            border: 1px solid #d0c9c9;
            margin-bottom: 16px;
            margin-right: 16px;
            position: relative;
        }

        .files .file .image {
            width: 100%;
            height: 100%;
            display: block;
            margin: 0 auto;

        }

        .files div.file-add {
            width: 70px;
            height: 70px;
            border: 1px dashed #d0c9c9;
            margin-bottom: 16px;
            margin-right: 16px;
        }

        .icon-add {
            margin-left: 34px;
            margin-top: 15px;
            display: inline-block;
            background: #aaa;
            height: 40px;
            position: relative;
            width: 2px;
        }

        .icon-add:after {
            background: #aaa;
            content: "";
            height: 40px;
            left: 0;
            position: absolute;
            top: 0;
            width: 2px;
            transform: rotateZ(90deg);
        }

        .file-title {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 20px;
            line-height: 20px;
            font-size: 10px;
            background-color: rgba(187, 187, 187, 0.7);
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .file-del {
            position: absolute;
            top: 2px;
            right: 2px;
            display: inline-block;
            width: 16px;
            height: 16px;
            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAQG0lEQVR4Xu2dCZAc1XnH//+eEVgHOOAysRSMjQEhEQiWhTHY2BYCabdHCO3MeqUCmThUUiQYUrYDxgQSLAJ24fsom4SQckHKkgXr7ZZW2u7VIkWLIKJCkDG3YuP4PrAJhyNWkTQ7X+r17C57zfQxfc1sd5VKKs173/mb1z393vse0YKXrF55Mo7SFqOiLQJkEYBTQBwLwRyQs52/of6NYxz3Bf8LYAjEECBDAA5C8HsQz0NkP4T7AXmOW/p/0mrhYrM7JKtXz0F+eDmAdgAXgDg7Op/k/xwYKA+B0g/t1d3sfuRgdPqil9yUAEjnykWoaDpAlfQPgjw6+lBNq+EgRAZB2ChXbPbueD4hOwKrbRoApHPFaRjOXwFiLcjTA3scaUd5BsB9qGATt9g/ilRVSMJTDYB06KdAw+UQrgVxZkg+xyRGHofgPhw58h1u3/mzmJT6VpNKAKSknwfBrSBX+vYolR2kF8QG9tiPp828VAEgpVUXAvL3AC5MW6DCsUcslPlp9lqPhSOvcSmpAEA62pdB4+0A39e4S00hYSdQuYlG/38mbW2iAEhX4S0o42vOg91Mu0QE5LdQGbqeWwZfScr9RAAQQEOxcC0gt4E8NinnU6FX8CJQuZ5m/71J2BM7ANKpL4HgHoB/koTDKda5F4IraVo/iNPG2ACQZcvyOG7ObSBugBoBsmu6CByGyM0w7S9RvaCO4YoFAOfd/KxcD8AlMfjUCir2oHxoLXt3vRC1M5EDIB3tV0HjlwHOjdqZlpIvUA+GV9C0tkfpV2QASNf5szH8B5sAdkTpQMvLFnyTpnVtVH5GAoB0LZuH8pxdIM6NyvAZJVfERn5eB7u7D4ftd+gASJf+ZpSxC+RZYRs7o+UJHkI5185t29R6hdCuUAGQkn4ilKHk20OzMBP0egQE+5A/fBG7d74aVlhCA0AubTsV+dwggD8Ky7hMzjQREDyNvCxnt/27MOITCgBSapsP0R4DuSAMozIZLhFwIBg6n92DBxqNVcMASFfb8SjnHgGxsFFjsv4+IiDyMH722+Xct++Ij15TmjYEgPNTr3ycSn6E6/Aaca/V+0ovDLtIoBLU08AAyNKls3DSCQMglwVVnvULIQKCb9O0rggqKTgARf3bINcHVZz1CzECIn9H0/5MEImBAJBi+0dA7Z4gCrM+kUSgAsgHaNj/7le6bwCchZrkk6CzuSK70hOB3yB3eJHfdwS+AKhO6c5+AuQZ6fE7s+T1CIhFw17lJyL+ACjpdwK82o+CrG3MERB8jKb1da9aPQMgxUK7swMmu9IfAZYXs2dgvxdDPQEgXV1HYfjAjwCe6EVo1ibxCOylYXlaYe0NgKL+GZA3Je5WZoD3CIh8mKa90a2DKwBSLCwE5BmQeTdh2ecpioBabZwfOtltvsADAPpekOenyLXMFM8RkG/QsP+6XvO6AEhJXwdws2d9WcP0RUB4Fs2+p2sZVhMAAYiS/gOAp6bPq8wizxEQGDStTv8AZN9+zzFOdUO1Ba2CM7nVfnY6O2uPAEVdPfhlb/xSnV2Pxolspmlf5hkAKbaXQK3Ho/gYmslrED4DyukA3xiDwpBUiNoG/pZUvD8RnD7dtrNpRwApFr6fmkUeghtoWl8YzYgU9fUgvplyEO5COf9J9vaq6mOQYvsFADeBfGtIZPkXI3IvTfvPJnecAoCsaXs/crk9/jVE0UP+mYb9l5Mlj2ww3Z1KCERupGl/borNicdVjqBy8ITJW9GnAlDS7wJ4VRTp9C2zwnO4pW/fdP1SCUGN5I+NXqXCTwGc5DsOYXUQXE3T+qfx4qYCUCy8BOK4sHQ2JCeH+ey2flNLhqwpvAc52ZWSfYcbaFi31vNXioX/SHS3lMjDNO331wRAOvQOaDQbSlqYnUUuomn/W92gViHYCXBemKp9yrqdhqVqG9W9pKi/moKCGG+nYamRyLkmjABSLPSo1z9ujsT4uadZLeloOxeapkaC+CEQ3EHT+lu3mEixcA2Ib7i1i/5zuZmG/dkpAMjKlXMxL/cywFnRG+FHg/v7bCXNgYC53fEuVZOv0rA/4eaNFNvPBxWgmO3WNvrP5Xka9mlTAegoXAYNm6I3IIgGjxCU9PdBOBAPBM2Y/NFx/8hC9jzwwwm3ACkV/gXAnwdJTzx90gRBEye/mqy/omHdNRmAZH+ieKLIBwSAejB8gyexvhp5Tb6uilirJXQpGPYnOSjopmk5pfmch8BqTV42SaVrrxCoqqMVK1wI5B9p2B9140WK+gcB7EiwirmLifISDftN4wBQdXw0Z0hojisJCFol+SMZluF30tzxRHUEKBbuB9HVHMkfdUDupmm7vrEcqT/cD+Co4P61WPKdpOM6mtaXRwDQf9yUVT3EIwSd7W0QrTcQBCLfomm7Phynf9if8hzwHZrW5awu+X7tUPBvR8I9o4SgVZPvjADyPZr2Ukpx1ZmgPJVwGhtT7weCCrd7WuHcysmvAnCIpv0GSkehExq+21gGUtDbKwRr9NXQYNSFQGQjTfvDbl413bA/xaHhBZSSfhPAQHvL3QIU++dhQCCyEaZ9hVut3uZPvjMKLKMU9XtB/mnsyYpKoR8Ics7MZ27MlJmU/Opt4Co1Ajzceid1ePzZVtI/BLDbAcDrsF9quxjIPRAVv/HKlc8rAJ5oydr9XkcCBwJ8CIZ9uVuxJXGSr6m3iymbMQ2IjXrQlVJBHVAwNj0YUFQ6u3mEwIvxLZf86rC3RY0AP0/FsmUvWQjSJgQIWjP5TjD3qIfAF0E6EwMtezUAQQsnX70OflrdAlT16fRNWYZNYwAIWjr51QffXykAYjmbJux8BpIn8q807Y946dvyya8G4aACQM0DNDBT5iWcKWmTATA5EYdnxjNAdbjzNH08PkLOKCCamjtI6nj6aL85ghcVAM05FewnNAGSPyq+JV751o7VjxUAT7b08S4NJL/lIRA8oZ4BVH3Z9/r5QjVN2xCS39IQqK1iUtL7AbY1TVK9Guox+TL6Kjg3bz27u4friW+924FYCoDNANd5jWtTtBP5Gk374262VpM/OhmE+2larnGorjEMe7Wxm6URfa4mwKTlikB6XDGsFoZMng6GfBeGvdZ9LUCatno1AIfIP7C1av/7SH6tVUGe1wS0BATrFQBq4+LeBjhKSdcQkj/2xOd1VVCTQ6AKcEjxojeBR7+YkiwGM0PkizTtT7p1Fi/rAWcSBAfK80b3BaShcIFb/qb/PIrkB4KAA4nUJwgWNfVm9Nc07QVVAEr6IwDPCyorsX5RJn8cBJ5WCF9aOAd5UYWr4i9SESQBIoM07QtHAfg6wLpFhYPoiLSP1+SrXUFe9wLUMtjrHoFmgkDwBZrWDaO7g9NVG8iNHD/JD7olbLINrQYBK+3s6d9RBcApD5P/PQDNLfYp+Ny1GpfjUyP7AVt9JBApIz9vLru7D48ViZKS/ijAd6cgwXVMkOtp2F9yszGS5L/+TOBts6i6HagSduSxbvbG/rnIgzRt58TX1wEo6neA/FTsxnhWmILk+4Wg2HY2oO1JHwRyCw37tkkAFFaAGPCcj1gbpij5Y3573HySRgik8l6a/Y9MBMA5FHKOqhJ6TKy5dVXmMfnOJI00WAjC1ZhJDZoQApHfwrTnj26CmVwo8m4Qf+E3DJG1H1fMqJ6OZGfovELQ3gVq90cWK6+CBV+haf3NaPOJAJQKHwDwoFdZkbcTnkGz77n6yXe2a20LtxiUX888zkMU9edBnuJXeqjtKe9ij/34tACo/0zTTiEaVv1DrTraL4Km7Qw1QIGFuUMgxcI2EJcEVtF4x/00rMXjxUxXLv5zAG9oXFcIEg5rJ3L79l9OJynZYb+Wb/VrCEqx8BSIM0OITEARE+sET3gIHJUoawpnIYcnA2oIt5tasGDan54sNJ3JH4vgtPWDRf0aYO774QbIp7RpvlA1jozRHwJ5gU/xETWvXEOj/86x8JbaPwpoX0z3djbZhEPatezre9m5rTpzBE4d5uR2YQu20rQ6JidpegBSdW91atqps3fUw+Di9P1MrcO9yPdGNt6+LaJvh3exkx7+aj4Ejn3Tivo+kO/yriFrmd4IiEXDXjWdfbXPDexsL0C0vvQ6lVnmOQIVLOEWa9rnj/o/s7JRwHOMU9tQZICmXXPfh8vh0W2rgNz21DqXGeYegcrwe7hlx6O1Gno5Pt4CqbtrylqkMAL30LCurGeXOwAl/UQI1CvM1twincKshWKS4BXkh09h946XGgLA+R1b1G8GeXsohmVC4omAKgJp2ne7KXMdARwA1FTx8XP+C8A73ARmn6cgAoJ9NK1zvFjiCQAHgup8e91DHL0ozNrEEAFWzmZPv6fX+Z4BqEKgfwWg667bGFzMVNSMgHyKhv15rwHyB8DSpbNw0h8+CuKdXhVk7WKNwG4a1nI/Gn0BUB0FCm+D4Nl4Dmf048oMbyt4AfnhM9ye+idHyTcAI7eCdQA3z/CQp8d9EUFFu4Bb+3zv8g4EwMhIkPKTRtOTnxgsuZWGtSGInuAAdHXlUD4wANLXPSeIkVmfOhEQOKd/BY1RYACcUWD16jmYVVYHTiwJakDWr4EIiAzAtHW3cw7qaWgIAAeCrrbjUdb2gjy9AVeyrv4jsBcHZTltu6Ej/xoGoPo80DYfoj0GcoF/P7Ie/iMgzyB38Dx2Dx7w33dij1AAcCAoFhYCMghyfqNGZf3r3vOfxvChi9m764Uw4hQaAA4Ea1a+Fbm82lhychjGZTImR0AeR+7Iheze+WpYsQkVgOozgf5mlKFGgjPCMjKT40RgN47kLuG2beqAj9Cu0AGoQnDxG1E+aheIpaFZOpMFCbbj5aEiBwfLYYchEgAcCFTVkbn5zQlvhQo7XknIu5OGdU1UiiMDYNRg6dCvhkZV1aP1zyUKM0si/wNN1qs6PmGKnSwrcgCc0eDStlOR09SBzWdF6UzLyBbZhTwuY7f9u6h9igUABwJnKvmEzwK4DmRseqMOYKjyRQ6BuJGG/dVQ5dYRFnsipNT+bgjvyX4lTMnKHoBX0uj777iSr/TEDkD1V0JXDsMH1MqiWwHOjdPh1OlS8/iQ62jaG5OwLREAxh4QL12xALlZd4JYk4TzCeusAHIXDuJG2raq0ZjIlSgAYyA45Vy1O2bOUjPpBbFhfKmWRLKf1C2glrMjBR5vaclDrNSqHWIrhnkLt1pPJZXwRH4G+nVWqvUJFAiqaFWzXxUIelCRDdxqP5s2Z1JxC6g9IrT9MYa1dSDXgViYtuDVtUegNmTeh+Ejm9n7wK/SanuqARgfNOnUl6ACBcPa1M42qoogwP0gNtKwf5HWpI+3q2kAmACDU8hKdAgKoDr0krOSCbYcgHAXgH5Uyn3cOvDzZOwIrrUpAZgAg64fi6O5Ahougci50b5gktdGahU9CFT6aexISY3CGQzAdK5L54rTUM4vQg6LIVhUvWXwGEDUhNSc6h/OHis4VS1CNQRiCBA13z4E4SsgfgjIfqDyHKDtb5Zh3Q8O/w83dkMy4BMWvQAAAABJRU5ErkJggg==);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }

        .poi-no{
            pointer-events: none;
        }

        .files .file .img-pdf{
            background: url(data:image/png;base64,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);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            width: 59%;
            height: 59%;
            display: block;
            margin: 0 auto;
            margin-top: 20%;
        }
    </style>
</head>
<body>
<div class="layui-form layui-form-content">
    <input id="surveyCode" value="${surveyCode}" hidden>
    <input id="modelType" value="${info.modelType}" hidden>
    <input id="id" value="${info.id}" hidden>
    <input id="modelPath" value="${info.modelPath}" hidden>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label label-input" style="width: 130px">模板名称</label>
            <div class="layui-input-inline">
                <input type="text" name="modelName" value="${info.modelName}" class="layui-input repay-no" autocomplete="off" style="width: 360px">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label label-input" style="width: 130px">模板类型</label>
            <div class="layui-input-inline" style="width: 360px">
                <div id="typeIds" class="selectMul"></div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label label-input" style="width: 130px">模板路径</label>
            <div class="layui-input-inline">
                <div class='files' id="jq21">
                    <div class="file-add uploadFile" >
                        <div class="icon-add v-p-upload"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block" style="margin-left:0;margin-top: 30px;text-align: center">
            <button class="layui-btn layui-btn-primary close-btn" style="width: 100px;">取消</button>
            <button class="layui-btn layui-btn-normal submit-btn" data-type="1" style="width: 100px;">保存并关闭</button>
        </div>
    </div>
</div>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script src="${ctx}/js/jquery-3.4.1.js" charset="utf-8"></script>
<script src="${ctx}/js/viewer.min.js" charset="utf-8"></script>
<script>
    layui.config({
        base: '${ctx}/js/layui/layui_exts/'
    }).extend({
        xmSelect: 'xm-select'
    })

    layui.use(['jquery', 'layer', 'util','laydate', 'upload','xmSelect'], function () {
        var layer = layui.layer,
            $ = layui.$,
            laydate= layui.laydate,
            upload = layui.upload,
            xmSelect = layui.xmSelect,
            layer = layui.layer,
            util = layui.util;
        var demo1 = xmSelect.render({
            el: '#typeIds',
            theme: {
                color: '#3BA9FF',
            },
            radio: true,
            filterable: true,
            filterDone: function(val, list){
                $('.xm-option-content').each(function () {
                    var _this = $(this)
                    _this.attr('title', _this.text())
                })
            },
            // toolbar: {
            //     show: true
            // },
            model: {
                label: {
                    type: 'xxxx', //自定义与下面的对应
                    xxxx: {
                        template(data, sels) {
                            var _html = ''
                            sels.filter(function (cur) {
                                _html +=
                                    '<div class="xm-label-block lf-select-block">' + cur
                                        .name + '</div>'
                            })
                            return _html
                        }
                    },
                }
            },
            data: [{
                name: '垫付协议',
                value: '1'
            },{
                name: '保险理赔授权委托书',
                value: '2'
            }]
        })

        var filePath = '';

        var modelTypeHidden = $("#modelType").val();
        if (modelTypeHidden != null){
            demo1.setValue([modelTypeHidden])
        }
        var modelPathHidden = $("#modelPath").val();
        if (modelTypeHidden != null && modelTypeHidden != ''){
            var fileObj = {};
            fileObj.filePath = modelPathHidden;
            putSessionFiles(fileObj);
            filePath = modelPathHidden;
        }



        $('.submit-btn').click(function () {
            var _this = $(this)
            var files= []
            $('.files .file').map(function (i,cur) {
                files.push({
                    filePath: $(cur).find('img').attr('src')
                })
            })
            var modelName = $("input[name=modelName]").val();
            var modelType = demo1.getValue('valurStr');
            if (modelName == '' || modelType == ''){
                return layer.msg("模板名称或类型不能为空");
            }
            _this.addClass('poi-no')
            $.ajax({
                url: '${ctx}/finaManager/update',
                type: 'post',
                data: {
                    surveyCode: $("#surveyCode").val(),
                    modelName: $("input[name=modelName]").val(),
                    modelType: demo1.getValue('valurStr')[0].value,
                    modelPath: filePath,
                    id:$("#id").val()
                },
                success: function (res) {
                    var res = JSON.parse(res)
                    if (res.isSuccess){
                        layer.msg('成功', {
                            time: 2000,
                            icon: 1
                        },function(){
                            parent.location.reload()
                        })
                    }else{
                        _this.removeClass('poi-no')
                        layer.msg('失败', {
                            time: 2000,
                            icon: 2
                        })
                    }
                }
            })
        })
        $('.close-btn').click(function () {
            closeDialog()
        })

        var uploadFile = upload.render({
            elem: '.uploadFile',
            url: '${ctx}/fina/files/uploadSftp', //改成您自己的上传接口
            data: {
                modelType: 'signModel',
                pathId: 1,
            },
            accept: 'file', //只能上传图片
//            acceptMime: 'image/*',
            before: function (obj) {
                layer.load();
                fileLen = $('.files .file').length
            },
            done: function (res) {
                layer.closeAll('loading')
                if (res.success == 'true') {
                    $(".file").detach()
                    filePath = res.surveyFile.filePath;
                    putSessionFiles(res.surveyFile);
                } else {
                    layer.alert('导入出错', {
                        icon: 2
                    })
                }
            },
            allDone: function (obj) {
                // if (fileLen) {
                //     viewer.destroy()
                // }

                // viewer = new Viewer(document.getElementById('jq21'));
            },
            error: function (index, upload) {
                layer.closeAll('loading')
            }
        });

        function putSessionFiles(surveyFile) {
            // var _html = ' <div class="file">\n' +
            //     '                        <img src="' + surveyFile.filePath + '" class="image"></img>\n' +
            // '                    </div>'
            var _html = ' <a class="file file-pdf" target="_blank" href="' + surveyFile.filePath + '">\n' +
                '<div target="_blank"  class="image  img-pdf"></div>\n' +
                // '<div class="file-title"></div>\n' +
                '                    </a>'
            $('.files').append(_html)
        }


    })

    function closeDialog(){
        var closeBtn = $("#diglog_close_btn");
        if(closeBtn.length == 0){
            closeBtn = $("#diglog_close_btn",window.parent.document);
        }
        closeBtn.click();
    }

</script>
</body>
</html>