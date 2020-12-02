package com.CatBoard.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.CatBoard.service.BoardService;
import com.CatBoard.vo.BoardVO;

public class BoardUpdateController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Parameter 추출
		String id = request.getParameter("id"); // id = 지은
		String board_id= request.getParameter("board_id"); //board_id  =  지식, 입양, 질문
		String title = request.getParameter("bbsTitle"); //title
		String content = request.getParameter("bbsContent"); //content
		String board_num = request.getParameter("board_num"); //content
		

		
		// VO객체에 데이타 바인딩
		BoardVO board = new BoardVO();
		board.setId(id);
		board.setBoard_id(board_id);
		board.setTitle(title);
		board.setContent(content);
		board.setBoard_num(board_num);

		// Service 객체의 메서드 호출
		BoardService service = BoardService.getInstance();
		service.boardUpdate(board);

		// Output View 페이지로 이동
		request.setAttribute("board", board);
		HttpUtil.forward(request, response, "/article.jsp");
	}
}
