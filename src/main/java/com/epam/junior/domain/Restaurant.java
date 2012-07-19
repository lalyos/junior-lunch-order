package com.epam.junior.domain;

import java.io.PrintStream;
import java.util.List;


public class Restaurant {

    private String id;
	private String name;
	
	private Address address;
	private List<Food> menu;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Food> getMenu() {
		return menu;
	}
	public void setMenu(List<Food> menu) {
		this.menu = menu;
	}
	
	
	public String listRestaurantMenu()
	{
		StringBuilder sb=new StringBuilder();
		for (Food food : menu) {
			sb.append(food);
		}
		return sb.toString();
	}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Restaurant [id=" + id + ", name=" + name + ", address=" + address + "]";
    }
    public void printMenu(PrintStream out) {
        out.println(String.format("\n=====\n  %s - %s \n=====", name, address));
        for (Food food : menu) {
            food.printFood(out);
        }
    }
	

    
	
}
