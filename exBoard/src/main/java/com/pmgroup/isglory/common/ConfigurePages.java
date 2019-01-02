package com.pmgroup.isglory.common;

public class ConfigurePages {
	
	private int totalCount; //전체 게시물 수
	private int pageNum; //현재 페이지 번호
	private int currentBlock; //현재페이지 블록
	private int numPerPage; //한 페이지에 표시되는 게시글 수
	private int startPage; //현재 블록 시작 페이지
	private int endPage; //현재 블록 마지막 페이지
	
	private int lastBlock; //마지막페이지 블록
	private int numPerBlock; //한 블록당 페이지수 초기값  10으로 설정 
	
	private boolean prev; //이전 페이지 화살표
	private boolean next; //다음페이지 화살표
	
	private int totalPage;
	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalCount, int numPerPage) {
		this.totalPage = totalCount/numPerPage; ////전체 게시글 수 / 한 페이지당 게시글 수
		if(totalCount%numPerPage >0) {totalPage++;} // 12/10 = 1.2 .. 2페이지 필요
	}

	//검색어
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	/*
	 * 이전, 다음 버튼 설정
	 * 처음블록- 이전버튼 비활성 
	 * 마지막블록- 다음버튼 비활성
	 */
	public void prevNext(int pageNum) {
		if(pageNum<=numPerBlock) {
			setPrev(false);
			if(totalPage>numPerBlock) {
				setNext(true);				
			}else {
				setNext(false);
			}
		}else if(getLastBlock() == getCurrentBlock()) {
			setPrev(true);
			setNext(false);
		}else {
			setPrev(true);
			setNext(true);
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getStartPage() {
		return startPage;
	}
	
	//현재블록 시작 페이지 설정
	public void setStartPage(int currentBlock) {
		this.startPage = (currentBlock*numPerBlock)-(numPerBlock-1); 
		/*
		 * 현재 블록:3, 블록당 페이지수:5 로가정
		 * 3*5 = 15, 15 - (5-1) = 11 (현재 블록 처음페이지) 
		 */
	}

	public int getEndPage() {
		return endPage;
	}
	//현재블록 마지막 페이지 설정
	public void setEndPage(int lastBlock, int currentBlock) {
		if(lastBlock == currentBlock) {
			this.endPage = totalPage;
		}else {
			this.endPage = getStartPage()+(numPerBlock-1);
		}
		/*
		 * 두가지 경우 : 다음 블록이 있을경우 / 마지막 블록인경우
		 * 
		 */
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

	public int getCurrentBlock() {
		return currentBlock;
	}
	//현재 페이지 번호를 통해 현재 페이지 블록을 구한다
	public void setCurrentBlock(int pageNum) {
		this.currentBlock = pageNum/numPerBlock;
		if(pageNum%numPerBlock>0) {
			this.currentBlock++;
		}
		/*
		 * 페이지번호/표시블록개수 
		 * 7page/ 10개 .. 0.7
		 * int .. 0.7+1 
		 * 현재페이지 : 1
		 */
	}

	public int getLastBlock() {
		return lastBlock;
	}
	//마지막블록 설정
	public void setLastBlock(int totalCount) {
		
		this.lastBlock = totalCount / (numPerBlock*numPerPage);
		if(totalCount%(numPerBlock*numPerPage)>0) {
			this.lastBlock++;
		}
		/*
		 * 총게시글 / 페이지당 게시글 * 블록개수
		 * 125 / 10 * 10
		 * 마지막 블록은 2
		 */
	}	
	
	//페이지 세팅
	public ConfigurePages setting(int numPerPage, int totalCount, int numPerBlock, int pageNum, String keyword){
		
		
		this.totalCount=totalCount; //총게시글 개수 
		this.numPerPage=numPerPage; //한 페이지에 표시될 게시글 수
		this.numPerBlock=numPerBlock;
		//쿼리 계산을 위해
		this.pageNum=(pageNum-1)*numPerPage; //현재 페이지를 페이지 객체에 지정 쿼리를 위해 -1
		this.setTotalPage(totalCount, numPerPage);//전체레코드수와 페이지에 표시될 레코드수로 전체 페이지 개수구함
		this.setCurrentBlock(pageNum); //현재 페이지 -> 현재블록 계산	
		this.setLastBlock(totalCount); //전체게시글 -> 마지막블록설정
		
		this.setStartPage(this.getCurrentBlock()); //현재 블럭기준 시작 페이지설정
		this.setEndPage(this.getLastBlock(), getCurrentBlock()); //현재 블록기준 마지막 페이지
		
		this.prevNext(pageNum); //현재페이지로 이전, 다음 페이지 설정
		this.setKeyword(keyword);
		
		return this;
	}
	
	
	
	
	
}
