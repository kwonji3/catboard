<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.CatBoard.vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

</head>
<body>
	<div class="container">
		<!-- 네비게이션바  헤더 -->
		<%@include file="view/header.jsp"%>
		<!-- 이미지 슬라이드 -->
		<div>
			<button class="btn btn-light" data-toggle="collapse"
				href="#collapseExample" aria-expanded="false"
				aria-controls="collapseExample"
				style="background-color: transparent;">▼</button>
		</div>
		<div id="carouselExampleInterval" class="carousel slide"
			data-ride="carousel">
			<div class="collapse" id="collapseExample">
				<div class="well">
					<div class="carousel-inner" id="imgs">
						<div class="carousel-item active" data-interval="3000">
							<img src="src/cat2.jpg" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item" data-interval="3000">
							<img src="src/cat3.jpg" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item" data-interval="3000">
							<img src="src/cat4.jpg" class="d-block w-100" alt="...">
						</div>
					</div>
					<a class="carousel-control-prev" href="#carouselExampleInterval"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleInterval"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>

				</div>
			</div>
		</div>
		<br> <a class="blog-header-logo text-dark"
			style="margin: 10px;"> 고양이 입양 </a><br>

		<!--  글 목록 -->
		<form action="article.do" method="post">
			<table class="table table-hover">
				<thead class="thead">
					<tr>
						<th scope="cols">No.</th>
						<th scope="cols">제목</th>
						<th scope="cols">작성자</th>
						<th scope="cols">작성날짜</th>
					</tr>
				</thead>
				<%
					ArrayList<BoardVO> list = (ArrayList<BoardVO>) request.getAttribute("list");
				%>
				<%
					if (!list.isEmpty()) {
					for (int i = 0; i < list.size(); i++) {
						BoardVO board = list.get(i);
				%>
				<!-- 글 상세보기  -->
				<tbody>
					<tr>
						<td><%=board.getBoard_num()%></td>
						<!-- title -->
						<th scope="row" style="cursor: pointer;"><a
							href="article.do?num=<%=Integer.toString(board.getBoard_num())%>"
							style="text-decoration: none;"><%=board.getTitle()%></a></th>
						<!-- 작성자 -->
						<td><%=board.getId()%></td>
						<td><%=board.getCREATE_TIME()%></td>
					</tr>
					<%
						}
					%>
					<%
						} else {
					%>
					<h3>등록된 글이 없습니다.</h3>
					<%
						}
					%>
				</tbody>
			</table>
		</form>
		<!--  글 작성 버튼 및 모달 -->
		<!-- 로그인 X 면 작성버튼 비활성화 -->
		<%
			if (session.isNew() || session.getAttribute("id") == null) {
		%>
		<button type="button" class="btn btn-outline-dark  float-right"
			disabled="disabled" style="margin-right: 25px;">작성하기</button>
		<br>
		<br>
		<br>
		<%
			} else {
		%>
		<button type="button" class="btn btn-outline-dark  float-right"
			data-toggle="modal" data-target="#exampleModal2"
			style="margin-right: 25px;">작성하기</button>
		<br>
		<br>
		<br>
		<%
			}
		%>
		<!-- 글 작성 모달 -->
		<div class="modal fade" id="exampleModal2" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">게시글 작성</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="container">
							<div class="row">
								<form action="BoardInsert.do" method="post">
									<table class="table table-striped"
										style="text-align: center; border: 1px solid #dddddd">
										<thead>
											<tr>
												<th colspan="2"
													style="background-color: #eeeeee; text-align: center; width: 400px;">작성하기</th>
											</tr>
											<tr>
												<!--  콤보상자 -->
												<!--  유효성 검사 -->
												<select class="custom-select" id="inputGroupSelect02"
													name="select" onchange="select(this)" required>
													<option value="">카테고리 선택</option>
													<option value="catinfo">고양이 지식</option>
													<option value="catadopt">고양이 입양</option>
													<option value="catqa">고양이 질문</option>
												</select>
												<input type="hidden" name="id"
													value="<%=session.getAttribute("id")%>">
											</tr>
										</thead>
										<tbody>
											<tr>
												<td><input type="text" class="form-control is vaild"
													placeholder="글 제목" name="bbsTitle" maxlength="50"
													id="validationServer03"
													aria-describedby="validationServer03Feedback" required /></td>
											</tr>
											<tr>
												<td><textarea class="form-control is vaild"
														placeholder="글 내용" name="bbsContent" maxlength="2048"
														style="height: 350px; width: 420px;"
														id="validationServer03"
														aria-describedby="validationServer03Feedback" required></textarea></td>
											</tr>
										</tbody>
									</table>
									<button type="submit" class="btn btn-outline-dark float-right">글쓰기</button>
									<button type="button" class="btn btn-secondary float-right"
										style="margin-right: 10px;" data-dismiss="modal">닫기</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 페이지 네이션 -->
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center text-secondary">
				<!-- prev가 true면 -->
				<c:if test="${page.prev }">
					<!--  현재 페이지 전에  previous 버튼 생김 -->
					<li class="page-item disabled"><a class="page-link"
						href="/CatBoard/BoardList.do?name=${category}&num=${page.startPageNum - 1}"
						tabindex="-1" aria-disabled="true">Previous</a></li>
				</c:if>
				<!-- 반복문 (페이지 시작 표시 수, 페이지 마지막 표시 수) -->
				<c:forEach begin="${page.startPageNum }" end="${page.endPageNum}"
					var="num">
					<!-- select=1 가 현재 페이지 번호 num과 같지 않으면  -->
					<c:if test="${select != num }">
						<li class="page-item"><a class="page-link"
							href="/CatBoard/BoardList.do?name=${category}&num=${num}"
							style="color: black;">${num}</a></li>
					</c:if>
					<!-- 현재 해당 페이지이면  -->
					<c:if test="${select == num }">
						<!-- 해당페이지 굵게 -->
						<li class="page-item"><b class="page-link"
							style="color: black;">${num}</b></li>
					</c:if>
				</c:forEach>
				<!-- next가 true면 -->
				<c:if test="${page.next }">
					<!-- 마지막 나온 페이지 다음에 Next버튼 나옴 -->
					<li class="page-item"><a class="page-link"
						href="/CatBoard/BoardList.do?name=${category}&num=${page.endPageNum + 1}"
						style="color: black;">Next</a></li>
				</c:if>
			</ul>
		</nav>
	</div>

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

</body>
</html>