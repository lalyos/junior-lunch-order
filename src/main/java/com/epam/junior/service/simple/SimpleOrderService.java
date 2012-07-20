package com.epam.junior.service.simple;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.junior.App;
import com.epam.junior.domain.Order;
import com.epam.junior.domain.OrderNotFountException;
import com.epam.junior.domain.OrderState;
import com.epam.junior.service.OrderService;

public class SimpleOrderService implements OrderService, SimpleOrderServiceMBean {

    private static Integer orderId = 0;
    private Map<String, Order> orders = new HashMap<String, Order>();
    private static Logger logger = LoggerFactory.getLogger(SimpleOrderService.class);
    
    public String doOrder(Order order) {
        String newId =  "" + orderId++;
        order.setId(newId);
        order.setState(OrderState.RECEIVED);
        orders.put(newId, order);
        return newId;
    }
    
    public OrderState getState(String orderId) {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new OrderNotFountException(orderId); 
        }
        return order.getState();
    }
    
    public List<Order> getFullHistory() {
        return new ArrayList<Order>(orders.values());
    }
    public List<Order> getCustomerHistory(String customer) {
       List<Order> history = new ArrayList<Order>();
        for (Order order : orders.values()) {
            if (order.getCustomer().equals(customer)) {
                history.add(order);
            }
        }
        return history;
    }

    public String readOrderState(String orderId) {
        return getState(orderId).name();
    }

    public void changeOrderState(String orderId, String state) {
        
        Order order = orders.get(orderId);
        logger.info("about to change order state {} to {}", orderId, state);
        if (order != null) {
            logger.info("order found");
            order.setState(OrderState.valueOf(state));
        }
        else {
            logger.warn("order found");            
        }
    }
}
