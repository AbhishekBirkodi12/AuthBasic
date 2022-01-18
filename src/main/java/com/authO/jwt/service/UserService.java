package com.authO.jwt.service;

import com.authO.jwt.dao.RoleDao;
import com.authO.jwt.dao.UserDao;
import com.authO.jwt.entity.Role;
import com.authO.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public List<User> getUsers() {
        return (List<User>) userDao.findAll();
    }

    public User getUserByUsername(String username) {
        User user= userDao.getUserByUsername(username);
        return user;

    }
    public void deleteUser( String userName) {
        userDao.deleteById(userName);
    }

    public User updateUser(User user) {
        User userNew = userDao.findById(user.getUserName()).get();
        userNew.setUserName(user.getUserName());
        userNew.setUserFirstName(user.getUserFirstName());
        userNew.setUserLastName(user.getUserLastName());
        userNew.setUserPassword(getEncodedPassword(user.getUserPassword()));
        return userDao.save(userNew);

    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
