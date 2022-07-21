package com.myportfolio.web.dao;

import com.myportfolio.web.domain.User;

public interface UserDao {
    int insert(User user) throws Exception;

    User select(String id) throws Exception;

    int update(User user) throws Exception;

    int delete(String id) throws Exception;

    int count() throws Exception;

    int deleteAll() throws Exception;
}
