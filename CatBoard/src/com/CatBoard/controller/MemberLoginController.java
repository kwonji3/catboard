package com.CatBoard.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.CatBoard.service.MemberService;
import com.CatBoard.vo.MemberVO;

public class MemberLoginController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Parameter 추출
		String id = request.getParameter("id");
		String pw = request.getParameter("passwd");


		// Service 객체의 메서드 호출
		MemberService service = MemberService.getInstance();
		int result = service.memberLogin(id,pw);
		
		if(result == 1) { //로그인 완료
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("id", id); //id를 세션에 저장
			HttpUtil.forward(request, response, "/index.jsp");
	
		}else { // 로그인안됨
			request.setAttribute("alert", "아이디/비밀번호가 틀렸습니다.");
			HttpUtil.forward(request, response, "/index.jsp");
			System.out.println("로그인 안됨");
		}
	



	}
}
