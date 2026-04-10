package com.lunchbox.businesses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lunchbox.order.Order;
import com.lunchbox.order.OrderRepository;
import com.lunchbox.order.Status;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BusinessRepository businessRepository, OrderRepository orderRepository) {
        return args -> {
            businessRepository.save(new Business("Generic Restaurant", "restaurant", "123 Address Dr", "(000) 000-0000",
                    "email@example.com", "description here", 1, true));
            businessRepository.save(new Business("Generic Business", "retail", "123 Here St", "(000) 000-0000",
                    "email@example.com", "description here", 2, false));

            businessRepository.findAll().forEach(business -> log.info("Preloaded " + business));

            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });
        };
    }
}
