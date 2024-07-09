package com.celuma.webapi.web.controller;

import com.celuma.webapi.domain.OrderDTO;
import com.celuma.webapi.domain.OrderDetailDTO;
import com.celuma.webapi.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    private String home() {
        return "Home page de las ordenes";
    }

    @GetMapping("/all")
    public List<OrderDTO> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<OrderDetailDTO> getById(@PathVariable("id") Integer orderId) {
        return orderService.getOrder(orderId);
    }

    @PostMapping("/save")
    public OrderDetailDTO save(@RequestBody OrderDetailDTO orderDetailDTO) {
        return orderService.save(orderDetailDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int orderId) {
        orderService.delete(orderId);
    }

}
