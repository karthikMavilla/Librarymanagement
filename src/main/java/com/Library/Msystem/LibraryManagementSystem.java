package com.Library.Msystem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.Library.Msystem.model")
public class LibraryManagementSystem {
    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystem.class, args);
    }
}
