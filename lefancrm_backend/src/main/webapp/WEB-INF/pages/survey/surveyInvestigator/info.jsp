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
    <input type="hidden" name="id" value="${surveyInvestigator.id}">
    <input type="hidden" name="surveyCode" value="${surveyCode}">
    <div class="title">
        <button class="butList defuelt" onclick="operate('${surveyInvestigator.id}','${surveyCode}','1000',false);">修改</button>
        <c:if test="${surveyInvestigator.authType==2}">
            <button class="butList defuelt" >认证成功</button>
        </c:if>
        <c:if test="${surveyInvestigator.authType==3}">
            <button class="butList defuelt" >认证失败</button>
        </c:if>
        <c:if test="${surveyInvestigator.authType==0 || surveyInvestigator.authType==1 || surveyInvestigator.authType==null}">
            <button class="butList defuelt" onclick="operate('${surveyInvestigator.id}','${surveyCode}','9999',true);">删除</button>
            <button class="butList active"  onclick="operate('${surveyInvestigator.id}','${surveyCode}','1100',true);">通过认证</button>
            <button class="butList defuelt" onclick="operate('${surveyInvestigator.id}','${surveyCode}','1200',true);">驳回认证</button>
        </c:if>
        <button class="butList defuelt" onclick="operate('${surveyInvestigator.userId}','userInfo','9998',true);">设置默认密码</button>
        <button class="butList defuelt" onclick="operate('${surveyInvestigator.userId}','franchisee','1200',false);">更换机构</button>
        <%--<c:if test="${surveyInvestigator.type==1}">--%>
            <%--<button class="butList defuelt" onclick="operate('${surveyInvestigator.id}','${surveyCode}','1300',true);">修改为加盟</button>--%>
        <%--</c:if>--%>
        <%--<c:if test="${surveyInvestigator.type==2}">--%>
            <%--<button class="butList defuelt" onclick="operate('${surveyInvestigator.id}','${surveyCode}','1400',true);">修改为自营</button>--%>
        <%--</c:if>--%>
    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>真实姓名</td>
                    <td>${surveyInvestigator.realName}</td>
                    <td>昵称</td>
                    <td>${surveyInvestigator.nickName}</td>
                    <td>身份证</td>
                    <td>${surveyInvestigator.idcard}</td>
                </tr>
                <tr>
                    <td>手机号</td>
                    <td>${surveyInvestigator.tel}</td>
<%--                    <td>覆盖区域</td>--%>
<%--                    <td>${surveyInvestigator.includeAreaName}</td>--%>
                    <td>第一责任区</td>
                    <td>${surveyInvestigator.resArea}</td>
                    <td>其他覆盖区域</td>
                    <td>${surveyInvestigator.otherOverlayArea}</td>
                </tr>
                <tr>
                    <td>擅长领域</td>
                    <td>${surveyInvestigator.includeBusName}</td>
                    <td>乐凡币</td>
                    <td>${surveyInvestigator.lefanCurrency}</td>
                    <td>成就点</td>
                    <td>${surveyInvestigator.achPoint}</td>
                </tr>
                <tr>
                    <td>基础积分</td>
                    <td>${surveyInvestigator.basicIntegral}</td>
                    <td>银行卡号</td>
                    <td>${surveyInvestigator.bankNo}</td>
                    <td>银行名称(含支行)</td>
                    <td>${surveyInvestigator.bankName}</td>
                </tr>
                <tr>
                    <td>擅长任务类型</td>
                    <td style="padding: 10px" colspan="5">${surveyInvestigator.includeTaskName}</td>
                </tr>
                <tr>
                    <td>带教老师</td>
                    <td>${surveyInvestigator.teacherUserName}</td>
                    <td>机构</td>
                    <td>${surveyInvestigator.orgName}</td>
                    <td>地址</td>
                    <td>${surveyInvestigator.province}${surveyInvestigator.city}${surveyInvestigator.district}${surveyInvestigator.address}</td>
                </tr>
                <tr>
                    <td>认证状态</td>
                    <td>
                        <c:if test="${surveyInvestigator.authType==0}">未认证</c:if>
                        <c:if test="${surveyInvestigator.authType==1}">认证中</c:if>
                        <c:if test="${surveyInvestigator.authType==2}">认证通过</c:if>
                        <c:if test="${surveyInvestigator.authType==3}">认证不通过</c:if>
                    </td>
                    <td>账户状态</td>
                    <td>
                        <c:if test="${surveyInvestigator.accState==0}">正常</c:if>
                        <c:if test="${surveyInvestigator.accState==1}">冻结</c:if>
                        <c:if test="${surveyInvestigator.accState==2}">删除</c:if>
                    </td>
                    <td>调查员类型</td>
                    <td>
                        <c:if test="${surveyInvestigator.type==1}">直营</c:if>
                        <c:if test="${surveyInvestigator.type==2}">合伙</c:if>
                        <c:if test="${surveyInvestigator.type==3}">合作</c:if>
                    </td>
                </tr>
                <tr>
                    <td>是否渠道调查员</td>
                    <td>
                        <c:if test="${surveyInvestigator.channelType==0}">否</c:if>
                        <c:if test="${surveyInvestigator.channelType==1}">是</c:if>
                    </td>
                    <td>称号</td>
                    <td>${surveyInvestigator.titleName}</td>
                    <td>备注</td>
                    <td colspan="3">${surveyInvestigator.remark}</td>
                </tr>
                <tr>
                    <td>创建人</td>
                    <td>${surveyInvestigator.createByName}</td>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${surveyInvestigator.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>更新时间</td>
                    <td><fmt:formatDate value="${surveyInvestigator.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
                <tr>
                    <td>认证材料</td>
                    <td style="padding: 10px" colspan="5">
                        <c:forEach items="${apiRsp.results}" var="item" varStatus="st">
                            <img src="${item.filePath}" width="75;" height="75;" onclick="openImgs(${st.index})"/>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td>手写签名</td>
                    <td style="padding: 10px" colspan="5">
                        <c:if test="${surveyInvestigator.signUrl!=null}">
                            <img src="${surveyInvestigator.signUrl}" width="75;" height="75;" class="picToBig"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>是否有暖哇账号</td>
                    <td>
                        <c:if test="${surveyInvestigator.haveNwAccount==null || surveyInvestigator.haveNwAccount==0}">无</c:if>
                        <c:if test="${surveyInvestigator.haveNwAccount!=null && surveyInvestigator.haveNwAccount==1}">有</c:if>
                    </td>
                    <td>登记执业证</td>
                    <td>
                        ${surveyInvestigator.registerNo}
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
                height = 550;
                width = 1100;
                title = '修改';
                url = "${ctx}/baseSurvey/edit?id="+id+"&surveyCode="+surveyCode
            }else if(btnCode == '1100'){
                height = 500;
                width = 800;
                title = '认证审核';
                url = "${ctx}/baseSurvey/popup?id="+id+"&surveyCode="+surveyCode+"&btnCode="+btnCode
            }else if(btnCode == '1200'){
                height = 600;
                width = 900;
                title = '更换机构';
                url="${ctx}/surveyFranchisee/selectFranchisee?userId="+id+"&surveyCode="+surveyCode+"&btnCode=changeFranchisee";
                load:true
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
            url:"${ctx}/baseSurvey/info?index="+index+"&surveyCode="+'${surveyCode}'+"&id="+'${surveyInvestigator.id}'+"&type=1"
        });
    }

</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>