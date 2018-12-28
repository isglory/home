package com.pmgroup.isglory.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.pmgroup.isglory.common.ConfigurePages;

@Repository
public class BoardDao {
	@Inject
	private SqlSession sqlSession;
	
	private static final String NameSpace = "com.pmgroup.isglory.mappers.boardMapper";
	
	//list 페이지
	public List<BoardVO> selectList() throws Exception {
		return sqlSession.selectList(NameSpace+".selectList");
	}
	//게시판 입력
	public int insertBoard(BoardVO data) throws Exception {
		return sqlSession.insert(NameSpace+".insertBoard",data);
	}
	//상세페이지
	public BoardVO selectOne(int no) throws Exception {
		return sqlSession.selectOne(NameSpace+".selectOne", no);
	}
	//게시글 수정
	public int updateBoard(BoardVO data) throws Exception {
		return sqlSession.update(NameSpace+".updateBoard", data);
	}
	//게시글 삭제
	public int delteBoard(String pageNum) throws Exception {
		return sqlSession.delete(NameSpace+".deleteBoard",Integer.parseInt(pageNum));
	}
	
	/*
	 * 페이징 관련 시작
	 * 총 게시물 수
	 * .select 언떤 메서드를 사용해야 하는지 확인
	 */
	public int getTotalPage() throws Exception {
		return sqlSession.selectOne(NameSpace+".selectTotalRow");
	}
	//페이징 처리한 리스트
	public List<BoardVO> getListByPage(ConfigurePages page) throws Exception {
		return sqlSession.selectList(NameSpace+".selectListByPage", page);
	}
}
