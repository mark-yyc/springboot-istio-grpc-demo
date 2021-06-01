package com.example.controller;

import com.example.config.GrpcClientConfiguration;
import com.example.grpc.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    GrpcClientConfiguration configuration;

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "mark", required = false) String name) {
        // 构建一个请求
        HelloWorldService.HelloRequest request = HelloWorldService.HelloRequest
                .newBuilder()
                .setName(name)
                .build();

        // 使用stub发送请求至服务端
        HelloWorldService.HelloResponse response = configuration.getStub().sayHello(request);
        return response.getMessage();
    }
}
