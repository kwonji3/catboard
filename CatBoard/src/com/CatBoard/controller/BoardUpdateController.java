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
		String board_id = request.getParameter("select"); // board_id = catinfo, catadopt, catqa
		String title = request.getParameter("bbsTitle"); // title
		String content = request.getParameter("bbsContent"); // content

		// 게시판 번호
		String preBoardNum = request.getParameter("board_num");
		Integer board_num = Integer.parseInt(preBoardNum);

		// VO객체에 데이타 바인딩
		BoardVO board = new BoardVO();
		board.setId(id);
		board.setBoard_id(board_id);
		board.setTitle(title);
		board.setContent(content);
		board.setBoard_num(board_num);

		// path 설정
		String path = null;
		path = "/CatBoard/qa_article.do?num=" + board_num ;

		// Service 객체의 메서드 호출
		BoardService service = BoardService.getInstance();
		service.boardUpdate(board);

		// Output View 페이지로 이동
		request.setAttribute("board", board);
		HttpUtil.redirect(request, response, path);
	}
}
