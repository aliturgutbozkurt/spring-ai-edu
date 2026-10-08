package com.springai.edu.module02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for Module 02: Prompt Engineering, Prompt Templates & Context Management.
 */
@SpringBootApplication(scanBasePackages = {"com.springai.edu.common", "com.springai.edu.module02"})
public class Module02Application {

    public static void main(String[] args) {
        SpringApplication.run(Module02Application.class, args);
    }
}
