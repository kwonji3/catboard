package com.CatBoard.vo;


public class BoardVO {

	
	
	private String board_num;
	private String board_id;
	private String id;
	private String title;
	private String content;
	private String CREATE_TIME;
	private String MODIFY_TIME;



	public String getMODIFY_TIME() {
		return MODIFY_TIME;
	}
	public void setMODIFY_TIME(String mODIFY_TIME) {
		MODIFY_TIME = mODIFY_TIME;
	}
	public String getCREATE_TIME() {
		return CREATE_TIME;
	}
	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	
	public String getBoard_num() {
		return board_num;
	}
	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	

}
