package com.epam.junior.service;

import java.util.List;

import com.epam.junior.domain.Food;
import com.epam.junior.domain.Restaurant;

public interface RestaurantRepository {

	public List<Restaurant> getAllRestaurants();
	public Food findFoodById(String id);
	public void listAllMenu();
	
}
