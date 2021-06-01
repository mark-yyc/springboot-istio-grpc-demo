package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GrpcClientCommandLineRunner implements CommandLineRunner {
    @Autowired
    GrpcClientConfiguration configuration;

    @Override
    public void run(String... args) {
        // 开启gRPC客户端
        configuration.start();

        // 添加客户端关闭的逻辑
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                configuration.shutdown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }
}
