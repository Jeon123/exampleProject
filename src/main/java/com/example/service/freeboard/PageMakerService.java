package com.example.service.freeboard;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.FreeBoard;
import com.example.pageMaker.PageMaker;

@Service
public class PageMakerService {
	
    public PageMaker generatePageMaker(int pageNum, int contentNum, List<FreeBoard> repository) {
        PageMaker pageMaker = new PageMaker();
        
        int totalCount = (int) repository.size();
        
        /*---------페이지 객체에 새로운 정보 다시 지정해주는 부분------------------*/
        // 전체 게시글 개수 지정한다
        pageMaker.setTotalCount(totalCount);
        // 전체 페이지 개수를 지정한다.
        pageMaker.setTotalPageNum();
        // 현재 페이지를 페이지 객체에 다시 지정해준다//몇번 페이지인지 PageMaker에 세팅한다
        pageMaker.setPageNum(pageNum);
        // 한 페이지에 몇개씩 보여줄지 세팅한다
        pageMaker.setContentNum(contentNum);
        // 현재 페이지블록이 몇번인지 현재 페이지 번호를 통해서 지정한다
        pageMaker.setCurrentBlock(pageNum);
        // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다
        pageMaker.setLastblock(pageMaker.getTotalCount());
        // 현재 페이지 번호로 화살표 나타낼지 결정한다
        pageMaker.prevNext(pageNum);
        // 시작페이지 번호를 현재 페이지 블록으로 정한다
        pageMaker.setStartPage(pageMaker.getCurrentBlock());
        // 현재 블록 번호와 마지막 블록 번호를 보내서 대조하고 페이지 블록의 마지막 번호를 지정한다
        pageMaker.setEndPage(pageMaker.getLastBlock(),pageMaker.getCurrentBlock());
        
        return pageMaker;
        
    }    
}
