package com.example.coffeemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoffeeMachineApplication {
    static final Logger log = LoggerFactory.getLogger(CoffeeMachineApplication.class);
    public static void main(String[] args) {
        log.debug("Coffee-machine is starting");
        SpringApplication.run(CoffeeMachineApplication.class, args);
    }

}
