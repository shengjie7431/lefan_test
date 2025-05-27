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
    <style>
        .stepList {
            margin: 0 32px;
        }

        .stepItem {
            display: flex;
        }

        .stepDate {
            width: 250px;
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
</head>
<body>

<div class="main">
    <input type="hidden" name="id" value="${surveyKnowledgeBase.id}">
    <input type="hidden" name="surveyCode" value="${surveyCode}">
    <div class="title">
        <c:if test="${surveyKnowledgeBase.isTop == 0 || surveyKnowledgeBase.isTop == null}">
            <button class="butList defuelt" onclick="operate('${surveyKnowledgeBase.id}','${surveyCode}','1100',true);">置顶</button>
        </c:if>
        <c:if test="${surveyKnowledgeBase.isTop == 1}">
            <button class="butList defuelt" onclick="operate('${surveyKnowledgeBase.id}','${surveyCode}','1200',true);">取消置顶</button>
        </c:if>
        <button class="butList defuelt" onclick="operate('${surveyKnowledgeBase.id}','${surveyCode}','1000',false);">修改</button>
        <button class="butList defuelt" onclick="operate('${surveyKnowledgeBase.id}','${surveyCode}','9999',true);">删除</button>

    </div>

    <div class="main-boy">
        <div>
            <table class="spec-info" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>标题</td>
                    <td>${surveyKnowledgeBase.title}</td>
                    <td>发布人</td>
                    <td>${surveyKnowledgeBase.authUserName}</td>
                    <td>摘要</td>
                    <td>${surveyKnowledgeBase.remark}</td>
                </tr>
                <tr>
                    <td>类别名称</td>
                    <td>${surveyKnowledgeBase.knowledgeTypeName}</td>
                    <td>评论数</td>
                    <td>${surveyKnowledgeBase.commentNum}</td>
                    <td>点赞数</td>
                    <td>${surveyKnowledgeBase.pointzNum}</td>
                </tr>
                <tr>
                    <td>创建人</td>
                    <td>${surveyKnowledgeBase.createByName}</td>
                    <td>创建时间</td>
                    <td><fmt:formatDate value="${surveyKnowledgeBase.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>更新时间</td>
                    <td><fmt:formatDate value="${surveyKnowledgeBase.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                </tr>
                <c:if test="${surveyKnowledgeBase.fileId !=null}">
                    <tr>
                        <td>材料信息</td>
                        <td style="padding: 10px" colspan="5">
                            <img src="${ctx}/img/word.png" width="75;" height="75;" class="picToBig">
                            <a onclick="onlinePreview('${surveyKnowledgeBase.fileName}')" target="_blank">预览/下载</a>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${surveyKnowledgeBase.content !=null}">
                    <tr>
                        <td>内容</th>
                        <td colspan="5">
                            <textarea class="form-control" name="content" id="content" style="height: 400px;" > ${surveyKnowledgeBase.content}</textarea>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>

            <c:if test="${surveyKnowledgeComment != null}">
                <c:forEach items="${surveyKnowledgeComment}" var="item">
                    <div class="stepItem" style="margin-left: 10px;margin-top: 20px">
                        <div class="stepDate"><fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                        <div class="stepDot"><div class="stepDot-dot finished"></div></div>
                        <div class="stepDetail"><div class="stepDetail-title">评论人：${item.userName}
                            <br>评论内容：
                            <c:if test="${item.type ==1}">
                                ${item.content}
                            </c:if>
                            <c:if test="${item.type ==2}">
                                ${item.content}
                            </c:if>
                            <br>
                        </div><div class="stepDetail-detail"></div></div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${surveyKnowledgeComment.size() == 0}">
                <div class="stepItem" style="margin-top: 10px;text-align:center;color: #ff0000;">
                    暂无留言
                </div>
            </c:if>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
        </div>
    </div>
</div>

<div id="dialogId"></div>
<%@ include file="/WEB-INF/pages/common/mainFooter.jsp" %>
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
    var ctx="${ctx}";
</script>
<script type="text/javascript">
    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        $("#content").text(editor1.html());
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

</script>
<script type="text/javascript" src="${ctx}/caseMid/js/xiangce.js" ></script>
</body>
</html>