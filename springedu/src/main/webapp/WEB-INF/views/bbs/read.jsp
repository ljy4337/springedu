<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/main_header.jsp" flush="false" />

<script>
	$(function() {
		
		// 글자수 제한-------------------------------
		var $bcontent = $("#bcontent");
		$("#bcontent").on("keyup", function() {
			var bcontent = $("#bcontent").val();
			var cnt = bcontent.trim().length;

			$("#count1").html(cnt + '/300')
			if (cnt > 300) {
				$("#bcontent").addClass("is-invalid");
				bcontent = bcontent.substring(bcontent.length - 300);
				$bcontent.val(bcontent);

				$("#count1").html(cnt + '/300')
				return false;
			}
			$("#bcontent").removeClass("is-invalid");
		});

		$("#umode").css({
			"display" : "none"
		});
		$("#rmode").css({
			"display" : ""
		});

		//유효성체크 오류시 에러표시 css적용 
		$("span[id$='.errors']").each(
				function(idx) {
					if ($(this).text().length > 0) {
						$(this).prev().removeClass("is-valid").addClass(
								"is-invalid");
						$(this).removeClass("valid-feedback").addClass(
								"invalid-feedback");
					}
				});
		// 답글
		$("#btn1")
				.on(
						"click",
						function(e) {
							//e.preventDefault();
							if ($("#sessionId").val().length == 0) {
								if (confirm("로그인이 필요합니다. 로그인 하시겠습니까?")) {
									location.href = "${pageContext.request.contextPath }/login/loginForm";
								} else {

								}
							} else {
								location.href = "/bbs/replyForm/${bbsDTO.bnum}/${rc.reqPage}";
							}

						});

		// 수정
		$("#btn2").on("click", function(e) {
			//e.preventDefault();
			$("#rmode").css({
				"display" : "none"
			});
			$("#umode").css({
				"display" : ""
			});
			$("#btitle").removeAttr("readonly");
			$("#bcontent").removeAttr("readonly");
			$("#title").text("게시글수정");

		});

		// 삭제
		$("#btn3")
				.on("click",function(e) {
					//e.preventDefault();
					location.href = "/bbs/delete?bnum=${bbsDTO.bnum}&reqPage=${rc.reqPage}";

		});

		// 완료
		$("#btn5").on("click", function(e) {
			//e.preventDefault();
			/* $("#rmode").css({"display":""});
			$("#umode").css({"display":"none"});
			$("#btitle").addAttr("readonly");
			$("#bcontent").addAttr("readonly"); */
			$("form").submit();
		});

		// 취소
		$("#btn6")
				.on(
						"click",
						function(e) {
							//e.preventDefault();
							location.href = "/bbs/view?bnum=${bbsDTO.bnum}&reqPage=${rc.reqPage}";

						});

		// 목록
		$("#btn4, #btn7").on("click", function(e) {
			//e.preventDefault();
			location.href = "/bbs/list?reqPage=${rc.reqPage}";
		});
	});

	//유효성체크 함수
	function valchk() {
		if ($("#btitle").val().length == 0) {
			alert('제목을 입력하세요.');
			$("#btitle").focus();
			return false;
		}
		if ($("#btitle").val().length > 30) {
			alert('제목이 너무 긴데요?????(30자 이하로 입력해주세요.)');
			$("#btitle").focus();
			return false;
		}

		if ($("#bcontent").val().length == 0) {
			alert('내용을 입력하세요.');
			$("#bcontent").focus();
			return false;
		}
		if ($("#bcontent").val().length > 500) {
			alert('내용도 좀 많군요.......(500자 이하로 입력해주세요.)');
			$("#bcontent").focus();
			return false;
		}

		return true;

	}
</script>

<div class="container">
	<div class="table-responsive">
		<h3 id="title" class="text-center p-3 mb-3 "">게시글읽기</h3>
		<form:form modelAttribute="bbsDTO" action="/bbs/modifyok">
			<input type="hidden" id="sessionId" value="${user.id }"></input>
			<input type="hidden" id="sessionId" value="${user.nickName }"></input>
			<input type="hidden" name="bnum" value=${bbsDTO.bnum } />
			<input type="hidden" name="reqPage" value="${rc.reqPage}" />
			<table class="table  table-sm" summary="글쓰기">

				<colgroup>
					<col width="20%">
					<col width="">
				</colgroup>

				<tbody>
					<tr>
						<th>제목</th>
						<td><form:input class="form-control" path="btitle"
								type="text" readonly="true" value="${bbsDTO.btitle }" /> <form:errors
								class="valid-feedback" path="btitle"></form:errors></td>
					</tr>
					<tr>
						<th>작성자</th>

						<td>${bbsDTO.bnickname }(${bbsDTO.bid })</td>
					</tr>
					<tr>
						<th>내용</th>
						<td><form:textarea class="form-control" path="bcontent"
								rows="15" readonly="true" value="${bbsDTO.bcontent }"></form:textarea>
							<form:errors class="valid-feedback" path="bcontent"></form:errors>
							<p class="text-right" id="count1">(0/300)</p>
						</td>
					</tr>

				</tbody>

				<div class="btngrp">
					<tr>
						<td colspan="2" align="right">
							<div class="btbgroup" id="rmode">
								<button type="button" id="btn1" class="btn btn-outline-primary">답글</button>
								<c:if test="${bbsDTO.bid eq sessionScope.user.id }">
									<button type="button" id="btn2" class="btn btn-outline-primary">수정</button>
									<button type="button" id="btn3" class="btn btn-outline-primary">삭제</button>
								</c:if>
								<button type="button" id="btn4" class="btn btn-outline-primary">목록</button>
							</div>
							<div class="btbgroup" id="umode">
								<button type="button" id="btn5" class="btn btn-outline-primary">완료</button>
								<button type="button" id="btn6" class="btn btn-outline-primary">취소</button>
								<button type="button" id="btn7" class="btn btn-outline-primary">목록</button>
							</div>
						</td>
					</tr>



				</div>



			</table>
		</form:form>
	</div>
</div>


<jsp:include page="../bbs/reReply.jsp" flush="false" />
<jsp:include page="/main_footer.jsp" flush="false" />
