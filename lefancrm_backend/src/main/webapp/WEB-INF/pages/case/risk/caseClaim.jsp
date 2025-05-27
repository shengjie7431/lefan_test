<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>
        function groBack(){

//            var val = $("#orgId").find("option:selected").text();
            //$("#orgName").attr("value",val);
//            $("#orgName").val(val);

            var checked = [];
            $('input:checkbox:checked').each(function() {
                checked.push($(this).val());
            });
            if(checked == null || checked == ""){
                alert("请选择机构角色！");
                return false;
            }
            return true;
        }

    </script>
    <style>
        .myColSm3{
            width: 30%;!important;
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="container">
    <form id="editForm" role="form" class="form-horizontal" action="${ctx}/user/addUser" method="post">
        <div class="form-group">
            <table class="table table-bordered" >
                <tbody>
               <%-- <tr>
                    <th width="20%" class="active">用户真实姓名</th>
                    <td width="40%">
                       sadsadsad <input type="text" id = "userName" name="userName" value="${newUser.userName}" style="width: 100px"  class="form-control">
                        sadsadsad <input type="text" id = "userName1" name="userName" value="${newUser.userName}" style="width: 100px"  class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">用户类型</th>
                    <td width="80%">
                        <select name="userType" style="width: 400px;" class="form-control" id = "userType">
                            <option value="1" <c:if test="${newUser.userType == 1}">selected </c:if>>普通用户</option>
                            <option value="2" <c:if test="${newUser.userType == 2}">selected </c:if>>机构用户</option>
                            <option value="3" <c:if test="${newUser.userType == 3}">selected </c:if>>测试用户</option>
                            <option value="4" <c:if test="${newUser.userType == 4}">selected </c:if>>其他</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">用户昵称</th>
                    <td width="80%">
                        <input type="text" id = "nickName" name="nickName"  style="width: 400px;" class="form-control" value="${newUser.nickName}">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">用户电话</th>
                    <td width="80%">
                        <input type="text" id = "userTel" name="userTel" style="width: 400px;" class="form-control" value="${newUser.userTel}">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">邮箱</th>
                    <td width="80%">
                        <input type="text" id = "email" name="email"  style="width: 400px;" class="form-control" value="${newUser.email}">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">用户状态</th>
                    <td width="80%">
                        <select id = "userState" name="userState"  style="width: 400px;" class="form-control">
                            <option value="0" <c:if test="${newUser.userState == 0}">selected </c:if>>正常</option>
                            <option value="1" <c:if test="${newUser.userState == 1}">selected </c:if>>禁用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">性别</th>
                    <td id="sex" width="80%">
                        男<input type="radio"  name="sex" value="0" style="width: 50px;" checked <c:if test="${newUser.sex == 0}">checked </c:if>>
                        女<input type="radio"   name="sex" value="1" style="width: 50px;" <c:if test="${newUser.sex == 1}">checked </c:if>>
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">用户地址</th>
                    <td width="80%">
                        <input type="text" id = "userAddress" name="userAddress" style="width: 400px;" class="form-control" value="${newUser.userAddress}">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active" >所属机构</th>
                    <td width="80%">
                        &lt;%&ndash;<select name="orgId" id="orgId" style="width: 400px;" class="form-control">&ndash;%&gt;
                        <c:forEach items="${apiRsp.results}" var="org">
                            &lt;%&ndash;<option value="${org.id}">${org.orgName}</option>\&ndash;%&gt;
                            <c:if test="${org.id == userOrgId}">${org.orgName}
                                <input type="hidden" name="orgId" id="orgId" value="${org.id}">
                                <input type="hidden" name="orgName" id="orgName" value="${org.orgName}"></c:if>
                        </c:forEach>
                        &lt;%&ndash;</select>&ndash;%&gt;
                        <input type="button" onclick="backOrg();" value="选择机构">
                    </td>
                </tr>
                <tr>
                    <th width="20%" class="active">机构角色</th>
                    <td width="80%">
                        <div class="form-group">
                            <div class="col-sm-10 row">
                                <c:forEach items="${bsInfo}" var="bs">
                                    <div class="col-sm-3 myColSm3">
                                        <input type="checkbox" name = "roles" value="${bs.id}">${bs.roleName}
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </td>
                </tr>--%>
                   <tr>
                       <td>伤者</td>
                       <td>${apiRes.results.userName}</td>
                       <td>贷款金额</td>
                       <td>${apiRes.results.loanMoney}</td>
                   </tr>
               <tr>
                   <td>索赔员</td>
                   <td>${apiRes.results.claimerName}</td>
                   <td>索赔受理时间</td>
                   <td><fmt:formatDate value="${apiRes.results.claimTime}" pattern="yyyy-MM-dd HH:mm"/></td>
               </tr>
               <tr>
                   <td>车牌号码</td>
                   <td>${apiRes.results.cardNumber}</td>
                   <td>出险时间</td>
                   <td><fmt:formatDate value="${apiRes.results.outInsuranceTime}" pattern="yyyy-MM-dd HH:mm"/></td>
               </tr>
               <tr>
                   <td>保险公司</td>
                   <td>${apiRes.results.insuranceCompany}</td>
                   <td>事故责任比例</td>
                   <td>${apiRes.results.liabilityRatio}%</td>
               </tr>
               <tr>
                   <td>保险公司调解人</td>
                   <td>${apiRes.results.insOfficerName}</td>
                   <td>保险公司调解人电话</td>
                   <td>${apiRes.results.insOfficerTel}</td>
               </tr>
               <tr>
                   <td>调解成功日期</td>
                   <td><fmt:formatDate value="${apiRes.results.mediateDate}" pattern="yyyy-MM-dd HH:mm"/></td>
                   <td>赔款预计到账时间</td>
                   <td><fmt:formatDate value="${apiRes.results.arrivalTime}" pattern="yyyy-MM-dd HH:mm"/></td>
               </tr>
               <tr>
                       <td align="center" colspan="4" style="font-size: 14px;font-weight: bold;">调解预案金额及保险公司确认赔偿金额</td>
               </tr>
               <tr>
                   <td></td>
                   <td>调解预案初审金额</td>
                   <td>调解预案审核金额</td>
                   <td>保险公司调解确认金额</td>
               </tr>
                <c:forEach items="${apiRes.results.caseMediationClaimReports}" var="item">
                   <tr>
                        <td>${item.projectName}</td>
                        <td>${item.opinionMoney}</td>
                        <td>${item.auditingMoney}</td>
                        <td>${item.icAuditingMoney}</td>
                     </tr>
                </c:forEach>
               <tr>
                   <td colspan="4">保险公司最终赔偿金额（按责任）（指最终赔偿到伤者账户的赔偿金额）
                        <br>
                       合计：&nbsp;&nbsp;&nbsp;${apiRes.results.cptMoney}&nbsp;&nbsp;&nbsp;元；&nbsp;&nbsp;&nbsp;其中：交强险
                       &nbsp;&nbsp;&nbsp;${apiRes.results.cpsMoney}&nbsp;&nbsp;&nbsp; 元；&nbsp;&nbsp;&nbsp;商业险&nbsp;&nbsp;&nbsp; ${apiRes.results.cocMoney}&nbsp;&nbsp;&nbsp; 元
                       <br>
                       贷款金额：&nbsp;&nbsp;&nbsp;${apiRes.results.loanMoney}&nbsp;&nbsp;&nbsp;元；已经还款金额&nbsp;&nbsp;&nbsp;
                       ${apiRes.results.repaymentMoney}&nbsp;&nbsp;&nbsp; 元；&nbsp;&nbsp;&nbsp;已经收取服务费金额：&nbsp;&nbsp;&nbsp; ${apiRes.results.serviceMoney}&nbsp;&nbsp;&nbsp; 元
                   </td>
               </tr>
             <%--  <tr>
                   <td  colspan="4">合计：&nbsp;&nbsp;&nbsp;${apiRes.results.cptMoney}&nbsp;&nbsp;&nbsp;元；&nbsp;&nbsp;&nbsp;其中：交强险
                       &nbsp;&nbsp;&nbsp;${apiRes.results.cpsMoney}&nbsp;&nbsp;&nbsp; 元；&nbsp;&nbsp;&nbsp;商业险&nbsp;&nbsp;&nbsp; ${apiRes.results.cocMoney}&nbsp;&nbsp;&nbsp; 元
                   </td>
               </tr>
               <tr>
                   <td  colspan="4">贷款金额：&nbsp;&nbsp;&nbsp;${apiRes.results.loanMoney}&nbsp;&nbsp;&nbsp;元；已经还款金额&nbsp;&nbsp;&nbsp;
                   ${apiRes.results.repaymentMoney}&nbsp;&nbsp;&nbsp; 元；&nbsp;&nbsp;&nbsp;已经收取服务费金额：&nbsp;&nbsp;&nbsp; ${apiRes.results.serviceMoney}&nbsp;&nbsp;&nbsp; 元</td>
               </tr>--%>
               <tr>
                   <td colspan="4">调解过程备注：（沟通时间、沟通对象、沟通内容、沟通结果）<br>
                       ${apiRes.results.mediateDesc}
                   </td>
               </tr>
              <%-- <tr>
                   <td colspan="4">${apiRes.results.mediateDesc}</td>
               </tr>--%>
               <tr>
                   <td colspan="4">调解不成功原因归纳：<br>
                       ${apiRes.results.mediateFailDesc}
                   </td>
               </tr>
            <%--   <tr>
                   <td colspan="4">${apiRes.results.mediateFailDesc}</td>
               </tr>--%>
                </tbody>
            </table>
        </div>
    </form>

</div>


<div id="dialogId"></div>
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
    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $('#fileupload').fileupload({
        done: function (e, data) {
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            if (r.success == true){
                $("#infImg").attr("src","http://ddrapi.shlefan.com/sftp/files/"+pathImg);
                $("#adPic").val("http://ddrapi.shlefan.com/sftp/files/"+pathImg);
            }else {
                alert("上传失败，请重试111");
            }
        }
    });
    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
        event.preventDefault();
    });

    var backOrg = function(){
        var img = $("#adPic").val();
        var userName = $("#userName").val();
        var userType = $("#userType").val();
        var nickName = $("#nickName").val();
        var userTel = $("#userTel").val();
        var email = $("#email").val();
        var userState = $("#userState").val();
        var sex = $('#sex input[name="sex"]:checked ').val();
        var userAddress = $("#userAddress").val();
        openDialog({
            frame:true,
            title:"",
            height:600,
            width:550,
            url:"${ctx}/org/treeList?img="+img+"&userName="+userName+"&userType="+userType
                    +"&nickName="+nickName+"&userTel="+userTel+"&email="+email
                    +"&userState="+userState+"&sex="+sex+"&userAddress="+userAddress+"&type=1"
        });
    }
</script>
</body>
</html>