package com.CatBoard.controller;



import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.CatBoard.service.CommentService;
import com.CatBoard.vo.CommentVO;

public class CommentListController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String num =request.getParameter("num");
		
		HttpUtil.forward(request, response, "/article.jsp");
	}
}
