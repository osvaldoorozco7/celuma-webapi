package com.celuma.webapi.persistence.crud;

import com.celuma.webapi.persistence.entity.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<User, Integer> {
    @Query("select u from User u where u.username = ?1")
    public User getByUsername(String username);
    @Query("select u from User u where u.email = ?1")
    public User getByEmail(String email);
}
