package com.CatBoard.service;

import java.util.ArrayList;

import com.CatBoard.dao.CommentDAO;

import com.CatBoard.vo.CommentVO;

public class CommentService {

	private static CommentService service = new CommentService();
	public CommentDAO dao = CommentDAO.getInstance();
	
	private CommentService(){}

	public static CommentService getInstance() {
		return service;
	}

	public void commentInsert(CommentVO comment) {
		dao.commentInsert(comment);
	}

//	public BoardVO memberSearch(String id,String pw) {
//		BoardVO member = dao.memberSearch(id,pw);
//		return member;
//	}

//	public void memberUpdate(BoardVO member) {
//		dao.memberUpdate(member);
//	}
//
//	public void memberDelete(String id) {
//		dao.memberDelete(id);
//	}

	public ArrayList<CommentVO> commentList(String num) {
		ArrayList<CommentVO> list = dao.commentList(num);
		return list;
	}

}
