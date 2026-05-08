package com.lunchbox.businesses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lunchbox.menu.MenuItem;
import com.lunchbox.menu.MenuItemRepository;
import com.lunchbox.order.Order;
import com.lunchbox.order.OrderRepository;
import com.lunchbox.order.Status;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BusinessRepository businessRepository, OrderRepository orderRepository, MenuItemRepository menuItemRepository) {
        return args -> {
            Business burger  = businessRepository.save(new Business("The Burger Joint",   "restaurant", "42 Main St",      "(555) 100-0001", "contact@burgerjoint.com", "Juicy smash burgers and loaded fries made fresh to order.",         1, true, 4.7, 15));
            Business sushi   = businessRepository.save(new Business("Sakura Sushi",        "sushi",      "88 Ocean Ave",    "(555) 100-0002", "hello@sakurasushi.com",   "Authentic Japanese sushi rolls and sashimi platters.",               3, true, 4.8, 25));
            Business pizza   = businessRepository.save(new Business("Mama Rosa's Pizza",   "pizza",      "7 Vine Lane",     "(555) 100-0003", "orders@mamarosa.com",     "Wood-fired Neapolitan pizzas with homemade sauce.",                  2, true, 4.5, 20));
            Business chinese = businessRepository.save(new Business("Dragon Palace",       "chinese",    "19 Harbor Blvd",  "(555) 100-0004", "info@dragonpalace.com",   "Classic Chinese-American dishes including dim sum and noodles.",     1, true, 4.3, 18));
            Business mexican = businessRepository.save(new Business("Casa Verde",          "mexican",    "55 Sunset Rd",    "(555) 100-0005", "hola@casaverde.com",      "Street-style tacos, burritos, and fresh guacamole.",                 1, true, 4.6, 12));
            Business italian = businessRepository.save(new Business("Bella Italia",        "italian",    "300 Olive St",    "(555) 100-0006", "ciao@bellaitalia.com",    "Handmade pasta, tiramisu, and classic Italian favourites.",          2, true, 4.4, 22));
            Business dessert = businessRepository.save(new Business("Sweet Scoop",         "dessert",    "11 Cherry Ln",    "(555) 100-0007", "hi@sweetscoop.com",       "Artisan ice cream, waffles, and decadent sundaes.",                  1, true, 4.9, 8));
            Business cafe    = businessRepository.save(new Business("Morning Brew Cafe",   "cafe",       "5 Park Ave",      "(555) 100-0008", "hello@morningbrew.com",   "Specialty coffee, fresh pastries, and light lunch options.",         2, true, 4.6, 10));

            businessRepository.findAll().forEach(b -> log.info("Preloaded " + b));

            // Burger Joint menu
            menuItemRepository.save(new MenuItem(burger.getId(), "Classic Smash Burger",    "Double smash patty, American cheese, pickles, mustard",          9.99,  "Burgers"));
            menuItemRepository.save(new MenuItem(burger.getId(), "BBQ Bacon Burger",        "Smoked bacon, BBQ sauce, cheddar, crispy onions",                11.99, "Burgers"));
            menuItemRepository.save(new MenuItem(burger.getId(), "Veggie Burger",           "Black bean patty, avocado, lettuce, tomato, chipotle mayo",      9.49,  "Burgers"));
            menuItemRepository.save(new MenuItem(burger.getId(), "Loaded Fries",            "Thick-cut fries with cheese sauce and jalapeños",                4.99,  "Sides"));
            menuItemRepository.save(new MenuItem(burger.getId(), "Onion Rings",             "Beer-battered golden onion rings",                               3.99,  "Sides"));
            menuItemRepository.save(new MenuItem(burger.getId(), "Vanilla Milkshake",       "Hand-spun thick vanilla shake",                                  4.49,  "Drinks"));
            menuItemRepository.save(new MenuItem(burger.getId(), "Chocolate Milkshake",     "Hand-spun thick chocolate shake",                                4.49,  "Drinks"));

            // Sakura Sushi menu
            menuItemRepository.save(new MenuItem(sushi.getId(), "California Roll",          "Crab, avocado, cucumber, sesame",                                8.99,  "Rolls"));
            menuItemRepository.save(new MenuItem(sushi.getId(), "Spicy Tuna Roll",          "Fresh tuna, sriracha mayo, cucumber",                            10.99, "Rolls"));
            menuItemRepository.save(new MenuItem(sushi.getId(), "Dragon Roll",              "Shrimp tempura topped with avocado and eel sauce",               13.99, "Rolls"));
            menuItemRepository.save(new MenuItem(sushi.getId(), "Salmon Sashimi (6pc)",     "Premium sliced Atlantic salmon",                                 12.99, "Sashimi"));
            menuItemRepository.save(new MenuItem(sushi.getId(), "Tuna Sashimi (6pc)",       "Premium sliced bluefin tuna",                                    14.99, "Sashimi"));
            menuItemRepository.save(new MenuItem(sushi.getId(), "Miso Soup",                "Traditional tofu and seaweed miso soup",                         2.99,  "Sides"));
            menuItemRepository.save(new MenuItem(sushi.getId(), "Edamame",                  "Steamed salted soybeans",                                        3.49,  "Sides"));

            // Mama Rosa's Pizza menu
            menuItemRepository.save(new MenuItem(pizza.getId(), "Margherita",               "San Marzano tomato, fresh mozzarella, basil",                    12.99, "Pizzas"));
            menuItemRepository.save(new MenuItem(pizza.getId(), "Pepperoni",                "Mozzarella, signature tomato sauce, Italian pepperoni",          13.99, "Pizzas"));
            menuItemRepository.save(new MenuItem(pizza.getId(), "Four Cheese",              "Mozzarella, gorgonzola, parmesan, ricotta",                      14.49, "Pizzas"));
            menuItemRepository.save(new MenuItem(pizza.getId(), "Garlic Bread",             "Wood-fired garlic and herb focaccia",                            4.99,  "Sides"));
            menuItemRepository.save(new MenuItem(pizza.getId(), "Caesar Salad",             "Romaine, parmesan, house-made Caesar dressing, croutons",        7.99,  "Sides"));
            menuItemRepository.save(new MenuItem(pizza.getId(), "Tiramisu",                 "Classic Italian tiramisu with espresso and mascarpone",           6.49,  "Desserts"));

            // Dragon Palace menu
            menuItemRepository.save(new MenuItem(chinese.getId(), "Kung Pao Chicken",       "Wok-tossed chicken, peanuts, chillies, Sichuan sauce",           11.99, "Mains"));
            menuItemRepository.save(new MenuItem(chinese.getId(), "Beef Fried Rice",        "Wok-fried rice, egg, spring onion, soy-glazed beef",             10.99, "Mains"));
            menuItemRepository.save(new MenuItem(chinese.getId(), "Dim Sum Basket (6pc)",   "Mixed steamed dumplings — pork, prawn, vegetable",               8.99,  "Dim Sum"));
            menuItemRepository.save(new MenuItem(chinese.getId(), "Spring Rolls (4pc)",     "Crispy vegetable spring rolls with sweet chilli dip",            5.99,  "Starters"));
            menuItemRepository.save(new MenuItem(chinese.getId(), "Wonton Soup",            "Pork wontons in a clear ginger broth",                           6.49,  "Starters"));
            menuItemRepository.save(new MenuItem(chinese.getId(), "Chow Mein",              "Stir-fried egg noodles with vegetables and oyster sauce",        9.99,  "Mains"));

            // Casa Verde menu
            menuItemRepository.save(new MenuItem(mexican.getId(), "Street Tacos (3pc)",     "Corn tortillas, carne asada, onion, cilantro, salsa verde",      9.99,  "Tacos"));
            menuItemRepository.save(new MenuItem(mexican.getId(), "Chicken Burrito",        "Grilled chicken, rice, black beans, cheese, sour cream",         10.99, "Burritos"));
            menuItemRepository.save(new MenuItem(mexican.getId(), "Veggie Burrito",         "Roasted peppers, corn, guacamole, rice, black beans",            9.49,  "Burritos"));
            menuItemRepository.save(new MenuItem(mexican.getId(), "Guacamole & Chips",      "Fresh smashed avocado with tortilla chips",                       5.99,  "Sides"));
            menuItemRepository.save(new MenuItem(mexican.getId(), "Elote (Corn)",           "Grilled corn with cotija, chilli, lime, mayo",                   4.49,  "Sides"));
            menuItemRepository.save(new MenuItem(mexican.getId(), "Horchata",               "Classic cinnamon rice milk drink",                               3.49,  "Drinks"));

            // Bella Italia menu
            menuItemRepository.save(new MenuItem(italian.getId(), "Spaghetti Carbonara",    "Guanciale, egg yolk, pecorino romano, black pepper",             13.99, "Pasta"));
            menuItemRepository.save(new MenuItem(italian.getId(), "Penne Arrabbiata",       "San Marzano tomato, garlic, chilli, fresh basil",                11.99, "Pasta"));
            menuItemRepository.save(new MenuItem(italian.getId(), "Lasagne al Forno",       "Slow-braised beef ragù, béchamel, fresh pasta sheets",           14.99, "Pasta"));
            menuItemRepository.save(new MenuItem(italian.getId(), "Bruschetta",             "Toasted ciabatta, cherry tomato, basil, olive oil",              5.99,  "Starters"));
            menuItemRepository.save(new MenuItem(italian.getId(), "Tiramisu",               "House-made tiramisu with Kahlúa-soaked ladyfingers",             6.49,  "Desserts"));
            menuItemRepository.save(new MenuItem(italian.getId(), "Panna Cotta",            "Vanilla panna cotta with raspberry coulis",                       5.99,  "Desserts"));

            // Sweet Scoop menu
            menuItemRepository.save(new MenuItem(dessert.getId(), "Single Scoop",           "Choose from 12 artisan flavours",                                3.99,  "Ice Cream"));
            menuItemRepository.save(new MenuItem(dessert.getId(), "Double Scoop",           "Two generous scoops of your choice",                             5.49,  "Ice Cream"));
            menuItemRepository.save(new MenuItem(dessert.getId(), "Waffle Sundae",          "Warm waffle, two scoops, hot fudge, whipped cream",              8.99,  "Sundaes"));
            menuItemRepository.save(new MenuItem(dessert.getId(), "Banana Split",           "Banana, three scoops, chocolate, strawberry, caramel sauce",     9.49,  "Sundaes"));
            menuItemRepository.save(new MenuItem(dessert.getId(), "Milkshake",              "Thick hand-spun milkshake in any flavour",                        5.99,  "Drinks"));
            menuItemRepository.save(new MenuItem(dessert.getId(), "Affogato",               "Vanilla ice cream drowned in a double espresso shot",             4.99,  "Drinks"));

            // Morning Brew Cafe menu
            menuItemRepository.save(new MenuItem(cafe.getId(), "Flat White",                "Double ristretto, steamed whole milk",                            4.50,  "Coffee"));
            menuItemRepository.save(new MenuItem(cafe.getId(), "Oat Milk Latte",            "Double espresso, steamed oat milk",                               4.99,  "Coffee"));
            menuItemRepository.save(new MenuItem(cafe.getId(), "Cold Brew",                 "12-hour cold-steeped black coffee",                               4.49,  "Coffee"));
            menuItemRepository.save(new MenuItem(cafe.getId(), "Avocado Toast",             "Sourdough, smashed avocado, chilli flakes, poached egg",          9.99,  "Food"));
            menuItemRepository.save(new MenuItem(cafe.getId(), "Almond Croissant",          "Buttery croissant filled with almond frangipane",                 3.99,  "Food"));
            menuItemRepository.save(new MenuItem(cafe.getId(), "Granola Bowl",              "House granola, Greek yogurt, fresh berries, honey",               7.99,  "Food"));

            menuItemRepository.findAll().forEach(item -> log.info("Preloaded " + item));

            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> log.info("Preloaded " + order));
        };
    }
}
