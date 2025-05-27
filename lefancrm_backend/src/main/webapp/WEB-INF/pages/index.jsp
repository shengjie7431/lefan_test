<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<link rel="shortcut icon" href="${ctx}/img/favicon.png" />
<!DOCTYPE html>
<html>
<head>
    <title>乐凡saas服务中心</title>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>

</head>
<%--<body class="easyui-layout"  href="javascript:;" title="首页">--%>
<body class="easyui-layout" >

<%--控制弹出之后 机构报表进入不弹遮罩层--%>
<input type="hidden" value="1" id="index_pay_flag">
<input type="hidden" value="" id="index_pay_id">
<div class="dalog-bg" ></div>
<%--调查费弹框提醒框--%>
<div class="dalog dalog__3">
    <div class="d-title">
        恭喜，你收到一笔调查费，请及时查收！
    </div>
    <div class="d-content">
        <div class="d-content__text">
            金额：<span class="span_value"></span>元
        </div>
        <div class="d-content__text">
            案件数量：<span class="span_value"></span>
<%--            <a class="d-a_active">（查看详情）</a>--%>
        </div>
        <div class="d-content__text">
            <span class="span_value"></span>
        </div>
        <div class="d-content__text text__line2">
            备注：<span class="span_value"></span>
        </div>
    </div>
    <div class="d-info">
        <div class="d-info__text">如有疑问请联系财务人员：<span class="span_value"></span></div>
        <div class="d-info__text">联系方式：<span class="span_value"></span></div>
    </div>
    <div class="d-btns">
        <div class="d-btn" data-id="1">暂未收到</div>
        <div class="d-btn d-btn__active" data-id="2">确认到账</div>
    </div>
</div>
<%--费用报销弹窗提醒框  待提交发票--%>
<div class="dalog dalog__3" hidden style="height: 250px;">
    <div class="d-title">
        你有一笔费用报销待提交发票，请尽快处理
    </div>
    <div class="d-content">
        <div class="d-content__text">
            清单名称：<span class="span_value"></span>
        </div>
        <div class="d-content__text">
            报销金额：<span class="span_value"></span>
        </div>
    </div>
    <div class="d-btns">
        <div class="d-btn" data-id="1">稍后至'报销清单'处理</div>
        <div class="d-btn d-btn__active" onclick="javascript:addTab('报销清单','${ctx}/fee/list?menuCode=list&searchCode=reimbursement-list&reState=1','update');">立即处理</div>
    </div>
</div>
<%--费用报销弹窗提醒框  待确认到账--%>
<div class="dalog dalog__3" hidden>
    <div class="d-title">
        恭喜，你收到一笔费用报销，请及时查收！
    </div>
    <div class="d-content">
        <div class="d-content__text">
            金额：<span class="span_value"></span>元
        </div>
        <div class="d-content__text">
            案件数量：<span class="span_value"></span>
        </div>
        <div class="d-content__text text__line2">
            备注：<span class="span_value"></span>
        </div>
    </div>
    <div class="d-info">
        <div class="d-info__text">如有疑问请在钉钉联系财务人员:<span class="span_value"></span></div>
    </div>
    <div class="d-btns">
        <div class="d-btn" data-id="1">暂未收到</div>
        <div class="d-btn d-btn__active" data-id="2">确认到账</div>
    </div>
</div>


<%--绩效弹框提醒框--%>
<div class="dalog dalog__3" id="performance" hidden style="height: 250px;">
    <div class="d-title">
        你有一份绩效待核对，超期将自动提交，请尽快核对确认
    </div>
    <div class="d-content">
        <div class="d-content__text">
            绩效月份：<span class="span_value" id="workTime"></span>
        </div>
        <div class="d-content__text">
            剩余时间：<span class="span_value" id="timeRemaining"></span>
        </div>
    </div>
    <div class="d-btns">
        <div class="d-btn" data-id="1">稍候至"绩效管理"处理</div>
        <div class="d-btn d-btn__active" onclick="javascript:addTab('绩效管理','${ctx}/staff/list?surveyCode=performance&type=info','update');">立即前往</div>
    </div>
</div>

<%--案件提醒弹窗--%>
<div class="dalog dalog__3" id="performance" hidden style="height: 250px;">
    <div class="d-title" style="color: #EE0707">
        每日案件提醒
    </div>
    <div class="d-title-desc" style="color: #EE0707">

    </div>
    <div class="d-content"  style="color: #EE0707">
        <%--<jsp:include page="survey/case/caseRemind.jsp"></jsp:include>--%>
    </div>
    <%--<div class="d-btns" style="justify-content: center">--%>
        <%--<div class="d-btn d-btn__active" onclick="javascript:addTab('案件提醒','${ctx}/survey/case/back?btnCode=fastReply','update');"  style="background-color: #EE0707">立即回复</div>--%>
    <%--</div>--%>
</div>

<%----onload="javascript:addTab('首页','${ctx}/index','update');"onload="javascript:addTab('首页','${ctx}/system/work','update');" href="javascript:;" title="首页"--%>
<div region="north" class="head">
    <span><img height="25px" width="25px" src="${ctx}/img/logo.png"/>乐凡saas服务中心</span>
    <div class="user">
        你好，<b>${(not empty (sessionScope.adminDto.userName))?(sessionScope.adminDto.userName):(sessionScope.adminDto.userName)}</b><span>|</span>
<%--        <a onclick="javascript:addTab('通知中心','${ctx}/baseSurvey/list?surveyCode=message&messageType=4','update');"  href="javascript:;" title="通知中心${sessionScope.messageMap.surveyMessagesResponse.count}"><span id="index_span_msg_size"><c:if test="${sessionScope.messageMap.surveyMessagesResponse.count > 0}"><img height="25px" width="25px" src="${ctx}/img/lingdang22.png"/></c:if><c:if test="${sessionScope.messageMap.surveyMessagesResponse.count == 0}"><img height="25px" width="25px" src="${ctx}/img/lingdang11.png"/></c:if></span></a>--%>
        <a onclick="javascript:addTab('通知中心','${ctx}/baseSurvey/list?surveyCode=message&messageType=4','update');"
           href="javascript:;" title="通知中心" style="position: relative;display: inline-block">
            <span id="index_span_msg_size">
                <img height="25px" width="25px" src="${ctx}/img/lingdang11.png"/>
                <span id="msg_span" style="position: absolute;margin: 0;top: -8px;left: 30px;color: red;font-size: 12px;font-weight: bold;">${sessionScope.messageMap.surveyMessagesResponse.count}</span>
            </span>
        </a>
        <span>|</span><a
            onclick="javascript:addTab('修改密码','${ctx}/system/admin/editPassword');" href="javascript:;" title="修改密码">修改密码</a><span>|</span><a href="${ctx}/logout" target="_top">退出</a>
    </div>
</div>

<div region="west" title="导航菜单" class="left">
    <div class="set-skin" id="set-skin">
        <a class="s1" href="javascript:;" rel="skin1" title="黑"></a>
        <a class="s2" href="javascript:;" rel="skin2" title="灰"></a>
        <a class="s3" href="javascript:;" rel="skin3" title="红"></a>
        <a class="s4" href="javascript:;" rel="skin4" title="紫"></a>
        <a class="s5" href="javascript:;" rel="skin5" title="蓝"></a>
        <a class="s6" href="javascript:;" rel="skin6" title="绿"></a>
    </div>
    <div id="aa" class="easyui-accordion">
        <jsp:include page="/WEB-INF/pages/leftMenu.jsp" />
    </div>
</div>

<div id="mainPanle" region="center" class="right">
    <div id="tabs" class="easyui-tabs man-tab" fit="true" border="false">
        <div title="首页" id="home" class="easyui-layout">

            <div class="quick-menu">
                <div class="tabBorder1"  >
                    <%--<jsp:include page="/WEB-INF/pages/work/workList.jsp" />--%>
                   <iframe marginheight="0" src="${ctx}/work/queryWork" marginwidth="0" scrolling="yes" frameborder="0" width="100%" height="100%" style="overflow-x:auto; overflow-y:auto;" id="iframepage1" name="iframepage1"></iframe>
                </div>
            </div>
			<div id="mainPanle_frame">
			 	请稍等，正在加载数据...
			</div>
            <iframe name="" class="iframe-main" scrolling="auto" frameborder="0" src="" style="width:100%"></iframe>
        </div>
    </div>
</div>
<div id="miss"></div>
<div class="main" onclick="back('${ctx}/work/workList');"></div>


<script>
    function back(url){
        window.location.reload();
    }
</script>
<style>
    html,body{height: 100%;}
    .main{
        width: 12%;height: 20%;background: #ffffff;position: fixed;
        bottom: -20%;right: 0;border: 1px solid #bee5ed;box-sizing: border-box;border-radius: 4px;cursor: pointer;
    }
    .top{width: 100%;height: 20%;background: #d9EDF6; border-bottom: 1px solid #bee5ed;}
    .kaitou{margin: 4px;}
    .conent p{margin: 4px; font-size: 10px;text-align: center;}



    /*************************************************************************弹出调查费确认提示*/
    .dalog-bg{
        display: none;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.4);
        position: absolute;
        top: 50px;
        left: 0;
        z-index: 999;
    }
    .dalog {
        display: none;
        margin: 0 auto;
        /* margin-top: 20%; */
        width: 508px;
        height: 418px;
        line-height: 20px;
        text-align: center;
        border: 1px solid rgba(187, 187, 187, 1);
        font-size: 14px;
        z-index: 1001;
        background-color: #fff;
    }

    .dalog .d-title {
        width: 82%;
        margin: 0 auto;
        height: 24px;
        line-height: 24px;
        padding: 30px 0;
        color: rgba(16, 16, 16, 1);
        font-size: 16px;
        text-align: center;
    }
    .d-title-desc{
        width: 82%;
        margin: 0 auto;
        height: 20px;
        line-height: 20px;
        padding: 5px 0;
        color: rgba(16, 16, 16, 1);
        font-size: 13px;
        text-align: center;
    }

    .dalog .d-cell {
        width: 90%;
        margin: 0 auto;
        padding: 10px;
        display: flex;
    }

    .dalog .d-cell .d-cell__label {
        display: inline-block;
        width: 150px;
        height: 30px;
        line-height: 30px;
        font-size: 14px;
        text-align: right;
    }

    .dalog .d-cell .d-cell__input {
        position: relative;
        display: inline-block;
        padding-left: 10px;
        width: 230px;
        height: 30px;
        line-height: 30px;
        font-size: 14px;
        display: flex;
    }
    .dalog .d-cell .d-cell__input--disabled input{
        background-color: rgba(179,179,179,0.25);
    }

    .dalog .d-cell .d-cell__input input {
        display: inline-block;
        width: 190px;
        padding: 0 10px;
        padding-right: 40px;
        height: 28px;
        line-height: 28px;
        border: 1px solid #bbb;
        font-size: 14px;
        text-align: right;
    }

    .dalog .d-cell .d-cell__input span {
        position: absolute;
        right: 10px;
        display: inline-block;
        width: 16px;
        height: 30px;
        line-height: 30px;
        font-size: 14px;
        text-align: center;
    }

    .dalog .d-cell .d-cell__textarea {
        display: inline-block;
        padding-left: 10px;
        width: 230px;
        height: 104px;
        font-size: 14px;
    }

    .dalog .d-cell .d-cell__textarea textarea {
        padding: 10px;
        width: 208px;
        height: 82px;
        line-height: 24px;
        border: 1px solid #bbb;
        font-size: 14px;
    }

    .dalog .d-btns {
        width: 320px;
        margin: 0 auto;
        padding-top: 30px;
        display: flex;
        justify-content: space-between;
    }

    .dalog .d-btns .d-btn {
        width: 130px;
        height: 34px;
        line-height: 34px;
        border: 1px solid #bbb;
        background-color: #fff;
        cursor: pointer;
    }

    .dalog .d-btns .d-btn:hover {
        box-shadow: 0px 0px 10px #bbb;
    }

    .dalog .d-btns .d-btn__active {
        background-color: #3ba9ff;
        color: #fff;
    }



    .dalog__3 {
        position: absolute;
        top: 20%;
        left: 20%;
        height: 400px;
        margin: 0;
    }
    .dalog__3 .d-title {
        font-size: 20px;
        color: #3ba9ff;
    }

    .dalog__3 .d-content {
        width: 90%;
        margin: 0 auto;
    }

    .dalog__3 .d-content .d-content__text,
    .dalog__3 .d-info .d-info__text {
        width: 100%;
        line-height: 20px;
        padding: 3px 0;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .dalog .text__line2 {
        height: 40px;
        white-space: normal !important;
        overflow: hidden !important;
        display: -webkit-box !important;
        -webkit-box-orient: vertical !important;
        -webkit-line-clamp: 2 !important;
    }

    .dalog__3 .d-info {
        width: 90%;
        margin: 0 auto;
        padding-top: 20px;
    }
    .dalog__3 .d-btns{
        width: 400px;
        padding-top: 20px;
    }
    .dalog__3 .d-btns .d-btn{
        width: 174px;
        height: 50px;
        line-height: 50px;
    }
    .dalog .d-a_active{
        color: #3ba9ff;
        cursor: pointer;
    }

</style>
<!--mainPanle-->

<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<script src="${ctx}/js/jquery.messager.js?v=1" type="text/javascript"></script>
<%--<script src="${ctx}/js/jquery-1.2.6.pack.js" type="text/javascript"></script>--%>

<script>
    // window.setInterval(refreshMsg,1000 * 60 * 5);//五分钟
    // window.setInterval(refreshMsg,10000);//5秒
    function refreshMsg(){
        var url = "${ctx}/baseSurvey/operate";
        ajaxSubmit(url,{"surveyCode" : "getMessageSize"},function(v,e,p){
            var size = e.data.results;
            console.log(size);
            if(size != $("#msg_span").html() && size > $("#msg_span").html()){
                $.messager.show('<span color=red>消息提醒</span>', '<span color=green style="font-size:14px;font-weight:bold;">您有一条新任务</span>',20000);
            }
            $("#msg_span").html(size);
        });
    }

    //调查员绩效提醒
    refreshStaffPerformance();

    function refreshStaffPerformance(){

        ajaxSubmit("${ctx}/staff/operate",{"operateCode" : "performanceForSurvey"},function(v,e,p){
            if(e.data.results != null){
                $("#performance").show();
                $("#workTime").html(e.data.results.workTime);
                $("#timeRemaining").html(e.data.results.timeRemaining);
            }else{
                $("#performance").hide();
            }
        })
    }
</script>


<script type="text/javascript">
    function createCookie(name, value, days) {
        if (days) {
            var date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
            var expires = "; expires=" + date.toGMTString();
        } else var expires = "";
        document.cookie = name + "=" + value + expires + "; path=/";
    }
    function readCookie(name) {
        var nameEQ = name + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ')c = c.substring(1, c.length);
            if (c.indexOf(nameEQ) == 0)return c.substring(nameEQ.length, c.length);
        }
        return null;
    }
    function eraseCookie(name) {
        createCookie(name, "", -1);
    }
    function switchStylestyle(styleName) {
        $('link[rel*=style][title]').each(function (i) {
            this.disabled = true;
            if (this.getAttribute('title') == styleName) this.disabled = false;
        });
        createCookie('style', styleName, 365);
    }
    function addTab(title, href, update) {
        var num = localStorage.getItem("pageSize") || 20;

        if(href.indexOf("?") > -1){
            href += "&pageSize=" + num;
        }else{
            href += "?pageSize=" + num;
        }
        var options = {
            title: title,
            content: createFrame(href),
            closable: true,
            cache: false,
            closed: true,
            width: $('#mainPanle').width() - 10,
            height: $('#mainPanle').height() - 26
        };
        var existed = $('#tabs').tabs('exists', title);
        if (existed) {
            var thisTab = $("#tabs").tabs('getTab', title);
            if (update) {
                $('#tabs').tabs('update', {
                    tab: thisTab,
                    options: options
                });
            }
            $('#tabs').tabs('select', title);
        } else {
            $('#tabs').tabs('add', options);
        }
    }
    function closeSelf() {
        var thisTab = $("#tabs").tabs("getSelected");
        var thisTabIndex = $("#tabs").tabs("getTabIndex", thisTab);
        $("#tabs").tabs("close", thisTabIndex);
    }
    function closeThenAddTab(title, href, update) {
        closeSelf();
        addTab(title, href, update);
    }
    function createFrame(url) {
        return '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
    }
    $(function () {
        $("#mainPanle_frame").html('<iframe class="iframe-main" scrolling="auto" frameborder="0"  src="" style="width:100%;height:100%;"></iframe>');
        $(".iframe-main").load(function () {
            $(this).height($(window).height() - 100);
        });
        $('.set-skin a').click(function () {
            switchStylestyle(this.getAttribute("rel"));
            return false;
        });
        var c = readCookie('style');
        if (c) {
            switchStylestyle(c);
        }
    });
</script>
</body>

<script>
    $(function (){
        var b_width = $('.dalog-bg').width(), b_height = $('.dalog-bg').height()
        var d_width = $('.dalog').width(), d_height = $('.dalog').height()
        var cssList = {}
        if (b_width - d_width > 0){
            cssList.left=( b_width - d_width ) / 2
        }
        if (b_height - d_height > 0){
            cssList.top = (b_height - d_height) / 2
        }
        $('.dalog__3').css(cssList)


        $('.dalog__3 .d-btn').click(function(){
            if ($(this).attr('data-id') == 1){

            }
            if ($(this).attr('data-id') == 2){
                var payId = $("#index_pay_id").val();
                var url = "${ctx}/survey/pay/operate",param = {
                    "btnCode" : "ok-acc-pay",
                    "id" : payId
                };
                ajaxSubmit(url,param,function(v,e,p){
                    if (e.data.code == "0000"){
                        alert("操作成功！")
                    }else{
                        alert("操作失败！")
                    }
                    setTimeout(function () {
                        location.reload();
                    },2000);

                })
            }
            $('.dalog__3').hide()
            $('.dalog-bg').hide()
        })
    })
</script>
</html>