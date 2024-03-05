package ru.practicum.ewm.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ru.practicum.ewm.service, ru.practicum.utils, ru.practicum.stats.client")
public class EwnServiceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(EwnServiceApplication.class, args);
    }
}
