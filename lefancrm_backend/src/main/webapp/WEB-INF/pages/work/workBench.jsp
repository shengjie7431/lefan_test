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
        height: 35px;
        background: #d9edf7;
        border-radius: 5px;text-align: center;line-height: 30px;
    }
    .list{
        display: block;
    }
    .list li{
        display: block;
        height: 50px;
        line-height: 50px;
    }
    .list li span{
        padding: 0 20px;
    }
</style>
<script>
    function selectDate(type){


        var date;
        var orgId = 0;
        if(type == 1){
            date = $("#saleGold").val();
        }else if(type == 2){
            date = $("#activityDayReport").val();
        }else if(type == 3){
            date = $("#sale").val();
            orgId = $("#org").val();
        }
       // alert(type+" "+date);
      ajaxSubmit("${ctx}/work/queryWorkDate",{"date":date,"type":type,"orgId":orgId},function(v,e,p){
          if(type == 1){
              $('.saleAmount').text(e.data.results.saleAmount);
              $('.saleGold').text(e.data.results.saleGold);
              bx();
          }else if(type == 2){
               $('.activityDayReports').empty();
              for(var i = 0; i < e.data.results.length; i++){
                  var val = e.data.results[i];
                  $('.activityDayReports').append('<tr>'
                          +' <td width="100"  style="display: none" class="td1">'+val.userName+'</td>'
                          +'<td width="150"   style="display: none" class="td2">'+val.completionRate+'</td>'
                          +' </tr>')
              }
              zz();
          }else if(type == 3){
              $('.activityDayReport').empty();
              var val = e.data.results;
              var targetNumRate = (val.targetNum / val.visitNum * 100).toFixed(2);
              var intentionNumRate = (val.intentionNum/val.targetNum*100).toFixed(2);
              var signNumRate = (val.signNum / val.intentionNum * 100).toFixed(2);
              var closedNumRate = (val.closedNum / val.signNum * 100).toFixed(2);
              if(isNaN(targetNumRate) ){
                  targetNumRate = 0;
              }
              if(isNaN(intentionNumRate) ){
                  intentionNumRate = 0;
              }
              if(isNaN(signNumRate) ){
                  signNumRate = 0;
              }
              if(isNaN(closedNumRate) ){
                  closedNumRate = 0;
              }
             $('.activityDayReport').append(
             '<ul class="list">'
                      +'<li ><a style="display: inline-block;width: 10px;height: 10px;border-radius: 10px;background: #4FC2F8;"></a><span style="color: #4FC2F8">拜访客户</span>'
                     +'<span style="color: #000000">'+val.visitNum+'单</span><span style="color: #000000"></span></li>'
                     +'<li style="color: #73BA78"><a  style="display: inline-block;width: 10px;height: 10px;border-radius: 10px;background: #73BA78"></a><span>目标客户</span>'
                     +'<span style="color: #000000">'+val.targetNum+'单</span><span style="color: #000000">'+targetNumRate+'%</span></li>'
                     +'<li style="color: #ffd54f"><a  style="display: inline-block;width: 10px;height: 10px;border-radius: 10px;background: #ffd54f"></a><span>意向客户</span>'
                     +'<span style="color: #000000">'+val.intentionNum+'单</span><span style="color: #000000">'+intentionNumRate+'%</span></li>'
                     +' <li style="color: #f16156"><a  style="display: inline-block;width: 10px;height: 10px;border-radius: 10px;background: #f16156"></a><span>签约客户</span>'
                     +'<span style="color: #000000">'+val.signNum+'单</span><span style="color: #000000">'+signNumRate+'%</span></li>'
                     +'<li style="color: #eb5180"><a  style="display: inline-block;width: 10px;height: 10px;border-radius: 10px;background: #eb5180"></a><span>结案客户</span>'
                     +'<span style="color: #000000">'+val.closedNum+'单</span><span style="color: #000000">'+closedNumRate+'%</span></li>'
                     +'</ul>'
              );
          }
        })
    }
</script>
<body>
      <div class="box">
          <div class="box1">
            <div class="top">销售简报</div>
            <div class="table-responsive">
              <table class="table table-hover">
                <tbody class="class-list">
                  <thead>

              <c:if test="${fn:length(workBench.caseSumDatas) == 0}">
              <tr>
                  <td width="100">
                      简易案件
                  </td>
                  <td width="100">
                      0
                  </td>
                  <td width="100">￥0</td>
              </tr>
                  <tr>
                      <td width="100">
                          案件代理
                      </td>
                      <td width="100">
                          0
                      </td>
                      <td width="100">￥0</td>
                  </tr>
                  <tr>
                      <td width="100">
                          代理+垫付
                      </td>

                      <td width="100">
                          0
                      </td>
                      <td width="100">￥0</td>
                  </tr>

              </c:if>
                    <c:forEach items="${workBench.caseSumDatas}" var="caseSumData">
                        <tr>
                            <td width="100"><c:if test="${caseSumData.caseType == 1}">
                                简易案件
                            </c:if>
                                <c:if test="${caseSumData.caseType == 2}">
                                    案件代理
                                </c:if>
                                <c:if test="${caseSumData.caseType == 3}">
                                    代理+垫付
                                </c:if>
                            </td>
                            <td width="100">${caseSumData.caseCount}</td>
                            <td width="100">￥${caseSumData.serviceFee}</td>
                        </tr>
                    </c:forEach>
                  </thead>
                </tbody>
                </table>
            </div>
          </div>
          <div class="box1">
            <div class="top">案件汇总</div>
            <div class="table-responsive">
                <table class="table table-hover">
                    <tbody class="class-list">
                    <thead>
                    <tr>
                        <th  width="100">成交数</th>
                        <th width="100">已放金额</th>
                        <th width="100">已回款金额</th>
                    </tr>
                    <tr>
                        <td  width="100">${workBench.caseSumReport.caseCount}</td>
                        <td width="100">￥${workBench.caseSumReport.loanFee}
                            <c:if test="${workBench.caseSumReport.loanFee == null}">0</c:if>
                        </td>
                        <td width="100">￥${workBench.caseSumReport.serviceFee}
                            <c:if test="${workBench.caseSumReport.serviceFee == null}">0</c:if>
                        </td>
                    </tr>
                    </thead>
                    </tbody>
                </table>
            </div>
          </div>
          <div class="box1">
            <div class="top">业绩目标
               <select  id="saleGold" class="form-control gengduo" style="width: 10%;height: 35px" onchange="selectDate(1)">
                   <option value="1">本月</option>
                    <option value="2">上月</option>
                    <option value="3">本季度</option>
                    <option value="4">上季度</option>
                    <option value="5">本年</option>
                </select>
            </div>
            <div class="table-responsive">
                <table class="table table-hover">
                  <%--  <thead>
                    <tr>
                        <th  width="150">已完成：￥<span class="saleAmount">${workBench.saleAmount}</span></th>
                        <th  width="150">目标：￥<span class="saleGold">${workBench.saleGold}</span></th>
                    </tr>
                    </thead>--%>
                    <tbody class="class-list">
                    </tbody>
                </table>
                <div class="row">
                    <div id="mycharts" class="col-md-6" style="height: 300px">
                    </div>
                    <div  class="col-md-6" style="height: 300px;">
                        <p style="padding-top: 130px;"><span>已完成：￥<span class="saleAmount">${workBench.saleAmount}</span></span><br>
                            <span>目标：￥<span class="saleGold">${workBench.saleGold}</span></span>
                        </p>
                    </div>
                </div>
            </div>
          </div>
          <div class="box1">
            <div class="top">业绩比例排行
                <select  id="activityDayReport" class="form-control gengduo" style="width: 10%;height: 35px" onchange="selectDate(2)">
                <option value="1">本月</option>
                <option value="2">上月</option>
                <option value="3">本季度</option>
                <option value="4">上季度</option>
                <option value="5">本年</option>
            </select></div>
            <div class="table-responsive">
                <div id="zz"  style="height: 500px;"></div>
                <table class="table table-hover activityDayReports">
                    <c:forEach items="${workBench.activityDayReports}" var="report">
                        <tr class="tr1">
                            <td  width="100" class="td1" style="display: none">${report.userName}</td>
                                <%--<td width="150">${report.orgName}</td>--%>
                                <%--<td width="150">￥${report.saleAmount}</td>--%>
                                <%--<td width="150">￥${report.saleGoal}</td>--%>
                            <td width="150"  class="td2"  style="display: none">${report.completionRate}</td>
                        </tr>

                    </c:forEach>
                    <%--<thead>
                    <tr>
                        <th width="100">姓名</th>
                        <th width="150">所在机构</th>
                        <th width="150">完成金额</th>
                        <th width="150">目标金额</th>
                        <th width="150">完成率</th>
                    </tr>
                    </thead>--%>

                    <tbody class="class-list">
                    </tbody>
                </table>
            </div>
          </div>
            <div class="box1">
                <div class="top">销售漏斗
                    <select  id="sale" class="form-control gengduo" style="width: 10%;height: 35px" onchange="selectDate(3)">
                        <option value="1">本月</option>
                        <option value="2">上月</option>
                        <option value="3">本季度</option>
                        <option value="4">上季度</option>
                        <option value="5">本年</option>
                    </select>


                    <select id="org" class="form-control gengduo" style="width: 10%;height: 35px" onchange="selectDate(3)">
                        <option value="${adminDto.orgId}"> ${adminDto.orgName}</option>
                            <c:forEach var="org" items="${workBench.orgInfos}">
                                 <option value="${org.id}">${org.orgName}</option>
                             </c:forEach>

                    </select>
                </div>
                <div class="table-responsive row">
                    <div class="col-md-5" style="padding-top: 30px;">
                        <img src="${ctx}/img/sale.png">
                    </div>
                    <div class="col-md-7 activityDayReport" style="padding-top: 30px;">
                        <ul class="list">
                            <li ><a style="display: inline-block;width: 10px;height: 10px;border-radius: 10px;background: #4FC2F8;"></a><span style="color: #4FC2F8">拜访客户</span>
                                <span style="color: #000000">${workBench.activityDayReport.visitNum}单</span><span style="color: #000000"></span></li>
                            <li style="color: #73BA78"><a  style="display: inline-block;width: 10px;height: 10px;border-radius: 10px;background: #73BA78"></a><span>目标客户</span>
                                <span style="color: #000000">${workBench.activityDayReport.targetNum}单</span><span style="color: #000000"><fmt:formatNumber type="number" value="${workBench.activityDayReport.targetNum
                             / workBench.activityDayReport.visitNum * 100}" pattern="0.00" maxFractionDigits="2"/>%</span></li>
                            <li style="color: #ffd54f"><a  style="display: inline-block;width: 10px;height: 10px;border-radius: 10px;background: #ffd54f"></a><span>意向客户</span>
                                <span style="color: #000000">${workBench.activityDayReport.intentionNum}单</span><span style="color: #000000"><fmt:formatNumber type="number" value="${workBench.activityDayReport.intentionNum
                             / workBench.activityDayReport.targetNum * 100}" pattern="0.00" maxFractionDigits="2"/>%</span></li>
                            <li style="color: #f16156"><a  style="display: inline-block;width: 10px;height: 10px;border-radius: 10px;background: #f16156"></a><span>签约客户</span>
                                <span style="color: #000000">${workBench.activityDayReport.signNum}单</span><span style="color: #000000"><fmt:formatNumber type="number" value="${workBench.activityDayReport.signNum
                             / workBench.activityDayReport.intentionNum * 100}" pattern="0.00" maxFractionDigits="2"/>%</span></li>
                            <li style="color: #eb5180"><a  style="display: inline-block;width: 10px;height: 10px;border-radius: 10px;background: #eb5180"></a><span>结案客户</span>
                                <span style="color: #000000">${workBench.activityDayReport.closedNum}单</span><span style="color: #000000"><fmt:formatNumber type="number" value="${workBench.activityDayReport.closedNum
                             / workBench.activityDayReport.signNum * 100}" pattern="0.00" maxFractionDigits="2"/>%</span></li>
                        </ul>
                    </div>
                   <%-- <table class="table table-hover activityDayReport">
                        <thead>
                        <tr>
                            <td width="150">拜访客户</td>
                            <td width="150">${workBench.activityDayReport.visitNum}单</td>
                            &lt;%&ndash;<th width="150">100%</th>&ndash;%&gt;
                        </tr>
                        <tr>
                            <td width="150">目标客户</td>
                            <td width="150">${workBench.activityDayReport.targetNum}单</td>
                            <td width="150"> <fmt:formatNumber type="number" value="${workBench.activityDayReport.targetNum
                             / workBench.activityDayReport.visitNum * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
                        </tr>
                        <tr>
                            <td width="150">意向客户</td>
                            <td width="150">${workBench.activityDayReport.intentionNum}单</td>
                            <td width="150"> <fmt:formatNumber type="number" value="${workBench.activityDayReport.intentionNum
                             / workBench.activityDayReport.targetNum * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
                        </tr>
                        <tr>
                            <td width="150">签约客户</td>
                            <td width="150">${workBench.activityDayReport.signNum}单</td>
                            <td width="150"> <fmt:formatNumber type="number" value="${workBench.activityDayReport.signNum
                             / workBench.activityDayReport.intentionNum * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
                        </tr>
                        <tr>
                            <td width="150">结案客户</td>
                            <td width="150">${workBench.activityDayReport.closedNum}单</td>
                            <td width="150"> <fmt:formatNumber type="number" value="${workBench.activityDayReport.closedNum
                             / workBench.activityDayReport.signNum * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
                        </tr>
                        </thead>
                        <tbody class="class-list">
                        </tbody>
                    </table>--%>
                </div>
            </div>
      </div>

<%--      <div id="miss"><embed height="0" width="0" src="http://openapi.shlefan.com/pic/mp3/8855.mp3"></div>--%>
</body>
<script>
    window.onload=function(){

        bx();
        zz();
    }

    function bx(){
        var saleAmount = $('.saleAmount').text();
        var saleGold =   $('.saleGold').text();

        var charts = echarts.init(document.getElementById('mycharts'))
        charts.setOption({
            series: [
                {
                    type: 'pie',
                    center: ['30%', '53%'],
                    radius: ['75%', '60%'],
                    label: {
                        normal: {
                            position: 'center'
                        }
                    },
                    data: [{
                        value: saleAmount,
                        name: '已完成',
                        itemStyle: {
                            normal: {
                                color: '#95ceff'
                            }
                        },
                        label: {
                            normal: {
                                formatter: '{d} %',
                                textStyle: {
                                    color: 'black',
                                    fontSize: 20

                                }
                            }
                        }
                    }, {
                        value: saleGold,
                        name: '目标',
                        tooltip: {
                            show: false
                        },
                        itemStyle: {
                            normal: {
                                color: '#f9f9f9'
                            }
                        },
                        label: {
                            normal: {
                                textStyle: {
                                    color: 'black'
                                },
                                formatter: '\n已完成'
                            }
                        }
                    }]
                }
            ]
        });
    }


    function zz(){
        var rankData = $(".td1");
        console.log(rankData)
        var dataAxis = [];
        var data = [];
        rankData.each(function(){
            dataAxis.push($(this).text())
        })
        var rankRate =  $(".td2");
        rankRate.each(function(){
            data.push($(this).text())
        })
        var yMax = data[0];
        var dataShadow = [];

        for (var i = 0; i < data.length; i++) {
            dataShadow.push(yMax);
        }

        var charts = echarts.init(document.getElementById('zz'))
        charts.setOption({
            title: {
                text: '',
                subtext: '当前排名是根据个人完成率排序（%）'
            },
            xAxis: {
                data: dataAxis,
                axisLabel: {
                    inside: true,
                    textStyle: {
                        color: '#333333'
                    }
                },
                axisTick: {
                    show: false
                },
                axisLine: {
                    show: false
                },
                z: 10
            },
            yAxis: {
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        color: '#999'
                    }
                }
            },
            dataZoom: [
                {
                    type: 'inside'
                }
            ],
            series: [
                { // For shadow
                    type: 'bar',
                    itemStyle: {
                        normal: {color: 'rgba(0,0,0,0.05)'}
                    },
                    barGap:'-100%',
                    barCategoryGap:'40%',
                    data: dataShadow,
                    animation: false
                },
                {
                    type: 'bar',
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(
                                    0, 0, 0, 1,
                                    [
                                        {offset: 0, color: '#83bff6'},
                                        {offset: 0.5, color: '#188df0'},
                                        {offset: 1, color: '#188df0'}
                                    ]
                            )
                        },
                        emphasis: {
                            color: new echarts.graphic.LinearGradient(
                                    0, 0, 0, 1,
                                    [
                                        {offset: 0, color: '#2378f7'},
                                        {offset: 0.7, color: '#2378f7'},
                                        {offset: 1, color: '#83bff6'}
                                    ]
                            )
                        }
                    },
                    data: data
                }
            ]
        });


// Enable data zoom when user click bar.
        var zoomSize = 6;
        charts.on('click', function (params) {
            console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
            charts.dispatchAction({
                type: 'dataZoom',
                startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
                endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
            });
        });
    }

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
