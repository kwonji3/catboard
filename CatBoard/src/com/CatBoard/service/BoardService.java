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


	public void boardUpdate(BoardVO board) {
		dao.boardUpdate(board);
	}

	public void boardDelete(int board_num) {
		dao.boardDelete( board_num);
	}

	public ArrayList<BoardVO> boardList(String category, int display, int disNum) {
		ArrayList<BoardVO> list = dao.boardList(category, display, disNum );
		return list;
	}
	public BoardVO boardArticle(int num) {
		BoardVO board = dao.boardArticle(num);
		return board;
	}
	
	//게시판 총 개수
	public int getBoardCount(String boardId) {
		return dao.getBoardCount(boardId);
	}

}
