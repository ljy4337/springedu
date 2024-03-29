<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko" class="h-100">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="ysh">
<title>JinYoung's Page</title>

<!-- Bootstrap 시작 -->
<link rel="stylesheet" href="/resources/bootstrap-4.2.1/css/bootstrap.css" />

<script src="/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
<script src="/webjars/popper.js/1.14.6/dist/umd/popper.min.js"></script>
<script src="/resources/bootstrap-4.2.1/js/bootstrap.js"></script>
<!-- Bootstrap  -->
<!-- font awesome -->
<link rel="stylesheet" href="/webjars/font-awesome/5.6.3/css/all.css"/>


<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<!-- Custom styles for this template -->
<link
	href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/resources/css/blog.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,700|Noto+Serif+KR:300,400,700|Open+Sans:300i,400,400i,600,700,800&amp;subset=korean"
	rel="stylesheet">
<style>
.display-4 {
	font-size: 2rem;
	font-weight: 400;
	line-height: 1.2;
}

html, body, samp, .tooltip, .popover, text-monospace {
	font-family: 'Open Sans', 'Noto Sans KR', sans-serif, 'Noto Serif KR',
		serif;
}
</style>
<script>
	$(function(){
 		$(".topMenu").mouseover(function(){
 			$("rowmenu1").toggle();
 		});
 		
	
	/* 	 $(".menu>a").mouseover(function(){            
		  var submenu = $(this).next("ul");             
		  // submenu 가 화면상에 보일때는 위로 접고 아닐시 아래로 펼치기            
		  if( submenu.is(":visible") ){                
		   submenu.slideUp();            
		  }else{                
		   submenu.slideDown();            
		  }      */   


	});
</script>
</head>
<body class="d-flex flex-column h-100">
	<div class="container">
		<header class="blog-header py-3">
			<div
				class="row flex-nowrap justify-content-between align-items-center">
				<div class="col-3 pt-1">
					<a class="text-muted" href="/">Home</a>
				</div>
				<div class="col-5 text-center">
					<a class="blog-header-logo text-dark" href="/">JinYoung's Page</a>
				</div>
				<div class="col-4 d-flex justify-content-end align-items-center">
					<a class="text-muted" href="#"> <svg
							xmlns="http://www.w3.org/2000/svg" width="20" height="20"
							viewBox="0 0 24 24" fill="none" stroke="currentColor"
							stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
							class="mx-3" focusable="false" role="img">
							<title>Search</title><circle cx="10.5" cy="10.5" r="7.5"></circle>
							<line x1="21" y1="21" x2="15.8" y2="15.8"></line></svg>
					</a>

					<c:choose>
						<c:when test="${sessionScope.user eq null }">
							<a class="btn btn-sm btn-outline-secondary mr-2"
								href="/login/loginForm">로그인</a>
							<a class="btn btn-sm btn-outline-secondary"
								href="/member/memberJoinForm">회원가입</a>
						</c:when>
						<c:otherwise>
							<b class="mr-2">${sessionScope.user.nickName}님</b>
							<a class="btn btn-sm btn-outline-secondary mr-2" href="/member/memberinfo/${sessionScope.user.id }">내정보</a>
							<a class="btn btn-sm btn-outline-secondary" href="/login/logout">로그아웃</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</header>
		
		<div>
			
		</div>
		<!-- <div class="topMenu">
			<div class="row">
				<div class="col-3 menu1">
					<span>MENU1</span>
				</div>
				<div class="col-3">
					<span>MENU2</span>
				</div>
				<div class="col-3">
					<span>MENU3</span>
				</div>
				<div class="col-3">
					<span>MENU4</span>
				</div>
			</div>
			<div class="row rowmenu1">
				<div class="col-3">
					<ul>
						<li>12333</li>
						<li>12</li>
						<li>12</li>
					</ul>
				</div>
				<div class="col-3">
					<ul>
						<li>12333</li>
						<li>12</li>
						<li>12</li>
					</ul>
				</div>
				<div class="col-3">
					<ul>
						<li>12333</li>
						<li>12</li>
						<li>12</li>
					</ul>
				</div>
				<div class="col-3">
					<ul>
						<li>12333</li>
						<li>1222</li>
						<li>12</li>
					</ul>
				</div>
				
			</div>
		</div> -->
		<div class="nav-scroller py-1 mb-2">
			<!-- <nav class="nav d-flex justify-content-between"> -->
			<nav class="nav d-flex justify-content-between">
				
					<a class="p-2 text-muted" href="/bbs/list">게시판</a>
				
			</nav>
		</div>



		<!--    <div class="row mb-2">
    <div class="col-md-6">
      <div class="card flex-md-row mb-4 shadow-sm h-md-250">
        <div class="card-body d-flex flex-column align-items-start">
          <strong class="d-inline-block mb-2 text-primary">World</strong>
          <h3 class="mb-0">
            <a class="text-dark" href="#">Featured post</a>
          </h3>
          <div class="mb-1 text-muted">Nov 12</div>
          <p class="card-text mb-auto">This is a wider card with supporting text below as a natural lead-in to additional content.</p>
          <a href="#">Continue reading</a>
        </div>
        <svg class="bd-placeholder-img card-img-right flex-auto d-none d-lg-block" width="200" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect fill="#55595c" width="100%" height="100%"/><text fill="#eceeef" dy=".3em" x="50%" y="50%">Thumbnail</text></svg>
      </div>
    </div>
    <div class="col-md-6">
      <div class="card flex-md-row mb-4 shadow-sm h-md-250">
        <div class="card-body d-flex flex-column align-items-start">
          <strong class="d-inline-block mb-2 text-success">Design</strong>
          <h3 class="mb-0">
            <a class="text-dark" href="#">Post title</a>
          </h3>
          <div class="mb-1 text-muted">Nov 11</div>
          <p class="card-text mb-auto">This is a wider card with supporting text below as a natural lead-in to additional content.</p>
          <a href="#">Continue reading</a>
        </div>
        <svg class="bd-placeholder-img card-img-right flex-auto d-none d-lg-block" width="200" height="250" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect fill="#55595c" width="100%" height="100%"/><text fill="#eceeef" dy=".3em" x="50%" y="50%">Thumbnail</text></svg>
      </div>
    </div>
  </div> -->
	</div>