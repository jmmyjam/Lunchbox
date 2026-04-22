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
            businessRepository.save(new Business("The Burger Joint", "restaurant", "42 Main St", "(555) 100-0001", "contact@burgerjoint.com", "Juicy smash burgers and loaded fries made fresh to order.", 1, true, 4.7, 15));
            businessRepository.save(new Business("Sakura Sushi", "sushi", "88 Ocean Ave", "(555) 100-0002", "hello@sakurasushi.com", "Authentic Japanese sushi rolls and sashimi platters.", 3, true, 4.8, 25));
            businessRepository.save(new Business("Mama Rosa's Pizza", "pizza", "7 Vine Lane", "(555) 100-0003", "orders@mamarosa.com", "Wood-fired Neapolitan pizzas with homemade sauce.", 2, true, 4.5, 20));
            businessRepository.save(new Business("Dragon Palace", "chinese", "19 Harbor Blvd", "(555) 100-0004", "info@dragonpalace.com", "Classic Chinese-American dishes including dim sum and noodles.", 1, true, 4.3, 18));
            businessRepository.save(new Business("Casa Verde", "mexican", "55 Sunset Rd", "(555) 100-0005", "hola@casaverde.com", "Street-style tacos, burritos, and fresh guacamole.", 1, true, 4.6, 12));
            businessRepository.save(new Business("Bella Italia", "italian", "300 Olive St", "(555) 100-0006", "ciao@bellaitalia.com", "Handmade pasta, tiramisu, and classic Italian favourites.", 2, true, 4.4, 22));
            businessRepository.save(new Business("Sweet Scoop", "dessert", "11 Cherry Ln", "(555) 100-0007", "hi@sweetscoop.com", "Artisan ice cream, waffles, and decadent sundaes.", 1, true, 4.9, 8));
            businessRepository.save(new Business("Morning Brew Cafe", "cafe", "5 Park Ave", "(555) 100-0008", "hello@morningbrew.com", "Specialty coffee, fresh pastries, and light lunch options.", 2, true, 4.6, 10));

            businessRepository.findAll().forEach(business -> log.info("Preloaded " + business));

            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });
        };
    }
}
