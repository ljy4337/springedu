<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<jsp:include page="/main_header.jsp" flush="false" />
<main role="main" class="container">
  <div class="jumbotron">
    <p class="lead">아이디 찾기 결과</p> 
    <p class="lead">등록하신 아이디는  ${id}  입니다.</p> 
    <a class="btn btn-lg btn-primary" href="/login/loginForm" role="button">로그인 하기 &raquo;</a>
  </div>
</main>
<jsp:include page="/main_footer.jsp" flush="false" />