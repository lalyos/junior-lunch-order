package com.epam.junior.domain;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    String id;
    Map<String, OrderItem> foodItemsMap = new HashMap<String, OrderItem>();
    String customer;
    Address deliveryAddress;
    OrderState state;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<OrderItem> getItems() {
        return new ArrayList(foodItemsMap.values());
    }
    
    public void addItem(OrderItem item) {
        String itemKey = item.getFood().getId();
        OrderItem existingItem = foodItemsMap.get(itemKey);
        if (existingItem == null) {
            foodItemsMap.put(itemKey, item);
        } else {
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        }
    }

    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public Address getDeliveryAddress() {
        return deliveryAddress;
    }
    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    public OrderState getState() {
        return state;
    }
    public void setState(OrderState state) {
        this.state = state;
    }
    
    public Integer getTotal() {
        Integer total = 0;
        for (OrderItem item : foodItemsMap.values()) {
            total += item.getTotal();
        }
        return total;
    }
    
    public void print(PrintStream out) {
        StringBuffer items = new StringBuffer();
        for (OrderItem item : foodItemsMap.values()) {
            items.append(String.format("%n\t%-5s : %-30s %-5d (%-5s) ", item.getQuantity(), item.getFood().getName(), item.getFood().getPrice(), item.getFood().getId()));
        }
        out.println(String.format("%n%s [state=%s]: %s%nTOTAL=%d ", customer, state, items, getTotal()));
    }
}
