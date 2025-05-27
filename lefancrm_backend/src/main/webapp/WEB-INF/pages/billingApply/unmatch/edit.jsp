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
</head>
<body>
<div class="container">
    <form id="editForm" role="form" action="${ctx}/billingApplyUnmatch/update" method="post">
        <input type="hidden" name="id" value="${info.id}">
        <div class="form-group">
            <table class="table">
                <tbody>
                <tr>
                    <th width="30%" class="active">编号</th>
                    <td width="70%">
                        <input type="text" id = "unmatchNo" name="unmatchNo" value="${info.unmatchNo}"  readonly="true" style="width: 400px;" class="form-control" required="required">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">到账公司<span style="color: red;">*</span></th>
                    <td width="70%">
                        <select name="receivingCompanyId" style="width: 400px;" class="form-control">
                            <option value="">请选择</option>
                            <c:forEach items="${corporations}" var="item">
                                <option value="${item.id}" <c:if test="${info.receivingCompanyId == item.id}">selected="selected"</c:if>>${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr style="display: none">
                    <th width="30%" class="active">开票产品<span style="color: red;">*</span></th>
                    <td width="70%">
                        <select  name="billingItemsId" style="width: 400px;" class="form-control">
                            <option value="">请选择</option>
                            <c:if test="${info.billingItemsId == null }">
                                <c:forEach items="${bullingItems}" var="item">
                                    <option value="${item.id}" <c:if test="${item.id == 82}">selected="selected"</c:if>>${item.enumText}</option>
                                </c:forEach>
                            </c:if>
                            <c:if test="${info.billingItemsId!= null }">
                                <c:forEach items="${bullingItems}" var="item">
                                    <option value="${item.id}" <c:if test="${info.billingItemsId == item.id}">selected="selected"</c:if>>${item.enumText}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">金额<span style="color: red;">*</span></th>
                    <td width="70%">
                        <input type="number" step="0.01" id = "money" name="money" value="${info.money}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">付款方<span style="color: red;">*</span></th>
                    <td width="70%">
                        <input type="text" id = "payer" name="payer" value="${info.payer}"  style="width: 400px;" class="form-control">
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">交易时间<span style="color: red;">*</span></th>
                    <td width="70%">
                        <input type="text" id = "payTime" name="payTime" value="<fmt:formatDate value="${info.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"  style="width: 400px;" class="form-control time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly>
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">附言</th>
                    <td width="70%">
                        <input type="text" id = "remark" name="remark" value="${info.remark}"  style="width: 400px;" class="form-control" >
                    </td>
                </tr>
                <tr>
                    <th width="30%" class="active">备注</th>
                    <td width="70%">
                        <input type="text" id = "comments" name="comments" value="${info.comments}"  style="width: 400px;" class="form-control" >
                    </td>
                </tr>
                <input type="hidden" id="matchMoney" name="matchMoney" value="${info.matchMoney==null?0:info.matchMoney}"  style="width: 400px;" class="form-control" >
                <input type="hidden" id="unmatchMoney" name="unmatchMoney" value="${info.unmatchMoney==null?0:info.unmatchMoney}"  style="width: 400px;" class="form-control" >
                <input type="hidden" id="state" name="state" value="${info.state}"  style="width: 400px;" class="form-control" >
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="diglog_close_btn-js" onclick="javascript:closeDialog();">关闭</button>
            <button type="submit"  class="btn btn-success loading-btn" data-loading-text="Loading..." autocomplete="off"><span class="glyphicon glyphicon-ok"></span> 确认提交</button>
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
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">


    $(function () {
        var payTime=$("#payTime").val();
        if(payTime == null || payTime =='' || payTime==undefined){
            let date = new Date()
            payTime=dateFormat('YYYY-mm-dd HH:MM:SS', date);
        }
        $("#payTime").val(payTime);
    })

    function dateFormat(fmt, date) {
        let ret;
        const opt = {
            "Y+": date.getFullYear().toString(),        // 年
            "m+": (date.getMonth() + 1).toString(),     // 月
            "d+": date.getDate().toString(),            // 日
            "H+": date.getHours().toString(),           // 时
            "M+": date.getMinutes().toString(),         // 分
            "S+": date.getSeconds().toString()          // 秒
            // 有其他格式化字符需求可以继续添加，必须转化成字符串
        };
        for (let k in opt) {
            ret = new RegExp("(" + k + ")").exec(fmt);
            if (ret) {
                fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
            };
        };
        return fmt;
    }



    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="content"]', {
            cssPath : '${ctx}/js/jQueryFileUpload/plugins/code/prettify.css',
            uploadJson : '${ctx}/uploadFileForKindEditor'
        });
    });

    $("#editForm").bind('submit', function(event) {
        var receivingCompanyId=$("[name='receivingCompanyId']").val();
        if(receivingCompanyId==null||receivingCompanyId==''||receivingCompanyId==undefined){
            alert("请选择到账公司!");
            return false;
        }
        // var billingItemsId=$("[name='billingItemsId']").val();
        // if(billingItemsId==null||billingItemsId==''||billingItemsId==undefined){
        //     alert("请选择开票项目!");
        //     return false;
        // }
        var money=$("[name='money']").val();
        if(money==null||money==''||money==undefined){
            alert("请输入金额!");
            return false;
        }
        var payer=$("[name='payer']").val();
        if(payer==null||payer==''||payer==undefined){
            alert("请填写付款方!");
            return false;
        }
        var payTime=$("[name='payTime']").val();
        if(payTime==null||payTime==''||payTime==undefined){
            alert("交易时间不可为空!");
            return false;
        }
        var matchMoney=$("#matchMoney").val();
        if(matchMoney != null){
            if(Number(money)<Number(matchMoney)){
                alert("金额不能小于已认领金额!");
                return false;
            }
            var unmatchMoney= Number(money) - Number(matchMoney);
            $("#unmatchMoney").val(unmatchMoney);
            if(unmatchMoney == 0){
                $("#state").val(2);
            }else if(unmatchMoney > 0 && Number(matchMoney) !=0 ){
                $("#state").val(3);
            }
        }
        //$("#content").text(editor1.html());
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

    //开票类目默认为“全程通”，自动生成“案件编号”
    toBuildNo();

    /*
     * 根据选择的类目 生成案件编号
     */
    var unmatchNo="";
    function toBuildNo(){
        $.ajax({
            url:'${ctx}/billingApplyUnmatch/toBuildUnmatchNo',
            type:"Get",
            success:function(res,param){
                $("#unmatchNo").val(param.data.results);
                unmatchNo=param.data.results;
            }
        });
    }
</script>
</body>
</html>