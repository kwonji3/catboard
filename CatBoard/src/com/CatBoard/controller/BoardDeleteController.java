package com.CatBoard.controller;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.CatBoard.service.BoardService;
import com.CatBoard.vo.BoardVO;

public class BoardDeleteController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Parameter 추출 제목 추출? 이거 기본키만 뽑아내야하는건가? 그럼 board_num?
		String board_num = request.getParameter("board_num");
		String board_id = request.getParameter("board_id");
		String category = request.getParameter("name");

		System.out.println(board_num);

		
		String path = null;
		path = "/BoardList.do?name="+board_id; // 셀렉트 박스에서 카테고리 선택시 선택한 카테고리.jsp로 이동
		
		// Service 객체의 메서드 호출
		BoardService service = BoardService.getInstance();
		service.boardDelete(board_num);

		BoardService service1 = BoardService.getInstance();
		ArrayList<BoardVO> list = service1.boardList(category);
		
		request.setAttribute("list", list);
		HttpUtil.forward(request, response, path);
		
//		// Output View 페이지로 이동
//		HttpUtil.forward(request, response, "/cat_info.jsp"); //path 지정해야하나?

	}
}
