package com.pmgroup.isglory.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.pmgroup.isglory.common.ConfigurePages;
import com.pmgroup.isglory.dao.BoardDao;
import com.pmgroup.isglory.dao.BoardVO;

@Service
public class BoardService {
	@Inject
	BoardDao dao;
	
	ConfigurePages page = new ConfigurePages();
	
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
	public int delete(String pageNum) throws Exception {
		return dao.delteBoard(pageNum);
	}
	//페이징 관련 시작, 전체 레코드 수
	public int getTotalPage() throws Exception {
		return dao.getTotalPage();
	}
	//페이징 처리된 게시판 목록
	public List<BoardVO> selecListByPage(ConfigurePages page) throws Exception{
		return dao.getListByPage(page);
	}
	//페이징
	public ConfigurePages setPage(
			String tblName,int numPerPage,int totalPage,int numPerBlock, int pageNum) {	
			return page.setting(tblName, numPerPage, totalPage, numPerBlock, pageNum);
		}
}
