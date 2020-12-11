package com.CatBoard.controller;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.CatBoard.service.BoardService;
import com.CatBoard.vo.BoardVO;

public class BoardDeleteController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Parameter 추출 
		String preBoardNum = request.getParameter("board_num");
		Integer board_num = Integer.parseInt(preBoardNum);
		String board_id = request.getParameter("board_id");
//		String category = request.getParameter("name");

		
		String path = null;
		path = "/CatBoard/BoardList.do?name="+board_id+"&num=1"; // 셀렉트 박스에서 카테고리 선택시 선택한 카테고리.jsp로 이동
		
		// Service 객체의 메서드 호출
		BoardService service = BoardService.getInstance();
		service.boardDelete(board_num);

		// Output View 페이지로 이동
		HttpUtil.redirect(request, response, path);
		

	}
}
