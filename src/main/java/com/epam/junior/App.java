package com.epam.junior;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

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
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer(); 
        try {
            ObjectName name = new ObjectName("com.epam.junior.service.simple:type=SimpleOrderService"); 
            mbs.registerMBean(orderService, name);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        

        
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
        
        while(true) {
            
            logger.info("still running");
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //logger.info("Main end....");

    }
}
