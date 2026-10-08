package com.springai.edu.module01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for Module 01: Foundations of Spring AI, Architecture & Model Providers.
 */
@SpringBootApplication(scanBasePackages = {"com.springai.edu.common", "com.springai.edu.module01"})
public class Module01Application {

    public static void main(String[] args) {
        SpringApplication.run(Module01Application.class, args);
    }
}
