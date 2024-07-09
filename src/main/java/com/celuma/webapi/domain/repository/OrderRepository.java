package com.celuma.webapi.domain.repository;

import com.celuma.webapi.domain.OrderDTO;
import com.celuma.webapi.domain.OrderDetailDTO;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    List<OrderDTO> getAll();

    Optional<OrderDetailDTO> getOrder(int orderId);

    OrderDetailDTO save(OrderDetailDTO orderDetailDTO);

    void delete(int order);

}
