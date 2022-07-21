package com.myportfolio.web.dao;

import com.myportfolio.web.domain.User;

public interface UserDao {
    int deleteUser(String id) throws Exception;

    User selectUser(String id) throws Exception;

    int insertUser(User user) throws Exception;

    int updateUser(User user) throws Exception;

    int count() throws Exception;

    void deleteAll() throws Exception;
}
