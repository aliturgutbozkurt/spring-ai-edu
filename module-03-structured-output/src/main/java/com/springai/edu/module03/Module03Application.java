package com.springai.edu.module03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for Module 03: Structured Output, BeanOutputConverter & Type-Safe Extraction.
 */
@SpringBootApplication(scanBasePackages = {"com.springai.edu.common", "com.springai.edu.module03"})
public class Module03Application {

    public static void main(String[] args) {
        SpringApplication.run(Module03Application.class, args);
    }
}
