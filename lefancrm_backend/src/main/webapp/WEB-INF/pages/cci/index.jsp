<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>客户详情</title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>

</head>
<body class="easyui-layout">



<div id="mainPanle" region="center" class="right">
    <div>
        <h3>客户详情</h3>
        <div id="left" style="float:left">
            <h6>
                <c:if test="${caseProgress!=null}">
                    <c:if test="${caseProgress == 1}">初访</c:if>
                    <c:if test="${caseProgress == 2}">洽谈中</c:if>
                    <c:if test="${caseProgress == 3}">待签约</c:if>
                    <c:if test="${caseProgress == 4}">已签约</c:if>
                    <c:if test="${caseProgress == 5}">暂时搁置</c:if>
                    <c:if test="${caseProgress == 6}">已放弃</c:if>
                </c:if>
                <c:if test="${caseProgress==null}">无数据</c:if>
            </h6>
        </div>

        <div style="float:left;padding-left:50px">
            ${name}<br>
            地址：${familyAddress}<br>
            负责人：${ccName}
        </div>
    </div>
    <div id="tabs" class="easyui-tabs man-tab" fit="true" border="false">
        <div title="客户全景" id="home" class="easyui-layout">

            <div class="quick-menu">
                <div class="tabBorder1"  >
                    <%--<jsp:include page="/WEB-INF/pages/work/workList.jsp" />--%>
                    <iframe marginheight="0" src="${ctx}/cci/customerPanorama?id=${id}" marginwidth="0" scrolling="yes" frameborder="0" width="100%" height="100%" style="overflow-x:auto; overflow-y:auto;" id="iframepage1" name="iframepage1"></iframe>
                </div>
            </div>
            <div id="mainPanle_frame">
                请稍等，正在加载数据...
            </div>
            <iframe name="" class="iframe-main" scrolling="auto" frameborder="0" src="" style="width:100%"></iframe>
        </div>
        <div title="销售动态" id="home1" class="easyui-layout">

            <div class="quick-menu">
                <div class="tabBorder1"  >
                    <%--<jsp:include page="/WEB-INF/pages/work/workList.jsp" />--%>
                    <iframe marginheight="0" src="${ctx}/cci/salesDynamics?customerId=${id}" marginwidth="0" scrolling="yes" frameborder="0" width="100%" height="100%" style="overflow-x:auto; overflow-y:auto;" id="iframepage1" name="iframepage1"></iframe>
                </div>
            </div>
            <div id="mainPanle_frame1">
                请稍等，正在加载数据...
            </div>
            <iframe name="" class="iframe-main" scrolling="auto" frameborder="0" src="" style="width:100%"></iframe>
        </div>
        <div title="案件信息" id="home2" class="easyui-layout">

            <div class="quick-menu">
                <div class="tabBorder1"  >
                    <%--<jsp:include page="/WEB-INF/pages/work/workList.jsp" />--%>
                    <iframe marginheight="0" src="${ctx}/cci/caseInformation?customerId=${id}&name=${name}" marginwidth="0" scrolling="yes" frameborder="0" width="100%" height="100%" style="overflow-x:auto; overflow-y:auto;" id="iframepage1" name="iframepage1"></iframe>
                </div>
            </div>
            <div id="mainPanle_frame2">
                请稍等，正在加载数据...
            </div>
            <iframe name="" class="iframe-main" scrolling="auto" frameborder="0" src="" style="width:100%"></iframe>
        </div>
        <div title="伤者信息" id="home3" class="easyui-layout">

            <div class="quick-menu">
                <div class="tabBorder1"  >
                    <%--<jsp:include page="/WEB-INF/pages/work/workList.jsp" />--%>
                    <iframe marginheight="0" src="${ctx}/cci/injuredInformation?id=${id}" marginwidth="0" scrolling="yes" frameborder="0" width="100%" height="100%" style="overflow-x:auto; overflow-y:auto;" id="iframepage1" name="iframepage1"></iframe>
                </div>
            </div>
            <div id="mainPanle_frame3">
                请稍等，正在加载数据...
            </div>
            <iframe name="" class="iframe-main" scrolling="auto" frameborder="0" src="" style="width:100%"></iframe>
        </div>
        <div title="伤情信息" id="home4" class="easyui-layout">

            <div class="quick-menu">
                <div class="tabBorder1"  >
                    <%--<jsp:include page="/WEB-INF/pages/work/workList.jsp" />--%>
                    <iframe marginheight="0" src="${ctx}/cci/injuryInformation?id=${id}" marginwidth="0" scrolling="yes" frameborder="0" width="100%" height="100%" style="overflow-x:auto; overflow-y:auto;" id="iframepage1" name="iframepage1"></iframe>
                </div>
            </div>
            <div id="mainPanle_frame4">
                请稍等，正在加载数据...
            </div>
            <iframe name="" class="iframe-main" scrolling="auto" frameborder="0" src="" style="width:100%"></iframe>
        </div>
        <div title="事故信息" id="home5" class="easyui-layout">

            <div class="quick-menu">
                <div class="tabBorder1"  >
                    <%--<jsp:include page="/WEB-INF/pages/work/workList.jsp" />--%>
                    <iframe marginheight="0" src="${ctx}/cci/accidentInformation?id=${id}" marginwidth="0" scrolling="yes" frameborder="0" width="100%" height="100%" style="overflow-x:auto; overflow-y:auto;" id="iframepage1" name="iframepage1"></iframe>
                </div>
            </div>
            <div id="mainPanle_frame5">
                请稍等，正在加载数据...
            </div>
            <iframe name="" class="iframe-main" scrolling="auto" frameborder="0" src="" style="width:100%"></iframe>
        </div>

    </div>
</div>


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
</style>

</body>

<script>
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

    function createFrame(url) {
        return '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
    }
</script>

</html>
