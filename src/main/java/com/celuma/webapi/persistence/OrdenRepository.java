package com.celuma.webapi.persistence;

import com.celuma.webapi.domain.OrderDTO;
import com.celuma.webapi.domain.OrderDetailDTO;
import com.celuma.webapi.domain.repository.OrderRepository;
import com.celuma.webapi.persistence.crud.OrdenCrudRepository;
import com.celuma.webapi.persistence.entity.Orden;
import com.celuma.webapi.persistence.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrdenRepository implements OrderRepository {

    @Autowired
    private OrdenCrudRepository ordenCrudRepository;
    @Autowired
    private OrderMapper mapper;

    @Override
    public List<OrderDTO> getAll() {
        List<Orden> ordenes = (List<Orden>) ordenCrudRepository.findAll();
        return mapper.toOrders(ordenes);
    }

    @Override
    public Optional<OrderDetailDTO> getOrder(int orderId) {
        return ordenCrudRepository.findById(orderId).map(orden -> mapper.toOrder(orden));
    }

    @Override
    public OrderDetailDTO save(OrderDetailDTO orderDetailDTO) {
        return null;
    }

    @Override
    public void delete(int order) {

    }
}
