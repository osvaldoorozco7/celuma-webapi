package com.celuma.webapi.domain.service;

import com.celuma.webapi.domain.OrderDTO;
import com.celuma.webapi.domain.OrderDetailDTO;
import com.celuma.webapi.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDTO> getAll(){
        return orderRepository.getAll();
    }

    public Optional<OrderDetailDTO> getOrder(int orderId) {
        return orderRepository.getOrder(orderId);
    }

    public OrderDetailDTO save(OrderDetailDTO orderDetailDTO) {
        return orderRepository.save(orderDetailDTO);
    }

    public void delete(int orderId) {
        orderRepository.delete(orderId);
    }

}
