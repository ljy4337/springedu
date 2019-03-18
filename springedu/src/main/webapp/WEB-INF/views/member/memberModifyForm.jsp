<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<jsp:include page="/main_header.jsp" flush="false"/>
<script>
	$(function(){
					
		$("#pw,#pwchk").on("keyup",function(){
			if($("#pw").val() != $("#pwchk").val()) {
				console.log($(this).val());
				$(".pwErr").text('비밀번호가 틀립니다!');
				$(this).focus();
			}else{
				$(".pwErr").text('');
			}
		});
		//유효성체크 오류시 에러표시 css적용 
		$(".errmsg").each(function(idx) {
			if ($(this).text().length > 0) {
				$(this).prev().removeClass("is-valid").addClass("is-invalid");
			}
		});

		
		// 수정버튼
		$("#modifyBtn").on("click",function(e){
			e.preventDefault();	
			$("form").submit();
		});
				
		// 수정취소버튼
		$("#modifyCancelBtn").on("click",function(e){
			e.preventDefault();	
			location.href="/member/memberList";
		});
		
	});
</script>

<%-- 	<form:form modelAttribute="mdto" action="memberJoin"> --%>
	<div class="container">
	<div class="row justify-content-center">
		<div class="col-4">
			<form:form modelAttribute="mdto"
				action="${pageContext.request.contextPath }/admin/adminModify">
				<div class="form-group">
						<form:label path="id" class="col-form-label">아이디</form:label>
		
							<form:input type="text" path="id"
								class="col-sm form-control form-control-sm is-valid"
								placeholder="아이디를 입력하세요!" readonly="true" required="required" />
							<form:errors path="id" cssClass="col-sm-2 errmsg"></form:errors>

				</div>
		
				<div class="form-group ">
					<form:label path="pw" class="col-form-label" >비밀번호</form:label>
					<form:input type="password" path="pw"
						class="col-sm form-control form-control-sm is-valid"
						placeholder="비밀번호를 입력하세요!"  readonly="true" required="required" />
					<form:errors path="pw" cssClass="col-sm-2 errmsg"></form:errors>
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
						<button class="btn btn-primary" type="submit" id="modifyBtn">수정완료</button>
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
<jsp:include page="/main_footer.jsp" flush="false"/>
