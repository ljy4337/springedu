<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<jsp:include page="/main_header.jsp" flush="false" />

<script>
	$(function(){
		
		// 글자수 제한-------------------------------
		var $bcontent = $("#bcontent");
		$("#bcontent").on("keyup", function() {
			var bcontent = $("#bcontent").val();
			var cnt = bcontent.trim().length;

			$("#count").html(cnt + '/300')
			if (cnt > 300) {
				$("#bcontent").addClass("is-invalid");
				bcontent = bcontent.substring(bcontent.length - 300);
				$bcontent.val(bcontent);

				$("#count").html(cnt + '/300')
				return false;
			}
			$("#bcontent").removeClass("is-invalid");
		});
		
		//유효성체크 오류시 에러표시 css적용 
		 $("span[id$='.errors']").each(function(idx){
			if($(this).text().length > 0){
				 $(this).prev().removeClass("is-valid").addClass("is-invalid");
				 $(this).removeClass("valid-feedback").addClass("invalid-feedback");
			}
		});	 
		
		$("#btn1").on("click",function(e){
			e.preventDefault();
			location.href="/bbs/list";
		});
		$("#btn2").on("click",function(e){
			e.preventDefault();
			//유효성체크
			
			//if(valchk()){
				
				$("form").submit();
			//}
			//서버로 전송 (form 태그의 action="/bbs/wirte")
			
		});
		$("#btn3").on("click",function(e){
			e.preventDefault();
			$("form").each(function(){
				this.reset();
			});
		});
	});
	
	//유효성체크 함수
	function valchk(){
		if($("#btitle").val().length == 0){
			alert('제목을 입력하세요.');
			$("#btitle").focus();
			return false;
		}
		if($("#btitle").val().length > 30){
			alert('제목이 너무 긴데요?????(30자 이하로 입력해주세요.)');
			$("#btitle").focus();
			return false;
		}
		
		if($("#bcontent").val().length == 0){
			alert('내용을 입력하세요.');
			$("#bcontent").focus();
			return false;
		}
		if($("#bcontent").val().length > 500){
			alert('내용도 좀 많군요.......(500자 이하로 입력해주세요.)');
			$("#bcontent").focus();
			return false;
		}
		
		return true;
		
	}
</script>
<div class="container">
	<div class="table-responsive">
	<h3 class="text-center p-3 mb-3 "">답글쓰기</h3>
		<form:form modelAttribute="bbsDTO" action="/bbs/replyok" method="post">
			<form:hidden  path="bgroup" value="${bbsDTO.bgroup }"/>
			<form:hidden  path="bstep" value="${bbsDTO.bstep }"/>
			<form:hidden  path="bindent" value="${bbsDTO.bindent }" />
			<input type="hidden" name="reqPage" value="${rc.reqPage}"  />
			<table class="table  table-sm" summary="답글쓰기">
				
				<colgroup>
					<col width="20%">
					<col width="">
				</colgroup>

				<tbody>
					<tr>
						<th>제목</th>
						<td>
							<form:input class="form-control is-valid" path="btitle"  type="text" value="[답글]:${bbsDTO.btitle }"/>
							<form:errors class="valid-feedback" path="btitle"></form:errors>
						</td>
					</tr>
					<tr>
						<th>작성자</th>

						<td>
							<%-- <%= session.getAttribute("nickname") %> ( <%= session.getAttribute("id") %> ) --%>
							${user.nickName }(${user.id }) <!-- <input name="bnickname" id="bnickname" type="text" placeholder="작성자(10자 이하)"> -->
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<form:textarea class="form-control is-valid" path="bcontent" rows="15" placeholder="내용을 입력하세요.(300자 이하)"></form:textarea>
							<form:errors class="valid-feedback" path="bcontent"></form:errors>
							<p class="text-right" id="count">(0/300)</p>
						</td>
					</tr>

				</tbody>

			<div class="btngrp">
				<tr>
					<td colspan="2" align="right">
						<button type="button" id="btn1" class="btn btn-outline-primary">목록</button>
						<button type="button" id="btn2" class="btn btn-outline-primary">등록</button>
						<button type="button" id="btn3" class="btn btn-outline-primary">취소</button>
					</td>
				</tr>
				
			
				
			</div>
			</table>
		</form:form>
	</div>
</div>

<jsp:include page="/main_footer.jsp" flush="false" />