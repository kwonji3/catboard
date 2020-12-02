package com.CatBoard.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import com.CatBoard.vo.CommentVO;

public class CommentDAO {

	private static CommentDAO dao = new CommentDAO();
	private CommentDAO(){}

	public static CommentDAO getInstance() {
		return dao;
	}

	public Connection connect() {
		Connection conn = null;
		try {
			 Class.forName("org.mariadb.jdbc.Driver");
	         conn = DriverManager.getConnection("jdbc:mariadb://gsitm-intern2020.c5tdqadv8vmd.ap-northeast-2.rds.amazonaws.com/it1451", "it1451", "it1451");
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

	//댓글 인서트
	public void commentInsert(CommentVO comment) { 
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into comment (board_num,id,cmt) value (?,?,?)");
			pstmt.setString(1, comment.getBoard_num());
			pstmt.setString(2, comment.getId());
			pstmt.setString(3, comment.getCmt());
			pstmt.executeQuery();
		
	
		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}


	//댓글 리스트
	public ArrayList<CommentVO> commentList(String num) {

		ArrayList<CommentVO> list = new ArrayList<CommentVO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		CommentVO comment = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select board_num,id,cmt,CREATE_TIME from comment where board_num=?");
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				comment = new CommentVO();
				comment.setBoard_num(rs.getString(1));
				comment.setId(rs.getString(2));
				comment.setCmt(rs.getString(3));
				comment.setCREATE_TIME(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(rs.getTimestamp(4))); 
				list.add(comment);
			}

		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
		} finally {
			close(conn, pstmt, rs);
		}

		return list;
	}
}
//	
