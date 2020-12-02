package com.CatBoard.controller;



import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.CatBoard.service.MemberService;
import com.CatBoard.vo.MemberVO;

public class MemberInsertController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Parameter 추출
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
	

		// 유효성 체크
		if (id.isEmpty() || passwd.isEmpty() ) {
			request.setAttribute("error", "모든 항목을 빠짐없이 입력해주시기 바랍니다!");
			HttpUtil.forward(request, response, "/memberInsert.jsp");
			return;
		}

		// VO객체에 데이타 바인딩
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPasswd(passwd);

		// Service 객체의 메서드 호출
		MemberService service = MemberService.getInstance();
		service.memberInsert(member);

		// Output View 페이지로 이동
		request.setAttribute("id", id);
		HttpUtil.forward(request, response, "index.jsp");
	}
}
