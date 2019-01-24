<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="pg" uri="/WEB-INF/tlds/page"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="/css/base.css" rel="stylesheet" type="text/css" />
<link href="/css/page_style.css" rel="stylesheet" type="text/css" />
<link href="/css/global_style.css" rel="stylesheet" type="text/css" />
<link href="/css/default.css" rel="stylesheet" type="text/css" />
<link href="/css/add_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/js/jquery.serializeObject.js"></script>
<script type="text/javascript" src="/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="/bootstrap/bootstrap-table/bootstrap-table.js"></script>
<link type="text/css" rel="stylesheet"
	href="/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet"
	href="/bootstrap/bootstrap-table/bootstrap-table.css" />

<script type="text/javascript">
	//페이지 이동
	function goPage(page) {
		$("#page").val(page);
		$('#searchForm').attr('action', "/index.do");
		$("#searchForm").submit();
	}

	function insert() {

		if ($("#insertForm #todo").val() == '') {
			alert("할일을 입력해주세요.");
			return;
		}

		$.ajax({
			type : "post",
			url : "/api/v1/insert.do",
			data : JSON.stringify($("#insertForm").serializeObject()),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if(data.errorCode != 200){
					alert(data.errorMessage);
				}
				else{
					goPage($("#page").val());
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
			}
		});
	}
		
	function update(id) {
			
			 $("#updatehForm #id").val(id);
			 $("#updatehForm #todo").val($("#todo_" + id).val());
			 
			
			if ($("#updatehForm #todo").val() == '') {
				alert("할일을 입력해주세요.");
				return;
			}

			$.ajax({
				type : "put",
				url : "/api/v1/update.do",
				data : JSON.stringify($("#updatehForm").serializeObject()),
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					if(data.errorCode != 200){
						alert(data.errorMessage);
					}
					else{
						goPage($("#page").val());
					}
				},
				error : function(e) {
					console.log("ERROR: ", e);
				}
			});
	}
	
	function complete(id) {
		$("#updatehForm #id").val(id);

		$.ajax({
			type : "put",
			url : "/api/v1/complete.do",
			data : JSON.stringify($("#updatehForm").serializeObject()),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if(data.errorCode != 200){
					alert(data.errorMessage);
				}
				else{
					goPage($("#page").val());
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
			}
		});
}
</script>
</head>
<body>

	<form id="searchForm" name="searchForm">
		<input id="page" name="page" type="hidden" value="0" />
	</form>
	
	<form id="updatehForm" name="updatehForm">
		<input id="id" name="id" type="hidden" value="" />
		<input id="todo" name="todo" type="hidden" value="" />
	</form>

	<div id="contents">

		<div class="blockWrapper">
			<div class="pageTitle">
				<h2>카카오페이 테스트</h2>
				<ul class="location">
					<li>카카오페이 &gt;</li>
					<li><strong>todoList</strong></li>
				</ul>
			</div>

			<div class="blockDetail mt15 pt0">
				<form id="insertForm" name="insertForm">
					<table class="th_fs10">
						<colgroup>
							<col width="25%" />
							<col width="75%" />
						</colgroup>
						<tbody>
							<tr>
								<th class="aLeft pl50">* 할일</th>
								<td><input class="inputbox submit" type="text" id="todo"
									name="todo" placeholder="참조 ID는 '할일@참조ID'형식으로 입력해주세요."
									value="" style="width: 650px;" /></td>
							</tr>
						</tbody>
					</table>
				</form>
				<div class="blockRightBtn">
					<a href="javascript:insert()" class="button blueB">저장</a>
				</div>
			</div>

			<div class="blockDetail mt15 pt0">
				<table class="th_fs10">
					<colgroup>
						<col width="6%" />
						<col width="*" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
					</colgroup>
					<thead>
						<tr>
							<th>ID</th>
							<th>할일</th>
							<th>작성일시</th>
							<th>최종수정일시</th>
							<th>완료일시</th>
							<th>완료/수정</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${fn:length(result.content) == 0 }">
							<tr>
								<td colspan="7">등록된 공지 사항이 없습니다.</td>
							</tr>
						</c:if>
						<c:forEach var="list" items="${result.content}" varStatus="status">
							<tr>
								<td>${list.id }</td>
								<td>
									<c:if test="${list.comYn eq 'Y' }">${list.todo }</c:if>
									<c:if test="${list.comYn eq 'N' }">
									<input class="inputbox submit" style="width: 350px;"
									type="text" id="todo_${list.id }" name="todo_${list.id }"
									value="${list.todo }" />
									</c:if>
								</td>
								<td><c:if test="${list.regDate  eq null }">-</c:if>${list.regDate }</td>
								<td><c:if test="${list.modDate  eq null }">-</c:if>${list.modDate }</td>
								<td><c:if test="${list.compDate eq null }">-</c:if>${list.compDate }</td>
								<td>
								<c:if test="${list.comYn eq 'Y' }">-</c:if>
								<c:if test="${list.comYn eq 'N' }">
								<a href="javascript:complete('${list.id }')" class="button whiteB"
									style="padding: 5px 0px; width: 45px;">완료</a>
								<a href="javascript:update('${list.id }')" class="button whiteB"
									style="padding: 5px 0px; width: 45px;">수정</a>
								</c:if>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>

				<!--Page Numbur Start-->
				<c:if test="${fn:length(result.content) gt 0 }">
					<div id="list12" class="pageNum mt30">
						<ul>
							<pg:page link="goPage" pg="${result.number}"
								pageSize="${result.totalPages}"
								pageGroupSize="${result.totalPages}" total="${result.size}"
								cssClassName="pageNum" beginLabel="class='btn_page_first'"
								endLabel="class='btn_page_last'"
								prevLabel="class='btn_page_pre'"
								nextLabel="class='btn_page_next'" />
						</ul>
					</div>
				</c:if>
				<!--//Page Numbur End-->
			</div>
			<!-- //blockBtn -->
		</div>
		<!--// content -->
	</div>

</body>
</html>
