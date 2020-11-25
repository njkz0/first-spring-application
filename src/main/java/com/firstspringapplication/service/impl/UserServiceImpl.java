package com.firstspringapplication.service.impl;

import com.firstspringapplication.dao.UserDAO;
import com.firstspringapplication.model.Profile;
import com.firstspringapplication.model.User;
import com.firstspringapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImpl implements UserService {


    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User save(User user) {
        if (user.getId() == null && userDAO.findAllByLogin(user.getLogin()).isEmpty()) {
            user.setProfile(Profile.CLIENT);
            return userDAO.save(user);
        }
        throw new RuntimeException("User already excist");
    }

    @Override
    public User update(User user) {
        if (user.getId() != null && userDAO.findById(user.getId()) != null) {
            return userDAO.save(user);
        }
        throw new RuntimeException("Cannot update excist");
    }

    @Override
    public User findOne(Integer id) {
        Optional<User> user = userDAO.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else return null;

    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findOneByLoginAndPassword(String login, String password) {
        List<User> users = userDAO.findByLoginAndPassword(login, password);
        if (users.size() == 1) {
            return users.get(0);
        } else throw new RuntimeException("Cant find user");
    }


    @Override
    public void delete(Integer id) {
        userDAO.deleteById(id);
    }
}
