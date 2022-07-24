package com.myportfolio.web.service;

import com.myportfolio.web.dao.BoardDao;
import com.myportfolio.web.dao.CommentDao;
import com.myportfolio.web.domain.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;
    @Autowired
    BoardDao boardDao;

    @Override
    public int getCount(Integer bno) throws Exception {
        return commentDao.count(bno);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int write(CommentDto dto) throws Exception {
        boardDao.updateCommentCnt(dto.getBno(), 1);
        return commentDao.insert(dto);
    }
    @Override
    public CommentDto read(Integer cno) throws Exception {
        return commentDao.select(cno);
    }
    @Override
    public int modify(CommentDto dto) throws Exception {
        return commentDao.update(dto);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Integer cno, Integer bno, String commenter) throws Exception {
        boardDao.updateCommentCnt(bno, -1);
        return commentDao.delete(cno, commenter);
    }
    @Override
    public List<CommentDto> getList(Integer bno) throws Exception {
        return commentDao.selectAll(bno);
//        throw new Exception("test");
    }
}
