package it.objectmethod.spring_starter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringStarterApplication.class, args);
    }

    //print to terminal a list of all beans created on start
    @Bean
    CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            final String[] beanNames = ctx.getBeanDefinitionNames();
            int n = 0;
            for (final String beanName : beanNames) {
                System.out.printf("Bean name -----> %s\n", beanName);
                n++;
            }
            System.out.println("Number of beans: " + n);
        };
    }
}