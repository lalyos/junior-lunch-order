package com.epam.junior.domain;

public class OrderItem {
    private Food food;
    private Integer quantity;
    
    
    public OrderItem() {
    }
    public OrderItem(Food food) {
        this.food = food;
        this.quantity = 1;
    }
    
    public OrderItem(Food food, Integer quantity) {
        super();
        this.food = food;
        this.quantity = quantity;
    }
    public Food getFood() {
        return food;
    }
    public void setFood(Food food) {
        this.food = food;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getTotal() {
        return food.getPrice() * quantity;
    }
    
}
