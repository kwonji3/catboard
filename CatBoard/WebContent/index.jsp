<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.CatBoard.vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cat Board</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="stylesheet" href="src/style.css">
<link rel="stylesheet" href="src/hover.css">
</head>
<body>
	<!--  메인 화면 로고 및 로그인 바 -->
	<nav class="navbar navbar-light bg-light">
		<a class="navbar-brand text-dark" href="index.jsp"
			style="padding-left: 80px;">CatBoard : 집사의 아지트</a>
		<div class="float-right" style="padding-right: 20px;">
			<%
				if (session.isNew() || session.getAttribute("id") == null) {
			%>
			<!-- 세션이 없으면, 로그인 아이디 없으면 공백 -->
			<button type="button" class="btn btn-dark" id="log"
				data-toggle="modal" data-target="#staticBackdrop"
				onclick="logout();" )>로그인</button>
			<button type="button" class="btn btn-dark" id="log"
				data-toggle="modal" data-target="#staticBackdrop2"
				style="margin-right: 50px;" )>회원가입</button>

			<%
				} else {
			%>
			<!--  로그인 했으면 -->
			<%=session.getAttribute("id")%>님 어서오세요
			<!-- id입력한 값 세션 -->
			<button type="button" class="btn btn-dark" id="log"
				data-toggle="modal" data-target="#staticBackdrop"
				style="margin-right: 50px;" )>로그아웃</button>
			<%
				}
			%>
		</div>
	</nav>
	<!--  모달 창 생성   -->
	<div class="modal fade" id="staticBackdrop" data-backdrop="static"
		data-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel"></h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<%
							if (session.isNew() || session.getAttribute("id") == null) {
						%>
						<%
							String msg = (String) request.getAttribute("error");
						if (msg == null)
							msg = "";
						//String result = (String)request.getAttribute("alert");
						%>
						<!-- 에러 메세지 -->
						<%=msg%>

						<!--  아이디, 비밀번호 입력 폼 -->
						<form action="memberLogin.do" method="post">
							<input type="text" class="form-control is vaild"
								placeholder="아이디" name="id" id="validationServer03"
								aria-describedby="validationServer03Feedback" required><br>
							<input type="password" class="form-control is vaild"
								placeholder="비밀번호" name="passwd" id="validationServer03"
								aria-describedby="validationServer03Feedback" required><br>

							<div class="modal-footer">
								<input type="submit" class="btn btn-light" value="로그인">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
								<script>
									if ("${alert}" != "") {
										alert("아이디/비밀번호가 틀렸습니다.")
									}
								</script>
							</div>
						</form>
						<%
							} else {
						%>
						<a href="logout.jsp">로그아웃</a>
						<!--  로그아웃 하기 위한 클릭 -->
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 회원가입 모달 -->
	<div class="modal fade" id="staticBackdrop2" data-backdrop="static"
		data-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel2" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel2">회원가입</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<!-- 회원가입 아이디, 비밀번호 입력 폼 -->
						<form action="memberInsert.do" method="post">
							<input type="text" class="form-control is vaild"
								placeholder="아이디" name="id" id="validationServer03"
								aria-describedby="validationServer03Feedback" required><br>
							<input type="password" class="form-control is vaild"
								onchange="join(this)" placeholder="비밀번호" name="passwd"
								id="validationServer03"
								aria-describedby="validationServer03Feedback" required><br>
							<div class="modal-footer">
								<input type="submit" class="btn btn-light" id="sub" value="회원가입">
								<script>
									function join(obj) {
										document.getElementById("sub").onclick = function() {
											alert("회원가입완료!");
											location.href = "index.jsp";
										}
									}
								</script>
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--  카테고리 선택 및 마우스 hover효과 -->
	<%
		BoardVO board = (BoardVO) request.getAttribute("board");
	%>
	<div class="grid2">
		<div class="card" style="padding: 20px; box-shadow: 3px 3px 7px gray;">
			<div class="hovereffect">
				<img class="img-responsive rounded" src="src/CatBoard_info.jpg"
					alt="">
				<div class="overlay">
					<h2>고양이 지식</h2>
					<a class="info" href="BoardList.do?name=고양이지식">좋은 집사가 되려면?</a>
				</div>
			</div>
		</div>
		<!-- 로그인을 해야 읽을 수 있는 카테고리 -->
		<div class="card" style="padding: 20px; box-shadow: 3px 3px 7px gray;">
			<div class="hovereffect">
				<img class="img-responsive rounded" src="src/CatBoard_adopt.jpg"
					alt="">
				<div class="overlay">
					<h2>고양이 입양</h2>
					<%
						if (session.isNew() || session.getAttribute("id") == null) {
					%>
					<a class="info" style="cursor: pointer;"
						onClick="alert('로그인을 해주세요.');"> 사지말고 입양하세요</a>
					<%
						} else {
					%>
					<a class="info" href="BoardList.do?name=고양이입양"> 사지말고 입양하세요</a>
					<%
						}
					%>
				</div>
			</div>
		</div>

		<div class="card" style="padding: 20px; box-shadow: 3px 3px 7px gray;">
			<div class="hovereffect">
				<img class="img-responsive rounded" src="src/CatBoard_qa.jpg" alt="">
				<div class="overlay">
					<h2>고양이 Q&A</h2>
					<%
						if (session.isNew() || session.getAttribute("id") == null) {
					%>
					<a class="info" style="cursor: pointer;"
						onClick="alert('로그인을 해주세요.');">무엇이든 물어보세요!</a>
					<%
						} else {
					%>
					<a class="info" href="BoardList.do?name=고양이질문">무엇이든 물어보세요!</a>
					<%
						}
					%>

				</div>
			</div>
		</div>
	</div>

	<script>
		// 모달을 위한 스크립트
		$('#myModal').on('shown.bs.modal', function() {
			$('#myInput').trigger('focus')
		})
	</script>
	<script
		scr="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
		crossorigin="anonymous"></script>
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link
		href="https://fonts.googleapis.com/css2?family=Gugi&family=Nanum+Gothic&display=swap"
		rel="stylesheet">
	<script type="text/javascript" src="js/main.js" charset="UTF-8"></script>
</body>
</html>