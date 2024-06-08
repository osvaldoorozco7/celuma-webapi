package com.celuma.webapi.persistence.crud;

import com.celuma.webapi.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<User, Integer> {

}
