package ru.practicum.stats.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ru.practicum.stats.server, ru.practicum.utils")
public class StatsServerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(StatsServerApplication.class, args);
    }

}
