package com.kestrel.weddingbookkeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeddingBookKeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeddingBookKeeperApplication.class, args);
    }
}
