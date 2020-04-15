package com;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingCartMain {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartMain.class,args);
        System.out.println("Hello Spring boot Application successfully created");
    }
}
