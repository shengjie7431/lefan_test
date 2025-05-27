<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <style>
        .items{
            width: 600px;
            height: 252px;
            padding: 20px;
            padding-right: 0;
            text-align: center;
            border: 1px solid #66C8FF;
            margin: 20px auto;
        }
        .item{
            float: left;
            width: 120px;
            height: 40px;
            line-height: 40px;
            border:1px solid #66C8FF;
            color: #3ba9ff;
            font-size: 14px;
            text-align: center;
            margin-right: 20px;
            margin-bottom: 20px;
        }
        div.active{
            background-color: #66C8FF;
            color: #fff;
        }
        .uuu{
            text-align: center;
            height: 50px;
            line-height: 64px;
            font-size: 18px;
        }
    </style>
</head>
<body>
<div class="container">

    <form id="editForm" action="${ctx}/case/center/exportNew"  method="post">
        <input type="hidden" id="ids" name="ids" value="${ids}">
        <input type="hidden" id="id" name="id" value="${id}">

            <table class="table">

                <div class="uuu">
                    选择要导出的模块
                </div>
                <div class="items">
                    <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState <5}">
                        <div class="item active" onclick="choice(1,true,101)" id="101">
                            案件基本信息
                        </div>
                        <input type="hidden" name="buss" id="1" value="1" checked="true">

                        <c:if test="${caseInfo.extend2.processState >=2}">
                            <div class="item active" onclick="choice(5,true,105)" id="105">
                                签约指导
                            </div>
                            <input type="hidden" name="buss" id="5" value="5" checked="true">
                        </c:if>
                        <c:if test="${caseInfo.extend2.processState >=3}">
                            <div class="item active" onclick="choice(6,true,106)" id="106">
                                伤残预估
                            </div>
                            <input type="hidden" name="buss" id="6" value="6" checked="true">
                        </c:if>
                        <c:if test="${caseInfo.extend2.processState >=4}">
                            <div class="item active" onclick="choice(7,true,107)" id="107">
                                测算报告
                            </div>
                            <input type="hidden" name="buss" id="7" value="7" checked="true">
                        </c:if>
                    </c:if>
                    <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=5}">
                        <div class="item active" onclick="choice(1,true,101)" id="101">
                            案件基本信息
                        </div>
                        <input type="hidden" name="buss" id="1" value="1" checked="true">

                        <div class="item active" onclick="choice(2,true,102)" id="102">
                            产品类型
                        </div>
                        <input type="hidden" name="buss" id="2" value="2" checked="true">

                        <div class="item active" onclick="choice(4,true,104)" id="104">
                            收费方式
                        </div>
                        <input type="hidden" name="buss" id="4" value="4" checked="true">

                        <div class="item active" onclick="choice(5,true,105)" id="105">
                            签约指导
                        </div>
                        <input type="hidden" name="buss" id="5" value="5" checked="true">

                        <div class="item active" onclick="choice(6,true,106)" id="106">
                            伤残预估
                        </div>
                        <input type="hidden" name="buss" id="6" value="6" checked="true">

                        <div class="item active" onclick="choice(7,true,107)" id="107">
                            测算报告
                        </div>
                        <input type="hidden" name="buss" id="7" value="7" checked="true">

                        <div class="item active" onclick="choice(8,true,108)" id="108">
                            评估服务费
                        </div>
                        <input type="hidden" name="buss" id="8" value="8" checked="true">

                        <c:if test="${caseInfo.extend2.processState >=7}">
                            <div class="item active" onclick="choice(9,true,109)" id="109">
                                索赔指导
                            </div>
                            <input type="hidden" name="buss" id="9" value="9" checked="true">
                        </c:if>
                    </c:if>

                    <c:if test="${caseInfo.extend2.processState!=null && caseInfo.extend2.processState >=7}">
                        <div class="item active" onclick="choice(10,true,110)" id="110">
                            索赔预案
                        </div>
                        <input type="hidden" name="buss" id="10" value="10" checked="true">

                        <div class="item active" onclick="choice(11,true,111)" id="111">
                            赔偿预案调解金额
                        </div>
                        <input type="hidden" name="buss" id="11" value="11" checked="true">

                        <c:if test="${caseInfo.legalUserId !=null}">
                            <div class="item active" onclick="choice(12,true,112)" id="112">
                                诉讼预案
                            </div>
                            <input type="hidden" name="buss" id="12" value="12" checked="true">

                            <div class="item active" onclick="choice(13,true,113)" id="113">
                                赔偿预案诉讼金额
                            </div>
                            <input type="hidden" name="buss" id="13" value="13" checked="true">

                        </c:if>
                    </c:if>
                </div>
            </table>
        <div class="modal-footer">
            <button type="submit" class="btn btn-success loading-btn" style="background-color: #66C8FF" data-loading-text="Loading..." autocomplete="off" onclick="close1()">确认导出</button>
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">取消</button>
        </div>
    </form>
</div>


<div id="dial
ogId"></div>
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
            $("input[name='buss']").prop("checked",this.checked);
        })
    })

    $(function(){
        $("#editForm").bind('submit', function(event) {
            var check_name = document.getElementsByName("buss");
            var idArr=new Array();
            for(var i=0;i<check_name.length;i++){
                if(check_name[i].checked){
                    idArr.push(check_name[i].value);
                }
            }
            if(idArr.length == 0){
                alert("请最少选中一条导出");
                return false;
            }else{
                $("#batchOperateBtn").attr("disabled",true);
                _checkRole();
//                ajaxFormSubmit(this,reloadParent,null,null,reloadParent);
//                event.preventDefault();
            }
        });
    });

    function _checkRole(){
        var check_name = document.getElementsByName("buss");
        var idArr=new Array();
        for(var i=0;i<check_name.length;i++){
            if(check_name[i].checked){
                idArr.push(check_name[i].value);
            }
        }
        $("#ids").val(idArr);
    }

    function choice(id,bool,id2) {
        if(bool){
            $("#"+id2).attr("class","item");
            $("#"+id2).attr("onclick","choice("+id+",false,"+id2+")");
            $("#"+id).removeAttr("checked");
        }else{
            $("#"+id2).attr("class","item active");
            $("#"+id2).attr("onclick","choice("+id+",true,"+id2+")");
            $("#"+id).prop("checked",true);
        }
    }

    function close1() {
        reloadParent();
    }
</script>
</body>
</html>