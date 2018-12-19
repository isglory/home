package com.pmgroup.isglory.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.pmgroup.isglory.dao.BoardDao;
import com.pmgroup.isglory.dao.BoardVO;

@Service
public class BoardService {
	@Inject
	BoardDao dao;
	//게시판 목록
	public List<BoardVO> selecList() throws Exception{
		return dao.selectList();
	}
	//게시글 저장
	public void insertBoard(BoardVO data) throws Exception {
		dao.insertBoard(data);
	}
	//상세 페이지
	public BoardVO selectOne(int no) throws Exception{
		return dao.selectOne(no);
	}
	//글 수정
	public int updateBoard(BoardVO data) throws Exception {
		return dao.updateBoard(data);
	}
	//글 삭제
	public int delete(String pageNum) {
		return dao.delteBoard(pageNum);
	}
}
