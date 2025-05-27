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
    <input type="hidden" name="id" value="${surveyConsigner.id}">
    <input type="hidden" name="surveyCode" value="${surveyCode}">
    <div class="title">
        <button class="butList defuelt" onclick="operate('${surveyConsigner.id}','${surveyCode}','1000',false);">修改</button>
        <c:if test="${surveyConsigner.authState==2}">
            <button class="butList defuelt" >认证成功</button>
        </c:if>
        <c:if test="${surveyConsigner.authState==3}">
            <button class="butList defuelt" >认证失败</button>
        </c:if>
        <c:if test="${surveyConsigner.authState==1 || surveyConsigner.authState==0}">
            <button class="butList defuelt" onclick="operate('${surveyConsigner.id}','${surveyCode}','9999',true);">删除</button>
            <button class="butList active"  onclick="operate('${surveyConsigner.id}','${surveyCode}','1100',true);">通过认证</button>
            <button class="butList defuelt" onclick="operate('${surveyConsigner.id}','${surveyCode}','1200',true);">驳回认证</button>
        </c:if>
        <%--<c:if test="${surveyConsigner.isCredit==0}">--%>
            <%--<button class="butList defuelt" onclick="operate('${surveyConsigner.id}','${surveyCode}','1500',true);">修改为授信</button>--%>
        <%--</c:if>--%>
        <%--<c:if test="${surveyConsigner.isCredit==1}">--%>
            <%--<button class="butList defuelt" onclick="operate('${surveyConsigner.id}','${surveyCode}','1600',true);">修改为非授信</button>--%>
        <%--</c:if>--%>
        <button class="butList active" onclick="operate('${surveyConsigner.id}','${surveyCode}','1900',false);">修改部门</button>
        <button class="butList defuelt" onclick="operate('${surveyConsigner.userId}','userInfo','9998',true);">设置默认密码</button>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>用户姓名</td>
                    <td>${surveyConsigner.userName}</td>
                    <td>联系电话</td>
                    <td>${surveyConsigner.tel}</td>
                    <td>单位简称</td>
                    <td>${surveyConsigner.company}</td>
                </tr>
                <tr>
                    <td>单位全称</td>
                    <td>${surveyConsigner.entrustOrgName}</td>
                    <td>部门名称</td>
                    <td>${surveyConsigner.departmentName}</td>
                    <td>单位code</td>
                    <td>${surveyConsigner.code}</td>
                </tr>
                <tr>
                    <td>是否授信</td>
                    <td>
                        <c:if test="${surveyConsigner.isCredit==0}">否</c:if>
                        <c:if test="${surveyConsigner.isCredit==1}">是</c:if>
                    </td>
                    <td>账户状态</td>
                    <td>
                        <c:if test="${surveyConsigner.accState==0}">正常</c:if>
                        <c:if test="${surveyConsigner.accState==1}">冻结</c:if>
                        <c:if test="${surveyConsigner.accState==2}">删除</c:if>
                    </td>
                    <td>邮箱</td>
                    <td>${surveyConsigner.email}</td>
                </tr>
                <tr>
                    <td>认证状态</td>
                    <td>
                        <c:if test="${surveyConsigner.authState==0}">未认证</c:if>
                        <c:if test="${surveyConsigner.authState==1}">认证中</c:if>
                        <c:if test="${surveyConsigner.authState==2}">认证通过</c:if>
                        <c:if test="${surveyConsigner.authState==3}">认证不通过</c:if>
                    </td>
                    <td>创建人</td>
                    <td>${surveyConsigner.createByName}</td>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${surveyConsigner.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
                <tr>
                    <td>抄送邮件集合</td>
                    <td colspan="5">${surveyConsigner.makeEmails}</td>
                </tr>
                <tr>
                    <td>认证材料</td>
                    <td style="padding: 10px" colspan="5">
                        <c:forEach items="${apiRsp.results}" var="item" varStatus="st">
                            <img src="${item.filePath}" width="75;" height="75;"  onclick="openImgs(${st.index})"/>
                        </c:forEach>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
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
                height = 400;
                width = 700;
                title = '修改';
                url = "${ctx}/baseSurvey/edit?id="+id+"&surveyCode="+surveyCode+"&consignorId="+'${surveyConsigner.entrustOrgId}'
            }
            else if(btnCode == '1100'){
                height = 500;
                width = 800;
                title = '授信';
                url = "${ctx}/surveyConsigner/credit?id="+id+"&surveyCode="+surveyCode+"&btnCode="+btnCode
            }
            else if(btnCode == '1900'){
                height = 400;
                width = 650;
                title = '修改部门';
                url = "${ctx}/baseSurvey/popup?id="+id+"&surveyCode="+surveyCode+"&btnCode="+btnCode+"&consignorId="+'${surveyConsigner.entrustOrgId}'
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

    function openImgs(index){
        openDialog({
            frame:true,
            title:"查看图片",
            height:500,
            width:750,
            url:"${ctx}/baseSurvey/info?index="+index+"&surveyCode="+'${surveyCode}'+"&id="+'${surveyConsigner.id}'+"&type=1"
        });
    }
</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>