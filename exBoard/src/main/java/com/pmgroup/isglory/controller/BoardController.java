package com.pmgroup.isglory.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pmgroup.isglory.dao.BoardVO;
import com.pmgroup.isglory.service.BoardService;

//모의 게시판
@Controller
public class BoardController {
	Logger log = Logger.getLogger(this.getClass());
	@Inject
	private BoardService service;

	// 리스트
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardList(Model model) throws Exception {
		log.debug("리스트페이지 로그 테스트");

		List<BoardVO> data = service.selecList();
		model.addAttribute("boardList", data);

		return "list";
	}

	// 작성폼
	@RequestMapping(value = "/writeform", method = RequestMethod.GET)
	public String writeFrom() {
		return "writeform";
	}

	// 글 저장
	@RequestMapping(value = "/writeform", method = RequestMethod.POST)
	public String writeSave(Model model, String title, String writer, String contents) throws Exception {

		BoardVO board = new BoardVO();
		// 리퀘스트 데이터 vo에 맵핑
		board.setTitle(title); // 제목
		board.setWriter(writer); // 작성자
		board.setContents(contents); // 컨텐츠
		board.setHit_cnt(0); // 조회수, 글이 작성되는 시점
		service.insertBoard(board);

		List<BoardVO> data = service.selecList();
		model.addAttribute("boardList", data);

		return "list";
	}

	/*
	 * 글 수정 request 파라미터로 작성했음
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, HttpServletRequest request) throws Exception {
		// TODO 수정할 객체 내용 설정
		BoardVO boardVO = new BoardVO();
		boardVO.setIdx(Integer.parseInt(request.getParameter("idx"))); // 글번호
		boardVO.setContents(request.getParameter("contents")); // 내용
		boardVO.setTitle(request.getParameter("title")); // 제목
		boardVO.setHit_cnt(Integer.parseInt(request.getParameter("hit_cnt"))); // 조회수 , TODO 조회수가 늘어나도록 구현
		boardVO.setWriter(request.getParameter("writer")); // 작성자

		System.out.println(boardVO.toString());

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
	public String del(HttpServletRequest request) {
		String pageNum = request.getQueryString();
		System.out.println(pageNum);
		service.delete(pageNum);
		return "redirect:list";
	}
}
