package com.CatBoard.controller;


import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.CatBoard.service.BoardService;
import com.CatBoard.service.CommentService;
import com.CatBoard.vo.BoardVO;
import com.CatBoard.vo.CommentVO;

public class CommentQInsertController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Parameter 추출
	
		String cmt = request.getParameter("cmt");
		String id = request.getParameter("id");
		String num =request.getParameter("num");
		
		System.out.println(id);
		System.out.println(num);

		String path = null;
		path = "/qa_article.do?num="+num; // 셀렉트 박스에서 카테고리 선택시 선택한 카테고리.jsp로 이동
		
		// VO객체에 데이타 바인딩
		CommentVO comment = new CommentVO();
		comment.setBoard_num(num);
		comment.setCmt(cmt);
		comment.setId(id);
		

		// Service 객체의 메서드 호출
		CommentService service = CommentService.getInstance();
		service.commentInsert(comment);
		
		ArrayList<CommentVO> list = service.commentList(num);
		request.setAttribute("comment", list);
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("id", id); //id를 세션에 저장

		HttpUtil.forward(request, response, path);
	}
}
