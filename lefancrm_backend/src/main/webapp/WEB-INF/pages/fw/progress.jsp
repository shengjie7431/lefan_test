<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<jsp:useBean id="dateValue" class="java.util.Date"/>
<!DOCTYPE html>
<html>
<head>
    <title>跟踪进度</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<style>
    .stepList {
        margin: 0 32px;
    }

    .stepItem {
        display: flex;
    }

    .stepDate {
        width: 140px;
        /*font-size: 28px;*/
        color: #555;
    }

    .stepDot {
        width: 60px;
        position: relative;
        border-left: solid 1px #54b0ff;
    }

    .stepDot-dot {
        box-sizing: border-box;
        border: solid 1px #54b0ff;
        background: #fff;
        width: 15px;
        height: 15px;
        border-radius: 15px;
        position: absolute;
        left: -8px;
    }
    .stepDot-dot.finished{
        background: #54b0ff;
    }
    .stepDetail {
        padding-left: 30px;
        flex: 1;
        color: #555;
        padding-bottom: 50px;
    }

    .stepDetail-title {
        /*font-size: 34px;*/
        line-height: 1.5;
    }

    .stepDetail-detail {
        font-size: 24px;
    }

    .finished.stepDot-dot {
        background: #54b0ff;
    }
    .stepItem.omit {
        min-height: 96px;
    }
    .stepDot.omit {
        width: 60px;
        position: relative;
        border-left: dashed 1px #54b0ff;
    }

</style>
<body>
<div class="container">
    <div class="main-top" style="float: left;width: 20%;border-right: 1px solid #888888;height: 700px;">
        <input type="hidden" id="fwId" value="${fwId}">
        <span style="color: red;">*</span>跟踪内容：<textarea id="progressDesc" style="width: 100%;height: 350px;" placeholder="请填写跟踪内容"></textarea><br/>
        <input style="width: 196px;height: 45px;" type="button" class="btn-info"  value="添加跟踪" id="btnAddProgress" />
    </div><!--main-top-->
    <div style="float: right;width: 70%;">
        <c:forEach items="${progressList}" var="item">
            <div class="stepItem" style="margin-left: 10px">
                <div class="stepDate"><fmt:formatDate value="${item.progressTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                <div class="stepDot"><div class="stepDot-dot finished"></div></div>
                <div class="stepDetail"><div class="stepDetail-title"><span style="color: blue;">(${item.createBy})</span>${item.progressName}<br><c:if test="${item.progressDesc !=null && item.progressDesc !=''}">
                    (${item.progressDesc})
                </c:if><br>
                </div><div class="stepDetail-detail"></div></div>
            </div>
        </c:forEach>
        <c:if test="${progressList.size() == 0}">
            <span style="color: #888888">无跟踪信息</span>
        </c:if>
    </div>
    <div class="main-bottom">

    </div><!--main-bottom-->

</div><!--main end-->


<script src="${ctx}/js/jquery-3.4.1.js" charset="utf-8"></script>
<script src="${ctx}/js/layui/layui.js" charset="utf-8"></script>
<script>

    layui.use(['upload','layer'], function () {
        var layer = layui.layer;


        $("#btnAddProgress").bind("click",function(){
            var progressDesc = $("#progressDesc").val();
            if (!progressDesc){
                layer.msg('跟踪内容必填',{
                    icon: 5
                })
                return;
            }
            var params ={
                fwId: $("#fwId").val(),
                progressDesc: progressDesc,
                progressName: "",
                btnCode : "progress"
            }
            $.ajax({
                url: '${ctx}/fw/operate',
                data: params,
                success: function (res) {
                    res = JSON.parse(res)
                    if (res.isSuccess){
                        layer.msg('操作成功',{
                            time: 1500,
                            icon: 1
                        },function(){
                            window.location.reload();
                        })
                    }
                }

            })
        })
    })
</script>
</body>
</html>
