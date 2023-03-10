package com.salesianostriana.dam.trianafy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableJpaAuditing
public class TrianafyBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrianafyBaseApplication.class, args);
    }

}
