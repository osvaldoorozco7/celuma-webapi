package com.celuma.webapi.persistence.crud;

import com.celuma.webapi.persistence.entity.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserCrudRepository extends CrudRepository<User, Integer> {
    // @Query("select u from users where u.email = ?1 and u.password = ?2")
    // public User getByEmailAndPassword(@Param("email") String email, String password);
}
