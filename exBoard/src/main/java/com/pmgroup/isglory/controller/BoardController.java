package com.pmgroup.isglory.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmgroup.isglory.common.ConfigurePages;
import com.pmgroup.isglory.dao.BoardVO;
import com.pmgroup.isglory.service.BoardService;

//모의 게시판
@Controller
public class BoardController {

	Logger log = Logger.getLogger(this.getClass());
	
	@Inject
	private BoardService service;
	
	/*
	 * 리스트 페이지
	 * 페이징 처리
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardList(Model model,
							@RequestParam(defaultValue="1") int pageNum,
							@RequestParam(defaultValue="") String keyword) throws Exception {
		//페이지 설정(1-페이지당 개수 2-전체 게시글수 3-페이지블록수 4-현재 페이지번호 5-검색어)
		ConfigurePages page = service.setPage(10,service.getTotalPage(keyword),10,pageNum,keyword);
		List<BoardVO> data = service.selecListByPage(page);
		
		model.addAttribute("boardList", data);
		model.addAttribute("page", page);

		return "list";
	}

	// 작성폼
	@RequestMapping(value = "/writeform", method = RequestMethod.GET)
	public String writeFrom() {
		return "writeform";
	}

	// 글 저장
	@RequestMapping(value = "/writeform", method = RequestMethod.POST)
	public String writeSave(Model model, @ModelAttribute BoardVO board) throws Exception {

		service.insertBoard(board);

		return "redirect:list";
	}

	/*
	 * 글 수정
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute BoardVO boardVO,Model model) throws Exception {
		// @ModelAttribute 적용전 코드
//		BoardVO boardVO = new BoardVO();
//		boardVO.setIdx(Integer.parseInt(request.getParameter("idx"))); // 글번호
//		boardVO.setContents(request.getParameter("contents")); // 내용
//		boardVO.setTitle(request.getParameter("title")); // 제목
//		boardVO.setHit_cnt(Integer.parseInt(request.getParameter("hit_cnt"))); // 조회수 , TODO 조회수가 늘어나도록 구현
//		boardVO.setWriter(request.getParameter("writer")); // 작성자


		service.updateBoard(boardVO);

		// 리다이렉트로 보내지 않으면 리스트데이터 안불러오는 이유 알아보기
		return "redirect:list";
	}

	// 상세페이지
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(HttpServletRequest request, Model model) throws Exception {
		BoardVO data = null;
		// 리퀘스트 쿼리로 부터 조회할 idx 번호 받아옴
		int pageNum = Integer.parseInt(request.getQueryString());
		data = service.selectOne(pageNum);
		// 조회수 증가
		int hit_cnt = data.getHit_cnt() + 1;
		data.setHit_cnt(hit_cnt);
		// 조회수 증가 - 데이터베이스에 적용
		service.updateBoard(data);
		model.addAttribute("board", data);
		return "content";

	}

	// 삭제
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public String del(HttpServletRequest request) throws Exception {
		String pageNum = request.getQueryString();
		System.out.println(pageNum);
		service.delete(pageNum);
		return "redirect:list";
	}
}
