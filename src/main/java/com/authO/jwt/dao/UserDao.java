package com.authO.jwt.dao;

import com.authO.jwt.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface UserDao extends CrudRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.userName =:username")
    public User getUserByUsername(@RequestParam("username") String username);

}
