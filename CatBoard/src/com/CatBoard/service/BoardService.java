package com.CatBoard.service;



import java.util.ArrayList;

import com.CatBoard.dao.BoardDAO;

import com.CatBoard.vo.BoardVO;

public class BoardService {

	private static BoardService service = new BoardService();
	public BoardDAO dao = BoardDAO.getInstance();
	
	private BoardService(){}

	public static BoardService getInstance() {
		return service;
	}

	public void boardInsert(BoardVO board) {
		dao.boardInsert(board);
	}

//	public BoardVO memberSearch(String id) {
//		BoardVO member = dao.memberSearch(id);
//		return member;
//	}

	public void boardUpdate(BoardVO board) {
		dao.boardUpdate(board);
	}

	public void boardDelete(String  board_num) {
		dao.boardDelete( board_num);
	}

	public ArrayList<BoardVO> boardList(String category) {
		ArrayList<BoardVO> list = dao.boardList(category);
		return list;
	}
	public BoardVO boardArticle(String num) {
		BoardVO board = dao.boardArticle(num);
		return board;
	}
 

}
