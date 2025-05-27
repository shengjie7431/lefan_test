<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <style>
        .table-hover{
            margin-bottom: 70px;
        }
        .modal-footer{
            width: 100%;
            background-color: #fff;
            position: fixed;
            left: 0;
            bottom: 0;
        }
        .display-table{
            display: table-row;
        }
        .display-none{
            display: none;
        }
    </style>
</head>
<body>
<div class="main administrator">
    <div class="main-top">
        <h3>列表</h3>
    </div><!--main-top-->
    <div class="panel-heading">
        <div class="pin">
            <form class="form-inline" role="form" action="${ctx}/baseSurvey/popup?surveyCode=${surveyCode}&modelId=${modelId}&btnCode=${btnCode}" method="post" onsubmit="return false;">
                <div class="form-group">
                    委托方机构:<input name="company" type="text" value="${company}" class="form-control">
                </div>
                <div class="btn-group">
                    &nbsp; &nbsp;<button id="batchBtn" type="button" class="btn btn-default">查询</button>&nbsp; &nbsp;
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-info">
        <form id="editForm" role="form" action="${ctx}/baseSurvey/update" method="post">
            <input type="hidden" name="modelId" value="${modelId}">
            <input type="hidden" name="surveyCode" value="${surveyCode}">
            <input type="hidden" id="roleIds" name="roleIds" value="${roleIds}">
            <input type="hidden" name="btnCode" value="${btnCode}">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th width="80"><input type="checkbox" id="all" >全选(${emailInfoName})</th>
                    <th width="100">委托机构名称<span style="color: red;">(括号内代表该委托机构选定的模板名称)</span></th>
                </tr>
                </thead>
                <tbody class="class-list">
                <c:forEach items="${consignors}" var="item">
                <tr>
                    <td>
                        <input type="checkbox" name="buss" value="${item.id}" class="bussBar"
                            <c:forEach items="${emailInfoOrgs}" var="my">
                                <c:if test="${my.entrustOrgId == item.id }">
                                    checked="checked"
                                </c:if>
                            </c:forEach>
                        />
                    </td>
                    <td class="companyName">
                        ${item.company}
                            <c:forEach items="${emailInfoOrgAll}" var="all">
                                <c:if test="${all.entrustOrgId == item.id }">
                                    <span style="color: #b3aaaa">(${all.emailInfoName})</span>
                                </c:if>
                            </c:forEach>
                            <c:forEach items="${emailInfoOrgs}" var="my">
                                <c:if test="${my.entrustOrgId == item.id }">
                                    <a href="javascript:operate('${my.id}','${surveyCode}','9999',true);">移除</a>
                                </c:if>
                            </c:forEach>
                    </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="modal-footer">
                <button id="batchOperateBtn" type="submit" class="btn btn-default">确定提交</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            </div>
        </form>
    </div>
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
    $(document).ready(function(){
        $("#all").on('change',function(){
            var _checked= this.checked
            $('.class-list tr').map(function (i,cur) {
                if (!$(cur).hasClass('display-none')){
                    $(cur).find("input[name='buss']").prop("checked",_checked);
                }
            })
        })
    })

    $('#batchBtn').click(function () {
        var _val = $('input[name=company]').val()
        $('.class-list tr').map(function () {
            var _this = $(this)
            var _text = _this.find('.companyName').text().replace(/\s+/g,"");
            if (_text.indexOf(_val) > -1){
                _this.addClass('display-table').removeClass('display-none')
            }else{
                _this.addClass('display-none').removeClass('display-table')
            }
        })
        
    })
    $(function(){
        $("#editForm").bind('submit', function(event) {
            $("#batchOperateBtn").attr("disabled",true);
            _checkRole();
            ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
            event.preventDefault();
        });

        do_list_checkbox();
    });

    function _checkRole(){
        var check_name = document.getElementsByName("buss");
        var idArr=new Array();
        for(var i=0;i<check_name.length;i++){
            if(check_name[i].checked){
                idArr.push(check_name[i].value);
            }
        }
        $("#roleIds").val(idArr);
    }

    function do_list_checkbox(){
        var checkValue = "${userRoleIds}";
        $("input[name=buss]").each(function (){
            var indexCode = checkValue.indexOf(","+$(this).val()+",");
            if(indexCode>-1){
                $(this).attr("checked","checked");
            }
        });
    }

    function operate(id,surveyCode,btnCode,ajax){
        if(ajax){
            var url = "${ctx}/baseSurvey/operate",param = {"id":id,"surveyCode":surveyCode,"btnCode":btnCode};
            if(confirm('是否确认？')){
                ajaxSubmit(url,param,function(v,e,p){
                    if(btnCode == '9999') {
                        location.reload();
                    }
                })
            }
        }
    }
</script>
</body>
</html>