package com.epam.junior.service;

import java.util.List;

import com.epam.junior.domain.Order;
import com.epam.junior.domain.OrderState;

public interface OrderService {

    public String doOrder(Order order);

    public OrderState getState(String orderId);

    public List<Order> getFullHistory();

    public List<Order> getCustomerHistory(String customer);

}