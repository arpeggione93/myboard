package org.mhboard.web.paging;

public class Paging {

	
	private int listSize = 10; // 목록의 개수는 10개로 설정
	private int contentSize = 10; //페이지당 10개씩 띄우기
	private int page; //현재 페이지 정보
	private int range; // 현재 페이지에 몇개의 content가 있는지?
	private int listCnt; //모든 페이지에 걸쳐 총 content의 갯수?
	private int pageCnt; //전체 페이지 갯수
	private int startPage; //시작페이지
	private int startList; //시작번호
	private int endPage; //마지막페이지
	private boolean prev; //이전버튼
	private boolean next; //다음버튼
	
	
	
	
	
	
	
	public int getListSize() {
		return listSize;
	}
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	public int getContentSize() {
		return contentSize;
	}
	public void setContentSize(int contentSize) {
		this.contentSize = contentSize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getListCnt() {
		return listCnt;
	}
	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getStartList() {
		return startList;
	}
	public void setStartList(int startList) {
		this.startList = startList;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	
	public void pageInfo(int page, int range, int listCnt) {
		
		
		this.page =page;
		this.range = range;
		this.listCnt = listCnt;
		
		//전체 페이지수 

				this.pageCnt = (int) Math.ceil(listCnt/listSize);

				

				//시작 페이지

				this.startPage = (range - 1) * contentSize + 1 ;

				

				//끝 페이지

				this.endPage = range * contentSize;

						

				//게시판 시작번호

				this.startList = (page - 1) * listSize;

				

				//이전 버튼 상태

				this.prev = range == 1 ? false : true;

				

				//다음 버튼 상태

				this.next = endPage > pageCnt ? false : true;

				if (this.endPage > this.pageCnt) {

					this.endPage = this.pageCnt;

					this.next = false;

				}

		
		
		
	}
	
	
	
	
	
}
