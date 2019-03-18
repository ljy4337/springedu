<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<jsp:include page="/main_header.jsp" flush="false" />

<script>
	$(function() {
		$("#modifyinfo").on("click", function() {
			console.log("1");
			location.href = "/member/memberModifyForm/${sessionScope.user.id}";
		});
		$("#modifypw").on("click", function() {
			console.log("1");
			location.href = "/member/memberModifyPwForm/${sessionScope.user.id}";
		});
		$("#exit").on("click", function() {
			console.log("1");
			location.href = "/member/memberDel/${sessionScope.user.id}";
			
			
		});
	});
</script>

<div class="container">
	<div class="card-deck mb-3 text-center">
		<div class="card mb-4 shadow-sm">
			<div class="card-header">
				<h4 class="my-0 font-weight-normal">내정보수정</h4>
			</div>
			<div class="card-body">

				<ul class="list-unstyled mt-3 mb-4">
					<li>등록된 회원정보를 수정합니다.</li>
					<li>이름, 주민등록번호, 전화번호, 주소 만 수정 가능합니다.</li>
					<li>그 외 정보 수정은 관리자에게 문의해주십시오.</li>
					<li>비밀번호 변경 비밀번호 변경 페이지에서 가능합니다.</li>
				</ul>
				<button type="button"
					class="btn btn-lg btn-block btn-outline-primary" id="modifyinfo">수정페이지로
					이동</button>
			</div>
		</div>
		<div class="card mb-4 shadow-sm">
			<div class="card-header">
				<h4 class="my-0 font-weight-normal">비밀번호변경</h4>
			</div>
			<div class="card-body">

				<ul class="list-unstyled mt-3 mb-4">
					<li>비밀번호를 변경 합니다.</li>
					<li>현재 비밀번호를 모르실 경우</li>
					<li>비밀번호 찾기를 통해 비밀번호를 찾으시거나</li>
					<li>관리자에게 문의주십시오.</li>
				</ul>
				<button type="button"
					class="btn btn-lg btn-block btn-outline-primary" id="modifypw">비밀번호
					변경</button>
			</div>
		</div>
		<div class="card mb-4 shadow-sm">
			<div class="card-header">
				<h4 class="my-0 font-weight-normal">회원탈퇴</h4>
			</div>
			<div class="card-body">

				<ul class="list-unstyled mt-3 mb-4">
					<li>회원탈퇴를 합니다.</li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
				<button type="button"
					class="btn btn-lg btn-block btn-outline-primary" 
					data-toggle="modal" data-target="#exitmodal">회원탈퇴하기</button>
			</div>
		</div>
	</div>
</div>
<!-- 탈퇴 modal -->
<div class="modal fade" id="exitmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">회원 탈퇴</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      	<p>탈퇴를 하실 경우 회원 정보가 모두 삭제됩니다.</p>
        <p>탈퇴를 진행하시겠습니까?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary" id="exit">탈퇴</button>
      </div>
    </div>
  </div>
</div>

<jsp:include page="/main_footer.jsp" flush="false" />
