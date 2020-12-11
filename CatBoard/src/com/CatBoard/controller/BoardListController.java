package com.CatBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.CatBoard.service.BoardService;
import com.CatBoard.vo.BoardVO;
import com.CatBoard.vo.Page;

public class BoardListController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 파라미터 추출
		String category = request.getParameter("name");
		String preNum = request.getParameter("num");
		Integer num = Integer.parseInt(preNum);

		BoardService service = BoardService.getInstance();

		// 페이지 네이션
		Page page = new Page();
		page.setNum(num);
		page.setCount(service.getBoardCount(category));

		ArrayList<BoardVO> list = service.boardList(category, page.getDisplayPost(), page.getPostNum());

		// path설정
		String path = null;
		if (category.equals("catinfo"))
			path = "/cat_info.jsp";
		else if (category.equals("catadopt"))
			path = "/cat_adopt.jsp";
		else if (category.equals("catqa"))
			path = "/cat_qa.jsp";

		request.setAttribute("select", num);
		request.setAttribute("page", page);
		request.setAttribute("list", list);
		request.setAttribute("category", category);

		HttpUtil.forward(request, response, path);
	}
}
