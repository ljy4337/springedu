<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



  <style>
    textarea.autosize {
      min-height:1rem;
      outline:none;      /* 클릭시 경계선 없애기 */
      width:100%;
      border:none;
      border-bottom:1px solid #000;
      overflow:hidden;   /* 스크롤바 앲애기 */
        transform: scaleX(1);
    }

  </style>
<script>
var bnum = ${bbsDTO.bnum};
var rereqPage = 1;
var login_id = "${sessionScope.user.id}";
var login_nickname = "${sessionScope.user.nickName}";

//대댓글 작성 html코드 시작-----------------------------------------------
var reply_str = '';
reply_str += "<li data-rrnum='' data-form='write' class='list-group-item m-0 p-0'>";
reply_str += "  <div>";
reply_str += "    <div class='row m-0 p-0'>";
reply_str += "      <div class='col-1'></div>";
reply_str += "      <div class='col-11'>";
reply_str += "        <textarea class='autosize'></textarea></div>";
reply_str += "      <div class='col-12 text-right'>";
reply_str += "        <a href='javascript:void(0)' class='rrclose badge badge-pill badge-light mx-0 px-2'>취소</a>";
reply_str += "        <a href='javascript:void(0)' class='rrreply badge badge-pill badge-primary mx-0 px-2'>댓글</a>";
reply_str += "      </div>";
reply_str += "    </div>";
reply_str += "  </div>";
reply_str += "</li>";
//대댓글 작성 html코드 끝-----------------------------------------------

// 대댓글 수정 html코드 시작-----------------------------------------------
var modify_str = '';
modify_str += "<li data-rrnum='' data-form='modify' class='list-group-item m-0 p-0'>";
modify_str += "  <div>";
modify_str += "    <div class='row m-0 p-0'>";
modify_str += "      <div class='col-1'></div>";
modify_str += "      <div class='col-11'>";
modify_str += "        <textarea class='autosize'></textarea></div>";
modify_str += "      <div class='col-12 text-right'>";
modify_str += "        <a href='javascript:void(0)' class='rrclose badge badge-pill badge-light mx-0 px-2'>취소</a>";
modify_str += "        <a href='javascript:void(0)' class='rrmodify badge badge-pill badge-warning mx-0 px-2'>수정</a>";
modify_str += "      </div>";
modify_str += "    </div>";
modify_str += "  </div>";
modify_str += "</li>";
//대댓글 수정 html코드 끝-----------------------------------------------


	$(function() {

		//댓글 목록 보이기
		 replyList(rereqPage); 

		// 글자수 제한-------------------------------
		var $rcontent = $("#rcontent");
		$("#rcontent").on("keyup", function() {
			var rcontent = $("#rcontent").val();
			var cnt = rcontent.trim().length;

			$("#count").html(cnt + '/100')
			if (cnt > 100) {
				$("#rcontent").addClass("is-invalid");
				rcontent = rcontent.substring(rcontent.length - 100);
				$rcontent.val(rcontent);

				$("#count").html(cnt + '/100')
				return false;
			}
			$("#rcontent").removeClass("is-invalid");
		});
		$rcontent.keyup();
		//----------------------------------------------
		// 댓글 작성
		$("#replyBtn").on("click",function() { 
					var rid = $("#rid").text(); //작성자
					var rcontent = $("#rcontent").val(); //댓글본문
					var rnickname = $("#rnickname").text();
					var cnt = rcontent.trim().length;
					
					if (cnt == 0) {
						alert("댓글을 입력하세요.");
						$("#rcontent").attr("palceholder", "댓글을 입력하세요.")
								.addClass("is-invalid");
						$("#rcontent").focus();
					} else if (cnt > 10) {
						alert("100자 이하로 입력해주세요.");
						$("#rcontent").focus();
						return false;
					}
					console.log(rid);
					console.log(rcontent);
					console.log(bnum);
					console.log(rnickname);

					//AJAX 비동기 처리기술
					$.ajax({
						type : "POST", //post, get, put, delete
						url : "/rbbs/posts",
						headers : {
							"Content-Type" : "application/json",
							"X-HTTP-Method-Override" : "POST"
						},
						dataType : "text",
						data : JSON.stringify({
							bnum : bnum,
							rid : rid,
							rnickname : rnickname,
							rcontent : rcontent
						}),

						success : function(result) {
							//댓글 목록 삽입
							replyList(rereqPage);
							/* $("#rcontent").reset(); */

						},
						error : function(xhr, status, err) {
							console.log("xhr : " + xhr);
							console.log("status : " + status);
							console.log("err : " + err);
						}

					});

				});// 댓글 작성 끝
				
		

	});

	//요청페이지에 대한 댓글목록 가져오기
	function replyList(rereqPage) {
		var str = "";
		var rrnickname = ""; // 부모댓글 닉네임
		var rrid = "";  // 부모댓글 아이디
		$.ajax({
					type : "GET",
					url : "/rbbs/posts/map/" + bnum + "/" + rereqPage,
					dataType : "json",

					success : function(data, status, xhr) {
						console.log("data : " + data);
						/* console.log("data.Map : " + data.Map);*/
						/* console.log("data.item : " + data.item);
						console.log("data.pagecriteria : " + data.pagecriteria); */
						
						str += "<ul class='list-group list-group-flush'>"

						$.each(data.item,function(idx, rec) {
									/* console.log("idx : " + idx);
									console.log("rec : " + rec) */
									
									if(rec.rrdto != null){
										rrnickname = '@'+rec.rrdto.rnickname;
										rrid = rec.rrdto.rrid;
									}
									// 개발자의 필요에 의해 속성을 넣어줄 수 있다.
									// li에 data-rNum 의 속성을 넣어줌(data-rnum에 rec.rnum을 넣어줌)
									
									 if(rec.isdel == 'Y'){
											//들여쓰기
											if( rec.rindent == 0){
											// 1step
												str += "<li data-rnum='"+rec.rnum+"' class='reList list-group-item m-0 p-0'>";
												str += "  <div>";
												str += "    <div class='row m-3 p-0'>";
												str += "      <div class='col-1'>";
												str += "      </div>";
												str += "      <div class='col-11'>";
												str += "        <p class='mb-0'><span class='mr-1 text-warning'>삭제된 댓글입니다.</span></p>";
												str += "      </div>";
												str += "    </div>";					
											}else{
												str += "<li data-rnum='"+rec.rnum+"' data-rrnum='"+rec.rrnum+"' class='reList list-group-item m-0 p-0'>";                   
												str += "  <div>";
												str += "    <div class='row m-3 p-0'>";
												str += "      <div class='col-1'></div>";
												str += "      <div class='col-1'>";
												str += "      </div>";
												str += "      <div class='col-10'>";
												str += "        <p class='mb-0'><span class='mr-1 text-warning'>삭제된 댓글입니다.</span></p>";
												str += "      </div>";
												str += "    </div>";		
											}
											str += "  </div>";
											str += "</li>";
									} 
									else{
										//들여쓰기
										if( rec.rindent == 0){
										// 1step
											str += "<li data-rnum='"+rec.rnum+"' class='reList list-group-item m-0 p-0'>";
											str += "  <div>";
											str += "    <div class='row m-0 p-0'>";
											str += "      <div class='col-1'>";
											str += "      <img src='/resources/images/basicicon.jpg' class='m-1 rounded-circle' style='width:45px;'>";
											str += "      </div>";
											str += "      <div class='col-11'>";
											str += "        <h6>"+rec.rnickname+"("+rec.rid+") <small class='ml-2'><i>"+rec.rcdate+"</i></small></h6>";
											str += "        <p clsss='replybody'>"+rec.rcontent+"</p>";
											str += "      </div>";
											str += "    </div>";					
										}else{
											str += "<li data-rnum='"+rec.rnum+"' data-rrnum='"+rec.rrnum+"' class='reList list-group-item m-0 p-0'>";                   
											str += "  <div>";
											str += "    <div class='row m-0 p-0'>";
											str += "      <div class='col-1'></div>";
											str += "      <div class='col-1'>";
											str += "      <img src='/resources/images/basicicon.jpg' class='m-1 rounded-circle' style='width:45px;'>";
											str += "      </div>";
											str += "      <div class='col-10'>";
											str += "        <h6>"+rec.rnickname+"("+rec.rid+") <small class='ml-2'><i>"+rec.rcdate+"</i></small></h6>";
											str += "        <p clsss='replybody'><span class='mr-1 text-primary'>"+rrnickname+"</span>"+rec.rcontent+"</p>";
											str += "      </div>";
											str += "    </div>";
										
										}
										str += "    <div class='row m-0 p-0'>";
										str += "      <div class='col-1'></div>";
										str += "      <div class='col-9 pl-4'>";
										str += "        <a href='javascript:void(0)' data-active='off' class='goodBtn badge badge-light' ";
										str += "        data-toggle='tooltip' data-placement='top' data-html='true' title='<b>호감</b>'>";
										str += "        <i class='far fa-thumbs-up mr-2'>"+rec.rgood+"</i></a>";
										str += "        <a href='javascript:void(0)' data-active='off' class='badBtn badge badge-light' ";
										str += "        data-toggle='tooltip' data-placement='top' data-html='true' title='<b>비호감</b>'>";
										str += "        <i class='far fa-thumbs-down mr-2'>"+rec.rbad+"</i></a>";
										str += "        <a href='javascript:void(0)' data-active='off' ";
										str += "        class='reReplyBtn badge badge-pill badge-primary px-2'>댓글</a>";
										str += "      </div>";
										
										if(login_id == rec.rid){
										str += "      <div class='col-2 m-0 p-0 text-right'>";
										str += "        <a href='javascript:void(0)' data-active='off' ";
										str += "        class='reModifyBtn badge badge-pill badge-warning mx-0 px-2'>수정</a>";
										str += "        <a href='javascript:void(0)' data-active='off' ";
										str += "        class='reDelBtn badge badge-pill badge-danger mx-0 px-2'>삭제</a>";
										str += "      </div>";
										}
										
										str += "    </div>";
										str += "  </div>";
										str += "</li>";
									}
						});
						str += "</ul>";
						
						//댓글 목록 삽입
						$("#replyList").html(str);	

						// 페이지 리스트 호출
						showPageList(data.pagecriteria);
						doActionEvent();

					},
					error : function(xhr, status, err) {
						console.log("xhr : " + xhr);
						console.log("status : " + status);
						console.log("err : " + err);
					}

				});

		// 페이지 번호 클릭시 이벤트 처리
		$("#pageNumList").on("click", "li a", function(e) {
			e.preventDefault(); // 현재 이벤트의 기본동작을 중단. 
			e.stopImmediatePropagation(); // 현재 이벤트가 상위 및 현재 레벨에 걸린 다른 이벤트도 동작않도록 중단
			e.stopPropagation(); // 현재 이벤트가 상위로 전파되지 않도록 중단
			reqPage = $(this).attr("href");
			replyList(reqPage);
			return false;

		});
	}

	// 페이지 구현
	function showPageList(pagecriteria) {

		var str = "";
		console.log(pagecriteria);

		// 이전페이지여부
		if (pagecriteria.prev) {
			// 처음  ▶︎▷◀︎◁
			//str += "<li><a href='1'>처음</a></li>";

			// 이전 페이지
			//str += "<li><a href='" + (pagecriteria.startPage - 1)+ "'>이전</a></li>";

			str += "<li class='page-item'><a class='page-link' href='1' tabindex='-1' aria-disabled='true'>처음</a></li>";
			str += "<li class='page-item'><a class='page-link' tabindex='-1' aria-disabled='true' href='"
					+ (pagecriteria.startPage - 1) + "'>이전</a></li>";

		}

		// 페이지 1~10
		for (i = pagecriteria.startPage, end = pagecriteria.endPage; i <= end; i++) {

			//str += "<li><a href='"+i+"'>" + i + "</a></li>";
			/* <!-- 현재페이지와 요청페이지가 다르면 --> */
			if (pagecriteria.recordCriteria.rereqPage != rereqPage) {
				str += "<li class='page-item'><a class='page-link' href='"+i+"'>"
						+ i + "</a></li>"

			}

			/* <!-- 현재페이지와 요청'페이지가 같으면 강조표시 --> */
			if (pagecriteria.recordCriteria.rereqPage == rereqPage) {
				str += "<li class='page-item active'><a class='page-link' href='"+i+"'>"
						+ i + "</a></li>"

			}
		}

		// 다음페이지여부
		if (pagecriteria.next) {
			// 다음 페이지
			str += "<li class='page-item'><a class='page-link' tabindex='-1' aria-disabled='true' href='"
					+ (pagecriteria.endPage + 1) + "'>다음</a></li>";
			// 마지막 페이지
			str += "<li class='page-item'><a class='page-link' href='"
					+ (pagecriteria.finalEndPage) + "'>마지막</a></li>";
		}

		// 페이징 삽입
		$("#pageNumList").html(str);
	}
	function doActionEvent(){
		//대댓글 작성 클릭시
	      $(".reReplyBtn").on("click",function(e){
	    	  console.log('reReplyBtn');
	        var $liEle = $(this).parents('li');
	        var $data_rnum  =  $liEle.attr('data-rnum'); //댓글번호
	        var $data_rrnum =  $liEle.next().attr('data-rrnum'); //대댓글번호
	        var $data_form   = $liEle.next().attr('data-form'); //

	        //대댓글 양식이 없을경우만 추가
	        if( $data_rnum != $data_rrnum) {
	          $liEle.after(reply_str);

	        }
	        //현재글의 댓글은 있으나 댓글작성
	     	 else if($data_rnum == $data_rrnum && $data_form == null){
	    		  $liEle.after(reply_str);
	      
	     	 }
	        else if($data_rnum == $data_rrnum && $data_form == 'write') {

	          var $textarea = $liEle.next().find('textarea');
	          var $tmp = $textarea.val();

	          $liEle.next().replaceWith(reply_str);
	          $liEle.next().find('textarea').val($tmp);

	        }else if($data_rnum == $data_rrnum && $data_form == 'modify'){

	          $liEle.next().replaceWith(reply_str);
	          $liEle.next().find('textarea').val($tmp);
	        }

	        $liEle.next().attr('data-rrnum', $liEle.attr('data-rnum'));

	        //대댓글 양식 높이 자동 조절
	        var $textareaEle = $('textarea.autosize');
	        $textareaEle.on('keyup focus', function(){
	          $(this)[0].style.height = 'auto';
	          $(this).css('height', $(this).prop('scrollHeight'));
	          console.log($(this).prop('scrollHeight'));
	        });
	        $textareaEle.trigger('focus');

	        
	        //대댓글 등록 클릭시 - bnum, rrnum, rid, rnickname, rcontent
		      $('.rrreply').on('click', function(){
		        console.log('등록');
		        var $li = $(this).parents("li");
		        var rrnum = $li.attr('data-rrnum');
		        var rcontent = $li.find('textarea').val(); 
		       	console.log("rrnum : "+rrnum);
		        
		        
		        $.ajax({
		        	type : "POST",
		        	url : "/rbbs/rposts",
		        	headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "POST"
					},
		        	dataType : "text",
		        	data : JSON.stringify({
		        		bnum : bnum,
		        		rrnum : rrnum,
		        		rid : login_id,
		        		rnickname : login_nickname,
		        		rcontent : rcontent
		        	}),
		        	success : function(result) {
						replyList(rereqPage);
					},
					error : function(xhr, status, err) {
						console.log("xhr : " + xhr);
						console.log("status : " + status);
						console.log("err : " + err);
					}
					
		        	
		        });
		       
		      });
	        
	        //대댓글 양식 닫기
	        $('.rrclose').on('click',function(e){
	          e.stopImmediatePropagation();
	          $liEle.next().remove();
	        });

	      });

	      //대댓글 수정 클릭시
	      $('.reModifyBtn').on('click',function(e){
	        console.log("reModifyBtn");

	        var $liEle = $(this).parents('li');
	        var $data_rnum  =  $liEle.attr('data-rnum'); //댓글번호
	        var $data_rrnum =  $liEle.next().attr('data-rrnum'); //대댓글번호
	        var $data_form   = $liEle.next().attr('data-form'); //

	        //대댓글 수정 양식이 없을경우만 추가
	        if( $data_rnum != $data_rrnum) {
	          var tmp = $liEle.find('p').text();
	          $liEle.after(modify_str);
	          $liEle.next().find('textarea').val(tmp);

	        }
		      //현재글의 대댓글은 있으나 댓글수정 중이 아닐경우
		      else if($data_rnum == $data_rrnum && $data_form == null){
		        var tmp = $liEle.find('p').text();
		        $liEle.after(modify_str);
		        $liEle.next().find('textarea').val(tmp);
		      }
	        else if($data_rnum == $data_rrnum && $data_form == 'write') {
	          $tmp = $liEle.find('p').text();
	          $liEle.next().replaceWith(modify_str);
	          $liEle.next().find('textarea').val($tmp);

	        }else if($data_rnum == $data_rrnum && $data_form == 'modify') {

	          var $textarea = $liEle.next().find('textarea')
	          var $tmp = $textarea.val();
	          if($tmp.trim().length == 0 ){
	            $tmp = $liEle.find('p').text();
	          }

	          $liEle.next().replaceWith(modify_str);
	          $liEle.next().find('textarea').val($tmp);
	        }
	        $liEle.next().attr('data-rrnum',$liEle.attr('data-rnum'));

	        //대댓글 수정 양식 높이 자동 조절
	        var $textareaEle = $('textarea.autosize');
	        $textareaEle.on('keyup focus', function(){
	          $(this)[0].style.height = 'auto';
	          $(this).css('height', $(this).prop('scrollHeight'));
	        });
	        $textareaEle.trigger('focus');
	        
		      //대댓글 수정 클릭시
		      $('.rrmodify').on('click', function(){
		        console.log('수정');
		        var $li = $(this).parents('li');
		        var rnum = $li.attr('data-rrnum');
		        var rcontent = $li.find('textarea').val();
		        console.log(rnum);
		        console.log(rcontent);
		
		      //AJAX 비동기 처리기술
				$.ajax({
					type : "PUT", //post, get, put, delete
					url : "/rbbs/posts",
					 headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "POST"
					}, 
					dataType : "text",
					data : JSON.stringify({
						rnum : rnum,
						rcontent : rcontent
			
					}),

					success : function(result) {
						replyList(rereqPage);
					},
					error : function(xhr, status, err) {
						console.log("xhr : " + xhr);
						console.log("status : " + status);
						console.log("err : " + err);
					}

				});
		        
		      });


	        //대댓글 수정 양식 닫기
	        $('.rrclose').on('click',function(e){
	          e.stopImmediatePropagation();
	          $liEle.next().remove();
	        });

	      });
	      //좋아요 클릭시
	      $('.goodBtn').on('click', function(){
	        console.log('좋아요');
	        var $li = $(this).parents('li');
	        var rnum = $li.attr('data-rnum');
	        var goodOrbad = $(this).attr('data-good');
	      //AJAX 비동기 처리기술
			$.ajax({
				type : "put", //post, get, put, delete
				url : "/rbbs/posts/good/"+rnum,
				/* headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				}, */
				dataType : "text",
				data : JSON.stringify({
					rnum : rnum,
					goodOrbad : goodOrbad
		
				}),

				success : function(result) {
					replyList(rereqPage);
				},
				error : function(xhr, status, err) {
					console.log("xhr : " + xhr);
					console.log("status : " + status);
					console.log("err : " + err);
				}

			});
	      });
	      
	      //싫어요 클릭시
	      $('.badBtn').on('click', function(){
	        console.log('싫어요');
	        var $li = $(this).parents('li');
	        var rnum = $li.attr('data-rnum');
	        var goodOrbad = $(this).attr('data-bad');
	      //AJAX 비동기 처리기술
			$.ajax({
				type : "put", //post, get, put, delete
				url : "/rbbs/posts/bad/"+rnum,
				/* headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				}, */
				dataType : "text",
				data : JSON.stringify({
					rnum : rnum,
					goodOrbad : goodOrbad
		
				}),

				success : function(result) {
					replyList(rereqPage);
				},
				error : function(xhr, status, err) {
					console.log("xhr : " + xhr);
					console.log("status : " + status);
					console.log("err : " + err);
				}

			});
	      });

	      //댓글 삭제 클릭시
	      $('.reDelBtn').on('click', function(){
	        //삭제 toast 띄우기
	        console.log('삭제');
	        var $li = $(this).parents('li');
	        var rnum = $li.attr('data-rnum');
	      //AJAX 비동기 처리기술
			$.ajax({
				type : "delete", //post, get, put, delete
				url : "/rbbs/posts/"+rnum,
				/* headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				}, */
				dataType : "text",
				data : JSON.stringify({
					rnum : rnum
				}),

				success : function(result) {
					replyList(rereqPage);
					$('#t-del-msg').toast('show');
				},
				error : function(xhr, status, err) {
					console.log("xhr : " + xhr);
					console.log("status : " + status);
					console.log("err : " + err);
				}

			});
	        
	      });
	      
	   

	      //tootip적용
	      $("[data-toggle='tooltip']").tooltip();
	}
</script>
<div class="container">
	<table class="table table-sm table-borderless table-dark">
		<colgroup>
			<col width=10%>
			<col width=80%>
			<col width=10%>
		</colgroup>
		<tr>
			<th>작성자</th>
			<td><span id="rid">${sessionScope.user.id}</span>/<span
				id="rnickname">${sessionScope.user.nickName }</span></td>
		</tr>
		<tr>
			<td>댓글</td>
			<td><textarea class="form-control" name="rcontent" id="rcontent"
					rows="2"></textarea></td>
			<td><button class="btn btn-danger btn-block btn-lg mt-2"
					id="replyBtn">등록</button>
				<p class="text-right" id="count">(0/100)</p></td>
		</tr>
	</table>

	<!-- 댓글목록 -->
	<div id="replyList"></div>

	</div>
	
	<!-- 댓글페이징 -->
	<div>
		<nav aria-label="Page navigation example">
			<ul class="pagination pagination-sm justify-content-center"
				id="pageNumList">
			</ul>
			
			<!-- tosts -->
  <div role='alert' aria-live='assertive' aria-atomic='true' class='toast' id='t-del-msg' data-autohide='true' data-delay='1300' style='position: absolute; right:50px; bottom:50px;'>
    <div class='toast-header'>
      <!--img src='...' class='rounded mr-2' alt='...'-->
      <i class='far fa-trash-alt fa-lg mr-2' style='color:#f00;'></i>
      <strong class='mr-auto'>[삭제]</strong>
      <small class='invisible'></small>
      <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>
        <span aria-hidden='true'>&times;</span>
      </button>
    </div>
    <div class='toast-body'>
      댓글이 삭제되었습니다.
    </div>
  </div>
		</nav>

</div>

