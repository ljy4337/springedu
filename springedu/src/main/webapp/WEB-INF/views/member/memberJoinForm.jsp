<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/main_header.jsp" flush="false" />

<style>
.errmsg, pwErr {
	color: #f00;
}
</style>

<script>
	$(function() {

		$("#idcheck").on("click", function(e) {
			var id = $("#id").val();
			e.preventDefault();
			console.log("idcheckbtn");
			if ($("#id").val().length == 0) {
				$("#checkmsg").text("아이디를 입력하세요.");
			} else {
				console.log("else");
				console.log(id);
				//AJAX 비동기 처리기술
				$.ajax({
					type : "PUT", //post, get, put, delete
					url : "/member/idcheck/"+id,
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "PUT"
					},
					dataType : "text",
					data : JSON.stringify({
						id : id

					}),

					success : function(result) {
						console.log(result);
						if (result) {
							$("#checkmsg").text("중복된 아이디가 존재합니다.");
						} else {
							$("#checkmsg").text("사용가능한 아이디 입니다.");
						}

					},
					error : function(xhr, status, err) {
						console.log("xhr : " + xhr);
						console.log("status : " + status);
						console.log("err : " + err);
					}

				});
			}

		});

		//유효성체크 오류시 에러표시 css적용 
		$(".errmsg").each(function(idx) {
			if ($(this).text().length > 0) {
				$(this).prev().removeClass("is-valid").addClass("is-invalid");
			}
		});

		//비밀번호 확인
		$("#pw,#pwchk").on("keyup", function() {

			if ($("#pw").val() != $("#pwchk").val()) {
				console.log($(this).val());
				$(".pwErr").text('비밀번호가 틀립니다!');
				$(this).focus();
			} else {
				$(".pwErr").text('');
			}
		})

		// 가입버튼
		$("#joinBtn").on("click", function(e) {
			e.preventDefault();
			$("form").submit();
		});

		// 가입초기화버튼
		$("#joinClearBtn").on("click", function(e) {
			e.preventDefault();
			$("form").each(function() {
				this.reset();
			});
		});

		// 가입취소버튼
		$("#joinCancelBtn").on("click", function(e) {
			e.preventDefault();
			location.href = "/";
		});

		// 회원목록버튼
		$("#listBtn").on("click", function(e) {
			e.preventDefault();
			location.href = "/member/memberList";
		});

	});
</script>
<div class="container">
	<div class="row justify-content-center">
		<div class="col-4">
			<form:form modelAttribute="mdto"
				action="${pageContext.request.contextPath }/member/memberJoin">
				<div class="form-group">
						<form:label path="id" class="col-form-label">아이디</form:label>
		
							<form:input type="text" path="id"
								class="col-sm form-control form-control-sm is-valid"
								placeholder="아이디를 입력하세요!" required="required" />
							<form:errors path="id" cssClass="col-sm-2 errmsg"></form:errors>
							<span id="checkmsg"></span>

				</div>
				<div class="form-group ">
						<button class="btn btn-primary" id="idcheck">중복검사</button>
	
				</div>
				<div class="form-group ">
					<form:label path="pw" class="col-form-label">비밀번호</form:label>
					<form:input type="password" path="pw"
						class="col-sm form-control form-control-sm is-valid"
						placeholder="비밀번호를 입력하세요!" required="required" />
					<form:errors path="pw" cssClass="col-sm-2 errmsg"></form:errors>
				</div>
				<div class="form-group">
					<label for="pwchk" class="col-form-label">비밀번호확인</label> <input
						type="password" id="pwchk"
						class="col-sm form-control form-control-sm is-valid"
						placeholder="비밀번호를 입력하세요!" required /> <span
						class="col-sm-2 pwErr"></span>
				</div>
				<div class="form-group">
					<form:label path="tel" class="col-form-label">전화번호</form:label>
					<form:input type="tel" path="tel"
						class="col-sm form-control form-control-sm is-valid"
						placeholder="전화번호를 입력하세요 ex)010-1234-5678" required="true" />
					<form:errors path="tel" cssClass="col-sm-2 errmsg"></form:errors>
				</div>
				<div class="form-group">
					<form:label path="nickName" class="col-form-label">닉네임</form:label>
					<form:input type="text" path="nickName"
						class="col-sm form-control form-control-sm is-valid"
						placeholder="별칭을 입력하세요!" required="true" />
					<form:errors path="nickName" cssClass="col-sm-2 errmsg"></form:errors>
				</div>
				<div class="form-group">
					<form:label path="gender" class="col-form-label">성 별</form:label>
					<form:select path="gender"
						class="col-sm form-control form-control-sm is-valid">
						<option value="">--성별--</option>
						<form:options items="${gender }" path="gender" itemLabel="label"
							itemValue="code" />
					</form:select>
				</div>

				<div class="form-group">

					<form:label path="region" class="col-form-label">지역</form:label>
					<form:select path="region"
						class="col-sm form-control form-control-sm is-valid">
						<option value="">--지역선택--</option>
						<form:options path="region" items="${rCodes }" itemLabel="label"
							itemValue="code" />
					</form:select>
					<!--        <div class="valid-feedback">
        Looks good!
      </div> -->
				</div>
				<div class="form-group">
					<form:label path="birth" class="col-form-label">생년월일</form:label>
					<form:input type="date" path="birth"
						class="col-sm form-control form-control-sm is-valid"
						placeholder="생년월일을 입력하세요!" required="true" />
					<form:errors path="birth" cssClass=" col-sm-2 errmsg"></form:errors>
				</div>
				<div class="form-group">
					<div class="row justify-content-center">
						<button class="btn btn-primary" type="submit" id="joinBtn">가입</button>
						<!-- <button class="btn btn-primary" type="submit" id="joinClearBtn">초기화</button>
						<button class="btn btn-primary" type="submit" id="joinCancelBtn">취소</button>
						<button class="btn btn-primary" type="submit" id="listBtn">회원목록</button> -->
						<div></div>
					</div>
				</div>
			</form:form>
		</div>

	</div>
</div>
<jsp:include page="/main_footer.jsp" flush="false" />

