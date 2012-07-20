package com.epam.junior.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.junior.App;
import com.epam.junior.service.RestaurantRepository;

public class OrderBuilder {

    private Order order = new Order();
    private RestaurantRepository repo;
    private static Logger logger = LoggerFactory.getLogger(OrderBuilder.class);

    private OrderBuilder(RestaurantRepository repo) {
        this.repo = repo;
    }
    
    public OrderBuilder with(Food food) {
        order.addItem(new OrderItem(food));
        return this;
    }
    public OrderBuilder withId(String foodId) {
        logger.trace("searching for food={}", foodId);
        Food food = repo.findFoodById(foodId);
        order.addItem(new OrderItem(food));
        return this;
    }
    
    public OrderBuilder with(Food food, Integer quantity) {
        order.addItem(new OrderItem(food,quantity));
        return this;
    }

    public OrderBuilder withId(String foodId, Integer quantity) {
        Food food = repo.findFoodById(foodId);
        order.addItem(new OrderItem(food,quantity));
        return this;
    }

    public OrderBuilder by(String customer) {
        order.setCustomer(customer);
        return this;
    }

    public OrderBuilder to(Address deliveryAddress) {
        order.setDeliveryAddress(deliveryAddress);
        return this;
    }

    public Order build() {
        return order;
    }

    public static OrderBuilder create(RestaurantRepository repo) {
        // TODO Auto-generated method stub
        return new OrderBuilder(repo);
    }
}
