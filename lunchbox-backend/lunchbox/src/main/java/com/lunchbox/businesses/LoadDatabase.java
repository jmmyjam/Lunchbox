package com.lunchbox.businesses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BusinessRepository repository) {
        return args -> {
            log.info("Preloading " + repository
                    .save(new Business("Generic Restaurant", "restaurant", "123 Address Dr", "(000) 000-0000",
                            "email@example.com", "description here", 1, true)));
            log.info("Preloading "
                    + repository.save(new Business("Generic Business", "retail", "123 Here St", "(000) 000-0000",
                            "email@example.com", "description here", 2, false)));
        };
    }
}
