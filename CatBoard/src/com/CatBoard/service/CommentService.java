package com.CatBoard.service;

import java.util.ArrayList;

import com.CatBoard.dao.CommentDAO;
import com.CatBoard.vo.BoardVO;
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

	public ArrayList<CommentVO> commentList(int num) {
		ArrayList<CommentVO> list = dao.commentList(num);
		return list;
	}
	public CommentVO comment_list(String num) {
		CommentVO comment = dao.comment_list(num);
		return comment;
	}
 

}
