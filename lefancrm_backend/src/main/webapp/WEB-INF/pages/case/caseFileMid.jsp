<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <style>
        .list-group-item{
            width: 100%;
        }
        .uuu{
            position: absolute;
            bottom: 20px;
            color: #fff;
            background-color: rgba(153, 153, 153, 0.6);
            line-height: 20px;
            width: 100px;
            text-align: center;
        }
        .col-sm-but{
            float: left;
        }
        .picButton{
            position: absolute;
            top: 0;
            left: 91px;
            height: 17px;
            width: 24px;
        }
    </style>
    <script>
        function onBack(caseNo,caseId,catalogId,enumName){
            ajaxSubmit("${ctx}/case/selectFileMid",{"caseNo":caseNo,"catalogId":catalogId,"viewType":"treeClick"},function(v,e,p){
                if(e.data.code=='0000'){
                    var vl = "";
                    for(var i = 0; i < e.data.results.length; i++){
                        var json = e.data.results[i];
                        vl += "<div class='col-sm-3' style='width: 15%;'>";
//                         vl += "<img id='infImg' src='"+json.filePath+"' width='100' height='100' onclick='openImgs("+i+")'><br/>";
                        vl += "" + (json.type == 1 ? "<a href='"+json.filePath+"' target='_blank'><img src='../img/pdf.jpg' width='100' height='100' onclick='openImgs("+i+")'></a><br/>" : "<img id='infImg' src='"+json.filePath+"' width='100' height='100' onclick='openImgs("+i+")'><br/>") + "";
//                        vl += "" + (json.createTimeName == null ? "" : json.createTimeName) + "<br/>";
                        vl += "<div class='uuu'>";
                        vl += "" + (json.catalogName == null ? "" : json.catalogName) + "<br/>";
                        vl += " </div>";
                        // "+(json.isDownLoad ==true?"":"disabled='false'")+"
                        vl += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox' name='files' value='"+json.filePath+"' onclick='poolIds("+json.id+")' data-id='"+json.id+"' class='picButton' style='display: none' >";
                        vl += " </div>";
                    }

                    $("#caseNo").val(caseNo);
                    $("#catalogId").val(catalogId);
                    $("#caseId").val(caseId);
                    $("#catalogName").val(enumName);
                    //不是具体分类时，上传div隐藏
                    if(catalogId !=1){
                        $("#uploadDiv").show();
                    }
                    if(catalogId ==1){
                        $("#uploadDiv").hide();
                    }
                    $("#f").html(vl);
                    if(! e.data.results.length) $('#btnSubmit').addClass('hidden')
                    else $('#btnSubmit').removeClass('hidden')
                }else{
                }
            })
        }

        function formatData(time,str){
            Date.prototype.Format = function(fmt) { //author: meizz
                var o = {
                    "M+": this.getMonth() + 1, //月份
                    "d+": this.getDate(), //日
                    "HH+": this.getHours(), //小时
                    "m+": this.getMinutes(), //分
                    "s+": this.getSeconds(), //秒
                    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                    "S": this.getMilliseconds() //毫秒
                };
                if(/(y+)/.test(fmt))
                    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
                            .substr(4 - RegExp.$1.length));
                for(var k in o)
                    if(new RegExp("(" + k + ")").test(fmt))
                        fmt = fmt.replace(RegExp.$1,
                                (RegExp.$1.length == 1) ? (o[k]) :
                                        (("00" + o[k])
                                                .substr(("" + o[k]).length)));
                return fmt;
            }
            return(new Date(time)).Format(str)
        }
    </script>
</head>
<body>
<div class="main">
    <div class="row">

        <div class="panel panel-info col-sm-2">
            <div class="content_wrap">
                <div class="">
                    <div class="zTreeDemoBackground left">
                        <ul class="list-group">
                            <c:forEach items="${enums}" var="e">
                                <%--<input type="button" class="list-group-item" onclick="" value="${e.enumName}"><br/>--%>
                                <input type="button" class="list-group-item" onclick="onBack('${caseNo}','${caseId}','${e.enumCode}','${e.enumName}')" value="${e.enumName}(${e.num})">
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div><!--panel-info-->
        <form method="post" action = "${ctx}/download" id="editFrom">
            <div class="col-sm-10 " id="result">
                <div class="row panel panel-info" id="uploadDiv" hidden="hidden">
                    <div>
                        <input type="hidden" id="caseNo" value="${caseNo}">
                        <input type="hidden" id="catalogId" value="${catalogId}">
                        <input type="hidden" id="caseId" value="${caseId}">
                        <input type="hidden" id="catalogName"  value="${catalogName}">
                        <input type="hidden" id="caseFile"  value="${caseFile}">
                    </div>
                    <%--<div>(单个文件上传)--%>
                    <%--<input required id="adPic" type="hidden" value="${img}" name="img">--%>
                    <%--<img hidden="hidden" id="infImg2" src="${img}" width="80" height="80">--%>
                    <%--</div>--%>
                    <%--<div>--%>
                    <%--<input id="fileupload" type="file"  name="file" multiple  data-url="${ctx}/uploadImage"><br>--%>
                    <%--</div>--%>
                    <div>
                        <input type="hidden" id="img" name="img" value="" />
                        <div id="materialImgsDiv" style="display: none"></div>
                        <div id="deleteDiv" style="display: none">
                            <input type="button" value="删除" onclick="del()"  style="width: 64px;  height: 22px;"/>
                        </div>
                    </div>
                    <div>
                        <input id="materialFileupload" type="file" name="file" multiple data-url="${ctx}/uploadFile/"><br>
                    </div>

                    <div class="col-sm-1 col-sm-offset-9" id="uploadButton"><button type="button" onclick="uploadImg()"  style="margin:5px;"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 上传单证</button></div>

                </div>

                <c:if test="${caseFile.size() > 0 && caseFile != null}">
                    <div id="row1" class="row" style="display: none;margin-left: 400px;">
                        <div class="col-sm-but"><button onclick="checkAll()" type="button" style="margin:5px;"  class="btn btn-success loading-btn">全选</button></div>
                        <%--<div class="col-sm-but" id="btnSubmit"><button type="submit"  style="margin:5px;"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off">批量下载</button></div>--%>
                        <div class="col-sm-but"><button onclick="downList()" type="button" style="margin:5px;"  class="btn btn-success loading-btn">批量下载</button></div>
                        <div class="col-sm-but"><button onclick="popup('${caseId}','caseFileMidRemove')" type="button" style="margin:5px;"  class="btn btn-success loading-btn">批量移动</button></div>
                        <div class="col-sm-but"><button onclick="deleteList('caseFileMidDelete')" type="button" style="margin:5px;"  class="btn btn-success loading-btn">批量删除</button></div>
                        <div class="col-sm-but"><button onclick="cOro(1)" type="button" style="margin:5px;"  class="btn btn-default">&nbsp;  取  消  &nbsp;</button></div>
                    </div>
                    <div id="row2" class="row">
                        <div class="col-sm-1 col-sm-offset-9"><button onclick="cOro(2)" type="button" style="margin:5px;"  class="btn btn-success loading-btn">批量操作</button></div>
                    </div>
                </c:if>

                <div class="row panel panel-info" id = "files">
                    <div class="col-sm-12 row" id="f" style="margin:5px 0">
                        <c:forEach items="${caseFile}" var="file" varStatus="st">
                            <div class="col-sm-3" style="width: 15%;">
                                    <%--<a href="${file.commonFile.filePath}" target="_blank"><img id="infImg" src="${file.commonFile.filePath}" width="100" height="100"><br/></a>--%>
                                <c:if test="${file.type == 2}">
                                    <img id="infImg" src="${file.commonFile.filePath}" width="100" height="100" onclick="openImgs(${st.index})"/>
                                    <div class="uuu">${file.catalogName}</div>
                                    <br/>
                                </c:if>
                                <c:if test="${file.type == 1}">
                                    <a href="${file.commonFile.filePath}" target="_blank"><img src="../img/pdf.jpg" width="100" height="100">
                                        <div class="uuu">${file.catalogName}</div>
                                    </a><br/>
                                </c:if>
                                <%--<fmt:formatDate value="${file.commonFile.createTime}" pattern="yyyy-MM-dd HH:mm"/><br/>--%>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <%--<c:if test="${file.isDownLoad==false}">disabled="false"</c:if>--%>
                                <input type="checkbox" name="files" value="${file.commonFile.filePath}" onclick="poolIds(${file.id})" data-id="${file.id}" class="picButton"  style="display: none" >
                            </div>
                        </c:forEach>
                    </div>

                </div>
            </div>

        </form>
    </div>
</div>
</div><!--main end-->
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
                $("#infImg2").attr("src","https://ddrapi.shlefan.com/sftp/files/"+pathImg);
                $("#adPic").val("https://ddrapi.shlefan.com/sftp/files/"+pathImg);
                $("#infImg2").show();
            }else {
                alert("上传失败，请重试111");
            }

        }
    });
    $("#editFrom").bind('submit', function(event) {
        var check_name = document.getElementsByName("files");
        var idArr=new Array();
        for(var i=0;i<check_name.length;i++){
            if(check_name[i].checked){
                if(check_name[i].value.indexOf("openapi.shlefan.com") > 1){
                    alert("32上面的案件")
                }
                idArr.push(check_name[i].value);
            }
        }
        if(idArr.length == 0){
            alert("请最少选中一条导出");
            return false;
        }
    });

    function downList(){
        var check_name = document.getElementsByName("files");
        var idArr=[];
        for(var i=0;i<check_name.length;i++){
            if(check_name[i].checked){
                idArr.push(check_name[i].value);
            }
        }
        if(idArr.length == 0){
            alert("请最少选中一条导出");
            return false;
        }

        if(confirm('是否确认下载？')){
            var url = '${ctx}/case/updCaseCenterInfoConfirmRepay?files='+JSON.stringify(idArr)+'&fromType=32Type';
            var param = {};
            ajaxSubmit(url,param,function(v,e,p){
                var path = e.data.results.realPath;

                var aLink = document.createElement('a');
                aLink.href=path
                aLink.dispatchEvent(new MouseEvent('click', {
                    bubbles: true,
                    cancelable: true,
                    view: window
                }));

//                window.location.href = path;
//                reload()
            })
        }

    }

    function uploadImg(){
        var caseNo = $("#caseNo").val();
        var caseId = $("#caseId").val();
        var catalogId = $("#catalogId").val();
        var catalogName = $("#catalogName").val();
        var img = $("#img").val();
        $.ajax({
            url:'${ctx}/case/uploadCommonFileImg?caseNo='+caseNo+'&caseId='+caseId+'&catalogId='+catalogId+'&catalogName='+catalogName+'&img='+img,
            type:"Get",
            success:function(res,param){
                window.location.reload();
//                $("#uploadDiv").html();
//                onBack(caseNo,caseId,catalogId,catalogName);
            }
        });
    }

    function openImgs(index){
//        var caseFile = $("#caseFile").val();
        var caseNo = $("#caseNo").val();
        var catalogId = $("#catalogId").val();
        <%--window.open("${ctx}/case/caseFileMidShow?index="+index+"&caseNo="+caseNo+"&catalogId="+catalogId);--%>
        openDialog({
            frame:true,
            title:"查看图片",
            height:550,
            width:1300,
            <%--url:"${ctx}/case/caseFileMidShow?caseFile="+caseFile+"&index="+index--%>
            <%--url:"${ctx}/case/caseFileMidShow?caseNo="+caseNo+"&index="+index+"&catalogId="+catalogId--%>
            url:"${ctx}/case/caseFileMidShow?index="+index+"&caseNo="+caseNo+"&catalogId="+catalogId
        });
    }

    //材料凭证
    var value = "";
    var id = 0;
    $('#materialFileupload').fileupload({
        done: function (e, data) {
            id = id + 1;
            var r  = data.result;
            var img=r.images;
            var pathImg=img[0].userFilePath;
            var vSrc = "";
            var ext = pathImg.substring(pathImg.lastIndexOf(".") + 1, pathImg.length);
//                $("#materialImgsDiv").append("<img width='80' height='80' src='http://ddrapi.shlefan.com/sftp/files/"+pathImg+"' />");
            var v = 'https://ddrapi.shlefan.com/sftp/files/' + pathImg;
            if(ext == "txt"){
                vSrc = "../img/txt.png";
            }else if(ext == "docx" || ext == "doc"){
                vSrc = "../img/word.png";
            }else if(ext == "xls" || ext == "xlsx"){
                vSrc = "../img/excel.png";
            }else if(ext == "pdf"){
                vSrc = "../img/pdf.jpg";
            }else{
                vSrc = "https://ddrapi.shlefan.com/sftp/files/" + pathImg;
            }
            $("#materialImgsDiv").append("<div id='div_" + id + "' class='col-sm-3'><img width='80' height='80' src='" + vSrc + "' />" +
            "<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id='" + id + "'  name='chknames' type='checkbox' value='" + v + "'> </div>");
            value += 'https://ddrapi.shlefan.com/sftp/files/' + pathImg + ',';

            $("#img").val(value);
            $("#materialImgsDiv").show();
            $("#deleteDiv").show();

        }
    });

    var divs = [];
    function del(){
        var chks = document.getElementsByName('chknames');
        for(var i = 0 ; i < chks.length ;i++){
            var chk = chks[i];
            if(chk.checked){
                value = value.replace(chk.value + ",","");
                divs.push($("#div_" + chk.id));
            }
        }
        for(var i = 0 ; i < divs.length ;i++){
            divs[i].remove();
        }
        $("#img").val(value);
    }

    var filesIds =[];
    function poolIds(id) {
        if(filesIds.indexOf(id) >-1){
            filesIds.splice(filesIds.indexOf(id),1);
        }else{
            filesIds.push(id);
        }
    }
    function popup(id,code) {
        var check_name = document.getElementsByName("files");
        var idArr=new Array();
        for(var i=0;i<check_name.length;i++){
            if(check_name[i].checked){
                idArr.push(check_name[i].value);
            }
        }
        if(idArr.length == 0){
            alert("请最少选中一条导出");
            return false;
        }
        openDialog({
            frame: true,
            title: "数据",
            height: 500,
            width: 800,
            url: "${ctx}/case/center/popup?caseId=" + id+"&code="+code+"&files="+filesIds
        });
    }

    function deleteList(code){
        var check_name = document.getElementsByName("files");
        var idArr=new Array();
        for(var i=0;i<check_name.length;i++){
            if(check_name[i].checked){
                idArr.push(check_name[i].value);
            }
        }
        if(idArr.length == 0){
            alert("请最少选中一条导出");
            return false;
        }
        <%--ajaxSubmit("${ctx}/case/center/caseFileMidOperate",{"code":code, "files":filesIds},reload,"删除成功！","确认删除？","删除失败！");--%>
        ajaxSubmit("${ctx}/case/center/caseFileMidOperate?code="+code+"&files="+filesIds,{},reload,"删除成功！","确认删除？","删除失败！");
    }

    function checkAll() {
        var ids = []
        $('#files .picButton').prop('checked',true)
        $('#files .picButton').map(function (i,cur) {
            ids.push(Number($(this).attr('data-id')))
        })
        var newIds = filesIds.concat(ids)
        filesIds = removeRepeat1(newIds)
        console.log(filesIds)
    }
    function removeRepeat1(arr){
        var removeArr = [],obj = {};
        var leng = arr.length
        for(var i = 0, l = leng; i < l; i++){
            if(!obj[arr[i]]){
                removeArr.push(arr[i]);
                obj[arr[i]] = 1;
            }
        };
        return removeArr;
    }

    function cOro(type) {
        var controls=document.getElementsByName("files");
        if(type==1){
            $("#row1").hide();
            $("#row2").show();
            for(var i=0;i<controls.length;i++)
            {
                $(controls[i]).hide();
//                controls[i].style.display="none";
            }

        }else if(type==2){
            $("#row2").hide();
            $("#row1").show();
            for(var i=0;i<controls.length;i++)
            {
                $(controls[i]).show();
//                controls[i].style.display="true";
            }
        }
    }
</script>
</body>
</html>