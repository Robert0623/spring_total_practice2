package com.myportfolio.web.service;

import com.myportfolio.web.dao.BoardDao;
import com.myportfolio.web.domain.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardDao boardDao;

    @Override
    public int getCount() throws Exception {
        return boardDao.count();
    }
    @Override
    public int write(BoardDto dto) throws Exception {
        return boardDao.insert(dto);
//        throw new Exception("test");
    }
    @Override
    public BoardDto read(Integer bno) throws Exception {
        BoardDto dto = boardDao.select(bno);
        boardDao.increaseViewCnt(bno);
        return dto;
    }
    @Override
    public int modify(BoardDto dto) throws Exception {
        return boardDao.update(dto);
    }
    @Override
    public int remove(Integer bno, String writer) throws Exception {
        return boardDao.delete(bno, writer);
    }
    @Override
    public List<BoardDto> getList() throws Exception {
        return boardDao.selectAll();
    }
    @Override
    public List<BoardDto> getPage(Map map) throws Exception {
        return boardDao.selectPage(map);
    }
}
