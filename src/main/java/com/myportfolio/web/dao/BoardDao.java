package com.myportfolio.web.dao;

import com.myportfolio.web.domain.BoardDto;
import com.myportfolio.web.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    int count() throws Exception;

    int insert(BoardDto dto) throws Exception;

    BoardDto select(Integer bno) throws Exception;

    int update(BoardDto dto) throws Exception;

    int delete(Integer bno, String writer) throws Exception;

    int increaseViewCnt(Integer bno) throws Exception;

    List<BoardDto> selectAll() throws Exception;

    List<BoardDto> selectPage(Map map) throws Exception;

    int deleteAll() throws Exception;

    List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;

    int searchResultCnt(SearchCondition sc) throws Exception;

    int updateCommentCnt(Integer bno, Integer cnt) throws Exception;
}
