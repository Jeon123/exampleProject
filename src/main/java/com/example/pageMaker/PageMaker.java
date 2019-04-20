package com.example.pageMaker;

public class PageMaker {
    private int totalCount;			// 전체 게시물 개수
    private int pageNum;			// 현재 페이지 번호
    private int totalPageNum;		// 전체 페이지 개수
    private int contentNum = 10;	// 한페이지에 보이는 게시글 
    private int startPage = 0;		// 현재 페이지 블록의 시작페이지
    private int endPage = 0;		// 현재 페이지 블록의 마지막 페이지
    private int currentBlock;		// 현재 페이지 블록
    private int lastBlock;			// 마지막 페이지 블록
    
    // 전체 몇페이지까지 있을지 함수로 계산한다
    public int calcPage(int totalCount, int contentNum) {
    	// 전체 게시물 수를 한 페이지당 몇개 보이는지로 나눈다
    	int totalPage = totalCount / contentNum;
    	
    	// 그리고 나머지가 있다면 1을 더해서 한 페이지 증가 시킨다
    	if (totalCount % contentNum > 0) {
    		totalPage++;
    	}

    	return totalPage;// 페이지 개수를 리턴한다
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
    
    public void setPageNum(int pageNum) {
    	this.pageNum = pageNum;
    }
    
    public int getContentNum() {
    	return contentNum ;
    }
    
    public void setContentNum(int contentNum) {
    	this.contentNum  = contentNum;
    }
    
    public int getStartPage() {
    	return startPage;
    }
    
    public void setStartPage(int currentBlock) {
    	// 한 페이지에 5개씩 보여지므로
    	// 현재 페이지 블록의 번호 * 블록당 페이지의 개수(5) - 4를 하면 시작 페이지 번호를 정할 수 있다
    	// 1 2 3 4 5
    	// 6 7 8 9 10
    	// 11 12 13
    	this.startPage = (currentBlock * 5) - 4;
    }
    
    public int getEndPage() {
    	return endPage;
    }
    
    public void setEndPage(int getLastBlock, int getCurrentBlock) {
    	// 현재 페이지 블록이 마지막 블록이면
    	if (getLastBlock == getCurrentBlock) {
    		// 페이지 개수를 끝 페이지로 지정
    		this.endPage = calcPage(getTotalCount(), getContentNum());
    	} else {
    		// 시작 페이지 + 4를 끝 페이지로 지정
    		this.endPage = getStartPage() + 4;
    	}
    }
    
    public int getCurrentBlock() {
    	return currentBlock;
    }
    
    // 현재 페이지 블록의 번호가 몇번인지 지정해주는 함수
    public void setCurrentBlock(int pageNum) {
		// 페이지 번호를 통해서 구한다.
    	// 페이지 번호 / 페이지 그룹 안의 개수
    	// 1p 1 / 5 => 0.2
    	// 5p 5 / 5 => 1 -> 나머지는 0 
    	this.currentBlock = pageNum / 5;
    	if (pageNum % 5 > 0) {
    		this.currentBlock++;
    	}
    }
    
    public int getLastBlock() {
    	return lastBlock;
    }
    
    // 마지막 페이지 블록의 번호가 뭔지 지정해주는 함수
    public void setLastblock(int totalCount) {
    	// 전체 글 개수를 사용해서 지정한다
    	// 전체 글 개수(128) / 페이지 그룹 안의 페이지 개수(5) * 한페이지당 글 개수(10)
    	// ->나머지 있으면 더하기 1한다. -> 이 결과를 마지막 블록 번호라고 부른다. last block
    	this.lastBlock = totalCount / (5 * this.contentNum );
    	if (totalCount % (5 * this.contentNum ) > 0) {
    		this.lastBlock++;
    	}
    }

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum() {
		this.totalPageNum = calcPage(getTotalCount(), getContentNum());
	} 
 
}