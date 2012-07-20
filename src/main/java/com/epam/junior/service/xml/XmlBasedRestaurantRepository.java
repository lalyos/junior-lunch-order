package com.epam.junior.service.xml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.junior.domain.Address;
import com.epam.junior.domain.Food;
import com.epam.junior.domain.Restaurant;
import com.epam.junior.service.RestaurantRepository;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class XmlBasedRestaurantRepository implements RestaurantRepository {

	private List<Restaurant> restaurants;
	private Map<String,Food> foodMap = new HashMap<String, Food>();
	
	static Logger logger = LoggerFactory.getLogger(XmlBasedRestaurantRepository.class);

	public XmlBasedRestaurantRepository(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public XmlBasedRestaurantRepository() {
		restaurants = new ArrayList<Restaurant>();
		unmarshalling();
		setfoodIds();

	}

	private void setfoodIds() {
        for (Restaurant nextRestaurant : restaurants) {
            String restaurantId = nextRestaurant.getId();
            Integer id = 0;
            for (Food nextFood : nextRestaurant.getMenu()) {
                String foodId = restaurantId + id++;
                nextFood.setId(foodId);
                foodMap.put(foodId, nextFood);
            }
        }
        
    }

    public List<Restaurant> getAllRestaurants() {
		return restaurants;
	}

	public void unmarshalling() {
		try {

			XStream xstream = new XStream(new StaxDriver());
			xstream.alias("restaurant", Restaurant.class);
			xstream.alias("food", Food.class);
			xstream.alias("address", Address.class);

			InputStream fin = getClass().getClassLoader().getResourceAsStream("restaurants.xml");
			
			restaurants= (List<Restaurant>) xstream.fromXML(fin);
			
		} catch (Exception ex) {
			logger.info(ex.toString());
			loadList();
		}
	}


	public void marshalling() {
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("restaurant", Restaurant.class);
		xstream.alias("food", Food.class);
		xstream.alias("address", Address.class);
		loadList();
		String xml = xstream.toXML(restaurants);
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("test.xml"));
			out.write(xml);
			out.close();
		} catch (IOException e) {
			System.out.println("Exception ");

		}
	}

	public void loadList() {

		Food food1 = new Food();
		food1.setCalories(123);
		food1.setIsvegan(true);
		food1.setName("Salata");
		food1.setPrice(12);

		Food food2 = new Food();
		food2.setCalories(123);
		food2.setIsvegan(false);
		food2.setName("Hus");
		food2.setPrice(12);

		Address address1 = new Address();
		address1.setCity("Budapest");
		address1.setCountry("Hu");
		address1.setStreet("Ady");
		address1.setZipcode("232131");

		Address address2 = new Address();
		address2.setCity("Gyor");
		address2.setCountry("Hu");
		address2.setStreet("Jozsef");
		address2.setZipcode("232132");

		Restaurant res1 = new Restaurant();
		res1.setName("Dudabest");
		ArrayList<Food> foodList1 = new ArrayList<Food>();
		foodList1.add(food1);
		foodList1.add(food2);
		res1.setMenu(foodList1);
		res1.setAddress(address1);

		Restaurant res2 = new Restaurant();
		res2.setName("Dudabest");
		ArrayList<Food> foodList2 = new ArrayList<Food>();
		foodList2.add(food2);
		res2.setMenu(foodList2);
		res2.setAddress(address2);

		restaurants.add(res1);
		restaurants.add(res2);
	}

	@Override
	public String toString() {
		return "RestaurantRepositoryImp [restaurants=" + restaurants + "]";
	}

	public void listAllMenu()
	{
		
		for (Restaurant  res: restaurants) {
			res.printMenu(System.out);
		}
	}

    public Food findFoodById(String id) {
        return foodMap.get(id);
    }

}
