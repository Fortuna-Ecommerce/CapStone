package com.capstone.ecommerce.services;

import com.capstone.ecommerce.model.Order;
import com.capstone.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService
{
    @Autowired
    OrderRepository orderRepository;

    public Order getOrder(String orderNumber)
    {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    public List<Order> getAllOrders()
    {
        Sort sort = new Sort(Sort.Direction.DESC, "createdOn");
        return orderRepository.findAll(sort);
    }

    public Order updateOrder(Order order) {
        Order o = getOrder(order.getOrderNumber());
        o.setStatus(order.getStatus());
        Order savedOrder = orderRepository.save(o);
        return savedOrder;
    }
}