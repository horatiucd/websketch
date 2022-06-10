package com.hcd.websketch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.hcd.websketch.repository")
@EntityScan("com.hcd.websketch.domain")
@SpringBootApplication
public class WebsketchApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsketchApplication.class, args);
    }

}
