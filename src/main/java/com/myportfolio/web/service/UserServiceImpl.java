package com.myportfolio.web.service;

import com.myportfolio.web.dao.UserDao;
import com.myportfolio.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public int write(User user) throws Exception {
        return userDao.insert(user);
    }
    @Override
    public User read(String id) throws Exception {
        return userDao.select(id);
    }
    @Override
    public int modify(User user) throws Exception {
        return userDao.update(user);
    }
    @Override
    public int remove(String id) throws Exception {
        return userDao.delete(id);
    }
    @Override
    public int getCount() throws Exception {
        return userDao.count();
    }
}
