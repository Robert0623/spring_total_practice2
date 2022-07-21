package com.myportfolio.web.service;

import com.myportfolio.web.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardService {
    int getCount() throws Exception;

    int write(BoardDto dto) throws Exception;

    BoardDto read(Integer bno) throws Exception;

    int modify(BoardDto dto) throws Exception;

    int remove(Integer bno, String writer) throws Exception;

    List<BoardDto> getList() throws Exception;

    List<BoardDto> getPage(Map map) throws Exception;
}
