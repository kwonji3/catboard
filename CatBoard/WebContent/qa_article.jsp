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
		<!-- 네비게이션바 헤더 -->
		<%@include file="view/header.jsp"%>

		<!-- 질문 상세보기  -->
		<%
			BoardVO board = (BoardVO) request.getAttribute("board");
		%>
		<div class="card" style="margin-top: 25px; margin-bottom: 25px;">
			<table>
				<thead>
					<tr>
						<!-- -->
						<td style="width: 70px;"><h1
								style="padding-left: 20px; padding-top: 20px;">Q.</h1></td>
						<td><h2 style="padding-top: 20px;">${board.title }</h2></td>
						<td style="text-align: center; padding-top: 20px;">${board.id }</td>
						<td
							style="text-align: center; padding-left: 20px; padding-top: 20px;">${board.CREATE_TIME }</td>
					</tr>
				</thead>
			</table>
			<p class="bg-white text-dark" id="content"
				style="text-align: left; padding-left: 40px;">${board.content }</p>
				<!-- 수정 삭제, 작성자가 아니면 버튼 비활성화 -->
		<c:set var="id" value='<%=session.getAttribute("id")%>' />
		<!-- 변수 id = 세션값 -->
		<c:if test="${id eq board.id }">
			<!-- 변수 id가 board.id와 같으면 -->
			<!--  삭제 버튼 -->
			<form action="BoardDelete.do" method="post">
				<input type="hidden" name="board_num" value="${board.board_num }" />
				<input type="hidden" name="board_id" value="${board.board_id }" />
				<input type="submit" class="btn btn-outline-dark   float-right"
					value="삭제" />
			</form>
			<!-- 수정 버튼 -->
			<input type="button" class="btn btn-outline-dark   float-right"
				data-toggle="modal" data-target="#exampleModal2" value="수정" />
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
							<form action="BoardUpdate.do" method="post">
								<input type="hidden" name="board_num"
									value="${board.board_num }" />
								<div class="container1">
									<div class="row">
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
														name="board_id" required>
														<option selected>${board.board_id }</option>
														<option value="고양이지식">고양이 지식</option>
														<option value="고양이입양">고양이 입양</option>
														<option value="고양이질문">고양이 질문</option>
													</select>
													<input type="hidden" name="id"
														value="<%=session.getAttribute("id")%>">
												</tr>
											</thead>
											<tbody>
												<tr>
													<td><textarea type="text"
															class="form-control is vaild" placeholder="글 제목"
															name="bbsTitle" maxlength="50" id="validationServer03"
															aria-describedby="validationServer03Feedback" rows="1"
															required />${board.title }</textarea></td>
												</tr>
												<tr>
													<td><textarea class="form-control is vaild"
															placeholder="글 내용" name="bbsContent" maxlength="2048"
															style="height: 350px; width: 420px;"
															id="validationServer03"
															aria-describedby="validationServer03Feedback" required>${board.content }</textarea></td>
												</tr>
											</tbody>
										</table>
										<button type="submit" class="btn btn-outline-dark float-right">수정하기</button>
										<button type="button" class="btn btn-secondary float-right"
											style="margin-right: 10px;" data-dismiss="modal">닫기</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<!-- id 와 board.id가 같지 않으면 버튼 비활성화 -->
		<c:if test="${id ne board.id }">
			<div>
				<input type="button" class="btn btn-outline-dark float-right"
					disabled="disabled" value="삭제" /> <input type="button"
					class="btn btn-outline-dark float-right" disabled="disabled"
					value="수정" />
			</div>
		</c:if>
		</div>
		
		<!-- 답변 보기 -->
		<div class="card" style="margin-bottom: 25px;">
			<div class="board__container">
				<table class="board">
					<c:forEach var="com" items="${comment }">
						<tr>
							<td style="width: 70px;"><h1
									style="padding-left: 20px; padding-top: 20px;">A.</h1></td>
							<td class="board__contents" id="board-body"
								style="padding-left: 20px; padding-top: 20px;">${com.cmt }<h2
									id=link-to-column></h2></td>
							<td>${com.id }</td>
							<td>${com.CREATE_TIME }</td>
						</tr>
					</c:forEach>
				</table>
				<div class="board__index-containeor" id="index-container">
					<!-- 답변 등록 -->
					<div class="editor__container">
						<div class="editor__container">
							<form action="CommentQInsert.do" method="post"
								class="editor__form" id="editor-form">
								<div class="input-group">
									<input type="text" class="form-control" id="editor-title-input"
										name="cmt" placeholder="답변내용" style="width: 400px;"> <input
										type="hidden" name="id"
										value="<%=session.getAttribute("id")%>"> <input
										type="hidden" name="num" value=${board.board_num }>
									<button type="submit" class="btn btn-dark"
										id="editor-submit-btn">답변하기</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
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