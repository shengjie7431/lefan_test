<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/common/mainCss.jsp" %>
    <link rel="stylesheet" href="${ctx}/js/jQueryFileUpload/jquery.fileupload.css">
    <script>


    </script>
    <style>
        .main22 {
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            overflow: hidden;
        }

        .step-contain {
            width: 100%;
            height: 100%;
            margin: 0 auto;
            background-color: #fff;
        }
        .step-contain .title{
            padding: 20px 0;
            width: 100%;
            font-size: 16px;
            text-align: center;
            display: block;
            border: none;
        }
        .border7{
            width: 100%;
            height: 7px;
        }
        .content{
            width: 100%;
            padding: 20px 0;
        }
        .content .btn-area {
            margin: 0 auto;
            width: 276px;
            display: flex;
            justify-content: space-between;
        }
        .content .btn-area .c-btn{
            width: 120px;
            height: 40px;
            line-height: 35px;
            color: #333;
            border:1px solid #bbb;
            font-size: 14px;
            text-align: center;
        }
        .content .btn-area .c-btn2{
            background-color: #259B24;
            width: 120px;
            height: 40px;
            line-height: 35px;
            color: #fff;
            border:1px solid #259B24;
            font-size: 14px;
            text-align: center;
        }
    </style>
    <style>
        .contain{
            width: 100%;
            padding: 10%;
            border: 1px solid #eee;
            font-size: 14px;
        }
        .c-title{
            width: 100%;
            text-align: center;
        }
        .params{
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 60px 0;
        }
        .params .param{
            width: 146px;
            height: 40px;
        }
        .params .icon-add{
            padding: 0 10px;
        }
        .params .param select{
            width: 146px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            background-color: #fff!important;
        }
        .params .param select option{
            text-align: center;
        }
        .c-btns{
            width: 40%;
            display: flex;
            justify-content: space-between;
            margin: 0 auto;
            padding-top: 60px;
        }
        .c-btns .c-btn{
            width: 120px;
            height: 35px;
            line-height: 35px;
            border: 1px solid #bbb;
            color: #333;
            background-color: #fff;
            text-align: center;
        }
        .c-btns div.c-btn-active{
            border: 1px solid #259B24;
            color: #fff;
            background-color: #259B24;
        }
        .dalogs{
            display: none;
            position: absolute;
            bottom: 30%;
            left: 20%;
            width: 400px;
            height: 300px;
            background-color: #fff;
            box-shadow: 0 0 10px #999;
            color: #000;
            z-index: 10000;
        }
    </style>
</head>
<body>
    <div class="contain">
        <form id="editForm" role="form" action="${ctx}/baseSurvey/update" method="post">
            <input type="hidden" name="surveyCode" value="consignorReportRule">
            <input type="hidden" name="btnCode" value="${btnCode}">
            <input type="hidden" name="surveyConsignorId" value="${surveyConsignorId}">
            <input type="hidden" name="surveyConsignorName" value="${surveyConsignorName}">
            <div class="c-title">报告命名规则</div>
            <div class="params">
                <c:if test="${surveyConsignorReportRule ==null}">
                    <div class="param">
                        <select name="oneRule" id="oneRule" onchange="inputSix('1',this)">
                            <option value="1" selected="selected">保单号</option>
                            <option value="2" >被保险人姓名</option>
                            <option value="3">理赔编号</option>
                            <option value="4">保险公司名称</option>
                            <option value="7">部门名称</option>
                            <option value="5">空</option>
                            <option value="6">手动输入内容</option>
                        </select>
                    </div>
                    <div class="icon-add">+</div>
                    <div class="param">
                        <select name="twoRule" id="twoRule" onchange="inputSix('2',this)">
                            <option value="1">保单号</option>
                            <option value="2" selected="selected">被保险人姓名</option>
                            <option value="3">理赔编号</option>
                            <option value="4">保险公司名称</option>
                            <option value="7">部门名称</option>
                            <option value="5">空</option>
                            <option value="6">手动输入内容</option>
                        </select>
                    </div>
                    <div class="icon-add">+</div>
                    <div class="param">
                        <select name="threeRule" id="threeRule" onchange="inputSix('3',this)">
                            <option value="1">保单号</option>
                            <option value="2">被保险人姓名</option>
                            <option value="3">理赔编号</option>
                            <option value="4">保险公司名称</option>
                            <option value="7">部门名称</option>
                            <option value="5" selected="selected">空</option>
                            <option value="6">手动输入内容</option>
                        </select>
                    </div>
                    <div class="icon-add">+</div>
                    <div class="param">
                        <select name="fourRule" id="fourRule" onchange="inputSix('4',this)">
                            <option value="1">保单号</option>
                            <option value="2">被保险人姓名</option>
                            <option value="3">理赔编号</option>
                            <option value="4">保险公司名称</option>
                            <option value="7">部门名称</option>
                            <option value="5" selected="selected">空</option>
                            <option value="6">手动输入内容</option>
                        </select>
                    </div>
                    <%--<input type="text" id = "oneRuleStr" name="oneRuleStr" class="form-control" style="display: none">--%>
                    <input type="text" id = "twoRuleStr" name="twoRuleStr" class="form-control" style="display: none">
                    <input type="text" id = "threeRuleStr" name="threeRuleStr" class="form-control" style="display: none">
                    <input type="text" id = "fourRuleStr" name="fourRuleStr" class="form-control" style="display: none">
                </c:if>


                <c:if test="${surveyConsignorReportRule !=null}">
                    <div class="param">
                        <select name="oneRule" id="oneRule" onchange="inputSix('1',this)">
                            <option value="1" <c:if test="${surveyConsignorReportRule.oneRule == 1}"> selected="selected" </c:if>>保单号</option>
                            <option value="2" <c:if test="${surveyConsignorReportRule.oneRule == 2}"> selected="selected" </c:if>>被保险人姓名</option>
                            <option value="3" <c:if test="${surveyConsignorReportRule.oneRule == 3}"> selected="selected" </c:if>>理赔编号</option>
                            <option value="4" <c:if test="${surveyConsignorReportRule.oneRule == 4}"> selected="selected" </c:if>>保险公司名称</option>
                            <option value="7" <c:if test="${surveyConsignorReportRule.oneRule == 7}"> selected="selected" </c:if>>部门名称</option>
                            <option value="5" <c:if test="${surveyConsignorReportRule.oneRule == 5}"> selected="selected" </c:if>>空</option>
                            <option value="6" <c:if test="${surveyConsignorReportRule.oneRule == 6}"> selected="selected" </c:if>>
                                <c:if test="${surveyConsignorReportRule.oneRule == 6}">${surveyConsignorReportRule.oneRuleStr}</c:if>
                                <c:if test="${surveyConsignorReportRule.oneRule != 6}">手动输入内容</c:if>
                            </option>
                        </select>
                    </div>
                    <input type="text" id = "oneRuleStr" name="oneRuleStr" class="form-control" style="display: none;height: 40px;" value="${surveyConsignorReportRule.oneRuleStr}" onclick="openSelect(1,'oneRule')">
                    <div class="icon-add">+</div>
                    <div class="param">
                        <select name="twoRule" id="twoRule" onchange="inputSix('2',this)">
                            <option value="1" <c:if test="${surveyConsignorReportRule.twoRule == 1}"> selected="selected" </c:if>>保单号</option>
                            <option value="2" <c:if test="${surveyConsignorReportRule.twoRule == 2}"> selected="selected" </c:if>>被保险人姓名</option>
                            <option value="3" <c:if test="${surveyConsignorReportRule.twoRule == 3}"> selected="selected" </c:if>>理赔编号</option>
                            <option value="4" <c:if test="${surveyConsignorReportRule.twoRule == 4}"> selected="selected" </c:if>>保险公司名称</option>
                            <option value="7" <c:if test="${surveyConsignorReportRule.twoRule == 7}"> selected="selected" </c:if>>部门名称</option>
                            <option value="5" <c:if test="${surveyConsignorReportRule.twoRule == 5}"> selected="selected" </c:if>>空</option>
                            <option value="6" <c:if test="${surveyConsignorReportRule.twoRule == 6}"> selected="selected" </c:if>>
                                <c:if test="${surveyConsignorReportRule.twoRule == 6}">${surveyConsignorReportRule.twoRuleStr}</c:if>
                                <c:if test="${surveyConsignorReportRule.twoRule != 6}">手动输入内容</c:if>
                            </option>
                        </select>
                    </div>
                    <input type="text" id = "twoRuleStr" name="twoRuleStr" class="form-control" style="display: none;height: 40px;" value="${surveyConsignorReportRule.twoRuleStr}" onclick="openSelect(2,'twoRule')">
                    <div class="icon-add">+</div>
                    <div class="param">
                        <select name="threeRule" id="threeRule" onchange="inputSix('3',this)">
                            <option value="1" <c:if test="${surveyConsignorReportRule.threeRule == 1}"> selected="selected" </c:if>>保单号</option>
                            <option value="2" <c:if test="${surveyConsignorReportRule.threeRule == 2}"> selected="selected" </c:if>>被保险人姓名</option>
                            <option value="3" <c:if test="${surveyConsignorReportRule.threeRule == 3}"> selected="selected" </c:if>>理赔编号</option>
                            <option value="4" <c:if test="${surveyConsignorReportRule.threeRule == 4}"> selected="selected" </c:if>>保险公司名称</option>
                            <option value="7" <c:if test="${surveyConsignorReportRule.threeRule == 7}"> selected="selected" </c:if>>部门名称</option>
                            <option value="5" <c:if test="${surveyConsignorReportRule.threeRule == 5}"> selected="selected" </c:if>>空</option>
                            <option value="6" <c:if test="${surveyConsignorReportRule.threeRule == 6}"> selected="selected" </c:if>>
                                <c:if test="${surveyConsignorReportRule.threeRule == 6}">${surveyConsignorReportRule.threeRuleStr}</c:if>
                                <c:if test="${surveyConsignorReportRule.threeRule != 6}">手动输入内容</c:if>
                            </option>
                        </select>
                    </div>
                    <input type="text" id = "threeRuleStr" name="threeRuleStr" class="form-control" style="display: none;height: 40px;" value="${surveyConsignorReportRule.threeRuleStr}" onclick="openSelect(3,'threeRule')">
                    <div class="icon-add">+</div>
                    <div class="param">
                        <select name="fourRule" id="fourRule" onchange="inputSix('4',this)">
                            <option value="1" <c:if test="${surveyConsignorReportRule.fourRule == 1}"> selected="selected" </c:if>>保单号</option>
                            <option value="2" <c:if test="${surveyConsignorReportRule.fourRule == 2}"> selected="selected" </c:if>>被保险人姓名</option>
                            <option value="3" <c:if test="${surveyConsignorReportRule.fourRule == 3}"> selected="selected" </c:if>>理赔编号</option>
                            <option value="4" <c:if test="${surveyConsignorReportRule.fourRule == 4}"> selected="selected" </c:if>>保险公司名称</option>
                            <option value="7" <c:if test="${surveyConsignorReportRule.fourRule == 7}"> selected="selected" </c:if>>部门名称</option>
                            <option value="5" <c:if test="${surveyConsignorReportRule.fourRule == 5}"> selected="selected" </c:if>>空</option>
                            <option value="6" <c:if test="${surveyConsignorReportRule.fourRule == 6}"> selected="selected" </c:if>>
                                <c:if test="${surveyConsignorReportRule.fourRule == 6}">${surveyConsignorReportRule.fourRuleStr}</c:if>
                                <c:if test="${surveyConsignorReportRule.fourRule != 6}">手动输入内容</c:if>
                            </option>
                        </select>
                    </div>
                    <input type="text" id = "fourRuleStr" name="fourRuleStr" class="form-control" style="display: none;height: 40px;" value="${surveyConsignorReportRule.fourRuleStr}" onclick="openSelect(4,'fourRule')">
                    <%--<input type="text" id = "oneRuleStr" name="oneRuleStr" class="form-control" style="display: none" value="${surveyConsignorReportRule.oneRuleStr}">--%>
                    <%--<input type="text" id = "twoRuleStr" name="twoRuleStr" class="form-control" style="display: none" value="${surveyConsignorReportRule.twoRuleStr}">--%>
                    <%--<input type="text" id = "threeRuleStr" name="threeRuleStr" class="form-control" style="display: none" value="${surveyConsignorReportRule.threeRuleStr}">--%>
                </c:if>
            </div>
            <div class="c-title" id="demoName"></div>
            <div class="c-btns">
                <%--<div class="c-btn" >取消</div>--%>
                <%--<div class="c-btn c-btn-active">保存并关闭</div>--%>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">取消</button>
                <button type="submit" class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 保存并关闭</button>
            </div>

            <div class="dalogs">
                <div class="close" onclick="javascript:$('.dalogs').hide()">×</div>
                <div class="d_content" style="padding: 20px;"></div>
            </div>
        </form>
    </div>

<div id="dialogId"></div>
<script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript"></script>

<script src="${ctx}/js/jQueryFileUpload/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/js/jQueryFileUpload/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-all-min.js" type="text/javascript"></script>

<script type="text/javascript">

    $("#editForm").bind('submit', function(event) {
        //$("#content").text(editor1.html());
        ajaxFormSubmit(this,reloadParent,null,null,function(v,e,p){
            alert(e.data.msg);
        });
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

    var demoName1 ="";
    var demoName2 ="";
    var demoName3 ="";
    var demoName4 ="";
    function inputSix(num,obj){
        var selectId = obj.id;
        var ruleId = $("#"+selectId).find("option:selected").val();
        if(ruleId == 6){
            showStrView(num,obj, selectId, selectId+"Str");
        }

        if(num ==1){
            if(ruleId == 1){
                demoName1 = "BDH123456";
            }else if(ruleId == 2){
                demoName1 = "张三";
            }else if(ruleId == 3){
                demoName1 = "LP123456";
            }else if(ruleId == 4){
                demoName1 = "${surveyConsignorName}";
            }else if(ruleId == 5){
                demoName1 = "";
            }else if(ruleId == 6){
                demoName1 = $("#oneRuleStr").val();
            }else if(ruleId == 7){
                demoName1 = "A部门";
            }
            demoName();
        }else if(num ==2){
            if(ruleId == 1){
                demoName2 = "BDH123456";
            }else if(ruleId == 2){
                demoName2 ="张三";
            }else if(ruleId == 3){
                demoName2 ="LP123456";
            }else if(ruleId == 4){
                demoName2 ="${surveyConsignorName}";
            }else if(ruleId == 5){
                demoName2 ="";
            }else if(ruleId == 6){
                demoName2 =$("#twoRuleStr").val();
            }else if(ruleId == 7){
                demoName2 = "A部门";
            }
            demoName();
        }else if(num ==3){
            if(ruleId == 1){
                demoName3 ="BDH123456";
            }else if(ruleId == 2){
                demoName3 ="张三";
            }else if(ruleId == 3){
                demoName3 ="LP123456";
            }else if(ruleId == 4){
                demoName3 ="${surveyConsignorName}";
            }else if(ruleId == 5){
                demoName3 ="";
            }else if(ruleId == 6){
                demoName3 =$("#threeRuleStr").val();
            }else if(ruleId == 7){
                demoName3 = "A部门";
            }
            demoName();
        }else if(num ==4){
            if(ruleId == 1){
                demoName4 ="BDH123456";
            }else if(ruleId == 2){
                demoName4 ="张三";
            }else if(ruleId == 3){
                demoName4 ="LP123456";
            }else if(ruleId == 4){
                demoName4 ="${surveyConsignorName}";
            }else if(ruleId == 5){
                demoName4 ="";
            }else if(ruleId == 6){
                demoName4 = $("#fourRuleStr").val();
            }else if(ruleId == 7){
                demoName4 = "A部门";
            }
            demoName();
        }
    }

    function demoName(){
        var demoName = "命名示例：";
        demoName += demoName1 + demoName2 + demoName3 +demoName4;
        demoName += "案"
        $("#demoName").html(demoName);
    }

    firstDemoName();

    function firstDemoName(){
        if(${surveyConsignorReportRule!=null}){
            if(${surveyConsignorReportRule.oneRule == 1}){
                demoName1 = "BDH123456";
            }else if(${surveyConsignorReportRule.oneRule == 2}){
                demoName1 = "张三";
            }else if(${surveyConsignorReportRule.oneRule == 3}){
                demoName1 = "LP123456";
            }else if(${surveyConsignorReportRule.oneRule == 4}){
                demoName1 = "${surveyConsignorName}";
            }else if(${surveyConsignorReportRule.oneRule == 5}){
                demoName1 = "";
            }else if(${surveyConsignorReportRule.oneRule == 6}){
                demoName1 = $("#oneRuleStr").val();
            }else if(${surveyConsignorReportRule.oneRule == 7}){
                demoName1 = "A部门";
            }
            demoName();
            if(${surveyConsignorReportRule.twoRule == 1}){
                demoName2 = "BDH123456";
            }else if(${surveyConsignorReportRule.twoRule == 2}){
                demoName2 = "张三";
            }else if(${surveyConsignorReportRule.twoRule == 3}){
                demoName2 = "LP123456";
            }else if(${surveyConsignorReportRule.twoRule == 4}){
                demoName2 = "${surveyConsignorName}";
            }else if(${surveyConsignorReportRule.twoRule == 5}){
                demoName2 = "";
            }else if(${surveyConsignorReportRule.twoRule == 6}){
                demoName2 = $("#twoRuleStr").val();
            }else if(${surveyConsignorReportRule.twoRule == 7}){
                demoName2 = "A部门";
            }
            demoName();
            if(${surveyConsignorReportRule.threeRule == 1}){
                demoName3 = "BDH123456";
            }else if(${surveyConsignorReportRule.threeRule == 2}){
                demoName3 = "张三";
            }else if(${surveyConsignorReportRule.threeRule == 3}){
                demoName3 = "LP123456";
            }else if(${surveyConsignorReportRule.threeRule == 4}){
                demoName3 = "${surveyConsignorName}";
            }else if(${surveyConsignorReportRule.threeRule == 5}){
                demoName3 = "";
            }else if(${surveyConsignorReportRule.threeRule == 6}){
                demoName3 = $("#threeRuleStr").val();
            }else if(${surveyConsignorReportRule.threeRule == 7}){
                demoName3 = "A部门";
            }
            demoName();
            if(${surveyConsignorReportRule.fourRule == 1}){
                demoName4 = "BDH123456";
            }else if(${surveyConsignorReportRule.fourRule == 2}){
                demoName4 = "张三";
            }else if(${surveyConsignorReportRule.fourRule == 3}){
                demoName4 = "LP123456";
            }else if(${surveyConsignorReportRule.fourRule == 4}){
                demoName4 = "${surveyConsignorName}";
            }else if(${surveyConsignorReportRule.fourRule == 5}){
                demoName4 = "";
            }else if(${surveyConsignorReportRule.fourRule == 6}){
                demoName4 = $("#fourRuleStr").val();
            }else if(${surveyConsignorReportRule.fourRule == 7}){
                demoName4 = "A部门";
            }
            demoName();
        }
    }

    //弹窗
    function showStrView(num,obj,optionId,inputId){
        var dialog = $(".dalogs");
        dialog.show();
        var position = $(obj).position();
        $(".dalogs").offset({
            left: 275,
            top: 50
        });

        var html ="";
//        html += '<h3>请输入</h3>';
//        html += '<input type="text" id="popu" name="popu" value=""> <br>';
//        html += '<button type="button" onclick="javascript:assignStr(\''+optionId+'\',\''+inputId+'\');">确定</button>';

        html += '<div class="main22">';
        html += '<div class="step-contain">';
        html += '<div class="title"></div>';
        html += '<div class="border7"><input type="text" id="popu" name="popu" value="" style="height: 40px; width: 300px"> <button type="button" onclick="javascript:assignStr('+num+',\''+optionId+'\',\''+inputId+'\');">确定</button></div>';
        html += '<div class="content">';
        html += '<div class="btn-area">';
        html += '</div></div></div></div>';
        $(".d_content").html(html);
    }

    //关闭弹窗
    function hidStrView(){
        var dialog = $(".dalogs");
        dialog.hide();
    }

    //赋值
    function assignStr(num,optionId,inputId){
        var str = $("#popu").val();
        $("#"+inputId).val(str);
//        $("#"+optionId+" option[value='6']").remove();
//        $("#"+optionId).append("<option value='6' selected='selected'>"+str+"</option>");

        //赋值到对应的input
        $("#"+optionId+"Str").val(str);
        if(num==1){
            demoName1 = $("#oneRuleStr").val();
        }else if(num==2){
            demoName2 = $("#twoRuleStr").val();
        }else if(num==3){
            demoName3 = $("#threeRuleStr").val();
        }else if(num==4){
            demoName4 = $("#fourRuleStr").val();
        }
        demoName();
        var dialog = $(".dalogs");
        dialog.hide();

        //关闭原下拉，显示input
        $("#"+optionId).hide();
        $("#"+optionId+"Str").show();
    }

    function openSelect(num,selectName){
        $("#"+selectName).show();
        $("#"+selectName+"Str").hide();
    }
</script>
</body>
</html>