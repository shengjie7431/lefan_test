<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/pages/common/taglibs.jsp" %>
<link rel="shortcut icon" href="${ctx}/img/favicon.png" />
<!DOCTYPE html>
<html>
<head>
   <title>登录-后台控制中心</title>
   <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
</head>
<body>
<img src="${ctx}/img/login_bg.jpg" style="position: fixed; top: 0;left: 0;width: 100%;z-index: -33" />
<div class="container">
  <form id="loginForm" class="form-signin form-horizontal" role="form" action="${ctx}/doLogin" method="post">
    <%--<h2 class="form-signin-heading">管理员登录</h2> --%>
    <label><span class="label label-danger">${msg}</span></label>
    <div class="form-group">
      <label for="userTelphone" class="col-sm-3 control-label" style="line-height: 1.6;color: #10243c">用户名</label>
      <div class="col-sm-9">
          <input name="userTelphone" value="${userTelphone}" type="text" class="form-control" placeholder="登录账号" required autofocus>
      </div>
    </div>

    <div class="form-group">
      <label for="password" class="col-sm-3 control-label" style="line-height: 4;color: #10243c">密&nbsp;&nbsp;&nbsp;码</label>
      <div class="col-sm-9">
        <input name="password" type="password" class="form-control" placeholder="登录密码" required style="margin-top:15px;">
      </div>
    </div>
    <!-- <div class="checkbox">
      <label>
        <input type="checkbox" value="remember-me"> 下次自动登录
      </label>
    </div> -->
    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>    
  </form>
  
</div>

<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
</body>
</html>