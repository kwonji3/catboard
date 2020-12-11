<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="src/style.css">
</head>
<body>
	<!--  네비게이션 바 -->
        <nav class="navbar navbar-expand-lg navbar-light">
			  <a class="blog-header-logo text-dark" href="index.jsp">
			    CatBoard
			  </a>
			  <div class="collapse navbar-collapse float-right" id="navbarNavDropdown" style = "padding:15px;">
			    <ul class="navbar-nav ">
			    
			      <li class="nav-item ">
			        <a class="nav-link" href="BoardList.do?name=catinfo&num=1"> 고양이 지식</a>
			      </li>
			      <li class="nav-item">
			       <%
						if (session.isNew() || session.getAttribute("id") == null) {
					%>
					<a class="nav-link" style="cursor: pointer;"
						onClick="alert('로그인을 해주세요.');"> 고양이 입양</a>
					<%
						} else {
					%>
					<a class="nav-link" href="BoardList.do?name=catadopt&num=1"> 고양이 입양</a>
					<%
						}
					%>
			      </li>
			      <li class="nav-item">
			        <%
						if (session.isNew() || session.getAttribute("id") == null) {
					%>
					<a class="nav-link" style="cursor: pointer;"
						onClick="alert('로그인을 해주세요.');">고양이 Q&A</a>
					<%
						} else {
					%>
					<a class="nav-link" href="BoardList.do?name=catqa&num=1">고양이 Q&A</a>
					<%
						}
					%>
			      </li>
			    </ul>
			  </div>
			
		  <button type="button" class="btn btn-outline-dark" data-toggle="modal" data-target="#exampleModal">마이페이지
		</nav>
			<!--  마이페이지 모달  -->	 
				<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">마이페이지</h5>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					      </div>
					      <div class="modal-body">
					    	<!--  로그인 안했을 때 -->
     						<% if(session.isNew() || session.getAttribute("id") == null){%>
     						
							   <a href="index.jsp">로그인</a>을 해주세요<br>
  
							 <%} else{%> <!--  로그인 했을 때  -->
							<h5><%= session.getAttribute("id")  %> 님의 마이페이지 입니다.</h5> <br>
							<!--  로그아웃 버튼 -->
							<a href = "logout.jsp" style = "padding-top: 20px;">로그아웃 하기</a> 
							 <%} %>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					      </div>
					    </div>
					  </div>
					</div>
</body>
</html>