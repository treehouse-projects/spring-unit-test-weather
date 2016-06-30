package com.teamtreehouse;

import com.teamtreehouse.dao.RoleDao;
import com.teamtreehouse.dao.UserDao;
import com.teamtreehouse.domain.Role;
import com.teamtreehouse.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role role = new Role("ROLE_USER");
        roleDao.save(role);

        User user = new User("user","password");
        user.setRole(role);
        userDao.save(user);
    }
}
