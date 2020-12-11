package com.CatBoard.controller;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.CatBoard.service.BoardService;
import com.CatBoard.vo.BoardVO;
import com.CatBoard.controller.HttpUtil;

public class BoardInsertController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Parameter 추출
		String id = request.getParameter("id"); // id = 지은
		String board_id = request.getParameter("select"); // board_id = 1,2,3 지식, 입양, 질문
		String title = request.getParameter("bbsTitle"); // title
		String content = request.getParameter("bbsContent"); // content
		String category = request.getParameter("name"); //게시판 카테고리

		// path 설정
		String path = null;
		path = "/CatBoard/BoardList.do?name=" + board_id + "&num=1"; // 셀렉트 박스에서 카테고리 선택시 선택한 카테고리.jsp로 이동

		// VO객체에 데이타 바인딩
		BoardVO board = new BoardVO();
		board.setId(id);
		board.setBoard_id(board_id);
		board.setTitle(title);
		board.setContent(content);

		// Service 객체의 메서드 호출
		BoardService service = BoardService.getInstance();
		service.boardInsert(board);

		// output view
		HttpUtil.redirect(request, response, path);
	}
}
