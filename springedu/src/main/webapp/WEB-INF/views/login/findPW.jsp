<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/main_header.jsp" flush="false" />
<div class="container">
	<div class="row justify-content-center">
	
		<div class="col-4">
			<form:form class="form-signin" modelAttribute="login" action="/login/findpwok">
				<div class="text-center mb-4">
					<!-- <img class="mb-4" src="/resources/images/login.jpg" alt=""
						width="150" height="150"> -->
					<h1 class="h3 mb-3 font-weight-normal">비밀번호 찾기</h1>
					<!-- <p>Build form controls with floating labels via the <code>:placeholder-shown</code> pseudo-element. <a href="https://caniuse.com/#feat=css-placeholder-shown">Works in latest Chrome, Safari, and Firefox.</a></p> -->
				</div>
				
				<div class="form-label-group">
					<form:input type="email" path="id" class="form-control" placeholder="아이디를 입력해주세요." required="required" autofocus="autofocus"/>
						 <form:errors path="id" cssClass="errmsg"></form:errors>
						<%-- <form:label path="id">Email address</form:label> --%>
						<span id="err"></span>
						<span id="idid"></span>
						 
				</div>
				<br >

				<div class="form-label-group">
					<form:input type="text" path="nickname" class="form-control" placeholder="닉네임을 입력해주세요." required="required" autofocus="autofocus"/>
						 <form:errors path="nickname" cssClass="errmsg"></form:errors>
						<%-- <form:label path="id">Email address</form:label> --%>
						<span id="err"></span>
						<span id="idid"></span>
						 
				</div>
				<br>

				<div class="form-label-group">
					<form:input type="text" path="tel" class="form-control" placeholder="전화번호를 입력해주세요." required="required"/> 
					<form:errors path="tel" cssClass="errmsg"></form:errors>
					<%-- <form:label path="pw">Password</form:label> --%>
					<span class="col-sm-2 pwErr"></span>
				</div>

				<button class="btn btn-lg btn-primary btn-block" type="submit">비밀번호 찾기</button>
			
			</form:form>
			
			
		</div>
	</div>
</div>
<jsp:include page="/main_footer.jsp" flush="false" />
