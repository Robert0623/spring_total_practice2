package com.myportfolio.web.dao;

import com.myportfolio.web.domain.CommentDto;

import java.util.List;

public interface CommentDao {
    int count(Integer bno) throws Exception;

    int insert(CommentDto dto) throws Exception;

    CommentDto select(Integer cno) throws Exception;

    int update(CommentDto dto) throws Exception;

    int delete(Integer cno, String commenter) throws Exception;

    List<CommentDto> selectAll(Integer bno) throws Exception;

    int deleteAll(Integer bno) throws Exception;
}
