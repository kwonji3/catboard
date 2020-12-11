package com.CatBoard.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.CatBoard.vo.BoardVO;

public class BoardDAO {

	private static BoardDAO dao = new BoardDAO();

	private BoardDAO() {
	}

	String passwd;

	public static BoardDAO getInstance() {
		return dao;
	}

	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mariadb://gsitm-intern2020.c5tdqadv8vmd.ap-northeast-2.rds.amazonaws.com/it1451", "it1451",
					"it1451");
		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
		}
		return conn;
	}

	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				System.out.println("오류 발생 : " + ex);
			}
		}
		close(conn, ps);
	} // close

	public void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception ex) {
				System.out.println("오류 발생 : " + ex);
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception ex) {
				System.out.println("오류 발생 : " + ex);
			}
		}
	} // close

	// 글 작성
	public void boardInsert(BoardVO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect(); // 조인하기 -> id값 불러오기
			pstmt = conn.prepareStatement("insert into board (board_id,id,title,content) values(?,?,?,?)");
			pstmt.setString(1, board.getBoard_id());
			pstmt.setString(2, board.getId());
			pstmt.setString(3, board.getTitle());
			pstmt.setString(4, board.getContent());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}

	// 글 수정
	public void boardUpdate(BoardVO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement(
					"update board set  title=?,content=?,MODIFY_TIME=? where board_num=?");
//			pstmt.setString(1, board.getBoard_id());
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getMODIFY_TIME());
			pstmt.setInt(4, board.getBoard_num());
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
		} finally {
			close(conn, pstmt);
		}

	}

	// 글 삭제
	public void boardDelete(int board_num) { // id 받아오기 근데 이거 board_num이여야 하지 않을까..
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("delete from board where board_num=?");
			pstmt.setInt(1, board_num);
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}

	// 글 목록 보기
	public ArrayList<BoardVO> boardList(String category, int display, int disNum) {

		ArrayList<BoardVO> list = new ArrayList<BoardVO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		BoardVO board = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement(
					"select board_num,board_id,id,title,CREATE_TIME from board where board_id = ? order by board_num desc limit ?, ?");
			pstmt.setString(1, category);
			pstmt.setInt(2, display);
			pstmt.setInt(3, disNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				board = new BoardVO();
				board.setBoard_num(rs.getInt(1)); // board_num
				board.setBoard_id(rs.getString(2)); // board_id
				board.setId(rs.getString(3)); // id
				board.setTitle(rs.getString(4)); // title
				board.setCREATE_TIME(new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp(5)));

				list.add(board);
			}

		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
		} finally {
			close(conn, pstmt, rs);
		}

		return list;
	}

	// 글 상세보기
	public BoardVO boardArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		BoardVO board = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement(
					"select board_num, board_id, id, title, content, CREATE_TIME from board where board_num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new BoardVO();
				board.setBoard_num(rs.getInt(1));
				board.setBoard_id(rs.getString(2));
				board.setId(rs.getString(3));
				board.setTitle(rs.getString(4));
				board.setContent(rs.getString(5));
				board.setCREATE_TIME(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(rs.getTimestamp(6)));

			}
			System.out.println(board.getCREATE_TIME());

		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
		} finally {
			close(conn, pstmt, rs);
		}

		return board;
	}

	public int getBoardCount(String boardId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select count(*) from board where board_id = ?");
			pstmt.setString(1, boardId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
		} finally {
			close(conn, pstmt, rs);
		}
		return -1; // 에러
	}
}
//	
