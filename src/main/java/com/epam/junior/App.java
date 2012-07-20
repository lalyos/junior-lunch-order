package com.epam.junior;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.junior.domain.Address;
import com.epam.junior.domain.Order;
import com.epam.junior.domain.OrderBuilder;
import com.epam.junior.service.OrderService;
import com.epam.junior.service.RestaurantRepository;
import com.epam.junior.service.simple.SimpleOrderService;
import com.epam.junior.service.xml.XmlBasedRestaurantRepository;

public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Main begin....");

        RestaurantRepository restaurantRepo = new XmlBasedRestaurantRepository();
        restaurantRepo.listAllMenu();
        
        OrderService orderService = new SimpleOrderService();
        Address deliveryAddress = new Address();
        orderService.doOrder(
                OrderBuilder.create(restaurantRepo)
                .by("lalyos")
                .withId("fld10").withId("shr0").withId("ancs2").withId("ancs2")
                .to(deliveryAddress).build()
        );

        orderService.doOrder(
                OrderBuilder.create(restaurantRepo)
                .by("jeno")
                .withId("fld7").withId("ancs0").withId("ancs0").withId("fld8")
                .to(deliveryAddress).build()
        );

        for (Order order : orderService.getFullHistory()) {
            order.print(System.out);
        }
        
        logger.info("Main end....");
    }
}
