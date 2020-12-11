package com.CatBoard.vo;

public class Page {
	// 현재 페이지 번호
	private int num;

	// 게시물 총 갯수
	private int count;

	// 한 페이지에 출력할 게시물 갯수
	private int postNum = 5;

	// 하단 페이징 번호 ([ 게시물 총 갯수 ÷ 한 페이지에 출력할 갯수 ]의 올림)
	private int pageNum;

	// 출력할 게시물
	private int displayPost;

	// 한번에 표시할 페이징 번호의 갯수
	private int pageNumCnt = 5;

	// 표시되는 페이지 번호 중 마지막 번호
	private int endPageNum;

	// 표시되는 페이지 번호 중 첫번째 번호
	private int startPageNum;

	// 다음/이전 표시 여부
	private boolean prev;
	private boolean next;

	public void setNum(int num) {
		this.num = num;
	}

	public void setCount(int count) {
		this.count = count;

		dataCalc();
	}

	public int getCount() {
		return count;
	}

	public int getPostNum() {
		return postNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getDisplayPost() {
		return displayPost;
	}

	public int getPageNumCnt() {
		return pageNumCnt;
	}

	public int getEndPageNum() {
		return endPageNum;
	}

	public int getStartPageNum() {
		return startPageNum;
	}

	public boolean getPrev() {
		return prev;
	}

	public boolean getNext() {
		return next;
	}

	private void dataCalc() {
		// 마지막 번호 = num (=1) 을 5로 나눈후 올림 = 1 * 5 -> 마지막 번호는 5가됨
		endPageNum = (int) (Math.ceil((double) num / (double) pageNumCnt) * pageNumCnt);
;
		// 시작 번호 = 5-(5-1) = 1 -> 시작번호 1
		startPageNum = endPageNum - (pageNumCnt - 1);

		// 마지막 번호 재계산 = 모든 글 개수 count / 5(한 페이지 출력 개수) 후 올림 , 만약 모든글이 6개면 6/5= 1.2 올려서 2.
		int endPageNum_tmp = (int) (Math.ceil((double) count / (double) postNum));//

		// 마지막 번호 5 가 2보다 크니까 
		if (endPageNum > endPageNum_tmp) {
			// 마지막 번호는 2로 재설정 (글 개수에 따라 마지막 페이지 숫자가 2가 됨)
			endPageNum = endPageNum_tmp;
		}

		// 이전 = 시작번호가 = 1 이면 false 아니면 true
		//prev에 false 저장시 이전 버튼 X
		prev = startPageNum == 1 ? false : true;
		
		// 다음 = 2 * 5 >= 6 -> false 반환 아니면 true
		//next에 false 저장시 이전 버튼 X
		next = endPageNum * postNum >= count ? false : true;//

		// 출력할 게시물 = num = 2면 postNum은 5니까 1*5해서 5개의 게시물이 나옴
		displayPost = (num - 1) * postNum;

	}
}
