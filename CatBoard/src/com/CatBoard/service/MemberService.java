package com.CatBoard.service;



import java.util.ArrayList;

import com.CatBoard.dao.MemberDAO;
import com.CatBoard.vo.BoardVO;
import com.CatBoard.vo.MemberVO;

public class MemberService {

	private static MemberService service = new MemberService();
	public MemberDAO dao = MemberDAO.getInstance();
	
	private MemberService(){}

	public static MemberService getInstance() {
		return service;
	}

	public void memberInsert(MemberVO member) {
		dao.memberInsert(member);
	}

	public int memberLogin(String id,String pw) {
		int member = dao.memberLogin(id,pw);
		return member;
	}

//	public void memberUpdate(MemberVO member) {
//		dao.memberUpdate(member);
//	}

	public void memberDelete(String id) {
		dao.memberDelete(id);
	}

	public ArrayList<MemberVO> memberList() {
		ArrayList<MemberVO> list = dao.memberList();
		return list;
	}

}
