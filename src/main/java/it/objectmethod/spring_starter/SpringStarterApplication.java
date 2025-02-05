package it.objectmethod.spring_starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class SpringStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringStarterApplication.class, args);
    }
}