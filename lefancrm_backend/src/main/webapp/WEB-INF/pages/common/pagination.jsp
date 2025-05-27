<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<style>
	.pagePosi-content{
		position: relative;
		min-height: 39px;
	}
	.pageSizeContent{
		position: absolute;
		left: 20px;
		bottom: 0;
		padding-bottom: 10px;
		width: 210px;
		display: flex;
		align-items: center;
		font-size: 12px;
		background-color: #fff;
	}
	.pageSizeBars{
		margin-left: 10px;
		width: 120px;
		border-left: 1px solid #eee;
		display: flex;
	}
	.pageSizeBars .pageSizeBar{
		width:40px;
		height: 30px;
		line-height: 30px;
		border: 1px solid #eee;
		border-left: none;
		cursor: pointer;
	}

	.pageSizeBars .pageSizeBar.active{
		background-color: #428bca;
		color: #fff;
	}
	@media screen and (max-width:1110px){
		.pagination{
			margin-left: 210px!important;
		}
	}


</style>

<c:set var="requestUrl" value="${param.requestUrl}"/>
<c:set var="paginationObjectName" value="${(not empty param.paginationObjectName)?(param.paginationObjectName):'pagination'}"/>
<c:set var="pageNoName" value="${(not empty param.pageNoName)?(param.pageNoName):'page'}"/>
<c:set var="pagination" value="${requestScope[paginationObjectName]}"/>
<c:set var="totalPages" value="${pagination.totalPages}"/>
<c:set var="curPage" value="${pagination.curPage}"/>
<c:set var="refreshDiv" value="${param.refreshDiv}"/>
<c:choose>
	<c:when test="${!fn:contains(requestUrl,'?') && !fn:contains(requestUrl,'&')}">
		<c:set var="requestUrl" value="${requestUrl}?${pageNoName}"/>
	</c:when>
	<c:otherwise>
		<c:set var="requestUrl" value="${requestUrl}&${pageNoName}"/>
	</c:otherwise>
</c:choose>
<div class="pagePosi">
	<div class="pagePosi-content">
		<div class="pageSizeContent">
			<div>每页显示数量</div>
			<div class="pageSizeBars">
				<div  class="pageSizeBar" id="pageSize10" onclick="setAcive(10)">10</div>
				<div  class="pageSizeBar" id="pageSize20" onclick="setAcive(20)">20</div>
				<div  class="pageSizeBar" id="pageSize50" onclick="setAcive(50)">50</div>

			</div>
		</div>
		<c:if test="${totalPages > 1}">
			<!-- <div class="pagi"> -->
			<ul class="pagination" style="margin: 0px;">
				<c:choose>
					<c:when test="${curPage==1}">
						<li class="disabled"><a href="javascript:;">«</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:gotoPage1('${requestUrl}=${curPage-1}','${refreshDiv}')" >« <span class="sr-only">(current)</span></a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${curPage <= 10 }">
						<c:forEach var="n" begin="1" end="${totalPages>10?10:totalPages}" step="1">
							<c:choose>
								<c:when test="${n==curPage}">
									<li class="active"><a href="javascript:;">${n}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="javascript:gotoPage1('${requestUrl}=${n}','${refreshDiv}')" >${n}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:when>
					<c:when test="${curPage+5 <= totalPages}">
						<c:forEach var="n" begin="${curPage-4}" end="${curPage+5}" step="1">
							<c:choose>
								<c:when test="${n==curPage}">
									<li class="active"><a href="javascript:;">${n}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="javascript:gotoPage1('${requestUrl}=${n}','${refreshDiv}')" >${n}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="n" begin="${curPage-4}" end="${totalPages}" step="1">
							<c:choose>
								<c:when test="${n==curPage && n != totalPages}">
									<li class="active"><a href="javascript:;">${n}</a></li>
								</c:when>
								<c:otherwise>
									<c:if test="${n != totalPages}">
										<li><a href="javascript:gotoPage1('${requestUrl}=${n}','${refreshDiv}')" >${n}</a></li>
									</c:if>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<li><span>...</span></li>
				<c:choose>
					<c:when test="${totalPages > 10}">
						<li><a href="javascript:gotoPage1('${requestUrl}=${totalPages}','${refreshDiv}')" >${totalPages}</a></li>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${curPage==totalPages}">
						<li class="disabled"><a href="javascript:;" >»</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:gotoPage1('${requestUrl}=${curPage+1}','${refreshDiv}')" >»</a></li>
					</c:otherwise>
				</c:choose>
				<li>
					<div class="input-group" style="width:120px; float:left; margin-left:10px">
						<input id="jumptoPageNo" name="jumptoPageNo" value="${curPage}" type="number" class="form-control">
						<span class="input-group-btn">
					<a href="javascript:jumpTo('${requestUrl}=','${refreshDiv}');" class="btn btn-default" role="button">Go!</a>
				  </span>
					</div><!-- /input-group -->
				</li>
			</ul>
		</c:if>
	</div>
</div>
<script>

	var pageSize = localStorage.getItem("pageSize") || 20;
	var pageSizeName = 'pageSize'+pageSize
	document.getElementById(pageSizeName).className +=' active'
	// if($("#pageSize")){
	// 	$("#pageSize").val(pageSize);
	// }
	if(document.getElementById('pageSize')){
		document.getElementById('pageSize').value = pageSize
	}

	function gotoPage1(url,divId){
		var num = localStorage.getItem("pageSize") || 20;
		if(url.indexOf("?") > -1){
			url += "&pageSize=" + num;
		}else{
			url += "?pageSize=" + num;
		}
		if(divId!=null && divId!=""){
			$.ajax({
				url:url,
				dataType:"html",
				success:function(event,param){
					$("#"+divId).html(param.data);
				}
			});
		}else{
			window.location.href=url;
		}
	}
	// $('.pageSizeBars .pageSizeBar').click(function () {
	// 	var  _this  = $(this)
	// 	if (!_this.hasClass('active')){
	// 		_this.addClass('active')
	// 		_this.siblings().removeClass('active')
	// 		localStorage.setItem("pageSize", _this.text());
	// 	}
	// })
	//
	//
	// var pageSize = localStorage.getItem("pageSize");
	// $('.pageSizeBars .pageSizeBar[data-num="'+pageSize+'"]').addClass('active').siblings().removeClass('active')

	function  setAcive(num) {
		if (num == 10){
			document.getElementById("pageSize10").className += ' active';
			document.getElementById("pageSize20").setAttribute('class', 'pageSizeBar')
			document.getElementById("pageSize50").setAttribute('class', 'pageSizeBar')
		}else if (num == 20){
			document.getElementById("pageSize10").setAttribute('class', 'pageSizeBar')
			document.getElementById("pageSize20").className += ' active';
			document.getElementById("pageSize50").setAttribute('class', 'pageSizeBar')
		}else if (num == 50){
			document.getElementById("pageSize10").setAttribute('class', 'pageSizeBar')
			document.getElementById("pageSize20").setAttribute('class', 'pageSizeBar')
			document.getElementById("pageSize50").className += ' active';
		}
		localStorage.setItem("pageSize", num);
		gotoPage1('${requestUrl}=${n}','${refreshDiv}');

		if($("#pageSize")){
			$("#pageSize").val(num);
		}

	}



	function jumpTo(pagingUrl,refreshDiv){
	var jumptoPageNo=parseInt(document.getElementById("jumptoPageNo").value);
	pagingUrl+=jumptoPageNo;
	return gotoPage(pagingUrl,refreshDiv);
}
</script>