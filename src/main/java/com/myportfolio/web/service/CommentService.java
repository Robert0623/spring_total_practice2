package com.myportfolio.web.service;

import com.myportfolio.web.domain.CommentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {
    int getCount(Integer bno) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int write(CommentDto dto) throws Exception;

    CommentDto read(Integer cno) throws Exception;

    int modify(CommentDto dto) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int remove(Integer cno, Integer bno, String commenter) throws Exception;

    List<CommentDto> getList(Integer bno) throws Exception;
}
