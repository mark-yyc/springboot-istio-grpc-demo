package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GrpcCommandLineRunner implements CommandLineRunner {
    @Autowired
    GrpcServerConfiguration configuration;

    @Override
    public void run(String... args) throws Exception {
        configuration.start();
        configuration.block();
    }
}
