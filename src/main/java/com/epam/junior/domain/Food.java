package com.epam.junior.domain;

import java.io.PrintStream;

public class Food {
	
    private String id;
	private String name;
	private int price;
	private boolean isvegan;
	private int calories;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isIsvegan() {
		return isvegan;
	}
	public void setIsvegan(boolean isvegan) {
		this.isvegan = isvegan;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Food [id=" + id + ", name=" + name + ", price=" + price + ", isvegan=" + isvegan + ", calories="
                + calories + "]";
    }
	
    public void printFood(PrintStream writer) {
        writer.println(String.format("\t[%s] %-30s : %-4d (%d kCal) v:%b", id, name, price, calories, isvegan));
    }

}
