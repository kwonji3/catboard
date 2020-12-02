package com.CatBoard.controller;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.CatBoard.service.BoardService;
import com.CatBoard.vo.BoardVO;


public class BoardQArticleController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Parameter 추출
		String num = request.getParameter("num"); 

	
		// Service 객체의 메서드 호출
		BoardService service = BoardService.getInstance();
		BoardVO board = service.boardArticle(num);


		// Output View 페이지로 이동
		request.setAttribute("board", board);
		HttpUtil.forward(request, response, "qa_article.jsp");
	}
}
