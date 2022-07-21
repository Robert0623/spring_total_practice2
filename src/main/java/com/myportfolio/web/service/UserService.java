package com.myportfolio.web.service;

import com.myportfolio.web.domain.User;

public interface UserService {
    int write(User user) throws Exception;

    User read(String id) throws Exception;

    int modify(User user) throws Exception;

    int remove(String id) throws Exception;

    int getCount() throws Exception;
}
