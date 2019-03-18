<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:include page="/main_header.jsp" flush="false" />

<script>
	$(function() {
		$("#write").on("click", function(e) {
			e.preventdefault;

			if($("#sessionId").val().length == 0){			
				if(confirm("로그인이 필요합니다. 로그인 하시겠습니까?")){
					location.href = "${pageContext.request.contextPath }/login/loginForm";
				}else{
					
				}
			}else{	
				location.href = "${pageContext.request.contextPath }/bbs/write";
			}
			 
			/* <c:if test="${sessionScope.user eq null}">
				console.log("세션정보없음");
				return;
			</c:if> */
			
			/* var user = "${sessionScope.user == null ? null:sessionScope.user.id}";
			if(user==null){
				location.href = "${pageContext.request.contextPath }/login/loginForm";
				return;
			}else{
				
			} */
		});
		
		$("#searchbtn").on("click", function(){
			if($("#keyword").val().trim().length == 0){
				alert('검색어를 입력하세요.');
				return;
			}
			else{
				var searchType = $("#searchType").val();
				var keyword = $("#keyword").val().trim();
				console.log(searchType);
				console.log(keyword);
				$("form").submit();
			}
		})
		
	});
</script>

<div class="container">
	<div class="table-responsive">
		<h3 class="text-center p-3 mb-3 ">게시판</h3>
		<table class="table table-striped table-sm" summary="게시글목록">
		<input  type="hidden" id="sessionId" value="${user.id }"></input>
		<input  type="hidden" id="sessionId2" value=""></input>
			<!-- <caption>
			<b>게시글목록</b>
		</caption> -->
			<colgroup>
				<col width="10%">
				<col width="55%">
				<col width="15%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">작성일</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="bbsDTO">
				<c:if test="${bbsDTO.isdel eq 'Y'}">
					<tr>
						<td>${bbsDTO.bnum}</td>

						<td>
						
          					삭제된 게시물입니다
          				</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</c:if>
				<c:if test="${bbsDTO.isdel ne 'Y'}">
				<tr>
						<td>${bbsDTO.bnum}</td>

						<td>
						<c:forEach begin="1" end="${bbsDTO.bindent }">&nbsp;&nbsp;</c:forEach>
            			  <c:if test="${bbsDTO.bindent>0 }">			
           				   	<img src = "${pageContext.request.contextPath}/resources/images/icon_reply.gif"/>
         				   </c:if>
          					<a class="text-reset" href="/bbs/view?bnum=${bbsDTO.bnum }&${pc.makeSearchURL(pc.recordCriteria.reqPage)}">${bbsDTO.btitle}</a>
          				</td>
						<td>${bbsDTO.bnickname}</td>
						<td>${bbsDTO.bcdate}</td>
						<td>${bbsDTO.bhit}</td>
					</tr>
				</c:if>
					
				</c:forEach>

				<tr>
					<td colspan="5" align="right">
						<button type="button" class="btn btn-sm btn-outline-success" id="write">글쓰기</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>


	<nav aria-label="Page navigation example">
		<ul class="pagination pagination-sm justify-content-center">
			
				<c:if test="${pc.prev }">
					<li class="page-item"><a class="page-link" href="/bbs/list?${pc.makeSearchURL(1) }&searchType=${pc.searchType}&keyword=${pc.keyword}">처음</a></li>
					<li class="page-item"><a class="page-link" href="/bbs/list?${pc.makeSearchURL(pc.startPage - 1) }&searchType=${pc.searchType}&keyword=${pc.keyword}">이전</a></li>
				</c:if>
				
				

				<c:forEach begin="${pc.startPage }" end="${pc.endPage }" var="pageNum">
					<!-- 현재페이지와 요청페이지가 다르면 -->
					<c:if test="${pc.recordCriteria.reqPage != pageNum }">
					<li class="page-item">
						<a class="page-link" href="/bbs/list?${pc.makeSearchURL(pageNum) }&searchType=${pc.searchType}&keyword=${pc.keyword}">${pageNum }</a>
					</li>
					</c:if>

					<!-- 현재페이지와 요청'페이지가 같으면 강조표시 -->
					<c:if test="${pc.recordCriteria.reqPage == pageNum }">
					<li class="page-item active">
						<a class="page-link" href="/bbs/list?${pc.makeSearchURL(pageNum) }&searchType=${pc.searchType}&keyword=${pc.keyword}" >${pageNum }</a>
					</li>
					</c:if>
					<!-- 요청페이지와 현재페이지가 같으면 강조표시 -->
				</c:forEach>


			<c:if test="${pc.next }">
				<li class="page-item"><a class="page-link" href="/bbs/list?${pc.makeSearchURL(pc.endPage + 1) }&searchType=${pc.searchType}&keyword=${pc.keyword}">다음</a></li>
				<li class="page-item"><a class="page-link" href="/bbs/list?${pc.makeSearchURL(pc.finalEndPage) }&searchType=${pc.searchType}&keyword=${pc.keyword}">마지막</a></li>
			</c:if>
		</ul>
	</nav>

      <div class="row justify-content-center mb-3">
        <form  action="/bbs/list" method="post">
       	<div class="row">
          <label class="col-sm-1 col-form-label col-form-label-sm px-0 mx-1" for="searchType">검색</label>
          <select class="col-sm-4 custon-select custom-select-sm px-1 mr-1" name="searchType" id="searchType">
            <option value="TC"
            	<c:out value="${pc.searchType == 'TC' ? 'selected' :'' }"/>>제목+내용</option>
            <option value="T"
            	<c:out value="${pc.searchType == 'T' ? 'selected' :'' }"/>>제목</option>
            <option value="C"
            	<c:out value="${pc.searchType == 'C' ? 'selected' :'' }"/>>내용</option>
            <option value="N"
            	<c:out value="${pc.searchType == 'N' ? 'selected' :'' }"/>>닉네임</option>
            <option value="I"
            	<c:out value="${pc.searchType == 'I' ? 'selected' :'' }"/>>아이디</option>
          </select>
          <input class="col-sm form-control form-control-sm px-1 mr-1" type="search" name="keyword" id="keyword" value = "${pc.keyword }" >
          <input class="btn btn-sm btn-outline-primary px-1 mx-0" class="button" type="button" value="검색" id="searchbtn">
        </div>
        </form>
      </div>
    </div>


<jsp:include page="/main_footer.jsp" flush="false" />
