<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<style>
    body,html{height: 100%;}
    .gengduo{float:right;margin-right: 20px}
    .box{
        display: flex;
        flex-wrap: wrap;
        height: 100%;
    }
    .box1{
        width: 50%;height: 50%;padding: 13px;
    }
    .top{
        height: 30px;
        background: #d9edf7;
        border-radius: 5px;text-align: center;line-height: 30px;
    }
</style>
<body>
      <div class="box">
        <c:if test="${loans != null}">
          <div class="box1">
            <div class="top">贷款申请案件<a class="gengduo" href="${ctx}/loan/loanApplicationList" >更多</a></div>
            <div class="table-responsive">
              <table class="table table-hover">
                <thead>
                   <tr>
                     <th  width="100">案件编号</th>
                     <th  width="80">用户姓名</th>
                     <th  width="150">用户手机</th>
                     <th  width="50">贷款金额</th>
                     <th  width="150">贷款用途</th>
                     <th  width="150">详细地址</th>
                     <th  width="150">申请时间</th>
                     <th  width="70">状态</th>
                     <th  width="150">操作</th>
                   </tr>
                </thead>
                <tbody class="class-list">
                   <c:forEach items="${loans}" var="item">
                     <tr>
                         <td>${item.loanNo}</td>
                         <td>${item.userName}</td>
                         <td>${item.userPhone}</td>
                         <td>${item.loanMoney}</td>
                         <td>
                             <c:if test="${item.loanPurpose == 1}">医疗费垫付</c:if>
                             <c:if test="${item.loanPurpose == 2}">赔偿款垫付</c:if>
                         </td>
                         <td>${item.accidentProvinceName}${item.accidentCityName}${item.accidentDistrictName}${item.accidentAddress}</td>
                         <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                         <td>
                             <c:if test="${item.state == 1}">待审核</c:if>
                             <c:if test="${item.state == 2}">审核通过</c:if>
                             <c:if test="${item.state == 3}">驳回</c:if>
                             <c:if test="${item.state == 4}">已受理</c:if>
                             <c:if test="${item.state == 5}">贷款评估</c:if>
                             <c:if test="${item.state == 6}">贷款面签</c:if>
                             <c:if test="${item.state == 7}">贷款审批</c:if>
                             <c:if test="${item.state == 8}">保证保险投保</c:if>
                             <c:if test="${item.state == 9}">贷款发放</c:if>
                             <c:if test="${item.state == 22}">结案</c:if>
                         </td>
                         <td>  <a href="javascript:loanUpdateLoan('${item.id}',2,'${item.userId}','${item.userName}','${item.accidentCityName}');">审核通过</a>
                             <a href="javascript:updateResonLoan('${item.id}');">驳回</a></td>
                     </tr>
                   </c:forEach>
                </table>
            </div>
          </div>
        </c:if>
      <c:if test="${agents != null}">
          <div class="box1">
            <div class="top">代理申请案件<a class="gengduo" href="${ctx}/agent/list" >更多</a></div>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th  width="100">案件编号</th>
                        <th width="100">用户姓名</th>
                        <th width="100">用户手机号</th>
                        <th width="150">事故发生地</th>
                        <th width="150">代理类型</th>
                        <th width="150">创建时间</th>
                        <th width="70">代理申请状态</th>
                        <th  width="150">操作</th>
                    </tr>
                    </thead>
                    <tbody class="class-list">
                    <c:forEach items="${agents}" var="item">
                        <tr>
                            <td>${item.agentNo}
                            </td>
                            <td>${item.userName}
                            </td>
                            <td>${item.userPhone}</td>
                            <td>
                                    ${item.accidentProvince}${item.accidentCity}${item.accidentDistrict}${item.accidentAddress}
                            </td>
                            <td><c:if test="${item.agentType == 1}">交通事故索赔</c:if>
                                <c:if test="${item.agentType == 2}">工伤事故索赔</c:if>
                                <c:if test="${item.agentType == 3}">寿险索赔</c:if>
                                <c:if test="${item.agentType == 4}">车辆损失索赔</c:if>
                                <c:if test="${item.agentType == 5}">保险拒赔</c:if>
                                <c:if test="${item.agentType == 6}">意外保险</c:if>
                                <c:if test="${item.agentType == 7}">其他侵权</c:if>
                                <c:if test="${item.agentType == 8}">援助服务</c:if>
                            </td>
                            <td>
                                <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td><c:if test="${item.state == 1}">待审核</c:if>
                                <c:if test="${item.state == 2}">审核通过</c:if>
                                <c:if test="${item.state == 3}">驳回</c:if>
                                <c:if test="${item.state == 4}">已受理</c:if>
                                <c:if test="${item.state == 10}">材料收集中</c:if>
                                <c:if test="${item.state == 11}">诉前调解</c:if>
                                <c:if test="${item.state == 12}">申请鉴定</c:if>
                                <c:if test="${item.state == 13}">鉴定中</c:if>
                                <c:if test="${item.state == 14}">待立案</c:if>
                                <c:if test="${item.state == 15}">已立案</c:if>
                                <c:if test="${item.state == 16}">开庭</c:if>
                                <c:if test="${item.state == 17}">已调解/判决</c:if>
                                <c:if test="${item.state == 18}">已上诉</c:if>
                                <c:if test="${item.state == 19}">补充证据</c:if>
                                <c:if test="${item.state == 20}">赔偿款已支付</c:if>
                                <c:if test="${item.state == 21}">贷款已还款</c:if>
                                <c:if test="${item.state == 22}">结案</c:if>
                            </td>
                            <td><a  href="javascript:void(0)" onclick="editStateAgent(${item.id},2,${item.userId})">审核通过</a>
                                <a  href="javascript:void(0)" onclick="updateResonAgent(${item.id})">驳回</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
          </div>
        </c:if>
        <c:if test="${invalis != null}">
          <div class="box1">
            <div class="top">伤残预估案件<a class="gengduo" href="${ctx}/invalidism/list" >更多</a></div>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th  width="150">用户姓名</th>
                        <th  width="150">用户手机号</th>
                        <th  width="150">事故发生地</th>
                        <th  width="150">事故性质</th>
                        <th  width="150">创建时间</th>
                        <th  width="70">预估状态</th>
                        <th  width="150">操作</th>
                    </tr>
                    </thead>
                    <tbody class="class-list">
                    <c:forEach items="${invalis}" var="item">
                        <tr>
                            <td>${item.userName}
                            </td>
                            <td>${item.userPhone}</td>
                            <td>
                                    ${item.accidentProvince}${item.accidentCity}${item.accidentDistrict}${item.accidentAddress}
                            </td>
                            <td><c:if test="${item.accidentType == 1}">交通事故</c:if>
                                <c:if test="${item.accidentType == 2}">工伤事故</c:if>
                                <c:if test="${item.accidentType == 3}">意外事故</c:if>
                            </td>
                            <td>
                                <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td><c:if test="${item.state == 1}">待预估</c:if>
                                <c:if test="${item.state == 2}">已预估</c:if>
                            </td>
                            <td> <a  href="javascript:void(0)" onclick="fileInval(${item.id})">查看伤残资料</a>
                                <c:if test="${item.state == 1}">
                                    <a  href="javascript:void(0)" onclick="updateResonInval(${item.id})">预估回复</a>
                                </c:if></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
          </div>
        </c:if>
        <c:if test="${promoteds != null}">
          <div class="box1">
            <div class="top">推广认证申请<a class="gengduo" href="${ctx}/promoted/userPromotedInfoList" >更多</a></div>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th width="100">真实姓名</th>
                        <th width="150">手机号</th>
                        <th width="150">职业</th>
                        <th width="150">所属地</th>
                        <th width="200">创建时间</th>
                        <th width="70">状态</th>
                        <th  width="150">操作</th>
                    </tr>
                    </thead>
                    <tbody class="class-list">
                    <c:forEach items="${promoteds}" var="item">
                        <tr>
                            <td>${item.realName}</td>
                            <td>${item.phone}</td>

                            <td>
                                <c:if test="${item.occupation == 1}">护士</c:if>
                                <c:if test="${item.occupation == 2}">医生</c:if>
                                <c:if test="${item.occupation == 3}">护工</c:if>
                                <c:if test="${item.occupation == 4}">调解组织</c:if>
                                <c:if test="${item.occupation == 5}">业务员</c:if>
                                <c:if test="${item.occupation == 6}">其他</c:if>
                                <c:if test="${item.occupation == 7}">保代公司</c:if>
                            </td>
                            <td>${item.province}${item.city}${item.district}</td>
                            <td>
                                <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <c:if test="${item.state == 1}">待审核</c:if>
                                <c:if test="${item.state == 2}">审核通过</c:if>
                                <c:if test="${item.state == 3}">驳回</c:if>
                            </td>
                            <td> <a href="javascript:promotedUpdatea('${item.id}',2);">审核通过</a>
                                <a href="javascript:bohuiPromoted('${item.id}');">驳回</a>
                                <c:if test="${item.promotedType == 2}"><a href="javascript:filePromoted('${item.id}');">查看凭证</a></c:if></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
          </div>
        </c:if>
        <c:if test="${withdrs != null}">
            <div class="box1">
                <div class="top">提现申请<a class="gengduo" href="${ctx}/account/withdrawalsInfoList" >更多</a></div>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th width="150">提现单号</th>
                            <th width="150">用户名</th>
                            <th width="150">提现金额</th>
                            <th width="150">电话号码</th>
                            <th width="150">交易凭证</th>
                            <th width="150">时间</th>
                            <th width="70">提现状态</th>
                            <th  width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody class="class-list">
                        <c:forEach items="${withdrs}" var="item">
                            <tr>
                                <td>${item.widraCode}</td>
                                <td>${item.userName}</td>
                                <td>${item.money}</td>
                                <td>${item.userTel}</td>
                                <td><a href="${item.unlineImg}" target="_blank">
                                    <img id="infImg"  target="_blank" src="${item.unlineImg}" width="80" height="80">
                                </a></td>
                                <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td>
                                    <c:if test="${item.state == 1}">待提现</c:if>
                                    <c:if test="${item.state == 2}">提现中</c:if>
                                    <c:if test="${item.state == 3}">提现成功</c:if>
                                </td>
                                <td> <c:if test="${item.state == 1}"><a href="javascript:withdrawalsOnline('${item.id}');">在线提现</a>|</c:if>
                                    <c:if test="${item.state == 1}"><a href="javascript:withdrawalsUnline('${item.id}');">线下提现</a></c:if></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>
      </div>

<%--      <div id="miss"><embed height="0" width="0" src="http://openapi.shlefan.com/pic/mp3/8855.mp3"></div>--%>
</body>
<script>
    function loanUpdateLoan(id,state,userId,userName,accidentCity){
        ajaxSubmit("${ctx}/loan/loanApplicationToUpdate",{"id":id,"state":state,"userId":userId,"userName":userName,"accidentCity":accidentCity},reload,"提交成功","确认提交该条信息吗？");
    }
    var updateResonLoan = function(id){
        openDialog({
            frame:true,
            title:"驳回贷款申请",
            height:400,
            width:600,
            url:"${ctx}/loan/toReson?id="+id
        });
    }
    function editStateAgent(id,state,userId){
        ajaxSubmit("${ctx}/agent/editState",{"id":id,"state":state,"userId":userId},reload,"审核成功！","确认通过审核？","审核失败！");
    }
    var updateResonAgent = function(id){
        openDialog({
            frame:true,
            title:"驳回代理信息",
            height:400,
            width:600,
            url:"${ctx}/agent/toReson?id="+id
        });
    }
    var fileInval = function(id){
        openDialog({
            frame:true,
            title:"伤残等级预估图片资料",
            height:400,
            width:600,
            url:"${ctx}/invalidism/queryFile?id="+id
        });
    }
    var updateResonInval = function(id){
        openDialog({
            frame:true,
            title:"伤残等级预估回复",
            height:400,
            width:600,
            url:"${ctx}/invalidism/toReport?id="+id
        });
    }
    function promotedUpdatea(id,state){
        ajaxSubmit("${ctx}/promoted/userPromotedInfoAddOrUpdate",{"id":id,"state":state},reload,"提交成功","确认提交吗？");
    }
    var filePromoted = function(id){
        openDialog({
            frame:true,
            title:"保代公司审核凭证",
            height:400,
            width:600,
            url:"${ctx}/promoted/queryFile?id="+id
        });
    }
    var bohuiPromoted = function(id){
        openDialog({
            frame:true,
            title:"驳回",
            height:200,
            width:800,
            url:"${ctx}/promoted/promotedInfoToReject?id="+id
        });
    }
    function withdrawalsUnline(id){
        ajaxSubmit("${ctx}/account/withdrawalsUnline",{"withdrawalsId":id},reload,"提现成功","确认提现吗？");
    }
    function withdrawalsOnline(id){
        ajaxSubmit("${ctx}/account/withdrawalsOnline",{"withdrawalsId":id},reload,"提现成功","确认提现吗？");
    }
</script>
<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
</html>
