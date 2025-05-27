<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/css/layer.css?v=1">
    <link href="${ctx}/caseMid/css/xiangce.css" rel="stylesheet" type="text/css" />
    <script>

    </script>

    <style>
        .table-title{
            font-size: 14px;
            font-weight: bold;
            line-height: 40px;
        }
        table.spec-info tbody tr td:nth-of-type(2n + 1) {
            width: 10%;
        }
        table.spec-info tbody tr td:nth-of-type(2n) {
            width: 20%;
        }
        table.spec-info tbody tr td:nth-of-type(6n) {
            width: 30%;
        }
        .div1{
            float: left;
        }
        .div1 button{
            margin: 0 1px;
            padding: 0 9px;
        }
        td div.pad5{
            padding: 5px;
        }
    </style>
</head>
<body>

<div class="main">
    <input type="hidden" name="id" value="${applyCorporation.id}">
    <input type="hidden" name="surveyCode" value="${surveyCode}">
    <div class="title">
        <button class="butList active" onclick="operate('${applyCorporation.id}','${surveyCode}','1000',false);">修改</button>
        <button class="butList defuelt" onclick="operate('${applyCorporation.id}','${surveyCode}','9999',true);">删除</button>

    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>名称</td>
                    <td colspan="5">${applyCorporation.name}</td>
                </tr>
                <tr>
                    <td>创建人</td>
                    <td>${applyCorporation.createBy}</td>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${applyCorporation.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>更新时间</td>
                    <td><fmt:formatDate value="${applyCorporation.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
                </tbody>
            </table>
        </div>

        <br>
        <%--<c:if test="${applyEnumItem != null}">--%>
            <%--<div class="table-title">名下产品信息</div>--%>
            <%--<div class="panel-heading">--%>
                <%--<div class="pin">--%>
                    <%--<form class="form-inline" role="form" action="${ctx}/baseSurvey/info" method="post">--%>
                        <%--<input type="hidden" name="surveyCode" value="applyCorporation">--%>
                        <%--<input type="hidden" name="id" value="${applyCorporation.id}">--%>
                        <%--<div class="form-group">--%>
                            <%--产品名称:--%>
                            <%--<select name="billingEnumId" class="form-control">--%>
                                <%--<option value="">全部</option>--%>
                                <%--<c:forEach items="${applyCorporationEnum}" var="item">--%>
                                    <%--<option <c:if test="${billingEnumId == item.billingEnumId}">selected="selected" </c:if> value="${item.billingEnumId}" >${item.billingEnumName}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                        <%--<div class="btn-group">--%>
                            <%--<button id="batchOperateBtn" type="submit" class="btn btn-default">查询</button>--%>
                            <%--&lt;%&ndash;<button onclick="add('applyEnumItem','${applyCorporation.id}')" type="button" class="btn btn-default">添加</button>&ndash;%&gt;--%>
                        <%--</div>--%>
                    <%--</form>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<br>--%>
            <%--<table class="table table-hover">--%>
                <%--<thead>--%>
                <%--<tr>--%>
                    <%--<th width="100">产品名称</th>--%>
                    <%--<th width="100">项目名称</th>--%>
                    <%--<th width="100">操作</th>--%>
                <%--</tr>--%>
                <%--</thead>--%>
                <%--<tbody class="class-list">--%>
                <%--<c:forEach items="${applyEnumItem}" var="item">--%>
                    <%--<tr>--%>
                        <%--<td>${item.billingEnumName}</td>--%>
                        <%--<td>${item.billingItemName}</td>--%>
                        <%--<td>--%>
                            <%--<button class="butList defuelt" onclick="operate('${item.id}','applyEnumItem','9998',true);">删除</button>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
                <%--</tbody>--%>
            <%--</table>--%>
        <%--</c:if>--%>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </div>
</div>

<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>

<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script type="text/javascript">
    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

    /**
     * 关闭dialog
     */
    function closeDialog(){
        var closeBtn = $("#diglog_close_btn");
        if(closeBtn.size() == 0){
            closeBtn = $("#diglog_close_btn",window.parent.document);
        }
        closeBtn.click();
    }

    /**
     *
     */
    function operate(id,surveyCode,btnCode,ajax){
        var height = 400,width = 800;
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"id":id,"surveyCode":surveyCode,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    if(btnCode == '9999'){
                        reloadParent();//删除时，需刷新父级
                    }else{
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

    var add = function(surveyCode,applyCorporationId){
        openDialog({
            frame:true,
            title:"添加",
            height:500,
            width:800,
            url:"${ctx}/baseSurvey/add?surveyCode="+surveyCode+"&applyCorporationId="+applyCorporationId
        });
    }

</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>