package com.CatBoard.controller;



import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.CatBoard.service.BoardService;
import com.CatBoard.service.CommentService;
import com.CatBoard.vo.BoardVO;
import com.CatBoard.vo.CommentVO;

public class BoardListController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category = request.getParameter("name");
		String num = request.getParameter("num");
		System.out.println(category);
		
		String path = null;
		if (category.equals("고양이지식"))
			path = "/cat_info.jsp";
		else if (category.equals("고양이입양"))
			path = "/cat_adopt.jsp";
		else if (category.equals("고양이질문"))
			path = "/cat_qa.jsp";	
				
		
		BoardService service = BoardService.getInstance();
		ArrayList<BoardVO> list = service.boardList(category);
		

		request.setAttribute("list", list);

		HttpUtil.forward(request, response, path);
	}
}
