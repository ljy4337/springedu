<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<jsp:include page="/main_header.jsp" flush="false" />

<style>
/* .bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
} */
	#err {color : red;}
	#errMsg {color : red;}
	#idid{color : green;}
	
	#findmenu li>a{
		display : inline-block;
	}
	
</style>


<script>
$(function(){

	$("#id").on("keyup",function(){
/* 	var regex=/^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{1,5}$/;
 	이메일 정규식 표현  */
 	var id_pattern = /^\w+@\w+\.\w+(\.\w+)?$/;
	v = $("#id").val()		
		if( !id_pattern.test(v) ) {
			console.log($(this).val());
			$("#idid").text('');
			$("#err").text('이메일주소를 입력해 주세요.');
			$(this).focus();
		}else{
			$("#err").text('');
			$("#idid").text('이메일 형식이 일치합니다.');
		}
	}) 

	//비밀번호 확인
			$("#pw").on("keyup",function(){
				
			 	/* if($("#pw").val() != $("#pwchk").val()) {
					console.log($(this).val());
					$(".pwErr").text('비밀번호가 틀립니다!');
					$(this).focus();
				}else{
					$(".pwErr").text('');
				}  */
			})
})

</script>
<!-- Custom styles for this template -->
<!-- <link href="/resources/css/floating-labels.css" rel="stylesheet"> -->

<main role="main" class="flex-shrink-0">

<div class="container">
	<div class="row justify-content-center">
	
		<div class="col-4">
			<form:form class="form-signin" modelAttribute="login" action="/login/loginOk">
				<div class="text-center mb-4">
					<!-- <img class="mb-4" src="/resources/images/login.jpg" alt=""
						width="150" height="150"> -->
					<h1 class="h3 mb-3 font-weight-normal">Log in</h1>
					<!-- <p>Build form controls with floating labels via the <code>:placeholder-shown</code> pseudo-element. <a href="https://caniuse.com/#feat=css-placeholder-shown">Works in latest Chrome, Safari, and Firefox.</a></p> -->
				</div>

				<div class="form-label-group">
					<form:input type="text" path="id" class="form-control" placeholder="Email address" required="required" autofocus="autofocus"/>
						<form:errors path="id" cssClass="errmsg"></form:errors>
						<span id="err"></span>
						<span id="idid"></span>
						<form:errors path="id" cssClass="errmsg">아이디 또는 비밀번호를 다시 확인하세요. 회원이 아니시거나 아이디 또는 비밀번호를 잘못 입력하셨습니다.</form:errors>

						 
				</div>
				<br>

				<div class="form-label-group">
					<form:input type="password" path="pw" class="form-control" placeholder="Password" required="required"/> 
					<form:errors path="pw" cssClass="errmsg"></form:errors>
					<%-- <form:label path="pw">Password</form:label> --%>
					<span class="col-sm-2 pwErr"></span>
				</div>

				<div class="checkbox mb-3">
					<label> 
					<input type="checkbox" name="rememberme" value="rememberme"/>
						Remember me
					</label>
				</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
				<div >
				<div align="center" style="margin-top:10px;">
					<a class="" style="padding-right:10px;" href="/login/findID">아이디찾기</a>
					<a class="" href="/login/findPW">비밀번호찾기</a>
				</div>
				
				</div>
				
			
				<p class="mt-5 mb-3 text-muted text-center">&copy; 2018-2019</p>
			</form:form>
			
			
		</div>
	</div>
</div>
</main>

<jsp:include page="/main_footer.jsp" flush="false" />
