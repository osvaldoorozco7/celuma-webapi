package com.celuma.webapi.persistence.crud;

import com.celuma.webapi.persistence.entity.Orden;
import org.springframework.data.repository.CrudRepository;

public interface OrdenCrudRepository extends CrudRepository<Orden, Integer> {
}
