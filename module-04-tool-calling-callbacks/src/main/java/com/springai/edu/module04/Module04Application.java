package com.springai.edu.module04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for Module 04: Tool Calling & Function Callbacks.
 */
@SpringBootApplication(scanBasePackages = {"com.springai.edu.common", "com.springai.edu.module04"})
public class Module04Application {

    public static void main(String[] args) {
        SpringApplication.run(Module04Application.class, args);
    }
}
